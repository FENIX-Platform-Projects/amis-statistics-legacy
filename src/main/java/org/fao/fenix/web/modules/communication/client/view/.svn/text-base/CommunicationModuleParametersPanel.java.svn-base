package org.fao.fenix.web.modules.communication.client.view;

import org.fao.fenix.web.modules.communication.client.control.CommunicationController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

public class CommunicationModuleParametersPanel {

	private String[] remoteSearchParameters;
	
	private String[] localSearchParameters;
	
	private ContentPanel panel;
	
	private ContentPanel controlsPanel;
	
	private Button remoteClearTableButton;
	
	private Button remoteSearchButton;
	
	private Button localClearTableButton;
	
	private Button localSearchButton;
	
	private ContentPanel localSearchPanel;
	
	private ContentPanel remoteSearchPanel;
	
	public CommunicationModuleParametersPanel() {
		panel = new ContentPanel();
		panel.setLayout(new AccordionLayout());
		panel.setHeaderVisible(false);
		initSearchParameters();
	}
	
	public ContentPanel build() {
		controlsPanel = buildControlsPanel();
		panel.add(controlsPanel);
		remoteSearchPanel = buildRemoteSearchPanel();
		panel.add(remoteSearchPanel);
		localSearchPanel = buildLocalSearchPanel();
		panel.add(localSearchPanel);
		return panel;
	}
	
	public void updateButtons(TabPanel tabPanel) {
		TabItem tabItem = tabPanel.getSelectedItem();
		if (tabItem.getText().equals(BabelFish.print().localResources())) {
			((Button)(controlsPanel.getData("download"))).setEnabled(false);
			((Button)(controlsPanel.getData("share"))).setEnabled(true);
			((RadioButton)(controlsPanel.getData("nothing"))).setEnabled(false);
			((RadioButton)(controlsPanel.getData("table"))).setEnabled(false);
			((RadioButton)(controlsPanel.getData("chart"))).setEnabled(false);
			((HTML)(controlsPanel.getData("question"))).setHTML("<b><font color='gray'>" + BabelFish.print().afterDownload() + ": </font></b>");
		} else if (tabItem.getText().equals(BabelFish.print().remoteResources())) {
			((Button)(controlsPanel.getData("download"))).setEnabled(true);
			((Button)(controlsPanel.getData("share"))).setEnabled(false);
			((RadioButton)(controlsPanel.getData("nothing"))).setEnabled(true);
			((RadioButton)(controlsPanel.getData("table"))).setEnabled(true);
			((RadioButton)(controlsPanel.getData("chart"))).setEnabled(true);
			((HTML)(controlsPanel.getData("question"))).setHTML("<b><font color='green'>" + BabelFish.print().afterDownload() + ": </font></b>");
		} 
	}
	
	public void enhanceButtons(Table remoteTable, Table localTable, CommunicationModuleParametersPanel communicationModuleParametersPanel, CommunicationModuleWindow window) {
		Button share = (Button)controlsPanel.getData("share");
		share.addSelectionListener(CommunicationController.getShareListener(localTable));
		Button download = (Button)controlsPanel.getData("download");
		download.addSelectionListener(CommunicationController.getDownloadListener(remoteTable, communicationModuleParametersPanel, window));
	}
	
	public ContentPanel buildControlsPanel() {
		ContentPanel controlsPanel = new ContentPanel();
		VerticalPanel tmpPanel = new VerticalPanel();
		tmpPanel.setSpacing(10);
		Button download = new Button(BabelFish.print().download());
		Button share = new Button(BabelFish.print().share());
		HTML question = new HTML("<b>" + BabelFish.print().afterDownload() + ": </b>");
		RadioButton nothing = new RadioButton("question", "do nothing");
		nothing.setChecked(true);
		RadioButton table = new RadioButton("question", BabelFish.print().openAsTable());
		RadioButton chart = new RadioButton("question", BabelFish.print().openAsChart());
		tmpPanel.add(download);
		tmpPanel.add(share);
		tmpPanel.add(question);
		tmpPanel.add(nothing);
		tmpPanel.add(table);
		tmpPanel.add(chart);
		controlsPanel.add(tmpPanel);
		controlsPanel.setHeading(BabelFish.print().controls());
		controlsPanel.setData("download", download);
		controlsPanel.setData("share", share);
		controlsPanel.setData("nothing", nothing);
		controlsPanel.setData("table", table);
		controlsPanel.setData("chart", chart);
		controlsPanel.setData("question", question);
		return controlsPanel;
	}
	
