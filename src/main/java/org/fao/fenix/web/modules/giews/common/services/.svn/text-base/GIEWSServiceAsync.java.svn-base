package org.fao.fenix.web.modules.giews.common.services;

import org.fao.fenix.web.modules.giews.common.vo.MapAndProject;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GIEWSServiceAsync {
	
	public void createGiewsMapsImages(AsyncCallback<Void> callback);

	public void getmapIdbyCode(String code, AsyncCallback<MapAndProject> callback);
	
	public void getProjectbyCode(String code, AsyncCallback<MapAndProject> callback);

	@SuppressWarnings("unchecked")
	public void createProjects(AsyncCallback callback);
	
	@SuppressWarnings("unchecked")
	public void updateProjects(AsyncCallback callback);
	
	@SuppressWarnings("unchecked")
	public void deleteProjects(AsyncCallback callback);
	
	@SuppressWarnings("unchecked")
	public void createSingleProject(String code, String label, AsyncCallback callback);
	
    public void createEarlyWarningResources(AsyncCallback<String> callback);
	
    public void createEarlyWarningOutputZipFile(AsyncCallback<?> callback);
    
    public void  getChartByTitle(String title, AsyncCallback<Long> callback);
	
}
