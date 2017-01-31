package org.fao.fenix.web.modules.venn.client.view;

import java.util.HashMap;

import org.fao.fenix.web.modules.core.client.security.LoginController;
import org.fao.fenix.web.modules.core.client.security.LoginView;
import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.datasetupload.client.view.JointLoginUploaderPanel;
import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.venn.client.control.VennController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class VennWrapper {

	private ContentPanel wrapper;
	
	private final String DEFAULT_WIDTH = "1240px";
	
	private JointLoginUploaderPanel loginUploadPanel;
	
	private VennPortalPanel vennPortalPanel;
	
	public VennWrapper() {
		wrapper = new ContentPanel();
		wrapper.setHeaderVisible(false);
	}
	
	public ContentPanel build(String width, String language, VennPortalPanel vennPortalPanel) {
		this.vennPortalPanel = vennPortalPanel;
		if (width != null)
			wrapper.setWidth(width);
		else {
			width = DEFAULT_WIDTH;
			wrapper.setWidth(DEFAULT_WIDTH);
		}
		wrapper.setTopComponent(buildTopPanel(width, language));
		
		wrapper.add(buildFlagsPanel(width, language));
		
//		wrapper.setBottomComponent(buildBottomPanel(width, language));
		return wrapper;
	}
	
	private HorizontalPanel buildFlagsPanel(String width, String language) {
		
		HorizontalPanel flagsPanel = new HorizontalPanel();
		flagsPanel.setSpacing(3);
		flagsPanel.setStyleAttribute("background-color", "#D0DDED");
		
		HorizontalPanel leftButtonsPanel = new HorizontalPanel();
		leftButtonsPanel.setSpacing(5);
		Button datasetButtons = new Button("Dataset");
		Menu datasetMenu = new Menu();
		MenuItem loadDataset = new MenuItem(HaitiLangEntry.getInstance("en").bigScreen());
		loadDataset.setIconStyle("dataset");
		loadDataset.setText("Change Interventions Dataset");
		datasetMenu.add(loadDataset);
		datasetButtons.setMenu(datasetMenu);
		leftButtonsPanel.add(datasetButtons);
		
		Button configButtons = new Button("Settings");
		Menu config = new Menu();
		MenuItem load = new MenuItem(HaitiLangEntry.getInstance("en").bigScreen());
		load.setIconStyle("load");
		load.setText("Load Configuration");
		MenuItem save = new MenuItem(HaitiLangEntry.getInstance("en").mediumScreen());
		save.setIconStyle("save");
		save.setText("Save Configuration");
		config.add(load);
		config.add(save);
		configButtons.setMenu(config);
		leftButtonsPanel.add(configButtons);
		
		/// QUICK SEARCH BY SUBSECTORS
		
		
		
		Button filterButtons = new Button("Quick Search By Subsector");
		addButtonQuickSearch(filterButtons);
		
		leftButtonsPanel.add(filterButtons);
		
		
		HorizontalPanel spacerPanel = new HorizontalPanel();
		spacerPanel.setWidth(calculateToolbarSpacerWidth(width));
		spacerPanel.setStyleAttribute("background-color", "#D0DDED");
		
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		Button settingsButton = new Button(HaitiLangEntry.getInstance(language).widthAndLanguage());
		settingsButton.setIconStyle("monitor");
		settingsButton.setWidth("125px");
		Menu menu = new Menu();
		
		MenuItem big_english = new MenuItem(HaitiLangEntry.getInstance("en").bigScreen());
		big_english.setIconStyle("english");
		big_english.addSelectionListener(HaitiController.sizeAndLanguage("650px", "1275px", "en"));
		MenuItem medium_english = new MenuItem(HaitiLangEntry.getInstance("en").mediumScreen());
		medium_english.setIconStyle("english");
		medium_english.addSelectionListener(HaitiController.sizeAndLanguage("485px", "1020px", "en"));
		MenuItem small_english = new MenuItem(HaitiLangEntry.getInstance("en").smallScreen());
		small_english.setIconStyle("english");
		small_english.addSelectionListener(HaitiController.sizeAndLanguage("485px", "795px", "en"));
		
		MenuItem big_french = new MenuItem(HaitiLangEntry.getInstance("fr").bigScreen());
		big_french.setIconStyle("french");
		big_french.addSelectionListener(HaitiController.sizeAndLanguage("650px", "1275px", "fr"));
		MenuItem medium_french = new MenuItem(HaitiLangEntry.getInstance("fr").mediumScreen());
		medium_french.setIconStyle("french");
		medium_french.addSelectionListener(HaitiController.sizeAndLanguage("485px", "1020px", "fr"));
		MenuItem small_french = new MenuItem(HaitiLangEntry.getInstance("fr").smallScreen());
		small_french.setIconStyle("french");
		small_french.addSelectionListener(HaitiController.sizeAndLanguage("485px", "795px", "fr"));
		
		menu.add(big_english);
		menu.add(medium_english);
		menu.add(small_english);
//		menu.add(big_french);
//		menu.add(medium_french);
//		menu.add(small_french);
		
		settingsButton.setMenu(menu);
		settingsButton.setWidth("175px");
		buttonsPanel.add(settingsButton);
		buttonsPanel.setStyleAttribute("background-color", "#D0DDED");
		
		Button contactUsButton = new Button(HaitiLangEntry.getInstance(language).contactUs(), new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Window.open("mailto:Info-GIEWS-Workstation@fao.org", "_blank", "");
			}
		});
		contactUsButton.setIconStyle("email");
		contactUsButton.setWidth("75px");
		
		Button manualButton = new Button(HaitiLangEntry.getInstance(language).helpOnline(), new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				Window.open("http://ldvapp07.fao.org:8030/downloads/Haiti_QuickStartGuide.pdf", "_blank", "");
			}
		});
		manualButton.setIconStyle("help");
		manualButton.setWidth("75px");
		
		Button loginbutton = new Button("Upload Data", new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				//LoginController.getInstance();
				com.extjs.gxt.ui.client.widget.Window window = new com.extjs.gxt.ui.client.widget.Window();
				loginUploadPanel = new JointLoginUploaderPanel();
				FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
				String urlJointLoginUploadServlet = ep.getJointLoginUploadServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());
				window.add(loginUploadPanel.build(urlJointLoginUploadServlet));
				window.setWidth(475);
				window.setHeading("Upload dataset");
				window.show();
			}
		});
		loginbutton.setIconStyle("login");
		loginbutton.setWidth("75px");
		
		
		Button report = new Button("Report");
		Menu standardReportMenu = new Menu();
		MenuItem standardReport = new MenuItem("Standard Report");
		standardReport.setText("Standard Report");
		standardReport.addSelectionListener(VennController.exportStandardReport(vennPortalPanel));

		standardReportMenu.add(standardReport);
		report.setMenu(standardReportMenu);
		
		
		
		flagsPanel.add(leftButtonsPanel);
		flagsPanel.add(spacerPanel);
		flagsPanel.add(report);
		flagsPanel.add(loginbutton);
		flagsPanel.add(contactUsButton);
		flagsPanel.add(manualButton);
		flagsPanel.add(buttonsPanel);
		
		
		
		return flagsPanel;
	}
	
	private String calculateToolbarSpacerWidth(String screenWidth) {
		int w = Integer.parseInt(screenWidth.substring(0, screenWidth.length() - 2));
		return String.valueOf(w - 750) + "px";
	}
	
	private HorizontalPanel buildTopPanel(String width, String language) {
		
		HorizontalPanel topPanel = new HorizontalPanel();
		if (width != null)
			topPanel.setWidth(width);
		
		Image giewsLogo = new Image("haiti-images/giews_logo_blue_small.png");
		giewsLogo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.open("http://lprapp08.fao.org/fenix-portal", "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
			}
		});
		
		
		
		HorizontalPanel spacer = new HorizontalPanel();
		if (width != null)
			spacer.setWidth(calculateSpacerWidth(width));
		else
			spacer.setWidth(calculateSpacerWidth(DEFAULT_WIDTH));
		spacer.setVerticalAlign(VerticalAlignment.MIDDLE);
		spacer.setHorizontalAlign(HorizontalAlignment.CENTER);
		HTML label = new HTML("<div style='font-size: 20pt; color: #1D4589; font-weight: bold; '> ADAM Tool </div>");
		if (width != null)
			label.setWidth(calculateSpacerWidth(width));
		else
			label.setWidth(DEFAULT_WIDTH);
		spacer.add(label);
		
		Image faoLogo = new Image("haiti-images/fao_logo_blue_small.png");
		
		topPanel.add(giewsLogo);
		topPanel.add(spacer);
