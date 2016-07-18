package org.fao.fenix.web.modules.rainfall.common.services;

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallConfigurationVo;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallParameters;

import com.google.gwt.user.client.rpc.RemoteService;

public interface RainfallService extends RemoteService {

	public List<CodeVo> findAllGaul0();
	
	public List<CodeVo> findAllGaul1FromGaul0(String gaul0);
	
	public List<CodeVo> findAllGaul2FromGaul1(String gaul1);
	
	public String createRainfallReport(RainfallParameters parameters) throws FenixGWTException ;
	
	public List<CodeVo> findAllRainstationFromGaul1(String gaul1);
	
	public List<String> findAllRainfallConfiguration();
	
	public RainfallConfigurationVo getRainfallConfiguration(String filename);
	
	public void saveFavourite(RainfallConfigurationVo vo, String filename);
	
	public void deleteFavourite(String filename);
	
}
