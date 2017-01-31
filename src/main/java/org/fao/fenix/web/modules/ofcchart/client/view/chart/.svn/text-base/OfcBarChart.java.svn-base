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

import java.util.List;

import org.fao.fenix.web.modules.ofcchart.common.vo.ValueVO;

import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;

public class OfcBarChart {

	public static BarChart createChart(List<ValueVO> values, List<String> xLabels, String yLabel, String... type){
		BarChart bchart = new BarChart();
		if ( type == null ) {
			bchart = new BarChart(BarStyle.NORMAL);
		}
		else {
			/* depends what's the style */
		}
		
		
		Double h = (Math.floor(Math.random()*0xFFFFFF));;
		String hex = "#" + Integer.toHexString(h.intValue());
		System.out.println(hex + " | " + Integer.toHexString(h.intValue()));
		bchart.setColour(hex);
		bchart.setText(yLabel);
		/** needs an hashMap **/
		int i=0;
//		System.out.println("label size: " + xLabels.size());
//		System.out.println("values size: " + values.size());
		for(String label : xLabels) {
			Boolean check = false;
			for(ValueVO value : values) {	
//				System.out.println("value: " + value.getxLabel());
				if(value.getxLabel().equals(label)) {
					bchart.addValues(value.getValue());
					check = true;
					break;
				}
			}
			if ( !check)
				bchart.addNull();
			i++;
		}	
//		bchart.setTooltip("#label# - #val#");
		return bchart;
	}
}