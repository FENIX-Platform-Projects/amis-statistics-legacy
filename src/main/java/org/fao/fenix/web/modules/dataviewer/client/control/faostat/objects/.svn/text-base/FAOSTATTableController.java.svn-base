package org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects;


import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATEmptyValuesPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmaker.FAOSTATBoxMakerChart;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmaker.FAOSTATBoxMakerFlatTable;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmaker.FAOSTATBoxMakerPivotTable;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuChart;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuMap;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.boxmenu.FAOSTATBoxMenuTable;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class FAOSTATTableController {
	
	public static void addTable(final ContentPanel panel, final DWFAOSTATQueryVO qvo, final String width, final String height) {
		//System.out.println(" ADD TABLE .... ");
		
		panel.add(new FAOSTATLoadingPanel().buildWaitingPanel(FAOSTATLanguage.print().loading(), width, height, true));

		try {
			DataViewerServiceEntry.getInstance().askDataViewerFAOSTAT(qvo, new AsyncCallback<DWFAOSTATResultVO>() {
				
		
				public void onSuccess(DWFAOSTATResultVO rvo) {
					panel.removeAll();	

					panel.setWidth(width);
					
					panel.add(FAOSTATBoxMakerFlatTable.build(qvo, rvo, width, height));
	
					panel.layout();
	
//
//					
//					//START
//                    panel.removeAll();	
//					
//					//panel.add(FAOSTATBoxMakerChart.buildChart(qvo, rvo, width, height));
//	
//					//END
//					ContentPanel holder = new ContentPanel();
//					holder.setBodyBorder(false);
//					holder.setHeaderVisible(false);
//					holder.addStyleName("content_box");
//					
//					holder.add(FAOSTATBoxMenuTable.buildMenu(qvo, rvo, width, width));
//
//					System.out.println("rvo.getText(): " + rvo.getTableHTML());
//				
//    				if ( rvo.getTableHTML() == null ) {
//    					holder.add(FAOSTATEmptyValuesPanel.build("No data to display", width, height));
//					}
//					else {
//						StringBuffer chartString = new StringBuffer();
//						chartString.append("<div>");				
//						chartString.append(rvo.getTableHTML());
//				
//						chartString.append("</div>");
//						HTML chart = new HTML(chartString.toString());
//						HorizontalPanel h = new HorizontalPanel();
//						h.addStyleName("chart_box");
//						h.add(chart);
//						holder.add(h);
//					}
//						
//					//
//    				panel.add(holder);
//    				
//    				panel.layout();
//					
//					/***FAOSTATBoxMenuChart.buildMenu(qvo, rvo, width, width)
//					//panel.add(FAOSTATBoxMenuChart.buildMenu(vo, "425px", null, true));
//					
//					StringBuffer chartString = new StringBuffer();
//					chartString.append("<div>");
//					chartString.append(vo.getText());
//	
//					chartString.append("</div>");
//					HTML chart = new HTML(chartString.toString());
////					System.out.println("chart.gethtml: " + chartString.toString());
//					HorizontalPanel h = new HorizontalPanel();
//					h.addStyleName("chart_box");
//					h.add(chart);
//					panel.add(h);
//
//					
//					panel.layout();***/
				}
				
				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static SelectionListener<MenuEvent> exportChartImage(final DWFAOSTATResultVO vo) {
//		return new SelectionListener<MenuEvent>() {
//			public void componentSelected(MenuEvent ce) {
//
//				exportChartImageAgent(vo);
//
//			}
//		};
//	}
	
//	private static void exportChartImageAgent(final DWFAOSTATResultVO vo) {
//		com.google.gwt.user.client.Window.open("../dataviewerObjects/" + vo.getBigImagePath(), "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
//	}

}
