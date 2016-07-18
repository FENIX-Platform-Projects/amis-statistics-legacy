package org.fao.fenix.web.modules.amis.server;

import java.util.*;

import org.apache.log4j.Logger;
import org.fao.fenix.core.persistence.amis.AMISDao;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentDatasetView;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.amis.common.vo.ClientAmisDatasetResult;
import org.fao.fenix.web.modules.amis.common.vo.ClientCbsDatasetResult;

public class AMISGetCodingSystem {
	
	private final static Logger LOGGER = Logger.getLogger(AMISGetCodingSystem.class);
	
	public static AMISResultVO getG20Countries(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();

         if(qvo.getSelectedDataset()!=null && qvo.getSelectedDataset().equals(AMISCurrentDatasetView.NATIONAL.toString()))
             qvo.setSql(AMISCodingSystemQueryBuilder.getG20NationalCountriesQuery(qvo, tablename));
     	 else
             qvo.setSql(AMISCodingSystemQueryBuilder.getG20CountriesQuery(qvo, tablename));

        rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("--- getG20Countries rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static AMISResultVO getG20TotalCountries(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        qvo.setSql(AMISCodingSystemQueryBuilder.getG20TotalCountriesQuery(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getG20Countries rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static AMISResultVO getG20CountriesWithCrops(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
		qvo.setTypeOfOutput("AMIS_COUNTRIES");
		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String countryTablename = sqlResult.get(0).toString();
	    
	    qvo.setTypeOfOutput("AMIS_COUNTRY_CROP_COUNT");
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String countryCropTablename = sqlResult.get(0).toString();
	    
	    qvo.setSql(AMISCodingSystemQueryBuilder.getG20CountriesWithCropsQuery(qvo, countryTablename, countryCropTablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
				
		LOGGER.info("getG20CountriesWithCrops rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static AMISResultVO getProductsWithCrops(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
		qvo.setTypeOfOutput("AMIS_PRODUCTS");
		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String countryTablename = sqlResult.get(0).toString();
	    
	    qvo.setTypeOfOutput("AMIS_COUNTRY_CROP_COUNT");
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String countryCropTablename = sqlResult.get(0).toString();
	    
	    qvo.setSql(AMISCodingSystemQueryBuilder.getProductsWithCropsQuery(qvo, countryTablename, countryCropTablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
				
		LOGGER.info("getProductsWithCrops rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static AMISResultVO getCropsNum(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){	    
	    qvo.setTypeOfOutput("AMIS_COUNTRY_CROP_COUNT");
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
	    List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String countryCropTablename = sqlResult.get(0).toString();
	    
	    qvo.setSql(AMISCodingSystemQueryBuilder.getCropsNumQuery(qvo, countryCropTablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
				
		LOGGER.info("getProductsWithCrops rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	//lastMonthMarketingyear is null if the product is not a commodity or if the commodity-country is not in the Marketing year table .... so no check for marketing year
	//Because the month column in that case will be only "0"
	//In fact for "Not cbs tool commodity" you can not change the month column
	public static AMISCodesModelData getElement(AMISQueryVO qvo, AMISCbsServerUtility aMISCbsServerUtility, AMISDao amisDao, String lastMonthMarketingyear){	    
	 //   qvo.setTypeOfOutput("AMIS_COUNTRY_CROP_COUNT");
	  //  qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
//		
//	    List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
//	    String countryCropTablename = sqlResult.get(0).toString();
		String bestMonth="";
	    qvo.setSql(AMISCodingSystemQueryBuilder.getElement(qvo));
		
	    String year = qvo.getYear();
		//2010-01-01
		int index = year.indexOf("-01-01");
		String onlyYear = year.substring(0, index);
	    
	    AMISResultVO rvo = new AMISResultVO();
		rvo.setCodes(getCodingSystemForOLAPElements(qvo, amisDao));
		//Check the month
		if(rvo.getCodes().size()>1)
		{
			//If the month is 0(validate).... it's not necessary to do a check
			int i=0;
			for(i=0; i< rvo.getCodes().size(); i++)
			{
				AMISCodesModelData amisCodesModelData = rvo.getCodes().get(i);
				//Take the month
				if((amisCodesModelData.getLabel()!=null)&&(amisCodesModelData.getLabel().equals("0")))
				{
					return amisCodesModelData;
				}
			}
			
			//If the month 0 has not been found... select the element that has the best month
			List<Object[]> sqlResultForLastForecast = new ArrayList<Object[]>();
			Object[] objArray;
			for(i=0; i< rvo.getCodes().size(); i++)
			{
				AMISCodesModelData amisCodesModelData = rvo.getCodes().get(i);
				//Take the month
				String month = amisCodesModelData.getLabel();
				objArray = new Object[3];
				objArray[0]= onlyYear;
				objArray[2]= month;
				sqlResultForLastForecast.add(objArray);
			}
//			for(Object obj[] : sqlResultForLastForecast)
//			{
//				System.out.println("Class: AmisserviceImpl Function: getDataFromAnElement Text: Month:  "+obj[0]+" Year:  "+obj[2]);
//			}
			//The "not cbs tool commodity" can not arrive hear because all the elements will have "0" for month column
			List<HashMap<String, String>> monthForecastToQuery = aMISCbsServerUtility.lastMonthForecastForYear(sqlResultForLastForecast, lastMonthMarketingyear);
			
			for(HashMap<String, String> hashMap : monthForecastToQuery)
			{
				Set<String> keySet = hashMap.keySet();
//				for(int j=0; j<keySet.size(); j++)
//				{
//					System.out.println("Class:AmisServiceImpl Function:getYearsWithTheBestMonthForForecast Text: BEFORE REMOVE Year= "+ keySet.);
//				}
				Iterator it= keySet.iterator();
				while(it.hasNext())
				{
					String code = (String)it.next();
					//System.out.println("Class: AMISGetCodingSystem Function: getElement Text: monthForecastToQuery code= "+ code+" hashMap.get(label) " +hashMap.get(code));
					bestMonth= hashMap.get(code);
					break;
				}
			}
			if(rvo.getCodes().size()>0)
				rvo.getCodes().get(0).setLabel(bestMonth);
		}
		LOGGER.info("getElement rvo codes size = " + rvo.getCodes().size());
		if(rvo.getCodes().size()>0)
			return rvo.getCodes().get(0);
		else
			return null;
	}
	
//	public static AMISCodesModelData getElement(AMISQueryVO qvo, AMISDao amisDao){
//		AMISResultVO rvo = new AMISResultVO();
//		qvo.setTablename("AMIS_DATA_MONTH_75ba84df");
//		qvo.setSql(AMISCodingSystemQueryBuilder.getElementWithMaxMonthPosition(qvo));
//		rvo.setCodes(getCodingSystemForOLAPElements(qvo, amisDao));
//		if(rvo.getCodes().size()>0)
//			return rvo.getCodes().get(0);
//		else
//			return null;
//	}
	public static List<ClientAmisDatasetResult> getElement(AMISQueryVO qvoList, AMISDao amisDao){
		qvoList.setSql(AMISCodingSystemQueryBuilder.getElementWithMaxMonthPosition(qvoList));
		List<ClientAmisDatasetResult> clientAmisDatasetResultList = getCodingSystemForAmisMonthElements(qvoList, amisDao);
		if(clientAmisDatasetResultList.size()==0)
		{
			return null;
		}
		return clientAmisDatasetResultList;
	}
	
	public static AMISResultVO getMarketingYear(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){	    
	    qvo.setTypeOfOutput("AMIS_MARKETING_YEAR");
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
	    List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String marketingYearTablename = sqlResult.get(0).toString();
	    
	    qvo.setSql(AMISCodingSystemQueryBuilder.getMarketingYearQuery(qvo, marketingYearTablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
	//	rvo.setCodes(getCodingSystemForMarketingYear(qvo, amisDao));
		LOGGER.info("getProductsWithCrops rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	//The marketing year table name is passed
	public static AMISResultVO getMarketingYearWithMyTablename(AMISQueryVO qvo, AMISResultVO rvo, String marketingYearTablename, AMISDao amisDao){	    
   
	    qvo.setSql(AMISCodingSystemQueryBuilder.getMarketingYearQuery(qvo, marketingYearTablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
	//	rvo.setCodes(getCodingSystemForMarketingYear(qvo, amisDao));
		LOGGER.info("getProductsWithCrops rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
    public static AMISResultVO getYears(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
		
//	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS"));
//
//        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
//	    String tablename = sqlResult.get(0).toString();
        String tablename = "amis_all_datasources";
		qvo.setSql(AMISCodingSystemQueryBuilder.getYears(qvo, tablename));
        rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		return rvo;
	}

    public static AMISResultVO getSplitYears(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

//	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS"));
//
//        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
//	    String tablename = sqlResult.get(0).toString();
        String tablename = "amis_all_datasources";
        qvo.setSql(AMISCodingSystemQueryBuilder.getSplitYears(qvo, tablename));
        rvo.setCodes(getCodingSystem(qvo, amisDao));

        return rvo;
    }
    
    public static AMISResultVO getDownloadYears(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
		
	    String tablename = "amis_all_datasources"; // combined data sources view
          
	   	qvo.setSql(AMISCodingSystemQueryBuilder.getYearsLabel(qvo, tablename));
		//qvo.setSql(AMISCodingSystemQueryBuilder.getYears(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		if(rvo.getCodes().size()==0) {
			//qvo.setSql(AMISCodingSystemQueryBuilder.getDefaultCBSYears(qvo, tablename));
			qvo.setSql(AMISCodingSystemQueryBuilder.getDefaultCBSYearsLabel(qvo, tablename));
			rvo.setCodes(getCodingSystem(qvo, amisDao));
		}
		
		return rvo;
	}
    
    public static AMISResultVO getRangeOfYears(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
		
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDatasetFile()));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
          
		qvo.setSql(AMISCodingSystemQueryBuilder.getRangeOfYears(qvo, tablename));
		
		rvo.setCodes(getCodingSystemForRangeOfYears(qvo, amisDao));
		
		return rvo;
	}
    
    public static AMISResultVO getRangeOfYearsWithoutMonth(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
		
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDatasetFile()));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
          
		qvo.setSql(AMISCodingSystemQueryBuilder.getRangeOfYearsWithoutMonth(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		return rvo;
	}
    
//    public static AMISResultVO getTimeSeries(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
//		
//	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS"));	
//		
//        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
//	    String tablename = sqlResult.get(0).toString();
//          
//		qvo.setSql(AMISCodingSystemQueryBuilder.getTimeSeries(qvo, tablename));
//		
//		rvo.setCodes(getCodingSystem(qvo, amisDao));
//		
//		return rvo;
//	}
    
    public static List<Object[]> getTimeSeriesForMonthForecast(AMISQueryVO qvo, String oneElementName, AMISDao amisDao){
		
	  //  qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS"));	
    	qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDatasetFile()));
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        
		qvo.setSql(AMISCodingSystemQueryBuilder.getTimeSeriesForMonth(qvo, oneElementName, tablename));
		
		List<Object[]> sqlResultArray = amisDao.getResultList(qvo.getSql());
		
		return sqlResultArray;
	}
    
    public static List<Object[]> getTimeSeries(AMISQueryVO qvo, String yearKey, String month, AMISDao amisDao){
		
	    //qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS"));	
    	qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDatasetFile()));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
	    int commodityCode = 0;
	    boolean isPopulation = false;
		qvo.setSql(AMISCodingSystemQueryBuilder.getTimeSeries(qvo, tablename, yearKey, month, commodityCode, isPopulation));
		
		List<Object[]> sqlResultArray = amisDao.getResultList(qvo.getSql());
		
		return sqlResultArray;
	}
    
    
    public static AMISCodesModelData getMarketingYearCountryInformation(AMISQueryVO qvo, String country_code, String commodity_code, AMISDao amisDao){
		
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDataset()));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        
		qvo.setSql(AMISCodingSystemQueryBuilder.getMarketingYearCountryInformation(qvo, tablename, country_code, commodity_code));
		
		List<AMISCodesModelData> aMISCodesModelDataList = getCodingSystemForMarketingYear(qvo, amisDao);
		AMISCodesModelData aMISCodesModelData;
		if((aMISCodesModelDataList!=null)&&(aMISCodesModelDataList.size()!=0))
		{
			aMISCodesModelData = aMISCodesModelDataList.get(0);
		}
		else
		{
			aMISCodesModelData = new AMISCodesModelData("", "");
		}
		
	//	codes.add(new AMISCodesModelData(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString()));
//		List<Object> sqlResultArray = amisDao.getSingleResultValue(qvo.getSql());
//		AMISCodesModelData aMISCodesModelData = new AMISCodesModelData(sqlResult.get(0).toString(), sqlResult.get(1).toString());
		
		return aMISCodesModelData;
	}
    
    public static AMISCodesModelData getCropsNumCountryInformation(AMISQueryVO qvo, String country_code, String commodity_code, AMISDao amisDao){
		
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDataset()));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        
		qvo.setSql(AMISCodingSystemQueryBuilder.getCropsNumCountryInformation(qvo, tablename, country_code, commodity_code));
		List<Object[]> sqlResultArray = amisDao.getResultList(qvo.getSql());
		
		List<AMISCodesModelData> aMISCodesModelDataList = getCodingSystem(qvo, amisDao);
		
	//	AMISCodesModelData aMISCodesModelData = aMISCodesModelDataList.get(0);
		
		AMISCodesModelData aMISCodesModelData;
		if((aMISCodesModelDataList!=null)&&(aMISCodesModelDataList.size()!=0))
		{
			aMISCodesModelData = aMISCodesModelDataList.get(0);
		}
		else
		{
			aMISCodesModelData = new AMISCodesModelData("", "");
		}
		
		return aMISCodesModelData;
	}

    public static List<Object[]> checkCountryData(AMISQueryVO qvo, AMISDao amisDao){
		
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDatasetFile()));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        
		qvo.setSql(AMISCodingSystemQueryBuilder.getDataFromCountryDataset(qvo, tablename));
		
		List<Object[]> sqlResultArray = amisDao.getResultList(qvo.getSql());
		
		return sqlResultArray;
	}
    
    public static HashMap<String, HashMap<String,String>> getOpeningClosingStocksForYear(AMISQueryVO qvo, AMISDao amisDao){
		
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDatasetFile()));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        
	    String closingStocksElementCode = CCBS.CLOSING_STOCKS_ELEMENT_CODE;
		qvo.setSql(AMISCodingSystemQueryBuilder.getOpeningClosingStocksForYear(qvo, closingStocksElementCode, tablename));
		
		//List<Object[]> sqlResultArray = amisDao.getResultList(qvo.getSql());
		
		HashMap<String, HashMap<String , String>> ris = getCodingSystemOpeningClosingStocksForYear(qvo, amisDao);
		return ris;
	}
    
    public static String getCountryDataTableName(AMISQueryVO qvo, AMISDao amisDao){
		
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDatasetFile()));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        
		return tablename;
	}
    
     public static boolean fillCountryData(AMISQueryVO qvo, int rowIndex, AMISDao amisDao, String tablename, Date d){
		        
		qvo.setSql(AMISCodingSystemQueryBuilder.fillCountryDataset(qvo, tablename, rowIndex));
		
		amisDao.insertCustomDatasetValues(qvo.getSql());
	//	List<Object[]> sqlResultArray = amisDao.getResultList(qvo.getSql());
//		
		return true;
	}
    
    //clientCbsDatasetResultObject contains the old element(that was in the AMIS_DATA_COUNTRY dataset)
    //Qvo contains the new element(that was in the client grids)
    public static boolean updateCountryElement(AMISQueryVO qvo, ClientCbsDatasetResult clientCbsDatasetResultObject, AMISDao amisDao, String tablename){
        
		qvo.setSql(AMISCodingSystemQueryBuilder.overwriteElement(qvo, clientCbsDatasetResultObject, tablename));
		
		amisDao.insertCustomDatasetValues(qvo.getSql());	
	
		return true;
	}
    
  //clientCbsDatasetResultObject contains the old element(that was in the AMIS_DATA_COUNTRY dataset)
    //Qvo contains the new element(that was in the client grids)
    public static boolean updateAllForecastCountryElement(AMISQueryVO qvo, ClientCbsDatasetResult clientCbsDatasetResultObject, AMISDao amisDao, String tablename){
        
		qvo.setSql(AMISCodingSystemQueryBuilder.overwriteForecastElements(qvo, clientCbsDatasetResultObject, tablename));
		
		amisDao.insertCustomDatasetValues(qvo.getSql());	
	
		return true;
	}
    
    //Qvo contains the new element(that was in the client grids)
    public static boolean insertCountryElement(AMISQueryVO qvo, AMISDao amisDao, String tablename){
        
		qvo.setSql(AMISCodingSystemQueryBuilder.insertElementInCountryDataset(qvo, tablename));
		
		amisDao.insertCustomDatasetValues(qvo.getSql());	
	
		return true;
	}
    
    public static List<Object[]> getSubElementsTimeSeries(AMISQueryVO qvo, String yearKey, String month, AMISDao amisDao){
		
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDatasetFile()));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        
		qvo.setSql(AMISCodingSystemQueryBuilder.getSubElementsTimeSeries(qvo, tablename, yearKey, month));
		
		List<Object[]> sqlResultArray = amisDao.getResultList(qvo.getSql());
		
		return sqlResultArray;
	}
    
