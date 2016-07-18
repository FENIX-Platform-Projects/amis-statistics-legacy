package org.fao.fenix.web.modules.ofcchart.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class OfcFormatChartPanel {
	
	private ContentPanel mainCont;
	private VerticalPanel cont;
	private CheckBox showLegend;
	private ListBox listPos;
	private ListBox dim;
	private ListBox disposition;
	private CheckBox flip;
	private TextBox chartTitleBox;
	private TextBox widthChart;
	private TextBox heightChart;
	private TextBox xAxisTitle;
	private TextBox yAxisTitle;
	private CheckBox showXLabels;
	private CheckBox showYLabels;
	private TextBox rotateXLabels;
	private Button preview;
	private Button back;
	
	public ContentPanel build() {
	
		mainCont = new ContentPanel();
		mainCont.setHeaderVisible(false);
		

		cont = new VerticalPanel();
		cont.setSpacing(5);
				
		cont.add(setChartProperties());
		cont.add(new HTML("&nbsp;&nbsp;&nbsp;&nbsp;<hr>&nbsp;&nbsp;&nbsp;&nbsp;"));
		cont.add(setLegendProperties());
		cont.add(new HTML("&nbsp;&nbsp;&nbsp;&nbsp;<hr>&nbsp;&nbsp;&nbsp;&nbsp;"));
		cont.add(setXAxis());
		cont.add(new HTML("&nbsp;&nbsp;&nbsp;&nbsp;<hr>&nbsp;&nbsp;&nbsp;&nbsp;"));
		cont.add(setYAxis());
		mainCont.add(cont);
		return mainCont;
	}

	private VerticalPanel setYAxis(){
				
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		
		HorizontalPanel hrLabel = new HorizontalPanel();
		HTML label = new HTML(BabelFish.print().YAxisTile() + ": &nbsp;&nbsp;");
		label.setStyleName("simpleText");
		hrLabel.add(label);
		yAxisTitle = new TextBox();
		hrLabel.add(yAxisTitle);
		vp.add(hrLabel);
		
		HorizontalPanel hr = new HorizontalPanel();
		hr.setSpacing(5);
		showYLabels = new CheckBox();
		showYLabels.setChecked(true);
		hr.add(showYLabels);
		hr.add(new HTML(BabelFish.print().showLabels()));
		
		vp.add(hr);
		
		return vp;
	}
	
	private VerticalPanel setXAxis(){
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		
		HorizontalPanel hrLabel = new HorizontalPanel();
		HTML label = new HTML(BabelFish.print().XAxisTile() + ": &nbsp;&nbsp;");
		label.setStyleName("simpleText");
		hrLabel.add(label);
		xAxisTitle = new TextBox();
		hrLabel.add(xAxisTitle);
		vp.add(hrLabel);
		
		HorizontalPanel hr = new HorizontalPanel();
		hr.setSpacing(5);
		showXLabels = new CheckBox();
		showXLabels.setChecked(true);
		hr.add(showXLabels);
		hr.add(new HTML(BabelFish.print().showLabels()));
		
		hr.add(new HTML(BabelFish.print().rotateLabels() + ": "));
		rotateXLabels = new TextBox();
		rotateXLabels.setText("45");
		hr.add(rotateXLabels);
		vp.add(hr);
		
		return vp;
	}
	
	private VerticalPanel setLegendProperties(){
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		
		HTML label = new HTML(BabelFish.print().legend() + ": ");
		label.setStyleName("simpleText");
		vp.add(label);
		
		HorizontalPanel hr = new HorizontalPanel();
		hr.setSpacing(5);
		hr.add(new HTML(BabelFish.print().showLegend()));
		showLegend = new CheckBox();
		showLegend.setChecked(true);
		hr.add(showLegend);
		hr.add(new HTML(BabelFish.print().position()));
		listPos = new ListBox();
		listPos.setWidth("100px");
		listPos.addItem("Right");
		listPos.addItem("Above");
		hr.add(listPos);
		vp.add(hr);
		
		return vp;
	}
	
	private VerticalPanel setChartProperties(){
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(5);
		
		HorizontalPanel hrTitle = new HorizontalPanel();
		HTML chartTitleLabel = new HTML(BabelFish.print().chartTitle() + ": &nbsp;&nbsp;");
		chartTitleLabel.setStyleName("simpleText");
		hrTitle.add(chartTitleLabel);
		chartTitleBox = new TextBox();
		hrTitle.add(chartTitleBox);
		vp.add(hrTitle);
		
		HTML chartSizeLabel = new HTML(BabelFish.print().chartSize() + ": ");
		//vp.add(chartSizeLabel);
		
		HorizontalPanel hrSize = new HorizontalPanel();
		hrSize.setSpacing(5);
		hrSize.add(new HTML(BabelFish.print().width() + ": "));
		widthChart = new TextBox();
		widthChart.setText("445");
		hrSize.add(widthChart);
		
		hrSize.add(new HTML(BabelFish.print().height() + ": "));
		heightChart = new TextBox();
		heightChart.setText("258");
		hrSize.add(heightChart);
		
		//vp.add(hrSize);
		
		HorizontalPanel hrDim = new HorizontalPanel();
		hrDim.setSpacing(5);
		hrDim.add(new HTML(BabelFish.print().dimension() + ": "));
		dim = new ListBox();
		dim.setWidth("130px");
		dim.addItem("3D");
		dim.addItem("2D");
		hrDim.add(dim);
		
//		hrDim.add(new HTML(I18N.print().disposition() + ": "));
//		disposition = new ListBox();
//		disposition.setWidth("110px");
//		disposition.addItem("Side by Side");
//		disposition.addItem("Stacked");
//		hrDim.add(disposition);
		
//		hrDim.add(new HTML(I18N.print().flipAxis() + ": "));
//		flip = new CheckBox();
//		hrDim.add(flip);
		
		vp.add(hrDim);
		return vp;
	}
	

	
	
	public Button getPreview() {
		return preview;
	}

	public TextBox getXAxisTitle() {
		return xAxisTitle;
	}

	public TextBox getYAxisTitle() {
		return yAxisTitle;
	}
		
	public CheckBox getShowXLabels() {
		return showXLabels;
	}

	public CheckBox getShowYLabels() {
		return showYLabels;
	}

	public TextBox getRotateXLabels() {
		return rotateXLabels;
	}

	public TextBox getWidthChart() {
		return widthChart;
	}

	public TextBox getHeightChart() {
		return heightChart;
	}

	public TextBox getChartTitleBox() {
		return chartTitleBox;
	}

	public ListBox getDisposition() {
		return disposition;
	}

	public ListBox getDim() {
		return dim;
	}

	public CheckBox getFlip() {
		return flip;
	}

	public CheckBox getShowLegend() {
		return showLegend;
	}

	public ListBox getListPos() {
		return listPos;
	}
	
	
}