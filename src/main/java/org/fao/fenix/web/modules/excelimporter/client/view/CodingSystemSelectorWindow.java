package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.excelimporter.client.control.ExcelImporterController;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingNameModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class CodingSystemSelectorWindow extends FenixWindow {

	private VerticalPanel wrapper;
	
	private SimpleComboBox<String> codingTypeCombo;
	
	private ListStore<CodingNameModelData> codingNameStore;
	
	private ComboBox<CodingNameModelData> codingNameCombo;
	
	private Button selectButton;
	
	private Button uploadButton;
	
	private Button cancelButton;
	
	private HorizontalPanel columnPanel;
	
	public final static String LABEL_WIDTH = "150px";
	
	public final static String FIELD_WIDTH = "150px";
	
	public CodingSystemSelectorWindow(final HorizontalPanel columnPanel) {
		codingNameStore = new ListStore<CodingNameModelData>();
		codingNameCombo = new ComboBox<CodingNameModelData>();
		codingNameCombo.setTriggerAction(TriggerAction.ALL);
		codingNameCombo.setStore(codingNameStore);
		this.columnPanel = columnPanel;
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
		getCenter().add(buildWrapper());		
		getCenter().setSize("275px", "100px");
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private VerticalPanel buildWrapper() {
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		wrapper.add(buildCodingTypeComboPanel());
		wrapper.add(buildCodingNameComboPanel());
		wrapper.add(buildButtonsPanel());
		return wrapper;
	}
	
	private HorizontalPanel buildCodingTypeComboPanel() {
		HorizontalPanel codingTypePanel = new HorizontalPanel();
		codingTypePanel.setSpacing(10);
		HTML label = new HTML("<b>Coding System Type:</b>");
		label.setWidth(LABEL_WIDTH);
		codingTypeCombo = new SimpleComboBox<String>();
		codingTypeCombo.setTriggerAction(TriggerAction.ALL);		
		codingTypeCombo.add("Commodity");
		codingTypeCombo.add("Geographic");
		codingTypeCombo.add("Indicator");
		codingTypeCombo.setWidth(FIELD_WIDTH);
		codingTypeCombo.setAllowBlank(false);
		codingTypeCombo.setAutoValidate(true);
		codingTypeCombo.setEmptyText("Coding System Type");
		codingTypeCombo.addSelectionChangedListener(ExcelImporterController.codingTypeSelectionChanged(this));
		codingTypePanel.add(label);
		codingTypePanel.add(codingTypeCombo);
		return codingTypePanel;
	}
	
	private HorizontalPanel buildCodingNameComboPanel() {
		HorizontalPanel codingNamePanel = new HorizontalPanel();
		codingNamePanel.setSpacing(10);
		HTML label = new HTML("<b>Coding System Name:</b>");
		label.setWidth(LABEL_WIDTH);
		codingNameCombo = new ComboBox<CodingNameModelData>();
		codingNameCombo.setTriggerAction(TriggerAction.ALL);
		codingNameCombo.setStore(codingNameStore);
		codingNameCombo.setWidth(FIELD_WIDTH);
		codingNameCombo.setAllowBlank(false);
		codingNameCombo.setAutoValidate(true);
		codingNameCombo.setEmptyText("Coding System Name");
		codingNameCombo.setDisplayField("codingName");
		codingNamePanel.add(label);
		codingNamePanel.add(codingNameCombo);
		return codingNamePanel;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		selectButton = new Button("Select");
		uploadButton = new Button("Upload New Coding System");
		cancelButton = new Button("Cancel");
		buttonsPanel.add(uploadButton);
		buttonsPanel.add(selectButton);
		buttonsPanel.add(cancelButton);
		uploadButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				new CodeListImporterWindow().build();
			}
		});
		return buttonsPanel;
	}
	
	private void format() {
		this.getWindow().setHeading("Coding System Selector");
		this.getWindow().setModal(true);
		this.getWindow().setSize("400px", "225px");
	}

	public ListStore<CodingNameModelData> getCodingNameStore() {
		return codingNameStore;
	}

	public ComboBox<CodingNameModelData> getCodingNameCombo() {
		return codingNameCombo;
	}

	public Button getUploadButton() {
		return uploadButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public Button getSelectButton() {
		return selectButton;
	}

	public SimpleComboBox<String> getCodingTypeCombo() {
		return codingTypeCombo;
	}
	
}
