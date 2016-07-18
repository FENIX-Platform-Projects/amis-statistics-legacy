package org.fao.fenix.web.modules.amis.client.view.compare;

import org.fao.fenix.web.modules.amis.client.control.compare.AMISCompareController;
import org.fao.fenix.web.modules.amis.client.control.compare.AMISSelectorController;
import org.fao.fenix.web.modules.amis.client.view.login.AMISLoginPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.core.client.security.FenixUser;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Size;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.Label;

public class AMISCompareSelectorPanel {

	AMISCompareSelectorMaker selectorMaker;

	public AMISCompareSelectorPanel() {
		selectorMaker = new AMISCompareSelectorMaker();
	}

	public void build(final AMISCompare compare) {

		compare.getForm().insert(buildDataSourceSelector(compare.getFormData(), compare), 0, compare.getFormData());   

		buildMainItemSelectors(compare);

		//compare.getColumn4().add(FormattingUtils.addVSpace(20));
		compare.getColumn4().add(FormattingUtils.addVSpace(8));
		compare.getColumn4().add(buildOutputSelector(compare.getFormData(), compare));
		//compare.getColumn4().add(buildOutputSelector(compare.getFormData()), compare);  
		compare.getColumn4().add(buildCompareBySelector(compare.getFormData()), compare.getFormData());   
		//compare.getColumn4().add(FormattingUtils.addVSpace(20));
		//compare.getColumn4().add(FormattingUtils.addVSpace(1));
		compare.getColumn4().add(compare.getShowComparisonButton());
		//compare.getColumn5().add(FormattingUtils.addVSpace(190));
	   // compare.getColumn5().add(compare.getShowComparisonButton());

		compare.getSelectionPanel().add(compare.getForm());

	}

