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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.qb.client.util.Operator;
import org.fao.fenix.web.modules.qb.client.view.ItemSelectorDialog;
import org.fao.fenix.web.modules.qb.client.view.PanelOneDatasetSelection;
import org.fao.fenix.web.modules.qb.client.view.PanelThreeFieldSelection;
import org.fao.fenix.web.modules.qb.common.model.DimensionModel;
import org.fao.fenix.web.modules.qb.common.services.QueryBuilderServiceEntry;
import org.fao.fenix.web.modules.qb.common.vo.DatasetVO;
import org.fao.fenix.web.modules.qb.common.vo.DimensionVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class PanelThreeController {

	private boolean onLoad = true;

	public static SelectionListener<ButtonEvent> addCondition(final PanelThreeFieldSelection panelThreeFieldSelection){
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				panelThreeFieldSelection.buildConditionPanel(false);

			}
		};
	}

	public static SelectionListener<ButtonEvent> removeCondition(final PanelThreeFieldSelection panelThreeFieldSelection, final HorizontalPanel conditionPanel){
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				panelThreeFieldSelection.getForm().remove(conditionPanel);
				panelThreeFieldSelection.counter.decrement(conditionPanel);
				System.out.println("removeCondition CALLED !!!!!!");
				if(panelThreeFieldSelection.getFirstCondition()!=null){
					System.out.println("get first condition is not null !!!!!!");
					HorizontalPanel panel = panelThreeFieldSelection.getFirstCondition();
					LayoutContainer cont = (LayoutContainer)panel.getItemByItemId("addOrContainer");

					if(cont!=null){
						panel.remove(cont);
						panel.layout();
					}

				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> test(final PanelThreeFieldSelection panelThreeFieldSelection){
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("panelThreeFieldSelection.numberOfConditions() = "+panelThreeFieldSelection.numberOfConditions());
			}
		};
	}

	public static SelectionListener<ButtonEvent> openItemSelectorDialog(final PanelThreeFieldSelection panelThreeFieldSelection, final DimensionModel selectedDimension, final Text displaySelectedItemsHtml) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				List<Long> datasetIds = new ArrayList<Long>();

				String datasetId = selectedDimension.getCode();

				if(datasetId.contains("_")) {
					String[] idArray = datasetId.split("_");
					for (int i= 0; i< idArray.length; i++) {
						String id = idArray[i];
						datasetIds.add(Long.valueOf(id));

					}
				} else {
					datasetIds.add(Long.valueOf(datasetId));
				}

				new ItemSelectorDialog(selectedDimension, datasetIds, displaySelectedItemsHtml);

			}
		};
	}

	/*public static SelectionListener<ButtonEvent> openItemSelectorDialog(final PanelTwoFieldSelection panelThreeFieldSelection, final DimensionModel selectedDimension, final Html displaySelectedItemsHtml) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				List<Long> datasetIds = new ArrayList<Long>();

				String datasetId = selectedDimension.getCode();

				if(datasetId.contains("_")) {
					String[] idArray = datasetId.split("_");
					for (int i= 0; i< idArray.length; i++) {
						String id = idArray[i];
						datasetIds.add(Long.valueOf(id));

					}
				} else {
					datasetIds.add(Long.valueOf(datasetId));
				}

				new ItemSelectorDialog(selectedDimension, datasetIds, displaySelectedItemsHtml);

			}
		};
	}*/

	public static void setDimensionListAndDatasetInfoMap(final PanelThreeFieldSelection panelThreeFieldSelection, final PanelOneDatasetSelection panelOneDatasetSelection){

		System.out.println("setDimensionListAndDatasetInfoMap CALLED !!!!!!");
		final List<ResourceChildModel> modelList = panelOneDatasetSelection.getGrid().getSelectionModel().getSelectedItems();

		final List<DimensionModel> dimensionList = new ArrayList<DimensionModel>();

		final List<Long> datasetIds = new ArrayList<Long>();

		final String alphabet = BabelFish.print().alphabet();

		for(int i = 0;  i< modelList.size(); i++){
			ResourceChildModel model = modelList.get(i);
			datasetIds.add(Long.parseLong(model.getId()));
		}


		/** no dataset selected **/
		if ( modelList.size() == 0)
			FenixAlert.info("Select a Dataset", "Please Select a Dataset");


		QueryBuilderServiceEntry.getInstance().getCombinedDimensionList(datasetIds, new AsyncCallback<Map<String, DimensionVO>>()   {
			public void onSuccess(final Map<String, DimensionVO> dimensions) {

				//Give user the option to select

				//if ( !OfcChartWizardController.checkEqualSizes(dimensions) && modelList.size() > 1)
				//	FenixAlert.info("Dataset", "The datasets contains more columns using the same coding system");
				//else {


				QueryBuilderServiceEntry.getInstance().getDatasetsInfoMap(datasetIds, new AsyncCallback<Map<Long, DatasetVO>>()   {

					public void onSuccess(Map<Long, DatasetVO> map) {

						System.out.println("dimensionDatasetMap size = "+map.size());
						for (String cs_identifier : dimensions.keySet()) {
							DimensionVO dimension = dimensions.get(cs_identifier);
                            StringBuilder datasetAlias = new StringBuilder();
                            datasetAlias.append("(");

                    			if(dimension.getDs_id().contains("_")) {
                    				String[] idArray = dimension.getDs_id().split("_");
                    				  for (int i= 0; i< idArray.length; i++) {
   										String datasetID = idArray[i];

   										if(i == idArray.length-1)
   										 datasetAlias.append(alphabet.charAt(map.get(Long.valueOf(datasetID)).getAliasIndex()));
   										else
   										 datasetAlias.append(alphabet.charAt(map.get(Long.valueOf(datasetID)).getAliasIndex())+"/");

   	   						          }
   								} else {
   									datasetAlias.append(Character.toString(alphabet.charAt(map.get(Long.valueOf(dimension.getDs_id())).getAliasIndex())));
   								}

                    	     datasetAlias.append(")");


							DimensionModel model = new DimensionModel(dimension.getHeader() + " " + datasetAlias, dimension.getDs_id(), dimension.getColumnDataType());
							model.setPeriodType(dimension.getPeriodType());

							System.out.println("dimension name  = "+model.getName());

							if(dimension.hasCodingSystem()) {
								model.setCodingSystemTitle(dimension.getCodingSystemVO().getTitle());
								model.setCodingSystemRegion(dimension.getCodingSystemVO().getRegion());
							}
							dimensionList.add(model);

						}


						for (Long dataId : map.keySet()) {
								DatasetVO dataVO = map.get(dataId);
								Map<String, DimensionBeanVO> dimensions = dataVO.getDimensionDescriptorMap();

								for (String dimensionID : dimensions.keySet()) {
									DimensionBeanVO dimensionBeanVO = dimensions.get(dimensionID);
							}

						}
System.out.println("dimensionList size = "+dimensionList.size());
						panelThreeFieldSelection.setDimensionList(dimensionList);
						panelThreeFieldSelection.setDatasetInfoMap(map);
						panelThreeFieldSelection.layout();
					}
					public void onFailure(Throwable caught) {


					}});


			}
			public void onFailure(Throwable caught) {


			}});
	}



	public static Listener<BaseEvent> setOperatorType(final PanelThreeFieldSelection panelThreeFieldSelection, final ComboBox<DimensionModel> dimensions, final ListStore<Operator> operatorsStore,  final ComboBox<Operator> operatorsCombo, final HorizontalPanel conditionPanel) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {


				final DimensionModel selecteddimension = dimensions.getValue();
				final String selectedDataType = selecteddimension.getDataType();

				operatorsStore.removeAll();

				panelThreeFieldSelection.populateOperatorsListStore(selectedDataType, operatorsStore);

				operatorsCombo.setValue(operatorsStore.getAt(1));

				panelThreeFieldSelection.getForm().layout();
				panelThreeFieldSelection.layout();

			}
		};
	}

	public static Listener<BaseEvent> setValueField(final PanelThreeFieldSelection panelThreeFieldSelection, final ComboBox<Operator> operators, final ComboBox<DimensionModel> dimensions, final LayoutContainer valueFieldContainer, final HorizontalPanel conditionPanel) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {

				final DimensionModel selectedDimension = dimensions.getValue();
				final Operator selectedOperator = operators.getValue();
				final String selectedCode = selectedOperator.getCode();
				final String selectedDimensionDataType = selectedDimension.getDataType();
				final String selectedDimensionPeriodType = selectedDimension.getPeriodType();

				GWT.log("setValueField: selectedCode = "+selectedCode +" for selectedDimensionDataType: " +selectedDimensionDataType, null);

				//List<Component> items = conditionPanel.getItems();




					if((String)conditionPanel.getData("onLoad") == "true") {
						GWT.log("setValueField: onLoad=true ", null);
						conditionPanel.setData("onLoad", "false");
					} 	else {
						GWT.log("setValueField: onLoad=false ", null);
						valueFieldContainer.removeAll();


						if(selectedCode.contains("List")) {

							HorizontalPanel panel = new HorizontalPanel();
							 Text displaySelectedItemsHtml = new Text();

							 Button chooseButton = panelThreeFieldSelection.createChooseButton(selectedDimension, displaySelectedItemsHtml);
							// chooseButton.setId("valueField");

							// valueFieldContainer.add(displaySelectedItemsHtml, new FormData("100%"));
							 //valueFieldContainer.add(chooseButton, new FormData("100%"));


							 panel.add(displaySelectedItemsHtml);
							 panel.add(chooseButton);

							 valueFieldContainer.add(panel);

						}
						else {

						List<Field> valueFields = panelThreeFieldSelection.createValueField(selectedDimensionDataType, selectedCode, selectedDimensionPeriodType);

						HorizontalPanel panel = new HorizontalPanel();


						for(int i = 0; i < valueFields.size(); i++) {
							 	//LayoutContainer cont = new LayoutContainer();
							 	//cont.setId("valueField");
							 	//FormLayout layout = new FormLayout();
							 	//layout.setHideLabels(true);
							 	//cont.setLayout(layout);

							     Field valueField = valueFields.get(i);
							  // valueField.setId("valueField");

							   if(i%2!=0){
								   Text and= new Text(BabelFish.print().and());
								   and.setId("valueField");
								   panel.add(and);
							   }
							  // valueFieldContainer.add(valueField, new FormData("100%"));
							    panel.add(valueField);



							   //conditionPanel.add(cont);
						}
						valueFieldContainer.add(panel);
						}
					}

				valueFieldContainer.layout();
				conditionPanel.layout();
				panelThreeFieldSelection.getForm().layout();
				panelThreeFieldSelection.layout();

			}
		};
	}

	public static void getValueField(final PanelThreeFieldSelection panelThreeFieldSelection, final ComboBox<Operator> operators, final ComboBox<DimensionModel> dimensions, final LayoutContainer valueFieldContainer, final HorizontalPanel conditionPanel) {
				final DimensionModel selectedDimension = dimensions.getValue();
				final Operator selectedOperator = operators.getValue();
				final String selectedCode = selectedOperator.getCode();
				final String selectedDimensionDataType = selectedDimension.getDataType();
				final String selectedDimensionPeriodType = selectedDimension.getPeriodType();

				GWT.log("getValueField: selectedCode = "+selectedCode +" for selectedDimensionDataType: " +selectedDimensionDataType, null);

				//List<Component> items = conditionPanel.getItems();


					//if((String)conditionPanel.getData("onLoad") == "true") {
					//	GWT.log("getValueField: onLoad=true ", null);
					//	conditionPanel.setData("onLoad", "false");
				//	} 	else {
						GWT.log("getValueField: onLoad is true ", null);
						conditionPanel.setData("onLoad", "false");
						valueFieldContainer.removeAll();


						if(selectedCode.contains("List")) {

							HorizontalPanel panel = new HorizontalPanel();
							 Text displaySelectedItemsHtml = new Text();

							 Button chooseButton = panelThreeFieldSelection.createChooseButton(selectedDimension, displaySelectedItemsHtml);
							// chooseButton.setId("valueField");

							// valueFieldContainer.add(displaySelectedItemsHtml, new FormData("100%"));
							 //valueFieldContainer.add(chooseButton, new FormData("100%"));


							 panel.add(displaySelectedItemsHtml);
							 panel.add(chooseButton);

							 valueFieldContainer.add(panel);

						}
						else {

						List<Field> valueFields = panelThreeFieldSelection.createValueField(selectedDimensionDataType, selectedCode, selectedDimensionPeriodType);

						HorizontalPanel panel = new HorizontalPanel();


						for(int i = 0; i < valueFields.size(); i++) {
							 	//LayoutContainer cont = new LayoutContainer();
							 	//cont.setId("valueField");
							 	//FormLayout layout = new FormLayout();
							 	//layout.setHideLabels(true);
							 	//cont.setLayout(layout);

							     Field valueField = valueFields.get(i);
							  // valueField.setId("valueField");

							   if(i%2!=0){
								   Text and= new Text(BabelFish.print().and());
								   and.setId("valueField");
								   panel.add(and);
							   }
							  // valueFieldContainer.add(valueField, new FormData("100%"));
							    panel.add(valueField);



							   //conditionPanel.add(cont);
						}
						valueFieldContainer.add(panel);
					}
				//	}

				valueFieldContainer.layout();
				conditionPanel.layout();
				panelThreeFieldSelection.getForm().layout();
				panelThreeFieldSelection.layout();

			}



	/**public static Listener<BaseEvent> setValueField1(final PanelTwoFieldSelection panelThreeFieldSelection, final ComboBox<Operator> operators, final ComboBox<DimensionModel> dimensions, final HorizontalPanel conditionPanel) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {

				final DimensionModel selectedDimension = dimensions.getValue();
				final Operator selectedOperator = operators.getValue();
				final String selectedCode = selectedOperator.getCode();
				final String selectedDimensionDataType = selectedDimension.getDataType();
				final String selectedDimensionPeriodType = selectedDimension.getPeriodType();

				GWT.log("setValueField: selectedCode = "+selectedCode +" for selectedDimensionDataType: " +selectedDimensionDataType, null);

				List<Component> items = conditionPanel.getItems();
				List<Component> valueComponents = new ArrayList<Component>();
				for(Component item: items){
					if(item.getId().contains("valueField")){
						valueComponents.add(item);
					}

				}

				if(valueComponents.size() > 0){
					List<Field> valueFields = panelThreeFieldSelection.createValueField(selectedDimensionDataType, selectedCode, selectedDimensionPeriodType);

					if((String)conditionPanel.getData("onLoad") == "true") {
						conditionPanel.setData("onLoad", "false");
					} 	else {

						for(Component valueComponent: valueComponents) {
							conditionPanel.remove(valueComponent);
						}

						if(selectedCode.contains("List")) {

							 Html displaySelectedItemsHtml = new Html();
							 displaySelectedItemsHtml.setId("valueField");

							 Button chooseButton = panelThreeFieldSelection.createChooseButton(selectedDimension, displaySelectedItemsHtml);
							 chooseButton.setId("valueField");

							 conditionPanel.add(displaySelectedItemsHtml);
						     conditionPanel.add(chooseButton);


						}
						else {
						for(int i = 0; i < valueFields.size(); i++) {
							   Field valueField = valueFields.get(i);
							   valueField.setId("valueField");

							   if(i%2!=0){
								   Html and= new Html(I18N.print().and());
								   and.setId("valueField");
								   conditionPanel.add(and);
							   }
							   conditionPanel.add(valueField);
						}
						}
					}
				}

				conditionPanel.layout();
				panelThreeFieldSelection.getForm().layout();
				panelThreeFieldSelection.layout();

			}
		};
	}**/


}