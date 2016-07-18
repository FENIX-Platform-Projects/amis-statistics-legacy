package org.fao.fenix.web.modules.dataviewer.server.faostat.xml;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {
	
	private final static Logger LOGGER = Logger.getLogger(XMLUtils.class);
	
	static String defaultLanguage = "E";

	
	public static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		Element el = getChildElement(ele, tagName);

		if(el!=null)
			textVal = el.getFirstChild().getNodeValue();

		return textVal;
	}


	public static List<String> getList(Element ele, DWFAOSTATQueryVO qvo, String language) {
		List<String> list = new ArrayList<String>();

		NodeList children = ele.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for (int i = 0; i < count; i++) {
			current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current; 
				String value;
				if (element.hasAttribute("lang")) {
					value = element.getFirstChild().getNodeValue()+language;
					if ( value == null ) {
						value = element.getFirstChild().getNodeValue()+defaultLanguage;
					}
//					list.add(element.getFirstChild().getNodeValue()+language);

				} else if(element.hasAttribute("type") && element.getAttribute("type").equals("aggregation")) {
					value = qvo.getAggregationType()+"("+element.getFirstChild().getNodeValue()+")";
//					list.add(qvo.getAggregationType()+"("+element.getFirstChild().getNodeValue()+")");			    	
				}
				else {
					value = element.getFirstChild().getNodeValue();
//					list.add(element.getFirstChild().getNodeValue());			    	
				}
				list.add(value);
				
				if (element.hasAttribute("alias"+language)){
					String alias = element.getAttribute("alias"+language);
			
					if ( alias == null ) {
						alias = element.getFirstChild().getNodeValue()+defaultLanguage;
					}
					
//					LOGGER.info("ALIAS: " + alias);
					qvo.getSelectsAlias().put(value, alias);
					
//					LOGGER.info("qvo.getSelectsAlias(): " + qvo.getSelectsAlias());
				}
				
				
			}
		}

		return list;
	}


	public static Map<String, String> getMap(Element ele, String language) {
		Map<String, String> map = new LinkedHashMap<String, String>();

		NodeList children = ele.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for (int i = 0; i < count; i++) {
			current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current; 
				if (element.hasAttribute("lang")) {
					
					String value = element.getFirstChild().getNodeValue()+language;
					if ( value == null ) {
						value = element.getFirstChild().getNodeValue()+defaultLanguage;
					}
					
					map.put(value, value);

				} 
				else {
					map.put(element.getFirstChild().getNodeValue(), element.getFirstChild().getNodeValue());			    	
				}
			}
		}

		return map;
	}
	
	public static Map<String, String> getTagValueMap(Element ele, String language) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		NodeList children = ele.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for (int i = 0; i < count; i++) {
			current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current; 
				map.put(current.getNodeName(), element.getFirstChild().getNodeValue());
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
//		LOGGER.info("tagName: |" + tagName + "|");
//		LOGGER.info("value: " + value);

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
