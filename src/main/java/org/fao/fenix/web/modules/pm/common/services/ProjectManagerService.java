package org.fao.fenix.web.modules.pm.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ProjectManagerService extends RemoteService {

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	List<FenixResourceVo> getProjectList();

	@Secured( { "ROLE_USER" })
	void deleteResourceFrom(Long projectId, Long resourceId);

	@Secured( { "ROLE_USER" })
	void addResourceTo(Long projectId, Long resourceId);

	@Secured( { "ROLE_USER" })
	void deleteProject(Long projectId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	List<FenixResourceVo> getResourceListFrom(Long projectId);

	@Secured( { "ROLE_USER" })
	Long saveProject(String title);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	Map<String, String> getNewProjectMetadata(Map<String, String> metadata);

	@Secured( { "ROLE_USER" })
	FenixResourceVo createProjectFenixResource(Long id);

	//-------------------------------------
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	Map<List<FenixResourceVo>, String> getResources(ResourceChildModel parent);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY"})
	List<FenixResourceVo> getResourceChildren(Long resourceId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	List<ResourceChildModel> getProjectManagerResourceModel(ResourceChildModel parent, List<ResourceChildModel> models);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	List<ResourceChildModel> createResourceModel(List<FenixResourceVo> resources, boolean isParent);


	@Secured( { "ROLE_USER" })
	FenixResourceVo getNewResource(Long id);
	
	@Secured( { "ROLE_USER" })
	Long createNewResource(String title, List<Long> resourceIdList);
	
	@Secured( { "ROLE_USER" })
	void addResourcesToNewProject(Long projectId, List<Long> resourceList);
	
}
