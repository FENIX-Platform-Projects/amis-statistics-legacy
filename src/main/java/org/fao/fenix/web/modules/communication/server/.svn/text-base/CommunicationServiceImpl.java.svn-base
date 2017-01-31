package org.fao.fenix.web.modules.communication.server;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.llom.OMAttributeImpl;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.wsdl.WSDLConstants;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.SharingCode;
import org.fao.fenix.core.domain.communication.CommunicationResource;
import org.fao.fenix.core.domain.constants.ResourceType;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.perspective.TextView;
import org.fao.fenix.core.persistence.communication.CommunicationDao;
import org.fao.fenix.web.modules.communication.common.services.CommunicationService;
import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.fao.fenix.web.modules.core.server.utils.NetworkSynchronizer;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.springframework.security.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CommunicationServiceImpl extends RemoteServiceServlet implements CommunicationService {

	private static EndpointReference targetEPR;
	
	private static final Logger LOGGER = Logger.getLogger(CommunicationServiceImpl.class);

	public static String NAMESPACE;

	public NetworkSynchronizer networkSynchronizer;

	public CommunicationDao communicationDao;

	public long TIMEOUT = 60000;

	UrlFinder urlFinder;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<CommunicationResourceVo> searchLocal(CommunicationResourceVo params) throws FenixGWTException {
		boolean atLeastOneParam = false;
		String hql = "SELECT d " +
					 "FROM " + getFromTable(params) + " d " + 
					 "WHERE d.resourceId > 0 " +
					 "AND d.sharingCode = '" + SharingCode.PublicAndDownload.name() + "' ";
		boolean titleCondition = (params.getTitle() != null && !params.getTitle().equals(""));
		if (titleCondition) {
			hql += "AND (";
			if (titleCondition) {
				if (atLeastOneParam)
					hql += "AND LOWER (d.title) LIKE LOWER( '%" + params.getTitle() + "%') ";
				else
					hql += "LOWER (d.title) LIKE LOWER( '%" + params.getTitle() + "%') ";
				atLeastOneParam = true;
			}
			hql += ")";
		}
		Query query = entityManager.createQuery(hql);
		LOGGER.info(hql);
		if (query.getResultList().size() > 0) {
			List<CommunicationResourceVo> vos = new ArrayList<CommunicationResourceVo>();
			if (params.getType().equalsIgnoreCase("Dataset")) {
				List<Dataset> rsrcs = query.getResultList();
				for (Dataset rsrc : rsrcs)
					vos.add(rsrc2vo(communicationDao.rsrc2communicationrsrc(rsrc, ResourceType.Dataset)));
				return vos;
			} else if (params.getType().equalsIgnoreCase("TextView")) {
				List<TextView> rsrcs = query.getResultList();
				for (TextView rsrc : rsrcs)
					vos.add(rsrc2vo(communicationDao.rsrc2communicationrsrc(rsrc, ResourceType.TextView)));
				return vos;
			}
		}
		return new ArrayList<CommunicationResourceVo>();
	}

	private String getFromTable(CommunicationResourceVo params) {
		if (params.getType().equalsIgnoreCase("TextView"))
			return "TextView";
		return "Dataset";
	}

	@SuppressWarnings("unchecked")
	public List<CommunicationResourceVo> searchRemote(CommunicationResourceVo params) throws FenixGWTException {
		boolean atLeastOneParam = false;
		String hql = "SELECT c " + "FROM CommunicationResource c " + "WHERE c.type = :type ";
		boolean titleCondition = (params.getTitle() != null && !params.getTitle().equals(""));
		boolean hostLabelCondition = (params.getHostLabel() != null && !params.getHostLabel().equals(""));
		boolean groupCondition = (params.getOGroup() != null && !params.getOGroup().equals(""));
		if (titleCondition || hostLabelCondition || groupCondition) {
			hql += "AND (";
			if (titleCondition) {
				if (atLeastOneParam)
					hql += "AND LOWER (c.title) LIKE LOWER( '%" + params.getTitle() + "%') ";
				else
					hql += "LOWER (c.title) LIKE LOWER( '%" + params.getTitle() + "%') ";
				atLeastOneParam = true;
			}
			if (hostLabelCondition) {
				if (atLeastOneParam)
					hql += "AND LOWER (c.hostLabel) LIKE LOWER( '%" + params.getHostLabel() + "%') ";
				else
					hql += "LOWER (c.hostLabel) LIKE LOWER( '%" + params.getHostLabel() + "%') ";
				atLeastOneParam = true;
			}
			if (groupCondition) {
				if (atLeastOneParam)
					hql += "AND LOWER (c.oGroup) LIKE LOWER( '%" + params.getOGroup() + "%') ";
				else
					hql += "LOWER (c.oGroup) LIKE LOWER( '%" + params.getOGroup() + "%') ";
				atLeastOneParam = true;
			}
			hql += ")";
		}
		Query query = entityManager.createQuery(hql);
		query.setParameter("type", params.getType());
		if (query.getResultList().size() > 0) {
			List<CommunicationResource> rsrcs = query.getResultList();
			List<CommunicationResourceVo> vos = new ArrayList<CommunicationResourceVo>();
			for (CommunicationResource rsrc : rsrcs)
				vos.add(rsrc2vo(rsrc));
			return vos;
		}
		return new ArrayList<CommunicationResourceVo>();
	}

	public CommunicationServiceImpl() {
		targetEPR = new EndpointReference("http://localhost:8080/fenix-services/services/CommunicationModuleService");
		NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";
	}

	public Long saveCommunicationResourceVo(CommunicationResourceVo vo) throws FenixGWTException {
		String hql = "SELECT c FROM CommunicationResource c WHERE c.host = :host AND c.hostLabel = :hostLabel ";
		Query query = entityManager.createQuery(hql);
		query.setParameter("host", vo.getHost());
		query.setParameter("hostLabel", vo.getHostLabel());
		if (query.getResultList().size() > 0) {
			CommunicationResource rsrc = (CommunicationResource) query.getResultList().get(0);
			return rsrc.getResourceId();
		} else {
			CommunicationResource rsrc = vo2rsrc(vo);
			communicationDao.save(rsrc);
			return rsrc.getResourceId();
		}
	}

	public CommunicationResource vo2rsrc(CommunicationResourceVo vo) {
		CommunicationResource rsrc = new CommunicationResource();
		rsrc.setDigest(vo.getDigest());
		rsrc.setHost(vo.getHost());
		rsrc.setHostLabel(vo.getHostLabel());
		rsrc.setLocalId(vo.getLocalId());
		rsrc.setOGroup(vo.getOGroup());
		rsrc.setPrivilege(vo.getPrivilege());
		rsrc.setTitle(vo.getTitle());
		rsrc.setType(vo.getType());
		return rsrc;
	}

	public Integer getRecordSize() throws FenixGWTException {
		return communicationDao.getCommunicationResourceSize(ResourceType.Dataset.name());
	}

	public Integer getLocalSize() throws FenixGWTException {
		return communicationDao.getLocalSize();
	}

	private void setEpr(String target) throws FenixGWTException {
		targetEPR = new EndpointReference(target);
	}

	public void setCommunicationModuleParameters(String hostLabel, String group) throws FenixGWTException {
		networkSynchronizer.setHostLabel(hostLabel);
		networkSynchronizer.setGroup(group);
	}

	public void deleteCommunicationResource(Long id) throws FenixGWTException {
		communicationDao.delete(communicationDao.findById(id));
	}

	@SuppressWarnings("unused")
	public String ping(String target) throws FenixGWTException {

		setEpr(target);

		try {

			OMElement pingPayload = CommunicationServicePayload.getPingPayload();
			Options options = new Options();
			options.setTo(targetEPR);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMElement result = sender.sendReceive(pingPayload);
			String response = result.getFirstElement().getText();

			return "true";

		} catch (AxisFault e) {
			return "false";
		}

	}

	public Integer getCommunicationResourcesListLength(String peer) throws FenixGWTException {
		return new Integer(getCommunicationResources(peer).size());
	}

	@SuppressWarnings("unchecked")
	public List<FenixUserVo> getUserList(String peer) throws FenixGWTException {
		List<FenixUserVo> list = new ArrayList<FenixUserVo>();
		try {

			OMElement userListPayload = CommunicationServicePayload.getUserListPayload();
			Options options = new Options();
			EndpointReference target = new EndpointReference(peer);
			options.setTo(target);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMElement root = sender.sendReceive(userListPayload);

			Iterator children = root.getChildren();
			while (children.hasNext()) {
				OMElement node = (OMElement) children.next();
				FenixUserVo rsrc = new FenixUserVo();
				Iterator ats = node.getAllAttributes();
				rsrc.setFirstName(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setLastName(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setLoginName(((OMAttributeImpl) ats.next()).getAttributeValue());
				list.add(rsrc);
			}

		} catch (AxisFault e) {
			throw new FenixGWTException(e.getMessage());
		}
		return list;
	}

	public List<CommunicationResourceVo> getCommunicationResources(String peer, int fromIndex, int toIndex) throws FenixGWTException {
		List<CommunicationResourceVo> fullList = getCommunicationResources(peer);
		List<CommunicationResourceVo> partialList = new ArrayList<CommunicationResourceVo>();
		for (int i = fromIndex; i < toIndex; i++)
			partialList.add(fullList.get(i));
		return partialList;
	}

	public List<CommunicationResourceVo> findAllPaged(int startIndex, int items) throws FenixGWTException {
		List<CommunicationResourceVo> vos = new ArrayList<CommunicationResourceVo>();
		List<CommunicationResource> rsrcs = communicationDao.findAllPaged(startIndex, items);
		for (CommunicationResource rsrc : rsrcs)
			vos.add(rsrc2vo(rsrc));
		return vos;
	}

	public List<CommunicationResourceVo> findAllLocalPaged(int startIndex, int items) throws FenixGWTException {
		List<CommunicationResourceVo> vos = new ArrayList<CommunicationResourceVo>();
		List<CommunicationResource> rsrcs = communicationDao.findAllLocalPaged(startIndex, items);
		for (CommunicationResource rsrc : rsrcs)
			vos.add(rsrc2vo(rsrc));
		return vos;
	}

	public CommunicationResourceVo rsrc2vo(CommunicationResource rsrc) throws FenixGWTException {
		CommunicationResourceVo vo = new CommunicationResourceVo();
		vo.setDigest(rsrc.getDigest());
		vo.setHost(rsrc.getHost());
		vo.setHostLabel(rsrc.getHostLabel());
		if (rsrc.getResourceId() != null)
			vo.setId(rsrc.getResourceId());
		vo.setLocalId(rsrc.getLocalId());
		vo.setOGroup(rsrc.getOGroup());
		vo.setPrivilege(rsrc.getPrivilege());
		vo.setTitle(rsrc.getTitle());
		vo.setType(rsrc.getType());
		return vo;
	}

	@SuppressWarnings("unchecked")
	public List<CommunicationResourceVo> getCommunicationResources(String peer) throws FenixGWTException {

		ArrayList<CommunicationResourceVo> list = new ArrayList<CommunicationResourceVo>();

		try {

			OMElement communicationResourcesPayload = CommunicationServicePayload.getCommunicationResourcesPayload();
			Options options = new Options();
			EndpointReference target = new EndpointReference(peer);
			options.setTo(target);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMElement root = sender.sendReceive(communicationResourcesPayload);

			Iterator children = root.getChildren();
			while (children.hasNext()) {
				OMElement node = (OMElement) children.next();
				CommunicationResourceVo rsrc = new CommunicationResourceVo();
				Iterator ats = node.getAllAttributes();
				rsrc.setId(Long.valueOf(((OMAttributeImpl) ats.next()).getAttributeValue()));
				rsrc.setLocalId(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setTitle(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setType(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setDigest(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setHost(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setHostLabel(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setOGroup(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setPrivilege(((OMAttributeImpl) ats.next()).getAttributeValue());

				list.add(rsrc);
			}

		} catch (AxisFault e) {
			throw new FenixGWTException(e.getMessage());
		}

		return list;
	}

	public List<CommunicationResourceVo> getActivePeerList(String fenixServicesUrl) throws FenixGWTException {
		List<CommunicationResourceVo> peers = getPeerList();
		ArrayList<CommunicationResourceVo> activePeerList = new ArrayList<CommunicationResourceVo>();
		for (int i = 0; i < peers.size(); i++) {
			CommunicationResourceVo rsrc = (CommunicationResourceVo) peers.get(i);
			System.out.println("getActivePeerList() | " + rsrc.getHost() + " | " + ping(rsrc.getHost()));
			if (Boolean.parseBoolean(ping(rsrc.getHost())))
				activePeerList.add(rsrc);
		}
		return activePeerList;
	}

	@SuppressWarnings(value = { "static-access", "unchecked" })
	public List<CommunicationResource> getActivePeerList() throws FenixGWTException {
		String hql = "SELECT DISTINCT c.host FROM CommunicationResource c GROUP BY c.host ";
		List<String> peers = entityManager.createQuery(hql).getResultList();
		ArrayList<CommunicationResource> activePeerList = new ArrayList<CommunicationResource>();
		for (String peer : peers) {
			if (!peer.equals(urlFinder.fenixServicesUrl) && Boolean.parseBoolean(ping(peer))) {
				CommunicationResource tmp = new CommunicationResource();
				tmp.setHost(peer);
				tmp.setHostLabel(getHostLabel(peer));
				activePeerList.add(tmp);
			}
		}
		return activePeerList;
	}

	private String getHostLabel(String host) throws FenixGWTException {
		String hql = "SELECT c.hostLabel FROM CommunicationResource c WHERE c.host = :host ";
		Query query = entityManager.createQuery(hql);
		query.setParameter("host", host);
		if (query.getResultList().size() > 0)
			return (String) query.getResultList().get(0);
		return host;
	}

	public List<CommunicationResourceVo> getActivePeerListVo() throws FenixGWTException {
		List<CommunicationResourceVo> vos = new ArrayList<CommunicationResourceVo>();
		for (CommunicationResource rsrc : getActivePeerList())
			vos.add(rsrc2vo(rsrc));
		return vos;
	}

	public List<CommunicationResourceVo> getActiveGroupPeerList(String fenixServicesUrl, String group) throws FenixGWTException {

		List<CommunicationResourceVo> peers = getPeerList(fenixServicesUrl);
		ArrayList<CommunicationResourceVo> activePeerList = new ArrayList<CommunicationResourceVo>();
		for (int i = 0; i < peers.size(); i++) {
			CommunicationResourceVo rsrc = (CommunicationResourceVo) peers.get(i);
			if (rsrc.getOGroup().equals(group))
				if (Boolean.parseBoolean(ping(rsrc.getHost())))
					activePeerList.add(rsrc);
		}
		return activePeerList;
	}

	public List<CommunicationResourceVo> getPeerList() throws FenixGWTException {
		List<CommunicationResource> peers = communicationDao.getPeerList();
		List<CommunicationResourceVo> crsrcs = new ArrayList<CommunicationResourceVo>();
		for (CommunicationResource peer : peers)
			crsrcs.add(rsrc2vo(peer));
		return crsrcs;
	}

	@SuppressWarnings("unchecked")
	public List<CommunicationResourceVo> getPeerList(String fenixServicesUrl) throws FenixGWTException {

		ArrayList<CommunicationResourceVo> list = new ArrayList<CommunicationResourceVo>();
		setEpr(fenixServicesUrl);
		LOGGER.info("Get peer list " + targetEPR);

		try {

			OMElement peerListPayload = CommunicationServicePayload.getPeerListPayload();
			Options options = new Options();
			options.setTo(targetEPR);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMElement root = sender.sendReceive(peerListPayload);

			Iterator children = root.getChildren();
			while (children.hasNext()) {

				OMElement node = (OMElement) children.next();
				CommunicationResourceVo rsrc = new CommunicationResourceVo();
				Iterator ats = node.getAllAttributes();

				rsrc.setHost(((OMAttributeImpl) ats.next()).getAttributeValue());
				rsrc.setHostLabel(((OMAttributeImpl) ats.next()).getAttributeValue());

				list.add(rsrc);
			}

		} catch (AxisFault e) {
			throw new FenixGWTException(e.getMessage());
		}

		return list;
	}

	public String saveCommunicationResource(String fenixServicesUrl, CommunicationResourceVo rsrc) throws FenixGWTException {
		setEpr(fenixServicesUrl);
		try {
			// save the resource in the local DB
			OMElement saveCommunicationResource = CommunicationServicePayload.getSaveCommunicationResourcePayload(rsrc);
			Options options = new Options();
			options.setTo(targetEPR);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OMElement result = sender.sendReceive(saveCommunicationResource);
			return result.getFirstElement().getText();
		} catch (AxisFault e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	public String shaDigest(String fenixServicesUrl, CommunicationResourceVo rsrc) throws FenixGWTException {
		setEpr(fenixServicesUrl);
		try {
			// save the resource in the local DB
			OMElement saveCommunicationResource = CommunicationServicePayload.getShaDigestPayload(rsrc);
			Options options = new Options();
			options.setTo(targetEPR);
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OMElement result = sender.sendReceive(saveCommunicationResource);
			return result.getFirstElement().getText();
		} catch (AxisFault e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	@SuppressWarnings("static-access")
	public void shareDataset(long datasetId) throws FenixGWTException {
		String hql = "SELECT d FROM Dataset d WHERE d.resourceId = :datasetId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("datasetId", datasetId);
		if (query.getResultList().size() > 0) {
			Dataset dataset = (Dataset) query.getResultList().get(0);
			LOGGER.info("Preparing to share dataset with id " + datasetId);
			CommunicationResource rsrc = communicationDao.rsrc2communicationrsrc(dataset, ResourceType.Dataset);
			rsrc.setHost(urlFinder.fenixServicesUrl);
			rsrc.setHostLabel(networkSynchronizer.getHostLabel());
			rsrc.setOGroup(networkSynchronizer.getGroup());
			pushInformation(rsrc2vo(rsrc));
		} else
			throw new FenixGWTException("Dataset with id " + datasetId + " not found.");
	}

	@SuppressWarnings("static-access")
	public void shareDataset(long datasetId, String targetPeer) throws FenixGWTException {
		String hql = "SELECT d FROM Dataset d WHERE d.resourceId = :datasetId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("datasetId", datasetId);
		if (query.getResultList().size() > 0) {
			Dataset dataset = (Dataset) query.getResultList().get(0);
			LOGGER.info("Preparing to share dataset with id " + datasetId);
			CommunicationResource rsrc = communicationDao.rsrc2communicationrsrc(dataset, ResourceType.Dataset);
			rsrc.setHost(urlFinder.fenixServicesUrl);
			rsrc.setHostLabel(networkSynchronizer.getHostLabel());
			rsrc.setOGroup(networkSynchronizer.getGroup());
			pushInformation(rsrc2vo(rsrc));
		} else
			throw new FenixGWTException("Dataset with id " + datasetId + " not found.");
	}

	@SuppressWarnings("static-access")
	public void shareText(long resourceId, String targetPeer) throws FenixGWTException {
		String hql = "SELECT t FROM TextView t WHERE t.resourceId = :resourceId";
		Query query = entityManager.createQuery(hql);
		query.setParameter("resourceId", resourceId);
		if (query.getResultList().size() > 0) {
			TextView text = (TextView) query.getResultList().get(0);
			LOGGER.info("Preparing to share text with id " + resourceId);
			CommunicationResource rsrc = communicationDao.rsrc2communicationrsrc(text, ResourceType.TextView);
			rsrc.setHost(urlFinder.fenixServicesUrl);
			rsrc.setHostLabel(networkSynchronizer.getHostLabel());
			rsrc.setOGroup(networkSynchronizer.getGroup());
			pushInformation(rsrc2vo(rsrc));
		} else
			throw new FenixGWTException("TextView with id " + resourceId + " not found.");
	}

	public String pushInformation(CommunicationResourceVo rsrc, String targetPeer) throws FenixGWTException {
		String resultMessage = "Share resource failed. Please try again.";
		rsrc.setHostLabel(networkSynchronizer.getHostLabel());
		rsrc.setOGroup(networkSynchronizer.getGroup());
		LOGGER.info("Target set to: " + targetPeer);
		try {
			OMElement pushInfo = CommunicationServicePayload.getPushInfoPayload(rsrc);
			Options options = new Options();
			options.setTo(new EndpointReference(targetPeer));
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OMElement result = sender.sendReceive(pushInfo);
			resultMessage = result.getFirstElement().getText();
		} catch (AxisFault e) {
			throw new FenixGWTException(e.getMessage());
		}
		return resultMessage;
	}

	public String pushInformation(CommunicationResourceVo rsrc) throws FenixGWTException {
		String resultMessage = "Share resource failed. Please try again.";
		rsrc.setHostLabel(networkSynchronizer.getHostLabel());
		rsrc.setOGroup(networkSynchronizer.getGroup());
		List<CommunicationResource> peers = getActivePeerList();
		LOGGER.info("Pushing info to " + peers.size() + " active peers");
		for (int i = 0; i < peers.size(); i++) {
			String target = peers.get(i).getHost();
			LOGGER.info("Target " + i + " set to: " + target);
			try {
				OMElement pushInfo = CommunicationServicePayload.getPushInfoPayload(rsrc);
				Options options = new Options();
				options.setTo(new EndpointReference(target));
				options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
				options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
				options.setTimeOutInMilliSeconds(TIMEOUT);
				ServiceClient sender = new ServiceClient();
				sender.setOptions(options);
				OMElement result = sender.sendReceive(pushInfo);
				resultMessage = result.getFirstElement().getText();
			} catch (AxisFault e) {
				throw new FenixGWTException(e.getMessage());
			}
		}
		return resultMessage;
	}

	public void pushBulkInformation(String fenixServicesUrl, List<CommunicationResourceVo> peers, List<CommunicationResourceVo> resources) throws FenixGWTException {
		for (int i = 0; i < peers.size(); i++) {
			String target = ((CommunicationResourceVo) peers.get(i)).getHost();
			for (int j = 0; j < resources.size(); j++) {
				CommunicationResourceVo rsrc = (CommunicationResourceVo) resources.get(j);
				try {
					OMElement pushInfo = CommunicationServicePayload.getPushInfoPayload(rsrc);
					Options options = new Options();
					options.setTo(new EndpointReference(target));
					options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
					options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
					options.setTimeOutInMilliSeconds(TIMEOUT);
					ServiceClient sender = new ServiceClient();
					sender.setOptions(options);
					// OMElement result = sender.sendReceive(pushInfo);
					sender.sendReceive(pushInfo);
				} catch (AxisFault e) {
					throw new FenixGWTException(e.getMessage());
				}
			}
		}
		LOGGER.info("Push Bulk Informatino Finished.");
	}

	@SuppressWarnings("static-access")
	public String findServicesUrl() throws FenixGWTException {
		System.out.println("*** URL HAS BEEN SET TO " + urlFinder.fenixServicesUrl);
		return urlFinder.fenixServicesUrl;
	}

	public void downloadResource(String remoteNodeUrl, String id, String type) throws FenixGWTException {

		setEpr(remoteNodeUrl);
		try {
			
			Options options = new Options();
			options.setTo(targetEPR);
			LOGGER.info("Target set to: " + options.getTo());
			options.setAction("urn:downloadResourceServer");
			options.setSoapVersionURI(SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI);

			options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS, Constants.VALUE_TRUE);
			options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "temp_dir");
			options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");

			// Increase the time out to receive large attachments
			options.setTimeOutInMilliSeconds(TIMEOUT);

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OperationClient mepClient = sender.createClient(ServiceClient.ANON_OUT_IN_OP);

			MessageContext mc = new MessageContext();
			SOAPEnvelope env = createEnvelope(id, type);
			mc.setEnvelope(env);

			mepClient.addMessageContext(mc);
			mepClient.execute(true); // TODO Huge Problems in here... But why???

			// Let's get the message context for the response
			MessageContext response = mepClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			SOAPBody body = response.getEnvelope().getBody();
			OMElement element = body.getFirstChildWithName(new QName(NAMESPACE, "downloadResourceServerResponse"));
			if (element != null) {
				try {
					processResponse(response, element);
				} catch (Throwable e) {
					e.printStackTrace();
					throw new FenixGWTException(e.getMessage());
				}
			} else {
				throw new FenixGWTException("Malformed response.");
			}
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new FenixGWTException("AxisFault: " + e.getMessage());
		}
	}

	private void processResponse(MessageContext response, OMElement element) throws Exception {
		// retrieve the resource
		OMElement resourceElement = element.getFirstChildWithName(new QName(NAMESPACE, "resource"));
		// retrieving the ID of the attachment
		String resourceElementID = resourceElement.getAttributeValue(new QName("href"));
		// remove the "cid:" prefix
		resourceElementID = resourceElementID.substring(4);
		// Accesing the attachment from the response message context
		/*
		DataHandler dataHandler = response.getAttachment(resourceElementID);
		if (dataHandler != null) {
			// Writing the attachment data (graph image) to a file
			File resourceFile = new File(System.getProperty("java.io.tmpdir") + File.separator + element.getFirstChildWithName(new QName(NAMESPACE, "localId")).getText() + ".zip");
			FileOutputStream outputStream = new FileOutputStream(resourceFile);
			dataHandler.writeTo(outputStream);
			outputStream.flush();
			String localId = element.getFirstChildWithName(new QName(NAMESPACE, "localId")).getText();
			String type = element.getFirstChildWithName(new QName(NAMESPACE, "type")).getText();
			importDataset(localId, type);
		} else {
			throw new FenixGWTException("DataHandler is null");
		}
		*/
	}

	private static SOAPEnvelope createEnvelope(String id, String type) throws FenixGWTException {
		SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
		SOAPEnvelope env = fac.getDefaultEnvelope();
		OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "swa");
		OMElement statsElement = fac.createOMElement("downloadResourceServer", omNs);
		OMElement nameEle = fac.createOMElement("projectName", omNs);
		nameEle.setText(id + ":" + type);
		statsElement.addChild(nameEle);
		env.getBody().addChild(statsElement);
		return env;
	}

	/**
	 * This function has been modified to import resources according to the
	 * field type so importDataset is not a proper name, it should be
	 * importResource
	 */
	public void importDataset(String localResourceId, String type) throws FenixGWTException {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			OMElement pushInfo = CommunicationServicePayload.getImportDatasetPayload(localResourceId, username, type);
			Options options = new Options();
			String url = findServicesUrl();
			LOGGER.info("Setting URL to: " + url);
			options.setTo(new EndpointReference(url));
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);
			ServiceClient sender = new ServiceClient();
			LOGGER.info("ServiceClient is null? " + (sender == null));
			sender.setOptions(options);
			sender.sendReceive(pushInfo);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		} catch (Throwable e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public void importIPCDataset(String localResourceId, String username) throws FenixGWTException {
		try {
			OMElement pushInfo = CommunicationServicePayload.getImportIPCDatasetPayload(localResourceId, username);
			Options options = new Options();
			String url = findServicesUrl();
			LOGGER.info("Setting URL to: " + url);
			options.setTo(new EndpointReference(url));
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);
			ServiceClient sender = new ServiceClient();
			LOGGER.info("ServiceClient is null? " + (sender == null));
			sender.setOptions(options);
			sender.sendReceive(pushInfo);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		} catch (Throwable e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public void createIPCMap(String gaul0code, String gaul0label, String layerLabel, String layerCode, String username, String phaseDatasetId, String  riskDatasetId, String trendDatasetId, String mapIdentifier) throws FenixGWTException {
		try {
			OMElement pushInfo = CommunicationServicePayload.getCreateIPCMapPayload(gaul0code, gaul0label, layerLabel, layerCode, username, phaseDatasetId, riskDatasetId, trendDatasetId, mapIdentifier);
			Options options = new Options();
			String url = findServicesUrl();
			LOGGER.info("Setting URL to: " + url);
			options.setTo(new EndpointReference(url));
			options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
			options.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			options.setTimeOutInMilliSeconds(TIMEOUT);
			ServiceClient sender = new ServiceClient();
			LOGGER.info("ServiceClient is null? " + (sender == null));
			sender.setOptions(options);
			sender.sendReceive(pushInfo);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		} catch (Throwable e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	public void setNetworkSynchronizer(NetworkSynchronizer networkSynchronizer) {
		this.networkSynchronizer = networkSynchronizer;
	}

	public void setCommunicationDao(CommunicationDao communicationDao) {
		this.communicationDao = communicationDao;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
