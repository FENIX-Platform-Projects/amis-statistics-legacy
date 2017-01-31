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

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class SelectionPortalContentChart extends SelectionPortalContent {

	
//	HTML centralChart;
	
//	HTML portletChart;
	
	ContentPanel centralChart;
	
	ContentPanel portletChart;
	
	ContentPanel centralChartPanel;

	
	TabPanel centralTabPanel;
	
	TabPanel portletTabPanel;
	
	public SelectionPortalContentChart(SelectionPortalPanel selectionPortalPanel, int widthPortlet, int heightPortlet, String type, String title) {
		super(selectionPortalPanel, widthPortlet, heightPortlet, type, title);
//		centralChart = new HTML();
//		portletChart = new HTML();
		centralChart = new ContentPanel();
		portletChart = new ContentPanel();
		portletChart.setHeaderVisible(false);
		centralChart.setHeaderVisible(false);
		portletChart.setBorders(false);
		centralChart.setBorders(false);
		portletTabPanel = new TabPanel();
		portletTabPanel.setAnimScroll(true);  
		portletTabPanel.setTabScroll(true);  
		centralTabPanel = new TabPanel();
		centralTabPanel.setAnimScroll(true);  
		centralTabPanel.setTabScroll(true);  
		buildPortlet();
		buildCenterPanel();
		selectionPortalPanel.setSwithCenterPanel(this.contentPanel);
		this.contentPanel.setWidth(selectionPortalPanel.getWidthPortal());
		this.contentPanel.setHeight(selectionPortalPanel.getHeightPortal());

	}
	
	private void buildPortlet(){
		portlet.setId(type);
		portlet.setHeading(title);  
		portlet.setHeight(heightPortlet); 
		portletChart.add(portletTabPanel);
		portlet.add(portletChart);
	    configPanel(portlet);
	   

	}
	

	
	private void buildCenterPanel(){
		contentPanel.setTopComponent(getToolBar());
		VerticalPanel panel = new VerticalPanel();
		centralChartPanel = new ContentPanel();
		centralChartPanel.setBorders(false);
		centralChartPanel.setHeaderVisible(false);
		centralChart.add(centralTabPanel);
		centralChartPanel.add(centralChart);
		panel.add(centralChartPanel);
		this.contentPanel.add(panel);
	}
	
	private void configPanel(final ContentPanel panel) {  
		panel.setCollapsible(false);  
	    panel.setAnimCollapse(false);  

	    
//	    panel.getHeader().addTool(new ToolButton("x-tool-gear", new SelectionListener<IconButtonEvent>() {  
	    panel.getHeader().addTool(new ToolButton("x-tool-toggle", new SelectionListener<IconButtonEvent>() {  
	    @Override  
	    public void componentSelected(IconButtonEvent ce) {  
	    	selectionPortalPanel.changeSelection(contentPanel, portlet);
	    	}  
	    }));  
	    


			  panel.layout();
	}

	private ToolBar getToolBar() {

		// edit chart
		IconButton chartEdit = new IconButton("chartEdit");
		chartEdit.setToolTip("Edit Chart");
		chartEdit.setEnabled(false);
		toolbar.add(chartEdit);
		toolbar.add(new SeparatorToolItem());
		
		// export
		IconButton export = new IconButton("sendToPdf");
		export.setToolTip(BabelFish.print().exportPdf());
		export.setEnabled(false);
		toolbar.add(export);
		toolbar.add(new SeparatorToolItem());
		
		
		
		
		return toolbar;
	}

//	public HTML getCentralChart() {
//		return centralChart;
//	}
//
//	public void setCentralChart(HTML centralChart) {
//		this.centralChart = centralChart;
//	}
//
//	public HTML getPortletChart() {
//		return portletChart;
//	}
//
//	public void setPortletChart(HTML portletChart) {
//		this.portletChart = portletChart;
//	}

	public ContentPanel getCentralChartPanel() {
		return centralChartPanel;
	}

	public void setCentralChartPanel(ContentPanel centralChartPanel) {
		this.centralChartPanel = centralChartPanel;
	}

	public ContentPanel getCentralChart() {
		return centralChart;
	}

	public void setCentralChart(ContentPanel centralChart) {
		this.centralChart = centralChart;
	}

	public ContentPanel getPortletChart() {
		return portletChart;
	}

	public void setPortletChart(ContentPanel portletChart) {
		this.portletChart = portletChart;
	}

	public TabPanel getCentralTabPanel() {
		return centralTabPanel;
	}

	public void setCentralTabPanel(TabPanel centralTabPanel) {
		this.centralTabPanel = centralTabPanel;
	}

	public TabPanel getPortletTabPanel() {
		return portletTabPanel;
	}

	public void setPortletTabPanel(TabPanel portletTabPanel) {
		this.portletTabPanel = portletTabPanel;
	}
	

	
	
	
	
}