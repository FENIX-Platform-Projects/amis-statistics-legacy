package org.fao.fenix.web.modules.rainfall.common.services;

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallConfigurationVo;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallParameters;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

public interface RainfallServiceAsync extends RemoteService {

	public void findAllGaul0(AsyncCallback<List<CodeVo>> callback);
	
	public void findAllGaul1FromGaul0(String gaul0, AsyncCallback<List<CodeVo>> callback);
	
	public void findAllGaul2FromGaul1(String gaul1, AsyncCallback<List<CodeVo>> callback);
	
	public void createRainfallReport(RainfallParameters parameters, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void findAllRainstationFromGaul1(String gaul1, AsyncCallback<List<CodeVo>> callback);
	
	public void findAllRainfallConfiguration(AsyncCallback<List<String>> callback);
	
	public void getRainfallConfiguration(String filename, AsyncCallback<RainfallConfigurationVo> callback);
	
	@SuppressWarnings("unchecked")
	public void saveFavourite(RainfallConfigurationVo vo, String filename, AsyncCallback callback);
	
	@SuppressWarnings("unchecked")
	public void deleteFavourite(String filename, AsyncCallback callback);
	
}
