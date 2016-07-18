package org.fao.fenix.web.modules.ec.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ECRSSParser {
	
	private static final Logger LOGGER = Logger.getLogger(ECRSSParser.class);

	private static Map<String, String> gaulMap;
	
	private static Map<String, String> gaulReliefWebMapping;
		
	static {
		/// RELIEF WEB NEW MAPPING RSS
		gaulReliefWebMapping = new HashMap<String, String>();
		
		
		// Afghanistan
		gaulReliefWebMapping.put("1", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-13");
		 
		 
		// bangladesh
		gaulReliefWebMapping.put("23", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-31");


//		Bolivia "33" 
		gaulReliefWebMapping.put("33","http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-39");
			 
			 
//		Burkina 
		gaulReliefWebMapping.put("42", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-46");
			 
			 
//		CAR
		gaulReliefWebMapping.put("49", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-54");
			 
			 
//		DRC 
		gaulReliefWebMapping.put("68", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-75");

		
//		Eritrea 
		gaulReliefWebMapping.put("77", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-85");
		 
//		Ethiopia        
		gaulReliefWebMapping.put("79", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-87");
		 
		 
//		Guinea  
		gaulReliefWebMapping.put("106","http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-110");
		 
		 
//		Haiti   
		gaulReliefWebMapping.put("108","http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-113");
		 
//		Honduras        
		gaulReliefWebMapping.put("111","http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-116");
		 
//		Kenya   
		gaulReliefWebMapping.put("133","http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-131");
		
		//Lesotho
		gaulReliefWebMapping.put("142","http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-138");
		 
		//Liberia
		 
		gaulReliefWebMapping.put("144", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-139");
		 
//		Madagascar      150
		gaulReliefWebMapping.put("150",	"http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-144");
		 
		 
//		Malawi  152
		gaulReliefWebMapping.put("152", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-146");
		 
		 
//		Mali    155
		gaulReliefWebMapping.put("155", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-149");
		 
		 
//		Mauritania      159
		gaulReliefWebMapping.put("159", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-153");
		 
		 
//		Mozambique      170
		gaulReliefWebMapping.put("170", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-164");
		
		//Nepal 175
		gaulReliefWebMapping.put("175", "http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-168");
		 
		 
		//Nicaragua     180
		gaulReliefWebMapping.put("180",
		"http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-173");
		 
		 
		//Niger 181
		gaulReliefWebMapping.put("181",
		"http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-174");
		 
		 
		//Pakistan      188
		gaulReliefWebMapping.put("188","http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-182");
		 
		//SierraLeone   221
		gaulReliefWebMapping.put("221",
		"http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-211");
		 
		 
		//Somalia       226
		gaulReliefWebMapping.put("226",
		"http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-216");
		 
		//Sri Lanka     231
		gaulReliefWebMapping.put("231",
		"http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-219");
		 
		//Sudan
		gaulReliefWebMapping.put("40764",
		"http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-220");
		 
		//Yemen 269
		gaulReliefWebMapping.put("269",
		"http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-255");
		 
		 
		//Zimbabwe      271
		gaulReliefWebMapping.put("271",
		"http://reliefweb.int/updates/rss.xml?sl=environment-report_listing%252Ctaxonomy_index_tid_country-257");
		
		
		
		gaulMap = new HashMap<String, String>();
		
		/** AFRICA **/
		gaulMap.put("40764", "http://www.irinnews.org/RSS/Sudan.xml");
		gaulMap.put("43", "http://www.irinnews.org/RSS/Burundi.xml");
		gaulMap.put("49", "http://www.irinnews.org/RSS/Central_African_Republic.xml");
		gaulMap.put("59", "http://www.irinnews.org/RSS/Congo.xml");
		gaulMap.put("68", "http://www.irinnews.org/RSS/DRC.xml");
		gaulMap.put("205", "http://www.irinnews.org/RSS/Rwanda.xml");
		gaulMap.put("133", "http://www.irinnews.org/RSS/Kenya.xml");
		gaulMap.put("257", "http://www.irinnews.org/RSS/Tanzania.xml");
		gaulMap.put("253", "http://www.irinnews.org/RSS/Uganda.xml");
		gaulMap.put("70", "http://www.irinnews.org/RSS/Djibouti.xml");
		gaulMap.put("77", "http://www.irinnews.org/RSS/Eritrea.xml");
		gaulMap.put("79", "http://www.irinnews.org/RSS/Ethiopia.xml");
		gaulMap.put("226", "http://www.irinnews.org/RSS/Somalia.xml");
		gaulMap.put("8", "http://www.irinnews.org/RSS/Angola.xml");
		gaulMap.put("35", "http://www.irinnews.org/RSS/Botswana.xml");
		gaulMap.put("58", "http://www.irinnews.org/RSS/Comoros.xml");
		gaulMap.put("142", "http://www.irinnews.org/RSS/Lesotho.xml");
		gaulMap.put("150", "http://www.irinnews.org/RSS/Madagascar.xml");
		gaulMap.put("152", "http://www.irinnews.org/RSS/Malawi.xml");
		gaulMap.put("160", "http://www.irinnews.org/RSS/Mauritius.xml");
		gaulMap.put("170", "http://www.irinnews.org/RSS/Mozambique.xml");
		gaulMap.put("172", "http://www.irinnews.org/RSS/Namibia.xml");
		gaulMap.put("220", "http://www.irinnews.org/RSS/Seychelles.xml");
		gaulMap.put("227", "http://www.irinnews.org/RSS/South_Africa.xml");
		gaulMap.put("235", "http://www.irinnews.org/RSS/Swaziland.xml");
		gaulMap.put("270", "http://www.irinnews.org/RSS/Zambia.xml");
		gaulMap.put("271", "http://www.irinnews.org/RSS/Zimbabwe.xml");
		gaulMap.put("42", "http://www.irinnews.org/RSS/Burkina_Faso.xml");
		gaulMap.put("45", "http://www.irinnews.org/RSS/Cameroon.xml");
		gaulMap.put("47", "http://www.irinnews.org/RSS/Cape_Verde.xml");
		gaulMap.put("50", "http://www.irinnews.org/RSS/Chad.xml");
		gaulMap.put("66", "http://www.irinnews.org/RSS/Cote_d'Ivoire.xml");
		gaulMap.put("89", "http://www.irinnews.org/RSS/Gabon.xml");
		gaulMap.put("90", "http://www.irinnews.org/RSS/Gambia.xml");
		gaulMap.put("94", "http://www.irinnews.org/RSS/Ghana.xml");
		gaulMap.put("106", "http://www.irinnews.org/RSS/Guinea.xml");
		gaulMap.put("105", "http://www.irinnews.org/RSS/Guinea-Bissau.xml");
		gaulMap.put("144", "http://www.irinnews.org/RSS/Liberia.xml");
		gaulMap.put("155", "http://www.irinnews.org/RSS/Mali.xml");
		gaulMap.put("159", "http://www.irinnews.org/RSS/Mauritania.xml");
		gaulMap.put("181", "http://www.irinnews.org/RSS/Niger.xml");
		gaulMap.put("182", "http://www.irinnews.org/RSS/Nigeria.xml");
		gaulMap.put("214", "http://www.irinnews.org/RSS/Sao_Tome_and_Principe.xml");
		gaulMap.put("217", "http://www.irinnews.org/RSS/Senegal.xml");
		gaulMap.put("221", "http://www.irinnews.org/RSS/Sierra_Leone.xml");
		gaulMap.put("243", "http://www.irinnews.org/RSS/Togo.xml");
		gaulMap.put("268", "http://www.irinnews.org/RSS/Western_Sahara.xml");
		gaulMap.put("76", "http://www.irinnews.org/RSS/Equatorial_Guinea.xml");		
		
		/** ASIA **/
		gaulMap.put("1", "http://www.irinnews.org/RSS/Afghanistan.xml");	
		gaulMap.put("23", "http://www.irinnews.org/RSS/Bangladesh.xml");
		gaulMap.put("116", "http://www.irinnews.org/RSS/Indonesia.xml");
		gaulMap.put("117", "http://www.irinnews.org/RSS/Iran.xml");
		gaulMap.put("132", "http://www.irinnews.org/RSS/Kazakhstan.xml");
		gaulMap.put("138", "http://www.irinnews.org/RSS/Kyrgyzstan.xml");
		gaulMap.put("139", "http://www.irinnews.org/RSS/Laos.xml");
		gaulMap.put("171", "http://www.irinnews.org/RSS/Myanmar.xml");
		gaulMap.put("175", "http://www.irinnews.org/RSS/Nepal.xml");
		gaulMap.put("188", "http://www.irinnews.org/RSS/Pakistan.xml");
		gaulMap.put("192", "http://www.irinnews.org/RSS/Papua_New_Guinea.xml");
		gaulMap.put("196", "http://www.irinnews.org/RSS/Philippines.xml");
		gaulMap.put("231", "http://www.irinnews.org/RSS/Sri_Lanka.xml");
		gaulMap.put("239", "http://www.irinnews.org/RSS/Tajikistan.xml");
		gaulMap.put("240", "http://www.irinnews.org/RSS/Thailand.xml");
		gaulMap.put("242", "http://www.irinnews.org/RSS/Timor-Leste.xml");
		gaulMap.put("250", "http://www.irinnews.org/RSS/Turkmenistan.xml");
		gaulMap.put("261", "http://www.irinnews.org/RSS/Uzbekistan.xml");
		gaulMap.put("264", "http://www.irinnews.org/RSS/Vietnam.xml");
		
		/** MIDDLE EAST **/
		gaulMap.put("40765", "http://www.irinnews.org/RSS/Egypt.xml");	
		gaulMap.put("118", "http://www.irinnews.org/RSS/Iraq.xml");
		gaulMap.put("121", "http://www.irinnews.org/RSS/Israel.xml");
		gaulMap.put("130", "http://www.irinnews.org/RSS/Jordan.xml");
		gaulMap.put("131", "http://www.irinnews.org/RSS/Lebanon.xml");
		gaulMap.put("238", "http://www.irinnews.org/RSS/Syria.xml");
		gaulMap.put("255", "http://www.irinnews.org/RSS/United_Arab_Emirates.xml");
		gaulMap.put("269", "http://www.irinnews.org/RSS/Yemen.xml");
		/** N.B. MISSION PALESTIAN TERRITORY **/
		
		
		
		
		
	}
	

	/*
	 * http://feeds.feedburner.com/time/world?format=xml
	 * http://www.irinnews.org/irin.xml
	 * http://newsrss.bbc.co.uk/rss/newsonline_world_edition/americas/rss.xml
	 */
	
	public List<String> getLatestNews(String urlString, String gaulCode, String filter, int entries) {
		List<String> news = new ArrayList<String>();
		try {
			URL url = new URL(urlString);
			InputStream is = url.openStream();
			DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(dis);
			Element element = document.getDocumentElement();
			NodeList itemTag = element.getElementsByTagName("item");
			for (int i = 0; i < itemTag.getLength(); i++) {
				if (news.size() < entries) {
					Element nodeElement = (Element) itemTag.item(i);
					NodeList titleTag = nodeElement.getElementsByTagName("title");
					String title = ((Element) titleTag.item(0)).getTextContent();
					NodeList linkTag = nodeElement.getElementsByTagName("link");
					String link = ((Element) linkTag.item(0)).getTextContent();
					NodeList descriptionTag = nodeElement.getElementsByTagName("description");
					String description = ((Element) descriptionTag.item(0)).getTextContent();
					String newsURL = "<a href='" + link + "' target='_blank'>" + title + "</a>" + "<br>" + description;
					if (title.toLowerCase().contains(filter) || description.toLowerCase().contains(filter)) {
						NodeList dateTag = nodeElement.getElementsByTagName("pubDate");
						if (dateTag.item(0) != null) {
							String date = ((Element) dateTag.item(0)).getTextContent();
							newsURL += "<br><br><i>Published on: " + date + "</i>";
						} else {
							dateTag = nodeElement.getElementsByTagName("dateLastUpdate");
							if (dateTag.item(0) != null) {
								String date = ((Element) dateTag.item(0)).getTextContent();
								newsURL += "<br><br><i>Published on: " + date + "</i>";
							}
						}
						news.add(newsURL);
					}
				} else
					break;
			}
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		} catch (SAXException e) {
			LOGGER.error(e.getMessage());
		}
		return news;
	}
	
	public List<String> getLatestNews(List<String> urls, String gaulCode, String filter, int entries) {
		List<String> news = new ArrayList<String>();
		for (String urlString : urls) {
			List<String> subList = getLatestNews(urlString, gaulCode, filter, entries);
			for (String subNews : subList)
				news.add(subNews);
		}
		return news;
	}
	
	
	public List<String> getLatestIrinNews(String gaulCode, int entries, String iso3Code) {
		LOGGER.info("gaulCode: " + gaulCode );
		LOGGER.info("entries: " + entries );
		LOGGER.info("iso3Code: " + iso3Code );
		LOGGER.info("gaulMap.containsKey(gaulCode): " + gaulMap.containsKey(gaulCode));
		
		String reliefweblink = gaulReliefWebMapping.get(gaulCode);
//		if ( !gaulMap.containsKey(gaulCode)) {
			LOGGER.info("getLatestRefliefWebNews: " + reliefweblink);
			return getLatestRefliefWebNewsNEW(reliefweblink, entries);
//		}
//		else {
//			LOGGER.info("getLatestIrinNews: ");
//				
//				List<String> news = new ArrayList<String>();
//				
//				try {
//					
//					URL url = new URL(gaulMap.get(gaulCode));
//					InputStream is = url.openStream();
//					
//					DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
//					
//					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//					DocumentBuilder db = dbf.newDocumentBuilder();
//					Document document = db.parse(dis);
//					Element element = document.getDocumentElement();
//					String irinCountry = getIrinCountry(gaulMap.get(gaulCode));
//					
//					NodeList itemTag = element.getElementsByTagName("item");
//					for (int i = 0; i < itemTag.getLength(); i++) {
//						if (news.size() < entries) {
//							Element nodeElement = (Element) itemTag.item(i);
//							NodeList titleTag = nodeElement.getElementsByTagName("title");
//							String title = ((Element) titleTag.item(0)).getTextContent();
//							if (title.toLowerCase().contains(irinCountry)) {
//								NodeList linkTag = nodeElement.getElementsByTagName("link");
//								String link = ((Element) linkTag.item(0)).getTextContent();
//								String newsURL = new String();
//								if ( title.length() > 65) 
//									newsURL = "<a href='" + link + "'>" + title.substring(0, 64) + "...</a>";
//								else
//									newsURL = "<a href='" + link + "'>" + title + "</a>";
//								news.add(newsURL);
//							}
//						} else
//							break;
//					}
//				
//	
//				
//			} catch (MalformedURLException e) {
//				LOGGER.error(e.getMessage());
//			} catch (IOException e) {
//				LOGGER.error(e.getMessage());
//			} catch (ParserConfigurationException e) {
//				LOGGER.error(e.getMessage());
//			} catch (SAXException e) {
//				LOGGER.error(e.getMessage());
//			}
//			
//			return news;
//		}
	}
	
	public List<String> getLatestRefliefWebNewsNEW(String reliefweblink, int entries) {
		
		List<String> news = new ArrayList<String>();
		
		try {
			
			URL url = new URL(reliefweblink);
			InputStream is = url.openStream();
			
			DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(dis);
			Element element = document.getDocumentElement();
			
			
			NodeList itemTag = element.getElementsByTagName("item");
			for (int i = 0; i < itemTag.getLength(); i++) {
				if (news.size() < entries) {
					Element nodeElement = (Element) itemTag.item(i);
					NodeList titleTag = nodeElement.getElementsByTagName("title");
					String title = ((Element) titleTag.item(0)).getTextContent();
//					if (title.toLowerCase().contains("haiti")) {
						NodeList linkTag = nodeElement.getElementsByTagName("link");
						String link = ((Element) linkTag.item(0)).getTextContent();
						String newsURL = new String();
						if ( title.length() > 55) 
							newsURL = "<a href='" + link + "'>" + title.substring(0, 54) + "...</a>";
						else
							newsURL = "<a href='" + link + "'>" + title + "</a>";
						news.add(newsURL);
//					}
				} else
					break;
			}

			LOGGER.info("NEWS: " + news);
			
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		} catch (SAXException e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info(news);
		return news;
	}
	
	
	public List<String> getLatestRefliefWebNews(String iso3Code, int entries) {
		
		List<String> news = new ArrayList<String>();
		
		try {
			
			URL url = new URL("http://www.reliefweb.int/RWFeed/Feed?Type=RSS20&ID=02-P&cc=" + iso3Code);
			InputStream is = url.openStream();
			
			DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(dis);
			Element element = document.getDocumentElement();
			
			
			NodeList itemTag = element.getElementsByTagName("item");
			for (int i = 0; i < itemTag.getLength(); i++) {
				if (news.size() < entries) {
					Element nodeElement = (Element) itemTag.item(i);
					NodeList titleTag = nodeElement.getElementsByTagName("title");
					String title = ((Element) titleTag.item(0)).getTextContent();
//					if (title.toLowerCase().contains("haiti")) {
						NodeList linkTag = nodeElement.getElementsByTagName("link");
						String link = ((Element) linkTag.item(0)).getTextContent();
						String newsURL = new String();
						if ( title.length() > 55) 
							newsURL = "<a href='" + link + "'>" + title.substring(0, 54) + "...</a>";
						else
							newsURL = "<a href='" + link + "'>" + title + "</a>";
						news.add(newsURL);
//					}
				} else
					break;
			}

			LOGGER.info("NEWS: " + news);
			
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		} catch (SAXException e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info(news);
		return news;
	}
	
	
	public List<String> getLatestRefliefWebNews(String gaulLabel, String iso3Code, int entries) {
		
		List<String> news = new ArrayList<String>();
		
		try {
			
			URL url = new URL("http://www.reliefweb.int/RWFeed/Feed?Type=RSS20&ID=02-P&cc=hti");
			InputStream is = url.openStream();
			
			DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(dis);
			Element element = document.getDocumentElement();
			
			
			NodeList itemTag = element.getElementsByTagName("item");
			for (int i = 0; i < itemTag.getLength(); i++) {
				if (news.size() < entries) {
					Element nodeElement = (Element) itemTag.item(i);
					NodeList titleTag = nodeElement.getElementsByTagName("title");
					String title = ((Element) titleTag.item(0)).getTextContent();
					if (title.toLowerCase().contains("haiti")) {
						NodeList linkTag = nodeElement.getElementsByTagName("link");
						String link = ((Element) linkTag.item(0)).getTextContent();
						String newsURL = new String();
						if ( title.length() > 55) 
							newsURL = "<a href='" + link + "'>" + title.substring(0, 54) + "...</a>";
						else
							newsURL = "<a href='" + link + "'>" + title + "</a>";
						news.add(newsURL);
					}
				} else
					break;
			}

			
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (ParserConfigurationException e) {
			LOGGER.error(e.getMessage());
		} catch (SAXException e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info(news);
		return news;
	}
	
	
	private String getIrinCountry(String link){
		String irinCountry = new String();		
		irinCountry =  link.replace("http://www.irinnews.org/RSS/", "");	
		irinCountry =  irinCountry.replace(".xml", "");
		irinCountry =  irinCountry.replace("_", " ");
		irinCountry = irinCountry.toLowerCase();
//		LOGGER.info("irinCountry: " + irinCountry);
		return irinCountry;
	}
}
