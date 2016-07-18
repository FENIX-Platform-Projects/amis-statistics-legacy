package org.fao.fenix.web.modules.olap.client.control;

import java.util.Comparator;
import java.util.Date;

import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

public class FieldParserComparator implements Comparator<String> {

	public int compare(String a, String b) {
		Date da = FieldParser.parseDate(a);
		Date db = FieldParser.parseDate(b);
		return -da.compareTo(db);
	}
	
}