package org.fao.fenix.web.modules.core.client.view.vo;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataViewVo  implements IsSerializable {

	private Long id;
	private String name;
	private List fields;

	public DataViewVo() {
		super();
	}
	

	public DataViewVo(Long id, String name, List fields) {
		super();
		this.id = id;
		this.name = name;
		this.fields = fields;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getFields() {
		return fields;
	}

	public void setFields(List fields) {
		this.fields = fields;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
