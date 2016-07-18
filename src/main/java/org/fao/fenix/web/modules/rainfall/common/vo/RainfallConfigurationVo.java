/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.rainfall.common.vo;

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RainfallConfigurationVo implements IsSerializable {

	private List<CodeVo> regions;
	
	private String fromDecade;
	
	private String fromYear;
	
	private String toDecade;
	
	private String toYear;
	
	private String compareYear;
	
	private String template;
	
	private Boolean cumulate;
	
	private Boolean average;
	
	private Boolean map;
	
	private Boolean chart;
	
	private Boolean table;

	public String getFromDecade() {
		return fromDecade;
	}

	public List<CodeVo> getRegions() {
		return regions;
	}

	public void setRegions(List<CodeVo> regions) {
		this.regions = regions;
	}

	public void setFromDecade(String fromDecade) {
		this.fromDecade = fromDecade;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToDecade() {
		return toDecade;
	}

	public void setToDecade(String toDecade) {
		this.toDecade = toDecade;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getCompareYear() {
		return compareYear;
	}

	public void setCompareYear(String compareYear) {
		this.compareYear = compareYear;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Boolean getCumulate() {
		return cumulate;
	}

	public void setCumulate(Boolean cumulate) {
		this.cumulate = cumulate;
	}

	public Boolean getAverage() {
		return average;
	}

	public void setAverage(Boolean average) {
		this.average = average;
	}

	public Boolean getMap() {
		return map;
	}

	public void setMap(Boolean map) {
		this.map = map;
	}

	public Boolean getChart() {
		return chart;
	}

	public void setChart(Boolean chart) {
		this.chart = chart;
	}

	public Boolean getTable() {
		return table;
	}

	public void setTable(Boolean table) {
		this.table = table;
	}
	
}