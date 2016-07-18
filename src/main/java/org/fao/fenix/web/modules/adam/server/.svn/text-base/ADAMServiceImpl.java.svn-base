package org.fao.fenix.web.modules.adam.server;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.ProtocolException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.coding.CodingHierarchy;
import org.fao.fenix.core.domain.coding.CodingSystem;
import org.fao.fenix.core.domain.dataset.CustomDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.QuantitativeCoreContent;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.domain.olap.OLAPBean;
import org.fao.fenix.core.domain.olap.OLAPFilter;
import org.fao.fenix.core.domain.olap.OLAPParameters;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.domain.security.FenixPermission;
import org.fao.fenix.core.domain.security.FenixSecurityConstant;
import org.fao.fenix.core.jfreechart.JFreeChartConnector;
import org.fao.fenix.core.jfreechart.JFreeChartConstants;
import org.fao.fenix.core.jfreechart.JFreeChartSettings;
import org.fao.fenix.core.olap.chart.OLAPChartConfiguration;
import org.fao.fenix.core.olap.chart.OLAPChartType;
import org.fao.fenix.core.persistance.adam.ADAMDao;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.CustomDatasetDao;
import org.fao.fenix.core.persistence.CustomDatasetJDBC;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.olap.FieldParserComparator;
import org.fao.fenix.core.persistence.olap.OLAPDao;
import org.fao.fenix.core.persistence.olap.OLAPHtml;
import org.fao.fenix.core.persistence.resourceview.ResourceViewDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.map.mapretriever.LegendRetriever;
import org.fao.fenix.map.mapretriever.MapRetriever;
import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.model.Donor;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.model.FAOSectorMatrixModel;
import org.fao.fenix.web.modules.adam.common.services.ADAMService;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.adam.common.vo.venn.matrix.ADAMVennDonorsVO;
import org.fao.fenix.web.modules.adam.common.vo.venn.matrix.ADAMVennMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.venn.matrix.ADAMVennRecipientsStatedPrioritiesVO;
import org.fao.fenix.web.modules.adam.common.vo.venn.matrix.ADAMVennRecipientsVO;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.server.utils.CodingVoConverter;
import org.fao.fenix.web.modules.core.client.utils.FormatValues;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.common.vo.JoinDatasetVO;
import org.fao.fenix.web.modules.map.server.MapUtils;
import org.fao.fenix.web.modules.map.server.util.MapViewUtils;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.poi.server.excel.OLAPExcel;
import org.fao.fenix.web.modules.table.server.TableExcel;
import org.jfree.chart.plot.PlotOrientation;
import org.springframework.core.io.Resource;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ADAMServiceImpl extends RemoteServiceServlet implements ADAMService {

	private final static Logger LOGGER = Logger.getLogger(ADAMServiceImpl.class);

//	public final static int SMALL_WIDTH = 545;

//	public final static int SMALL_WIDTH = 435;
//
////	private final static int SMALL_WIDTH = 375;
//
//	private final static int SMALL_HEIGHT = 195;
//
////	private final static int BIG_WIDTH = 950;
//
//	private final static int BIG_WIDTH = 940;
//
//	private final static int BIG_HEIGHT = 450;

//	private final static int MEDIUM_WIDTH = 430;

	private final static int MEDIUM_WIDTH = 750;

	private final static int MEDIUM_HEIGHT = 270;

	private ADAMDao adamDao;

	private DatasetDao datasetDao;

	private CustomDatasetJDBC customDatasetJDBC;

	private CodecDao codecDao;

	private CustomDatasetDao customDatasetDao;

	private FenixPermissionManager fenixPermissionManager;

	private String dir;

	private OLAPDao olapDao;

	private ResourceViewDao resourceViewDao;

    private UrlFinder urlFinder;

	private WMSMapProviderDao wmsMapProviderDao;

	TableExcel tableExcel;

	public ADAMServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private void settingADAMResultVO(ADAMResultVO rvo, ADAMQueryVO qvo){
		rvo.setResourceType(qvo.getResourceType());
		rvo.setDescription(qvo.getDescription());
		rvo.setTitle(qvo.getTitle());
		rvo.setQuestion(qvo.getQuestion());
	}


	public ADAMResultVO askADAM(ADAMQueryVO qvo) {

		if(qvo.getCurrentView()!=null)
			LOGGER.info("######################## ASK ADAM current VIEW " + qvo.getCurrentView().name());


		// ADDING SO, OR FILTERS
		if ( qvo.getFilterFrameworkType() != null ) {
			ADAMBoxContent addFilter = ADAMBoxContent.valueOf(qvo.getFilterFrameworkType());
			switch (addFilter) {
				case FILTER_ALL_SO: qvo = addSOFilter(qvo); break;
				case FILTER_BY_SO: qvo = addSOFilter(qvo); break;
			}
		}


		// outputs
		ADAMResultVO rvo = new ADAMResultVO();

		// set ADAMResultVO
		settingADAMResultVO(rvo, qvo);

		// find SQL tablenames from Resource's codes
		//if(qvo.getTableNames().isEmpty()){
	     List<String> tablenames = customDatasetDao.findTablenamesFromCodes(qvo.getDatasetCodes());
			
	     //This is for when FAO-Related sub sectors is selected as a sector ---> query fao_sub_sectors_data View
			if(!qvo.getAggregatedViewNames().isEmpty() && qvo.getAggregatedViewNames().get(0)!=null){
				qvo.setTableNames(qvo.getAggregatedViewNames());
			}
			else {
				if( tablenames.isEmpty()) 
					qvo.setTableNames(qvo.getDatasetCodes());

				else 
					qvo.setTableNames(tablenames);
			}
			
			LOGGER.info("------------------ DATASET: "+qvo.getTableNames().get(0));
			
		//}


		// create the appropiate object
		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());

		/**
		 *  TODO: CHECK ALL THE TABLES quantity columns
		 */

		switch (c) {
			case BAR: rvo = createChartBar(qvo, rvo); break;
			case BAR_STACK_OR_DONOR: rvo = createChartBarStackOR_Donor(qvo, rvo); break;
			case BAR_STACK_DAC_DONOR: rvo = createChartBarStackDAC_Donor(qvo, rvo); break;
			case BAR_STACK_SECTOR_COMPARISON: rvo = createChartBarStackComparisonSwitcher(qvo, rvo); break;
			case BAR_WITH_CATEGORIES: rvo = createChartBarSeries(qvo, rvo); break;
			case PIE: rvo = createChartPie(qvo, rvo); break;
			case TIMESERIE: rvo = createTimeSerieSwitcher(qvo, rvo); break;
			case SCATTER: rvo = createScatterSwitcher(qvo, rvo); break;
			case MAP : break;
			//case MAP_COUNTRIES_BY_DONOR_GOOGLE : createGoogleDataset(qvo, rvo); break;
			case MAP_COUNTRIES_BY_DONOR_GOOGLE : createGoogleMapSwitcher(qvo, rvo); break;
			case TOP_DONORS_TABLE: rvo = createTable(qvo, rvo); break;
			case TOP_DONORS_TABLE_FILTERED: rvo = createTable(qvo, rvo); break;
			case TOP_SECTORS_DONOR_VIEW_TABLE: rvo = createTable(qvo, rvo); break;
			case TOP_SECTORS_DONOR_COUNTRY_VIEW_TABLE: rvo = createTable(qvo, rvo); break;
			case TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE: rvo = createTable(qvo, rvo); break;
			case TOP_AGRICULTURAL_SECTORS_DONOR_COUNTRY_VIEW_TABLE: rvo = createTable(qvo, rvo); break;
			case TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE: rvo = createTable(qvo, rvo); break;
			case TOP_COUNTRIES_BY_DONOR: rvo = createTable(qvo, rvo); break;
			case FAVOURITE_PURPOSES_QUESTIONS_VIEW: rvo = createTable(qvo, rvo); break;
			case CONTRIBUTION_TABLE: rvo = createTable(qvo, rvo); break;
			case IMPLEMENTING_AGENCIES_TABLE: rvo = createTable(qvo, rvo); break;			
			case OLAP : break;
			case PROJECTS_LIST_CRS: rvo = getProjects(qvo, rvo); break;
			case ROWS_COUNT: rvo = getTotalRows(qvo, rvo); break;
			case DONOR_PROFILE_TABLE: rvo = createTable(qvo, rvo); break;
			case DONOR_PROCESSES_TABLE: rvo = createTable(qvo, rvo); break;
			case DONOR_ODA_TABLE: rvo = createTable(qvo, rvo); break;
			case VENN_PRIORITIES: rvo = getPriorities(qvo, rvo); break;
			case VENN_MATRIX: rvo = getVennMatrix(qvo, rvo); break;
            case GET_FILTERED_VALUES: rvo = getFilteredValues(qvo, rvo); break;
			case VIEW_MATRIX: rvo = getViewMatrix(qvo, rvo); break;
			case COMPARATIVE_ADVANTAGE: rvo = getComparativeAdvantage(qvo, rvo); break;
			

			// FPMIS
			case FPMIS_GET_COUNTRY_ID: rvo = getFPMISCodes(qvo, rvo); break;
			case FPMIS_CHART: rvo = createFPMISChart(qvo, rvo); break;

//			case FPMIS_REQUEST: rvo = getFPMISRequest(qvo, rvo); break;
		}

		// return the result bean
		rvo.setTitle(qvo.getTitle());
		rvo.setBoxColor(qvo.getBoxColor());
		rvo.setSmallWidth(qvo.getSmallWidth() + "px");
		rvo.setSmallHeight(qvo.getSmallHeight() + "px");
		rvo.setMediumWidth(String.valueOf(MEDIUM_WIDTH) + "px");
		rvo.setMediumHeight(String.valueOf(MEDIUM_HEIGHT) + "px");
		rvo.setBigWidth(qvo.getSmallWidth() + "px");
		rvo.setBigHeight(qvo.getSmallHeight() + "px");
		rvo.setOutputType(qvo.getOutputType());
		rvo.setResourceType(rvo.getResourceType());
		return rvo;
	}

	public ADAMResultVO donorProfile(ADAMQueryVO qvo) {

		// outputs
		ADAMResultVO rvo = new ADAMResultVO();

		// set ADAMResultVO
		settingADAMResultVO(rvo, qvo);

		rvo.setResourceType(qvo.getResourceType());
		rvo.setDescription(qvo.getDescription());
		rvo.setTitle(qvo.getTitle());
		rvo.setQuestion(qvo.getQuestion());


		// find SQL tablenames from Resource's codes
		qvo.setTableNames(customDatasetDao.findTablenamesFromCodes(qvo.getDatasetCodes()));


		// find SQL aggregated view names from Resource's codes
		qvo.setAggregatedViewNames(customDatasetDao.findAggregatedViewNamesFromCodes(qvo.getDatasetCodes()));

		// create the appropiate object
		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());

		switch (c) {
			case BAR: rvo = createChartBarSeries(qvo, rvo); break;
			case DONOR_PROFILE_TABLE: rvo = createTable(qvo, rvo); break;
			case DONOR_PROCESSES_TABLE: rvo = createTable(qvo, rvo); break;
			case DONOR_ODA_TABLE: rvo = createTable(qvo, rvo); break;
		}

		// return the result bean
		rvo.setTitle(qvo.getTitle());
		rvo.setBoxColor(qvo.getBoxColor());
		rvo.setSmallWidth(qvo.getSmallWidth() + "px");
		rvo.setSmallHeight(qvo.getSmallHeight() + "px");
		rvo.setMediumWidth(String.valueOf(MEDIUM_WIDTH) + "px");
		rvo.setMediumHeight(String.valueOf(MEDIUM_HEIGHT) + "px");
		rvo.setBigWidth(qvo.getSmallWidth() + "px");
		rvo.setBigHeight(qvo.getSmallHeight() + "px");
		rvo.setOutputType(qvo.getOutputType());
		return rvo;
	}

	private ADAMResultVO getTotalRows(ADAMQueryVO qvo, ADAMResultVO rvo) {
		// variables
		String sql = "";
		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());

		// build SQL
		switch (c) {
			case ROWS_COUNT:
				sql =  ADAMQueryBuilder.getRowsCountEliminatingEmptyValueRows(qvo.getTableNames().get(0), qvo.getWheres()); // ADAMQueryBuilder.getRowsCount(qvo.getTableNames().get(0), qvo.getWheres());
			break;
		}

		// query the DB
		long sqlResult = adamDao.tableQueryInteger(sql);

		switch (c) {
		case ROWS_COUNT:
			LOGGER.info("ROWS_COUNT: " + sqlResult);
			rvo.setRows(sqlResult);
			break;
		}

		return rvo;
	}

	private ADAMResultVO getProjects(ADAMQueryVO qvo, ADAMResultVO rvo) {
		// variables
		String sql = "";
		String donorCode = "";
		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());
		List<String> tableHeaders = new ArrayList<String>();
		List<List<String>> tableContents = new ArrayList<List<String>>();


		// build SQL
		switch (c) {
			case PROJECTS_LIST_CRS:
				sql = ADAMQueryBuilder.getProjectsList(qvo.getTableNames().get(0), qvo.getSelectsMap(), qvo.getWheres(), null, "EN");
			break;
		}

		// query the DB
		//LOGGER.info("start query");
		List<Object[]> sqlResult = adamDao.tableQuery(sql);
		//LOGGER.info("ended query");

		Boolean isGrouped = true;


		switch (c) {

		case PROJECTS_LIST_CRS:
			LOGGER.info("PROJECTS_LIST_CRS");
//			for(int i=0; i < sqlResult.size(); i++) {
//				for(int j=0; j < sqlResult.get(i).length; j++)
//					System.out.print(sqlResult.get(i)[j] + " | ");
//			}
//			System.out.println();

			// create results	
			tableHeaders = ADAMTableUtils.projectsHeaders();
			
			if(!qvo.getSelectsMap().containsKey("recipientname"))
				tableHeaders.remove("Recipient Country");
			
			if(!qvo.getSelectsMap().containsKey("donorname"))
				tableHeaders.remove("Resource Partner");
			
			if(!qvo.getSelectsMap().containsKey("dac_label"))
				tableHeaders.remove("Sector");
						
			// fill the result
			//rvo.setTableHTML(ADAMTableUtils.createHtmlTable(tableHeaders, tableContents, sqlResult));
			rvo.setTableHTML(ADAMTableUtils.createFixedHeaderColumnHtmlTable(tableHeaders, tableContents, sqlResult));

			rvo.setTableHeaders(tableHeaders);
			rvo.setTableContents(tableContents);

			break;
		}

		return rvo;
	}

	private ADAMResultVO createTable(ADAMQueryVO qvo, ADAMResultVO rvo) {

		// variables
		String sql = "";
		String donorCode = "";
		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());
		List<String> tableHeaders = new ArrayList<String>();
		List<List<String>> tableContents = new ArrayList<List<String>>();

		// limit query
		int limit = 10;

		int subQueryLimit = 5;

//		LOGGER.info("LIMIT: " + limit);

		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";
		String aggregationType = "SUM";

		List<String> tablenames = customDatasetDao.findTablenamesFromCodes(qvo.getDatasetCodes());

		
		//This is for when FAO-Related sub sectors is selected as a sector ---> query fao_sub_sectors_data View
		if(!qvo.getAggregatedViewNames().isEmpty() && qvo.getAggregatedViewNames().get(0)!=null){
			qvo.setTableNames(qvo.getAggregatedViewNames());
		}
		else {
			if( tablenames.isEmpty()) 
				qvo.setTableNames(qvo.getDatasetCodes());

			else 
				qvo.setTableNames(tablenames);
		}


		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}

		LOGGER.info("AGGREGATION: " + quantityColumn + " |  " + aggregationType) ;

		// build SQL
		switch (c) {
			case TOP_DONORS_TABLE:
				sql = ADAMQueryBuilder.getDonors(qvo.getTableNames().get(0), qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());
			break;
			case TOP_DONORS_TABLE_FILTERED:
				sql = ADAMQueryBuilder.getDonors(qvo.getTableNames().get(0), qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());
			break;
			case TOP_SECTORS_DONOR_VIEW_TABLE:
				sql = ADAMQueryBuilder.topSectorsDonorView(qvo.getTableNames().get(0), qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());
			break;
			case TOP_SECTORS_DONOR_COUNTRY_VIEW_TABLE:
				sql = ADAMQueryBuilder.topSectorsDonorView(qvo.getTableNames().get(0), qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());
			break;
			case TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE:
				sql = ADAMQueryBuilder.topSubSectorsView(qvo.getTableNames().get(0), qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());
			break;
			case TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE:
				sql = ADAMQueryBuilder.topSubSectorsView(qvo.getTableNames().get(0), qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());
			break;
			case TOP_AGRICULTURAL_SECTORS_DONOR_COUNTRY_VIEW_TABLE:
				sql = ADAMQueryBuilder.topSubSectorsView(qvo.getTableNames().get(0), qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());
			break;
			case TOP_COUNTRIES_BY_DONOR:
				sql = ADAMQueryBuilder.topCountries(qvo.getTableNames().get(0), qvo.getWheres(), quantityColumn, aggregationType, limit, "ADAM_RECIPIENTS");
			break;
			case FAVOURITE_PURPOSES_QUESTIONS_VIEW:
				donorCode = qvo.getWheres().keySet().toArray()[0].toString();
				sql = ADAMQueryBuilder.favouritePurposesQuestionsView(qvo.getTableNames().get(0), donorCode);
			break;
			case DONOR_PROFILE_TABLE:
				donorCode = qvo.getWheres().keySet().toArray()[0].toString();
				LOGGER.info("key = "+donorCode + " : value = "+qvo.getWheres().get(donorCode).toString());

				sql = ADAMQueryBuilder.donorProfileView(qvo.getTableNames().get(0), qvo.getWheres().get(donorCode).get(0));
			break;
			case DONOR_PROCESSES_TABLE:
				donorCode = qvo.getWheres().keySet().toArray()[0].toString();
				LOGGER.info("key = "+donorCode + " : value = "+qvo.getWheres().get(donorCode).toString());

				sql = ADAMQueryBuilder.donorProcessesView(qvo.getTableNames().get(0), qvo.getWheres().get(donorCode).get(0));
			break;
			case DONOR_ODA_TABLE:
				donorCode = qvo.getWheres().keySet().toArray()[0].toString();

				sql = ADAMQueryBuilder.donorTotalODAView(qvo.getAggregatedViewNames().get(0), qvo.getWheres().get(donorCode).toString());
			break;
			case CONTRIBUTION_TABLE:
				if(qvo.getPriorities()!=null && !qvo.getPriorities().isEmpty()) {
					CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
					sql = ADAMQueryBuilder.buildORContributionTableQuery(convtable.getTablename(), qvo.getTableNames().get(0), qvo); 
				}	
				else
					sql = ADAMQueryBuilder.buildQuery(qvo);
				break;
			case IMPLEMENTING_AGENCIES_TABLE:
				if(qvo.getPriorities()!=null && !qvo.getPriorities().isEmpty()) {
					CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
					sql = ADAMQueryBuilder.buildORTopImplementingAgenciesTableQuery(convtable.getTablename(), qvo.getTableNames().get(0), qvo); 
				}	
				else
					sql = ADAMQueryBuilder.buildQuery(qvo);
				break;
				
		}

		// query the DB
		//LOGGER.info("start query");
		List<Object[]> sqlResult = adamDao.tableQuery(sql);
		//LOGGER.info("ended query");

		Boolean isGrouped = true;

		Map<String, String> channelsLinks = new HashMap<String, String>();
		Map<String, String> dacLinks = new HashMap<String, String>();
		Map<String, String> donorsLinks = new HashMap<String, String>();
		Map<String, String> recipientsLinks = new HashMap<String, String>();

		switch (c) {

			case TOP_DONORS_TABLE:

				/*** get donors codes **/
				LinkedHashMap<String, List<Object[]>> channels = new LinkedHashMap<String, List<Object[]>>();
				LinkedHashMap<String, List<Object[]>> recipients = new LinkedHashMap<String, List<Object[]>>();
				LinkedHashMap<String, List<Object[]>> dacSectors = new LinkedHashMap<String, List<Object[]>>();



//				LOGGER.info("TOP_DONORS_TABLE start sub queries");
				for(int i=0; i < sqlResult.size(); i++) {

					// query channels, recipients e sectos
//					sql = ADAMQueryBuilder.globalViewDAC(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), 3);
					sql = ADAMQueryBuilder.globalViewDAC(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit);
					List<Object[]> dacResult = adamDao.tableQuery(sql);
					dacSectors.put(sqlResult.get(i)[0].toString(), dacResult);
					ADAMLinkUtils.createGlobalDACLinkMap(dacLinks, dacResult);

//					sql = ADAMQueryBuilder.globalViewChannel(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), 3);
					sql = ADAMQueryBuilder.globalViewChannel(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit);
					List<Object[]> channelResult = adamDao.tableQuery(sql);
					channels.put(sqlResult.get(i)[0].toString(), channelResult);
					ADAMLinkUtils.createGlobalChannelsLinkMap(channelsLinks, channelResult);


//					sql = ADAMQueryBuilder.globalViewRecipient(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), 3, "CRS_RECIPIENTS");
					sql = ADAMQueryBuilder.globalViewRecipient(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit, "ADAM_RECIPIENTS");
					List<Object[]> recipientResult = adamDao.tableQuery(sql);
					recipients.put(sqlResult.get(i)[0].toString(), recipientResult);
					ADAMLinkUtils.createGlobalRecipientLinkMap(recipientsLinks, recipientResult);



				}
//				LOGGER.info("TOP_DONORS_TABLE ended sub queries");


				isGrouped = true;

				// create results
				tableHeaders = ADAMTableUtils.globalDonorTableHeaders();
				tableContents = ADAMTableUtils.globalTableContents(sqlResult, channels, recipients, dacSectors, isGrouped, subQueryLimit);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(ADAMTableUtils.globalDonorTable(tableContents, 2, isGrouped));

				// fill the links
				rvo.setChannelMap(channelsLinks);
				rvo.setDacMap(dacLinks);
				rvo.setGaulMap(recipientsLinks);

				ADAMLinkUtils.createLinksMap(donorsLinks,  sqlResult);
				rvo.setDonorMap(donorsLinks);



				rvo.setGroupingTableColumn(0);
				rvo.setGroupTable(isGrouped);

			break;

			case TOP_DONORS_TABLE_FILTERED:

				/*** get donors codes **/
				LinkedHashMap<String, List<Object[]>> channelsFilter = new LinkedHashMap<String, List<Object[]>>();
				LinkedHashMap<String, List<Object[]>> dacSectorsFilter = new LinkedHashMap<String, List<Object[]>>();


//				LOGGER.info("TOP_DONORS_TABLE_FILTERED start sub queries ");
				for(int i=0; i < sqlResult.size(); i++) {

					// query channels, recipients e sectos
					sql = ADAMQueryBuilder.countryViewDAC(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit);
//					sql = ADAMQueryBuilder.countryViewDAC(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), 3);
					List<Object[]> dacResult = adamDao.tableQuery(sql);
					dacSectorsFilter.put(sqlResult.get(i)[0].toString(), dacResult);
					ADAMLinkUtils.createLinksMap(dacLinks, dacResult);


//					sql = ADAMQueryBuilder.countryViewChannel(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), 3);
					sql = ADAMQueryBuilder.countryViewChannel(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit);

					List<Object[]> channelResult = adamDao.tableQuery(sql);
					channelsFilter.put(sqlResult.get(i)[0].toString(), channelResult);
					ADAMLinkUtils.createLinksMap(channelsLinks, channelResult);


				}
//				LOGGER.info("TOP_DONORS_TABLE_FILTERED end sub queries");

				isGrouped = true;

				// create results
				tableHeaders = ADAMTableUtils.countryDonorTableHeaders();
				tableContents = ADAMTableUtils.countryTableContents(sqlResult, channelsFilter, dacSectorsFilter, isGrouped, subQueryLimit);


				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(ADAMTableUtils.generalTableContents(tableContents, 2, isGrouped));

				// fill the links
				rvo.setChannelMap(channelsLinks);
				rvo.setDacMap(dacLinks);
				ADAMLinkUtils.createLinksMap(donorsLinks,  sqlResult);
				rvo.setDonorMap(donorsLinks);


				// grouping settings
				rvo.setGroupingTableColumn(0);
				rvo.setGroupTable(isGrouped);

			break;

			case TOP_SECTORS_DONOR_VIEW_TABLE:

				/*** get donors codes **/
				LinkedHashMap<String, List<Object[]>> topSecCountries = new LinkedHashMap<String, List<Object[]>>();

//				LOGGER.info("TOP_SECTORS_DONOR_VIEW_TABLE start sub queries");
				for(int i=0; i < sqlResult.size(); i++) {
//					System.out.print("Donor Code: " + sqlResult.get(i)[0]);

					// query channels, recipients e sectos
//					sql = ADAMQueryBuilder.topSectorsDonorViewRecipiens(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), 3, "CRS_RECIPIENTS");
					sql = ADAMQueryBuilder.topSectorsDonorViewRecipiens(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit, "ADAM_RECIPIENTS");
					List<Object[]> recipientsResult = adamDao.tableQuery(sql);
//					LOGGER.info("size recipientsResult: " + recipientsResult.size());
					topSecCountries.put(sqlResult.get(i)[0].toString(), recipientsResult);
					ADAMLinkUtils.createLinksMap(recipientsLinks, recipientsResult);



				}
//				LOGGER.info("TOP_SECTORS_DONOR_VIEW_TABLE ended sub queries");


				isGrouped = true;

				// create results
				tableHeaders = ADAMTableUtils.topSectorsRecipientsTableHeaders();
				tableContents = ADAMTableUtils.donorTableContents(sqlResult, topSecCountries, isGrouped, 3, subQueryLimit);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(ADAMTableUtils.generalTableContents(tableContents, 2, isGrouped));
//				rvo.setTableContents(ADAMTableUtils.topSectorsRecipientsTableContents(tableContents, 2));


				// fill the links
				rvo.setGaulMap(recipientsLinks);
				ADAMLinkUtils.createLinksMap(dacLinks,  sqlResult);
				rvo.setDacMap(dacLinks);

				rvo.setGroupingTableColumn(0);
				rvo.setGroupTable(isGrouped);

			break;

			case TOP_SECTORS_DONOR_COUNTRY_VIEW_TABLE:

				/*** get donors codes **/
				LinkedHashMap<String, List<Object[]>> topDRChannels = new LinkedHashMap<String, List<Object[]>>();

//				LOGGER.info("TOP_SECTORS_DONOR_COUNTRY_VIEW_TABLE start sub queries");
				for(int i=0; i < sqlResult.size(); i++) {
//					System.out.print("Donor Code: " + sqlResult.get(i)[0]);

					// query channels, recipients e sectos
//					sql = ADAMQueryBuilder.topSectorsDonorCountryViewChannels(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), 3);
					sql = ADAMQueryBuilder.topSectorsDonorCountryViewChannels(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit);
					List<Object[]> result = adamDao.tableQuery(sql);
//					LOGGER.info("size topDRChannels: " + result.size());
					topDRChannels.put(sqlResult.get(i)[0].toString(), result);
					ADAMLinkUtils.createLinksMap(channelsLinks, result);

				}
//				LOGGER.info("TOP_SECTORS_DONOR_COUNTRY_VIEW_TABLE ended sub queries");


				// take only the first line for each donor out of the SQL result


				isGrouped = true;

				// create results
				tableHeaders = ADAMTableUtils.topSectorsChannelsTableHeaders();
				tableContents = ADAMTableUtils.donorTableContents(sqlResult, topDRChannels, isGrouped, 3, subQueryLimit);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(ADAMTableUtils.generalTableContents(tableContents, 2, isGrouped));

				// fill the links
				rvo.setChannelMap(channelsLinks);

				ADAMLinkUtils.createLinksMap(dacLinks,  sqlResult);
				rvo.setDacMap(dacLinks);


				rvo.setGroupingTableColumn(0);
				rvo.setGroupTable(isGrouped);

			break;

			case TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE:

				/*** get donors codes **/
				LinkedHashMap<String, List<Object[]>> topSubSecCountries = new LinkedHashMap<String, List<Object[]>>();

//				LOGGER.info("TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE start sub queries");
				for(int i=0; i < sqlResult.size(); i++) {
//					System.out.print("Donor Code: " + sqlResult.get(i)[0]);

					// query channels, recipients e sectos
					sql = ADAMQueryBuilder.topSubSectorsDonorViewRecipiens(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit, "ADAM_RECIPIENTS");
					List<Object[]> recipientsResult = adamDao.tableQuery(sql);
//					LOGGER.info("size recipientsResult: " + recipientsResult.size());
					topSubSecCountries.put(sqlResult.get(i)[0].toString(), recipientsResult);
					ADAMLinkUtils.createLinksMap(recipientsLinks, recipientsResult);



				}
//				LOGGER.info("TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE ended sub queries");


				isGrouped = true;

				// create results
				tableHeaders = ADAMTableUtils.agriculturalDonorHeaders();
				tableContents = ADAMTableUtils.donorTableContents(sqlResult, topSubSecCountries, isGrouped, 4, subQueryLimit);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(ADAMTableUtils.generalTableContents(tableContents, 2, isGrouped));


				// fill the links
				rvo.setGaulMap(recipientsLinks);
				ADAMLinkUtils.createLinksMap(dacLinks,  sqlResult);
				rvo.setDacMap(dacLinks);

				rvo.setGroupingTableColumn(0);
				rvo.setGroupTable(isGrouped);

			break;


			case TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE:

				/*** get donors codes **/
				LinkedHashMap<String, List<Object[]>> topSubSecDonor = new LinkedHashMap<String, List<Object[]>>();

				/*** get channels codes **/
				LinkedHashMap<String, List<Object[]>> topSubSecChannel = new LinkedHashMap<String, List<Object[]>>();

//				LOGGER.info("TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE start sub queries");
				for(int i=0; i < sqlResult.size(); i++) {
//					System.out.print("Donor Code: " + sqlResult.get(i)[0]);

					// query channels, recipients e sectos
//					sql = ADAMQueryBuilder.getSubSectorDonors(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), 5);
					sql = ADAMQueryBuilder.getSubSectorDonors(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit);
					List<Object[]> resultDonors = adamDao.tableQuery(sql);
//					LOGGER.info("size resultDonors: " + resultDonors.size());
					topSubSecDonor.put(sqlResult.get(i)[0].toString(), resultDonors);
					ADAMLinkUtils.createLinksMap(donorsLinks, resultDonors);

//					sql = ADAMQueryBuilder.topSubSectorsChannels(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), 5);
					sql = ADAMQueryBuilder.topSubSectorsChannels(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit);
					List<Object[]> resultChannels = adamDao.tableQuery(sql);
//					LOGGER.info("size resultChannels: " + resultChannels.size());
					topSubSecChannel.put(sqlResult.get(i)[0].toString(), resultChannels);
					ADAMLinkUtils.createLinksMap(channelsLinks, resultChannels);



				}
//				LOGGER.info("TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE ended sub queries");



				isGrouped = true;

				// create results
				tableHeaders = ADAMTableUtils.topSectorsDonorsChannelsTableHeaders();
				tableContents = ADAMTableUtils.tableContents(sqlResult, topSubSecDonor, topSubSecChannel, isGrouped, subQueryLimit);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(ADAMTableUtils.generalTableContents(tableContents, 2, isGrouped));


				// fill the links
				rvo.setDonorMap(donorsLinks);
				rvo.setChannelMap(channelsLinks);

				ADAMLinkUtils.createLinksMap(dacLinks,  sqlResult);
				rvo.setDacMap(dacLinks);



				rvo.setGroupingTableColumn(0);
				rvo.setGroupTable(isGrouped);

			break;


			case TOP_AGRICULTURAL_SECTORS_DONOR_COUNTRY_VIEW_TABLE:

				/*** get donors codes **/
				LinkedHashMap<String, List<Object[]>> topSubChannels = new LinkedHashMap<String, List<Object[]>>();

//				LOGGER.info("TOP_AGRICULTURAL_SECTORS_DONOR_COUNTRY_VIEW_TABLE start sub queries");
				for(int i=0; i < sqlResult.size(); i++) {

					sql = ADAMQueryBuilder.topSubSectorsChannels(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(),quantityColumn, aggregationType, subQueryLimit);
					List<Object[]> result = adamDao.tableQuery(sql);
//					LOGGER.info("size result: " + result.size());
					topSubChannels.put(sqlResult.get(i)[0].toString(), result);
					ADAMLinkUtils.createLinksMap(channelsLinks, result);

				}
//				LOGGER.info("TOP_AGRICULTURAL_SECTORS_DONOR_COUNTRY_VIEW_TABLE ended sub queries");


				isGrouped = true;

				// create results
				tableHeaders = ADAMTableUtils.topAgricSectorsChannelsTableHeaders();
				tableContents = ADAMTableUtils.donorTableContents(sqlResult, topSubChannels, isGrouped, 3, subQueryLimit);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(ADAMTableUtils.generalTableContents(tableContents, 2, isGrouped));

				// fill the links
				rvo.setChannelMap(channelsLinks);

				ADAMLinkUtils.createLinksMap(dacLinks,  sqlResult);
				rvo.setDacMap(dacLinks);

				rvo.setGroupingTableColumn(0);
				rvo.setGroupTable(isGrouped);

			break;

			case TOP_COUNTRIES_BY_DONOR:

				/*** get donors codes **/
				LinkedHashMap<String, List<Object[]>> topSecCountry = new LinkedHashMap<String, List<Object[]>>();

				//LOGGER.info("TOP_COUNTRIES_BY_DONOR SIZE: " +  sqlResult.size());

				//LOGGER.info("TOP_COUNTRIES_BY_DONOR start sub queries");
				for(int i=0; i < sqlResult.size(); i++) {
//					System.out.print("country Code: " + sqlResult.get(i)[0]);

					sql = ADAMQueryBuilder.topSectorsByRecipientCode(qvo.getTableNames().get(0), sqlResult.get(i)[0].toString(), qvo.getWheres(), quantityColumn, aggregationType, subQueryLimit);
					List<Object[]> resultSectors = adamDao.tableQuery(sql);

					topSecCountry.put(sqlResult.get(i)[0].toString(), resultSectors);
					ADAMLinkUtils.createLinksMap(dacLinks, resultSectors);




				}
//				LOGGER.info("TOP_COUNTRIES_BY_DONOR ended sub queries");



				isGrouped = true;

				// create results
				tableHeaders = ADAMTableUtils.topCountriesSectorsByDonorTableHeaders();
				tableContents = ADAMTableUtils.donorTableContents(sqlResult, topSecCountry, isGrouped, 3, subQueryLimit);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(ADAMTableUtils.generalTableContents(tableContents, 2, isGrouped));


				// fill the links
//				rvo.setDonorMap(donorsLinks);
//				rvo.setChannelMap(channelsLinks);

				ADAMLinkUtils.createLinksMap(recipientsLinks,  sqlResult);
				rvo.setDacMap(dacLinks);
				rvo.setGaulMap(recipientsLinks);



				rvo.setGroupingTableColumn(0);
				rvo.setGroupTable(isGrouped);

			break;

			case DONOR_PROFILE_TABLE:
				// create results
				tableHeaders = ADAMTableUtils.donorProfileTableHeaders();
				tableContents = globalTableContents(sqlResult);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(tableContents);

			break;

			case DONOR_PROCESSES_TABLE:
				// create results
				tableHeaders = ADAMTableUtils.donorProcessesTableHeaders();
				tableContents = globalTableContents(sqlResult);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(tableContents);

			break;
			case CONTRIBUTION_TABLE:			
				// create results
				tableHeaders = ADAMTableUtils.globalContributionHeaders(qvo.getDatasetCodes().get(0));
				tableContents = globalTableContents(sqlResult);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(tableContents);
				rvo.setAddTotalRow(true);

			break;
            case IMPLEMENTING_AGENCIES_TABLE:
				// create results
				tableHeaders = ADAMTableUtils.globalImplementingAgenciesHeaders(qvo.getDatasetCodes().get(0));
				tableContents = globalTableContents(sqlResult);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(tableContents);
				rvo.setAddTotalRow(true);

			break;
			default:

				// create results
				tableHeaders = ADAMTableUtils.topDonorsTableHeaders();
				tableContents = globalTableContents(sqlResult);
//				tableContents = globalTableContentsGroupBy(sqlResult);

				// fill the result bean
				rvo.setTableHeaders(tableHeaders);
				rvo.setTableContents(tableContents);

			break;
		}

		return rvo;
	}


	private ADAMResultVO createChartPie(ADAMQueryVO qvo, ADAMResultVO rvo) {
		if ( qvo.getIsOR() ) {
			return createChartPieOR(qvo, rvo);
		}
		else
			return createChartPieSimple(qvo, rvo);


	}

	private ADAMResultVO createChartPieOR(ADAMQueryVO qvo, ADAMResultVO rvo) {
		String sql = null;
		// create the SQL query
		if ( !qvo.getSelects().contains("purposecode") ) {
			// convert OR to DAC
			Map<String, HashMap<String, String>> dac_ors = convertORtoDAC(qvo.getPriorities());

			// adding dac derived filters
		Map<String, List<String>> filters = qvo.getWheres();

			List<String> codes = new ArrayList<String>();
			for(String code : dac_ors.keySet()) {
				codes.add(code);
			}
			filters.put("purposecode", codes);
			sql = ADAMQueryBuilder.buildQuery(qvo);
		}
		else {
			CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
//			CustomDataset oecdtable = customDatasetDao.find(qvo.getDatasetCodes().get(0));

			sql = ADAMQueryBuilder.buildORquery(convtable.getTablename(), qvo.getTableNames().get(0), qvo); // NO limit in this query, handled later
				
		}

		LOGGER.info("createChartPieOR: " + sql);

		Map<String, Map<String, Double>> codeLabelsResult = adamDao.orChartQuery(sql); 
		
			
		Map<String, Double> labelsResult = new HashMap<String, Double>();
		Map<String, Double> codesResult = new HashMap<String, Double>();
		
		for(String code : codeLabelsResult.keySet()) {
			for(String label : codeLabelsResult.get(code).keySet()) {
				// if the query is on the purpose code, they should be translated in ORs
//				if ( qvo.getSelects().contains("purposecode") ) {
//
//					StringBuffer l = new StringBuffer();
//					HashMap<String, String> ors = dac_ors.get(code);
//					int i=0;
//					LOGGER.info("yes it contains: " + ors.size());
//					for(String or_code : ors.keySet()) {
//						l.append(or_code);
//						if ( i < ors.size() -1)
//							l.append(" - ");
//						i++;
//					}
//					labelsResult.put(l.toString(), codeLabelsResult.get(code).get(label));
//				}
//				else {
					labelsResult.put(label, codeLabelsResult.get(code).get(label));
//				}
				codesResult.put(code, codeLabelsResult.get(code).get(label));
			}

		}

		LOGGER.info("END CHART: " + sql);
		LOGGER.info("codeLabelsResult: " + codeLabelsResult);
		LOGGER.info("labelsResult: " + labelsResult);
		LOGGER.info("codesResult: " + codesResult);


		if(qvo.getLimit()!=0 || !qvo.getSelects().contains("purposecode")) {
			sql = ADAMQueryBuilder.buildAgrgegationQuery(qvo);
		}
		LOGGER.info("CHART TOTAL: " + sql);
		// query the DB
		Double sqlResult = null;
		if(!qvo.getSelects().contains("purposecode")){
			sqlResult = adamDao.tableQueryValue(sql);
		}
		LOGGER.info("END CHART TOTAL: " + sql);

		// prepare JFreeChart input
		Map<String, Double> sortedJDBCResult = sortByValue(labelsResult);
			
		//Handling the Limit at the point of the sorted results
		Integer limit = qvo.getLimit();

		if (qvo.getSelects().contains("purposecode") && (limit != null ||  limit != 0 )) {
			Map<String, Double> limitedResult = limitResultMap(sortedJDBCResult, limit); 
			
			if(!limitedResult.isEmpty()){
				sortedJDBCResult.clear();
				sortedJDBCResult.putAll(limitedResult);
			}
		} 	
		
//		LOGGER.info("sortedJDBCResult: " + sortedJDBCResult);
		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		if(!qvo.getSelects().contains("purposecode")){
			getOtherValue(sortedJDBCResult, sqlResult);
		}

		if ( !sortedJDBCResult.isEmpty() )
			values.put("values", sortedJDBCResult);

		rvo.setChartValues(values);

		createPIEchart(qvo, rvo, values);

		return rvo;
	}

	private ADAMResultVO createChartPieSimple(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("^^ --1-- CREATE PIE for '"+qvo.getTitle()+"'");
		
		// create the SQL query
		String sql = ADAMQueryBuilder.buildQuery(qvo);
		//LOGGER.info("CHART: " + sql);
		Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartQuery(sql);
		Map<String, Double> jdbcResult = new HashMap<String, Double>();
		for(String code : codeLabelsResult.keySet()) {
			for(String label : codeLabelsResult.get(code).keySet()) {
				jdbcResult.put(label, codeLabelsResult.get(code).get(label));
			}
		}
		LOGGER.info("^^ --1-- PIE sql '"+sql);
	
		sql = ADAMQueryBuilder.buildAgrgegationQuery(qvo);
		//LOGGER.info("CHART TOTAL: " + sql);
		// query the DB
		Double sqlResult = adamDao.tableQueryValue(sql);

		//LOGGER.info("END CHART TOTAL: " + sql);
		// print out
//		for(String key : jdbcResult.keySet())
//			System.out.println(key + ", " + jdbcResult.get(key));

//		LOGGER.info("jdbcResult: " + jdbcResult);

		// prepare JFreeChart input
		Map<String, Double> sortedJDBCResult = sortByValue(jdbcResult);

//		LOGGER.info("sortedJDBCResult: " + sortedJDBCResult);

		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		getOtherValue(sortedJDBCResult, sqlResult);

		if ( !sortedJDBCResult.isEmpty() )
			values.put("values", sortedJDBCResult);


		rvo.setChartValues(values);

		/** this is not needed, but it will be needed to have different classes **/
//		for (String key : sortedJDBCResult.keySet()) {
//			Map<String, Double> bar = new HashMap<String, Double>();
//			bar.put(key, sortedJDBCResult.get(key));
//			values.put(key, bar);
//		}


//		values = new LinkedHashMap<String, Map<String,Double>>();
//
//		Map<String,Double> value = new LinkedHashMap<String, Double>();
//		value.put("1", new Double(10));
//		value.put("2", new Double(20));
//		values.put("2008", value);
//
//		value = new LinkedHashMap<String, Double>();
//		value.put("1", new Double(100));
//		value.put("2", new Double(200));
//		values.put("2007", value);
//
//
//		LOGGER.info("SERIES: " + values);

		createPIEchart(qvo, rvo, values);

		return rvo;
	}

	private ADAMResultVO createPIEchart(ADAMQueryVO qvo, ADAMResultVO rvo, LinkedHashMap<String, Map<String, Double>> values) {
		// outputs
		String bigObjectPath = "";
		String smallObjectPath = "";
		String mediumObjectPath = "";
		String exportObjectPath = "";

		// create JFreeChart settings
//		JFreeChartSettings s = new JFreeChartSettings(qvo.getTitle(), qvo.getxLabel(), qvo.getyLabel());
		JFreeChartSettings s = new JFreeChartSettings("", qvo.getxLabel(), qvo.getyLabel());
		s.setChartType(JFreeChartConstants.valueOf(qvo.getOutputType()));


		/** SMALL **/
		// max label width and legend size
		s.setMaxLabelWidth(23);
		s.setLegendLabelSize(10);

		// create small chart file
		s.setWidth(Integer.valueOf(qvo.getSmallWidth()));
		s.setHeight(Integer.valueOf(qvo.getSmallHeight()));
		s.setLegendLabelSize(9);
//		s.setTitleSize(7);
//		if ( qvo.getTitle().length() > 25) {
//			s.setTitle(qvo.getTitle().substring(0, 24) + "...");
//		}
		smallObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String smallImageMap = JFreeChartConnector.createADAMFile(values, "", dir, smallObjectPath, qvo.getMeasurementUnit(), s, null, qvo.getIsSO());


		// create medium chart file
		s.setWidth(MEDIUM_WIDTH);
		s.setHeight(MEDIUM_HEIGHT);
//		s.setTitle(qvo.getTitle());
		s.setLegendLabelSize(9);
		s.setMaxLabelWidth(-1);
//		s.setAxislSize(s.getAxislSize() * 2);
		mediumObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String mediumImageMap = JFreeChartConnector.createADAMFile(values, "", dir, mediumObjectPath, qvo.getMeasurementUnit(), s, null, qvo.getIsSO());

		/** BIG **/
		// max label width and legend size
		s.setMaxLabelWidth(-1);
		s.setLegendLabelSize(11);

		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
		s.setLegendLabelSize(11);
//		s.setTitleSize(11);
//		s.setTitle(qvo.getTitle());
//		s.setLegendLabelSize(s.getLegendLabelSize() * 2);
		s.setAxislSize(s.getAxislSize() * 2);
		bigObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String bigImageMap = JFreeChartConnector.createADAMFile(values, "", dir, bigObjectPath, qvo.getMeasurementUnit(), s, null,qvo.getIsSO());

		// create export chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
		s.setTitle(qvo.getTitle());
		s.setTitleSize(15);
		s.setAxislSize(s.getAxislSize() + 3);
		exportObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String exportImageMap = JFreeChartConnector.createADAMFile(values, "", dir, exportObjectPath, qvo.getMeasurementUnit(), s, null, qvo.getIsSO());


		// fill the result bean
		rvo.setBigImagePath(bigObjectPath);
		rvo.setSmallImagePath(smallObjectPath);
		rvo.setMediumImagePath(mediumObjectPath);
		rvo.setExportImagePath(exportObjectPath);

		// setting imagemap
		rvo.setBigHtmlImageMap(bigImageMap);
		rvo.setSmallHtmlImageMap(smallImageMap);
		rvo.setMediumHtmlImageMap(mediumImageMap);
		rvo.setExportHtmlImageMap(exportImageMap);



//		rvo.setDacMap(createGlobalLinksMap(jdbcResult, "CRS_PURPOSES", qvo.getLangCode()));
//		rvo.setDonorMap(createGlobalLinksMap(new HashMap<String, Double>(), "CRS_DONORS", qvo.getLangCode()));
//		rvo.setGaulMap(createGlobalLinksMap(new HashMap<String, Double>(), "CRS_RECIPIENTS", qvo.getLangCode()));

//		LOGGER.info("CREATING LINKS");

		// get links
/**		Map<String, String> channelsLinks = new HashMap<String, String>();
		Map<String, String> dacLinks = new HashMap<String, String>();
		Map<String, String> donorsLinks = new HashMap<String, String>();
		Map<String, String> recipientsLinks = new HashMap<String, String>();
		String sqlQuery = null;
		List<Object[]> result = null;

		// get recipients links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "recipientcode", "recipientname");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(recipientsLinks,  result);
		rvo.setGaulMap(recipientsLinks);


		// get dac links
//		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "CRS_SECTORS", "dac_sector");
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(),  "dac_sector", "dac_label");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(dacLinks,  result);
		rvo.setDacMap(dacLinks);

		// get channels links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "channelcode", "channelname");
//		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "CRS_CHANNELS", "channelcode");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(channelsLinks,  result);
		rvo.setChannelMap(channelsLinks);

		// get donors links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "donorcode", "donorname");
//		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "CRS_DONORS", "donorcode");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(donorsLinks,  result);
		rvo.setDonorMap(donorsLinks);
**/

//		LOGGER.info("END CREATING LINKS");
		return rvo;
	}

	private void getOtherValue(Map<String, Double> jdbcResult, Double value) {
		Double total = new Double(0);


		for(String key :  jdbcResult.keySet()) {
//			LOGGER.info("partial total: " + jdbcResult.get(key) + " | " + total);
			total = total + jdbcResult.get(key);
		}
//		LOGGER.info("total: "  + total);


//		LOGGER.info("others: "  + value);



		Double v = value - total;

//		LOGGER.info("total - others: "  + v);
		if ( v > 0.5 ) {
			//LOGGER.info("putting VALUE FOR OTHERS "  + v);
			jdbcResult.put("Others", v );
		}
		else {
//			LOGGER.info("not putting v "  + v);
		}
	}
	
	private HashMap limitResultMap(Map<String, Double> jdbcResult, Integer limit) {
		HashMap<String, Double> limitedMap = new LinkedHashMap<String, Double>();
		
		int i = 1;
		for(String key :  jdbcResult.keySet()) {
			limitedMap.put(key, jdbcResult.get(key));
			
			if(i == limit)
				break;
			
			i++;
		}

		LOGGER.info(" %%%%% limitResultMap "+ limitedMap.size());
		
		return limitedMap;
	}
	
	private HashMap limitTimeseriesResultMap(Map<String, Map<String, Double>> result, Integer limit) {
		HashMap<String, Map<String, Double>> limitedMap = new LinkedHashMap<String, Map<String, Double>>();
		
		int i = 1;
		for(String key :  result.keySet()) {
			limitedMap.put(key, result.get(key));
			
			if(i == limit)
				break;
			
			i++;
		}
		
		return limitedMap;
	}

	private ADAMResultVO createChartBarSeries(ADAMQueryVO qvo, ADAMResultVO rvo) {

		// outputs
		String bigObjectPath = "";
		String smallObjectPath = "";
		String mediumObjectPath = "";
		String exportObjectPath = "";

		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		List<String> series = new ArrayList<String>();
		String sql = "";


		//LOGGER.info("qvo.getSeries(): "+qvo.getSeries().size());

			//aggregations.put("\"Total ODA\"", "totalOdaSum");
		//aggregations.put("\"FAO sectors\"", "totalFaoSum");
		//q.setAggregations(aggregations);

		if(!qvo.getCalculatedSeries().isEmpty()) {
				Map<String, String> aggregations = new LinkedHashMap<String, String>();
				List<String> seriesList = new ArrayList<String>();

				for (String columnx : qvo.getCalculatedSeries().keySet()) {
					aggregations.put(columnx, qvo.getCalculatedSeries().get(columnx));
					qvo.setAggregations(aggregations);
					seriesList.add(columnx);
				}

				if(!qvo.getWheres().isEmpty()) {
					//qvo.getWheres().put(column, series);
					sql = ADAMQueryBuilder.buildQuery(qvo);
					LOGGER.info("SERIES: "+ sql);

					Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartSeriesQuery(sql, seriesList);

					Map<String, Double> jdbcResult = new HashMap<String, Double>();

					Map<String, String> seriesLabels = qvo.getSeriesLabels();

					for(String code : codeLabelsResult.keySet()) {
						for(String label : codeLabelsResult.get(code).keySet()) {
							jdbcResult.put(label, codeLabelsResult.get(code).get(label));
						}
						Map<String, Double> sortedJDBCResult = sortByValue(jdbcResult);
						values.put(seriesLabels.get(code), sortedJDBCResult);
					}
					//LOGGER.info("jdbcResult SIZE: "+jdbcResult.size());

					//aggregations.clear();
				}


				/****for (String column : qvo.getCalculatedSeries().keySet()) {
					LOGGER.info("column: "+column);

					//for (String aggColumn : qvo.getCalculatedSeries().get(column)) {
						aggregations.put(column, qvo.getCalculatedSeries().get(column));
						qvo.setAggregations(aggregations);

						//LOGGER.info("data: "+data);
						//series.add(data);
						if(!qvo.getWheres().isEmpty()) {
							//qvo.getWheres().put(column, series);
							sql = ADAMQueryBuilder.buildQuery(qvo);
							LOGGER.info("SERIES: "+column+": "+ sql);
								Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartQuery(sql);
							Map<String, Double> jdbcResult = new HashMap<String, Double>();
							for(String code : codeLabelsResult.keySet()) {
								for(String label : codeLabelsResult.get(code).keySet()) {
									jdbcResult.put(label, codeLabelsResult.get(code).get(label));
								}
							}
							LOGGER.info("jdbcResult SIZE: "+jdbcResult.size());
							Map<String, Double> sortedJDBCResult = sortByValue(jdbcResult);
							LOGGER.info("sortedJDBCResult SIZE: "+sortedJDBCResult.size());
							values.put(column, sortedJDBCResult);
							aggregations.clear();


					}
				}	}***/
		}
		else if (!qvo.getSeries().isEmpty()) {
			for (String column : qvo.getSeries().keySet()) {
				//LOGGER.info("column: "+column);
				for (String data : qvo.getSeries().get(column)) {
					//LOGGER.info("data: "+data);
					series.add(data);
					if(!qvo.getWheres().isEmpty()) {
						qvo.getWheres().put(column, series);
						sql = ADAMQueryBuilder.buildQuery(qvo);
						//LOGGER.info("SERIES: "+data+": "+ sql);
							Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartQuery(sql);
						Map<String, Double> jdbcResult = new HashMap<String, Double>();
						for(String code : codeLabelsResult.keySet()) {
							for(String label : codeLabelsResult.get(code).keySet()) {
								jdbcResult.put(label, codeLabelsResult.get(code).get(label));
							}
						}
						//LOGGER.info("jdbcResult SIZE: "+jdbcResult.size());
						Map<String, Double> sortedJDBCResult = sortByValue(jdbcResult);
						//LOGGER.info("sortedJDBCResult SIZE: "+sortedJDBCResult.size());
						values.put(data, sortedJDBCResult);
						series.clear();
					}

				}
			}
		}

		//LOGGER.info("SERIES MAP: size = "+values.size());
    	rvo.setChartValues(values);

		/** this is not needed, but it will be needed to have different classes **/
//		for (String key : sortedJDBCResult.keySet()) {
//			Map<String, Double> bar = new HashMap<String, Double>();
//			bar.put(key, sortedJDBCResult.get(key));
//			values.put(key, bar);
//		}




//		values = new LinkedHashMap<String, Map<String,Double>>();
//
//		Map<String,Double> value = new LinkedHashMap<String, Double>();
//		value.put("1", new Double(10));
//		value.put("2", new Double(20));
//		values.put("2008", value);
//
//		value = new LinkedHashMap<String, Double>();
//		value.put("1", new Double(100));
//		value.put("2", new Double(200));
//		values.put("2007", value);
//
//

		// create JFreeChart settings
		JFreeChartSettings s = new JFreeChartSettings(qvo.getTitle(), qvo.getxLabel(), qvo.getyLabel());
		s.setChartType(JFreeChartConstants.valueOf(ADAMBoxContent.BAR_WITH_CATEGORIES.name()));
		if(qvo.getSubTitle()!=null)
			s.setSubTitle(qvo.getSubTitle());

		// create small chart file
		s.setWidth(Integer.valueOf(qvo.getSmallWidth()));
		s.setHeight(Integer.valueOf(qvo.getSmallHeight()));
		//LOGGER.info("FONT SIZE SMALL : " + s.getLegendLabelSize());
		//LOGGER.info("FONT SIZE SMALL : " + s.getTitleSize());
//		if ( qvo.getTitle().length() > 25) {
//			s.setTitle(qvo.getTitle().substring(0, 24) + "...");
//		}
		smallObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String smallImageMap = JFreeChartConnector.createADAMFile(values, "", dir, smallObjectPath,qvo.getMeasurementUnit(), s, null, qvo.getIsSO());


		// create medium chart file
		s.setWidth(MEDIUM_WIDTH);
		s.setHeight(MEDIUM_HEIGHT);
		s.setTitle(qvo.getTitle());
//		s.setAxislSize(s.getAxislSize() * 2);
		mediumObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String mediumImageMap = JFreeChartConnector.createADAMFile(values, "", dir, mediumObjectPath, qvo.getMeasurementUnit(), s, null, qvo.getIsSO());

		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() * 2);
		bigObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String bigImageMap = JFreeChartConnector.createADAMFile(values, "", dir, bigObjectPath, qvo.getMeasurementUnit(), s, null, qvo.getIsSO());

		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() + 3);
		s.setTitleSize(15);
		exportObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String exportImageMap = JFreeChartConnector.createADAMFile(values, "", dir, exportObjectPath, qvo.getMeasurementUnit(), s,null, qvo.getIsSO());



		// get links
		Map<String, String> channelsLinks = new HashMap<String, String>();
		Map<String, String> dacLinks = new HashMap<String, String>();
		Map<String, String> donorsLinks = new HashMap<String, String>();
		Map<String, String> recipientsLinks = new HashMap<String, String>();
		String sqlQuery = null;
		List<Object[]> result = null;

		// get recipients links
		/*sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "CRS_RECIPIENTS", "recipientcode");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(recipientsLinks,  result);
		rvo.setGaulMap(recipientsLinks);


		// get dac links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "CRS_SECTORS", "dac_sector");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(dacLinks,  result);
		rvo.setDacMap(dacLinks);

		// get channels links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "CRS_CHANNELS", "channelcode");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(channelsLinks,  result);
		rvo.setChannelMap(channelsLinks);

		// get donors links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "CRS_DONORS", "donorcode");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(donorsLinks,  result);
		rvo.setDonorMap(donorsLinks);*/

//		LOGGER.info("END CREATING LINKS");

		// fill the result bean
		rvo.setBigImagePath(bigObjectPath);
		rvo.setSmallImagePath(smallObjectPath);
		rvo.setMediumImagePath(mediumObjectPath);
		rvo.setExportImagePath(exportObjectPath);

		// setting imagemap
		rvo.setBigHtmlImageMap(bigImageMap);
		rvo.setSmallHtmlImageMap(smallImageMap);
		rvo.setMediumHtmlImageMap(mediumImageMap);
		rvo.setExportHtmlImageMap(exportImageMap);

		return rvo;
	}


	private ADAMResultVO createChartBar(ADAMQueryVO qvo, ADAMResultVO rvo) {
		if ( qvo.getIsOR() || qvo.getIsSO() ) {

			// this is the switch for the different return types
			if ( qvo.getReturnFrameworkType() != null ) {
				ADAMBoxContent returnType = ADAMBoxContent.valueOf(qvo.getReturnFrameworkType());
				switch (returnType) {
					case RETURN_OR: rvo = createChartOR(qvo, rvo); break;
					case RETURN_SO: rvo = createChartSO(qvo, rvo); break;
				}
				return rvo;
			}
			else
				return createChartBarOR(qvo, rvo);
		}
		else
			return createChartBarSimple(qvo, rvo);
				
	}

	private ADAMQueryVO addSOFilter(ADAMQueryVO qvo) {

		CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");

		String sql = null;
		List<Object> result = null;

		ADAMBoxContent addFilter = ADAMBoxContent.valueOf(qvo.getFilterFrameworkType());
		switch (addFilter) {
			case FILTER_ALL_SO:
				sql = ADAMQueryBuilder.getALLDacFromORs(convtable.getTablename());
				result = adamDao.tableQueryUniqueValue(sql);
				break;
			case FILTER_BY_SO:
				List<String> priorities = new ArrayList<String>();
				for(String key : qvo.getPriorities().keySet() ) {
					priorities.add(key);
				}
				sql = ADAMQueryBuilder.getALLDacFromSOs(convtable.getTablename(), priorities);
				result = adamDao.tableQueryUniqueValue(sql);
				break;
		}

		List<String> values = new ArrayList<String>();
		//ADD TO FILTERS
		for(int i=0; i < result.size(); i++) {
			try {
				//LOGGER.info("inside the for");
				values.add(result.get(i).toString());
			}catch (Exception e) {
				LOGGER.info("cannot parse it..");
			}
		}

		if (!values.isEmpty())
			qvo.getWheres().put("purposecode", values);



		return qvo;
	}
	
	

 private Map<String, Map<String, String>> convertORSToDAC(Map<String, Map<String, String>> priorities) {

	   Map<String, Map<String, String>> dac_priorities = new LinkedHashMap<String, Map<String, String>>();

		CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");

		String sql = ADAMQueryBuilder.getALLDacFromSOs(convtable.getTablename(), priorities);

		List<Object[]> result = adamDao.tableQuery(sql);

		LOGGER.info("result size: "+result.size());


		for (Object[] array : result) {

				Map<String, String> recipients = new HashMap<String, String>();

				String dac = array[0].toString();
				String fao = array[1].toString();

				LOGGER.info("[dac]: "+dac + " [fao]: " + fao);

				if(dac_priorities.containsKey(dac)){
					LOGGER.info("dac_priorities.containsKey : "+dac);
					recipients = dac_priorities.get(dac);
				}

				if(!priorities.containsKey(array[1].toString())){
					//take the first character as it is an SO
					LOGGER.info("priorities  DOES NOT contains: "+fao + " fao.charAt(0) "+fao.charAt(0));

					recipients.putAll(priorities.get(String.valueOf(fao.charAt(0))));
				} else {
					LOGGER.info("priorities contains: "+fao);
					recipients.putAll(priorities.get(fao));
				}

				dac_priorities.put(dac, recipients);
		}


		LOGGER.info("dac_priorities size: "+dac_priorities.size());

		return dac_priorities;
	}


  private List<String> convertORSToDAC(Object[] priorities) {

	    List<String> dac_priorities = new ArrayList<String>();

		CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");

		String sql = ADAMQueryBuilder.getALLDacFromSOs(convtable.getTablename(), priorities);

		List<Object> result = adamDao.tableQueryUniqueValue(sql);

		for (Object dac : result) {
			   dac_priorities.add(dac.toString());
		}

		return dac_priorities;
	}

  private LinkedHashMap<String, String> getDACCodesLabels(List<String> dacCodes) {

	   LinkedHashMap<String, String> dac = new LinkedHashMap<String, String>();

		String sql = ADAMQueryBuilder.getDACCodesLabels(dacCodes);

		List<Object[]> result = adamDao.tableQuery(sql);

		for (Object[] array : result) {
			dac.put(array[0].toString(), array[1].toString());
		}

		return dac;
	}



  private ADAMResultVO createChartOR(ADAMQueryVO qvo, ADAMResultVO rvo) {


		String sql = null;
		// create the SQL query

		CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
//		CustomDataset oecdtable = customDatasetDao.find(qvo.getDatasetCodes().get(0));


		sql = ADAMQueryBuilder.buildORquery(convtable.getTablename(), qvo.getTableNames().get(0), qvo); // NO limit in this query, handled later


		LOGGER.info("createChartOR: " + sql);

		Map<String, Map<String, Double>> codeLabelsResult = adamDao.orChartQuery(sql); 
		Map<String, Double> labelsResult = new HashMap<String, Double>();
		Map<String, Double> codesResult = new HashMap<String, Double>();

		for(String code : codeLabelsResult.keySet()) {
			for(String label : codeLabelsResult.get(code).keySet()) {
				labelsResult.put(label, codeLabelsResult.get(code).get(label));
				codesResult.put(code, codeLabelsResult.get(code).get(label));
			}

		}

		//LOGGER.info("END buildORquery: " + sql);
		LOGGER.info("codeLabelsResult: " + codeLabelsResult);
		LOGGER.info("labelsResult: " + labelsResult);
		LOGGER.info("codesResult: " + codesResult);
		


		/*if(qvo.getLimit()!=0 )
			sql = ADAMQueryBuilder.buildAgrgegationQuery(qvo);

		LOGGER.info("CHART TOTAL: " + sql);
		// query the DB
		Double sqlResult = null;

		sqlResult = adamDao.tableQueryValue(sql);

		LOGGER.info("END CHART TOTAL: " + sql);*/


		// prepare JFreeChart input
		Map<String, Double> sortedJDBCResult = sortByValue(labelsResult);
	
		//Handling the Limit at the point of the sorted results
		Integer limit = qvo.getLimit();

		if (limit != null ||  limit != 0) {
			Map<String, Double> limitedResult = limitResultMap(sortedJDBCResult, limit); 
			
			if(!limitedResult.isEmpty()){
				sortedJDBCResult.clear();
				sortedJDBCResult.putAll(limitedResult);
			}
		} 	
		
//		LOGGER.info("sortedJDBCResult: " + sortedJDBCResult);
		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		
		// Show other values if OR is not being plotted
		Double sqlResult = null;
		if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode")) {
			sql = ADAMQueryBuilder.buildORAggregationQuery(convtable.getTablename(), qvo.getTableNames().get(0), qvo);	
			sqlResult = adamDao.tableQueryValue(sql);
			
			LOGGER.info("TOTAL for OTHER CALC: " + sqlResult);
			getOtherValue(sortedJDBCResult, sqlResult);
		}
			
		if ( !sortedJDBCResult.isEmpty() )
			values.put("values", sortedJDBCResult);

		rvo.setChartValues(values);


		// retrieve coding system for the hover on the chart...
		List<String> sfCodes = new ArrayList<String>();
		for(String code : sortedJDBCResult.keySet()) {
			sfCodes.add(code);
		}

		// If OR is being plotted get labels
		HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
		
		
		if((qvo.getSelects().isEmpty()) || (qvo.getSelects().contains("purposecode"))) {
			List<Code> codes = codecDao.getCodesFromCode(sfCodes, "FAO_SO", "EN");
			for(Code c : codes) {
				xAxisFullLabel.put(c.getCode(), c.getCode() + " - " + c.getLabel());
			}
		} else
			xAxisFullLabel = null;	



		// switch chart
		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());

		/** TODO: get coding system **/

		switch (c) {
			case BAR: return createBarChart(qvo, rvo, values, xAxisFullLabel);
			case PIE: return createPIEchart(qvo, rvo, values);
		}

		LOGGER.error("ERROR: should be or BAR or PIE instead of: " + c.toString());
		return rvo;

	}
  
	private ADAMResultVO createChartBarOR(ADAMQueryVO qvo, ADAMResultVO rvo) {


		String sql = null;
		// create the SQL query
		if ( !qvo.getSelects().contains("purposecode") ) {
			// convert OR to DAC
			Map<String, HashMap<String, String>> dac_ors = convertORtoDAC(qvo.getPriorities());

			// adding dac derived filters
			Map<String, List<String>> filters = qvo.getWheres();

			List<String> codes = new ArrayList<String>();
			for(String code : dac_ors.keySet()) {
				codes.add(code);
			}
			filters.put("purposecode", codes);
			sql = ADAMQueryBuilder.buildQuery(qvo);
		}
		else {
			CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");

			sql = ADAMQueryBuilder.buildORquery(convtable.getTablename(), qvo.getTableNames().get(0), qvo); // NO limit in this query, handled later
					
		}

		LOGGER.info("CHART: " + sql);

		Map<String, Map<String, Double>> codeLabelsResult = adamDao.orChartQuery(sql); 
		Map<String, Double> labelsResult = new HashMap<String, Double>();
		Map<String, Double> codesResult = new HashMap<String, Double>();

		for(String code : codeLabelsResult.keySet()) {
			for(String label : codeLabelsResult.get(code).keySet()) {
					labelsResult.put(label, codeLabelsResult.get(code).get(label));
				codesResult.put(code, codeLabelsResult.get(code).get(label));
			}

		}

		LOGGER.info("END CHART: " + sql);
		LOGGER.info("1)codeLabelsResult: " + codeLabelsResult);
		LOGGER.info("2)labelsResult: " + labelsResult);
		LOGGER.info("3)codesResult: " + codesResult);


		if(qvo.getLimit()!=0 || !qvo.getSelects().contains("purposecode")) {
			sql = ADAMQueryBuilder.buildAgrgegationQuery(qvo);
		}
		LOGGER.info("CHART TOTAL: " + sql);
		// query the DB
		Double sqlResult = null;
		if(!qvo.getSelects().contains("purposecode")){
			sqlResult = adamDao.tableQueryValue(sql);
		}
		LOGGER.info("END CHART TOTAL: " + sql);




		// prepare JFreeChart input
		Map<String, Double> sortedJDBCResult = sortByValue(labelsResult);
			
	    //Handling the Limit at the point of the sorted results
		Integer limit = qvo.getLimit();

		if (qvo.getSelects().contains("purposecode") && (limit != null ||  limit != 0 )) {
			Map<String, Double> limitedResult = limitResultMap(sortedJDBCResult, limit); 
			
			if(!limitedResult.isEmpty()){
				sortedJDBCResult.clear();
				sortedJDBCResult.putAll(limitedResult);
			}
		} 	
		
		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		if(!qvo.getSelects().contains("purposecode")){
			getOtherValue(sortedJDBCResult, sqlResult);
		}

		if ( !sortedJDBCResult.isEmpty() )
			values.put("values", sortedJDBCResult);

		rvo.setChartValues(values);

		List<String> sfCodes = new ArrayList<String>();
		for(String code : sortedJDBCResult.keySet()) {
			sfCodes.add(code);
		}
		
		HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
		List<Code> codes = codecDao.getCodesFromCode(sfCodes, "FAO_SO", "EN");

		//LOGGER.info("--------codes.size(): " + codes.size());
		for(Code c : codes) {
			//LOGGER.info("----> Labels SF: " + c.getCode() + " | " + c.getLabel());
			xAxisFullLabel.put(c.getCode(), c.getCode() + " - " + c.getLabel());
		}
		//LOGGER.info("--------");
		// end



//		List<Code> codes = codecDao.getCodes(codes, "FAO Strategic Framework", "EN");


		return createBarChart(qvo, rvo, values, xAxisFullLabel);
	}

	private ADAMResultVO createChartBarSimple(ADAMQueryVO qvo, ADAMResultVO rvo) {
		 LOGGER.info("$$ --1-- CREATE BAR for '"+qvo.getTitle()+"'");
		
		
		// create the SQL query
		String sql = ADAMQueryBuilder.buildQuery(qvo);
		LOGGER.info("$$ --1-- BAR sql " + sql);
		
		

		Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartQuery(sql);
		Map<String, Double> labelsResult = new HashMap<String, Double>();
		Map<String, Double> codesResult = new HashMap<String, Double>();

		for(String code : codeLabelsResult.keySet()) {
			for(String label : codeLabelsResult.get(code).keySet()) {
				labelsResult.put(label, codeLabelsResult.get(code).get(label));
				codesResult.put(code, codeLabelsResult.get(code).get(label));
			}

		}

		//LOGGER.info("END CHART: " + sql);
		//LOGGER.info("codeLabelsResult: " + codeLabelsResult);
		//LOGGER.info("labelsResult: " + labelsResult);
		//LOGGER.info("codesResult: " + codesResult);


		if(qvo.getLimit()!=0) {
			sql = ADAMQueryBuilder.buildAgrgegationQuery(qvo);
		}
		//LOGGER.info("CHART TOTAL: " + sql);
		// query the DB
		Double sqlResult = adamDao.tableQueryValue(sql);
		//LOGGER.info("END CHART TOTAL: " + sql);


		// prepare JFreeChart input
		Map<String, Double> sortedJDBCResult = sortByValue(labelsResult);

		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		getOtherValue(sortedJDBCResult, sqlResult);

		if ( !sortedJDBCResult.isEmpty() )
			values.put("values", sortedJDBCResult);

		//LOGGER.info("******************* values: " + values);
		
		rvo.setChartValues(values);

		return createBarChart(qvo, rvo, values, null);
	}

	private ADAMResultVO createBarChart(ADAMQueryVO qvo, ADAMResultVO rvo, LinkedHashMap<String, Map<String, Double>> values, HashMap<String, String> xAxisFullLabel) {
//		LOGGER.info("SERIES: " + values);
//		LOGGER.info("CHART SIZE SMALL: " + qvo.getSmallWidth() + " | " + qvo.getSmallHeight());
//		LOGGER.info("CHART SIZE BIG: " + qvo.getBigWidth() + " | " + qvo.getBigHeight());


		// outputs
		String bigObjectPath = "";
		String smallObjectPath = "";
		String mediumObjectPath = "";
		String exportObjectPath = "";

		// create JFreeChart settings
//		JFreeChartSettings s = new JFreeChartSettings(qvo.getTitle(), qvo.getxLabel(), qvo.getyLabel());
		JFreeChartSettings s = new JFreeChartSettings("", qvo.getxLabel(), qvo.getyLabel());
		s.setChartType(JFreeChartConstants.valueOf(qvo.getOutputType()));
		s.setxAxisGridBackgroundColor(Color.gray);

		// max label width and legend size
		s.setMaxLabelWidth(25);
		s.setLegendLabelSize(10);



		// create small chart file
		s.setWidth(Integer.valueOf(qvo.getSmallWidth()));
		s.setHeight(Integer.valueOf(qvo.getSmallHeight()));
//		LOGGER.info("FONT SIZE SMALL : " + s.getLegendLabelSize());
//		LOGGER.info("FONT SIZE SMALL : " + s.getTitleSize());

		smallObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		// USE THE MEASUREMENT UNIT qvo.getMeasurementUnit()
		String smallImageMap = JFreeChartConnector.createADAMFile(values, "", dir, smallObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		// create medium chart file
		s.setWidth(MEDIUM_WIDTH);
		s.setHeight(MEDIUM_HEIGHT);
		s.setMaxLabelWidth(-1);
//		s.setTitle(qvo.getTitle());
		mediumObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String mediumImageMap = JFreeChartConnector.createADAMFile(values, "", dir, mediumObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		/** BIG **/
		// max label width and legend size and default title
		s.setMaxLabelWidth(-1);
		s.setLegendLabelSize(11);
//		s.setTitle("");

		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
//		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() + 3);
		bigObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String bigImageMap = JFreeChartConnector.createADAMFile(values, "", dir, bigObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		/** EXPORT IMAGE **/
		s.setMaxLabelWidth(-1);
		s.setLegendLabelSize(11);
//		s.setTitle("");

		// create export chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() + 3);
		s.setTitleSize(15);
		exportObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String exportImageMap = JFreeChartConnector.createADAMFile(values, "", dir, exportObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		// fill the result bean
		rvo.setBigImagePath(bigObjectPath);
		rvo.setSmallImagePath(smallObjectPath);
		rvo.setMediumImagePath(mediumObjectPath);
		rvo.setExportImagePath(exportObjectPath);

		// setting imagemap
		rvo.setBigHtmlImageMap(bigImageMap);
		rvo.setSmallHtmlImageMap(smallImageMap);
		rvo.setMediumHtmlImageMap(mediumImageMap);
		rvo.setExportHtmlImageMap(exportImageMap);


//		LOGGER.info("CREATING LINKS");

		// get links
/**			Map<String, String> channelsLinks = new HashMap<String, String>();
		Map<String, String> dacLinks = new HashMap<String, String>();
		Map<String, String> donorsLinks = new HashMap<String, String>();
		Map<String, String> recipientsLinks = new HashMap<String, String>();
		String sqlQuery = null;
		List<Object[]> result = null;

		// get recipients links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "recipientcode", "recipientname");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(recipientsLinks,  result);
		rvo.setGaulMap(recipientsLinks);


		// get dac links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(),  "dac_sector", "dac_label");
//		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "CRS_SECTORS", "dac_sector");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(dacLinks,  result);
		rvo.setDacMap(dacLinks);

		// get channels links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "channelcode", "channelname");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(channelsLinks,  result);
		rvo.setChannelMap(channelsLinks);

		// get donors links
		sql = ADAMQueryBuilder.getLinks(qvo.getTableNames().get(0), qvo.getWheres(), "donorcode", "donorname");
		result = adamDao.tableQuery(sql);
		ADAMLinkUtils.createLinksMap(donorsLinks,  result);
		rvo.setDonorMap(donorsLinks);
**/

//		LOGGER.info("END CREATING LINKS");
		return rvo;
	}

	private ADAMResultVO createBarStackChart(ADAMQueryVO qvo, ADAMResultVO rvo, LinkedHashMap<String, Map<String, Double>> values, HashMap<String, String> xAxisFullLabel, JFreeChartConstants stackType) {
//		LOGGER.info("SERIES: " + values);
//		LOGGER.info("CHART SIZE SMALL: " + qvo.getSmallWidth() + " | " + qvo.getSmallHeight());
//		LOGGER.info("CHART SIZE BIG: " + qvo.getBigWidth() + " | " + qvo.getBigHeight());

     	LOGGER.info("createBarStackChart: " + stackType.toString());
		// outputs
		String bigObjectPath = "";
		String smallObjectPath = "";
		String mediumObjectPath = "";
		String exportObjectPath = "";


		// create JFreeChart settings
//		JFreeChartSettings s = new JFreeChartSettings(qvo.getTitle(), qvo.getxLabel(), qvo.getyLabel());
		JFreeChartSettings s = new JFreeChartSettings("", qvo.getxLabel(), qvo.getyLabel());
		s.setChartType(stackType);
		s.setxAxisGridBackgroundColor(Color.gray);

		if(qvo.getSubTitle()!=null) {
			s.setSubTitle(qvo.getSubTitle()); 
		}
		
		
		// max label width and legend size
		s.setMaxLabelWidth(25);
		s.setLegendLabelSize(10);

		// LEGEND
		s.setShowLegend(false);

		// create small chart file
		s.setWidth(Integer.valueOf(qvo.getSmallWidth()));
		s.setHeight(Integer.valueOf(qvo.getSmallHeight()));
//		LOGGER.info("FONT SIZE SMALL : " + s.getLegendLabelSize());
//		LOGGER.info("FONT SIZE SMALL : " + s.getTitleSize());
//		if ( qvo.getTitle().length() > 25) {
//			s.setTitle(qvo.getTitle().substring(0, 24) + "...");
//		}
		smallObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		// USE THE MEASUREMENT UNIT qvo.getMeasurementUnit()
		String smallImageMap = JFreeChartConnector.createADAMFile(values, "", dir, smallObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		// create medium chart file
		s.setWidth(MEDIUM_WIDTH);
		s.setHeight(MEDIUM_HEIGHT);
		s.setMaxLabelWidth(-1);
//		s.setTitle(qvo.getTitle());
//		s.setAxislSize(s.getAxislSize() * 2);
		mediumObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String mediumImageMap = JFreeChartConnector.createADAMFile(values, "", dir, mediumObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());

		/** BIG **/
		// max label width and legend size
		s.setMaxLabelWidth(-1);
		s.setLegendLabelSize(11);

		// LEGEND
		s.setShowLegend(true);

		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
//		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() + 3);
		bigObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String bigImageMap = JFreeChartConnector.createADAMFile(values, "", dir, bigObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() + 3);
		s.setTitleSize(15);
		exportObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String exportImageMap = JFreeChartConnector.createADAMFile(values, "", dir, exportObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		// fill the result bean
		rvo.setBigImagePath(bigObjectPath);
		rvo.setSmallImagePath(smallObjectPath);
		rvo.setMediumImagePath(mediumObjectPath);
		rvo.setExportImagePath(exportObjectPath);

		// setting imagemap
		rvo.setBigHtmlImageMap(bigImageMap);
		rvo.setSmallHtmlImageMap(smallImageMap);
		rvo.setMediumHtmlImageMap(mediumImageMap);
		rvo.setExportHtmlImageMap(exportImageMap);

		return rvo;
	}


	static Map sortByValue(Map map) {
	     List list = new LinkedList(map.entrySet());
	     Collections.sort(list, new Comparator() {
	          public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
	          }
	     });
		// logger.info(list);
		Map result = new LinkedHashMap();

		for (int i= (list.size() -1); i >= 0; i--) {
		     Map.Entry entry = (Map.Entry) list.get(i);
		     result.put(entry.getKey(), entry.getValue());
		}

	return result;
	}

	static LinkedHashMap sortByKey(Map map) {
	     List list = new LinkedList(map.entrySet());
	     LOGGER.info("-- START --- "+ list + " | size = "+list.size());
	     
	     Collections.sort(list, new Comparator() {
	          public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o1)).getKey()).compareTo(((Map.Entry) (o2)).getKey());
	          }
	     });
	     LOGGER.info("-- END SORTED --- "+ list);
	     LinkedHashMap result = new LinkedHashMap();

	     for (int i = 0 ; i < list.size() ; i++) {
		     Map.Entry entry = (Map.Entry) list.get(i);
		     result.put(entry.getKey(), entry.getValue());
		  }
	 	
		/*for (int i= (list.size() -1); i >= 0; i--) {
		     Map.Entry entry = (Map.Entry) list.get(i);
		     result.put(entry.getKey(), entry.getValue());
		}*/

		 LOGGER.info("-- END SORTED --- "+ result + " : result size = "+result.size());
		 
	return result;
	}

	private Map<String, String> createGlobalLinksMap(Map<String, Double> sectorBudgetMap, String codingSystemCode, String langcode) {
		List<String> sectors = new ArrayList<String>();
		for (String sector : sectorBudgetMap.keySet())
			sectors.add(sector);
		return adamDao.createGlobalLinksMap(sectors, codingSystemCode, langcode);
	}

	private List<Object[]> filterTopDonorsQueryResult(List<Object[]> sqlResult) {
		List<Object[]> result = new ArrayList<Object[]>();
		List<String> donorCodes = new ArrayList<String>();
		for (int i = 0 ; i < sqlResult.size() ; i++) {
			String donorCode = sqlResult.get(i)[0].toString();
			if (!donorCodes.contains(donorCode)) {
				donorCodes.add(donorCode);
				result.add(sqlResult.get(i));
			}
		}
		return result;
	}

	private List<Object[]> filterTopDonorsQueryResult(List<Object[]> sqlResult, int size) {
		List<Object[]> result = new ArrayList<Object[]>();
		HashMap<String, Integer> donorCodes = new HashMap<String, Integer>();
		for (int i = 0 ; i < sqlResult.size() ; i++) {
			String donorCode = sqlResult.get(i)[0].toString();
			if (!donorCodes.containsKey(donorCode)) {
				donorCodes.put(donorCode, 1);
				result.add(sqlResult.get(i));
			}
			else {
				Integer value = donorCodes.get(donorCode);
				if ( value < size) {
					value++;
					donorCodes.put(donorCode, value);
					result.add(sqlResult.get(i));
				}
			}

		}
		return result;
	}

	private List<Object[]> filterTopDonorsQueryResult(List<Object[]> sqlResult, LinkedHashMap<String, List<Object[]>> channels,  LinkedHashMap<String, List<Object[]>> recipients, LinkedHashMap<String, List<Object[]>> dacSectors) {
		List<Object[]> result = new ArrayList<Object[]>();
		HashMap<String, Integer> donorCodes = new HashMap<String, Integer>();
		for (int i = 0 ; i < sqlResult.size() ; i++) {
			String donorCode = sqlResult.get(i)[0].toString();
			if (!donorCodes.containsKey(donorCode)) {
				donorCodes.put(donorCode, 1);
				result.add(sqlResult.get(i));
			}
		}
		return result;
	}

	private List<List<String>> globalTopDonorsTableContents(List<Object[]> result, int rows) {
		List<List<String>> tableContents = new ArrayList<List<String>>();
		int i = 0;
		for (Object[] a : result) {
			if ( i >= rows )
				break;

			List<String> row = new ArrayList<String>();
			for (Object o : a)
				if (o != null)
					row.add(o.toString());
				else
					row.add("n.a.");
			tableContents.add(row);

			i++;
		}
		return tableContents;
	}


	/**
	 *
	 * This is used for the group by in the first column
	 *
	 * it's added a number to each field, used as workaround in the goup by of the table
	 *
	 * @param result
	 * @return
	 */
	private List<List<String>> globalTableContentsGroupBy(List<Object[]> result) {
		List<List<String>> tableContents = new ArrayList<List<String>>();
		HashMap<String, String> value = new HashMap<String, String>();
		int counter=1;
		for (Object[] a : result) {
			List<String> row = new ArrayList<String>();
			int i=0;
			for (Object o : a) {
				if (o != null) {
					if ( i == 0 ) {
						if ( !value.containsKey(o.toString())) {
							value.put(o.toString(), getNumber(counter));
							counter++;
						}

						row.add("#" + value.get(o.toString()) + " - " + o.toString());
					}
					else
						row.add(o.toString());
				}
				else
					row.add("n.a.");
				i++;
			}
			tableContents.add(row);
		}
		return tableContents;
	}

	
	
		private List<List<String>> globalTableContents(List<Object[]> result) {
		List<List<String>> tableContents = new ArrayList<List<String>>();
		for (Object[] a : result) {
			List<String> row = new ArrayList<String>();
			int i=0;
			for (Object o : a) {
				if (o != null) {
					row.add(o.toString());
				}
				else
					row.add("n.a.");
				i++;
			}
			tableContents.add(row);
		}
		return tableContents;
	}

	private static String getNumber(int n) {
		if ( n < 10 )
			return "00" + n;
		else if ( n < 100 )
			return "0" + n;
		else
			return String.valueOf(n);
	}


	public OLAPChartConfiguration createChartConfiguration(String title, String xLabel, String yLabel, OLAPChartType chartType) {
		OLAPChartConfiguration c = new OLAPChartConfiguration();
		c.setChartType(chartType);
		c.setLegend(false);
		c.setPlotOrientation(PlotOrientation.VERTICAL);
		c.setTooltips(false);
		c.setUrls(false);
		c.setTitle(title);
		c.setxLabel(xLabel);
		c.setyLabel(yLabel);
		return c;
	}

	private String findSQLColumnName(String columnCode, List<Descriptor> descriptors) {
		for (Descriptor d : descriptors)
			if (d.getCode().equalsIgnoreCase(columnCode))
				return d.getSqlColName();
		return null;
	}

	public Map<String, String> getKeyMessages(String code) {
		List<QuantitativeCoreContent> contents = adamDao.getKeyMessages(code);
		Map<String, String> map = new HashMap<String, String>();
		for (QuantitativeCoreContent q : contents)
			map.put(q.getSecondIndicator(), q.getFeatureCode());
		return map;
	}

	public List<CodeVo> findAll(String prefix, String codingSystemCode) {

		List<Code> codes = adamDao.findAll(prefix, codingSystemCode);
		List<CodeVo> vos = new ArrayList<CodeVo>();
		
		for (Code c : codes) {
			vos.add(code2vo(c));
		}	
		
	    //LOGGER.info("findAll vos size "+vos.size());
		return vos;
	}
	
	
	public List<CodeVo> findChildren(String prefix, List<String> parents, String codingSystemCode) {
		
		
		//DEBUG 

		StringBuilder likeStatement = new StringBuilder();
		
		likeStatement.append("(");
		
		int i = 0;
		for(String parent: parents){
			likeStatement.append(" c.code LIKE '"+prefix + parent+"%'");
			
			if(i < parents.size() - 1)
				likeStatement.append(" OR ");
				
			i++;
		}
		
		likeStatement.append(")");
		
		String hql = "SELECT c " +
					 "FROM CodingSystem cs " +
					 "INNER JOIN cs.codes c " +
					 "WHERE cs.code = :code " +
					 "AND " + likeStatement.toString() +
					 "ORDER BY c.label ";
     	//LOGGER.info(hql);
		
		
		//DEBUG END
	
		
		
		List<Code> codes = adamDao.findHierarchy(prefix, parents, codingSystemCode);
		List<CodeVo> vos = new ArrayList<CodeVo>();
		
		for (Code c : codes) {
			//LOGGER.info("RESULTS CODE: "+c.getCode());
			vos.add(code2vo(c));
		}	
		
		//LOGGER.info("RESULT Vos size "+vos.size());
		
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


	/** TODO: reference period **/
	public String createReport(HashMap<String, ADAMQueryVO> qvos, ADAMReportBeanVO reportBean) {
//		LOGGER.info("reportType: " + reportBean.getReportType());

		if ( reportBean.getReportType().equals("GLOBAL_REPORT")){
			createReportVO(qvos, reportBean);
		}
		else if (reportBean.getReportType().contains("ASKADAM")) {
			createReportVO(qvos, reportBean);
		}
		else if (reportBean.getReportType().equals("CURRENTVIEW")) {
			// the resources are already setted

		}
		//else if (reportBean.getReportType().contains("DONORPROFILE")) {
			//createReportVO(qvos, reportBean);

		//}

		CreateADAMReport report = new CreateADAMReport(reportBean);
//		return report.createReport();
		return report.createExportReport();
	}

	private void createReportVO(HashMap<String, ADAMQueryVO> qvos, ADAMReportBeanVO reportBean) {

		for(String key : qvos.keySet()) {
			LOGGER.info("key: " + key);
			LOGGER.info("getTitle: " + qvos.get(key).getTitle());
			LOGGER.info("getOutputType: " + qvos.get(key).getOutputType());

			reportBean.getResources().put(key, askADAM(qvos.get(key)));
		}
	}

//	private void createGlobalAdamReportVO(HashMap<String, ADAMQueryVO> qvos, ADAMReportBeanVO reportBean) {
//		for(String key : qvos.keySet()) {
//			LOGGER.info("key: " + key);
//			LOGGER.info("getTitle: " + qvos.get(key).getTitle());
//			LOGGER.info("getOutputType: " + qvos.get(key).getOutputType());
//
//			reportBean.getResources().put(key, askADAM(qvos.get(key)));
//		}
//	}

	public List<ClientGeoView> getLayersByCodingSystem(String codingSystemTitle, String prefix) {
		LOGGER.info(codingSystemTitle);
		LOGGER.info(prefix);
		List<Code> layers = codecDao.findAllCodesByCSTitleAndPrefix(codingSystemTitle, prefix);
		List<ClientGeoView> clientGeoViews = new ArrayList<ClientGeoView>();
		for (Code layer : layers) {
//			WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByTitle(layer.getLabel());
			WMSMapProvider dbLayer = wmsMapProviderDao.getLayerByCode(layer.getLabel());
			ClientGeoView c = MapUtils.wmsMapProvider2ClientGeoView(urlFinder.geoserverUrl, dbLayer);
			if ( layer.getDescription().equals("off"))
				c.setHidden(true);
			else
				c.setHidden(false);
			clientGeoViews.add(c);
		}
		return clientGeoViews;
	}


	/** this create the map image, and legend image **/
	public void createGlobalMapPath(List<ClientGeoView> cgvlist, ADAMResultVO rvo) {
		ClientMapView cmv = buildGlobalClientMapView(cgvlist);


		MapViewUtils mapViewUtils = new MapViewUtils();
		mapViewUtils.setWmsMapProviderDao(wmsMapProviderDao);
		MapView mapView = mapViewUtils.build(cmv);

		List<GeoView> gvList = new ArrayList<GeoView>();
		for (ClientGeoView clientGeoView : cgvlist) {
			gvList.add(mapViewUtils.buildGeoView(clientGeoView, false));
		}

		MapRetriever map = new MapRetriever(mapView,System.getProperty("java.io.tmpdir"));
		mapView.setWidth(1024);
		mapView.setHeight(768);
		BufferedImage image = map.getMapImage();
		String nameFile= String.valueOf((Math.random() * 10)) + ".png";
		try{
			ImageIO.write(image, "png", new File(System.getProperty("java.io.tmpdir") + File.separator + nameFile));
		} catch (Exception e) {

		}

		// legends
		GeoView gv = new GeoView();
		try {
			for (int i=0; i < cmv.getLayerList().size(); i++) {
				System.out.println("clientGeoView: " + cmv.getLayerList().get(i).getLayerName());
//				String url = MapUtils.getLegendURL(cmv.getLayerList().get(i));
				gv = mapViewUtils.buildGeoView(cmv.getLayerList().get(i), false);
			}
		}catch (Exception e) {
		}


		LegendRetriever lr = new LegendRetriever();

		String legendFile = new String();
		List<File> legends = new ArrayList<File>();



			lr.setLayer(gv);

			try {
				legendFile = String.valueOf((Math.random() * 10)) + ".png";
				String legendFileExport = System.getProperty("java.io.tmpdir") + File.separator + legendFile;


				File legendImage = new File(legendFileExport);

				if(lr.retrieve(legendImage)) {
//					LOGGER.info("Legend added");

				} else {
					LOGGER.warn("ERROR adding legend.");
				}
				legends.add(legendImage);
			} catch (Exception e) {
				e.printStackTrace();
			}

		// map path
		rvo.setBigImagePath(nameFile);
		rvo.setSmallImagePath(legendFile);
		LOGGER.info(nameFile);
		LOGGER.info(legendFile);
	}

	private ClientMapView buildGlobalClientMapView(List<ClientGeoView> cgvlist) {
		ClientMapView cmv = new ClientMapView();
		cmv.setTitle("");
		cmv.setBbox(new ClientBBox("EPSG:4326", -180, 180, -90, 90));
		for(ClientGeoView cgv : cgvlist)
			cmv.addLayer(cgv);

		return cmv;
	}


	/***
	 *
	 * this method generate the view that has to be joined to the layer
	 *
	 * **/
	public JoinDatasetVO joinDataset(ADAMQueryVO qvo) {
		JoinDatasetVO jvo = new JoinDatasetVO();

		String quantityColumn = "usd_disbursement";
		String aggregationType = "SUM";

		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}

		LOGGER.info("aggregation: " + quantityColumn);
		LOGGER.info("aggregationType: " + aggregationType);


		// find SQL tablenames from Resource's codes
		List<String> tablenames = customDatasetDao.findTablenamesFromCodes(qvo.getDatasetCodes());
		if( tablenames.isEmpty() ) {
			qvo.setTableNames(qvo.getDatasetCodes());
		}
		else
			qvo.setTableNames(tablenames);

		String baseName = BirtUtil.randomChartName();

		/** VIEWS **/
//		String datasetViewName = "ADAM_DATASET_VIEW_" + baseName;
		String datasetViewName = "adam_dataset_view_" + baseName;
		jvo.setDatasetViewName(datasetViewName);

//		String prjViewName = "ADAM_PRJ_VIEW_" + baseName;
		String prjViewName = "adam_prj_view_" + baseName;
		jvo.setLayerJoinView(prjViewName);

		/** layer to be joined **/
		jvo.setLayer(qvo.getLayerName());

		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());
		switch (c) {
//			case MAP_COUNTRIES_BY_DONOR: adamDao.countriesBudgetByDonorVIEW(qvo.getSelects().get(0), qvo.getTableNames().get(0), datasetViewName); break;
			case MAP_COUNTRIES_BY_DONOR: adamDao.countriesBudgetByDonorVIEW(qvo.getWheres(), qvo.getTableNames().get(0), datasetViewName, quantityColumn, aggregationType); break;
		}



		LOGGER.info("datasetViewName: " + jvo.getDatasetViewName());
		LOGGER.info("prjViewName: " + jvo.getLayerJoinView());


		return jvo;
	}

	public Long createDonorMatrixViews(){
		Dataset dataset = datasetDao.findByCode("ADAM_CRS");

		Long viewId = adamDao.createDonorMatrixViewsAndResource(dataset);

		fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.READ, viewId, true);
		fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.WRITE, viewId, true);
		fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DELETE, viewId, true);
		fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DOWNLOAD, viewId, true);

		return viewId;
	}

    public ADAMDonorMatrixVO populateEmptyAxesDonorMatrix(ADAMDonorMatrixVO vo, List<String> dates) {

    	OLAPParametersVo populatedParams = null;

    	OLAPParametersVo params = vo.getOlapParametersVO();

    	LOGGER.info("x: " + params.getXLabels().isEmpty());
    	LOGGER.info("y: " + params.getZLabels().isEmpty());

    	//Global View
    	if(params.getXLabels().isEmpty() && params.getZLabels().isEmpty()){
    		 LOGGER.info("Global View: "+ populatedParams);

    		//Selected dates
    		Map<String, List<String>> filters = new HashMap<String, List<String>>();
    		filters.put("year", dates);

    		String sql = "";

    		LOGGER.info(vo.getSelectedDataset());

    		switch (vo.getSelectedDataset()) {
				case ADAM_CRS: sql = ADAMQueryBuilder.getDonorMatrixTop6Donors(filters, customDatasetDao.find("ADAM_CRS").getTablename()); break;
				//case AID_DATA: sql = ADAMQueryBuilder.getDonorMatrixTop6Donors(filters,  customDatasetDao.find("AID_DATA").getTablename()); break;
				//case ADAM_CRS_AID_DATA: sql = ADAMQueryBuilder.getDonorMatrixTop6Donors(filters); break;
		    }

    		//GET THE DONOR CODES
    		List<Object> result = adamDao.tableQueryUniqueValue(sql);

    		List<String> donorCodes = new ArrayList<String>();
    		List<String> recipientCodes = new ArrayList<String>();



    		if(result.size() > 0){
    			//LOGGER.info(result.size());

				params.getXLabels().clear();
				params.getxCodes().clear();
				donorCodes.clear();

				for(int i=0; i < result.size(); i++) {
					//params.getXLabels().put(sqlResult.get(i)[0].toString(), sqlResult.get(i)[1].toString());
					//params.getxCodes().put(sqlResult.get(i)[1].toString(), sqlResult.get(i)[0].toString());
					donorCodes.add(result.get(i).toString());

				}
				filters.put("donorcode", donorCodes);

				//LOGGER.info(donorCodes.size());

				//GET THE DONOR LABELS
				sql = ADAMQueryBuilder.getDonorLabels(donorCodes);

				//LOGGER.info(sql);

				List<Object[]> sqlResult = adamDao.tableQuery(sql);

				if(sqlResult.size() > 0){
				   for (String code: donorCodes){

					for(int i=0; i < sqlResult.size(); i++) {
						//LOGGER.info("code  "+ code + " : sqlResult.get(i)[0].toString() "+sqlResult.get(i)[0].toString());
						if(code.equals(sqlResult.get(i)[0].toString())) {
							params.getXLabels().put(sqlResult.get(i)[0].toString(), sqlResult.get(i)[1].toString());
							params.getxCodes().put(sqlResult.get(i)[1].toString(), sqlResult.get(i)[0].toString());
						}
					}
					}
				}

				//LOGGER.info("donors = "+params.getXLabels().size());
				params.setSortXLabels(false);
				params.setOlapTitle("Most Financed Countries by the Top 6 Resource Partners");

				//GET THE RECIPIENTS
				sql = ADAMQueryBuilder.getDonorMatrixTop14CountriesForFilteredDonors(filters);
				result = adamDao.tableQueryUniqueValue(sql);

				if(result.size() > 0){
					recipientCodes.clear();
					for(int i=0; i < result.size(); i++) {
						recipientCodes.add(result.get(i).toString());
					}
				}
				//GET THE RECIPIENT COUNTRY LABELS
				sql = ADAMQueryBuilder.getRecipientLabels(recipientCodes);
				sqlResult = adamDao.tableQuery(sql);

				if(sqlResult.size() > 0){
					for(int i=0; i < sqlResult.size(); i++)
					params.getZLabels().put(sqlResult.get(i)[0].toString(),  sqlResult.get(i)[1].toString());
				}

				populatedParams = params;

			/**	CustomDataset dataset = customDatasetDao.find("ADAM_DONOR_CONVERSION_TABLE");

				sql = ADAMQueryBuilder.dacMembershipForDonors(donorCodes, dataset.getTablename());

				sqlResult = adamDao.tableQuery(sql);
				vo.getDonorsDacMembership().clear();

				if(sqlResult.size() > 0){
					// LOGGER.info("sqlResult3 size = " + sqlResult.size());
					for(int j=0; j < sqlResult.size(); j++) {
						 //LOGGER.info("sqlResult3 size = " + sqlResult.get(j)[0].toString() + " : "+sqlResult.get(j)[1].toString());
						vo.getDonorsDacMembership().put(sqlResult.get(j)[1].toString(),  sqlResult.get(j)[0].toString());
					}
				}**/
		   }



		/**	List<String> donorCodes = new ArrayList<String>();

			if(sqlResult.size() > 0){
				params.getXLabels().clear();
				params.getxCodes().clear();
				for(int i=0; i < sqlResult.size(); i++) {
					params.getXLabels().put(sqlResult.get(i)[0].toString(), sqlResult.get(i)[1].toString());
					params.getxCodes().put(sqlResult.get(i)[1].toString(), sqlResult.get(i)[0].toString());
					donorCodes.add(sqlResult.get(i)[0].toString());
				}
				filters.put("donorcode", donorCodes);

				params.setSortXLabels(false);
				params.setTitle("Most Financed Countries by the Top 6 Resource Partners");

				sql = ADAMQueryBuilder.getDonorMatrixTop14CountriesForFilteredDonors(filters);
				sqlResult = adamDao.tableQuery(sql);

				for(int i=0; i < sqlResult.size(); i++) {
					params.getZLabels().put(sqlResult.get(i)[0].toString(),  sqlResult.get(i)[1].toString());
				}
				populatedParams = params;

				CustomDataset dataset = customDatasetDao.find("ADAM_DONOR_CONVERSION_TABLE");

				sql = ADAMQueryBuilder.dacMembershipForDonors(params.getXLabels(), dataset.getTablename());

				sqlResult = adamDao.tableQuery(sql);
				vo.getDonorsDacMembership().clear();

				if(sqlResult.size() > 0){
					 LOGGER.info("sqlResult3 size = " + sqlResult.size());
					for(int j=0; j < sqlResult.size(); j++) {
						 //LOGGER.info("sqlResult3 size = " + sqlResult.get(j)[0].toString() + " : "+sqlResult.get(j)[1].toString());
						vo.getDonorsDacMembership().put(sqlResult.get(j)[1].toString(),  sqlResult.get(j)[0].toString());
					}
				}
		   }**/


    	}
		//Standard Selections Driven View
		else {
			LOGGER.info("Menu driven: "+ populatedParams);
			params.setSortXLabels(true);
			populatedParams = populateStandardEmptyAxesDonorMatrix(vo, dates);
		}

    	LOGGER.info("populatedParams: "+ populatedParams);

    	vo.setOlapParametersVO(populatedParams);

    	 LOGGER.info("vo.getOlapParametersVO(populatedParams);: "+ vo.getOlapParametersVO());

    	 LOGGER.info("end ............."+params.isSortXLabels());
    	return vo;
	}

	private OLAPParametersVo populateStandardEmptyAxesDonorMatrix(ADAMDonorMatrixVO vo, List<String> dates) {


		OLAPParametersVo params = vo.getOlapParametersVO();

		String sql = "";

		LOGGER.info("X Labels isEmpty "+ params.getXLabels().isEmpty() + " : Z Labels isEmpty" + params.getZLabels().isEmpty());

		//Selected dates
		Map<String, List<String>> dateFilter = new HashMap<String, List<String>>();
		dateFilter.put("year", dates);
		StringBuilder title = new StringBuilder();

		if(!params.getZLabels().isEmpty() && params.getZLabels().size() > 1) {
			LOGGER.info("params.getZLabels().isEmpty() ");
				sql = ADAMQueryBuilder.getRecipientsWithData(params.getZLabels(), dateFilter, params.getXLabels());
				List<Object[]> sqlResult = adamDao.tableQuery(sql);

				if(sqlResult.size() > 0){
					LOGGER.info("recipients with data size() = "+sqlResult.size());

					if(params.getZLabels().size() > sqlResult.size()){
						title.append("Recipient Countries");
					} else
						title.append("");

					params.getZLabels().clear();
					for(int i=0; i < sqlResult.size(); i++) {
						params.getZLabels().put(sqlResult.get(i)[0].toString(),  sqlResult.get(i)[1].toString());
					}
				}
		}

		LOGGER.info("params.getXLabels() " + params.getXLabels());
		LOGGER.info("!params.getXLabels().isEmpty() && params.getXLabels().size() > 1 " + (!params.getXLabels().isEmpty() && params.getXLabels().size() > 1));
//		if(!params.getXLabels().isEmpty() && params.getXLabels().size() > 1) {
		if(!params.getXLabels().isEmpty() && params.getXLabels().size() > 1) {
			LOGGER.info("donors().isEmpty() ");
			sql = ADAMQueryBuilder.getDonorsWithData(params.getXLabels(), dateFilter);
			List<Object[]> sqlResult = adamDao.tableQuery(sql);

			if(sqlResult.size() > 0){
				LOGGER.info("donors with data size() = "+sqlResult.size());

				if(params.getXLabels().size() > sqlResult.size()){
					if(title.length() > 0)
						title.append(" and Resource Partners");
					else
						title.append("Resource Partners");
				} else
					title.append("");

				params.getXLabels().clear();
				params.getxCodes().clear();
				for(int i=0; i < sqlResult.size(); i++) {
					params.getXLabels().put(sqlResult.get(i)[0].toString(),  sqlResult.get(i)[1].toString());
					params.getxCodes().put(sqlResult.get(i)[1].toString(), sqlResult.get(i)[0].toString());
				}


			}
		}

		if(title.length() > 0) {
			title.append(" with no data are not shown in the matrix");
			params.setOlapTitle(title.toString());
		}

		/***if(!params.getXLabels().isEmpty() && params.getXLabels().size() > 1) {

			int count = 0;

			if(!vo.getNonDacDonorCodes().isEmpty()){
				CustomDataset dataset = customDatasetDao.find("ADAM_NONDAC_ODACOMMITMENTS");
				if(dataset!=null){
					sql = ADAMQueryBuilder.getNonDacDonorsWithData(dataset.getTablename(), vo.getNonDacDonorCodes(), dateFilter);
					List<Object[]> sqlResult = adamDao.tableQuery(sql);

					count += sqlResult.size();

					if(sqlResult.size() > 0){
						LOGGER.info("NON dac donors with data size() = "+sqlResult.size());

						params.getXLabels().clear();
						params.getxCodes().clear();
						for(int i=0; i < sqlResult.size(); i++) {
							params.getXLabels().put(sqlResult.get(i)[0].toString(),  sqlResult.get(i)[1].toString());
							params.getxCodes().put(sqlResult.get(i)[1].toString(), sqlResult.get(i)[0].toString());
						}
					}
				}
			}

			if(!vo.getDacDonorCodes().isEmpty()){
				sql = ADAMQueryBuilder.getDacDonorsWithData(vo.getDacDonorCodes(), dateFilter);

				List<Object[]> sqlResult = adamDao.tableQuery(sql);

				count += sqlResult.size();

				if(sqlResult.size() > 0){
					LOGGER.info("Dac donors with data size() = "+sqlResult.size());

					if(vo.getNonDacDonorCodes().isEmpty()) {
						params.getXLabels().clear();
						params.getxCodes().clear();
					}

					for(int i=0; i < sqlResult.size(); i++) {
						params.getXLabels().put(sqlResult.get(i)[0].toString(),  sqlResult.get(i)[1].toString());
						params.getxCodes().put(sqlResult.get(i)[1].toString(), sqlResult.get(i)[0].toString());
					}
				}
			}

			if((vo.getNonDacDonorCodes().size() + vo.getDacDonorCodes().size()) > count){
				if(title.length() > 0)
					title.append(" and Resource Partners");
				else
					title.append("Resource Partners");
			} else
				title.append("");

			LOGGER.info("Original no.of donors = "+vo.getNonDacDonorCodes().size() + vo.getDacDonorCodes().size() + ": final count "+params.getXLabels().size());

		}

		if(title.length() > 0) {
			title.append(" with no data are not shown in the matrix");
			params.setTitle(title.toString());
		}	**/

		return populateAxes(vo, params, dates);
	}


	private OLAPParametersVo populateAxes(ADAMDonorMatrixVO vo, OLAPParametersVo params, List<String> dates) {

		String sql="";
		Map<String, List<String>> dateFilter = new HashMap<String, List<String>>();
		dateFilter.put("year", dates);
		//CustomDataset dataset = customDatasetDao.find("ADAM_NONDAC_ODACOMMITMENTS");

		List<String> donorCodes = new ArrayList<String>();
		List<String> recipientCodes = new ArrayList<String>();


		LOGGER.info("params.getZLabels(): " + params.getZLabels());
		LOGGER.info("params.getXLabels(): " + params.getXLabels());
		if(params.getZLabels().isEmpty()) {
			donorCodes.clear();

			for(String dCode: params.getXLabels().keySet() ) {
				donorCodes.add(dCode);
			}

			sql =  ADAMQueryBuilder.recipientCountriesForDonor(params.getXLabels(), dateFilter);
           //List<Object[]> sqlResult = adamDao.tableQueryUniqueValue(sql);
			List<Object> sqlResult = adamDao.tableQueryUniqueValue(sql);

			if(sqlResult.size() > 0){
				recipientCodes.clear();
				for(int j=0; j < sqlResult.size(); j++) {
					recipientCodes.add(sqlResult.get(j).toString());
					//params.getZLabels().put(sqlResult.get(j)[0].toString(),  sqlResult.get(j)[1].toString());
				}

				 sql =  ADAMQueryBuilder.getRecipientLabels(recipientCodes);
				 List<Object[]> result = adamDao.tableQuery(sql);

				if(result.size() > 0){
					for(int j=0; j < result.size(); j++)
						params.getZLabels().put(result.get(j)[0].toString(),  result.get(j)[1].toString());
				}
			}
		}
		if(params.getXLabels().isEmpty()) {
			recipientCodes.clear();
			for(String rCode: params.getZLabels().keySet() ) {
				recipientCodes.add(rCode);
			}

            LOGGER.info(vo.getSelectedDataset());

    		switch (vo.getSelectedDataset()) {
				case ADAM_CRS: sql = ADAMQueryBuilder.getDonorsForRecipientByDataset(dateFilter, params.getZLabels(), customDatasetDao.find("ADAM_CRS").getTablename()); break;
				//case AID_DATA: sql = ADAMQueryBuilder.getDonorsForRecipientByDataset(dateFilter,  params.getZLabels(), customDatasetDao.find("AID_DATA").getTablename()); break;
				//case ADAM_CRS_AID_DATA: sql = ADAMQueryBuilder.donorsForRecipientCountry(params.getZLabels(), dateFilter); break;
		    }

		//sql = ADAMQueryBuilder.donorsForRecipientCountry(params.getZLabels(), dateFilter);

			//List<Object[]> sqlResult2 = adamDao.tableQuery(sql);
			LOGGER.info(sql);

			List<Object> sqlResult2 = adamDao.tableQueryUniqueValue(sql);
			//LOGGER.info(""+ sqlResult2.size());
			if(sqlResult2.size() > 0){
				donorCodes.clear();
				for(int j=0; j < sqlResult2.size(); j++) {
					donorCodes.add(sqlResult2.get(j).toString());
			//		LOGGER.info("donorCode = "+ sqlResult2.get(j).toString());
//					params.getXLabels().put(sqlResult2.get(j)[0].toString(),  sqlResult2.get(j)[1].toString());
//					params.getxCodes().put(sqlResult2.get(j)[1].toString(), sqlResult2.get(j)[0].toString());
				}

				sql =  ADAMQueryBuilder.getDonorLabels(donorCodes);
				List<Object[]> sqlResult = adamDao.tableQuery(sql);


				LOGGER.info("getDonorLabels sqlResult  "+ sqlResult.size());
				if(sqlResult.size() > 0){
					for(int j=0; j < sqlResult.size(); j++) {
						params.getXLabels().put(sqlResult.get(j)[0].toString(),  sqlResult.get(j)[1].toString());
						params.getxCodes().put(sqlResult.get(j)[1].toString(), sqlResult.get(j)[0].toString());
//						params.getZLabels().put(sqlResult.get(j)[0].toString(),  sqlResult.get(j)[1].toString());
					}
				}
			}
		}


		// SIMONE!!! ATTEMPT
		// It was missing the filling of the donorcodes and recipientcodes
		// if both where selected\
		if ( donorCodes.isEmpty()) {
			for(String dCode: params.getXLabels().keySet() ) {
				donorCodes.add(dCode);
			}
		}

		if( recipientCodes.isEmpty()) {
			for(String rCode: params.getZLabels().keySet() ) {
				recipientCodes.add(rCode);
			}
		}
		///////////////////////////////////////////


	/****	CustomDataset dataset = customDatasetDao.find("ADAM_DONOR_CONVERSION_TABLE");

		LOGGER.info("donorCodes SIZE = "+ donorCodes.size()+ ": list= "+ donorCodes);

		sql = ADAMQueryBuilder.dacMembershipForDonors(donorCodes, dataset.getTablename());

		LOGGER.info("dacMembershipForDonors sql= "+ sql);

		List<Object[]> sqlResult3 = adamDao.tableQuery(sql);


		HashMap<String, String> donorsDacMembership = new HashMap<String, String>();
		if(sqlResult3.size() > 0){
			donorsDacMembership.clear();
			for(int j=0; j < sqlResult3.size(); j++) {
				donorsDacMembership.put(sqlResult3.get(j)[1].toString(),  sqlResult3.get(j)[0].toString());
			}
		}
		//LOGGER.info("Size of donorsDacMembership = "+ String.valueOf(donorsDacMembership.size()));
		vo.setDonorsDacMembership(donorsDacMembership);
		****/

		//LOGGER.info("GET donorsDacMembership = "+ vo.getDonorsDacMembership().size());
		/**if(params.getZLabels().isEmpty()) {
			if(!vo.getNonDacDonorCodes().isEmpty() && dataset!=null) {
				sql =  ADAMQueryBuilder.recipientCountriesForNonDacDonor(dataset.getTablename(),vo.getNonDacDonorCodes(), "ADAMRecipients", dateFilter);
				List<Object[]> sqlResult = adamDao.tableQuery(sql);
				if(sqlResult.size() > 0){
					for(int j=0; j < sqlResult.size(); j++) {
						params.getZLabels().put(sqlResult.get(j)[0].toString(),  sqlResult.get(j)[1].toString());
					}
				}
			}
			if(!vo.getDacDonorCodes().isEmpty()){
				sql =  ADAMQueryBuilder.recipientCountriesForDacDonor(vo.getDacDonorCodes(), "ADAMRecipients", dateFilter);

				List<Object[]> sqlResult = adamDao.tableQuery(sql);
				if(sqlResult.size() > 0){
					for(int j=0; j < sqlResult.size(); j++) {
						params.getZLabels().put(sqlResult.get(j)[0].toString(),  sqlResult.get(j)[1].toString());
					}
				}
			}
		}

		if(params.getXLabels().isEmpty()) {
			if(!vo.getNonDacDonorCodes().isEmpty() && dataset!=null) {
				sql = ADAMQueryBuilder.nonDacDonorsForRecipientCountry(dataset.getTablename(), params.getZLabels(), dateFilter);
				List<Object[]> sqlResult2 = adamDao.tableQuery(sql);

				if(sqlResult2.size() > 0){
					for(int j=0; j < sqlResult2.size(); j++) {
						params.getXLabels().put(sqlResult2.get(j)[0].toString(),  sqlResult2.get(j)[1].toString());
						params.getxCodes().put(sqlResult2.get(j)[1].toString(), sqlResult2.get(j)[0].toString());
					}
				}
			}

			if(!vo.getDacDonorCodes().isEmpty()){
				sql = ADAMQueryBuilder.donorsForRecipientCountry(params.getZLabels(), dateFilter);

				List<Object[]> sqlResult2 = adamDao.tableQuery(sql);
				if(sqlResult2.size() > 0){
					for(int j=0; j < sqlResult2.size(); j++) {
						params.getXLabels().put(sqlResult2.get(j)[0].toString(),  sqlResult2.get(j)[1].toString());
						params.getxCodes().put(sqlResult2.get(j)[1].toString(), sqlResult2.get(j)[0].toString());
					}
				}
			}
		}**/

		sortLabels(params);

		return params;
	}

	/***
	 *
	 * this method generates the donor matrix models to populate the Donor Matrix Grid
	 *
	 * **/


public List<DonorMatrixModel> getDonorMatrixRows(OLAPParametersVo params) {

	//Map<String, String> xList, Map<String, String> yList, Map<String, String> zList, String year
	//Create some dummy Parameters
	//set X = donorcode
	//set Y = assistanceType
	//set Z = recipientcode

	 List<DonorMatrixModel> models = new ArrayList<DonorMatrixModel>();

	 LOGGER.info("params: " + params);

	 OLAPParameters p = vo2parameters(params);

	 LOGGER.info("OLAPParameters: " + p);

	//ResourceView rv = resourceViewDao.findByCode("ADAM_DONOR_MATRIX_VIEW");
	//p.setDataSourceId(rv.getResourceId());



	// create cube
	List<OLAPBean> beans = olapDao.createCube(p);

	LOGGER.info("beans: " + beans);
	if(!beans.isEmpty()) {
	 LOGGER.info("!beans.is NOT Empty()");
	  models = getFourDimensionsDonorMatrixModels(beans, p, params.isSortXLabels());

	 /** if(!p.getZLabels().isEmpty() && models.size() > 0) {

		  List<String> list = new ArrayList<String>();
		  list.add("RECIPIENT_KEYSOS");
		  List<String> tableNameResult = customDatasetDao.findTablenamesFromCodes(list);
		  String tableName = null;

		  if(tableNameResult.size() > 0) {
			  for(int i=0; i < tableNameResult.size(); i++) {
				  tableName = tableNameResult.get(i).toString();
			  }
		  }
		  else
			  LOGGER.info("tableName Result is 0 AND TableName set to NULL");

		  List<Object[]> keySosAndOrsResult = getRecipientKeySO(p.getZLabels(), tableName);

		  Map<String, DonorMatrixModel> modelMap = new HashMap<String, DonorMatrixModel>();
		  for(DonorMatrixModel model: models)
			  modelMap.put(model.getRecipientCode(), model);

		  if(keySosAndOrsResult.size() > 0)
			  addKeySOAndORs2(keySosAndOrsResult, modelMap, tableName);


	  }**/

	} else  LOGGER.info("beans.isEmpty()");

	return models;
}

/***
 *
 * this method generates the fao sector matrix models to populate the FAO Sector Matrix Grid
 *
 * **/
public OLAPParametersVo populateVOWithSectors(OLAPParametersVo params, String recipientCode) {

		OLAPFilterVo dateFilter = params.getFilters().get(0);
		final List<String> dates = new ArrayList<String>();
		for(String year: dateFilter.getDimensionMap().keySet())
		dates.add(year);

		Map<String, List<String>> filters = new HashMap<String, List<String>>();
		filters.put("year", dates);


		String sql = ADAMQueryBuilder.sectorsForRecipientCountry(recipientCode, params.getXLabels(), filters);

		List<Object[]> sqlResult = adamDao.tableQuery(sql);
		if(sqlResult.size() > 0){
			for(int j=0; j < sqlResult.size(); j++) {
				params.getZLabels().put(sqlResult.get(j)[0].toString(),  sqlResult.get(j)[1].toString());
			}
		}


	  return params;
	}


public List<FAOSectorMatrixModel> getFAOSectorMatrixRows(OLAPParametersVo params) {

//Map<String, String> xList, Map<String, String> yList, Map<String, String> zList, String year
//Create some dummy Parameters
//set X = donorcode
//set Y = commitment ODA
//set Z = sector


 List<FAOSectorMatrixModel> models = new ArrayList<FAOSectorMatrixModel>();
 OLAPParameters p = vo2parameters(params);

//ResourceView rv = resourceViewDao.findByCode("ADAM_DONOR_MATRIX_VIEW");
//p.setDataSourceId(rv.getResourceId());

// create cube
List<OLAPBean> beans = olapDao.createCube(p);

if(!beans.isEmpty()) {
  models = getFourDimensionsFAOSectorMatrixModels(beans, p, params.isSortXLabels());

}

return models;
}


private OLAPParameters vo2parameters(OLAPParametersVo vo) {

	OLAPParameters p = new OLAPParameters();
	//p.setDatasetId(vo.getDatasetId());
	//p.setDatasetTitle(vo.getDatasetTitle());
	p.setDataSourceId(vo.getDataSourceId());
	p.setDataSourceTitle(vo.getDataSourceTitle());
	p.setDataSourceType(vo.getDataSourceType());



	List<OLAPFilter> filters = new ArrayList<OLAPFilter>();
	for (OLAPFilterVo f : vo.getFilters())
		filters.add(vo2filter(f));
	p.setFilters(filters);

	p.setFunction(vo.getFunction());
	p.setValue(vo.getValue());
	p.setValueLabel(vo.getValueLabel());
	p.setW(vo.getW());
	p.setWLabel(vo.getWLabel());
	p.setWLabels(vo.getWLabels());
	p.setX(vo.getX());
	p.setXLabel(vo.getXLabel());
	p.setXLabels(vo.getXLabels());
	p.setShowXLabel(vo.isShowXLabel());
	p.setY(vo.getY());
	p.setYLabel(vo.getYLabel());
	p.setYLabels(vo.getYLabels());
	p.setShowYLabel(vo.isShowYLabel());
	p.setZ(vo.getZ());
	p.setZLabel(vo.getZLabel());
	p.setZLabels(vo.getZLabels());

	p.setShowSingleValues(vo.isShowSingleValues());
	p.setShowTotals(vo.isShowTotals());
	p.setChartType(vo.getChartType());
	p.setDecimals(vo.getDecimals());
	p.setOlapHtml(vo.getOlapHtml());

	p.setShowBaseLayer(vo.isShowBaseLayer());
	p.setUseFlashCharts(vo.isUseFlashCharts());
	p.setReportOrientation(vo.getReportOrientation());
	p.setMapClasses(vo.getMapClasses());
	p.setMinColor(vo.getMinColor());
	p.setMaxColor(vo.getMaxColor());

	p.setxUniqueValuesMap(vo.getxUniqueValuesMap());
	p.setyUniqueValuesMap(vo.getyUniqueValuesMap());
	p.setzUniqueValuesMap(vo.getzUniqueValuesMap());
	p.setwUniqueValuesMap(vo.getwUniqueValuesMap());

	p.setAggregateXAsMonthly(vo.isAggregateXAsMonthly());
	p.setAggregateZAsMonthly(vo.isAggregateZAsMonthly());
	p.setAggregateYAsMonthly(vo.isAggregateYAsMonthly());
	p.setAggregateWAsMonthly(vo.isAggregateWAsMonthly());

	p.setAggregateXAsYearly(vo.isAggregateXAsYearly());
	p.setAggregateZAsYearly(vo.isAggregateZAsYearly());
	p.setAggregateYAsYearly(vo.isAggregateYAsYearly());
	p.setAggregateWAsYearly(vo.isAggregateWAsYearly());

	p.setAggregatedTableViewName(vo.getAggregatedTableViewName());


	p.setAggregateSelectedYDimensions(vo.getAggregateSelectedYDimensions());
	p.setAggregateSelectedXDimensions(vo.getAggregateSelectedXDimensions());
	p.setAggregateSelectedZDimensions(vo.getAggregateSelectedZDimensions());

	p.setZHeader(vo.getZHeader());

	//p.setResourceViewID(vo.getResourceViewID());

	return p;
}

private OLAPFilter vo2filter(OLAPFilterVo vo) {
	OLAPFilter f = new OLAPFilter();
	f.setDimension(vo.getDimension());
	f.setDimensionLabel(vo.getDimensionLabel());
	f.setDimensionMap(vo.getDimensionMap());
	return f;
}
   private List<DonorMatrixModel> getFourDimensionsDonorMatrixModels(List<OLAPBean> beans, OLAPParameters params, boolean sortDonorLabels) {

	//  LOGGER.info("START .............");

	    // sort labels
	    params = OLAPHtml.sortLabels(params);

		// create label -> code maps
		params = OLAPHtml.createLabelCodeMaps(params);


		// format decimals
		OLAPHtml.formatDecimals(params.getDecimals(), params.getDecimalsForTotals(), params.getDecimalsForVariation());


		List<DonorMatrixModel> models = new ArrayList<DonorMatrixModel>();

		 // settings
		boolean showTotals = params.isShowTotals();

		// format decimals
		OLAPHtml.formatDecimals(params.getDecimals(), params.getDecimalsForTotals(), params.getDecimalsForVariation());

		// add empty beans
//		if (!OLAPHtml.isCore(beans))
	//		beans = OLAPHtml.addFourDimensionsEmptyBeans(beans, params);


		// create a map for the OLAP beans
//		Map<String, OLAPBean> olapBeansMap = OLAPHtml.create4DOlapBeansMap(beans, params); // TODO Check the creation of the 4D OLAP Beans Map

		//LOGGER.info("START 2 .............");

		Map<String, OLAPBean> olapBeansMap = create3DOlapBeansMap(beans, params); // TODO Check the creation of the 4D OLAP Beans Map

		// counter for the column summary
		int colSumCount = 0;

		// values
		for (String zLabel : params.getSortedZLabels()) {

			DonorMatrixModel model = new DonorMatrixModel();

			String zKey = params.getzCodes().get(zLabel);
			colSumCount++;

			// Recipient

			model.setRecipient(params.getZLabels().get(zKey));
			model.setRecipientCode(zKey);

			//Default value
			//model.setKeyComment("No available information on Key SOs or ORs");

			//TEST COMMENT OUT addKeySOAndORs(zKey, model);

			//TEST COMMENT OUT model.setYear("2008");

			// Commented out W for Now - Must Re-instate -- W labels and values
			int c = 0;

			//for (String wLabel : params.getSortedWLabels()) {

				//String wKey = params.getwCodes().get(wLabel);
				List<Double> faoSecSum = new ArrayList<Double>();

				List<Double> totalOdaSum = new ArrayList<Double>();

				// setKeySOs
			//model.setKeySOs(params.getWLabels().get(wKey));

			if(sortDonorLabels) {
				Iterator<String> iterator =params.getSortedXLabels().iterator();

			   for (int i = 0; i < params.getSortedXLabels().size(); i++) {
					String  xLabel = iterator.next();

					String xKey = params.getxCodes().get(xLabel);
					String yODAFAOSectors = params.getyCodes().get("FAO sectors");


					//StringBuilder key = OLAPHtml.generateKey(xKey, zKey, yTotalODA, wKey);
					StringBuilder key = generateKey(xKey, zKey, yODAFAOSectors);
					//LOGGER.info(key);
					OLAPBean b = olapBeansMap.get(key.toString());

				//	LOGGER.info("b.getStringValue() = "+ b.getStringValue());

					String property = "totalOda"+i+"fao";
					if (b!=null && b.getStringValue() != null && b.getStringValue() !="") {
					//	LOGGER.info(b.getStringValue());
						model.set("totalOda"+i+"fao", Double.valueOf(b.getStringValue()));
						faoSecSum.add(Double.valueOf(b.getStringValue()));
					} else {
					//	 LOGGER.info("sortDonorLabels:TOTAL ODA FAO b.getStringValue() IS NULL= ");
						model.set("totalOda"+i+"fao", null);
					}

					String yTotalODA = params.getyCodes().get("Total ODA");

					 key = generateKey(xKey, zKey, yTotalODA);
					 b = olapBeansMap.get(key.toString());


						if (b!=null && b.getStringValue() != null && b.getStringValue() !="") {
							model.set("totalOda"+i+"all", Double.valueOf(b.getStringValue()));
							totalOdaSum.add(Double.valueOf(b.getStringValue()));
						} else {
							// LOGGER.info("sortDonorLabels:TOTAL ODA ALL b.getStringValue() IS NULL= ");
							model.set("totalOda"+i+"all", null);
						}


				   /* HIDE PRODUCTION! String yProductionPercent = params.getyCodes().get("% on Production");

					 key = generateKey(xKey, zKey, yProductionPercent);
					 b = olapBeansMap.get(key.toString());

					 //LOGGER.info("b.getStringValue() = "+ b.getStringValue());
						if (b!=null && b.getStringValue() != null) {
							model.set("totalOda"+i+"percent", Double.valueOf(b.getStringValue()));
						} else {
							model.set("totalOda"+i+"percent", null);
						}
					*/

					/* if (b.getQuantity() != null) {
							model.setProductionPercentage(b.getQuantity());
						} else {
							model.setProductionPercentage(null);
					 }*/


					/** String yWebPage = params.getyCodes().get("Web link");

					// key = OLAPHtml.generateKey(xKey, zKey, yWebPage, wKey);
					 key = generateKey(xKey, zKey, yWebPage);
					 LOGGER.info(key);
					 b = olapBeansMap.get(key.toString());

					 if (b!=null && b.getStringValue() != null & b.getStringValue()!="") { // should be a string handling
							model.set("webPage"+i, b.getStringValue());
					} else {
						 LOGGER.info("sortDonorLabels: web link b.getStringValue() IS NULL= ");
							model.set("webPage"+i, null);
					}
					**/
				}
		} else {
			Iterator<Map.Entry<String, String>> iterator =params.getXLabels().entrySet().iterator();

			   for (int i = 0; i < params.getXLabels().size(); i++) {
					Map.Entry<String, String> entry = iterator.next();

					//final String xLabel = entry.getKey();
					final String xLabel = entry.getValue();

					//LOGGER.info("xLabel "+ xLabel);


				  // String  xLabel = iterator.next();

					String xKey = params.getxCodes().get(xLabel);

					//LOGGER.info("xKey "+ xKey);

					String yODAFAOSectors = params.getyCodes().get("FAO sectors");


					//StringBuilder key = OLAPHtml.generateKey(xKey, zKey, yTotalODA, wKey);
					StringBuilder key = generateKey(xKey, zKey, yODAFAOSectors);
					//LOGGER.info("NO SORT: "+ key);
					OLAPBean b = olapBeansMap.get(key.toString());


					String property = "totalOda"+i+"fao";
					if (b!=null && b.getStringValue() != null && b.getStringValue() !="") {
						model.set("totalOda"+i+"fao", Double.valueOf(b.getStringValue()));
						faoSecSum.add(Double.valueOf(b.getStringValue()));
					} else {
						   //LOGGER.info("TOTAL ODA FAO b.getStringValue() IS NULL");

						model.set("totalOda"+i+"fao", null);
					}

					String yTotalODA = params.getyCodes().get("Total ODA");

					 key = generateKey(xKey, zKey, yTotalODA);
					// LOGGER.info("yKey "+ key);
					 b = olapBeansMap.get(key.toString());

						if (b!=null && b.getStringValue() != null && b.getStringValue() !="") {
							//LOGGER.info(b.getStringValue());
							model.set("totalOda"+i+"all", Double.valueOf(b.getStringValue()));
							totalOdaSum.add(Double.valueOf(b.getStringValue()));
						} else {
							//LOGGER.info("TOTAL ODA ALL b.getStringValue() is null");
							model.set("totalOda"+i+"all", null);
						}


				   /* HIDE PRODUCTION! String yProductionPercent = params.getyCodes().get("% on Production");

					 key = generateKey(xKey, zKey, yProductionPercent);
					 b = olapBeansMap.get(key.toString());

					 //LOGGER.info("b.getStringValue() = "+ b.getStringValue());
						if (b!=null && b.getStringValue() != null) {
							model.set("totalOda"+i+"percent", Double.valueOf(b.getStringValue()));
						} else {
							model.set("totalOda"+i+"percent", null);
						}
					*/

					/* if (b.getQuantity() != null) {
							model.setProductionPercentage(b.getQuantity());
						} else {
							model.setProductionPercentage(null);
					 }*/


					/** String yWebPage = params.getyCodes().get("Web link");

					// key = OLAPHtml.generateKey(xKey, zKey, yWebPage, wKey);
					 key = generateKey(xKey, zKey, yWebPage);
					//LOGGER.info("web: "+key);
					 b = olapBeansMap.get(key.toString());

					 if (b!=null && b.getStringValue() != null & b.getStringValue()!="") { // should be a string handling
							model.set("webPage"+i, b.getStringValue());
					} else {
							model.set("webPage"+i, null);
					}**/

				}
		}
				c++;

				// add row summary
				if (showTotals) {
					// LOGGER.info(OLAPDaoUtils.sum(row));
					 //OLAPHtml.twoDigit.format(OLAPDaoUtils.sum(row));
					model.set("totalFAOSum", sum(faoSecSum));
					model.set("totalOdaSum", sum(totalOdaSum));

				}


			//}

			models.add(model);
		}

		// return result

		// LOGGER.info("END ............."+sortDonorLabels);
		return models;
	}

   private List<FAOSectorMatrixModel> getFourDimensionsFAOSectorMatrixModels(List<OLAPBean> beans, OLAPParameters params, boolean sortDonorLabels) {

		    // sort labels
		    params = OLAPHtml.sortLabels(params);

			// create label -> code maps
			params = OLAPHtml.createLabelCodeMaps(params);


			// format decimals
			OLAPHtml.formatDecimals(params.getDecimals(), params.getDecimalsForTotals(), params.getDecimalsForVariation());


			List<FAOSectorMatrixModel> models = new ArrayList<FAOSectorMatrixModel>();

			 // settings
			boolean showTotals = params.isShowTotals();

			// format decimals
			OLAPHtml.formatDecimals(params.getDecimals(), params.getDecimalsForTotals(), params.getDecimalsForVariation());

			Map<String, OLAPBean> olapBeansMap = create3DOlapBeansMap(beans, params); // TODO Check the creation of the 4D OLAP Beans Map

			// counter for the column summary
			int colSumCount = 0;

			 //Reference OR
			 CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
			 String sql = ADAMQueryBuilder.getORsForDACSectorCodes(params.getZLabels(), convtable.getTablename());
			 List<Object[]> sqlResult = adamDao.tableQuery(sql);


			// values
			for (String zLabel : params.getSortedZLabels()) {

				FAOSectorMatrixModel model = new FAOSectorMatrixModel();

				String zKey = params.getzCodes().get(zLabel);
				colSumCount++;

				// Sector

				model.setFAOSector(params.getZLabels().get(zKey));
				model.setFAOSectorCode(zKey);

				 if(sqlResult.size() > 0) {
					 StringBuilder sb = new StringBuilder();
					 StringBuilder sb2 = new StringBuilder();

					 for(int j=0; j < sqlResult.size(); j++) {
						 String sectorCode = (String)sqlResult.get(j)[0];
						 if(sectorCode.equals(zKey)) {

							 sb2.append(sqlResult.get(j)[1] + " ");

							// if(c < c - 1)
							//	 sb2.append(", ");

							 sb.append("&nbsp;<strong>"+sqlResult.get(j)[1]+"</strong>:&nbsp;  "+ sqlResult.get(j)[2] + "<br/><br/>");


						 }

					 }

					 model.setMappedORs(sb2.toString());
					 model.setMappedORsDescription(sb.toString());
				 }

				// Commented out W for Now - Must Re-instate -- W labels and values
				int c = 0;

				//for (String wLabel : params.getSortedWLabels()) {

					//String wKey = params.getwCodes().get(wLabel);
					List<Double> disbursementSum = new ArrayList<Double>();

					List<Double> commitmentSum = new ArrayList<Double>();

				if(sortDonorLabels) {
					Iterator<String> iterator =params.getSortedXLabels().iterator();

				   for (int i = 0; i < params.getSortedXLabels().size(); i++) {
						String  xLabel = iterator.next();

						String xKey = params.getxCodes().get(xLabel);
						String yODACommitment = params.getyCodes().get("Total Commitment");

						StringBuilder key = generateKey(xKey, zKey, yODACommitment);
						//LOGGER.info(key);
						OLAPBean b = olapBeansMap.get(key.toString());

					   // LOGGER.info("b.getStringValue() = "+ b.getStringValue());

						String property = "commitmentOda"+i;
						if (b!=null && b.getStringValue() != null && b.getStringValue() != "") {
							model.set("commitmentOda"+i, Double.valueOf(b.getStringValue()));
							commitmentSum.add(Double.valueOf(b.getStringValue()));
						} else {
							model.set("commitmentOda"+i, null);
						}

						model.set("commitmentOda"+i+"xKey", xKey);

						String yDisbursementODA = params.getyCodes().get("Total Disbursement");

						 key = generateKey(xKey, zKey, yDisbursementODA);
						 b = olapBeansMap.get(key.toString());
						// LOGGER.info(key);
						//LOGGER.info("b.getStringValue() = "+ b.getStringValue());
							if (b!=null && b.getStringValue() != null && b.getStringValue() != "") {
								model.set("disbursementOda"+i, Double.valueOf(b.getStringValue()));
								disbursementSum.add(Double.valueOf(b.getStringValue()));
							} else {
								model.set("disbursementOda"+i, null);
							}

							model.set("disbursementOda"+i+"xKey", xKey);

						/*String yPercentDisbursement = params.getyCodes().get("% Disbursement");

							key = generateKey(xKey, zKey, yPercentDisbursement);
							b = olapBeansMap.get(key.toString());
							//LOGGER.info(key);
							//LOGGER.info("b.getStringValue() = "+ b.getStringValue());
							if (b!=null && b.getStringValue() != null && b.getStringValue() != "") {
								model.set("percentDisbursement"+i, Double.valueOf(b.getStringValue()));
							} else {
								model.set("percentDisbursement"+i, null);
							}

							model.set("percentDisbursement"+i+"xKey", xKey);


							String yPercentCommitment = params.getyCodes().get("% Commitment");

							key = generateKey(xKey, zKey, yPercentCommitment);
							b = olapBeansMap.get(key.toString());
							//LOGGER.info(key);
							//LOGGER.info("b.getStringValue() = "+ b.getStringValue());
							if (b!=null && b.getStringValue() != null && b.getStringValue() != "") {
								model.set("percentCommitment"+i, Double.valueOf(b.getStringValue()));
							} else {
								model.set("percentCommitment"+i, null);
							}

							model.set("percentCommitment"+i+"xKey", xKey);
*/

						/* String ySector = params.getyCodes().get("label");

						 key = generateKey(xKey, zKey, ySector);
						 LOGGER.info(key);
						 b = olapBeansMap.get(key.toString());

						 if (b!=null && b.getStringValue() != null & b.getStringValue()!="") { // should be a string handling
								model.set("label"+i, b.getStringValue());
						} else {
								model.set("label"+i, null);
						}*/

					}
			} else {
				Iterator<Map.Entry<String, String>> iterator =params.getXLabels().entrySet().iterator();

				   for (int i = 0; i < params.getXLabels().size(); i++) {
						Map.Entry<String, String> entry = iterator.next();

						//final String xLabel = entry.getKey();
						final String xLabel = entry.getValue();

						//LOGGER.info("xLabel "+ xLabel);


					  // String  xLabel = iterator.next();

						String xKey = params.getxCodes().get(xLabel);

						//LOGGER.info("xKey "+ xKey);

						String yODACommitment = params.getyCodes().get("Total Commitment");
						StringBuilder key = generateKey(xKey, zKey, yODACommitment);
						//LOGGER.info("NO SORT: "+ key);
						OLAPBean b = olapBeansMap.get(key.toString());

					//	LOGGER.info("b.getStringValue() = "+ b.getStringValue());

						String property = "commitmentOda"+i;
						if (b!=null && b.getStringValue() != null && b.getStringValue() != "") {
							model.set("commitmentOda"+i, Double.valueOf(b.getStringValue()));
							commitmentSum.add(Double.valueOf(b.getStringValue()));
						} else {
							model.set("commitmentOda"+i, null);
						}

						model.set("commitmentOda"+i+"xKey", xKey);

						String yDisbursementODA = params.getyCodes().get("Total Disbursement");

						 key = generateKey(xKey, zKey, yDisbursementODA);
						 b = olapBeansMap.get(key.toString());

						 //LOGGER.info("b.getStringValue() = "+ b.getStringValue());
							if (b!=null && b.getStringValue() != null && b.getStringValue() != "") {
								model.set("disbursementOda"+i, Double.valueOf(b.getStringValue()));
								disbursementSum.add(Double.valueOf(b.getStringValue()));
							} else {
								model.set("disbursementOda"+i, null);
							}

							model.set("disbursementOda"+i+"xKey", xKey);

							/*	String yPercentDisbursement = params.getyCodes().get("% Disbursement");

							key = generateKey(xKey, zKey, yPercentDisbursement);
							b = olapBeansMap.get(key.toString());
							LOGGER.info(key);
							//LOGGER.info("b.getStringValue() = "+ b.getStringValue());
							if (b!=null && b.getStringValue() != null && b.getStringValue() != "") {
								model.set("percentDisbursement"+i, Double.valueOf(b.getStringValue()));
							} else {
								model.set("percentDisbursement"+i, null);
							}

							model.set("percentDisbursement"+i+"xKey", xKey);


							String yPercentCommitment = params.getyCodes().get("% Commitment");

							key = generateKey(xKey, zKey, yPercentCommitment);
							b = olapBeansMap.get(key.toString());
							LOGGER.info(key);
							//LOGGER.info("b.getStringValue() = "+ b.getStringValue());
							if (b!=null && b.getStringValue() != null && b.getStringValue() != "") {
								model.set("percentCommitment"+i, Double.valueOf(b.getStringValue()));
							} else {
								model.set("percentCommitment"+i, null);
							}

							model.set("percentCommitment"+i+"xKey", xKey);
						*/
						/*HIDE PRODUCTION! String yProductionPercent = params.getyCodes().get("% on Production");

						 key = generateKey(xKey, zKey, yProductionPercent);
						 b = olapBeansMap.get(key.toString());

						 //LOGGER.info("b.getStringValue() = "+ b.getStringValue());
							if (b!=null && b.getStringValue() != null) {
								model.set("totalOda"+i+"percent", Double.valueOf(b.getStringValue()));
							} else {
								model.set("totalOda"+i+"percent", null);
							}
						*/

						/* if (b.getQuantity() != null) {
								model.setProductionPercentage(b.getQuantity());
							} else {
								model.setProductionPercentage(null);
						 }*/


						/** String ySector = params.getyCodes().get("label");

						// key = OLAPHtml.generateKey(xKey, zKey, yWebPage, wKey);
						 key = generateKey(xKey, zKey, ySector);
						 LOGGER.info(key);
						 b = olapBeansMap.get(key.toString());

						 if (b!=null && b.getStringValue() != null & b.getStringValue()!="") { // should be a string handling
								model.set("label"+i, b.getStringValue());
						} else {
								model.set("label"+i, null);
						}**/

					}
			}
					c++;

					// add row summary
					if (showTotals) {
						// LOGGER.info(OLAPDaoUtils.sum(row));
						 //OLAPHtml.twoDigit.format(OLAPDaoUtils.sum(row));
						model.set("commitmentOdaSum", sum(commitmentSum));
						model.set("disbursementOdaSum", sum(disbursementSum));

					}
					if(sum(commitmentSum) != null && sum(commitmentSum) == 0.00 && sum(disbursementSum) !=null && sum(disbursementSum) == 0.00){
						LOGGER.info("sum(commitmentSum) == 0.00 && sum(disbursementSum) == 0.00 .. empty row");
					} else
						models.add(model);

				//}


			}

			// return result

			//LOGGER.info("END ............."+models.size());
			return models;
		}

	private static Map<String, OLAPBean> create3DOlapBeansMap(List<OLAPBean> beans, OLAPParameters params) {

		//LOGGER.info("HERE START");
		Map<String, OLAPBean> map = new HashMap<String, OLAPBean>();
		List<String> existingKeys = new ArrayList<String>();

		for (OLAPBean o : beans) {
			if (!params.getYLabels().isEmpty()) {
				boolean xExists = params.getXLabels().keySet().contains(o.getXValue());
				boolean zExists = params.getZLabels().keySet().contains(o.getZValue());
				boolean yExists = params.getYLabels().keySet().contains(o.getYValue());

				//LOGGER.info("HERE 2");

				if (xExists && zExists && yExists) {
					StringBuilder key = generateKey(o.getXValue(), o.getZValue(), o.getYValue());
					//LOGGER.info(key);
					if (!existingKeys.contains(key.toString())) {
						existingKeys.add(key.toString());
						map.put(key.toString(), o);
					}
				}
			}
			if (!params.getWLabels().isEmpty()) {
				StringBuilder key = generateKey(o.getXValue(), o.getZValue(), o.getWValue());
				if (!existingKeys.contains(key.toString())) {
					existingKeys.add(key.toString());
					map.put(key.toString(), o);
				}
			}
		}

		return map;
	}

	private static StringBuilder generateKey(String x, String z, String y) {

	//LOGGER.info("x:"+x+": z "+z+": y"+y);

		StringBuilder b = new StringBuilder();
		b.append("\"").append(x).append("\"").append(",");
		b.append("\"").append(z).append("\"").append(",");
		b.append("\"").append(y).append("\"");

	//	LOGGER.info(b);

		return b;
	}



private static OLAPParametersVo sortLabels(OLAPParametersVo p) {

		TreeSet<String> sortedXLabels = new TreeSet<String>(p.getXLabels().values());
		TreeSet<String> sortedZLabels = new TreeSet<String>(p.getZLabels().values());
		TreeSet<String> sortedYLabels = new TreeSet<String>();
		TreeSet<String> sortedWLabels = new TreeSet<String>();

		if (p.getX().contains("date") || p.getX().contains("Date")) {
			sortedXLabels = new TreeSet<String>(new FieldParserComparator());
			sortedXLabels.addAll(p.getXLabels().values());
		}
		if (p.getZ().contains("date") || p.getZ().contains("Date")) {
			sortedZLabels = new TreeSet<String>(new FieldParserComparator());
			sortedZLabels.addAll(p.getZLabels().values());
		}

		if (!p.getYLabels().isEmpty()) {
			sortedYLabels = new TreeSet<String>(p.getYLabels().values());
			if (p.getY().contains("date") || p.getY().contains("Date")) {
				sortedYLabels = new TreeSet<String>(new FieldParserComparator());
				sortedYLabels.addAll(p.getYLabels().values());
			}
		}

		if (!p.getWLabels().isEmpty()) {
			sortedWLabels = new TreeSet<String>(p.getWLabels().values());
			if (p.getW().contains("date") || p.getW().contains("Date")) {
				sortedWLabels = new TreeSet<String>(new FieldParserComparator());
				sortedWLabels.addAll(p.getWLabels().values());
			}
		}
		if(p.isSortXLabels())
			p.setSortedXLabels(sortedXLabels);
		p.setSortedZLabels(sortedZLabels);
		p.setSortedYLabels(sortedYLabels);
		p.setSortedWLabels(sortedWLabels);

		return p;
	}


private Map<String, DonorMatrixModel> addKeySOAndORs2(List<Object[]> recipientsResult, Map<String, DonorMatrixModel> allModelsMap,  String tableName) {
	  // String sql = "";

	    List<String> recipientCodes = new ArrayList<String>();



	  // for(DonorMatrixModel model: allModels) {

		   for(int i=0; i < recipientsResult.size(); i++) {
			   String recipientCode = (String)recipientsResult.get(i)[2];
			   DonorMatrixModel model = allModelsMap.get(recipientCode);

			   if(model!=null) {

				   recipientCodes.add(recipientCode);

				   if(recipientsResult.get(i)[0]!=null && recipientsResult.get(i)[1]!=null) {
					   Date to_date = (Date)recipientsResult.get(i)[0];
					   Date from_date = (Date)recipientsResult.get(i)[1];
					   SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
					   String fromDate = formatter.format(from_date);
					   String toDate = formatter.format(to_date);

					   model.setCPFReferenceDate(fromDate + "-" + toDate);

					   //recipientCodes.add(recipientCode);
				   } else {
					   model.setCPFReferenceDate("");
				   }

				  if(recipientsResult.get(i)[3]!=null){
					   model.setKeyComment(recipientsResult.get(i)[3].toString());
				   } else {
					   model.setKeyComment("");
					   model.setRecipientKeySOs("");
					   model.setRecipientKeySOsDescription("");
					   model.setRecipientKeyOrs("");
					   model.setRecipientKeyORsDescription("");

					 /*  sql = ADAMQueryBuilder.getRecipientKeySOsQuery(recipientCode, tableName, (Date)recipientsResult.get(i)[0], (Date)recipientsResult.get(i)[1]);

					   List<Object[]> keySosResult = adamDao.tableQuery(sql);

					   if(keySosResult.size() > 0) {
						   StringBuilder sb = new StringBuilder();
						   StringBuilder sb2 = new StringBuilder();
						   for(int j=0; j < keySosResult.size(); j++) {
							   sb2.append(keySosResult.get(j)[0]);

							   if(j < keySosResult.size() - 1)
								   sb2.append(", ");


							   sb.append("&nbsp;<strong>"+keySosResult.get(j)[0]+"</strong>:&nbsp;  "+ keySosResult.get(j)[1] + "<br/><br/>");
						   }

						   model.setRecipientKeySOs(sb2.toString());
						   model.setRecipientKeySOsDescription(sb.toString());
					   } else  {
						   model.setRecipientKeySOs("");
						   model.setRecipientKeySOsDescription("");
					   }

					   sql = ADAMQueryBuilder.getRecipientORsQuery(recipientCode, tableName, (Date)recipientsResult.get(i)[0], (Date)recipientsResult.get(i)[1]);

					   List<Object[]> orsResult = adamDao.tableQuery(sql);

					   if(orsResult.size() > 0) {
						   StringBuilder sb = new StringBuilder();
						   StringBuilder sb2 = new StringBuilder();
						   for(int j=0; j < orsResult.size(); j++) {
							   sb2.append(orsResult.get(j)[0]);

							   if(j < orsResult.size() - 1)
								   sb2.append(", ");

							   sb.append("&nbsp;<strong>"+orsResult.get(j)[0]+"</strong>:&nbsp;  "+ orsResult.get(j)[1] + "<br/><br/>");
						   }

						   model.setRecipientKeyOrs(sb2.toString());
						   model.setRecipientKeyORsDescription(sb.toString());
					   } else  {
						   model.setRecipientKeyOrs("");
						   model.setRecipientKeyORsDescription("");
					   }*/

				 }
			   }

		   }

	   //}
		     String sql = ADAMQueryBuilder.getRecipientKeySOsQuery(recipientCodes, tableName);
			 List<Object[]> keySosResult = adamDao.tableQuery(sql);

			 sql = ADAMQueryBuilder.getRecipientKeyORsQuery(recipientCodes, tableName);
             List<Object[]> orsResult = adamDao.tableQuery(sql);

			 //sb.append("SELECT DISTINCT key_so, recipientcode, c.label, MAX(to_year) as to_year, MAX(from_year) as from_year" +

			 for(String rCode: recipientCodes) {

				 DonorMatrixModel model = allModelsMap.get(rCode);

				 if(keySosResult.size() > 0) {
					 StringBuilder sb = new StringBuilder();
					 StringBuilder sb2 = new StringBuilder();

					 for(int j=0; j < keySosResult.size(); j++) {
						 String recipientCode = (String)keySosResult.get(j)[1];

						 if(recipientCode.equals(rCode)) {

							 // model = allModelsMap.get(recipientCode);
							 sb2.append(keySosResult.get(j)[0]);

							 if(j < keySosResult.size() - 1)
								 sb2.append(", ");

							 sb.append("&nbsp;<strong>"+keySosResult.get(j)[0]+"</strong>:&nbsp;  "+ keySosResult.get(j)[2] + "<br/><br/>");
						 }
					 }

					// LOGGER.info("model code "+model.getRecipientCode() +": KeySOs = "+sb2.toString() +" : KeySOsDescription = "+sb.toString());
					 model.setRecipientKeySOs(sb2.toString());
					 model.setRecipientKeySOsDescription(sb.toString());
				 }

				 if(orsResult.size() > 0) {
					 StringBuilder sb = new StringBuilder();
					 StringBuilder sb2 = new StringBuilder();

					 for(int j=0; j < orsResult.size(); j++) {
						 String recipientCode = (String)orsResult.get(j)[1];

						 if(recipientCode.equals(rCode)) {
							 sb2.append(orsResult.get(j)[0]);

							 if(j < orsResult.size() - 1)
								 sb2.append(", ");

							 sb.append("&nbsp;<strong>"+orsResult.get(j)[0]+"</strong>:&nbsp;  "+ orsResult.get(j)[2] + "<br/><br/>");
						 }
					 }

					// LOGGER.info("model code "+model.getRecipientCode() +": KeyORS = "+sb2.toString() +" : KeyORSDesc = "+sb.toString());
					  model.setRecipientKeyOrs(sb2.toString());
					  model.setRecipientKeyORsDescription(sb.toString());
				 }




			 }
			  /* sql = ADAMQueryBuilder.getRecipientKeyORsQuery(recipientCodes, tableName);
               List<Object[]> orsResult = adamDao.tableQuery(sql);

			   if(orsResult.size() > 0) {
				   StringBuilder sb = new StringBuilder();
				   StringBuilder sb2 = new StringBuilder();
				   DonorMatrixModel model = null;

				   for(int j=0; j < orsResult.size(); j++) {
					   String recipientCode = (String)orsResult.get(j)[1];
					   model = allModelsMap.get(recipientCode);

					   sb2.append(orsResult.get(j)[0]);

					   if(j < orsResult.size() - 1)
						   sb2.append(", ");

					   sb.append("&nbsp;<strong>"+orsResult.get(j)[0]+"</strong>:&nbsp;  "+ orsResult.get(j)[2] + "<br/><br/>");
				   }

				   if(model!=null) {
					   LOGGER.info(" KeyORs = "+sb2.toString() +" : KeyORsDescription = "+sb.toString());
					   model.setRecipientKeyOrs(sb2.toString());
					   model.setRecipientKeyORsDescription(sb.toString());
				   }


			   } */


	   return allModelsMap;
}

private DonorMatrixModel addKeySOAndORs(String recipientCode, DonorMatrixModel model) {

	   String sql = ADAMQueryBuilder.getKeySosAndOrsTableNameQuery();

	   List<String> list = new ArrayList<String>();
	   list.add("ADAM_DONORMATRIX_KEYSOSORS");


	   List<String> tableNameResult = customDatasetDao.findTablenamesFromCodes(list);

	   String tableName = null;

	   LOGGER.info("tableNameResult = "+tableNameResult + "tableNameResult.size() "+tableNameResult.size());

	   if(tableNameResult.size() > 0) {
		   for(int i=0; i < tableNameResult.size(); i++) {
			   tableName = tableNameResult.get(i).toString();
			   LOGGER.info("tableName = "+tableName);
		   }
	   }
	   else
		   LOGGER.info("tableName Result is 0 AND TableName set to NULL");

	   SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	   SimpleDateFormat df2=new SimpleDateFormat("yyyy");



	  // LOGGER.info("tableName = "+tableName);
	   if(tableName!=null){
		   sql = ADAMQueryBuilder.getRecipientKeySosAndOrsQuery(recipientCode, tableName);
		   List<Object[]> keySosAndOrsResult = adamDao.tableQuery(sql);

		   if(keySosAndOrsResult.size() > 0) {

			   LOGGER.info("keySosAndOrsResult "+keySosAndOrsResult);
			   for(int i=0; i < keySosAndOrsResult.size(); i++) {

				   if(keySosAndOrsResult.get(i)[0]!=null) {
					   try{
					   Date date = df1.parse(keySosAndOrsResult.get(i)[0].toString());
					   model.setCPFReferenceDate(df2.format(date));
					   }
					   catch(Exception ex){
					   }

				   }
				   if(keySosAndOrsResult.get(i)[1]!=null)
				   model.setRecipientKeySOs(keySosAndOrsResult.get(i)[1].toString());
				   if(keySosAndOrsResult.get(i)[2]!=null)
				   model.setRecipientKeyOrs(keySosAndOrsResult.get(i)[2].toString());

				   if(keySosAndOrsResult.get(i)[3]!=null)
				   model.setKeyComment(keySosAndOrsResult.get(i)[3].toString());
				   else if(keySosAndOrsResult.get(i)[1]!=null || keySosAndOrsResult.get(i)[2]!=null)
				   model.setKeyComment("");

				   if(model.getRecipientKeySOs()!=null) {
					   //get the descriptions
					   String keySOs = model.getRecipientKeySOs();
					   String[] codes = keySOs.split(";");
					   StringBuilder sb = new StringBuilder();
					   for (String code : codes)
					   {
						   LOGGER.info("code "+code);
						   sql = ADAMQueryBuilder.getKeySosAndOrsDescriptionQuery(code);

						   List<Object[]> descResult = adamDao.tableQuery(sql);

						   if(descResult.size() > 0) {
							   for(int j=0; j < descResult.size(); j++) {
								   LOGGER.info("descResult.get(j) "+descResult.get(j));
								   sb.append("&nbsp;<strong>"+code+"</strong>:&nbsp;  "+ descResult.get(j) + "<br/><br/>");
							   }
						   } else {
							   sb.append("KEYSOS: No label for" + "("+code+"); ");
						   }

					   }
					   model.setRecipientKeySOsDescription(sb.toString());
				   }

				   if(model.getRecipientKeyOrs()!=null) {
						   //get the descriptions
						   String keyOrs = model.getRecipientKeyOrs();
						   String[] codes = keyOrs.split(";");
						   StringBuilder sb = new StringBuilder();
						   for (String code : codes)
						   {
							   sql = ADAMQueryBuilder.getKeySosAndOrsDescriptionQuery(code);
							   List<Object[]> descResult = adamDao.tableQuery(sql);

							   if(descResult.size() > 0) {
								   for(int j=0; j < descResult.size(); j++) {
									   sb.append("&nbsp;<strong>"+code+"</strong>:&nbsp; "+ descResult.get(j) + "<br/><br/>");
								   }
							   }else {
								   sb.append("KEYORS: No label for" + "("+code+"); ");
							   }

						   }

						   model.setRecipientKeyORsDescription(sb.toString());
				   }

			   }

		   } else {

			   model.setKeyComment("No available data");
		   }

	   }

	   LOGGER.info("model = "+model.getRecipient());


	return model;
}

   private List<Object[]> getRecipientKeySO(Map<String, String> recipientsMap, String tableName){
       List<Object[]> results = null;

	 if(tableName!=null){
		  String sql = ADAMQueryBuilder.getRecipientsWithSOsAndOrsQuery(recipientsMap, tableName);
		   results = adamDao.tableQuery(sql);
	   }
		 return results;
   }

   private List<DonorMatrixModel> addKeySOAndORs(List<Object[]> recipientsResult, List<DonorMatrixModel> allModels,  String tableName) {
	   String sql = "";

	   for(DonorMatrixModel model: allModels) {

		   for(int i=0; i < recipientsResult.size(); i++) {
			   String recipientCode = (String)recipientsResult.get(i)[2];

			   if(model.getRecipientCode().equals(recipientCode)) {

				   if(recipientsResult.get(i)[0]!=null && recipientsResult.get(i)[1]!=null) {
					   Date to_date = (Date)recipientsResult.get(i)[0];
					   Date from_date = (Date)recipientsResult.get(i)[1];
					   SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
					   String fromDate = formatter.format(from_date);
					   String toDate = formatter.format(to_date);

					   model.setCPFReferenceDate(fromDate + "-" + toDate);
				   }

				   if(recipientsResult.get(i)[3]!=null){
					   model.setKeyComment(recipientsResult.get(i)[3].toString());
				   } else {
					   model.setKeyComment("");

					   sql = ADAMQueryBuilder.getRecipientKeySOsQuery(recipientCode, tableName, (Date)recipientsResult.get(i)[0], (Date)recipientsResult.get(i)[1]);

					   List<Object[]> keySosResult = adamDao.tableQuery(sql);

					   if(keySosResult.size() > 0) {
						   StringBuilder sb = new StringBuilder();
						   StringBuilder sb2 = new StringBuilder();
						   for(int j=0; j < keySosResult.size(); j++) {
							   sb2.append(keySosResult.get(j)[0]);

							   if(j < keySosResult.size() - 1)
								   sb2.append(", ");


							   sb.append("&nbsp;<strong>"+keySosResult.get(j)[0]+"</strong>:&nbsp;  "+ keySosResult.get(j)[1] + "<br/><br/>");
						   }

						   model.setRecipientKeySOs(sb2.toString());
						   model.setRecipientKeySOsDescription(sb.toString());
					   } else  {
						   model.setRecipientKeySOs("");
						   model.setRecipientKeySOsDescription("");
					   }

					   sql = ADAMQueryBuilder.getRecipientORsQuery(recipientCode, tableName, (Date)recipientsResult.get(i)[0], (Date)recipientsResult.get(i)[1]);

					   List<Object[]> orsResult = adamDao.tableQuery(sql);

					   if(orsResult.size() > 0) {
						   StringBuilder sb = new StringBuilder();
						   StringBuilder sb2 = new StringBuilder();
						   for(int j=0; j < orsResult.size(); j++) {
							   sb2.append(orsResult.get(j)[0]);

							   if(j < orsResult.size() - 1)
								   sb2.append(", ");

							   sb.append("&nbsp;<strong>"+orsResult.get(j)[0]+"</strong>:&nbsp;  "+ orsResult.get(j)[1] + "<br/><br/>");
						   }

						   model.setRecipientKeyOrs(sb2.toString());
						   model.setRecipientKeyORsDescription(sb.toString());
					   } else  {
						   model.setRecipientKeyOrs("");
						   model.setRecipientKeyORsDescription("");
					   }

				   }
			   }

		   }

	   }



	   return allModels;
   }


	public static Double sum(List<Double> values) {
		Double sum = null;
		for (Double v : values) {
			if (sum == null)
				sum = v.doubleValue();
			else
				sum = sum.doubleValue() + v.doubleValue();
		}
		return sum;
	}


	public String exportExcelTable(String title, List<String> headers, List<List<String>> table, Boolean isAgroupedTable) {
		/** TODO: title with the slash create problems **/
		return tableExcel.createExcel(title,headers, table, isAgroupedTable);
	}



	public void cleanLayersAndViews(HashMap<String, String> datasetProjectionViews) {
		for(String datasetView : datasetProjectionViews.keySet()) {
			// dropping the projection view
			adamDao.dropView(datasetProjectionViews.get(datasetView));
			// dropping the datasetView view
			adamDao.dropView(datasetView);


			/** TODO: delete the layer and the style from geoserver **/
		}
	}

	public LinkedHashMap<CodeVo, LinkedHashMap<CodeVo, List<CodeVo>>> getCountriesList(String codingSystemTitle) {
		LOGGER.info("START");
		LinkedHashMap<CodeVo, LinkedHashMap<CodeVo, List<CodeVo>>> result = new LinkedHashMap<CodeVo, LinkedHashMap<CodeVo,List<CodeVo>>>();



		CodingSystem codingSystem = codecDao.getCodingSystem(codingSystemTitle, "0");

		if ( codingSystem == null )
			LOGGER.info("coding system is null is null");

		List<Code> top = adamDao.getHierarchyTopLevel(codingSystem.getResourceId(), codingSystem.getTitle(), "EN");
		LOGGER.info("top: " + top);
		List<CodeVo> topcodesvo = new ArrayList<CodeVo>();
		for (Code topcode : top) {
			topcodesvo.add(code2vo(topcode));
		}


		for(CodeVo topcodevo : topcodesvo) {
			List<Code> sub = adamDao.findSubLevel(codingSystem.getResourceId(), codingSystem.getTitle(), topcodevo.getCode(), "EN");
			LinkedHashMap<CodeVo, List<CodeVo>> subCodes = new LinkedHashMap<CodeVo, List<CodeVo>>();

			LOGGER.info("subcode: " + topcodevo.getLabel() + " | " + sub);
			List<CodeVo> subcodesvo = new ArrayList<CodeVo>();

			for (Code subcode : sub) {
				subcodesvo.add(code2vo(subcode));
			}


			if ( !sub.isEmpty() ) {
				for(CodeVo subcodevo : subcodesvo) {
					List<Code> subsub = adamDao.findSubLevel(codingSystem.getResourceId(), codingSystem.getTitle(), subcodevo.getCode(), "EN");
					LOGGER.info("subsubcode: " + subcodevo.getLabel() + " | " + subsub);
					List<CodeVo> subsubcodesvo = new ArrayList<CodeVo>();
					for (Code subsubcode : subsub) {
						subsubcodesvo.add(code2vo(subsubcode));
					}
					subCodes.put(subcodevo, subsubcodesvo);
				}
			}

			result.put(topcodevo, subCodes);


		}
		LOGGER.info("END");


//		LOGGER.info("PRINT");
//		for(CodeVo topcodeVo : result.keySet()) {
//			System.out.println("- " + topcodeVo.getLabel());
//			for(CodeVo subCodeVo : result.get(topcodeVo).keySet()) {
//				System.out.println("-- " + subCodeVo.getLabel());
//				if ( result.get(topcodeVo).containsKey(subCodeVo)) {
//					for(CodeVo subSubCodeVo : result.get(topcodeVo).get(subCodeVo)) {
//						System.out.println("--- " + subSubCodeVo.getLabel());
//					}
//				}
//			}
//		}

		return result;
	}


	public ADAMDonorProfileVO getDonorProfileVO(String donorcode, String donorlabel, String selectedDataset){
		 ADAMDonorProfileVO vo = new ADAMDonorProfileVO();
		vo.setResourcePartnerName(donorlabel);

		String sql = "";

		CustomDataset dataset = customDatasetDao.find("ADAM_RESOURCEPARTNER_PROFILES");
		vo.setProfileDBTableName(dataset.getTablename());

		sql = ADAMQueryBuilder.getDonorProfile(donorcode, dataset.getTablename(), selectedDataset);
		List<Object[]> sqlResult = adamDao.tableQuery(sql);

		if(sqlResult.size() > 0){
			 List<String> urls = new ArrayList<String>();
			
			for(int j=0; j < sqlResult.size(); j++) {

				if(sqlResult.get(j)[0]!=null) {

					Date date = (Date)sqlResult.get(j)[0];
					SimpleDateFormat formatter = new SimpleDateFormat("MMM-yyyy");
					String formattedDate = formatter.format(date);
					vo.setProfileReferenceDate(formattedDate);
				}
				else
					vo.setProfileReferenceDate("");

				if(sqlResult.get(j)[1]!=null)
					vo.setPriorityThemes(sqlResult.get(j)[1].toString());
				else
					vo.setPriorityThemes("");

				if(sqlResult.get(j)[2]!=null)
					vo.setFavouredFundingArrangements(sqlResult.get(j)[2].toString());
				else
					vo.setFavouredFundingArrangements("");

				if(sqlResult.get(j)[3]!=null)
					vo.setFundingBodies(sqlResult.get(j)[3].toString());
				else
					vo.setFundingBodies("");

				if(sqlResult.get(j)[4]!=null) {
				       String[] links = sqlResult.get(j)[4].toString().split(";");
					   for (String link : links)
					   {
						   urls.add(link);
					   }
					   vo.setExternalLinks(urls);
				}
				else
					vo.setExternalLinks(null);

				if(sqlResult.get(j)[5]!=null)
					vo.setResponsibleOfficer(sqlResult.get(j)[5].toString());
				else
					vo.setResponsibleOfficer("");

				if(sqlResult.get(j)[6]!=null)
					vo.setResourceMobilizationOfficerEmail(sqlResult.get(j)[6].toString());
				else
					vo.setResourceMobilizationOfficerEmail("");

				if(sqlResult.get(j)[7]!=null)
					vo.setPriorityGeographicalAreas(sqlResult.get(j)[7].toString());
				else
					vo.setPriorityGeographicalAreas("");

				if(sqlResult.get(j)[8]!=null)
					vo.setChannelsOfCooperation(sqlResult.get(j)[8].toString());
				else
					vo.setChannelsOfCooperation("");
				
				if(sqlResult.get(j)[9]!=null)
					vo.setAnnualFundingCycle(sqlResult.get(j)[9].toString());
				else
					vo.setAnnualFundingCycle("");
				
				if(sqlResult.get(j)[10]!=null)
					vo.setApplicationAndNegotiationProcess(sqlResult.get(j)[10].toString());
				else
					vo.setApplicationAndNegotiationProcess("");
				
				if(sqlResult.get(j)[11]!=null)
					vo.setBudgetRevisionPolicies(sqlResult.get(j)[11].toString());
				else
					vo.setBudgetRevisionPolicies("");
				
				if(sqlResult.get(j)[12]!=null)
					vo.setAccruedInterestPolicies(sqlResult.get(j)[12].toString());
				else
					vo.setAccruedInterestPolicies("");
				
				if(sqlResult.get(j)[13]!=null)
					vo.setSpecialCharacteristics(sqlResult.get(j)[13].toString());
				else
					vo.setSpecialCharacteristics("");
				
			}
		}

		
		return vo;

	}

	/**public ADAMDonorProfileVO getDonorProfileVO_ORIGINAL(String donorcode, String donorlabel){
		 ADAMDonorProfileVO vo = new ADAMDonorProfileVO();
		vo.setResourcePartnerName(donorlabel);

		String sql = "";

		CustomDataset dataset = customDatasetDao.find("ADAM_DONOR_PROFILE");
		vo.setProfileDBTableName(dataset.getTablename());

		sql = ADAMQueryBuilder.getDonorProfile(donorcode, dataset.getTablename());
		List<Object[]> sqlResult = adamDao.tableQuery(sql);

		if(sqlResult.size() > 0){
			 List<String> urls = new ArrayList<String>();
			 Map<String, String> fundingArrangements = new HashMap<String, String>();

			for(int j=0; j < sqlResult.size(); j++) {

				if(sqlResult.get(j)[0]!=null) {

					Date date = (Date)sqlResult.get(j)[0];
					SimpleDateFormat formatter = new SimpleDateFormat("MMM-yyyy");
					String formattedDate = formatter.format(date);
					vo.setProfileReferenceDate(formattedDate);
				}
				else
					vo.setProfileReferenceDate("");

				if(sqlResult.get(j)[1]!=null)
					vo.setPriorityThemes(sqlResult.get(j)[1].toString());
				else
					vo.setResponsibleOfficer("");

				if(sqlResult.get(j)[2]!=null) {
					  String[] fundings = sqlResult.get(j)[2].toString().split(";");
					   StringBuilder sb = new StringBuilder();
					   for (String code : fundings)
					   {
						   fundingArrangements.put(code, code);
					   }
					vo.setFavouredFundingArrangements(fundingArrangements);
				}
				else
					vo.setFavouredFundingArrangements(null);

				if(sqlResult.get(j)[3]!=null)
					vo.setFundingBodies(sqlResult.get(j)[3].toString());
				else
					vo.setFundingBodies("");

				if(sqlResult.get(j)[4]!=null) {
				       String[] links = sqlResult.get(j)[4].toString().split(";");
					   StringBuilder sb = new StringBuilder();
					   for (String link : links)
					   {
						   urls.add(link);
					   }
					   vo.setExternalLinks(urls);
				}
				else
					vo.setExternalLinks(null);

				if(sqlResult.get(j)[5]!=null)
				vo.setResponsibleOfficer(sqlResult.get(j)[5].toString());
				else
					vo.setResponsibleOfficer("");

				if(sqlResult.get(j)[6]!=null)
					vo.setResponsibleOfficerEmail(sqlResult.get(j)[6].toString());
				else
					vo.setResponsibleOfficerEmail("");

				if(sqlResult.get(j)[7]!=null)
					vo.setPriorityGeographicalAreas(sqlResult.get(j)[7].toString());
				else
					vo.setPriorityGeographicalAreas("");

			}
		}

		dataset = customDatasetDao.find("ADAM_DONOR_PROCESSES");
		vo.setProcessesDBTableName(dataset.getTablename());

		sql = ADAMQueryBuilder.getDonorProcesses(donorcode, dataset.getTablename());
		sqlResult = adamDao.tableQuery(sql);

		if(sqlResult.size() > 0){
			 Map<String, ADAMDonorChannelOfCooperationVO> channelsOfCooperation = new HashMap<String, ADAMDonorChannelOfCooperationVO>();

			for(int j=0; j < sqlResult.size(); j++) {
				ADAMDonorChannelOfCooperationVO channelVO = new ADAMDonorChannelOfCooperationVO();

				if(sqlResult.get(j)[0]!=null)
					channelVO.setReferenceDate(sqlResult.get(j)[0].toString());
				else
					channelVO.setReferenceDate("");

				if(sqlResult.get(j)[1]!=null)
					channelVO.setChannelOfCooperation(sqlResult.get(j)[1].toString());
				else
					channelVO.setChannelOfCooperation("");

				if(sqlResult.get(j)[2]!=null) {
					channelVO.setApplicationAndNegotiationProcess(sqlResult.get(j)[2].toString());
				}
				else
					channelVO.setApplicationAndNegotiationProcess("");

				if(sqlResult.get(j)[3]!=null)
					channelVO.setAnnualFundingCycle(sqlResult.get(j)[3].toString());
				else
					channelVO.setAnnualFundingCycle("");

				if(sqlResult.get(j)[4]!=null)
					channelVO.setSpecialCharacteristics(sqlResult.get(j)[4].toString());
				else
					channelVO.setSpecialCharacteristics("");

				if(sqlResult.get(j)[5]!=null)
					channelVO.setBudgetRevisionPolicies(sqlResult.get(j)[5].toString());
				else
					channelVO.setBudgetRevisionPolicies("");

				if(sqlResult.get(j)[6]!=null)
					channelVO.setAccruedInterestPolicies(sqlResult.get(j)[6].toString());
				else
					channelVO.setAccruedInterestPolicies("");


				channelsOfCooperation.put(Integer.toString(j), channelVO);
			}

			vo.setChannelsOfCooperation(channelsOfCooperation);
		}
		else
			vo.setChannelsOfCooperation(null);


		dataset = customDatasetDao.find("ADAM_DONOR_PROFILE_DELIVERIES");
		vo.setDeliveriesDBTableName(dataset.getTablename());

		sql = ADAMQueryBuilder.getDonorDeliveries(donorcode, dataset.getTablename());
		sqlResult = adamDao.tableQuery(sql);


		if(sqlResult.size() > 0){
			 Map<String, String> externalFundingForDeliveryPeriods = new HashMap<String, String>();

			for(int j=0; j < sqlResult.size(); j++) {

				if(sqlResult.get(j)[0]!=null && sqlResult.get(j)[1]!=null) {
					externalFundingForDeliveryPeriods.put(sqlResult.get(j)[0].toString(), sqlResult.get(j)[1].toString());
					vo.setExternalFundingForDeliveryPeriods(externalFundingForDeliveryPeriods);
				}
			  }
		}
		else
			vo.setExternalFundingForDeliveryPeriods(null);

		dataset = customDatasetDao.find("ADAM_DONOR_PROFILE_FAOREGION_RECIPIENTS");
		vo.setRegionalCountriesDBTableName(dataset.getTablename());

		sql = ADAMQueryBuilder.getDonorRegionalCountries(donorcode, dataset.getTablename());
		sqlResult = adamDao.tableQuery(sql);

		if(sqlResult.size() > 0){
			 Map<String, String> recipientsByRegion = new HashMap<String, String>();

			for(int j=0; j < sqlResult.size(); j++) {
				if(sqlResult.get(j)[0]!=null && sqlResult.get(j)[1]!=null ){

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

					Date from_date = (Date)sqlResult.get(j)[0];
					String formattedFromDate = formatter.format(from_date);

					Date to_date = (Date)sqlResult.get(j)[1];
					String formattedToDate = formatter.format(to_date);

					vo.setRegionalCountriesReferenceDate(formattedFromDate +" - "+formattedToDate);
				}
				if(sqlResult.get(j)[2]!=null && sqlResult.get(j)[3]!=null) {
					recipientsByRegion.put(sqlResult.get(j)[2].toString(), sqlResult.get(j)[3].toString());
					vo.setRegionalRecipientCountries(recipientsByRegion);
				}
			  }
		}
		else
			vo.setRegionalRecipientCountries(null);


		dataset = customDatasetDao.find("DONOR_KEYSOS");
		vo.setKeySOsDBTableName(dataset.getTablename());


		sql = ADAMQueryBuilder.getDonorKeySOs(donorcode, dataset.getTablename());
		sqlResult = adamDao.tableQuery(sql);

		if(sqlResult.size() > 0){

			 Map<String, String> keySOs = new LinkedHashMap<String, String>();

			for(int j=0; j < sqlResult.size(); j++) {

			 if(sqlResult.get(j)[1]!=null && sqlResult.get(j)[2]!=null ){

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

					Date from_date = (Date)sqlResult.get(j)[1];
					String formattedFromDate = formatter.format(from_date);

					Date to_date = (Date)sqlResult.get(j)[2];
					String formattedToDate = formatter.format(to_date);

					vo.setKeySOsReferenceDate(formattedFromDate +" - "+formattedToDate);
				}

				if(sqlResult.get(j)[0]!=null && sqlResult.get(j)[3]!=null) {
					keySOs.put(sqlResult.get(j)[0].toString(), sqlResult.get(j)[3].toString());
					vo.setKeySOs(keySOs);
				}	else if (sqlResult.get(j)[0]!=null && sqlResult.get(j)[3]==null){
					keySOs.put(sqlResult.get(j)[0].toString(), sqlResult.get(j)[0].toString());
					vo.setKeySOs(keySOs);
				}
			  }
		}
		else
			vo.setKeySOs(null);


		return vo;

	}**/

	private ADAMResultVO getVennMatrix(ADAMQueryVO qvo, ADAMResultVO rvo) {

		LOGGER.info("getVennMatrix");


		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getResourceType());
		switch (c) {
		    case VENN_RECIPIENT_CHANNEL_MATRIX: getVennRecipientChannelMatrix(qvo, rvo); break;
			case VENN_RECIPIENT_MATRIX: getVennRecipientMatrix(qvo, rvo); break;
			case VENN_CHANNEL_MATRIX: getVennChannelMatrix(qvo, rvo); break;
			case VENN_DONOR_MATRIX: getVennDonorMatrix(qvo, rvo); break;
		}



		return rvo;
	}

	
	private ADAMResultVO getVennRecipientChannelMatrix(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("getVennRecipientChannelMatrix");

		CustomDataset dac_or_conversion = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");

		ADAMVennMatrixVO vennMatrix = new ADAMVennMatrixVO();

		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = new HashMap<String, ADAMVennRecipientsVO>();

		//Store distinct list of all ORs (Map<or, description>)
		LinkedHashMap<String, String> ors = new LinkedHashMap<String, String>();

		//RECIPIENT PRIORITY MAPS		
		//1. Store national priorities map (Map<or, List<stated priorities>>)
		LinkedHashMap<String, List<String>> national_priorities_map = new LinkedHashMap<String, List<String>>();
		//2. Store final recipient related national priorities (Map<recipientcode, Map<or, List<stated priorities>>)
		Map<String, Map<String, List<String>>> recipient_national_priorities = new HashMap<String, Map<String, List<String>>>();
		//3. Store source document reference (Map<recipientcode, source>)
		Map<String, String> national_priorities_sources = new HashMap<String, String>();

		//RECIPIENT PRIORITIES
		CustomDataset national_priorities = customDatasetDao.find("RECIPIENT_KEYSOS");

		String sqlAllNationalPriorities = "";

		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());
		CustomDataset convtable = customDatasetDao.find("ADAM_RECIPIENT_CONVERSION_TABLE");
		
		//convert the recipientcode list to CRS list
		
		
		switch(datasetSource){
		case ADAM_CRS:
			if(qvo.getCurrentView().equals(ADAMCurrentVIEW.ADAMVIEW))
				sqlAllNationalPriorities  = ADAMQueryBuilder.getDACPriorities(national_priorities.getTablename(), dac_or_conversion.getTablename(), qvo.getWheres(), qvo.getRecipients());		

			else if(qvo.getCurrentView().equals(ADAMCurrentVIEW.FAOVIEW)) 
				sqlAllNationalPriorities  = ADAMQueryBuilder.getPriorities(national_priorities.getTablename(), qvo.getWheres(), qvo.getRecipients(), datasetSource.name().toLowerCase(), convtable.getTablename(), "ADAM");
			break;
		case ADAM_FPMIS:
			String convertSql = ADAMQueryBuilder.convertToDAC(qvo.getRecipients(), datasetSource.name().toLowerCase(), convtable.getTablename());
			List<Object[]> convertedRecipients = adamDao.tableQuery(convertSql);
			Map<String, String> map = new LinkedHashMap<String, String>();	

			for(int i=0; i < convertedRecipients.size(); i++) {
				String recipient_code = null;
				if (convertedRecipients.get(i)[0] != null ) {
					recipient_code =  convertedRecipients.get(i)[0].toString();
				}
				String recipient_label = null;
				if (convertedRecipients.get(i)[0] != null ) {
					recipient_label =  convertedRecipients.get(i)[1].toString();

					map.put(recipient_code, recipient_label);
				}
			}

			if(!map.isEmpty()) {
				qvo.setRecipients(map);
				rvo.setConvertedRecipients(map);
			} else
				rvo.setConvertedRecipients(null);
			
			sqlAllNationalPriorities  = ADAMQueryBuilder.getPriorities(national_priorities.getTablename(), qvo.getWheres(), qvo.getRecipients(), datasetSource.name().toLowerCase(), convtable.getTablename(), "FPMIS");
			break;
		}
		List<Object[]> sqlAllNationalPrioritiesResult = adamDao.tableQuery(sqlAllNationalPriorities);

		setPriorityMaps(sqlAllNationalPrioritiesResult, ors, national_priorities_map, recipient_national_priorities, national_priorities_sources, recipientsVOHM, true);

		LOGGER.info("ORS Size ... BEFORE CHANNELS "+ ors.size());

		//FAO PRIORITY MAPS		
		//1. Store agreed priorities map (Map<or, List<stated priorities>>)
		LinkedHashMap<String, List<String>> agreed_priorities_map = new LinkedHashMap<String, List<String>>();
		//2. Store final recipient related agreed priorities (Map<recipientcode, Map<or, List<stated priorities>>)
		Map<String, Map<String, List<String>>> recipient_agreed_priorities = new HashMap<String, Map<String, List<String>>>();
		//3. Store source document reference (Map<recipientcode, source>)
		Map<String, String> agreed_priorities_sources = new HashMap<String, String>();

		//AGREED PRIORITIES
		//Agreed priorities with FAO (Channel = 41301, is the only channel in the dataset)
		CustomDataset fao_priorities = customDatasetDao.find("CHANNEL_KEYSOS");
		String sqlAllAgreedPriorities = "";

		switch(datasetSource){
		case ADAM_CRS:
			if(qvo.getCurrentView().equals(ADAMCurrentVIEW.ADAMVIEW))
				sqlAllAgreedPriorities  = ADAMQueryBuilder.getDACPriorities(fao_priorities.getTablename(), dac_or_conversion.getTablename(), qvo.getWheres(), qvo.getRecipients());

			else if(qvo.getCurrentView().equals(ADAMCurrentVIEW.FAOVIEW))
				sqlAllAgreedPriorities  = ADAMQueryBuilder.getPriorities(fao_priorities.getTablename(), qvo.getWheres(), qvo.getRecipients(), datasetSource.name().toLowerCase(), convtable.getTablename(), "ADAM");
			break;
		case ADAM_FPMIS:
			sqlAllAgreedPriorities  = ADAMQueryBuilder.getPriorities(fao_priorities.getTablename(), qvo.getWheres(), qvo.getRecipients(), datasetSource.name().toLowerCase(), convtable.getTablename(), "FPMIS");
			break;
		}

		List<Object[]> sqlAllAgreedPrioritiesResult = adamDao.tableQuery(sqlAllAgreedPriorities);

		setPriorityMaps(sqlAllAgreedPrioritiesResult, ors, agreed_priorities_map, recipient_agreed_priorities, agreed_priorities_sources, recipientsVOHM, false);

		//set Ors
		LOGGER.info("FINAL ORS Size"+ ors.size());


		for(String selectedRecipient: qvo.getRecipients().keySet()){

			if(recipient_national_priorities.containsKey(selectedRecipient)){

				//for(String code: recipient_national_priorities.keySet()){
				ADAMVennRecipientsVO recipientVO = new ADAMVennRecipientsVO();
				Map<String, List<String>> or_priorities = recipient_national_priorities.get(selectedRecipient);

				if (recipientsVOHM.containsKey(selectedRecipient) ) {
					// country already in the hashmap
					recipientVO = recipientsVOHM.get(selectedRecipient);
				}

				//set Source
				if(!national_priorities_sources.isEmpty() && national_priorities_sources.containsKey(selectedRecipient)) {
					recipientVO.setSourceDocument(national_priorities_sources.get(selectedRecipient));
				}

				//set Priorities
				for(String or: or_priorities.keySet()){
					recipientVO.getOrs().put(or, or);
					List<String> priorities = or_priorities.get(or);

					ADAMVennRecipientsStatedPrioritiesVO stated_priorities = new ADAMVennRecipientsStatedPrioritiesVO();

					if (recipientVO.getStatedPrioritiesVO().containsKey(or)) {
						stated_priorities = recipientVO.getStatedPrioritiesVO().get(or.toString());
					}

					stated_priorities.getStatedPriorities().addAll(priorities);

					recipientVO.getStatedPrioritiesVO().put(or, stated_priorities);	
				}



				recipientsVOHM.put(selectedRecipient, recipientVO);
				//}
			}

			else { //No National Priorities
				ADAMVennRecipientsVO recipientVO = new ADAMVennRecipientsVO();
				recipientVO.setCode(selectedRecipient);
				recipientVO.setLabel(qvo.getRecipients().get(selectedRecipient));
				recipientVO.setOrs(null);
				recipientVO.setStatedPrioritiesVO(null);	
				recipientVO.setIsCalculated(false);

				recipientsVOHM.put(selectedRecipient, recipientVO);
			}
		} 


		for(String selectedRecipient: qvo.getRecipients().keySet()){
			if(recipient_agreed_priorities.containsKey(selectedRecipient)){

				//for(String code: recipient_agreed_priorities.keySet()){
				ADAMVennRecipientsVO recipientVO = new ADAMVennRecipientsVO();
				Map<String, List<String>> or_priorities = recipient_agreed_priorities.get(selectedRecipient);

				if (recipientsVOHM.containsKey(selectedRecipient) ) {
					// country already in the hashmap
					recipientVO = recipientsVOHM.get(selectedRecipient);
				}

				//set Source
				if(!agreed_priorities_sources.isEmpty() && agreed_priorities_sources.containsKey(selectedRecipient)) {
					recipientVO.setCpfSourceDocument(agreed_priorities_sources.get(selectedRecipient));
				}

				//set priorities
				for(String or: or_priorities.keySet()){
					recipientVO.getAgreedOrs().put(or, or);
					List<String> priorities = or_priorities.get(or);


					ADAMVennRecipientsStatedPrioritiesVO stated_priorities = new ADAMVennRecipientsStatedPrioritiesVO();

					if (recipientVO.getStatedPrioritiesVO().containsKey(or)) {
						stated_priorities = recipientVO.getStatedPrioritiesVO().get(or.toString());
					}

					stated_priorities.getAgreedPriorities().addAll(priorities);

					recipientVO.getStatedPrioritiesVO().put(or, stated_priorities);	

				}

				recipientsVOHM.put(selectedRecipient, recipientVO);
				//}
			} else { //No Agreed priorities
				if (recipientsVOHM.containsKey(selectedRecipient) ) {
					// country already in the hashmap
					ADAMVennRecipientsVO recipientVO = recipientsVOHM.get(selectedRecipient);
					recipientVO.setAgreedOrs(null);	

					if(recipientVO.getStatedPrioritiesVO()!=null && recipientVO.getStatedPrioritiesVO().isEmpty()){
						recipientVO.setStatedPrioritiesVO(null);	
					}
				} 
			}

		}


		for(String key: recipientsVOHM.keySet()) {
			LOGGER.info("--> " + key + " | " + recipientsVOHM.get(key).getLabel() + " | stated priorities: " + recipientsVOHM.get(key).getOrs() + " | " + recipientsVOHM.get(key).getStatedPrioritiesVO());
		}



		// setting venn matrix
		vennMatrix.setRecipientsVOHM(recipientsVOHM);

		if(ors.size() > 0) {
			LinkedHashMap<String, String> sortedOrs = sortByKey(ors);
			vennMatrix.setOrs(sortedOrs);
		} else
			vennMatrix.setOrs(null);

		rvo.setVennMatrix(vennMatrix);

		return rvo;
	}

	private ADAMResultVO getVennRecipientMatrix(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("getVennRecipientMatrix");

		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = new HashMap<String, ADAMVennRecipientsVO>();

		//Store distinct list of all ORs or DACS (Map<or, description>)
		LinkedHashMap<String, String> ors = new LinkedHashMap<String, String>();

		//RECIPIENT PRIORITY MAPS		
		//1. Store national priorities map (Map<or, List<stated priorities>>)
		LinkedHashMap<String, List<String>> national_priorities_map = new LinkedHashMap<String, List<String>>();
		//2. Store final recipient related national priorities (Map<recipientcode, Map<or, List<stated priorities>>)
		Map<String, Map<String, List<String>>> recipient_national_priorities = new HashMap<String, Map<String, List<String>>>();
		//3. Store source document reference (Map<recipientcode, source>)
		Map<String, String> national_priorities_sources = new HashMap<String, String>();

		//RECIPIENT PRIORITIES
		CustomDataset national_priorities = customDatasetDao.find("RECIPIENT_KEYSOS");
		getAndSetPriorities(qvo, rvo, national_priorities, ors, national_priorities_map, recipient_national_priorities, national_priorities_sources, recipientsVOHM, true);

		ADAMVennMatrixVO vennMatrix = createVennMatrixVO(qvo, recipient_national_priorities, national_priorities_sources, recipientsVOHM, ors);
		rvo.setVennMatrix(vennMatrix);

		return rvo;
	}	

	private ADAMResultVO getVennChannelMatrix(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("getVennChannelMatrix");

		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = new HashMap<String, ADAMVennRecipientsVO>();

		//Store distinct list of all ORs (Map<or, description>)
		LinkedHashMap<String, String> ors = new LinkedHashMap<String, String>();

		//FAO PRIORITY MAPS		
		//1. Store agreed priorities map (Map<or, List<stated priorities>>)
		LinkedHashMap<String, List<String>> agreed_priorities_map = new LinkedHashMap<String, List<String>>();
		//2. Store final recipient related agreed priorities (Map<recipientcode, Map<or, List<stated priorities>>)
		Map<String, Map<String, List<String>>> recipient_agreed_priorities = new HashMap<String, Map<String, List<String>>>();
		//3. Store source document reference (Map<recipientcode, source>)
		Map<String, String> agreed_priorities_sources = new HashMap<String, String>();

		//AGREED PRIORITIES
		//Agreed priorities with FAO (Channel = 41301, is the only channel in the dataset)
		CustomDataset agreed_priorities = customDatasetDao.find("CHANNEL_KEYSOS");
		getAndSetPriorities(qvo, rvo, agreed_priorities, ors, agreed_priorities_map, recipient_agreed_priorities, agreed_priorities_sources, recipientsVOHM, false);

		ADAMVennMatrixVO vennMatrix = createVennMatrixVO(qvo, recipient_agreed_priorities, agreed_priorities_sources, recipientsVOHM, ors);
		rvo.setVennMatrix(vennMatrix);


		return rvo;
	}
	
	private ADAMResultVO getVennDonorMatrix(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("getVennDonorMatrix");

		List<String> tablenames = customDatasetDao.findTablenamesFromCodes(qvo.getDatasetCodes());
		if( tablenames.isEmpty() ) {
			qvo.setTableNames(qvo.getDatasetCodes());
		}
		else
			qvo.setTableNames(tablenames);


		/** The donor venn is based on:
		 * 		1) OECD:
		 * 					works only on DAC (country level)
		 *
		 * 		2) AidData:
		 * 					works only on NONDAC (global)
		 *
		 * 		3) OECD_AidData:
		 * 					works on DAC (country level) and NONDAC (global level)
		 *
		 * **/

		LOGGER.info("Datasource switch: " + qvo.getDatasetSource());
		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());

		switch (datasetSource) {
		case ADAM_CRS:
			getVennDonorMatrixData(qvo, rvo, "ADAM", "DAC");
			break;
		case ADAM_FPMIS:
			getVennDonorMatrixData(qvo, rvo, "FPMIS", "FPMIS");
			break;
		case AID_DATA:
//			getVennDonorPrioritiesAIDData(qvo, rvo, priorities);
			break;

		case ADAM_CRS_AID_DATA:
//			getVennDonorPrioritiesOECD(qvo, rvo, priorities);
//			getVennDonorPrioritiesAIDData(qvo, rvo, priorities);
			break;

		}


		return rvo;
	}

	private void getVennDonorMatrixData(ADAMQueryVO qvo, ADAMResultVO rvo, String csType, String source) {

		ADAMVennMatrixVO vennMatrix = new ADAMVennMatrixVO();

		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = new HashMap<String, ADAMVennRecipientsVO>();

		// get all donors
		String sqlAllDonors = ADAMQueryBuilder.getAllVennDonors(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(), qvo.getRecipients(), source, "EN", csType);

		List<Object[]> sqlAllDonorsResult = adamDao.tableQuery(sqlAllDonors);

		LOGGER.info("(sqlAllDonors) sqlsize: " + sqlAllDonorsResult.size());

		LinkedHashMap<String, String> donors = new LinkedHashMap<String, String>();

		for(int i=0; i < sqlAllDonorsResult.size(); i++) {
			try {
				Object donorcode =  sqlAllDonorsResult.get(i)[0];

				Object donorname = sqlAllDonorsResult.get(i)[1];

				donors.put(donorcode.toString(), donorname.toString());
			}
			catch (Exception e) {
				LOGGER.info("error: " + e.toString());
			}
		}


		// get all the ORs/DACS country based
		// The assumpition is that the donor and recipients are always mapped at the OR level and not SO

		String sqlAllRecipientDonors = "";
		
		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());

		switch (datasetSource) {
		case ADAM_CRS:
			if(qvo.getCurrentView().equals(ADAMCurrentVIEW.ADAMVIEW)) {
				CustomDataset dac_or_conversion = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
				sqlAllRecipientDonors =	ADAMQueryBuilder.getAllVennRecipientDonorsDAC(dac_or_conversion.getTablename(), qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(), qvo.getRecipients(), source, "EN");	
			}		
			else if(qvo.getCurrentView().equals(ADAMCurrentVIEW.FAOVIEW)) 
				sqlAllRecipientDonors =	ADAMQueryBuilder.getAllVennRecipientDonors(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(), qvo.getRecipients(), source, "EN");
			
			break;
		case ADAM_FPMIS:
			sqlAllRecipientDonors =	ADAMQueryBuilder.getAllVennRecipientDonors(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(), qvo.getRecipients(), source, "EN");
			break;
		}	
		
		List<Object[]> sqlAllRecipientDonorsResult = adamDao.tableQuery(sqlAllRecipientDonors);

		LOGGER.info("(sqlAllRecipientDonorsResult) sqlsize: " + sqlAllRecipientDonorsResult.size());



		for(int i=0; i < sqlAllRecipientDonorsResult.size(); i++) {
			try {
				Object recipientcode =  sqlAllRecipientDonorsResult.get(i)[0];
				Object donorcode = sqlAllRecipientDonorsResult.get(i)[1];
				Object or = sqlAllRecipientDonorsResult.get(i)[2];
				Object or_desc = sqlAllRecipientDonorsResult.get(i)[3];


				ADAMVennRecipientsVO recipientVO = new ADAMVennRecipientsVO();
				if ( recipientsVOHM.containsKey(recipientcode.toString())) {
					recipientVO = recipientsVOHM.get(recipientcode.toString());
				}

				// setting ORs
				recipientVO.getOrs().put(or.toString(), or_desc.toString());

				// setting donor
				ADAMVennDonorsVO donorVO = new ADAMVennDonorsVO();
				if ( recipientVO.getDonorsVO().containsKey(donorcode.toString())) {
					donorVO = recipientVO.getDonorsVO().get(donorcode.toString());
				}
				donorVO.getOrs().put(or.toString(), or_desc.toString());
				recipientVO.getDonorsVO().put(donorcode.toString(), donorVO);


				recipientsVOHM.put(recipientcode.toString(), recipientVO);


			}
			catch (Exception e) {
				LOGGER.info("error: " + e.toString());
			}
		}

		LOGGER.info("sqlAllRecipientDonorsResult: " + donors);


		// setting venn matrix
		vennMatrix.setRecipientsVOHM(recipientsVOHM);
		vennMatrix.setDonors(donors);


		LOGGER.info("recipientsVOH size" + recipientsVOHM.size());
		LOGGER.info("recipientsVOH" + recipientsVOHM);
		for(String key: recipientsVOHM.keySet()) {
			LOGGER.info("--> " + key + " | " + recipientsVOHM.get(key).getLabel() + " | " + recipientsVOHM.get(key).getOrs());
			for ( String donorcode : recipientsVOHM.get(key).getDonorsVO().keySet()) {
				LOGGER.info("--------> " + donorcode + " | " + donors.get(donorcode) + " | "+ recipientsVOHM.get(key).getDonorsVO().get(donorcode).getOrs());
			}
		}

		LOGGER.info("DONORS: " + vennMatrix.getDonors());

		rvo.setVennMatrix(vennMatrix);
	}

	/*private void getVennDonorMatrixAIDData(ADAMQueryVO qvo, ADAMResultVO rvo) {

		*//** The queries are based the donors if selected (and not on the recipients), returing
		 * 	first the ORs then the SOs (distincted)
		 *  and at the end all the Ors and SOs to in order to have the full map with all the donorsc(code, label)
		 *  with their ORs and SOs.
		 * **//*

		ADAMVennMatrixVO vennMatrix = new ADAMVennMatrixVO();

		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = new HashMap<String, ADAMVennRecipientsVO>();


		// get all donors
		String sqlAllDonors = ADAMQueryBuilder.getAllVennDonors(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(), new HashMap<String, String>(), "NONDAC", "EN");

		List<Object[]> sqlAllDonorsResult = adamDao.tableQuery(sqlAllDonors);

		LOGGER.info("(sqlAllDonors) sqlsize: " + sqlAllDonorsResult.size());

		LinkedHashMap<String, String> donors = new LinkedHashMap<String, String>();

		for(int i=0; i < sqlAllDonorsResult.size(); i++) {
			try {
				Object donorcode =  sqlAllDonorsResult.get(i)[0];

				Object donorname = sqlAllDonorsResult.get(i)[1];

				donors.put(donorcode.toString(), donorname.toString());
			}
			catch (Exception e) {
				LOGGER.info("error: " + e.toString());
			}
		}



		// setting venn matrix
		vennMatrix.setRecipientsVOHM(recipientsVOHM);
		vennMatrix.setDonors(donors);


		LOGGER.info("recipientsVOH size" + recipientsVOHM.size());
		LOGGER.info("recipientsVOH" + recipientsVOHM);
		for(String key: recipientsVOHM.keySet()) {
			LOGGER.info("--> " + key + " | " + recipientsVOHM.get(key).getLabel() + " | " + recipientsVOHM.get(key).getOrs());
			for ( String donorcode : recipientsVOHM.get(key).getDonorsVO().keySet()) {
				LOGGER.info("--------> " + donorcode + " | " + donors.get(donorcode) + " | "+ recipientsVOHM.get(key).getDonorsVO().get(donorcode).getOrs());
			}
		}

		LOGGER.info("DONORS: " + vennMatrix.getDonors());

		rvo.setVennMatrix(vennMatrix);
	}
*/

	/*private ADAMResultVO getVennChannelMatrixOriginal(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("getVennChannelMatrix");

		ADAMVennMatrixVO vennMatrix = new ADAMVennMatrixVO();

		HashMap<String, ADAMVennRecipientsVO> recipientsVOHM = new HashMap<String, ADAMVennRecipientsVO>();

		LinkedHashMap<String, String> ors = new LinkedHashMap<String, String>();



		List<String> tablenames = customDatasetDao.findTablenamesFromCodes(qvo.getDatasetCodes());
		if( tablenames.isEmpty() ) {
			qvo.setTableNames(qvo.getDatasetCodes());
		}
		else
			qvo.setTableNames(tablenames);


		// Recipient Priorities i.e. National Document stated priorities
		List<String> recipientDatasetCode = new ArrayList<String>();
		recipientDatasetCode.add("RECIPIENT_KEYSOS");

		List<String> recipientstablenames = customDatasetDao.findTablenamesFromCodes(recipientDatasetCode);
		String recipientsTableName = null;
		if( !recipientstablenames.isEmpty()) {
			recipientsTableName = recipientstablenames.get(0);
		}

		Map<String, Map<String, String>> recipient_national_priorities = new HashMap<String, Map<String, String>>();
		Map<String, String> recipient_priorities = new HashMap<String, String>();
		Map<String, String> recipient_priorities_sources = new HashMap<String, String>();
		Map<String, Boolean> recipient_isCalculated = new HashMap<String, Boolean>();



		String sqlAllrecipientPriorities = ADAMQueryBuilder.getAllRecipientsPriorities(recipientsTableName, qvo.getWheres(), qvo.getRecipients());
		List<Object[]> sqlRecipientsResult = adamDao.tableQuery(sqlAllrecipientPriorities);

		LOGGER.info("1) sqlsize (National priorities): " + sqlRecipientsResult.size());

		for(int i=0; i < sqlRecipientsResult.size(); i++) {
			try {

				Object or =  sqlRecipientsResult.get(i)[1];
				Object recipient_code =  sqlRecipientsResult.get(i)[2];
				Object is_calculated = sqlRecipientsResult.get(i)[4];
				Object cpf_priority = null;
				Object cpf_source = null;


				if (sqlRecipientsResult.get(i)[5] != null )
					cpf_priority =  sqlRecipientsResult.get(i)[5];

				if (  sqlRecipientsResult.get(i)[6] != null ) {
					cpf_source =  sqlRecipientsResult.get(i)[6];
					recipient_priorities_sources.put(recipient_code.toString(), cpf_source.toString());
				}


				if(is_calculated != null){
					if(is_calculated.toString().equalsIgnoreCase("calculated")) {
						recipient_isCalculated.put(recipient_code.toString(), true);
						recipient_priorities.put(or.toString(), or.toString());
					}
				   else {
					   recipient_isCalculated.put(recipient_code.toString(), false);
					   recipient_priorities.put(or.toString(), cpf_priority.toString());
				   }

				} else {
					 recipient_isCalculated.put(recipient_code.toString(), false);
					 recipient_priorities.put(or.toString(), cpf_priority.toString());
				}

				recipient_national_priorities.put(recipient_code.toString(), recipient_priorities);
			}
			catch (Exception e) {
				LOGGER.info("getAllRecipientsPriorities error: " + e.toString());
			}
		}


		// Recipient Priorities end


		// get all countries with related ORs
		String sqlGetAllRecipients = ADAMQueryBuilder.getChannelsVennMatrixORPriorities(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getChannels(), qvo.getRecipients(), "EN");
		LOGGER.info("sql: " + sqlGetAllRecipients);


		List<Object[]> sqlResult = adamDao.tableQuery(sqlGetAllRecipients);

		LOGGER.info("sqlsize: " + sqlResult.size());


		for(int i=0; i < sqlResult.size(); i++) {
			try {
				Object recipientCode =  sqlResult.get(i)[0];

				Object or_code = sqlResult.get(i)[1];

				Object or_label = sqlResult.get(i)[2];

				Object stated_priority = null;
				if (  sqlResult.get(i)[3] != null )
					stated_priority =  sqlResult.get(i)[3];

				Object source = null;
				if (  sqlResult.get(i)[4] != null )
					source =  sqlResult.get(i)[4];




				// getting all ORs
				ors.put(or_code.toString(), or_label.toString());

				// saving per country

				ADAMVennRecipientsVO recipientVO = new ADAMVennRecipientsVO();

				if ( recipientsVOHM.containsKey(recipientCode)) {
					recipientVO = recipientsVOHM.get(recipientCode);

					if(!recipient_isCalculated.isEmpty() && recipient_isCalculated.containsKey(recipientCode))
						recipientVO.setIsCalculated(recipient_isCalculated.get(recipientCode));
					else
						recipientVO.setIsCalculated(false);
				}

				recipientVO.getOrs().put(or_code.toString(), or_label.toString());

				// get the stated priority
				if ( stated_priority != null ) {
					ADAMVennRecipientsStatedPrioritiesVO stated_priorities = new ADAMVennRecipientsStatedPrioritiesVO();


					if ( recipientVO.getStatedPrioritiesVO().containsKey(or_code.toString())) {
						stated_priorities = recipientVO.getStatedPrioritiesVO().get(or_code.toString());

					}
					stated_priorities.getStatedPriorities().add(stated_priority.toString());

					recipientVO.getStatedPrioritiesVO().put(or_code.toString(), stated_priorities);


					// Add National priorities
					if(recipient_national_priorities.containsKey(recipientCode.toString())){
						if(recipient_national_priorities.get(recipientCode.toString()).containsKey(or_code.toString())){
							recipientVO.getRecipientPriorities().put(or_code.toString(), recipient_national_priorities.get(recipientCode.toString()).get(or_code.toString()));
						}

					}

				}

				// getting source
				if ( source != null ) {
					recipientVO.setSourceDocument(source.toString());
				}

				if(!recipient_priorities_sources.isEmpty() && recipient_priorities_sources.containsKey(recipientCode)) {
					recipientVO.setNationalSourceDocument(recipient_priorities_sources.get(recipientCode));
				}

				recipientVO.setIsSF(false);

				// saving the recipienty
				recipientsVOHM.put(recipientCode.toString(), recipientVO);
			}
			catch (Exception e) {
				LOGGER.info("error: " + e.toString());
			}
		}


		// TODO: set all the countries with the SF if are not in recipientsVOHM

		for(String recipientCode : qvo.getRecipients().keySet()) {
			if ( !recipientsVOHM.containsKey(recipientCode) ) {
				ADAMVennRecipientsVO recipientVO = new ADAMVennRecipientsVO();
				recipientVO.setIsSF(true);
				
				recipientsVOHM.put(recipientCode, recipientVO);
			}
		}


		LOGGER.info("GET ALL RECIPIENTS PRIORIETIES END");


		LOGGER.info("recipientsVOH size" + recipientsVOHM.size());
		LOGGER.info("recipientsVOH" + recipientsVOHM);
		for(String key: recipientsVOHM.keySet()) {
			LOGGER.info("--> " + key + " | " + " | stated priorities: " + recipientsVOHM.get(key).getOrs() + " | " + recipientsVOHM.get(key).getStatedPrioritiesVO());
		}


		// setting venn matrix
		vennMatrix.setRecipientsVOHM(recipientsVOHM);
		vennMatrix.setOrs(ors);

		rvo.setVennMatrix(vennMatrix);
		return rvo;
	}*/

	private ADAMResultVO getPriorities(ADAMQueryVO qvo, ADAMResultVO rvo) {
		// variables

		List<String> tablenames = customDatasetDao.findTablenamesFromCodes(qvo.getDatasetCodes());
		if( tablenames.isEmpty() ) {
			qvo.setTableNames(qvo.getDatasetCodes());
		}
		else
			qvo.setTableNames(tablenames);

		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());
		CustomDataset convtable = customDatasetDao.find("ADAM_RECIPIENT_CONVERSION_TABLE");
		
		Map<String, String> priorities = new HashMap<String, String>();

		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getResourceType());
		switch (c) {
			case VENN_RECIPIENT_PRIORITIES:

				// TAKING ORs
				
				String sqlrecipients = ADAMQueryBuilder.getRecipientsORPriorities(qvo.getTableNames().get(0), convtable.getTablename(), qvo.getWheres(), qvo.getRecipients(), "EN", datasetSource.name().toLowerCase());
					
			    //String sqlrecipients = ADAMQueryBuilder.getRecipientsPriorities(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getRecipients(), "EN");


				LOGGER.info("sql1: " + sqlrecipients);

				List<Object[]> sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);



				LOGGER.info("2) sqlsize (recipient priorities): " + sqlRecipientsResult.size());

				for(int i=0; i < sqlRecipientsResult.size(); i++) {
					try {
						Object so =  sqlRecipientsResult.get(i)[0];

						Object so_label = sqlRecipientsResult.get(i)[1];

						priorities.put(so.toString(), so_label.toString());
					}
					catch (Exception e) {
						LOGGER.info("error: " + e.toString());
					}
				}

				// TAKING whole SOs
				sqlrecipients = ADAMQueryBuilder.getRecipientsWholeSO(qvo.getTableNames().get(0), convtable.getTablename(), qvo.getWheres(), qvo.getRecipients(), "EN", datasetSource.name().toLowerCase());

				LOGGER.info("sql2: " + sqlrecipients);

				sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);

				LOGGER.info("2) sqlsize: " + sqlRecipientsResult.size());

				if ( sqlRecipientsResult.size() > 0 ) {
					// contains complete SOs, the get all the ORs related
					List<String> sos = new ArrayList<String>();
					for(int i=0; i < sqlRecipientsResult.size(); i++) {

						try {
							Object so =  sqlRecipientsResult.get(i)[0];
							sos.add(so.toString());
						}
						catch (Exception e) {
							LOGGER.info("error: " + e.toString());
						}
					}

					sqlrecipients = ADAMQueryBuilder.getALLORsFromSOs(sos);

					LOGGER.info("sql3: " + sqlrecipients);

					sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);

					for(int i=0; i < sqlRecipientsResult.size(); i++) {
						try {
							Object so =  sqlRecipientsResult.get(i)[0];

							Object so_label = sqlRecipientsResult.get(i)[1];

							priorities.put(so.toString(), so_label.toString());
						}
						catch (Exception e) {
							LOGGER.info("error: " + e.toString());
						}
					}

					LOGGER.info("END SUB QUERIES: " + sqlrecipients);
				}


				LOGGER.info("GET ALL RECIPIENTS PRIORIETIES START");
				String sqlAllrecipients = ADAMQueryBuilder.getAllRecipientsPriorities(qvo.getTableNames().get(0), convtable.getTablename(), qvo.getWheres(), qvo.getRecipients(), datasetSource.name().toLowerCase());

				LOGGER.info("sql: " + sqlAllrecipients);

				List<Object[]> sqlAllrecResult = adamDao.tableQuery(sqlAllrecipients);

				LOGGER.info("sqlsize: " + sqlAllrecResult.size());

				Map<String, Map<String, String>> recipientPriorities = new HashMap<String, Map<String,String>>();

				for(int i=0; i < sqlAllrecResult.size(); i++) {
					// SO
					/**try {
						// if SO exist get the hashmap
						Object so =  sqlAllrecResult.get(i)[0];
						Object recipientCode =  sqlAllrecResult.get(i)[2];
						Object recipientLabel =  sqlAllrecResult.get(i)[3];

						if ( recipientPriorities.containsKey(so.toString())) {
							recipientPriorities.get(so.toString()).put(recipientCode.toString(), recipientLabel.toString());
						}
						else {
							Map<String, String> recipients = new HashMap<String, String>();
							recipients.put(recipientCode.toString(), recipientLabel.toString());
							recipientPriorities.put(so.toString(), recipients);
						}

					}
					catch (Exception e) {
						LOGGER.info("error SO: " + e.toString());
					}**/
					// OR
					try {
						// if SO exist get the hashmap
						Object or =  sqlAllrecResult.get(i)[1];
						Object recipientCode =  sqlAllrecResult.get(i)[2];
						Object recipientLabel =  sqlAllrecResult.get(i)[3];

						if ( recipientPriorities.containsKey(or.toString())) {
							recipientPriorities.get(or).put(recipientCode.toString(), recipientLabel.toString());
						}
						else {
							Map<String, String> donors = new HashMap<String, String>();
							donors.put(recipientCode.toString(), recipientLabel.toString());
							recipientPriorities.put(or.toString(), donors);
						}
					}
					catch (Exception e) {
						LOGGER.info("error OR: " + e.toString());
					}
				}
				LOGGER.info("GET ALL RECIPIENTS PRIORIETIES END");


				switch (datasetSource) {
				case ADAM_CRS: 
					if (qvo.getCurrentView().equals(ADAMCurrentVIEW.ADAMVIEW)) { //DAC Framework
						if(recipientPriorities.isEmpty()){
							rvo.setCountriesPriorities(recipientPriorities);
						} else {
							rvo.setCountriesPriorities(convertORSToDAC(recipientPriorities));
						}

					} else if(qvo.getCurrentView().equals(ADAMCurrentVIEW.FAOVIEW)){ //FAO Strategic Framework
						rvo.setCountriesPriorities(recipientPriorities);
					}
					break;
				case ADAM_FPMIS: 	
					rvo.setCountriesPriorities(recipientPriorities);
					break;
				}
				
				LOGGER.info("RECIPIENT: getCountriesPriorities:" + rvo.getCountriesPriorities());
				//rvo.setCountriesPriorities(recipientPriorities);

				break;


			case VENN_CHANNEL_PRIORITIES:

				// check if all the recipients have the CPF
				String sqlCheckCPFRecipientsNumber = ADAMQueryBuilder.checkCFPRecipientsNumber(qvo.getTableNames().get(0), convtable.getTablename(), qvo.getWheres(), qvo.getChannels(), qvo.getRecipients(), "EN", datasetSource.name().toLowerCase());
				LOGGER.info("sql: " + sqlCheckCPFRecipientsNumber);
				Double cpfRecipientsNumber = adamDao.tableQueryValue(sqlCheckCPFRecipientsNumber);

				LOGGER.info("cpfRecipientsNumber: " + cpfRecipientsNumber);

			/**	if ( cpfRecipientsNumber != qvo.getRecipients().size()) {
					// GET ALL THE Strategic Framework
					LOGGER.info("Get all the strategic Framework -> cpfRecipientsNumber: " + cpfRecipientsNumber + " AND recipients: " + qvo.getRecipients().size());
					LOGGER.info("(Channel) Quering to get the FAO whole strategic framework ");
					// TAKING whole SOs for the selected recipients
					sqlrecipients = ADAMQueryBuilder.getChannelsWholeORs(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getChannels(), "EN");

					LOGGER.info("(Channel) WHOLE ORs: " + sqlrecipients);

					sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);

					LOGGER.info("(Channel) WHOLE ORs: sqlsize: " + sqlRecipientsResult.size());

					if ( sqlRecipientsResult.size() > 0 ) {
						for(int i=0; i < sqlRecipientsResult.size(); i++) {
							try {
								Object or =  sqlRecipientsResult.get(i)[0];

								Object or_label = sqlRecipientsResult.get(i)[1];

								priorities.put(or.toString(), or_label.toString());
							}
							catch (Exception e) {
								LOGGER.info("error: " + e.toString());
							}
						}
					}
				}
**/
				// get Countries priorities
				//else {
					LOGGER.info("cpfRecipientsNumber == qvo.getRecipients().size(): ALL THE COUNTRIES HAVE THE CPF" );

					// this one gets all the priorities between the channel (FAO) and the countries selected
					String sqlc = ADAMQueryBuilder.getChannelsORPriorities(qvo.getTableNames().get(0), convtable.getTablename(), qvo.getWheres(), qvo.getChannels(), qvo.getRecipients(), "EN", datasetSource.name().toLowerCase());

					LOGGER.info("sql: " + sqlc);

					List<Object[]> sqlCResult = adamDao.tableQuery(sqlc);

					LOGGER.info("sqlsize: " + sqlCResult.size());

					for(int i=0; i < sqlCResult.size(); i++) {
						try {
							Object so =  sqlCResult.get(i)[0];

							Object so_label = sqlCResult.get(i)[1];

							priorities.put(so.toString(), so_label.toString());
						}
						catch (Exception e) {
							LOGGER.info("error: " + e.toString());
						}
					}


					// TAKING whole SOs for the selected recipients
					sqlrecipients = ADAMQueryBuilder.getChannelsSOPriorities(qvo.getTableNames().get(0), convtable.getTablename(), qvo.getWheres(), qvo.getChannels(), qvo.getRecipients(), "EN", datasetSource.name().toLowerCase());

					LOGGER.info("sql2: " + sqlrecipients);

					sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);

					LOGGER.info("2) sqlsize: " + sqlRecipientsResult.size());

					if ( sqlRecipientsResult.size() > 0 ) {
							// contains complete SOs, the get all the ORs related
						List<String> sos = new ArrayList<String>();
						for(int i=0; i < sqlRecipientsResult.size(); i++) {

							try {
								Object so =  sqlRecipientsResult.get(i)[0];
								sos.add(so.toString());
							}
							catch (Exception e) {
								LOGGER.info("error: " + e.toString());
							}
						}

						sqlrecipients = ADAMQueryBuilder.getALLORsFromSOs(sos);

						LOGGER.info("sql3: " + sqlrecipients);

						sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);

						for(int i=0; i < sqlRecipientsResult.size(); i++) {
							try {
								Object so =  sqlRecipientsResult.get(i)[0];

								Object so_label = sqlRecipientsResult.get(i)[1];

								priorities.put(so.toString(), so_label.toString());
							}
							catch (Exception e) {
								LOGGER.info("error: " + e.toString());
							}
						}

						LOGGER.info("END SUB QUERIES: " + sqlrecipients);
					}
				//}

				LOGGER.info("(Channel) priorities: " + priorities);

				break;

			case VENN_DONOR_PRIORITIES:
				getVennDonorPriorities(qvo, rvo, priorities);
			}

		LOGGER.info("END CURRENT VIEW : " + qvo.getCurrentView().name());

		switch (datasetSource) {
		case ADAM_CRS: 
			if (qvo.getCurrentView().equals(ADAMCurrentVIEW.ADAMVIEW)) { //DAC Framework

				if(!priorities.keySet().isEmpty()){
					List<String> dacCodes = convertORSToDAC(priorities.keySet().toArray());
					rvo.setPriorities(getDACCodesLabels(dacCodes));
				} else
					rvo.setPriorities(null);

			} else if(qvo.getCurrentView().equals(ADAMCurrentVIEW.FAOVIEW)){ //FAO Strategic Framework
				rvo.setPriorities(priorities);
			}
			break;
		case ADAM_FPMIS: 	
			rvo.setPriorities(priorities);
			break;
		}
		

		/*	if (qvo.getCurrentView().equals(ADAMCurrentVIEW.ADAMVIEW)) { //DAC Framework

				if(!priorities.keySet().isEmpty()){
					List<String> dacCodes = convertORSToDAC(priorities.keySet().toArray());
					rvo.setPriorities(getDACCodesLabels(dacCodes));
				} else
					rvo.setPriorities(null);

			} else if(qvo.getCurrentView().equals(ADAMCurrentVIEW.FAOVIEW)){ //FAO Strategic Framework
				rvo.setPriorities(priorities);
			}*/

			//rvo.setPriorities(priorities);



		return rvo;
	}

	private void getVennDonorPriorities(ADAMQueryVO qvo, ADAMResultVO rvo, Map<String, String> priorities){
		/** The donor venn is based on:
		 * 		1) OECD:
		 * 					works only on DAC (country level)
		 *
		 * 		2) AidData:
		 * 					works only on NONDAC (global)
		 *
		 * 		3) OECD_AidData:
		 * 					works on DAC (country level) and NONDAC (global level)
		 *
		 * **/

		LOGGER.info("Datasource switch: " + qvo.getDatasetSource());
		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());

		switch (datasetSource) {
		case ADAM_CRS:
			getVennDonorPrioritiesData(qvo, rvo, priorities, "DAC");
			break;
		case ADAM_FPMIS:
			getVennDonorPrioritiesData(qvo, rvo, priorities, null);
			break;
		case AID_DATA:
			//getVennDonorPrioritiesAIDData(qvo, rvo, priorities);
			break;

		case ADAM_CRS_AID_DATA:
			//getVennDonorPrioritiesOECD(qvo, rvo, priorities);
			//getVennDonorPrioritiesAIDData(qvo, rvo, priorities);
			break;

		}

	}

	private void getVennDonorPrioritiesData(ADAMQueryVO qvo, ADAMResultVO rvo, Map<String, String> priorities, String source) {
		
		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());
		
		
		String sqlD = ADAMQueryBuilder.getDonorsPrioritiesORs(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(), qvo.getRecipients(), source, "EN");

		/** The queries are based on the recipients selected (and the donors if selected), returing
		 * 	first the ORs then the SOs (distincted)
		 *  and at the end all the Ors and SOs to in order to have the full map with all the donorsc(code, label)
		 *  with their ORs and SOs.
		 * **/

		LOGGER.info("(Donor) sql: " + sqlD);

		List<Object[]> sqlDResult = adamDao.tableQuery(sqlD);

		LOGGER.info("(Donor) sqlsize: " + sqlDResult.size());

		for(int i=0; i < sqlDResult.size(); i++) {
			try {
				Object so =  sqlDResult.get(i)[0];

				Object so_label = sqlDResult.get(i)[1];

				priorities.put(so.toString(), so_label.toString());
			}
			catch (Exception e) {
				LOGGER.info("error: " + e.toString());
			}
		}


		LOGGER.info("(Donor) getting all the SOs and ORs");

		// TAKING whole SOs
		String sqlrecipients = ADAMQueryBuilder.getDonorsWholeSOs(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(), qvo.getRecipients(), source, "EN");

		LOGGER.info("(Donor) sql2: " + sqlrecipients);

		List<Object[]> sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);

		LOGGER.info("(Donor) 2) sqlsize: " + sqlRecipientsResult.size());

		if ( sqlRecipientsResult.size() > 0 ) {
				// contains complete SOs, the get all the ORs related
			List<String> sos = new ArrayList<String>();
			for(int i=0; i < sqlRecipientsResult.size(); i++) {

					try {
						Object so =  sqlRecipientsResult.get(i)[0];
						sos.add(so.toString());
					}
					catch (Exception e) {
						LOGGER.info("error: " + e.toString());
					}
				}

				sqlrecipients = ADAMQueryBuilder.getALLORsFromSOs(sos);

				LOGGER.info("sql3: " + sqlrecipients);

				sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);

				for(int i=0; i < sqlRecipientsResult.size(); i++) {
					try {
						Object so =  sqlRecipientsResult.get(i)[0];

						Object so_label = sqlRecipientsResult.get(i)[1];

						priorities.put(so.toString(), so_label.toString());
					}
					catch (Exception e) {
						LOGGER.info("error: " + e.toString());
					}
				}

				if ( !priorities.isEmpty() ) {
					LOGGER.info("(Donor) got priorities at country level " + priorities);
				}
				else {
					LOGGER.info("(Donor) getting priorities at global level");
				}
			}

			LOGGER.info("(Donor) END SUB QUERIES: " + sqlrecipients);


        String dataset = "";
        
		if(datasetSource.equals(ADAMSelectedDataset.ADAM_FPMIS))
			dataset = "FPMIS";
		else
			dataset = "ADAM";
			
		String sqlAll = ADAMQueryBuilder.getAllDonorsPriorities(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(), qvo.getRecipients(), source, dataset);
//
		LOGGER.info("(Donor) sql: " + sqlAll);

		List<Object[]> sqlAllResult = adamDao.tableQuery(sqlAll);

		LOGGER.info("(Donor) sqlsize: " + sqlAllResult.size());

		Map<String, Map<String, String>> donorsPriorities = new HashMap<String, Map<String,String>>();

		for(int i=0; i < sqlAllResult.size(); i++) {
			// SO
			try {
				// if SO exist get the hashmap
				Object so =  sqlAllResult.get(i)[0];
				Object donorCode =  sqlAllResult.get(i)[2];
				Object donorLabel =  sqlAllResult.get(i)[3];

				if ( donorsPriorities.containsKey(so.toString())) {
					donorsPriorities.get(so.toString()).put(donorCode.toString(), donorLabel.toString());
				}
				else {
					Map<String, String> donors = new HashMap<String, String>();
					donors.put(donorCode.toString(), donorLabel.toString());
					donorsPriorities.put(so.toString(), donors);
				}

			}
			catch (Exception e) {
				LOGGER.info("error SO: " + e.toString());
			}
			// OR
			try {
				// if SO exist get the hashmap
				Object or =  sqlAllResult.get(i)[1];
				Object donorCode =  sqlAllResult.get(i)[2];
				Object donorLabel =  sqlAllResult.get(i)[3];

				if ( donorsPriorities.containsKey(or.toString())) {
					donorsPriorities.get(or).put(donorCode.toString(), donorLabel.toString());
				}
				else {
					Map<String, String> donors = new HashMap<String, String>();
					donors.put(donorCode.toString(), donorLabel.toString());
					donorsPriorities.put(or.toString(), donors);
				}
			}
			catch (Exception e) {
				LOGGER.info("error OR: " + e.toString());
			}
		}
		LOGGER.info("(Donor) GET ALL DONORS PRIORIETIES END");

		LOGGER.info("(Donor) donorsPriorities:" + donorsPriorities.toString());

//		LOGGER.info("(Donor)  rvo.getDonorsPriorities():" + rvo.getDonorsPriorities());
		if ( rvo.getDonorsPriorities() == null) {
			switch (datasetSource) {
			case ADAM_CRS:
				if (qvo.getCurrentView().equals(ADAMCurrentVIEW.ADAMVIEW)) { //DAC Framework
					if(donorsPriorities.isEmpty()){
						rvo.setDonorsPriorities(donorsPriorities);
					} else {
						rvo.setDonorsPriorities(convertORSToDAC(donorsPriorities));
					}

					LOGGER.info("rvo.getDonorsPriorities() IF size: "+rvo.getDonorsPriorities().size());

				} else if(qvo.getCurrentView().equals(ADAMCurrentVIEW.FAOVIEW)){ //FAO Strategic Framework
					rvo.setDonorsPriorities(donorsPriorities);

					LOGGER.info("rvo.getDonorsPriorities() ELSE size: "+rvo.getDonorsPriorities().size());
				}
				break;
			case ADAM_FPMIS:
				rvo.setDonorsPriorities(donorsPriorities);
				break;
			}
			
			//rvo.setDonorsPriorities(donorsPriorities);
		}
		else {
			for( String key : donorsPriorities.keySet()){
//				LOGGER.info("priority: " + key );
				Map<String, String> map = new HashMap<String, String>();
				if ( rvo.getDonorsPriorities().containsKey(key)) {
					map = rvo.getDonorsPriorities().get(key);
				}
				map.putAll(donorsPriorities.get(key));
//				LOGGER.info("map: " + map );
				rvo.getDonorsPriorities().put(key, map);
			}
		}
	}


	/*private void getVennDonorPrioritiesAIDData(ADAMQueryVO qvo, ADAMResultVO rvo, Map<String, String> priorities) {

		*//** The queries are based the donors if selected (and not on the recipients), returing
		 * 	first the ORs then the SOs (distincted)
		 *  and at the end all the Ors and SOs to in order to have the full map with all the donorsc(code, label)
		 *  with their ORs and SOs.
		 * **//*

		String sqlD = ADAMQueryBuilder.getDonorsPrioritiesORs(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(), new HashMap<String, String>(), "NONDAC", "EN");


		LOGGER.info("(Donor) sql: " + sqlD);

		List<Object[]> sqlDResult = adamDao.tableQuery(sqlD);

		LOGGER.info("(Donor) sqlsize: " + sqlDResult.size());

		for(int i=0; i < sqlDResult.size(); i++) {
			try {
				Object so =  sqlDResult.get(i)[0];

				Object so_label = sqlDResult.get(i)[1];

				priorities.put(so.toString(), so_label.toString());
			}
			catch (Exception e) {
				LOGGER.info("error: " + e.toString());
			}
		}


		LOGGER.info("(Donor) getting all the SOs and ORs");

		// TAKING whole SOs
		String sqlrecipients = ADAMQueryBuilder.getDonorsWholeSOs(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(),  new HashMap<String, String>(), "NONDAC", "EN");

		LOGGER.info("(Donor) sql2: " + sqlrecipients);

		List<Object[]> sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);

		LOGGER.info("(Donor) 2) sqlsize: " + sqlRecipientsResult.size());

		if ( sqlRecipientsResult.size() > 0 ) {
				// contains complete SOs, the get all the ORs related
			List<String> sos = new ArrayList<String>();
			for(int i=0; i < sqlRecipientsResult.size(); i++) {

					try {
						Object so =  sqlRecipientsResult.get(i)[0];
						sos.add(so.toString());
					}
					catch (Exception e) {
						LOGGER.info("error: " + e.toString());
					}
				}

			sqlrecipients = ADAMQueryBuilder.getALLORsFromSOs(sos);

			LOGGER.info("(Donor) sql3: " + sqlrecipients);

			sqlRecipientsResult = adamDao.tableQuery(sqlrecipients);

			for(int i=0; i < sqlRecipientsResult.size(); i++) {
				try {
					Object so =  sqlRecipientsResult.get(i)[0];

					Object so_label = sqlRecipientsResult.get(i)[1];

					priorities.put(so.toString(), so_label.toString());
				}
				catch (Exception e) {
					LOGGER.info("error: " + e.toString());
				}
			}

				if ( !priorities.isEmpty() ) {
					LOGGER.info("(Donor) got priorities at country level " + priorities);
				}
				else {
					LOGGER.info("(Donor) getting priorities at global level");
				}
			}

			LOGGER.info("(Donor) END SUB QUERIES: " + sqlrecipients);



		String sqlAll = ADAMQueryBuilder.getAllDonorsPriorities(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getDonors(),  new HashMap<String, String>(), "NONDAC");
//
		LOGGER.info("(Donor) sql: " + sqlAll);

		List<Object[]> sqlAllResult = adamDao.tableQuery(sqlAll);

		LOGGER.info("(Donor) sqlsize: " + sqlAllResult.size());

		Map<String, Map<String, String>> donorsPriorities = new HashMap<String, Map<String,String>>();

		for(int i=0; i < sqlAllResult.size(); i++) {
			// SO
			try {
				// if SO exist get the hashmap
				Object so =  sqlAllResult.get(i)[0];
				Object donorCode =  sqlAllResult.get(i)[2];
				Object donorLabel =  sqlAllResult.get(i)[3];

				if ( donorsPriorities.containsKey(so.toString())) {
					donorsPriorities.get(so.toString()).put(donorCode.toString(), donorLabel.toString());
				}
				else {
					Map<String, String> donors = new HashMap<String, String>();
					donors.put(donorCode.toString(), donorLabel.toString());
					donorsPriorities.put(so.toString(), donors);
				}

			}
			catch (Exception e) {
				LOGGER.info("error SO: " + e.toString());
			}
			// OR
			try {
				// if SO exist get the hashmap
				Object or =  sqlAllResult.get(i)[1];
				Object donorCode =  sqlAllResult.get(i)[2];
				Object donorLabel =  sqlAllResult.get(i)[3];

				if ( donorsPriorities.containsKey(or.toString())) {
					donorsPriorities.get(or).put(donorCode.toString(), donorLabel.toString());
				}
				else {
					Map<String, String> donors = new HashMap<String, String>();
					donors.put(donorCode.toString(), donorLabel.toString());
					donorsPriorities.put(or.toString(), donors);
				}
			}
			catch (Exception e) {
				LOGGER.info("error OR: " + e.toString());
			}
		}
		LOGGER.info("(Donor) GET ALL DONORS PRIORIETIES END");

		LOGGER.info("(Donor) donorsPriorities:" + donorsPriorities.toString());


//		LOGGER.info("(Donor)  rvo.getDonorsPriorities():" + rvo.getDonorsPriorities());
		if ( rvo.getDonorsPriorities() == null) {
				if (qvo.getCurrentView().equals(ADAMCurrentVIEW.ADAMVIEW)) { //DAC Framework
					if(donorsPriorities.isEmpty()){
						rvo.setDonorsPriorities(donorsPriorities);
					} else {
						rvo.setDonorsPriorities(convertORSToDAC(donorsPriorities));
					}

				} else if(qvo.getCurrentView().equals(ADAMCurrentVIEW.FAOVIEW)){ //FAO Strategic Framework
					rvo.setDonorsPriorities(donorsPriorities);
				}

				//rvo.setDonorsPriorities(donorsPriorities);
		}
		else {
			for( String key : donorsPriorities.keySet()){
//				LOGGER.info("priority: " + key );
				Map<String, String> map = new HashMap<String, String>();
				if ( rvo.getDonorsPriorities().containsKey(key)) {
					map = rvo.getDonorsPriorities().get(key);
				}
				map.putAll(donorsPriorities.get(key));
//				LOGGER.info("map: " + map );
				rvo.getDonorsPriorities().put(key, map);
			}
		}
	}


*/



	private HashMap<String, List<CodeVo>> getDonorProfileCodeList(String langCode) {
			HashMap<String, List<CodeVo>> result = new HashMap<String, List<CodeVo>>();
			List<CodeVo> r = new ArrayList<CodeVo>();
			String codeList = new String();

			/** FAO Strategic Framework code list **/
			codeList = "FAO Strategic Framework";
			List<Code> codes = codecDao.getCodesOfaSystemLangSortedByCode(codeList, "0", langCode);
			for(Code code : codes)
				r.add(CodingVoConverter.code2Vo(code));
			result.put("DD-00", r);


			return result;
}

	public List<Donor> getDonorsWithProfiles() {

		LOGGER.info("START---------");

		List<Donor> models = new ArrayList<Donor>();
		CustomDataset dataset = customDatasetDao.find("ADAM_DONOR_PROFILE");
		String sql = ADAMQueryBuilder.getDonorsWithProfiles(dataset.getTablename());

		List<Object[]> sqlResult = adamDao.tableQuery(sql);

		LOGGER.info(sqlResult.size());
		if(sqlResult.size() > 0){


			for(int j=0; j < sqlResult.size(); j++) {
				Donor model = new Donor();
				if(sqlResult.get(j)[0]!=null) {
					Date date = (Date)sqlResult.get(j)[0];
					SimpleDateFormat formatter = new SimpleDateFormat("MMM-yyyy");
					String formattedDate = formatter.format(date);
					model.setReferenceDate(formattedDate);
				}
				else
					model.setReferenceDate("");

				if(sqlResult.get(j)[1]!=null){
					model.setCode(sqlResult.get(j)[1].toString());
				}
				else
					model.setCode("");

				if(sqlResult.get(j)[2]!=null) {
					model.setName(sqlResult.get(j)[2].toString());
				}
				else
					model.setName("");

				models.add(model);
			}
		}

		LOGGER.info("END ---------");

		return models;
	}

	private Map<String, HashMap<String, String>> convertORtoDAC(Map<String, String> ors) {
		Map<String, String> dac = new HashMap<String, String>();

		// DAC_code, ORs codes, ORs, Labels
		Map<String, HashMap<String, String>> dac_ors_labels = new HashMap<String, HashMap<String, String>>();


		CustomDataset dataset = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");

		// get all dac codes from
		String sql = ADAMQueryBuilder.getDACfromORs(dataset.getTablename(), ors);


		List<Object[]> result = adamDao.tableQuery(sql);

		for (Object[] array : result) {
			try {
				// adding dac
				dac.put(array[0].toString(), array[0].toString());

				// adding OR
				if ( dac_ors_labels.containsKey(array[0].toString())) {
					// dac already exist...
					dac_ors_labels.get(array[0].toString()).put(array[1].toString(), array[1].toString());
				}
				else {
					// creating a new HashMap
					HashMap<String, String> or = new HashMap<String, String>();
					or.put(array[1].toString(), array[1].toString());
					dac_ors_labels.put(array[0].toString(), or);
				}

			}catch (Exception e) {
				LOGGER.error("this should not happen...");
			}
		}

		LOGGER.info("dac_ors_labels: " + dac_ors_labels);




		return dac_ors_labels;
	}

	public Map<String,String> getRecipientsForFAORegion(String regionCode) {
		Map<String, String> codes = new HashMap<String, String>();

		String sql = ADAMQueryBuilder.getRecipientCodesForFAORegion(regionCode);
		List<Object[]> result = adamDao.tableQuery(sql);

		for(int i=0; i < result.size(); i++)
			codes.put(result.get(i)[0].toString(), result.get(i)[1].toString());


		return codes;
	}


private ADAMResultVO createChartBarStackOR_Donor(ADAMQueryVO qvo, ADAMResultVO rvo) {


		String sql = null;
		// create the SQL query
		CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
//		CustomDataset oecdtable = customDatasetDao.find(qvo.getDatasetCodes().get(0));

		if ( qvo.getIsOR() ) {
			LOGGER.info("IS OR");
//			sql = ADAMQueryBuilder.buildOR_Donorquery(convtable.getTablename(), oecdtable.getTablename(), qvo);
		}

		if ( qvo.getIsSO() ) {
			// TODO: get all ORs by SOs add to the priorities hashmap, and get the result
			List<Object[]> result = null;
			LOGGER.info("IS SO");
			HashMap<String, String> prioritiesHM = new HashMap<String, String>();
			List<String> priorities = new ArrayList<String>();
			for(String key : qvo.getPriorities().keySet() ) {
				priorities.add(key);
			}
			sql = ADAMQueryBuilder.getALLORsFromSOs(priorities);
			result = adamDao.tableQuery(sql);

			for(int i=0; i < result.size(); i++) {
				try {
					Object so =  result.get(i)[0];

					Object so_label = result.get(i)[1];

					prioritiesHM.put(so.toString(), so_label.toString());
				}
				catch (Exception e) {
					LOGGER.info("error: " + e.toString());
				}
			}

			qvo.setPriorities(prioritiesHM);

		}


		sql = ADAMQueryBuilder.buildOR_Donorquery(convtable.getTablename(), qvo.getTableNames().get(0), qvo);

		LOGGER.info("CHART: " + sql);

		Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartStackQuery(sql);
		

		LOGGER.info("TOTAL RESULT: " + codeLabelsResult);
		Map<String, Double> valueToSort = new HashMap<String, Double>();

		for(String or : codeLabelsResult.keySet()) {
			for(String country : codeLabelsResult.get(or).keySet()) {
				Double currentValue = codeLabelsResult.get(or).get(country);

				if( valueToSort.containsKey(or)) {
					Double value = valueToSort.get(or);
					currentValue = value + currentValue;
				}

				System.out.println("OR: " + or + " | " + country + " | " + currentValue);
				valueToSort.put(or, currentValue);

			}
		}

		LOGGER.info("VALUE TO SORT: " + valueToSort);
		LOGGER.info("END CHART: " + sql);

		// prepare JFreeChart input
		Map<String, Double> sortedJDBCResult = sortByValue(valueToSort);


		LOGGER.info("sortedJDBCResult: " + sortedJDBCResult);


		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		int i = 0;

		Double othersValue = new Double(0);
		for(String or : sortedJDBCResult.keySet()) {
			if ( i < qvo.getLimit())
				values.put(or, sortByValue(codeLabelsResult.get(or)));
			else {
				othersValue = othersValue + sortedJDBCResult.get(or);
			}
			i++;
		}



		if ( othersValue != 0 ) {
			Map<String,Double> others = new HashMap<String, Double>();
			others.put("Others", othersValue);
			values.put("Others", others);
		}

		LOGGER.info("OTHERS VALUES: " + othersValue);
		LOGGER.info("FINAL VALUES: " + values);

		rvo.setChartValues(values);


		// get ORs labels (they are displayed in the description of the chart (info icon)
		List<String> ors = new ArrayList<String>();
		for(String or : sortedJDBCResult.keySet()) {
			ors.add(or);
		}

		HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
		if ( !ors.isEmpty() ) {
			List<Code>	codes = codecDao.getCodes(ors, "FAO Strategic Framework", "EN");

			//rvo.setDescription(createInfoDescription("Organizational Results (ORs)", codes));

			// retrieve coding system for the hover on the chart...
			//LOGGER.info("--------codes.size(): " + codes.size());
			for(Code c : codes) {
				//LOGGER.info("----> Labels SF: " + c.getCode() + " | " + c.getLabel());
				xAxisFullLabel.put(c.getCode(), c.getCode() + " - " + c.getLabel());
			}
			//LOGGER.info("--------");
			// end
		}

		if(!xAxisFullLabel.isEmpty())
			rvo.setxAxisFullLabels(xAxisFullLabel);

		return createBarStackChart(qvo, rvo, values, xAxisFullLabel, JFreeChartConstants.BAR_STACK);

	}

	private ADAMResultVO createChartBarStackComparisonSwitcher(ADAMQueryVO qvo, ADAMResultVO rvo) {
	
	if ( qvo.getIsOR() || qvo.getIsSO() ) {
			// this is the switch for the different return types
			if ( qvo.getReturnFrameworkType() != null ) {
				ADAMBoxContent returnType = ADAMBoxContent.valueOf(qvo.getReturnFrameworkType());
				switch (returnType) {
					case RETURN_OR: rvo = createChartStackComparisonOR(qvo, rvo); break;
					//case RETURN_SO: rvo = createTimeSerieSO(qvo, rvo); break;
				}
				return rvo;
			}
			else
				return createChartStackComparisonOR(qvo, rvo);
		}
		else
			return createChartBarStackComparison(qvo, rvo);
	}

	 private ADAMResultVO createChartBarStackComparison(ADAMQueryVO qvo, ADAMResultVO rvo) {

		  List<String> tablenames = customDatasetDao.findTablenamesFromCodes(qvo.getDatasetCodes());
			
		     //This is for when FAO-Related sub sectors is selected as a sector ---> query fao_sub_sectors_data View
				if(!qvo.getAggregatedViewNames().isEmpty() && qvo.getAggregatedViewNames().get(0)!=null){
					qvo.setTableNames(qvo.getAggregatedViewNames());
				}
				else {
					if( tablenames.isEmpty()) 
						qvo.setTableNames(qvo.getDatasetCodes());

					else 
						qvo.setTableNames(tablenames);
				}
				
		  
		// CustomDataset oecdtable = customDatasetDao.find(qvo.getDatasetCodes().get(0));
		 
		 //get the sum for the selected sub-sector
		 String sql = ADAMQueryBuilder.sumQuery(qvo.getTableNames().get(0), qvo);
		 //Double sum = adamDao.tableQueryValue(sql);
		 List<Object> sum = adamDao.tableQueryUniqueValue(sql);
		 
		 //There will only be 1 OR
		 String selectedPurposeCode = qvo.getWheres().get("purposecode").get(0);
		 qvo.setTitle(qvo.getTitle());
		
	     String label = codecDao.getLabelFromCodeCodingSystem(selectedPurposeCode, "ADAMPurposes", "0", "EN");
		   
	     if(label!=null)
	    	 qvo.setSubTitle("Total "+qvo.getAggregationLabel()+" for "+label+" is "+sum.get(0)+" "+qvo.getMeasurementUnit());
	     else
	    	 qvo.setSubTitle("Total "+qvo.getAggregationLabel()+" for "+selectedPurposeCode+" is "+sum.get(0)+" "+qvo.getMeasurementUnit());
	     
		 if(qvo.isAgricultureFilter() && !qvo.getTableNames().get(0).equalsIgnoreCase("fao_sub_sectors_data")){
			 CustomDataset dac_or_conversion = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
		 	sql = ADAMQueryBuilder.compareSubSectorToTop10AgricultureRelatedSectorsQuery(qvo.getTableNames().get(0), dac_or_conversion.getTablename(), qvo);
		 } else {
			 sql = ADAMQueryBuilder.compareSubSectorToTop10SubSectorsQuery(qvo.getTableNames().get(0), qvo); 
		 }		
			 
			 
		  Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartStackQuery(sql);

		  LOGGER.info("TOTAL RESULT: " + codeLabelsResult);
		  Map<String, Double> valueToSort = new HashMap<String, Double>();

		  HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
		  
		  for(String dac : codeLabelsResult.keySet()) {
			  for(String dac_label : codeLabelsResult.get(dac).keySet()) {
				  Double currentValue = codeLabelsResult.get(dac).get(dac_label);

				 /** if( valueToSort.containsKey(dac)) {
					  Double value = valueToSort.get(dac);
					  currentValue = value + currentValue;
				  }**/

				  if( valueToSort.containsKey(dac_label)) {
					  Double value = valueToSort.get(dac);
					  currentValue = value + currentValue;
				  }
				  
				  System.out.println("DAC: " + dac + " | " + dac_label + " | " + currentValue);
				  
				 //valueToSort.put(dac, currentValue);
			     //xAxisFullLabel.put(dac, dac_label);
			     
				 
				  valueToSort.put(dac_label, currentValue);
				  xAxisFullLabel.put(dac_label, dac_label);
				 
			  }
		  }

		LOGGER.info("VALUE TO SORT: " + valueToSort);



		LOGGER.info("END CHART: " + sql);

		// prepare JFreeChart input
		Map<String, Double> sortedJDBCResult = sortByValue(valueToSort);


		LOGGER.info("sortedJDBCResult: " + sortedJDBCResult);


		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		int i = 0;

		Double othersValue = new Double(0);
		for(String or : sortedJDBCResult.keySet()) {
			if ( i < qvo.getLimit())
				values.put(or, sortByValue(codeLabelsResult.get(or)));
			else {
				othersValue = othersValue + sortedJDBCResult.get(or);
			}
			i++;
		}



		/**if ( othersValue != 0 ) {
			Map<String,Double> others = new HashMap<String, Double>();
			others.put("Others", othersValue);
			values.put("Others", others);
		}**/

		//LOGGER.info("OTHERS VALUES: " + othersValue);
		LOGGER.info("FINAL VALUES: " + values);

		rvo.setChartValues(values);


		/*// get ORs labels (they are displayed in the description of the chart (info icon)
		List<String> ors = new ArrayList<String>();
		for(String or : sortedJDBCResult.keySet()) {
			ors.add(or);
		}

		HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
		if ( !ors.isEmpty() ) {
			List<Code>	codes = codecDao.getCodes(ors, "FAO Strategic Framework", "EN");

			//rvo.setDescription(createInfoDescription("Organizational Results (ORs)", codes));

			// retrieve coding system for the hover on the chart...
			LOGGER.info("--------codes.size(): " + codes.size());
			for(Code c : codes) {
				LOGGER.info("----> Labels SF: " + c.getCode() + " | " + c.getLabel());
				xAxisFullLabel.put(c.getCode(), c.getCode() + " - " + c.getLabel());
			}
			LOGGER.info("--------");
			// end
		}
	*/


		return createBarStackChart(qvo, rvo, values, xAxisFullLabel, JFreeChartConstants.BAR_STACK_DIFFERENCE);

	}
  
	 private ADAMResultVO createChartStackComparisonOR(ADAMQueryVO qvo, ADAMResultVO rvo) {

	  CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");

	  //get the sum for the selected OR
	  String sql = ADAMQueryBuilder.buildORAggregationQuery(convtable.getTablename(), qvo.getTableNames().get(0), qvo);	
	  Double sum = adamDao.tableQueryValue(sql);
	  
	  //There will only be 1 OR
	  String selectedOr = "";
	  for(String code: qvo.getPriorities().keySet()) 
			selectedOr = code;
			  
	  qvo.setTitle(qvo.getTitle());	
	  qvo.setSubTitle("Total Commitment for "+selectedOr+" is "+sum+" "+qvo.getMeasurementUnit());
	  sql = ADAMQueryBuilder.compareOrToTop10OrsQuery(convtable.getTablename(), qvo.getTableNames().get(0), sum, qvo);
	  
	  Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartStackQuery(sql);

	  LOGGER.info("TOTAL RESULT: " + codeLabelsResult);
	  Map<String, Double> valueToSort = new HashMap<String, Double>();
  
	  for(String or : codeLabelsResult.keySet()) {
		  for(String or_label : codeLabelsResult.get(or).keySet()) {
			  Double currentValue = codeLabelsResult.get(or).get(or_label);

			  if( valueToSort.containsKey(or_label)) {
				  Double value = valueToSort.get(or);
				  currentValue = value + currentValue;
			  }
			  
			  valueToSort.put(or_label, currentValue);
			 
		  }
	  }

	 
	LOGGER.info("VALUE TO SORT: " + valueToSort);

	LOGGER.info("END CHART: " + sql);

	// prepare JFreeChart input
	Map<String, Double> sortedJDBCResult = sortByValue(valueToSort);


	LOGGER.info("sortedJDBCResult: " + sortedJDBCResult);

	LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

	int i = 0;

	for(String or : sortedJDBCResult.keySet()) {
		if ( i < qvo.getLimit())
			values.put(or, sortByValue(codeLabelsResult.get(or)));
		i++;
	}


	//LOGGER.info("OTHERS VALUES: " + othersValue);
	LOGGER.info("FINAL VALUES: " + values);

	rvo.setChartValues(values);


	// get ORs labels (they are displayed in the description of the chart (info icon)
	List<String> ors = new ArrayList<String>();
	for(String or : sortedJDBCResult.keySet()) {
		ors.add(or);
	}

	HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
	if ( !ors.isEmpty() ) {
		List<Code>	codes = codecDao.getCodes(ors, "FAO Strategic Framework", "EN");

		//rvo.setDescription(createInfoDescription("Organizational Results (ORs)", codes));

		// retrieve coding system for the hover on the chart...
		//LOGGER.info("--------codes.size(): " + codes.size());
		for(Code c : codes) {
			//LOGGER.info("----> Labels SF: " + c.getCode() + " | " + c.getLabel());
			xAxisFullLabel.put(c.getCode(), c.getCode() + " - " + c.getLabel());
		}
		//LOGGER.info("--------");
		// end
	}



	return createBarStackChart(qvo, rvo, values, xAxisFullLabel, JFreeChartConstants.BAR_STACK_DIFFERENCE);

}
  
 

  private ADAMResultVO createChartBarStackDAC_Donor(ADAMQueryVO qvo, ADAMResultVO rvo) {


	String sql = ADAMQueryBuilder.buildDAC_Donorquery(qvo.getTableNames().get(0), qvo);

	LOGGER.info("CHART: " + sql);

	Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartStackQuery(sql);

	LOGGER.info("TOTAL RESULT: " + codeLabelsResult);
	Map<String, Double> valueToSort = new HashMap<String, Double>();

	for(String dac: codeLabelsResult.keySet()) {
		for(String country : codeLabelsResult.get(dac).keySet()) {
			Double currentValue = codeLabelsResult.get(dac).get(country);

			if( valueToSort.containsKey(dac)) {
				Double value = valueToSort.get(dac);
				currentValue = value + currentValue;
			}

			System.out.println("DAC: " + dac + " | " + country + " | " + currentValue);
			valueToSort.put(dac, currentValue);

		}
	}

	LOGGER.info("VALUE TO SORT: " + valueToSort);



	LOGGER.info("END CHART: " + sql);

	// prepare JFreeChart input
	Map<String, Double> sortedJDBCResult = sortByValue(valueToSort);


	LOGGER.info("sortedJDBCResult: " + sortedJDBCResult);


	LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

	int i = 0;

	Double othersValue = new Double(0);
	for(String dac : sortedJDBCResult.keySet()) {
		if ( i < qvo.getLimit())
			values.put(dac, sortByValue(codeLabelsResult.get(dac)));
		else {
			othersValue = othersValue + sortedJDBCResult.get(dac);
		}
		i++;
	}



	if ( othersValue != 0 ) {
		Map<String,Double> others = new HashMap<String, Double>();
		others.put("Others", othersValue);
		values.put("Others", others);
	}

	LOGGER.info("OTHERS VALUES: " + othersValue);
	LOGGER.info("FINAL VALUES: " + values);

	rvo.setChartValues(values);


	// get DAC labels (they are displayed in the description of the chart (info icon)
	List<String> dacs = new ArrayList<String>();
	for(String dac : sortedJDBCResult.keySet()) {
		dacs.add(dac);
	}

	HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
	
	if ( !dacs.isEmpty() ) {
		List<Code>	codes = codecDao.getCodes(dacs, "ADAMPurposes", "EN");

		//rvo.setDescription(createInfoDescription("DAC Sector Results", codes));

		// retrieve coding system for the hover on the chart...
		//LOGGER.info("--------codes.size(): " + codes.size());
		for(Code c : codes) {
			//LOGGER.info("----> Labels SF: " + c.getCode() + " | " + c.getLabel());
			xAxisFullLabel.put(c.getCode(), c.getCode() + " - " + c.getLabel());
		}
		
		
		//LOGGER.info("--------");
		// end
	}
	

	//LOGGER.info(">>> xAxisFullLabel = "+ xAxisFullLabel);
	if(!xAxisFullLabel.isEmpty())
		rvo.setxAxisFullLabels(xAxisFullLabel);
		
	//LOGGER.info(">>> getxAxisFullLabels = "+ rvo.getxAxisFullLabels());

	return createBarStackChart(qvo, rvo, values, xAxisFullLabel, JFreeChartConstants.BAR_STACK);

}

	private String createInfoDescription(String title, List<Code> codes) {
		StringBuffer html = new StringBuffer();

		// title
		html.append("<div class='title-box'>" + title + "</div>");
		html.append("<br>");

		// codes
		for(Code code : codes) {
			html.append("<div class='small-content'><b>" +code.getCode() + "</b> - " + code.getLabel() + "</div>");

			html.append("<br>");
		}

		return html.toString();
	}

	private ADAMResultVO createGoogleMapSwitcher(ADAMQueryVO qvo, ADAMResultVO rvo) {

		//Add the Sub-Regional countries to the where clause, when only 1 country has been selected 
		if(!qvo.getWheres().isEmpty() && qvo.getWheres().containsKey("recipientcode")){
			if(qvo.getWheres().get("recipientcode").size() == 1) 
				setSubRegionalCountries(qvo);
		}
		if ( qvo.getIsOR() || qvo.getIsSO() ) {
			// this is the switch for the different return types
			if ( qvo.getReturnFrameworkType() != null ) {
				ADAMBoxContent returnType = ADAMBoxContent.valueOf(qvo.getReturnFrameworkType());
				switch (returnType) {
					case RETURN_OR: rvo = createGoogleORDataset(qvo, rvo); break;
					case RETURN_SO: rvo = createGoogleDataset(qvo, rvo); break;
				}
				return rvo;
			}
			else
				return createGoogleORDataset(qvo, rvo);
		}
		else
			return createGoogleDataset(qvo, rvo);
	}
		

	private void setSubRegionalCountries(ADAMQueryVO qvo) {
		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());
		String hiearchyCode = "";
		if(datasetSource.equals(ADAMSelectedDataset.ADAM_FPMIS))
			hiearchyCode = "FPMISRecipients";
		else
			hiearchyCode = "ADAMRecipients";
		
		
		CodingSystem codingSystem = codecDao.getCodingSystem(hiearchyCode, "0");
		String recipientcode = qvo.getWheres().get("recipientcode").get(0);

		List<CodingHierarchy> sub_regions = codecDao.getCodingHierarchyFather(codingSystem.getResourceId(), recipientcode);
		
		LOGGER.info("----------- sub_region of "+recipientcode + " | [" +sub_regions.get(0).getCode()+"]");
		
		if(!sub_regions.isEmpty()){
			List<Code> subregional_countries = adamDao.findSubLevel(codingSystem.getResourceId(), codingSystem.getTitle(), sub_regions.get(0).getCode(), "EN");
			LOGGER.info("----------- sub_region of "+sub_regions.get(0).getCode() + " | [" +subregional_countries+"]");
			
			List<String> countries = new ArrayList<String>();
			for(Code code: subregional_countries){
				countries.add(code.getCode());
			}
			
			if(!countries.isEmpty()){
				 qvo.getWheres().get("recipientcode").clear();	
				 qvo.getWheres().get("recipientcode").addAll(countries);
			}
			 
		}
		
	}

	private ADAMResultVO createGoogleDataset(ADAMQueryVO qvo, ADAMResultVO rvo) {

	    LOGGER.info("## --1-- CREATE GOOGLE MAP for '"+qvo.getTitle()+"'");
		
		CustomDataset convtable = customDatasetDao.find("ADM0_CONVERSION_TABLE");

//		CustomDataset oecdtable = customDatasetDao.find(qvo.getDatasetCodes().get(0));

		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());

		String sql = ADAMQueryBuilder.buildGoogleDataset(convtable.getTablename(), qvo.getTableNames().get(0), qvo, datasetSource);

		LOGGER.info("## --1-- GOOGLE MAP sql "+sql);

//		Map<String, Double> result = adamDao.standardQuery(sql);

		Map<String, Map<String, Double>> result = adamDao.chartQuery(sql);

		Map<String, Map<String, Double>> results = adamDao.chartQuery(sql);


//		Map<String, Double> values = new HashMap<String, Double>();



		for (String countryCode : result.keySet()) {
			for( String countryLabel : result.get(countryCode).keySet())
			try {
				HashMap<String, Double> value = new HashMap<String, Double>();
				value.put(countryLabel, Double.valueOf(FormatValues.formatValue(result.get(countryCode).get(countryLabel), 2)));
				results.put(countryCode, value);
			}
			catch (Exception e) {
				LOGGER.error("NO PARSABLE VALUE: " + countryCode + " | " + result.get(countryCode));
			}
		}

		//LOGGER.info("RESULT: " + result);

		// TODO: create a map value <String, Double>
		rvo.getChartValues().putAll(results);
		return rvo;
	}
	
	private ADAMResultVO createGoogleORDataset(ADAMQueryVO qvo, ADAMResultVO rvo) {

		CustomDataset iso_convtable = customDatasetDao.find("ADM0_CONVERSION_TABLE");
		
		CustomDataset fao_convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
	
		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());

		// get all crs codes
		String sql = "";
		switch(datasetSource){
		case ADAM_CRS: 
				sql = ADAMQueryBuilder.buildMapCRSORquery(fao_convtable.getTablename(), qvo.getTableNames().get(0), iso_convtable.getTablename(), qvo); 
				break;			
		case ADAM_FPMIS: 
				sql = ADAMQueryBuilder.buildMapFPMISORquery(qvo.getTableNames().get(0), iso_convtable.getTablename(), qvo); 
				break;	
		}
			
		LOGGER.info("sql: " + sql);

		Map<String, Map<String, Double>> result = adamDao.orChartQuery(sql);
		
		Map<String, Map<String, Double>> results = adamDao.orChartQuery(sql);


		for (String countryCode : result.keySet()) {
			for( String countryLabel : result.get(countryCode).keySet())
			try {
				HashMap<String, Double> value = new HashMap<String, Double>();
				value.put(countryLabel, Double.valueOf(FormatValues.formatValue(result.get(countryCode).get(countryLabel), 2)));
				results.put(countryCode, value);
			}
			catch (Exception e) {
				LOGGER.error("NO PARSABLE VALUE: " + countryCode + " | " + result.get(countryCode));
			}
		}

		LOGGER.info("RESULT: " + result);

		// TODO: create a map value <String, Double>
		rvo.getChartValues().putAll(results);
		
		return rvo;
	}


	private ADAMResultVO createChartSO(ADAMQueryVO qvo, ADAMResultVO rvo) {


		/** for every priority, get the DACs and get the TOTAL
		 *
		 *  put in the Map and Sort IT
		 *
		 * **/

		CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
//		CustomDataset oecdtable = customDatasetDao.find(qvo.getDatasetCodes().get(0));

		Map<String, Double> valuesHM = new HashMap<String, Double>();

		for(String so : qvo.getPriorities().keySet()) {
			//LOGGER.info("SO: " + so);

			// get dacs
			String dacSQL = ADAMQueryBuilder.getALLDacFromSO(convtable.getTablename(), so);
			//LOGGER.info("querying here...");
			List<Object> result = adamDao.tableQueryUniqueValue(dacSQL);

			LOGGER.info("after query...");

			List<String> values = new ArrayList<String>();
			//ADD TO FILTERS

//			for(Object[] content : result){
//				try {
//					LOGGER.info("inside the for");
//					values.add(content.toString());
//				}catch (Exception e) {
//					LOGGER.info("cannot parse it..");
//				}
//			}

			for(int i=0; i < result.size(); i++) {
				try {
					//LOGGER.info("inside the for");
					values.add(result.get(i).toString());
				}catch (Exception e) {
					LOGGER.info("cannot parse it..");
				}
			}

			LOGGER.info("DACs: " + values);



			// get TOTAL of the SO (overWrite purposecode filter)
			qvo.getWheres().put("purposecode", values);

			LOGGER.info("qvo.getWheres().get(purposecode)" + qvo.getWheres().get("purposecode"));

			String sql = ADAMQueryBuilder.buildAgrgegationQuery(qvo);
			LOGGER.info("sql: " + sql);

			Double soValue = adamDao.tableQueryValue(sql);

			LOGGER.info("VALUE: " + soValue);
			if ( soValue != 0 )
				valuesHM.put(so, soValue);
		}


		LOGGER.info("VALUES: " + valuesHM);

		Map<String, Double> sortedJDBCResult = sortByValue(valuesHM);


		LOGGER.info("sortedJDBCResult: " + sortedJDBCResult);


		List<String> sfCodes = new ArrayList<String>();
		for(String code : sortedJDBCResult.keySet()) {
			sfCodes.add(code);
		}





		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();



		if ( !sortedJDBCResult.isEmpty() )
			values.put("values", sortedJDBCResult);

		rvo.setChartValues(values);

		// retrieve coding system for the hover on the chart...
		HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
		List<Code> codes = codecDao.getCodesFromCode(sfCodes, "FAO_SO", "EN");

		//LOGGER.info("--------codes.size(): " + codes.size());
		for(Code c : codes) {
			//LOGGER.info("----> Labels SF: " + c.getCode() + " | " + c.getLabel());
			xAxisFullLabel.put(c.getCode(), c.getCode() + " - " + c.getLabel());
		}
		//LOGGER.info("--------");
		// end


		// switch chart
		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());

		switch (c) {
			case BAR: return createBarChart(qvo, rvo, values, xAxisFullLabel);
			case PIE: return createPIEchart(qvo, rvo, values);
		}

		LOGGER.error("ERROR: should be or BAR or PIE instead of: " + c.toString());
		return rvo;

	}

	private ADAMResultVO createTimeSerieSO(ADAMQueryVO qvo, ADAMResultVO rvo) {

		// if the aggreation is not setted that's the default
		String quantityColumn = "usd_disbursement";
		String aggregationType = "SUM";

		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}

		CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
		Map<String, Map<String, Double>> valuesHM = new LinkedHashMap<String, Map<String, Double>>();

		for(String so : qvo.getPriorities().keySet()) {
			// get dacs
			String dacSQL = ADAMQueryBuilder.getALLDacFromSO(convtable.getTablename(), so);
			List<Object> result = adamDao.tableQueryUniqueValue(dacSQL);
			
			LOGGER.info("%% GET DAC FROM SO QUERY RESULT SIZE (tableQueryUniqueValue): " + result.size());

			List<String> values = new ArrayList<String>();
			//ADD TO FILTERS

//			for(Object[] content : result){
//				try {
//					LOGGER.info("inside the for");
//					values.add(content.toString());
//				}catch (Exception e) {
//					LOGGER.info("cannot parse it..");
//				}
//			}

			for(int i=0; i < result.size(); i++) {
				try {
					//LOGGER.info("inside the for");
					values.add(result.get(i).toString());
				}catch (Exception e) {
					LOGGER.info("cannot parse result ..");
				}
			}


			//LOGGER.info("DACs: " + values);

			// get TOTAL of the SO (overWrite purposecode filter)
			qvo.getWheres().put("purposecode", values);

			//LOGGER.info("qvo.getWheres().get(purposecode)" + qvo.getWheres().get("purposecode"));

			String sql= ADAMQueryBuilder.getTimeSerie(qvo.getTableNames().get(0), qvo.getWheres(), quantityColumn, aggregationType);
			
			Map<String, Double> soValues = adamDao.standardQuery(sql);

			LOGGER.info("%% SO TIMESERIES QUERY RESULT SIZE (standardQuery): " + soValues.size());
			
			if ( !soValues.isEmpty() )
				valuesHM.put(so, soValues);
		}


		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		// clean dates
		for(String serie : valuesHM.keySet()) {
			for(String date : valuesHM.get(serie).keySet()) {
				if ( values.containsKey(serie)) {
					Map<String, Double> currentHM = values.get(serie);
					currentHM.put(date.substring(0, date.indexOf("-")), valuesHM.get(serie).get(date));
					values.put(serie, currentHM);
				}
				else {
					Map<String, Double> currentHM = new HashMap<String, Double>();
					currentHM.put(date.substring(0, date.indexOf("-")), valuesHM.get(serie).get(date));
					values.put(serie, currentHM);
				}
			}
		}

		LOGGER.info("%% SO TIMESERIES SET CHART VALUES: " + values);

//		values.putAll(codeLabelsResult);

		rvo.setChartValues(values);

		

		// retrieve coding system for the hover on the chart...
		List<String> sfCodes = new ArrayList<String>();
		for(String code : qvo.getPriorities().keySet()) {
			sfCodes.add(code);
		}

		HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
		List<Code> codes = codecDao.getCodesFromCode(sfCodes, "FAO_SO", "EN");

		//LOGGER.info("--------codes.size(): " + codes.size());
		for(Code c : codes) {
			//LOGGER.info("----> Labels SF: " + c.getCode() + " | " + c.getLabel());
			xAxisFullLabel.put(c.getCode(), c.getCode() + " - " + c.getLabel());
		}
		//LOGGER.info("--------");
		// end



		return createTimeSerie(qvo, rvo, values, xAxisFullLabel);

	}

	private ADAMResultVO createTimeSerieOR(ADAMQueryVO qvo, ADAMResultVO rvo) {
		String sql = null;
		// create the SQL query
		/*if (!qvo.getReturnFrameworkType().equals(ADAMBoxContent.RETURN_OR.toString()) && !qvo.getSelects().contains("purposecode") ) {
			// convert OR to DAC
			Map<String, HashMap<String, String>> dac_ors = convertORtoDAC(qvo.getPriorities());

			// adding dac derived filters
			Map<String, List<String>> filters = qvo.getWheres();

			List<String> codes = new ArrayList<String>();
			for(String code : dac_ors.keySet()) {
				codes.add(code);
			}
			filters.put("purposecode", codes);
			sql = ADAMQueryBuilder.buildQuery(qvo);
		}
		else {*/
			CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");

			
			//if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode") && !qvo.getSelects().contains("purposename")) {
				
				//dealing with the limit, by filtering out first if the timeseries 
				sql = ADAMQueryBuilder.topItemsORQuery(convtable.getTablename(), qvo.getTableNames().get(0), qvo); // Returns the top partners, recipients, ors ... limited result
				
				List<Object[]> limitedResults = adamDao.tableQuery(sql);
				List<String> topCodes = new ArrayList<String>();
				Map<String, List<String>> filters = new HashMap<String,  List<String>>();
				filters.clear();
				
				LOGGER.info("%% OR TIMESERIES LIMITED QUERY: RESULT SIZE (tableQuery): " + limitedResults.size());
				
				for (int i = 0 ; i < limitedResults.size() ; i++)
					topCodes.add(limitedResults.get(i)[0].toString());
				
				if(!qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposecode") && !qvo.getSelects().contains("purposename")) {	 
					for(String column: qvo.getSelects()) {	
						if(column.contains("name")) {
							column = column.replace("name", "code");
							filters.put(column, topCodes);
						}		
					}
				 } else {
					 filters.put("fao", topCodes);
			    }
				
				/**if(qvo.getSelects()!=null && qvo.getSelects().isEmpty() && !qvo.getSelects().contains("purposename")) {
					if(qvo.getSelects().contains("donorname"))
						filters.put("donorcode", topCodes);
					else if( qvo.getSelects().contains("recipientname"))
						filters.put("recipientcode", topCodes);
				} else
					filters.put("fao", topCodes);**/
				
				qvo.getWheres().putAll(filters);
				
				LOGGER.info("%% OR TIMESERIES LIMITED RESULTS: filters added to WHERES : " + filters);
			//}
			
			sql = ADAMQueryBuilder.buildTimeseriesORquery(convtable.getTablename(), qvo.getTableNames().get(0), qvo); // NO limit in this query 
					
		//}

		Map<String, Map<String, Double>> result = adamDao.orChartQuery(sql); 
		List<String> orCodes = new ArrayList<String>();
	
		for(String or : result.keySet()) {
			orCodes.add(or);
		}

		LOGGER.info("%% OR TIMESERIES QUERY RESULT SIZE (orChartQuery): " + result.size());
	
		
		//sort values
		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();
	   
		//Handling the Limit at the point of the sorted results
		//Integer limit = qvo.getLimit();

		//if (qvo.getSelects().contains("purposecode") && (limit != null ||  limit != 0 )) {
		/**if (limit != null ||  limit != 0 ) {	
			Map<String, Map<String, Double>> limitedResult = limitTimeseriesResultMap(result, limit); 
			
			if(!limitedResult.isEmpty()){
				values.clear();
				values.putAll(limitedResult);
			}
		} else {
			values.clear();
			values.putAll(result);
		}**/
		
		LOGGER.info("%% OR TIMESERIES SET CHART VALUES: " + values);
		
		values.putAll(result);
		rvo.setChartValues(values);
		
		// If OR is being plotted get labels
		HashMap<String, String> xAxisFullLabel = new HashMap<String, String>();
	
		if((qvo.getSelects().isEmpty()) || (qvo.getSelects().contains("purposecode"))) {
			List<Code> codes = codecDao.getCodesFromCode(orCodes, "FAO_SO", "EN");
			for(Code c : codes) {
				xAxisFullLabel.put(c.getCode(), c.getCode() + " - " + c.getLabel());
			}
		} else
			xAxisFullLabel = null;	

		
		return createTimeSerie(qvo, rvo, values, xAxisFullLabel);
		
	}

	private ADAMResultVO createTimeSerieSwitcher(ADAMQueryVO qvo, ADAMResultVO rvo) {
		//LOGGER.info("START %% TIMESERIES .... ");
		if ( qvo.getIsOR() || qvo.getIsSO() ) {
				// this is the switch for the different return types
				if ( qvo.getReturnFrameworkType() != null ) {
					ADAMBoxContent returnType = ADAMBoxContent.valueOf(qvo.getReturnFrameworkType());
					switch (returnType) {
						case RETURN_OR: 
						//	LOGGER.info("%% getReturnFrameworkType(): createTimeSerieOR .... ");
							rvo = createTimeSerieOR(qvo, rvo); break;
						case RETURN_SO: 
							//LOGGER.info("%% createTimeSerieSO .... ");
							rvo = createTimeSerieSO(qvo, rvo); break;
					}
					return rvo;
				}
				else {
					//LOGGER.info("%% createTimeSerieOR .... ");
					return createTimeSerieOR(qvo, rvo);
				}	
			}
			else {
				//LOGGER.info("%% createTimeSerie .... ");
				return createTimeSerie(qvo, rvo);
		
			}		
		}
		
	

	/**
	 *
	 *  The time serie get the top donors/purpose etc in the query and then get the various times serie
	 *
	 * @param qvo
	 * @param rvo
	 * @return
	 */
	private ADAMResultVO createTimeSerie(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("%% --1-- CREATE TIMESERIES for '"+qvo.getTitle()+"'");
		
		String quantityColumn = "usd_disbursement";
		String aggregationType = "SUM";

		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}

		// create the SQL query
		String sql = ADAMQueryBuilder.getTimeSerie(qvo.getTableNames().get(0), qvo.getSelects().get(0), qvo.getSelects().get(1), qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());
		

		Map<String, Map<String, Double>> codeLabelsResult = adamDao.chartStackQuery(sql);
		
		
		//LOGGER.info("%% CREATE TIMESERIES QUERY RESULT SIZE (chartStackQuery): " + codeLabelsResult.size());
		

		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();




		// clean dates
		for(String serie : codeLabelsResult.keySet()) {
			for(String date : codeLabelsResult.get(serie).keySet()) {
				if ( values.containsKey(serie)) {
					Map<String, Double> currentHM = values.get(serie);
					currentHM.put(date.substring(0, date.indexOf("-")), codeLabelsResult.get(serie).get(date));
					values.put(serie, currentHM);
				}
				else {
					Map<String, Double> currentHM = new HashMap<String, Double>();
					currentHM.put(date.substring(0, date.indexOf("-")), codeLabelsResult.get(serie).get(date));
					values.put(serie, currentHM);
				}
			}
		}
	
	//	LOGGER.info("%% TIMESERIES SET CHART VALUES: " + values );
	//	LOGGER.info("%% TIMESERIES VALUES SIZE: " + values.size() );

//		values.putAll(codeLabelsResult);

		rvo.setChartValues(values);

		if(values.size() == 1) {
			boolean hasOneValue = false;
			for(String key: values.keySet()) {
				if(values.get(key).size() == 1) {
					hasOneValue = true;
					break;
				} else  {
					hasOneValue = false;
					break;
				}
			}

			if(hasOneValue) {
				qvo.setOutputType(ADAMBoxContent.BAR.name());
				qvo.setTitle(qvo.getTitle().replace("Time series of ", "Total for "));
				qvo.setLimit(10);
				rvo.setDisclaimer("* Only 1 value was returned, so a Bar chart has been plotted instead of the expected Time series chart.");
				
				if(!qvo.getWhereDates().isEmpty())
					qvo.getWheres().put("year", qvo.getWhereDates().get("year"));
           
				return createChartBarSimple(qvo, rvo);
			} else {
				return createTimeSerie(qvo, rvo, values, null);
			}	
		}	
		else
			return createTimeSerie(qvo, rvo, values, null);

		
	}

	private ADAMResultVO createTimeSerie(ADAMQueryVO qvo, ADAMResultVO rvo, LinkedHashMap<String, Map<String, Double>> values, HashMap<String, String> xAxisFullLabel) {
		//LOGGER.info("SERIES: " + values);
		//LOGGER.info("CHART SIZE SMALL: " + qvo.getSmallWidth() + " | " + qvo.getSmallHeight());
		//LOGGER.info("CHART SIZE BIG: " + qvo.getBigWidth() + " | " + qvo.getBigHeight());

		// outputs
		String bigObjectPath = "";
		String smallObjectPath = "";
		String mediumObjectPath = "";
		String exportObjectPath = "";

		// create JFreeChart settings
//		JFreeChartSettings s = new JFreeChartSettings(qvo.getTitle(), qvo.getxLabel(), qvo.getyLabel());
		JFreeChartSettings s = new JFreeChartSettings("", qvo.getxLabel(), qvo.getyLabel());
		s.setChartType(JFreeChartConstants.valueOf(qvo.getOutputType()));
		s.setxAxisGridBackgroundColor(Color.gray);

		// max label width and legend size
		s.setMaxLabelWidth(25);
		s.setLegendLabelSize(10);



		// create small chart file
		s.setWidth(Integer.valueOf(qvo.getSmallWidth()));
		s.setHeight(Integer.valueOf(qvo.getSmallHeight()));
		//LOGGER.info("FONT SIZE SMALL : " + s.getLegendLabelSize());
		//LOGGER.info("FONT SIZE SMALL : " + s.getTitleSize());

		smallObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		// USE THE MEASUREMENT UNIT qvo.getMeasurementUnit()
		String smallImageMap = JFreeChartConnector.createADAMFile(values, "", dir, smallObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		// create medium chart file
		s.setWidth(MEDIUM_WIDTH);
		s.setHeight(MEDIUM_HEIGHT);
		s.setMaxLabelWidth(-1);
//		s.setTitle(qvo.getTitle());
//		s.setAxislSize(s.getAxislSize() * 2);
		mediumObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String mediumImageMap = JFreeChartConnector.createADAMFile(values, "", dir, mediumObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());

		/** BIG **/
		// max label width and legend size
		s.setMaxLabelWidth(-1);
		s.setLegendLabelSize(11);

		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
//		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() + 3);
		bigObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String bigImageMap = JFreeChartConnector.createADAMFile(values, "", dir, bigObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());

		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() + 3);
		s.setTitleSize(15);
		exportObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String exportImageMap = JFreeChartConnector.createADAMFile(values, "", dir, exportObjectPath, qvo.getMeasurementUnit(), s,null, qvo.getIsSO());


		// fill the result bean
		rvo.setBigImagePath(bigObjectPath);
		rvo.setSmallImagePath(smallObjectPath);
		rvo.setMediumImagePath(mediumObjectPath);
		rvo.setExportImagePath(exportObjectPath);

		// setting imagemap
		rvo.setBigHtmlImageMap(bigImageMap);
		rvo.setSmallHtmlImageMap(smallImageMap);
		rvo.setMediumHtmlImageMap(mediumImageMap);
		rvo.setExportHtmlImageMap(exportImageMap);

		//LOGGER.info("END %% TIMESERIES");
		
		return rvo;
	}

	
	public String createExcelReport(LinkedHashMap<String, ADAMQueryVO> qvos, LinkedHashMap<String, OLAPParametersVo> matrixvos) {
//		String path = "";
		//LOGGER.info("HERE");

		LinkedHashMap<String, ADAMResultVO> resultAskADAM = new LinkedHashMap<String, ADAMResultVO>();
		for(String key : qvos.keySet() ) {
			resultAskADAM.put(key, askADAM(qvos.get(key)));
		}

		// CREATE THE EXCEL
		HSSFWorkbook workbook = new HSSFWorkbook();

		for(String key : resultAskADAM.keySet() ) {
			LOGGER.info("KEY: " + key + " | " + resultAskADAM.get(key).getTableHeaders() + " | " + resultAskADAM.get(key).getGroupTable());

			ADAMBoxContent type = ADAMBoxContent.valueOf(resultAskADAM.get(key).getResourceType());
			System.out.println("TYPE: " + type);
			switch(type) {
				case TABLE:
//					ADAMTableController.addTable(position, color, q, objectWindowId);
				break;
				case CHART:
					tableExcel.addTableSheet(workbook, key, new ArrayList<String>(), getChartTable(resultAskADAM.get(key)), true);
				break;
				case PROJECTS_LIST_CRS:

					tableExcel.addTableSheet(workbook, key, resultAskADAM.get(key).getTableHeaders(), resultAskADAM.get(key).getTableContents(), false);
				break;

			}

		}


		for(String key : matrixvos.keySet()) {
			addMatrixToExcel(workbook, key, matrixvos.get(key));
		}


		return tableExcel.createFileExcelWorkbook(workbook, "EXCEL");
	}

	public List<List<String>> getChartTable(ADAMResultVO vo) {
		List<List<String>> table = new ArrayList<List<String>>();
		LinkedHashMap<String, Map<String, Double>> chartValues = vo.getChartValues();

	//	System.out.println("chartValues" + chartValues);

		// TODO: quick fix for the stack (DO A SERIE switcher)
		System.out.println("vo.getOutputType(): " + vo.getOutputType());
		if ( vo.getOutputType().equals(ADAMBoxContent.BAR_STACK_OR_DONOR.toString()) || vo.getOutputType().equals(ADAMBoxContent.TIMESERIE.toString())) {

			for(String key : chartValues.keySet()) {
				for(String chartKey : chartValues.get(key).keySet()) {
					List<String> row = new ArrayList<String>();
					row.add(key);
					row.add(chartKey);
					row.add(chartValues.get(key).get(chartKey).toString());
					table.add(row);
				}
			}
		}
		// TODO: FIX FOR THE SERIES this is just without the series...
		else {
			for(String key : chartValues.keySet()) {
				for(String chartKey : chartValues.get(key).keySet()) {
					List<String> row = new ArrayList<String>();
					row.add(chartKey);
					row.add(chartValues.get(key).get(chartKey).toString());
					table.add(row);
				}
			}
		}
		return table;
	}

	public void addMatrixToExcel(HSSFWorkbook workbook, String sheetTitle, OLAPParametersVo params) {
		OLAPParameters p = vo2parameters(params);
		p = sortLabels(p);
		p = createLabelCodeMaps(p);
		List<OLAPBean> beans = olapDao.createCube(p);
		OLAPExcel.createExcelSheet(workbook, sheetTitle, p, beans);
	}

	private static OLAPParameters sortLabels(OLAPParameters p) {

		TreeSet<String> sortedXLabels = new TreeSet<String>(p.getXLabels().values());
		TreeSet<String> sortedZLabels = new TreeSet<String>(p.getZLabels().values());
		TreeSet<String> sortedYLabels = new TreeSet<String>();
		TreeSet<String> sortedWLabels = new TreeSet<String>();

		if (p.getX().contains("date") || p.getX().contains("Date")) {
			sortedXLabels = new TreeSet<String>(new FieldParserComparator());
			sortedXLabels.addAll(p.getXLabels().values());
		}
		if (p.getZ().contains("date") || p.getZ().contains("Date")) {
			sortedZLabels = new TreeSet<String>(new FieldParserComparator());
			sortedZLabels.addAll(p.getZLabels().values());
		}

		if (!p.getYLabels().isEmpty()) {
			sortedYLabels = new TreeSet<String>(p.getYLabels().values());
			if (p.getY().contains("date") || p.getY().contains("Date")) {
				sortedYLabels = new TreeSet<String>(new FieldParserComparator());
				sortedYLabels.addAll(p.getYLabels().values());
			}
		}

		if (!p.getWLabels().isEmpty()) {
			sortedWLabels = new TreeSet<String>(p.getWLabels().values());
			if (p.getW().contains("date") || p.getW().contains("Date")) {
				sortedWLabels = new TreeSet<String>(new FieldParserComparator());
				sortedWLabels.addAll(p.getWLabels().values());
			}
		}

		p.setSortedXLabels(sortedXLabels);
		p.setSortedZLabels(sortedZLabels);
		p.setSortedYLabels(sortedYLabels);
		p.setSortedWLabels(sortedWLabels);

		return p;
	}

	private static OLAPParameters createLabelCodeMaps(OLAPParameters p) {

		Map<String, String> xCodes = new HashMap<String, String>();
		Map<String, String> zCodes = new HashMap<String, String>();
		Map<String, String> yCodes = new HashMap<String, String>();
		Map<String, String> wCodes = new HashMap<String, String>();

		for (String xKey : p.getXLabels().keySet())
			xCodes.put(p.getXLabels().get(xKey), xKey);
		p.setxCodes(xCodes);

		for (String zKey : p.getZLabels().keySet())
			zCodes.put(p.getZLabels().get(zKey), zKey);
		p.setzCodes(zCodes);

		if (!p.getYLabels().isEmpty())
			for (String yKey : p.getYLabels().keySet())
				yCodes.put(p.getYLabels().get(yKey), yKey);
		p.setyCodes(yCodes);

		if (!p.getWLabels().isEmpty())
			for (String wKey : p.getWLabels().keySet())
				wCodes.put(p.getWLabels().get(wKey), wKey);
		p.setwCodes(wCodes);

		return p;
	}

	private ADAMResultVO createScatterSwitcher(ADAMQueryVO qvo, ADAMResultVO rvo) {
		// this is the switch for the different return types
		return createScatterChart(qvo, rvo);
	}

	private ADAMResultVO createScatterChart(ADAMQueryVO qvo, ADAMResultVO rvo) {

		// FIRST QUERY (X-Axis)

		LOGGER.info("START");


		Map<String, Map<String, Double>> xValuesMap = getChartvalues(qvo);

		/** TODO: TEST on y-axis existence? **/
		Map<String, Map<String, Double>> yValuesMap  = getChartvalues(qvo.getSecondQuery());

		// TO EXPORT
		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

		/** TODO; use an axis limit for top and bottom **/
		int i=0;
		Map<String, Double> xValues = new LinkedHashMap<String, Double>();
		for(String key : xValuesMap.keySet()) {
			for(String keyValue : xValuesMap.get(key).keySet()) {
				if ( i != 0)
					xValues.put(keyValue,  xValuesMap.get(key).get(keyValue));

				i++;
			}
		}

		int j=0;
		Map<String, Double> yValues = new LinkedHashMap<String, Double>();
		for(String key : yValuesMap.keySet()) {
			for(String keyValue : yValuesMap.get(key).keySet()) {
				if ( j != 0)
					yValues.put(keyValue,  yValuesMap.get(key).get(keyValue));

				j++;
			}
		}

		LOGGER.info("X: " + xValues);
		LOGGER.info("Y: " + yValues);

//		rvo.setChartValues(values);


		return createScatterChart (qvo, rvo, xValues, yValues);
	}

	private Map<String, Map<String, Double>> getChartvalues(ADAMQueryVO qvo) {

		/** TODO: TEST IF IT'S a CUSTOM DATASET **/
		// find SQL tablenames from Resource's codes
		List<String> tablenames = customDatasetDao.findTablenamesFromCodes(qvo.getDatasetCodes());
		if( tablenames.isEmpty() ) {
			qvo.setTableNames(qvo.getDatasetCodes());
		}
		else
			qvo.setTableNames(tablenames);

		LOGGER.info("----------");
		String sql = ADAMQueryBuilder.buildQuery(qvo);
		LOGGER.info("CHART: " + sql);

		Map<String, Map<String, Double>> values = adamDao.chartQuery(sql);

		return values;
	}

	private ADAMResultVO createScatterChart(ADAMQueryVO qvo, ADAMResultVO rvo, Map<String, Double> xValues, Map<String, Double> yValues) {
//		LOGGER.info("SERIES: " + values);
		LOGGER.info("CHART SIZE SMALL: " + qvo.getSmallWidth() + " | " + qvo.getSmallHeight());
		LOGGER.info("CHART SIZE BIG: " + qvo.getBigWidth() + " | " + qvo.getBigHeight());

		// outputs
		String bigObjectPath = "";
		String smallObjectPath = "";
		String mediumObjectPath = "";

		// create JFreeChart settings
//		JFreeChartSettings s = new JFreeChartSettings(qvo.getTitle(), qvo.getxLabel(), qvo.getyLabel());
		JFreeChartSettings s = new JFreeChartSettings("", qvo.getxLabel(), qvo.getyLabel());
		s.setChartType(JFreeChartConstants.valueOf(qvo.getOutputType()));
		s.setxAxisGridBackgroundColor(Color.gray);

		// max label width and legend size
		s.setMaxLabelWidth(25);
		s.setLegendLabelSize(10);



		// create small chart file
		s.setWidth(Integer.valueOf(qvo.getSmallWidth()));
		s.setHeight(Integer.valueOf(qvo.getSmallHeight()));
		LOGGER.info("FONT SIZE SMALL : " + s.getLegendLabelSize());
		LOGGER.info("FONT SIZE SMALL : " + s.getTitleSize());

		smallObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		// USE THE MEASUREMENT UNIT qvo.getMeasurementUnit()
		String smallImageMap = JFreeChartConnector.createADAMFile(xValues, yValues, "", dir, smallObjectPath, qvo.getMeasurementUnit(), s);


		// create medium chart file
		s.setWidth(MEDIUM_WIDTH);
		s.setHeight(MEDIUM_HEIGHT);
		s.setMaxLabelWidth(-1);
//		s.setTitle(qvo.getTitle());
//		s.setAxislSize(s.getAxislSize() * 2);
		mediumObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String mediumImageMap = JFreeChartConnector.createADAMFile(xValues, yValues, "", dir, mediumObjectPath, qvo.getMeasurementUnit(), s);

		/** BIG **/
		// max label width and legend size
		s.setMaxLabelWidth(-1);
		s.setLegendLabelSize(11);

		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
//		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() + 3);
		bigObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String bigImageMap = JFreeChartConnector.createADAMFile(xValues, yValues, "", dir, bigObjectPath, qvo.getMeasurementUnit(), s);

		// fill the result bean
		rvo.setBigImagePath(bigObjectPath);
		rvo.setSmallImagePath(smallObjectPath);
		rvo.setMediumImagePath(mediumObjectPath);

		// setting imagemap
		rvo.setBigHtmlImageMap(bigImageMap);
		rvo.setSmallHtmlImageMap(smallImageMap);
		rvo.setMediumHtmlImageMap(mediumImageMap);

		return rvo;
	}


		private ADAMResultVO getFilteredValues(ADAMQueryVO qvo, ADAMResultVO rvo) {
		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getResourceType());

		switch (c) {
			case GET_IMPLENTING_AGENCIES: rvo = getImplemetingAgencies(qvo, rvo); break;
		}

		return rvo;
	}

	private ADAMResultVO getImplemetingAgencies(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("getImplemetingAgencies");
		/* SELECT channelcode, channelname FROM OFFICIAL_DEVELOPMENT_DATA_FROM_OECD_eb08f6be WHERE ( recipientcode = '71' ) AND ( year = '01-01-2008'  OR year = '01-01-2009' ) GROUP BY channelcode, channelname HAVING SUM(usd_disbursement) != 0 AND channelname <> '' ORDER BY channelname ASC;
		**/


		return rvo;
	}

	private ADAMResultVO getViewMatrix(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("VIEW MATRIX START ---------");

		//Converting the SOs to corresponding purposecodes
		
		if(qvo.getPriorities()!=null && !qvo.getPriorities().isEmpty()) {
		
			CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
			
			List<String> priorities = new ArrayList<String>();
			for(String key : qvo.getPriorities().keySet() ) {
				priorities.add(key);
			}
			String sql = ADAMQueryBuilder.getALLDacFromSOs(convtable.getTablename(), priorities);
			List<Object> result = adamDao.tableQueryUniqueValue(sql);
		
			List<String> values = new ArrayList<String>();
			//ADD TO FILTERS
			for(int i=0; i < result.size(); i++) {
				try {
					//LOGGER.info("inside the for");
					values.add(result.get(i).toString());
				}catch (Exception e) {
					LOGGER.info("cannot parse it..");
				}
			}

			if (!values.isEmpty())
				qvo.getWheres().put("purposecode", values);
		 }


		String quantityColumn = "usd_disbursement";
		String aggregationType = "SUM";

		if (!qvo.getAggregations().isEmpty()) {
			for (String column : qvo.getAggregations().keySet()) {
				quantityColumn = column;
				aggregationType = qvo.getAggregations().get(column);
			}
		}

		/** TODO:
		 *
		 *  the alternative is to have have three different queries
		 *
		 *  1) the one now that is nested and the labels (of the firstColumn)
		 *  2) another one to query the result (the outer query)
		 *  3) the third one is to get the labels of all the secondColumn)
		 *
		 */



		String firstColumnCode = qvo.getSelects().get(0);
		String firstColumnLabel = qvo.getSelects().get(1);
		String secondColumnCode = qvo.getSelects().get(2);
		String secondColumnLabel = qvo.getSelects().get(3);

		//LOGGER.info("qvo.getWheres(): " + qvo.getWheres());
		// create the SQL query
		String sql = "";
		
		if ( qvo.getReturnFrameworkType() != null ) {
			ADAMBoxContent returnType = ADAMBoxContent.valueOf(qvo.getReturnFrameworkType());
			CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
			
			switch (returnType) {
				case RETURN_OR: 
					sql = ADAMQueryBuilder.getORViewMatrix(qvo.getTableNames().get(0), firstColumnCode, firstColumnLabel, secondColumnCode, secondColumnLabel, qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit(), convtable.getTablename(), qvo.getPriorities());	 
					break;
				case RETURN_SO:  {
					boolean addfilterOnSOs = true;
					
					if(qvo.getFilterFrameworkType()!=null && qvo.getFilterFrameworkType().equals(ADAMBoxContent.FILTER_ALL_SO.name())) {
						addfilterOnSOs = false;
					}
					
					sql = ADAMQueryBuilder.getSOViewMatrix(qvo.getTableNames().get(0), firstColumnCode, firstColumnLabel, secondColumnCode, secondColumnLabel, qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit(), convtable.getTablename(), qvo.getPriorities(), addfilterOnSOs);	
					break;
				}	
			}
		} else
			sql = ADAMQueryBuilder.getViewMatrix(qvo.getTableNames().get(0), firstColumnCode, firstColumnLabel, secondColumnCode, secondColumnLabel, qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());	
		

	
	     //sql = ADAMQueryBuilder.getViewMatrix(qvo.getTableNames().get(0), firstColumnCode, firstColumnLabel, secondColumnCode, secondColumnLabel, qvo.getWheres(), quantityColumn, aggregationType, qvo.getLimit());
			
		
		LOGGER.info("SQL VIEW MATRIX: " + sql);

		// query the DB
		List<Object[]> sqlResult = adamDao.tableQuery(sql);


		Map<String, String> firstColumnMap = new LinkedHashMap<String, String>();
		Map<String, String> secondColumnMap = new LinkedHashMap<String, String>();
		Map<String, String> sortedSecondColumnMap = new LinkedHashMap<String, String>();
		Map<String, Map<String, String>> matrixMap = new LinkedHashMap<String, Map<String,String>>() ;


		LOGGER.info("SQL VIEW MATRIX Result Size "+ sqlResult.size());
		if(sqlResult.size() > 0){


			for(int i=0; i < sqlResult.size(); i++) {
				String firstValue = "";
				String secondValue = "";

				try {
					firstValue = sqlResult.get(i)[0].toString();
				}catch (Exception e) {
				}
				try {
					secondValue = sqlResult.get(i)[1].toString();
				}catch (Exception e) {
				}

				firstColumnMap.put(firstValue, firstValue);
				secondColumnMap.put(secondValue, secondValue);

				Map<String, String> map = new LinkedHashMap<String, String>();
				if( matrixMap.containsKey(firstValue)) {
					map = matrixMap.get(firstValue);
					map.put(secondValue, secondValue);
				}
				else {
					map.put(secondValue, secondValue);
				}

				matrixMap.put(firstValue, map);
			}
		}

		/** TODO: Sorting **/


	    //LOGGER.info("firstColumnMap: " + firstColumnMap);
		//LOGGER.info("secondColumnMap: " + secondColumnMap);
		//LOGGER.info("matrixMap: " + matrixMap);

		sortedSecondColumnMap = sortByValues(secondColumnMap);




		ADAMTableUtils.createMatrix(matrixMap, sortedSecondColumnMap, qvo.getMatrixColumnText(), rvo);

		// grouping settings
		rvo.setGroupTable(false);


		LOGGER.info(" VIEW MATRIX END ---------");

		return rvo;
	}
	
	

	/**
	 * TODO: in theory could be simplified by passing the
	 * 		 ADAMQueryVO for the chart informations,
	 * 		 and the LinkedHashMap<String, Map<String, Double>> values
	 * 		 with the values contained in the ADAMResultVO
	 */
	public ADAMResultVO createChartFromRVO(ADAMQueryVO qvo, ADAMResultVO rvo) {

		return createChart(qvo, rvo, rvo.getChartValues(), null);
	}


	private ADAMResultVO getFPMISRequest(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("getFPMISRequest: " + qvo.getText());

		String response;
		try {
			response = ADAMFPMISRequests.getString(qvo.getText());
			LOGGER.info("RESPONSE: " + response);

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return rvo;
	}

	private ADAMResultVO getFPMISCodes(ADAMQueryVO qvo, ADAMResultVO rvo) {
		Map<String, String> recipient = new HashMap<String, String>();



		ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());

		// switch to get the right query
		String query;
		switch (c) {

			case FPMIS_GET_COUNTRY_ID: query = ADAMQueryBuilder.allDonors(); break;

		}

		// query to get the codes


		// TODO: REMOVE
		recipient.put("ID","ID");
		rvo.setGaulMap(recipient);

		return rvo;
	}

	private ADAMResultVO createFPMISChart(ADAMQueryVO qvo, ADAMResultVO rvo) {

		// TODO: procedure to get the XML





		return rvo;
	}

	private ADAMResultVO createChart(ADAMQueryVO qvo, ADAMResultVO rvo, LinkedHashMap<String, Map<String, Double>> values, HashMap<String, String> xAxisFullLabel) {

		// outputs
		String bigObjectPath = "";
		String smallObjectPath = "";
		String mediumObjectPath = "";

		// create JFreeChart settings
//		JFreeChartSettings s = new JFreeChartSettings(qvo.getTitle(), qvo.getxLabel(), qvo.getyLabel());
		JFreeChartSettings s = new JFreeChartSettings("", qvo.getxLabel(), qvo.getyLabel());
		s.setChartType(JFreeChartConstants.valueOf(qvo.getOutputType()));
		s.setxAxisGridBackgroundColor(Color.gray);

		// max label width and legend size
		s.setMaxLabelWidth(25);
		s.setLegendLabelSize(10);



		// create small chart file
		s.setWidth(Integer.valueOf(qvo.getSmallWidth()));
		s.setHeight(Integer.valueOf(qvo.getSmallHeight()));

		smallObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		// USE THE MEASUREMENT UNIT qvo.getMeasurementUnit()
		String smallImageMap = JFreeChartConnector.createADAMFile(values, "", dir, smallObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		// create medium chart file
		s.setWidth(MEDIUM_WIDTH);
		s.setHeight(MEDIUM_HEIGHT);
		s.setMaxLabelWidth(-1);
		mediumObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String mediumImageMap = JFreeChartConnector.createADAMFile(values, "", dir, mediumObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());

		/** BIG **/
		// max label width and legend size
		s.setMaxLabelWidth(-1);
		s.setLegendLabelSize(11);

		// create big chart file
		s.setWidth(Integer.valueOf(qvo.getBigWidth()));
		s.setHeight(Integer.valueOf(qvo.getBigHeight()));
//		s.setTitle(qvo.getTitle());
		s.setAxislSize(s.getAxislSize() + 3);
		bigObjectPath = "ADAM_CHART_" + UUID.randomUUID().toString() + ".png";
		String bigImageMap = JFreeChartConnector.createADAMFile(values, "", dir, bigObjectPath, qvo.getMeasurementUnit(), s, xAxisFullLabel, qvo.getIsSO());


		// fill the result bean
		rvo.setBigImagePath(bigObjectPath);
		rvo.setSmallImagePath(smallObjectPath);
		rvo.setMediumImagePath(mediumObjectPath);

		// setting imagemap
		rvo.setBigHtmlImageMap(bigImageMap);
		rvo.setSmallHtmlImageMap(smallImageMap);
		rvo.setMediumHtmlImageMap(mediumImageMap);

		return rvo;
	}


	public static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}

	public List<CodeVo> convertISO3toDatasetCS(List<String> iso3Codes, String system, String langCode, ADAMSelectedDataset selectedDataset) {
		List<String> crs_codes = new ArrayList<String>();
		List<CodeVo> codesVo = new ArrayList<CodeVo>();
		
		CustomDataset dataset = customDatasetDao.find("ADM0_CONVERSION_TABLE");

		// get all crs codes
		String sql = "";
		switch(selectedDataset){
		case ADAM_CRS: 
				sql = ADAMQueryBuilder.getConversionfromISO3(dataset.getTablename(), iso3Codes, "crs"); 
				break;			
		case ADAM_FPMIS: 
				sql = ADAMQueryBuilder.getConversionfromISO3(dataset.getTablename(), iso3Codes, "fpmis"); 
				break;	
		}
		//String sql = ADAMQueryBuilder.getCRSfromISO3(dataset.getTablename(), iso3Codes);

		List<Object> crsResult = adamDao.tableQueryUniqueValue(sql);

		for(int i=0; i < crsResult.size(); i++) {
			try {
			    crs_codes.add(crsResult.get(i).toString());
			}catch (Exception e) {
				LOGGER.info("cannot parse it..");
			}
		}

		LOGGER.error(crs_codes.size());
		if(crs_codes.size() > 0) {

				List<Code> result = codecDao.getCodesFromCode(crs_codes, system, langCode);

				LOGGER.error("result size = "+result.size());
				for (Code c : result)
					codesVo.add(new CodeVo(c.getCode(), c.getLabel()));
	    }

		return codesVo;
	}


	private ADAMResultVO getComparativeAdvantage(ADAMQueryVO qvo, ADAMResultVO rvo) {
		LOGGER.info("getComparativeAdvantage "+qvo.getIsOR());
		
		String sql = "";
		
		if ( qvo.getIsOR() ) {
			CustomDataset convtable = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
			sql = ADAMQueryBuilder.getFAOComparativeAdvantageORMatrix(qvo.getTableNames().get(0), convtable.getTablename(), qvo.getWheres(), qvo.getSelects(), qvo.getAggregations());
		}
		else
			sql = ADAMQueryBuilder.getFAOComparativeAdvantageMatrix(qvo.getTableNames().get(0), qvo.getWheres(), qvo.getSelects(), qvo.getAggregations());

		
		LOGGER.info("SQL getComparativeAdvantage: " + sql);

		// query the DB
		List<Object[]> sqlResult = adamDao.tableQuery(sql);

		List<BaseModel> models = new ArrayList<BaseModel>();
		List<List<String>> tableContents = new ArrayList<List<String>>();

		LinkedHashMap<String, String> tableHeadersMap = new LinkedHashMap<String, String>();
		tableHeadersMap.put("year", "Year");
		if(qvo.getIsOR())
			tableHeadersMap.put("faoSector", "Organizational Result");
		else
			tableHeadersMap.put("faoSector", "Sector");
		//tableHeadersMap.put("donor", "Resource Partner");
		tableHeadersMap.put("name", "Recipient Country");
		tableHeadersMap.put("sectorRelevance", "% Delivery over Total FAO Delivery");
		tableHeadersMap.put("sectorDelivery", "% FAO Delivery over Total Delivery");
		tableHeadersMap.put("totalDelivery", "% Total FAO Delivery over Total Agriculture");
		tableHeadersMap.put("comparativeAdvantageRatio", "Comparative Advantage Ratio");
		tableHeadersMap.put("comparativeAdvantage", "Does FAO have a Comparative Advantage?");

		rvo.setTableHeadersMap(tableHeadersMap);


		List<String> tableHeaders = new ArrayList<String>();
		tableHeaders.add("Year");
		
		if(qvo.getIsOR())
			tableHeaders.add("Organizational Result");
		else
			tableHeaders.add("Sector");
		//tableHeaders.add("Resource Partner");
		tableHeaders.add("Recipient Country");
		tableHeaders.add("% Delivery over Total FAO Delivery");
		tableHeaders.add("% FAO Delivery over Total Delivery");
		tableHeaders.add("% Total FAO Delivery over Total Agriculture");
		tableHeaders.add("Comparative Advantage Ratio");
		tableHeaders.add("Does FAO have a Comparative Advantage?");

		rvo.setTableHeaders(tableHeaders);

		if(qvo.getAggregations()!=null){
			rvo.getTableHeadersMap().remove("year");
			rvo.getTableHeaders().remove("Year");
		}




		//Map<String, String> firstColumnMap = new LinkedHashMap<String, String>();
		//Map<String, String> secondColumnMap = new LinkedHashMap<String, String>();
		//Map<String, String> sortedSecondColumnMap = new LinkedHashMap<String, String>();
		//Map<String, Map<String, String>> matrixMap = new LinkedHashMap<String, Map<String,String>>() ;


		LOGGER.info("REULT SIZE " + sqlResult.size() + " qvo.getAggregations() "+qvo.getAggregations());

		if(sqlResult.size() > 0){

			for(int i=0; i < sqlResult.size(); i++) {

				List<String> values = new ArrayList<String>();
				String year = "";
				String sector = "";
				String donor = "";
				String recipient = "";

				FAOSectorMatrixModel model = new FAOSectorMatrixModel();
				
				if(qvo.getAggregations()==null){ //Add year first
					try {
						year = sqlResult.get(i)[7].toString();
						model.setYear(year);
						values.add(year);
					}catch (Exception e) {
					}
				}

				try {
					sector = sqlResult.get(i)[0].toString();
					model.setFAOSector(sector);
					model.setFAOSectorCode(sector);
					values.add(sector);
				}catch (Exception e) {
				}
				//try {
				//	donor = sqlResult.get(i)[2].toString();
				//	model.setResourcePartner(donor);
				//	model.setResourcePartnerCode(donor);
				//	values.add(donor);
				//}catch (Exception e) {
				//}
				try {
					recipient = sqlResult.get(i)[1].toString();
					model.setRecipient(recipient);
					model.setRecipientCode(recipient);
					values.add(recipient);
				}catch (Exception e) {
				}
				try {
					model.setSectorRelevance(Double.valueOf(sqlResult.get(i)[2].toString()));
					values.add(sqlResult.get(i)[2].toString());
				}catch (Exception e) {
				}
				try {
					model.setSectorDelivery(Double.valueOf(sqlResult.get(i)[3].toString()));
					values.add(sqlResult.get(i)[3].toString());
				}catch (Exception e) {
				}
				try {
					model.setTotalDelivery(Double.valueOf(sqlResult.get(i)[4].toString()));
					values.add(sqlResult.get(i)[4].toString());
				}catch (Exception e) {
				}
				try {
					model.setComparativeAdvantageRatio(Double.valueOf(sqlResult.get(i)[5].toString()));
					values.add(sqlResult.get(i)[5].toString());
				}catch (Exception e) {
				}
				try {
					model.setHasComparativeAdvantage(sqlResult.get(i)[6].toString());
					values.add(sqlResult.get(i)[6].toString());
				}catch (Exception e) {
				}

               models.add(model);
               tableContents.add(values);
			}
		}




	//	LOGGER.info("secondColumnMap: " + secondColumnMap);
		//LOGGER.info("matrixMap: " + matrixMap);

	//	sortedSecondColumnMap = sortByValues(secondColumnMap);

		rvo.setBaseModels(models);
		rvo.setTableContents(tableContents);

		LOGGER.info("rvo.getBaseModels() size: " + rvo.getBaseModels().size());
		LOGGER.info("rvo.getTableHeaders() size: " + rvo.getTableHeaders().size());
		LOGGER.info("rvo.getTableContents() size: " + rvo.getTableContents().size());

	//	ADAMTableUtils.createMatrix(matrixMap, sortedSecondColumnMap, qvo.getMatrixColumnText(), rvo);

		// grouping settings
		//rvo.setGroupTable(true);

		rvo.setBaseModelGrid(true);

		LOGGER.info("END ---------");

		return rvo;
	}
	

	//Venn Matrix method 
	private void setPriorityMaps(List<Object[]> result, LinkedHashMap<String, String> ors_map, LinkedHashMap<String, List<String>> priorities_mapx, Map<String, Map<String, List<String>>> recipient_priorities_map,
			Map<String, String> sources_map, HashMap<String, ADAMVennRecipientsVO> recipientsVOHM, boolean isNationalPriorities){
		
		for(int i=0; i < result.size(); i++) {
			Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();	
			
			try {				
				String recipient_code = null;
				if (result.get(i)[0] != null ) {
					recipient_code =  result.get(i)[0].toString();
				}
				
				String recipient_label = null;
				if (result.get(i)[1] != null ) {
					recipient_label =  result.get(i)[1].toString();
				}
				
				String or_dac = null;
				if (result.get(i)[2] != null ) {
					or_dac =  result.get(i)[2].toString();
				}
				
				String or_dac_label = null;
				if (result.get(i)[3] != null ) {
					or_dac_label =  result.get(i)[3].toString();
				}
							
				String stated_priority = null;
				if (result.get(i)[4] != null ) {
					stated_priority =  result.get(i)[4].toString();
				}
				
				String source = null;
				if (result.get(i)[5] != null ) {
					source =  result.get(i)[5].toString();
				}
				
				String comment = null;
				if (result.get(i)[6] != null ) {
					comment =  result.get(i)[6].toString();
				}
				
				String source_link = null;
				if (result.get(i)[7] != null ) {
					source_link =  result.get(i)[7].toString();
				}
				
				//store ORs or DACs
				if(or_dac!=null && or_dac_label!=null)
					ors_map.put(or_dac, or_dac_label);
				
				
				//store stated priorities
				
				
				if(recipient_priorities_map.containsKey(recipient_code)) {
					map = recipient_priorities_map.get(recipient_code);	
				} 
				
				if(stated_priority!=null) {

					List<String> stated_priorities_list = new ArrayList<String>();

					if(map.containsKey(or_dac)) {
						//stated_priorities_list = priorities_map.get(or_dac);	
						stated_priorities_list = map.get(or_dac);	
					} 
					
					//add the priority if it is not already in the list, to avoid duplications 
					if(!stated_priorities_list.contains(stated_priority)){
						stated_priorities_list.add(stated_priority);
					}
			
					LOGGER.info("or_dac "+or_dac );
					
					map.put(or_dac, stated_priorities_list);	
					//priorities_map.put(or_dac, stated_priorities_list);	
				}
				else {
					//No stated priorities, set the OR directly as the priority
					if(source!=null || (comment!=null && comment.equalsIgnoreCase("CALCULATED"))){
						List<String> stated_priorities_list = new ArrayList<String>();

						if(map.containsKey(or_dac)) {
							stated_priorities_list = map.get(or_dac);	
						} 
						
						//add the priority if it is not already in the list, to avoid duplications 
						if(!stated_priorities_list.contains(or_dac_label)){
							stated_priorities_list.add(or_dac_label);
						}

						//priorities_map.put(or_dac, stated_priorities_list);		
						map.put(or_dac, stated_priorities_list);	
					} 
				}
				
				//store source	
				if(recipient_code!=null && source!=null) {
					//
					if(isNationalPriorities) {
						HashMap<String, String> docs= getNationalStrategyDocuments(recipient_code);

						if(docs.isEmpty()) {
							if(source_link!=null)
								sources_map.put(recipient_code, "<a href='"+source_link+"' target='_blank'>"+source+"</a>");
							else
								sources_map.put(recipient_code, source);						
						}
						else {
							StringBuilder title = new StringBuilder();
							int counter = 0;

							for(String docPath: docs.keySet()){
								title.append("<a href='"+docs.get(docPath)+"' target='_blank'>"+source+"</a>");

								if (counter < docs.size() - 1){
									title.append("<br/>");
									counter++;
								}
							}

							sources_map.put(recipient_code, title.toString());
						}
					} else {
						if(source_link!=null)
							sources_map.put(recipient_code, "<a href='"+source_link+"' target='_blank'>"+source+"</a>");
						else
							sources_map.put(recipient_code, source);
					}
					//if(source_link!=null)
					//	sources_map.put(recipient_code, "<a href='"+source_link+"' target='_blank'>"+source+"</a>");
					//else
					//	sources_map.put(recipient_code, source);
				}
					
				
				//set the recipient_priorities_map
				//recipient_priorities_map.put(recipient_code, priorities_map);
				LOGGER.info("row "+i+" | recipient_code " + recipient_code + " | size " + map.size());
				
				recipient_priorities_map.put(recipient_code, map);
				
				
				// Create base properties on VO
				if(!recipientsVOHM.containsKey(recipient_code)) {
					ADAMVennRecipientsVO vo = new ADAMVennRecipientsVO();
					vo.setCode(recipient_code);
					vo.setLabel(recipient_label);
					
					if(comment!=null && comment.equalsIgnoreCase("CALCULATED")) {
						vo.setIsCalculated(true);	
					}	
					
					recipientsVOHM.put(recipient_code, vo);
					
				}
			}
			catch (Exception e) {
				LOGGER.info("setPriorityMaps error: " + e.toString());
			}
		}
		for(String code: recipient_priorities_map.keySet()){
			LOGGER.info("code "+code + " | no. of priorities "+recipient_priorities_map.get(code).size());
			LOGGER.info("   --- "+recipient_priorities_map.get(code));
		}
	}
	
	//Venn Matrix method 
	private void getAndSetPriorities(ADAMQueryVO qvo, ADAMResultVO rvo, CustomDataset customdataset, LinkedHashMap<String, String> ors_dacs, 
			LinkedHashMap<String, List<String>> ors_dac_priorities_map, Map<String, Map<String, List<String>>> recipient_priorities, 
			Map<String, String> recipient_sources, HashMap<String, ADAMVennRecipientsVO> recipientsVOHM, boolean isNationalPriorities) {
				
		ADAMSelectedDataset datasetSource = ADAMSelectedDataset.valueOf(qvo.getDatasetSource());
		CustomDataset convtable = customDatasetDao.find("ADAM_RECIPIENT_CONVERSION_TABLE");
		
		String sqlPriorities = "";
		
		switch(datasetSource){
		case ADAM_CRS:
			if(qvo.getCurrentView().equals(ADAMCurrentVIEW.ADAMVIEW)) {
				CustomDataset dac_or_conversion = customDatasetDao.find("FAO_DAC_CONVERTION_TABLE");
				sqlPriorities  = ADAMQueryBuilder.getDACPriorities(customdataset.getTablename(), dac_or_conversion.getTablename(), qvo.getWheres(), qvo.getRecipients());		
			}		
			else if(qvo.getCurrentView().equals(ADAMCurrentVIEW.FAOVIEW)) {
				sqlPriorities  = ADAMQueryBuilder.getPriorities(customdataset.getTablename(), qvo.getWheres(), qvo.getRecipients(), datasetSource.name().toLowerCase(), convtable.getTablename(), "ADAM");
			}
			break;
		case ADAM_FPMIS:
			String convertSql = ADAMQueryBuilder.convertToDAC(qvo.getRecipients(), datasetSource.name().toLowerCase(), convtable.getTablename());
			List<Object[]> convertedRecipients = adamDao.tableQuery(convertSql);
			Map<String, String> map = new LinkedHashMap<String, String>();	

			for(int i=0; i < convertedRecipients.size(); i++) {
				String recipient_code = null;
				if (convertedRecipients.get(i)[0] != null ) {
					recipient_code =  convertedRecipients.get(i)[0].toString();
				}
				String recipient_label = null;
				if (convertedRecipients.get(i)[0] != null ) {
					recipient_label =  convertedRecipients.get(i)[1].toString();

					map.put(recipient_code, recipient_label);
				}
			}


			if(!map.isEmpty()) {
				qvo.setRecipients(map);
				rvo.setConvertedRecipients(map);
			} else
				rvo.setConvertedRecipients(null);
			
			sqlPriorities  = ADAMQueryBuilder.getPriorities(customdataset.getTablename(), qvo.getWheres(), qvo.getRecipients(), datasetSource.name().toLowerCase(), convtable.getTablename(), "FPMIS");
			break;
		}
		
		List<Object[]> prioritiesResult = adamDao.tableQuery(sqlPriorities);

		setPriorityMaps(prioritiesResult, ors_dacs, ors_dac_priorities_map, recipient_priorities, recipient_sources, recipientsVOHM, isNationalPriorities);
	}
	
	//Venn Matrix method 
	private ADAMVennMatrixVO createVennMatrixVO(ADAMQueryVO qvo, Map<String, Map<String, List<String>>> priorities, 
			Map<String, String> sources, HashMap<String, ADAMVennRecipientsVO> recipientsVOHM, 
			  LinkedHashMap<String, String> ors_dacs){
		
		ADAMVennMatrixVO vennMatrix = new ADAMVennMatrixVO();
		
		for(String selectedRecipient: qvo.getRecipients().keySet()){

			if(priorities.containsKey(selectedRecipient)){

				//for(String code: recipient_national_priorities.keySet()){
				ADAMVennRecipientsVO recipientVO = new ADAMVennRecipientsVO();
				Map<String, List<String>> or_dac_priorities = priorities.get(selectedRecipient);

				if (recipientsVOHM.containsKey(selectedRecipient) ) {
					// country already in the hashmap
					recipientVO = recipientsVOHM.get(selectedRecipient);
				}

				//set Source
				if(!sources.isEmpty() && sources.containsKey(selectedRecipient)) {
					recipientVO.setSourceDocument(sources.get(selectedRecipient));
				}

				//set Priorities (OR or DAC)
				for(String or: or_dac_priorities.keySet()){
					recipientVO.getOrs().put(or, or);
					List<String> priorities_list = or_dac_priorities.get(or);

					ADAMVennRecipientsStatedPrioritiesVO stated_priorities = new ADAMVennRecipientsStatedPrioritiesVO();

					if (recipientVO.getStatedPrioritiesVO().containsKey(or)) {
						stated_priorities = recipientVO.getStatedPrioritiesVO().get(or.toString());
					}

					stated_priorities.getStatedPriorities().addAll(priorities_list);

					/**(priorities.size() > 0)
					recipientVO.setIsCalculated(false);
				else
					recipientVO.setIsCalculated(true);**/

					recipientVO.getStatedPrioritiesVO().put(or, stated_priorities);	
				}

				recipientVO.setIsSF(false);
				recipientsVOHM.put(selectedRecipient, recipientVO);
				//}
			}

			else { //No Priorities
				ADAMVennRecipientsVO recipientVO = new ADAMVennRecipientsVO();
				recipientVO.setCode(selectedRecipient);
				recipientVO.setLabel(qvo.getRecipients().get(selectedRecipient));
				recipientVO.setOrs(null);
				recipientVO.setStatedPrioritiesVO(null);	
				recipientVO.setIsCalculated(false);
				recipientVO.setIsSF(true);
				
				recipientsVOHM.put(selectedRecipient, recipientVO);
			}
		} 
		
		for(String key: recipientsVOHM.keySet()) {
			LOGGER.info("--> " + key + " | " + recipientsVOHM.get(key).getLabel() + " | stated priorities: " + recipientsVOHM.get(key).getOrs() + " | " + recipientsVOHM.get(key).getStatedPrioritiesVO());
		}
		
		// setting venn matrix
		vennMatrix.setRecipientsVOHM(recipientsVOHM);
		
		//ORs or DACs
		if(ors_dacs.size() > 0) {
			LinkedHashMap<String, String> sortedOrs = sortByKey(ors_dacs);
			vennMatrix.setOrs(sortedOrs);
		} else
			vennMatrix.setOrs(null);
		
		
		return vennMatrix;
		
	}
	
	
	public HashMap<String, String> getNationalStrategyDocuments(String recipientCode) 
	{
		HashMap<String, String> sourceDocs = new HashMap<String, String>();
		
		String path = Setting.getSystemPath() + File.separator + "data"+ File.separator + "adam-docs" + File.separator + "national_strategies" + File.separator + recipientCode; 
		String shortPath = "adam-docs/national_strategies/" + recipientCode; 

		File folder = new File(path);

		LOGGER.info("getNationalStrategyDocuments:"+ path);
		boolean exists = folder.exists();

		// folder.getAbsolutePath());

		if(exists) {
			File[] listOfFiles = folder.listFiles(); 

			for (int i = 0; i < listOfFiles.length; i++) {

				if (listOfFiles[i].isFile()) {
					sourceDocs.put(listOfFiles[i].getName(), shortPath + "/"+ listOfFiles[i].getName());
				}
			}
			LOGGER.info("sourceDocs = "+ sourceDocs);
		}
		return sourceDocs ;
	}

	public ADAMResultVO getResourcePartnerProfile(ADAMDonorProfileVO vo, String donorcode, String selectedDataset) {
		
		ADAMResultVO rvo = new ADAMResultVO();
		List<List<String>> tableContents = new ArrayList<List<String>>();
		List<String> tableHeaders = new LinkedList<String>();
		   
		CustomDataset dataset = customDatasetDao.find("ADAM_RESOURCEPARTNER_PROFILES");
		vo.setProfileDBTableName(dataset.getTablename());

		String  sql = ADAMQueryBuilder.getDonorProfile(donorcode, dataset.getTablename(), selectedDataset);
		List<Object[]> sqlResult = adamDao.tableQuery(sql);

		if(sqlResult.size() > 0){
			 List<String> urls = new ArrayList<String>();
			
			for(int j=0; j < sqlResult.size(); j++) {

				if(sqlResult.get(j)[0]!=null) {

					Date date = (Date)sqlResult.get(j)[0];
					SimpleDateFormat formatter = new SimpleDateFormat("MMM-yyyy");
					String formattedDate = formatter.format(date);
					vo.setProfileReferenceDate(formattedDate);
				}
				else
					vo.setProfileReferenceDate(null);

				if(sqlResult.get(j)[1]!=null && sqlResult.get(j)[1].toString().length() > 0)
					vo.setPriorityThemes(sqlResult.get(j)[1].toString());
				else
					vo.setPriorityThemes(null);

				if(sqlResult.get(j)[2]!=null && sqlResult.get(j)[2].toString().length() > 0)
					vo.setFavouredFundingArrangements(sqlResult.get(j)[2].toString());
				else
					vo.setFavouredFundingArrangements(null);

				if(sqlResult.get(j)[3]!=null && sqlResult.get(j)[3].toString().length() > 0)
					vo.setFundingBodies(sqlResult.get(j)[3].toString());
				else
					vo.setFundingBodies(null);

				if(sqlResult.get(j)[4]!=null && sqlResult.get(j)[4].toString().length() > 0) {
				       String[] links = sqlResult.get(j)[4].toString().split(";");
					   for (String link : links)
					   {
						   urls.add(link);
					   }
					   vo.setExternalLinks(urls);
				}
				else
					vo.setExternalLinks(null);

				if(sqlResult.get(j)[5]!=null && sqlResult.get(j)[5].toString().length() > 0)
					vo.setResponsibleOfficer(sqlResult.get(j)[5].toString());
				else
					vo.setResponsibleOfficer(null);

				if(sqlResult.get(j)[6]!=null && sqlResult.get(j)[6].toString().length() > 0)
					vo.setResourceMobilizationOfficerEmail(sqlResult.get(j)[6].toString());
				else
					vo.setResourceMobilizationOfficerEmail(null);

				if(sqlResult.get(j)[7]!=null && sqlResult.get(j)[7].toString().length() > 0 )
					vo.setPriorityGeographicalAreas(sqlResult.get(j)[7].toString());
				else
					vo.setPriorityGeographicalAreas(null);

				if(sqlResult.get(j)[8]!=null && sqlResult.get(j)[8].toString().length() > 0)
					vo.setChannelsOfCooperation(sqlResult.get(j)[8].toString());
				else
					vo.setChannelsOfCooperation(null);
				
				if(sqlResult.get(j)[9]!=null && sqlResult.get(j)[9].toString().length() > 0)
					vo.setAnnualFundingCycle(sqlResult.get(j)[9].toString());
				else
					vo.setAnnualFundingCycle(null);
				
				if(sqlResult.get(j)[10]!=null && sqlResult.get(j)[10].toString().length() > 0)
					vo.setApplicationAndNegotiationProcess(sqlResult.get(j)[10].toString());
				else
					vo.setApplicationAndNegotiationProcess(null);
				
				if(sqlResult.get(j)[11]!=null && sqlResult.get(j)[11].toString().length() > 0)
					vo.setBudgetRevisionPolicies(sqlResult.get(j)[11].toString());
				else
					vo.setBudgetRevisionPolicies(null);
				
				if(sqlResult.get(j)[12]!=null && sqlResult.get(j)[12].toString().length() > 0)
					vo.setAccruedInterestPolicies(sqlResult.get(j)[12].toString());
				else
					vo.setAccruedInterestPolicies(null);
				
				
				
				if(sqlResult.get(j)[13]!=null && sqlResult.get(j)[13].toString().trim().length() > 0)
					vo.setSpecialCharacteristics(sqlResult.get(j)[13].toString());
				else
					vo.setSpecialCharacteristics(null);
				
			
				
			}
		}

		
		List<String> contents = new LinkedList<String>();
		if(vo.getPriorityThemes()!=null) {
			contents.add(vo.getPriorityThemes());
			tableHeaders.add(vo.getHeaders().get(0));
		}	
		if(vo.getPriorityGeographicalAreas()!=null){
			contents.add(vo.getPriorityGeographicalAreas());
			tableHeaders.add(vo.getHeaders().get(1));
		}	
		if(vo.getFundingBodies()!=null){
			contents.add(vo.getFundingBodies());
			tableHeaders.add(vo.getHeaders().get(2));
		}
		if(vo.getChannelsOfCooperation()!=null){
			contents.add(vo.getChannelsOfCooperation());
			tableHeaders.add(vo.getHeaders().get(3));
		}
		if(vo.getFavouredFundingArrangements()!=null){
			contents.add(vo.getFavouredFundingArrangements());
			tableHeaders.add(vo.getHeaders().get(4));
		}
		if(vo.getAnnualFundingCycle()!=null){
			contents.add(vo.getAnnualFundingCycle());
			tableHeaders.add(vo.getHeaders().get(5));
		}
		if(vo.getApplicationAndNegotiationProcess()!=null){
			contents.add(vo.getApplicationAndNegotiationProcess());
			tableHeaders.add(vo.getHeaders().get(6));
		}
		if(vo.getBudgetRevisionPolicies()!=null){
			contents.add(vo.getBudgetRevisionPolicies());
			tableHeaders.add(vo.getHeaders().get(7));
		}
		if(vo.getAccruedInterestPolicies()!=null){
			contents.add(vo.getAccruedInterestPolicies());
			tableHeaders.add(vo.getHeaders().get(8));
		}
		if(vo.getSpecialCharacteristics()!=null){
			contents.add(vo.getSpecialCharacteristics());
			tableHeaders.add(vo.getHeaders().get(9));
		}
		
		StringBuilder html = new StringBuilder();
		
		if(vo.getExternalLinks()!=null && !vo.getExternalLinks().isEmpty()) {
			for(String link: vo.getExternalLinks()){
				html.append("<a class='profile-link' href='"+link+"' target='_blank'>"+link+"</a><br/>");
			}
			contents.add(html.toString());
			tableHeaders.add(vo.getHeaders().get(10));
			
		} 
		
		tableContents.add(contents);
		
	     // fill the result
	     rvo.setTableHTML(createDonorProfileHTML(tableHeaders, tableContents, vo));

	     rvo.setTableHeaders(tableHeaders);
		 rvo.setTableContents(tableContents);
		 rvo.setDonorProfileVO(vo);
		
		return rvo;
	}
	
	private String createDonorProfileHTML(List<String> headers, List<List<String>> tableContents, ADAMDonorProfileVO vo) {
		StringBuilder table = new StringBuilder();
		
		if(headers.size()==0){
			table.append("<div id='profile-header-box' style='height:400px;'><div class='profile-content'>"+vo.getResourcePartnerName()+" profile is not available.</div></div>");
		} else {
			table.append("<div id='profile-header-box'> ");
			table.append("<div id='profile-header-left'> "+vo.getResourcePartnerName()+"</div> ");
			table.append("<div id='profile-header-right'>"+vo.getResourceMobilizationOfficerTitle()+": <span class='profile-contact'>"+vo.getResponsibleMobilizationOfficer()+"</span><br/>"+vo.getEmailTitle()+": <a href='mailto:"+vo.getResourceMobilizationOfficerEmail()+"' class='profile-contact-link'>"+vo.getResourceMobilizationOfficerEmail()+"</a></div>");
			table.append("</div>");

			table.append("<div id='profile-box'>");
			
			if(vo.getProfileReferenceDate() == null || vo.getProfileReferenceDate() == "null")
			    table.append("<div id='PROFILE-EXPORT' class='profile-title'>"+vo.getProfileTitle()+" <img src='images/print.png' class='pointer' title='Print "+vo.getResourcePartnerName()+" Profile'></img></div><div id='PROFILE-EXPORT-WAITINGPANEL'></div>");
			else
				table.append("<div id='PROFILE-EXPORT' class='profile-title'>"+vo.getProfileTitle()+" ("+vo.getProfileReferenceDate()+") <img src='images/print.png' class='pointer' title='Print "+vo.getResourcePartnerName()+" Profile'></img></div><div id='PROFILE-EXPORT-WAITINGPANEL'></div>");

				int i = 0;
				for(String header: headers){
					table.append("<div class='profile-heading'>"+header+"</div>");
					table.append("<div class='profile-content'>"+tableContents.get(0).get(i)+"</div>");			
					i++;
				}
		
			table.append("<div class='profile-bottom-border-line'/>");
			table.append("</div>");
		}
		
		LOGGER.info("createDonorProfileHTML completed");
		//System.out.println("table: " + table);
		return table.toString();
	}
	
	
	public void setAdamDao(ADAMDao adamDao) {
		this.adamDao = adamDao;
	}

	public void setCustomDatasetJDBC(CustomDatasetJDBC customDatasetJDBC) {
		this.customDatasetJDBC = customDatasetJDBC;
	}

	public void setCustomDatasetDao(CustomDatasetDao customDatasetDao) {
		this.customDatasetDao = customDatasetDao;
	}

	public void setDir(File dir) {
		this.dir = dir.getPath();
	}

     public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setUrlFinder(UrlFinder urlFinder) {
		this.urlFinder = urlFinder;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setFenixPermissionManager(FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}

	public void setOlapDao(OLAPDao olapDao) {
		this.olapDao = olapDao;
	}

	public void setResourceViewDao(ResourceViewDao resourceViewDao) {
		this.resourceViewDao= resourceViewDao;
	}

	public void setTableExcel(TableExcel tableExcel) {
		this.tableExcel = tableExcel;
	}






}