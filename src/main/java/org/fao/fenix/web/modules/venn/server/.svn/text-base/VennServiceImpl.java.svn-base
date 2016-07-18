package org.fao.fenix.web.modules.venn.server;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.constants.CodingType;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.IndexCoreContent;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.map.cql.CQLFilter;
import org.fao.fenix.core.domain.map.cql.CQLFilterOp;
import org.fao.fenix.core.domain.map.cql.gaul.GaulCQLFilterFactory;
import org.fao.fenix.core.domain.map.geoserver.BoundingBox;
import org.fao.fenix.core.domain.map.geoserver.GeoServer;
import org.fao.fenix.core.domain.map.layer.InternalWMSLayer;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.olap.OLAPDao;
import org.fao.fenix.core.persistence.perspective.ViewDao;
import org.fao.fenix.core.persistence.utils.LayerGaulUtils;
import org.fao.fenix.core.persistence.venn.VennDao;
import org.fao.fenix.core.utils.GaulUtils;
import org.fao.fenix.map.geoserver.io.GeoserverIO;
import org.fao.fenix.map.geoserver.io.GeoserverPublisher;
import org.fao.fenix.map.mapretriever.ImageMerger;
import org.fao.fenix.map.mapretriever.WMSMapRetriever;
import org.fao.fenix.map.quickmap.DBFLGeometryResolver;
import org.fao.fenix.map.quickmap.GeoPairImpl;
import org.fao.fenix.map.quickmap.LayerValuesProviderImpl;
import org.fao.fenix.map.quickmap.QuickFeatureBuilder;
import org.fao.fenix.map.quickmap.QuickFeatureCollection;
import org.fao.fenix.map.quickmap.QuickMap;
import org.fao.fenix.map.quickmap.QuickMap.Range;
import org.fao.fenix.map.quickmap.iface.FeatureBuilder;
import org.fao.fenix.map.quickmap.iface.GeoPair;
import org.fao.fenix.map.quickmap.iface.GeometryResolver;
import org.fao.fenix.map.quickmap.iface.LayerValuesProvider;
import org.fao.fenix.map.style.TextSymbUtil;
import org.fao.fenix.map.util.ColourUtil;
import org.fao.fenix.web.modules.birt.common.vo.ChartBean;
import org.fao.fenix.web.modules.birt.common.vo.ChartBeanValues;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.YSerieBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.server.utils.EmptyDatumLabel;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.olap.server.utils.OLAPUtils;
import org.fao.fenix.web.modules.venn.common.services.VennService;
import org.fao.fenix.web.modules.venn.common.vo.GridHeaderVO;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennChartBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennConfigurationBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennCountryBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennProjectsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennResultsVO;
import org.fao.fenix.web.modules.venn.common.vo.VennSetBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportTableValuesBeanVO;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class VennServiceImpl extends RemoteServiceServlet implements VennService {

	VennDao vennDao;
	
	DatasetDao datasetDao;
	
	CodecDao codecDao;
	
	WMSMapProviderDao wmsMapProviderDao;
	
	OLAPDao olapDao;
	
	LayerGaulUtils layerGaulUtils;
	
	ViewDao viewDao;

	final private static int HEIGHT = 350;
	
	final private static int WIDTH = 470;
	
	private String dir;
	
	private Logger LOGGER = Logger.getLogger(VennServiceImpl.class);
	
	public VennServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public List<CodeVo> getCountriesList(String language) {
		List<Code> codes = vennDao.getCountriesList(language);
		List<CodeVo> vos = new ArrayList<CodeVo>();
		for (Code code : codes)
			vos.add(code2vo(code));
		return vos;
	}
	
	private CodeVo code2vo(Code c) {
		CodeVo vo = new CodeVo();
		vo.setCode(c.getCode());
		vo.setDescription(c.getDescription());
		vo.setLabel(c.getLabel());
		vo.setLangCode(c.getLangCode());
		return vo;
	}
	
	
	public HashMap<String, String> findOrganizationsType(String language) {
		HashMap<String, String> result = new HashMap<String, String>();
		result = vennDao.findOrganizationsType(language);
		return result;
	}
	
	public HashMap<String, String> findOrganizations(String organizationType, String language) {
		HashMap<String, String> result = new HashMap<String, String>();
		result = vennDao.findOrganizations(organizationType, language);
		return result;
	}
	
	public HashMap<String, String> findSOIFA(String organizationType, String organization, String language) {
		HashMap<String, String> result = new HashMap<String, String>();
		result = vennDao.findSOIFA(organizationType, organization, language);
		return result;
	}
	
	public HashMap<String, String> findStrategiesLvl1(String organizationType, String organization, String soifa, String language) {
		HashMap<String, String> result = new HashMap<String, String>();
		result = vennDao.findStrategiesLvl1(organizationType, organization, soifa, language);
		return result;
	}
	
	public HashMap<String, String> findStrategiesLvl2(String organizationType, String organization, String soifa, String codeLvl1, String language) {
		HashMap<String, String> result = new HashMap<String, String>();
		result = vennDao.findStrategiesLvl2(organizationType, organization, soifa, codeLvl1, language);
		return result;
	}
	
	
	
	public VennResultsVO getDACCodes(HashMap<String, String> aCodes, HashMap<String, String> bCodes, HashMap<String, String> cCodes, String language) {
		VennResultsVO result = new VennResultsVO();
		
		// this is a quick hardcored solution for the dac hierarchy 
		Integer aggregationLevel = 2;
		
		HashMap<String, String> a_DACCodes = new HashMap<String, String>();
		HashMap<String, String> b_DACCodes = new HashMap<String, String>();
		HashMap<String, String> c_DACCodes = new HashMap<String, String>();
		HashMap<String, String> mergedCodes = new HashMap<String, String>();
		HashMap<String, HashMap<String, String>> so_dacCorrispondence = new HashMap<String, HashMap<String,String>>();
		HashMap<String, HashMap<String, String>> dac_soCorrispondence = new HashMap<String, HashMap<String,String>>();
		
		/** get all codes **/
//		LOGGER.info("aCodes a: " + aCodes.keySet());
//		LOGGER.info("bCodes b " + bCodes.keySet());
//		LOGGER.info("cCodes c: " + cCodes.keySet());
		
		for (String key : aCodes.keySet()) {
			HashMap<String, String> dacCodes = vennDao.findDacCodes(key, language);	
			for (String dacCode : dacCodes.keySet()) {
				aggregationLevel = getDacCodesSubLvlevels(a_DACCodes, mergedCodes, dacCode, language, aggregationLevel);
				a_DACCodes.put(dacCode, dacCodes.get(dacCode));
				mergedCodes.put(dacCode, dacCodes.get(dacCode));
				getDacAggregatedCode(mergedCodes, dacCode, language);
			}
//			System.out.println("dacCodes a: " + dacCodes);
			so_dacCorrispondence.put(key, dacCodes);
		}
		
		for (String key : bCodes.keySet()){
			HashMap<String, String>  dacCodes = vennDao.findDacCodes(key, language);
			
			for (String dacCode : dacCodes.keySet()) {
				aggregationLevel = getDacCodesSubLvlevels(b_DACCodes, mergedCodes, dacCode, language, aggregationLevel);
				b_DACCodes.put(dacCode, dacCodes.get(dacCode));
				mergedCodes.put(dacCode, dacCodes.get(dacCode));
				getDacAggregatedCode(mergedCodes, dacCode, language);
			}
//			System.out.println("dacCodes b : " + dacCodes);
			so_dacCorrispondence.put(key, dacCodes);
		}
		
		for (String key : cCodes.keySet()){
			HashMap<String, String>  dacCodes = vennDao.findDacCodes(key, language);
			for (String dacCode : dacCodes.keySet()) {
				aggregationLevel = getDacCodesSubLvlevels(c_DACCodes, mergedCodes, dacCode, language, aggregationLevel);
				c_DACCodes.put(dacCode, dacCodes.get(dacCode));
				mergedCodes.put(dacCode, dacCodes.get(dacCode));
				getDacAggregatedCode(mergedCodes, dacCode, language);
			}
//			System.out.println("dacCodes c : " + dacCodes);
			so_dacCorrispondence.put(key, dacCodes);
		}
		
		
		
//		LOGGER.info("a_DACCodes:" + a_DACCodes);
//		LOGGER.info("b_DACCodes:" + b_DACCodes);
//		LOGGER.info("c_DACCodes:" + c_DACCodes);
//		LOGGER.info("mergedCodes:" + mergedCodes);
//		LOGGER.info("aggregationLevel:" + aggregationLevel);
		
		// filter dac codes by aggregation
		if ( aggregationLevel != 2) {
			a_DACCodes = filterDACbyaggregationLevel(a_DACCodes, mergedCodes, aggregationLevel);
			b_DACCodes = filterDACbyaggregationLevel(b_DACCodes, mergedCodes, aggregationLevel);
			c_DACCodes = filterDACbyaggregationLevel(c_DACCodes, mergedCodes, aggregationLevel);
		}
		
//		LOGGER.info("a_DACCodes:" + a_DACCodes);
//		LOGGER.info("b_DACCodes:" + b_DACCodes);
//		LOGGER.info("c_DACCodes:" + c_DACCodes);
//		LOGGER.info("mergedCodes:" + mergedCodes);
//		LOGGER.info("aggregationLevel:" + aggregationLevel);
		
		result.setA_dacCodes(a_DACCodes);
		result.setB_dacCodes(b_DACCodes);
		result.setC_dacCodes(c_DACCodes);
		result.setMergedDacCodes(mergedCodes);
		result.setSo_dacCorrispondence(so_dacCorrispondence);
//		result.setDac_soCorrispondence(dac_soCorrispondence);
		result.setAggregationLevel(aggregationLevel);
			
		// set dac coded aggregation level
		
		return result;
	}
		
	private HashMap<String, String> filterDACbyaggregationLevel(HashMap<String, String> hashMap , HashMap<String, String> mergedHashMap, Integer aggregationLevel) {
		 HashMap<String, String> result = new HashMap<String, String>();
		 
		 for(String dacCode : hashMap.keySet()) {
			if ( aggregationLevel == 0) {
				System.out.println(dacCode + " | " + dacCode.substring(0, 2));
				result.put(dacCode.substring(0, 2) + "000", mergedHashMap.get(dacCode.substring(0, 2) + "000"));
			}
			else if ( aggregationLevel == 1) {
				System.out.println(dacCode + " | " + dacCode.substring(0, 3));
				result.put(dacCode.substring(0, 3) + "00", mergedHashMap.get(dacCode.substring(0, 3) + "00"));
			}
		 }
		 
		 return result;
	}
	
	private Integer getAggregationLevel(String dacCode) {
		if (dacCode.contains("000")) 
			return 0;
		else if (dacCode.contains("00"))
			return 1;
		return 2;

		
	}

	private Integer getDacCodesSubLvlevels(HashMap<String, String> dacCodesHM, HashMap<String, String> mergedCodesHM, String dacCode, String language, Integer aggregationLevel) {
		List<Code> subDacCodes = new ArrayList<Code>();
		Code code = new Code();
	
		if (dacCode.contains("000")) {
//			LOGGER.info("code: " + dacCode + " |  LVL:0");
			code = codecDao.getCodeFromCodeSystemRegion(dacCode, "DAC", "0", language);
			dacCode = dacCode.replace("000", "");
			subDacCodes = vennDao.getDacCodesSubLevels(dacCode, language);
			if ( aggregationLevel >= 0 ) 
				aggregationLevel = 0;
		}			
		else if (dacCode.contains("00")){
			code = codecDao.getCodeFromCodeSystemRegion(dacCode, "DAC", "0", language);
//			LOGGER.info("code: " + dacCode + " |  LVL:1");
			dacCode = dacCode.replace("00", "");
			subDacCodes = vennDao.getDacCodesSubLevels(dacCode, language);
			if ( aggregationLevel >= 1 )
				aggregationLevel = 1;
		}
		else {
			code = codecDao.getCodeFromCodeSystemRegion(dacCode, "DAC", "0", language);
			dacCodesHM.put(code.getCode(), code.getLabel());
			mergedCodesHM.put(code.getCode(), code.getLabel());
			if ( aggregationLevel >= 2 )
				aggregationLevel = 2;
		} 
			
			
		// adding retrieved codes
		if ( !subDacCodes.isEmpty()) {
			mergedCodesHM.put(code.getCode(), code.getLabel());
			
			for(Code subCode : subDacCodes) {
//				LOGGER.info("-->" + subCode.getCode());
				dacCodesHM.put(subCode.getCode(), subCode.getLabel());
				mergedCodesHM.put(subCode.getCode(), subCode.getLabel());
			}				
		}
		
		

		return aggregationLevel;
	}
	
	private void getDacAggregatedCode(HashMap<String, String> mergedCodesHM, String dacCode, String language) {
		
		Code code = new Code();
		
		String dacCode0 = dacCode.substring(0, 2) + "000";
//		LOGGER.info("DAC0 "+ dacCode0);
		
		String dacCode1 = dacCode.substring(0, 3) + "00";
//		LOGGER.info("DAC1 "+ dacCode1);
		
	
		if ( !mergedCodesHM.containsKey(dacCode0)) {
			code = codecDao.getCodeFromCodeSystemRegion(dacCode0, "DAC", "0", language);
			mergedCodesHM.put(code.getCode(), code.getLabel());
		}
		
		if ( !mergedCodesHM.containsKey(dacCode1)) {
			code = codecDao.getCodeFromCodeSystemRegion(dacCode1, "DAC", "0", language);
			mergedCodesHM.put(code.getCode(), code.getLabel());
		}

	}
	

	public HashMap<String, HashMap<String, String>> getDAC_FAO_SO_Codes(HashMap<String, String> dacCodes) {
		HashMap<String, HashMap<String, String>> dac_so = new HashMap<String, HashMap<String,String>>();
		for(String dac : dacCodes.keySet()) {
			HashMap<String, String> so = vennDao.findDAC_DAO_SO(dac, "FAO", "EN");
			dac_so.put(dac, so);
		}
		
		return dac_so;
	}

	
	
	public List<VennIframeVO> createVenn(VennBeanVO bean, String vennType) {	
		List<VennIframeVO> results = new ArrayList<VennIframeVO>();
		VennResultsVO result = new VennResultsVO();
	
		VennIframeVO vennIframeVO = new VennIframeVO();
		
		CreateVennImage vennContentPanel = new CreateVennImage(bean, 330, 330, 14, 15, 20);
		vennIframeVO.setIframeCentralPanel(vennContentPanel.createImage());
		
//		CreateVennImage vennPortlet = new CreateVennImage(bean, 100, 100);
//		CreateVennImage vennPortlet = new CreateVennImage(bean, 200, 200, 9, 10);
		CreateVennImage vennPortlet = new CreateVennImage(bean, 200, 200, 10, 10, 9);
		vennIframeVO.setIframePortlet(vennPortlet.createImage());
		
		result.setIframes(vennIframeVO);
		
		bean.getVennGraphBeanVO().setVennResults(result); 
		
		results.add(vennIframeVO);
		return results;
	}
	
	

	public List<VennIframeVO> createHighlightedVenn(VennBeanVO bean, String vennType, String intersectionLabel) {	
			
		List<VennIframeVO> results = new ArrayList<VennIframeVO>();
		
		VennResultsVO result = new VennResultsVO();
		
		VennIframeVO vennIframeVO = new VennIframeVO();
		
		System.out.println("createHighlightedVenn intersection Label = "+intersectionLabel);
			
		//Highlighted Big venn
		CreateVennImage vennContentPanel = new CreateVennImage(bean, 330, 330, 14, 15, 20, intersectionLabel);
		vennIframeVO.setIframeCentralPanel(vennContentPanel.createImage());
			
		//Highlighted Small venn
		CreateVennImage venn1 = new CreateVennImage(bean, 200, 200, 10, 10, 9, intersectionLabel); // e.g intersectionLabel = abc = middle intersection
		vennIframeVO.setIframePortlet(venn1.createImage());
		
        result.setIframes(vennIframeVO);
		
		bean.getVennGraphBeanVO().setVennResults(result); 
		
		results.add(vennIframeVO);
		
		return results;
		
	}
	
	private void getProjects(VennBeanVO vennBean, String countryCode, String language) {
		
		LOGGER.info("view dao");
		String viewName = "VIEW_" + countryCode + "_ADAM";
		
		// getting the countries list to get the datasets and the related views

		VennCountryBeanVO bean = new VennCountryBeanVO();
			
		// select dinamically the dataset
		Dataset dataset = datasetDao.findByCode(countryCode + "_ADAM");
			
		List<Descriptor> descriptors = dataset.getDatasetType().getDescriptors();
		List<GridHeaderVO> gridHeadersVO = new ArrayList<GridHeaderVO>();
		List<String> headers = new ArrayList<String>();
		headers.add("ID");
		
		Integer sectorCodeIdx = 0;
		int i=0;
		
		for( Descriptor header : descriptors) {
			GridHeaderVO gridHeader = new GridHeaderVO();
			gridHeader.setDataType(header.getDataType().toString());
			gridHeader.setHeader(header.getHeader());
			gridHeadersVO.add(gridHeader);
			System.out.println("headers: " + header.getHeader() + " | "+ header.getDataType());
				
			headers.add(header.getHeader());
			
			if ( header.getHeader().equals("Sector Code")) {
				sectorCodeIdx = i;
			}
			i++;
		}
		
		System.out.println("sectorCodeIdx: " + sectorCodeIdx);
			
		
		List<DataType> contentDescriptorList = GwtConnector.getContentDescriptorsFromColumnNames(dataset, headers);
		System.out.println("--> " + contentDescriptorList.size());
			
		for(DataType datatype : contentDescriptorList)
			System.out.println("" + datatype.toString());
		
		if( dataset == null)
			System.out.println("datatset is null");
		
		
		
		for(VennProjectsBean projectsBean : bean.getAllIntersections()) {
			VennIntersectionsBean intersaction = vennBean.getVennGraphBeanVO().getIntersection(projectsBean.getIntersectionName());
			if ( !intersaction.getDacCodes().isEmpty() ) {
				HashMap<String, List<String>> filterCriteria = new HashMap<String, List<String>>();
				// get the full list of dacCodes
//				LOGGER.info("list od DAC: " + intersaction.getDacCodes());
				HashMap<String, String> fullDACcodes = new HashMap<String, String>();
				HashMap<String, String> mergedCodes = new HashMap<String, String>();
				for(String dacCode : intersaction.getDacCodes()) {
					// this is just the other function that i use before in the seach of the codes
					getDacCodesSubLvlevels(fullDACcodes, mergedCodes, dacCode, "EN", new Integer(0));
				}
				
//				LOGGER.info("fullDACcodes list od DAC: " + fullDACcodes);
				
				List<String> fullDACcodesList = new ArrayList<String>();
				for(String dacCode : fullDACcodes.keySet())
					fullDACcodesList.add(dacCode);
				
//				LOGGER.info("fullDACcodesList list od DAC: " + fullDACcodesList);
				
//				fullDACcodesList.add("41090209");
				
				filterCriteria.put("col" + sectorCodeIdx, fullDACcodesList);
//				filterCriteria.put("col0", fullDACcodesList);
		
				
				List<List<String>> rawRows = new ArrayList<List<String>>();
				List<List<String>> rows = viewDao.getFilteredRawView(dataset, headers, filterCriteria, rawRows, viewName, language);
//				
				

//				List<List<String>> rows = getFilteredRecords(dataset, headers, filterCriteria, language, rawRows);
//					System.out.println("ROWS " + rows);
//				System.out.println("RAW ROWS " + rawRows);
				
				projectsBean.setHeaders(gridHeadersVO);
				projectsBean.setProjectsRows(rows);
				projectsBean.setProjectsRawRows(rawRows);
			}
			else {
				projectsBean.setHeaders(new ArrayList<GridHeaderVO>());
				projectsBean.setProjectsRows(new ArrayList<List<String>>());
				projectsBean.setProjectsRawRows(new ArrayList<List<String>>());
			}
				
		}
		
		vennBean.getVennCountryBeanVO().put(countryCode, bean);
	}
	
	
	/* TODO: this is going to be changed with the new dataset table **/
	// use dataset id or dataset code, and language for the codes
	private void getProjectsGEOCodes(VennBeanVO bean, String datasetcode, String language) {
//		LOGGER.info("geo codes");
//		
//		// select dinamically the dataset
//		Dataset dataset = datasetDao.findByCode("BorromeoProjectsDatasetGEO");
//		
//		List<Descriptor> descriptors = vennDao.getProjectsHeaders("BorromeoProjectsDatasetGEO");
//		List<GridHeaderVO> gridHeadersVO = new ArrayList<GridHeaderVO>();
//		List<String> headers = new ArrayList<String>();
//		headers.add("ID");
//		
//			for( Descriptor header : descriptors) {
//				GridHeaderVO gridHeader = new GridHeaderVO();
//				gridHeader.setDataType(header.getDataType().toString());
//				gridHeader.setHeader(header.getHeader());
//				gridHeadersVO.add(gridHeader);
//				System.out.println("headers: " + header.getHeader() + " | "+ header.getDataType());
//				
//				headers.add(header.getHeader());
//			}
//		
//		
//		
//		List<DataType> contentDescriptorList = GwtConnector.getContentDescriptorsFromColumnNames(dataset, headers);
//		System.out.println("--> " + contentDescriptorList.size());
//		
//		for(DataType datatype : contentDescriptorList)
//			System.out.println("" + datatype.toString());
//		
//		if( dataset == null)
//			System.out.println("datatset is null");
//		
//		for(VennIntersectionsBean intersaction : bean.getAllIntersections()) {
//			if ( !intersaction.getDacCodes().isEmpty() ) {
//				HashMap<String, List<String>> filterCriteria = new HashMap<String, List<String>>();
//				
//				LOGGER.info("list od DAC: " + intersaction.getDacCodes());
//				HashMap<String, String> fullDACcodes = new HashMap<String, String>();
//				HashMap<String, String> mergedCodes = new HashMap<String, String>();
//				for(String dacCode : intersaction.getDacCodes()) {
//					// this is just the other function that i use before in the seach of the codes
//					getDacCodesSubLvlevels(fullDACcodes, mergedCodes, dacCode, "EN", new Integer(0));
//				}
//				
//				LOGGER.info("fullDACcodes list od DAC: " + fullDACcodes);
//				
//				List<String> fullDACcodesList = new ArrayList<String>();
//				for(String dacCode : fullDACcodes.keySet())
//					fullDACcodesList.add(dacCode);
//				
//				LOGGER.info("fullDACcodesList list od DAC: " + fullDACcodesList);
//				
//				filterCriteria.put("firstIndicator", fullDACcodesList);
//				List<List<String>> rawRows = new ArrayList<List<String>>();
//				List<List<String>> rows = getFilteredRecords(dataset, headers, filterCriteria, language, rawRows);
//
//				System.out.println("ROWS " + rows);
//				System.out.println("RAW ROWS " + rawRows);
//				
//				intersaction.setHeadersGEO(gridHeadersVO);
//				intersaction.setProjectsGEORows(rows);
//				intersaction.setProjectsGEORawRows(rawRows);
//				
//				LOGGER.info("GEO CODES: " +  intersaction.getProjectsGEORows());
//			}
//			else {
//				intersaction.setHeaders(new ArrayList<GridHeaderVO>());
//				intersaction.setProjectsRows(new ArrayList<List<String>>());
//				intersaction.setProjectsRawRows(new ArrayList<List<String>>());
//			}
//				
//		}

	}
	
	private List<List<String>> getFilteredRecords(Dataset dataset, List<String> headerList, Map<String, List<String>> filterCriteria, String language, List<List<String>> rawRows) {
//		LOGGER.info("----- getFilteredRecords ----------------- ################");
		
//		Dataset dataset = datasetDao.findById(datasetId);
		
		List<Object[]> originalRowList = datasetDao.getFilteredRowValues(dataset, headerList, filterCriteria);
		
//		System.out.println("O: " + originalRowList.get(0).length);
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		for (int i = 0; i < originalRowList.size(); i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 0; j < originalRowList.get(i).length; j++)
				// if for core dataset
				if (originalRowList.get(i)[j] != null){
					// FIXME if for flex dataset
					if (!originalRowList.get(i)[j].equals("null"))
						row.add(originalRowList.get(i)[j].toString());
					else row.add(EmptyDatumLabel.getLabel());
				}				
				else
					row.add(EmptyDatumLabel.getLabel());
			rowList.add(row);
				
		}
		
		List<List<String>> rowListResult = new ArrayList<List<String>>();
	
		//--- cache some info for each column
		
		int columns = headerList.size();
		//LOGGER.info("------------- BEFORE columns =  "+ columns );
		long t0 = System.currentTimeMillis();
		DataType	colTypes[]		= new DataType[columns];
		Option		codingOptions[]	= new Option[columns];
		HashMap		cachedCodes[]	= new HashMap[columns];
		for (int j = 0; j < columns; j++) {
			String header = headerList.get(j);
//			LOGGER.info("------getFilteredRecords: header = " + header);
			if(!header.equals("ID")){	
			
				Descriptor descriptor = GwtConnector.getDescriptorFromColumnName(dataset, header);
				if(descriptor == null)
					throw new IllegalArgumentException("Could not find column '"+header+"' in dataset '"+dataset.getTitle()+"' (id:"+dataset.getResourceId()+")");
				// datatype
				colTypes[j] = descriptor.getDataType();
				// coding option, if any
				List<Option> optionList = descriptor.getOptions();

				codingOptions[j] = null; // init
				for (Option op : optionList){
					if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null){
						codingOptions[j] = op;
//						LOGGER.info("Found coding " + op);
						break;
					}
				}
			}
			// init maps
			cachedCodes[j] = new HashMap(60); // ??? this doesnt work ---> toIndex-fromIndex+5);
		}
		long t1 = System.currentTimeMillis();
