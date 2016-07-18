package org.fao.fenix.web.modules.amis.client.view.inputImporter;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class ImporterWindow extends FenixWindow {
private ImporterPanel importerPanel;
	
	public ImporterWindow() {
		importerPanel = new ImporterPanel();
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
		getCenter().add(importerPanel.build());		
//		getCenter().setSize(300, 150);
		getCenter().setSize(300, 100);
		getCenter().setScrollMode(Scroll.AUTO);
		getCenter().setBodyBorder(false);
		//getCenterData().setSize(300);
		getCenterData().setSize(200);
		addCenterPartToWindow();
	}
	
	private void format() {
//		setSize("600px", "350px");
		setSize("600px", "204px");
		setModal(true);
		getWindow().setHeading(BabelFish.print().datasetUploader());
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("uploadData");
		getWindow().setCollapsible(false);
	}

	public ImporterPanel getExcelImporterPanel() {
		return importerPanel;
	}
}
