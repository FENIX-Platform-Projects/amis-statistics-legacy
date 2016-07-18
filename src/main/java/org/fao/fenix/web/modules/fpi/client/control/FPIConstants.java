package org.fao.fenix.web.modules.fpi.client.control;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;

public class FPIConstants {

	public static Map<String, String> dayMap = new HashMap<String, String>();
	public static Map<String, String> monthMap = new HashMap<String, String>();
	
	static {
		dayMap.put("0", BabelFish.print().sunday().substring(0, 3));
		dayMap.put("1", BabelFish.print().monday().substring(0, 3));
		dayMap.put("2", BabelFish.print().tuesday().substring(0, 3));
		dayMap.put("3", BabelFish.print().wednesday().substring(0, 3));
		dayMap.put("4", BabelFish.print().thursday().substring(0, 3));
		dayMap.put("5", BabelFish.print().friday().substring(0, 3));
		dayMap.put("6", BabelFish.print().saturday().substring(0, 3));
		monthMap.put("0", BabelFish.print().january().substring(0, 3));
		monthMap.put("1", BabelFish.print().february().substring(0, 3));
		monthMap.put("2", BabelFish.print().march().substring(0, 3));
		monthMap.put("3", BabelFish.print().april().substring(0, 3));
		monthMap.put("4", BabelFish.print().may().substring(0, 3));
		monthMap.put("5", BabelFish.print().june().substring(0, 3));
		monthMap.put("6", BabelFish.print().july().substring(0, 3));
		monthMap.put("7", BabelFish.print().august().substring(0, 3));
		monthMap.put("8", BabelFish.print().september().substring(0, 3));
		monthMap.put("9", BabelFish.print().october().substring(0, 3));
		monthMap.put("10", BabelFish.print().november().substring(0, 3));
		monthMap.put("11", BabelFish.print().december().substring(0, 3));
	}
}
