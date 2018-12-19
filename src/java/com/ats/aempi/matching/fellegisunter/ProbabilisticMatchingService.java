package com.ats.aempi.matching.fellegisunter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.ats.aempi.ApplicationException;
import com.ats.aempi.InitializationException;
import com.ats.aempi.blocking.RecordPairIterator;
import com.ats.aempi.blocking.RecordPairSource;
import com.ats.aempi.configuration.MatchField;
import com.ats.aempi.context.Context;
import com.ats.aempi.dao.PersonLinkDao;
import com.ats.aempi.matching.AbstractMatchingService;
import com.ats.aempi.matching.MatchingService;
import com.ats.aempi.model.ComparisonVector;
import com.ats.aempi.model.Record;
import com.ats.aempi.model.RecordPair;
import com.ats.aempi.stringcomparison.StringComparisonService;

public class ProbabilisticMatchingService extends AbstractMatchingService implements MatchingService
{
	private MatchConfiguration matchConfiguration;
	private HashMap<String,MatchField> matchFieldByName;
	private PersonLinkDao personLinkDao;
	private String[] matchFieldNames;
	private MatchField[] matchFields;
	private boolean initialized = false;
	private FellegiSunterParameters fellegiSunterParams;
	private static final Double MIN_MARGINAL_VALUE = 0.000001;
	private static final String FELLEGI_SUNTER_CONFIG_FILE_NAME = "FellegiSunterConfiguration.ser";
	
	public void init() throws InitializationException {
		loadConfiguration();
		try {
			fellegiSunterParams = loadParameters(matchConfiguration.getConfigFileDirectory());
			initialized = true;
		} catch (Throwable t) {
			try {
				linkRecords();
			} catch (Exception e) {
				log.error("Failed while initializing the probabilistic matching service: " + e, e);
				initialized = false;
			}
		}		
	}
	public List<String> matchEmpi(Record record){
		return null;
	}
	
	public List<String> matchPersonID(Record record){
		return null;
	}
	public Set<RecordPair> match(Record record) throws ApplicationException {
		log.debug("Looking for matches on record " + record);
		if (!initialized) {
			throw new ApplicationException("Matching service has not been initialized yet.");
		}
		List<RecordPair> pairs = Context.getBlockingService().findCandidates(record);
		Set<RecordPair> matches = new java.util.HashSet<RecordPair>();
		scoreRecordPairs(pairs);
		calculateRecordPairWeights(pairs, fellegiSunterParams);
		
		// Apply Fellegi-Sunter decision rule
		for (RecordPair pair : pairs) {
			if (pair.getWeight() >= fellegiSunterParams.getUpperBound()) {
				matches.add(pair);
			} else if (pair.getWeight() > fellegiSunterParams.getLowerBound()) {
				// This is a possible match; need to add it to the list for review
			}
		}
		return matches;
	}

	public void linkRecords() {
		List<RecordPair> pairs = getRecordPairs();
		scoreRecordPairs(pairs);
		fellegiSunterParams = new FellegiSunterParameters(matchConfiguration.getMatchFields().size());
		fellegiSunterParams.setMu(matchConfiguration.getFalsePositiveProbability());
		fellegiSunterParams.setLambda(matchConfiguration.getFalseNegativeProbability());
		calculateVectorFrequencies(pairs, fellegiSunterParams);
		estimateMarginalProbabilities(fellegiSunterParams);
		calculateRecordPairWeights(pairs, fellegiSunterParams);
		orderRecordPairsByWeight(pairs);
		calculateMarginalProbabilities(pairs, fellegiSunterParams);
		calculateBounds(pairs, fellegiSunterParams);
		saveParameters(matchConfiguration.getConfigFileDirectory(), fellegiSunterParams);
		initialized = true;
	}

	public FellegiSunterParameters getFellegiSunterParameters() {
		return fellegiSunterParams;
	}
	
	public List<RecordPair> getRecordPairs() {
		RecordPairSource source = Context.getBlockingService().getRecordPairSource();
		List<RecordPair> pairs = new ArrayList<RecordPair>();
		for (RecordPairIterator iter = source.iterator(); iter.hasNext(); ) {
			pairs.add(iter.next());
		}
		return pairs;
	}

