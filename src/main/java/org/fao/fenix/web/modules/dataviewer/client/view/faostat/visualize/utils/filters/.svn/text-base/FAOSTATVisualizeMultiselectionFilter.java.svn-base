package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualize;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FAOSTATVisualizeMultiselectionFilter {
	
	public ContentPanel panel;
	
	public ContentPanel selectionPanel;
	
	public ContentPanel selectionPanelContent;
	
	public ContentPanel currentSelection;

	
	public ListStore<DWCodesModelData> store;
	
	public ListView<DWCodesModelData> list;
	
	public List<FAOSTATVisualizeMultiselectionItem> multiSelectionItems;

	public FAOSTATVisualizeMultiselectionFilter() {
		panel = new ContentPanel();		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		
		selectionPanel = new ContentPanel();		
		selectionPanel.setHeaderVisible(false);
		selectionPanel.setBodyBorder(false);	
		selectionPanel.setScrollMode(Scroll.AUTO);
		
//		selectionPanel.setHeight(300);
		
		selectionPanelContent = new ContentPanel();		
		selectionPanelContent.setHeaderVisible(false);
		selectionPanelContent.setBodyBorder(false);	
		selectionPanelContent.setScrollMode(Scroll.AUTO);
		
		selectionPanelContent.setHeight(280);
		
		currentSelection = new ContentPanel();		
		currentSelection.setHeaderVisible(false);
		currentSelection.setBodyBorder(false);	
		currentSelection.setScrollMode(Scroll.AUTO);

		currentSelection.setHeight(300);
		currentSelection.setWidth(280);
		
		multiSelectionItems = new ArrayList<FAOSTATVisualizeMultiselectionItem>();
		
		store = new ListStore<DWCodesModelData>();
		list = new ListView<DWCodesModelData>();

		list.setStore(store);
		list.setDisplayProperty("label");
		
		list.setWidth(280);
		list.setHeight(250);
		
	}
	
	
	/** TODO: constant width and height **/ 
	public void build(FAOSTATVisualize visualization, FAOSTATVisualizeFilter filter, String filterTypeMulti,  DWCodesModelData code) {
		Window window = new Window();
		window.setHeading(FAOSTATLanguage.print().multiselection());
//		window.setAutoHeight(true);
//		window.setAutoWidth(true);
		window.setModal(true);
		window.setSize(755, 380);
		
		System.out.println("SELECTED CODE BUILD: " + filter.getSelectedCodes());
		
		panel.setWidth(750);
		
		panel.removeAll();
		selectionPanel.removeAll();
//		selectionPanel.add(selectionPanelContent);
		
		selectionPanel.setWidth(350);
		

		panel.add(DataViewerClientUtils.addHSpace(5));
		panel.add(buildFilters(window, visualization, filter, filterTypeMulti, code));
		panel.add(DataViewerClientUtils.addHSpace(5));
		
		// selection & selected panel
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		
//		selectionPanel.add(list);
		h.add(selectionPanel);
		buildSelectionPanel(filterTypeMulti, code, filter.getSelectedCodes());
		
		panel.add(DataViewerClientUtils.addHSpace(10));
		h.add(buildCurrentSelection());
		
		panel.add(h);
		
		window.add(panel);
		
		window.show();
	}

	private void buildSelectionPanel(String filterTypeMulti,  DWCodesModelData code, HashMap<String, String> selectedCodes) {
		FAOSTATVisualizeByDomainController.loadCodingSystem(this, filterTypeMulti, code, selectedCodes);
//		FAOSTATVisualizeByDomainController.loadCodingSystem(this, list, store, filterTypeMulti, code);
	}
	
	private ContentPanel buildCurrentSelection() {
		currentSelection.removeAll();
		
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
//		Html title = new Html("<div class='multiselection_title'>Current selected items:</div>");
//		panel.add(title);
//		panel.setHeight(30);
		
		currentSelection.add(panel);
		return currentSelection;
	}
	
	private HorizontalPanel buildFilters(Window window, FAOSTATVisualize visualization, FAOSTATVisualizeFilter filter, String filterTypeMulti,  DWCodesModelData code) {
		HorizontalPanel panel = new HorizontalPanel();
		
		panel.setSpacing(5);
		
		panel.add(addApply(window, visualization, filter, filterTypeMulti, code, filter.getSelectedCodes()));
		panel.add(addCancel(window));
		panel.add(addSelectAll());
		panel.add(addDeselectAll());
		
		return panel;
	}
	
	private HorizontalPanel addSelectAll() {
		HorizontalPanel panel = new HorizontalPanel();
		
		Button button = new Button(FAOSTATLanguage.print().selectAll());
		button.setIconStyle("addIcon");
		
		button.addSelectionListener(FAOSTATVisualizeByDomainController.selectAllMultiselection(this));
		
		panel.add(button);
		
		return panel;
	}
	
	private HorizontalPanel addDeselectAll() {
		HorizontalPanel panel = new HorizontalPanel();
		
		Button button = new Button(FAOSTATLanguage.print().deselectAll());
		button.setIconStyle("removeIcon");
		
		button.addSelectionListener(FAOSTATVisualizeByDomainController.deselectAllMultiselection(this));

		panel.add(button);
		
		return panel;
	}
	
	private HorizontalPanel addApply(Window window, FAOSTATVisualize visualization, FAOSTATVisualizeFilter filter, String filterTypeMulti,  DWCodesModelData code, HashMap<String, String> selectedCodes) {
		HorizontalPanel panel = new HorizontalPanel();
		
		Button button = new Button(FAOSTATLanguage.print().apply());
		button.setIconStyle("greenThick");
		
		
		/** TODO: do the multiselection is a more efficient way...**/
		button.addSelectionListener(FAOSTATVisualizeByDomainController.updateMultiselectionFilter(window, visualization, filter, multiSelectionItems, filterTypeMulti.replace("_MULTI", ""), code, selectedCodes));
				
		panel.add(button);
		
		return panel;
	}
	
	private HorizontalPanel addCancel(Window window) {
		HorizontalPanel panel = new HorizontalPanel();
		
		Button button = new Button(FAOSTATLanguage.print().cancel());
		button.setIconStyle("delete");
		
		button.addSelectionListener(FAOSTATVisualizeByDomainController.closeWindow(window));

		
		panel.add(button);
		
		return panel;
	}

	public List<FAOSTATVisualizeMultiselectionItem> getMultiSelectionItems() {
		return multiSelectionItems;
	}

	public void setMultiSelectionItems(
			List<FAOSTATVisualizeMultiselectionItem> multiSelectionItems) {
		this.multiSelectionItems = multiSelectionItems;
	}

	public ContentPanel getSelectionPanel() {
		return selectionPanel;
	}

	public ContentPanel getCurrentSelection() {
		return currentSelection;
	}

	public ContentPanel getSelectionPanelContent() {
		return selectionPanelContent;
	}
	
	
	
	
}
