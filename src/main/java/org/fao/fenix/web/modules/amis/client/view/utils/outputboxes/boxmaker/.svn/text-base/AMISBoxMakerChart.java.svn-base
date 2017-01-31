package org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmaker;

import org.fao.fenix.web.modules.amis.client.view.utils.AMISHighchart;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmenu.AMISBoxMenuChart;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;


import com.extjs.gxt.ui.client.widget.ContentPanel;

public class AMISBoxMakerChart extends BoxMaker {

	public static ContentPanel buildChartOriginal(final AMISQueryVO qvo, final AMISResultVO rvo, final String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
//		panel.addStyleName("content_box");
	//	if ( rvo.getChartValues().isEmpty() ) {
	//		panel.add(EmptyValuesPanel.build("No Data available", width, height, qvo));
	//	}
	//	else {
			panel.add(AMISBoxMenuChart.buildMenu(qvo, rvo, width, width));
			panel.add(FormattingUtils.addVSpace(2));

			ContentPanel chart = new ContentPanel();
			chart.setBodyBorder(false);
			chart.setHeaderVisible(false);

			chart.setWidth(width);
			chart.setHeight(height);

			chart.addStyleName("content_box");

			AMISHighchart.createChart(chart, rvo, width, height);

			panel.add(chart);
		//}

		return panel;
	}
	
	public static ContentPanel buildChart(final AMISQueryVO qvo, final AMISResultVO rvo, final String width, String height) {
		ContentPanel panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
//		panel.addStyleName("content_box");
	//	if ( rvo.getChartValues().isEmpty() ) {
	//		panel.add(EmptyValuesPanel.build("No Data available", width, height, qvo));
	//	}
	//	else {
			panel.add(AMISBoxMenuChart.buildMenu(qvo, rvo, width, width));
			panel.add(FormattingUtils.addVSpace(2));

			ContentPanel chart = new ContentPanel();
			chart.setBodyBorder(false);
			chart.setHeaderVisible(false);

			chart.setWidth(width);
			chart.setHeight(height);

			chart.addStyleName("content_box");
			AMISHighchart.createChart(chart, rvo, width, height);

			panel.add(chart);
		//}

		return panel;
	}


}
