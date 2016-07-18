package org.fao.fenix.web.modules.cpf.client.view.utils.html;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.cpf.common.constants.CPFConstants;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

public class HTMLUtils {


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
		
		public static void buildAndReplaceLanguageElement(String id, String historyToken){
			 Element p = RootPanel.get(id).getElement();
			 ClickHtml clickHtml = new ClickHtml();
			 clickHtml.setStyleName("right");
			 clickHtml.setId(id);
			 
			 String currentLang = CPFConstants.defaultLanguage;
			 
			 
			 String html = "";
			 String fr_html = "<a href='index.html?#"+historyToken+"'>english</a> | <b>fran&#231;ais</b> | <a href='index_es.html?locale=es#"+historyToken+"'>espa&#241;ol</a>";
			 String es_html = "<a href='index.html?#"+historyToken+"'>english</a> | <a href='index_fr.html?locale=fr#"+historyToken+"'>fran&#231;ais</a> | <b>espa&#241;ol</b>";
			 String en_html = "<b>english</b> | <a href='index_fr.html?locale=fr#"+historyToken+"'>fran&#231;ais</a> | <a href='index_es.html?locale=es#"+historyToken+"'>espa&#241;ol</a>";
				 
			 if(currentLang.equalsIgnoreCase("en")){
				 html = en_html;
			 }	else if (currentLang.equalsIgnoreCase("fr")){
				 html = fr_html;
			 } else if(currentLang.equalsIgnoreCase("es")){
				html = es_html; 
			}
					 
			 clickHtml.setHtml(html);
			 //clickHtml.addListener(Events.OnClick, listener); 
			 p.getParentElement().replaceChild(clickHtml.getElement(), p);
			 clickHtml.attach();
		}
}
