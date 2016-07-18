package org.fao.fenix.web.modules.table.server;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.SharingCode;
import org.fao.fenix.core.domain.coding.CodingSystem;
import org.fao.fenix.core.domain.constants.CodingType;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.constants.PeriodTypeCode;
import org.fao.fenix.core.domain.constants.ResourceType;
import org.fao.fenix.core.domain.constants.UploadPolicy;
import org.fao.fenix.core.domain.dataset.CoreContent;
import org.fao.fenix.core.domain.dataset.CoreDataset;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.FlexCell;
import org.fao.fenix.core.domain.dataset.FlexDataset;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.domain.perspective.SelectedField;
import org.fao.fenix.core.domain.perspective.TableView;
import org.fao.fenix.core.domain.resourceview.DescriptorView;
import org.fao.fenix.core.domain.resourceview.ResourceView;
import org.fao.fenix.core.domain.security.FenixPermission;
import org.fao.fenix.core.domain.security.FenixSecurityConstant;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.domain.udtable.UniqueValue;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.olap.OLAPUpdater;
import org.fao.fenix.core.persistence.Appender;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetCoreDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.DatasetFlexDao;
import org.fao.fenix.core.persistence.FindDao;
import org.fao.fenix.core.persistence.MEDao;
import org.fao.fenix.core.persistence.UniqueValuesDao;
import org.fao.fenix.core.persistence.crisis.CrisisDao;
import org.fao.fenix.core.persistence.olap.OLAPJDBC;
import org.fao.fenix.core.persistence.perspective.TableDao;
import org.fao.fenix.core.persistence.resourceview.ResourceViewDao;
import org.fao.fenix.core.persistence.security.FenixPermissionManager;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.birt.server.utils.chart.ChartUpdater;
import org.fao.fenix.web.modules.chartdesigner.server.ChartDesignUpdater;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.server.utils.EmptyDatumLabel;
import org.fao.fenix.web.modules.core.server.utils.FenixResourceBuilder;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.DimensionsBeanVO;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;
import org.fao.fenix.web.modules.table.common.model.DatasetRowModel;
import org.fao.fenix.web.modules.table.common.services.TableService;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableFilterVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;
import org.fao.fenix.web.modules.table.server.utils.CoreDatasetUtils;
import org.fao.fenix.web.modules.table.server.utils.FlexDatasetUtils;
import org.fao.fenix.web.modules.table.server.utils.TableDataCache;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TableServiceImpl extends RemoteServiceServlet implements TableService {

	private static final long serialVersionUID = -9143672898353646421L;

	private static final Logger LOGGER = Logger.getLogger(TableServiceImpl.class);

	private TableDao tableDao;

	private DatasetDao datasetDao;

	private CodecDao codecDao;

	private GwtConnector gwtConnector;

	private MEDao meDao;

	private DatasetCoreDao datasetCoreDao;

	private DatasetFlexDao datasetFlexDao;

	private Appender appender;

	private CrisisDao crisisDao;

	private UniqueValuesDao uniqueValuesDao;

	private TableExcel tableExcel;

	private ChartUpdater chartUpdater;

	private FenixPermissionManager fenixPermissionManager;
	
	private ResourceViewDao resourceViewDao;
	
	private OLAPJDBC olapJdbc;
	
	private OLAPUpdater olapUpdater;
	
	private ChartDesignUpdater chartDesignUpdater;
	
	private FindDao findDao;
	
	private Map<Long, List<DatasetRowModel>> tableMap = TableDataCache.getTableCache();
	
	public Map<String, List<String>> loadFilterCriteria(Long resourceViewID) {
		Map<String, List<String>> filterCriteria = new HashMap<String, List<String>>();
		ResourceView rv = resourceViewDao.find(resourceViewID);
		for (DescriptorView dv : rv.getDescriptorViews()) {
			String dataType = dv.getContentDescriptor();
			List<String> values = new ArrayList<String>();
			if (dataType.contains("date")) {
				List<UniqueDateValues> udvs = resourceViewDao.findDateValues(dv);
				for (UniqueDateValues udv : udvs)
					values.add(FieldParser.parseDate(udv.getValue()));
			} else {
				List<UniqueTextValues> utvs = resourceViewDao.findTextValues(dv);
				for (UniqueTextValues utv : utvs)
					values.add(utv.getValue());
			}
			filterCriteria.put(dataType, values);
		}
		return filterCriteria;
	}
	
	@SuppressWarnings("deprecation")
	private List<UniqueDateValues> findDescriptorViewUniqueDateValues(DescriptorView d) {
		List<UniqueDateValues> udvs = new ArrayList<UniqueDateValues>();
		olapJdbc.openConnection();
		List<Long> ids = olapJdbc.findUniqueDateValue(d.getHeader(), d.getContentDescriptor());
		olapJdbc.closeConnection();
		udvs = resourceViewDao.findUniqueDateValues(ids, "EN");
		return udvs;
	}
	
	@SuppressWarnings("deprecation")
	private List<UniqueTextValues> findDescriptorViewUniqueTextValues(DescriptorView d) {
		List<UniqueTextValues> udvs = new ArrayList<UniqueTextValues>();
		olapJdbc.openConnection();
		List<Long> ids = olapJdbc.findUniqueTextValue(d.getHeader(), d.getContentDescriptor());
		olapJdbc.closeConnection();
		udvs = resourceViewDao.findUniqueTextValues(ids, "EN");
		return udvs;
	}
	
	public Long saveTableView(Long tableViewID, ResourceViewVO rvo, boolean saveAs) throws FenixGWTException {
		if (saveAs) {
			return saveTableViewAs(tableViewID, rvo);
		} else {
			ResourceView rv = vo2resourceView(rvo);
			if (tableViewID == null) {
				LOGGER.info("Saving a new TableView... START");
				tableViewID = resourceViewDao.saveTableView(rv);
				fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.READ, tableViewID, true);
				fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.WRITE, tableViewID, true);
				fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DELETE, tableViewID, true);
				fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DOWNLOAD, tableViewID, true);
				LOGGER.info("Saving a new TableView... DONE");
			} else {
				LOGGER.info("Updating Existing TableView... START");
				return updateTableView(tableViewID, rv);
			}
		}
		return tableViewID;
	}
	
	private Long saveTableViewAs(Long tableViewID, ResourceViewVO rvo) throws FenixGWTException {
		
		LOGGER.info("Saving TableView As... START");
		
//		if (rvo.getDatasets() != null)
			rvo.getDatasets().removeAll(rvo.getDatasets());
//		if (rvo.getDescriptorViews() != null)
//			rvo.getDescriptorViews().removeAll(rvo.getDescriptorViews());
		
		Long datasetID = resourceViewDao.findTableViewDatasets(tableViewID);
		DatasetVO datasetVO = new DatasetVO();
		datasetVO.setDsId(String.valueOf(datasetID));
					
		List<DescriptorView> dviews = resourceViewDao.findDescriptorViews(tableViewID);
		for (DescriptorView dview : dviews) {
			DescriptorViewVO vo = new DescriptorViewVO();
			vo.setContentDescriptor(dview.getContentDescriptor());
			vo.setHeader(dview.getHeader());
			vo.addDataset(datasetVO);
			if (dview.getContentDescriptor().contains("date")) {
				List<UniqueDateValues> values = resourceViewDao.findDateValues(dview);
				for (UniqueDateValues ud : values) {
					UniqueValueVO uvvo = new UniqueValueVO();
					uvvo.setDatatype(dview.getContentDescriptor());
					uvvo.setDs_id(datasetID);
					uvvo.setLangCode(ud.getLangCode());
					uvvo.setHeader(dview.getHeader());
					uvvo.setCode(FieldParser.parseDate(ud.getDate()));
					uvvo.setLabel(FieldParser.parseDate(ud.getDate()));
					vo.addUniqueValue(uvvo);
				}
			} else {
				List<UniqueTextValues> values = resourceViewDao.findTextValues(dview);
				for (UniqueTextValues ud : values) {
					UniqueValueVO uvvo = new UniqueValueVO();
					uvvo.setDatatype(dview.getContentDescriptor());
					uvvo.setDs_id(datasetID);
					uvvo.setLangCode(ud.getLangCode());
					uvvo.setHeader(dview.getHeader());
					uvvo.setCode(ud.getValue());
					uvvo.setLabel(ud.getLabel());
					vo.addUniqueValue(uvvo);
				}
			}
			datasetVO.addDescriptorViewVO(vo);
		}
		
		rvo.addDataset(datasetVO);
		
		ResourceView rv = vo2resourceView(rvo);
		tableViewID = resourceViewDao.saveTableView(rv);
		fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.READ, tableViewID, true);
		fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.WRITE, tableViewID, true);
		fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DELETE, tableViewID, true);
		fenixPermissionManager.updateGroupPermissionOnResource(FenixSecurityConstant.ALL_USER_GROUP, FenixPermission.DOWNLOAD, tableViewID, true);
		
		return tableViewID;
	}
	
	private Long updateTableView(Long tableViewID, ResourceView rv) throws FenixGWTException {
		ResourceView existingRV = resourceViewDao.findByID(tableViewID);
		existingRV.getDatasets().removeAll(existingRV.getDatasets());
		existingRV.getDescriptorViews().removeAll(existingRV.getDescriptorViews());
		resourceViewDao.updateTableView(existingRV, rv);
		LOGGER.info("Updating Existing TableView... DONE");
		return tableViewID;
	}
	
	private ResourceView vo2resourceView(ResourceViewVO vo) {
		ResourceView rv = new ResourceView();
		for (DatasetVO dvo : vo.getDatasets()) {
			Dataset dataset = vo2dataset(dvo); 
			rv.addDataset(dataset);
			for (DescriptorViewVO dvvo : dvo.getDescriptorViews())
				rv.addDescriptorView(vo2descriptor(dvvo, dataset));
		}
		return rv;
	}
	
	private DescriptorView vo2descriptor(DescriptorViewVO vo, Dataset dataset) {
		DescriptorView v = new DescriptorView();
		v.setContentDescriptor(vo.getContentDescriptor());
		v.addDataset(dataset);
		v.setContentDescriptor(vo.getContentDescriptor());
		v.setDimensionVisible(vo.isDimensionVisible());
		v.setHeader(vo.getHeader());
		v.setIsSelected(vo.isSelected());
		if (vo.getValues() != null)
			for (UniqueValueVO uvvo : vo.getValues())
				v.addUniqueValue(vo2uniqueValue(uvvo));
		return v;
	}
	
	private Dataset vo2dataset(DatasetVO vo) {
		Dataset d = new CoreDataset();
		if (vo.getDsId() != null && !vo.getDsId().equalsIgnoreCase("null"))
			d.setResourceId(Long.valueOf(vo.getDsId()));
		if (vo.getDatasetName() != null)
			d.setTitle(vo.getDatasetName());
		return d;
	}
	
	private UniqueValue vo2uniqueValue(UniqueValueVO vo) {
		if (vo.getDatatype().contains("date") || vo.getDatatype().contains("Date")) {
			UniqueDateValues u = new UniqueDateValues();
			u.setDs_id(vo.getDs_id());
			u.setHeader(vo.getHeader());
			u.setLangCode(vo.getLangCode());
			u.setDs_id(vo.getDs_id());
			u.setDate(FieldParser.parseDate(vo.getCode()));
			return u;
		} else {
			UniqueTextValues u = new UniqueTextValues();
			u.setDs_id(vo.getDs_id());
			u.setHeader(vo.getHeader());
			u.setLangCode(vo.getLangCode());
			u.setDs_id(vo.getDs_id());
			u.setLabel(vo.getLabel());
			u.setValue(vo.getCode());
			return u;
		}
	}

	public void createGhostTables() {
		uniqueValuesDao.createGhostTables();
	}

	public Long getDatasetIdFromTableViewId(Long tableViewId) {
		return tableDao.findById(tableViewId).getDatasetId();
	}

	public Long saveTableView(Long datasetID, List<String> list) {
		TableView tableView = new TableView();
		List<SelectedField> selectedFieldList = new ArrayList<SelectedField>();
		for (int i = 0; i < list.size(); i++) {
			SelectedField selectedField = new SelectedField();
			selectedField.setFieldName(list.get(i));
			selectedFieldList.add(selectedField);
			// Logger.getRootLogger().warn(selectedField.getFieldName());
		}
		tableView.setDatasetId(datasetID);
		tableView.setSelectedFields(selectedFieldList);
		tableView.setSharingCode(SharingCode.Public.name());
		tableDao.save(tableView);
		return tableView.getResourceId();
	}

	private List<String> getContentType(long datasetId) {
		Dataset dataset = datasetDao.findById(datasetId);
		List<DataType> fieldList = GwtConnector.getContentDescriptors(dataset);

		List<String> result = new ArrayList<String>();
		for (DataType string : fieldList) {
			result.add(string.name());
		}
		return result;
	}

	// POSSIBLY DELETE
	private List<String> getColumnNames(long datasetId) {
		Dataset dataset = datasetDao.findById(datasetId);
		return GwtConnector.getColumnNames(dataset);
	}

	private Map<String, Descriptor> getDatasetDescriptors(Dataset dataset) {
		return GwtConnector.getColumnDescriptors(dataset);
	}

	public TableVO getDatasetDetails(Long resourceId, String language) {

		Dataset dataset = datasetDao.findById(resourceId);

		TableVO vo = new TableVO();

		if (dataset != null) {
			vo.setPeriodTypeCode(dataset.getPeriodTypeCode());
			vo.setDatasetTitle(dataset.getTitle());
			vo.setDateLastUpdate(dataset.getDateLastUpdate());
			vo.setRegion(dataset.getRegion());
			vo.setDatasetType(dataset.getType().name());
			vo.setDatasetExplicitType(dataset.getDatasetType().getTitle());

			Map<String, Descriptor> columns = getDatasetDescriptors(dataset);

			vo.setDimensionDescriptorMap(getDimensionDetails(columns, resourceId, language));
			vo.setDimensionSize(columns.size());
			vo.setLanguage(language.toUpperCase());

		} else
			LOGGER.info("DATASET is NULL");

		return vo;
	}
	
	private Map<String, DimensionBeanVO> getDimensionDetails(Map<String, Descriptor> descriptors, Long datasetId, String language) {

		Map<String, DimensionBeanVO> dimensionMap = new LinkedHashMap<String, DimensionBeanVO>();

		Iterator<Map.Entry<String, Descriptor>> iterator = descriptors.entrySet().iterator();
		for (int i = 0; i < descriptors.size(); i++) {
			Map.Entry<String, Descriptor> column = iterator.next();
			// String header = column.getKey();
			String id = column.getKey();
			Descriptor descriptor = column.getValue();
			String header = descriptor.getHeader();

			List<Option> optionList = descriptor.getOptions();
			// System.out.println("header -----> " + header);

			String columnId = "column" + (i + 1);// i+1 because column0 = row id

			// LOGGER.info("header = "+header + " columnId: " + columnId +
			// " datatype: "+descriptor.getDataType().toString());

			DimensionBeanVO dimensionBean = new DimensionBeanVO();
			dimensionBean.setHeader(header);
			dimensionBean.setDescriptorId(id);
			dimensionBean.setColumnDataType(descriptor.getDataType().toString());

			for (Option op : optionList) {
				if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null) {
					// LOGGER.info("Found coding " + op);
					dimensionBean.setHasCodingSystem(true);
					CodingSystemVo vo = new CodingSystemVo();
					vo.setTitle(op.getOptionValue());
					vo.setRegion(op.getOptionRegion());
					dimensionBean.setCodingSystemVO(vo);
					break;
				}
			}

			if (dimensionBean.getColumnDataType().equals("date") || dimensionBean.getColumnDataType().equals("baseDateFrom") || dimensionBean.getColumnDataType().equals("baseDateTo"))
				dimensionBean.setIsDate(true);

			UniqueTextValues utv = null;
			UniqueDateValues udv = null;

			// if the column has a coding system
			if (!dimensionBean.isDate() && dimensionBean.hasCodingSystem()) {
//				System.out.println("looking for header and language ----> " + header + " | " + language);
				List<UniqueTextValues> utvList = uniqueValuesDao.getText(datasetId, header, language);
				List<UniqueTextValues> utvDefaultLanguage = new ArrayList<UniqueTextValues>();
				Boolean sort = false;

				/**
				 * if there is no coding system for the used language use the
				 * english
				 **/
//				System.out.println("utvList " + utvList.size());
				if (!language.equals("EN"))
					utvDefaultLanguage = uniqueValuesDao.getText(datasetId, header, "EN");

				if (!language.equals("EN")) {
					if (utvList.size() != utvDefaultLanguage.size()) {
						utvList = mergeCodes(utvList, utvDefaultLanguage);
						sort = true;
					}
				}

				dimensionBean.setDistinctDimensionValues(getUniqueTexts(utvList, sort));
			}

			// if the column doesn't use a langcode
			if (!dimensionBean.isDate() && !dimensionBean.hasCodingSystem()) {
//				System.out.println("looking for header ----> " + header);
				List<UniqueTextValues> utvList = uniqueValuesDao.getText(datasetId, header);
				/**
				 * if there is no coding system for the used language use the
				 * english
				 **/
//				System.out.println("utvList " + utvList.size());
				dimensionBean.setDistinctDimensionValues(getUniqueTexts(utvList, false));
			}

			if (dimensionBean.isDate()) {
				// List of all unique dates for the column
				List<UniqueDateValues> udvList = uniqueValuesDao.getDates(datasetId, header);
				// LOGGER.info("date List size =  = "+udvList.size());

				if (udvList.size() > 0)
					udv = udvList.get(0);

				if (udv != null) {
					// LOGGER.info("header = "+header + " isDate: " +
					// " NOCodingSystem: datatype: "+udv.getDatatype());

					dimensionBean.setHasCodingSystem(false);
					dimensionBean.setDistinctDimensionValues(getUniqueDates(udvList));
				}
			}

			dimensionMap.put(columnId, dimensionBean);
		}

		return dimensionMap;
	}

	private List<UniqueTextValues> mergeCodes(List<UniqueTextValues> utvList, List<UniqueTextValues> utvDefaultLanguage) {
		List<UniqueTextValues> utvToAdd = new ArrayList<UniqueTextValues>();
		for (UniqueTextValues utvDL : utvDefaultLanguage) {
			Boolean isIn = false;
			for (UniqueTextValues utv : utvList) {
//				System.out.println("utvDL: " + utvDL.getValue() + " | " + utv.getValue());
				if (utv.getValue().equals(utvDL.getValue())) {
					isIn = true;
					break;
				}
			}

			if (!isIn)
				utvToAdd.add(utvDL);
		}

		if (!utvToAdd.isEmpty())
			utvList.addAll(utvToAdd);

//		System.out.println("printing utvDefaultLanguage");
//		System.out.println(utvDefaultLanguage);
//		System.out.println("printing utvToAdd");
//		System.out.println(utvToAdd);
//		System.out.println("printing utvList");
//		System.out.println(utvList);
		return utvList;
	}

	public List<TableFilterVO> getFilters(Long resourceId) {
		List<TableFilterVO> filters = new ArrayList<TableFilterVO>();
		List<String> columns = getColumnNames(resourceId);
		Dataset dataset = datasetDao.findById(resourceId);

		// LOGGER.info("ADDING FILTERS " + columns.size());
		for (int i = 0; i < columns.size(); i++) {
			TableFilterVO t = new TableFilterVO();
			t.setResourceId(resourceId);
			t.setHeader(columns.get(i));
			t.setDataType(GwtConnector.findContentDescriptorFromColumnName(dataset, columns.get(i)).name());
			t.setDimensionDescriptorId(GwtConnector.getDescriptorFromColumnName(dataset, columns.get(i)).getId());

			filters.add(t);
		}
		// LOGGER.info("ADDED FILTERS: " + filters.size());
		// LOGGER.info("BEGINNING FILTERS SIZE: " + filters.size() );
		return filters;
	}

	public Integer getRecordSize(Long resourceId) {
		return new Integer((int) datasetDao.getContentCount(resourceId));

		// List<String> columnNames = gwtConnector.getColumnNames(resourceId);
		// return datasetDao.getRowValues(resourceId, columnNames).size();
	}

	public Long createNewTableResource(Long resourceId) {
		List<String> columnNames = getContentType(resourceId);
		Long id = saveTableView(resourceId, columnNames);
		// Logger.getRootLogger().warn("Returning TableView ID " + id);
		return id;
	}

	public FenixResourceVo getNewResource(Long id) throws FenixGWTException {
		TableView view = tableDao.findById(id);
		FenixResourceVo fenixResource = null;
		try {
			fenixResource = FenixResourceBuilder.build(tableDao.findById(id));
			return fenixResource;
		} catch (NullPointerException e) {
			throw new FenixGWTException(e.getMessage());
		}
	}
	
	public List<List<String>> getFilteredRecords(String decimalPlaces, Long datasetId, List<String> headerList, Map<String, List<String>> filterCriteria, String language) {
		// LOGGER.info("----- getFilteredRecords ----------------- ################");

		Dataset dataset = datasetDao.findById(datasetId);

		List<Object[]> originalRowList = datasetDao.getFilteredRowValues(dataset, headerList, filterCriteria);
		List<List<String>> rowList = new ArrayList<List<String>>();

		for (int i = 0; i < originalRowList.size(); i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 0; j < originalRowList.get(i).length; j++)
				// if for core dataset
				if (originalRowList.get(i)[j] != null) {
					// FIXME if for flex dataset
					if (!originalRowList.get(i)[j].equals("null"))
						row.add(originalRowList.get(i)[j].toString());
					else
						row.add(EmptyDatumLabel.getLabel());
				} else
					row.add(EmptyDatumLabel.getLabel());
			rowList.add(row);

		}

		List<List<String>> rowListResult = new ArrayList<List<String>>();

		// --- cache some info for each column

		int columns = headerList.size();
		// LOGGER.info("------------- BEFORE columns =  "+ columns );
		long t0 = System.currentTimeMillis();
		DataType colTypes[] = new DataType[columns];
		Option codingOptions[] = new Option[columns];
		HashMap cachedCodes[] = new HashMap[columns];
		for (int j = 0; j < columns; j++) {
			String header = headerList.get(j);
			// LOGGER.info("------getFilteredRecords: header = " + header);
			if (!header.equals("ID")) {

				Descriptor descriptor = GwtConnector.getDescriptorFromColumnName(dataset, header);
				if (descriptor == null)
					throw new IllegalArgumentException("Could not find column '" + header + "' in dataset '" + dataset.getTitle() + "' (id:" + datasetId + ")");
				// datatype
				colTypes[j] = descriptor.getDataType();
				// coding option, if any
				List<Option> optionList = descriptor.getOptions();

				codingOptions[j] = null; // init
				for (Option op : optionList) {
					if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null) {
						codingOptions[j] = op;
						// LOGGER.info("Found coding " + op);
						break;
					}
				}
			}
			// init maps
			cachedCodes[j] = new HashMap(60); // ??? this doesnt work --->
												// toIndex-fromIndex+5);
		}
		long t1 = System.currentTimeMillis();
		// LOGGER.info("getRecords: cacheInfo: " + (t1-t0) + "ms" );

		// LOGGER.info("------------- AFTER columns =  "+ columns );

		// if(dataset.getType() == Type.Flex)
		// columns = columns -1;

		// --- transcode codes
		for (int rowidx = 0; rowidx < rowList.size(); rowidx++) {
			List<String> rowResult = new ArrayList<String>(columns);

			for (int colidx = 0; colidx < columns; colidx++) {

				String cellvalue = rowList.get(rowidx).get(colidx);

				if (colidx == 0 /* && dataset.getType() != Type.Flex */) {
					rowResult.add(cellvalue);
				} else {
					try {
						/*
						 * if(dataset.getType() == Type.Flex &&
						 * colTypes[colidx+1] == DataType.date){ cellvalue =
						 * GwtConnector.formatDate(dataset, cellvalue); } else
						 * if(dataset.getType() == Type.Core && colTypes[colidx]
						 * == DataType.date){ cellvalue =
						 * GwtConnector.formatDate(dataset, cellvalue); }
						 */
						if (colTypes[colidx] == DataType.date) {
							//LOGGER.info("before format = "+ cellvalue);							
							cellvalue = GwtConnector.formatDate(dataset, cellvalue);
							//LOGGER.info("after = "+ cellvalue);							
						} else if (colTypes[colidx] == DataType.quantity) {
							//LOGGER.info("before format = "+ cellvalue + "decimalPlaces = "+decimalPlaces);
							if (cellvalue.equals(EmptyDatumLabel.getLabel()) || cellvalue.equals("") || cellvalue ==null)
								cellvalue = EmptyDatumLabel.getLabel();
							else 
							cellvalue = getDecimalFormatter(Integer.valueOf(decimalPlaces)).format(Double.valueOf(cellvalue));
							//LOGGER.info("after format = "+ cellvalue);							
						} else {
							Option codingOption = null;
							// if(dataset.getType() == Type.Flex ){
							// codingOption = codingOptions[colidx+1];
							// } else if(dataset.getType() == Type.Core){
							codingOption = codingOptions[colidx];
							// }

							if (codingOption != null) { // if option is null, no
														// decoding is needed
								String cached = (String) cachedCodes[colidx].get(cellvalue);
								if (cached == null) { // not in cache yet:
														// retrieve the label
									cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), language);
									if (cached.equals(cellvalue) && !language.equals("EN")) {
										cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), "EN");
									}
									/*
									 * LOGGER.info("Caching code: " +
									 * "coding["+codingOption
									 * .getOptionValue()+"] " +
									 * "region["+codingOption
									 * .getOptionRegion()+"] " +
									 * "code["+cellvalue+"] " +
									 * "label["+cached+"]");
									 */
									cachedCodes[colidx].put(cellvalue, cached);
								}
								cellvalue = cached;
							}
						}
						rowResult.add(colidx, cellvalue);

					} catch (IndexOutOfBoundsException e) {
						throw new FenixException(e.getMessage());
					}
				}
			}

			// add a fake row id
			// if (dataset.getType() == Type.Flex)
			// rowResult.add(0, "0");

			rowListResult.add(rowResult);
		}
		long t2 = System.currentTimeMillis();
		// LOGGER.info("getRecords: decode: " + (t2-t1) + "ms" );

		return rowListResult;
	}
	
	public Map<String, String> getColumnConfig(Long datasetId) {

		List<Descriptor> descriptors = gwtConnector.getDatasetDescriptors(datasetId);

		Map<String, String> headerTypeMap = new LinkedHashMap<String, String>();
		for (Descriptor descriptor : descriptors) {
			headerTypeMap.put(descriptor.getHeader(), descriptor.getDataType().toString());
		}

		return headerTypeMap;
	}
	
	@SuppressWarnings("unchecked")
	public PagingLoadResult<DatasetRowModel> getFilteredData(final String datasetPrecision, final PagingLoadConfig config, final Long datasetId, final List<String> headerList, final Map<String, List<String>> filterCriteria, final String caller, String language) {
		// if(tableMap==null || !tableMap.containsKey(datasetId)){

	//	 LOGGER.info("--- getFilteredData = "+ caller
	//	 +" ----------------------");
		if (tableMap == null || caller != null) {
			// LOGGER.info("--- LOADING NEW FILTERED DATA -----------------");
			loadFilteredData(datasetPrecision, datasetId, headerList, filterCriteria, language);
		}

		if (config.getSortInfo().getSortField() != null) {
			final String sortField = config.getSortInfo().getSortField();
			if (sortField != null) {
				Collections.sort(TableDataCache.getTableData(datasetId), config.getSortInfo().getSortDir().comparator(new Comparator() {
					public int compare(Object o1, Object o2) {
						DatasetRowModel p1 = (DatasetRowModel) o1;
						DatasetRowModel p2 = (DatasetRowModel) o2;

						for (int i = 0; i < headerList.size(); i++) {
							String columnId = "column" + i;

							if (sortField.equals(columnId)) {
								return p1.getColumnValue(columnId).compareTo(p2.getColumnValue(columnId));
							}

						}
						return 0;
					}
				}));
			}
		}

		ArrayList<DatasetRowModel> sublist = new ArrayList<DatasetRowModel>();
		int start = config.getOffset();
		int limit = TableDataCache.getTableData(datasetId).size();
		if (config.getLimit() > 0) {
			limit = Math.min(start + config.getLimit(), limit);
		}
		for (int i = config.getOffset(); i < limit; i++) {
			sublist.add(TableDataCache.getTableData(datasetId).get(i));
		}
		return new BasePagingLoadResult(sublist, config.getOffset(), TableDataCache.getTableData(datasetId).size());
	}

	private void loadFilteredData(String datasetPrecision, Long datasetId, List<String> headerList, Map<String, List<String>> filterCriteria, String language) {
		List<DatasetRowModel> data = new ArrayList<DatasetRowModel>();

		List<List<String>> records = getFilteredRecords(datasetPrecision, datasetId, headerList, filterCriteria, language);

//		LOGGER.info(" -------------------- records = "+records.size());
		for (int j = 0; j < records.size(); j++) {
			List<String> row = records.get(j);
			DatasetRowModel datasetRow = new DatasetRowModel(row);
			data.add(datasetRow);
		}
	
		TableDataCache.addTableDataToCache(datasetId, data);
	}

	private Map<String, String> getUniqueTexts(List<UniqueTextValues> utv, Boolean sort) {
		Map<String, String> dimensionsMap = new LinkedHashMap<String, String>();

		if (utv != null) {
			for (UniqueTextValues u : utv) {
				// LOGGER.info(u.getDatatype() + " | " + u.getLabel() + " | " +
				// u.getValue());
				if (!u.getLabel().isEmpty())
					dimensionsMap.put(u.getValue(), u.getLabel());
			}
		}

		if (sort)
			dimensionsMap = sortByValues(dimensionsMap);

		return dimensionsMap;
	}

	private Map<String, String> getUniqueDates(List<UniqueDateValues> udv) {
		Map<String, String> dimensionsMap = new LinkedHashMap<String, String>();
		// LOGGER.info("udv.size() = "+ udv.size() );

		if (udv != null) {
			for (UniqueDateValues u : udv) {
				// LOGGER.info(u.getDatatype() + " | " + u.getDate() );

				/*** check if it's monthly or yearly ***/
				if (u.getDate() != null) {
					if (u.getPeriodType().equals(PeriodTypeCode.monthly.toString()))
						dimensionsMap.put(u.getDate().toString(), parseMonthlyDate(u.getDate()));
					else if (u.getPeriodType().equals(PeriodTypeCode.yearly.toString()))
						dimensionsMap.put(u.getDate().toString(), parseYearDate(u.getDate()));
					else
						dimensionsMap.put(u.getDate().toString(), u.getDate().toString());
				}
			}
		}
		// LOGGER.info("Dates dimension Map Size = " + dimensionsMap.size());
		return dimensionsMap;
	}

	public List<List<List<String>>> getColumnsValues(Long datasetId, List<String> columnNames, String lang) {

		List<List<List<String>>> valueCodeLabelList = new ArrayList<List<List<String>>>();

		for (String columnName : columnNames) {

			Map<String, String> r = getDimensionValues(datasetId, columnName, lang);
			List<List<String>> data = new ArrayList<List<String>>();
			Iterator<Map.Entry<String, String>> iterator = r.entrySet().iterator();
			List<String> el;
			for (int i = 0; i < r.size(); i++) {
				el = new ArrayList<String>();
				Map.Entry<String, String> entry = iterator.next();
				el.add(entry.getKey());
				el.add(entry.getValue());
				data.add(el);
			}

			valueCodeLabelList.add(data);
		}

		return valueCodeLabelList;

	}

	/*
	 * public Map<String, String> getDimensionValues(Long datasetId, String
	 * dimensionName) {
	 * 
	 * List<String> valueList = null; Map<String, String> dimensionsMap = new
	 * LinkedHashMap<String, String>();
	 * 
	 * valueList = datasetDao.findDistinctRowValues(datasetId, dimensionName) ;
	 * Dataset dataset = datasetDao.findById(datasetId); DataType
	 * contentDescriptor =
	 * gwtConnector.findContentDescriptorFromColumnName(dataset, dimensionName);
	 * Map<String,String> labelCache = new HashMap<String, String>();
	 * 
	 * for(String value : valueList) { if (contentDescriptor == DataType.date) {
	 * dimensionsMap.put(value, gwtConnector.formatDate(datasetId, value)); }
	 * else { if(labelCache.containsKey(value)) { dimensionsMap.put(value,
	 * labelCache.get(value)); } else { String label =
	 * MatchCodeLabel.getLabel(gwtConnector, codecDao, datasetId, value,
	 * dimensionName); dimensionsMap.put(value, label); labelCache.put(value,
	 * label); } //dimensionsMap.put(value,
	 * MatchCodeLabel.getLabel(gwtConnector, codecDao, datasetId, value,
	 * dimensionName)); }
	 * 
	 * }
	 * 
	 * 
	 * LOGGER.info("------------------- dimensionsMap.size() = "+dimensionsMap.size
	 * ());
	 * 
	 * return dimensionsMap;
	 * 
	 * }
	 */

	public void saveDataset(Long datasetId, Map<String, Map<String, String>> updatedRecords, Map<Integer, Map<String, String>> newRecords, Map<String, DimensionBeanVO> dimensions) {
		// LOGGER.info("saveDataset .. ");
		Dataset dataset = datasetDao.findById(datasetId);
		String datasetType = dataset.getType().name();

		List<Descriptor> keys = new ArrayList<Descriptor>();
		List<Descriptor> nonKeys = new ArrayList<Descriptor>();
		List<Descriptor> descriptors = GwtConnector.getDatasetDescriptors(dataset);

		for (Descriptor descriptor : descriptors) {
			if (descriptor.isKey())
				keys.add(descriptor);
			else
				nonKeys.add(descriptor);
		}

		if (updatedRecords.size() > 0) {// overwrite rows
			
	     // LOGGER.info("Overwrite .. ");
			if (datasetType.equals("Core"))
				overwriteCoreDataset(updatedRecords, keys, nonKeys, dimensions, dataset);
			else if (datasetType.equals("Flex"))
				overwriteFlexDataset(updatedRecords, dimensions, dataset);

//			uniqueValuesDao.createGhostTables(datasetId);
		}

		if (newRecords.size() > 0) {// append rows
		// LOGGER.info("New Records .. ");
			if (datasetType.equals("Core"))
				appendCoreDataset(newRecords, keys, nonKeys, dimensions, dataset);
			else if (datasetType.equals("Flex"))
				appendFlexDataset(newRecords, dimensions, dataset);

	//		uniqueValuesDao.createGhostTables(datasetId);

			// call the function to update the chart (if exist)

		}
		
		
		/** UPDATE GHOST TABLES 
		 * 	TODO: Handle the language
		 * 	TODO: Handle check if all the values are still in the dataset...
		 * 		  (i.e. if a date is manually changed, and the old date it's not anymore in the dataset
		 * 		  should be deleted from the ghost table)
		 * **/
		updateRecordsGhostTables(updatedRecords, dimensions, dataset);		
		updateNewRecordsGhostTables(newRecords, dimensions, dataset);

		// update OLAP's
		olapUpdater.updateOLAP(datasetId);
		
		// update charts
		List<Long> datasetIDs = new ArrayList<Long>();
		datasetIDs.add(datasetId);
		chartDesignUpdater.recreateCharts(datasetIDs);
	}

	private void updateRecordsGhostTables(Map<String, Map<String, String>> modifiedRecords, Map<String, DimensionBeanVO> dimensions, Dataset dataset) {
//		LOGGER.info("DIMENSIONS");
//		for(String key : dimensions.keySet()) {
//			LOGGER.info("KEY: " + key + " | " + dimensions.get(key).getColumnDataType() +" | " + dimensions.get(key).getHeader() + " | " + dimensions.get(key).getValue());
//		}
		
//		LOGGER.info("modifiedRecords");
		for(String key : modifiedRecords.keySet()) {
//			LOGGER.info("KEY: " + key);
			for(String valueKey : modifiedRecords.get(key).keySet()) {
//				LOGGER.info("VALUEKEY: " + valueKey + " | " +  modifiedRecords.get(key).get(valueKey));
				
				DimensionBeanVO dimension = dimensions.get(valueKey);
				
//				LOGGER.info("dimension: " + dimension.getHeader() + " | " + dimension.getColumnDataType() );
				CodingSystem cs = null;
				
				if ( !dimension.getColumnDataType().equalsIgnoreCase("quantity")) {
					if ( dimension.getCodingSystemVO() != null ) {
//						LOGGER.info("coding system: " +  dimension.getCodingSystemVO().getTitle() + " | " + dimension.getCodingSystemVO().getRegion());
						 cs = codecDao.getCodingSystem(dimension.getCodingSystemVO().getTitle(), dimension.getCodingSystemVO().getRegion());
					}
					
					if ( dimension.getColumnDataType().equalsIgnoreCase("date") || dimension.getColumnDataType().equalsIgnoreCase("basedatefrom") ||  dimension.getColumnDataType().equalsIgnoreCase("basedateto")){
						Date date = FieldParser.parseDate(modifiedRecords.get(key).get(valueKey));
						uniqueValuesDao.addUpdateDateValue(dataset.getResourceId(), dimension.getColumnDataType(), dimension.getHeader(), dataset.getPeriodTypeCode(),date );
					}
					else {
						uniqueValuesDao.addUpdateTextValue(dataset.getResourceId(), dimension.getColumnDataType(), dimension.getHeader(), cs, dimension.getValue(), modifiedRecords.get(key).get(valueKey), "EN");
					}
				}

			}
		}
		
		
		
	}
	
	
	
	private void updateNewRecordsGhostTables(Map<Integer, Map<String, String>> modifiedRecords, Map<String, DimensionBeanVO> dimensions, Dataset dataset) {
//		LOGGER.info("DIMENSIONS");
//		for(String key : dimensions.keySet()) {
//			LOGGER.info("KEY: " + key + " | " + dimensions.get(key).getColumnDataType() +" | " + dimensions.get(key).getHeader() + " | " + dimensions.get(key).getValue());
//		}
		
//		LOGGER.info("new recod");
		for(Integer key : modifiedRecords.keySet()) {
//			LOGGER.info("KEY: " + key);
			for(String valueKey : modifiedRecords.get(key).keySet()) {
//				LOGGER.info("VALUEKEY: " + valueKey + " | " +  modifiedRecords.get(key).get(valueKey));
				
				DimensionBeanVO dimension = dimensions.get(valueKey);
				
//				LOGGER.info("dimension: " + dimension.getHeader() + " | " + dimension.getColumnDataType() );
				CodingSystem cs = null;
				
				if ( !dimension.getColumnDataType().equalsIgnoreCase("quantity")) {
					if ( dimension.getCodingSystemVO() != null ) {
//						LOGGER.info("coding system: " +  dimension.getCodingSystemVO().getTitle() + " | " + dimension.getCodingSystemVO().getRegion());
						 cs = codecDao.getCodingSystem(dimension.getCodingSystemVO().getTitle(), dimension.getCodingSystemVO().getRegion());
					}
					
					if ( dimension.getColumnDataType().equalsIgnoreCase("date") || dimension.getColumnDataType().equalsIgnoreCase("basedatefrom") ||  dimension.getColumnDataType().equalsIgnoreCase("basedateto")){
						Date date = FieldParser.parseDate(modifiedRecords.get(key).get(valueKey));
						uniqueValuesDao.addUpdateDateValue(dataset.getResourceId(), dimension.getColumnDataType(), dimension.getHeader(), dataset.getPeriodTypeCode(),date );					}
					else {
						uniqueValuesDao.addUpdateTextValue(dataset.getResourceId(), dimension.getColumnDataType(), dimension.getHeader(), cs, dimension.getValue(), modifiedRecords.get(key).get(valueKey), "EN");
					}
				}

					
			}
		}
	}

	private void overwriteCoreDataset(Map<String, Map<String, String>> modifiedRecords, List<Descriptor> keys, List<Descriptor> nonKeys, Map<String, DimensionBeanVO> dimensions, Dataset dataset) {
		//LOGGER.info("--- IN OVERWRITR overwriteCoreDataset ");
		Iterator<Map.Entry<String, Map<String, String>>> iterator = modifiedRecords.entrySet().iterator();

		Map<String, DimensionBeanVO> codeValues = new HashMap<String, DimensionBeanVO>();

		List<CoreContent> list = new ArrayList<CoreContent>();

		for (int i = 0; i < modifiedRecords.size(); i++) {

			Map.Entry<String, Map<String, String>> entry = iterator.next();
			String rowId = entry.getKey();
			Map<String, String> records = entry.getValue();

			CoreContent coreContent = datasetCoreDao.findCoreContentRow(Long.valueOf(rowId));

			Iterator<Map.Entry<String, String>> columnIterator = records.entrySet().iterator();

			for (int j = 0; j < records.size(); j++) {
				Map.Entry<String, String> columnEntry = columnIterator.next();
				String column = columnEntry.getKey();
				String value = columnEntry.getValue();
				// LOGGER.info("--- TableServiceImpl ----  VALUE =  "+value);

				DimensionBeanVO bean = dimensions.get(column);
				CodingSystemVo codingSystemVO = bean.getCodingSystemVO();

				// coding option, if any
			//	LOGGER.info("--- VALUE "+value);
				String code = value;
				if(code.equalsIgnoreCase("Not available")){
						code = null;
				}
				
				if (bean.hasCodingSystem()) {
					code = codecDao.getCodeFromLabel(value, codingSystemVO.getTitle(), codingSystemVO.getRegion());
				}
				//LOGGER.info("--- TableServiceImpl ----  VALUE = "+value +" CODE =  "+code);

				
				bean.setValue(code);

				
				codeValues.put(bean.getDescriptorId(), bean);
			}

			coreContent = CoreDatasetUtils.overwriteCoreContent(dataset, coreContent, codeValues, dataset.getPeriodTypeCode());

			list.add(coreContent);
		}
		// LOGGER.info("------------ list.size = "+list.size());

		save(list, (CoreDataset) dataset, keys, nonKeys);

		datasetDao.update(dataset);
	}

	private void appendCoreDataset(Map<Integer, Map<String, String>> newRecords, List<Descriptor> keys, List<Descriptor> nonKeys, Map<String, DimensionBeanVO> dimensions, Dataset dataset) {

		Iterator<Map.Entry<Integer, Map<String, String>>> iterator = newRecords.entrySet().iterator();

		Map<String, DimensionBeanVO> codeValues = new HashMap<String, DimensionBeanVO>();

		List<CoreContent> list = new ArrayList<CoreContent>();

		for (int i = 0; i < newRecords.size(); i++) {

			Map.Entry<Integer, Map<String, String>> entry = iterator.next();
			Map<String, String> records = entry.getValue();

			CoreContent coreContent = null;

			Iterator<Map.Entry<String, String>> columnIterator = records.entrySet().iterator();

			for (int j = 0; j < records.size(); j++) {
				Map.Entry<String, String> columnEntry = columnIterator.next();
				String columnIdx = columnEntry.getKey();
				String value = columnEntry.getValue();
				// LOGGER.info("--- TableServiceImpl ----  VALUE =  "+value);

				DimensionBeanVO bean = dimensions.get(columnIdx);
				CodingSystemVo codingSystemVO = bean.getCodingSystemVO();

				// coding option, if any
				String code = value;
				if(code.equalsIgnoreCase("Not available")){
					code = null;
				}
				
				if (bean.hasCodingSystem()) {
					code = codecDao.getCodeFromLabel(value, codingSystemVO.getTitle(), codingSystemVO.getRegion());
				}
				bean.setValue(code);

				// LOGGER.info("--- TableServiceImpl ----  VALUE = "+value
				// +" CODE =  "+code);

				codeValues.put(bean.getDescriptorId(), bean);
			}

			coreContent = CoreDatasetUtils.createCoreContent(dataset, codeValues, dataset.getPeriodTypeCode());

			list.add(coreContent);
		}
		// LOGGER.info("------------ list.size = "+list.size());

		save(list, (CoreDataset) dataset, keys, nonKeys);

		// datasetDao.update(dataset);
	}

	private void overwriteFlexDataset(Map<String, Map<String, String>> modifiedRecords, Map<String, DimensionBeanVO> dimensions, Dataset dataset) {

		Iterator<Map.Entry<String, Map<String, String>>> iterator = modifiedRecords.entrySet().iterator();

		Map<String, DimensionBeanVO> codeValues = new HashMap<String, DimensionBeanVO>();

		for (int i = 0; i < modifiedRecords.size(); i++) {

			Map.Entry<String, Map<String, String>> entry = iterator.next();
			String rowId = entry.getKey();
			Map<String, String> records = entry.getValue();

			Iterator<Map.Entry<String, String>> columnIterator = records.entrySet().iterator();

			for (int j = 0; j < records.size(); j++) {
				Map.Entry<String, String> columnEntry = columnIterator.next();
				String column = columnEntry.getKey();
				String value = columnEntry.getValue();
				// LOGGER.info("--- TableServiceImpl ----  VALUE =  "+value);

				DimensionBeanVO bean = dimensions.get(column);
				CodingSystemVo codingSystemVO = bean.getCodingSystemVO();

				// coding option, if any
				String code = value;
				if(code.equalsIgnoreCase("Not available")){
					code = null;
				}
				
				if (bean.hasCodingSystem()) {
					code = codecDao.getCodeFromLabel(value, codingSystemVO.getTitle(), codingSystemVO.getRegion());
				}

				bean.setValue(code);

				// LOGGER.info("--- TableServiceImpl ----  VALUE = "+value
				// +" CODE =  "+code);

				codeValues.put(bean.getDescriptorId(), bean);

			}

			// LOGGER.info("--- TableServiceImpl ----  Row Id  = "+rowId);

			List<? extends FlexCell> cellsOriginal = datasetFlexDao.findFlexCells(Long.valueOf(rowId));

			List<FlexCell> updatedCells = FlexDatasetUtils.updateFlexCells(codeValues, cellsOriginal);

			appender.findExistingFlexRow((FlexDataset) dataset, updatedCells, UploadPolicy.overwrite);

		}
	}

	private void appendFlexDataset(Map<Integer, Map<String, String>> newRecords, Map<String, DimensionBeanVO> dimensions, Dataset dataset) {

		Iterator<Map.Entry<Integer, Map<String, String>>> iterator = newRecords.entrySet().iterator();

		Map<String, DimensionBeanVO> codeValues = new HashMap<String, DimensionBeanVO>();

		for (int i = 0; i < newRecords.size(); i++) {

			Map.Entry<Integer, Map<String, String>> entry = iterator.next();
			int rowId = entry.getKey();
			Map<String, String> records = entry.getValue();

			Iterator<Map.Entry<String, String>> columnIterator = records.entrySet().iterator();

			for (int j = 0; j < records.size(); j++) {
				Map.Entry<String, String> columnEntry = columnIterator.next();
				String column = columnEntry.getKey();
				String value = columnEntry.getValue();
				// LOGGER.info("--- TableServiceImpl ----  VALUE =  "+value);

				DimensionBeanVO bean = dimensions.get(column);
				CodingSystemVo codingSystemVO = bean.getCodingSystemVO();

				// coding option, if any
				String code = value;
				if(code.equalsIgnoreCase("Not available")){
					code = null;
				}
				
				if (bean.hasCodingSystem()) {
					code = codecDao.getCodeFromLabel(value, codingSystemVO.getTitle(), codingSystemVO.getRegion());
				}

				bean.setValue(code);

				// LOGGER.info("--- TableServiceImpl ----  VALUE = "+value
				// +" CODE =  "+code);

				codeValues.put(bean.getDescriptorId(), bean);

			}

			// LOGGER.info("--- TableServiceImpl ----  Row Id  = "+rowId);

			List<FlexCell> updatedCells = FlexDatasetUtils.createFlexCells(dataset, codeValues);

			appender.findExistingFlexRow((FlexDataset) dataset, updatedCells, UploadPolicy.overwrite);

			datasetDao.save(dataset);

		}
	}

	private void save(List<CoreContent> coreContentList, CoreDataset dataset, List<Descriptor> keys, List<Descriptor> nonKeys) {
		Appender.Worker worker = appender.createWorker(dataset, keys, nonKeys, UploadPolicy.overwrite);
		for (CoreContent content : coreContentList) {
			worker.append(content);
			// LOGGER.debug("------------ worker.append(content) = "+worker.append(content));
		}
		worker.flush();
	}

	public Long getCountriesCrisisDatasetID() {
		Dataset datasetCrisis = datasetDao.findByTitle("Countries in crisis requiring external assistance");
		return datasetCrisis.getResourceId();
	}

	public Long getUnfavourableProspectsDatasetID() {
		Dataset datasetUnfavourable = datasetDao.findByTitle("Countries with unfavourable prospects for current crops");
		return datasetUnfavourable.getResourceId();
	}

	public String getMostRecentDate(String datasetType) {
		Date date = crisisDao.getMostRecentDateCountriesCrisis(datasetType);
		return date.toString();
	}

	public Boolean isCoreDataset(Long datasetId) {
		return gwtConnector.isCoreDataset(datasetId);
	}

	public void deleteRow(List<String> rowIdList, String datasetType, Long datasetId) {
		if (datasetType.equals("Core")) {
			for (String rowId : rowIdList)
				datasetCoreDao.deleteCoreContent(datasetCoreDao.findCoreContentRow(Long.valueOf(rowId)));

			uniqueValuesDao.createGhostTables(datasetId);
		}

		else if (datasetType.equals("Flex")) {
			for (String rowId : rowIdList)
				datasetFlexDao.deleteFlexRow(datasetFlexDao.findFlexRow(Long.valueOf(rowId)));

			uniqueValuesDao.createGhostTables(datasetId);
		}

		// chartUpdater.updateDatasetCharts(datasetId);
	}

	public static String parseMonthlyDate(Date date) {
		int year = 1900 + date.getYear();
		int month = 1 + date.getMonth();
		String y = String.valueOf(year);
		String m = String.valueOf(month);
		// LOGGER.debug(year);
		if (month < 10)
			m = "0" + m;
		return y + "-" + m;
	}

	public static String parseYearDate(Date date) {
		int year = 1900 + date.getYear();
		String y = String.valueOf(year);
		return y;
	}

	public List<DimensionsBeanVO> hasValidDimensions(String datasetId, List<String> headers, String lang) {
		List<DimensionsBeanVO> results = new ArrayList<DimensionsBeanVO>();
		UniqueTextValues utv = new UniqueTextValues();
		UniqueDateValues udv = new UniqueDateValues();

		for (String header : headers) {
			// LOGGER.debug("dimension: " + header + " | " + lang);
			DimensionsBeanVO dimension = new DimensionsBeanVO();
			utv = uniqueValuesDao.isValidText(Long.parseLong(datasetId), header, lang);

			if (utv != null) {
				dimension.setDatatype(utv.getDatatype());
				dimension.setHeader(header);
				// LOGGER.debug("UTV: " + header + " | " + utv.getDatatype());
				results.add(dimension);
			} else {
				udv = uniqueValuesDao.isValidDate(Long.parseLong(datasetId), header);
				if (udv != null) {
					dimension.setDatatype(udv.getDatatype());
					dimension.setHeader(header);
					// LOGGER.debug("UDV: " + header + " | " +
					// udv.getDatatype());
					results.add(dimension);
				}
			}
		}
		return results;
	}

	public List<List<String>> getFilteredRecordsToExport(String datasetPrecision, Long datasetId, List<String> headerList, Map<String, List<String>> filterCriteria, String type, String language) {
	//	 LOGGER.info("----- getFilteredRecordsToExport ----------------- ################");
//		 LOGGER.info(datasetPrecision);

		Dataset dataset = datasetDao.findById(datasetId);

		List<Object[]> originalRowList = datasetDao.getFilteredRowValues(dataset, headerList, filterCriteria);
		List<List<String>> rowList = new ArrayList<List<String>>();

		for (int i = 0; i < originalRowList.size(); i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 1; j < originalRowList.get(i).length; j++)
				if (originalRowList.get(i)[j] != null) {
					if (!originalRowList.get(i)[j].equals("null"))
						row.add(originalRowList.get(i)[j].toString());
					else
						row.add(EmptyDatumLabel.getLabel());
				} else
					row.add(EmptyDatumLabel.getLabel());
			rowList.add(row);

		}
		// LOGGER.info(rowList);
		if (type.equals("code")) {
			// LOGGER.info("EXPORT WITH CODES");
			return fillExcelExportWithCodes(headerList, rowList);
		} else if (type.equals("label")) {
			// LOGGER.info("EXPORT WITH LABELS");
			return fillExcelExportWithLabels(datasetPrecision, dataset, headerList, rowList, language);
		} else if (type.equals("codelabel")) {
//			 LOGGER.info("EXPORT WITH CODES AND LABELS");
			return fillExcelExportWithCodesLabels(datasetPrecision, dataset, headerList, rowList, language);
		}
		return null;
	}

	private List<List<String>> fillExcelExportWithCodes(List<String> headerList, List<List<String>> rowList) {
		List<List<String>> result = new ArrayList<List<String>>();
		result.add(0, headerList);
		result.addAll(rowList);
		return result;
	}

	private List<List<String>> fillExcelExportWithLabels(String datasetPrecision, Dataset dataset, List<String> headerList, List<List<String>> rowList, String language) {
		List<List<String>> result = new ArrayList<List<String>>();
		int columns = headerList.size();
		long t0 = System.currentTimeMillis();
		DataType colTypes[] = new DataType[columns];
		Option codingOptions[] = new Option[columns];
		HashMap cachedCodes[] = new HashMap[columns];
		for (int j = 0; j < columns; j++) {
			String header = headerList.get(j);
			// LOGGER.info("------getFilteredRecords: header = " + header);
			if (!header.equals("ID")) {

				Descriptor descriptor = GwtConnector.getDescriptorFromColumnName(dataset, header);
				if (descriptor == null)
					throw new IllegalArgumentException("Could not find column '" + header + "' in dataset '" + dataset.getTitle() + "' (id:" + dataset.getResourceId() + ")");
				// datatype
				colTypes[j] = descriptor.getDataType();
				// coding option, if any
				List<Option> optionList = descriptor.getOptions();

				codingOptions[j] = null; // init
				for (Option op : optionList) {
					if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null) {
						codingOptions[j] = op;
						// LOGGER.info("Found coding " + op);
						break;
					}
				}
			}
			// init maps
			cachedCodes[j] = new HashMap(60); // ??? this doesnt work --->
												// toIndex-fromIndex+5);
		}
		long t1 = System.currentTimeMillis();
		// LOGGER.info("getRecords: cacheInfo: " + (t1-t0) + "ms" );

		// --- transcode codes
		for (int rowidx = 0; rowidx < rowList.size(); rowidx++) {
			List<String> rowResult = new ArrayList<String>(columns);

			for (int colidx = 0; colidx < columns; colidx++) {

				String cellvalue = rowList.get(rowidx).get(colidx);
				try {
					if (colTypes[colidx] == DataType.date) {
						cellvalue = GwtConnector.formatDate(dataset, cellvalue);
					} else if (colTypes[colidx] == DataType.quantity) {
						if (cellvalue.equals(EmptyDatumLabel.getLabel()) || cellvalue.equals("") || cellvalue ==null)
							cellvalue = EmptyDatumLabel.getLabel();
						else 
						cellvalue = getDecimalFormatter(Integer.valueOf(datasetPrecision)).format(Double.valueOf(cellvalue));
					//	LOGGER.info(cellvalue);
					}else {
						Option codingOption = null;
						codingOption = codingOptions[colidx];

						if (codingOption != null) { // if option is null, no
													// decoding is needed
							String cached = (String) cachedCodes[colidx].get(cellvalue);
							if (cached == null) { // not in cache yet: retrieve
													// the label
								cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), language);
//								System.out.println("cached: " + cached + " | " + cellvalue + " | " + language);
								if (cached.equals(cellvalue) && !language.equals("EN"))
									cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), "EN");

								// LOGGER.info("Caching code: " +
								// "coding["+codingOption.getOptionValue()+"] "
								// +
								// "region["+codingOption.getOptionRegion()+"] "
								// +
								// "code["+cellvalue+ "]");
								// "label["+cached+"]");
								cachedCodes[colidx].put(cellvalue, cached);
							}
							cellvalue = cached;
						}
					}
					rowResult.add(colidx, cellvalue);

				} catch (IndexOutOfBoundsException e) {
					throw new FenixException(e.getMessage());
				}
			}

			result.add(rowResult);
		}
		long t2 = System.currentTimeMillis();
		// LOGGER.info("getRecords: decode: " + (t2-t1) + "ms" );

		result.add(0, headerList);
		return result;
	}

	private List<List<String>> fillExcelExportWithCodesLabels(String datasetPrecision, Dataset dataset, List<String> headerList, List<List<String>> rowList, String language) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> fullHeaderList = new ArrayList<String>();

		int columns = headerList.size();
		long t0 = System.currentTimeMillis();
		DataType colTypes[] = new DataType[columns];
		Option codingOptions[] = new Option[columns];
		HashMap cachedCodes[] = new HashMap[columns];
		int csNumber = 0;
		for (int j = 0; j < columns; j++) {
			String header = headerList.get(j);
			// LOGGER.info("------getFilteredRecords: header = " + header);
			if (!header.equals("ID")) {

				Descriptor descriptor = GwtConnector.getDescriptorFromColumnName(dataset, header);
				if (descriptor == null)
					throw new IllegalArgumentException("Could not find column '" + header + "' in dataset '" + dataset.getTitle() + "' (id:" + dataset.getResourceId() + ")");
				// datatype
				colTypes[j] = descriptor.getDataType();
				// coding option, if any
				List<Option> optionList = descriptor.getOptions();

				codingOptions[j] = null; // init
				for (Option op : optionList) {
					if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null) {
						codingOptions[j] = op;
						// LOGGER.info("Found coding " + op);
						csNumber++;
						break;
					}
				}
			}
			// init maps
			cachedCodes[j] = new HashMap(60); // ??? this doesnt work --->
												// toIndex-fromIndex+5);
		}
		long t1 = System.currentTimeMillis();
		// LOGGER.info("getRecords: cacheInfo: " + (t1-t0) + "ms" );

		// LOGGER.info("-------------  columns =  "+ columns );
		// LOGGER.info("-------------  codingsystems =  " + csNumber);

		// set headers
		for (int colidx = 0; colidx < columns; colidx++) {

			try {
				Option codingOption = null;
				codingOption = codingOptions[colidx];
				if (codingOption != null) { // if option is null, no decoding is
											// needed
					fullHeaderList.add(headerList.get(colidx));
					fullHeaderList.add(headerList.get(colidx) + " Label");
				} else {
					fullHeaderList.add(headerList.get(colidx));
				}

			} catch (IndexOutOfBoundsException e) {
				throw new FenixException(e.getMessage());
			}
		}
		// LOGGER.info(fullHeaderList);
		result.add(fullHeaderList);

		// --- transcode codes
		for (int rowidx = 0; rowidx < rowList.size(); rowidx++) {
			List<String> rowResult = new ArrayList<String>();

			for (int colidx = 0; colidx < columns; colidx++) {

				String cellvalue = rowList.get(rowidx).get(colidx);
				try {
					if (colTypes[colidx] == DataType.date) {
						cellvalue = GwtConnector.formatDate(dataset, cellvalue);
						rowResult.add(cellvalue);
					} else if (colTypes[colidx] == DataType.quantity) {
						//LOGGER.info("colidx  = "+colidx + ": value "+cellvalue);
						if (cellvalue.equals(EmptyDatumLabel.getLabel()) || cellvalue.equals("") || cellvalue ==null)
							cellvalue = EmptyDatumLabel.getLabel();
						else 
						cellvalue = getDecimalFormatter(Integer.valueOf(datasetPrecision)).format(Double.valueOf(cellvalue));	
						
						//	LOGGER.info("AFTER: colidx  = "+colidx + ": value "+cellvalue);
						rowResult.add(cellvalue);
					}else {
						rowResult.add(cellvalue);
						Option codingOption = null;
						codingOption = codingOptions[colidx];

						if (codingOption != null) { // if option is null, no
													// decoding is needed
							String cached = (String) cachedCodes[colidx].get(cellvalue);
							if (cached == null) { // not in cache yet: retrieve
													// the label
								cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), language);
								if (cached.equals(cellvalue) && !language.equals("EN"))
									cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), "EN");
								// LOGGER.info("Caching code: " +
								// "coding["+codingOption.getOptionValue()+"] "
								// +
								// "region["+codingOption.getOptionRegion()+"] "
								// +
								// "code["+cellvalue+ "]");
								// "label["+cached+"]");
								cachedCodes[colidx].put(cellvalue, cached);
							}
							cellvalue = cached;
							rowResult.add(cellvalue);
						}

					}

				} catch (IndexOutOfBoundsException e) {
					throw new FenixException(e.getMessage());
				}
			}

			result.add(rowResult);
		}
		long t2 = System.currentTimeMillis();
		// LOGGER.info("getRecords: decode: " + (t2-t1) + "ms" );

		return result;
	}

	public Map<String, String> getDimensionValues(List<Long> datasetsId, String columnName, String lang) {
		Map<String, String> dimensionsMap = new LinkedHashMap<String, String>();
		Boolean isDate = false;
		// LOGGER.info("getDimensionValues(List<Long> datasetsId, String columnName, String lang)");

		for (Long datasetId : datasetsId) {
			/**
			 * TODO: should be tested, or passed to the method if the column has
			 * a coding system or not if there is not a codingsystem assosiated
			 * the query should be run without the language but getting all the
			 * codes and labels.
			 */
			// System.out.println("looking for header and language ----> " +
			// columnName + " | " + lang);
			List<UniqueTextValues> utvList = uniqueValuesDao.getText(datasetId, columnName, lang);
			List<UniqueTextValues> utvDefaultLanguage = new ArrayList<UniqueTextValues>();
			Boolean sort = false;

			/**
			 * if there is no coding system for the used language use the
			 * english
			 **/
			// System.out.println("utvList " + utvList.size());

			if (!lang.equals("EN")) {
				utvDefaultLanguage = uniqueValuesDao.getText(datasetId, columnName, "EN");
				if (utvList.size() != utvDefaultLanguage.size()) {
					utvList = mergeCodes(utvList, utvDefaultLanguage);
					sort = true;
				}
			}

			if (!utvList.isEmpty()) {
				for (UniqueTextValues u : utvList) {
					// LOGGER.info(u.getDatatype() + " | " + u.getLabel() +
					// " | " + u.getValue());
					if (!u.getLabel().isEmpty())
						dimensionsMap.put(u.getValue(), u.getLabel());
				}
			}

			if (utvList.isEmpty()) {
				List<UniqueDateValues> udv = uniqueValuesDao.getDates(datasetId, columnName);
				isDate = true;

				if (!udv.isEmpty())
					for (UniqueDateValues u : udv) {
						// LOGGER.info(u.getDatatype() + " | " + u.getDate() );

						/*** check if it's monthly or yearly ***/
						if (u.getDate() != null) {
							if (u.getPeriodType().equals(PeriodTypeCode.monthly.toString()))
								dimensionsMap.put(u.getDate().toString(), parseMonthlyDate(u.getDate()));
							else if (u.getPeriodType().equals(PeriodTypeCode.yearly.toString()))
								dimensionsMap.put(u.getDate().toString(), parseYearDate(u.getDate()));
							else
								dimensionsMap.put(u.getDate().toString(), u.getDate().toString());
						}
					}
			}
      }
      
		if (!isDate)
			dimensionsMap = sortByValues(dimensionsMap);
		else
			dimensionsMap = sortByValuesDates(dimensionsMap);
		return dimensionsMap;
	}

	public Map<String, String> getDimensionValues(Long datasetId, String columnName, String language) {
		Map<String, String> dimensionsMap = new LinkedHashMap<String, String>();
		// LOGGER.info("getDimensionValues(Long datasetId, String columnName, String language)");
		Boolean isDate = false;

		/**
		 * TODO: should be tested, or passed to the method if the column has a
		 * coding system or not if there is not a codingsystem assosiated the
		 * query should be run without the language but getting all the codes
		 * and labels.
		 */
//		System.out.println("looking for header and language ----> " + columnName + " | " + language);
		List<UniqueTextValues> utvList = uniqueValuesDao.getText(datasetId, columnName, language);
		List<UniqueTextValues> utvDefaultLanguage = new ArrayList<UniqueTextValues>();
		Boolean sort = false;

		/** if there is no coding system for the used language use the english **/
		// System.out.println("utvList " + utvList.size());

		if (!language.equals("EN")) {
			utvDefaultLanguage = uniqueValuesDao.getText(datasetId, columnName, "EN");
			if (utvList.size() != utvDefaultLanguage.size()) {
				utvList = mergeCodes(utvList, utvDefaultLanguage);
				sort = true;
			}
		}

		// System.out.println("UTVLIST: " + utvList);

		if (!utvList.isEmpty())
			for (UniqueTextValues u : utvList) {
				// LOGGER.info(u.getDatatype() + " | " + u.getLabel() + " | " +
				// u.getValue());
				if (!u.getLabel().isEmpty())
					dimensionsMap.put(u.getValue(), u.getLabel());
			}

		if (utvList.isEmpty()) {
			List<UniqueDateValues> udv = uniqueValuesDao.getDates(datasetId, columnName);

			if (!udv.isEmpty())
				for (UniqueDateValues u : udv) {
					// LOGGER.info(u.getDatatype() + " | " + u.getDate() );

					/*** check if it's monthly or yearly ***/
					if (u.getPeriodType().equals(PeriodTypeCode.monthly.toString()))
						dimensionsMap.put(u.getDate().toString(), parseMonthlyDate(u.getDate()));
					else if (u.getPeriodType().equals(PeriodTypeCode.yearly.toString()))
						dimensionsMap.put(u.getDate().toString(), parseYearDate(u.getDate()));
					else
						dimensionsMap.put(u.getDate().toString(), u.getDate().toString());
				}
		}

		if (!utvList.isEmpty() && !language.equals("EN") && utvList.size() != utvDefaultLanguage.size()) {
			dimensionsMap = sortByValues(dimensionsMap);
		}
		return dimensionsMap;
	}

	private static TreeMap<String, String> sortByKeys(Map<String, String> in) {
		TreeMap<String, String> out = new TreeMap<String, String>(new StringComparator());
		for (String key : in.keySet()) {
//			System.out.println(key);
			out.put(key, in.get(key));
		}
		return out;
	}

	private static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = 0; i < size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}

	private static LinkedHashMap<String, String> sortByValuesDates(Map<String, String> in) {
		// LOGGER.info("sortByValuesDates IN: " + in);
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);

		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = size - 1; i >= 0; i--)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		// LOGGER.info("sortByValuesDates OUT: " + out);
		return out;
	}

	public String createExcel(Long dataset, String type, String title, List<List<String>> table, String precision) {
		List<String> headers = gwtConnector.getColumnNames(dataset);
		Integer quantatyIndex = -1;
		int i = 0;
		if (!type.equals("codelabel")) {
			for (String header : headers) {
				DataType datatype = gwtConnector.findContentDescriptorFromColumnName(dataset, header);
				if (DataType.quantity == datatype) {
					quantatyIndex = i;
					break;
				}
				i++;
			}
		} else {
			//for each header
			List<String> headersFullList = table.get(0);
			
			for (int j = 0; j < headersFullList.size(); j++) {		
				String headerMain = headersFullList.get(j);
			//	LOGGER.info("headerMain = " + headerMain + " :col index j = "+j);
				
				for (String header : headers) {					
					if(headerMain.equals(header)) {
						DataType datatype = gwtConnector.findContentDescriptorFromColumnName(dataset, header);
						if (DataType.quantity == datatype) {
							quantatyIndex = j;
						//	LOGGER.info("headerMain = " + headerMain + " :quantatyIndex = "+quantatyIndex+ " :col index j = "+j);
							
							break;
						}	
					}
				 }
			}
			
		/**	for (String header : headers) {
				
				Descriptor descriptor = gwtConnector.getDescriptorFromColumnName(dataset, header);
				if (DataType.quantity == descriptor.getDataType()) {
					quantatyIndex = i;
					LOGGER.info("header = " + header + " :quantatyIndex = "+quantatyIndex+ " :col index = "+i);
					
					break;
				}

				List<Option> optionList = descriptor.getOptions();
				for (Option op : optionList) {
					if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null) {
						LOGGER.info("header option = " + header + " :quantatyIndex = "+quantatyIndex+ " :col index = "+i);
						i++;
					}
			    }
				
				i++;
				
				
			}*/
			
			
		}
		return tableExcel.createExcel(title, table, quantatyIndex, precision);
	}

	public DecimalFormat getDecimalFormatter(int decimals) {
//		LOGGER.info("decimals: " + decimals);
		DecimalFormat decimalFormat =null;

		switch (decimals) {
			case 0: decimalFormat = new DecimalFormat("#0"); break;
			case 1: decimalFormat = new DecimalFormat("#,#0.0"); break;
			case 2: decimalFormat = new DecimalFormat("#,##0.00"); break;
			case 3: decimalFormat = new DecimalFormat("#,###0.000"); break;
			case 4: decimalFormat = new DecimalFormat("#,####0.0000"); break;
       	}
	
		//LOGGER.info("getDecimal: " + decimalFormat.format(123.4567));

		return decimalFormat;
	}
	
	public Long getDatasetByCode(String datasetCode) {
		return findDao.findDatasetByCode(datasetCode);	
	}
	
	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	public void setMeDao(MEDao meDao) {
		this.meDao = meDao;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setGwtConnector(GwtConnector gwtConnector) {
		this.gwtConnector = gwtConnector;
	}

	public void setDatasetCoreDao(DatasetCoreDao datasetCoreDao) {
		this.datasetCoreDao = datasetCoreDao;
	}

	public void setDatasetFlexDao(DatasetFlexDao datasetFlexDao) {
		this.datasetFlexDao = datasetFlexDao;
	}

	public void setAppender(Appender appender) {
		this.appender = appender;
	}

	public void setCrisisDao(CrisisDao crisisDao) {
		this.crisisDao = crisisDao;
	}

	public void setUniqueValuesDao(UniqueValuesDao uniqueValuesDao) {
		this.uniqueValuesDao = uniqueValuesDao;
	}

	public void setTableExcel(TableExcel tableExcel) {
		this.tableExcel = tableExcel;
	}

	public void setChartUpdater(ChartUpdater chartUpdater) {
		this.chartUpdater = chartUpdater;
	}

	public void setFenixPermissionManager(FenixPermissionManager fenixPermissionManager) {
		this.fenixPermissionManager = fenixPermissionManager;
	}

	public void setResourceViewDao(ResourceViewDao resourceViewDao) {
		this.resourceViewDao = resourceViewDao;
	}

	public void setOlapJdbc(OLAPJDBC olapJdbc) {
		this.olapJdbc = olapJdbc;
	}

	public void setOlapUpdater(OLAPUpdater olapUpdater) {
		this.olapUpdater = olapUpdater;
	}
	
	public void setFindDao(FindDao findDao) {
		this.findDao = findDao;
	}

	public void setChartDesignUpdater(ChartDesignUpdater chartDesignUpdater) {
		this.chartDesignUpdater = chartDesignUpdater;
	}
	
}