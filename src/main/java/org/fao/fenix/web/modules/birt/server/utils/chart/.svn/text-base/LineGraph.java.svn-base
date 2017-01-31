package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.Marker;
import org.eclipse.birt.chart.model.attribute.MarkerType;
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
import org.fao.fenix.web.modules.birt.server.utils.MatchValue;

public class LineGraph {

	public ExtendedItemHandle createChart(ReportDesignHandle designHandle,
			ChartWizardBean dataGWT, String rptDesign) throws ExtendedElementException {

		ChartStyle wrapperBean = CreateWrapperBean.getChartStyle(dataGWT, rptDesign);
		
		ExtendedItemHandle chartHandle = designHandle.getElementFactory()
				.newExtendedItem(null, "Chart");

		try {

//			chartHandle.setHeight("258pt");
//			chartHandle.setWidth("445pt");
			chartHandle.setProperty("outputFormat", "PNG");
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP,
					"setScripted");

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
		int countFirstScale = -1;
		int countSecondScale = -1;
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
					
		
//		if (dataGWT.getScaleSecondY()!=null){
//			// Y-Axis (2)
//			yAxis = AxisImpl.create( Axis.ORTHOGONAL );
//			if (dataGWT.getScaleSecondY().equals("Linear")){
//				yAxis.setType(AxisType.LINEAR_LITERAL);
//				yAxisPrimary.setType(AxisType.LINEAR_LITERAL);
//			} else if (dataGWT.getScaleSecondY().equals("Logarithmic")){
//				yAxis.setType(AxisType.LOGARITHMIC_LITERAL);
//				yAxisPrimary.setType(AxisType.LOGARITHMIC_LITERAL);
//			}
//			
//			if (dataGWT.getScaleFirstY().equals("Linear")){
//				yAxisPrimary.setType(AxisType.LINEAR_LITERAL);
//			} else if (dataGWT.getScaleFirstY().equals("Logarithmic")){
//				yAxisPrimary.setType(AxisType.LOGARITHMIC_LITERAL);
//			}
//			
//			
//			yAxis.getMajorGrid( ).setTickStyle( TickStyle.RIGHT_LITERAL );
//			yAxis.setLabelPosition( Position.RIGHT_LITERAL );
//			xAxisPrimary.getAssociatedAxes( ).add( yAxis );
//		}

		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"label\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		List<String> series = new ArrayList<String>();		
		// Y-Series
		int numValuesY=MatchValue.getNumValuesY(dataGWT);
		List<String> labels = MatchValue.getYLabel(dataGWT);
		for (int j = 0; j < numValuesY; j++) {
			LineSeries bs = (LineSeries) LineSeriesImpl.create();
			
			Query query1 = QueryImpl.create("row[\"value" + j + "\"]");//$NON-NLS-1$
			bs.getDataDefinition().add(query1);

			series.add("value"+j);
			
			bs.setConnectMissingValue(false);
			
			bs.getLabel().setVisible(wrapperBean.isValueVisible());
			bs.getLabel().getCaption().getFont().setSize(wrapperBean.getValueSizeLabels());
			
			((Marker) bs.getMarker()).setVisible(true);
			((Marker) bs.getMarker()).setType(MarkerType.CIRCLE_LITERAL);
//			((Marker) bs.getMarker()).setSize(1);
			((Marker) bs.getMarker()).setSize(0);
						
			//smooth line
			//bs.setCurve(true);
			//bs.setLabelPosition(Position.LEFT_LITERAL);
			
			//String condition = "if (row[\"value" + j + "\"] == null) -1; else row[\"value" + j + "\"]";
			//bs.getTriggers( ).add( TriggerImpl.create( TriggerCondition.ONMOUSEOVER_LITERAL, ActionImpl.create( ActionType.SHOW_TOOLTIP_LITERAL, TooltipValueImpl.create(500, condition) ) ) );
			
			
			
			SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
			
			List<Integer> rgbColor = ChartColors.getRightColorWhenChartisRefreshed(wrapperBean.getSeriesColor(), j);
						
			
			bs.getLineAttributes().setColor(ColorDefinitionImpl.create(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2)));
			bs.getLineAttributes().setThickness(2);
			((Marker) bs.getMarker()).setFill(ColorDefinitionImpl.create(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2)));
			

			if (!dataGWT.isDoubleScale()){
				bs.setSeriesIdentifier(labels.get(j));
				yAxisPrimary.getSeriesDefinitions().add(sdY1);
				sdY1.getSeries().add(bs);
			} else{
				if (dataGWT.getScalesMap().get(getDatasetIdByCountLabel(j, dataGWT.getDimensionValuesY(), datasetIdList)).equals("FirstScale")){
					countFirstScale++;
					//bs.setSeriesIdentifier("(Y1) " + dataGWT.getDimensionValuesY().get(getDatasetIdByCountLabel(j, dataGWT.getDimensionValuesY(), datasetIdList)).get(countFirstScale).get(1));
					bs.setSeriesIdentifier("(Y1) " + labels.get(j)); 
					yAxisPrimary.getSeriesDefinitions().add(sdY1);
					sdY1.getSeries().add(bs);
				} else if (dataGWT.getScalesMap().get(getDatasetIdByCountLabel(j, dataGWT.getDimensionValuesY(), datasetIdList)).equals("SecondScale")){
					countSecondScale++;
					//bs.setSeriesIdentifier("(Y2)" + dataGWT.getDimensionValuesY().get(getDatasetIdByCountLabel(j, dataGWT.getDimensionValuesY(), datasetIdList)).get(countSecondScale).get(1));
					bs.setSeriesIdentifier("(Y2)" + labels.get(j));
					yAxis.getSeriesDefinitions().add(sdY1);
					sdY1.getSeries().add(bs);
				}
			}
			
		}
		
		BoundColumns.setBoundColumns(chartHandle, "label", series);

		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);

		return chartHandle;

	}
	
	private String getDatasetIdByCountLabel(int step, Map<String, List<List<String>>> dimY, List<String> datasetIdList){
		
		long count = 0L;
		for (String dsId : datasetIdList){
			count += dimY.get(dsId).size();
			if ( step < count) return dsId;
		}
				
		return null;
		
	}
}