	private FieldSet buildDataSourceSelector(FormData formData, AMISCompare compare){

		FieldSet  fieldSet = new FieldSet(); 
		fieldSet.setCollapsible(false); 
		fieldSet.setStyleAttribute("background", "white");
		fieldSet.setStyleAttribute("border", "white");
		fieldSet.setStyleAttribute("color", "black");
		fieldSet.setStyleAttribute("padding-bottom", "0px");
		
		FormLayout layout = new FormLayout();   
		layout.setLabelWidth(200);   
		fieldSet.setLayout(layout);  

		final CheckBoxGroup checkBoxGroup = new CheckBoxGroup();   
		checkBoxGroup.setSpacing(20);	 
		checkBoxGroup.setFieldLabel("<font color='#154179'><b>Select Data Source(s)</b></font>");   
		checkBoxGroup.setId("DATASOURCE");
		
		CheckBox checkBox = new CheckBox();   
//		checkBox.setBoxLabel("FAO-CBS (C)");
        checkBox.setBoxLabel("FAO-AMIS (C)");
		checkBox.setId(AMISConstants.CBS.toString());
		checkBox.setValue(true);  
		checkBox.addListener(Events.Change, AMISSelectorController.getDataSourceCountryListListener(compare, checkBoxGroup, selectorMaker));
		checkBoxGroup.add(checkBox);   

		checkBox = new CheckBox();   
		checkBox.setBoxLabel("FAOSTAT (F)"); 
		checkBox.setId(AMISConstants.FAOSTAT.toString());
		checkBox.addListener(Events.Change, AMISSelectorController.getDataSourceCountryListListener(compare, checkBoxGroup, selectorMaker));
		//checkBoxGroup.add(checkBox);   

		checkBox = new CheckBox();   
		checkBox.setBoxLabel("USDA-PSD (P)"); 
		checkBox.setId(AMISConstants.PSD.toString());
		checkBox.addListener(Events.Change, AMISSelectorController.getDataSourceCountryListListener(compare, checkBoxGroup, selectorMaker));
		checkBoxGroup.add(checkBox);   
			
		checkBox = new CheckBox();   
		checkBox.setBoxLabel("IGC (I)");   
		checkBox.setId(AMISConstants.IGC.toString());
		checkBox.addListener(Events.Change, AMISSelectorController.getDataSourceCountryListListener(compare, checkBoxGroup, selectorMaker));
		checkBoxGroup.add(checkBox);
		
		checkBox = new CheckBox();   
		checkBox.setBoxLabel("AMIS (A)"); 
		checkBox.setId(AMISConstants.AMIS.toString());
		checkBox.setEnabled(false);
		checkBox.addListener(Events.Change, AMISSelectorController.getDataSourceCountryListListener(compare, checkBoxGroup, selectorMaker));
		//checkBoxGroup.add(checkBox);
			
		checkBox = new CheckBox();   
		checkBox.setId(AMISConstants.NATIONAL.toString());
		checkBox.addListener(Events.Change, AMISSelectorController.getDataSourceCountryListListener(compare, checkBoxGroup, selectorMaker));
       // checkBox.setEnabled(true);

        //if(!CCBS.COUNTRY_NAMES.isEmpty()) {
        //    System.out.println("CCBS.COUNTRY_NAMES.isEmpty() "+CCBS.COUNTRY_NAMES.isEmpty() + " | CCBS.COUNTRY_NAMES.get(0) "+ CCBS.COUNTRY_NAMES.get(0));
        //    checkBox.setBoxLabel("NATIONAL "+CCBS.COUNTRY_NAMES.get(0) +" Only (N)");
        //}
       // else {
       //     System.out.println("CCBS.COUNTRY_NAMES.isEmpty() "+ CCBS.COUNTRY_NAMES.isEmpty());
//            checkBox.setBoxLabel("NATIONAL (N)");
//        checkBox.setBoxLabel("NATIONAL (N) - as of mid-December 2013");
        //checkBox.setBoxLabel("NATIONAL (N) - as of mid-May 2014");
        checkBox.setBoxLabel("NATIONAL (N)");
       // }

        // Log in handling
//		if(!FenixUser.hasAdminRole()) {
//			//checkBox.setBoxLabel("NATIONAL (N) Requires 'Log In'");
//			checkBox.setEnabled(false);
//		}
//		else
//		{
//			if((AMISLoginPanel.getAmisUserParameters().getUsername()!=null)&& AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT))
//			{
//				checkBox.setEnabled(true);
//				checkBox.setBoxLabel("NATIONAL (N)");
//			}
//			if((AMISLoginPanel.getAmisUserParameters().getUsername()!=null)&& !AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT))
//			{
//				checkBox.setEnabled(false);
//				checkBox.setBoxLabel("NATIONAL (N)");
//			}
//
//			if((AMISLoginPanel.getAmisUserParameters().getUsername()!=null)&& !(AMISLoginPanel.getAmisUserParameters().getUsername().equals(CCBS.AMIS_SECRETARIAT)))
//			{
//				checkBox.setEnabled(true);
//
//				if(!CCBS.COUNTRY_NAMES.isEmpty()) {
//					System.out.println("CCBS.COUNTRY_NAMES.isEmpty() "+CCBS.COUNTRY_NAMES.isEmpty() + " | CCBS.COUNTRY_NAMES.get(0) "+ CCBS.COUNTRY_NAMES.get(0));
//					checkBox.setBoxLabel("NATIONAL "+CCBS.COUNTRY_NAMES.get(0) +" Only (N)");
//				}
//				else {
//					System.out.println("CCBS.COUNTRY_NAMES.isEmpty() "+ CCBS.COUNTRY_NAMES.isEmpty());
//					checkBox.setBoxLabel("NATIONAL (N)");
//				}
//			}
//		}

        if(!FenixUser.hasAdminRole()) {
            //checkBox.setBoxLabel("NATIONAL (N) Requires 'Log In'");
            //checkBox.setEnabled(false);
            checkBox.hide();
        }
        else{
            //Admin... Secretariat or Country
            //checkBox.setEnabled(true);
            checkBox.show();
        }
		
		checkBoxGroup.add(checkBox);   
	
		
		fieldSet.add(checkBoxGroup, formData);   


		return fieldSet;
	}

