package org.fao.fenix.web.modules.udtable.common.vo;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UDTableRowVO extends ResourceVO implements IsSerializable {

	private Long rowId;

	private List<UDTableCellVO> cells;

	public void addCell(UDTableCellVO cell) {
		if (this.cells == null)
			this.cells = new ArrayList<UDTableCellVO>();
		this.cells.add(cell);
	}

	public Long getRowId() {
		return rowId;
	}

	public void setRowId(Long rowId) {
		this.rowId = rowId;
	}

	public List<UDTableCellVO> getCells() {
		return cells;
	}

	public void setCells(List<UDTableCellVO> cells) {
		this.cells = cells;
	}

}