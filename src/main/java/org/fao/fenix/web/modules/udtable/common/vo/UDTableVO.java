package org.fao.fenix.web.modules.udtable.common.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UDTableVO extends ResourceVO implements IsSerializable {

	private Boolean isCore;

	private List<UDTableRowVO> rows;

	private List<UDTableFilterVO> filters;
	
	private List<UDTableHeaderVO> headers;
	
	
	private String datasetTitle;	
	private String datasetType;
	private Map<String, DimensionBean> dimensionDescriptorMap = new LinkedHashMap<String, DimensionBean>();
	private int dimensionSize;
    private String datasetExplicitType;
    
    private int recordCount;
	
	public UDTableVO() {
		this.rows = new ArrayList<UDTableRowVO>();
		this.filters = new ArrayList<UDTableFilterVO>();
	}

	public void addRow(UDTableRowVO row) {
		if (this.rows == null)
			this.rows = new ArrayList<UDTableRowVO>();
		this.rows.add(row);
	}

	public void addFilter(UDTableFilterVO filter) {
		if (this.filters == null)
			this.filters = new ArrayList<UDTableFilterVO>();
		this.filters.add(filter);
	}
	
	public void addHeader(UDTableHeaderVO header) {
		if (this.headers == null)
			this.headers = new ArrayList<UDTableHeaderVO>();
		this.headers.add(header);
	}

	public Boolean getIsCore() {
		return isCore;
	}

	public void setIsCore(Boolean isCore) {
		this.isCore = isCore;
	}

	public List<UDTableRowVO> getRows() {
		return rows;
	}

	public void setRows(List<UDTableRowVO> rows) {
		this.rows = rows;
	}

	public List<UDTableFilterVO> getFilters() {
		return filters;
	}

	public void setFilters(List<UDTableFilterVO> filters) {
		this.filters = filters;
	}

	public List<UDTableHeaderVO> getHeaders() {
		return headers;
	}

	public void setHeaders(List<UDTableHeaderVO> headers) {
		this.headers = headers;
	}

	public String getDatasetTitle() {
		return datasetTitle;
	}

	public void setDatasetTitle(String datasetTitle) {
		this.datasetTitle = datasetTitle;
	}

	/* core or flex */
	public String getDatasetType() {
		return datasetType;
	}

	public void setDatasetType(String datasetType) {
		this.datasetType = datasetType;
	}

       /* e.g. UnfavourableProspects*/
	public String getDatasetExplicitType() {
		return datasetExplicitType;
	}

	public void setDatasetExplicitType(String datasetExplicitType) {
		this.datasetExplicitType = datasetExplicitType;
	}

	public Map<String, DimensionBean> getDimensionDescriptorMap() {
		return dimensionDescriptorMap;
	}

	public void setDimensionDescriptorMap(
			Map<String, DimensionBean> dimensionDescriptorMap) {
		this.dimensionDescriptorMap = dimensionDescriptorMap;
	}

	public int getDimensionSize() {
		return dimensionSize;
	}

	public void setDimensionSize(int dimensionSize) {
		this.dimensionSize = dimensionSize;
	}
	
	public int getDatasetRecordCount() {
		return recordCount;
	}
		
	public void setDatasetRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}