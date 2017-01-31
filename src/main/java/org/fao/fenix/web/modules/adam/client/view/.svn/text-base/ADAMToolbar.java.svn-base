package org.fao.fenix.web.modules.adam.client.view;


import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.lang.ADAMLanguage;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.model.DatasetModel;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.HTML;

public class ADAMToolbar {

	//private MenuBar mainMenuBar;
	
	private HorizontalPanel mainMenuBar;
	
	private HorizontalPanel secondaryMenuBar;
	
	private HorizontalPanel askADAMFilters;
	
	private HorizontalPanel reportAskADAMFilters;
	
	private HorizontalPanel referencePeriodFilter;
	
	private HorizontalPanel donorMatrixFilters;
	
	private VerticalPanel reportPanel;

	private VerticalPanel reportPanelExtraInfo;
	
	private static ListStore<GaulModelData> entityStore;

	private static ComboBox<GaulModelData> entityList;
	
	private static ListStore<GaulModelData> adamCodesStore;

	private static ComboBox<GaulModelData> adamCodesList;
	
	private static Button askADAMButton;
	
	private static Button donorMatrixButton;
	
	public static ListStore<GaulModelData> fromDateStore;

	public static ComboBox<GaulModelData> fromDateList;
	
	public static ListStore<GaulModelData> toDateStore;

	public static ComboBox<GaulModelData> toDateList;
	
	private static Button referencePeriodButton;

	private static String LIST_WIDTH = "125px";
	
	private static ListStore<GaulModelData> reportStore;

	private static ComboBox<GaulModelData> reportList;
	
	private static ListStore<GaulModelData> reportEntityStore;

	private static ComboBox<GaulModelData> reportEntityList;
	
	private static ListStore<GaulModelData> reportAdamCodesStore;

	private static ComboBox<GaulModelData> reportAdamCodesList;
	
	private static Button reportButton;
	
	private HorizontalPanel reportDonorProfileFilters;
	
	public TextField<String> usernameTextField;
	
	public TextField<String> passwordTextField;
	

	
	public ADAMToolbar() {
		mainMenuBar = new HorizontalPanel();
		secondaryMenuBar = new HorizontalPanel();
		askADAMFilters = new HorizontalPanel();
		donorMatrixFilters =  new HorizontalPanel();
		referencePeriodFilter =  new HorizontalPanel();
		reportPanel =  new VerticalPanel();
		reportAskADAMFilters = new HorizontalPanel();
		reportDonorProfileFilters = new HorizontalPanel();
		
	}
	
	public HorizontalPanel removeLoginItems() {	
	    mainMenuBar.remove(mainMenuBar.getWidget(0));
		mainMenuBar.remove(mainMenuBar.getItemByItemId("logout"));
		
		ClickHtml login = new ClickHtml();
		login.setHtml(ADAMLanguage.print().signIn()+ "&nbsp;&nbsp;&nbsp;&nbsp;");
		login.setId("login");
		login.setStyleName("top-links");
		login.addListener(Events.OnClick, ADAMController.logIn(this));
		mainMenuBar.insert(login, 0);
	
	    return mainMenuBar;
	}
	
