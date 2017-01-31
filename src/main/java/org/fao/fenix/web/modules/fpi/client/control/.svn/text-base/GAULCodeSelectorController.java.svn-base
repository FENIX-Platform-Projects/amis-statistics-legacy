package org.fao.fenix.web.modules.fpi.client.control;

import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.AlertFactory;
import org.fao.fenix.web.modules.core.common.vo.FenixGaulVo;
import org.fao.fenix.web.modules.fpi.client.view.toolbar.GAULCodeSelectorToolbar;
import org.fao.fenix.web.modules.fpi.common.constants.GAULCodeSelectorConstants;
import org.fao.fenix.web.modules.fpi.common.services.FpiServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

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

public class GAULCodeSelectorController {

	public static void addContinentTrees(final Window window) {
		addWorldTree(window);
		FpiServiceEntry.getInstance().getContinents(new AsyncCallback<List<FenixGaulVo>>() {
			public void onSuccess(List<FenixGaulVo> result) {
				for (int i = 0; i < result.size(); i++) {
					Tree tree = new Tree();
					tree.addTreeListener(createTreeListener(window));
					TreeItem item = new TreeItem(result.get(i).getContinent());
					item.setTitle(BabelFish.print().gaulCodeNotAvailable());
					tree.addItem(item);
					((VerticalPanel) window.getData("panel")).add(tree);
					((HashMap<TreeItem, FenixGaulVo>) window.getData("treeItemMap")).put(item, result.get(i));
				}
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failure", "GAULCodeSelector @ addContinentTrees()");
			}
		});
	}
	
	public static void addWorldTree(final Window window) {
		Tree tree = new Tree();
		TreeItem item = new TreeItem(BabelFish.print().gaulWorld());
		tree.addItem(item);
		((VerticalPanel) window.getData("panel")).add(tree);
	}

	public static TreeListener createTreeListener(final Window window) {
		return new TreeListener() {
			public void onTreeItemStateChanged(TreeItem item) {
				System.out.println("onTreeItemStateChanged");
			}

			public void onTreeItemSelected(TreeItem item) {
				FenixGaulVo vo = ((HashMap<TreeItem, FenixGaulVo>) window.getData("treeItemMap")).get(item);
				
				if (vo.getLevel().equals(GAULCodeSelectorConstants.CONTINENT)) {
					((GAULCodeSelectorToolbar)window.getData("toolbar")).updateGAULCodeReminder(vo.getContinent(), BabelFish.print().gaulCodeNotAvailable());
					addRegionsToTreeItem(window, item);
				} else if (vo.getLevel().equals(GAULCodeSelectorConstants.REGION)) {
					((GAULCodeSelectorToolbar)window.getData("toolbar")).updateGAULCodeReminder(vo.getRegion(), vo.getCode());
					addNationsToTreeItem(window, item);
				} else if (vo.getLevel().equals(GAULCodeSelectorConstants.NATION)) {
					((GAULCodeSelectorToolbar)window.getData("toolbar")).updateGAULCodeReminder(vo.getName(), vo.getCode());
					addProvencesToTreeItem(window, item);
				} else if (vo.getLevel().equals(GAULCodeSelectorConstants.PROVENCE)) {
					((GAULCodeSelectorToolbar)window.getData("toolbar")).updateGAULCodeReminder(vo.getName(), vo.getCode());
					addCitiesToTreeItem(window, item);
				} else if (vo.getLevel().equals(GAULCodeSelectorConstants.CITY)) {
					((GAULCodeSelectorToolbar)window.getData("toolbar")).updateGAULCodeReminder(vo.getName(), vo.getCode());
				}
			}
		};
	}

