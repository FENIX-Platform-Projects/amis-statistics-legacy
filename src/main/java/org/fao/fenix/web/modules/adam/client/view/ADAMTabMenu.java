package org.fao.fenix.web.modules.adam.client.view;

import org.fao.fenix.web.modules.adam.client.control.history.ADAMHistoryController;
import org.fao.fenix.web.modules.adam.client.lang.ADAMLanguage;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.event.Events;
import com.google.gwt.user.client.ui.RootPanel;



public class ADAMTabMenu {

	
	/*public static void build(ADAMCurrentVIEW selectedView) {  		
		  
		ClickHtml home = new ClickHtml();
		//home.setHtml(ADAMLanguage.print().home());
		home.setHtml("<img src='adam-images/home.png' title='ADAM "+ADAMLanguage.print().home()+"'>");
		home.addListener(Events.OnClick, ADAMController.openADAM("TAB1", ADAMCurrentVIEW.HOME, ADAMCurrentVIEW.HOME.name()));
		RootPanel.get("TAB1").add(home);
		if ( selectedView.equals(ADAMCurrentVIEW.HOME))
			RootPanel.get("TAB1").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB1").setStyleName("tab_menu_default");


		ClickHtml adamView = new ClickHtml();
		adamView.setHtml(ADAMLanguage.print().browseData());
    	adamView.addListener(Events.OnClick, ADAMController.openADAM("TAB2", ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.BROWSEDATA.name()));
		RootPanel.get("TAB2").add(adamView);
		if ( selectedView.equals(ADAMCurrentVIEW.ADAMVIEW))
			RootPanel.get("TAB2").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB2").setStyleName("tab_menu_default");
		
		
//		ClickHtml faoView = new ClickHtml();
//		faoView.setHtml(BabelFish.print().faoView());
//		RootPanel.get("TAB3").add(faoView);
//		faoView.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.FAOVIEW));
//		if ( selectedView.equals(ADAMCurrentVIEW.FAOVIEW))
//			RootPanel.get("TAB3").setStyleName("tab_menu_selected");
//		else
//			RootPanel.get("TAB3").setStyleName("tab_menu_default");


		ClickHtml analyseData = new ClickHtml();
		analyseData.setHtml("Analyse Data");
		RootPanel.get("TAB3").add(analyseData);
		analyseData.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.ANALYSEDATA.name()));
		if ( selectedView.equals(ADAMCurrentVIEW.ANALYSEDATA))
			RootPanel.get("TAB3").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB3").setStyleName("tab_menu_default");
		
		ClickHtml donorMatrix = new ClickHtml();
		donorMatrix.setHtml(ADAMLanguage.print().resourcePartnerMatrix());
		RootPanel.get("TAB3").add(donorMatrix);
		donorMatrix.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.DONORMATRIX));
		if ( selectedView.equals(ADAMCurrentVIEW.DONORMATRIX))
			RootPanel.get("TAB3").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB3").setStyleName("tab_menu_default");

		ClickHtml donorProfile = new ClickHtml();
		donorProfile.setHtml(ADAMLanguage.print().resourcePartnerProfiles());
		RootPanel.get("TAB4").add(donorProfile);
		donorProfile.addListener(Events.OnClick, ADAMController.openADAM("TAB4", ADAMCurrentVIEW.PROFILES, ADAMCurrentVIEW.PROFILES.name()));
		if ( selectedView.equals(ADAMCurrentVIEW.PROFILES))
			RootPanel.get("TAB4").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB4").setStyleName("tab_menu_default");
		
		
		ClickHtml links = new ClickHtml();
		links.setHtml("Useful Links");
		RootPanel.get("TAB5").add(links);
		links.addListener(Events.OnClick, ADAMController.openADAM("TAB5", ADAMCurrentVIEW.LINKS, ADAMCurrentVIEW.LINKS.name()));
		if ( selectedView.equals(ADAMCurrentVIEW.LINKS))
			RootPanel.get("TAB5").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB5").setStyleName("tab_menu_default");
	
	
	}  */
	
	
	public static void build(ADAMCurrentVIEW selectedView) {  		
		  
		ClickHtml home = new ClickHtml();
		home.setHtml("<img src='adam-images/home.png' title='ADAM "+ADAMLanguage.print().home()+"'>");
		home.addListener(Events.OnClick, ADAMHistoryController.setHistoryItemListener(ADAMCurrentVIEW.HOME, ADAMCurrentVIEW.HOME.name()));
		//home.addListener(Events.OnClick, ADAMController.openADAM("TAB1", ADAMCurrentVIEW.HOME, ADAMCurrentVIEW.HOME.name()));
		RootPanel.get("TAB1").add(home);
		if ( selectedView.equals(ADAMCurrentVIEW.HOME))
			RootPanel.get("TAB1").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB1").setStyleName("tab_menu_default");


		ClickHtml adamView = new ClickHtml();
		adamView.setHtml(ADAMLanguage.print().browseData());
		adamView.addListener(Events.OnClick, ADAMHistoryController.setHistoryItemListener(ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.BROWSE.name()));
    	//adamView.addListener(Events.OnClick, ADAMController.openADAM("TAB2", ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.BROWSEDATA.name()));
		RootPanel.get("TAB2").add(adamView);
		if ( selectedView.equals(ADAMCurrentVIEW.ADAMVIEW))
			RootPanel.get("TAB2").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB2").setStyleName("tab_menu_default");
		
		
//		ClickHtml faoView = new ClickHtml();
//		faoView.setHtml(BabelFish.print().faoView());
//		RootPanel.get("TAB3").add(faoView);
//		faoView.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.FAOVIEW));
//		if ( selectedView.equals(ADAMCurrentVIEW.FAOVIEW))
//			RootPanel.get("TAB3").setStyleName("tab_menu_selected");
//		else
//			RootPanel.get("TAB3").setStyleName("tab_menu_default");


		ClickHtml analyseData = new ClickHtml();
		analyseData.setHtml(ADAMLanguage.print().analyseData());
		RootPanel.get("TAB3").add(analyseData);
		analyseData.addListener(Events.OnClick, ADAMHistoryController.setHistoryItemListener(ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.ANALYSE.name()));
	   // analyseData.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.ANALYSEDATA.name()));
		if ( selectedView.name().contains(ADAMCurrentVIEW.ANALYSE.name()))
			RootPanel.get("TAB3").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB3").setStyleName("tab_menu_default");
		
	/*	ClickHtml donorMatrix = new ClickHtml();
		donorMatrix.setHtml(ADAMLanguage.print().resourcePartnerMatrix());
		RootPanel.get("TAB3").add(donorMatrix);
		donorMatrix.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.DONORMATRIX));
		if ( selectedView.equals(ADAMCurrentVIEW.DONORMATRIX))
			RootPanel.get("TAB3").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB3").setStyleName("tab_menu_default");*/

		ClickHtml donorProfile = new ClickHtml();
		donorProfile.setHtml(ADAMLanguage.print().resourcePartnerProfiles());
		RootPanel.get("TAB4").add(donorProfile);
		donorProfile.addListener(Events.OnClick, ADAMHistoryController.setHistoryItemListener(ADAMCurrentVIEW.PROFILES, ADAMCurrentVIEW.PROFILES.name()));
		//donorProfile.addListener(Events.OnClick, ADAMController.openADAM("TAB4", ADAMCurrentVIEW.PROFILES, ADAMCurrentVIEW.PROFILES.name()));
		if ( selectedView.equals(ADAMCurrentVIEW.PROFILES))
			RootPanel.get("TAB4").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB4").setStyleName("tab_menu_default");
		
		
		ClickHtml links = new ClickHtml();
		links.setHtml(ADAMLanguage.print().usefulLinks());
		RootPanel.get("TAB5").add(links);
		links.addListener(Events.OnClick, ADAMHistoryController.setHistoryItemListener(ADAMCurrentVIEW.LINKS, ADAMCurrentVIEW.LINKS.name()));
		//links.addListener(Events.OnClick, ADAMController.openADAM("TAB5", ADAMCurrentVIEW.LINKS, ADAMCurrentVIEW.LINKS.name()));
		if ( selectedView.equals(ADAMCurrentVIEW.LINKS))
			RootPanel.get("TAB5").setStyleName("tab_menu_selected");
		else
			RootPanel.get("TAB5").setStyleName("tab_menu_default");
	
	
	}  
	
	
	  
