package org.fao.fenix.web.modules.haiti.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.geoserver.FeatureType;
import org.fao.fenix.core.domain.map.layer.ExternalWMSLayer;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.map.wms.harvest.CapabilitiesFetcher;
import org.fao.fenix.map.wms.harvest.WMSHarvester;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.ec.server.ECRSSParser;
import org.fao.fenix.web.modules.haiti.common.services.HaitiService;
import org.fao.fenix.web.modules.haiti.common.vo.WMSModelData;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.server.util.ClientGeoViewLoader;
import org.fao.geonet.lib.wms.schema.type.WMSCapabilities;
import org.fao.geonet.lib.wms.schema.type.WMSLayer;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class HaitiServiceImpl extends RemoteServiceServlet implements HaitiService {

	private CodecDao codecDao;
	
	private WMSMapProviderDao wmsMapProviderDao;
	
	private UrlFinder urlFinder;
	
	private String dir;
	
	private static final Logger LOGGER = Logger.getLogger(HaitiServiceImpl.class);
	
	public HaitiServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Map<String, String> findAllCountryProvinces(String gaul0code, String language) {
		Map<String, String> map = new TreeMap<String, String>();
		// TODO change "EN" with parameter language when GAUL will support this
		Code country = codecDao.getCodeFromCodeSystemRegion(gaul0code, "GAUL0", "0", "EN");
		map.put(country.getCode(), country.getLabel());
		List<Code> provinces = codecDao.findAllGaul1FromGaul0(gaul0code);
		for (Code c : provinces)
			map.put(c.getCode(), c.getLabel());
//		return map;
		return codecDao.findProvincesWithData(map);
	}
	
	public List<ClientGeoView> getHaitiLayers(String codingSystemTitle, String prefix) {
		List<Code> layers = codecDao.findAllCodesByCSTitleAndPrefix(codingSystemTitle, prefix);
		List<ClientGeoView> clientGeoViews = new ArrayList<ClientGeoView>();
		for (Code layer : layers) {
//			WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByTitle(layer.getLabel());
			WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByCode(layer.getLabel());
			ClientGeoView c = wmsMapProvider2ClientGeoView(dbLayer);
			if ( layer.getDescription().equals("off"))
				c.setHidden(true);
			else
				c.setHidden(false);
			clientGeoViews.add(c);
		}
		return clientGeoViews;
	}
	
	@SuppressWarnings("static-access")
	private ClientGeoView wmsMapProvider2ClientGeoView(WMSMapProvider f) {
		ClientGeoView c = new ClientGeoView();
		c.setBBox(bbox2ClientBBox(f.getBoundingBox()));
		c.setTitle(f.getTitle());
		c.setLayerId(f.getResourceId());
		c.setOpacity(100);
		c.setLayerName(f.getLayerName());
		c.setGetMapUrl(urlFinder.geoserverUrl + "wms?");
		c.setStyleName(f.getDefaultStyle());
		c.setAbstractAbstract(f.getAbstractAbstract());
		c.setSource(f.getSource().getTitle());
		c.setSourceContact(f.getSource().getContact());
		c.setProvider(f.getProvider().getTitle());
		c.setProviderContact(f.getProvider().getContact());
		c.setFenixCode(f.getCode());
		return c;
	}
	
	@SuppressWarnings("unused")
	private ClientGeoView featureType2ClientGeoView(FeatureType f) {
		ClientGeoView c = new ClientGeoView();
		c.setBBox(bbox2ClientBBox(f.getBbox()));
		c.setTitle(f.getTitle());
		c.setLayerId(f.getId());
		c.setOpacity(100);
		c.setLayerName(f.getLayerName());
		c.setGetMapUrl(urlFinder.geoserverUrl + "wms?");
		c.setStyleName(f.getDefaultStyle());
		return c;
	}
	
	private ClientBBox bbox2ClientBBox(BoundingBox b) {
		ClientBBox c = new ClientBBox();
		c.setMaxLat(b.getMaxY());
		c.setMaxLon(b.getMaxX());
		c.setMinLat(b.getMinY());
		c.setMinLon(b.getMinX());
		c.setSrs(b.getSrs());
		return c;
	}
	
	public List<ClientGeoView> getPredefinedMapLayers(String gaul0code, String predefineMapName) throws FenixGWTException {
		List<ClientGeoView> geoViews = new ArrayList<ClientGeoView>();
		List<Long> ids = getPredefinedMapLayerIDs(gaul0code, predefineMapName);
		for(Long layerId: ids) {
    		WMSMapProvider wmsMapProvider = wmsMapProviderDao.findById(layerId);
    		geoViews.add(wmsMapProvider2ClientGeoView(wmsMapProvider));
        }
		return geoViews;
	}
	
	private List<Long> getPredefinedMapLayerIDs(String gaul0code, String predefineMapName) throws FenixGWTException {
		List<Long> ids = new ArrayList<Long>();
		try {
			FileInputStream is = new FileInputStream(new File(dir + File.separator + gaul0code + ".xml"));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(is);
			Element element = document.getDocumentElement();
			NodeList presetNode = element.getElementsByTagName("preset");
			for (int i = 0; i < presetNode.getLength(); i++) {
				Element channelElement = (Element) presetNode.item(i);
				String title = channelElement.getAttribute("title");
				if (title.equalsIgnoreCase(predefineMapName)) {
					NodeList layersNode = element.getElementsByTagName("layers");
					for (int j = 0; j < layersNode.getLength(); j++) {
						Element layersElement = (Element) layersNode.item(j);
						NodeList layerNode = layersElement.getElementsByTagName("layer");
						for (int k = 0; k < layerNode.getLength(); k++) {
							Element layerElement = (Element) layerNode.item(k);
							Long id = Long.parseLong(layerElement.getAttribute("id"));
							ids.add(id);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (SAXException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return ids;
	}
	
	public List<ClientGeoView> loadPredifinedMapList(String gaul0code) throws FenixGWTException {
		List<ClientGeoView> geoViews = new ArrayList<ClientGeoView>();
		try {
			FileInputStream is = new FileInputStream(new File(dir + File.separator + gaul0code + ".xml"));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(is);
			Element element = document.getDocumentElement();
			NodeList presetNode = element.getElementsByTagName("preset");
			for (int i = 0; i < presetNode.getLength(); i++) {
				Element channelElement = (Element) presetNode.item(i);
				String title = channelElement.getAttribute("title");
				ClientGeoView cgv = new ClientGeoView();
				cgv.setTitle(title);
				geoViews.add(cgv);
			}
		} catch (FileNotFoundException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (SAXException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return geoViews;
	}
	
	public Map<String, List<String>> getLatestNews(Map<String, String> sourceUrlMap, String gaulCode, int entries) {
		Map<String, List<String>> sourceNewsMap = new HashMap<String, List<String>>();
		ECRSSParser p = new ECRSSParser();
		for (String source : sourceUrlMap.keySet()) {
			List<String> news = p.getLatestNews(sourceUrlMap.get(source), gaulCode, "haiti", entries);
			sourceNewsMap.put(source, news);
		}
		return sourceNewsMap;
	}
	
	public List<String> getLatestNews(List<String> urls, String gaulCode, int entries) {
		ECRSSParser p = new ECRSSParser();
		return p.getLatestNews(urls, gaulCode, "haiti", entries);
	}
	
	public List<WMSModelData> findAllWMSServers() {
		List<WMSModelData> servers = new ArrayList<WMSModelData>();
		List<Code> codes = codecDao.findAllCodes("WMSServers");
		for (Code c : codes) {
			WMSModelData m = new WMSModelData(c.getLabel(), c.getCode(), Boolean.parseBoolean(c.getDescription()));
			servers.add(m);
		}
		return servers;
	}
	
	public Map<Integer, List<String>> parseFeatureInfoHTML(String featureInfoHTML, String fenixCode) throws FenixGWTException {
		if (fenixCode == null) {
			return formatTableFromHTML(featureInfoHTML);
		} else {
			try {
				return formatTableFromXML(featureInfoHTML, fenixCode);
			} catch (FenixException e) {
				LOGGER.info("No any file is associated with code [" + fenixCode + "], table headers will not be formatted");
				return formatTableFromHTML(featureInfoHTML);
			}
		}
	}
	
	private Map<Integer, List<String>> formatTableFromHTML(String featureInfoHTML) {
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		List<String> headers = parseHeaders(featureInfoHTML);
		List<String> values = parseContents(featureInfoHTML); 
		map.put(0, headers);
		map.put(1, values);
		return map;
	}
	
	private Map<Integer, List<String>> formatTableFromXML(String featureInfoHTML, String fenixCode) {
		
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		
		Map<String, String> headers = parseHeadersFromXML(fenixCode);
		List<String> headerLabels = new ArrayList<String>();
		for (String label : headers.values())
			headerLabels.add(label);
		map.put(0, headerLabels);
		
		List<Integer> indices = parseHeaderIndicesFromXML(featureInfoHTML, headers);
		
		Map<String, String> values = parseAllowedValues(featureInfoHTML, indices);
		List<String> valueLabels = new ArrayList<String>();
		for (String label : values.values())
			valueLabels.add(label);
		map.put(1, valueLabels);
		
		LOGGER.info(map);
		
		return map;
	}
	
	private List<Integer> parseHeaderIndicesFromXML(String html, Map<String, String> headers) {
		List<Integer> indices = new ArrayList<Integer>();
		DOMParser parser = new DOMParser();
		try {
			parser.parse(new InputSource(new java.io.StringReader(html)));
			Document doc = parser.getDocument();
			Element element = doc.getDocumentElement();
			NodeList rowNode = element.getElementsByTagName("tr");
			for (int i = 0 ; i < rowNode.getLength() ; i++) {
				Element rowElement = (Element) rowNode.item(i);
				NodeList thNode = rowElement.getElementsByTagName("th");
				for (int j = 0 ; j < thNode.getLength() ; j++) {
					String header = thNode.item(j).getTextContent();
					if (headers.keySet().contains(header))
						indices.add(j);
				}
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indices;
	}
	
	private Map<String, String> parseAllowedValues(String html, List<Integer> allowedIndices) {
		Map<String, String> contents = new HashMap<String, String>();
		List<String> headers = parseHeaders(html);
		DOMParser parser = new DOMParser();
		try {
			parser.parse(new InputSource(new java.io.StringReader(html)));
			Document doc = parser.getDocument();
			Element element = doc.getDocumentElement();
			NodeList rowNode = element.getElementsByTagName("tr");
			for (int i = 0 ; i < rowNode.getLength() ; i++) {
				Element rowElement = (Element) rowNode.item(i);
				NodeList tdNode = rowElement.getElementsByTagName("td");
				for (int j = 0 ; j < tdNode.getLength() ; j++) {
					String content = tdNode.item(j).getTextContent();
					if (allowedIndices.contains(j))
						contents.put(headers.get(j), content);
				}
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contents;
	}
	
	private Map<String, String> parseHeadersFromXML(String fenixCode) throws FenixException {
		Map<String, String> headers = new HashMap<String, String>();
		try {
			FileInputStream xml = new FileInputStream(new File(dir + File.separator + fenixCode + ".xml"));	
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(xml);
			Element element = document.getDocumentElement();
			NodeList headerNode = element.getElementsByTagName("header");
			for (int i = 0 ; i < headerNode.getLength() ; i++) {
				Element headerElement = (Element) headerNode.item(i);
				String original = headerElement.getAttribute("original");
				boolean isVisible = Boolean.parseBoolean(headerElement.getAttribute("isVisible"));
				String label = headerElement.getAttribute("label");
//				LOGGER.info(original + " - " + isVisible + " : " + label);
				if (isVisible)
					headers.put(original, label);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
		return headers;
	}
	
	private List<String> parseHeaders(String html) {
		List<String> headers = new ArrayList<String>();
		DOMParser parser = new DOMParser();
		try {
			parser.parse(new InputSource(new java.io.StringReader(html)));
			Document doc = parser.getDocument();
			Element element = doc.getDocumentElement();
			NodeList rowNode = element.getElementsByTagName("tr");
			for (int i = 0 ; i < rowNode.getLength() ; i++) {
				Element rowElement = (Element) rowNode.item(i);
				NodeList thNode = rowElement.getElementsByTagName("th");
				for (int j = 0 ; j < thNode.getLength() ; j++) {
					String header = thNode.item(j).getTextContent();
					headers.add(header);
				}
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return headers;
	}
	
	private List<String> parseContents(String html) {
		List<String> contents = new ArrayList<String>();
		DOMParser parser = new DOMParser();
		try {
			parser.parse(new InputSource(new java.io.StringReader(html)));
			Document doc = parser.getDocument();
			Element element = doc.getDocumentElement();
			NodeList rowNode = element.getElementsByTagName("tr");
			for (int i = 0 ; i < rowNode.getLength() ; i++) {
				Element rowElement = (Element) rowNode.item(i);
				NodeList tdNode = rowElement.getElementsByTagName("td");
				for (int j = 0 ; j < tdNode.getLength() ; j++) {
					String content = tdNode.item(j).getTextContent();
					contents.add(content);
				}
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contents;
	}

	public List<ClientGeoView> getLayers(String url, Boolean isInternalLayer, ClientBBox countryBBox, Boolean checkBBox) {
		List<ClientGeoView> clientGeoViews = new ArrayList<ClientGeoView>();
		WMSCapabilities capabilities;
		ClientGeoView c = new ClientGeoView();
		try {
			capabilities = CapabilitiesFetcher.fetchCapabilities(url);
			List<WMSLayer> layerList = WMSHarvester.getLayers(capabilities);
			for(WMSLayer layer: layerList) {
//				LOGGER.info(layer.getTitle());
				/**TODO: 
				 * check if is an internal or external wms and if is internal get the layer from db
				 * **/
				if ( !isInternalLayer) {
					c = getExternalLayer(layer, url);					
				}
				if ( isInternalLayer ) {
					WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByTitle(layer.getTitle());
					if (dbLayer != null) {
//						LOGGER.info("isInternalLayer");
						c = wmsMapProvider2ClientGeoView(dbLayer);
					}
					else {
						c = getExternalLayer(layer, url);
					}
				}
				
				clientGeoViews.add(c);

				
			}
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return clientGeoViews;
	}
	
	private ClientGeoView getExternalLayer(WMSLayer layer, String url) {
//		LOGGER.info("getExternalLayer()");
		ExternalWMSLayer ext = WMSHarvester.buildLayer(layer, url, "image/png");

		ClientGeoView c = ClientGeoViewLoader.buildClientResource(ext);
		if(layer.getStyleSize() > 0) {
			try { 
				if ( layer.getStyle(0).getLegendURL(0) != null)
					c.setLagendUrl(layer.getStyle(0).getLegendURL(0).getOnlineResource().getHref());
//				else
//					LOGGER.info("There is no legend");
			} 
			catch (Exception e) {
			}				
		}
		return c;
	}

	public List<CodeVo> getCropCalendarProvinces(String gaulCode, String langCode) {
		List<CodeVo> codesVo = new ArrayList<CodeVo>();
		List<Code> codes = codecDao.findAllGaul1FromGaul0(gaulCode, langCode);
		for (Code c : codes) 
			codesVo.add(new CodeVo(c.getCode(), c.getLabel()));					
		return codesVo;
	}
	
	
	
	public TreeMap<String, List<CodeVo>> getMapsGallery(String countryCode, String language) throws FenixGWTException {
		TreeMap<String, List<CodeVo>> categories = new TreeMap<String, List<CodeVo>>();
		List<CodeVo> files = new ArrayList<CodeVo>();
		try {
			FileInputStream is = new FileInputStream(new File(dir + File.separator + countryCode + "_maps_gallery" + File.separator +"maps_gallery_" + language.toUpperCase() + ".xml"));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(is);
			Element element = document.getDocumentElement();
			NodeList categoryNode = element.getElementsByTagName("category");
			for (int i = 0; i < categoryNode.getLength(); i++) {
				files = new ArrayList<CodeVo>();
				Element catergoryElement = (Element) categoryNode.item(i);
				String catergory = catergoryElement.getAttribute("category");
				NodeList pdfNode = catergoryElement.getElementsByTagName("pdf");			
				for (int j = 0; j < pdfNode.getLength(); j++) {
					Element pdfElement = (Element) pdfNode.item(j);
					String file = pdfElement.getAttribute("file");
					String label = pdfElement.getAttribute("label");
					files.add(new CodeVo(file, label));
				}
				categories.put(catergory, files);
			}
			
		} catch (FileNotFoundException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (SAXException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return categories;
	}


	
	
	

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}
	
	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}
	
	
	
}