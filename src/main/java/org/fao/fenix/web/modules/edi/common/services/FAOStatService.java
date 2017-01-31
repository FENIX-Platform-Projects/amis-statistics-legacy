package org.fao.fenix.web.modules.edi.common.services;

import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;
import org.fao.fenix.web.modules.edi.common.vo.FAOStatParametersVO;

import com.google.gwt.user.client.rpc.RemoteService;

public interface FAOStatService extends RemoteService {

	public Map<String, String> getFAOStatDimensions(FAOStatParametersVO vo, EDISettings tableName) throws FenixGWTException;
	
	public Long importFAOStatDataset(FAOStatParametersVO vo) throws FenixGWTException;
	
	public String exportExcel(FAOStatParametersVO vo, boolean showCodes, boolean showLabels) throws FenixGWTException;
	
}