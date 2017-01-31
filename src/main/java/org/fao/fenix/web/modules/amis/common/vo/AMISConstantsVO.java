package org.fao.fenix.web.modules.amis.common.vo;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.amis.client.control.download.AMISFoodBalanceDownloadController;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AMISConstantsVO implements IsSerializable {

public static void setLanguageSettings(AMISQueryVO p) {
		p.setLanguage(AMISConstants.defaultLanguage);
	}

	/*public static void setFAOSTATDBSettings(AMISQueryVO p) {
	p.setDbDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	p.setConnectionDriver("JDBC");
	p.setDatasource("FAOSTAT");
	
	p.setDbUrl("jdbc:sqlserver://");
	p.setDbServerName("FAOSTAT-PROD\\Production");
	p.setDbName("Warehouse");
	p.setDbUserName("Warehouse");
	p.setDbPassword("w@reh0use");
	String connection = ""+p.getDbUrl()+p.getDbServerName()+";databaseName="+p.getDbName()+";";
	//"jdbc:sqlserver://FAOSTAT-PROD\\Production;databaseName=Warehouse;"
	p.setConnectionString(connection);
	//System.out.println("AMISConstantVO Url = "+p.getConnectionString());
	
	// remove languages
	p.setLanguage(AMISConstants.defaultLanguage);
}*/
	
	
	
	
	
	
//	public static void selectLabels(AMISQueryVO p, Boolean addCodes, Boolean addFlag, Boolean addMeasurementUnit) {
//		List<String> selects = new ArrayList<String>();
//
//		System.out.println("addCodes: " + addCodes);
//		System.out.println("addFlag: " + addFlag);
//		System.out.println("addMeasurementUnit: " + addMeasurementUnit);
//
//		if(p.getSelectedDataset().equals(AMISConstants.FAOSTAT.toString()))
//		{
//			if ( addCodes )
//				selects.add("D.AreaCode");
//	
//			selects.add("A.AreaName" + AMISConstants.defaultLanguage);
//	
//			if ( addCodes )
//				selects.add("D.ItemCode");
//	
//			selects.add("I.ItemName" + AMISConstants.defaultLanguage);
//	
//			if ( addCodes )
//				selects.add("D.ElementCode");
//	
//			selects.add("E.ElementName" + AMISConstants.defaultLanguage);
//	
//			if ( addMeasurementUnit )
//				selects.add("E.UnitName" + AMISConstants.defaultLanguage);
//	
//			if ( addFlag ) {
//				selects.add("F.FlagDescription" + AMISConstants.defaultLanguage);
//			}
//	
//			selects.add("D.Year");
//			selects.add("D.Value");
//		}
//		else
//		{
////			 country     | text             | 
////			 product     | text             | 
////			 element     | text             | 
////			 year        | date             | 
////			 value       | double precision | 
////			 countrycode | text             | 
////			 elementcode | text             | 
////			 productcode | text             | 
//
//			//For PSD AND CCBS
//			if ( addCodes )
//				selects.add("D.countrycode");
//	
//			selects.add("D.country");
//	
//			if ( addCodes )
//				selects.add("D.productcode");
//	
//			selects.add("D.product");
//	
//			if ( addCodes )
//				selects.add("D.elementcode");
//	
//			selects.add("D.element");
//	
//			if ( addMeasurementUnit )
//				selects.add("E.unitname" + AMISConstants.defaultLanguage);
//	
////			if ( addFlag ) {
////				selects.add("F.FlagDescription" + AMISConstants.defaultLanguage);
////			}
//	
//			selects.add("D.year");
//			selects.add("D.value");
//		}
//
//		p.setSelects(selects);
//
//		System.out.println("Class: AmisConstantVo Function: SELECTS:selectLabels Text:Select  " + selects);
//	}


    public static void setFoodBalanceSelects(AMISQueryVO p) {
        List<String> selects = new ArrayList<String>();

        selects.add("D.database");

        if(AMISFoodBalanceDownloadController.selectedByOptionModel==null || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="COUNTRY"
                || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="DATASOURCE"){
            selects.add("D.region_name");
            selects.add("D.product_name");
        }
        else if(AMISFoodBalanceDownloadController.selectedByOptionModel!=null || AMISFoodBalanceDownloadController.selectedByOptionModel.getCode()=="PRODUCT"){
            selects.add("D.product_name");
            selects.add("D.region_name");
        }

         selects.add("D.element_code");
       // selects.add("D.element_name || ' - ' || D.units ");

        selects.add("D.year_label");
//        selects.add("ROUND(CAST(D.value as numeric), 1) ");
        selects.add("ROUND(CAST(D.value as numeric), 2) ");

        //selects.add("D.element_name ");

        p.setSelects(selects);

        //System.out.println("Class: AmisConstantVo Function: setFoodBalanceSelects Text:Select  " + selects);
    }

	public static void setSelects(AMISQueryVO p, Boolean addCodes, Boolean addMeasurementUnit) {
		List<String> selects = new ArrayList<String>();

		System.out.println("Class: AmisConstantVo Function: SELECTS:selectLabels Text:addCodes: " + addCodes);
	//	System.out.println("Class: AmisConstantVo Function: SELECTS:selectLabels Text:addFlag: " + addFlag);
		System.out.println("Class: AmisConstantVo Function: SELECTS:selectLabels Text:addMeasurementUnit: " + addMeasurementUnit);

		    selects.add("database");
		
			if ( addCodes )
				selects.add("D.region_code");
	
			selects.add("D.region_name");
	
			if ( addCodes )
				selects.add("D.product_code");
	
			selects.add("D.product_name");
	
			if ( addCodes )
				selects.add("D.element_code");
	
			selects.add("D.element_name");
	
			if ( addMeasurementUnit )
				selects.add("D.units");
	
			selects.add("D.year_label");
			selects.add("D.value");

		p.setSelects(selects);

		System.out.println("Class: AmisConstantVo Function: SELECTS:selectLabels Text:Select  " + selects);
	}

	public static void setTableHeaders(AMISQueryVO p, Boolean addCodes, Boolean addMeasurementUnit) {
		List<String> tableHeaders = new ArrayList<String>();

		System.out.println("Class: AmisConstantVo Function: setTableHeaders Text:addCodes: " + addCodes);
	//	System.out.println("Class: AmisConstantVo Function: setTableHeaders Text:addFlag: " + addFlag);
		System.out.println("Class: AmisConstantVo Function: setTableHeaders Text:addMeasurementUnit: " + addMeasurementUnit);
	
		 tableHeaders.add("Data Source");
		 
			if ( addCodes )
				tableHeaders.add("Country/Region Code");
	
			tableHeaders.add("Country/Region Name");
	
			if ( addCodes )
				tableHeaders.add("Product Code");
	
			tableHeaders.add("Product Name");
	
			if ( addCodes )
				tableHeaders.add("Element Code");
	
			tableHeaders.add("Element Name");
	
			if ( addMeasurementUnit )
				tableHeaders.add("Units");
	
			tableHeaders.add("Year");
			tableHeaders.add("Value");

		p.setTableHeaders(tableHeaders);

		System.out.println("Class: AmisConstantVo Function: setTableHeaders Text:Select  " + tableHeaders);
	}
	
	
	public static void setOrderBys(AMISQueryVO p) {
		List<String> orderBys = new ArrayList<String>();

		orderBys.add("D.database");
		orderBys.add("D.region_name");
		orderBys.add("D.product_name");
		orderBys.add("D.element_name");
		orderBys.add("D.year_label");
       // orderBys.add("D.year");
	
		p.setOrderBys(orderBys);

		System.out.println("Class: AmisConstantVo Function: orderBys Text:Select  " + orderBys);
	}
}