	public HorizontalPanel addLoginItems() {	
		HTML dm = new HTML(BabelFish.print().dataManagement()+"&nbsp;&nbsp;&nbsp;&nbsp;");
		dm.setStyleName("top-links-loggedin");
		dm.addClickHandler(ADAMController.openDataManagement(this));
		mainMenuBar.insert(dm, 0);
				
		HTML upload = new HTML("<div class='link'>Upload Data</div>");
		upload.addClickHandler(ADAMController.upload2(this));
		//mainMenuBar.add(upload);
		
		mainMenuBar.remove(mainMenuBar.getItemByItemId("login"));
		
		ClickHtml logout = new ClickHtml();
		logout.setHtml(BabelFish.print().logout()+"&nbsp;&nbsp;&nbsp;&nbsp;");
		logout.setId("logout");
		logout.setStyleName("top-links");
		logout.addListener(Events.OnClick, ADAMController.logOut(this));
		mainMenuBar.insert(logout, 1);
	
		
		return mainMenuBar;
	}
	public HorizontalPanel buildDatasetSelectorBar() {
		
		HorizontalPanel datasourcePanel = new HorizontalPanel();
		datasourcePanel.addStyleName("datasource");
		datasourcePanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		Html dataSource = new Html("Data Source Selection"+"&nbsp;&nbsp;");
//		dataSource.setStyleName("top-links");
		

		
		ListStore<DatasetModel> datasetStore = new ListStore<DatasetModel>();
		ComboBox<DatasetModel> datasetCombo= new ComboBox<DatasetModel>();
		datasetCombo.setTriggerAction(TriggerAction.ALL);
		datasetCombo.setStore(datasetStore);
		datasetCombo.setDisplayField("name");
		datasetCombo.setEmptyText("Please select a Data Source");
		datasetCombo.setWidth(ADAMConstants.DATASET_LIST_WIDTH);
		datasetStore.add(ADAMBoxMaker.defaultDatasetCodes());
		datasetCombo.setValue(datasetStore.getAt(0));
		datasetCombo.addSelectionChangedListener(ADAMController.setCurrentDatasetSelection(datasetCombo));
		
		
		
//		mainMenuBar.add(dataSource);
		
//		mainMenuBar.add(datasetCombo);
//		
//		mainMenuBar.add(new Html("&nbsp;&nbsp;&nbsp;&nbsp;"));
		
		datasourcePanel.add(dataSource);
		datasourcePanel.add(datasetCombo);
		
		mainMenuBar.add(datasourcePanel);
		mainMenuBar.add(new Html("&nbsp;&nbsp;&nbsp;&nbsp;"));
		
		return mainMenuBar;
	}
	
	
	public HorizontalPanel buildMainMenuBar() {
		//mainMenuBar.addItem("Create Report", ADAMController.createReport(this));
		//mainMenuBar.addItem("Ask ADAM", ADAMController.askADAM(this));
		mainMenuBar.setHorizontalAlign(HorizontalAlignment.RIGHT);
		
//		buildDatasetSelectorBar();
		
		/*ClickHtml login = new ClickHtml();
		login.setHtml(ADAMLanguage.print().signIn()+ "&nbsp;&nbsp;&nbsp;&nbsp;");
		login.setId("login");
		login.setStyleName("top-links");
		login.addListener(Events.OnClick, ADAMController.logIn(this));
		mainMenuBar.add(login);*/
		
		
		ClickHtml help = new ClickHtml();
		help.setHtml(ADAMLanguage.print().help()+"&nbsp;&nbsp;&nbsp;&nbsp;");
		help.setStyleName("top-links");
		help.addListener(Events.OnClick, ADAMController.help());
		mainMenuBar.add(help);
		
		
		ClickHtml about = new ClickHtml();
		about.setHtml(ADAMLanguage.print().about()+"&nbsp;&nbsp;&nbsp;&nbsp;");
		about.setStyleName("top-links");
		about.addListener(Events.OnClick, ADAMController.about());
		mainMenuBar.add(about);
		
		
		ClickHtml contactUs = new ClickHtml();
		contactUs.setStyleName("top-links");
		contactUs.setHtml("<a class='top-links' href='mailto:ADAM@fao.org?Subject=ADAM Queries and Feedback'>"+ADAMLanguage.print().contactUs()+ "</a>&nbsp;&nbsp;");	
		mainMenuBar.add(contactUs);
		
		return mainMenuBar;
	}
	
