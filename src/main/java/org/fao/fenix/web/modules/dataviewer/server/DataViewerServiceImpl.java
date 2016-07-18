package org.fao.fenix.web.modules.dataviewer.server;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.dataset.CustomDataset;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CustomDatasetDao;
import org.fao.fenix.core.persistence.faostat.FAOStatDao;
import org.fao.fenix.core.persistence.file.FileFactory;
import org.fao.fenix.core.utils.RSSReader;
import org.fao.fenix.fwds.core.constant.DATASOURCE;
import org.fao.fenix.web.modules.core.client.utils.FormatValues;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATAggregationConstant;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerService;
import org.fao.fenix.web.modules.dataviewer.common.vo.BulkDownloadVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.NoteVO;
import org.fao.fenix.web.modules.dataviewer.server.faostat.codingsystem.FAOSTATGetCodingSystem;
import org.fao.fenix.web.modules.dataviewer.server.faostat.xml.FAOSTATNotesXMLParser;
import org.fao.fenix.web.modules.dataviewer.server.faostat.xml.FAOSTATXMLParser;
import org.fao.fenix.web.modules.dataviewer.server.utils.DataViewerUtils;
import org.fao.fenix.web.modules.dataviewer.server.utils.lang.FAOSTATLanguageConstantsServer;
import org.fao.fenix.web.modules.table.server.TableExcel;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

@SuppressWarnings("serial")
public class DataViewerServiceImpl extends RemoteServiceServlet implements DataViewerService {
	
	private final static Logger LOGGER = Logger.getLogger(DataViewerServiceImpl.class);
	
	private String dir;
	
	private String configFilePath;
	
	private String notesConfigFilePath;
	
	FileFactory fileFactory;
	
	CustomDatasetDao customDatasetDao;
	
	private FAOStatDao faoStatDao;
	
	TableExcel tableExcel;
	
	private FAOSTATNotesXMLParser faostatNotesXMLParser;
	
	private DataServiceUtils dataServiceUtils;
	
	private MapsServiceUtils mapsServiceUtils;

	private final static String BULK_DOWNLOAD_ROOT_URL = "http://faostat.fao.org/Portals/_Faostat/Downloads/zip_files/";

    private String dbName;
    
    private String DATABASE;
    
    // used by the web services
    private DATASOURCE datasource;
    
    long thresholdQuery = 500;
    long thresholdRequest = 1000;
	
	public DataViewerServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public DWFAOSTATResultVO askDataViewerFAOSTAT(DWFAOSTATQueryVO qvo) throws FenixGWTException {
		LOGGER.info("STARTING | " + qvo.getTypeOfOutput() + " | " + qvo.getTitle());
		// outputs
		DWFAOSTATResultVO rvo = new DWFAOSTATResultVO();
		
		long t0 = System.currentTimeMillis();

		// DB faostat connection
		setFAOSTATDBSettings(qvo);
		// set ADAMResultVO
		settingFAOSTATResultVO(rvo, qvo);
		// setting DB connection STRING
		setConnectionString(qvo);

		DataViewerBoxContent output = DataViewerBoxContent.valueOf(qvo.getOutput());
		DataViewerBoxContent typeOfOutput = DataViewerBoxContent.valueOf(qvo.getTypeOfOutput());
		
		switch (output) {
			case CHART: rvo = chartSwitch(qvo, rvo); break;
			case MAP: rvo = mapSwitch(qvo, rvo); break;
			case TABLE: rvo = createTable(qvo, rvo); break;
			case PIVOT_TABLE: rvo = createPivotTable(qvo, rvo); break;		
			case FAOSTAT_METADATA_TABLE: rvo = createFAOSTATMetadataTable(qvo, rvo); break;
			case METADATA_METHODOLOGY: rvo = createMetadataMethodology(qvo, rvo); break;
			case FENIX_METADATA_TABLE: rvo = createFENIXMetadataTable(qvo, rvo); break;
			case DOMAIN_NOTES: rvo = createDomainNotes(qvo, rvo); break;
			case DOMAIN_NOTES_CONTENT: rvo = createDomainNoteContent(qvo, rvo); break;
			case R_GWT: rvo = getRGWTUrl(qvo, rvo); break;
			case GET: rvo = get(qvo, rvo); break;
			default: break;
		}

		// return the result bean
		rvo.setTitle(qvo.getTitle());
		rvo.setOutput(qvo.getOutput());	
		rvo.setTypeOfOutput(qvo.getTypeOfOutput());
		rvo.setMapsVO(qvo.getMapsVO());
		if ( qvo.getMaxDateLimit() != null ) {
			rvo.setMaxDateLimit(qvo.getMaxDateLimit());
			if(qvo.getTimeSpan()!=null)
				rvo.setTimeIntervalSpan(qvo.getTimeSpan());		
		}
		if ( qvo.getPalette() != null ) {
			rvo.setPalette(qvo.getPalette());
		}
		qvo.setRvo(rvo);
//		long endTime = System.currentTimeMillis();			
//		long end = (endTime-t0);			
//		if ( end > thresholdRequest ) {
//			LOGGER.info(end +"ms" + " | " + typeOfOutput.name() + " | " + qvo.getTitle());
//		}
		return rvo;
	}
	
