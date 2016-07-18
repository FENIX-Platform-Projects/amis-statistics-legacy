/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.sldeditor.server;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.map.layer.SLDImporter;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.sldeditor.common.services.SLDEditorService;
import org.fao.fenix.web.modules.sldeditor.common.vo.ColorMapSmallGrid;
import org.fao.fenix.web.modules.sldeditor.common.vo.ColorMapBigGrid;
import org.fao.fenix.web.modules.sldeditor.common.vo.Interval;
import org.fao.fenix.web.modules.sldeditor.common.vo.RGBContrast;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorConstants;
import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SLDEditorServiceImpl extends RemoteServiceServlet implements SLDEditorService {
	
	private final static String OPEN = "<?xml version='1.0' encoding='ISO-8859-1'?><StyledLayerDescriptor version='1.0.0' xsi:schemaLocation='http://www.opengis.net/sld StyledLayerDescriptor.xsd' xmlns='http://www.opengis.net/sld' xmlns:ogc='http://www.opengis.net/ogc' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>\n";
	
	private final static String CLOSE = "</StyledLayerDescriptor>";
	
	private static final Logger LOGGER = Logger.getLogger(SLDEditorServiceImpl.class);
	
	private String dir;
	
	public SLDEditorServiceImpl(Resource resource) {
		try {
			setDir(resource.getFile());
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public String[] createSLD(String sldName, String sldDescription, List<SLDEditorRuleVO> vos) throws FenixGWTException {
		String fileInfo[]= new String [2];
		String filename = UUID.randomUUID().toString() + ".sld";
		String sldPath = dir + File.separator + filename;
		if(vos.get(0).createInterval)
		{
			createFillPolygon(vos);		
		}
		String sldContent = createSLDContent(sldName, sldDescription, vos).toString();
		File sldFile = writeFile(sldPath, sldContent);
		LOGGER.info("SLD File @ " + sldFile.getAbsolutePath());
		fileInfo[0] = filename;
		fileInfo[1] =  dir + File.separator;
		return fileInfo;
	}
	
	public String[] createSLDRaster(String sldName, String sldDescription, List<SLDEditorRuleVO> vos) throws FenixGWTException {
		String fileInfo[]= new String [2];
		String filename = UUID.randomUUID().toString() + ".sld";
		String sldPath = dir + File.separator + filename;
		String sldContent = createSLDContentRaster(sldName, sldDescription, vos).toString();
		File sldFile = writeFile(sldPath, sldContent);
		LOGGER.info("SLD File @ " + sldFile.getAbsolutePath());
		fileInfo[0] = filename;
		fileInfo[1] =  dir + File.separator;
		return fileInfo;
	}
	
	public List<SLDEditorRuleVO> createSLDeditor(String pathFile, String nameFile) throws FenixGWTException
	{
		List<SLDEditorRuleVO> vos = null;
		 try {
			 File file;
			 if(nameFile.contains(".sld"))
	    	  {
	    		 file = new File(pathFile+ File.separator + nameFile);
	    	  }
	    	  else
	    	  {
	    		  file = new File(pathFile+ File.separator + nameFile+".sld");
	    	  }
   		  String path = pathFile+ File.separator + nameFile;
			FileInputStream streamInput = new FileInputStream(file);
			byte byteStream[] = new byte[1024];
			String streamOutput= "";
			String streamApp = "";
			int len=0;
			while((len = streamInput.read(byteStream)) > 0)
			{
				streamApp = new String(byteStream,0,len);
				streamOutput = streamOutput+ streamApp;
				byteStream = new byte[1024];
			}
			streamInput.close();  
			vos = parsingSLDfile(streamOutput);
		}
		 catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return vos;
	}
	
	private List<SLDEditorRuleVO> parsingSLDfile(String streamOutput) {
		List<SLDEditorRuleVO> vos = null;
		
		if(streamOutput.contains("RasterSymbolizer"))
		{
			vos = parsingSLDfileRaster(streamOutput);
		}
		else
		{
			vos = parsingSLDfileShape(streamOutput);
		}
		return vos;
	}


	private List<SLDEditorRuleVO> parsingSLDfileRaster(String streamOutput) {
		List<SLDEditorRuleVO> vos = new ArrayList<SLDEditorRuleVO>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		SLDEditorRuleVO vo = null; 
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(streamOutput.getBytes()));
		
			Node radix = document.getChildNodes().item(0);
			Node rule;
			vo = new SLDEditorRuleVO();
			Node userLayer = getFirstElementNode(radix, "UserLayer");
			if(userLayer == null)
			{
				userLayer = getFirstElementNode(radix, "NamedLayer");
			}
						
			vo.setNameUserLayer(searchNode(userLayer, "Name"));
			
			Node userStyle = getFirstElementNode(userLayer, "UserStyle");
			
			if(vo.getNameUserLayer().equals(""))
			{
				vo.setNameUserLayer(searchNode(userStyle, "Name"));
			}
			
			vo.setTitleUserStyle(searchNode(userStyle, "Title"));
				
			vo.setAbstractUserStyle(searchNode(userStyle, "Abstract"));
			
			vo.setTypeSld("raster");
			
			vos.add(vo);
			
			Node featureTypeStyle = getFirstElementNode(userStyle, "FeatureTypeStyle");
			
			int i=0, j=0;
			while((rule = getFirstElementNode(featureTypeStyle, "Rule")) != null)
			{
				vo = new SLDEditorRuleVO();
				vo.setTypeSld("raster");
				
				Node raster = getFirstElementNode(rule, "RasterSymbolizer");
				NodeList rasterList = raster.getChildNodes();
				Node rasterChild = null;
				for(j=0; j<rasterList.getLength(); j++)
				{
					rasterChild = rasterList.item(j);

			          if (rasterChild.getNodeType() == Node.ELEMENT_NODE) {
			        	  String nodeNameApp = rasterChild.getNodeName();
			        	  if(nodeNameApp.contains(":"))
			        	  {
			        		  nodeNameApp = nodeNameApp.substring(nodeNameApp.indexOf(":") +1);
			        	  }
			        	  
			        	  if(nodeNameApp.equals("Opacity"))
			        	  {
			        		  parseRasterSliderOpacity(rasterChild, vo);
			        	  }  
			        	  else if(nodeNameApp.equals("ColorMap"))
			        	  {
			        		  parseRasterColorMap(rasterChild, vo);
			        	  }  
			        	  else if(nodeNameApp.equals("ChannelSelection"))
			        	  {
			        		  parseRasterSelectionChannel(rasterChild, vo);
			        	  }
			        	  else if(nodeNameApp.equals("ContrastEnhancement"))
			        	  {
			        		  parseRasterContrastEnhancement(rasterChild, vo);
			        	  }   
			        	  rasterChild.getParentNode().removeChild(rasterChild); 
			          }
				}
				rule.getParentNode().removeChild(rule);
				i++;
				vos.add(vo);
			}					
			
		} catch (ParserConfigurationException e) {
			//System.out.println("SldEditorServiceImpl parsingSLDfile ParserConfigurationException");
		}
		catch (SAXException e) {
			//System.out.println("SldEditorServiceImpl parsingSLDfile SAXException");
			e.printStackTrace();
		} catch (IOException e) {
			//System.out.println("SldEditorServiceImpl parsingSLDfile IOException");
			e.printStackTrace();
		}
		return vos;
		
	}

	private List<SLDEditorRuleVO> parsingSLDfileShape(String streamOutput) {
		List<SLDEditorRuleVO> vos = new ArrayList<SLDEditorRuleVO>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		SLDEditorRuleVO vo = null; 
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(streamOutput.getBytes()));
		
			Node radix = document.getChildNodes().item(0);
			Node rule, shape, filter, propertyType, lowerBoundary, upperBoundary, propertyApp;
			
			String color="";
			double min = 0;
			double max = min;
			boolean firstPolygonSymb = true;
			List<Interval> intervals = null;
			
			boolean fillParametersIntervalsFirst = true;
			boolean polygon = false;
			boolean polygonTurn = false;
			SLDEditorRuleVO voPolyg = null;			
			vo = new SLDEditorRuleVO();
			Node userLayer = getFirstElementNode(radix, "UserLayer");
			if(userLayer == null)
			{
				userLayer = getFirstElementNode(radix, "NamedLayer");
			}
						
			vo.setNameUserLayer(searchNode(userLayer, "Name"));
			
			Node userStyle = getFirstElementNode(userLayer, "UserStyle");
			
			if(vo.getNameUserLayer().equals(""))
			{
				vo.setNameUserLayer(searchNode(userStyle, "Name"));
			}
			
			vo.setTitleUserStyle(searchNode(userStyle, "Title"));
			
			vo.setAbstractUserStyle(searchNode(userStyle, "Abstract"));
			
			vo.setTypeSld("shapefile");
			
			Node featureTypeStyle = getFirstElementNode(userStyle, "FeatureTypeStyle");
			
			vos.add(vo);
			
			int i=0, j=0;
			while((rule = getFirstElementNode(featureTypeStyle, "Rule")) != null)
			{
				polygonTurn = false;
				vo = new SLDEditorRuleVO();
				vo.setTypeSld("shapefile");

				if((shape = getFirstElementNode(rule, "LineSymbolizer")) != null)
				{
					polygonTurn = false;
					vo.setType(SLDEditorConstants.LINE);
					NodeList shapeList = shape.getChildNodes();
					Node shapeChild = null;
					for(j=0; j<shapeList.getLength(); j++)
					{
						shapeChild = shapeList.item(j);
						
						 if (shapeChild.getNodeType() == Node.ELEMENT_NODE) {
				        	 String nodeNameApp = shapeChild.getNodeName();
				        	  if(nodeNameApp.contains(":"))
				        	  {
				        		  nodeNameApp = nodeNameApp.substring(nodeNameApp.indexOf(":") +1);
				        	  }
				        	  
				        	  if(nodeNameApp.equals("Stroke"))
				        	  {
				        		  parseShapeLineStroke(shapeChild, vo);
				        		  shapeChild.getParentNode().removeChild(shapeChild); 
				        	  }  
						 }
					}
					if((filter = getFirstElementNode(rule, "Filter")) != null)
					{
						if((propertyType = getFirstElementNode(filter, "PropertyIsLessThan")) != null)
						{
							vo.setFilterPropertyName(searchNode(propertyType, "PropertyName"));
							vo.setFilterPropertyValue(searchNode(propertyType, "Literal"));
							vo.setIs(true);
							vo.setComparison(SLDEditorConstants.valueOf("PropertyIsLessThan"));
						}
						else if((propertyType = getFirstElementNode(filter, "PropertyIsEqualTo")) != null)
						{
							vo.setFilterPropertyName(searchNode(propertyType, "PropertyName"));
							vo.setFilterPropertyValue(searchNode(propertyType, "Literal"));
							vo.setIs(true);
							vo.setComparison(SLDEditorConstants.valueOf("PropertyIsEqualTo"));
						}
						else if((propertyType = getFirstElementNode(filter, "PropertyIsGreaterThan")) != null)
						{
							vo.setFilterPropertyName(searchNode(propertyType, "PropertyName"));
							vo.setFilterPropertyValue(searchNode(propertyType, "Literal"));
							vo.setIs(true);
							vo.setComparison(SLDEditorConstants.valueOf("PropertyIsGreaterThan"));
						}
						else if((propertyType = getFirstElementNode(filter, "Not")) != null)
						{
							if((propertyApp = getFirstElementNode(propertyType, "PropertyIsLessThan")) != null)
							{
								vo.setFilterPropertyName(searchNode(propertyApp, "PropertyName"));
								vo.setFilterPropertyValue(searchNode(propertyApp, "Literal"));
								vo.setIs(false);
								vo.setComparison(SLDEditorConstants.valueOf("PropertyIsLessThan"));
							}
							else if((propertyApp = getFirstElementNode(propertyType, "PropertyIsEqualTo")) != null)
							{
								vo.setFilterPropertyName(searchNode(propertyApp, "PropertyName"));
								vo.setFilterPropertyValue(searchNode(propertyApp, "Literal"));
								vo.setIs(false);
								vo.setComparison(SLDEditorConstants.valueOf("PropertyIsEqualTo"));
							}
							else if((propertyApp = getFirstElementNode(propertyType, "PropertyIsGreaterThan")) != null)
							{
								vo.setFilterPropertyName(searchNode(propertyApp, "PropertyName"));
								vo.setFilterPropertyValue(searchNode(propertyApp, "Literal"));
								vo.setIs(false);
								vo.setComparison(SLDEditorConstants.valueOf("PropertyIsGreaterThan"));
							}
						}						
						filter.getParentNode().removeChild(filter); 
					}
					rule.getParentNode().removeChild(rule); 
				}
				else if((shape = getFirstElementNode(rule, "TextSymbolizer")) != null)
				{
					polygonTurn = false;
					vo.setType(SLDEditorConstants.LABEL);
					NodeList shapeList = shape.getChildNodes();
					Node shapeChild = null;
					for(j=0; j<shapeList.getLength(); j++)
					{
						shapeChild = shapeList.item(j);
						
						 if (shapeChild.getNodeType() == Node.ELEMENT_NODE) {
				        	  String nodeNameApp = shapeChild.getNodeName();
				        	  if(nodeNameApp.contains(":"))
				        	  {
				        		  nodeNameApp = nodeNameApp.substring(nodeNameApp.indexOf(":") +1);
				        	  }
				        	  
				        	  if(nodeNameApp.equals("Label"))
				        	  {
				        		  parseShapeLabelLabel(shapeChild, vo);
				        	  }  				        	  
				        	  if(nodeNameApp.equals("Font"))
				        	  {
				        		  parseShapeLabelFont(shapeChild, vo);
				        	  }  
				        	  if(nodeNameApp.equals("Fill"))
				        	  {
				        		  parseShapeLabelFill(shapeChild, vo);
				        	  }  
				        	  shapeChild.getParentNode().removeChild(shapeChild); 
						 }
					}
					if((filter = getFirstElementNode(rule, "Filter")) != null)
					{
						if((propertyType = getFirstElementNode(filter, "PropertyIsLessThan")) != null)
						{
							vo.setFilterPropertyName(searchNode(propertyType, "PropertyName"));
							vo.setFilterPropertyValue(searchNode(propertyType, "Literal"));
							vo.setIs(true);
							vo.setComparison(SLDEditorConstants.valueOf("PropertyIsLessThan"));
						}
						else if((propertyType = getFirstElementNode(filter, "PropertyIsEqualTo")) != null)
						{
							vo.setFilterPropertyName(searchNode(propertyType, "PropertyName"));
							vo.setFilterPropertyValue(searchNode(propertyType, "Literal"));
							vo.setIs(true);
							vo.setComparison(SLDEditorConstants.valueOf("PropertyIsEqualTo"));
						}
						else if((propertyType = getFirstElementNode(filter, "PropertyIsGreaterThan")) != null)
						{
							vo.setFilterPropertyName(searchNode(propertyType, "PropertyName"));
							vo.setFilterPropertyValue(searchNode(propertyType, "Literal"));
							vo.setIs(true);
							vo.setComparison(SLDEditorConstants.valueOf("PropertyIsGreaterThan"));
						}
						else if((propertyType = getFirstElementNode(filter, "Not")) != null)
						{
							if((propertyApp = getFirstElementNode(propertyType, "PropertyIsLessThan")) != null)
							{
								vo.setFilterPropertyName(searchNode(propertyApp, "PropertyName"));
								vo.setFilterPropertyValue(searchNode(propertyApp, "Literal"));
								vo.setIs(false);
								vo.setComparison(SLDEditorConstants.valueOf("PropertyIsLessThan"));
							}
							else if((propertyApp = getFirstElementNode(propertyType, "PropertyIsEqualTo")) != null)
							{
								vo.setFilterPropertyName(searchNode(propertyApp, "PropertyName"));
								vo.setFilterPropertyValue(searchNode(propertyApp, "Literal"));
								vo.setIs(false);
								vo.setComparison(SLDEditorConstants.valueOf("PropertyIsEqualTo"));
							}
							else if((propertyApp = getFirstElementNode(propertyType, "PropertyIsGreaterThan")) != null)
							{
								vo.setFilterPropertyName(searchNode(propertyApp, "PropertyName"));
								vo.setFilterPropertyValue(searchNode(propertyApp, "Literal"));
								vo.setIs(false);
								vo.setComparison(SLDEditorConstants.valueOf("PropertyIsGreaterThan"));
							}
						}
						filter.getParentNode().removeChild(filter); 
					}
					rule.getParentNode().removeChild(rule); 
				}
				else if((shape = getFirstElementNode(rule, "PolygonSymbolizer")) != null)
				{
					polygonTurn = true;
					if(fillParametersIntervalsFirst)
					{
						intervals = new ArrayList<Interval>();
						polygon = true;
						fillParametersIntervalsFirst = false;
						voPolyg = new SLDEditorRuleVO();
					}
					color="";
					min = 0;
					max = min;
					NodeList shapeList = shape.getChildNodes();
					Node shapeChild = null;
					
					for(j=0; j<shapeList.getLength(); j++)
					{
						shapeChild = shapeList.item(j);
						
						 if (shapeChild.getNodeType() == Node.ELEMENT_NODE) {
				        	  String nodeNameApp = shapeChild.getNodeName();
				        	  if(nodeNameApp.contains(":"))
				        	  {
				        		  nodeNameApp = nodeNameApp.substring(nodeNameApp.indexOf(":") +1);
				        	  }
				 
				        	  if(nodeNameApp.equals("Fill"))
				        	  {
				        		  color = parseShapePolygonFill(shapeChild, voPolyg);
				        		  shapeChild.getParentNode().removeChild(shapeChild); 
				        	  }  
						 }
					}
					if((filter = getFirstElementNode(rule, "Filter")) != null)
					{
						if((propertyType = getFirstElementNode(filter, "PropertyIsLessThan")) != null)
						{
							voPolyg.setLabelIntervalPropertyName(searchNode(propertyType, "PropertyName"));
							min = 0;
							max = new Double(searchNode(propertyType, "Literal"));
							intervals.add(new Interval(min, max, color));
						}
						else if((propertyType = getFirstElementNode(filter, "PropertyIsGreaterThan")) != null)
						{
							voPolyg.setLabelIntervalPropertyName(searchNode(propertyType, "PropertyName"));
							min = new Double(searchNode(propertyType, "Literal"));
							max = -1;
							intervals.add(new Interval(min, max, color));
						}
						else if((propertyType = getFirstElementNode(filter, "PropertyIsBetween")) != null)
						{
							voPolyg.setLabelIntervalPropertyName(searchNode(propertyType, "PropertyName"));
							lowerBoundary = getFirstElementNode(propertyType, "LowerBoundary");
							min = new Double(searchNode(lowerBoundary, "Literal"));
							
							upperBoundary = getFirstElementNode(propertyType, "UpperBoundary");
							max = new Double(searchNode(upperBoundary, "Literal"));
							intervals.add(new Interval(min, max, color));
						}						
						
						if((propertyType = getFirstElementNode(filter, "PropertyIsEqualTo")) != null)
						{
							voPolyg.setFilterPropertyName(searchNode(propertyType, "PropertyName"));
							voPolyg.setFilterPropertyValue(searchNode(propertyType, "Literal"));
							voPolyg.setIs(true);
							voPolyg.setComparison(SLDEditorConstants.valueOf("PropertyIsEqualTo"));
						}
						else if((propertyType = getFirstElementNode(filter, "Not")) != null)
						{
							if((propertyApp = getFirstElementNode(propertyType, "PropertyIsEqualTo")) != null)
							{
								voPolyg.setFilterPropertyName(searchNode(propertyApp, "PropertyName"));
								voPolyg.setFilterPropertyValue(searchNode(propertyApp, "Literal"));
								voPolyg.setIs(false);
								voPolyg.setComparison(SLDEditorConstants.valueOf("PropertyIsEqualTo"));
							}
						}
						filter.getParentNode().removeChild(filter); 
					}
					rule.getParentNode().removeChild(rule); 
				}
				i++;
				if(!polygonTurn)
				{
					vos.add(vo);
				}
			}
			if(polygon)
			{
				vo = new SLDEditorRuleVO();
				vo.setTypeSld("shapefile");
				vo.setType(SLDEditorConstants.BACKGROUND);
				vo.setNameUserLayer(vos.get(0).getNameUserLayer());
				vo.setTitleUserStyle(vos.get(0).getTitleUserStyle());
				vo.setAbstractUserStyle(vos.get(0).getAbstractUserStyle());
				vo.setFillParametersIntervals(intervals);
				vo.setMinTot(intervals.get(0).getMinValue());
				vo.setMaxTot(intervals.get(intervals.size()-1).getMaxValue());
				vo.setMinColor(intervals.get(0).getColor());
				vo.setMaxColor(intervals.get(intervals.size()-1).getColor());
				vo.setLabelIntervalPropertyName(voPolyg.getLabelIntervalPropertyName());
				vo.setFilterPropertyName(voPolyg.getFilterPropertyName());
				vo.setFilterPropertyValue(voPolyg.getFilterPropertyValue());
				vo.setIs(voPolyg.isIs());
				voPolyg.setComparison(SLDEditorConstants.valueOf("PropertyIsEqualTo"));
				
				vos.add(1, vo);	
				vos.remove(0);
			}			
		} catch (ParserConfigurationException e) {
			//System.out.println("SldEditorServiceImpl parsingSLDfile ParserConfigurationException");
		}
		catch (SAXException e) {
			//System.out.println("SldEditorServiceImpl parsingSLDfile SAXException");
			e.printStackTrace();
		} catch (IOException e) {
			//System.out.println("SldEditorServiceImpl parsingSLDfile IOException");
			e.printStackTrace();
		}
		return vos;
	}


	public String saveSLD(String pathFile, String nameFile, String boxValuePath, String boxValueName)throws FenixGWTException 
	{
		  try {
    		  String path = pathFile+ File.separator + nameFile;
	    	  File sld = new File(path);
	    	  File file;
	    	  if(boxValueName.contains(".sld"))
	    	  {
	    		  file = new File(boxValuePath+ File.separator + boxValueName);
	    	  }
	    	  else
	    	  {
	    		  file = new File(boxValuePath+ File.separator + boxValueName+".sld");
	    	  }
    		  sld.createNewFile();
			writeFile(file, sld);
		} catch (IOException e) {
			//System.out.println("SldEditorServiceImpl saveSLD FileNotFoundException");
		}
		return null;
	}
	
	public String uploadSLD(String nameFile, String pathFile, String nameStyle)throws FenixGWTException 
	{
		try {
			FileInputStream streamInput = new FileInputStream(new File(pathFile+ File.separator + nameFile));
			byte byteStream[] = new byte[1024];
			String streamOutput= "";
			String streamApp = "";
			int len=0;
			while((len = streamInput.read(byteStream)) > 0)
			{
				streamApp = new String(byteStream,0,len);
				streamOutput = streamOutput+ streamApp;
				byteStream = new byte[1024];
			}
			SLDImporter.UploadStyleInGS(nameStyle, streamOutput);
			streamInput.close();
			
		} catch (FileNotFoundException e) {			
			//System.out.println("SldEditorServiceImpl uploadSLD FileNotFoundException");
		}
		catch (IOException e) {
			//System.out.println("SldEditorServiceImpl uploadSLD FileNotFoundException");
		}
		return null;
	}
		
	public static File writeFile(File sldOutput, File sldInput)
	{
		try {
			FileInputStream streamInput = new FileInputStream(sldInput);
			byte byteStream[] = new byte[1024];
			FileOutputStream streamOutput = new FileOutputStream(sldOutput);
			int len=0;
			while((len = streamInput.read(byteStream)) > 0)
			{
				streamOutput.write(byteStream, 0, len);
				byteStream = new byte[1024];
			}
			streamOutput.close();
			streamInput.close();
		} catch (IOException e) {
			//System.out.println("SldEditorServiceImpl writeFile FileNotFoundException");	
			}
		return sldOutput;
	}
	
	
	private void createFillPolygon(List<SLDEditorRuleVO> vos) {
		Map<String, String> m = new LinkedHashMap<String, String>();	
		int i=0, j=0;
		for(i=0; i<vos.size(); i++)
		{
			if(vos.get(i).getFillParametersIntervals() != null)
			{
			SLDEditorRuleVO vo = vos.get(i);
			List<Interval> intervalList = vo.getFillParametersIntervals();
			for(j=0; j < (intervalList.size()); j++)
			{
				m.put(String.valueOf(intervalList.get(j).getMinValue()), intervalList.get(j).getColor());
			}
			m.put(String.valueOf(intervalList.get(j-1).getMaxValue()), intervalList.get(j-1).getColor());
			vos.get(i).setFillParameters(m);
			}
		}
	}


	private File writeFile(String sldPath, String sldContent) throws FenixGWTException {
		try {
			File file = new File(sldPath);
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(sldContent.getBytes());
			stream.close();
			return file;
		} catch (IOException e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	private StringBuilder createSLDContent(String sldName, String sldDescription, List<SLDEditorRuleVO> vos) throws FenixGWTException {
		int i=0;
		String firstKey = null;
		StringBuilder sb = new StringBuilder();
		sb.append(OPEN);
		sb.append("<NamedLayer>\n");
		if(sldName == null)
		{
			sldName = "";
		}
		sb.append("<Name>").append(sldName).append("</Name>\n");
		sb.append("<UserStyle>\n");
		sb.append("<Title>").append(sldName).append("</Title>\n");
		if(sldDescription == null)
		{
			sldDescription = "";
		}
		sb.append("<Abstract>").append(sldDescription).append("</Abstract>\n");
		sb.append("<FeatureTypeStyle>\n");
		for (SLDEditorRuleVO vo : vos) {
			switch (vo.getType()) {
				case LINE:
					sb.append("<Rule>\n");
					sb.append("<Name>").append(UUID.randomUUID().toString()).append("</Name>\n");
					sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
					if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
						sb.append("<ogc:Filter>\n");
						if (vo.isIs() == false)
							sb.append("<ogc:Not>\n");
						sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
						sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
						sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
						sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
						if (vo.isIs() == false)
							sb.append("</ogc:Not>\n");
						sb.append("</ogc:Filter>\n");
					}
					sb.append("<LineSymbolizer>\n");
					sb.append("<Stroke>\n");
					for (String cssParameter : vo.getStrokeParameters().keySet())
						sb.append("<CssParameter name='").append(cssParameter).append("'>").append(vo.getStrokeParameters().get(cssParameter)).append("</CssParameter>\n");
					sb.append("</Stroke>\n");
					sb.append("</LineSymbolizer>\n");
					sb.append("</Rule>\n");
				break;
				case LABEL:
					sb.append("<Rule>\n");
					sb.append("<Name>").append(UUID.randomUUID().toString()).append("</Name>\n");
					sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
					if (!vo.getFontParameters().isEmpty()) {
						if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
							sb.append("<ogc:Filter>\n");
							if (vo.isIs() == false)
								sb.append("<ogc:Not>\n");
							sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
							sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
							sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
							sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
							if (vo.isIs() == false)
								sb.append("</ogc:Not>\n");
							sb.append("</ogc:Filter>\n");
						}
						sb.append("<TextSymbolizer>\n");
						sb.append("<Label>").append("<ogc:PropertyName>").append(vo.getLabelPropertyName()).append("</ogc:PropertyName>").append("</Label>\n");
						sb.append("<Font>\n");
						for (String fontProperty : vo.getFontParameters().keySet())
							if (!fontProperty.contains("fill"))
								sb.append("<CssParameter name='").append(fontProperty).append("'>").append(vo.getFontParameters().get(fontProperty)).append("</CssParameter>\n");
						sb.append("</Font>\n");
						sb.append("<Fill>\n");
						for (String fontProperty : vo.getFontParameters().keySet())
							if (fontProperty.contains("fill"))
								sb.append("<CssParameter name='").append(fontProperty).append("'>").append(vo.getFontParameters().get(fontProperty)).append("</CssParameter>\n");
						sb.append("</Fill>\n");
						sb.append("</TextSymbolizer>\n");
						sb.append("</Rule>\n");
					}
				break;
				case BACKGROUND:
					String extremeValues = vo.getExtremeValues();
					if(extremeValues.equalsIgnoreCase("lowerValuesIncluded"))
					{
						i=0;
						for (String key : vo.getFillParameters().keySet()) {
							if(i == 0)
							{
								firstKey = key;
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								sb.append("<ogc:Filter>\n");								
								sb.append("<ogc:PropertyIsLessThan>\n");
								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
								sb.append("<ogc:Literal>").append(key).append("</ogc:Literal>\n");
								sb.append("</ogc:PropertyIsLessThan>\n");
								sb.append("</ogc:Filter>\n");
								
								sb.append("<PolygonSymbolizer>\n");
								sb.append("<Fill>\n");
								if (vo.getFillParameters().get(key).contains("#"))
									sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								else
									sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								sb.append("</Fill>\n");
								sb.append("</PolygonSymbolizer>\n");
								sb.append("</Rule>\n");
								
								if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
									sb.append("<Rule>\n");
									sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								
									sb.append("<ogc:Filter>\n");
									if (vo.isIs() == false)
										sb.append("<ogc:Not>\n");
									sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
									sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
									sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
									sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
									if (vo.isIs() == false)
										sb.append("</ogc:Not>\n");
									sb.append("</ogc:Filter>\n");
									
									sb.append("<PolygonSymbolizer>\n");
									sb.append("<Fill>\n");
									if (vo.getFillParameters().get(key).contains("#"))
										sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									else
										sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									sb.append("</Fill>\n");
									sb.append("</PolygonSymbolizer>\n");
									sb.append("</Rule>\n");
								}
							}
							if(i > 0)
							{
								// This is the first element
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								sb.append("<ogc:Filter>\n");
//								sb.append("<ogc:And>\n");
								
								sb.append("<ogc:PropertyIsBetween>\n");
								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
								sb.append("<ogc:LowerBoundary>\n");
								sb.append("<ogc:Literal>").append(firstKey).append("</ogc:Literal>\n");
								sb.append("</ogc:LowerBoundary>\n");
								sb.append("<ogc:UpperBoundary>\n");
								sb.append("<ogc:Literal>").append(key).append("</ogc:Literal>\n");
								sb.append("</ogc:UpperBoundary>\n");
								sb.append("</ogc:PropertyIsBetween>\n");
								
//								sb.append("<ogc:PropertyIsGreaterThan>\n");
//								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
//								sb.append("<ogc:Literal>").append(min).append("</ogc:Literal>\n");
//								sb.append("</ogc:PropertyIsGreaterThan>\n");
								
//								sb.append("</ogc:And>\n");
								sb.append("</ogc:Filter>\n");
							
								sb.append("<PolygonSymbolizer>\n");
								sb.append("<Fill>\n");
								if (vo.getFillParameters().get(key).contains("#"))
									sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								else
									sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								sb.append("</Fill>\n");
								sb.append("</PolygonSymbolizer>\n");
								sb.append("</Rule>\n");
								
								if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								
									sb.append("<ogc:Filter>\n");
									if (vo.isIs() == false)
										sb.append("<ogc:Not>\n");
									sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
									sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
									sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
									sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
									if (vo.isIs() == false)
										sb.append("</ogc:Not>\n");
									sb.append("</ogc:Filter>\n");
									
									sb.append("<PolygonSymbolizer>\n");
									sb.append("<Fill>\n");
									if (vo.getFillParameters().get(key).contains("#"))
										sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									else
										sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									sb.append("</Fill>\n");
									sb.append("</PolygonSymbolizer>\n");
									sb.append("</Rule>\n");
								}
								firstKey = key;
							}
								i++;
						}
					}
					else if(extremeValues.equalsIgnoreCase("higherValuesIncluded"))
					{
						i=0;
						String last = null;
						for (String key : vo.getFillParameters().keySet()) {
							if(i == 0)
							{
								firstKey = key;
								last = key;
							}
							if(i > 0)
							{
								// This is the first element
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								sb.append("<ogc:Filter>\n");
//								sb.append("<ogc:And>\n");
								
								sb.append("<ogc:PropertyIsBetween>\n");
								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
								sb.append("<ogc:LowerBoundary>\n");
								sb.append("<ogc:Literal>").append(firstKey).append("</ogc:Literal>\n");
								sb.append("</ogc:LowerBoundary>\n");
								sb.append("<ogc:UpperBoundary>\n");
								sb.append("<ogc:Literal>").append(key).append("</ogc:Literal>\n");
								sb.append("</ogc:UpperBoundary>\n");
								sb.append("</ogc:PropertyIsBetween>\n");
								
//								sb.append("<ogc:PropertyIsGreaterThan>\n");
//								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
//								sb.append("<ogc:Literal>").append(min).append("</ogc:Literal>\n");
//								sb.append("</ogc:PropertyIsGreaterThan>\n");
								
//								sb.append("</ogc:And>\n");
								sb.append("</ogc:Filter>\n");
							
								sb.append("<PolygonSymbolizer>\n");
								sb.append("<Fill>\n");
								if (vo.getFillParameters().get(key).contains("#"))
									sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								else
									sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								sb.append("</Fill>\n");
								sb.append("</PolygonSymbolizer>\n");
								sb.append("</Rule>\n");
								
								if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								
									sb.append("<ogc:Filter>\n");
									if (vo.isIs() == false)
										sb.append("<ogc:Not>\n");
									sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
									sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
									sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
									sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
									if (vo.isIs() == false)
										sb.append("</ogc:Not>\n");
									sb.append("</ogc:Filter>\n");
									
									sb.append("<PolygonSymbolizer>\n");
									sb.append("<Fill>\n");
									if (vo.getFillParameters().get(key).contains("#"))
										sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									else
										sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									sb.append("</Fill>\n");
									sb.append("</PolygonSymbolizer>\n");
									sb.append("</Rule>\n");
								}
								
								firstKey = key;
								last = key;
							}
								i++;
						}
						if(last != null)
						{
							sb.append("<Rule>\n");
							sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
							sb.append("<ogc:Filter>\n");								
							sb.append("<ogc:PropertyIsGreaterThan>\n");
							sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
							sb.append("<ogc:Literal>").append(last).append("</ogc:Literal>\n");
							sb.append("</ogc:PropertyIsGreaterThan>\n");
							sb.append("</ogc:Filter>\n");
							
							sb.append("<PolygonSymbolizer>\n");
							sb.append("<Fill>\n");
							if (vo.getFillParameters().get(last).contains("#"))
								sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(last)).append("</CssParameter>\n");
							else
								sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(last)).append("</CssParameter>\n");
							sb.append("</Fill>\n");
							sb.append("</PolygonSymbolizer>\n");
							sb.append("</Rule>\n");
							
							if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								sb.append("<ogc:Filter>\n");
								if (vo.isIs() == false)
									sb.append("<ogc:Not>\n");
								sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
								sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
								sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
								sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
								if (vo.isIs() == false)
									sb.append("</ogc:Not>\n");
								sb.append("</ogc:Filter>\n");
								
								sb.append("<PolygonSymbolizer>\n");
								sb.append("<Fill>\n");
								if (vo.getFillParameters().get(last).contains("#"))
									sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(last)).append("</CssParameter>\n");
								else
									sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(last)).append("</CssParameter>\n");
								sb.append("</Fill>\n");
								sb.append("</PolygonSymbolizer>\n");
								sb.append("</Rule>\n");
							}
						}
						
					}
					else if(extremeValues.equalsIgnoreCase("bothValuesIncluded"))
					{
						i=0;
						String last = null;
						for (String key : vo.getFillParameters().keySet()) {
							if(i == 0)
							{
								firstKey = key;
								last= key;
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								sb.append("<ogc:Filter>\n");								
								sb.append("<ogc:PropertyIsLessThan>\n");
								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
								sb.append("<ogc:Literal>").append(key).append("</ogc:Literal>\n");
								sb.append("</ogc:PropertyIsLessThan>\n");
								sb.append("</ogc:Filter>\n");
								
								sb.append("<PolygonSymbolizer>\n");
								sb.append("<Fill>\n");
								if (vo.getFillParameters().get(key).contains("#"))
									sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								else
									sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								sb.append("</Fill>\n");
								sb.append("</PolygonSymbolizer>\n");
								sb.append("</Rule>\n");
								
								if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
									sb.append("<Rule>\n");
									sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
									sb.append("<ogc:Filter>\n");
									if (vo.isIs() == false)
										sb.append("<ogc:Not>\n");
									sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
									sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
									sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
									sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
									if (vo.isIs() == false)
										sb.append("</ogc:Not>\n");
									sb.append("</ogc:Filter>\n");
									
									sb.append("<PolygonSymbolizer>\n");
									sb.append("<Fill>\n");
									if (vo.getFillParameters().get(key).contains("#"))
										sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									else
										sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									sb.append("</Fill>\n");
									sb.append("</PolygonSymbolizer>\n");
									sb.append("</Rule>\n");
								}
							}
							if(i > 0)
							{
								// This is the first element
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								sb.append("<ogc:Filter>\n");
//								sb.append("<ogc:And>\n");
								
								sb.append("<ogc:PropertyIsBetween>\n");
								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
								sb.append("<ogc:LowerBoundary>\n");
								sb.append("<ogc:Literal>").append(firstKey).append("</ogc:Literal>\n");
								sb.append("</ogc:LowerBoundary>\n");
								sb.append("<ogc:UpperBoundary>\n");
								sb.append("<ogc:Literal>").append(key).append("</ogc:Literal>\n");
								sb.append("</ogc:UpperBoundary>\n");
								sb.append("</ogc:PropertyIsBetween>\n");
								
//								sb.append("<ogc:PropertyIsGreaterThan>\n");
//								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
//								sb.append("<ogc:Literal>").append(min).append("</ogc:Literal>\n");
//								sb.append("</ogc:PropertyIsGreaterThan>\n");
								
//								sb.append("</ogc:And>\n");
								sb.append("</ogc:Filter>\n");
							
								sb.append("<PolygonSymbolizer>\n");
								sb.append("<Fill>\n");
								if (vo.getFillParameters().get(key).contains("#"))
									sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								else
									sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
								sb.append("</Fill>\n");
								sb.append("</PolygonSymbolizer>\n");
								sb.append("</Rule>\n");
								
								if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
									sb.append("<Rule>\n");
									sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
									sb.append("<ogc:Filter>\n");
									if (vo.isIs() == false)
										sb.append("<ogc:Not>\n");
									sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
									sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
									sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
									sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
									if (vo.isIs() == false)
										sb.append("</ogc:Not>\n");
									sb.append("</ogc:Filter>\n");
									
									sb.append("<PolygonSymbolizer>\n");
									sb.append("<Fill>\n");
									if (vo.getFillParameters().get(key).contains("#"))
										sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									else
										sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(key)).append("</CssParameter>\n");
									sb.append("</Fill>\n");
									sb.append("</PolygonSymbolizer>\n");
									sb.append("</Rule>\n");
								}
								
								firstKey = key;
								last = key;
							}
								i++;
						}
						if(last != null)
						{
							sb.append("<Rule>\n");
							sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
							sb.append("<ogc:Filter>\n");								
							sb.append("<ogc:PropertyIsGreaterThan>\n");
							sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
							sb.append("<ogc:Literal>").append(last).append("</ogc:Literal>\n");
							sb.append("</ogc:PropertyIsGreaterThan>\n");
							sb.append("</ogc:Filter>\n");
							
							sb.append("<PolygonSymbolizer>\n");
							sb.append("<Fill>\n");
							if (vo.getFillParameters().get(last).contains("#"))
								sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(last)).append("</CssParameter>\n");
							else
								sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(last)).append("</CssParameter>\n");
							sb.append("</Fill>\n");
							sb.append("</PolygonSymbolizer>\n");
							sb.append("</Rule>\n");
							
							if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								sb.append("<ogc:Filter>\n");
								if (vo.isIs() == false)
									sb.append("<ogc:Not>\n");
								sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
								sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
								sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
								sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
								if (vo.isIs() == false)
									sb.append("</ogc:Not>\n");
								sb.append("</ogc:Filter>\n");
								
								sb.append("<PolygonSymbolizer>\n");
								sb.append("<Fill>\n");
								if (vo.getFillParameters().get(last).contains("#"))
									sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(last)).append("</CssParameter>\n");
								else
									sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(last)).append("</CssParameter>\n");
								sb.append("</Fill>\n");
								sb.append("</PolygonSymbolizer>\n");
								sb.append("</Rule>\n");
							}
						}
					}
					else if(extremeValues.equalsIgnoreCase("bothValuesExcluded"))
					{
						i=0;
						for (String key : vo.getFillParameters().keySet()) {
							if(i == 0)
							{
								firstKey = key;
							}
							if(i > 0)
							{
								// This is the first element
								sb.append("<Rule>\n");
								sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
								sb.append("<ogc:Filter>\n");
//								sb.append("<ogc:And>\n");
								
								sb.append("<ogc:PropertyIsBetween>\n");
								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
								sb.append("<ogc:LowerBoundary>\n");
								sb.append("<ogc:Literal>").append(firstKey).append("</ogc:Literal>\n");
								sb.append("</ogc:LowerBoundary>\n");
								sb.append("<ogc:UpperBoundary>\n");
								sb.append("<ogc:Literal>").append(key).append("</ogc:Literal>\n");
								sb.append("</ogc:UpperBoundary>\n");
								sb.append("</ogc:PropertyIsBetween>\n");
								
//								sb.append("<ogc:PropertyIsGreaterThan>\n");
//								sb.append("<ogc:PropertyName>").append(vo.getLabelIntervalPropertyName()).append("</ogc:PropertyName>\n");
//								sb.append("<ogc:Literal>").append(min).append("</ogc:Literal>\n");
//								sb.append("</ogc:PropertyIsGreaterThan>\n");
								
//								sb.append("</ogc:And>\n");
								sb.append("</ogc:Filter>\n");
							
								sb.append("<PolygonSymbolizer>\n");
								sb.append("<Fill>\n");
								if (vo.getFillParameters().get(firstKey).contains("#"))
									sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(firstKey)).append("</CssParameter>\n");
								else
									sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(firstKey)).append("</CssParameter>\n");
								sb.append("</Fill>\n");
								sb.append("</PolygonSymbolizer>\n");
								sb.append("</Rule>\n");
								
								if ((vo.getFilterPropertyValue() != null) && (!vo.getFilterPropertyValue().equals(""))) {
									sb.append("<Rule>\n");
									sb.append("<Title>").append(UUID.randomUUID().toString()).append("</Title>\n");
									sb.append("<ogc:Filter>\n");
									if (vo.isIs() == false)
										sb.append("<ogc:Not>\n");
									sb.append("<ogc:").append(vo.getComparison().name()).append(">\n");
									sb.append("<ogc:PropertyName>").append(vo.getFilterPropertyName()).append("</ogc:PropertyName>\n");
									sb.append("<ogc:Literal>").append(vo.getFilterPropertyValue()).append("</ogc:Literal>\n");
									sb.append("</ogc:").append(vo.getComparison().name()).append(">\n");
									if (vo.isIs() == false)
										sb.append("</ogc:Not>\n");
									sb.append("</ogc:Filter>\n");
									
									sb.append("<PolygonSymbolizer>\n");
									sb.append("<Fill>\n");
									if (vo.getFillParameters().get(firstKey).contains("#"))
										sb.append("<CssParameter name='fill'>").append(vo.getFillParameters().get(firstKey)).append("</CssParameter>\n");
									else
										sb.append("<CssParameter name='fill'>#").append(vo.getFillParameters().get(firstKey)).append("</CssParameter>\n");
									sb.append("</Fill>\n");
									sb.append("</PolygonSymbolizer>\n");
									sb.append("</Rule>\n");									
								}
								
								firstKey = key;
							}
								i++;
						}
					}
				break;
			}
		}
		sb.append("</FeatureTypeStyle>\n");
		sb.append("</UserStyle>\n");
		sb.append("</NamedLayer>\n");
		sb.append(CLOSE);
		LOGGER.info("\n" + sb);
		return sb;
	}
	
	private StringBuilder createSLDContentRaster(String sldName, String sldDescription, List<SLDEditorRuleVO> vos) throws FenixGWTException 
	{
		int i=0;
		String firstKey = null;
		StringBuilder sb = new StringBuilder();
		sb.append(OPEN);
		sb.append("<NamedLayer>\n");
		if(sldName == null)
		{
			sldName = "";
		}
		sb.append("<Name>").append(sldName).append("</Name>\n");
		sb.append("<UserStyle>\n");
		sb.append("<Title>").append(sldName).append("</Title>\n");
		if(sldDescription == null)
		{
			sldDescription = "";
		}
		sb.append("<Abstract>").append(sldDescription).append("</Abstract>\n");
		sb.append("<FeatureTypeStyle>\n");
		for (SLDEditorRuleVO vo : vos) {
			sb.append("<Rule>\n");
			sb.append("<RasterSymbolizer>\n");
			//Aggiungere controllo
			if(!vo.isColorMapSelected())
			{
				if(vo.getSliderOpacityOnlyMap() != null)
				{
					if((vo.getSliderOpacityOnlyMap().get("fill-opacity")) != null)
					{
						sb.append("<Opacity>").append(vo.getSliderOpacityOnlyMap().get("fill-opacity")).append("</Opacity>\n");
					}
				}
			}
			if(vo.isColorMapSelected()&&(vo.isColorMapGlobalOpacity()))
			{
				sb.append("<Opacity>").append(vo.getColorMapItem().get("fill-opacity")).append("</Opacity>\n");
			}
			
			if((vo.getChannelSelectionMap() != null)&&(vo.getChannelSelectionMap().containsKey("type-attribute")))
			{
				String channelTypeValue = vo.getChannelSelectionMap().get("type-attribute");
				sb.append("<ChannelSelection>\n");
				
				if(channelTypeValue.equals("Red/Green/Blue Channels"))
				{
					if(vo.getChannelSelectionMap().containsKey("red-channel-name"))
					{
						if((vo.getChannelSelectionMap().get("red-channel-name")) != null)
						{
						sb.append("<RedChannel>\n");
						sb.append("<SourceChannelName>").append(vo.getChannelSelectionMap().get("red-channel-name")).append("</SourceChannelName>");
						if(vo.getContrastEnhancementMap() != null)
						{
							if(vo.getContrastEnhancementMap().get("contrast-type")!= null)
							{
								if((vo.getContrastEnhancementMap().get("contrast-type")).equals("Red/Green/Blue Channel Contrast"))
								{
									int rgbIndex=0;
									for(rgbIndex=0; rgbIndex<vo.rgbcontrast.size(); rgbIndex++)
									{
										if(vo.rgbcontrast.get(rgbIndex).getnameChannel().equals("Red"))
										{
											break;
										}
									}
									if(rgbIndex != vo.rgbcontrast.size())
									{
										sb.append("<ContrastEnhancement>\n");
										String rgbType = vo.rgbcontrast.get(rgbIndex).gettypeChannelContrast();
										if(rgbType.equals("Gamma"))
										{
											sb.append("<GammaValue>").append(vo.rgbcontrast.get(rgbIndex).getgammaValueContrast()).append("</GammaValue>\n");
										}
										else
										{
											sb.append("<").append(rgbType).append("/>\n");
										}
										sb.append("</ContrastEnhancement>\n");
									}
								}
						}
					}
						sb.append("</RedChannel>\n");
					}
					}
					if(vo.getChannelSelectionMap().containsKey("green-channel-name"))
					{
						if((vo.getChannelSelectionMap().get("green-channel-name")) != null)
						{
						sb.append("<GreenChannel>\n");
						sb.append("<SourceChannelName>").append(vo.getChannelSelectionMap().get("green-channel-name")).append("</SourceChannelName>");
						if(vo.getContrastEnhancementMap() != null)
						{
							if(vo.getContrastEnhancementMap().get("contrast-type")!= null)
							{
								if((vo.getContrastEnhancementMap().get("contrast-type")).equals("Red/Green/Blue Channel Contrast"))
								{
									int rgbIndex=0;
									for(rgbIndex=0; rgbIndex<vo.rgbcontrast.size(); rgbIndex++)
									{
										if(vo.rgbcontrast.get(rgbIndex).getnameChannel().equals("Green"))
										{
											break;
										}
									}
									if(rgbIndex != vo.rgbcontrast.size())
									{
										sb.append("<ContrastEnhancement>\n");
										String rgbType = vo.rgbcontrast.get(rgbIndex).gettypeChannelContrast();
										if(rgbType.equals("Gamma"))
										{
											sb.append("<GammaValue>").append(vo.rgbcontrast.get(rgbIndex).getgammaValueContrast()).append("</GammaValue>\n");
										}
										else
										{
											sb.append("<").append(rgbType).append("/>\n");
										}
										sb.append("</ContrastEnhancement>\n");
									}
								}
							}
						}
						sb.append("</GreenChannel>\n");
						}
					}
					if(vo.getChannelSelectionMap().containsKey("blue-channel-name"))
					{
						if((vo.getChannelSelectionMap().get("blue-channel-name")) != null)
						{
						sb.append("<BlueChannel>\n");
						sb.append("<SourceChannelName>").append(vo.getChannelSelectionMap().get("blue-channel-name")).append("</SourceChannelName>");
						if((vo.getContrastEnhancementMap() != null))
						{
						if(vo.getContrastEnhancementMap().containsKey("contrast-type"))
						{
							if((vo.getContrastEnhancementMap().get("contrast-type")).equals("Red/Green/Blue Channel Contrast"))
							{
								int rgbIndex=0;
								for(rgbIndex=0; rgbIndex<vo.rgbcontrast.size(); rgbIndex++)
								{
									if(vo.rgbcontrast.get(rgbIndex).getnameChannel().equals("Blue"))
									{
										break;
									}
								}
								if(rgbIndex != vo.rgbcontrast.size())
								{
									sb.append("<ContrastEnhancement>\n");
									String rgbType = vo.rgbcontrast.get(rgbIndex).gettypeChannelContrast();
									if(rgbType.equals("Gamma"))
									{
										sb.append("<GammaValue>").append(vo.rgbcontrast.get(rgbIndex).getgammaValueContrast()).append("</GammaValue>\n");
									}
									else
									{
										sb.append("<").append(rgbType).append("/>\n");
									}
									sb.append("</ContrastEnhancement>\n");
								}
							}
						}
						}
						sb.append("</BlueChannel>\n");
						}
					}
				}
				else if(channelTypeValue.equals("Gray Channel"))
				{
					if(vo.getChannelSelectionMap().containsKey("gray-channel-name"))
					{
						if((vo.getChannelSelectionMap().get("gray-channel-name")) != null)
						{
							sb.append("<GrayChannel>\n");
							sb.append("<SourceChannelName>").append(vo.getChannelSelectionMap().get("gray-channel-name")).append("</SourceChannelName>");
							if((vo.getContrastEnhancementMap() != null))
							{
						
								if(vo.getContrastEnhancementMap().containsKey("contrast-type"))
								{
									if(vo.getContrastEnhancementMap().get("contrast-type") != null)
									{
									if((vo.getContrastEnhancementMap().get("contrast-type")).equals("Gray Channel Contrast"))
									{
										sb.append("<ContrastEnhancement>\n");
										String grayType = vo.rgbcontrast.get(0).gettypeChannelContrast();
										if(grayType.equals("Gamma"))
										{
											sb.append("<GammaValue>").append(vo.rgbcontrast.get(0).getgammaValueContrast()).append("</GammaValue>\n");
										}
										else
										{
											sb.append("<").append(grayType).append("/>\n");
										}
										sb.append("</ContrastEnhancement>\n");
									}
									}
								}
							}
							sb.append("</GrayChannel>\n");
						}
					}
				} 
				sb.append("</ChannelSelection>\n");
			}
			if((vo.getContrastEnhancementMap() != null)&&(vo.getContrastEnhancementMap().containsKey("contrast-type")))
			{
				if((vo.getContrastEnhancementMap() != null))
				{
				String selectionContrast = vo.getContrastEnhancementMap().get("contrast-type");
					if(selectionContrast != null)
					{
						if(selectionContrast.equals("Global Contrast"))
						{
							sb.append("<ContrastEnhancement>\n");
							String selectionContrast2 = vo.getContrastEnhancementMap().get("global-contrast-value");
							if(selectionContrast2.equals("Normalize")||selectionContrast2.equals("Histogram"))
							{
								sb.append("<").append(selectionContrast2).append("/>\n");
							}
							else if(selectionContrast2.equals("Gamma"))
							{
								sb.append("<GammaValue>").append(vo.getContrastEnhancementMap().get("gamma-value")).append("</GammaValue>\n");
							}
							sb.append("</ContrastEnhancement>\n");
						}
					}
				}
			}
				
			if(vo.isColorMapSelected())
			{
				String extendedBool = "";
				if(vo.getColorMapItem() != null)
				{
				if((vo.getColorMapItem().get("extended-attribute") != null))
				{
					if(vo.getColorMapItem().get("extended-attribute").equals("Normal Range Color"))
					{
						extendedBool = "false";
					}
					else 
					{
						extendedBool = "true";
					}
				}
				else
				{
					extendedBool = "false";
				}
				sb.append("<ColorMap type=\"").append(vo.getColorMapItem().get("type-attribute")).append("\" extended= \"").append(extendedBool).append("\" >\n");
				String stringOne = vo.getColorMapItem().get("color-map-items");
				if(stringOne.equals("Color Entry with Global Opacity"))
				{
					int bigIndex = 0;
					for(bigIndex=0; bigIndex <vo.getItemsSmallGrid().size(); bigIndex++)
					{
						sb.append("<ColorMapEntry color=\"").append(vo.getItemsSmallGrid().get(bigIndex).getColor()).append("\" quantity=\"").append(vo.getItemsSmallGrid().get(bigIndex).getquantity()).append("\" label=\"").append(vo.getItemsSmallGrid().get(bigIndex).getlabel()).append("\" />\n");
					}
					
				}
				else if(stringOne.equals("Color Entry with Opacity For Each Interval"))
				{
					int bigIndex = 0;
					for(bigIndex=0; bigIndex <vo.getItemsColorMapBigGrids().size(); bigIndex++)
					{
						sb.append("<ColorMapEntry color=\"").append(vo.getItemsColorMapBigGrids().get(bigIndex).getColor()).append("\" quantity=\"").append(vo.getItemsColorMapBigGrids().get(bigIndex).getquantity()).append("\" label=\"").append(vo.getItemsColorMapBigGrids().get(bigIndex).getlabel()).append("\" opacity=\"").append(vo.getItemsColorMapBigGrids().get(bigIndex).getopacity()).append("\" />\n");
					}					
				}
				else
				{
					//System.out.println("SldEditorServiceImpl Color Entry with Opacity For Each Interval not ");
				}
				sb.append("</ColorMap>\n");
			}
			}
			sb.append("</RasterSymbolizer>\n");
			sb.append("</Rule>\n");
		}
		sb.append("</FeatureTypeStyle>\n");
		sb.append("</UserStyle>\n");
		sb.append("</NamedLayer>\n");
		sb.append(CLOSE);
		LOGGER.info("\n" + sb);
		return sb;
	}
	
	private double min(Map<String, String> map) {
		double min = Double.MAX_VALUE;
		for (String key : map.keySet())
			if (Double.valueOf(key) < min)
				min = Double.valueOf(key);
		return min;
	}
	
	private double max(Map<String, String> map) {
		double max = Double.MIN_VALUE;
		for (String key : map.keySet())
			if (Double.valueOf(key) > max)
				max = Double.valueOf(key);
		return max;
	}
	
	private double delta(Map<String, String> map) {
		double delta = Math.abs(max(map) - min(map)) / map.size();
		LOGGER.info("DELTA: " + delta);
		return delta;
	}
	
	public void setDir(File dir) {
		this.dir = dir.getPath();
	}
	
	private void parseRasterContrastEnhancement(Node rasterChild, SLDEditorRuleVO vo) {
		Node contrast;
		String gammaValue;
		vo.setContrastValueType("global-contrast-value");
		if((contrast = getFirstElementNode(rasterChild, "Normalize")) != null)
		{
			vo.getContrastEnhancementMap().put("global-contrast-value", "Normalize");			
		}
		else if((contrast = getFirstElementNode(rasterChild, "Histogram")) != null) 
		{
			vo.getContrastEnhancementMap().put("global-contrast-value", "Histogram");
		}
		else if(!((gammaValue = searchNode(rasterChild, "GammaValue")).equals("")))
		{
			vo.getContrastEnhancementMap().put("global-contrast-value", "Gamma");
			vo.getContrastEnhancementMap().put("gamma-value", gammaValue);
		}
	}

	private void parseRasterSelectionChannel(Node rasterChild, SLDEditorRuleVO vo) {
		Node channel, contrast;
		String sourceNodeString, gammaValue;
		
		if((channel = getFirstElementNode(rasterChild, "RedChannel")) != null)
		{
			if(!((sourceNodeString = searchNode(channel, "SourceChannelName")).equals("")))
			{
				if(vo.getChannelSelectionMap() == null)
				{
					vo.channelSelectionMap = new HashMap<String, String>();
				
					if(vo.getChannelSelectionMap() == null)
					{
						vo.channelSelectionMap = new HashMap<String, String>();
					}
					else
					{
						//System.out.println("inside vo.getChannelSelectionMap() NOT  null");
					}
				}
				else
				{
					//System.out.println("vo.getChannelSelectionMap() NOT  null");
				}
				 vo.getChannelSelectionMap().put("red-channel-name", sourceNodeString);
			}
			if((contrast = getFirstElementNode(channel, "ContrastEnhancement")) != null)
			{
				vo.setContrastValueType("rgb-contrast-value");
				if((getFirstElementNode(contrast, "Normalize")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Red", "Normalize", 0));
				}
				else if((getFirstElementNode(contrast, "Histogram")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Red", "Histogram", 0));
				}
				else if((gammaValue = searchNode(contrast, "GammaValue")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Red", "GammaValue", new Double(gammaValue)));
				}
			
			}
		}
		if((channel = getFirstElementNode(rasterChild, "GreenChannel")) != null)
		{
			if(!(((sourceNodeString = searchNode(channel, "SourceChannelName"))).equals("")))
			{
				 vo.getChannelSelectionMap().put("green-channel-name", sourceNodeString);
			}
			if((contrast = getFirstElementNode(channel, "ContrastEnhancement")) != null)
			{
				vo.setContrastValueType("rgb-contrast-value");
				if((getFirstElementNode(contrast, "Normalize")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Green", "Normalize", 0));
				}
				else if((getFirstElementNode(contrast, "Histogram")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Green", "Histogram", 0));
				}
				else if((gammaValue = searchNode(contrast, "GammaValue")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Green", "GammaValue", new Double(gammaValue)));
				}
			}
		}
		if((channel = getFirstElementNode(rasterChild, "BlueChannel")) != null)
		{
			if(!((sourceNodeString = searchNode(channel, "SourceChannelName")).equals("")))
			{
				 vo.getChannelSelectionMap().put("blue-channel-name", sourceNodeString);
			}
			if((contrast = getFirstElementNode(channel, "ContrastEnhancement")) != null)
			{
				vo.setContrastValueType("rgb-contrast-value");
				if((getFirstElementNode(contrast, "Normalize")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Blue", "Normalize", 0));
				}
				else if((getFirstElementNode(contrast, "Histogram")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Blue", "Histogram", 0));
				}
				else if((gammaValue = searchNode(contrast, "GammaValue")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Blue", "GammaValue", new Double(gammaValue)));
				}
			}
		}
		if((channel = getFirstElementNode(rasterChild, "GrayChannel")) != null)
		{
			if(!((sourceNodeString = searchNode(channel, "SourceChannelName")).equals("")))
			{
				 vo.getChannelSelectionMap().put("gray-channel-name", sourceNodeString);
			}
			if((contrast = getFirstElementNode(channel, "ContrastEnhancement")) != null)
			{
				vo.setContrastValueType("gray-contrast-value");
				if((getFirstElementNode(contrast, "Normalize")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Gray", "Normalize", 0));
				}
				else if((getFirstElementNode(contrast, "Histogram")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Gray", "Histogram", 0));
				}
				else if((gammaValue = searchNode(contrast, "GammaValue")) != null)
				{
					vo.getRgbcontrast().add(new RGBContrast("Gray", "GammaValue", new Double(gammaValue)));
				}
			
			}
		}		
	}

	private void parseRasterColorMap(Node rasterChild, SLDEditorRuleVO vo) {
		NamedNodeMap attrList = rasterChild.getAttributes();
		NamedNodeMap mapEntryAttr;
		Node mapEntry;
		String color = "";
		String quantity = "";
		String label = "";
		String opacity = "";
		boolean opacityBool = false;
		vo.setColorMapFound(true);
		List<ColorMapBigGrid> itemsBigGridAp = new ArrayList<ColorMapBigGrid>();
		int i=0, j=0;
		for(i=0; i< attrList.getLength(); i++)
		{
			if(attrList.item(i).getNodeName().equals("type"))
			{
				vo.getColorMapItem().put("type-attribute", attrList.item(i).getNodeValue());
			}
			else
			{
				vo.getColorMapItem().put("type-attribute", "Ramp");
			}
			
			if(attrList.item(i).getNodeName().equals("extended"))
			{
				if(attrList.item(i).getNodeValue().equals("true"))
				{
					vo.getColorMapItem().put("extended-attribute", "Extended Range Color");
				}
				else
				{
					vo.getColorMapItem().put("extended-attribute", "Normal Range Color");
				}
			}
		}
		while((mapEntry = getFirstElementNode(rasterChild, "ColorMapEntry")) != null)
		{
			mapEntryAttr = mapEntry.getAttributes();
			color = "";
			quantity = "";
			label = "";
			opacity = "";
			for(j=0; j< mapEntryAttr.getLength(); j++)
			{				
				if(mapEntryAttr.item(j).getNodeName().equals("color"))
				{
					color = mapEntryAttr.item(j).getNodeValue();
				}
				if(mapEntryAttr.item(j).getNodeName().equals("quantity"))
				{
					quantity = mapEntryAttr.item(j).getNodeValue();
				}
				if(mapEntryAttr.item(j).getNodeName().equals("label"))
				{
					label = mapEntryAttr.item(j).getNodeValue();
				}
				if(mapEntryAttr.item(j).getNodeName().equals("opacity"))
				{
					opacity = mapEntryAttr.item(j).getNodeValue();
					opacityBool = true;
				}
			}
			if(opacity.equals(""))
			{
				opacity = "0";
			}
			itemsBigGridAp.add(new ColorMapBigGrid(new Double(quantity), label,color, opacity)); 
			
			mapEntry.getParentNode().removeChild(mapEntry);
		}
		if(opacityBool)
		{
			vo.setColorMapSlider(false);
			vo.setGridType("BigGrid");
			vo.itemsBigGrid = new ArrayList<ColorMapBigGrid>();
			for(i=0; i<itemsBigGridAp.size();i++)
			{
				vo.itemsBigGrid.add(new ColorMapBigGrid(itemsBigGridAp.get(i).getquantity(),itemsBigGridAp.get(i).getlabel(),itemsBigGridAp.get(i).getColor(),itemsBigGridAp.get(i).getopacity()));  
			}		
		}
		else
		{
			vo.setColorMapSlider(true);
			vo.setGridType("SmallGrid");
			vo.itemsSmallGrid = new ArrayList<ColorMapSmallGrid>();
			for(i=0; i<itemsBigGridAp.size();i++)
			{
				vo.itemsSmallGrid.add(new ColorMapSmallGrid(itemsBigGridAp.get(i).getquantity(),itemsBigGridAp.get(i).getlabel(),itemsBigGridAp.get(i).getColor()));  
			}
		}	
	}

	private void parseRasterSliderOpacity(Node rasterChild, SLDEditorRuleVO vo) {

		NodeList childList = rasterChild.getChildNodes();
  	  	Node child;
    	 for(int j=0; j<childList.getLength(); j++)
  		{
    		 child = childList.item(j);
    		 if (child.getNodeType() == Node.TEXT_NODE) {
    			 
    			 String contentVO = child.getTextContent();
    			 vo.getSliderOpacityOnlyMap().put("fill-opacity", contentVO);
    		 }
  		}
	}
	
	private String parseShapePolygonFill(Node shapeChild, SLDEditorRuleVO vo) {
		vo.setType(SLDEditorConstants.BACKGROUND);
		Node cssParam, cssParamChild;
		NodeList cssParameterList;
		int i=0, j=0;
		NamedNodeMap attrList;
		String color = "";
		
		while((cssParam = getFirstElementNode(shapeChild, "CssParameter")) != null)
		{
			attrList = cssParam.getAttributes();
			for(i=0; i< attrList.getLength(); i++)
			{
				if(attrList.item(i).getNodeName().equals("name"))
				{
					String attr_value = attrList.item(0).getNodeValue();
					if( attr_value.equals("fill"))
					{
						cssParameterList = cssParam.getChildNodes();
			        	  
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			color = cssParamChild.getTextContent();
		 	        		 }
		 	      		}
						
					}
				}
			}
			cssParam.getParentNode().removeChild(cssParam); 
		}
		return color;
	}

	private void parseShapeLineStroke(Node shapeChild, SLDEditorRuleVO vo) {
		vo.setType(SLDEditorConstants.LINE);
		Node cssParam, cssParamChild;
		NodeList cssParameterList;
		int i=0, j=0;
		NamedNodeMap attrList;
		while((cssParam = getFirstElementNode(shapeChild, "CssParameter")) != null)
		{
			attrList = cssParam.getAttributes();
			for(i=0; i< attrList.getLength(); i++)
			{
				if(attrList.item(i).getNodeName().equals("name"))
				{
					String attr_value = attrList.item(0).getNodeValue();
					if( attr_value.equals("stroke"))
					{
						cssParameterList = cssParam.getChildNodes();
			        	  
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			vo.getStrokeParameters().put("stroke", cssParamChild.getTextContent());
		 	        		 }
		 	      		}
						
					}
					else if( attr_value.equals("stroke-dasharray"))
					{
						cssParameterList = cssParam.getChildNodes();
			        	  
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			vo.getStrokeParameters().put("stroke-dasharray", cssParamChild.getTextContent());
		 	        		 }
		 	      		}
					}
					else if( attr_value.equals("stroke-width"))
					{
						cssParameterList = cssParam.getChildNodes(); 
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			vo.getStrokeParameters().put("stroke-width", cssParamChild.getTextContent());
		 	        		 }
		 	      		}
					}
				}
			}
			cssParam.getParentNode().removeChild(cssParam); 
		}		
	}
	
	private void parseShapeLabelLabel(Node shapeChild, SLDEditorRuleVO vo) {
		vo.setType(SLDEditorConstants.LABEL);
		String property = searchNode(shapeChild, "PropertyName");
		vo.setLabelPropertyName(property);
	}

	private void parseShapeLabelFont(Node shapeChild, SLDEditorRuleVO vo) {
		vo.setType(SLDEditorConstants.LABEL);
		Node cssParam, cssParamChild;
		NodeList cssParameterList;
		int i=0, j=0;
		NamedNodeMap attrList;
		
		while((cssParam = getFirstElementNode(shapeChild, "CssParameter")) != null)
		{
			attrList = cssParam.getAttributes();
			for(i=0; i< attrList.getLength(); i++)
			{
				if(attrList.item(i).getNodeName().equals("name"))
				{
					String attr_value = attrList.item(0).getNodeValue();
					if( attr_value.equals("font-size"))
					{
						cssParameterList = cssParam.getChildNodes();
			        	  
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			vo.getFontParameters().put("font-size", cssParamChild.getTextContent());
		 	        		 }
		 	      		}
						
					}
					else if( attr_value.equals("font-style"))
					{
						cssParameterList = cssParam.getChildNodes();
			        	  
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			vo.getFontParameters().put("font-style", cssParamChild.getTextContent());
		 	        		 }
		 	      		}
					}
					else if( attr_value.equals("font-family"))
					{
						cssParameterList = cssParam.getChildNodes();
			        	  
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			vo.getFontParameters().put("font-family", cssParamChild.getTextContent());
		 	        		 }
		 	      		}
					}
					else if( attr_value.equals("font-weight"))
					{
						cssParameterList = cssParam.getChildNodes();
			        	  
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			vo.getFontParameters().put("font-weight", cssParamChild.getTextContent());
		 	        		 }
		 	      		}
					}
				}
			}
			cssParam.getParentNode().removeChild(cssParam); 
		}		
	}
	
	private void parseShapeLabelFill(Node shapeChild, SLDEditorRuleVO vo) {
		vo.setType(SLDEditorConstants.LABEL);
		Node cssParam, cssParamChild;
		NodeList cssParameterList;
		int i=0, j=0;
		NamedNodeMap attrList;
		
		while((cssParam = getFirstElementNode(shapeChild, "CssParameter")) != null)
		{
			attrList = cssParam.getAttributes();
			for(i=0; i< attrList.getLength(); i++)
			{
				if(attrList.item(i).getNodeName().equals("name"))
				{
					String attr_value = attrList.item(0).getNodeValue();
					if( attr_value.equals("fill"))
					{
						cssParameterList = cssParam.getChildNodes();
			        	  
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			vo.getFontParameters().put("fill", cssParamChild.getTextContent());
		 	        		 }
		 	      		}
						
					}
					else if( attr_value.equals("fill-opacity"))
					{
						cssParameterList = cssParam.getChildNodes();
			        	  
		 	        	 for(j=0; j<cssParameterList.getLength(); j++)
		 	      		{
		 	        		cssParamChild = cssParameterList.item(j);
		 	        		 if (cssParamChild.getNodeType() == Node.TEXT_NODE) {
		 	        			vo.getFontParameters().put("fill-opacity", cssParamChild.getTextContent());
		 	        		 }
		 	      		}
					}
				}
			}
			cssParam.getParentNode().removeChild(cssParam); 
		}	
	}

	public String searchNode(Node node, String nodeName)
	{
		String content = "";
		NodeList childList = node.getChildNodes();
		NodeList childList2 = null;
		Node appChild, appChild2;
		String nodeNameApp = "";
		
		for(int i=0; i<childList.getLength(); i++)
		{
			  appChild = childList.item(i);

	          if (appChild.getNodeType() == Node.ELEMENT_NODE) {
	        	  
	        	  nodeNameApp = appChild.getNodeName();
	        	  if(nodeNameApp.contains(":"))
	        	  {
	        		  nodeNameApp = nodeNameApp.substring(nodeNameApp.indexOf(":") +1);
	        	  }
	        	  if(nodeNameApp.equals(nodeName))
	        	  {
	        		  childList2 = appChild.getChildNodes();
		        	  
	 	        	 for(int j=0; j<childList2.getLength(); j++)
	 	      		{
	 	        		 appChild2 = childList2.item(j);
	 	        		 if (appChild2.getNodeType() == Node.TEXT_NODE) {
	 	        			 content = appChild2.getTextContent();
	 	        		 }
	 	      		}
	        	  }	        	  
	          }
		}
		
		return content;
	}
	
	public Node getFirstElementNode(Node node, String nodeName)
	{
		NodeList childList = node.getChildNodes();
		Node appChild =  null;
		String nodeNameApp="";
		for(int i=0; i<childList.getLength(); i++)
		{
			  appChild = childList.item(i);

	          if (appChild.getNodeType() == Node.ELEMENT_NODE) {
	        	  
	        	  nodeNameApp = appChild.getNodeName();
	        	  if(nodeNameApp.contains(":"))
	        	  {
	        		  nodeNameApp = nodeNameApp.substring(nodeNameApp.indexOf(":") +1);
	        	  }
	        	  if(nodeNameApp.equals(nodeName))
	        	  {
	        		return appChild;
	        	  }	    
	        	  else 
	        	  {
	        		  appChild = null;
	        	  }
	          }
	          else 
        	  {
        		  appChild = null;
        	  }
		}
		
		return appChild;
	}
	
}