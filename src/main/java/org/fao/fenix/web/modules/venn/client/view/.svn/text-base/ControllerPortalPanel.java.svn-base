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
package org.fao.fenix.web.modules.venn.client.view;

import java.util.List;

import org.fao.fenix.web.modules.venn.client.control.VennController;
import org.fao.fenix.web.modules.venn.client.control.VennControllerReport;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.custom.Portal;

public class ControllerPortalPanel {
	
	private ContentPanel contentPanel; 
	
	private Portal portal;
	
	private DatasetPortletPanel datasetPortlet;
	
	private DimensionPortletPanel xPortlet;
	
	private DimensionPortletPanel yPortlet;
	
	private DimensionPortletPanel wPortlet;
	
	private DimensionPortletPanel zPortlet;
	
	public ControllerPortalPanel() {
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		contentPanel.setBodyBorder(false);
		contentPanel.setBorders(false);
		portal = new Portal(1);
		portal.setBorders(false);  
		portal.setStyleAttribute("backgroundColor", "white");  
		portal.setColumnWidth(0, 300);  

//		datasetPortlet = new DatasetPortletPanel(400, 400, true, "Dataset");
		xPortlet = new DimensionPortletPanel(400, 200, true, "x", "Set A");
		yPortlet = new DimensionPortletPanel(400, 200, true, "y", "Set B");
		wPortlet = new DimensionPortletPanel(400, 200, true, "w", "Set C");
//		zPortlet = new DimensionPortletPanel(250, 200, false, "z", "Dimension Z");
		
	}
	
	

	
	public ContentPanel build(VennPortalPanel vennPortal, CenterToolPanel centerPanel, Boolean useXml, String xml, String language, List<String> countryCodes){
		portal.add(xPortlet.build(vennPortal, centerPanel, useXml, xml, language, "setA"), 0);
		portal.add(yPortlet.build(vennPortal, centerPanel, useXml, xml, language, "setB"), 0);
		portal.add(wPortlet.build(vennPortal, centerPanel, useXml, xml, language, "setC"), 0);
		
		if ( useXml) {
			/** TODO: questo e' un test **/
//			VennControllerReport.applyXMLConfiguration(vennPortal, centerPanel, language, xml);
			
			VennController.applyXMLConfiguration(vennPortal, centerPanel, language, xml);
		}
		
		contentPanel.add(portal);
		return contentPanel;
	}


	public DimensionPortletPanel getxPortlet() {
		return xPortlet;
	}




	public DimensionPortletPanel getyPortlet() {
		return yPortlet;
	}




	public DimensionPortletPanel getwPortlet() {
		return wPortlet;
	}

		  
		
	
}