	public Map<String, List<BulkDownloadVO>> findAllBulkDownloads(String lang) throws FenixGWTException {
		Map<String, List<BulkDownloadVO>> map = new HashMap<String, List<BulkDownloadVO>>();
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
		setFAOSTATDBSettings(qvo);
		setConnectionString(qvo);
		String sql = "SELECT * FROM BulkDownloads WHERE LanguageID = '" + lang + "' ORDER BY Source ";
		qvo.setSql(sql);
		
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				BulkDownloadVO vo = new BulkDownloadVO();
				vo.setModuleID(rs.getLong(1));
				vo.setDomainLabel(rs.getString(2));
				vo.setLang(rs.getString(3));
				vo.setCreationDate(rs.getDate(4));
				vo.setFilename(rs.getString(5));
				vo.setFolder(rs.getString(6));
				vo.setContent(rs.getString(7));
				vo.setTabOrder(rs.getInt(8));
				vo.setDomainCode(rs.getString(9));
				/** 
				 * TODO: get directly the zip filename and not the csv
				 */
				vo.setFilename(vo.getFilename().replace(".csv", ".zip"));
				vo.setUrl(BULK_DOWNLOAD_ROOT_URL + vo.getFilename());
				
				if (map.keySet().contains(vo.getDomainLabel())) {
					map.get(vo.getDomainLabel()).add(vo);
				} else {
					List<BulkDownloadVO> vos = new ArrayList<BulkDownloadVO>();
					vos.add(vo);
					map.put(vo.getDomainLabel(), vos);
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (InstantiationException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (SQLException e) {
			throw new FenixGWTException(e.getMessage());
		}
		return map;
	}

	public List<Map<String, String>> read(String lang, int items) throws FenixGWTException {
		try {
			return RSSReader.read(lang, items);
		} catch (ParserConfigurationException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (IOException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (SAXException e) {
			throw new FenixGWTException(e.getMessage());
		} catch (URISyntaxException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public Map<String, List<String>> getLatestDatabaseUpdates(DWFAOSTATQueryVO qvo, String lang, String languageName) throws FenixGWTException {
		qvo.setSql(DWFAOSTATQueryBuilder.getLastUpdatedDomains(lang, languageName));
		// DB faostat connection
		setFAOSTATDBSettings(qvo);
		setConnectionString(qvo);
		return getLastUpdatedDomains(qvo);
	}
	
	public List<DWFAOSTATResultVO> askDataViewerFAOSTAT(List<DWFAOSTATQueryVO> qvos) throws FenixGWTException {
		List<DWFAOSTATResultVO> rvos = new ArrayList<DWFAOSTATResultVO>();
		for(DWFAOSTATQueryVO qvo : qvos ) {
			rvos.add(askDataViewerFAOSTAT(qvo));
		}
		return rvos;
		
	}

	private DWFAOSTATResultVO chartSwitch(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		DataViewerBoxContent outputType = DataViewerBoxContent.valueOf(qvo.getTypeOfOutput());
		switch (outputType) {
			case BAR: rvo = createChart(qvo, rvo); break;
			case BAR_WITH_CATEGORIES: rvo = createCatgeorizedChart(qvo, rvo); break;
			case PIE: rvo = createCatgeorizedChart(qvo, rvo); break;
			case TIMESERIE: rvo = createCatgeorizedChart(qvo, rvo); break;
			case AREA: rvo = createCatgeorizedChart(qvo, rvo); break;
			case STACKED: rvo = createCatgeorizedChart(qvo, rvo); break;		
			case SCATTER: rvo = createCatgeorizedChart(qvo, rvo); break;
		}
		return rvo;
	}
	
	private DWFAOSTATResultVO mapSwitch(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		DataViewerBoxContent outputType = DataViewerBoxContent.valueOf(qvo.getTypeOfOutput());
		switch (outputType) {
			case MAP: rvo = createMap(qvo, rvo, false); break;
			case MAP_GOOGLE: rvo = createMap(qvo, rvo, true); break;
		}
		return rvo;
	}

	private void settingFAOSTATResultVO(DWFAOSTATResultVO rvo, DWFAOSTATQueryVO qvo){
		rvo.setOutput(qvo.getOutput());
		rvo.setTypeOfOutput(qvo.getTypeOfOutput());
		rvo.setTitle(qvo.getTitle());
	}
	
	private DWFAOSTATResultVO createChart(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();
		List<String> singleValue = new ArrayList<String>();
		Map<String, Double> calculatedValues = new HashMap<String, Double>();
		
		if (qvo.getRunMaxDateQuery()) {
			qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, true, false, true));
			singleValue = querySingleResult(qvo);

			for (String val : singleValue) {
				qvo.setRunMaxDateQuery(false);
				qvo.setMaxDateLimit(val);
				rvo.setMaxDateLimit(val);

				if (qvo.getTimeSpan() != null)
					rvo.setTimeIntervalSpan(qvo.getTimeSpan());

				Map<String, String> years = new HashMap<String, String>();
				years.put(val, val);
				qvo.setYears(years);
			}
		}

		if (qvo.getRunCalculationQuery()) {
			if (!calculatedValues.isEmpty()) {
				calculatedValues.clear();
			}

			qvo.setSql(DWFAOSTATQueryBuilder.buildCalculationQuery(qvo));
			singleValue = querySingleResult(qvo);
			for (String val : singleValue) {
				calculatedValues.put(qvo.getCalculationParametersVO().getResultLabel(), Double.valueOf(val));
			}

		}
		// create the SQL query
		qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, false, true, true));
		values = querySimpleChartResult(qvo, rvo);
		
		qvo.setyLabel(rvo.getMeasurementUnit());
		
		
		if (!calculatedValues.isEmpty()) {
			// TODO: other should be multilanguage
			values.put("Other", calculatedValues);
		}

		rvo.setChartValues(values);
		
		return rvo;
	}
	
	private DWFAOSTATResultVO createCatgeorizedChart(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		
		long t0 = System.currentTimeMillis();	
		long t1;	
		long t2;	
		
		// COMPARE VIEW
		if ( qvo.getIsCompare()) {
			LinkedHashMap<String, String> mus = new LinkedHashMap<String, String>();
			for(DWFAOSTATQueryVO q : qvo.getJoinQueryVO().getQvos()) {
				DWFAOSTATResultVO r = new DWFAOSTATResultVO();

				// settting up the vo
				// DB faostat connection
				setFAOSTATDBSettings(q);
				// setting DB connection STRING
				setConnectionString(q);
				
				// make a single call for each call and join it
				q.setSql(DWFAOSTATQueryBuilder.buildQuery(q, false, false, false));					
				queryChart(q, r, mus);
				// add the results rvo	
				for(String key : r.getChartValues().keySet()) {
					rvo.getChartValues().put(key, r.getChartValues().get(key));
				}
				for(String key : r.getSerieMeasurementUnits().keySet()) {
					rvo.getSerieMeasurementUnits().put(key, r.getSerieMeasurementUnits().get(key));
				}
			}	
			rvo.setMeasurementUnits(mus);
		    t1 = System.currentTimeMillis();	
			t2 = System.currentTimeMillis();	
		}
		
		// JOIN QUERY
		else if( qvo.getRunJoinQuery()) { 
		    if(qvo.getRunMaxDateQuery()) {
				// // getting as default the first QVO....
		    	/// TODO: remove and set as default...
		    	qvo.getJoinQueryVO().getQvos().get(0).setRunMaxDateQuery(true);
		    	qvo.getJoinQueryVO().getQvos().get(0).setTimeSpan(qvo.getTimeSpan());
		    	qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo.getJoinQueryVO().getQvos().get(0), true, false, true));
				List<String> singleValue =	querySingleResult(qvo);
				
				for(String val: singleValue){
//					LOGGER.info("singleValue: " + val + " | " + qvo.getTitle());
					qvo.setRunMaxDateQuery(false);
					
					for(DWFAOSTATQueryVO q : qvo.getJoinQueryVO().getQvos()) {
						rvo.setMaxDateLimit(val);	
						if(qvo.getTimeSpan()!=null)
							rvo.setTimeIntervalSpan(qvo.getTimeSpan());	
						
						Map<String, String> years = new HashMap<String, String>();
						years.put(val, val);
						
						q.setTimeSpan(qvo.getTimeSpan());
						q.setYears(years);
						q.setMaxDateLimit(val);	
					}
				}
			}    
			// setting the query 
			qvo.setSql(DWFAOSTATQueryBuilder.sqlJoinQuery(qvo));
			queryJoinChart(qvo, rvo);
		}
		
		// TODO: make it generic somewhere
		else if( qvo.getRunCalculationQuery() && !qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.AVERAGE_OVER_TIME)) { 		
			if(qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.GROWTH_RATE) || 
			   qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.ANNUAL_GROWTH_RATE) ||
			   qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.ANNUAL_GROWTH_RATE_POPULATION) ){
				
				buildGrowthRate(qvo, rvo);
			} else if (qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.ANNUAL_GROWTH_RATE_LEAST_SQUARE)) {
				buildGrowthRateLeastSquare(qvo, rvo);
			}
		}
		// GENERAL QUERY
		else {
			    if(qvo.getRunMaxDateQuery()) {
			    	List<String> singleValue = new ArrayList<String>();
					// create the SQL query ... for a BAR chart
					qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, true, false, true));
					singleValue = querySingleResult(qvo);
					
					for(String val: singleValue){
//						LOGGER.info("singleValue: " + val);
						qvo.setRunMaxDateQuery(false);
						qvo.setMaxDateLimit(val);	
						rvo.setMaxDateLimit(val);	
						
						if(qvo.getTimeSpan()!=null)
							rvo.setTimeIntervalSpan(qvo.getTimeSpan());	
						
						Map<String, String> years = new HashMap<String, String>();
						years.put(val, val);
						qvo.setYears(years);
					}	
				}
			    
			    t1 = System.currentTimeMillis();	
				qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, false, true, true));
				t2 = System.currentTimeMillis();	
				queryChart(qvo, rvo, null);
		}	
		
//		long endTime = System.currentTimeMillis();	
//		long end = (endTime-t0);	
//		long buildQueryStart = (endTime-t0);	
//		long buildQueryEnd = (endTime-t0);	
//		
//		LOGGER.info(end + "ms " + " | " + buildQueryStart + "ms " + " | " + buildQueryEnd + "ms " +
//                " | " + qvo.getTypeOfOutput() + " | " + qvo.getTitle());
		
		return rvo;
	}
		

	private DWFAOSTATResultVO buildGrowthRate(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
//		LOGGER.info("buildGrowthRate: ");
		
		/** TODO: RUN MAX QUERY **/ 
		if(qvo.getRunMaxDateQuery()) {
			// present value
			qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, true, false, true));
			List<String> presValue = querySingleResult(qvo);
			qvo.setRunMaxDateQuery(false);
			qvo.getCalculationParametersVO().setTimeperiod_present(presValue);
			Integer pastValueInt = Integer.valueOf(presValue.get(0)) - qvo.getTimeSpan();
			
			// past value
			List<String>  pastValue = new ArrayList<String>();	
			pastValue.add(pastValueInt.toString());
			qvo.getCalculationParametersVO().setTimeperiod_past(pastValue);
		}
		else if ( !qvo.getYears().isEmpty() ) {
			
			/** TODO: Sort years
			 *  take the first and the last year
			 **/
			 LinkedHashMap<String, String> sort = DataViewerUtils.sortByValuesDESC(qvo.getYears());
			 
			 int i = 0;
			 List<String> presValue = new ArrayList<String>();
			 List<String>  pastValue = new ArrayList<String>();	
			 for(String key : sort.keySet()) {
				 if ( i == 0) {
					 presValue.add(key);
				 }
				 if ( i == sort.size() - 1 ) {
					 pastValue.add(key);
				 }
				 i++;
			 }

			qvo.getCalculationParametersVO().setTimeperiod_present(presValue);
			qvo.getCalculationParametersVO().setTimeperiod_past(pastValue);
		}

		List<String> timeperiod_present = qvo.getCalculationParametersVO().getTimeperiod_present();
//		LOGGER.info("timeperiod_present: " + timeperiod_present);
		
		qvo.getYears().clear();
		for(String year : timeperiod_present ) {
			qvo.getYears().put(year, year);
		}
		
		qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, false, true, true));

		LinkedHashMap<String, Map<String, Double>> presentValues = queryChartTimeSerieResult(qvo);
//		LOGGER.info("presentValues: " + presentValues);
		
		/** TODO: get the second results **/
		List<String> timeperiod_past = qvo.getCalculationParametersVO().getTimeperiod_past();
//		LOGGER.info("timeperiod_past: " + timeperiod_past);
		
		qvo.getYears().clear();
		for(String year : timeperiod_past ) {
			qvo.getYears().put(year, year);
		}
		
		qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, false, true, true));

		LinkedHashMap<String, Map<String, Double>> pastValues = queryChartTimeSerieResult(qvo);
//		LOGGER.info("pastValues: " + pastValues);
		
		// TODO: modify for the title...
		qvo.getYears().clear();
		for(String year : timeperiod_present ) {
			qvo.getYears().put(year, year);
		}
		for(String year : timeperiod_past ) {
			qvo.getYears().put(year, year);
		}
		buildTimerangeTitle(qvo, rvo);
		
