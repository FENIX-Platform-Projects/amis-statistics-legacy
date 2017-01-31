package org.fao.fenix.web.modules.venn.common.vo;


import com.google.gwt.user.client.rpc.IsSerializable;

public class GridHeaderVO implements IsSerializable {

	/**
	 * e.g. Geographic Area
	 */
	private String header;

	/**
	 * e.g. featureCode
	 */
	private String dataType;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	

	


	

}
