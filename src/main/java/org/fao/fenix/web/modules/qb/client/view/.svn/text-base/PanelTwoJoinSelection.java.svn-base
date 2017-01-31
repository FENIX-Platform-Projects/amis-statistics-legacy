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
import org.fao.fenix.web.modules.qb.client.control.PanelTwoController;
import org.fao.fenix.web.modules.qb.client.util.Counter;
import org.fao.fenix.web.modules.qb.client.util.DateOperatorType;
import org.fao.fenix.web.modules.qb.client.util.NumericOperatorType;
import org.fao.fenix.web.modules.qb.client.util.Operator;
import org.fao.fenix.web.modules.qb.client.util.StringOperatorType;
import org.fao.fenix.web.modules.qb.common.model.DimensionModel;
import org.fao.fenix.web.modules.qb.common.vo.DatasetVO;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;

import com.extjs.gxt.ui.client.Style.ButtonScale;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;

public class PanelTwoJoinSelection extends LayoutContainer {

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
	
  
	public PanelTwoJoinSelection(QueryBuilder queryBuilder) {
		hideToolTip();
		//setStyleAttribute("padding", "15px");
		setId("screen-2");

		setQueryBuilder(queryBuilder);

		//DatasetSelectionPanelOne panelOne = queryBuilder.getDatasetSelectionPanelOne();

		form = new FormPanel();
		form.setScrollMode(Scroll.AUTO);
		form.setPadding(10);
		form.setFrame(false);
		form.setHeaderVisible(false);
		form.setBodyBorder(false);
		form.setButtonAlign(HorizontalAlignment.CENTER);
		form.setLayout(new FitLayout());

		formData = new FormData("100%");
	    vp = new VerticalPanel();
	    vp.setSpacing(10);

	    //String introText = I18N.print().selectRecordsWhereFollowingConditionsApply();

	  	
		//html = new Html(introText);
		datasetAliasInfo = new Html();

		//html.setVisible(false);
		datasetAliasInfo.setVisible(false);
	
		vp.add(new Text("The <b>Join</b> or Shared Dimensions, for each dataset are given below"));
		
		vp.add(datasetAliasInfo);
		//vp.add(html);

		addConditionButton = new Button("Click to Add Another Join Dimension");
		addConditionButton.setScale(ButtonScale.MEDIUM);
		addConditionButton.setIconStyle("addIcon");
		addConditionButton.addSelectionListener(PanelTwoController.addCondition(this));

		
		form.add(addConditionButton);

		format();

		vp.add(form);
		add(vp);
	}


	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		PanelTwoController.setDimensionListAndDatasetInfoMap(this, queryBuilder.getPanelOneDatasetSelection());
		
		setTitle(queryBuilder.getStepXofN()+" Join Column Selection");
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
	
	public void buildAddJoinColumnPanel(){

				HorizontalPanel panel = new HorizontalPanel();
				panel.setSpacing(10);
				panel.setBorders(true);
				
				for (Long dataId : getDatasetInfoMap().keySet()) {
					LayoutContainer container = new LayoutContainer();
					FormLayout formLayout = new FormLayout();
					formLayout.setHideLabels(true); 
					container.setLayout(formLayout);
					container.setId(dataId.toString());
					
					DatasetVO dataVO = getDatasetInfoMap().get(dataId);	
					//container.add(new Text(dataVO.getDatasetTitle()));
					SimpleComboBox<String> combo = new SimpleComboBox<String>();
					combo.setTriggerAction(TriggerAction.ALL);	
						
					
						Map<String, DimensionBeanVO> dimensions = dataVO.getDimensionDescriptorMap();

						for (String dimensionID : dimensions.keySet()) {
							DimensionBeanVO dimensionBeanVO = dimensions.get(dimensionID);
							combo.add(dimensionBeanVO.getHeader());
					}
						combo.setEmptyText("Please select");
						
						container.add(combo, new FormData("100%"));
						
						panel.add(container);
				}
				
				panel.add(createRemoveButton(panel));
				
				form.add(panel);
			
		
		
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
    	
    	 button.addSelectionListener(PanelTwoController.openItemSelectorDialog(this, selectedDimension, displaySelectedItemsText));

 		 return button;
 	  }
     
     
   public Button createRemoveButton(HorizontalPanel conditionPanel){
 
	    Button removeConditionButton = new Button("Remove");
		removeConditionButton.setScale(ButtonScale.SMALL);
		removeConditionButton.setIconStyle("removeIcon");
		removeConditionButton.addSelectionListener(PanelTwoController.removeCondition(this, conditionPanel));

 		 return removeConditionButton;
 	  }

}