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
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItemTree;
import org.fao.fenix.web.modules.venn.client.control.VennController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckCascade;
import com.google.gwt.user.client.ui.HTML;

public class DimensionPortletPanel {

	private Portlet dimension;
	
	private int height;
	
	private int width;
	
	private String comboBoxLength = "150px";
	
	private String labelsLength = "110px";
	
	private Boolean expanded;
	
	private ComboBox<ListItem> comboOrganizationsType = new ComboBox<ListItem>();
	
	private ListStore<ListItem> storeOrganizationsType = new ListStore<ListItem>();
	
	private ComboBox<ListItem> comboOrganizations = new ComboBox<ListItem>();
	
	private ListStore<ListItem> storeOrganizations = new ListStore<ListItem>();
	
	private ContentPanel usedPanel;
	
	private Window showWindowLabel;
	
	private TreeStore<ListItemTree> treeStore;
	
	private TreePanel<ListItemTree> treePanel;
	
	private Button selectAll;
	
	private Button deselectAll;
	
	
	@SuppressWarnings("deprecation")
	public DimensionPortletPanel(int height, int width, Boolean expanded, String type, String title) {
		dimension = new Portlet();
		dimension.setId(type);
		dimension.setHeading(title);
		this.height = height;
		this.width = width;
		this.expanded = expanded;
		comboOrganizationsType = new ComboBox<ListItem>();
		storeOrganizationsType = new ListStore<ListItem>();
		comboOrganizations = new ComboBox<ListItem>();
		storeOrganizations = new ListStore<ListItem>();

		usedPanel = new ContentPanel();
		usedPanel.setHeaderVisible(false);
		usedPanel.setBorders(false);
		
		treeStore = new TreeStore<ListItemTree>();  
	    treePanel = new TreePanel<ListItemTree>(treeStore);  
	    treePanel.setDisplayProperty("label");
	    treePanel.setCheckable(true);
		treePanel.setWidth(255);
		treePanel.setHeight(235);
		treePanel.setCheckStyle(CheckCascade.CHILDREN);

		showWindowLabel = new Window();
		
		selectAll = new Button("Select All");
		deselectAll = new Button("Deselect All");
		
	}
	
	private void enancheButtons() {
		selectAll.addSelectionListener(VennController.selectAllEvent(treePanel));
		deselectAll.addSelectionListener(VennController.deselectAllEvent(treePanel));
	}

	
	public Portlet build(VennPortalPanel vennPortal, CenterToolPanel centerPanel, Boolean useXml, String xml, String language, String set) {
	    dimension.setWidth(width);
	    dimension.setHeight(height);
	    configPanel(dimension);
	    dimension.add(buildOrganizationsTypeComboBox());
	    dimension.add(buildOrganizationsComboBox());
	    dimension.add(buildTreePanel());
//	    dimension.setBottomComponent(selectAll);
	    dimension.setExpanded(expanded);
	    enancheButtons();
	    initializeComboBox(vennPortal, centerPanel, useXml, xml, language, set);
		return dimension;
	}
	
	private void initializeComboBox(VennPortalPanel vennPortal, CenterToolPanel centerPanel,Boolean useXml, String xml, String language, String set){
		// this is disabled to retrive the codes from configuration
		if ( useXml ) { 
			System.out.println("use xml");
			comboOrganizationsType.disableEvents(true);
			comboOrganizations.disableEvents(true);
			VennController.initializeWithXMLConfiguration(vennPortal, centerPanel, this, language, xml, set);
		}
		else {
			System.out.println("not use xml");
			VennController.initializeOrganizationType(this);
		}
		
			
	}
	
	
	private void configPanel(final ContentPanel panel) {  
		panel.setCollapsible(true);  
//	    panel.setAnimCollapse(false);  
//	    panel.getHeader().addTool(new ToolButton("x-tool-gear"));  
//	    panel.getHeader().addTool(  
//	
//	    new ToolButton("x-tool-close", new SelectionListener<IconButtonEvent>() {  
//	    @Override  
//	    public void componentSelected(IconButtonEvent ce) {  
//	    	panel.setExpanded(true);
//	    	}  
//	    }));  
	}
	
	private HorizontalPanel buildOrganizationsTypeComboBox() {
		HorizontalPanel vpanel = new HorizontalPanel();
		vpanel.setSpacing(5);
		HTML label = new HTML( "<b>Organization Type:</b>");
		label.setWidth(labelsLength);
		comboOrganizationsType.setWidth(comboBoxLength);
		vpanel.add(label);
		vpanel.add(comboOrganizationsType);
		comboOrganizationsType.setStore(storeOrganizationsType);
		comboOrganizationsType.setTypeAhead(true);
		comboOrganizationsType.setEditable(true);
		comboOrganizationsType.setDisplayField("name");
		comboOrganizationsType.setEmptyText(BabelFish.print().pleaseSelect());
		comboOrganizationsType.setAllowBlank(false);
		comboOrganizationsType.setId("comboOrganizationsType");
		
		comboOrganizationsType.setTriggerAction(TriggerAction.ALL);
		comboOrganizationsType.addSelectionChangedListener(VennController.getOrganizations(storeOrganizations, comboOrganizations, comboOrganizationsType));
		return vpanel;
	}
	
	private HorizontalPanel buildOrganizationsComboBox() {
		HorizontalPanel vpanel = new HorizontalPanel();
		vpanel.setSpacing(5);
		HTML label = new HTML("<b>Organization:</b>");
		label.setWidth(labelsLength);
		comboOrganizations.setWidth(comboBoxLength);
		vpanel.add(label);
		vpanel.add(comboOrganizations);
		comboOrganizations.setStore(storeOrganizations);
		comboOrganizations.setEditable(true);
		comboOrganizations.setDisplayField("name");
		comboOrganizations.setEmptyText(BabelFish.print().pleaseSelect());
		comboOrganizations.setAllowBlank(false);
		comboOrganizations.setId("comboOrganizations");
		comboOrganizations.setTriggerAction(TriggerAction.ALL);
		
		comboOrganizations.addSelectionChangedListener(VennController.getSOIFA(comboOrganizationsType, comboOrganizations, treePanel, treeStore));
		return vpanel;
	}


	@SuppressWarnings("unchecked")
	private VerticalPanel buildTreePanel() {

		HTML label = new HTML("<b>Strategies:  </b>");
		label.setWidth(labelsLength);
		VerticalPanel v = new VerticalPanel();  
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		v.setSpacing(5);
		v.add(label);
//		v.add(selectAll);
		v.add(treePanel);
		v.setHorizontalAlign(HorizontalAlignment.LEFT);
		v.setVerticalAlign(VerticalAlignment.TOP);
		h.add(selectAll);
		h.add(deselectAll);
		v.add(h);
//		v.setBorders(true);

		return v;
	}

	

	
	

	public ContentPanel getUsedPanel() {
		return usedPanel;
	}



	public ComboBox<ListItem> getComboOrganizationsType() {
		return comboOrganizationsType;
	}



	public ListStore<ListItem> getStoreOrganizationsType() {
		return storeOrganizationsType;
	}



	public ComboBox<ListItem> getComboOrganizations() {
		return comboOrganizations;
	}



	public ListStore<ListItem> getStoreOrganizations() {
		return storeOrganizations;
	}



	public void setUsedPanel(ContentPanel usedPanel) {
		this.usedPanel = usedPanel;
	}



	public TreeStore<ListItemTree> getTreeStore() {
		return treeStore;
	}



	public TreePanel<ListItemTree> getTreePanel() {
		return treePanel;
	}



	
	
}