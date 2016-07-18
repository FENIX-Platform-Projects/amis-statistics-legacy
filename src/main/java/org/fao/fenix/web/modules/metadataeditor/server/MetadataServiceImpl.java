/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.metadataeditor.server;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.Resource;
import org.fao.fenix.core.domain.Source;
import org.fao.fenix.core.domain.coding.CodingSystem;
import org.fao.fenix.core.domain.comparison.StringComparator;
import org.fao.fenix.core.domain.constants.CodingType;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.constants.DatasetTemplate;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.DatasetType;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.domain.dataset.RangeDefinition;
import org.fao.fenix.core.domain.map.layer.DBFeatureLayer;
import org.fao.fenix.core.domain.perspective.ChartView;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.domain.perspective.ReportView;
import org.fao.fenix.core.domain.perspective.TableView;
import org.fao.fenix.core.domain.perspective.TextView;
import org.fao.fenix.core.domain.project.Project;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.MEDao;
import org.fao.fenix.core.persistence.MetadataDao;
import org.fao.fenix.core.persistence.file.FileFactory;
import org.fao.fenix.core.utils.BackupUtils;
import org.fao.fenix.core.utils.FieldMetadata;
import org.fao.fenix.core.utils.ResourceUtils;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.metadataeditor.common.services.MetadataService;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DatasetTypeVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.FieldMetadataVo;
import org.fao.fenix.web.modules.metadataeditor.common.vo.MetadataVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.OptionVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.ResourceVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.DataTypeModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.FeatureCodeSetModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class MetadataServiceImpl extends RemoteServiceServlet implements MetadataService {

	MetadataDao metadataDao;
	
	MEDao meDao;
	
	FileFactory fileFactory;
	
	CodecDao codecDao;
	
	BackupUtils backupUtils;
	
	public List<String> findAllCodingSystemNames() {
		return codecDao.findAllCodingSystems();
	}
	
	public List<String> findAllCodingSystemTypes() {
		return meDao.findAllCodingSystemTypes();
	}
	
	public void backup() throws FenixGWTException {
		try {
			backupUtils.backup();
		} catch(FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public void restore() throws FenixGWTException {
		backupUtils.restore();
	}
	
	public List<FieldMetadataVo> findAllResourceSpecificFields(String resourceType) {
		List<FieldMetadataVo> vos = new ArrayList<FieldMetadataVo>();
		List<FieldMetadata> fields = ResourceUtils.getEditableFields(getResourceClass(resourceType));
		for (FieldMetadata f : fields)
			vos.add(fieldMetadata2Vo(f));
		return vos;
	}
	
	private FieldMetadataVo fieldMetadata2Vo(FieldMetadata f) {
		FieldMetadataVo vo = new FieldMetadataVo();
		vo.setName(f.name);
		vo.setBaseField(f.isBaseField);
		vo.setMandatory(f.isMandatory);
		vo.setReadonly(f.isReadonly);
		return vo;
	}
	
	public List<FeatureCodeSetModelData> findAllGeographicCodingSystems() {
		List<FeatureCodeSetModelData> vos = new ArrayList<FeatureCodeSetModelData>();
		List<CodingSystem> sys = meDao.findAllGeographicCodingSystems();
		for (CodingSystem cs : sys) {
			FeatureCodeSetModelData m = new FeatureCodeSetModelData();
			m.setName(cs.getTitle());
			m.setCodingType(CodingType.Geographic.name());
			m.setRegion(cs.getRegion());
			vos.add(m);
		}
		return vos;
	}
	
	public List<ResourceVO> findMetadataByGAULCode(String gaulCode) throws FenixGWTException {
//		List<Resource> rsrcs = meDao.findResourceByRegion(gaulCode);
		List<Resource> rsrcs = meDao.findHaitiResourceByGAULCode(gaulCode);
		List<ResourceVO> vos = new ArrayList<ResourceVO>();
		for (Resource rsrc : rsrcs) {
			ResourceVO vo = buildResourceVO(rsrc); 
			HaitiResourceType type = HaitiResourceType.valueOf(vo.getResourceType()); 
			if (type != null)
				vos.add(vo);
		}
		return vos;
	}
	
	public ResourceVO buildDBFeatureLayerResourceVO(Long dbFeatureLayerID) throws FenixGWTException {
		DBFeatureLayer l = meDao.findDBFeatureLayer(dbFeatureLayerID);
		return buildResourceVO(l);
	}
	
	private ResourceVO buildResourceVO(Resource rsrc) throws FenixGWTException {
		ResourceVO rvo = rsrc2vo(rsrc);
		rvo.setSourceContact(meDao.findSourceContact(rsrc.getResourceId(), "source"));
		rvo.setProviderContact(meDao.findSourceContact(rsrc.getResourceId(), "provider"));
		String resourceType = meDao.getResourceTypeById(rsrc.getResourceId()); 
		rvo.setResourceType(resourceType);
		rvo.setImageLink(getImageLink(resourceType));
		return rvo;
	}
	
	private String getImageLink(String resourceType) {
		String imageLink = "";
		org.fao.fenix.core.domain.constants.ResourceType type = org.fao.fenix.core.domain.constants.ResourceType.valueOf(resourceType);
		switch (type) {
			case Dataset : imageLink = "toolBox-images/tableToolbox.gif"; break;
			case TextView : imageLink = "toolBox-images/textToolbox.gif"; break;
			case ChartView : imageLink = "toolBox-images/chartToolbox.gif"; break;
			default : imageLink = "";
		}
		return imageLink;
	}
	
	private MetadataVO buildMetadataVO(Resource rsrc) throws FenixGWTException {
		
		MetadataVO vo = new MetadataVO();
		vo.setResourceType(meDao.getResourceTypeById(rsrc.getResourceId()));
		ResourceVO rvo = rsrc2vo(rsrc);
		rvo.setSourceContact(meDao.findSourceContact(rsrc.getResourceId(), "source"));
		rvo.setProviderContact(meDao.findSourceContact(rsrc.getResourceId(), "provider"));
		vo.setResourceVo(rvo);
		
		DatasetTypeVO tvo = new DatasetTypeVO();
		if (vo.getResourceType().equals(ResourceType.DATASET)) {
			Dataset dataset = (Dataset)rsrc;
			tvo.setTitle(dataset.getDatasetType().getTitle());
			rvo.setPeriodTypeCode(dataset.getPeriodTypeCode());
		}
		vo.setDatasetTypeVo(tvo);
		
		GaulModelData gaulModelData = new GaulModelData();
		gaulModelData.setGaulCode(rvo.getRegion());
		gaulModelData.setGaulLabel(codecDao.getLabelFromCodeCodingSystem(rvo.getRegion(), "GAUL0", "0", "EN"));
		vo.setGaulModelData(gaulModelData);
		
		GaulModelData sourceGaulModelData = new GaulModelData();
		sourceGaulModelData.setGaulCode(rvo.getSourceRegion());
		sourceGaulModelData.setGaulLabel(codecDao.getLabelFromCodeCodingSystem(rvo.getSourceRegion(), "GAUL0", "0", "EN"));
		vo.setSourceGaulModelData(sourceGaulModelData);
		
		GaulModelData providerGaulModelData = new GaulModelData();
		providerGaulModelData.setGaulCode(rvo.getProviderRegion());
		providerGaulModelData.setGaulLabel(codecDao.getLabelFromCodeCodingSystem(rvo.getProviderRegion(), "GAUL0", "0", "EN"));
		vo.setProviderGaulModelData(providerGaulModelData);
		
		FeatureCodeSetModelData featureCodeSetModelData = new FeatureCodeSetModelData();
		featureCodeSetModelData.setCodingType(rvo.getFeatureCodeSet());
		vo.setFeatureCodeSetModelData(featureCodeSetModelData);
		
		CategoryModelData categoryModelData = new CategoryModelData();
		categoryModelData.setCategoryValue(rvo.getCategories());
		categoryModelData.setCategoryName(codecDao.getLabelFromCodeCodingSystem(rvo.getCategories(), "Categories", "0", "EN"));
		vo.setCategoryModelData(categoryModelData);

		// Fill in fields metadata
		List<FieldMetadata> metadataFields = ResourceUtils.getEditableFields(rsrc.getClass());
		FieldMetadataVo fmvos[] = new FieldMetadataVo[metadataFields.size()];
		int idx=0;
		for (FieldMetadata metadataField : metadataFields)
			fmvos[idx++] = fieldMetadata2Vo(metadataField);
		vo.setFieldMetadataVos(fmvos);
		
		// Fill resource specific values
		try {
			vo.setResourceSpecificFieldMap(createResourceSpecificFields(rsrc, fmvos));
		} catch (FenixException e) {
			throw new FenixGWTException(e.getMessage());
		}

		return vo;
	}
	
	public MetadataVO findMetadataById(Long resourceId) throws FenixGWTException {
		Resource rsrc = meDao.findResourceById(resourceId);
		Logger.getRootLogger().warn("rsrc for ID " + resourceId + " is null? " + (rsrc == null));
		MetadataVO vo = buildMetadataVO(rsrc);
		return vo;
	}
	
	private Map<FieldMetadataVo, String> createResourceSpecificFields(Resource rsrc, FieldMetadataVo fmvos[]) throws FenixException {
		try {
			Map<FieldMetadataVo, String> map = new HashMap<FieldMetadataVo, String>();
			for (FieldMetadataVo vo : fmvos) {
				if (!vo.isBaseField)
					map.put(vo, BeanUtils.getProperty(rsrc, vo.getName()));
			}
			return map;
		} catch (IllegalAccessException e) {
			throw new FenixException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new FenixException(e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new FenixException(e.getMessage());
		}
	}
	
	public String createMetadataFile(ResourceVO rvo, DatasetTypeVO dvo) {
		Resource resource = vo2rsrc(rvo);
		DatasetType type = vo2type(dvo);
		return fileFactory.createDatasetZipFile(resource, type, rvo.getPeriodTypeCode());
	}
	
	private DatasetType vo2type(DatasetTypeVO vo) {
		
		DatasetType t = new DatasetType();
		t.setId(vo.getId());
		t.setTitle(vo.getTitle());
		t.setDescriptors(new ArrayList<Descriptor>());
		
		for (DescriptorVO dvo : vo.getDescriptors()) {
			
			Descriptor d = new Descriptor();
			d.setHeader(dvo.getHeader());
			DataType dataType = DataType.valueOfIgnoreCase(dvo.getContentDescriptor());
			d.setDataType(dataType);
			d.setKey(dvo.isKey());
			
			d.setOptions(new ArrayList<Option>());
			for (OptionVO o : dvo.getOptions())
				d.addOption(new Option(o.getOptionName(), o.getOptionValue(), o.getOptionRegion()));
			
			d.setRangeDefinitions(new ArrayList<RangeDefinition>());
			t.addDescriptor(d);
		}
		
		return t;
	}
	
	// TODO Set Contact both for Source and Provider
	private Resource vo2rsrc(ResourceVO vo) {
		Resource r = new Resource();
		r.setAbstractAbstract(vo.getAbstractAbstract());
		r.setCategories(vo.getCategories());
		r.setCode(vo.getCode());
		r.setDateLastUpdate(vo.getDateLastUpdate());
		r.setEndDate(vo.getEndDate());
		r.setKeywords(vo.getKeywords());
		r.setProvider(new Source(vo.getProvider(), vo.getProviderRegion(), vo.getProviderContact()));
		r.setRegion(vo.getRegion());
		r.setResourceId(vo.getResourceId());
		r.setSharingCode(vo.getSharingCode());
		r.setSource(new Source(vo.getSource(), vo.getSourceRegion(), vo.getSourceContact()));
		r.setStartDate(vo.getStartDate());
		r.setTitle(vo.getTitle());
		return r;
	}
	
	// TODO Set Contact both for Source and Provider
	private ResourceVO rsrc2vo(Resource r) {
		ResourceVO vo = new ResourceVO();
		vo.setAbstractAbstract(r.getAbstractAbstract());
		vo.setCategories(r.getCategories());
		vo.setCode(r.getCode());
		vo.setDateLastUpdate(r.getDateLastUpdate());
		vo.setEndDate(r.getEndDate());
		vo.setKeywords(r.getKeywords());
		vo.setRegion(r.getRegion());
		vo.setResourceId(r.getResourceId());
		vo.setSharingCode(r.getSharingCode());
		if (r.getProvider() != null) {
			vo.setProvider(r.getProvider().getTitle());
			vo.setProviderRegion(r.getProvider().getCountry());
			vo.setProviderContact(r.getProvider().getContact());
		}
		if (r.getSource() != null) {
			vo.setSource(r.getSource().getTitle());
			vo.setSourceRegion(r.getSource().getCountry());
			vo.setSourceContact(r.getSource().getContact());
		}
		vo.setStartDate(r.getStartDate());
		vo.setTitle(r.getTitle());
		return vo;
	}
	
	public List<DataTypeModelData> findAllDataType() {
		List<DataTypeModelData> dataTypes = new ArrayList<DataTypeModelData>();
		for (DataType dataType : DataType.values())
			dataTypes.add(new DataTypeModelData(dataType.name()));
		return dataTypes;
	}
	
	public List<DescriptorVO> getTemplateDatasetDescriptors(String templateDatasetName) throws FenixGWTException {
		List<DescriptorVO> vos = new ArrayList<DescriptorVO>();
		List<Descriptor> descriptors = meDao.getDescriptorsFromTemplate(templateDatasetName);
		Map<String, Descriptor> headersMap = new HashMap<String, Descriptor>();
		for (Descriptor d : descriptors)
			headersMap.put(d.getHeader(), d);
		for (Descriptor d : headersMap.values())
			vos.add(descriptor2vo(d));
		return vos;
	}
	
	@SuppressWarnings("deprecation")
	private DescriptorVO descriptor2vo(Descriptor d) {
		DescriptorVO vo = new DescriptorVO();
		vo.setContentDescriptor(d.getContentDescriptor());
		vo.setHeader(d.getHeader());
		vo.setKey(d.isKey());
		vo.setOptions(new ArrayList<OptionVO>());
		for (Option option : d.getOptions()) {
			OptionVO ovo = new OptionVO();
			ovo.setOptionName(option.getOptionName());
			ovo.setOptionRegion(option.getOptionRegion());
			ovo.setOptionValue(option.getOptionValue());
			vo.addOption(ovo);
		}
		return vo;
	}
	
	/**
	 * Create a collection of DatasetType, starting from the Templates stored in
	 * the DatasetTemplate enum, the adding all the DatasetType already stored
	 * in the DB;
	 */
	public List<String> findAllDatasetType() {
		List<String> dsTypes = new ArrayList<String>();
		for (DatasetTemplate dsType : DatasetTemplate.values())
			dsTypes.add(dsType.name());
		List<String> dbTypes = metadataDao.findAllDatasetType();
		for (String dbType : dbTypes)
			if (!dsTypes.contains(dbType))
				dsTypes.add(dbType);
		Collections.sort(dsTypes, new StringComparator());
		return dsTypes;
	}

	public Map<String, String> getMetadataNameValueMap(Long resourceId) {
		Map<String, String> map = metadataDao.getMetadataNameValueMap(resourceId);
		return map;
	}

	public List<String> getOrderedMetadataFieldList() {
		List<String> fieldList = metadataDao.getFieldOrder();
		return fieldList;
	}

	public List<String> getMetadataFields(Long resourceId) {

		List<String> metadataFields = metadataDao.getMetadataFields(resourceId);
		return metadataFields;
	}

	public Map<String, String> getMetadataFieldsMap(Long resourceId) {

		List<String> metadataFields = metadataDao.getMetadataFields(resourceId);
		Map<String, String> metadataMap = new LinkedHashMap<String, String>();

		for (int i = 0; i < metadataFields.size(); i++) {
			String fieldName = metadataFields.get(i);
			metadataMap.put(fieldName, "");
		}

		return metadataMap;
	}

	/**
	 * Maps a string indicating a {@see ResourceType} into the corresponding
	 * Class. If no corresponding class if found, {@see Resource}.class will be
	 * returned.
	 * 
	 * @param resourceType
	 *            A String as defined in {@see ResourceType}
	 * @return
	 */
	public static Class<? extends Resource> getResourceClass(String resourceType) {

		Class<? extends Resource> ret;

		if (resourceType.equals(ResourceType.CHARTVIEW)) {
			ret = ChartView.class;
		} else if (resourceType.equals(ResourceType.REPORT)) {
			ret = ReportView.class;
		} else if (resourceType.equals(ResourceType.TABLEVIEW)) {
			ret = TableView.class;
		} else if (resourceType.equals(ResourceType.PROJECT)) {
			ret = Project.class;
		} else if (resourceType.equals(ResourceType.TEXTVIEW)) {
			ret = TextView.class;
		} else if (resourceType.equals(ResourceType.MAPVIEW)) {
			ret = MapView.class;
		} else {
			System.out.println("Unknown resource type = " + resourceType);
			ret = Resource.class;
		}

		return ret;
	}

	public Map<String, String> getResourceMetadataFieldsMap(String resourceType) {

		Class<? extends Resource> resClass = getResourceClass(resourceType);
		List<FieldMetadata> metadataFields = ResourceUtils.getEditableFields(resClass);
		
		Map<String, String> ret = new LinkedHashMap<String, String>();
		for (FieldMetadata field : metadataFields) {
			ret.put(field.name, "");
		}

		return ret;
	}

	public List<String> getMetadataFields(String resourceType) {

		Class<? extends Resource> resClass = getResourceClass(resourceType);

		List<FieldMetadata> metadataFields = ResourceUtils.getEditableFields(resClass);
		
		List<String> ret = new ArrayList<String>(metadataFields.size());
		for (FieldMetadata field : metadataFields) {
			ret.add(field.name);
		}

		return ret;		
	}

	public void rename(Long resourceId, String newName) {
		metadataDao.renameResource(resourceId, newName);
	}

	public void updateMetadata(Map<String, String> metadata, Long resourceId) {
		metadataDao.updateMetadata(metadata, resourceId);
	}

	public void setMetadataDao(MetadataDao metadataDao) {
		this.metadataDao = metadataDao;
	}

	public void setMeDao(MEDao meDao) {
		this.meDao = meDao;
	}

	public void setFileFactory(FileFactory fileFactory) {
		this.fileFactory = fileFactory;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setBackupUtils(BackupUtils backupUtils) {
		this.backupUtils = backupUtils;
	}
	
	enum HaitiResourceType {
		Dataset, TextView, ChartView;
	}

}
