package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class LookAndFeelTabItem {

	private HorizontalPanel wrapper;
	
	private VerticalPanel leftWrapper;
	
	private VerticalPanel rightWrapper;
	
	private TabItem tabItem;
	
	private int SPACING = 5;
	
	private String TITLE = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";
	
	private String HEIGHT = "440px";
	
	private String BUTTON_WIDTH = "125px";
	
	private String FIELD_WIDTH = "650px";
	
	private String WRAPPER_WIDTH = "680px";
	
	private String WRAPPER_HEIGHT = "420px";
	
	private String HIGHLIGHT_WRAPPER_HEIGHT = "250px";
	
	private String HIGHLIGHT_FIELD_WIDTH = "650px";
	
	private Button chartSizeButton;
	private Button borderButton;
	
	
	
	private Button legendButton;
	
	private Button subTitleButton;
	
	private Button titleButton;
	
	private Button verticalGridButton;
	
	private Button horizontalGridButton;
	
	private Button xAxisButton;
	
	private Button xAxisTitleButton;
	
	private Button xAxisValuesButton;
	
	private Button axisAllValuesButton;
	
	private Button axisMinValueButton;
	
	private Button axisMaxValueButton;
	
	private Button axisTitleButton;
	
	private Button axisValuesButton;
	
	private Button axisButton;
	
	private Button axisShowDatesButton;
	
	private Button axisShowValuesButton;
	
	private Button seriesColorButton;
	
	private TextField<String> title;
	
	private HTML titleFontColor;
	
	private ListBox titleFontStyle;
	
	private ListBox titleFontSize;
	
	private ListBox pointType;
	
	private TextArea subTitle;
	
	private HTML subTitleFontColor;
	
	private ListBox subTitleFontStyle;
	
	private ListBox subTitleFontSize;
	
	private TextField<String> imageWidth;
	
	private TextField<String> imageHeight;
	
	private TextField<String> legendTitle;
	
	private ListBox legendFontFamily;
	
	private ListBox legendFontSize;
	
	private ListBox legendPosition;
	
	private CheckBox legendHorizontal;
	
	private CheckBox stacked;
	
	private CheckBox showLegend;
	
	private CheckBox showBorder;
	
	private HTML legendFontColor;
	
	private CheckBox hasXGrid;
	
	private HTML xGridColor;
	
	private ListBox xGridLineType;
	
	private TextField<String> xAxisTitle;
	
	private TextField<String> xAxisNumberOfIntervals;
	
	private HTML xAxisTicksColor;
	
	private HTML xAxisColor;
	
	private HTML xAxisTitleFontColor;
	
	private ListBox xAxisTitleFontFamily;
	
	private ListBox xAxisTitleFontSize;
	
	private HTML xValuesColor;
	
	private ListBox xValuesFontSize;
	
	private ListBox xValuesFontFamily;
	
	private ListBox xLabelInclination;
	
	private CheckBox allValues;
	
	private HTML allValuesFontColor;
	
	private ListBox allValuesFontFamily;
	
	private ListBox allValuesFontStize;
	
	private CheckBox maxValue;
	
	private HTML maxValueFontColor;
	
	private ListBox maxValueFontFamily;
	
	private ListBox maxValueFontStize;
	
	private CheckBox minValue;
	
	private HTML minValueFontColor;
	
	private ListBox minValueFontFamily;
	
	private ListBox minValueFontStize;
	
	private CheckBox hasYGrid;
	
	private HTML yGridColor;
	
	private ListBox yGridLineType;
	
	private TextField<String> axisTitle;
	
	private TextField<String> axisNumberOfIntervals;
	
	private HTML axisTicksColor;
	
	private HTML axisColor;
	
	private HTML axisTitleFontColor;
	
	private ListBox axisTitleFontFamily;
	
	private ListBox axisTitleFontSize;
	
	private HTML valuesColor;
	
	private ListBox valuesFontSize;
	
	private ListBox valuesFontFamily;
	
	private ListBox labelInclination;
	
	private ListBox axisPosition;
	
	private ListBox axisLineWidth;
	
	private ListBox axisLineType;
	
	private TextField<String> axisFromValue;
	
	private TextField<String> axisToValue;
	
	private TextField<String> labelsDistanceFromAxis;
	
	private Long datasetID;
	
	private VerticalPanel seriesColorPanel;
	
	private Map<TextField<String>, HTML> seriesColorMap;
	
	private Map<TextField<String>, String> headerYCodeMap;
	
	private CheckBox axisShowDates;
	
	private CheckBox xAxisEquidistantDates;
	
	private CheckBox yAxisEquidistantDates;
	
	private HTML axisShowDatesFontColor;
	
	private ListBox axisShowDatesFontSize;
	
	private ListBox axisShowDatesFontStyle;
	
	private VerticalPanel datesWrapper;
	
	private List<HorizontalPanel> axisShowDatePanels;
	
	private CheckBox axisShowValues;
	
	private HTML axisShowValuesFontColor;
	
	private ListBox axisShowValuesFontSize;
	
	private ListBox axisShowValuesFontStyle;
	
	private ListBox datesFormat;
	
	private VerticalPanel valuesWrapper;
	
	private List<HorizontalPanel> axisShowValuePanels;
	
	private Html xAxisNumberOfLabels;
	
	public LookAndFeelTabItem(String header) {
		wrapper = new HorizontalPanel();
		wrapper.setSpacing(SPACING);
		leftWrapper = new VerticalPanel();
		leftWrapper.setBorders(true);
		leftWrapper.setVerticalAlign(VerticalAlignment.MIDDLE);
		leftWrapper.setSpacing(SPACING);
		leftWrapper.setHeight(HEIGHT);
		leftWrapper.setLayout(new FillLayout());
		rightWrapper = new VerticalPanel();
		rightWrapper.setBorders(true);
		rightWrapper.setHorizontalAlign(HorizontalAlignment.CENTER);
		rightWrapper.setVerticalAlign(VerticalAlignment.MIDDLE);
		rightWrapper.setSpacing(SPACING);
		rightWrapper.setHeight(HEIGHT);
		rightWrapper.setWidth(WRAPPER_WIDTH);
		rightWrapper.setScrollMode(Scroll.AUTO);
		rightWrapper.setLayout(new FillLayout());
		tabItem = new TabItem(header);
		allValues = new CheckBox();
		showLegend = new CheckBox();
		showBorder = new CheckBox();
		allValuesFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		allValuesFontColor.addClickHandler(ChartDesignerController.colorPicker(allValuesFontColor));
		allValuesFontFamily = new ListBox();
		allValuesFontStize = new ListBox();
		ChartDesignerController.fillFontFamilyList(allValuesFontFamily);
		ChartDesignerController.fillFontSizeList(allValuesFontStize);
		maxValue = new CheckBox();
		maxValueFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #CA1616; color: #CA1616; font-weight: bold; font-style: italic;'>#CA1616</div>");
		maxValueFontColor.addClickHandler(ChartDesignerController.colorPicker(maxValueFontColor));
		maxValueFontFamily = new ListBox();
		maxValueFontStize = new ListBox();
		ChartDesignerController.fillFontFamilyList(maxValueFontFamily);
		ChartDesignerController.fillFontSizeList(maxValueFontStize);
		datesFormat = new ListBox();
		ChartDesignerController.fillDatesFormat(datesFormat);
		minValue = new CheckBox();
		minValueFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #006400; color: #006400; font-weight: bold; font-style: italic;'>#006400</div>");
		minValueFontColor.addClickHandler(ChartDesignerController.colorPicker(minValueFontColor));
		minValueFontFamily = new ListBox();
		minValueFontStize = new ListBox();
		ChartDesignerController.fillFontFamilyList(minValueFontFamily);
		ChartDesignerController.fillFontSizeList(minValueFontStize);
		xAxisTitle = new TextField<String>();
		xAxisNumberOfIntervals = new TextField<String>();
		xAxisTicksColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		xAxisTicksColor.addClickHandler(ChartDesignerController.colorPicker(xAxisTicksColor));
		xAxisColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		xAxisColor.addClickHandler(ChartDesignerController.colorPicker(xAxisColor));
		xAxisTitleFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		xAxisTitleFontColor.addClickHandler(ChartDesignerController.colorPicker(xAxisTitleFontColor));
		xAxisTitleFontFamily = new ListBox();
		xAxisTitleFontSize = new ListBox();
		ChartDesignerController.fillFontFamilyList(xAxisTitleFontFamily);
		ChartDesignerController.fillFontSizeList(xAxisTitleFontSize);
		xValuesColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		xValuesColor.addClickHandler(ChartDesignerController.colorPicker(xValuesColor));
		xValuesFontSize = new ListBox();
		xValuesFontFamily = new ListBox();
		ChartDesignerController.fillFontFamilyList(xValuesFontFamily);
		ChartDesignerController.fillFontSizeList(xValuesFontSize);
		xGridColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #D3D3D3; color: #D3D3D3; font-weight: bold; font-style: italic;'>#D3D3D3</div>");
		xGridColor.addClickHandler(ChartDesignerController.colorPicker(xGridColor));
		xGridLineType = new ListBox();
		xLabelInclination = new ListBox();
		hasXGrid = new CheckBox();
		axisTitle = new TextField<String>();
		axisNumberOfIntervals = new TextField<String>();
		axisTicksColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		axisTicksColor.addClickHandler(ChartDesignerController.colorPicker(axisTicksColor));
		axisColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		axisColor.addClickHandler(ChartDesignerController.colorPicker(axisColor));
		axisTitleFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		axisTitleFontColor.addClickHandler(ChartDesignerController.colorPicker(axisTitleFontColor));
		axisTitleFontFamily = new ListBox();
		axisTitleFontSize = new ListBox();
		ChartDesignerController.fillFontFamilyList(axisTitleFontFamily);
		ChartDesignerController.fillFontSizeList(axisTitleFontSize);
		valuesColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		valuesColor.addClickHandler(ChartDesignerController.colorPicker(valuesColor));
		valuesFontSize = new ListBox();
		valuesFontFamily = new ListBox();
		ChartDesignerController.fillFontFamilyList(valuesFontFamily);
		ChartDesignerController.fillFontSizeList(valuesFontSize);
		yGridColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #D3D3D3; color: #D3D3D3; font-weight: bold; font-style: italic;'>#D3D3D3</div>");
		yGridColor.addClickHandler(ChartDesignerController.colorPicker(yGridColor));
		yGridLineType = new ListBox();
		labelInclination = new ListBox();
		hasYGrid = new CheckBox();
		legendTitle = new TextField<String>();
		legendFontFamily = new ListBox();
		legendFontSize = new ListBox();
		legendPosition = new ListBox();
		legendPosition.addItem("Top", "top");
		legendPosition.addItem("Top Left", "topleft");
		legendPosition.addItem("Top Right", "topright");
		legendPosition.addItem("Bottom", "bottom");
		legendPosition.addItem("Bottom Left", "bottomleft");
		legendPosition.addItem("Bottom Right", "bottomright");
		legendPosition.setSelectedIndex(5);
		legendFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		legendFontColor.addClickHandler(ChartDesignerController.colorPicker(legendFontColor));
		chartSizeButton = new Button("Chart Size");
		chartSizeButton.setWidth(BUTTON_WIDTH);
		
		borderButton = new Button("Border");
		borderButton.setWidth(BUTTON_WIDTH);
		
		legendButton = new Button("Legend");
		legendButton.setWidth(BUTTON_WIDTH);
		subTitleButton = new Button("Notes");
		subTitleButton.setWidth(BUTTON_WIDTH);
		titleButton = new Button("Chart Title");
		titleButton.setWidth(BUTTON_WIDTH);
		verticalGridButton = new Button("Vertical Grid");
		verticalGridButton.setWidth(BUTTON_WIDTH);
		horizontalGridButton = new Button("Horizontal Grid");
		horizontalGridButton.setWidth(BUTTON_WIDTH);
		xAxisButton = new Button("X-Axis Color");
		xAxisButton.setWidth(BUTTON_WIDTH);
		xAxisTitleButton = new Button("X-Axis Title");
		xAxisTitleButton.setWidth(BUTTON_WIDTH);
		xAxisValuesButton = new Button("X-Axis Labels");
		xAxisValuesButton.setWidth(BUTTON_WIDTH);
		axisAllValuesButton = new Button("Show All Values");
		axisAllValuesButton.setWidth(BUTTON_WIDTH);
//		axisAllValuesButton.setEnabled(false);
		axisMinValueButton = new Button("Show Minimum Value");
		axisMinValueButton.setWidth(BUTTON_WIDTH);
//		axisMinValueButton.setEnabled(false);
		axisMaxValueButton = new Button("Show Maximum Value");
		axisMaxValueButton.setWidth(BUTTON_WIDTH);
//		axisMaxValueButton.setEnabled(false);
		axisTitleButton = new Button("Axis Label");
		axisTitleButton.setWidth(BUTTON_WIDTH);
		axisValuesButton = new Button("Axis Values");
		axisValuesButton.setWidth(BUTTON_WIDTH);
		axisButton = new Button("Axis Color and Position");
		axisButton.setWidth(BUTTON_WIDTH);
		axisShowDatesButton = new Button("Show Dates");
		axisShowDatesButton.setWidth(BUTTON_WIDTH);
		axisShowValuesButton = new Button("Show Values");
		axisShowValuesButton.setWidth(BUTTON_WIDTH);
		seriesColorButton = new Button("Series Colors");
		seriesColorButton.setWidth(BUTTON_WIDTH);
		title = new TextField<String>();
		titleFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		titleFontColor.addClickHandler(ChartDesignerController.colorPicker(titleFontColor));
		titleFontStyle = new ListBox();
		titleFontSize = new ListBox();
		ChartDesignerController.fillFontFamilyList(titleFontStyle);
		pointType = new ListBox();
		ChartDesignerController.fillPointTypeList(pointType);
		ChartDesignerController.fillFontSizeList(titleFontSize);
		subTitle = new TextArea();
		subTitleFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		subTitleFontColor.addClickHandler(ChartDesignerController.colorPicker(subTitleFontColor));
		subTitleFontStyle = new ListBox();
		subTitleFontSize = new ListBox();
		ChartDesignerController.fillFontFamilyList(subTitleFontStyle);
		ChartDesignerController.fillFontSizeList(subTitleFontSize);
		imageWidth = new TextField<String>();
		imageHeight = new TextField<String>();
		ChartDesignerController.fillFontFamilyList(legendFontFamily);
		ChartDesignerController.fillFontSizeList(legendFontSize);
		axisPosition = new ListBox();
		axisPosition.addItem("Left", "2");
		axisPosition.addItem("Right", "4");
		axisFromValue = new TextField<String>();
		axisToValue = new TextField<String>();
		axisLineWidth = new ListBox();
		for (int i = 1 ; i < 11 ; i++)
			axisLineWidth.addItem(String.valueOf(i), String.valueOf(i));
		axisLineType = new ListBox();
		axisLineType.addItem("Solid", "1");
		axisLineType.addItem("Dashed", "2");
		axisLineType.addItem("Dotted", "3");
		axisLineType.addItem("Dot Dash", "4");
		axisLineType.addItem("Long Dash", "5");
		axisLineType.addItem("Two Dash", "6");
		seriesColorPanel = new VerticalPanel();
		seriesColorPanel.setSpacing(SPACING);
		seriesColorPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		seriesColorPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		seriesColorMap = new HashMap<TextField<String>, HTML>();
		headerYCodeMap = new HashMap<TextField<String>, String>();
		labelsDistanceFromAxis = new TextField<String>();
		legendHorizontal = new CheckBox();
		stacked = new CheckBox();
		axisShowDates = new CheckBox();
		xAxisEquidistantDates = new CheckBox();
		yAxisEquidistantDates = new CheckBox();
		axisShowDatesFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		axisShowDatesFontColor.addClickHandler(ChartDesignerController.colorPicker(axisShowDatesFontColor));
		axisShowDatesFontSize = new ListBox();
		axisShowDatesFontStyle = new ListBox();
		ChartDesignerController.fillFontFamilyList(axisShowDatesFontStyle);
		ChartDesignerController.fillFontSizeList(axisShowDatesFontSize);
		datesWrapper = new VerticalPanel();
		datesWrapper.setLayout(new FillLayout());
		axisShowDatePanels = new ArrayList<HorizontalPanel>();
		axisShowValues = new CheckBox();
		axisShowValuesFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		axisShowValuesFontColor.addClickHandler(ChartDesignerController.colorPicker(axisShowValuesFontColor));
		axisShowValuesFontSize = new ListBox();
		for (int i = 1 ; i < 11 ; i++)
			axisShowValuesFontSize.addItem(String.valueOf(i), String.valueOf(i));
		axisShowValuesFontStyle = new ListBox();
		axisShowValuesFontStyle.addItem("Dashed", "2");
		axisShowValuesFontStyle.addItem("Solid", "1");
		axisShowValuesFontStyle.addItem("Dotted", "3");
		axisShowValuesFontStyle.addItem("Dot Dash", "4");
		axisShowValuesFontStyle.addItem("Long Dash", "5");
		axisShowValuesFontStyle.addItem("Two Dash", "6");
		valuesWrapper = new VerticalPanel();
		valuesWrapper.setLayout(new FillLayout());
		axisShowValuePanels = new ArrayList<HorizontalPanel>();
		xAxisNumberOfLabels = new Html();
	}
	
	public TabItem build(Long datasetID, List<Long> datasetIDs, boolean calculateYAxisMaxValue) {
		if (datasetID != null) {
			wrapper.add(buildDatasetLeftWrapper());
			this.setDatasetID(datasetID);
			axisNumberOfIntervals.setValue("10");
			if (calculateYAxisMaxValue)
				ChartDesignerController.calculateYMaxValue(axisToValue, datasetIDs);
		} else {
			wrapper.add(buildGeneralSettingsLeftWrapper());
//			rightWrapper.removeAll();
//			rightWrapper.add(buildTitlePanel());
//			rightWrapper.getLayout().layout();
		}
		wrapper.add(buildRightWrapper());
		tabItem.add(wrapper);
		return tabItem;
	}
	
	private VerticalPanel buildGeneralSettingsLeftWrapper() {
		leftWrapper.add(titleButton);
		leftWrapper.add(xAxisTitleButton);
		leftWrapper.add(xAxisValuesButton);
		leftWrapper.add(xAxisButton);
		leftWrapper.add(legendButton);
		leftWrapper.add(verticalGridButton);
		leftWrapper.add(subTitleButton);
		leftWrapper.add(chartSizeButton);
		leftWrapper.add(borderButton);
		
		
		xAxisTitleButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildXAxisTitlePanel()));
		xAxisValuesButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildXAxisValuesPanel()));
		chartSizeButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildChartSizePanel()));
		borderButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildBorderPanel()));
		
		legendButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildLegendPanel()));
		subTitleButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildSubTitlePanel()));
		titleButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildTitlePanel()));
		verticalGridButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildVerticalGridPanel()));
		xAxisButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildXAxisPanel()));
		return leftWrapper;
	}
	
	private VerticalPanel buildDatasetLeftWrapper() {
		leftWrapper.add(seriesColorButton);
		leftWrapper.add(axisTitleButton);
		leftWrapper.add(axisValuesButton);
		leftWrapper.add(axisButton);
		leftWrapper.add(horizontalGridButton);
		leftWrapper.add(axisAllValuesButton);
		leftWrapper.add(axisMaxValueButton);
		leftWrapper.add(axisMinValueButton);
		leftWrapper.add(axisShowDatesButton);
		leftWrapper.add(axisShowValuesButton);
		axisButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildAxisPanel()));
		axisTitleButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildAxisTitlePanel()));
		axisValuesButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildAxisValuesPanel()));
		horizontalGridButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildHorizontalGridPanel()));
		seriesColorButton.addSelectionListener(ChartDesignerController.addSeriesColor(this));
		axisAllValuesButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildAllValuesPanel()));
		axisMaxValueButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildMaxValuePanel()));
		axisMinValueButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildMinValuePanel()));
		axisShowDatesButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildShowDatePanel()));
		axisShowValuesButton.addSelectionListener(ChartDesignerController.showSettingsPanel(rightWrapper, buildShowValuePanel()));
		return leftWrapper;
	}
	
	private VerticalPanel buildShowValuePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html datesFontColorLabel = new Html(TITLE + "Value(s) Font Color <i>(click on the box to pick another colour)</i></div>");
		Html datesFontStyleLabel = new Html(TITLE + "Line Type</div>");
		Html datesFontSizeLabel = new Html(TITLE + "Line Width</div>");
		datesFontColorLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		datesFontStyleLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		datesFontSizeLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		axisShowValues.setBoxLabel("Show Value(s) <i>(enter FIRST, LAST or a value)</i>");
		axisShowValues.setValue(true);
		axisShowValuesFontColor.setWidth(HIGHLIGHT_FIELD_WIDTH);
		axisShowValuesFontSize.setWidth(HIGHLIGHT_FIELD_WIDTH);
		axisShowValuesFontStyle.setWidth(HIGHLIGHT_FIELD_WIDTH);
		valuesWrapper.setWidth(HIGHLIGHT_FIELD_WIDTH);
		valuesWrapper.setHeight(HIGHLIGHT_WRAPPER_HEIGHT);
		valuesWrapper.setScrollMode(Scroll.AUTO);
		valuesWrapper.setBorders(true);
		p.add(axisShowValues);
		p.add(valuesWrapper);
		p.add(datesFontColorLabel);
		p.add(axisShowValuesFontColor);
		p.add(datesFontSizeLabel);
		p.add(axisShowValuesFontSize);
		p.add(datesFontStyleLabel);
		p.add(axisShowValuesFontStyle);
		buildAndAddValuePanel(true);
		return p;
	}
	
	private VerticalPanel buildShowDatePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html datesFontColorLabel = new Html(TITLE + "Date(s) Font Color <i>(click on the box to pick another colour)</i></div>");
		Html datesFontStyleLabel = new Html(TITLE + "Date(s) Font Style</div>");
		Html datesFontSizeLabel = new Html(TITLE + "Date(s) Font Size</div>");
		datesFontColorLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		datesFontStyleLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		datesFontSizeLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		axisShowDates.setBoxLabel("Show Date(s)");
		axisShowDatesFontColor.setWidth(HIGHLIGHT_FIELD_WIDTH);
		axisShowDatesFontSize.setWidth(HIGHLIGHT_FIELD_WIDTH);
		axisShowDatesFontStyle.setWidth(HIGHLIGHT_FIELD_WIDTH);
		datesWrapper.setWidth(HIGHLIGHT_FIELD_WIDTH);
		datesWrapper.setHeight(HIGHLIGHT_WRAPPER_HEIGHT);
		datesWrapper.setScrollMode(Scroll.AUTO);
		datesWrapper.setBorders(true);
		p.add(axisShowDates);
		p.add(datesWrapper);
		p.add(datesFontColorLabel);
		p.add(axisShowDatesFontColor);
		p.add(datesFontSizeLabel);
		p.add(axisShowDatesFontSize);
		p.add(datesFontStyleLabel);
		p.add(axisShowDatesFontStyle);
		buildAndAddDatePanel(false);
		return p;
	}
	
	public void buildAndAddDatePanel(boolean show) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		CheckBox cb = new CheckBox();
		cb.setBoxLabel("Show Date: ");
		cb.setValue(show);
		DateField df = new DateField();
		df.setWidth("425px");
		df.setValue(new Date());
		Button b = new Button("Add Date");
		b.setIconStyle("addIcon");
		b.setWidth("110px");
		b.addSelectionListener(ChartDesignerController.addDatePanel(this));
		p.add(cb);
		p.add(df);
		p.add(b);
		p.setData("CHECKBOX", cb);
		p.setData("DATEFIELD", df);
		datesWrapper.add(p);
		datesWrapper.getLayout().layout();
		axisShowDatePanels.add(p);
	}
	
	public void buildAndAddDatePanel(boolean show, Date date) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		CheckBox cb = new CheckBox();
		cb.setBoxLabel("Show Date: ");
		cb.setValue(show);
		DateField df = new DateField();
		df.setWidth("425px");
		df.setValue(date);
		Button b = new Button("Add Date");
		b.setIconStyle("addIcon");
		b.setWidth("110px");
		b.addSelectionListener(ChartDesignerController.addDatePanel(this));
		p.add(cb);
		p.add(df);
		p.add(b);
		p.setData("CHECKBOX", cb);
		p.setData("DATEFIELD", df);
		datesWrapper.add(p);
		datesWrapper.getLayout().layout();
		axisShowDatePanels.add(p);
	}
	
	public void buildAndAddValuePanel(boolean enabled) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		CheckBox cb = new CheckBox();
		cb.setBoxLabel("Show Value: ");
		cb.setValue(enabled);
		TextField<String> tf = new TextField<String>();
		tf.setWidth("75px");
		tf.setValue("LAST");
		tf.setEmptyText("enter FIRST, LAST or a value");
		Html lbl = new Html("Label <i>(for specific values only)</i>");
		lbl.setWidth("150px");
		TextField<String> aliastf = new TextField<String>();
		aliastf.setWidth("175px");
		aliastf.setEmptyText("e.g. Production Target for 2010");
		Button b = new Button("Add Value");
		b.setIconStyle("addIcon");
		b.setWidth("110px");
		b.addSelectionListener(ChartDesignerController.addValuePanel(this));
		p.add(cb);
		p.add(tf);
		p.add(lbl);
		p.add(aliastf);
		p.add(b);
		p.setData("CHECKBOX", cb);
		p.setData("TEXTFIELD", tf);
		p.setData("ALIAS_TEXTFIELD", aliastf);
		valuesWrapper.add(p);
		valuesWrapper.getLayout().layout();
		axisShowValuePanels.add(p);
	}
	
	public void buildAndAddValuePanel(boolean enabled, String value, String alias) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		CheckBox cb = new CheckBox();
		cb.setBoxLabel("Show Value: ");
		cb.setValue(enabled);
		TextField<String> tf = new TextField<String>();
		tf.setWidth("410px");
		tf.setValue(value);
		tf.setEmptyText("enter FIRST, LAST or a value");
		Html lbl = new Html("Label <i>(for specific values only)</i>");
		lbl.setWidth("150px");
		TextField<String> aliastf = new TextField<String>();
		aliastf.setWidth("175px");
		aliastf.setEmptyText("e.g. Production Target for 2010");
		aliastf.setValue(alias);
		Button b = new Button("Add Value");
		b.setIconStyle("addIcon");
		b.setWidth("110px");
		b.addSelectionListener(ChartDesignerController.addValuePanel(this));
		p.add(cb);
		p.add(tf);
		p.add(lbl);
		p.add(aliastf);
		p.add(b);
		p.setData("CHECKBOX", cb);
		p.setData("TEXTFIELD", tf);
		p.setData("ALIAS_TEXTFIELD", aliastf);
		valuesWrapper.add(p);
		valuesWrapper.getLayout().layout();
		axisShowValuePanels.add(p);
	}
	
	public void addSeriesColorAgent(Map<String, String> map) {
		seriesColorPanel = new VerticalPanel();
		seriesColorPanel.setLayout(new FillLayout());
		seriesColorPanel.setSpacing(SPACING);
		seriesColorPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		seriesColorPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		seriesColorMap.clear();
		for (String key : map.keySet()) {
			HTML color = new HTML("<div align='center' style='border: 4px black solid; background-color: " + map.get(key) + "; color: " + map.get(key) + "; font-weight: bold; font-style: italic;'>" + map.get(key) + "</div>");
			TextField<String> header = new TextField<String>();
			header.setValue(key);
			seriesColorPanel.add(buildColorPanel(header, color));
			headerYCodeMap.put(header, key);
		}
		rightWrapper.removeAll();
		rightWrapper.add(seriesColorPanel);
		seriesColorPanel.getLayout().layout();
		rightWrapper.getLayout().layout();
	}
	
	public void addSeriesColorAgent() {
		seriesColorPanel = new VerticalPanel();
		seriesColorPanel.setSpacing(SPACING);
		seriesColorPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		seriesColorPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		for (TextField<String> key : seriesColorMap.keySet()) {
			seriesColorPanel.add(buildColorPanel(key, seriesColorMap.get(key)));
		}
		rightWrapper.removeAll();
		rightWrapper.add(seriesColorPanel);
		rightWrapper.getLayout().layout();
	}
	
	private HorizontalPanel buildColorPanel(TextField<String> header, HTML hex) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		hex.addClickHandler(ChartDesignerController.colorPicker(hex));
		hex.setWidth("100px");
		header.setWidth("200px");
		p.add(hex);
		p.add(header);
		seriesColorMap.put(header, hex);
		return p;
	}
	
	private VerticalPanel buildRightWrapper() {
		return rightWrapper;
	}
	
	private VerticalPanel buildLegendPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html legendTitleLabel = new Html(TITLE + "Legend</div>");
		Html legendFontColorLabel = new Html(TITLE + "Legend Background Color <i>(click on the box to pick another colour)</i></div>");
		Html legendFontFamilyLabel = new Html(TITLE + "Legend Font Size</div>");
		Html legendFontSizeLabel = new Html(TITLE + "Legend Font Style</div>");
		Html legendPositionLabel = new Html(TITLE + "Legend Position</div>");
		legendTitleLabel.setWidth(FIELD_WIDTH);
		legendFontColorLabel.setWidth(FIELD_WIDTH);
		legendFontFamilyLabel.setWidth(FIELD_WIDTH);
		legendFontSizeLabel.setWidth(FIELD_WIDTH);
		legendPositionLabel.setWidth(FIELD_WIDTH);
		legendTitle.setWidth(FIELD_WIDTH);
		legendTitle.setValue("Legend");
		legendFontColor.setWidth(FIELD_WIDTH);
		legendFontFamily.setWidth(FIELD_WIDTH);
		legendFontSize.setWidth(FIELD_WIDTH);
		legendFontSize.setSelectedIndex(3);
		legendPosition.setWidth(FIELD_WIDTH);
		showLegend.setBoxLabel("Show Legend");
		showLegend.setValue(true);
		legendHorizontal.setBoxLabel("Arrange Items Horizontally");
