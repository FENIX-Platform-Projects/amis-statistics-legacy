package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.io.File;

import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.ChartWithoutAxes;
import org.eclipse.birt.chart.model.attribute.NumberFormatSpecifier;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Scale;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.NumberDataElementImpl;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.FormatChartVo;
import org.fao.fenix.web.modules.core.server.utils.Setting;

import com.ibm.icu.util.ULocale;

public class CreateWrapperBean {
	
	static ChartStyle getChartStyle(ChartWizardBean dataGWT, String rptDesign){
		ChartStyle chartStyle = new ChartStyle();

		//by chart wizard
		if (rptDesign.equals("")){
			
			
			//size
			if (  dataGWT.getWidth() != null)
				chartStyle.setWidth(Integer.valueOf(dataGWT.getWidth()));
			else	
				chartStyle.setWidth(400);
			
			if (  dataGWT.getHeight() != null)
				chartStyle.setHeight(Integer.valueOf(dataGWT.getHeight()));
			else	
				chartStyle.setHeight(230);
			
			//background
			chartStyle.setBackgroundPlotArea("255_255_255");
			chartStyle.setBackgroundChartArea("255_255_255");
			
			//series color
			// RANDOM
			
			chartStyle.setFlip(dataGWT.isFlip());
			chartStyle.setDim2_3D(dataGWT.getDim2_3D());
			if (dataGWT.getDisposition().equals("Stacked")){
				chartStyle.setStacked(true);
			} else if (dataGWT.getDisposition().equals("Side by Side")){
				chartStyle.setStacked(false);
			}
			
			
			//title
			chartStyle.setTitle(dataGWT.getTitle());
			chartStyle.setSizeTitle(15);
			chartStyle.setColorTitle("0_0_0");
			
			//x axis
			chartStyle.setXAxisTitleVisible(true);
			chartStyle.setXAxisTitle(dataGWT.getXAxisTitle());
			chartStyle.setXAxisTitleSize(15);
			chartStyle.setColorXAxisTitle("0_0_0");
			chartStyle.setXAxisLabelsVisible(dataGWT.isXAxisShowLabels());
			chartStyle.setXAxisSizeLabels(10);
			chartStyle.setColorXAxisLabel("0_0_0");
						
			//y axis (scale 1)
			chartStyle.setYAxisTitleVisible(true);
			chartStyle.setYAxisTitle(dataGWT.getYAxisTitle());
			chartStyle.setYAxisTitleSize(15);
			chartStyle.setColorYAxisTitle("0_0_0");
			chartStyle.setYAxisLabelsVisible(dataGWT.isYAxisShowLabels());
			chartStyle.setYAxisSizeLabels(10);
			chartStyle.setColorYAxisLabel("0_0_0");
			
			//y axis (scale 2)
			chartStyle.setYAxisTitleVisibleScale2(true);
			chartStyle.setYAxisTitleScale2(dataGWT.getYSecondAxisTitle());
			chartStyle.setYAxisTitleSizeScale2(15);
			chartStyle.setColorYAxisTitleScale2("0_0_0");
			chartStyle.setYAxisLabelsVisibleScale2(true);
			chartStyle.setYAxisSizeLabelsScale2(10);
			chartStyle.setColorYAxisLabelScale2("0_0_0");
			
			//values
			chartStyle.setValueVisible(false);
			chartStyle.setValueSizeLabels(10);
			
			//legend
			chartStyle.setShowLegend(dataGWT.isShowLegend());
			chartStyle.setPosLegend(dataGWT.getPosLegend());
			chartStyle.setSizeLegend(10);
			chartStyle.setUnitSpacing(50);
			
			//scale1
			chartStyle.setAutoScale(true);
			//chartStyle.setStepNumber(stepNumber);
			chartStyle.setMin(null);
			chartStyle.setMax(null);
			
			
			//scale2
			chartStyle.setAutoScale2(true);
			//chartStyle.setStepNumber(stepNumber);
			chartStyle.setMinScale2(null);
			chartStyle.setMaxScale2(null);
			
			
		} /* by chart viewer*/ 
		else {

			DesignConfig dConfig = new DesignConfig();
			dConfig.setBIRTHome(Setting.getReportEngine());

			DesignEngine dEngine = new DesignEngine(dConfig);
			// Create a session handle, using the system locale.
			SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
			// Create a handle for an existing report design.
			String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
			ReportDesignHandle design = null;
			try {
				design = session.openDesign(name);
				DesignElementHandle ex = design.findElement("NewChart");
				ExtendedItemHandle eHandle = (ExtendedItemHandle) ex;
				Chart chart = (Chart) eHandle.getReportItem().getProperty("chart.instance");
				
				//size
				chartStyle.setWidth(chart.getBlock().getBounds().getWidth());
				chartStyle.setHeight(chart.getBlock().getBounds().getHeight());
				
				//background Plot Area
				if (!dataGWT.getChartType().equals("Pie")){
					ColorDefinitionImpl backgroundColorPA = (ColorDefinitionImpl) chart.getPlot().getClientArea().getBackground();
					chartStyle.setBackgroundPlotArea(backgroundColorPA.getRed() + "_" + backgroundColorPA.getGreen() + "_" + backgroundColorPA.getBlue());
					ColorDefinitionImpl backgroundColorCA = (ColorDefinitionImpl) chart.getBlock().getBackground();
					chartStyle.setBackgroundChartArea(backgroundColorCA.getRed() + "_" + backgroundColorCA.getGreen() + "_" + backgroundColorCA.getBlue());
				} else {
					chartStyle.setBackgroundPlotArea("255_255_255");
					chartStyle.setBackgroundChartArea("255_255_255");
				}
				 
				chartStyle.setDim2_3D(chart.getDimension().getLiteral()); 
				if (!dataGWT.getChartType().equals("Line") && !dataGWT.getChartType().equals("Area") && !dataGWT.getChartType().equals("Pie") && !dataGWT.getChartType().equals("Scatter")){
					SeriesDefinition s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(0);
					if (((Series) s.getSeries().get(0)).isStacked()) chartStyle.setStacked(true);
					else chartStyle.setStacked(false);
				}
				
				//series color
				chartStyle.setSeriesColor(ChartColors.getChartColors(chart, "dec"));
				
				//title
				chartStyle.setTitle(chart.getTitle().getLabel().getCaption().getValue());
				chartStyle.setSizeTitle((int) chart.getTitle().getLabel().getCaption().getFont().getSize());
				ColorDefinitionImpl colorTitle = (ColorDefinitionImpl) chart.getTitle().getLabel().getCaption().getColor();
				chartStyle.setColorTitle(colorTitle.getRed() + "_" + colorTitle.getGreen() + "_" + colorTitle.getBlue());
				
				
				if (!dataGWT.getChartType().equals("Pie")){
					int scaleNumbers = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true).length;
					
					//x axis
					chartStyle.setXAxisTitleVisible(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().isVisible());
					chartStyle.setXAxisTitle(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getValue());
					chartStyle.setXAxisTitleSize((int) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().getSize());
					ColorDefinitionImpl colorXAxisTitle = (ColorDefinitionImpl) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getColor();
					chartStyle.setXGrid(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getMajorGrid().getLineAttributes().isVisible());
					chartStyle.setColorXAxisTitle(colorXAxisTitle.getRed() + "_" + colorXAxisTitle.getGreen() + "_" + colorXAxisTitle.getBlue());
					chartStyle.setXAxisLabelsVisible(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().isVisible());
					chartStyle.setXAxisSizeLabels((int) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().getSize());
					ColorDefinitionImpl colorXAxisLabel = (ColorDefinitionImpl) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getColor();
					chartStyle.setColorXAxisLabel(colorXAxisLabel.getRed() + "_" + colorXAxisLabel.getGreen() + "_" + colorXAxisLabel.getBlue());
										
					//y axis (Scale 1)
					chartStyle.setYAxisTitleVisible(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().isVisible());
					chartStyle.setYAxisTitle(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getValue());
					chartStyle.setYAxisTitleSize((int) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().getSize());
					ColorDefinitionImpl colorYAxisTitle = (ColorDefinitionImpl) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getColor();
					chartStyle.setColorYAxisTitle(colorYAxisTitle.getRed() + "_" + colorYAxisTitle.getGreen() + "_" + colorYAxisTitle.getBlue());
					chartStyle.setYAxisLabelsVisible(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().isVisible());
					chartStyle.setYGrid(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getMajorGrid().getLineAttributes().isVisible());
					chartStyle.setYAxisSizeLabels((int) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().getSize());
					ColorDefinitionImpl colorYAxisLabel = (ColorDefinitionImpl) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getColor();
					chartStyle.setColorYAxisLabel(colorYAxisLabel.getRed() + "_" + colorYAxisLabel.getGreen() + "_" + colorYAxisLabel.getBlue());
					
					
					//y axis (Scale 2)
					if ( scaleNumbers == 2 ){
						Axis axis2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0],true)[1];
						
						chartStyle.setYAxisTitleVisibleScale2(axis2.getTitle().isVisible());
						chartStyle.setYAxisTitleScale2(axis2.getTitle().getCaption().getValue());
						chartStyle.setYAxisTitleSizeScale2((int) axis2.getTitle().getCaption().getFont().getSize());
						ColorDefinitionImpl colorY2AxisTitle = (ColorDefinitionImpl) axis2.getTitle().getCaption().getColor();
						chartStyle.setColorYAxisTitleScale2(colorY2AxisTitle.getRed() + "_" + colorY2AxisTitle.getGreen() + "_" + colorY2AxisTitle.getBlue());
						chartStyle.setYAxisLabelsVisibleScale2(axis2.getLabel().isVisible());
						chartStyle.setYAxisSizeLabelsScale2((int) axis2.getLabel().getCaption().getFont().getSize());
						ColorDefinitionImpl colorY2AxisLabel = (ColorDefinitionImpl) axis2.getLabel().getCaption().getColor();
						chartStyle.setColorYAxisLabelScale2(colorY2AxisLabel.getRed() + "_" + colorY2AxisLabel.getGreen() + "_" + colorY2AxisLabel.getBlue());
					}
				
				}
				
