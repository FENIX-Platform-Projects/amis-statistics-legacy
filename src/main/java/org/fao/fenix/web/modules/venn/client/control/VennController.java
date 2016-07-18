package org.fao.fenix.web.modules.venn.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.common.services.MapServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItemTree;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.venn.client.view.CenterToolPanel;
import org.fao.fenix.web.modules.venn.client.view.DimensionPortletPanel;
import org.fao.fenix.web.modules.venn.client.view.SelectionPortalContentChart;
import org.fao.fenix.web.modules.venn.client.view.SelectionPortalContentMap;
import org.fao.fenix.web.modules.venn.client.view.SelectionPortalContentOlap;
import org.fao.fenix.web.modules.venn.client.view.SelectionPortalContentVenn;
import org.fao.fenix.web.modules.venn.client.view.VennPortalPanel;
import org.fao.fenix.web.modules.venn.common.services.VennServiceEntry;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennChartBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennConfigurationBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennCountryBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIframeVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennProjectsBean;
import org.fao.fenix.web.modules.venn.common.vo.VennResultsVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportTableBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportTableValuesBeanVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreeEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Point;
import com.extjs.gxt.ui.client.util.Size;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.tree.TreeItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class VennController {
	

	public static SelectionChangedListener<ListItem> getSOIFA(final ComboBox<ListItem> comboBoxOrganizationType, final ComboBox<ListItem> comboBoxOrganizations, final TreePanel<ListItemTree> treePanel, final TreeStore<ListItemTree> treeStore) {
		return new SelectionChangedListener<ListItem>() {
			public void selectionChanged(SelectionChangedEvent<ListItem> se) {

				String language = CheckLanguage.getLanguage();
				final ListItem valueType = comboBoxOrganizationType.getValue();
				final ListItem value = comboBoxOrganizations.getValue();

				VennServiceEntry.getInstance().findSOIFA(valueType.getValue(), value.getValue(), language, new AsyncCallback<HashMap<String, String>>() {
					public void onSuccess(HashMap<String, String> result) {

						LinkedHashMap<String, String> out = sortByValues(result);
						List<ListItem> values = new ArrayList<ListItem>();

						treeStore.removeAll();
			
						
						for (String key : out.keySet()) {

							ListItemTree treeItem = createTreeItem(key, result.get(key), result.get(key), "0", "0", valueType.getValue(), value.getValue());
							treeStore.add(treeItem, false);



							values.add(new ListItem(key, result.get(key)));
				
							createLvlStrategies(treePanel, treeStore, treeItem);
							
						}
						
						

//						selectAll(treePanel);
					}
		

					public void onFailure(Throwable caught) {
						FenixAlert.error("Error", "Error retrieving organizations", "");
					}
				});

			}
		};
	}

	public static SelectionChangedListener<ListItem> getOrganizations(final ListStore<ListItem> storeOrganizations, final ComboBox<ListItem> comboBoxOrganizations, final ComboBox<ListItem> comboBoxOrganizationsType) {
		return new SelectionChangedListener<ListItem>() {
			public void selectionChanged(SelectionChangedEvent<ListItem> se) {
				String language = CheckLanguage.getLanguage();
				ListItem value = comboBoxOrganizationsType.getValue();

				VennServiceEntry.getInstance().findOrganizations(value.getValue(), language, new AsyncCallback<HashMap<String, String>>() {
					public void onSuccess(HashMap<String, String> result) {
						LinkedHashMap<String, String> out = sortByValues(result);

						List<ListItem> values = new ArrayList<ListItem>();
						for (String key : out.keySet()) {
							values.add(new ListItem(key, result.get(key)));
						}
						storeOrganizations.removeAll();
						storeOrganizations.add(values);
						

						if (!values.isEmpty())
							comboBoxOrganizations.setValue(values.get(0));

						
						storeOrganizations.clearFilters();
					}

					public void onFailure(Throwable caught) {
						FenixAlert.error("Error", "Error retrieving organizations", "");
					}
				});
			}
		};
	}

	/**
	 THIS USED THE DATASET FOR THE HIERARCHY
	 
	public static void initializeOrganizationType(final TreePanel<ListItemTree> treePanel, final ListStore<ListItem> store, final ComboBox<ListItem> comboBox) {
		String language = CheckLanguage.getLanguage();
		
		VennServiceEntry.getInstance().findOrganizationsType(language, new AsyncCallback<HashMap<String, String>>() {
			public void onSuccess(HashMap<String, String> result) {
				LinkedHashMap<String, String> out = sortByValues(result);

				List<ListItem> values = new ArrayList<ListItem>();
				for (String key : out.keySet()) {
					values.add(new ListItem(key, result.get(key)));
				}
				store.removeAll();
				store.add(values);

				if (!values.isEmpty())
					comboBox.setValue(values.get(0));

				
				store.clearFilters();
				
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "Error retrieving organizations", "");
			}
		});
	}**/
	
	public static void initializeOrganizationType(final DimensionPortletPanel dimensionPortlet) {
		String language = CheckLanguage.getLanguage();
		
		VennServiceEntry.getInstance().findOrganizationsType(language, new AsyncCallback<HashMap<String, String>>() {
			public void onSuccess(HashMap<String, String> result) {
				
				ListStore<ListItem> store = dimensionPortlet.getStoreOrganizationsType();
				ComboBox<ListItem> comboBox = dimensionPortlet.getComboOrganizationsType();
				
				LinkedHashMap<String, String> out = sortByValues(result);
	
				final List<ListItem> values = new ArrayList<ListItem>();
				for (String key : out.keySet()) {
					values.add(new ListItem(key, result.get(key)));
				}
				store.removeAll();
				store.add(values);
				
				comboBox.setValue(values.get(0));
	
				store.clearFilters();
				
				
				
				
			}
	
			public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "Error retrieving organizations", "");
			}
		});
	}

	public static void createLvlStrategies(final TreePanel<ListItemTree> treePanel, final TreeStore<ListItemTree> treeStore, final ListItemTree item) {

		int level = Integer.parseInt(item.getLevel());
		final String strategyCode = item.getCode();
		final String organizationTypeCode = item.getOrganizationTypeCode();
		final String organizationCode = item.getOrganizationCode();
		final String fatherCode = item.getFather();
		final String language = CheckLanguage.getLanguage();

		VennServiceEntry.getInstance().findStrategiesLvl1(organizationTypeCode, organizationCode, strategyCode, language, new AsyncCallback<HashMap<String, String>>() {

			@SuppressWarnings("unchecked")
			public void onSuccess(HashMap<String, String> result) {
				LinkedHashMap<String, String> out = sortByValues(result);

				List<ListItem> values = new ArrayList<ListItem>();

				for (String key : out.keySet()) {
					values.add(new ListItem(key, result.get(key)));
					final ListItemTree strategy = createTreeItem(key, result.get(key), result.get(key), "1", strategyCode, organizationTypeCode, organizationCode);

					treeStore.add(item, strategy, false);

					final String strategyCode2 = strategy.getCode();
					final String organizationTypeCode2 = strategy.getOrganizationTypeCode();
					final String organizationCode2 = strategy.getOrganizationCode();
					final String fatherCode2 = strategy.getFather();
					final String language = CheckLanguage.getLanguage();

					VennServiceEntry.getInstance().findStrategiesLvl2(organizationTypeCode2, organizationCode2, fatherCode2, strategyCode2, language, new AsyncCallback<HashMap<String, String>>() {

						public void onSuccess(HashMap<String, String> result) {

							LinkedHashMap<String, String> out = sortByValues(result);

							List<ListItemTree> treeItems = new ArrayList<ListItemTree>();
							List<ListItem> values = new ArrayList<ListItem>();
							for (String key : out.keySet()) {
								values.add(new ListItem(key, result.get(key)));
								// cut the label
								final ListItemTree strategy2 = createTreeItem(key, result.get(key), result.get(key), "2", strategyCode, organizationTypeCode, organizationCode);
								treeItems.add(strategy2);
								
							}
							treeStore.add(strategy, treeItems, false);
							
//							selectAll(treePanel);
							
			
						}

						public void onFailure(Throwable caught) {

						}
					});

				}
			}

			public void onFailure(Throwable caught) {

			}
		});

	}

	public static Listener<TreeEvent> createStrategies() {
		return new Listener<TreeEvent>() {
			public void handleEvent(TreeEvent te) {
				final TreeItem item = te.getTree().getSelectedItem();
				if (item.getItemCount() == 0) {
					int level = Integer.parseInt((String) item.getData("level"));
					String strategyCode = (String) item.getData("strategyCode");
					String organizationTypeCode = item.getData("organizationTypeCode");
					String organizationCode = item.getData("organizationCode");
					String fatherCode = item.getData("codeFather");
					String language = CheckLanguage.getLanguage();

					if (level == 0) {
						addStrategyLvl1(item, strategyCode, organizationTypeCode, organizationCode, language);
					} else if (level == 1) {
						addStrategyLvl2(item, strategyCode, fatherCode, organizationTypeCode, organizationCode, language);
					}
				}
			}
		};
	}

	private static void addStrategyLvl1(final TreeItem strategyItem, final String strategyCode, final String organizationTypeCode, final String organizationCode, final String language) {
		//		
		// VennServiceEntry.getInstance().findStrategiesLvl1(organizationTypeCode,
		// organizationCode, strategyCode, language, new
		// AsyncCallback<HashMap<String,String>>() {
		//			
		// public void onSuccess(HashMap<String, String> result) {
		// LinkedHashMap<String, String> out = sortByValues(result);
		//
		// List<ListItem> values = new ArrayList<ListItem>();
		// for (String key : out.keySet()) {
		// values.add(new ListItem(key, result.get(key)));
		// final TreeItem strategy = createTreeItem(key, result.get(key),
		// result.get(key), "1", strategyCode, organizationTypeCode,
		// organizationCode);
		// strategy.setIconStyle("geoIcon");
		// strategy.setChecked(strategyItem.isChecked());
		// strategyItem.add(strategy);
		// }
		//
		// strategyItem.setExpanded(true);
		// }
		//			
		// public void onFailure(Throwable caught) {
		//			
		// }
		// });
	}

	private static void addStrategyLvl2(final TreeItem strategyItem, final String strategyCode, final String soifa, final String organizationTypeCode, final String organizationCode, final String language) {
		//		
		// VennServiceEntry.getInstance().findStrategiesLvl2(organizationTypeCode,
		// organizationCode, soifa, strategyCode, language, new
		// AsyncCallback<HashMap<String,String>>() {
		//			
		// public void onSuccess(HashMap<String, String> result) {
		// LinkedHashMap<String, String> out = sortByValues(result);
		//
		// List<ListItem> values = new ArrayList<ListItem>();
		// for (String key : out.keySet()) {
		// values.add(new ListItem(key, result.get(key)));
		// // cutt the label
		// final TreeItem strategy = createTreeItem(key, result.get(key),
		// result.get(key), "2", strategyCode, organizationTypeCode,
		// organizationCode);
		// strategy.setIconStyle("geoIcon");
		// strategy.setChecked(strategyItem.isChecked());
		// strategyItem.add(strategy);
		// }
		//
		// strategyItem.setExpanded(true);
		// }
		//			
		// public void onFailure(Throwable caught) {
		//			
		// }
		// });
	}

	private static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = 0; i < size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}

	private static ListItemTree createTreeItem(String code, String fullLabel, String reducedLabel, String level, String codeFather, String organizationTypeCode, String organizationCode) {
		if ( organizationCode.toLowerCase().equals("fao") && !level.equals("0"))
			return faoListItems(code, fullLabel, reducedLabel, level, codeFather, organizationTypeCode, organizationCode);
		
		if (reducedLabel.length() > 25)
			reducedLabel = reducedLabel.substring(0, 24) + "...";

		ListItemTree item = new ListItemTree(code, fullLabel);
		item.setFather(codeFather);
		item.setShortLabel(reducedLabel);
		item.setLevel(level);
		item.setOrganizationTypeCode(organizationTypeCode);
		item.setOrganizationCode(organizationCode);

		return item;
	}
	
	private static ListItemTree faoListItems(String code, String fullLabel, String reducedLabel, String level, String codeFather, String organizationTypeCode, String organizationCode) {
//		System.out.println("code: " + code + " | " + level);
		
		if (reducedLabel.length() > 25)
			reducedLabel = reducedLabel.substring(0, 24) + "...";
		
		String stringCode = new String(code);
		if ( level.equals("2")) {
			stringCode = code.substring(code.indexOf("_") + 1 , code.length());
		}
		
		ListItemTree item = new ListItemTree(code, stringCode + " - " + fullLabel);
		item.setFather(codeFather);
		item.setShortLabel(reducedLabel);
		item.setLevel(level);
		item.setOrganizationTypeCode(organizationTypeCode);
		item.setOrganizationCode(organizationCode);
		
		return item;
	}

	public static Listener<TreeEvent> showWindow(final Window window) {
		return new Listener<TreeEvent>() {
			public void handleEvent(TreeEvent te) {
				final TreeItem item = te.getTree().getSelectedItem();
				window.removeAll();
				window.setHeading("Full Label");
				String fullLabel = item.getData("fullLabel");
				HTML html = new HTML(fullLabel);
				window.add(html);
				window.setScrollMode(Scroll.AUTO);
				int width = 500;
				int height = 120;
				window.setSize(width, height);
				Point point = getPosition(width, height);
				window.setPosition(point.x, point.y);
				window.show();
			}
		};
	}

	public static Listener<TreeEvent> hideWindow(final Window window) {
		return new Listener<TreeEvent>() {
			public void handleEvent(TreeEvent te) {
				final TreeItem item = te.getTree().getSelectedItem();
				window.close();

			}
		};
	}

	private static Point getPosition(int width, int height) {
		Size s = XDOM.getViewportSize();
//		System.out.println("XDOM.getViewportSize(): " + XDOM.getViewportSize());
		// System.out.println("XDOM.getBodyScrollLeft(): " +
		// XDOM.getBodyScrollLeft());
		// System.out.println("XDOM.getBodyScrollTop(): " +
		// XDOM.getBodyScrollTop());
		int left = s.width - width - 10 - XDOM.getBodyScrollLeft();
		int top = s.height - height - 10 - XDOM.getBodyScrollTop();
		return new Point(left, top);
	}
	
	public static void getChart(final VennBeanVO vennBean, final SelectionPortalContentChart vennPortal, final VennPortalPanel vennPortalPanel) {

		vennPortal.getCentralTabPanel().removeAll();
		vennPortal.getPortletTabPanel().removeAll();
		
		// remove all chart from venn
		LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> charts = vennPortalPanel.getVennChartsBeanVO();
		charts.clear();
		
		createChart(charts, vennBean, vennPortal, "totalbycategories", "By Categories");
		
		createBackGroundChart(charts, vennBean, vennPortal, "globalbycategories", "Global By Categories");

	}
	
	

	public static void getOldChart(final VennBeanVO vennBean, final SelectionPortalContentChart vennPortal, final VennPortalPanel vennPortalPanel) {

		vennPortal.getCentralTabPanel().removeAll();
		vennPortal.getPortletTabPanel().removeAll();
		
		// remove all chart from venn
		LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> charts = vennPortalPanel.getVennChartsBeanVO();
		charts.clear();

//		createChart(charts, vennBean, vennPortal, "byrecipient", "By Implementing Agency");
		
		
		createChart(charts, vennBean, vennPortal, "frequencies", "By Common Priorities");
		createChart(charts, vennBean, vennPortal, "bydonors", "By Donors");
		createChart(charts, vennBean, vennPortal, "bycategories", "By Categories");

//		createChart(charts, vennBean, vennPortal, "byfunding", "By Funding Modalities/Source");
		
	}
	
	private static void createBackGroundChart(final LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> charts, final VennBeanVO vennBean, final SelectionPortalContentChart vennPortal, final String chartType, final String title) {
		VennServiceEntry.getInstance().getChart(vennBean, "preview", chartType, new AsyncCallback<LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>>>() {
			public void onSuccess(LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> results) {
				charts.putAll(results);
			}

			public void onFailure(Throwable caught) {

			}

		});
	}
	
	
	private static void createChart(final LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> charts, final VennBeanVO vennBean, final SelectionPortalContentChart vennPortal, final String chartType, final String title) {
		VennServiceEntry.getInstance().getChart(vennBean, "preview", chartType, new AsyncCallback<LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>>>() {
			public void onSuccess(LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> results) {
				
				charts.putAll(results);
				System.out.println("CHART SUBSET: "+results);
				
				for(String key : results.keySet()) {
					TabItem itemCentral = new TabItem();			
					itemCentral.setText(title);
					itemCentral.setHeight(660);
					itemCentral.setScrollMode(Scroll.AUTO);
					VerticalPanel centralVerticalPanel = new VerticalPanel();
//					centralVerticalPanel.setScrollMode(Scroll.ALWAYS);
					
					TabItem itemPortlet = new TabItem();
					itemPortlet.setText(title);
					itemPortlet.setHeight(260);
					itemPortlet.setScrollMode(Scroll.AUTO);
					
					if ( title.equals("byfunding")) {
						itemCentral.setText(title + " ");
						itemPortlet.setText(title + " ");
					}
					
//					for (String chartKeys : results.get(key).keySet()) {
						if (results.get(key).size() > 1 ) {
							System.out.println("addCharts((results.size() > 1 ");
							addCharts(vennPortal, centralVerticalPanel, itemPortlet, results.get(key), "");
						}
						else {
							System.out.println("addChart( == 1");
							addChart(vennPortal, centralVerticalPanel, itemPortlet, results.get(key));
						}
//					}
						
					
					
					itemCentral.add(centralVerticalPanel);
					
					vennPortal.getCentralTabPanel().add(itemCentral);
					vennPortal.getPortletTabPanel().add(itemPortlet);
					
					vennPortal.getContentPanel().layout();
					vennPortal.getPortlet().layout();
					
				}
				
				
			}

			public void onFailure(Throwable caught) {

			}

		});
	}

	private static void addChart(final SelectionPortalContentChart vennPortal, final VerticalPanel centralVerticalPanel, final TabItem itemPortlet, LinkedHashMap<String, VennChartBeanVO> result )  {
		for(String key : result.keySet()) { 
			centralVerticalPanel.add(new HTML(result.get(key).getIframeCentralPanel()));
			vennPortal.getContentPanel().layout();
	
			itemPortlet.add(new HTML(result.get(key).getIframePortlet()));
			vennPortal.getPortlet().layout();
		}
	}
	
	private static void addCharts(final SelectionPortalContentChart vennPortal, final VerticalPanel centralVerticalPanel, final TabItem itemPortlet, final LinkedHashMap<String, VennChartBeanVO> result, final String linksTitle)  {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		
		VerticalPanel linksPanel = new VerticalPanel();
		linksPanel.setSpacing(5);
		linksPanel.setWidth(130);
		linksPanel.setHeight(550);
		linksPanel.setScrollMode(Scroll.AUTO);
		linksPanel.setBorders(true);
		
		VerticalPanel chartPanel = new VerticalPanel();
//		chartPanel.setWidth(880);
//		chartPanel.setHeight(550);
//		chartPanel.setScrollMode(Scroll.AUTO);
//		chartPanel.setBorders(true);
		
		panel.add(linksPanel);
		panel.add(chartPanel);
		
		int i=0;
		for(String key : result.keySet()) { 
			System.out.println("KEY: " + key + " | " );
			if ( i == 0 )
				changeChart(chartPanel, new HTML(result.get(key).getIframeCentralPanel()));
		
			linksPanel.add(addLink(chartPanel, key, new HTML(result.get(key).getIframeCentralPanel())));
			
			vennPortal.getContentPanel().layout();
	
			itemPortlet.add(new HTML(result.get(key).getIframePortlet()));
			vennPortal.getPortlet().layout();
			i++;
		}
		
		centralVerticalPanel.add(panel);
	}
	
	private static Hyperlink addLink(final VerticalPanel chartPanel, final String text, final HTML iframe) {
		Hyperlink link = new Hyperlink();
		link.setHTML("<div style='font-size:7pt;'><u>" + text + "</u></div>");
		

		link.addClickHandler(VennController.selectChart(chartPanel, iframe));
		return link;
	}
	
	private static ClickHandler selectChart(final VerticalPanel chartPanel, final HTML iframe) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				changeChart(chartPanel, iframe);
			}
		};
	}
	
	private static void changeChart(final VerticalPanel chartPanel, final HTML iframe) {
		chartPanel.removeAll();
		chartPanel.add(iframe);
		chartPanel.layout();
	}	
	
	public static void getVenn(final VennBeanVO vennBean, final SelectionPortalContentVenn vennPortal) {
		
//		MapServiceEntry.getInstance().getLayersByCode(
		

		VennServiceEntry.getInstance().createVenn(vennBean, "aggregationDAC", new AsyncCallback<List<VennIframeVO>>() {
			public void onSuccess(List<VennIframeVO> results) {
				
				/** TODO: if there are more venn should be created a tab for each venn **/
				for(VennIframeVO result : results) {
					String urlCP = "../venn/temp/" + result.getIframeCentralPanel();
					String iFrameCP = "<div align='center'> Priorities Matching </div>" + "<div align='center'> <img src='" + urlCP + "'" + "> </div>";
					vennPortal.getCentralHtml().setHTML(iFrameCP);
					vennPortal.getContentPanel().layout();
	
					String urlP = "../venn/temp/" + result.getIframePortlet();
					String iFrameP = "<div align='center'> Priorities Matching </div>" + "<div align='center'> <img src='" + urlP + "'" + "> </div>";
					vennPortal.getPortletHtml().setHTML(iFrameP);
	
//					getDacProjects(vennPortal, vennBean);
	
					vennPortal.getPortletInfoPanel().show();
					vennPortal.getPortlet().layout();
					
					System.out.println("result.getIframeCentralPanel():" + result.getIframeCentralPanel() +"|");
					
//					/** Store the venn image path for the report **/
//					vennBean.getVennGraphBeanVO().getVennImages().put("normal", result.getIframeCentralPanel());
					vennBean.getVennGraphBeanVO().getVennImages().put("normal", result.getIframePortlet());
					
					
//					vennBean.getVennGraphBeanVO().getShowLegend().put("normal", true);
				}
				
				
				
				
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "TableController @ getFilters");
			}
		});
	}
	
	public static void getMap(final VennBeanVO vennBean, final SelectionPortalContentMap vennPortal) {
		// make layer dynamic
		VennServiceEntry.getInstance().createVennMap(vennBean, "layer_gaul1", "Total amount of projects per province", 6, "RED", "GREEN", true, new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> mapPaths) {

				for (int i = vennPortal.getPanelCentral().getItemCount() - 1; i >= 0; i--)
					vennPortal.getPanelCentral().remove(vennPortal.getPanelCentral().getWidget(i));
				
				for (int i = vennPortal.getPanelPortlet().getItemCount() - 1; i >= 0; i--)
					vennPortal.getPanelPortlet().remove(vennPortal.getPanelPortlet().getWidget(i));
				
				for (String s : mapPaths) {
					Frame frameCenter = new Frame("../venn/temp/" + s);
					Frame framePortlet = new Frame("../venn/temp/" + s);
					vennPortal.getPanelCentral().add(frameCenter);
					vennPortal.getPanelPortlet().add(framePortlet);
					frameCenter.setSize("800px", "900px");
					framePortlet.setSize("800px", "900px");
				}
				
				vennPortal.getContentPanel().layout();
				vennPortal.getPortlet().layout();
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "error creating map");
			}
		});
	}

	public static SelectionListener<ButtonEvent> applyButton(final VennPortalPanel vennPortal, final TreePanel<ListItemTree> aTree, final TreePanel<ListItemTree> bTree, final TreePanel<ListItemTree> cTree, final CenterToolPanel centerPanel, final String language) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
