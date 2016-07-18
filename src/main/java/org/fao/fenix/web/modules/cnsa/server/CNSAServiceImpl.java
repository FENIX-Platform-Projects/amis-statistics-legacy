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
package org.fao.fenix.web.modules.cnsa.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.cnsa.CNSAByDateBean;
import org.fao.fenix.core.domain.cnsa.CNSAByDateRowBean;
import org.fao.fenix.core.domain.constants.CodingType;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.dataset.Option;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.cnsa.CNSADao;
import org.fao.fenix.web.modules.cnsa.common.services.CNSAService;
import org.fao.fenix.web.modules.core.server.utils.EmptyDatumLabel;
import org.fao.fenix.web.modules.venn.common.vo.GridHeaderVO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CNSAServiceImpl extends RemoteServiceServlet implements CNSAService {
	
	CNSADao cnsaDao;
	
	DatasetDao datasetDao;
	
	CodecDao codecDao;
	
	CreateCNSAReportByDate report;
	
	CNSAExportExcel cnsaExportExcel;
	
	private static final Logger LOGGER = Logger.getLogger(CNSAServiceImpl.class);

	public HashMap<String, String> getReportInfo(String datasetCode, String type, String language) {
		LOGGER.info("querying: " + datasetCode + " | "+ type) ;
		HashMap<String, String> result = new HashMap<String, String>();
		
		if ( type.equals("bydate")) 
			result = cnsaDao.getReportDates(datasetCode);
		
		LOGGER.info("result: " + result);
		return result;
	}


	
	public String getReport(String datasetCode, String type, String value, String reportType, String format, String language, String height, String width) {
		CNSAByDateBean cnsaBean = new CNSAByDateBean();
		Boolean export = false;
		
		if ( reportType.equals("export"))
			export = true;
		
		if (type.equals("bydate")) 
			cnsaBean = getByDateBean(datasetCode, type, value, language);
		

		/// create report
		report = new CreateCNSAReportByDate(cnsaBean, height, width);		
		return report.createReport(export);
//		List<List<String>> table = createExportExcelTable(cnsaBean, language);
//		return report.createTable(table);
	}
	
	public String exportExcel(String datasetCode, String type, String value, String language) {
		CNSAByDateBean cnsaBean = new CNSAByDateBean();
		
		if (type.equals("bydate")) 
			cnsaBean = getByDateBean(datasetCode, type, value, language);
		
		List<List<String>> table = createExportExcelTable(cnsaBean, language);
		
		return cnsaExportExcel.createExcel("Report - " + value, table);
	}


	private CNSAByDateBean getByDateBean(String datasetCode, String type, String date,  String language) {
		LOGGER.info("language: " + language);
		CNSAByDateBean cnsaBean = new CNSAByDateBean();
		cnsaBean.setDate(date);
		
		// select dinamically the dataset
		Dataset dataset = datasetDao.findByCode(datasetCode);
		
		List<Descriptor> descriptors = dataset.getDatasetType().getDescriptors();
		List<GridHeaderVO> gridHeadersVO = new ArrayList<GridHeaderVO>();
		List<String> headers = new ArrayList<String>();
		headers.add("ID");
		
		Integer marketIdx = 0;
		Integer commodityIdx = 0;
		Integer quantityIdx = 0;
		Integer measurementUnitIdx = 0;
		
		int i=1;
		for( Descriptor header : descriptors) {
			GridHeaderVO gridHeader = new GridHeaderVO();
			gridHeader.setDataType(header.getDataType().toString());
			gridHeader.setHeader(header.getHeader());
			gridHeadersVO.add(gridHeader);
			
		
			headers.add(header.getHeader());
			
			if ( header.getDataType().toString().equals("firstIndicator")) {
				System.out.println("commodityIdx: " + header.getHeader());
				commodityIdx = i;
			}
			else if ( header.getDataType().toString().equals("featureCode")) {
				marketIdx = i;
			}
			else if ( header.getDataType().toString().equals("quantity")){
				System.out.println("quantity: " + header.getHeader());
				quantityIdx = i;
			}
			else if ( header.getDataType().toString().equals("secondIndicator")) {
				System.out.println("secondInd: " + header.getHeader());
				measurementUnitIdx = i;
			}
				
			i++;
		}
		
		LOGGER.info("commodityIdx: " + commodityIdx);
		LOGGER.info("marketIdx: " + marketIdx);
		LOGGER.info("quantityIdx: " + quantityIdx);
		LOGGER.info("measurementUnitIdx: " + measurementUnitIdx);
		
		List<DataType> contentDescriptorList = GwtConnector.getContentDescriptorsFromColumnNames(dataset, headers);
		System.out.println("--> " + contentDescriptorList.size());
		
		for(DataType datatype : contentDescriptorList)
			System.out.println("" + datatype.toString());
		
		if( dataset != null) {
			HashMap<String, List<String>> filterCriteria = new HashMap<String, List<String>>();
			List<String> filterList = new ArrayList<String>();
			filterList.add(date);
				
			filterCriteria.put("date", filterList);
			List<List<String>> rawRows = new ArrayList<List<String>>();
			List<List<String>> rows = getFilteredRecords(dataset, headers, filterCriteria, language.toUpperCase(), rawRows, cnsaBean, commodityIdx, marketIdx, quantityIdx, measurementUnitIdx);
			System.out.println("ROWS " + rows);
			System.out.println("RAW ROWS " + rawRows);
			System.out.println("headerList: " + headers);				
			fillCNSAByDateBean(cnsaBean, rows, marketIdx, commodityIdx, quantityIdx, measurementUnitIdx);
		}

		return cnsaBean;
	}
	
	
	private List<List<String>> getFilteredRecords(Dataset dataset, List<String> headerList, Map<String, List<String>> filterCriteria, String language, List<List<String>> rawRows, CNSAByDateBean cnsaBean, Integer commodityIdx, Integer marketIdx, Integer quantityIdx, Integer measurementUnitIdx) {
		
		List<Object[]> originalRowList = datasetDao.getFilteredRowValues(dataset, headerList, filterCriteria);
		
		List<List<String>> rowList = new ArrayList<List<String>>();
		
		for (int i = 0; i < originalRowList.size(); i++) {
			List<String> row = new ArrayList<String>();
			for (int j = 0; j < originalRowList.get(i).length; j++)
				// if for core dataset
				if (originalRowList.get(i)[j] != null){
					// FIXME if for flex dataset
					if (!originalRowList.get(i)[j].equals("null"))
						row.add(originalRowList.get(i)[j].toString());
					else row.add(EmptyDatumLabel.getLabel());
				}				
				else
					row.add(EmptyDatumLabel.getLabel());
			rowList.add(row);
				
		}
		
		List<List<String>> rowListResult = new ArrayList<List<String>>();
	
		//--- cache some info for each column
		
		int columns = headerList.size();
		//LOGGER.info("------------- BEFORE columns =  "+ columns );
		long t0 = System.currentTimeMillis();
		DataType	colTypes[]		= new DataType[columns];
		Option		codingOptions[]	= new Option[columns];
		HashMap		cachedCodes[]	= new HashMap[columns];
		for (int j = 0; j < columns; j++) {
			String header = headerList.get(j);
//			LOGGER.info("------getFilteredRecords: header = " + header);
			if(!header.equals("ID")){	
			
				Descriptor descriptor = GwtConnector.getDescriptorFromColumnName(dataset, header);
				if(descriptor == null)
					throw new IllegalArgumentException("Could not find column '"+header+"' in dataset '"+dataset.getTitle()+"' (id:"+dataset.getResourceId()+")");
				// datatype
				colTypes[j] = descriptor.getDataType();
				// coding option, if any
				List<Option> optionList = descriptor.getOptions();

				codingOptions[j] = null; // init
				for (Option op : optionList){
					if (CodingType.valueOfIgnoreCase(op.getOptionName()) != null){
						codingOptions[j] = op;
						LOGGER.info("Found coding " + op.getOptionName() + " | " + op.getOptionRegion() + " | " + op.getOptionValue());
						break;
					}
				}
			}
			// init maps
			cachedCodes[j] = new HashMap(60); // ??? this doesnt work ---> toIndex-fromIndex+5);
		}
		long t1 = System.currentTimeMillis();

		
		//--- transcode codes	
		for (int rowidx = 0; rowidx < rowList.size(); rowidx++){
			List<String> rowResult = new ArrayList<String>(columns);
			List<String> rowRawResult = new ArrayList<String>(columns);
				
			for (int colidx = 0; colidx < columns; colidx++) {
				
				String cellvalue = rowList.get(rowidx).get(colidx);
			
				rowRawResult.add(colidx, cellvalue);
				
				if(colidx == 0 /*&& dataset.getType() != Type.Flex*/){
					rowResult.add(cellvalue);
			    }
				else {
					try {
						if(colTypes[colidx] == DataType.date){
								cellvalue = GwtConnector.formatDate(dataset, cellvalue);;
						}	
						else {
							Option codingOption = null;	
							codingOption = codingOptions[colidx];	

							
							if(codingOption != null) { // if option is null, no decoding is needed
								String cached = (String)cachedCodes[colidx].get(cellvalue);
								if(cached == null) { // not in cache yet: retrieve the label
									cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), language);
									if ( cached.equals(cellvalue) && !language.equals("EN")) {
										cached = codecDao.getLabelFromCodeCodingSystem(cellvalue, codingOption.getOptionValue(), codingOption.getOptionRegion(), "EN");
									}
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

			
			rowListResult.add(rowResult);
			rawRows.add(rowRawResult);
		}
		long t2 = System.currentTimeMillis();
		//LOGGER.info("getRecords: decode: " + (t2-t1) + "ms" );

		return rowListResult;
	}
	
	
	private void fillCNSAByDateBean(CNSAByDateBean cnsaBean, List<List<String>> rowResult, Integer marketIdx, Integer commodityIdx, Integer quantityIdx, Integer measurementUnitIdx) {
		LOGGER.info("rowResult: " + rowResult);
		HashMap<String, String> marketsList = new HashMap<String, String>();
		

		for(List<String> row : rowResult) {
			LOGGER.info("row: " + row);
			String commodity = row.get(commodityIdx);
			String measurementUnit = row.get(measurementUnitIdx);
			String market = row.get(marketIdx);
			String value = row.get(quantityIdx);
			
			CNSAByDateRowBean rowBean = new CNSAByDateRowBean();
			if ( cnsaBean.getRows().containsKey(commodity)) {
				rowBean = cnsaBean.getRows().get(commodity);
			}

			
			try {
				marketsList.put(market, market);
				rowBean.getMarketsValue().put(market, Double.valueOf(value));
			}
			
			catch (Exception e) {
				LOGGER.warn("value is null");
			}
			
			rowBean.setMeasurmentUnit(measurementUnit);
			
			cnsaBean.getRows().put(commodity, rowBean);
		}

		cnsaBean.setMarkets(marketsList);
	}
	
	private List<List<String>> createExportExcelTable(CNSAByDateBean cnsaBean, String language) {
		List<List<String>> table = new ArrayList<List<String>>();
		List<String> headers = new ArrayList<String>();
		
		headers.add("Produits");
		headers.add("Unité de mesure");
		
		for(String key : cnsaBean.getMarkets().keySet()) {
		
			headers.add(cnsaBean.getMarkets().get(key));
		}
		
		table.add(headers);
		
	
				
		// create commodities
		int j=2;
		for(String commodityKey : cnsaBean.getRows().keySet()){
			List<String> row = new ArrayList<String>();
			
			// add commodity 
			row.add(commodityKey);
			CNSAByDateRowBean rowBean = cnsaBean.getRows().get(commodityKey);
			
			// add measuremant unit
			row.add(rowBean.getMeasurmentUnit());
			
			// add provinces values
			int z=2;
			for(String key : cnsaBean.getMarkets().keySet()) {
				if ( rowBean.getMarketsValue().get(key) != null )
					row.add(String.valueOf(rowBean.getMarketsValue().get(key)));
				else
					row.add("");
				z++;
			}
			j++;
			table.add(row);
		}
		
		return table;
	}
	
	
	

	public void setCnsaDao(CNSADao cnsaDao) {
		this.cnsaDao = cnsaDao;
	}



	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}



	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}



	public void setCnsaExportExcel(CNSAExportExcel cnsaExportExcel) {
		this.cnsaExportExcel = cnsaExportExcel;
	}







	


	
}