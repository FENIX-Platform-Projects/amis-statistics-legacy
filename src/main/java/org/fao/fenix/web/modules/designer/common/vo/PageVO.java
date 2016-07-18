package org.fao.fenix.web.modules.designer.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PageVO implements IsSerializable {

	private int pageNumber;
	
	private DesignerConstants orientation;
	
	private List<BoxVO> boxes;
	
	public void addBox(BoxVO vo) {
		if (this.boxes == null)
			this.boxes = new ArrayList<BoxVO>();
		this.boxes.add(vo);
	}
	
	public List<BoxVO> getBoxes() {
		return boxes;
	}

	public void setBoxes(List<BoxVO> boxes) {
		this.boxes = boxes;
	}

	public DesignerConstants getOrientation() {
		return orientation;
	}

	public void setOrientation(DesignerConstants orientation) {
		this.orientation = orientation;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
}