package org.fao.fenix.web.modules.bangladesh.server.birt;

import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.FormatChartVo;

public class SettingGraphStyle {

	private static Integer lineFontSize = 5;
	
	private static Integer barFontSize = 5;
	
	public static ChartWizardBean setInfoBarChart(String title, String yAxisTitle, Boolean stacked, String xLabelsRotate) {
		ChartWizardBean bean = new ChartWizardBean();
		bean.setTitle(title);
	
		bean.setYAxisTitle(yAxisTitle);
		bean.setChartType("Bar");
		if ( stacked )
			bean.setDisposition("Stacked");
		else
			bean.setDisposition("");
//		bean.setFlip(true);
//		bean.setChartType("Bar");
		bean.setXAxisTitle("");
		bean.setXAxisShowLabels(true);
		bean.setYAxisShowLabels(true);
		bean.setPosLegend("Below");
		bean.setShowLegend(true);
		bean.setDim2_3D("Two_Dimensional");
		bean.setRotateXLabels(xLabelsRotate);
		bean.setWidth("245");
		bean.setHeight("165");

		return bean;
	}
	
	public static FormatChartVo setInfoFormatBarChart(Boolean titleVisible) {
		FormatChartVo format = new FormatChartVo();
		/** title **/
		format.setSizeTitle(barFontSize);
		format.setTitleVisible(titleVisible);
		
		
		/** y **/
		format.setSizeYAxis(barFontSize);
		format.setSizeYAxisLabel(barFontSize);
		
		/** x **/
		format.setSizeXAxisLabel(barFontSize);
		
		/** legend **/
		format.setSizeLabel(barFontSize);
		
		format.setLegendHeight(new Double(10));
		format.setLegendWidth(new Double(50));
		
		return format;
	}
	
	
	
	public static ChartWizardBean setInfoLineChart(String title, String yAxisTitle) {
		ChartWizardBean bean = new ChartWizardBean();
		bean.setTitle(title);
	
		bean.setYAxisTitle(yAxisTitle);
		bean.setChartType("Line");
//		bean.setDisposition("Stacked");
//		bean.setFlip(true);
//		bean.setChartType("Bar");
		bean.setDisposition("");
		bean.setXAxisTitle("");
		bean.setXAxisShowLabels(true);
		bean.setYAxisShowLabels(true);
		bean.setPosLegend("Below");
		bean.setShowLegend(true);
		bean.setDim2_3D("Two_Dimensional");
		bean.setRotateXLabels("45");
		bean.setWidth("245");
		bean.setHeight("165");
		return bean;
	}
	
	public static FormatChartVo setInfoFormatLineChart() {
		FormatChartVo format = new FormatChartVo();
		/** title **/
		format.setSizeTitle(lineFontSize);
		
		/** y **/
		format.setSizeYAxis(lineFontSize);
		format.setSizeYAxisLabel(lineFontSize);
		
		/** x **/
		format.setSizeXAxisLabel(lineFontSize);
		
		/** legend **/
		format.setSizeLabel(lineFontSize);
		
		format.setLegendHeight(new Double(5));
		format.setLegendWidth(new Double(50));
		
		return format;
	}
	
	
	public static ChartWizardBean setInfoBarChartCNSA(String title, String yAxisTitle, Boolean stacked, String xLabelsRotate, String width, String height, String legendPosition) {
		ChartWizardBean bean = new ChartWizardBean();
		bean.setTitle(title);
	
		bean.setYAxisTitle(yAxisTitle);
		bean.setChartType("Bar");
		if ( stacked )
			bean.setDisposition("Stacked");
		else
			bean.setDisposition("");
//		bean.setFlip(true);
//		bean.setChartType("Bar");
		bean.setXAxisTitle("");
		bean.setXAxisShowLabels(true);
		bean.setYAxisShowLabels(true);
		bean.setPosLegend(legendPosition);
		bean.setShowLegend(true);
		bean.setDim2_3D("Two_Dimensional");
		bean.setRotateXLabels(xLabelsRotate);
		bean.setWidth(width);
		bean.setHeight(height);
		return bean;
	}
	
	public static FormatChartVo setInfoFormatBarChart(Boolean titleVisible, Integer titleFontSize, Integer axisFontSize, Integer legendFontSize ) {
		FormatChartVo format = new FormatChartVo();
		/** title **/
		format.setSizeTitle(titleFontSize);
		format.setTitleVisible(titleVisible);
		
		
		/** y **/
		format.setSizeYAxis(axisFontSize);
		format.setSizeYAxisLabel(axisFontSize);
		
		/** x **/
		format.setSizeXAxisLabel(axisFontSize);
		
		/** legend **/
		format.setSizeLabel(legendFontSize);
		
		format.setLegendHeight(new Double(10));
		format.setLegendWidth(new Double(50));
		
		return format;
	}
}