	public static void addRegionsToTreeItem(final Window window, final TreeItem item) {
		FpiServiceEntry.getInstance().getRegionsByContinentName(item.getText(), new AsyncCallback<List<FenixGaulVo>>() {
			public void onSuccess(List<FenixGaulVo> result) {
				for (int i = 0; i < result.size(); i++) {
					TreeItem regionItem = new TreeItem(result.get(i).getRegion());
					regionItem.setTitle(BabelFish.print().gaulCodeNotAvailable());
					item.addItem(regionItem);
					((HashMap<TreeItem, FenixGaulVo>) window.getData("treeItemMap")).put(regionItem, result.get(i));
				}
			}

			public void onFailure(Throwable caught) {
				AlertFactory.failureAlert("RPC Failure", "GAULCodeSelector @ addRegionsToTreeItem(" + item.getText() + ")");
			}
		});
	}

	public static void addNationsToTreeItem(final Window window, final TreeItem item) {
		FpiServiceEntry.getInstance().getNationsByRegionName(item.getText(), new AsyncCallback<List<FenixGaulVo>>() {
			public void onSuccess(List<FenixGaulVo> result) {
				for (int i = 0; i < result.size(); i++) {
					TreeItem regionItem = new TreeItem(result.get(i).getName());
					regionItem.setTitle(BabelFish.print().gaulCode() + ": " + result.get(i).getCode());
					item.addItem(regionItem);
					((HashMap<TreeItem, FenixGaulVo>) window.getData("treeItemMap")).put(regionItem, result.get(i));
				}
			}

			public void onFailure(Throwable caught) {
				AlertFactory.failureAlert("RPC Failure", "GAULCodeSelector @ addNationsToTreeItem(" + item.getText() + ")");
			}
		});
	}

	public static void addProvencesToTreeItem(final Window window, final TreeItem item) {
		FenixGaulVo vo = ((HashMap<TreeItem, FenixGaulVo>) window.getData("treeItemMap")).get(item);
		FpiServiceEntry.getInstance().getProvencesByNationCode(vo.getCode(), new AsyncCallback<List<FenixGaulVo>>() {
			public void onSuccess(List<FenixGaulVo> result) {
				for (int i = 0; i < result.size(); i++) {
					TreeItem regionItem = new TreeItem(result.get(i).getName());
					regionItem.setTitle(BabelFish.print().gaulCode() + ": " + result.get(i).getCode());
					item.addItem(regionItem);
					((HashMap<TreeItem, FenixGaulVo>) window.getData("treeItemMap")).put(regionItem, result.get(i));
				}
			}

			public void onFailure(Throwable caught) {
				AlertFactory.failureAlert("RPC Failure", "GAULCodeSelector @ addProvencesToTreeItem(" + item.getText() + ")");
			}
		});
	}

	public static void addCitiesToTreeItem(final Window window, final TreeItem item) {
		FenixGaulVo vo = ((HashMap<TreeItem, FenixGaulVo>) window.getData("treeItemMap")).get(item);
		FpiServiceEntry.getInstance().getCitiesByProvenceCode(vo.getCode(), new AsyncCallback<List<FenixGaulVo>>() {
			public void onSuccess(List<FenixGaulVo> result) {
				for (int i = 0; i < result.size(); i++) {
					TreeItem regionItem = new TreeItem(result.get(i).getName());
					regionItem.setTitle(BabelFish.print().gaulCode() + ": " + result.get(i).getCode());
					item.addItem(regionItem);
					((HashMap<TreeItem, FenixGaulVo>) window.getData("treeItemMap")).put(regionItem, result.get(i));
				}
			}

			public void onFailure(Throwable caught) {
				AlertFactory.failureAlert("RPC Failure", "GAULCodeSelector @ addCitiesToTreeItem(" + item.getText() + ")");
			}
		});
	}
	
	public static SelectionListener<IconButtonEvent> buildGetGaulCodeListener(final ToolBar toolbar, final HorizontalPanel parameterPanel, final HTML reminder) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				//FenixAlert.info("buildGetGaulCodeListener", "buildGetGaulCodeListener");
				String name = (String)toolbar.getData("gaulName");
				String value = (String)toolbar.getData("gaulCode");
				HTML gaul = new HTML(name);
				parameterPanel.setData("gaulCode", value);
				// ((HTML)parameterPanel.getData("parameterValue")).setText(name);
				reminder.setHTML(name);
				parameterPanel.setData("parameterValue", gaul);
			}
		};
	}

}