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



import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class SelectionPortalContentOlap extends SelectionPortalContent {
	
	

	
	HorizontalPanel panelPortlet;
//	
	HorizontalPanel panelCentral;
	
	ContentPanel gridPortletPanel;
//	
	ContentPanel gridCenterPanel;	
	
	TabPanel centralTabPanel;
	
	TabPanel portletTabPanel;
	
	
	public SelectionPortalContentOlap(SelectionPortalPanel selectionPortalPanel, int widthPortlet, int heightPortlet, String type, String title) {
		super(selectionPortalPanel, widthPortlet, heightPortlet, type, title);
		panelPortlet = new HorizontalPanel();
		panelPortlet.setSpacing(10);
//		panelPortlet.setScrollMode(Scroll.AUTO);
		
		panelCentral = new HorizontalPanel();
		panelCentral.setSpacing(10);
		
		portletTabPanel = new TabPanel();
		portletTabPanel.setAnimScroll(true);  
		portletTabPanel.setTabScroll(true);  
		centralTabPanel = new TabPanel();
		centralTabPanel.setAnimScroll(true);  
		centralTabPanel.setTabScroll(true);  
		
		buildCenter();
		buildPortlet();
		buildContentPanel();
		initalizeObjects();
		selectionPortalPanel.setSwithCenterPanel(this.contentPanel);
		
		
	}
	
	private void buildCenter(){
		contentPanel.setId(type);
		contentPanel.setHeading(title);  
		panelCentral.add(centralTabPanel);
//		panelCentral.add(buildCentralGridPanel());
		contentPanel.add(panelCentral);
		contentPanel.setWidth(selectionPortalPanel.getWidthPortal());
		contentPanel.setHeight(selectionPortalPanel.getHeightPortal());
	}
	
	private void buildPortlet(){
		portlet.setId(type);
		portlet.setHeading(title);  
		portlet.setHeight(heightPortlet);  
	    configPanel(portlet);
	    panelPortlet.add(portletTabPanel);
//	    panelPortlet.add(buildPortletGridPanel());
	    portlet.add(panelPortlet);
	}
	

	
	private ContentPanel buildCentralGridPanel() {
		gridCenterPanel = new ContentPanel();
		gridCenterPanel.setBorders(false);
		gridCenterPanel.setWidth(850);
		gridCenterPanel.setHeight(620);
		gridCenterPanel.setHeaderVisible(false);
//		gridCenterPanel.setScrollMode(Scroll.AUTO);
		return gridCenterPanel;
	}
	
//	private ContentPanel buildCentralGridPanel() {
//		ContentPanel p = new ContentPanel();
//		gridCenterPanel = new VerticalPanel();
//		gridCenterPanel.setBorders(false);
//		gridCenterPanel.setWidth(850);
//		gridCenterPanel.setHeight(640);
//		gridCenterPanel.setScrollMode(Scroll.AUTO);
//	p.setCollapsible(true);
//		p.add(gridCenterPanel);
//		return p;
//}
	

	
	private ContentPanel buildPortletGridPanel() {
		gridPortletPanel = new ContentPanel();
		gridPortletPanel.setWidth(370);
		gridPortletPanel.setHeight(250);
		gridPortletPanel.setHeaderVisible(false);
		gridPortletPanel.setScrollMode(Scroll.AUTO);
		return gridPortletPanel;
	}
	
	
	private void initalizeObjects() {
		
		
	}
	
	private void buildContentPanel(){
		contentPanel.setTopComponent(getToolBar());
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

		
		return toolbar;
	}

	public HorizontalPanel getPanelPortlet() {
		return panelPortlet;
	}

	public HorizontalPanel getPanelCentral() {
		return panelCentral;
	}

	public ContentPanel getGridPortletPanel() {
		return gridPortletPanel;
	}

//	public VerticalPanel getGridCenterPanel() {
//		return gridCenterPanel;
//	}
//
//	public void setGridCenterPanel(VerticalPanel gridCenterPanel) {
//		this.gridCenterPanel = gridCenterPanel;
//	}

	public void setPanelPortlet(HorizontalPanel panelPortlet) {
		this.panelPortlet = panelPortlet;
	}

	public void setPanelCentral(HorizontalPanel panelCentral) {
		this.panelCentral = panelCentral;
	}

	public void setGridPortletPanel(ContentPanel gridPortletPanel) {
		this.gridPortletPanel = gridPortletPanel;
	}

	public ContentPanel getGridCenterPanel() {
		return gridCenterPanel;
	}

	public TabPanel getCentralTabPanel() {
		return centralTabPanel;
	}

	public TabPanel getPortletTabPanel() {
		return portletTabPanel;
	}

	
	
}