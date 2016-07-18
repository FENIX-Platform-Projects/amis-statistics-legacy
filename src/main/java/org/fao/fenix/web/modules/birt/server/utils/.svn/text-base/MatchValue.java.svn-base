package org.fao.fenix.web.modules.birt.server.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.server.BirtServiceImpl;

public class MatchValue {

	private static DatasetDao datasetDao;
	
	private static String getLabelPrefix(int datasetNum, int dsCount){
		if (datasetNum > 1 ) return "DS" + dsCount + "-";
		else return "";
	}
	
	public static List<String> getYLabel(ChartWizardBean dataGWT){
		List<String> labels = new ArrayList<String>();
		
		for (int i=0; i<dataGWT.getDatasetId().size(); i++){
			for (List<String> values : dataGWT.getDimensionValuesY().get(dataGWT.getDatasetId().get(i).get(1))){
				System.out.println("getYLabel: " + values.get(1));
				labels.add(getLabelPrefix(dataGWT.getDatasetId().size(),(i+1)) + values.get(1));
			}
		}
		
		return labels;
	}
	
	public static List<List<String>> getYLabelBarLine(ChartWizardBean dataGWT){
		List<List<String>> labels = new ArrayList<List<String>>();
		
		List<String> labelBar = new ArrayList<String>();
		List<String> labelLine = new ArrayList<String>();
		
		for (int i=0; i<dataGWT.getDatasetId().size(); i++){
			if (dataGWT.getChartType().equals("BarLine")){
				for (List<String> values : dataGWT.getDimensionValuesYBar().get(dataGWT.getDatasetId().get(i).get(1))){
					labelBar.add(getLabelPrefix(dataGWT.getDatasetId().size(),(i+1)) + values.get(1));
				}
				for (List<String> values : dataGWT.getDimensionValuesYLine().get(dataGWT.getDatasetId().get(i).get(1))){
					labelLine.add(getLabelPrefix(dataGWT.getDatasetId().size(),(i+1)) + values.get(1));
				}
			} 
		}
		
		labels.add(labelBar);
		labels.add(labelLine);
		
		return labels;
	}
	
	
	public static int getNumValuesY(ChartWizardBean dataGWT){
		int numValuesY=0;
		for (int k=0; k<dataGWT.getDatasetId().size(); k++){
			if (dataGWT.getChartType().equals("BarLine")){
				numValuesY+=dataGWT.getDimensionValuesYBar().get(dataGWT.getDatasetId().get(k).get(1)).size();
				numValuesY+=dataGWT.getDimensionValuesYLine().get(dataGWT.getDatasetId().get(k).get(1)).size();
			} else {
				numValuesY+=dataGWT.getDimensionValuesY().get(dataGWT.getDatasetId().get(k).get(1)).size();
			}
			
		}
		
		return numValuesY;
	}
	

