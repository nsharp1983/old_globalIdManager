package com.ats.aempi.transformation.function;

import org.apache.commons.codec.language.Soundex;

public class SoundexFunction extends AbstractTransformationFunction
{
	private Soundex soundex;
	
	public SoundexFunction() {
		super();
		soundex = new Soundex();
	}
	
	public Object transform(Object field) {
		log.debug("Applying the soundex transform to field with value: " + field);
		if (field == null) {
			return null;
		}
		String encodedValue = soundex.encode(field.toString());
		log.debug("The soundex value for field: '" + field + "' is '" + encodedValue + "'");
		return encodedValue;
	}
}
