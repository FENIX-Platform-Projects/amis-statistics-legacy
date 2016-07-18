package org.fao.fenix.web.modules.amis.common.constants;

public enum AMISCurrentView {

	HOME,

	COMPARE, COMPARE_DATASOURCES, COMPARE_ACTIVITY_TRACKING,

	DOWNLOAD, DOWNLOAD_STANDARD, DOWNLOAD_FULL_BALANCE,

	INPUT,

	STATISTICALNOTES,
	
	PRODUCTION;
	
	
	public static AMISCurrentView getCurrentView(String name) {
		
		AMISCurrentView currentView = null;
		 
		 for (AMISCurrentView view: AMISCurrentView.values()) {
		       if(name.equals(view.name())) {
		    		currentView = view;
		    		break;
		    	}
		    }
		 
		 return currentView;
	}

}
