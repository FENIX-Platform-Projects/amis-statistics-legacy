/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.rainfall.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.rainfall.client.view.RainfallWindow;
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

public class ConfigurationController {

	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> deleteFavourite(final RainfallWindow w) {
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

	public static SelectionListener<ButtonEvent> saveAs(final RainfallWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ListBox list = w.getRainfallTabPanel().getConfigurationPanel().getConfigurationList();
				SaveAsWindow saveAsWindow = new SaveAsWindow(BabelFish.print().saveAs(), list.getItemText(list.getSelectedIndex()));
				saveAsWindow.getButton().addSelectionListener(saveFavouriteListener(w, saveAsWindow));
				saveAsWindow.show();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static SelectionListener<ButtonEvent> saveFavouriteListener(final RainfallWindow w, final SaveAsWindow saveAsWindow) {
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

	private static void refreshConfigurationList(final RainfallWindow w) {
		ListBox list = w.getRainfallTabPanel().getConfigurationPanel().getConfigurationList();
		for (int i = list.getItemCount() - 1; i >= 0; i--)
			list.removeItem(i);
		fillConfigurationList(list);
	}

	public static RainfallConfigurationVo createConfigurationVo(final RainfallWindow w) {

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

	public static SelectionListener<ButtonEvent> loadConfiguration(final RainfallWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ListBox configurationList = window.getRainfallTabPanel().getConfigurationPanel().getConfigurationList();
				String conf = configurationList.getValue(configurationList.getSelectedIndex());
				RainfallServiceEntry.getInstance().getRainfallConfiguration(conf, new AsyncCallback<RainfallConfigurationVo>() {
					public void onSuccess(RainfallConfigurationVo bean) {
						fillUserSelection(window, bean);
						fillPeriod(window, bean);
						fillOptions(window, bean);
						fillTemplate(window, bean);
					};

					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
					};
				});
			}
		};
	}

	public static SelectionListener<ButtonEvent> loadAndCreate(final RainfallWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ListBox configurationList = window.getRainfallTabPanel().getConfigurationPanel().getConfigurationList();
				String conf = configurationList.getValue(configurationList.getSelectedIndex());
				RainfallServiceEntry.getInstance().getRainfallConfiguration(conf, new AsyncCallback<RainfallConfigurationVo>() {
					public void onSuccess(RainfallConfigurationVo bean) {
						fillUserSelection(window, bean);
						fillPeriod(window, bean);
						fillOptions(window, bean);
						fillTemplate(window, bean);
						RainfallController.createReport(window);
					};

					public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
					};
				});
			}
		};
	}

	private static void fillUserSelection(RainfallWindow window, RainfallConfigurationVo bean) {
		Tree tree = window.getReminderPanel().getTree();
		tree.getRootItem().removeAll();
		for (CodeVo vo : bean.getRegions()) {
			TreeItem item = new TreeItem(vo.getLabel());
			item.setData("gaulCode", vo.getCode());
			item.setData("codingSystem", vo.getDescription());
			tree.getRootItem().add(item);
		}
	}

	private static void fillPeriod(RainfallWindow window, RainfallConfigurationVo bean) {
		ListBox fromDecade = window.getRainfallTabPanel().getSeasonPeriodPanel().getFromDecade();
		ListBox toDecade = window.getRainfallTabPanel().getSeasonPeriodPanel().getToDecade();
		ListBox fromYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear();
		ListBox toYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getToYear();
		ListBox compareYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getCompareYear();
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

	private static void fillOptions(RainfallWindow window, RainfallConfigurationVo bean) {
		window.getRainfallTabPanel().getOptionPanel().getCumulate().setValue(bean.getCumulate());
		window.getRainfallTabPanel().getOptionPanel().getShowAverage().setValue(bean.getAverage());
		window.getRainfallTabPanel().getOptionPanel().getMap().setValue(bean.getMap());
		window.getRainfallTabPanel().getOptionPanel().getChart().setValue(bean.getChart());
		window.getRainfallTabPanel().getOptionPanel().getTable().setValue(bean.getTable());
	}

	private static void fillTemplate(RainfallWindow window, RainfallConfigurationVo bean) {
		ListBox templateList = window.getRainfallTabPanel().getOptionPanel().getTemplateList();
		for (int i = 0; i < templateList.getItemCount(); i++)
			if (templateList.getValue(i).equalsIgnoreCase(bean.getTemplate()))
				templateList.setSelectedIndex(i);
	}

}