//		legendHorizontal.setValue(true);
		p.add(showLegend);
		p.add(legendHorizontal);
//		p.add(legendTitleLabel);
//		p.add(legendTitle);
		p.add(legendFontColorLabel);
		p.add(legendFontColor);
		p.add(legendFontFamilyLabel);
		p.add(legendFontFamily);
		p.add(legendFontSizeLabel);
		p.add(legendFontSize);
		p.add(legendPositionLabel);
		p.add(legendPosition);
		return p;
	}
	
	private VerticalPanel buildTitlePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html titleLabel = new Html(TITLE + "Title</div>");
		Html titleFontColorLabel = new Html(TITLE + "Title Font Color <i>(click on the box to pick another colour)</i></div>");
		Html titleFontSizeLabel = new Html(TITLE + "Title Font Size</div>");
		Html titleFontStyleLabel = new Html(TITLE + "Title Font Style</div>");
		titleLabel.setWidth(FIELD_WIDTH);
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		titleFontStyleLabel.setWidth(FIELD_WIDTH);
		title.setWidth(FIELD_WIDTH);
//		title.setValue("Title");
		titleFontColor.setWidth(FIELD_WIDTH);
		titleFontSize.setWidth(FIELD_WIDTH);
		titleFontSize.setSelectedIndex(10);
		titleFontStyle.setWidth(FIELD_WIDTH);
		p.add(titleLabel);
		p.add(title);
		p.add(titleFontColorLabel);
		p.add(titleFontColor);
		p.add(titleFontSizeLabel);
		p.add(titleFontSize);
		p.add(titleFontStyleLabel);
		p.add(titleFontStyle);
		return p;
	}
	
	private VerticalPanel buildXAxisTitlePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html titleLabel = new Html(TITLE + "X-Axis Label</div>");
		Html titleFontColorLabel = new Html(TITLE + "X-Axis Label Font Color <i>(click on the box to pick another colour)</i></div>");
		Html titleFontSizeLabel = new Html(TITLE + "X-Axis Label Font Size</div>");
		Html titleFontStyleLabel = new Html(TITLE + "X-Axis Label Font Style</div>");
		titleLabel.setWidth(FIELD_WIDTH);
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		titleFontStyleLabel.setWidth(FIELD_WIDTH);
		xAxisTitle.setWidth(FIELD_WIDTH);
