package org.fao.fenix.web.modules.fpi.client.view.dataentry;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.ListBox;

public class FPIDataEntryWindow extends FenixWindow {

	protected FPIDataEntryParametersPanel fpiDataEntryParametersPanel;

	protected VerticalPanel parametersPanel;
	
	public static LoadingWindow loadingWindow = new LoadingWindow();
	
	public static VerticalPanel dataEntryPanel;
	
	public static ListBox dayList;
	
	public static ListBox monthList;
	
	public static ListBox yearList;
	
	public static FPIDataEntryPanel fpiDataEntryPanel;

	static {
		loadingWindow.create();
	}
	
	public void build() {
		buildToolbar();
		buildCenterPanel();
		buildWestPanel();
		format();
		loadingWindow.destroy();
	}

	protected void format() {
		setSize(1000, 400);
		setTitle(BabelFish.print().dataEntry());
		setCollapsible(true);
		getWindow().setHeading(BabelFish.print().fpiTool());
	}

	protected void buildToolbar() {
		//TODO FPIToolbar extending FenixToolbar 
	}

	protected void buildCenterPanel() {
		fpiDataEntryPanel = new FPIDataEntryPanel();
		dayList = fpiDataEntryPanel.weekList;
		monthList = fpiDataEntryPanel.monthList;
		yearList = fpiDataEntryPanel.yearList;
		dataEntryPanel = new FPIDataEntryPanel().build();
		fillCenterPart(dataEntryPanel);
		getCenter().setHeading(BabelFish.print().dataEntry());
	}

	protected void buildWestPanel() {
		fpiDataEntryParametersPanel = new FPIDataEntryParametersPanel();
		parametersPanel = fpiDataEntryParametersPanel.build(dataEntryPanel, fpiDataEntryPanel);
		fillWestPart(parametersPanel);
		getWestData().setSize(350);
		getWest().setHeading(BabelFish.print().parameters());
	}

	public void setParameterValue(String parameter, Object value) {
		fpiDataEntryParametersPanel.setParameterValue((HorizontalPanel) parametersPanel.getData(parameter), value.toString());
	}

}