package org.fao.fenix.web.modules.aquastat.server.utils;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Date> {

	@Override
	public int compare(Date one, Date two) {
		return one.compareTo(two);
	}
	
}
