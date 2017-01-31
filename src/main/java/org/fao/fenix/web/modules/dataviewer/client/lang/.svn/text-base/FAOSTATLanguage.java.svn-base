package org.fao.fenix.web.modules.dataviewer.client.lang;

import com.google.gwt.core.client.GWT;

public class FAOSTATLanguage {

	private static FAOSTATLanguageConstants instance = null;

	protected FAOSTATLanguage() {
		// Exists only to defeat instantiation.
	}

	public static FAOSTATLanguageConstants print() {
		if (instance == null) 
			instance = (FAOSTATLanguageConstants) GWT.create(FAOSTATLanguageConstants.class);
		return instance;
	}
	
}