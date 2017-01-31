package org.fao.fenix.web.modules.ipc.client.view.old;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.google.gwt.user.client.ui.HTML;

public class CreateIPCReportViewer extends FenixWindow {

	private HTML report;
	private String areaName;

	public CreateIPCReportViewer(String reportIFrame, String area) {
		this.setAreaName(area);
		this.setReport(new HTML(reportIFrame));
		build();
	}

	public void setReport(HTML report) {
		this.report = report;
	}
	
	public HTML getReport() {
		return report;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void build() {
		setTitle(BabelFish.print().IPC() + ": "+getAreaName());
		setSize(650, 650);
		setAutomaticScrollBar();

		setCenterProperties();
		getCenter().setHeaderVisible(false);

		getCenter().add(getReport());
		addCenterPartToWindow();

		show();
	}

}
