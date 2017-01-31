package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.amis.client.control.input.Month;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.MonthForecast;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;

public class TestMonthForecast {

	public static List<MonthForecast> getMonthForecast() {

		List<MonthForecast> monthForecast = new ArrayList<MonthForecast>();
		
		for (int i = 0; i < CCBS.MONTH_WITH_FLAG.length; i++)
		{
			monthForecast.add(new MonthForecast(CCBS.MONTH_WITH_FLAG[i]));
		}
		
		return monthForecast;
	}
	
	//firstTime is false if this function is called from year combo listener
	public static List<MonthForecast> getMonthForecastFromCbsDb(boolean firstTime) {

		List<MonthForecast> monthForecast = new ArrayList<MonthForecast>();
		int firstMonthIndex = 0;
		int lastMonthIndex = 0;
		int i=0;
		//This value is set at the beginning... when the user select the country
		//If the marketing year information for the commodity and country it's set false
		
	//	System.out.println("***************************|||||||||||||||||||||||||||||||||||Class: TestMonthForecast Function: getMonthForecastFromCbsDb Text: CCBS.MARKETING_YEAR_AVAILABLE "+CCBS.MARKETING_YEAR_AVAILABLE);
//		if(!CCBS.MARKETING_YEAR_AVAILABLE)
//		{
//			monthForecast.add(new MonthForecast(CCBS.N_A_MONTH));
//			return monthForecast;
//		}
//		for(String year: CCBS.YEARS)
//		{
//			System.out.println("Class: TestMonthForecast Function: getMonthForecastFromCbsDb Text: CCBS.YEARS "+ year);
//		}
//		for(String year: CCBS.OTHER_YEARS)
//		{
//			System.out.println("Class: TestMonthForecast Function: getMonthForecastFromCbsDb Text: OTHER_YEARS "+ year);
//		}
		//This is done only if the last year that is in the database ...is the same of the combo box in the last column
		//In fact that means that the user can change the forecast from the month after that is in the database
		if((CCBS.FIRST_MONTH_COMBO!=null)&&(!CCBS.FIRST_MONTH_COMBO.equals(""))&&((CCBS.YEARS.get(CCBS.YEARS.size()-1)).equals(CCBS.OTHER_YEARS.get(0)))&&(firstTime))
		{
			CCBS.FIRST_MONTH_COMBO = CCBSParametersPanel.getOneMonthAfter(CCBS.FIRST_MONTH_COMBO);
		}
		String firstMonthCombo = abbreviatingMonth(CCBS.FIRST_MONTH_COMBO);
		String lastMonthCombo = abbreviatingMonth(CCBS.LAST_MONTH_COMBO);
		//System.out.println("Class: TestMonthForecast Function: getMonthForecastFromCbsDb Text: firstMonthCombo"+ firstMonthCombo);
		//System.out.println("Class: TestMonthForecast Function: getMonthForecastFromCbsDb Text: lastMonthCombo"+ lastMonthCombo);
		if((firstMonthCombo == null)||(firstMonthCombo.equals("")))
		{
			//System.out.println("Class: TestMonthForecast Function: getMonthForecastFromCbsDb Text:Null or empty firstMonthCombo"+ firstMonthCombo);
			//Case COARSE GRAIN and TOTAL CEREAL
//			firstMonthCombo = "Jan";
//			firstMonthIndex = 0;
			monthForecast.add(new MonthForecast(CCBS.N_A_MONTH));
			return monthForecast;
		}
//		if((lastMonthCombo == null)||(lastMonthCombo.equals("")))
//		{
////			lastMonthCombo = "Dec";
////			lastMonthIndex = 11;
//			monthForecast.add(new MonthForecast(CCBS.N_A_MONTH));
//		}
		
		for (i = 0; i < CCBS.MONTH_WITH_FLAG.length; i++)
		{
			if(CCBS.MONTH_WITH_FLAG[i].equalsIgnoreCase(firstMonthCombo))
			{
				firstMonthIndex = i;
			}
			if(CCBS.MONTH_WITH_FLAG[i].equalsIgnoreCase(lastMonthCombo))
			{
				lastMonthIndex = i;
			}
		}
	//	System.out.println("Class: TestMonthForecast Function: getMonthForecastFromCbsDb Text: firstMonthIndex"+ firstMonthIndex);
	//	System.out.println("Class: TestMonthForecast Function: getMonthForecastFromCbsDb Text: lastMonthIndex"+ lastMonthIndex);
		for (i = firstMonthIndex; i <= lastMonthIndex; i++)
		{
			//System.out.println("Class: TestMonthForecast Function: getMonthForecastFromCbsDb Text: Before add CCBS.MONTH_WITH_FLAG[i] "+ CCBS.MONTH_WITH_FLAG[i]);
			monthForecast.add(new MonthForecast(CCBS.MONTH_WITH_FLAG[i]));
		}
		monthForecast.add(new MonthForecast(CCBS.N_A_MONTH));
		return monthForecast;
	}
	
	//"Jan",	"Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"
	//"January","February", "March","April","May","June","July","August","September","October","November","December",
	
	public static String abbreviatingMonth(String monthName)
	{
		String abbreviatedMonth = "";
		if((monthName!=null)&&(!monthName.equalsIgnoreCase("")))
		{
			if(monthName.equalsIgnoreCase("January"))
			{
				abbreviatedMonth = "Jan";
			}
			else if(monthName.equalsIgnoreCase("February"))
			{
				abbreviatedMonth = "Feb";
			}
			else if(monthName.equalsIgnoreCase("March"))
			{
				abbreviatedMonth = "Mar";
			}
			else if(monthName.equalsIgnoreCase("April"))
			{
				abbreviatedMonth = "Apr";
			}
			else if(monthName.equalsIgnoreCase("May"))
			{
				abbreviatedMonth = "May";
			}
			else if(monthName.equalsIgnoreCase("June"))
			{
				abbreviatedMonth = "Jun";
			}
			else if(monthName.equalsIgnoreCase("July"))
			{
				abbreviatedMonth = "Jul";
			}
			else if(monthName.equalsIgnoreCase("August"))
			{
				abbreviatedMonth = "Aug";
			}
			else if(monthName.equalsIgnoreCase("September"))
			{
				abbreviatedMonth = "Sep";
			}
			else if(monthName.equalsIgnoreCase("October"))
			{
				abbreviatedMonth = "Oct";
			}
			else if(monthName.equalsIgnoreCase("November"))
			{
				abbreviatedMonth = "Nov";
			}
			else if(monthName.equalsIgnoreCase("December"))
			{
				abbreviatedMonth = "Dec";
			}
		}		
		return abbreviatedMonth;
	}
	
	public static List<MonthForecast> getShortMonth(boolean isNaNeeded) {

		List<MonthForecast> month = new ArrayList<MonthForecast>();
		month.add(new MonthForecast("Jan"));
		month.add(new MonthForecast("Feb"));
		month.add(new MonthForecast("Mar"));
		month.add(new MonthForecast("Apr"));
		month.add(new MonthForecast("May"));
		month.add(new MonthForecast("Jun"));
		month.add(new MonthForecast("Jul"));
		month.add(new MonthForecast("Aug"));
		month.add(new MonthForecast("Sep"));
		month.add(new MonthForecast("Oct"));
		month.add(new MonthForecast("Nov"));
		month.add(new MonthForecast("Dec"));
		if(isNaNeeded)
		{
			month.add(new MonthForecast("N/A"));
		}
		
		return month;
	}
}