//		LOGGER.info("TIMERANGE: " + qvo.getYears());
//		LOGGER.info("getMaxDateLimit: " + rvo.getMaxDateLimit());
//		LOGGER.info("getTimeIntervalSpan: " + rvo.getTimeIntervalSpan());

		// FORMULA APPLIED
		if(qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.GROWTH_RATE)) {
			rvo.setChartValues(performGrowthRate(presentValues, pastValues));
		}
		else if (qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.ANNUAL_GROWTH_RATE)) {
			rvo.setChartValues(performAnnualGrowthRate(presentValues, pastValues, rvo.getTimeIntervalSpan()));
		}
		else if (qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.ANNUAL_GROWTH_RATE_POPULATION)) {
			rvo.setChartValues(performAnnualGrowthRatePopulation(presentValues, pastValues, rvo.getTimeIntervalSpan()));

		}
		
		// TODO: Chech the label dynamically...multilanguage

		String growthRate = FAOSTATLanguageConstantsServer.print("GROWTH_RATE", qvo.getLanguage());
//		LOGGER.info("GROWTH RATE: " + growthRate + " | " + qvo.getLanguage());
		rvo.getMeasurementUnits().put("% "+ growthRate, "0");
		rvo.setMeasurementUnit("% "+ growthRate);

		// TODO: ADD on rvo the timerange for the title...and create at client side
//		buildGrowthRateTitle(qvo, rvo);

		return rvo;
	}
	
	
	private DWFAOSTATResultVO buildGrowthRateLeastSquare(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		if(qvo.getRunMaxDateQuery()) {
			qvo.getYears().clear();
			// present value
			qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, true, false, true));
			List<String> presValue = querySingleResult(qvo);
			qvo.setRunMaxDateQuery(false);
			qvo.getCalculationParametersVO().setTimeperiod_present(presValue);
			Integer pastValueInt = Integer.valueOf(presValue.get(0)) - qvo.getTimeSpan();
			
			for(int i = pastValueInt; i <= Integer.valueOf(presValue.get(0)); i++) {
				qvo.getYears().put(String.valueOf(i), String.valueOf(i));
			}
			
		}
		else if ( !qvo.getYears().isEmpty() ) {
			
			/** TODO: Sort years
			 *  take the first and the last year
			 **/
			 LinkedHashMap<String, String> sort = DataViewerUtils.sortByValuesDESC(qvo.getYears());
			 
			 int i = 0;
			 String presValue = null;
			 String pastValue = null;
			 for(String key : sort.keySet()) {
				 if ( i == 0) {
					 presValue = key;
				 }
				 if ( i == sort.size() - 1 ) {
					 pastValue = key;
				 }
				 i++;
			 }
			// create years 
			for(int j = Integer.valueOf(pastValue); j <= Integer.valueOf(presValue); j++) {
				qvo.getYears().put(String.valueOf(j), String.valueOf(j));
			}
		}

		
		qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, false, true, true));
		
//		LOGGER.info("SQL: " + qvo.getSql());
		
		LinkedHashMap<String, Map<String, List<Double>>> valuesLS = queryAnnualGrowthRateLeastSquare(qvo);
		
		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();
//		LOGGER.info("valuesLS: " + valuesLS );
		
		for(String serie : valuesLS.keySet()) { 
			Map<String, Double> value = new LinkedHashMap<String, Double>(); 
			for(String xValue : valuesLS.get(serie).keySet()) {
				Double statisticalValue = dataServiceUtils.annualGrowtRateLeastSquare(valuesLS.get(serie).get(xValue));

				value.put(xValue, statisticalValue);

			}
			values.put(serie, value);
		}
