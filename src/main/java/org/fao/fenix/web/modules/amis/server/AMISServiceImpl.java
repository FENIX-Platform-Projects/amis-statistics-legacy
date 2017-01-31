package org.fao.fenix.web.modules.amis.server;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.dataset.CustomDataset;
import org.fao.fenix.core.email.EmailSender;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.core.persistence.file.FileFactory;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.web.modules.amis.common.constants.AMISAggregationConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentDatasetView;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentView;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISService;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISSettingsVO;
import org.fao.fenix.web.modules.amis.common.vo.Book;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.amis.common.vo.ClientAmisDatasetResult;
import org.fao.fenix.web.modules.amis.common.vo.ClientCbsDatasetResult;
import org.fao.fenix.web.modules.amis.common.vo.Page;
import org.fao.fenix.web.modules.amis.common.vo.CCBS.Codes;
import org.fao.fenix.web.modules.amis.server.supplydemand.AMISRequestBuilder;
import org.fao.fenix.web.modules.amis.server.supplydemand.AMISSupplyDemandExcel;
import org.fao.fenix.web.modules.amis.server.utils.AMISFaostatUtils;
import org.fao.fenix.web.modules.amis.server.utils.AMISHtmlUtils;
import org.fao.fenix.web.modules.amis.server.utils.AMISUtils;
import org.fao.fenix.web.modules.amis.server.xml.AMISXMLParser;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.table.server.TableExcel;
import org.springframework.core.io.Resource;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

