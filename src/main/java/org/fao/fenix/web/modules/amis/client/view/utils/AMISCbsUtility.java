package org.fao.fenix.web.modules.amis.client.view.utils;

import org.fao.fenix.web.modules.amis.common.vo.CCBS;

public class AMISCbsUtility {

	public static String convertNumberToLongMonth(String numberMonth) {
		String longMonth = "";
		if((numberMonth.equalsIgnoreCase("0")))
		{
			longMonth = CCBS.VALIDATED_MONTH;
		}
		else 
		if((numberMonth.equalsIgnoreCase("1")))
		{
			longMonth = "January";
		}
		else if((numberMonth.equalsIgnoreCase("2")))
		{
			longMonth = "February";
		}
		else if((numberMonth.equalsIgnoreCase("3")))
		{
			longMonth = "March";
		}
		else if((numberMonth.equalsIgnoreCase("4")))
		{
			longMonth = "April";
		}
		else if((numberMonth.equalsIgnoreCase("5")))
		{
			longMonth = "May";
		}
		else if((numberMonth.equalsIgnoreCase("6")))
		{
			longMonth = "June";
		}
		else if((numberMonth.equalsIgnoreCase("7")))
		{
			longMonth = "July";
		}
		else if((numberMonth.equalsIgnoreCase("8")))
		{
			longMonth = "August";
		}
		else if((numberMonth.equalsIgnoreCase("9")))
		{
			longMonth = "September";
		}
		else if((numberMonth.equalsIgnoreCase("10")))
		{
			longMonth = "October";
		}
		else if((numberMonth.equalsIgnoreCase("11")))
		{
			longMonth = "November";
		}
		else if((numberMonth.equalsIgnoreCase("12")))
		{
			longMonth = "December";
		}
		return longMonth;
	}
	
	
}
