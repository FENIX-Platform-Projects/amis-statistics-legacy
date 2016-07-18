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

package org.fao.fenix.web.modules.qb.client.view;


import java.util.List;

import org.fao.fenix.web.modules.qb.client.control.ItemSelectorController;
import org.fao.fenix.web.modules.qb.common.model.DimensionModel;
import org.fao.fenix.web.modules.qb.common.services.QueryBuilderServiceEntry;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.StoreFilterField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ItemSelectorDialog {
	private ListStore<DimensionItemModel> store;
	private SimpleComboBox<String> sort;
	private CheckBoxListView<DimensionItemModel> view;
	private Dialog chooser;
	private ToggleButton selectAllItemsButton;

	public Dialog getChooser() {
		return chooser;
	}

	public void setChooser(Dialog chooser) {
		this.chooser = chooser;
	}

	private ListLoader<ListLoadResult<DimensionItemModel>> listLoader;


	public ListStore<DimensionItemModel> getStore() {
		return store;
	}

	public void setStore(ListStore<DimensionItemModel> store) {
		this.store = store;
	}

	public void setListLoader(BaseListLoader<ListLoadResult<DimensionItemModel>> listLoader) {
		this.listLoader = listLoader;
	}
	
	public ListLoader<ListLoadResult<DimensionItemModel>> getListLoader() {
		return listLoader;
	}
	
	public ItemSelectorDialog(final DimensionModel selectedDimension, final List<Long> datasetIds, final Text displaySelectedItemsHtml){

		ItemSelectorController.loadItems(this, datasetIds, selectedDimension);
		
		chooser = new Dialog();
		chooser.setHeading("Select Item(s)");
		chooser.setWidth(400);
		chooser.setHeight(300);
		chooser.setModal(true);
		chooser.setLayout(new BorderLayout());
		chooser.setBodyStyle("border: none;background: none");
		chooser.setBodyBorder(false);
		chooser.setButtons(Dialog.OKCANCEL);
		chooser.setHideOnButtonClick(true);
		chooser.getButtonById("ok").disable();
		
		
		ContentPanel main = new ContentPanel();
		main.setBorders(true);
		main.setBodyBorder(false);
		main.setLayout(new FitLayout());
		main.setHeaderVisible(false);

		ToolBar bar = new ToolBar();
		bar.add(new LabelToolItem("Filter:"));

		StoreFilterField<DimensionItemModel> field = new StoreFilterField<DimensionItemModel>() {
			@Override
			protected boolean doSelect(Store<DimensionItemModel> store, DimensionItemModel parent,
					DimensionItemModel record, String property, String filter) {
				String name = record.getName().toLowerCase();
				if (name.indexOf(filter.toLowerCase()) != -1) {
					return true;
				}
				return false;
			}

			@Override
			protected void onFilter() {
				super.onFilter();
				view.getSelectionModel().select(0, false);
			}

		};
		field.setWidth(100);
		field.bind(store);

		bar.add(field);
		bar.add(new SeparatorToolItem());
		bar.add(new LabelToolItem("Sort:"));

		sort = new SimpleComboBox<String>();
		sort.setTriggerAction(TriggerAction.ALL);
		sort.setEditable(false);
		sort.setForceSelection(true);
		sort.setWidth(90);
		sort.add("Ascending");
		sort.add("Descending");
		sort.setSimpleValue("Ascending");
		sort.addListener(Events.Select, ItemSelectorController.setSort(sort, store));
		bar.add(sort);

		main.setTopComponent(bar);

		view = new CheckBoxListView<DimensionItemModel>() {
			@Override
			protected DimensionItemModel prepareData(DimensionItemModel model) {
				String s = model.get("name");
				model.set("shortName", Format.ellipse(s, 40));
				return model;
			}

		};


		view.setStore(store);
		view.setDisplayProperty("name");
		view.setHeight(200);
		view.getSelectionModel().select(0, false);
		
		view.getSelectionModel().addListener(Events.SelectionChange, ItemSelectorController.setOnSelectionChanged(chooser, view));

		chooser.addListener(Events.Hide, ItemSelectorController.setButtonHandling(chooser, view, displaySelectedItemsHtml));
		
		main.add(view);
		
		
		
		selectAllItemsButton = new ToggleButton("Select All");
		selectAllItemsButton.setIconStyle("selectAllItems");
		selectAllItemsButton.setTitle("Click to select all items");
		selectAllItemsButton.addListener(Events.Toggle, ItemSelectorController.selectAllItems(this, view));
		
		chooser.getButtonBar().insert(selectAllItemsButton, 0);
		
			
		chooser.add(main);

		chooser.show();
		

	}
	
	public void ItemSelectorDialogOriginal(final DimensionModel selectedDimension, final List<Long> datasetIds, final PanelThreeFieldSelection panelThreeFieldSelection){
	
		RpcProxy<List<DimensionItemModel>> proxy = new RpcProxy<List<DimensionItemModel>>() {
			@Override
			protected void load(Object loadConfig, AsyncCallback<List<DimensionItemModel>> callback) {
				QueryBuilderServiceEntry.getInstance().getDimensionValues(datasetIds, selectedDimension.getDataType(), selectedDimension.getCodingSystemTitle(), selectedDimension.getCodingSystemRegion(), callback);
			}
		};

		ListLoader<ListLoadResult<DimensionItemModel>> loader = new BaseListLoader<ListLoadResult<DimensionItemModel>>(proxy);
		store = new ListStore<DimensionItemModel>(loader);

		loader.load();

		chooser = new Dialog();
		chooser.setHeading("Select List Item(s)");
		chooser.setWidth(400);
		chooser.setHeight(300);
		chooser.setModal(true);
		chooser.setLayout(new BorderLayout());
		chooser.setBodyStyle("border: none;background: none");
		chooser.setBodyBorder(false);
		
		chooser.setButtons(Dialog.OKCANCEL);
		chooser.setHideOnButtonClick(true);
		chooser.addListener(Events.Hide, new Listener<WindowEvent>() {
			public void handleEvent(WindowEvent be) {
				DimensionItemModel model = (DimensionItemModel)view.getSelectionModel().getSelectedItem();
				if (model != null) {
					if (be.getButtonClicked() == chooser.getButtonById("ok")) {
						Info.display("Checked Items", "There are " + view.getChecked().size()
								+ " items checked!");
					}
				}
			}
		});

		ContentPanel main = new ContentPanel();
		main.setBorders(true);
		main.setBodyBorder(false);
		main.setLayout(new FitLayout());
		main.setHeaderVisible(false);

		ToolBar bar = new ToolBar();
		bar.add(new LabelToolItem("Filter:"));

		StoreFilterField<DimensionItemModel> field = new StoreFilterField<DimensionItemModel>() {
			@Override
			protected boolean doSelect(Store<DimensionItemModel> store, DimensionItemModel parent,
					DimensionItemModel record, String property, String filter) {
				String name = record.getName().toLowerCase();
				if (name.indexOf(filter.toLowerCase()) != -1) {
					return true;
				}
				return false;
			}

			@Override
			protected void onFilter() {
				super.onFilter();
				view.getSelectionModel().select(0, false);
			}

		};
		field.setWidth(100);
		field.bind(store);

		bar.add(field);
		bar.add(new SeparatorToolItem());
		bar.add(new LabelToolItem("Sort:"));

		sort = new SimpleComboBox<String>();
		sort.setTriggerAction(TriggerAction.ALL);
		sort.setEditable(false);
		sort.setForceSelection(true);
		sort.setWidth(90);
		sort.add("Ascending");
		sort.add("Descending");
		sort.setSimpleValue("Ascending");
		sort.addListener(Events.Select, new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				sortX();
			}
		});

		bar.add(sort);

		main.setTopComponent(bar);

		view = new CheckBoxListView<DimensionItemModel>() {
			@Override
			protected DimensionItemModel prepareData(DimensionItemModel model) {
				String s = model.get("name");
				model.set("shortName", Format.ellipse(s, 40));
				return model;
			}

		};


		view.setStore(store);
		view.setDisplayProperty("name");
		view.setHeight(200);
		view.getSelectionModel().addListener(Events.SelectionChange,
				new Listener<SelectionChangedEvent<DimensionItemModel>>() {

			public void handleEvent(SelectionChangedEvent<DimensionItemModel> be) {
				onSelectionChangeX(be);
			}

		});



		main.add(view);

		chooser.add(main);




		chooser.show();
		view.getSelectionModel().select(0, false);

	}

	private void sortX() {
		String v = sort.getSimpleValue();
		if (v.equals("Ascending")) {
			store.sort("name", SortDir.ASC);
		} else {
			store.sort("name", SortDir.DESC);
		}
	}

	private void onSelectionChangeX(SelectionChangedEvent<DimensionItemModel> se) {
		if (se.getSelection().size() > 0) {
			chooser.getButtonById("ok").enable();
		} else {
			chooser.getButtonById("ok").disable();
		}
	}

}