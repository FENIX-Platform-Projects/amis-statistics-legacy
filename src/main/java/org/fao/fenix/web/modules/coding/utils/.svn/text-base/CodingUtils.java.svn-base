package org.fao.fenix.web.modules.coding.utils;

import java.util.StringTokenizer;

import org.fao.fenix.core.domain.coding.CodingSystem;



public class CodingUtils {
	

	/**
	 * Tokenizer used to find the selected code system (title, region)
	 *  
	 * @param codeSystem
	 * @return
	 */
	
	public CodingSystem tokenizeStringBox(String codeSystem) {
		System.out.println("TOKENIZING" + codeSystem);
		CodingSystem codingSystem = new CodingSystem();
		StringTokenizer tokenizer = new StringTokenizer(codeSystem, ",");
		if (tokenizer.hasMoreTokens()) {
			codingSystem.setTitle(tokenizer.nextToken().trim());	
		}
		if (tokenizer.hasMoreTokens()) {
			codingSystem.setRegion(tokenizer.nextToken().trim());	
		}
		else {
			codingSystem.setRegion("");
		}
		return codingSystem;
	}

}
