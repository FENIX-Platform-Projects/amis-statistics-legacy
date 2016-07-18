package org.fao.fenix.web.modules.welcome.common.services;

import java.util.List;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.welcome.common.vo.QueryResultVO;
import org.fao.fenix.web.modules.welcome.common.vo.QueryVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WelcomeServiceAsync {

	public void search(QueryVO queryVO, AsyncCallback<List<QueryResultVO>> callback) throws FenixGWTException;
	
}