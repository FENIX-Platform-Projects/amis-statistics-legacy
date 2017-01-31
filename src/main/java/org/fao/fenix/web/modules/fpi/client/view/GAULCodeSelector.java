package org.fao.fenix.web.modules.fpi.client.view;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.WindowFactory;
import org.fao.fenix.web.modules.core.common.vo.FenixGaulVo;
import org.fao.fenix.web.modules.fpi.client.control.GAULCodeSelectorController;
import org.fao.fenix.web.modules.fpi.client.view.toolbar.GAULCodeSelectorToolbar;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GAULCodeSelector {

	public static Window window;

	public static VerticalPanel panel;

	public static Map<TreeItem, FenixGaulVo> treeItemMap;

	public GAULCodeSelector() {
		window = WindowFactory.createWindow(BabelFish.print().gaulCodeSelector(), 325, 300);
		panel = new VerticalPanel();
		panel.setHeight("100px");
		treeItemMap = new HashMap<TreeItem, FenixGaulVo>();
		window.setData("panel", panel);
		window.setData("treeItemMap", treeItemMap);
	}

	public void build(HorizontalPanel parameterPanel) {
		GAULCodeSelectorToolbar toolbar = new GAULCodeSelectorToolbar();
		window.setData("toolbar", toolbar);
		window.add(toolbar.build(parameterPanel));
		window.add(panel);
		GAULCodeSelectorController.addContinentTrees(window);
		window.show();
	}

}
