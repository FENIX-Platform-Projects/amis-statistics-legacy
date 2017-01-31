package org.fao.fenix.web.modules.amis.client.lang;

import com.google.gwt.core.client.GWT;

public class AMISLanguage {

	private static AMISLanguageConstants instance = null;

	protected AMISLanguage() {
		// Exists only to defeat instantiation.
	}

	public static AMISLanguageConstants print() {
		if (instance == null)
			instance = (AMISLanguageConstants) GWT.create(AMISLanguageConstants.class);
		return instance;
	}

}