	private void buildMainItemSelectors(final AMISCompare compare){

		//final Html elementNote = new Html("<div class='compare_text'>*Availability of data by source (e.g. [C,P] means data is available for FAO-CBS and USDA-PSD, but not from FAOSTAT)</div>");
			
		ContentPanel countries = buildTitleContainer("Countries/Regions");
		final CheckBoxListView<AMISCodesModelData> countriesList = selectorMaker.buildCheckBoxList(compare, AMISConstants.AMIS_COUNTRIES.toString(), AMISConstants.AMIS_COUNTRIES.toString(), true, false, "190px", "200px", null);
//		final CheckBoxListView<AMISCodesModelData> countriesList = selectorMaker.buildCheckBoxList(compare, AMISConstants.AMIS_COUNTRIES.toString(), AMISConstants.AMIS_COUNTRIES.toString(), true, false, "190px", "155px", null);
		
		compare.getColumn1().add(countries);
		compare.getColumn1().add(countriesList, compare.getFormData());
		
		Button clearAllButton = new Button("<b>Clear All</b> Area Selections");
		clearAllButton.setIconStyle("refreshListsIcon");
		clearAllButton.addSelectionListener(AMISCompareController.clearSelections(countriesList, compare));
		compare.getColumn1().add(FormattingUtils.addVSpace(5));
		compare.getColumn1().add(clearAllButton);
		

		ContentPanel products = buildTitleContainer("Products");
		final CheckBoxListView<AMISCodesModelData> productsList = selectorMaker.buildCheckBoxList(compare, AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString(), AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT_COMPARE.toString(), true, false, "190px", "200px", null);
		//final CheckBoxListView<AMISCodesModelData> productsList = selectorMaker.buildCheckBoxList(compare, AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString(), AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString(), true, false, "190px", "155px", null);
		//TEST HIDE MAY NEED TO RE-INSTATE:productsList.getSelectionModel().addListener(Events.SelectionChange, AMISCompareController.getElementsForProducts(compare, productsList, compare.getColumn3(), selectorMaker));
		productsList.addListener(Events.OnClick, AMISCompareController.getElementsForProductsOnClick(compare, productsList, compare.getColumn3(), selectorMaker));
		
		
		compare.getColumn2().add(products);
		compare.getColumn2().insert(productsList, 1, compare.getFormData());
		
		ContentPanel elements = buildTitleContainer("Elements");
		final CheckBoxListView<AMISCodesModelData> elementsList = selectorMaker.buildCheckBoxList(compare, AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString(), AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT_COMPARE.toString(), true, false, "200px", "200px", null);
		//final CheckBoxListView<AMISCodesModelData> elementsList = selectorMaker.buildCheckBoxList(compare, AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString(), AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString(), true, false, "200px", "155px", null);
		elementsList.addListener(Events.OnClick,   
				new Listener<BaseEvent>() {   
			public void handleEvent(final BaseEvent ce) {
			/* Single element selection: 
			  	AMISCodesModelData selected = elementsList.getSelectionModel().getSelectedItem();
			 
				for(AMISCodesModelData model: elementsList.getChecked()) {
					if(!model.getCode().equals(selected.getCode())) {
						elementsList.getSelectionModel().deselect(model);
						elementsList.refreshNode(elementsList.getStore().indexOf(model));
					}

				}*/
				if(productsList.getChecked().size() == 0) {
					AMISCompareController.rebuildProductSelector(compare, elementsList, compare.getColumn2(), selectorMaker);		
				}
					
			}
		});
		
		/*if(productsList.isRendered()){
			System.out.println(" elementsList: productsList checked size "+ productsList.getChecked().size());
			if(productsList.getChecked().size() == 0) {
				elementsList.getSelectionModel().addListener(Events.SelectionChange, AMISCompareController.getProductsForElement(compare, elementsList, compare.getColumn2(), selectorMaker));
				elementsList.addListener(Events.OnClick, AMISCompareController.getProductsForElementOnClick(compare, elementsList, compare.getColumn2(), selectorMaker));
			}
			
		} */
		
      	
		compare.getColumn3().add(elements);
		compare.getColumn3().add(elementsList, compare.getFormData());
		//compare.getColumn3().add(elementNote);
		
		
		
		Button resetButton = new Button("<b>Clear All</b> Product Selections");
		resetButton.setIconStyle("refreshListsIcon");
		resetButton.addSelectionListener(AMISCompareController.reset(productsList, elementsList, compare, compare.getColumn3(), compare.getColumn2(), selectorMaker));
		compare.getColumn2().add(FormattingUtils.addVSpace(5));
		compare.getColumn2().add(resetButton);
	

		HorizontalPanel dateContainer = buildDateContainer();
		compare.getColumn4().add(dateContainer,compare.getFormData());

	}
	

	private ContentPanel buildTitleContainer(String title){

		ContentPanel container = new ContentPanel();
		container.setHeaderVisible(false);		
		container.setAutoHeight(true);
		container.setScrollMode(Scroll.NONE);
		
		container.setBorders(false);
		container.setBodyBorder(false);
		

		Html titleHtml = new Html();
		titleHtml.setHtml("<div class='compare_tab_selected'>"+title+"</div>");

		container.add(titleHtml);

		return container;
	}

	private FieldSet buildCompareBySelector(FormData formData){

		FieldSet fieldSet = new FieldSet(); 
	    fieldSet.setHeading("<font color='#000000' style='font-weight:normal;font-size:13px'>Compare by: </font>");   
		fieldSet.setCollapsible(false);  
		fieldSet.setStyleAttribute("padding-right", "0px");
		fieldSet.setStyleAttribute("padding-left", "0px");
		fieldSet.setStyleAttribute("width", "170px");
		
		FormLayout layout = new FormLayout(); 
		layout.setLabelSeparator("");
		layout.setLabelAlign(LabelAlign.LEFT);	
		layout.setLabelWidth(0);
		layout.setLabelPad(0);
		//layout.setAnchorSize(new Size(2, 3));
		fieldSet.setLayout(layout);  
		
		RadioGroup radioGroup = new RadioGroup();   
		//radioGroup.setAutoHeight(true);
		//radioGroup.setSpacing(7);
		radioGroup.setSpacing(1);
		radioGroup.setOrientation(Orientation.VERTICAL);
		radioGroup.setId("COMPARE_BY");
		radioGroup.setStyleAttribute("width", "170px");
		
		Radio radio = new Radio();      
		radio.setId(AMISConstants.AMIS_DATASETS.toString());
		radio.setBoxLabel("Data Sources");   
		radio.setValue(true);  
		radioGroup.add(radio);   

		radio = new Radio();   
		radio.setId(AMISConstants.AMIS_COUNTRIES.toString());
		radio.setBoxLabel("Countries/Regions");   
		radioGroup.add(radio);   

		radio = new Radio();   
		radio.setId(AMISConstants.AMIS_GET_PRODUCTS_BY_ELEMENT.toString());
		radio.setBoxLabel("Products");  
		radioGroup.add(radio);   
    
		
		radio = new Radio();   
		radio.setId(AMISConstants.AMIS_GET_ELEMENTS_BY_PRODUCT.toString());
		radio.setBoxLabel("Elements");  
		radioGroup.add(radio);   
		
		fieldSet.add(radioGroup, formData);

		return fieldSet;
	}
	
