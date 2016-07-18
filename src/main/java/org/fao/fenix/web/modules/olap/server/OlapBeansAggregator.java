package org.fao.fenix.web.modules.olap.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.olap.OLAPBean;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

@SuppressWarnings("deprecation")
public class OlapBeansAggregator {
	
	private final static Logger LOGGER = Logger.getLogger(OlapBeansAggregator.class);

	public List<OLAPBean> aggregateAsMonthly(OLAPParametersVo p, List<OLAPBean> beans) {
		
		// find axis with the dates
		Axis aggregationAxis = findAggregationAxis(p);
		
		return null;
	}
	
	private List<Integer> findAllYears(List<OLAPBean> beans, Axis aggregationAxis) {
		List<Integer> years = new ArrayList<Integer>();
		for (OLAPBean b : beans) {
			Date date = null;
			switch (aggregationAxis) {
				case x: date = FieldParser.parseDate(b.getXValue()); break;
				case z: date = FieldParser.parseDate(b.getZValue()); break;
				case y: date = FieldParser.parseDate(b.getYValue()); break;
				case w: date = FieldParser.parseDate(b.getWValue()); break;
			}
			Integer year = 1900 + date.getYear();
			if (!years.contains(year))
				years.add(year);
		}
		return years;
	}
	
	private List<String> findAllValues(List<OLAPBean> beans, Axis aggregationAxis) {
		List<String> values = new ArrayList<String>();
		for (OLAPBean b : beans) {
			String value = null;
			switch (aggregationAxis) {
				case x: value = b.getXValue(); break;
				case z: value = b.getZValue(); break;
				case y: value = b.getYValue(); break;
				case w: value = b.getWValue(); break;
			}
			if ((value != null) && !value.contains("date") && !value.contains("Date") && !values.contains(value))
				values.add(value);
		}
		return values;
	}
	
	private Axis findAggregationAxis(OLAPParametersVo p) {
		if (p.isAggregateXAsMonthly())
			return Axis.x;
		else if (p.isAggregateZAsMonthly())
			return Axis.z;
		else if (p.isAggregateYAsMonthly())
			return Axis.y;
		else if (p.isAggregateWAsMonthly())
			return Axis.w;
		else return null;
	}
	
	enum Axis {
		x, z, y, w;
	}
	
}