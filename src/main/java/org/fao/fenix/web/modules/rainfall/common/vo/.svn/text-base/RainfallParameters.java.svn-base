package org.fao.fenix.web.modules.rainfall.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RainfallParameters implements IsSerializable {

	private String country;

	private List<String> regions;
	
	private List<String> stations;

	private List<Date> yearOneDates;
	
	private List<Date> yearTwoDates;

	private Integer yearOne;

	private Integer yearTwo;

	private boolean hasAverage;

	private boolean hasChart;

	private boolean hasTable;

	private boolean hasMap;
	
	private boolean hasNdvi;
	
	private boolean hasCumulate;
	
	private boolean hasGeneralClimate;
	
	private String title;
	
	private String xLabel;
	
	private String yLabel;

	public boolean hasAverage() {
		return this.hasAverage;
	}

	public boolean hasChart() {
		return this.hasChart;
	}

	public boolean hasTable() {
		return this.hasTable;
	}

	public boolean hasMap() {
		return this.hasMap;
	}
	
	public boolean hasCumulate() {
		return this.hasCumulate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getYearOne() {
		return yearOne;
	}

	public void setYearOne(Integer yearOne) {
		this.yearOne = yearOne;
	}

	public Integer getYearTwo() {
		return yearTwo;
	}

	public void setYearTwo(Integer yearTwo) {
		this.yearTwo = yearTwo;
	}

	public void setHasAverage(boolean hasAverage) {
		this.hasAverage = hasAverage;
	}

	public void setHasChart(boolean hasChart) {
		this.hasChart = hasChart;
	}

	public void setHasTable(boolean hasTable) {
		this.hasTable = hasTable;
	}
	
	public void setHasCumulate(boolean hasCumulate) {
		this.hasCumulate = hasCumulate;
	}

	public void setHasMap(boolean hasMap) {
		this.hasMap = hasMap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getXLabel() {
		return xLabel;
	}

	public void setXLabel(String label) {
		xLabel = label;
	}

	public String getYLabel() {
		return yLabel;
	}

	public void setYLabel(String label) {
		yLabel = label;
	}

	public List<Date> getYearOneDates() {
		return yearOneDates;
	}

	public void setYearOneDates(List<Date> yearOneDates) {
		this.yearOneDates = yearOneDates;
	}

	public List<Date> getYearTwoDates() {
		return yearTwoDates;
	}

	public void setYearTwoDates(List<Date> yearTwoDates) {
		this.yearTwoDates = yearTwoDates;
	}

	public List<String> getRegion() {
		return regions;
	}

	public void setRegion(List<String> regions) {
		this.regions = regions;
	}
	
	public void addRegion(String region) {
		if (this.regions == null)
			this.regions = new ArrayList<String>();
		this.regions.add(region);
	}

	public List<String> getRegions() {
		return regions;
	}

	public void setRegions(List<String> regions) {
		this.regions = regions;
	}

	public List<String> getStations() {
		return stations;
	}

	public void setStations(List<String> stations) {
		this.stations = stations;
	}

	public boolean isHasGeneralClimate() {
		return hasGeneralClimate;
	}

	public void setHasGeneralClimate(boolean hasGeneralClimate) {
		this.hasGeneralClimate = hasGeneralClimate;
	}

	public boolean isHasNdvi() {
		return hasNdvi;
	}

	public void setHasNdvi(boolean hasNdvi) {
		this.hasNdvi = hasNdvi;
	}
	
}