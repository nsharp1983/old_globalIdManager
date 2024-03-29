package com.ats.aempi.util;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import com.ats.aempi.model.NationalityDict;
import com.ats.aempi.model.Person;
import com.ats.aempi.model.Record;

public class TestConversions
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person person = new Person();
		person.setAddress1("2930 Oak Shadow Drive");
		person.setCity("Oak Hill");
		person.setFamilyName("Pentakalos");
		person.setGivenName("Odysseas");
		NationalityDict nationality = new NationalityDict();
		nationality.setNationalityCd(100);
		person.setNationalityDict(nationality);
		person.setDateOfBirth(new java.util.Date());
		
		Record record = new Record(person);
		String[] matchingAttributes = {"givenName", "familyName"};
		for (String attribute : matchingAttributes) {
			System.out.println("The record has a value of " + record.getAsString(attribute) + " for matching field " + attribute);
		}
		
		java.util.Set<String> propertySet = record.getPropertyNames();
		for (String property : propertySet) {
			System.out.println("Object has the following property: " + property);
			Object value = record.get(property);
			if (value != null) {
				System.out.println("The record has a value of " + value + " of type " + value.getClass() + " for field " + property);
			}
		}
	}

	public static void exploringBeanUtils() {
		Person person = new Person();
		person.setAddress1("2930 Oak Shadow Drive");
		person.setCity("Oak Hill");
		NationalityDict nationality = new NationalityDict();
		nationality.setNationalityCd(100);
		person.setNationalityDict(nationality);
		person.setDateOfBirth(new java.util.Date());
		
		ConvertingWrapDynaBean bean = new ConvertingWrapDynaBean(person);
		System.out.println("Build a dyna bean using my person:");
		System.out.println(bean.get("address1"));
		System.out.println(bean.get("dateOfBirth"));
		
		System.out.println("Changing some of the values.");
		bean.set("givenName", "Odysseas");
		bean.set("familyName", "Pentakalos");
		System.out.println(bean.get("nationality.nationalityCd"));
		bean.set("nationality.nationalityCd", "150");
		System.out.println("Value " + bean.get("nationality.nationalityCd") + " is of type " + bean.get("nationality.nationalityCd").getClass());
		person = (Person) bean.getInstance();
		System.out.println(person);
		
		List<String> properties = ConvertUtil.extractProperties(person);
		for (String property : properties) {
			System.out.println("Property name is: " + property);
		}

//		DynaProperty[] properties = bean.getDynaClass().getDynaProperties();
//		for (DynaProperty property : properties) {
//			System.out.println("The map has the property: " + property.getName() + " which is mapped " + property.getType());
//			if (property.getType().getName().startsWith("org.openhie")) {
//				WrapDynaClass dynaClass = WrapDynaClass.createDynaClass(property.getType());
//				DynaProperty[] internalProperties = dynaClass.getDynaProperties();
//				for (DynaProperty internalProperty : internalProperties) {
//					System.out.println("The map has the property: " + property.getName() + "." + internalProperty.getName());
//				}
//			}
//		}
		
//		BeanMap beanMap = new BeanMap(person);
//		Set<String> properties = beanMap.keySet();
//		for (String key : properties) {
//			System.out.println("The map has the property: " + key);
//		}
		
		org.apache.commons.beanutils.converters.DateConverter converter = new org.apache.commons.beanutils.converters.DateConverter();
		converter.setPattern("yyyy.MM.dd HH:mm:ss z");
		String[] patterns = converter.getPatterns();
		for (String pattern : patterns) {
			System.out.println("Pattern is " + pattern);
		}
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
		System.out.println(sdf.format(now));
		
		ConvertUtils.register(converter, java.util.Date.class);
		ConvertUtils convertUtils = new ConvertUtils();
		System.out.println(convertUtils.convert("2009.03.06 15:13:29 EST", java.util.Date.class));
		
		try {
			BeanUtils.setProperty(person, "dateOfBirth", "2009.03.06 15:13:29 EST");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bean.get("dateOfBirth"));
		
		System.out.println(bean.getDynaClass().getDynaProperty("dateOfBirth"));
		bean.set("dateOfBirth", "2009.03.06 15:13:29 EST");
		System.out.println(bean.get("dateOfBirth"));	
	}
}
