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

import java.util.HashMap;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.user.client.ui.HTML;

public class DatasetPortletPanel {

	private Portlet portlet;
	
	private int height;
	
	private int width;
	
	private Boolean expanded;
	
	private ComboBox<ListItem> comboBox = new ComboBox<ListItem>();
	
	VennPortalPanel vennPortalPanel;
	
	CenterToolPanel centerPanel;
	
	public DatasetPortletPanel(int width, int height, Boolean expanded, String title, VennPortalPanel vennPortalPanel, CenterToolPanel centerPanel) {
		portlet = new Portlet();
		portlet.setHeading(title);
		this.height = height;
		this.width = width;
		this.expanded = expanded;
		comboBox = new ComboBox<ListItem>();
		this.centerPanel = centerPanel;
		this.vennPortalPanel = vennPortalPanel;
	}
	

	

	
	public Portlet build(){
		portlet.setWidth(width);
		portlet.setHeight(height);
		portlet.setExpanded(expanded);
	    configPanel(portlet);
	    portlet.add(buildComboBox());
		return portlet;
	}
	
	private HorizontalPanel buildComboBox() {
		HorizontalPanel vpanel = new HorizontalPanel();
		vpanel.setSpacing(5);
		vpanel.add(new HTML("<b>Filter:</b>"));
		vpanel.add(comboBox);
		ListStore<ListItem> store = new ListStore<ListItem>();
		comboBox.setStore(store);
		comboBox.setFieldLabel("<b>Category: <b>");
//		comboBox.setEditable(true);
//		comboBox.setTypeAhead(true);
		comboBox.setDisplayField("name");
		comboBox.setEmptyText(BabelFish.print().pleaseSelect());
		comboBox.setAllowBlank(false);
		comboBox.setId("country");
		store.add(new ListItem("Agriculture", "31100"));
		HashMap<String, String> filters = new HashMap<String, String>();
		filters.put("31100", "Agriculture");
//		comboBox.addSelectionChangedListener(VennController.byCategories(filters,  "Agriculture", vennPortalPanel, centerPanel));
		return vpanel;
	}
	
	private void configPanel(final ContentPanel panel) {  
		panel.setCollapsible(true);  
	    panel.setAnimCollapse(false);  
	    panel.getHeader().addTool(new ToolButton("x-tool-gear"));  
	    panel.getHeader().addTool(  
	
	    new ToolButton("x-tool-close", new SelectionListener<IconButtonEvent>() {  
	    @Override  
	    public void componentSelected(IconButtonEvent ce) {  
	    	panel.setExpanded(true);
	    	}  
	    }));  
	}
}