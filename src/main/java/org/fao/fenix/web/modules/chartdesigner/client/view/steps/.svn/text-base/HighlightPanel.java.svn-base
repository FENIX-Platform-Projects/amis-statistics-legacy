package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class HighlightPanel {

	private String FIELD_WIDTH = "180px";

	private String FIELD_SET_WIDTH = "200px";
	
	private String TITLE = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";
	
	private FieldSet fs;
	
	private Long datasetID;
	
	private String datasetTitle;
	
	private CheckBox showHighlight;
	
	private HTML color;
	
	private ListBox fontStyle;
	
	private ListBox fontSize;
	
	private ListBox plotSymbol;
	
	private String highlightType;
	
	public HighlightPanel(Long datasetID, String datasetTitle, String highlightType) {
		this.setDatasetID(datasetID);
		this.setDatasetTitle(datasetTitle);
		this.setHighlightType(highlightType);
		showHighlight = new CheckBox();
		String randomHEX = ChartDesignerController.createRandomHex(datasetID);
		color = new HTML("<div align='center' style='border: 4px black solid; background-color: " + randomHEX + "; color: " + randomHEX + "; font-weight: bold; font-style: italic;'>" + randomHEX + "</div>");
		fontStyle = new ListBox();
		fontSize = new ListBox();
		plotSymbol = new ListBox();
		ChartDesignerController.fillFontFamilyList(fontStyle);
		fontStyle.setSelectedIndex(1);
		ChartDesignerController.fillFontSizeList(fontSize);
		fontSize.setSelectedIndex(7);
		ChartDesignerController.fillPlotSymbolList(plotSymbol);
	}
	
	public FieldSet build(String highlightTitle) {
		fs = new FieldSet();
		fs.setCheckboxToggle(true);
		fs.setHeading("Show " + highlightTitle + " for:<br>" + this.getDatasetTitle());
		fs.setWidth(FIELD_SET_WIDTH);
		fs.setBorders(true);
		fs.collapse();
		Html fontColorLabel = new Html(TITLE + "Font Color</div>");
		fontColorLabel.setWidth(FIELD_WIDTH);
		Html fontSizeLabel = new Html(TITLE + "Font Size</div>");
		fontSizeLabel.setWidth(FIELD_WIDTH);
		Html fontStyleLabel = new Html(TITLE + "Font Style</div>");
		fontStyleLabel.setWidth(FIELD_WIDTH);
		Html plotSymbolLabel = new Html(TITLE + "Plot Symbol</div>");
		plotSymbolLabel.setWidth(FIELD_WIDTH);
		showHighlight.setBoxLabel("Show " + highlightTitle);
		color.setWidth(FIELD_WIDTH);
		color.addClickHandler(ChartDesignerController.colorPicker(color));
		fontStyle.setWidth(FIELD_WIDTH);
		fontSize.setWidth(FIELD_WIDTH);
		plotSymbol.setWidth(FIELD_WIDTH);
		fs.add(showHighlight);
		fs.add(fontColorLabel);
		fs.add(color);
		fs.add(fontSizeLabel);
		fs.add(fontSize);
		fs.add(fontStyleLabel);
		fs.add(fontStyle);
//		fs.add(plotSymbolLabel);
//		fs.add(plotSymbol);
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

	public FieldSet getFs() {
		return fs;
	}

	public CheckBox getShowHighlight() {
		return showHighlight;
	}

	public HTML getColor() {
		return color;
	}

	public ListBox getFontStyle() {
		return fontStyle;
	}

	public ListBox getFontSize() {
		return fontSize;
	}

	public ListBox getPlotSymbol() {
		return plotSymbol;
	}

	public String getHighlightType() {
		return highlightType;
	}

	public void setHighlightType(String highlightType) {
		this.highlightType = highlightType;
	}
	
}