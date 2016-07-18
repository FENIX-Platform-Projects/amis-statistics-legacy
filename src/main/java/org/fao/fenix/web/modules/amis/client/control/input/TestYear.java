package org.fao.fenix.web.modules.amis.client.control.input;

import java.util.ArrayList;
import java.util.List;

public class TestYear {

	public static List<Year> getYear() {

		List<Year> year = new ArrayList<Year>();
		year.add(new Year("1990"));
		year.add(new Year("1991"));
		year.add(new Year("1992"));
		year.add(new Year("1993"));
		year.add(new Year("1994"));
		year.add(new Year("1995"));
		year.add(new Year("1996"));
		year.add(new Year("1997"));
		year.add(new Year("1998"));
		year.add(new Year("1999"));
		year.add(new Year("2000"));
		year.add(new Year("2001"));
		year.add(new Year("2002"));
		year.add(new Year("2003"));
		year.add(new Year("2004"));
		year.add(new Year("2005"));
		year.add(new Year("2006"));
		year.add(new Year("2007"));
		year.add(new Year("2008"));
		year.add(new Year("2009"));
		year.add(new Year("2010"));
		year.add(new Year("2011"));
		year.add(new Year("2012"));
		year.add(new Year("2013"));
		year.add(new Year("2014"));
		year.add(new Year("2015"));
		
		return year;
		  }
	
	public static List<Year> getOtherYear() {

		List<Year> year = new ArrayList<Year>();
		year.add(new Year("2010/11"));
		year.add(new Year("2011/12"));
		year.add(new Year("2012/13"));
		year.add(new Year("2013/14"));
		year.add(new Year("2014/15"));
		year.add(new Year("2015/16"));
		
		return year;
		  }
}
