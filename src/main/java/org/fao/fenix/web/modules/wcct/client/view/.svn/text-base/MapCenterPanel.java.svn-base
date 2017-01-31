package org.fao.fenix.web.modules.wcct.client.view;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;



public class MapCenterPanel {
	
	private VerticalPanel panel;
	
	private VerticalPanel i;
	
	private Button report;
	
	private HTML image;
	
	private HTML header;
	
	private HTML legend; 
	
	
	public MapCenterPanel(){
		panel = new VerticalPanel();
		report = new Button();
		header = new HTML();
		image = new HTML();
		legend = new HTML("<-- Legend -->");
		
	}
	
	public VerticalPanel build(WCCTTool wcctTool){
//		panel.add(header);
		panel.add(image);
		panel.add(buildLegendPanel());
		enhance();
		return panel;
	}
	
	private HorizontalPanel buildLegendPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHeight(40);
		panel.add(legend);
		return panel;
	}
	
	private void enhance() {		
	}

	public HTML getImage() {
		return image;
	}

	public void setImage(HTML image) {
		this.image = image;
	}

	public VerticalPanel getI() {
		return i;
	}

	public void setI(VerticalPanel i) {
		this.i = i;
	}

}
