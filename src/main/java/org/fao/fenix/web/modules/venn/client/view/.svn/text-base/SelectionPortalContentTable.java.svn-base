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
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HTML;

public class SelectionPortalContentTable extends SelectionPortalContent {

	
	HTML chartImage;
	
	HTML centralImage;
	
	public SelectionPortalContentTable(SelectionPortalPanel selectionPortalPanel, int widthPortlet, int heightPortlet, String type, String title) {
		super(selectionPortalPanel, widthPortlet, heightPortlet, type, title);
		centralImage = new HTML();
		chartImage = new HTML();
		buildPortlet();
		buildContentPanel();
		initalizeObjects();
		selectionPortalPanel.setSwithCenterPanel(this.contentPanel);
		this.contentPanel.setWidth(selectionPortalPanel.getWidthPortal());
		this.contentPanel.setHeight(selectionPortalPanel.getHeightPortal());
	}
	
	private void buildPortlet(){
		portlet.setId(type);
		portlet.setHeading(title);  
		portlet.setHeight(heightPortlet);  
	    configPanel(portlet);
	   

	}
	
	
	private void initalizeObjects() {
		
		
	}
	
	private void buildContentPanel(){
		contentPanel.setTopComponent(getToolBar());
//		contentPanel.add(getIframe(type));
	}
	
	private void configPanel(final ContentPanel panel) {  
		panel.setCollapsible(true);  
	    panel.setAnimCollapse(false);  

	    
	    panel.getHeader().addTool(new ToolButton("x-tool-gear", new SelectionListener<IconButtonEvent>() {  
	    @Override  
	    public void componentSelected(IconButtonEvent ce) {  
//	    	selchangeSelection(panel.getId());
	    	selectionPortalPanel.changeSelection(contentPanel, portlet);
	    	}  
	    }));  
	    


			  panel.layout();
	}

	private ToolBar getToolBar() {
		// create table
		Button createTable = new Button(BabelFish.print().createMultidimensionalTable());
		createTable.setToolTip(BabelFish.print().createMultidimensionalTable());
		toolbar.add(createTable);
		toolbar.add(new SeparatorToolItem());
		
		// reset
		IconButton reset = new IconButton("undo");
		reset.setToolTip(BabelFish.print().reset());
		toolbar.add(reset);
		toolbar.add(new SeparatorToolItem());
		
		// show flat data
		IconButton showFlatData = new IconButton("workspaceTable");
		showFlatData.setToolTip(BabelFish.print().showFlatData());
		showFlatData.setEnabled(false);
		toolbar.add(showFlatData);
		toolbar.add(new SeparatorToolItem());
		
		// send to project manager
		IconButton openInProjectManager = new IconButton("sendToProject");
		openInProjectManager.setToolTip("Open in the Workstation");
		toolbar.add(openInProjectManager);
		toolbar.add(new SeparatorToolItem());
		
		
	
		
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
	
//	private void changeSelection(String type) {
//		selectionPortalPanel.getCenterPanel().removeAll();
//    	selectionPortalPanel.getCenterPanel().add(getContentPanel());
//    	selectionPortalPanel.getCenterPanel().layout();	
//		int position = selectionPortalPanel.getSelectionPortal().getPortletColumn(portlet);
//		selectionPortalPanel.getSelectionPortal().remove(portlet, position);
//		selectionPortalPanel.getSelectionPortal().add(selectionPortalPanel.getCurrentPortlet(), position);
//		selectionPortalPanel.setCurrentPortlet(portlet);
//	}
	
	
}