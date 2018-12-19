package com.ats.aempi.matching.fellegisunter;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.ats.aempi.configuration.MatchField;

public class MatchConfiguration
{
	private static final long serialVersionUID = -4012644666481353904L;
	
	private float falseNegativeProbability;
	private float falsePositiveProbability;
	private List<MatchField> matchFields;
	private String configFileDirectory;
	
	public MatchConfiguration() {
		matchFields = new ArrayList<MatchField>();
	}
	
	public float getFalseNegativeProbability() {
		return falseNegativeProbability;
	}

	public void setFalseNegativeProbability(float falseNegativeProbability) {
		this.falseNegativeProbability = falseNegativeProbability;
	}

	public float getFalsePositiveProbability() {
		return falsePositiveProbability;
	}

	public void setFalsePositiveProbability(float falsePositiveProbability) {
		this.falsePositiveProbability = falsePositiveProbability;
	}

	public void addMatchField(MatchField matchField) {
		matchFields.add(matchField);
	}

	public List<MatchField> getMatchFields() {
		return matchFields;
	}

	public void setMatchFields(List<MatchField> matchFields) {
		this.matchFields = matchFields;
	}

	public String getConfigFileDirectory() {
		return configFileDirectory;
	}

	public void setConfigFileDirectory(String configFileDirectory) {
		this.configFileDirectory = configFileDirectory;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("falseNegativeProbability", falseNegativeProbability).append(
				"falsePositiveProbability", falsePositiveProbability).append("matchFields", matchFields).append(
				"configFileDirectory", configFileDirectory).toString();
	}
}
