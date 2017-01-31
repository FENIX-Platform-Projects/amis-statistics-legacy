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
package org.fao.fenix.web.modules.amis.common.model;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class AMISCodesModelData extends BaseModel implements IsSerializable {

	private String code;

	private String label;

	private Boolean isList = false;

	private String domain;
	
	private ArrayList<String> codeList;

	public AMISCodesModelData() {

	}

	public AMISCodesModelData(String code, String label) {
		this.setLabel(label);
		this.setCode(code);
	}

	public AMISCodesModelData(String code, String label, Boolean isList) {
		this.setLabel(label);
		this.setCode(code);
		this.setIsList(isList);
	}

	public AMISCodesModelData(String code, String label, String domain, Boolean isList) {
		this.setLabel(label);
		this.setCode(code);
		this.setDomain(domain);
		this.setIsList(isList);
	}

	public AMISCodesModelData(String code, String label, String domain) {
		this.setLabel(label);
		this.setCode(code);
		this.setDomain(domain);
	}

	public String getCode() {
		return code;
	}

	public Boolean getIsList() {
		return isList;
	}

	public String getDomain() {
		return domain;
	}

	public void setCode(String code) {
		this.code = code;
		set("code", this.code);
	}

	public void setIsList(Boolean isList) {
		this.isList = isList;
		set("isList", this.isList);
	}

	public Boolean isList() {
		return isList;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
		set("label", this.label);
	}

	public void setDomain(String domain) {
		this.domain = domain;
		set("domain", this.domain);
	}
	
	public ArrayList<String> getCodeList() {
		return codeList;
	}

	public void setCodeList(ArrayList<String> codeList) {
		this.codeList = codeList;
	}


}