//		xAxisTitle.setValue("X-Axis");
		xAxisTitleFontColor.setWidth(FIELD_WIDTH);
		xAxisTitleFontFamily.setWidth(FIELD_WIDTH);
		xAxisTitleFontSize.setWidth(FIELD_WIDTH);
		p.add(titleLabel);
		p.add(xAxisTitle);
		p.add(titleFontColorLabel);
		p.add(xAxisTitleFontColor);
		p.add(titleFontSizeLabel);
		p.add(xAxisTitleFontSize);
		p.add(titleFontStyleLabel);
		p.add(xAxisTitleFontFamily);
		return p;
	}
	
	private VerticalPanel buildAxisTitlePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html titleLabel = new Html(TITLE + "Y-Axis Label</div>");
		Html titleFontColorLabel = new Html(TITLE + "Y-Axis Label Font Color <i>(click on the box to pick another colour)</i></div>");
		Html titleFontSizeLabel = new Html(TITLE + "Y-Axis Label Font Size</div>");
		Html titleFontStyleLabel = new Html(TITLE + "Y-Axis Label Font Style</div>");
		titleLabel.setWidth(FIELD_WIDTH);
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		titleFontStyleLabel.setWidth(FIELD_WIDTH);
		axisTitle.setWidth(FIELD_WIDTH);
		axisTitle.setValue("Y-Axis");
		axisTitleFontColor.setWidth(FIELD_WIDTH);
		axisTitleFontFamily.setWidth(FIELD_WIDTH);
		axisTitleFontSize.setWidth(FIELD_WIDTH);
		p.add(titleLabel);
		p.add(axisTitle);
		p.add(titleFontColorLabel);
		p.add(axisTitleFontColor);
		p.add(titleFontSizeLabel);
		p.add(axisTitleFontSize);
		p.add(titleFontStyleLabel);
		p.add(axisTitleFontFamily);
		return p;
	}
	
	private VerticalPanel buildAllValuesPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		Html titleFontColorLabel = new Html(TITLE + "Values Font Color <i>(click on the box to pick another colour)</i></div>");
		Html titleFontSizeLabel = new Html(TITLE + "Values Font Size</div>");
		Html titleFontStyleLabel = new Html(TITLE + "Values Font Style</div>");
		titleFontColorLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		titleFontSizeLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		titleFontStyleLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		allValues.setBoxLabel("Show All Values");
		allValuesFontColor.setWidth(HIGHLIGHT_FIELD_WIDTH);
		allValuesFontFamily.setWidth(HIGHLIGHT_FIELD_WIDTH);
