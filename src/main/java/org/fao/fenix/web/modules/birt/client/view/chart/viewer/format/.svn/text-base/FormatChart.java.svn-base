package org.fao.fenix.web.modules.birt.client.view.chart.viewer.format;

import java.util.List;

import org.fao.fenix.web.modules.birt.client.control.chart.ChartViewerController;
import org.fao.fenix.web.modules.birt.client.utils.ColorPicker;
import org.fao.fenix.web.modules.birt.client.utils.ColorPickerCaller;
import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.birt.common.vo.FormatChartVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
//import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;

public class FormatChart extends FenixWindow {

	TabPanel panel;
	TabTitle tabTitle;
	TabAxes tabAxes;
	TabLegSer tabLegSer;
	TabChartArea tabChartArea;
	FormatChartVo formatChartVo = new FormatChartVo();
	Button ok;
	ChartViewer chartViewer;
	String chartType;
	
	public FormatChartVo getFormatChartVo() {
		return formatChartVo;
	}

	public TabChartArea getTabChartArea() {
		return tabChartArea;
	}

	public TabTitle getTabTitle() {
		return tabTitle;
	}

	public TabAxes getTabAxes() {
		return tabAxes;
	}

	public TabLegSer getTabLegSer() {
		return tabLegSer;
	}

	private int findIdByLabel(ListBox l,int value){
		for (int i=0; i<l.getItemCount(); i++){
			if (l.getItemText(i).equals(String.valueOf(value))){
				return i;
			}
		}
		
		return 0;
	}
	
	private void populateTabChartArea(){
		tabChartArea.getPlotAreaColor().setStyleAttribute("background", formatChartVo.getPlotAreaColor());
		tabChartArea.getChartAreaColor().setStyleAttribute("background", formatChartVo.getChartAreaColor());
		if (!chartType.equals("Line") && !chartType.equals("Scatter")){
			tabChartArea.addDimension();
			//System.out.println(formatChartVo.getDimension());
			if (formatChartVo.getDimension().equals("Two_Dimensional")) tabChartArea.getDimension().setSelectedIndex(0); 
			else if (formatChartVo.getDimension().equals("Two_Dimensional_With_Depth")) tabChartArea.getDimension().setSelectedIndex(1);
		}
	}
	
	private void populateTabTile(){
		if (formatChartVo.getTitle() != null) tabTitle.getTextTitle().setValue(formatChartVo.getTitle());
		tabTitle.getVisibleTitle().setChecked(formatChartVo.isTitleVisible());
		tabTitle.getSizeTitle().setSelectedIndex(findIdByLabel(tabTitle.getSizeTitle(),formatChartVo.getSizeTitle()));
		tabTitle.getColorTitle().setStyleAttribute("background", formatChartVo.getColorTitle());
		
		// setting the title style
		System.out.println("SETTINGS getTitleIsBold: " + formatChartVo.getTitleIsBold());
		System.out.println("SETTINGS getTitleIsItalic: " + formatChartVo.getTitleIsItalic());
		System.out.println("SETTINGS getTitleIsUnderline: " + formatChartVo.getTitleIsUnderline());
		System.out.println("SETTINGS getXtitleFont: " + formatChartVo.getxTitleFont());
		tabTitle.getStylePanel().getBold().setValue(formatChartVo.getTitleIsBold());
		tabTitle.getStylePanel().getItalic().setValue(formatChartVo.getTitleIsItalic());
		tabTitle.getStylePanel().getUnderline().setValue(formatChartVo.getTitleIsUnderline());
		tabTitle.getStylePanel().setFontStyle(formatChartVo.getxTitleFont());
	}
	
