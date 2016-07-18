package org.fao.fenix.web.modules.tinymcereport.client.control;

import java.util.Date;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author kalimaha
 * 
 *         FENIX objects (charts and OLAPs, maps in the future) auto-update
 *         themselves when their data-source changes. This window sets a limit
 *         to the updates through the 'From Date To Date' feature, setting the
 *         'From Date' equals to the first available date and making the user
 *         choice the 'To Date'.
 * 
 */
public class TinyMCEFreezerWindow extends FenixWindow {
	
	private static final String WINDOW_WIDTH = "250px";
	
	private static final String FIELD_WIDTH = "225px";
	
	private static final String FIELD_WIDTH_2 = "200px";
	
	private static final String WINDOW_HEIGHT = "280px";
	
	private static final String STYLE = "<div style='font-family: sans-serif; color: #1D4589;'>"; 
	
	private static final int SPACING = 5;
	
	private Button okButton;
	
	private String html;
	
	private String tinyMCEPanelID;
	
	private CheckBox freezeAutoUpdate;
	
	private DateField toDate;
	
	private FieldSet descriptionFieldSet;
	
	public TinyMCEFreezerWindow(String html, String tinyMCEPanelID) {
		this.setHtml(html);
		this.setTinyMCEPanelID(tinyMCEPanelID);
		okButton = new Button("OK");
		okButton.addSelectionListener(TinyMCEController.freeze(tinyMCEPanelID, this));
		freezeAutoUpdate = new CheckBox();
		toDate = new DateField();
		descriptionFieldSet = new FieldSet();
		descriptionFieldSet.setCheckboxToggle(false);
		descriptionFieldSet.setHeading("What is This?");
		descriptionFieldSet.setWidth(FIELD_WIDTH);
		descriptionFieldSet.setBorders(true);
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildPanel());
		getCenter().setBottomComponent(okButton);
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	public Long extractID() throws FenixGWTException {
		int start = this.getHtml().indexOf("id=\"");
		int end = this.getHtml().indexOf("\"", start + "id=\"".length());
		if ((start > -1) && (end > -1)) {
			if (this.getHtml().contains("CHART_")) {
				return Long.valueOf(this.getHtml().substring(start + "id=\"CHART_".length(), end));
			} else if (this.getHtml().contains("OLAP_")) {
				return Long.valueOf(this.getHtml().substring(start + "id=\"OLAP_".length(), end));
			}
		}
		throw new FenixGWTException("It's not possible to read the FENIX id.");
	}
	
	public String extractResourceType() throws FenixGWTException {
		if (this.getHtml().contains("CHART_")) {
			return "CHART";
		} else if (this.getHtml().contains("OLAP_")) {
			return "OLAP";
		} else {
			throw new FenixGWTException("It's not possible to read the FENIX resource type.");
		}
	}
	
	private VerticalPanel buildPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		freezeAutoUpdate.setBoxLabel("<b>Auto-Update This Object Up To:</b>");
//		p.add(freezeAutoUpdate);
		toDate.setWidth(FIELD_WIDTH);
		toDate.setValue(new Date());
		HTML lbl1 = new HTML(STYLE + "<b>Auto-Update This Object Up To:</b></div>");
		lbl1.setWidth(FIELD_WIDTH);
		HTML lbl2 = new HTML(STYLE + "<i style='font-size: 8pt;'>" +
									 "Workstation objects, such as charts and tables, automatically update themselves " +
									 "when their datasource changes. This feature limit the auto-update up to a cetain date. " +
									 "e.g. Setting the date to July 1st 2011 means that any data beyond July 1st 2011 " +
									 "will not be shown by charts/tables using this feature." +
									 "</i></div>");
		lbl2.setWidth(FIELD_WIDTH_2);
		descriptionFieldSet.add(lbl2);
		p.add(lbl1);
		p.add(toDate);
		p.add(descriptionFieldSet);
		return p;
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Settings");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("tools");
		getWindow().setCollapsible(false);
		getWindow().setModal(true);
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getTinyMCEPanelID() {
		return tinyMCEPanelID;
	}

	public void setTinyMCEPanelID(String tinyMCEPanelID) {
		this.tinyMCEPanelID = tinyMCEPanelID;
	}

	public DateField getToDate() {
		return toDate;
	}

}