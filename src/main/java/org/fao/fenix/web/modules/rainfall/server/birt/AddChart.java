package org.fao.fenix.web.modules.rainfall.server.birt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.Anchor;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.ChartDimension;
import org.eclipse.birt.chart.model.attribute.Fill;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.LineStyle;
import org.eclipse.birt.chart.model.attribute.Marker;
import org.eclipse.birt.chart.model.attribute.MarkerType;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.TickStyle;
import org.eclipse.birt.chart.model.attribute.impl.BoundsImpl;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.attribute.impl.FontDefinitionImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.Query;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.NumberDataElementImpl;
import org.eclipse.birt.chart.model.data.impl.QueryImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithAxesImpl;
import org.eclipse.birt.chart.model.layout.Legend;
import org.eclipse.birt.chart.model.layout.Plot;
import org.eclipse.birt.chart.model.type.BarSeries;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.chart.model.type.impl.BarSeriesImpl;
import org.eclipse.birt.chart.model.type.impl.LineSeriesImpl;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.core.domain.rainfall.RainfallBean;
import org.fao.fenix.web.modules.birt.server.utils.chart.BoundColumns;

public class AddChart {
	
	static public ExtendedItemHandle getChart(ReportDesignHandle designHandle,	RainfallBean rainfallBean, int count, Double maxScale) throws ExtendedElementException, SemanticException {

		ExtendedItemHandle chartHandle = designHandle.getElementFactory().newExtendedItem(null, "Chart");

		try {

//			chartHandle.setHeight("258pt");
//			chartHandle.setWidth("445pt");
			chartHandle.setProperty("outputFormat", "PNG");
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP,
					"setScripted" + count);

		} catch (SemanticException e) {
			e.printStackTrace();
		}

		
		ChartWithAxes cwaBar = ChartWithAxesImpl.create();
		//cwaBar.setType("Bar/Line");
		
		// Plot
		cwaBar.getBlock().getOutline().setVisible(true);
		cwaBar.getBlock().getOutline().setColor(ColorDefinitionImpl.create(208, 221, 237));

		cwaBar.getBlock().setBounds(BoundsImpl.create(0, 0, 500, 230));

		Plot p = cwaBar.getPlot();
		// p.setBounds(BoundsImpl.create(0,0,120,100));
		p.getClientArea().setBackground(ColorDefinitionImpl.create(255, 255, 255));

		// Title
		cwaBar.getTitle().getLabel().getCaption().setValue(rainfallBean.getTitle());
		cwaBar.getTitle().getLabel().getCaption().getFont().setSize(8);
		cwaBar.getTitle().getLabel().getCaption().getFont().setBold(true);
		cwaBar.getTitle().getLabel().getCaption().getFont().setName("Arial");
		//cwaBar.getTitle().getLabel().getOutline().setVisible(true);
		//cwaBar.getTitle().getLabel().getOutline().setColor(ColorDefinitionImpl.BLUE());
		
		// Legend
		Legend lg = cwaBar.getLegend();
		LineAttributes lia = lg.getOutline();
		lg.getText().getFont().setSize(10);
		lg.getText().getFont().setName("Arial");
		lia.setStyle(LineStyle.SOLID_LITERAL);
		lg.setVisible(true);
		lg.setPosition(Position.RIGHT_LITERAL);
						
		lg.getInsets().set(10, 5, 0, 0);
		lg.getOutline().setVisible(false);
		lg.setAnchor(Anchor.NORTH_LITERAL);
		lg.setBounds(BoundsImpl.create(0, 0, 50, 20));

		// X-Axis
		Axis xAxisPrimary = cwaBar.getPrimaryBaseAxes()[0];
		xAxisPrimary.setType(AxisType.TEXT_LITERAL);
		xAxisPrimary.getMajorGrid().setTickStyle(TickStyle.BELOW_LITERAL);
		xAxisPrimary.getOrigin().setType(IntersectionType.VALUE_LITERAL);
		xAxisPrimary.getTitle().setVisible(true);
		xAxisPrimary.getTitle().getCaption().setValue(rainfallBean.getXLabel());
		xAxisPrimary.getTitle().getCaption().setFont(FontDefinitionImpl.create("Arial", 8, false, false, false, false, false, 0, null));
		xAxisPrimary.getTitle().getCaption().getFont().setSize(8);
		xAxisPrimary.getTitle().getCaption().getFont().setBold(true);
		//xAxisPrimary.getLabel().setVisible(dataGWT.isXAxisShowLabels());
		xAxisPrimary.getLabel().getCaption().getFont().setSize(8);
		xAxisPrimary.getLabel().getCaption().getFont().setRotation(45);
		
