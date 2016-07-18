package org.fao.fenix.web.modules.olap.client.control;

import java.util.Comparator;
import java.util.Date;

import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

public class UniqueValueVODateComparator implements Comparator<UniqueValueVO> {

	@Override
	public int compare(UniqueValueVO a, UniqueValueVO b) {
		Date da = FieldParser.parseDate(a.getLabel());
		Date db = FieldParser.parseDate(b.getLabel());
		return -da.compareTo(db);
	}

}
