package org.fao.fenix.web.modules.olap.client.view.chart;

import org.fao.fenix.web.modules.olap.common.vo.OLAPChartResultVo;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class OlapChartView {
	
	Window window;
	ContentPanel cont;
	HTML content;
	OlapChartToolbar olapChartToolbar;
	
	public Window getWindow() {
		return window;
	}

	public OlapChartView(OLAPChartResultVo element){
		
		window = new Window();
		window.setHeading("OLAP Chart");
		window.setLayout(new FitLayout());
		window.setSize(600, 400);
		
		cont = new ContentPanel();
		cont.setHeaderVisible(false);
		cont.setLayout(new FitLayout());
		
		content = new HTML(element.getIFrame());
		cont.add(content);
		
		olapChartToolbar = new OlapChartToolbar(element.getReportName(), element.getChartType(), content);
		cont.setTopComponent(olapChartToolbar.getToolBar());
		
		window.add(cont);
		window.show();
		
	}

}
