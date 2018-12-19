package com.ats.aempi.transformation.function;

import org.apache.commons.codec.language.RefinedSoundex;;

public class RefinedSoundexFunction extends AbstractTransformationFunction
{
	private RefinedSoundex refinedSoundex;
	
	public RefinedSoundexFunction() {
		super();
		refinedSoundex = new RefinedSoundex();
	}
	
	public Object transform(Object field) {
		log.debug("Applying the refined soundex transform to field with value: " + field);
		if (field == null) {
			return null;
		}
		String encodedValue = refinedSoundex.encode(field.toString());
		log.debug("The refined soundex value for field: '" + field + "' is '" + encodedValue + "'");
		return encodedValue;
	}
}
