package org.fao.fenix.web.modules.r.common.services;

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixMenuItemVo;
import org.fao.fenix.web.modules.r.common.vo.RFunctionVO;
import org.fao.fenix.web.modules.r.common.vo.RResultVO;
import org.fao.fenix.web.modules.r.common.vo.RUserSettingsVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RServiceAsync {

	public void parseMenu(AsyncCallback<List<FenixMenuItemVo>> callback) throws FenixGWTException;
	
	public void parseFunction(String command, AsyncCallback<RFunctionVO> callback) throws FenixGWTException;
	
	public void calculate(RUserSettingsVO usvo, AsyncCallback<RResultVO> callback) throws FenixGWTException;
	
}
