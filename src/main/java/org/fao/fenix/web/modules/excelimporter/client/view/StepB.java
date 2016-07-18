package org.fao.fenix.web.modules.excelimporter.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.excelimporter.client.control.ExcelImporterController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingNameModelData;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingTypeModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class StepB extends ExcelImporterStepPanel {

	private VerticalPanel wrapper;
	
	private ListStore<CodingTypeModelData> codingTypeListStore;
	
	private ListStore<CodingNameModelData> codingNameListStore;
	
	private final static String FIELD_WIDTH = "125px";
	
	private final static String COL_PANEL_WIDTH = "525px";
	
	private List<HorizontalPanel> columnPanelList;
	
	public StepB(String suggestion, String width) {
		super(suggestion, width);
		this.getLayoutContainer().add(buildWrapperPanel());
		codingTypeListStore = new ListStore<CodingTypeModelData>();
		codingNameListStore = new ListStore<CodingNameModelData>();
		MEController.fillCodingTypeStore(codingTypeListStore);
		MEController.fillCodingNameStore(codingNameListStore);
		columnPanelList = new ArrayList<HorizontalPanel>();
	}
	
	private VerticalPanel buildWrapperPanel() {
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setLayout(new FillLayout());
		wrapper.setHeight("240px");
		wrapper.setWidth("580px");
		return wrapper;
	}
	
	public void addColumnPanel(String columnName) {
		wrapper.add(buildColumnPanel(columnName));
	}
	
	private HorizontalPanel buildColumnPanel(String columnName) {
		
		HorizontalPanel columnPanel = new HorizontalPanel();
		columnPanel.setSpacing(10);
		columnPanel.setBorders(true);
		columnPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		columnPanel.setHorizontalAlign(HorizontalAlignment.RIGHT);
		columnPanel.setWidth(COL_PANEL_WIDTH);
		
		TextField<String> columnNameField = new TextField<String>();
		columnNameField.setValue(columnName);
		columnNameField.setWidth(FIELD_WIDTH);
		columnNameField.setReadOnly(true);
		
		IconButton csButton = new IconButton("addIcon");
		csButton.addSelectionListener(ExcelImporterController.selectCodingSystem(columnPanel));
		csButton.setToolTip("Associate a Coding System to this column");
		
		IconButton uploadCSButton = new IconButton("cs");
		uploadCSButton.addSelectionListener(ExcelImporterController.uploadCodingSystem());
		uploadCSButton.setToolTip("Upload a new Coding System");
		
		columnPanel.add(createWidthlessLabel("Header"));
		columnPanel.add(columnNameField);
		columnPanel.add(createWidthlessLabel("Data Type"));
		ToolBar toolbar = buildDataTypeSelector();
		columnPanel.add(toolbar);
		columnPanel.add(createWidthlessLabel("Codelist"));
		columnPanel.add(csButton);
		columnPanel.add(uploadCSButton);
		
		columnPanel.setData("ICON_BUTTON", csButton);
		columnPanel.setData("COLUMN_NAME_FIELD", columnNameField);
		columnPanel.setData("TOOLBAR", toolbar);
		
		columnPanelList.add(columnPanel);
		
		return columnPanel;
	}
	
	private ToolBar buildDataTypeSelector() {
		
		ToolBar t = new ToolBar();
		t.setStyleName("excel-importer-toolbar");
		
		Button b = new Button(BabelFish.print().pleaseSelect());
		b.setIconStyle("select");
		b.setWidth(FIELD_WIDTH);
		
		Menu menu = new Menu();
		
		MenuItem indicator = new MenuItem(BabelFish.print().indicator());
		indicator.setIconStyle(ExcelImporterController.dataTypeIconMap.get(indicator.getText()));
		indicator.addSelectionListener(ExcelImporterController.dataTypeSelector(indicator, b));
		menu.add(indicator);
		
		MenuItem numericValue = new MenuItem(BabelFish.print().numericValue());
		numericValue.setIconStyle(ExcelImporterController.dataTypeIconMap.get(numericValue.getText()));
		numericValue.addSelectionListener(ExcelImporterController.dataTypeSelector(numericValue, b));
		menu.add(numericValue);
		
		MenuItem textValue = new MenuItem(BabelFish.print().textValue());
		textValue.setIconStyle(ExcelImporterController.dataTypeIconMap.get(indicator.getText()));
		textValue.addSelectionListener(ExcelImporterController.dataTypeSelector(textValue, b));
//		menu.add(textValue);
		
		MenuItem date = new MenuItem(BabelFish.print().date());
		date.setIconStyle(ExcelImporterController.dataTypeIconMap.get(date.getText()));
		date.addSelectionListener(ExcelImporterController.dataTypeSelector(date, b));
		menu.add(date);
		
		MenuItem measurementUnit = new MenuItem(BabelFish.print().measurementUnit());
		measurementUnit.setIconStyle(ExcelImporterController.dataTypeIconMap.get(measurementUnit.getText()));
		measurementUnit.addSelectionListener(ExcelImporterController.dataTypeSelector(measurementUnit, b));
		menu.add(measurementUnit);
		
		b.setMenu(menu);
		t.add(b);
		
		return t;
	}

	public List<HorizontalPanel> getColumnPanelList() {
		return columnPanelList;
	}
	
}