package com.ats.aempi.transformation.function;

import org.apache.commons.codec.language.DoubleMetaphone;

public class DoubleMetaphoneFunction extends AbstractTransformationFunction
{
	private DoubleMetaphone metaphone;
	
	public DoubleMetaphoneFunction() {
		super();
		metaphone = new DoubleMetaphone();
	}
	
	public Object transform(Object field) {
		log.debug("Applying the double metaphone transform to field with value: " + field);
		if (field == null) {
			return null;
		}
		String encodedValue = metaphone.encode(field.toString());
		log.debug("The double metaphone value for field: '" + field + "' is '" + encodedValue + "'");
		return encodedValue;
	}
}
