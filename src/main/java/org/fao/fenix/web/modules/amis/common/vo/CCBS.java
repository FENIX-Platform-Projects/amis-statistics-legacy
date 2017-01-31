package org.fao.fenix.web.modules.amis.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.amis.client.control.input.Month;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

import com.extjs.gxt.ui.client.store.ListStore;

public class CCBS
{
//	public enum Codes { CCBS_POPULATION("CCBSDatasetPopulation"),
//
//						CCBS_PRODUCTION("CCBSDatasetProduction"),
//
//						CCBS_IMPORTS_J_J("CCBSDatasetJJImports"),
//						CCBS_IMPORTS("CCBSDatasetImports"),
//						//CCBS_COMMERCIAL_IMPORTS("CCBSDatasetCommercialImports"),
//						//CCBS_FOOD_AID("CCBSDatasetFoodAid"),
//
//						CCBS_FOOD_USE("CCBSDatasetFoodUse"),
//						CCBS_FEED_USE("CCBSDatasetFeedUse"),
//						CCBS_OTHER_USES("CCBSDatasetOtherUses"),
//
//						CCBS_EXPORTS_J_J("CCBSDatasetJJExports"),
//						CCBS_EXPORTS("CCBSDatasetExports"),
//
//						CCBS_CLOSING_STOCKS("CCBSDatasetClosingStocks"),
//						CCBS_GOVERNMENT_STOCKS("CCBSDatasetGovernmentStocks"),
//
//						CCBS_AREA_HARVESTED("CCBSDatasetAreaHarvested");
//
//						private String dscode;
//
//						private Codes(String dscode)
//						{
//							this.dscode = dscode;
//						}
//
//						public String getDatasetCode() { return dscode; }
//		}

//	public enum Codes { CCBS_POPULATION("Total Population"),
//
//		CCBS_OPENING_STOCKS("Opening Stocks"),
//		CCBS_PRODUCTION("Production"),
//		
//		CCBS_AREA_HARVESTED("Area Harvested"),
//
//		CCBS_IMPORTS_J_J("Imports (July/June)"),
//		CCBS_IMPORTS("Imports"),
//		//CCBS_COMMERCIAL_IMPORTS("CCBSDatasetCommercialImports"),
//		//CCBS_FOOD_AID("CCBSDatasetFoodAid"),
//
//		CCBS_FOOD_USE("Food"),
//		CCBS_FEED_USE("Feed"),
//		CCBS_OTHER_USES("Other Utilisation"),
//
//		CCBS_EXPORTS_J_J("Exports (July/June)"),
//		CCBS_EXPORTS("Exports"),
//
//		CCBS_CLOSING_STOCKS("Closing Stocks"),
//		CCBS_GOVERNMENT_STOCKS("Government Stocks");
//
//		private String dscode;
//
//		private Codes(String dscode)
//		{
//			this.dscode = dscode;
//		}
//
//		public String getDatasetCode() { return dscode; }
//}
	
	public enum Codes { CCBS_POPULATION("Population"),

		CCBS_OPENING_STOCKS("Opening Stocks"),
		CCBS_PRODUCTION("Production"),
		
		CCBS_AREA_HARVESTED("Area Harvested"),

		//CCBS_IMPORTS_J_J("Imports (July/June)"),
		CCBS_IMPORTS_J_J("Imports"),
		CCBS_IMPORTS("Imports (NMY)"),
		//CCBS_COMMERCIAL_IMPORTS("CCBSDatasetCommercialImports"),
		//CCBS_FOOD_AID("CCBSDatasetFoodAid"),

		CCBS_FOOD_USE("Food Use"),
		CCBS_FEED_USE("Feed Use"),
		CCBS_OTHER_USES("Other Uses"),

		//CCBS_EXPORTS_J_J("Exports (July/June)"),
		CCBS_EXPORTS_J_J("Exports"),
		CCBS_EXPORTS("Exports (NMY)"),

		CCBS_CLOSING_STOCKS("Closing Stocks"),
		CCBS_GOVERNMENT_STOCKS("Government Stocks");

		private String dscode;

		private Codes(String dscode)
		{
			this.dscode = dscode;
		}

