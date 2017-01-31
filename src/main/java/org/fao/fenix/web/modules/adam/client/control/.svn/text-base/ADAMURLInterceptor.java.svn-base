package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.ADAM;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMURLParameters;

import com.google.gwt.http.client.URL;



public class ADAMURLInterceptor {
	
	/*public static void parseParams(String url) {
		System.out.println("ADAMURLInterceptor params: " + url);
		
		String[] urlParts = url.split("\\?");
		if (urlParts.length > 1) {
		    String query = urlParts[1];
		    for (String param : query.split("&")) {
		        String[] pair = param.split("=");
		        
		        String key = URL.decode(pair[0]);
				String value = URL.decode(pair[1]);
		        
		        System.out.println("KEY: " + key);
				System.out.println("VALUES: " + value);
				
		        try {
			        ADAMURLParameters urlParameter = ADAMURLParameters.valueOf(key);
			        
			        // TODO: parse the values 
			        List<String> values = getParameters(value, "+");
			        
			        if ( !values.isEmpty() )
			        	ADAM.parameters.put(urlParameter, values);
			        
//			        USER SWITCHER?? 
//			        switch (urlParameter) {
//					case view:
//						System.out.println("view");
//						break;
//	
//					default:
//						break;
//					}
		        }
		        catch (Exception e) {
				}
		        
		        
//				System.out.println("KEY: " + key);
//			
//				String value = URL.decode(pair[1]);
//				System.out.println("value: " + value);
//		        List<String> values = params.get(key);
//		        if (values == null) {
//		            values = new ArrayList<String>();
//		            params.put(key, values);
//		        }
//		        values.add(value);
		    }
		}
		
		
		    for(ADAMURLParameters key : ADAM.parameters.keySet()) { 
		    	for(String value : ADAM.parameters.get(key)){
		    		System.out.println("KEY: " + key.toString() + " | " + " VALUE: " + value );
		    	}
		    }
	}
	
	private static List<String> getParameters(String value, String delimiter) {
		List<String> values = new ArrayList<String>();
		
		
		if ( !value.contains(delimiter)) {
			// just one parameter 
			values.add(value);			
		}
		else {
			//TODO: Parse values
			System.out.println("VALUE: " + value);
			while(value.contains(delimiter)) {
				String subValue = value.substring(0, value.indexOf(delimiter));
				
				System.out.println("SUB VALUE: " + subValue);
				values.add(subValue);
				
				value = value.substring(value.indexOf(delimiter) +1);

			}
			
			System.out.println("SUB VALUE: " + value);
			values.add(value);
		}
		
		
		
		return values;
	}*/

}
