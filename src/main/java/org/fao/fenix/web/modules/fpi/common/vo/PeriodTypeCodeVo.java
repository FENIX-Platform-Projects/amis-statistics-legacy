package org.fao.fenix.web.modules.fpi.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum PeriodTypeCodeVo implements IsSerializable {

	hourly,
	daily,
	weekly,
	decade,
	monthly,
	annual,
	yearly;
	
	public static PeriodTypeCodeVo valueOfIgnoreCase(String name) {
		for(PeriodTypeCodeVo type : PeriodTypeCodeVo.values()) 
			if(type.toString().equalsIgnoreCase(name))
				return type;
		return null;
	}
	
}