//		LOGGER.info("values: " + values );
		
		rvo.setChartValues(values);
		
		buildTimerangeTitle(qvo, rvo);
		
		// chart strings
		String growthRate = FAOSTATLanguageConstantsServer.print("ANNUAL_GROWTH_RATE_LEAST_SQUARE", qvo.getLanguage());
		rvo.getMeasurementUnits().put("% "+ growthRate, "0");
		rvo.setMeasurementUnit("% "+ growthRate);
		
		
		return rvo;
	}
	
	private DWFAOSTATResultVO buildGrowthRateTable(DWFAOSTATResultVO rvo, Boolean addSerie) {
//		LOGGER.info("buildGrowthRate: ");
		LinkedHashMap<String, Map<String, Double>> values = rvo.getChartValues();

	    String styleName = "calculated_table";
	    String styleNameTitle = "calculated_table_title";
	
		DataViewerUtils.getHtmlTableGrowthRate(rvo, values, styleName, styleNameTitle);
		
		return rvo;
	}

	private void buildTimerangeTitle(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		String title = qvo.getTitle();
	
		if ( !qvo.getYears().isEmpty() ) {
			
			StringBuffer dates = new StringBuffer();

			 LinkedHashMap<String, String> sort = DataViewerUtils.sortByValuesDESC(qvo.getYears());
			 
			 int i = 0;
			 String presValue = null; 
			 String  pastValue = null;
			 for(String key : sort.keySet()) {
				 if ( i == (sort.size() - 1) ) {
					 pastValue = key;
				 }
				  if ( i == 0) {
						 presValue = key;
				 }
				 i++;
			 }
			
			 rvo.setMaxDateLimit(presValue);
			
			if ( !presValue.equals(pastValue)) {
				rvo.setTimeIntervalSpan(Integer.valueOf(presValue) - Integer.valueOf(pastValue));
			}
			else {
//				rvo.setTimeIntervalSpan(Integer.valueOf(presValue) - Integer.valueOf(pastValue));
			}
			
//			 LOGGER.info("getMaxDateLimit: " + rvo.getMaxDateLimit());
//			 LOGGER.info("getTimeIntervalSpan: " + rvo.getTimeIntervalSpan());


			
//			 LOGGER.info("pres: " + presValue);
//			 LOGGER.info("past:" +pastValue);
//
//
//			dates.append("( ");
//			dates.append(pastValue);
//			
//			if ( !presValue.equals(pastValue)) {
//				dates.append(" - " + presValue);
//			}
//			
//			dates.append(" )");
//
//			title = title + " " + dates.toString();
//			qvo.setTitle(title);
//			rvo.setTitle(title);

		}

//		StringBuffer dates = new StringBuffer();
//		dates.append("(");
//		
//		// past values
//		if ( qvo.getCalculationParametersVO().getTimeperiod_past().size() > 1 ) {
//			dates.append("[ ");
//		}
//		for(String value : qvo.getCalculationParametersVO().getTimeperiod_past() ) {
//			dates.append(value);	
//			dates.append(" ");
//		}
//		if ( qvo.getCalculationParametersVO().getTimeperiod_past().size() > 1 ) {
//			dates.append(" ]");
//		}
//		
//		dates.append(" - ");
//		
//		// present values
//		if ( qvo.getCalculationParametersVO().getTimeperiod_present().size() > 1 ) {
//			dates.append("[ ");
//		}
//		for(String value : qvo.getCalculationParametersVO().getTimeperiod_present() ) {
//			dates.append(value);	
//			dates.append(" ");
//		}
//		if ( qvo.getCalculationParametersVO().getTimeperiod_present().size() > 1 ) {
//			dates.append(" ]");
//		}
//
//		dates.append(")");
//		
//		qvo.setTitle(title = title + " " + dates.toString());
	}
	
	private LinkedHashMap<String, Map<String, Double>> performGrowthRate(LinkedHashMap<String, Map<String, Double>> presentValues, LinkedHashMap<String, Map<String, Double>> pastValues) {
		 LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();
		 
		 
		 
		 for( String serie : presentValues.keySet() ) {
			 if ( pastValues.containsKey(serie)) {
				 
				 for( String xAxis : presentValues.get(serie).keySet() ) {
					 if ( pastValues.get(serie).containsKey(xAxis) ) {
						 Double presentValue = presentValues.get(serie).get(xAxis);
						 Double pastValue = pastValues.get(serie).get(xAxis);
//		
//						 LOGGER.info("serie: " + serie);
//						 LOGGER.info("xAxis: " + xAxis);
//						 LOGGER.info("presentValue: " + presentValue);
//						 LOGGER.info("pastValue: " + pastValue);
						 
						 Double growthRate = (presentValue - pastValue);
						 
//						 LOGGER.info("patial growthRate: " + growthRate);
						 
						 growthRate = (growthRate / pastValue) * 100;
						 
//						 LOGGER.info("growthRate: " + growthRate);
		
						 Map<String, Double> value = new LinkedHashMap<String, Double>();

						 if ( values.containsKey(serie)) {
							 value = values.get(serie);
						 }
						 
						 // TODO: DO a better function for rounding the values
						 value.put(xAxis, Double.valueOf(FormatValues.formatValue(growthRate, 2)));
						 values.put(serie, value);

						 
					 }
				 }
			 }
		 }
		 
//		 LOGGER.info("values: " + values);
		 return values;
	}
	
	/**
	 * 
	 * Formula applied:
	 * 
	 * r = {[ ( Pn / P1) * (1 / n)] -1 } * 100;  
	 * 
	 * @param presentValues
	 * @param pastValues
	 * @param yearsInterval
	 * @return
	 */
	private LinkedHashMap<String, Map<String, Double>> performAnnualGrowthRate(LinkedHashMap<String, Map<String, Double>> presentValues, LinkedHashMap<String, Map<String, Double>> pastValues, Integer yearsInterval) {
		 LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();
		 
		 
		 
		 for( String serie : presentValues.keySet() ) {
			 if ( pastValues.containsKey(serie)) {
				 
				 for( String xAxis : presentValues.get(serie).keySet() ) {
					 if ( pastValues.get(serie).containsKey(xAxis) ) {
						 Double presentValue = presentValues.get(serie).get(xAxis);
						 Double pastValue = pastValues.get(serie).get(xAxis);
		
//						 LOGGER.info("serie: " + serie);
//						 LOGGER.info("xAxis: " + xAxis);
//						 LOGGER.info("presentValue: " + presentValue);
//						 LOGGER.info("pastValue: " + pastValue);

						 
						 Double v = (presentValue / pastValue); 
						 Double exp = 1.0 / yearsInterval ;
						 
//						 LOGGER.info("division: " + v);
//						 LOGGER.info("exp: " + exp);
//						 LOGGER.info("yearsInterval: " + yearsInterval);
//					
//						 LOGGER.info("1 / yearsInterval: " + 1 / yearsInterval);
						 
						 Double growthRate = Math.pow(v, exp);	
						 
//						 LOGGER.info("patial growthRate: " + growthRate);
						 
						 growthRate = (growthRate - 1) * 100;
						 
//						 LOGGER.info("growthRate: " + growthRate);
		
						 Map<String, Double> value = new LinkedHashMap<String, Double>();

						 if ( values.containsKey(serie)) {
							 value = values.get(serie);
						 }
						 
						 // TODO: DO a better function for rounding the values
						 value.put(xAxis, Double.valueOf(FormatValues.formatValue(growthRate, 2)));
						 values.put(serie, value);

						 
					 }
				 }
			 }
		 }
		 
//		 LOGGER.info("values: " + values);
		 return values;
	}
		
	
	private LinkedHashMap<String, Map<String, Double>> performAnnualGrowthRatePopulation(LinkedHashMap<String, Map<String, Double>> presentValues, LinkedHashMap<String, Map<String, Double>> pastValues, Integer yearsInterval) {
		 LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();
		 
		 
		 
		 for( String serie : presentValues.keySet() ) {
			 if ( pastValues.containsKey(serie)) {
				 
				 for( String xAxis : presentValues.get(serie).keySet() ) {
					 if ( pastValues.get(serie).containsKey(xAxis) ) {
						 Double presentValue = presentValues.get(serie).get(xAxis);
						 Double pastValue = pastValues.get(serie).get(xAxis);
						 
//						 LOGGER.info("serie: " + serie);
//						 LOGGER.info("xAxis: " + xAxis);
//						 LOGGER.info("presentValue: " + presentValue);
//						 LOGGER.info("pastValue: " + pastValue);
						 
						 Double div = presentValue / pastValue;

						 Double v = Math.log(div); 

//						 LOGGER.info("div: " + div);
//						 LOGGER.info("v: " + v);
//						 LOGGER.info("yearsInterval: " + yearsInterval);
//						 LOGGER.info("1 / yearsInterval: " + 1 / yearsInterval);
						 
						 Double growthRate = (v / yearsInterval);
						 
//						 LOGGER.info("patial growthRate: " + growthRate);
						 
						 growthRate = growthRate * 100;
						 
//						 LOGGER.info("growthRate: " + growthRate);
		
						 Map<String, Double> value = new LinkedHashMap<String, Double>();

						 if ( values.containsKey(serie)) {
							 value = values.get(serie);
						 }
						 
						 // TODO: DO a better function for rounding the values
						 value.put(xAxis, Double.valueOf(FormatValues.formatValue(growthRate, 2)));
						 values.put(serie, value);

						 
					 }
				 }
			 }
		 }
		 
//		 LOGGER.info("values: " + values);
		 return values;
	}
	
	

	
	private Map<String, Map<String, Double>> queryMap(DWFAOSTATQueryVO qvo,  DWFAOSTATResultVO rvo, Map<String, Double> faostatValues) throws FenixException {
		Map<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();
//		Map<String, Double> value = new LinkedHashMap<String, Double>();
		long t0 = System.currentTimeMillis();
		long t1;
		long t2;
		long t3;
		long t4;
		
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			
			t1 = System.currentTimeMillis();
			
			stmt.executeQuery(qvo.getSql());
			
			t2 = System.currentTimeMillis();	
			
			ResultSet rs = stmt.getResultSet();
			
			t3 = System.currentTimeMillis();	
		
			
			while (rs.next()) {
				// code value
				faostatValues.put(rs.getString(1).toString(), rs.getDouble(3));
				
				// code, label, value
				Map<String, Double> value =new HashMap<String, Double>();
				value.put(rs.getString(2).toString(), rs.getDouble(3));
				values.put(rs.getString(1).toString(), value);
				rvo.setMeasurementUnit(rs.getString(4));
			}
			
			t4 = System.currentTimeMillis();			
			
			rs.close();
			stmt.close();
			c.close();
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		return values;
	}
	
	private void queryJoinChart(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) throws FenixException {
		
		LinkedHashMap<String, Map<String, Double>> map = new LinkedHashMap<String, Map<String, Double>>();
		
		LinkedHashMap<String, String> serieMeasurementUnits = new LinkedHashMap<String, String>();
		
		LinkedHashMap<String, String> measurementUnits = new LinkedHashMap<String, String>();

		long t0 = System.currentTimeMillis();
		long t1;
		long t2;
		
		try {
			 
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			
			t1 = System.currentTimeMillis();
			
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			t2 = System.currentTimeMillis();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			 int columnCount = rsmd.getColumnCount();
			 

			 Integer muIndex = 0;
			 while (rs.next()) {
				  
				  // X-Value
				  String xValue = rs.getString(1);
				  
				  for (int i = 2; i < columnCount; i=i+3) { 
					  // Serie
					  Map<String, Double> value = new LinkedHashMap<String, Double>();
					  String serie = rs.getString(i);
					  String mu = rs.getString(i + 1 );
					  String v = rs.getString(i + 2 );
					  
					  value.put(xValue, Double.valueOf(v));
					  
//					  LOGGER.info( "SERIE: " +serie);
//					  LOGGER.info( "xValue: " + xValue);
//					  LOGGER.info( "MU: " + mu);
//					  LOGGER.info( "VALUE: " + v);
//					  LOGGER.info("----");
					  
					  if ( map.containsKey(serie)) {
							Map<String, Double> currentHM = map.get(serie);
							currentHM.put(xValue, Double.valueOf(v));
					  }
				 	else {
						map.put(serie, value);
					}
					  
						serieMeasurementUnits.put(serie, mu);
						if ( !measurementUnits.containsKey(mu)) {
							measurementUnits.put(mu, muIndex.toString());
							muIndex++;
						}
				
				  }
			 }
			
			rvo.setChartValues(map);
			
			rvo.setSerieMeasurementUnits(serieMeasurementUnits);
			rvo.setMeasurementUnits(measurementUnits);
			
			rs.close();
			stmt.close();
			c.close();
			
			long endTime = System.currentTimeMillis();	
			long connection = (t1-t0);
			long query = (t2-t0);
			long end = (endTime-t0);	
			
//			LOGGER.info(end + "ms " + " | " + connection + "ms " + " | " + query + "ms " +
//	                " | " + qvo.getTypeOfOutput() + " | " + qvo.getTitle());
			
//			long endTime = System.currentTimeMillis();			
//			long end = (endTime-t0);			
//			if ( end > thresholdQuery ) {
//				LOGGER.info(end + "ms " + " | TYPE OF OUTPUT: " + qvo.getTypeOfOutput() + " | " + qvo.getTitle());
////				LOGGER.info(end + "ms " + " | OUTPUT: "+ qvo.getOutput() + " | TYPE OF OUTPUT: " + qvo.getTypeOfOutput() + " |" + qvo.getSql());
//			}
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 


	}
	
	private Double applyConditionOnValue(DWFAOSTATQueryVO qvo, double d) {
		if (qvo.getValueCondition() != null) {
			double t = qvo.getValueTreshold().doubleValue();
			switch (qvo.getValueCondition()) {
			case GREATERTHAN:
				if (d > t)
					return d;
				break;
				case GREATERTHANOREQUALSTO:
					if (d >= t)
						return d;
				break;
				case EQUALSTO:
					if (d == t)
						return d;
				break;
				case LESSTHAN:
					if (d < t)
						return d;
				break;
				case LESSTHANOREQUALSTO:
					if (d <= t)
						return d;
				break;
			}
			return null;
		} else {
			return d;
		}
	}
	
	private void queryChart(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, LinkedHashMap<String, String> mus) throws FenixException {
		LinkedHashMap<String, Map<String, Double>> map = new LinkedHashMap<String, Map<String, Double>>();
		LinkedHashMap<String, String> serieMeasurementUnits = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> measurementUnits = new LinkedHashMap<String, String>();

		if ( mus != null )
			measurementUnits.putAll(mus);
		
		long t0 = System.currentTimeMillis();
		long t1;
		long t2;
		
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			
			t1 = System.currentTimeMillis();
			
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			t2 = System.currentTimeMillis();
		
			Integer muIndex = 0;
			if ( !measurementUnits.isEmpty()){
				muIndex = measurementUnits.size();
			}
			while (rs.next()) {
				try {
					Map<String, Double> value = new LinkedHashMap<String, Double>();
					
					Double number = applyConditionOnValue(qvo, Double.valueOf(rs.getString(3)));
					if (number != null)
						value.put(rs.getString(2), number);
					
					if ( map.containsKey(rs.getString(1))) {
						Map<String, Double> currentHM = map.get(rs.getString(1));
						currentHM.put(rs.getString(2), number);
						map.put(rs.getString(1), currentHM);
					}
					else {
						map.put(rs.getString(1), value);
					}
					try {
						serieMeasurementUnits.put(rs.getString(1), rs.getString(4));
						if ( !measurementUnits.containsKey(rs.getString(4))) {
							measurementUnits.put(rs.getString(4), muIndex.toString());
							muIndex++;
						}
					}catch (Exception e) {
						LOGGER.error("missing MU");
					}
					

				}catch (Exception e) {
					try {		
						LinkedHashMap<String, Double> value = new LinkedHashMap<String, Double>();
						Double number = applyConditionOnValue(qvo, Double.valueOf(rs.getString(3)));
						if (number != null)
							value.put("", number);
						map.put("", value);
					}catch (Exception e2) {
						
					}
				}
			}
			
			if ( qvo.isSumOthers()) {
				if ( qvo.getOthersLimit() != null ) {
					DataViewerBoxContent outputType = DataViewerBoxContent.valueOf(qvo.getTypeOfOutput());
					Boolean pie = false;
					if ( outputType.equals(DataViewerBoxContent.PIE))
						pie = true;
					
					sumOthers(map, measurementUnits, pie, qvo.getOthersLimit(), qvo.getLanguage());
				}
			}
			
//			System.out.println("===================================================================================");
//			System.out.println(rvo.getTitle());
//			for (String key : map.keySet()) {
//				System.out.println("KEY: " + key);
//				Map<String, Double> m = map.get(key);
//				for (String s : m.keySet())
//					System.out.println("\t" + s + ": " + m.get(s));
//			}
//			System.out.println("===================================================================================");
			
//			LOGGER.info("chart map: " + map);
//			LOGGER.info("chart serieMeasurementUnits: " + serieMeasurementUnits);
//			LOGGER.info("chart measurementUnits: " + measurementUnits);

			rvo.setChartValues(map);
			rvo.setSerieMeasurementUnits(serieMeasurementUnits);
			rvo.setMeasurementUnits(measurementUnits);
			if ( mus != null)
				mus.putAll(measurementUnits);
			
			rs.close();
			stmt.close();
			c.close();
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
		long endTime = System.currentTimeMillis();	
		long connection = (t1-t0);
		long query = (t2-t0);
		long end = (endTime-t0);	
		
//		LOGGER.info(end + "ms " + " | " + connection + "ms " + " | " + query + "ms " +
//                " | " + qvo.getTypeOfOutput() + " | " + qvo.getTitle());
	}
	
	private void sumOthers(LinkedHashMap<String, Map<String, Double>> map, LinkedHashMap<String, String> measurementUnits, Boolean isPie, Integer othersLimit, String language) {
		
		if ( isPie ) {
			LinkedHashMap<String, Map<String, Double>> finalValues = new LinkedHashMap<String, Map<String,Double>>();
			LinkedHashMap<String, Map<String, Double>> otherValues = new LinkedHashMap<String, Map<String,Double>>();
			Map<String, Double> otherValue = new LinkedHashMap<String,Double>();
			int i = 0;
			for(String code : map.keySet()) {
				for(String label : map.get(code).keySet()) {
					if ( i < othersLimit ) {
						finalValues.put(code, map.get(code));
					}
					else {
						Double value = map.get(code).get(label);
						String other = FAOSTATLanguageConstantsServer.print("OTHERS", language);
						if ( otherValue.containsKey(other)) {
							value = value + otherValue.get(other);
						}
						otherValue.put(other, value);
						otherValues.put(FAOSTATLanguageConstantsServer.print("OTHERS", language), otherValue);
					}
					i++;
				}
			}
			finalValues.putAll(otherValues);
			map.clear();
			map.putAll(finalValues);
//			LOGGER.info(finalValues);
		}
		else {
			
		}

	}
	
	private LinkedHashMap<String, Map<String, Double>> queryChartTimeSerieResult(DWFAOSTATQueryVO qvo) throws FenixException {
		LinkedHashMap<String, Map<String, Double>> map = new LinkedHashMap<String, Map<String, Double>>();
		
		LinkedHashMap<String, String> measurementUnits = new LinkedHashMap<String, String>();
		
		long startTime = System.currentTimeMillis();
		
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				try {
					Map<String, Double> value = new LinkedHashMap<String, Double>();
					value.put(rs.getString(2), Double.valueOf(rs.getString(3)));
					if ( map.containsKey(rs.getString(1))) {
						Map<String, Double> currentHM = map.get(rs.getString(1));
						currentHM.put(rs.getString(2), Double.valueOf(rs.getString(3)));
						map.put(rs.getString(1), currentHM);

					}
					else {
						map.put(rs.getString(1), value);
					}
					
					try {
						measurementUnits.put(rs.getString(1), rs.getString(4));
					}catch (Exception e) {
					}
					
				}catch (Exception e) {
					try {
					
						LinkedHashMap<String, Double> value = new LinkedHashMap<String, Double>();
						value.put("", Double.valueOf(rs.getString(3)));
						map.put("", value);
					}catch (Exception e2) {
						
					}
				}
			}
//			LOGGER.info("chart map: " + map);
//			LOGGER.info("chart measurementUnits: " + measurementUnits);

			//stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
		long endTime = System.currentTimeMillis();			
		long value = (endTime-startTime);			
//		if ( value > thresholdQuery ) {
//			LOGGER.info(value +"ms" + " | "+ qvo.getTypeOfOutput() + " | " + qvo.getTitle());
//		}
		return map;
	}
	
	private LinkedHashMap<String, Map<String, List<Double>>> queryAnnualGrowthRateLeastSquare(DWFAOSTATQueryVO qvo) throws FenixException {
		LinkedHashMap<String, Map<String, List<Double>>> map = new LinkedHashMap<String, Map<String, List<Double>>>();
		
		long startTime = System.currentTimeMillis();
		
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				try {
//					LOGGER.info("ROW: "+ rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4));
					
					Double value =  Double.valueOf(rs.getString(4));
					
					Map<String, List<Double>> serieHM = new LinkedHashMap<String, List<Double>>();
					// if already exists the serie
					if ( map.containsKey(rs.getString(1))) {
						serieHM = map.get(rs.getString(1));
						
						// check if exist X axis 
						List<Double> values = new ArrayList<Double>();
						if ( serieHM.containsKey(rs.getString(2))) {
							values = serieHM.get(rs.getString(2));
							values.add(value);
						}
						// if it doesn't exit add the value 
						else {
							values.add(value);
						}
						serieHM.put(rs.getString(2), values);
						
					}
					else {
						// new serie, just add the value
						List<Double> values = new ArrayList<Double>();
						values.add(value);
						serieHM.put(rs.getString(2), values);
					}
					
					map.put(rs.getString(1), serieHM);
					
				}catch (Exception e) {
				}
			}

