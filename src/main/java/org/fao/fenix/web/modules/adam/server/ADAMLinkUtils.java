package org.fao.fenix.web.modules.adam.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;

public class ADAMLinkUtils {
	
	private final static Logger LOGGER = Logger.getLogger(ADAMLinkUtils.class);


	public static void createLinksMap(Map<String, String> map, List<Object[]> contents) {
		for(Object[] content : contents){
			try {
				map.put(content[0].toString(), content[1].toString());
			}
			catch (Exception e) {
			}
		}
	}
	
	
	public static void createGlobalDACLinkMap(Map<String, String> map, List<Object[]> contents) {
		for(Object[] content : contents){
			try {
				map.put(content[2].toString(), content[1].toString());
			}
			catch (Exception e) {
			}
		}
	}
	
	public static void createGlobalChannelsLinkMap(Map<String, String> map, List<Object[]> contents) {
		for(Object[] content : contents){
			try {
				map.put(content[1].toString(), content[2].toString());
			}
			catch (Exception e) {
			}
		}
	}
	
	public static void createGlobalRecipientLinkMap(Map<String, String> map, List<Object[]> contents) {
		for(Object[] content : contents){
			try {
				map.put(content[1].toString(), content[2].toString());
			}
			catch (Exception e) {
			}
		}
	}
	
}
