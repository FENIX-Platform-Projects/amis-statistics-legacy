package org.fao.fenix.web.modules.amis.client.control;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.amis.client.view.home.AMISDatasource;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISAreasFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISMultiselectionFilter;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISMultiselectionItem;
import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISMultiselectionSelectedItem;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;



public class AMISMultiSelectionController {


	public static SelectionListener<ButtonEvent> closeWindow(final Window window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.close();
			}
		};
	}

	/** MULTISELECTION SELECT/DESELECT ALL **/
		public static SelectionListener<ButtonEvent> selectAllMultiselection(final AMISMultiselectionFilter filterPanel) {
			return new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					selectAllMultiselectionAgent(filterPanel);

				}
			};
		}

		private static void selectAllMultiselectionAgent(AMISMultiselectionFilter filterPanel) {
			for(AMISMultiselectionItem item : filterPanel.getMultiSelectionItems()) {
				item.getCheckbox().setValue(true);
				item.getPanel().setStyleName("visualize_multiselection_item_selected");
			}

			buildListCurrentSelectedCodes(filterPanel);
		}

		public static SelectionListener<ButtonEvent> deselectAllMultiselection(final AMISMultiselectionFilter filterPanel) {
			return new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					deselectAllMultiselectionAgent(filterPanel);
				}
			};
		}

		private static void deselectAllMultiselectionAgent(AMISMultiselectionFilter filterPanel) {
			for(AMISMultiselectionItem item : filterPanel.multiSelectionItems) {
				item.getCheckbox().setValue(false);
				item.getPanel().setStyleName("visualize_multiselection_item");
			}

			buildListCurrentSelectedCodes(filterPanel);

		}
		/*****/

		public static SelectionListener<ButtonEvent> updateMultiselectionFilter(final Window window, final AMISDatasource visualization, final AMISFilter filter, final List<AMISMultiselectionItem> multiSelectionItems, final String filterType, final AMISCodesModelData code, final HashMap<String, String> selectedCodes) {
			return new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					updateMultiselectionFilterAgent(window, visualization, filter, multiSelectionItems, filterType, code, selectedCodes);
				}
			};
		}





		private static void updateMultiselectionFilterAgent(Window window, final AMISDatasource visualization, AMISFilter filter, List<AMISMultiselectionItem> multiSelectionItems, final String filterType, final AMISCodesModelData code, final HashMap<String, String> selectedCodes) {

			System.out.println("updateFilterAgent");
			selectedCodes.clear();

			for(AMISMultiselectionItem item : multiSelectionItems) {
				if ( item.getCheckbox().getValue() ) {
					selectedCodes.put(item.getCode().getCode(), item.getCode().getLabel());
				}
			}


			if( selectedCodes.isEmpty() ) {
				FenixAlert.alert("Selection Error", "Please select some codes or close the window");
			}
			else{
				window.close();
				System.out.println("selectedCodes: " + selectedCodes);

			    HashMap<String, List<AMISQueryVO>> hmVOs = visualization.getSettings().getQvos();
				List<AMISQueryVO> qvos = hmVOs.get("DEFAULT");

				for (AMISQueryVO qvo : qvos ) {
					// TODO: pass also domain if needed
					AMISProductionController.addToFilters(qvo, selectedCodes, filterType, null);
				}

				// parameters
				AMISProductionController.buildParameters(visualization.getParametersPanel(), visualization.getFiltersPanel().getFilters());

				AMISProductionController.buildViews(visualization.getCenterPanel(), qvos, "aggregated");
			}


			// set the combo box
			if ( selectedCodes.size() > 1 ) {
				// set multiselection combo box
				List<AMISCodesModelData> store = filter.getStore().getModels();
				for(AMISCodesModelData value : store) {
					if ( value.getCode().equals("MULTI")) {
						filter.getCombo().enableEvents(false);
						filter.getCombo().setValue(value);
						filter.getCombo().enableEvents(true);
						break;
					}
				}

			}
			// single value
			else if ( selectedCodes.size() == 1 ) {
				List<AMISCodesModelData> store = filter.getStore().getModels();
				for(String key : selectedCodes.keySet() ) {
					Boolean added = false;
					for(AMISCodesModelData value : store) {
						if ( value.getCode().equals(key)) {
							filter.getCombo().enableEvents(false);
							filter.getCombo().setValue(value);
							filter.getCombo().enableEvents(true);
							added = true;
							break;
						}
					}
					if ( !added ) {
						// if any coded is retrieved as default if set the multiselection value
						// (i.e. if the combobox contains the regions and the multiselection is based on
						//  countries and the countries selected are just one)
						for(AMISCodesModelData value : store) {
							if ( value.getCode().equals("MULTI")) {
								filter.getCombo().enableEvents(false);
								filter.getCombo().setValue(value);
								filter.getCombo().enableEvents(true);
								break;
							}
						}
					}
				}
			}




		}



		public static SelectionListener<IconButtonEvent> multiselectionAggregatedAreas(final AMISDatasource visualization, final AMISAreasFilter filter, final String filterTypeMultisel, final AMISCodesModelData code) {
			return new SelectionListener<IconButtonEvent>() {
				public void componentSelected(IconButtonEvent ce) {
					multiselectionAggregatedAreasAgent(visualization, filter, filterTypeMultisel, code);
				}
			};
		}

		static void multiselectionAggregatedAreasAgent(final AMISDatasource visualization, final AMISAreasFilter filter, final String filterTypeMultisel, final AMISCodesModelData code) {
			filter.getMultiselection().build(visualization, filter, filterTypeMultisel, code);
		}

		public static SelectionListener<IconButtonEvent> multiselectionFilter(final AMISDatasource visualization, final AMISFilter filter, final String filterTypeMultisel, final AMISCodesModelData code) {
			return new SelectionListener<IconButtonEvent>() {
				public void componentSelected(IconButtonEvent ce) {
					multiselectionFilterAgent(visualization, filter, filterTypeMultisel, code);
				}
			};
		}


		public static void buildListCurrentSelectedCodes(AMISMultiselectionFilter filterPanel) {

				VerticalPanel panel = new VerticalPanel();
				panel.setSpacing(5);

				// title
				Html title = new Html("<div class='visualize_multiselection_title'>Current selected items:</div>");
				panel.add(title);
				panel.add(FormattingUtils.addHSpace(5));

				// get the list
				List<AMISMultiselectionItem> items = filterPanel.getMultiSelectionItems();

				for(AMISMultiselectionItem item : items) {
					if (item.getCheckbox().getValue()) {
						AMISMultiselectionSelectedItem itemSelected = new AMISMultiselectionSelectedItem(filterPanel);
						panel.add(itemSelected.build(item.getCode()));
						panel.add(FormattingUtils.addHSpace(2));

					}
				}
				panel.layout();

				// build the panel
				filterPanel.getCurrentSelection().removeAll();
				filterPanel.getCurrentSelection().add(panel);

				filterPanel.getCurrentSelection().layout();
	}
		private static void multiselectionFilterAgent(final AMISDatasource visualization, final AMISFilter filter, final String filterTypeMultisel, final AMISCodesModelData code) {
			filter.getMultiselection().build(visualization, filter, filterTypeMultisel, code);
	}
		
		/*** multiselection icon deselectItem **/
		
		public static SelectionListener<IconButtonEvent> deselectItem(final AMISMultiselectionFilter filterPanel, final AMISMultiselectionSelectedItem removedItem) {
			return new SelectionListener<IconButtonEvent>() {
				public void componentSelected(IconButtonEvent ce) {
					deselectItemAgent(filterPanel, removedItem);
				}
			};
		}
		
		public static void deselectItemAgent(AMISMultiselectionFilter filterPanel,  AMISMultiselectionSelectedItem removedItem) {
			
			// TODO: list of items, checkbox
			List<AMISMultiselectionItem> items = filterPanel.getMultiSelectionItems();
			
			for(AMISMultiselectionItem item : items) {
				if ( item.getCode().getCode().equals(removedItem.getCode().getCode())) {		
					item.getPanel().setStyleName("visualize_multiselection_item");
					item.getCheckbox().setValue(false);
					break;
				}
			}

			/** TODO: change it... **/
			buildListCurrentSelectedCodes(filterPanel);
			
		}


}
