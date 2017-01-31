package org.fao.fenix.web.modules.dataviewer.server.faostat.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATAggregationConstant;
import org.fao.fenix.web.modules.dataviewer.common.enums.FAOSTATTableConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.CalculationParametersVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATJoinQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeFilter;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsInfoVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.ValueCondition;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FAOSTATXMLParser {

	private static final Logger LOGGER = Logger.getLogger(FAOSTATXMLParser.class);
	private String fileName;
	private String dirPath;
	private String language;
	
	private static final String DEFAULT_RESOURCES = "DEFAULT";
	private static final String OTHER_RESOURCES = "OTHER" ;
	
	private static String defaultLanguage = "E";
	

	private FAOSTATVisualizeSettingsVO settings;
	
	
	public FAOSTATXMLParser(String dirPath, String filename, String lang) {
		setDirPath(dirPath);
		setFileName(filename);
		setLanguage(lang);
		buildFAOSTATQueryVOsMap();
	}
	
	public FAOSTATVisualizeSettingsVO getAllFAOSTATQueryVOs() {
		 return getSettings();			
	}

	public List<DWFAOSTATQueryVO> getDefaultFAOSTATQueryVOs() {
		 if(!getSettings().getQvos().isEmpty() && getSettings().getQvos().get(DEFAULT_RESOURCES)!=null){
		   return getSettings().getQvos().get(DEFAULT_RESOURCES);	 
		 } else 
			 return null;
	}
	
	public List<DWFAOSTATQueryVO> getOtherFAOSTATQueryVOs() {
		if(!getSettings().getQvos().isEmpty() && getSettings().getQvos().get(OTHER_RESOURCES)!=null){
		   return getSettings().getQvos().get(OTHER_RESOURCES);	 
		 } else
			 return null;
	}

	private void  buildFAOSTATQueryVOsMap() {
		
		// settings
		FAOSTATVisualizeSettingsVO settings = new FAOSTATVisualizeSettingsVO();
		
		// settings of the view (i.e. viewTitle)
		buildConfigurationSettings(settings);
		
		
		// questions TODO: make another service or add a check to retrieve questions (?)
		settings.setQuestions(buildQuestionsVO());
		
		// filters 
		settings.setFilters(buildfiltersVO());
		
		
		// query VOs 
		 List<DWFAOSTATQueryVO> defaultvos = buildDefaultResourcesVO();
		 
		 /** TODO: so far they are not used **/
		 List<DWFAOSTATQueryVO> othervos = buildOtherResourcesVO();
		 
         HashMap<String, List<DWFAOSTATQueryVO>> qvos = new LinkedHashMap<String, List<DWFAOSTATQueryVO>>();
         
         if(!defaultvos.isEmpty()) {
        	 qvos.put(DEFAULT_RESOURCES, defaultvos);
        	 
         }	 
         if(!othervos.isEmpty()) {
        	 qvos.put(OTHER_RESOURCES, othervos);
         }	 
      
         settings.setQvos(qvos);
               
         setSettings(settings);          
	}
	
	
	private List<FAOSTATVisualizeQuestionsVO> buildQuestionsVO() {
		List<FAOSTATVisualizeQuestionsVO> questions = new ArrayList<FAOSTATVisualizeQuestionsVO>();
		try {
			Element element = getRootElementOfConfigFile();
			NodeList tag = element.getElementsByTagName("QUESTIONS");
			for (int i = 0; i < tag.getLength(); i++) {	
				Element filtersElement = (Element) tag.item(i);
				tag = filtersElement.getElementsByTagName("QUESTION");
				for (int j = 0; j < tag.getLength(); j++) {	

					FAOSTATVisualizeQuestionsVO question = new FAOSTATVisualizeQuestionsVO();
					Element filterElement = (Element) tag.item(j);

					String title = XMLUtils.getTextValue(XMLUtils.getChildElement(filterElement, "TITLE"), getLanguage());
					if ( title == null ) {
						title = XMLUtils.getTextValue(XMLUtils.getChildElement(filterElement, "TITLE"), defaultLanguage);
					}
					question.setTitle(title);

					try { 
						String subtitle = XMLUtils.getTextValue(XMLUtils.getChildElement(filterElement, "SUBTITLE"), getLanguage()); 
						if ( subtitle == null )
							subtitle = XMLUtils.getTextValue(XMLUtils.getChildElement(filterElement, "SUBTITLE"), defaultLanguage);
						question.setSubtitle(subtitle);
					} catch (Exception e) {
					}
					
					try { 
						String description = XMLUtils.getTextValue(XMLUtils.getChildElement(filterElement, "DESCRIPTION"), getLanguage()); 
						if ( description == null )
							description = XMLUtils.getTextValue(XMLUtils.getChildElement(filterElement, "DESCRIPTION"), defaultLanguage);
						question.setDescription(description);
					} catch (Exception e) {
					}
					
					question.setFilename(XMLUtils.getTextValue(filterElement, "FILENAME")); 

					// this is used for multiple information related to a topic or top20
					// i.e. category and all the subcategories
					buildQuestionInfosVO(question, filterElement);
					questions.add(question);
				}
			}
		}
		catch (Exception e) {
		}
		return questions;
	}
	
	
	private void buildQuestionInfosVO(FAOSTATVisualizeQuestionsVO question, Element element) { 
		// this is used for multiple information related to a topic or top20
		// i.e. category and all the subcategories
		try {
			NodeList tag = element.getElementsByTagName("INFOS");
			for (int i = 0; i < tag.getLength(); i++) {	
				Element infosElement = (Element) tag.item(i);
				tag = infosElement.getElementsByTagName("INFO");
				for (int j = 0; j < tag.getLength(); j++) {	
					FAOSTATVisualizeQuestionsInfoVO infoVO = new FAOSTATVisualizeQuestionsInfoVO();
					Element infoElement = (Element) tag.item(j);

					String title = XMLUtils.getTextValue(XMLUtils.getChildElement(infoElement, "TITLE"), getLanguage()); 
					if ( title == null ) {
						title = XMLUtils.getTextValue(XMLUtils.getChildElement(infoElement, "TITLE"), defaultLanguage);
					}
					infoVO.setTitle(title);
					try { 
						String subtitle = XMLUtils.getTextValue(XMLUtils.getChildElement(infoElement, "SUBTITLE"), getLanguage()); 
						if ( subtitle == null )
							subtitle = XMLUtils.getTextValue(XMLUtils.getChildElement(infoElement, "SUBTITLE"), defaultLanguage);
						infoVO.setSubtitle(subtitle);
					} catch (Exception e) {
					}
					
					try { 
						String description = XMLUtils.getTextValue(XMLUtils.getChildElement(infoElement, "DESCRIPTION"), getLanguage()); 
						if ( description == null )
							description = XMLUtils.getTextValue(XMLUtils.getChildElement(infoElement, "DESCRIPTION"), defaultLanguage);
						
						infoVO.setDescription(description);
					} catch (Exception e) {
					}
					
					infoVO.setFilename(XMLUtils.getTextValue(infoElement, "FILENAME")); 
					question.getInfoVO().put(infoVO.getFilename(), infoVO);
				}
			}
		}catch (Exception e) {
//			LOGGER.error("no infos found");
		}
	}
	
	
	/**
	 * 
	 * TODO: This method fills all the parameters of the view (title, layoutType...)
	 * 
	 * @param settings
	 */
	private void buildConfigurationSettings(FAOSTATVisualizeSettingsVO settings) {
		try {
			Element element = getRootElementOfConfigFile();
			String title = XMLUtils.getTextValue(XMLUtils.getChildElement(element,"TITLE"), getLanguage()); 
			if ( title == null )
				title = XMLUtils.getTextValue(XMLUtils.getChildElement(element,"TITLE"), defaultLanguage);
			settings.setViewTitle(title);
		}catch (Exception e) {
//			LOGGER.info("ERRORE VIEW TITLE: ");
		}
	}
	
	private List<FAOSTATVisualizeFilter> buildfiltersVO() {
		List<FAOSTATVisualizeFilter> filters = new ArrayList<FAOSTATVisualizeFilter>();
		
		// TODO: at this level the try/catch?
		try {
			Element element = getRootElementOfConfigFile();
			NodeList tag = element.getElementsByTagName("FILTERS");
			for (int i = 0; i < tag.getLength(); i++) {	
				Element filtersElement = (Element) tag.item(i);
				tag = filtersElement.getElementsByTagName("FILTER");
				for (int j = 0; j < tag.getLength(); j++) {	
					FAOSTATVisualizeFilter filter = new FAOSTATVisualizeFilter();
					// FILTER TYPE and MULTISELECTION
					Element filterElement = (Element) tag.item(j);
					// check if exist the useCodingSystem attribute
					String useCodingSystem = filterElement.getAttribute("useCodingSystem");
					if ( useCodingSystem != null && !useCodingSystem.equalsIgnoreCase("")) {
						filter.setUseCodingSystem( Boolean.valueOf(useCodingSystem));
					}

					String width = filterElement.getAttribute("width");				
					if ( width != null ) {
						filter.setWidth(width);
					}
					
					String height = filterElement.getAttribute("height");	
					if ( height != null ) {
						filter.setHeight(height);
					}
				
					Element filterTypeElement = XMLUtils.getChildElement(filterElement,"FILTERTYPE");
					
					String filterType = filterTypeElement.getTextContent();
					filter.setFilterType(filterType);
					String multiselection = filterTypeElement.getAttribute("multiselection");					
					if ( multiselection != null ) {
						Boolean isMultiSelection = Boolean.valueOf(multiselection);
						filter.setIsMultiSelection(isMultiSelection);
					}
					
					String disaggregate = filterTypeElement.getAttribute("disaggregate");					
					if ( disaggregate != null ) {
						Boolean isDisaggregate = Boolean.valueOf(disaggregate);
						filter.setDisaggregated(isDisaggregate);
					}
					
					try {
						Element levelElement = XMLUtils.getChildElement(filterElement,"LEVEL");
						String level = levelElement.getTextContent();
						filter.setLevel(level);
					}	
					catch (Exception e) {
					}
					
					try {
						Element domainsElement = XMLUtils.getChildElement(filterElement,"DOMAINS");
						LOGGER.info("domainsElement: " + domainsElement);
						filter.setDomains(XMLUtils.getMap(domainsElement, getLanguage()));
					}	
					catch (Exception e) {
						LOGGER.info("Exception: " + e.getMessage());
					}

					LOGGER.info("FILTER: " + filterType + " | " + filter.getDomains());
					
					try {
						NodeList ddCodesList = filterElement.getElementsByTagName("DROPDOWNCODES");
						List<DWCodesModelData> ddCodes = new ArrayList<DWCodesModelData>();
						
						for (int z = 0; z < ddCodesList.getLength(); z++) {	
							
							Element ddCodesElement = (Element) ddCodesList.item(z);
							
							NodeList codeList = ddCodesElement.getElementsByTagName("CODE");
							for (int k = 0; k < codeList.getLength(); k++) {	
								Element codeElement = (Element) codeList.item(k);
								
								// TODO: for everything is setted with codes...should be called the DB for the label? 
								String label = codeElement.getTextContent();
								// check if exist the useCodingSystem attribute
								String attributeLabel = codeElement.getAttribute("label" + getLanguage() );
								
								// attribute label (with language)
								if ( attributeLabel == null ){
									attributeLabel = codeElement.getAttribute("label" + defaultLanguage );
								} 

								if ( attributeLabel != null && !label.equalsIgnoreCase("")) {
									label = attributeLabel;
								}
								
								// attribute label (wihtout language)
								String attributeWithouthLang = codeElement.getAttribute("label");
								if ( attributeWithouthLang != null ) {
									label = attributeWithouthLang;
								}

								String attributeDomain = codeElement.getAttribute("domain");
								DWCodesModelData c = new DWCodesModelData(codeElement.getTextContent(), label, attributeDomain);
								ddCodes.add(c);
							}
							
						}
						
						filter.setCodes(ddCodes);
						
					}catch (Exception e) {}
					// this are the Default codes of the filter
					try {
						NodeList defaultCodesList = filterElement.getElementsByTagName("DEFAULTCODES");
						List<DWCodesModelData> defaultCodes = new ArrayList<DWCodesModelData>();
						for (int z = 0; z < defaultCodesList.getLength(); z++) {	
							Element defualtCodesElement = (Element) defaultCodesList.item(z);
							NodeList codeList = defualtCodesElement.getElementsByTagName("CODE");
							for (int k = 0; k < codeList.getLength(); k++) {	
								Element codeElement = (Element) codeList.item(k);
								// TODO: for everything is setted with codes...should be called the DB for the label? 
								String label =  codeElement.getTextContent();
								// check if exist the useCodingSystem attribute
								String attribute = codeElement.getAttribute("label");
								if ( attribute != null && !label.equalsIgnoreCase("")) {
									label = attribute;
								}	
								String attributeDomain = codeElement.getAttribute("domain");
								defaultCodes.add(new DWCodesModelData(codeElement.getTextContent(), label, attributeDomain));
							}
						}
						filter.setDefaultCodes(defaultCodes);
					}
					catch (Exception e) {
					}
					filters.add(filter);
				}
			}
		}catch (Exception e) {
		}	
		return filters;
	}
	
	private List<DWFAOSTATQueryVO> buildDefaultResourcesVO() {
		
		List<DWFAOSTATQueryVO> qvos = new ArrayList<DWFAOSTATQueryVO>();

		Element element = getRootElementOfConfigFile();
		NodeList tag = element.getElementsByTagName("DEFAULT");
		for (int i = 0; i < tag.getLength(); i++) {
			Element defaultElement = (Element) tag.item(i);
			tag = defaultElement.getElementsByTagName("RESOURCE");
			for (int j = 0; j < tag.getLength(); j++) {	
				Element resourceElement = (Element) tag.item(j);

				DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();

				qvo.setOutput(XMLUtils.getTextValue(resourceElement,"RESOURCETYPE"));
				qvo.setTypeOfOutput(XMLUtils.getTextValue(resourceElement,"OUTPUTTYPE"));
				
				try {
					qvo.setUseCustomColors(XMLUtils.getBooleanValue(resourceElement,"USECUSTOMCOLORS"));
				} catch (Exception e1) {
					
				}
				
				try {
					qvo.setUseAbsoluteValues(XMLUtils.getBooleanValue(resourceElement,"USEABSOLUTEVALUES"));
				} catch (Exception e1) {
					
				}

				String title = XMLUtils.getTextValue(XMLUtils.getChildElement(resourceElement,"TITLE"), getLanguage()); 
				if ( title == null )
					title = XMLUtils.getTextValue(XMLUtils.getChildElement(resourceElement,"TITLE"), defaultLanguage);
				qvo.setTitle(title);
				
				// footnote
				try {
					String footnote = XMLUtils.getTextValue(XMLUtils.getChildElement(resourceElement, "FOOTNOTE"), getLanguage());
					if ( footnote == null ) {
						footnote = XMLUtils.getTextValue(XMLUtils.getChildElement(resourceElement, "FOOTNOTE"), defaultLanguage);
					}
					qvo.setFootnote(footnote);
				}catch (Exception e) {}
				
				try {
					String aggregationType = XMLUtils.getTextValue(resourceElement,"AGGREGATIONTYPE");
//					LOGGER.info("aggregationType: " + aggregationType);
					if ( aggregationType != null ) {
						if ( aggregationType.equalsIgnoreCase("FALSE")) {
							qvo.setAggregationType(null);
						}
						else {
							qvo.setAggregationType(aggregationType.toUpperCase());
						}
					}
				}
				catch (Exception e) {
				}
	
				try {
					qvo.setPalette(XMLUtils.getTextValue(resourceElement,"PALETTE"));
				}catch (Exception e) {
				}
				
				// Resource Size (this is not mandatory)
				try {
					Element sizeElement = XMLUtils.getChildElement(resourceElement,"SIZE");
					qvo.setHeight(XMLUtils.getTextValue(sizeElement,"HEIGHT"));
					qvo.setWidth(XMLUtils.getTextValue(sizeElement,"WIDTH"));
				} catch (Exception e) {
				}
				
				// Pivot Parameters
				try { 
					Element pivotDimensions = XMLUtils.getChildElement(resourceElement,"PIVOT_PARAMETERS");
					if ( pivotDimensions != null ) {
						setPivotDimensions(qvo, pivotDimensions);
					}
				} catch (Exception e) {
				}
		
				
				// Map Options (this is not mandatory)
				try {
					Element mapOptions = XMLUtils.getChildElement(resourceElement,"MAPOPTIONS");
					
					NodeList children = mapOptions.getChildNodes();
					Node current = null;
					int count = children.getLength();
					for (int k = 0; k < count; k++) {
						current = children.item(k);
						if (current.getNodeType() == Node.ELEMENT_NODE) {
							Element e = (Element) current; 				
							String value = e.getTextContent();
							String key = e.getAttribute("type");
							qvo.getMapsVO().getValues().put(key, value);
						}
					}
					LOGGER.info(qvo.getMapsVO().getValues());
				} catch (Exception e) {
				}
				
				
				
				
				setQueryParameters(qvo, resourceElement);
				setApplicableFilters(qvo, resourceElement);

				
				qvos.add(qvo);
			}
		}		
		return qvos;
	}
	
	private void setPivotDimensions(DWFAOSTATQueryVO qvo, Element pivotElement) {
//		LOGGER.info("SETTING PIVOT TABLE DIMENSIONS");
		try {
			String nestedby = XMLUtils.getTextValue(pivotElement, "NESTED_BY");
			qvo.setNestedby(nestedby);
			
			String xAxis = XMLUtils.getTextValue(pivotElement, "X");
			qvo.setxAxis(xAxis);
			
			String y1Axis = XMLUtils.getTextValue(pivotElement, "Y1");
			qvo.setY1Axis(y1Axis);
			
			String y2Axis = XMLUtils.getTextValue(pivotElement, "Y2");
			qvo.setY2Axis(y2Axis);
		}
		catch (Exception e) {
//			LOGGER.error("Error setting the PIVOT TABLE parameters");
		}
	}

	/** TODO: make the same as buildDefaultResourcesVO **/
	private List<DWFAOSTATQueryVO> buildOtherResourcesVO() {

		List<DWFAOSTATQueryVO> qvos = new ArrayList<DWFAOSTATQueryVO>();

//		LOGGER.info("OTHER RESOURCES ..." );
		
		Element element = getRootElementOfConfigFile();
		
		NodeList children = element.getElementsByTagName("RESOURCES");
		
		for (int j = 0; j < children.getLength(); j++) {	
			Element resourcesElement = (Element) children.item(j);
		
			if(resourcesElement.getTagName().equalsIgnoreCase("RESOURCE")){
		
					DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();


					qvo.setOutput(XMLUtils.getTextValue(resourcesElement,"RESOURCETYPE"));
					qvo.setTypeOfOutput(XMLUtils.getTextValue(resourcesElement,"OUTPUTTYPE"));
					
					String title = XMLUtils.getTextValue(XMLUtils.getChildElement(resourcesElement,"TITLE"), getLanguage()); 
					if ( title == null )
						title = XMLUtils.getTextValue(XMLUtils.getChildElement(resourcesElement,"TITLE"), defaultLanguage);
					qvo.setTitle(title);

//					qvo.setTitle(XMLUtils.getTextValue(XMLUtils.getChildElement(resourcesElement,"TITLE"),getLanguage())); 

					try { 
						Element pivotDimensions = XMLUtils.getChildElement(resourcesElement,"PIVOT_PARAMETERS");
						if ( pivotDimensions != null ) {
							setPivotDimensions(qvo, pivotDimensions);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					setQueryParameters(qvo, resourcesElement);

					qvos.add(qvo);
				}
			}
		
//		LOGGER.info("OTHER RESOURCES ... SIZE "+ qvos.size());
		
		return qvos;
	}

	private void setQueryParameters(DWFAOSTATQueryVO qvo, Element resourceElement) {
		//SET DATE QUERY PARAMS
		Element dateQueryElement = XMLUtils.getChildElement(resourceElement,"DATEQUERY");
		if (dateQueryElement!=null && dateQueryElement.hasChildNodes()) {
			qvo.setRunMaxDateQuery(true);	
			qvo.setTimeSpan(XMLUtils.getIntValue(dateQueryElement, "TIMESPAN")); 
			
			/** quick fix for the DEMO **/
			String maxDateLimit = XMLUtils.getTextValue(dateQueryElement,"MAXDATELIMIT");			
			if ( maxDateLimit != null) {
				qvo.setRunMaxDateQuery(false);	
				qvo.setMaxDateLimit(maxDateLimit);			
			}
		}

		//SET CALCULATION QUERY PARAMS
		Element calcQueryElement = XMLUtils.getChildElement(resourceElement,"CALCULATIONQUERY");
		if (calcQueryElement!=null && calcQueryElement.hasChildNodes()) {
			qvo.setRunCalculationQuery(true);
			CalculationParametersVO  calcParameters = new CalculationParametersVO();
			FAOSTATAggregationConstant calculationType = FAOSTATAggregationConstant.valueOf(XMLUtils.getTextValue(calcQueryElement,"TYPE"));
			try {
				calcParameters.setCalculationType(calculationType);
				calcParameters.setResultLabel(XMLUtils.getTextValue(calcQueryElement,"RESULTLABEL"));
			}catch (Exception e) {}
			try {
				Element calcParamsElement = XMLUtils.getChildElement(calcQueryElement,"PARAMETERS");
				try {
					calcParameters.setFilterColumn(FAOSTATTableConstant.valueOf(XMLUtils.getTextValue(calcParamsElement,"FILTERCOLUMN")));
				}catch (Exception e) {
				}
				if(calculationType.equals(FAOSTATAggregationConstant.SUBTRACT)){
					Element minuendElement = XMLUtils.getChildElement(calcParamsElement,"MINUEND");
					calcParameters.setSubtractionMinuend(XMLUtils.getList(minuendElement, qvo, getLanguage()));
	
					Element subtrahendElement = XMLUtils.getChildElement(calcParamsElement,"SUBTRAHEND");
					calcParameters.setSubtractionSubtrahend(XMLUtils.getList(subtrahendElement, qvo, getLanguage()));
				}
				if(calculationType.equals(FAOSTATAggregationConstant.GROWTH_RATE)){
					parseGrowthRate(qvo, calcParameters, calcParamsElement);
				}
			}catch (Exception e) {
			}
			qvo.setCalculationParametersVO(calcParameters);
		}
		
		//Check if it's a JOINQUERY
		Element joinQueryElement = XMLUtils.getChildElement(resourceElement,"JOINQUERY");

		if (joinQueryElement!=null && joinQueryElement.hasChildNodes()) {
			qvo.setRunJoinQuery(true);
			joinQueries(qvo, joinQueryElement);
		}
		else {
			// it's a just one query
			Element baseQueryElement = XMLUtils.getChildElement(resourceElement,"BASEQUERY");
			setBaseQuery(qvo, baseQueryElement);
		}
	}
	
	private void joinQueries(DWFAOSTATQueryVO qvo, Element ele) {
		
		FAOSTATJoinQueryVO joinQueryVO = new FAOSTATJoinQueryVO();
		List<DWFAOSTATQueryVO> qvos = new ArrayList<DWFAOSTATQueryVO>();
		

		try {
			qvo.setJoinColumn(XMLUtils.getTextValue(ele,"JOINCOLUMN"));
		}catch (Exception e) {
//			LOGGER.error("no join column setted");
		}
		
		// JOIN QUERY PARAMETERS
		setBaseQuery(qvo, ele);

//		LOGGER.info("SELECTS:" + qvo.getSelects());

		Element baseQueries = XMLUtils.getChildElement(ele,"BASEQUERIES");
		
		NodeList children = baseQueries.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for (int i = 0; i < count; i++) {
			current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current; 				
				DWFAOSTATQueryVO queryVO = new DWFAOSTATQueryVO();
				
				setBaseQuery(queryVO, element);
				
				qvos.add(queryVO);
			}
		}
		
		joinQueryVO.setQvos(qvos);
		qvo.setJoinQueryVO(joinQueryVO);

	}
		
	private void setBaseQuery(DWFAOSTATQueryVO qvo, Element baseQueryElement) {
		//SET BASE QUERY PARAMS 
		
		// Options
		try {
			Boolean showNull = XMLUtils.getBooleanValue(baseQueryElement,"SHOWNULL");
			if ( showNull != null ) {
				qvo.setShowNull(showNull);
			}
		}
		catch (Exception e) {
		}
		
		/** TODO: dop the same for nested limit **/
		qvo.setLimit(XMLUtils.getIntValue(baseQueryElement, "LIMIT"));
		
		// NESTED LIMIT
		try {
			Element nestedLimit = XMLUtils.getChildElement(baseQueryElement,"NESTEDLIMIT");
			String limit = nestedLimit.getTextContent();
			qvo.setNestedLimit(Integer.valueOf(limit));
			String nestedLimitField = nestedLimit.getAttribute("field");
			qvo.setNestedLimitField(nestedLimitField);

			try {
				Element sortElement = XMLUtils.getChildElement(baseQueryElement,"NESTEDSORT");
	
				if (sortElement!=null && sortElement.hasAttribute("type") && sortElement.getAttribute("type")!=null) {
					qvo.setSortingOrderNested(sortElement.getAttribute("type"));
				}
				// ORDER BY
				try {
					qvo.setOrderBysNested(XMLUtils.getList(sortElement, qvo, getLanguage()));
				}
				catch (Exception e) {
				}
			}catch (Exception e) {
			}
		}catch (Exception e) {
		}

		Element selectsElement = XMLUtils.getChildElement(baseQueryElement,"SELECTS");

		qvo.setSelects(XMLUtils.getList(selectsElement, qvo, getLanguage()));

		Element sortElement = XMLUtils.getChildElement(baseQueryElement,"SORT");

		if (sortElement!=null && sortElement.hasAttribute("type") && sortElement.getAttribute("type")!=null) {
			qvo.setSortingOrder(sortElement.getAttribute("type"));
		}

		// ORDER BY
		try {
			qvo.setOrderBys(XMLUtils.getList(sortElement, qvo, getLanguage()));
		}
		catch (Exception e) {
		}
		
		// REGIONAL LEVEL QUERY
		try {
			// TODO: change it, if is regional 
			Boolean value = XMLUtils.getBooleanValue(baseQueryElement, "REGIONAL_LEVEL");
			if ( value != null) {
				qvo.setIsRegionLevel(value);
			}
		}
		catch (Exception e) {
		}
		
		// COUNTRY LEVEL QUERY
		try {
			// TODO: change it, if is regional 
			Boolean value = XMLUtils.getBooleanValue(baseQueryElement, "COUNTRY_LEVEL");
			if ( value != null) {
				qvo.setIsCountryLevel(value);
			}
		}
		catch (Exception e) {
		}
		
		//SUMOTHERS
		try {
			// TODO: change it, if is regional 
			
			Integer value = XMLUtils.getIntValue(baseQueryElement, "SUMOTHERS");
			if ( value != null) {
				qvo.setSumOthers(true);
				qvo.setOthersLimit(value);
			}
		}
		catch (Exception e) {
		}


		Element groupBysElement = XMLUtils.getChildElement(baseQueryElement,"GROUPBYS");
		try {
			qvo.setGroupBys(XMLUtils.getMap(groupBysElement, getLanguage()));
		}	
		catch (Exception e) {
		}
		

		Element whereElement = XMLUtils.getChildElement(baseQueryElement,"WHERE");
		try {
			Element domainsElement = XMLUtils.getChildElement(whereElement,"DOMAINS");
			qvo.setDomains(XMLUtils.getMap(domainsElement, getLanguage()));
		}	
		catch (Exception e) {
		}
		
		
		try {
			Element itemsElement = XMLUtils.getChildElement(whereElement,"ITEMS");
			qvo.setItems(XMLUtils.getMap(itemsElement, getLanguage()));
		}	
		catch (Exception e) {
		}
		
		try {
			Element itemsLevelElement = XMLUtils.getChildElement(whereElement,"ITEMSLEVEL");
			qvo.setItemsLevel(XMLUtils.getMap(itemsLevelElement, getLanguage()));
		}	
		catch (Exception e) {
		}

		try {
			Element elementsElement = XMLUtils.getChildElement(whereElement,"ELEMENTS");
			qvo.setElements(XMLUtils.getMap(elementsElement, getLanguage()));
		}	
		catch (Exception e) {
		}
		
		try {
			Element elementsListElement = XMLUtils.getChildElement(whereElement,"ELEMENTSLIST");
			qvo.setElementsList(XMLUtils.getMap(elementsListElement, getLanguage()));
		}	
		catch (Exception e) {
		}
		
		try {
			Element elementsElement = XMLUtils.getChildElement(whereElement,"AREAS");
			qvo.setAreas(XMLUtils.getMap(elementsElement, getLanguage()));
		}	
		catch (Exception e) {
		}
		
		try {
			Element elementsElement = XMLUtils.getChildElement(whereElement,"AREASBLACKLIST");
			qvo.setAreasBlacklisted(XMLUtils.getMap(elementsElement, getLanguage()));
			
		}	
		catch (Exception e) {
		}
		
		
		
		try {
			Element yearsElemet = XMLUtils.getChildElement(whereElement,"YEARS");
			qvo.setYears(XMLUtils.getMap(yearsElemet, getLanguage()));
			
		}	
		catch (Exception e) {
		}
		
		/**
		 * <VALUE>
		 *    <GREATERTHAN>0</GREATERTHAN>
		 *    <GREATERTHANOREQUALSTO>0</GREATERTHANOREQUALSTO>
		 *    <LESSTHAN>0</LESSTHAN>
		 *    <LESSTHANOREQUALSTO>0</LESSTHANOREQUALSTO>
		 *    <EQUALSTO>0</EQUALSTO>
		 * </VALUE>
		 */
		try {
			Element valueElemet = XMLUtils.getChildElement(whereElement, "VALUE");
			Map<String, String> m = XMLUtils.getTagValueMap(valueElemet, getLanguage());
			for (String key : m.keySet()) {
				ValueCondition vc = ValueCondition.valueOf(key.toUpperCase());
				Double treshold = Double.valueOf(m.get(key));
				qvo.setValueCondition(vc);
				qvo.setValueTreshold(treshold);
			}
		} catch (Exception e) {
			
		}

	}
	
	
	private void setApplicableFilters(DWFAOSTATQueryVO qvo, Element resourceElement) {

		//SET APPLICABLE FILTERS  
		Element applicableFiltersElement = XMLUtils.getChildElement(resourceElement,"APPLICABLE_FILTERS");
		
		
		if ( applicableFiltersElement != null ) {

			//SET APPLY_ALL_FILTERS_EXCEPT_YEARS PARAM
			Boolean exceptYears = XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ALL_FILTERS_EXCEPT_YEARS");
//			LOGGER.info("exceptYears: " + exceptYears);
			if ( exceptYears != null ) {
				qvo.setApplyAllFiltersExceptYears(exceptYears);
			}
			
			//SET APPLY_ONLY_YEAR_FILTER
			Boolean onlyYears = XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ONLY_YEAR_FILTER");
//			LOGGER.info("ONLYYears: " + onlyYears);
			if ( onlyYears != null ) {
				qvo.setApplyOnlyYearFilter(onlyYears);
			}
			
			//SET APPLY_ALL_FILTERS_EXCEPT_AGGREGATION_TYPE
			Boolean exceptAggregationType= XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ALL_FILTERS_EXCEPT_AGGREGATION_TYPE");
//			LOGGER.info("exceptAggregationType: " + exceptAggregationType);
			if ( exceptAggregationType != null ) {
				qvo.setApplyAllFiltersExceptAggregrationType(exceptAggregationType);
			}
			
			//SET APPLY_ALL_FILTERS_EXCEPT_AREAS
			Boolean applyAllFiltersExceptAreas = XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ALL_FILTERS_EXCEPT_AREAS");
//			LOGGER.info("applyAllFiltersExceptAreas: " + applyAllFiltersExceptAreas);
			if ( applyAllFiltersExceptAreas != null ) {
				qvo.setApplyAllFiltersExceptAreas(applyAllFiltersExceptAreas);
			}
			
			//SET APPLY_ALL_FILTERS_EXCEPT_ITEMS
			Boolean applyAllFiltersExceptItems = XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ALL_FILTERS_EXCEPT_ITEMS");
//			LOGGER.info("APPLY_ALL_FILTERS_EXCEPT_ITEMS: " + applyAllFiltersExceptItems);
			if ( applyAllFiltersExceptItems != null ) {
				qvo.setApplyAllFiltersExceptItems(applyAllFiltersExceptItems);
			}

			//SET APPLY_ONLY_AREAS
			Boolean applyOnlyAreas = XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ONLY_AREAS");
//			LOGGER.info("APPLY_ONLY_AREAS: " + applyOnlyAreas);
			if ( applyOnlyAreas != null ) {
				qvo.setApplyOnlyAreasFilter(applyOnlyAreas);
			}
			
			//SET APPLY_ONLY_ITEMS
			Boolean applyOnlyItems = XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ONLY_ITEMS_FILTER");
//			LOGGER.info("APPLY_ONLY_ITEMS_FILTER: " + applyOnlyItems);
			if ( applyOnlyItems != null ) {
				qvo.setApplyOnlyItemsFilter(applyOnlyItems);
			}

			
			
			Boolean notApplyFilters = XMLUtils.getBooleanValue(applicableFiltersElement, "NOT_APPLY_FILTERS");
//			LOGGER.info("notApplyFilters: " + notApplyFilters);
			if ( notApplyFilters != null ) {
				qvo.setNotApplyFilters(notApplyFilters);
			}
			
			
			
			
		}

	}


	private Element getRootElementOfConfigFile() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
//			LOGGER.info("Reading file @ " + (Setting.getSystemPath() + File.separator + dirPath + File.separator + fileName + ".xml"));
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
	
	private void parseGrowthRate(DWFAOSTATQueryVO qvo, CalculationParametersVO  calcParameters, Element calcParamsElement) {
//		LOGGER.info("parseGrowthRate: ");
		try {
			Element presentElement = XMLUtils.getChildElement(calcParamsElement,"TIMEPERIOD_PRESENT");
			calcParameters.setTimeperiod_present(XMLUtils.getList(presentElement, qvo, getLanguage()));
//			LOGGER.info("setTimeperiod_present: " +calcParameters.getTimeperiod_present() );

		}catch (Exception e) {
		}

		try {
			Element pastElement = XMLUtils.getChildElement(calcParamsElement,"TIMEPERIOD_PAST");
			calcParameters.setTimeperiod_past(XMLUtils.getList(pastElement, qvo, getLanguage()));
//			LOGGER.info("setTimeperiod_past: " +calcParameters.getTimeperiod_past() );
		}catch (Exception e) {
		}

	}


	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FAOSTATVisualizeSettingsVO getSettings() {
		return settings;
	}

	public void setSettings(FAOSTATVisualizeSettingsVO settings) {
		this.settings = settings;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}




}