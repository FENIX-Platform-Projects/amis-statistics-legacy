package org.fao.fenix.web.modules.pm.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.core.domain.Resource;
import org.fao.fenix.core.domain.constants.ResourceType;
import org.fao.fenix.core.domain.project.Project;
import org.fao.fenix.core.domain.project.ProjectObject;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.perspective.ProjectDao;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.server.utils.FenixResourceBuilder;
import org.fao.fenix.web.modules.pm.common.services.ProjectManagerService;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.re.common.vo.ResourceParentModel;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProjectManagerServiceImpl extends RemoteServiceServlet implements ProjectManagerService {

	private static final long serialVersionUID = -2501142149540556558L;
	CodecDao codecDao; 

	// DomainVoMapper mapper = new DomainVoMapper();
	ProjectDao projectDao;
	DatasetDao datasetDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public List<FenixResourceVo> getProjectList() {
		List<Project> projectList = projectDao.findAll();
		List<Resource> resources = new ArrayList<Resource>();

		for (Project project : projectList) {
			Resource rsrc = (Resource) project;
			resources.add(rsrc);
		}

		/*
		 * List<FenixResourceMetadataVo> list = new ArrayList<FenixResourceMetadataVo>();
		 * mapper.convertResourceDo2FenixResourceVo((List<Resource>)projectList); for (Project
		 * project : projectList) { list.add(mapper.do2vo(project)); }
		 */

		return convertResourceDo2FenixResourceVo((resources));
	}

	public void addResourceTo(Long projectId, Long resourceId) {
		Project project = projectDao.findById(projectId);
		ProjectObject projectObject = projectDao.findProjectObjectById(resourceId);
		project.addProjectObject(projectObject);
		projectDao.update(project);
	}

	/*
	 * public Long create(FenixResourceMetadataVo project) { Project domainObject = new Project();
	 * domainObject.setAbstractAbstract(project.getAbstractAbstract());
	 * domainObject.setContact(project.getContact());
	 * domainObject.setCategories(project.getCategories()); domainObject.setDateLastUpdate(new
	 * Date()); domainObject.setEndDate(mapper.string2Date(project.getEndDate()));
	 * domainObject.setKeywords(project.getKeywords());
	 * domainObject.setPeriodTypeCode(project.getPeriodTypeCode());
	 * domainObject.setProvider(project.getProvider()); domainObject.setRegion(project.getRegion());
	 * domainObject.setSharingCode(project.getSharingCode());
	 * domainObject.setSource(project.getSource());
	 * domainObject.setStartDate(mapper.string2Date(project.getStartDate()));
	 * domainObject.setTitle(project.getTitle()); projectDao.save(domainObject);
	 * 
	 * Long projectId = domainObject.getResourceId();
	 * 
	 * System.out.println("projectId = "+projectId );
	 * 
	 * return projectId; }
	 */

	public Long saveProject(String title) {

		Project project = new Project();
		project.setTitle(title);
		projectDao.save(project);

		return project.getResourceId();
	}

	public Map getNewProjectMetadata(Map metadata) {

		// save new project
		final Long id = saveProject((String) metadata.get("title"));

		// update metadata
		metadata.put("id", id);

		return metadata;

	}

	public FenixResourceVo createProjectFenixResource(Long id) {

		Project project = projectDao.findById(id);
		FenixResourceVo fenixResource = null;

		if (project != null) {
			fenixResource = FenixResourceBuilder.build(projectDao.findById(id));
		}

		return fenixResource;
	}

	public void deleteResourceFrom(Long projectId, Long resourceId) {
		Project project = projectDao.findById(projectId);
		ProjectObject projectObject = projectDao.findProjectObjectById(resourceId);
		project.removeProjectObject(projectObject);
		projectDao.update(project);
	}

	public void deleteProject(Long projectId) {
		Project project = projectDao.findById(projectId);
		projectDao.delete(project);
	}

	public List<FenixResourceVo> getResourceListFrom(Long projectId) {

		List<FenixResourceVo> fenixResourceList = new ArrayList<FenixResourceVo>();
		Project project = projectDao.findById(projectId.longValue());

		List<Resource> resources = new ArrayList<Resource>();
		List<ProjectObject> pol = project.getProjectObjectList();

		for (ProjectObject projectObject : pol) {
			Resource rsrc = (Resource) projectObject;
			resources.add(rsrc);
		}

		return convertResourceDo2FenixResourceVo((resources));

	}

	/*
	 * public List<FenixResourceMetadataVo> getResourceListFrom(Long projectId) { Project project =
	 * projectDao.findById(projectId); List<ProjectObject> projectList =
	 * project.getProjectObjectList(); List<FenixResourceMetadataVo> projectListVo = new
	 * ArrayList<FenixResourceMetadataVo>();
	 * 
	 * for (ProjectObject projectObject : projectList) {
	 * projectListVo.add(mapper.do2vo(projectObject)); } return projectListVo; }
	 */

	public List<FenixResourceVo> convertResourceDo2FenixResourceVo(List<Resource> domainResourceList) {

		List<FenixResourceVo> list = new ArrayList<FenixResourceVo>();

		// conversion to FenixResource
		for (int i = 0; i < domainResourceList.size(); i++) {
			FenixResourceVo resource = new FenixResourceVo();
			Resource domainResource = domainResourceList.get(i);
			resource.setId(String.valueOf(domainResource.getResourceId()));
			resource.setTitle((domainResource).getTitle());
			if (domainResource.getDateLastUpdate() != null)
				resource.setLastUpdated(domainResource.getDateLastUpdate().toString());
			else
				resource.setLastUpdated("");
			resource.setRegion(domainResource.getRegion());
			if (domainResource.getCategories() != null)
				resource.setCategory(domainResource.getCategories());
			else
				resource.setCategory("");

			if (domainResource.getClass().getSimpleName().contains("Dataset"))
				resource.setType("Dataset");
			else
				resource.setType(domainResource.getClass().getSimpleName());
			list.add(resource);
		}
		return list;
	}

	public List<ResourceChildModel> getProjectManagerResourceModel(ResourceChildModel parent,
			List<ResourceChildModel> models) {
		List<FenixResourceVo> resources = null;
		List<ResourceChildModel> modelList = new ArrayList<ResourceChildModel>();
		System.out.println("getProjectManagerResourceModel HERE");

		if (parent == null) { // get root resources
			System.out.println("getProjectManagerResourceModel: ROOT ITEMS");
			modelList = models;
		} else { // get child resources for the project
			System.out.println("getProjectManagerResourceModel: CHILD ITEMS");
			// resources = getResourceChildren(Long.valueOf(parent.getId()), parent.getType());

			if (parent.getType().equals(ResourceType.Project)) { // List of resource types for the
																	// project selected
				System.out.println("getProjectManagerResourceModel: CHILD ITEMS: parent is a PROJECT");
				modelList = createResourceModel(resources, true);
			} else {
				System.out.println("getProjectManagerResourceModel: CHILD ITEMS: parent is NOT a project");
				modelList = createResourceModel(resources, false); // List of resources for the
																	// resourceType selected
			}
		}

		if (modelList != null)
			System.out.println("getProjectManagerResourceModel: RETURNED MODELS size = " + modelList.size());
		else
			System.out.println("getProjectManagerResourceModel: RETURNED MODELS = NULL");

		System.out.println("-------------------------- modelList size " + modelList.size());
		return modelList;
	}

	public Map<List<FenixResourceVo>, String> getResources(ResourceChildModel parent) {
		List<FenixResourceVo> resources = null;
		Map<List<FenixResourceVo>, String> map = new HashMap<List<FenixResourceVo>, String>();

		if (parent == null) { // get root resources
			System.out.println("getResources: ROOT ITEMS");
			// modelList = models;
			map.put(resources, "");

		} else { // get child resources for the project
			System.out.println("getResources: CHILD ITEMS");
			// resources = getResourceChildren(Long.valueOf(parent.getId()), parent.getType());

			if (parent.getType().equals(ResourceType.Project)) { // List of resource types for the
																	// project selected
				System.out.println("getResources: CHILD ITEMS: parent is a PROJECT");
				// modelList = createResourceModel(resources, true);
				map.put(resources, "true");

			} else {
				System.out.println("getResources: CHILD ITEMS: parent is NOT a project");
				// modelList = createResourceModel(resources, false); //List of resources for the
				// resourceType selected
				map.put(resources, "false");
			}
		}

		// if(modelList!=null)
		// System.out.println("getProjectManagerResourceModel: RETURNED MODELS size = "+modelList.size());
		// else
		// System.out.println("getProjectManagerResourceModel: RETURNED MODELS = NULL");

		return map;
	}

	public List<FenixResourceVo> getResourceChildren(Long resourceId) {

		System.out.println("PROJECTMANGAER: getResourceChildren -------------- START ");
		List<FenixResourceVo> fenixResourceList = new ArrayList<FenixResourceVo>();
		Project project = projectDao.findById(resourceId.longValue());

		if (project != null) {
			List<ProjectObject> pol = project.getProjectObjectList();

			if (pol != null) {
				System.out.println("PROJECTMANGAER: getResourceChildren -------------- POL SIZE =  " + pol.size());

				List<Resource> resourceList = new ArrayList<Resource>();

				for (ProjectObject projectObject : pol) {
					Resource rsrc = (Resource) projectObject;
					System.out.println("PROJECT MANGER: rsrc = " + rsrc);
					resourceList.add(rsrc);
				}
				fenixResourceList = convertResourceDo2FenixResourceVo(resourceList);
			}
		} else {
			System.out.println("project object list IS NULL");

		}

		Collections.sort(fenixResourceList, new FenixResourceVoComparator());
		return fenixResourceList;
	}

	public List<ResourceChildModel> createResourceModel(List<FenixResourceVo> resources, boolean isParent) {

		List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
		for (FenixResourceVo r : resources) {
			ResourceChildModel reModel = null;
			if (isParent) {
				reModel = new ResourceParentModel(r.getTitle(), r.getType());
			} else {
				reModel = new ResourceChildModel(r.getTitle());
			}
			reModel.set("dateModified", r.getLastUpdated());
			reModel.set("region", r.getRegion());
			reModel.set("category", codecDao.getLabelFromCodeCodingSystem(r.getCategory(), "Categories", "0", "EN"));

			reModel.setId(r.getId()); // set ID
			reModel.setType(r.getType()); // set Resource Type
			reModel.setWritePermission(r.isHasWritePermission());
			reModel.setDeletePermission(r.isHasDeletePermission());
			reModel.setDownloadPermission(r.isHasDownloadPermission());
			reModel.setFlexDatasetType(r.isDatasetFlex());

			models.add(reModel);

		}
		return models;
	}

	public Long createNewResource(String title, List<Long> resourceList) {

		// save new project
		final Long id = saveProject(title);
		if(resourceList!=null)
			addResourcesToNewProject(id, resourceList);

		return id;
	}

	public FenixResourceVo getNewResource(Long id) {

		Project project = projectDao.findById(id);
		FenixResourceVo fenixResource = null;

		if (project != null) {
			fenixResource = FenixResourceBuilder.build(projectDao.findById(id));
		}

		return fenixResource;
	}

	public void addResourcesToNewProject(Long projectId, List<Long> resourceList) {

		if (resourceList != null && resourceList.size() > 0) {
			for (int i = 0; i < resourceList.size(); i++) {
				addResourceTo(projectId, resourceList.get(i));
			}
		}

	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	
}
