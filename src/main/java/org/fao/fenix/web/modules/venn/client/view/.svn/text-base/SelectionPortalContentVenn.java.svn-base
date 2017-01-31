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

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HTML;

public class SelectionPortalContentVenn extends SelectionPortalContent {

	
	HorizontalPanel panelPortlet;
	
	HorizontalPanel panelCentral;
	
	HTML centralHtml;
	
	HTML portletHtml;
	
	HTML portletInfoHtml;
	
	ContentPanel portletInfoPanel;
	
	ContentPanel centralInfoPanel;
	
	public SelectionPortalContentVenn(SelectionPortalPanel selectionPortalPanel, int widthPortlet, int heightPortlet, String type, String title) {
		super(selectionPortalPanel, widthPortlet, heightPortlet, type, title);
		centralHtml = new HTML();
		portletHtml = new HTML();
		portletInfoHtml = new HTML();
		panelPortlet = new HorizontalPanel();
		panelPortlet.setSpacing(10);
		
		panelCentral = new HorizontalPanel();
		panelCentral.setSpacing(10);
		
		buildCenter();
		buildPortlet();
		buildContentPanel();
		initalizeObjects();
		selectionPortalPanel.setSwithCenterPanel(this.contentPanel);
	}
	
	private void buildCenter(){
		contentPanel.setId(type);
		contentPanel.setHeading(title);  
		
		panelCentral.add(buildCentralInfoPanel());
		panelCentral.add(buildCentralHtmlPanel());
		contentPanel.add(panelCentral);
		contentPanel.setWidth(selectionPortalPanel.getWidthPortal());
		contentPanel.setHeight(selectionPortalPanel.getHeightPortal());
	}
	
	private void buildPortlet(){
		portlet.setId(type);
		portlet.setHeading(title);  
		portlet.setHeight(heightPortlet);  
	    configPanel(portlet);
	    panelPortlet.add(buildPortletInfoPanel());
	    panelPortlet.add(buildPortletHtmlPanel());
	    portlet.add(panelPortlet);
	}
	
	private ContentPanel buildCentralInfoPanel() {
		centralInfoPanel = new ContentPanel();
		centralInfoPanel.setWidth(150);
		centralInfoPanel.setHeight(430);
//		portletInfoPanel.setHeaderVisible(false);
		centralInfoPanel.setHeading("Common Priorities");
		centralInfoPanel.setBorders(true);
		centralInfoPanel.setScrollMode(Scroll.AUTO);
		return centralInfoPanel;
	}
	
	private ContentPanel buildCentralHtmlPanel() {
		ContentPanel panel = new ContentPanel();
		panel.setBorders(false);
		panel.setWidth(700);
		panel.setHeight(450);
		panel.setHeaderVisible(false);
		panel.add(centralHtml);
		return panel;
	}
	
	
	
	private ContentPanel buildPortletInfoPanel() {
		portletInfoPanel = new ContentPanel();
		portletInfoPanel.setWidth(150);
		portletInfoPanel.setHeight(250);
//		portletInfoPanel.setHeaderVisible(false);
		portletInfoPanel.setHeading("Common Priorities");
		portletInfoPanel.setBorders(true);
		portletInfoPanel.setScrollMode(Scroll.AUTO);
		portletInfoPanel.hide();
		return portletInfoPanel;
	}
	
	private ContentPanel buildPortletHtmlPanel() {
		ContentPanel panel = new ContentPanel();
		panel.setWidth(250);
//		panel.setHeight(150);
		panel.setHeaderVisible(false);
		panel.add(portletHtml);
		return panel;
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
	
		
		// export
		IconButton export = new IconButton("sendToPdf");
		export.setToolTip(BabelFish.print().exportPdf());
		export.setEnabled(false);
		toolbar.add(export);
		toolbar.add(new SeparatorToolItem());
		
		// export to excel
		IconButton exportExcel = new IconButton("sendToExcel");
		exportExcel.setToolTip("Export To Excel");
		exportExcel.setEnabled(false);
		toolbar.add(exportExcel);
		toolbar.add(new SeparatorToolItem());
		
		// export to HTML
		IconButton exportHTML = new IconButton("sendToHTML");
		exportHTML.setToolTip("Export To HTML");
		exportHTML.setEnabled(false);
		toolbar.add(exportHTML);
		toolbar.add(new SeparatorToolItem());
		
		return toolbar;
	}
	

	public HTML getCentralHtml() {
		return centralHtml;
	}

	public void setCentralHtml(HTML centralHtml) {
		this.centralHtml = centralHtml;
	}

	public HTML getPortletHtml() {
		return portletHtml;
	}

	public void setPortletHtml(HTML portletHtml) {
		this.portletHtml = portletHtml;
	}



	public HTML getPortletInfoHtml() {
		return portletInfoHtml;
	}

	public void setPortletInfoHtml(HTML portletInfoHtml) {
		this.portletInfoHtml = portletInfoHtml;
	}

	public ContentPanel getPortletInfoPanel() {
		return portletInfoPanel;
	}

	public ContentPanel getCentralInfoPanel() {
		return centralInfoPanel;
	}
	
	
}