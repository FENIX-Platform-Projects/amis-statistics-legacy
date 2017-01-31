package org.fao.fenix.web.modules.olap.client.control;

import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;

public class OlapDateFormatter {

	public static String format(UniqueValueVO vo) {
		String lbl = vo.getLabel();
		if ((vo.getPeriodType() != null) || !vo.getPeriodType().equals("")) {
			if (vo.getPeriodType().equalsIgnoreCase("MONTHLY")) {
				lbl = vo.getCode().substring(5, 7) + "-" + vo.getCode().substring(0, 4);
			} else if (vo.getPeriodType().equalsIgnoreCase("ANNUAL") || vo.getPeriodType().equalsIgnoreCase("YEARLY")) {
				lbl = vo.getCode().substring(0, 4);
			}
		}
		return lbl;
	}
	
}
