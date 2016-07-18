package org.fao.fenix.web.modules.dataviewer.server;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class MapsServiceUtils {
	
	private String mapsIP;

	private String mapsPORT;
	
	private String faostatBaseURL;
	
	private final static Logger LOGGER = Logger.getLogger(MapsServiceUtils.class);

	public String getMap(Map<String, Double> values, String legendtitle, String mu) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("http://"+ mapsIP +":" + mapsPORT + "/maps/api?");
		
		sb.append(faostatBaseURL);

		sb.append(addData(values));
		
		if ( legendtitle != null )
			sb.append("&legendtitle=" + legendtitle.replaceAll(" ", "+"));
		
		if ( mu != null )
			sb.append("&mu=" + mu.replaceAll(" ", "+"));
		
		LOGGER.info(sb.toString());
		return sb.toString();
	}

	private String addData(Map<String, Double> values) {
		StringBuilder sb = new StringBuilder();
		// data
		sb.append("&joindata=[");
		int i=0;
		for(String key : values.keySet()) {
			sb.append("("+ key + ","+values.get(key) +")");
			if ( i < values.size() -1)
				sb.append(",");
			i++;
		}
		sb.append("]");
		return sb.toString();
	}
	
	public void setMapsIP(String mapsIP) {
		this.mapsIP = mapsIP;
	}

	public void setMapsPORT(String mapsPORT) {
		this.mapsPORT = mapsPORT;
	}

	public void setFaostatBaseURL(String faostatBaseURL) {
		this.faostatBaseURL = faostatBaseURL;
	}
	
}
