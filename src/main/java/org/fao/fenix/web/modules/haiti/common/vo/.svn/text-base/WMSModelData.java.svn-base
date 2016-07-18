package org.fao.fenix.web.modules.haiti.common.vo;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class WMSModelData extends BaseModel implements IsSerializable {

	private String wmsURL;
	
	private String wmsLabel;
	
	private boolean isInternal;
	
	public WMSModelData() {
		
	}
	
	public WMSModelData(String wmsLabel, String wmsURL, boolean isInternal) {
		this.setWmsLabel(wmsLabel);
		this.setWmsURL(wmsURL);
		this.setInternal(isInternal);
	}

	public String getWmsURL() {
		return wmsURL;
	}

	public void setWmsURL(String wmsURL) {
		this.wmsURL = wmsURL;
		set("wmsURL", this.wmsURL);
	}

	public String getWmsLabel() {
		return wmsLabel;
	}

	public void setWmsLabel(String wmsLabel) {
		this.wmsLabel = wmsLabel;
		set("wmsLabel", this.wmsLabel);
	}

	public boolean isInternal() {
		return isInternal;
	}

	public void setInternal(boolean isInternal) {
		this.isInternal = isInternal;
		set("isInternal", this.isInternal);
	}
	
}