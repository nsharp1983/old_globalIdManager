package com.ats.aempi.loader;

import org.springframework.context.ApplicationContext;

public class FileLoaderFactory
{
	public static FileLoader getFileLoader(ApplicationContext applicationContext, String loaderAlias) {
		return (FileLoader) applicationContext.getBean(loaderAlias);
	}
}