		public String getDatasetCode() { return dscode; }
}
	//This variable is used when to check which kind of user is logged in...
	//In fact the user can belong to the Amis Secretariat or to a country
	public static String USERNAME;
	//This variable is used to check if the user has changed same cells of the grid
	//If it's so... show the message when the user selects other years in the north panel
	//because it can lose all its changes
	public static boolean CHANGES_IN_THE_GRID = false;
	//This variable is used to manage the action: Show and Hide Flags
	//of the icon on the rigth top of the Cbs panel
	public static boolean isHide = false;
	//This variable is for FAO-CBS Sheet
//	public static String elementForTraining = "FAO-CBS";
    public static String elementForTraining = "FAO-AMIS";
	//This variable is for NEW Sheet
	public static String elementEmpty = "NEW";
	//This variable is for Data Source Sheet
	public static String elementGeneralDatasource;
	public static List<String> DATA_SOURCE_NAMES= new ArrayList();
	public static List<String> COUNTRY_NAMES = new ArrayList();
	public static List<String> COUNTRY_CODES = new ArrayList();
	public static List<String> COMMODITY_NAMES_CBS= new ArrayList();
	public static List<String> COMMODITY_CODES_CBS= new ArrayList();
	public static List<String> YEARS= new ArrayList();
	//This List contains all the years that are in YEARS without gap
	//Could happen that some years are not in the database
	public static List<String> SEQUENTIAL_YEARS= new ArrayList();
	//The years that are as column header
	public static List<String> YEARS_ON_GRID= new ArrayList();
	public static List<String> OTHER_YEARS= new ArrayList();
	//Cointains the forecast month for each year
	public static HashMap<String, String> MONTH_FOR_YEARS= new HashMap<String, String>();
	//public static List<String> MONTH_FOR_YEARS= new ArrayList();
	public static ListStore<AMISCodesModelData> FLAGS;
	public static ListStore<AMISCodesModelData> LAST_YEAR;
	//Cointains the Header of Row in CBS Sheet, its code and the original label in the database... FOR ALMOST EVERY ELEMENT
	public static HashMap<String, HashMap<String, String>> NAMES_CODES_FIELD= new HashMap<String, HashMap<String, String>>();
	//Cointains the Header of Row in CBS Sheet, its code and the original label in the database... ONLY FO THE ELEMENT IN THE OTHER_ELEMENTS_NAMES
	public static HashMap<String, HashMap<String, String>> OTHER_NAMES_CODES_FIELD= new HashMap<String, HashMap<String, String>>();
	//This list is used to store Production and Other Uses Form listForProdAndOtherUForm
	public static List<ClientCbsDatasetResult> LIST_FOR_PROD_AND_OTHER_USES_FORM = new ArrayList();
	//HashMap of the Sub Elements for the Other Uses form: name, code
	public static HashMap<String, String> SUB_ELEMENTS_WITH_CODE = new HashMap<String, String>();
	public static int NUM_BOOK_PAGE = 1;
	//This variable contains the first year to show in the combo box of the last column of the grid
	public static String FIRST_YEAR_COMBO= "2010/11";
	//This variable contains the last year to show in the combo box of the last column of the grid
	public static String LAST_YEAR_COMBO = "2013/14";
	//This variable contains the first month to show in the combo box of the last column of the grid
	public static String FIRST_MONTH_COMBO;
	//This variable contains the last month to show in the combo box of the last column of the grid
	public static String LAST_MONTH_COMBO;
	//This is a month for a non applicable commodity(COARSE GRAIN and TOTAL CEREAL) 
	public static String N_A_MONTH = "N/A"; 
	//This is for a validated value that is not more forecast
	public static String VALIDATED_MONTH = "VALIDATED";
	//This list is used to contain the Hashmap with YEAR,MONTH for a specific element (the best Month for the Year) 
	public static List<HashMap<String, String>> MONTH_FORECAST_TO_QUERY = new ArrayList<HashMap<String, String>>();
	//This values indicate the number of row of the Production Element and Other Uses Element in the Grid
	public static int PRODUCTION_ROW_NUM = 5;
	//public static int OPENING_ROW_NUM = 4;
	//This is a particular row ... because it's not editable(grey) just to indicate to the user
	//that has to insert the value and flag through Production Row
	public static int AREA_HARVESTED_ROW_NUM = 6;
	public static int OTHER_USES_ROW_NUM = 14;
	public static int OPENING_STOCKS_ROW_NUM = 4;
	public static int CLOSING_STOCKS_ROW_NUM = 17;
	//This variable is used to put a listener on the Closing Stocks  
	//Last column
	public static int FORECAST_COL_NUM = 12;
	//The second last column
	public static int BEFORE_FORECAST_COL_NUM = 10;
	public static String OPENING_STOCKS_ELEMENT_CODE = "18";
	public static String CLOSING_STOCKS_ELEMENT_CODE = "16";
	//This hashmap contains the element with this property: YEAR - OPENING STOCKS, CLOSING STOCKS
	//It's used to update the grid when the user change the opening or closing stocks element for the last three columns
	public static HashMap<String, HashMap<String,String>> OPENING_CLOSING_STOCKS_HASHMAP;
	
