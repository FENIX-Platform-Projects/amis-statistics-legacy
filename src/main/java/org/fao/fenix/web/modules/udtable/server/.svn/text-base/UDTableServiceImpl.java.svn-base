package org.fao.fenix.web.modules.udtable.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.Resource;
import org.fao.fenix.core.domain.constants.CodingType;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.constants.PeriodTypeCode;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.domain.olap.OLAPChartBean;
import org.fao.fenix.core.domain.olap.OLAPParameters;
import org.fao.fenix.core.domain.udtable.UDTable;
import org.fao.fenix.core.domain.udtable.UDTableFilter;
import org.fao.fenix.core.domain.udtable.UDTableHeader;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.persistence.Appender;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.DeleteDao;
import org.fao.fenix.core.persistence.FindDao;
import org.fao.fenix.core.persistence.MEDao;
import org.fao.fenix.core.persistence.UniqueValuesDao;
import org.fao.fenix.core.persistence.UpdateDao;
import org.fao.fenix.core.persistence.crisis.CrisisDao;
import org.fao.fenix.core.persistence.udtable.UDTableConnector;
import org.fao.fenix.core.persistence.udtable.UDTableDao;
import org.fao.fenix.web.modules.olap.common.vo.OLAPChartBeanVO;
import org.fao.fenix.web.modules.olap.common.vo.OLAPChartResultVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.olap.server.birt.OlapChart;
import org.fao.fenix.web.modules.olap.server.utils.OLAPUtils;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.udtable.common.constants.UDTableConstants;
import org.fao.fenix.web.modules.udtable.common.services.UDTableService;
import org.fao.fenix.web.modules.udtable.common.vo.DimensionBean;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableFilterVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableHeaderVO;
import org.fao.fenix.web.modules.udtable.common.vo.UDTableVO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class UDTableServiceImpl extends RemoteServiceServlet implements UDTableService {

	private static Logger LOGGER = Logger.getLogger(UDTableServiceImpl.class);

	private UDTableDao udtableDao;
	private UDTableConnector udtableConnector;

	DatasetDao datasetDao;
	CodecDao codecDao;
	GwtConnector gwtConnector;
	MEDao meDao;
	FindDao findDao;
	UpdateDao updateDao;
	Appender appender;
	DeleteDao deleteDao;
	CrisisDao crisisDao;
	UniqueValuesDao uniqueValuesDao;

	private List<String> getColumnNames(long datasetId) {
		return gwtConnector.getColumnNames(datasetId);
	}

	/*** QUA DEVO MODIFICARE ***/
	public Map<String, String> getDimensionValues(Long datasetId, String columnName, String lang) {
		Map<String, String> dimensionsMap = new LinkedHashMap<String, String>();
		List<UniqueTextValues> utv = uniqueValuesDao.getText(datasetId, columnName, lang);
		for (UniqueTextValues u : utv) {
			System.out.println(u.getDatatype() + " | " + u.getLabel() + " | " + u.getValue());
			if (!u.getLabel().isEmpty())
				dimensionsMap.put(u.getValue(), u.getLabel());
		}

		List<UniqueDateValues> udv = uniqueValuesDao.getDates(datasetId, columnName);

		for (UniqueDateValues u : udv) {
			

			/*** check if it's monthly or yearly ***/
			if (u.getPeriodType().equals(PeriodTypeCode.monthly.toString()))
				dimensionsMap.put(u.getDate().toString(), parseMonthlyDate(u.getDate()));
			else if (u.getPeriodType().equals(PeriodTypeCode.yearly.toString()))
				dimensionsMap.put(u.getDate().toString(), parseYearDate(u.getDate()));
			else
				dimensionsMap.put(u.getDate().toString(), u.getDate().toString());
		}
		return dimensionsMap;
	}
	

	/*** to modifiy the queries on tables dimensions **/
	public UDTableVO getDatasetDetails(Long resourceId) {

		Resource rsrc = meDao.findResourceById(resourceId);
		Dataset dataset = (Dataset) rsrc;

		UDTableVO vo = new UDTableVO();

		if (meDao.getResourceTypeById(resourceId).equals(ResourceType.DATASET)) {

			vo.setPeriodTypeCode(dataset.getPeriodTypeCode());

		}
		vo.setDatasetTitle(rsrc.getTitle());
		vo.setDateLastUpdate(rsrc.getDateLastUpdate());
		vo.setRegion(rsrc.getRegion());
		vo.setDatasetType(dataset.getType().name());
		vo.setDatasetExplicitType(dataset.getDatasetType().getTitle());

		Map<String, DimensionBean> dimensionDescriptorMap = new LinkedHashMap<String, DimensionBean>();

		int i = 0;
		if (gwtConnector.isFlexDataset(dataset.getResourceId())) {
			List<String> columnNames = getColumnNames(resourceId);
			for (String columnName : columnNames) {
				System.out.println("columnName name :" + columnName);
				Descriptor dimensionName = gwtConnector.getDescriptorFromColumnName(dataset, columnName);
				if (DataType.date.equals(dimensionName.getDataType()) || DataType.baseDateFrom.equals(dimensionName.getDataType()) || DataType.baseDateTo.equals(dimensionName.getDataType())) {
					UniqueDateValues uv = uniqueValuesDao.isValidDate(resourceId, dimensionName.toString());
					if (uv != null) {
						System.out.println("VALID columnName name :" + columnName);
						String header = uv.getHeader();
						DimensionBean dimensionBean = new DimensionBean();
						dimensionBean.setColumnDescriptor(dimensionName.getDataType().toString());
						dimensionBean.setLabel(header);
						dimensionDescriptorMap.put(Integer.toString(i + 1), dimensionBean);
						i++;
					}
				} else {
					UniqueTextValues uv = uniqueValuesDao.isValidText(resourceId, columnName, "EN");
					if (uv != null) {
						System.out.println("VALID Dimension name :" + dimensionName.toString());
						String header = uv.getHeader();
						DimensionBean dimensionBean = new DimensionBean();
						dimensionBean.setColumnDescriptor(dimensionName.getDataType().toString());
						dimensionBean.setLabel(header);
						dimensionDescriptorMap.put(Integer.toString(i + 1), dimensionBean);
						i++;
					}
				}
			}
		} else {
			List<DataType> dimensionNames = gwtConnector.getContentDescriptors(resourceId);
			for (DataType dimensionName : dimensionNames) {
				System.out.println("Dimension name :" + dimensionName.toString());
				if (DataType.date.equals(dimensionName) || DataType.baseDateFrom.equals(dimensionName) || DataType.baseDateTo.equals(dimensionName)) {
					UniqueDateValues uv = uniqueValuesDao.isValidDateTOREMOVE(resourceId, dimensionName.toString());
					if (uv != null) {
						System.out.println("VALID Dimension name :" + dimensionName.toString());
						String header = uv.getHeader();
						DimensionBean dimensionBean = new DimensionBean();
						dimensionBean.setColumnDescriptor(dimensionName.toString());
						dimensionBean.setLabel(header);
						dimensionDescriptorMap.put(Integer.toString(i + 1), dimensionBean);
						i++;
					}
				} else {
					UniqueTextValues uv = uniqueValuesDao.isValidTextTOREMOVE(resourceId, dimensionName.toString(), "EN");
					if (uv != null) {
						System.out.println("VALID Dimension name :" + dimensionName.toString());
						String header = uv.getHeader();
						DimensionBean dimensionBean = new DimensionBean();
						dimensionBean.setColumnDescriptor(dimensionName.toString());
						dimensionBean.setLabel(header);
						dimensionDescriptorMap.put(Integer.toString(i + 1), dimensionBean);
						i++;
					}
				}
			}

		}

		vo.setDimensionDescriptorMap(dimensionDescriptorMap);
		vo.setDimensionSize(i);

		return vo;
	}
	
	/* public TableVO getDatasetDetails(Long resourceId) {
			
		   Resource rsrc = meDao.findResourceById(resourceId);
		   Dataset dataset = (Dataset)rsrc;
		   
			if(rsrc!=null){
				System.out.println("RSRC IS NOT NUL ...................");	
			} else System.out.println("RSRC  NULL !!!!!!!!!!!!!!!!!!!!");
			
				
			List<String> dimensionNames = getColumnNames(resourceId);
			
			TableVO vo = new TableVO();
		
			if (meDao.getResourceTypeById(resourceId).equals(ResourceType.DATASET)) {
				
				vo.setPeriodTypeCode(dataset.getPeriodTypeCode());
			
			}
			vo.setDatasetTitle(rsrc.getTitle());
			vo.setDateLastUpdate(rsrc.getDateLastUpdate());
			vo.setRegion(rsrc.getRegion());
			vo.setDatasetType(dataset.getType().name());
			vo.setDatasetExplicitType(dataset.getDatasetType().getTitle());

			
			Map<String, DimensionBean> dimensionDescriptorMap = new LinkedHashMap<String, DimensionBean>();
			
			List<String> columnNames = getColumnNames(resourceId);
		
			
			for (int i = 0; i< columnNames.size(); i++ ){
				String header = columnNames.get(i);
				DimensionBean dimensionBean = new DimensionBean();
				String columnDescriptor = gwtConnector.findContentDescriptorFromColumnName(resourceId, header).name();

				dimensionBean.setColumnDescriptor(columnDescriptor);
				dimensionBean.setLabel(header);

				List<Option> options = gwtConnector.getOptionsFromColumnName(dataset, header);
				LOGGER.info("------------- options size = "+options.size() + " for "+header);
				
				if(options.size() > 0){
					dimensionBean.setHasCodingSystem(true);
					for (Option option : options){
						if(option.getOptionName().equals("featureCodeSet")) {
							dimensionBean.setGAULReference((option.getOptionValue()).toUpperCase());
						}
						dimensionBean.setCodingSystemName(option.getOptionName());
						dimensionBean.setCodingSystemValue(option.getOptionValue());
						dimensionBean.setCodingSystemRegion(option.getOptionRegion());

					}
				}		    	
					//dimensionDescriptorMap.put((gwtConnector.findContentDescriptorFromColumnName(resourceId, header)).toString(), header);
					dimensionDescriptorMap.put("column"+(i+1), dimensionBean);
				}
			
			vo.setDimensionDescriptorMap(dimensionDescriptorMap);
		
		//	vo.setDimensionNameList(dimensionNames);
			vo.setDimensionSize(dimensionNames.size());
			
		
			return vo;
		}*/

	/*** lang "EN" ??? **/
	/** TODO: make lang diynamic ***/
	public List<UDTableFilterVO> getFilters(Long resourceId) {
		List<UDTableFilterVO> filters = new ArrayList<UDTableFilterVO>();
		List<String> columns = getColumnNames(resourceId);
		for (int i = 0; i < columns.size(); i++) {
			
			String dataType = gwtConnector.getDescriptorFromColumnName(resourceId, columns.get(i)).getDataType().toString();
			
			if(!dataType.equals(DataType.quantity)) {
				UDTableFilterVO t = new UDTableFilterVO();
				t.setResourceId(resourceId);
				t.setHeader(columns.get(i));

				t.setDataType(dataType);
				List<Option> options = gwtConnector.getOptionsFromColumnName(resourceId, columns.get(i));
				for (Option option : options) {
					CodingType ct = CodingType.valueOfIgnoreCase(option.getOptionName());
					if (ct != null) {
						t.setCodingSystemName(option.getOptionValue());
						t.setCodingSystemRegion(option.getOptionRegion());
						t.setCodingSystemLang("EN");
						t.setHasCodingSystem(true);
						t.setIsCode(true);
					}
				}
				if (options.isEmpty()) {
					t.setHasCodingSystem(false);
					t.setIsCode(false);
				}

				t.setAllowedValues(new ArrayList<String>());
				t.setShowValues(new ArrayList<String>());
				filters.add(t);
			}
		}
		return filters;
	}
	/***** END MINE ***/
	
	/*public List<UDTableFilterVO> getDefaultFilter(Long resourceId) {
		List<UDTableFilterVO> filters = getFilters(resourceId);
			
		return filters;
	}*/



	public UDTableVO getTable(List<UDTableFilterVO> filtersVO, String language, boolean addCode) {
		List<UDTableFilter> filters = new ArrayList<UDTableFilter>();
		for (UDTableFilterVO vo : filtersVO) {
			filters.add(UDTableVOConverter.vo2filter(vo));
		}
		UDTable table = udtableConnector.getTablePaged(filters, language, 0, 10, addCode);
		
		return UDTableVOConverter.table2vo(table);
	}

	//paged table
	public UDTableVO getTable(List<UDTableFilterVO> filtersVO, String language, int startIndex, int elements, boolean addCode) {
		List<UDTableFilter> filters = new ArrayList<UDTableFilter>();
		for (UDTableFilterVO vo : filtersVO)
			filters.add(UDTableVOConverter.vo2filter(vo));
		UDTable table = udtableConnector.getTablePaged(filters, language, startIndex, elements, addCode);
		table.setDatasetRecordCount(getRecordSizeForFilter(filters, language, elements));
		
		return UDTableVOConverter.table2vo(table);
	}

	public List<OLAPChartResultVo> createOLAPChartBeans(OLAPParametersVo parameters) {
//		LOGGER.info(parameters.getXLabels().size() + " xLabels");
//		LOGGER.info(parameters.getZLabels().size() + " zLabels");
		List<OLAPChartBeanVO> vos = new ArrayList<OLAPChartBeanVO>();
		OLAPParameters p = OLAPUtils.vo2p(parameters);
		List<OLAPChartBean> beans = udtableDao.createOLAPChartBeans(p);
		
		beans = addNullValues(beans);
		
		OlapChart olapCharts = new OlapChart(beans);
		List<OLAPChartResultVo> iFrameList = olapCharts.createOlapCharts();
		
		return iFrameList;
		
//		for (OLAPChartBean b : beans)
//			vos.add(bean2vo(b));
//		return addNullValues(vos);
	}
	
	private List<OLAPChartBean> addNullValues(List<OLAPChartBean> vos) {
		for (OLAPChartBean vo : vos) {
			int max = 0;
			for (String zKey : vo.getYSeries().keySet()) {
				if (vo.getYSeries().get(zKey).size() > max)
					max = vo.getYSeries().get(zKey).size();
			}
			for (String zKey : vo.getYSeries().keySet()) {
				List<String> yValues = vo.getYSeries().get(zKey);
				if (yValues.size() < max) {
					for (int i = 0; i < (max - yValues.size()); i++)
						yValues.add(null);
				}
			}
		}
		return vos;
	}
	
	private List<OLAPChartBeanVO> addNullValuesVO(List<OLAPChartBeanVO> vos) {
		for (OLAPChartBeanVO vo : vos) {
			int max = 0;
			for (String zKey : vo.getYSeries().keySet()) {
				if (vo.getYSeries().get(zKey).size() > max)
					max = vo.getYSeries().get(zKey).size();
			}
			for (String zKey : vo.getYSeries().keySet()) {
				List<String> yValues = vo.getYSeries().get(zKey);
				if (yValues.size() < max) {
					for (int i = 0; i < (max - yValues.size()); i++)
						yValues.add(null);
				}
			}
		}
		return vos;
	}

	private OLAPChartBeanVO bean2vo(OLAPChartBean b) {
		OLAPChartBeanVO vo = new OLAPChartBeanVO();
		vo.setChartType(b.getChartType());
		vo.setTitle(b.getTitle());
		vo.setXLabel(b.getXLabel());
		vo.setXParameter(b.getXParameter());
		vo.setXValues(b.getXValues());
		vo.setYLabel(b.getYLabel());
		vo.setYParameter(b.getYParameter());
		vo.setYSeries(b.getYSeries());
		return vo;
	}


	public List<UDTableHeaderVO> getTableHeaders(List<UDTableFilterVO> filtersVO, String language) {
		List<UDTableFilter> filters = new ArrayList<UDTableFilter>();
		for (UDTableFilterVO vo : filtersVO)
			filters.add(UDTableVOConverter.vo2filter(vo));
		List<UDTableHeaderVO> headersVO = new ArrayList<UDTableHeaderVO>();
		List<UDTableHeader> headers = udtableDao.getHeaders(filters, language, true);
		for (UDTableHeader item : headers)
			headersVO.add(UDTableVOConverter.header2vo(item));

		return headersVO;
	}

	@SuppressWarnings("deprecation")
	public static String parseMonthlyDate(Date date) {
		int year = 1900 + date.getYear();
		int month = 1 + date.getMonth();
		String y = String.valueOf(year);
		String m = String.valueOf(month);
		System.out.println(year);
		if (month < 10)
			m = "0" + m;
		return y + "-" + m;
	}

	@SuppressWarnings("deprecation")
	public static String parseYearDate(Date date) {
		int year = 1900 + date.getYear();
		String y = String.valueOf(year);
		return y;
	}

	public String createGhostTables() {
		uniqueValuesDao.createGhostTables();
		return null;
	}

	private Integer getRecordSizeForFilter(List<UDTableFilter> filters, String language, Integer elements) {
		return new Integer((int)udtableConnector.getResultSetSize(filters, language, elements));
	}
	
	private Integer numberItemsPerPage() {
		return UDTableConstants.ITEMS_PER_PAGE;
	}
	
	private Integer maxResultSetSize() {
		return UDTableConstants.MAX_RESULT_SET_SIZE;
	}
	
	public Integer getResultSetSizeForVOFilter(List<UDTableFilterVO> filtersVo, String language) {
		List<UDTableFilter> filters = new ArrayList<UDTableFilter>();
		for (UDTableFilterVO vo : filtersVo)
			filters.add(UDTableVOConverter.vo2filter(vo));
	
		return getRecordSizeForFilter(filters, language, maxResultSetSize());
	}
	
	public void setUdtableConnector(UDTableConnector udtableConnector) {
		this.udtableConnector = udtableConnector;
	}

	public void setUdtableDao(UDTableDao udtableDao) {
		this.udtableDao = udtableDao;
	}

	public void setUniqueValuesDao(UniqueValuesDao uniqueValuesDao) {
		this.uniqueValuesDao = uniqueValuesDao;
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

	public void setMeDao(MEDao meDao) {
		this.meDao = meDao;
	}

	public void setFindDao(FindDao findDao) {
		this.findDao = findDao;
	}

	public void setUpdateDao(UpdateDao updateDao) {
		this.updateDao = updateDao;
	}

	public void setAppender(Appender appender) {
		this.appender = appender;
	}

	public void setDeleteDao(DeleteDao deleteDao) {
		this.deleteDao = deleteDao;
	}
	
	public void setCrisisDao(CrisisDao crisisDao) {
		this.crisisDao = crisisDao;
	}
}