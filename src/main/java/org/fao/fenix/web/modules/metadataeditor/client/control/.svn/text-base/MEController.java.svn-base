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
package org.fao.fenix.web.modules.metadataeditor.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.utils.CategoryList;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.view.ColumnDefinitionPanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.DatasetTypePanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.ExtraInfoPanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.GeneralInfoPanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.KeysPanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.METabPanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataPanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.ResourceSpecificPanel;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingNameModelData;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingTypeModelData;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.DatasetTypeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DatasetTypeVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.OptionVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.DataTypeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.FeatureCodeSetModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.rainfall.common.services.RainfallServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class MEController {

	public static SelectionListener<ButtonEvent> getXml(final METabPanel tabPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
//				if (tabPanel.getKeysPanel().validate() || tabPanel.getDatasetTypePanel().validate() && tabPanel.getGeneralInfoPanel().validate() && tabPanel.getColumnDefinitionPanel().validate()) {
				try {
					if (tabPanel.getMetadataPanel().validate() || tabPanel.getDatasetTypePanel().validate() && tabPanel.getMetadataPanel().validate() && tabPanel.getColumnDefinitionPanel().validate()) {
//					ResourceVO vo = createResourceVO(tabPanel.getGeneralInfoPanel(), tabPanel.getKeysPanel());
						ResourceVO vo = createResourceVO(tabPanel.getMetadataPanel(), tabPanel.getExtraInfoPanel());
						DatasetTypeVO dtvo = createDatasetTypeVo(tabPanel);
						MetadataServiceEntry.getInstance().createMetadataFile(vo, dtvo, new AsyncCallback<String>() {
							public void onSuccess(String result) {
								Window.open("../exportObject/" + result, "_blank", "status=no");
							}

							public void onFailure(Throwable caught) {
								FenixAlert.error(BabelFish.print().info(), caught.getMessage());
							}
						});
					} else {
						FenixAlert.alert(BabelFish.print().info(), BabelFish.print().fillMandatoryFields());
					}
				} catch (FenixGWTException e) {
					FenixAlert.info(BabelFish.print().info(), e.getMessage());
				}
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static DatasetTypeVO createDatasetTypeVo(METabPanel tabPanel) {

		DatasetTypeVO vo = new DatasetTypeVO();
		vo.setTitle(getDatasetTypeName(tabPanel.getDatasetTypePanel()));

		Map<String, VerticalPanel> columnDefinitionMap = tabPanel.getColumnDefinitionPanel().getColumnDefinitionMap();
		for (VerticalPanel columnDefinitionPanel : columnDefinitionMap.values()) {
			HorizontalPanel descriptorPanel = (HorizontalPanel) columnDefinitionPanel.getData("descriptorPanel");
			TextField<String> header = (TextField<String>) descriptorPanel.getData("header");
			ComboBox<DataTypeModelData> typeList = (ComboBox<DataTypeModelData>) descriptorPanel.getData("dataType");
			CheckBox isKey = (CheckBox) descriptorPanel.getData("isKey");
			DescriptorVO des = new DescriptorVO();
			des.setHeader(header.getValue());
			des.setKey(isKey.isChecked());
			String contentDescriptor = "";
			for (int i = 0; i < typeList.getSelection().size(); i++)
				contentDescriptor = typeList.getSelection().get(i).getDataType();
			des.setContentDescriptor(contentDescriptor);
			des.setOptions(new ArrayList<OptionVO>());
			List<HorizontalPanel> optionPanels = (List<HorizontalPanel>) columnDefinitionPanel.getData("optionPanels");
			for (HorizontalPanel optionPanel : optionPanels) {
				TextField<String> nameField = (TextField<String>) optionPanel.getData("optionName");
				TextField<String> valueField = (TextField<String>) optionPanel.getData("optionValue");
//				ComboBox<GaulModelData> gaulComboBox = (ComboBox<GaulModelData>) optionPanel.getData("optionRegion");
				
				ComboBox<CodingTypeModelData> codingTypeList = (ComboBox<CodingTypeModelData>) optionPanel.getData("optionName");
				ComboBox<CodingNameModelData> codingNameList = (ComboBox<CodingNameModelData>) optionPanel.getData("optionValue");
				
				OptionVO ovo = new OptionVO();
				
				for (int i = 0; i < codingTypeList.getSelection().size(); i++)
					ovo.setOptionName(codingTypeList.getSelection().get(i).getCodingType());
				
				for (int i = 0; i < codingNameList.getSelection().size(); i++)
					ovo.setOptionValue(codingNameList.getSelection().get(i).getCodingName());
				
//				for (int i = 0; i < gaulComboBox.getSelection().size(); i++)
//					ovo.setOptionRegion(gaulComboBox.getSelection().get(i).getGaulCode());
				
				des.addOption(ovo);
			}
			vo.addDescriptorVO(des);
		}

		return vo;
	}

	public static String getDatasetTypeName(DatasetTypePanel panel) {
		String datasetTypeName = "";
		if (panel.getRadioScratch().isChecked()) {
			return panel.getScratchTextbox().getValue();
		} else if (panel.getRadioDB().isChecked()) {
			ComboBox<DatasetTypeModelData> dbListbox = panel.getDbListbox();
			for (int i = 0; i < dbListbox.getSelection().size(); i++)
				return dbListbox.getSelection().get(i).getDatasetType();
		}
		return datasetTypeName;
	}
	
	public static ResourceVO createResourceVO(GeneralInfoPanel panel, KeysPanel keysPanel, ResourceSpecificPanel rsrcPanel) {
		
		ResourceVO vo = createResourceVO(panel, keysPanel);
		
		Map<String, String> resourceSpecificMap = new HashMap<String, String>();
		for (String field: rsrcPanel.getFieldMap().keySet())
			resourceSpecificMap.put(field, rsrcPanel.getFieldMap().get(field).getValue());
		vo.setResourceSpecificMap(resourceSpecificMap);
		
		return vo;
	}

	@SuppressWarnings("unchecked")
	public static ResourceVO createResourceVO(GeneralInfoPanel panel, KeysPanel keysPanel) {

		ResourceVO vo = new ResourceVO();

		VerticalPanel abstractPanel = panel.getFieldPanelMap().get(BabelFish.print().abstractAbstract());
		TextArea abstractArea = (TextArea) abstractPanel.getData(BabelFish.print().abstractAbstract());
		vo.setAbstractAbstract(abstractArea.getValue());

		VerticalPanel titlePanel = panel.getFieldPanelMap().get(BabelFish.print().title());
		TextField<String> textField = (TextField<String>) titlePanel.getData(BabelFish.print().title());
		vo.setTitle(textField.getValue());

		VerticalPanel keywordsPanel = panel.getFieldPanelMap().get(BabelFish.print().keywords());
		TextField<String> keywordsField = (TextField<String>) keywordsPanel.getData(BabelFish.print().keywords());
		vo.setKeywords(keywordsField.getValue());

		VerticalPanel codePanel = panel.getFieldPanelMap().get(BabelFish.print().metadataCode());
		TextField<String> codeField = (TextField<String>) codePanel.getData(BabelFish.print().metadataCode());
		vo.setCode(codeField.getValue());

		VerticalPanel startDatePanel = panel.getFieldPanelMap().get(BabelFish.print().startDate());
		DateField startDateField = (DateField) startDatePanel.getData(BabelFish.print().startDate());
		vo.setStartDate(startDateField.getValue());

		VerticalPanel endDatePanel = panel.getFieldPanelMap().get(BabelFish.print().endDate());
		DateField endDateField = (DateField) endDatePanel.getData(BabelFish.print().endDate());
		vo.setEndDate(endDateField.getValue());

		for (int i = 0; i < panel.getGaulList().getSelection().size(); i++)
			vo.setRegion(panel.getGaulList().getSelection().get(i).getGaulCode());

		for (int i = 0; i < panel.getSharingList().getSelection().size(); i++)
			vo.setSharingCode(panel.getSharingList().getSelection().get(i).getSharingCode());

		for (int i = 0; i < panel.getCategoryList().getSelection().size(); i++)
			vo.setCategories(panel.getCategoryList().getSelection().get(i).getCategoryValue());

//		for (int i = 0; i < keysPanel.getGaulList().getSelection().size(); i++)
//			vo.setSourceRegion(keysPanel.getGaulList().getSelection().get(i).getGaulCode());

		if (keysPanel.getPeriodList() != null)
			for (int i = 0; i < keysPanel.getPeriodList().getSelection().size(); i++)
				vo.setPeriodTypeCode(keysPanel.getPeriodList().getSelection().get(i).getPeriodTypeCode());

		
		vo.setSourceRegion(keysPanel.getGaulList().getValue().getGaulCode());
		
		if (keysPanel.getProviderGaulList().getValue() != null)
			vo.setProviderRegion(keysPanel.getProviderGaulList().getValue().getGaulCode());
		

		vo.setSource(keysPanel.getSource().getValue());
		
		if (keysPanel.getSourceContact().getValue() != null)
			vo.setSourceContact(keysPanel.getSourceContact().getValue());
		if (keysPanel.getProvider().getValue() != null)
			vo.setProvider(keysPanel.getProvider().getValue());
		if (keysPanel.getProviderContact().getValue() != null)
			vo.setProviderContact(keysPanel.getProviderContact().getValue());
		
		vo.setDateLastUpdate(new Date());

		return vo;
	}
	
	@SuppressWarnings("unchecked")
	public static ResourceVO createResourceVO(MetadataPanel metadataPanel, ExtraInfoPanel extraInfoPanel) {

		ResourceVO vo = new ResourceVO();

		TextArea abstractArea = metadataPanel.getAbstractAbstract();
		vo.setAbstractAbstract(abstractArea.getValue());

		TextField<String> textField = metadataPanel.getTITLE();
		vo.setTitle(textField.getValue());

		TextField<String> keywordsField = metadataPanel.getKeywords();
		vo.setKeywords(keywordsField.getValue());

		TextField<String> codeField = metadataPanel.getCode();
		vo.setCode(codeField.getValue());

		for (int i = 0; i < metadataPanel.getGaulList().getSelection().size(); i++)
			vo.setRegion(metadataPanel.getGaulList().getSelection().get(i).getGaulCode());

		for (int i = 0; i < metadataPanel.getSharingList().getSelection().size(); i++)
			vo.setSharingCode(metadataPanel.getSharingList().getSelection().get(i).getSharingCode());

		for (int i = 0; i < metadataPanel.getCategoryList().getSelection().size(); i++)
			vo.setCategories(metadataPanel.getCategoryList().getSelection().get(i).getCategoryValue());

		for (int i = 0; i < metadataPanel.getSourceGaulList().getSelection().size(); i++)
			vo.setSourceRegion(metadataPanel.getSourceGaulList().getSelection().get(i).getGaulCode());

		if (metadataPanel.getPeriodList() != null)
			for (int i = 0; i < metadataPanel.getPeriodList().getSelection().size(); i++)
				vo.setPeriodTypeCode(metadataPanel.getPeriodList().getSelection().get(i).getPeriodTypeCode());

		vo.setSource(metadataPanel.getSourceName().getValue());
		if (metadataPanel.getSourceContact().getValue() != null)
			vo.setSourceContact(metadataPanel.getSourceContact().getValue());
		for (int i = 0; i < metadataPanel.getSourceGaulList().getSelection().size(); i++)
			vo.setSourceRegion(metadataPanel.getSourceGaulList().getSelection().get(i).getGaulCode());
		
		vo.setDateLastUpdate(new Date());
		vo.setStartDate(extraInfoPanel.getStartDate().getValue());
		vo.setEndDate(extraInfoPanel.getEndDate().getValue());
		
		if (extraInfoPanel.getProviderName().getValue() != null)
			vo.setProvider(extraInfoPanel.getProviderName().getValue());
		if (extraInfoPanel.getProviderContact().getValue() != null)
			vo.setSourceContact(extraInfoPanel.getProviderContact().getValue());
		for (int i = 0; i < extraInfoPanel.getProviderGaulList().getSelection().size(); i++)
			vo.setSourceRegion(extraInfoPanel.getProviderGaulList().getSelection().get(i).getGaulCode());

		return vo;
	}

	public static void fillCategoryPanel(ListStore<CategoryModelData> categoryStore) {
		CategoryList categories = new CategoryList();
		ListStore<CategoryModelData> tmp = new ListStore<CategoryModelData>();
		categories.getCategoryStore(categoryStore);
//		for (int i = 0; i < tmp.getCount(); i++)
//			categoryStore.add(tmp.getAt(i));
	}
	
	public static void fillFeatureCodeSetPanel(final ListStore<FeatureCodeSetModelData> featureCodeSetStore) {
		MetadataServiceEntry.getInstance().findAllGeographicCodingSystems(new AsyncCallback<List<FeatureCodeSetModelData>>() {
			public void onSuccess(List<FeatureCodeSetModelData> models) {
				for (FeatureCodeSetModelData model : models)
					featureCodeSetStore.add(model);
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	/**
	 * This method read the given Template Dataset's file, generates the
	 * Descriptor(s) and show them to the user.
	 * 
	 * @param tabPanel
	 * @return
	 */
	public static SelectionChangedListener<DatasetTypeModelData> datasetTypeComboBoxListener(final METabPanel tabPanel, final boolean isEditable) {
		return new SelectionChangedListener<DatasetTypeModelData>() {
			public void selectionChanged(SelectionChangedEvent<DatasetTypeModelData> se) {
				ComboBox<DatasetTypeModelData> dbListbox = tabPanel.getDatasetTypePanel().getDbListbox();
				for (int i = 0; i < dbListbox.getSelection().size(); i++) {
					String templateDatasetName = dbListbox.getSelection().get(i).getDatasetType();
					showDatasetDescriptors(templateDatasetName, tabPanel, isEditable);
//					tabPanel.getTabPanel().setSelection(tabPanel.getKeys());
				}
			}
		};
	}

	public static void showDatasetDescriptors(final String templateDatasetName, final METabPanel tabPanel, final boolean isEditable) {
		try {
			MetadataServiceEntry.getInstance().getTemplateDatasetDescriptors(templateDatasetName, new AsyncCallback<List<DescriptorVO>>() {
				public void onSuccess(java.util.List<DescriptorVO> vos) {
					// remove existing widgets
					for (int i = tabPanel.getColumnDefinitionPanel().getWrapper().getItemCount() - 1; i >= 1; i--)
						tabPanel.getColumnDefinitionPanel().getWrapper().remove(tabPanel.getColumnDefinitionPanel().getWrapper().getWidget(i));
					// flush the HashMap
					tabPanel.getColumnDefinitionPanel().getColumnDefinitionMap().clear();
					// add column definitions
					ColumnDefinitionPanel panel = tabPanel.getColumnDefinitionPanel();
					for (DescriptorVO vo : vos) 
						addColumnDefinitionPanel(panel, vo.getHeader(), vo.getContentDescriptor(), vo.isKey(), vo.getOptions(), isEditable);
				};

				public void onFailure(Throwable caught) {
					FenixAlert.error(BabelFish.print().info(), caught.getMessage());
				};
			});
		} catch (FenixGWTException e) {
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static void fillDataTypeComboBox(final HorizontalPanel descriptorPanel, final String dataTypeValue) {
		MetadataServiceEntry.getInstance().findAllDataType(new AsyncCallback<List<DataTypeModelData>>() {
			public void onSuccess(List<DataTypeModelData> result) {
				ComboBox<DataTypeModelData> dataTypeComboBox = (ComboBox<DataTypeModelData>) descriptorPanel.getData("dataTypeComboBox");
				ListStore<DataTypeModelData> dataTypeStore = dataTypeComboBox.getStore();
				for (DataTypeModelData m : result)
					dataTypeStore.add(m);
				dataTypeComboBox.setValue(new DataTypeModelData(dataTypeValue));
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	/**
	 * Add an OptionPanel inside the given ColumnDefinitionPanel.
	 * 
	 * @param columnDefinitionPanel
	 * @param columnKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Listener<BaseEvent> addOption(final ColumnDefinitionPanel columnDefinitionPanel, final String columnKey, final String optionName, final String optionValue, final boolean isEditable) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				VerticalPanel columnPanel = columnDefinitionPanel.getColumnDefinitionMap().get(columnKey);
				HorizontalPanel optionPanel = columnDefinitionPanel.buildOptionPanel(optionName, optionValue, isEditable);
				columnPanel.add(optionPanel);
				columnPanel.getLayout().layout();
				columnDefinitionPanel.getOptionMap().put(columnDefinitionPanel.getOptionKey(), optionPanel);
				columnDefinitionPanel.getOptionColumnMap().put(columnDefinitionPanel.getOptionKey(), columnPanel);
				String optionKey = String.valueOf(1 + Integer.parseInt(columnDefinitionPanel.getOptionKey()));
				columnDefinitionPanel.setOptionKey(optionKey);

				List<HorizontalPanel> optionPanels = (List<HorizontalPanel>) columnPanel.getData("optionPanels");
				if (optionPanels == null) {
					optionPanels = new ArrayList<HorizontalPanel>();
					optionPanels.add(optionPanel);
					columnPanel.setData("optionPanels", optionPanels);
				} else {
					optionPanels.add(optionPanel);
					columnPanel.setData("optionPanels", optionPanels);
				}
			}
		};
	}

	/**
	 * Remove an OptionPanel from the given ColumnDefinitionPanel.
	 * 
	 * @param columnDefinitionPanel
	 * @param optionKey
	 * @return
	 */
	public static Listener<BaseEvent> removeOption(final ColumnDefinitionPanel columnDefinitionPanel, final String optionKey) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				VerticalPanel columnPanel = columnDefinitionPanel.getOptionColumnMap().get(optionKey);
				HorizontalPanel optionPanel = columnDefinitionPanel.getOptionMap().get(optionKey);
				columnPanel.remove(optionPanel);
				columnPanel.getLayout().layout();
			}
		};
	}

	public static SelectionListener<ButtonEvent> addColumn(final ColumnDefinitionPanel columnDefinitionPanel, final String headerValue, final String dataTypeValue, final boolean isKey, final List<OptionVO> options, final boolean isEditable) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				addColumnDefinitionPanel(columnDefinitionPanel, headerValue, dataTypeValue, isKey, options, isEditable);
				columnDefinitionPanel.getWrapper().getLayout().layout();
			}
		};
	}

	/**
	 * Add a ColumnDefinitionPanel to the MetadetaEditorWindow.
	 * 
	 * @param columnDefinitionPanel
	 * @return
	 */
	private static void addColumnDefinitionPanel(ColumnDefinitionPanel columnDefinitionPanel, String headerValue, String dataTypeValue, boolean isKey, List<OptionVO> options, boolean isEditable) {
		VerticalPanel columnPanel = columnDefinitionPanel.buildColumnDefinitionPanel(headerValue, dataTypeValue, isKey, options, isEditable);
		VerticalPanel wrapper = columnDefinitionPanel.getWrapper();
		wrapper.add(columnPanel);
		wrapper.getLayout().layout();
		columnDefinitionPanel.getColumnDefinitionMap().put(columnDefinitionPanel.getColumnKey(), columnPanel);
		columnDefinitionPanel.setColumnKey(String.valueOf(1 + Integer.parseInt(columnDefinitionPanel.getColumnKey())));
	}

	/**
	 * Remove a ColumnDefinitionPanel to the MetadetaEditorWindow.
	 * 
	 * @param columnDefinitionPanel
	 * @param columnKey
	 * @return
	 */
	public static SelectionListener<ButtonEvent> removeColumn(final ColumnDefinitionPanel columnDefinitionPanel, final String columnKey) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				VerticalPanel columnPanel = columnDefinitionPanel.getColumnDefinitionMap().get(columnKey);
				columnDefinitionPanel.getWrapper().remove(columnPanel);
				columnDefinitionPanel.getWrapper().getLayout().layout();
				columnDefinitionPanel.getColumnDefinitionMap().remove(columnKey);
			}
		};
	}

	/**
	 * Fill the DatasetType ComboBox from DatasetTemplate enum and DB.
	 * 
	 * @param panel
	 */
	public static void fillDatasetTypeStore(final DatasetTypePanel panel) {
		MetadataServiceEntry.getInstance().findAllDatasetType(new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> result) {
				ListStore<DatasetTypeModelData> list = panel.getDatasetTypeStore();
				for (String s : result) {
					DatasetTypeModelData d = new DatasetTypeModelData();
					d.setDatasetType(s);
					list.add(d);
				}
				panel.getDbListbox().setStore(list);
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	/**
	 * Fill the GAUL ComboBox from DB.
	 * 
	 * @param panel
	 */
	public static void fillGaulStore(final METabPanel panel) {
		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				ListStore<GaulModelData> list = panel.getMetadataPanel().getGaulList().getStore();
				for (CodeVo vo : vos) {
					GaulModelData g = new GaulModelData();
					g.setGaulCode(vo.getCode());
					g.setGaulLabel(vo.getLabel());
					list.add(g);
				}
				panel.getMetadataPanel().getGaulList().setStore(list);
				list = panel.getMetadataPanel().getGaulList().getStore();
				for (CodeVo vo : vos) {
					GaulModelData g = new GaulModelData();
					g.setGaulCode(vo.getCode());
					g.setGaulLabel(vo.getLabel());
					list.add(g);
				}
				panel.getMetadataPanel().getGaulList().setStore(list);
				/*
				list = panel.getKeysPanel().getProviderGaulStore();
				for (CodeVo vo : vos) {
					GaulModelData g = new GaulModelData();
					g.setGaulCode(vo.getCode());
					g.setGaulLabel(vo.getLabel());
					list.add(g);
				}
				panel.getKeysPanel().getProviderGaulList().setStore(list);
				*/
			};

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static void fillGaulStore(final ListStore<GaulModelData> gaulListStore) {
		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				for (CodeVo vo : vos) {
					GaulModelData g = new GaulModelData();
					g.setGaulCode(vo.getCode());
					g.setGaulLabel(vo.getLabel());
					gaulListStore.add(g);
				}
			};

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void fillGaulStore(final ComboBox<GaulModelData> gaulList, final ListStore<GaulModelData> gaulListStore, final String gaul0code) {
		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				for (CodeVo vo : vos) {
					if (vo.getCode().equals(gaul0code)) {
						GaulModelData g = new GaulModelData();
						g.setGaulCode(vo.getCode());
						g.setGaulLabel(vo.getLabel());
						gaulListStore.add(g);
						gaulList.setValue(g);
						break;
					}
				}
			};

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void fillCodingTypeStore(final ListStore<CodingTypeModelData> codingTypeListStore) {
		MetadataServiceEntry.getInstance().findAllCodingSystemTypes(new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> result) {
				for (String s : result) {
					CodingTypeModelData ctmd = new CodingTypeModelData(s);
					codingTypeListStore.add(ctmd);
				}
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void fillCodingNameStore(final ListStore<CodingNameModelData> codingNameListStore) {
		MetadataServiceEntry.getInstance().findAllCodingSystemNames(new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> result) {
				for (String s : result) {
					CodingNameModelData ctmd = new CodingNameModelData(s);
					codingNameListStore.add(ctmd);
				}
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	/**
	 * Enable/disable RadioButtons in the MetadetaEditorWindow.
	 * 
	 * @param panel
	 * @return
	 */
	public static ClickListener radioScratchListener(final DatasetTypePanel panel) {
		return new ClickListener() {
			public void onClick(Widget widget) {
				panel.getDbListbox().setVisible(false);
				panel.getScratchTextbox().setVisible(true);
				((HTML) panel.getScratchPanel().getData("label")).setVisible(true);
				((HTML) panel.getDbPanel().getData("label")).setVisible(false);
			}
		};
	}

	/**
	 * Enable/disable RadioButtons in the MetadetaEditorWindow.
	 * 
	 * @param panel
	 * @return
	 */
	public static ClickListener radioDbListener(final DatasetTypePanel panel) {
		return new ClickListener() {
			public void onClick(Widget widget) {
				panel.getDbListbox().setVisible(true);
				panel.getScratchTextbox().setVisible(false);
				((HTML) panel.getScratchPanel().getData("label")).setVisible(false);
				((HTML) panel.getDbPanel().getData("label")).setVisible(true);
			}
		};
	}

	/**
	 * Enable/disable RadioButtons in the MetadetaEditorWindow.
	 * 
	 * @param panel
	 * @return
	 */
	public static ClickListener radioFileListener(final DatasetTypePanel panel) {
		return new ClickListener() {
			public void onClick(Widget widget) {
				panel.getDbListbox().setVisible(false);
				panel.getScratchTextbox().setVisible(false);
				((HTML) panel.getScratchPanel().getData("label")).setVisible(false);
				((HTML) panel.getDbPanel().getData("label")).setVisible(false);
			}
		};
	}

	public static Listener<BaseEvent> enableAddColumn(final Button addColumnButton, final Boolean isEnabled) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				addColumnButton.setEnabled(isEnabled);
			}
		};
	}

}
