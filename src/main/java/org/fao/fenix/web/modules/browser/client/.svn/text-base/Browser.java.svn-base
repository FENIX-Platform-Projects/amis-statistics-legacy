package org.fao.fenix.web.modules.browser.client;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class Browser implements EntryPoint {

	private final int SPACING = 5;
	
	private final String SCREEN_WIDTH = "800px";
	
	private final String LABEL_WIDTH = "60px";
	
	private final String FIELD_WIDTH = "150px";
	
	private Html login;
	
	private Html tools;
	
	private Html language;
	
	private Html help;
	
	private Html products;
	
	private Html usernameLabel;
	
	private Html passwordLabel;
	
	private Html institutionTop;
	
	private Html institutionBottom;
	
	private Html linksTop;
	
	private Html linksBottom;
	
	private Html linksBottomBottom;
	
	private TextField<String> usernameField;
	
	private TextField<String> passwordField;
	
	private Portal portal;
	
	public void onModuleLoad() {
		RootPanel.get("MENU_BAR").add(menuBar());
//		RootPanel.get("SUB_MENU_BAR").add(subMenuBar());
		RootPanel.get("SEARCH_BAR").add(searchBar());
		RootPanel.get("LINKS").add(links());
		RootPanel.get("RESULTS").add(results());
	}
	
	private VerticalPanel results() {
		
		VerticalPanel p = new VerticalPanel();
		p.setWidth(SCREEN_WIDTH);
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		portal = new Portal(3);
		portal.setBorders(false);
		portal.setStyleAttribute("backgroundColor", "#FFFFFF");
		portal.setSpacing(10);
		portal.setWidth(SCREEN_WIDTH);
		
		portal.add(createPortlet("Projects", "projectIcon"), 0);
		portal.add(createPortlet("Datasets", "tableIcon"), 0);
		
		portal.add(createPortlet("Maps", "mapIcon"), 1);
		portal.add(createPortlet("Reports", "reportIcon"), 1);
		
		portal.add(createPortlet("Pivot Tables", "olap"), 2);
		portal.add(createPortlet("Charts", "chartIcon"), 2);
		
		p.add(portal);
		return p;
	}
	
	private Portlet createPortlet(String title, String iconStyle) {
		Portlet p = new Portlet();
		p.setHeading(title);
		p.setIconStyle(iconStyle);
		configPanel(p);
		p.setLayout(new FitLayout());
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.setSize("250px", "125px");
		p.setSize(250, 125);
		wrapper.setScrollMode(Scroll.AUTO);
		if (iconStyle.equals("projectIcon")) {
			wrapper.add(resource("After Earthquake Analysis", "CNSA", "toolBox-images/projectToolbox.gif"));
			wrapper.add(resource("Population Census", "OCHA", "toolBox-images/projectToolbox.gif"));
			wrapper.add(resource("Crop Monitoring", "FAO", "toolBox-images/projectToolbox.gif"));
			wrapper.setSize("250px", "75px");
			p.setSize(250, 75);
		} else if (iconStyle.equals("mapIcon")) {
			wrapper.add(resource("Livelihood Zones", "FEWSNET", "toolBox-images/mapToolbox.gif"));
			wrapper.add(resource("GAUL 4", "FAO", "toolBox-images/mapToolbox.gif"));
			wrapper.add(resource("Crop Calendars", "FAO", "toolBox-images/mapToolbox.gif"));
			wrapper.add(resource("Agroecological Zones", "FEWSNET", "toolBox-images/mapToolbox.gif"));
			wrapper.add(resource("Population Density", "OCHA", "toolBox-images/mapToolbox.gif"));
			wrapper.add(resource("Distribution Centres", "OCHA", "toolBox-images/mapToolbox.gif"));
			wrapper.setSize("250px", "175px");
			p.setSize(250, 175);
		} else if (iconStyle.equals("reportIcon")) {
			wrapper.add(resource("Food Security Update", "FAO", "toolBox-images/reportToolbox.gif"));
			wrapper.add(resource("Country Brief", "FAO-EC", "toolBox-images/reportToolbox.gif"));
			wrapper.add(resource("Education", "UNICEF", "toolBox-images/reportToolbox.gif"));
			wrapper.setSize("250px", "75px");
			p.setSize(250, 75);
		} else if (iconStyle.equals("chartIcon")) {
			wrapper.add(resource("Market Prices", "FAO", "toolBox-images/chartToolbox.gif"));
			wrapper.add(resource("Rainfall Pattern", "NOAA", "toolBox-images/chartToolbox.gif"));
			wrapper.add(resource("Population DIstribution", "OCHA", "toolBox-images/chartToolbox.gif"));
			wrapper.add(resource("Population Growth", "OCHA", "toolBox-images/chartToolbox.gif"));
			wrapper.add(resource("Aid Effectiveness", "CNSA", "toolBox-images/chartToolbox.gif"));
			wrapper.setSize("250px", "175px");
			p.setSize(250, 175);
		} else if (iconStyle.equals("olap")) {
			wrapper.add(resource("Food Security Update", "FAO", "toolBox-images/olap.png"));
			wrapper.add(resource("Country Brief", "FAO-EC", "toolBox-images/olap.png"));
			wrapper.add(resource("Education", "UNICEF", "toolBox-images/olap.png"));
			wrapper.setSize("250px", "75px");
			p.setSize(250, 75);
		} else if (iconStyle.equals("tableIcon")) {
			wrapper.add(resource("Food Security Update", "FAO", "toolBox-images/tableToolbox.gif"));
			wrapper.add(resource("Country Brief", "FAO-EC", "toolBox-images/tableToolbox.gif"));
			wrapper.add(resource("Education", "UNICEF", "toolBox-images/tableToolbox.gif"));
			wrapper.add(resource("Food Security Update", "FAO", "toolBox-images/tableToolbox.gif"));
			wrapper.add(resource("Country Brief", "FAO-EC", "toolBox-images/tableToolbox.gif"));
			wrapper.add(resource("Education", "UNICEF", "toolBox-images/tableToolbox.gif"));
			wrapper.setSize("250px", "175px");
			p.setSize(250, 175);
		}
		p.add(wrapper);
		return p;
	}
	
	private HorizontalPanel resource(String title, String source, String icon) {
		HorizontalPanel w = new HorizontalPanel();
		w.setSpacing(SPACING);
		w.setHorizontalAlign(HorizontalAlignment.LEFT);
		Image type = new Image(icon);
		w.add(type);
		VerticalPanel titleSource = new VerticalPanel();
		Html t1 = new Html("<div style='font-family: sans-serif; font-size: 10pt; color: #1D4589; text-decoration:underline; font-weight: bold; '>" + title + "</div>");
		Html t2 = new Html("<div style='font-family: sans-serif; font-size:  8pt; color: #1D4589; font-style: italic; '>source: " + source + "</div>");
		titleSource.add(t1);
		titleSource.add(t2);
		w.add(titleSource);
		return w;
	}
	
	private void configPanel(final ContentPanel p) {
		p.setCollapsible(true);
//		p.setBodyBorder(false);
//		p.setBorders(false);
		p.setShadow(false);
		p.setFrame(false);
	}
	
	private VerticalPanel searchBar() {
		VerticalPanel p = new VerticalPanel();
		p.setWidth(SCREEN_WIDTH);
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.add(logos());
		p.add(searchbar());
		p.add(filters());
		return p;
	}
	
	private HorizontalPanel logos() {
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(SCREEN_WIDTH);
		p.setSpacing(SPACING * 2);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Image haiti = new Image("images/HAITI.png");
		Image eu = new Image("images/EU.png");
		Image fao = new Image("images/FAO.png");
		p.add(haiti);
		p.add(institutionBanner());
		p.add(fao);
		p.add(eu);
		return p;
	}
	
	private VerticalPanel institutionBanner() {
		VerticalPanel p = new VerticalPanel();
//		p.setWidth("600px");
		institutionTop = new Html("<div style='font-family: sans-serif; font-size: 20pt; color: #1D4589; font-weight: bold; '>CNSA Workstation</div>");
		institutionBottom = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; font-style: italic; '>Coordination Nationale de la Securite Alimentaire</div>");
		p.add(institutionTop);
		p.add(institutionBottom);
		return p;
	}
	
	private VerticalPanel links() {
		VerticalPanel w = new VerticalPanel();
		w.setVerticalAlign(VerticalAlignment.MIDDLE);
		w.setWidth(SCREEN_WIDTH);
		w.setHorizontalAlign(HorizontalAlignment.CENTER);
		VerticalPanel p = new VerticalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setWidth(SCREEN_WIDTH);
		linksTop = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; '>FENIX (Food security and Early warning Network and Information Exchange) </div>");
		linksBottom = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; '>Copyright (c) 2010, by FAO of UN under the EC-FAO Food Security Information for Action Programme</div>");
		linksBottomBottom = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; '><u>FENIX Portal</u> | <u>Contact Us</u></div>");
		p.add(linksTop);
		p.add(linksBottom);
		p.add(linksBottomBottom);
		w.add(p);
		return w;
	}
	
	private HorizontalPanel filters() {
		HorizontalPanel wrapper = new HorizontalPanel();
		wrapper.setHorizontalAlign(HorizontalAlignment.RIGHT);
		wrapper.setVerticalAlign(VerticalAlignment.MIDDLE);
		wrapper.setWidth(SCREEN_WIDTH);
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(SCREEN_WIDTH); 
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		Html categories = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; '><b>Categories: </b>All ▼</div>");
		p.add(categories);
		Html countries = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; '><b>Countries: </b>All ▼</div>");
		p.add(countries);
		Html fromYear = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; '><b>From: </b>1961 ▼</div>");
		p.add(fromYear);
		Html toYear = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; '><b>From: </b>2009 ▼</div>");
		p.add(toYear);
		wrapper.add(p);
		return wrapper;
	}
	
	private HorizontalPanel searchbar() {
		HorizontalPanel wrapper = new HorizontalPanel();
		wrapper.setHorizontalAlign(HorizontalAlignment.RIGHT);
		wrapper.setVerticalAlign(VerticalAlignment.MIDDLE);
		wrapper.setWidth(SCREEN_WIDTH);
		wrapper.setSpacing(SPACING);
		TextField<String> searchbar = new TextField<String>();
		searchbar.setSize("700px", "50px");
		searchbar.setEmptyText("e.g. market prices");
		wrapper.add(searchbar);
		Button b = new Button("Search!");
		b.setSize("80px", "50px");
		wrapper.add(b);
		return wrapper;
	}
	
	private HorizontalPanel menuBar() {
		HorizontalPanel wrapper = new HorizontalPanel();
		wrapper.setHorizontalAlign(HorizontalAlignment.RIGHT);
		wrapper.setVerticalAlign(VerticalAlignment.MIDDLE);
		wrapper.setWidth(SCREEN_WIDTH);
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(SCREEN_WIDTH); 
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		help = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; text-decoration:underline;'><img src='images/help.png'> Help ▼</div>");
		p.add(help);
		products = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; text-decoration:underline;'><img src='images/report.png'> FENIX Products ▼</div>");
		p.add(products);
		tools = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; text-decoration:underline;'><img src='menu-images/wrench.png'> Tools ▼</div>");
		p.add(tools);
		language = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; text-decoration:underline;'><img src='menu-images/gb.png'> English ▼</div>");
		p.add(language);
		login = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; text-decoration:underline;'><img src='menu-images/key.png'> Login</div>");
		p.add(login);
		wrapper.add(p);
		return wrapper;
	}
	
	private HorizontalPanel subMenuBar() {
		HorizontalPanel wrapper = new HorizontalPanel();
		wrapper.setHorizontalAlign(HorizontalAlignment.RIGHT);
		wrapper.setWidth(SCREEN_WIDTH);
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setWidth(SCREEN_WIDTH);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		usernameLabel = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; font-weight: bold; '>Username</div>");
		usernameLabel.setWidth(LABEL_WIDTH);
		p.add(usernameLabel);
		usernameField = new TextField<String>();
		usernameField.setWidth(FIELD_WIDTH);
		usernameField.setEmptyText("Username");
		p.add(usernameField);
		passwordLabel = new Html("<div style='font-family: sans-serif; font-size: 8pt; color: #1D4589; font-weight: bold; '>Password</div>");
		passwordLabel.setWidth(LABEL_WIDTH);
		p.add(passwordLabel);
		passwordField = new TextField<String>();
		passwordField.setWidth(FIELD_WIDTH);
		passwordField.setPassword(true);
		passwordField.setEmptyText("Password");
		p.add(passwordField);
		wrapper.add(p);
		return wrapper;
	}
	
}