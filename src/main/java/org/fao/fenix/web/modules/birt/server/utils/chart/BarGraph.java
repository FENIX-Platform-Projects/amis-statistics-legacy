package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.Fill;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
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
import org.eclipse.birt.chart.model.type.impl.BarSeriesImpl;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.server.utils.MatchValue;

public class BarGraph{

	public ExtendedItemHandle createChart(ReportDesignHandle designHandle,
			ChartWizardBean dataGWT, String rptDesign) throws ExtendedElementException {

		
		ChartStyle wrapperBean = CreateWrapperBean.getChartStyle(dataGWT, rptDesign);
		
		ExtendedItemHandle chartHandle = designHandle.getElementFactory()
				.newExtendedItem(null, "Chart");

		
		
		try {

			//chartHandle.setHeight("258pt");
			//chartHandle.setWidth("445pt");
			chartHandle.setProperty("outputFormat", "PNG");
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP,
					"setScripted");
			

		} catch (SemanticException e) {
			e.printStackTrace();
		}

		ChartWithAxes cwaBar = ChartWithAxesImpl.create();
		cwaBar.setType("Bar");
		
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
		// Y-Series
		int numValuesY=MatchValue.getNumValuesY(dataGWT);
		List<String> labels = MatchValue.getYLabel(dataGWT);
		for (int j = 0; j < numValuesY; j++) {
			BarSeries bs = (BarSeries) BarSeriesImpl.create();
			
			bs.getLabel().setVisible(wrapperBean.isValueVisible());
			bs.getLabel().getCaption().getFont().setSize(wrapperBean.getValueSizeLabels());
			
			bs.setStacked(wrapperBean.isStacked());
			
//			if (dataGWT.getDisposition().equals("Stacked")){
//				bs.setStacked(true);
//			} else if (dataGWT.getDisposition().equals("Side by Side")){
//				bs.setLabelPosition(Position.OUTSIDE_LITERAL);
//			}
									
			bs.setSeriesIdentifier(labels.get(j));
			
			Query query1 = QueryImpl.create("row[\"value" + j + "\"]");
			bs.getDataDefinition().add(query1);

			series.add("value"+j);
			
			bs.setRiserOutline(null);
						
			/*
			bs.getTriggers( )
			.add( TriggerImpl.create( TriggerCondition.ONMOUSEOVER_LITERAL,
					ActionImpl.create( ActionType.SHOW_TOOLTIP_LITERAL,
							TooltipValueImpl.create( 500, "if (row[\"value" + j + "\"] == null) -1; else row[\"value" + j + "\"]" ) ) ) );
			*/

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

		BoundColumns.setBoundColumns(chartHandle, "label", series);
		
		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);

		return chartHandle;

	}

}
