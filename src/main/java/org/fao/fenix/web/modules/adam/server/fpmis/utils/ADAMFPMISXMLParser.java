package org.fao.fenix.web.modules.adam.server.fpmis.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.fao.fenix.core.jfreechart.JFreeChartConnector;
import org.fao.fenix.core.jfreechart.JFreeChartConstants;
import org.fao.fenix.core.jfreechart.JFreeChartSettings;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class ADAMFPMISXMLParser {
	
	static Logger LOGGER = Logger.getLogger(ADAMFPMISXMLParser.class);
 
	
	/**
	 * this methos get information titles, meaturement units
	 * 
	 *  (i.e.)
	 *	<headers>
			<header>Field Programme Estimates and Delivery by Strategic and Functional Objectives</header>
			<header>Recipient: Bangladesh</header>
			<header>Strategic Objective</header>
			<header>USD Millions</header>
			<header>Field Programme Management Information System (FPMIS) as at 11 Apr 2011</header>
			<header>fpmis.htm?TRX=QuickReportsWrapper&amp;ACTION=EXECREPORT&amp;REPORTNAME=CHART_EB_CONTR_BY_OR_BDGT_DLVRY&amp;FORMAT=HTMLCATEGORYCHART&amp;GRAPHTYPE=verticalbar&amp;SO_CODE=[DATASOURCE_CATEGORY]&amp;OPRT_ORG=&amp;TECH_ORG=&amp;DONOR_CODE=&amp;RECIPIENT_CODE=BD&amp;PORTFOLIO=0&amp;DOPAGINATE=F&amp;DOLIMITATE=F&amp;SHOWFRAME=F&amp;SHOWHTML=F&amp;DRILL_DOWN=T
	 	</header>
	 * 
	 * @param qvo
	 * @param xml
	 */
	public static void getChartInformation(ADAMQueryVO qvo, String xml) {
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xml));

	        Document document = db.parse(is);
			Element element = document.getDocumentElement();
				NodeList headersNode = element.getElementsByTagName(FPMISXMLConstants.headers.toString());			
				for (int j = 0; j < headersNode.getLength(); j++) {
					Element headersElement = (Element) headersNode.item(j);
					NodeList headerNode = headersElement.getElementsByTagName(FPMISXMLConstants.header.toString());
					
					/** this is FIX due to FPMIS structure **/ 
					// index 0 is the title (i.e. Field Programme Estimates and Delivery by Strategic and Functional Objectives)
					
					// index 1 is the subttitle (i.e. Donor: Italy) 
					
					// index 2 is the explantory note della chart (i.e. Strategic Objective)
					
					// index 3 is the measurement unit (i.e. USD Millions)
					qvo.setMeasurementUnit(headerNode.item(3).getTextContent());
					qvo.setyLabel(headerNode.item(3).getTextContent());
					
					// index 4 is the FPMIS Note (i.e. Field Programme Management Information System (FPMIS) as at 11 Apr 2011)	
				}
						
		} catch (Exception e) {
		
		}	
	}
	

	/**
	 * this method get the xml return from FPMIS and parse the values to create the chart
	 * 
	 * @param xml
	 * @return the Map used to jfreechart to build the chart
	 */
	public static LinkedHashMap<String, Map<String, Double>> getChartData(String xml) {
		
		LinkedHashMap<String, Map<String, Double>> values = new LinkedHashMap<String, Map<String,Double>>();
    
		LOGGER.info(xml);
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
			Document document = db.parse(stream);
			Element element = document.getDocumentElement();

				NodeList firstNode = element.getElementsByTagName(FPMISXMLConstants.rows.toString());		
				for (int j = 0; j <= firstNode.getLength(); j++) {
					Element firstElement = (Element) firstNode.item(j);
					NodeList secondNode = firstElement.getElementsByTagName(FPMISXMLConstants.rows.toString());
					
					for (int i = 0; i < secondNode.getLength(); i++) {
						Element rowElement = (Element) secondNode.item(i);
						
						String serie = "";
						String category = "";
						Double value = new Double(0);
						
						
						// getting serie and categories values
						try {
							NodeList serieNode = rowElement.getElementsByTagName(FPMISXMLConstants.SERIE_FIELD.toString());
							serie = serieNode.item(0).getTextContent();
						}
						catch (Exception e) {
						}
						
						try {
							NodeList categoryNode = rowElement.getElementsByTagName(FPMISXMLConstants.CATEGORY_FIELD.toString());
							category = categoryNode.item(0).getTextContent();
						}
						catch (Exception e) {
						}
						

						
						try {
							NodeList valueNode = rowElement.getElementsByTagName(FPMISXMLConstants.VALUE_FIELD.toString());
							value = Double.valueOf(valueNode.item(0).getTextContent());
						}
						catch (Exception e) {
						}
						
						// additional informations in the xml, not useful
//						NodeList urlNode = rowElement.getElementsByTagName("URL_FIELD");
//						NodeList sectionNode = rowElement.getElementsByTagName("SECTION_FIELD");
//						NodeList tooltipNode = rowElement.getElementsByTagName("TOOLTIP_FIELD");

						
						Map<String, Double> categoryMap = new HashMap<String, Double>();						
						if ( values.containsKey(serie) ) {
							categoryMap = values.get(serie);
						}
						categoryMap.put(category, value);
						values.put(serie, categoryMap);
					}

					
				}
						
		} catch (Exception e) {
		
		}
		
		return values;
	}

}
