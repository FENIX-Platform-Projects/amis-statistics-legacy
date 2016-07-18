package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class AxisFormatPanel {
	
	private TextField<String> yMin;
	
	private TextField<String> yMax;
	
	private TextField<String> title;

	private ListBox fontFamily;

	private ListBox fontSize;
	
	private ListBox valuesFontFamily;

	private ListBox valuesFontSize;
	
	private ListBox scaleType;

	private HTML fontColor;
	
	private HTML valuesFontColor;

	private HTML axisColor;

	private HTML ticksColor;

	private TextField<String> step;
	
	private ListBox labelOrientation;
	
	private ListBox axisPosition;
	
	private String FIELD_WIDTH = "180px";

	private String FIELD_SET_WIDTH = "200px";
	
	private String TITLE = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";
	
	private FieldSet fs;
	
	private Long datasetID;
	
	private String datasetTitle;
	
	private ListBox lineType;
	
	private ListBox lineWidth;
	
	public AxisFormatPanel(Long datasetID, String datasetTitle) {
		this.setDatasetID(datasetID);
		this.setDatasetTitle(datasetTitle);
		yMin = new TextField<String>();
		yMax = new TextField<String>();
		title = new TextField<String>();
		fontFamily = new ListBox();
		fontSize = new ListBox();
		valuesFontFamily = new ListBox();
		valuesFontSize = new ListBox();
		axisPosition = new ListBox();
		scaleType = new ListBox();
		fontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		axisColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		ticksColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		valuesFontColor = new HTML("<div align='center' style='border: 4px black solid; background-color: #000000; color: #000000; font-weight: bold; font-style: italic;'>#000000</div>");
		step = new TextField<String>();
		labelOrientation = new ListBox();
		fs = new FieldSet();
		ChartDesignerController.fillFontFamilyList(fontFamily);
		ChartDesignerController.fillFontSizeList(fontSize);
		ChartDesignerController.fillFontFamilyList(valuesFontFamily);
		ChartDesignerController.fillFontSizeList(valuesFontSize);
		fontSize.setSelectedIndex(10);
		valuesFontSize.setSelectedIndex(7);
		step.setValue("10");
//		ChartDesignerController.calculateStep(step, this.getDatasetID());
		lineType = new ListBox();
		lineWidth = new ListBox();
	}
	
	public FieldSet build() {
		fs = new FieldSet();
		fs.setCheckboxToggle(true);
		fs.setHeading("Y-Axis for Dataset:<br><i>" + this.getDatasetTitle() + "</i>");
		fs.setWidth(FIELD_SET_WIDTH);
		fs.setBorders(true);
		fs.collapse();
		Html titleFontColorLabel = new Html(TITLE + "Axis Label Font Color</div>");
		titleFontColorLabel.setWidth(FIELD_WIDTH);
		Html valuesFontColorLabel = new Html(TITLE + "Values Font Color</div>");
		valuesFontColorLabel.setWidth(FIELD_WIDTH);
		Html axisColorLabel = new Html(TITLE + "Axis Color</div>");
		axisColorLabel.setWidth(FIELD_WIDTH);
		Html axisTicksColorLabel = new Html(TITLE + "Axis Ticks Color</div>");
		axisTicksColorLabel.setWidth(FIELD_WIDTH);
		Html titleFontFamilyLabel = new Html(TITLE + "Axis Label Font Style</div>");
		titleFontFamilyLabel.setWidth(FIELD_WIDTH);
		Html titleFontSizeLabel = new Html(TITLE + "Axis Label Font Size</div>");
		titleFontSizeLabel.setWidth(FIELD_WIDTH);
		Html valuesFontFamilyLabel = new Html(TITLE + "Values Font Style</div>");
		valuesFontFamilyLabel.setWidth(FIELD_WIDTH);
		Html valuesFontSizeLabel = new Html(TITLE + "Values Font Size</div>");
		valuesFontSizeLabel.setWidth(FIELD_WIDTH);
		Html labelInclinationLabel = new Html(TITLE + "Label Orientation</div>");
		labelInclinationLabel.setWidth(FIELD_WIDTH);
		Html numberOfIntervalsLabel = new Html(TITLE + "Step</div>");
		numberOfIntervalsLabel.setWidth(FIELD_WIDTH);
		Html yMinLabel = new Html(TITLE + "Axis Minimum Value</div>");
		yMinLabel.setWidth(FIELD_WIDTH);
		Html yMaxLabel = new Html(TITLE + "Axis Maximum Value</div>");
		yMaxLabel.setWidth(FIELD_WIDTH);
		Html axisPositionLabel = new Html(TITLE + "Axis Position</div>");
		axisPositionLabel.setWidth(FIELD_WIDTH);
		Html scaleTypeLabel = new Html(TITLE + "Scale Type</div>");
		scaleTypeLabel.setWidth(FIELD_WIDTH);
		Html lineTypeLabel = new Html(TITLE + "Line Type</div>");
		lineTypeLabel.setWidth(FIELD_WIDTH);
		Html lineWidthLabel = new Html(TITLE + "Line width</div>");
		lineWidthLabel.setWidth(FIELD_WIDTH);
		lineType.setWidth(FIELD_WIDTH);
		lineType.addItem("Solid", "1");
		lineType.addItem("Dashed", "2");
		lineType.addItem("Dotted", "3");
		lineType.addItem("Dot Dash", "4");
		lineType.addItem("Long Dash", "5");
		lineType.addItem("Two Dash", "6");
		lineWidth.setWidth(FIELD_WIDTH);
		for (int i = 1 ; i < 20 ; i++)
			lineWidth.addItem(String.valueOf(i), String.valueOf(i));
		title.setWidth(FIELD_WIDTH);
		title.setValue("Axis Title");
		step.setWidth(FIELD_WIDTH);
		step.setValue("10");
		fontColor.setWidth(FIELD_WIDTH);
		fontColor.addClickHandler(ChartDesignerController.colorPicker(fontColor));
		axisColor.setWidth(FIELD_WIDTH);
		axisColor.addClickHandler(ChartDesignerController.colorPicker(axisColor));
		ticksColor.setWidth(FIELD_WIDTH);
		ticksColor.addClickHandler(ChartDesignerController.colorPicker(ticksColor));
		valuesFontColor.setWidth(FIELD_WIDTH);
		valuesFontColor.addClickHandler(ChartDesignerController.colorPicker(valuesFontColor));
		valuesFontFamily.setWidth(FIELD_WIDTH);
		valuesFontSize.setWidth(FIELD_WIDTH);
		fontFamily.setWidth(FIELD_WIDTH);
		fontSize.setWidth(FIELD_WIDTH);
		labelOrientation.setWidth(FIELD_WIDTH);
		labelOrientation.addItem("Horizontal", "1");
		labelOrientation.addItem("Vertical", "3");
		axisPosition.addItem("Left", "2");
		axisPosition.addItem("Right", "4");
		axisPosition.setWidth(FIELD_WIDTH);
		scaleType.setWidth(FIELD_WIDTH);
		scaleType.addItem("Linear", "LINEAR");
		scaleType.addItem("Logaritmic", "LOGARITMIC");
		yMin.setWidth(FIELD_WIDTH);
		yMin.setValue("0");
		yMin.setEmptyText("e.g. 0");
		yMax.setWidth(FIELD_WIDTH);
		yMax.setValue("");
		yMax.setEmptyText("e.g. 100");
		fs.add(title);
		fs.add(numberOfIntervalsLabel);
		fs.add(step);
		fs.add(lineTypeLabel);
		fs.add(lineType);
		fs.add(lineWidthLabel);
		fs.add(lineWidth);
		fs.add(axisTicksColorLabel);
		fs.add(ticksColor);
		fs.add(titleFontColorLabel);
		fs.add(fontColor);
		fs.add(titleFontFamilyLabel);
		fs.add(fontFamily);
		fs.add(titleFontSizeLabel);
		fs.add(fontSize);
		fs.add(valuesFontColorLabel);
		fs.add(valuesFontColor);
		fs.add(valuesFontSizeLabel);
		fs.add(valuesFontSize);
		fs.add(valuesFontFamilyLabel);
		fs.add(valuesFontFamily);
		fs.add(labelInclinationLabel);
		fs.add(labelOrientation);
		fs.add(yMinLabel);
		fs.add(yMin);
		fs.add(yMaxLabel);
		fs.add(yMax);
		fs.add(axisPositionLabel);
		fs.add(axisPosition);
		return fs;
	}

	public TextField<String> getTitle() {
		return title;
	}

	public TextField<String> getyMin() {
		return yMin;
	}

	public TextField<String> getyMax() {
		return yMax;
	}

	public ListBox getFontFamily() {
		return fontFamily;
	}

	public ListBox getFontSize() {
		return fontSize;
	}

	public HTML getFontColor() {
		return fontColor;
	}

	public HTML getAxisColor() {
		return axisColor;
	}

	public HTML getTicksColor() {
		return ticksColor;
	}

	public TextField<String> getStep() {
		return step;
	}

	public ListBox getLabelOrientation() {
		return labelOrientation;
	}

	public FieldSet getFieldSet() {
		return fs;
	}

	public Long getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(Long datasetID) {
		this.datasetID = datasetID;
	}

	public String getDatasetTitle() {
		return datasetTitle;
	}

	public void setDatasetTitle(String datasetTitle) {
		this.datasetTitle = datasetTitle;
	}

	public ListBox getAxisPosition() {
		return axisPosition;
	}

	public ListBox getScaleType() {
		return scaleType;
	}

	public ListBox getValuesFontFamily() {
		return valuesFontFamily;
	}

	public ListBox getValuesFontSize() {
		return valuesFontSize;
	}

	public HTML getValuesFontColor() {
		return valuesFontColor;
	}

	public ListBox getLineType() {
		return lineType;
	}

	public ListBox getLineWidth() {
		return lineWidth;
	}
	
}