//		topPanel.add(faoLogo);
		
		return topPanel;
	}
	
	private String calculateSpacerWidth(String width) {
		int spacer = Integer.parseInt(width.substring(0, width.length() - 2));
		return String.valueOf((spacer - 180)) + "px";
	}
	
	private HorizontalPanel buildBottomPanel(String width, String language) {
		HorizontalPanel bottomPanel = new HorizontalPanel();
		if (width != null)
			bottomPanel.setWidth(width);
		bottomPanel.setSpacing(5);
		HTML label = new HTML("<div style='font-size: 8pt; font-family: serif; background-color: #D0DDED; '>" +
							  "<b>" + HaitiLangEntry.getInstance(language).acknowledgement() + ": </b> " +
							  "<a href='http://www.cnigs.ht/' target='_blank'>CNIGS/MPCE (4, rue Faustin 1er (PAP-Turgeau/Haïti)</a> " +
							  "<a href='http://www.fews.net/' target='_blank'>USAID/FEWSNET</a> " +
							  "<a href='http://www.aecid.es/' target='_blank'>AECID Haiti</a> " +
							  "<a href='http://www.proyectoaraucaria.cl/' target='_blank'>Proyecto Araucaria</a> " +
							  "<a href='http://www.openstreetmap.org/' target='_blank'>OpenStreetMap</a> " +
							  "<a href='https://gist.itos.uga.edu/' target='_blank'>GIST</a> " +
							  "<br>" +
							  HaitiLangEntry.getInstance(language).poweredBy() + 
							  "</div>");
		
		if (width != null)
			label.setWidth(width);
		else
			label.setWidth(DEFAULT_WIDTH);
		bottomPanel.add(label);
		return bottomPanel;
	}
	
	private void addButtonQuickSearch(Button filterButtons) {
		Menu filter = new Menu();

		addquickSearchEntry(filter, "filter_agriculture", "Agriculture, Forestry and Fishing", vennPortalPanel);
		addquickSearchEntry(filter, "filter_health", "Health", vennPortalPanel);
		addquickSearchEntry(filter, "filter_all", "All", vennPortalPanel);
		
		filterButtons.setMenu(filter);
	}
	
	private void addquickSearchEntry(Menu filter, String filterString, String title, VennPortalPanel vennPortalPanel) {
		MenuItem menuItem = new MenuItem();
		menuItem.setText(title);
		HashMap<String, String> filters = new HashMap<String, String>();
		filters.put(filterString, title);
		menuItem.addSelectionListener(VennController.byCategories(filters, title, vennPortalPanel, vennPortalPanel.getCenterToolPanel()));
		filter.add(menuItem);
	}

	public ContentPanel getWrapper() {
		return wrapper;
	}
	
}