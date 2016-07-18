package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import java.util.List;
import java.util.MissingResourceException;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.NoteVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;

public class FAOSTATNotesPanel {

	private ContentPanel panel;
	
	private VerticalPanel wrapper;

	public FAOSTATNotesPanel(List<NoteVO> notes) {
		
		wrapper = new VerticalPanel();
		wrapper.setLayout(new FitLayout());
		wrapper.setSpacing(10);
		
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);
		
		/*
		List<NoteVO> tmp = new ArrayList<NoteVO>();
		NoteVO vo = new NoteVO();
		vo.setTitle("production");
		vo.setContent("Q");
		tmp.add(vo);
		vo = new NoteVO();
		vo.setTitle("pin");
		vo.setContent("QI");
		tmp.add(vo);
		vo = new NoteVO();
		vo.setTitle("valueOfAgriculturalProduction");
		vo.setContent("QV");
		tmp.add(vo);
		panel.add(buildNotesHeader(tmp));
		*/
		
		panel.add(buildNotesHeader(notes));
		panel.add(wrapper);
		
	}
	
	/*
	public FAOSTATNotesPanel(List<NoteVO> notes) {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);
		for(NoteVO note: notes) {
			if(note.getIsGroupNote()!=null && note.getIsGroupNote())
				buildGroup(note);
			else 
				build(note);
		}
	}
	*/

	
	public void build(NoteVO note) {
		
		System.out.println("FAOSTATNotesPanel @ build");
		
		VerticalPanel p = new VerticalPanel();
		p.setStyleName("textFormat");
	
		if(note.getTitle()!=null){
//			p.add(createTitle(note.getTitle()));
		}
		
//		p.add(DataViewerClientUtils.addVSpace(5));
		
		if(note.getContent()!=null){
			p.add(createContent(note.getContent()));
		} else {
			System.out.println("Content IS NULL");
		}
		
	    p.add(DataViewerClientUtils.addVSpace(10));


		panel.add(p);		
	}

	public void buildGroup(NoteVO note) {
		
		System.out.println("FAOSTATNotesPanel @ buildGroup");

		VerticalPanel p = new VerticalPanel();
		p.setStyleName("textFormat");

		if(note.getTitle()!=null){
//			p.add(createTitle(note.getTitle()));
		}

		if(!note.getGroupNotes().isEmpty()) {
			for(NoteVO vo: note.getGroupNotes()){
				if(vo.getTitle()!=null){
//					p.add(createTitle(note.getTitle()));
				}	

				p.add(DataViewerClientUtils.addVSpace(5));

				if(vo.getContent()!=null){
					p.add(createContent(vo.getContent()));
				}

				p.add(DataViewerClientUtils.addVSpace(10));
			}
		}
		panel.add(p);

	}

	public ContentPanel getPanel() {
		return panel;
	}


	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}
	
	private Html createContent(String content) {
		Html html = new Html();
		html.setStyleAttribute("text-align", "justify");
		html.setHtml(content);
		
		return html;
	}
	
	private Html createTitle(String title) {
		Html html = new Html();
		html.setStyleName("titleFormat");
		html.setHtml(title);
		return html;
	}
	
	private VerticalPanel buildNotesHeader(List<NoteVO> notes) {
//		HorizontalPanel p = new HorizontalPanel();
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		int i = 0;
		for (NoteVO vo : notes) {
			String lbl = null;
//			if ( i != 0 )
//				p.add(DataViewerClientUtils.addHSpace(5));
			try {
				lbl = FAOSTATLanguage.print().getString(vo.getTitle());
			} catch (MissingResourceException e) {
				lbl = FAOSTATLanguage.print().defaultLabel();
			}
//			p.add(new Html("<img src=\"dataviewer-images/arrow_002.png\">"));
			
			if (i > 0) {											// first one is the domain, different style
				p.add(createButton(vo.getContent(), lbl));
			} else {												// first one is the domain, different style
				HTML html = new HTML("<div class='download_title' style='cursor: pointer;' >" + lbl + "</div>");
				html.addClickHandler(FAOSTATDownloadController.showNotes(vo.getContent(), lbl, wrapper));
				p.add(html);
			}
			
			i++;
		}
		return p;
	}
	
	private HTML createButton(final String code, final String label) {
		HTML b = new HTML("<div style='text-align: left;' class='notes button green' id='" + 
						  code + 
						  "'><img width='8px' height='8px' src=\"dataviewer-images/show_icon.png\">&nbsp;" + 
						  label + 
						  "</div>");
		b.addClickHandler(FAOSTATDownloadController.showNotes(code, label, wrapper));
		return b;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

}

