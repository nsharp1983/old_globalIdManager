package com.ats.aempi.loader.configuration;

import java.util.Comparator;


public class FieldCompositionComparator implements Comparator<LoaderFieldComposition>
{
	public int compare(LoaderFieldComposition pair1, LoaderFieldComposition pair2) {
		return ((Integer)pair1.getIndex()).compareTo(pair2.getIndex());
	}
}
