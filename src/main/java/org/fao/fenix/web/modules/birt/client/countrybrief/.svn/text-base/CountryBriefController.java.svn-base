package org.fao.fenix.web.modules.birt.client.countrybrief;

import java.util.List;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class CountryBriefController {

	CountryBriefView countryBriefView;
	
	public CountryBriefController(){
		countryBriefView = new CountryBriefView();
		setEvents();
	}
	
	private void setEvents(){
		
		countryBriefView.ok.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				final String countryCode = countryBriefView.countryList.getValue(countryBriefView.countryList.getSelectedIndex());
				final String countryLabel = countryBriefView.countryList.getItemText(countryBriefView.countryList.getSelectedIndex());
				final LoadingWindow loading = new LoadingWindow();
				loading.create();
				BirtServiceEntry.getInstance().countryBriefTemplate(countryCode, countryLabel, new AsyncCallback<List<String>>(){
					
					public void onSuccess(List<String> report){
						
						new ReportView(countryCode, countryLabel, report);
						
						loading.destroy();
					}
					
					public void onFailure(Throwable caught){
						Info.display("countryBriefTemplate", caught.getLocalizedMessage());
						loading.destroy();
					}
				});
				
				//countryBriefView.window.close();
		
			}
			
		});
		
	}
	
	public static void countryBriefList(final ListBox countries){
		BirtServiceEntry.getInstance().getCountryBriefList(new AsyncCallback<List<CodeVo>>(){
			public void onSuccess(List<CodeVo> codes){
				for(CodeVo c : codes)
					countries.addItem(c.getLabel(), c.getCode());
			}
			
			public void onFailure(Throwable caught){
				Info.display("countryBriefTemplate", caught.getLocalizedMessage());
		
			}
		});
		
		
	}
	
}
