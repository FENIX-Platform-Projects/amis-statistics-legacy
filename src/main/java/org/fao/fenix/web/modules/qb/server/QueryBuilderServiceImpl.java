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

package org.fao.fenix.web.modules.qb.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.constants.CodingType;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.domain.ofcchart.DimensionsBean;
import org.fao.fenix.core.domain.querybuilder.DimensionItemBean;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.querybuilder.QueryBuilderDao;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.qb.common.services.QueryBuilderService;
import org.fao.fenix.web.modules.qb.common.vo.DatasetVO;
import org.fao.fenix.web.modules.qb.common.vo.DimensionItemVO;
import org.fao.fenix.web.modules.qb.common.vo.DimensionVO;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class QueryBuilderServiceImpl extends RemoteServiceServlet implements QueryBuilderService {
	
    QueryBuilderDao queryBuilderDao;
    DatasetDao datasetDao;
	
	Logger LOGGER = Logger.getLogger(QueryBuilderServiceImpl.class);

	/** 
     * Returns the basic information for each selected dataset
     * 
     * @param datasetIdList 		list of selected dataset IDs
	 * @return  Map<Long, DatasetVO>  where Long   = datasetID 
	 *       		  					  DatasetVO  = all dataset related info including dataset title, 
	 *                                               all dimensions (Descriptors), dimension values etc
	**/	
	public Map<Long, DatasetVO> getDatasetsInfoMap(List<Long> datasetIdList){
		Map<Long, DatasetVO> datasetMap = new HashMap<Long, DatasetVO>(); //key = dataset_id
		
		for(int i=0; i<datasetIdList.size(); i++) {
			Long id = datasetIdList.get(i);
			int aliasIndex = i;
			Dataset dataset = datasetDao.findById(id);
			//RE_INSTATE for language	DatasetVO vo = getDatasetVO(id, CheckLanguage.getLanguage(), dataset);
				DatasetVO vo = getDatasetVO(id, "EN", dataset, aliasIndex);
				datasetMap.put(id, vo);
		}
		/**for(Long id :  datasetIdList) {
			Dataset dataset = datasetDao.findById(id);
		//RE_INSTATE for language	DatasetVO vo = getDatasetVO(id, CheckLanguage.getLanguage(), dataset);
			DatasetVO vo = getDatasetVO(id, "EN", dataset);
			datasetMap.put(id, vo);
		}**/
		
		return datasetMap;
	}
	
	/** 
     * Returns a combined dimension list (excluding quantity) for the selected datasets 
     * The list contains the common dimensions as well as those that are unique to each dataset
     * 
     * @param datasetIds 				  list of selected dataset IDs
	 * @return  Map<String, DimensionVO>  where String   = Coding System Identifier (cs_title+"_"+cs_region or "notitle_noregion") 
	 *                                                          or Date Identifier (period_type+"_"+data_type or "noperiodtype_"+datatype)
	 *       		  					  DimensionVO  = all dimension related info including dataset id, coding system details, header, datatype, period type (as appropriate)
	**/	
	
	public Map<String, DimensionVO> getCombinedDimensionList(List<Long> datasetIdList) {
		HashMap<String, DimensionVO> allDimensions = new HashMap<String, DimensionVO>();
		LOGGER.info("getDimensions start ...");
				
		Map<String, DimensionsBean> dateDimensionMap = queryBuilderDao.getAllDateDimensions(datasetIdList);
		Map<String, DimensionsBean> otherDimensionsMap = queryBuilderDao.getAllNonDateDimensions(datasetIdList);
		Map<String, DimensionsBean> quantityDimensionsMap = queryBuilderDao.getAllQuantityDimensions(datasetIdList);
		
		Iterator<Map.Entry<String, DimensionsBean>> iterator = otherDimensionsMap.entrySet().iterator();
		for (int z = 0; z < otherDimensionsMap.size(); z++) {
			Map.Entry<String,DimensionsBean> dimension = iterator.next();
			String cs_identifier = dimension.getKey(); //cs_identifier = cs_title+"_"+cs_region or if no codingSystem cs_identifier = "notitle_noregion" 
			DimensionsBean bean = dimension.getValue(); //bean.getDs_id() = datasetID, if the dimension is common to more than 1 dataset then bean.getDs_id() = datasetID1_datasetID2
			
			allDimensions.put(cs_identifier, DimensionBean2DimensionVO(bean));
		}
		
		Iterator<Map.Entry<String, DimensionsBean>> iteratorDates = dateDimensionMap.entrySet().iterator();
		for (int z = 0; z < dateDimensionMap.size(); z++) {
			Map.Entry<String,DimensionsBean> dimension = iteratorDates.next();
			String date_identifier = dimension.getKey(); //date_identifier = period_type+"_"+data_type or if no codingSystem date_identifier = "noperiodtype_"+datatype 
			DimensionsBean bean = dimension.getValue(); //bean.getDs_id() = datasetID, if the dimension is common to more than 1 dataset then bean.getDs_id() = datasetID1_datasetID2
			
			
			allDimensions.put(date_identifier, DimensionBean2DimensionVO(bean));
		}
		
		Iterator<Map.Entry<String, DimensionsBean>> iteratorQuantities = quantityDimensionsMap.entrySet().iterator();
		for (int z = 0; z < quantityDimensionsMap.size(); z++) {
			Map.Entry<String,DimensionsBean> dimension = iteratorQuantities.next();
			String quantity_identifier = dimension.getKey(); //quantity_identifier = data_type+"_"+dataset_id 
			DimensionsBean bean = dimension.getValue(); //bean.getDs_id() = datasetID
			
			
			allDimensions.put(quantity_identifier, DimensionBean2DimensionVO(bean));
		}
		
		
	     return allDimensions;
	}
	

	private Map<String, Descriptor> getDatasetDescriptors(Dataset dataset) {
		return GwtConnector.getColumnDescriptors(dataset);
	}
	
	private DatasetVO getDatasetVO(Long resourceId, String language, Dataset dataset, int aliasIndex) {
		DatasetVO vo = new DatasetVO();

		if(dataset!=null){
			vo.setPeriodTypeCode(dataset.getPeriodTypeCode());
			vo.setDatasetTitle(dataset.getTitle());
			vo.setRegion(dataset.getRegion());
			vo.setDatasetType(dataset.getType().name());
			vo.setDatasetExplicitType(dataset.getDatasetType().getTitle());
			vo.setAliasIndex(aliasIndex);

			List<Descriptor> descriptors = dataset.getDatasetType().getDescriptors();
			
			Map<String, Descriptor> columns = new LinkedHashMap<String, Descriptor>(descriptors.size());
			for (Descriptor descriptor : descriptors) {
				columns.put(Long.toString(descriptor.getId()), descriptor);
				//LOGGER.info(descriptor.getHeader() + " added to hashmap.");
			}
			
			Map<String, Descriptor> descriptorsMap = getDatasetDescriptors(dataset);

			vo.setDimensionDescriptorMap(getDimensionDetails(descriptorsMap, resourceId, language));
			vo.setLanguage(language.toUpperCase());		

		} else LOGGER.info("DATASET is NULL");

		return vo;
	}

	private Map<String, DimensionBeanVO> getDimensionDetails(Map<String, Descriptor> descriptors, Long datasetId, String language){

		Map<String, DimensionBeanVO> dimensionMap = new LinkedHashMap<String, DimensionBeanVO>();
		
		Iterator<Map.Entry<String, Descriptor>> iterator = descriptors.entrySet().iterator();
			for (int i = 0; i < descriptors.size(); i++) {
			Map.Entry<String,Descriptor> column = iterator.next();
			//String header = column.getKey();
			String id = column.getKey();
			Descriptor descriptor = column.getValue();
			String header = descriptor.getHeader();
		
			
			List<Option> optionList = descriptor.getOptions();
//			System.out.println("header -----> "  + header);
			
			
			String columnId = "column"+(i+1);// i+1 because column0 = row id
			
//			LOGGER.info("header = "+header + " columnId: " + columnId + " datatype: "+descriptor.getDataType().toString());
			
			DimensionBeanVO dimensionBean = new DimensionBeanVO();
			dimensionBean.setHeader(header);
			dimensionBean.setDescriptorId(id);
			dimensionBean.setColumnDataType(descriptor.getDataType().toString());
			
			for (Option op : optionList){
				if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null){
					LOGGER.info("Found coding " + op);
					dimensionBean.setHasCodingSystem(true);
					CodingSystemVo vo = new CodingSystemVo(op.getOptionValue(), op.getOptionRegion()); 
					dimensionBean.setCodingSystemVO(vo);
					break;
				}
			}
			
			if (dimensionBean.getColumnDataType().equals("date") || dimensionBean.getColumnDataType().equals("baseDateFrom") || dimensionBean.getColumnDataType().equals("baseDateTo")) {
				dimensionBean.setIsDate(true);
				dimensionBean.setHasCodingSystem(false);  
			}
			

			dimensionMap.put(columnId, dimensionBean);
		}
	
		return dimensionMap;
	}
	
	//LANGUAGE HANDLING!!
	/*public List<DimensionItemVO> getDimensionValues(List<Long> datasetIds, String dataType, String codingSystemTitle, String codingSystemRegion){
		return DimensionItemBean2DimensionItemVO(queryBuilderDao.getDimensionValues(datasetIds, dataType, codingSystemTitle, codingSystemRegion));
	}*/
	
	public List<DimensionItemModel> getDimensionValues(List<Long> datasetIds, String dataType, String codingSystemTitle, String codingSystemRegion){
		return DimensionItemBean2DimensionItemModel(queryBuilderDao.getDimensionValues(datasetIds, dataType, codingSystemTitle, codingSystemRegion));
	}
	
	
	private DimensionVO DimensionBean2DimensionVO(DimensionsBean dimension) {
		DimensionVO result = new DimensionVO();
		result.setDs_id(dimension.getDs_id());
		result.setCodingSystemVO(new CodingSystemVo(dimension.getCs_title(), dimension.getCs_region()));
		result.setColumnDataType(dimension.getDatatype());
		result.setHeader(dimension.getHeader());
		LOGGER.info("dimension.getHeader() = "+dimension.getHeader());
		result.setPeriodType(dimension.getPeriodType());
		result.setIsDate(dimension.getIsDate());
		if(dimension.getHasCodingSystem()!=null)
			result.setHasCodingSystem(dimension.getHasCodingSystem());
	
		return result;
		
	}
	
	private List<DimensionItemVO> DimensionItemBean2DimensionItemVO(List<DimensionItemBean> dimensionItemList) {
		List<DimensionItemVO> itemVOList = new ArrayList<DimensionItemVO>();
		
		for(DimensionItemBean item: dimensionItemList)
			itemVOList.add(new DimensionItemVO(item.getLabel(), item.getValue()));
	
			return itemVOList;
	}
	
	private List<DimensionItemModel> DimensionItemBean2DimensionItemModel(List<DimensionItemBean> dimensionItemList) {
		List<DimensionItemModel> itemVOList = new ArrayList<DimensionItemModel>();
		
		for(DimensionItemBean item: dimensionItemList)
			itemVOList.add(new DimensionItemModel(item.getLabel(), item.getValue()));
	
			return itemVOList;
	}
	
	public void setQueryBuilderDao(QueryBuilderDao queryBuilderDao) {
		this.queryBuilderDao = queryBuilderDao;
	}
	
	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	
}
