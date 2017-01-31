package org.fao.fenix.web.modules.r.common.vo;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RResultVO implements IsSerializable {

	private Map<String, Double[][]> results;
	
	/** 
	 * Map<Result Name, Result Type> 
	 * Result Type = {SQUARE_MATRIX, SINGLE_VALUE, LIST}
	 * */
	private Map<String, String> resultType;
	
	private Map<String, String> plots;
	
	public RResultVO() {
		results = new HashMap<String, Double[][]>();
		plots = new HashMap<String, String>();
		resultType = new HashMap<String, String>();
	}

	public Map<String, Double[][]> getResults() {
		return results;
	}

	public void setResults(Map<String, Double[][]> results) {
		this.results = results;
	}

	public Map<String, String> getPlots() {
		return plots;
	}

	public void setPlots(Map<String, String> plots) {
		this.plots = plots;
	}

	public Map<String, String> getResultType() {
		return resultType;
	}

	public void setResultType(Map<String, String> resultType) {
		this.resultType = resultType;
	}
	
}