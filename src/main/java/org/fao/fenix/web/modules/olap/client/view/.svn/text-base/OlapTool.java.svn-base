package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTabPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.OlapToolController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class OlapTool extends FenixToolBase {

	private OlapTabPanel tabPanel;
	
	private OlapFilterPanel filterPanel;
	
	private HTML html;
	
	private VerticalPanel wrapper;
	
	private OlapToolbar toolbar;

	public OlapTool() {
		tabPanel = new OlapTabPanel();
		filterPanel = new OlapFilterPanel();
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		wrapper.setBorders(true);
		wrapper.setScrollMode(Scroll.AUTO);
		html = new HTML(BabelFish.print().fsatmisHints());
		html.setWidth("450px");
		wrapper.add(html);
		wrapper.setSize("400px", "490px");
//		wrapper.setAutoHeight(true);
//		wrapper.setAutoWidth(true);
	}
	
	public void build(FSATMISTabPanel fsatmisTabPanel) {
		buildEastPanel();
		buildWestPanel();
		buildCenterPanel(fsatmisTabPanel);
		format();
		show();
		filterPanel.getAddFilterButton().addSelectionListener(OlapToolController.addFilter(this, filterPanel, tabPanel.getFsatmisDatasetPanel().getDataSource()));
		filterPanel.getRemoveFilterButton().addSelectionListener(OlapToolController.removeFilter(filterPanel));
	}
	
	private void buildCenterPanel(FSATMISTabPanel fsatmisTabPanel) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeading(BabelFish.print().multidimensionalTableTool());
		toolbar = new OlapToolbar();
		getCenter().setTopComponent(toolbar.build(this, fsatmisTabPanel));
		getCenter().add(wrapper);
		getCenterData().setSize(400);
		addCenterPartToWindow();
	}
	
	private void buildWestPanel() {
		setWestProperties();
		fillWestPart(tabPanel.buildForFSATMIS());
		getWestData().setSize(250);
		getWest().setHeading(BabelFish.print().controller());
	}
	
	private void buildEastPanel() {
		setEastProperties();
		fillEastPart(filterPanel.build());
		getEastData().setSize(250);
		getEast().setHeading(BabelFish.print().filters());
		
	}
	
	private void format() {
		setSize("800px", "700px");
		getToolBase().setHeaderVisible(false);
	}
	
	public OlapTabPanel getTabPanel() {
		return tabPanel;
	}

	public HTML getHtml() {
		return html;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public OlapFilterPanel getFilterPanel() {
		return filterPanel;
	}

	public OlapToolbar getOlapToolbar() {
		return toolbar;
	}
	
}