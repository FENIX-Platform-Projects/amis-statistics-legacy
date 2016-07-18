package org.fao.fenix.web.modules.communication.common.services;

import java.util.List;

import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface CommunicationService extends RemoteService {

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String ping(String target) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> getCommunicationResources(String peer) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Integer getCommunicationResourcesListLength(String peer) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> getCommunicationResources(String peer, int fromIndex, int toIndex) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> getPeerList(String fenixServicesUrl) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> getActivePeerList(String fenixServicesUrl) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> getActiveGroupPeerList(String fenixServicesUrl, String group) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String saveCommunicationResource(String fenixServicesUrl, CommunicationResourceVo rsrc) throws FenixGWTException;

	public void downloadResource(String remoteNodeUrl, String id, String type) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String findServicesUrl() throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String pushInformation(CommunicationResourceVo rsrc) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String shaDigest(String fenixServicesUrl, CommunicationResourceVo rsrc) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void importDataset(String localResourceId, String type) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void importIPCDataset(String localResourceId, String username) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void createIPCMap(String gaul0code, String gaul0label, String layerLabel, String layerCode, String username, String phaseDatasetId, String riskDatasetId, String trendDatasetId, String mapIdentifier) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void setCommunicationModuleParameters(String hostLabel, String group) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void deleteCommunicationResource(Long id) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixUserVo> getUserList(String peer) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> findAllPaged(int startIndex, int items) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> findAllLocalPaged(int startIndex, int items) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Integer getRecordSize() throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Integer getLocalSize() throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void shareDataset(long datasetId) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> getActivePeerListVo() throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void shareDataset(long datasetId, String targetPeer) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> getPeerList() throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Long saveCommunicationResourceVo(CommunicationResourceVo vo) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> searchRemote(CommunicationResourceVo params) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> searchLocal(CommunicationResourceVo params) throws FenixGWTException;
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void shareText(long resourceId, String targetPeer) throws FenixGWTException;

}
