package org.fao.fenix.web.modules.dataviewer.common.vo.faostat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATAggregationConstant;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATTableConstant;


public class FAOSTATConstants {
	
	// TODO: check the CSS to get the right width and height (and the margins)
	public static Integer INTERFACE_WIDTH = 1100;	
	
	// height
	public static Integer HEADER_HEIGHT = 65;	
	
	// height
	public static Integer MENU_HEIGHT = 30;	
	
	public static Integer MAIN_CONTENT_MIN_HEIGHT = 30;	
	
	// height
	public static Integer SUBMENU_HEIGHT = 30;	
	
	// height
	public static Integer SEPARATION = 30;	
	
	// margin + height
	public static Integer FOOTER_HEIGHT = 20 + 30;	

	// TODO: check if the SUBMENU IS ACTIVE
//	public static Integer MAIN_CONTENT_MIN_HEIGHT = Window.getClientHeight() - (HEADER_HEIGHT + MENU_HEIGHT + FOOTER_HEIGHT + SEPARATION);


	static HashMap<String, String> faostatAlias;
	
	static HashMap<String, String> aggregationTypeAlias;
	
	
	
	public final static String BOX_MENU_HEIGHT = "45";
	
	/**
	 * 
	 * TODO: Change calculate removing the height of the menu from the panel
	 * 		 and not like now adding it...
	 * 
	 * @param height
	 * @return
	 */
	public static String calculateHeightWithBoxMenu(String height) {
		
//		System.out.println("height: " + height);
//		String current = height.substring(0, height.length());
//		String boxMenu = BOX_MENU_HEIGHT.substring(0, BOX_MENU_HEIGHT.length());
		String current = String.valueOf(Integer.parseInt(height) + (Integer.parseInt(BOX_MENU_HEIGHT))) + "px";

//		System.out.println("height: " + height + " | " + current);
		return current;
	}
	
	
//	public static void calculateMainContentMinimumHeight() {
//		Integer height = 0;
//		
//		Integer banner = FAOSTATConstants.BANNER_HEIGHT;
//		Integer menu = FAOSTATConstants.MENU_HEIGHT;
//		Integer footer = FAOSTATConstants.FOOTER_HEIGHT; 
//		
//		Integer totalHeight = Window.getClientHeight();
//		
//		height = totalHeight - (banner + menu + footer);
//
//		
//		System.out.println("totalHeight: " + totalHeight);
//		System.out.println("height: " + height);
//		
//		MAIN_CONTENT_MIN_HEIGHT = height;
//	}

		
//	public final static String MEDIUM_WIDTH_PIXEL = "750";
//	public final static String MEDIUM_HEIGHT_PIXEL = "270";
	
	public final static String MEDIUM_WIDTH = "750";
	public final static String MEDIUM_HEIGHT = "270";
		
//	public final static String SMALL_WIDTH_PIXEL = "530px";
//	public final static String SMALL_HEIGHT_PIXEL = "250px";
	
	public final static String SMALL_WIDTH = "530";
	public final static String SMALL_HEIGHT = "250";
	
	
	static {
		faostatAlias = new HashMap<String, String>();

		faostatAlias.put(FAOSTATTableConstant.Data.toString(), "D");
		faostatAlias.put(FAOSTATTableConstant.DomainItem.toString(), "DI");
		faostatAlias.put(FAOSTATTableConstant.Element.toString(), "E");
		faostatAlias.put(FAOSTATTableConstant.DomainElement.toString(), "DE");
		faostatAlias.put(FAOSTATTableConstant.Item.toString(), "I");
		faostatAlias.put(FAOSTATTableConstant.Flag.toString(), "F");
		faostatAlias.put(FAOSTATTableConstant.DomainItemElement.toString(), "DIE");
	}
	
	
	static {
		aggregationTypeAlias = new HashMap<String, String>();

		aggregationTypeAlias.put(FAOSTATAggregationConstant.SUM.toString(), "SUM");
		aggregationTypeAlias.put(FAOSTATAggregationConstant.SUBTRACT.toString(), "-");
	}
	
	
	public static void setFAOSTATDBSettings(DWFAOSTATQueryVO p) {
//		p.setDbDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		p.setConnectionDriver("JDBC");
//		p.setDatasource("FAOSTAT");
//		
//		p.setDbUrl("jdbc:sqlserver://");
//		p.setDbServerName("FAOSTAT-PROD\\Production");
//		p.setDbName("Warehouse");
//		p.setDbUserName("Warehouse");
//		p.setDbPassword("w@reh0use");
//		
//		// remove languages
		p.setLanguage(faostatLanguage);
	}
	
