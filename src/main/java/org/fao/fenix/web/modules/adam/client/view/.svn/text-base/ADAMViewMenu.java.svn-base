package org.fao.fenix.web.modules.adam.client.view;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.fao.fenix.web.modules.adam.client.control.ADAMChannelsController;
//import org.fao.fenix.web.modules.adam.client.control.ADAMController;
//import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
//import org.fao.fenix.web.modules.adam.client.control.ADAMExcelTemplateController;
//import org.fao.fenix.web.modules.adam.client.control.ADAMFPMISController;
//import org.fao.fenix.web.modules.adam.client.control.DonorMatrixController;
//import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
//import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
//import org.fao.fenix.web.modules.lang.client.BabelFish;
//import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
//
//import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
//import com.extjs.gxt.ui.client.Style.Scroll;
//import com.extjs.gxt.ui.client.Style.VerticalAlignment;
//import com.extjs.gxt.ui.client.event.IconButtonEvent;
//import com.extjs.gxt.ui.client.event.SelectionListener;
//import com.extjs.gxt.ui.client.widget.HorizontalPanel;
//import com.extjs.gxt.ui.client.widget.Html;
//import com.extjs.gxt.ui.client.widget.VerticalPanel;
//import com.extjs.gxt.ui.client.widget.Window;
//import com.extjs.gxt.ui.client.widget.button.Button;
//import com.extjs.gxt.ui.client.widget.button.IconButton;
//import com.extjs.gxt.ui.client.widget.form.ComboBox;
//import com.extjs.gxt.ui.client.widget.form.TextField;
//import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
//import com.extjs.gxt.ui.client.widget.menu.Menu;
//import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
//import com.extjs.gxt.ui.client.widget.menu.MenuItem;
//import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
//import com.google.gwt.user.client.ui.Image;


