package org.fao.fenix.web.modules.tinymcereport.common.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TinyMCEServiceAsync {

	public void load(Long tinyMCEID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void save(String html, Map<String, List<ChartDesignerParametersVO>> charts, Map<String, OLAPParametersVo> olaps, AsyncCallback<Long> callback) throws FenixGWTException;
	
	public void save(String html, boolean isTemplate, AsyncCallback<Long> callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void deleteGhostResources(Map<String, List<Long>> resourceTypeIDsMap, AsyncCallback callback) throws FenixGWTException;
	
	public void save(Long reportID, String html, boolean isTemplate, AsyncCallback<Long> callback) throws FenixGWTException;
	
	public void export(String html, String format, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void findAllTemplates(AsyncCallback<Map<String, String>> callback) throws FenixGWTException;
	
	public void freeze(Long id, String resourceType, Date date, AsyncCallback<String> callback) throws FenixGWTException;
	
}