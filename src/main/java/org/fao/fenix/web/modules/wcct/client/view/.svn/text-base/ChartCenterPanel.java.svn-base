package org.fao.fenix.web.modules.wcct.client.view;

import org.fao.fenix.web.modules.wcct.client.control.WCCTController;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;



public class ChartCenterPanel {
	
	private VerticalPanel panel;

	private Button export;
	
	private HTML image;
	
	private HTML header;
	
	private String selectedCountry;
	
	
	public ChartCenterPanel(){
		panel = new VerticalPanel();
		export = new Button("Export");
		header = new HTML();
		image = new HTML();	
		selectedCountry = new String();
	}
	
	public VerticalPanel build(WCCTTool wcctTool){
		panel.add(buildHeader());
		panel.add(buildImage());
		panel.add(buildExportPanel());
		enhance(wcctTool);
		return panel;
	}
	
	private void enhance(WCCTTool wcctTool) {
		export.addSelectionListener(WCCTController.exportCalendar(this));	
	}
	
	private VerticalPanel buildHeader(){
		VerticalPanel panel = new VerticalPanel();;
		panel.setHeight(30);
		panel.add(header);
		return panel;
	}
	
	private ContentPanel buildImage(){
		ContentPanel panel = new ContentPanel();;
		panel.setHeaderVisible(false);
		panel.setScrollMode(Scroll.AUTO);
		panel.setBodyBorder(false);
		panel.setBorders(false);
		panel.setSize(745, 378);
		panel.add(image);
		return panel;
	}
	
	private ContentPanel buildExportPanel() {
		ContentPanel panel = new ContentPanel();
		VerticalPanel vPanel = new VerticalPanel();
		panel.setHeaderVisible(false);
		vPanel.setSpacing(10);
		vPanel.add(export);
		panel.add(vPanel);
		panel.setHeight(45);
		panel.setWidth(1200);
		return panel;
	}
	

	public HTML getImage() {
		return image;
	}

	public void setImage(HTML image) {
		this.image = image;
	}

	public HTML getHeader() {
		return header;
	}

	public String getSelectedCountry() {
		return selectedCountry;
	}

	public void setSelectedCountry(String selectedCountry) {
		this.selectedCountry = selectedCountry;
	}

	public VerticalPanel getPanel() {
		return panel;
	}
	
	
}
