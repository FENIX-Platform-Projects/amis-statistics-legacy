package org.fao.fenix.web.modules.adam.client.view;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.control.ADAMMultiSelectionController;
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

public class ADAMCustomComboMultiSelection {
	
	public HorizontalPanel panel;


	
	public TreePanel<ListItemTree> gaulTreePanel;
	
	public TreeStore<ListItemTree> gaulTreeStore;
	
	public TreePanel<ListItemTree> donorTreePanel;
	
	public TreeStore<ListItemTree> donorTreeStore;
	
	public TreePanel<ListItemTree> sectorTreePanel;
	
	public TreeStore<ListItemTree> sectorTreeStore;
	
	private Button applyGaul;
	
	private Button applyDonor;
	
	private Button applySector;
	
	private Button close;
	
	
	private Button selectAll;
	
	private Button deselectAll;
	
	private int treePanelHeight = 165; 
	
	public ADAMCustomComboMultiSelection() {
		gaulTreeStore = new TreeStore<ListItemTree>();
		gaulTreePanel = new TreePanel<ListItemTree>(gaulTreeStore);
		gaulTreePanel.setDisplayProperty("label");
		gaulTreePanel.setCheckable(true);
		gaulTreePanel.setWidth(285);
		//gaulTreePanel.setHeight(treePanelHeight);
		gaulTreePanel.setCheckStyle(CheckCascade.CHILDREN);

		donorTreeStore = new TreeStore<ListItemTree>();
		donorTreePanel = new TreePanel<ListItemTree>(donorTreeStore);
		donorTreePanel.setDisplayProperty("label");
		donorTreePanel.setCheckable(true);
		donorTreePanel.setWidth(285);
		//donorTreePanel.setHeight(treePanelHeight);
		donorTreePanel.setCheckStyle(CheckCascade.CHILDREN);
//		donorTreePanel.setBorders(true);
		
		sectorTreeStore = new TreeStore<ListItemTree>();
		sectorTreePanel = new TreePanel<ListItemTree>(sectorTreeStore);	
		sectorTreePanel.setDisplayProperty("label");
		sectorTreePanel.setCheckable(true);
		sectorTreePanel.setWidth(285);
		//sectorTreePanel.setHeight(treePanelHeight);
		sectorTreePanel.setCheckStyle(CheckCascade.CHILDREN);
		panel = new HorizontalPanel();
		
//		applyGaul = new Button("Apply");
//		applyDonor = new Button("Apply", ADAMController.applyDonorMultiselection(this));
//		applySector = new Button("Apply");
	}
	


	public HorizontalPanel buildGaulRegions(VerticalPanel infoPanel) {		
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
		selectedStuffPanel.add(new HTML("<b><font size='2pt'><u>" + "Current Selection" + "</u></font></b>"));
		infoPanel.add(new Html("<div class='small-content'>All selected<div>"));
		selectedStuffPanel.add(infoPanel);
		
		
		
		close = new Button("Close", ADAMMultiSelectionController.closeMultiselection());
		applyGaul = new Button("Apply", ADAMMultiSelectionController.applyMultiselection(gaulTreePanel, ADAMBoxMaker.countriesSelected, null, "COUNTRY"));
		
		selectAll = new Button("Select All", ADAMCustomController.selectAllEvent(gaulTreePanel, infoPanel));	
		deselectAll = new Button("Deselect All", ADAMCustomController.deselectAllEvent(gaulTreePanel, infoPanel));	
		
		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		h.add(selectAll);
		h.add(deselectAll);
		
//		Html label = new Html("<b><font size='2pt'><u>" + "Select Geographic Areas" + "</u></font></b>");
//		selectionPanel.add(label);
		treeContentPanel.add(gaulTreePanel);
		selectionPanel.add(treeContentPanel);
//		h.add(applyGaul);
//		h.add(close);
		selectionPanel.add(h);
		panel.add(selectionPanel);
		panel.add(selectedStuffPanel);

		
	

		
		
		return panel;
	}
	
	
	public HorizontalPanel buildDonorPanel(VerticalPanel infoPanel) {
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
		selectedStuffPanel.add(new HTML("<b><font size='2pt'><u>" + "Current Selection" + "</u></font></b>"));
		infoPanel.add(new Html("<div class='small-content'>All selected<div>"));
		selectedStuffPanel.add(infoPanel);
		

		close = new Button("Close", ADAMMultiSelectionController.closeMultiselection());
		applyDonor = new Button("Apply", ADAMMultiSelectionController.applyMultiselection(donorTreePanel, ADAMBoxMaker.donorsSelected, null, "DONOR"));
		
		selectAll = new Button("Select All", ADAMCustomController.selectAllEvent(donorTreePanel, infoPanel));	
		deselectAll = new Button("Deselect All", ADAMCustomController.deselectAllEvent(donorTreePanel, infoPanel));	
		
		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		h.add(selectAll);
		h.add(deselectAll);
		
//		Html label = new Html("<b><font size='2pt'><u>" + "Donor List" + "</u></font></b>");
//		selectionPanel.add(label);
		//tree content panel
		treeContentPanel.add(donorTreePanel);
		selectionPanel.add(treeContentPanel);

		selectionPanel.add(h);
		panel.add(selectionPanel);
		panel.add(selectedStuffPanel);
		

		return panel;
	}
	
	public HorizontalPanel buildSectorPanel(VerticalPanel infoPanel) {
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

		selectedStuffPanel.add(new HTML("<b><font size='2pt'><u>" + "Selected Sectors" + "</u></font></b>"));
		infoPanel.add(new Html("<div class='small-content'>All selected<div>"));
		selectedStuffPanel.add(infoPanel);	
		
		close = new Button("Close", ADAMMultiSelectionController.closeMultiselection());
		applySector = new Button("Apply", ADAMMultiSelectionController.applyMultiselection(sectorTreePanel, ADAMBoxMaker.sectorsSelected, null, "SECTOR"));
		
		selectAll = new Button("Select All", ADAMCustomController.selectAllEvent(sectorTreePanel, infoPanel));	
		deselectAll = new Button("Deselect All", ADAMCustomController.deselectAllEvent(sectorTreePanel, infoPanel));	
		
		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		h.add(selectAll);
		h.add(deselectAll);
		
		Html label = new Html("<b><font size='2pt'><u>" + "Current Selection" + "</u></font></b>");
		
		selectionPanel.add(label);
		treeContentPanel.add(sectorTreePanel);
		selectionPanel.add(treeContentPanel);
//		h.add(applySector);
//		h.add(close);
		selectionPanel.add(h);
		panel.add(selectionPanel);
		panel.add(selectedStuffPanel);

		return panel;
	}

	public HorizontalPanel getPanel() {
		return panel;
	}
	
	private ContentPanel getTreeContentPanel(){
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setSize(ADAMConstants.TREE_CONTENT_CUSTOM_PANEL_WIDTH, ADAMConstants.TREE_CONTENT_PANEL_HEIGHT);
		panel.setScrollMode(Scroll.AUTO);
		
		return panel;
	}
//	
//	
//	private
	
	
//	private voidBuil
	
}
