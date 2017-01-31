package org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.byarea;


import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.selection.FAOSTATMultipleSelectionPanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

public class FAOSTATCompareByArea {
	
	ContentPanel panel;
	
	ContentPanel areasPanel;
	
	ContentPanel selectionPanel;
	
	ContentPanel outputPanel;
	
	ContentPanel tablePanel;
	
	FAOSTATAreasPanel areas;
	
	FAOSTATCompareSelectorPanel domains;
	
	FAOSTATCompareSelectorPanel years;
	
	FAOSTATCompareSelectorPanel elements;
		
	FAOSTATCompareSelectorPanel items;
	
	
	Html message;
	

	Button compareButton;
	
	FormPanel formPanel;
	FormData formData;
	LayoutContainer column1;   
	LayoutContainer column2;   
	LayoutContainer column3;  
	LayoutContainer column4;  
	
	
	public FAOSTATCompareByArea() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		panel.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.getMainContentHeight(false) + "px"));

				
		areasPanel = new ContentPanel();
		areasPanel.setBodyBorder(false);
		areasPanel.setHeaderVisible(false);
	
			
		
		selectionPanel = new ContentPanel();
		selectionPanel.setAutoWidth(true);
		selectionPanel.setAutoHeight(true);
		selectionPanel.setBorders(false);
		selectionPanel.setBodyBorder(false);
		selectionPanel.setHeaderVisible(false);
		
		
		outputPanel = new ContentPanel();
		outputPanel.setAutoWidth(true);
		outputPanel.setAutoHeight(true);
		outputPanel.setBodyBorder(false);
		outputPanel.setHeaderVisible(false);
		
			
		formPanel = new FormPanel();
		formData = new FormData("100%");   
		formPanel.setFrame(false);   
		formPanel.setHeaderVisible(false);
	    formPanel.setSize(1050, -1);   
	    formPanel.setFieldWidth(100);
	  //  formPanel.setLabelAlign(LabelAlign.TOP);   
	    formPanel.setButtonAlign(HorizontalAlignment.CENTER); 
		   	
	
	    LayoutContainer main = new LayoutContainer(); 
	    main.setStyleAttribute("padding", "10px");
	    main.setLayout(new ColumnLayout());   
	  
	    //view
	    column1 = new LayoutContainer();   
	    column1.setStyleAttribute("paddingRight", "20px");   
	    column1.setLayout(new FormLayout());   
	       
	    //domain
	    column2 = new LayoutContainer();
	    FormLayout layout = new FormLayout();
	    layout.setLabelAlign(LabelAlign.TOP);
	    column2.setStyleAttribute("paddingLeft", "10px");   
	    column2.setLayout(layout);   
	    
	    //date 1
	    column3 = new LayoutContainer();
	    column3.setStyleAttribute("paddingLeft", "40px");  
	    FormLayout lay = new FormLayout();
	    lay.setLabelAlign(LabelAlign.TOP);
	    column3.setLayout(lay); 
	    
	  //date 2
	    column4 = new LayoutContainer();
	    column4.setStyleAttribute("paddingLeft", "80px");  
	    FormLayout lay2 = new FormLayout();
	    lay2.setLabelAlign(LabelAlign.TOP);
	    column4.setLayout(lay2); 
	  

	     
		Button b = new Button("<b><font color='#33528C'>"+FAOSTATLanguage.print().showComparison()+"</font></b>");
		b.setIconStyle("showComparisonChartIcon");
		b.setHeight(30);
		b.addSelectionListener(FAOSTATCompareController.compareAreas(outputPanel, formPanel, column1, column2));
		
		column4.add(DataViewerClientUtils.addVSpace(10));
		
		column4.add(b);
		
		column4.add(DataViewerClientUtils.addVSpace(5));
		
		column4.add(new Html("<div class='empty-values-panel'><img src='images/information.png' align='bottom'/><font size='1.5px'> "+FAOSTATLanguage.print().selectedAreasYearsWithNoDataAreNotShownInTheChart()+"</font></div>"));
		
	
		
		//main.add(leftColumn, new ColumnData(.5));   
	    //main.add(middleColumn, new ColumnData(.5));   
	    //main.add(rightColumn, new ColumnData(.5));   
	    
	    main.add(column1, new ColumnData(250));
	    main.add(column2, new ColumnData(300));
	    main.add(column3, new ColumnData(160));
	    main.add(column4, new ColumnData(280));
	  
	    formPanel.add(main, new FormData("100%"));  
	    
	    //mainContainer.add(main);		
		//formPanel.add(mainContainer, new FormData("100%"));

		
		//formPanel.addButton(b);   
	   
		FormButtonBinding binding = new FormButtonBinding(formPanel);   
	    binding.addButton(b);   
	    
		
	    
		areas = new FAOSTATAreasPanel();
		
		

	}

	public ContentPanel build() {

		panel.add(new Html("<p class='compare_text'>"+FAOSTATLanguage.print().clickOnTheCountriesRegionsYouWantToCompare()+"</p>")); 		
		panel.add(buildMessage());		
		panel.add(buildMainPanel());
		panel.add(DataViewerClientUtils.addVSpace(10));
		HorizontalPanel h = new HorizontalPanel();
		
		h.add(DataViewerClientUtils.addHSpace(10));
		h.add(outputPanel);
		panel.add(h);
	
		return panel;
	}
	
