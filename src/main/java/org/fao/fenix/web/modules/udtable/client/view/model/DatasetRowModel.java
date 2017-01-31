package org.fao.fenix.web.modules.udtable.client.view.model;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class DatasetRowModel extends BaseTreeModel implements IsSerializable {

	public DatasetRowModel() {
	}

	public DatasetRowModel(List<String> row) {
		for (int z = 0; z < row.size(); z++) {
			System.out.println("-------------- DatasetRowModel:: column" + z + row.get(z));	
			set("column" + z, row.get(z));  
		}
				
	}

	public String getColumnValue(String columnId) {
		return (String) get(columnId);
	}
}
