package org.fao.fenix.web.modules.re.common.vo;

import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * The ResourceChildModel class is a representation of a FenixResourceVo object
 * as displayed in the Catalogue
 * 
 **/

public class ResourceChildModel extends BaseTreeModel implements IsSerializable {

	private static final long serialVersionUID = 2885802321308400582L;
	
	private FenixResourceVo fenixResourceVo; 



	public ResourceChildModel() {
	}

	public ResourceChildModel(String name) {
		setName(name);
	}

	public void setName(String name) {
		set("name", name);
	}

	public String getName() {
		return get("name");
	}
	
	public void setSource(String source) {
		set("source", source);
	}

	public String getSource() {
		return get("source");
	}
	
	public void setRegion(String region) {
		set("region", region);
	}

	public String getRegion() {
		return get("region");
	}
	
	public void setCategoryLabel(String categoryLabel) {
		set("categoryLabel", categoryLabel);
	}

	public String getCategoryLabel() {
		return get("categoryLabel");
	}
	
	public void setRegionCode(String regionCode) {
		set("regionCode", regionCode);
	}

	public String getRegionCode() {
		return get("regionCode");
	}
	
	public void setPeriodTypeCode(String periodTypeCode) {
		set("periodTypeCode", periodTypeCode);
	}

	public String getPeriodTypeCode() {
		return get("periodTypeCode");
	}
	
	public void setDateModified(String dateModified) {
		set("dateModified", dateModified);
	}

	public String getDateModified() {
		return get("dateModified");
	}

	public void setId(String id) {
		set("id", id);
	}

	public String getId() {
		return get("id");
	}

	public void setType(String id) {
		set("type", id);
	}

	public String getType() {
		return get("type");
	}
	
	public void setLocalId(String localId) {
		set("localId", localId);
	}
	
	public String getLocalId() {
		return get("localId");
	}
	
	public void setDigest(String digest) {
		set("digest", digest);
	}
	
	public String getDigest() {
		return get("digest");
	}
	
	public void setHost(String host) {
		set("host", host);
	}
	
	public String getHost() {
		return get("host");
	}
	
	public void setHostLabel(String hostLabel) {
		set("hostLabel", hostLabel);
	}
	
	public String getHostLabel() {
		return get("hostLabel");
	}
	
	public void setResourceIsModifiable(String isResourceModifiable) {
		set("isModifiable", isResourceModifiable);
	}
	
	public String getResourceIsModifiable() {
		return get("isModifiable");
	}
	
	public void setReadPermission(Boolean hasReadPermission) {
		set("readPermission", hasReadPermission);
	}
	
	public void setDeletePermission(Boolean hasDeletePermission) {
		set("deletePermission", hasDeletePermission);
	}
	
	public void setWritePermission(Boolean hasWritePermission) {
		set("writePermission", hasWritePermission);
	}
	
	public void setDownloadPermission(Boolean hasDownloadPermission) {
		set("downloadPermission", hasDownloadPermission);
	}
	
	public Boolean hasReadPermission() {
		return get("readPermission");
	}
	
	public Boolean hasWritePermission() {
		return get("writePermission");
	}
	
	public Boolean hasDeletePermission() {
		return get("deletePermission");
	}
	
	public Boolean hasDownloadPermission() {
		return get("downloadPermission");
	}

	public Boolean isFlexDatasetType() {
		return get("flexDataset");
	}
	
	public void setFlexDatasetType(Boolean isFlexDatasetType) {
		set("flexDataset", isFlexDatasetType);
	}
	
	public FenixResourceVo getFenixResourceVo() {
		return fenixResourceVo;
	}

	public void setFenixResourceVo(FenixResourceVo fenixResourceVo) {
		this.fenixResourceVo = fenixResourceVo;
	}	
	
	public String toString() {
		return "Title: " + this.getName() + ", Source: " + this.getSource() + ", " + this.getCategoryLabel() + ", " + this.getPeriodTypeCode();
	}
}
