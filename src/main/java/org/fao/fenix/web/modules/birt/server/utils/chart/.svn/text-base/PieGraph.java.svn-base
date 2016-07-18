package org.fao.fenix.web.modules.birt.server.utils.chart;

import org.eclipse.birt.chart.model.ChartWithoutAxes;
import org.eclipse.birt.chart.model.attribute.Anchor;
import org.eclipse.birt.chart.model.attribute.ChartDimension;
import org.eclipse.birt.chart.model.attribute.DataPointComponent;
import org.eclipse.birt.chart.model.attribute.DataPointComponentType;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.LineStyle;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.impl.BoundsImpl;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.attribute.impl.DataPointComponentImpl;
import org.eclipse.birt.chart.model.attribute.impl.JavaNumberFormatSpecifierImpl;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.Query;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.QueryImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithoutAxesImpl;
import org.eclipse.birt.chart.model.layout.Legend;
import org.eclipse.birt.chart.model.type.PieSeries;
import org.eclipse.birt.chart.model.type.impl.PieSeriesImpl;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;

public class PieGraph {

	public ExtendedItemHandle createChart(ReportDesignHandle designHandle,
			ChartWizardBean dataGWT, String rptDesign) throws ExtendedElementException {

		ChartStyle wrapperBean = CreateWrapperBean.getChartStyle(dataGWT, rptDesign);
		
		ExtendedItemHandle chartHandle = designHandle.getElementFactory()
				.newExtendedItem(null, "Chart");

		int height=dataGWT.getDimensionValuesX().size()*258;
		
		try {

			
			chartHandle.setHeight(String.valueOf(height)+"pt");
						
			chartHandle.setWidth("445pt");
			
			chartHandle.setProperty("outputFormat", "PNG");
			chartHandle.setProperty(ExtendedItemHandle.DATA_SET_PROP,
					"setScripted");

		} catch (SemanticException e) {
			e.printStackTrace();
		}

		
		ChartWithoutAxes cwoaPie = ChartWithoutAxesImpl.create();
		cwoaPie.setType("Pie");
		cwoaPie.setSubType("Standard Pie Chart");
		cwoaPie.getTitle().setVisible(true);
		cwoaPie.getBlock().setBounds(BoundsImpl.create(0, 0, 400, (230*dataGWT.getDimensionValuesX().size())));
		

		// Legend
		Legend lg = cwoaPie.getLegend();
		LineAttributes lia = lg.getOutline();
		lg.getText().getFont().setSize(13);
		lia.setStyle(LineStyle.SOLID_LITERAL);
		
		lg.setPosition(Position.RIGHT_LITERAL);
		//ManageLegend.modifyLegend(wrapperBean.getPosLegend() , lg);
		
		lg.getInsets().set(10, 5, 0, 0);
		lg.getOutline().setVisible(false);
		lg.setAnchor(Anchor.NORTH_LITERAL);
		lg.setBounds(BoundsImpl.create(0, 0, 50, 20));
		
		//cwoaPie.getBlock().getOutline().setVisible(true);
		/*
		cwoaPie.getBlock().setBackground(
				GradientImpl.create(ColorDefinitionImpl.create(204, 254, 204),
						ColorDefinitionImpl.create(254, 226, 240), -35, false));
		cwoaPie.getPlot().getClientArea().setBackground(
				ColorDefinitionImpl.TRANSPARENT());
		cwoaPie.getLegend().setBackground(ColorDefinitionImpl.TRANSPARENT());
		cwoaPie.getLegend().getClientArea().setBackground(
				ColorDefinitionImpl.TRANSPARENT());
		*/
		
				
		cwoaPie.getBlock().setBackground(ColorDefinitionImpl.WHITE());
		
		// Title
		cwoaPie.getTitle().getLabel().getCaption().setValue(dataGWT.getTitle());
		cwoaPie.getTitle().getLabel().getCaption().getFont().setSize(15);
				
		Series seCategory = SeriesImpl.create();
		Query query = QueryImpl.create("row[\"labelY\"]");
		seCategory.getDataDefinition().add(query);

		SeriesDefinition series = SeriesDefinitionImpl.create();
		series.getSeries().add(seCategory);
		cwoaPie.getSeriesDefinitions().add(series);

		PieSeries ps = (PieSeries) PieSeriesImpl.create();
		ps.getLabel().getCaption().getFont().setSize(10);
		ps.getLabel().setVisible(true);
		
		Query query2 = QueryImpl.create("row[\"value\"]");
		ps.getDataDefinition().add(query2);
		ps.setExplosion(4);
		
		
		/*
		ps.getTriggers( )
		.add( TriggerImpl.create( TriggerCondition.ONMOUSEOVER_LITERAL,
				ActionImpl.create( ActionType.SHOW_TOOLTIP_LITERAL,
						TooltipValueImpl.create( 500, "row[\"value\"]" ) ) ) );
		*/
				
		SeriesDefinition seGroup = SeriesDefinitionImpl.create();
		Query query1 = QueryImpl.create("row[\"label\"]");
		seGroup.setQuery(query1);
		series.getSeriesPalette().update(-2);
		series.getSeriesDefinitions().add(seGroup);
		seGroup.getSeries().add(ps);

		if (dataGWT.getDim2_3D().equals("2D")){
			cwoaPie.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
		} else if (dataGWT.getDim2_3D().equals("2D with depth")){
			cwoaPie.setDimension(ChartDimension.TWO_DIMENSIONAL_WITH_DEPTH_LITERAL);
		}
		
		
		DataPointComponent dpc =
		DataPointComponentImpl.create(DataPointComponentType.ORTHOGONAL_VALUE_LITERAL,
		JavaNumberFormatSpecifierImpl.create("###,###"));
		ps.getDataPoint().getComponents().clear();
		ps.getDataPoint().getComponents().add(dpc);
		 
		BoundColumns.setBoundColumnsForPie(chartHandle, "label", "labelY", "value");		 

		chartHandle.getReportItem().setProperty("chart.instance", cwoaPie);

		return chartHandle;

	}

}