	public ContentPanel buildRemoteSearchPanel() {
		remoteSearchPanel = new ContentPanel();
		for (String searchParameter : remoteSearchParameters) {
			HorizontalPanel searchParameterPanel = buildSearchParameterPanel(searchParameter); 
			remoteSearchPanel.add(searchParameterPanel);
			remoteSearchPanel.setData(searchParameter, searchParameterPanel);
		}
		HorizontalPanel typePanel = buildTypePanel();
		remoteSearchPanel.add(typePanel);
		remoteSearchPanel.setData("type", typePanel);
		remoteSearchPanel.add(buildRemoteButtonsPanel(remoteSearchPanel));
		remoteSearchPanel.setHeading(BabelFish.print().remoteSearch());
		return remoteSearchPanel;
	}
	
	public ContentPanel buildLocalSearchPanel() {
		localSearchPanel = new ContentPanel();
		for (String searchParameter : localSearchParameters) {
			HorizontalPanel searchParameterPanel = buildSearchParameterPanel(searchParameter); 
			localSearchPanel.add(searchParameterPanel);
			localSearchPanel.setData(searchParameter, searchParameterPanel);
		}
		HorizontalPanel typePanel = buildTypePanel();
		localSearchPanel.add(typePanel);
		localSearchPanel.setData("type", typePanel);
		localSearchPanel.add(buildLocalButtonsPanel());
		localSearchPanel.setHeading(BabelFish.print().localSearch());
		return localSearchPanel;
	}
	
	public HorizontalPanel buildTypePanel() {
		HorizontalPanel typePanel = new HorizontalPanel();
		typePanel.setSpacing(10);
		HTML label = new HTML("<b>" + BabelFish.print().type() + ": </b>");
		ListBox list = new ListBox();
		list.addItem(BabelFish.print().datasets(), "dataset");
//		list.addItem(I18N.print().text(), "TextView");
		typePanel.add(label);
		typePanel.add(list);
		label.setWidth("100px");
		list.setWidth("100px");
		typePanel.setData("type", list);
		return typePanel;
	}
	
	private HorizontalPanel buildSearchParameterPanel(String searchParameter) {
		HorizontalPanel parameterPanel = new HorizontalPanel();
		parameterPanel.setSpacing(10);
		HTML label = new HTML("<b>" + searchParameter + ": </b>");
		TextBox value = new TextBox();
		parameterPanel.add(label);
		parameterPanel.add(value);
		parameterPanel.setData(searchParameter, value);
		label.setWidth("100px");
		value.setWidth("100px");
		return parameterPanel;
	}
	
	public HorizontalPanel buildRemoteButtonsPanel(ContentPanel remoteSearchPanel) {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		remoteClearTableButton = new Button(BabelFish.print().clear());
		remoteSearchButton = new Button(BabelFish.print().search());
		buttonsPanel.add(remoteSearchButton);
		buttonsPanel.add(remoteClearTableButton);
		buttonsPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		return buttonsPanel;
	}
	
	public void enhanceRemoteSearchButtons(CommunicationTable communicationTable, ContentPanel remoteSearchPanel) {
		remoteSearchButton.addSelectionListener(CommunicationController.getSearchRemoteListener(communicationTable, remoteSearchPanel));
		remoteClearTableButton.addSelectionListener(CommunicationController.getClearRemoteListener(communicationTable));
	}
	
	public void enhanceLocalSearchButtons(LocalTable localTable, ContentPanel localSearchPanel) {
		localSearchButton.addSelectionListener(CommunicationController.getSearchLocalListener(localTable, localSearchPanel));
		localClearTableButton.addSelectionListener(CommunicationController.getClearLocalListener(localTable));
	}
	
	public HorizontalPanel buildLocalButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		localSearchButton = new Button(BabelFish.print().search());
		localClearTableButton = new Button(BabelFish.print().clear());
		buttonsPanel.add(localSearchButton);
		buttonsPanel.add(localClearTableButton);
		buttonsPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		return buttonsPanel;
	}
	
	private void initSearchParameters() {
		remoteSearchParameters = new String[]{BabelFish.print().title(),
											  BabelFish.print().hostLabel(),
											  BabelFish.print().group()};
		localSearchParameters = new String[]{BabelFish.print().title()};
	}

	public ContentPanel getControlsPanel() {
		return controlsPanel;
	}

	public ContentPanel getLocalSearchPanel() {
		return localSearchPanel;
	}

	public ContentPanel getRemoteSearchPanel() {
		return remoteSearchPanel;
	}

}