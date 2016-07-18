package org.fao.fenix.web.modules.birt.client.utils;

import java.util.ArrayList;
import java.util.List;

public class DimensionBlackList {
	
	public static List<String> blackDimensionList;
	
	static {
		blackDimensionList = new ArrayList<String>();
		blackDimensionList.add("quantity");
		blackDimensionList.add("numeric");
		blackDimensionList.add("value");
		//blackDimensionList.add("currency");
		//blackDimensionList.add("measurementunit");
	}

}
