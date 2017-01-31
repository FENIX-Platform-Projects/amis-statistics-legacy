package org.fao.fenix.web.modules.edi.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.constants.UploadPolicy;
import org.fao.fenix.core.domain.faostat.FAOStatParameters;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.faostat.FAOStatDao;
import org.fao.fenix.core.persistence.file.CsvFactory;
import org.fao.fenix.core.utils.DatasetImporter;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.edi.common.services.FAOStatService;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;
import org.fao.fenix.web.modules.edi.common.vo.FAOStatParametersVO;
import org.fao.fenix.web.modules.table.server.TableExcel;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class FAOStatServiceImpl extends RemoteServiceServlet implements FAOStatService {
	
	private static final Logger LOGGER = Logger.getLogger(FAOStatServiceImpl.class);
	
	private FAOStatDao faoStatDao;
	
	private String dir;
	
	private DatasetImporter datasetImporter;
	
	private CsvFactory csvFactory;
	
	private TableExcel tableExcel;
	
	public FAOStatServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Long importFAOStatDataset(FAOStatParametersVO vo) throws FenixGWTException {
		Long datasetID = null;
		FAOStatParameters p = vo2p(vo);
		p.setLanguage(getLanguageCode(p.getLanguage()));
		String sql = sql(p);
		p.setSql(sql);
		p.setConnectionString(createSQLServer2000ConnectionString(p));
		try {
			List<List<String>> dataset = faoStatDao.importFAOStatDataset(p, true, false);
			List<String> headers = createFAOStatHeaders(false);
			File csv = csvFactory.createFAOStatCsvFile(headers, dataset);
			File xml = getMetadata(p);
			datasetID = datasetImporter.importDataset(new FileInputStream(csv), new FileInputStream(xml), UploadPolicy.overwrite, ",");
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (FileNotFoundException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return datasetID;
	}
	
	public String exportExcel(FAOStatParametersVO vo, boolean showCodes, boolean showLabels) throws FenixGWTException {
		String filename = null;
		FAOStatParameters p = vo2p(vo);
		p.setLanguage(getLanguageCode(p.getLanguage()));
		String sql = null;
		if (showLabels)
			sql = sqlWithLabels(p);
		else
			sql = sql(p);
		p.setSql(sql);
		p.setConnectionString(createSQLServer2000ConnectionString(p));
		try {
//			if ((showCodes) && (!showLabels)) {
				List<List<String>> dataset = faoStatDao.importFAOStatDataset(p, false, showLabels);
				List<String> headers = createFAOStatHeaders(showLabels);
				dataset.add(0, headers);
				if (showLabels)
					filename = tableExcel.createExcel("FAOStat - " + p.getGroupLabel(), dataset, 7, "2");
				else
					filename = tableExcel.createExcel("FAOStat - " + p.getGroupLabel(), dataset, 4, "2");
//			}
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return filename;
	}
	
	private String sql(FAOStatParameters p) {
		String sql = "SELECT D.Year, D.AreaCode, D.ItemCode, D.ElementCode, D.Value, F.FlagDescription" + p.getLanguage() + ", E.UnitName" + p.getLanguage() + " " +
					 "FROM Data D, DomainItem DI, Element E, Flag F " +
					 "WHERE D.AreaCode IN (" + getAreas(p) + ") " +
					 "AND D.ItemCode IN (" + getItems(p) + ") " +
					 "AND D.ElementCode IN (" + getElements(p) + ") " +
					 "AND DI.DomainCode = '" + getDomain(p) + "' " +
					 "AND D.Year IN (" + getYears(p) + ") " +
					 "AND DI.ItemCode = D.ItemCode " +
					 "AND D.ElementCode = E.ElementCode " +
					 "AND F.Flag = D.Flag " +
					 "ORDER BY D.Year DESC";
		LOGGER.info(sql);
		return sql;
	}
	
	private String sqlWithLabels(FAOStatParameters p) {
		String sql = "SELECT D.Year, D.AreaCode, A.AreaName" + p.getLanguage() + ", D.ItemCode, I.ItemName" + p.getLanguage() + ", D.ElementCode, E.ElementName" + p.getLanguage() + ", D.Value, F.FlagDescription" + p.getLanguage() + ", E.UnitName" + p.getLanguage() + " " +
					 "FROM Data D, DomainItem DI, Element E, Flag F, Area A, Item I " +
					 "WHERE D.AreaCode IN (" + getAreas(p) + ") " +
					 "AND D.ItemCode IN (" + getItems(p) + ") " +
					 "AND D.ElementCode IN (" + getElements(p) + ") " +
					 "AND DI.DomainCode = '" + getDomain(p) + "' " +
					 "AND D.Year IN (" + getYears(p) + ") " +
					 "AND DI.ItemCode = D.ItemCode " +
					 "AND D.ElementCode = E.ElementCode " +
					 "AND F.Flag = D.Flag " +
					 "AND D.AreaCode = A.AreaCode " +
					 "AND D.ItemCode = I.ItemCode " +
					 "ORDER BY D.Year DESC";
		LOGGER.info(sql);
		return sql;
	}
	
	private File getMetadata(FAOStatParameters p) throws FenixException {
		File xml = null;
		if (p.getGroupCode().equals("E")) {
			
		} else if (p.getGroupCode().equals("F")) {
			
		} else if (p.getGroupCode().equals("P")) {
			
		} else if (p.getGroupCode().equals("Q")) {
			for (String domain : p.getDomains().keySet()) {
				if (domain.equals("QC")) {
					xml = new File(dir + File.separator + "FAOStatProductionCrops_metadata.xml");
					break;
				} else if (domain.equals("QD")) {
					xml = new File(dir + File.separator + "FAOStatProductionCropsProcessed_metadata.xml");
					break;
				} else if (domain.equals("QA")) {
					xml = new File(dir + File.separator + "FAOStatProductionLiveAnimals_metadata.xml");
					break;
				} else if (domain.equals("QL")) {
					xml = new File(dir + File.separator + "FAOStatProductionLivestockPrimary_metadata.xml");
					break;
				} else if (domain.equals("QP")) {
					xml = new File(dir + File.separator + "FAOStatProductionLivestockProcessed_metadata.xml");
					break;
				} else if (domain.equals("Q")) {
					xml = new File(dir + File.separator + "FAOStatProductionProduction_metadata.xml");
					break;
				}
			}
		} else if (p.getGroupCode().equals("R")) {
			
		} else if (p.getGroupCode().equals("S")) {
			
		} else if (p.getGroupCode().equals("T")) {
			
		}
		return xml;
	}
	
	private List<String> createFAOStatHeaders(boolean addCodeColumns) {
		List<String> headers = new ArrayList<String>();
		headers.add("Year");
		if (addCodeColumns)
			headers.add("Area Code");
		headers.add("Area Name");
		if (addCodeColumns)
			headers.add("Item Code");
		headers.add("Item Name");
		if (addCodeColumns)
			headers.add("Element Code");
		headers.add("Element Name");
		headers.add("Value");
		headers.add("Flag");
		headers.add("Measurement Unit");
		return headers;
	}
	
	public Map<String, String> getFAOStatDimensions(FAOStatParametersVO vo, EDISettings tableName) throws FenixGWTException {
		FAOStatParameters p = vo2p(vo);
		p.setLanguage(getLanguageCode(p.getLanguage()));
		String sql = null;
		switch (tableName) {
			case Domain:
				sql = "SELECT DomainCode, DomainName" + p.getLanguage() + " FROM Domain WHERE GroupCode = '" + p.getGroupCode() + "'";
			break;
			case Area:
				String area_codes = "";
				Object[] area_items = p.getItems().keySet().toArray();
				for (int i = 0 ; i < area_items.length ; i++) {
					area_codes += "'" + area_items[i].toString() + "'";
					if (i < area_items.length - 1)
						area_codes += ", ";
				}
				sql = "SELECT AreaCode, AreaName" + p.getLanguage() + " FROM Area WHERE AreaCode IN (SELECT AreaCode FROM Data WHERE ItemCode IN (" + area_codes + ")) ORDER BY AreaName" + p.getLanguage() + " ASC";
			break;
			case Item:
				sql = "SELECT ItemCode, ItemName" + p.getLanguage() + " FROM Item";
			break;
			case Element:
				String codes = "";
				Object[] items = p.getItems().keySet().toArray();
				for (int i = 0 ; i < items.length ; i++) {
					codes += "'" + items[i].toString() + "'";
					if (i < items.length - 1)
						codes += ", ";
				}
				sql = "SELECT ElementCode, ElementName" + p.getLanguage() + " FROM Element WHERE ElementCode IN (SELECT ElementCode FROM Data WHERE ItemCode IN (" + codes + ")) ORDER BY ElementName" + p.getLanguage() + " ASC";
			break;
			case DomainItem:
				String domain = null;
				for (String key : p.getDomains().keySet())
					domain = key;
				sql = "SELECT ItemCode, ItemName" + p.getLanguage() + " FROM Item WHERE ItemCode IN (SELECT ItemCode FROM DomainItem WHERE DomainCode = '" + domain + "') ";
			break;
		}
		p.setSql(sql);
		p.setConnectionString(createSQLServer2000ConnectionString(p));
		try {
			return faoStatDao.getFAOStatDimensions(p);
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private String getLanguageCode(String language) {
		switch (LANG.valueOf(language)) {
			case EN: return "E";
			case FR: return "F";
			case ES: return "S";
			case AR: return "A";
			case ZH: return "C";
			case RU: return "R";
		}
		return language;
	}
	
	private FAOStatParameters vo2p(FAOStatParametersVO vo) {
		FAOStatParameters p = new FAOStatParameters();
		p.setAreas(vo.getAreas());
		p.setDatasource(vo.getDatasource());
		p.setDbDriver(vo.getDbDriver());
		p.setDbName(vo.getDbName());
		p.setDbPassword(vo.getDbPassword());
		p.setDbPortNumber(vo.getDbPortNumber());
		p.setDbServerName(vo.getDbServerName());
		p.setDbUrl(vo.getDbUrl());
		p.setDbUserName(vo.getDbUserName());
		p.setDomains(vo.getDomains());
		p.setElements(vo.getElements());
		p.setGroupCode(vo.getGroupCode());
		p.setGroupLabel(vo.getGroupLabel());
		p.setItems(vo.getItems());
		p.setYears(vo.getYears());
		p.setLanguage(vo.getLanguage());
		p.setConnectionString(vo.getConnectionString());
		p.setConnectionDriver(vo.getConnectionDriver());
		p.setSql(vo.getSql());
		return p;
	}
	
	private String createSQLServer2000ConnectionString(FAOStatParameters p) {
		StringBuilder sb = new StringBuilder();
		sb.append(p.getDbUrl());
		sb.append(p.getDbServerName());
		sb.append(";");
		sb.append("databaseName=");
		sb.append(p.getDbName());
		sb.append(";");
		return sb.toString();
	}
	
	private List<Long> getViewConfigurationFile(String viewName) throws FenixGWTException {
		List<Long> ids = new ArrayList<Long>();
		try {
			FileInputStream is = new FileInputStream(new File(dir + File.separator + viewName + ".xml"));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(is);
			Element element = document.getDocumentElement();
			NodeList presetNode = element.getElementsByTagName("preset");
			for (int i = 0; i < presetNode.getLength(); i++) {
				Element channelElement = (Element) presetNode.item(i);
				String title = channelElement.getAttribute("title");
				/*if (title.equalsIgnoreCase(predefineMapName)) {
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
				}*/
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
	
	
	public void setFaoStatDao(FAOStatDao faoStatDao) {
		this.faoStatDao = faoStatDao;
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setDatasetImporter(DatasetImporter datasetImporter) {
		this.datasetImporter = datasetImporter;
	}
	
	private String getAreas(FAOStatParameters p) {
		String area_codes = "";
		Object[] areas = p.getAreas().keySet().toArray();
		for (int i = 0 ; i < areas.length ; i++) {
			area_codes += "'" + areas[i].toString() + "'";
			if (i < areas.length - 1)
				area_codes += ", ";
		}
		return area_codes;
	}
	
	private String getItems(FAOStatParameters p) {
		String item_codes = "";
		Object[] items = p.getItems().keySet().toArray();
		for (int i = 0 ; i < items.length ; i++) {
			item_codes += "'" + items[i].toString() + "'";
			if (i < items.length - 1)
				item_codes += ", ";
		}
		return item_codes;
	}
	
	private String getElements(FAOStatParameters p) {
		String elements_codes = "";
		Object[] elements = p.getElements().keySet().toArray();
		for (int i = 0 ; i < elements.length ; i++) {
			elements_codes += "'" + elements[i].toString() + "'";
			if (i < elements.length - 1)
				elements_codes += ", ";
		}
		return elements_codes;
	}
	
	private String getYears(FAOStatParameters p) {
		String years_codes = "";
		Object[] years = p.getYears().keySet().toArray();
		for (int i = 0 ; i < years.length ; i++) {
			years_codes += "'" + years[i].toString() + "'";
			if (i < years.length - 1)
				years_codes += ", ";
		}
		return years_codes;
	}
	
	private String getDomain(FAOStatParameters p) {
		Object[] domains = p.getDomains().keySet().toArray();
		return domains[0].toString().trim();
	}

	public void setCsvFactory(CsvFactory csvFactory) {
		this.csvFactory = csvFactory;
	}

	public void setTableExcel(TableExcel tableExcel) {
		this.tableExcel = tableExcel;
	}

	enum LANG {
		EN, FR, ES, AR, ZH, RU
	}
	
}