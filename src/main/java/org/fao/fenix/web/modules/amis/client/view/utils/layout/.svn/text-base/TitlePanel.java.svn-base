package org.fao.fenix.web.modules.amis.client.view.utils.layout;

import java.util.List;

import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISFilter;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class TitlePanel {

	ContentPanel panel;

	public TitlePanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

	}

	public ContentPanel build() {
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
		
		return panel;
	}

	public void build(List<AMISFilter> filters, AMISCodesModelData selectedArea) {
		 System.out.println("START @@@@@@@@@@@@@@@@@ build(List<AMISFilter> filters, AMISCodesModelData selectedArea)");	
		 
		panel.removeAll();
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
		//panel.add(FormattingUtils.addVSpace(5));

		String title = "";
		
		if(selectedArea==null)
			title = "WORLD ";
		else
			title = selectedArea.getLabel() + " ";
	
		for(AMISFilter filter : filters ) {
			if(filter.getCombo()!=null && filter.getCombo().getValue()!=null){
				System.out.println("Filters getCombo NOT NULL");	
				
				title += " - "+ filter.getCombo().getValue().getLabel();
			} else {
				title += " - TOTAL CEREALS";
			    System.out.println("Filters getCombo NULL");	
			}
		}

		title += " at a glance ";
		
		 System.out.println("END @@@@@@@@@@@@@@@@@ title = "+title);	
		 
		panel.add(buildTitle(title));

	}
	
	public void build(String title) {

		panel.removeAll();
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
		//panel.add(FormattingUtils.addVSpace(5));

		panel.add(buildTitle(title));
	}
	
	public void build(String selectedItem, String selectedArea) {

		panel.removeAll();
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
		panel.setStyleName("view_title");
		//panel.add(FormattingUtils.addVSpace(5));

		String title = "";
		
		if(selectedArea==null)
			title = "WORLD ";
		else
			title = selectedArea + " - " + selectedItem;
	

		title += " at a glance ";
		
		panel.add(buildTitle(title));
	}
	
	public ContentPanel build(Boolean buildWaitingPanel) {
		panel.removeAll();
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
    	return panel;
	}
	
	private HorizontalPanel buildTitle(String title) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.CENTER);
		panel.setStyleAttribute("backgroundColor", "#FFFFFF");
		

		panel.addStyleName("general_title");

		Html html = new Html("<div class='general_title'>" + title + "</div>");
		panel.add(html);
		return panel;
	}

	public ContentPanel getPanel() {
		return panel;
	}



}
