package org.fao.fenix.web.modules.giews.common.services;

import org.fao.fenix.web.modules.giews.common.vo.MapAndProject;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface GIEWSService extends RemoteService {
	
	public void createGiewsMapsImages();

	public void createProjects();
	
	public void updateProjects();
	
	public void deleteProjects();
	
	public MapAndProject getmapIdbyCode(String code);
	
	public MapAndProject getProjectbyCode(String code);
	
	public void createSingleProject(String code, String label);
	
	public Long getChartByTitle(String title);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String createEarlyWarningResources();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public void createEarlyWarningOutputZipFile();
}
