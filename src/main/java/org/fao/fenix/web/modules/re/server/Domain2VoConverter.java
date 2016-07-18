package org.fao.fenix.web.modules.re.server;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.core.domain.Resource;
import org.fao.fenix.core.domain.chartdesigner.ChartDesign;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Dataset.Type;
import org.fao.fenix.core.domain.map.WMSMapProvider;
import org.fao.fenix.core.persistence.MEDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.core.persistence.security.ResourcePermission;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.springframework.util.Assert;
import org.springmodules.cache.annotations.Cacheable;

public class Domain2VoConverter {

	private FenixPermissionManager fenixPermissionManager;

	boolean enrichWithPermissions = true;

	private MEDao meDao;

	@Cacheable(modelId = "fenixCaching")
	public List<FenixResourceVo> convertDomain2Vo(List<Resource> domainResourceList, String userName, List<String> resourceTypeList) {
		List<ResourcePermission> plist = null;
		if (enrichWithPermissions) {
			plist = fenixPermissionManager.derivePermissionList(domainResourceList);
			Assert.isTrue(plist.size() == domainResourceList.size(), "Lists should have the same size");
		}
		List<FenixResourceVo> list = new ArrayList<FenixResourceVo>();
		int i = 0;
		for (Resource domainResource : domainResourceList) {
			FenixResourceVo resource = new FenixResourceVo();
			if (enrichWithPermissions) {
				ResourcePermission rp = plist.get(i++);
				Assert.isTrue(rp.isHasReadPermission(), "Should have always read permission");
				resource.setHasDeletePermission(rp.isHasDeletePermission());
				resource.setHasDownloadPermission(rp.isHasDownloadPermission());
				resource.setHasWritePermission(rp.isHasWritePermission());
			}
			resource.setId(String.valueOf(domainResource.getResourceId()));
			resource.setTitle((domainResource).getTitle());
			if (domainResource.getSource() != null && domainResource.getSource().getTitle() != null)
				resource.setSource(domainResource.getSource().getTitle());
			else
				resource.setSource("n.a.");
			if (domainResource.getDateLastUpdate() != null)
				resource.setLastUpdated(domainResource.getDateLastUpdate().toString());
			else
				resource.setLastUpdated("");
			resource.setRegion(domainResource.getRegion());
			if (domainResource.getCategories() != null)
				resource.setCategory(domainResource.getCategories());
			else
				resource.setCategory("");
			if (domainResource.getClass().getSimpleName().contains("Dataset")) {
				resource.setType(ResourceType.DATASET);
				Dataset dataset = (Dataset) domainResource;
				if (dataset.getType() == Type.Flex) {
					resource.setIsDatasetFlex(true);
					resource.setType(ResourceType.CUSTOM_DATASET);
				} 
//				else if (dataset.getType() == Type.Core) {
//					resource.setIsDatasetFlex(false);
//					resource.setType(ResourceType.CORE_DATASET);
//				} else if (dataset.getType() == Type.Custom) {
//					resource.setIsDatasetFlex(true);
//					resource.setType(ResourceType.CUSTOM_DATASET);
//				}
			} else if (domainResource instanceof WMSMapProvider) {
				resource.setType(ResourceType.LAYER);
			} else if (domainResource instanceof ChartDesign) {
				resource.setType(ResourceType.CHARTVIEW);
			} else {
				resource.setType(domainResource.getClass().getSimpleName());
			}
			
			if (resourceTypeList != null) {
//				for (String rsrcType : resourceTypeList)
//					System.out.println("Domain2VOconverter: " + rsrcType);
				if (resourceTypeList.contains("TableView"))
					setLocalID(resource, domainResource);
			}
			
			list.add(resource);
		}
		return list;
	}

	public void setLocalID(FenixResourceVo vo, Resource domainResource) {
		Long datasetID = meDao.findAssociatedDatasetID(domainResource.getResourceId());
//		System.out.println("Domain2VOconverter: " + domainResource.getResourceId() + " is associated with dataset: " + datasetID);
		vo.setLocalID(String.valueOf(datasetID));
	}

	public List<FenixResourceVo> convertDomain2VoWihtoutPermissions(List<Resource> domainResourceList, String userName, List<String> resourceTypeList) {
		enrichWithPermissions = false;
		return this.convertDomain2Vo(domainResourceList, userName, resourceTypeList);
	}

	public void setFenixPermissionManager(FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}

	public void setMeDao(MEDao meDao) {
		this.meDao = meDao;
	}

}
