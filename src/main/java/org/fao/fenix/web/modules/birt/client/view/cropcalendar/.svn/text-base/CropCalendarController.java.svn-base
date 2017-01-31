package org.fao.fenix.web.modules.birt.client.view.cropcalendar;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class CropCalendarController {

	CropCalendarView cropCalendarView;
	
	public CropCalendarController(){
		cropCalendarView = new CropCalendarView();
		setEvents();
	}
	
	private void setEvents(){
		
		cropCalendarView.ok.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				String selectedCountryCode = cropCalendarView.countryList.getValue(cropCalendarView.countryList.getSelectedIndex());
				String selectedCountryLabel = cropCalendarView.countryList.getItemText(cropCalendarView.countryList.getSelectedIndex());
				
				String url = "../cropCalendars/"+selectedCountryCode+"_CAL1E.GIF";
				
				String iFrame = "<iframe src='" +url + "' width='100%' height='100%'/>";
				
				Window window = new Window();
				window.setHeading("GIEWS Crop Calendar: "+selectedCountryLabel);
				ContentPanel cont = new ContentPanel();
				cont.setHeaderVisible(false);
				cont.setLayout(new FitLayout());
				
				window.setLayout(new FitLayout());
				window.setSize(600, 400);
				
				HTML content = new HTML(iFrame);
				cont.add(content);
				
				window.add(cont);
				window.show();
				
				cropCalendarView.window.close();
		
			}
			
		});
		
	}
	
}
