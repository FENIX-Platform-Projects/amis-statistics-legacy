package org.fao.fenix.web.modules.udtable.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DimensionBean implements IsSerializable {

	/**
	 *
	 */
	private List<DimensionItemModel> selectedDimensionItemModelList = new ArrayList<DimensionItemModel>();

	private String columnDescriptor;
	private boolean hasCodingSystem = false;
    private String label;	
	private String codingSystemName;
	private String codingSystemValue;
	private String codingSystemRegion;
	private String gaulReference;

	public List<DimensionItemModel> getSelectedDimensionItemModelList() {
		return selectedDimensionItemModelList;
	}

	public void setSelectedDimensionItemModelList(
			List<DimensionItemModel> selectedDimensionItemModelList) {
		this.selectedDimensionItemModelList = selectedDimensionItemModelList;
	}

	public String getColumnDescriptor() {
		return columnDescriptor;
	}

	public void setColumnDescriptor(String columnDescriptor) {
		this.columnDescriptor = columnDescriptor;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setHasCodingSystem(boolean hasCodingSystem) {
		this.hasCodingSystem = hasCodingSystem;
	}

	public boolean hasCodingSystem() {
		return hasCodingSystem;
	}
	
	public String getCodingSystemName() {
		return codingSystemName;
	}

	public void setCodingSystemName(String codingSystemName) {
		this.codingSystemName = codingSystemName;
	}
	
	public String getCodingSystemValue() {
		return codingSystemValue;
	}

	public void setCodingSystemValue(String codingSystemValue) {
		this.codingSystemValue = codingSystemValue;
	}
	
	public String getCodingSystemRegion() {
		return codingSystemRegion;
	}

	public void setCodingSystemRegion(String codingSystemRegion) {
		this.codingSystemRegion = codingSystemRegion;
	}
	
	public String getGAULReference() {
		return gaulReference;
	}

	public void setGAULReference(String gaulReference) {
		this.gaulReference = gaulReference;
	}
	

}
