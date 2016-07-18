package org.fao.fenix.web.modules.core.server.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.communication.CommunicationResource;
import org.fao.fenix.core.domain.constants.ResourceType;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.SaveUniqueDao;
import org.fao.fenix.core.persistence.communication.CommunicationDao;
import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.communication.server.CommunicationServiceImpl;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;

public class NetworkSynchronizer {

	private String fenixServicesUrl;

	private String group;

	private String hostLabel;

	private UrlFinder urlFinder;
	
	private CommunicationDao communicationDao;
	
	private CommunicationServiceImpl communicationServiceImpl;
	
	private SaveUniqueDao saveUniqueDao;

	@SuppressWarnings("static-access")
	public void synchronizeNetwork() throws FenixException {
		try {
			Logger.getRootLogger().warn("GIEWS Network synchronization started.");
			List<CommunicationResource> local = getLocalResources();
			communicationServiceImpl = new CommunicationServiceImpl();
			fenixServicesUrl = urlFinder.fenixServicesUrl;
			List<CommunicationResource> peers = communicationDao.getPeerList();
			for (CommunicationResource peer : peers) {
				if ((!peer.getHost().equals(fenixServicesUrl)) && Boolean.parseBoolean(communicationServiceImpl.ping(peer.getHost()))) {
					List<CommunicationResource> remote = getRemoteResources(peer.getHost());
					synchPeer(local, remote, peer);
					Logger.getRootLogger().warn(peer.getHostLabel() + " synchronization complete.");
				} else {
					Logger.getRootLogger().warn(peer.getHostLabel() + " is off.");
				}
			}
			Logger.getRootLogger().warn("GIEWS Network synchronization finished.");
		} catch (FenixGWTException e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	private void synchPeer(List<CommunicationResource> local, List<CommunicationResource> remote, CommunicationResource peer) throws FenixException {
		try {
			List<CommunicationResource> difference = communicationDao.difference(local, remote);
			if (local.size() > remote.size()) {
				// local list is larger than remote one, difference must be pushed to the remote peer
				Logger.getRootLogger().warn("Pushing " + difference.size() + " CommunicationResource(s) to " + peer.getHostLabel());
				for (CommunicationResource crsrc : difference)
					communicationServiceImpl.pushInformation(communicationServiceImpl.rsrc2vo(crsrc), peer.getHost());
			} else {
				// remote list is larger than local one, difference must be saved in the local DB
				Logger.getRootLogger().warn("Saving " + difference.size() + " CommunicationResource(s) in the local DB.");
				for (CommunicationResource crsrc : difference)
					// if (!crsrc.getHost().equals(fenixServicesUrl)) // avoid to store node own resources...
						saveUniqueDao.saveUnique(crsrc);
			}
		} catch (FenixGWTException e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	private List<CommunicationResource> getLocalResources() throws FenixException {
		List<CommunicationResource> all = communicationDao.findAll();
		List<CommunicationResource> local = new ArrayList<CommunicationResource>();
		for (CommunicationResource rsrc : all)
			if (!rsrc.getType().equals(ResourceType.BusinessCard.name()))
				local.add(rsrc);
		return local;
	}
	
	private List<CommunicationResource> getRemoteResources(String peer) throws FenixException {
		List<CommunicationResource> rsrcs = new ArrayList<CommunicationResource>();
		try {
			List<CommunicationResourceVo> vos = communicationServiceImpl.getCommunicationResources(peer);
			Logger.getRootLogger().warn(vos.size() + " rsrc retrieved from peer " + peer);
			for (CommunicationResourceVo vo : vos) 
				 if (!vo.getType().equals(ResourceType.BusinessCard.name())) // TODO Restore this? 
					rsrcs.add(communicationServiceImpl.vo2rsrc(vo));
			Logger.getRootLogger().warn(rsrcs.size() + " converted rsrc");
		} catch (FenixGWTException e) {
			throw new FenixException(e.getMessage());
		}
		return rsrcs;
	}

	public CommunicationResourceVo createBusinessCard() {
		CommunicationResourceVo rsrc = new CommunicationResourceVo();
		rsrc.setHost(fenixServicesUrl);
		rsrc.setHostLabel(hostLabel);
		rsrc.setLocalId("Business Card");
		rsrc.setOGroup(group);
		rsrc.setPrivilege("public");
		rsrc.setTitle("Business Card");
		rsrc.setType("businessCard");
		return rsrc;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void setHostLabel(String hostLabel) {
		this.hostLabel = hostLabel;
	}

	public String getGroup() {
		return group;
	}

	public String getHostLabel() {
		return hostLabel;
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	public void setCommunicationDao(CommunicationDao communicationDao) {
		this.communicationDao = communicationDao;
	}

	public void setSaveUniqueDao(SaveUniqueDao saveUniqueDao) {
		this.saveUniqueDao = saveUniqueDao;
	}
	
}
