package org.fao.fenix.web.modules.tinymcereport.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEController;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCENativeController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;

public class TinyMCEReportWindow extends FenixWindow {

	private static final String WINDOW_WIDTH = "850px";
	
	private static final String WINDOW_HEIGHT = "700px";
	
	private final static int SPACING = 3;
	
	private final static String SIMPLE_BUTTON_WIDTH = "205px";
	
	private final static String MENU_BUTTON_WIDTH = "195px";
	
	private TinyMCEPanel tinyMCEPanel;
	
	private MenuItem loadChartMenuItem;
	
	private MenuItem createChartMenuItem;
	
	private MenuItem loadOlapMenuItem;
	
	private MenuItem createOlapMenuItem;
	
	private MenuItem loadMapMenuItem;
	
	private MenuItem createMapMenuItem;
	
	private MenuItem loadImageMenuItem;
	
	private MenuItem createImageMenuItem;
	
	private MenuItem saveMenuItem;
	
	private MenuItem saveAsMenuItem;
	
	private MenuItem saveAsTemplateMenuItem;
	
	private MenuItem saveTemplateMenuItem;
	
	private MenuItem exportToWordMenuItem;
	
	private MenuItem exportToPDFMenuItem;
	
	private MenuItem exportToHTMLMenuItem;
	
	private Button saveButton;
	
	private Button templateButton;
	
	private Button previewButton;
	
	private Button printButton;
	
	private Button fullScreenButton;
	
	private Long reportID;
	
	public TinyMCEReportWindow() {
		tinyMCEPanel = new TinyMCEPanel();
		exportToWordMenuItem = new MenuItem("Export To Word");
		exportToWordMenuItem.setIconStyle("docIcon");
		exportToPDFMenuItem = new MenuItem("Export To PDF");
		exportToPDFMenuItem.setIconStyle("sendToPdf");
		exportToHTMLMenuItem = new MenuItem("Export To HTML");
		exportToHTMLMenuItem.setIconStyle("sendToHTML");
		loadChartMenuItem = new MenuItem("Add Existing Chart");
		loadChartMenuItem.setIconStyle("workspaceChart");
		createChartMenuItem = new MenuItem("Create New Chart");
		createChartMenuItem.setIconStyle("wand");
		loadOlapMenuItem = new MenuItem("Add Existing Multidimensional Table");
		loadOlapMenuItem.setIconStyle("olap");
		createOlapMenuItem = new MenuItem("Create New Multidimensional Table");
		createOlapMenuItem.setIconStyle("wand");
		loadMapMenuItem = new MenuItem("Add Existing Map");
		loadMapMenuItem.setIconStyle("workspaceMap");
		createMapMenuItem = new MenuItem("Create New Map");
		createMapMenuItem.setIconStyle("wand");
		loadImageMenuItem = new MenuItem("Add Existing Image");
		loadImageMenuItem.setIconStyle("smallCamera");
		createImageMenuItem = new MenuItem("Upload New Image");
		createImageMenuItem.setIconStyle("wand");
		saveMenuItem = new MenuItem("Save");
		saveMenuItem.setIconStyle("save");
		saveAsMenuItem = new MenuItem("Save As...");
		saveAsMenuItem.setIconStyle("save");
		saveAsTemplateMenuItem = new MenuItem("Save Template As...");
		saveAsTemplateMenuItem.setIconStyle("reportIcon");
		saveTemplateMenuItem = new MenuItem("Save Template");
		saveTemplateMenuItem.setIconStyle("reportIcon");
		saveButton = new Button("Save");
		saveButton.setIconStyle("save");
		saveButton.setWidth(SIMPLE_BUTTON_WIDTH);
		templateButton = new Button("Load Template");
		templateButton.setIconStyle("reportIcon");
		templateButton.setWidth(SIMPLE_BUTTON_WIDTH);
		previewButton = new Button("Preview");
		previewButton.setIconStyle("sendToHTML");
		previewButton.setWidth(SIMPLE_BUTTON_WIDTH);
		printButton = new Button("Print");
		printButton.setIconStyle("print");
		printButton.setWidth(SIMPLE_BUTTON_WIDTH);
		fullScreenButton = new Button("Full Screen");
		fullScreenButton.setIconStyle("aggregation");
		fullScreenButton.setWidth(SIMPLE_BUTTON_WIDTH);
	}
	
