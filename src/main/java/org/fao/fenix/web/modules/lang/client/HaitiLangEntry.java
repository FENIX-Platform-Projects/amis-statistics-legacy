package org.fao.fenix.web.modules.lang.client;

import com.google.gwt.core.client.GWT;

public class HaitiLangEntry {

	private static HaitiLang_FR instance_fr = null;
	
	private static HaitiLang_EN instance_en = null;
	
	protected HaitiLangEntry() {
 
	}

	@Deprecated
	public static HaitiLang getInstance() {
		if (instance_en == null) 
			instance_en = (HaitiLang_EN) GWT.create(HaitiLang_EN.class);
		return instance_en;
	}
	
	public static HaitiLang getInstance(String language) {
		if (language == null || language.equals("")) {
			if (instance_en == null) 
				instance_en = (HaitiLang_EN) GWT.create(HaitiLang_EN.class);
			return instance_en;
		}
		Language lang = Language.valueOf(language);
		switch (lang) {
			case en:
				if (instance_en == null) 
					instance_en = (HaitiLang_EN) GWT.create(HaitiLang_EN.class);
				return instance_en;
			case fr:
				if (instance_fr == null) 
					instance_fr = (HaitiLang_FR) GWT.create(HaitiLang_FR.class);
				return instance_fr;
			case EN:
				if (instance_en == null) 
					instance_en = (HaitiLang_EN) GWT.create(HaitiLang_EN.class);
				return instance_en;
			case FR:
				if (instance_fr == null) 
					instance_fr = (HaitiLang_FR) GWT.create(HaitiLang_FR.class);
				return instance_fr;
		}
		return null;
	}
	
	enum Language {
		en, fr, es, EN, FR;
	}
	
}