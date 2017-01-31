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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.qb.client.control.PanelThreeController;
import org.fao.fenix.web.modules.qb.client.util.Counter;
import org.fao.fenix.web.modules.qb.client.util.DateOperatorType;
import org.fao.fenix.web.modules.qb.client.util.NumericOperatorType;
import org.fao.fenix.web.modules.qb.client.util.Operator;
import org.fao.fenix.web.modules.qb.client.util.StringOperatorType;
import org.fao.fenix.web.modules.qb.common.model.DimensionModel;
import org.fao.fenix.web.modules.qb.common.vo.DatasetVO;

import com.extjs.gxt.ui.client.Style.ButtonScale;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;

public class PanelThreeFieldSelection extends LayoutContainer {

	private QueryBuilder queryBuilder;
	private List<DimensionModel> dimensionList;
	private Map<Long, DatasetVO> datasetInfoMap;
	private FormPanel form;
	private Button addConditionButton;
	private Button testButton;
	//private Html html;
	private Html datasetAliasInfo;
	private FormData formData;
	private VerticalPanel vp;
	public Counter counter = new Counter();


	public PanelThreeFieldSelection(QueryBuilder queryBuilder) {
		hideToolTip();
		//setStyleAttribute("padding", "15px");
		setId("screen-3");

		setQueryBuilder(queryBuilder);

		//DatasetSelectionPanelOne panelOne = queryBuilder.getDatasetSelectionPanelOne();

		form = new FormPanel();
		form.setPadding(0);
		form.setFrame(false);
		form.setHeaderVisible(false);
		form.setBodyBorder(false);
		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.setLayout(new FitLayout());

		formData = new FormData("100%");
	    vp = new VerticalPanel();
	    vp.setSpacing(10);

	    String introText = BabelFish.print().selectRecordsWhereFollowingConditionsApply();

		//html = new Html(introText);
		datasetAliasInfo = new Html();

		//html.setVisible(false);
		datasetAliasInfo.setVisible(false);

		vp.add(datasetAliasInfo);
		//vp.add(html);

		addConditionButton = new Button( BabelFish.print().clickToAddCondition());
		addConditionButton.setScale(ButtonScale.MEDIUM);
		addConditionButton.setIconStyle("addIcon");
		addConditionButton.addSelectionListener(PanelThreeController.addCondition(this));

		form.add(addConditionButton);

		format();

		vp.add(form);
		add(vp);
	}


	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		PanelThreeController.setDimensionListAndDatasetInfoMap(this, queryBuilder.getPanelOneDatasetSelection());
		setTitle(queryBuilder.getStepXofN()+" Add Conditions");
	}

	private void format() {

		form.setFrame(true);
		form.setLayout(new FlowLayout());
		form.setHeaderVisible(false);
		form.setLabelWidth(50);
		form.setLabelSeparator("");
		form.setLabelAlign(LabelAlign.LEFT);
		form.setHideLabels(false);
	}

	public int numberOfConditions(){
		List<Component> formItems = form.getItems();

		int i = 0;
		for(Component comp: formItems){
			if(comp instanceof HorizontalPanel)
				i++	;
		}
		System.out.println("no of conditions = "+i);
		return i;
	}

	public HorizontalPanel getFirstCondition(){
		return getAllConditions().get(0);
	}

	public List<HorizontalPanel> getAllConditions(){
		List<Component> formItems = form.getItems();

		List<HorizontalPanel> conditionList = new ArrayList<HorizontalPanel>();

		for(Component comp: formItems){
			if(comp instanceof HorizontalPanel)
				conditionList.add((HorizontalPanel)comp);
		}
		System.out.println("no of conditions = "+conditionList.size());
		return conditionList;
	}

	public void buildConditionPanel(boolean onLoad){

		HorizontalPanel conditionPanel = new HorizontalPanel();
		conditionPanel.setWidth(850);
		conditionPanel.setBorders(true);
		conditionPanel.setSpacing(10);
		conditionPanel.setData("onLoad", "true");
		conditionPanel.setId(counter.toString());

	    FormLayout layout = new FormLayout();

	    LayoutContainer includeConditionContainer = new LayoutContainer();
	    layout = new FormLayout();
	    layout.setHideLabels(true);
	    includeConditionContainer.setLayout(layout);

	  //checkbox for select/de-select condition
		CheckBox check = new CheckBox();
		check.setValue(true);
		check.setTitle("Include condition");

		includeConditionContainer.add(check, formData);
		conditionPanel.add(includeConditionContainer);


		if(counter.getValue() > 1){
			LayoutContainer andOrContainer = new LayoutContainer();
			andOrContainer.setId("addOrContainer");
			layout = new FormLayout();
			layout.setHideLabels(true);



			/* SimpleComboBox<String> simple = new SimpleComboBox<String>() {
					@Override
					protected String prepareData(String model) {
						String s = model.get("name");
						model.set("shortName", Format..ellipse(s, 40));
						return model;
					}

				};*/

			SimpleComboBox<String> simple = new SimpleComboBox<String>();
			 simple.setWidth(50);
			 simple.setTriggerAction(TriggerAction.ALL);
			 simple.setStyleAttribute("color", "red");
			 simple.add("AND");
			 simple.add("OR");
			 simple.setSimpleValue("AND");

			 andOrContainer.setLayout(layout);
			 andOrContainer.add(simple, formData);
			 conditionPanel.add(andOrContainer);
		}


		LayoutContainer container2 = new LayoutContainer();
		layout = new FormLayout();
		layout.setHideLabels(true);
		container2.setLayout(layout);


		//Dimension List
		//With the first item selected
		ComboBox<DimensionModel> datasetDimensions = new ComboBox<DimensionModel>();
		datasetDimensions.setTriggerAction(TriggerAction.ALL);
		datasetDimensions.setDisplayField("name");
		ListStore<DimensionModel> store = new ListStore<DimensionModel>();

		if(getDimensionList()!=null)
			store.add(getDimensionList());
		else System.out.println("--------------------- getDimensionList is NULL !!!!!!!!!!!!!!");
		store.sort("name", SortDir.ASC);

		datasetDimensions.setStore(store);
		datasetDimensions.setValue(store.getAt(0));

		//Currently selected dimension
	    DimensionModel selectedDimension = datasetDimensions.getValue();
	   // System.out.println("--------------------- selectedDimension = "+ selectedDimension.getName());
		String datasetId = selectedDimension.getCode();
		String dataType = selectedDimension.getDataType();

		GWT.log("selected dimension = "+selectedDimension.getName()+"("+dataType+")", null);

		container2.add(datasetDimensions, formData);

		//Operator List (either numeric, string or date operators displayed)
		//With the first item selected

		 LayoutContainer container3 = new LayoutContainer();
		 layout = new FormLayout();
		 layout.setHideLabels(true);
		 container3.setLayout(layout);


		ComboBox<Operator> operators = new ComboBox<Operator>();
		operators.setWidth(150);
		operators.setTriggerAction(TriggerAction.ALL);
		operators.setDisplayField("name");
		ListStore<Operator> operatorStore = new ListStore<Operator>();
		operatorStore.removeAll();

		populateOperatorsListStore(dataType, operatorStore);

		operators.setStore(operatorStore);
		operators.setValue(operatorStore.getAt(0));

		container3.add(operators, formData);

		conditionPanel.add(container2);
		conditionPanel.add(container3);

		//Currently selected operator
		Operator selectedOperator = operators.getValue();
		String selectedName = selectedOperator.getName();
		String selectedCode = selectedOperator.getCode();

		GWT.log("selected operator = "+selectedName+"("+selectedCode+")", null);


		LayoutContainer valueContainer = new LayoutContainer();
		valueContainer.setId("valueField");
		layout = new FormLayout();
		layout.setHideLabels(true);
		valueContainer.setLayout(layout);


		//Field Value (dependant on data type of dimension and operator)
	/**	List<Field> valueFieldList = createValueField(dataType, selectedCode, selectedDimension.getPeriodType());

		if(valueFieldList!=null && valueFieldList.size() > 0) {
			conditionPanel.setData("onLoad", "false");
			GWT.log("added field 1 ", null);
			for(Field valueField: valueFieldList){
				valueField.setId("valueField");
				GWT.log("added field 2", null);
				valueContainer.add(new Text("added"));
				valueContainer.add(new TextField("added"), formData);
				valueContainer.add(valueField, formData);
			}
		}**/



		conditionPanel.add(valueContainer);
		conditionPanel.add(createRemoveButton(conditionPanel));

		PanelThreeController.getValueField(this, operators, datasetDimensions, valueContainer, conditionPanel);


		operators.addListener(Events.SelectionChange, PanelThreeController.setValueField(this, operators, datasetDimensions, valueContainer, conditionPanel));

		datasetDimensions.addListener(Events.SelectionChange, PanelThreeController.setOperatorType(this, datasetDimensions, operatorStore, operators, conditionPanel));

        //Displaying the selected dataset names with an Alias
		final String alphabet = BabelFish.print().alphabet();

		StringBuilder builder = new StringBuilder();

		if(getDatasetInfoMap()!=null){
			for (Long dataId : getDatasetInfoMap().keySet()) {
				DatasetVO dataVO = getDatasetInfoMap().get(dataId);
				builder.append((dataVO.getDatasetTitle() +" = <b>" + alphabet.charAt(dataVO.getAliasIndex())) + "</b><br/>");

			}
		}

		datasetAliasInfo.setHtml(builder.toString());

		//add icon for nested query
		//html.setVisible(true);
		datasetAliasInfo.setVisible(true);

		//this container is just to test ID is correct



		form.add(conditionPanel, new FormData("100%"));
		counter.increment(conditionPanel);

		form.layout();

		vp.layout();
	}

	public void buildConditionPanel1(boolean onLoad){

		HorizontalPanel conditionPanel = new HorizontalPanel();
		conditionPanel.setWidth(700);
		conditionPanel.setBorders(true);
		conditionPanel.setSpacing(10);
		conditionPanel.setData("onLoad", "true");
		//conditionPanel.setId(id);

		LayoutContainer left = new LayoutContainer();
	  //  left.setStyleAttribute("paddingRight", "10px");
	    FormLayout layout = new FormLayout();
	    layout.setLabelAlign(LabelAlign.LEFT);
	    left.setLayout(layout);


		//checkbox for select/de-select condition
		CheckBox check = new CheckBox();
		check.setValue(true);
		check.setTitle("Include condition");

		conditionPanel.add(check);

		//Dimension List
		//With the first item selected
		ComboBox<DimensionModel> datasetDimensions = new ComboBox<DimensionModel>();
		datasetDimensions.setTriggerAction(TriggerAction.ALL);
		datasetDimensions.setDisplayField("name");
		ListStore<DimensionModel> store = new ListStore<DimensionModel>();

		if(getDimensionList()!=null)
			store.add(getDimensionList());
		else System.out.println("--------------------- getDimensionList is NULL !!!!!!!!!!!!!!");
		store.sort("name", SortDir.ASC);

		datasetDimensions.setStore(store);
		datasetDimensions.setValue(store.getAt(0));

		//Currently selected dimension
	    DimensionModel selectedDimension = datasetDimensions.getValue();
	   // System.out.println("--------------------- selectedDimension = "+ selectedDimension.getName());
		String datasetId = selectedDimension.getCode();
		String dataType = selectedDimension.getDataType();

		GWT.log("selected dimension = "+selectedDimension.getName()+"("+dataType+")", null);

		//Operator List (either numeric, string or date operators displayed)
		//With the first item selected
		ComboBox<Operator> operators = new ComboBox<Operator>();
		operators.setTriggerAction(TriggerAction.ALL);
		operators.setDisplayField("name");
		ListStore<Operator> operatorStore = new ListStore<Operator>();
		operatorStore.removeAll();

		populateOperatorsListStore(dataType, operatorStore);

		operators.setStore(operatorStore);
		operators.setValue(operatorStore.getAt(0));

		conditionPanel.add(datasetDimensions);
		conditionPanel.add(operators);

		//Currently selected operator
		Operator selectedOperator = operators.getValue();
		String selectedName = selectedOperator.getName();
		String selectedCode = selectedOperator.getCode();

		GWT.log("selected operator = "+selectedName+"("+selectedCode+")", null);



		//Field Value (dependant on data type of dimension and operator)
		List<Field> valueFieldList = createValueField(dataType, selectedCode, selectedDimension.getPeriodType());

		if(valueFieldList!=null) {

			for(Field valueField: valueFieldList){
				valueField.setId("valueField");
				conditionPanel.add(valueField);
			}
		}





		//operators.addListener(Events.SelectionChange, PanelTwoController.setValueField(this, operators, datasetDimensions, conditionPanel));

		datasetDimensions.addListener(Events.SelectionChange, PanelThreeController.setOperatorType(this, datasetDimensions, operatorStore, operators, conditionPanel));

        //Displaying the selected dataset names with an Alias
		final String alphabet = BabelFish.print().alphabet();

		StringBuilder builder = new StringBuilder();

		if(getDatasetInfoMap()!=null){
			for (Long dataId : getDatasetInfoMap().keySet()) {
				DatasetVO dataVO = getDatasetInfoMap().get(dataId);
				builder.append((dataVO.getDatasetTitle() +" = <b>" + alphabet.charAt(dataVO.getAliasIndex())) + "</b><br/>");

			}
		}

		datasetAliasInfo.setHtml(builder.toString());

		//add icon for nested query
		//html.setVisible(true);
		datasetAliasInfo.setVisible(true);

		form.add(conditionPanel, new FormData("100%"));

		form.layout();

		vp.layout();
	}

	public QueryBuilder getQueryBuilder() {
		return queryBuilder;
	}

	public void setQueryBuilder(QueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

	public void setDimensionList(List<DimensionModel> dimensionList) {
		this.dimensionList = dimensionList;
	}

	public List<DimensionModel> getDimensionList() {
		return dimensionList;
	}

	public void setDatasetInfoMap(Map<Long, DatasetVO> datasetInfoMap) {
		this.datasetInfoMap = datasetInfoMap;
	}

	public Map<Long, DatasetVO> getDatasetInfoMap() {
		return datasetInfoMap;
	}


	public FormPanel getForm() {
		return form;
	}

	public void setForm(FormPanel form) {
		this.form = form;
	}

     public void populateOperatorsListStore(String dataType, ListStore<Operator> operatorStore){
    	 if(dataType.equals("quantity")){
    		 for(NumericOperatorType type: NumericOperatorType.getOperatorTypes()){
    			 operatorStore.add(new Operator(BabelFish.print().getString(type.toString()), type.toString()));
    		 }
    	 } else if((dataType.toLowerCase()).contains("date")){
    		 for(DateOperatorType type: DateOperatorType.getOperatorTypes()){
    			 operatorStore.add(new Operator(BabelFish.print().getString(type.toString()), type.toString()));
    		 }
    	 }
    	 else {
    		 for(StringOperatorType type: StringOperatorType.getOperatorTypes()){
    			 operatorStore.add(new Operator(BabelFish.print().getString(type.toString()), type.toString()));
    		 }

    	 }
     }

     public List<Field> createValueField(String dataType, String selectedOperator, String periodType){

    	 if(dataType.equals("quantity")){
    		 return NumericOperatorType.getValueField(selectedOperator);
    	 } else if((dataType.toLowerCase()).contains("date")){
    		return DateOperatorType.getValueField(selectedOperator, periodType);
    	 }
    	 else {
    		 return StringOperatorType.getValueField(selectedOperator);

    	 }
     }

     public Button createChooseButton(DimensionModel selectedDimension, Text displaySelectedItemsText){

    	 Button button = new Button("choose");
    	 button.setScale(ButtonScale.SMALL);

    	 button.addSelectionListener(PanelThreeController.openItemSelectorDialog(this, selectedDimension, displaySelectedItemsText));

 		 return button;
 	  }


   public Button createRemoveButton(HorizontalPanel conditionPanel){

	    Button removeConditionButton = new Button("Remove");
		removeConditionButton.setScale(ButtonScale.SMALL);
		removeConditionButton.setIconStyle("removeIcon");
		removeConditionButton.addSelectionListener(PanelThreeController.removeCondition(this, conditionPanel));

 		 return removeConditionButton;
 	  }

}