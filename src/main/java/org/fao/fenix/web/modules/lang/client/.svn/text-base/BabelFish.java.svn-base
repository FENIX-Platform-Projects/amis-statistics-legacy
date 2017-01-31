package org.fao.fenix.web.modules.lang.client;

import com.google.gwt.core.client.GWT;

public class BabelFish {

	private static FENIXMultilanguage instance = null;

	protected BabelFish() {
		// Exists only to defeat instantiation.
	}

	public static FENIXMultilanguage print() {
		if (instance == null) 
			instance = (FENIXMultilanguage) GWT.create(FENIXMultilanguage.class);
		return instance;
	}
	
}