	public HorizontalPanel buildLoginMenu() {
		usernameTextField = new TextField<String>();
		passwordTextField = new TextField<String>();
		secondaryMenuBar.setSpacing(5);
		secondaryMenuBar.setHorizontalAlign(HorizontalAlignment.RIGHT);
		secondaryMenuBar.add(new Html("<b><font color='white'>" + ADAMLanguage.print().username() + "</font></b>"));
		usernameTextField.setHeight("20px");
		secondaryMenuBar.add(usernameTextField);
		secondaryMenuBar.setData("USERNAME", usernameTextField);
		secondaryMenuBar.add(new Html("<b><font color='white'>" + ADAMLanguage.print().password() + "</font></b>"));
		passwordTextField.setHeight("20px");
		passwordTextField.setPassword(true);
		secondaryMenuBar.add(passwordTextField);
		secondaryMenuBar.setData("PASSWORD", passwordTextField);
		IconButton b = new IconButton("moreIcon");
		b.setHeight("20px");
		b.addSelectionListener(ADAMController.loginListener(this, usernameTextField, passwordTextField));
		secondaryMenuBar.add(b);
		return secondaryMenuBar;
	}
	
	
   /*public HorizontalPanel createFilters(String buttonLabel, SelectionListener<ButtonEvent> buttonListener) {
		
		adamCodesStore = new ListStore<GaulModelData>();
		adamCodesList = new ComboBox<GaulModelData>();
		entityStore = new ListStore<GaulModelData>();
		entityList = new ComboBox<GaulModelData>();
		
		adamCodesList.setTriggerAction(TriggerAction.ALL);
		adamCodesList.setStore(adamCodesStore);
		adamCodesList.setDisplayField("gaulLabel");
		adamCodesList.setEmptyText("Please Select...");
		adamCodesList.setWidth(LIST_WIDTH);
		
		entityStore.add(new GaulModelData("Country", "Gaul_"));
		entityStore.add(new GaulModelData("Donor", "Donor_"));
		entityStore.add(new GaulModelData("Sector", "Dac_"));
		
		entityList.setTriggerAction(TriggerAction.ALL);
		entityList.setStore(entityStore);
		entityList.setDisplayField("gaulLabel");
		entityList.setEmptyText("Please Select...");
		entityList.setWidth(LIST_WIDTH);
		
		entityList.addSelectionChangedListener(ADAMController.entityListener(entityList, adamCodesStore));
		
	    askADAMButton = new Button(buttonLabel, buttonListener);
		
		askADAMFilters.setSpacing(5);
		askADAMFilters.setHorizontalAlign(HorizontalAlignment.RIGHT);
		askADAMFilters.setVerticalAlign(VerticalAlignment.MIDDLE);
		askADAMFilters.add(new Html("<b><font color='white'>About: </font></b>"));
		askADAMFilters.add(entityList);
		askADAMFilters.add(adamCodesList);
		askADAMFilters.add(askADAMButton);
		
		return askADAMFilters;
	
	}*/


