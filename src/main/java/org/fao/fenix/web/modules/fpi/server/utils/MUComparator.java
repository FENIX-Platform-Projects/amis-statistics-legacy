package org.fao.fenix.web.modules.fpi.server.utils;

import java.text.CollationKey;
import java.util.Comparator;

import org.fao.fenix.web.modules.fpi.common.vo.MeasurementUnitVo;

public class MUComparator implements Comparator<MeasurementUnitVo> {

	public int compare(MeasurementUnitVo sourceVo, MeasurementUnitVo targetVo) {
		String source = sourceVo.getLabel();
		String target = targetVo.getLabel();
		int max = source.length();
		if (target.length() < max)
			max = target.length();
		for (int i = 0 ; i < max ; i++) 
			if (source.charAt(i) > target.charAt(i))
				return 1;
		return -1;
	}

	public CollationKey getCollationKey(String source) {
		// TODO Auto-generated method stub
		return null;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}