	//This variable is used to fill the Combo in the last column of the grid... if it's false
	//this information is not available, so in this list is shown only N\A value
	public static boolean MARKETING_YEAR_AVAILABLE= false;
	
	public static String AMIS_SECRETARIAT = "amisSecretariat";
	public static String IGC_USER = "igcUser";
//	public static final String COMMODITY_CODES[] = {
//			"1001",   // wheat
//			"1003",   // barley
//			"1005",   // maize
//			"100610", // rice (paddy)
//			"100630", // rice (milled)
//			"100820", // millet
//			"100890", // other cereals
//			"1007",   // grain sorghum
//			"1002",   // rye
//			"1004"    // oats
//	};
	
	//The number of years to show
	public static int NUMBER_OF_YEARS = 6;
	
	public static String NAME_FIRST_YEARS_TO_SHOW;
	public static int NUMBER_FIRST_YEARS_TO_SHOW;

	public static final String COMMODITY_WITHOUT_MARKETING_YEAR[] = {
		"COARSE GRAINS",
		"TOTAL CEREALS"
	};
	
	// TODO: should be read from localized strings
//	public static final String COMMODITY_NAMES[] = {
//			"Wheat",
//			"Barley",
//			"Maize",
//			"Rice (paddy)",
//			"Rice (milled)",
//			"Millet",
//			"Other cereals",
//			"Grain sorghum",
//			"Rye",
//			"Oats",
//			"Coarse grains",
//			"Total cereals"
//	};

//	public static final String MONTH_WITH_FLAG[] = {		
//		"January Forecast",
//		"February Forecast",
//		"March Forecast",
//		"April Forecast",
//		"May Forecast",
//		"June Forecast",
//		"July Forecast",
//		"August Forecast",
//		"September Forecast",
//		"October Forecast",
//		"November Forecast",
//		"December Forecast",
//		"Estimate",
//		"Validated"
//};
	
//	public static final String MONTH_WITH_FLAG[] = {		
//		"Jan Forecast",
//		"Feb Forecast",
//		"Mar Forecast",
//		"Apr Forecast",
//		"May Forecast",
//		"Jun Forecast",
//		"Jul Forecast",
//		"Aug Forecast",
//		"Sep Forecast",
//		"Oct Forecast",
//		"Nov Forecast",
//		"Dec Forecast",
//		"Estimate",
//		"Validated"
//};
	
//	public static final String MONTH_WITH_FLAG[] = {		
//		"Jan Fc*",
//		"Feb Fc*",
//		"Mar Fc*",
//		"Apr Fc*",
//		"May Fc*",
//		"Jun Fc*",
//		"Jul Fc*",
//		"Aug Fc*",
//		"Sep Fc*",
//		"Oct Fc*",
//		"Nov Fc*",
//		"Dec Fc*",
//		"Est*",
//		"Val*"
//};
	
	public static final String MONTH_WITH_FLAG[] = {		
		"Jan",
		"Feb",
		"Mar",
		"Apr",
		"May",
		"Jun",
		"Jul",
		"Aug",
		"Sep",
		"Oct",
		"Nov",
		"Dec"
};

	public static final String MONTH_WITH_FLAG_LONG[] = {		
		"January",
		"February",
		"March",
		"April",
		"May",
		"June",
		"July",
		"August",
		"September",
		"October",
		"November",
		"December",
		"Estimate",
		"Validated"
};
	
	public static final HashMap<String, String> SUB_ELEMENTS_ROW_NUM = new HashMap(){
		{
			        put("Production","5");  
			        put("Area Harvested","5");  
			        put("Yield","5");  
			        put("Seeds","14");  
			        put("Post Harvest Losses","14"); 
			        put("Industrial Use","14");  
			        put("Malt","14"); 
			        put("Biofuel","14");  
			        put("Sweeteners","14"); 
			        put("Starch","14");  
			        put("Others","14");
		}
		};  

	
//	// TODO: should be read from the database
//	public static final int[] YEARS = { /* 1978, 1979,
//										1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989,
//										1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, */
//										2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008
//	};
	
