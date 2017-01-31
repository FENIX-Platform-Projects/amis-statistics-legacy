package org.fao.fenix.web.modules.ccbs.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;

public class FenixCCBSTimeSeries implements TimeSeries
{
	static public class LocalElement implements Element
	{
		private QuantitativeCoreContent content;

		public LocalElement(QuantitativeCoreContent content) {
			this.content = content;
		}

		public Date getDate()            { return content.getDate(); }
		public String getFeatureCode()   { return content.getFeatureCode();   }
		public String getCommodityCode() { return content.getCommodityCode(); }
		public String getNote()          { return content.getFirstIndicator(); }
		public double getValue()         { return content.getQuantity(); }
	}

	final private List<Element> elementsList;

	public FenixCCBSTimeSeries() {
		this(20);
	}
	
	public FenixCCBSTimeSeries(int size) {
		elementsList = new ArrayList<Element>(size);		
	}
	
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

		FenixCCBSTimeSeries ts = new FenixCCBSTimeSeries();
		for (TimeSeries.Element el: elementsList)
			if (el.getFeatureCode().equals(featureCode))
				ts.add(el);
		return ts;
	}

	TimeSeries select(String featureCode, String commodityCode)
	{
		// System.out.println("LocalTimeSeries.select(" + featureCode + ", " + commodityCode + ")"); // DEBUG
		
		FenixCCBSTimeSeries ts = new FenixCCBSTimeSeries();
		for (TimeSeries.Element el: elementsList)
			if (el.getFeatureCode().equals(featureCode) && el.getCommodityCode().equals(commodityCode))
				ts.add(el);
		return ts;
	}
}