	public static void selectLabels(DWFAOSTATQueryVO p, Boolean addCodes, Boolean addFlag, Boolean addMeasurementUnit, Boolean isTradeMatrix) {
		List<String> selects = new ArrayList<String>();
		
//		System.out.println("addCodes: " + addCodes);
//		System.out.println("addFlag: " + addFlag);
//		System.out.println("addMeasurementUnit: " + addMeasurementUnit);

		
		
		// AREAS
		if ( !isTradeMatrix ) {
			if ( addCodes )
					selects.add("D.AreaCode as '"+ FAOSTATLanguage.print().countryCodes() +"'");
			selects.add("A.AreaName" + faostatLanguage + " as '"+ FAOSTATLanguage.print().country() +"'");
		}
		else {
			if ( addCodes ) {
				selects.add("D.ReporterAreaCode as '"+ FAOSTATLanguage.print().reporterCodes() +"'");
			}
			selects.add("RA.AreaName" + faostatLanguage + " as '"+ FAOSTATLanguage.print().reporter() +"'");
			if ( addCodes ) {
				selects.add("D.PartnerAreaCode as '"+ FAOSTATLanguage.print().partnerCodes() +"'");
			}
			selects.add("PA.AreaName" + faostatLanguage + " as '"+ FAOSTATLanguage.print().partner() +"'");
		}
		
		
		// ITEMS
		if ( addCodes ) {
			selects.add("D.ItemCode as '"+ FAOSTATLanguage.print().itemCodes() +"'");
		}
		selects.add("I.ItemName" + faostatLanguage + " as '"+ FAOSTATLanguage.print().item() +"'");
		
		
		// ELEMENTS
		if ( addCodes ) {
			selects.add("D.ElementCode as '"+ FAOSTATLanguage.print().elementCodes() +"'");
		}
		selects.add("E.ElementName" + faostatLanguage + " as '"+ FAOSTATLanguage.print().element() +"'");
		
		// YEAR
		selects.add("D.Year as '"+ FAOSTATLanguage.print().year() +"'");
		
		// MEASUREMENT UNIT
		if ( addMeasurementUnit ) {
			selects.add("E.UnitName" + faostatLanguage + " as '"+ FAOSTATLanguage.print().unit() +"'");
		}
		
		// VALUE TODO: dynamic ROUND
		selects.add("CAST(D.Value as numeric(38,2)) as '"+ FAOSTATLanguage.print().value() +"'");
//		selects.add("D.Value as '"+ FAOSTATLanguage.print().value() +"'");
		
		// FLAG
		if ( addFlag ) {
			if ( addCodes ) {
				selects.add("D.Flag as '"+ FAOSTATLanguage.print().flagCode() +"'");
			}
			selects.add("F.FlagDescription" + faostatLanguage + " as '"+ FAOSTATLanguage.print().flag() +"'");
		}
		
		// adding flag boolean
		p.setAddFlag(addFlag);

		// Add to the select
		p.setSelects(selects);
		
		
		p.setIsTradeMatrix(isTradeMatrix);
	}

	
	/** This is use to retrieve the current used language from the user 
	 * 	the Default is "E" = English
	 * **/
	public static String faostatLanguage = "E";
	
	public static HashMap<String, String> faostatLanguages;
	
	static {
		faostatLanguages = new HashMap<String, String>();

		faostatLanguages.put("en", "E");
		faostatLanguages.put("fr", "F");
		faostatLanguages.put("es", "S");
		faostatLanguages.put("ch", "C");
		faostatLanguages.put("ar", "A");
		faostatLanguages.put("ru", "R");
	}
	
	public static HashMap<String, String> faostatToLocaleLanguages;
	
	static {
		faostatToLocaleLanguages = new HashMap<String, String>();

		faostatToLocaleLanguages.put("E", "en");
		faostatToLocaleLanguages.put("F", "fr");
		faostatToLocaleLanguages.put("S", "es");
	}

	public static String browser;
	
	public static String version;
	
}
