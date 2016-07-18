package org.fao.fenix.web.modules.fsatmis.client.view;

import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class FSATMISFormatCharts extends FenixWindow {

	
	
	private TextField<String> xsize;

	private TextField<String> ysize;
	
	private ListBox legendPosition;
	
	private ContentPanel panel;
	
	private Button ok;
	

	
	private int firstWidthInt = 100;

	public FSATMISFormatCharts() {
		panel = new ContentPanel();
		FormLayout layout = new FormLayout();   
	    layout.setLabelWidth(firstWidthInt);  
	    layout.setLabelAlign(LabelAlign.LEFT);  
	    layout.setLabelSeparator("");
	    panel.setFrame(false);
	    panel.setHeaderVisible(false);
	    panel.setBodyBorder(false);
	    layout.setLabelPad(10);   
	    xsize = new TextField<String>();
	    ysize = new TextField<String>();
	    legendPosition = new ListBox();
	    panel.setLayout(layout); 
	    panel.setWidth(300);
		ok = new Button("Change Sizes");	
	}
	
	public void build(ContentPanel chartPanel, String x, String y) {
		buildCenterPanel(chartPanel, x, y);
		format();
		show();	
	}
	
	private void buildCenterPanel(ContentPanel chartPanel, String x, String y) {
		
		setCenterProperties();
		getCenter().setHeaderVisible(false);
		VerticalPanel v = new VerticalPanel();
		v.setSpacing(10);	
		xsize.setValue(x);
		ysize.setValue(y);
		
		HorizontalPanel h = new HorizontalPanel();
		HTML label = new HTML("<b>Set Width: </b>");
		label.setWidth("80px");
		h.add(label);
		h.add(xsize);
		v.add(h);
		
		h = new HorizontalPanel();
		label = new HTML("<b>Set Height: </b>");
		label.setWidth("80px");
		h.add(label);
		h.add(ysize);
		v.add(h);
		
//		h = new HorizontalPanel();
//		label = new HTML("<b>Legend Position: </b>");
//		label.setWidth("80px");
//		h.add(label);
//		legendPosition.addItem("Top", "0");
//		legendPosition.addItem("Right", "1");
//		h.add(legendPosition);
//		v.add(h);
		
		
		
		
		
		panel.add(v);
		panel.setBottomComponent(ok);	
		getCenter().add(panel);
		
		addCenterPartToWindow();
		enhance(chartPanel);
	}

	
	private void enhance(ContentPanel chartPanel){
		ok.addSelectionListener(OfcChartController.resizeChartsToCustom(this, chartPanel));
	}

	
	private void format() {
		setSize("300px", "180px");
		getWindow().setHeading("Resize the Charts");
		setCollapsible(true);
		getWindow().setResizable(true);
	}

	public TextField<String> getXsize() {
		return xsize;
	}

	public void setXsize(TextField<String> xsize) {
		this.xsize = xsize;
	}

	public TextField<String> getYsize() {
		return ysize;
	}

	public void setYsize(TextField<String> ysize) {
		this.ysize = ysize;
	}
	
	





	
	
	
}