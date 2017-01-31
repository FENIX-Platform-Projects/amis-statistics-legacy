package org.fao.fenix.web.modules.cnsa.client.view;

import org.fao.fenix.web.modules.cnsa.client.control.CNSAController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.lang.client.CNSALangEntry;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class CNSAReportsSelection {
	
	VerticalPanel panel;
	
	private ComboBox<ListItem> combo = new ComboBox<ListItem>();
	
	private ListStore<ListItem> store = new ListStore<ListItem>();
	
	private ListBox dates;
	
	private String labelsLength = "100px";
	
	private String comboBoxLength = "150px";
	
	private Button exportPDF;
	
	private Button exportExcel;
	
	private ContentPanel reportPanel;
	
	private String width;
	
	private String height;
	
	private String language;
	
	public CNSAReportsSelection() {
		panel = new VerticalPanel();
		combo = new ComboBox<ListItem>();
		store = new ListStore<ListItem>();
		dates = new ListBox();
		exportPDF = new Button();
		exportExcel = new Button();
		reportPanel = new ContentPanel();
		reportPanel.setHeaderVisible(false);
		reportPanel.setHeight(400);
		reportPanel.setWidth(470);
		reportPanel.setBorders(false);
		reportPanel.setBodyBorder(false);
		panel.setHeight(500);
		panel.setWidth(500);

		
	}
	
	public VerticalPanel build(String type, String height, String width, String language) {
		this.setWidth(width);
		this.setHeight(height);
		this.setLanguage(language);
		panel.setSpacing(10);
		panel.add(buildQueryPanel(type, height, width, language));
		panel.add(reportPanel);
		panel.layout();
		exportPDF.setText(CNSALangEntry.getInstance(language).exportInPDF());
		exportExcel.setText(CNSALangEntry.getInstance(language).exportInExcel());
		
		reportPanel.setWidth(width);
		reportPanel.setHeight(height);
		
		enancheButtons(type, height, width, language);
		
		return panel;
	}
	
	private void enancheButtons(String type, String height, String width, String language) {
		exportPDF.addSelectionListener(CNSAController.getReportExport(reportPanel, dates, type, height, width, language));
		exportExcel.addSelectionListener(CNSAController.getExcel(dates, type, language));
	}
	
	private HorizontalPanel buildQueryPanel(String type, String widht, String lenght, String language) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
//		panel.add(buildComboBox(type));
		panel.add(buildListBox(type));
		
		return panel;
	}

	
	private HorizontalPanel buildComboBox(String type) {
		HorizontalPanel vpanel = new HorizontalPanel();
		vpanel.setSpacing(10);
		HTML label = new HTML("<b>" + CNSALangEntry.getInstance(language).selectAdate() + " </b>");
		label.setWidth(labelsLength);
		combo.setWidth(comboBoxLength);
		vpanel.add(label);
		vpanel.add(combo);
		combo.setStore(store);
		combo.setTypeAhead(true);
		combo.setEditable(true);
		combo.setDisplayField("name");
		combo.setEmptyText(BabelFish.print().pleaseSelect());
		combo.setAllowBlank(false);
		combo.setId("combo");
		
		combo.setTriggerAction(TriggerAction.ALL);
		CNSAController.initializeCNSAReport(store, combo, type);
		vpanel.add(exportPDF);
		vpanel.add(exportExcel);
		return vpanel;
	}
	
	private HorizontalPanel buildListBox(String type) {
		HorizontalPanel vpanel = new HorizontalPanel();
		vpanel.setSpacing(10);
		HTML label = new HTML("<b>" + CNSALangEntry.getInstance(language).selectAdate() + " </b>");
		label.setWidth(labelsLength);
		dates.setWidth(comboBoxLength);
		vpanel.add(label);
		vpanel.add(dates);

		CNSAController.initializeCNSAReport(reportPanel, dates, type, height, width, language);
		
		dates.addChangeListener(CNSAController.getReportListener(reportPanel, dates, type, height, width, language));
		vpanel.add(exportPDF);
		vpanel.add(exportExcel);
		return vpanel;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	

	
}
