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
package org.fao.fenix.web.modules.qb.client.control;

import java.util.List;

import org.fao.fenix.web.modules.qb.client.view.ItemSelectorDialog;
import org.fao.fenix.web.modules.qb.common.model.DimensionModel;
import org.fao.fenix.web.modules.qb.common.services.QueryBuilderServiceEntry;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ItemSelectorController {

	public static void loadItems(final ItemSelectorDialog itemSelectorDialog, final List<Long> datasetIds, final DimensionModel selectedDimension) {
		RpcProxy<List<DimensionItemModel>> proxy = new RpcProxy<List<DimensionItemModel>>() {
			@Override
			protected void load(Object loadConfig, AsyncCallback<List<DimensionItemModel>> callback) {
				QueryBuilderServiceEntry.getInstance().getDimensionValues(datasetIds, selectedDimension.getDataType(), selectedDimension.getCodingSystemTitle(), selectedDimension.getCodingSystemRegion(), callback);
			}
		};

		itemSelectorDialog.setListLoader(new BaseListLoader<ListLoadResult<DimensionItemModel>>(proxy));
		itemSelectorDialog.setStore(new ListStore<DimensionItemModel>(itemSelectorDialog.getListLoader()));
		itemSelectorDialog.getListLoader().load();
	}
	
	
	public static Listener<SelectionChangedEvent<DimensionItemModel>> setOnSelectionChanged(final Dialog chooser, final CheckBoxListView<DimensionItemModel> view) {
		return new Listener<SelectionChangedEvent<DimensionItemModel>>() {
			public void handleEvent(SelectionChangedEvent<DimensionItemModel> be) {
				onSelectionChange(be, chooser, view);
			}
		};
	}
	
	private static void onSelectionChange(SelectionChangedEvent<DimensionItemModel> se, Dialog chooser, CheckBoxListView<DimensionItemModel> view) {
	//se.getSelection().size() > 0 &&
	
		
	//	view.getChecked(model, false);
	
		if (se.getSelection().size() > 0) {
			chooser.getButtonById("ok").enable();
		} else {
			chooser.getButtonById("ok").disable();
		}
	}

	
	public static Listener<FieldEvent> setSort(final SimpleComboBox<String> sort, final ListStore<DimensionItemModel> store) {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				sort(sort, store);
			}
		};
	}
	
		private static void sort(final SimpleComboBox<String> sort, final ListStore<DimensionItemModel> store) {
			String v = sort.getSimpleValue();
			if (v.equals("Ascending")) {
				store.sort("name", SortDir.ASC);
			} else {
				store.sort("name", SortDir.DESC);
			}
		}
	
		public static Listener<WindowEvent> setButtonHandling(final Dialog chooser, final CheckBoxListView<DimensionItemModel> view, final Text displaySelectedItemsHtml) {
			return new Listener<WindowEvent>() {
				public void handleEvent(WindowEvent be) {
					
					if (view.getChecked()!=null) {
						chooser.getButtonById("ok").enable();

						Info.display("Checked Items", "There are " + view.getChecked().size()
								+ " items checked!");

						
						//DimensionItemModel model = (DimensionItemModel)view.getSelectionModel().getSelectedItems();
						//if (model != null) {
							if (be.getButtonClicked() == chooser.getButtonById("ok")) {
							//	Info.display("Checked Items", "There are " + view.getChecked().size()
								//		+ " items checked!");

								StringBuilder dimensionBuilder = new StringBuilder();
								if(view.getChecked().size() == view.getItemCount()) {
									displaySelectedItemsHtml.setText("All "+view.getChecked().size()+" Items Selected");		
								}
								else {
								for(int i=0; i < view.getChecked().size(); i++){		
									dimensionBuilder.append(view.getChecked().get(i).getName()+", </br>");
								}

								displaySelectedItemsHtml.setText(dimensionBuilder.toString());	
								}
							}

						//} 
					}//else {
						//chooser.getButtonById("ok").disable();
						//Info.display("NO Checked Items", "There are NO ITEMS SELECTED");
						
						
					//}
				}
			};
		}
		
		public static Listener<ButtonEvent> selectAllItems(final ItemSelectorDialog window, final CheckBoxListView<DimensionItemModel> view) {
			return new Listener<ButtonEvent>() {
				public void handleEvent(ButtonEvent be) {
					ToggleButton btn = (ToggleButton)be.getButton();
					if(btn.isPressed()) {
						btn.setText("Undo Select All");
						btn.setTitle("Click to undo");
						btn.setIconStyle("undo");
						for(DimensionItemModel model: window.getStore().getModels())
							view.setChecked(model, true);
						
						
					} else {
						btn.setText("Select All");
						btn.setTitle("Click to select all items");
						btn.setIconStyle("selectAllItems");
						for(DimensionItemModel model: window.getStore().getModels())
							view.setChecked(model, false);
					}
				}
			};
		}

}