//			stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();
			
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
		long endTime = System.currentTimeMillis();			
		long value = (endTime-startTime);			
//		if ( value > thresholdQuery ) {
//			LOGGER.info(value +"ms" + " sec " + " | " + qvo.getTypeOfOutput());
//		}
		return map;
	}
	
	
	
	private List<String> querySingleResult(DWFAOSTATQueryVO qvo) throws FenixException {
		List<String> values = new ArrayList<String>();
		
		long t0 = System.currentTimeMillis();
		long t1;
		long t2;
		
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			
			t1 = System.currentTimeMillis();
			
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			t2 = System.currentTimeMillis();
			
			while (rs.next()) {
				values.add(rs.getString(1));
			}

//			stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();

			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
//		long endTime = System.currentTimeMillis();	
//		long connection = (t1-t0);
//		long query = (t2-t0);
//		long end = (endTime-t0);	
//		
//		LOGGER.info(end + "ms " + " | " + connection + "ms " + " | " + query + "ms " +
//                " | " + qvo.getTypeOfOutput() + " | " + qvo.getTitle());

		return values;
	}

	private LinkedHashMap<String, Map<String, Double>> querySimpleChartResult(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) throws FenixException {
		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();
//		Map<String, Double> value = new LinkedHashMap<String, Double>();

		long t0 = System.currentTimeMillis();
		long t1;
		long t2;
		
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			
			t1 = System.currentTimeMillis();
			
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			t2 = System.currentTimeMillis();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			 int columnCount = rsmd.getColumnCount();

			while (rs.next()) {
					Map<String, Double> value = new LinkedHashMap<String, Double>();
					value.put("1", rs.getDouble(3));
					values.put(rs.getString(2).toString(), value);
					
					rvo.setMeasurementUnit(rs.getString(4));
			}

			rs.close();
			stmt.close();
			c.close();
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InstantiationException e) {
			throw new FenixException(e.getMessage());
		} 
		
//		long endTime = System.currentTimeMillis();	
//		long connection = (t1-t0);
//		long query = (t2-t0);
//		long end = (endTime-t0);	
//		
//		LOGGER.info(end + "ms " + " | " + connection + "ms " + " | " + query + "ms " +
//                " | " + qvo.getTypeOfOutput() + " | " + qvo.getTitle());
	
