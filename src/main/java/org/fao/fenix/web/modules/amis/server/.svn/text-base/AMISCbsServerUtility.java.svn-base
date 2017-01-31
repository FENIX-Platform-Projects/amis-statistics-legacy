package org.fao.fenix.web.modules.amis.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.amis.common.vo.CCBS;

public class AMISCbsServerUtility {

	public List<HashMap<String, String>> lastMonthForecastForYear(List<Object[]> sqlResultForLastForecast, String lastMonthMarketingyear)
	{
		List<HashMap<String, String>> monthForecastToQuery = new ArrayList<HashMap<String, String>>();
		//List<Integer> indexYear = new ArrayList();
		String storedMonth = "";
		String newMonth = "";
		boolean found = false;
		for(int i =0; i< sqlResultForLastForecast.size(); i++)
		{
			found = false;
			for(HashMap<String, String> hashMap: monthForecastToQuery)
			{			
				//storedMonth is the best value until now
				storedMonth = hashMap.get(sqlResultForLastForecast.get(i)[2]);
				System.out.println("Class:AMISCbsServerUtility Function: lastMonthForecastForYear Text: i= "+i+" for sqlResultForLastForecast.get(i)[2]= "+sqlResultForLastForecast.get(i)[2]+" storedMonth= "+storedMonth);
				if(storedMonth!= null)
				{
					System.out.println("Class:AMISCbsServerUtility Function: lastMonthForecastForYear Text: Before toCalculatingTheBestMonth ");
					newMonth = toCalculatingTheBestMonth(storedMonth, (String)sqlResultForLastForecast.get(i)[0], lastMonthMarketingyear);
					System.out.println("Class:AMISCbsServerUtility Function: lastMonthForecastForYear Text: After toCalculatingTheBestMonth sqlResultForLastForecast.get(i)[2]= "+sqlResultForLastForecast.get(i)[2]+" newMonth= "+newMonth);
					hashMap.put((String)sqlResultForLastForecast.get(i)[2], newMonth);
					found = true;
//					if((newMonth.equals(lastMonthMarketingyear))||(newMonth.equals("0")))
//					{
//						found = true;
//					}					
					break;
				}
			}
			if(!found)
			{
				//New Element
				HashMap<String, String> hashMap = new HashMap<String, String>();
				hashMap.put((String)sqlResultForLastForecast.get(i)[2], (String)sqlResultForLastForecast.get(i)[0]);
				System.out.println("Class:AMISCbsServerUtility Function: lastMonthForecastForYear Text:if(!found) sqlResultForLastForecast.get(i)[2] = "+sqlResultForLastForecast.get(i)[2]+" sqlResultForLastForecast.get(i)[0]= "+sqlResultForLastForecast.get(i)[0]);
				monthForecastToQuery.add(hashMap);
			}
		}
		return monthForecastToQuery;
	}
	
	public String toCalculatingTheBestMonth(String oldMonth, String newMonth, String lastMonthMarketingyear)
	{
		System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth oldMonth= "+oldMonth +" newMonth= "+newMonth+" lastMonthMarketingyear= "+lastMonthMarketingyear);
		String monthToReturn = "";
		Integer oldMonthInt;
		Integer newMonthInt;
		int theGreatest;
		String lastMonthMarketingyearString = ""+convertMonthToNumber(lastMonthMarketingyear);
		if(oldMonth == null)
		{
			monthToReturn = newMonth;
			System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth if(oldMonth == null)");
		}
		else
		{
			System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth if(oldMonth == null) else ");
			if(oldMonth.equalsIgnoreCase(""))
			{
				System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth if(oldMonth.equalsIgnoreCase())");
				monthToReturn = newMonth;
			}
			else
			{
				System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth if(oldMonth.equalsIgnoreCase()) else");
				//To check what's the best month to store
				if(oldMonth.equalsIgnoreCase("0"))
				{
					System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth if(oldMonth.equalsIgnoreCase(0))");
					//The value is validated so is the best result
					monthToReturn = oldMonth;
				}
				else if(newMonth.equalsIgnoreCase("0"))
				{
					System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth else if(newMonth.equalsIgnoreCase(0))");
					//The value is validated so is the best result
					monthToReturn = newMonth;
				}
				else if(newMonth.equalsIgnoreCase(oldMonth))
				{
					System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth else if(newMonth.equalsIgnoreCase(oldMonth))");
					monthToReturn = newMonth;
				}
				else if(newMonth.equalsIgnoreCase(lastMonthMarketingyearString))
				{
					System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth else if(newMonth.equalsIgnoreCase(lastMonthMarketingyear))");
					monthToReturn = newMonth;
				}
				else if(oldMonth.equalsIgnoreCase(lastMonthMarketingyearString))
				{
					System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth else if(oldMonth.equalsIgnoreCase(lastMonthMarketingyear))");
					monthToReturn = oldMonth;
				}
				else
				{
					oldMonthInt = Integer.parseInt(oldMonth);
					newMonthInt = Integer.parseInt(newMonth);
					int marketingYearMonthNum = convertMonthToNumber(lastMonthMarketingyear);
					System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth else oldMonthInt="+oldMonthInt+" newMonthInt="+newMonthInt+" marketingYearMonthNum="+marketingYearMonthNum);
					if(newMonthInt<marketingYearMonthNum)
					{
						System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth 111 ");
						if(oldMonthInt<marketingYearMonthNum)
						{
							if(oldMonthInt > newMonthInt)
							{
								System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth 222");
								theGreatest = oldMonthInt;
							}
							else
							{
								System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth  333");
								theGreatest = newMonthInt;
							}
							monthToReturn = "" + theGreatest;
						}
						else
						{
							System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth  444");
							monthToReturn = "" + newMonthInt;
						}
					}
					else
					{
						System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth  555");
						if(oldMonthInt<marketingYearMonthNum)
						{
							System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth  666");
							monthToReturn = "" + oldMonthInt;							
						}
						else
						{
							System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth  777");
							if(oldMonthInt > newMonthInt)
							{
								System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth 888");
								theGreatest = oldMonthInt;
							}
							else
							{
								System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth 999");
								theGreatest = newMonthInt;
							}
							monthToReturn = "" + theGreatest;
						}
					}
					System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth monthToReturn="+monthToReturn);
				}
			}
		}
		System.out.println("Class:AMISCbsServerUtility Function: toCalculatingTheBestMonth Before return monthToReturn="+monthToReturn);		
		return monthToReturn;
	}

