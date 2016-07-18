package org.fao.fenix.web.modules.bangladesh.common.services;

import java.util.Date;
import java.util.List;


import com.google.gwt.user.client.rpc.RemoteService;

public interface BangladeshService extends RemoteService {

	public String createReport(List<String> chartFileNames, String issue, Date date);
	
//	public List<ChartWizardBean> getBirtCharts(Date date);
	
}