//				HashMap<String, String> aCodes = getCodes(aTree);
//				HashMap<String, String> bCodes = getCodes(bTree);
//				HashMap<String, String> cCodes = getCodes(cTree);
				
				/*** TEEEESTTT **/
				HashMap<String, String> setA = getCodes(aTree);
				HashMap<String, String> setB = getCodes(bTree);
				HashMap<String, String> setC = getCodes(bTree);
				setA.put("175", "Nepal");
				setB.put("FAO", "FAO");
				setC.put("filter_all", "filter_all");
				
				HashMap<String, String> cCodes = new HashMap<String, String>();
				cCodes.put("filter_all", "filter_all");
				
				getIntersectionInfo(vennPortal, setA, setB, setC, language);
//				createStandardReport(vennPortal, aCodes, bCodes, cCodes, centerPanel, language, vennPortal.getCountryCodes(), "", "", "All Others");
//				apply(vennPortal, aCodes, bCodes, cCodes, centerPanel, language, vennPortal.getCountryCodes(), "", "", "");
			}
		};
	}
	
	
	private static void createStandardReport(final VennPortalPanel vennPortal, final HashMap<String, String> aCodes, final HashMap<String, String> bCodes, HashMap<String, String> cCodes, final CenterToolPanel centerPanel, final String language, final List<String> countryCodes, final String aStaticLabel, final String bStaticLabel, final String cStaticLabel) {

		VennServiceEntry.getInstance().getDACCodes(aCodes, bCodes, cCodes, language, new AsyncCallback<VennResultsVO>() {
			public void onSuccess(final VennResultsVO results) {
				System.out.println("createStandardReport HERE");


				VennServiceEntry.getInstance().calculateIntersections(results, countryCodes, language, new AsyncCallback<VennBeanVO>() {
					public void onSuccess(VennBeanVO bean) {
						System.out.println("createStandardReport HERE");
						
						String aLabel = aStaticLabel;
						String bLabel = bStaticLabel;
						String cLabel = cStaticLabel;
						if ( !results.getA_dacCodes().isEmpty() && aLabel.equals(""))
							aLabel = vennPortal.getControllerPortalPanel().getxPortlet().getComboOrganizations().getValue().getName();
						
						if ( !results.getB_dacCodes().isEmpty() && bLabel.equals(""))
							bLabel = vennPortal.getControllerPortalPanel().getyPortlet().getComboOrganizations().getValue().getName();
						
						if ( !results.getC_dacCodes().isEmpty() && cLabel.equals(""))
							cLabel = (vennPortal.getControllerPortalPanel().getwPortlet().getComboOrganizations().getValue().getName());
							

						bean.getVennGraphBeanVO().setDacCodes(results.getMergedDacCodes());
						bean.getVennGraphBeanVO().setSo_dacCodes(results.getSo_dacCorrispondence());
						bean.getVennGraphBeanVO().getA().setLabel(aLabel);
						bean.getVennGraphBeanVO().getB().setLabel(bLabel);
						bean.getVennGraphBeanVO().getC().setLabel(cLabel);
						bean.getVennGraphBeanVO().getAb().setLabel(bean.getVennGraphBeanVO().getA().getLabel() + " / " + bean.getVennGraphBeanVO().getB().getLabel());
						bean.getVennGraphBeanVO().getBc().setLabel(bean.getVennGraphBeanVO().getB().getLabel() + " / " + bean.getVennGraphBeanVO().getC().getLabel());
						bean.getVennGraphBeanVO().getAc().setLabel(bean.getVennGraphBeanVO().getA().getLabel() + " / " + bean.getVennGraphBeanVO().getC().getLabel());
						bean.getVennGraphBeanVO().getAbc().setLabel(bean.getVennGraphBeanVO().getA().getLabel() + " / " + bean.getVennGraphBeanVO().getB().getLabel() + " / " + bean.getVennGraphBeanVO().getC().getLabel());
						
						
						HashMap<String, String> countries = new HashMap<String, String>();
						for(String country : vennPortal.getCountryCodes()) {
							countries.put(country, country);
						} 
						
						bean.setCountries(countries);
						
						System.out.println("countries: " + countries );
			
						/** chart **/
						getChart(bean, centerPanel.getSelectionPortal().getChartContent(), vennPortal);

						/** venn **/
						getVenn(bean, centerPanel.getSelectionPortal().getVennContent());

						/** tables **/
//						getProjects(bean, centerPanel.getSelectionPortal().getOlapContent());
						
						/** map **/
//						getMap(bean, centerPanel.getSelectionPortal().getMapContent());

						// set bean
//						vennPortal.setVennBean(bean);
////						loadingWindow.destroyLoadingBox();
//						
//						
//						
//						// setting the Report Bean.
//						//venn 
						vennPortal.getVennReportBean().getVennGraphReportBean().setVennGraphBeanVO(bean.getVennGraphBeanVO());					
//						// tables
						vennPortal.getVennReportBean().setVennCountryBeanVO(bean.getVennCountryBeanVO());
//						// charts
						vennPortal.getVennReportBean().setVennChartsBeanVO(vennPortal.getVennChartsBeanVO());
//						
//						// setting the venn report table
//						setVennSummaryReportTables(bean, vennPortal);
						
//						exportStandardReportMethod(vennPortal);
						
					}

					public void onFailure(Throwable caught) {
//						loadingWindow.destroyLoadingBox();
					}

				});
			}

			public void onFailure(Throwable caught) {

			}

		});

	}
	
	
	private static void apply(final VennPortalPanel vennPortal, final HashMap<String, String> aCodes, final HashMap<String, String> bCodes, HashMap<String, String> cCodes, final CenterToolPanel centerPanel, final String language, final List<String> countryCodes, final String aStaticLabel, final String bStaticLabel, final String cStaticLabel) {
		final LoadingWindow loadingWindow = new LoadingWindow("Loading Data", BabelFish.print().pleaseWait(), BabelFish.print().loading());
		loadingWindow.showLoadingBox();
		
		System.out.println("country codes: " + countryCodes); 
		
		System.out.println("aCodes codes: " + aCodes); 
		
		System.out.println("bCodes codes: " + bCodes); 
		
		System.out.println("cCodes codes: " + cCodes); 

//		VennServiceEntry.getInstance().getDACCodes(aCodes, bCodes, cCodes, language, new AsyncCallback<VennResultsVO>() {
//			public void onSuccess(final VennResultsVO results) {
			
				
				final VennResultsVO results = new VennResultsVO();
				
				results.setA_dacCodes(aCodes);
				results.setB_dacCodes(bCodes);
				results.setC_dacCodes(cCodes);
//				results.setMergedDacCodes(aCodes);
//				results.getMergedDacCodes().putAll(bCodes);
//				results.getMergedDacCodes().putAll(cCodes);
				results.setAggregationLevel(0);
		
				
				System.out.println("results.getA_dacCodes(): " + results.getA_dacCodes());
				System.out.println("results.getB_dacCodes(): " + results.getB_dacCodes());
				System.out.println("results.getC_dacCodes(): " + results.getC_dacCodes());
				System.out.println("results.getMergedDacCodes(): " + results.getMergedDacCodes());
				

				VennServiceEntry.getInstance().calculateIntersections(results, countryCodes, language, new AsyncCallback<VennBeanVO>() {
					public void onSuccess(VennBeanVO bean) {
												
						String aLabel = aStaticLabel;
						String bLabel = bStaticLabel;
						String cLabel = cStaticLabel;
						if ( !results.getA_dacCodes().isEmpty() && aLabel.equals(""))
							aLabel = vennPortal.getControllerPortalPanel().getxPortlet().getComboOrganizations().getValue().getName();
						
						if ( !results.getB_dacCodes().isEmpty() && bLabel.equals(""))
							bLabel = vennPortal.getControllerPortalPanel().getyPortlet().getComboOrganizations().getValue().getName();
						
						if ( !results.getC_dacCodes().isEmpty() && cLabel.equals(""))
							cLabel = (vennPortal.getControllerPortalPanel().getwPortlet().getComboOrganizations().getValue().getName());
							
						
						
						
						bean.getVennGraphBeanVO().setDacCodes(results.getMergedDacCodes());
						bean.getVennGraphBeanVO().setSo_dacCodes(results.getSo_dacCorrispondence());
						bean.getVennGraphBeanVO().getA().setLabel(aLabel);
						bean.getVennGraphBeanVO().getB().setLabel(bLabel);
						bean.getVennGraphBeanVO().getC().setLabel(cLabel);
						bean.getVennGraphBeanVO().getAb().setLabel(bean.getVennGraphBeanVO().getA().getLabel() + " / " + bean.getVennGraphBeanVO().getB().getLabel());
						bean.getVennGraphBeanVO().getBc().setLabel(bean.getVennGraphBeanVO().getB().getLabel() + " / " + bean.getVennGraphBeanVO().getC().getLabel());
						bean.getVennGraphBeanVO().getAc().setLabel(bean.getVennGraphBeanVO().getA().getLabel() + " / " + bean.getVennGraphBeanVO().getC().getLabel());
						bean.getVennGraphBeanVO().getAbc().setLabel(bean.getVennGraphBeanVO().getA().getLabel() + " / " + bean.getVennGraphBeanVO().getB().getLabel() + " / " + bean.getVennGraphBeanVO().getC().getLabel());
						
						
						/** chart **/
						getChart(bean, centerPanel.getSelectionPortal().getChartContent(), vennPortal);
//						getOldChart(bean, centerPanel.getSelectionPortal().getChartContent(), vennPortal);

						/** venn **/
						getVenn(bean, centerPanel.getSelectionPortal().getVennContent());

						/** tables **/
//						getProjects(bean, centerPanel.getSelectionPortal().getOlapContent());
						
						/** map **/
//						getMap(bean, centerPanel.getSelectionPortal().getMapContent());

						// set bean
						vennPortal.setVennBean(bean);
						loadingWindow.destroyLoadingBox();
						
						
						
						// setting the Report Bean.
						//venn 
						vennPortal.getVennReportBean().getVennGraphReportBean().setVennGraphBeanVO(bean.getVennGraphBeanVO());					
						// tables
						vennPortal.getVennReportBean().setVennCountryBeanVO(bean.getVennCountryBeanVO());
						// charts
						vennPortal.getVennReportBean().setVennChartsBeanVO(vennPortal.getVennChartsBeanVO());
						
						// setting the venn report table
//						setVennSummaryReportTables(bean, vennPortal);
						
					}

					public void onFailure(Throwable caught) {
						loadingWindow.destroyLoadingBox();
					}

				});
//			}
//			public void onFailure(Throwable caught) {
//
//			}
//
//		});

	}
	
	public static void setVennSummaryReportTables(final VennBeanVO vennBean, final VennPortalPanel vennPortal) {
		
		System.out.println("setVennSummaryReportTables");
		
		VennServiceEntry.getInstance().createSummaryReportTables(vennBean, new AsyncCallback<LinkedHashMap<String, VennReportTableValuesBeanVO>>() {
			public void onSuccess(LinkedHashMap<String, VennReportTableValuesBeanVO> result) {
				vennPortal.getVennReportBean().setVennTablesReportBean(result);	
			}

			public void onFailure(Throwable caught) {
			
			}

		});
	}
	
	
	public static SelectionListener<MenuEvent> byCategories(final HashMap<String, String> dacCodes, final String vennLabel, final VennPortalPanel vennPortal,final CenterToolPanel centerPanel) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
			
				final List<String> countryCodes = vennPortal.getCountryCodes();
				
				System.out.println("by categories: " + countryCodes);

				
				HashMap<String, String> bCodes = new HashMap<String, String>();
				HashMap<String, String> cCodes = new HashMap<String, String>();
				final String language = CheckLanguage.getLanguage();
				
				/** TODO: Use apply function **/
				apply(vennPortal, dacCodes, bCodes, cCodes, centerPanel, language, countryCodes, vennLabel, "", "");

			}
		};
	}

	@SuppressWarnings("deprecation")
	private static HashMap<String, String> getCodes(TreePanel<ListItemTree> tree) {
		HashMap<String, String> codes = new HashMap<String, String>();

//		System.out.println("getCodes: ");

		if (tree == null)
			System.out.println("tree is null");

		TreeStore<ListItemTree> treeStore = tree.getStore();
//		System.out.println("getSize1: " + treeStore.getAllItems().size());
		List<ListItemTree> items = tree.getCheckedSelection();
//		System.out.println("getSize2: " + items.size());
		for (ListItemTree itemSelected : items) {
			if (tree.isLeaf(itemSelected)) {
//				System.out.println("item selected: " + itemSelected.getLabel());
				String code = itemSelected.getCode();
				String label = itemSelected.getLabel();
//				System.out.println("-----> code: " + code + " | " + "label: " + label);
				codes.put(code, label);
			}
		}

		return codes;
	}

	private static void getProjects(VennBeanVO vennBean, SelectionPortalContentOlap olapPanel) {
		
		olapPanel.getCentralTabPanel().removeAll();
		olapPanel.getPortletTabPanel().removeAll();
		
		for ( String key : vennBean.getVennCountryBeanVO().keySet() ){
			VennCountryBeanVO countryBean = vennBean.getVennCountryBeanVO().get(key);
			List<VennProjectsBean> intersactions = countryBean.getAllIntersections();
			

			// projects intersections
//			List<TabItem> projectsTabs = VennGridUtils.createIntersectionProjects(intersactions, vennBean.getVennGraphBeanVO());
			List<TabItem> projectsSummaryTabs = VennGridUtils.createSummaryProjectsTab(intersactions, vennBean.getVennGraphBeanVO(), null);
			List<TabItem> projectsSectorCodeTabs = VennGridUtils.createSummaryProjectsTab(intersactions, vennBean.getVennGraphBeanVO(), "Sector Code");
			
			olapPanel.getCentralTabPanel().add(projectsSummaryTabs.get(0));
			olapPanel.getPortletTabPanel().add(projectsSummaryTabs.get(1));
			
			olapPanel.getCentralTabPanel().add(projectsSectorCodeTabs.get(0));
			olapPanel.getPortletTabPanel().add(projectsSectorCodeTabs.get(1));
			
			// projects intersections
	//		List<TabItem> donorsTab = VennGridUtils.createBudgetModality(intersactions);
	//
	//		olapPanel.getCentralTabPanel().add(donorsTab.get(0));
	//		olapPanel.getPortletTabPanel().add(donorsTab.get(1));
			
			
			// layout
			olapPanel.getPanelCentral().layout();
			olapPanel.getPanelPortlet().layout();

		}
	}
	
	
	
	
	



	private static void getDacProjects(final SelectionPortalContentVenn vennPortal, final VennBeanVO beanVenn) {
		final List<VennIntersectionsBean> intersactions = beanVenn.getVennGraphBeanVO().getAllIntersections();

//		
		VerticalPanel panelPortlet = new VerticalPanel();
		panelPortlet.setSpacing(5);
		
		VerticalPanel panelCentral = new VerticalPanel();
		panelCentral.setSpacing(5);
		
		
		
		// getting the labels from FAO Strategic Objectives
		createDACProjects(intersactions, beanVenn, panelCentral, panelPortlet);

		
		vennPortal.getPortletInfoPanel().removeAll();
		vennPortal.getPortletInfoPanel().add(new HTML("<div><i>Click for the DAC list <br></i></div>"));
		vennPortal.getPortletInfoPanel().add(panelPortlet);
		vennPortal.getPortletInfoPanel().show();
		vennPortal.getPortletInfoPanel().layout();
		
		vennPortal.getCentralInfoPanel().removeAll();
		vennPortal.getCentralInfoPanel().add(new HTML("<div><i>Click for the DAC list <br></i></div>"));
		vennPortal.getCentralInfoPanel().add(panelCentral);
		vennPortal.getCentralInfoPanel().show();
		vennPortal.getCentralInfoPanel().layout();
	
	}
	
	private static void createDACProjects(List<VennIntersectionsBean> intersactions, VennBeanVO beanVenn, VerticalPanel panelCentral, VerticalPanel panelPortlet) {
		for (VennIntersectionsBean intersaction : intersactions) { 
//			List<String> dacCodes = filterDACCodes(intersaction.getDacCodes(), beanVenn.getAggregationLevel());
		
				if (!intersaction.getAggregatedDacCodes().isEmpty()) {
					
					// CENTRAL
					String htmlCentralString = new String();
					Hyperlink labelCentral = new Hyperlink();
					String str = "000000" + Integer.toHexString(intersaction.getColor());
					String color = "#" + str.substring(str.length() - 6);
					if ( color.equals("#ffffff")) 
						color = "#000000";
					labelCentral.setHTML("<div style='font-size:11px; color:" + color + "'><u>" + intersaction.getLabel() + "</u></div>");
					panelCentral.add(labelCentral);
	
					Window windowCentral = new Window();
					windowCentral.setHeading(intersaction.getLabel());
					for(String dacCode : intersaction.getAggregatedDacCodes()) {
//						htmlString += l + " - " + beanVenn.getDacCodes().get(l);
//						htmlString +=  dacCode + " - " + beanVenn.getDacCodes().get(dacCode);
						htmlCentralString += beanVenn.getVennGraphBeanVO().getDacCodes().get(dacCode);
						htmlCentralString += "<br>";
					}
					HTML htmlCentral = new HTML(htmlCentralString);
					windowCentral.add(htmlCentral);
	
					labelCentral.addClickHandler(VennController.selectIntersaction(windowCentral));
					
					
					// PORTLET
					String htmlPortletString = new String();
					Hyperlink labelPortlet = new Hyperlink();
//					String str = "000000" + Integer.toHexString(intersaction.getColor());
//					String color = "#" + str.substring(str.length() - 6);
					if ( color.equals("#ffffff")) 
						color = "#000000";
					labelPortlet.setHTML("<div style='font-size:11px; color:" + color + "'><u>" + intersaction.getLabel() + "</u></div>");
					panelPortlet.add(labelPortlet);
	
					Window windowPortlet = new Window();
					windowPortlet.setHeading(intersaction.getLabel());
					for(String dacCode : intersaction.getAggregatedDacCodes()) {
//						htmlString += l + " - " + beanVenn.getDacCodes().get(l);
//						htmlString +=  dacCode + " - " + beanVenn.getDacCodes().get(dacCode);
						htmlPortletString += dacCode + " | " + beanVenn.getVennGraphBeanVO().getDacCodes().get(dacCode);
						htmlPortletString += "<br>";
					}
					HTML htmlPortlet = new HTML(htmlPortletString);
					windowPortlet.add(htmlPortlet);
	
					labelPortlet.addClickHandler(VennController.selectIntersaction(windowPortlet));
					
					
				}
			
			
		}
	}
	
	private static List<String> filterDACCodes(List<String> dacCodes, Integer level) {
//		System.out.println("***************DAC CODES!!: "+ dacCodes );
//		System.out.println("LVL: "+ level);
		HashMap<String, String> filteredCodes = new HashMap<String, String>();
		List<String> result = new ArrayList<String>();
		if ( !dacCodes.isEmpty()) {
			for( String dacCode : dacCodes ) {
				if ( level == 0)
					filteredCodes.put(dacCode.substring(0, 2) + "000", "");
				else if ( level == 1 )
					filteredCodes.put(dacCode.substring(0, 3) + "00", "");
				else
					return dacCodes;
			}
	
			for(String dacCode : filteredCodes.keySet())
				result.add(dacCode);
		}
		
		return result;
	}

	public static ClickHandler selectIntersaction(final Window window) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (window.isVisible())
					window.hide();
				else
					window.show();
			}
		};
	}

	public static void fillCountriesListStore(final ComboBox<ListItem> comboBox, final ListStore<ListItem> listStore) {
		String language = CheckLanguage.getLanguage();
		VennServiceEntry.getInstance().getCountriesList(language, new AsyncCallback<List<CodeVo>>() {

			public void onSuccess(List<CodeVo> result) {
				List<ListItem> countries = new ArrayList<ListItem>();
				for (CodeVo code : result)
					countries.add(new ListItem(code.getLabel(), code.getCode()));

				listStore.add(countries);

				comboBox.setValue(listStore.getAt(0));
			}

			public void onFailure(Throwable caught) {
			}

		});
	}

	
	public static SelectionListener<ButtonEvent> deselectAllEvent(final TreePanel<ListItemTree> tree) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				
				List<ListItemTree> items = tree.getCheckedSelection();
