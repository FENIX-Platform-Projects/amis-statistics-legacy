package org.fao.fenix.web.modules.olap.client.view;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.TextField;



public class MTSizePanel {

	private HorizontalPanel p;
	
	private String TITLE = "<div style='text-align: right; font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";
	
	private int SPACING = 5;
	
	private String type;
	
	private TextField<String> size;
	
	private String LABEL_WIDTH = "200px";
	
	private String FIELD_WIDTH = "200px";
	
	public MTSizePanel(String type) {
		this.setType(type);
		p = new HorizontalPanel();
		p.setSpacing(SPACING);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
//		p.setBorders(true);
		size = new TextField<String>();
	}
	
	public HorizontalPanel build(String label, String defaultSize) {
		Html lbl = new Html(TITLE + "<i>" + label + "</i>'s " + this.getType().toLowerCase() + ": </div>");
		lbl.setWidth(LABEL_WIDTH);
		size.setWidth(FIELD_WIDTH);
		size.setValue(defaultSize);
		p.add(lbl);
		p.add(size);
		return p;
	}

	public HorizontalPanel getP() {
		return p;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TextField<String> getSize() {
		return size;
	}
	
}