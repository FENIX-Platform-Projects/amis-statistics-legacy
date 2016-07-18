package org.fao.fenix.web.modules.dataviewer.common.enums;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum FAOSTATAggregationConstant implements IsSerializable {

	SUM, SUBTRACT, 
	
	GROWTH_RATE, ANNUAL_GROWTH_RATE, ANNUAL_GROWTH_RATE_POPULATION, ANNUAL_GROWTH_RATE_LEAST_SQUARE, AVERAGE_OVER_TIME;
	
}