				//values
				SeriesDefinition s;
				if (!dataGWT.getChartType().equals("Pie")){
					s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(0);
				} else {
					s = (SeriesDefinition) ((ChartWithoutAxes) chart).getSeriesDefinitions().get(0);
				}
				Series ob = (Series) s.getSeries().get(0);
				chartStyle.setValueVisible(ob.getLabel().isVisible());
				chartStyle.setValueSizeLabels((int) ob.getLabel().getCaption().getFont().getSize());
				
				
				//legend
				chartStyle.setShowLegend(chart.getLegend().isVisible());
				chartStyle.setPosLegend(chart.getLegend().getPosition().getLiteral());
				chartStyle.setSizeLegend((int) chart.getLegend().getText().getFont().getSize());
				if (!dataGWT.getChartType().equals("Pie")){
					chartStyle.setUnitSpacing(((ChartWithAxes) chart).getUnitSpacing());
					chartStyle.setFlip(((ChartWithAxes) chart).isTransposed());
				}
					
				
				if (!dataGWT.getChartType().equals("Pie")){
					int scaleNumbers = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true).length;
					//scale1
					Scale scale = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getScale();
					chartStyle.setAutoScale(!scale.isSetStepNumber());
					chartStyle.setStepNumber(scale.getStepNumber());
					if (scale.getMin() != null)
						chartStyle.setMin(((NumberDataElementImpl) scale.getMin()).getValue());
					else chartStyle.setMin(null);
					if (scale.getMax() != null)
						chartStyle.setMax(((NumberDataElementImpl) scale.getMax()).getValue());
					else chartStyle.setMax(null);
					NumberFormatSpecifier format = (NumberFormatSpecifier) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getFormatSpecifier();
					if (format != null) chartStyle.setFractionDigits(format.getFractionDigits());
					else chartStyle.setFractionDigits(0);
					