	public void scoreRecordPairs(List<RecordPair> pairs) {
		StringComparisonService comparisonService = Context.getStringComparisonService();
		for (RecordPair pair : pairs) {
			pair.setComparisonVector(new ComparisonVector(matchFields));
			for (int i=0; i < matchFieldNames.length; i++) {
				String fieldName = matchFieldNames[i];
				Object value1 = pair.getLeftRecord().get(fieldName);
				Object value2 = pair.getRightRecord().get(fieldName);
				double distance = comparisonService.score(matchFields[i].getComparatorFunction().getFunctionName(), 
						matchFields[i].getComparatorFunction().getParameterMap(), value1, value2);
				pair.getComparisonVector().setScore(i, distance);
			}
			log.trace("Comparing records " + pair.getLeftRecord().getRecordId() + " and " + pair.getRightRecord().getRecordId() + " got vector " +
					pair.getComparisonVector().getBinaryVectorString());
		}
	}

	public void calculateVectorFrequencies(List<RecordPair> pairs, FellegiSunterParameters fellegiSunterParams) {
		for (int i=0; i < fellegiSunterParams.getVectorCount(); i++) {
			fellegiSunterParams.setVectorFrequency(i, 0);
		}
		for (RecordPair pair : pairs) {
			ComparisonVector vector = pair.getComparisonVector();
			if (vector.getBinaryVectorValue() == 2) {
				System.out.println(vector.getBinaryVectorString() + "=>" + vector.getScoreVectorString() + "=>" + this.getRecordPairMatchFields(pair));
			}
			fellegiSunterParams.incrementVectorFrequency(vector.getBinaryVectorValue());
		}
	}
	
	public void calculateRecordPairWeights(List<RecordPair> pairs, FellegiSunterParameters fellegiSunterParams) {
		for (RecordPair pair : pairs) {
			calculateRecordPairWeight(pair, fellegiSunterParams);
		}
	}
	
	private void calculateRecordPairWeight(RecordPair pair, FellegiSunterParameters fellegiSunterParams) {
		ComparisonVector vector = pair.getComparisonVector();
		double weight=0.0;
		for (int i=0; i < fellegiSunterParams.getFieldCount(); i++) {
			if (vector.getBinaryVector()[i] == 1) {
				Double numerator = fellegiSunterParams.getMValue(i);
				Double denominator = fellegiSunterParams.getUValue(i);
				numerator = adjustMinimumValue(numerator);
				denominator = adjustMinimumValue(denominator);
				weight += Math.log(numerator/denominator) / Math.log(2.0);
			} else {
				Double numerator = (1.0-fellegiSunterParams.getMValue(i));
				Double denominator = (1.0-fellegiSunterParams.getUValue(i));
				numerator = adjustMinimumValue(numerator);
				denominator = adjustMinimumValue(denominator);
				weight += Math.log(numerator/denominator) / Math.log(2.0);
			}
		}
		pair.setWeight(weight);
	}

	private Double adjustMinimumValue(Double numerator) {
		if (numerator.doubleValue() < MIN_MARGINAL_VALUE.doubleValue()) {
			numerator = MIN_MARGINAL_VALUE;
		}
		return numerator;
	}

	public List<RecordPair> orderRecordPairsByWeight(List<RecordPair> pairs) {
		Collections.sort(pairs, new RecordPairComparator());
		return pairs;
	}
	
	public void calculateMarginalProbabilities(List<RecordPair> pairs, FellegiSunterParameters fellegiSunterParams) {
		for (RecordPair pair : pairs) {
			ComparisonVector vector = pair.getComparisonVector();
			vector.calculateProbabilityGivenMatch(fellegiSunterParams.getMValues());
			vector.calculateProbabilityGivenNonmatch(fellegiSunterParams.getUValues());
		}
	}
	
	public void estimateMarginalProbabilities(FellegiSunterParameters fellegiSunterParams) {
		ExpectationMaximizationEstimator estimator = new ExpectationMaximizationEstimator();
		estimator.estimateMarginalProbabilities(fellegiSunterParams, 0.9, 0.1, 0.01, 40);
	}
	
