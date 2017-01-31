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
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.custom.Portal;

public class CenterToolPanel {
	
	private ContentPanel showObject;
	
	private SelectionPortalPanel selectionPortal;

	
	public CenterToolPanel() {	
		showObject = new ContentPanel();
//		showObject.setWidth(900);
//		showObject.setHeight(500);
//		showObject.setHeaderVisible(false);
//		showObject.setBorders(false);
//		showObject.hide();
//		showObject.setScrollMode(Scroll.AUTO);
		//885
		selectionPortal = new SelectionPortalPanel(showObject, 885, 685, 220, 160); // dynamic
	}
	
	

	
	public VerticalPanel build(){
		VerticalPanel vpanel = new VerticalPanel();
//		vpanel.add(buildShowObjectPortal());
		vpanel.add(buildSelectionPortal());
		return vpanel;
	}
	
//	private VerticalPanel buildShowObjectPortal(){
//		VerticalPanel vpanel = new VerticalPanel();
////		vpanel.setSpacing(10);
//		vpanel.add(showObject);
//		showObject.setCollapsible(true);
//		return vpanel;
//	}
	
	private Portal buildSelectionPortal(){
		return selectionPortal.build();
	}


	public VerticalPanel build4Portlet() {
		VerticalPanel vpanel = new VerticalPanel();
		vpanel.setScrollMode(Scroll.AUTO);
//		vpanel.add(buildShowObjectPortal());
		
		selectionPortal.getVennContent().getContentPanel().hide();
		selectionPortal.getMapContent().getContentPanel().hide();
		selectionPortal.getOlapContent().getContentPanel().hide();
		selectionPortal.getChartContent().getContentPanel().hide();
		vpanel.add(selectionPortal.getVennContent().getContentPanel());
		vpanel.add(selectionPortal.getMapContent().getContentPanel());
		vpanel.add(selectionPortal.getOlapContent().getContentPanel());
		vpanel.add(selectionPortal.getChartContent().getContentPanel());
		vpanel.add(selectionPortal.build4Portlet());
	
		return vpanel;
	}
	
	

	public ContentPanel getShowObject() {
		return showObject;
	}




	public void setShowObject(ContentPanel showObject) {
		this.showObject = showObject;
	}




	public SelectionPortalPanel getSelectionPortal() {
		return selectionPortal;
	}




	public void setSelectionPortal(SelectionPortalPanel selectionPortal) {
		this.selectionPortal = selectionPortal;
	}

		  
	
	
}