package org.fao.fenix.web.modules.amis.client.view.utils.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.amis.client.control.AMISMultiSelectionController;
import org.fao.fenix.web.modules.amis.client.control.AMISProductionController;
import org.fao.fenix.web.modules.amis.client.view.home.AMISDatasource;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;


import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;

public class AMISMultiselectionFilter {

	public ContentPanel panel;

	public ContentPanel selectionPanel;

	public ContentPanel selectionPanelContent;

	public ContentPanel currentSelection;


	public ListStore<AMISCodesModelData> store;

	public ListView<AMISCodesModelData> list;

	public List<AMISMultiselectionItem> multiSelectionItems;

	public AMISMultiselectionFilter() {
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

		multiSelectionItems = new ArrayList<AMISMultiselectionItem>();

		store = new ListStore<AMISCodesModelData>();
		list = new ListView<AMISCodesModelData>();

		list.setStore(store);
		list.setDisplayProperty("label");

		list.setWidth(280);
		list.setHeight(250);

	}

	/** TODO: pass generic **/
	public void build(final AMISDatasource visualization, AMISFilter filter, String filterTypeMulti,  AMISCodesModelData code) {
		Window window = new Window();
		window.setHeading("Multiselection");
		window.setAutoHeight(true);
		window.setAutoWidth(true);
		window.setModal(true);
//		window.setSize(600, 400);

		System.out.println("SELECTED CODE BUILD: " + filter.getSelectedCodes());

		panel.setWidth(750);

		panel.removeAll();
		selectionPanel.removeAll();
//		selectionPanel.add(selectionPanelContent);

		selectionPanel.setWidth(350);


		panel.add(FormattingUtils.addHSpace(5));
		panel.add(buildFilters(window, visualization, filter, filterTypeMulti, code));
		panel.add(FormattingUtils.addHSpace(5));

		// selection & selected panel
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);

//		selectionPanel.add(list);
		h.add(selectionPanel);
		buildSelectionPanel(filterTypeMulti, code, filter.getSelectedCodes());

		panel.add(FormattingUtils.addHSpace(10));
		h.add(buildCurrentSelection());

		panel.add(h);

		window.add(panel);

		window.show();
	}

	private void buildSelectionPanel(String filterTypeMulti,  AMISCodesModelData code, HashMap<String, String> selectedCodes) {
		AMISProductionController.loadCodingSystem(this, filterTypeMulti, code, selectedCodes);
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

	private HorizontalPanel buildFilters(Window window, final AMISDatasource visualization, AMISFilter filter, String filterTypeMulti,  AMISCodesModelData code) {
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

		Button button = new Button("Select All");
		button.setIconStyle("addIcon");

		button.addSelectionListener(AMISMultiSelectionController.selectAllMultiselection(this));

		panel.add(button);

		return panel;
	}

	private HorizontalPanel addDeselectAll() {
		HorizontalPanel panel = new HorizontalPanel();

		Button button = new Button("Deselect All");
		button.setIconStyle("removeIcon");

		button.addSelectionListener(AMISMultiSelectionController.deselectAllMultiselection(this));

		panel.add(button);

		return panel;
	}

	private HorizontalPanel addApply(Window window, AMISDatasource visualization, AMISFilter filter, String filterTypeMulti,  AMISCodesModelData code, HashMap<String, String> selectedCodes) {
		HorizontalPanel panel = new HorizontalPanel();

		Button button = new Button("Apply");
		button.setIconStyle("greenThick");


		/** TODO: do the multiselection is a more efficient way...**/
		button.addSelectionListener(AMISMultiSelectionController.updateMultiselectionFilter(window, visualization, filter, multiSelectionItems, filterTypeMulti.replace("_MULTI", ""), code, selectedCodes));

		panel.add(button);

		return panel;
	}

	private HorizontalPanel addCancel(Window window) {
		HorizontalPanel panel = new HorizontalPanel();

		Button button = new Button("Cancel");
		button.setIconStyle("delete");

		button.addSelectionListener(AMISMultiSelectionController.closeWindow(window));


		panel.add(button);

		return panel;
	}

	public List<AMISMultiselectionItem> getMultiSelectionItems() {
		return multiSelectionItems;
	}

	public void setMultiSelectionItems(
			List<AMISMultiselectionItem> multiSelectionItems) {
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
