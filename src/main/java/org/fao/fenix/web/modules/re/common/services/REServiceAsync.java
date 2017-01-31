package org.fao.fenix.web.modules.re.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixMenuItemVo;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceMetadataVo;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.re.common.vo.FenixSearchParameters;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceParentModel;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface REServiceAsync {
	
	public void getImages(List<Long> imageIDs, AsyncCallback<List<String>> callback) throws FenixGWTException;

	public void getMenubarModel(String modelName, AsyncCallback<List<FenixMenuItemVo>> callback);

	public void getFullList(AsyncCallback<FenixResourceVo> callback);

	public void getProjectResources(Long id, AsyncCallback<FenixResourceVo> callback);

	public void getMapsByProjectId(long id, AsyncCallback<FenixResourceVo> callback);

	public void getChartsByProjectId(long id, AsyncCallback<FenixResourceVo> callback);

	public void getTablesByProjectId(long id, AsyncCallback<FenixResourceVo> callback);

	public void getTextsByProjectId(long id, AsyncCallback<FenixResourceVo> callback);

	public void getCatalogueSize(String resourceType, AsyncCallback<String> callback);

	public void deleteResource(long id, String resourceType, AsyncCallback<?> callback);

	public void adminDeletesResource(long id, String resourceType, AsyncCallback<?> callback);

	public void saveTableView(String title, Long datasetID, List<String> list, AsyncCallback<Long> callback);

	public void getNewTableViewMetadata(FenixResourceMetadataVo metadata, Long datasetId, List<String> columns, AsyncCallback<FenixResourceMetadataVo> callback);

	public void createTableFenixResource(Long id, AsyncCallback<FenixResourceVo> callback);

	void getSearchResultListLength(FenixSearchParameters searchParameters, AsyncCallback<Integer> callback);

	void search(FenixSearchParameters searchParameters, int fromIndex, int toIndex, AsyncCallback<List<FenixResourceVo>> callback);

	public void getResourceModel(ResourceChildModel model, FenixSearchParameters fenixSearchParameters, int fromIndex, int toIndex, AsyncCallback<List<ResourceChildModel>> callback);

	public void getResourceChildren(Long resourceId, String resourceType, AsyncCallback<List<FenixResourceVo>> callback);

	public void createResourceModel(List<FenixResourceVo> resources, boolean isParent, AsyncCallback<List<ResourceChildModel>> callback);

	public void getResourceModel2(ResourceChildModel model, List<FenixResourceVo> resources, AsyncCallback<List<ResourceChildModel>> callback);

	public void searchCommunication(FenixSearchParameters fenixSearchParameters, int fromIndex, int toIndex, AsyncCallback<List<CommunicationResourceVo>> callback);

	public void getResourceModelCommunication2(ResourceChildModel parent, List<CommunicationResourceVo> resources, AsyncCallback<List<ResourceChildModel>> callback);
	
	public void getCommunicationResourceSize(FenixSearchParameters fenixSearchParameters, AsyncCallback<Integer> callback);

	public void getApplicationName(AsyncCallback<String> callback);
	
	public void getLastSavedDataset(AsyncCallback<List<String>> callback);
	
	public void getCategories(AsyncCallback<List<CodeVo>> callback);
	
	public void getCategoryLabel(String categoryCode, AsyncCallback<CodeVo> callback);
	
	public void getImagesByFolder(String folder, AsyncCallback<List<String>> callback);
	
	public void checkConnectedResources(long id, String resourceType, AsyncCallback<List<ResourceParentModel>> callback);
	
	public void associateDatesetToChart(Long chartId, Long datasetId, AsyncCallback<Boolean> callback);
	
	@SuppressWarnings("unchecked")
	public void changeLayerStyle(Long layerId, String styleName, AsyncCallback callback);
	
	public void getIMGTag(Long imageID, AsyncCallback<String> callback) throws FenixGWTException;
	
	public void getTools(String xmlFileName, String category, AsyncCallback<List<FenixMenuItemVo>> callback) throws FenixGWTException;
	
	public void getIntroText(AsyncCallback<String> callback) throws FenixGWTException;
	
	public void getNodeSettings(AsyncCallback<Map<String, String>> callback) throws FenixGWTException;
	
}