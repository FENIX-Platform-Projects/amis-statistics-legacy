package org.fao.fenix.web.modules.olap.client.control;

import java.util.Comparator;

import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;

public class UniqueValueVOLabelComparator implements Comparator<UniqueValueVO> {

	public int compare(UniqueValueVO a, UniqueValueVO b) {
		return a.getLabel().compareTo(b.getLabel());
	}
	
}