package org.fao.fenix.web.modules.adam.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.map.geoserver.FeatureType;
import org.fao.fenix.map.geoserver.io.GeoserverPublisher;

public class ADAMFPMISRequests {
	
	private final static Logger LOGGER = Logger.getLogger(ADAMFPMISRequests.class);

	
	public static void connectToFPMIS(String urlString) {
		        try {
//		            URL url = new URL("http://www.kodejava.org/index.html");
		        	
		        	URL url = new URL(urlString);
		            URLConnection connection = url.openConnection();

		            Map responseMap = connection.getHeaderFields();
		            for (Iterator iterator = responseMap.keySet().iterator(); iterator.hasNext();) {
		                String key = (String) iterator.next();
		                System.out.print(key + " = ");

		                List values = (List) responseMap.get(key);
		                for (int i = 0; i < values.size(); i++) {
		                    Object o = values.get(i);
		                    System.out.print(o + ", ");
		                }
		                System.out.println("");
		            }
		        } catch (MalformedURLException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }


	}
	
	public static String getString(String urlString) throws IOException, ProtocolException {
		LOGGER.info("urlString::getString : start " + urlString);
		URL url = new URL(urlString);
		
	
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);

		
		con.setRequestMethod("GET");
		
          
		System.out.println(con.getResponseCode());
		System.out.println(con.getResponseMessage());

		switch(con.getResponseCode()) {
			case HttpURLConnection.HTTP_OK:
				InputStreamReader is = new InputStreamReader(con.getInputStream());
				String response = readIs(is);
				is.close(); 
				return response;
			default:
				LOGGER.warn("Bad response from GS: code["+con.getResponseCode()+"] msg["+con.getResponseMessage()+"]");
				return null;
		}
	}
	
	public static String readIs(InputStreamReader is) {

		char[] inCh = new char[1024];
		StringBuffer input = new StringBuffer();
		int r;
		try {
			while((r = is.read(inCh)) > 0) {
				input.append(inCh, 0, r);
			}
		} catch(IOException e) {
		
			e.printStackTrace();
		}

		return input.toString();
	}
	
}
