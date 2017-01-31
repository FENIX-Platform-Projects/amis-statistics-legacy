package org.fao.fenix.web.modules.rainfall.client.control;

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.rainfall.client.view.GAULExplorerPanel;
import org.fao.fenix.web.modules.rainfall.common.services.RainfallServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreeEvent;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.rpc.AsyncCallback;

@SuppressWarnings("deprecation")
public class GAULExplorerController {

	public static void initiateTreeWithCountries(final Tree tree) {
		final LoadingWindow loading = new LoadingWindow(BabelFish.print().estimatedRainfallReport(), BabelFish.print().sysBusy(), BabelFish.print().pleaseWait());
		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				loading.destroyLoadingBox();
				TreeItem root = tree.getRootItem();
				for (CodeVo vo : vos) {
					TreeItem i = createTreeItem(vo, "0", "0");
					root.add(i);
				}
				loading.destroyLoadingBox();
			}
			public void onFailure(Throwable caught) {
				loading.destroyLoadingBox();
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static Listener<TreeEvent> createRegionLeaves() {
		return new Listener<TreeEvent>() {
			public void handleEvent(TreeEvent te) {
				final TreeItem item = te.getTree().getSelectedItem();
				if (item.getItemCount() == 0) {
					int gaulLevel = Integer.parseInt((String) item.getData("gaulLevel"));
					String gaulCode = (String) item.getData("gaulCode");
					if (gaulLevel == 0) {
						addRegions(item, gaulCode);
					} else if (gaulLevel == 1) {
						String gaulFather = (String) item.getData("gaulFather");
						if (GAULExplorerUtils.africaGaulCodes.contains(gaulFather))
							addProvinces(item, gaulCode);
					}
				}
			}
		};
	}
	
	public static void addRegions(final TreeItem countryItem, final String gaul0code) {
		RainfallServiceEntry.getInstance().findAllGaul1FromGaul0(gaul0code, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				for (CodeVo vo : vos) {
					final TreeItem region = createTreeItem(vo, "1", gaul0code);
					countryItem.add(region);
				}
				countryItem.setChecked(false);
				countryItem.setExpanded(true);
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	public static void addProvinces(final TreeItem countryItem, final String gaul1code) {
		RainfallServiceEntry.getInstance().findAllGaul2FromGaul1(gaul1code, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				for (CodeVo vo : vos) {
					final TreeItem region = createTreeItem(vo, "2", gaul1code);
					countryItem.add(region);
				}
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}
	
	private static TreeItem createTreeItem(CodeVo codeVo, String gaulLevel, String gaulFather) {
		return createTreeItem(codeVo.getCode(), codeVo.getLabel(), gaulLevel, gaulFather);
	}
	
	private static TreeItem createTreeItem(String code, String label, String gaulLevel, String gaulFather) {
		TreeItem item = new TreeItem(label);
		item.setData("gaulCode", code);
		item.setData("codingSystem", "GAUL");
		item.setData("gaulLevel", gaulLevel);
		item.setData("gaulFather", gaulFather);
		item.setIconStyle("geoIcon");
		return item;
	}
	
	public static SelectionListener<ButtonEvent> addUserSelection(final Tree gaulTree, final Tree userSelectionTree) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (TreeItem item : gaulTree.getChecked()) {
					String gaulLevel = (String) item.getData("gaulLevel");
					if (!gaulLevel.equals("0")) {
						String gaulCode = (String) item.getData("gaulCode");
						if (codeIsNotAlreadyIn(userSelectionTree, gaulCode)) {
							String gaulFather = (String) item.getData("gaulFather");
							String gaulLabel = item.getText();
							TreeItem i = createTreeItem(gaulCode, gaulLabel, gaulLevel, gaulFather);
							userSelectionTree.getRootItem().add(i);
						}
					}
				}
			}
		};
	}
	
	private static boolean codeIsNotAlreadyIn(Tree tree, String gaulCode) {
		for (TreeItem i : tree.getRootItem().getItems()) {
			String userSelectionCode = (String) i.getData("gaulCode");
			if (userSelectionCode.equalsIgnoreCase(gaulCode)) 
				return false;
		}
		return true;
	}
	
	public static SelectionChangedListener<GaulModelData> oracleListener(final GAULExplorerPanel gaulExplorerPanel) {
		return new SelectionChangedListener<GaulModelData>() {
			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
				Tree tree = gaulExplorerPanel.getTree();
				ComboBox<GaulModelData> gaulList = gaulExplorerPanel.getGaulList();
				for (int i = 0; i < gaulList.getSelection().size(); i++) {
					String gaulLabel = gaulList.getSelection().get(i).getGaulLabel();
					
					for (TreeItem item : tree.getRootItem().getItems()) {
						if (!item.getText().toLowerCase().contains(gaulLabel.toLowerCase()))
							item.hide();
						else
							item.show();
					}
				}
			}
		};
	}
	
}
