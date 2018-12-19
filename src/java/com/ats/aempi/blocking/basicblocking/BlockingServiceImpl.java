package com.ats.aempi.blocking.basicblocking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ats.aempi.blocking.BlockingService;
import com.ats.aempi.blocking.RecordPairSource;
import com.ats.aempi.configuration.BaseField;
import com.ats.aempi.configuration.CustomField;
import com.ats.aempi.context.Context;
import com.ats.aempi.dao.BlockingDao;
import com.ats.aempi.model.Criteria;
import com.ats.aempi.model.Criterion;
import com.ats.aempi.model.Operation;
import com.ats.aempi.model.Record;
import com.ats.aempi.model.RecordPair;
import com.ats.aempi.service.impl.BaseServiceImpl;
import com.ats.aempi.transformation.TransformationService;

public class BlockingServiceImpl extends BaseServiceImpl implements BlockingService
{
	private BlockingDao blockingDao;
	private List<BlockingRound> blockingRounds;
	
	public void init() {
		log.trace("Initializing the Blocking Service");
	}
	
	public RecordPairSource getRecordPairSource(List<BlockingRound> blockingRounds) {
		BasicRecordPairSource recordPairSource = new BasicRecordPairSource();
		recordPairSource.setBlockingDao(getBlockingDao());
		recordPairSource.setBlockingRounds(blockingRounds);
		recordPairSource.init();
		return recordPairSource;
	}
	
	public RecordPairSource getRecordPairSource() {
		return getRecordPairSource(getBlockingRounds());
	}
	
	//panmin-20150625
	public List<String> findMatchEmpi(Record record){
		
		blockingRounds = getBlockingRounds();

		int count = 1;

		List<String> records = new java.util.ArrayList<String>();

		for (BlockingRound round : blockingRounds) {
			
			List<BaseField> fields = round.getFields();

			Object value;
			
			int SqlFlag=0;
			
			String mySQL= "select distinct(EMPI) from Person where date_Voided is null ";

			for (BaseField field : fields) {
				
				if (record.getAsString(field.getFieldName())==null) {		
					log.fatal("关联条件" + count + "中字段：" + field.getFieldName() + "记录为空，此次查询跳过"); 
					SqlFlag =1;
					continue;
				}			
				
				SqlFlag =0 ;
				
				if (field instanceof CustomField) {
					CustomField custom = (CustomField) field;
					String sourceValue = record.getAsString(custom.getSourceFieldName());
					if (sourceValue == null) {
						return null;
					}
					value = generateTransformedValue(custom, sourceValue);
					if (value == null) {
						return null;
					}
				} else {
					value = record.get(field.getFieldName());
					
					if (value != null) {
						
						mySQL = mySQL + " and " + TransFieldName(field.getFieldName()) + " = '" +  value.toString() + "'";
						
						log.fatal("关联条件" + count + "中增加关联条件字段:" + field.getFieldName() + "= " + value.toString()); 
	
					}

				}
				
			}
			
			if(SqlFlag==0){
				
				List<String> TempList =  new ArrayList<String>();
			
				TempList = blockingDao.EmpiBlockRecords(mySQL);
				
				if (TempList != null) {
					for (String Tlist : TempList) {
						if (Tlist != null && !records.contains(Tlist))
							records.add(Tlist);
					}
				}
			}

			count++;
		}
		

		return records;
	}
	
	
	//panmin-20150714
		public List<String> findMatchPersonID(Record record){
			
			blockingRounds = getBlockingRounds();

			int count = 1;

			List<String> records = new java.util.ArrayList<String>();

			for (BlockingRound round : blockingRounds) {
				
				List<BaseField> fields = round.getFields();

				Object value;
				
				int SqlFlag=0;
				
				String mySQL= "select distinct(Person_ID) from Person where date_Voided is null ";

				for (BaseField field : fields) {
					
					if (record.getAsString(field.getFieldName())==null) {		
						log.fatal("关联条件" + count + "中字段：" + field.getFieldName() + "记录为空，此次查询跳过"); 
						SqlFlag =1;
						continue;
					}			
					
					SqlFlag =0 ;
					
					if (field instanceof CustomField) {
						CustomField custom = (CustomField) field;
						String sourceValue = record.getAsString(custom.getSourceFieldName());
						if (sourceValue == null) {
							return null;
						}
						value = generateTransformedValue(custom, sourceValue);
						if (value == null) {
							return null;
						}
					} else {
						value = record.get(field.getFieldName());
						
						if (value != null) {
							
							mySQL = mySQL + " and " + TransFieldName(field.getFieldName()) + " = '" +  value.toString() + "'";
							
							log.fatal("关联条件" + count + "中增加关联条件字段:" + field.getFieldName() + "= " + value.toString()); 
		
						}

					}
					
				}
				
				if(SqlFlag==0){
					
					List<String> TempList =  new ArrayList<String>();
				
					List list= blockingDao.EmpiBlockRecords(mySQL);
					
					//List list = queryObject.list();
					
					if(list!=null){

					for (Object object : list) {
						BigDecimal entry = (BigDecimal) object;
						TempList.add(entry.toString());
					}
					
					if (TempList != null) {
						for (String Tlist : TempList) {
							if (Tlist != null && !records.contains(Tlist))
								records.add(Tlist);
						}
					}
					}
				}

				count++;
			}
			

			return records;
		}
	
