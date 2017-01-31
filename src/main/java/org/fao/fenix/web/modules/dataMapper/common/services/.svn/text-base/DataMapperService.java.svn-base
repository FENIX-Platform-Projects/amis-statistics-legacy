package org.fao.fenix.web.modules.dataMapper.common.services;


import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;

import com.google.gwt.user.client.rpc.RemoteService;

public interface DataMapperService extends RemoteService {
	
	public List<CodeVo> getAllCodes(String codingSystem, String langcode);
	
	public List<CodeVo> getCodesByDescription(String code, String langCode, String codingSystemCode);
	
	public void dropTable(String tablename);
}
