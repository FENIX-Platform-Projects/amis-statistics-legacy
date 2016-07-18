package org.fao.fenix.web.modules.bangladesh.client.view;

import org.fao.fenix.web.modules.bangladesh.client.control.BangladeshController;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;

public class BangladeshNewOutlookTab {

	private ContentPanel panel;
	
	private DateField releaseDateField;
	
	private TextField<Integer> issueNumberField;
	
	private VerticalPanel chartPanel;
	
	private Window window;
	
	private Button submit;
	
	private Button chart;
	
	public BangladeshNewOutlookTab(BangladeshWindow bangladeshWindow) {
		panel = new ContentPanel();
		submit = new Button("Submit");
		submit.setSize(40, 25);
		chart = new Button("Submit");
		chart.setSize(40, 25);
		chartPanel = new VerticalPanel();
		window = new Window();
		window.setSize(1, 1);
		window.setResizable(false);
	}
	
	public ContentPanel build(BangladeshWindow bangladeshWindow) {
		panel.add(buildIssueNumberPanel());
		panel.add(buildDatePanel());
		panel.add(chartPanel);
//		panel.setBottomComponent(buildButtons());
		panel.setHeaderVisible(false);
		enanche(bangladeshWindow);
		return panel;
	}
	
	private void enanche(BangladeshWindow bangladeshWindow){
		submit.addSelectionListener(BangladeshController.createReport(bangladeshWindow, issueNumberField, releaseDateField));
//		chart.addSelectionListener(BangladeshController.createChart(bangladeshWindow));
	}
	
	public HorizontalPanel buildIssueNumberPanel() {
		HorizontalPanel issueNumberPanel = new HorizontalPanel();
		issueNumberPanel.setSpacing(10);
		HTML label = new HTML("<b>Issue No.<b>");
		label.setWidth("100px");
		issueNumberField = new TextField<Integer>();
		issueNumberPanel.add(label);
		issueNumberPanel.add(issueNumberField);
		return issueNumberPanel;
	}
	
	public HorizontalPanel buildDatePanel() {
		HorizontalPanel datePanel = new HorizontalPanel();
		datePanel.setSpacing(10);
		HTML label = new HTML("<b>Date<b>");
		label.setWidth("100px");
		releaseDateField = new DateField();
		datePanel.add(label);
		datePanel.add(releaseDateField);
		datePanel.add(submit);
//		datePanel.add(chart);
		return datePanel;
	}
	
	public Button buildButtons() {
		
		return submit;
	}
	
	
	
}