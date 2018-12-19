package com.ats.aempi.loader.configuration;

public class LoaderFieldComposition
{
	private int index;
	private String separator;
	private String value;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		if (separator == null)
			return;
		
		if (separator.equals("SPACE"))
			this.separator = " ";
		else if (separator.equals("DASH"))
			this.separator = "-";
		else if (separator.equals("PER"))
			this.separator = "/";
		else if (separator.equals("BACKSLASH"))
			this.separator = "\\";
		else if (separator.equals("COMMA"))
			this.separator = ",";
		else if (separator.equals("PERCENT"))
			this.separator = "%";
		else if (separator.equals("DOLLAR"))
			this.separator = "$";
		else if (separator.equals("HASHMARK"))
			this.separator = "#";
		else if (separator.equals("AT"))
			this.separator = "@";
		else if (separator.equals("CAP"))
			this.separator = "^";
		else if (separator.equals("STAR"))
			this.separator = "*";
		else if (separator.equals("EXCLAMATION"))
			this.separator = "!";
		else if (separator.equals("QUESTION"))
			this.separator = "?";
		else
			this.separator = separator;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
