package org.fao.fenix.web.modules.re.client.control;

import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

public class REModel {

	public static final boolean CLOSE = false;
	private static boolean reIsopen; // to avoid multiple instances of the RE
	private String resourceType;
	private String caller;
	private int totalPagerItems;
	private int actualPagerPage;
	private String scope; // to activate the communication module
	private static ResourceExplorer resourceExplorer; //current resource explorer

	public static boolean isReIsopen() {
		return reIsopen;
	}

	public static void setReIsopen(boolean reIsopen) {
		REModel.reIsopen = reIsopen;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public int getTotalPagerItems() {
		return totalPagerItems;
	}

	public void setTotalPagerItems(int totalPagerItems) {
		this.totalPagerItems = totalPagerItems;
	}

	public int getActualPagerPage() {
		return actualPagerPage;
	}

	public void setActualPagerPage(int actualPagerPage) {
		this.actualPagerPage = actualPagerPage;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public static ResourceExplorer getResourceExplorer() {
		return resourceExplorer;
	}

	public static void setResourceExplorer(ResourceExplorer openExplorer) {
		REModel.resourceExplorer = openExplorer;
	}

}