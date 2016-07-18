package org.fao.fenix.web.modules.edi.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.edi.client.view.EDIDatabaseSettingsPanel;
import org.fao.fenix.web.modules.edi.client.view.faostat.FAOStatDataPanel;
import org.fao.fenix.web.modules.edi.client.view.faostat.FAOStatTabPanel;
import org.fao.fenix.web.modules.edi.client.view.faostat.FAOStatWindow;
import org.fao.fenix.web.modules.edi.common.services.FAOStatServiceEntry;
import org.fao.fenix.web.modules.edi.common.vo.EDISettings;
import org.fao.fenix.web.modules.edi.common.vo.FAOStatParametersVO;
import org.fao.fenix.web.modules.excelimporter.client.control.ExcelImporterController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class FAOStatController {

	public static SelectionListener<ButtonEvent> importFAOStatDataset(final FAOStatWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent e) {
				if (isValid(w)) {
					FAOStatParametersVO vo = collectParameters(w);
					try {
						final LoadingWindow l = new LoadingWindow("FAOStat Connector", "Connecting to the FAOStat DB...", "Loading...");
						FAOStatServiceEntry.getInstance().importFAOStatDataset(vo, new AsyncCallback<Long>() {
							public void onSuccess(final Long datasetID) {
								l.destroyLoadingBox();
								w.setDatasetID(datasetID);
								w.getFaoStatTabPanel().getFenixPanel().getTabItem().setEnabled(true);
								w.getFaoStatTabPanel().getPanel().setSelection(w.getFaoStatTabPanel().getFenixPanel().getTabItem());
								w.getFaoStatTabPanel().getFenixPanel().getOpenOLAPButton().addClickHandler(ExcelImporterController.openAsOLAP(datasetID));
								w.getFaoStatTabPanel().getFenixPanel().getOpenChartButton().addClickHandler(ExcelImporterController.openAsChart(datasetID));
								w.getFaoStatTabPanel().getFenixPanel().getOpenTableButton().addClickHandler(ExcelImporterController.openAsTable(datasetID, "FAOStat Dataset"));
								w.getFaoStatTabPanel().getFenixPanel().getOpenMapButton().addClickHandler(new ClickHandler() {
									public void onClick(ClickEvent arg0) {
										FenixAlert.info(BabelFish.print().info(), "This feature has not been implemented yet.");
									}
								});
								FenixAlert.info(BabelFish.print().info(), "Dataset Successfully Imported!");
								l.destroyLoadingBox();
							}
							public void onFailure(Throwable e) {
								l.destroyLoadingBox();
								FenixAlert.error(BabelFish.print().error(), e.getMessage());
								l.destroyLoadingBox();							
							}
						});
					} catch (FenixGWTException e1) {
						FenixAlert.error(BabelFish.print().error(), e1.getMessage());
					}
				} else {
					FenixAlert.info(BabelFish.print().info(), "Please check your selection.");
				}
			}
		};
	}
	
	public static boolean isValid(final FAOStatWindow w) {
		if (w.getFaoStatTabPanel().getDataPanel().getGroupList().getSelectedIndex() == 0) {
			FenixAlert.info(BabelFish.print().info(), "Please Select a Group");
			return false;
		} else if (w.getFaoStatTabPanel().getDataPanel().getDomainList().getSelectedIndex() == 0) {
			FenixAlert.info(BabelFish.print().info(), "Please Select a Domain");
			return false;
		} else if (w.getFaoStatTabPanel().getDataPanel().getItemList().getSelectedIndex() == 0) {
			FenixAlert.info(BabelFish.print().info(), "Please Select an Item");
			return false;
		} else if (w.getFaoStatTabPanel().getDataPanel().getElementList().getSelectedIndex() == 0) {
			FenixAlert.info(BabelFish.print().info(), "Please Select an Element");
			return false;
		}
		return true;
	}
	
	public static ChangeHandler groupList(final FAOStatWindow w) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent ce) {
				FAOStatParametersVO vo = collectParameters(w);
				if (w.getFaoStatTabPanel().getDataPanel().getGroupList().getSelectedIndex() > 0) {
					populateList(w.getFaoStatTabPanel().getDataPanel().getDomainList(), vo, EDISettings.Domain);
				} else {
					w.getFaoStatTabPanel().getDataPanel().getDomainList().clear();
					FenixAlert.info(BabelFish.print().info(), "Please Select a Group");
				}
			}
		};
	}
	
	public static ChangeHandler domainList(final FAOStatWindow w) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent ce) {
				FAOStatParametersVO vo = collectParameters(w);
				if (w.getFaoStatTabPanel().getDataPanel().getDomainList().getSelectedIndex() > 0) {
					populateList(w.getFaoStatTabPanel().getDataPanel().getItemList(), vo, EDISettings.DomainItem);
				} else {
					w.getFaoStatTabPanel().getDataPanel().getItemList().clear();
					FenixAlert.info(BabelFish.print().info(), "Please Select a Domain");
				}
			}
		};
	}
	
	public static ChangeHandler itemList(final FAOStatWindow w) {
		return new ChangeHandler() {
			public void onChange(ChangeEvent ce) {
				FAOStatParametersVO vo = collectParameters(w);
				if (w.getFaoStatTabPanel().getDataPanel().getItemList().getSelectedIndex() > 0) {
					populateList(w.getFaoStatTabPanel().getDataPanel().getElementList(), vo, EDISettings.Element);
					populateList(w.getFaoStatTabPanel().getDataPanel().getAreaList(), vo, EDISettings.Area);
				} else {
					w.getFaoStatTabPanel().getDataPanel().getAreaList().clear();
					w.getFaoStatTabPanel().getDataPanel().getElementList().clear();
					FenixAlert.info(BabelFish.print().info(), "Please Select an Item");
				}
			}
		};
	}
	
	public static void populateList(final ListBox targetList, final FAOStatParametersVO vo, final EDISettings tableName) {
		final LoadingWindow l = new LoadingWindow("FAOStat Connector", "Connecting to the FAOStat DB to Retrieve " + tableName.name() + "s", "Loading...");
		try {
			FAOStatServiceEntry.getInstance().getFAOStatDimensions(vo, tableName, new AsyncCallback<Map<String,String>>() {
				public void onSuccess(Map<String, String> domains) {
					l.destroyLoadingBox();
					targetList.clear();
					targetList.addItem("Please Select...");
					LinkedHashMap<String, String> sorted = sortByValues(domains);
					for (String code : sorted.keySet())
						targetList.addItem(sorted.get(code), code);
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable e) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
					l.destroyLoadingBox();							
				}
			});
		} catch (FenixGWTException e) {
			l.destroyLoadingBox();
			FenixAlert.error(BabelFish.print().error(), e.getMessage());
			l.destroyLoadingBox();
		}
	}
	
	public static FAOStatParametersVO collectParameters(FAOStatWindow w) {
		
		FAOStatParametersVO vo = new FAOStatParametersVO();
		FAOStatTabPanel tp = w.getFaoStatTabPanel();
		FAOStatDataPanel dp = tp.getDataPanel();
		EDIDatabaseSettingsPanel dbsp = tp.getSettingsPanel().getDatabaseSettingsPanel();
		
		Map<String, String> areas = new HashMap<String, String>();
		for (int i = 0 ; i < dp.getAreaList().getItemCount() ; i++)
			if (dp.getAreaList().isItemSelected(i))
				areas.put(dp.getAreaList().getValue(i), dp.getAreaList().getItemText(i));
		vo.setAreas(areas);
		
		vo.setConnectionDriver(dbsp.getDatabaseDriver().getValue(dbsp.getDatabaseDriver().getSelectedIndex()));
		vo.setDatasource(dbsp.getDatasourceList().getValue(dbsp.getDatasourceList().getSelectedIndex()));
		vo.setDbName(dbsp.getDbName().getValue());
		vo.setDbPassword(dbsp.getDbPassword().getValue());
		vo.setDbPortNumber(dbsp.getDbPortNumber().getValue());
		vo.setDbServerName(dbsp.getDbServerName().getValue());
		vo.setDbUrl(dbsp.getDbUrl().getValue());
		vo.setDbUserName(dbsp.getDbUsername().getValue());
		
		Map<String, String> domains = new HashMap<String, String>();
		for (int i = 0 ; i < dp.getDomainList().getItemCount() ; i++) 
			if (dp.getDomainList().isItemSelected(i)) 
				domains.put(dp.getDomainList().getValue(i), dp.getDomainList().getItemText(i));
		vo.setDomains(domains);
		
		Map<String, String> elements = new HashMap<String, String>();
		for (int i = 0 ; i < dp.getElementList().getItemCount() ; i++)
			if (dp.getElementList().isItemSelected(i))
				elements.put(dp.getElementList().getValue(i), dp.getElementList().getItemText(i));
		vo.setElements(elements);
		
		Map<String, String> years = new HashMap<String, String>();
		for (int i = 0 ; i < dp.getYearList().getItemCount() ; i++)
			if (dp.getYearList().isItemSelected(i))
				years.put(dp.getYearList().getValue(i), dp.getYearList().getItemText(i));
		vo.setYears(years);
		
		Map<String, String> items = new HashMap<String, String>();
		for (int i = 0 ; i < dp.getItemList().getItemCount() ; i++)
			if (dp.getItemList().isItemSelected(i))
				items.put(dp.getItemList().getValue(i), dp.getItemList().getItemText(i));
		vo.setItems(items);
		
		vo.setGroupCode(dp.getGroupList().getValue(dp.getGroupList().getSelectedIndex()));
		vo.setGroupLabel(dp.getGroupList().getItemText(dp.getGroupList().getSelectedIndex()));
		vo.setLanguage(CheckLanguage.getLanguage());
		
		return vo;
	}
	
	public static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i=0; i<size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}
	
	public static SelectionListener<ButtonEvent> exportExcel(final FAOStatWindow w, final boolean showCodes, final boolean showLabels) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent e) {
				if (isValid(w)) {
					FAOStatParametersVO vo = collectParameters(w);
					try {
						final LoadingWindow l = new LoadingWindow("FAOStat Connector", "Connecting to the FAOStat DB...", "Loading...");
						FAOStatServiceEntry.getInstance().exportExcel(vo, showCodes, showLabels, new AsyncCallback<String>() {
							public void onSuccess(final String filename) {
								l.destroyLoadingBox();
								Window.open("../olapExcel/" + filename, "_blank", "status=no");
								l.destroyLoadingBox();
							}
							public void onFailure(Throwable e) {
								l.destroyLoadingBox();
								FenixAlert.error(BabelFish.print().error(), e.getMessage());
								l.destroyLoadingBox();							
							}
						});
					} catch (FenixGWTException e1) {
						FenixAlert.error(BabelFish.print().error(), e1.getMessage());
					}
				} else {
					FenixAlert.info(BabelFish.print().info(), "Please check your selection.");
				}
			}
		};
	}
	
}