	/*public HorizontalPanel buildAskADAMFilters() {
		askADAMFilters.removeAll();
		askADAMFilters = new HorizontalPanel();
		
		adamCodesStore = new ListStore<GaulModelData>();
		adamCodesList = new ComboBox<GaulModelData>();
		entityStore = new ListStore<GaulModelData>();
		entityList = new ComboBox<GaulModelData>();
		
		adamCodesList.setTriggerAction(TriggerAction.ALL);
		adamCodesList.setStore(adamCodesStore);
		adamCodesList.setDisplayField("gaulLabel");
		adamCodesList.setEmptyText("Please Select...");
		adamCodesList.setWidth(LIST_WIDTH);
	
		
		entityStore.add(new GaulModelData("Country", "Gaul_"));
		entityStore.add(new GaulModelData("Resource Partner", "Donor_"));
		entityList.setTriggerAction(TriggerAction.ALL);
		entityList.setStore(entityStore);
		entityList.setDisplayField("gaulLabel");
		entityList.setEmptyText("Please Select...");
		entityList.setWidth(LIST_WIDTH);
		entityList.addSelectionChangedListener(ADAMController.entityListener(entityList, adamCodesStore, adamCodesList));
		entityList.setValue(entityStore.getAt(0));

		
		askADAMButton = new Button("Ask!", ADAMController.askADAM(entityList, adamCodesList));
		
		askADAMFilters.setSpacing(5);
		askADAMFilters.setHorizontalAlign(HorizontalAlignment.RIGHT);
		askADAMFilters.setVerticalAlign(VerticalAlignment.MIDDLE);
		askADAMFilters.add(new Html("<b><font color='white'>About: </font></b>"));
		askADAMFilters.add(entityList);
		askADAMFilters.add(adamCodesList);
		
		askADAMFilters.add(askADAMButton);
		
		
		return askADAMFilters;
	}*/
	
	
	public HorizontalPanel buildAskADAMFiltersReport() {

		reportAskADAMFilters.removeAll();
		reportAskADAMFilters = new HorizontalPanel();
			
		reportAdamCodesStore = new ListStore<GaulModelData>();
		reportAdamCodesList = new ComboBox<GaulModelData>();
		reportEntityStore = new ListStore<GaulModelData>();
		reportEntityList = new ComboBox<GaulModelData>();
		
		reportAdamCodesList.setTriggerAction(TriggerAction.ALL);
		reportAdamCodesList.setStore(reportAdamCodesStore);
		reportAdamCodesList.setDisplayField("gaulLabel");
		reportAdamCodesList.setEmptyText("Please Select...");
		reportAdamCodesList.setWidth(LIST_WIDTH);
	
		
		reportEntityStore.add(new GaulModelData("Country", "Gaul_"));
		reportEntityStore.add(new GaulModelData("Resource Partner", "Donor_"));
		reportEntityList.setTriggerAction(TriggerAction.ALL);
		reportEntityList.setStore(reportEntityStore);
		reportEntityList.setDisplayField("gaulLabel");
		reportEntityList.setEmptyText("Please Select...");
		reportEntityList.setWidth(LIST_WIDTH);
		reportEntityList.addSelectionChangedListener(ADAMController.entityListener(reportEntityList, reportAdamCodesStore, reportAdamCodesList));
		reportEntityList.setValue(reportEntityStore.getAt(0));
		
		reportAskADAMFilters.setSpacing(5);
		reportAskADAMFilters.setHorizontalAlign(HorizontalAlignment.RIGHT);
		reportAskADAMFilters.setVerticalAlign(VerticalAlignment.MIDDLE);
		reportAskADAMFilters.add(new Html("<b><font color='white'>About: </font></b>"));
		reportAskADAMFilters.add(reportEntityList);
		reportAskADAMFilters.add(reportAdamCodesList);
		
		return reportAskADAMFilters;
	}
	