	private void populateTabAxes(){
		
		// style
		System.out.println("SETTINGS getxAxisLabelIsBold(): " + formatChartVo.getxAxisLabelIsBold());
		System.out.println("SETTINGS getxAxisLabelIsItalic(): " + formatChartVo.getxAxisLabelIsItalic());
		System.out.println("SETTINGS getxAxisLabelIsUnderline(): " + formatChartVo.getxAxisLabelIsUnderline());
		System.out.println("SETTINGS getxAxisTitleFont: " + formatChartVo.getxAxisTitleFont());

		
		tabAxes.getxAxisLabelStylePanel().getBold().setValue(formatChartVo.getxAxisLabelIsBold());
		tabAxes.getxAxisLabelStylePanel().getItalic().setValue(formatChartVo.getxAxisLabelIsItalic());
		tabAxes.getxAxisLabelStylePanel().getUnderline().setValue(formatChartVo.getxAxisLabelIsUnderline());
		tabAxes.getxAxisLabelStylePanel().setFontStyle(formatChartVo.getxAxisTitleFont());
		
		tabAxes.getTextXAxisTitle().setValue(formatChartVo.getXAxisTitle());
		tabAxes.getVisibleXAxisTitle().setChecked(formatChartVo.isXAxisVisible());
		tabAxes.getSizeXAxisTitle().setSelectedIndex(findIdByLabel(tabAxes.getSizeXAxisTitle(), formatChartVo.getSizeXAxis()));
		tabAxes.getColorXAxisTitle().setStyleAttribute("background", formatChartVo.getXAxisTitleColor());
		tabAxes.getVisibleXAxisLabel().setChecked(formatChartVo.isXAxisLabelVisible());
		tabAxes.getSizeXAxisLabel().setSelectedIndex(findIdByLabel(tabAxes.getSizeXAxisLabel(), formatChartVo.getSizeXAxisLabel()));
		tabAxes.getColorXAxisLabel().setStyleAttribute("background", formatChartVo.getXAxisLabelColor());
		
		System.out.println("SETTINGS getxAxisIsBold(): " + formatChartVo.getxAxisIsBold());
		System.out.println("SETTINGS getxAxisIsItalic(): " + formatChartVo.getxAxisIsItalic());
		System.out.println("SETTINGS getxAxisIsUnderline(): " + formatChartVo.getxAxisIsUnderline());
		System.out.println("SETTINGS getxLabelFont: " + formatChartVo.getxLabelFont());
		
		tabAxes.getxAxisStylePanel().getBold().setValue(formatChartVo.getxAxisIsBold());
		tabAxes.getxAxisStylePanel().getItalic().setValue(formatChartVo.getxAxisIsItalic());
		tabAxes.getxAxisStylePanel().getUnderline().setValue(formatChartVo.getxAxisIsUnderline());
		tabAxes.getxAxisStylePanel().setFontStyle(formatChartVo.getxLabelFont());
		
		
		tabAxes.getRotateXLabels().setValue(formatChartVo.getxAxixRotation());
		
		tabAxes.getTextYAxisTitle().setValue(formatChartVo.getYAxisTitle());
		tabAxes.getVisibleYAxisTitle().setChecked(formatChartVo.isYAxisVisible());
		tabAxes.getSizeYAxisTitle().setSelectedIndex(findIdByLabel(tabAxes.getSizeYAxisTitle(), formatChartVo.getSizeYAxis()));
		tabAxes.getColorYAxisTitle().setStyleAttribute("background", formatChartVo.getYAxisTitleColor());
		tabAxes.getVisibleYAxisLabel().setChecked(formatChartVo.isYAxisLabelVisible());
		tabAxes.getSizeYAxisLabel().setSelectedIndex(findIdByLabel(tabAxes.getSizeYAxisLabel(), formatChartVo.getSizeYAxisLabel()));
		tabAxes.getColorYAxisLabel().setStyleAttribute("background", formatChartVo.getYAxisLabelColor());
		
		
		System.out.println("SETTINGS getyTitleLabelIsBold(): " + formatChartVo.getyTitleLabelIsBold());
		System.out.println("SETTINGS getyTitleIsItalic(): " + formatChartVo.getyTitleIsItalic());
		System.out.println("SETTINGS getyTitleIsUnderline(): " + formatChartVo.getyTitleIsUnderline());
		System.out.println("SETTINGS getyTitleFont: " + formatChartVo.getyTitleFont());

		
		tabAxes.getyAxisTitleStylePanel().getBold().setValue(formatChartVo.getyTitleLabelIsBold());
		tabAxes.getyAxisTitleStylePanel().getItalic().setValue(formatChartVo.getyTitleIsItalic());
		tabAxes.getyAxisTitleStylePanel().getUnderline().setValue(formatChartVo.getyTitleIsUnderline());
		tabAxes.getyAxisTitleStylePanel().setFontStyle(formatChartVo.getyTitleFont());

		
		System.out.println("SETTINGS getyAxisLabelIsBold(): " + formatChartVo.getyAxisLabelIsBold());
		System.out.println("SETTINGS getyAxisLabelIsItalic(): " + formatChartVo.getyAxisLabelIsItalic());
		System.out.println("SETTINGS getyAxisLabelIsUnderline(): " + formatChartVo.getyAxisLabelIsUnderline());
		System.out.println("SETTINGS getyLabelFont: " + formatChartVo.getyLabelFont());

		
		tabAxes.getyAxisStylePanel().getBold().setValue(formatChartVo.getyAxisLabelIsBold());
		tabAxes.getyAxisStylePanel().getItalic().setValue(formatChartVo.getyAxisLabelIsItalic());
		tabAxes.getyAxisStylePanel().getUnderline().setValue(formatChartVo.getyAxisLabelIsUnderline());
		tabAxes.getyAxisStylePanel().setFontStyle(formatChartVo.getyLabelFont());

		
		if (formatChartVo.isAutoScale()){
			tabAxes.getAutoStep().setChecked(true);
			tabAxes.getStepNumber().setEnabled(false);
		} else {
			tabAxes.getNumberStep().setChecked(true);
			tabAxes.getStepNumber().setEnabled(true);
		}
		
		if (formatChartVo.getStepNumber().equals("0")) tabAxes.getStepNumber().setValue("1");
		else tabAxes.getStepNumber().setValue(formatChartVo.getStepNumber());	
		
		if (formatChartVo.getFractionDigits() == null) tabAxes.getFractionDigits().setValue("0");
		else tabAxes.getFractionDigits().setValue(formatChartVo.getFractionDigits());
		
		tabAxes.getMinScale().setValue(formatChartVo.getMin());
		tabAxes.getMaxScale().setValue(formatChartVo.getMax());
		
		if (formatChartVo.isSecondScaleIsThere()){
			tabAxes.addWidgetsForSecondScale();
			
			tabAxes.getTextY2AxisTitle().setValue(formatChartVo.getYAxisTitleScale2());
			tabAxes.getVisibleY2AxisTitle().setChecked(formatChartVo.isYAxisVisibleScale2());
			tabAxes.getSizeY2AxisTitle().setSelectedIndex(findIdByLabel(tabAxes.getSizeY2AxisTitle(), formatChartVo.getSizeYAxisScale2()));
			tabAxes.getColorY2AxisTitle().setStyleAttribute("background", formatChartVo.getYAxisTitleColorScale2());
			tabAxes.getVisibleY2AxisLabel().setChecked(formatChartVo.isYAxisLabelVisibleScale2());
			tabAxes.getSizeY2AxisLabel().setSelectedIndex(findIdByLabel(tabAxes.getSizeY2AxisLabel(), formatChartVo.getSizeYAxisLabelScale2()));
			tabAxes.getColorY2AxisLabel().setStyleAttribute("background", formatChartVo.getYAxisLabelColorScale2());
			
			if (formatChartVo.isAutoScaleScale2()){
				tabAxes.getAutoStep2().setChecked(true);
				tabAxes.getStepNumber2().setEnabled(false);
			} else {
				tabAxes.getNumberStep2().setChecked(true);
				tabAxes.getStepNumber2().setEnabled(true);
			}
			
			if (formatChartVo.getStepNumberScale2().equals("0")) tabAxes.getStepNumber2().setValue("1");
			else tabAxes.getStepNumber2().setValue(formatChartVo.getStepNumberScale2());	
			
			if (formatChartVo.getFractionDigitsScale2() == null) tabAxes.getFractionDigits2().setValue("0");
			else tabAxes.getFractionDigits2().setValue(formatChartVo.getFractionDigitsScale2());
			
			tabAxes.getMinScale2().setValue(formatChartVo.getMinScale2());
			tabAxes.getMaxScale2().setValue(formatChartVo.getMaxScale2());
			
		}
		
		
		
	}
	
