package org.fao.fenix.web.modules.ofcchart.client.view;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.FlowPanel;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.axis.XAxis;
import com.rednels.ofcgwt.client.model.axis.YAxis;
import com.rednels.ofcgwt.client.model.elements.BarChart;
import com.rednels.ofcgwt.client.model.elements.BarChart.BarStyle;

public class OfcLineGraph{
	
	public void OfcLineGraph(){
		ChartWidget chart = new ChartWidget();	
		
		ChartData cd = new ChartData("Relative Performance","font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		
//		SketchBarChart lc1 = new SketchBarChart();
//		LineChart lc1 = new LineChart(LineStyle.NORMAL);
//		lc1.setText("PoorEnterprises Pty");
//		lc1.setColour("#1f0010");
//
//		for (int t=0;t<30;t++) {
//			lc1.addValues(Random.nextDouble()*.5 - .5);
//			
//		}
//		LineChart lc2 = new LineChart(LineStyle.HOLLOW);
		
//		lc2.setColour("#00ff00");
//		lc2.setText("Ave-Ridge Co LLC");
//		for (int t=0;t<30;t++) {
//			lc2.addValues(Random.nextDouble()*.8);
//		}
//		LineChart lc3 = new LineChart(LineStyle.DOT);
		
//		lc3.setColour("#0000ff");
//		lc3.setText("Suu Perb Enterprises");
//		for (int t=0;t<30;t++) {
//			if ( t % 3 != 0) {
////				lc3.addValues(Random.nextDouble()*1.1 + .5);
//				lc3.addDots(new Dot(Random.nextDouble()*1.1 + .5));
//				
//			}
//			else 
//				lc3.addDots(new Dot(null));
//		}
//		lc3.setHaloSize(2);
//	
	
		/** SCATTER
		ScatterChart sc = new ScatterChart(ScatterStyle.LINE);
		sc.setColour("#0000ff");
		sc.setText("Suu Perb Enterprises");		
		for (int t=0;t<30;t++) {
//			if ( t % 3 != 0) {
				int x = Random.nextInt(50)-25;
				int y = Random.nextInt(50)-25;
				sc.addPoints(new ScatterChart.Point(x,y));						
//			}								ChartWidget chart = new ChartWidget();	
	
					ChartData cd = new ChartData("Relative Performance","font-size: 14px; font-family: Verdana; text-align: center;");
					cd.setBackgroundColour("#ffffff");
					
//					SketchBarChart lc1 = new SketchBarChart();
					LineChart lc1 = new LineChart(LineStyle.NORMAL);
//					lc1.setText("PoorEnterprises Pty");
//					lc1.setColour("#1f0010");
//			
//					for (int t=0;t<30;t++) {
//						lc1.addValues(Random.nextDouble()*.5 - .5);
//						
//					}
					LineChart lc2 = new LineChart(LineStyle.HOLLOW);
					
//					lc2.setColour("#00ff00");
//					lc2.setText("Ave-Ridge Co LLC");
//					for (int t=0;t<30;t++) {
//						lc2.addValues(Random.nextDouble()*.8);
//					}
					LineChart lc3 = new LineChart(LineStyle.DOT);
					
					lc3.setColour("#0000ff");
					lc3.setText("Suu Perb Enterprises");
					for (int t=0;t<30;t++) {
						if ( t % 3 != 0) {
//							lc3.addValues(Random.nextDouble()*1.1 + .5);
							lc3.addDots(new Dot(Random.nextDouble()*1.1 + .5));
							
						}
						else 
							lc3.addDots(new Dot(null));
					}
					lc3.setHaloSize(2);
				
				
					/** SCATTER
					ScatterChart sc = new ScatterChart(ScatterStyle.LINE);
					sc.setColour("#0000ff");
					sc.setText("Suu Perb Enterprises");		
					for (int t=0;t<30;t++) {
//						if ( t % 3 != 0) {
							int x = Random.nextInt(50)-25;
							int y = Random.nextInt(50)-25;
							sc.addPoints(new ScatterChart.Point(x,y));						
//						}			
//						else 
//							sc.addNull();
					}
					**/
				
					FlowPanel fp3 = new FlowPanel();
					chart = new ChartWidget();		
					ChartData cd3 = new ChartData("Sales by Month 2008","font-size: 14px; font-family: Verdana; text-align: center;");
					cd3.setBackgroundColour("#ffffff");
					XAxis xa = new XAxis();
					YAxis ya = new YAxis();
					xa = new XAxis();
					xa.setLabels("J123123","F1231232123","M123123123123","A","M","J","J","A","S","O","N","D");
					
					xa.setZDepth3D(5);
					xa.setMax(12);
					xa.setTickHeight(4);
					xa.setOffset(true);
					xa.setColour("#909090");
//					xa.setGridColour("#G09120");
					xa.getLabels().setRotationAngle(90);
					cd3.setXAxis(xa);
					
					
					ya.setSteps(10);
					ya.setMax(160);
					cd3.setYAxis(ya);
					BarChart bchart3 = new BarChart(BarStyle.NORMAL);
					bchart3.setColour("#ff8800");
//					bchart3.setTooltip("$#val#");
					bchart3.addValues(103,123,133,138,126,117,121,143,140,152,121,105);		
					BarChart bchart4 = new BarChart(BarStyle.NORMAL);
					bchart4.setColour("#1f8800");
//					bchart4.setTooltip("$#val#");
					bchart4.addValues(103,123,133,3,4,5,6,7,80,9,-2,105);
			
					cd3.addElements(bchart3);
					cd3.addElements(bchart4);
				
//					chart.setSize("350", "250");
					chart.setJsonData(cd3.toString());		

					
					
//					YAxis ya = new YAxis();
//					ya.setMax(2);
//					ya.setMin(-1);
//					ya.setLabels("Y-AXIS");
//					XAxis xa = new XAxis();
//					xa.setLabels("NEW X-AXIS");
					
//					cd.setYAxis(ya);
//					cd.setXAxis(xa);
					
					
					
//					cd.addElements(lc1);
//					cd.addElements(lc2);
//					cd.addElements(lc3);
				
//					cd.addElements(sc);
//					cd.addElements(chart3);
				
					
				
					chart.setSize("750", "500");
//					chart.setJsonData(cd3.toString());
					
					com.extjs.gxt.ui.client.widget.Window window = new com.extjs.gxt.ui.client.widget.Window();
					window.setHeading("Simone test");
					ContentPanel cont = new ContentPanel();
					cont.setHeaderVisible(false);
					cont.setLayout(new FitLayout());
					window.setLayout(new FitLayout());
					window.setSize(800, 600);
					window.add(chart);
//					cont.add(content);
//					window.add(cont);
					window.show();
					
					
				
			}


}