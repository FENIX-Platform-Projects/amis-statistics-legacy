package org.fao.fenix.web.modules.edi.client.view;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class EDIFENIXPanel extends EDIPanel {

	private VerticalPanel wrapper;
	
	private PushButton openTableButton = new PushButton(new Image("toolBox-images/tableToolbox.gif"));
	
	private PushButton openChartButton = new PushButton(new Image("toolBox-images/chartToolbox.gif"));
	
	private PushButton openOLAPButton = new PushButton(new Image("toolBox-images/olap.png"));
	
	private PushButton openMapButton = new PushButton(new Image("toolBox-images/mapToolbox.gif"));
	
	public EDIFENIXPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		wrapper = new VerticalPanel();
		wrapper.setSpacing(getSPACING() + getSPACING());
		openTableButton.setSize("35px", "35px");
		openChartButton.setSize("35px", "35px");
		openOLAPButton.setSize("35px", "35px");
		openMapButton.setSize("35px", "35px");
	}
	
	@Override
	public TabItem build() {
		wrapper.add(buildOpenAsTablePanel());
		wrapper.add(buildOpenAsChartPanel());
		wrapper.add(buildOpenAsOLAPPanel());
		wrapper.add(buildOpenAsMapPanel());
		this.getPanel().add(wrapper);
		return this.getTabItem();
	}
	
	private HorizontalPanel buildOpenAsTablePanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(getSPACING());
		p.add(openTableButton);
		p.add(new Html("<b>Open as Table</b><br>Opening the FAOStat's dataset as table allow you to filter columns and data."));
		return p;
	}
	
	private HorizontalPanel buildOpenAsChartPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(getSPACING());
		p.add(openChartButton);
		p.add(new Html("<b>Open as Chart</b><br>Chart FAOStat's data and publish the result on any website.."));
		return p;
	}
	
	private HorizontalPanel buildOpenAsOLAPPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(getSPACING());
		p.add(openOLAPButton);
		p.add(new Html("<b>Open as Multidimensional Table</b><br>Create pivot tables out of FAOStat's data."));
		return p;
	}
	
	private HorizontalPanel buildOpenAsMapPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(getSPACING());
		p.add(openMapButton);
		p.add(new Html("<b>Open as Map</b><br>Project FAOStat's data on a map and add more layers, if you need."));
		return p;
	}

	public PushButton getOpenTableButton() {
		return openTableButton;
	}

	public PushButton getOpenChartButton() {
		return openChartButton;
	}

	public PushButton getOpenOLAPButton() {
		return openOLAPButton;
	}

	public PushButton getOpenMapButton() {
		return openMapButton;
	}
	
}