@SuppressWarnings("serial")
public class AMISServiceImpl extends RemoteServiceServlet implements
        AMISService {

    FileFactory fileFactory;
    private DBAdapter dbAdapter; // = new LocalDBAdapter();

    private final static Logger LOGGER = Logger
            .getLogger(AMISServiceImpl.class);
    private AMISDao amisDao;
    private DatasetDao datasetDao;

    private String ip;
    private String port;
    private String cbsMonths_ip;
    private String cbsMonths_port;
//	private CustomDatasetDao customDatasetDao;


    private TableExcel tableExcel;

    private AMISSupplyDemandExcel amisSupplyDemandExcel;
    private AMISRequestBuilder amisRequestBuilder;

    private String dir;
    private String configFilePath;
    private AMISCbsServerUtility aMISCbsServerUtility;



    private FenixPermissionManager fenixPermissionManager;

    private EmailSender emailSender;

    public AMISServiceImpl() {

    }

    public AMISServiceImpl(Resource resource) {
        try {
            aMISCbsServerUtility = new AMISCbsServerUtility();
            setDir(resource.getFile());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getCBSURL() {
        return "http://" + ip + ":" + port + "/cbs/data/index.html";
    }

    //This is for the monthly cbs tool
//    public String getCBSMONTHSURL() {
//		return "http://" + cbsMonths_ip + ":" + cbsMonths_port + "/cbsmonths/src/main/webapp/index.html";
//	}

    public String getCBSMONTHSURL() {
        return "http://" + cbsMonths_ip + ":" + cbsMonths_port + "/cbsmonths/index.html";
    }

    public String getCBSInputToolUrl() {
        return "http://statistics.amis-outlook.org/cbs_input_tool/";
    }

    public String getPopulationURL() {
        return "http://" + cbsMonths_ip + ":" + cbsMonths_port + "/amisPopulation/population.jsp";
    }


    public String sendRegistrationEMail(Map<String, String> formFields) throws FenixGWTException {
        try {
            //String receiver = "barbara.cintoli@fao.org";
            String receiver = "AMIS-Secretariat@fao.org";
            String header = "Request for AMIS login access";
            StringBuilder sb = new StringBuilder();
            for (String key : formFields.keySet())
                sb.append(key).append(": ").append(formFields.get(key)).append("\n");
            //emailSender.sendEMail(receiver, header, sb.toString(), new ArrayList<String>());
            emailSender.sendAMISEMail(receiver, header, sb.toString(), new ArrayList<String>());
            return "E-Mail sent";
        } catch (Exception e) {
            throw new FenixGWTException(e.getMessage());
        }
    }

    public AMISSettingsVO getAMISQueryVOs(AMISCurrentView currentView,
                                          String filename) {

        LOGGER.info("FILENAME:" + configFilePath + File.separator
                + currentView.name() + File.separator + filename);

        AMISXMLParser parser = new AMISXMLParser(configFilePath + File.separator + currentView.name(), filename);

        return parser.getAllAMISQueryVOs();
    }

    public AMISResultVO askAMIS(AMISQueryVO qvo) throws FenixGWTException {

        LOGGER.info("askAMIS START QVO OUTPUT = " + qvo.getOutput());
        // outputs
        AMISResultVO rvo = new AMISResultVO();

        // set AMISResultVO
        rvo.setOutput(qvo.getOutput());
        // rvo.setTitle(qvo.getTitle());

        // create the appropriate object
        AMISConstants o = AMISConstants.valueOf(qvo.getOutput());
        AMISConstants ot = null;
        if(qvo.getTypeOfOutput()!=null)
            ot = AMISConstants.valueOf(qvo.getTypeOfOutput());

        boolean elementsWithoutUnit = qvo.isElementsWithoutUnit();
        boolean flags = qvo.isFlags();
        boolean totalFlag = qvo.isTotalFlag();
        boolean elementsUnitsWithoutBrackets = qvo.isElementsUnitsWithoutBrackets();

        switch (o) {
            case CODING_SYSTEM:
                switch (ot) {
                    case AMIS_COUNTRIES:
                        if(!totalFlag)
                        {
                            rvo = AMISGetCodingSystem.getG20Countries(qvo, rvo, amisDao);
                        }
                        else
                        {	//Case New for Cbs Tool
                            rvo = AMISGetCodingSystem.getG20TotalCountries(qvo, rvo, amisDao);
                        }
                        break;
                    case AMIS_COUNTRY_CROP_COUNT:
                        rvo = AMISGetCodingSystem.getG20CountriesWithCrops(qvo, rvo, amisDao);
                        break;
                    case AMIS_PRODUCT_CROP_COUNT:
                        rvo = AMISGetCodingSystem.getProductsWithCrops(qvo, rvo, amisDao);
                        break;
                    case AMIS_NUM_CROP_COUNT:
                        rvo = AMISGetCodingSystem.getCropsNum(qvo, rvo, amisDao);
                        break;
                    // case AMIS_ELEMENTS: rvo =
                    // AMISFaostatGetCodingSystem.getAllElements(qvo, rvo, amisDao);
                    // break;
                    case AMIS_MARKETING_YEAR:
                        rvo = AMISGetCodingSystem.getMarketingYear(qvo, rvo, amisDao);
                        break;
                    case AMIS_ELEMENTS_CODE:
                        rvo = AMISGetCodingSystem.getElementsWithCode(qvo, rvo, amisDao);
                        break;
                    case AMIS_ELEMENTS:
                        if(elementsWithoutUnit)
                        {
                            //rvo = AMISGetCodingSystem.getElementsWithUnits(qvo, rvo, amisDao);
                            rvo = AMISGetCodingSystem.getElementsWidthoutUnits(qvo, rvo, amisDao);
                        }
                        else
                        {
                            //rvo = AMISGetCodingSystem.getElements(qvo, rvo, amisDao);
                            if(elementsUnitsWithoutBrackets)
                            {
                                rvo = AMISGetCodingSystem.getElementsWithUnits(qvo, rvo, amisDao);
                            }
                            else
                            {
                                rvo = AMISGetCodingSystem.getElements(qvo, rvo, amisDao);
                            }
                        }
                        break;
                    case AMIS_ELEMENTS_WITH_DATABASES:
                        rvo = AMISGetCodingSystem.getElementsWithDatabases(qvo, rvo, amisDao);
                        break;
                    case AMIS_PRODUCTS:
                        if(!totalFlag)
                        {
                            rvo = AMISGetCodingSystem.getProducts(qvo, rvo, amisDao);
                        }
                        else
                        {	//Case New for Cbs Tool
                            rvo = AMISGetCodingSystem.getTotalProducts(qvo, rvo, amisDao);
                        }
                        break;
                    case AMIS_PRODUCTS_WITHOUT_POPULATION:
                        rvo = AMISGetCodingSystem.getProductsWithoutPopulation(qvo, rvo, amisDao);
                        break;
                    case AMIS_GET_ELEMENTS_BY_PRODUCT:
                        rvo = AMISGetCodingSystem.getElementsByProduct(qvo, rvo, amisDao);
                        break;
                    //This has been created to avoid the Population element in the Compare Page
                    case AMIS_GET_ELEMENTS_BY_PRODUCT_COMPARE:
                        rvo = AMISGetCodingSystem.getElementsByProductCompare(qvo, rvo, amisDao);
                        break;
                    case AMIS_GET_PRODUCTS_BY_ELEMENT:
                        rvo = AMISGetCodingSystem.getProductsByElement(qvo, rvo, amisDao);
                        break;
                    //This has been created to avoid the Population element in the Compare Page
                    case AMIS_GET_PRODUCTS_BY_ELEMENT_COMPARE:
                        rvo = AMISGetCodingSystem.getProductsByElementCompare(qvo, rvo, amisDao);
                        break;
                    case TIMERANGE:
                        rvo = AMISGetCodingSystem.getYears(qvo, rvo, amisDao);
                        break;
                    case TIMERANGE_SPLIT:
                        rvo = AMISGetCodingSystem.getSplitYears(qvo, rvo, amisDao);
                        break;
                    case YEARS:
                        rvo = AMISGetCodingSystem.getRangeOfYears(qvo, rvo, amisDao);
                        break;
                    case DOWNLOAD_TIMERANGE:
                        rvo = AMISGetCodingSystem.getDownloadYears(qvo, rvo, amisDao);
                        break;
                    case AMIS_FOOD_BALANCE_COUNTRIES:
                        rvo = AMISGetCodingSystem.getFoodBalanceCountries(qvo, rvo, amisDao);
                        break;
                    case AMIS_FOOD_BALANCE_PRODUCTS:
                        rvo = AMISGetCodingSystem.getFoodBalanceProducts(qvo, rvo, amisDao);
                        break;
                    case AMIS:
                        if(flags)
                        {
                            rvo = AMISGetCodingSystem.getFlags(qvo, rvo, amisDao);
                        }
                        else
                        {
                            rvo = AMISGetCodingSystem.getDatatSource(qvo, rvo, amisDao);
                        }
                        break;
                    // case PSD_ELEMENTS: rvo = AMISGetCodingSystem.getPSDElements(qvo,
                    // rvo, amisDao); break;
                    // case CCBS_ELEMENTS: rvo = AMISGetCodingSystem.getPSDElements(qvo,
                    // rvo, amisDao); break;
                    // case TIMERANGE: rvo = AMISGetCodingSystem.getCCBSYears(qvo, rvo,
                    // amisDao); break;
                    case CODING_SYSTEM_PSD_PRODUCTS:
                        rvo = AMISGetCodingSystem.getPSDProducts(qvo, rvo, amisDao);
                        break;
                    case CODING_SYSTEM_PSD_ELEMENTS:
                        rvo = AMISGetCodingSystem.getPSDElements(qvo, rvo, amisDao);
                        break;
                    case CODING_SYSTEM_PSD_YEARS:
                        rvo = AMISGetCodingSystem.getPSDYears(qvo, rvo, amisDao);
                        break;
                    case CODING_SYSTEM_FAOSTAT_CROP_GROUPS_DOMAINS:
                        rvo = AMISFaostatGetCodingSystem
                                .getCropGroupsAndAssociatedDomains(qvo, rvo);
                        break;
                    case CODING_SYSTEM_FAOSTAT_YEAR:
                        rvo = AMISFaostatGetCodingSystem.getStaticYears(qvo, rvo);
                        break;
                }
                break;
            case CHART:
                switch (ot) {
                    default:
                        rvo = createDefaultChart(qvo, rvo);
                        break;
                }
                break;
            case TABLE: //Download Table
                rvo = createTable(qvo, rvo);
                break;
            case CEREAL_BALANCE_TABLE:    //Home Page Table
                rvo = createCerealBalanceTable(qvo, rvo);
                break;
            case FOOTNOTE_TABLE:
                rvo = createCerealBalanceTable(qvo, rvo);
                break;
            // case TABLE: rvo = createTable(qvo, amisDao, rvo); break;
            // case PIVOT_TABLE: rvo = createPivotTable(qvo, rvo); break;
            /***
             * case MAP: rvo = createMap(qvo, rvo); break; case CHART: switch (ot){
             * case PIE: rvo = createChart(qvo, rvo); break; case SCATTER: rvo =
             * createChart(qvo, rvo); break; } case TABLE: rvo = createTable(qvo,
             * rvo); break;
             ***/
        }

        // return the result bean
        rvo.setTitle(qvo.getTitle());
        rvo.setImageWidth(qvo.getWidth() + "px");
        rvo.setImageHeight(qvo.getHeight() + "px");
        rvo.setOutput(qvo.getOutput());
        rvo.setTypeOfOutput(qvo.getTypeOfOutput());

        if(qvo.getExportTitle()!=null)
            rvo.setExportTitle(qvo.getExportTitle());

        if(qvo.getSelectedDataset()!=null){
            AMISCurrentDatasetView c = AMISCurrentDatasetView.valueOf(qvo.getSelectedDataset());
            switch(c){
                case FAOSTAT : rvo.setSource(qvo.getSelectedDataset());	break;
//                case CBS : rvo.setSource("FAO-"+qvo.getSelectedDataset());	break;
                case CBS : rvo.setSource("AMIS-"+qvo.getSelectedDataset());	break;
                case PSD : rvo.setSource("USDA-"+qvo.getSelectedDataset());	break;
            }
        }

        LOGGER.info("askAMIS END");
        return rvo;
    }

    public List<AMISCodesModelData> findGroupsOfUser(String username)
    {
        List<AMISCodesModelData> models = new ArrayList<AMISCodesModelData>();

        String[] groupsOfUser = fenixPermissionManager.findGroupsOfUser(username);
        for(String userGroup:groupsOfUser )
        {
            LOGGER.info("userGroup "+userGroup);


            AMISCodesModelData countryGroup = new AMISCodesModelData();
            countryGroup.setCode(userGroup);
            countryGroup.setLabel(userGroup);

            models.add(countryGroup);
        }

        return models;
    }


    public Map<Long, AMISCodesModelData> getAllCustomDatasets() {

        Map<Long, AMISCodesModelData> customDatasetsMap = new LinkedHashMap<Long, AMISCodesModelData>();

        // retrieve all custom datasets
        for (CustomDataset dataset : datasetDao.findAllCustomDatasets()) {
            AMISCodesModelData model = new AMISCodesModelData();
            model.setCode(dataset.getCode());
            model.setLabel(dataset.getTitle());
            customDatasetsMap.put(dataset.getResourceId().longValue(), model);
        }

        return customDatasetsMap;
    }

    public List<AMISResultVO> getDatasetProperties(AMISQueryVO qvo)
            throws FenixGWTException {

        LOGGER.info("getDatasetProperties START");

        List<AMISResultVO> rvo = new ArrayList<AMISResultVO>();

        // create the appropriate object
        AMISConstants o = AMISConstants.valueOf(qvo.getOutput());
        AMISConstants ot = AMISConstants.valueOf(qvo.getTypeOfOutput());

        switch (o) {
            case FENIX_DATABASE:
                switch (ot) {
                    case FENIX_DATASET:
                        rvo = AMISGetQueryResult.getFenixDatasetProperties(qvo, rvo,
                                amisDao);
                        break;
                }
                break;
        }

        LOGGER.info("getDatasetProperties END");
        return rvo;
    }



    private String getSupplyDemandExcel(AMISQueryVO qvo, AMISResultVO rvo, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Map<String, Double>>>> foodBalanceResult, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Map<String, Double>>>> otherResult, LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Map<String, Double>>>> ityResult)  throws FenixException {
        String outputfile = "";

        LOGGER.info("################ getSupplyDemandExcel SQL " + qvo.getSql());
        try {
            if(((foodBalanceResult == null) || (foodBalanceResult.size() == 0)) && ((otherResult == null) || (otherResult.size() == 0))){
                LOGGER.info("----------getSupplyDemandExcel: BOTH ARE NULL or Size = 0 Other = " + otherResult + " | and foodBalance = "+foodBalanceResult);
                outputfile = "";
            }
            else {
                LOGGER.info("------- getSupplyDemandExcel: FINAL foodBalanceResult = " + foodBalanceResult);
                LOGGER.info("-------- getSupplyDemandExcel: FINAL otherResult = " + otherResult);


                outputfile = amisSupplyDemandExcel.createExcel(foodBalanceResult, otherResult, ityResult, qvo);

            }
        } catch (Exception e) {
            throw new FenixException(e.getMessage());
        }

        return outputfile;
    }


    private String sendSupplyDemandRequest(AMISQueryVO qvo) {
        String outputfile = "";


        LOGGER.info("============== sendSupplyDemandRequest ...");
        outputfile = amisRequestBuilder.postRequest(qvo);

        return outputfile;
    }

    private AMISResultVO createCerealBalanceTable(AMISQueryVO qvo, AMISResultVO rvo) {

        LinkedHashMap<String, Map<String, String>> values = new LinkedHashMap<String, Map<String,String>>();
        qvo.setAddLabels(true);

        // TODO: make it generic somewhere
        if( qvo.getRunCalculationQuery()) {

            LOGGER.info("run calc query ...");

            if(qvo.getCalculationParametersVO().getCalculationType().equals(AMISAggregationConstants.PERCENTAGE_SHARE)){
                LOGGER.info("TODO: run percentage share ...");
                //buildGrowthRate(qvo, rvo);

            }

        }
        //else {
        LOGGER.info("createCerealBalanceTable");
        values = new LinkedHashMap<String, Map<String,String>>();
        List<Object> singleValue = new ArrayList<Object>();
        Map<String, String> calculatedValues = new HashMap<String, String>();


        // create measurement unit SQL query
        qvo.setSql(AMISQueryBuilder.getMeasurementUnits(qvo, amisDao));

        List<Object> mus = amisDao.getSingleResultValue(qvo.getSql());

        //if ( mus.size() > 1 ) {
        LOGGER.info("DOUBLE SCALE");
        //}
        //else {
        LOGGER.info("mu "+mus.get(0).toString());

        if(qvo.getRunMaxDateQuery()) {
            // create the SQL query ... for a BAR chart

            if(qvo.getRunTimeIntervalQuery()){
                qvo.setSql(AMISQueryBuilder.buildQuery(qvo, true, false));

                List<Object[]> resultValues =	amisDao.getResultList(qvo.getSql());

                for(int i = 0; i < resultValues.size(); i++){
                    Object[] result = resultValues.get(i);
                    qvo.setRunMaxDateQuery(false);
                    qvo.setRunTimeIntervalQuery(false);
                    if(result[1]!=null && result[0]!=null){
                        qvo.setLowerDateLimit(result[0].toString()+"-01-01");
                        qvo.setMaxDateLimit(result[1].toString()+"-01-01");
                        rvo.setMaxDateLimit(result[1].toString()+"-01-01");
                        Map<String, String> years = new HashMap<String, String>();
                        years.put(result[1].toString()+"-01-01", result[1].toString()+"-01-01");
                        qvo.setYears(years);
                    } else {
                        qvo.setLowerDateLimit(null);
                        qvo.setMaxDateLimit(null);
                        rvo.setMaxDateLimit(null);
                        qvo.setYears(null);
                    }

                }
                //LOGGER.info(" qvo.setLowerDateLimit: " + qvo.getLowerDateLimit());
                //	LOGGER.info("setMaxDateLimit: " + qvo.getMaxDateLimit());

            } else {
                qvo.setSql(AMISQueryBuilder.buildQuery(qvo, true, false));

                singleValue =	amisDao.getSingleResultValue(qvo.getSql());

                for(Object val: singleValue){
                    //	LOGGER.info("singleValue: " + val);
                    qvo.setRunMaxDateQuery(false);
                    qvo.setMaxDateLimit(val.toString());
                    rvo.setMaxDateLimit(val.toString());

                    if(qvo.getTimeSpan()!=null)
                        rvo.setTimeIntervalSpan(qvo.getTimeSpan());


                    Map<String, String> years = new HashMap<String, String>();
                    years.put(val.toString(), val.toString());
                    qvo.setYears(years);
                }
            }
            if(qvo.getRunCalculationQuery()){
                if(!calculatedValues.isEmpty())
                    calculatedValues.clear();

                qvo.setSql(AMISQueryBuilder.buildCalculationQuery(qvo));
                singleValue =	amisDao.getSingleResultValue(qvo.getSql());

                for(Object val: singleValue){
                    //	LOGGER.info("singleValue: " + val);
                    calculatedValues.put(qvo.getCalculationParametersVO().getResultLabel(), val.toString());
                }

            }
            qvo.setSql(AMISQueryBuilder.buildQuery(qvo, false, false));
            // the size are the select +1 (the value)
            // TODO Dynamic Select size
            //				values = querySimpleChartResult(qvo, 3);
            values = queryTableResult(qvo);


            if(mus.get(0).toString().toLowerCase().equals("mt"))
                rvo.setMeasurementUnit("million tonnes");
            else
                rvo.setMeasurementUnit(mus.get(0).toString());

            //rvo.setTableHeaders(List<String>tableHeaders);
            //rvo.setTableContents(List<List<String>>);

            //rvo.setTableHTML(tableHTML)


            //qvo.setyLabel(mus.get(0).toString());

            //				createChart(qvo, rvo, values);

        }
        else {
            LOGGER.info("BUILD QUERY .... START");

            qvo.setSql(AMISQueryBuilder.buildQuery(qvo, false, false));
            // the size are the select +1 (the value)
            // TODO Dynamic Select size
            //			values = querySimpleChartResult(qvo, 3);
            values = queryTableResult(qvo);

            //	LOGGER.info("chart values "+values.size());

            if(mus.get(0).toString().toLowerCase().equals("mt"))
                rvo.setMeasurementUnit("million tonnes");
            else
                rvo.setMeasurementUnit(mus.get(0).toString());

            //	LOGGER.info("chart values "+values.size());
            qvo.setyLabel(mus.get(0).toString());

            //	LOGGER.info("chart values "+values.size());

            //			createChart(qvo, rvo, values);
        }
        //}
        LOGGER.info("createCerealBalanceTable SQL: "+qvo.getSql());

        createCerealBalanceTable(qvo, rvo, values);
        //rvo.setChartValues(values);
        //}	


        return rvo;
    }

    /**
     * private AMISResultVO createDefaultChart(AMISQueryVO qvo, AMISResultVO
     * rvo) {
     *
     * LinkedHashMap<String, Map<String, Double>> values = new
     * LinkedHashMap<String, Map<String,Double>>();
     *
     * LinkedHashMap<String, String> wheres = new LinkedHashMap<String,
     * String>();
     *
     * //qvo.setWheres().put(); //run 1 query per dataset for(String dataset:
     * qvo.getSelectedDatasets()){ LOGGER.info("selected dataset: "+dataset);
     * if(dataset.equals(AMISConstants.PSD.toString()) ||
     * dataset.equals(AMISConstants.CBS.toString()) ){
     * if(qvo.getFirstSelectField
     * ().equals(AMISConstants.AMIS_DATASETS.toString())){
     * qvo.getSelects().add(0, "dataset"); qvo.getOrderBys().add(0, "dataset");
     * wheres.put("dataset", dataset); } else
     * if(qvo.getFirstSelectField().equals
     * (AMISConstants.AMIS_PRODUCTS.toString())){ qvo.getSelects().add(0,
     * "product"); qvo.getOrderBys().add(0, "product"); } else
     * if(qvo.getFirstSelectField
     * ().equals(AMISConstants.AMIS_COUNTRIES.toString())){
     * qvo.getSelects().add(0, "country"); qvo.getOrderBys().add(0, "country");
     * }
     *
     * rvo = createDefaultChartQueries(qvo, rvo);
     * values.putAll(rvo.getChartValues());
     *
     * } else if(dataset.equals(AMISConstants.FAOSTAT.toString()) ) {
     *
     * } }
     *
     * return rvo; }
     **/

    private AMISResultVO createDefaultChartOriginal(AMISQueryVO qvo, AMISResultVO rvo) {
        // TODO: make it generic somewhere
        LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();

        if( qvo.getRunCalculationQuery()) {

            LOGGER.info("run calc query ...");

            if(qvo.getCalculationParametersVO().getCalculationType().equals(AMISAggregationConstants.PERCENTAGE_SHARE)){
                LOGGER.info("TODO: run percentage share ...");
                //buildGrowthRate(qvo, rvo);

            }

        }
        //else {
        LOGGER.info("createDefaultChart");
        values = new LinkedHashMap<String, Map<String,Double>>();
        List<Object> singleValue = new ArrayList<Object>();
        Map<String, Double> calculatedValues = new HashMap<String, Double>();


        // create measurement unit SQL query
        qvo.setSql(AMISQueryBuilder.getMeasurementUnits(qvo, amisDao));

        List<Object> mus = amisDao.getSingleResultValue(qvo.getSql());

        if ( mus.size() > 1 ) {
            LOGGER.info("DOUBLE SCALE");
        }
        else {
            LOGGER.info("mu "+mus.get(0).toString());

            if(qvo.getRunMaxDateQuery()) {
                // create the SQL query ... for a BAR chart
                qvo.setSql(AMISQueryBuilder.buildQuery(qvo, true, false));
                singleValue =	amisDao.getSingleResultValue(qvo.getSql());

                for(Object val: singleValue){
                    LOGGER.info("singleValue: " + val);
                    qvo.setRunMaxDateQuery(false);
                    qvo.setMaxDateLimit(val.toString());
                    rvo.setMaxDateLimit(val.toString());

                    if(qvo.getTimeSpan()!=null)
                        rvo.setTimeIntervalSpan(qvo.getTimeSpan());

                    Map<String, String> years = new HashMap<String, String>();
                    years.put(val.toString(), val.toString());
                    qvo.setYears(years);
                }

                if(qvo.getRunCalculationQuery()){
                    if(!calculatedValues.isEmpty())
                        calculatedValues.clear();

                    qvo.setSql(AMISQueryBuilder.buildCalculationQuery(qvo));
                    singleValue =	amisDao.getSingleResultValue(qvo.getSql());

                    for(Object val: singleValue){
                        LOGGER.info("singleValue: " + val);
                        calculatedValues.put(qvo.getCalculationParametersVO().getResultLabel(), (Double)val);
                    }

                }
                qvo.setSql(AMISQueryBuilder.buildQuery(qvo, false, false));
                // the size are the select +1 (the value)
                // TODO Dynamic Select size
                //				values = querySimpleChartResult(qvo, 3);
                values = queryChartResult(qvo);

                if(qvo.getMeasurementUnit()!=null){
                    rvo.setMeasurementUnit(qvo.getMeasurementUnit());
                    qvo.setyLabel(qvo.getMeasurementUnit());
                } else {
                    rvo.setMeasurementUnit(mus.get(0).toString());
                    qvo.setyLabel(mus.get(0).toString());
                }

                //rvo.setMeasurementUnit(mus.get(0).toString());
                //qvo.setyLabel(mus.get(0).toString());

                //				createChart(qvo, rvo, values);

            }
            else {
                LOGGER.info("BUILD QUERY .... START");

                qvo.setSql(AMISQueryBuilder.buildQuery(qvo, false, false));
                // the size are the select +1 (the value)
                // TODO Dynamic Select size
                //			values = querySimpleChartResult(qvo, 3);
                values = queryChartResult(qvo);

                LOGGER.info("chart values "+values.size());

                if(qvo.getMeasurementUnit()!=null){
                    rvo.setMeasurementUnit(qvo.getMeasurementUnit());
                    qvo.setyLabel(qvo.getMeasurementUnit());
                } else {
                    rvo.setMeasurementUnit(mus.get(0).toString());
                    qvo.setyLabel(mus.get(0).toString());
                }

                LOGGER.info("chart values "+values.size());

                //			createChart(qvo, rvo, values);
            }
            //}

            rvo.setChartValues(values);

            rvo.getHighChartVO().setChartValues(values);
            rvo.getHighChartVO().setTitle(qvo.getTitle());
            rvo.getHighChartVO().setType(qvo.getTypeOfOutput());
        }


        return rvo;
    }

    private LinkedHashMap<String, Map<String, String>> queryTableResult(
            AMISQueryVO qvo) throws FenixException {
        return amisDao.tableQueryResult(qvo.getSql());

    }

    private LinkedHashMap<String, LinkedHashMap<String, Map<String, Double>>> queryTableResultFoodBalance(
            AMISQueryVO qvo) throws FenixException {

        return amisDao.tableQueryResult2(qvo.getSql());

    }


    private LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Map<String, Double>>>> queryTableResultFoodBalance2(
            AMISQueryVO qvo) throws FenixException {

        return amisDao.tableQueryResult3(qvo.getSql());

    }

    private LinkedHashMap<String, Map<String, Double>> queryChartResult(
            AMISQueryVO qvo) throws FenixException {
        return amisDao.chartQueryResult(qvo.getSql());

    }

    public String export(AMISResultVO rvo) {

        // TODO: change it...
        System.out.println(" EXPORT TO FIX AMIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!?????????????????????????????");
        System.out.println(rvo);
        String filename = tableExcel.createExcel("", rvo.getExportTitle(), rvo.getExportTableHeaders(), rvo
                .getTableContents(), rvo.getStringColumnIndex(), rvo.getNumericColumnIndex());

        return filename;
    }

    private AMISResultVO createPivotTable(AMISQueryVO qvo, AMISResultVO rvo) {

        qvo.setSql(AMISFaostatQueryBuilder.pivotTable(qvo));

        LOGGER.info("START createTable");
        try {
            SQLServerDriver.class.newInstance();
            Connection c = DriverManager.getConnection(qvo
                    .getConnectionString(), qvo.getDbUserName(), qvo
                    .getDbPassword());
            Statement stmt = c.createStatement();
            LOGGER.info("EXECUTING QUERY...");
            stmt.executeQuery(qvo.getSql());
            LOGGER.info("FINISHED QUERY...CREATING ");
            ResultSet rs = stmt.getResultSet();

            AMISConstants type = AMISConstants.valueOf(qvo.getTypeOfOutput());
            //System.out.println("AMISServiceImpl Type" + type);
            switch (type) {
                case HTML:
                    AMISFaostatUtils.getHtmlPivotTable(rs, qvo, rvo, "pivot_table",
                            true);
                    LOGGER.info("ROWS: " + rvo.getRows());
                    break;
                case EXPORT_CSV:
                    String outputfile = exportOther(qvo);
                    // TODO: change with filepath
                    // rvo.setText(outputfile);
                    rvo.setExportFilename(outputfile);
                    break;


            }


            // check
            stmt.close();

            LOGGER.info("END createTable");
        } catch (SQLException e) {
            throw new FenixException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new FenixException(e.getMessage());
        } catch (Exception e) {
            throw new FenixException(e.getMessage());
        }

        return rvo;
    }

    // If there are dots ahead
    private List<String> getHeaders(List<String> selectFromSql) {
        List<String> header = new ArrayList<String>();
        int dot = 0;
        for (String select : selectFromSql) {
            if (select.contains(".")) {
                dot = select.indexOf(".");
                select = select.substring(dot + 1);
            }
            header.add(select);
        }
        return header;
    }






    private String exportOther(AMISQueryVO qvo) throws FenixException {
        String outputfile = "";

        LOGGER.info("START EXPORT OTHER " + qvo.getSql());
        try {
            // List<Object> res = amisDao.getSingleResultValue(qvo.getSql());
            List<Object[]> res = amisDao.getResultList(qvo.getSql());
            List<String> header = new ArrayList<String>();

            if(qvo.getTableHeaders().size() > 0){
                header = qvo.getTableHeaders();
            } else {
                header = qvo.getSelects();
            }
            //List<String> header = getHeaders(qvo.getSelects());

            if ((res == null) || (res.size() == 0)) {
                AMISUtils.getTable(res, null, qvo.getShowColumnHeaders(),
                        "flat_table", header);
                outputfile = "";
            } else {
                /** TODO have a sort of formatted selects fields **/
                outputfile = fileFactory.createCSVZipFileOther(res, "\"", ",",
                        false, header);

                LOGGER.info("outputfile: " + outputfile);
            }
            LOGGER.info("END EXPORT");
            // } catch (SQLException e) {
            // throw new FenixException(e.getMessage());
            // } catch (IllegalAccessException e) {
        } catch (Exception e) {
            throw new FenixException(e.getMessage());
        }

        return outputfile;
    }

    private String export(AMISQueryVO qvo) throws FenixException {
        String outputfile = "";

        LOGGER.info("START EXPORT");
        try {
            SQLServerDriver.class.newInstance();
            Connection c = DriverManager.getConnection(qvo
                    .getConnectionString(), qvo.getDbUserName(), qvo
                    .getDbPassword());
            Statement stmt = c.createStatement();
            LOGGER.info("EXECUTING QUERY...");
            stmt.executeQuery(qvo.getSql());
            LOGGER.info("FINISHED QUERY...CREATING CSV");
            ResultSet rs = stmt.getResultSet();

            /** TODO have a sort of formatted selects fields **/
            outputfile = fileFactory.createCSVZipFile(rs, "\"", ",", false);

            LOGGER.info("outputfile: " + outputfile);
            stmt.close();

            LOGGER.info("END EXPORT");
        } catch (SQLException e) {
            throw new FenixException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new FenixException(e.getMessage());
        } catch (Exception e) {
            throw new FenixException(e.getMessage());
        }

        return outputfile;
    }


    /** private String exportFoodBalance(AMISQueryVO qvo) throws FenixException {
     String outputfile = "";

     LOGGER.info("START FOOD BALANCE EXPORT");
     try {
     SQLServerDriver.class.newInstance();
     Connection c = DriverManager.getConnection(qvo
     .getConnectionString(), qvo.getDbUserName(), qvo
     .getDbPassword());
     Statement stmt = c.createStatement();
     LOGGER.info("EXECUTING QUERY...");
     stmt.executeQuery(qvo.getSql());
     LOGGER.info("FINISHED QUERY...CREATING CSV");
     ResultSet rs = stmt.getResultSet();

     outputfile = fileFactory.createCSVZipFile(rs, "\"", ",", false);

     LOGGER.info("outputfile: " + outputfile);
     stmt.close();

     LOGGER.info("END EXPORT");
     } catch (SQLException e) {
     throw new FenixException(e.getMessage());
     } catch (IllegalAccessException e) {
     throw new FenixException(e.getMessage());
     } catch (Exception e) {
     throw new FenixException(e.getMessage());
     }

     return outputfile;
     } **/

    /**
     * public AMISResultVO createTable(AMISQueryVO qvo, AMISDao amisDao,
     * AMISResultVO rvo) {
     *
     * LOGGER.info("createTable"); // List<String> singleValue = new
     * ArrayList<String>();
     *
     * // create measurement unit SQL query
     * qvo.setSql(AMISQueryBuilder.getMeasurementUnits(qvo, amisDao));
     *
     * if(qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT)){ List<String>
     * mus = querySingleResult(qvo); rvo.setMeasurementUnit(mus.get(0)); } else
     * { List<Object> mus = amisDao.getSingleResultValue(qvo.getSql());
     * rvo.setMeasurementUnit(mus.get(0).toString()); }
     *
     * if(qvo.getRunMaxDateQuery()) {
     * qvo.setSql(AMISFaostatQueryBuilder.sqlTableQuery(qvo));
     *
     * if(qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT)){ List<String>
     * singleValue = querySingleResult(qvo); for(String val: singleValue){
     * LOGGER.info("singleValue: " + val); qvo.setRunMaxDateQuery(false);
     * qvo.setMaxDateLimit(val); rvo.setMaxDateLimit(val);
     *
     * if(qvo.getTimeSpan()!=null) rvo.setTimeIntervalSpan(qvo.getTimeSpan());
     *
     * Map<String, String> years = new HashMap<String, String>(); years.put(val,
     * val); qvo.setYears(years); } } else { List<Object> singleValue =
     * amisDao.getSingleResultValue(qvo.getSql()); for(Object val: singleValue){
     * LOGGER.info("singleValue: " + val); qvo.setRunMaxDateQuery(false);
     * qvo.setMaxDateLimit(val.toString()); rvo.setMaxDateLimit(val.toString());
     *
     * if(qvo.getTimeSpan()!=null) rvo.setTimeIntervalSpan(qvo.getTimeSpan());
     *
     * Map<String, String> years = new HashMap<String, String>();
     * years.put(val.toString(), val.toString()); qvo.setYears(years); }
     *
     * }
     *
     *
     * // create the SQL query qvo.setSql(AMISQueryBuilder.sqlTableQuery(qvo));
     *
     * //rvo.setMeasurementUnit(mus.get(0)); createFlatTable(qvo, rvo);
     *
     * } else { // create the SQL query
     * qvo.setSql(AMISQueryBuilder.sqlTableQuery(qvo));
     *
     * //rvo.setMeasurementUnit(mus.get(0)); createFlatTable(qvo, rvo); }
     *
     *
     * return rvo; }
     **/
    private AMISResultVO createTableOriginalVersion(AMISQueryVO qvo,
                                                    AMISResultVO rvo) {

        LOGGER.info("createTable");
        List<String> singleValue = new ArrayList<String>();

        // create measurement unit SQL query
        qvo.setSql(AMISFaostatQueryBuilder.getMeasurementUnits(qvo));
        LOGGER.info("URL: " + qvo.getDbUrl());
        List<String> mus = querySingleResult(qvo);

        if (qvo.getRunMaxDateQuery()) {
            qvo.setSql(AMISFaostatQueryBuilder.sqlTableQuery(qvo));
            singleValue = querySingleResult(qvo);

            for (String val : singleValue) {
                LOGGER.info("singleValue: " + val);
                qvo.setRunMaxDateQuery(false);
                qvo.setMaxDateLimit(val);
                rvo.setMaxDateLimit(val);

                if (qvo.getTimeSpan() != null)
                    rvo.setTimeIntervalSpan(qvo.getTimeSpan());

                Map<String, String> years = new HashMap<String, String>();
                years.put(val, val);
                qvo.setYears(years);
            }

            // create the SQL query
            qvo.setSql(AMISFaostatQueryBuilder.sqlTableQuery(qvo));

            rvo.setMeasurementUnit(mus.get(0));
            createFlatTable(qvo, rvo);

        } else {
            // create the SQL query
            qvo.setSql(AMISFaostatQueryBuilder.sqlTableQuery(qvo));

            rvo.setMeasurementUnit(mus.get(0));
            createFlatTable(qvo, rvo);
        }

        return rvo;
    }

    // private List<String> queryAllSingleResult(AMISQueryVO qvo)
    // {
    // List<String> mus = null;
    //		
    // LOGGER.info("selected dataset: " + qvo.getSelectedDataset());
    //			
    // AMISConstants dataset = AMISConstants.valueOf(qvo.getSelectedDataset());
    //		
    // switch (dataset) {
    // case PSD: mus =
    // objectListToString(amisDao.getSingleResultValue(qvo.getSql())); break;
    // case FAOSTAT: mus = querySingleResult(qvo); break;
    // case CBS: mus =
    // objectListToString(amisDao.getSingleResultValue(qvo.getSql())); break;
    // }
    //		
    // return mus;
    // }

    private List<String> queryAllSingleResult(AMISQueryVO qvo) {
        List<String> mus = null;

        LOGGER.info("selected dataset: " + qvo.getSelectedDataset());

        mus = objectListToString(amisDao.getSingleResultValue(qvo.getSql()));
        return mus;
    }

    private List<String> objectListToString(List<Object> list) {
        List<String> listRes = new ArrayList<String>();
        for (Object obj : list) {
            listRes.add((String) obj);
        }
        return listRes;
    }


    private AMISResultVO createTable(AMISQueryVO qvo, AMISResultVO rvo) {
        LOGGER.info("--- createTable "+ qvo.getOutput() + " | " + qvo.getTypeOfOutput() );
        long startTime = System.currentTimeMillis();

        if(qvo.getTypeOfOutput()!=null && qvo.getTypeOfOutput().contains(AMISConstants.FOOD_BALANCE.name())){
            rvo = createSupplyDemandTable(qvo, rvo);
            System.out.println("Before exportExcelServiceCall!!!");
//            rvo = getSupplyDemandTable(qvo, rvo);

        }  else {
            rvo = createStandardTable(qvo, rvo);
        }

        long endTime = System.currentTimeMillis();

        // LOGGER.info("--- createTable "+ "Took " + (endTime - startTime) + " milliseconds");

        return rvo;
    }

    private AMISResultVO createSupplyDemandTable(AMISQueryVO qvo, AMISResultVO rvo)  {
        // LOGGER.info("------ createFoodBalanaceTable START ");

        //Reset the years and elements
        qvo.setYears(new HashMap<String, String>());
        qvo.setElements(new HashMap<String, String>());

        //Query to get Base Years (based on CBS)
        //qvo = FoodBalanceQueryBuilder.getBaseYears(qvo, amisDao);
        //LOGGER.info("createSupplyDemandTable getBaseYears QUERY " + qvo.getSql());

        qvo = FoodBalanceQueryBuilder.getCountryBaseYears(qvo, amisDao);
        LOGGER.info("createSupplyDemandTable getCountryBaseYears QUERY " + qvo.getSql());
        System.out.println("createSupplyDemandTable getCountryBaseYears QUERY " + qvo.getCountryDates());

        //Query to get Elements for country, year selection
        qvo = FoodBalanceQueryBuilder.getFoodBalanceElements(qvo, amisDao);
        rvo = buildTableQuery(qvo, rvo);

        LOGGER.info("createSupplyDemandTable getFoodBalanceElements QUERY " + qvo.getSql());
        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Map<String, Double>>>> foodBalanceResult = queryTableResultFoodBalance2(qvo);

        //Query to get ITY Elements for country, year selection
        qvo = FoodBalanceQueryBuilder.getITYElements(qvo, amisDao);
        rvo = buildTableQuery(qvo, rvo);

        LOGGER.info("createSupplyDemandTable getITYElements QUERY " + qvo.getSql());
        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Map<String, Double>>>> ityResult = queryTableResultFoodBalance2(qvo);


        //Query to get Elements for country, year selection
        qvo = FoodBalanceQueryBuilder.getOtherFoodBalanceElements(qvo, amisDao);
        rvo = buildTableQuery(qvo, rvo);


        LOGGER.info("createSupplyDemandTable ityResult = " + ityResult);
        LOGGER.info("createSupplyDemandTable getOtherFoodBalanceElements QUERY " + qvo.getSql());
        LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Map<String, Double>>>> otherResult = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, Map<String, Double>>>>();

        otherResult.clear();

        if(qvo.getSql()!=null){
            otherResult =   queryTableResultFoodBalance2(qvo);
        }

        LOGGER.info("createSupplyDemandTable otherResult QUERY " + otherResult);

        qvo = FoodBalanceQueryBuilder.getMarketingAndTradeYear(qvo, amisDao);

        qvo = FoodBalanceQueryBuilder.getAMISDataLastUpdated(qvo, amisDao);

        if(qvo.getDatabases().containsKey(AMISConstants.NATIONAL.name())) {
            //Get DataSource For National Data (includes last updated)
            qvo = FoodBalanceQueryBuilder.getNationalDataSource(qvo, amisDao);
        }

        //Get Footnotes
        qvo =  FoodBalanceQueryBuilder.getElementFootnotes(qvo, amisDao);


        //Get Population Details
        qvo = FoodBalanceQueryBuilder.getPopulationValues(qvo, amisDao);


        try {
            AMISConstants type = AMISConstants.valueOf(qvo.getTypeOfOutput());
            switch (type) {
                case HTML:
                    break;
                case EXPORT_EXCEL_FOOD_BALANCE:
                    long startTime = System.currentTimeMillis();

                    String file = getSupplyDemandExcel(qvo, rvo, foodBalanceResult, otherResult, ityResult );
                    rvo.setExportFilename(file);
                    long endTime = System.currentTimeMillis();

                    break;
            }

        } catch (Exception e) {
            throw new FenixException(e.getMessage());
        }


        return rvo;

    }

    private AMISResultVO getSupplyDemandTable(AMISQueryVO qvo, AMISResultVO rvo)  {
        LOGGER.info("============== getSupplyDemandTable START ...");
        try {
            AMISConstants type = AMISConstants.valueOf(qvo.getTypeOfOutput());
            switch (type) {
                case HTML:
                    break;
                case EXPORT_EXCEL_FOOD_BALANCE:
                    long startTime = System.currentTimeMillis();
                    LOGGER.info("============== getSupplyDemandTable 1 ...");

                    String file = sendSupplyDemandRequest(qvo);
                    rvo.setExportFilename(file);

                    break;
            }

        } catch (Exception e) {
            throw new FenixException(e.getMessage());
        }


        return rvo;

    }

    private AMISResultVO buildTableQuery(AMISQueryVO qvo, AMISResultVO rvo){
        List<String> singleValue = new ArrayList<String>();

        // create measurement unit SQL query
        if(AMISQueryBuilder.getMeasurementUnits(qvo, amisDao)!=null) {
            LOGGER.info("buildTableQuery: setMeasurementUnit: NOT NULL ");

            qvo.setSql(AMISQueryBuilder.getMeasurementUnits(qvo, amisDao));
            List<String> mus = queryAllSingleResult(qvo);
            LOGGER.info("URL: " + qvo.getDbUrl());
            LOGGER.info("create table sql: " + qvo.getSql());

            // create the SQL query
            // qvo.setSql(AMISFaostatQueryBuilder.sqlTableQuery(qvo));
          //  qvo.setSql(AMISQueryBuilder.sqlTableQuerySupplyAndDemand(qvo, true, amisDao));
            qvo.setSql(AMISQueryBuilder.sqlTableQuerySupplyAndDemand(qvo, true));

            rvo.setMeasurementUnit(mus.get(0));
        } else {
            LOGGER.info("buildTableQuery: setMeasurementUnit: NULL ");
            qvo.setSql(null);
            rvo.setMeasurementUnit(null);
        }

        return rvo;
    }

    private AMISResultVO createStandardTable(AMISQueryVO qvo, AMISResultVO rvo) {
        //LOGGER.info("AmisServiceImpl createStandardTable Dataset"+ qvo.getOutput() + " | " + qvo.getTypeOfOutput() );

        List<String> singleValue = new ArrayList<String>();

        // create measurement unit SQL query
        qvo.setSql(AMISQueryBuilder.getMeasurementUnits(qvo, amisDao));
        List<String> mus = queryAllSingleResult(qvo);
        LOGGER.info("createStandardTable URL: " + qvo.getDbUrl());
        LOGGER.info("createStandardTable create table sql: " + qvo.getSql());

        if (qvo.getRunMaxDateQuery()) {
            qvo.setSql(AMISQueryBuilder.sqlTableQuery(qvo, true, amisDao));
            singleValue = queryAllSingleResult(qvo);
            // singleValue = amisDao.getSingleResultValue(qvo.getSql());

            for (String val : singleValue) {
                LOGGER.info("singleValue: " + val);
                qvo.setRunMaxDateQuery(false);
                qvo.setMaxDateLimit(val);
                rvo.setMaxDateLimit(val);

                if (qvo.getTimeSpan() != null)
                    rvo.setTimeIntervalSpan(qvo.getTimeSpan());

                Map<String, String> years = new HashMap<String, String>();
                years.put(val, val);
                qvo.setYears(years);
            }

            // create the SQL query
            // qvo.setSql(AMISFaostatQueryBuilder.sqlTableQuery(qvo));
            qvo.setSql(AMISQueryBuilder.sqlTableQuery(qvo, true, amisDao));

            rvo.setMeasurementUnit(mus.get(0));
            createAllFlatTable(qvo, rvo);

        } else {
            // create the SQL query
            // qvo.setSql(AMISFaostatQueryBuilder.sqlTableQuery(qvo));
            qvo.setSql(AMISQueryBuilder.sqlTableQuery(qvo, true, amisDao));

            rvo.setMeasurementUnit(mus.get(0));
            createAllFlatTable(qvo, rvo);
        }

        return rvo;
    }

    private List<String> querySingleResult(AMISQueryVO qvo)
            throws FenixException {
        List<String> values = new ArrayList<String>();
        try {
            SQLServerDriver.class.newInstance();
            Connection c = DriverManager.getConnection(qvo
                    .getConnectionString(), qvo.getDbUserName(), qvo
                    .getDbPassword());
            Statement stmt = c.createStatement();
            stmt.executeQuery(qvo.getSql());
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                values.add(rs.getString(1));
            }

            stmt.close();
        } catch (SQLException e) {
            throw new FenixException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new FenixException(e.getMessage());
        } catch (InstantiationException e) {
            throw new FenixException(e.getMessage());
        }

        return values;
    }

    // For PSD and CCBS
    private void createAllFlatTable(AMISQueryVO qvo, AMISResultVO rvo)
            throws FenixException {

        LOGGER.info("START createAllFlatTable " + qvo.getSql());
        LOGGER.info("START createAllFlatTable Headers " + qvo.getShowColumnHeaders());
        try {
            List<Object[]> res = amisDao.getResultList(qvo.getSql());

            List<String> header = new ArrayList<String>();

            if(qvo.getTableHeaders().size() > 0){
                header = qvo.getTableHeaders();
            } else {
                header = qvo.getSelects();
            }

            //List<String> header = getHeaders(qvo.getSelects());
            AMISConstants type = null;
            // AMISConstants type = AMISConstants.valueOf(qvo.getTypeOfOutput());
            if(qvo.getTypeOfOutput()!=null){
                type = AMISConstants.valueOf(qvo.getTypeOfOutput());
            }
            else {
                type = AMISConstants.valueOf("EXPORT_CSV");
            }

            if ((res == null) || (res.size() == 0)) {
                AMISUtils.getTable(res, rvo, qvo.getShowColumnHeaders(),
                        "flat_table", header);
                if (type.toString().equalsIgnoreCase("EXPORT_CSV")) {
                    rvo.setExportFilename("");
                }
            } else {
                /** TODO have a sort of formatted selects fields **/

                switch (type) {
                    case HTML:
                        AMISUtils.getHtmlTable(res, rvo,
                                qvo.getShowColumnHeaders(), "flat_table", header);
                        LOGGER.info("ROWS: " + rvo.getRows());
                        break;
                    case EXPORT_CSV:
                        String outputfile = exportOther(qvo);
                        // TODO: change with filepath
                        // rvo.setText(outputfile);
                        rvo.setExportFilename(outputfile);
                        break;
                    //  case EXPORT_EXCEL_FOOD_BALANCE:
                    //     String file = getFoodBalanceTable(qvo, rvo);

                    //String outputfile = exportOther(qvo);
                    // TODO: change with filepath
                    // rvo.setText(outputfile);
                    //     rvo.setExportFilename(file);
                    //      break;
                }
            }

            LOGGER.info("END createAllFlatTable");
            // } catch (SQLException e) {
            // throw new FenixException(e.getMessage());
            // } catch (IllegalAccessException e) {
            // throw new FenixException(e.getMessage());
        } catch (Exception e) {
            throw new FenixException(e.getMessage());
        }

    }

    private void createFlatTable(AMISQueryVO qvo, AMISResultVO rvo)
            throws FenixException {

        LOGGER.info("START createTable " + qvo.getSql());
        try {
            SQLServerDriver.class.newInstance();
            Connection c = DriverManager.getConnection(qvo
                    .getConnectionString(), qvo.getDbUserName(), qvo
                    .getDbPassword());
            Statement stmt = c.createStatement();
            LOGGER.info("EXECUTING QUERY...");
            stmt.executeQuery(qvo.getSql());
            LOGGER.info("FINISHED QUERY...CREATING ");
            ResultSet rs = stmt.getResultSet();
            /** TODO have a sort of formatted selects fields **/
            AMISConstants type = AMISConstants.valueOf(qvo.getTypeOfOutput());
            switch (type) {
                case HTML:
                    AMISFaostatUtils.getHtmlTable(rs, rvo, qvo
                            .getShowColumnHeaders(), "flat_table");
                    LOGGER.info("ROWS: " + rvo.getRows());
                    break;
                case EXPORT_CSV:
                    String outputfile = export(qvo);
                    // TODO: change with filepath
                    // rvo.setText(outputfile);
                    rvo.setExportFilename(outputfile);
                    break;
                //case EXPORT_EXCEL_FOOD_BALANCE:
                //    String excelFileName =  getFoodBalanceTable(qvo, rvo);
                //String excelFileName =exportFoodBalance(qvo);
                //  rvo.setExportFilename(excelFileName);
                // break;
                //}
            }
            stmt.close();

            LOGGER.info("END createTable");
        } catch (SQLException e) {
            throw new FenixException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new FenixException(e.getMessage());
        } catch (Exception e) {
            throw new FenixException(e.getMessage());
        }

    }

    private void createCerealBalanceTable(AMISQueryVO qvo, AMISResultVO rvo, LinkedHashMap<String, Map<String,String>> values) throws FenixException {

        //LOGGER.info("#### VALUES "+values.isEmpty());


        if(!values.isEmpty()){



            //	 {Domestic use MT={2010=2.923E9, 2009=2.951E9, 2008=2.876E9, 2007=2.732E9, 2006=2.641E9, 2005=2.668E9, 2004=2.637E9, 2003=2.533E9, 2002=2.589E9, 2001=2.677E9, 2000=2.656E9, 1999=2.684E9, 1998=2.639E9, 1997=2.58E9, 1996=2.516E9, 1995=2.397E9, 1994=2.461E9, 1993=2.446E9, 1992=2.495E9, 1991=2.436E9, 1990=2.422E9}, Exports MT={2010=2.7E8, 2009=2.874E8, 2008=2.838E8, 2007=2.751E8, 2006=2.598E8, 2005=2.539E8, 2004=2.41E8, 2003=2.394E8, 2002=2.366E8, 2001=2.349E8, 2000=2.299E8, 1999=2.412E8, 1998=2.207E8, 1997=2.172E8, 1996=2.195E8, 1995=2.171E8, 1994=2.164E8, 1993=2.137E8, 1992=2.261E8, 1991=2.256E8, 1990=2.118E8}, Closing Stocks MT={2010=4.235E8, 2009=4.861E8, 2008=4.517E8, 2007=3.679E8, 2006=3.448E8, 2005=3.921E8, 2004=4.062E8, 2003=3.581E8, 2002=4.433E8, 2001=5.364E8, 2000=5.661E8, 1999=5.864E8, 1998=5.816E8, 1997=5.425E8, 1996=4.876E8, 1995=4.392E8, 1994=4.811E8, 1993=4.881E8, 1992=5.265E8, 1991=4.915E8, 1990=4.984E8}, Production MT={2010=2.17E9, 2009=2.222E9, 2008=2.234E9, 2007=2.117E9, 2006=1.995E9, 2005=2.017E9, 2004=2.043E9, 2003=1.864E9, 2002=1.821E9, 2001=1.88E9, 2000=1.846E9, 1999=1.874E9, 1998=1.877E9, 1997=1.879E9, 1996=1.872E9, 1995=1.712E9, 1994=1.761E9, 1993=1.719E9, 1992=1.796E9, 1991=1.722E9, 1990=1.779E9}} 
            List<String> headerList = new ArrayList<String>();
            List<String> exportHeaderList = new ArrayList<String>();
            LinkedHashMap<String, String> headers = new LinkedHashMap<String, String>();

            //HashMap<String, String> years = new LinkedHasMap<String, String>();

            exportHeaderList.add("Elements");

            for(String key : values.keySet()) {
                for(String value : values.get(key).keySet()) {
                    headers.put(value, value);
                }
            }


            Map<String, String> years = new LinkedHashMap<String, String>();



            for(String header : headers.keySet()) {
                //	LOGGER.info("header " + header);
                //String year = header.substring( 0, header.length() - 2 );   
                String year = header.substring(header.length() - 2);
                String nextYear = String.valueOf(Integer.valueOf(year) + 1);
                String title = header + "/";
                if (year.charAt(0) == '0' && !year.equals("09")) {
                    title+= "0"+nextYear;
                } else
                    title+= nextYear;

                //LOGGER.info("year " + year);
                //String nextYear = String.valueOf(Integer.valueOf(year) + 1);
                //LOGGER.info("nextYear " + nextYear);

                //if (nextYear.charAt(0) == '0')

                //String title = header + "/"+nextYear;
                //LOGGER.info("title " + title);

                years.put(header, title);
                headerList.add(title);
                exportHeaderList.add(title);

                //	LOGGER.info("header " + header);
                //String year = header.substring( 0, header.length() - 2 );   
                //headerList.add(title);
            }

            //	LOGGER.info("headerList size " + headerList.size());

            rvo.setTableHeaders(headerList);
            rvo.setExportTableHeaders(exportHeaderList);

            List<List<String>> rows = new ArrayList<List<String>>();

            for(String rowLabel : values.keySet()){
                //LOGGER.info("rowLabel '" + rowLabel + "'");
            }

            /**HashMap<String, String> labelsMap = new LinkedHashMap<String, String>();

             labelsMap.put("Production", "Production");
             labelsMap.put("Domestic use", "Utilization");
             labelsMap.put("Exports", "Trade");
             labelsMap.put("Closing Stocks", "Ending Stocks");**/
            //Start hashmap
//    		HashMap<String, String> labelsMap = new LinkedHashMap<String, String>();
//    		labelsMap.put("Production", "Production");
////    		labelsMap.put("Supply", "Supply");
////    		labelsMap.put("Utilization", "Utilization");
//    		labelsMap.put("Supply", "Domestic Availability");
//    		labelsMap.put("Utilization", "Domestic Utilization");
//    	  
//    		if(qvo.getAreas()!=null && 	qvo.getAreas().containsKey("999000")) { //GLOBAL
//    			labelsMap.put("Exports", "Trade (Exports)");
//    		} else {
//    			labelsMap.put("Exports (NMY)", "Exports (NMY)");
//    			labelsMap.put("Imports (NMY)", "Imports (NMY)");
//    		}
            //End hashmap
            HashMap<String, String> labelsMap = new LinkedHashMap<String, String>();

//    		labelsMap.put("Supply", "Supply");
//    		labelsMap.put("Utilization", "Utilization");



            if(qvo.getAreas()!=null && 	qvo.getAreas().containsKey("999000")) { //GLOBAL
                //labelsMap.put("Production", "Production <sup>[1]</sup> <p title=\"Prova1\">W3Schools.com</p>");
//    			labelsMap.put("Production", "Production <sup>[1]</sup> title=\"Prova1\"");
                //labelsMap.put("Supply", "<p title=\"Prova2\">Supply</p> <sup>[2]</sup>");

                labelsMap.put("Production", "<p title=\"Production (except for soybeans) refers to the first year of the split year shown. Rice production is expressed in milled terms.\">Production<sup>[1]</sup></p> ");
//                labelsMap.put("Total Supply", "<p title=\"Supply equals production plus opening stocks.\">Supply<sup>[2]</sup></p> ");
                labelsMap.put("Domestic Supply", "<p title=\"Supply equals production plus opening stocks.\">Domestic Supply<sup>[2]</sup></p> ");
//                labelsMap.put("Domestic Utilization", "Domestic Utilization");
                labelsMap.put("Domestic Utilization", "Domestic Utilization");
                //labelsMap .put("Utilization", "Utilization <sup>[3]</sup> title=\"Prova3\"");
//    			labelsMap.put("Exports", "Trade (Exports) <sup>[4]</sup> title=\"Prova4\"");
//    			labelsMap.put("Closing Stocks", "Closing Stocks <sup>[5]</sup> title=\"Prova5\"");
                //labelsMap.put("Exports", "Trade (Exports) <sup>[3]</sup> title=\" Trade refers to exports on a July/June season for wheat and maize, October/September for soybean and January/December (calendar) for rice (corresponding to second year of the split year shown). For the forecast year, world exports and imports are always assumed to be equal.\"");
                labelsMap.put("Exports (ITY)", "<p title=\"Trade refers to the following export seasons: July/June for wheat and maize, October/September for soybean and January/December (calendar) for rice (corresponding to second year of the split year shown). For the forecast year, world exports and imports are always assumed to be equal.\">Trade (Exports)<sup>[3]</sup></p> ");
                //html.append("<TD nowrap align=\"left\">" + "\"Trade\" refers to the following export seasons: July/June for wheat and maize, October/September for soybean and January/December (calendar) for rice (corresponding to second year of the split year shown).For the forecast year, world exports and imports are always assumed to be equal." + "</TD>");
                labelsMap.put("Closing Stocks", "<p title=\"Closing Stocks may not equal the difference between supply and utilization due to differences in individual national marketing years.\">Closing Stocks<sup>[4]</sup></p> ");

            } else {
                labelsMap.put("Production", "<p title=\"Production refers to the full amount of the harvest before any deductions are taken for post-harvest losses, seed use, etc.\">Production<sup>[1]</sup></p> ");
//                labelsMap.put("Total Supply", "<p title=\"Domestic Availability refers to Opening Stocks (quantity of stocks held at the beginning of the marketing year) plus Production.\">Domestic Availability<sup>[2]</sup></p> ");
                labelsMap.put("Domestic Supply", "<p title=\"Supply refers to Opening Stocks (quantity of stocks held at the beginning of the marketing year) plus Production.\">Domestic Supply<sup>[2]</sup></p> ");
                labelsMap.put("Domestic Utilization", "<p title=\"Utilization includes food, feed and other uses.\">Domestic Utilization<sup>[3]</sup></p> ");
                labelsMap.put("Exports (NMY)", "<p title=\"Trade (imports and/or exports) is based on the national marketing year (NMY).\">Exports (NMY)<sup>[4]</sup></p> ");
                labelsMap.put("Imports (NMY)", "<p title=\"Trade (imports and/or exports) is based on the national marketing year (NMY).\">Imports (NMY)<sup>[4]</sup></p> ");
                labelsMap.put("Closing Stocks", "<p title=\"Closing stocks refers to stocks at the end of the marketing year.\">Closing Stocks<sup>[5]</sup></p> ");
            }

            //int i = 0;
            for(String modifiedRowLabel: labelsMap.keySet()) {
                List<String> rowValues = new ArrayList<String>();
                rowValues.add(labelsMap.get(modifiedRowLabel));
                //LOGGER.info("modifiedRowLabel " + modifiedRowLabel);


                //for(String rowLabel : values.keySet()) {
                //if(rowLabel.equals(modifiedRowLabel)){

                for(String headerValue : years.keySet()) {
                    //LOGGER.info("headerValue " + headerValue);
                    //	LOGGER.info("modifiedLabel '" + modifiedRowLabel+"'");


                    //if(values.get(modifiedRowLabel)==null) {
                    //	LOGGER.info("values.get(modifiedRowLabel) IS NULL ");
                    //rowValues.add("");
                    //break;
                    //}

                    //LOGGER.info("row: " + modifiedRowLabel + ": header "+headerValue + " value " +  values.get(modifiedRowLabel).get(headerValue).toString());

                    if(values.get(modifiedRowLabel) != null) {
                        //	LOGGER.info("values.get(modifiedRowLabel) IS NOT NULL ");

                        if(!values.get(modifiedRowLabel).containsKey(headerValue))		{
                            //	LOGGER.info("values.get(modifiedRowLabel).get(headerValue) IS EMPTY ");  
                            rowValues.add("");
                        }
                        else{
                            //LOGGER.info("values.get(modifiedRowLabel).get(headerValue).toString() " + values.get(modifiedRowLabel).get(headerValue).toString());              			

                            if(values.get(modifiedRowLabel).get(headerValue).toString()!=null && values.get(modifiedRowLabel).get(headerValue).toString()!="0.00")
                                rowValues.add(values.get(modifiedRowLabel).get(headerValue).toString());
                            else
                                rowValues.add("");
                        }
                    }else {
                        //rowValues.add(values.get(modifiedRowLabel).get(headerValue).toString());        	
                        rowValues.add("");
                    }

                }
                //	}
                //LOGGER.info("rowValues: " + rowValues.size() + " : " +rowValues);

                rows.add(rowValues);

                //i++;	
                //}	
            }




            /**for(String rowLabel : values.keySet()) {
             List<String> rowValues = new ArrayList<String>();
             rowValues.add(rowLabel);

             for(String headerValue : years.keySet()) {
             //LOGGER.info("row: " + row + ": header "+headerValue + " value " +  values.get(row).get(headerValue).toString());

             if(values.get(rowLabel).get(headerValue).toString()!=null)
             rowValues.add(values.get(rowLabel).get(headerValue).toString());        				
             else
             rowValues.add("");

             }
             LOGGER.info("rowValues: " + rowValues.size() + " : " +rowValues);

             rows.add(rowValues);


             }	**/

            //LOGGER.info("rows: " + rows);

            rvo.setTableContents(rows);
            rvo.setStringColumnIndex(0); // first column string and all other columns numeric


            //rvo.setTableContents(rows);
        }
        else {
            rvo.setTableHeaders(null);
            rvo.setExportTableHeaders(null);
            rvo.setTableContents(null);
            qvo.setShowColumnHeaders(false);
            rvo.setStringColumnIndex(null);
        }

        AMISConstants type = AMISConstants.valueOf(qvo.getTypeOfOutput());
        switch (type) {
            case HTML:
                AMISHtmlUtils.getHtmlTable(rvo, qvo.getShowColumnHeaders(), "cereal_table");
                LOGGER.info("ROWS: " + rvo.getRows());
                break;
            case EXPORT_CSV: String outputfile = export(qvo);
                // TODO: change with filepath
                //rvo.setText(outputfile);
                rvo.setExportFilename(outputfile);
                break;
        }

    }




    private void createFlatTableNew(AMISQueryVO qvo, AMISResultVO rvo)
            throws FenixException {

        if (qvo.getSelectedDataset().equals(AMISConstants.FAOSTAT)) {
            LOGGER.info("START createTable");
            try {
                SQLServerDriver.class.newInstance();
                Connection c = DriverManager.getConnection(qvo
                        .getConnectionString(), qvo.getDbUserName(), qvo
                        .getDbPassword());
                Statement stmt = c.createStatement();
                LOGGER.info("EXECUTING QUERY...");
                stmt.executeQuery(qvo.getSql());
                LOGGER.info("FINISHED QUERY...CREATING ");
                ResultSet rs = stmt.getResultSet();

                /** TODO have a sort of formatted selects fields **/
                AMISConstants type = AMISConstants.valueOf(qvo
                        .getTypeOfOutput());
                switch (type) {
                    case HTML:
                        AMISFaostatUtils.getHtmlTable(rs, rvo, qvo
                                .getShowColumnHeaders(), "flat_table");
                        LOGGER.info("ROWS: " + rvo.getRows());
                        break;
                    case EXPORT_CSV:
                        String outputfile = export(qvo);
                        // TODO: change with filepath
                        // rvo.setText(outputfile);
                        rvo.setExportFilename(outputfile);
                        break;
                }

                stmt.close();

                LOGGER.info("END createTable");
            } catch (SQLException e) {
                throw new FenixException(e.getMessage());
            } catch (IllegalAccessException e) {
                throw new FenixException(e.getMessage());
            } catch (Exception e) {
                throw new FenixException(e.getMessage());
            }
        } else {

        }

    }

    // CCBS tool Start
    public Integer populateCCBS() {
        try {
            // TODO: replace with real code
            return 0;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        }
    }

    public List<HashMap<String, String>> getDataFromAnElement(AMISQueryVO qvo, String oneElementName, String lastMonthMarketingyear)
    {
        //System.out.println("Class: AmisserviceImpl Function: getDataFromAnElement Text: Start qvo.getSelectedDataset() "+ qvo.getSelectedDataset()+"qvo.getSelectedDatasetFile() "+ qvo.getSelectedDatasetFile());
        //Calculating the month to take(the last forecast)
        List<Object[]> sqlResultForLastForecast = AMISGetCodingSystem.getTimeSeriesForMonthForecast(qvo,oneElementName,amisDao);
//		for(Object obj[] : sqlResultForLastForecast)
//		{
//			System.out.println("Class: AmisserviceImpl Function: getDataFromAnElement Text: Month:  "+obj[0]+" Year:  "+obj[2]);
//		}
        List<HashMap<String, String>> monthForecastToQuery = aMISCbsServerUtility.lastMonthForecastForYear(sqlResultForLastForecast, lastMonthMarketingyear);
//		for(HashMap<String, String> hashMap : monthForecastToQuery)
//		{
//			Set<String> keySet = hashMap.keySet();
////			for(int j=0; j<keySet.size(); j++)
////			{
////				System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: BEFORE REMOVE Year= "+ keySet.);
////			}
//			Iterator it= keySet.iterator();
//			while(it.hasNext())
//			{
//				String label = (String)it.next();
//				System.out.println("Class: AmisserviceImpl Function: getDataFromAnElement Text: monthForecastToQuery label= "+ label+" hashMap.get(label) " +hashMap.get(label));
//			}
//		}
        return monthForecastToQuery;
    }

    public AMISResultVO getYearsWithTheBestMonthForForecast(AMISQueryVO qvo, List<HashMap<String, String>> monthForecastToQuery)
    {
        // outputs
        AMISResultVO rvo = new AMISResultVO();
        // set AMISResultVO
        //	rvo.setOutput(qvo.getOutput());
        AMISResultVO rvoRes = AMISGetCodingSystem.getRangeOfYears(qvo, rvo, amisDao);

        //System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: monthForecastToQuery.size()= "+ monthForecastToQuery.size());
//		for(int i=0; i<monthForecastToQuery.size(); i++) {
//			HashMap<String, String> hashMap = monthForecastToQuery.get(i);
//			Set<String> keySet = hashMap.keySet();
////			for(int j=0; j<keySet.size(); j++)
////			{
////				System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: BEFORE REMOVE Year= "+ keySet.);
////			}
//			Iterator it= keySet.iterator();
//			while(it.hasNext())
//			{
//				String label = (String)it.next();
//				System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: monthForecastToQuery label= "+ label+" hashMap.get(label) " +hashMap.get(label));
//			}
//			
//		}

//		for(AMISCodesModelData topcode : rvoRes.getCodes()) {
//			System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: BEFORE REMOVE Year= "+topcode.getLabel()+" Month= "+topcode.getCode());
//		}

        List<AMISCodesModelData> indexToEliminate = new ArrayList();

        for(AMISCodesModelData topcode : rvoRes.getCodes()) {
            //Label=year
            //Code=month
            for(HashMap<String, String> hashMap:monthForecastToQuery)
            {
                if(hashMap.get(topcode.getLabel())!=null)
                {
                    String month = hashMap.get(topcode.getLabel());
                    if(!(month.equalsIgnoreCase(topcode.getCode())))
                    {
                        indexToEliminate.add(topcode);
                    }
                }
            }
        }
        //To delete the objects
        for(AMISCodesModelData element : indexToEliminate)
        {
            if(element!=null)
            {
                rvoRes.getCodes().remove(element);
            }
        }

//		for(AMISCodesModelData topcode : rvoRes.getCodes()) {
//			System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: AFTER REMOVE Year= "+topcode.getLabel()+" Month= "+topcode.getCode());
//		}
        return rvoRes;
    }

    //This function is used to fill the combo box with in the North Panel of the Cbs Tool
    public AMISResultVO getYearsForComboBox(AMISQueryVO qvo)
    {
        // outputs
        AMISResultVO rvo = new AMISResultVO();
        // set AMISResultVO
        //	rvo.setOutput(qvo.getOutput());
        AMISResultVO rvoRes = AMISGetCodingSystem.getRangeOfYearsWithoutMonth(qvo, rvo, amisDao);
        return rvoRes;
    }

    public Book getCCBSData(AMISQueryVO qvo, List<String> years, int numberFirstYearsToShow, String lastMonthMarketingyear, String oneElementName, List<HashMap<String, String>> monthForecastToQuery) {
        //System.out.println("Class: AmisserviceImpl Function: getCCBSData Text Start");
        try {
            //Book book = new Book(CCBS.COMMODITY_CODES.length);
            Book book = new Book(CCBS.NUM_BOOK_PAGE);

            // prepare map from years to position
            Map<Integer, Integer> year2Idx = new HashMap<Integer, Integer>();
            int yearIndex = 0;
            for (int i = numberFirstYearsToShow; i < years.size(); i++) {
                // System.out.println("year2Idx: " + years[i] + " --> " + i *
                // 2); // DEBUG
                //year2Idx.put(Integer.parseInt(parseYear(years.get(i))), i * 2);
                year2Idx.put(Integer.parseInt(parseYear(years.get(i))), yearIndex * 2);
                yearIndex++;
            }
            Set<Integer> keySet = year2Idx.keySet();
//			for(Integer key : keySet)
//			{
//		//		System.out.println("Class: AmisServiceImp Function: getCCBSData Text: key "+key+" year2Idx.get(key) "+year2Idx.get(key));
//			}
            // fill result book
            CCBS.Codes codes[] = CCBS.Codes.values();
            for (int pageNum = 0; pageNum < CCBS.NUM_BOOK_PAGE; pageNum++) {
                Page page = new Page(codes.length, (CCBS.NUMBER_OF_YEARS+1) * 2);
                book.setPage(pageNum, page);
                //String commodityCode = CCBS.COMMODITY_CODES[pageNum];

                String commodityCode = qvo.getCommodityCode();
                //To take the hashMap for each year and the best month
                List<Object[]> sqlResultForEachYear = new ArrayList<Object[]>();
                if(!lastMonthMarketingyear.equalsIgnoreCase(CCBS.N_A_MONTH))
                {
                    //Calculating the month to take(the last forecast)
//				List<Object[]> sqlResultForLastForecast = AMISGetCodingSystem.getTimeSeriesForMonthForecast(qvo,oneElementName,amisDao);
//				for(Object obj[] : sqlResultForLastForecast)
//				{
//					System.out.println("Month:  "+obj[0]+" Year:  "+obj[2]);
//				}
//				List<HashMap<String, String>> monthForecastToQuery = aMISCbsServerUtility.lastMonthForecastForYear(sqlResultForLastForecast, lastMonthMarketingyear);
                    for(HashMap<String, String> hashMap: monthForecastToQuery)
                    {
                        //System.out.println("New HASHMAP  ");
                        Set<String> key = hashMap.keySet();
                        Iterator it = key.iterator();
                        String yearKey = (String)it.next();
                        String month = hashMap.get(yearKey);
                        //System.out.println("Year= "+yearKey+" Month= "+month);
                    }
                    for(HashMap<String, String> hashMap: monthForecastToQuery)
                    {
                        Set<String> key = hashMap.keySet();
                        Iterator it = key.iterator();
                        String yearKey = (String)it.next();
                        String month = hashMap.get(yearKey);
                        List<Object[]> sqlResult = AMISGetCodingSystem.getTimeSeries(qvo, yearKey, month, amisDao);
                        if(sqlResult != null)
                        {
                            for(Object obj[]: sqlResult)
                            {
                                sqlResultForEachYear.add(obj);
                            }
                        }

                        List<Object[]> sqlResult2 = AMISGetCodingSystem.getTimeSeriesForPopulation(qvo, yearKey, month, amisDao);
                        if(sqlResult2 != null)
                        {
                            for(Object obj[]: sqlResult2)
                            {
                                sqlResultForEachYear.add(obj);
                            }
                        }
                    }
                }
                else
                {
                    //Case COARSE GRAIN and TOTAL CEREAL: there is no Marketing Year
                    String yearKey = null;
                    String month = null;
                    List<Object[]> sqlResult = AMISGetCodingSystem.getTimeSeries(qvo, yearKey, month, amisDao);
                    List<Object[]> sqlResultForPopulation = AMISGetCodingSystem.getTimeSeriesForPopulation(qvo, yearKey, month, amisDao);
                    //sqlResultTot = sqlResult + sqlResultForPopulation;
                    //List<Object[]> sqlResultTot = new ArrayList<Object[]>();
                    for(Object obj[]: sqlResult)
                    {
                        sqlResultForEachYear.add(obj);
                    }
                    for(Object obj[]: sqlResultForPopulation)
                    {
                        sqlResultForEachYear.add(obj);
                    }
                }
//			List<Object[]> sqlResultForPopulation = AMISGetCodingSystem.getTimeSeriesForPopulation(qvo, amisDao);
//			//sqlResultTot = sqlResult + sqlResultForPopulation;
//			List<Object[]> sqlResultTot = new ArrayList<Object[]>();
//			for(Object obj[]: sqlResult)
//			{
//				sqlResultTot.add(obj);
//			}
//			for(Object obj[]: sqlResultForPopulation)
//			{
//				sqlResultTot.add(obj);
//			}
                //TimeSeries rowArray[] = getListTimeSeries(qvo,sqlResultTot, codes);
                //System.out.println("Class: AmisServiceImpl Function: getCCBSData Text: After getListTimeSeries sqlResultForEachYear.size() "+ sqlResultForEachYear.size() );
                TimeSeries rowArray[] = getListTimeSeries(qvo,sqlResultForEachYear, codes);
                //System.out.println("Class: AmisServiceImpl Function: getCCBSData Text: After getListTimeSeries rowArray.size "+ rowArray.length );
                for (int rowNum = 0; rowNum < rowArray.length; rowNum++) {
                    fillRow(rowArray[rowNum], year2Idx, page, rowNum, numberFirstYearsToShow);
                }
                //		System.out.println("Class: AmisServiceImpl Function: getCCBSData Text: After fillRow" );
            }
//			Set<Integer> keySet2 = year2Idx.keySet();
//			for(Integer key : keySet2)
//			{
//				System.out.println("Class: AmisServiceImp Function: getCCBSData Text: key "+key+" year2Idx.get(key) "+year2Idx.get(key));
//			}
            // System.out.println("Book (Server):"); // DEBUG
            // System.out.println(book); // DEBUG
            //INIZIO DA ELIMINARE
            Page p = book.getPage(0);
            for(int i=0; i< p.numCols(); i++)
            {
                for(int j=0; j< p.numRows(); j++)
                {
                    String text = p.getCell(j, i).getText();
                    Double value = p.getCell(j, i).getValue();
                    //	   System.out.println("Class: AmisServiceImpl Function: getCCBSData Text:Before return ROW= "+j+" COL= "+i+ " text= "+ text+" value= "+value );
                }
            }
            //FINE DA ELIMINARE
            return book;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    //This function returns true if the datasetIsEmpty (true=data On Cbs)
    //or false if the dataset is full (false = the data don't have to be taken from Cbs)
    public AMISResultVO checkCountryDataForLoadingWindow(AMISQueryVO qvo) {
        //	System.out.println("Class: AmisserviceImpl Function: checkCountryDataForLoadingWindow Text Start");
        AMISResultVO rvo = new AMISResultVO();
        try {

            //Check if the dataset of the country is empty....
            List<Object[]> result = AMISGetCodingSystem.checkCountryData(qvo, amisDao);
            //rvo.setCbsResult(result);
            //If it's empty ...
            if((result == null)||(result.size() == 0))
            {
                rvo.setResultFromCbs(true);
            }
            else
            {
                //The dataset of the country is full
                rvo.setResultFromCbs(false);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

        return rvo;
    }

    //This function prepares all the information shown clicking on the countryInformation
    //button in the Cbs tool(marketing year and commodity crop for each commodity related with the selected country)
    public AMISResultVO getCountryInformation(AMISQueryVO qvo, List<String> commodityCodesCbs)
    {
        AMISResultVO aMISResultVO = new AMISResultVO();
        //For each commodity
        String country_code = qvo.getCountryCode();
        String marketingYearArray[] = new String[commodityCodesCbs.size()];
        String cropsNumArray[] = new String[commodityCodesCbs.size()];
        AMISCodesModelData aMISCodesModelData = new AMISCodesModelData();
        //commodity index
        int i =0;
        for(String commodity_code:commodityCodesCbs)
        {
            //To avoid the commodity Population
            if(!commodity_code.equals("9"))
            {
                qvo.setSelectedDataset("AMIS_MARKETING_YEAR");
                aMISCodesModelData = AMISGetCodingSystem.getMarketingYearCountryInformation(qvo, country_code, commodity_code, amisDao);
                //Store the start month
                if((aMISCodesModelData==null)||(aMISCodesModelData!=null)&&(aMISCodesModelData.getCode().equals("")))
                {
                    //There is no Marketing Year for the commodity...do not set the name because is not used
                    aMISCodesModelData.setLabel("Not Available");
                }
                marketingYearArray[i] = aMISCodesModelData.getLabel();
                qvo.setSelectedDataset("AMIS_COUNTRY_CROP_COUNT");

                aMISCodesModelData = AMISGetCodingSystem.getCropsNumCountryInformation(qvo, country_code, commodity_code, amisDao);
                //Store the crops num
                if((aMISCodesModelData==null)||(aMISCodesModelData!=null)&&(aMISCodesModelData.getCode().equals("")))
                {
                    //There is no Marketing Year for the commodity...do not set the name because is not used
                    aMISCodesModelData.setLabel("Not Available");
                }
                cropsNumArray[i] = aMISCodesModelData.getLabel();
            }
            i++;
        }
        aMISResultVO.setMarketingYearArray(marketingYearArray);
        aMISResultVO.setCropsNumArray(cropsNumArray);
        return aMISResultVO;
    }

    public AMISResultVO checkCountryData(AMISQueryVO qvo, boolean fill) {
        //	System.out.println("Class: AmisserviceImpl Function: checkCountryData Text Start");
        AMISResultVO rvo = new AMISResultVO();
        try {
            String selectedDatasetFile = qvo.getSelectedDatasetFile();
            //Check if the dataset of the country is empty....
            List<Object[]> result = AMISGetCodingSystem.checkCountryData(qvo, amisDao);
            //rvo.setCbsResult(result);
            //If it's empty ... take all the result from Cbs Dataset
            if((result == null)||(result.size() == 0))
            {
                AMISQueryVO qvoFill =  new AMISQueryVO();
                qvoFill.setSelectedDataset(qvo.getSelectedDataset());
                //Set in the rvo to show if the result are taken by Cbs
                qvo.setSelectedDatasetFile("AMIS");
                //Taking values from Cbs
                qvo.setSelectedDataset(AMISConstants.CBS.toString());
                List<Object[]> resultCbs = AMISGetCodingSystem.checkCountryData(qvo, amisDao);
                //rvo.setCbsResult(resultCbs);
                rvo.setResultFromCbs(true);

                //This service is used to fill the dataset of the country and to fill the CCBS.MONTH_FORECAST_TO_QUERY list
                if(fill)
                {
                    //Copy all the result to new dataset                		
                    //It's necessary to change the column of the Dataset because the values are taken by Cbs
                    //qvoFill.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
                    qvoFill.setSelectedDatasetFile(selectedDatasetFile);
                    qvoFill.setCbsResult(resultCbs);
                    fillCountryData(qvoFill);
                }
            }
            else
            {
                //The dataset of the country is full
                rvo.setResultFromCbs(false);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

        return rvo;
    }

    public Boolean fillCountryData(AMISQueryVO qvo) {
        //	System.out.println("Class: AmisserviceImpl Function: fillCountryData Text Start");
        //	AMISResultVO rvo = new AMISResultVO();
        Boolean ris = false;
        try
        {
            List<Object[]> cbsData = qvo.getCbsResult();

            //	System.out.println("Class: AmisserviceImpl Function: fillCountryData Text cbsData.size() "+ cbsData.size() + " qvo.getSelectedDataset() "+ qvo.getSelectedDataset());
            //Change the column of the Dataset to put the name of the Country Dataset
            for(int iList=0; iList<cbsData.size(); iList++)
            //for(int iList=0; iList<10; iList++)
            {
                //	System.out.println("Class: AmisserviceImpl Function: fillCountryData Text cbsData.size() "+ cbsData.size() + " cbsData.get(iList)[0] "+ cbsData.get(iList)[0]);
                //	cbsData.get(iList)[0] = qvo.getSelectedDataset();
                qvo.getCbsResult().get(iList)[0] = qvo.getSelectedDataset();
            }
            //	System.out.println("Class: AmisserviceImpl Function: fillCountryData Text qvo.getCbsResult().get(0)[0] "+ qvo.getCbsResult().get(0)[0]);
            //Insert all the values from Cbs Data Source to Country Data Source
            String tablename = AMISGetCodingSystem.getCountryDataTableName(qvo, amisDao);
            //To calculate the current year
            GregorianCalendar gc = new GregorianCalendar();
            Date d = gc.getTime();
            for(int iList=0; iList<cbsData.size(); iList++)
            //	for(int iList=0; iList<10; iList++)
            {
                ris = AMISGetCodingSystem.fillCountryData(qvo, iList, amisDao, tablename, d);
            }

            //	rvo.setDataInserted(ris);	

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

        return ris;
    }

    //This function is used to fill the hashmap that contains the information about closing, opening stocks
    //for every year
    public HashMap<String, HashMap<String , String>> getOpeningClosingStocksForYear(AMISQueryVO qvo, String oneElementName, String lastMonthMarketingyear) {
        //	System.out.println("Class: AmisserviceImpl Function: getOpeningClosingStocksForYear Text Start");
        List<HashMap<String, String>> listOfHM = getDataFromAnElement(qvo, oneElementName, lastMonthMarketingyear);

//		for(HashMap<String, String> hashMap : listOfHM)
//		{
//			Set<String> keySet = hashMap.keySet();
////			for(int j=0; j<keySet.size(); j++)
////			{
////				System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: BEFORE REMOVE Year= "+ keySet.);
////			}
//			Iterator it= keySet.iterator();
//			while(it.hasNext())
//			{
//				String label = (String)it.next();
//				System.out.println("Class: AmisserviceImpl Function: getOpeningClosingStocksForYear Text: monthForecastToQuery label= "+ label+" hashMap.get(label) " +hashMap.get(label));
//			}
//		}
        HashMap<String, HashMap<String , String>> ris = AMISGetCodingSystem.getOpeningClosingStocksForYear(qvo,amisDao);
        Set<String> keySet3 = ris.keySet();
        Iterator<String> it3 = keySet3.iterator();
        //	System.out.println("Class: AmisserviceImpl Function: getOpeningClosingStocksForYear Text : After AMISGetCodingSystem.getOpeningClosingStocksForYear");
        while(it3.hasNext())
        {
//			HashMap<String,String> hashmap = (HashMap<String,String>)it.next();
            String year = (String)it3.next();
            HashMap<String,String> hashmap = ris.get(year);
            Set<String> keySet2 = hashmap.keySet();
            Iterator<String> it2 = keySet2.iterator();
            while(it2.hasNext())
            {
                String code = it2.next();
                //System.out.println("Class: AmisserviceImpl Function: getOpeningClosingStocksForYear Text :ris year = "+ year +" code = "+code+" value = "+ hashmap.get(code));
            }
        }
        //It's necessary to return a hashmap where year is not year*month but just year
        HashMap<String, HashMap<String , String>> risToReturn = new HashMap<String, HashMap<String , String>>();

        for(HashMap<String, String> hMelement: listOfHM)
        {
            //Take the best month for a year		
            Set<String> hMelementkeySet = hMelement.keySet();
            Iterator it = hMelementkeySet.iterator();
            String year = (String)it.next();
            String bestMonth = hMelement.get(year);
            //System.out.println("Class: AmisserviceImpl Function: getOpeningClosingStocksForYear Text: monthForecastToQuery year= "+ year+" bestMonth " +bestMonth);
            //Select the right element from the list of ris that corresponds to that month
            HashMap<String , String> elementWithTheBestForecast = ris.get(year+"*"+bestMonth);
            //Setting default value for opening and closing stocks
            if(elementWithTheBestForecast==null)
            {
                elementWithTheBestForecast = new HashMap<String, String>();
                elementWithTheBestForecast.put(CCBS.OPENING_STOCKS_ELEMENT_CODE, "");
                elementWithTheBestForecast.put(CCBS.CLOSING_STOCKS_ELEMENT_CODE, "");
            }
            risToReturn.put(year, elementWithTheBestForecast);
        }
        Set<String> keySet = risToReturn.keySet();
        Iterator<String> it = keySet.iterator();
        //	System.out.println("Class: AmisserviceImpl Function: getOpeningClosingStocksForYear Text : before while");
        while(it.hasNext())
        {
//			HashMap<String,String> hashmap = (HashMap<String,String>)it.next();
            String year = (String)it.next();
            HashMap<String,String> hashmap = risToReturn.get(year);
            if(hashmap!= null)
            {
                Set<String> keySet2 = hashmap.keySet();
                Iterator<String> it2 = keySet2.iterator();
                while(it2.hasNext())
                {
                    String code = it2.next();
                    System.out.println("Class: AmisserviceImpl Function: getOpeningClosingStocksForYear Text :risToReturn year = "+ year +" code = "+code+" value = "+ hashmap.get(code));
                }
            }
            else
            {
                System.out.println("Class: AmisserviceImpl Function: getOpeningClosingStocksForYear Text :risToReturn year = "+ year +" hashmap = null");
            }
        }
//		
//		Set<String> externalHMkeySet = ris.keySet();
//		Iterator it = externalHMkeySet.iterator();
//		HashMap<String,String> hm;
//		String element = "";
//		while(it.hasNext())
//		{
//			element = (String)it.next();
//			hm = ris.get(element);
//			//year*month
//			String month = element.substring(5);
//			String year = element.substring(0,4);
//			
//			Set<String> internalHMkeySet = hm.keySet();
//			Iterator it2 = internalHMkeySet.iterator();
//			//Update the closing stocks value for that year
//			while(it2.hasNext())
//			{
//				String code = (String)it2.next();
//				if(code.equals(CCBS.CLOSING_STOCKS_ELEMENT_CODE))
//				{
//					//Second Element
//					openingClosingStocksValues[1] = hashmap.get(code);
//				}
//			}
//			if(element.equals(headerYear))
//			{
//				hm = CCBS.OPENING_CLOSING_STOCKS_HASHMAP.get(element);
//				hm.put(CCBS.CLOSING_STOCKS_ELEMENT_CODE, closingStocksValue);
//				found1 = true;
//				System.out.println("Class: PagePanel  Function: handleEvent Text: found1 = true closingStocksValue "+ closingStocksValue);
////				Set<String> internalHMkeySet = hm.keySet();
////				Iterator it2 = internalHMkeySet.iterator();
////				//Update the closing stocks value for that year
////				while(it2.hasNext())
////				{
////					String code = (String)it2.next();
////					if(code.equals(CCBS.CLOSING_STOCKS_ELEMENT_CODE))
////					{
////						//Second Element
////						openingClosingStocksValues[1] = hashmap.get(code);
////					}
////				}
//				
//		
//		for(AMISCodesModelData topcode : rvoRes.getCodes()) {
//			//Label=year
//			//Code=month
//			for(HashMap<String, String> hashMap:monthForecastToQuery)
//			{
//				if(hashMap.get(topcode.getLabel())!=null)
//				{
//					String month = hashMap.get(topcode.getLabel());
//					if(!(month.equalsIgnoreCase(topcode.getCode())))
//					{
//						indexToEliminate.add(topcode);
//					}
//				}
//			}
//		}
//		//To delete the objects
//		for(AMISCodesModelData element : indexToEliminate)
//		{
//			if(element!=null)
//			{
//				rvoRes.getCodes().remove(element);
//			}
//		}

        System.out.println("Class: AmisserviceImpl Function: getOpeningClosingStocksForYear Text end");
        return risToReturn;
    }

//	public Boolean fillCountryData(AMISQueryVO qvo) {
//		System.out.println("Class: AmisserviceImpl Function: fillCountryData Text Start");
//	//	AMISResultVO rvo = new AMISResultVO();
//		Boolean ris = false;
//		try 
//		{
//			List<Object[]> cbsData = qvo.getCbsResult();
//			//Change the column of the Dataset to put the name of the Country Dataset
//			for(int iList=0; iList<cbsData.size(); iList++)
//			{
//				cbsData.get(iList)[0] = qvo.getSelectedDataset();
//			}
//			//Insert all the values from Cbs Data Source to Country Data Source
//			for(int iList=0; iList<cbsData.size(); iList++)
//			{
//				ris = AMISGetCodingSystem.fillCountryData(qvo, iList, amisDao);
//			}
//			
//		//	rvo.setDataInserted(ris);	
//
//		} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				return false;
//			}
//				
//		return ris;		
//	}	

    //This method saves all the changes made on the grid
    public Boolean saveGridCcbsTool(String amisCountryDataset, List<HashMap<String, String>> listToSaveOnDb, int numColumns)  {

        Boolean result = false;
        System.out.println("Class: AmisServiceImpl Function:saveGridCcbsTool Text: Start int numColumns= "+numColumns);
        if(listToSaveOnDb==null)
        {
            System.out.println("Class: AmisServiceImpl Function:saveGridCcbsTool Text: listToSave==null");
        }
        else
        {
            System.out.println("Class: AmisServiceImpl Function:saveGridCcbsTool Text: listToSave!=null");
            System.out.println("Class: AmisServiceImpl Function:saveGridCcbsTool Text: listToSave size = "+ listToSaveOnDb.size());
        }


//		int dim = timeSeriesDim+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size();
//		//ClientCbsDatasetResult listToSave[] = new ClientCbsDatasetResult[dim]; 
//		List<HashMap<String, String>> listToSave = new ArrayList<HashMap<String,String>>();
        List<ClientCbsDatasetResult> listClientCbsDataset = new ArrayList<ClientCbsDatasetResult>();
        ClientCbsDatasetResult clientCbsDatasetResult;
        String idString;
        for(HashMap<String, String> hashMap : listToSaveOnDb)
        {
            clientCbsDatasetResult = new ClientCbsDatasetResult();
            Set<String> clientHashMap = hashMap.keySet();
            Iterator it = clientHashMap.iterator();
            while(it.hasNext())
            {
                String cellSaveKey = (String)it.next();
                String cellSaveValue = hashMap.get(cellSaveKey);
                if(cellSaveKey.equals("id"))
                {
                    clientCbsDatasetResult.setId(cellSaveValue);
                }
                else if(cellSaveKey.equals("datasource"))
                {
                    clientCbsDatasetResult.setDatabase(cellSaveValue);
                }
                else if(cellSaveKey.equals("countrycode"))
                {
                    clientCbsDatasetResult.setRegion_code(cellSaveValue);
                }
                else if(cellSaveKey.equals("countryname"))
                {
                    clientCbsDatasetResult.setRegion_name(cellSaveValue);
                }
                else if(cellSaveKey.equals("commoditycode"))
                {
                    clientCbsDatasetResult.setProduct_code(cellSaveValue);
                }
                else if(cellSaveKey.equals("commodityname"))
                {
                    clientCbsDatasetResult.setProduct_name(cellSaveValue);
                }
                else if(cellSaveKey.equals("value"))
                {
                    clientCbsDatasetResult.setValue(cellSaveValue);
                }
                else if(cellSaveKey.equals("elementcode"))
                {
                    clientCbsDatasetResult.setElement_code(cellSaveValue);
                }
                else if(cellSaveKey.equals("elementname"))
                {
                    clientCbsDatasetResult.setElement_name(cellSaveValue);
                }
                else if(cellSaveKey.equals("elementunit"))
                {
                    clientCbsDatasetResult.setUnits(cellSaveValue);
                }
                else if(cellSaveKey.equals("forecast"))
                {
                    clientCbsDatasetResult.setMonth(cellSaveValue);
                }
                else if(cellSaveKey.equals("year"))
                {
                    clientCbsDatasetResult.setYear(cellSaveValue);
                }
                else if(cellSaveKey.equals("flag"))
                {
                    clientCbsDatasetResult.setAnnotation(cellSaveValue);
                }
                else if(cellSaveKey.equals("valuetype"))
                {
                    clientCbsDatasetResult.setValueType(cellSaveValue);
                }
            }
            if(clientCbsDatasetResult.getElement_name().equals("Population"))
            {
                clientCbsDatasetResult.setProduct_code("9");
                clientCbsDatasetResult.setProduct_name("Population");
            }
            listClientCbsDataset.add(clientCbsDatasetResult);
        }
//		for(ClientCbsDatasetResult client : listToSaveOnDb)
//		{
//			HashMap<String, String> hashMap = new HashMap<String, String>();
//			hashMap.put("id", client.getId());
//			hashMap.put("datasource", client.getDatabase());
//			hashMap.put("countrycode", client.getRegion_code());
//			hashMap.put("countryname", client.getRegion_name());
//			hashMap.put("commoditycode", client.getProduct_code());
//			hashMap.put("commodityname", client.getProduct_name());
//			hashMap.put("value", client.getValue());
//			hashMap.put("elementcode", client.getElement_code());
//			hashMap.put("elementname", client.getElement_name());
//			hashMap.put("elementunit", client.getUnits());
//			hashMap.put("forecast", client.getMonth());
//			hashMap.put("year", client.getYear());
//			hashMap.put("flag", client.getAnnotation());
//			hashMap.put("valuetype", client.getValueType());
//			listToSave.add(hashMap);
//		}
//		
        int i =0;
//		for(ClientCbsDatasetResult dataResult: listClientCbsDataset)
//		{
//			System.out.println("Class: AmisServiceImpl Function:saveGridCcbsTool Text: listToSave loop i = "+ i);	
//			//For Each Element
//			System.out.println(" Id: "+ dataResult.getId());
//			System.out.println(" DataSource: "+ dataResult.getDatabase());
//			System.out.println(" CountryCode: "+ dataResult.getRegion_code());
//			System.out.println(" CountryName: "+ dataResult.getRegion_name());
//			System.out.println(" CommodityCode: "+ dataResult.getProduct_code());
//			System.out.println(" CommodityName: "+ dataResult.getProduct_name());
//			System.out.println(" Value: "+ dataResult.getValue());
//			System.out.println(" ElementCode: "+ dataResult.getElement_code());
//			System.out.println(" ElementName: "+ dataResult.getElement_name());
//			System.out.println(" ElementUnit : "+ dataResult.getUnits());
//			System.out.println(" Forecast : "+ dataResult.getMonth());
//			System.out.println(" Year : "+ dataResult.getYear());
//			System.out.println(" Flag : "+ dataResult.getAnnotation());		
//			System.out.println(" ValueType : "+ dataResult.getValueType());	
//			i++;
//		}

        int col;

        AMISQueryVO qvo = new AMISQueryVO();
//		qvo.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
        qvo.setSelectedDatasetFile(amisCountryDataset);
        String tablename = AMISGetCodingSystem.getCountryDataTableName(qvo, amisDao);
        //(numColumns-2) è l'ultima colonna
        for(ClientCbsDatasetResult dataResult: listClientCbsDataset)
        {
            idString = dataResult.getId();
            col = getColumnFromId(idString);

            saveOndb(dataResult, col, numColumns, tablename, amisCountryDataset);

        }

        System.out.println("Class: AmisServiceImpl Function:saveGridCcbsTool Text: listToSave Before end ");
        result = true;
        return result;
    }

    private void saveOndb(ClientCbsDatasetResult dataResult, int col, int numColumns, String tablename, String amisCountryDataset) {
        //numColumns contains also the first column(element list) of the grid
        //amisQueryVO is the cell of the grid or the Subelement of the Production Form or the Subelement of the Other Uses Form 
        AMISQueryVO amisQueryVO = new AMISQueryVO();
//		amisQueryVO.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
        amisQueryVO.setSelectedDatasetFile(amisCountryDataset);
        amisQueryVO.setDatabase(dataResult.getDatabase());
        amisQueryVO.setCountryCode(dataResult.getRegion_code());
        amisQueryVO.setCountryName(dataResult.getRegion_name());
        amisQueryVO.setCommodityCode(dataResult.getProduct_code());
        amisQueryVO.setCommodityName(dataResult.getProduct_name());
        amisQueryVO.setElementCode(dataResult.getElement_code());
        amisQueryVO.setElementName(dataResult.getElement_name());
        amisQueryVO.setUnit(dataResult.getUnits());
        amisQueryVO.setYear(dataResult.getYear());
        amisQueryVO.setMonth(dataResult.getMonth());
        amisQueryVO.setValue(dataResult.getValue());
        amisQueryVO.setFlag(dataResult.getAnnotation());
        amisQueryVO.setValueType(dataResult.getValueType());
        //This object has been obtain through SELECT 
        //Checks if the element is already stored
        ClientCbsDatasetResult clientCbsDatasetResultObject = AMISGetCodingSystem.getSingleElement(amisQueryVO, amisDao, tablename);

        if(clientCbsDatasetResultObject!=null)
        {
            String flagClient = dataResult.getAnnotation();
            String flagDb = clientCbsDatasetResultObject.getAnnotation();
            String valueClient = dataResult.getValue();
            String valueDb = clientCbsDatasetResultObject.getValue();
            boolean equalFlag = false;
            boolean equalValue = false;
            boolean equal=false;
            if(((flagClient==null)||(flagClient.equals(""))||(flagClient.equals("null"))||(flagClient.equals("NULL")))&&((flagDb==null)||(flagDb.equals(""))||(flagDb.equals("null"))||(flagDb.equals("NULL"))))
            {
                equalFlag = true;
            }
            if(((valueClient==null)||(valueClient.equals(""))||(valueClient.equals("null"))||(valueClient.equals("NULL"))||(Double.parseDouble(valueClient)==0))&&((valueDb==null)||(valueDb.equals(""))||(valueDb.equals("null"))||(valueDb.equals("NULL"))||(Double.parseDouble(valueDb)==0)))
            {
                equalValue = true;
            }
            if((equalFlag)&&(equalValue))
            {
                equal = true;
            }
            //	System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text 1: Element name "+ clientCbsDatasetResultObject.getElement_name()+" Year "+ clientCbsDatasetResultObject.getYear()+" Flag "+ clientCbsDatasetResultObject.getAnnotation()+" Value "+ clientCbsDatasetResultObject.getValue());
            //	System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text 2: Element name "+ dataResult.getElement_name()+" Year "+ dataResult.getYear()+" Flag "+ dataResult.getAnnotation()+" Value "+ dataResult.getValue());
            double dataResultValue = Double.parseDouble(dataResult.getValue());
            double clientCbsDatasetResultObjectValue = Double.parseDouble(clientCbsDatasetResultObject.getValue());
//			if((equal)||((dataResult.getAnnotation().equals(clientCbsDatasetResultObject.getAnnotation()))&&(dataResult.getValue().equals(clientCbsDatasetResultObject.getValue()))))
            if((equal)||((dataResult.getAnnotation().equals(clientCbsDatasetResultObject.getAnnotation()))&&(dataResultValue==clientCbsDatasetResultObjectValue)))
            {
                //If equal....do nothing
               // System.out.println("UGUALI !!!!");
            }
            else
            {
                //Old element
                AMISQueryVO amisQueryVO2 = new AMISQueryVO();
                amisQueryVO2.setSelectedDatasetFile(AMISConstants.AMIS_HISTORICAL_COUNTRY_DATA.toString());
                amisQueryVO2.setDatabase(clientCbsDatasetResultObject.getDatabase());
                amisQueryVO2.setCountryCode(clientCbsDatasetResultObject.getRegion_code());
                amisQueryVO2.setCountryName(clientCbsDatasetResultObject.getRegion_name());
                amisQueryVO2.setCommodityCode(clientCbsDatasetResultObject.getProduct_code());
                amisQueryVO2.setCommodityName(clientCbsDatasetResultObject.getProduct_name());
                amisQueryVO2.setElementCode(clientCbsDatasetResultObject.getElement_code());
                amisQueryVO2.setElementName(clientCbsDatasetResultObject.getElement_name());
                amisQueryVO2.setUnit(clientCbsDatasetResultObject.getUnits());
                amisQueryVO2.setYear(clientCbsDatasetResultObject.getYear());
                amisQueryVO2.setMonth(clientCbsDatasetResultObject.getMonth());
                amisQueryVO2.setValue(clientCbsDatasetResultObject.getValue());
                amisQueryVO2.setFlag(clientCbsDatasetResultObject.getAnnotation());
                amisQueryVO2.setValueType(clientCbsDatasetResultObject.getValueType());
                System.out.println("DIVERSI !!!!values  From grid New : *"+amisQueryVO.getValue()+"*"+" From dataset Old : *"+amisQueryVO2.getValue()+"*");
                String historicalTablename = AMISGetCodingSystem.getCountryDataTableName(amisQueryVO2, amisDao);
                //Copy the old element taken from AMIS_COUNTRY_DATA to the historical dataset
                //No copy the calculated elements
                int i=0;
                for(i=0; i<CCBS.CALCULATED_ELEMENTS_CODE.length; i++)
                {
                    if(amisQueryVO2.getElementCode().equals(CCBS.CALCULATED_ELEMENTS_CODE[i]))
                    {
                        break;
                    }
                }
                if(i>=CCBS.CALCULATED_ELEMENTS_CODE.length)
                {
                    //This element is not calculated
                    //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text: To insert in the historical table Element Name: "+amisQueryVO2.getElementName());
                    AMISGetCodingSystem.insertCountryElement(amisQueryVO2, amisDao, historicalTablename);
                }

                //Overwrite the element in the AMIS_COUNTRY_DATA with the new one(UPDATE)
                AMISGetCodingSystem.updateCountryElement(amisQueryVO, clientCbsDatasetResultObject, amisDao, tablename);
                //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text: Before updateAllForecastCountryElement amisQueryVO.getElementName() "+ amisQueryVO.getElementName());
                //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text: Before updateAllForecastCountryElement amisQueryVO.getElementName() "+ dataResult.getElement_name());
                if(amisQueryVO.getElementCode().equalsIgnoreCase(CCBS.CLOSING_STOCKS_ELEMENT_CODE))
                {
                    //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text: Before updateAllForecastCountryElement if(amisQueryVO.getElementCode().equalsIgnoreCase(CCBS.CLOSING_STOCKS_ELEMENT_CODE)) ");
                    //Case CLOSING_STOCKS: it's necessary update the OPENING_STOCKS of all forecast of the all years
                    //Check if there is already the OPENING_STOCKS element for the next year
                    AMISQueryVO amisQueryVONextYear = new AMISQueryVO();
//					amisQueryVO.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
                    amisQueryVONextYear.setSelectedDatasetFile(amisQueryVO.getSelectedDatasetFile());
                    amisQueryVONextYear.setDatabase(amisQueryVO.getDatabase());
                    amisQueryVONextYear.setCountryCode(amisQueryVO.getCountryCode());
                    amisQueryVONextYear.setCountryName(amisQueryVO.getCountryName());
                    amisQueryVONextYear.setCommodityCode(amisQueryVO.getCommodityCode());
                    amisQueryVONextYear.setCommodityName(amisQueryVO.getCommodityName());
                    amisQueryVONextYear.setElementCode(CCBS.OPENING_STOCKS_ELEMENT_CODE);
                    //amisQueryVONextYear.setElementName(amisQueryVO.getElementName());
                    amisQueryVONextYear.setUnit(amisQueryVO.getUnit());
                    String nextYearString = amisQueryVO.getYear().substring(0, 4);
                    int nextYear = Integer.parseInt(nextYearString)+1;
                    amisQueryVONextYear.setYear(""+nextYear);
                    //amisQueryVONextYear.setMonth(amisQueryVO.getMonth());
                    amisQueryVONextYear.setValue(amisQueryVO.getValue());
                    amisQueryVONextYear.setFlag(amisQueryVO.getFlag());
                    amisQueryVONextYear.setValueType(amisQueryVO.getValueType());
                    ClientCbsDatasetResult nextYearDatasetResultObject = AMISGetCodingSystem.getSingleElement(amisQueryVONextYear, amisDao, tablename);
                    if(nextYearDatasetResultObject!= null)
                    {
                        //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text: if(nextYearDatasetResultObject!= null) ");
                        //The element exists
                        if((nextYearDatasetResultObject.getValue().equals(amisQueryVONextYear.getValue()))&&(nextYearDatasetResultObject.getAnnotation().equals(amisQueryVONextYear.getFlag())))
                        //if(nextYearDatasetResultObject.getValue().equals(amisQueryVONextYear.getValue()))
                        {
                            //The element contains the same value and the same flag... Do nothing
                            //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text: if((nextYearDatasetResultObject.getValue().equals(amisQueryVONextYear.getValue()))&&(nextYearDatasetResultObject.getAnnotation().equals(amisQueryVONextYear.getFlag()))) ");
                        }
                        else
                        {
                            //It's necessary to use the UPDATE function
                            amisQueryVONextYear.setElementName(nextYearDatasetResultObject.getElement_name());
                            //amisQueryVONextYear.setMonth(nextYearDatasetResultObject.getMonth());
                            //not set the month because it must update all the month for that year
                            //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text: Before updateAllForecastCountryElement Year "+amisQueryVONextYear.getYear());
                            AMISGetCodingSystem.updateAllForecastCountryElement(amisQueryVONextYear, nextYearDatasetResultObject, amisDao, tablename);
                        }
                    }
                    else
                    {
                        //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text: if(nextYearDatasetResultObject!= null) NULL!!! ");
                        //The element doestn't exist.... NOTHING....
                        //It's necessary to use the INSERT function
//						AMISGetCodingSystem.insertCountryElement(amisQueryVONextYear, amisDao, tablename);
                    }
                }
            }
        }
        else
        {
            AMISGetCodingSystem.insertCountryElement(amisQueryVO, amisDao, tablename);
            if(amisQueryVO.getElementCode().equalsIgnoreCase(CCBS.CLOSING_STOCKS_ELEMENT_CODE))
            {
                //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text:New Element Before updateAllForecastCountryElement if(amisQueryVO.getElementCode().equalsIgnoreCase(CCBS.CLOSING_STOCKS_ELEMENT_CODE)) ");
                //Case CLOSING_STOCKS: it's necessary update the OPENING_STOCKS of all forecast of the all years
                //Check if there is already the OPENING_STOCKS element for the next year
                AMISQueryVO amisQueryVONextYear = new AMISQueryVO();
//				amisQueryVO.setSelectedDatasetFile(AMISConstants.AMIS_COUNTRY_DATA.toString());
                amisQueryVONextYear.setSelectedDatasetFile(amisQueryVO.getSelectedDatasetFile());
                amisQueryVONextYear.setDatabase(amisQueryVO.getDatabase());
                amisQueryVONextYear.setCountryCode(amisQueryVO.getCountryCode());
                amisQueryVONextYear.setCountryName(amisQueryVO.getCountryName());
                amisQueryVONextYear.setCommodityCode(amisQueryVO.getCommodityCode());
                amisQueryVONextYear.setCommodityName(amisQueryVO.getCommodityName());
                amisQueryVONextYear.setElementCode(CCBS.OPENING_STOCKS_ELEMENT_CODE);
                //amisQueryVONextYear.setElementName(amisQueryVO.getElementName());
                amisQueryVONextYear.setUnit(amisQueryVO.getUnit());
                String nextYearString = amisQueryVO.getYear().substring(0, 4);
                int nextYear = Integer.parseInt(nextYearString)+1;
                amisQueryVONextYear.setYear(""+nextYear);
                //amisQueryVONextYear.setMonth(amisQueryVO.getMonth());
                amisQueryVONextYear.setValue(amisQueryVO.getValue());
                amisQueryVONextYear.setFlag(amisQueryVO.getFlag());
                amisQueryVONextYear.setValueType(amisQueryVO.getValueType());
                ClientCbsDatasetResult nextYearDatasetResultObject = AMISGetCodingSystem.getSingleElement(amisQueryVONextYear, amisDao, tablename);
                if(nextYearDatasetResultObject!= null)
                {
                    //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text:New Element if(nextYearDatasetResultObject!= null) ");
                    //The element exists
                    if((nextYearDatasetResultObject.getValue().equals(amisQueryVONextYear.getValue()))&&(nextYearDatasetResultObject.getAnnotation().equals(amisQueryVONextYear.getFlag())))
                    //if(nextYearDatasetResultObject.getValue().equals(amisQueryVONextYear.getValue()))
                    {
                        //The element contains the same value and the same flag... Do nothing
                        //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text:New Element if((nextYearDatasetResultObject.getValue().equals(amisQueryVONextYear.getValue()))&&(nextYearDatasetResultObject.getAnnotation().equals(amisQueryVONextYear.getFlag()))) ");
                    }
                    else
                    {
                        //It's necessary to use the UPDATE function
                        amisQueryVONextYear.setElementName(nextYearDatasetResultObject.getElement_name());
                        //amisQueryVONextYear.setMonth(nextYearDatasetResultObject.getMonth());
                        //not set the month because it must update all the month for that year
                        //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text:New Element Before updateAllForecastCountryElement Year "+amisQueryVONextYear.getYear());
                        AMISGetCodingSystem.updateAllForecastCountryElement(amisQueryVONextYear, nextYearDatasetResultObject, amisDao, tablename);
                    }
                }
                else
                {
                    //System.out.println("Class: AmisServiceImpl Fuction: saveOndb Text:New Element if(nextYearDatasetResultObject!= null) NULL!!! ");
                    //The element doestn't exist.... NOTHING....
                    //It's necessary to use the INSERT function
//					AMISGetCodingSystem.insertCountryElement(amisQueryVONextYear, amisDao, tablename);
                }
            }
//			//If the column is the last one
//			if(col == (numColumns-2))
//			{
//				//This is a new year or the same (last) year (that is in the db) with another month for forecast
//				//Insert this element only in the AMIS_COUNTRY_DATA
//				AMISGetCodingSystem.insertCountryElement(amisQueryVO, amisDao, tablename);
//			}
//			else
//			{
//				//Impossible for the columns before the last
//			}
        }

//		
//		//If the column is NOT the last one
//		if(col != (numColumns-2))
//		{
//			if(clientCbsDatasetResultObject!=null)
//			{
//				if((dataResult.getAnnotation().equals(clientCbsDatasetResultObject.getAnnotation()))&&(dataResult.getValue().equals(clientCbsDatasetResultObject.getValue())))
//				{
//					//If equal....do nothing
//				}
//				else
//				{
//					//Copy the old element taken from AMIS_COUNTRY_DATA to the historical dataset
//					
//					
//					//Overwrite the element in the AMIS_COUNTRY_DATA with the new one(UPDATE)
//					AMISGetCodingSystem.updateCountryElement(amisQueryVO, clientCbsDatasetResultObject, amisDao, tablename);
//					
//				}
//			}
//		}
//		else
//		{
//			//This is a new year or the same (last) year (that is in the db) with another month for forecast
//			if(clientCbsDatasetResultObject == null)
//			{
//				//Insert this element only in the AMIS_COUNTRY_DATA
//			}
//			else
//			{
//				if((dataResult.getAnnotation().equals(clientCbsDatasetResultObject.getAnnotation()))&&(dataResult.getValue().equals(clientCbsDatasetResultObject.getValue())))
//				{
//					//If equal....do nothing
//				}
//				else
//				{
//					//Copy the old element taken from AMIS_COUNTRY_DATA to the historical dataset
//					
//					
//					//Overwrite the element in the AMIS_COUNTRY_DATA with the new one(UPDATE)
//					AMISGetCodingSystem.updateCountryElement(amisQueryVO, clientCbsDatasetResultObject, amisDao, tablename);
//					
//				}
//			}
//		}

    }

    //Ex.row_14col_8SubElement_IndustrialUse_Others
//	dataResult.setId("row_"+iRow+"col_"+iColumn+"Element_"+dataResult.getElement_name());
    //row_14col_8SubElement_IndustrialUse_Biofuel
    private int getColumnFromId(String idString) {
        //	System.out.println("Class AmisserviceImpl Function: getColumnFromId Text : idString=*"+idString+"*");
        int colNum = 0;
        int indexCol_ = idString.indexOf("col_");
        int i = 0;
        indexCol_ +=4;
        for(i=indexCol_; i< idString.length(); i++)
        {
            //	System.out.println("Class AmisserviceImpl Function: getColumnFromId Text : idString.charat("+i+")=*"+idString.charAt(i)+"*");
            if(!Character.isDigit(idString.charAt(i))){
                break;
            }
        }

        if(i == idString.length()){
            //It seems impossibile
        }
        else{
            //System.out.println("Class AmisserviceImpl Function: getColumnFromId Text : indexCol_=*"+indexCol_+"* i-1=*"+(i-1)+"*");
            idString = idString.substring(indexCol_, i);
            colNum = Integer.parseInt(idString);
        }
        //System.out.println("Class AmisserviceImpl Function: getColumnFromId Text : colNum=*"+colNum+"*");
        return colNum;
    }

//	private int getColumnFromId(String idString) {
//		System.out.println("Class AmisserviceImpl Function: getColumnFromId Text : idString=*"+idString+"*");
//		int colNum;
//		int indexCol_ = idString.indexOf("col_");
//		int indexElement_;		
//		indexElement_ = idString.indexOf("Element_");
//		System.out.println("Class AmisserviceImpl Function: getColumnFromId Text : Element_ indexElement_*"+indexElement_+"*");
//		if(indexElement_<=0)
//		{
//			indexElement_ = idString.indexOf("SubElement_");
//			System.out.println("Class AmisserviceImpl Function: getColumnFromId Text : SubElement_ indexElement_*"+indexElement_+"*");
//		}
//		if(indexElement_<=0)
//		{
//			//The id string doesn't contain neither "Element_" nor "SubElement_" 
//			//Impossible
//			System.out.println("Class AmisserviceImpl Function: getColumnFromId Text : ?????*"+indexElement_+"*");
//		}
//		indexCol_+=4;
//		if((indexCol_<=0)||(indexElement_<=0))
//		{
//			System.out.println("Class AmisserviceImpl Function: getColumnFromId Text : indexCol_=*"+indexCol_+"* indexElement_=*"+indexElement_+"*");
//		}
//		idString = idString.substring(indexCol_, indexElement_);
//		colNum = Integer.parseInt(idString);
//		return colNum;
//	}

    public List<ClientCbsDatasetResult> getCBSSubElementsData(AMISQueryVO qvo, List<String> years, String lastMonthMarketingyear, int numberFirstYearsToShow, String oneElementName, List<HashMap<String, String>> monthForecastToQuery)  {
        List<ClientCbsDatasetResult> listForProdAndOtherUsesForm;
        //	System.out.println("Class: AmisServiceImpl Function: getCBSSubElementsData Text: Start lastMonthMarketingyear= "+lastMonthMarketingyear+ " oneElementName= "+oneElementName+ " monthForecastToQuery.size = "+ monthForecastToQuery.size());
        //	System.out.println("Class: AmisServiceImpl Function: getCBSSubElementsData Text: Start qvo.getSelectedDataset() "+ qvo.getSelectedDataset()+" qvo.getSelectedDatasetFile() "+qvo.getSelectedDatasetFile());
        try {
            //	System.out.println("Class: AmisServiceImpl Function: getCBSSubElementsData Text: Start in try years "+years.size());
            // prepare map from years to position
            Map<Integer, Integer> year2Idx = new HashMap<Integer, Integer>();
            int yearIndex = 0;
            for (int i = numberFirstYearsToShow; i < years.size(); i++) {
                // 2); // DEBUG
                year2Idx.put(Integer.parseInt(parseYear(years.get(i))), yearIndex * 2);
                System.out.println("year2Idx: " + years.get(i) + " --> " + yearIndex * 2 );
                yearIndex++;
            }
            //To take the hashMap for each year and the best month
            List<Object[]> sqlResultForEachYear = new ArrayList<Object[]>();
            //	System.out.println("Class: AmisServiceImpl Function: getCBSSubElementsData Text: Before if(!lastMonthMarketingyear.equalsIgnoreCase(CCBS.N_A_MONTH))");
            if(!lastMonthMarketingyear.equalsIgnoreCase(CCBS.N_A_MONTH))
            {
                //		System.out.println("Class: AmisServiceImpl Function: getCBSSubElementsData Text: In if(!lastMonthMarketingyear.equalsIgnoreCase(CCBS.N_A_MONTH))");
                //Calculating the month to take(the last forecast)
//			List<Object[]> sqlResultForLastForecast = AMISGetCodingSystem.getTimeSeriesForMonthForecast(qvo,oneElementName,amisDao);
//			List<HashMap<String, String>> monthForecastToQuery = aMISCbsServerUtility.lastMonthForecastForYear(sqlResultForLastForecast, lastMonthMarketingyear);
//	
            //    System.out.println("Class: AmisServiceImpl Function: getCBSSubElementsData Text: 1 qvo.getSelectedDataset() "+ qvo.getSelectedDataset()+" qvo.getSelectedDatasetFile() "+qvo.getSelectedDatasetFile());

                for(HashMap<String, String> hashMap: monthForecastToQuery)
                {
                    Set<String> key = hashMap.keySet();
                    Iterator it = key.iterator();
                    String yearKey = (String)it.next();
                    String month = hashMap.get(yearKey);
                    List<Object[]> sqlResult = AMISGetCodingSystem.getSubElementsTimeSeries(qvo, yearKey, month, amisDao);
                    if(sqlResult != null)
                    {
                        for(Object obj[]: sqlResult)
                        {
                            sqlResultForEachYear.add(obj);
                        }
                    }
                }
            }
            else
            {
              //  System.out.println("Class: AmisServiceImpl Function: getCBSSubElementsData Text: 2 qvo.getSelectedDataset() "+ qvo.getSelectedDataset()+" qvo.getSelectedDatasetFile() "+qvo.getSelectedDatasetFile());

                //Case COARSE GRAIN and TOTAL CEREAL: there is no Marketing Year
                String yearKey = null;
                String month = null;
                sqlResultForEachYear = AMISGetCodingSystem.getSubElementsTimeSeries(qvo, yearKey, month, amisDao);
            }
            //System.out.println("Class: AmisServiceImpl Function: getCBSSubElementsData Text: Before getSubElementsTimeSeries");
            //List<Object[]> sqlResult = AMISGetCodingSystem.getSubElementsTimeSeries(qvo, amisDao);
            //System.out.println("Class: AmisServiceImpl Function: getCBSSubElementsData Text: After getSubElementsTimeSeries");
            //listForProdAndOtherUsesForm = fillSubElementsListForClientSide(sqlResult, qvo, year2Idx);
            listForProdAndOtherUsesForm = fillSubElementsListForClientSide(sqlResultForEachYear, qvo, year2Idx);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        return listForProdAndOtherUsesForm;
    }

    private List<ClientCbsDatasetResult> fillSubElementsListForClientSide(List<Object[]> sqlResult, AMISQueryVO qvo, Map<Integer, Integer> year2Idx) {

        //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Start = ");
        //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Start sqlResult.size() ="+sqlResult.size());
        List<ClientCbsDatasetResult> listForProdAndOtherUsesForm = new ArrayList<ClientCbsDatasetResult>();
        boolean found = false;
        for(int i=0; i < sqlResult.size(); i++) {
            found = false;
            if(sqlResult.get(i)[0] ==null)
            {
                sqlResult.get(i)[0] = "";
            }
            if(sqlResult.get(i)[1] ==null)
            {
                sqlResult.get(i)[1] = "";
            }
            if(sqlResult.get(i)[2] ==null)
            {
                sqlResult.get(i)[2] = "";
            }
            if(sqlResult.get(i)[3] ==null)
            {
                sqlResult.get(i)[3] = "";
            }
            if(sqlResult.get(i)[4] ==null)
            {
                sqlResult.get(i)[4] = "";
            }
            if(sqlResult.get(i)[5] ==null)
            {
                sqlResult.get(i)[5] = "";
            }
            if(sqlResult.get(i)[6] ==null)
            {
                sqlResult.get(i)[6] = "";
            }
            if(sqlResult.get(i)[7] ==null)
            {
                sqlResult.get(i)[7] = "";
            }
            if(sqlResult.get(i)[8] ==null)
            {
                sqlResult.get(i)[8] = "";
            }
            if(sqlResult.get(i)[9] ==null)
            {
                sqlResult.get(i)[9] = "";
            }
            if(sqlResult.get(i)[10] ==null)
            {
                sqlResult.get(i)[10] = "";
            }

            Set<Integer> yearKey=	year2Idx.keySet();
            Iterator<Integer> it = yearKey.iterator();
            while(it.hasNext())
            {
                Integer integ = it.next();
                //		System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: before test integ key= *"+integ+"*");
                //		System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: before test year object= "+year2Idx.get(integ));
            }
            String year = sqlResult.get(i)[10].toString();
            String nextYear = followingYear(year);
            String yearToSave = year.concat("/"+nextYear.substring(2));
            //	String yearTot=year +"/"+nextYear.substring(2);
            //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: before test year= "+year);
            //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: before test nextYear= "+nextYear);
            //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: before test yearTot= "+yearTot);
            //To insert only the elements with the write year
            int iYear = 0;
            Integer colNum = new Integer(0);
            //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: year2Idx.size() ="+year2Idx.size());
            for(iYear = 0; iYear< year2Idx.size(); iYear++)
            {
                //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: before test year= *"+year+"*");
                colNum = year2Idx.get(Integer.parseInt(year));
                if(colNum!=null)
                {
                    //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: before break colNum"+colNum);
                    found = true;
                    break;
                }
            }
            if(!found)
            {
                //			System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: before continue ");
                continue;
            }
            //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: after if found = "+found);
            //Insert only the years to show in the grid
            //ClientCbsDatasetResult clientCbsDatasetResult = new ClientCbsDatasetResult(qvo.getSelectedDataset(),qvo.getCountryCode(), sqlResult.get(i)[0].toString(), qvo.getCommodityCode(), sqlResult.get(i)[1].toString(), sqlResult.get(i)[3].toString(), sqlResult.get(i)[2].toString(), sqlResult.get(i)[4].toString(), sqlResult.get(i)[10].toString(), sqlResult.get(i)[5].toString(), sqlResult.get(i)[6].toString(), sqlResult.get(i)[7].toString(), sqlResult.get(i)[8].toString());
            ClientCbsDatasetResult clientCbsDatasetResult = new ClientCbsDatasetResult(qvo.getSelectedDataset(),qvo.getCountryCode(), sqlResult.get(i)[0].toString(), qvo.getCommodityCode(), sqlResult.get(i)[1].toString(), sqlResult.get(i)[3].toString(), sqlResult.get(i)[2].toString(), sqlResult.get(i)[4].toString(), yearToSave, sqlResult.get(i)[5].toString(), sqlResult.get(i)[6].toString(), sqlResult.get(i)[7].toString(), sqlResult.get(i)[8].toString());
            String rowNum = CCBS.SUB_ELEMENTS_ROW_NUM.get(clientCbsDatasetResult.getElement_name());
            String currentId = "";
            //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: rowNum = "+rowNum);
            if(clientCbsDatasetResult.getElement_name().equals("Production"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"Crop_";
                currentId+= "Production";
                //		System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Production currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Area Harvested"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"Crop_";
                currentId+= "AreaHarvested";
                //		System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Area Harvested currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Yield"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"Crop_";
                currentId+= "Yield";
                //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Yield currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Seeds"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_Main_";
                currentId+= "Seeds";
                //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Area Harvested currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Post Harvest Losses"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_Main_";
                currentId+= "PostHarvestLosses";
                //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Seeds currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Industrial Use"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_Main_";
                currentId+= "IndustrialUse";
                //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Industrial Use currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Malt"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_IndustrialUse_";
                currentId+= "Malt";
                //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Malt currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Biofuel"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_IndustrialUse_";
                currentId+= "Biofuel";
                //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Biofuel currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Sweeteners"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_IndustrialUse_";
                currentId+= "Sweeteners";
                //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Sweeteners currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Starch"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_IndustrialUse_";
                currentId+= "Starch";
                //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Starch currentId= "+currentId);
            }
            if(clientCbsDatasetResult.getElement_name().equals("Others"))
            {
                currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_IndustrialUse_";
                currentId+= "Others";
                //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: Others currentId= "+currentId);
            }
            clientCbsDatasetResult.setId(currentId);
            //System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text:before add "+currentId);
            listForProdAndOtherUsesForm.add(clientCbsDatasetResult);
        }
        //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: End = ");
        //	System.out.println("Class: AmisServiceImpl Function: fillSubElementsListForClientSide Text: End listForProdAndOtherUsesForm.size() = "+listForProdAndOtherUsesForm.size());
        return listForProdAndOtherUsesForm;
    }



    private static TimeSeries [] getListTimeSeries(AMISQueryVO qvo, List<Object[]> sqlResult, CCBS.Codes codes[]) {

        List<DatasetResult> datasetResult = new ArrayList<DatasetResult>();
        for(int i=0; i < sqlResult.size(); i++) {
            if(sqlResult.get(i)[0] ==null)
            {
                sqlResult.get(i)[0] = "";
            }
            if(sqlResult.get(i)[1] ==null)
            {
                sqlResult.get(i)[1] = "";
            }
            if(sqlResult.get(i)[2] ==null)
            {
                sqlResult.get(i)[2] = "";
            }
            if(sqlResult.get(i)[3] ==null)
            {
                sqlResult.get(i)[3] = "";
            }
            if(sqlResult.get(i)[4] ==null)
            {
                sqlResult.get(i)[4] = "";
            }
            if(sqlResult.get(i)[5] ==null)
            {
                sqlResult.get(i)[5] = "";
            }
            if(sqlResult.get(i)[6] ==null)
            {
                sqlResult.get(i)[6] = "";
            }
            String rowLabel = sqlResult.get(i)[0].toString();
            boolean found = false;
            for(int j=0; j< codes.length; j++)
            {
                if(codes[j].getDatasetCode().equalsIgnoreCase(rowLabel))
                {
                    found = true;
                    break;
                }
            }
            if(found)
            {
                datasetResult.add(new DatasetResult(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString(), sqlResult.get(i)[2].toString(),sqlResult.get(i)[3].toString(), sqlResult.get(i)[4].toString(),sqlResult.get(i)[6].toString()));
            }
        }
        int countElementsForRow[] = new int[codes.length];
        for(int j= 0; j< countElementsForRow.length; j++)
        {
            countElementsForRow[j] = 0;
        }
        int z=0;
        boolean found = false;
        for(DatasetResult datasetResultElement : datasetResult)
        {
            //	System.out.println("Class: AmisServiceImpl Function: getListTimeSeries Text: After for Start  ");
            found = false;
            int i=0;
            String datasetResultElementName = datasetResultElement.getElement_name();
            //	System.out.println("Class: AmisServiceImpl Function: getListTimeSeries Text: New datasetResultElement z= "+z+ " datasetResultElementName "+ datasetResultElementName + " codes.length= "+codes.length);
            for(i= 0; i< codes.length; i++)
            {
                if(datasetResultElementName.equalsIgnoreCase(codes[i].getDatasetCode()))
                {
                    //	System.out.println("Class: AmisServiceImpl Function: getListTimeSeries Text: Found = true datasetResultElement z= "+z+ " datasetResultElementName "+ datasetResultElementName + " codes[i].getDatasetCode()= "+codes[i].getDatasetCode());
                    found = true;
                    break;
                }
            }
            //	System.out.println("Class: AmisServiceImpl Function: getListTimeSeries Text: After for for found "+found +"i= "+i);
            if(found)
            {
                countElementsForRow[i]++;
            }
            //	System.out.println("Class: AmisServiceImpl Function: getListTimeSeries Text: After for for countElementsForRow[i] "+countElementsForRow[i]);
            z++;
        }
        System.out.println("Class: AmisServiceImpl Function: getListTimeSeries Text: codes.length= "+codes.length);
        FenixCBSToolTimeSeries timeSeriesForRow[] = new FenixCBSToolTimeSeries[codes.length];
        for(int j= 0; j< countElementsForRow.length; j++)
        {
            timeSeriesForRow[j] = new FenixCBSToolTimeSeries(countElementsForRow[j]);
        }
        for(DatasetResult datasetResultElement : datasetResult)
        {
            int rowIndex = searchRowIndex(codes, datasetResultElement);
            //	System.out.println("Class: AmisServiceImpl Function: getListTimeSeries Text: ");
            timeSeriesForRow[rowIndex].add(new FenixCBSToolTimeSeries.LocalElement(datasetResultElement));
        }
        //	System.out.println("Class: AmisServiceImpl Function: getListTimeSeries Text: Before end");
        return timeSeriesForRow;
    }


    private static int searchRowIndex(Codes[] codes, DatasetResult datasetResultElement) {

        String element_name = datasetResultElement.getElement_name();
        int i = 0;
        for(i = 0; i< codes.length; i++)
        {
            if(element_name.equalsIgnoreCase(codes[i].getDatasetCode()))
            {
                break;
            }
        }
        return i;
    }

    private String parseYear(String year) {
        int index = year.indexOf("/");
        String newYear = year.substring(0, index);
        return newYear;
    }

    private void fillRow(TimeSeries ts, Map<Integer, Integer> year2Idx,
                         Page page, int rowNum, int numberFirstYearsToShow) {
        System.out.println(" ");
        System.out.println(" ");
        //	System.out.println("Class: AmisServiceImp Function: fillRow Text: RowNum "+rowNum + " ts.elements().size() "+ ts.elements().size());
        Calendar cal = new GregorianCalendar();
        int i=0;

        for (TimeSeries.Element el : ts.elements()) {
//			cal.setTime(el.getDate());
//			int year = cal.get(Calendar.YEAR);
            //System.out.println("Class: AmisServiceImp Function: fillRow Text: el.getYear() "+el.getYear());
            Integer year = Integer.parseInt(el.getYear());
            //		System.out.println("Class: AmisServiceImp Function: fillRow Text: el.getYear() "+el.getYear()+" year "+year);
            // System.out.println("year = " + year); // DEBUG

            //To Cancel
            //Integer colNum = year2Idx.get(year)-numberFirstYearsToShow;
            //To Leave
            Integer colNum = year2Idx.get(year);
            //Integer colNum = i;
            //	System.out.println("Class: AmisServiceImp Function: fillRow Text: rowNum + "+rowNum +" colNum "+colNum);
            //		System.out.println("Class: AmisServiceImp Function: fillRow Text: el.getElement_name() + "+el.getElement_name() +" el.getValue() + "+el.getValue());
            //		System.out.println("");
            // System.out.println("colNum = " + colNum); // DEBUG
            //System.out.println("Class: AmisServiceImp Function: fillRow Text: rowNum "+ rowNum+ " colNum "+ colNum+ " el.getValue() "+ el.getValue()+ " el.getAnnotation() "+ el.getAnnotation());
            //This number depends on the number of columns to show
            if ((colNum != null) &&(colNum <11)) {
                //	System.out.println("Class: AmisServiceImp Function: fillRow Text: if (colNum != null)!!!!!!!!!!!!!!!!!!!! ");
                if(el.getValue().equals(""))
                {
                    page.getCell(rowNum, colNum).setNullFromDb(true);
                }
                page.setCell(rowNum, colNum, el.getValue());
                //page.setCell(rowNum, colNum + 1, el.getNote());
                page.setCell(rowNum, colNum + 1, el.getAnnotation());
            }
            i++;
        }
    }
    private static String followingYear(String year)
    {
        long numYear = Long.parseLong(year);
        numYear++;
        return ""+numYear;
    }

    // CCBS tool End

    /**
     * With multi-scale handling
     */
    private AMISResultVO createDefaultChart(AMISQueryVO qvo, AMISResultVO rvo) {
        LOGGER.info("createMultiScaleChart: ");
        System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: Start Now");

        List<Object> singleValue = new ArrayList<Object>();
        HashMap<String, LinkedHashMap> resultsMap = new HashMap<String, LinkedHashMap>();

        if(qvo.getRunMaxDateQuery()) {
            	//System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: if(qvo.getRunMaxDateQuery())");
            // create the SQL query ... for a BAR chart
            qvo.setSql(AMISQueryBuilder.buildQuery(qvo, true, false));
            singleValue =	amisDao.getSingleResultValue(qvo.getSql());

            for(Object val: singleValue){
                LOGGER.info("singleValue: " + val);
                	//System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: singleValue "+singleValue);
                qvo.setRunMaxDateQuery(false);
                qvo.setMaxDateLimit(val.toString());
                rvo.setMaxDateLimit(val.toString());

                if(qvo.getTimeSpan()!=null)
                    rvo.setTimeIntervalSpan(qvo.getTimeSpan());

                Map<String, String> years = new HashMap<String, String>();
                years.put(val.toString(), val.toString());
                qvo.setYears(years);
                //System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: qvo.getYears "+val.toString());
            }
            //	System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: After for(Object val: singleValue)");
            // Compare View Handling: qvo.getNationalDataSourceFilters() is NOT empty when a Non-AMIS Secretariat user is logged in
            // This user can only access their country data i.e. Where region_code = qvo.getNationalDataSourceFilters().get(region_code) and database = NATIONAL
            //This is not used because now each country can see all the data
            if(!qvo.getNationalDataSourceFilters().isEmpty())
            {
                	//System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: if(!qvo.getNationalDataSourceFilters().isEmpty()) 1");
                setSqlandDatabasesForOneNationalDataSource(qvo);
            }
            else
            {
                	//System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: else if(!qvo.getNationalDataSourceFilters().isEmpty()) 1");
                qvo.setSql(AMISQueryBuilder.buildQuery(qvo, false, true));
            }
//			try {
//				resultsMap = amisDao.chartMultiScaleQueryResult(qvo.getSql());
//			} catch (Exception e) {
//				String tmp = qvo.getSql();
//				tmp = tmp.
//				resultsMap = amisDao.chartMultiScaleQueryResult(qvo.getSql());
//			}
            //	System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: Before call chartMultiScaleQueryResult 1");
            resultsMap = amisDao.chartMultiScaleQueryResult(qvo.getSql());
        }
        else {
            //System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: else(qvo.getRunMaxDateQuery())");
            LOGGER.info("$$$$$$$ START ... Title = "+qvo.getTitle() + " | "+qvo.getDatabases() + "| "+qvo.getAreas() + " | "+qvo.getAreas().containsKey(qvo.getNationalDataSourceFilters().get("region_code")));
//            for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
//                System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text:select "+qvo.getSelects().get(i));
//            }
//
//            for (int i = 0 ; i < qvo.getSelects().size() ; i++) {
//                System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text:groupby "+qvo.getGroupBys().get(i));
//            }

            // Compare View Handling: qvo.getNationalDataSourceFilters() is NOT empty when a Non-AMIS Secretariat user is logged in
            // This user can only access their country data i.e. Where region_code = qvo.getNationalDataSourceFilters().get(region_code) and database = NATIONAL
            if(!qvo.getNationalDataSourceFilters().isEmpty())
            {
                //System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: if(!qvo.getNationalDataSourceFilters().isEmpty()) 2");
                //setSqlandDatabasesForOneNationalDataSource(qvo);
                setSqlandDatabasesForOneNationalDataSourceWithBestMonth(qvo);
            }
            else
            {
                //System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: else if(!qvo.getNationalDataSourceFilters().isEmpty()) 2");
                if(qvo.isChartForAmisHomePage())
                {
                    	//System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: if(qvo.isChartForAmisHomePage())");
                    qvo.setSql(AMISQueryBuilder.buildQuery(qvo, false, true));
                }
                else
                {
                    	//System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: else if(qvo.isChartForAmisHomePage())");
                    qvo.setSql(AMISQueryBuilder.buildQueryWithMonth(qvo, false, true));
                }
            }
            //System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: QUERY ****"+qvo.getSql()+"****");
            LOGGER.info("$$$$$$$ END ");

            //qvo.setSql(AMISQueryBuilder.buildQuery(qvo, false, true));	
            if(qvo.getSql()!=null)
            {
                	//System.out.println("Class: AMISServiceImpl Function: createDefaultChart Text: Before call chartMultiScaleQueryResult 2");
                resultsMap = amisDao.chartMultiScaleQueryResult(qvo.getSql());
            }
            else
                resultsMap = new HashMap<String, LinkedHashMap>();

        }

        if(!resultsMap.isEmpty()){
            if(resultsMap.containsKey("VALUES"))
                rvo.setChartValues(resultsMap.get("VALUES"));
            if(resultsMap.containsKey("MEASUREMENT_UNIT_SERIES"))
                rvo.setSerieMeasurementUnits(resultsMap.get("MEASUREMENT_UNIT_SERIES"));
            if(resultsMap.containsKey("MEASUREMENT_UNITS"))
                rvo.setMeasurementUnits(resultsMap.get("MEASUREMENT_UNITS"));
        } else {
            rvo.setChartValues(new LinkedHashMap<String, Map<String, Double>>());
            rvo.setSerieMeasurementUnits(new LinkedHashMap<String, String>());
            rvo.setMeasurementUnits(new LinkedHashMap<String, String>());
        }

        rvo.getHighChartVO().setTitle(qvo.getTitle());
        rvo.getHighChartVO().setType(qvo.getTypeOfOutput());

        return rvo;
    }


    private void setSqlandDatabasesForOneNationalDataSource(AMISQueryVO qvo){
        if(!qvo.getAreas().containsKey(qvo.getNationalDataSourceFilters().get("region_code"))) {

            if(qvo.getDatabases().containsKey(AMISConstants.NATIONAL.toString()));
            qvo.getDatabases().remove(AMISConstants.NATIONAL.toString());

            // qvo.getFirstSelectField(), is the Compare By option
            //By Elements	
            if(qvo.getDatabases().isEmpty() && qvo.getFirstSelectField().equals(AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString())) {
                qvo.setSql(null);
            }
            //By Products
            else if(qvo.getDatabases().isEmpty() && qvo.getFirstSelectField().equals(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString())){
                qvo.setSql(null);
            }
            else {
                qvo.setSql(AMISQueryBuilder.buildQuery(qvo, false, true));
            }

            LOGGER.info("------ IF contains KEY FALSE databases end = "+qvo.getDatabases());
        }
        else {
            LOGGER.info("----- ELSE contains KEY TRUE = "+qvo.getTitle());
            // qvo.getFirstSelectField(), is the Compare By option
            //By Data Sources	
            if(qvo.getFirstSelectField().equals(AMISConstants.AMIS_DATASETS.toString())) {
                qvo.getDatabases().put(AMISConstants.NATIONAL.toString(), AMISConstants.NATIONAL.toString());
                LOGGER.info("----- AMIS_DATASETS contains qvo.getDatabases() = "+qvo.getDatabases());
            }
            //By Countries	
            else if(qvo.getFirstSelectField().equals(AMISConstants.AMIS_COUNTRIES.toString())) {
                if(qvo.getDatabases().containsKey(AMISConstants.NATIONAL.toString())) {
                    String value = qvo.getAreas().get(qvo.getNationalDataSourceFilters().get("region_code"));
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(qvo.getNationalDataSourceFilters().get("region_code"), value);
                    qvo.getAreas().clear();
                    qvo.getAreas().putAll(map);
                }

                LOGGER.info("----- AMIS_COUNTRIES contains qvo.getAreas() = "+qvo.getAreas());

            }

            qvo.setSql(AMISQueryBuilder.buildQuery(qvo, false, true));
        }
    }

    private void setSqlandDatabasesForOneNationalDataSourceWithBestMonth(AMISQueryVO qvo){
        if(!qvo.getAreas().containsKey(qvo.getNationalDataSourceFilters().get("region_code"))) {

            if(qvo.getDatabases().containsKey(AMISConstants.NATIONAL.toString()));
            qvo.getDatabases().remove(AMISConstants.NATIONAL.toString());

            // qvo.getFirstSelectField(), is the Compare By option
            //By Elements	
            if(qvo.getDatabases().isEmpty() && qvo.getFirstSelectField().equals(AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString())) {
                qvo.setSql(null);
            }
            //By Products
            else if(qvo.getDatabases().isEmpty() && qvo.getFirstSelectField().equals(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString())){
                qvo.setSql(null);
            }
            else {
                qvo.setSql(AMISQueryBuilder.buildQueryWithMonth(qvo, false, true));
            }

            LOGGER.info("------ IF contains KEY FALSE databases end = "+qvo.getDatabases());
        }
        else {
            LOGGER.info("----- ELSE contains KEY TRUE = "+qvo.getTitle());
            // qvo.getFirstSelectField(), is the Compare By option
            //By Data Sources	
            if(qvo.getFirstSelectField().equals(AMISConstants.AMIS_DATASETS.toString())) {
                qvo.getDatabases().put(AMISConstants.NATIONAL.toString(), AMISConstants.NATIONAL.toString());
                LOGGER.info("----- AMIS_DATASETS contains qvo.getDatabases() = "+qvo.getDatabases());
            }
            //By Countries	
            else if(qvo.getFirstSelectField().equals(AMISConstants.AMIS_COUNTRIES.toString())) {
                if(qvo.getDatabases().containsKey(AMISConstants.NATIONAL.toString())) {
                    String value = qvo.getAreas().get(qvo.getNationalDataSourceFilters().get("region_code"));
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(qvo.getNationalDataSourceFilters().get("region_code"), value);
                    qvo.getAreas().clear();
                    qvo.getAreas().putAll(map);
                }

                LOGGER.info("----- AMIS_COUNTRIES contains qvo.getAreas() = "+qvo.getAreas());

            }

            qvo.setSql(AMISQueryBuilder.buildQueryWithMonth(qvo, false, true));
        }
    }

    private void queryChart(AMISQueryVO qvo, AMISResultVO rvo) throws FenixException {


        LinkedHashMap<String, Map<String, Double>> map = new LinkedHashMap<String, Map<String, Double>>();

        LinkedHashMap<String, String> serieMeasurementUnits = new LinkedHashMap<String, String>();

        LinkedHashMap<String, String> measurementUnits = new LinkedHashMap<String, String>();


        try {
            SQLServerDriver.class.newInstance();
            Connection c = DriverManager.getConnection(qvo.getConnectionString(), qvo.getDbUserName(), qvo.getDbPassword());
            Statement stmt = c.createStatement();
            stmt.executeQuery(qvo.getSql());

            ResultSet rs = stmt.getResultSet();

            Integer muIndex = 0;
            while (rs.next()) {
                try {
                    LOGGER.info("ROW: "+ rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " + rs.getString(4));
                    Map<String, Double> value = new LinkedHashMap<String, Double>();
                    value.put(rs.getString(2), Double.valueOf(rs.getString(3)));
                    if ( map.containsKey(rs.getString(1))) {
                        Map<String, Double> currentHM = map.get(rs.getString(1));
                        currentHM.put(rs.getString(2), Double.valueOf(rs.getString(3)));
                        map.put(rs.getString(1), currentHM);



//						LOGGER.info("MU: "+ rs.getString(4));
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
                        value.put("", Double.valueOf(rs.getString(3)));
                        map.put("", value);
                    }catch (Exception e2) {

                    }
                }
            }
            LOGGER.info("chart map: " + map);
            LOGGER.info("chart serieMeasurementUnits: " + serieMeasurementUnits);
            LOGGER.info("chart measurementUnits: " + measurementUnits);

            rvo.setChartValues(map);

            rvo.setSerieMeasurementUnits(serieMeasurementUnits);
            rvo.setMeasurementUnits(measurementUnits);

            stmt.close();
        } catch (SQLException e) {
            throw new FenixException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new FenixException(e.getMessage());
        } catch (InstantiationException e) {
            throw new FenixException(e.getMessage());
        }


    }

    //userCountryCode indicates the country which the user belong to
    //userRole can be "Secretariat", "Country User" and "Not Logged User"
    public List<HashMap<String, String>> createOlapTable2(AMISQueryVO qvo, String userRole, String userCountryCode)
    {
        List<HashMap<String, String>> listToReturn = new ArrayList<HashMap<String,String>>();
//		 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: userRole "+ userRole);
//		 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: userCountryCode "+ userCountryCode);
//		AMISQueryVO test = qvo;
//		 int lenght = test.getAreas().size();
//		 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: AREAS lenght "+ lenght);
//		 Set<String> keyset= test.getAreas().keySet();
//		 
//		 Iterator it = keyset.iterator();
//		 while(it.hasNext())
//		 {
//			 String key =(String)it.next();
//			 String value = test.getAreas().get(key);
//			 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: key "+ key+ " value "+ value);
//		 }
//		 
//		 lenght = test.getDatabases().size();
//		 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: DATASET lenght "+ lenght);
//		 keyset= test.getDatabases().keySet();
//		 
//		 it = keyset.iterator();
//		 while(it.hasNext())
//		 {
//			 String key =(String)it.next();
//			 String value = test.getDatabases().get(key);
//			 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: key "+ key+ " value "+ value);
//		 }
//		 lenght = test.getItems().size();
//		 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PRODUCTS lenght "+ lenght);
//		 keyset= test.getItems().keySet();
//		 
//		 it = keyset.iterator();
//		 while(it.hasNext())
//		 {
//			 String key =(String)it.next();
//			 String value = test.getItems().get(key);
//			 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: key "+ key+ " value "+ value);
//		 }
//		 
//		 lenght = test.getElements().size();
//		 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: ELEMENTS lenght "+ lenght);
//		 keyset= test.getElements().keySet();
//		 
//		 it = keyset.iterator();
//		 while(it.hasNext())
//		 {
//			 String key =(String)it.next();
//			 String value = test.getElements().get(key);
//			 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: key "+ key+ " value "+ value);
//		 }
//		 lenght = test.getYears().size();
//		 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: YEARS lenght "+ lenght);
//		 keyset= test.getYears().keySet();
//		 
//		 it = keyset.iterator();
//		 while(it.hasNext())
//		 {
//			 String key =(String)it.next();
//			 String value = test.getYears().get(key);
//			 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: key "+ key+ " value "+ value);
//		 }
//		qvo.setAreas
//		qvo.setItems
//		qvo.setElements
//		qvo.getYears()
//		qvo.setDatabases

        //Loop to create the queries

        //A AMISQueryVO contains the parameters for a query
        AMISQueryVO voForQuery;
        //All the AMISQueryVO(query) are stored in this list 
        List<AMISQueryVO> voForQueryList = new ArrayList<AMISQueryVO>();
//		//AMIS DATA table name
//		 String amisTable = AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(AMISConstants.AMIS.toString());
//		 List<Object> sqlResult = amisDao.getSingleResultValue(amisTable);
//		 String amisTablename = sqlResult.get(0).toString();

        //MARKETING YEAR table name
        String marketingYearTableName =  AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(AMISConstants.AMIS_MARKETING_YEAR.toString());
        List<Object> sqlResult = amisDao.getSingleResultValue(marketingYearTableName);
        String myTablename = sqlResult.get(0).toString();

        int databasesLenght = qvo.getDatabases().size();
        int countryLenght = qvo.getAreas().size();
        int productlenght = qvo.getItems().size();
        int elementslenght = qvo.getElements().size();
        int yearlenght = qvo.getYears().size();
        String dbkey = "";
        String dbvalue = "";
        String countrykey = "";
        String countryvalue = "";
        String productkey = "";
        String productvalue = "";
        String elementkey = "";
        String elementvalue = "";
        String view = "amis_all_datasources";
        boolean nationalFound = false;
        // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: DATASET lenght "+ databasesLenght);
        //Loop on the databases
        Set<String> databasesKeyset= qvo.getDatabases().keySet();
        Set<String> countrykeyset, productkeyset, elementkeyset, yearkeyset;
        int i=0;
        Iterator databasesIt = databasesKeyset.iterator();
        Iterator countryIt, productit, elementIt, yearIt;


        while(databasesIt.hasNext())
        {
            dbkey =(String)databasesIt.next();
            dbvalue = qvo.getDatabases().get(dbkey);
            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: database key "+ dbkey+ " value "+ dbvalue);
            //The national datasource will be analyzed later
            if(!dbkey.contains("NATIONAL"))
            {
                //Loop on the countries

                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: AREAS lenght "+ countryLenght);
                countrykeyset= qvo.getAreas().keySet();
                countryIt = countrykeyset.iterator();
                while(countryIt.hasNext())
                {
                    countrykey =(String)countryIt.next();
                    countryvalue = qvo.getAreas().get(countrykey);
                    //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: country key "+ countrykey+ " value "+ countryvalue);

                    //Loop on the products

                    //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product lenght "+ productlenght);
                    productkeyset= qvo.getItems().keySet();

                    productit = productkeyset.iterator();
                    while(productit.hasNext())
                    {
                        productkey =(String)productit.next();
                        productvalue = qvo.getItems().get(productkey);
                        //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product key "+ productkey+ " value "+ productvalue);

                        //Loop on the elements

                        // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: ELEMENTS lenght "+ elementslenght);
                        elementkeyset= qvo.getElements().keySet();

                        elementIt = elementkeyset.iterator();
                        while(elementIt.hasNext())
                        {
                            elementkey =(String)elementIt.next();
                            elementvalue = qvo.getElements().get(elementkey);
                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: element key "+ elementkey+ " value "+ elementvalue);		 


                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: YEARS lenght "+ yearlenght);
                            yearkeyset= qvo.getYears().keySet();

                            yearIt = yearkeyset.iterator();
                            while(yearIt.hasNext())
                            {
                                String yearkey =(String)yearIt.next();
                                String yearvalue = qvo.getYears().get(yearkey);
                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: year key "+ yearkey+ " value "+ yearvalue);

                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" START");
                                System.out.println("\nClass: AMISServiceImpl Function: createOlapTable Text: TABLENAME: "+view +" -- DATASOURCE: "+ dbkey+" -- COUNTRY CODE: "+ countrykey+" -- COUNTRY NAME: "+ countryvalue+" -- PRODUCT CODE: "+ productkey+" -- PRODUCT NAME: "+ productvalue+" -- ELEMENT CODE: "+ elementkey+" -- ELEMENT NAME: "+ elementvalue+" -- YEAR : "+ yearkey+ " yearvalue "+yearvalue);
                                voForQuery = new AMISQueryVO();
                                //voForQuery.setTablename(amisTablename);
                                voForQuery.setTablename(view);
                                voForQuery.setDatabase(dbkey);
                                voForQuery.setCountryCode(countrykey);
                                voForQuery.setCountryName(countryvalue);
                                voForQuery.setCommodityCode(productkey);
                                voForQuery.setCommodityName(productvalue);
                                voForQuery.setElementCode(elementkey);
                                voForQuery.setElementName(elementvalue);
                                voForQuery.setYear(yearvalue);
                                voForQuery.setValueType("TOTAL");
                                voForQueryList.add(voForQuery);
                                System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" END");
                                i++;
                            }

                        }
                    }
                }
            }
            else
            {
                nationalFound = true;
            }

        }

        //National can be found only if it's a logged user
        if(nationalFound)
        {
            String database = "NATIONAL";
            //Check which type of user is logged in
            if((userRole!=null)&&(!(userRole.equals(AMISConstants.AMIS_NOT_LOGGED_USER.toString()))))
            {
                // It can be either the secretariat or the country user
                // Only in these cases NATIONAL can be in databases map
                if(userRole.equals(AMISConstants.AMIS_SECRETARIAT.toString()))
                {
                    //Loop on the countries

                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: AREAS lenght "+ countryLenght);
                    countrykeyset= qvo.getAreas().keySet();
                    countryIt = countrykeyset.iterator();
                    while(countryIt.hasNext())
                    {
                        countrykey =(String)countryIt.next();
                        countryvalue = qvo.getAreas().get(countrykey);
                        //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: country key "+ countrykey+ " value "+ countryvalue);

                        //Loop on the products

                        //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product lenght "+ productlenght);
                        productkeyset= qvo.getItems().keySet();

                        productit = productkeyset.iterator();
                        while(productit.hasNext())
                        {
                            productkey =(String)productit.next();
                            productvalue = qvo.getItems().get(productkey);
                            //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product key "+ productkey+ " value "+ productvalue);

                            //Loop on the elements

                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: ELEMENTS lenght "+ elementslenght);
                            elementkeyset= qvo.getElements().keySet();

                            elementIt = elementkeyset.iterator();
                            while(elementIt.hasNext())
                            {
                                elementkey =(String)elementIt.next();
                                elementvalue = qvo.getElements().get(elementkey);
                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: element key "+ elementkey+ " value "+ elementvalue);		 


                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: YEARS lenght "+ yearlenght);
                                yearkeyset= qvo.getYears().keySet();

                                yearIt = yearkeyset.iterator();
                                while(yearIt.hasNext())
                                {
                                    String yearkey =(String)yearIt.next();
                                    String yearvalue = qvo.getYears().get(yearkey);
                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: year key "+ yearkey+ " value "+ yearvalue);

                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" START");
                                    System.out.println("\nClass: AMISServiceImpl Function: createOlapTable Text: VIEW: "+view +" -- DATASOURCE: "+ database+" -- COUNTRY CODE: "+ countrykey+" -- COUNTRY NAME: "+ countryvalue+" -- PRODUCT CODE: "+ productkey+" -- PRODUCT NAME: "+ productvalue+" -- ELEMENT CODE: "+ elementkey+" -- ELEMENT NAME: "+ elementvalue+" -- YEAR : "+ yearkey+ " yearvalue "+yearvalue);
                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" END");
                                    voForQuery = new AMISQueryVO();
                                    voForQuery.setTablename(view);
                                    voForQuery.setDatabase(database);
                                    voForQuery.setCountryCode(countrykey);
                                    voForQuery.setCountryName(countryvalue);
                                    voForQuery.setCommodityCode(productkey);
                                    voForQuery.setCommodityName(productvalue);
                                    voForQuery.setElementCode(elementkey);
                                    voForQuery.setElementName(elementvalue);
                                    voForQuery.setYear(yearvalue);
                                    voForQuery.setValueType("TOTAL");
                                    voForQueryList.add(voForQuery);

                                    i++;
                                }

                            }
                        }
                    }
                }
                else if(userRole.equals(AMISConstants.AMIS_COUNTRY_USER.toString()))
                {
                    //Take the name of the country throught the code of the country
                    if(userCountryCode!=null)
                    {
                        String countryname = qvo.getAreas().get(userCountryCode);

                        //Query for that country
                        productkeyset= qvo.getItems().keySet();

                        productit = productkeyset.iterator();
                        while(productit.hasNext())
                        {
                            productkey =(String)productit.next();
                            productvalue = qvo.getItems().get(productkey);
                            //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product key "+ productkey+ " value "+ productvalue);

                            //Loop on the elements

                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: ELEMENTS lenght "+ elementslenght);
                            elementkeyset= qvo.getElements().keySet();

                            elementIt = elementkeyset.iterator();
                            while(elementIt.hasNext())
                            {
                                elementkey =(String)elementIt.next();
                                elementvalue = qvo.getElements().get(elementkey);
                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: element key "+ elementkey+ " value "+ elementvalue);		 


                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: YEARS lenght "+ yearlenght);
                                yearkeyset= qvo.getYears().keySet();

                                yearIt = yearkeyset.iterator();
                                while(yearIt.hasNext())
                                {
                                    String yearkey =(String)yearIt.next();
                                    String yearvalue = qvo.getYears().get(yearkey);
                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: year key "+ yearkey+ " value "+ yearvalue);

                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" START");
                                    System.out.println("\nClass: AMISServiceImpl Function: createOlapTable Text: VIEW: "+view +" -- DATASOURCE: "+ database+" -- COUNTRY CODE: "+ userCountryCode+" -- COUNTRY NAME: "+ countryname+" -- PRODUCT CODE: "+ productkey+" -- PRODUCT NAME: "+ productvalue+" -- ELEMENT CODE: "+ elementkey+" -- ELEMENT NAME: "+ elementvalue+" -- YEAR : "+ yearkey + " yearvalue "+yearvalue);
                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" END");
                                    voForQuery = new AMISQueryVO();
                                    voForQuery.setTablename(view);
                                    voForQuery.setDatabase(database);
                                    voForQuery.setCountryCode(userCountryCode);
                                    voForQuery.setCountryName(countryname);
                                    voForQuery.setCommodityCode(productkey);
                                    voForQuery.setCommodityName(productvalue);
                                    voForQuery.setElementCode(elementkey);
                                    voForQuery.setElementName(elementvalue);
                                    voForQuery.setYear(yearvalue);
                                    voForQuery.setValueType("TOTAL");
                                    voForQueryList.add(voForQuery);
                                    i++;

//									 database     | text                   | 
//									 region_code  | text                   | 
//									 region_name  | text                   | 
//									 product_code | text                   | 
//									 product_name | text                   | 
//									 element_code | text                   | 
//									 element_name | text                   | 
//									 units        | character varying(255) | 
//									 year         | date                   | 
//									 month        | text                   | 
//									 value        | double precision       | 
//									 flag         | text                   | 
//									 value_type   | text  
                                }

                            }
                        }
                    }
                }
            }
        }
        //System.out.println("server end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        AMISResultVO amisrvo;
        //System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: The query list has been created!! It contains  "+ voForQueryList.size()+" elements!");
        int ilist=0;
        int iNotFoundlist=0;
        HashMap<String, String> notFoundElTitle = new HashMap<String, String>();
        AMISQueryVO voForQueryPop;
        for(AMISQueryVO voQuery: voForQueryList)
        {
            boolean cbsCommodity = false;
            //System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: The query list has been created!! voQuery.getElementCode()  "+ voQuery.getElementCode());
            //Population case
            if((voQuery.getElementCode()!=null)&&(voQuery.getElementCode().equals("1")))
            {
                //System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: The query list has been created!! voQuery.getElementCode().equals(1))  ");
                //It needs a separate case because Population has not a row in Marketing Year table
                voForQueryPop = new AMISQueryVO();
                voForQueryPop.setTablename(voQuery.getTablename());
                voForQueryPop.setDatabase(voQuery.getDatabase());
                voForQueryPop.setCountryCode(voQuery.getCountryCode());
                voForQueryPop.setCountryName(voQuery.getCountryName());
                //Static choice: using "Maize" commodity to take the rigth month
                //In this way we can select the best month for Population = the best month for Maize
                voForQueryPop.setCommodityCode("5");
                voForQueryPop.setCommodityName("Maize");
                voForQueryPop.setElementCode(voQuery.getElementCode());
                voForQueryPop.setElementName(voQuery.getElementName());
                voForQueryPop.setYear(voQuery.getYear());
                voForQueryPop.setValueType(voQuery.getValueType());
                //voForQueryList.add(voForQuery);
                amisrvo = new AMISResultVO();
                amisrvo = AMISGetCodingSystem.getMarketingYearWithMyTablename(voForQueryPop, amisrvo, myTablename, amisDao);
            }
            else
            {//Checking if it's possible to call the MARKETING YEAR TABLE
                amisrvo = new AMISResultVO();
                int j=0;

                if(voQuery.getCommodityCode()!=null)
                {
                    for(j=0; j< CCBS.CBS_COMMODITY_CODE.length; j++)
                    {
                        if(voQuery.getCommodityCode().equals(CCBS.CBS_COMMODITY_CODE[j]))
                        {
                            cbsCommodity = true;
                            break;
                        }
                    }
                }
                if(cbsCommodity)
                {
                    amisrvo = AMISGetCodingSystem.getMarketingYearWithMyTablename(voQuery, amisrvo, myTablename, amisDao);
                    //If the cuntry is not in the marketing year table for that commodity
                    //NO check for marketing year
                    if((amisrvo==null)||(amisrvo.getCodes()==null)||(amisrvo.getCodes().size()==0))
                    {
                        AMISCodesModelData amisCodesModelData = AMISGetCodingSystem.getElement(voQuery, aMISCbsServerUtility, amisDao, null);
                        if(amisCodesModelData!=null)
                        {
                            //The element has been found
                            listToReturn.add(createHashMap(voQuery, amisCodesModelData, true));
                        }
                        else
                        {
                            //The element has NOT been found
                            String title= voQuery.getCommodityName()+" "+ voQuery.getCountryName()+" "+voQuery.getElementName();
                            if(!notFoundElTitle.containsKey(title))
                            {
                                listToReturn.add(createHashMap(voQuery, amisCodesModelData, false));
                                notFoundElTitle.put(title, "FALSE");
                            }
                            iNotFoundlist++;
                        }
                        //System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: i "+ ilist);
                        ilist++;
                    }
                }
                else
                {
                    AMISCodesModelData amisCodesModelData = AMISGetCodingSystem.getElement(voQuery, aMISCbsServerUtility, amisDao, null);
                    if(amisCodesModelData!=null)
                    {
                        //The element has been found
                        listToReturn.add(createHashMap(voQuery, amisCodesModelData, true));
                    }
                    else
                    {
                        //The element has NOT been found
                        String title= voQuery.getCommodityName()+" "+ voQuery.getCountryName()+" "+voQuery.getElementName();
                        if(!notFoundElTitle.containsKey(title))
                        {
                            listToReturn.add(createHashMap(voQuery, amisCodesModelData, false));
                            notFoundElTitle.put(title, "FALSE");
                        }
                        iNotFoundlist++;
                    }
                    //System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: i "+ ilist);
                    ilist++;
                }
            }

            if(((amisrvo!=null)&&((amisrvo.getCodes()!=null))&&(amisrvo.getCodes().size()!=0))&&(voQuery.getElementCode().equals("1")||(cbsCommodity)))
            {
                //General Case				 
                String startMonth = amisrvo.getCodes().get(0).getCode();
                int startMonthInteger = aMISCbsServerUtility.convertMonthToNumber(startMonth);
                int endMonthInteger = startMonthInteger-1;
                if(endMonthInteger==0)
                {
                    //If the start month is January.... the end month will be December
                    endMonthInteger = 12;
                }
                String endMonth = aMISCbsServerUtility.convertMonthToString(endMonthInteger);
                AMISCodesModelData amisCodesModelData = AMISGetCodingSystem.getElement(voQuery, aMISCbsServerUtility, amisDao, endMonth);
                if(amisCodesModelData!=null)
                {
                    //The element has been found
                    listToReturn.add(createHashMap(voQuery, amisCodesModelData, true));
                }
                else
                {
                    //The element has NOT been found
                    String title= voQuery.getCommodityName()+" "+ voQuery.getCountryName()+" "+voQuery.getElementName();
                    if(!notFoundElTitle.containsKey(title))
                    {
                        listToReturn.add(createHashMap(voQuery, amisCodesModelData, false));
                        notFoundElTitle.put(title, "FALSE");
                    }
                    iNotFoundlist++;
                }
                //System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: i "+ ilist);
                ilist++;
            }
        }

        if(iNotFoundlist == ilist)
        {
            //The list contains only "NOT FOUND" elements... so delete everything to show the right message
            listToReturn.clear();
        }
        return listToReturn;
    }

    //userCountryCode indicates the country which the user belong to
    //userRole can be "Secretariat", "Country User" and "Not Logged User"
    public List<HashMap<String, String>> createOlapTable(AMISQueryVO qvo, String userRole, String userCountryCode)
    {
        List<HashMap<String, String>> listToReturn = new ArrayList<HashMap<String,String>>();
        //Loop to create the queries

        //A AMISQueryVO contains the parameters for a query
        AMISQueryVO voForQuery;
        //All the AMISQueryVO(query) are stored in this list 
        List<AMISQueryVO> voForQueryList = new ArrayList<AMISQueryVO>();
//		//AMIS DATA table name
//		 String amisTable = AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(AMISConstants.AMIS.toString());
//		 List<Object> sqlResult = amisDao.getSingleResultValue(amisTable);
//		 String amisTablename = sqlResult.get(0).toString();

        //MARKETING YEAR table name
        String marketingYearTableName =  AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(AMISConstants.AMIS_MARKETING_YEAR.toString());
        List<Object> sqlResult = amisDao.getSingleResultValue(marketingYearTableName);
        String myTablename = sqlResult.get(0).toString();

        int databasesLenght = qvo.getDatabases().size();
        int countryLenght = qvo.getAreas().size();
        int productlenght = qvo.getItems().size();
        int elementslenght = qvo.getElements().size();
        int yearlenght = qvo.getYears().size();
        String dbkey = "";
        String dbvalue = "";
        String countrykey = "";
        String countryvalue = "";
        String productkey = "";
        String productvalue = "";
        String elementkey = "";
        String elementvalue = "";
        // String view = "amis_all_datasources";
        String view = AMISConstants.amis_all_datasources.toString();
        boolean nationalFound = false;
        // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: DATASET lenght "+ databasesLenght);
        //Loop on the databases
        Set<String> databasesKeyset= qvo.getDatabases().keySet();
        Set<String> countrykeyset, productkeyset, elementkeyset, yearkeyset;
        int i=0;
        Iterator databasesIt = databasesKeyset.iterator();
        Iterator countryIt, productit, elementIt, yearIt;

        while(databasesIt.hasNext())
        {
            dbkey =(String)databasesIt.next();
            dbvalue = qvo.getDatabases().get(dbkey);
            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: database key "+ dbkey+ " value "+ dbvalue);
            //The national datasource will be analyzed later
            if(!dbkey.contains("NATIONAL"))
            {
                //Loop on the countries

                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: AREAS lenght "+ countryLenght);
                countrykeyset= qvo.getAreas().keySet();
                countryIt = countrykeyset.iterator();
                while(countryIt.hasNext())
                {
                    countrykey =(String)countryIt.next();
                    countryvalue = qvo.getAreas().get(countrykey);
                    //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: country key "+ countrykey+ " value "+ countryvalue);

                    //Loop on the products

                    //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product lenght "+ productlenght);
                    productkeyset= qvo.getItems().keySet();

                    productit = productkeyset.iterator();
                    while(productit.hasNext())
                    {
                        productkey =(String)productit.next();
                        productvalue = qvo.getItems().get(productkey);
                        //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product key "+ productkey+ " value "+ productvalue);

                        //Loop on the elements

                        // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: ELEMENTS lenght "+ elementslenght);
                        elementkeyset= qvo.getElements().keySet();

                        elementIt = elementkeyset.iterator();
                        while(elementIt.hasNext())
                        {
                            elementkey =(String)elementIt.next();
                            elementvalue = qvo.getElements().get(elementkey);
                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: element key "+ elementkey+ " value "+ elementvalue);		 


                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: YEARS lenght "+ yearlenght);
                            yearkeyset= qvo.getYears().keySet();

                            yearIt = yearkeyset.iterator();
                            while(yearIt.hasNext())
                            {
                                String yearkey =(String)yearIt.next();
                                String yearvalue = qvo.getYears().get(yearkey);
                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: year key "+ yearkey+ " value "+ yearvalue);

                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" START");
                                System.out.println("\nClass: AMISServiceImpl Function: createOlapTable Text: TABLENAME: "+view +" -- DATASOURCE: "+ dbkey+" -- COUNTRY CODE: "+ countrykey+" -- COUNTRY NAME: "+ countryvalue+" -- PRODUCT CODE: "+ productkey+" -- PRODUCT NAME: "+ productvalue+" -- ELEMENT CODE: "+ elementkey+" -- ELEMENT NAME: "+ elementvalue+" -- YEAR : "+ yearkey+ " yearvalue "+yearvalue);
                                voForQuery = new AMISQueryVO();
                                //voForQuery.setTablename(amisTablename);
                                voForQuery.setTablename(view);
                                voForQuery.setDatabase(dbkey);
                                voForQuery.setCountryCode(countrykey);
                                voForQuery.setCountryName(countryvalue);
                                voForQuery.setCommodityCode(productkey);
                                voForQuery.setCommodityName(productvalue);
                                voForQuery.setElementCode(elementkey);
                                voForQuery.setElementName(elementvalue);
                                voForQuery.setYear(yearvalue);
                                voForQuery.setValueType("TOTAL");
                                voForQueryList.add(voForQuery);
                                System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" END");
                                i++;
                            }
                        }
                    }
                }
            }
            else
            {
                nationalFound = true;
            }

        }

        //National can be found only if it's a logged user
        if(nationalFound)
        {
            String database = "NATIONAL";
            //Check which type of user is logged in
            if((userRole!=null)&&(!(userRole.equals(AMISConstants.AMIS_NOT_LOGGED_USER.toString()))))
            {
                // It can be either the secretariat or the country user
                // Only in these cases NATIONAL can be in databases map
                if(userRole.equals(AMISConstants.AMIS_SECRETARIAT.toString()))
                {
                    //Loop on the countries

                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: AREAS lenght "+ countryLenght);
                    countrykeyset= qvo.getAreas().keySet();
                    countryIt = countrykeyset.iterator();
                    while(countryIt.hasNext())
                    {
                        countrykey =(String)countryIt.next();
                        countryvalue = qvo.getAreas().get(countrykey);
                        //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: country key "+ countrykey+ " value "+ countryvalue);

                        //Loop on the products

                        //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product lenght "+ productlenght);
                        productkeyset= qvo.getItems().keySet();

                        productit = productkeyset.iterator();
                        while(productit.hasNext())
                        {
                            productkey =(String)productit.next();
                            productvalue = qvo.getItems().get(productkey);
                            //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product key "+ productkey+ " value "+ productvalue);

                            //Loop on the elements

                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: ELEMENTS lenght "+ elementslenght);
                            elementkeyset= qvo.getElements().keySet();

                            elementIt = elementkeyset.iterator();
                            while(elementIt.hasNext())
                            {
                                elementkey =(String)elementIt.next();
                                elementvalue = qvo.getElements().get(elementkey);
                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: element key "+ elementkey+ " value "+ elementvalue);		 


                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: YEARS lenght "+ yearlenght);
                                yearkeyset= qvo.getYears().keySet();

                                yearIt = yearkeyset.iterator();
                                while(yearIt.hasNext())
                                {
                                    String yearkey =(String)yearIt.next();
                                    String yearvalue = qvo.getYears().get(yearkey);
                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: year key "+ yearkey+ " value "+ yearvalue);

                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" START");
                                    System.out.println("\nClass: AMISServiceImpl Function: createOlapTable Text: VIEW: "+view +" -- DATASOURCE: "+ database+" -- COUNTRY CODE: "+ countrykey+" -- COUNTRY NAME: "+ countryvalue+" -- PRODUCT CODE: "+ productkey+" -- PRODUCT NAME: "+ productvalue+" -- ELEMENT CODE: "+ elementkey+" -- ELEMENT NAME: "+ elementvalue+" -- YEAR : "+ yearkey+ " yearvalue "+yearvalue);
                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" END");
                                    voForQuery = new AMISQueryVO();
                                    voForQuery.setTablename(view);
                                    voForQuery.setDatabase(database);
                                    voForQuery.setCountryCode(countrykey);
                                    voForQuery.setCountryName(countryvalue);
                                    voForQuery.setCommodityCode(productkey);
                                    voForQuery.setCommodityName(productvalue);
                                    voForQuery.setElementCode(elementkey);
                                    voForQuery.setElementName(elementvalue);
                                    voForQuery.setYear(yearvalue);
                                    voForQuery.setValueType("TOTAL");
                                    voForQueryList.add(voForQuery);

                                    i++;
                                }

                            }
                        }
                    }
                }
                else if(userRole.equals(AMISConstants.AMIS_COUNTRY_USER.toString()))
                {
                    //Take the name of the country throught the code of the country
                    if(userCountryCode!=null)
                    {
                        String countryname = qvo.getAreas().get(userCountryCode);

                        //Query for that country
                        productkeyset= qvo.getItems().keySet();

                        productit = productkeyset.iterator();
                        while(productit.hasNext())
                        {
                            productkey =(String)productit.next();
                            productvalue = qvo.getItems().get(productkey);
                            //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product key "+ productkey+ " value "+ productvalue);

                            //Loop on the elements

                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: ELEMENTS lenght "+ elementslenght);
                            elementkeyset= qvo.getElements().keySet();

                            elementIt = elementkeyset.iterator();
                            while(elementIt.hasNext())
                            {
                                elementkey =(String)elementIt.next();
                                elementvalue = qvo.getElements().get(elementkey);
                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: element key "+ elementkey+ " value "+ elementvalue);		 


                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: YEARS lenght "+ yearlenght);
                                yearkeyset= qvo.getYears().keySet();

                                yearIt = yearkeyset.iterator();
                                while(yearIt.hasNext())
                                {
                                    String yearkey =(String)yearIt.next();
                                    String yearvalue = qvo.getYears().get(yearkey);
                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: year key "+ yearkey+ " value "+ yearvalue);

                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" START");
                                    //	 System.out.println("\nClass: AMISServiceImpl Function: createOlapTable Text: VIEW: "+view +" -- DATASOURCE: "+ database+" -- COUNTRY CODE: "+ userCountryCode+" -- COUNTRY NAME: "+ countryname+" -- PRODUCT CODE: "+ productkey+" -- PRODUCT NAME: "+ productvalue+" -- ELEMENT CODE: "+ elementkey+" -- ELEMENT NAME: "+ elementvalue+" -- YEAR : "+ yearkey + " yearvalue "+yearvalue);
                                    // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" END");
                                    voForQuery = new AMISQueryVO();
                                    voForQuery.setTablename(view);
                                    voForQuery.setDatabase(database);
                                    voForQuery.setCountryCode(userCountryCode);
                                    voForQuery.setCountryName(countryname);
                                    voForQuery.setCommodityCode(productkey);
                                    voForQuery.setCommodityName(productvalue);
                                    voForQuery.setElementCode(elementkey);
                                    voForQuery.setElementName(elementvalue);
                                    voForQuery.setYear(yearvalue);
                                    voForQuery.setValueType("TOTAL");
                                    voForQueryList.add(voForQuery);
                                    i++;

//									 database     | text                   | 
//									 region_code  | text                   | 
//									 region_name  | text                   | 
//									 product_code | text                   | 
//									 product_name | text                   | 
//									 element_code | text                   | 
//									 element_name | text                   | 
//									 units        | character varying(255) | 
//									 year         | date                   | 
//									 month        | text                   | 
//									 value        | double precision       | 
//									 flag         | text                   | 
//									 value_type   | text  
                                }

                            }
                        }
                    }
                }
            }
            else
            {
                //Now also the not logged user can see the National Data

                //The behaviour is the same of the Amis Secretariat User
                //Loop on the countries

                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: AREAS lenght "+ countryLenght);
                countrykeyset= qvo.getAreas().keySet();
                countryIt = countrykeyset.iterator();
                while(countryIt.hasNext())
                {
                    countrykey =(String)countryIt.next();
                    countryvalue = qvo.getAreas().get(countrykey);
                    //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: country key "+ countrykey+ " value "+ countryvalue);

                    //Loop on the products

                    //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product lenght "+ productlenght);
                    productkeyset= qvo.getItems().keySet();

                    productit = productkeyset.iterator();
                    while(productit.hasNext())
                    {
                        productkey =(String)productit.next();
                        productvalue = qvo.getItems().get(productkey);
                        //	 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: product key "+ productkey+ " value "+ productvalue);

                        //Loop on the elements

                        // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: ELEMENTS lenght "+ elementslenght);
                        elementkeyset= qvo.getElements().keySet();

                        elementIt = elementkeyset.iterator();
                        while(elementIt.hasNext())
                        {
                            elementkey =(String)elementIt.next();
                            elementvalue = qvo.getElements().get(elementkey);
                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: element key "+ elementkey+ " value "+ elementvalue);


                            // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: YEARS lenght "+ yearlenght);
                            yearkeyset= qvo.getYears().keySet();

                            yearIt = yearkeyset.iterator();
                            while(yearIt.hasNext())
                            {
                                String yearkey =(String)yearIt.next();
                                String yearvalue = qvo.getYears().get(yearkey);
                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: year key "+ yearkey+ " value "+ yearvalue);

                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" START");
                                System.out.println("\nClass: AMISServiceImpl Function: createOlapTable Text: VIEW: "+view +" -- DATASOURCE: "+ database+" -- COUNTRY CODE: "+ countrykey+" -- COUNTRY NAME: "+ countryvalue+" -- PRODUCT CODE: "+ productkey+" -- PRODUCT NAME: "+ productvalue+" -- ELEMENT CODE: "+ elementkey+" -- ELEMENT NAME: "+ elementvalue+" -- YEAR : "+ yearkey+ " yearvalue "+yearvalue);
                                // System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: PARAMETERS FOR QUERY NUMBER "+i +" END");
                                voForQuery = new AMISQueryVO();
                                voForQuery.setTablename(view);
                                voForQuery.setDatabase(database);
                                voForQuery.setCountryCode(countrykey);
                                voForQuery.setCountryName(countryvalue);
                                voForQuery.setCommodityCode(productkey);
                                voForQuery.setCommodityName(productvalue);
                                voForQuery.setElementCode(elementkey);
                                voForQuery.setElementName(elementvalue);
                                voForQuery.setYear(yearvalue);
                                voForQuery.setValueType("TOTAL");
                                voForQueryList.add(voForQuery);

                                i++;
                            }

                        }
                    }
                }
            }
        }
        System.out.println("server end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! voForQueryList.size() "+voForQueryList.size());
        //start
//		 HashMap<String, String> notFoundElTitle = new HashMap<String, String>();
//		 int iNotFoundlist=0, ilist=0;
//		 for(AMISQueryVO voQuery: voForQueryList)
//		 {
//			 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: The query list has been created!! voQuery.getElementCode()  "+ voQuery.getElementCode());
//			 AMISCodesModelData amisCodesModelData = AMISGetCodingSystem.getElement(voQuery, amisDao);
//			 if(amisCodesModelData!=null)
//			 {
//				 //The element has been found
//				 listToReturn.add(createHashMap(voQuery, amisCodesModelData, true));
//			 }
//			 else
//			 {
//				 //The element has NOT been found
//				 String title= voQuery.getCommodityName()+" "+ voQuery.getCountryName()+" "+voQuery.getElementName();
//				 if(!notFoundElTitle.containsKey(title))
//				 {
//					 listToReturn.add(createHashMap(voQuery, amisCodesModelData, false));
//					 notFoundElTitle.put(title, "FALSE");
//				 }
//				 iNotFoundlist++;
//			 }
//			 System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: i "+ ilist);
//			 ilist++;
//		 }
        //end
        HashMap<String, String> notFoundElTitle = new HashMap<String, String>();
        int iNotFoundlist=0, ilist=0;
        AMISQueryVO voQueryList = new AMISQueryVO();
        voQueryList.setTablename(view);
        for(AMISQueryVO voQuery: voForQueryList)
        {
            if(ilist==0)
            {
                //This is the first element
                voQueryList.setDatabase("'"+voQuery.getDatabase()+"'");
                voQueryList.setCountryCode("'"+voQuery.getCountryCode()+"'");
                voQueryList.setCommodityCode("'"+voQuery.getCommodityCode()+"'");
                voQueryList.setElementCode("'"+voQuery.getElementCode()+"'");
                voQueryList.setYear("'"+voQuery.getYear()+"'");
            }
            else
            {
                if(!(voQueryList.getDatabase().contains(voQuery.getDatabase())))
                {
                    voQueryList.setDatabase(voQueryList.getDatabase()+", '"+ voQuery.getDatabase()+"'");
                }
                if(!(voQueryList.getCountryCode().contains(voQuery.getCountryCode())))
                {
                    voQueryList.setCountryCode(voQueryList.getCountryCode()+", '"+ voQuery.getCountryCode()+"'");
                }
                if(!(voQueryList.getCommodityCode().contains(voQuery.getCommodityCode())))
                {
                    voQueryList.setCommodityCode(voQueryList.getCommodityCode()+", '"+ voQuery.getCommodityCode()+"'");
                }
                if(!(voQueryList.getElementCode().contains(voQuery.getElementCode())))
                {
                    voQueryList.setElementCode(voQueryList.getElementCode()+", '"+ voQuery.getElementCode()+"'");
                }
                if(!(voQueryList.getYear().contains(voQuery.getYear())))
                {
                    voQueryList.setYear(voQueryList.getYear()+", '"+ voQuery.getYear()+"'");
                }
            }
            //System.out.println("Class: AMISServiceImpl Function: createOlapTable Text: i "+ ilist);
            ilist++;
        }
//        System.out.println("Class: AMISServiceImpl Function: createOlapTable Text:  voQueryList Database =*"+ voQueryList.getDatabase()+"*");
//        System.out.println("Class: AMISServiceImpl Function: createOlapTable Text:  voQueryList country code =*"+ voQueryList.getCountryCode()+"*");
//        System.out.println("Class: AMISServiceImpl Function: createOlapTable Text:  voQueryList commodity code =*"+ voQueryList.getCommodityCode()+"*");
//        System.out.println("Class: AMISServiceImpl Function: createOlapTable Text:  voQueryList element code =*"+ voQueryList.getElementCode()+"*");
//        System.out.println("Class: AMISServiceImpl Function: createOlapTable Text:  voQueryList year: " + voQueryList.getYear());
        List<ClientAmisDatasetResult> clientAmisDatasetResultList = AMISGetCodingSystem.getElement(voQueryList, amisDao);
        if(clientAmisDatasetResultList!=null)
        {
            for(ClientAmisDatasetResult clientAmisDatasetResult:clientAmisDatasetResultList)
            {
                listToReturn.add(createHashMap(clientAmisDatasetResult));
            }
        }
        else
        {
            listToReturn.clear();
        }
        return listToReturn;
    }

    private HashMap<String, String> createHashMap(ClientAmisDatasetResult clientAmisDatasetResult) {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("FOUND_ELEMENT", "TRUE");
        hm.put("DATABASE", clientAmisDatasetResult.getDatabase());
        hm.put("COUNTRY_CODE", clientAmisDatasetResult.getRegion_code());
        hm.put("COUNTRY_NAME", clientAmisDatasetResult.getRegion_name());
        hm.put("PRODUCT_CODE", clientAmisDatasetResult.getProduct_code());
        hm.put("PRODUCT_NAME", clientAmisDatasetResult.getProduct_name());
        hm.put("ELEMENT_CODE", clientAmisDatasetResult.getElement_code());
        hm.put("ELEMENT_NAME", clientAmisDatasetResult.getElement_name());
        hm.put("YEAR", clientAmisDatasetResult.getYear());
        hm.put("VALUE_TYPE", clientAmisDatasetResult.getValue_type());
        hm.put("UNIT", clientAmisDatasetResult.getUnits());
        hm.put("MONTH", clientAmisDatasetResult.getMonth());
        hm.put("VALUE", clientAmisDatasetResult.getValue());
        hm.put("FLAG", clientAmisDatasetResult.getAnnotation());
        return hm;
    }

    private HashMap<String, String> createHashMap(AMISQueryVO voQuery, AMISCodesModelData amisCodesModelData, boolean found) {
        HashMap<String, String> hm = new HashMap<String, String>();
        //("SELECT units, month, value, flag ");
        //String code, String label, String domain
        //String valueFlagResult = sqlResult.get(i)[2].toString()+ "**"+ sqlResult.get(i)[3].toString();

//		voForQuery = new AMISQueryVO();
//		 voForQuery.setTablename(view);
//		 voForQuery.setDatabase(database);
//		 voForQuery.setCountryCode(userCountryCode);
//		 voForQuery.setCountryName(countryname);
//		 voForQuery.setCommodityCode(productkey);
//		 voForQuery.setCommodityName(productvalue);
//		 voForQuery.setElementCode(elementkey);
//		 voForQuery.setElementName(elementvalue);
//		 voForQuery.setYear(yearvalue);
//		 voForQuery.setValueType("TOTAL");
//		 voForQueryList.add(voForQuery);

        if(!found)
        {
            //The element has NOT been found
            hm.put("FOUND_ELEMENT", "FALSE");
        }
        else
        {
            //The element has been found
            hm.put("FOUND_ELEMENT", "TRUE");
        }
        hm.put("DATABASE", voQuery.getDatabase());
        hm.put("COUNTRY_CODE", voQuery.getCountryCode());
        hm.put("COUNTRY_NAME", voQuery.getCountryName());
        hm.put("PRODUCT_CODE", voQuery.getCommodityCode());
        hm.put("PRODUCT_NAME", voQuery.getCommodityName());
        hm.put("ELEMENT_CODE", voQuery.getElementCode());
        hm.put("ELEMENT_NAME", voQuery.getElementName());
        hm.put("YEAR", voQuery.getYear());
        hm.put("VALUE_TYPE", voQuery.getValueType());

        if(found)
        {
            String valueFlag = amisCodesModelData.getDomain();
            int valueFlagSize= 0;
            if(valueFlag!=null)
            {
                valueFlagSize = valueFlag.length();
            }
            String value = "";
            String flag = "";
            if(valueFlag!=null)
            {
                int index = valueFlag.indexOf("**");
                //index=-1 only if either value and flag are null
                if(index !=-1)
                {
                    if(index>0)
                    {
                        value = valueFlag.substring(0, index);
                    }
                    if((index+2)<valueFlagSize)
                    {
                        flag = valueFlag.substring(index+2);
                    }
                }

            }
            System.out.println("Class: AMISServiceImpl Function: createHashMap Text: value "+ value+" flag "+flag);
            hm.put("UNIT", amisCodesModelData.getCode());
            hm.put("MONTH", amisCodesModelData.getLabel());
            hm.put("VALUE", value);
            hm.put("FLAG", flag);
        }

        return hm;
    }

    public void setDbAdapter(DBAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    public void setDir(File dir) {
        this.dir = dir.getPath();
    }

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public AMISDao getAmisDao() {
        return amisDao;
    }

    public void setAmisDao(AMISDao amisDao) {
        this.amisDao = amisDao;
    }

    public void setTableExcel(TableExcel tableExcel) {
        this.tableExcel = tableExcel;
    }

    public void setFileFactory(FileFactory fileFactory) {
        this.fileFactory = fileFactory;
    }

    public void setFenixPermissionManager(
            FenixPermissionManager fenixPermissionManager) {
        this.fenixPermissionManager = fenixPermissionManager;
    }

    public void setDatasetDao(DatasetDao datasetDao) {
        this.datasetDao = datasetDao;
    }

    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setCbsMonths_ip(String cbsMonthsIp) {
        cbsMonths_ip = cbsMonthsIp;
    }

    public void setCbsMonths_port(String cbsMonthsPort) {
        cbsMonths_port = cbsMonthsPort;
    }

    public void setAmisSupplyDemandExcel(AMISSupplyDemandExcel amisSupplyDemandExcel) {
        this.amisSupplyDemandExcel = amisSupplyDemandExcel;
    }

    public void setAmisRequestBuilder(AMISRequestBuilder amisRequestBuilder) {
        this.amisRequestBuilder = amisRequestBuilder;
    }



//	public CustomDatasetDao getCustomDatasetDao() {
//		return customDatasetDao;
//	}
//
//	public void setCustomDatasetDao(CustomDatasetDao customDatasetDao) {
//		this.customDatasetDao = customDatasetDao;
//	}
//	


}
