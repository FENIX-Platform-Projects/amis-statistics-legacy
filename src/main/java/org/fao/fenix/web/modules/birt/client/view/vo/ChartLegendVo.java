package org.fao.fenix.web.modules.birt.client.view.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChartLegendVo implements IsSerializable{
	
	private boolean visible;
	private int sizeLabel;
	private String position;
	private String unitSpacing;
		
	/*
	 * 
	 * #0 label
	 * #1 color
	 * 
	 */
	private List<List<String>> colorAndLabelFromClientToServer;
	
	public ChartLegendVo() {
		super();
		this.colorAndLabelFromClientToServer= new ArrayList<List<String>>();
	}
	
		
	public String getUnitSpacing() {
		return unitSpacing;
	}

	public void setUnitSpacing(String unitSpacing) {
		this.unitSpacing = unitSpacing;
	}

	public int getSizeLabel() {
		return sizeLabel;
	}

	public void setSizeLabel(int sizeLabel) {
		this.sizeLabel = sizeLabel;
	}

	public List<List<String>> getColorAndLabelFromClientToServer() {
		return colorAndLabelFromClientToServer;
	}


	public void addSetColorAndLabelFromClientToServer(List<String> colorAndLabelFromClientToServer) {
		this.colorAndLabelFromClientToServer.add(colorAndLabelFromClientToServer);
	}
	
	public void setColorAndLabelFromClientToServer(
			List<List<String>> colorAndLabelFromClientToServer) {
		this.colorAndLabelFromClientToServer = colorAndLabelFromClientToServer;
	}

	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	

}
