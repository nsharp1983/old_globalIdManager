package com.ats.aempi.transformation.function;

import org.apache.commons.codec.language.Metaphone;

public class MetaphoneFunction extends AbstractTransformationFunction
{
	private Metaphone metaphone;
	
	public MetaphoneFunction() {
		super();
		metaphone = new Metaphone();
	}
	
	public Object transform(Object field) {
		log.debug("Applying the metaphone transform to field with value: " + field);
		if (field == null) {
			return null;
		}
		String encodedValue = metaphone.encode(field.toString());
		log.debug("The metaphone value for field: '" + field + "' is '" + encodedValue + "'");
		return encodedValue;
	}
}
