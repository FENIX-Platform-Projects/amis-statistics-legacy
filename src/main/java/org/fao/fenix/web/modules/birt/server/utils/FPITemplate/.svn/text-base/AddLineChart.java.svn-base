package org.fao.fenix.web.modules.birt.server.utils.FPITemplate;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.Anchor;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.LineStyle;
import org.eclipse.birt.chart.model.attribute.Marker;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.TickStyle;
import org.eclipse.birt.chart.model.attribute.impl.BoundsImpl;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.Query;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.QueryImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithAxesImpl;
import org.eclipse.birt.chart.model.layout.Legend;
import org.eclipse.birt.chart.model.layout.Plot;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.chart.model.type.impl.LineSeriesImpl;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.web.modules.birt.server.utils.chart.BoundColumns;

public class AddLineChart {

	public static ExtendedItemHandle getChart(ReportDesignHandle designHandle, String xRow, String yRow) throws ExtendedElementException {

		ExtendedItemHandle chartHandle = designHandle.getElementFactory().newExtendedItem(null, "Chart");

		try {

			chartHandle.setHeight("258pt");
			chartHandle.setWidth("445pt");
			chartHandle.setProperty("outputFormat", "PNG");
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP, "setScripted");

		} catch (SemanticException e) {
			e.printStackTrace();
		}

		ChartWithAxes cwaBar = ChartWithAxesImpl.create();
		cwaBar.setType("Line");

		// Plot
		cwaBar.getBlock().setBackground(ColorDefinitionImpl.WHITE());

		cwaBar.getBlock().setBounds(BoundsImpl.create(0, 0, 400, 230));

		Plot p = cwaBar.getPlot();
		// p.setBounds(BoundsImpl.create(0,0,120,100));
		p.getClientArea().setBackground(ColorDefinitionImpl.create(0, 114, 188));
		
		
		// Title
		cwaBar.getTitle().getLabel().getCaption().setValue("");
		cwaBar.getTitle().getLabel().getCaption().getFont().setSize(15);

		// Trasposed axis
		// cwaBar.setTransposed(dataGWT.isFlip());

		// Legend
		Legend lg = cwaBar.getLegend();
		LineAttributes lia = lg.getOutline();
		lg.getText().getFont().setSize(13);
		lia.setStyle(LineStyle.SOLID_LITERAL);
		

		// ManageLegend.modifyLegend(dataGWT, lg);

		lg.getInsets().set(10, 5, 0, 0);
		lg.getOutline().setVisible(false);
		lg.setAnchor(Anchor.NORTH_LITERAL);
		lg.setBounds(BoundsImpl.create(0, 0, 50, 30));

		// X-Axis
		Axis xAxisPrimary = cwaBar.getPrimaryBaseAxes()[0];
		xAxisPrimary.setType(AxisType.TEXT_LITERAL);
		xAxisPrimary.getMajorGrid().setTickStyle(TickStyle.BELOW_LITERAL);
		xAxisPrimary.getMajorGrid().getLineAttributes().setVisible(true);
		xAxisPrimary.getOrigin().setType(IntersectionType.VALUE_LITERAL);
		xAxisPrimary.getTitle().setVisible(true);
		xAxisPrimary.getTitle().getCaption().setValue(xRow);
		xAxisPrimary.getTitle().getCaption().getFont().setSize(12);
		xAxisPrimary.getLabel().getCaption().getFont().setSize(10);
		xAxisPrimary.getLabel().getCaption().getFont().setRotation(45);
		
		// Y-Axis
		Axis yAxisPrimary = cwaBar.getPrimaryOrthogonalAxis(xAxisPrimary);
		yAxisPrimary.getMajorGrid().setTickStyle(TickStyle.LEFT_LITERAL);
		yAxisPrimary.getMajorGrid().getLineAttributes().setVisible(true);
		yAxisPrimary.getTitle().getCaption().getFont().setSize(12);
		yAxisPrimary.getLabel().getCaption().getFont().setSize(10);
		yAxisPrimary.getTitle().setVisible(true);
		yAxisPrimary.getTitle().getCaption().setValue(yRow);

		// X-Series
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"" + xRow + "\"]");//$NON-NLS-1$
		seCategory.getDataDefinition().add(query);

		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		// Y-Series
		List<String> series = new ArrayList<String>();
		LineSeries bs = (LineSeries) LineSeriesImpl.create();

		bs.setSeriesIdentifier("Index");//$NON-NLS-1$
		Query query1 = QueryImpl.create("row[\"" + yRow + "\"]");//$NON-NLS-1$
		bs.getDataDefinition().add(query1);

		series.add(yRow);
		// bs.setRiserOutline(null);

		bs.getLabel().setVisible(false);

		((Marker) bs.getMarker()).setVisible(false);

		// smooth line
		// bs.setCurve(true);
		bs.setLabelPosition(Position.LEFT_LITERAL);
		bs.getLabel().getCaption().getFont().setSize(10);

		SeriesDefinition sdY1 = SeriesDefinitionImpl.create();
		//int red = (int) Math.round((Math.random() * 255));
		//int green = (int) Math.round((Math.random() * 255));
		//int blue = (int) Math.round((Math.random() * 255));

		bs.getLineAttributes().setColor(ColorDefinitionImpl.create(255, 242, 0));
		bs.getLineAttributes().setThickness(4);

		yAxisPrimary.getSeriesDefinitions().add(sdY1);
		sdY1.getSeries().add(bs);

		BoundColumns.setBoundColumns(chartHandle, xRow, series);
		chartHandle.getReportItem().setProperty("chart.instance", cwaBar);

		return chartHandle;

	}

}
