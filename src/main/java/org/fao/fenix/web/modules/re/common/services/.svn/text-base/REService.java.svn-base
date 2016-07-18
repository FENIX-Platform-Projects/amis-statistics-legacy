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
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface REService extends RemoteService {
	
	public List<String> getImages(List<Long> imageIDs) throws FenixGWTException;

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixMenuItemVo> getMenubarModel(String modelName);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixResourceVo> getFullList();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixResourceVo> getProjectResources(Long id);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixResourceVo> getMapsByProjectId(long id);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixResourceVo> getChartsByProjectId(long id);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixResourceVo> getTablesByProjectId(long id);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixResourceVo> getTextsByProjectId(long id);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String getCatalogueSize(String resourceType);

	@Secured( { "ROLE_USER" })
	public void deleteResource(long id, String resourceType);

	@Secured( { "ROLE_ADMIN" })
	public void adminDeletesResource(long id, String resourceType);

	@Secured( { "ROLE_USER" })
	public Long saveTableView(String title, Long datasetID, List<String> list);

	@Secured( { "ROLE_USER" })
	public FenixResourceMetadataVo getNewTableViewMetadata(FenixResourceMetadataVo metadata, Long datasetId,
			List<String> columns);

	@Secured( { "ROLE_USER" })
	public FenixResourceVo createTableFenixResource(Long id);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Integer getSearchResultListLength(FenixSearchParameters searchParameters);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixResourceVo> search(FenixSearchParameters searchParameters, int fromIndex, int toIndex);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<ResourceChildModel> getResourceModel(ResourceChildModel model,
			FenixSearchParameters fenixSearchParameters, int fromIndex, int toIndex);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<FenixResourceVo> getResourceChildren(Long resourceId, String resourceType);

	@Secured( { "ROLE_USER" })
	public List<ResourceChildModel> createResourceModel(List<FenixResourceVo> resources, boolean isParent);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<ResourceChildModel> getResourceModel2(ResourceChildModel model, List<FenixResourceVo> resources);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CommunicationResourceVo> searchCommunication(FenixSearchParameters fenixSearchParameters,
			int fromIndex, int toIndex);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<ResourceChildModel> getResourceModelCommunication2(ResourceChildModel parent,
			List<CommunicationResourceVo> resources);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public Integer getCommunicationResourceSize(FenixSearchParameters fenixSearchParameters);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String getApplicationName();

	@Secured( { "ROLE_ADMIN" })
	public List<String> getLastSavedDataset();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CodeVo> getCategories();
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public CodeVo getCategoryLabel(String categoryCode);
	
	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> getImagesByFolder(String folder);
	
	@Secured( { "ROLE_USER" })
	public List<ResourceParentModel> checkConnectedResources(long id, String resourceType);
	
	@Secured( { "ROLE_USER" })
	public Boolean associateDatesetToChart(Long chartId, Long datasetId);
	
	@Secured( { "ROLE_USER" })
	public void changeLayerStyle(Long layerId, String styleName);
	
	public String getIMGTag(Long imageID) throws FenixGWTException;
	
	public List<FenixMenuItemVo> getTools(String xmlFileName, String category) throws FenixGWTException;
	
	public String getIntroText() throws FenixGWTException;
	
	public Map<String, String> getNodeSettings() throws FenixGWTException;
	
}