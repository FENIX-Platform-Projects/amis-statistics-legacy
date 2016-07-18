package org.fao.fenix.web.modules.ec.common.services;

import java.util.List;
import java.util.TreeMap;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ec.common.vo.ECBeanVO;
import org.fao.fenix.web.modules.ec.common.vo.ECItemVO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ECServiceAsync {
	
	public void getCountryList(AsyncCallback<TreeMap<String, ECItemVO>> callback);	
	
	public void getHaitiEmergencyList(AsyncCallback<TreeMap<String, ECItemVO>> callback);	
	
	public void createReport(ECBeanVO ecBeanVO, AsyncCallback<String> callback);

	public void saveCharts(List<String> image, AsyncCallback<List<String>> callback);
	
	public void getLatestIrinNews(String gaulCode, int entries, AsyncCallback<List<String>> callback);
	
	public void getECCountry(String gaul0Code, AsyncCallback<CodeVo> callback);
	
	public void harverstGiewsCharts(AsyncCallback callback);
}
