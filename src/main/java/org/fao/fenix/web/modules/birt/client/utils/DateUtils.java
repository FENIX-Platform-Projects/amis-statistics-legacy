package org.fao.fenix.web.modules.birt.client.utils;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateFromToPanel;
import org.fao.fenix.web.modules.birt.common.vo.DateVo;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

import com.google.gwt.user.client.ui.ListBox;


public class DateUtils {
	
	
	/** TODO: it can be optimized making some break 
	 * 		  and test with the monthly and yearly**/
	public static List<List<String>> getDates(Date fromDate, Date toDate, ListBox dimensionx){
		List<List<String>> result = new ArrayList<List<String>>();
//		System.out.println("DATES: " + fromDate + " | " + toDate);
		/** TODO: that should be used in asceding order **/
		for(int i=0; i < dimensionx.getItemCount(); i++){
//			System.out.println("PARSING: " + dimensionx.getValue(i));
			Date d = FieldParser.parseDate(dimensionx.getValue(i));
			if ( (d.before(toDate) && d.after(fromDate)) || (d.compareTo(toDate) == 0) || (d.compareTo(fromDate) == 0)) {
				List<String> element = new ArrayList<String>();
				element.add(dimensionx.getValue(i));
				element.add(dimensionx.getItemText(i));
				result.add(0, element);
//				System.out.println("   -> getting: " + d.toString());
			}		
		}
		
//		/** TODO: that should be used in descending order **/
//		for(int i=dimensionx.getItemCount()-1; i >=0  ; i--){
////			System.out.println("PARSING: " + dimensionx.getValue(i));
//			Date d = FieldParser.parseDate(dimensionx.getValue(i));
//			if ( (d.before(toDate) && d.after(fromDate)) || (d.compareTo(toDate) == 0) || (d.compareTo(fromDate) == 0)) {
//				List<String> element = new ArrayList<String>();
//				element.add(dimensionx.getValue(i));
//				element.add(dimensionx.getItemText(i));
//				result.add(element);
////				System.out.println("   -> getting: " + d.toString());
//			}		
//		}
			
		return result;
	}
	
	public static List<List<String>> getDates(Date fromDate, Date toDate, Map<String, String> dimensionx){
		List<List<String>> result = new ArrayList<List<String>>();
		Iterator<Map.Entry<String, String>> iterator = dimensionx.entrySet().iterator();
		
		/** TODO: that should be used in asceding order 
		for (int i = 0; i < dimensionx.size(); i++) {
			Map.Entry<String, String> entry = iterator.next();
//			dimensionXValues.addItem(entry.getValue(),entry.getKey());
//			System.out.println("PARSING entry.getKey(): " + entry.getKey());
//			System.out.println("PARSING entry.getValue(): " + entry.getValue());
			Date d = FieldParser.parseDate(entry.getKey());
			if ( (d.before(toDate) && d.after(fromDate)) || (d.compareTo(toDate) == 0) || (d.compareTo(fromDate) == 0)) {
				List<String> element = new ArrayList<String>();
				element.add(entry.getKey());
				element.add(entry.getValue());
				result.add(element);
//				System.out.println("   -> getting: " + d.toString());
			}		
		}**/
		/** TODO: that should be used in descending order **/
		for(int i=dimensionx.size()-1; i >=0  ; i--){
			Map.Entry<String, String> entry = iterator.next();
//			dimensionXValues.addItem(entry.getValue(),entry.getKey());
//			System.out.println("PARSING entry.getKey(): " + entry.getKey());
//			System.out.println("PARSING entry.getValue(): " + entry.getValue());
			Date d = FieldParser.parseDate(entry.getKey());
			if ( (d.before(toDate) && d.after(fromDate)) || (d.compareTo(toDate) == 0) || (d.compareTo(fromDate) == 0)) {
				List<String> element = new ArrayList<String>();
				element.add(entry.getKey());
				element.add(entry.getValue());
				result.add(element);
//				System.out.println("   -> getting: " + d.toString());
			}		
		}
		
		return result;
	}
	
	
	public static void setDateFromToDefault(String startingDate, String endDate, String periodtype, ListBox startyear, ListBox startmonth, ListBox startday, ListBox endyear, ListBox endmonth, ListBox endday) {
		Date start = FieldParser.parseDate(startingDate);
		Date end = FieldParser.parseDate(endDate);
		cleanListBox(periodtype, startyear, startmonth, startday, endyear, endmonth, endday);
		System.out.println("startingDate:" + startingDate);
		System.out.println("start:" + start);
		System.out.println("endDate:" + endDate);
		System.out.println("end:" + end);
		System.out.println("periodtype:" + periodtype);
		
		/** filling years **/
		for(int i= (1900 + start.getYear()); i <= (1900 + end.getYear()); i++){
			System.out.println(".");
			startyear.addItem(Integer.toString(i));
			endyear.addItem(Integer.toString(i));
		}
		
		/** filling months **/
		if ( !periodtype.equals("yearly"))
			for(int i= 1; i <= 12; i++){
				startmonth.addItem(Integer.toString(i));
				endmonth.addItem(Integer.toString(i));
			}
		
		/** filling days **/
		if ( !periodtype.equals("yearly") && !periodtype.equals("monthly"))
			for(int i= 1; i <= 31; i++){
				startday.addItem(Integer.toString(i));
				endday.addItem(Integer.toString(i));
			}
		
		/** setting values start **/
		startyear.setSelectedIndex(0);
		startmonth.setSelectedIndex(start.getMonth());
		startday.setSelectedIndex(start.getDate() -1);
		
		endyear.setSelectedIndex(endyear.getItemCount() -1);
		endmonth.setSelectedIndex(end.getMonth());
		endday.setSelectedIndex(end.getDate() -1);
		disableDateFromToListBox(periodtype, startyear, startmonth, startday, endyear, endmonth, endday);
	}
	
	
	public static void setDateLastUpdateDefault(ListBox years, ListBox months, ListBox days, String periodtype, String startYear, String endYear) {
		Integer yearsPhase = Integer.parseInt(endYear) - Integer.parseInt(startYear);
//		System.out.println(yearsPhase);
		if ( periodtype.equals("yearly")) 
			years.setSelectedIndex(yearsPhase);		
		
		/** filling days **/
		else if ( periodtype.equals("monthly")) {
			if ( yearsPhase < 5) 
				years.setSelectedIndex(yearsPhase);
			else
				years.setSelectedIndex(5);
		}
		else 
			if ( yearsPhase < 3) 
				years.setSelectedIndex(yearsPhase);
			else
				years.setSelectedIndex(3);
	}
	