	private String TransFieldName(String myFieldName){
		
		String ReturnFieldName = null;
		
		if (myFieldName.equalsIgnoreCase("givenName")) ReturnFieldName =  "given_Name";

		if (myFieldName.equalsIgnoreCase("middleName")) ReturnFieldName =  "middle_Name";

		if (myFieldName.equalsIgnoreCase("familyName")) ReturnFieldName =  "family_Name";

		if (myFieldName.equalsIgnoreCase("prefix")) ReturnFieldName =  "prefix";

		if (myFieldName.equalsIgnoreCase("suffix")) ReturnFieldName =  "suffix";

		if (myFieldName.equalsIgnoreCase("name")) ReturnFieldName =  "name";

		if (myFieldName.equalsIgnoreCase("nameCode")) ReturnFieldName =  "name_Code";

		if (myFieldName.equalsIgnoreCase("birthPlace")) ReturnFieldName =  "birth_Place";

		if (myFieldName.equalsIgnoreCase("multipleBirthInd")) ReturnFieldName =  "multiple_Birth_Ind";

		if (myFieldName.equalsIgnoreCase("mothersMaidenName")) ReturnFieldName =  "mothers_Maiden_Name";

		if (myFieldName.equalsIgnoreCase("ssn")) ReturnFieldName =  "ssn";

		if (myFieldName.equalsIgnoreCase("identityNo")) ReturnFieldName =  "identity_No";

		if (myFieldName.equalsIgnoreCase("insuranceNo")) ReturnFieldName =  "insurance_No";

		if (myFieldName.equalsIgnoreCase("insuranceType")) ReturnFieldName =  "insurance_Type";

		if (myFieldName.equalsIgnoreCase("insuranceName")) ReturnFieldName =  "insurance_Name";

		if (myFieldName.equalsIgnoreCase("email")) ReturnFieldName =  "email";

		if (myFieldName.equalsIgnoreCase("address1")) ReturnFieldName =  "address1";

		if (myFieldName.equalsIgnoreCase("postalCode")) ReturnFieldName =  "postal_Code";

		if (myFieldName.equalsIgnoreCase("address2")) ReturnFieldName =  "address2";

		if (myFieldName.equalsIgnoreCase("postalCode1")) ReturnFieldName =  "postal_Code1";

		if (myFieldName.equalsIgnoreCase("city")) ReturnFieldName =  "city";

		if (myFieldName.equalsIgnoreCase("state")) ReturnFieldName =  "state";

		if (myFieldName.equalsIgnoreCase("country")) ReturnFieldName =  "country";

		if (myFieldName.equalsIgnoreCase("countryCode")) ReturnFieldName =  "country_Code";

		if (myFieldName.equalsIgnoreCase("phoneCountryCode")) ReturnFieldName =  "phone_Country_Code";

		if (myFieldName.equalsIgnoreCase("phoneAreaCode")) ReturnFieldName =  "phone_Area_Code";

		if (myFieldName.equalsIgnoreCase("phoneNumber")) ReturnFieldName =  "phone_Number";

		if (myFieldName.equalsIgnoreCase("phoneExt")) ReturnFieldName =  "phone_Ext";

		if (myFieldName.equalsIgnoreCase("phoneCountryCode1")) ReturnFieldName =  "phone_Country_Code1";

		if (myFieldName.equalsIgnoreCase("phoneAreaCode1")) ReturnFieldName =  "phone_Area_Code1";

		if (myFieldName.equalsIgnoreCase("phoneNumber1")) ReturnFieldName =  "phone_Number1";

		if (myFieldName.equalsIgnoreCase("phoneExt1")) ReturnFieldName =  "phone_Ext1";

		if (myFieldName.equalsIgnoreCase("company")) ReturnFieldName =  "company";

		if (myFieldName.equalsIgnoreCase("companycontacts")) ReturnFieldName =  "companycontacts";

		if (myFieldName.equalsIgnoreCase("account")) ReturnFieldName =  "account";

		if (myFieldName.equalsIgnoreCase("deathInd")) ReturnFieldName =  "death_Ind";

		if (myFieldName.equalsIgnoreCase("groupNumber")) ReturnFieldName =  "group_Number";

		if (myFieldName.equalsIgnoreCase("empi")) ReturnFieldName =  "empi";

		if (myFieldName.equalsIgnoreCase("custom1")) ReturnFieldName =  "custom1";

		if (myFieldName.equalsIgnoreCase("custom2")) ReturnFieldName =  "custom2";

		if (myFieldName.equalsIgnoreCase("custom3")) ReturnFieldName =  "custom3";

		if (myFieldName.equalsIgnoreCase("custom4")) ReturnFieldName =  "custom4";

		if (myFieldName.equalsIgnoreCase("custom5")) ReturnFieldName =  "custom5";

		if (myFieldName.equalsIgnoreCase("custom6")) ReturnFieldName =  "custom6";

		if (myFieldName.equalsIgnoreCase("custom7")) ReturnFieldName =  "custom7";

		if (myFieldName.equalsIgnoreCase("custom8")) ReturnFieldName =  "custom8";

		if (myFieldName.equalsIgnoreCase("custom9")) ReturnFieldName =  "custom9";

		if (myFieldName.equalsIgnoreCase("custom10")) ReturnFieldName =  "custom10";

		if (myFieldName.equalsIgnoreCase("custom11")) ReturnFieldName =  "custom11";

		if (myFieldName.equalsIgnoreCase("custom12")) ReturnFieldName =  "custom12";

		if (myFieldName.equalsIgnoreCase("custom13")) ReturnFieldName =  "custom13";

		if (myFieldName.equalsIgnoreCase("custom14")) ReturnFieldName =  "custom14";

		if (myFieldName.equalsIgnoreCase("custom15")) ReturnFieldName =  "custom15";

		if (myFieldName.equalsIgnoreCase("custom16")) ReturnFieldName =  "custom16";

		if (myFieldName.equalsIgnoreCase("custom17")) ReturnFieldName =  "custom17";

		if (myFieldName.equalsIgnoreCase("custom18")) ReturnFieldName =  "custom18";

		if (myFieldName.equalsIgnoreCase("custom19")) ReturnFieldName =  "custom19";

		if (myFieldName.equalsIgnoreCase("custom20")) ReturnFieldName =  "custom20";

		if (myFieldName.equalsIgnoreCase("custom21")) ReturnFieldName =  "custom21";

		if (myFieldName.equalsIgnoreCase("custom22")) ReturnFieldName =  "custom22";

		if (myFieldName.equalsIgnoreCase("custom23")) ReturnFieldName =  "custom23";

		if (myFieldName.equalsIgnoreCase("custom24")) ReturnFieldName =  "custom24";

		if (myFieldName.equalsIgnoreCase("custom25")) ReturnFieldName =  "custom25";

		if (myFieldName.equalsIgnoreCase("custom26")) ReturnFieldName =  "custom26";

		if (myFieldName.equalsIgnoreCase("custom27")) ReturnFieldName =  "custom27";

		if (myFieldName.equalsIgnoreCase("custom28")) ReturnFieldName =  "custom28";

		if (myFieldName.equalsIgnoreCase("custom29")) ReturnFieldName =  "custom29";

		if (myFieldName.equalsIgnoreCase("custom30")) ReturnFieldName =  "custom30";

		if (myFieldName.equalsIgnoreCase("nameSpellCode")) ReturnFieldName =  "name_Spell_Code";

		if (myFieldName.equalsIgnoreCase("nameWbCode")) ReturnFieldName =  "name_Wb_Code";

		if (myFieldName.equalsIgnoreCase("birthProvince")) ReturnFieldName =  "birth_Province";

		if (myFieldName.equalsIgnoreCase("birthCity")) ReturnFieldName =  "birth_City";

		if (myFieldName.equalsIgnoreCase("birthCounty")) ReturnFieldName =  "birth_County";

		if (myFieldName.equalsIgnoreCase("birthZip")) ReturnFieldName =  "birth_Zip";

		if (myFieldName.equalsIgnoreCase("citizenCard")) ReturnFieldName =  "citizen_Card";

		if (myFieldName.equalsIgnoreCase("medicalCertificate")) ReturnFieldName =  "medical_Certificate";

		if (myFieldName.equalsIgnoreCase("meicarePerson")) ReturnFieldName =  "meicare_Person";

		if (myFieldName.equalsIgnoreCase("elderCertificate")) ReturnFieldName =  "elder_Certificate";

		if (myFieldName.equalsIgnoreCase("opcaseno")) ReturnFieldName =  "opcaseno";

		if (myFieldName.equalsIgnoreCase("genderName")) ReturnFieldName =  "gender_Name";

		if (myFieldName.equalsIgnoreCase("genderDomain")) ReturnFieldName =  "gender_Domain";

		if (myFieldName.equalsIgnoreCase("ethnicName")) ReturnFieldName =  "ethnic_Name";

		if (myFieldName.equalsIgnoreCase("ethincDomain")) ReturnFieldName =  "ethinc_Domain";

		if (myFieldName.equalsIgnoreCase("raceName")) ReturnFieldName =  "race_Name";

		if (myFieldName.equalsIgnoreCase("raceDomain")) ReturnFieldName =  "race_Domain";

		if (myFieldName.equalsIgnoreCase("nationalityName")) ReturnFieldName =  "nationality_Name";

		if (myFieldName.equalsIgnoreCase("nationalityDomain")) ReturnFieldName =  "nationality_Domain";

		if (myFieldName.equalsIgnoreCase("maritalStatusName")) ReturnFieldName =  "marital_Status_Name";

		if (myFieldName.equalsIgnoreCase("maritalDomain")) ReturnFieldName =  "marital_Domain";

		if (myFieldName.equalsIgnoreCase("degreeName")) ReturnFieldName =  "degree_Name";

		if (myFieldName.equalsIgnoreCase("degreeDomain")) ReturnFieldName =  "degree_Domain";

		if (myFieldName.equalsIgnoreCase("homeProvince")) ReturnFieldName =  "home_Province";

		if (myFieldName.equalsIgnoreCase("homeCity")) ReturnFieldName =  "home_City";

		if (myFieldName.equalsIgnoreCase("homeCounty")) ReturnFieldName =  "home_County";

		if (myFieldName.equalsIgnoreCase("homeZip")) ReturnFieldName =  "home_Zip";

		if (myFieldName.equalsIgnoreCase("homeAddress")) ReturnFieldName =  "home_Address";

		if (myFieldName.equalsIgnoreCase("registeredProvince")) ReturnFieldName =  "registered_Province";

		if (myFieldName.equalsIgnoreCase("registeredCity")) ReturnFieldName =  "registered_City";

		if (myFieldName.equalsIgnoreCase("registeredCounty")) ReturnFieldName =  "registered_County";

		if (myFieldName.equalsIgnoreCase("registeredZip")) ReturnFieldName =  "registered_Zip";

		if (myFieldName.equalsIgnoreCase("registeredAddress")) ReturnFieldName =  "registered_Address";

		if (myFieldName.equalsIgnoreCase("nativeProvince")) ReturnFieldName =  "native_Province";

		if (myFieldName.equalsIgnoreCase("nativeCity")) ReturnFieldName =  "native_City";

		if (myFieldName.equalsIgnoreCase("professionName")) ReturnFieldName =  "profession_Name";

		if (myFieldName.equalsIgnoreCase("professionDomain")) ReturnFieldName =  "profession_Domain";

		if (myFieldName.equalsIgnoreCase("workZip")) ReturnFieldName =  "work_Zip";

		if (myFieldName.equalsIgnoreCase("workAddress")) ReturnFieldName =  "work_Address";

		if (myFieldName.equalsIgnoreCase("privateNumber")) ReturnFieldName =  "private_Number";

		if (myFieldName.equalsIgnoreCase("homeNumber")) ReturnFieldName =  "home_Number";

		if (myFieldName.equalsIgnoreCase("workNumber")) ReturnFieldName =  "work_Number";

		if (myFieldName.equalsIgnoreCase("guardianPerson")) ReturnFieldName =  "guardian_Person";

		if (myFieldName.equalsIgnoreCase("vip")) ReturnFieldName =  "vip";

		if (myFieldName.equalsIgnoreCase("healthCard")) ReturnFieldName =  "health_Card";

		if (myFieldName.equalsIgnoreCase("accountLocked")) ReturnFieldName =  "account_Locked";

		if (myFieldName.equalsIgnoreCase("cardType")) ReturnFieldName =  "cardT_ype";
		
		return ReturnFieldName;

	}
	/**
	 * Iterates over the list of blocking rounds that have been
	 * defined and accumulates patients that match the search criteria
	 * configured for the specific values present in the record
	 * provided.
	 * 
	 */
	public List<RecordPair> findCandidates(Record record) {
		
		//panm-从mpi-config.xml获取blockrounds字段值，既关联匹配字段
		blockingRounds = getBlockingRounds();
		
		//System.out.println("关联字段：" + blockingRounds);
		//log.fatal("关联字段:" + blockingRounds.get(index));
		
		//panm-第一轮匹配查找，根据设置字段进行sql匹配查询
		int count = 0;
		
		List<Record> records = new java.util.ArrayList<Record>();
		
		for (BlockingRound round : blockingRounds) 
		{
			List<BaseField> fields = round.getFields();
			
			Criteria criteria = new Criteria();
			
			int NullCounts = 0;
			
			for (BaseField field : fields) 
			{
				Criterion criterion = buildCriterion(field, record);
				
				if(record.getAsString(field.getFieldName())==null){NullCounts++;}
				
				if (criterion != null) 
				{
					log.fatal("In round " + count + " added criterion: " + criterion);
					
					//System.out.println("In round " + count + " added criterion: " + criterion);
					
					criteria.addCriterion(criterion);
				}
			}
			
			if(NullCounts>0)
			{
				continue;
			}
			
			records.addAll(blockingDao.blockRecords(criteria));
			
			count++;
		}
		
		//添加纪录至RECORDPAIR，左为当前记录，entry为查询记录
		List<RecordPair> pairs = new java.util.ArrayList<RecordPair>(records.size());
		
		for (Record entry : records) 
		{
			pairs.add(new RecordPair(record, entry));
		}
		
		return pairs;
	}

