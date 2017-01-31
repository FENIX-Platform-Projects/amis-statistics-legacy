package org.fao.fenix.web.modules.adam.common.enums;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum ADAMBoxContent implements IsSerializable {

	FAO, RECIPIENT, SECTOR, DONOR, CHANNEL, BAR, BAR_WITH_CATEGORIES, BAR_STACK_OR_DONOR, BAR_STACK_DAC_DONOR, PIE, TIMESERIE, SCATTER, CHART, MAP, TABLE, MAP_COUNTRIES_BY_DONOR, MAP_COUNTRIES_BY_DONOR_GOOGLE, TOP_COUNTRIES_BY_DONOR, TOP_DONORS_TABLE, TOP_DONORS_TABLE_FILTERED, TOP_SECTORS_DONOR_VIEW_TABLE, TOP_SECTORS_DONOR_COUNTRY_VIEW_TABLE, 
	TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE, TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE, TOP_AGRICULTURAL_SECTORS_DONOR_COUNTRY_VIEW_TABLE, FAVOURITE_PURPOSES_QUESTIONS_VIEW, OLAP, PROJECTS_LIST_CRS, ROWS_COUNT,CUSTOM_CHART, DONOR_PROFILE_TABLE, DONOR_PROCESSES_TABLE, DONOR_ODA_TABLE,
	COMPARATIVE_ADVANTAGE, BAR_STACK_SECTOR_COMPARISON, CONTRIBUTION_TABLE,IMPLEMENTING_AGENCIES_TABLE, RESOURCE_PARTNER_MATRIX,
	
	// VENN
	VENN_PRIORITIES, VENN_RECIPIENT_PRIORITIES, VENN_DONOR_PRIORITIES, VENN_CHANNEL_PRIORITIES,
	
	VENN_MATRIX, VENN_RECIPIENT_MATRIX, VENN_CHANNEL_MATRIX, VENN_DONOR_MATRIX, VENN_RECIPIENT_CHANNEL_MATRIX,
	

	// SOs and ORs
	FILTER_ALL_SO, FILTER_BY_SO,  
	
	// RETURN SOs AND ORs
	RETURN_OR, RETURN_SO,
	
	
	// RETURN FILTERED VALUES CHANNELS
	GET_FILTERED_VALUES, GET_IMPLENTING_AGENCIES,
	
	// GET VIEW MATRIX
	VIEW_MATRIX,
	
	// FPMIS_REQUESTS
	FPMIS_REQUEST, FPMIS_GET_COUNTRY_ID, FPMIS_CHART;
	
	
}