package org.fao.fenix.web.modules.ccbs.common.vo;

public class CCBS
{
	public enum Codes { CCBS_POPULATION("CCBSDatasetPopulation"),

						CCBS_PRODUCTION("CCBSDatasetProduction"),

						CCBS_IMPORTS_J_J("CCBSDatasetJJImports"),
						CCBS_IMPORTS("CCBSDatasetImports"),
						CCBS_COMMERCIAL_IMPORTS("CCBSDatasetCommercialImports"),
						CCBS_FOOD_AID("CCBSDatasetFoodAid"),

						CCBS_FOOD_USE("CCBSDatasetFoodUse"),
						CCBS_FEED_USE("CCBSDatasetFeedUse"),
						CCBS_OTHER_USES("CCBSDatasetOtherUses"),

						CCBS_EXPORTS_J_J("CCBSDatasetJJExports"),
						CCBS_EXPORTS("CCBSDatasetExports"),

						CCBS_CLOSING_STOCKS("CCBSDatasetClosingStocks"),
						CCBS_GOVERNMENT_STOCKS("CCBSDatasetGovernmentStocks"),

						CCBS_AREA_HARVESTED("CCBSDatasetAreaHarvested");

						private String dscode;

						private Codes(String dscode)
						{
							this.dscode = dscode;
						}

						public String getDatasetCode() { return dscode; }
		}

	public static final String COMMODITY_CODES[] = {
			"1001",   // wheat
			"1003",   // barley
			"1005",   // maize
			"100610", // rice (paddy)
			"100630", // rice (milled)
			"100820", // millet
			"100890", // other cereals
			"1007",   // grain sorghum
			"1002",   // rye
			"1004"    // oats
	};

	// TODO: should be read from localized strings
	public static final String COMMODITY_NAMES[] = {
			"Wheat",
			"Barley",
			"Maize",
			"Rice (paddy)",
			"Rice (milled)",
			"Millet",
			"Other cereals",
			"Grain sorghum",
			"Rye",
			"Oats",
			"Coarse grains",
			"Total cereals"
	};

	// TODO: should be read from the database
	public static final int[] YEARS = { /* 1978, 1979,
										1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989,
										1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999, */
										2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008
	};

	public static final String FIELD_NAMES[] = { "Population",            //  0 <--  0
												 "Unbalanced",	          //  1 <--  empty
												 "Total Supply",          //  2 <--  = domestic availability + imports
												 "Domestic Availability", //  3 <--  = opening stocks + production
												 "Opening Stocks",        //  4 <--  = closing stocks of previous year
												 "Production",            //  5 <--  1
												 "J/J Imports",           //  6 <--  2
												 "Imports",               //  7 <--  3
												 "Commercial Imports",    //  8 <--  4
												 "Food Aid",              //  9 <--  5
												 "Unbalanced Imports",    // 10 <--  empty
												 "Total Utilization",     // 11 <--  = domestic utilization + exports + closing stocks
												 "Domestic Utilization",  // 12 <--  = food use + feed use + other uses
												 "Food Use",              // 13 <--  6
												 "Feed Use",              // 14 <--  7
												 "Other Uses",            // 15 <--  8
												 "J/J Exports",           // 16 <--  9
												 "Exports",               // 17 <-- 10
												 "Closing Stocks",        // 18 <-- 11
												 "Of which Government",   // 19 <-- 12
												 "Per Cap. Food Use",     // 20 <--  = food use / population * 1000 (circa 100)
												 "Per Cap. Feed Use",     // 21 <--  = feed use / population * 1000 (circa 100)
												 "Area Harvested",        // 22 <-- 13
												 "Yield"                  // 23 <--  = production / area
	};

	public static final boolean FIELD_EDITABLE[] = { true,  // Population
													 false, // Unbalanced
													 false, // Total Supply
													 false, // Domestic Availability
													 false, // Opening Stocks
													 true,  // Production
													 true,  // J/J Imports
													 true,  // Imports
													 true,  // Commercial Imports
													 true,  // Food Aid
													 false, // Unbalanced Imports
													 false, // Total Utilization
													 false, // Domestic Utilization
													 true,  // Food Use
													 true,  // Feed Use
													 true,  // Other Uses
													 true,  // J/J Exports
													 true,  // Exports
													 true,  // Closing Stocks
													 true,  // Of which Government
													 false, // Per Cap. Food Use
													 false, // Per Cap. Feed Use
													 true,  // Area Harvested
													 false, // Yield
	};
}