	public String getRecordPairMatchFields(RecordPair pair) {
		StringBuffer sb = new StringBuffer("{ ");
		for (int i=0; i < matchFieldNames.length; i++) {
			String fieldName = matchFieldNames[i];
			Object value1 = pair.getLeftRecord().get(fieldName);
			Object value2 = pair.getRightRecord().get(fieldName);
			sb.append("[").append(value1).append(",").append(value2).append("]");
			if (i < matchFieldNames.length-1) {
				sb.append(",");
			}
		}
		return sb.append(" }").toString();
	}

	public void calculateBounds(List<RecordPair> pairs, FellegiSunterParameters fellegiSunterParams) {
		double sum = 0;
		int index = 0;
		for (RecordPair pair : pairs) {
			sum += pair.getComparisonVector().getVectorProbGivenM();
			if (sum > fellegiSunterParams.getLambda()) {
				break;
			}
			index++;
		}
		log.debug("Sum: " + sum + ", lambda: " + fellegiSunterParams.getLambda() + ", index: " + (index-1) + ", value: " + pairs.get(index-1).getWeight());
		fellegiSunterParams.setLowerBound(pairs.get(index-1).getWeight());
		
		sum = 0;
		index = pairs.size()-1;
		for (int i = index; i >= 0; i--) {
			sum += pairs.get(i).getComparisonVector().getVectorProbGivenU();
			if (sum > fellegiSunterParams.getMu()) {
				break;
			}
		}
		log.debug("Sum: " + sum + ", mu: " + fellegiSunterParams.getMu() + ", index: " + index + ", value: " + pairs.get(index).getWeight());
		fellegiSunterParams.setUpperBound(pairs.get(index).getWeight());
	}
	
	private void saveParameters(String configDirectory, FellegiSunterParameters params) {
		String filename = configDirectory + "/" + FELLEGI_SUNTER_CONFIG_FILE_NAME;
		log.debug("Attempting to save the Fellegi-Sunter configuration file into: " + filename);
		try {
			ObjectOutputStream ois = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(filename)));
			ois.writeObject(params);
			ois.flush();
			ois.close();
		} catch (Exception e) {
			log.error("Failed while saving the Fellegi-Sunter configuration file: " + e.getMessage(), e);
			throw new RuntimeException("Failed while saving the Fellegi-Sunter configuration: " + e.getMessage());
		}		
	}

	private FellegiSunterParameters loadParameters(String configDirectory) {
		String filename = configDirectory + "/" + FELLEGI_SUNTER_CONFIG_FILE_NAME;
		log.debug("Attempting to load the configuration file for the migration log from: " + filename);
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(filename)));
			FellegiSunterParameters params = (FellegiSunterParameters) ois.readObject();
			ois.close();
			return params;
		} catch (Exception e) {
			log.error("Failed while loading the Fellegi-Sunter configuration file: " + e.getMessage(), e);
			throw new RuntimeException("Failed while loading the Fellegi-Sunter configuration: " + e.getMessage());
		}
	}
	
	private void loadConfiguration() {
		Object obj = Context.getConfiguration().lookupConfigurationEntry(ProbabilisticMatchingConstants.PROBABILISTIC_MATCHING_FIELDS_REGISTRY_KEY);
		if (obj == null) {
			log.error("Probabilistic matching service has not been configured properly; no match fields have been defined.");
			throw new RuntimeException("Probabilistic maching service has not been configured properly.");
		}
		matchConfiguration = (MatchConfiguration) obj;
		matchFieldByName = new HashMap<String,MatchField>();
		matchFieldNames = new String[matchConfiguration.getMatchFields().size()];
		matchFields = new MatchField[matchConfiguration.getMatchFields().size()];
		int index=0;
		for (MatchField field : matchConfiguration.getMatchFields()) {
			matchFieldNames[index] = field.getFieldName();
			matchFields[index] = field;
			matchFieldByName.put(field.getFieldName(), field);
			index++;
		}
		log.debug("Matching service " + getClass().getName() + " will perform matching using " + toString());		
	}

	public PersonLinkDao getPersonLinkDao() {
		return personLinkDao;
	}

	public void setPersonLinkDao(PersonLinkDao personLinkDao) {
		this.personLinkDao = personLinkDao;
	}
	
	public static void main(String[] args) {
		ProbabilisticMatchingService service = new ProbabilisticMatchingService();
		String baseDirectory = Context.getAEmpiHome();
		System.out.println(service.loadParameters(baseDirectory + "/" + "conf"));
	}
}