//				System.out.println("getSize2: " + items.size());
				for (ListItemTree itemSelected : items) {
					tree.setChecked(itemSelected, false);
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> selectAllEvent(final TreePanel<ListItemTree> tree) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {	
				selectAll(tree);
			}
		};
	}
	
	public static void selectAll(final TreePanel<ListItemTree> tree) {
		TreeStore<ListItemTree> itemsTreeStore = tree.getStore();
		List<ListItemTree> items = itemsTreeStore.getAllItems();
		for (ListItemTree itemSelected : items) {
			tree.setChecked(itemSelected, true);
		}
	}
	
	
	
	
	public static void applyXMLConfiguration(final VennPortalPanel vennPortal, final CenterToolPanel centerPanel, final String language,final String xml) {
		
		// getting xml configuration settings
		VennServiceEntry.getInstance().getConfiguration(xml, true, new AsyncCallback<VennConfigurationBeanVO>() {
			public void onSuccess(VennConfigurationBeanVO bean) {
				
				// 	apply to venn	
				HashMap<String, String> aCodes = getCodes(bean.getSetA().getStrategies());
				HashMap<String, String> bCodes = getCodes(bean.getSetB().getStrategies());
				HashMap<String, String> cCodes = getCodes(bean.getSetC().getStrategies());
				System.out.println("bean countries= " + bean.getCountries());
				List<String> countryCodes = bean.getCountries();
				vennPortal.setCountryCodes(countryCodes);
				
				createStandardReport(vennPortal, aCodes, bCodes, cCodes, centerPanel, language, countryCodes, bean.getSetA().getOrganization(), bean.getSetB().getOrganization(), bean.getSetC().getOrganization());
				
//				apply(vennPortal, aCodes, bCodes, cCodes, centerPanel, language, countryCodes, bean.getSetA().getOrganization(), bean.getSetB().getOrganization(), bean.getSetC().getOrganization());
			}
			
			public void onFailure(Throwable arg0) {
				FenixAlert.error("Retrieving XML", "Error retrieving configuration");
			}

		
		});
		
	}
	
	
	
	
	public static void initializeWithXMLConfiguration(final VennPortalPanel vennPortal, final CenterToolPanel centerPanel, final DimensionPortletPanel dimensionPortlet,final String language,final String xml, final String set) {
		System.out.println("GETTING XML CONFIGURAION");
		
		// getting xml configuration settings
		VennServiceEntry.getInstance().getConfiguration(xml, true, new AsyncCallback<VennConfigurationBeanVO>() {
			public void onSuccess(VennConfigurationBeanVO bean) {
			
//				if (bean.get)
				if ( bean.getSet(set).getOrganizationType() != null ) {
//					if ( !bean.getSet(set).getOrganizationType().contains("filter") ) {
//					getXMLOrganizationType(dimensionPortlet, language, xml, set, bean);
//					}
				}
				
			}
			
			public void onFailure(Throwable arg0) {
				FenixAlert.error("Retrieving XML", "Error initializing configuration");
			}

		
		});
		
	}
	
	private static void getXMLOrganizationType(final DimensionPortletPanel dimensionPortlet,final String language,final String xml, final String set, final VennConfigurationBeanVO configurationBean) {

		System.out.println("configurationBean.getSet(set).getOrganizationType(): " + configurationBean.getSet(set).getOrganizationType());
		
		
		
		
			// getting organizations types list
			VennServiceEntry.getInstance().findOrganizationsType(language, new AsyncCallback<HashMap<String, String>>() {
				public void onSuccess(HashMap<String, String> result) {
					
					ListStore<ListItem> store = dimensionPortlet.getStoreOrganizationsType();
					ComboBox<ListItem> comboBox = dimensionPortlet.getComboOrganizationsType();
					
					LinkedHashMap<String, String> out = sortByValues(result);
		
					final List<ListItem> values = new ArrayList<ListItem>();
					for (String key : out.keySet()) {
						values.add(new ListItem(key, result.get(key)));
					}
					
					store.removeAll();
					ListItem lItem = null;
					
					
					if ( !configurationBean.getSet(set).getOrganizationType().contains("filter") || !configurationBean.getSet(set).getOrganizationType().contains("")) {
						// setting SET organization type interface
						for( ListItem listItem : values){
							if( listItem.getValue().equals(configurationBean.getSet(set).getOrganizationType())) {
								lItem = listItem;
							}
						
						}
					}
					else { 
						ListItem listItem = new ListItem(configurationBean.getSet(set).getOrganization(), configurationBean.getSet(set).getOrganizationType());
						values.add(listItem);	
						lItem = listItem;
					}

					store.add(values);
					comboBox.setValue(lItem);
					
					store.clearFilters();
					
//					System.out.println("STORE: " + store.getCount());
//					for(int i=0; i < store.getCount(); i++) {
//						System.out.println("STORE--->: " + store.getAt(i).getValue());
//					}
					
					
					if ( !configurationBean.getSet(set).getOrganizationType().contains("filter") ) {
						getXMLOrganization(dimensionPortlet, language, xml, set, configurationBean);
					}

				}
		
				public void onFailure(Throwable caught) {
					FenixAlert.error("Error", "Error retrieving organizations type", "");
				}

			});
		
		
	}
	
	
	private static void getXMLOrganization(final DimensionPortletPanel dimensionPortlet,final String language,final String xml, final String set, final VennConfigurationBeanVO configurationBean) {
		
		// getting organizations list
		ListItem value = dimensionPortlet.getComboOrganizationsType().getValue();
		
		VennServiceEntry.getInstance().findOrganizations(value.getValue(), language, new AsyncCallback<HashMap<String, String>>() {
			public void onSuccess(HashMap<String, String> result) {
				LinkedHashMap<String, String> out = sortByValues(result);
				
				ListStore<ListItem> store = dimensionPortlet.getStoreOrganizations();
				ComboBox<ListItem> comboBox = dimensionPortlet.getComboOrganizations();
				

				List<ListItem> values = new ArrayList<ListItem>();
				for (String key : out.keySet()) {
					values.add(new ListItem(key, result.get(key)));
				}
				store.removeAll();
				store.add(values);
				

				for( ListItem listItem : values){
					// SET A
					if ( set.equals("setA")) {
						if( listItem.getValue().equals(configurationBean.getSetA().getOrganization())) {
//							System.out.println("found O item A "+ listItem.getValue());
							comboBox.setValue(listItem);
						}	
					}
					else if ( set.equals("setB")) {
						if( listItem.getValue().equals(configurationBean.getSetB().getOrganization())) {
//							System.out.println("found O item B"+ listItem.getValue());
							comboBox.setValue(listItem);
						}	
					}
					else if ( set.equals("setC")) {
						if( listItem.getValue().equals(configurationBean.getSetC().getOrganization())) {
//							System.out.println("found O item C"+ listItem.getValue());
							comboBox.setValue(listItem);
						}	
					}
				}	
				store.clearFilters();
				
				
				
				// create recursive tree
				createRecursiveTree(dimensionPortlet, language, set, configurationBean);
				
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "Error retrieving organizations", "");
			}
		});
		
		
	}

	private static void createRecursiveTree(final DimensionPortletPanel dimensionPortlet,final String language, final String set, final VennConfigurationBeanVO configurationBean) {
		final ListItem valueType = dimensionPortlet.getComboOrganizationsType().getValue();
		final ListItem value = dimensionPortlet.getComboOrganizations().getValue();

		VennServiceEntry.getInstance().findSOIFA(valueType.getValue(), value.getValue(), language, new AsyncCallback<HashMap<String, String>>() {
			public void onSuccess(HashMap<String, String> result) {
				TreePanel<ListItemTree> treePanel = dimensionPortlet.getTreePanel();
				TreeStore<ListItemTree> treeStore = dimensionPortlet.getTreeStore();

				LinkedHashMap<String, String> out = sortByValues(result);
				List<ListItem> values = new ArrayList<ListItem>();

				treeStore.removeAll();
	
				
				for (String key : out.keySet()) {

					ListItemTree treeItem = createTreeItem(key, result.get(key), result.get(key), "0", "0", valueType.getValue(), value.getValue());
					treeStore.add(treeItem, false);



					values.add(new ListItem(key, result.get(key)));
		
					createTreeLeafs(dimensionPortlet, language, set, configurationBean, treeItem);
					
				}

			}


			public void onFailure(Throwable caught) {
				FenixAlert.error("Error", "Error retrieving values", "");
			}
		});
	}

	
	private static void createTreeLeafs(final DimensionPortletPanel dimensionPortlet,final String language, final String set, final VennConfigurationBeanVO configurationBean, final ListItemTree item) {
		int level = Integer.parseInt(item.getLevel());
		final String strategyCode = item.getCode();
		final String organizationTypeCode = item.getOrganizationTypeCode();
		final String organizationCode = item.getOrganizationCode();
		final String fatherCode = item.getFather();

		VennServiceEntry.getInstance().findStrategiesLvl1(organizationTypeCode, organizationCode, strategyCode, language, new AsyncCallback<HashMap<String, String>>() {

			@SuppressWarnings("unchecked")
			public void onSuccess(HashMap<String, String> result) {
				TreeStore<ListItemTree> treeStore = dimensionPortlet.getTreeStore();
				
				
				LinkedHashMap<String, String> out = sortByValues(result);

				List<ListItem> values = new ArrayList<ListItem>();

				for (String key : out.keySet()) {
					values.add(new ListItem(key, result.get(key)));
					final ListItemTree strategy = createTreeItem(key, result.get(key), result.get(key), "1", strategyCode, organizationTypeCode, organizationCode);

					treeStore.add(item, strategy, false);

					final String strategyCode2 = strategy.getCode();
					final String organizationTypeCode2 = strategy.getOrganizationTypeCode();
					final String organizationCode2 = strategy.getOrganizationCode();
					final String fatherCode2 = strategy.getFather();
					final String language = CheckLanguage.getLanguage();

					VennServiceEntry.getInstance().findStrategiesLvl2(organizationTypeCode2, organizationCode2, fatherCode2, strategyCode2, language, new AsyncCallback<HashMap<String, String>>() {

						public void onSuccess(HashMap<String, String> result) {
							TreeStore<ListItemTree> treeStore = dimensionPortlet.getTreeStore();

							LinkedHashMap<String, String> out = sortByValues(result);

							List<ListItemTree> treeItems = new ArrayList<ListItemTree>();
							List<ListItem> values = new ArrayList<ListItem>();
							for (String key : out.keySet()) {
								values.add(new ListItem(key, result.get(key)));
								// cut the label
								final ListItemTree strategy2 = createTreeItem(key, result.get(key), result.get(key), "2", strategyCode, organizationTypeCode, organizationCode);
								treeItems.add(strategy2);
								
							}
							treeStore.add(strategy, treeItems, false);
							
//							selectAll(treePanel);
						
							checkTreefinal(dimensionPortlet, language, set, configurationBean);
						}

						public void onFailure(Throwable caught) {

						}
					});

				}
			}

			public void onFailure(Throwable caught) {

			}
		});
	}
	
	
	private static void checkTreefinal(DimensionPortletPanel dimensionPortlet, final String language, final String set, final VennConfigurationBeanVO configurationBean) {
		List<String> strategies = new ArrayList<String>();
		TreePanel<ListItemTree> treePanel = dimensionPortlet.getTreePanel();
		
		if ( set.equals("setA")) {
			strategies = configurationBean.getSetA().getStrategies();
		}
		else if ( set.equals("setB")) {
			strategies = configurationBean.getSetB().getStrategies();
		}
		else if ( set.equals("setC")) {
			strategies = configurationBean.getSetC().getStrategies();
		}
		
		TreeStore<ListItemTree> itemsTreeStore = dimensionPortlet.getTreeStore();
		List<ListItemTree> items = itemsTreeStore.getAllItems();
		for (String strategy : strategies) {
//			System.out.println("looking for strategy: " + strategy);
			for (ListItemTree itemSelected : items) {
				if ( strategy.equals(itemSelected.getCode())) {
//					System.out.println("found code: " + strategy);
					treePanel.setChecked(itemSelected, true);
					break;
				}
					
			}
		}
		
		dimensionPortlet.getComboOrganizationsType().disableEvents(false);
		dimensionPortlet.getComboOrganizations().disableEvents(false);
	}

	
	@SuppressWarnings("deprecation")
	private static HashMap<String, String> getCodes(List<String> strategies) {
		HashMap<String, String> codes = new HashMap<String, String>();
		
//		System.out.println("------------------> " + strategies);
		
		for (String strategy : strategies) 
			codes.put(strategy, strategy);
		

		return codes;
	}
	

	private static HashMap<String, String> getStrategyCodes(HashMap<String, HashMap<String, String>> strategies) {
		HashMap<String, String> codes = new HashMap<String, String>();


		for (String strategy : strategies.keySet()) 
			codes.putAll(strategies.get(strategy));
		

		return codes;
	}
	
	
	
	public static SelectionListener<MenuEvent> exportStandardReport(final VennPortalPanel vennPortal) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				exportStandardReportMethod(vennPortal);
			}
		};
	}
	
	public static void exportStandardReportMethod(final VennPortalPanel vennPortal) {
		final VennReportBeanVO vennReportBean = vennPortal.getVennReportBean();

		VennServiceEntry.getInstance().vennReport(vennReportBean, new AsyncCallback<String>() {
			public void onSuccess(final String result) {
				Window window = new Window();
				ContentPanel cont = new ContentPanel();
					cont.setHeaderVisible(false);
					cont.setLayout(new FitLayout());
					window.setLayout(new FitLayout());
					window.setSize(800, 600);
					HTML content = new HTML(result);
					cont.add(content);
					window.add(cont);
					window.show();	
			}
				

			public void onFailure(Throwable caught) {
			}

		});
	}
	
	
	/***************** this method create the standard report ***/
	public static void getIntersectionInfo(final VennPortalPanel vennPortal, final HashMap<String, String> setA, final HashMap<String, String> setB, final HashMap<String, String> setC, final String language) {
		String setALabel= "";
		String setBLabel = "";
		String setCLabel = "";

		
		
		final List<String> countryCodes = new ArrayList<String>();
		
		for(String key : setA.keySet()) {
			countryCodes.add(key);
			setALabel += setA.get(key) + " ";
		}
		
		for(String key : setB.keySet()) 
			setBLabel += setB.get(key) + " ";
		
		
		for(String key : setC.keySet()) 
			setCLabel += setC.get(key) + " ";
		
		final String labelA = setALabel;
		final String labelB = setBLabel;
		final String labelC = setCLabel;
		

//		countryCodes.add(coutryCode);
		
		/** TODO: the strategic bojective should be takeing with a list of string, and not just a code **/
		VennServiceEntry.getInstance().getStrategicObjectives(setA, language, new AsyncCallback<HashMap<String, HashMap<String, String>>>() {
			public void onSuccess(HashMap<String, HashMap<String, String>> result) {
				final HashMap<String, String> aCodes = getStrategyCodes(result);
				
				VennServiceEntry.getInstance().getStrategicObjectives(setB, language, new AsyncCallback<HashMap<String, HashMap<String, String>>>() {
					public void onSuccess(HashMap<String, HashMap<String, String>> result) {
						final HashMap<String, String> bCodes = getStrategyCodes(result);
						
						VennServiceEntry.getInstance().getStrategicObjectives(setC, language, new AsyncCallback<HashMap<String, HashMap<String, String>>>() {
							public void onSuccess(HashMap<String, HashMap<String, String>> result) {
								HashMap<String, String> cCodes = getStrategyCodes(result);
								
								/*** is it right??? vennPortal.getCenterToolPanel() **/
								apply(vennPortal, aCodes, bCodes, cCodes, vennPortal.getCenterToolPanel(), language, countryCodes, labelA, labelB, labelC);
							}
							
							public void onFailure(Throwable arg0) {
							}
						});
						
						
					}
					
					public void onFailure(Throwable arg0) {
					}
				});
				
			}
			public void onFailure(Throwable arg0) {
			}
		});
		
	}
	

}
