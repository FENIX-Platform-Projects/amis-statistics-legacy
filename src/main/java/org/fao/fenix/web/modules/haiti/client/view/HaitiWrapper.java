package org.fao.fenix.web.modules.haiti.client.view;

import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;

public class HaitiWrapper {

	private ContentPanel wrapper;
	
	private final String DEFAULT_WIDTH = "1100px";
	
	public HaitiWrapper() {
		wrapper = new ContentPanel();
		wrapper.setHeaderVisible(false);
	}
	
	public ContentPanel build(String width, String language) {
		if (width != null)
			wrapper.setWidth(width);
		else
			wrapper.setWidth(DEFAULT_WIDTH);
		wrapper.setTopComponent(buildTopPanel(width, language));
		
		wrapper.add(buildFlagsPanel(width, language));
		
		wrapper.setBottomComponent(buildBottomPanel(width, language));
		return wrapper;
	}
	
	private HorizontalPanel buildFlagsPanel(String width, String language) {
		
		HorizontalPanel flagsPanel = new HorizontalPanel();
		flagsPanel.setSpacing(3);
		flagsPanel.setStyleAttribute("background-color", "#D0DDED");
		
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
		menu.add(big_french);
		menu.add(medium_french);
		menu.add(small_french);
		
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
		
		flagsPanel.add(spacerPanel);
		flagsPanel.add(contactUsButton);
		flagsPanel.add(manualButton);
		flagsPanel.add(buttonsPanel);
		
		return flagsPanel;
	}
	
	private String calculateToolbarSpacerWidth(String screenWidth) {
		int w = Integer.parseInt(screenWidth.substring(0, screenWidth.length() - 2));
		return String.valueOf(w - 340) + "px";
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
		HTML label = new HTML("<div style='font-size: 20pt; color: #1D4589; font-weight: bold; '>" + HaitiLangEntry.getInstance(language).toolName() + "</div>");
		if (width != null)
			label.setWidth(calculateSpacerWidth(width));
		else
			label.setWidth(DEFAULT_WIDTH);
		spacer.add(label);
		
		Image faoLogo = new Image("haiti-images/fao_logo_blue_small.png");
		
		topPanel.add(giewsLogo);
		topPanel.add(spacer);
		topPanel.add(faoLogo);
		
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

	public ContentPanel getWrapper() {
		return wrapper;
	}
	
}