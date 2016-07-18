package org.fao.fenix.web.modules.welcome.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.control.menu.FenixSelectionPool;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.common.vo.FenixMenuItemVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.welcome.client.control.SplashWindowController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class SplashWindow extends FenixWindow {
	
	private static final String WINDOW_WIDTH = "900px";
	
	private static final String WINDOW_HEIGHT = "400px";
	
	private final static int SPACING = 10;
	
	private boolean focusedOnTools;
	
	private TabPanel tabPanel;
	
	private TabItem toolsTabItem;
	
	private TabItem infoTabItem;
	
	private TabItem searchTabItem;
	
	private VerticalPanel toolsPanel;
	
	private SplashSearchPanel splashSearchPanel;
	
	private HTML intro;
	
	private String WELCOME_SCREEN_BACKGROUND_COLOR_ONE = "#CA1616";
	
	private String WELCOME_SCREEN_BACKGROUND_COLOR_TWO = "#FFFFFF";
	
	private HorizontalPanel toolsGalleryWrapper;
	
	private VerticalPanel categoriesPanelWrapper;
	
	private VerticalPanel itemPanelWrapper;
	
	private VerticalPanel bodyWrapper;
	
	private VerticalPanel introductionHeaderWrapper;
	
	private HTML introductionHeaderHTML;
	
	private VerticalPanel toolsHeaderWrapper;
	
	private HTML toolsHeaderHTML;
	
	public SplashWindow() {
		tabPanel = new TabPanel();
		toolsPanel = new VerticalPanel();
		splashSearchPanel = new SplashSearchPanel();
		intro = new HTML();
		toolsGalleryWrapper = new HorizontalPanel();
		categoriesPanelWrapper = new VerticalPanel();
		itemPanelWrapper = new VerticalPanel();
		introductionHeaderWrapper = new VerticalPanel();
		toolsHeaderWrapper = new VerticalPanel();
		bodyWrapper = new VerticalPanel();
	}

	public void build() {
		buildCenterPanel();
		format();
		show();
		getWindow().setPosition(getWindow().getAbsoluteLeft(), getWindow().getAbsoluteTop() - 10);//50
		SplashWindowController.getIntroText(this);
		SplashWindowController.getNodeSettings(this);
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildTabPanel());	
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private TabPanel buildTabPanel() {
//		tabPanel.setSize("885px", "570px");
		tabPanel.setSize("885px", "340px");
		infoTabItem = buildIntroductionTabItem();
		tabPanel.add(infoTabItem);
		toolsTabItem = buildToolsTabItem();
		tabPanel.add(toolsTabItem);
	
		searchTabItem = new TabItem("Search");
		searchTabItem.add(splashSearchPanel.build());
		splashSearchPanel.getSearchButton().addSelectionListener(SplashWindowController.search(this));
		splashSearchPanel.getOpenResourceButton().addSelectionListener(SplashWindowController.opens(this));
		splashSearchPanel.getOpenMetaDataButton().addSelectionListener(SplashWindowController.metadatas(this));
		//tabPanel.add(searchTabItem);
		
		tabPanel.add(new TabItem("Tutorials"));
//		tabPanel.add(new TabItem("Gallery"));
		if (this.isFocusedOnTools()) {
			tabPanel.setSelection(toolsTabItem);
			SplashWindowController.getToolsAgent(this, "tools", "all");
		}
		return tabPanel;
	}
	
	private TabItem buildToolsTabItem() {
		TabItem ti = new TabItem("Tools");
		ti.add(buildToolsHeader("Tools Gallery")); 
		ti.add(buildToolsGallery());
		ti.addListener(Events.Select, SplashWindowController.getToolsListener(this, "tools", "all"));
		return ti;
	}
	
	private TabItem buildIntroductionTabItem() {
		TabItem ti = new TabItem("Introduction");
		ti.add(buildIntroductionHeader("Welcome!"));
		ti.add(buildBody());
		return ti;
	}
	
	private HorizontalPanel buildToolsGallery() {
		toolsGalleryWrapper = new HorizontalPanel();
		toolsGalleryWrapper.setSpacing(SPACING / 2);
		toolsGalleryWrapper.setStyleAttribute("backgroundColor", WELCOME_SCREEN_BACKGROUND_COLOR_ONE);
		toolsGalleryWrapper.setSize("885px", "490px");
		toolsGalleryWrapper.add(buildCategoriesPanel());
		toolsGalleryWrapper.add(buildToolsPanel());
		return toolsGalleryWrapper;
	}
	
	private VerticalPanel buildCategoriesPanel() {
		categoriesPanelWrapper = new VerticalPanel();
		categoriesPanelWrapper.setSpacing(SPACING);
		categoriesPanelWrapper.setStyleAttribute("backgroundColor", WELCOME_SCREEN_BACKGROUND_COLOR_ONE);
		categoriesPanelWrapper.setSize("150px", "450px");
		ToggleButton all = new ToggleButton("All Tools");
		all.setWidth("125px");
		all.toggle(true);
		all.setToggleGroup("toolsGallery");
		all.addSelectionListener(SplashWindowController.getTools(this, "tools", "all"));
		categoriesPanelWrapper.add(all);
		ToggleButton dataManagement = new ToggleButton("Data Management");
		dataManagement.setWidth("125px");
		dataManagement.setToggleGroup("toolsGallery");
		dataManagement.addSelectionListener(SplashWindowController.getTools(this, "tools", "dataManagement"));
		categoriesPanelWrapper.add(dataManagement);
		ToggleButton products = new ToggleButton("Products");
		products.setWidth("125px");
		products.setToggleGroup("toolsGallery");
		products.addSelectionListener(SplashWindowController.getTools(this, "tools", "products"));
		categoriesPanelWrapper.add(products);
		ToggleButton utils = new ToggleButton("Utils");
		utils.setWidth("125px");
		utils.setToggleGroup("toolsGallery");
		utils.addSelectionListener(SplashWindowController.getTools(this, "tools", "utils"));
		categoriesPanelWrapper.add(utils);
		ToggleButton security = new ToggleButton("Security");
		security.setWidth("125px");
		security.setToggleGroup("toolsGallery");
		security.addSelectionListener(SplashWindowController.getTools(this, "tools", "security"));
		categoriesPanelWrapper.add(security);
		ToggleButton administration = new ToggleButton("Administration");
		administration.setWidth("125px");
		administration.setToggleGroup("toolsGallery");
		administration.addSelectionListener(SplashWindowController.getTools(this, "tools", "administration"));
		categoriesPanelWrapper.add(administration);
		return categoriesPanelWrapper;
	}
	
	private VerticalPanel buildToolsPanel() {
		toolsPanel.setLayout(new FitLayout());
//		toolsPanel.setSpacing(SPACING);
		toolsPanel.setStyleAttribute("backgroundColor", WELCOME_SCREEN_BACKGROUND_COLOR_ONE);
//		toolsPanel.setSize("725px", "450px");
		toolsPanel.setSize("725px", "260px");
		toolsPanel.setScrollMode(Scroll.AUTO);
		return toolsPanel;
	}
	
	public void addItems(List<FenixMenuItemVo> vos) {
		toolsPanel.removeAll();
		int counter = 0;
		List<FenixMenuItemVo> row = new ArrayList<FenixMenuItemVo>();
		for (int i = 0 ; i < vos.size() ; i++) {
			row.add(vos.get(i));
			counter++;
			if (counter == 3) {
				counter = 0;
				HorizontalPanel p = buildItemRow(row);
				toolsPanel.add(p);
				row = new ArrayList<FenixMenuItemVo>();
			} else if (i == vos.size() - 1) {
				HorizontalPanel p = buildItemRow(row);
				toolsPanel.add(p);
			}
		}
		toolsPanel.getLayout().layout();
	}
	
	private HorizontalPanel buildItemRow(List<FenixMenuItemVo> vos) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(SPACING / 2);
		for (FenixMenuItemVo vo : vos)
			p.add(buildItemPanel(vo));
		return p;
	}
	
	private VerticalPanel buildItemPanel(FenixMenuItemVo vo) {
		itemPanelWrapper = new VerticalPanel();
		boolean authorisationGranted = authorisationGranted(vo);
		itemPanelWrapper.setSpacing(SPACING);
		itemPanelWrapper.setVerticalAlign(VerticalAlignment.MIDDLE);
		itemPanelWrapper.setHorizontalAlign(HorizontalAlignment.CENTER);
//		itemPanelWrapper.setSize("220px", "210px");
		itemPanelWrapper.setSize("220px", "175px");
		itemPanelWrapper.setStyleAttribute("backgroundColor", WELCOME_SCREEN_BACKGROUND_COLOR_TWO);
		HTML html = new HTML("<div style='font-size: 8pt; font-family: sans-serif; text-align: left; color: #000000; '>" +
							 "<img src='" + vo.getIconStyle() + "' align='right' width='100px' height='100px' />" +
							 "<b>" + BabelFish.print().getString(vo.getName()) + "</b><br><br>" +
							 BabelFish.print().getString(vo.getDescription()) +
							 "</div>");
//		html.setSize("200px", "160px");
		html.setSize("200px", "125px");
		itemPanelWrapper.add(html);
		Button b = new Button("Go To " + BabelFish.print().getString(vo.getName()));
		b.addSelectionListener(FenixSelectionPool.getSelectionListenerForButtons(vo.getCommand()));
		b.setEnabled(authorisationGranted);
		b.setWidth("200px");
		itemPanelWrapper.add(b);
		return itemPanelWrapper;
	}
	
	
	
	private VerticalPanel buildIntroductionHeader(String text) {
		introductionHeaderWrapper = new VerticalPanel();
		introductionHeaderWrapper.setSpacing(SPACING);
		introductionHeaderWrapper.setStyleAttribute("backgroundColor", WELCOME_SCREEN_BACKGROUND_COLOR_ONE);
		introductionHeaderHTML = new HTML("<div style='font-family: sans-serif; font-size: 28pt; color: #FFFFFF; background-color: " + WELCOME_SCREEN_BACKGROUND_COLOR_ONE + ";'>" + text + "</div>");
		introductionHeaderHTML.setSize("845px", "50px");
		introductionHeaderWrapper.add(introductionHeaderHTML);
		return introductionHeaderWrapper;
	}
	
	private VerticalPanel buildToolsHeader(String text) {
		toolsHeaderWrapper = new VerticalPanel();
		toolsHeaderWrapper.setSpacing(SPACING);
		toolsHeaderWrapper.setStyleAttribute("backgroundColor", WELCOME_SCREEN_BACKGROUND_COLOR_ONE);
		toolsHeaderHTML = new HTML("<div style='font-family: sans-serif; font-size: 28pt; color: #FFFFFF; background-color: " + WELCOME_SCREEN_BACKGROUND_COLOR_ONE + ";'>" + text + "</div>");
		toolsHeaderHTML.setSize("845px", "50px");
		toolsHeaderWrapper.add(toolsHeaderHTML);
		return toolsHeaderWrapper;
	}
	
	private VerticalPanel buildBody() {
		bodyWrapper = new VerticalPanel();
		bodyWrapper.setSpacing(SPACING);
		bodyWrapper.setStyleAttribute("backgroundColor", WELCOME_SCREEN_BACKGROUND_COLOR_TWO);
		bodyWrapper.setScrollMode(Scroll.AUTO);
//		HTML html = new HTML("<div style='text-align: justify; font-family: sans-serif; font-size: 12pt; color: #FFFFFF; background-color: #9FCA1B;' class='dropcap'>" + text + "</div>");
//		intro.setSize("845px", "490px");
		intro.setSize("845px", "270px");
		bodyWrapper.add(intro);
		return bodyWrapper;
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Splash Window");
		setCollapsible(false);
		getWindow().setResizable(false);
		getWindow().setIconStyle("info");
//		getWindow().setCollapsible(false);
	}

	public boolean isFocusedOnTools() {
		return focusedOnTools;
	}

	public void setFocusedOnTools(boolean focusedOnTools) {
		this.focusedOnTools = focusedOnTools;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public TabItem getToolsTabItem() {
		return toolsTabItem;
	}

	public TabItem getInfoTabItem() {
		return infoTabItem;
	}

	public VerticalPanel getToolsPanel() {
		return toolsPanel;
	}
	
	boolean authorisationGranted(FenixMenuItemVo item) {
		boolean granted = true;
		if (item.getName().equals("administration"))
			granted = FenixUser.hasAdminRole();
		if (item.getName().equals("dcmtCodesCreator"))
			granted = FenixUser.hasAdminRole();
		if (item.getName().equals("developersArea"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("dataManagement"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("integratedPhaseClassification"))
			return FenixUser.hasIpcRole();		
		if (item.getName().equals("giewsEarlyWarning"))
			return FenixUser.hasUserRole();		
		if (item.getName().equals("informationeXchange"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("esoko"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("fieldClimate"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("recreateCharts"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("faostatImporter"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("login"))
			return FenixUser.hasAnonymousRole();
		if (item.getName().equals("logout"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("fewsnetImporter"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("excelImporter"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("uploadCodingSystem"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("uploadShape"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("uploadImage"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("userManager"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("groupManager"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("permissionManager"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("userProperties"))
			return FenixUser.hasAdminRole();
		if (item.getName().equals("newOlap"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("openDataset"))
			return FenixUser.hasUserRole();
		if (item.getName().equals("openImage"))
			return FenixUser.hasUserRole();
		return granted;
	}

	public TabItem getSearchTabItem() {
		return searchTabItem;
	}

	public SplashSearchPanel getSplashSearchPanel() {
		return splashSearchPanel;
	}

	public HTML getIntro() {
		return intro;
	}

	public String getWELCOME_SCREEN_BACKGROUND_COLOR_ONE() {
		return WELCOME_SCREEN_BACKGROUND_COLOR_ONE;
	}

	public void setWELCOME_SCREEN_BACKGROUND_COLOR_ONE(String wELCOMESCREENBACKGROUNDCOLORONE) {
		WELCOME_SCREEN_BACKGROUND_COLOR_ONE = wELCOMESCREENBACKGROUNDCOLORONE;
	}

	public String getWELCOME_SCREEN_BACKGROUND_COLOR_TWO() {
		return WELCOME_SCREEN_BACKGROUND_COLOR_TWO;
	}

	public void setWELCOME_SCREEN_BACKGROUND_COLOR_TWO(String wELCOMESCREENBACKGROUNDCOLORTWO) {
		WELCOME_SCREEN_BACKGROUND_COLOR_TWO = wELCOMESCREENBACKGROUNDCOLORTWO;
	}

	public HorizontalPanel getToolsGalleryWrapper() {
		return toolsGalleryWrapper;
	}

	public VerticalPanel getCategoriesPanelWrapper() {
		return categoriesPanelWrapper;
	}

	public VerticalPanel getItemPanelWrapper() {
		return itemPanelWrapper;
	}

	public VerticalPanel getBodyWrapper() {
		return bodyWrapper;
	}

	public VerticalPanel getIntroductionHeaderWrapper() {
		return introductionHeaderWrapper;
	}

	public void setIntroductionHeaderWrapper(VerticalPanel introductionHeaderWrapper) {
		this.introductionHeaderWrapper = introductionHeaderWrapper;
	}

	public HTML getIntroductionHeaderHTML() {
		return introductionHeaderHTML;
	}

	public void setIntroductionHeaderHTML(HTML introductionHeaderHTML) {
		this.introductionHeaderHTML = introductionHeaderHTML;
	}

	public VerticalPanel getToolsHeaderWrapper() {
		return toolsHeaderWrapper;
	}

	public void setToolsHeaderWrapper(VerticalPanel toolsHeaderWrapper) {
		this.toolsHeaderWrapper = toolsHeaderWrapper;
	}

	public HTML getToolsHeaderHTML() {
		return toolsHeaderHTML;
	}

	public void setToolsHeaderHTML(HTML toolsHeaderHTML) {
		this.toolsHeaderHTML = toolsHeaderHTML;
	}
	
}