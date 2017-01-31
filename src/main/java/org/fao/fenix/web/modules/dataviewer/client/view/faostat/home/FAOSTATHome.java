package org.fao.fenix.web.modules.dataviewer.client.view.faostat.home;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.menu.FAOSTATMenuController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATChartController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATMapController;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.menu.FAOSTATMainMenu;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

public class FAOSTATHome {

	ContentPanel panel;
	
	FAOSTATMainMenu mainMenu;
	
	FAOSTATRSSPanel rssPanel;
	
	FAOSTATDatabaseUpdatesPanel databaseUpdatesPanel;
	
	public FAOSTATHome(FAOSTATMainMenu menu) {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		mainMenu = menu;
		rssPanel = new FAOSTATRSSPanel();		
		databaseUpdatesPanel = new FAOSTATDatabaseUpdatesPanel();
	}
	
	
	public void build() {  		
		
		System.out.println("build history "+History.getToken() + " current lang = "+ FAOSTATConstants.faostatLanguage);
		
//		buildAndReplaceElement("goto-browse", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.VISUALIZE));
//		
//		buildAndReplaceElement("browse-domain", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.VISUALIZE_BY_DOMAIN));
//		buildAndReplaceElement("browse-area", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.VISUALIZE_BY_AREA));
//		buildAndReplaceElement("browse-topic", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.VISUALIZE_TOP_20));
//
////		buildAndReplaceElement("goto-download", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.DOWNLOAD));
//		buildAndReplaceElement("browse-download", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.DOWNLOAD));
//		
////		buildAndReplaceElement("goto-search", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.SEARCH_DATA));
//		buildAndReplaceElement("browse-search", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.SEARCH_DATA));
//		
////		buildAndReplaceElement("goto-analysis", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.ANALYSIS));
//		buildAndReplaceElement("browse-analysis", FAOSTATMenuController.setHistoryItem(FAOSTATCurrentView.ANALYSIS));
		
		styleVisibilityDisplay("HOME_CONTENT");
		restoreHomeVisibility();

		// RSS updates
		if (RootPanel.get("rss").getWidgetCount() > 0)
			RootPanel.get("rss").remove(RootPanel.get("rss").getWidget(0));
		RootPanel.get("rss").add(rssPanel);
		
		// Database Updates
		if (RootPanel.get("updates").getWidgetCount() > 0)
			RootPanel.get("updates").remove(RootPanel.get("updates").getWidget(0));
		RootPanel.get("updates").add(databaseUpdatesPanel);
	}  
	
	
	public static void buildAndReplaceLanguageElement(String id, String historyToken){
		 Element p = RootPanel.get(id).getElement();
		 ClickHtml clickHtml = new ClickHtml();
		 clickHtml.setStyleName("right");
		 clickHtml.setId(id);
		 
		 String currentLang = FAOSTATConstants.faostatLanguage;
		// clickHtml.setHtml("| <a href='dataviewer_"+id+".html?#"+historyToken+"&locale="+id+"'>"+label+"</a> | ");

		 String html = "";
		 String fr_html = "<a href='index.html?#"+historyToken+"'>english</a> | <b>fran&#231;ais</b> | <a href='index_es.html?locale=es#"+historyToken+"'>espa&#241;ol</a>";
		 String es_html = "<a href='index.html?#"+historyToken+"'>english</a> | <a href='index_fr.html?locale=fr#"+historyToken+"'>fran&#231;ais</a> | <b>espa&#241;ol</b>";
		 String en_html = "<b>english</b> | <a href='index_fr.html?locale=fr#"+historyToken+"'>fran&#231;ais</a> | <a href='index_es.html?locale=es#"+historyToken+"'>espa&#241;ol</a>";
			 
		 if(currentLang.equalsIgnoreCase("E")){
			 html = en_html;
		 }	else if (currentLang.equalsIgnoreCase("F")){
			 html = fr_html;
		 } else if(currentLang.equalsIgnoreCase("S")){
			html = es_html; 
		}
				 
		 clickHtml.setHtml(html);
		 //clickHtml.addListener(Events.OnClick, listener); 
		 p.getParentElement().replaceChild(clickHtml.getElement(), p);
		 clickHtml.attach();
	}
	
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
		 clickHtml.setHtml(p.getInnerHTML());
		 clickHtml.setStyleName(style);
		 clickHtml.addListener(Events.OnClick, listener); 
		 p.getParentElement().replaceChild(clickHtml.getElement(), p);
		 clickHtml.attach();
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public static void restoreHomeVisibility() {
		styleVisibilityDisplay("HOME_CONTENT");
	} 
	
	public static void hideHomeVisibility() {
		styleVisibilityNoDisplay("HOME_CONTENT");
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
