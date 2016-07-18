package org.fao.fenix.web.modules.ofcchart.client.view.wizard;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartWizardController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.ListBox;

public class OfcSelectOptions extends TabItem {
	
	private ContentPanel mainCont;
	private ListBox mu;
	private ListBox aggregation;

		
	
	private Button next;
	private Button back;
	private Button ok;
	
	
	public OfcSelectOptions(OfcChartWizard chartWizard){
		
		this.setEnabled(false);
		setText("Options");
		mu = new ListBox();
		mu.setMultipleSelect(true);
		mu.setSize("100px", "50px");
		aggregation = new ListBox();
		aggregation.setWidth("100px");
		fillAggregationListBox(aggregation);

		next = new Button(BabelFish.print().next());
		next.addSelectionListener(OfcChartWizardController.changeTab(5, chartWizard));
		back = new Button(BabelFish.print().back());
		back.addSelectionListener(OfcChartWizardController.changeTab(3, chartWizard));
		ok = new Button(BabelFish.print().ok());
		ok.setEnabled(false);
//		ok.addSelectionListener(ChartWizardController.chartPreview(chartWizard));
		
		mainCont = new ContentPanel();
		mainCont.setBodyBorder(true);
//		mainCont.setLayout(new AccordionLayout());
		mainCont.setHeaderVisible(false);
		mainCont.setSize(730, 470);
		mainCont.setScrollMode(Scroll.AUTO);
		

		HorizontalPanel hr = new HorizontalPanel();
		hr.setSpacing(5);
		hr.add(back);
		hr.add(ok);
		hr.add(next);
		hr.setHorizontalAlign(HorizontalAlignment.RIGHT);	
		mainCont.add(createMeasuramentUnits());
		mainCont.add(createAggregations());
		mainCont.setBottomComponent(hr);
		add(mainCont);
	}
	

	private void fillAggregationListBox(ListBox listBox){
		listBox.addItem("None", "0");
		listBox.addItem("Sum", "1");
		listBox.addItem("AVG", "2");
	}
	
	private FieldSet createMeasuramentUnits(){
		
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Measurament Units");	
		fieldSet.setSize(670, 90);
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
		layout.setLabelPad(4);
		fieldSet.setLayout(layout);
		Html label = new Html("Measurament Unit");
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(10);
		h.add(label);
		h.add(mu);		
		fieldSet.add(h);
		return fieldSet;
	}
	
	
	private FieldSet createAggregations(){
		
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Aggregation");	
		fieldSet.setSize(670, 60);
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
		layout.setLabelPad(4);
		fieldSet.setLayout(layout);
		Html label = new Html("Aggregation");
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(10);
		h.add(label);
		h.add(aggregation);		
		fieldSet.add(h);
		return fieldSet;
	}
	
	public void clean(){
		mu = new ListBox();
		mu.setWidth("100px");
		mu.setMultipleSelect(true);
	}

	public Button getOk() {
		return ok;
	}

	public ListBox getMu() {
		return mu;
	}


	public void setMu(ListBox mu) {
		this.mu = mu;
	}


	public ListBox getAggregation() {
		return aggregation;
	}


	public void setAggregation(ListBox aggregation) {
		this.aggregation = aggregation;
	}

}