package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.util.List;

public class ChartStyle {
	
	/*
	 * 
	 * red_green_blue
	 * 
	 */
	private String backgroundPlotArea;
	private String backgroundChartArea;
	/*
	 * #0 serie name
	 * #1 color
	 */
	private List<List<String>> seriesColor;
	
	private double width;
	private double height;
	
	private String title;
	private int sizeTitle;
	private boolean visibleTitle = true;
	private String colorTitle;
	
	private String xAxisTitle;
	private boolean xAxisTitleVisible;
	private int xAxisTitleSize;
	private String colorXAxisTitle;
	
	private boolean xAxisLabelsVisible;
	private int xAxisSizeLabels;
	private String colorXAxisLabel;
	private boolean xGrid;
	
	//scale1
	private String yAxisTitle;
	private int yAxisTitleSize;
	private boolean yAxisTitleVisible;
	private String colorYAxisTitle;
		
	private boolean yAxisLabelsVisible;
	private int yAxisSizeLabels;
	private String colorYAxisLabel;
	private boolean yGrid;
	
	//scale2
	private String yAxisTitleScale2;
	private int yAxisTitleSizeScale2;
	private boolean yAxisTitleVisibleScale2;
	private String colorYAxisTitleScale2;
	
	private boolean yAxisLabelsVisibleScale2;
	private int yAxisSizeLabelsScale2;
	private String colorYAxisLabelScale2;
	
	private boolean valueVisible;
	private int valueSizeLabels;
	
	private String posLegend;
	private boolean showLegend; 
	private int sizeLegend;
	private double unitSpacing;
	private Double legendHeight = new Double(20);
	private Double legendWidth = new Double(50);
	
	//Scale1
	Double min;
	Double max;
	int stepNumber;
	boolean autoScale;
	int fractionDigits;
	
	//Scale2
	Double minScale2;
	Double maxScale2;
	int stepNumberScale2;
	boolean autoScale2;
	int fractionDigits2;
	
	private boolean flip;
	private String dim2_3D;
	private boolean stacked;
	
	public boolean isYGrid() {
		return yGrid;
	}

	public boolean isXGrid() {
		return xGrid;
	}

	public void setXGrid(boolean grid) {
		xGrid = grid;
	}

	public void setYGrid(boolean grid) {
		yGrid = grid;
	}

	public int getFractionDigits() {
		return fractionDigits;
	}

	public void setFractionDigits(int fractionDigits) {
		this.fractionDigits = fractionDigits;
	}

	public int getFractionDigits2() {
		return fractionDigits2;
	}

	public void setFractionDigits2(int fractionDigits2) {
		this.fractionDigits2 = fractionDigits2;
	}

	public String getColorYAxisTitleScale2() {
		return colorYAxisTitleScale2;
	}

	public void setColorYAxisTitleScale2(String colorYAxisTitleScale2) {
		this.colorYAxisTitleScale2 = colorYAxisTitleScale2;
	}

	public String getColorYAxisLabelScale2() {
		return colorYAxisLabelScale2;
	}

	public void setColorYAxisLabelScale2(String colorYAxisLabelScale2) {
		this.colorYAxisLabelScale2 = colorYAxisLabelScale2;
	}

	public String getColorYAxisTitle() {
		return colorYAxisTitle;
	}

	public void setColorYAxisTitle(String colorYAxisTitle) {
		this.colorYAxisTitle = colorYAxisTitle;
	}

	public String getColorYAxisLabel() {
		return colorYAxisLabel;
	}

	public void setColorYAxisLabel(String colorYAxisLabel) {
		this.colorYAxisLabel = colorYAxisLabel;
	}

	public String getColorXAxisTitle() {
		return colorXAxisTitle;
	}

	public void setColorXAxisTitle(String colorXAxisTitle) {
		this.colorXAxisTitle = colorXAxisTitle;
	}

	public String getColorXAxisLabel() {
		return colorXAxisLabel;
	}

	public void setColorXAxisLabel(String colorXAxisLabel) {
		this.colorXAxisLabel = colorXAxisLabel;
	}

	public String getColorTitle() {
		return colorTitle;
	}

	public void setColorTitle(String colorTitle) {
		this.colorTitle = colorTitle;
	}

	public String getYAxisTitleScale2() {
		return yAxisTitleScale2;
	}

	public void setYAxisTitleScale2(String axisTitleScale2) {
		yAxisTitleScale2 = axisTitleScale2;
	}

	public int getYAxisTitleSizeScale2() {
		return yAxisTitleSizeScale2;
	}

	public void setYAxisTitleSizeScale2(int axisTitleSizeScale2) {
		yAxisTitleSizeScale2 = axisTitleSizeScale2;
	}

	public boolean isYAxisTitleVisibleScale2() {
		return yAxisTitleVisibleScale2;
	}

	public void setYAxisTitleVisibleScale2(boolean axisTitleVisibleScale2) {
		yAxisTitleVisibleScale2 = axisTitleVisibleScale2;
	}

	public boolean isYAxisLabelsVisibleScale2() {
		return yAxisLabelsVisibleScale2;
	}

	public void setYAxisLabelsVisibleScale2(boolean axisLabelsVisibleScale2) {
		yAxisLabelsVisibleScale2 = axisLabelsVisibleScale2;
	}

	public int getYAxisSizeLabelsScale2() {
		return yAxisSizeLabelsScale2;
	}

	public void setYAxisSizeLabelsScale2(int axisSizeLabelsScale2) {
		yAxisSizeLabelsScale2 = axisSizeLabelsScale2;
	}

