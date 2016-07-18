package org.fao.fenix.web.modules.ccbs.server.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fao.fenix.web.modules.ccbs.server.TimeSeries;

class LocalTimeSeries implements TimeSeries
{
	static class LocalElement implements Element
	{
		private Date   date;
		private String featureCode, commodityCode, note;
		private double value;

		LocalElement(Date date, String featureCode, String commodityCode, double value, String note)
		{
			this.date          = date;
			this.featureCode   = featureCode;
			this.commodityCode = commodityCode;
			this.value         = value;
			this.note          = note;
		}

		public Date getDate()            { return date;          }
		public String getFeatureCode()   { return featureCode;   }
		public String getCommodityCode() { return commodityCode; }
		public String getNote()          { return note;          }
		public double getValue()         { return value;         }
	}

	List<Element> elementsList = new ArrayList<Element>();

	public List<Element> elements()
	{
		return elementsList;
	}

	void add(Element el)
	{
		elementsList.add(el);
	}

	TimeSeries select(String featureCode)
	{
		// System.out.println("LocalTimeSeries.select(" + featureCode + ")"); // DEBUG

		LocalTimeSeries ts = new LocalTimeSeries();
		for (TimeSeries.Element el: elementsList)
			if (el.getFeatureCode().equals(featureCode))
				ts.add(el);
		return ts;
	}

	TimeSeries select(String featureCode, String commodityCode)
	{
		// System.out.println("LocalTimeSeries.select(" + featureCode + ", " + commodityCode + ")"); // DEBUG
		
		LocalTimeSeries ts = new LocalTimeSeries();
		for (TimeSeries.Element el: elementsList)
			if (el.getFeatureCode().equals(featureCode) && el.getCommodityCode().equals(commodityCode))
				ts.add(el);
		return ts;
	}
}