//		allValuesFontFamily.setSelectedIndex(1);
		allValuesFontStize.setWidth(HIGHLIGHT_FIELD_WIDTH);
		allValuesFontStize.setSelectedIndex(2);
		p.add(allValues);
		p.add(titleFontColorLabel);
		p.add(allValuesFontColor);
		p.add(titleFontSizeLabel);
		p.add(allValuesFontFamily);
		p.add(titleFontStyleLabel);
		p.add(allValuesFontStize);
		return p;
	}
	
	private VerticalPanel buildMaxValuePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		Html titleFontColorLabel = new Html(TITLE + "Maximum Value Font Color <i>(click on the box to pick another colour)</i></div>");
		Html titleFontSizeLabel = new Html(TITLE + "Maximum Value Font Size</div>");
		Html titleFontStyleLabel = new Html(TITLE + "Maximum Value Font Style</div>");
		titleFontColorLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		titleFontSizeLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		titleFontStyleLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		maxValue.setBoxLabel("Show Maximum Value");
//		maxValue.setValue(true);
		maxValueFontColor.setWidth(HIGHLIGHT_FIELD_WIDTH);
		maxValueFontFamily.setWidth(HIGHLIGHT_FIELD_WIDTH);
		maxValueFontStize.setWidth(HIGHLIGHT_FIELD_WIDTH);
		maxValueFontStize.setSelectedIndex(2);
		p.add(maxValue);
		p.add(titleFontColorLabel);
		p.add(maxValueFontColor);
		p.add(titleFontSizeLabel);
		p.add(maxValueFontFamily);
		p.add(titleFontStyleLabel);
		p.add(maxValueFontStize);
		return p;
	}
	
	private VerticalPanel buildMinValuePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		Html titleFontColorLabel = new Html(TITLE + "Minimum Value Font Color <i>(click on the box to pick another colour)</i></div>");
		Html titleFontSizeLabel = new Html(TITLE + "Minimum Value Font Size</div>");
		Html titleFontStyleLabel = new Html(TITLE + "Minimum Value Font Style</div>");
		titleFontColorLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		titleFontSizeLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		titleFontStyleLabel.setWidth(HIGHLIGHT_FIELD_WIDTH);
		minValue.setBoxLabel("Show Minimum Value");
