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
import org.fao.fenix.web.modules.birt.common.vo.YSerieBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;

public class BarGraphReport{

	public ExtendedItemHandle createChart(ReportDesignHandle designHandle, ChartWizardBean dataGWT) throws ExtendedElementException {

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
		series = createChartReportYSerie(dataGWT, wrapperBean, yAxisPrimary);

		BoundColumns.setBoundColumns(chartHandle, "label", series);
		
		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);
		

		return chartHandle;

	}

	
	
	private List<String> createChartReportYSerie(ChartWizardBean dataGWT, ChartStyle wrapperBean, Axis yAxisPrimary) {
		List<String> series = new ArrayList<String>();	
		
		List<YSerieBean> ySeries = dataGWT.getChartReportBean().getChartValues().getySeries();
		List<String> labels = new ArrayList<String>();
		
		for ( YSerieBean y : ySeries)
			labels.add(y.getLabel());
			
		for (int j = 0; j < ySeries.size(); j++) {
			BarSeries bs = (BarSeries) BarSeriesImpl.create();
			
			bs.getLabel().setVisible(wrapperBean.isValueVisible());
			bs.getLabel().getCaption().getFont().setSize(wrapperBean.getValueSizeLabels());
			
	
			bs.setStacked(wrapperBean.isStacked());
			
//			System.out.println("wrapperBean.isStacked(): " + wrapperBean.isStacked());
						
			bs.setSeriesIdentifier(labels.get(j));
			
			Query query1 = QueryImpl.create("row[\"value" + j + "\"]");
			bs.getDataDefinition().add(query1);

			series.add("value"+j);
			
			bs.setRiserOutline(null);
			

			SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
			
			List<Integer> rgbColor = null;
			
			if ( ySeries.get(j).getColor() != null) 
					rgbColor = ySeries.get(j).getColor();
			else 
				rgbColor = ChartColors.getRightColorWhenChartisRefreshed(wrapperBean.getSeriesColor(), j);
			
						
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
		return series;
	}
}