		// Y-Axis
		Axis yAxisPrimary = cwaBar.getPrimaryOrthogonalAxis(xAxisPrimary);
		yAxisPrimary.getMajorGrid().setTickStyle(TickStyle.LEFT_LITERAL);
		yAxisPrimary.getTitle().setVisible(true);
		yAxisPrimary.getTitle().getCaption().setValue(rainfallBean.getYLabel());
		yAxisPrimary.getTitle().getCaption().setFont(FontDefinitionImpl.create("Arial", 8, false, false, false, false, false, 90, null));
		yAxisPrimary.getTitle().getCaption().getFont().setSize(8);
		yAxisPrimary.getTitle().getCaption().getFont().setBold(true);
		//yAxisPrimary.getLabel().setVisible(dataGWT.isYAxisShowLabels());
		yAxisPrimary.getLabel().getCaption().getFont().setSize(8);
		
		// set a fix scale?
		yAxisPrimary.getScale().setMax(NumberDataElementImpl.create(maxScale));
		
		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"date\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		
		// Y-Series BAR
		//List<List<String>> labels = MatchValue.getYLabelBarLine(dataGWT);
		//List<String> labelBar = labels.get(0);
		List<String> series = new ArrayList<String>();
		int numBars = 1;
		if (rainfallBean.getSecondIstogramValues().size() > 0) numBars++;
		for (int j = 0; j < numBars; j++) {
			
				// draw a bar
				BarSeries bs = (BarSeries) BarSeriesImpl.create();

				bs.getLabel().setVisible(false);
				bs.setLabelPosition(Position.OUTSIDE_LITERAL);
				bs.getLabel().getCaption().getFont().setSize(10);
				
				if (j==0) bs.setSeriesIdentifier(rainfallBean.getYearOneRange());
				else bs.setSeriesIdentifier(rainfallBean.getYearTwoRange());
				Query query1 = QueryImpl.create("row[\"value" + j + "\"]");//$NON-NLS-1$
				bs.getDataDefinition().add(query1);

				series.add("value"+j);
				bs.setRiserOutline(null);

				SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
//				int red = (int) Math.round((Math.random() * 255));
//				int green = (int) Math.round((Math.random() * 255));
//				int blue = (int) Math.round((Math.random() * 255));
				Fill[] fiaBase=new Fill[1];
				if (j==0){
					fiaBase[0] = ColorDefinitionImpl.create(29, 68, 137);
				} else {
					fiaBase[0] = ColorDefinitionImpl.create(208, 221, 237);
				}
				
				sdY1.getSeriesPalette().getEntries().clear();
				for (int i = 0; i < fiaBase.length; i++) {
					sdY1.getSeriesPalette().getEntries().add(fiaBase[i]);
				}

				yAxisPrimary.getSeriesDefinitions().add(sdY1);
				sdY1.getSeries().add(bs);
			}
		
			// Y-Series LINE
		
		//for (int j = 0; j < labelLine.size(); j++) {
			// draw a line
		if (rainfallBean.getLineValues().size()>0){	
			LineSeries bs = (LineSeries) LineSeriesImpl.create();
			
			bs.setSeriesIdentifier("Avg " + rainfallBean.getAverageRange());//$NON-NLS-1$
			// bs.setSeriesIdentifier("Average");
			Query query1=null;
			if (rainfallBean.getSecondIstogramValues().size() > 0){
				query1 = QueryImpl.create("row[\"value2\"]");
				series.add("value2");
			} else {
				query1 = QueryImpl.create("row[\"value1\"]");
				series.add("value1");
			}
			bs.getDataDefinition().add(query1);

			// bs.setRiserOutline(null);

			bs.getLabel().setVisible(false);
			
			((Marker) bs.getMarker()).setVisible(true);
			((Marker) bs.getMarker()).setType(MarkerType.CIRCLE_LITERAL);
			((Marker) bs.getMarker()).setSize(1);
			
			//smooth line
			//bs.setCurve(true);
			bs.setLabelPosition(Position.LEFT_LITERAL);

			SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
//			int red = (int) Math.round((Math.random() * 255));
//			int green = (int) Math.round((Math.random() * 255));
//			int blue = (int) Math.round((Math.random() * 255));
			
			bs.getLineAttributes().setColor(ColorDefinitionImpl.create(202, 22, 22));
			bs.getLineAttributes().setThickness(2);

			yAxisPrimary.getSeriesDefinitions().add(sdY1);
			sdY1.getSeries().add(bs);
		}
		
		
		

		cwaBar.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
		
		BoundColumns.setBoundColumns(chartHandle, "date", series);
		
		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);
		
		if (!rainfallBean.isHasTable())
			chartHandle.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");

		return chartHandle;

	}

}
