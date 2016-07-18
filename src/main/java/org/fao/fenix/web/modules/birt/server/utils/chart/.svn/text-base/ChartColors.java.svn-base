package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.ChartWithoutAxes;
import org.eclipse.birt.chart.model.attribute.ColorDefinition;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.emf.common.util.EList;

public class ChartColors {
	
	
	static private boolean isHexColor(String rgbType){
		if (rgbType.equals("hex")) return true;
		else return false;
	}
	
	static public List<List<String>> getChartColors(Chart chart, String rgbType){
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		if (!chart.getType().equals("Pie")) {
			//bean.getColor().clear();
			int tmp = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().size();
			if (((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getAssociatedAxes().size() > 1) {
				tmp += ((Axis) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getAssociatedAxes().get(1)).getSeriesDefinitions().size();
			}
			List<String> listPar;
			int y1 = 0;
			int y2 = 0;
			for (int i = 0; i < tmp; i++) {
				listPar = new ArrayList<String>();
				SeriesDefinition s;
				if (i < ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().size()) {
					s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(y1);
					y1++;
				} else {
					s = (SeriesDefinition) ((Axis) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getAssociatedAxes().get(1)).getSeriesDefinitions().get(y2);
					y2++;
				}

				listPar.add((String) ((Series) s.getSeries().get(0)).getSeriesIdentifier());
//				s.getSeries().get(0).getLabel().getCaption().getFont().set
				EList list = s.getSeriesPalette().getEntries();
				
				String color = "";
				if (isHexColor(rgbType)) color = "#";
				if (chart.getType().equals("Line")) {
					for (int z = 0; z < s.getSeries().size(); z++) {
						if (isHexColor(rgbType)){
							String r = Integer.toHexString(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getRed());
							if (r.length() == 1)
								r = "0" + r;
							String g = Integer.toHexString(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getGreen());
							if (g.length() == 1)
								g = "0" + g;
							String b = Integer.toHexString(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getBlue());
							if (b.length() == 1)
								b = "0" + b;
							color += r + g + b;
						} else {
							String r = String.valueOf(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getRed());
							String g = String.valueOf(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getGreen());
							String b = String.valueOf(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getBlue());
							color += r + "_" + g + "_" + b;
						}
						
					}

				} else if (chart.getType().equals("Bar/Line")) {
					String packClass = s.getSeries().get(0).getClass().toString();
					int index = (packClass.length()) - 1;
					while (packClass.charAt(index) != '.') {
						index--;
					}
					index++;
					packClass = packClass.substring(index, packClass.length());
					if (packClass.equals("LineSeriesImpl")) {
						for (int z = 0; z < s.getSeries().size(); z++) {
							if (isHexColor(rgbType)){
								String r = Integer.toHexString(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getRed());
								if (r.length() == 1)
									r = "0" + r;
								String g = Integer.toHexString(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getGreen());
								if (g.length() == 1)
									g = "0" + g;
								String b = Integer.toHexString(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getBlue());
								if (b.length() == 1)
									b = "0" + b;
								color += r + g + b;
							} else {
								String r = String.valueOf(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getRed());
								String g = String.valueOf(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getGreen());
								String b = String.valueOf(((LineSeries) s.getSeries().get(z)).getLineAttributes().getColor().getBlue());
								color += r + "_" + g + "_" + b;
							}
							
						}
					} else {
						for (int j = 0; j < list.size(); j++) {
							if (isHexColor(rgbType)){
								String r = Integer.toHexString(((ColorDefinition) list.get(j)).getRed());
								if (r.length() == 1)
									r = "0" + r;
								String g = Integer.toHexString(((ColorDefinition) list.get(j)).getGreen());
								if (g.length() == 1)
									g = "0" + g;
								String b = Integer.toHexString(((ColorDefinition) list.get(j)).getBlue());
								if (b.length() == 1)
									b = "0" + b;
								color += r + g + b;
							} else {
								String r = String.valueOf(((ColorDefinition) list.get(j)).getRed());
								String g = String.valueOf(((ColorDefinition) list.get(j)).getGreen());
								String b = String.valueOf(((ColorDefinition) list.get(j)).getBlue());
								color += r + "_" + g + "_" + b;
							}
							
						}
					}
				} else {
					for (int j = 0; j < list.size(); j++) {
						if (isHexColor(rgbType)){
							String r = Integer.toHexString(((ColorDefinition) list.get(j)).getRed());
							if (r.length() == 1)
								r = "0" + r;
							String g = Integer.toHexString(((ColorDefinition) list.get(j)).getGreen());
							if (g.length() == 1)
								g = "0" + g;
							String b = Integer.toHexString(((ColorDefinition) list.get(j)).getBlue());
							if (b.length() == 1)
								b = "0" + b;
							color += r + g + b;
						} else {
							String r = String.valueOf(((ColorDefinition) list.get(j)).getRed());
							String g = String.valueOf(((ColorDefinition) list.get(j)).getGreen());
							String b = String.valueOf(((ColorDefinition) list.get(j)).getBlue());
							color += r + "_" + g + "_" + b;
						}
						
					}
				}

				listPar.add(color);
				result.add(listPar);

			}
		} else {
			//bean.getColor().clear();
			int tmp = ((ChartWithoutAxes) chart).getSeriesDefinitions().size();
			List<String> listPar;
			for (int i = 0; i < tmp; i++) {
				listPar = new ArrayList<String>();
				SeriesDefinition s = (SeriesDefinition) ((ChartWithoutAxes) chart).getSeriesDefinitions().get(i);
				listPar.add((String)((Series) s.getSeries().get(0)).getSeriesIdentifier());
				String color = "";
				if (isHexColor(rgbType)) color = "#";
				EList list = s.getSeriesPalette().getEntries();
				for (int j = 0; j < list.size(); j++) {
					if (isHexColor(rgbType)){
						String r = Integer.toHexString(((ColorDefinition) list.get(j)).getRed());
						if (r.length() == 1)
							r = "0" + r;
						String g = Integer.toHexString(((ColorDefinition) list.get(j)).getGreen());
						if (g.length() == 1)
							g = "0" + g;
						String b = Integer.toHexString(((ColorDefinition) list.get(j)).getBlue());
						if (b.length() == 1)
							b = "0" + b;
						color += r + g + b;
					} else {
						String r = String.valueOf(((ColorDefinition) list.get(j)).getRed());
						String g = String.valueOf(((ColorDefinition) list.get(j)).getGreen());
						String b = String.valueOf(((ColorDefinition) list.get(j)).getBlue());
						color += r + "_" + g + "_" + b;
					}
					
				}

				listPar.add(color);
				result.add(listPar);
		
			}

		}
		
		return result;
		
	}
	
	public static List<Integer> getRightColorWhenChartisRefreshed(List<List<String>> seriesColor, int index){
		List<Integer> rgb = new ArrayList<Integer>();
		
		if (seriesColor == null || index >= seriesColor.size()){
			rgb.add((int) Math.round((Math.random() * 255)));
			rgb.add((int) Math.round((Math.random() * 255)));
			rgb.add((int) Math.round((Math.random() * 255)));
		} else {
			StringTokenizer tokenizer = new StringTokenizer(seriesColor.get(index).get(1) , "_");
			rgb.add(Integer.valueOf(tokenizer.nextToken()));
			rgb.add(Integer.valueOf(tokenizer.nextToken()));
			rgb.add(Integer.valueOf(tokenizer.nextToken()));
		}
		
		
		return rgb;
	}

}
