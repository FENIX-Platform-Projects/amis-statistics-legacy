package org.fao.fenix.web.modules.adam.client.view.fpmis;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class ADAMFPMISProjects {
	
	VerticalPanel panel;
	
	public ADAMFPMISProjects() {
		panel = new VerticalPanel();

	}
	
//	public static VerticalPanel buildSmallTable(ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener) {
//	
//		
//		HorizontalPanel h1 = ADAMBoxMaker.buildTableBoxMenu(vo, ADAMConstants.SMALL_MENU_GAP_WIDTH, position, color,  objectSizeListener, true, true);
//
//		return p;
//	}
	
//	public static VerticalPanel buildBigTable(ADAMResultVO vo,  String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean showLinks) {
//		VerticalPanel p = new VerticalPanel();
//		p.setHeight(ADAMConstants.BIG_BOX_HEIGHT);
//		p.setWidth(ADAMConstants.BIG_BOX_WIDTH);
//		
//		p.setSpacing(5);
////		HorizontalPanel h1 = buildChartBoxMenu(vo, ADAMConstants.BIG_MENU_GAP_WIDTH, position, color, objectSizeListener, false);
//
//		HorizontalPanel h1 = buildTableBoxMenu(vo, ADAMConstants.BIG_MENU_GAP_WIDTH, position, color, objectSizeListener, false, true);
//		HorizontalPanel h2 = new HorizontalPanel();
//		ContentPanel cp = buildGrid(vo, ADAMConstants.BIG_TABLE_CHART_WIDTH, ADAMConstants.BIG_TABLE_CHART_HEIGHT);
//		h2.add(cp);
//		p.add(h1);
//		if ( showLinks ) {
//			h2.add(buildLinksPanel(vo.getGaulMap(), vo.getDonorMap(), vo.getChannelMap(), vo.getDacMap(), false));
//		}
//		p.add(h2);
//		return p;
//	}
	
	
	public VerticalPanel buildBigProjectList(ADAMResultVO vo, String url, String width, String height, String position, String color) {
		panel.removeAll();
		
		HorizontalPanel h1 = ADAMBoxMaker.buildFPMISProjects(vo, ADAMConstants.BIG_MENU_GAP_WIDTH, position, color, ADAMController.closeCenter(), false, true);


		
		panel.add(h1);
		panel.add(new Html(buildIframe(url, width, height)));
		
		return panel;
	}
	
	private String buildIframe(String url, String width, String height) {
		
//		String fpmis = "<IFRAME SRC='https://extranet.fao.org/fpmis/FPMISReportServlet.jsp?APD=&countryId=ID&div=&fundG=&type=countryprofileopen&lng=EN&qlfrs=&UF=N&typeUF=&colorder=2345&pwb=&sorttype=1' WIDTH=800 HEIGHT=500> </IFRAME>";

		
		String iframe = "<IFRAME SRC=' " + url + "' WIDTH="+ width + " HEIGHT=" + height + " FRAMEBORDER='0'> </IFRAME>";
		
		System.out.println("iframe: " + iframe);
		
		return iframe;
	}

}
