package org.fao.fenix.web.modules.birt.server.utils.report;

import java.util.ArrayList;
import java.util.List;

public class FindGridReference {

	/*
	 *  @return element:
	 *  #0 Grid
	 *  #1 Row
	 *  #2 Cell
	 */
	public static List<Integer> template1(int objectPosition){
		List<Integer> gridReference = new ArrayList<Integer>();
		switch (objectPosition) {
		case 1:
			gridReference.add(1);
			gridReference.add(0);
			gridReference.add(0);
			break;
		case 2:
			gridReference.add(1);
			gridReference.add(0);
			gridReference.add(1);
			break;	
		case 3:
			gridReference.add(2);
			gridReference.add(0);
			gridReference.add(0);
			break;
		case 4:
			gridReference.add(2);
			gridReference.add(2);
			gridReference.add(0);
			break;
			
		}
		return gridReference;
	}
	
	public static List<Integer> template2(int objectPosition){
		List<Integer> gridReference = new ArrayList<Integer>();
		switch (objectPosition) {
		case 1:
			gridReference.add(1);
			gridReference.add(0);
			gridReference.add(0);
			break;
		case 2:
			gridReference.add(1);
			gridReference.add(0);
			gridReference.add(1);
			break;	
		case 3:
			gridReference.add(2);
			gridReference.add(0);
			gridReference.add(0);
			break;
		case 4:
			gridReference.add(2);
			gridReference.add(0);
			gridReference.add(1);
			break;
		case 5:
			gridReference.add(3);
			gridReference.add(0);
			gridReference.add(0);
			break;
		}
		
		return gridReference;
	}
	
}
