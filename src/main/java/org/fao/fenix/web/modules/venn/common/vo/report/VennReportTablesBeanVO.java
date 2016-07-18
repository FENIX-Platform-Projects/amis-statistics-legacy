package org.fao.fenix.web.modules.venn.common.vo.report;


import java.util.HashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

public class VennReportTablesBeanVO implements IsSerializable {

	// this is refered to each TAB containing data on the tables
	private HashMap<String, VennReportTabTablesBeanVO> vennReportTabTablesBeanVO;

	public HashMap<String, VennReportTabTablesBeanVO> getVennReportTabTablesBeanVO() {
		return vennReportTabTablesBeanVO;
	}

	public void setVennReportTabTablesBeanVO(
			HashMap<String, VennReportTabTablesBeanVO> vennReportTabTablesBeanVO) {
		this.vennReportTabTablesBeanVO = vennReportTabTablesBeanVO;
	}
	

}
