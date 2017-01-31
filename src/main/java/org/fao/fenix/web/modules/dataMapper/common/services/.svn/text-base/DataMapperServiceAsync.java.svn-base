package org.fao.fenix.web.modules.dataMapper.common.services;

import java.util.List;


import org.fao.fenix.web.modules.coding.common.vo.CodeVo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataMapperServiceAsync {
	
	public void getAllCodes(String codingSystem, String langcode, AsyncCallback<List<CodeVo>> callback);
	
	public void getCodesByDescription(String code, String langCode, String codingSystemCode, AsyncCallback<List<CodeVo>> callback);

	public void dropTable(String tablename, AsyncCallback callback);

	
}