public class ADAMViewMenu {

//	private static VerticalPanel titlePanel;
//	
//	private VerticalPanel functionsPanel;
//	
//	private static VerticalPanel fullDescription;
//	
//	private static ADAMViewMenuDescription countriesDescription;
//	
//	private static ADAMViewMenuDescription donorsDescription;
//	
//	private static ADAMViewMenuDescription sectorsDescription;
//	
//	private static ADAMViewMenuDescription channelsDescription;
//	
//	private static HorizontalPanel waitingPanel;
//	
//	private static Html waitingTitle = new Html("Loading..");
//	
//	
//	public ADAMViewMenu() {
////		mainMenuBar = new MenuBar(false);
//		titlePanel = new VerticalPanel();
//		functionsPanel = new VerticalPanel();
//		fullDescription = new VerticalPanel();
//
//	}
//	
//	public static VerticalPanel buildTitle(String title) {
//		VerticalPanel p = new VerticalPanel();
//		p.setHorizontalAlign(HorizontalAlignment.CENTER);
//		//p.setSpacing(5);
//		p.add(buildTitlePanel(title));
//		return p;
//	}
//	
//	public static VerticalPanel buildDescriptionPanel(Map<String, String> countries, Map<String, String> donors, Map<String, String> sectors, Map<String, String> channels ) {
//		VerticalPanel p = new VerticalPanel();
//		p.setHorizontalAlign(HorizontalAlignment.CENTER);
//		//p.setSpacing(5);
//		p.add(buildFullDescriptionPanel(countries, donors, sectors, channels));
//		return p;
//	}
//	
//	public static VerticalPanel buildTitlePanel(String title) {
//		VerticalPanel p = new VerticalPanel();
//		HorizontalPanel h = new HorizontalPanel();
//		h.setSpacing(5);
//		
//		IconButton icon = new IconButton("moreIcon");
//		icon.setHeight(20);
//		icon.addSelectionListener(expandPanel());
//		icon.setToolTip("Show all selected information");
//		h.add(icon);
//		
//		
//		titlePanel = new VerticalPanel();
//		titlePanel.setVerticalAlign(VerticalAlignment.BOTTOM);
//		p.setHorizontalAlign(HorizontalAlignment.LEFT);
//		Html html = new Html("<div class='viewmenu-title' valign='bottom'> " + title + "</div>");
//		titlePanel.add(html);
//		//titlePanel.add(buildFullDescriptionPanel(countries, donors, sectors));
//		h.add(titlePanel);
//		
//		p.add(h);
//		return p;
//	}
//	
//	public static VerticalPanel buildDescriptionsPanel(String title, Map<String, String> countries, Map<String, String> donors, Map<String, String> sectors, Map<String, String> channels ) {
//		VerticalPanel p = new VerticalPanel();
//		HorizontalPanel h = new HorizontalPanel();
//		h.setSpacing(5);
//		
//		IconButton icon = new IconButton("moreIcon");
//		icon.setHeight(20);
//		icon.addSelectionListener(expandPanel());
//		icon.setToolTip("Show all informations");
//		h.add(icon);
//		
//		
//		titlePanel = new VerticalPanel();
//		p.setHorizontalAlign(HorizontalAlignment.LEFT);
//		Html html = new Html("<div class='viewmenu-title'> " + title + "</div>");
//		titlePanel.add(html);
//		titlePanel.add(buildFullDescriptionPanel(countries, donors, sectors, channels));
//		h.add(titlePanel);
//		
//		p.add(h);
//		return p;
//	}
//	
//	public static SelectionListener<IconButtonEvent> expandPanel() {
//		return new SelectionListener<IconButtonEvent>() {
//			public void componentSelected(IconButtonEvent ce) {
//				if ( fullDescription.isVisible() )
//					fullDescription.setVisible(false);
//				else
//					fullDescription.setVisible(true);
//			}
//		};
//	}
//	
//	public static VerticalPanel buildFullDescriptionPanel(Map<String, String> countries, Map<String, String> donors, Map<String, String> sectors,  Map<String, String> channels ) {
//
//		fullDescription = new VerticalPanel();
//		
//		fullDescription.setSpacing(2);
//		countriesDescription = new ADAMViewMenuDescription();
//		donorsDescription = new ADAMViewMenuDescription();
//		sectorsDescription = new ADAMViewMenuDescription();
//		channelsDescription = new ADAMViewMenuDescription();
//	
//		fullDescription.add(countriesDescription.build("Selected Recipient Countries: ", getDescription(countries)));
//		fullDescription.add(donorsDescription.build("Selected Resource Partners: ", getDescription(donors)));
//		fullDescription.add(sectorsDescription.build("Selected Sectors: ", getDescription(sectors)));
////		fullDescription.add(channelsDescription.build("Selected Implementing Agencies: ", getDescription(channels)));
//
//		fullDescription.setVisible(true); // expanded by default
//		return fullDescription;
//	}
//	
//	
//	
//	private static String getDescription(Map<String, String> map ) {
//		if ( map == null )
//			return "All";
//		else if ( map.isEmpty() ) {
//			return "All";
//		}
//		
//		StringBuffer text = new StringBuffer();
//		text.append("<div class='viewmenu-content'>");
//		int i=0;
//		for(String key : map.keySet()) {
//			text.append(map.get(key));
//			if (i != map.size() -1)
//				text.append(", ");
//			i++;
//		}
//		text.append("</div>");
//		return text.toString();
//	}
//		
//	
//	public static HorizontalPanel buildLeftToolbar(Boolean showProjects) {
//		HorizontalPanel p = new HorizontalPanel();
//		ToolBar toolbar = new ToolBar();
//		toolbar.setBorders(false);
//		p.setHorizontalAlign(HorizontalAlignment.RIGHT);
//		p.setSpacing(5);
//		
//		p.add(toolbar);
//		
//	    Button item1 = new Button("Button w/ Menu");  
////	    item1.setIcon(Resources.ICONS.menu_show());  
//	  
////	    List<Stock> list = TestData.getStocks();  
////	    final ListStore<Stock> store = new ListStore<Stock>();  
////	    store.add(list);  
//	  
////	    final ComboBox<Stock> combo = new ComboBox<Stock>();  
////	    combo.setFieldLabel("Company");  
////	    combo.setDisplayField("name");  
////	    combo.setName("name");  
////	    combo.setValueField("symbol");  
////	    combo.setForceSelection(true);  
////	    combo.setStore(store);  
////	    combo.setTriggerAction(TriggerAction.ALL);  
//	  
////	    AdapterMenuItem adapter = new AdapterMenuItem(combo);  
////	    adapter.setManageFocus(true);  
////	    adapter.setCanActivate(true);  
//	      
//	    Menu menu = new Menu();  
//	    menu.add(new MenuItem("aosijdoij1"));  
//	    menu.add(new MenuItem("aosijdoij2"));  
//	  
////	    CheckMenuItem menuItem = new CheckMenuItem("I Like Cats");  
////	    menuItem.setChecked(true);  
////	    menu.add(menuItem);  
//	  
////	    menuItem = new CheckMenuItem("I Like Dogs");  
////	    menu.add(menuItem);  
//	    item1.setMenu(menu);  
//		
//	    toolbar.add(item1);
////		p.add(buildExcelExportPanel());
////
////		p.add(buildReportPanel());
////
////		if( !ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) ) {
////			p.add(buildFPMISPanel());
////			p.add(buildChannelsPanel());
////			p.add(buildFAOView());
////		}
////		
////		p.add(buildShowProjects(showProjects));
////		p.add(buildAddResource());
////	
////		p.add(buildWaitingPanel());
////		hideWaitingPanel();
//		
//		
//		
//		return p;
//	}
//	
//	public static HorizontalPanel buildRightPanel(Boolean showProjects) {
//		HorizontalPanel p = new HorizontalPanel();
//		p.setHorizontalAlign(HorizontalAlignment.RIGHT);
//		p.setVerticalAlign(VerticalAlignment.BOTTOM);
//		p.setSpacing(5);
//
//		p.add(buildExcelExportPanel());
//
//		p.add(buildReportPanel());
//
//		if( !ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) ) {
//			p.add(buildFPMISPanel());
//			p.add(buildChannelsPanel());
//			p.add(buildFAOView());
//		}
//		
//		p.add(buildShowProjects(showProjects));
//		p.add(buildAddResource());
//	
//		p.add(buildWaitingPanel());
//		hideWaitingPanel();
//		
//		
//		
//		return p;
//	}
//	
//	private static HorizontalPanel buildFAOView() {
//		HorizontalPanel p = new HorizontalPanel();
//		Button button = new Button("SHOW FAO Implementation(OECD/DAC)");
//		button.setIconStyle("fao_icon");
//		button.addSelectionListener(ADAMCustomController.createFAOView(button));
//		p.add(button);
//		return p;
//	}
//	
//	
//
//	private static HorizontalPanel buildExcelExportPanel() {
//		HorizontalPanel p = new HorizontalPanel();
//		Button button = new Button("Export Excel");
//		button.setIconStyle("sendToExcel");
//		button.addSelectionListener(ADAMExcelTemplateController.createExcelTemplateExport());
//		p.add(button);
//		return p;
//	}
//	
//	private static HorizontalPanel buildChannelsPanel() {
//		HorizontalPanel p = new HorizontalPanel();
//		Button button = new Button("Show Agencies...");
//		button.setIconStyle("mapIcon");
//		button.addSelectionListener(ADAMChannelsController.createChannelsView(button));
//		p.add(button);
//		return p;
//	}
//	
//	private static HorizontalPanel buildFPMISPanel() {
//		HorizontalPanel p = new HorizontalPanel();
//		Button button = new Button("Show FPMIS...");
//		button.setIconStyle("mapIcon");
//		button.addSelectionListener(ADAMFPMISController.showFPMIS(button));
//		p.add(button);
//		return p;
//	}
//	
//	private static HorizontalPanel buildReportPanel() {
//		HorizontalPanel p = new HorizontalPanel();
//		Button button = new Button("Create Report");
//		button.setIconStyle("adam_print");
//		button.addSelectionListener(ADAMCustomController.createReport());
//		p.add(button);
//		return p;
//	}
//	
//	private static HorizontalPanel buildAddResource() {
//		HorizontalPanel p = new HorizontalPanel();
//		Button button = new Button("Create Resource");
//		button.setIconStyle("adam_add");
//		button.addSelectionListener(ADAMCustomController.createCustomResourceButtonEvent());
//		p.add(button);
//		return p;
//	}
//	
//	private static HorizontalPanel buildShowProjects(Boolean show) {
//		HorizontalPanel p = new HorizontalPanel();
//		Button button = new Button("Show Projects");
//		button.setIconStyle("tableIcon");
//		button.addSelectionListener(ADAMCustomController.showProjectsFAOButtonEvent());
//		p.add(button);
//		return p;
//	}
//	
//	public static HorizontalPanel buildWaitingPanel() {
//		
//		waitingPanel = new HorizontalPanel();
////		waitingPanel.setWidth(250);
//		waitingPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
//		waitingPanel.setVerticalAlign(VerticalAlignment.BOTTOM);
////		waitingPanel.setSpacing(5);
//		
//		
//		VerticalPanel left = new VerticalPanel();
//		left.setSpacing(5);
//		
//		VerticalPanel right = new VerticalPanel();
//		right.setHorizontalAlign(HorizontalAlignment.RIGHT);
//		right.setSpacing(5);
//		
////		Html q = new Html("<div align='center' style='font-size: 10px; color: #224466;'> " + waitingTitle.toString() +" </div>");
////		q.setHeight("120px");
//		
//		left.add(waitingTitle);
//		left.setHorizontalAlign(HorizontalAlignment.LEFT);
////		left.add(buildQuestionsToolbar());
//		
//		Image i = new Image("adam-images/loading_small.gif");
//		i.setSize("13px", "13px");
//		
//		right.add(i);
//		
//		waitingPanel.add(left);
//		waitingPanel.add(right);
//		
//		return waitingPanel;
//	}
//	
//	public static void showWaitingPanel(String title) {
//		waitingTitle.setHtml("<div align='left' class='small-content'><b> " +title +"</b> </div>");
////		waitingTitle.setBorders(true);
//		int lenght = title.length();
////		
//		System.out.println("waiting lenght: " + lenght * 9);
//		waitingPanel.setWidth(lenght * 9);
//		waitingPanel.setBorders(true);
////		waitingPanel.setAutoWidth(true);
//		waitingPanel.layout();
//		waitingPanel.show();
//	}
//	
//	public static void hideWaitingPanel() {
////		waitingPanel.setAutoWidth(true);
//		waitingPanel.layout();
//		waitingPanel.hide();
//	}
//	
//	
//
//
//
//	public static ADAMViewMenuDescription getCountriesDescription() {
//		return countriesDescription;
//	}
//
//
//	public static ADAMViewMenuDescription getDonorsDescription() {
//		return donorsDescription;
//	}

	

	
	
  

	
	
	
	
	
}