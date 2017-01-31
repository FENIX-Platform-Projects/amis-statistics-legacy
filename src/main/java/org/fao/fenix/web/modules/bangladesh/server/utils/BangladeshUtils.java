package org.fao.fenix.web.modules.bangladesh.server.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.fao.fenix.core.persistence.utils.DateComparator;

public class BangladeshUtils {

	@SuppressWarnings("deprecation")
	public static Date getPreviousFortnight(Date referenceDate) {
		return new Date(referenceDate.getYear(), referenceDate.getMonth(), referenceDate.getDate() - 14);
	}
	
	@SuppressWarnings("deprecation")
	public static Date getNextFortnight(Date referenceDate) {
		return new Date(referenceDate.getYear(), referenceDate.getMonth(), referenceDate.getDate() + 14);
	}
	
	public static Double getChangePercentage(Double old, Double nu) {
		if ((old != null) && (nu != null) && (nu != 0))
			return (100 * old / nu) - 100;
		return null;
	}
	
	public static Date findReportDate(Date referenceDate, Date reportDate) {
		List<Date> allReportDates = findAllReportDates(referenceDate);
		Date date = null;
		for (Date d : allReportDates) {
			if (d.compareTo(reportDate) <= 0) 
				date = d;
			else 
				break;
		}
		return date;
	}
	
	public static List<Date> findAllReportDates(Date referenceDate) {
		List<Date> previous = findAllPreviousReportDates(referenceDate);
		List<Date> next = findAllNextReportDates(referenceDate);
		List<Date> dates = merge(referenceDate, previous, next);
		Collections.sort(dates, new DateComparator());
		return dates;
	}
	
	public static List<Date> merge(Date referenceDate, List<Date> a, List<Date> b) {
		List<Date> dates = new ArrayList<Date>();
		dates.add(referenceDate);
		for (Date d : a)
			if (!dates.contains(d))
				dates.add(d);
		for (Date d : b)
			if (!dates.contains(d))
				dates.add(d);
		return dates;
	}
	
	@SuppressWarnings("deprecation")
	public static List<Date> findAllPreviousReportDates(Date referenceDate) {
		Integer year = referenceDate.getYear();
		List<Date> dates = new ArrayList<Date>();
		Integer flag = year;
		Boolean addIt = false;
		while (flag >= year) {
			referenceDate = new Date(referenceDate.getYear(), referenceDate.getMonth(), referenceDate.getDate() - 1);
			flag = referenceDate.getYear();
			if ((flag >= year) && (referenceDate.getDay() == 4)) {
				if (addIt) {
					dates.add(referenceDate);
					addIt = false;
				} else
					addIt = true;
			}
		}
		return dates;
	}
	
	@SuppressWarnings("deprecation")
	public static List<Date> findAllNextReportDates(Date referenceDate) {
		Integer year = referenceDate.getYear();
		List<Date> dates = new ArrayList<Date>();
		Integer flag = year;
		Boolean addIt = false;
		while (flag <= year) {
			referenceDate = new Date(referenceDate.getYear(), referenceDate.getMonth(), referenceDate.getDate() + 1);
			flag = referenceDate.getYear();
			if ((flag <= year) && (referenceDate.getDay() == 4)) {
				if (addIt) {
					dates.add(referenceDate);
					addIt = false;
				} else
					addIt = true;
			}
		}
		return dates;
	}
	
}
