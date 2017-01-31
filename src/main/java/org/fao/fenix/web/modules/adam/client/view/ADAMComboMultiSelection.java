package org.fao.fenix.web.modules.adam.client.view;



import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.control.ADAMMultiSelectionController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItemTree;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckCascade;

public class ADAMComboMultiSelection {
	
	public HorizontalPanel panel;


	
	public static TreePanel<ListItemTree> gaulTreePanel;
	
	public static TreeStore<ListItemTree> gaulTreeStore;
	
	public static TreePanel<ListItemTree> donorTreePanel;
	
	public static TreeStore<ListItemTree> donorTreeStore;
	
	public static TreePanel<ListItemTree> sectorTreePanel;
	
	public static TreeStore<ListItemTree> sectorTreeStore;
	
	public static TreePanel<ListItemTree> soTreePanel;
	
	public static TreeStore<ListItemTree> soTreeStore;
	
    public static TreePanel<ListItemTree> subSectorTreePanel;
	
	public static TreeStore<ListItemTree> subSectorTreeStore;
	
	public static TreePanel<ListItemTree> orTreePanel;
		
    public static TreeStore<ListItemTree> orTreeStore;
	
	private Button applyGaul;
	
	private Button applyDonor;
	
	private Button applySector;
	
	private Button applySo;
	
	private Button applySubSector;
	
	private Button applyOr;

	
	private Button close;
	
	private static int treePanelHeight = 165; 
	
	private static String applyButtonLabel = "<font color='green'><b>Apply</b></font>";
	
