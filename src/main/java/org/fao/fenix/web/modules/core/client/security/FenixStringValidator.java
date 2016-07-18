package org.fao.fenix.web.modules.core.client.security;

public class FenixStringValidator {

	public static boolean filled(String field) {
		boolean filled = false;
		if (field != null && !field.equals(""))
			filled = true;
		return filled;
	}
}
