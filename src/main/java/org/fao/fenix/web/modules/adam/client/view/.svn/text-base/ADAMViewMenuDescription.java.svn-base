package org.fao.fenix.web.modules.adam.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMDonorMatrixController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

public class ADAMViewMenuDescription {

//	private MenuBar mainMenuBar;
	
	private HorizontalPanel panel;
	
	private Html titleHtml;
	
	private Html contentHtml;
	
	public ADAMViewMenuDescription() {
		titleHtml = new Html();
		titleHtml.setWidth(ADAMConstants.VIEW_MENU_DESCRIPTION_LABEL_WIDTH);
		contentHtml = new Html();
	}
	
		
	public HorizontalPanel build(String title, String content) {
		panel =  new HorizontalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		titleHtml.setHtml("<div class='viewmenu-small-title'> "+ title +"</div>");
		panel.add(titleHtml);
		contentHtml.setHtml("<div class='viewmenu-content'>"+ content +"</div>");
		panel.add(contentHtml);
		return panel;
	}
	
	public HorizontalPanel buildORDisclaimer() {
		panel =  new HorizontalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		contentHtml.setHtml("<div class='viewmenu-content'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ ADAMQueryVOBuilderFAO.getORsDisclaimer() +"</div>");
		panel.add(contentHtml);
		return panel;
	}
	
	private HorizontalPanel buildTitle(String title) {
		HorizontalPanel panel = new HorizontalPanel();
		Html html = new Html("<div class='viewmenu-title'>"+ title + "</div>");
		panel.add(html);
		return panel;
	}
	
	private static HorizontalPanel buildContent(List<String> content) {
		HorizontalPanel panel = new HorizontalPanel();
		StringBuffer text = new StringBuffer();
		text.append("<div class='viewmenu-content'>");
		for(int i=0; i < content.size(); i++) {
			text.append(content.get(i));
			if (i != content.size() -1)
				text.append(", ");
		}
		text.append("</div>");
		Html html = new Html(text.toString());
		panel.add(html);
		return panel;
	}


	public HorizontalPanel getPanel() {
		return panel;
	}


	public void setPanel(HorizontalPanel panel) {
		this.panel = panel;
	}


	public Html getTitleHtml() {
		return titleHtml;
	}


	public void setTitleHtml(Html titleHtml) {
		this.titleHtml = titleHtml;
	}


	public Html getContentHtml() {
		return contentHtml;
	}


	public void setContentHtml(Html contentHtml) {
		this.contentHtml = contentHtml;
	}


	
	
	
	
	
	
}