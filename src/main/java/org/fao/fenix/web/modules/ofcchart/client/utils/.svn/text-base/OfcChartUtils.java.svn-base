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
package org.fao.fenix.web.modules.ofcchart.client.utils;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fao.fenix.web.modules.ofcchart.client.view.chart.OfcBarChart;
import org.fao.fenix.web.modules.ofcchart.client.view.chart.OfcLineChart;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartBeanVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcParametersVo;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcSeriesVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.ValueVO;

import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.Legend;
import com.rednels.ofcgwt.client.model.Text;
import com.rednels.ofcgwt.client.model.Legend.Position;
import com.rednels.ofcgwt.client.model.axis.Label;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.LineChart;


public class OfcChartUtils {

	
	public static List<String> sortXLabels(Map<String, String> xLabels) {
		Collection<String> values = xLabels.values();
		
		List<String> labels = new ArrayList<String>();
		labels.addAll(values);
		Collections.sort(labels);
		for(String label : labels)
			System.out.println(label);
		return labels;	
	}
	
	/** TODO: instead of + 10 create a function that dynamically return something to add **/
	public static void setRange(YAxis ya, List<ValueVO> values){
		for(ValueVO value : values){
			/** MAX **/
			if ( value.getValue() != null) {
				if(ya.getMax() == null || ya.getMax().doubleValue() < value.getValue().doubleValue())	{
					ya.setMax(value.getValue().doubleValue() * 1.1);	
				}
				/** MIN **/
				if ( value.getValue() < 0 ) {
					if ( ya.getMin() == null || ya.getMin().doubleValue() > value.getValue().doubleValue())
						ya.setMin(value.getValue().doubleValue());
				}
			}
		}
		
		setSteps(ya);
	}
	
	/** TODO FIX: FOR BANGLADESH **/
	public static void setSteps(YAxis ya){
		if ( ya.getMax() != null) {;
			Double max = ya.getMax().doubleValue() * 1.1;
			Double min = new Double(0);
			if ( ya.getMin() != null )
				min = ya.getMin().doubleValue();
			Double range = max - min;
			if ( range > 10)
				ya.setSteps(range / 10);
	//			Integer steps = range / 10;
			System.out.println("MAX  : " + max);
			System.out.println("MIN  : " + min);
			System.out.println("RANGE: " + range);
	
		
	//		System.out.println("STEPS: " + steps);
	//		ya.setSteps(steps);
		}
	}
	
	/** TODO: FIX PER BANGLADESH **/
//	public static void setXlabelsToXAxisBangladesh(XAxis xa, List<String> xlabels, String type) {
//		int i= 0;
//		for(String xLabel : xlabels) {
//			if ( i % 14 == 0) {
//				Label label = new Label();
//				label.setSize(8);
//				label.setText(xLabel);
//				xa.addLabels(label);	
//			}
//			else 
//				xa.addLabels("");	
//			i++;
//		}
//	}
	
	
	public static void setXlabelsToXAxis(XAxis xa, List<String> xlabels) {
		for(String xLabel : xlabels) 
			xa.addLabels(xLabel);
	}
	
	public static void setXlabelsToXAxisGeneral(XAxis xa, List<String> xlabels) {
//		for(String xLabel : xlabels) {
//			Label label = new Label();
//			label.setSize(7);
//			label.setText(xLabel);
//			xa.addLabels(label);		
//		}
		
		for(int i = xlabels.size() -1 ; i >= 0 ; i--) {
			Label label = new Label();
			if ( xlabels.get(i).equals("")) {
				label.setSize(0);
				label.setText("");
			}
			else {
				label.setSize(11);
				label.setText(xlabels.get(i));
			}
			xa.addLabels(label);		
		}
	}
	

