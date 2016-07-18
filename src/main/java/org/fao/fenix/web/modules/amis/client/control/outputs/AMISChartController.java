package org.fao.fenix.web.modules.amis.client.control.outputs;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.fao.fenix.web.modules.amis.client.control.compare.AMISCompareController;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.EmptyValuesPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.LoadingPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmaker.AMISBoxMakerChart;
import org.fao.fenix.web.modules.amis.common.constants.AMISInterfaceConstants;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AMISChartController {//setChartValues

public static void addChart(final ContentPanel panel, final AMISQueryVO qvo, final String width, final String height) {

		panel.add(new LoadingPanel().buildPanel("Loading", width, height, true));

		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

                public void onSuccess(AMISResultVO rvo) {
					panel.removeAll();
					panel.setWidth(width);
					
					if (!rvo.getChartValues().isEmpty() ) {
						System.out.println("Class: AMISChartController Function: addChart Text: Start linked hashmap");
						Set<String> keySet = rvo.getChartValues().keySet();
						Iterator<String> it = keySet.iterator();
						
						while(it.hasNext())
						{
							String key = it.next();
							Map<String, Double> map = rvo.getChartValues().get(key);
							System.out.println("Class: AMISChartController Function: addChart Text: key: "+key);
							Set<String> mapKeySet = map.keySet();
							Iterator<String> mapit = mapKeySet.iterator();
							while(mapit.hasNext())
							{
								String key2 = mapit.next();
								Double value = map.get(key2);
								System.out.println("Class: AMISChartController Function: addChart Text: small key: "+key2 +" small value "+value);
							}
						}
						System.out.println("Class: AMISChartController Function: addChart Text: End linked hashmap\n\n");
						if(qvo.getAreas()!=null && !qvo.getAreas().containsKey("999000")) { // NOT GLOBAL
							LinkedHashMap<String, Map<String, Double>> lhm = changeLabel(rvo.getChartValues());
						//	LinkedHashMap<String, Map<String, Double>> lhm = rvo.getChartValues();
							rvo.setChartValues(lhm);
							System.out.println("Class: AMISChartController Function: addChart Text: AFTER CHANGE Start linked hashmap");
							keySet = rvo.getChartValues().keySet();
							it = keySet.iterator();
							
							while(it.hasNext())
							{
								String key = it.next();
								Map<String, Double> map = rvo.getChartValues().get(key);
								System.out.println("Class: AMISChartController Function: addChart Text: AFTER CHANGE key: "+key);
								Set<String> mapKeySet = map.keySet();
								Iterator<String> mapit = mapKeySet.iterator();
								while(mapit.hasNext())
								{
									String key2 = mapit.next();
									Double value = map.get(key2);
									System.out.println("Class: AMISChartController Function: addChart Text: AFTER CHANGE small key: "+key2 +" small value "+value);
								}
							}
						}
						panel.setHeight(AMISInterfaceConstants.calculateHeightWithBoxMenu(height));	
						panel.add(AMISBoxMakerChart.buildChart(qvo, rvo, width, height));
					}
					else {
						panel.add(EmptyValuesPanel.build(width, height, qvo)); // no data available message
					}
					
					panel.layout();
				}

				private LinkedHashMap<String, Map<String, Double>> changeLabel(LinkedHashMap<String, Map<String, Double>> chartValues) {
					LinkedHashMap<String, Map<String, Double>> newLhm = new LinkedHashMap<String, Map<String, Double>>();
					Set<String> keySet = chartValues.keySet();
					Iterator<String> it = keySet.iterator();
					
					while(it.hasNext())
					{
						String key = it.next();
						Map<String, Double> map = chartValues.get(key);
//						if((key!=null)&&(key.equals("Utilization")))
//						{
//							key = "Domestic Utilization";
//						}
//						else if((key!=null)&&(key.equals("Supply")))
//						{
//							key = "Domestic Availability";
//						}
						newLhm.put(key, map);
					}
					return newLhm;
				}

				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//public static void addChart(final ContentPanel panel, final AMISQueryVO qvo, final String width, final String height) {
//
//	panel.add(new LoadingPanel().buildPanel("Loading", width, height, true));
//
//	try {
//		AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
//
//            public void onSuccess(AMISResultVO rvo) {
//				panel.removeAll();
//				panel.setWidth(width);
//				
//				if (!rvo.getChartValues().isEmpty() ) {
//					panel.setHeight(AMISInterfaceConstants.calculateHeightWithBoxMenu(height));	
//					panel.add(AMISBoxMakerChart.buildChart(qvo, rvo, width, height));
//				}
//				else {
//					panel.add(EmptyValuesPanel.build(width, height, qvo)); // no data available message
//				}
//				
//				panel.layout();
//			}
//
//			public void onFailure(Throwable arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//}

/**public static void addChart(final ContentPanel panel, final AMISQueryVO qvo, final String width, final String height) {

		panel.add(new LoadingPanel().buildPanel("Loading", width, height, true));

		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {


				public void onSuccess(AMISResultVO rvo) {
					panel.removeAll();

					panel.setWidth(width);
					panel.setHeight(AMISInterfaceConstants.calculateHeightWithBoxMenu(height));
					panel.add(AMISBoxMakerChart.buildChart(qvo, rvo, width, height));

					panel.layout();
				}

				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}**/
	
public static void addChart(final VerticalPanel vp, final ContentPanel emptyPanelHolder, final ContentPanel panel, final AMISQueryVO qvo, final String width, final String height, final LoadingWindow loadingWindow) {
//public static void addChart(final VerticalPanel vp, final ContentPanel emptyPanelHolder, final ContentPanel panel, final AMISQueryVO qvo, final String width, final String height) {
	panel.add(new LoadingPanel().buildPanel("Loading", width, height, true));
	try {
		AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {

            public void onSuccess(AMISResultVO rvo) {
				panel.removeAll();
				panel.setWidth(width);
				//HorizontalPanel mainHolder = new HorizontalPanel();
				//mainHolder.setBorders(true);
				
				if (!rvo.getChartValues().isEmpty() ) {
					rvo.setTableHeaders(qvo.getTableHeaders());
					
					System.out.println("qvo.getTableHeaders() " + qvo.getTableHeaders()); 
					
					panel.setHeight(AMISInterfaceConstants.calculateHeightWithBoxMenu(height));
					
					panel.add(AMISBoxMakerChart.buildChart(qvo, rvo, width, height));
					
					System.out.println("$$$$$  PANEL ADDED  "); 
					vp.add(panel);
					vp.add(FormattingUtils.addVSpace(AMISInterfaceConstants.CENTER_PANEL_VERTICAL_SPACING));
					
					//AMISCompareController.getChartPanels().add(AMISBoxMakerChart.buildChart(qvo, rvo, width, height));
					
					//compare.getOutPutPanel().addChart(AMISCompareController.getChartPanels());
					
					//vp.add(mainHolder);
					
				}
				else {
					//panel.add(EmptyValuesPanel.build(width, height, qvo)); // no data available message
				AMISCompareController.getEmptyPanels().add(EmptyValuesPanel.build(width, height, qvo));
				
				
					emptyPanelHolder.setBorders(true);
					emptyPanelHolder.add(EmptyValuesPanel.build(width, height, qvo));
					emptyPanelHolder.setWidth("1030px");
					System.out.println("$$$$$ ADD TO EMPTY PANEL HASH MAP "+qvo.getTitle()+" size = " + AMISCompareController.getEmptyPanels().size()); 
					
				}
				if(loadingWindow!=null)
				{
					loadingWindow.destroyLoadingBox();
				}
				
				vp.layout();
				//mainHolder.layout();
				emptyPanelHolder.layout();
				panel.layout();
			}

			public void onFailure(Throwable arg0) {
				if(loadingWindow!=null)
				{
					loadingWindow.destroyLoadingBox();
				}
				arg0.printStackTrace();
			}
		});

	} catch (Exception e) {
		if(loadingWindow!=null)
		{
			loadingWindow.destroyLoadingBox();
		}
		e.printStackTrace();
	}
}

}
