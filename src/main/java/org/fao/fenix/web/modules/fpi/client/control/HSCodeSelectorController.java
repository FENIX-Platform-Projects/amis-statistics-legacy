package org.fao.fenix.web.modules.fpi.client.control;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.AlertFactory;
import org.fao.fenix.web.modules.core.common.vo.HSVo;
import org.fao.fenix.web.modules.fpi.client.view.toolbar.HSCodeSelectorToolbar;
import org.fao.fenix.web.modules.fpi.common.services.FpiServiceEntry;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HSCodeSelectorController {

	public static void addLevel0HSCode(final Window window) {
		FpiServiceEntry.getInstance().getLevel0HSCode(new AsyncCallback<List<HSVo>>() {
			public void onSuccess(List<HSVo> result) {
				for (int i = 0; i < result.size(); i++) {
					Tree tree = new Tree();
					tree.addTreeListener(createTreeListener(window));
					TreeItem item = new TreeItem(result.get(i).getCode() + " - " + result.get(i).getDescription());
					item.setTitle(result.get(i).getDescription());
					tree.addItem(item);
					((VerticalPanel) window.getData("panel")).add(tree);
					((HashMap<TreeItem, HSVo>) window.getData("treeItemMap")).put(item, result.get(i));
				}
			}

			public void onFailure(Throwable caught) {
				AlertFactory.failureAlert("RPC Failure", "HSCodeSelectorController @ addLevel0HSCode()");
			}
		});
	}

	public static TreeListener createTreeListener(final Window window) {
		return new TreeListener() {
			public void onTreeItemStateChanged(TreeItem item) {
				System.out.println("onTreeItemStateChanged");
			}

			public void onTreeItemSelected(TreeItem item) {
				HSVo vo = ((HashMap<TreeItem, HSVo>) window.getData("treeItemMap")).get(item);
				String description = vo.getDescription();
				if (description.length() > 64)
					description = description.substring(0, 64) + "...";
				((HSCodeSelectorToolbar) window.getData("toolbar")).updateHSCodeReminder(description, vo.getCode());
				if (vo.getCode().length() == 2) {
					((HSCodeSelectorToolbar) window.getData("toolbar")).updateHSCodeReminder(description, vo.getCode());
					addLevel1HSCode(window, item);
				} else if (vo.getCode().length() == 4) {
					((HSCodeSelectorToolbar) window.getData("toolbar")).updateHSCodeReminder(description, vo.getCode());
					addLevel2HSCode(window, item);
				} else if (vo.getCode().length() == 4) {
					((HSCodeSelectorToolbar) window.getData("toolbar")).updateHSCodeReminder(description, vo.getCode());
				}
			}
		};
	}

	public static void addLevel1HSCode(final Window window, final TreeItem item) {
		HSVo vo = ((HashMap<TreeItem, HSVo>) window.getData("treeItemMap")).get(item);
		FpiServiceEntry.getInstance().getLevel1HSCode(vo.getCode(), new AsyncCallback<List<HSVo>>() {
			public void onSuccess(List<HSVo> result) {
				for (int i = 0; i < result.size(); i++) {
					if (result.get(i).getCode().length() <= 4 && result.get(i).getCode().length() > 2 && !result.get(i).getCode().contains("_")) {
						TreeItem level1item = new TreeItem(result.get(i).getCode() + " - " + result.get(i).getDescription());
						level1item.setTitle(result.get(i).getDescription());
						item.addItem(level1item);
						((HashMap<TreeItem, HSVo>) window.getData("treeItemMap")).put(level1item, result.get(i));
					}
				}
			}

			public void onFailure(Throwable caught) {
				AlertFactory.failureAlert("RPC Failure", "HSCodeSelectorController @ addLevel0HSCode()");
			}
		});
	}

	public static void addLevel2HSCode(final Window window, final TreeItem item) {
		HSVo vo = ((HashMap<TreeItem, HSVo>) window.getData("treeItemMap")).get(item);
		FpiServiceEntry.getInstance().getLevel2HSCode(vo.getCode(), new AsyncCallback<List<HSVo>>() {
			public void onSuccess(List<HSVo> result) {
				for (int i = 0; i < result.size(); i++) {
					TreeItem level1item = new TreeItem(result.get(i).getCode() + " - " + result.get(i).getDescription());
					level1item.setTitle(result.get(i).getDescription());
					item.addItem(level1item);
					((HashMap<TreeItem, HSVo>) window.getData("treeItemMap")).put(level1item, result.get(i));
				}
			}

			public void onFailure(Throwable caught) {
				AlertFactory.failureAlert("RPC Failure", "HSCodeSelectorController @ addLevel0HSCode()");
			}
		});
	}

	public static SelectionListener<IconButtonEvent> buildGetHSCodeListener(final ToolBar toolbar, final HorizontalPanel parameterPanel, final HTML reminder) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				String name = (String) toolbar.getData("hsName");
				String value = (String) toolbar.getData("hsCode");
				HTML hs = new HTML(name);
				parameterPanel.setData("hsCode", value);
				// ((HTML) parameterPanel.getData("parameterValue")).setText(name);
				reminder.setHTML(name);
				parameterPanel.setData("parameterValue", hs);
			}
		};
	}

}
