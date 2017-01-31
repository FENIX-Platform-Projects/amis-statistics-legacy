package org.fao.fenix.web.modules.chartdesigner.client.view;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class ChartDesignerToolbar {

	private ToolBar toolbar;
	
	private Button manualButton;
	
	private HorizontalPanel wrapper;
	
	private MenuItem cloneItem;
	
	private MenuItem saveMenuItem;
	
	private MenuItem saveAsMenuItem;
	
	private MenuItem imageMenuItem;
	
	private MenuItem embedItem;
	
	private MenuItem excelMenuItem;
	
	private MenuItem csvMenuItem;
	
	private final static String WIDTH = "850px";
	
	public ChartDesignerToolbar() {
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
		
		cloneItem = new MenuItem("Clone");
		cloneItem.setIconStyle("textGroupIcon");
		m.add(cloneItem);
		
		b.setMenu(m);
		
		return b;
	}
	
	private Button buildExportMenu() {
		
		Button b = new Button("Export...");
		b.setIconStyle("export");
		
		Menu m = new Menu();
		
		imageMenuItem = new MenuItem("Export Chart as Image");
		imageMenuItem.setIconStyle("smallCamera");
		imageMenuItem.setEnabled(false);
		m.add(imageMenuItem);
		
		m.add(new SeparatorMenuItem()); 
		
		embedItem = new MenuItem("Embed in Your Website");
		embedItem.setIconStyle("sendToHTML");
		embedItem.setEnabled(false);
		m.add(embedItem);
		
		m.add(new SeparatorMenuItem());
		
		excelMenuItem = new MenuItem("Export Data as Excel File");
		excelMenuItem.setIconStyle("sendToExcel");
		m.add(excelMenuItem);
		
		csvMenuItem = new MenuItem("Export Data as CSV File");
		csvMenuItem.setIconStyle("workspaceTable");
		m.add(csvMenuItem);
		
		b.setMenu(m);
		
		return b;
	}
	
	private Button buildOpenAsMenu() {
		
		Button b = new Button("Open As...");
		b.setIconStyle("wand");
		
		Menu m = new Menu();
		
		MenuItem tableMenuItem = new MenuItem("Open as a Table");
		tableMenuItem.setIconStyle("workspaceTable");
		tableMenuItem.setEnabled(false);
		m.add(tableMenuItem);
		
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

	public Button getManualButton() {
		return manualButton;
	}

	public MenuItem getCloneItem() {
		return cloneItem;
	}

	public MenuItem getSaveMenuItem() {
		return saveMenuItem;
	}

	public MenuItem getSaveAsMenuItem() {
		return saveAsMenuItem;
	}

	public MenuItem getImageMenuItem() {
		return imageMenuItem;
	}

	public MenuItem getEmbedItem() {
		return embedItem;
	}

	public MenuItem getExcelMenuItem() {
		return excelMenuItem;
	}

	public MenuItem getCsvMenuItem() {
		return csvMenuItem;
	}
	
}