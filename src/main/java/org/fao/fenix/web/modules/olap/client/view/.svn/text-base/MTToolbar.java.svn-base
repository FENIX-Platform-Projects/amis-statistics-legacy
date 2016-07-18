package org.fao.fenix.web.modules.olap.client.view;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class MTToolbar {

	private ToolBar toolbar;
	
	private Button manualButton;
	
	private HorizontalPanel wrapper;
	
//	private MenuItem cloneItem;
	
	private MenuItem saveMenuItem;
	
	private MenuItem saveAsMenuItem;
	
	private MenuItem embedItem;
	
	private MenuItem excelMenuItem;
	
	private MenuItem openAsChartMenuItem;
	
	private final static String WIDTH = "850px";
	
	public MTToolbar() {
		wrapper = new HorizontalPanel();
		toolbar = new ToolBar();
		manualButton = new Button("Manual");
		manualButton.setIconStyle("help");
	}
	
	public HorizontalPanel build() {
		toolbar.add(manualButton);
		toolbar.add(new SeparatorToolItem());
		toolbar.add(buildSaveMenu());
		toolbar.add(buildOpenAsMenu());
		toolbar.add(buildExportMenu());
		toolbar.setWidth(WIDTH);
		wrapper.add(toolbar);
		return wrapper;
	}
	
	private Button buildSaveMenu() {
		
		Button b = new Button("Resource");
		b.setIconStyle("projectIcon");
		
		Menu m = new Menu();
		
		saveMenuItem = new MenuItem("Save");
		saveMenuItem.setIconStyle("save");
		m.add(saveMenuItem);
		
		saveAsMenuItem = new MenuItem("Save As");
		saveAsMenuItem.setIconStyle("saveAs");
		m.add(saveAsMenuItem);
		
		m.add(new SeparatorMenuItem());
		
//		cloneItem = new MenuItem("Clone");
//		cloneItem.setIconStyle("textGroupIcon");
//		m.add(cloneItem);
		
		b.setMenu(m);
		
		return b;
	}
	
	private Button buildExportMenu() {
		
		Button b = new Button("Export...");
		b.setIconStyle("export");
		
		Menu m = new Menu();
		
		embedItem = new MenuItem("Embed in Your Website");
		embedItem.setIconStyle("sendToHTML");
		m.add(embedItem);
		
		m.add(new SeparatorMenuItem());
		
		excelMenuItem = new MenuItem("Export Data as Excel File");
		excelMenuItem.setIconStyle("sendToExcel");
		m.add(excelMenuItem);
		
		b.setMenu(m);
		
		return b;
	}
	
	private Button buildOpenAsMenu() {
		
		Button b = new Button("Open As...");
		b.setIconStyle("wand");
		
		Menu m = new Menu();
		
		openAsChartMenuItem = new MenuItem("Open as a Chart");
		openAsChartMenuItem.setIconStyle("workspaceChart");
		m.add(openAsChartMenuItem);
		
		MenuItem olapMenuItem = new MenuItem("Open as a Multidimensional Table");
		olapMenuItem.setIconStyle("olap");
		olapMenuItem.setEnabled(false);
		m.add(olapMenuItem);
		
		MenuItem mapMenuItem = new MenuItem("Open as a Map");
		mapMenuItem.setIconStyle("workspaceMap");
		mapMenuItem.setEnabled(false);
		m.add(mapMenuItem);
		
		b.setMenu(m);
		
		return b;
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	public Button getManualButton() {
		return manualButton;
	}

//	public MenuItem getCloneItem() {
//		return cloneItem;
//	}

	public MenuItem getSaveMenuItem() {
		return saveMenuItem;
	}

	public MenuItem getSaveAsMenuItem() {
		return saveAsMenuItem;
	}

	public MenuItem getEmbedItem() {
		return embedItem;
	}

	public MenuItem getExcelMenuItem() {
		return excelMenuItem;
	}

	public MenuItem getOpenAsChartMenuItem() {
		return openAsChartMenuItem;
	}
	
}