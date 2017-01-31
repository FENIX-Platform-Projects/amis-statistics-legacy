package org.fao.fenix.web.modules.haiticnsatool.common.services;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;

import com.google.gwt.user.client.rpc.RemoteService;

public interface HaitiCNSAToolService extends RemoteService {

	// this is for the HAITI CNSA Tool
	
	public HashMap<String, String> retrieveCNSADates(String countryCode);
	
	public List<ClientGeoView> retrieveCNSALayers(String countryCode, String date);
	
	public String retrieveCNSAChart(String countryCode, String date, String chartType, String width, String height, String chartTitle, String yAxesTitle, String language);
	
	public String exportReport(ClientMapView cmv, List<ClientGeoView> cgvlist, String typeExport, String countryCode, String date, String chartType, String width, String height, String chartTitle, String yAxesTitle, String language );
	
	
}