package org.fao.fenix.web.modules.wcct.common.services;

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.wcct.common.vo.WCCTVo;

import com.google.gwt.user.client.rpc.RemoteService;

public interface WCCTService extends RemoteService {
	
	public Boolean createMap();

	public List<CodeVo> getCountries();
	
	public List<CodeVo> getCommodities();
	
	public List<CodeVo> getCommoditiesByCountry(List<String> countries);
	
	public List<CodeVo> getSeason(String commodity);
	
	public List<CodeVo> getLocations(String commodity);
	
	public List<CodeVo> getOthers(String commodity);
	
	public List<List<CodeVo>> getDimensions(String commodity);

	public List<WCCTVo> fillTable(List<String> countries, List<String> commodities);
	
	public String getImage();
	
	public String getMap(Boolean dekade, String month, String day, String crop, String season, String others);
	
}
