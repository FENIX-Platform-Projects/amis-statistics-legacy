package org.fao.fenix.web.modules.adam.client.view.projects;

import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;


public class ADAMShowProjectsTable {

	VerticalPanel panel;
	
	VerticalPanel tablePanel;
	
	VerticalPanel summaryPanel;
	
	ADAMShowProjectsToolbar toolbar;
	
	Html projectsHtmlTable;
	
	Html summaryHtml;
	
	
	
	public ADAMShowProjectsTable() {
		panel = new VerticalPanel();
		//panel.setBorders(true);
		panel.setSpacing(5);
		projectsHtmlTable = new Html();
		summaryHtml = new Html();
	}
	
	public VerticalPanel build(ADAMResultVO vo) {
		panel.removeAll();
		
//		panel.add(buildToolbar(vo));
		
		panel.add(buildSummaryPanel());
		
		panel.add(buildTablePanel());

		return panel;
	}
	
	/*private HorizontalPanel buildToolbar(ADAMResultVO vo, String title) {
		toolbar = new ADAMShowProjectsToolbar();
		return toolbar.build(vo, title);
	}
	*/
	private VerticalPanel buildTablePanel() {
		tablePanel = new VerticalPanel();
		Integer panelWidth = Integer.valueOf(ADAMConstants.BIGGER_TABLE_CHART_WIDTH) - 10 ;
		tablePanel.setWidth(panelWidth);
		tablePanel.setHeight(ADAMConstants.PROJECTS_TABLE_HEIGHT);
		//tablePanel.setScrollMode(Scroll.AUTO);	
		//tablePanel.setBorders(true);
		tablePanel.add(projectsHtmlTable);
		return tablePanel;
	}
	
	private VerticalPanel buildSummaryPanel() {
		summaryPanel = new VerticalPanel();
		summaryPanel.setWidth(ADAMConstants.BIGGER_TABLE_CHART_WIDTH);
		//summaryPanel.setScrollMode(Scroll.AUTO);	
		summaryPanel.add(summaryHtml);
		return summaryPanel;
	}

	public Html getProjectsHtmlTable() {
		return projectsHtmlTable;
	}

	public Html getSummaryHtml() {
		return summaryHtml;
	}

	public ADAMShowProjectsToolbar getToolbar() {
		return toolbar;
	}
	
	
	
	
	
	
}
