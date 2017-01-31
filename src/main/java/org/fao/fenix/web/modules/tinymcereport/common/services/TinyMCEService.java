package org.fao.fenix.web.modules.tinymcereport.common.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.common.vo.ChartDesignerParametersVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.google.gwt.user.client.rpc.RemoteService;

public interface TinyMCEService extends RemoteService {

	public String load(Long tinyMCEID) throws FenixGWTException;
	
	public Long save(String html, Map<String, List<ChartDesignerParametersVO>> charts, Map<String, OLAPParametersVo> olaps) throws FenixGWTException;
	
	public Long save(String html, boolean isTemplate) throws FenixGWTException;
	
	/** @param resourceTypeIDsMap Map<ResourceType, List<ResourceID>> */
	public void deleteGhostResources(Map<String, List<Long>> resourceTypeIDsMap) throws FenixGWTException;
	
	public Long save(Long reportID, String html, boolean isTemplate) throws FenixGWTException;
	
	public String export(String html, String format) throws FenixGWTException;
	
	public Map<String, String> findAllTemplates() throws FenixGWTException;
	
	public String freeze(Long id, String resourceType, Date date) throws FenixGWTException;
	
}