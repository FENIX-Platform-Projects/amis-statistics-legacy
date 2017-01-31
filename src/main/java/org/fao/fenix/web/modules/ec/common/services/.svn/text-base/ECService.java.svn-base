package org.fao.fenix.web.modules.ec.common.services;

import java.util.List;
import java.util.TreeMap;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ec.common.vo.ECBeanVO;
import org.fao.fenix.web.modules.ec.common.vo.ECItemVO;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ECService extends RemoteService {
	
	public TreeMap<String, ECItemVO> getCountryList();	
	
	public TreeMap<String, ECItemVO> getHaitiEmergencyList();	
	
	public String createReport(ECBeanVO ecBeanVO);
	
	public List<String> saveCharts(List<String> images);
	
	public List<String> getLatestIrinNews(String gaulCode, int entries);
	
	public CodeVo getECCountry(String gaul0Code);
	
	public void harverstGiewsCharts();
	
}
