package org.fao.fenix.web.modules.haiti.common.services;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.haiti.common.vo.WMSModelData;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HaitiServiceAsync {

	/**
	 * Function to parse GeoServer Feature Info's HTML
	 * 
	 * @param featureInfoHTML GeoServer Feature Info's HTML
	 * @return Map<ColumnIndex, List<Values>>: idx(0) = Headers; idx(1): 1st Row; ...
	 * @throws FenixGWTException
	 */
	public void parseFeatureInfoHTML(String featureInfoHTML, String fenixCode, AsyncCallback<Map<Integer, List<String>>> callback) throws FenixGWTException;
	
	public void findAllWMSServers(AsyncCallback<List<WMSModelData>> callback);
	
	public void getLatestNews(List<String> urls, String gaulCode, int entries, AsyncCallback<List<String>> callback);

	public void getLayers(String url, Boolean isInternalLayer, ClientBBox countryBBox, Boolean checkBBox, AsyncCallback<List<ClientGeoView>> callback);
	
	public void getLatestNews(Map<String, String> sourceUrlMap, String gaulCode, int entries, AsyncCallback<Map<String, List<String>>> callback);

	public void getCropCalendarProvinces(String gaulCode, String langCode, AsyncCallback<List<CodeVo>> callback);
	
	public void loadPredifinedMapList(String gaul0code, AsyncCallback<List<ClientGeoView>> callback) throws FenixGWTException;
	
	public void getPredefinedMapLayers(String gaul0code, String predefineMapName, AsyncCallback<List<ClientGeoView>> callback) throws FenixGWTException;
	
	public void getHaitiLayers(String codingSystemTitle, String prefix, AsyncCallback<List<ClientGeoView>> callback);
	
	public void findAllCountryProvinces(String gaul0code, String language, AsyncCallback<Map<String, String>> callback);
	
	public void getMapsGallery(String countryCode, String language, AsyncCallback<TreeMap<String, List<CodeVo>>> callback);	
	
}