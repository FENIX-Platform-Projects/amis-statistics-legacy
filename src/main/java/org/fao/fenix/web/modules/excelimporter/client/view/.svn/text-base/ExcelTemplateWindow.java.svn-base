package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.Image;

public class ExcelTemplateWindow extends FenixWindow {

	private ContentPanel panel;

	private CardLayout layout;
	
	private Button giewsWorkstationButton;

	private Button worldBankButton;
	
	private Button aboutButton;
	
	private VerticalPanel introPanel;
	
	private VerticalPanel giewsWorkstationPanel;
	
	private VerticalPanel worldBankPanel;
	
	private static final String WINDOW_WIDTH = "650px";
	
	private static final String WINDOW_HEIGHT = "450px";
	
	public ExcelTemplateWindow() {
		panel = new ContentPanel();
		layout = new CardLayout();
		panel.setLayout(layout);
		panel.setHeaderVisible(false);
		giewsWorkstationButton = new Button("<b>GIEWS Workstation</b> Layout");
		worldBankButton = new Button("<b>World Bank</b> Layout");
		aboutButton = new Button("About the Templates");
	}
	
	// p.getLayout().setActiveItem(p.getStep_b().getLayoutContainer());
	public void build() {
		buildCenterPanel();
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildCardLayoutPanel());		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private ContentPanel buildCardLayoutPanel() {
//		panel.add(buildIntroPanel());
		panel.add(buildGiewsWorkstationPanel());
		panel.add(buildWorldBankPanel());
		panel.setBottomComponent(buildButtonsPanel());
		layout.setActiveItem(introPanel);
		return panel;
	}
	
	private VerticalPanel buildIntroPanel() {
		introPanel = new VerticalPanel();
		introPanel.setSpacing(10);
		Html text = new Html("<b><u>Excel Templates</b></u><br><br>" +
							 "Data contained in you Excel file can be formatted in different ways. " +
							 "The GIEWS Workstation can handle several Excel files structures, called <u>Templates</u>. " +
							 "Please click on the buttons below to know more about each one of them.");
		introPanel.add(text);
		return introPanel;
	}
	
	private VerticalPanel buildGiewsWorkstationPanel() {
		giewsWorkstationPanel = new VerticalPanel();
		giewsWorkstationPanel.setSpacing(10);
		Html text = new Html("<b><u>'GIEWS Workstation' Layout</b></u><br><br>" +
							 "The 1st row of the Excel file must contains the dataset headers, " +
							 "data is listed in columns below. In order to store your data as a " +
							 "GIEWS Workstation Core Dataset, you can specify 1 column for numeric values, " +
							 "1 column for the measurement unit, up to 3 columns for the dates, " +
							 "up to 4 columns for the indicators. In any other case your data will be stored " +
							 "as a FLEX Dataset.");
		Image img = new Image("images/TEMPLATE_FENIX.png");
		giewsWorkstationPanel.add(text);
		giewsWorkstationPanel.add(img);
		return giewsWorkstationPanel;
	}
	
	private VerticalPanel buildWorldBankPanel() {
		worldBankPanel = new VerticalPanel();
		worldBankPanel.setSpacing(10);
		Html text = new Html("<b><u>'World Bank' Layout</b></u><br><br>" +
							 "The 1st row of the Excel file contains the dataset's indicator AND " +
							 "the dates. You can specify up to 4 indicators in the first 4 columns " +
							 "of first line. Then you can specify as many dates as you need.");
		Image img = new Image("images/TEMPLATE_WORLD_BANK.png");
		worldBankPanel.add(text);
		worldBankPanel.add(img);
		return worldBankPanel;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
//		buttonsPanel.add(aboutButton);
		buttonsPanel.add(giewsWorkstationButton);
		buttonsPanel.add(worldBankButton);
		aboutButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				layout.setActiveItem(introPanel);
			}
		});
		giewsWorkstationButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				layout.setActiveItem(giewsWorkstationPanel);
			}
		});
		worldBankButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				layout.setActiveItem(worldBankPanel);
			}
		});
		return buttonsPanel;
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading(BabelFish.print().information());
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("info");
		getWindow().setCollapsible(false);
	}
	
}