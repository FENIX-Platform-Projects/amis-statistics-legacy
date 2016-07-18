package org.fao.fenix.web.modules.chartdesigner.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ChartDesignerFilterVO implements IsSerializable {

	private Long datasetID = null;
	
	private String header = "";
	
	private String contentDescriptor = "";
	
	private List<String> codes = new ArrayList<String>();

	public Long getDatasetID() {
		return datasetID;
	}

	public void setDatasetID(Long datasetID) {
		this.datasetID = datasetID;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContentDescriptor() {
		return contentDescriptor;
	}

	public void setContentDescriptor(String contentDescriptor) {
		this.contentDescriptor = contentDescriptor;
	}

	public List<String> getCodes() {
		return codes;
	}

	public void setCodes(List<String> codes) {
		this.codes = codes;
	}
	
}