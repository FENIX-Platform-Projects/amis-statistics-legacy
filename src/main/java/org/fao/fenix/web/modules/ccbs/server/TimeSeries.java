package org.fao.fenix.web.modules.ccbs.server;

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
		public double getValue();
	}

	public List<Element> elements();
}
