package com.ats.aempi.transformation.function;

import java.util.Map;

public interface TransformationFunction
{
	public void init(Map<String,String> configParameters);

	public Object transform(Object field);

	public String getName();

	public void setName(String name);
}
