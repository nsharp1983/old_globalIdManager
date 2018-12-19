package com.ats.aempi.transformation.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ats.aempi.ValidationException;
import com.ats.aempi.service.impl.BaseServiceImpl;
import com.ats.aempi.transformation.function.TransformationFunction;
import com.ats.aempi.transformation.TransformationFunctionType;
import com.ats.aempi.transformation.TransformationService;

public class TransformationServiceImpl extends BaseServiceImpl implements TransformationService
{
	private HashMap<String,TransformationFunction> transformationFunctionTypeMap;

	public TransformationServiceImpl() {
		super();
	}

	public void init(String transformationFunctionType, Map<String,String> configParameters) {
		TransformationFunction transformationFunction = getTransformationFunction(transformationFunctionType);
		transformationFunction.init(configParameters);
	}

	public Object transform(String transformationFunctionType, Object field) {
		TransformationFunction transformationFunction = getTransformationFunction(transformationFunctionType);
		return transformationFunction.transform(field);
	}

	// Factory method pattern
	private TransformationFunction getTransformationFunction(String transformationFunctionType) {
		TransformationFunction transformationFunction = transformationFunctionTypeMap.get(transformationFunctionType);
		if (transformationFunction == null) {
			log.error("Unknown transformation function requested: " + transformationFunctionType);
			throw new ValidationException("Unknown transformation function requested for field transformation: " + transformationFunction);
		}
		return transformationFunction;
	}
	
	public TransformationFunctionType getTransformationFunctionType(String name) {
		TransformationFunction function = transformationFunctionTypeMap.get(name);
		if (function == null) {
			return null;
		}
		return new TransformationFunctionType(name, function);
	}

	public TransformationFunctionType[] getTransformationFunctionTypes() {
		TransformationFunctionType[] list = new TransformationFunctionType[transformationFunctionTypeMap.keySet().size()];
		int index = 0;
		for (String key : transformationFunctionTypeMap.keySet()) {
			list[index++] = new TransformationFunctionType(key, transformationFunctionTypeMap.get(key));
		}
		return list;
	}
	
	public HashMap<String, TransformationFunction> getTransformationFunctionTypeMap() {
		return transformationFunctionTypeMap;
	}

	public List<String> getTransformationFunctionNames() {
		List<String> transformationFunctionNames = new ArrayList<String>();
		for (String key : transformationFunctionTypeMap.keySet()) {
			transformationFunctionNames.add(key);
		}
		return transformationFunctionNames;
	}
	
	public void setTransformationFunctionTypeMap(HashMap<String, TransformationFunction> transformationFunctionTypeMap) {
		this.transformationFunctionTypeMap = transformationFunctionTypeMap;
	}
}
