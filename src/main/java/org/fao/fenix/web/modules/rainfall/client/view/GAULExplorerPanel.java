package org.fao.fenix.web.modules.rainfall.client.view;

import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.rainfall.client.control.GAULExplorerController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.Tree.CheckCascade;
import com.google.gwt.user.client.ui.HTML;

@SuppressWarnings("deprecation")
public class GAULExplorerPanel {

	private ContentPanel panel;
	
	private Tree tree;
	
	private VerticalPanel wrapper;
	
	private final int SPACING = 5;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	public GAULExplorerPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		tree = new Tree();
		gaulStore = new ListStore<GaulModelData>(); 
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		wrapper = new VerticalPanel();
		wrapper.setSpacing(SPACING);
	}
	
	public ContentPanel build() {
		
		wrapper.add(buildOraclePanel());
		wrapper.add(buildTreePanel());
		wrapper.add(tree);
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setHeight("200px");
		panel.add(wrapper);
		panel.setHeight("400px");
		MEController.fillGaulStore(gaulStore);
		
		GAULExplorerController.initiateTreeWithCountries(tree);
		tree.addListener(Events.SelectionChange, GAULExplorerController.createRegionLeaves());
		tree.setCheckStyle(CheckCascade.NONE);
		
		return panel;
	}
	
	private HorizontalPanel buildOraclePanel() {
		HorizontalPanel oraclePanel = new HorizontalPanel();
		oraclePanel.setSpacing(SPACING);
		HTML label = new HTML( "<div style='font-family: Sans-Serif; font-weight:bold; color: #15428B;'>Select a Country: </div>");
		label.setWidth("150px");
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		gaulList.setWidth("500px");
		gaulList.addSelectionChangedListener(GAULExplorerController.oracleListener(this));
		oraclePanel.add(label);
		oraclePanel.add(gaulList);
		return oraclePanel;
	}
	
	private VerticalPanel buildTreePanel() {
		VerticalPanel treePanel = new VerticalPanel();
		tree.setCheckable(true);
		tree.getStyle().setLeafIconStyle("geoIcon");
		treePanel.setWidth("250px");
		treePanel.add(tree);
		return treePanel;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public Tree getTree() {
		return tree;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}
	
}