package org.fao.fenix.web.modules.cpf.common.constants;

public enum CPFCurrentView {

	HOME,
	CREATE_CPF_RESULTS_MATRIX,
	FIND_CPF;


	public static CPFCurrentView getCurrentView(String name) {

		CPFCurrentView currentView = null;

		 for (CPFCurrentView view: CPFCurrentView.values()) {
		       if(name.equals(view.name())) {
		    		currentView = view;
		    		break;
		    	}
		    }

		 return currentView;
	}

}
