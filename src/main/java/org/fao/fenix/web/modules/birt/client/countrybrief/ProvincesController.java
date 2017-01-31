package org.fao.fenix.web.modules.birt.client.countrybrief;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.DataListSelectionModel;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ProvincesController {
	
	ProvincesView provincesView;
		
	public ProvincesController(ReportView reportView){
		provincesView = new ProvincesView(reportView);
		setEvents();
	}
	
	public static SelectionListener<ButtonEvent> getAllProvinces(final ReportView reportView) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				new ProvincesController(reportView);
			}
		};
	}
	
	private void setEvents(){
		
		provincesView.ok.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				List<String> provinceList = new ArrayList<String>();
				
				DataListSelectionModel sm = provincesView.getList().getSelectionModel();
				
				List<DataListItem> listItems = sm.getSelectedItems(); 
				
				for (DataListItem item : listItems){
					provinceList.add(item.getId());
				}
				
				if (provinceList.size() == 0){
					FenixAlert.info("Country Brief", "You have to select al least one province", "");
					return;
				}
				
				final LoadingWindow loading = new LoadingWindow();
				loading.create();
				
				BirtServiceEntry.getInstance().updateCountryBrief(provinceList, provincesView.reportView.getCountryCode(), provincesView.reportView.getRptDesign(), new AsyncCallback<String>(){
					
					public void onSuccess(String report){
						
						provincesView.reportView.content.setHTML(report);			
						provincesView.window.close();
						loading.destroy();
					}
					
					public void onFailure(Throwable caught){
						Info.display("updateCountryBrief", caught.getLocalizedMessage());
						loading.destroy();
					}
					
				});
				
			}
			
		});
		
	}
	
}
