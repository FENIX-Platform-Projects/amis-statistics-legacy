package org.fao.fenix.web.modules.haiti.common.services;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.haiti.common.vo.WMSModelData;
import org.fao.fenix.web.modules.map.common.vo.ClientBBox;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;

import com.google.gwt.user.client.rpc.RemoteService;

public interface HaitiService extends RemoteService {

	/**
	 * Function to parse GeoServer Feature Info's HTML
	 * 
	 * @param featureInfoHTML GeoServer Feature Info's HTML
	 * @return Map<ColumnIndex, List<Values>>: idx(0) = Headers; idx(1): 1st Row; ...
	 * @throws FenixGWTException
	 */
	public Map<Integer, List<String>> parseFeatureInfoHTML(String featureInfoHTML, String fenixCode) throws FenixGWTException;
	
	public List<WMSModelData> findAllWMSServers();
	
	public List<String> getLatestNews(List<String> urls, String gaulCode, int entries);
	
	public List<ClientGeoView> getLayers(String url, Boolean isInternalLayer, ClientBBox countryBBox, Boolean checkBBox);
	
	public Map<String, List<String>> getLatestNews(Map<String, String> sourceUrlMap, String gaulCode, int entries);
	
	public List<CodeVo> getCropCalendarProvinces(String gaulCode, String langCode);
	
	public List<ClientGeoView> loadPredifinedMapList(String gaul0code) throws FenixGWTException;
	
	public List<ClientGeoView> getPredefinedMapLayers(String gaul0code, String predefineMapName) throws FenixGWTException;
	
	public List<ClientGeoView> getHaitiLayers(String codingSystemTitle, String prefix);
	
	public Map<String, String> findAllCountryProvinces(String gaul0code, String language);
	
	public TreeMap<String, List<CodeVo>> getMapsGallery(String countryCode, String language) throws FenixGWTException;	
	
}