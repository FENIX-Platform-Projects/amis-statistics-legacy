package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bytopic;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.menu.FAOSTATMenuController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByTopicController;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeQuestionsVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.HideMode;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.dom.client.Style.VerticalAlign;

public class FAOSTATVisualizeTopicsPanel {
	
	ContentPanel panel;

	ContentPanel topic;
	
	List<DWFAOSTATQueryVO> qvos;
	
	ContentPanel content;
	
	FAOSTATVisualizeQuestionsVO question;

	
	Boolean isOpen = false;
	
	final ClickHtml titleHtml = new ClickHtml(); 
	
	IconButton icon;

	public FAOSTATVisualizeTopicsPanel() {
		panel = new ContentPanel();	
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);

//		panel.setWidth("430px");
//		panel.setHeight("30px");
//		panel.setBorders(true);
		
		topic = new ContentPanel();		
		topic.setHeaderVisible(false);
		topic.setBodyBorder(false);
		topic.setAutoHeight(true);
		topic.setScrollMode(Scroll.NONE);
		
		content = new ContentPanel();		
		content.setHeaderVisible(false);
		content.setBodyBorder(false);
		content.setAutoHeight(true);
		content.setScrollMode(Scroll.NONE);

		
		qvos = new ArrayList<DWFAOSTATQueryVO>();
		
	}
	
	public ContentPanel build(String index, FAOSTATVisualizeQuestionsVO question) {
		
		this.question = question;
		
		// TODO: Change it
		TableLayout l = new TableLayout();
		l.setCellHorizontalAlign(HorizontalAlignment.LEFT);
		l.setWidth("1080px");
		panel.setLayout(l); 
	
		panel.add(buildTopic(index));
		
		HorizontalPanel h = new HorizontalPanel();
		h.add(DataViewerClientUtils.addHSpace(40));
		h.add(content);
		panel.add(h);


		// this is to hide the content panel
		content.hide();

		
		addListeners();
		
		return panel;
	}
	
	private ContentPanel buildTopic(String index) {

		VerticalPanel p = new VerticalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		final HorizontalPanel h = new HorizontalPanel();
		
		h.setVerticalAlign(VerticalAlignment.MIDDLE);
		h.setSize("430px", "30px");
//		h.setBorders(true);
		
		h.add(DataViewerClientUtils.addHSpace(15));
		
//		h.add(new Html("<div class='visualize_topic_index'>" + index + "<div>"));
		
		h.add(DataViewerClientUtils.addHSpace(5));
		
		h.add(addTitle());
		
		h.add(DataViewerClientUtils.addHSpace(5));
		
		IconButton icon = new IconButton();
		icon.addStyleName("question_mark");
		icon.setWidth(24);
		icon.setHeight(21);
		
		h.add(icon);
		
		p.add(h);
//		if ( question.getSubtitle() != null ) {
//			p.add(addSubtitle());
//		}

		topic.add(p);

		return topic;
	}

	
	private void addListeners() {
		
	}
	
	
	private HorizontalPanel addSubtitle() {
		HorizontalPanel panel = new HorizontalPanel();
		
		Html html = new Html("<div class='visualize_topic_subtitle'> " + question.getSubtitle() + "</div>");
		panel.add(html);
		return panel;
	}
	private ClickHtml addTitle() {
//		titleHtml = new ClickHtml(); 
		titleHtml.setHtml("<div class='visualize_topic_title'> " + question.getTitle().toUpperCase() + "</div>");
		titleHtml.addListener(Events.OnClick, FAOSTATVisualizeByTopicController.buildViews(this));
		/** todo set width **/
		return titleHtml;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public ContentPanel getTopic() {
		return topic;
	}

	public List<DWFAOSTATQueryVO> getQvos() {
		return qvos;
	}

	public ContentPanel getContent() {
		return content;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public ClickHtml getTitleHtml() {
		return titleHtml;
	}

	public IconButton getIcon() {
		return icon;
	}

	public void setQvos(List<DWFAOSTATQueryVO> qvos) {
		this.qvos = qvos;
	}

	public FAOSTATVisualizeQuestionsVO getQuestion() {
		return question;
	}
	
	
	

}
