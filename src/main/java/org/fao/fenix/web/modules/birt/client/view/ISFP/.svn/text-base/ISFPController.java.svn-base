package org.fao.fenix.web.modules.birt.client.view.ISFP;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;


public class ISFPController {

	ISFPView isfpView;
	
	public ISFPController(){
		isfpView = new ISFPView();
		setEvents();
	}
	
	private void setEvents(){
		
		isfpView.ok.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				List<String> country = new ArrayList<String>();
				country.add(isfpView.countryList.getValue(isfpView.countryList.getSelectedIndex()));
				country.add(isfpView.countryList.getItemText(isfpView.countryList.getSelectedIndex()));
				final LoadingWindow loading = new LoadingWindow();
				loading.create();
				BirtServiceEntry.getInstance().ISFPTemplate(country, new AsyncCallback<String>(){
					
					public void onSuccess(String report){
						
						//-------------- temporary behavior
						Window window = new Window();
						window.setHeading("ISFP Report");
						ContentPanel cont = new ContentPanel();
						cont.setHeaderVisible(false);
						cont.setLayout(new FitLayout());
						window.setLayout(new FitLayout());
						window.setSize(800, 600);
						HTML content = new HTML(report);
						cont.add(content);
						window.add(cont);
						window.show();
						//---------------------
						
						loading.destroy();
					}
					
					public void onFailure(Throwable caught){
						Info.display("ISFPTemplate", caught.getLocalizedMessage());
						loading.destroy();
					}
				});
				//isfpView.window.close();
			}
			
		});
		
	}
	
}
