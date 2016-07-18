package org.fao.fenix.web.modules.birt.client.view.ISFP;

import org.fao.fenix.web.modules.birt.common.ISFPCountries;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ISFPView {
	
	Window window;
	VerticalPanel mainCont;
	ListBox countryList;
	Button ok;
	
	public ISFPView(){
		window = new Window();
		window.setHeading("ISFP");
		window.setSize(350, 130);
		mainCont = new VerticalPanel();
		mainCont.setSpacing(10);
		
		HorizontalPanel hr = new HorizontalPanel();
		hr.setSpacing(5);
		hr.add(new HTML("<div style='font-weight:bold;'>" + BabelFish.print().country() + ": </div>"));
		
		countryList = new ISFPCountries().getCategoriesListBox();
		countryList.setWidth("250px");
		hr.add(countryList);
		
		mainCont.add(hr);
						
		HorizontalPanel hrOK = new HorizontalPanel();
		ok = new Button(BabelFish.print().ok());
		hrOK.add(ok);
		
		mainCont.add(hrOK);
		
		window.add(mainCont);
		
		mainCont.setCellHorizontalAlignment(hr, HorizontalPanel.ALIGN_CENTER);
		mainCont.setCellWidth(hr, "100%");
		
		mainCont.setCellHorizontalAlignment(hrOK, HorizontalPanel.ALIGN_CENTER);
		mainCont.setCellWidth(hrOK, "100%");
		
		window.show();
	}
	

}
