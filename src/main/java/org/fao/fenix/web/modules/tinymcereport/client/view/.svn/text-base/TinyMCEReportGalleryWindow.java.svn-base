package org.fao.fenix.web.modules.tinymcereport.client.view;

import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEReportGalleryController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class TinyMCEReportGalleryWindow extends FenixWindow {

	private static final String WINDOW_WIDTH = "850px";
	
	private static final String HTML_WIDTH = "820px";
	
	private static final String WINDOW_HEIGHT = "700px";
	
	private static final String LIST_WIDTH = "760px";
	
	private static final int SPACING = 5;
	
	private Button selectButton;
	
	private Button deleteButton;
	
	private HTML html;
	
	private ListStore<GaulModelData> templatesListStore;
	
	private ComboBox<GaulModelData> templatesComboBox;
	
	private String tinyMCEPanelCode = "";
	
	public TinyMCEReportGalleryWindow(String code) {
		this.setTinyMCEPanelCode(code);
		selectButton = new Button("Select Template");
		deleteButton = new Button("Delete Template");
		html = new HTML("<table width='100%' align='center' style='text-align: center; font-family: sans-serif;'>" +
						"<tr><td><H1>TEMPLATES PREVIEW</H1></td></tr>" +
						"<tr><td style='font-style: italic;'>Please select a template from the above drop-down to visulize its preview.</td></tr>" +
						"</table>");
		html.setWidth(HTML_WIDTH);
		templatesListStore = new ListStore<GaulModelData>();
		templatesComboBox = new ComboBox<GaulModelData>();
		templatesComboBox.setTriggerAction(TriggerAction.ALL);
		templatesComboBox.setStore(templatesListStore);
		templatesComboBox.setDisplayField("gaulLabel");
		templatesComboBox.setEmptyText("start typing to filter the list");
		templatesComboBox.setWidth(LIST_WIDTH);
		templatesComboBox.addSelectionChangedListener(TinyMCEReportGalleryController.selectionChangedListener(this));
		selectButton.addSelectionListener(TinyMCEReportGalleryController.selectTemplate(this));
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
		TinyMCEReportGalleryController.populateTemplatesListStore(this);
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().setTopComponent(buildSelectorPanel());
		getCenter().add(buildPreviewPanel());
		getCenter().setBottomComponent(buildButtonsPanel());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private HorizontalPanel buildPreviewPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.add(html);
		return p;
	}
	
	private HorizontalPanel buildSelectorPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		HTML lbl = new HTML("<b>Template: </b>");
		p.add(lbl);
		p.add(templatesComboBox);
		return p;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(SPACING);
		p.add(selectButton);
		p.add(deleteButton);
		return p;
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Report Templates Gallery");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("reportIcon");
		getWindow().setCollapsible(false);
		getWindow().setModal(true);
	}

	public Button getSelectButton() {
		return selectButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public HTML getHtml() {
		return html;
	}

	public ListStore<GaulModelData> getTemplatesListStore() {
		return templatesListStore;
	}

	public ComboBox<GaulModelData> getTemplatesComboBox() {
		return templatesComboBox;
	}

	public String getTinyMCEPanelCode() {
		return tinyMCEPanelCode;
	}

	public void setTinyMCEPanelCode(String tinyMCEPanelCode) {
		this.tinyMCEPanelCode = tinyMCEPanelCode;
	}
	
}