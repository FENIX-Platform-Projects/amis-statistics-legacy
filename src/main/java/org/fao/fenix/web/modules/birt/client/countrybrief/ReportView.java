package org.fao.fenix.web.modules.birt.client.countrybrief;

import java.util.List;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class ReportView {

	Window window = new Window();
	HTML content;
	ContentPanel cont;
	String rptDesign;
	String countryCode;
	String countryLabel;
	
	public String getRptDesign() {
		return rptDesign;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCountryLabel() {
		return countryLabel;
	}

	public ReportView(String countryCode, String countryLabel, List<String> report){
		
		rptDesign = report.get(0);
		this.countryCode = countryCode;
		this.countryLabel = countryLabel;
		window = new Window();
		window.setHeading("GIEWS Country Brief: " + countryLabel);
		cont = new ContentPanel();
		cont.setHeaderVisible(false);
		cont.setLayout(new FitLayout());
		window.setLayout(new FitLayout());
		window.setSize(800, 600);
		content = new HTML(report.get(1));
		
		cont.setTopComponent(new CBToolBar(ReportView.this).getToolBar());
		cont.add(content);
		window.add(cont);
		window.show();
		
	}
	
}