	private void populateTabLegSer(){
		
		tabLegSer.getChBoxLabel().setChecked(formatChartVo.isVisible());
		tabLegSer.getSizeLabel().setSelectedIndex(findIdByLabel(tabLegSer.getSizeLabel(), formatChartVo.getSizeLabel()));
		tabLegSer.getPosition().setSelectedIndex(Integer.valueOf(formatChartVo.getPosition()).intValue());
		
		if (!chartType.equals("Line") && !chartType.equals("Area") && !chartType.equals("Pie") && !chartType.equals("Scatter")){
			tabLegSer.addSerieThings();
			tabLegSer.getUnitSpace().setValue(formatChartVo.getUnitSpacing());
			if (formatChartVo.getDisposition().equals("Stacked")) tabLegSer.getDisposition().setSelectedIndex(1);
			else if (formatChartVo.getDisposition().equals("Side by Side")) tabLegSer.getDisposition().setSelectedIndex(0);
		}
		
		if (!chartType.equals("Pie")){
			tabAxes.addFlip();
			tabAxes.getFlip().setChecked(formatChartVo.isFlip());
			tabAxes.getShowValue().setChecked(formatChartVo.isShowSeriesValues());
		}
		
		tabLegSer.getFieldSetSeries().add(tabLegSer.getColorCont());
		//unitSpace.setValue(String.valueOf(result.getUnitSpacing()));
		List<List<String>> listColor=formatChartVo.getColorAndLabelFromClientToServer();
		
		HorizontalPanel hrLineColor;
		IconButton launchColorPicker=null;
		TextField<String> labLeg;
		for (int i=0; i<listColor.size(); i++){
			hrLineColor=new HorizontalPanel();
			hrLineColor.setSpacing(5);
			labLeg = new TextField<String>();
			labLeg.setValue(listColor.get(i).get(0));
			labLeg.setWidth("230px");
			hrLineColor.add(labLeg);
			launchColorPicker = new IconButton();
			launchColorPicker.setSize(45, 30);
			launchColorPicker.setStyleAttribute("border", "1px solid black");
			launchColorPicker.setStyleAttribute("background", listColor.get(i).get(1));
			launchColorPicker.addSelectionListener(new SelectionListener<IconButtonEvent>(){
				
				public void componentSelected(IconButtonEvent ce){
					new ColorPicker(ce.getTarget(), ce.getTarget().getAbsoluteLeft(), ce.getTarget().getAbsoluteTop()).build(ColorPickerCaller.GENERIC);
				}
				
			});
			hrLineColor.add(launchColorPicker);
			
			tabLegSer.getColorCont().add(hrLineColor);
			
		}
		
	}
	
