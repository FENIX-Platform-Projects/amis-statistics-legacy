package org.fao.fenix.web.modules.edi.common.services;

import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;
import org.fao.fenix.web.modules.edi.common.vo.FAOStatParametersVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FAOStatServiceAsync {

	public void getFAOStatDimensions(FAOStatParametersVO vo, EDISettings tableName, AsyncCallback<Map<String, String>> callback) throws FenixGWTException;
	
	public void importFAOStatDataset(FAOStatParametersVO vo, AsyncCallback<Long> callback) throws FenixGWTException;
	
	public void exportExcel(FAOStatParametersVO vo, boolean showCodes, boolean showLabels, AsyncCallback<String> callback) throws FenixGWTException;
	
}