	public HorizontalPanel buildDonorProfileFiltersReport() {
			
		reportDonorProfileFilters.removeAll();
		reportDonorProfileFilters = new HorizontalPanel();
		
		reportAdamCodesStore = new ListStore<GaulModelData>();
		reportAdamCodesList = new ComboBox<GaulModelData>();
		reportEntityStore = new ListStore<GaulModelData>();
		reportEntityList = new ComboBox<GaulModelData>();
		
		reportAdamCodesList.setTriggerAction(TriggerAction.ALL);
		reportAdamCodesList.setStore(reportAdamCodesStore);
		reportAdamCodesList.setDisplayField("gaulLabel");
		reportAdamCodesList.setEmptyText("Please Select...");
		reportAdamCodesList.setWidth(LIST_WIDTH);
	
		
		reportEntityStore.add(new GaulModelData("Resource Partner", "Donor_"));
		reportEntityList.setTriggerAction(TriggerAction.ALL);
		reportEntityList.setStore(reportEntityStore);
		reportEntityList.setDisplayField("gaulLabel");
		reportEntityList.setEmptyText("Please Select...");
		reportEntityList.setWidth(LIST_WIDTH);
		reportEntityList.addSelectionChangedListener(ADAMController.entityListener(reportEntityList, reportAdamCodesStore, reportAdamCodesList));
		reportEntityList.setValue(reportEntityStore.getAt(0));
		
		reportDonorProfileFilters.setSpacing(5);
		reportDonorProfileFilters.setHorizontalAlign(HorizontalAlignment.RIGHT);
		reportDonorProfileFilters.setVerticalAlign(VerticalAlignment.MIDDLE);
		reportDonorProfileFilters.add(new Html("<b><font color='white'>For: </font></b>"));
		reportDonorProfileFilters.add(reportEntityList);
		reportDonorProfileFilters.add(reportAdamCodesList);
		
		return reportDonorProfileFilters;
	}
	
	
	

	
    /* public HorizontalPanel buildDonorMatrixFilters() {
		
		adamCodesStore = new ListStore<GaulModelData>();
		adamCodesList = new ComboBox<GaulModelData>();
		entityStore = new ListStore<GaulModelData>();
		entityList = new ComboBox<GaulModelData>();
		
		adamCodesList.setTriggerAction(TriggerAction.ALL);
		adamCodesList.setStore(adamCodesStore);
		adamCodesList.setDisplayField("gaulLabel");
		adamCodesList.setEmptyText("Please Select...");
		adamCodesList.setWidth(LIST_WIDTH);
		
		entityStore.add(new GaulModelData("Recipient Country", "Gaul_"));
		entityStore.add(new GaulModelData("Donor", "Donor_"));
		entityList.setTriggerAction(TriggerAction.ALL);
		entityList.setStore(entityStore);
		entityList.setDisplayField("gaulLabel");
		entityList.setEmptyText("Please Select...");
		entityList.setWidth(LIST_WIDTH);
		entityList.addSelectionChangedListener(ADAMController.entityListener(entityList, adamCodesStore));
		
		donorMatrixButton = new Button("Open", DonorMatrixController.buildMatrix(adamCodesList));
		
		donorMatrixFilters.setSpacing(5);
		donorMatrixFilters.setHorizontalAlign(HorizontalAlignment.RIGHT);
		donorMatrixFilters.setVerticalAlign(VerticalAlignment.MIDDLE);
		donorMatrixFilters.add(new Html("<b><font color='white'>Donor Matrix for: </font></b>"));
		donorMatrixFilters.add(entityList);
		donorMatrixFilters.add(adamCodesList);
		donorMatrixFilters.add(donorMatrixButton);
		
		
		return donorMatrixFilters;
	}*/


   // public HorizontalPanel buildDonorMatrixFilters() {
	//   return createFilters("Open", DonorMatrixController.buildMatrix(adamCodesList));
	//}

	
     
