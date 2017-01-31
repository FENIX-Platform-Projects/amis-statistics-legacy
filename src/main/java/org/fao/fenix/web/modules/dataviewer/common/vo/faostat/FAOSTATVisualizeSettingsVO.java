package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;

import java.util.HashMap;
import java.util.List;


import com.google.gwt.user.client.rpc.IsSerializable;

public class FAOSTATVisualizeSettingsVO implements IsSerializable {
	
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
	List<FAOSTATVisualizeFilter> filters;
	
	
	/**
	 * List queries to run
	 */
	HashMap<String, List<DWFAOSTATQueryVO>> qvos;

	
	/** 
	 * Questions, used for now in the topic view.
	 * **/
	
	List<FAOSTATVisualizeQuestionsVO> questions;
	
	

	

	public List<FAOSTATVisualizeFilter> getFilters() {
		return filters;
	}


	public void setFilters(List<FAOSTATVisualizeFilter> filters) {
		this.filters = filters;
	}


	public HashMap<String, List<DWFAOSTATQueryVO>> getQvos() {
		return qvos;
	}


	public void setQvos(HashMap<String, List<DWFAOSTATQueryVO>> qvos) {
		this.qvos = qvos;
	}


	public String getViewTitle() {
		return viewTitle;
	}


	public void setViewTitle(String viewTitle) {
		this.viewTitle = viewTitle;
	}


	public List<FAOSTATVisualizeQuestionsVO> getQuestions() {
		return questions;
	}


	public void setQuestions(List<FAOSTATVisualizeQuestionsVO> questions) {
		this.questions = questions;
	}


	public String getLayoutType() {
		return layoutType;
	}


	public void setLayoutType(String layoutType) {
		this.layoutType = layoutType;
	}


	


	
	
}