	public int convertMonthToNumber(String lastMonthMarketingyear) {
		int marketingYearMonthNum = 1;
		if((lastMonthMarketingyear.equalsIgnoreCase("Jan"))||(lastMonthMarketingyear.equalsIgnoreCase("January")))
		{
			marketingYearMonthNum = 1;
		}
		else if((lastMonthMarketingyear.equalsIgnoreCase("Feb"))||(lastMonthMarketingyear.equalsIgnoreCase("February")))
		{
			marketingYearMonthNum = 2;
		}
		else if((lastMonthMarketingyear.equalsIgnoreCase("Mar"))||(lastMonthMarketingyear.equalsIgnoreCase("March")))
		{
			marketingYearMonthNum = 3;
		}
		else if((lastMonthMarketingyear.equalsIgnoreCase("Apr"))||(lastMonthMarketingyear.equalsIgnoreCase("April")))
		{
			marketingYearMonthNum = 4;
		}
		else if((lastMonthMarketingyear.equalsIgnoreCase("May")))
		{
			marketingYearMonthNum = 5;
		}
		if((lastMonthMarketingyear.equalsIgnoreCase("Jun"))||(lastMonthMarketingyear.equalsIgnoreCase("June")))
		{
			marketingYearMonthNum = 6;
		}
		else if((lastMonthMarketingyear.equalsIgnoreCase("Jul"))||(lastMonthMarketingyear.equalsIgnoreCase("July")))
		{
			marketingYearMonthNum = 7;
		}
		else if((lastMonthMarketingyear.equalsIgnoreCase("Aug"))||(lastMonthMarketingyear.equalsIgnoreCase("August")))
		{
			marketingYearMonthNum = 8;
		}
		else if((lastMonthMarketingyear.equalsIgnoreCase("Sep"))||(lastMonthMarketingyear.equalsIgnoreCase("September")))
		{
			marketingYearMonthNum = 9;
		}
		else if((lastMonthMarketingyear.equalsIgnoreCase("Oct"))||(lastMonthMarketingyear.equalsIgnoreCase("October")))
		{
			marketingYearMonthNum = 10;
		}
		if((lastMonthMarketingyear.equalsIgnoreCase("Nov"))||(lastMonthMarketingyear.equalsIgnoreCase("November")))
		{
			marketingYearMonthNum = 11;
		}
		else if((lastMonthMarketingyear.equalsIgnoreCase("Dec"))||(lastMonthMarketingyear.equalsIgnoreCase("December")))
		{
			marketingYearMonthNum = 12;
		}
		return marketingYearMonthNum;
	}
	
	public String convertMonthToString(int lastMonthMarketingyear) {
		String month = CCBS.VALIDATED_MONTH;
		
		if(lastMonthMarketingyear ==0)
		{
			month = CCBS.VALIDATED_MONTH;
		}
		else if(lastMonthMarketingyear ==1)
		{
			month = "January";
		}
		else if(lastMonthMarketingyear ==2)
		{
			month = "February";
		}
		else if(lastMonthMarketingyear ==3)
		{
			month = "March";
		}
		else if(lastMonthMarketingyear ==4)
		{
			month = "April";
		}
		else if(lastMonthMarketingyear ==5)
		{
			month = "May";
		}
		else if(lastMonthMarketingyear ==6)
		{
			month = "June";
		}
		else if(lastMonthMarketingyear ==7)
		{
			month = "July";
		}
		else if(lastMonthMarketingyear ==8)
		{
			month = "August";
		}
		else if(lastMonthMarketingyear ==9)
		{
			month = "September";
		}
		else if(lastMonthMarketingyear ==10)
		{
			month = "October";
		}
		else if(lastMonthMarketingyear ==11)
		{
			month = "November";
		}
		else if(lastMonthMarketingyear ==12)
		{
			month = "December";
		}	
		return month;
	}
}
