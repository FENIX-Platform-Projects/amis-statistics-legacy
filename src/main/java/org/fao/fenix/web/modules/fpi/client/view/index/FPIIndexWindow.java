package org.fao.fenix.web.modules.fpi.client.view.index;

import org.fao.fenix.web.modules.fpi.client.view.toolbar.FPIIndexToolbar;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.table.Table;

public class FPIIndexWindow extends FenixWindow {

	protected FPIIndexParametersPanel fpiIndexParametersPanel;
	
	protected VerticalPanel parametersPanel;
	
	protected Table table;
	
	public void build() {
		buildCenterPanel();
		buildWestPanel();
		addToolbar(new FPIIndexToolbar(parametersPanel, table).getToolbar());
		format();
		show();
	}
	
	protected void buildCenterPanel() {
		FPIIndexResultPanel resultPanel = new FPIIndexResultPanel();
		table = resultPanel.build();
		fillCenterPart(table);
		getCenter().setHeading(BabelFish.print().result());
	}
	
	protected void buildWestPanel() {
		fpiIndexParametersPanel = new FPIIndexParametersPanel();
		parametersPanel = fpiIndexParametersPanel.build(table);
		fillWestPart(parametersPanel);
		getWestData().setSize(350);
		getWest().setHeading(BabelFish.print().parameters());
	}
	
	protected void format() {
		setSize(1000, 450);
		setTitle(BabelFish.print().dataEntry());
		setCollapsible(true);
		getWindow().setHeading(BabelFish.print().fpiTool());
		getWindow().setResizable(false);
	}
	
}