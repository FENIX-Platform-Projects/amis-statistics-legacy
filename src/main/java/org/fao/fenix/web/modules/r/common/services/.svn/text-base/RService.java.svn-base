package org.fao.fenix.web.modules.r.common.services;

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixMenuItemVo;
import org.fao.fenix.web.modules.r.common.vo.RFunctionVO;
import org.fao.fenix.web.modules.r.common.vo.RResultVO;
import org.fao.fenix.web.modules.r.common.vo.RUserSettingsVO;

import com.google.gwt.user.client.rpc.RemoteService;

public interface RService extends RemoteService {

	public List<FenixMenuItemVo> parseMenu() throws FenixGWTException;
	
	public RFunctionVO parseFunction(String command) throws FenixGWTException;
	
	public RResultVO calculate(RUserSettingsVO usvo) throws FenixGWTException;
	
}