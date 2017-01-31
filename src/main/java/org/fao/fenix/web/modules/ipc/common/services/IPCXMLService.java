package org.fao.fenix.web.modules.ipc.common.services;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;

import com.google.gwt.user.client.rpc.RemoteService;

public interface IPCXMLService extends RemoteService {

	public List<ModuleVO> getAllModules(String filename, boolean isFromFile);
	
	public ModuleVO getModule(String filename, Integer level, boolean isFromFile);
	
	public String createXml(List<ModuleVO> modulesVO);
	
	public HashMap<String, List<CodeVo>> getIPCCodeList(String langCode);
	
	public List<ProvinceVO> getIPCMapValues(List<ProvinceVO> provincesVO);
	
	public ProvinceVO saveXml(List<ModuleVO> modulesVO, WorkflowInfoVO workflowInfoVO, ProvinceVO provinceVO);
	
	public List<ProvinceVO> saveAllXml(List<List<ModuleVO>> modulesVO, WorkflowInfoVO workflowInfoVO, List<ProvinceVO> provinceVO);
	
	public List<ProvinceVO> getWorkflowProvinces(Long contributor_id, Long workflow_id);
	
}
