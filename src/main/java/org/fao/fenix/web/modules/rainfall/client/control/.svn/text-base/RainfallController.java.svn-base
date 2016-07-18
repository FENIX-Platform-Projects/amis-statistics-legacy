package org.fao.fenix.web.modules.rainfall.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.rainfall.client.view.RainfallWindow;
import org.fao.fenix.web.modules.rainfall.common.services.RainfallServiceEntry;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallParameters;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreeEvent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class RainfallController {

	public static Map<String, String> monthLabelMap;

	static {
		monthLabelMap = new HashMap<String, String>();
		monthLabelMap.put("01", BabelFish.print().january());
		monthLabelMap.put("02", BabelFish.print().february());
		monthLabelMap.put("03", BabelFish.print().march());
		monthLabelMap.put("04", BabelFish.print().april());
		monthLabelMap.put("05", BabelFish.print().may());
		monthLabelMap.put("06", BabelFish.print().june());
		monthLabelMap.put("07", BabelFish.print().july());
		monthLabelMap.put("08", BabelFish.print().august());
		monthLabelMap.put("09", BabelFish.print().september());
		monthLabelMap.put("10", BabelFish.print().october());
		monthLabelMap.put("11", BabelFish.print().november());
		monthLabelMap.put("12", BabelFish.print().december());
	}

	public static SelectionListener<ButtonEvent> createListener(final RainfallWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				if (isValid(window)) {
					createReport(window);
				}
			}
		};
	}
	
	public static void createReport(final RainfallWindow window) {
		
		final LoadingWindow loading = new LoadingWindow(BabelFish.print().estimatedRainfallReport(), BabelFish.print().sysBusy(), BabelFish.print().pleaseWait());
//		loading.create("Creating report.");
		RainfallParameters p = retrieveParameters(window);

		try {

			RainfallServiceEntry.getInstance().createRainfallReport(p, new AsyncCallback<String>() {
				public void onSuccess(String report) {
					BirtServiceEntry.getInstance().openReport(report, "frameset", new AsyncCallback<String>() {
						public void onSuccess(String report) {
							loading.destroyLoadingBox();
							Window window = new Window();
							window.setHeading(BabelFish.print().estimatedRainfallReport());
							ContentPanel cont = new ContentPanel();
							cont.setHeaderVisible(false);
							cont.setLayout(new FitLayout());
							window.setLayout(new FitLayout());
							window.setSize(600, 500);
							HTML content = new HTML(report);
							cont.add(content);
							window.add(cont);
							window.show();
							// ---------------------
							loading.destroyLoadingBox();
						}

						public void onFailure(Throwable caught) {
							loading.destroyLoadingBox();
							Info.display("ISFPTemplate", caught.getLocalizedMessage());
							loading.destroyLoadingBox();
						}
					});
				}

				public void onFailure(Throwable caught) {
					loading.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().info(), caught.getMessage());
				}
			});

		} catch (FenixGWTException e) {
			loading.destroyLoadingBox();
			FenixAlert.error(BabelFish.print().info(), e.getMessage());
		}
	}

	// FIXME
	public static boolean isValid(RainfallWindow window) {
//		if (window.getRainfallTabPanel().getSeasonPeriodPanel().getDecades().getSelectedIndex() < 0) {
//			FenixAlert.alert(I18N.print().info(), "Please select one or more Dekade(s).");
//			return false;
//		} else if (window.getRainfallTabPanel().getSeasonPeriodPanel().getYearOne().getSelectedIndex() <= 0) {
//			FenixAlert.alert(I18N.print().info(), "Please select Year One.");
//			return false;
//		}
		return true;
	}

	public static RainfallParameters retrieveParameters(RainfallWindow window) {

		RainfallParameters p = new RainfallParameters();
		
		p.setXLabel(createXLabel(window));
		p.setYLabel(createYLabel());
		p.setTitle(createTitle(window));
		p.setHasAverage(window.getRainfallTabPanel().getOptionPanel().getShowAverage().getValue());
		p.setHasChart(window.getRainfallTabPanel().getOptionPanel().getChart().getValue());
		p.setHasTable(window.getRainfallTabPanel().getOptionPanel().getTable().getValue());
		p.setHasMap(window.getRainfallTabPanel().getOptionPanel().getMap().getValue());
		p.setHasCumulate(window.getRainfallTabPanel().getOptionPanel().getCumulate().getValue());
		p.setHasNdvi(window.getRainfallTabPanel().getOptionPanel().getNdvi().getValue());
		
		p.setYearOneDates(createFirstYearDates(window));
		
		if (window.getRainfallTabPanel().getSeasonPeriodPanel().getCompareYear().getSelectedIndex() > 0)
			p.setYearTwoDates(createSecondYearDates(window));
		else
			p.setYearTwoDates(new ArrayList<Date>());
		
		p.setCountry(getCountry(window));
		p.setRegion(getRegion(window));
		p.setStations(getStations(window));
		
//		print(p);
		
		return p;
	}
	
	private static void print(RainfallParameters p) {
		System.out.println("RAINFALL | X Label: " + p.getXLabel());
		System.out.println("RAINFALL | Y Label: " + p.getYLabel());
		System.out.println("RAINFALL | Title: " + p.getTitle());
		System.out.println("RAINFALL | AVG: " + p.hasAverage());
		System.out.println("RAINFALL | Chart: " + p.hasChart());
		System.out.println("RAINFALL | Table: " + p.hasTable());
		System.out.println("RAINFALL | Map: " + p.hasMap());
		System.out.println("RAINFALL | NDVI: " + p.isHasNdvi());
		System.out.println("RAINFALL | Cumulate: " + p.hasCumulate());
		System.out.println("RAINFALL | Country: " + p.getCountry());
		System.out.println("RAINFALL | Region: " + p.getRegion());
		
		for (String s : p.getStations())
			System.out.println("RAINFALL | Station: " + s);
		
		for (Date s : p.getYearOneDates())
			System.out.println("RAINFALL | Year One: " + s);
		
		for (Date s : p.getYearTwoDates())
			System.out.println("RAINFALL | Year Two: " + s);
		
		for (String s : p.getRegions())
			System.out.println("RAINFALL | Region: " + s);
	}
	
	public static String getCountry(RainfallWindow window) {
		Tree tree = window.getReminderPanel().getTree();
		String gaulCode = "";
		for (TreeItem i : tree.getRootItem().getItems())
			gaulCode = (String) i.getData("gaulCode");
		return gaulCode;
	}

	// TODO Set the correct GAUL0 code
	public static List<String> getRegion(RainfallWindow window) {
		List<String> regions = new ArrayList<String>();
		Tree tree = window.getReminderPanel().getTree();
		for (TreeItem i : tree.getRootItem().getItems())  
			if (((String) i.getData("codingSystem")).equals("GAUL")) 
				regions.add((String) i.getData("gaulCode"));
		return regions;
	}

	public static List<String> getStations(RainfallWindow window) {
		List<String> stations = new ArrayList<String>();
		Tree tree = window.getReminderPanel().getTree();
		for (TreeItem i : tree.getRootItem().getItems()) 
			if (((String) i.getData("codingSystem")).equals("GTS"))
				stations.add((String) i.getData("gaulCode"));
		return stations;
	}

	// FIXME
	public static Integer getYearOne(RainfallWindow window) {
		// ListBox yearList =
		// window.getRainfallTabPanel().getSeasonPeriodPanel().getYearOne();
		ListBox yearList = new ListBox();
		String year = yearList.getItemText(yearList.getSelectedIndex());
		return Integer.parseInt(year);
	}

	// FIXME
	public static Integer getYearTwo(RainfallWindow window) {
		// ListBox yearList =
		// window.getRainfallTabPanel().getSeasonPeriodPanel().getYearTwo();
		ListBox yearList = new ListBox();
		if (yearList.getSelectedIndex() > 0) {
			String year = yearList.getItemText(yearList.getSelectedIndex());
			return Integer.parseInt(year);
		}
		return null;
	}

	// TODO restore title. Is it needed?
	public static String createTitle(RainfallWindow window) {
		return "TITLE";
	}

	public static String createYLabel() {
		return BabelFish.print().estimatedRainfallMm();
	}

	public static String createXLabel(RainfallWindow window) {
		
		ListBox fromDecade = window.getRainfallTabPanel().getSeasonPeriodPanel().getFromDecade();
		ListBox toDecade = window.getRainfallTabPanel().getSeasonPeriodPanel().getToDecade();
		ListBox fromYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear();
		ListBox toYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getToYear();

		String d1 = fromDecade.getItemText(fromDecade.getSelectedIndex());
		String d2 = toDecade.getItemText(toDecade.getSelectedIndex());
		String y1 = fromYear.getItemText(fromYear.getSelectedIndex());
		String y2 = fromYear.getItemText(toYear.getSelectedIndex());
		
		return BabelFish.print().from() + " " + d1 + " " + y1 + " " + BabelFish.print().to() + " " + d2 + " " + y2;
	}

	public static List<Date> createFirstYearDates(RainfallWindow window) {

		ListBox fromDecade = window.getRainfallTabPanel().getSeasonPeriodPanel().getFromDecade();
		ListBox toDecade = window.getRainfallTabPanel().getSeasonPeriodPanel().getToDecade();
		ListBox fromYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear();
		ListBox toYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getToYear();

		String d1 = fromDecade.getValue(fromDecade.getSelectedIndex());
		String d2 = toDecade.getValue(toDecade.getSelectedIndex());
		String y1 = fromYear.getItemText(fromYear.getSelectedIndex());
		String y2 = fromYear.getItemText(toYear.getSelectedIndex());
		
		Integer delta = Integer.valueOf(y2) - Integer.valueOf(y1);
		if (delta < 2) 
			return ControllerUtils.findAllRangeDecades(d1, d2, y1);
		
		return ControllerUtils.findAllRangeDecades(d1, d2, y1, y2);
	}

	public static List<Date> createSecondYearDates(RainfallWindow window) {
		
		ListBox fromDecade = window.getRainfallTabPanel().getSeasonPeriodPanel().getFromDecade();
		ListBox toDecade = window.getRainfallTabPanel().getSeasonPeriodPanel().getToDecade();
		ListBox fromYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear();
		ListBox toYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getToYear();
		ListBox compareYear = window.getRainfallTabPanel().getSeasonPeriodPanel().getCompareYear();

		String d1 = fromDecade.getValue(fromDecade.getSelectedIndex());
		String d2 = toDecade.getValue(toDecade.getSelectedIndex());
		String y1 = fromYear.getItemText(fromYear.getSelectedIndex());
		String y2 = fromYear.getItemText(toYear.getSelectedIndex());
		String compare = compareYear.getItemText(compareYear.getSelectedIndex());
		
		Integer delta = Integer.valueOf(y2) - Integer.valueOf(y1);
		if (delta < 2) 
			return ControllerUtils.findAllRangeDecades(d1, d2, compare);

		return ControllerUtils.findAllRangeDecades(d1, d2, compare, String.valueOf(delta + Integer.valueOf(compare)));
	}

	@Deprecated
	public static void fillCountryList(final RainfallWindow window) {
		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
//				ListBox countryList = window.getRainfallTabPanel().getTreeRegionPanel().getCountryList();
				ListBox countryList = null;
				countryList.addItem(BabelFish.print().pleaseSelectCountry(), null);
				for (CodeVo vo : vos)
					countryList.addItem(vo.getLabel(), vo.getCode());
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static KeyListener oracleListener(final RainfallWindow window) {
		return new KeyListener() {
			public void componentKeyUp(ComponentEvent event) {
				super.componentKeyUp(event);
//				String value = window.getRainfallTabPanel().getTreeRegionPanel().getOracle().getValue();
//				Tree tree = window.getRainfallTabPanel().getTreeRegionPanel().getTree();
				String value = null;
				Tree tree = null;
				for (TreeItem i : tree.getRootItem().getItems()) {
					if (value != null && i.getText() != null && !i.getText().toLowerCase().contains(value.toLowerCase()))
						i.hide();
					else
						i.show();
				}
			}

		};
	}

	public static void fillCountryTree(final RainfallWindow window) {
		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
//				Tree tree = window.getRainfallTabPanel().getTreeRegionPanel().getTree();
				Tree tree = null;
				for (final CodeVo vo : vos) {
					TreeItem i = new TreeItem(vo.getLabel());
					i.setData("gaulCode", vo.getCode());
					i.setData("gaulLevel", "0");
					tree.getRootItem().add(i);
				}
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static Listener<TreeEvent> createRegionLeaves(final RainfallWindow window) {
		return new Listener<TreeEvent>() {
			public void handleEvent(TreeEvent te) {
				final TreeItem item = te.getTree().getSelectedItem();
				if (item.getItemCount() == 0) {
					try {
						String gaulLevel = (String) item.getData("gaulLevel");
						if (gaulLevel.equals("0")) {
							String gaul0 = (String) item.getData("gaulCode");
							RainfallServiceEntry.getInstance().findAllGaul1FromGaul0(gaul0, new AsyncCallback<List<CodeVo>>() {
								public void onSuccess(List<CodeVo> vos) {
									for (CodeVo vo : vos) {
										final TreeItem region = new TreeItem(vo.getLabel());
										region.setData("gaulCode", vo.getCode());
										region.setData("codingSystem", "GAUL");
										region.setData("gaulLevel", "1");
										item.add(region);
									}
									item.setExpanded(true);
								}

								public void onFailure(Throwable caught) {
									FenixAlert.error(BabelFish.print().info(), caught.getMessage());
								}
							});
						} else if (gaulLevel.equals("1")) {
							
							// ADD GAUL2
							String gaul1 = (String) item.getData("gaulCode");
							RainfallServiceEntry.getInstance().findAllGaul2FromGaul1(gaul1, new AsyncCallback<List<CodeVo>>() {
								public void onSuccess(List<CodeVo> vos) {
									for (CodeVo vo : vos) {
										final TreeItem province = new TreeItem(vo.getLabel());
										province.setData("gaulCode", vo.getCode());
										province.setData("codingSystem", "GAUL");
										province.setData("gaulLevel", "2");
										item.add(province);
									}
									item.setExpanded(true);
								}

								public void onFailure(Throwable caught) {
									FenixAlert.error(BabelFish.print().info(), caught.getMessage());
								}
							});
							
							// ADD STATIONS
//							final Tree stationTree = window.getRainfallTabPanel().getTreeRegionPanel().getStationTree();
							final Tree stationTree = null;
							RainfallServiceEntry.getInstance().findAllRainstationFromGaul1(gaul1, new AsyncCallback<List<CodeVo>>() {
								public void onSuccess(List<CodeVo> vos) {
									stationTree.removeAll();
									for (CodeVo vo : vos) {
										TreeItem station = new TreeItem(vo.getLabel());
										station.setData("gaulCode", vo.getCode());
										station.setData("codingSystem", "GTS");
										station.setData("gaulLevel", "GTS");
										station.setIconStyle("workspaceTable");
										stationTree.getRootItem().add(station);
									}
									stationTree.getRootItem().setExpanded(true);
								}

								public void onFailure(Throwable caught) {
									FenixAlert.error(BabelFish.print().info(), caught.getMessage());
								}
							});
							
						} 

					} catch (Exception e) {
						FenixAlert.info(BabelFish.print().info(), BabelFish.print().pleaseSelectCountry());
					}
				}
			}
		};
	}

	public static void createStationLeaves(final CodeVo vo, final TreeItem region) {
		RainfallServiceEntry.getInstance().findAllRainstationFromGaul1(vo.getCode(), new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				for (CodeVo vo : vos) {
					TreeItem station = new TreeItem(vo.getLabel());
					station.setData("gaulCode", vo.getCode());
					station.setData("codingSystem", "GTS");
					station.setIconStyle("workspaceTable");
					region.add(station);
				}
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static ChangeListener findAllGaul1FromGaul0(final RainfallWindow window) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
//				ListBox countryList = window.getRainfallTabPanel().getTreeRegionPanel().getCountryList();
				ListBox countryList = null;
				try {
					String gaul0 = countryList.getValue(countryList.getSelectedIndex());
					RainfallServiceEntry.getInstance().findAllGaul1FromGaul0(gaul0, new AsyncCallback<List<CodeVo>>() {
						public void onSuccess(List<CodeVo> vos) {
//							ListBox regionList = window.getRainfallTabPanel().getTreeRegionPanel().getRegionList();
							ListBox regionList = null;
							for (int i = regionList.getItemCount() - 1; i >= 0; i--)
								regionList.removeItem(i);
							if (vos.size() == 0) {
								regionList.addItem(BabelFish.print().noRegionFound(), null);
							} else {
								regionList.addItem(BabelFish.print().pleaseSelectRegion());
								for (CodeVo vo : vos)
									regionList.addItem(vo.getLabel(), vo.getCode());
							}
						}

						public void onFailure(Throwable caught) {
							FenixAlert.error(BabelFish.print().info(), caught.getMessage());
						}
					});
				} catch (Exception e) {
					FenixAlert.info(BabelFish.print().info(), BabelFish.print().pleaseSelectCountry());
				}
			};
		};
	}

	public static void fillDecadeList(final ListBox list) {
		for (int i = 1; i < 13; i++) {
			for (int j = 1; j < 4; j++) {
				String decadeCode = String.valueOf(i);
				if (i > 9) {
					decadeCode = decadeCode + String.valueOf(j);
					list.addItem(monthLabelMap.get(String.valueOf(i)).substring(0, 3) + " d" + String.valueOf(j), decadeCode);
				} else {
					decadeCode = "0" + decadeCode + String.valueOf(j);
					list.addItem(monthLabelMap.get("0" + String.valueOf(i)).substring(0, 3) + " d" + String.valueOf(j), decadeCode);
				}
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static ChangeListener changeYear(final ListBox decadeList) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				ListBox yearList = (ListBox)sender;
				Integer year = Integer.parseInt(yearList.getItemText(yearList.getSelectedIndex()));
				Date today = new Date();
				if ((1900 + today.getYear()) == year) {
					Integer month = 1 + today.getMonth();
					Integer decade = 1;
					if ((today.getDate() > 10) && (today.getDate() < 20))
						decade = 2;
					else if (today.getDate() > 20)
						decade = 3;
					Integer index = (month * decade) - 1;
					for (int i = decadeList.getItemCount() - 1 ; i >= 0 ; i--)
						if (i > index)
							decadeList.removeItem(i);
				} else {
					for (int i = decadeList.getItemCount() - 1 ; i >= 0 ; i--)
						decadeList.removeItem(i);
					fillDecadeList(decadeList);
				}
			}
		};
	}

	@SuppressWarnings("deprecation")
	public static void fillYearList(final ListBox list) {
		list.addItem(BabelFish.print().pleaseSelect());
		int year = 1900 + (new Date().getYear());
		for (int j = year; j >= (year - 50); j--) {
			if (j >= 1974)
				list.addItem(String.valueOf(j));
			else
				break;
		}
	}

	public static Listener<TreeEvent> addUserSelection(final Tree tree) {
		return new Listener<TreeEvent>() {
			public void handleEvent(TreeEvent te) {
				final TreeItem item = te.getTree().getSelectedItem();
				String gaulLevel = (String) item.getData("gaulLevel");
				String codingSystem = (String) item.getData("codingSystem");
				if (item.isChecked()) {

					if (!gaulLevel.equals("0")) {
						TreeItem t = new TreeItem(item.getText());
						t.setData("gaulCode", (String)item.getData("gaulCode"));
						t.setData("codingSystem", (String)item.getData("codingSystem"));
						tree.getRootItem().add(t);
					}

					else if (gaulLevel.equals("1") && codingSystem.equals("GAUL")) {
						final String gaul1 = (String) item.getData("gaulCode");
						RainfallServiceEntry.getInstance().findAllRainstationFromGaul1(gaul1, new AsyncCallback<List<CodeVo>>() {
							public void onSuccess(List<CodeVo> vos) {
								for (CodeVo vo : vos) {
									TreeItem t = new TreeItem(vo.getLabel());
									t.setIconStyle("workspaceTable");
									t.setData("gaulCode", vo.getCode());
									t.setData("codingSystem", vo.getDescription());
									tree.getRootItem().add(t);
								}
							}

							public void onFailure(Throwable caught) {
								FenixAlert.error(BabelFish.print().info(), caught.getMessage());
							}
						});
					}
					
					else if (gaulLevel.equals("1") && codingSystem.equals("GTS")) {
						final String gaul1 = (String) item.getData("gaulCode");
						RainfallServiceEntry.getInstance().findAllRainstationFromGaul1(gaul1, new AsyncCallback<List<CodeVo>>() {
							public void onSuccess(List<CodeVo> vos) {
								for (CodeVo vo : vos) {
									TreeItem t = new TreeItem(vo.getLabel());
									t.setIconStyle("workspaceTable");
									t.setData("gaulCode", vo.getCode());
									t.setData("codingSystem", vo.getDescription());
									tree.getRootItem().add(t);
								}
							}

							public void onFailure(Throwable caught) {
								FenixAlert.error(BabelFish.print().info(), caught.getMessage());
							}
						});
					}
				}

			}
		};
	}

	public static SelectionListener<ButtonEvent> removeSelection(final Tree tree) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				for (TreeItem item : tree.getRootItem().getItems())
					if (item.isChecked())
						tree.getRootItem().remove(item);
			}
		};
	}

	public static SelectionListener<ButtonEvent> removeAll(final Tree tree) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				tree.getRootItem().removeAll();
			}
		};
	}

}