	public Double getMinScale2() {
		return minScale2;
	}

	public void setMinScale2(Double minScale2) {
		this.minScale2 = minScale2;
	}

	public Double getMaxScale2() {
		return maxScale2;
	}

	public void setMaxScale2(Double maxScale2) {
		this.maxScale2 = maxScale2;
	}

	public int getStepNumberScale2() {
		return stepNumberScale2;
	}

	public void setStepNumberScale2(int stepNumberScale2) {
		this.stepNumberScale2 = stepNumberScale2;
	}

	public boolean isAutoScale2() {
		return autoScale2;
	}

	public void setAutoScale2(boolean autoScale2) {
		this.autoScale2 = autoScale2;
	}
	
	
	public String getBackgroundPlotArea() {
		return backgroundPlotArea;
	}

	public void setBackgroundPlotArea(String backgroundPlotArea) {
		this.backgroundPlotArea = backgroundPlotArea;
	}

	public String getBackgroundChartArea() {
		return backgroundChartArea;
	}

	public void setBackgroundChartArea(String backgroundChartArea) {
		this.backgroundChartArea = backgroundChartArea;
	}

	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSizeTitle() {
		return sizeTitle;
	}
	public void setSizeTitle(int sizeTitle) {
		this.sizeTitle = sizeTitle;
	}
	public boolean isVisibleTitle() {
		return visibleTitle;
	}
	public void setVisibleTitle(boolean visibleTitle) {
		this.visibleTitle = visibleTitle;
	}
	public String getXAxisTitle() {
		return xAxisTitle;
	}
	public void setXAxisTitle(String axisTitle) {
		xAxisTitle = axisTitle;
	}
	public boolean isXAxisTitleVisible() {
		return xAxisTitleVisible;
	}
	public void setXAxisTitleVisible(boolean axisTitleVisible) {
		xAxisTitleVisible = axisTitleVisible;
	}
	public int getXAxisTitleSize() {
		return xAxisTitleSize;
	}
	public void setXAxisTitleSize(int axisTitleSize) {
		xAxisTitleSize = axisTitleSize;
	}
	public boolean isXAxisLabelsVisible() {
		return xAxisLabelsVisible;
	}
	public void setXAxisLabelsVisible(boolean axisLabelsVisible) {
		xAxisLabelsVisible = axisLabelsVisible;
	}
	public int getXAxisSizeLabels() {
		return xAxisSizeLabels;
	}
	public void setXAxisSizeLabels(int axisSizeLabels) {
		xAxisSizeLabels = axisSizeLabels;
	}
	public String getYAxisTitle() {
		return yAxisTitle;
	}
	public void setYAxisTitle(String axisTitle) {
		yAxisTitle = axisTitle;
	}
	public int getYAxisTitleSize() {
		return yAxisTitleSize;
	}
	public void setYAxisTitleSize(int axisTitleSize) {
		yAxisTitleSize = axisTitleSize;
	}
	public boolean isYAxisTitleVisible() {
		return yAxisTitleVisible;
	}
	public void setYAxisTitleVisible(boolean axisTitleVisible) {
		yAxisTitleVisible = axisTitleVisible;
	}
	public boolean isYAxisLabelsVisible() {
		return yAxisLabelsVisible;
	}
	public void setYAxisLabelsVisible(boolean axisLabelsVisible) {
		yAxisLabelsVisible = axisLabelsVisible;
	}
	public int getYAxisSizeLabels() {
		return yAxisSizeLabels;
	}
	public void setYAxisSizeLabels(int axisSizeLabels) {
		yAxisSizeLabels = axisSizeLabels;
	}
	public boolean isValueVisible() {
		return valueVisible;
	}
	public void setValueVisible(boolean valueVisible) {
		this.valueVisible = valueVisible;
	}
	public int getValueSizeLabels() {
		return valueSizeLabels;
	}
	public void setValueSizeLabels(int valueSizeLabels) {
		this.valueSizeLabels = valueSizeLabels;
	}
	public String getPosLegend() {
		return posLegend;
	}
	public void setPosLegend(String posLegend) {
		this.posLegend = posLegend;
	}
	public boolean isShowLegend() {
		return showLegend;
	}
	public void setShowLegend(boolean showLegend) {
		this.showLegend = showLegend;
	}
	
	public int getSizeLegend() {
		return sizeLegend;
	}
	
	
	public void setSizeLegend(int sizeLegend) {
		this.sizeLegend = sizeLegend;
	}

	public double getUnitSpacing() {
		return unitSpacing;
	}
	public void setUnitSpacing(double unitSpacing) {
		this.unitSpacing = unitSpacing;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public int getStepNumber() {
		return stepNumber;
	}
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}
	public boolean isAutoScale() {
		return autoScale;
	}
	public void setAutoScale(boolean autoScale) {
		this.autoScale = autoScale;
	}
	public boolean isFlip() {
		return flip;
	}
	public void setFlip(boolean flip) {
		this.flip = flip;
	}
	public String getDim2_3D() {
		return dim2_3D;
	}
	public void setDim2_3D(String dim2_3D) {
		this.dim2_3D = dim2_3D;
	}
	
	public boolean isStacked() {
		return stacked;
	}

	public void setStacked(boolean stacked) {
		this.stacked = stacked;
	}

	public List<List<String>> getSeriesColor() {
		return seriesColor;
	}

	public void setSeriesColor(List<List<String>> seriesColor) {
		this.seriesColor = seriesColor;
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
	
	
	
}
