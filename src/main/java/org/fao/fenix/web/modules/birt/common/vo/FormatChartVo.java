package org.fao.fenix.web.modules.birt.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FormatChartVo implements IsSerializable{
	
	
	// ########### color ##############
	String plotAreaColor;
	String chartAreaColor;
	String dimension;
	
	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getChartAreaColor() {
		return chartAreaColor;
	}

	public void setChartAreaColor(String chartAreaColor) {
		this.chartAreaColor = chartAreaColor;
	}

	public String getPlotAreaColor() {
		return plotAreaColor;
	}

	public void setPlotAreaColor(String plotAreaColor) {
		this.plotAreaColor = plotAreaColor;
	}
	
	// ############ TITLE #################
	
	private String title;
	private boolean titleVisible;
	private Integer sizeTitle;
	private String colorTitle;
	private String xTitleFont = "Arial";
	private Boolean titleIsBold = true;
	private Boolean titleIsItalic = false;
	private Boolean titleIsUnderline = false;
	
	
	
	public String getColorTitle() {
		return colorTitle;
	}

	public void setColorTitle(String colorTitle) {
		this.colorTitle = colorTitle;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean isTitleVisible() {
		return titleVisible;
	}
	
	public void setTitleVisible(boolean titleVisible) {
		this.titleVisible = titleVisible;
	}
	
	public Integer getSizeTitle() {
		return sizeTitle;
	}
	
	public void setSizeTitle(Integer sizeTitle) {
		this.sizeTitle = sizeTitle;
	}
	
	// ############## X Axis ########################
	
	private String xAxisTitle;
	private boolean xAxisVisible;
	private Integer sizeXAxis;
	private String xAxisTitleColor;
	private boolean xAxisLabelVisible;
	private Integer sizeXAxisLabel;
	private String xAxisLabelColor;
	private boolean xGrid;
	private String xAxisTitleFont = "Arial";
	private String xLabelFont = "Arial";
	private String xAxixRotation = "45";
	
	private Boolean xAxisIsBold = false;
	private Boolean xAxisIsItalic = false;
	private Boolean xAxisIsUnderline = false;
	
	private Boolean xAxisLabelIsBold = false;
	private Boolean xAxisLabelIsItalic = false;
	private Boolean xAxisLabelIsUnderline = false;
	
	public boolean isXGrid() {
		return xGrid;
	}

	public void setXGrid(boolean grid) {
		xGrid = grid;
	}

	public String getXAxisTitleColor() {
		return xAxisTitleColor;
	}

	public void setXAxisTitleColor(String axisTitleColor) {
		xAxisTitleColor = axisTitleColor;
	}

	public String getXAxisLabelColor() {
		return xAxisLabelColor;
	}

	public void setXAxisLabelColor(String axisLabelColor) {
		xAxisLabelColor = axisLabelColor;
	}

	public String getXAxisTitle() {
		return xAxisTitle;
	}

	public void setXAxisTitle(String axisTitle) {
		xAxisTitle = axisTitle;
	}

	public boolean isXAxisVisible() {
		return xAxisVisible;
	}

	public void setXAxisVisible(boolean axisVisible) {
		xAxisVisible = axisVisible;
	}

	public boolean isXAxisLabelVisible() {
		return xAxisLabelVisible;
	}

	public void setXAxisLabelVisible(boolean axisLabelVisible) {
		xAxisLabelVisible = axisLabelVisible;
	}

	public Integer getSizeXAxisLabel() {
		return sizeXAxisLabel;
	}

	public void setSizeXAxisLabel(Integer sizeXAxisLabel) {
		this.sizeXAxisLabel = sizeXAxisLabel;
	}

	public Integer getSizeXAxis() {
		return sizeXAxis;
	}

	public void setSizeXAxis(Integer sizeXAxis) {
		this.sizeXAxis = sizeXAxis;
	}
	
	public Boolean getxAxisLabelIsBold() {
		return xAxisLabelIsBold;
	}

	public void setxAxisLabelIsBold(Boolean xAxisLabelIsBold) {
		this.xAxisLabelIsBold = xAxisLabelIsBold;
	}

	public Boolean getxAxisLabelIsItalic() {
		return xAxisLabelIsItalic;
	}
	
	
	public Boolean getxAxisIsBold() {
		return xAxisIsBold;
	}

	public void setxAxisIsBold(Boolean xAxisIsBold) {
		this.xAxisIsBold = xAxisIsBold;
	}

	public Boolean getxAxisIsItalic() {
		return xAxisIsItalic;
	}
	
	public void setxAxisIsItalic(Boolean xAxisIsItalic) {
		this.xAxisIsItalic = xAxisIsItalic;
	}

	public Boolean getxAxisIsUnderline() {
		return xAxisIsUnderline;
	}

	public void setxAxisIsUnderline(Boolean xAxisIsUnderline) {
		this.xAxisIsUnderline = xAxisIsUnderline;
	}
	
	
	public void setxAxisLabelIsItalic(Boolean xAxisLabelIsItalic) {
		this.xAxisLabelIsItalic = xAxisLabelIsItalic;
	}

	public Boolean getxAxisLabelIsUnderline() {
		return xAxisLabelIsUnderline;
	}

	public void setxAxisLabelIsUnderline(Boolean xAxisLabelIsUnderline) {
		this.xAxisLabelIsUnderline = xAxisLabelIsUnderline;
	}
	
	// ############## Y Axis ########################

	private String yAxisTitle;
	private boolean yAxisVisible;
	private boolean yAxisLabelVisible;
	private String yAxisTitleColor;
	private Integer sizeYAxisLabel;
	private Integer sizeYAxis;
	private String yAxisLabelColor;
	private boolean yGrid;
	private boolean showSeriesValues;
	private String yTitleFont = "Arial";
	private String yLabelFont = "Arial";
	
	private Boolean yAxisLabelIsBold = false;
	private Boolean yAxisLabelIsItalic = false;
	private Boolean yAxisLabelIsUnderline = false;
	
	private String min;
	private String max;
	private String stepNumber;
	private String fractionDigits;
	private boolean autoScale;
	
	
	private Boolean yTitleLabelIsBold = true;
	private Boolean yTitleIsItalic = false;
	private Boolean yTitleIsUnderline = false;
	

	public boolean isShowSeriesValues() {
		return showSeriesValues;
	}

	public void setShowSeriesValues(boolean showSeriesValues) {
		this.showSeriesValues = showSeriesValues;
	}

	public boolean isYGrid() {
		return yGrid;
	}

	public void setYGrid(boolean grid) {
		yGrid = grid;
	}

	public String getYAxisTitleColor() {
		return yAxisTitleColor;
	}

	public void setYAxisTitleColor(String axisTitleColor) {
		yAxisTitleColor = axisTitleColor;
	}

	public String getYAxisLabelColor() {
		return yAxisLabelColor;
	}

	public void setYAxisLabelColor(String axisLabelColor) {
		yAxisLabelColor = axisLabelColor;
	}

	public String getYAxisTitle() {
		return yAxisTitle;
	}

	public void setYAxisTitle(String axisTitle) {
		yAxisTitle = axisTitle;
	}

	public boolean isYAxisVisible() {
		return yAxisVisible;
	}

	public void setYAxisVisible(boolean axisVisible) {
		yAxisVisible = axisVisible;
	}

	public boolean isYAxisLabelVisible() {
		return yAxisLabelVisible;
	}

	public void setYAxisLabelVisible(boolean axisLabelVisible) {
		yAxisLabelVisible = axisLabelVisible;
	}

	public Integer getSizeYAxisLabel() {
		return sizeYAxisLabel;
	}

	public void setSizeYAxisLabel(Integer sizeYAxisLabel) {
		this.sizeYAxisLabel = sizeYAxisLabel;
	}

	public Integer getSizeYAxis() {
		return sizeYAxis;
	}

	public void setSizeYAxis(Integer sizeYAxis) {
		this.sizeYAxis = sizeYAxis;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(String stepNumber) {
		this.stepNumber = stepNumber;
	}

	public String getFractionDigits() {
		return fractionDigits;
	}

	public void setFractionDigits(String fractionDigits) {
		this.fractionDigits = fractionDigits;
	}

	public boolean isAutoScale() {
		return autoScale;
	}

	public void setAutoScale(boolean autoScale) {
		this.autoScale = autoScale;
	}
	
	
	
	
	public Boolean getyAxisLabelIsBold() {
		return yAxisLabelIsBold;
	}

	public void setyAxisLabelIsBold(Boolean yAxisLabelIsBold) {
		this.yAxisLabelIsBold = yAxisLabelIsBold;
	}

	public Boolean getyAxisLabelIsItalic() {
		return yAxisLabelIsItalic;
	}

	public void setyAxisLabelIsItalic(Boolean yAxisLabelIsItalic) {
		this.yAxisLabelIsItalic = yAxisLabelIsItalic;
	}

	public Boolean getyAxisLabelIsUnderline() {
		return yAxisLabelIsUnderline;
	}

	public void setyAxisLabelIsUnderline(Boolean yAxisLabelIsUnderline) {
		this.yAxisLabelIsUnderline = yAxisLabelIsUnderline;
	}

	// ############## Y Axis 2 ########################
	private String yAxisTitleScale2;
	private boolean yAxisVisibleScale2;
	private Integer sizeYAxisScale2;
	private String yAxisTitleColorScale2;
	private boolean yAxisLabelVisibleScale2;
	private Integer sizeYAxisLabelScale2;
	private String yAxisLabelColorScale2;
		
	private boolean secondScaleIsThere;
	private String minScale2;
	private String maxScale2;
	private String stepNumberScale2;
	private String fractionDigitsScale2;
	private boolean autoScaleScale2;
	
	private String yTitle2Font = "Arial";
	private String yAxis2Font = "Arial";
	
	
	private Boolean yAxis2TitleIsBold = false;
	private Boolean yAxis2TitleIsItalic = false;
	private Boolean yAxis2TitleIsUnderline = false;
	
	private Boolean yAxis2LabelIsBold = false;
	private Boolean yAxis2LabelIsItalic = false;
	private Boolean yAxis2LabelIsUnderline = false;
	
		
	public String getYAxisTitleColorScale2() {
		return yAxisTitleColorScale2;
	}

	public void setYAxisTitleColorScale2(String axisTitleColorScale2) {
		yAxisTitleColorScale2 = axisTitleColorScale2;
	}

	public String getYAxisLabelColorScale2() {
		return yAxisLabelColorScale2;
	}

	public void setYAxisLabelColorScale2(String axisLabelColorScale2) {
		yAxisLabelColorScale2 = axisLabelColorScale2;
	}

	public String getYAxisTitleScale2() {
		return yAxisTitleScale2;
	}

	public void setYAxisTitleScale2(String axisTitleScale2) {
		yAxisTitleScale2 = axisTitleScale2;
	}

	public boolean isYAxisVisibleScale2() {
		return yAxisVisibleScale2;
	}

	public void setYAxisVisibleScale2(boolean axisVisibleScale2) {
		yAxisVisibleScale2 = axisVisibleScale2;
	}

	public boolean isYAxisLabelVisibleScale2() {
		return yAxisLabelVisibleScale2;
	}

	public void setYAxisLabelVisibleScale2(boolean axisLabelVisibleScale2) {
		yAxisLabelVisibleScale2 = axisLabelVisibleScale2;
	}

	public Integer getSizeYAxisLabelScale2() {
		return sizeYAxisLabelScale2;
	}

	public void setSizeYAxisLabelScale2(Integer sizeYAxisLabelScale2) {
		this.sizeYAxisLabelScale2 = sizeYAxisLabelScale2;
	}

	public Integer getSizeYAxisScale2() {
		return sizeYAxisScale2;
	}

	public void setSizeYAxisScale2(Integer sizeYAxisScale2) {
		this.sizeYAxisScale2 = sizeYAxisScale2;
	}

	public String getMinScale2() {
		return minScale2;
	}

	public void setMinScale2(String minScale2) {
		this.minScale2 = minScale2;
	}

	public String getMaxScale2() {
		return maxScale2;
	}

	public void setMaxScale2(String maxScale2) {
		this.maxScale2 = maxScale2;
	}

	public String getStepNumberScale2() {
		return stepNumberScale2;
	}

	public void setStepNumberScale2(String stepNumberScale2) {
		this.stepNumberScale2 = stepNumberScale2;
	}

	public String getFractionDigitsScale2() {
		return fractionDigitsScale2;
	}

	public void setFractionDigitsScale2(String fractionDigitsScale2) {
		this.fractionDigitsScale2 = fractionDigitsScale2;
	}

	public boolean isAutoScaleScale2() {
		return autoScaleScale2;
	}

	public void setAutoScaleScale2(boolean autoScaleScale2) {
		this.autoScaleScale2 = autoScaleScale2;
	}

	public boolean isSecondScaleIsThere() {
		return secondScaleIsThere;
	}

	public void setSecondScaleIsThere(boolean secondScaleIsThere) {
		this.secondScaleIsThere = secondScaleIsThere;
	}
	
	
	public Boolean getyAxis2LabelIsBold() {
		return yAxis2LabelIsBold;
	}

	public void setyAxis2LabelIsBold(Boolean yAxis2LabelIsBold) {
		this.yAxis2LabelIsBold = yAxis2LabelIsBold;
	}

	public Boolean getyAxis2LabelIsItalic() {
		return yAxis2LabelIsItalic;
	}

	public void setyAxis2LabelIsItalic(Boolean yAxis2LabelIsItalic) {
		this.yAxis2LabelIsItalic = yAxis2LabelIsItalic;
	}

	public Boolean getyAxis2LabelIsUnderline() {
		return yAxis2LabelIsUnderline;
	}

	public void setyAxis2LabelIsUnderline(Boolean yAxis2LabelIsUnderline) {
		this.yAxis2LabelIsUnderline = yAxis2LabelIsUnderline;
	}

	// ############## Legend / Series ########################
	private boolean visible;
	private Integer sizeLabel;
	private String position;
	private String unitSpacing;
	private String disposition;
	private String legendFont;
	private boolean flip;
	private Double legendHeight = new Double(20);
	private Double legendWidth = new Double(50);
//	private Boolean legendIsBold = true;
//	private Boolean legendIsItalic = false;
//	private Boolean legendIsUnderline = false;
		
	/*
	 * #0 label #1 color
	 * 
	 */
	private List<List<String>> colorAndLabelFromClientToServer = new ArrayList<List<String>>();

	public boolean isFlip() {
		return flip;
	}

	public void setFlip(boolean flip) {
		this.flip = flip;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Integer getSizeLabel() {
		return sizeLabel;
	}

	public void setSizeLabel(Integer sizeLabel) {
		this.sizeLabel = sizeLabel;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUnitSpacing() {
		return unitSpacing;
	}

	public void setUnitSpacing(String unitSpacing) {
		this.unitSpacing = unitSpacing;
	}

	public List<List<String>> getColorAndLabelFromClientToServer() {
		return colorAndLabelFromClientToServer;
	}

	public void addSetColorAndLabelFromClientToServer(List<String> colorAndLabelFromClientToServer) {
		this.colorAndLabelFromClientToServer.add(colorAndLabelFromClientToServer);
	}
	
	public void setColorAndLabelFromClientToServer(
			List<List<String>> colorAndLabelFromClientToServer) {
		this.colorAndLabelFromClientToServer = colorAndLabelFromClientToServer;
	}

	public String getxTitleFont() {
		return xTitleFont;
	}

	public void setxTitleFont(String xTitleFont) {
		this.xTitleFont = xTitleFont;
	}

	public String getxLabelFont() {
		return xLabelFont;
	}

	public void setxLabelFont(String xLabelFont) {
		this.xLabelFont = xLabelFont;
	}

	public String getyAxisTitle() {
		return yAxisTitle;
	}

	public void setyAxisTitle(String yAxisTitle) {
		this.yAxisTitle = yAxisTitle;
	}

	public String getyLabelFont() {
		return yLabelFont;
	}

	public void setyLabelFont(String yLabelFont) {
		this.yLabelFont = yLabelFont;
	}

	public String getLegendFont() {
		return legendFont;
	}

	public void setLegendFont(String legendFont) {
		this.legendFont = legendFont;
	}

	public Double getLegendHeight() {
		return legendHeight;
	}

	public void setLegendHeight(Double legendHeight) {
		this.legendHeight = legendHeight;
	}

	public Double getLegendWidth() {
		return legendWidth;
	}

	public void setLegendWidth(Double legendWidth) {
		this.legendWidth = legendWidth;
	}

	public Boolean getTitleIsBold() {
		return titleIsBold;
	}

	public void setTitleIsBold(Boolean titleIsBold) {
		this.titleIsBold = titleIsBold;
	}

	public Boolean getTitleIsItalic() {
		return titleIsItalic;
	}

	public void setTitleIsItalic(Boolean titleIsItalic) {
		this.titleIsItalic = titleIsItalic;
	}

	public Boolean getTitleIsUnderline() {
		return titleIsUnderline;
	}

	public void setTitleIsUnderline(Boolean titleIsUnderline) {
		this.titleIsUnderline = titleIsUnderline;
	}

//	public Boolean getLegendIsBold() {
//		return legendIsBold;
//	}
//
//	public void setLegendIsBold(Boolean legendIsBold) {
//		this.legendIsBold = legendIsBold;
//	}
//
//	public Boolean getLegendIsItalic() {
//		return legendIsItalic;
//	}
//
//	public void setLegendIsItalic(Boolean legendIsItalic) {
//		this.legendIsItalic = legendIsItalic;
//	}
//
//	public Boolean getLegendIsUnderline() {
//		return legendIsUnderline;
//	}
//
//	public void setLegendIsUnderline(Boolean legendIsUnderline) {
//		this.legendIsUnderline = legendIsUnderline;
//	}

	public String getxAxixRotation() {
		return xAxixRotation;
	}

	public void setxAxixRotation(String xAxixRotation) {
		this.xAxixRotation = xAxixRotation;
	}

	public Boolean getyTitleLabelIsBold() {
		return yTitleLabelIsBold;
	}

	public void setyTitleLabelIsBold(Boolean yTitleLabelIsBold) {
		this.yTitleLabelIsBold = yTitleLabelIsBold;
	}

	public Boolean getyTitleIsItalic() {
		return yTitleIsItalic;
	}

	public void setyTitleIsItalic(Boolean yTitleIsItalic) {
		this.yTitleIsItalic = yTitleIsItalic;
	}

	public Boolean getyTitleIsUnderline() {
		return yTitleIsUnderline;
	}

	public void setyTitleIsUnderline(Boolean yTitleIsUnderline) {
		this.yTitleIsUnderline = yTitleIsUnderline;
	}

	public Boolean getyAxis2TitleIsBold() {
		return yAxis2TitleIsBold;
	}

	public void setyAxis2TitleIsBold(Boolean yAxis2TitleIsBold) {
		this.yAxis2TitleIsBold = yAxis2TitleIsBold;
	}

	public Boolean getyAxis2TitleIsItalic() {
		return yAxis2TitleIsItalic;
	}

	public void setyAxis2TitleIsItalic(Boolean yAxis2TitleIsItalic) {
		this.yAxis2TitleIsItalic = yAxis2TitleIsItalic;
	}

	public Boolean getyAxis2TitleIsUnderline() {
		return yAxis2TitleIsUnderline;
	}

	public void setyAxis2TitleIsUnderline(Boolean yAxis2TitleIsUnderline) {
		this.yAxis2TitleIsUnderline = yAxis2TitleIsUnderline;
	}

	public String getxAxisTitleFont() {
		return xAxisTitleFont;
	}

	public void setxAxisTitleFont(String xAxisTitleFont) {
		this.xAxisTitleFont = xAxisTitleFont;
	}

	public String getyTitleFont() {
		return yTitleFont;
	}

	public void setyTitleFont(String yTitleFont) {
		this.yTitleFont = yTitleFont;
	}

	public String getyTitle2Font() {
		return yTitle2Font;
	}

	public void setyTitle2Font(String yTitle2Font) {
		this.yTitle2Font = yTitle2Font;
	}

	public String getyAxis2Font() {
		return yAxis2Font;
	}

	public void setyAxis2Font(String yAxis2Font) {
		this.yAxis2Font = yAxis2Font;
	}

	
	
	
	
}
