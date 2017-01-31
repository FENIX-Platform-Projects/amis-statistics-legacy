package org.fao.fenix.web.modules.ipc.client.view.old;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.google.gwt.user.client.ui.HTML;

public class ShowIPCWorkflowViewer extends FenixWindow {

	private HTML ipc;
	private String areaName;

	public ShowIPCWorkflowViewer(String ipcIFrame, String area) {
		this.setAreaName(area);
		this.setIPC(new HTML(ipcIFrame));
		build();
	}

	public HTML getIPC() {
		return ipc;
	}

	public void setIPC(HTML ipc) {
		this.ipc = ipc;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void build() {
		setTitle(BabelFish.print().ipcWorkflows() + ": "+ getAreaName());
		setSize(650, 650);
		setAutomaticScrollBar();

		setCenterProperties();
		getCenter().setHeaderVisible(false);

		getCenter().add(getIPC());
		addCenterPartToWindow();

		show();
	}

}
