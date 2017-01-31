package org.fao.fenix.web.modules.birt.client.view.report.template;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ChooseResourceType;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Blank {

private VerticalPanel mainCont;
	
	public VerticalPanel getMainCont() {
		return mainCont;
	}
	
	public Blank(final ReportViewer reportViewer){
		mainCont = new VerticalPanel();
		mainCont.setSpacing(5);
		mainCont.setSize("120px", "150px");
		mainCont.setBorderWidth(1);
		DOM.setStyleAttribute(mainCont.getElement(), "backgroundColor", "#FFFFFF");
		
		HorizontalPanel firstRow = new HorizontalPanel();
		firstRow.setSpacing(5);
		
		IconButton map=new IconButton("templateBlank");
		map.setSize(95, 50);
		map.addSelectionListener(new SelectionListener<IconButtonEvent>(){
			public void componentSelected(IconButtonEvent componentEvent){
				new ChooseResourceType(reportViewer);
			}
		}); 
		firstRow.add(map);
		
		mainCont.add(firstRow);
		
		mainCont.setCellHorizontalAlignment(firstRow, HorizontalPanel.ALIGN_CENTER);
		mainCont.setCellWidth(firstRow, "100%");
		
		
	}
	
}
