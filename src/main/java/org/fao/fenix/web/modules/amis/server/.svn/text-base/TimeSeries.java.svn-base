package org.fao.fenix.web.modules.amis.server;

import java.util.Date;
import java.util.List;

public interface TimeSeries
{
	public interface Element
	{
		public Date   getDate();
		public String getFeatureCode();
		public String getCommodityCode();
		public String getNote();
		//public double getValue();
		
		
		public String getDatabase();
		public String getRegion_code();
		public String getRegion_name();
		public String getProduct_code();
		public String getProduct_name();
		public String getElement_code();
		public String getElement_name();
		public String getUnits();
		public String getYear();
		public String getValue();
		public String getAnnotation();
		public String getMonth();
	}

	public List<Element> elements();
}
