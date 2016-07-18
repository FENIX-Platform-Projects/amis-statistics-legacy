package org.fao.fenix.web.modules.table.common.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DimensionBeanVO implements IsSerializable {

	private List<DimensionItemModel> selectedDimensionItemModelList = new ArrayList<DimensionItemModel>();

	private String id;
	private String columnDataType;
	private boolean hasCodingSystem = false;
    private String header;	
	private CodingSystemVo codingSystemVO;
	private Map<String, String> dimensionValues;
	private String value;
	private boolean isDate = false;
	
	

	public String getDescriptorId() {
		return id;
	}

	public void setDescriptorId(String id) {
		this.id = id;
	}
	
	public List<DimensionItemModel> getSelectedDimensionItemModelList() {
		return selectedDimensionItemModelList;
	}

	public void setSelectedDimensionItemModelList(
			List<DimensionItemModel> selectedDimensionItemModelList) {
		this.selectedDimensionItemModelList = selectedDimensionItemModelList;
	}

	public String getColumnDataType() {
		return columnDataType;
	}

	public void setColumnDataType(String columnDataType) {
		this.columnDataType = columnDataType;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String label) {
		this.header = label;
	}

	public void setHasCodingSystem(boolean hasCodingSystem) {
		this.hasCodingSystem = hasCodingSystem;
	}

	public boolean hasCodingSystem() {
		return hasCodingSystem;
	}
	
	
	public void setCodingSystemVO(CodingSystemVo codingSystemVO) {
		this.codingSystemVO = codingSystemVO;
	}
	
	public CodingSystemVo getCodingSystemVO() {
		return codingSystemVO;
	}

	public Map<String, String> getDistinctDimensionValues() {
		return dimensionValues;
	}
	
	public void setDistinctDimensionValues(Map<String, String> values) {
		this.dimensionValues = values; 			 
	}
	
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value; 			 
	}

	public boolean isDate() {
		return isDate;
	}

	public void setIsDate(boolean isDate) {
		this.isDate = isDate;
	}

	
	
}
