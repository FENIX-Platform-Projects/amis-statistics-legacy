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
import java.util.Set;

import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartBeanVO;

import com.rednels.ofcgwt.client.model.elements.PieChart;

public class OfcPieChart {

	
	public static List<PieChart> createChart(OfcChartBeanVO beans){
		List<PieChart> charts = new ArrayList<PieChart>();
	
		Set<String> keySet = beans.getValues().keySet();
		System.out.println(keySet.size());
		for(String key : keySet) {
			PieChart chart = new PieChart();
			chart.setAlpha(0.3f);
//			chart.setNoLabels(true);
			chart.setTooltip("#label# $#val#<br>#percent#");
			chart.setAnimateOnShow(true);
			chart.setGradientFill(true);
		/*	List<ValueVO> values =  beans.getValues().get(key);
			List<String> colours = addColours(values.size());
			chart.setColours(colours);
			for(ValueVO value : values)
				if ( value.getValue() != null)
					chart.addSlices(new PieChart.Slice(value.getValue(),value.getxLabel()));
			charts.add(chart);*/
		}
		return charts;
	}
	
	private static List<String> addColours(Integer size){
		List<String> colours = new ArrayList<String>();	
		for(int i=0; i< size; i++) {
			Double h = (Math.floor(Math.random()*0xFFFFFF));;
			colours.add("#" + Integer.toHexString(h.intValue()));
		}
//		System.out.println(colours);
		return colours;
	}
	
	
}