	private Criterion buildCriterion(BaseField field, Record record) {
		Criterion criterion = new Criterion();
		criterion.setName(field.getFieldName());
		criterion.setOperation(Operation.EQ);
		if (field instanceof CustomField) {
			CustomField custom = (CustomField) field;
			String sourceValue = record.getAsString(custom.getSourceFieldName());
			if (sourceValue == null) {
				return null;
			}
			Object value = generateTransformedValue(custom, sourceValue);
			if (value == null) {
				return null;
			}
			criterion.setValue(value);
		} else {
			Object value = record.get(field.getFieldName());
			if (value == null) {
				return null;
			}
			criterion.setValue(value);
		}
		return criterion;
	}

	private Object generateTransformedValue(CustomField custom, String sourceValue) {
		TransformationService transformationService = Context.getTransformationService();
		try {
			return transformationService.transform(custom.getTransformationFunctionName(), sourceValue);
		} catch (Exception e) {
			log.error("Failed while trying to create a transform for generation of a blocking field: " + e, e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private List<BlockingRound> getBlockingRounds() {
		if (blockingRounds == null) {
			blockingRounds = (List<BlockingRound>)
				Context.getConfiguration().lookupConfigurationEntry(BasicBlockingConstants.BLOCKING_ROUNDS_REGISTRY_KEY);
		}
		return blockingRounds;
	}

	public BlockingDao getBlockingDao() {
		return blockingDao;
	}

	public void setBlockingDao(BlockingDao blockingDao) {
		this.blockingDao = blockingDao;
	}
}
