package com.ats.aempi.matching.fellegisunter;

import java.util.Comparator;

import com.ats.aempi.model.RecordPair;

public class RecordPairComparator implements Comparator<RecordPair>
{
	public int compare(RecordPair pair1, RecordPair pair2) {
		return pair1.getWeight().compareTo(pair2.getWeight());
	}

}
