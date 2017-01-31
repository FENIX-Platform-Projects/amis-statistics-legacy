package org.fao.fenix.web.modules.adam.client.view.projects;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMProjectsController;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMEmptyValuesPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;


public class ADAMShowProjectsPanel {
	
	VerticalPanel panel;

	ADAMShowProjectsFilters adamShowProjectsFilters;
	
	ADAMShowProjectsTable adamShowProjectsTable;
	
	ADAMShowProjectsToolbar toolbar;
	

	public ADAMShowProjectsPanel() {
		//panel = new VerticalPanel();
		//panel.setSpacing(5);

	}

	public VerticalPanel build(ADAMResultVO rvo, Long projectNumber) {

		VerticalPanel p = new VerticalPanel();
		VerticalPanel h1 = buildProjectsBoxMenu(rvo, ADAMConstants.BIGGER_MENU_GAP, null, true);
		if(projectNumber > 0) {
			p.add(h1);
			p.add(buildProjectsTable(rvo));
			getAdamShowProjectsTable().getProjectsHtmlTable().setHtml(rvo.getTableHTML());
			
				//getAdamShowProjectsTable().getSummaryHtml().setHtml("<div class='content'><b>Total Number of Projects: " + projectNumber + "</b><div>");
		}
		else	{
			p.add(ADAMEmptyValuesPanel.build("No projects are available for the current selection"));
		}
		return p;
	}
	 
	/*public VerticalPanel build(ADAMResultVO vo, Boolean buildFilter) {
		
		panel.removeAll();
		panel.add(buildProjectsBoxMenu(vo, ADAMConstants.BIG_MENU_GAP, null, true));
		panel.add(buildProjectsTable(vo));
		
		//if ( buildFilter )
			//panel.add(buildFiltersFieldSet());

		format();
		
		return panel;
	}*/
	
	/*public HorizontalPanel buildTitle() {
		HorizontalPanel panel = new HorizontalPanel();
		
		Html title = new Html("Projects's List");
		
		panel.add(title);
		return panel;
	}*/
	
/*	private HorizontalPanel buildToolbar(ADAMResultVO vo, String title) {
		toolbar = new ADAMShowProjectsToolbar();
		return toolbar.build(vo, title);
	}
	
	public VerticalPanel buildFilters() {
		adamShowProjectsFilters = new ADAMShowProjectsFilters();
		return adamShowProjectsFilters.build();
	}
	
	public FieldSet buildFiltersFieldSet() {
		adamShowProjectsFilters = new ADAMShowProjectsFilters();
		return adamShowProjectsFilters.buildFilterFieldSet(this);
	}*/
	
	public VerticalPanel buildProjectsTable(ADAMResultVO vo) {
		adamShowProjectsTable = new ADAMShowProjectsTable();
		return adamShowProjectsTable.build(vo);
	}
	
	/*private void format() {
		panel.setWidth(ADAMConstants.BIG_BOX_WIDTH);
		panel.setHeight(ADAMConstants.PROJECTS_PANEL_HEIGHT);
		panel.setScrollMode(Scroll.AUTO);
	}

	public VerticalPanel getPanel() {
		return panel;
	}

	public ADAMShowProjectsFilters getAdamShowProjectsFilters() {
		return adamShowProjectsFilters;
	}*/

	public ADAMShowProjectsTable getAdamShowProjectsTable() {
		return adamShowProjectsTable;
	}

	/*public ADAMShowProjectsToolbar getToolbar() {
		return toolbar;
	}*/
	

		private static VerticalPanel buildProjectsBoxMenu(ADAMResultVO vo, String labelWidth, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) { 
			VerticalPanel vPanel = new VerticalPanel();	
			vPanel.add(ADAMBoxMaker.buildBoxHeader(vo, labelWidth, objectSizeListener, isSmall));
			vPanel.add(buildProjectsIconsToolbar(vo));
			return vPanel;
		}
		
		private static HorizontalPanel buildProjectsIconsToolbar(final ADAMResultVO vo) {
			
			HorizontalPanel panel = new HorizontalPanel();
			panel.setStyleAttribute("padding-top", "3px");
			panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
			panel.setVerticalAlign(VerticalAlignment.MIDDLE);

			ToolBar toolBar = new ToolBar();  
			toolBar.setStyleAttribute("background", "#F3F5F7"); //very light grey
			toolBar.setBorders(false);
			panel.add(toolBar);
	
			Button exportExcel = ADAMBoxMaker.createToolbarButton("Download Data");
			exportExcel.setIconStyle("exportDataIcon");
			exportExcel.addSelectionListener(ADAMController.exportExcelTableButton(vo, false));
			
			if(vo.getTableContents().size() > 0)
				toolBar.add(exportExcel);  
			

			return panel;
		}
		
	
}
