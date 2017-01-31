package org.fao.fenix.web.modules.adam.common.enums;


public enum ADAMAnalyseVIEW {
	
	ANALYSE_VENN, ANALYSE_PRIORITIES, ANALYSE_PARTNER_MATRIX, ANALYSE_PROJECTS, ANALYSE_IMPLEMENTING_AGENCIES,
	ANALYSE_FAO_COMPARATIVE_ADVANTAGE;

    public static ADAMAnalyseVIEW getCurrentView(String name) {
		
    	ADAMAnalyseVIEW analyseView = null;
		 
		 for (ADAMAnalyseVIEW view: values()) {
		       if(name.equals(view.name())) {
		    	   analyseView = view;
		    		break;
		    	}
		    }
		 
		 return analyseView;
	}
}
