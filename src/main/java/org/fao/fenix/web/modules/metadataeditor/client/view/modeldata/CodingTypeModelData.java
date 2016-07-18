package org.fao.fenix.web.modules.metadataeditor.client.view.modeldata;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class CodingTypeModelData extends BaseModel implements IsSerializable {

	private String codingType;
	
	public CodingTypeModelData() {
		
	}
	
	public CodingTypeModelData(String codingType) {
		this.setCodingType(codingType);
	}

	public String getCodingType() {
		return codingType;
	}

	public void setCodingType(String codingType) {
		this.codingType = codingType;
		set("codingType", this.codingType);
	}
	
}