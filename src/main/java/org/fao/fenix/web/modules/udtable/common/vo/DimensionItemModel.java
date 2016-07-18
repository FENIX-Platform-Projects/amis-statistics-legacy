package org.fao.fenix.web.modules.udtable.common.vo;


import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class DimensionItemModel extends BaseTreeModel implements IsSerializable {

	private static final long serialVersionUID = 1L;

	public DimensionItemModel() {
	}

	public DimensionItemModel(String name, String code) {
		setName(name);
		setCode(code);
	}

	private void setName(String name) {
		set("name", name);
	}

	private void setCode(String code) {
		set("code", code);		
	}
	
	public String getName() {
		return get("name");
	}

	public String getCode() {
		return get("code");		
	}
}