					//scale2
													
					if ( scaleNumbers == 2 ){
						Scale scale2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0],true)[1].getScale(); 
						chartStyle.setAutoScale2(!scale2.isSetStepNumber());
						chartStyle.setStepNumberScale2(scale2.getStepNumber());
						if (scale2.getMin() != null)
							chartStyle.setMinScale2(((NumberDataElementImpl) scale2.getMin()).getValue());
						else chartStyle.setMinScale2(null);
						if (scale2.getMax() != null)
							chartStyle.setMaxScale2(((NumberDataElementImpl) scale2.getMax()).getValue());
						else chartStyle.setMaxScale2(null);
						
						NumberFormatSpecifier format2 = (NumberFormatSpecifier) (NumberFormatSpecifier) (((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0],true)[1]).getFormatSpecifier();
						if (format2 != null) chartStyle.setFractionDigits2(format2.getFractionDigits());
						else chartStyle.setFractionDigits2(0);
					
					}
				}
				
					
			}
			catch (Exception e) {
				System.err.println("Report " + name + " not opened!\nReason is " + e.toString());

			}
			
		}
		
		return chartStyle;
	}
	
	
	static ChartStyle getChartStyle(ChartWizardBean dataGWT, FormatChartVo formatChart){
		ChartStyle chartStyle = new ChartStyle();
		
			
			//size
			if (  dataGWT.getWidth() != null)
				chartStyle.setWidth(Integer.valueOf(dataGWT.getWidth()));
			else	
				chartStyle.setWidth(400);
			
			if (  dataGWT.getHeight() != null)
				chartStyle.setHeight(Integer.valueOf(dataGWT.getHeight()));
			else	
				chartStyle.setHeight(230);
			
			//background
			chartStyle.setBackgroundPlotArea("255_255_255");
			chartStyle.setBackgroundChartArea("255_255_255");
			
			//series color
			// RANDOM
			
			chartStyle.setFlip(dataGWT.isFlip());
			chartStyle.setDim2_3D(dataGWT.getDim2_3D());
			
			if (dataGWT.getDisposition().equals("Stacked")){
				chartStyle.setStacked(true);
			} else if (dataGWT.getDisposition().equals("Side by Side")){
				chartStyle.setStacked(false);
			}
			System.out.println("->" + dataGWT.getDisposition() + " | " + chartStyle.isStacked());
			
			//title
			chartStyle.setVisibleTitle(formatChart.isTitleVisible());
			chartStyle.setTitle(dataGWT.getTitle());
			chartStyle.setSizeTitle(15);			
			chartStyle.setColorTitle("0_0_0");
			if ( formatChart.getSizeTitle() != null)
				chartStyle.setSizeTitle(formatChart.getSizeTitle());
			else
				chartStyle.setSizeTitle(10);
			
			//x axis
			chartStyle.setXAxisTitleVisible(true);
			chartStyle.setXAxisTitle(dataGWT.getXAxisTitle());
			chartStyle.setXAxisTitleSize(15);
			chartStyle.setColorXAxisTitle("0_0_0");
			chartStyle.setXAxisLabelsVisible(dataGWT.isXAxisShowLabels());
			chartStyle.setXAxisSizeLabels(10);
			chartStyle.setColorXAxisLabel("0_0_0");
			if ( formatChart.getSizeXAxisLabel() != null)
				chartStyle.setXAxisSizeLabels(formatChart.getSizeXAxisLabel());
			else
				chartStyle.setXAxisSizeLabels(10);
						
			//y axis (scale 1)
			chartStyle.setYAxisTitleVisible(true);
			chartStyle.setYAxisTitle(dataGWT.getYAxisTitle());
			chartStyle.setYAxisTitleSize(15);
			chartStyle.setColorYAxisTitle("0_0_0");
			chartStyle.setYAxisLabelsVisible(dataGWT.isYAxisShowLabels());
			chartStyle.setYAxisSizeLabels(10);
			chartStyle.setColorYAxisLabel("0_0_0");
			if ( formatChart.getSizeYAxisLabel() != null)
				chartStyle.setYAxisSizeLabels(formatChart.getSizeYAxisLabel());
			else
				chartStyle.setYAxisSizeLabels(10);
			if ( formatChart.getSizeYAxis() != null)
				chartStyle.setYAxisTitleSize(formatChart.getSizeYAxis());
			else
				chartStyle.setYAxisTitleSize(10);
			
			//y axis (scale 2)
			chartStyle.setYAxisTitleVisibleScale2(true);
			chartStyle.setYAxisTitleScale2(dataGWT.getYSecondAxisTitle());
			chartStyle.setYAxisTitleSizeScale2(15);
			chartStyle.setColorYAxisTitleScale2("0_0_0");
			chartStyle.setYAxisLabelsVisibleScale2(true);
			chartStyle.setYAxisSizeLabelsScale2(10);
			chartStyle.setColorYAxisLabelScale2("0_0_0");
			
			//values
			chartStyle.setValueVisible(false);
			chartStyle.setValueSizeLabels(10);
			
			//legend
			chartStyle.setShowLegend(dataGWT.isShowLegend());
			chartStyle.setPosLegend(dataGWT.getPosLegend());			
			if ( formatChart.getSizeLabel() != null)
				chartStyle.setSizeLegend(formatChart.getSizeLabel());
			else
				chartStyle.setSizeLegend(10);
			chartStyle.setUnitSpacing(50);
			chartStyle.setLegendHeight(formatChart.getLegendHeight());
			chartStyle.setLegendWidth(formatChart.getLegendWidth());
			
			//scale1
			chartStyle.setAutoScale(true);
			//chartStyle.setStepNumber(stepNumber);
			chartStyle.setMin(null);
			chartStyle.setMax(null);
			
			
			//scale2
			chartStyle.setAutoScale2(true);
			//chartStyle.setStepNumber(stepNumber);
			chartStyle.setMinScale2(null);
			chartStyle.setMaxScale2(null);
			
			return chartStyle;
		}

}