	// TODO: should be read from the database
//	public static final String[] YEARS = { /* 1978, 1979,
//										1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989,
//										1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, */
//										"2000/01", "2001/02", "2002/03", "2003/04", "2004/05", "2005/06", "2006/07", "2007/08", "2008/09", "2009/10"
//	};

//	public static final String[] OTHER_YEARS = { 
//		"2011/12", "2012/13", "2013/14", "2014/15", "2015/16", "2016/17", "2017/18", "2018/19", "2019/20", "2020/21", "2010/11"
//};
	
	
//	public static final String FIELD_NAMES[] = { "Population",            //  0 <--  0
//												 "Unbalanced",	          //  1 <--  empty
//												 "Total Supply",          //  2 <--  = domestic availability + imports
//												 "Domestic Availability", //  3 <--  = opening stocks + production
//												 "Opening Stocks",        //  4 <--  = closing stocks of previous year
//												 "Production",            //  5 <--  1
//												 "J/J Imports",           //  6 <--  2
//												 "Imports",               //  7 <--  3
//												 "Commercial Imports",    //  8 <--  4
//												 "Food Aid",              //  9 <--  5
//												 "Unbalanced Imports",    // 10 <--  empty
//												 "Total Utilization",     // 11 <--  = domestic utilization + exports + closing stocks
//												 "Domestic Utilization",  // 12 <--  = food use + feed use + other uses
//												 "Food Use",              // 13 <--  6
//												 "Feed Use",              // 14 <--  7
//												 "Other Uses",            // 15 <--  8
//												 "J/J Exports",           // 16 <--  9
//												 "Exports",               // 17 <-- 10
//												 "Closing Stocks",        // 18 <-- 11
//												 "Of which Government",   // 19 <-- 12
//												 "Per Cap. Food Use",     // 20 <--  = food use / population * 1000 (circa 100)
//												 "Per Cap. Feed Use",     // 21 <--  = feed use / population * 1000 (circa 100)
//												 "Area Harvested",        // 22 <-- 13
//												 "Yield"                  // 23 <--  = production / area
//	};

//	public static final String FIELD_NAMES[] = { "Population",            //  0 <--  0
//		 "Unbalanced",	          //  1 <--  empty
//		 "Total Supply",          //  2 <--  = domestic availability + imports
//		 "Domestic Availability", //  3 <--  = opening stocks + production
//		 "Opening Stocks",        //  4 <--  = closing stocks of previous year
//		 "Production",            //  5 <--  1
//		 "J/J Imports",           //  6 <--  2
//		 "Imports",               //  7 <--  3
//		 //"Commercial Imports",    //  8 <--  4
//		 //"Food Aid",              //  9 <--  5
//		 "Unbalanced Imports",    // 10 <--  empty
//		 "Total Utilization",     // 11 <--  = domestic utilization + exports + closing stocks
//		 "Domestic Utilization",  // 12 <--  = food use + feed use + other uses
//		 "Food Use",              // 13 <--  6
//		 "Feed Use",              // 14 <--  7
//		 "Other Uses",            // 15 <--  8
//		 "J/J Exports",           // 16 <--  9
//		 "Exports",               // 17 <-- 10
//		 "Closing Stocks",        // 18 <-- 11
//		 "Of which Government",   // 19 <-- 12
//		 "Per Cap. Food Use",     // 20 <--  = food use / population * 1000 (circa 100)
//		 "Per Cap. Feed Use",     // 21 <--  = feed use / population * 1000 (circa 100)
//		 "Area Harvested",        // 22 <-- 13
//		 "Yield"                  // 23 <--  = production / area
//};
	
	public static final String SUB_ELEMENTS_NAMES[] = {
		"Seeds",
		"Post Harvest Losses",
		"Industrial Use",
		"Malt",
		"Biofuel",
		"Sweeteners",
		"Starch",
		"Others"
	};
	
	public static final String SUB_PRODUCTION_ELEMENTS_NAMES[] = {
		"Production",
		"Area Harvested",
		"Yield"
	};
	
	public static final String OTHER_ELEMENTS_NAMES[] = {
		"Domestic Availability",
		"Total Utilization",
		"Domestic Utilization",
		"Per Cap. Food Use"
	};