	private void buildInterface(){
		System.out.println("buildInterface()");
	
		setTitle(BabelFish.print().formatChart());
		setSize(598, 500);
		
		panel=new TabPanel();
//		panel.setAutoHeight(true);
//		panel.setAutoWidth(true);
//		panel.setPlain(true);
//		panel.setBodyBorder(false);
		
		tabLegSer = new TabLegSer();
		panel.add(tabLegSer, "Chart Legend");
		tabTitle = new TabTitle();
		panel.add(tabTitle, "Chart Title");
		tabAxes = new TabAxes();
		panel.add(tabAxes, "Chart Axis");
		tabChartArea = new TabChartArea();
		panel.add(tabChartArea, "Chart Area");
		panel.selectTab(0);
		
		ok = new Button(BabelFish.print().ok());
		
		setCenterProperties();
		getCenter().add(panel);
		getCenter().setBottomComponent(ok);
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		
	}
	
	public FormatChart(String rptDesign, String chartType, HTML content){
		System.out.println("FormatChart(String rptDesign, String chartType, HTML content)");
		
		buildInterface();
		
		this.chartType = chartType;
		
		ok.addSelectionListener(ChartViewerController.setChartStyle(rptDesign, chartType, content, this));
		
		final LoadingWindow loading = new LoadingWindow();
		loading.create();
		AsyncCallback<FormatChartVo> callback = new AsyncCallback<FormatChartVo>(){
			
			public void onSuccess(FormatChartVo result){
				
				formatChartVo = result;
				
				//Grid
				tabAxes.getXGrid().setChecked(formatChartVo.isXGrid());
				tabAxes.getYGrid().setChecked(formatChartVo.isYGrid());
				
				populateTabTile();
				populateTabAxes();
				populateTabLegSer();
				populateTabChartArea();
				
				show();
				
				loading.destroy();
				
			}
			
			public void onFailure(Throwable caught){
				Info.display("getChartInfo", caught.getLocalizedMessage());
				loading.destroy();
			}
			
		};
		
		BirtServiceEntry.getInstance().getChartInfo(rptDesign, formatChartVo, callback);
		
	}
	
	public FormatChart(ChartViewer chartViewer){
		
		System.out.println("FormatChart(ChartViewer chartViewer)");
		
		buildInterface();
		
		this.chartViewer = chartViewer;
		
		this.chartType = this.chartViewer.getChartWizardBean().getChartType();
		
		ok.addSelectionListener(ChartViewerController.setChartStyle(chartViewer, this));
		
//		final LoadingWindow loading = new LoadingWindow();
//		loading.create();
		AsyncCallback<FormatChartVo> callback = new AsyncCallback<FormatChartVo>(){
			
			public void onSuccess(FormatChartVo result){
				System.out.println("onSuccess(FormatChartVo result){");
				
				formatChartVo = result;
				
				//Grid
				tabAxes.getXGrid().setChecked(formatChartVo.isXGrid());
				tabAxes.getYGrid().setChecked(formatChartVo.isYGrid());
				
				populateTabTile();
				populateTabAxes();
				populateTabLegSer();
				populateTabChartArea();
				
				show();
				
//				loading.destroy();
				
			}
			
			public void onFailure(Throwable caught){
				Info.display("getChartInfo", caught.getLocalizedMessage());
//				loading.destroy();
			}
			
		};
		
		BirtServiceEntry.getInstance().getChartInfo(chartViewer.getRptdesign(), formatChartVo, callback);
			
	}
	
}
