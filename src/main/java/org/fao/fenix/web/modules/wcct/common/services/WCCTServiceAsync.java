package org.fao.fenix.web.modules.wcct.common.services;

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.wcct.common.vo.WCCTVo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WCCTServiceAsync {
	
	public void createMap(AsyncCallback<Boolean> callback);
	
	public void getCountries(AsyncCallback<List<CodeVo>> callback);
	
	public void getCommodities(AsyncCallback<List<CodeVo>> callback);
	
	public void getCommoditiesByCountry(List<String> countries, AsyncCallback<List<CodeVo>> callback);
	
	public void getSeason(String commodity, AsyncCallback<List<CodeVo>> callback);
	
	public void getLocations(String commodity, AsyncCallback<List<CodeVo>> callback);
	
	public void getOthers(String commodity, AsyncCallback<List<CodeVo>> callback);
	
	public void getDimensions(String commodity, AsyncCallback<List<List<CodeVo>>> callback);
	
	public void fillTable(List<String> countries, List<String> commodities, AsyncCallback<List<WCCTVo>> callback);
	
	public void getImage(AsyncCallback<String> callback);
	
	public void getMap(Boolean dekade, String month, String day, String crop, String season, String others, AsyncCallback<String> callback);

}
