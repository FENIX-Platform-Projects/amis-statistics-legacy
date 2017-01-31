package org.fao.fenix.web.modules.amis.client.lang;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.i18n.client.Constants.DefaultStringValue;

public interface AMISLanguageConstants  extends ConstantsWithLookup {

	@DefaultStringValue("Agricultural Management Information System")
	public String amisTitle();
	
	@DefaultStringValue("Production")
	public String PRODUCTION();
	
	@DefaultStringValue("Trade")
	public String TRADE();
	
	@DefaultStringValue("Products")
	public String PSD_PRODUCTS();
	
	@DefaultStringValue("Elements")
	public String PSD_ELEMENTS();
	
	@DefaultStringValue("Timerange")
	public String PSD_TIMERANGE();
	
	@DefaultStringValue("Products")
	public String CCBS_PRODUCTS();
	
	@DefaultStringValue("Elements")
	public String CCBS_ELEMENTS();
	
	@DefaultStringValue("Timerange")
	public String CCBS_TIMERANGE();
	
	@DefaultStringValue("G20 Countries")
	public String AMIS_G20_COUNTRIES();
	
	@DefaultStringValue("Aggregation")
	public String aggregation();
	
	@DefaultStringValue("Products")
	public String AMIS_PRODUCTS();
	
	@DefaultStringValue("Countries")
	public String AMIS_COUNTRIES();

	@DefaultStringValue("AMIS Statistics Home")
	public String HOME();
	
	@DefaultStringValue("DOWNLOAD DATA")
	public String DOWNLOAD();
	
	@DefaultStringValue("VIEW & COMPARE DATA")
	public String COMPARE();

	@DefaultStringValue("INPUT DATA")
	public String INPUT();
	
	@DefaultStringValue("STATISTICAL NOTES")
	public String STATISTICALNOTES();

	@DefaultStringValue("Flags List")
	public String ccbsFlag();
	
	@DefaultStringValue("Country Information")
	public String iconViewCountryInformation();
	
	@DefaultStringValue("View Flags List")
	public String iconViewCcbsFlag();
	
	@DefaultStringValue("Show/Hide Flags")
	public String iconShowHideCcbsFlag();
	
	@DefaultStringValue("AMIS Commodity Balance Sheet (FAO-AMIS)")
	public String ccbsTool();
	
	@DefaultStringValue("AMIS")
	public String ccbsToolTitle();
	
	@DefaultStringValue("Loaded data")
	public String table();
	
	@DefaultStringValue("Registration Form")
	public String cbsRegistrationForm();
	
}