	static {
		gaulTreeStore = new TreeStore<ListItemTree>();
		gaulTreePanel = new TreePanel<ListItemTree>(gaulTreeStore);
		gaulTreePanel.setDisplayProperty("label");
		gaulTreePanel.setCheckable(true);
		gaulTreePanel.setWidth(ADAMConstants.TREE_PANEL_WIDTH);
		//gaulTreePanel.setHeight(treePanelHeight);
		gaulTreePanel.setCheckStyle(CheckCascade.CHILDREN);

		donorTreeStore = new TreeStore<ListItemTree>();
		donorTreePanel = new TreePanel<ListItemTree>(donorTreeStore);
		donorTreePanel.setDisplayProperty("label");
		donorTreePanel.setCheckable(true);
		donorTreePanel.setWidth(ADAMConstants.TREE_PANEL_WIDTH);
		//donorTreePanel.setHeight(treePanelHeight);
		donorTreePanel.setCheckStyle(CheckCascade.CHILDREN);
//		donorTreePanel.setBorders(true);
		
		sectorTreeStore = new TreeStore<ListItemTree>();
		sectorTreePanel = new TreePanel<ListItemTree>(sectorTreeStore);	
		sectorTreePanel.setDisplayProperty("label");
		sectorTreePanel.setCheckable(true);
		sectorTreePanel.setWidth(ADAMConstants.TREE_PANEL_WIDTH);
		//sectorTreePanel.setHeight(treePanelHeight);
		sectorTreePanel.setCheckStyle(CheckCascade.CHILDREN);
		
		
		soTreeStore = new TreeStore<ListItemTree>();
		soTreePanel = new TreePanel<ListItemTree>(soTreeStore);	
		soTreePanel.setDisplayProperty("label");
		soTreePanel.setCheckable(true);
		soTreePanel.setWidth(ADAMConstants.TREE_PANEL_WIDTH);
		//sectorTreePanel.setHeight(treePanelHeight);
		soTreePanel.setCheckStyle(CheckCascade.CHILDREN);
		
		
		subSectorTreeStore = new TreeStore<ListItemTree>();
		subSectorTreePanel = new TreePanel<ListItemTree>(subSectorTreeStore);	
		subSectorTreePanel.setDisplayProperty("label");
		subSectorTreePanel.setCheckable(true);
		subSectorTreePanel.setWidth(ADAMConstants.TREE_PANEL_WIDTH);
		subSectorTreePanel.setCheckStyle(CheckCascade.CHILDREN);
		
		
		orTreeStore = new TreeStore<ListItemTree>();
		orTreePanel = new TreePanel<ListItemTree>(orTreeStore);	
		orTreePanel.setDisplayProperty("label");
		orTreePanel.setCheckable(true);
		orTreePanel.setWidth(ADAMConstants.TREE_PANEL_WIDTH);
		orTreePanel.setCheckStyle(CheckCascade.CHILDREN);
		
		
//		applyGaul = new Button("Apply");
//		applyDonor = new Button("Apply", ADAMController.applyDonorMultiselection(this));
//		applySector = new Button("Apply");
	}
	
	
	public ADAMComboMultiSelection() {
		panel = new HorizontalPanel();
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
		Html label = new Html("Selected Repicipient Countries");
		label.setStyleName("filters-text-blue");
		selectedStuffPanel.add(label);
		selectedStuffPanel.add(infoPanel);
		
		
		close = new Button("Close", ADAMMultiSelectionController.closeMultiselection());
		applyGaul = new Button(applyButtonLabel, ADAMMultiSelectionController.applyMultiselection(gaulTreePanel, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.countriesRegionsSelected, "COUNTRY"));		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		label = new Html("Select the Geographic Areas/Recipient Countries (expand folders) then click on "+applyButtonLabel);
		label.setStyleName("filters-text-blue");
		
		Button selectAll = new Button("Select All", ADAMCustomController.selectAllEvent(gaulTreePanel, infoPanel));	
		Button deselectAll = new Button("Deselect All", ADAMCustomController.deselectAllEvent(gaulTreePanel, infoPanel));	

		
		selectionPanel.add(label);
		treeContentPanel.add(gaulTreePanel);
		selectionPanel.add(treeContentPanel);
		h.add(applyGaul);
		h.add(selectAll);
		h.add(deselectAll);
		h.add(close);
		
		
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
		Html label = new Html("Selected Resource Partners");
		label.setStyleName("filters-text-blue");
		selectedStuffPanel.add(label);
		selectedStuffPanel.add(infoPanel);
		
		close = new Button("Close", ADAMMultiSelectionController.closeMultiselection());
		applyDonor = new Button(applyButtonLabel, ADAMMultiSelectionController.applyMultiselection(donorTreePanel, ADAMBoxMaker.donorsSelected, null, "DONOR"));
		Button selectAll = new Button("Select All", ADAMCustomController.selectAllEvent(donorTreePanel, infoPanel));	
		Button deselectAll = new Button("Deselect All", ADAMCustomController.deselectAllEvent(donorTreePanel, infoPanel));	

		
		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		label = new Html("Select the Resource Partners then click on "+applyButtonLabel);
		label.setStyleName("filters-text-blue");
		selectionPanel.add(label);
		treeContentPanel.add(donorTreePanel);
		selectionPanel.add(treeContentPanel);
		h.add(applyDonor);
		h.add(selectAll);
		h.add(deselectAll);
		h.add(close);

		
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
		Html label = new Html("<u>Selected Sectors</u>");
		label.setStyleName("filters-text-blue");
		selectedStuffPanel.add(label);
		selectedStuffPanel.add(infoPanel);
		
		close = new Button("Close", ADAMMultiSelectionController.closeMultiselection());
		applySector = new Button(applyButtonLabel, ADAMMultiSelectionController.applyMultiselection(sectorTreePanel, ADAMBoxMaker.sectorsSelected, null, "SECTOR"));
		Button selectAll = new Button("Select All", ADAMCustomController.selectAllEvent(sectorTreePanel, infoPanel));	
		Button deselectAll = new Button("Deselect All", ADAMCustomController.deselectAllEvent(sectorTreePanel, infoPanel));	

		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		label = new Html("Select the Sectors then click on "+applyButtonLabel);
		label.setStyleName("filters-text-blue");
		selectionPanel.add(label);
		treeContentPanel.add(sectorTreePanel);
		selectionPanel.add(treeContentPanel);
		h.add(applySector);
		h.add(selectAll);
		h.add(deselectAll);
		h.add(close);
		selectionPanel.add(h);
		panel.add(selectionPanel);
		panel.add(selectedStuffPanel);
		
	
		return panel;
	}
	
	
	public HorizontalPanel buildSOPanel(VerticalPanel infoPanel) {
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
		Html label = new Html("Selected Strategic Objectives");
		label.setStyleName("filters-text-blue");
		selectedStuffPanel.add(label);
		selectedStuffPanel.add(infoPanel);
		
		close = new Button("Close", ADAMMultiSelectionController.closeMultiselection());
		applySo = new Button(applyButtonLabel, ADAMMultiSelectionController.applyMultiselection(soTreePanel, ADAMBoxMaker.soSelected, null, "SO"));
		Button selectAll = new Button("Select All", ADAMCustomController.selectAllEvent(soTreePanel, infoPanel));	
		Button deselectAll = new Button("Deselect All", ADAMCustomController.deselectAllEvent(soTreePanel, infoPanel));	

		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		label = new Html("Select the Strategic Objectives then click on "+applyButtonLabel);
		label.setStyleName("filters-text-blue");
		selectionPanel.add(label);
		treeContentPanel.add(soTreePanel);
		selectionPanel.add(treeContentPanel);
		h.add(applySo);
		h.add(selectAll);
		h.add(deselectAll);
		h.add(close);
		selectionPanel.add(h);
		panel.add(selectionPanel);
		panel.add(selectedStuffPanel);
		
	
		return panel;
	}

	
	public HorizontalPanel buildSubSectorPanel(VerticalPanel infoPanel) {
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
		Html label = new Html("Selected Sub-Sectors");
		label.setStyleName("filters-text-blue");
		selectedStuffPanel.add(label);
		selectedStuffPanel.add(infoPanel);
		
		close = new Button("Close", ADAMMultiSelectionController.closeMultiselection());
		applySubSector = new Button(applyButtonLabel, ADAMMultiSelectionController.applyMultiselection(subSectorTreePanel, ADAMBoxMaker.subSectorsSelected, null, "SUB-SECTOR"));
		Button selectAll = new Button("Select All", ADAMCustomController.selectAllEvent(subSectorTreePanel, infoPanel));	
		Button deselectAll = new Button("Deselect All", ADAMCustomController.deselectAllEvent(subSectorTreePanel, infoPanel));	

		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		label = new Html("Select the Sub-Sectors then click on "+applyButtonLabel);
		label.setStyleName("filters-text-blue");
		selectionPanel.add(label);
		treeContentPanel.add(subSectorTreePanel);
		selectionPanel.add(treeContentPanel);
		h.add(applySubSector);
		h.add(selectAll);
		h.add(deselectAll);
		h.add(close);
		selectionPanel.add(h);
		panel.add(selectionPanel);
		panel.add(selectedStuffPanel);
		
	
		return panel;
	}
	
