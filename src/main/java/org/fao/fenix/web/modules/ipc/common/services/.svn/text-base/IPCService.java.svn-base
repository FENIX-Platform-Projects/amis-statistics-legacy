package org.fao.fenix.web.modules.ipc.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.common.vo.MapVO;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.UserVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface IPCService extends RemoteService {

	@Secured( { "ROLE_IPC" })
	public String getIPCApplName();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CodeVo> getCountryList();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> getLayerList();
	
	@Secured( { "ROLE_IPC" })
	public Map<String, String> getAdministrativeUnits(String layerCode, String gaulCodeCountry);
	
	@Secured( { "ROLE_IPC" })
	public String openIPCCreateWorkflow(String layerCode, String layerLabel, String gaulCodeCountry, String countryLabel);
	
	@Secured( { "ROLE_IPC" })
	public String openIPCShowWorkflows(String gaulCodeCountry);
	
	@Secured( { "ROLE_IPC" })
	public String getSecurityTokenFromIPC(String username, String name);
	
	@Secured( { "ROLE_IPC" })
	public List<CodeVo> getUserCountryList();
	
	@Secured( { "ROLE_IPC" })
	public List<CodeVo> getCountryWorkflowList(String countryGaulCode);
    
//	@Secured( { "ROLE_IPC" })
	public List<CodeVo> getAreasCodes(String countryGaulCode, String gaulLayer);
	
//	@Secured( { "ROLE_IPC" })
	public UserVO getCurrentUserInfo();
	
//	@Secured( { "ROLE_IPC" })
	public List<UserVO> getUsers(List<CodeVo> name);
	
//	@Secured( { "ROLE_IPC" })
	public Long saveWorkflow(WorkflowInfoVO workflowInfoVO);
	
//	@Secured( { "ROLE_IPC" })
	public void createIPCDatasetsAndMap(List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow);
	
//	@Secured( { "ROLE_IPC" })
	public List<WorkflowInfoVO> getContributorWorkflows(Long contributor_id, String countryCode);
	
//	@Secured( { "ROLE_IPC" })
	public List<ProvinceVO> getWorkflowProvinces(Long contributor_id, Long workflow_id);
	
//	@Secured( { "ROLE_IPC" })
	public List<WorkflowInfoVO> getObserverProvinces(Long observer_id, String countryCode);
	
//	@Secured( { "ROLE_IPC" })
	public MapVO findIPCMap(List<ProvinceVO> userSpecificProvinceVO, WorkflowInfoVO workflow);
		
	public void deleteWork(WorkflowInfoVO workflowInfoVO);
	
	public void saveWork(WorkflowInfoVO workflowInfoVO, ProvinceVO provinceVO);
	
	public Long getWorkflowID(WorkflowInfoVO workflowInfoVO);
	
	public String getXml(Long workId);
	
	public Boolean isModerator(Long workflowId);
	
	public List<UserVO> getContributors(Long workflowId);
	
	public List<UserVO> getObservers(Long WorkflowId);
	
	public List<WorkflowInfoVO> getModeratorsWorkflows(String countryCode);
	
	public Boolean deleteIPCWorkflow(Long workflowId);
	
	public Boolean saveWorkflowInformations(WorkflowInfoVO newWorkflowInfoVO, WorkflowInfoVO workflowInfoVO);

}
