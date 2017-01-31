package org.fao.fenix.web.modules.adam.common.enums;

public enum ADAMCurrentVIEW {
	
	HOME, ADAMVIEW, FAOVIEW, ASKADAM, DONORMATRIX, PROFILES, ANALYSE, BROWSE, LINKS,
	
	//analyse sub-menu
	ANALYSE_VENN, ANALYSE_PRIORITIES, ANALYSE_PARTNER_MATRIX, ANALYSE_PROJECTS, ANALYSE_IMPLEMENTING_AGENCIES,
	ANALYSE_FAO_COMPARATIVE_ADVANTAGE;

	public static ADAMCurrentVIEW getCurrentView(String name) {
		
		ADAMCurrentVIEW currentView = null;
		 
		 for (ADAMCurrentVIEW view: values()) {
		       if(name.equals(view.name())) {
		    		currentView = view;
		    		break;
		    	}
		    }
		 
		 return currentView;
	}
}
