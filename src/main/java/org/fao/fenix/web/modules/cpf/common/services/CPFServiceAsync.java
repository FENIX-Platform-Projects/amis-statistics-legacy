package org.fao.fenix.web.modules.cpf.common.services;


import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.cpf.common.vo.CPFQueryVO;
import org.fao.fenix.web.modules.cpf.common.vo.CPFResultVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CPFServiceAsync {

	public void askCPF(CPFQueryVO qvo, AsyncCallback<CPFResultVO> callback) throws FenixGWTException;
	
	public void export(CPFResultVO rvo, AsyncCallback<String> callback);
	
	
	//@Secured({"IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER"})
	//void populateCCBS(AsyncCallback<Integer> async);

}