	  public static void setDefaultStyle(String selectedPosition) {
		  // avoid the selected position
		  if ( !selectedPosition.equals("TAB1")) {
			  RootPanel.get("TAB1").setStyleName("tab_menu_default");
		  }
		  if ( !selectedPosition.equals("TAB2")) {
			  RootPanel.get("TAB2").setStyleName("tab_menu_default");
		  }
		  if ( !selectedPosition.equals("TAB3")) {
			  RootPanel.get("TAB3").setStyleName("tab_menu_default");
		  } 
		  if ( !selectedPosition.equals("TAB4")) {
			  RootPanel.get("TAB4").setStyleName("tab_menu_default");
		  }
		  if ( !selectedPosition.equals("TAB5")) {
			  RootPanel.get("TAB5").setStyleName("tab_menu_default");
		  }
		 /* if ( !selectedPosition.equals("TAB5")) {
			  RootPanel.get("TAB5").setStyleName("tab_menu_default");
		  }*/
	  }
	
	  
	 
	  
//	  private MenuBar mainMenuBar;
	//	
//		private static MenuItem home;
	//	
//		private static MenuItem adamView;
	//	
//		private static MenuItem donorMatrixView;
	//	
//		private static MenuItem donorProfileView;
	
	
//	public ADAMTabMenu() {
//		mainMenuBar = new MenuBar();
//		mainMenuBar.addStyleName("main-MenuBar");
//	}
//	
//	public MenuBar buildTabs() {
//		
//		home = new MenuItem("ADAM Home", ADAMController.callView(ADAMCurrentVIEW.HOME));
//		home.setCommand(changeStyleHome());
//		
//		mainMenuBar.addItem(home);
//
//		adamView = new MenuItem("ADAM View", ADAMController.callView(ADAMCurrentVIEW.ADAMVIEW));
//		adamView.setCommand(changeStyleAskADAM());
//
//		
//		mainMenuBar.addItem(adamView);
//		
//		donorMatrixView = new MenuItem("Resource Partner Matrix", ADAMController.callView(ADAMCurrentVIEW.DONORMATRIX));
//		donorMatrixView.setCommand(changeStyleDonorMatrix());
//		
//		donorProfileView = new MenuItem("Resource Partner Profiles", ADAMController.callView(ADAMCurrentVIEW.PROFILES));
//		donorProfileView.setCommand(changeStyleDonorProfile());
//		
//		
//		//mainMenuBar.addItem(adamView, ADAMController.callView(ADAMCurrentVIEW.ADAMVIEW));
//		mainMenuBar.addItem(donorMatrixView);
//		mainMenuBar.addItem(donorProfileView);
//		
//		//mainMenuBar.addItem("Ask ADAM", ADAMController.askADAM(this));
//		//mainMenuBar.addItem("Ask ADAM", ADAMController.askADAM(this));
//
//		mainMenuBar.selectItem(adamView);
//		
//		return mainMenuBar;
//	}
//	
//	public static Command changeStyleHome() {
//		return new Command() {
//			public void execute() {
//				ADAMController.callViewAgent(ADAMCurrentVIEW.HOME);
//			}
//		};
//	};
//	
//	public static Command changeStyleAskADAM() {
//		return new Command() {
//			public void execute() {
////				ADAMTabMenu.getAdamView().setStyleName("main-MenuBar gwt-MenuItem-selected2");
////				ADAMTabMenu.getDonorMatrixView().setStyleName("demo-MenuBar");
//				
//				ADAMController.callViewAgent(ADAMCurrentVIEW.ADAMVIEW);
//			}
//		};
//	};
//	
//	public static Command changeStyleDonorMatrix() {
//		return new Command() {
//			public void execute() {
////				ADAMTabMenu.getAdamView().setStyleName("demo-MenuBar");
////				ADAMTabMenu.getDonorMatrixView().setStyleName("selected-tab");
//				
//				ADAMController.callViewAgent(ADAMCurrentVIEW.DONORMATRIX);
//			}
//		};
//	};
//	
//	public static Command changeStyleDonorProfile() {
//		return new Command() {
//			public void execute() {				
//				ADAMController.callViewAgent(ADAMCurrentVIEW.PROFILES);
//			}
//		};
//	};
//     
//	public MenuBar getMainMenuBar() {
//		return mainMenuBar;
//	}
//
//	public static MenuItem getAdamView() {
//		return adamView;
//	}
//
//	public static MenuItem getDonorMatrixView() {
//		return donorMatrixView;
//	}
	
	
	
}