package org.fao.fenix.web.modules.venn.client.view;


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;

public class BaseTool extends FenixToolBase {
	
	private VennPortalPanel vennPortalPanel;
	
	private String width;
	
	private String height;
	
	private String language;
	
	private List<String> countryCodes;
	
	private String xml;
	
	
	public BaseTool() {
		vennPortalPanel = new VennPortalPanel();
	}
	
	public void build() {
		buildWestPanel();
		buildCenterPanel();
		format();

	}
	
	public void build(String type, String height, String width, String language, String countries, String xml) {
		this.width = width;
		this.height = height;
		this.language = language;
		this.xml = xml;
		
		// if xml is not null as parameter it's been selected an xml configuration file
		if ( xml != null){
			buildXMLInterface();		
		}
		else {
			parseCountries(countries);
		
			/** TODO: review with the new use cases... **/
			if ( type.equals("g"))
				buildGeneric();
			else if ( type.equals(Type.multi.toString()))
				buildMultiCountry();
			else
				buildOneCountry();
		}

	}
	
	private void buildOneCountry() {
		buildWestPanel();
		buildCenterPanel();
		format();
	}
	
	private void buildMultiCountry() {
		buildWestPanel();
		buildCenterPanel();
		format();
	}
	
	
	
	private void buildGeneric() {
		VennGraphBeanVO vennGraphBeanVO = new VennGraphBeanVO();
		System.out.println("here");
		System.out.println("-> " + vennGraphBeanVO.getA().getClass());
		System.out.println("-> " + vennGraphBeanVO.getA());
//		System.out.println("-> " + vennGraphBeanVO.getA().
		
		System.out.println("generic");
		buildWestPanel();
		buildCenterPanel();
		format();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
//		enter().add(vennPortalPanel.build(1200, 900));
		getCenter().add(vennPortalPanel.buildCenter(1200, 900));
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
	}
	
	private void buildWestPanel() {
		setWestProperties();
		getWest().add(vennPortalPanel.buildWest(1200, 900, false, xml, language, countryCodes));
		addWestPartToWindow();
		getWestData().setSize(340);
	}
	
	private void buildXMLInterface() {
		System.out.println("buildXMLInterface");
//		VennController.getConfiguration(xml);
//		buildWestXMLPanel();
		buildWestXMLPanel();
		buildCenterPanel();
		format();
	}
	
	private void buildWestXMLPanel() {
		setWestProperties();
		getWest().add(vennPortalPanel.buildWest(1200, 900, true, xml, language, countryCodes));
		addWestPartToWindow();
		getWestData().setSize(340);
//		getWest().setHeaderVisible(false);
	}
	
	
	private void format() {
		getToolBase().setWidth("1240px");	
		getToolBase().setHeight("700px");
		getToolBase().setHeaderVisible(false);
	}
	
	
	private void parseCountries(String countries) {
		this.countryCodes = new ArrayList<String>();
		if ( !countries.contains(",")) 
			countryCodes.add(countries);
		else {
			while( countries.contains(",")) {
				countryCodes.add(countries.substring(0, countries.indexOf(",")));
				countries = countries.substring(countries.indexOf(",") +1, countries.length());
			}
		}
		
	}

	enum Type {
		multi;
	}


	public VennPortalPanel getVennPortalPanel() {
		return vennPortalPanel;
	}
	
	

}