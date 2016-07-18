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
package org.fao.fenix.web.modules.ofcchart.client.control;


import java.util.Iterator;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.view.viewer.OfcChartViewer;
import org.fao.fenix.web.modules.ofcchart.common.services.OfcChartServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.Text;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.Element;


public class OfcChartViewerController {
	
	public static SelectionListener<IconButtonEvent> save(final OfcChartViewer window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				System.out.println("chartdata" + window.getChartData().toString());
//				ChartWidget chart = new ChartWidget();
//				chart.setJsonData(window.getChartData().toString());
//				chart.setSize("600", "500");
				ChartWidget chart = window.getChart();
				String image = chart.getImageData();
				System.out.println("IMAGE: " + image);
				
				OfcChartServiceEntry.getInstance().saveChart(chart.getImageData(), new AsyncCallback<String>() {
				public void onSuccess(String result) {
//					Window.open(Window.open("../cropCalendars/", "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
				};
				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().info(), e.getMessage());
				}
			});

			}
		};
	}

	public static SelectionListener<IconButtonEvent> refreshChart(final OfcChartViewer window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				System.out.println("changing title");
				ChartWidget chart = window.getChart();
				ChartData cd = window.getChartData();
				System.out.println(cd.toString());
				cd.setTitle(new Text("CIAO"));
				chart.setJsonData(cd.toString());
			}
		};
	}

	public static SelectionListener<IconButtonEvent> chartFormat(final OfcChartViewer window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				ChartWidget chart = window.getChart();
				ChartData cd = window.getChartData();
				ChartData newCd = new ChartData(chart.getStyleName());
				
				
				Iterator<Element> a = cd.getElements().iterator();
				while(a.hasNext()) {
					BarChart barChart = (BarChart) a.next();
					System.out.println("->");
					barChart.setColour("444444");
					barChart.setText("BELLA");
					System.out.println("BARCHART TEXT: " + barChart.getText());
					
//					Element e = (Element) i;
//					cd.removeElement(e);
//					e.setColours("666666");					
//					BarChart bchart = (BarChart) i;
//					bchart.setColour("666666");
//					cd.setElements(elements)
//					chart.setJsonData(cd.toString());
//					cd.addElements(e);
					newCd.addElements(barChart);
				} 
				newCd.setTitle(new Text(chart.getTitle()));
				newCd.setXAxis(cd.getXAxis());
				newCd.setYAxis(cd.getYAxis());
				newCd.setXLegend(cd.getXLegend());
				newCd.setYLegend(cd.getYLegend());
				chart.setChartData(newCd);
			}
		};
	}

	
	
}