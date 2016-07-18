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
import org.fao.fenix.web.modules.rainfall.client.view.RainfallTool;
import org.fao.fenix.web.modules.rainfall.client.view.RainfallToolResult;
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
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.tree.Tree;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class RainfallToolController {

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

	public static SelectionListener<ButtonEvent> createListener(final RainfallTool tool) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				if (isValid(tool)) {
					createReport(tool);
				} else {
					FenixAlert.alert(BabelFish.print().information(), "Please select at least one region and fill up all the parameters in the Period panel please.");
				}
			}
		};
	}

	public static void createReport(final RainfallTool tool) {

		final LoadingWindow loading = new LoadingWindow(BabelFish.print().estimatedRainfallReport(), BabelFish.print().sysBusy(), BabelFish.print().pleaseWait());
		// loading.create("Creating report.");
		RainfallParameters p = retrieveParameters(tool);

		try {

			RainfallServiceEntry.getInstance().createRainfallReport(p, new AsyncCallback<String>() {

				public void onSuccess(String report) {

					BirtServiceEntry.getInstance().openReport(report, "frameset", new AsyncCallback<String>() {

						public void onSuccess(String report) {

							loading.destroyLoadingBox();
							RainfallToolResult result = new RainfallToolResult();
							result.setResult(report);
//							System.out.println(report);
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
	public static boolean isValid(RainfallTool tool) {
		boolean r = tool.getReminderPanel().getTree().getRootItem().getItemCount() > 0;
		boolean p1 = tool.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear().getSelectedIndex() > 0;
		boolean p2 = tool.getRainfallTabPanel().getSeasonPeriodPanel().getToYear().getSelectedIndex() > 0;
		if (r && p1 && p2)
			return true;
		return false;
	}

	public static RainfallParameters retrieveParameters(RainfallTool tool) {

		RainfallParameters p = new RainfallParameters();

		p.setXLabel(createXLabel(tool));
		p.setYLabel(createYLabel());
		p.setTitle(createTitle(tool));
		p.setHasAverage(tool.getRainfallTabPanel().getOptionPanel().getShowAverage().getValue());
		p.setHasChart(tool.getRainfallTabPanel().getOptionPanel().getChart().getValue());
		p.setHasTable(tool.getRainfallTabPanel().getOptionPanel().getTable().getValue());
		p.setHasMap(tool.getRainfallTabPanel().getOptionPanel().getMap().getValue());
		p.setHasGeneralClimate(tool.getRainfallTabPanel().getOptionPanel().getGeneralClimate().getValue());
		p.setHasCumulate(tool.getRainfallTabPanel().getOptionPanel().getCumulate().getValue());
		p.setHasNdvi(tool.getRainfallTabPanel().getOptionPanel().getNdvi().getValue());

		p.setYearOneDates(createFirstYearDates(tool));

		if (tool.getRainfallTabPanel().getSeasonPeriodPanel().getCompareYear().getSelectedIndex() > 0)
			p.setYearTwoDates(createSecondYearDates(tool));
		else
			p.setYearTwoDates(new ArrayList<Date>());

		p.setCountry(getCountry(tool));
		p.setRegion(getRegion(tool));
		p.setStations(getStations(tool));

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

	public static String getCountry(RainfallTool tool) {
		Tree tree = tool.getReminderPanel().getTree();
		String gaulCode = "";
		for (TreeItem i : tree.getRootItem().getItems())
			gaulCode = (String) i.getData("gaulCode");
		return gaulCode;
	}

	// TODO Set the correct GAUL0 code
	public static List<String> getRegion(RainfallTool tool) {
		List<String> regions = new ArrayList<String>();
		Tree tree = tool.getReminderPanel().getTree();
		for (TreeItem i : tree.getRootItem().getItems())
			if (((String) i.getData("codingSystem")).equals("GAUL"))
				regions.add((String) i.getData("gaulCode"));
		return regions;
	}

	public static List<String> getStations(RainfallTool tool) {
		List<String> stations = new ArrayList<String>();
		Tree tree = tool.getReminderPanel().getTree();
		for (TreeItem i : tree.getRootItem().getItems())
			if (((String) i.getData("codingSystem")).equals("GTS"))
				stations.add((String) i.getData("gaulCode"));
		return stations;
	}

	// FIXME
	public static Integer getYearOne(RainfallTool tool) {
		// ListBox yearList =
		// tool.getRainfallTabPanel().getSeasonPeriodPanel().getYearOne();
		ListBox yearList = new ListBox();
		String year = yearList.getItemText(yearList.getSelectedIndex());
		return Integer.parseInt(year);
	}

	// FIXME
	public static Integer getYearTwo(RainfallTool tool) {
		// ListBox yearList =
		// tool.getRainfallTabPanel().getSeasonPeriodPanel().getYearTwo();
		ListBox yearList = new ListBox();
		if (yearList.getSelectedIndex() > 0) {
			String year = yearList.getItemText(yearList.getSelectedIndex());
			return Integer.parseInt(year);
		}
		return null;
	}

	// TODO restore title. Is it needed?
	public static String createTitle(RainfallTool tool) {
		return "TITLE";
	}

	public static String createYLabel() {
		return BabelFish.print().estimatedRainfallMm();
	}

	public static String createXLabel(RainfallTool tool) {

		ListBox fromDecade = tool.getRainfallTabPanel().getSeasonPeriodPanel().getFromDecade();
		ListBox toDecade = tool.getRainfallTabPanel().getSeasonPeriodPanel().getToDecade();
		ListBox fromYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear();
		ListBox toYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getToYear();

		String d1 = fromDecade.getItemText(fromDecade.getSelectedIndex());
		String d2 = toDecade.getItemText(toDecade.getSelectedIndex());
		String y1 = fromYear.getItemText(fromYear.getSelectedIndex());
		String y2 = fromYear.getItemText(toYear.getSelectedIndex());

		return BabelFish.print().from() + " " + d1 + " " + y1 + " " + BabelFish.print().to() + " " + d2 + " " + y2;
	}

	public static List<Date> createFirstYearDates(RainfallTool tool) {

		List<Date> dates = new ArrayList<Date>();
		
		ListBox fromDecade = tool.getRainfallTabPanel().getSeasonPeriodPanel().getFromDecade();
		ListBox toDecade = tool.getRainfallTabPanel().getSeasonPeriodPanel().getToDecade();
		ListBox fromYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear();
		ListBox toYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getToYear();

		String d1 = fromDecade.getValue(fromDecade.getSelectedIndex());
		String d2 = toDecade.getValue(toDecade.getSelectedIndex());
		String y1 = fromYear.getItemText(fromYear.getSelectedIndex());
		String y2 = fromYear.getItemText(toYear.getSelectedIndex());

		Integer delta = Integer.valueOf(y2) - Integer.valueOf(y1);
//		System.out.println("RainfallToolController | Delta: " + delta);
		if (delta < 1)
			dates = ControllerUtils.findAllRangeDecades(d1, d2, y1);
		else if (delta >= 1)
			dates = ControllerUtils.findAllRangeDecades(d1, d2, y1, y2);
//		System.out.println(dates.size() + " dates in the range");
		return dates;
	}

	public static List<Date> createSecondYearDates(RainfallTool tool) {

		ListBox fromDecade = tool.getRainfallTabPanel().getSeasonPeriodPanel().getFromDecade();
		ListBox toDecade = tool.getRainfallTabPanel().getSeasonPeriodPanel().getToDecade();
		ListBox fromYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getFromYear();
		ListBox toYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getToYear();
		ListBox compareYear = tool.getRainfallTabPanel().getSeasonPeriodPanel().getCompareYear();

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
	public static void fillCountryList(final RainfallTool tool) {
		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
//				ListBox countryList = tool.getRainfallTabPanel().getTreeRegionPanel().getCountryList();
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

	public static KeyListener oracleListener(final RainfallTool tool) {
		return new KeyListener() {
			public void componentKeyUp(ComponentEvent event) {
				super.componentKeyUp(event);
//				tool.getRainfallTabPanel().getTreeRegionPanel().getStationTree().getRootItem().getItem(0).removeAll();
//				tool.getRainfallTabPanel().getTreeRegionPanel().getStationTree().expandAll();
//				String value = tool.getRainfallTabPanel().getTreeRegionPanel().getOracle().getValue();
//				Tree tree = tool.getRainfallTabPanel().getTreeRegionPanel().getTree();
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

	public static void fillCountryTree(final RainfallTool tool) {
		final LoadingWindow loading = new LoadingWindow(BabelFish.print().estimatedRainfallReport(), BabelFish.print().sysBusy(), BabelFish.print().pleaseWait());
		RainfallServiceEntry.getInstance().findAllGaul0(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
//				Tree tree = tool.getRainfallTabPanel().getTreeRegionPanel().getTree();
				Tree tree = null;
				for (final CodeVo vo : vos) {
					TreeItem i = new TreeItem(vo.getLabel());
					i.setData("gaulCode", vo.getCode());
					i.setData("gaulLevel", "0");
					tree.getRootItem().add(i);
				}
				loading.destroyLoadingBox();
			}

			public void onFailure(Throwable caught) {
				loading.destroyLoadingBox();
				FenixAlert.error(BabelFish.print().info(), caught.getMessage());
			}
		});
	}

	public static Listener<TreeEvent> createRegionLeaves(final RainfallTool tool) {
		return new Listener<TreeEvent>() {
			public void handleEvent(TreeEvent te) {
				final TreeItem item = te.getTree().getSelectedItem();
				if (item.getItemCount() == 0) {
					try {
						String gaulLevel = (String) item.getData("gaulLevel");
						item.setChecked(false);
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
//							final Tree stationTree = tool.getRainfallTabPanel().getTreeRegionPanel().getStationTree();
//							RainfallServiceEntry.getInstance().findAllRainstationFromGaul1(gaul1, new AsyncCallback<List<CodeVo>>() {
//								public void onSuccess(List<CodeVo> vos) {
//									stationTree.getRootItem().getItem(0).removeAll();
//									for (CodeVo vo : vos) {
//										TreeItem station = new TreeItem(vo.getLabel());
//										station.setData("gaulCode", vo.getCode());
//										station.setData("codingSystem", "GTS");
//										station.setData("gaulLevel", "GTS");
//										station.setIconStyle("workspaceTable");
//										//stationTree.getRootItem().add(station)
//										// ;
//										stationTree.getRootItem().getItem(0).add(station);
//										stationTree.getStyle().setLeafIconStyle("workspaceTable");
//										stationTree.expandAll();
//									}
//									stationTree.getRootItem().setExpanded(true);
//								}
//
//								public void onFailure(Throwable caught) {
//									FenixAlert.error(I18N.print().info(), caught.getMessage());
//								}
//							});

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

	public static ChangeListener findAllGaul1FromGaul0(final RainfallTool tool) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
//				ListBox countryList = tool.getRainfallTabPanel().getTreeRegionPanel().getCountryList();
				ListBox countryList = null;
				try {
					String gaul0 = countryList.getValue(countryList.getSelectedIndex());
					RainfallServiceEntry.getInstance().findAllGaul1FromGaul0(gaul0, new AsyncCallback<List<CodeVo>>() {
						public void onSuccess(List<CodeVo> vos) {
//							ListBox regionList = tool.getRainfallTabPanel().getTreeRegionPanel().getRegionList();
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
	public static void fillYearList(final ListBox list) {
		list.addItem(BabelFish.print().pleaseSelect());
		int year = 1900 + (new Date().getYear());
		for (int j = year; j >= (year - 50); j--)
			list.addItem(String.valueOf(j));
	}

	public static SelectionListener<ButtonEvent> addUserSelection(final Tree sourceTree, final Tree stationTree, final Tree tree) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				addUserSelectionController(sourceTree, tree);
//				addUserSelectionController(stationTree, tree);
			}
		};
	}

	private static void addUserSelectionController(final Tree sourceTree, final Tree tree) {

		for (TreeItem item : sourceTree.getChecked()) {

			final String gaulLevel = (String) item.getData("gaulLevel");
			final String codingSystem = (String) item.getData("codingSystem");

			if (!gaulLevel.equals("0")) { 
				TreeItem t = new TreeItem(item.getText());
				t.setData("gaulCode", (String) item.getData("gaulCode"));
				t.setData("codingSystem", (String) item.getData("codingSystem"));
				if (codingSystem.equals("GTS"))
					t.setIconStyle("workspaceTable");
				tree.getRootItem().add(t);
			}
	
//			if (gaulLevel.equals("1") && codingSystem.equals("GAUL")) {
//				final String gaul1 = (String) item.getData("gaulCode");
//				RainfallServiceEntry.getInstance().findAllRainstationFromGaul1(gaul1, new AsyncCallback<List<CodeVo>>() {
//					public void onSuccess(List<CodeVo> vos) {
//						for (CodeVo vo : vos) {
//							TreeItem t = new TreeItem(vo.getLabel());
//							t.setIconStyle("workspaceTable");
//							t.setData("gaulCode", vo.getCode());
//							t.setData("codingSystem", vo.getDescription());
//							System.out.println("\t\tadded " + vo.getLabel() + "["+codingSystem+"]");
//							tree.getRootItem().add(t);
//						}
//					}
//	
//					public void onFailure(Throwable caught) {
//						FenixAlert.error(I18N.print().info(), caught.getMessage());
//					}
//				});
//			}

//			if (gaulLevel.equals("GTS") && codingSystem.equals("GTS")) {
//				final String gaul1 = (String) item.getData("gaulCode");
//				System.out.println("GAUL1 is " + gaul1);
//				RainfallServiceEntry.getInstance().findAllRainstationFromGaul1(gaul1, new AsyncCallback<List<CodeVo>>() {
//					public void onSuccess(List<CodeVo> vos) {
//						System.out.println(vos.size() + " codes found.");
//						for (CodeVo vo : vos) {
//							System.out.println(vo.getLabel() + ", " + vo.getCode() + ", " + vo.getDescription());
//							TreeItem t = new TreeItem(vo.getLabel());
//							t.setIconStyle("workspaceTable");
//							t.setData("gaulCode", vo.getCode());
//							t.setData("codingSystem", vo.getDescription());
//							tree.getRootItem().add(t);
//						}
//					}
//
//					public void onFailure(Throwable caught) {
//						FenixAlert.error(I18N.print().info(), caught.getMessage());
//					}
//				});
//			}
		}

	}

	public static SelectionListener<ComponentEvent> removeSelection(final Tree tree) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				for (TreeItem item : tree.getRootItem().getItems())
					if (item.isChecked())
						tree.getRootItem().remove(item);
			}
		};
	}

	public static SelectionListener<ComponentEvent> removeAll(final Tree tree) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				tree.getRootItem().removeAll();
			}
		};
	}

}