//		LOGGER.info("getRecords: cacheInfo: " + (t1-t0) + "ms" );

		//LOGGER.info("------------- AFTER columns =  "+ columns );
		
		//if(dataset.getType() == Type.Flex)
		//	columns = columns -1;
		
		//--- transcode codes	
		for (int rowidx = 0; rowidx < rowList.size(); rowidx++){
			List<String> rowResult = new ArrayList<String>(columns);
			List<String> rowRawResult = new ArrayList<String>(columns);
				
			for (int colidx = 0; colidx < columns; colidx++) {
				
				String cellvalue = rowList.get(rowidx).get(colidx);
			
				rowRawResult.add(colidx, cellvalue);
				
				if(colidx == 0 /*&& dataset.getType() != Type.Flex*/){
					rowResult.add(cellvalue);
			    }
				else {
					try {
						/*if(dataset.getType() == Type.Flex && colTypes[colidx+1] == DataType.date){
							cellvalue = GwtConnector.formatDate(dataset, cellvalue);
						} else if(dataset.getType() == Type.Core && colTypes[colidx] == DataType.date){
							cellvalue = GwtConnector.formatDate(dataset, cellvalue);
						}*/
						if(colTypes[colidx] == DataType.date){
								cellvalue = GwtConnector.formatDate(dataset, cellvalue);;
						}	
						else {
							Option codingOption = null;	
							//if(dataset.getType() == Type.Flex ){
							//	 codingOption = codingOptions[colidx+1];
							//} else if(dataset.getType() == Type.Core){
								codingOption = codingOptions[colidx];	
							//}
							
							if(codingOption != null) { // if option is null, no decoding is needed
								String cached = (String)cachedCodes[colidx].get(cellvalue);
								if(cached == null) { // not in cache yet: retrieve the label
									cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), language);
									if ( cached.equals(cellvalue) && !language.equals("EN")) {
										cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), "EN");
									}
									/*LOGGER.info("Caching code: " +
											"coding["+codingOption.getOptionValue()+"] " +
											"region["+codingOption.getOptionRegion()+"] " +
											"code["+cellvalue+"] " +
											"label["+cached+"]");*/
									cachedCodes[colidx].put(cellvalue, cached);
								}
								cellvalue = cached;
							}
						}
						rowResult.add(colidx, cellvalue);

					} catch (IndexOutOfBoundsException e) {
						throw new FenixException(e.getMessage());
					}
		         }	
			}
						
			// add a fake row id
			//if (dataset.getType() == Type.Flex)
			//	rowResult.add(0, "0");		
			
			rowListResult.add(rowResult);
			rawRows.add(rowRawResult);
		}
		long t2 = System.currentTimeMillis();
		//LOGGER.info("getRecords: decode: " + (t2-t1) + "ms" );

		return rowListResult;
	}
	

	private List<List<String>> getValues(List<IndexCoreContent> contents) {
		List<List<String>> values = new ArrayList<List<String>>();
		for(IndexCoreContent content :contents) {
			List<String> value = new ArrayList<String>();
			value.add(content.getFeatureCode());
			value.add(content.getCommodityCode());
			value.add(content.getFirstIndicator());
			value.add(content.getSecondIndicator());
//			value.add(content.getBaseDateFrom().toString());
//			value.add(content.getBaseDateTo().toString());
			values.add(value);
//			values.add(content.getBaseDateFrom()());
		}
//		System.out.println("values: " + values);
		return values;
	}


	public VennBeanVO calculateIntersections(VennResultsVO results, List<String> countryCodes, String language){
		VennBeanVO bean = new VennBeanVO();
		Integer aggregationLevel = results.getAggregationLevel();
		bean.getVennGraphBeanVO().setAggregationLevel(aggregationLevel);
		HashMap<String, String> dacCodes = new HashMap<String, String>();
		
		
		List<String> a_dacCodes = new ArrayList<String>();
		List<String> b_dacCodes = new ArrayList<String>();
		List<String> c_dacCodes = new ArrayList<String>();
		
		List<String> a_dacCodesAggregations = new ArrayList<String>();
		List<String> b_dacCodesAggregations = new ArrayList<String>();
		List<String> c_dacCodesAggregations = new ArrayList<String>();
		
		if(results.getA_dacCodes()!=null) {
			for (String key : results.getA_dacCodes().keySet()) {
				a_dacCodes.add(key);
				a_dacCodesAggregations.add(key);
				getDacLabel(dacCodes, key);
			}
		}
		
		if(results.getB_dacCodes()!=null) {
			for (String key : results.getB_dacCodes().keySet()){
				b_dacCodes.add(key);
				b_dacCodesAggregations.add(key);
				getDacLabel(dacCodes, key);
			}
		}
		
		if(results.getC_dacCodes()!=null) {
			for (String key : results.getC_dacCodes().keySet()) {
				c_dacCodes.add(key);
				c_dacCodesAggregations.add(key);
				getDacLabel(dacCodes, key);
			}
		}
		VennUtils.calculateNumeberOfIntersections(a_dacCodesAggregations, b_dacCodesAggregations, c_dacCodes, bean.getVennGraphBeanVO(), false, true);

		
//		VennUtils.calculateDACAggregations(a_dacCodesAggregations, b_dacCodesAggregations, c_dacCodesAggregations, bean, false, true, aggregationLevel);
//		VennUtils.calculateIntersections(a_dacCodes, b_dacCodes, c_dacCodes, bean, true, false);
//		
//		for(String countryCode: countryCodes)
//			getProjects(bean, countryCode, language);
//		getProjectsGEOCodes(bean, "BorromeoProjectsDatasetGEO", language);
	
//		VennUtils.calulateNumberOfProjects(bean);
		
		VennUtils.fillIntersectionsColors(bean.getVennGraphBeanVO());
		
		/** get dac codes **/
		bean.getVennGraphBeanVO().getDacCodes().putAll(dacCodes);
		
		LOGGER.info(bean.getVennGraphBeanVO().getDacCodes());
		return bean;
	}
	
	private void getDacLabel(HashMap<String, String> dacCodes, String key) {
//		LOGGER.info(key);
		if(!dacCodes.containsKey(key)) {
			dacCodes.put(key, codecDao.getLabelFromCodeCodingSystem(key, "DAC", "0", "EN"));
//			LOGGER.info(dacCodes);
		}
				
	}
	
	
	
	
	/** tODO: handle it with different countries **/
	
	public LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> getChart(VennBeanVO bean, String servletType, String chartType) {
		LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> results = new LinkedHashMap<String, LinkedHashMap<String,VennChartBeanVO>>();
	
		String portletChartWidth = "270";
		String portletChartHeigth = "200";
		
		String centralChartWidth = "600";
		String centralChartHeigth = "350";
		
		
		LOGGER.info(chartType);
		if( chartType.toLowerCase().equals("totalbycategories")) { 
			/** TODO: datasetId and eventually the reference date **/
			return VennChartsUtils.getChartTotalByCategoriesLVL1(new Long(100), bean, servletType, chartType, centralChartWidth, centralChartHeigth, portletChartWidth, portletChartHeigth, new HashMap<String, List<Date>>(), vennDao, codecDao);
		}
		
		else if( chartType.toLowerCase().equals("globalbycategories")) { 
			/** TODO: datasetId and eventually the reference date **/
			return VennChartsUtils.getChartGlobalByCategories(new Long(100), bean, servletType, chartType, centralChartWidth, centralChartHeigth, portletChartWidth, portletChartHeigth, new HashMap<String, List<String>>(),new HashMap<String, List<Date>>(), vennDao, codecDao);
		}
		
		
		
		/** TODO: create charts multicountries **/
		
		/** this is country specific, for more countries we will have more charts **/
		for (String key  : bean.getVennCountryBeanVO().keySet()) {
			
			
			
			VennCountryBeanVO country = bean.getVennCountryBeanVO().get(key);
			
	
			
			String centralMultipleChartWidth = "530";
			String centralMultipleChartHeigth = "320";
	
		
			
			ChartWizardBean chartCP = null;
			ChartWizardBean chartPortlet = null;
			
			
			
			
			
			
	
			// Chart Per Frequencies Budget Projects
			if( chartType.toLowerCase().equals("frequencies")) {
				HashMap<String, VennChartBeanVO> charts = new HashMap<String, VennChartBeanVO>();
				chartCP = VennChartsUtils.setInfoBarChart("Total amount of Budget for common priorities", "Budget (mn)",  false, "45", centralChartWidth, centralChartHeigth);
				chartPortlet = VennChartsUtils.setInfoBarChart("Total amount of Budget for common priorities", "Budget (mn)", false, "45", portletChartWidth, portletChartHeigth);
	//			chartCP.setChartReportBean(VennChartsUtils.createChartPerFrequencies(bean, 10));
	//			chartPortlet.setChartReportBean(VennChartsUtils.createChartPerFrequencies(bean, 6));
				
				List<LinkedHashMap<String, Double>> chartsHM = VennChartsUtils.getBudgetProjectsForIntersection(country, bean.getVennGraphBeanVO());
				
				Integer chartIdx = 0;
				for (LinkedHashMap<String, Double> chartHM : chartsHM) {
					System.out.println("frequencies");
					chartCP.setChartReportBean(VennChartsUtils.createChartWithHashMap(chartHM, 10, chartIdx, "#003399"));
					chartPortlet.setChartReportBean(VennChartsUtils.createChartWithHashMap(chartHM, 6, chartIdx, "#003399"));
					
					String cp = VennChartsUtils.createReport(chartCP);
					String portlet = VennChartsUtils.createReport(chartPortlet);
					
					chartIdx++;
					
					/** saving chart **/
					LinkedHashMap<String, VennChartBeanVO> chartHashMap = new LinkedHashMap<String, VennChartBeanVO>();
					VennChartBeanVO chartBeanVO = new VennChartBeanVO();
					chartBeanVO.setChart(chartPortlet);
					
					VennChartsUtils.setIframes(cp, portlet, servletType, "", chartBeanVO);
					chartHashMap.put("frequencies", chartBeanVO);
					results.put("frequencies_" + key, chartHashMap);
				}
			}
			
			else if( chartType.toLowerCase().equals("bydonors")) {
	
	
				LinkedHashMap<String,LinkedHashMap<String, Double>> donors = VennUtils.calculateDonorsTotalAndintersections(country, bean.getVennGraphBeanVO());
				int chartIdx = 0;	
				LinkedHashMap<String, VennChartBeanVO> chartHashMap = new LinkedHashMap<String, VennChartBeanVO>();
				for (String donor : donors.keySet()) {
	//				List<VennIframeVO> partialResult = new ArrayList<VennIframeVO>();
	
					LinkedHashMap<String, Double> fundingModalities = donors.get(donor);
			
					chartCP = VennChartsUtils.setInfoBarChart("Total amount of Budget " + donor, "Budget (mn)", false, "45", centralMultipleChartWidth , centralMultipleChartHeigth);
					chartPortlet = VennChartsUtils.setInfoBarChart("Total amount of Budget " + donor, "Budget (mn)", false, "45", portletChartWidth, portletChartHeigth);
					chartCP.setChartReportBean(VennChartsUtils.createChartWithHashMap(fundingModalities, 10, chartIdx, "#003300"));		
					chartPortlet.setChartReportBean(VennChartsUtils.createChartWithHashMap(fundingModalities, 6, chartIdx, "#003300"));	
					
					String cp = VennChartsUtils.createReport(chartCP);
					String portlet = VennChartsUtils.createReport(chartPortlet);
					
//					partialResult.add(VennChartsUtils.setIframes(cp, portlet, servletType, donor));
					/** saving chart **/
					VennChartBeanVO chartBeanVO = new VennChartBeanVO();
					chartBeanVO.setChart(chartPortlet);
					chartBeanVO.setText(donor);
					VennChartsUtils.setIframes(cp, portlet, servletType, "", chartBeanVO);
					chartHashMap.put(donor, chartBeanVO);
		

					chartIdx++;
				}
				
				/** saving charts **/
				results.put("bydonors_" + key, chartHashMap);
				
//				results.put("frequencies_" + key, chartBeanVO);
//				results.add(partialResult);
			}
			
			// Chart Per Categories

			else if( chartType.toLowerCase().equals("bycategories")) {
				LinkedHashMap<String, VennChartBeanVO> chartHashMap = new LinkedHashMap<String, VennChartBeanVO>();
				LinkedHashMap<String,LinkedHashMap<String, Double>> donors = VennUtils.calcultateCategoriesTotalAndintersections(country, bean.getVennGraphBeanVO(), codecDao);
				int chartIdx = 0;	
//				List<VennIframeVO> partialResult = new ArrayList<VennIframeVO>();
				for (String donor : donors.keySet()) {
	//				List<VennIframeVO> partialResult = new ArrayList<VennIframeVO>();
	
					LinkedHashMap<String, Double> fundingModalities = donors.get(donor);
			
					chartCP = VennChartsUtils.setInfoBarChart("Total amount of Budget " + donor, "Budget (mn)", false, "45", centralMultipleChartWidth , centralMultipleChartHeigth);
					chartPortlet = VennChartsUtils.setInfoBarChart("Total amount of Budget " + donor, "Budget (mn)", false, "45", portletChartWidth, portletChartHeigth);
					chartCP.setChartReportBean(VennChartsUtils.createChartWithHashMap(fundingModalities, 10, chartIdx, "#000CCC"));				
					chartPortlet.setChartReportBean(VennChartsUtils.createChartWithHashMap(fundingModalities, 6, chartIdx, "#000CCC"));	
					
					String cp = VennChartsUtils.createReport(chartCP);
					String portlet = VennChartsUtils.createReport(chartPortlet);
					
//					partialResult.add(VennChartsUtils.setIframes(cp, portlet, servletType, donor));
					
					/** saving chart **/
					VennChartBeanVO chartBeanVO = new VennChartBeanVO();
					chartBeanVO.setChart(chartPortlet);
					chartBeanVO.setText(donor);
					VennChartsUtils.setIframes(cp, portlet, servletType, "", chartBeanVO);
					chartHashMap.put(donor, chartBeanVO);

					chartIdx++;
				}
				
				/** saving charts **/
				results.put("bycategories_" + key, chartHashMap);
//				results.add(partialResult);
			}
			
			// chart By Donor and Founding Modality
			else if (chartType.toLowerCase().equals("byfunding")) {
				LinkedHashMap<String, VennChartBeanVO> chartHashMap = new LinkedHashMap<String, VennChartBeanVO>();
				List<ChartWizardBean> chartsCP = new ArrayList<ChartWizardBean>();
				List<ChartWizardBean> chartsPortlet = new ArrayList<ChartWizardBean>();
				
				LinkedHashMap<String,LinkedHashMap<String, Double>> donors = VennUtils.calculateDonorsAndFunding(country);
				int chartIdx = 0;	
				List<VennIframeVO> partialResult = new ArrayList<VennIframeVO>();
				for (String donor : donors.keySet()) {
	//				List<VennIframeVO> partialResult = new ArrayList<VennIframeVO>();
	
					HashMap<String, Double> fundingModalities = donors.get(donor);
			
					chartCP = VennChartsUtils.setInfoBarChart("Total amount of Budget " + donor, "Budget (mn)", false, "45", centralMultipleChartWidth , centralMultipleChartHeigth);
					chartPortlet = VennChartsUtils.setInfoBarChart("Total amount of Budget " + donor, "Budget (mn)", false, "45", portletChartWidth, portletChartHeigth);
					chartCP.setChartReportBean(VennChartsUtils.createChartWithHashMap(fundingModalities, 10, chartIdx, "#FF0000"));				
					chartPortlet.setChartReportBean(VennChartsUtils.createChartWithHashMap(fundingModalities, 6, chartIdx, "#FF0000"));		
					
					String cp = VennChartsUtils.createReport(chartCP);
					String portlet = VennChartsUtils.createReport(chartPortlet);
					
//					partialResult.add(VennChartsUtils.setIframes(cp, portlet, servletType, donor));
					/** saving chart **/
					VennChartBeanVO chartBeanVO = new VennChartBeanVO();
					chartBeanVO.setChart(chartPortlet);
					chartBeanVO.setText(donor);
					VennChartsUtils.setIframes(cp, portlet, servletType, "", chartBeanVO);
					chartHashMap.put(donor, chartBeanVO);
					
					chartIdx++;
				}
//				results.add(partialResult);
				
				/** saving charts **/
				results.put("byfounding_" + key, chartHashMap);
			}
			
			else if( chartType.toLowerCase().equals("byrecipient")) {
				
	
				LinkedHashMap<String,LinkedHashMap<String, Double>> donors = VennUtils.calculateReciepientTotalAndintersections(country);
				int chartIdx = 0;	
				List<VennIframeVO> partialResult = new ArrayList<VennIframeVO>();
				for (String donor : donors.keySet()) {
	//				List<VennIframeVO> partialResult = new ArrayList<VennIframeVO>();
	
					LinkedHashMap<String, Double> fundingModalities = donors.get(donor);
			
					chartCP = VennChartsUtils.setInfoBarChart("Total amount of Budget " + donor, "Budget (mn)", false, "45", centralMultipleChartWidth , centralMultipleChartHeigth);
					chartPortlet = VennChartsUtils.setInfoBarChart("Total amount of Budget " + donor, "Budget (mn)", false, "45", portletChartWidth, portletChartHeigth);
					chartCP.setChartReportBean(VennChartsUtils.createChartWithHashMap(fundingModalities, 10, chartIdx, "#0000CC"));				
					chartPortlet.setChartReportBean(VennChartsUtils.createChartWithHashMap(fundingModalities, 6, chartIdx, "#0000CC"));	
					
					String cp = VennChartsUtils.createReport(chartCP);
					String portlet = VennChartsUtils.createReport(chartPortlet);
					
					partialResult.add(VennChartsUtils.setIframes(cp, portlet, servletType, donor));
					

					chartIdx++;
				}
//				results.add(partialResult);
			}
		}
		
		LOGGER.info(bean.getVennChartsBeanVO().keySet());
		
		return results;
	}
	
	/*** TODO: that are all to be changed **/

	
	
	
	
	
	
	private ChartBean createChartPerDacCategories(VennBeanVO bean, int fontsize) {
		ChartBean chartCB = new ChartBean();
		
		HashMap<String, Double> hashMap = VennUtils.calculateCategories(bean);
		chartCB.setSetScripted("setScripted1");
		chartCB.setSrcScripted("srcScripted1");
		
		ChartBeanValues chartValues = new ChartBeanValues();
		List<YSerieBean> series = new ArrayList<YSerieBean>();
		YSerieBean total = new YSerieBean();
		total.setLabel("Budget (mn)");

		List<String> xLabelsTanslated = getChartLabelsDacCategories(hashMap);
		List<String> xLabels = VennChartsUtils.getChartLabels(hashMap);
		total.setValues(VennChartsUtils.getChartValues(hashMap, xLabels));
		total.setColor(BirtUtil.convertHextToRGB("#FF0000"));
		series.add(total);
	
		chartValues.setxValues(xLabelsTanslated);
		chartValues.setySeries(series);
		chartCB.setChartValues(chartValues);

		chartCB.setFormatChartVo(VennChartsUtils.setInfoFormatLineChart(true, fontsize));

		return chartCB;
	}
	
	private List<String> getChartLabelsDacCategories(HashMap<String, Double> hashMap) {
		List<String> labels = new ArrayList<String>();
		for (String key : hashMap.keySet()) {
			// pass lanuage
			String l = codecDao.getLabelFromCodeCodingSystem(key + "000", "DAC", "0", "EN");
			if ( l.length() > 15 )
				labels.add(l.substring(0, 14));
			else
				labels.add(l);
			
		}
		return labels;
	}
	


	
	public List<String> createVennMap(VennBeanVO vennBean, String geoDataType, String geoLabel, int intervals, String minColor, String maxColor, boolean showBaseLayer) throws FenixGWTException {
		
		HashMap<String, Double> projections = new HashMap<String, Double>();
		
		for (String key  : vennBean.getVennCountryBeanVO().keySet()) {
			VennCountryBeanVO countryBean = vennBean.getVennCountryBeanVO().get(key);
			for(VennProjectsBean intersection : countryBean.getAllIntersections()) {
				if ( !intersection.getProjectsRawRows().isEmpty()) {
					getNumberOfProjects(projections, intersection.getProjectsGEORawRows(), intersection.getHeadersGEO());
				}
			}
		}
		
		String layerCode = geoDataType;
		
		
//		// find layer code

//		
//		// layer values provider
		LayerValuesProviderImpl provider = new LayerValuesProviderImpl(layerCode);
		
		//geo header idx
		
		

		
		// create GeoPair
		for(String projection : projections.keySet()) {
			GeoPairImpl geoPair = new GeoPairImpl(projection, projections.get(projection));
			provider.addPair(geoPair);
		}


		for (GeoPair g : provider.getPairs())
			LOGGER.info(g.getFID() + ", " + g.getValue());
		

		return createMap(vennBean, provider, geoLabel, intervals, minColor, maxColor, showBaseLayer);
	}
	
	private void getNumberOfProjects(HashMap<String, Double> projections, List<List<String>> projectsData, List<GridHeaderVO> headers) {
		Integer geoIdx = 0;
		for(GridHeaderVO header : headers ) {
			geoIdx++ ;
			if ( header.getDataType().toLowerCase().equals("featureCode")) {
				break;
			}			
		}
		
		for(List<String> project : projectsData) {
			Double count = new Double(0);
			if ( projections.containsKey(project.get(geoIdx)) ){
				count = projections.get(project.get(geoIdx));
				count++;
			}
//			LOGGER.info("project.get(geoIdx): " + project.get(geoIdx) + " | " + count);
			projections.put(project.get(geoIdx), count);	
		}
		
		LOGGER.info("MAP PROJECTION: " + projections);
	}
	
	
	private List<String> createMap(VennBeanVO vennBean, LayerValuesProvider provider, String mapTitle, int intervals, String minColor, String maxColor, boolean showBaseLayer) {
		
		// initiate result
		List<String> paths = new ArrayList<String>();
		
		// GAUL level
		int gaulLevel = 0;
		if (provider.getLayerCode().charAt(provider.getLayerCode().length() - 1) == '1')
			gaulLevel = 1;
		else if (provider.getLayerCode().charAt(provider.getLayerCode().length() - 1) == '2')
			gaulLevel = 2;
		
		LOGGER.info(gaulLevel);
		
		GeometryResolver geometryResolver = new DBFLGeometryResolver(provider);
		((DBFLGeometryResolver)geometryResolver).setLayerGaulUtils(layerGaulUtils);
		
		FeatureBuilder featureBuilder = new QuickFeatureBuilder(geometryResolver);

		QuickFeatureCollection fc = new QuickFeatureCollection(provider, featureBuilder);
		QuickMap quickMap = new QuickMap();
		quickMap.setWidth(WIDTH);
		quickMap.setHeight(HEIGHT);
		quickMap.setIntervals(intervals);
		
		quickMap.setMaxColor(OLAPUtils.getUserColor(minColor)); 
		quickMap.setMinColor(OLAPUtils.getUserColor(maxColor)); 
		
		BufferedImage quickimage = quickMap.render(fc);
		
		List<Range> ranges = quickMap.getLastRanges();
		
		CQLFilter gaul1Filters = getFilter(provider, 1);
		
		try {
			GeoView geoViewG0;
			
			BoundingBox bbox = quickMap.getLastBbox();

			// building the lower layers
			List<GeoView> lowerLayers = new ArrayList<GeoView>();									
			if (showBaseLayer) {
				geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_baseraster"));
				lowerLayers.add(geoViewG0);
			}
			geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_gaul1"));
			lowerLayers.add(geoViewG0);

			// building the upper layers
			List<GeoView> upperLayers = new ArrayList<GeoView>();									

			// the style can be created once for all
			Long layerId = olapDao.findLayerIdFromLayerCode(provider.getLayerCode());
			String admName = GaulUtils.getMainLabelFieldname(gaulLevel);
			String labels = createLabelStyle(layerId, admName, "000000");

			geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_gaul1"));
			geoViewG0.setStyleName(labels);		
			geoViewG0.setCqlFilter(gaul1Filters);
			upperLayers.add(geoViewG0);			
			geoViewG0 = new GeoView(wmsMapProviderDao.findByCode("layer_gaul0"));
			upperLayers.add(geoViewG0);			

			BufferedImage lowerImage = getLayerImage(bbox, lowerLayers);
			BufferedImage upperImage = getLayerImage(bbox, upperLayers);
			
			// create additional layers
			List<BufferedImage> images = new ArrayList<BufferedImage>();
			
			images.add(lowerImage);
			images.add(quickimage);
			images.add(upperImage);
			
			// add transparencies
			List<Float> transparencies = new ArrayList<Float>();
			for (int i = 0 ; i < images.size() ; i++)
				transparencies.add(new Float(1.0));
			
			// merge and save
			BufferedImage merged = ImageMerger.merge(images, transparencies);
			String mergedFileName = String.valueOf(Math.random()) + "_MERGED.png";
			File mergedFile = new File(Setting.getSystemPath() + File.separator + "venn" + File.separator +"temp" + File.separator + mergedFileName);
			ImageIO.write(merged, "png", mergedFile);

			StringBuffer table = createImageTableWithSideLegend(vennBean, mergedFileName, ranges, mapTitle);
			String mapPath = createImageWithLegend(table);
			paths.add(mapPath);
			
		} catch (IOException ex) {
			LOGGER.error(ex);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (FenixGWTException e) {
			LOGGER.error(e);
		}
		
		// return result
		return paths;
	}


	private StringBuffer createImageTableWithSideLegend(VennBeanVO vennBean, String mapPath, List<Range> ranges, String mapTitle) {
		StringBuffer sb = new StringBuffer();
//		DecimalFormat twoDigit = new DecimalFormat("#,##0.00");
		DecimalFormat twoDigit = new DecimalFormat("#");
		sb.append("<table width='100%'>");
		
		sb.append("<tr><td colspan = '2' align='center'><font size='6'>" + mapTitle + "</font></td>");
		
		Range firstRange = ranges.get(0);
		String firstRGB = Integer.toHexString(firstRange.color.getRGB());
		firstRGB = firstRGB.substring(2, firstRGB.length());
		sb.append("<tr><td rowspan = '" + ranges.size() + "'><img src='" + mapPath + "'/></td>");
		sb.append("<td><b>Legend</b></td></tr>");
		
		if (ranges.size() > 1) {
			for (int i = 1 ; i < ranges.size() ; i++) {
				Range r = ranges.get(i);
				String rgb = Integer.toHexString(r.color.getRGB());
				rgb = rgb.substring(2, rgb.length());
				sb.append("<tr>");
				sb.append("<td width='20%' bgcolor='#" + rgb + "'><font size='3'>" + twoDigit.format(r.min) + " to " + twoDigit.format(r.max) + "</font></td>");
//				sb.append("<td width='20%' bgcolor='#" + rgb + "'><font size='4'> <center>" + twoDigit.format(r.max) + " </center> </font></td>");
				sb.append("</tr>");
			}
		}
		
		sb.append("</table>");
//		sb.append(createFiltersLegend(p, filters).toString());
		return sb;
	}
	
	
	private String createImageWithLegend(StringBuffer table) {
		String path = String.valueOf(Math.random()) + "_OLAP_MAP.html";
		File file = new File(Setting.getSystemPath() + File.separator + "venn" + File.separator +"temp" + File.separator + path);

		try {
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(table.toString().getBytes());
			stream.close();
		} catch (FileNotFoundException e) {
			throw new FenixException(e.getMessage());
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}
		return path;
	}
	
	
	private CQLFilter getFilter(LayerValuesProvider provider, int gaulLevel) {
		List<CQLFilter> filters = new ArrayList<CQLFilter>();
		
		for (GeoPair geoPair : provider) {
			filters.add(getFilter(geoPair.getFID(), gaulLevel));
		}

		return CQLFilterOp.or(filters);
	}
	
	public CQLFilter getFilter(String regionCode, int gaulLevel) {
		if(gaulLevel==0)
			return GaulCQLFilterFactory.createGaul0Is(regionCode);
		else if(gaulLevel==1)
			return GaulCQLFilterFactory.createGaul1Is(regionCode);
		else 
			throw new IllegalArgumentException("unknown GAUL level");
	}
	
	private BufferedImage getLayerImage(BoundingBox bbox, List<GeoView> gvlist) {
		// create the map builder
		WMSMapRetriever mm = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
		mm.setHeight(HEIGHT);
		mm.setWidth(WIDTH);
		mm.setBoundingBox(bbox);

		for (GeoView geoView : gvlist) {
			System.out.println("GEOVIEWWWW");
			System.out.println(geoView.getWmsMapProvider().getGetMapUrl());
			System.out.println(geoView.getWmsMapProvider().getLayerName());
			System.out.println(geoView.getStyleName());
			System.out.println(geoView.getCqlFilter());
			System.out.println(geoView.getOpacity());
			geoView.setOpacity(new Float(1));
			mm.addLayer(geoView);			
		}

		// create the image
		BufferedImage image = mm.getMapImage();
		return image;
	}
	
	public String createLabelStyle(long layerId, String fieldName, String scolor) throws FenixGWTException {

		Color color = ColourUtil.hex2colour(scolor);
		String sld = TextSymbUtil.createLabelStyle(fieldName, color);
		String stylename = "label_" + layerId + "_"+fieldName.toLowerCase() +"_" + ColourUtil.colour2string(color);

		WMSMapProvider mp = wmsMapProviderDao.findById(layerId);
		String layername = mp.getLayerName().replace(":", "_");
		GeoServer geoServer = ((InternalWMSLayer)mp).getGeoServer();

		GeoserverIO gsio = new GeoserverIO();
		if( ! gsio.existsStyle(geoServer, stylename)) {
//			LOGGER.info("Creating style " + stylename);			
			GeoserverPublisher.publishLayerStyle(geoServer, stylename, sld, stylename);
		}
		
		return stylename;
	}
	
	
	public VennConfigurationBeanVO getConfiguration(String xml, Boolean isFile) {
		if ( isFile )
			return getConfigurationByFile(xml);
		// retruieve info from 
		return null;
	}
	
	private VennConfigurationBeanVO getConfigurationByFile(String xml) {
		LOGGER.info("get configuration");
		VennConfigurationBeanVO configurationBean = new VennConfigurationBeanVO();
		
		try {
			
			FileInputStream is = new FileInputStream(new File(dir + File.separator + xml + ".xml"));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			
			db = dbf.newDocumentBuilder();
			Document document;
			document = db.parse(is);

			Element element = document.getDocumentElement();
			
			// get countries
			getXMLCountries(element, configurationBean);
			
			// get sets 
			NodeList typeNode = element.getElementsByTagName("type");			
			for (int j = 0; j < typeNode.getLength(); j++) {
				Element typeElement = (Element) typeNode.item(j);
				String type = typeElement.getAttribute("type");
			
				getXMLSet(element, configurationBean, "setA");
				getXMLSet(element, configurationBean, "setB");
				getXMLSet(element, configurationBean, "setC");
				
				configurationBean.setType(type);
			}		
			
			
		} catch (FileNotFoundException e) {
			LOGGER.error(e);
		} catch (ParserConfigurationException e) {
			LOGGER.error(e);
		} catch (SAXException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		
		LOGGER.info("countries:  " + configurationBean.getCountries());
		
		LOGGER.info("setA:  " + configurationBean.getSetA().getOrganizationType() + " | " + configurationBean.getSetA().getOrganization() + " | " + configurationBean.getSetA().getStrategies());
		LOGGER.info("setB:  " + configurationBean.getSetB().getOrganizationType() + " | " + configurationBean.getSetB().getOrganization() + " | " + configurationBean.getSetB().getStrategies());
		LOGGER.info("setC:  " + configurationBean.getSetC().getOrganizationType() + " | " + configurationBean.getSetC().getOrganization() + " | " + configurationBean.getSetC().getStrategies());

		return configurationBean;
	}
	
	private void getXMLCountries(Element element, VennConfigurationBeanVO configurationBean) {
		// get countries 
		NodeList countriesNode = element.getElementsByTagName("countries");			
		for (int j = 0; j < countriesNode.getLength(); j++) {
			Element countryElement = (Element) countriesNode.item(j);
			NodeList countryNode = countryElement.getElementsByTagName("country");
			List<String> countries = new ArrayList<String>();
			for (int k = 0; k < countryNode.getLength(); k++) {
				Element country = (Element) countryNode.item(k);
				String countryCode = country.getAttribute("countryCode");
				countries.add(countryCode);
			}		
			configurationBean.setCountries(countries);
		}			
	}
	
	private void getXMLSet(Element element, VennConfigurationBeanVO configurationBean, String set) {
		VennSetBeanVO vennSetBean = new VennSetBeanVO();
		// get a set 
		NodeList setNode = element.getElementsByTagName(set);	
		String organizationType = "";
		String organization = "";
		List<String> strategies = new ArrayList<String>();
		for (int j = 0; j < setNode.getLength(); j++) {
			Element setElement = (Element) setNode.item(j);
			organizationType = setElement.getAttribute("organizationType");
			organization = setElement.getAttribute("organization");
			
			// get strategies
			NodeList strategiesNode = setElement.getElementsByTagName("strategies");			
			for (int k = 0; k < strategiesNode.getLength(); k++) {
				Element strategiesElement = (Element) strategiesNode.item(k);
				NodeList strategieNode = strategiesElement.getElementsByTagName("strategy");					
				for (int z = 0; z < strategieNode.getLength(); z++) {
					Element strategieElement = (Element) strategieNode.item(z);
					String strategyCode = strategieElement.getAttribute("strategyCode");
					strategies.add(strategyCode);
				}		
			}
		}	
		
		vennSetBean.setOrganizationType(organizationType);
		vennSetBean.setOrganization(organization);
		vennSetBean.setStrategies(strategies);		
		
		// which set
		if ( set.equals("setA"))
			configurationBean.setSetA(vennSetBean);
		else if ( set.equals("setB"))
			configurationBean.setSetB(vennSetBean);
		else if ( set.equals("setC"))
			configurationBean.setSetC(vennSetBean);
		
	}
	
	
	
	
	
	
	public String vennReport(VennReportBeanVO vennReportBean) {
		// save the images to export to the report
		
		/** TODO: switch based on the kind of report to do **/
		// if venn has to be exported export venn diagram
		VennGraphBeanVO vennGraphBeanVO = vennReportBean.getVennGraphReportBean().getVennGraphBeanVO();
		String vennImagePath = vennGraphBeanVO.getVennImages().get("normal");
		
		vennGraphBeanVO.getVennImages().put("normal", saveVennOnBirt(vennImagePath));
		
		
		CreateVennReport report = new CreateVennReport(vennReportBean);
		return report.createReport();
	}
	
	
	private String saveVennOnBirt(String image) {	
//		LOGGER.info("imageName: |" + image +"|" );
		
		String imageFile = Setting.getSystemPath() + File.separator + "venn" + File.separator + "temp" + File.separator + image;
		
		String nameFile = BirtUtil.randomFileExport() + ".png";
		
//		LOGGER.info("imageFilePath:" + imageFile + "|");
//		LOGGER.info(nameFile);
		
		String outFileName = Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "venn" + File.separator + "temp" + File.separator + nameFile;
		
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(imageFile);
			out = new FileOutputStream(outFileName);
			// Transfer bytes from in to out
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null)
					in.close();
				if(out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 

		
		return nameFile;
	}
	
	
	
	public LinkedHashMap<String, VennReportTableValuesBeanVO> createSummaryReportTables(VennBeanVO bean) {
		LinkedHashMap<String, VennReportTableValuesBeanVO> result = new LinkedHashMap<String, VennReportTableValuesBeanVO>();
		
		LOGGER.info("bean.getVennCountryBeanVO().keySet(): " + bean.getVennCountryBeanVO().keySet());
		
		
		/** TODO: HANDLE THE MULTICOUNTRY **/
		/** MAKE A FASTER ALGORTHM **/
		LOGGER.info("STARTING TIMING: " );
//		for(int j=0; j < 10; j ++) {
			for (String key  : bean.getVennCountryBeanVO().keySet()) {
//				LOGGER.info("intermediate");
				VennReportTableValuesBeanVO table = new VennReportTableValuesBeanVO();
				
				VennCountryBeanVO country = bean.getVennCountryBeanVO().get(key);
				
				// getting all the categories
				LinkedHashMap<String,LinkedHashMap<String, Double>> categories = VennUtils.calcultateCategoriesTotalAndintersections(country, bean.getVennGraphBeanVO(), codecDao);
/*		
				LinkedHashMap<String,LinkedHashMap<String, Double>> numberOfProjects = VennUtils.calculateNumberOfProjectsByCategory(country, bean.getVennGraphBeanVO(), codecDao);
				
				LinkedHashMap<String,LinkedHashMap<String, Double>> donors = VennUtils.calculateDonorsByCategory(country, bean.getVennGraphBeanVO(), codecDao);
				
//				LOGGER.info("SUMMARY");
//				LOGGER.info("categories: " + categories.get("Total"));
//				LOGGER.info("numberOfProjects: " + numberOfProjects.get("Total"));
//				LOGGER.info("donors: " + donors);
		
//				LOGGER.info("END SUMMARY");
				
				
				// parsing the various categories with the numberOfProjects, and the various donors
				List<List<String>> tableValues = new ArrayList<List<String>>();
				tableValues.add(buildCategoriesProjectsDonorHeaders());
				for(String categoryKey : categories.get("Total").keySet()){
//					LOGGER.info("categoryKey: " + categoryKey); 
					List<String> row = new ArrayList<String>();
					// add category 
					row.add(categoryKey);
					
					// add number of projecst		
					try {
						if ( numberOfProjects.get("Total").containsKey(categoryKey) ) {
//							LOGGER.info("numberOfProjects.get(Total): " + numberOfProjects.get("Total").get(categoryKey));
							Double value = numberOfProjects.get("Total").get(categoryKey);
							row.add(String.valueOf(value.intValue()));
						}
						else {
							row.add("");
						}
					}
					catch (Exception e) {
					
					}
	
					
					// add donors
					int i=0;
					StringBuffer donorsString = new StringBuffer();
					if ( donors.containsKey(categoryKey)) {
						for(String donorKey : donors.get(categoryKey).keySet()){
							donorsString.append(donorKey);
							if ( i > 3 || i >= donors.get(categoryKey).size())
								break;
							else 
								donorsString.append(", ");
							i++;
						}
					}
					row.add(donorsString.toString());
					
					
					tableValues.add(row);
					
				}
				
//				LOGGER.info("tableList: tableListtableList" + tableValues);
				table.setTableValues(tableValues);
				result.put("categories", table);*/
//			}
		}
		
		LOGGER.info("END TIMING");

//		System.out.println("reult hashmap: " + result);
		return result;
	}

	private List<String> buildCategoriesProjectsDonorHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("Sector");
		headers.add("Projects Number");
		headers.add("Most Active Donors");
		return headers;
		
	}
	

	public HashMap<String, HashMap<String, String>> getStrategicObjectives(HashMap<String, String> codes, String language) {
		HashMap<String, HashMap<String, String>> result = new HashMap<String, HashMap<String,String>>();
		for(String code : codes.keySet() ) {
			HashMap<String, String> hashMap = vennDao.findDacCodesReference(code, language);
			if ( hashMap != null) {
				result.put(code, hashMap);
			}
		}
		return result;
	}
	
	public void setVennDao(VennDao vennDao) {
		this.vennDao = vennDao;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setOlapDao(OLAPDao olapDao) {
		this.olapDao = olapDao;
	}

	public void setLayerGaulUtils(LayerGaulUtils layerGaulUtils) {
		this.layerGaulUtils = layerGaulUtils;
	}

	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

	public void setViewDao(ViewDao viewDao) {
		this.viewDao = viewDao;
	}
	





	
}
