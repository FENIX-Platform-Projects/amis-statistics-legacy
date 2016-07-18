package org.fao.fenix.web.modules.lang.client;

import com.google.gwt.core.client.GWT;

public class CNSALangEntry {

	private static CNSALang_FR instance_fr = null;
	
	private static CNSALang_EN instance_en = null;
	
	protected CNSALangEntry() {
 
	}

	@Deprecated
	public static CNSALang getInstance() {
		if (instance_en == null) 
			instance_en = (CNSALang_EN) GWT.create(CNSALang_EN.class);
		return instance_en;
	}
	
	public static CNSALang getInstance(String language) {
		if (language == null || language.equals("")) {
			if (instance_en == null) 
				instance_en = (CNSALang_EN) GWT.create(CNSALang_EN.class);
			return instance_en;
		}
		Language lang = Language.valueOf(language);
		switch (lang) {
			case en:
				if (instance_en == null) 
					instance_en = (CNSALang_EN) GWT.create(CNSALang_EN.class);
				return instance_en;
			case fr:
				if (instance_fr == null) 
					instance_fr = (CNSALang_FR) GWT.create(CNSALang_FR.class);
				return instance_fr;
		}
		return null;
	}
	
	enum Language {
		en, fr, es;
	}
	
}