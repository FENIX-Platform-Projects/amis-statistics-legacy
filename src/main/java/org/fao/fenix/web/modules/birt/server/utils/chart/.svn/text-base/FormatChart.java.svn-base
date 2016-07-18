package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.Anchor;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.ChartDimension;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.LineStyle;
import org.eclipse.birt.chart.model.attribute.NumberFormatSpecifier;
import org.eclipse.birt.chart.model.attribute.TickStyle;
import org.eclipse.birt.chart.model.attribute.impl.BoundsImpl;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.attribute.impl.NumberFormatSpecifierImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Scale;
import org.eclipse.birt.chart.model.data.impl.NumberDataElementImpl;
import org.eclipse.birt.chart.model.layout.Legend;
import org.eclipse.birt.chart.model.layout.Plot;
import org.fao.fenix.web.modules.birt.server.utils.ManageLegend;

public class FormatChart {
	
	public static void setDim(Chart chart, String dimension){
		if (dimension.equals("Two_Dimensional")){
			chart.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
		} else if (dimension.equals("Two_Dimensional_With_Depth")){
			chart.setDimension(ChartDimension.TWO_DIMENSIONAL_WITH_DEPTH_LITERAL);
		}
	}
	
	private static List<Integer> getColor(String color){
		StringTokenizer colorFormat = new StringTokenizer(color, "_");
		List<Integer> RGB = new ArrayList<Integer>();

		while (colorFormat.hasMoreTokens()) {
			RGB.add(Integer.valueOf(colorFormat.nextToken()));
		}
		
		return RGB;
	}
	
	public static void setChartStyle(ChartStyle wrapperBean, ChartWithAxes chart){
		
		// Plot
		chart.getBlock().setBounds(BoundsImpl.create(0, 0, wrapperBean.getWidth(), wrapperBean.getHeight()));
		
		
		chart.getTitle().setVisible(wrapperBean.isVisibleTitle());
		
		StringTokenizer tokenizer = new StringTokenizer(wrapperBean.getBackgroundPlotArea(), "_");
		int redB = Integer.valueOf(tokenizer.nextToken());
		int greenB = Integer.valueOf(tokenizer.nextToken());
		int blueB = Integer.valueOf(tokenizer.nextToken());
		
		Plot p = chart.getPlot();
		List<Integer> c = getColor(wrapperBean.getBackgroundPlotArea());
		p.getClientArea().setBackground(ColorDefinitionImpl.create(c.get(0), c.get(1), c.get(2)));
		
		c =getColor(wrapperBean.getBackgroundChartArea());
		chart.getBlock().setBackground(ColorDefinitionImpl.create(c.get(0), c.get(1), c.get(2)));
		
		// Title
		chart.getTitle().getLabel().getCaption().setValue(wrapperBean.getTitle());
		chart.getTitle().getLabel().getCaption().getFont().setSize(wrapperBean.getSizeTitle());
		c = getColor(wrapperBean.getColorTitle());
		chart.getTitle().getLabel().getCaption().setColor(ColorDefinitionImpl.create(c.get(0), c.get(1), c.get(2)));
		
		
		// Legend
		Legend lg = chart.getLegend();
		
		if (wrapperBean.isShowLegend()) lg.setVisible(true);
		else lg.setVisible(false);
		LineAttributes lia = lg.getOutline();
		lg.getText().getFont().setSize(wrapperBean.getSizeLegend());
		lia.setStyle(LineStyle.SOLID_LITERAL);
		
		
//		lg.getClientArea().getInsets().set(0, 0, 0, 0);
//		lg.getClientArea().setVisible(true);
		
		ManageLegend.modifyLegend(wrapperBean.getPosLegend() , lg);
						
		
//		lg.getInsets().set(10, 5, 0, 0);
//		lg.getInsets().set(0, 0, 0, 0);
	
	
		lg.getOutline().setVisible(false);
		lg.setAnchor(Anchor.NORTH_LITERAL);
		lg.setBounds(BoundsImpl.create(0, 0, 20, 50));
//		lg.setHeightHint(0);
//		lg.setVerticalSpacing(0);
		
		
		int scaleNumbers = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true).length;
		
