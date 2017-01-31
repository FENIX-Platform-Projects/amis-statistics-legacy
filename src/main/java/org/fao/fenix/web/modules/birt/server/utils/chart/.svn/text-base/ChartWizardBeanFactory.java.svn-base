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
package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fenix.core.domain.perspective.ChartView;
import org.fao.fenix.core.domain.perspective.Scales;
import org.fao.fenix.core.domain.perspective.SelectedField;
import org.fao.fenix.core.domain.perspective.SelectedValue;
import org.fao.fenix.core.domain.perspective.SelectedValueBar;
import org.fao.fenix.core.domain.perspective.SelectedValueLine;
import org.fao.fenix.core.domain.perspective.charting.OtherYDimension;
import org.fao.fenix.core.domain.perspective.charting.YDimension;
import org.fao.fenix.core.domain.perspective.charting.YDimensionBarLine;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.persistence.UniqueValuesDao;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.DateVo;

public class ChartWizardBeanFactory {
	
	private static final Logger LOGGER = Logger.getLogger(ChartWizardBeanFactory.class);

	public ChartWizardBean fill(ChartView chartView, ChartWizardBean chartWizardBean, UniqueValuesDao uniqueValuesDao) {
		
		LOGGER.info("START");
		
		
		for(YDimension dimension : chartView.getSelectedFieldsY()) {
			LOGGER.info("***********************************");
			LOGGER.info("List<Ydimensions>: " + dimension.getId() + " | " + dimension.getDataset().getTitle() + " | " + dimension.getName());
			
			LOGGER.info("SELECTED FIELD: " + dimension.getSelectedField().getId() + " | " + dimension.getSelectedField().getFieldName());
			
			for(SelectedValue value : dimension.getSelectedField().getSelectedValues() ){
				LOGGER.info("SELECTED VALUE: " + value.getId() + " | " + value.getSelectedValue());
			}
//			for(Sele)
			LOGGER.info("-----------------------------------");
		}
		
		
		LOGGER.info("END");
		
		chartWizardBean.setAggregation(chartView.getAggregation());
		chartWizardBean.setMostRecent(chartView.isMostRecent());
		
		if (chartWizardBean.isMostRecent())
			chartWizardBean.setMostRecent(new DateVo(chartView.getYears(), chartView.getMonths(), chartView.getDays()));
		else
			chartWizardBean.setMostRecent(new DateVo("0", "0", "0"));

		chartWizardBean.setDimensionX(chartView.getSelectedFieldsX().getFieldName());
		List<List<String>> dimensionValuesX = new ArrayList<List<String>>();
		List<String> elementValuesX;
		
		for (SelectedValue selectedValue : chartView.getSelectedFieldsX().getSelectedValues()) {
			elementValuesX = new ArrayList<String>();
			elementValuesX.add(selectedValue.getSelectedValue());
			elementValuesX.add(selectedValue.getSelectedValue());
			dimensionValuesX.add(elementValuesX);
		}

		chartWizardBean.setDimensionValuesX(dimensionValuesX);

		List<List<String>> datasetId = new ArrayList<List<String>>();
		List<String> elementDS;
		Map<String, String> dimensionY = new HashMap<String, String>();
		Map<String, List<List<String>>> dimensionValuesY = new HashMap<String, List<List<String>>>();
		List<List<String>> dimensionValuesYList = new ArrayList<List<String>>();
		Map<String, List<List<String>>> dimensionValuesYBar = new HashMap<String, List<List<String>>>();
		List<List<String>> dimensionValuesYBarList = new ArrayList<List<String>>();
		Map<String, List<List<String>>> dimensionValuesYLine = new HashMap<String, List<List<String>>>();
		List<List<String>> dimensionValuesYLineList;
		List<String> elementValues = new ArrayList<String>();
		Map<String, List<List<String>>> otherDimensionMap = new HashMap<String, List<List<String>>>();
		List<List<String>> dimensionValuesOthList;


		if (chartView.getChartType().equals("BarLine")) {

//			dimensionValuesYBarList = new ArrayList<List<String>>();
			for (YDimensionBarLine yDimensionBarLine : chartView.getSelectedFieldsYBarLine()) {
				dimensionY.put(String.valueOf(yDimensionBarLine.getDataset().getResourceId()), yDimensionBarLine.getSelectedField().getFieldName());
				elementDS = new ArrayList<String>();
				elementDS.add(yDimensionBarLine.getDataset().getTitle());
				elementDS.add(String.valueOf(yDimensionBarLine.getDataset().getResourceId()));
				datasetId.add(elementDS);
				dimensionValuesYBarList = new ArrayList<List<String>>();
				for (SelectedValueBar selectedValue : yDimensionBarLine.getSelectedField().getSelectedBarValues()) {
//					dimensionValuesYBarList = new ArrayList<List<String>>();
					elementValues = new ArrayList<String>();
					elementValues.add(selectedValue.getSelectedValue());
					elementValues.add(selectedValue.getSelectedValue());
					dimensionValuesYBarList.add(elementValues);
				}

				dimensionValuesYBar.put(String.valueOf(yDimensionBarLine.getDataset().getResourceId()), dimensionValuesYBarList);

				dimensionValuesYLineList = new ArrayList<List<String>>();
				for (SelectedValueLine selectedValue : yDimensionBarLine.getSelectedField().getSelectedLineValues()) {
					elementValues = new ArrayList<String>();
					elementValues.add(selectedValue.getSelectedValue());
					elementValues.add(selectedValue.getSelectedValue());
					dimensionValuesYLineList.add(elementValues);
				}

				dimensionValuesYLine.put(String.valueOf(yDimensionBarLine.getDataset().getResourceId()), dimensionValuesYLineList);

			}

			chartWizardBean.setDimensionValuesYBar(dimensionValuesYBar);
			chartWizardBean.setDimensionValuesYLine(dimensionValuesYLine);

		} else {

			System.out.println("GETTING DIMENSION Y SIZE: " + chartView.getSelectedFieldsY().size());			
			// dimensionValuesYList = new ArrayList<List<String>>();
			
			for (YDimension yDimension : chartView.getSelectedFieldsY()) {
				
				dimensionY.put(String.valueOf(yDimension.getDataset().getResourceId()), yDimension.getSelectedField().getFieldName());
				System.out.println("->GETTING dimensionY Y: " +dimensionY);
				elementDS = new ArrayList<String>();
				elementDS.add(yDimension.getDataset().getTitle());
				elementDS.add(String.valueOf(yDimension.getDataset().getResourceId()));
				datasetId.add(elementDS);
				dimensionValuesYList = new ArrayList<List<String>>();
				
				System.out.println("yDimension.getSelectedField().getSelectedValues(): " + yDimension.getSelectedField().getSelectedValues().size());
				
				for (SelectedValue selectedValue : yDimension.getSelectedField().getSelectedValues()) {
//					dimensionValuesYList = new ArrayList<List<String>>();
					elementValues = new ArrayList<String>();
					elementValues.add(selectedValue.getSelectedValue());
					
					System.out.println("-------------");
					System.out.println("  --> ALL INFO-> header: " + dimensionY.get(String.valueOf(yDimension.getDataset().getResourceId())) + " | value: " + selectedValue.getSelectedValue() );
					/** TODO: this should be changed retrieving the legend from the rptdesign and should not be dinamically created all the time
					 */
					
					List<UniqueTextValues> label = uniqueValuesDao.getText(yDimension.getDataset().getResourceId(), dimensionY.get(String.valueOf(yDimension.getDataset().getResourceId())) , selectedValue.getSelectedValue(), "EN");
					if ( !label.isEmpty()) {
						System.out.println("label -------------> " + label.get(0).getLabel());
						elementValues.add(label.get(0).getLabel());
					}
					else {
						elementValues.add(selectedValue.getSelectedValue());
					}
					/** TODO: Here are not retrieved the labels..
					 * 		  it should be called the codecDao with the language
					 * 		  and retrieve the labels.
					 * 		  Because in the db is stored just the code...
					 */
				
					
					
					// test
					Boolean add = true;
					for(int j=0; j < dimensionValuesYList.size(); j++ ){
						for(int k=0; k < dimensionValuesYList.get(j).size(); k++ ){
							System.out.println("dimensionValuesYList.get(j).get(k): " + dimensionValuesYList.get(j).get(k) + " | " + selectedValue.getSelectedValue());
							if ( dimensionValuesYList.get(j).get(k).equals(selectedValue.getSelectedValue())) {
								System.out.println("ALREADY IN!!!");								
								add = false;
								break;
							}
							else {
								// do nothing
							}
						}
						
						
					}
					
					
					System.out.println("ADD VALUE: " + add);			
					if ( add ) 
						dimensionValuesYList.add(elementValues);
					
					
					System.out.println("-->GETTING elementValues " + dimensionValuesYList);
				}
				System.out.println("--->GETTING dimensionValuesYList Y: " +dimensionValuesYList);
				dimensionValuesY.put(String.valueOf(yDimension.getDataset().getResourceId()), dimensionValuesYList);
			}
			System.out.println("---->GETTING dimensionValuesY: " + dimensionValuesY);
			chartWizardBean.setDimensionValuesY(dimensionValuesY);

		}

		for (OtherYDimension otherYDimension : chartView.getSelectedFieldsOtherDim()) {
			dimensionValuesOthList = new ArrayList<List<String>>();
			for (SelectedField selectedField : otherYDimension.getSelectedFieldList().getSelectedFieldsList()) {
				elementValues = new ArrayList<String>();
				elementValues.add(selectedField.getFieldName());
				elementValues.add(selectedField.getSelectedValues().get(0).getSelectedValue());
				if (selectedField.getSelectedValues().get(0).getUniqueOtherValue() == null)
					elementValues.add("false");
				else
					elementValues.add(selectedField.getSelectedValues().get(0).getUniqueOtherValue());
				dimensionValuesOthList.add(elementValues);

			}
			otherDimensionMap.put(String.valueOf(otherYDimension.getDataset().getResourceId()), dimensionValuesOthList);
		}

		if (chartView.getScales().size() > 0) {
			Map<String, String> scalesMap = new HashMap<String, String>();
			for (Scales s : chartView.getScales()) {
				scalesMap.put(String.valueOf(s.getDataset().getResourceId()), s.getNumberScale());
			}
			chartWizardBean.setScalesMap(scalesMap);
		}

		chartWizardBean.setDatasetId(datasetId);
		chartWizardBean.setDimensionY(dimensionY);

		chartWizardBean.setDoubleScale(chartView.isDoubleAxis());
		chartWizardBean.setOtherDimension(otherDimensionMap);
		chartWizardBean.setTitle(chartView.getTitleChart());
		chartWizardBean.setChartType(chartView.getChartType());
		chartWizardBean.setFlip(chartView.isFlip());
		chartWizardBean.setPosLegend(chartView.getPositionLegend());
		chartWizardBean.setShowLegend(chartView.isShowLegend());
		chartWizardBean.setXAxisTitle(chartView.getXAxisTitle());
		chartWizardBean.setXAxisShowLabels(chartView.isXAxisShowLabels());
		chartWizardBean.setYAxisTitle(chartView.getYAxisTitle());
		chartWizardBean.setYAxisShowLabels(chartView.isYAxisShowLabels());
		chartWizardBean.setRotateXLabels(String.valueOf(chartView.getRotateXLabels()));
		chartWizardBean.setDim2_3D(chartView.getDimension());
		chartWizardBean.setDisposition(chartView.getDisposition());
		
		
		System.out.println("getting dimensionValuesY Y: " + dimensionValuesY);
		System.out.println("getting dimensionValuesYList Y: " + dimensionValuesYList);
		System.out.println("getting elementValues: " + elementValues);
		

		return chartWizardBean;
	}

	
}