    public static List<Object[]> getTimeSeriesForPopulation(AMISQueryVO qvo, String yearKey, String month, AMISDao amisDao){
		
	   // qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS"));	
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo.getSelectedDatasetFile()));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        
	  //  qvo.setCommodityCode("9");
	    int commodityCode = 9;
	    boolean isPopulation = true;
		qvo.setSql(AMISCodingSystemQueryBuilder.getTimeSeries(qvo, tablename, yearKey, month, commodityCode, isPopulation));
		
		List<Object[]> sqlResultArray = amisDao.getResultList(qvo.getSql());
		
		return sqlResultArray;
	}
    
    public static AMISResultVO getDatatSource(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
       // qvo.setSql(AMISCodingSystemQueryBuilder.getG20CountriesQuery(qvo, tablename));
        qvo.setSql(AMISCodingSystemQueryBuilder.getDatabaseQuery(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getDatatSource rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}

    public static AMISResultVO getElementsWithUnits(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
     
        qvo.setSql(AMISCodingSystemQueryBuilder.getElementsWithMeasurementUnits(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getElementsWithUnits rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}

    public static AMISResultVO getElementsWithCode(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
     
        qvo.setSql(AMISCodingSystemQueryBuilder.getElementsWithMeasurementUnits(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getElementsWithUnits rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
    
    public static AMISResultVO getElementsWithDatabases(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
     
        qvo.setSql(AMISCodingSystemQueryBuilder.getElementsWithDatabasesQuery(qvo, tablename));
		
		//rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		rvo.setCodes(getCodingSystemForElements(qvo, amisDao));
		
		LOGGER.info("getElementsWithDatabases rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}

    public static AMISResultVO getFlags(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

    	qvo.setTypeOfOutput(AMISConstants.AMIS_FLAGS.toString());
		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
     
        qvo.setSql(AMISCodingSystemQueryBuilder.getFlags(qvo, tablename));
		
		rvo.setCodes(getFlagsCodingSystem(qvo, amisDao));
		
		LOGGER.info("getFlags rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
    
	public static AMISResultVO getCCBSYears(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
		
	    qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("CCBS"));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
          
		qvo.setSql(AMISCodingSystemQueryBuilder.getYears(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getCCBSYears rvo codes size = " + rvo.getCodes().size());
		
		return rvo;
	}


//	public static AMISResultVO getItems(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){
//
//		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
//		
//        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
//	    String tablename = sqlResult.get(0).toString();
//        qvo.setSql(AMISCodingSystemQueryBuilder.getItemsQuery(qvo, tablename));
//		
//		rvo.setCodes(getCodingSystem(qvo, amisDao));
//		
//		LOGGER.info("getItem rvo codes size = " + rvo.getCodes().size());
//		return rvo;
//	}

	public static AMISResultVO getProductsByElement(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_PRODUCTS"));	
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	 
        if(!qvo.getElements().isEmpty()){
        	String table1 = sqlResult.get(0).toString() + " p"; //with alias

        	qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_PRODUCT_ELEMENTS"));	
        	sqlResult = amisDao.getSingleResultValue(qvo.getSql());
        	String table2 = sqlResult.get(0).toString() + " pe"; // with alias

        	qvo.setSql(AMISCodingSystemQueryBuilder.getProductsByElementQuery(qvo, table1, table2));

        	rvo.setCodes(getCodingSystem(qvo, amisDao));
        }
        else {
        	String tablename = sqlResult.get(0).toString();
        	qvo.setSql(AMISCodingSystemQueryBuilder.getProductsQuery(qvo, tablename));

        	rvo.setCodes(getCodingSystem(qvo, amisDao));
        }
		LOGGER.info("getProductsByElement rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}

    public static AMISResultVO getProductsByElementCompare(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_PRODUCTS"));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        if(!qvo.getElements().isEmpty()){
            String table1 = sqlResult.get(0).toString() + " p"; //with alias

            qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_PRODUCT_ELEMENTS"));
            sqlResult = amisDao.getSingleResultValue(qvo.getSql());
            String table2 = sqlResult.get(0).toString() + " pe"; // with alias

            qvo.setSql(AMISCodingSystemQueryBuilder.getProductsByElementQueryCompare(qvo, table1, table2));

            rvo.setCodes(getCodingSystem(qvo, amisDao));
        }
        else {
            String tablename = sqlResult.get(0).toString();
            qvo.setSql(AMISCodingSystemQueryBuilder.getProductsQueryCompare(qvo, tablename));

            rvo.setCodes(getCodingSystem(qvo, amisDao));
        }
        LOGGER.info("getProductsByElement rvo codes size = " + rvo.getCodes().size());
        return rvo;
    }

	public static AMISResultVO getElementsByProduct(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_ELEMENTS"));	
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	 
        if(!qvo.getItems().isEmpty()){
        	String table1 = sqlResult.get(0).toString() + " e"; //with alias

        	qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_PRODUCT_ELEMENTS"));	
        	sqlResult = amisDao.getSingleResultValue(qvo.getSql());
        	String table2 = sqlResult.get(0).toString() + " pe"; // with alias

        	qvo.setSql(AMISCodingSystemQueryBuilder.getElementsByProductQuery(qvo, table1, table2));

        	//rvo.setCodes(getCodingSystem(qvo, amisDao));
        	rvo.setCodes(getCodingSystemForElements(qvo, amisDao));
        }
        else {
        	String tablename = sqlResult.get(0).toString();
        	qvo.setSql(AMISCodingSystemQueryBuilder.getElementsWithDatabasesQuery(qvo, tablename));

        	//rvo.setCodes(getCodingSystem(qvo, amisDao));
        	rvo.setCodes(getCodingSystemForElements(qvo, amisDao));
        }
		LOGGER.info("getElementsByProduct rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}

    public static AMISResultVO getElementsByProductCompare(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_ELEMENTS"));
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());

        if(!qvo.getItems().isEmpty()){
            String table1 = sqlResult.get(0).toString() + " e"; //with alias

            qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_PRODUCT_ELEMENTS"));
            sqlResult = amisDao.getSingleResultValue(qvo.getSql());
            String table2 = sqlResult.get(0).toString() + " pe"; // with alias

            qvo.setSql(AMISCodingSystemQueryBuilder.getElementsByProductCompareQuery(qvo, table1, table2));

            //rvo.setCodes(getCodingSystem(qvo, amisDao));
            rvo.setCodes(getCodingSystemForElements(qvo, amisDao));
        }
        else {
            String tablename = sqlResult.get(0).toString();
            qvo.setSql(AMISCodingSystemQueryBuilder.getElementsWithDatabasesCompareQuery(qvo, tablename));

            //rvo.setCodes(getCodingSystem(qvo, amisDao));
            rvo.setCodes(getCodingSystemForElements(qvo, amisDao));
        }
        LOGGER.info("getElementsByProduct rvo codes size = " + rvo.getCodes().size());
        return rvo;
    }
	
	
	public static AMISResultVO getProducts(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        qvo.setSql(AMISCodingSystemQueryBuilder.getProductsQuery(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getProducts rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}

    public static AMISResultVO getFoodBalanceProducts(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_PRODUCTS"));

        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
        String tablename = sqlResult.get(0).toString();
        qvo.setSql(FoodBalanceQueryBuilder.getFoodBalanceProductsQuery(qvo, tablename));

        rvo.setCodes(getCodingSystem(qvo, amisDao));

        LOGGER.info("getFoodBalanceProducts rvo codes size = " + rvo.getCodes().size());
        return rvo;
    }

    public static AMISResultVO getFoodBalanceCountries(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

        qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_MARKET_TRADE_YEAR"));

        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
        String tablename = sqlResult.get(0).toString();
        qvo.setSql(FoodBalanceQueryBuilder.getFoodBalanceCountriesQuery(qvo, tablename));

        rvo.setCodes(getCodingSystem(qvo, amisDao));

        LOGGER.info("getFoodBalanceCountries rvo codes size = " + rvo.getCodes().size());
        return rvo;
    }

	public static AMISResultVO getProductsWithoutPopulation(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem("AMIS_PRODUCTS"));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        qvo.setSql(AMISCodingSystemQueryBuilder.getProductsWithoutPopulationQuery(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getProductsWithoutPopulation rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static AMISResultVO getElementsWidthoutUnits(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        qvo.setSql(AMISCodingSystemQueryBuilder.getElementsWidthoutUnitsQuery(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getElementsWidthoutUnits rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static AMISResultVO getTotalProducts(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        qvo.setSql(AMISCodingSystemQueryBuilder.getTotalProductsQuery(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getProducts rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static AMISResultVO getElements(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));	
		
        List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
	    String tablename = sqlResult.get(0).toString();
        qvo.setSql(AMISCodingSystemQueryBuilder.getElementsQuery(qvo, tablename));
		
		rvo.setCodes(getCodingSystem(qvo, amisDao));
		
		LOGGER.info("getElements rvo codes size = " + rvo.getCodes().size());
		return rvo;
	}
	
	public static ClientCbsDatasetResult getSingleElement(AMISQueryVO qvo, AMISDao amisDao, String tablename){

        qvo.setSql(AMISCodingSystemQueryBuilder.getSingleElement(qvo, tablename));
		
        ClientCbsDatasetResult clientCbsDatasetResult = getClientCbsDatasetResultObject(qvo, amisDao);
		
		//LOGGER.info("getSingleElement ");
		return clientCbsDatasetResult;
	}
	
	public static AMISResultVO getPSDProducts(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getCodingSystemQuery(qvo, "PSD_PRODUCTS"));

		rvo.setCodes(getCodingSystem(qvo, amisDao));

		return rvo;
	}
	
	public static AMISResultVO getPSDElements(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getTableNameOfCodingSystem(qvo));

		 List<Object> sqlResult = amisDao.getSingleResultValue(qvo.getSql());
		 String tablename = sqlResult.get(0).toString();
	     qvo.setSql(PSDCodingSystemQueryBuilder.getPSDElementsQuery(qvo, tablename));
			
	        
		rvo.setCodes(getCodingSystem(qvo, amisDao));

		return rvo;
	}
	
	
	public static AMISResultVO getPSDYears(AMISQueryVO qvo, AMISResultVO rvo, AMISDao amisDao){

		qvo.setSql(AMISCodingSystemQueryBuilder.getYearsQuery(qvo));

		rvo.setCodes(getCodingSystem(qvo, amisDao));

		return rvo;
	}

  public static List<AMISCodesModelData> getCodingSystemForElements(AMISQueryVO qvo, AMISDao amisDao) {
		
		List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
		List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
		
		for(int i=0; i < sqlResult.size(); i++) {
			AMISCodesModelData model = new AMISCodesModelData(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString());
			model.set("unit_databases", sqlResult.get(i)[2].toString());
			model.set("is_aggregated", sqlResult.get(i)[3].toString());
			codes.add(model);
		}

		LOGGER.info("CODES: " + codes);
		return codes;
	}

	private static List<AMISCodesModelData> getCodingSystem(AMISQueryVO qvo, AMISDao amisDao) {
		
		List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
		List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
		
		for(int i=0; i < sqlResult.size(); i++) {
			codes.add(new AMISCodesModelData(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString()));
		}

		LOGGER.info("CODES: " + codes);
		return codes;
	}
	
	private static List<AMISCodesModelData> getCodingSystemForMarketingYear(AMISQueryVO qvo, AMISDao amisDao) {
		
		List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
		List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
		
		for(int i=0; i < sqlResult.size(); i++) {
			if((sqlResult.get(i)[0]!=null)&&(sqlResult.get(i)[1]!=null))
			{
				codes.add(new AMISCodesModelData(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString()));
			}			
		}

		LOGGER.info("CODES: " + codes);
		return codes;
	}
	
	private static HashMap<String, HashMap<String , String>> getCodingSystemOpeningClosingStocksForYear(AMISQueryVO qvo, AMISDao amisDao) {
		//HashMap<String, HashMap<String , String>>
		List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
		
		HashMap<String, HashMap<String , String>> hashMap = new HashMap<String, HashMap<String,String>>();
		String year;
		HashMap<String , String> element;
		for(int i=0; i < sqlResult.size(); i++) {
			year = sqlResult.get(i)[1].toString();
			element = hashMap.get(year+"*"+sqlResult.get(i)[4].toString());
			if(element == null)
			{
				//The year is not in the hashmap ... it'necessary create a new element of the hashmap and then set the closing stocks or the opening stocks value
				element = new HashMap<String, String>();
				//Element_code/Value
				element.put(sqlResult.get(i)[2].toString(), sqlResult.get(i)[3].toString());
				hashMap.put(year+"*"+sqlResult.get(i)[4].toString(), element);
			}
			else
			{
				//Element_code/Value
				element.put(sqlResult.get(i)[2].toString(), sqlResult.get(i)[3].toString());
			}
		}
		return hashMap;
	}
	
	private static ClientCbsDatasetResult getClientCbsDatasetResultObject(AMISQueryVO qvo, AMISDao amisDao) {
		
		ClientCbsDatasetResult clientCbsDatasetResultObject = null;
		List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
		List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
		if(sqlResult!=null)
		{
			for(int i=0; i < sqlResult.size(); i++) {
				//ClientCbsDatasetResult(String database, String region_code, String region_name, String product_code, String product_name, String element_code, String element_name, String units, String year, String month, String value, String annotation, String value_type, String id/*not used*/){
				clientCbsDatasetResultObject = new ClientCbsDatasetResult(sqlResult.get(i)[0].toString(), sqlResult.get(i)[1].toString(), sqlResult.get(i)[2].toString(), sqlResult.get(i)[3].toString(), sqlResult.get(i)[4].toString(), sqlResult.get(i)[5].toString(), sqlResult.get(i)[6].toString(), sqlResult.get(i)[7].toString(), sqlResult.get(i)[8].toString(), sqlResult.get(i)[9].toString(), sqlResult.get(i)[10].toString(), sqlResult.get(i)[11].toString(), sqlResult.get(i)[12].toString(), sqlResult.get(i)[13].toString());
			}
		}
	//	LOGGER.info("getClientCbsDatasetResultObject: " + clientCbsDatasetResultObject);
		return clientCbsDatasetResultObject;
	}
	
	private static List<AMISCodesModelData> getFlagsCodingSystem(AMISQueryVO qvo, AMISDao amisDao) {
		
		List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
		List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
		
		for(int i=0; i < sqlResult.size(); i++) {
			if(sqlResult.get(i)[0]!=null)
			{
				codes.add(new AMISCodesModelData(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString()));
			}
			else
			{
				//This is the case when the flag code is null because the flag is Unknown
				codes.add(new AMISCodesModelData("",sqlResult.get(i)[1].toString()));
			}			
		}

		LOGGER.info("CODES: " + codes);
		return codes;
	}
	
private static List<AMISCodesModelData> getCodingSystemForRangeOfYears(AMISQueryVO qvo, AMISDao amisDao) {
		
		List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
		List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
		
		for(int i=0; i < sqlResult.size(); i++) {
			if(sqlResult.get(i)[2]!=null)
			{
				codes.add(new AMISCodesModelData(sqlResult.get(i)[2].toString(),sqlResult.get(i)[1].toString()));
			}
			else
			{
				codes.add(new AMISCodesModelData("",sqlResult.get(i)[1].toString()));
			}
		}

		LOGGER.info("CODES: " + codes);
		for(AMISCodesModelData object: codes)
		{
			LOGGER.info("CODES: object.getLabel()= " + object.getLabel()+" object.getCode() = " + object.getCode());
		}
		return codes;
	}

	//This method is used by OLAP table function
	private static List<AMISCodesModelData> getCodingSystemForOLAPElements(AMISQueryVO qvo, AMISDao amisDao) {
	
	List<AMISCodesModelData> codes = new ArrayList<AMISCodesModelData>();
	List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
	
	for(int i=0; i < sqlResult.size(); i++) {
		String valueFlagResult = "";
		if((sqlResult.get(i)[2] !=null)&&(sqlResult.get(i)[3]!=null))
		{
			valueFlagResult = sqlResult.get(i)[2].toString()+ "**"+ sqlResult.get(i)[3].toString();
		}
		else if((sqlResult.get(i)[2] !=null)&&(sqlResult.get(i)[3]==null))
		{
			valueFlagResult = sqlResult.get(i)[2].toString()+ "**";
		}
		else if((sqlResult.get(i)[2] ==null)&&(sqlResult.get(i)[3]!=null))
		{
			valueFlagResult = "**"+ sqlResult.get(i)[3].toString();
		}
		codes.add(new AMISCodesModelData(sqlResult.get(i)[0].toString(),sqlResult.get(i)[1].toString(), valueFlagResult));
	}

	LOGGER.info("CODES: " + codes);
	return codes;
}
	
	//This method is used by OLAP table function
	private static List<ClientAmisDatasetResult> getCodingSystemForAmisMonthElements(AMISQueryVO qvo, AMISDao amisDao) {
	ClientAmisDatasetResult clientAmisDatasetResultObject = null;
	List<ClientAmisDatasetResult> clientAmisDatasetResultList = new ArrayList<ClientAmisDatasetResult>();
	List<Object[]> sqlResult = amisDao.getResultList(qvo.getSql());
	if(sqlResult!=null)
	{
		for(int i=0; i < sqlResult.size(); i++) {
		
			if(sqlResult.get(i)[11]==null && sqlResult.get(i)[12]==null)
			{
				//The value and flag could be null
				clientAmisDatasetResultObject = new ClientAmisDatasetResult(sqlResult.get(i)[0].toString(), sqlResult.get(i)[2].toString(), sqlResult.get(i)[3].toString(), sqlResult.get(i)[4].toString(), sqlResult.get(i)[5].toString(), sqlResult.get(i)[6].toString(), sqlResult.get(i)[7].toString(), sqlResult.get(i)[8].toString(), sqlResult.get(i)[9].toString(), sqlResult.get(i)[10].toString(), "", "", sqlResult.get(i)[13].toString(), sqlResult.get(i)[14].toString());
			} 
			else if(sqlResult.get(i)[11]==null)
			{
				//The value could be null
				clientAmisDatasetResultObject = new ClientAmisDatasetResult(sqlResult.get(i)[0].toString(), sqlResult.get(i)[2].toString(), sqlResult.get(i)[3].toString(), sqlResult.get(i)[4].toString(), sqlResult.get(i)[5].toString(), sqlResult.get(i)[6].toString(), sqlResult.get(i)[7].toString(), sqlResult.get(i)[8].toString(), sqlResult.get(i)[9].toString(), sqlResult.get(i)[10].toString(), "", sqlResult.get(i)[12].toString(), sqlResult.get(i)[13].toString(), sqlResult.get(i)[14].toString());
			}
			else if(sqlResult.get(i)[12]==null)
			{
				//The annotation could be null
				clientAmisDatasetResultObject = new ClientAmisDatasetResult(sqlResult.get(i)[0].toString(), sqlResult.get(i)[2].toString(), sqlResult.get(i)[3].toString(), sqlResult.get(i)[4].toString(), sqlResult.get(i)[5].toString(), sqlResult.get(i)[6].toString(), sqlResult.get(i)[7].toString(), sqlResult.get(i)[8].toString(), sqlResult.get(i)[9].toString(), sqlResult.get(i)[10].toString(), sqlResult.get(i)[11].toString(), "", sqlResult.get(i)[13].toString(), sqlResult.get(i)[14].toString());
			}
			else
			{
				clientAmisDatasetResultObject = new ClientAmisDatasetResult(sqlResult.get(i)[0].toString(), sqlResult.get(i)[2].toString(), sqlResult.get(i)[3].toString(), sqlResult.get(i)[4].toString(), sqlResult.get(i)[5].toString(), sqlResult.get(i)[6].toString(), sqlResult.get(i)[7].toString(), sqlResult.get(i)[8].toString(), sqlResult.get(i)[9].toString(), sqlResult.get(i)[10].toString(), sqlResult.get(i)[11].toString(), sqlResult.get(i)[12].toString(), sqlResult.get(i)[13].toString(), sqlResult.get(i)[14].toString());
			}			
			clientAmisDatasetResultList.add(clientAmisDatasetResultObject);
		}
	}
	LOGGER.info("CODES: " + clientAmisDatasetResultList);
	return clientAmisDatasetResultList;
}

	

}
