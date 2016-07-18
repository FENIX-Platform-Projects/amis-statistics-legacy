package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class ExcelImporterWindow extends FenixWindow {

	private ExcelImporterPanel excelImporterPanel;
	
	public ExcelImporterWindow() {
		excelImporterPanel = new ExcelImporterPanel();
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(excelImporterPanel.build());		
		getCenter().setSize(300, 150);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		getCenterData().setSize(300);
		addCenterPartToWindow();
	}
	
	private void format() {
		setSize("600px", "350px");
		getWindow().setHeading(BabelFish.print().datasetUploader());
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("uploadData");
		getWindow().setCollapsible(false);
	}

	public ExcelImporterPanel getExcelImporterPanel() {
		return excelImporterPanel;
	}
	
}