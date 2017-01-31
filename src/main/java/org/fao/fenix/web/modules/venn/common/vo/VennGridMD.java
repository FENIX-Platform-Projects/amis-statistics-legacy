package org.fao.fenix.web.modules.venn.common.vo;

import java.util.List;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class VennGridMD extends BaseTreeModel implements IsSerializable  {
	
	Integer quantityIndex;

	
	public VennGridMD() {
		
	}
	
	public VennGridMD(List<String> row) {
		for (int z = 0; z < row.size(); z++)
			set("column" + z, row.get(z));
	}
	
	public void addColumnValue(int column, String value) {
		set("column" + column, value);
	}

	public String getColumnValue(String columnId) {
		return (String) get(columnId);
	}

	public void addColumnValue(int column, Double value) {
		set("column" + column, value);
	}

	public Integer getQuantityIndex() {
		return quantityIndex;
	}

	public void setQuantityIndex(Integer quantityIndex) {
		this.quantityIndex = quantityIndex;
	}
	
	public Double getColumnQuantityValue(Integer columnIndex) {
		try {
			return (Double) get("column" + columnIndex);
		}
		catch (Exception e) {
			System.out.println(get("column" + columnIndex));
			return null;
		}

	}
	

}
