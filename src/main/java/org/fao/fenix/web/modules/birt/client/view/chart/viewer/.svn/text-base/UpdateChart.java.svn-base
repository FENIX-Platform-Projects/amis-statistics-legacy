package org.fao.fenix.web.modules.birt.client.view.chart.viewer;

import java.util.List;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;

import com.google.gwt.user.client.rpc.AsyncCallback;


public class UpdateChart  {

	public UpdateChart(String datasetID) {		
		System.out.println("Checking if datasetID: " + datasetID + " has charts");
//		new ChartViewer("355349140", true);
		BirtServiceEntry.getInstance().hasChart(Long.valueOf(datasetID), new AsyncCallback<List<Long>>() {		
			public void onSuccess(List<Long> result) {
				if ( result != null ) {
					for (Long chartViewID : result) {
						System.out.println("UPDATING: " + chartViewID);
						new ChartViewer(String.valueOf(chartViewID), true);
					}
				}
			}
			
			public void onFailure(Throwable caught) {
			}
		});
	}
}
