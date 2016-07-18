package org.fao.fenix.web.modules.udtable.client.view.utils;


import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;


public class FieldParser {

    private static final DateTimeFormat formatterMinus = DateTimeFormat.getFormat("yyyy-MM-dd");
	private static final DateTimeFormat formatterSlash = DateTimeFormat.getFormat("MM/dd/yyyy");
	private static final DateTimeFormat formatter = DateTimeFormat.getFormat("EEE MMM dd hh:mm:ss zzz yyyy");
	private static final DateTimeFormat formatterYear = DateTimeFormat.getFormat("yyyy");
	private static final DateTimeFormat formatterYearMonth = DateTimeFormat.getFormat("yyyy-MM");
	private static final DateTimeFormat longDateFormatter = DateTimeFormat.getFormat("E, dd MMM yyyy HH:mm:ss");


	public static Date parseDate(String dateString, String periodType) {
		
		if (dateString != null && !dateString.isEmpty()) {
			Date date = null;

			if (periodType.equals("yearly")) {
				date = formatterYear.parse(dateString);
			} else if (periodType.equals("monthly")) {
				date = formatterYearMonth.parse(dateString);
			} else {
				if (dateString.indexOf("-") == 4) {
					date = formatterMinus.parse(dateString);
				} else if (dateString.indexOf("/") > 0) {
					date = formatterSlash.parse(dateString);
				} else if (dateString.indexOf(",") > 0){
					date = longDateFormatter.parse(dateString);
				}	else{
					date = formatter.parse(dateString);
				}

			}

			return date;
		} else {
			return null;
		}
	}

	public static String formatDate(Date dateString, String periodType) {
		String date = null;

		if (periodType.equals("yearly")) {
			date = formatterYear.format(dateString);
		} else if (periodType.equals("monthly")) {
			date = formatterYearMonth.format(dateString);
		} else {
			date = formatterMinus.format(dateString);
		}

		return date;
	}


}