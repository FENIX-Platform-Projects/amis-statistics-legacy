package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.ui.HTML;

public class ExcelImporterStepPanel {

	private LayoutContainer layoutContainer;
	
	public final static String LABEL_WIDTH = "75px";
	
	public ExcelImporterStepPanel(String suggestion, String width) {
		layoutContainer = new LayoutContainer();
		layoutContainer.setBorders(false);
		layoutContainer.add(buildSuggestionPanel(suggestion, width));
	}
	
	public ExcelImporterStepPanel(String suggestion, String width, boolean isAddToReport) {
		layoutContainer = new LayoutContainer();
		layoutContainer.setBorders(false);
		layoutContainer.add(buildSuggestionPanel(suggestion, width));
	}
	
	public LayoutContainer build() {
		return layoutContainer;
	}
	
	public HorizontalPanel buildSuggestionPanel(String suggestion, String width) {
		HorizontalPanel suggestionPanel = new HorizontalPanel();
		suggestionPanel.setSpacing(10);
		suggestionPanel.setBorders(true);
		HTML suggestionHTML = new HTML("<font color='#15428B'>" + BabelFish.print().getString(suggestion) + "</font>");
		suggestionPanel.add(suggestionHTML);
		return suggestionPanel;
	}
	
	public Html createLabel(String text) {
		Html label = new Html("<b>" + text + ": </b>");
		label.setWidth(LABEL_WIDTH);
		return label;
	}
	
	public Html createLabel(String text, String width) {
		Html label = new Html("<b>" + text + ": </b>");
		label.setWidth(width);
		return label;
	}
	
	public Html createLabel(String text, String width, String color) {
		Html label = new Html("<b><font color='" + color + "'>" + text + ":</color></b>");
		label.setWidth(width);
		return label;
	}
	
	public Html createWidthlessLabel(String text) {
		Html label = new Html("<b>" + text + ": </b>");
		return label;
	}

	public LayoutContainer getLayoutContainer() {
		return layoutContainer;
	}
	
}
