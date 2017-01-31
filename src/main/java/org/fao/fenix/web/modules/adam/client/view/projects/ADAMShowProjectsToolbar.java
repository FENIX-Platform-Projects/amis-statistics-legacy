package org.fao.fenix.web.modules.adam.client.view.projects;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomSelectionBox;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomShowBox;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;


public class ADAMShowProjectsToolbar {

	HorizontalPanel panel;
	
	Button close;
	
	MenuItem excel;
	
	public ADAMShowProjectsToolbar() {
		panel = new HorizontalPanel();
	}
	
	public HorizontalPanel build(ADAMResultVO vo, String title) {
		panel.removeAll();
		
//		panel.add(ADAMBoxMaker.addInfo(vo.getDescription()));
		panel.add(buildTableMenu(vo, title));
		
		return panel;
	}
	
	
	public HorizontalPanel buildTableMenu(ADAMResultVO vo, String title) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.TOP);
		panel.setSpacing(5);
		
		Html space = new Html("<div class='content'><b>"+ title +"</b></div>");
		space.setWidth(ADAMConstants.BIG_MENU_GAP_WIDTH);
		panel.add(space);

		
		// Export Menu
		SplitButton export = new SplitButton("Export...");
		export.setIconStyle("export");
		excel = new MenuItem("Export Excel");
		excel.setIconStyle("sendToExcel");
		excel.addSelectionListener(ADAMController.exportExcelTable(vo, false));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");
		Menu exportMenu = new Menu();
		exportMenu.add(excel);
		export.setMenu(exportMenu);
		
		
		// Close Button Menu
		close = new Button(BabelFish.print().close());
		close.addSelectionListener(ADAMCustomController.closeShowProjects());

		close.setToolTip("Close Window");
		close.setIconStyle("delete");	

		
		panel.add(export);
		
		panel.add(close);
		return panel;
	}

	public MenuItem getExcel() {
		return excel;
	}
	
	

	
	
	
	
	
	
	
}