	private static String getDatasource(ChartWizardBean dataGWT, List<List<String>> rowList) {
		String tmp = "";
		String sD = null;
		
		Map<String,String> mapDimX=changeFromListToMapX(dataGWT.getDimensionValuesX());
		
		//for (int i=0; i< dataGWT.getDimensionValuesYForLine().size(); i++){
		//	mapDimY.put((String) ((List)dataGWT.getDimensionValuesYForLine().get(i)).get(0), (String) ((List)dataGWT.getDimensionValuesYForLine().get(i)).get(1));
		//}
		
		
		if (dataGWT.getChartType().equals("Pie")) {
			
			List<String> mapDimY=getYLabel(dataGWT);
			
			//int dim = (dataGWT.getDimensionValuesY().size() + dataGWT.getDimensionValuesYForLine().size()) * rowList.size();
			int dim=MatchValue.getNumValuesY(dataGWT)*dataGWT.getDimensionValuesX().size();
			for (int i = 0; i < dim; i++) {
				if ((i + 1) == dim) {
					tmp += "new Array(" + 3 + ")";
				} else {
					tmp += "new Array(" + 3 + "),";
				}
			}

			sD = "sourcedata = new Array(" + tmp + ");";

//			int count=0;
//			List<Integer> posValueInRowList = new ArrayList<Integer>();
//			for (int i=0; i<dataGWT.getDatasetId().size(); i++){
//				count+=dataGWT.getDimensionValuesY().get(dataGWT.getDatasetId().get(i).get(1)).size();
//				posValueInRowList.add(count);
//			}
			
			int z = 0;
			for (int i = 0; i < ((List) rowList).size(); i++) {
				List tmpList = (List) ((List) rowList).get(i);

				for (int j = 1; j < tmpList.size(); j++) {

					sD += "sourcedata[" + z + "][" + 0 + "]=\""
							+ mapDimX.get(tmpList.get(0)) + "\";";
										
					sD += "sourcedata[" + z + "][" + 1 + "]=\""+ mapDimY.get(j-1) + "\";";
					//sD += "sourcedata[" + z + "][" + 1 + "]=\""+ getPieYLabel(posValueInRowList,j) + mapDimY.get(tmpList.get(j)) + "\";";
					
					sD += "sourcedata[" + z + "][" + 2 + "]=" + tmpList.get(j) + ";";
					//sD += "sourcedata[" + z + "][" + 2 + "]=" + tmpList.get(++j) + ";";

					z++;
				}

			}
		} else {

			//int dim = dataGWT.getDimensionValuesY().size() + dataGWT.getDimensionValuesYForLine().size() + 1;
			int dim=MatchValue.getNumValuesY(dataGWT);
			for (int i = 0; i < rowList.size(); i++) {
				if ((i + 1) == rowList.size()) {
					tmp += "new Array(" + dim + ")";
				} else {
					tmp += "new Array(" + dim + "),";
				}
			}

			sD = "sourcedata = new Array(" + tmp + ");";

			for (int i = 0; i < ((List) rowList).size(); i++) {
				List tmpList = (List) ((List) rowList).get(i);

				for (int j = 0; j < tmpList.size(); j++) {
					if (j == 0) {
						sD += "sourcedata[" + i + "][" + j + "]=\""
								+ mapDimX.get(tmpList.get(j)) + "\";";
					} else {
						sD += "sourcedata[" + i + "][" + j + "]="
								+ tmpList.get(j) + ";";
					}
				}
			}

		}
		System.out.println("rowLIST:\n " + rowList + "\n\n");
		System.out.println("sD:\n " + sD + "\n\n");
		return sD;
	}

	public static List getCodeDimension(List<List<String>> dim){
		List<String> codeList=new ArrayList<String>();
		for (int i=0; i< dim.size(); i++){
			codeList.add(dim.get(i).get(0));
		}
		return codeList;
	}
	
	public static List<String> getLabelDimension(List<List<String>> dim){
		List<String> labelList=new ArrayList<String>();
		for (int i=0; i< dim.size(); i++){
			labelList.add(dim.get(i).get(1));
		}
		return labelList;
	}
	
	public static Map<String,String> changeFromListToMapX(List<List<String>> dim){
		Map<String,String> mapDim=new HashMap<String, String>();
		for (int i=0; i< dim.size(); i++){
			mapDim.put(dim.get(i).get(0), dim.get(i).get(1));
		}
		
		return mapDim;
	}
	
	
	// never used
	public static String getPieYLabel(List<Integer> posValueInRowList, int posValue){
		for (int i=0; i<posValueInRowList.size(); i++){
			if (posValue <= posValueInRowList.get(i)){
				return "DS"+(i+1);
			}
		}
	
		return null;
	}
	
	private static int getIndexRowlist(String XDim,List<List<String>> rowList){
		
		for (int i=0; i < rowList.size(); i++){
			for (int j=0; j < rowList.get(i).size(); j++){
				if (rowList.get(i).get(0).equals(XDim)){
					return i;
				}
			}
		}
		
		return 0;
		
	}
	