//		minValue.setValue(true);
		minValueFontColor.setWidth(HIGHLIGHT_FIELD_WIDTH);
		minValueFontFamily.setWidth(HIGHLIGHT_FIELD_WIDTH);
		minValueFontStize.setWidth(HIGHLIGHT_FIELD_WIDTH);
		minValueFontStize.setSelectedIndex(2);
		p.add(minValue);
		p.add(titleFontColorLabel);
		p.add(minValueFontColor);
		p.add(titleFontSizeLabel);
		p.add(minValueFontFamily);
		p.add(titleFontStyleLabel);
		p.add(minValueFontStize);
		return p;
	}
	
	private VerticalPanel buildXAxisValuesPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html titleLabel = new Html(TITLE + "X-Axis Values Font Style</div>");
		Html titleFontColorLabel = new Html(TITLE + "X-Axis Values Font Color <i>(click on the box to pick another colour)</i></div>");
		Html titleFontSizeLabel = new Html(TITLE + "X-Axis Values Font Size</div>");
		Html orientationLabel = new Html(TITLE + "Labels Orientation</div>");
		Html distanceLabel = new Html(TITLE + "Labels Y Coordinate</div>");
		Html datesFormatLabel = new Html(TITLE + "Dates Format</div>");
		xAxisNumberOfLabels = new Html(TITLE + "Maximum Number of Labels</div>");
		titleLabel.setWidth(FIELD_WIDTH);
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		orientationLabel.setWidth(FIELD_WIDTH);
		distanceLabel.setWidth(FIELD_WIDTH);
		datesFormatLabel.setWidth(FIELD_WIDTH);
		xAxisNumberOfLabels.setWidth(FIELD_WIDTH);
		xValuesColor.setWidth(FIELD_WIDTH);
		xValuesFontFamily.setWidth(FIELD_WIDTH);
		xValuesFontSize.setWidth(FIELD_WIDTH);
		xLabelInclination.setWidth(FIELD_WIDTH);
		xValuesFontSize.setSelectedIndex(2);
		xLabelInclination.addItem("0", "0");
		xLabelInclination.addItem("30", "30");
		xLabelInclination.addItem("45", "45");
		xLabelInclination.addItem("60", "60");
		xLabelInclination.addItem("90", "90");
		xLabelInclination.setSelectedIndex(2);
		labelsDistanceFromAxis.setWidth(FIELD_WIDTH);
		labelsDistanceFromAxis.setValue("-1");
		xAxisNumberOfIntervals.setWidth(FIELD_WIDTH);
		xAxisNumberOfIntervals.setValue("5");
		xAxisEquidistantDates.setValue(true);
		xAxisEquidistantDates.setBoxLabel("Equidistant Dates");
		xAxisEquidistantDates.addListener(Events.OnClick, ChartDesignerController.equidistantDates(this, xAxisEquidistantDates));
		datesFormat.setWidth(FIELD_WIDTH);
		p.add(titleFontColorLabel);
		p.add(xValuesColor);
		p.add(titleLabel);
		p.add(xValuesFontFamily);
		p.add(titleFontSizeLabel);
		p.add(xValuesFontSize);
		p.add(datesFormatLabel);
		p.add(datesFormat);
		p.add(orientationLabel);
		p.add(xLabelInclination);
		p.add(buildXAxisNumberOfLabelsPanel());
		p.add(xAxisNumberOfIntervals);
		p.add(distanceLabel);
		p.add(labelsDistanceFromAxis);
		p.add(xAxisEquidistantDates);
		return p;
	}
	
	private HorizontalPanel buildXAxisNumberOfLabelsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		xAxisNumberOfLabels = new Html(TITLE + "Number of Labels</div>");
		xAxisNumberOfLabels.setWidth("430px");
		IconButton i = new IconButton("info");
		i.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				FenixAlert.info(BabelFish.print().info(), "When the <i>Equidistant Dates</i> option " +
														  "is selected this option determinates the number " +
														  "of visible labels on the X-Axis. Otherwise it represents " +
														  "the frequency of the visible labels, e.g. it shows a label " +
														  "every 50, while 0 will show them all. " +
														  "In any case, first and last label are always visible.");
			};
		});
	    p.add(xAxisNumberOfLabels);
	    p.add(i);
		return p;
	}
	
	private VerticalPanel buildAxisValuesPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html titleLabel = new Html(TITLE + "Y-Axis Values Font Style</div>");
		Html titleFontColorLabel = new Html(TITLE + "Y-Axis Values Font Color <i>(click on the box to pick another colour)</i></div>");
		Html titleFontSizeLabel = new Html(TITLE + "Y-Axis Values Font Size</div>");
		Html lineWidthLabel = new Html(TITLE + "Line Width <i>(line charts only)</i></div>");
		Html lineTypeLabel = new Html(TITLE + "Line Type <i>(line charts only)</i></div>");
		Html stepLabel = new Html(TITLE + "Number of intervals</div>");
		Html titleFontStyleLabel = new Html(TITLE + "Labels Orientation</div>");
		Html axisFromLabel = new Html(TITLE + "Axis Minimum Value</div>");
		Html axisToLabel = new Html(TITLE + "Axis Maximum Value</div>");
		Html pointTypeLabel = new Html(TITLE + "Point Type <i>(point chart only)</i></div>");
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		titleFontStyleLabel.setWidth(FIELD_WIDTH);
		titleLabel.setWidth(FIELD_WIDTH);
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		stepLabel.setWidth(FIELD_WIDTH);
		lineWidthLabel.setWidth(FIELD_WIDTH);
		lineTypeLabel.setWidth(FIELD_WIDTH);
		axisFromLabel.setWidth(FIELD_WIDTH);
		axisToLabel.setWidth(FIELD_WIDTH);
		pointTypeLabel.setWidth(FIELD_WIDTH);
		stacked.setBoxLabel("Stacked Bars <i>(bar chart only)</i>");
		valuesColor.setWidth(FIELD_WIDTH);
		valuesFontFamily.setWidth(FIELD_WIDTH);
		valuesFontSize.setWidth(FIELD_WIDTH);
		valuesFontSize.setSelectedIndex(2);
		axisLineWidth.setWidth(FIELD_WIDTH);
		axisLineWidth.setSelectedIndex(1);
		axisLineType.setWidth(FIELD_WIDTH);
		pointType.setWidth(FIELD_WIDTH);
		axisNumberOfIntervals.setWidth(FIELD_WIDTH);
		axisNumberOfIntervals.setValue("1");
		labelInclination.setWidth(FIELD_WIDTH);
		labelInclination.addItem("Horizontal", "1");
		labelInclination.addItem("Vertical", "3");
		p.add(lineWidthLabel);
		p.add(axisLineWidth);
		p.add(pointTypeLabel);
		p.add(pointType);
		p.add(lineTypeLabel);
		p.add(axisLineType);
		p.add(stacked);
		p.add(titleFontColorLabel);
		p.add(valuesColor);
		p.add(titleLabel);
		p.add(valuesFontFamily);
		p.add(titleFontSizeLabel);
		p.add(valuesFontSize);
		p.add(stepLabel);
		p.add(axisNumberOfIntervals);
		p.add(axisFromLabel);
		p.add(axisFromValue);
		p.add(axisToLabel);
		p.add(axisToValue);
		p.add(titleFontStyleLabel);
		p.add(labelInclination);
		return p;
	}
	
	private VerticalPanel buildAxisPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html titleFontColorLabel = new Html(TITLE + "Axis Ticks Color <i>(click on the box to pick another colour)</i></div>");
		Html titleLabel = new Html(TITLE + "Axis Colour <i>(click on the box to pick another colour)</i></div>");
		Html axisPositionLabel = new Html(TITLE + "Axis Position</div>");
		titleLabel.setWidth(FIELD_WIDTH);
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		axisPositionLabel.setWidth(FIELD_WIDTH);
		axisColor.setWidth(FIELD_WIDTH);
		axisTicksColor.setWidth(FIELD_WIDTH);
		axisPosition.setWidth(FIELD_WIDTH);
		axisFromValue.setWidth(FIELD_WIDTH);
		axisFromValue.setValue("0");
		axisFromValue.setEmptyText("e.g. 0");
		axisToValue.setWidth(FIELD_WIDTH);
		axisToValue.setEmptyText("e.g. 100");
		p.add(titleLabel);
		p.add(axisColor);
		p.add(titleFontColorLabel);
		p.add(axisTicksColor);
		p.add(axisPositionLabel);
		p.add(axisPosition);
		return p;
	}
	
	private VerticalPanel buildSubTitlePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html titleLabel = new Html(TITLE + "Notes</div>");
		Html titleFontColorLabel = new Html(TITLE + "Notes Font Color <i>(click on the box to pick another colour)</i></div>");
		Html titleFontSizeLabel = new Html(TITLE + "Notes Font Size</div>");
		Html titleFontStyleLabel = new Html(TITLE + "Notes Font Style</div>");
		titleLabel.setWidth(FIELD_WIDTH);
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		titleFontStyleLabel.setWidth(FIELD_WIDTH);
		subTitle.setWidth(FIELD_WIDTH);
		subTitle.setHeight("100px");
