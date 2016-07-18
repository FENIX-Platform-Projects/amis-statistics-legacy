package org.fao.fenix.web.modules.olap.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.olap.client.control.MTController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class MTLookAndFeelPanel extends MTStepPanel {

	private HorizontalPanel wrapper;

	private VerticalPanel leftWrapper;

	private VerticalPanel rightWrapper;

	private VerticalPanel contentsHeightsWrapper;

	private VerticalPanel contentsWidthsWrapper;

	private int SPACING = 5;

	private String TITLE = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";

	private String HEIGHT = "520px";

	private String SIZE_HEIGHT = "490px";

	private String BUTTON_WIDTH = "175px";

	private String FIELD_WIDTH = "600px";

	private String WRAPPER_WIDTH = "625px";

	private String SIZE_WIDTH = "435px";

	private Button generalButton;

	private Button titleButton;

	private Button columnsButton;

	private Button rowsButton;

	private Button subColumnsButton;

	private Button subRowsButton;

	private Button notesButton;

	private Button contentButton;

	private Button totalsButton;

	private Button rowLabelWidthButton;

	private Button subRowLabelWidthButton;

	private Button functionWidthButton;

	private Button functionHeigthButton;

	private Button contentsWidthButton;

	private Button contentsHeigthButton;

	private Button extendedFeatureButton;

	private ListBox fontFamily;

	private ListBox fontSize;

	private ListBox decimals;

	private ListBox decimalsForTotals;

	private ListBox decimalsForVariation;

	private TextField<String> titleLabel;

	private HTML titleColor;
	
	
	private String xLabel;
	
	private String yLabel;
	
	private String wLabel;
	
	private String zLabel;
	

	private TextField<String> xLabelShowTitle;

	private HTML xLabelColor;
	
	private TextField<String> yLabelShowTitle;

	private HTML yLabelColor;
	
	private TextField<String> zLabelShowTitle;

	private HTML zLabelColor;
	
	private TextField<String> wLabelShowTitle;

	private HTML wLabelColor;
	
	private TextField<String> notesLabel;

	private HTML notesColor;

	private HTML columnBackgroundColor;

	private HTML columnFontColor;

	private HTML subColumnBackgroundColor;

	private HTML subColumnFontColor;

	private HTML rowBackgroundColor;

	private HTML rowFontColor;

	private HTML subRowBackgroundColor;

	private HTML subRowFontColor;

	private HTML summaryBackgroundColor;

	private HTML summaryFontColor;
	
	private HTML shading1Color;

	private HTML shading2Color;
	
	private CheckBox showRowTotals;

	private CheckBox showColumnTotals;

	private CheckBox showRowVariation;

	private CheckBox showColumnVariation;

	private CheckBox showVariationArrows;

	private CheckBox showGridLine;

	private CheckBox showShading;

	private CheckBox showBorder;

	private CheckBox showSubColSubject;

	private CheckBox showColSubject;
	
	private CheckBox showSubRowSummary;

	private CheckBox showSubColSummary;
	 
	private CheckBox showRowSummary;

	private CheckBox showColSummary;
	
	private CheckBox  showRowSubject;

	private CheckBox showSubRowSubject;
	

	private HTML contentsBackgroundColor;

	private HTML contentsFontColor;

	private TextField<String> rowLabelWidth;

	private TextField<String> subRowLabelWidth;

	private TextField<String> functionWidth;

	private TextField<String> functionHeigth;

	private TextField<String> variationThreshold;

	private TextField<String> variationThresholdYellow;

	private TextField<String> variationThresholdRed;

	private Map<String, TextField<String>> labelWidthMap;

	private Map<String, TextField<String>> labelHeightMap;

	private ListBox datesFormat;

	public MTLookAndFeelPanel(String suggestion, String width) {
		super(suggestion, width);
		showRowVariation = new CheckBox();
		showColumnVariation = new CheckBox();
		showVariationArrows = new CheckBox();
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
		contentsHeightsWrapper = new VerticalPanel();
		// contentsHeightsWrapper.setSpacing(SPACING);
		contentsHeightsWrapper.setHeight(SIZE_HEIGHT);
		contentsHeightsWrapper.setWidth(SIZE_WIDTH);
		contentsHeightsWrapper.setScrollMode(Scroll.AUTO);
		contentsHeightsWrapper.setLayout(new FillLayout());
		contentsWidthsWrapper = new VerticalPanel();
		// contentsWidthsWrapper.setSpacing(SPACING);
		contentsWidthsWrapper.setHeight(SIZE_HEIGHT);
		contentsWidthsWrapper.setWidth(SIZE_WIDTH);
		contentsWidthsWrapper.setScrollMode(Scroll.AUTO);
		contentsWidthsWrapper.setLayout(new FillLayout());
		generalButton = new Button("General");
		generalButton.setWidth(BUTTON_WIDTH);
		titleButton = new Button("Title");
		titleButton.setWidth(BUTTON_WIDTH);
		columnsButton = new Button("Columns");
		columnsButton.setWidth(BUTTON_WIDTH);
		rowsButton = new Button("Rows");
		rowsButton.setWidth(BUTTON_WIDTH);
		subColumnsButton = new Button("Sub-Columns");
		subColumnsButton.setWidth(BUTTON_WIDTH);
		subRowsButton = new Button("Sub-Rows");
		subRowsButton.setWidth(BUTTON_WIDTH);
		notesButton = new Button("Notes");
		notesButton.setWidth(BUTTON_WIDTH);
		totalsButton = new Button("Totals");
		totalsButton.setWidth(BUTTON_WIDTH);
		contentButton = new Button("Contents");
		contentButton.setWidth(BUTTON_WIDTH);
		rowLabelWidthButton = new Button("Row Label Width");
		rowLabelWidthButton.setWidth(BUTTON_WIDTH);
		subRowLabelWidthButton = new Button("Sub-Row Label Width");
		subRowLabelWidthButton.setWidth(BUTTON_WIDTH);
		functionWidthButton = new Button("Function Width");
		functionWidthButton.setWidth(BUTTON_WIDTH);
		functionHeigthButton = new Button("Function Height");
		functionHeigthButton.setWidth(BUTTON_WIDTH);
		contentsWidthButton = new Button("Contents Widths");
		contentsWidthButton.setWidth(BUTTON_WIDTH);
		contentsHeigthButton = new Button("Contents Heights");
		contentsHeigthButton.setWidth(BUTTON_WIDTH);
		extendedFeatureButton = new Button("Extended Feature");
		extendedFeatureButton.setWidth(BUTTON_WIDTH);

		fontFamily = new ListBox();
		fontSize = new ListBox();
		MTController.fillFontFamilyList(fontFamily);
		MTController.fillFontSizeList(fontSize);
		decimals = new ListBox();
		MTController.fillDecimalsList(decimals);
		decimalsForTotals = new ListBox();
		MTController.fillDecimalsList(decimalsForTotals);
		decimalsForVariation = new ListBox();
		MTController.fillDecimalsList(decimalsForVariation);
		
		xLabelShowTitle = new TextField<String>();
		
		xLabelColor = new HTML(
		"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		xLabelColor.addClickHandler(MTController.colorPicker(xLabelColor));

yLabelShowTitle = new TextField<String>();
		
		yLabelColor = new HTML(
		"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		yLabelColor.addClickHandler(MTController.colorPicker(yLabelColor));
		
zLabelShowTitle = new TextField<String>();
		
		zLabelColor = new HTML(
		"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		zLabelColor.addClickHandler(MTController.colorPicker(zLabelColor));
		
wLabelShowTitle = new TextField<String>();
		
		wLabelColor = new HTML(
		"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		wLabelColor.addClickHandler(MTController.colorPicker(wLabelColor));
		
		titleLabel = new TextField<String>();
		titleColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		titleColor.addClickHandler(MTController.colorPicker(titleColor));
		notesLabel = new TextField<String>();
		notesColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		notesColor.addClickHandler(MTController.colorPicker(notesColor));
		columnBackgroundColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		columnBackgroundColor.addClickHandler(MTController
				.colorPicker(columnBackgroundColor));
		columnFontColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		columnFontColor.addClickHandler(MTController
				.colorPicker(columnFontColor));
		rowBackgroundColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		rowBackgroundColor.addClickHandler(MTController
				.colorPicker(rowBackgroundColor));
		rowFontColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		rowFontColor.addClickHandler(MTController.colorPicker(rowFontColor));
		subColumnBackgroundColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		subColumnBackgroundColor.addClickHandler(MTController
				.colorPicker(subColumnBackgroundColor));
		subColumnFontColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		subColumnFontColor.addClickHandler(MTController
				.colorPicker(subColumnFontColor));
		subRowBackgroundColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		subRowBackgroundColor.addClickHandler(MTController
				.colorPicker(subRowBackgroundColor));
		subRowFontColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		subRowFontColor.addClickHandler(MTController
				.colorPicker(subRowFontColor));
		showRowTotals = new CheckBox();
		showColumnTotals = new CheckBox();

		showGridLine = new CheckBox();
		showGridLine.setValue(true);
		showShading = new CheckBox();
		showShading.setValue(true);
		showBorder = new CheckBox();
		showBorder.setValue(true);
		
		showRowSubject = new CheckBox();
		showRowSubject.setValue(true);
		showColSubject = new CheckBox();
		showColSubject.setValue(true);
		showSubRowSubject = new CheckBox();
		showSubRowSubject.setValue(true);
		showSubColSubject = new CheckBox();
		showSubColSubject.setValue(true);
		
		showRowSummary = new CheckBox();
		showRowSummary.setValue(true);
		showColSummary = new CheckBox();
		showColSummary.setValue(true);
		showSubRowSummary = new CheckBox();
		showSubRowSummary.setValue(true);
		showSubColSummary = new CheckBox();
		showSubColSummary.setValue(true);
	
		summaryBackgroundColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		summaryBackgroundColor.addClickHandler(MTController
				.colorPicker(summaryBackgroundColor));
		summaryFontColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		summaryFontColor.addClickHandler(MTController
				.colorPicker(summaryFontColor));
		
		shading1Color = new HTML("<div align='center' style='border: 4px black solid; background-color: #FFCCCC; color: #FFCCCC; font-weight: bold; font-style: italic;'>#FFCCCC</div>");
		shading1Color.addClickHandler(MTController.colorPicker(shading1Color));
		
		shading2Color = new HTML("<div align='center' style='border: 4px black solid; background-color: #CCCCFF; color: #CCCCFF; font-weight: bold; font-style: italic;'>#CCCCFF</div>");
		shading2Color.addClickHandler(MTController.colorPicker(shading2Color));
		
		contentsBackgroundColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #FFFFFF; color: #FFFFFF; font-weight: bold; font-style: italic;'>#FFFFFF</div>");
		contentsBackgroundColor.addClickHandler(MTController
				.colorPicker(contentsBackgroundColor));
		contentsFontColor = new HTML(
				"<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		contentsFontColor.addClickHandler(MTController
				.colorPicker(contentsFontColor));
		rowLabelWidth = new TextField<String>();
		subRowLabelWidth = new TextField<String>();
		functionWidth = new TextField<String>();
		functionHeigth = new TextField<String>();
		variationThreshold = new TextField<String>();
		variationThresholdYellow = new TextField<String>();
		variationThresholdRed = new TextField<String>();
		labelWidthMap = new HashMap<String, TextField<String>>();
		labelHeightMap = new HashMap<String, TextField<String>>();
		datesFormat = new ListBox();
		ChartDesignerController.fillDatesFormat(datesFormat);
		this.getLayoutContainer().add(buildWrapperPanel());
	}

	private HorizontalPanel buildWrapperPanel() {
		wrapper.add(buildLeftWrapper());
		wrapper.add(rightWrapper);
		rightWrapper.removeAll();
		rightWrapper.add(buildGeneralPanel());
		rightWrapper.getLayout().layout();
		return wrapper;
	}

	private VerticalPanel buildLeftWrapper() {
		leftWrapper.add(generalButton);
		leftWrapper.add(titleButton);
		leftWrapper.add(notesButton);
		leftWrapper.add(columnsButton);
		leftWrapper.add(rowsButton);
		leftWrapper.add(subColumnsButton);
		leftWrapper.add(subRowsButton);
		leftWrapper.add(totalsButton);
		leftWrapper.add(contentButton);
		leftWrapper.add(contentsWidthButton);
		leftWrapper.add(contentsHeigthButton);
		leftWrapper.add(extendedFeatureButton);

		generalButton.addSelectionListener(MTController.showSettingsPanel(
				rightWrapper, buildGeneralPanel()));
		notesButton.addSelectionListener(MTController.showSettingsPanel(
				rightWrapper, buildNotesPanel()));
		titleButton.addSelectionListener(MTController.showSettingsPanel(
				rightWrapper, buildTitlePanel()));
		columnsButton.addSelectionListener(MTController.showSettingsPanel(
				rightWrapper, buildColumnsPanel()));
		rowsButton.addSelectionListener(MTController.showSettingsPanel(
				rightWrapper, buildRowsPanel()));
		subColumnsButton.addSelectionListener(MTController.showSettingsPanel(
				rightWrapper, buildSubColumnsPanel()));
		subRowsButton.addSelectionListener(MTController.showSettingsPanel(
				rightWrapper, buildSubRowsPanel()));
		totalsButton.addSelectionListener(MTController.showSettingsPanel(
				rightWrapper, buildSummaryPanel()));
		contentButton.addSelectionListener(MTController.showSettingsPanel(
				rightWrapper, buildContentsPanel()));
		contentsWidthButton.addSelectionListener(MTController
				.showSettingsPanel(rightWrapper, buildContentWidthsPanel()));
		contentsHeigthButton.addSelectionListener(MTController
				.showSettingsPanel(rightWrapper, buildContentHeightsPanel()));
		extendedFeatureButton.addSelectionListener(MTController
				.showSettingsPanel(rightWrapper,
						buildExtendedFeaturePanel()));

		return leftWrapper;
	}

	private VerticalPanel buildContentWidthsPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.add(contentsWidthsWrapper);
		return p;
	}

	public void addContentWidths(List<String> labels) {
		contentsWidthsWrapper.removeAll();
		labelWidthMap.clear();
		for (String label : labels) {
			MTSizePanel s = new MTSizePanel("WIDTH");
			System.out.println("::: " + label + " 100 :::");
			contentsWidthsWrapper.add(s.build(label, "100"));
			contentsWidthsWrapper.getLayout().layout();
			labelWidthMap.put(label, s.getSize());
		}
	}

	public void addContentWidths(Map<String, String> labels) {
		contentsWidthsWrapper.removeAll();
		labelWidthMap.clear();
		System.out.println("WIDTHS: " + labels.keySet().size());
		for (String label : labels.keySet()) {
			System.out
					.println("\tADDING: " + label + " & " + labels.get(label));
			MTSizePanel s = new MTSizePanel("WIDTH");
			contentsWidthsWrapper.add(s.build(label, labels.get(label)));
			contentsWidthsWrapper.getLayout().layout();
			labelWidthMap.put(label, s.getSize());
		}
	}

	private VerticalPanel buildExtendedFeaturePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		showBorder.setBoxLabel("Show Border");
		showGridLine.setBoxLabel("Show Grid Line");
		showShading.setBoxLabel("Show Shading");
		
		showRowSummary.setBoxLabel("Show Row Summary");
		showColSummary.setBoxLabel("Show Column Summary");
		showSubRowSummary.setBoxLabel("Show Sub-Row Summary");
		showSubColSummary.setBoxLabel("Show Sub-Column Summary");
		
		showRowSubject.setBoxLabel("Show Row Label");
		showColSubject.setBoxLabel("Show Column Label");
		showSubRowSubject.setBoxLabel("Show Sub-Row Label");
		showSubColSubject.setBoxLabel("Show Sub-Column Label");
		
		p.add(showBorder);
		p.add(showGridLine);
		p.add(showShading);
		
		Html lbl0sd1 = new Html(TITLE + "First Shading Color</div>");
		lbl0sd1.setWidth(FIELD_WIDTH);
		shading1Color.setWidth(FIELD_WIDTH);
		p.add(lbl0sd1);
		p.add(shading1Color);
		
		Html lbl0sd2 = new Html(TITLE + "Second Shading Color</div>");
		lbl0sd2.setWidth(FIELD_WIDTH);
		shading2Color.setWidth(FIELD_WIDTH);
		p.add(lbl0sd2);
		p.add(shading2Color);
	
		
		p.add(showRowSummary);
		p.add(showColSummary);
		p.add(showSubRowSummary);
		p.add(showSubColSummary);
		
		p.add(showRowSubject);
		p.add(showColSubject);
		p.add(showSubRowSubject);
		p.add(showSubColSubject);
		
		
		//
		Html lbl01 = new Html(TITLE + "Column Label</div>");
		Html lbl02 = new Html(TITLE + "Column Label Color</div>");
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		xLabelShowTitle.setWidth(FIELD_WIDTH);
		xLabelShowTitle.setValue("");
		xLabelColor.setWidth(FIELD_WIDTH);

		p.add(lbl01);
		p.add(xLabelShowTitle);
		//p.add(lbl02);
		//p.add(xLabelColor);

		//
		Html lbl03 = new Html(TITLE + "Sub-Column Label</div>");
		Html lbl04 = new Html(TITLE + "Sub-Column Label Color</div>");
		lbl03.setWidth(FIELD_WIDTH);
		lbl04.setWidth(FIELD_WIDTH);
		yLabelShowTitle.setWidth(FIELD_WIDTH);
		yLabelShowTitle.setValue("");
		yLabelColor.setWidth(FIELD_WIDTH);

		p.add(lbl03);
		p.add(yLabelShowTitle);
		//p.add(lbl04);
		//p.add(yLabelColor);

		//
		Html lbl05 = new Html(TITLE + "Row Label</div>");
		Html lbl06 = new Html(TITLE + "Row Label Color</div>");
		lbl05.setWidth(FIELD_WIDTH);
		lbl06.setWidth(FIELD_WIDTH);
		zLabelShowTitle.setWidth(FIELD_WIDTH);
		zLabelShowTitle.setValue("");
		zLabelColor.setWidth(FIELD_WIDTH);

		p.add(lbl05);
		p.add(zLabelShowTitle);
		//p.add(lbl06);
		//p.add(zLabelColor);


		//
		Html lbl07 = new Html(TITLE + "Sub-Row Label</div>");
		Html lbl08 = new Html(TITLE + "Sub-Row Label Color</div>");
		lbl07.setWidth(FIELD_WIDTH);
		lbl08.setWidth(FIELD_WIDTH);
		wLabelShowTitle.setWidth(FIELD_WIDTH);
		wLabelShowTitle.setValue("");
		wLabelColor.setWidth(FIELD_WIDTH);

		p.add(lbl07);
		p.add(wLabelShowTitle);
		//p.add(lbl08);
		//p.add(wLabelColor);
		//

		return p;
	}

	private VerticalPanel buildContentHeightsPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.add(contentsHeightsWrapper);
		return p;
	}

	public void addContentHeights(List<String> labels) {
		contentsHeightsWrapper.removeAll();
		labelHeightMap.clear();
		for (String label : labels) {
			MTSizePanel s = new MTSizePanel("HEIGHT");
			contentsHeightsWrapper.add(s.build(label, "25"));
			contentsHeightsWrapper.getLayout().layout();
			labelHeightMap.put(label, s.getSize());
		}
	}

	public void addContentHeights(Map<String, String> labels) {
		contentsHeightsWrapper.removeAll();
		labelHeightMap.clear();
		for (String label : labels.keySet()) {
			MTSizePanel s = new MTSizePanel("HEIGHT");
			contentsHeightsWrapper.add(s.build(label, labels.get(label)));
			contentsHeightsWrapper.getLayout().layout();
			labelHeightMap.put(label, s.getSize());
		}
	}

	private VerticalPanel buildGeneralPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lbl01 = new Html(TITLE + "Font Family</div>");
		Html lbl02 = new Html(TITLE + "Font Size</div>");
		Html lbl03 = new Html(TITLE + "Decimal Places</div>");
		Html lbl04 = new Html(TITLE + "Dates Format</div>");
		Html lbl05 = new Html(TITLE + "Decimal Places <i>(totals)</i></div>");
		Html lbl06 = new Html(TITLE
				+ "Decimal Places <i>(percentage variation)</i></div>");
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		lbl03.setWidth(FIELD_WIDTH);
		lbl04.setWidth(FIELD_WIDTH);
		lbl05.setWidth(FIELD_WIDTH);
		lbl06.setWidth(FIELD_WIDTH);
		fontFamily.setWidth(FIELD_WIDTH);
		fontSize.setWidth(FIELD_WIDTH);
		decimals.setWidth(FIELD_WIDTH);
		datesFormat.setWidth(FIELD_WIDTH);
		decimalsForTotals.setWidth(FIELD_WIDTH);
		decimalsForVariation.setWidth(FIELD_WIDTH);
		p.add(lbl01);
		p.add(fontFamily);
		p.add(lbl02);
		p.add(fontSize);
		p.add(lbl03);
		p.add(decimals);
		p.add(lbl05);
		p.add(decimalsForTotals);
		p.add(lbl06);
		p.add(decimalsForVariation);
		p.add(lbl04);
		p.add(datesFormat);
		return p;
	}

	private VerticalPanel buildTitlePanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lbl01 = new Html(TITLE + "Title</div>");
		Html lbl02 = new Html(TITLE + "Title Color</div>");
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		titleLabel.setWidth(FIELD_WIDTH);
		titleLabel.setEmptyText("e.g. My First Multidimensional Table");
		titleLabel.setValue(" ");
		titleColor.setWidth(FIELD_WIDTH);
		;
		p.add(lbl01);
		p.add(titleLabel);
		p.add(lbl02);
		p.add(titleColor);
		return p;
	}

	private VerticalPanel buildNotesPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lbl01 = new Html(TITLE + "Notes</div>");
		Html lbl02 = new Html(TITLE + "Notes Color</div>");
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		notesLabel.setWidth(FIELD_WIDTH);
		notesLabel.setEmptyText("e.g. My First Multidimensional Table");
		notesLabel.setValue(" ");
		notesColor.setWidth(FIELD_WIDTH);
		;
		p.add(lbl01);
		p.add(notesLabel);
		p.add(lbl02);
		p.add(notesColor);
		return p;
	}

	private VerticalPanel buildColumnsPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lbl01 = new Html(TITLE + "Columns Background Color</div>");
		Html lbl02 = new Html(TITLE + "Columns Font Color</div>");
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		columnBackgroundColor.setWidth(FIELD_WIDTH);
		columnFontColor.setWidth(FIELD_WIDTH);
		;
		p.add(lbl01);
		p.add(columnBackgroundColor);
		p.add(lbl02);
		p.add(columnFontColor);
		return p;
	}

	private VerticalPanel buildSubColumnsPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lbl01 = new Html(TITLE + "Sub-Columns Background Color</div>");
		Html lbl02 = new Html(TITLE + "Sub-Columns Font Color</div>");
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		subColumnBackgroundColor.setWidth(FIELD_WIDTH);
		subColumnFontColor.setWidth(FIELD_WIDTH);
		;
		p.add(lbl01);
		p.add(subColumnBackgroundColor);
		p.add(lbl02);
		p.add(subColumnFontColor);
		return p;
	}

	private VerticalPanel buildRowsPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lbl01 = new Html(TITLE + "Rows Background Color</div>");
		Html lbl02 = new Html(TITLE + "Rows Font Color</div>");
		Html lbl03 = new Html(TITLE + "Row Label Width</div>");
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		lbl03.setWidth(FIELD_WIDTH);
		rowLabelWidth.setWidth(FIELD_WIDTH);
		rowLabelWidth.setValue("75");
		System.out.println("::: rowLabelWidth ::: " + rowLabelWidth.getValue());
		rowBackgroundColor.setWidth(FIELD_WIDTH);
		rowFontColor.setWidth(FIELD_WIDTH);
		;
		p.add(lbl01);
		p.add(rowBackgroundColor);
		p.add(lbl02);
		p.add(rowFontColor);
		p.add(lbl03);
		p.add(rowLabelWidth);
		return p;
	}

	private VerticalPanel buildSubRowsPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lbl01 = new Html(TITLE + "Sub-Rows Background Color</div>");
		Html lbl02 = new Html(TITLE + "Sub-Rows Font Color</div>");
		Html lbl03 = new Html(TITLE + "Sub-Row Label Width</div>");
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		lbl03.setWidth(FIELD_WIDTH);
		subRowLabelWidth.setWidth(FIELD_WIDTH);
		subRowLabelWidth.setValue("75");
		subRowBackgroundColor.setWidth(FIELD_WIDTH);
		subRowFontColor.setWidth(FIELD_WIDTH);
		;
		p.add(lbl01);
		p.add(subRowBackgroundColor);
		p.add(lbl02);
		p.add(subRowFontColor);
		p.add(lbl03);
		p.add(subRowLabelWidth);
		return p;
	}

	private VerticalPanel buildSummaryPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lbl01 = new Html(TITLE + "Summary Background Color</div>");
		Html lbl02 = new Html(TITLE + "Summary Font Color</div>");
		Html lbl03 = new Html(TITLE + "Summary Label Width</div>");
		Html lbl04 = new Html(TITLE + "Variation Threshold <i>(%)</i></div>");
		Html lbl05 = new Html(
				TITLE
						+ "<i style='color: #CA1616'>Percentage Variation functionalities are <b>NOT</b> (yet) available for tables with 4 dimensions</i></div>");
		Html lbl06 = new Html(
				TITLE
						+ "Minimum/Medium Variation Threshold <i>(green arrow for values below the treshold, yellow arrow for values above)</i></div>");
		Html lbl07 = new Html(
				TITLE
						+ "Medium/Maximum Variation Threshold <i>(yellow arrow for values below the treshold, red arrow for values above)</i></div>");
		lbl03.setWidth(FIELD_WIDTH);
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		lbl04.setWidth(FIELD_WIDTH);
		lbl05.setWidth(FIELD_WIDTH);
		showColumnTotals.setBoxLabel("Show Columns Totals");
		showRowTotals.setBoxLabel("Show Rows Totals");
		summaryBackgroundColor.setWidth(FIELD_WIDTH);
		summaryFontColor.setWidth(FIELD_WIDTH);
		functionWidth.setWidth(FIELD_WIDTH);
		functionWidth.setValue("75");
		showRowVariation.setBoxLabel("Show Percentage Variation on Rows");
		showVariationArrows.setBoxLabel("Show Variation Arrows");
		showVariationArrows.setValue(true);
		showColumnVariation.setBoxLabel("Show Percentage Variation on Columns");
		variationThreshold.setWidth(FIELD_WIDTH);
		variationThreshold.setValue("5");
		variationThresholdYellow.setWidth(FIELD_WIDTH);
		variationThresholdYellow.setValue("5");
		variationThresholdRed.setWidth(FIELD_WIDTH);
		variationThresholdRed.setValue("10");
	//	p.add(showRowTotals);
	//	p.add(showColumnTotals);
		p.add(lbl05);
		p.add(showRowVariation);
		p.add(showColumnVariation);
		p.add(showVariationArrows);
		// p.add(lbl04);
		// p.add(variationThreshold);
		p.add(lbl06);
		p.add(variationThresholdYellow);
		p.add(lbl07);
		p.add(variationThresholdRed);
		p.add(lbl01);
		p.add(summaryBackgroundColor);
		p.add(lbl02);
		p.add(summaryFontColor);
		p.add(lbl03);
		p.add(functionWidth);
		return p;
	}

	private VerticalPanel buildContentsPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html lbl01 = new Html(TITLE + "Contents Background Color</div>");
		Html lbl02 = new Html(TITLE + "Contents Font Color</div>");
		lbl01.setWidth(FIELD_WIDTH);
		lbl02.setWidth(FIELD_WIDTH);
		contentsBackgroundColor.setWidth(FIELD_WIDTH);
		contentsFontColor.setWidth(FIELD_WIDTH);
		;
		p.add(lbl01);
		p.add(contentsBackgroundColor);
		p.add(lbl02);
		p.add(contentsFontColor);
		return p;
	}

	public ListBox getFontFamily() {
		return fontFamily;
	}

	public ListBox getFontSize() {
		return fontSize;
	}

	public ListBox getDecimals() {
		return decimals;
	}

	public TextField<String> getTitleLabel() {
		return titleLabel;
	}

	public HTML getTitleColor() {
		return titleColor;
	}

	public HTML getShading1Color() {
		return shading1Color;
	}

	public HTML getShading2Color() {
		return shading2Color;
	}

	public HTML getColumnBackgroundColor() {
		return columnBackgroundColor;
	}

	public HTML getColumnFontColor() {
		return columnFontColor;
	}

	public HTML getSubColumnBackgroundColor() {
		return subColumnBackgroundColor;
	}

	public HTML getSubColumnFontColor() {
		return subColumnFontColor;
	}

	public HTML getRowBackgroundColor() {
		return rowBackgroundColor;
	}

	public HTML getRowFontColor() {
		return rowFontColor;
	}

	public HTML getSubRowBackgroundColor() {
		return subRowBackgroundColor;
	}

	public HTML getSubRowFontColor() {
		return subRowFontColor;
	}

	public HTML getSummaryBackgroundColor() {
		return summaryBackgroundColor;
	}

	public HTML getSummaryFontColor() {
		return summaryFontColor;
	}

	public CheckBox getShowRowTotals() {
		return showRowSummary;
		//return showRowTotals;
	}

	public CheckBox getShowColumnTotals() {
		return showColSummary;
		//return showColumnTotals;
	}

	public CheckBox getShowGridLine() {
		return showGridLine;
	}

	public CheckBox getShowShading() {
		return showShading;
	}
	
	public CheckBox getShowSubRowSummary() {
		return showSubRowSummary;
	}
	public CheckBox getShowSubColSummary() {
		return showSubColSummary;
	}
	
	//
	public CheckBox getShowBorder() {
		return showBorder;
	}

	public CheckBox getShowSubColSubject() {
		return showSubColSubject;
	}

	public CheckBox getShowColSubject() {
		return showColSubject;
	}
	
	public CheckBox getShowRowSummary() {
		return showRowSummary;
	}

	public CheckBox getShowColSummary() {
		return showColSummary;
	}

	public CheckBox getShowRowSubject() {
		return showRowSubject;
	}

	public CheckBox getShowSubRowSubject() {
		return showSubRowSubject;
	}

	public HTML getContentsBackgroundColor() {
		return contentsBackgroundColor;
	}

	public HTML getContentsFontColor() {
		return contentsFontColor;
	}

	public TextField<String> getRowLabelWidth() {
		return rowLabelWidth;
	}

	public TextField<String> getSubRowLabelWidth() {
		return subRowLabelWidth;
	}

	public TextField<String> getFunctionWidth() {
		return functionWidth;
	}

	public TextField<String> getFunctionHeigth() {
		return functionHeigth;
	}

	public Map<String, TextField<String>> getLabelWidthMap() {
		return labelWidthMap;
	}

	public Map<String, TextField<String>> getLabelHeightMap() {
		return labelHeightMap;
	}

	public ListBox getDatesFormat() {
		return datesFormat;
	}

	public CheckBox getShowRowVariation() {
		return showRowVariation;
	}

	public CheckBox getShowColumnVariation() {
		return showColumnVariation;
	}

	public TextField<String> getNotesLabel() {
		return notesLabel;
	}

	public HTML getNotesColor() {
		return notesColor;
	}

	public TextField<String> getVariationThreshold() {
		return variationThreshold;
	}

	public CheckBox getShowVariationArrows() {
		return showVariationArrows;
	}

	public ListBox getDecimalsForTotals() {
		return decimalsForTotals;
	}

	public ListBox getDecimalsForVariation() {
		return decimalsForVariation;
	}

	public TextField<String> getVariationThresholdYellow() {
		return variationThresholdYellow;
	}

	public TextField<String> getVariationThresholdRed() {
		return variationThresholdRed;
	}

	public TextField<String> getxLabelShowTitle() {
		return xLabelShowTitle;
	}

	public HTML getxLabelColor() {
		return xLabelColor;
	}

	public TextField<String> getyLabelShowTitle() {
		return yLabelShowTitle;
	}

	public HTML getyLabelColor() {
		return yLabelColor;
	}

	public TextField<String> getzLabelShowTitle() {
		return zLabelShowTitle;
	}

	public HTML getzLabelColor() {
		return zLabelColor;
	}

	public TextField<String> getwLabelShowTitle() {
		return wLabelShowTitle;
	}

	public HTML getwLabelColor() {
		return wLabelColor;
	}

	public String getxLabel() {
		return xLabel;
	}

	public void setxLabel(String xLabel) {
		this.xLabel = xLabel;
	}

	public String getyLabel() {
		return yLabel;
	}

	public void setyLabel(String yLabel) {
		this.yLabel = yLabel;
	}

	public String getwLabel() {
		return wLabel;
	}

	public void setwLabel(String wLabel) {
		this.wLabel = wLabel;
	}

	public String getzLabel() {
		return zLabel;
	}

	public void setzLabel(String zLabel) {
		this.zLabel = zLabel;
	}
	
}