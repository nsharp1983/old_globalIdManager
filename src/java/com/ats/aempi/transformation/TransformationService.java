package com.ats.aempi.transformation;

import java.util.List;
import java.util.Map;

public interface TransformationService
{
	public TransformationFunctionType[] getTransformationFunctionTypes();
	
	public List<String> getTransformationFunctionNames();
	
	public TransformationFunctionType getTransformationFunctionType(String name);
	
	public void init(String transformationFunctionType, Map<String,String> configParameters);
	
	public Object transform(String transformationFunctionType, Object field);
	
}
