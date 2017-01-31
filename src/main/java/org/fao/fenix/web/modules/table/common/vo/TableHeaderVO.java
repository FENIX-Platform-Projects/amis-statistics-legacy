package org.fao.fenix.web.modules.table.common.vo;

import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TableHeaderVO extends ResourceVO implements IsSerializable {

	/**
	 * e.g. Geographic Area
	 */
	private String header;

	/**
	 * e.g. featureCode
	 */
	private String dataType;

	/**
	 * e.g. text
	 */
	private String rawType;

	/**
	 * e.g. true :)
	 */
	private Boolean isCode;

	/**
	 * e.g. true :)
	 */
	private Boolean hasCodingSystem;

	/**
	 * e.g. GAUL
	 */
	private String codingSystemName;

	/**
	 * e.g. 0
	 */
	private String codingSystemRegion;

	/**
	 * e.g. EN
	 */
	private String codingSystemLang;

	public TableHeaderVO() {
		this.setIsCode(false);
		this.setHasCodingSystem(false);
		this.setCodingSystemLang("EN");
		this.setCodingSystemName("");
		this.setCodingSystemRegion("0");
	}

	public String getDataType() {
		return dataType;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getRawType() {
		return rawType;
	}

	public void setRawType(String rawType) {
		this.rawType = rawType;
	}

	public Boolean getIsCode() {
		return isCode;
	}

	public void setIsCode(Boolean isCode) {
		this.isCode = isCode;
	}

	public Boolean getHasCodingSystem() {
		return hasCodingSystem;
	}

	public void setHasCodingSystem(Boolean hasCodingSystem) {
		this.hasCodingSystem = hasCodingSystem;
	}

	public String getCodingSystemName() {
		return codingSystemName;
	}

	public void setCodingSystemName(String codingSystemName) {
		this.codingSystemName = codingSystemName;
	}

	public String getCodingSystemRegion() {
		return codingSystemRegion;
	}

	public void setCodingSystemRegion(String codingSystemRegion) {
		this.codingSystemRegion = codingSystemRegion;
	}

	public String getCodingSystemLang() {
		return codingSystemLang;
	}

	public void setCodingSystemLang(String codingSystemLang) {
		this.codingSystemLang = codingSystemLang;
	}

}
