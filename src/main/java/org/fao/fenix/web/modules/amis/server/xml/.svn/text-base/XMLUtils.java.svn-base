package org.fao.fenix.web.modules.amis.server.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {

	private final static Logger LOGGER = Logger.getLogger(XMLUtils.class);


	public static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		Element el = getChildElement(ele, tagName);

		if(el!=null)
			textVal = el.getFirstChild().getNodeValue();

		return textVal;
	}


	public static List<String> getList(Element ele, AMISQueryVO qvo) {
		List<String> list = new ArrayList<String>();

		NodeList children = ele.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for (int i = 0; i < count; i++) {
			current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current;
				if (element.hasAttribute("lang")) {
					list.add(element.getFirstChild().getNodeValue()+AMISConstants.defaultLanguage);

				} else if(element.hasAttribute("type") && element.getAttribute("type").equals("aggregation")) {
					list.add(qvo.getAggregationType()+"("+element.getFirstChild().getNodeValue()+")");
				}
				else {
					LOGGER.info(ele + ": " + element.getFirstChild().getNodeValue());
					list.add(element.getFirstChild().getNodeValue());
				}
			}
		}

		LOGGER.info("List size "+ list.size());
		return list;
	}
	
	public static Map<String, String> getMap(Element ele, AMISQueryVO qvo) {
		Map<String, String> map = new LinkedHashMap<String, String>();

		NodeList children = ele.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for (int i = 0; i < count; i++) {
			current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current;				
				if (element.hasAttribute("lang")) {
				//	System.out.println("IN LANG ..");
					map.put(element.getFirstChild().getNodeValue()+AMISConstants.defaultLanguage, element.getFirstChild().getNodeValue()+AMISConstants.defaultLanguage);

				} else if(element.hasAttribute("label")){
					//System.out.println("IN LABEL .. label = " + element.getAttribute("label") + " key: "+element.getFirstChild().getNodeValue());
					map.put(element.getFirstChild().getNodeValue(), element.getAttribute("label"));
				}
				else {
					//System.out.println("IN ELSE .. key: "+element.getFirstChild().getNodeValue());					
					map.put(element.getFirstChild().getNodeValue(), element.getFirstChild().getNodeValue());
				}
			}
		}

		return map;
	}

	/**
	 * Calls getTextValue and returns a int value
	 */

	public static boolean isInteger(String string) {
		try {
			Integer.valueOf(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}



	static Integer getIntValue(Element ele, String tagName) {
		String value = getTextValue(ele,tagName);

		if(isInteger(value))
			return Integer.valueOf(value);
		else
			return null;
	}

	public static boolean isBoolean(String string) {
		try {
			Boolean.valueOf(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	static Boolean getBooleanValue(Element ele, String tagName) {
		String value = getTextValue(ele,tagName);
		LOGGER.info("tagName: " + tagName);
		LOGGER.info("value: " + value);

		if ( value != null) {
			if(isBoolean(value))
				return Boolean.valueOf(value);
		}
		return null;
	}

	public static Element getChildElement(Element parent, String childTagName){

		Element el = null;

		NodeList nl = parent.getElementsByTagName(childTagName);

		if(nl != null && nl.getLength() > 0) {
			el = (Element)nl.item(0);
		}

		return el;

	}


}
