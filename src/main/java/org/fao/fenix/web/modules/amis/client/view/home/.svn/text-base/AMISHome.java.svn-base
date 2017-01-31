package org.fao.fenix.web.modules.amis.client.view.home;


import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.view.map.AMISMap;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentDatasetView;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class AMISHome {

	static ContentPanel panel;
	
	static ContentPanel dataPanel;
	

	//public static AMISMainMenu mainMenu;
	
	public static AMISMainMenu mainMenu;
	
	ListStore<AMISCodesModelData> store;    
   // ComboBox<AMISCodesModelData> combo;  
    
    static AMISDatasourceSelectorPanel datasourceSelectorPanel;

	public AMISHome(AMISMainMenu menu) {
		System.out.println(" AMISHome Constructor AMISController.currentDatasetView = *"+ AMISController.currentDatasetView+ "*");
		panel = new ContentPanel();
		panel.setHeaderVisible(true);
		panel.setBodyBorder(false);
		panel.setBorders(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("backgroundColor", "#d7d6d4");
		
		
		dataPanel = new ContentPanel();
		dataPanel.setHeaderVisible(false);
		dataPanel.setBodyBorder(false);
		dataPanel.setBorders(false);
		dataPanel.setStyleAttribute("backgroundColor", "#d7d6d4");
		//panel.setHeight(700);
	
		dataPanel.removeAll();
		
		//combo = new ComboBox<AMISCodesModelData>();
		//store = new ListStore<AMISCodesModelData>();
		//combo.setDisplayField("label");
		//combo.setWidth(300);
		//combo.setTriggerAction(TriggerAction.ALL);

		//combo.setStore(store);
		//combo.setId("areas");
		
		
		
		mainMenu = menu;
	}

	public void build() {
        
		//buildAndReplaceElement("goto-browse", AMISMenuController.openView(AMISCurrentView.HOME, mainMenu));

		restoreHomeVisibility();	
	
		//populateAreaSelector();
		
		dataPanel.removeAll();   
		RootPanel.get("OLAP_IFRAME").setVisible(false);
		RootPanel.get("COMPARE_NOTES").setVisible(false);
		RootPanel.get("OLAP_IFRAME_NO_ELEMENTS").setVisible(false);
		//RootPanel.get("WHITE_SPACE").setVisible(false);
		//Default model should be World
		AMISCodesModelData model = new AMISCodesModelData();
		model.setCode("999000");
		model.setLabel("WORLD");
		
		//Default Product
		AMISCodesModelData product = new AMISCodesModelData();
		product.setCode("8");
		product.setLabel("TOTAL CEREALS");
		
		datasourceSelectorPanel = new AMISDatasourceSelectorPanel(model, product);
	//	datasourceSelectorPanel = new AMISDatasourceSelectorPanel(model, product);
		dataPanel.add(datasourceSelectorPanel.build());

		//panel.add(combo);
		
		//dataPanel.add(new Html("Prova!!!!!!!!!!"));
//		dataPanel.add(AMISHtmlUtils.getHtmlFootnoteTable());
		//dataPanel.add(AMISHomeUtils.getHtmlFootnoteTable("cereal_table"));
		panel.add(dataPanel);
		
		panel.layout();
		//RootPanel.get("HOME_CONTENT").add(panel);	
		
		
		if (RootPanel.get("DATASET_CONTENT").getWidgetCount() > 0)
			RootPanel.get("DATASET_CONTENT").remove(RootPanel.get("DATASET_CONTENT").getWidget(0));
		
		RootPanel.get("DATASET_CONTENT").add(panel);
//		RootPanel.get("DATASET_CONTENT").add(new HTML("ciao ciao"));
		
		buildMap();
		
		panel.layout();
			
	}
		
	private void populateAreaSelector() {	
		AMISQueryVO qvo =  new AMISQueryVO();
		qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
		qvo.setTypeOfOutput(AMISConstants.AMIS_COUNTRIES.toString());	
		if(AMISController.getCurrentDatasetView()!=null)
			qvo.setSelectedDataset(AMISController.getCurrentDatasetView().toString());
		else {
			AMISController.setCurrentDatasetView(AMISCurrentDatasetView.PSD);
			qvo.setSelectedDataset(AMISController.getCurrentDatasetView().toString());
		}
	   // AMISSelectorController.getSelectors(this, combo, store, qvo, null);
	    
	   ContentPanel contentPanel = new ContentPanel();
	   contentPanel.setHeaderVisible(false);
	   contentPanel.setBodyBorder(false);
	   contentPanel.setBorders(false);
	    
	    //Html message = new Html("");
	    
		//combo.addSelectionChangedListener(AMISFilterController.updateFilter(visualization, this, combo, "AMIS_COUNTRIES", code, selectedCodes));

	  // combo.addSelectionChangedListener(getDatasetView(contentPanel)); 
		
	}

	public static void getDatasetViewAgent(String code, String label) {
		
		dataPanel.removeAll();
		
		//Default Country
		AMISCodesModelData model = new AMISCodesModelData();
		model.setCode(code);
		model.setLabel(label);
		
		//Default Product
		AMISCodesModelData product = new AMISCodesModelData();
		product.setCode("8");
		product.setLabel("TOTAL CEREALS");
		
		datasourceSelectorPanel = new AMISDatasourceSelectorPanel(model, product);
		
		dataPanel.add(datasourceSelectorPanel.build());
		
		dataPanel.layout();
		
	}
	

//
//	public  SelectionChangedListener<AMISCodesModelData> getDatasetView(final ContentPanel contentPanel) {
//		return new SelectionChangedListener<AMISCodesModelData>() {
//			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
//			    //message.setHtml("<font size='2pt'>Dataset: "+ AMISController.getCurrentDatasetView().toString() + " -  selected value: " + combo.getSelection().get(0).getLabel()+"</font>");
//			    //contentPanel.add(FormattingUtils.addVSpace(5));
//				//contentPanel.add(message);
//				
//				dataPanel.removeAll();
//				
//				AMISCodesModelData model = combo.getSelection().get(0);
//				
//				datasourceSelectorPanel = new AMISDatasourceSelectorPanel(model);
//				
//				dataPanel.add(datasourceSelectorPanel.build());
//			
//				
//				
//				dataPanel.layout();
//				
//			}
//		};
//	}
	
	
	public static void buildAndReplaceElement(String id, Listener<ComponentEvent> listener){
		 Element p = RootPanel.get(id).getElement();
		 ClickHtml clickHtml = new ClickHtml();
		 clickHtml.setId(id);
		 clickHtml.setHtml(p.getInnerHTML());
		 clickHtml.addListener(Events.OnClick, listener);
		 p.getParentElement().replaceChild(clickHtml.getElement(), p);
		 clickHtml.attach();
	}

	public static void buildAndReplaceElement(String id, Listener<ComponentEvent> listener, String style){
		 Element p = RootPanel.get(id).getElement();
		 ClickHtml clickHtml = new ClickHtml();
		 clickHtml.setId(id);
		 //clickHtml.setHtml(p.getInnerHTML());
		 clickHtml.setHtml("Log In");
		 clickHtml.setStyleName(style);
		 clickHtml.addListener(Events.OnClick, listener);
		 p.getParentElement().replaceChild(clickHtml.getElement(), p);
		 clickHtml.attach();
	}

	public static void restoreHomeVisibility() {
		styleVisibilityDisplay("HOME_CONTENT");
	}

	public static void hideHomeVisibility() {
		styleVisibilityNoDisplay("HOME_CONTENT");
	}
	
	public ContentPanel getPanel() {
		return panel;
	}
	
	  public void buildMap() {
			AMISMap amisMap = new AMISMap();
//			amisMap.buildWorldMap();
			amisMap.buildWorldMap3();
		}
		


	 public static native String styleVisibilityDisplay(String id)/*-{
     var elem;
     elem = $doc.getElementById(id);
     if (elem != null) {
	//  alert(id + " | " + elem.style.display);

//	    elem.style.visibility = "visible";
	    elem.style.display = "";
	    return "in-line";
	 }
     else
     	return null;


}-*/;

	 public static native String styleVisibilityNoDisplay(String id)/*-{
		var elem;
		elem = $doc.getElementById(id);
		if (elem != null) {
//		  	alert(id + " | " + elem.style.display);

//		    elem.style.visibility = "hidden";
		    elem.style.display = "none";
		    return "none";

		}
		else
		   return null;


	}-*/;

}
