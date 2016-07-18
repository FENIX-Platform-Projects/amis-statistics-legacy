package org.fao.fenix.web.modules.cpf.common.services;


import org.fao.fenix.web.modules.cpf.common.vo.CPFQueryVO;
import org.fao.fenix.web.modules.cpf.common.vo.CPFResultVO;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;

import com.google.gwt.user.client.rpc.RemoteService;

public interface CPFService extends RemoteService {

	public CPFResultVO askCPF(CPFQueryVO qvo) throws FenixGWTException;
	
    public String export(CPFResultVO rvo);
	
	//@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY", "ROLE_USER" })
	//public Integer populateCCBS();

}
