package org.fao.fenix.web.modules.dataviewer.server.faostat.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.NoteVO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FAOSTATNotesXMLParser {

	private static final Logger LOGGER = Logger.getLogger(FAOSTATNotesXMLParser.class);
	
	private String fileName;
	
	private String dirPath;
	
	private String language;
	
	private DOM4JUtils dom4jUtils;
	
	public FAOSTATNotesXMLParser() {
		
	}
	
	/*
	public FAOSTATNotesXMLParser(String dirPath, String filename, String lang) {
		super();
		setDirPath(dirPath);
		setFileName(filename);
		setLanguage(lang);
	}
	*/

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public List<NoteVO> readIndex(String code, String language) {
		List<NoteVO> vos = new ArrayList<NoteVO>();
		try {
			List<String[]> l = dom4jUtils.readIndex(code);
			for (String[] s : l) {
				NoteVO vo = new NoteVO();
				vo.setContent(s[0]);
				vo.setTitle(s[1]);
				vos.add(vo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return vos;
	}
	
	public List<NoteVO> getNotes(String code, String language) {
		List<NoteVO> vos = new ArrayList<NoteVO>();
		try {
			String html = dom4jUtils.readFile("/" + code + ".xml", language);
			NoteVO vo = new NoteVO();
			vo.setContent(html);
			vos.add(vo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return vos;
	}
	
	public String getFilename(String code) {
		if (code.trim().equals("FB")) {
			return code;
		} else if (code.trim().equals("CS")) {
			return code;
		} else {
			return String.valueOf(code.charAt(0));
		}
	}
	
	public NoteVO getNote() {
		NoteVO vo = new NoteVO();
		return vo;
	}

	/**
	public List<NoteVO> getNotes() {

		List<NoteVO> nvos = new ArrayList<NoteVO>();
	
		LOGGER.info("buildNotesVO ..." );
		
		Element element = getRootElementOfConfigFile();
		NodeList tag = element.getChildNodes();
		LOGGER.info("ROOT CHILD NODES = "+ tag.getLength());
		
			for (int i = 0; i < tag.getLength(); i++) {		  
			 LOGGER.info("ROOT CHILD "+ tag.item(i).getNodeName());
			 
			if (tag.item(i) instanceof Element) {
				Element childElement = (Element) tag.item(i);	
				
					if(childElement.getNodeName().equalsIgnoreCase("NOTE")){
						 LOGGER.info("NOTE ADDED TO MAIN LIST");
						nvos.add(buildNoteVO(childElement));
					} else if(childElement.getNodeName().equalsIgnoreCase("NOTEGROUP")){
						NoteVO grpVO = new NoteVO();
						 LOGGER.info("GRP VO CREATED");
						List<NoteVO> grpNotes = new ArrayList<NoteVO>();
						
						NodeList grpChildren = childElement.getChildNodes();	
						LOGGER.info("GRP CHILD NODES = "+ grpChildren.getLength());
						
						for (int j = 0; j < grpChildren.getLength(); j++) {		
							 
							 LOGGER.info(grpChildren.item(j).getNodeName());						 
							 if (grpChildren.item(j) instanceof Element) {
									grpVO.setIsGroupNote(true);	
									 Element grpChildElement = (Element) grpChildren.item(j);		
			 
									if(grpChildElement.getNodeName().equalsIgnoreCase("NOTE")){
										LOGGER.info("GRP NOTE IS ADDED");
										grpNotes.add(buildNoteVO(grpChildElement));	
									}										
									else if(grpChildElement.getNodeName().equalsIgnoreCase("TITLE")){
										LOGGER.info("GRP VO Title IS ADDED");
										grpVO.setTitle(XMLUtils.getTextValue(grpChildElement,getLanguage()));
									}				
							 }	
							 
							 grpVO.setGroupNotes(grpNotes);
						}
						LOGGER.info("GRP NOTE ADDED TO MAIN LIST");
						nvos.add(grpVO);		
					}					
				
			}
		}
		
		//Add General Disclaimer
		nvos.add(getGeneralDisclaimer());
		
		
		LOGGER.info("NOTES ... SIZE "+ nvos.size());
		
		return nvos;
	}
	*/
	
	private NoteVO buildNoteVO(Element element){
		NoteVO nvo = new NoteVO();
		String type = element.getAttribute("type");
		LOGGER.info("type "+type);
		nvo.setType(type);
	
			NodeList children = element.getChildNodes();
			Element child = null;
			for (int i = 0; i < children.getLength(); i++) {		  
				if (children.item(i) instanceof Element) {
					child = (Element)children.item(i);
					if(child.getNodeName().equalsIgnoreCase("TITLE")){
						   nvo.setTitle(XMLUtils.getTextValue(child,getLanguage()));
						   LOGGER.info("title "+XMLUtils.getTextValue(child,getLanguage()));
					} else if(child.getNodeName().equalsIgnoreCase(getLanguage())){
						  LOGGER.info("child.getNodeName() "+child.getNodeName() + " value "+child.getTextContent());
						 nvo.setContent(child.getTextContent());
						 LOGGER.info("content "+child.getTextContent());
					}	
				}
			}
			LOGGER.info("content "+nvo.getContent());
	
	
		return nvo;
		
	}
	public NoteVO getGeneralDisclaimer() {

		NoteVO nvo = new NoteVO();
		LOGGER.info("getGeneralDisclaimer ..." );
		
		Element element = getRootElementOfGeneralDisclaimerFile();
		
		NodeList tag = element.getChildNodes();
		
		Element noteElement = null;
		
		for (int i = 0; i < tag.getLength(); i++) {		  
			 LOGGER.info(tag.item(i).getNodeName());
			if (tag.item(i) instanceof Element) {
					noteElement = (Element) tag.item(i);			
					
					if(noteElement.getTagName().equalsIgnoreCase("TITLE")){
						   nvo.setTitle(XMLUtils.getTextValue(noteElement,getLanguage()));
						   LOGGER.info("title "+XMLUtils.getTextValue(noteElement,getLanguage()));
					}
					else if(noteElement.getTagName().equalsIgnoreCase("NOTE")){
						    nvo.setContent(XMLUtils.getTextValue(noteElement,getLanguage()));
						    LOGGER.info("content "+XMLUtils.getTextValue(noteElement,getLanguage()));
					}
			}
		}
		
		
			
		/**NodeList children = element.getElementsByTagName("DISCLAIMER");
		
		LOGGER.info("DISCLAIMER: children.getLength() "+children.getLength());
		
		for (int j = 0; j < children.getLength(); j++) {	
			Element noteElement = (Element) children.item(j);	
			LOGGER.info("noteElement.getTagName() "+noteElement.getTagName());
			
			if(noteElement.getTagName().equalsIgnoreCase("TITLE")){
			   nvo.setTitle(XMLUtils.getTextValue(noteElement,getLanguage()));
			}
			else if(noteElement.getTagName().equalsIgnoreCase("NOTE")){
			    nvo.setContent(XMLUtils.getTextValue(noteElement,getLanguage()));
		    }
	    }**/
		
		return nvo;
	}

	private Element getRootElementOfConfigFile() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
				
			LOGGER.info("Reading Note file @ " + (Setting.getSystemPath() + File.separator + dirPath + File.separator + fileName + ".xml"));
			Document document = db.parse(new File(Setting.getSystemPath() + File.separator +dirPath +  File.separator + fileName + ".xml"));
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
	
	
	private Element getRootElementOfGeneralDisclaimerFile() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			LOGGER.info("Reading GeneralDisclaimerFile file @ " + (Setting.getSystemPath() +  File.separator +  dirPath + File.separator  + "GeneralDisclaimer.xml"));
			Document document = db.parse(new File(Setting.getSystemPath() +  File.separator +  dirPath + File.separator + "GeneralDisclaimer.xml"));
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
	
	
	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setDom4jUtils(DOM4JUtils dom4jUtils) {
		this.dom4jUtils = dom4jUtils;
	}

}