		//Unit spacing
		if (!chart.getType().equals("Pie")){
			((ChartWithAxes) chart).setUnitSpacing(wrapperBean.getUnitSpacing());
			((ChartWithAxes) chart).setTransposed(wrapperBean.isFlip());
			
			if (wrapperBean.isFlip()){
				((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setRotation(90);
				((Axis) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0])).getTitle().getCaption().getFont().setRotation(0);
				if (scaleNumbers == 2){
					Axis axis2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0],true)[1];
					axis2.getTitle().getCaption().getFont().setRotation(0);
				}
			} else {
				((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setRotation(0);
				((Axis) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0])).getTitle().getCaption().getFont().setRotation(90);
				if (scaleNumbers == 2){
					Axis axis2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0],true)[1];
					axis2.getTitle().getCaption().getFont().setRotation(-90);
				}
			}
			
		}
		
		FormatChart.setDim(chart, wrapperBean.getDim2_3D());
		
		// X-Axis
		Axis xAxisPrimary = chart.getPrimaryBaseAxes()[0];
		xAxisPrimary.setType(AxisType.TEXT_LITERAL);
		xAxisPrimary.getMajorGrid().setTickStyle(TickStyle.BELOW_LITERAL);
		//xAxisPrimary.getOrigin().setType(IntersectionType.VALUE_LITERAL);
		xAxisPrimary.getOrigin().setType(IntersectionType.MIN_LITERAL);
		xAxisPrimary.getTitle().setVisible(wrapperBean.isXAxisTitleVisible());
		xAxisPrimary.getTitle().getCaption().setValue(wrapperBean.getXAxisTitle());
		xAxisPrimary.getMajorGrid().getLineAttributes().setVisible(wrapperBean.isXGrid());
		xAxisPrimary.getTitle().getCaption().getFont().setSize(wrapperBean.getXAxisTitleSize());
		c = getColor(wrapperBean.getColorXAxisTitle());
		xAxisPrimary.getTitle().getCaption().setColor(ColorDefinitionImpl.create(c.get(0), c.get(1), c.get(2)));
		xAxisPrimary.getLabel().setVisible(wrapperBean.isXAxisLabelsVisible());
		xAxisPrimary.getLabel().getCaption().getFont().setSize(wrapperBean.getXAxisSizeLabels());
		c = getColor(wrapperBean.getColorXAxisLabel());
		xAxisPrimary.getLabel().getCaption().setColor(ColorDefinitionImpl.create(c.get(0), c.get(1), c.get(2)));
	

		// Y-Axis (scale1)
		Axis yAxisPrimary = chart.getPrimaryOrthogonalAxis(xAxisPrimary);
		yAxisPrimary.getMajorGrid().setTickStyle(TickStyle.LEFT_LITERAL);
		yAxisPrimary.getTitle().setVisible(wrapperBean.isYAxisTitleVisible());
		yAxisPrimary.getTitle().getCaption().setValue(wrapperBean.getYAxisTitle());
		yAxisPrimary.getTitle().getCaption().getFont().setSize(wrapperBean.getYAxisTitleSize());
		c = getColor(wrapperBean.getColorYAxisTitle());
		yAxisPrimary.getTitle().getCaption().setColor(ColorDefinitionImpl.create(c.get(0), c.get(1), c.get(2)));
		yAxisPrimary.getLabel().setVisible(wrapperBean.isYAxisLabelsVisible());		
		yAxisPrimary.getLabel().getCaption().getFont().setSize(wrapperBean.getYAxisSizeLabels());
		c = getColor(wrapperBean.getColorYAxisLabel());
		yAxisPrimary.getLabel().getCaption().setColor(ColorDefinitionImpl.create(c.get(0), c.get(1), c.get(2)));
		yAxisPrimary.getMajorGrid().getLineAttributes().setVisible(wrapperBean.isYGrid());
		
		
		//scale1
		Scale scale = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getScale();
		if (wrapperBean.isAutoScale()) scale.unsetStepNumber();
		else scale.setStepNumber(wrapperBean.getStepNumber());
		if (wrapperBean.getMin() != null){
			scale.setMin(NumberDataElementImpl.create(wrapperBean.getMin()));
		} else {
			scale.setMin(null);
		}
		if (wrapperBean.getMax() != null){
			scale.setMax(NumberDataElementImpl.create(wrapperBean.getMax()));
		} else {
			scale.setMax(null);
		}
		if (wrapperBean.getFractionDigits() != 0){
			NumberFormatSpecifier format = NumberFormatSpecifierImpl.create();
			format.setFractionDigits(wrapperBean.getFractionDigits());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).setFormatSpecifier(format);
		} else ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).setFormatSpecifier(null);
		
		//scale2
		
						
		if ( scaleNumbers == 2 ){
			Scale scale2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0],true)[1].getScale();
			if (wrapperBean.isAutoScale2()) scale2.unsetStepNumber();
			else scale2.setStepNumber(wrapperBean.getStepNumberScale2());
			if (wrapperBean.getMinScale2() != null){
				scale2.setMin(NumberDataElementImpl.create(wrapperBean.getMinScale2()));
			} else {
				scale2.setMin(null);
			}
			if (wrapperBean.getMaxScale2() != null){
				scale2.setMax(NumberDataElementImpl.create(wrapperBean.getMaxScale2()));
			} else {
				scale2.setMax(null);
			}
			if (wrapperBean.getFractionDigits2() != 0){
				NumberFormatSpecifier format = NumberFormatSpecifierImpl.create();
				format.setFractionDigits(wrapperBean.getFractionDigits2());
				(((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0],true)[1]).setFormatSpecifier(format);
			} else (((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0],true)[1]).setFormatSpecifier(null);
			
			// Y-Axis (scale2)
			Axis axis2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0],true)[1];
			axis2.getTitle().setVisible(wrapperBean.isYAxisTitleVisibleScale2());
			axis2.getTitle().getCaption().setValue(wrapperBean.getYAxisTitleScale2());
			axis2.getTitle().getCaption().getFont().setSize(wrapperBean.getYAxisTitleSizeScale2());
			c = getColor(wrapperBean.getColorYAxisTitleScale2());
			axis2.getTitle().getCaption().setColor(ColorDefinitionImpl.create(c.get(0), c.get(1), c.get(2)));
			axis2.getLabel().setVisible(wrapperBean.isYAxisLabelsVisibleScale2());
			axis2.getLabel().getCaption().getFont().setSize(wrapperBean.getYAxisSizeLabelsScale2());
			c = getColor(wrapperBean.getColorYAxisLabelScale2());
			axis2.getLabel().getCaption().setColor(ColorDefinitionImpl.create(c.get(0), c.get(1), c.get(2)));
			
		}
		
		
		
	}

}
