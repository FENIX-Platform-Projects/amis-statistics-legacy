package org.fao.fenix.web.modules.haiti.common.vo;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class FeatureInfoModelData extends BaseModel implements IsSerializable {

	public FeatureInfoModelData() {
		
	}
	
	public FeatureInfoModelData(List<String> row) {
		for (int z = 0; z < row.size(); z++)
			set("column" + z, row.get(z));
	}
	
	public void addColumnValue(int column, String value) {
		set("column" + column, value);
	}

	public String getColumnValue(String columnId) {
		return (String) get(columnId);
	}

//	public void setId(String id) {
//		set("id", id);
//	}
//
//	public String getId() {
//		return get("id");
//	}

}