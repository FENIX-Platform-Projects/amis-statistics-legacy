package org.fao.fenix.web.modules.venn.common.vo;


import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennProjectsBean implements IsSerializable {
	
	private String intersectionName;
		
	// that's the table of the projects retrieved from the projects dataset 
	private List<GridHeaderVO> headers;
	
	private List<List<String>> projectsRows;
	
	private List<List<String>> projectsRawRows;
	
	// that's the table of the GEO reference retrieved from the projects dataset 
	private List<GridHeaderVO> headersGEO;
	
	private List<List<String>> projectsGEORows;
	
	private List<List<String>> projectsGEORawRows;
	
	// maps	
	private HashMap<String, Double> geoFrequency;
	
	
	private Double value;
	
	public VennProjectsBean() {
	}

	public VennProjectsBean(String intersectionName) {
		this.intersectionName = intersectionName;  
	}
	

	public List<List<String>> getProjectsRows() {
		return projectsRows;
	}

	public void setProjectsRows(List<List<String>> projectsRows) {
		this.projectsRows = projectsRows;
	}

	public List<GridHeaderVO> getHeaders() {
		return headers;
	}

	public void setHeaders(List<GridHeaderVO> headers) {
		this.headers = headers;
	}

	public HashMap<String, Double> getGeoFrequency() {
		return geoFrequency;
	}

	public void setGeoFrequency(HashMap<String, Double> geoFrequency) {
		this.geoFrequency = geoFrequency;
	}

	public List<List<String>> getProjectsRawRows() {
		return projectsRawRows;
	}

	public void setProjectsRawRows(List<List<String>> projectsRawRows) {
		this.projectsRawRows = projectsRawRows;
	}

	public List<List<String>> getProjectsGEORows() {
		return projectsGEORows;
	}

	public void setProjectsGEORows(List<List<String>> projectsGEORows) {
		this.projectsGEORows = projectsGEORows;
	}

	public List<List<String>> getProjectsGEORawRows() {
		return projectsGEORawRows;
	}

	public void setProjectsGEORawRows(List<List<String>> projectsGEORawRows) {
		this.projectsGEORawRows = projectsGEORawRows;
	}

	public List<GridHeaderVO> getHeadersGEO() {
		return headersGEO;
	}

	public void setHeadersGEO(List<GridHeaderVO> headersGEO) {
		this.headersGEO = headersGEO;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getIntersectionName() {
		return intersectionName;
	}

	public void setIntersectionName(String intersectionName) {
		this.intersectionName = intersectionName;
	}


	
	
}