	public void build(Long reportID) {
		this.setReportID(reportID);
		buildCenterPanel();
		format();
		show();
		TinyMCENativeController.initializeTinyMCE(tinyMCEPanel.getCode());
		TinyMCENativeController.attachTinyMCE(tinyMCEPanel.getCode());
		loadChartMenuItem.addSelectionListener(TinyMCEController.selectChart(tinyMCEPanel.getCode()));
		loadImageMenuItem.addSelectionListener(TinyMCEController.selectImage(tinyMCEPanel.getCode()));
		createImageMenuItem.addSelectionListener(TinyMCEController.createNewImage(tinyMCEPanel.getCode()));
		loadOlapMenuItem.addSelectionListener(TinyMCEController.selectOLAP(tinyMCEPanel.getCode()));
		createOlapMenuItem.addSelectionListener(TinyMCEController.createNewOLAP(tinyMCEPanel.getCode()));
		createChartMenuItem.addSelectionListener(TinyMCEController.createNewChart(tinyMCEPanel.getCode()));
		previewButton.addSelectionListener(TinyMCEController.preview(tinyMCEPanel.getCode()));
		templateButton.addSelectionListener(TinyMCEController.loadTemplate(tinyMCEPanel.getCode()));
		printButton.addSelectionListener(TinyMCEController.infoPrint(tinyMCEPanel.getCode()));
		fullScreenButton.addSelectionListener(TinyMCEController.fullScreen(tinyMCEPanel.getCode()));
		saveMenuItem.addSelectionListener(TinyMCEController.save(this, tinyMCEPanel.getCode(), false));
		saveAsMenuItem.addSelectionListener(TinyMCEController.saveAs(this, tinyMCEPanel.getCode(), false));
		saveAsTemplateMenuItem.addSelectionListener(TinyMCEController.saveAs(this, tinyMCEPanel.getCode(), true));
		saveTemplateMenuItem.addSelectionListener(TinyMCEController.save(this, tinyMCEPanel.getCode(), true));
		exportToHTMLMenuItem.addSelectionListener(TinyMCEController.export(this, tinyMCEPanel.getCode(), "HTML"));
		exportToPDFMenuItem.addSelectionListener(TinyMCEController.export(this, tinyMCEPanel.getCode(), "PDF"));
		exportToWordMenuItem.addSelectionListener(TinyMCEController.export(this, tinyMCEPanel.getCode(), "DOC"));
		TinyMCEController.close(this);
		System.out.println("Report ID? " + this.getReportID());
		if (this.getReportID() != null) {
//			System.out.println("Load Report");
			TinyMCEController.loadReport(this);
		} 
		if (this.getReportID() == null) {
//			System.out.println("Prepare...");
			prepare();
		}
	}
	
	private void prepare() {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Clean Up the Report Board.", BabelFish.print().loading());
		this.getWindow().disable();
		Timer t = new Timer() {
			public void run() {
				GWT.log("Timer is runnning");
				TinyMCENativeController.replaceAll(tinyMCEPanel.getCode());
				GWT.log("replace all");
//				TinyMCENativeController.cleanup(tinyMCEPanel.getCode());
				GWT.log("cleanup");
				l.destroyLoadingBox();
				getWindow().enable();
			}
		};
		t.schedule(2000);
	}
	
	public Button buildSaveMenu() {
		Button b = new Button("Save...");
		b.setIconStyle("save");
		Menu m = new Menu();
		m.add(saveMenuItem);
		m.add(saveAsMenuItem);
		m.add(saveTemplateMenuItem);
		m.add(saveAsTemplateMenuItem);
		b.setMenu(m);
		b.setWidth(MENU_BUTTON_WIDTH);
		return b;
	}
	
