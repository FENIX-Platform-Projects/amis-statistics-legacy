package org.fao.fenix.web.modules.venn.common.services;

import java.util.Collections;
import java.util.List;

import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;

public class VennUtils {

	
	public static void calculareIntersections(List<String> dacCodes_A, List<String> dacCodes_B , List<String> dacCodes_C, VennBeanVO vennBean) {
		if ( dacCodes_C != null) {
			
		}
		else
			calculateTwoIntersections(dacCodes_A, dacCodes_B, vennBean);
	}
	
	private static void calculateTwoIntersections(List<String> dacCodes_A, List<String> dacCodes_B , VennBeanVO vennBean) {
		dacCodes_A.add("a");
		dacCodes_A.add("b");
		dacCodes_B.add("c");
		dacCodes_B.add("b");
		System.out.println("Collections.frequency(dacCodes_A, dacCodes_B): " + Collections.frequency(dacCodes_A, dacCodes_B));
	}
}
