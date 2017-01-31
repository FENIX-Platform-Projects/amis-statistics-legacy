package org.fao.fenix.web.modules.amis.common.vo;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AMISSettingsVO implements IsSerializable {
	
	/** 
	 * this is the title of the view
	 * TODO: make it dynamic and derived from the selections (?)
	 */
	String viewTitle;
	
	// Layout type (this can be used to call a specific layout (?))
	 String layoutType;
	
	
	/**
	 * List of filters
	 */
	List<AMISFilterVO> filters;
	
	
	/**
	 * List queries to run
	 */
	HashMap<String, List<AMISQueryVO>> qvos;


	

	public List<AMISFilterVO> getFilters() {
		return filters;
	}


	public void setFilters(List<AMISFilterVO> filters) {
		this.filters = filters;
	}


	public HashMap<String, List<AMISQueryVO>> getQvos() {
		return qvos;
	}


	public void setQvos(HashMap<String, List<AMISQueryVO>> qvos) {
		this.qvos = qvos;
	}


	public String getViewTitle() {
		return viewTitle;
	}


	public void setViewTitle(String viewTitle) {
		this.viewTitle = viewTitle;
	}


	
	public String getLayoutType() {
		return layoutType;
	}


	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}


	


	
	
}
