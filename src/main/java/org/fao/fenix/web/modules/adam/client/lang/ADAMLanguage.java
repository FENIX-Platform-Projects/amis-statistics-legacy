package org.fao.fenix.web.modules.adam.client.lang;

import com.google.gwt.core.client.GWT;
import org.fao.fenix.web.modules.adam.client.lang.ADAMLanguageConstants;

public class ADAMLanguage {

	private static ADAMLanguageConstants instance = null;

	protected ADAMLanguage() {
		// Exists only to defeat instantiation.
	}

	public static ADAMLanguageConstants print() {
		if (instance == null)
			instance = (ADAMLanguageConstants) GWT.create(ADAMLanguageConstants.class);
		return instance;
	}

}