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


import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class SelectionPortalContent {
	
	Portlet portlet;
	
	ContentPanel contentPanel;
	
	int heightPortlet;
	
	int widthPortlet;

	String type;
	
	String title;
	
	SelectionPortalPanel selectionPortalPanel;
	
	ToolBar toolbar;
	
	
	public SelectionPortalContent(SelectionPortalPanel selectionPortalPanel, int widthPortlet, int heightPortlet, final String type, String title) {
		this.heightPortlet = heightPortlet;
		this.widthPortlet = widthPortlet;
		this.type = type;
		this.title = title;
		this.selectionPortalPanel = selectionPortalPanel;
		contentPanel = new ContentPanel();
		contentPanel.setHeaderVisible(false);
		portlet = new Portlet();  
		toolbar = new ToolBar();
		portlet.setPinned(true);

	}
	
	public Portlet getPortlet() {
		return portlet;
	}



	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	



	
	
	
}