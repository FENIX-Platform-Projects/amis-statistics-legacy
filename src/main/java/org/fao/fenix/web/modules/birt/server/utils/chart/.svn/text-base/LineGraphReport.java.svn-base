package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.Marker;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.TickStyle;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.AxisImpl;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.Query;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.QueryImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithAxesImpl;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.chart.model.type.impl.LineSeriesImpl;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.YSerieBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;

public class LineGraphReport {

	public ExtendedItemHandle createChart(ReportDesignHandle designHandle, ChartWizardBean dataGWT) throws ExtendedElementException {
		System.out.println("LineGraphReport");
		
		ChartStyle wrapperBean = new ChartStyle();
		
		ExtendedItemHandle chartHandle = designHandle.getElementFactory().newExtendedItem(BirtUtil.randomChartName(), "Chart");

		wrapperBean = CreateWrapperBean.getChartStyle(dataGWT, dataGWT.getChartReportBean().getFormatChartVo());
		try {
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP, dataGWT.getChartReportBean().getSetScripted());
//			chartHandle.setHeight("258pt");
//			chartHandle.setWidth("445pt");
			chartHandle.setProperty("outputFormat", "PNG");
		} catch (SemanticException e) {
		
			e.printStackTrace();
		}
		ChartWithAxes cwaBar = ChartWithAxesImpl.create();
		cwaBar.setType("Line");

		// X-Axis
		Axis xAxisPrimary = cwaBar.getPrimaryBaseAxes()[0];
		xAxisPrimary.getOrigin().setType(IntersectionType.MIN_LITERAL);
		
		
		// Y-Axis	
		Axis yAxisPrimary = cwaBar.getPrimaryOrthogonalAxis(xAxisPrimary);
				
		Axis yAxis=null;
		List<String> datasetIdList = null;
		if (dataGWT.isDoubleScale()){
			datasetIdList = new ArrayList<String>();
			
			Iterator<Map.Entry<String, List<List<String>>>> keyValuePairs = dataGWT.getDimensionValuesY().entrySet().iterator();
			for (int i = 0; i < dataGWT.getDimensionValuesY().size(); i++) {
				Map.Entry<String, List<List<String>>> entry = keyValuePairs.next();
				datasetIdList.add(entry.getKey());
			}
			
			yAxis = AxisImpl.create( Axis.ORTHOGONAL );
			yAxis.setType(AxisType.LINEAR_LITERAL);
			yAxis.getMajorGrid( ).setTickStyle( TickStyle.RIGHT_LITERAL );
			yAxis.setLabelPosition( Position.RIGHT_LITERAL );
			yAxis.setTitlePosition(Position.RIGHT_LITERAL);
			yAxis.getTitle().getCaption().getFont().setRotation(-90);
//			yAxis.getTitle().setVisible(true);
//			yAxis.getTitle().getCaption().setValue(dataGWT.getYSecondAxisTitle());
//			yAxis.getTitle().getCaption().getFont().setSize(15);
//			yAxis.getLabel().getCaption().getFont().setSize(10);
			xAxisPrimary.getAssociatedAxes( ).add( yAxis );
			
		}
		
		FormatChart.setChartStyle(wrapperBean, cwaBar);
		
			
		xAxisPrimary.getLabel().getCaption().getFont().setRotation(Double.valueOf(dataGWT.getRotateXLabels()));


		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"label\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		xAxisPrimary.getScale().setStepNumber(10);
		
		
		sdX.getSeries().add(seCategory);
		

		List<String> series = new ArrayList<String>();	
		
		
		// Y-Series
		series = createChartReportYSerie(dataGWT, wrapperBean, yAxisPrimary);
		
		BoundColumns.setBoundColumns(chartHandle, "label", series);
		
		xAxisPrimary.getScale().setAutoExpand(true);

		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);
		

		return chartHandle;

	}
	

	
	
	/** TODO: for now that is made for just one scale **/
	private List<String> createChartReportYSerie(ChartWizardBean dataGWT, ChartStyle wrapperBean, Axis yAxisPrimary) {
		List<String> series = new ArrayList<String>();	
		
		List<YSerieBean> ySeries = dataGWT.getChartReportBean().getChartValues().getySeries();
		List<String> labels = new ArrayList<String>();
		
		for ( YSerieBean y : ySeries)
			labels.add(y.getLabel());
			

		for (int j = 0; j < ySeries.size(); j++) {
			LineSeries bs = (LineSeries) LineSeriesImpl.create();
			
			Query query1 = QueryImpl.create("row[\"value" + j + "\"]");//$NON-NLS-1$
			bs.getDataDefinition().add(query1);

			series.add("value"+j);
			
//			bs.setConnectMissingValue(false);
			bs.setConnectMissingValue(true);
			
			bs.getLabel().setVisible(wrapperBean.isValueVisible());
			bs.getLabel().getCaption().getFont().setSize(wrapperBean.getValueSizeLabels());
			
			((Marker) bs.getMarker()).setVisible(false);

//			((Marker) bs.getMarker()).setType(MarkerType.);
//			((Marker) bs.getMarker()).setSize(1);
			
			SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
			
			List<Integer> rgbColor = null;
			
			if ( ySeries.get(j).getColor() != null) 
					rgbColor = ySeries.get(j).getColor();
			else 
				rgbColor = ChartColors.getRightColorWhenChartisRefreshed(wrapperBean.getSeriesColor(), j);
		
						
			
			bs.getLineAttributes().setColor(ColorDefinitionImpl.create(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2)));
			bs.getLineAttributes().setThickness(2);
			((Marker) bs.getMarker()).setFill(ColorDefinitionImpl.create(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2)));
			
			bs.setSeriesIdentifier(labels.get(j));
			yAxisPrimary.getSeriesDefinitions().add(sdY1);
			sdY1.getSeries().add(bs);
		}
		return series;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
