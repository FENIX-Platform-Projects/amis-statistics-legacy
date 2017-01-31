package org.fao.fenix.web.modules.ofcchart.common.vo;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DatasetVO implements IsSerializable {

	private String dsId;
	
	private String datasetName;
	
	private String sourceName;
	
	private String sourceRegion;
	
	private String sourceContact;
	
	private List<DescriptorViewVO> descriptorViews;
	
	private List<ResourceViewSettingVO> settings;
	
	public void addDescriptorViewVO(DescriptorViewVO vo) {
		if (this.descriptorViews == null)
			this.descriptorViews = new ArrayList<DescriptorViewVO>();
		this.descriptorViews.add(vo);
	}
	
	public void addResourceViewSettingVO(ResourceViewSettingVO vo) {
		if (this.settings == null)
			this.settings = new ArrayList<ResourceViewSettingVO>();
		this.settings.add(vo);
	}
	
	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceRegion() {
		return sourceRegion;
	}

	public void setSourceRegion(String sourceRegion) {
		this.sourceRegion = sourceRegion;
	}

	public String getSourceContact() {
		return sourceContact;
	}

	public void setSourceContact(String sourceContact) {
		this.sourceContact = sourceContact;
	}

	public List<ResourceViewSettingVO> getSettings() {
		return settings;
	}

	public void setSettings(List<ResourceViewSettingVO> settings) {
		this.settings = settings;
	}

	public List<DescriptorViewVO> getDescriptorViews() {
		return descriptorViews;
	}

	public void setDescriptorViews(List<DescriptorViewVO> descriptorViews) {
		this.descriptorViews = descriptorViews;
	}

	public String getDsId() {
		return dsId;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}

	public String getDatasetName() {
		return datasetName;
	}

	public void setDatasetName(String datasetName) {
		this.datasetName = datasetName;
	}

}