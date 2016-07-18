package org.fao.fenix.web.modules.ipc.common.services;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IPCXMLServiceAsync {

	public void getAllModules(String filename, boolean isFromFile, AsyncCallback<List<ModuleVO>> callback);
	
	public void getModule(String filename, Integer level, boolean isFromFile, AsyncCallback<ModuleVO> callback);
	
	public void createXml(List<ModuleVO> modulesVO, AsyncCallback<String> callback);
	
	public void  getIPCCodeList(String langCode, AsyncCallback<HashMap<String, List<CodeVo>>> callback);
	
	public void getIPCMapValues(List<ProvinceVO> xml, AsyncCallback<List<ProvinceVO>> callback);
	
	public void saveXml(List<ModuleVO> modulesVO, WorkflowInfoVO workflowInfoVO, ProvinceVO provinceVO, AsyncCallback<ProvinceVO> callback);
	
	public void saveAllXml(List<List<ModuleVO>> modulesVO, WorkflowInfoVO workflowInfoVO, List<ProvinceVO> provinceVO, AsyncCallback<List<ProvinceVO>> callback);
	
	public void getWorkflowProvinces(Long contributor_id, Long workflow_id, AsyncCallback<List<ProvinceVO>> callback);
}
