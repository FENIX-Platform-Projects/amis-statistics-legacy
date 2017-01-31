package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class LookAndFeelPanel extends ChartDesignerStepPanel {

	private HorizontalPanel wrapper;
	
	private final static int SPACING = 3;
	
	private TextField<String> title;
	
	private TextField<String> subTitle;
	
	private ListBox titleFontFamily;
	
	private ListBox subTitleFontFamily;
	
	private ListBox titleFontSize;
	
	private ListBox subTitleFontSize;
	
	private HTML titleFontColor;
	
	private HTML subTitleFontColor;
	
	private TextField<String> xAxisTitle;
	
	private ListBox xAxisTitleFontFamily;
	
	private ListBox xLimFactor;
	
	private ListBox xAxisTitleFontSize;
	
	private ListBox showLegend;
	
	private HTML xAxisTitleFontColor;
	
	private ListBox xLabelInclination;
	
	private HTML xAxisColor;
	
	private HTML xAxisTicksColor;
	
	private TextField<String> xAxisNumberOfIntervals;
	
	private TextField<String> legendTitle;
	
	private ListBox legendFontFamily;
	
	private ListBox legendFontSize;
	
	private ListBox legendPosition;
	
	private HTML legendFontColor;
	
	private ListBox gridLineType;
	
	private HTML gridColor;
	
	private CheckBox hasXGrid;
	
	private CheckBox hasYGrid;
	
	private CheckBox hasXandYGrid;
	
	private FieldSet seriesFieldSet;
	
	private final static String FIELD_WIDTH = "180px";
	
	private final static String FIELD_SET_WIDTH = "200px";
		
	private final static String WRAPPER_HEIGHT = "370px";
	
	private final static String TITLE = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";
	
	private VerticalPanel secondColumnPanel;
	
	private VerticalPanel thirdColumnPanel;
	
	private Map<String, String> seriesMap;
	
	private TextField<String> imageWidth;
	
	private TextField<String> imageHeight;
	
	private Map<Long, AxisFormatPanel> axisFormatPanelsMap;
	
	private Map<Long, SeriesFormatPanel> seriesFormatPanelsMap;
	
	private Map<String, HighlightPanel> highlightPanelsMap;
	
	private HTML xValuesColor;
	
	private ListBox xValuesFontSize;
	
	private ListBox xValuesFontFamily;
	
	public LookAndFeelPanel(String suggestion, String width) {
		super(suggestion, width);
		wrapper = new HorizontalPanel();
		title = new TextField<String>();
		subTitle = new TextField<String>();
		titleFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		xValuesColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		subTitleFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		titleFontFamily = new ListBox();
		subTitleFontFamily = new ListBox();
		xValuesFontSize = new ListBox();
		xValuesFontFamily = new ListBox();
		ChartDesignerController.fillFontFamilyList(titleFontFamily);
		ChartDesignerController.fillFontFamilyList(xValuesFontFamily);
		ChartDesignerController.fillFontFamilyList(subTitleFontFamily);
		titleFontSize = new ListBox();
		subTitleFontSize = new ListBox();
		ChartDesignerController.fillFontSizeList(titleFontSize);
		ChartDesignerController.fillFontSizeList(xValuesFontSize);
		xValuesFontSize.setSelectedIndex(7);
		titleFontSize.setSelectedIndex(20);
		ChartDesignerController.fillFontSizeList(subTitleFontSize);
		subTitleFontSize.setSelectedIndex(10);
		xAxisTitle = new TextField<String>();
		xAxisTitleFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		xAxisTitleFontFamily = new ListBox();
		ChartDesignerController.fillFontFamilyList(xAxisTitleFontFamily);
		xAxisTitleFontSize = new ListBox();
		ChartDesignerController.fillFontSizeList(xAxisTitleFontSize);
		xAxisTitleFontSize.setSelectedIndex(10);
		xLabelInclination = new ListBox();
		showLegend = new ListBox();
		xAxisColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		xAxisTicksColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		legendTitle = new TextField<String>();
		legendFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		legendFontSize = new ListBox();
		ChartDesignerController.fillFontSizeList(legendFontSize);
		legendFontSize.setSelectedIndex(7);
		legendFontFamily = new ListBox();
		ChartDesignerController.fillFontFamilyList(legendFontFamily);
		hasXGrid = new CheckBox();
		hasYGrid = new CheckBox();
		hasXandYGrid = new CheckBox();
		hasXandYGrid.addListener(Events.OnClick, ChartDesignerController.enableBothAxis(hasXandYGrid, hasXGrid, hasYGrid));
		seriesFieldSet = new FieldSet();
		thirdColumnPanel = new VerticalPanel();
		thirdColumnPanel.setLayout(new FillLayout());
		secondColumnPanel = new VerticalPanel();
		secondColumnPanel.setLayout(new FillLayout());
		seriesMap = new HashMap<String, String>();
		imageHeight = new TextField<String>();
		imageWidth = new TextField<String>();
		legendPosition = new ListBox();
		gridLineType = new ListBox();
		xLimFactor = new ListBox();
		gridColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #BEBEBE; color: #BEBEBE; font-weight: bold; font-style: italic;'>#BEBEBE</div>");
		xAxisNumberOfIntervals = new TextField<String>();
		axisFormatPanelsMap = new HashMap<Long, AxisFormatPanel>();
		seriesFormatPanelsMap = new HashMap<Long, SeriesFormatPanel>();
		highlightPanelsMap = new HashMap<String, HighlightPanel>();
//		this.getLayoutContainer().add(buildWrapperPanel());
		this.getLayoutContainer().add(buildTabPanel());
	}
	
	private TabPanel buildTabPanel() {
		TabPanel tp = new TabPanel();
		tp.setHeight(WRAPPER_HEIGHT);
		tp.add(new TabItem("General Settings"));
		tp.add(new TabItem("[BAR] - FPMU Prices"));
		tp.add(new TabItem("[LINE] - Atmospheric Data"));
		return tp;
	}
	
	// AS IS
	
	private HorizontalPanel buildWrapperPanel() {
		wrapper.setSpacing(SPACING);
		wrapper.add(buildFirstColumnPanel());
		wrapper.add(buildSecondColumnPanel());
		wrapper.add(buildThirdColumnPanel());
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setHeight(WRAPPER_HEIGHT);
		return wrapper;
	}
	
	private VerticalPanel buildFirstColumnPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.add(buildTitlePanel());
		p.add(buildLegendPanel());
		p.add(buildImageSizePanel());
		p.add(buildGridPanel());
		return p;
	}
	
	private VerticalPanel buildSecondColumnPanel() {
//		VerticalPanel p = new VerticalPanel();
		secondColumnPanel.setSpacing(SPACING);
		secondColumnPanel.add(buildXAxisPanel());
		return secondColumnPanel;
	}
	
	private VerticalPanel buildThirdColumnPanel() {
//		thirdColumnPanel = new VerticalPanel();
		thirdColumnPanel.setSpacing(SPACING);
		return thirdColumnPanel;
	}
	
	public void addAxisFormatPanel(Long datasetID, String datasetTitle) {
		if (!axisFormatPanelsMap.containsKey(datasetID)) {
			AxisFormatPanel afp = new AxisFormatPanel(datasetID, datasetTitle);
			thirdColumnPanel.add(afp.build());
//			thirdColumnPanel.getLayout().layout();
			axisFormatPanelsMap.put(datasetID, afp);
		}
	}
	
	private FieldSet buildTitlePanel() {
		FieldSet fs = new FieldSet();
		fs.setCheckboxToggle(true);
		fs.setHeading("Title");
		fs.setWidth(FIELD_SET_WIDTH);
		fs.setBorders(true);
		fs.collapse();
		Html titleLabel = new Html(TITLE + "<div align='center' style='text-decoration: underline; font-size: 10pt'>" + BabelFish.print().title() + "</div></div>");
		titleLabel.setWidth(FIELD_WIDTH);
		Html titleFontColorLabel = new Html(TITLE + "Title Color</div>");
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		Html titleFontFamilyLabel = new Html(TITLE + "Title Font Style</div>");
		titleFontFamilyLabel.setWidth(FIELD_WIDTH);
		Html titleFontSizeLabel = new Html(TITLE + "Title Font Size</div>");
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		Html subTitleLabel = new Html(TITLE + "Sub Title</div>");
		subTitleLabel.setWidth(FIELD_WIDTH);
		Html subTitleFontColorLabel = new Html(TITLE + "Sub Title Color</div>");
		subTitleFontColorLabel.setWidth(FIELD_WIDTH);
		Html subTitleFontFamilyLabel = new Html(TITLE + "Sub Title Font Style</div>");
		subTitleFontFamilyLabel.setWidth(FIELD_WIDTH);
		Html subTitleFontSizeLabel = new Html(TITLE + "Sub Title Font Size</div>");
		subTitleFontSizeLabel.setWidth(FIELD_WIDTH);
		title.setWidth(FIELD_WIDTH);
		title.setValue("Title");
		subTitle.setWidth(FIELD_WIDTH);
		subTitle.setValue("Sub-Title");
		titleFontColor.setWidth(FIELD_WIDTH);
		titleFontColor.addClickHandler(ChartDesignerController.colorPicker(titleFontColor));
		titleFontFamily.setWidth(FIELD_WIDTH);
		titleFontSize.setWidth(FIELD_WIDTH);
		subTitleFontColor.setWidth(FIELD_WIDTH);
		subTitleFontColor.addClickHandler(ChartDesignerController.colorPicker(subTitleFontColor));
		subTitleFontFamily.setWidth(FIELD_WIDTH);
		subTitleFontSize.setWidth(FIELD_WIDTH);
		fs.add(title);
		fs.add(titleFontColorLabel);
		fs.add(titleFontColor);
		fs.add(titleFontFamilyLabel);
		fs.add(titleFontFamily);
		fs.add(titleFontSizeLabel);
		fs.add(titleFontSize);
		fs.add(subTitleLabel);
		fs.add(subTitle);
		fs.add(subTitleFontColorLabel);
		fs.add(subTitleFontColor);
		fs.add(subTitleFontFamilyLabel);
		fs.add(subTitleFontFamily);
		fs.add(subTitleFontSizeLabel);
		fs.add(subTitleFontSize);
		return fs;
	}
	
	private FieldSet buildXAxisPanel() {
		FieldSet fs = new FieldSet();
		fs.setCheckboxToggle(true);
		fs.setHeading("X-Axis");
		fs.setWidth(FIELD_SET_WIDTH);
		fs.setBorders(true);
		fs.collapse();
		Html titleFontColorLabel = new Html(TITLE + "Axis Label Font Color</div>");
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		Html axisColorLabel = new Html(TITLE + "Axis Color</div>");
		axisColorLabel.setWidth(FIELD_WIDTH);
		Html axisTicksColorLabel = new Html(TITLE + "Axis Ticks Color</div>");
		axisTicksColorLabel.setWidth(FIELD_WIDTH);
		Html titleFontFamilyLabel = new Html(TITLE + "Axis Label Font Style</div>");
		titleFontFamilyLabel.setWidth(FIELD_WIDTH);
		Html titleFontSizeLabel = new Html(TITLE + "Axis Label Font Size</div>");
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		Html labelInclinationLabel = new Html(TITLE + "Label Orientation</div>");
		labelInclinationLabel.setWidth(FIELD_WIDTH);
		Html numberOfIntervalsLabel = new Html(TITLE + "Step</div>");
		numberOfIntervalsLabel.setWidth(FIELD_WIDTH);
		Html xLimValueLabel = new Html(TITLE + "Axis Limit</div>");
		xLimValueLabel.setWidth(FIELD_WIDTH);
		Html valuesFontFamilyLabel = new Html(TITLE + "Values Font Style</div>");
		valuesFontFamilyLabel.setWidth(FIELD_WIDTH);
		Html valuesFontSizeLabel = new Html(TITLE + "Values Font Size</div>");
		valuesFontSizeLabel.setWidth(FIELD_WIDTH);
		Html valuesFontColorLabel = new Html(TITLE + "Values Font Color</div>");
		valuesFontColorLabel.setWidth(FIELD_WIDTH);
		xAxisTitle.setWidth(FIELD_WIDTH);
		xAxisTitle.setValue("X-Axis");
		xAxisTitleFontColor.setWidth(FIELD_WIDTH);
		xAxisTitleFontColor.addClickHandler(ChartDesignerController.colorPicker(xAxisTitleFontColor));
		xAxisColor.setWidth(FIELD_WIDTH);
		xAxisColor.addClickHandler(ChartDesignerController.colorPicker(xAxisColor));
		xAxisTicksColor.setWidth(FIELD_WIDTH);
		xAxisTicksColor.addClickHandler(ChartDesignerController.colorPicker(xAxisTicksColor));
		xValuesColor.addClickHandler(ChartDesignerController.colorPicker(xValuesColor));
		xValuesColor.setWidth(FIELD_WIDTH);
		xValuesFontFamily.setWidth(FIELD_WIDTH);
		xValuesFontSize.setWidth(FIELD_WIDTH);
		xAxisTitleFontFamily.setWidth(FIELD_WIDTH);
		xAxisTitleFontSize.setWidth(FIELD_WIDTH);
		xLabelInclination.setWidth(FIELD_WIDTH);
		xLabelInclination.addItem("0", "0");
		xLabelInclination.addItem("30", "30");
		xLabelInclination.addItem("45", "45");
		xLabelInclination.addItem("60", "60");
		xLabelInclination.addItem("90", "90");
		xLabelInclination.setSelectedIndex(2);
		xAxisNumberOfIntervals.setWidth(FIELD_WIDTH);
		xAxisNumberOfIntervals.setValue("0");
		fs.add(xAxisTitle);
		fs.add(numberOfIntervalsLabel);
		fs.add(xAxisNumberOfIntervals);
//		fs.add(axisColorLabel);
//		fs.add(xAxisColor);
		fs.add(axisTicksColorLabel);
		fs.add(xAxisTicksColor);
		fs.add(titleFontColorLabel);
		fs.add(xAxisTitleFontColor);
		fs.add(titleFontFamilyLabel);
		fs.add(xAxisTitleFontFamily);
		fs.add(titleFontSizeLabel);
		fs.add(xAxisTitleFontSize);
		fs.add(valuesFontColorLabel);
		fs.add(xValuesColor);
		fs.add(valuesFontSizeLabel);
		fs.add(xValuesFontSize);
		fs.add(valuesFontFamilyLabel);
		fs.add(xValuesFontFamily);
		fs.add(labelInclinationLabel);
		fs.add(xLabelInclination);
		return fs;
	}
	
	private FieldSet buildLegendPanel() {
		FieldSet fs = new FieldSet();
		fs.setCheckboxToggle(true);
		fs.setHeading("Legend");
		fs.setWidth(FIELD_SET_WIDTH);
		fs.setBorders(true);
		fs.collapse();
		Html legendLabel = new Html(TITLE + "Legend Label</div>");
		legendLabel.setWidth(FIELD_WIDTH);
		Html titleFontColorLabel = new Html(TITLE + "Background Color</div>");
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		Html titleFontFamilyLabel = new Html(TITLE + "Font</div>");
		titleFontFamilyLabel.setWidth(FIELD_WIDTH);
		Html titleFontSizeLabel = new Html(TITLE + "Size</div>");
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		Html legendPositionLabel = new Html(TITLE + "Position</div>");
		legendPositionLabel.setWidth(FIELD_WIDTH);
		Html showLegendLabel = new Html(TITLE + "Show Legend</div>");
		showLegendLabel.setWidth(FIELD_WIDTH);
		legendTitle.setWidth(FIELD_WIDTH);
		legendTitle.setValue("Legend");
		legendFontColor.setWidth(FIELD_WIDTH);
		legendFontColor.addClickHandler(ChartDesignerController.colorPicker(legendFontColor));
		legendFontFamily.setWidth(FIELD_WIDTH);
		legendFontSize.setWidth(FIELD_WIDTH);
		legendPosition.setWidth(FIELD_WIDTH);
		legendPosition.addItem("Top", "top");
		legendPosition.addItem("Top Left", "topleft");
		legendPosition.addItem("Top Right", "topright");
		showLegend.setWidth(FIELD_WIDTH);
		showLegend.addItem("Show Legend", "true");
		showLegend.addItem("Do Not Show Legend", "false");
		fs.add(showLegendLabel);
		fs.add(showLegend);
		fs.add(legendLabel);
		fs.add(legendTitle);
		fs.add(titleFontColorLabel);
		fs.add(legendFontColor);
		fs.add(titleFontFamilyLabel);
		fs.add(legendFontFamily);
		fs.add(titleFontSizeLabel);
		fs.add(legendFontSize);
		fs.add(legendPositionLabel);
		fs.add(legendPosition);
		fs.setBorders(true);
		return fs;
	}
	
	public void addSeries(Long datasetID, String datasetTitle, Map<String, String> headerColorMap) {
		if (!seriesFormatPanelsMap.containsKey(datasetID)) {
			SeriesFormatPanel sfp = new SeriesFormatPanel(datasetID, datasetTitle);
			thirdColumnPanel.add(sfp.build(headerColorMap));
			thirdColumnPanel.getLayout().layout();
			seriesFormatPanelsMap.put(datasetID, sfp);
		} else {
			SeriesFormatPanel sfp = seriesFormatPanelsMap.get(datasetID);
			thirdColumnPanel.remove(sfp.getFs());
			thirdColumnPanel.getLayout().layout();
			thirdColumnPanel.add(sfp.build(headerColorMap));
			thirdColumnPanel.getLayout().layout();
			seriesFormatPanelsMap.put(datasetID, sfp);
		}
	}
	
	public void addHighlight(Long datasetID, String datasetTitle, String highlightType) {
		String key = highlightType + ":" + datasetID;
		if (!highlightPanelsMap.containsKey(key)) {
			HighlightPanel hp = new HighlightPanel(datasetID, datasetTitle, highlightType);
			secondColumnPanel.add(hp.build(BabelFish.print().getString(highlightType)));
			secondColumnPanel.getLayout().layout();
			highlightPanelsMap.put(key, hp);
		} else {
			HighlightPanel hp = highlightPanelsMap.get(key);
			secondColumnPanel.remove(hp.getFs());
			secondColumnPanel.getLayout().layout();
			secondColumnPanel.add(hp.build(BabelFish.print().getString(highlightType)));
			secondColumnPanel.getLayout().layout();
			highlightPanelsMap.put(key, hp);
		}
	}
	
	private FieldSet buildGridPanel() {
		FieldSet fs = new FieldSet();
		fs.setCheckboxToggle(true);
		fs.setHeading("Grids");
		fs.setWidth(FIELD_SET_WIDTH);
		fs.setBorders(true);
		fs.collapse();
		hasXandYGrid.setBoxLabel("Show X and Y Grid");
		hasXGrid.setBoxLabel("Show X Grid");
		hasYGrid.setBoxLabel("Show Y Grid");
		Html gridColorLabel = new Html(TITLE + "Grid Color</div>");
		gridColorLabel.setWidth(FIELD_WIDTH);
		gridColor.setWidth(FIELD_WIDTH);
		gridColor.addClickHandler(ChartDesignerController.colorPicker(gridColor));
		Html gridLineTypeLabel = new Html(TITLE + "Line Type</div>");
		gridLineTypeLabel.setWidth(FIELD_WIDTH);
		gridLineType.setWidth(FIELD_WIDTH);
		gridLineType.addItem("Solid", "1");
		gridLineType.addItem("Dashed", "2");
		gridLineType.addItem("Dotted", "3");
		gridLineType.addItem("Dot Dash", "4");
		gridLineType.addItem("Long Dash", "5");
		gridLineType.addItem("Two Dash", "6");
		fs.add(hasXandYGrid);
		fs.add(hasXGrid);
		fs.add(hasYGrid);
		fs.add(gridColorLabel);
		fs.add(gridColor);
		fs.add(gridLineTypeLabel);
		fs.add(gridLineType);
		return fs;
	}
	
	private FieldSet buildImageSizePanel() {
		FieldSet fs = new FieldSet();
		fs.setCheckboxToggle(true);
		fs.setHeading("Image Size");
		fs.setWidth(FIELD_SET_WIDTH);
		fs.setBorders(true);
		fs.collapse();
		Html imageWidthLabel = new Html(TITLE + "Width [px]</div>");
		imageWidthLabel.setWidth(FIELD_WIDTH);
		Html imageHeightLabel = new Html(TITLE + "Height [px]</div>");
		imageHeightLabel.setWidth(FIELD_WIDTH);
		imageWidth.setWidth(FIELD_WIDTH);
		imageWidth.setValue("615");
		imageHeight.setWidth(FIELD_WIDTH);
		imageHeight.setValue("290");
		fs.add(imageWidthLabel);
		fs.add(imageWidth);
		fs.add(imageHeightLabel);
		fs.add(imageHeight);
		return fs;
	}

	public HorizontalPanel getWrapper() {
		return wrapper;
	}

	public TextField<String> getTitle() {
		return title;
	}

	public ListBox getTitleFontFamily() {
		return titleFontFamily;
	}

	public ListBox getTitleFontSize() {
		return titleFontSize;
	}

	public HTML getTitleFontColor() {
		return titleFontColor;
	}

	public TextField<String> getxAxisTitle() {
		return xAxisTitle;
	}

	public ListBox getxAxisTitleFontFamily() {
		return xAxisTitleFontFamily;
	}

	public ListBox getxAxisTitleFontSize() {
		return xAxisTitleFontSize;
	}

	public HTML getxAxisTitleFontColor() {
		return xAxisTitleFontColor;
	}

	public ListBox getxLabelInclination() {
		return xLabelInclination;
	}

	public TextField<String> getLegendTitle() {
		return legendTitle;
	}

	public ListBox getLegendFontFamily() {
		return legendFontFamily;
	}

	public ListBox getLegendFontSize() {
		return legendFontSize;
	}

	public HTML getLegendFontColor() {
		return legendFontColor;
	}

	public CheckBox getHasXGrid() {
		return hasXGrid;
	}

	public CheckBox getHasYGrid() {
		return hasYGrid;
	}

	public CheckBox getHasXandYGrid() {
		return hasXandYGrid;
	}

	public Map<String, String> getSeriesMap() {
		return seriesMap;
	}

	public TextField<String> getImageWidth() {
		return imageWidth;
	}

	public TextField<String> getImageHeight() {
		return imageHeight;
	}

	public ListBox getLegendPosition() {
		return legendPosition;
	}

	public ListBox getGridLineType() {
		return gridLineType;
	}

	public HTML getGridColor() {
		return gridColor;
	}

	public TextField<String> getxAxisNumberOfIntervals() {
		return xAxisNumberOfIntervals;
	}

	public HTML getxAxisColor() {
		return xAxisColor;
	}

	public HTML getxAxisTicksColor() {
		return xAxisTicksColor;
	}

	public ListBox getShowLegend() {
		return showLegend;
	}

	public TextField<String> getSubTitle() {
		return subTitle;
	}

	public ListBox getxLimFactor() {
		return xLimFactor;
	}
	
	public FieldSet getSeriesFieldSet() {
		return seriesFieldSet;
	}

	public Map<Long, AxisFormatPanel> getAxisFormatPanelsMap() {
		return axisFormatPanelsMap;
	}

	public Map<Long, SeriesFormatPanel> getSeriesFormatPanelsMap() {
		return seriesFormatPanelsMap;
	}

	public Map<String, HighlightPanel> getHighlightPanelsMap() {
		return highlightPanelsMap;
	}

	public ListBox getSubTitleFontFamily() {
		return subTitleFontFamily;
	}

	public ListBox getSubTitleFontSize() {
		return subTitleFontSize;
	}

	public HTML getSubTitleFontColor() {
		return subTitleFontColor;
	}

	public HTML getxValuesColor() {
		return xValuesColor;
	}

	public ListBox getxValuesFontSize() {
		return xValuesFontSize;
	}

	public ListBox getxValuesFontFamily() {
		return xValuesFontFamily;
	}
	
}