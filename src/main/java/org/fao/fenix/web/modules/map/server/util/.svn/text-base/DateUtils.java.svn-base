/*
 */

package org.fao.fenix.web.modules.map.server.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author etj
 */
public class DateUtils {
	static public String getNow() {
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		return now.get(Calendar.YEAR) + "-"
				+ now.get(Calendar.MONTH) + "-"
				+ now.get(Calendar.DAY_OF_MONTH) + "|"
				+ now.get(Calendar.HOUR_OF_DAY) + ":"
				+ now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND)
				+ "." + now.get(Calendar.MILLISECOND);
	}
}
