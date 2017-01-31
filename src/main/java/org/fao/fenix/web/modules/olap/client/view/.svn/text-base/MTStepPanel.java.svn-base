package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.ui.HTML;

public abstract class MTStepPanel {

	private LayoutContainer layoutContainer;
	
	private HTML suggestionHTML;
	
	private Long datasetID;
	
	private int SPACING = 5;
	
	private final static String SUGGESTION_HEIGHT = "50px";
	
	public MTStepPanel(String suggestion, String width) {
		layoutContainer = new LayoutContainer();
		layoutContainer.setBorders(false);
		layoutContainer.add(buildSuggestionPanel(suggestion, width));
		this.setDatasetID(datasetID);
	}
	
	public LayoutContainer build() {
		return layoutContainer;
	}
	
	public HorizontalPanel buildSuggestionPanel(String suggestion, String width) {
		HorizontalPanel suggestionPanel = new HorizontalPanel();
		suggestionPanel.setSpacing(SPACING);
		suggestionPanel.setBorders(true);
		suggestionHTML = new HTML("<font color='#CA1616'><b>Suggestions: </b></font>" + "<font color='#15428B'>" + BabelFish.print().getString(suggestion) + "</font>");
		suggestionPanel.add(suggestionHTML);
		suggestionPanel.setHeight(SUGGESTION_HEIGHT);
		suggestionPanel.setScrollMode(Scroll.AUTO);
		return suggestionPanel;
	}

	public LayoutContainer getLayoutContainer() {
		return layoutContainer;
	}

	public HTML getSuggestionHTML() {
		return suggestionHTML;
	}

	public Long getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(Long datasetID) {
		this.datasetID = datasetID;
	}

	public int getSPACING() {
		return SPACING;
	}
	
}