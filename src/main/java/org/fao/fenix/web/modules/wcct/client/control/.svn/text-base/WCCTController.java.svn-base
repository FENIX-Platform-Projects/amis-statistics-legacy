package org.fao.fenix.web.modules.wcct.client.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.wcct.client.view.ChartCenterPanel;
import org.fao.fenix.web.modules.wcct.client.view.ChartWestPanel;
import org.fao.fenix.web.modules.wcct.client.view.MapCenterPanel;
import org.fao.fenix.web.modules.wcct.client.view.MapWestPanel;
import org.fao.fenix.web.modules.wcct.client.view.TableCenterPanel;
import org.fao.fenix.web.modules.wcct.client.view.WCCTTool;
import org.fao.fenix.web.modules.wcct.client.view.vo.WCCTTableMD;
import org.fao.fenix.web.modules.wcct.common.services.WCCTServiceEntry;
import org.fao.fenix.web.modules.wcct.common.vo.WCCTVo;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class WCCTController {

	public static Map<String, String> monthLabelMap;

	public static Map<String, String> dekLabelMap;

	static {
		monthLabelMap = new HashMap<String, String>();
		monthLabelMap.put("1", BabelFish.print().january());
		monthLabelMap.put("2", BabelFish.print().february());
		monthLabelMap.put("3", BabelFish.print().march());
		monthLabelMap.put("4", BabelFish.print().april());
		monthLabelMap.put("5", BabelFish.print().may());
		monthLabelMap.put("6", BabelFish.print().june());
		monthLabelMap.put("7", BabelFish.print().july());
		monthLabelMap.put("8", BabelFish.print().august());
		monthLabelMap.put("9", BabelFish.print().september());
		monthLabelMap.put("10", BabelFish.print().october());
		monthLabelMap.put("11", BabelFish.print().november());
		monthLabelMap.put("12", BabelFish.print().december());
	}

	static {
		dekLabelMap = new HashMap<String, String>();
		dekLabelMap.put("1", "Dek1");
		dekLabelMap.put("11", "Dek2");
		dekLabelMap.put("21", "Dek3");
	}

	public static void fillMonthList(final ListBox list) {
		list.addItem("Please select a month...");
		list.addItem(BabelFish.print().january());
		list.addItem(BabelFish.print().february());
		list.addItem(BabelFish.print().march());
		list.addItem(BabelFish.print().april());
		list.addItem(BabelFish.print().may());
		list.addItem(BabelFish.print().june());
		list.addItem(BabelFish.print().july());
		list.addItem(BabelFish.print().august());
		list.addItem(BabelFish.print().september());
		list.addItem(BabelFish.print().october());
		list.addItem(BabelFish.print().november());
		list.addItem(BabelFish.print().december());
	}

	public static void fillDecadeList(final ListBox list) {
		list.addItem("---");
		list.addItem("Dek1");
		list.addItem("Dek2");
		list.addItem("Dek3");
	}

	public static void createMap() {
		WCCTServiceEntry.getInstance().createMap(new AsyncCallback<Boolean>() {
			public void onSuccess(Boolean check) {
				if (check)
					System.out.println("created");
				else
					System.out.println("already created");
			}

			public void onFailure(Throwable caught) {
			}
		});
	}

	public static void getCountries(final ListBox listBox) {
		WCCTServiceEntry.getInstance().getCountries(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> countries) {
				List<String> countryList = new ArrayList<String>();
				for (CodeVo c : countries) {
					countryList.add(c.getLabel());
				}

				Collections.sort(countryList);
				for (String ag : countryList) {
					for (CodeVo c : countries) {
						if (c.getLabel().equals(ag)) {
							listBox.addItem(ag, c.getCode());
							break;
						}
					}
				}
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: Failed retriving codes");
			}
		});
	}

	public static void fillCountryCommodity(final ListBox countriesListBox, final ListBox commoditiesListBox) {
		 /*** retrieve countries ***/
		List<String> countryList = new ArrayList<String>();
		 for(int i=0; i < countriesListBox.getItemCount(); i++) 
			 if ( countriesListBox.isItemSelected(i))
				 countryList.add(countriesListBox.getValue(i)); 
		
				
		 /*** retrieve commodity ***/
		 WCCTServiceEntry.getInstance().getCommoditiesByCountry(countryList, new AsyncCallback<List<CodeVo>>() {
			 public void onSuccess(List<CodeVo> commodities) {
				 List<String> commodityList = new ArrayList<String>();
			 	 commoditiesListBox.clear();
							
				 for(CodeVo c : commodities) {
					 commodityList.add(c.getLabel());
				 }
							
				 Collections.sort(commodityList);
				 for(String commodity : commodityList)
					 for (CodeVo c : commodities) 
							if (c.getLabel().equals(commodity)) {
								commoditiesListBox.addItem(commodity, c.getCode());
								break;
							}
			 }
			 public void onFailure(Throwable caught) {
				 FenixAlert.error("ERROR", "RPC: Failed retriving country codes");
			 }
		});
	}

	public static void getCommodities(final ListBox listBox) {
		WCCTServiceEntry.getInstance().getCommodities(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> commodities) {
				List<String> commodityList = new ArrayList<String>();
				for (CodeVo c : commodities) {
					commodityList.add(c.getLabel());
				}
				Collections.sort(commodityList);
				listBox.addItem("Please select a crop...");
				for (String commodity : commodityList) {
					for (CodeVo c : commodities) {
						if (c.getLabel().equals(commodity)) {
							listBox.addItem(commodity, c.getCode());
							break;
						}
					}
				}
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: Failed retriving commodities codes");
			}
		});
	}

	public static SelectionListener<ButtonEvent> createTable(final WCCTTool wcctTool, final ListBox countries, final ListBox commodities) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().loadingTable(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
				List<String> countryList = new ArrayList<String>();
				List<String> commodityList = new ArrayList<String>();

				for (int i = 0; i < countries.getItemCount(); i++)
					if (countries.isItemSelected(i)) 
						countryList.add(countries.getValue(i));

				for (int i = 0; i < commodities.getItemCount(); i++) 
					if (commodities.isItemSelected(i)) 
						commodityList.add(commodities.getValue(i));


				WCCTServiceEntry.getInstance().fillTable(countryList, commodityList, new AsyncCallback<List<WCCTVo>>() {
					public void onSuccess(List<WCCTVo> resultList) {
			
						if (resultList.isEmpty()) {
							FenixAlert.alert("", "RPC: No codes retrieved");
						} else {
							
							/*** query and fill the grid ***/
							List<WCCTTableMD> md = new ArrayList<WCCTTableMD>();
							wcctTool.getCenter().removeAll();
							TableCenterPanel tableCenterPanel = new TableCenterPanel();
							wcctTool.getCenter().add(tableCenterPanel.build(wcctTool));
							ListStore<WCCTTableMD> listStore = tableCenterPanel.getListStore();
							listStore.removeAll();
							for (WCCTVo result : resultList) {
								md.add(new WCCTTableMD(result.getCountry(), result.getCrop(), result.getFurther(), result.getCropStage(), monthLabelMap.get(result.getStartMonth()), dekLabelMap.get(result.getStartDek()), monthLabelMap.get(result.getEndMonth()), dekLabelMap.get(result.getEndDek())));
							}
							listStore.add(md);
							wcctTool.setTableCenterPanel(tableCenterPanel);
							wcctTool.getCenter().layout();
//							wcctTool.addCenterPartToWindow();
//							wcctTool.getWest().collapse();
//							wcctTool.getWest().expand();
						}
						loadingWindow.destroyLoadingBox();
						
					}

					public void onFailure(Throwable caught) {
						FenixAlert.error("ERROR", "RPC: Failed retriving codes");
						loadingWindow.destroy();
					}
				});
				
			}
		};
	}

	public static SelectionListener<IconButtonEvent> buildExportToSelectionListener(final ListStore<WCCTTableMD> listStore, final String typeExport) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().exportingTable(), BabelFish.print().pleaseWait(),BabelFish.print().exporting() );
				loadingWindow.showLoadingBox();

				List<List<String>> result = new ArrayList<List<String>>();
				List<String> header = new ArrayList<String>();

				/*** header hardcoded ***/
				header.add(BabelFish.print().country());
				header.add(BabelFish.print().crop());
				header.add("Further");
				header.add(BabelFish.print().cropStage());
				header.add(BabelFish.print().startMonth());
				header.add(BabelFish.print().startDek());
				header.add(BabelFish.print().endMonth());
				header.add(BabelFish.print().endDek());

				result.add(header);

				for (int i = 0; i < listStore.getCount(); i++) {
					WCCTTableMD md = listStore.getAt(i);
					List<String> data = new ArrayList<String>();
					data.add(md.getCountry());
					data.add(md.getCrop());
					data.add(md.getFurther());
					data.add(md.getCropStage());
					data.add(md.getStartmonth());
					data.add(md.getStartDek());
					data.add(md.getEndMonth());
					data.add(md.getEndDek());
					result.add(data);
				}

				BirtServiceEntry.getInstance().exportTable(result, typeExport, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						loadingWindow.destroyLoadingBox();
						Window.open(result, "_blank", "status=no");
					}

					public void onFailure(Throwable caught) {
						loadingWindow.destroyLoadingBox();
						FenixAlert.alert("RPC Failed: TableToolbarController:buildExportToSelectionListener", "@exportTable");
					}
				});
			}
		};
	}

	public static void getSeason(final ListBox commoditiesListBox, final ListBox seasonListBox) {
		String commodity = commoditiesListBox.getValue(commoditiesListBox.getSelectedIndex());
		if (commodity != null) {
			WCCTServiceEntry.getInstance().getSeason(commodity, new AsyncCallback<List<CodeVo>>() {
				public void onSuccess(List<CodeVo> seasons) {
					seasonListBox.clear();

					/** returning just one selection **/
					if (seasons.size() <= 1) {
						seasonListBox.addItem(seasons.get(0).getLabel(), seasons.get(0).getCode());
					}

					else {
						List<String> seasonList = new ArrayList<String>();
						for (CodeVo c : seasons) {
							seasonList.add(c.getLabel());
						}

						Collections.sort(seasonList);
						for (String ag : seasonList)
							for (CodeVo c : seasons) {
								if (c.getLabel().equals(ag)) {
									seasonListBox.addItem(ag, c.getCode());
									break;
								}
							}
					}

				}

				public void onFailure(Throwable caught) {
					FenixAlert.error("ERROR", "RPC: Failed retriving season codes");
				}
			});
		} else {
			seasonListBox.clear();
			seasonListBox.addItem("---");
		}
	}

	public static void getOthers(final ListBox commoditiesListBox, final ListBox othersListBox, final VerticalPanel othersPanel) {
		String commodity = commoditiesListBox.getValue(commoditiesListBox.getSelectedIndex());
		if (commodity != null) {
			WCCTServiceEntry.getInstance().getOthers(commodity, new AsyncCallback<List<CodeVo>>() {
				public void onSuccess(List<CodeVo> others) {

					if (others.size() <= 1 && others.get(0).getCode().equals("0")) {
						othersPanel.hide();
					} else {
						othersPanel.show();
						othersListBox.clear();

						List<String> othersList = new ArrayList<String>();
						for (CodeVo c : others) {
							othersList.add(c.getLabel());
						}

						Collections.sort(othersList);
						othersListBox.addItem("There are other dimensions...");
						for (String ag : othersList)
							for (CodeVo c : others) {
								if (c.getLabel().equals(ag)) {
									if (!c.getCode().equals("0")) {
										othersListBox.addItem(ag, c.getCode());
										break;
									}
								}
							}
					}
				}

				public void onFailure(Throwable caught) {
					FenixAlert.error("ERROR", "RPC: Failed retriving others codes");
				}
			});
		} else {
			othersListBox.clear();
			othersListBox.addItem("---");
		}
	}

	public static SelectionListener<ComponentEvent> getMap(final MapWestPanel panel, final WCCTTool wcctTool) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent event) {
				final LoadingWindow loadingWindow = new LoadingWindow("Retrieving Map", BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();

				if (panel.getCommodities().getSelectedIndex() == 0) 
					FenixAlert.alert("Selection", "Please select a crop");
				
				String crop = panel.getCommodities().getValue(panel.getCommodities().getSelectedIndex());

				if (panel.getMonth().getSelectedIndex() == 0)
					FenixAlert.alert("Selection", "Please select a month");
				String month = Integer.toString(panel.getMonth().getSelectedIndex());

				Boolean decade = false;
				String day = new String();
				if (panel.getDecade().getSelectedIndex() != 0) {
					decade = true;
					switch (panel.getDecade().getSelectedIndex()) {
					case 1:
						day = "1";
						break;
					case 2:
						day = "11";
						break;
					case 3:
						day = "21";
						break;
					}

				} else
					day = "1";

				String season = panel.getSeason().getValue(panel.getSeason().getSelectedIndex());

				String others = "0";
				if (panel.getOthers().getSelectedIndex() != 0 && panel.getOthersPanel().isVisible())
					others = panel.getOthers().getValue(panel.getOthers().getSelectedIndex());
				WCCTServiceEntry.getInstance().getMap(decade, month, day, crop, season, others, new AsyncCallback<String>() {
					public void onSuccess(String image) {
						wcctTool.getCenter().removeAll();
						MapCenterPanel mapCenterPanel = new MapCenterPanel();
						mapCenterPanel.getImage().setHTML(image);
						wcctTool.getCenter().add(mapCenterPanel.build(wcctTool));
//						wcctTool.addCenterPartToWindow();
//						wcctTool.getWest().collapse();
//						wcctTool.getWest().expand();
						loadingWindow.destroyLoadingBox();

					}

					public void onFailure(Throwable caught) {
						loadingWindow.destroyLoadingBox();
					}
				});
			}
		};
	}
	
	
	public static SelectionListener<ButtonEvent> getCropCalendar(final ChartWestPanel panel, final WCCTTool wcctTool) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				String selectedCountryCode = panel.getCountries().getValue(panel.getCountries().getSelectedIndex());
				getCropCalendarAction(selectedCountryCode, wcctTool);
			}
		};
	}
	
	public static void getCropCalendarAction(final String selectedCountryCode, final WCCTTool wcctTool) {
//		String selectedCountryCode = panel.getCountries().getValue(panel.getCountries().getSelectedIndex());
//		String selectedCountryLabel = panel.getCountries().getItemText(panel.getCountries().getSelectedIndex());
		String url = "../cropCalendars/"+selectedCountryCode+"_CAL1E.GIF";
		String iFrame = "<div align='center'> <img src='" +url + "'" + "> </div>";
		if ( wcctTool.getCenter() != null)
			wcctTool.getCenter().removeAll();
		ChartCenterPanel chartCenterPanel = wcctTool.getChartCenterPanel();
		HTML content = new HTML(iFrame);
		chartCenterPanel.setImage(content);
		chartCenterPanel.setSelectedCountry(selectedCountryCode);
		wcctTool.getCenter().add(chartCenterPanel.build(wcctTool));
		wcctTool.getCenter().layout();
		wcctTool.setChartCenterPanel(chartCenterPanel);
//		wcctTool.addCenterPartToWindow();
//		wcctTool.getWest().collapse();
//		wcctTool.getWest().expand();
	}
	

	public static SelectionListener<ButtonEvent> exportCalendar(final ChartCenterPanel panel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if ( !panel.getSelectedCountry().isEmpty() )
					Window.open("../cropCalendars/"+ panel.getSelectedCountry()+"_CAL1E.GIF", "_blank", "menubar=no,location=no,resizable=yes,scrollbars=yes,status=no");
			}
		};
	}
	
	public static ChangeListener getCropCalendar(final WCCTTool wcctTool, final ListBox listBox) {
		return new ChangeListener() {
			public void onChange(Widget arg0) {	
				System.out.println("here");
				String selectedCountryCode = listBox.getValue(listBox.getSelectedIndex());
				String selectedCountryLabel = listBox.getItemText(listBox.getSelectedIndex());
				String url = "../cropCalendars/"+selectedCountryCode+"_CAL1E.GIF";
				String iFrame = "<div align='center'> <img src='" +url + "'" + "> </div>";
				wcctTool.getCenter().removeAll();
				ChartCenterPanel chartCenterPanel = new ChartCenterPanel();
				HTML content = new HTML(iFrame);
				chartCenterPanel.setImage(content);
				chartCenterPanel.setSelectedCountry(selectedCountryCode);
				wcctTool.getCenter().add(chartCenterPanel.build(wcctTool));
				wcctTool.getCenter().layout();
				wcctTool.setChartCenterPanel(chartCenterPanel);
//				wcctTool.addCenterPartToWindow();
////				wcctTool.getWest().collapse();
//				wcctTool.getWest().expand();
			}
		};
	}

}
