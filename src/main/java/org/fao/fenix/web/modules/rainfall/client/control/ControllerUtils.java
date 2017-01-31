/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.rainfall.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerUtils {

	public static List<Date> findAllRangeDecades(String d1, String d2, String y1, String y2) {
		List<Date> dates = new ArrayList<Date>();
		List<Date> tmp = findAllRangeDecades(d1, "123", y1);
		for (Date date : tmp)
			dates.add(date);
		int year1 = Integer.valueOf(y1);
		int year2 = Integer.valueOf(y2);
		for (int i = 1 + year1 ; i < year2 ; i++) {
			tmp = findAllRangeDecades("011", "123", String.valueOf(i));
			for (Date date : tmp)
				dates.add(date);
		}
		tmp = findAllRangeDecades("011", d2, y2);
		for (Date date : tmp)
			dates.add(date);
		
//		System.out.println("CONTROLLER UTILS: d1: " + d1 + ", d2: " + d2 + ", y1: " + y1 + ", y2: " + y2);
//		for (Date d : dates)
//			System.out.println(d);
		
		return dates;
	}
	
	public static List<Date> findAllRangeDecades(String d1, String d2, String year) {
		List<Date> dates = new ArrayList<Date>();
		dates.add(createDateFromDecade(d1, year));
		if (d1.equals(d2))
			return dates;
		String d3 = calculateNextDecade(d1);
		while (!d3.equals(d2)) {
			dates.add(createDateFromDecade(d3, year));
			d3 = calculateNextDecade(d3);
		}
		dates.add(createDateFromDecade(d2, year));
		dates = fixDates(dates);
		
//		System.out.println("CONTROLLER UTILS: d1: " + d1 + ", d2: " + d2);
//		for (Date d : dates)
//			System.out.println(d);
		
		return dates;
	}
	
	@SuppressWarnings("deprecation")
	public static List<Date> fixDates(List<Date> dates) {
		int index = 0;
		boolean fixme = false;
		for (int i = dates.size() - 1 ; i > 0 ; i--) {
			if (dates.get(i).getMonth() < dates.get(i - 1).getMonth()) {
				index = i;
				fixme = true;
				break;
			}	
		}
		if (fixme == true) {
			for (int i = index ; i < dates.size() ; i++)
				dates.get(i).setYear(1 + dates.get(i).getYear());
		}
		return dates;
	}
	
	@SuppressWarnings("deprecation")
	public static Date createDateFromDecade(String decade, String year) {
		int month = Integer.valueOf(decade.substring(0, 2));
		int dec = Integer.valueOf(decade.substring(2));
		int day = 1;
		if (dec == 2)
			day = 11;
		else if (dec == 3)
			day = 21;
		Date date = new Date((Integer.valueOf(year) - 1900), (month - 1), day);
		return date;
	}
	
	public static String calculateNextDecade(String decade) {
		int month = Integer.valueOf(decade.substring(0, 2));
		int dec = Integer.valueOf(decade.substring(2));
		if (dec == 3) {
			month++;
			dec = 1;
		} else 
			dec++;
		if (month == 13)
			month = 1;
		String nextDecade = String.valueOf(month) + String.valueOf(dec);
		if (month < 10)
			nextDecade = "0" + nextDecade;
		return nextDecade;
	}
	
}
