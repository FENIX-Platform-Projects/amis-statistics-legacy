/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.x.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMException;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPBody;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axiom.soap.SOAPProcessingException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.OperationClient;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.wsdl.WSDLConstants;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.Resource;
import org.fao.fenix.core.domain.constants.UploadPolicy;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.layer.DBFeatureLayer;
import org.fao.fenix.core.domain.map.layer.ShpFeatureLayer;
import org.fao.fenix.core.domain.perspective.TextView;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.domain.x.XDescriptor;
import org.fao.fenix.core.domain.x.XOption;
import org.fao.fenix.core.domain.x.XResource;
import org.fao.fenix.core.domain.x.XUniqueValue;
import org.fao.fenix.core.email.EmailReceiver;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.x.XDao;
import org.fao.fenix.core.utils.DatasetImporter;
import org.fao.fenix.core.utils.DcmtImporter;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.core.utils.TextImporter;
import org.fao.fenix.core.x.PayloadFactory;
import org.fao.fenix.core.x.PayloadParser;
import org.fao.fenix.core.x.RSSFactory;
import org.fao.fenix.core.x.XExplorerSearchParameters;
import org.fao.fenix.core.x.XImporter;
import org.fao.fenix.core.x.XLayerUtils;
import org.fao.fenix.core.x.XResourceType;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.server.upload.RasterLayerPersister;
import org.fao.fenix.web.modules.core.server.upload.ShapeFilePersister;
import org.fao.fenix.web.modules.core.server.upload.ShpFeatureLayerPersister;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.x.common.exception.XLayerException;
import org.fao.fenix.web.modules.x.common.services.XService;
import org.fao.fenix.web.modules.x.common.vo.RSSConfigurationVO;
import org.fao.fenix.web.modules.x.common.vo.XDescriptorVO;
import org.fao.fenix.web.modules.x.common.vo.XExplorerSearchParametersVO;
import org.fao.fenix.web.modules.x.common.vo.XResourceVO;
import org.fao.fenix.web.modules.x.common.vo.XUniqueValueVO;
import org.fao.fenix.web.modules.x.server.utils.OptionsFactory;
import org.fao.fenix.web.modules.x.server.utils.RSSWriter;
import org.springframework.security.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class XServiceImpl extends RemoteServiceServlet implements XService {

	private RSSFactory rssFactory;
	
	private RSSWriter rssWriter;
	
	private UrlFinder urlFinder;
	
	private XDao xDao;
	
	private CodecDao codecDao;
	
	private XImporter xImporter;
	
	private DatasetImporter datasetImporter;
	
	private DcmtImporter dcmtImporter;
	
	private TextImporter textImporter;
	
	private XLayerUtils xLayerUtils;
	
	private ShapeFilePersister shapeFilePersister;
	
	private ShpFeatureLayerPersister shpFeatureLayerPersister;
	
	private RasterLayerPersister rasterLayerPersister;
	
	private EmailReceiver emailReceiver;
	
	private String POP_HOST;

	private String USERNAME;

	private String PASSWORD;
	
	private final static Logger LOGGER =  Logger.getLogger(XServiceImpl.class);
	
	private final static String NAMESPACE = "http://webservice.communication.fenix.fao.org/xsd";
	
	private final static int CHUNK_SIZE = 1048576; // FIXME dynamic, indeed
	
	private static Map<String, String> gaulMap;
	
	private static Map<String, String> categoryMap;
	
	@SuppressWarnings("static-access")
	public List<Long> checkEMail() throws FenixGWTException {
		try {
			return emailReceiver.checkEMailAccount(POP_HOST, USERNAME, PASSWORD, urlFinder.fenixWebUrl);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public String getFenixURL() {
		return urlFinder.fenixWebUrl;
	}
	
	public List<String> findAllChunks(String localResourceID, String url) throws FenixGWTException {
		List<String> chunks = new ArrayList<String>();
		try {
			chunks = xDao.findAllChunks(localResourceID, url);
			return chunks;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public List<String> findAllDownloadedChunks(String localResourceID) throws FenixGWTException {
		List<String> paths = new ArrayList<String>();
		try {
			File tmpDir = new File(System.getProperty("java.io.tmpdir"));
			for (File tmpFile : tmpDir.listFiles()) {
				if (tmpFile.getName().startsWith(localResourceID) && tmpFile.getName().endsWith(".zip")) 
					paths.add(tmpFile.getName());
			}		
			return paths;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	public void exportShapefile(Long dbFeatureLayerID) throws FenixGWTException {
		List<String> chunks = new ArrayList<String>();
		try {
			chunks = xLayerUtils.createDBFeatureLayerChunks(dbFeatureLayerID);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public void delete(String resourceID) throws FenixGWTException {
		try {
			Long id = Long.parseLong(resourceID);
			xDao.deleteXResource(id);
			List<XDescriptor> xDescriptors = xDao.findAllXDescriptorByXResourceID(id);
			for (XDescriptor xDescriptor : xDescriptors) {
				xDao.deleteXDescriptor(xDescriptor.getId());
				List<XOption> xOptions = xDao.findAllXOptions(id, xDescriptor.getId());
				for (XOption xOption : xOptions)
					xDao.deleteXOption(xOption.getId());
				List<XUniqueValue> xUniqueValues = xDao.findAllXUniqueValues(id, xDescriptor.getId());
				for (XUniqueValue xUniqueValue : xUniqueValues)
					xDao.deleteXUniqueValue(xUniqueValue.getId());
			}
		} catch (NumberFormatException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public void createXResource(List<Long> resourceIDs, String resourceType) throws FenixGWTException {
		try {
			XResourceType xResourceType = XResourceType.valueOfIgnoreCase(resourceType);
			if (xResourceType == null) {
				throw new FenixGWTException("Resource Type '" + resourceType + "' unknown.");
			} else {
				switch (xResourceType) {
					case dataset: createDatasetXResource(resourceIDs); break;
					case text: createTextXResource(resourceIDs); break;
					case WMSMapProvider: createLayerXResource(resourceIDs); break;
				}
			}
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private void createLayerXResource(List<Long> resourceIDs) throws FenixGWTException {
		try {
			for (Long layerID : resourceIDs) {
				WMSMapProvider w = xDao.findWMSMapProvider(layerID);
				XResource x = null;
				switch (w.getLayerType()) {
					case 8: 
						DBFeatureLayer dbFeatureLayer = xDao.findDBFeatureLayer(layerID);
						if (dbFeatureLayer != null) {
							x = createXResource(dbFeatureLayer, XResourceType.dbfeaturelayer.name());
							xDao.saveUnique(x);
						} else {
							ShpFeatureLayer shpFeatureLayer = xDao.findShpFeatureLayer(layerID);
							x = createXResource(shpFeatureLayer, XResourceType.shplayer.name());
							xDao.saveUnique(x);
						}
						break;
					case 16: 
						x = createXResource(w, XResourceType.rasterlayer.name());
						xDao.saveUnique(x);
					break;
				}
			}
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private void createDatasetXResource(List<Long> resourceIDs) throws FenixGWTException {
		try {
			LOGGER.info(resourceIDs.size() + " RESOURCE ID'S");
			for (Long datasetId : resourceIDs) {
				LOGGER.info("DATASET ID: " + datasetId);
				Dataset dataset = xDao.findDataset(datasetId);
				LOGGER.info("DATASET FOUND: " + dataset.getResourceId());
				XResource x = createDatasetXResource(dataset);
				x.setDatasetType(dataset.getDatasetType().getTitle());
				x.setPeriodTypeCode(dataset.getPeriodTypeCode());
				XResource saved = xDao.saveUnique(x);
				createXDescriptors(datasetId, saved.getResourceId(), dataset.getDatasetType().getDescriptors());
			}
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private void createTextXResource(List<Long> resourceIDs) throws FenixGWTException {
		try {
			for (Long textID : resourceIDs) {
				TextView text = xDao.findText(textID);
				XResource x = createXResource(text, XResourceType.text.name());
				xDao.saveUnique(x);
			}
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("deprecation")
	private void createXDescriptors(Long datasetID, Long xResourceID, List<Descriptor> descriptors) throws FenixGWTException {
		try {
			for (Descriptor d : descriptors) {
				XDescriptor x = new XDescriptor();
				x.setContentDescriptor(d.getContentDescriptor());
				x.setHeader(d.getHeader());
				x.setxResourceID(xResourceID);
				xDao.save(x);
				createXOptions(xResourceID, x.getId(), d.getOptions());
				createXUniqueValues(datasetID, x.getId(), xResourceID, d);
			}
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("deprecation")
	private void createXUniqueValues(Long datasetID, Long xDescriptorID, Long xResourceID, Descriptor d) throws FenixGWTException {
		try {
			if (d.getContentDescriptor().contains("date")) {
				List<UniqueDateValues> dates = xDao.findAllUniqueDateValues(datasetID, d.getHeader(), d.getContentDescriptor());
				for (UniqueDateValues u : dates) {
					XUniqueValue x = new XUniqueValue();
					x.setCode(FieldParser.parseDate(u.getValue()));
					x.setxDescriptorID(xDescriptorID);
					x.setxResourceID(xResourceID);
					xDao.save(x);
				}
			} else {
				List<UniqueTextValues> values = xDao.findAllUniqueTextValues(datasetID, d.getHeader(), d.getContentDescriptor());
				for (UniqueTextValues u : values) {
					XUniqueValue x = new XUniqueValue();
					x.setCode(u.getValue());
					x.setLabel(u.getLabel());
					x.setxDescriptorID(xDescriptorID);
					x.setxResourceID(xResourceID);
					xDao.save(x);
				}
			}
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private void createXOptions(Long xResourceID, Long xDescriptorID, List<Option> options) throws FenixGWTException {
		try {
			for (Option o : options) {
				XOption x = new XOption();
				x.setName(o.getOptionName());
				x.setValue(o.getOptionValue());
				x.setxDescriptorID(xDescriptorID);
				x.setxResourceID(xResourceID);
				xDao.save(x);
			}
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	private XResource createDatasetXResource(Resource r) throws FenixGWTException {
		XResource x = new XResource();
		try {
			x.setAbstractAbstract(r.getAbstractAbstract());
			x.setCategories(r.getCategories());
			x.setCode(r.getCode());
			x.setDateLastUpdate(r.getDateLastUpdate());
			x.setEndDate(r.getEndDate());
			x.setKeywords(r.getKeywords());
			x.setLocalID(String.valueOf(r.getResourceId()));
			x.setProvider(r.getProvider());
			x.setRegion(r.getRegion());
			x.setResourceType(XResourceType.dataset.name());
			x.setSharingCode(r.getSharingCode());
			x.setSource(r.getSource());
			x.setStartDate(r.getStartDate());
			x.setTitle(r.getTitle());
			x.setUrl(urlFinder.xServicesUrl);
			x.setUrlLabel(rssFactory.getHostLabel());
			x.setLocalUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			x.setLocalFirstName("n.a."); // TODO how to get this?
			x.setLocalLastName("n.a."); // TODO how to get this?
			return x;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	private XResource createXResource(Resource r, String resourceType) throws FenixGWTException {
		XResource x = new XResource();
		try {
			x.setAbstractAbstract(r.getAbstractAbstract());
			x.setCategories(r.getCategories());
			x.setCode(r.getCode());
			x.setDateLastUpdate(r.getDateLastUpdate());
			x.setEndDate(r.getEndDate());
			x.setKeywords(r.getKeywords());
			x.setLocalID(String.valueOf(r.getResourceId()));
			x.setProvider(r.getProvider());
			x.setRegion(r.getRegion());
			XResourceType rt = XResourceType.valueOfIgnoreCase(resourceType);
			if (rt != null)
				x.setResourceType(rt.name());
			x.setSharingCode(r.getSharingCode());
			x.setSource(r.getSource());
			x.setStartDate(r.getStartDate());
			x.setTitle(r.getTitle());
			x.setUrl(urlFinder.xServicesUrl);
			x.setUrlLabel(rssFactory.getHostLabel());
			x.setLocalUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			x.setLocalFirstName("n.a."); // TODO how to get this?
			x.setLocalLastName("n.a."); // TODO how to get this?
			return x;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public List<XUniqueValueVO> findAllXUniqueValuesByXResourceIDAndXDescriptorID(Long xResourceID, Long xDescriptorID) throws FenixGWTException {
		List<XUniqueValue> values = new ArrayList<XUniqueValue>();
		try {
			values = xDao.findAllXUniqueValuesByXResourceIDAndXDescriptorID(xResourceID, xDescriptorID);
			List<XUniqueValueVO> vos = new ArrayList<XUniqueValueVO>();
			for (XUniqueValue u : values)
				vos.add(xUniqueValue2VO(u));
			return vos;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public List<XDescriptorVO> findAllXDescriptorByXResourceID(Long xResourceID) throws FenixGWTException {
		List<XDescriptor> descriptors = new ArrayList<XDescriptor>();
		try {
			descriptors = xDao.findAllXDescriptorByXResourceID(xResourceID);
			List<XDescriptorVO> vos = new ArrayList<XDescriptorVO>();
			for (XDescriptor d : descriptors)
				vos.add(xDescriptor2VO(d));
			return vos;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private XUniqueValueVO xUniqueValue2VO(XUniqueValue u) throws FenixGWTException {
		XUniqueValueVO vo = new XUniqueValueVO();
		try {
			vo.setCode(u.getCode());
			vo.setId(u.getId());
			vo.setLabel(u.getLabel());
			vo.setxDescriptorID(u.getxDescriptorID());
			vo.setxResourceID(u.getxResourceID());
			return vo;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private XDescriptorVO xDescriptor2VO(XDescriptor d) throws FenixGWTException {
		XDescriptorVO vo = new XDescriptorVO();
		try {
			vo.setId(d.getId());
			vo.setHeader(d.getHeader());
			vo.setContentDescriptor(d.getContentDescriptor());
			vo.setxResourceID(d.getxResourceID());
			vo.setLocalDescriptorID(d.getLocalDescriptorID());
			return vo;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String requestDataset(String url, String localID, Map<Map<String, String>, Map<String, String>> filterMap, Map<String, XDescriptorVO> descriptorMap) throws FenixGWTException {
		
		Map<String, XDescriptor> coreDescriptorMap = vo2descriptorMap(descriptorMap);
		
		try {
			
			Options options = OptionsFactory.createRequestDatasetOptions(url);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OperationClient mepClient = sender.createClient(ServiceClient.ANON_OUT_IN_OP);
			
			MessageContext mc = new MessageContext();
			SOAPEnvelope env = createRequestDatasetEnvelope(localID, filterMap, coreDescriptorMap);
			mc.setEnvelope(env);

			mepClient.addMessageContext(mc);
			mepClient.execute(true);
			
			MessageContext response = mepClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			SOAPBody body = response.getEnvelope().getBody();
			OMElement element = body.getFirstChildWithName(new QName(NAMESPACE, "requestDatasetResponse"));
			
			if (element != null) {
				processRequestDatasetResponse(response, element);
			} else {
				throw new FenixGWTException("Malformed response.");
			}
			
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}
		
		return "DONE";
	}
	
	public String requestText(String url, String localID) throws FenixGWTException {
		
		try {
			
			Options options = OptionsFactory.createRequestTextOptions(url);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OperationClient mepClient = sender.createClient(ServiceClient.ANON_OUT_IN_OP);
			
			MessageContext mc = new MessageContext();
			SOAPEnvelope env = createRequestTextEnvelope(localID);
			mc.setEnvelope(env);

			mepClient.addMessageContext(mc);
			mepClient.execute(true);
			
			MessageContext response = mepClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			SOAPBody body = response.getEnvelope().getBody();
			OMElement element = body.getFirstChildWithName(new QName(NAMESPACE, "requestTextResponse"));
			
			if (element != null) {
				processRequestTextResponse(response, element);
			} else {
				throw new FenixGWTException("Malformed response.");
			}
			
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}
		
		return "DONE";
	}
	
	public String requestDBFeatureLayerChunkList(String url, String localResourceID) throws FenixGWTException {
		
		try {
			
			Options options = OptionsFactory.requestDBFeatureLayerChunkList(url);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OperationClient mepClient = sender.createClient(ServiceClient.ANON_OUT_IN_OP);
			
			MessageContext mc = new MessageContext();
			SOAPEnvelope env = createRequestDBFeatureLayerChunkListEnvelope(localResourceID);
			mc.setEnvelope(env);

			mepClient.addMessageContext(mc); 
			mepClient.execute(true);
			
			MessageContext response = mepClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			SOAPBody body = response.getEnvelope().getBody();
			OMElement element = body.getFirstChildWithName(new QName(NAMESPACE, "requestDBFeatureLayerChunkListResponse"));
			
			if (element != null) {
				processRequestDBFeatureLayerChunkListEnvelope(response, element);
			} else {
				throw new FenixGWTException("Malformed response.");
			}
			
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}
		
		return "DONE";
	}
	
	public String requestShpFeatureLayerChunkList(String url, String localResourceID) throws FenixGWTException {
		
		try {
			
			Options options = OptionsFactory.requestShpFeatureLayerChunkList(url);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OperationClient mepClient = sender.createClient(ServiceClient.ANON_OUT_IN_OP);
			
			MessageContext mc = new MessageContext();
			SOAPEnvelope env = createRequestShpFeatureLayerChunkListEnvelope(localResourceID);
			mc.setEnvelope(env);

			mepClient.addMessageContext(mc); 
			mepClient.execute(true);
			
			MessageContext response = mepClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			SOAPBody body = response.getEnvelope().getBody();
			OMElement element = body.getFirstChildWithName(new QName(NAMESPACE, "requestShpFeatureLayerChunkListResponse"));
			
			if (element != null) {
				processRequestShpFeatureLayerChunkListEnvelope(response, element);
			} else {
				throw new FenixGWTException("Malformed response.");
			}
			
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}
		
		return "DONE";
	}
	
	public String requestRasterLayerChunkList(String url, String localResourceID) throws FenixGWTException {
		
		LOGGER.info("RASTER: " + url);
		LOGGER.info("RASTER: " + localResourceID);
		
		try {
			
			Options options = OptionsFactory.requestRasterLayerChunkList(url);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OperationClient mepClient = sender.createClient(ServiceClient.ANON_OUT_IN_OP);
			
			MessageContext mc = new MessageContext();
			SOAPEnvelope env = createRequestRasterLayerChunkListEnvelope(localResourceID);
			mc.setEnvelope(env);

			mepClient.addMessageContext(mc); 
			mepClient.execute(true);
			
			MessageContext response = mepClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			SOAPBody body = response.getEnvelope().getBody();
			OMElement element = body.getFirstChildWithName(new QName(NAMESPACE, "requestRasterLayerChunkListResponse"));
			
			if (element != null) {
				processRequestRasterLayerChunkListEnvelope(response, element);
			} else {
				throw new FenixGWTException("Malformed response.");
			}
			
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}
		
		return "DONE";
	}
	
	private Map<String, XDescriptor> vo2descriptorMap(Map<String, XDescriptorVO> descriptorMap) throws FenixGWTException {
		Map<String, XDescriptor> map = new HashMap<String, XDescriptor>();
		try {
			for (String key : descriptorMap.keySet()) {
				XDescriptor d = vo2XDescriptor(descriptorMap.get(key));
				map.put(key, d);
			}
			return map;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private XDescriptor vo2XDescriptor(XDescriptorVO vo) throws FenixGWTException {
		XDescriptor x = new XDescriptor();
		try {
			x.setContentDescriptor(vo.getContentDescriptor());
			x.setHeader(vo.getHeader());
			x.setLocalDescriptorID(vo.getLocalDescriptorID());
			x.setxResourceID(vo.getxResourceID());
			return x;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String requestUpdate(String url, String resourceType, int maxResults) throws FenixGWTException {
		
		LOGGER.info("REQUEST UPDATE AT URL: " + url);
		
		XResourceType xResourceType = null;
		try {
			xResourceType = XResourceType.valueOf(resourceType);
			LOGGER.info("xResourceType: " + xResourceType);
		} catch (IllegalArgumentException e) {
			throw new FenixGWTException("Can't synchronize all the resource types at the same type. Please select a valid Data Type for the Synchronization: Dataset, Text or any type of Layer.");
		}	
		
		int startIndex = 0;
		List<Long> ids = findAllKnownXResourcesIDs(url, xResourceType, startIndex, null);
		for (Long id : ids)
			LOGGER.info("KNOWN ID: " + id);
		
		Integer updateSize = requestUpdateSize(url, xResourceType, ids);
		LOGGER.info("UPDATE SIZE: " + updateSize);
		int steps = calculateSteps(updateSize, maxResults);
		LOGGER.info("STEPS: " + steps);
		
		for (int i = 0 ; i < steps ; i++) {
			requestUpdateAgent(url, xResourceType, ids, maxResults);
			startIndex += maxResults;
			ids = findAllKnownXResourcesIDs(url, xResourceType, startIndex, null);
		}
		
		return "DONE";
	}
	
	private int calculateSteps(int updateSize, int maxResults) throws FenixGWTException {
		try {
			int steps = updateSize / maxResults;
			if (updateSize % maxResults != 0)
				steps++;
			return steps;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String deleteChunks(String url, String localID) throws FenixGWTException { 
		
		try {
			
			Options options = OptionsFactory.createDeleteChunksOptions(url);
			
			OMFactory fac = OMAbstractFactory.getOMFactory();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

			OMElement deleteChunksPayload = fac.createOMElement("deleteChunks", omNs);
			OMElement value = fac.createOMElement("symbol", omNs);
			
			LOGGER.info("FIND ALL CHUNKS FOR " + url + " AND " + localID);
			List<XResource> chunks = xDao.findAllXResourceByType(localID, url, XResourceType.chunk.name());
			LOGGER.info(chunks.size() + " CHUNKS FOUND");
			List<String> chunkPaths = new ArrayList<String>();
			for (XResource x : chunks)
				chunkPaths.add(x.getTitle());
			String payload = PayloadFactory.deleteChunks(chunkPaths);
			
			value.addChild(fac.createOMText(value, payload));
			deleteChunksPayload.addChild(value);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMElement result = sender.sendReceive(deleteChunksPayload);
			String resultString = result.getFirstElement().getText();
			
			LOGGER.info("Flush local DB: START...");
			for (XResource x : chunks)
				xDao.deleteXResource(x.getResourceId());
			LOGGER.info("Flush local DB: DONE");
			
			return resultString;
			
		} catch (AxisFault e) {
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String deleteChunks(String url, String localID, Long importedLayerID, String layerType) throws FenixGWTException { 
		
		try {
			
			Options options = OptionsFactory.createDeleteChunksOptions(url);
			
			OMFactory fac = OMAbstractFactory.getOMFactory();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

			OMElement deleteChunksPayload = fac.createOMElement("deleteChunks", omNs);
			OMElement value = fac.createOMElement("symbol", omNs);
			
			List<XResource> chunks = xDao.findAllXResourceByType(localID, url, XResourceType.chunk.name());
			List<String> chunkPaths = new ArrayList<String>();
			for (XResource x : chunks)
				chunkPaths.add(x.getTitle());
			String payload = PayloadFactory.deleteChunks(chunkPaths);
			
			value.addChild(fac.createOMText(value, payload));
			deleteChunksPayload.addChild(value);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMElement result = sender.sendReceive(deleteChunksPayload);
			String resultString = result.getFirstElement().getText();
			
			LOGGER.info("Flush local DB: START...");
			for (XResource x : chunks)
				xDao.deleteXResource(x.getResourceId());
			LOGGER.info("Flush local DB: DONE");
			
			LOGGER.info("[" + layerType + "] Flush Temporary Directory: START...");
			xLayerUtils.removeLayerRelatedFiles(importedLayerID, layerType);
			LOGGER.info("[" + layerType + "] Flush Temporary Directory: DONE");
			
			return resultString;
			
		} catch (AxisFault e) {
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private Integer requestUpdateSize(String url, XResourceType xResourceType, List<Long> ids) throws FenixGWTException {
		
		try {
			
			Options options = OptionsFactory.createRequestUpdateSizeOptions(url);
			
			OMFactory fac = OMAbstractFactory.getOMFactory();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "tns");

			OMElement requestUpdateSizePayload = fac.createOMElement("requestUpdateSize", omNs);
			OMElement value = fac.createOMElement("symbol", omNs);

			String payload = PayloadFactory.requestUpdate(ids, xResourceType, 0);
			
			value.addChild(fac.createOMText(value, payload));
			requestUpdateSizePayload.addChild(value);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMElement result = sender.sendReceive(requestUpdateSizePayload);
			Integer updateSize = Integer.parseInt(result.getFirstElement().getText());
			return updateSize;
			
		} catch (AxisFault e) {
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private void requestUpdateAgent(String url, XResourceType xResourceType, List<Long> ids, int maxResults) throws FenixGWTException {
		
		try {
			
			Options options = OptionsFactory.createRequestUpdateOptions(url);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OperationClient mepClient = sender.createClient(ServiceClient.ANON_OUT_IN_OP);
			
			MessageContext mc = new MessageContext();
			SOAPEnvelope env = createRequestUpdateEnvelope(ids, xResourceType.name(), maxResults);
			mc.setEnvelope(env);

			mepClient.addMessageContext(mc);
			mepClient.execute(true);
			
			MessageContext response = mepClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			SOAPBody body = response.getEnvelope().getBody();
			OMElement element = body.getFirstChildWithName(new QName(NAMESPACE, "requestUpdateResponse"));
			
			if (element != null) {
				processRequestUpdateResponse(response, element);
			} else {
				throw new FenixGWTException("Malformed response.");
			}
			
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private void processRequestUpdateResponse(MessageContext response, OMElement element) throws Exception {
		OMElement resourceElement = element.getFirstChildWithName(new QName(NAMESPACE, "resource"));
		String resourceElementID = resourceElement.getAttributeValue(new QName("href"));
		resourceElementID = resourceElementID.substring(4);
		LOGGER.info("RESOURCE ELEMENT ID: " + resourceElementID);
		/*
		DataHandler dataHandler = response.getAttachment(resourceElementID);
		if (dataHandler != null) {
			File tmpFile = File.createTempFile("REMOTE_", ".zip");
			FileOutputStream outputStream = new FileOutputStream(tmpFile);
			dataHandler.writeTo(outputStream);
			outputStream.flush();
			List<File> unzippeds = unzip(tmpFile.getAbsolutePath());
			for (File f : unzippeds)
				System.out.println("\tUNZIPPED: " + f.getAbsolutePath());
			FileInputStream inputStream = new FileInputStream(unzippeds.get(0));
			xImporter.importRSS(inputStream);
		} else {
			throw new FenixGWTException("DataHandler is null");
		}
		*/
	}
	
	private String processRequestChunkResponse(MessageContext response, OMElement element, String url, String chunkPath, String localResourceID, int counter) throws FenixGWTException {
		OMElement resourceElement = element.getFirstChildWithName(new QName(NAMESPACE, "resource"));
		String resourceElementID = resourceElement.getAttributeValue(new QName("href"));
		resourceElementID = resourceElementID.substring(4);
		/*
		DataHandler dataHandler = response.getAttachment(resourceElementID);
		if (dataHandler != null) {
			try {
				File tmpFile = File.createTempFile(localResourceID + "_DOWNLOADED_CHUNK_" + counter + "_", ".zip");
				FileOutputStream outputStream = new FileOutputStream(tmpFile);
				dataHandler.writeTo(outputStream);
				outputStream.flush();
				return tmpFile.getAbsolutePath();
			} catch (FileNotFoundException e) {
				LOGGER.error("\t" + resourceElementID + " file not found, try again..." + " ["+counter+"]");
				processRequestChunkResponse(response, resourceElement, url, chunkPath, localResourceID, counter);
			} catch (IOException e) {
				LOGGER.error("\t" + e.getMessage() + " ["+counter+"]");
			}
		} else {
			requestChunk(url, chunkPath, localResourceID, counter);
		}
		*/
		return null;
	}
	
	private void processRequestDBFeatureLayerChunkListEnvelope(MessageContext response, OMElement element) throws Exception {
		OMElement resourceElement = element.getFirstChildWithName(new QName(NAMESPACE, "resource"));
		String resourceElementID = resourceElement.getAttributeValue(new QName("href"));
		resourceElementID = resourceElementID.substring(4);
		/*
		DataHandler dataHandler = response.getAttachment(resourceElementID);
		if (dataHandler != null) {
			File tmpFile = File.createTempFile("REMOTE_", ".zip");
			FileOutputStream outputStream = new FileOutputStream(tmpFile);
			dataHandler.writeTo(outputStream);
			outputStream.flush();
			List<File> unzippeds = unzip(tmpFile.getAbsolutePath());
			for (File f : unzippeds) {
				List<String> paths = PayloadParser.parseChunkPathsFromRequestRequestDBFeatureLayerChunkListPayload(f.getAbsolutePath());
				String localResourceID = PayloadParser.parseLocalResourceIDFromRequestRequestDBFeatureLayerChunkListPayload(f.getAbsolutePath());
				String url = PayloadParser.parseURLFromRequestRequestDBFeatureLayerChunkListPayload(f.getAbsolutePath());
				for (String p : paths) {
					XResource chunk = createChunkXResource(localResourceID, url, p);
					xDao.saveUnique(chunk);
				}
			}
		} else {
			throw new FenixGWTException("DataHandler is null");
		}
		*/
	}
	
	private void processRequestShpFeatureLayerChunkListEnvelope(MessageContext response, OMElement element) throws Exception {
		OMElement resourceElement = element.getFirstChildWithName(new QName(NAMESPACE, "resource"));
		String resourceElementID = resourceElement.getAttributeValue(new QName("href"));
		resourceElementID = resourceElementID.substring(4);
		/*
		DataHandler dataHandler = response.getAttachment(resourceElementID);
		if (dataHandler != null) {
			File tmpFile = File.createTempFile("REMOTE_", ".zip");
			FileOutputStream outputStream = new FileOutputStream(tmpFile);
			dataHandler.writeTo(outputStream);
			outputStream.flush();
			List<File> unzippeds = unzip(tmpFile.getAbsolutePath());
			for (File f : unzippeds) {
				LOGGER.info(f.getAbsolutePath());
				List<String> paths = PayloadParser.parseChunkPathsFromRequestRequestDBFeatureLayerChunkListPayload(f.getAbsolutePath());
				String localResourceID = PayloadParser.parseLocalResourceIDFromRequestRequestDBFeatureLayerChunkListPayload(f.getAbsolutePath());
				String url = PayloadParser.parseURLFromRequestRequestDBFeatureLayerChunkListPayload(f.getAbsolutePath());
				for (String p : paths) {
					XResource chunk = createChunkXResource(localResourceID, url, p);
					xDao.saveUnique(chunk);
					LOGGER.info(chunk.getTitle() + " saved with id " + chunk.getResourceId());
				}
			}
		} else {
			throw new FenixGWTException("DataHandler is null");
		}
		*/
	}
	
	private void processRequestRasterLayerChunkListEnvelope(MessageContext response, OMElement element) throws Exception {
		OMElement resourceElement = element.getFirstChildWithName(new QName(NAMESPACE, "resource"));
		String resourceElementID = resourceElement.getAttributeValue(new QName("href"));
		resourceElementID = resourceElementID.substring(4);
		/*
		DataHandler dataHandler = response.getAttachment(resourceElementID);
		if (dataHandler != null) {
			File tmpFile = File.createTempFile("REMOTE_", ".zip");
			FileOutputStream outputStream = new FileOutputStream(tmpFile);
			dataHandler.writeTo(outputStream);
			outputStream.flush();
			List<File> unzippeds = unzip(tmpFile.getAbsolutePath());
			for (File f : unzippeds) {
				List<String> paths = PayloadParser.parseChunkPathsFromRequestRequestDBFeatureLayerChunkListPayload(f.getAbsolutePath());
				String localResourceID = PayloadParser.parseLocalResourceIDFromRequestRequestDBFeatureLayerChunkListPayload(f.getAbsolutePath());
				String url = PayloadParser.parseURLFromRequestRequestDBFeatureLayerChunkListPayload(f.getAbsolutePath());
				for (String p : paths) {
					XResource chunk = createChunkXResource(localResourceID, url, p);
					xDao.saveUnique(chunk);
				}
			}
		} else {
			throw new FenixGWTException("DataHandler is null");
		}
		*/
	}
	
	public List<String> requestAllChunks(String url, List<String> chunkPaths, String localResourceID) throws FenixGWTException {
		try {
			List<String> chunks = new ArrayList<String>();
			for (int i = 0 ; i < chunkPaths.size() ; i++)
				chunks.add(requestChunk(url, chunkPaths.get(i), localResourceID, i));
			return chunks;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String mergeChunks(List<String> chunks) throws FenixGWTException {
		LOGGER.info("MERGEFILE... START");
		try {
			File merged = File.createTempFile("MERGED_", ".zip");
			FileOutputStream fos = new FileOutputStream(merged);
			for (String chunk : chunks) {
				FileInputStream fis = new FileInputStream(chunk);
				byte buffer[] = new byte[CHUNK_SIZE];
				while (true) {
					int i = fis.read(buffer, 0, CHUNK_SIZE);
					if (i == -1)
						break;
					fos.write(buffer, 0, i);
				}
			}
			fos.flush();
			fos.close();
			LOGGER.info("MERGEFILE... OVER. Please find your merged file @ " + merged.getAbsolutePath());
			return merged.getAbsolutePath();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Long importDBFeatureLayer(String url, String localID, String mergedFilePath) throws FenixGWTException, XLayerException {
		try {
			return shapeFilePersister.persist(mergedFilePath);
		} catch (XLayerException e) {
			LOGGER.info(e.getMessage());
			deleteChunks(url, localID);
			LOGGER.info("DONE");
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Long importShpFeatureLayer(String url, String localID, String mergedFilePath) throws FenixGWTException, XLayerException {
		try {
			return shpFeatureLayerPersister.persist(mergedFilePath);
		} catch (XLayerException e) {
			LOGGER.info(e.getMessage());
			deleteChunks(url, localID);
			LOGGER.info("DONE");
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Long importRasterLayer(String url, String localID, String mergedFilePath) throws FenixGWTException, XLayerException {
		try {
			return rasterLayerPersister.persist(mergedFilePath);
		} catch (XLayerException e) {
			LOGGER.info(e.getMessage());
			deleteChunks(url, localID);
			LOGGER.info("DONE");
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public String requestChunk(String url, String chunkPath, String localResourceID, int counter) throws FenixGWTException {
		
		try {
			
			Options options = OptionsFactory.createRequestChunkOptions(url);
			
			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);
			OperationClient mepClient = sender.createClient(ServiceClient.ANON_OUT_IN_OP);
			
			MessageContext mc = new MessageContext();
			SOAPEnvelope env = createRequestChunkEnvelope(chunkPath);
			mc.setEnvelope(env);

			mepClient.addMessageContext(mc);
			mepClient.execute(true);
			
			MessageContext response = mepClient.getMessageContext(WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			SOAPBody body = response.getEnvelope().getBody();
			OMElement element = body.getFirstChildWithName(new QName(NAMESPACE, "requestChunkResponse"));
			
			if (element != null) {
				return processRequestChunkResponse(response, element, url, chunkPath, localResourceID, counter);
			} else {
				throw new FenixGWTException("Malformed response.");
			}
			
		} catch (AxisFault e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}  catch (Exception e) {
			e.printStackTrace();
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private XResource createChunkXResource(String localResourceID, String url, String filename) throws FenixGWTException {
		XResource x = new XResource();
		try {
			x.setTitle(filename);
			x.setLocalID(localResourceID);
			x.setUrl(url);
			x.setResourceType(XResourceType.chunk.name());
			return x;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private void processRequestDatasetResponse(MessageContext response, OMElement element) throws Exception {
		OMElement resourceElement = element.getFirstChildWithName(new QName(NAMESPACE, "resource"));
		String resourceElementID = resourceElement.getAttributeValue(new QName("href"));
		resourceElementID = resourceElementID.substring(4);
		/*
		DataHandler dataHandler = response.getAttachment(resourceElementID);
		if (dataHandler != null) {
			File tmpFile = File.createTempFile("REMOTE_", ".zip");
			LOGGER.info(tmpFile.getAbsolutePath());
			FileOutputStream outputStream = new FileOutputStream(tmpFile);
			dataHandler.writeTo(outputStream);
			outputStream.flush();
			List<File> unzippeds = unzip(tmpFile.getAbsolutePath());
			LOGGER.info(unzippeds.size() + " unzipped file(s)");
			for (File f : unzippeds)
				LOGGER.info("\t" + f.getAbsolutePath());
			Map<File, File> codingSystemsMap = mapCodingSystemFiles(unzippeds);
			for (File csvFile : codingSystemsMap.keySet()) {
				FileInputStream csvStream = new FileInputStream(csvFile);
				FileInputStream xmlStream = new FileInputStream(codingSystemsMap.get(csvFile));
				dcmtImporter.importCodesFromCSV(xmlStream, csvStream, ",", UploadPolicy.ignore);
			}
			FileInputStream datasetStream = getFileInputStream(unzippeds, "DATASET_", ".csv");
			FileInputStream metadataStream = getFileInputStream(unzippeds, "DATASET_", ".xml");
			datasetImporter.importDataset(datasetStream, metadataStream, UploadPolicy.overwrite, ",");
		} else {
			throw new FenixGWTException("DataHandler is null");
		}
		*/
	}
	
	private void processRequestTextResponse(MessageContext response, OMElement element) throws FenixGWTException {
		try {
			OMElement resourceElement = element.getFirstChildWithName(new QName(NAMESPACE, "resource"));
			String resourceElementID = resourceElement.getAttributeValue(new QName("href"));
			resourceElementID = resourceElementID.substring(4);
			/*
			DataHandler dataHandler = response.getAttachment(resourceElementID);
			if (dataHandler != null) {
				File tmpFile = File.createTempFile("REMOTE_", ".zip");
				LOGGER.info(tmpFile.getAbsolutePath());
				FileOutputStream outputStream = new FileOutputStream(tmpFile);
				dataHandler.writeTo(outputStream);
				outputStream.flush();
				List<File> unzippeds = unzip(tmpFile.getAbsolutePath());
				LOGGER.info(unzippeds.size() + " unzipped file(s):");
				for (File f : unzippeds)
					LOGGER.info("\t" + f.getAbsolutePath());
				FileInputStream csvStream = null;
				FileInputStream xmlStream = null;
				for (File f : unzippeds) {
					if (f.getAbsolutePath().endsWith("csv") || f.getAbsolutePath().endsWith("txt")) {
						csvStream = new FileInputStream(f); 	
					} else if (f.getAbsolutePath().endsWith("xml")) {
						xmlStream = new FileInputStream(f);
					}
				}
				if (csvStream != null && xmlStream != null) {
					textImporter.importText(csvStream, xmlStream);
				} else {
					throw new FenixGWTException("Text and/or Metadata file is null");
				}
			} else {
				throw new FenixGWTException("DataHandler is null");
			}
			*/
		} catch (OMException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		} 
	}
	
	private Map<File, File> mapCodingSystemFiles(List<File> unzippeds) throws FenixGWTException {
		Map<File, File> map = new HashMap<File, File>();
		try {
			List<String> csNames = new ArrayList<String>();
			for (File file : unzippeds) {
				String name = file.getName();
				if (name.startsWith("CODES_") && name.endsWith(".csv")) {
					String csName = extractCSName(name);
					if (!csNames.contains(csName))
						csNames.add(csName);
				}
			}
			for (String csName : csNames) {
				File csvFile = null;
				File xmlFile = null;
				for (File file : unzippeds) {
					if (file.getName().startsWith("CODES_") && file.getName().contains(csName)) {
						if (file.getName().endsWith(".csv"))
							csvFile = file;
						else if (file.getName().endsWith(".xml"))
							xmlFile = file;
						if (csvFile != null && xmlFile != null) {
							map.put(csvFile, xmlFile);
							break;
						}
					}
				}
			}
			return map;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private String extractCSName(String filename) throws FenixGWTException {
		try {
			int startIndex = 0;
			int endIndex = 0;
			for (int i = 0 ; i < filename.length() ; i++) {
				if (filename.charAt(i) == '_') {
					startIndex = i;
					break;
				}
			}
			for (int i = startIndex + 1 ; i < filename.length() ; i++) {
				if (filename.charAt(i) == '_') {
					endIndex = i;
					break;
				}
			}
			return filename.substring(1 + startIndex, endIndex);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private FileInputStream getFileInputStream(List<File> files, String resourceType, String suffix) throws FenixGWTException {
		try {
			for (File file : files) {
				String name = file.getName();
				if (name.contains(resourceType) && name.contains(suffix))
					return new FileInputStream(file);
			}
		} catch (FileNotFoundException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return null;
	}
	
	private List<File> unzip(String filename) throws FenixGWTException {
		List<File> files = new ArrayList<File>();
		try {
			String inFilename = filename;
			ZipInputStream in = new ZipInputStream(new FileInputStream(inFilename));
			List<String> list = list(inFilename);
			for (String name : list) {
				ZipEntry entry = in.getNextEntry();
				String suffix = name.substring(name.length() - 4);
				File unzipped = File.createTempFile(prefix(name), suffix);
				OutputStream out = new FileOutputStream(unzipped);
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) 
					out.write(buf, 0, len);
				out.close();
				files.add(unzipped);
			}
			in.close();
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return files;
	}
	
	private String prefix(String filename) throws FenixGWTException {
		try {
			String prefix = "DIFF_";
			if (filename.contains("DATASET_")) 
				return "DATASET_";
			else if (filename.contains("CODES_")) 
				return "CODES_" + extractCSName(filename) + "_";
			return prefix;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	private static List<String> list(String resourceName) throws FenixGWTException {
		ArrayList<String> list = new ArrayList<String>();
		try {
			try {
				ZipFile zipfile = new ZipFile(resourceName);
				for (Enumeration entries = zipfile.entries(); entries.hasMoreElements();) 
					list.add(((ZipEntry) entries.nextElement()).getName());
			} catch (IOException e) {
				throw new FenixGWTException(e.getMessage());
			}
			return list;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private static SOAPEnvelope createRequestUpdateEnvelope(List<Long> ids, String resourceType, int maxResults) throws FenixGWTException {
		try {
			SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
			SOAPEnvelope env = fac.getDefaultEnvelope();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "swa");
			OMElement statsElement = fac.createOMElement("requestUpdate", omNs);
			XResourceType xResourceType = XResourceType.valueOf(resourceType);
			String payload = PayloadFactory.requestUpdate(ids, xResourceType, maxResults);
			LOGGER.info("REQUEST UPDATE PAYLOAD\n" + payload);
			OMElement payloadElement = fac.createOMElement("payload", omNs);
			payloadElement.setText(payload);
			statsElement.addChild(payloadElement);
			env.getBody().addChild(statsElement);
			return env;
		} catch (SOAPProcessingException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (OMException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private static SOAPEnvelope createRequestChunkEnvelope(String chunkPath) throws FenixGWTException {
		try {
			SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
			SOAPEnvelope env = fac.getDefaultEnvelope();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "swa");
			OMElement statsElement = fac.createOMElement("requestChunk", omNs);
			String payload = PayloadFactory.requestChunk(chunkPath);
			OMElement payloadElement = fac.createOMElement("payload", omNs);
			payloadElement.setText(payload);
			statsElement.addChild(payloadElement);
			env.getBody().addChild(statsElement);
			return env;
		} catch (SOAPProcessingException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (OMException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private static SOAPEnvelope createRequestDatasetEnvelope(String localID, Map<Map<String, String>, Map<String, String>> filterMap, Map<String, XDescriptor> descriptorMap) throws FenixGWTException {
		try {
			SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
			SOAPEnvelope env = fac.getDefaultEnvelope();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "swa");
			OMElement statsElement = fac.createOMElement("requestDataset", omNs);
			String payload = PayloadFactory.requestDataset(localID, filterMap, descriptorMap);
			OMElement payloadElement = fac.createOMElement("payload", omNs);
			payloadElement.setText(payload);
			statsElement.addChild(payloadElement);
			env.getBody().addChild(statsElement);
			return env;
		} catch (SOAPProcessingException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (OMException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private static SOAPEnvelope createRequestTextEnvelope(String localID) throws FenixGWTException {
		try {
			SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
			SOAPEnvelope env = fac.getDefaultEnvelope();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "swa");
			OMElement statsElement = fac.createOMElement("requestText", omNs);
			String payload = PayloadFactory.requestText(localID);
			OMElement payloadElement = fac.createOMElement("payload", omNs);
			payloadElement.setText(payload);
			statsElement.addChild(payloadElement);
			env.getBody().addChild(statsElement);
			return env;
		} catch (SOAPProcessingException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (OMException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private static SOAPEnvelope createRequestDBFeatureLayerChunkListEnvelope(String localResourceID) throws FenixGWTException {
		try {
			SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
			SOAPEnvelope env = fac.getDefaultEnvelope();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "swa");
			OMElement statsElement = fac.createOMElement("requestDBFeatureLayerChunkList", omNs);
			String payload = PayloadFactory.requestDBFeatureLayerChunkList(localResourceID);
			OMElement payloadElement = fac.createOMElement("payload", omNs);
			payloadElement.setText(payload);
			statsElement.addChild(payloadElement);
			env.getBody().addChild(statsElement);
			return env;
		} catch (SOAPProcessingException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (OMException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private static SOAPEnvelope createRequestShpFeatureLayerChunkListEnvelope(String localResourceID) throws FenixGWTException {
		try {
			SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
			SOAPEnvelope env = fac.getDefaultEnvelope();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "swa");
			OMElement statsElement = fac.createOMElement("requestShpFeatureLayerChunkList", omNs);
			String payload = PayloadFactory.requestShpFeatureLayerChunkList(localResourceID);
			OMElement payloadElement = fac.createOMElement("payload", omNs);
			payloadElement.setText(payload);
			statsElement.addChild(payloadElement);
			env.getBody().addChild(statsElement);
			return env;
		} catch (SOAPProcessingException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (OMException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private static SOAPEnvelope createRequestRasterLayerChunkListEnvelope(String localResourceID) throws FenixGWTException {
		try {
			SOAPFactory fac = OMAbstractFactory.getSOAP11Factory();
			SOAPEnvelope env = fac.getDefaultEnvelope();
			OMNamespace omNs = fac.createOMNamespace(NAMESPACE, "swa");
			OMElement statsElement = fac.createOMElement("requestRasterLayerChunkList", omNs);
			String payload = PayloadFactory.requestRasterLayerChunkList(localResourceID);
			LOGGER.info(payload);
			OMElement payloadElement = fac.createOMElement("payload", omNs);
			payloadElement.setText(payload);
			statsElement.addChild(payloadElement);
			env.getBody().addChild(statsElement);
			return env;
		} catch (SOAPProcessingException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (OMException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private List<Long> findAllKnownXResourcesIDs(String url, XResourceType resourceType, Integer startIndex, Integer maxResults) throws FenixGWTException {
		List<Long> ids = new ArrayList<Long>();
		try {
			List<XResource> knownXResources = xDao.findAllXResourcesByTypeAndURL(resourceType, url, startIndex, maxResults);
			for (XResource x : knownXResources)
				ids.add(Long.parseLong(x.getLocalID()));
			return ids;
		} catch (NumberFormatException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public List<XResourceVO> search(XExplorerSearchParametersVO pvo) throws FenixGWTException {
		List<XResourceVO> vos = new ArrayList<XResourceVO>();
		try {
			gaulMap = new HashMap<String, String>();
			categoryMap = new HashMap<String, String>();
			XExplorerSearchParameters p = vo2par(pvo);
			List<XResource> xrsrcs = xDao.search(p);
			for (XResource x : xrsrcs)
				vos.add(xrsrc2vo(x, pvo.getLanguage()));
			return vos;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public int getSearchSize(XExplorerSearchParametersVO pvo) throws FenixGWTException {
		XExplorerSearchParameters p = new XExplorerSearchParameters();
		try {
			p = vo2par(pvo);
			return xDao.getSearchSize(p);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public void createDatasetRSS(boolean addUniqueValues) throws FenixGWTException {
		try {
			RSSConfigurationVO vo = createRSSConfiguration("00D", "02", "03", "04");
			String rss = rssFactory.createLatestDatasetRSS(vo.getChannelTitle(), vo.getChannelDescription(), vo.getChannelLink(), vo.getImageLink(), urlFinder.fenixWebUrl, urlFinder.xServicesUrl, addUniqueValues);
			rssWriter.writeDatasetRSS(rss, XResourceType.dataset);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public void createTextRSS() throws FenixGWTException {
		try {
			RSSConfigurationVO vo = createRSSConfiguration("00T", "02", "03", "04");
			String rss = rssFactory.createResourceRSS(vo.getChannelTitle(), vo.getChannelDescription(), vo.getChannelLink(), vo.getImageLink(), urlFinder.fenixWebUrl, urlFinder.xServicesUrl, XResourceType.text);
			rssWriter.writeDatasetRSS(rss, XResourceType.text);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public void createRasterRSS() throws FenixGWTException {
		try {
			RSSConfigurationVO vo = createRSSConfiguration("00R", "02", "03", "04");
			String rss = rssFactory.createResourceRSS(vo.getChannelTitle(), vo.getChannelDescription(), vo.getChannelLink(), vo.getImageLink(), urlFinder.fenixWebUrl, urlFinder.xServicesUrl, XResourceType.rasterlayer);
			rssWriter.writeDatasetRSS(rss, XResourceType.rasterlayer);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public void createVectorRSS() throws FenixGWTException {
		try {
			RSSConfigurationVO vo = createRSSConfiguration("00V", "02", "03", "04");
			String rss = rssFactory.createResourceRSS(vo.getChannelTitle(), vo.getChannelDescription(), vo.getChannelLink(), vo.getImageLink(), urlFinder.fenixWebUrl, urlFinder.xServicesUrl, XResourceType.dbfeaturelayer);
			rssWriter.writeDatasetRSS(rss, XResourceType.dbfeaturelayer);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private RSSConfigurationVO createRSSConfiguration(String channelTitleCode, String channelDescriptionCode, String channelLinkCode, String imageLinkCode) throws FenixGWTException {
		RSSConfigurationVO vo = new RSSConfigurationVO();
		try {
			String channelTitle = codecDao.getCodeFromCodeSystemRegion(channelTitleCode, "RSSConfiguration", "0", "EN").getLabel();
			String channelDescription = codecDao.getCodeFromCodeSystemRegion(channelDescriptionCode, "RSSConfiguration", "0", "EN").getLabel();
			String channelLink = codecDao.getCodeFromCodeSystemRegion(channelLinkCode, "RSSConfiguration", "0", "EN").getLabel();
			String imageLink = codecDao.getCodeFromCodeSystemRegion(imageLinkCode, "RSSConfiguration", "0", "EN").getLabel();
			vo.setChannelDescription(channelDescription);
			vo.setChannelLink(channelLink);
			vo.setChannelTitle(channelTitle);
			vo.setImageLink(imageLink);
			return vo;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Map<String, String> findAllNodes() throws FenixGWTException {
		try {
			return xDao.findAllURLs(urlFinder.xServicesUrl);
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private XResourceVO xrsrc2vo(XResource x, String language) throws FenixGWTException {
		XResourceVO vo = new XResourceVO();
		try {
			vo.setTitle(x.getTitle());
			vo.setResourceType(x.getResourceType());
			vo.setKeywords(vo.getKeywords());
			vo.setCategories(x.getCategories());
			vo.setUrl(x.getUrl());
			vo.setUrlLabel(x.getUrlLabel());
			vo.setSource(x.getSource().getTitle());
			vo.setDateLastUpdate(x.getDateLastUpdate());
			vo.setLocalID(x.getLocalID());
			vo.setResourceId(x.getResourceId());
			vo.setAbstractAbstract(x.getAbstractAbstract());
			vo.setEndDate(x.getEndDate());
			vo.setKeywords(x.getKeywords());
			vo.setStartDate(x.getStartDate());
			vo.setProvider(x.getProvider().getTitle());
			vo.setDatasetType(x.getDatasetType());
			vo.setPeriodTypeCode(x.getPeriodTypeCode());
			vo.setResourceType(x.getResourceType());
			if (gaulMap.keySet().contains(x.getRegion())) {
				vo.setRegion(gaulMap.get(x.getRegion()));
			} else {
				String label = codecDao.getLabelFromCodeCodingSystem(x.getRegion(), "GAUL0", "0", language);
				vo.setRegion(label);
				gaulMap.put(x.getRegion(), label);
			}
			if (categoryMap.keySet().contains(x.getCategories())) {
				vo.setCategories(categoryMap.get(x.getCategories()));
			} else {
				String label = codecDao.getLabelFromCodeCodingSystem(x.getCategories(), "Categories", "0", language);
				vo.setCategories(label);
				categoryMap.put(x.getCategories(), label);
			}
			return vo;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private XExplorerSearchParameters vo2par(XExplorerSearchParametersVO vo) throws FenixGWTException {
		XExplorerSearchParameters p = new XExplorerSearchParameters();
		try {
			p.setCategory(vo.getCategory());
			p.setGeographicArea(vo.getGeographicArea());
			p.setKeywords(vo.getKeywords());
			p.setOrderBy(vo.getOrderBy());
			p.setOrderDirection(vo.getOrderDirection());
			p.setResourceType(vo.getResourceType());
			p.setTitle(vo.getTitle());
			p.setValues(vo.getValues());
			p.setFirstResultIndex(vo.getFirstResultIndex());
			p.setMaxResults(vo.getMaxResults());
			p.setMaxItemsPerPage(vo.getMaxItemsPerPage());
			for (String url : vo.getUrls())
				p.addUrl(url);
			return p;
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}

	public void setRssFactory(RSSFactory rssFactory) {
		this.rssFactory = rssFactory;
	}

	public void setRssWriter(RSSWriter rssWriter) {
		this.rssWriter = rssWriter;
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	public void setxDao(XDao xDao) {
		this.xDao = xDao;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setxImporter(XImporter xImporter) {
		this.xImporter = xImporter;
	}

	public void setDatasetImporter(DatasetImporter datasetImporter) {
		this.datasetImporter = datasetImporter;
	}

	public void setDcmtImporter(DcmtImporter dcmtImporter) {
		this.dcmtImporter = dcmtImporter;
	}

	public void setxLayerUtils(XLayerUtils xLayerUtils) {
		this.xLayerUtils = xLayerUtils;
	}

	public void setShapeFilePersister(ShapeFilePersister shapeFilePersister) {
		this.shapeFilePersister = shapeFilePersister;
	}

	public void setTextImporter(TextImporter textImporter) {
		this.textImporter = textImporter;
	}

	public void setShpFeatureLayerPersister(ShpFeatureLayerPersister shpFeatureLayerPersister) {
		this.shpFeatureLayerPersister = shpFeatureLayerPersister;
	}

	public void setRasterLayerPersister(RasterLayerPersister rasterLayerPersister) {
		this.rasterLayerPersister = rasterLayerPersister;
	}

	public void setPOP_HOST(String pOPHOST) {
		POP_HOST = pOPHOST;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public void setEmailReceiver(EmailReceiver emailReceiver) {
		this.emailReceiver = emailReceiver;
	}
	
}