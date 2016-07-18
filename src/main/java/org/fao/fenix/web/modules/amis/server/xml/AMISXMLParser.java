package org.fao.fenix.web.modules.amis.server.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.amis.common.constants.AMISAggregationConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISTableConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISCalculationParametersVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISFilterVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISSettingsVO;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AMISXMLParser {

	private static final Logger LOGGER = Logger.getLogger(AMISXMLParser.class);
	private String fileName;
	private String dirPath;

	private static final String DEFAULT_RESOURCES = "DEFAULT";
	private static final String OTHER_RESOURCES = "OTHER" ;


	private AMISSettingsVO settings;


	public AMISXMLParser(String dirPath, String filename) {
		setDirPath(dirPath);
		setFileName(filename);
		buildAMISQueryVOsMap();
	}

	public AMISSettingsVO getAllAMISQueryVOs() {
		return getSettings();
	}




	public List<AMISQueryVO> getDefaultAMISQueryVOs() {
		 if(!getSettings().getQvos().isEmpty() && getSettings().getQvos().get(DEFAULT_RESOURCES)!=null){
		   return getSettings().getQvos().get(DEFAULT_RESOURCES);
		 } else
			 return null;
	}

	public List<AMISQueryVO> getOtherAMISQueryVOs() {
		if(!getSettings().getQvos().isEmpty() && getSettings().getQvos().get(OTHER_RESOURCES)!=null){
		   return getSettings().getQvos().get(OTHER_RESOURCES);
		 } else
			 return null;
	}


	private void  buildAMISQueryVOsMap() {

		// settings
		AMISSettingsVO settings = new AMISSettingsVO();

		// settings of the view (i.e. viewTitle)
		buildConfigurationSettings(settings);

		// filters
		settings.setFilters(buildfiltersVO());


		// query VOs
		 List<AMISQueryVO> defaultvos = buildDefaultResourcesVO();

		 /** TODO: probably they shouldn't work... **/
		 List<AMISQueryVO> othervos = buildOtherResourcesVO();

         HashMap<String, List<AMISQueryVO>> qvos = new LinkedHashMap<String, List<AMISQueryVO>>();

         if(!defaultvos.isEmpty()) {
        	 qvos.put(DEFAULT_RESOURCES, defaultvos);

         }
         if(!othervos.isEmpty()) {
        	 qvos.put(OTHER_RESOURCES, othervos);
         }

         settings.setQvos(qvos);


         setSettings(settings);
	}


	
	/**
	 *
	 * TODO: This method fills all the parameters of the view (title, layoutType...)
	 *
	 * @param settings
	 */
	private void buildConfigurationSettings(AMISSettingsVO settings) {

		try {
			Element element = getRootElementOfConfigFile();
			settings.setViewTitle(XMLUtils.getTextValue(XMLUtils.getChildElement(element,"TITLE"),AMISConstants.defaultLanguage));

			LOGGER.info("VIEW TITLE: " + settings.getViewTitle());

		}catch (Exception e) {
			LOGGER.info("ERRORE VIEW TITLE: ");
		}
	}

	private List<AMISFilterVO> buildfiltersVO() {
		LOGGER.info("FILTERS ... ");

		List<AMISFilterVO> filters = new ArrayList<AMISFilterVO>();

		// TODO: at this level the try/catch?
		try {
			Element element = getRootElementOfConfigFile();
			NodeList tag = element.getElementsByTagName("FILTERS");
			for (int i = 0; i < tag.getLength(); i++) {
				Element filtersElement = (Element) tag.item(i);

				tag = filtersElement.getElementsByTagName("FILTER");
				LOGGER.info("FILTERS lenght: " + tag.getLength());



				for (int j = 0; j < tag.getLength(); j++) {
					AMISFilterVO filter = new AMISFilterVO();

					// FILTER TYPE and MULTISELECTION
					Element filterElement = (Element) tag.item(j);

					// check if exist the useCodingSystem attribute
					String useCodingSystem = filterElement.getAttribute("useCodingSystem");
//					LOGGER.info("useCodingSystem: " + useCodingSystem + "");
					if ( useCodingSystem != null && !useCodingSystem.equalsIgnoreCase("")) {
//						LOGGER.info("Boolean.valueOf(useCodingSystem): " + Boolean.valueOf(useCodingSystem));
						filter.setUseCodingSystem( Boolean.valueOf(useCodingSystem));
					}

					Element filterTypeElement = XMLUtils.getChildElement(filterElement,"FILTERTYPE");

					String filterType = filterTypeElement.getTextContent();
					filter.setFilterType(filterType);
					String multiselection = filterTypeElement.getAttribute("multiselection");

					LOGGER.info("FILTERTYPE: " + filterType);

					if ( multiselection != null ) {
						Boolean isMultiSelection = Boolean.valueOf(multiselection);
						filter.setIsMultiSelection(isMultiSelection);
					}

					try {
						NodeList ddCodesList = filterElement.getElementsByTagName("DROPDOWNCODES");
						List<AMISCodesModelData> ddCodes = new ArrayList<AMISCodesModelData>();

						for (int z = 0; z < ddCodesList.getLength(); z++) {

							Element ddCodesElement = (Element) ddCodesList.item(z);

							NodeList codeList = ddCodesElement.getElementsByTagName("CODE");
							for (int k = 0; k < codeList.getLength(); k++) {
								Element codeElement = (Element) codeList.item(k);

								// TODO: for everything is setted with codes...should be called the DB for the label?
								String label = codeElement.getTextContent();
								// check if exist the useCodingSystem attribute
								String attributeLabel = codeElement.getAttribute("label");
								if ( attributeLabel != null && !label.equalsIgnoreCase("")) {
									label = attributeLabel;
								}

								String attributeDomain = codeElement.getAttribute("domain");
								ddCodes.add(new AMISCodesModelData(codeElement.getTextContent(), label, attributeDomain));

							}

						}
						LOGGER.info("DDCODES: " + ddCodes);

						filter.setCodes(ddCodes);

					}catch (Exception e) {}

					// this are the Default codes of the filter
					try {
						NodeList defaultCodesList = filterElement.getElementsByTagName("DEFAULTCODES");
						List<AMISCodesModelData> defaultCodes = new ArrayList<AMISCodesModelData>();
//						List<String> defaultCodes = new ArrayList<String>();
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
								defaultCodes.add(new AMISCodesModelData(codeElement.getTextContent(), label, attributeDomain));
							}

						}
						LOGGER.info("DEFAULTCODES: " + defaultCodes);

						filter.setDefaultCodes(defaultCodes);
					}
					catch (Exception e) {
					}

					filters.add(filter);

				}

			}
		}catch (Exception e) {
		}

		LOGGER.info("filters ... SIZE "+ filters.size());

		return filters;
	}

	private List<AMISQueryVO> buildDefaultResourcesVO() {
		LOGGER.info("DEFAULT RESOURCES ... ");

		List<AMISQueryVO> qvos = new ArrayList<AMISQueryVO>();

		Element element = getRootElementOfConfigFile();
		NodeList tag = element.getElementsByTagName("DEFAULT");
		for (int i = 0; i < tag.getLength(); i++) {
			Element defaultElement = (Element) tag.item(i);
			tag = defaultElement.getElementsByTagName("RESOURCE");
			for (int j = 0; j < tag.getLength(); j++) {
				Element resourceElement = (Element) tag.item(j);

				AMISQueryVO qvo = new AMISQueryVO();

				qvo.setOutput(XMLUtils.getTextValue(resourceElement,"RESOURCETYPE"));
				qvo.setTypeOfOutput(XMLUtils.getTextValue(resourceElement,"OUTPUTTYPE"));
				if(XMLUtils.getChildElement(resourceElement,"TITLE")!=null)
				qvo.setTitle(XMLUtils.getTextValue(XMLUtils.getChildElement(resourceElement,"TITLE"),AMISConstants.defaultLanguage));

				//qvo.setTitle(XMLUtils.getTextValue(XMLUtils.getChildElement(resourceElement,"TITLE"),AMISConstants.defaultLanguage));

				//Image sizes (this should not be mandatory..)
			/***	try {
					Element smallElement = XMLUtils.getChildElement(XMLUtils.getChildElement(resourceElement,"IMAGESIZE"),"SMALL");
					qvo.setSmallWidth(XMLUtils.getTextValue(smallElement,"HEIGHT"));
					qvo.setSmallHeight(XMLUtils.getTextValue(smallElement,"WIDTH"));

					Element bigElement = XMLUtils.getChildElement(XMLUtils.getChildElement(resourceElement,"IMAGESIZE"),"BIG");
					qvo.setBigHeight(XMLUtils.getTextValue(bigElement,"HEIGHT"));
					qvo.setBigWidth(XMLUtils.getTextValue(bigElement,"WIDTH"));
				} catch (Exception e) {
					LOGGER.info("image sizes are not defined");
				}***/

				// Resource Size (this is not mandatory)
				try {
					Element sizeElement = XMLUtils.getChildElement(resourceElement,"SIZE");
					qvo.setHeight(XMLUtils.getTextValue(sizeElement,"HEIGHT"));
					qvo.setWidth(XMLUtils.getTextValue(sizeElement,"WIDTH"));
					LOGGER.info("SIZE width: " + qvo.getWidth() + " | height: " + qvo.getHeight());
				} catch (Exception e) {
					LOGGER.info("custom sizes are not defined");
				}

				setQueryParameters(qvo, resourceElement);

				setApplicableFilters(qvo, resourceElement);

				qvos.add(qvo);

				LOGGER.info("qvo title = "+qvo.getTitle() + " : timespan = "+qvo.getTimeSpan() + " : elements = "+qvo.getElements());
			}
		}

		LOGGER.info("DEFAULT ... SIZE "+ qvos.size());

		return qvos;
	}

	private List<AMISQueryVO> buildOtherResourcesVO() {

		List<AMISQueryVO> qvos = new ArrayList<AMISQueryVO>();

		LOGGER.info("OTHER RESOURCES ..." );

		Element element = getRootElementOfConfigFile();

		NodeList children = element.getElementsByTagName("RESOURCES");

		for (int j = 0; j < children.getLength(); j++) {
			Element resourcesElement = (Element) children.item(j);

			if(resourcesElement.getTagName().equalsIgnoreCase("RESOURCE")){

					AMISQueryVO qvo = new AMISQueryVO();

					qvo.setOutput(XMLUtils.getTextValue(resourcesElement,"RESOURCETYPE"));
					qvo.setTypeOfOutput(XMLUtils.getTextValue(resourcesElement,"OUTPUTTYPE"));
					qvo.setTitle(XMLUtils.getTextValue(XMLUtils.getChildElement(resourcesElement,"TITLE"),AMISConstants.defaultLanguage));

					setQueryParameters(qvo, resourcesElement);

					qvos.add(qvo);
				}
			}

		LOGGER.info("OTHER RESOURCES ... SIZE "+ qvos.size());

		return qvos;
	}

	private void setQueryParameters(AMISQueryVO qvo, Element resourceElement) {

		//SET DATE QUERY PARAMS
		Element dateQueryElement = XMLUtils.getChildElement(resourceElement,"DATEQUERY");

		if (dateQueryElement!=null && dateQueryElement.hasChildNodes()) {
			qvo.setRunMaxDateQuery(true);
			qvo.setTimeSpan(XMLUtils.getIntValue(dateQueryElement, "TIMESPAN"));
			
			Element timeSpanQueryElement = XMLUtils.getChildElement(dateQueryElement,"TIMESPANQUERY");
	        if(timeSpanQueryElement!=null){
	        	qvo.setTimeIntervalQuery(XMLUtils.getTextValue(dateQueryElement, "TIMESPANQUERY"));	
				qvo.setRunTimeIntervalQuery(true);
			}
				
		}
		
		//SET SHOW ZERO VALUES PARAMS
		Element showZeroElement = XMLUtils.getChildElement(resourceElement,"SHOWZEROES");

		if (showZeroElement!=null) {
			qvo.setShowZeroValues(XMLUtils.getBooleanValue(resourceElement, "SHOWZEROES"));	
		} else
			qvo.setShowZeroValues(true);	
		

		//SET CALCULATION QUERY PARAMS
		Element calcQueryElement = XMLUtils.getChildElement(resourceElement,"CALCULATIONQUERY");

		if (calcQueryElement!=null && calcQueryElement.hasChildNodes()) {
			qvo.setRunCalculationQuery(true);

			AMISCalculationParametersVO  calcParameters = new AMISCalculationParametersVO();

			AMISAggregationConstants calculationType = AMISAggregationConstants.valueOf(XMLUtils.getTextValue(calcQueryElement,"TYPE"));

			try {
				calcParameters.setCalculationType(calculationType);
				calcParameters.setResultLabel(XMLUtils.getTextValue(calcQueryElement,"RESULTLABEL"));
			}catch (Exception e) {}

			try {
				Element calcParamsElement = XMLUtils.getChildElement(calcQueryElement,"PARAMETERS");

				try {
					calcParameters.setFilterColumn(AMISTableConstants.valueOf(XMLUtils.getTextValue(calcParamsElement,"FILTERCOLUMN")));
				}catch (Exception e) {
				}


				if(calculationType.equals(AMISAggregationConstants.SUBTRACT)){
					Element minuendElement = XMLUtils.getChildElement(calcParamsElement,"MINUEND");
					calcParameters.setSubtractionMinuend(XMLUtils.getList(minuendElement, qvo));

					Element subtrahendElement = XMLUtils.getChildElement(calcParamsElement,"SUBTRAHEND");
					calcParameters.setSubtractionSubtrahend(XMLUtils.getList(subtrahendElement, qvo));
				}
				if(calculationType.equals(AMISAggregationConstants.PERCENTAGE_SHARE)){
					Element dividendElement = XMLUtils.getChildElement(calcParamsElement,"DIVIDEND");
					calcParameters.setDividend(XMLUtils.getList(dividendElement, qvo));

					Element divisorElement = XMLUtils.getChildElement(calcParamsElement,"DIVISOR");
					calcParameters.setDivisor(XMLUtils.getList(divisorElement, qvo));
				}

				if(calculationType.equals(AMISAggregationConstants.GROWTH_RATE)){
					LOGGER.info("GROWTH_RATE: ");
					parseGrowthRate(qvo, calcParameters, calcParamsElement);
				}
			}catch (Exception e) {
				LOGGER.info("error...: ");
			}



			qvo.setCalculationParametersVO(calcParameters);
		}

		//SET BASE QUERY PARAMS
		Element baseQueryElement = XMLUtils.getChildElement(resourceElement,"BASEQUERY");

		/** TODO: dop the same for nested limit **/
		qvo.setLimit(XMLUtils.getIntValue(baseQueryElement, "LIMIT"));


		// NESTED LIMIT
		try {
			Element nestedLimit = XMLUtils.getChildElement(baseQueryElement,"NESTEDLIMIT");
			String limit = nestedLimit.getTextContent();

			qvo.setNestedLimit(Integer.valueOf(limit));

			String nestedLimitField = nestedLimit.getAttribute("field");
			qvo.setNestedLimitField(nestedLimitField);

			LOGGER.info("NESTED LIMIT: " + qvo.getNestedLimit());
			LOGGER.info("NESTED field: " + qvo.getNestedLimitField());

		}catch (Exception e) {
		}

		Element selectsElement = XMLUtils.getChildElement(baseQueryElement,"SELECTS");

		qvo.setSelects(XMLUtils.getList(selectsElement, qvo));
		
		
		Element unitNameElement = XMLUtils.getChildElement(baseQueryElement,"UNITNAME");
		
		if(unitNameElement!=null)
			qvo.setMeasurementUnit(XMLUtils.getTextValue(baseQueryElement,"UNITNAME"));
	

		Element sortElement = XMLUtils.getChildElement(baseQueryElement,"SORT");

		if (sortElement!=null && sortElement.hasAttribute("type") && sortElement.getAttribute("type")!=null) {
			qvo.setSortingOrder(sortElement.getAttribute("type"));
		}

		// ORDER BY
		try {
			qvo.setOrderBys(XMLUtils.getList(sortElement, qvo));
		}
		catch (Exception e) {
			LOGGER.info("no sort defined");
		}
		
		Element groupByElement = XMLUtils.getChildElement(baseQueryElement,"GROUPBYS");

		if (groupByElement!=null) {
			// GROUP BY
			try {
				qvo.setGroupBys(XMLUtils.getList(groupByElement, qvo));
				
				LOGGER.info("GROUP BYS "+qvo.getGroupBys().size());
			}
			catch (Exception e) {
				LOGGER.info("no sort defined");
			}
		}

		

		// REGIONAL LEVEL QUERY
		try {
			// TODO: change it, if is regional
			Boolean value = XMLUtils.getBooleanValue(baseQueryElement, "REGIONAL_LEVEL");

			LOGGER.info("REGIONAL_LEVEL: " + value);
			if ( value != null) {
				LOGGER.info("SETTING REGIONAL_LEVEL: " + value);
				qvo.setIsRegionLevel(value);
			}
		}
		catch (Exception e) {
		}

		// COUNTRY LEVEL QUERY
		try {
			// TODO: change it, if is regional
			Boolean value = XMLUtils.getBooleanValue(baseQueryElement, "COUNTRY_LEVEL");

			LOGGER.info("COUNTRY_LEVEL: " + value);
			if ( value != null) {
				LOGGER.info("SETTING COUNTRY_LEVEL: " + value);
				qvo.setIsCountryLevel(value);
			}
		}
		catch (Exception e) {
		}



		Element whereElement = XMLUtils.getChildElement(baseQueryElement,"WHERE");
		//Element domainsElement = XMLUtils.getChildElement(whereElement,"DOMAINS");
		//qvo.setDomains(XMLUtils.getMap(domainsElement, qvo));



		try {
			Element itemsElement = XMLUtils.getChildElement(whereElement,"ITEMS");
			qvo.setItems(XMLUtils.getMap(itemsElement, qvo));
		}
		catch (Exception e) {
		}

		try {
			Element elementsElement = XMLUtils.getChildElement(whereElement,"ELEMENTS");
			qvo.setElements(XMLUtils.getMap(elementsElement, qvo));
		}
		catch (Exception e) {
		}

		/**try {
			Element elementsListElement = XMLUtils.getChildElement(whereElement,"ELEMENTSLIST");
			qvo.setElementsList(XMLUtils.getMap(elementsListElement, qvo));
		}
		catch (Exception e) {
		}***/

		try {
			Element elementsElement = XMLUtils.getChildElement(whereElement,"AREAS");
			qvo.setAreas(XMLUtils.getMap(elementsElement, qvo));
		}
		catch (Exception e) {
		}

		try {
			Element yearsElemet = XMLUtils.getChildElement(whereElement,"YEARS");
			qvo.setYears(XMLUtils.getMap(yearsElemet, qvo));
		}
		catch (Exception e) {
		}
		
		try {
			Element databasesElement = XMLUtils.getChildElement(whereElement,"DATABASES");
			qvo.setDatabases(XMLUtils.getMap(databasesElement, qvo));
		}
		catch (Exception e) {
		}


	}


	private void setApplicableFilters(AMISQueryVO qvo, Element resourceElement) {

		//SET APPLICABLE FILTERS
		Element applicableFiltersElement = XMLUtils.getChildElement(resourceElement,"APPLICABLE_FILTERS");


		if ( applicableFiltersElement != null ) {

			//SET APPLY_ALL_FILTERS_EXCEPT_YEARS PARAM
			Boolean exceptYears = XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ALL_FILTERS_EXCEPT_YEARS");
			LOGGER.info("exceptYears: " + exceptYears);
			if ( exceptYears != null ) {
				qvo.setApplyAllFiltersExceptYears(exceptYears);
			}

			//SET APPLY_ONLY_YEAR_FILTER
			Boolean onlyYears = XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ONLY_YEAR_FILTER");
			LOGGER.info("ONLYYears: " + onlyYears);
			if ( onlyYears != null ) {
				qvo.setApplyOnlyYearFilter(onlyYears);
			}

			//SET APPLY_ALL_FILTERS_EXCEPT_AGGREGATION_TYPE
			Boolean exceptAggregationType= XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ALL_FILTERS_EXCEPT_AGGREGATION_TYPE");
			LOGGER.info("exceptAggregationType: " + exceptAggregationType);
			if ( exceptAggregationType != null ) {
				qvo.setApplyAllFiltersExceptAggregrationType(exceptAggregationType);
			}

			//SET APPLY_ALL_FILTERS_EXCEPT_AREAS
			Boolean applyAllFiltersExceptAreas = XMLUtils.getBooleanValue(applicableFiltersElement, "APPLY_ALL_FILTERS_EXCEPT_AREAS");
			LOGGER.info("applyAllFiltersExceptAreas: " + applyAllFiltersExceptAreas);
			if ( applyAllFiltersExceptAreas != null ) {
				qvo.setApplyAllFiltersExceptYears(applyAllFiltersExceptAreas);
			}


			Boolean notApplyFilters = XMLUtils.getBooleanValue(applicableFiltersElement, "NOT_APPLY_FILTERS");
			LOGGER.info("notApplyFilters: " + notApplyFilters);
			if ( notApplyFilters != null ) {
				qvo.setNotApplyFilters(notApplyFilters);
			}




		}

	}


	private Element getRootElementOfConfigFile() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			LOGGER.info("Reading file @ " + (Setting.getSystemPath() + File.separator + dirPath + File.separator + fileName + ".xml"));
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

	private void parseGrowthRate(AMISQueryVO qvo, AMISCalculationParametersVO  calcParameters, Element calcParamsElement) {
		LOGGER.info("parseGrowthRate: ");
		try {
			Element presentElement = XMLUtils.getChildElement(calcParamsElement,"TIMEPERIOD_PRESENT");
			calcParameters.setTimeperiod_present(XMLUtils.getList(presentElement, qvo));
			LOGGER.info("setTimeperiod_present: " +calcParameters.getTimeperiod_present() );

		}catch (Exception e) {
		}

		try {
			Element pastElement = XMLUtils.getChildElement(calcParamsElement,"TIMEPERIOD_PAST");
			calcParameters.setTimeperiod_past(XMLUtils.getList(pastElement, qvo));
			LOGGER.info("setTimeperiod_past: " +calcParameters.getTimeperiod_past() );
		}catch (Exception e) {
		}



	}


	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public AMISSettingsVO getSettings() {
		return settings;
	}

	public void setSettings(AMISSettingsVO settings) {
		this.settings = settings;
	}




}