package org.fao.fenix.web.modules.statistics.client.view;

import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.statistics.common.services.StatisticsServiceEntry;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.custom.ThemeSelector;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HTML;

public class StatisticsPanel {

	private ContentPanel panel;
	
	private TabPanel tabPanel;

	private ContentPanel commandLinePanel;

	private HorizontalPanel topPanel;

	private HorizontalPanel bottomPanel;
	
	private ComboBox<GaulModelData> cmdSyntaxComboBox;

	public ComboBox<GaulModelData> getCmdSyntaxComboBox() {
		return cmdSyntaxComboBox;
	}

	private TextArea input;


	private Button executeButton;
	private Button historyButton;

	private HTML result;

	private ToolBar verticalToolBar;
	
	private TabItem tableViewTabItem;
	
	private TabItem commandLineTabItem;
	
	ListStore<GaulModelData> cmdSyntaxStore ;

	public ListStore<GaulModelData> getCmdSyntaxStore() {
		return cmdSyntaxStore;
	}

	public StatisticsPanel() {
		panel = build();
	}

	public ContentPanel build() {
		
		panel = new ContentPanel();
		panel.setLayout(new FillLayout());
		panel.setHeaderVisible(false);
		panel.setTopComponent(buildToolbar());
		
		tabPanel = new TabPanel();

		commandLinePanel = new ContentPanel();
		commandLinePanel.setHeaderVisible(false);
		commandLinePanel.setLayout(new RowLayout(Orientation.VERTICAL));
		
		commandLinePanel.add(buildTopPanel() , new RowData());  
		commandLinePanel.add(buildBottomPanel() ,new RowData());
		
		commandLinePanel.setHeight("600px");
		
		
		tableViewTabItem = new TabItem("Table View");
		
		commandLineTabItem = new TabItem("Command Line");
		commandLineTabItem.add(commandLinePanel);
		
		tabPanel.add(tableViewTabItem);
		tabPanel.add(commandLineTabItem);
		
		panel.add(tabPanel);
				
		return panel;
	}

	private HorizontalPanel buildBottomPanel() {

		bottomPanel = new HorizontalPanel();
		bottomPanel.setStyleAttribute("backgroundColor", "#51A828");
		bottomPanel.setBorders(true);
		//bottomPanel.setVerticalAlign(VerticalAlignment.BOTTOM);
		
		executeButton = new Button("Execute");
		historyButton = new Button("History");
		
		
		cmdSyntaxStore = new ListStore<GaulModelData>();
//		cmdSyntaxStore.add(new GaulModelData("Standard Deviation", "sd"));
		cmdSyntaxComboBox = new ComboBox<GaulModelData>();
		cmdSyntaxComboBox.setTriggerAction(TriggerAction.ALL);
		cmdSyntaxComboBox.setStore(cmdSyntaxStore);
		cmdSyntaxComboBox.setDisplayField("gaulLabel");
		
		
		cmdSyntaxStore.insert(new GaulModelData("Please Select From the list", null), 0);

		input = new TextArea();
		input.setWidth("400px");
		bottomPanel.add(input);
		bottomPanel.add(executeButton);
		bottomPanel.add(historyButton);
		
		HTML label = new HTML(
				"<div style='font-family: sans-serif; font-size: 8pt; color: #FFFFFF; font-variant: small-caps; font-weight: bold;'>"
						+ "Syntax of R</div>");
		label.setWidth("120px");
		cmdSyntaxComboBox.setWidth("200px");
		cmdSyntaxComboBox.setEmptyText("e.g. Mean etc.");
		
		bottomPanel.add(cmdSyntaxComboBox);
		
		return bottomPanel;
	}

	private HorizontalPanel buildTopPanel() {

		topPanel = new HorizontalPanel();
		topPanel.setLayout(new FillLayout());
		topPanel.setBorders(true);
		//topPanel.setVerticalAlign(VerticalAlignment.TOP);
		topPanel.setStyleAttribute("backgroundColor", "#000000");
		topPanel.setScrollMode(Scroll.AUTO);
		
		verticalToolBar = new ToolBar();

		result = new HTML("<H1>Hello World?</H1>");
		result.setHeight("400px");
		topPanel.add(result);
		return topPanel;
	}

	public ToolBar buildToolbar() {

		ToolBar toolBar = new ToolBar();

		Button item1 = new Button("Button Manik");
		toolBar.add(item1);

		toolBar.add(new SeparatorToolItem());

		Button toggle = new Button("Button Guido");
		toolBar.add(toggle);

		return toolBar;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public HorizontalPanel getTopPanel() {
		return topPanel;
	}
	public HTML getResult() {
		return result;
	}

	public void setResult(HTML result) {
		this.result = result;
	}
	
	public Button getHistoryButton() {
		return historyButton;
	}

	public void setHistoryButton(Button historyButton) {
		this.historyButton = historyButton;
	}

	public Button getExecuteButton() {
		return executeButton;
	}
	
	public TextArea getInput() {
		return input;
	}

	public void setInput(TextArea input) {
		this.input = input;
	}
	
}