	public Button buildExportMenu() {
		Button b = new Button("Export...");
		b.setIconStyle("sendToReport");
		Menu m = new Menu();
		m.add(exportToHTMLMenuItem);
		m.add(exportToPDFMenuItem);
		m.add(exportToWordMenuItem);
		b.setMenu(m);
		b.setWidth(MENU_BUTTON_WIDTH);
		return b;
	}
	
	public Button buildChartMenu() {
		Button b = new Button("Add Chart");
		b.setIconStyle("workspaceChart");
		Menu m = new Menu();
		m.add(loadChartMenuItem);
		m.add(createChartMenuItem);
		b.setMenu(m);
		b.setWidth(MENU_BUTTON_WIDTH);
		return b;
	}
	
	public Button buildOlapMenu() {
		Button b = new Button("Add Multidimensional Table");
		b.setIconStyle("olap");
		Menu m = new Menu();
		m.add(loadOlapMenuItem);
		m.add(createOlapMenuItem);
		b.setMenu(m);
		b.setWidth(MENU_BUTTON_WIDTH);
		return b;
	}
	
	public Button buildMapMenu() {
		Button b = new Button("Add Map");
		b.setIconStyle("workspaceMap");
		Menu m = new Menu();
		m.add(loadMapMenuItem);
		m.add(createMapMenuItem);
		b.setMenu(m);
		b.setEnabled(false);
		b.setWidth(MENU_BUTTON_WIDTH);
		return b;
	}
	
	public Button buildImageMenu() {
		Button b = new Button("Add Image");
		b.setIconStyle("smallCamera");
		Menu m = new Menu();
		m.add(loadImageMenuItem);
		m.add(createImageMenuItem);
		b.setMenu(m);
		b.setWidth(MENU_BUTTON_WIDTH);
		return b;
	}
	
	public void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(tinyMCEPanel.build());
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenter().setBottomComponent(buildButtonsPanel());
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	public void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Report Editor");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("reportIcon");
		getWindow().setCollapsible(false);
		getWindow().setMaximizable(true);
	}
	
	public VerticalPanel buildButtonsPanel() {
		
		VerticalPanel wrapper = new VerticalPanel();
		
		VerticalPanel rightPanel = new VerticalPanel();
		rightPanel.setSpacing(SPACING);
		
		HorizontalPanel topRow = new HorizontalPanel();
		topRow.setSpacing(SPACING);
		
		HorizontalPanel bottomRow = new HorizontalPanel();
		bottomRow.setSpacing(SPACING);
		
		topRow.add(buildChartMenu());
		topRow.add(buildOlapMenu());
		topRow.add(buildImageMenu());
		topRow.add(buildExportMenu());
		
		bottomRow.add(templateButton);
		bottomRow.add(previewButton);
		bottomRow.add(printButton);
		bottomRow.add(buildSaveMenu());
		
		wrapper.add(topRow);
		wrapper.add(bottomRow);
		
		return wrapper;
	}

	public TinyMCEPanel getTinyMCEPanel() {
		return tinyMCEPanel;
	}

	public Long getReportID() {
		return reportID;
	}

	public void setReportID(Long reportID) {
		this.reportID = reportID;
	}

	public MenuItem getLoadChartMenuItem() {
		return loadChartMenuItem;
	}

	public MenuItem getCreateChartMenuItem() {
		return createChartMenuItem;
	}

	public MenuItem getLoadOlapMenuItem() {
		return loadOlapMenuItem;
	}

	public MenuItem getCreateOlapMenuItem() {
		return createOlapMenuItem;
	}

	public MenuItem getLoadMapMenuItem() {
		return loadMapMenuItem;
	}

	public MenuItem getCreateMapMenuItem() {
		return createMapMenuItem;
	}

	public MenuItem getLoadImageMenuItem() {
		return loadImageMenuItem;
	}

	public MenuItem getCreateImageMenuItem() {
		return createImageMenuItem;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public Button getTemplateButton() {
		return templateButton;
	}

	public Button getPreviewButton() {
		return previewButton;
	}

	public Button getPrintButton() {
		return printButton;
	}

	public Button getFullScreenButton() {
		return fullScreenButton;
	}
	
}