package org.fao.fenix.web.modules.venn.common.vo.report;


import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennReportGraphBeanVO implements IsSerializable {

	private VennGraphBeanVO vennGraphBeanVO;

	public VennGraphBeanVO getVennGraphBeanVO() {
		return vennGraphBeanVO;
	}

	public void setVennGraphBeanVO(VennGraphBeanVO vennGraphBeanVO) {
		this.vennGraphBeanVO = vennGraphBeanVO;
	}
	
}