//		subTitle.setValue("Notes");
		subTitleFontColor.setWidth(FIELD_WIDTH);
		subTitleFontSize.setWidth(FIELD_WIDTH);
		subTitleFontSize.setSelectedIndex(2);
		subTitleFontStyle.setWidth(FIELD_WIDTH);
		p.add(titleLabel);
		p.add(subTitle);
		p.add(titleFontColorLabel);
		p.add(subTitleFontColor);
		p.add(titleFontSizeLabel);
		p.add(subTitleFontSize);
		p.add(titleFontStyleLabel);
		p.add(subTitleFontStyle);
		return p;
	}
	
	private VerticalPanel buildXAxisPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html titleFontColorLabel = new Html(TITLE + "Axis Color <i>(click on the box to pick another colour)</i></div>");
		Html titleLabel = new Html(TITLE + "Axis Ticks Colour <i>(click on the box to pick another colour)</i></div>");
		Html titleFontStyleLabel = new Html(TITLE + "Labels Orientation</div>");
		titleLabel.setWidth(FIELD_WIDTH);
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		titleFontStyleLabel.setWidth(FIELD_WIDTH);
		xAxisColor.setWidth(FIELD_WIDTH);
		xAxisTicksColor.setWidth(FIELD_WIDTH);
		p.add(titleLabel);
		p.add(xAxisColor);
		p.add(titleFontColorLabel);
		p.add(xAxisTicksColor);
		return p;
	}
	
	
	
	private VerticalPanel buildBorderPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		showBorder.setBoxLabel("Show Border");
		showBorder.setValue(true);
		p.add(showBorder);
		return p;
	}
	
	private VerticalPanel buildChartSizePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html imageWidthLabel = new Html(TITLE + "Image Width [pixels]</div>");
		Html imageHeightLabel = new Html(TITLE + "Image Height [pixels]</i></div>");
		imageWidthLabel.setWidth(FIELD_WIDTH);
		imageHeightLabel.setWidth(FIELD_WIDTH);
		imageWidth.setWidth(FIELD_WIDTH);
		imageWidth.setValue("460");
		imageHeight.setWidth(FIELD_WIDTH);
		imageHeight.setValue("250");
		p.add(imageWidthLabel);
		p.add(imageWidth);
		p.add(imageHeightLabel);
		p.add(imageHeight);
		p.add(buildDefaultImageSizeButtonsPanel());
		return p;
	}
	
	private HorizontalPanel buildDefaultImageSizeButtonsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		String buttonWidth = "120px";
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Button s = new Button("Small");
		s.setWidth(buttonWidth);
		s.addSelectionListener(ChartDesignerController.defaultImageSize(this, "210", "195"));
		Button m = new Button("Medium");
		m.setWidth(buttonWidth);
		m.addSelectionListener(ChartDesignerController.defaultImageSize(this, "460", "250"));
		Button l = new Button("Large");
		l.setWidth(buttonWidth);
		l.addSelectionListener(ChartDesignerController.defaultImageSize(this, "900", "500"));
		Button w = new Button("Window");
		w.setWidth(buttonWidth);
		w.addSelectionListener(ChartDesignerController.defaultImageSize(this, "820", "440"));
		p.add(s);
		p.add(m);
		p.add(l);
		p.add(w);
		return p;
	}
	
	private VerticalPanel buildVerticalGridPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		Html xGridColorLabel = new Html(TITLE + "Grid Color <i>(click on the box to pick another colour)</i></div>");
		Html xGridLineTypeLabel = new Html(TITLE + "Line Type</i></div>");
		xGridColorLabel.setWidth(FIELD_WIDTH);
		xGridLineTypeLabel.setWidth(FIELD_WIDTH);
		hasXGrid.setBoxLabel("Show Vertical Grid");
		hasXGrid.setValue(true);
		xGridColor.setWidth(FIELD_WIDTH);
		xGridLineType.setWidth(FIELD_WIDTH);
		xGridLineType.addItem("Solid", "1");
		xGridLineType.addItem("Dashed", "2");
		xGridLineType.addItem("Dotted", "3");
		xGridLineType.addItem("Dot Dash", "4");
		xGridLineType.addItem("Long Dash", "5");
		xGridLineType.addItem("Two Dash", "6");
		p.add(hasXGrid);
		p.add(xGridColorLabel);
		p.add(xGridColor);
		p.add(xGridLineTypeLabel);
		p.add(xGridLineType);
		return p;
	}
	
	private VerticalPanel buildHorizontalGridPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		Html xGridColorLabel = new Html(TITLE + "Grid Color <i>(click on the box to pick another colour)</i></div>");
		Html xGridLineTypeLabel = new Html(TITLE + "Line Type</i></div>");
		xGridColorLabel.setWidth(FIELD_WIDTH);
		xGridLineTypeLabel.setWidth(FIELD_WIDTH);
		hasYGrid.setBoxLabel("Show Horizontal Grid");
		hasYGrid.setValue(true);
		yGridColor.setWidth(FIELD_WIDTH);
		yGridLineType.setWidth(FIELD_WIDTH);
		yGridLineType.addItem("Solid", "1");
		yGridLineType.addItem("Dashed", "2");
		yGridLineType.addItem("Dotted", "3");
		yGridLineType.addItem("Dot Dash", "4");
		yGridLineType.addItem("Long Dash", "5");
		yGridLineType.addItem("Two Dash", "6");
		p.add(hasYGrid);
		p.add(xGridColorLabel);
		p.add(yGridColor);
		p.add(xGridLineTypeLabel);
		p.add(yGridLineType);
		return p;
	}

	public Button getChartSizeButton() {
		return chartSizeButton;
	}

	public Button getLegendButton() {
		return legendButton;
	}

	public Button getSubTitleButton() {
		return subTitleButton;
	}

	public Button getVerticalGridButton() {
		return verticalGridButton;
	}

	public Button getHorizontalGridButton() {
		return horizontalGridButton;
	}

	public Button getAxisMinValueButton() {
		return axisMinValueButton;
	}

	public Button getAxisMaxValueButton() {
		return axisMaxValueButton;
	}

	public TextField<String> getTitle() {
		return title;
	}

	public HTML getTitleFontColor() {
		return titleFontColor;
	}

	public ListBox getTitleFontStyle() {
		return titleFontStyle;
	}

	public ListBox getTitleFontSize() {
		return titleFontSize;
	}

	public TextArea getSubTitle() {
		return subTitle;
	}

	public HTML getSubTitleFontColor() {
		return subTitleFontColor;
	}

	public ListBox getSubTitleFontStyle() {
		return subTitleFontStyle;
	}

	public ListBox getSubTitleFontSize() {
		return subTitleFontSize;
	}

	public TextField<String> getImageWidth() {
		return imageWidth;
	}

	public TextField<String> getImageHeight() {
		return imageHeight;
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

	public ListBox getLegendPosition() {
		return legendPosition;
	}

	public HTML getLegendFontColor() {
		return legendFontColor;
	}

	public CheckBox getHasXGrid() {
		return hasXGrid;
	}

	public HTML getxGridColor() {
		return xGridColor;
	}

	public ListBox getxGridLineType() {
		return xGridLineType;
	}

	public TextField<String> getxAxisTitle() {
		return xAxisTitle;
	}

	public TextField<String> getxAxisNumberOfIntervals() {
		return xAxisNumberOfIntervals;
	}

	public HTML getxAxisTicksColor() {
		return xAxisTicksColor;
	}

	public HTML getxAxisColor() {
		return xAxisColor;
	}

	public HTML getxAxisTitleFontColor() {
		return xAxisTitleFontColor;
	}

	public ListBox getxAxisTitleFontFamily() {
		return xAxisTitleFontFamily;
	}

	public ListBox getxAxisTitleFontSize() {
		return xAxisTitleFontSize;
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

	public ListBox getxLabelInclination() {
		return xLabelInclination;
	}

	public CheckBox getAllValues() {
		return allValues;
	}

	public HTML getAllValuesFontColor() {
		return allValuesFontColor;
	}

	public ListBox getAllValuesFontFamily() {
		return allValuesFontFamily;
	}

	public ListBox getAllValuesFontStize() {
		return allValuesFontStize;
	}

	public CheckBox getMaxValue() {
		return maxValue;
	}

	public HTML getMaxValueFontColor() {
		return maxValueFontColor;
	}

	public ListBox getMaxValueFontFamily() {
		return maxValueFontFamily;
	}

	public ListBox getMaxValueFontStize() {
		return maxValueFontStize;
	}

	public CheckBox getMinValue() {
		return minValue;
	}

	public HTML getMinValueFontColor() {
		return minValueFontColor;
	}

	public ListBox getMinValueFontFamily() {
		return minValueFontFamily;
	}

	public ListBox getMinValueFontStize() {
		return minValueFontStize;
	}

	public CheckBox getHasYGrid() {
		return hasYGrid;
	}

	public HTML getyGridColor() {
		return yGridColor;
	}

	public ListBox getyGridLineType() {
		return yGridLineType;
	}

	public TextField<String> getAxisTitle() {
		return axisTitle;
	}

	public TextField<String> getAxisNumberOfIntervals() {
		return axisNumberOfIntervals;
	}

	public HTML getAxisTicksColor() {
		return axisTicksColor;
	}

	public HTML getAxisColor() {
		return axisColor;
	}

	public HTML getAxisTitleFontColor() {
		return axisTitleFontColor;
	}

	public ListBox getAxisTitleFontFamily() {
		return axisTitleFontFamily;
	}

	public ListBox getAxisTitleFontSize() {
		return axisTitleFontSize;
	}

	public HTML getValuesColor() {
		return valuesColor;
	}

	public ListBox getValuesFontSize() {
		return valuesFontSize;
	}

	public ListBox getValuesFontFamily() {
		return valuesFontFamily;
	}

	public ListBox getLabelInclination() {
		return labelInclination;
	}

	public CheckBox getShowLegend() {
		return showLegend;
	}
	
	public CheckBox getShowBorder() {
		return showBorder;
	}
	

	public ListBox getAxisPosition() {
		return axisPosition;
	}

	public TextField<String> getAxisFromValue() {
		return axisFromValue;
	}

	public TextField<String> getAxisToValue() {
		return axisToValue;
	}

	public Long getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(Long datasetID) {
		this.datasetID = datasetID;
	}

	public ListBox getAxisLineWidth() {
		return axisLineWidth;
	}

	public Map<TextField<String>, HTML> getSeriesColorMap() {
		return seriesColorMap;
	}

	public ListBox getAxisLineType() {
		return axisLineType;
	}

	public CheckBox getLegendHorizontal() {
		return legendHorizontal;
	}

	public TextField<String> getLabelsDistanceFromAxis() {
		return labelsDistanceFromAxis;
	}

	public List<HorizontalPanel> getAxisShowDatePanels() {
		return axisShowDatePanels;
	}

	public CheckBox getAxisShowDates() {
		return axisShowDates;
	}

	public HTML getAxisShowDatesFontColor() {
		return axisShowDatesFontColor;
	}

	public ListBox getAxisShowDatesFontSize() {
		return axisShowDatesFontSize;
	}

	public ListBox getAxisShowDatesFontStyle() {
		return axisShowDatesFontStyle;
	}

	public CheckBox getAxisShowValues() {
		return axisShowValues;
	}

	public HTML getAxisShowValuesFontColor() {
		return axisShowValuesFontColor;
	}

	public ListBox getAxisShowValuesFontSize() {
		return axisShowValuesFontSize;
	}

	public ListBox getAxisShowValuesFontStyle() {
		return axisShowValuesFontStyle;
	}

	public List<HorizontalPanel> getAxisShowValuePanels() {
		return axisShowValuePanels;
	}

	public Button getAxisTitleButton() {
		return axisTitleButton;
	}

	public Button getAxisValuesButton() {
		return axisValuesButton;
	}

	public Button getAxisButton() {
		return axisButton;
	}

	public Map<TextField<String>, String> getHeaderYCodeMap() {
		return headerYCodeMap;
	}

	public CheckBox getxAxisEquidistantDates() {
		return xAxisEquidistantDates;
	}

	public CheckBox getyAxisEquidistantDates() {
		return yAxisEquidistantDates;
	}

	public Html getxAxisNumberOfLabels() {
		return xAxisNumberOfLabels;
	}

	public ListBox getDatesFormat() {
		return datesFormat;
	}

	public CheckBox getStacked() {
		return stacked;
	}

	public void setStacked(CheckBox stacked) {
		this.stacked = stacked;
	}

	public ListBox getPointType() {
		return pointType;
	}
	
}