	public static final String FIELD_NAMES[] = { "Population",            //  0 <--  0
		 "Unbalanced",	          //  1 <--  empty
		 "Total Supply",          //  2 <--  = domestic availability + imports
		 "Domestic Availability", //  3 <--  = opening stocks + production
		 "Opening Stocks",        //  4 <--  = closing stocks of previous year
		 "Production",            //  5 <--  1
		 "Area Harvested",        // 6 <-- 2
		 "Yield",                  // 7 <--  = production / area
		 //"J/J Imports",           //  8 <--  3
		 "Imports",           //  8 <--  3
		// "Imports",               //  9 <--  4
		 "Imports (NMY)",				//  9 <--  4
		 //"Commercial Imports",    //  8 <--  4
		 //"Food Aid",              //  9 <--  5
		// "Unbalanced Imports",    // 10 <--  empty
		 "Total Utilization",     // 11 <--  = domestic utilization + exports + closing stocks
		 "Domestic Utilization",  // 12 <--  = food use + feed use + other uses
		 "Food Use",              // 13 <--  5
		 "Feed Use",              // 14 <--  6
		 "Other Uses",            // 15 <--  7
		// "J/J Exports",           // 16 <--  8
		 "Exports",           // 16 <--  8
		// "Exports",               // 17 <-- 9
		 "Exports (NMY)",				// 17 <-- 9
		 "Closing Stocks",        // 18 <-- 10
		 "Of which Government",   // 19 <-- 11
		 "Per Cap. Food Use"     // 20 <--  = food use / population * 1000 (circa 100)
		// "Per Cap. Feed Use",     // 21 <--  = feed use / population * 1000 (circa 100)
};
	
	public static final String FIELD_NAMES_WITH_UNIT[] = { "Population",            //  0 <--  0
		 "Unbalanced (Million tonnes)",	          //  1 <--  empty
		 "Total Supply",          //  2 <--  = domestic availability + imports
//		 "Domestic Availability (Million tonnes)", //  3 <--  = opening stocks + production
		 "Domestic Availability", //  3 <--  = opening stocks + production
		 "Opening Stocks",        //  4 <--  = closing stocks of previous year
		 "Production",            //  5 <--  1
		 "Area Harvested",        // 6 <-- 2
		 "Yield",                  // 7 <--  = production / area
		//"J/J Imports",           //  8 <--  3
		 "Imports",           //  8 <--  3
		// "Imports",               //  9 <--  4
		 "Imports (NMY)",				//  9 <--  4
		 //"Commercial Imports",    //  8 <--  4
		 //"Food Aid",              //  9 <--  5
		// "Unbalanced Imports",    // 10 <--  empty
		 "Total Utilization",     // 11 <--  = domestic utilization + exports + closing stocks
		 "Domestic Utilization",  // 12 <--  = food use + feed use + other uses
		 "Food Use",              // 13 <--  5
		 "Feed Use",              // 14 <--  6
		 "Other Uses",            // 15 <--  7
		// "J/J Exports",           // 16 <--  8
		 "Exports",           // 16 <--  8
		// "Exports",               // 17 <-- 9
		 "Exports (NMY)",				// 17 <-- 9
		 "Closing Stocks",        // 18 <-- 10
		 "Of which Government",   // 19 <-- 11
		 "Per Cap. Food Use (Kg/Yr)"     // 20 <--  = food use / population * 1000 (circa 100)
		// "Per Cap. Feed Use",     // 21 <--  = feed use / population * 1000 (circa 100)
};
	
	public static final String FIELD_NAMES_EXPORT_EXCEPTION[] = { "Population",            //  0 <--  0
		 "Unbalanced",	          //  1 <--  empty
		 "Total Supply",          //  2 <--  = domestic availability + imports
		 "Domestic Availability", //  3 <--  = opening stocks + production
		 "Opening Stocks",        //  4 <--  = closing stocks of previous year
		 "Production",            //  5 <--  1
		 "Area Harvested",        // 6 <-- 2
		 "Yield",                  // 7 <--  = production / area
		 "Imports",           //  8 <--  3
		 "Imports (NMY)",           //  9 <--  4
		 //"Commercial Imports",    //  8 <--  4
		 //"Food Aid",              //  9 <--  5
		// "Unbalanced Imports",    // 10 <--  empty
		 "Total Utilization",     // 11 <--  = domestic utilization + exports + closing stocks
		 "Domestic Utilization",  // 12 <--  = food use + feed use + other uses
		 "Food Use",              // 13 <--  5
		 "Feed Use",              // 14 <--  6
		 "Other Uses",            // 15 <--  7
		 "Exports", // 16 <--  8
		 "Exports (NMY)",               // 17 <-- 9
		 "Closing Stocks",        // 18 <-- 10
		 "Of which Government",   // 19 <-- 11
		 "Per Cap. Food Use"    // 20 <--  = food use / population * 1000 (circa 100)
		// "Per Cap. Feed Use",     // 21 <--  = feed use / population * 1000 (circa 100)
};
	
