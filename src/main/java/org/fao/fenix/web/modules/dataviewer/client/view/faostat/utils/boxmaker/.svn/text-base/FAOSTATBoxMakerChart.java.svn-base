package org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmaker;

import org.fao.fenix.web.modules.core.client.utils.highcharts.HighchartTest;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATEmptyValuesPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxFootnote;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuChart;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class FAOSTATBoxMakerChart extends FAOSTATBoxMaker {

	public static ContentPanel buildChart(final DWFAOSTATQueryVO qvo, final DWFAOSTATResultVO rvo, String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
//		panel.addStyleName("content_box");
		if ( rvo.getChartValues().isEmpty() ) {
			panel.add(FAOSTATBoxMenuChart.buildMenu(qvo, rvo, width, width, false));
			panel.add(FAOSTATEmptyValuesPanel.build(FAOSTATLanguage.print().noDataAvailable(), width, height));
		}
		else {
			panel.add(FAOSTATBoxMenuChart.buildMenu(qvo, rvo, width, width, true));
			panel.add(DataViewerClientUtils.addVSpace(2));
			
			ContentPanel chartWrapper = new ContentPanel();
			chartWrapper.setBodyBorder(false);
			chartWrapper.setHeaderVisible(false);
			chartWrapper.addStyleName("content_box");
			
			ContentPanel chart = new ContentPanel();
			chart.setBodyBorder(false);
			chart.setHeaderVisible(false);
			
			chart.setWidth(width);
			chart.setHeight(height);
//			chart.addStyleName("content_box");

			HighchartTest.createChart(chart, rvo, width, height, FAOSTATConstants.browser, FAOSTATConstants.version);
			
			chartWrapper.add(chart);
			
			if ( !DataViewerBoxContent.valueOf(rvo.getTypeOfOutput()).toString().equalsIgnoreCase(DataViewerBoxContent.PIE.toString())) {
				chartWrapper.add(addDisclaimer());
			}
			
			panel.add(chartWrapper);
			
			if ( qvo.getFootnote() != null ) {
				panel.add(new FAOSTATBoxFootnote().buildFootnote(qvo.getFootnote()));
//				Integer i = panel.getHeight();
//				panel.setHeight(panel.getHeight() + 15);
				panel.layout();
			}
			
			panel.layout();
		}
		
		return panel;
	} 

	private static ContentPanel addDisclaimer() {
		ContentPanel panel = new ContentPanel();

		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		Html html = new Html();
		html.setHtml("<div class='disclaimer chart'>"+ FAOSTATLanguage.print().chartDislaimer()+ "</div>");
		
		panel.add(html);
		
		return panel;
	}	
}
