package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;

public class SeriesFormatPanel {

	private FieldSet fs;
	
	private Long datasetID;
	
	private String datasetTitle;

	private String FIELD_SET_WIDTH = "200px";
	
	private String LABEL_WIDTH = "105px";
	
	private String HTML_WIDTH = "65px";
	
	private Map<String, HTML> headerHTMLMap;
	
	private Map<String, HTML> knownColorMap;
	
	public SeriesFormatPanel(Long datasetID, String datasetTitle) {
		this.setDatasetID(datasetID);
		this.setDatasetTitle(datasetTitle);
		headerHTMLMap = new HashMap<String, HTML>();
	}
	
	public FieldSet build(Map<String, String> headerColorMap) {
		fs = new FieldSet();
		fs.setCheckboxToggle(true);
		fs.setHeading("Series <font color='#CA1616'>C</font>" +
							 "<font color='#FFBA00'>o</font>" +
							 "<font color='#1D4589'>l</font>" +
							 "<font color='#80461B'>o</font>" +
							 "<font color='#008000'>r</font>" +
							 "<font color='#CA1616'>s</font> for Dataset:<br><i>" + this.getDatasetTitle() + "</i>");
		fs.setWidth(FIELD_SET_WIDTH);
		fs.setBorders(true);
		fs.collapse();
		for (String h : headerColorMap.keySet()) {
			HorizontalPanel sp = new HorizontalPanel();
			sp.setVerticalAlign(VerticalAlignment.MIDDLE);
			sp.setSpacing(5);
			String hex = headerColorMap.get(h);
			HTML color = null;
			if (!headerHTMLMap.containsKey(h)) {
				color = new HTML("<div align='center' style='border: 4px black solid; background-color: " + hex + "; color: " + hex + "; font-weight: bold; font-style: italic;'>" + hex + "</div>");
				color.setTitle(h);
				color.addClickHandler(ChartDesignerController.colorPicker(color));
				color.setWidth(HTML_WIDTH);
				headerHTMLMap.put(h, color);
			} else {
				color = headerHTMLMap.get(h);
			}
			sp.add(color);
			TextField<String> label = new TextField<String>();
			label.setValue(h);
			label.setTitle(h);
			label.setWidth(LABEL_WIDTH);
			sp.add(label);
			fs.add(sp);
		}
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

	public Map<String, HTML> getHeaderHTMLMap() {
		return headerHTMLMap;
	}
	
}