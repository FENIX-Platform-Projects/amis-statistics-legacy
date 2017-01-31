package org.fao.fenix.web.modules.fpi.client.view;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.WindowFactory;
import org.fao.fenix.web.modules.core.common.vo.HSVo;
import org.fao.fenix.web.modules.fpi.client.control.HSCodeSelectorController;
import org.fao.fenix.web.modules.fpi.client.view.toolbar.HSCodeSelectorToolbar;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HSCodeSelector {

public static Window window;
	
	public static VerticalPanel panel;

	public static Map<TreeItem, HSVo> treeItemMap;

	public HSCodeSelector() {
		window = WindowFactory.createWindow(BabelFish.print().hsCodeSelector(), 650, 300);
		panel = new VerticalPanel();
		panel.setHeight("100px");
		treeItemMap = new HashMap<TreeItem, HSVo>();
		window.setData("panel", panel);
		window.setData("treeItemMap", treeItemMap);
	}

	public void build(HorizontalPanel parameterPanel) {
		HSCodeSelectorToolbar toolbar = new HSCodeSelectorToolbar();
		window.setData("toolbar", toolbar);
		window.add(toolbar.build(parameterPanel));
		window.add(panel);
		HSCodeSelectorController.addLevel0HSCode(window);
		window.show();
	}
	
}
