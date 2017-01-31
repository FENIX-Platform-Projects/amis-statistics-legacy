package org.fao.fenix.web.modules.haiticnsatool.common.services;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HaitiCNSAToolServiceAsync {
	
	
	public void retrieveCNSADates(String countryCode, AsyncCallback<HashMap<String, String>> callback);	
	
	public void retrieveCNSALayers(String countryCode, String date, AsyncCallback<List<ClientGeoView>> callback);
	
	public void retrieveCNSAChart(String countryCode, String date, String chartType, String width, String height, String chartTitle, String yAxesTitle, String language, AsyncCallback<String> callback);
	
	public void exportReport(ClientMapView cmv, List<ClientGeoView> cgvlist, String typeExport, String countryCode, String date, String chartType, String width, String height, String chartTitle, String yAxesTitle, String language, AsyncCallback<String> callback);

}