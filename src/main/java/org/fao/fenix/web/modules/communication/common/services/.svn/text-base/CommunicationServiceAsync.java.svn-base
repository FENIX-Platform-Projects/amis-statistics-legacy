package org.fao.fenix.web.modules.communication.common.services;

import java.util.List;

import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CommunicationServiceAsync {

	public void ping(String target, AsyncCallback<String> callback) throws FenixGWTException;

	public void getCommunicationResources(String peer, AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;

	public void getCommunicationResourcesListLength(String peer, AsyncCallback<Integer> callback) throws FenixGWTException;

	public void getCommunicationResources(String peer, int fromIndex, int toIndex, AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;

	public void getPeerList(String fenixServicesUrl, AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;

	public void getActivePeerList(String fenixServicesUrl, AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;

	public void getActiveGroupPeerList(String fenixServicesUrl, String group, AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;

	public void saveCommunicationResource(String fenixServicesUrl, CommunicationResourceVo rsrc, AsyncCallback<String> callback) throws FenixGWTException;

	@SuppressWarnings("unchecked")
	public void downloadResource(String remoteNodeUrl, String id, String type, AsyncCallback callback) throws FenixGWTException;

	public void findServicesUrl(AsyncCallback<String> callback) throws FenixGWTException;

	public void pushInformation(CommunicationResourceVo rsrc, AsyncCallback<String> callback) throws FenixGWTException;

	public void shaDigest(String fenixServicesUrl, CommunicationResourceVo rsrc, AsyncCallback<String> callback) throws FenixGWTException;

	@SuppressWarnings("unchecked")
	public void importDataset(String localResourceId, String type, AsyncCallback callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void importIPCDataset(String localResourceId, String username, AsyncCallback callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void createIPCMap(String gaul0code, String gaul0label, String layerLabel, String layerCode, String username, String phaseDatasetId, String riskDatasetId, String trendDatasetId, String mapIdentifier, AsyncCallback callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void setCommunicationModuleParameters(String hostLabel, String group, AsyncCallback callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void deleteCommunicationResource(Long id, AsyncCallback callback) throws FenixGWTException;
	
	public void getUserList(String peer, AsyncCallback<List<FenixUserVo>> callback) throws FenixGWTException;
	
	public void getRecordSize(AsyncCallback<Integer> callback) throws FenixGWTException;
	
	public void getLocalSize(AsyncCallback<Integer> callback) throws FenixGWTException;
	
	public void findAllPaged(int startIndex, int items, AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;
	
	public void findAllLocalPaged(int startIndex, int items, AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void shareDataset(long datasetId, AsyncCallback callback) throws FenixGWTException;
	
	public void getActivePeerListVo(AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void shareDataset(long datasetId, String targetPeer, AsyncCallback callback) throws FenixGWTException;
	
	public void getPeerList(AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;
	
	public void saveCommunicationResourceVo(CommunicationResourceVo vo, AsyncCallback<Long> callback) throws FenixGWTException;
	
	public void searchRemote(CommunicationResourceVo params, AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;
	
	public void searchLocal(CommunicationResourceVo params, AsyncCallback<List<CommunicationResourceVo>> callback) throws FenixGWTException;
	
	@SuppressWarnings("unchecked")
	public void shareText(long resourceId, String targetPeer, AsyncCallback callback) throws FenixGWTException;

}
