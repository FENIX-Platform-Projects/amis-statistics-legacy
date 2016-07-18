package org.fao.fenix.web.modules.venn.common.vo;



import java.util.HashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennIframeVO implements IsSerializable {
	
	String text;
	
	String iframePortlet;
	
	String iframeCentralPanel;

	public String getIframePortlet() {
		return iframePortlet;
	}

	public void setIframePortlet(String iframePortlet) {
		this.iframePortlet = iframePortlet;
	}

	public String getIframeCentralPanel() {
		return iframeCentralPanel;
	}

	public void setIframeCentralPanel(String iframeCentralPanel) {
		this.iframeCentralPanel = iframeCentralPanel;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
