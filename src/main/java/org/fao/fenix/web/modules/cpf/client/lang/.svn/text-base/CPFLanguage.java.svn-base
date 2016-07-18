package org.fao.fenix.web.modules.cpf.client.lang;

import com.google.gwt.core.client.GWT;

public class CPFLanguage {

	private static CPFLanguageConstants instance = null;

	protected CPFLanguage() {
		// Exists only to defeat instantiation.
	}

	public static CPFLanguageConstants print() {
		if (instance == null)
			instance = (CPFLanguageConstants) GWT.create(CPFLanguageConstants.class);
		return instance;
	}

}