	private static void aggregationFunction(String type, Map<String, Double> aggregationMap, String YDim, Double value){
	
		if (type.equals("SUM") || type.equals("AVG")){
			
			if (aggregationMap.containsKey(YDim)) aggregationMap.put(YDim, (aggregationMap.get(YDim) + value));
			else aggregationMap.put(YDim,value);
			
		} else if (type.equals("MIN")){
			
			if (aggregationMap.containsKey(YDim)){
				if (value <  aggregationMap.get(YDim) ) aggregationMap.put(YDim, value);
			} else aggregationMap.put(YDim,value);
			
		} else if (type.equals("MAX")){
			
			if (aggregationMap.containsKey(YDim)){
				if (value >  aggregationMap.get(YDim) ) aggregationMap.put(YDim, value);
			} else aggregationMap.put(YDim,value);
			
		} else if (type.equals("FREQUENCY")){
			
			if (aggregationMap.containsKey(YDim)) aggregationMap.put(YDim, (aggregationMap.get(YDim) + 1));
			else aggregationMap.put(YDim,1D);
					
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<List<String>> getRecords(long datasetId, List<String> columnNames) {
		List originalRowList = datasetDao.getRowValues(datasetId, columnNames);
		// Copy content in a new List: original objects may be unavailable to
		// GWT.
		List<List<String>> rowList = new ArrayList<List<String>>();

		for (Object[] originalRow : (List<Object[]>) originalRowList) {
			List<String> row = new ArrayList<String>(originalRow.length);
			for (Object field : originalRow) {
				if (field == null)
					row.add("");
				else
					row.add(field.toString());
			}
			rowList.add(row);
		}
		return rowList;
	}

	//ETJ: FIXME: this method has too many db accesses
	public static String getValue(ChartWizardBean dataGWT, BirtServiceImpl birtServiceImpl, GwtConnector gwtConnector) {
		List<String> valueCol;
		List<List<String>> rowList = new ArrayList<List<String>>();
		Object l;
		for (List<String> DS : dataGWT.getDatasetId()){
			long idDS = Long.valueOf(DS.get(1)).longValue();
			valueCol = new ArrayList<String>();
			valueCol.add(dataGWT.getDimensionX());
			valueCol.add(dataGWT.getDimensionY().get(DS.get(1)));
//			System.out.println("***> "+dataGWT.getOtherDimension());			
			for (int i=0; i< dataGWT.getOtherDimension().get(DS.get(1)).size(); i++){
//				System.out.println("---> "+dataGWT.getOtherDimension().get(DS.get(1)).get(i));
				if (dataGWT.getOtherDimension().get(DS.get(1)).get(i).get(2).equals("true")) {
//					System.out.println("---> " + dataGWT.getOtherDimension().get(DS.get(1)).get(i));
					valueCol.add(dataGWT.getOtherDimension().get(DS.get(1)).get(i).get(0));
				}
			}
			
			
			if (gwtConnector.isCoreDataset(idDS)){
				valueCol.add(gwtConnector.getColumnNameFromContentDescriptor(idDS, DataType.quantity.name()));
			} else if (gwtConnector.isFlexDataset(idDS)){
				List<String> allDim = gwtConnector.getColumnNames(idDS);
				for (String dim : allDim){
					if (gwtConnector.findContentDescriptorFromColumnName(idDS, dim) == DataType.quantity){
						if (!valueCol.contains(dim)){
							valueCol.add(dim);
							break;
						}
					}
				}
			}
			
						
//			l = birtServiceImpl.getRecords(idDS, valueCol);
			l = getRecords(idDS, valueCol);
		
			
		if (!dataGWT.getChartType().equals("BarLine")){
						
			for (int i = 0; i < dataGWT.getDimensionValuesX().size(); i++) {
				List<String> values = new ArrayList<String>();
				values.add((String) ((List) dataGWT.getDimensionValuesX().get(i)).get(0));
				
				Map<String,Double> aggregationMap = new HashMap<String, Double>();
								
				for (int j = 0; j < dataGWT.getDimensionValuesY().get(DS.get(1)).size(); j++) {
					boolean found = false;
					/*
					if (dataGWT.getChartType().equals("Pie")){
						values.add(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0));
					}
					*/
					int count = 0;
					for (int ib = 0; ib < ((List) l).size(); ib++) {
						List tmpList = (List) ((List) l).get(ib);
						int numOtherDim=tmpList.size()-3;
						
						if (((List) dataGWT.getDimensionValuesX().get(i)).get(0).equals(
								tmpList.get(0))
								&& dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0).equals(
										tmpList.get(1))) {
							
							if (numOtherDim > 0){
								boolean quit=false;
								int contOtherDim=0;
								int contOtherDimMatch=2;
								while (!quit){
									if (dataGWT.getOtherDimension().get(DS.get(1)).get(contOtherDim).get(2).equals("false")){
										contOtherDim++;
									} else if (!dataGWT.getOtherDimension().get(DS.get(1)).get(contOtherDim).get(1).equals(tmpList.get(contOtherDimMatch))){
										quit=true;
									}else {
										contOtherDim++;
										contOtherDimMatch++;
										if ((contOtherDimMatch+1)==tmpList.size()){
											found=true;
											break;
										}
										
									}
																
								}
							} else {
								found=true;
							}
							
						}
						if (found){
							count++;
							if (!((String)tmpList.get(tmpList.size()-1)).equals("null") && !((String)tmpList.get(tmpList.size()-1)).equals("")){
								if (aggregationMap.containsKey(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0))){
//									MatchValue.aggregationFunction(dataGWT.getAggregation(), aggregationMap, dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
																	
									if (dataGWT.getAggregation().equals("SUM") || dataGWT.getAggregation().equals("AVG")){
										aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), (aggregationMap.get(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0)) + Double.valueOf((String)tmpList.get(tmpList.size()-1))));
									} else if (dataGWT.getAggregation().equals("MIN")){
										if (Double.valueOf((String)tmpList.get(tmpList.size()-1)) <  aggregationMap.get(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0)) )
											aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									} else if (dataGWT.getAggregation().equals("MAX")){
										if (Double.valueOf((String)tmpList.get(tmpList.size()-1)) >  aggregationMap.get(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0)) )
											aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									} else if (dataGWT.getAggregation().equals("FREQUENCY")){
										aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), (aggregationMap.get(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0)) + 1));
									}
									//aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), aggregationMap.get(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0))+ Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									} else {
										if (dataGWT.getAggregation().equals("SUM") || dataGWT.getAggregation().equals("AVG"))
											aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0),Double.valueOf((String)tmpList.get(tmpList.size()-1)));
										else if (dataGWT.getAggregation().equals("MIN"))
											aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0),Double.valueOf((String)tmpList.get(tmpList.size()-1)));
										else if (dataGWT.getAggregation().equals("MAX"))								
											aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0),Double.valueOf((String)tmpList.get(tmpList.size()-1)));
										else if (dataGWT.getAggregation().equals("FREQUENCY"))
											aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0),1D);
																					
										//aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									}
								//values.add((String) tmpList.get(tmpList.size()-1));
								//break;
							}
							
						}
						
						if ((ib + 1) == ((List) l).size()) {
							if (!aggregationMap.containsKey(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0))) aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0),null);  
							//values.add("null");
						}
						
						found = false;
					}
					
					if (dataGWT.getAggregation().equals("AVG"))
						if (aggregationMap.get(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0)) != null){
							aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), aggregationMap.get(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0))/count);
						}
					
					
				}
				
				for (List<String> keyYAxis : dataGWT.getDimensionValuesY().get(DS.get(1))){
					if (aggregationMap.get(keyYAxis.get(0))==null) values.add("null");
					else values.add(String.valueOf(aggregationMap.get(keyYAxis.get(0))));
				}
				
				rowList.add(values);
				
			}
		} else if (dataGWT.getChartType().equals("BarLine")){
			
			for (int i = 0; i < dataGWT.getDimensionValuesX().size(); i++) {
				List<String> values = new ArrayList<String>();
				values.add((String) ((List) dataGWT.getDimensionValuesX().get(i)).get(0));
				
				Map<String,Double> aggregationMap = new HashMap<String, Double>();

				for (int j = 0; j < dataGWT.getDimensionValuesYBar().get(DS.get(1)).size(); j++) {
					boolean found = false;
					
					int count = 0;
					for (int ib = 0; ib < ((List) l).size(); ib++) {
						List tmpList = (List) ((List) l).get(ib);
						int numOtherDim=tmpList.size()-3;
						if (((List) dataGWT.getDimensionValuesX().get(i)).get(0).equals(
								tmpList.get(0))
								&& dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0).equals(
										tmpList.get(1))) {
							
							if (numOtherDim > 0){
								boolean quit=false;
								int contOtherDim=0;
								int contOtherDimMatch=2;
								while (!quit){
									if (dataGWT.getOtherDimension().get(DS.get(1)).get(contOtherDim).get(2).equals("false")){
										contOtherDim++;
									} else if (!dataGWT.getOtherDimension().get(DS.get(1)).get(contOtherDim).get(1).equals(tmpList.get(contOtherDimMatch))){
										quit=true;
									}else{
										contOtherDim++;
										contOtherDimMatch++;
										if ((contOtherDimMatch+1)==tmpList.size()){
											found=true;
											break;
										}
										
									}
																
								}
							} else {
								found=true;
							}
							
						}
						if (found){
							count++;
							if (!((String)tmpList.get(tmpList.size()-1)).equals("null")){
												
							 if (aggregationMap.containsKey(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0))){
//																								
								if (dataGWT.getAggregation().equals("SUM") || dataGWT.getAggregation().equals("AVG")){
									aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0), (aggregationMap.get(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0)) + Double.valueOf((String)tmpList.get(tmpList.size()-1))));
								} else if (dataGWT.getAggregation().equals("MIN")){
									if (Double.valueOf((String)tmpList.get(tmpList.size()-1)) <  aggregationMap.get(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0)) )
										aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
								} else if (dataGWT.getAggregation().equals("MAX")){
									if (Double.valueOf((String)tmpList.get(tmpList.size()-1)) >  aggregationMap.get(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0)) )
										aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
								} else if (dataGWT.getAggregation().equals("FREQUENCY")){
									aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0), (aggregationMap.get(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0)) + 1));
								}
								//aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), aggregationMap.get(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0))+ Double.valueOf((String)tmpList.get(tmpList.size()-1)));
							 } else {
									if (dataGWT.getAggregation().equals("SUM") || dataGWT.getAggregation().equals("AVG"))
										aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0),Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									else if (dataGWT.getAggregation().equals("MIN"))
										aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0),Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									else if (dataGWT.getAggregation().equals("MAX"))								
										aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0),Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									else if (dataGWT.getAggregation().equals("FREQUENCY"))
										aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0),1D);
																				
									//aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
							 }
							
							//values.add((String) tmpList.get(tmpList.size()-1));
							//break;
						  }
							
						}
						
						if ((ib + 1) == ((List) l).size()) {
							if (!aggregationMap.containsKey(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0))) aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0),null);
						}
						
						found = false;
					}
					
					if (dataGWT.getAggregation().equals("AVG"))
						if (aggregationMap.get(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0)) != null){
							aggregationMap.put(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0), aggregationMap.get(dataGWT.getDimensionValuesYBar().get(DS.get(1)).get(j).get(0))/count);
						}
				}

				for (List<String> keyYAxis : dataGWT.getDimensionValuesYBar().get(DS.get(1))){
					if (aggregationMap.get(keyYAxis.get(0))==null) values.add("null");
					else values.add(String.valueOf(aggregationMap.get(keyYAxis.get(0))));
				}
				
				rowList.add(values);
			}
			
			for (int i = 0; i < dataGWT.getDimensionValuesX().size(); i++) {
				
				Map<String,Double> aggregationMap = new HashMap<String, Double>();	
				
				for (int j = 0; j < dataGWT.getDimensionValuesYLine().get(DS.get(1)).size(); j++) {
					boolean found = false;
					
										
					int count = 0;
					for (int ib = 0; ib < ((List) l).size(); ib++) {
						List tmpList = (List) ((List) l).get(ib);
						int numOtherDim=tmpList.size()-3;
						if (((List) dataGWT.getDimensionValuesX().get(i)).get(0).equals(
								tmpList.get(0))
								&& dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0).equals(
										tmpList.get(1))) {
							
							if (numOtherDim > 0){
								boolean quit=false;
								int contOtherDim=0;
								int contOtherDimMatch=2;
								while (!quit){
									if (dataGWT.getOtherDimension().get(DS.get(1)).get(contOtherDim).get(2).equals("false")){
										contOtherDim++;
									} else if (!((List)dataGWT.getOtherDimension().get(DS.get(1)).get(contOtherDim)).get(1).equals(tmpList.get(contOtherDimMatch))){
										quit=true;
									}else{
										contOtherDim++;
										contOtherDimMatch++;
										if ((contOtherDimMatch+1)==tmpList.size()){
											found=true;
											break;
										}
										
									}
																
								}
							} else {
								found=true;
							}
														
							
						}
						if (found){
							count++;
							if (!((String)tmpList.get(tmpList.size()-1)).equals("null")){
											
							 if (aggregationMap.containsKey(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0))){
//																								
								if (dataGWT.getAggregation().equals("SUM") || dataGWT.getAggregation().equals("AVG")){
									aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0), (aggregationMap.get(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0)) + Double.valueOf((String)tmpList.get(tmpList.size()-1))));
								} else if (dataGWT.getAggregation().equals("MIN")){
									if (Double.valueOf((String)tmpList.get(tmpList.size()-1)) <  aggregationMap.get(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0)) )
										aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
								} else if (dataGWT.getAggregation().equals("MAX")){
									if (Double.valueOf((String)tmpList.get(tmpList.size()-1)) >  aggregationMap.get(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0)) )
										aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
								} else if (dataGWT.getAggregation().equals("FREQUENCY")){
									aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0), (aggregationMap.get(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0)) + 1));
								}
								//aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), aggregationMap.get(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0))+ Double.valueOf((String)tmpList.get(tmpList.size()-1)));
								} else {
									if (dataGWT.getAggregation().equals("SUM") || dataGWT.getAggregation().equals("AVG"))
										aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0),Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									else if (dataGWT.getAggregation().equals("MIN"))
										aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0),Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									else if (dataGWT.getAggregation().equals("MAX"))								
										aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0),Double.valueOf((String)tmpList.get(tmpList.size()-1)));
									else if (dataGWT.getAggregation().equals("FREQUENCY"))
										aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0),1D);
																				
									//aggregationMap.put(dataGWT.getDimensionValuesY().get(DS.get(1)).get(j).get(0), Double.valueOf((String)tmpList.get(tmpList.size()-1)));
								}
							
							//rowList.get(getIndexRowlist((String)tmpList.get(0),rowList)).add((String) tmpList.get(tmpList.size()-1));
							
						  }
							 
						}
							
						if ((ib + 1) == ((List) l).size()) {
							if (!aggregationMap.containsKey(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0))) aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0),null);
							//rowList.get(getIndexRowlist(dataGWT.getDimensionValuesX().get(i).get(0),rowList)).add("null");
						}
						
						found = false;
					}
					
					if (dataGWT.getAggregation().equals("AVG"))
						if (aggregationMap.get(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0)) != null){
							aggregationMap.put(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0), aggregationMap.get(dataGWT.getDimensionValuesYLine().get(DS.get(1)).get(j).get(0))/count);
						}
				}
				
				for (List<String> keyYAxis : dataGWT.getDimensionValuesYLine().get(DS.get(1))){
					if (aggregationMap.get(keyYAxis.get(0))==null) rowList.get(getIndexRowlist(dataGWT.getDimensionValuesX().get(i).get(0),rowList)).add("null");
					else rowList.get(getIndexRowlist(dataGWT.getDimensionValuesX().get(i).get(0),rowList)).add(String.valueOf(aggregationMap.get(keyYAxis.get(0))));
				}
			}
					
		 } //end check chartType
			
	  } //end loop dataset
		 
	
	  if (dataGWT.getDatasetId().size()>1){
		rowList = moveAllYsToTheSameX(rowList);
	  }
		
		
	  return getDatasource(dataGWT, rowList);
	}
	
	private static List<List<String>> moveAllYsToTheSameX(List<List<String>> rowList){
		
		List<List<String>> newRowList = new ArrayList<List<String>>(); 
		List<String> xAlreadyParsed = new ArrayList<String>();
		List<String> element;
		for (int i=0; i<rowList.size(); i++){
			if (!xAlreadyParsed.contains(rowList.get(i).get(0))){
				element = new ArrayList<String>();
				for(String value: rowList.get(i)){
					element.add(value);
				}
				
				for (int j=(i+1); j<rowList.size(); j++){
					if (rowList.get(j).get(0).equals(rowList.get(i).get(0))){
						for (int z=1; z<rowList.get(j).size(); z++){
							element.add(rowList.get(j).get(z));
						}
					}
				}
				
				xAlreadyParsed.add(rowList.get(i).get(0));
			
				newRowList.add(element);
				
			}
		}
		
		return newRowList;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}
	

}
