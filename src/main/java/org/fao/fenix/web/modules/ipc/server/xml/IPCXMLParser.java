package org.fao.fenix.web.modules.ipc.server.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.ipc.common.vo.BulletPointVO;
import org.fao.fenix.web.modules.ipc.common.vo.DropDownVO;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class IPCXMLParser {

	private static final Logger LOGGER = Logger.getLogger(IPCXMLParser.class);
	
//	private String path = "/home/kalimaha/Desktop"; // TODO change this, add the path in the application context
	private String path = Setting.getSystemPath() + "/ipc";
	
	
	public void test() {
		DropDownVO ddvo = findDropDown("IPC.xml", "DD-00", true);
		LOGGER.info("DD-Code: " + ddvo.getCode());
		LOGGER.info("DD-Name: " + ddvo.getName());
		LOGGER.info("DD-Code: " + ddvo.getDropDownCode());
		LOGGER.info("DD-Label: " + ddvo.getDropDownLabel());
		FreeTextVO ftvo = findFreeText("IPC.xml", "FT-00", true);
		LOGGER.info("FT-Code: " + ftvo.getCode());
		LOGGER.info("FT-Name: " + ftvo.getName());
		LOGGER.info("FT-Level-Code: " + ftvo.getLevelCode());
		LOGGER.info("FT-Level-Label: " + ftvo.getLevelLabel());
		LOGGER.info("FT-Range-Code: " + ftvo.getRangeCode());
		LOGGER.info("FT-Rabge-Label: " + ftvo.getRangeLabel());
		LOGGER.info("FT-Value: " + ftvo.getExactValue());
		LOGGER.info("FT-BPs: " + ftvo.getBulletPoints().size());
		for (BulletPointVO bvo : ftvo.getBulletPoints())
			LOGGER.info("\t" + bvo.getDirectEvidence() + " | " + bvo.getText());
	}
	
	/**
	 * Parse the XML to retrieve the drop down with the given code
	 * 
	 * @param source Filename or XML formatted string
	 * @param code Drop down's code (e.g. DD-01)
	 * @param isFromFile true if source is a path, false if it is the XML itself
	 * @return VO representing the drop down
	 */
	public DropDownVO findDropDown(String source, String code, boolean isFromFile) {
		
		// initiate objects
		DropDownVO vo = new DropDownVO();
		Element element = null;
		
		// retrieve XML's root
		if (isFromFile) 
			element = getXmlRootFromFile(source);
		else 
			element = getXmlRootFromString(source);
		
		// parse the XML
		NodeList tag = element.getElementsByTagName("dropDown");
		for (int i = 0; i < tag.getLength(); i++) {
			Element dropDownElement = (Element) tag.item(i);
			if (dropDownElement.getAttribute("code").equals(code)) {
				vo.setCode(code);
				vo.setName(dropDownElement.getAttribute("name"));
				vo.setDropDownCode(dropDownElement.getElementsByTagName("dropDownCode").item(0).getTextContent());
				vo.setDropDownLabel(dropDownElement.getElementsByTagName("dropDownLabel").item(0).getTextContent());
				break;
			}
		}
		
		// return result
		return vo;
	}
	
	/**
	 * Parse the XML to retrieve the drop down with the given code
	 * 
	 * @param source Filename or XML formatted string
	 * @param code free text field's code (e.g. FT-01)
	 * @param isFromFile true if source is a path, false if it is the XML itself
	 * @return VO representing the free text field
	 */
	public FreeTextVO findFreeText(String source, String code, boolean isFromFile) {
		
		// initiate objects
		FreeTextVO vo = new FreeTextVO();
		Element element = null;
		
		// retrieve XML's root
		if (isFromFile) 
			element = getXmlRootFromFile(source);
		else 
			element = getXmlRootFromString(source);
		
		// parse the XML
		NodeList tag = element.getElementsByTagName("freeText");
		for (int i = 0; i < tag.getLength(); i++) {
			Element freeTextElement = (Element) tag.item(i);
			if (freeTextElement.getAttribute("code").equals(code)) {
				vo.setCode(code);
				vo.setName(freeTextElement.getAttribute("name"));
			
				try {
					vo.setRangeCode(freeTextElement.getElementsByTagName("rangeCode").item(0).getTextContent());
				} catch (Exception e) {	}
				try {
					vo.setRangeLabel(freeTextElement.getElementsByTagName("rangeLabel").item(0).getTextContent());
				} catch (Exception e) {	}
				try {
					vo.setExactValue(Double.valueOf(freeTextElement.getElementsByTagName("exactValue").item(0).getTextContent()));
				} catch (Exception e) {	}
				try {				
					vo.setLevelCode(freeTextElement.getElementsByTagName("levelCode").item(0).getTextContent());
				} catch (Exception e) {	}
				try {
					vo.setLevelLabel(freeTextElement.getElementsByTagName("levelLabel").item(0).getTextContent());
				} catch (Exception e) {	}

				List<BulletPointVO> bulletPoints = findBulletPoints(freeTextElement);
				vo.setBulletPoints(bulletPoints);
				return vo;
			}
		}
		
		// return result
		return vo;
	}
	
	/**
	 * Retrieve the <bulletPoint> tags from a <freeText> one
	 * 
	 * @param freeTextElement <freeText> element to parse
	 * @return A collection of VO's representing the bullet points
	 */
	public List<BulletPointVO> findBulletPoints(Element freeTextElement) {
		List<BulletPointVO> list = new ArrayList<BulletPointVO>();
		NodeList rootTag = freeTextElement.getElementsByTagName("bulletPoints");
		for (int i = 0; i < rootTag.getLength(); i++) {
			Element rootElement = (Element) rootTag.item(i);
			NodeList bulletPointsTag = rootElement.getElementsByTagName("bulletPoint");
//			for (int j = 0; j < bulletPointsTag.getLength(); j++) {
				Element bulletPointElement = (Element) bulletPointsTag.item(0);
				BulletPointVO bvo = new BulletPointVO();
				bvo.setDirectEvidence(Boolean.valueOf(bulletPointElement.getElementsByTagName("directEvidence").item(0).getTextContent()));
				bvo.setText(bulletPointElement.getElementsByTagName("text").item(0).getTextContent());
				list.add(bvo);
//			}
		}
		return list;
	}
	
	/**
	 * Return the XML's root element from a file
	 * 
	 * @param filename The XML's filename
	 * @return XML root element
	 */
	private Element getXmlRootFromFile(String filename) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			LOGGER.info("Reading file @ " + (path + File.separator + filename));
			Document document = db.parse(new File(path + File.separator + filename));
			Element element = document.getDocumentElement();
			return element;
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		} catch (SAXException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Return the XML's root element from XML formatted string
	 * 
	 * @param filename The XML string
	 * @return XML root element
	 */
	private Element getXmlRootFromString(String xml) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			Document document = db.parse(stream);
			Element element = document.getDocumentElement();
			return element;
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		} catch (SAXException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
}