	public HorizontalPanel buildORPanel(VerticalPanel infoPanel) {
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
		Html label = new Html("Selected Organizational Results");
		label.setStyleName("filters-text-blue");
		selectedStuffPanel.add(label);
		selectedStuffPanel.add(infoPanel);
		
		close = new Button("Close", ADAMMultiSelectionController.closeMultiselection());
		applyOr = new Button(applyButtonLabel, ADAMMultiSelectionController.applyMultiselection(orTreePanel, ADAMBoxMaker.orSelected, null, "OR"));
		Button selectAll = new Button("Select All", ADAMCustomController.selectAllEvent(orTreePanel, infoPanel));	
		Button deselectAll = new Button("Deselect All", ADAMCustomController.deselectAllEvent(orTreePanel, infoPanel));	

		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		label = new Html("Select the Organizational Results then click on "+applyButtonLabel);
		label.setStyleName("filters-text-blue");
		selectionPanel.add(label);
		treeContentPanel.add(orTreePanel);
		selectionPanel.add(treeContentPanel);
		h.add(applyOr);
		h.add(selectAll);
		h.add(deselectAll);
		h.add(close);
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
		panel.setSize(ADAMConstants.TREE_CONTENT_PANEL_WIDTH, ADAMConstants.TREE_CONTENT_PANEL_HEIGHT);
		panel.setScrollMode(Scroll.AUTO);
		
		return panel;
	}

	
}
