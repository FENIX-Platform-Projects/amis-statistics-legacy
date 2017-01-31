package org.fao.fenix.web.modules.ipc.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.common.vo.MapVO;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.UserVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IPCServiceAsync {

	public void getIPCApplName(AsyncCallback<String> callback);
	
	public void getCountryList(AsyncCallback<List<CodeVo>> callback);
	
	public void getLayerList(AsyncCallback<List<String>> callback);
	
	public void getAdministrativeUnits(String layerCode, String gaulCodeCountry, AsyncCallback<Map<String, String>> callback);
	
	public void openIPCCreateWorkflow(String layerCode, String layerLabel, String gaulCodeCountry, String countryLabel, AsyncCallback<String> callback);
	
	public void openIPCShowWorkflows(String gaulCodeCountry, AsyncCallback<String> callback);
		
	public void getSecurityTokenFromIPC(String username, String name, AsyncCallback<String> callback);
	
	public void getUserCountryList(AsyncCallback<List<CodeVo>> callback);
	
	public void getCountryWorkflowList(String countryGaulCode, AsyncCallback<List<CodeVo>> callback);
	
	public void getAreasCodes(String countryGaulCode, String gaulLayer, AsyncCallback<List<CodeVo>> callback);
	
	public void getCurrentUserInfo(AsyncCallback<UserVO> callback);
	
	public void getUsers(List<CodeVo> name, AsyncCallback<List<UserVO>> callback);
	
	@SuppressWarnings("unchecked")
	public void saveWorkflow(WorkflowInfoVO workflowInfoVO, AsyncCallback<Long> callback);
	
	@SuppressWarnings("unchecked")
	public void createIPCDatasetsAndMap(List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow, AsyncCallback callback);
	
	public void getContributorWorkflows(Long contributor_id, String countryCode, AsyncCallback<List<WorkflowInfoVO>> callback);
	
	public void getWorkflowProvinces(Long contributor_id, Long workflow_id, AsyncCallback<List<ProvinceVO>> callback);
	
	public void getObserverProvinces(Long observer_id, String countryCode, AsyncCallback<List<WorkflowInfoVO>> callback);
	
	@SuppressWarnings("unchecked")
	public void findIPCMap(List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow, AsyncCallback<MapVO> callback);
	
	public void deleteWork(WorkflowInfoVO workflowInfoVO, AsyncCallback callback);
	
	public void saveWork(WorkflowInfoVO workflowInfoVO, ProvinceVO provinceVO, AsyncCallback callback);
	
	public void getWorkflowID(WorkflowInfoVO workflowInfoVO, AsyncCallback<Long> callback);
	
	public void getXml(Long workId, AsyncCallback<String> callback);
	
	public void isModerator(Long workflowId, AsyncCallback<Boolean> callback);
	
	public void getContributors(Long workflowId, AsyncCallback<List<UserVO>> callback);
	
	public void getObservers(Long workflowId, AsyncCallback<List<UserVO>> callback);
	
	public void getModeratorsWorkflows(String countryCode, AsyncCallback<List<WorkflowInfoVO>> callback);
	
	public void deleteIPCWorkflow(Long workflowId, AsyncCallback<Boolean> callback);
	
	public void saveWorkflowInformations(WorkflowInfoVO newWorkflowInfoVO, WorkflowInfoVO workflowInfoVO, AsyncCallback<Boolean> callback);
}
