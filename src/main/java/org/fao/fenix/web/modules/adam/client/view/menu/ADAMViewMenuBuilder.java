package org.fao.fenix.web.modules.adam.client.view.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.control.ADAMExcelTemplateController;
import org.fao.fenix.web.modules.adam.client.control.ADAMFPMISController;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMChannelsController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMFAOComparativeAdvantageController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMDonorMatrixController;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilderFAO;
import org.fao.fenix.web.modules.adam.client.view.ADAMViewMenuDescription;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.ui.Image;


public class ADAMViewMenuBuilder {

//	private MenuBar mainMenuBar;
	
	private static VerticalPanel titlePanel;
	
	private VerticalPanel functionsPanel;
	
	private static VerticalPanel fullDescription;
	
	private static ADAMViewMenuDescription countriesDescription;
	
	private static ADAMViewMenuDescription donorsDescription;
	
	private static ADAMViewMenuDescription sectorsDescription;
	
	private static ADAMViewMenuDescription subSectorsDescription;
	
	private static ADAMViewMenuDescription orDisclaimer;
	
	private static ADAMViewMenuDescription channelsDescription;
	
	private static HorizontalPanel waitingPanel;
	
	private static String DARK_BLUE = "#003A6C";
	private static String LIGHT_BLUE = "#589BCC";
	
	

	
	private static Html waitingTitle = new Html("Loading..");
	
	
	public ADAMViewMenuBuilder() {
//		mainMenuBar = new MenuBar(false);
		titlePanel = new VerticalPanel();
		functionsPanel = new VerticalPanel();
		fullDescription = new VerticalPanel();
	}
	
	public static VerticalPanel buildTitle(String title) {
		VerticalPanel p = new VerticalPanel();
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		//p.setSpacing(5);
		p.add(buildTitlePanel(title));
		return p;
	}
	
	public static HorizontalPanel buildDescriptionPanel(Map<String, String> countries, Map<String, String> donors, Map<String, String> sectors, Map<String, String> subSectors, Map<String, String> channels ) {
		HorizontalPanel p = new HorizontalPanel();
		//p.setStyleName("selected-items-panel");
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setSpacing(5);
		p.add(buildFullDescriptionPanel(countries, donors, sectors, subSectors, channels));
		return p;
	}
	
	public static VerticalPanel buildTitlePanel(String title) {
		VerticalPanel p = new VerticalPanel();
		
		HorizontalPanel h = new HorizontalPanel();
		h.setStyleAttribute("padding-top", "5px");
		
		//h.setSpacing(5);
		
		IconButton icon = new IconButton("moreIcon");
		icon.setHeight(20);
		icon.addSelectionListener(expandPanel());
		icon.setToolTip("Click to Show/Hide all selected items");
		h.add(icon);
		
		
		titlePanel = new VerticalPanel();
		titlePanel.setVerticalAlign(VerticalAlignment.BOTTOM);
		titlePanel.setStyleAttribute("padding-top", "4px");
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		//Html html = new Html("<div class='viewmenu-title' valign='bottom'> " + title + "</div>");
		
		String label = "Selected Items";
		if(ADAMController.selectedTab.equals(ADAMCurrentVIEW.ANALYSE.name())){
			label += " for Analysis:";
		} else {
			label += ":";
		}
		
		Html html = new Html("<div class='viewmenu-title' valign='bottom' style='text-indent:5px'> "+label+"</div>");
		html.setId("SELECTED_ITEMS_TITLE");
		html.addListener(Events.OnClick, expandPanel());
		titlePanel.add(html);
		//titlePanel.add(buildFullDescriptionPanel(countries, donors, sectors));
		h.add(titlePanel);
		
		p.add(h);
		return p;
	}
	