	public static final String FIELD_NAMES_IMPORT_EXPORT_EXCEPTION[] = { "Population",            //  0 <--  0
		 "Unbalanced",	          //  1 <--  empty
		 "Total Supply",          //  2 <--  = domestic availability + imports
		 "Domestic Availability", //  3 <--  = opening stocks + production
		 "Opening Stocks",        //  4 <--  = closing stocks of previous year
		 "Production",            //  5 <--  1
		 "Area Harvested",        // 6 <-- 2
		 "Yield",                  // 7 <--  = production / area
		 "Imports",      //  8 <--  3
		 "Imports (NMY)",               //  9 <--  4
		 //"Commercial Imports",    //  8 <--  4
		 //"Food Aid",              //  9 <--  5
		// "Unbalanced Imports",    // 10 <--  empty
		 "Total Utilization",     // 11 <--  = domestic utilization + exports + closing stocks
		 "Domestic Utilization",  // 12 <--  = food use + feed use + other uses
		 "Food Use",              // 13 <--  5
		 "Feed Use",              // 14 <--  6
		 "Other Uses",            // 15 <--  7
		 "Exports",      // 16 <--  8
		 "Exports (NMY)",               // 17 <-- 9
		 "Closing Stocks",        // 18 <-- 10
		 "Of which Government",   // 19 <-- 11
		 "Per Cap. Food Use"     // 20 <--  = food use / population * 1000 (circa 100)
		// "Per Cap. Feed Use",     // 21 <--  = feed use / population * 1000 (circa 100)
};
	
	//Calculated fields
	public static final String CALCULATED_FIELD_NAMES[] = { 
		 "Unbalanced",	     
		 "Total Supply",           
		 "Domestic Availability", 
		 "Opening Stocks",       
		 "Total Utilization",          
		 "Domestic Utilization",      
		 "Per Cap. Food Use"
};
	
//	public static final boolean FIELD_EDITABLE[] = { true,  // Population
//													 false, // Unbalanced
//													 false, // Total Supply
//													 false, // Domestic Availability
//													 false, // Opening Stocks
//													 true,  // Production
//													 true,  // J/J Imports
//													 true,  // Imports
//													 true,  // Commercial Imports
//													 true,  // Food Aid
//													 false, // Unbalanced Imports
//													 false, // Total Utilization
//													 false, // Domestic Utilization
//													 true,  // Food Use
//													 true,  // Feed Use
//													 true,  // Other Uses
//													 true,  // J/J Exports
//													 true,  // Exports
//													 true,  // Closing Stocks
//													 true,  // Of which Government
//													 false, // Per Cap. Food Use
//													 false, // Per Cap. Feed Use
//													 true,  // Area Harvested
//													 false, // Yield
//	};
	
	//If the elements is calculated it's not necessary to save it in the historical table
	public static String CALCULATED_ELEMENTS_CODE[] = {
		"19", "27", "18", "4", "20", "35", "25"
	};
	
	public static String COMMODITY_CODE_TO_ELIMINATE[] = {
		"2", "7", "8"
	};	
	
	public static String CBS_COMMODITY_CODE[] = {
		"1", "4", "5", "6"
	}; 
	
	public static final boolean FIELD_EDITABLE[] = { true,  // Population
		 false, // Unbalanced
		 false, // Total Supply
		 false, // Domestic Availability
		 false, // Opening Stocks
		 false,  // Production
		 false,  // Area Harvested
		 false, // Yield
		 true,  // J/J Imports
		 true,  // Imports
		// true,  // Commercial Imports
		// true,  // Food Aid
		// false, // Unbalanced Imports
		 false, // Total Utilization
		 false, // Domestic Utilization
		 true,  // Food Use
		 true,  // Feed Use
		 false,  // Other Uses
		 true,  // J/J Exports
		 true,  // Exports
		 true,  // Closing Stocks
		 true,  // Of which Government
		 false // Per Cap. Food Use
		// false, // Per Cap. Feed Use
};
	

}
