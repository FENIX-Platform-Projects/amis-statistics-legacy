package org.fao.fenix.web.modules.chartdesigner.client.view;

import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class ChartDesignerExportToHTMLWindow extends FenixWindow {
	
	private static final String WINDOW_WIDTH = "350px";
	
	private static final String WINDOW_HEIGHT = "135px";
	
	private static final String FIELD_WIDTH = "310px";
	
	public void build(Map<String, String> tagMap) {
		buildCenterPanel(tagMap);
		format();
		show();
	}
	
	private void buildCenterPanel(Map<String, String> tagMap) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(buildPanel(tagMap));		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private VerticalPanel buildPanel(Map<String, String> tagMap) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		for (String tag : tagMap.keySet()) {
			Html lbl = new Html("<b>" + tag + "</b>");
			lbl.setWidth(FIELD_WIDTH);
			TextField<String> content = new TextField<String>();
			content.setWidth(FIELD_WIDTH);
			content.setValue(tagMap.get(tag));
			content.selectAll();
			p.add(lbl);
			p.add(content);
		}
		return p;
	}
	
	private void format() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getWindow().setHeading("Embed");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("sendToHTML");
		getWindow().setCollapsible(false);
		getWindow().setModal(true);
	}

}