	/*public static VerticalPanel buildDescriptionsPanel(String title, Map<String, String> countries, Map<String, String> donors, Map<String, String> sectors, Map<String, String> subSectors, Map<String, String> channels ) {
		VerticalPanel p = new VerticalPanel();
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		
		IconButton icon = new IconButton("moreIcon");
		icon.setHeight(20);
		icon.addSelectionListener(expandPanel());
		icon.setToolTip("Show all informations");
		h.add(icon);
		
		
		titlePanel = new VerticalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		Html html = new Html("<div class='viewmenu-title'> " + title + "</div>");
		titlePanel.add(html);
		titlePanel.add(buildFullDescriptionPanel(countries, donors, sectors, subSectors, channels));
		h.add(titlePanel);
		
		p.add(h);
		return p;
	}
	*/
	public static SelectionListener<IconButtonEvent> expandPanel() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				if ( fullDescription.isVisible() )
					fullDescription.setVisible(false);
				else
					fullDescription.setVisible(true);
			}
		};
	}
	
	
	public static Listener<BaseEvent> expand() {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {
				if ( fullDescription.isVisible() )
					fullDescription.setVisible(false);
				else
					fullDescription.setVisible(true);
			}
		};
	}
	
	
	public static VerticalPanel buildFullDescriptionPanel(Map<String, String> countries, Map<String, String> donors, Map<String, String> sectors, Map<String, String> subSectors, Map<String, String> channels ) {

		fullDescription = new VerticalPanel();
		fullDescription.setSpacing(2);
		countriesDescription = new ADAMViewMenuDescription();
		donorsDescription = new ADAMViewMenuDescription();
		sectorsDescription = new ADAMViewMenuDescription();
		subSectorsDescription = new ADAMViewMenuDescription();
		channelsDescription = new ADAMViewMenuDescription();
		orDisclaimer = new ADAMViewMenuDescription();
	
		String sectorTitle = "Sectors: ";
		String subSetorTitle = "Sub-Sectors: ";
			
		if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) || ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS)) {
			sectorTitle = "SOs: ";
			subSetorTitle = "ORs: ";
		}
			
		fullDescription.add(countriesDescription.build("Recipient Countries: ", getDescription(countries)));
		fullDescription.add(donorsDescription.build("Resource Partners: ", getDescription(donors)));		
		fullDescription.add(sectorsDescription.build(sectorTitle, getDescription(sectors)));
		fullDescription.add(subSectorsDescription.build(subSetorTitle, getSubSectorDescription(subSectors)));
		
		if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
			fullDescription.add(orDisclaimer.buildORDisclaimer());

		
		//		fullDescription.add(channelsDescription.build("Selected Implementing Agencies: ", getDescription(channels)));

		fullDescription.setVisible(true); // expanded by default
		return fullDescription;
	}
	
	public static String getSubSectorDescription(Map<String, String> map ) {
		
		System.out.println("************************************* getSubSectorDescription map = "+ map);
		
		if (map == null) {
				
			if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) && ADAMBoxMaker.sectorList!=null && ADAMBoxMaker.sectorList.getValue()!=null &&  ADAMBoxMaker.sectorList.getValue().equals(ADAMBoxMaker.sectorStore.findModel("gaulCode", "Dac_9999")))
		        return "Click <a class='general-link' href='adam-docs/adam_fao_sector_breakdown.pdf' target='_blank'>here</a> for FAO Related Sub-Sectors";
			else
				return "All";
			
		}	
		else if ( map.isEmpty() ) {
			if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW) && ADAMBoxMaker.sectorList!=null &&  ADAMBoxMaker.sectorList.getValue()!=null && ADAMBoxMaker.sectorList.getValue().equals(ADAMBoxMaker.sectorStore.findModel("gaulCode", "Dac_9999")))
				return "Click <a class='general-link' href='adam-docs/adam_fao_sector_breakdown.pdf' target='_blank'>here</a> for FAO Related Sub-Sectors";
			else
				return "All";
		}
		
		StringBuffer text = new StringBuffer();
		text.append("<div class='viewmenu-content'>");
		int i=0;
		for(String key : map.keySet()) {
			text.append(map.get(key));
			if (i != map.size() -1)
				text.append(", ");
			i++;
		}
		text.append("</div>");
		return text.toString();
	}
	
	public static String getDescription(Map<String, String> map ) {
		if ( map == null)
			return "All";
		else if ( map.isEmpty() ) {
			return "All";
		}
		
		StringBuffer text = new StringBuffer();
		text.append("<div class='viewmenu-content'>");
		int i=0;
		for(String key : map.keySet()) {
			text.append(map.get(key));
			if (i != map.size() -1)
				text.append(", ");
			i++;
		}
		text.append("</div>");
		return text.toString();
	}
	
	public static String getUnFormattedDescription(Map<String, String> map ) {
		if ( map == null )
			return "All";
		else if ( map.isEmpty() ) {
			return "All";
		}
		
		StringBuffer text = new StringBuffer();
		int i=0;
		for(String key : map.keySet()) {
			text.append(map.get(key));
			if (i != map.size() -1)
				text.append(", ");
			i++;
		}
		return text.toString();
	}
	
	public static String getUnFormattedKeyDescription(Map<String, String> map ) {
		if ( map == null )
			return "All";
		else if ( map.isEmpty() ) {
			return "All";
		}
		
		StringBuffer text = new StringBuffer();
		int i=0;
		for(String key : map.keySet()) {
			text.append(key);
			if (i != map.size() -1)
				text.append(", ");
			i++;
		}
		return text.toString();
	}
		
	public static String getUnFormattedKey(Map<String, String> map ) {
		if ( map == null )
			return "All";
		else if ( map.isEmpty() ) {
			return "All";
		}
		
		StringBuffer text = new StringBuffer();
		int i=0;
		for(String key : map.keySet()) {
			text.append(key);
			if (i != map.size() -1)
				text.append(", ");
			i++;
		}
		return text.toString();
	}
	
	public static HorizontalPanel buildLeftToolbar(Boolean showProjects) {
		HorizontalPanel panel = new HorizontalPanel();  
		panel.setSpacing(5);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		panel.add(new Html("<div class='title-toolbar'>" + BabelFish.print().getString("dataSource") + ": <br>"+ BabelFish.print().getString(ADAMController.currentlySelectedDatasetCode.name()) +"</div>"));

		//		ToolBar toolBar = new ToolBar();  
//		Button btn = new Button("Cool");  
//		toolBar.add(btn);  
//		Menu menu = new Menu();  
//		menu.add(new MenuItem("Ribbons are cool"));  
//		btn.setMenu(menu);  
//		toolBar.add(new SeparatorToolItem());  
//			  
//			    
//		Button btn2 = new Button("Cool2");  
//		toolBar.add(btn2);  
//		Menu menu2 = new Menu();  
//		menu2.add(new MenuItem("Ribbons are cool"));  
//		btn2.setMenu(menu2);  
//			 
//		panel.add(toolBar);  
			  
		return panel;  
	}  
	
	public static HorizontalPanel buildRightPanel(Boolean showProjects) {
		HorizontalPanel p = new HorizontalPanel();
//		p.addStyleName("div-donor");
	//	p.setHorizontalAlign(HorizontalAlignment.RIGHT);
		p.setSpacing(5);
		
		
		ToolBar toolBar = new ToolBar();  
		toolBar.setBorders(true);
		toolBar.setStyleAttribute("border", "2px solid "+LIGHT_BLUE);
		
		p.add(toolBar);
		p.add(buildWaitingPanel());
		hideWaitingPanel();
		
		//toolBar.add(analyticalTools());
		toolBar.add(buildFAOComparativeAdvantageItem());
		
		toolBar.add(new SeparatorToolItem());  
		toolBar.add(additionalViews(showProjects));
		toolBar.add(new SeparatorToolItem());  
		toolBar.add(createResource());
		toolBar.add(new SeparatorToolItem());  
		toolBar.add(exportMenu());
	
		return p;
	}
	
	private static Button exportMenu() {
		Button button = new Button("<font color='"+DARK_BLUE+"'><b>Export as...</b></font>");
		button.setIconStyle("export");
		
		setExportMenu(button);
		return button;
	}
	
	private static void setExportMenu(Button button) {
		Menu menu = new Menu();  
		menu.add(buildExcelExportPanel());
		
		button.setMenu(menu);  
	}
	
	private static Button createResource() {
		Button button = new Button("<font color='"+DARK_BLUE+"'><b>Create...</b></font>");
		button.setIconStyle("wand");
		
		setCreateResourceMenu(button);
		return button;
	}
	
	private static void setCreateResourceMenu(Button button) {
		Menu menu = new Menu();  
	
		menu.add(buildReportPanel());
		
		/** TODO: do it in a more nice WAY!! **/
		if ( ADAMController.currentlySelectedDatasetCode.name().equalsIgnoreCase("ADAM_CRS")) {		
			menu.add(buildAddResource());
		}
	
		
		button.setMenu(menu);  
	}
	
	private static Button additionalViews(Boolean showProjects) {
		Button button = new Button("<font color='"+DARK_BLUE+"'><b>Show Additional Information</b></font>");
		button.setIconStyle("tableIcon");
		
		setAdditionalViewsMenu(button, showProjects);
		return button;
	}
	
	private static Button analyticalTools() {
		Button button = new Button("Analytical Tools");
		button.setIconStyle("gear");
		button.setStyleAttribute("color", DARK_BLUE);
		
		setAnalyticalToolsMenu(button);
		return button;
	}
	
	private static void setAdditionalViewsMenu(Button button, Boolean showProjects) {
		Menu menu = new Menu();  
		
		menu.add(buildShowProjects(showProjects));
		
		if( !ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) ) {
//			menu.add(buildFPMISPanel());
		
			/** TODO: CHANGE IT IN A MORE NICE WAY!!! **/
			if ( ADAMController.currentlySelectedDatasetCode.name().equalsIgnoreCase("ADAM_CRS") || ADAMController.currentlySelectedDatasetCode.name().equalsIgnoreCase("ADAM_CRS_AID_DATA")) {		
				menu.add(buildChannelsPanel());
				menu.add(buildFAOView());
			}
		}
	

		
		button.setMenu(menu);  
	}
	
	private static void setAnalyticalToolsMenu(Button button) {
		Menu menu = new Menu();  
		
		menu.add(buildFAOComparativeAdvantageItem());
		
	/**	if( !ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW) ) {
		
			//TODO: CHANGE IT IN A MORE NICE WAY!!! 
			if ( ADAMController.currentlySelectedDatasetCode.name().equalsIgnoreCase("ADAM_CRS") || ADAMController.currentlySelectedDatasetCode.name().equalsIgnoreCase("ADAM_CRS_AID_DATA")) {		
				menu.add(buildFAOComparativeAdvantageItem());
			}
		}**/
	
		button.setMenu(menu);  
	}
	
	private static MenuItem buildFAOView() {
		MenuItem button = new MenuItem("Add FAO Implementation (OECD/DAC)");
		button.setIconStyle("fao_icon");
//		button.addSelectionListener(ADAMCustomController.createFAOView(button));
		button.addSelectionListener(ADAMCustomController.createFAOView(button));
		return button;
	}
	
	

	private static MenuItem buildExcelExportPanel() {
		MenuItem button = new MenuItem("Export to Excel");
		button.setIconStyle("sendToExcel");
		button.addSelectionListener(ADAMExcelTemplateController.createExcelTemplateExportMenu());
		return button;
	}
	
	private static MenuItem buildChannelsPanel() {
		MenuItem button = new MenuItem("Add Implementation Agencies (OECD/DAC)");
		button.setIconStyle("tableIcon");
		//button.addSelectionListener(ADAMChannelsController.createChannelsView(button));
		return button;
	}
	
	
	private static Button buildFAOComparativeAdvantageItem() {
		Button button = new Button("<font color='"+DARK_BLUE+"'><b>Show Revealed FAO Comparative Advantage</b></font>");
		button.setIconStyle("fao_icon");
		//button.addSelectionListener(ADAMFAOComparativeAdvantageController.createADAMComparativeAdvantageView());
		return button;
	}
	
	private static MenuItem buildFAOComparativeAdvantageItemOriginal() {
		MenuItem button = new MenuItem("Show Revealed FAO Comparative Advantage");
		button.setIconStyle("fao_icon");
		//button.addSelectionListener(ADAMFAOComparativeAdvantageController.createADAMComparativeAdvantageView(button));
		return button;
	}
	
	private static MenuItem buildFPMISPanel() {
		MenuItem button = new MenuItem("Show FPMIS Projects");
		button.setIconStyle("tableIcon");
		button.addSelectionListener(ADAMFPMISController.showFPMIS(button));
//		p.add(button);
		return button;
	}
	
	private static MenuItem buildReportPanel() {
//		HorizontalPanel p = new HorizontalPanel();
		MenuItem button = new MenuItem("Create PDF Report");
		button.setIconStyle("adam_print");
		button.addSelectionListener(ADAMCustomController.createReportMenu());
//		p.add(button);
		return button;
	}
	
	private static MenuItem buildAddResource() {
		HorizontalPanel p = new HorizontalPanel();
		MenuItem button = new MenuItem("Create Customized Chart");
		button.setIconStyle("adam_add");
		button.addSelectionListener(ADAMCustomController.createCustomResourceMenuEvent());
//		p.add(button);
		return button;
	}
	
	private static MenuItem buildShowProjects(Boolean show) {
//		HorizontalPanel p = new HorizontalPanel();
		MenuItem button = new MenuItem("Show Projects");
		button.setIconStyle("tableIcon");
		//button.addSelectionListener(ADAMController.showProjects());
//		p.add(button);
		return button;
	}
	
	public static HorizontalPanel buildWaitingPanel() {
		
		waitingPanel = new HorizontalPanel();
//		waitingPanel.setWidth(250);
		waitingPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		waitingPanel.setVerticalAlign(VerticalAlignment.BOTTOM);
		waitingPanel.setStyleAttribute("backgroundColor", "white");
//		waitingPanel.setSpacing(5);
		
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		
		VerticalPanel right = new VerticalPanel();
		right.setHorizontalAlign(HorizontalAlignment.RIGHT);
		right.setSpacing(5);
		
//		Html q = new Html("<div align='center' style='font-size: 10px; color: #224466;'> " + waitingTitle.toString() +" </div>");
//		q.setHeight("120px");
		
		left.add(waitingTitle);
		left.setHorizontalAlign(HorizontalAlignment.LEFT);
//		left.add(buildQuestionsToolbar());
		
		Image i = new Image("adam-images/loading_small.gif");
		i.setSize("13px", "13px");
		
		right.add(i);
		
		waitingPanel.add(left);
		waitingPanel.add(right);
		
		return waitingPanel;
	}
	
	public static void showWaitingPanel(String title) {
		waitingTitle.setHtml("<div align='left' class='small-content'><b> " +title +"</b> </div>");
//		waitingTitle.setBorders(true);
		int lenght = title.length();
//		
		System.out.println("waiting lenght: " + lenght * 9);
		waitingPanel.setWidth(lenght * 9);
		waitingPanel.setBorders(true);
//		waitingPanel.setAutoWidth(true);
		waitingPanel.layout();
		waitingPanel.show();
	}
	
	public static void hideWaitingPanel() {
//		waitingPanel.setAutoWidth(true);
		waitingPanel.layout();
		waitingPanel.hide();
	}
	
	



	public static ADAMViewMenuDescription getCountriesDescription() {
		return countriesDescription;
	}


	public static ADAMViewMenuDescription getDonorsDescription() {
		return donorsDescription;
	}

	

	
	
  

	
	
	
	
	
}