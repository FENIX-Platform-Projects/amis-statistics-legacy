package org.fao.fenix.web.modules.birt.client.view.chart.viewer.format;

import org.fao.fenix.web.modules.birt.client.utils.ColorPicker;
import org.fao.fenix.web.modules.birt.client.utils.ColorPickerCaller;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;


public class TabChartArea extends TabItem {
	
	ContentPanel mainCont;
	IconButton plotAreaColor;
	IconButton chartAreaColor;
	ListBox dimension;
	FieldSet fieldSet;
	
	public ListBox getDimension() {
		return dimension;
	}

	public IconButton getPlotAreaColor() {
		return plotAreaColor;
	}

	public IconButton getChartAreaColor() {
		return chartAreaColor;
	}

	public void addDimension(){
		
		ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		HorizontalPanel hrDim = new HorizontalPanel();
		hrDim.setSpacing(5);
		hrDim.add(new HTML(BabelFish.print().dimension() + ": "));
		dimension = new ListBox();
		dimension.setWidth("130px");
		dimension.addItem("2D","Two_Dimensional");
		dimension.addItem("2D with depth","Two_Dimensional_With_Depth");
		hrDim.add(dimension);
		
		panel.add(hrDim);
		add(panel);
		
	}
	
	private FieldSet createColorPart(){
		
		fieldSet = new FieldSet();  
		fieldSet.setHeading("Color Area");
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
		layout.setLabelPad(4);
		fieldSet.setLayout(layout);
	
		HorizontalPanel hrPlotArea = new HorizontalPanel();
		hrPlotArea.setSpacing(5);
		hrPlotArea.add(new HTML("Plot Area&nbsp;&nbsp;&nbsp;"));
		plotAreaColor = new IconButton();
		plotAreaColor.setSize(60, 10);
		plotAreaColor.setStyleAttribute("border", "1px solid black");
		plotAreaColor.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			
			public void componentSelected(IconButtonEvent ce){
				new ColorPicker(ce.getTarget(), ce.getTarget().getAbsoluteLeft(), ce.getTarget().getAbsoluteTop()).build(ColorPickerCaller.GENERIC);
			}
			
		});
		hrPlotArea.add(plotAreaColor);
		fieldSet.add(hrPlotArea);
		
		HorizontalPanel hrChartArea = new HorizontalPanel();
		hrChartArea.setSpacing(5);
		hrChartArea.add(new HTML("Chart Area"));
		chartAreaColor = new IconButton();
		chartAreaColor.setSize(60, 10);
		chartAreaColor.setStyleAttribute("border", "1px solid black");
		chartAreaColor.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			
			public void componentSelected(IconButtonEvent ce){
				new ColorPicker(ce.getTarget(), ce.getTarget().getAbsoluteLeft(), ce.getTarget().getAbsoluteTop()).build(ColorPickerCaller.GENERIC);
			}
			
		});
		hrChartArea.add(chartAreaColor);
		fieldSet.add(hrChartArea);		
		
		
		return fieldSet;
	}
	
	public TabChartArea(){
		
		setText("Chart Area");
		mainCont = new ContentPanel();
		mainCont.setHeaderVisible(false);
		mainCont.setBodyBorder(false);
		VerticalPanel v = new VerticalPanel();
		
		v.setSpacing(10);
		
		
		
		v.add(createColorPart());
		mainCont.add(v);
		
		add(mainCont);
	}

}