//		values.put("1", value);
		return values;
	}
	
	
	/**
	 * 
	 * Method to get informations (i.e. Coding systems)
	 * 
	 * @param qvo
	 * @param rvo
	 * @return
	 */
	private DWFAOSTATResultVO get(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		DataViewerBoxContent c = DataViewerBoxContent.valueOf(qvo.getTypeOfOutput());
//		LOGGER.info(c.name());
		
		switch (c) {
			case CODING_SYSTEM_FAOSTAT_GROUPS: rvo = FAOSTATGetCodingSystem.getGroups(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_ALL_GROUPS_DOMAINS: rvo = FAOSTATGetCodingSystem.getAllGroupsAndAssociatedDomains(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_ALL_GROUPS_DOMAINS_BULK_DOWNLOAD: rvo = FAOSTATGetCodingSystem.getAllGroupsAndAssociatedDomainsBulkDownload(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_DOMAINS_FOR_GROUP: rvo = FAOSTATGetCodingSystem.getDomainsForGroup(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_ALL_METADATA_GROUPS_DOMAINS: rvo = FAOSTATGetCodingSystem.getAllMetadataGroupsAndAssociatedDomains(qvo, rvo);  break;
			case CODING_SYSTEM_FAOSTAT_ALL_METADATA_METHODOLOGIES: rvo = FAOSTATGetCodingSystem.getAllMetadataMethodologies(qvo, rvo);  break;
			
			case CODING_SYSTEM_FAOSTAT_COUNTRIES: rvo = FAOSTATGetCodingSystem.getCountries(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_COUNTRIES_CONTINENTS: rvo = FAOSTATGetCodingSystem.getCountriesWithContinents(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_AREAS_WORLD: rvo = FAOSTATGetCodingSystem.getAreas(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_AREAS_WORLD_AGGREGATED: rvo = FAOSTATGetCodingSystem.getAreas(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_AREAS_FAO: rvo = FAOSTATGetCodingSystem.getAreasFAO(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_COUNTRIES_AREAS_ALL: rvo = FAOSTATGetCodingSystem.getCountriesAreasAll(qvo, rvo); break;

			case CODING_SYSTEM_FAOSTAT_YEAR: rvo = FAOSTATGetCodingSystem.getYears(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_ELEMENT: rvo = FAOSTATGetCodingSystem.getElements(qvo, rvo); break;
		    case CODING_SYSTEM_FAOSTAT_ELEMENT_LIST: rvo = FAOSTATGetCodingSystem.getElementsList(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_ELEMENT_BY_DOMAIN_ITEM_ELEMENT: rvo = FAOSTATGetCodingSystem.getElementsByItem(qvo, rvo); break;
			
			
			
			case CODING_SYSTEM_FAOSTAT_ITEM: rvo = FAOSTATGetCodingSystem.getItems(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_ITEM_COMPARE: rvo = FAOSTATGetCodingSystem.getItemsCompareData(qvo, rvo); break;
			
			case DOWNLOAD_GET_ITEMS_ONLY: rvo = FAOSTATGetCodingSystem.getDownloadItemsOnly(qvo, rvo); break;
//			case DOWNLOAD_GET_AGGREGATES_ONLY: rvo = FAOSTATGetCodingSystem.getDownloadAggregatesOnly(qvo, rvo); break;
			case DOWNLOAD_GET_AGGREGATES_ONLY: rvo = FAOSTATGetCodingSystem.getItemsAggregations(qvo, rvo); break;
			case DOWNLOAD_GET_ITEMS_AND_AGGREGATES: rvo = FAOSTATGetCodingSystem.getDownloadItemsAndAggregates(qvo, rvo); break;

			case CODING_SYSTEM_FAOSTAT_ITEM_AGGREGATIONS: rvo = FAOSTATGetCodingSystem.getItemsAggregations(qvo, rvo); break; 
			case CODING_SYSTEM_FAOSTAT_ITEM_DISAGGREGATIONS: rvo = FAOSTATGetCodingSystem.getItemsDisaggregations(qvo, rvo); break;
			case CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT_UNIT: rvo = FAOSTATGetCodingSystem.getItemsByElement(qvo, rvo); break;

			case CODING_SYSTEM_FAOSTAT_ITEM_BY_DOMAIN_ITEM_ELEMENT: rvo = FAOSTATGetCodingSystem.getItemsDisaggregations(qvo, rvo); break;
			
			
			// USED FOR THE SEARCH
			case ITEMS_BY_LABEL: rvo = FAOSTATGetCodingSystem.getItemsByLabel(qvo, rvo); break;
			case ELEMENTS_BY_LABEL: rvo = FAOSTATGetCodingSystem.getElementsByLabel(qvo, rvo); break;
			
			case GROUPS_AND_DOMAINS_BY_ITEM: rvo = FAOSTATGetCodingSystem.getAllGroupsAndAssociatedDomainsByItems(qvo, rvo); break;
			case GROUPS_AND_DOMAINS_BY_ELEMENTLIST: rvo = FAOSTATGetCodingSystem.getAllGroupsAndAssociatedDomainsByElementsList(qvo, rvo); break;

			case RESULT_SEARCH_INFOS: rvo = FAOSTATGetCodingSystem.getResultSearchInfos(qvo, rvo); break;
			
			case RESULT_SEARCH_ITEMS: rvo = FAOSTATGetCodingSystem.getResultSearchItems(qvo, rvo); break;
			case RESULT_SEARCH_ELEMENTS: rvo = FAOSTATGetCodingSystem.getResultSearchElements(qvo, rvo); break;
		}
		
		
		return rvo;
	}

	private DWFAOSTATResultVO createPivotTable(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {

		qvo.setSql(DWFAOSTATQueryBuilder.pivotTable(qvo, this.dbName));
		
		long t0 = System.currentTimeMillis();	
		long t1;	
		long t2;	
		long t3 = 0;
		long t4 = 0;

		try
		{
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			
			t1 = System.currentTimeMillis();	
			
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			
			t2 = System.currentTimeMillis();	
			
			DataViewerBoxContent type = DataViewerBoxContent.valueOf(qvo.getTypeOfOutput());
			switch (type) {
				case HTML:  
							Map<String, String> flags = new HashMap<String, String>();
							DataViewerUtils.getHtmlPivotTable(rs, qvo, rvo, "pivot_table", flags); 
							t3 = System.currentTimeMillis();			
							// run query
							// check if > 0
							if (rvo.getRows() > 0) {
								StringBuffer table = new StringBuffer();
								table.append(rvo.getTableHTML());
								if ( qvo.getAddFlag() ) {
									List<DWCodesModelData> codes = FAOSTATGetCodingSystem.getFlags(qvo, flags);
									t4 = System.currentTimeMillis();	
									table.append(DataViewerUtils.addDislaimerFlags(codes, "pivot_table"));
								}
								table.append(DataViewerUtils.addCopyright("pivot_table"));
								rvo.setTableHTML(table.toString());
							}
				break;
				case EXPORT_CSV: 
							String outputfile = fileFactory.createCSVZipFilePivotFaostat(rs, "\"", ",", true);
							// TODO: change with filepath
//							LOGGER.info("setting the filepath");
							rvo.setText(outputfile);
				break;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (Exception e) {
			throw new FenixException(e.getMessage());
		} 
		
		long endTime = System.currentTimeMillis();	
		long connection = (t1-t0);
		long query = (t2-t0);
		long flagquery = (t4-t3);
		long end = (endTime-t0);	
		
//		LOGGER.info(end + "ms " + " | " + connection + "ms " + " | " + query + "ms " + " | " + flagquery + "ms " +
//                " | " + qvo.getTypeOfOutput() + " | " + qvo.getTitle());

		return rvo;
	}




	public String export(DWFAOSTATResultVO rvo) {
		
		// TODO: change it...
		String filename = tableExcel.createExcel("", rvo.getTitle(), rvo.getTableHeaders(), rvo.getTableContents(), false);
		
		return filename;
	}
	
	
	private String export(DWFAOSTATQueryVO qvo) throws FenixException {
		String outputfile ="";
		
//		LOGGER.info("START EXPORT");
		try
		{
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
//			LOGGER.info("EXECUTING QUERY...");
			stmt.executeQuery(qvo.getSql());
//			LOGGER.info("FINISHED QUERY...CREATING CSV");
			ResultSet rs = stmt.getResultSet();
			
			/** TODO have a sort of formatted selects fields **/
			outputfile = fileFactory.createCSVZipFile(rs, "\"", ",", true);
			
//			LOGGER.info("outputfile: " + outputfile);
			//stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();
			
//			LOGGER.info("END EXPORT");
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (Exception e) {
			throw new FenixException(e.getMessage());
		} 
	
		
		return outputfile;
	}
	
	private DWFAOSTATResultVO createFENIXMetadataTable(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {

		List<String> froms = new ArrayList<String>();
		List<String> columns = qvo.getTableHeaders();
		
		CustomDataset customDataset = customDatasetDao.find(qvo.getFenixDatasetCode());
		if(customDataset!=null) {
			froms.add(customDataset.getTablename());
		}
		
		qvo.setFroms(froms);
		qvo.setSql(DWFAOSTATQueryBuilder.sqlFenixMetadataTableQuery(qvo));
		

		List<List<String>> rows = new ArrayList<List<String>>();
		
	    List<Object[]> sqlResult = faoStatDao.createQuery(qvo.getSql());
        int rowCount = 0;
        int cols = qvo.getSelects().size();

        for(int i=0; i < sqlResult.size(); i++) {
			  rowCount++;
			  List<String> tableContents = new ArrayList<String>();
			  for (int j = 0; j < cols; j++) {
				 String value;
				 
				  if(sqlResult.get(i)[j]!=null)
					  value =  sqlResult.get(i)[j].toString();
				  else 
					  value = "";
				  
				  tableContents.add(value);
			    }
			   rows.add(tableContents);
			  }
			
        String styleName = "metadata_table";
        
		String table = DataViewerUtils.getHtmlTable(sqlResult, columns, styleName);

		rvo.setTableHTML(table);
		rvo.setTableHeaders(columns);
		rvo.setTableContents(rows);
		
		return rvo;
	}
	
	private DWFAOSTATResultVO createFAOSTATMetadataTable(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		qvo.setSql(DWFAOSTATQueryBuilder.sqlFaostatMetadataTableQuery(qvo));
		try
		{
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet rs = stmt.getResultSet();
			DataViewerUtils.getHtmlTable(rs, rvo, qvo.getTableHeaders(), "metadata_table");
			//stmt.close();
//			c.close();
			
			rs.close();
			stmt.close();
			c.close();

		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (Exception e) {
			throw new FenixException(e.getMessage());
		} 	
		return rvo;
	}
	
	private DWFAOSTATResultVO createMetadataMethodology(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		
		qvo.setSql(DWFAOSTATQueryBuilder.sqlFaostatMetadataTableQuery(qvo));
		
		try
		{
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			ResultSet methodologyResultSet = stmt.getResultSet();
			
			Statement stmt2 = c.createStatement();
			stmt2.executeQuery(DWFAOSTATQueryBuilder.sqlGlossaryTermsForMetadataMethodologyQuery(qvo));
			ResultSet glossaryResultSet = stmt2.getResultSet();
				
			DataViewerUtils.getHtmlTableMethodology(methodologyResultSet, glossaryResultSet, rvo, qvo.getTableHeaders(), "metadata_table");
			
//			stmt.close();			
//			stmt2.close();
			
			methodologyResultSet.close();
			glossaryResultSet.close();
			stmt.close();			
			stmt2.close();
			c.close();
			
//			LOGGER.info("END createMetadataMethodology");
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (Exception e) {
			throw new FenixException(e.getMessage());
		} 
		
		return rvo;
	}
	
	private DWFAOSTATResultVO createTable(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		
		
		// JOIN QUERY
		if( qvo.getRunJoinQuery()) { 
			
		    if(qvo.getRunMaxDateQuery()) {
				// // getting as default the first QVO....
		    	/// TODO: remove and set as default...
		    	qvo.getJoinQueryVO().getQvos().get(0).setRunMaxDateQuery(true);
		    	qvo.getJoinQueryVO().getQvos().get(0).setTimeSpan(qvo.getTimeSpan());
		    	qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo.getJoinQueryVO().getQvos().get(0), true, false, false));
				List<String> singleValue =	querySingleResult(qvo);
				for(String val: singleValue){
					qvo.setRunMaxDateQuery(false);
					for(DWFAOSTATQueryVO q : qvo.getJoinQueryVO().getQvos()) {
						rvo.setMaxDateLimit(val);	
						if(qvo.getTimeSpan()!=null)
							rvo.setTimeIntervalSpan(qvo.getTimeSpan());	
						
						Map<String, String> years = new HashMap<String, String>();
						years.put(val, val);
						
						q.setTimeSpan(qvo.getTimeSpan());
						q.setYears(years);
						q.setMaxDateLimit(val);	
					}
				}
			}	
			qvo.setSql(DWFAOSTATQueryBuilder.sqlJoinQuery(qvo));
			createFlatTable(qvo, rvo);
		}
		
		// TODO: make it generic somewhere
		else if( qvo.getRunCalculationQuery()) { 
			if(qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.GROWTH_RATE) || 
			   qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.ANNUAL_GROWTH_RATE) ||
			   qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.ANNUAL_GROWTH_RATE_POPULATION) ){	
				buildGrowthRate(qvo, rvo);
			}
			else if (qvo.getCalculationParametersVO().getCalculationType().equals(FAOSTATAggregationConstant.ANNUAL_GROWTH_RATE_LEAST_SQUARE)) {
				buildGrowthRateLeastSquare(qvo, rvo);
			}
			buildGrowthRateTable(rvo, true);
		}
		// GENERAL QUERY
		else {
			List<String> singleValue = new ArrayList<String>();
			//STARt
				if(qvo.getRunMaxDateQuery()) {
					qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, true, false, false));
					singleValue = querySingleResult(qvo);
	
					for(String val: singleValue){
						qvo.setRunMaxDateQuery(false);
						qvo.setMaxDateLimit(val);	
						rvo.setMaxDateLimit(val);	
	
						if(qvo.getTimeSpan()!=null)
							rvo.setTimeIntervalSpan(qvo.getTimeSpan());	
						
						Map<String, String> years = new HashMap<String, String>();
						years.put(val, val);
						qvo.setYears(years);
					}	
				}

			// create the SQL query
			qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, false, false, false));
			createFlatTable(qvo, rvo);
			
		}
		return rvo;
	}
	
	private void createFlatTable(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) throws FenixException {
		
		long t0 = System.currentTimeMillis();
		long t1;
		long t2;

		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			
			t1 = System.currentTimeMillis();
			
			stmt.executeQuery(qvo.getSql());
			
			ResultSet rs = stmt.getResultSet();
			
			t2 = System.currentTimeMillis();
			
			/** TODO have a sort of formatted selects fields **/
			DataViewerBoxContent type = DataViewerBoxContent.valueOf(qvo.getTypeOfOutput());
			switch (type) {
				case HTML:
						DataViewerUtils.getHtmlTable(rs, rvo,  qvo.getShowColumnHeaders(), "flat_table"); 
//						LOGGER.info("ROWS: " + rvo.getRows());
				break;
				case EXPORT_CSV: 
						// String outputfile = export(qvo);
					    String outputfile = fileFactory.createCSVZipFile(rs, "\"", ",", true);
						// TODO: change with filepath
						rvo.setText(outputfile);
				break;
				case TO_EXPORT: 
						DataViewerUtils.getExportTable(rs, rvo,  qvo.getShowColumnHeaders()); 
//						LOGGER.info("headers " + rvo.getTableHeaders());
//						LOGGER.info("contents " + rvo.getTableContents());
				break;
			}
			
			
			rs.close();
			stmt.close();
			c.close();
			
		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (Exception e) {
			throw new FenixException(e.getMessage());
		} 
		
		long endTime = System.currentTimeMillis();	
		long connection = (t1-t0);
		long query = (t2-t0);
		long end = (endTime-t0);	
		
//		LOGGER.info(end + "ms " + " | " + connection + "ms " + " | " + query + "ms " +
//                " | " + qvo.getTypeOfOutput() + " | " + qvo.getTitle());
	
	}

	
	private DWFAOSTATResultVO createMap(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo, Boolean isGoogleMap) {
		List<String> singleValue = new ArrayList<String>();

		if(qvo.getRunMaxDateQuery()) {
			qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, true, false, true));
			singleValue =querySingleResult(qvo);

			for(String val: singleValue){
				qvo.setRunMaxDateQuery(false);
				qvo.setMaxDateLimit(val);	
				rvo.setMaxDateLimit(val);	
				
				if(qvo.getTimeSpan()!=null)
					rvo.setTimeIntervalSpan(qvo.getTimeSpan());	

				Map<String, String> years = new HashMap<String, String>();
				years.put(val, val);
				qvo.setYears(years);
			}	
		}

		// create the SQL query
		qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, false, true, true));

		// the size are the select +1 (the value)
		// TODO Dynamic Select size
		Map<String, Double> faostatValues = new LinkedHashMap<String, Double>();
		rvo.getChartValues().putAll(queryMap(qvo, rvo, faostatValues));
		List<String> blacklist = new ArrayList<String>();
		
		if (qvo.getValueCondition() != null) {
			double t = qvo.getValueTreshold().doubleValue();
			for (String country : faostatValues.keySet()) {
				double d = faostatValues.get(country).doubleValue();
				switch (qvo.getValueCondition()) {
					case GREATERTHAN:
						if (d > t)
							faostatValues.put(country, d);
						else
							blacklist.add(country);
					break;
					case GREATERTHANOREQUALSTO:
						if (d >= t)
							faostatValues.put(country, d);
						else
							blacklist.add(country);
					break;
					case EQUALSTO:
						if (d == t)
							faostatValues.put(country, d);
						else
							blacklist.add(country);
					break;
					case LESSTHAN:
						if (d < t)
							faostatValues.put(country, d);
						else
							blacklist.add(country);
					break;
					case LESSTHANOREQUALSTO:
						if (d <= t)
							faostatValues.put(country, d);
						else
							blacklist.add(country);
					break;
				}
			}
			for (String country : blacklist) 
				faostatValues.remove(country);
		}
		
		if (qvo.isUseAbsoluteValues()) {
			for (String country : faostatValues.keySet()) {
				double d = Math.abs(faostatValues.get(country).doubleValue());
				faostatValues.put(country, d);
			}
		}

		// translate codes from FAOSTAT to ISO
		CustomDataset convtable = customDatasetDao.find("GEO_CONVERTION_TABLE");

		List<String> faostatCodes = new ArrayList<String>();
		for (String key : faostatValues.keySet()) {
			faostatCodes.add(key);
		}
		
		rvo.getChartValues().putAll(new LinkedHashMap<String, Map<String,Double>>());
		if ( !faostatCodes.isEmpty() ) {
//			LOGGER.info("isGoogleMap: " + isGoogleMap);
			if ( isGoogleMap ) {
				qvo.setSql(DWFAOSTATQueryBuilder.buildGoogleDataset(convtable.getTablename(), faostatCodes, qvo.getLanguage()));
				// create googlemap dataset
				rvo.getChartValues().putAll(createGoogleMap(qvo, faostatValues));
			}
			else {
				// call server (use map)
				// TODO: check if the server is up, if it's down use the google maps
				rvo.setUrl(mapsServiceUtils.getMap(faostatValues, rvo.getMeasurementUnit(), rvo.getMeasurementUnit()));
				// TODO: change it and export directly from the first map values.
				// 1) add it in the XML selects values
				// 2) change the the query results values (code, label, value, mu)
//				rvo.getChartValues().put("", faostatValues);
			}
		}

		return rvo;
	}
	
	private Map<String, Map<String, Double>> createGoogleMap(DWFAOSTATQueryVO qvo, Map<String, Double> faostatValues){
		Map<String, Map<String, Double>> results = new HashMap<String, Map<String,Double>>();
		
		List<Object[]> sqlResult = faoStatDao.createQuery(qvo.getSql());
	
		for(int i=0; i < sqlResult.size(); i++) {
			try {
		
			String faostatCode = sqlResult.get(i)[0].toString();
			String iso2Code =  sqlResult.get(i)[1].toString();
			String label =  sqlResult.get(i)[2].toString();
			
			Double value = faostatValues.get(faostatCode);
			
			HashMap<String, Double> v = new HashMap<String, Double>();
			v.put(label, value);
			results.put(iso2Code, v);
			
			} catch (Exception e) {
				
			}
		}
		return results;
	}
	
	// TODO: should be used that one...
   private void runMaxQuery(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
	   
	   
//	   if ( qvo.getY)
//	   qvo.setSql(DWFAOSTATQueryBuilder.buildQuery(qvo, true, false));
//		singleValue =querySingleResult(qvo);
//
//		for(String val: singleValue){
//			qvo.setRunMaxDateQuery(false);
//			qvo.setMaxDateLimit(val);	
//			rvo.setMaxDateLimit(val);	
//			
//			if(qvo.getTimeSpan()!=null)
//				rvo.setTimeIntervalSpan(qvo.getTimeSpan());	
//
//			Map<String, String> years = new HashMap<String, String>();
//			years.put(val, val);
//			qvo.setYears(years);
   }
		
   public FAOSTATVisualizeSettingsVO getFAOSTATQueryVOs(FAOSTATCurrentView currentView, String filename, String lang) {

		FAOSTATXMLParser parser = new FAOSTATXMLParser(configFilePath + File.separator + currentView.name(), filename, lang);		
		
	    return parser.getAllFAOSTATQueryVOs();
	}
   
   public List<NoteVO> getNotesByDomain(String filename, String language) {
	    return faostatNotesXMLParser.readIndex(filename, language);
	}
   
   
   private DWFAOSTATResultVO createDomainNotes(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		for(String code: qvo.getDomains().keySet()){
			rvo.setNotes(getNotesByDomain(code, qvo.getLanguage()));
		}
		
		return rvo;
	}
   
   private DWFAOSTATResultVO createDomainNoteContent(DWFAOSTATQueryVO qvo, DWFAOSTATResultVO rvo) {
		for(String code: qvo.getDomains().keySet()){
			rvo.setNotes(getNotesContentByDomain(code, qvo.getLanguage()));
		}
		return rvo;
	}
   
   public List<NoteVO> getNotesContentByDomain(String filename, String language) {
	   return faostatNotesXMLParser.getNotes(filename, language);
	}
   
   
    private Map<String, List<String>> getLastUpdatedDomains(DWFAOSTATQueryVO qvo) throws FenixException {
		Map<String, List<String>> groupMap = new LinkedHashMap<String, List<String>>();
		try {
			SQLServerDriver.class.newInstance();
			Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
			Statement stmt = c.createStatement();
			stmt.executeQuery(qvo.getSql());
			
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				String group = rs.getString(1);
				String domain = rs.getString(2);
				String date = rs.getString(3);
				
				if(groupMap.containsKey(group)){
					groupMap.get(group).add(domain + " " + date);
				} else {
					List<String> domainList = new ArrayList<String>();
					domainList.add(domain + " " + date);
					groupMap.put(group, domainList);
				}
			}
			rs.close();
			stmt.close();
			c.close();

		} catch (SQLException e) {
			throw new FenixException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (Exception e) {
			throw new FenixException(e.getMessage());
		} 
	
		return groupMap;
	}
    
    
	public List<DWCodesModelData> getCodes(String cs, String domainCode, String language) throws FenixGWTException {
		try {
			return dataServiceUtils.getCodes(cs, domainCode, language, datasource);
		} catch (AxisFault e) {
			e.printStackTrace();
			System.out.println("AxisFault @ getCodes");
			throw new FenixGWTException(e.getMessage());
		} catch (FenixGWTException e) {
			e.printStackTrace();
			System.out.println("RGWTException @ getCodes");
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	private DWFAOSTATResultVO getRGWTUrl(DWFAOSTATQueryVO qvo,  DWFAOSTATResultVO rvo) {
		
		rvo.setUrl(dataServiceUtils.getRGwtURL(qvo.getLanguage()));
		return rvo;
	}
	
	
    private void setConnectionString(DWFAOSTATQueryVO qvo) {
        selectDatabase();
        
//      randomDatabaseSelection();
        
        String s = "jdbc:sqlserver://" + DATABASE + ";databaseName=Warehouse;";
        qvo.setConnectionString(s);
	}
	
	public void setFAOSTATDBSettings(DWFAOSTATQueryVO p) {
	        selectDatabase();
	        p.setDbDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        p.setConnectionDriver("JDBC");
	        p.setDatasource("FAOSTAT");
	        p.setDbUrl("jdbc:sqlserver://");
	        p.setDbServerName(DATABASE);
	        p.setDbName("Warehouse");
	        p.setDbUserName("Warehouse");
	        p.setDbPassword("w@reh0use");
	}
	
//	private void randomDatabaseSelection() {
//		int r = (int) Math.floor(Math.random() * 100);
//		
//		LOGGER.info("Random: " + r);
//		
//		if ( r < 25 ) {
//			 DATABASE = "FAOSTAT-GLBL\\Dissemination";
//		}
//		else {
//			  DATABASE = "HQWPRFAOSTATDB1\\Dissemination";
//		}
//	}
	
	private void selectDatabase() {
		if (this.getDbName().equalsIgnoreCase("FAOSTAT")) {
			DATABASE = "HQWPRFAOSTATDB1\\Dissemination";
			datasource = DATASOURCE.FAOSTAT;
		} else if (this.getDbName().equalsIgnoreCase("PRODUCTION-DISSEMINATION")) {
			DATABASE = "FAOSTAT-PROD\\Dissemination";
		//	datasource = DATASOURCE.FAOSTATPRODDISS;
		} else if (this.getDbName().equalsIgnoreCase("PRODUCTION")) {
			DATABASE = "FAOSTAT-PROD\\Production";
		//	datasource = DATASOURCE.FAOSTATPROD;
		} else if (this.getDbName().equalsIgnoreCase("GLBL")) {
			DATABASE = "FAOSTAT-GLBL\\Dissemination";
		//	datasource = DATASOURCE.FAOSTATGLBL;
		}
	}

	public void setDir(File dir) {
		this.dir = dir.getPath();
	}


	public void setFileFactory(FileFactory fileFactory) {
		this.fileFactory = fileFactory;
	}
	
	public void setCustomDatasetDao(CustomDatasetDao customDatasetDao) {
		this.customDatasetDao = customDatasetDao;
	}
	
	
	public void setFaoStatDao(FAOStatDao faoStatDao) {
		this.faoStatDao = faoStatDao;
	}
	
	public void setConfigFilePath(String configFilePath) {
		this.configFilePath = configFilePath;
	}
	
	public void setNotesConfigFilePath(String notesConfigFilePath) {
		this.notesConfigFilePath = notesConfigFilePath;
	}

	public void setTableExcel(TableExcel tableExcel) {
		this.tableExcel = tableExcel;
	}

	public void setFaostatNotesXMLParser(FAOSTATNotesXMLParser faostatNotesXMLParser) {
		this.faostatNotesXMLParser = faostatNotesXMLParser;
	}

	public void setDataServiceUtils(DataServiceUtils dataServiceUtils) {
		this.dataServiceUtils = dataServiceUtils;
	}
	
    public void setDbName(String dbName) {
        this.dbName = dbName;
	}
	
	public String getDbName() {
	        return dbName;
	}

	public void setMapsServiceUtils(MapsServiceUtils mapsServiceUtils) {
		this.mapsServiceUtils = mapsServiceUtils;
	}
}