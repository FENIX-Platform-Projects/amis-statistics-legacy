package org.fao.fenix.web.modules.dataviewer.common.enums.faostat;


public enum FAOSTATCurrentView {

	HOME, 
	
	// SEARCH 
	SEARCH_DATA,
	
	// VISUALIZARTION VIEWS
	VISUALIZE, VISUALIZE_BY_DOMAIN, VISUALIZE_BY_TOPIC, VISUALIZE_BY_AREA, VISUALIZE_TOP_20,
	
	// DOWNLOAD VIEWS
	DOWNLOAD, DOWNLOAD_STANDARD, DOWNLOAD_BULK,
	
	// Metadata
	METADATA, METADATA_ABBREVIATIONS, METADATA_GLOSSARY, METADATA_CLASSIFICATION, METADATA_UNITS, METADATA_CURRENCY, METADATA_METHODOLOGY,
	
	// Comparison View
	COMPARE, COMPARE_BY_AREA, COMPARE_DATA,

	// ANALYSIS
	ANALYSIS;



 public static FAOSTATCurrentView getCurrentView(String name) {
	
	 FAOSTATCurrentView currentView = null;
	 
	 for (FAOSTATCurrentView view: FAOSTATCurrentView.values()) {
	       if(name.equals(view.name())) {
	    		currentView = view;
	    		break;
	    	}
	    }
	 
	 return currentView;
}

}