/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.olap.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UniqueValueVO implements IsSerializable {

	private Long id;
	
	private Long ds_id;
	
	private String datatype;
	
	private String header;
	
	private String code;
	
	private String label;
	
	private String langCode;
	
	private String periodType;
	
	public String toString() {
		return "ID: " + id + "\n" +
			   "Dataset ID: " + this.getDs_id() + "\n" +
			   "Data Type: " + this.getDatatype() + "\n" +
			   "Header: " + this.getHeader() + "\n" +
			   "Code: " + this.getCode() + "\n" +
			   "Label: " + this.getLabel() + "\n" +
			   "Priod Type Code: " + this.getPeriodType() + "\n" + 
			   "Lang Code: " + this.getLangCode();
	}
	
	public String getLangCode() {
		return langCode;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDs_id() {
		return ds_id;
	}

	public void setDs_id(Long dsId) {
		ds_id = dsId;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}