     /*** TODO: retrieve the dates automatically **/
     public HorizontalPanel buildReferencePeriod() {
 		
    	fromDateStore = new ListStore<GaulModelData>();
    	fromDateList = new ComboBox<GaulModelData>();
    	toDateStore = new ListStore<GaulModelData>();
    	toDateList = new ComboBox<GaulModelData>();
 		
    	fromDateList.setTriggerAction(TriggerAction.ALL);
    	fromDateList.setStore(fromDateStore);
    	fromDateList.setDisplayField("gaulLabel");
    	fromDateList.setWidth(LIST_WIDTH);
 		
    	GaulModelData fromBaseDate = new GaulModelData("2008", "01-01-2008");
      	fromDateStore.add(fromBaseDate);
     	fromDateStore.add(new GaulModelData("2007", "01-01-2007"));
     	fromDateList.setValue(fromBaseDate);
    	
     	
    	toDateList.setTriggerAction(TriggerAction.ALL);
    	toDateList.setStore(toDateStore);
    	toDateList.setWidth(LIST_WIDTH);
    	
    	GaulModelData toBaseDate = new GaulModelData("2008", "01-01-2008");
    	toDateStore.add(toBaseDate);
    	toDateStore.add(new GaulModelData("2007", "01-01-2007"));
    	toDateList.setDisplayField("gaulLabel");
    	toDateList.setValue(toBaseDate);
    	
 		
 		referencePeriodButton = new Button("Refresh Data", ADAMController.changeReferencePeriod(fromDateList, toDateList));
 		
 		referencePeriodFilter.setSpacing(5);
 		referencePeriodFilter.setHorizontalAlign(HorizontalAlignment.RIGHT);
 		referencePeriodFilter.setVerticalAlign(VerticalAlignment.MIDDLE);
 		referencePeriodFilter.add(new Html("<b><font color='white'>From: </font></b>"));
 		referencePeriodFilter.add(fromDateList);
 		referencePeriodFilter.add(new Html("<b><font color='white'>To: </font></b>"));
 		referencePeriodFilter.add(toDateList);
 		referencePeriodFilter.add(referencePeriodButton);
 		
 		return referencePeriodFilter;
}
  
     
     public VerticalPanel buildReportPanel() {
    	reportPanel = new VerticalPanel();

    	HorizontalPanel panel = new HorizontalPanel();	
    	reportStore = new ListStore<GaulModelData>();
    	reportList = new ComboBox<GaulModelData>();
    	reportPanelExtraInfo = new VerticalPanel();
 		reportPanel.setSpacing(5);
 		reportPanel.setHorizontalAlign(HorizontalAlignment.RIGHT);
 		reportPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
 		
 		buildAskADAMFiltersReport();
		buildDonorProfileFiltersReport();
			
    	reportList.setStore(reportStore);
    	
    	
//    	GaulModelData reports = new GaulModelData("Current View", "Current View");
//    	reportStore.add(reports);
    	reportList.addSelectionChangedListener(reportChangeListener(reportList, reportPanelExtraInfo));
    	reportStore.add(new GaulModelData("Current View", "CURRENTVIEW"));
    	reportStore.add(new GaulModelData("Ask Adam", "ASKADAM"));
    	reportStore.add(new GaulModelData("Global View", "Global View"));
    	reportStore.add(new GaulModelData("Donor Profile", "DONORPROFILE"));
    	
    	reportList.setDisplayField("gaulLabel");
    	reportList.setTriggerAction(TriggerAction.ALL);
    	reportList.setValue(reportStore.getAt(0));

 		   	
    	reportButton = new Button("Create Report");
    	reportButton.addSelectionListener(ADAMController.createReport(reportList, reportEntityList, reportAdamCodesList));
 	
    	panel.setSpacing(5);
 		panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
 		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
 		panel.add(new Html("<b><font color='white'>Create Report: </font></b>"));
 		panel.add(reportList);
 		panel.add(reportButton);
 		
 		reportPanel.add(panel);
 		reportPanel.add(reportPanelExtraInfo);
 		
 		
 		
		
 		return reportPanel;
 	} 


     public SelectionChangedListener<GaulModelData> reportChangeListener(final ComboBox<GaulModelData> list, final VerticalPanel reportPanelExtraInfo) {
 		return new SelectionChangedListener<GaulModelData>() {
 			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
 				System.out.println("create panel");
 				reportPanelExtraInfo.removeAll();
 				String code = list.getSelection().get(0).getGaulCode();
 				if( code.equalsIgnoreCase("ASKADAM" )){
 					System.out.println("ask adam");
 					//reportPanelExtraInfo.add(buildAskADAMFiltersReport());
 					reportPanelExtraInfo.add(reportAskADAMFilters);
// 					
 				} else if(code.equalsIgnoreCase("DONORPROFILE" )){
 	 					System.out.println("donor profile");
 	 				//	reportPanelExtraInfo.add(buildDonorProfileFiltersReport());
 	 					reportPanelExtraInfo.add(reportDonorProfileFilters);
 	 			}
 				
 				reportPanelExtraInfo.layout();
 			}
		
 		};
}

	public HorizontalPanel getMainMenuBar() {
		return mainMenuBar;
	}

	public HorizontalPanel getSecondaryMenuBar() {
		return secondaryMenuBar;
	}
	
}