//	public ContentPanel build() {
//
//		panel.add(new Html("<p class='compare_text'>"+FAOSTATLanguage.print().clickOnTheCountriesRegionsYouWantToCompare()+"</p>")); 		
//		panel.add(buildMessage());		
//
//		panel.add(DataViewerClientUtils.addVSpace(10));
//
//
//		
//		
//		panel.add(DataViewerClientUtils.addVSpace(10));
//		FAOSTATMultipleSelectionPanel sp1 = new FAOSTATMultipleSelectionPanel();
//		panel.add(sp1);
//		panel.add(outputPanel);
//		
//		
//		return panel;
//	}
	
	
	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		//selectionPanel.setHeading("Click on the countries/regions you want to compare");
		
		//p.add(buildAreas());
		areas.build(this);
		
		
		p.add(selectionPanel);
		
		

		return p;
	}
	
	public void buildSelectors(DWCodesModelData code){
		selectionPanel.removeAll();
		
		
		VerticalPanel p = new VerticalPanel();
		p.add(DataViewerClientUtils.addVSpace(5));
		
		HorizontalPanel h0 = new HorizontalPanel();
		HorizontalPanel h1 = new HorizontalPanel();
		HorizontalPanel h2 = new HorizontalPanel();
		VerticalPanel v1 = new VerticalPanel();
		VerticalPanel v2 = new VerticalPanel();
		
		//build domains
		// build subdomains
		// build years to and from
		// build items
		//build elements
		//.add(buildCountriesYears(code));
		v1.add(addSpace());
		//v1.add(buildElementsItems(code));
		h1.add(v1);
			
		v2.add(DataViewerClientUtils.addVSpace(20));
		h2.add(v2);
		
		h0.add(h1);
		h0.add(h2);
		p.add(h0);
		p.add(DataViewerClientUtils.addVSpace(5));
		p.add(addButtons());
		
		selectionPanel.add(p);
		
		selectionPanel.layout();
	}
	
	
	private HorizontalPanel addSpace(){ 
		HorizontalPanel p = new HorizontalPanel();
		p.setHeight(10);
		return p;
	}
	
	
	private HorizontalPanel addButtons() {
		HorizontalPanel p = new HorizontalPanel();
		p.add(DataViewerClientUtils.addHSpace(10));
		
		compareButton = new Button("Compare Areas");
		//compareButton.setIconStyle("sendToExcel");
		
		p.add(compareButton);
		
		return p;
	}
	
	private Html buildMessage(){
		//message.setStyleAttribute("padding-left", "20px");
		message = new Html();
		message.setStyleName("selected_message");
		message.setHtml("");
	
		return message;
	}
	
	public Html getMessage(){
		return message;
	}
	
//	private ContentPanel buildAreas() {
	//	return areas.build();
	//}

	public ContentPanel getPanel() {
		return panel;
	}
	
	public FormPanel getForm() {
		return formPanel;
	}

	public FormData getFormData() {
		return formData;
	}

	
	public ContentPanel getAreaPanel() {
		return areasPanel;
	}

	public ContentPanel getSelectionPanel() {
		return selectionPanel;
	}

	public FAOSTATAreasPanel getAreas() {
		return areas;
	}

	public LayoutContainer getColumn1() {
		return column1;
	}

	public LayoutContainer getColumn2() {
		return column2;
	}
	
	public LayoutContainer getColumn3() {
		return column3;
	}
	
	public LayoutContainer getColumn4() {
		return column4;
	}

	public ContentPanel getOutputPanel() {
		return outputPanel;
	}

	@SuppressWarnings("unchecked")
	public ComboBox<DWCodesModelData> getColumn2ComboBoxById(String id){
			Component component = column2.getItemByItemId(id);	   
		   if(component==null)
			   return null;
		   else
			   return (ComboBox<DWCodesModelData>) component;
	}
	
	public void removeColumn2ComboBoxById(String id){			
		ComboBox<DWCodesModelData> combo = getColumn2ComboBoxById(id);
		if(combo!=null)
			column2.remove(combo);			
	}
	
	
}
