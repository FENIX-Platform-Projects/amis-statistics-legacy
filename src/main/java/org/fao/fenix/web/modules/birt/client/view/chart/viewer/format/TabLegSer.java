package org.fao.fenix.web.modules.birt.client.view.chart.viewer.format;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TabLegSer extends TabItem {
	
	VerticalPanel mainCont;
	
	CheckBox chBoxLabel;
	ListBox sizeLabel;
	ListBox position;
	VerticalPanel colorCont;
	FieldSet fieldSetSeries;
	ListBox disposition;
	TextField<String> unitSpace;
	
	
	public ListBox getDisposition() {
		return disposition;
	}

	public FieldSet getFieldSetSeries() {
		return fieldSetSeries;
	}

	public CheckBox getChBoxLabel() {
		return chBoxLabel;
	}

	public void setChBoxLabel(CheckBox chBoxLabel) {
		this.chBoxLabel = chBoxLabel;
	}

	public ListBox getSizeLabel() {
		return sizeLabel;
	}

	public void setSizeLabel(ListBox sizeLabel) {
		this.sizeLabel = sizeLabel;
	}

	public ListBox getPosition() {
		return position;
	}

	public void setPosition(ListBox position) {
		this.position = position;
	}

	public VerticalPanel getColorCont() {
		return colorCont;
	}

	public void setColorCont(VerticalPanel colorCont) {
		this.colorCont = colorCont;
	}

	public TextField<String> getUnitSpace() {
		return unitSpace;
	}

	public void setUnitSpace(TextField<String> unitSpace) {
		this.unitSpace = unitSpace;
	}

	private FieldSet createSeries(){
		
		colorCont = new VerticalPanel();
		
		fieldSetSeries = new FieldSet();  
		fieldSetSeries.setHeading("Series");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
		layout.setLabelPad(4);
		fieldSetSeries.setLayout(layout);
		
		return fieldSetSeries;
	}
	
	private FieldSet createLegendLabels(){
		
		FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading("Legend");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
		layout.setLabelPad(4);
		fieldSet.setLayout(layout);
		
		HorizontalPanel hrLeg = new HorizontalPanel();
		hrLeg.setSpacing(10);
		
		HTML labViIn=new HTML("Visible");
		//labViIn.setWidth("70px");
		hrLeg.add(labViIn);
		chBoxLabel=new CheckBox();
		hrLeg.add(chBoxLabel);
		hrLeg.add(new HTML(BabelFish.print().size()));
		sizeLabel=new ListBox();
		sizeLabel.setWidth("50px");
		for (int z=6; z<31; z++){
			sizeLabel.addItem(String.valueOf(z));
		}
		hrLeg.add(sizeLabel);
		
		HTML labPos=new HTML("Position:");
		//labPos.setWidth("70px");
		hrLeg.add(labPos);
		position=new ListBox();
		position.setWidth("150px");
		position.addItem("Above","0");
		position.addItem("Below","1");
		position.addItem("Left","2");
		position.addItem("Right","3");
		hrLeg.add(position);
		
		fieldSet.add(hrLeg);
	
		return fieldSet;
	}
	
	
	
	public void addSerieThings(){
		
		HorizontalPanel hrUniSpace = new HorizontalPanel();
		hrUniSpace.setSpacing(5);
		unitSpace = new TextField<String>();
		unitSpace.setWidth("50px");
		unitSpace.setToolTip("between 0 and 100");
		hrUniSpace.add(new HTML("Unit spacing"));
		hrUniSpace.add(unitSpace);
		fieldSetSeries.add(hrUniSpace);
		
		HorizontalPanel hrDim = new HorizontalPanel();
		hrDim.setSpacing(5);
		hrDim.add(new HTML(BabelFish.print().disposition() + ": "));
		disposition = new ListBox();
		disposition.setWidth("110px");
		disposition.addItem("Side by Side");
		disposition.addItem("Stacked");
		hrDim.add(disposition);
		fieldSetSeries.add(hrDim);
		
	}
	
	public TabLegSer(){
		setText("Legend and Series");
		setScrollMode(Scroll.AUTO);
		ContentPanel forScroll = new ContentPanel();
		forScroll.setHeight(415);
		forScroll.setHeaderVisible(false);
		forScroll.setScrollMode(Scroll.AUTO);
		mainCont = new VerticalPanel();
		//mainCont.setHeight("480");
		mainCont.setSpacing(10);
		mainCont.add(createLegendLabels());
		mainCont.add(createSeries());
		forScroll.add(mainCont);
		
		add(forScroll);
	}

}
