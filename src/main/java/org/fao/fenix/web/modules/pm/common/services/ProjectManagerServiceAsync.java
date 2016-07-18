package org.fao.fenix.web.modules.pm.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectManagerServiceAsync {

	void getProjectList(AsyncCallback<List<FenixResourceVo>> callback);
	void deleteResourceFrom(Long projectId, Long resourceId, AsyncCallback<?> callback);
	void addResourceTo(Long projectId, Long resourceId, AsyncCallback<?> callback);
	//void create(FenixResourceMetadataVo project, AsyncCallback<Long> callback);
	void deleteProject(Long projectId, AsyncCallback<?> callback);
	void getResourceListFrom(Long projectId, AsyncCallback<List<FenixResourceVo>> callback);

	void saveProject(String title, AsyncCallback<Long> callback);
	void getNewProjectMetadata(Map<String, String> metadata, AsyncCallback<Map<String, String>> callback);
	void createProjectFenixResource(Long id, AsyncCallback<FenixResourceVo> callback);
//-------------

	void  getResources(ResourceChildModel parent, AsyncCallback<Map<List<FenixResourceVo>, String>> callback);
	void  getResourceChildren(Long resourceId, AsyncCallback<List<FenixResourceVo>> callback);


	void getProjectManagerResourceModel(ResourceChildModel parent, List<ResourceChildModel> models, AsyncCallback<List<ResourceChildModel>> callback);
	void createResourceModel(List<FenixResourceVo> resources, boolean isParent, AsyncCallback<List<ResourceChildModel>> callback);

    void createNewResource(String title, List<Long> resourceIdList, AsyncCallback<Long> callback);

	void getNewResource(Long id, AsyncCallback<FenixResourceVo> callback);
	
	void addResourcesToNewProject(Long projectId, List<Long> resourceList, AsyncCallback<?> callback);

}

