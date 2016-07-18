package org.fao.fenix.web.modules.adam.client.view;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItemTree;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckCascade;
import com.google.gwt.user.client.ui.HTML;

public class ADAMCustomMultiSelection {
	
	public HorizontalPanel panel;


	public TreePanel<ListItemTree> treePanel;
	
	public TreeStore<ListItemTree> treeStore;
	
	private VerticalPanel infoPanel;
	

	private int treePanelHeight = 165; 
	
	public ADAMCustomMultiSelection() {
		treeStore = new TreeStore<ListItemTree>();
		treePanel = new TreePanel<ListItemTree>(treeStore);
		treePanel.setDisplayProperty("label");
		treePanel.setCheckable(true);
		treePanel.setWidth(285);
	//	treePanel.setHeight(treePanelHeight);
		treePanel.setCheckStyle(CheckCascade.CHILDREN);
		panel = new HorizontalPanel();
		infoPanel = new VerticalPanel();
		infoPanel.setHeight(150);
		infoPanel.setWidth(250);
		infoPanel.setScrollMode(Scroll.AUTO);
	}
	


	public HorizontalPanel buildPanel(String title) {		
		panel = new HorizontalPanel();
		panel.setSpacing(5);
		

		
		// selection panel
		VerticalPanel selectionPanel = new VerticalPanel();
		selectionPanel.setSpacing(5);
		
		//tree content panel
		ContentPanel treeContentPanel = getTreeContentPanel();
	
		// selected stuff
		VerticalPanel selectedStuffPanel = new VerticalPanel();
		selectedStuffPanel.setSpacing(7);
//		selectionPanel.setSpacing();
		selectedStuffPanel.add(new HTML("<b><font size='2pt'><u>Current Selection</u></font></b>"));
		infoPanel.add(new Html("<font size='1pt'>All selected</font>"));
		selectedStuffPanel.add(infoPanel);
		

		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
//		Html label = new Html("<b><font size='2pt'><u>" + "Select Geographic Areas" + "</u></font></b>");
//		selectionPanel.add(label);
		treeContentPanel.add(treePanel);
		selectionPanel.add(treeContentPanel);
//		h.add(applyGaul);
//		h.add(close);
		selectionPanel.add(h);
		panel.add(selectionPanel);
		panel.add(selectedStuffPanel);

		return panel;
	}
	
	
	

	public HorizontalPanel getPanel() {
		return panel;
	}



	public TreePanel<ListItemTree> getTreePanel() {
		return treePanel;
	}



	public VerticalPanel getInfoPanel() {
		return infoPanel;
	}
	
	private ContentPanel getTreeContentPanel(){
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setSize(ADAMConstants.TREE_CONTENT_CUSTOM_PANEL_WIDTH, ADAMConstants.TREE_CONTENT_PANEL_HEIGHT);
		panel.setScrollMode(Scroll.AUTO);
		
		return panel;
	}
	
}
