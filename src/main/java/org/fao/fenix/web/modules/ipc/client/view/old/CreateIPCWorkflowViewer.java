package org.fao.fenix.web.modules.ipc.client.view.old;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.google.gwt.user.client.ui.HTML;

public class CreateIPCWorkflowViewer extends FenixWindow {

	private HTML ipc;
	private String areaName;
	private String layerName;
	
	public CreateIPCWorkflowViewer(String ipcIFrame, String area, String layer){
		this.setAreaName(area);
		this.setLayerName(layer);
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
	
	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	
	public void build(){
		setTitle(BabelFish.print().ipcWorkflow()+": "+getAreaName() + " "+ getLayerName());
		setSize(650, 650);
		setAutomaticScrollBar();
		
		setCenterProperties();
		getCenter().setHeaderVisible(false);
		
		getCenter().add(getIPC());
		addCenterPartToWindow();
				
		show();
	
	}
	
}
