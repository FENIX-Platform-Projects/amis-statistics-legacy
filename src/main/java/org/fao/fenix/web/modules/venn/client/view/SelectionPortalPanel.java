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

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.google.gwt.user.client.ui.HTML;

public class SelectionPortalPanel {
	
	Portal selectionPortal;

	SelectionPortalContentTable tableContent;
	
	SelectionPortalContentMap mapContent;
	
	SelectionPortalContentChart chartContent;
	
	SelectionPortalContentVenn vennContent;
	
	SelectionPortalContentOlap olapContent;
	
	
	int heightPortal;
	
	int widthPortal;
	
	int heightPortlet;
	
	int widthPortlet;
	
	ContentPanel contentPanel;
	
	Portlet currentPortlet;
	
	HTML chart;
	
//	public SelectionPortalPanel(ContentPanel centerPanel, int widthPortal, int heightPortal, int widthPortlet, int heightPortlet) {
//		this.centerPanel = centerPanel;
//		
//		selectionPortal = new Portal(4);
//		tableContent = new SelectionPortalContentTable(this, widthPortlet, heightPortlet, "table", "Table");
//		mapContent = new SelectionPortalContentMap(this, widthPortlet, heightPortlet, "map", "Map");
//		chartContent = new SelectionPortalContentChart(this, widthPortlet, heightPortlet, "chart", "Chart");
//		vennContent = new SelectionPortalContentVenn(this, widthPortlet, heightPortlet, "venn", "Venn");
//		olapContent = new SelectionPortalContentOlap(this, widthPortlet, heightPortlet, "olap", "Olap");
//	
//		
//		this.heightPortal = heightPortal;
//		this.widthPortal = widthPortal;
//		this.heightPortlet = heightPortlet;
//		this.widthPortlet = widthPortlet;
//		
//		selectionPortal.setBorders(false);  
//		selectionPortal.setStyleAttribute("backgroundColor", "white");  
//		selectionPortal.setColumnWidth(0, widthPortal);  
//		selectionPortal.setColumnWidth(1, widthPortal);  
//		selectionPortal.setColumnWidth(2, widthPortal);  
//		selectionPortal.setColumnWidth(3, widthPortal);  
//
//		
//
//	}
	
	
	public SelectionPortalPanel(ContentPanel centerPanel, int widthPortal, int heightPortal, int widthPortlet, int heightPortlet) {
		this.contentPanel = centerPanel;
		this.heightPortal = heightPortal;
		this.widthPortal = widthPortal;
		this.heightPortlet = heightPortlet;
		this.widthPortlet = widthPortlet;
		this.contentPanel.setWidth(widthPortal);
		this.contentPanel.setWidth(widthPortal);
		
		selectionPortal = new Portal(2);
//		tableContent = new SelectionPortalContentTable(this, widthPortlet, heightPortlet + 150, "table", "Table");
		mapContent = new SelectionPortalContentMap(this, widthPortlet, heightPortlet + 150, "map", "Map");
		chartContent = new SelectionPortalContentChart(this, widthPortlet, heightPortlet + 150, "chart", "Chart");
		vennContent = new SelectionPortalContentVenn(this, widthPortlet, heightPortlet + 150, "venn", "Venn");
		olapContent = new SelectionPortalContentOlap(this, widthPortlet, heightPortlet + 150, "olap", "Table");
	
		
		
		
		selectionPortal.setBorders(false);  
		selectionPortal.setStyleAttribute("backgroundColor", "white");  
 
		
		selectionPortal.setColumnWidth(0, widthPortlet + 200);  
		selectionPortal.setColumnWidth(1, widthPortlet + 200);  
		
	
		
	}
	

	
	public Portal build(){
		selectionPortal.add(tableContent.getPortlet(), 0);
		selectionPortal.add(olapContent.getPortlet(), 1);
		selectionPortal.add(chartContent.getPortlet(), 2);
		selectionPortal.add(mapContent.getPortlet(), 3);
		currentPortlet = vennContent.getPortlet();
			
		return selectionPortal;
	}


	public Portal build4Portlet(){
		selectionPortal.add(olapContent.getPortlet(), 1);
		selectionPortal.add(vennContent.getPortlet(), 0);
		selectionPortal.add(mapContent.getPortlet(), 1);
		selectionPortal.add(chartContent.getPortlet(), 0);		

		return selectionPortal;
	}
	
	
//	void changeSelection(ContentPanel contentPanel, Portlet portlet) {
//		this.getCenterPanel().removeAll();
//		this.getCenterPanel().add(contentPanel);
//		this.getCenterPanel().layout();	
//		int position = this.getSelectionPortal().getPortletColumn(portlet);
//		this.getSelectionPortal().remove(portlet, position);
//		this.getSelectionPortal().add(this.getCurrentPortlet(), position);
//		this.setCurrentPortlet(portlet);
//		collapseAll();
//	}
	
	void changeSelection(ContentPanel contentPanel, Portlet portlet) {
		contentPanel.show();
//		centerPanel.add(contentPanel);
//		contentPanel.show();
//		centerPanel.layout();
//		this.setCenterPanel(contentPanel);
//		this.getCenterPanel().layout();	
//		int position = this.getSelectionPortal().getPortletColumn(portlet);
//		this.getSelectionPortal().remove(portlet, position);
//		this.getSelectionPortal().add(this.getCurrentPortlet(), position);
//		this.setCurrentPortlet(portlet);
		collapseAll();
	}


	public Portal getSelectionPortal() {
		return selectionPortal;
	}


	public void collapseAll() {
//		tableContent.getPortlet().hide();
		olapContent.getPortlet().hide();
		chartContent.getPortlet().hide();
		mapContent.getPortlet().hide();
		vennContent.getPortlet().hide();
//		centerPanel.show();
	}
	
	public void expandAll() {
//		tableContent.getPortlet().show();
		olapContent.getPortlet().show();
		chartContent.getPortlet().show();
		mapContent.getPortlet().show();
		vennContent.getPortlet().show();
//		centerPanel.hide();
	}

	
	public void swithPanel() {
		contentPanel.hide();
    	expandAll();
	}

	
	public void setSwithCenterPanel(final ContentPanel panel) {  
		panel.setCollapsible(false);  
	    panel.setAnimCollapse(false);  
	    panel.setHeaderVisible(true);
	    
//	    panel.getHeader().addTool(new ToolButton("x-tool-gear", new SelectionListener<IconButtonEvent>() {  
	    panel.getHeader().addTool(new ToolButton("x-tool-toggle", new SelectionListener<IconButtonEvent>() {  
	    @Override  
	    public void componentSelected(IconButtonEvent ce) {  
	    	panel.hide();
	    	expandAll();
	    	}  
	    }));  

	}
	

	public int getHeightPortal() {
		return heightPortal;
	}



	public int getWidthPortal() {
		return widthPortal;
	}



	public int getHeightPortlet() {
		return heightPortlet;
	}



	public int getWidthPortlet() {
		return widthPortlet;
	}
	
	
	
	public Portlet getCurrentPortlet() {
		return currentPortlet;
	}



	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	

	public void setCurrentPortlet(Portlet currentPortlet) {
		this.currentPortlet = currentPortlet;
	}



	public SelectionPortalContentTable getTableContent() {
		return tableContent;
	}



	public SelectionPortalContentMap getMapContent() {
		return mapContent;
	}



	public SelectionPortalContentChart getChartContent() {
		return chartContent;
	}



	public SelectionPortalContentVenn getVennContent() {
		return vennContent;
	}



	public SelectionPortalContentOlap getOlapContent() {
		return olapContent;
	}



	public void setCenterPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}



	
	
	
}