package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.Fill;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.Marker;
import org.eclipse.birt.chart.model.attribute.MarkerType;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.Query;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.QueryImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithAxesImpl;
import org.eclipse.birt.chart.model.type.BarSeries;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.chart.model.type.impl.BarSeriesImpl;
import org.eclipse.birt.chart.model.type.impl.LineSeriesImpl;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.server.utils.MatchValue;

public class BarLineGraph {

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
		cwaBar.setType("Bar/Line");
		
		FormatChart.setChartStyle(wrapperBean, cwaBar);		
		
				
		// X-Axis
		Axis xAxisPrimary = cwaBar.getPrimaryBaseAxes()[0];
		xAxisPrimary.getOrigin().setType(IntersectionType.MIN_LITERAL);
		xAxisPrimary.getLabel().getCaption().getFont().setRotation(Double.valueOf(dataGWT.getRotateXLabels()));
		
		// Y-Axis
		Axis yAxisPrimary = cwaBar.getPrimaryOrthogonalAxis(xAxisPrimary);
		
		
		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"label\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		List<String> series = new ArrayList<String>();
		// Y-Series BAR
		List<List<String>> labels = MatchValue.getYLabelBarLine(dataGWT);
		List<String> labelBar = labels.get(0);
		for (int j = 0; j < labelBar.size(); j++) {
			
				// draw a bar
				BarSeries bs = (BarSeries) BarSeriesImpl.create();

				bs.getLabel().setVisible(wrapperBean.isValueVisible());
				bs.getLabel().getCaption().getFont().setSize(wrapperBean.getValueSizeLabels());
				
				bs.setStacked(wrapperBean.isStacked());
				
//				if (dataGWT.getDisposition().equals("Stacked")) {
//					bs.setStacked(true);
//				} else if (dataGWT.getDisposition().equals("Side by Side")) {
//					bs.setLabelPosition(Position.OUTSIDE_LITERAL);
//				}

				bs.setSeriesIdentifier(labelBar.get(j));//$NON-NLS-1$
				Query query1 = QueryImpl.create("row[\"value" + j + "\"]");//$NON-NLS-1$
				bs.getDataDefinition().add(query1);

				series.add("value"+j);
				
				bs.setRiserOutline(null);

				SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
				List<Integer> rgbColor = ChartColors.getRightColorWhenChartisRefreshed(wrapperBean.getSeriesColor(), j);
				
				final Fill[] fiaBase = {
						ColorDefinitionImpl.create(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2)),
						
				};
				sdY1.getSeriesPalette().getEntries().clear();
				for (int i = 0; i < fiaBase.length; i++) {
					sdY1.getSeriesPalette().getEntries().add(fiaBase[i]);
				}

				yAxisPrimary.getSeriesDefinitions().add(sdY1);
				sdY1.getSeries().add(bs);
			}
		
		// Y-Series LINE
		List<String> labelLine = labels.get(1);
		for (int j = 0; j < labelLine.size(); j++) {
			// draw a line
			LineSeries bs = (LineSeries) LineSeriesImpl.create();
			
			bs.setSeriesIdentifier(labelLine.get(j));//$NON-NLS-1$
			Query query1 = QueryImpl.create("row[\"value" + (labelBar.size() + j) + "\"]");//$NON-NLS-1$
			bs.getDataDefinition().add(query1);

			series.add("value"+(labelBar.size() + j));
			
			bs.setConnectMissingValue(false);

			bs.getLabel().setVisible(wrapperBean.isValueVisible());
			bs.getLabel().getCaption().getFont().setSize(wrapperBean.getValueSizeLabels());
			
			((Marker) bs.getMarker()).setVisible(true);
			((Marker) bs.getMarker()).setType(MarkerType.CIRCLE_LITERAL);
			((Marker) bs.getMarker()).setSize(1);
			
			//smooth line
			//bs.setCurve(true);
			bs.setLabelPosition(Position.LEFT_LITERAL);

			SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
			List<Integer> rgbColor = ChartColors.getRightColorWhenChartisRefreshed(wrapperBean.getSeriesColor(), (j+labelBar.size()));
			
			bs.getLineAttributes().setColor(ColorDefinitionImpl.create(rgbColor.get(0), rgbColor.get(1), rgbColor.get(2)));
			bs.getLineAttributes().setThickness(2);

			yAxisPrimary.getSeriesDefinitions().add(sdY1);
			sdY1.getSeries().add(bs);
		}
				
		BoundColumns.setBoundColumns(chartHandle, "label", series);		

		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);

		return chartHandle;

	}

}
