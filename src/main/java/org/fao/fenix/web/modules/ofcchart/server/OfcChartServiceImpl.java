package org.fao.fenix.web.modules.ofcchart.server;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.ofcchart.DimensionsBean;
import org.fao.fenix.core.domain.ofcchart.OfcChartBean;
import org.fao.fenix.core.domain.ofcchart.OfcFilter;
import org.fao.fenix.core.domain.ofcchart.OfcParameters;
import org.fao.fenix.core.domain.olap.OLAPFilter;
import org.fao.fenix.core.domain.olap.OLAPParameters;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.persistence.UniqueValuesDao;
import org.fao.fenix.core.persistence.ofcchart.OfcChartDao;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.ofcchart.common.services.OfcChartService;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.DimensionsBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcFilterVo;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcParametersVo;
import org.fao.fenix.web.modules.ofcchart.common.vo.ParametersVo;
import org.fao.fenix.web.modules.ofcchart.common.vo.ValueVO;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class OfcChartServiceImpl extends RemoteServiceServlet implements OfcChartService {
	
	OfcChartDao ofcChartDao;
	
	UniqueValuesDao uniqueValuesDao;
	
	Logger LOGGER = Logger.getLogger(OfcChartServiceImpl.class);

	
	public List<OfcChartBeanVO> getChart(List<OfcParametersVo> parameters) {
		System.out.println("START");
		List<OfcParameters> p = new ArrayList<OfcParameters>(parameters.size());
		for(OfcParametersVo parameter : parameters) 
			p.add(vo2parameters(parameter));
		
		System.out.println("Querying");
		List<List<OfcChartBean>> beans = ofcChartDao.createCube(p);
		System.out.println("BEANS SIZE: " + beans.size());
		for(List<OfcChartBean> ofcBeans : beans) {
				for (OfcChartBean bean : ofcBeans){				
					System.out.println("\nDS: " + bean.getDatasetId() +  "| " + bean.getDatasetName());
					System.out.println("X: " + bean.getxDimension().getDatatype() +  "| " + bean.getxValue());
					System.out.println("Y: " + bean.getyDimension().getDatatype() +  "| " + bean.getyValue());
					if ( bean.getFilterDimension() != null && bean.getFilterValue() != null)
						System.out.println("F: " + bean.getFilterDimension().getDatatype() +  "| " + bean.getFilterValue());
					if ( bean.getQuantity() != null)
						System.out.println("Value: " + bean.getQuantity().toString());
				}
		}
		
		
		List<OfcChartBeanVO> result = ocfChartBean2vo(beans, parameters);
		System.out.println("END");
		
		return result;
	}
	
	public HashMap<String, String> getFirstCode(String datasetId, String header, String lang) {
		HashMap<String, String> results = new HashMap<String, String>();
		UniqueTextValues utv = new UniqueTextValues();
		UniqueDateValues udv = new UniqueDateValues();
		
		
			System.out.println("dimension: " + header + " | " + lang);
			utv = uniqueValuesDao.isValidText(Long.parseLong(datasetId), header, lang);
			
			if ( utv != null) {
				results.put(utv.getValue(), utv.getLabel());
			}
			else {
				udv = uniqueValuesDao.isValidDate(Long.parseLong(datasetId), header);
				if ( udv != null) {
					results.put(udv.getValue().toString(), udv.getDate().toString());
				}
			}
		return results;	
	}
	
	public HashMap<String, List<DimensionsBeanVO>> getXCommonDimensions(List<DatasetVO> datasets) {
		HashMap<String, List<DimensionsBeanVO>> dimensions = new HashMap<String, List<DimensionsBeanVO>>();
		List<DimensionsBean> commonCs = new ArrayList<DimensionsBean>();
		List<DimensionsBean> commonDates = new ArrayList<DimensionsBean>();
		
		LOGGER.info("getXCommonDimensions");
		int i=0;
		List<DimensionsBean> allDimensions = new ArrayList<DimensionsBean>();
		for(DatasetVO dataset :  datasets) {
			LOGGER.info("CODING SYSTEM");
			List<DimensionsBean> c = ofcChartDao.getAllCs(Long.parseLong(dataset.getDsId()));	
			List<DimensionsBean> cs = ofcChartDao.getAllDistinctCs(Long.parseLong(dataset.getDsId()));	
			checkCs(commonCs, cs, i);
			
			LOGGER.info("DATES");
			List<DimensionsBean> dates = ofcChartDao.getAllDates(Long.parseLong(dataset.getDsId()));
			checkDates(commonDates, dates, i);
			allDimensions.addAll(c);
			allDimensions.addAll(dates);
			i++;
		}
		System.out.println("COMMON X AXIS:");
		for(DimensionsBean cs: commonCs ) 
			System.out.println("+ " +cs.getCs_title() + " | " + cs.getCs_region());
		for(DimensionsBean cs: commonDates ) 
			System.out.println("- " + cs.getDatatype() + " | " + cs.getPeriodType());
		
		if ( i == 0 ) {			
			List<DimensionsBeanVO> result = new ArrayList<DimensionsBeanVO>();
			for(DimensionsBean dimension : allDimensions) 			
				result.add(DimensionsBean2DimensionsBeanVO(dimension));
			dimensions.put(allDimensions.get(0).getDs_id(), result);
		}
		else {
			for(DatasetVO dataset :  datasets) {
				List<DimensionsBeanVO> result = new ArrayList<DimensionsBeanVO>();
				for(DimensionsBean cs: commonCs ) {
					for(DimensionsBean dimension : allDimensions) {
						if ( dimension.getDs_id().equals(dataset.getDsId()) && dimension.getCs_title().equals(cs.getCs_title()) && dimension.getCs_region().equals(cs.getCs_region()))
							result.add(DimensionsBean2DimensionsBeanVO(dimension));
					}
				}
				for(DimensionsBean dates : commonDates ) {
					for(DimensionsBean dimension : allDimensions) {
						if ( dimension.getDs_id().equals(dataset.getDsId()) && dimension.getDatatype().equals(dates.getDatatype()) && dimension.getPeriodType().equals(dates.getPeriodType())) 
							result.add(DimensionsBean2DimensionsBeanVO(dimension));					
					}
				}
				dimensions.put(dataset.getDsId(), result);
			}
		
		}
		
		 Set set= dimensions.keySet () ; 
	     Iterator iter = set.iterator() ; 
	     int k=1; 
	     while ( iter.hasNext ())  {  
	    	List<DimensionsBeanVO> d = dimensions.get(iter.next());
	    	for ( DimensionsBeanVO d1 : d) {
	    		 System.out.println ( " "+k+" )  "+ d1.getDs_id() + " | " + d1.getDatatype()) ; 
	    	}
	      
	       k++; 
	      }  

	     return dimensions;
	}
	
	private DimensionsBeanVO DimensionsBean2DimensionsBeanVO(DimensionsBean dimension) {
		DimensionsBeanVO result = new DimensionsBeanVO();
		result.setDs_id(dimension.getDs_id());
		result.setCs_title(dimension.getCs_title());
		result.setCs_region(dimension.getCs_region());
		result.setDatatype(dimension.getDatatype());
		result.setHeader(dimension.getHeader());
		result.setPeriodType(dimension.getPeriodType());
		result.setIsDate(dimension.getIsDate());
		return result;
		
	}
	
	
	private void checkCs(List<DimensionsBean> commonCs, List<DimensionsBean> newCs, int i){
		List<DimensionsBean> filteredCs = new ArrayList<DimensionsBean>();
		if (i == 0)
			commonCs.addAll(newCs);
		else {
			for(int j=0; j < commonCs.size(); j++){
				boolean check = false;
				for(DimensionsBean newCS : newCs){
					if (newCS.getCs_title().equals(commonCs.get(j).getCs_title()) && newCS.getCs_region().equals(commonCs.get(j).getCs_region())){
						check = true;
						break;
					}
				}
				if ( check ) 
					filteredCs.add(commonCs.get(j));
			}
			if ( filteredCs.isEmpty()) 
				LOGGER.info("NO COMMON CS FOUND");
			else
				LOGGER.info("FOUND CS SIZE: " + filteredCs.size());
			commonCs.clear();
			commonCs.addAll(filteredCs);
		}		
	}
	
	private void checkDates(List<DimensionsBean> commonDates, List<DimensionsBean> newCs, int i){
		List<DimensionsBean> filteredCs = new ArrayList<DimensionsBean>();
		if (i == 0)
			commonDates.addAll(newCs);
		else {
			for(int j=0; j < commonDates.size(); j++){
				boolean check = false;
				for(DimensionsBean newCS : newCs){
					if (newCS.getPeriodType().equals(commonDates.get(j).getPeriodType()) && newCS.getDatatype().equals(commonDates.get(j).getDatatype())){
						check = true;
						break;
					}
				}
				if ( check ) 
					filteredCs.add(commonDates.get(j));
			}
			if ( filteredCs.isEmpty()) 
				LOGGER.info("NO COMMON DATES FOUND");
			else
				LOGGER.info("FOUND DATES SIZE: " + filteredCs.size());
			commonDates.clear();
			commonDates.addAll(filteredCs);
		}		
	}
	
	
	
	
	
	
	public OfcChartBeanVO createChart(OLAPParametersVo p) {
//		System.out.println("START");
//		OLAPParameters parameters = vo2parameters(p);
//		System.out.println("Querying");
//		List<OfcChartBean> beans = ofcChartDao.createCube(parameters);
//		System.out.println("BEANS");
//		for (OfcChartBean bean : beans){		
//			System.out.println("X: " + bean.getXValue());
//			System.out.println("Z: " + bean.getZValue());
//			System.out.println("Value: " + bean.getQuantity().toString());
//			System.out.println("\n");
//		}
//		OfcChartBeanVO result = ocfChartBean2vo(beans, p);
//		System.out.println("END");
//		return result;
		return null;
	}
	
	
	public OfcChartBeanVO createChart(List<ParametersVo> p) {
//		System.out.println("START");
//		List<OfcParameters> parameters = new ArrayList<OfcParameters>();
//		for (ParametersVo parameter : p) {
////			parameters.add(vo2parameters(parameter));
//			System.out.println("Querying");
//			System.out.println("END");
//		}
//		List<List<OfcChartBean>> beans = ofcChartDao.createCube(parameters);
//		for(List<OfcChartBean> ofcBeans : beans) {
//				System.out.println("BEANS");
//				for (OfcChartBean bean : ofcBeans){		
//					System.out.println("X: " + bean.getXValue());
//					System.out.println("Y: " + bean.getYValue());
//					System.out.println();
//					if (bean.getQuantity() != null)
//						System.out.println("Value: " + bean.getQuantity().toString());
//					else 
//						System.out.println("Value: NULL");
//					System.out.println("\n");
//				}
//		}
//		List<OfcChartBeanVO> result = ocfChartBean2vo(beans, p);
////		return result;
		return null;
	}
	
	private List<OfcChartBeanVO> ocfChartBean2vo(List<List<OfcChartBean>> beans, List<OfcParametersVo> p) {
		
		List<OfcChartBeanVO> results = new ArrayList<OfcChartBeanVO>();
		
		LOGGER.info("BEAN SIZE: " + beans.size());
		for(List<OfcChartBean> ofcBeans : beans) {
			OfcChartBeanVO vo = new OfcChartBeanVO();
			for(OfcChartBean bean : ofcBeans) {
				for ( OfcParametersVo param : p) {
					if ( !param.getFilters().isEmpty()) {
						if ( param.getDataset().getDsId().equals(bean.getDatasetId())) 
							vo = addFilteredValue(vo, param, bean);
					}
					else 
						vo = addNotFilteredValues(vo, param, bean);
					vo.setxLabels(param.getxLabels());
				}
			}
			results.add(vo);
		}

	
		/** printing hashmaps **/
		LOGGER.info("RESULTS SIZE: " + results.size() + " (one each dataset)");
		
		
		for(OfcChartBeanVO vo : results) {
			if ( vo.isHasFilters()) {
				for(String ykey : vo.getValues().keySet()) {
					System.out.println("Y-KEY: " + ykey);

						for(String fkey : vo.getValues().get(ykey).keySet()) {
							System.out.println("F-KEY: " + fkey);
							List<ValueVO> v =   vo.getValues().get(ykey).get(fkey);
							for(ValueVO value : v){
								if (  value.getValue() != null)	
									System.out.println(fkey + " | " + value.getxLabel() + " | " + value.getValue().toString());
								else 
									System.out.println(fkey + " | " + value.getxLabel() + " -> NULL");
							}
							
						}
					}
			}
			else {
				for(String ykey : vo.getValuesNF().keySet()) {
					System.out.println("Y-KEY: " + ykey);
					List<ValueVO> v =   vo.getValuesNF().get(ykey);
					for(ValueVO value : v){
						if (  value.getValue() != null)	
							System.out.println(ykey + " | " + value.getxLabel() + " | " + value.getValue().toString());
						else 
							System.out.println(ykey + " | " + value.getxLabel() + " -> NULL");
					}
				}
			}
		}
		

		return results;
	}
	
	
	private OfcChartBeanVO addFilteredValue(OfcChartBeanVO vo, OfcParametersVo param, OfcChartBean bean){
	
			HashMap<String, HashMap<String, List<ValueVO>>> y = vo.getValues();
			if( y.containsKey(param.getyLabels().get(bean.getyValue()))) {
				 HashMap<String, List<ValueVO>> values = y.get(param.getyLabels().get(bean.getyValue()));
				 if ( values == null ) {
					 values = new HashMap<String, List<ValueVO>>();
				 }
				/** checking filters **/
				 for(OfcFilterVo filter : param.getFilters()) {
					if(filter.getDimension().getHeader().equals(bean.getFilterDimension().getHeader())) {
						System.out.println("bean.getFilterValue(): " + bean.getFilterValue() + " | " + param.getxLabels().get(bean.getxValue())) ;
						System.out.println("filter.getDimensionMap().get(bean.getFilterValue(): " + filter.getDimensionMap().get(bean.getFilterValue()) + " | " + bean.getQuantity() + " | " + values.containsKey(filter.getDimensionMap().get(bean.getFilterValue())));
							if ( values.containsKey(filter.getDimensionMap().get(bean.getFilterValue()))) {
								List<ValueVO> backup = values.get(filter.getDimensionMap().get(bean.getFilterValue()));
								ValueVO value = new ValueVO();
								value.setValue(bean.getQuantity());
								value.setxLabel(param.getxLabels().get(bean.getxValue()));
								backup.add(value);
								values.put(filter.getDimensionMap().get(bean.getFilterValue()), backup);
								y.put(param.getyLabels().get(bean.getyValue()), values);
											vo.setValues(y);
								System.out.println("valuessize: " + values.size());
							}
							else {
								List<ValueVO> newValue = new ArrayList<ValueVO>();
								ValueVO value = new ValueVO();
								value.setValue(bean.getQuantity());
								value.setxLabel(param.getxLabels().get(bean.getxValue()));
								newValue.add(value);
								values.put(filter.getDimensionMap().get(bean.getFilterValue()), newValue);
								y.put(param.getyLabels().get(bean.getyValue()), values);
								/** ADD LABEL **/
											vo.setValues(y);
							}
						break;
					}
				}
			}
		
			/** the serie doesn't exist **/
			else {
				List<ValueVO> newValue = new ArrayList<ValueVO>();
				ValueVO value = new ValueVO();
				value.setValue(bean.getQuantity());
				value.setxLabel(param.getxLabels().get(bean.getxValue()));
				newValue.add(value);
				HashMap<String, List<ValueVO>> values = new HashMap<String, List<ValueVO>>();
				/** adding value to filter**/
				for(OfcFilterVo filter : param.getFilters()) {
					if(filter.getDimension().getHeader().equals(bean.getFilterDimension().getHeader())) {
						System.out.println("PUTTING: " + value.getxLabel());
						values.put(filter.getDimensionMap().get(bean.getFilterValue()), newValue);
						break;
					}	
				}
				/** adding filter to y **/
				y.put(param.getyLabels().get(bean.getyValue()), values);
				/** ADD LABEL **/
				vo.setValues(y);
			}
		
		vo.setHasFilters(true);
		return vo;
	}
	
	private OfcChartBeanVO addNotFilteredValues(OfcChartBeanVO vo, OfcParametersVo param, OfcChartBean bean){
		
		/** already existing serie **/
		if( vo.getValuesNF().containsKey(param.getyLabels().get(bean.getyValue()))) {
			ValueVO value = new ValueVO(param.getxLabels().get(bean.getxValue()), bean.getQuantity());
			vo.getValuesNF().get(param.getyLabels().get(bean.getyValue())).add(value);
		}
		/** new serie **/
		else {
			List<ValueVO> newValue = new ArrayList<ValueVO>();
			newValue.add(new ValueVO(param.getxLabels().get(bean.getxValue()), bean.getQuantity()));
			vo.getValuesNF().put(param.getyLabels().get(bean.getyValue()), newValue);
		}
		
		System.out.println("valuessize: " + vo.getValuesNF().size());
		
//			List<ValueVO> backup = vo.getValues().get(p.getZLabels().get(bean.getZValue())); 
//			ValueVO value = new ValueVO();
//			value.setValue(bean.getQuantity());
//			value.setxLabel(p.getXLabels().get(bean.getXValue()));
//			backup.add(value);
//
//			o.getValues().put(p.getZLabels().get(bean.getZValue()), backup);
//			}
//		else {
//			List<ValueVO> newValue = new ArrayList<ValueVO>();
//			ValueVO value = new ValueVO();
//			value.setValue(bean.getQuantity());
//			value.setxLabel(p.getXLabels().get(bean.getXValue()));
//			newValue.add(value);
//			o.getValues().put(p.getZLabels().get(bean.getZValue()), newValue);
//		}
//		vo.setXLabel(p.getXLabels().get(bean.getXValue()));
//		vo.setYLabel(p.getZLabels().get(bean.getZValue()));
//		vo.setValues(bean.getQuantity());
//		System.out.println("X: " + p.getXLabels().get(bean.getXValue()));
//		System.out.println("Z: " + p.getZLabels().get(bean.getZValue()));
//		System.out.println("Value: " + bean.getQuantity().toString());
//		System.out.println("\n");
		vo.setHasFilters(false);
		return vo;
	}
	
	private OfcChartBeanVO ocfChartBean2vo(List<OfcChartBean> beans, OLAPParametersVo p) {
		
		List<OfcChartBeanVO> results = new ArrayList<OfcChartBeanVO>();
//
//		System.out.println("BEANS:");
//		List<String> yLabels = new ArrayList<String>();
////		OfcChartBeanVO o = new OfcChartBeanVO();
//		for(OfcChartBean bean : beans){
////			OfcChartBeanVO vo = new OfcChartBeanVO();
////			o.getyLabels().add(p.getZLabels().get(bean.getZValue()));
//			/*** la Serie gia' c'e...va aggiunto un nuovo elemento **/
////			if ( o.getValues().containsKey(p.getZLabels().get(bean.getZValue()))) {
//				List<ValueVO> backup = o.getValues().get(p.getZLabels().get(bean.getZValue())); 
//				ValueVO value = new ValueVO();
//				value.setValue(bean.getQuantity());
//				value.setxLabel(p.getXLabels().get(bean.getXValue()));
//				backup.add(value);
//
////				o.getValues().put(p.getZLabels().get(bean.getZValue()), backup);
//				}
//			else {
//				List<ValueVO> newValue = new ArrayList<ValueVO>();
//				ValueVO value = new ValueVO();
//				value.setValue(bean.getQuantity());
//				value.setxLabel(p.getXLabels().get(bean.getXValue()));
//				newValue.add(value);
////				o.getValues().put(p.getZLabels().get(bean.getZValue()), newValue);
//			}
//			vo.setXLabel(p.getXLabels().get(bean.getXValue()));
//			vo.setYLabel(p.getZLabels().get(bean.getZValue()));
//			vo.setValues(bean.getQuantity());
//			System.out.println("X: " + p.getXLabels().get(bean.getXValue()));
//			System.out.println("Z: " + p.getZLabels().get(bean.getZValue()));
//			System.out.println("Value: " + bean.getQuantity().toString());
//			System.out.println("\n");
//		}
		
//		Set<String> keySet = o.getValues().keySet();
//		
//		for(String key : keySet) {
//			List<ValueVO> values =  o.getValues().get(key);
//			for(ValueVO value : values){
//				if (  value.getValue() != null)	
//					System.out.println(key + " | " + value.getxLabel() + " | " + value.getValue().toString());
//			}
//		}
	
		
	return null;
//		return o;
	}
	
//	private OfcParameters vo2parameters(ParametersVo vo) {
//		
//		OfcParameters p = new OfcParameters();
//		p.setAggregateX(vo.isAggregateX());
//		p.setAggregateY(vo.isAggregateY());
//		p.setAggregateZ(vo.isAggregateZ());
//		p.setDatasetId(vo.getDatasetId());
//		p.setDatasetTitle(vo.getDatasetTitle());
//		
//		List<OfcFilter> filters = new ArrayList<OfcFilter>();
//		for (OfcFilterVo f : vo.getFilters())
//			filters.add(vo2filter(f));
//		p.setFilters(filters);
//		
//		p.setFunction(vo.getFunction());
//		p.setValue(vo.getValue());
//		p.setValueLabel(vo.getValueLabel());
//		p.setW(vo.getW());
//		p.setWLabel(vo.getWLabel());
//		p.setWLabels(vo.getWLabels());
//		p.setX(vo.getX());
//		p.setXLabel(vo.getXLabel());
//		p.setXLabels(vo.getXLabels());
//		p.setY(vo.getY());
//		p.setYLabel(vo.getYLabel());
//		p.setYLabels(vo.getYLabels());
//		p.setZ(vo.getZ());
//		p.setZLabel(vo.getZLabel());
//		p.setZLabels(vo.getZLabels());
//		
//		p.setShowSingleValues(vo.isShowSingleValues());
//		p.setShowTotals(vo.isShowTotals());
//		p.setChartType(vo.getChartType());
//		
//		return p;
//	}
	
//	private OfcFilter vo2filter(OfcFilterVo vo) {
//		OfcFilter f = new OfcFilter();
//		f.setDimension(vo.getDimension());
//		f.setDimensionLabel(vo.getDimensionLabel());
//		f.setDimensionMap(vo.getDimensionMap()); 
//		return f;
//	}
	
	private OfcParameters vo2parameters(OfcParametersVo vo) {
		
		OfcParameters p = new OfcParameters();
		p.setDatasetId(vo.getDataset().getDsId());
		p.setDatasetName(vo.getDataset().getDatasetName());
		
		/** convert filters **/
		List<OfcFilter> filters = new ArrayList<OfcFilter>();
		for (OfcFilterVo f : vo.getFilters())
			filters.add(vo2filter(f));
		p.setFilters(filters);
		
		p.setDatasetType(vo.getDatasetType());
		p.setMeasuramentUnit(vo.getMeasuramentUnit());
		
		/** convert DimensionBean **/
		p.setxDimension(vo2dimensions(vo.getxDimension()));
		p.setxLabels(vo.getxLabels());
		
		/** convert DimensionBean **/
		p.setyDimension(vo2dimensions(vo.getyDimension()));
		p.setyLabels(vo.getyLabels());	
		
		return p;
	}
	

	private OLAPParameters vo2parameters(OLAPParametersVo vo) {
		
		OLAPParameters p = new OLAPParameters();
		p.setDataSourceId(vo.getDataSourceId());
		p.setDataSourceTitle(vo.getDataSourceTitle());
		
		List<OLAPFilter> filters = new ArrayList<OLAPFilter>();
		for (OLAPFilterVo f : vo.getFilters())
			filters.add(vo2filter(f));
		p.setFilters(filters);
		
		p.setFunction(vo.getFunction());
		p.setValue(vo.getValue());
		p.setValueLabel(vo.getValueLabel());
		p.setW(vo.getW());
		p.setWLabel(vo.getWLabel());
		p.setWLabels(vo.getWLabels());
		p.setX(vo.getX());
		p.setXLabel(vo.getXLabel());
		p.setXLabels(vo.getXLabels());
		p.setY(vo.getY());
		p.setYLabel(vo.getYLabel());
		p.setYLabels(vo.getYLabels());
		p.setZ(vo.getZ());
		p.setZLabel(vo.getZLabel());
		p.setZLabels(vo.getZLabels());
		
		p.setShowSingleValues(vo.isShowSingleValues());
		p.setShowTotals(vo.isShowTotals());
		p.setChartType(vo.getChartType());
		
		return p;
	}
	
	public void setOfcChartDao(OfcChartDao ofcChartDao) {
		this.ofcChartDao = ofcChartDao;
	}
	
	private OLAPFilter vo2filter(OLAPFilterVo vo) {
		OLAPFilter f = new OLAPFilter();
		f.setDimension(vo.getDimension());
		f.setDimensionLabel(vo.getDimensionLabel());
		f.setDimensionMap(vo.getDimensionMap()); 
		return f;
	}
	
	private OfcFilter vo2filter(OfcFilterVo vo) {
		OfcFilter f = new OfcFilter();
		f.setDimension(vo2dimensions(vo.getDimension()));
		f.setDimensionMap(vo.getDimensionMap()); 
		return f;
	}
	
	private DimensionsBean vo2dimensions(DimensionsBeanVO vo) {
		DimensionsBean d = new DimensionsBean();
		d.setCs_title(vo.getCs_title());
		d.setCs_region(vo.getCs_region());
		d.setDatatype(vo.getDatatype());
		d.setDs_id(vo.getDs_id());
		d.setHeader(vo.getHeader());
		d.setIsDate(vo.getIsDate());
		d.setPeriodType(vo.getPeriodType());
		return d;
	}
	
	public String saveChart(String image) {
		String nameFile = BirtUtil.randomFileExport() + ".png";

		File file = new File( Setting.getSystemPath() + "/exportObject/" +  nameFile);
		System.out.println("PATH: " + file.getAbsolutePath());
		byte[] imageBytes = Base64.decode(image);
		
		System.out.println("imageBytes: " + imageBytes);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(imageBytes);
			fos.close();
			return nameFile;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/** TODO: FIX TO EXPORT THE IMAGE ON BIRT ***CHANGE IT WITH ANOTHER SERVICE **/
	public List<String> saveCharts(List<String> images) {
		List<String> fileNames = new ArrayList<String>();
		for (String image: images) {
			String nameFile = BirtUtil.randomFileExport() + ".png";
		
			
			LOGGER.info(Setting.getFenixPath());
			LOGGER.info(Setting.getBirtApplName());
			LOGGER.info(nameFile);
			
			File file = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/bangladeshCharts/" + nameFile);
			System.out.println("PATH: " + file.getAbsolutePath());
			byte[] imageBytes = Base64.decode(image);
			
			System.out.println("imageBytes: " + imageBytes);
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(imageBytes);
				fos.close();
				fileNames.add(nameFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileNames;
	}
	

	public void setUniqueValuesDao(UniqueValuesDao uniqueValuesDao) {
		this.uniqueValuesDao = uniqueValuesDao;
	}


	public void waitForChart() {
		
	}



	

	
}
