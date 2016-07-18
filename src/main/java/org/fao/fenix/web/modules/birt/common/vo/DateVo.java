package org.fao.fenix.web.modules.birt.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DateVo implements IsSerializable{

	private String years;
	
	private String months;
	
	private String days;
	
	public DateVo() {	
	}
	
	public DateVo(String years, String months, String days) {
		this.setYears(years);
		this.setMonths(months);
		this.setDays(days);
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}
	
	
}
