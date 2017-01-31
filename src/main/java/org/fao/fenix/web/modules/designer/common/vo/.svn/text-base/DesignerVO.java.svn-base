package org.fao.fenix.web.modules.designer.common.vo;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;

import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class DesignerVO extends ResourceVO implements IsSerializable {

	private List<PageVO> pages;
	
	private Long reportID;

	public void addPage(PageVO vo) {
		if (this.pages == null)
			this.pages = new ArrayList<PageVO>();
		this.pages.add(vo);
	}
	
	public List<PageVO> getPages() {
		return pages;
	}

	public void setPages(List<PageVO> pages) {
		this.pages = pages;
	}

	public Long getReportID() {
		return reportID;
	}

	public void setReportID(Long reportID) {
		this.reportID = reportID;
	}
	
}