	public static ChartData createBarChart(ChartData cd, OfcChartBeanVO bean, List<String> xlabels, YAxis ya, YAxis yra, OfcSeriesVO series, String... type ) {
		Set<String> keySet = bean.getValues().keySet();
		List<ValueVO> values = new ArrayList<ValueVO>(); 
		String ylabel = new String();
		List<BarChart> charts = new ArrayList<BarChart>();
//		if ( cd.getYLegend() == null) {
//			cd.setYLegend(new Text("(" + bean.getMeasuramentUnit() + ")", "font-size: 14px; font-family: Verdana; text-align: center;"));
//		}
		System.out.println("CREATING BAR CHART");
		if ( bean.isHasFilters()) {
			for(String ykey : keySet) {
				for(String fkey : bean.getValues().get(ykey).keySet()) {
					values =   bean.getValues().get(ykey).get(fkey);
					ylabel = ykey.concat(" - " + fkey);
					System.out.println(ylabel);
					// set the range of the chart 
//					if ( new Text("(" + bean.getMeasuramentUnit()+ ")", "font-size: 14px; font-family: Verdana; text-align: center;").equals(cd.getYLegend()))
						OfcChartUtils.setRange(ya, values);
//					else {
//						if ( cd.getYRightLegend() == null) {
//							cd.setYRightLegend(new Text("(" + bean.getMeasuramentUnit() + ")", "font-size: 14px; font-family: Verdana; text-align: center;"));
//							cd.setYAxisRight(yra);
//						}
//						OfcChartUtils.setRange(yra, values);					
//					} 
					BarChart chart = OfcBarChart.createChart(values, xlabels, ylabel, type);
					charts.add(chart);
					cd.addElements(chart);
				}
			}
		}
		else {
			keySet = bean.getValuesNF().keySet();
			System.out.println("HAS NOT FILTERS");
			for(String ykey : keySet) {
				values =   bean.getValuesNF().get(ykey);
				ylabel = ykey;
				System.out.println("ykey: " + ykey);
				// set the range of the chart 
				OfcChartUtils.setRange(ya, values);		
				BarChart chart = OfcBarChart.createChart(values, xlabels, ylabel, type);
				charts.add(chart);
				cd.addElements(chart);
			}
		}
		/** adding the serie **/
		series.getBarChartSeries().put(bean.getDatasetId(), charts);
		return cd;
	}
	
	public static ChartData createLineChart(ChartData cd, OfcChartBeanVO bean, List<String> xlabels, YAxis ya, OfcSeriesVO series, String... type ) {
//		Set<String> keySet = bean.getValues().keySet();
		System.out.println("CREATE LINE CHART");
		List<ValueVO> values = new ArrayList<ValueVO>();
		List<List<LineChart>> chartsSeries = new ArrayList<List<LineChart>>();
		String ylabel = new String();
		if ( bean.isHasFilters()) {
			Set<String> keySet = bean.getValues().keySet();
			System.out.println("HAS FILTERS");
			for(String ykey : keySet) {
				for(String fkey : bean.getValues().get(ykey).keySet()) {
					values =   bean.getValues().get(ykey).get(fkey);
					ylabel = ykey.concat(" - " + fkey);
					
					// set the range of the chart 
					OfcChartUtils.setRange(ya, values);
					List<LineChart> charts = OfcLineChart.createChartList(values, xlabels, ylabel);
					chartsSeries.add(charts);
					for(LineChart chart : charts) 
						cd.addElements(chart);
				}
			}
		}
		else {
			Set<String> keySet = bean.getValuesNF().keySet();
			System.out.println("HAS NOT FILTERS");
			for(String ykey : keySet) {
				values = bean.getValuesNF().get(ykey);
				ylabel = ykey;
				System.out.println("ykey: " + ykey);
				// set the range of the chart 
				OfcChartUtils.setRange(ya, values);
				List<LineChart> charts = OfcLineChart.createChartList(values, xlabels, ylabel);
				chartsSeries.add(charts);
				for(LineChart chart : charts)
					cd.addElements(chart);
			}
		}
		/** adding the serie **/
		series.getLineChartSeries().put(bean.getDatasetId(), chartsSeries);
		return cd;
	}
	
	public static void setXYLegend(ChartData cd, OfcParametersVo p){
		cd.setYLegend(new Text(p.getMeasuramentUnit().get(0), "font-size: 14px; font-family: Verdana; text-align: center;"));
		cd.setXLegend(new Text(p.getxDimension().getHeader(), "font-size: 14px; font-family: Verdana; text-align: center;"));
	}
	
	public static void setLegend(ChartData cd){
		Legend legend = new Legend(Position.TOP, true); 		
//		legend.setMargin(20);
		legend.setBgColour("#ffffff");
		legend.setBorder(true);
		cd.setLegend(legend);
	}
}