	public static void cleanListBox(String periodtype, ListBox startyear, ListBox startmonth, ListBox startday, ListBox endyear, ListBox endmonth, ListBox endday) {
		startyear.clear();
		startmonth.clear();
		startday.clear();		
		endyear.clear();
		endmonth.clear();
		endday.clear();	
		startyear.setVisible(true);
		startmonth.setVisible(true);
		startday.setVisible(true);
		endyear.setVisible(true);
		endmonth.setVisible(true);
		endday.setVisible(true);
		
	}
	
	public static void cleanListBox(String periodtype, ListBox years, ListBox months, ListBox days) {
		years.clear();
		months.clear();
		days.clear();				
		years.setVisible(true);
		months.setVisible(true);
		days.setVisible(true);
	}
	
	public static Date setFromDate(DateFromToPanel p){
		Integer yearFrom = new Integer(0);
		Integer monthFrom = new Integer(0);
		Integer dayFrom = new Integer(1);
	
		if ( p.getYearFrom().isEnabled()) 
			yearFrom = new Integer(p.getYearFrom().getItemText(p.getYearFrom().getSelectedIndex())) - 1900;
		if ( p.getMonthFrom().isEnabled()) 
			monthFrom = new Integer(p.getMonthFrom().getItemText(p.getMonthFrom().getSelectedIndex())) -1;
		if ( p.getDayFrom().isEnabled())
			dayFrom = new Integer(p.getDayFrom().getItemText(p.getDayFrom().getSelectedIndex()));
		
//		System.out.println("year: "+ p.getYearFrom().getItemText(p.getYearFrom().getSelectedIndex()));
//		System.out.println("month: " + p.getMonthFrom().getItemText(p.getMonthFrom().getSelectedIndex()));
//		System.out.println("date: "+ p.getDayFrom().getItemText(p.getDayFrom().getSelectedIndex()));
		return new Date(yearFrom, monthFrom, dayFrom);
	}
	
	
	
	public static Date setToDate(DateFromToPanel p){
		Integer yearTo = new Integer(0);
		Integer monthTo = new Integer(0);
		Integer dayTo = new Integer(1);
		

		if ( p.getYearTo().isEnabled()) 
			yearTo = new Integer(p.getYearTo().getItemText(p.getYearTo().getSelectedIndex())) - 1900;
		if ( p.getMonthTo().isEnabled()) 
			monthTo = new Integer(p.getMonthTo().getItemText(p.getMonthTo().getSelectedIndex())) -1;
		if ( p.getDayTo().isEnabled())
			dayTo = new Integer(p.getDayTo().getItemText(p.getDayTo().getSelectedIndex()));

//		System.out.println("year: "+ p.getYearFrom().getItemText(p.getYearFrom().getSelectedIndex()));
//		System.out.println("month: " + p.getMonthFrom().getItemText(p.getMonthFrom().getSelectedIndex()));
//		System.out.println("date: "+ p.getDayFrom().getItemText(p.getDayFrom().getSelectedIndex()));
		return new Date(yearTo, monthTo, dayTo);
	}
	
