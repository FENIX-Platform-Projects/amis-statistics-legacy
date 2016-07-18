package org.fao.fenix.web.modules.adam.client.view;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMVisibilityController;
import org.fao.fenix.web.modules.adam.client.resources.ADAMResources;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;



public class ADAMHome {

	private static final String html = ADAMResources.INSTANCE.getHomeHtml().getText();

	private String home;
	
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		home = home;
	}

	public static void build() {  		
		Integer width = Window.getClientWidth();
		Integer height = Window.getClientHeight();

		buildAndReplaceElement("adam-label", ADAMController.openADAMListener("TAB2", ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.BROWSE.name()), "indent");
		//buildAndReplaceElement("adam-go", ADAMController.openADAMListener("TAB2", ADAMCurrentVIEW.ADAMVIEW, ADAMCurrentVIEW.BROWSE.name()), "gototext");

		buildAndReplaceElement("analyse-label", ADAMController.openADAMListener("TAB3", ADAMCurrentVIEW.ANALYSE, ADAMCurrentVIEW.ANALYSE.name()), "indent");
		//buildAndReplaceElement("analyse-go", ADAMController.openADAMListener("TAB3", ADAMCurrentVIEW.ANALYSE, ADAMCurrentVIEW.ANALYSE.name()), "gototext");

		buildAndReplaceElement("profile-label", ADAMController.openADAMListener("TAB4", ADAMCurrentVIEW.PROFILES, ADAMCurrentVIEW.PROFILES.name()), "indent");
		//buildAndReplaceElement("profile-go", ADAMController.openADAMListener("TAB4", ADAMCurrentVIEW.PROFILES, ADAMCurrentVIEW.PROFILES.name()), "gototext"); 
		 
		 
	/***	 if(Document.get().getElementById("adam-label")!=null){
			 System.out.println(" ------------> adam-label NOT NULL");
			 
			 try{
			 Anchor anchor = Anchor.wrap(Document.get().getElementById("adam-label"));
			 RootPanel.detachNow(anchor); 
			 
			 if(anchor==null)
					System.out.println(" ------------> ANCHOR NOT NULL");
			 
			  anchor.addClickListener(new ClickListener() {
			      public void onClick(Widget sender) {
			        Window.alert("w00t!");
			      }
			    });
			 }
			 catch (AssertionError e) {
				 System.out.println(" ------------> "+e.getLocalizedMessage()); 
			 }
			 
			

			

		 }****/
		
		
		// Element p = RootPanel.get("adam-label").getElement();
		
		// Element p = htmlPanel.getElementById("adam-label");
		// ClickHtml adam = new ClickHtml();
		// adam.getElement().setId("adam-label");
		// adam.setHtml(p.getInnerHTML());
		// adam.setStyleName("indent");
		// adam.addListener(Events.OnClick, ADAMController.openADAM("TAB2", ADAMCurrentVIEW.ADAMVIEW)); 
		 
		// rootPanel.insert(adam, 0);
		 
//				if(RootPanel.get("HOME").getElement().getInnerHTML()!=null){
//					setHome(RootPanel.get("HOME").getElement().getInnerHTML());
//				} 	
//			}
		 
		 
		// htmlPanel.addAndReplaceElement(adam, p);	
			 
		/** p = htmlPanel.getElementById("adam-go");
		 adam = new ClickHtml();
		 adam.getElement().setId("wp-adam");
		 adam.setHtml(p.getInnerHTML());
		 adam.setStyleName("gototext");
		 adam.addListener(Events.OnClick, ADAMController.openADAM("TAB2", ADAMCurrentVIEW.ADAMVIEW)); 
		 
		 htmlPanel.addAndReplaceElement(adam, p);	
	
		 p = htmlPanel.getElementById("matrix-label");
		 ClickHtml matrix = new ClickHtml();
		 matrix.getElement().setId("wp-matrix-label");
		 matrix.setHtml(p.getInnerHTML());
		 matrix.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.DONORMATRIX));
		 matrix.setStyleName("indent");
		 
		 htmlPanel.addAndReplaceElement(matrix, p);	
		 
		 p = htmlPanel.getElementById("matrix-go");
		 matrix = new ClickHtml();
		 matrix.getElement().setId("wp-matrix");
		 matrix.setHtml(p.getInnerHTML());
		 matrix.addListener(Events.OnClick, ADAMController.openADAM("TAB3", ADAMCurrentVIEW.DONORMATRIX));
		 matrix.setStyleName("gototext");
		 
		 htmlPanel.addAndReplaceElement(matrix, p);	
		 
		 p = htmlPanel.getElementById("profile-label");
		 ClickHtml  profile = new ClickHtml();
		 profile.getElement().setId("wp-profile-label");
		 profile.setHtml(p.getInnerHTML());
		 profile.addListener(Events.OnClick, ADAMController.openADAM("TAB4", ADAMCurrentVIEW.PROFILES));
		 profile.setStyleName("indent");
		 
		 htmlPanel.addAndReplaceElement(profile, p);
		 
		 p = htmlPanel.getElementById("profile-go");
		 profile = new ClickHtml();
		 profile.getElement().setId("wp-profile");
		 profile.setHtml(p.getInnerHTML());
		 profile.addListener(Events.OnClick, ADAMController.openADAM("TAB4", ADAMCurrentVIEW.PROFILES));
		 profile.setStyleName("gototext");
		 
		 htmlPanel.addAndReplaceElement(profile, p);	**/
	
		 ADAMVisibilityController.restoreHomeVisibility();
		
		  
		 
//		if (RootPanel.get("HOME").getWidgetCount() > 0)
//				RootPanel.get("HOME").remove(RootPanel.get("HOME").getWidget(0));
		 
//		RootPanel.get("HOME").add(htmlPanel);


	}  
	
	
	private static void buildAndReplaceElement(String id, Listener<ComponentEvent> listener, String style){
		 Element p = RootPanel.get(id).getElement();
		 ClickHtml clickHtml = new ClickHtml();
		 clickHtml.setId(id);
		 clickHtml.setHtml(p.getInnerHTML());
		 clickHtml.setStyleName(style);
		 clickHtml.addListener(Events.OnClick, listener); 
		 p.getParentElement().replaceChild(clickHtml.getElement(), p);
		 clickHtml.attach();
	}
	
	public static void buildAndReplaceLanguageElement(String historyToken){
		 Element p = RootPanel.get("languages-menu").getElement();
		 ClickHtml clickHtml = new ClickHtml();
		 clickHtml.setId("languages-menu");
		
		 StringBuilder en = new StringBuilder();
		 en.append("<td  width='70px' align='right' class='language-links_selected'><b>english</b></td>");
		 en.append("<td  width='10px'></td>");
		 en.append("<td  width='70px' align='right' class='language-links'><a href='index_fr.html?locale=fr#"+historyToken+"'>fran&#231;ais</a></td>");
		 en.append("<td  width='10px'></td>");
		 en.append("<td  width='70px' align='right' class='language-links'><a href='index_es.html?locale=es#"+historyToken+"'>espa&#241;ol</a></td>");
		 en.append("<td  width='10px' align='right'></td>");
		 
		 StringBuilder fr = new StringBuilder();
		 fr.append("<td  width='70px' align='right' class='language-links'><b><a href='index.html?#"+historyToken+"'>english</a></b></td>");
		 fr.append("<td  width='10px'></td>");
		 fr.append("<td  width='70px' align='right' class='language-links_selected'><b>fran&#231;ais</b></td>");
		 fr.append("<td  width='10px'></td>");
		 fr.append("<td  width='70px' align='right' class='language-links'><a href='index_es.html?locale=es#"+historyToken+"'>espa&#241;ol</a></td>");
		 fr.append("<td  width='10px' align='right'></td>");
		 
		 StringBuilder es = new StringBuilder();
		 es.append("<td  width='70px' align='right' class='language-links'><b><a href='index.html?#"+historyToken+"'>english</a></b></td>");
		 es.append("<td  width='10px'></td>");
		 es.append("<td  width='70px' align='right' class='language-links'><a href='index_fr.html?locale=fr#"+historyToken+"'>fran&#231;ais</a></td>");
		 es.append("<td  width='10px'></td>");
		 es.append("<td  width='70px' align='right' class='language-links_selected'><b>espa&#241;ol</b></td>");
		 es.append("<td  width='10px' align='right'></td>");
	
		 String html = "";
		 if(LocaleInfo.getCurrentLocale().getLocaleName().equals("en")){
			 html = createLanguageMenuHtml(en);
		 }	else if (LocaleInfo.getCurrentLocale().getLocaleName().equals("fr")){
			 html = createLanguageMenuHtml(fr);
		 } else if(LocaleInfo.getCurrentLocale().getLocaleName().equals("es")){
			 html = createLanguageMenuHtml(es);
		 } else
			 html = createLanguageMenuHtml(en);
			
		 clickHtml.setHtml(html);
		  
		 p.getParentElement().replaceChild(clickHtml.getElement(), p);
		 clickHtml.attach();
	}
	
	 private static String createLanguageMenuHtml(StringBuilder langLinksHtml){
		 StringBuilder table = new StringBuilder();
		 table.append("<table cellspacing='0' border='0' align='center'>");
		 table.append("<tr valign='center'>");
		 table.append("<td  width='1010px'></td>");
		 table.append(langLinksHtml.toString());
		 table.append("</tr>");
		 table.append("</table>");
		 
		 return table.toString();
		 
	 }
}