package org.fao.fenix.web.modules.re.server;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.Resource;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.coding.FenixGaul0;
import org.fao.fenix.core.domain.communication.CommunicationResource;
import org.fao.fenix.core.domain.constants.ResourceType;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.image.FenixImage;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.geoserver.Coverage;
import org.fao.fenix.core.domain.map.geoserver.FeatureType;
import org.fao.fenix.core.domain.map.layer.ExternalWMSLayer;
import org.fao.fenix.core.domain.map.layer.FeatureLayer;
import org.fao.fenix.core.domain.map.layer.InternalWMSLayer;
import org.fao.fenix.core.domain.map.layer.RasterLayer;
import org.fao.fenix.core.domain.map.projecteddata.ProjectedData;
import org.fao.fenix.core.domain.perspective.ChartView;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.domain.perspective.ReportView;
import org.fao.fenix.core.domain.perspective.SelectedField;
import org.fao.fenix.core.domain.perspective.TableView;
import org.fao.fenix.core.domain.perspective.TextView;
import org.fao.fenix.core.domain.project.Project;
import org.fao.fenix.core.domain.project.ProjectObject;
import org.fao.fenix.core.domain.search.SearchParameters;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.FindDao;
import org.fao.fenix.core.persistence.MetadataDao;
import org.fao.fenix.core.persistence.ResourceDao;
import org.fao.fenix.core.persistence.UniqueValuesDao;
import org.fao.fenix.core.persistence.UpdateDao;
import org.fao.fenix.core.persistence.coding.FenixGaulDao;
import org.fao.fenix.core.persistence.communication.CommunicationDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.perspective.ChartDao;
import org.fao.fenix.core.persistence.perspective.MapDao;
import org.fao.fenix.core.persistence.perspective.ProjectDao;
import org.fao.fenix.core.persistence.perspective.ReportDao;
import org.fao.fenix.core.persistence.perspective.TableDao;
import org.fao.fenix.core.persistence.perspective.TextDao;
import org.fao.fenix.core.persistence.search.SearchDao;
import org.fao.fenix.core.utils.DcmtImporter;
import org.fao.fenix.core.utils.PropertiesReader;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.server.utils.CodingVoConverter;
import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixGaulVo;
import org.fao.fenix.web.modules.core.common.vo.FenixMenuItemVo;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceMetadataVo;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.common.vo.GAULCountryList;
import org.fao.fenix.web.modules.core.server.utils.FenixResourceBuilder;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.re.common.services.REService;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceParentModel;
import org.springframework.security.context.SecurityContextHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class REServiceImpl extends RemoteServiceServlet implements REService {

	private static final long serialVersionUID = 351250855790793787L;

	private DatasetDao datasetDao;
	
	private ProjectDao projectDao;
	
	private ChartDao chartDao;
	
	private ReportDao reportDao;
	
	private MapDao mapDao;
	
	private TableDao tableDao;
	
	private WMSMapProviderDao wmsMapProviderDao;
	
	private TextDao textDao;
	
	private MetadataDao metadataDao;
	
	private SearchDao searchDao;
	
	private Domain2VoConverter domain2VoConverter;
	
	private CommunicationDao communicationDao;
	
	private FenixGaulDao fenixGaulDao;
	
	private DcmtImporter dcmtImporter;
	
	private CodecDao codecDao;
	
	private ResourceDao resourceDao;
	
	private UniqueValuesDao uniqueValuesDao;
	
	private UpdateDao updateDao;
	
	private FindDao findDao;
	
	private String dir;
	
	private UrlFinder urlFinder;
	
	private PropertiesReader propertiesReader;
	
	private final static Logger LOGGER = Logger.getLogger(REServiceImpl.class);
	
	public REServiceImpl(org.springframework.core.io.Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Map<String, String> getNodeSettings() throws FenixGWTException {
		Map<String, String> map = new HashMap<String, String>();
		try {
			map.put("BANNER_LEFT", propertiesReader.read("nodeSettings.properties", "BANNER_LEFT"));
			map.put("BANNER_RIGHT", propertiesReader.read("nodeSettings.properties", "BANNER_RIGHT"));
			map.put("BANNER_BACKGROUND_COLOR", propertiesReader.read("nodeSettings.properties", "BANNER_BACKGROUND_COLOR"));
			map.put("WELCOME_SCREEN_URL", propertiesReader.read("nodeSettings.properties", "WELCOME_SCREEN_URL"));
			String welcomeScreenText = getIntroText(map.get("WELCOME_SCREEN_URL"));
			map.put("WELCOME_SCREEN_TEXT", welcomeScreenText);
			map.put("WELCOME_SCREEN_BACKGROUND_COLOR_ONE", propertiesReader.read("nodeSettings.properties", "WELCOME_SCREEN_BACKGROUND_COLOR_ONE"));
			map.put("WELCOME_SCREEN_BACKGROUND_COLOR_TWO", propertiesReader.read("nodeSettings.properties", "WELCOME_SCREEN_BACKGROUND_COLOR_TWO"));
		} catch (FenixException e) {
			throw new FenixException(e.getMessage());
		}
		return map;
	}
	
	public String getIntroText() throws FenixGWTException {
		try {
			StringBuilder sb = new StringBuilder();
			FileInputStream fin =  new FileInputStream(dir + File.separator + "SplashWindowText.html");
	        BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
	        String line = "";
	        while ((line = myInput.readLine()) != null) {  
	        	sb.append(line);
	        }
			return sb.toString();
		} catch (Exception e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	public String getIntroText(String filename) throws FenixGWTException {
		try {
			LOGGER.info("Properties @ " + filename);
			StringBuilder sb = new StringBuilder();
			FileInputStream fin =  new FileInputStream(dir + File.separator + filename);
	        BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
	        String line = "";
	        while ((line = myInput.readLine()) != null) {  
	        	sb.append(line);
	        }
			return sb.toString();
		} catch (Exception e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	@SuppressWarnings("static-access")
	public String getIMGTag(Long imageID) throws FenixGWTException {
		try {
			FenixImage fenixImage = findDao.findImage(imageID);
			if (fenixImage != null) {
				try {
					String imgName = fenixImage.getResourceId() + ".png";
					String imgPath = dir + File.separator + imgName;
					byte[] bytes = fenixImage.getImage();
					InputStream in = new ByteArrayInputStream(bytes);
					BufferedImage image = ImageIO.read(in);
					File file = new File(imgPath);
					ImageIO.write(image, "png", file);
					String img = "<img src='http://" + urlFinder.serverip + ":" + urlFinder.port + "/fenix-web/exportObject/" + imgName + "'/>";
					return img;
				} catch (IOException e) {
					throw new FenixGWTException(e.getMessage());
				}
			} else {
				throw new FenixGWTException("There is no image with ID " + imageID);
			}
		} catch (Exception e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public List<String> getImages(List<Long> imageIDs) throws FenixGWTException {
		List<String> paths = new ArrayList<String>();
		for (Long id : imageIDs) {
			FenixImage img = findDao.findImage(id);
			if (img != null) {
				try {
					String imgName = UUID.randomUUID() + ".png";
					String imgPath = dir + File.separator + imgName;
					byte[] bytes = img.getImage();
					InputStream in = new ByteArrayInputStream(bytes);
					BufferedImage image = ImageIO.read(in);
					File file = new File(imgPath);
					ImageIO.write(image, "png", file);
					paths.add(imgName);
				} catch (IOException e) {
					throw new FenixGWTException(e.getMessage());
				}
			} else {
				throw new FenixGWTException("There is no image with ID " + id);
			}
		}
		return paths;
	}

	public List<String> getImagesByFolder(String folder){
		List<String> imageList = new ArrayList<String>();
		File file = new File(Setting.getSystemPath() + "/" + folder);
		String[] imagesName = file.list();
		for (int i=0; i<imagesName.length; i++){
			if (!imagesName[i].equals("readme.txt")) imageList.add(imagesName[i]);
		}
		Collections.sort(imageList);
		return imageList;
	}
	
	public List<String> getLastSavedDataset() {
		List<String> result = new ArrayList<String>();
		Dataset dataset = datasetDao.getLastSavedDataset();
		result.add(dataset.getTitle());
		result.add(String.valueOf(dataset.getResourceId()));
		return result;
	}
	
	public List<FenixMenuItemVo> getTools(String xmlFileName, String category) throws FenixGWTException {
		try {
			List<FenixMenuItemVo> items = new ArrayList<FenixMenuItemVo>();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(Setting.XML_FILES + xmlFileName + ".xml");
			Element element = document.getDocumentElement();
			NodeList toolNodeList = element.getElementsByTagName("tool");
			for (int i = 0; i < toolNodeList.getLength(); i++) {
				Element toolElement = (Element) toolNodeList.item(i);
				FenixMenuItemVo toolItem = getToolItem(toolElement);
				if ((category.equalsIgnoreCase("all")) || (toolItem.getCategory().equalsIgnoreCase(category))) {
					items.add(toolItem);
				}
			}
			return items;
		} catch (ParserConfigurationException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (SAXException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private FenixMenuItemVo getToolItem(Element element) {
		FenixMenuItemVo vo = new FenixMenuItemVo();
		String name = "";
		if (element.getAttribute("name") != null) {
			name = element.getAttribute("name");
		}
		vo.setName(name);
		String description = "";
		if (element.getAttribute("description") != null) {
			description = element.getAttribute("description");
		}
		vo.setDescription(description);
		String category = "";
		if (element.getAttribute("category") != null) {
			category = element.getAttribute("category");
		}
		vo.setCategory(category);
		String command = "";
		if (element.getAttribute("command") != null) {
			command = element.getAttribute("command");
		}
		vo.setCommand(command);
		String iconStyle = "";
		if (element.getAttribute("iconStyle") != null) {
			iconStyle = element.getAttribute("iconStyle");
		}
		vo.setIconStyle(iconStyle);
		return vo;
	}
	
	private static FenixMenuItemVo getMenuItem(Element element, int level, String parent, boolean children) {
		// get the value from the XML
		String name = element.getAttribute("name");
		String command = element.getAttribute("command");
		// tooltip
		String tooltip = "";
		String iconStyle = "";
		if (element.getAttribute("tooltip") != null)
			tooltip = element.getAttribute("tooltip");
		if (element.getAttribute("iconStyle") != null)
			iconStyle = element.getAttribute("iconStyle");
		// fill the bean
		FenixMenuItemVo item = new FenixMenuItemVo(name, level, parent, children, tooltip, command, iconStyle);
		// return the bean
		return item;
	}

	public List<FenixMenuItemVo> getMenubarModel(String modelName) {
		List<FenixMenuItemVo> items = new ArrayList<FenixMenuItemVo>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(Setting.XML_FILES + modelName + ".xml");
			Element element = document.getDocumentElement();
			NodeList firstLevel = element.getElementsByTagName("first-level");
			if (firstLevel != null && firstLevel.getLength() > 0) {
				for (int i = 0; i < firstLevel.getLength(); i++) {
					Element firstLevelElement = (Element) firstLevel.item(i);
					FenixMenuItemVo firstLevelItem;
					if (firstLevelElement.hasChildNodes())
						firstLevelItem = getMenuItem(firstLevelElement, 1, "", true);
					else
						firstLevelItem = getMenuItem(firstLevelElement, 1, "", false);
					items.add(firstLevelItem);
					NodeList secondLevel = firstLevelElement.getElementsByTagName("second-level");
					if (secondLevel != null && secondLevel.getLength() > 0) {
						for (int j = 0; j < secondLevel.getLength(); j++) {
							Element secondLevelElement = (Element) secondLevel.item(j);
							FenixMenuItemVo secondLevelItem;
							if (secondLevelElement.hasChildNodes())
								secondLevelItem = getMenuItem(secondLevelElement, 2, firstLevelItem.getName(), true);
							else
								secondLevelItem = getMenuItem(secondLevelElement, 2, firstLevelItem.getName(), false);
							items.add(secondLevelItem);
							NodeList thirdLevel = secondLevelElement.getElementsByTagName("third-level");
							if (thirdLevel != null && thirdLevel.getLength() > 0) {
								for (int k = 0; k < thirdLevel.getLength(); k++) {
									Element thirdLevelElement = (Element) thirdLevel.item(k);
									FenixMenuItemVo thirdLevelItem;
									if (thirdLevelElement.hasChildNodes())
										thirdLevelItem = getMenuItem(thirdLevelElement, 3, secondLevelItem.getName(), true);
									else
										thirdLevelItem = getMenuItem(thirdLevelElement, 3, secondLevelItem.getName(), false);
									items.add(thirdLevelItem);
									NodeList fourthLevel = thirdLevelElement.getElementsByTagName("fourth-level");
									if (fourthLevel != null && fourthLevel.getLength() > 0) {
										for (int m = 0; m < fourthLevel.getLength(); m++) {
											Element fourthLevelElement = (Element) fourthLevel.item(m);
											FenixMenuItemVo fourthLevelItem = getMenuItem(fourthLevelElement, 4, thirdLevelItem.getName(), false);
											items.add(fourthLevelItem);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return items;
	}
	
	public List<FenixResourceVo> getFullList() {

		// initiate result
		List<FenixResourceVo> list = new ArrayList<FenixResourceVo>();

		// retrieve all resources
		for (ChartView chart : chartDao.findAll())
			list.add(FenixResourceBuilder.build(chart));
		for (MapView map : mapDao.findAll())
			list.add(FenixResourceBuilder.build(map));
		for (TableView table : tableDao.findAll())
			list.add(FenixResourceBuilder.build(table));
		for (ReportView report : reportDao.findAll())
			list.add(FenixResourceBuilder.build(report));
		for (TextView text : textDao.findAll())
			list.add(FenixResourceBuilder.build(text));
		for (Project project : projectDao.findAll())
			list.add(FenixResourceBuilder.build(project));
		for (Dataset dataset : datasetDao.findAll())
			list.add(FenixResourceBuilder.build(dataset));
		for (InternalWMSLayer l : wmsMapProviderDao.findAllInternalLayers())
			list.add(FenixResourceBuilder.build(l));
		for (ExternalWMSLayer l : wmsMapProviderDao.findAllExternalLayers())
			list.add(FenixResourceBuilder.build(l));

		// return cut array
		return list;
	}

	public List<FenixResourceVo> getProjectResources(Long id) {
		List<FenixResourceVo> list = new ArrayList<FenixResourceVo>();
		List<FenixResourceVo> tmp = getMapsByProjectId(id);
		for (int i = 0; i < tmp.size(); i++)
			list.add((FenixResourceVo) tmp.get(i));
		tmp = getChartsByProjectId(id);
		for (int i = 0; i < tmp.size(); i++)
			list.add((FenixResourceVo) tmp.get(i));
		tmp = getTablesByProjectId(id);
		for (int i = 0; i < tmp.size(); i++)
			list.add((FenixResourceVo) tmp.get(i));
		tmp = getTextsByProjectId(id);
		for (int i = 0; i < tmp.size(); i++)
			list.add((FenixResourceVo) tmp.get(i));
		tmp = getReportsByProjectId(id);
		for (int i = 0; i < tmp.size(); i++)
			list.add((FenixResourceVo) tmp.get(i));
		return list;
	}

	public List<FenixResourceVo> getMapsByProjectId(long id) {
		List<FenixResourceVo> retList = new ArrayList<FenixResourceVo>();
		for (MapView view : mapDao.findByProject(id)) {
			retList.add(FenixResourceBuilder.build(view));
		}
		return retList;
	}

	public List<FenixResourceVo> getChartsByProjectId(long id) {
		List<FenixResourceVo> retList = new ArrayList<FenixResourceVo>();
		for (ChartView view : chartDao.findByProject(id)) {
			retList.add(FenixResourceBuilder.build(view));
		}
		return retList;
	}

	public List<FenixResourceVo> getTablesByProjectId(long id) {
		List<FenixResourceVo> retList = new ArrayList<FenixResourceVo>();
		for (TableView view : tableDao.findByProject(id)) {
			retList.add(FenixResourceBuilder.build(view));
		}
		return retList;
	}

	public List<FenixResourceVo> getTextsByProjectId(long id) {
		List<FenixResourceVo> retList = new ArrayList<FenixResourceVo>();
		for (TextView view : textDao.findByProject(id)) {
			retList.add(FenixResourceBuilder.build(view));
		}
		return retList;
	}

	public List<FenixResourceVo> getReportsByProjectId(long id) {
		List<FenixResourceVo> retList = new ArrayList<FenixResourceVo>();
		for (ReportView view : reportDao.findByProject(id)) {
			retList.add(FenixResourceBuilder.build(view));
		}
		return retList;
	}

	public String getCatalogueSize(String resourceType) {

		// initiate size
		int size = 0;

		// single resource
		if (resourceType.equals(ResourceType.ChartView.name()))
			size = chartDao.findAll().size();
		if (resourceType.equals(ResourceType.ReportView.name()))
			size = reportDao.findAll().size();
		if (resourceType.equals(ResourceType.Dataset.name()))
			size = datasetDao.findAll().size();
		if (resourceType.equals(ResourceType.MapView.name()))
			size = mapDao.findAll().size();
		if (resourceType.equals(ResourceType.Project.name()))
			size = projectDao.findAll().size();
		if (resourceType.equals(ResourceType.TableView.name()))
			size = tableDao.findAll().size();
		if (resourceType.equals(ResourceType.TextView.name()))
			size = textDao.findAll().size();
		if (resourceType.equals(ResourceType.Layer.name()))
			size = wmsMapProviderDao.findAllExternalLayers().size() + wmsMapProviderDao.findAllInternalLayers().size();
		if (resourceType.equals(ResourceType.INTERNAL_LAYER))
			size = wmsMapProviderDao.findAllInternalLayers().size();
		if (resourceType.equals(ResourceType.EXTERNAL_LAYER))
			size = wmsMapProviderDao.findAllExternalLayers().size();

		// all resource
		/*
		 * if (resourceType.equals(ResourceType.ALL)) { size += chartDao.findAll().size(); size +=
		 * reportDao.findAll().size(); size += datasetDao.findAll().size(); size +=
		 * mapDao.findAll().size(); size += projectDao.findAll().size(); size +=
		 * tableDao.findAll().size(); size += textDao.findAll().size(); size +=
		 * wmsMapProviderDao.findAllExternalLayers().size(); size +=
		 * wmsMapProviderDao.findAllInternalLayers().size(); }
		 */
		// return size
		return String.valueOf(size);
	}

	public void deleteResource(long id, String resourceType) {
		LOGGER.info("deleting: " +  resourceType);
		if (resourceType.equals(ResourceType.ChartView.name())) {
			chartDao.delete(chartDao.findById(id));	
			deleteLinkChart(id);
		} else if (resourceType.equals(ResourceType.CodingSystem.name())) {
			dcmtImporter.deleteClassification(codecDao.getCodingSystemFromId(id));
			uniqueValuesDao.deleteCS(id);		
		} else if (resourceType.equals(ResourceType.ReportView.name())) {
			reportDao.delete(reportDao.findById(id));
		} else if (resourceType.equals(ResourceType.TableView.name())) {
			tableDao.delete(tableDao.findById(id));
		} else if (resourceType.equals(ResourceType.Project.name())) {
			projectDao.delete(projectDao.findById(id));
		} else if (resourceType.equals(ResourceType.TextView.name())) {
			textDao.delete(textDao.findById(id));
		} else if (resourceType.equals(ResourceType.Dataset.name())) {
			datasetDao.delete(datasetDao.findById(id));
			uniqueValuesDao.deleteGhostTables(id);
		} else if (resourceType.equals(ResourceType.MapView.name())) {
			mapDao.delete(mapDao.findById(id));
		}
		else if (resourceType.equals(ResourceType.WMSMapProvider.name())) {
			LOGGER.info("deleting WMSMapProvider: " +  resourceType);
			mapDao.deleteLayer(id);
		}

	}

	public void adminDeletesResource(long id, String resourceType) {
		LOGGER.info("deleting: " +  resourceType);
		if (resourceType.equals(ResourceType.CodingSystem.name())) {
			dcmtImporter.deleteClassification(codecDao.getCodingSystemFromId(id));
			uniqueValuesDao.deleteCS(id);		
		}
		else {
			if (resourceType.equals(ResourceType.Dataset.name())) {
				resourceDao.deleteDataset(datasetDao.findById(id));
				uniqueValuesDao.deleteGhostTables(id);
			} 
			else if (resourceType.equals(ResourceType.ChartView.name())) {
				chartDao.delete(chartDao.findById(id));
				deleteLinkChart(id);
			}
//			else if (resourceType.equals(ResourceType.WMSMapProvider.name())) {
//				LOGGER.info("deleting WMSMapProvider: " +  resourceType);
//				mapDao.deleteLayer(id);
//			}
			else 
				resourceDao.delete(resourceDao.findById(id));
		}
	}

	public Long saveTableView(String title, Long datasetID, List<String> list) {

		TableView tableView = new TableView();
		tableView.setTitle(title);
		// getKeywords() and setKeywords should no longer be used to set the
		// dataset ID, as keywords is a metadata field and in use.
		// The dataset ID accessed as follows
		// tableView.setDataList(datasetDao.findById(datasetID.longValue()));
		tableView.setDatasetId(datasetID.longValue());

		List<SelectedField> selectedFieldList = new ArrayList<SelectedField>();
		for (int i = 1; i < list.size(); i++) {
			SelectedField selectedField = new SelectedField();
			selectedField.setFieldName((String) list.get(i));
			selectedFieldList.add(selectedField);
		}
		tableView.setSelectedFields(selectedFieldList);
		tableDao.save(tableView);

		return tableView.getResourceId();
	}

	public FenixResourceMetadataVo getNewTableViewMetadata(FenixResourceMetadataVo metadata, Long datasetId,
			List<String> columns) {

		// save new table view
		final Long id = saveTableView(metadata.getTitle(), datasetId, columns);

		// update metadata
		metadata.setResourceId(id);

		return metadata;

	}

	public FenixResourceVo createTableFenixResource(Long id) {

		TableView table = tableDao.findById(id);
		FenixResourceVo fenixResource = null;

		if (table != null) {
			fenixResource = FenixResourceBuilder.build(tableDao.findById(id));
		}

		return fenixResource;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public void setMapDao(MapDao mapDao) {
		this.mapDao = mapDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setTextDao(TextDao textDao) {
		this.textDao = textDao;
	}

	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}

	public Integer getSearchResultListLength(FenixSearchParameters fenixSearchParameters) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		SearchParameters p = mapVo2Do(fenixSearchParameters);
		List<Resource> rsrcs = searchDao.searchCached(p, userName);
		Integer size = rsrcs.size(); 
		return size;
	}

	public List<FenixResourceVo> search(FenixSearchParameters fenixSearchParameters, int fromIndex, int toIndex) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Resource> tmp = searchDao.searchCached(mapVo2Do(fenixSearchParameters), userName).subList(fromIndex,toIndex);
		List<String> resourceTypeList = fenixSearchParameters.getResourceTypeList();
//		for (String rsrcType : resourceTypeList)
//			LOGGER.info("RESOURCE TYPE: " + rsrcType);
		List<FenixResourceVo> list = domain2VoConverter.convertDomain2Vo(tmp, userName, resourceTypeList);
		return list;
	}
	
/*	
 public List<FenixResourceVo> search(FenixSearchParameters fenixSearchParameters, int fromIndex, int toIndex) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Resource> tmp = searchDao.searchCachedRevised(mapVo2Do(fenixSearchParameters), userName).subList(fromIndex,
				toIndex);
		System.out.println("-------------- search: tmp size = "+tmp.size());
		List<FenixResourceVo> list = domain2VoConverter.convertDomain2Vo(tmp, userName);
		
		return list;
	}*/

	public List<CommunicationResourceVo> searchCommunication(FenixSearchParameters fenixSearchParameters,
			int fromIndex, int toIndex) {

		List<CommunicationResourceVo> list = new ArrayList<CommunicationResourceVo>();
		List<CommunicationResource> result = new ArrayList<CommunicationResource>();
		for (int z = 0; z < fenixSearchParameters.getResourceTypeList().size(); z++) {
			result = communicationDao.findByTitleAndKeywordAndType(fenixSearchParameters.getTitle(),
					fenixSearchParameters.getKeyword(), fenixSearchParameters.getResourceTypeList().get(z)).subList(
					fromIndex, toIndex);
			for (int i = 0; i < result.size(); i++)
				list.add(communication2vo(result.get(i)));
		}
		return list;
	}

	public CommunicationResourceVo communication2vo(CommunicationResource rsrc) {
		CommunicationResourceVo vo = new CommunicationResourceVo();
		vo.setDigest(rsrc.getDigest());
		vo.setHost(rsrc.getHost());
		vo.setHostLabel(rsrc.getHostLabel());
		vo.setId(rsrc.getResourceId());
		vo.setLocalId(rsrc.getLocalId());
		vo.setOGroup(rsrc.getOGroup());
		vo.setPrivilege(rsrc.getPrivilege());
		vo.setTitle(rsrc.getTitle());
		vo.setType(rsrc.getType());
		return vo;
	}

	private SearchParameters mapVo2Do(FenixSearchParameters fenixSearchParameters) {

		// initiate search parameters
		SearchParameters searchParameters = new SearchParameters();

		searchParameters.setOrderby(fenixSearchParameters.getOrderBy());
		searchParameters.setOrderType(fenixSearchParameters.getOrderType());

		// set keyword
		if (fenixSearchParameters.getKeyword() != null)
			searchParameters.setKeywords(fenixSearchParameters.getKeyword());

		if (fenixSearchParameters.getRegion() != null)
			searchParameters.setRegion(fenixSearchParameters.getRegion());

		if (fenixSearchParameters.getCategories() != null)
			searchParameters.setCategories(fenixSearchParameters.getCategories());

		// set title
		if (fenixSearchParameters.getTitle() != null)
			searchParameters.setTitle(fenixSearchParameters.getTitle());

		// set resource types
		if (fenixSearchParameters.getResourceTypeList() != null) {
			List<String> resourceTypeNameList = fenixSearchParameters.getResourceTypeList();
			List<ResourceType> resourceTypeList = new ArrayList<ResourceType>();
			for (String resourceTypeName : resourceTypeNameList) {
				// if (!clientDomainMap.containsKey(resourceTypeName))
				// throw new FenixException("The resource " + resourceTypeName +
				// " is not a valid Fenix resource");
				ResourceType resourceType = ResourceType.getResourceType(resourceTypeName);
				resourceTypeList.add(resourceType);
			}
			searchParameters.setResourceTypeList(resourceTypeList);
		}
		return searchParameters;
	}

	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}

	/**
	 * Returns the children of the given parent (for project resources ).
	 * 
	 * @param parent
	 *            is the parent resource
	 * @return the children
	 */

	/*
	 * public List<ResourceChildModel> getResourceModel(ResourceChildModel parent,
	 * FenixSearchParameters fenixSearchParameters, int fromIndex, int toIndex) {
	 * List<FenixResourceVo> resources = null; List<ResourceChildModel> models = new
	 * ArrayList<ResourceChildModel>();
	 * 
	 * if (parent == null) { //get root resources resources = search(fenixSearchParameters,
	 * fromIndex, toIndex);//i.e. list of all the projects in FENIX models =
	 * createResourceModel(resources, true); } else { //get child resources for the project
	 * resources = getResourceChildren(Long.valueOf(parent.getId()), parent.getType());
	 * 
	 * if (parent.getType().equals(ResourceType.PROJECT)){ //List of resource types for the project
	 * selected models = createResourceModel(resources, true); } else { models =
	 * createResourceModel(resources, false); //List of resources for the resourceType selected } }
	 * 
	 * return models; }
	 */

	public List<ResourceChildModel> getResourceModel(ResourceChildModel parent,
			FenixSearchParameters fenixSearchParameters, int fromIndex, int toIndex) {
		List<FenixResourceVo> resources = null;
		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();

		if (parent == null) { // get root resources
			resources = search(fenixSearchParameters, fromIndex, toIndex);// i.e.
			// list
			// of
			// all
			// the
			// projects
			// in
			// FENIX
			System.out.println("getResourceModel: ROOT ITEMS");
			models = createResourceModel(resources, true);
		} else { // get child resources for the project
			System.out.println("getResourceModel: CHILD ITEMS");
			resources = getResourceChildren(Long.valueOf(parent.getId()), parent.getType());

			if (parent.getType().equals(ResourceType.Project.name())) { // List of
				// resource
				// types for
				// the
				// project
				// selected
				System.out.println("getResourceModel: CHILD ITEMS: parent is a PROJECT");
				models = createResourceModel(resources, true);
			} else {
				System.out.println("getResourceModel: CHILD ITEMS: parent is NOT a project");
				models = createResourceModel(resources, false); // List of
				// resources for
				// the
				// resourceType
				// selected
			}
		}

		if (models != null)
			System.out.println("getResourceModel: RETURNED MODELS size = " + models.size());
		else
			System.out.println("getResourceModel: RETURNED MODELS = NULL");

		return models;
	}

	public List<ResourceChildModel> getResourceModel2(ResourceChildModel parent, List<FenixResourceVo> resources) {
		List<FenixResourceVo> resources2 = null;
		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();

		if (parent == null) { // get root resources
			resources2 = resources;
			System.out.println("getResourceModel2: ROOT ITEMS");
			models = createResourceModel(resources, true);
		} else { // get child resources for the project
			System.out.println("getResourceModel2: CHILD ITEMS");
			resources2 = getResourceChildren(Long.valueOf(parent.getId()), parent.getType());

			if (parent.getType().equals(ResourceType.Project.name())) { // List of
				// resource
				// types for
				// the
				// project
				// selected
				System.out.println("getResourceModel2: CHILD ITEMS: parent is a PROJECT");
				models = createResourceModel(resources2, true);

				System.out.println("&&&&&&&&&&&&&&&&&& models1 size =  " + models.size());
			} else {
				System.out.println("getResourceModel2: CHILD ITEMS: parent is NOT a project");
				models = createResourceModel(resources2, false); // List of

				System.out.println("&&&&&&&&&&&&&&&&&& models2 size =  " + models.size());
				// resources
				// for the
				// resourceType
				// selected
			}
		}

		if (models != null)
			System.out.println("getResourceModel2: RETURNED MODELS size = " + models.size());
		else
			System.out.println("getResourceModel2: RETURNED MODELS = NULL");

		return models;
	}

	public List<ResourceChildModel> getResourceModelCommunication2(ResourceChildModel parent,
			List<CommunicationResourceVo> resources) {
		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
		models = createCommunicationResourceModel(resources, true);
		if (models != null)
			System.out.println("getResourceModel2: RETURNED MODELS size = " + models.size());
		else
			System.out.println("getResourceModel2: RETURNED MODELS = NULL");
		return models;
	}

	public List<ResourceChildModel> createCommunicationResourceModel(List<CommunicationResourceVo> resources,
			boolean isParent) {
		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
		for (CommunicationResourceVo r : resources) {
			ResourceChildModel reModel = null;
			if (isParent)
				reModel = new ResourceParentModel(r.getTitle(), r.getType());
			else
				reModel = new ResourceChildModel(r.getTitle());
			reModel.set("dateModified", "DATE MODIFIED");
			reModel.set("region", r.getOGroup());
			reModel.set("category", "CATEGORY");
			reModel.setId(String.valueOf(r.getId())); // set ID
			reModel.setType(r.getType()); // set Resource Type
			reModel.setDigest(r.getDigest());
			reModel.setHost(r.getHost());
			reModel.setHostLabel(r.getHostLabel());
			reModel.setLocalId(r.getLocalId());
			models.add(reModel);
		}
		return models;
	}

	public List<ResourceChildModel> createResourceModel(List<FenixResourceVo> resources, boolean isParent) {
		System.out.println("&&&&&&&&&&&&&&&&&& createResourceModel START ");

		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
		for (FenixResourceVo r : resources) {
			ResourceChildModel reModel = null;
			if (isParent) {
				reModel = new ResourceParentModel(r.getTitle(), r.getType());
			} else {
				reModel = new ResourceChildModel(r.getTitle());
			}

			System.out.println("&&&&&&&&&&&&&&&&&& DATE LAST UPDATED BEFORE");

			reModel.set("dateModified", r.getLastUpdated());

			System.out.println("&&&&&&&&&&&&&&&&&& DATE LAST UPDATED AFTER");

			// reModel.set("region", r.getRegion());
			GAULCountryList countryList = new GAULCountryList();

			System.out
					.println("&&&&&&&&&&&&&&&&&& BEFORE GAUL LABEL SET FOR REGION countryList.getGaulCountryMap().size() = "
							+ countryList.getGaulCountryMap().size());

			if (countryList.getGaulCountryMap().size() > 0) {
				reModel.set("region", countryList.getGaulCountryMap().get(r.getRegion()));
				// if(countryList!=null && countryList.getGAULCountryLabel(r.getRegion())!=null){
				// reModel.set("region", countryList.getGAULCountryLabel(r.getRegion()));
			} else {
				List<FenixGaulVo> countryGaulList = getCountryList();

				for (int i = 0; i < countryGaulList.size(); i++) {
					String countryName = countryGaulList.get(i).getName();
					String gaulCode = Long.toString(countryGaulList.get(i).getCode());

					System.out.println("country name = " + countryName + " gaul = " + gaulCode);

					if (r.getRegion().equals(gaulCode)) {
						reModel.set("region", countryName);
					}
				}
			}

			// reModel.set("region", r.getRegion());

			System.out.println("&&&&&&&&&&&&&&&&&& AFTER GAUL LABEL SET ");

			reModel.set("category", codecDao.getLabelFromCodeCodingSystem(r.getCategory(), "Categories", "0", "EN"));

			
			reModel.setId(r.getId()); // set ID
			reModel.setType(r.getType()); // set Resource Type
			if(r.getPeriodTypeCode()!=null)
				reModel.setPeriodTypeCode(r.getPeriodTypeCode()); // periodicity
			reModel.setWritePermission(r.isHasWritePermission());
			reModel.setDeletePermission(r.isHasDeletePermission());
			reModel.setDownloadPermission(r.isHasDownloadPermission());
			reModel.setFlexDatasetType(r.isDatasetFlex());

			models.add(reModel);

		}
		System.out.println("&&&&&&&&&&&&&&&&&& createResourceModel END ");

		return models;
	}

	public List<FenixResourceVo> getResourceChildren(Long resourceId, String resourceType) {

		List<FenixResourceVo> fenixResourceList = new ArrayList<FenixResourceVo>();
		Project project = projectDao.findById(resourceId.longValue());

		if (project != null) {
			System.out.println("project NOT null: resourceType = " + resourceType);

			List<ProjectObject> pol = project.getProjectObjectList();

			if (pol != null) {
				System.out.println("project object list IS NOT NULL");

				LinkedHashMap<String, String> resourceTypeMap = new LinkedHashMap<String, String>();
				List<Resource> resourceList = new ArrayList<Resource>();

				for (ProjectObject projectObject : pol) {
					Resource rsrc = (Resource) projectObject;
					System.out.println("rsrc = " + rsrc);
					resourceList.add(rsrc);
					resourceTypeMap.put(rsrc.getClass().getSimpleName(), rsrc.getClass().getSimpleName());
				}

				System.out.println("resourceTypeMap size = " + resourceTypeMap.size());

				if (resourceType.equals(ResourceType.Project.name())) {

					List<FenixResourceVo> resourceTypeList = new ArrayList<FenixResourceVo>();

					Collection<String> c = resourceTypeMap.values();
					Iterator<String> itr = c.iterator();

					while (itr.hasNext()) {
						String type = itr.next();
						System.out.println("type = " + type);
						FenixResourceVo fr = new FenixResourceVo();
						fr.setTitle(type);
						fr.setId(resourceId.toString()); // parent project ID
						fr.setCategory(null);
						fr.setRegion(null);
						fr.setLastUpdated(null);
						fr.setType(type);
						fr.setPeriodTypeCode(null);
						// FIXME look FenixResourceVo about security
						// fr.setModifiable(false);
						resourceTypeList.add(fr);
					}

					fenixResourceList = resourceTypeList;

					System.out.println("resourceTypeList size = " + resourceTypeList.size());

				} else {
					List<Resource> typeRelatedResourceList = new ArrayList<Resource>();

					for (Resource resource : resourceList) {
						if (resource.getClass().getSimpleName().equals(resourceType)) {
							typeRelatedResourceList.add(resource);
						}
					}
					String userName = SecurityContextHolder.getContext().getAuthentication().getName();
					List<String> resourceTypeList = new ArrayList<String>();
					resourceTypeList.add(resourceType);
					fenixResourceList = domain2VoConverter.convertDomain2Vo(typeRelatedResourceList, userName, resourceTypeList);
				}
			}
		} else {
			System.out.println("project object list IS NULL");

		}

		return fenixResourceList;
	}

	public Integer getCommunicationResourceSize(FenixSearchParameters fenixSearchParameters) {
		Integer size = 0;
		for (int i = 0; i < fenixSearchParameters.getResourceTypeList().size(); i++)
			size += communicationDao.getCommunicationResourceSize(fenixSearchParameters.getResourceTypeList().get(i));
		System.out.println(">>>>>>>>>> COMMUNICATION SIZE " + size);
		return size;
	}

	public void setDomain2VoConverter(Domain2VoConverter domain2VoConverter) {
		this.domain2VoConverter = domain2VoConverter;
	}

	public void setCommunicationDao(CommunicationDao communicationDao) {
		this.communicationDao = communicationDao;
	}

	public void setFenixGaulDao(FenixGaulDao fenixGaulDao) {
		this.fenixGaulDao = fenixGaulDao;
	}

	public void setDcmtImporter(DcmtImporter dcmtImporter) {
		this.dcmtImporter = dcmtImporter;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public String getApplicationName() {
		return Setting.getApplicationName();
	}

	private List<FenixGaulVo> getCountryList() {
		System.out.println("getCountryList: HERE !!!!!!!!!!!!!!!!!!!!");
		List<FenixGaul0> list = fenixGaulDao.findAllGaul0();
		System.out.println("getCountryList: list size = " + list.size());

		List<String> countries = new ArrayList<String>();
		List<FenixGaulVo> result = new ArrayList<FenixGaulVo>();
		for (int i = 0; i < list.size(); i++) {
			if (!countries.contains(list.get(i).getName())) {
				countries.add(list.get(i).getName());
				result.add(mapFenixGaul0(list.get(i), "NATION"));
			}
		}

		return result;
	}

	private FenixGaulVo mapFenixGaul0(FenixGaul0 gaul, String level) {
		FenixGaulVo vo = new FenixGaulVo();
		vo.setCode(gaul.getCode());
		vo.setContinent(gaul.getContinent());
		vo.setName(gaul.getName());
		vo.setRegion(gaul.getRegion());
		vo.setLevel(level);
		return vo;
	}
	
	
	public List<CodeVo> getCategories() {
		/** TODO: for multilanguage use also different langCode for the research **/ 
		List<Code> codes = codecDao.getCodesOfaSystemLangCodeSorted("Categories", "0", "EN");
		List<CodeVo> result = new ArrayList<CodeVo>();
		for(Code code : codes) {
			result.add(CodingVoConverter.code2Vo(code));
		}
		return result;
		
	}
	
	public List<ResourceChildModel> createResourceModelTsest(List<FenixResourceVo> resources, boolean isParent) {
		System.out.println("&&&&&&&&&&&&&&&&&& createResourceModel START ");

		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
		for (FenixResourceVo r : resources) {
			
			ResourceChildModel reModel = null;
			if (isParent) {
				reModel = new ResourceParentModel(r.getTitle(), r.getType());
			} else {
				reModel = new ResourceChildModel(r.getTitle());
			}

			System.out.println("&&&&&&&&&&&&&&&&&& DATE LAST UPDATED BEFORE");

			reModel.set("dateModified", r.getLastUpdated());

			System.out.println("&&&&&&&&&&&&&&&&&& DATE LAST UPDATED AFTER");

			// reModel.set("region", r.getRegion());
			GAULCountryList countryList = new GAULCountryList();

			System.out
					.println("&&&&&&&&&&&&&&&&&& BEFORE GAUL LABEL SET FOR REGION countryList.getGaulCountryMap().size() = "
							+ countryList.getGaulCountryMap().size());

		/*	if (countryList.getGaulCountryMap().size() > 0) {
				reModel.set("region", countryList.getGaulCountryMap().get(r.getRegion()));
				
				// if(countryList!=null && countryList.getGAULCountryLabel(r.getRegion())!=null){
				// reModel.set("region", countryList.getGAULCountryLabel(r.getRegion()));
			} else {
				List<FenixGaulVo> countryGaulList = getCountryList();

				for (int i = 0; i < countryGaulList.size(); i++) {
					String countryName = countryGaulList.get(i).getName();
					String gaulCode = Long.toString(countryGaulList.get(i).getCode());

					System.out.println("country name = " + countryName + " gaul = " + gaulCode);

					if (r.getRegion().equals(gaulCode)) {
						reModel.set("region", countryName);
					}
				}
			
			}*/
			
			
			reModel.set("region", codecDao.getLabelFromCodeCodingSystem(r.getRegion(), "GAUL", "0", "EN"));
			// reModel.set("region", r.getRegion());

			System.out.println("&&&&&&&&&&&&&&&&&& AFTER GAUL LABEL SET ");

			reModel.set("category", codecDao.getLabelFromCodeCodingSystem(r.getCategory(), "Categories", "0", "EN"));

			
			reModel.setId(r.getId()); // set ID
			reModel.setType(r.getType()); // set Resource Type
			if(r.getPeriodTypeCode()!=null)
				reModel.setPeriodTypeCode(r.getPeriodTypeCode()); // periodicity
			reModel.setWritePermission(r.isHasWritePermission());
			reModel.setDeletePermission(r.isHasDeletePermission());
			reModel.setDownloadPermission(r.isHasDownloadPermission());
			reModel.setFlexDatasetType(r.isDatasetFlex());

			models.add(reModel);

		}
		System.out.println("&&&&&&&&&&&&&&&&&& createResourceModel END ");

		return models;
	}
	
	
		
	public CodeVo getCategoryLabel(String categoryCode) {
		System.out.println("entering cate ");
		List<Code> codes = codecDao.getCodeFromCodeLangSystemRegion(categoryCode, "EN", "Category", "0");	
		CodeVo result = CodingVoConverter.code2Vo(codes.get(0));
		return result;
	}
	
	private void deleteLinkChart(Long chartID) {
		String fileName = chartID.toString();
		fileName += ".rptdesign";
        File file = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "report" + File.separator + "links" + File.separator + fileName);        
        boolean success = file.delete();

        if (!success)
          throw new IllegalArgumentException("Delete: failed");
	}
	
	
	/** depending to which resource is selected are throwed differently checks on
	 *  resource dependencies 
	 *  **/
	public List<ResourceParentModel> checkConnectedResources(long id, String resourceType) {
		if ( resourceType.toLowerCase().contains("dataset")) 
			return checkDatasetConnectedResources(id);
		else if ( resourceType.toLowerCase().contains(ResourceType.ChartView.toString()))
			return checkChartConnectedResources(id);
		else if ( resourceType.toLowerCase().contains(ResourceType.MapView.toString()))
			return checkMapConnectedResources(id);
		else if ( resourceType.toLowerCase().contains(ResourceType.Layer.toString()))
			return checkLayerConnectedResources(id);
		else if ( resourceType.toLowerCase().contains(ResourceType.TextView.toString()))
			return checkTextConnectedResources(id);
		else 
			return new ArrayList<ResourceParentModel>();
	}
	
	private List<ResourceParentModel> checkDatasetConnectedResources(long datasetId) {
		List<ResourceParentModel> result = new ArrayList<ResourceParentModel>();
		List<Long> chartIds = chartDao.getCharts(datasetId);
		
		/** charts **/
		if ( !chartIds.isEmpty()) {
			/** query for the title of the charts **/
//			System.out.println("chart size: " + chartIds.size() + " | " + chartIds);
			for(Long chartId : chartIds) {
				ChartView chartView = chartDao.findById(chartId);
				ResourceParentModel resource = new ResourceParentModel();
				resource.setName(chartView.getTitle());
				resource.setType(ResourceType.ChartView.toString());
				resource.setId(Long.toString(chartId));
				result.add(resource);
			}
		}
		
		/** maps **/
		List<ProjectedData> projections = mapDao.getAllProjectedData(datasetId);
		if ( !projections.isEmpty()) {
			/** query for the title of the ProjectedData **/
			for(ProjectedData projection : projections) {
//				System.out.println("projection.getTitle(): " + projection.getTitle());
				ResourceParentModel resource = new ResourceParentModel();
				resource.setName(projection.getTitle());
				resource.setType(ResourceType.Layer.toString());
				resource.setId(Long.toString(projection.getResourceId()));			
				result.add(resource);
//				System.out.println("projection.getResourceId(): " +projection.getResourceId());
				List<MapView> maps = mapDao.findMapViews(projection.getResourceId());
				if ( !maps.isEmpty()) {
					/** get all maps that contains that layer **/
					for(MapView map : maps) {
						resource = new ResourceParentModel();
						resource.setName(map.getTitle());
						resource.setType(ResourceType.MapView.toString());
						resource.setId(Long.toString(map.getResourceId()));			
						result.add(resource);
					}
				}
			}
				
		}	
		
		return result;
	}
	
	private List<ResourceParentModel> checkChartConnectedResources(long chartId) {
		List<ResourceParentModel> result = new ArrayList<ResourceParentModel>();
		List<ReportView> reportViews = reportDao.findChartsViews(chartId);		
		if ( !reportViews.isEmpty()) {
			for(ReportView reportView : reportViews) {
				ResourceParentModel resource = new ResourceParentModel();
				resource.setName(reportView.getTitle());
				resource.setType(ResourceType.ReportView.toString());
				resource.setId(Long.toString(reportView.getResourceId()));
				result.add(resource);
			}
		}
		
		return result;
	}
	
	private List<ResourceParentModel> checkMapConnectedResources(long mapId) {
		List<ResourceParentModel> result = new ArrayList<ResourceParentModel>();
		List<ReportView> reportViews = reportDao.findMapViews(mapId);		
		if ( !reportViews.isEmpty()) {
			for(ReportView reportView : reportViews) {
				ResourceParentModel resource = new ResourceParentModel();
				resource.setName(reportView.getTitle());
				resource.setType(ResourceType.ReportView.toString());
				resource.setId(Long.toString(reportView.getResourceId()));
				result.add(resource);
			}
		}
		
		return result;
	}
	
	private List<ResourceParentModel> checkLayerConnectedResources(long LayerId) {
		List<ResourceParentModel> result = new ArrayList<ResourceParentModel>();
		List<MapView> mapViews = mapDao.findMapViews(LayerId);		
		if ( !mapViews.isEmpty()) {
			for(MapView mapView : mapViews) {
				ResourceParentModel resource = new ResourceParentModel();
				resource.setName(mapView.getTitle());
				resource.setType(ResourceType.ReportView.toString());
				resource.setId(Long.toString(mapView.getResourceId()));
				result.add(resource);
			}
		}
		
		return result;
	}
	
	private List<ResourceParentModel> checkTextConnectedResources(long textId) {
		List<ResourceParentModel> result = new ArrayList<ResourceParentModel>();
		List<ReportView> reportViews = reportDao.findTextViews(textId);		
		if ( !reportViews.isEmpty()) {
			for(ReportView reportView : reportViews) {
				ResourceParentModel resource = new ResourceParentModel();
				resource.setName(reportView.getTitle());
				resource.setType(ResourceType.ReportView.toString());
				resource.setId(Long.toString(reportView.getResourceId()));
				result.add(resource);
			}
		}
		
		return result;
	}
	
	
	/**
	 * This is going to change the layer style name on fenixDB.
	 * Should be added a funcionality to change the style name also on GeoServer
	 * Otherwise accessing the layer directly from GeoServer the layer
	 * will be displayed with the old style.
	 */
	public void changeLayerStyle(Long layerId, String styleName) {
		WMSMapProvider layer = wmsMapProviderDao.findById(layerId);
		
		LOGGER.info("default style: " + layer.getDefaultStyle());
		
		boolean isFeatureType = false;
		
		try {
			FeatureLayer lf = (FeatureLayer) layer;
			FeatureType featureType = lf.getFeatureType();
			
			featureType.setDefaultStyle(styleName);
			updateDao.updateFeatureLayer(featureType);
			isFeatureType = true;
		}
		catch (Exception e) {
			LOGGER.info("dbfeaturelayer error");
		}
		
		LOGGER.info("isFeaturetype: " + isFeatureType);
		
		if ( !isFeatureType ) {
			try {
				RasterLayer lr = (RasterLayer) layer;
				Coverage coverage = lr.getCoverage();
				
				coverage.setDefaultStyle(styleName);
				updateDao.updateRasterLayer(coverage);
			}
			catch (Exception e) {
				LOGGER.info("dbfeaturelayer error");
			}
		}
		
		LOGGER.info("set new  style: " + layer.getDefaultStyle());
		
		
	}
	
	public Boolean associateDatesetToChart(Long chartId, Long datasetId) {
		Dataset dataset = datasetDao.findById(datasetId);
		chartDao.associateDatasetToChart(chartId, dataset);
		return null;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public void setUniqueValuesDao(UniqueValuesDao uniqueValuesDao) {
		this.uniqueValuesDao = uniqueValuesDao;
	}

	public void setUpdateDao(UpdateDao updateDao) {
		this.updateDao = updateDao;
	}

	public void setFindDao(FindDao findDao) {
		this.findDao = findDao;
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	public void setPropertiesReader(PropertiesReader propertiesReader) {
		this.propertiesReader = propertiesReader;
	}

}