	public static void disableDateFromToListBox(String periodtype, ListBox startyear, ListBox startmonth, ListBox startday, ListBox endyear, ListBox endmonth, ListBox endday) {	
		/** filling months **/

		System.out.println("DISABLE");
		if ( !periodtype.equals("daily") && !periodtype.equals("dekad") && !periodtype.equals("weekly")) {
//			startday.setVisible(false);
//			endday.setVisible(false);
			startday.setEnabled(false);
			endday.setEnabled(false);
		}
			
		
		/** filling days **/
		if ( periodtype.equals("yearly") ) {
//			startmonth.setVisible(false);
//			startday.setVisible(false);
//			endmonth.setVisible(false);
//			endday.setVisible(false);
			startmonth.setEnabled(false);
			startday.setEnabled(false);
			endmonth.setEnabled(false);
			endday.setEnabled(false);
		}	
//		startyear.setEnabled(false);
//		endyear.setEnabled(false);
//		startmonth.setEnabled(false);
//		startday.setEnabled(false);
//		endmonth.setEnabled(false);
//		endday.setEnabled(false);
	}
	
	public static void disableDateLastUpdateListBox(String periodtype, ListBox years, ListBox months, ListBox days) {	
		/** filling months **/
		if ( !periodtype.equals("daily") && !periodtype.equals("dekad") && !periodtype.equals("weekly")) {
//			days.setVisible(false);
			days.setEnabled(false);
		}
			
		
		/** filling days **/
		if ( periodtype.equals("yearly") ) {
//			months.setVisible(false);
//			days.setVisible(false);
			months.setEnabled(false);
			days.setEnabled(false);
		}	
//		years.setEnabled(false);
//		months.setEnabled(false);
//		days.setEnabled(false);
	}

	public static Date getLastModifiedDate(ListBox yearsListBox, ListBox monthsListBox, ListBox daysListBox, Date startDate, String periodType){
		Integer years = new Integer(0);
		Integer months = new Integer(0);
		Integer days = new Integer(1);
		/** yearly **/
		if ( periodType.equals("yearly")) {
			if ( yearsListBox.getSelectedIndex()  != 0) 
				years = Integer.valueOf(yearsListBox.getItemText(yearsListBox.getSelectedIndex())) -1;
		}
		/** monthly **/
		else if ( periodType.equals("monthly")) {
			if ( yearsListBox.getSelectedIndex()  != 0) {
				years = Integer.valueOf(yearsListBox.getItemText(yearsListBox.getSelectedIndex()));
				if ( monthsListBox.getSelectedIndex() != 0 ) 
					months = Integer.valueOf(monthsListBox.getItemText(monthsListBox.getSelectedIndex()));
			}
			else {
				if ( monthsListBox.getSelectedIndex() != 0 ) 
					months = Integer.valueOf(monthsListBox.getItemText(monthsListBox.getSelectedIndex())) -1;
			}
		}
		/** daily, dekad, etc **/
		else {
			if ( yearsListBox.getSelectedIndex()  != 0) {
				years = Integer.valueOf(yearsListBox.getItemText(yearsListBox.getSelectedIndex()));
				if ( monthsListBox.getSelectedIndex() != 0 ) 
					months = Integer.valueOf(monthsListBox.getItemText(monthsListBox.getSelectedIndex()));
				if ( daysListBox.getSelectedIndex() != 0) 
					days = Integer.valueOf(daysListBox.getItemText(daysListBox.getSelectedIndex()));
			}
			else if ( monthsListBox.getSelectedIndex() != 0 ) {	
				months = Integer.valueOf(monthsListBox.getItemText(monthsListBox.getSelectedIndex()));
				if ( daysListBox.getSelectedIndex() != 0) 
					days = Integer.valueOf(daysListBox.getItemText(daysListBox.getSelectedIndex()));
			}
			else if ( daysListBox.getSelectedIndex() != 0) 
				days = Integer.valueOf(daysListBox.getItemText(daysListBox.getSelectedIndex())) -1;
		}
		
		return getLastModifiedDate(years, months, days, startDate);
	}
	
	public static DateVo getLastModifiedDate(ListBox yearsListBox, ListBox monthsListBox, ListBox daysListBox) {
		String years = "0";
		String months = "0";
		String days = "0";
		if ( yearsListBox.getSelectedIndex()  != 0) 
			years = yearsListBox.getItemText(yearsListBox.getSelectedIndex());
		if ( monthsListBox.getSelectedIndex() != 0 ) 
			months = monthsListBox.getItemText(monthsListBox.getSelectedIndex());
		if ( daysListBox.getSelectedIndex() != 0) 
			days = daysListBox.getItemText(daysListBox.getSelectedIndex());
		return new DateVo(years, months, days);
	}
	
	public static Date getLastModifiedDate(Integer years, Integer months, Integer days, Date latestDate){
		Date result = new Date(latestDate.getYear() - years, latestDate.getMonth() - months, latestDate.getDate() - days);		
//		System.out.println("-----------");	
//		System.out.println("years" + years);
//		System.out.println("months" + months);
//		System.out.println("days " + days);
//		System.out.println("-> " + result);
//		System.out.println("-----------");	
		return result;
	}
}
