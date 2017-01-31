package org.fao.fenix.web.modules.coding.client.view.creator;


import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.coding.client.view.vo.DcmtCodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CodingCodeSelector {

	public static Window window;

	public static VerticalPanel panel;

	public static Map<TreeItem, DcmtCodeVo> treeItemMap;

	public CodingCodeSelector() {
		window = createWindow("Dcmt Code Selector", 500, 400);
		panel = new VerticalPanel();
		panel.setHeight("100px");
		treeItemMap = new HashMap<TreeItem, DcmtCodeVo>();
		window.setData("panel", panel);
		window.setData("treeItemMap", treeItemMap);
	}
	
	public static Window createWindow(String title, int width, int height) {
		Window window = new Window();
		window.setResizable(true);
		window.setModal(true);
		window.setCollapsible(true);
		window.setMaximizable(true);
		window.setHeading(title);
		window.setSize(width, height);
		window.setBodyStyleName("pad-text");
		window.setScrollMode(Style.Scroll.AUTO);
		return window;
	}

	public void build(HorizontalPanel parameterPanel) {
		window.add(panel);
		addWorldTree(window);
		window.show();
	}

	
	public static void addWorldTree(final Window window) {
		Tree tree = new Tree();
		TreeItem item = new TreeItem(BabelFish.print().gaulWorld());
		tree.addItem(item);
		((VerticalPanel) window.getData("panel")).add(tree);
	}
}