	private HorizontalPanel buildDateContainer(){

		HorizontalPanel panel = new HorizontalPanel();
		panel.setId(AMISConstants.AMIS_YEAR_PANEL.toString());
		//panel.setSpacing(5);
		
		Html titleHtml = new Html();
		titleHtml.setHtml("<div class='compare_tab_label'>From</div>");
		panel.add(titleHtml);
		
//		final  ComboBox<AMISCodesModelData> fromDateCombo = selectorMaker.buildComboBox("From", AMISConstants.TIMERANGE.name(), AMISConstants.TIMERANGE.name()+"_FROM", AMISConstants.TIMERANGE.toString(), false, false, "60", "400px", "ASC");
        final  ComboBox<AMISCodesModelData> fromDateCombo = selectorMaker.buildComboBox("From", AMISConstants.TIMERANGE_SPLIT.name(), AMISConstants.TIMERANGE.name()+"_FROM", AMISConstants.TIMERANGE.toString(), false, false, "80", "400px", "ASC");
		panel.add(fromDateCombo);
		
		titleHtml = new Html();
		titleHtml.setHtml("<div class='compare_tab_label'>To</div>");
		panel.add(titleHtml);
		
//		final  ComboBox<AMISCodesModelData> toDateCombo = selectorMaker.buildComboBox("To",  AMISConstants.TIMERANGE.name(), AMISConstants.TIMERANGE.name()+"_TO", AMISConstants.TIMERANGE.toString(), false, false, "60", "400px", "DESC");
        final  ComboBox<AMISCodesModelData> toDateCombo = selectorMaker.buildComboBox("To",  AMISConstants.TIMERANGE_SPLIT.name(), AMISConstants.TIMERANGE.name()+"_TO", AMISConstants.TIMERANGE.toString(), false, false, "80", "400px", "DESC");
		panel.add(toDateCombo);
	
		return panel;
	}
	
	//Show charts or olaptable
	private FieldSet buildOutputSelector(FormData formData, AMISCompare compare){

		FieldSet fieldSet = new FieldSet(); 
	    fieldSet.setHeading("<font color='#000000' style='font-weight:normal;font-size:13px'>View by: </font>");   
		fieldSet.setCollapsible(false);  
		fieldSet.setStyleAttribute("padding-right", "0px");
		fieldSet.setStyleAttribute("padding-left", "0px");
		fieldSet.setStyleAttribute("width", "150px");
		
		FormLayout layout = new FormLayout(); 
		layout.setLabelSeparator("");
		layout.setLabelAlign(LabelAlign.LEFT);	
		layout.setLabelWidth(0);
		layout.setLabelPad(0);
		//layout.setAnchorSize(new Size(2, 3));
		fieldSet.setLayout(layout);  
		
		RadioGroup radioGroup = new RadioGroup();   
		//radioGroup.setAutoHeight(true);
		//radioGroup.setSpacing(7);
		radioGroup.setSpacing(1);
		radioGroup.setOrientation(Orientation.HORIZONTAL);
		radioGroup.setId("VIEW_BY");
		radioGroup.setStyleAttribute("width", "150px");
		
		Radio radio = new Radio();      
		radio.setId(AMISConstants.AMIS_CHART.toString());
		radio.setBoxLabel("Chart");   
		radio.setValue(true);  
		
		radio.addListener(Events.OnClick, AMISCompareController.changeFieldsetHeading(compare, radio, "COMPARE"));
		radioGroup.add(radio);   		
		
		Radio radio2 = new Radio();   
		radio2.setId(AMISConstants.OLAP_TABLE.toString());
		radio2.setBoxLabel("Table"); 
		radio2.addListener(Events.OnClick, AMISCompareController.changeFieldsetHeading(compare, radio2, "NESTED"));
		radioGroup.add(radio2);   

		fieldSet.add(radioGroup, formData);

		return fieldSet;
	}
	
	
	
}
