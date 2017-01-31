package org.fao.fenix.web.modules.fsatmis.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

public class FSATMISTool extends FenixToolBase {

	private FSATMISTabPanel fsatmisTabPanel;
	
	public FSATMISTool() {
		fsatmisTabPanel = new FSATMISTabPanel();
	}
	
	public void build() {
		buildCenterPanel();
		format();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().add(fsatmisTabPanel.build());
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
	}
	
	private void format() {
		getToolBase().setWidth("800px");
		getToolBase().setHeight("725px");
		getToolBase().setHeaderVisible(false);
	}
	
}