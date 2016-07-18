package org.fao.fenix.web.modules.rainfall.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.rainfall.client.view.RainfallTool;
import org.fao.fenix.web.modules.rainfall.common.services.RainfallServiceEntry;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallConfigurationVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.SaveAsWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class ToolConfigurationController {

	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> deleteFavourite(final RainfallTool w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ListBox list = w.getRainfallTabPanel().getConfigurationPanel().getConfigurationList();
				if (list.getSelectedIndex() > 0) {
					final String filename = list.getItemText(list.getSelectedIndex()) + ".xml";
					RainfallServiceEntry.getInstance().deleteFavourite(filename, new AsyncCallback() {
						public void onSuccess(Object result) {
							refreshConfigurationList(w);
							FenixAlert.info(BabelFish.print().info(), filename + " " + BabelFish.print().deleted());
						}

						public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().info(), e.getMessage());
						}
					});
				} else {
					FenixAlert.alert(BabelFish.print().info(), BabelFish.print().selectAFavouriteToDelete());
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> saveAs(final RainfallTool w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (RainfallToolController.isValid(w)) {
					ListBox list = w.getRainfallTabPanel().getConfigurationPanel().getConfigurationList();
					SaveAsWindow saveAsWindow = new SaveAsWindow(BabelFish.print().saveAs(), list.getItemText(list.getSelectedIndex()));
					saveAsWindow.getButton().addSelectionListener(saveFavouriteListener(w, saveAsWindow));
					saveAsWindow.show();
				} else {
					FenixAlert.alert(BabelFish.print().information(), "Please select at least one region and fill up all the parameters in the Period panel please.");
				}
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> saveFavouriteListener(final RainfallTool w, final SaveAsWindow saveAsWindow) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				RainfallConfigurationVo vo = createConfigurationVo(w);
				final String filename = saveAsWindow.getTextField().getValue() + ".xml";
				RainfallServiceEntry.getInstance().saveFavourite(vo, filename, new AsyncCallback() {
					public void onSuccess(Object result) {
						refreshConfigurationList(w);
						FenixAlert.info(BabelFish.print().info(), filename + " " + BabelFish.print().savedAsFavourite());
					}

					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
					}
				});
			}
		};
	}

	private static void refreshConfigurationList(final RainfallTool w) {
		ListBox list = w.getRainfallTabPanel().getConfigurationPanel().getConfigurationList();
		for (int i = list.getItemCount() - 1; i >= 0; i--)
			list.removeItem(i);
		fillConfigurationList(list);
	}

	public static RainfallConfigurationVo createConfigurationVo(final RainfallTool w) {

		RainfallConfigurationVo vo = new RainfallConfigurationVo();

		vo.setAverage(w.getRainfallTabPanel().getOptionPanel().getShowAverage().getValue());
		vo.setChart(w.getRainfallTabPanel().getOptionPanel().getChart().getValue());
		vo.setMap(w.getRainfallTabPanel().getOptionPanel().getMap().getValue());
		vo.setTable(w.getRainfallTabPanel().getOptionPanel().getTable().getValue());
		vo.setCumulate(w.getRainfallTabPanel().getOptionPanel().getCumulate().getValue());

		ListBox compare = w.getRainfallTabPanel().getSeasonPeriodPanel().getCompareYear();
		vo.setCompareYear(compare.getItemText(compare.getSelectedIndex()));
		ListBox fromDecade = w.getRainfallTabPanel().getSeasonPeriodPanel().getFromDecade();
		vo.setFromDecade(fromDecade.getValue(fromDecade.getSelectedIndex()));
		ListBox fromYear = w.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear();
		vo.setFromYear(fromYear.getItemText(fromYear.getSelectedIndex()));
		ListBox toDecade = w.getRainfallTabPanel().getSeasonPeriodPanel().getToDecade();
		vo.setToDecade(toDecade.getValue(toDecade.getSelectedIndex()));
		ListBox toYear = w.getRainfallTabPanel().getSeasonPeriodPanel().getToYear();
		vo.setToYear(toYear.getItemText(toYear.getSelectedIndex()));

		ListBox template = w.getRainfallTabPanel().getOptionPanel().getTemplateList();
		vo.setTemplate(template.getValue(template.getSelectedIndex()));

		Tree tree = w.getReminderPanel().getTree();
		List<CodeVo> vos = new ArrayList<CodeVo>();
		for (TreeItem i : tree.getRootItem().getItems()) {
			CodeVo cvo = new CodeVo();
			cvo.setLabel(i.getText());
			cvo.setCode((String) i.getData("gaulCode"));
			cvo.setDescription((String) i.getData("codingSystem"));
			vos.add(cvo);
		}
		vo.setRegions(vos);

		return vo;
	}

	public static void fillConfigurationList(final ListBox list) {
		RainfallServiceEntry.getInstance().findAllRainfallConfiguration(new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> result) {
				list.addItem(BabelFish.print().pleaseSelect());
				for (String conf : result)
					list.addItem(conf.substring(0, conf.length() - 4), conf);
			};

			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().info(), e.getMessage());
			};
		});
	}

	public static SelectionListener<ButtonEvent> loadConfiguration(final RainfallTool tool) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ListBox configurationList = tool.getRainfallTabPanel().getConfigurationPanel().getConfigurationList();
				String conf = configurationList.getValue(configurationList.getSelectedIndex());
				RainfallServiceEntry.getInstance().getRainfallConfiguration(conf, new AsyncCallback<RainfallConfigurationVo>() {
					public void onSuccess(RainfallConfigurationVo bean) {
						fillUserSelection(tool, bean);
						fillPeriod(tool, bean);
						fillOptions(tool, bean);
						fillTemplate(tool, bean);
					};

					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
					};
				});
			}
		};
	}

	public static SelectionListener<ButtonEvent> loadAndCreate(final RainfallTool tool) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ListBox configurationList = tool.getRainfallTabPanel().getConfigurationPanel().getConfigurationList();
				String conf = configurationList.getValue(configurationList.getSelectedIndex());
				RainfallServiceEntry.getInstance().getRainfallConfiguration(conf, new AsyncCallback<RainfallConfigurationVo>() {
					public void onSuccess(RainfallConfigurationVo bean) {
						fillUserSelection(tool, bean);
						fillPeriod(tool, bean);
						fillOptions(tool, bean);
						fillTemplate(tool, bean);
						RainfallToolController.createReport(tool);
					};

					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
					};
				});
			}
		};
	}
	
	public static void cleanTree(final RainfallTool tool) {
//		Tree tree = tool.getRainfallTabPanel().getTreeRegionPanel().getTree();
		Tree tree = null;
		TreeItem root = tree.getRootItem();
		for (int i = 0 ; i < root.getItemCount() ; i++) {
			TreeItem item = root.getItem(i); 
			if (item.isChecked())
				item.setChecked(false);
			for (int j = 0 ; j < item.getItemCount() ; j++) {
				TreeItem subItem = item.getItem(j);
				if (subItem.isChecked())
					subItem.setChecked(false);
			}
		}
	}

	private static void fillUserSelection(RainfallTool tool, RainfallConfigurationVo bean) {
		cleanTree(tool); // clean previously selected items
		Tree tree = tool.getReminderPanel().getTree();
		tree.getRootItem().removeAll();
		for (CodeVo vo : bean.getRegions()) {
			TreeItem item = new TreeItem(vo.getLabel());
			item.setData("gaulCode", vo.getCode());
			item.setData("codingSystem", vo.getDescription());
			tree.getRootItem().add(item);
		}
	}

	private static void fillPeriod(RainfallTool tool, RainfallConfigurationVo bean) {
		ListBox fromDecade = tool.getRainfallTabPanel().getSeasonPeriodPanel().getFromDecade();
		ListBox toDecade = tool.getRainfallTabPanel().getSeasonPeriodPanel().getToDecade();
		ListBox fromYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear();
		ListBox toYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getToYear();
		ListBox compareYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getCompareYear();
		for (int i = 0; i < fromDecade.getItemCount(); i++)
			if (fromDecade.getValue(i).equalsIgnoreCase(bean.getFromDecade()))
				fromDecade.setSelectedIndex(i);
		for (int i = 0; i < toDecade.getItemCount(); i++)
			if (toDecade.getValue(i).equalsIgnoreCase(bean.getToDecade()))
				toDecade.setSelectedIndex(i);
		for (int i = 0; i < toYear.getItemCount(); i++)
			if (toYear.getItemText(i).equalsIgnoreCase(bean.getToYear()))
				toYear.setSelectedIndex(i);
		for (int i = 0; i < fromYear.getItemCount(); i++)
			if (fromYear.getItemText(i).equalsIgnoreCase(bean.getFromYear()))
				fromYear.setSelectedIndex(i);
		for (int i = 0; i < compareYear.getItemCount(); i++)
			if (compareYear.getItemText(i).equalsIgnoreCase(bean.getCompareYear()))
				compareYear.setSelectedIndex(i);
	}

	private static void fillOptions(RainfallTool tool, RainfallConfigurationVo bean) {
		tool.getRainfallTabPanel().getOptionPanel().getCumulate().setValue(bean.getCumulate());
		tool.getRainfallTabPanel().getOptionPanel().getShowAverage().setValue(bean.getAverage());
		tool.getRainfallTabPanel().getOptionPanel().getMap().setValue(bean.getMap());
		tool.getRainfallTabPanel().getOptionPanel().getChart().setValue(bean.getChart());
		tool.getRainfallTabPanel().getOptionPanel().getTable().setValue(bean.getTable());
	}

	private static void fillTemplate(RainfallTool tool, RainfallConfigurationVo bean) {
		ListBox templateList = tool.getRainfallTabPanel().getOptionPanel().getTemplateList();
		for (int i = 0; i < templateList.getItemCount(); i++)
			if (templateList.getValue(i).equalsIgnoreCase(bean.getTemplate()))
				templateList.setSelectedIndex(i);
	}
	
}