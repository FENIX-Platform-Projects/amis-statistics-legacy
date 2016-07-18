package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class ExportToHtmlWindow extends FenixWindow {

	private TextArea textArea;
	
	public void build(String html) {
		textArea = new TextArea();
		buildCenterPanel(html);
		getWindow().setHeading("Multidimensional Table HTML");
		show();
		getCenter().getLayout().layout();
		getWindow().setHeight("100px");
		getWindow().setIconStyle("olap");
	}
	
	private void buildCenterPanel(String html) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		textArea.setValue(html);
		textArea.selectAll();
		getCenter().add(textArea);
		getCenterData().setSize(600);
		addCenterPartToWindow();
	}
	
}