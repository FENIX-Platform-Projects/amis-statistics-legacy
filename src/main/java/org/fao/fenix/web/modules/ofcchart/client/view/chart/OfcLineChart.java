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
package org.fao.fenix.web.modules.ofcchart.client.view.chart;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.ofcchart.common.vo.ValueVO;

import com.rednels.ofcgwt.client.model.elements.LineChart;
import com.rednels.ofcgwt.client.model.elements.LineChart.LineStyle;
import com.rednels.ofcgwt.client.model.elements.dot.BaseDot;
import com.rednels.ofcgwt.client.model.elements.dot.Dot;

public class OfcLineChart {

	
	public static LineChart createChart(List<ValueVO> values, List<String> xLabels, LineStyle... style){
		LineChart lchart = new LineChart();
		

		Double h = (Math.floor(Math.random()*0xFFFFFF));;
		String hex = "#" + Integer.toHexString(h.intValue());
		lchart.setColour(hex);
		
		int i=0;
		for(String label : xLabels) {
			Boolean check = false;
			for(ValueVO value : values) {	
				if(value.getxLabel().equals(label)) {
					lchart.addValues(value.getValue());
					check = true;
					break;
				}
			}
			if ( !check) {
				lchart.addNull();
			}
		 i++;
		}	
		return lchart;
	}
	

	
	public static List<LineChart> createChartList(List<ValueVO> values, List<String> xLabels, String yLabel, LineStyle... style){
		List<LineChart> charts = new ArrayList<LineChart>();
		Double h = (Math.floor(Math.random()*0xFFFFFF));;
		String hex = "#" + Integer.toHexString(h.intValue());

		System.out.println("colour: " + hex);	
		System.out.println("yLabel: " + yLabel);
		/** missing values **/
		LineChart chart = new LineChart();

		
		chart.setColour(hex);
//		chart.setLineStyle(LineStyle.NORMAL);
		chart.setText(yLabel);
		chart.setFontSize(9);
//		chart.setLineStyle()
		charts.add(chart);
		
		
		
		int missingValues = missingValues(values, xLabels);
		for(int i = 0 ; i <= missingValues; i++) {
			LineChart chart2 = new LineChart();
//			chart2.setFontSize(9);
			chart2.setColour(hex);
			charts.add(chart2);
		}
		
		/** TODO: optimize the cicle **/
		int chartNumber=0;
		int addedValues=0;
		for(String label : xLabels) {
			Boolean check = false;
			for(ValueVO value : values) {	
				if(value.getxLabel().equals(label)) {
					if ( value.getValue() != null) {
						BaseDot dot = new Dot();
						dot.setSize(1);
						dot.setHaloSize(1);
						dot.setColour(charts.get(chartNumber).getColour());
						dot.setValue(value.getValue());
//						charts.get(chartNumber).addValues(value.getValue());
						charts.get(chartNumber).addDots(dot);
						
						check = true;
					}
					break;
				}
			}
			if (!check) {
				chartNumber++;
				addNnullValules(charts.get(chartNumber), addedValues);
			}
			addedValues++;
		}
		
		return charts;
	}
	
	private static void addNnullValules(LineChart chart, int j){
		for(int k =0; k < j; k++)
			chart.addNull();
	}
	
	private static Integer missingValues(List<ValueVO> values, List<String> xLabels){
		Integer missingValues = 0;
//		for(String label : xLabels) {
//			System.out.println("label: " + label);
//			Boolean check = false;
			for(ValueVO value : values) {
				if ( value.getValue() == null) {
					System.out.println(value.getxLabel());
					missingValues++;
				}
//				if(value.getxLabel().equals(label)) {
//					check = true;
//					break;
//				}
			}
//			if ( !check) 
//				missingValues++;
//		}	
		System.out.println("missing Values: " + missingValues);
		return missingValues;
	}
}

	