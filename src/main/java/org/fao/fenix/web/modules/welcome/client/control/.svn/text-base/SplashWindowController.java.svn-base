package org.fao.fenix.web.modules.welcome.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.vo.FenixMenuItemVo;
import org.fao.fenix.web.modules.corejs.client.FenixJS;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEOpener;
import org.fao.fenix.web.modules.re.client.control.image.CatalogueContextMenuImageController;
import org.fao.fenix.web.modules.re.client.control.olap.CatalogueContextMenuOlapController;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportWindow;
import org.fao.fenix.web.modules.welcome.client.view.SplashWindow;
import org.fao.fenix.web.modules.welcome.common.services.WelcomeServiceEntry;
import org.fao.fenix.web.modules.welcome.common.vo.QueryResultVO;
import org.fao.fenix.web.modules.welcome.common.vo.QueryVO;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class SplashWindowController {

	public static HashMap<String, String> iconMap = new HashMap<String, String>();

	static {
		iconMap = new HashMap<String, String>();
		iconMap.put("ChartDesign", "chartIcon");
		iconMap.put("CodingSystem", "codingSystemIcon");
		iconMap.put("CoreDataset", "datasetIcon");
		iconMap.put("DBFeatureLayer", "layerIcon");
		iconMap.put("ExternalWMSLayer", "layerIcon");
		iconMap.put("FenixDocument", "smallCamera");
		iconMap.put("FenixImage", "smallCamera");
		iconMap.put("MTParameters", "olap");
		iconMap.put("MapView", "mapIcon");
		iconMap.put("Resource", "defaultResourceIcon");
		iconMap.put("TableView", "tableIcon");
		iconMap.put("TinyMCEReport", "reportIcon");
		iconMap.put("WMSMapProvider", "layerIcon");
	}

	public static SelectionListener<IconButtonEvent> metadata(final QueryResultVO model) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				MEOpener.showMetadata(Long.valueOf(model.getResourceid()), true, false);
			}
		};
	}

	public static SelectionListener<IconButtonEvent> open(final QueryResultVO model) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				openAgent(model);
			}
		};
	}

	public static Listener<GridEvent> onDoubleClick(final SplashWindow w) {
		return new Listener<GridEvent>() {
			public void handleEvent(GridEvent be) {
				QueryResultVO model = (QueryResultVO) be.getGrid().getStore().getAt(be.getRowIndex());
				openAgent(model);
				// System.out.println("vo? " + vo.getTitle());
			}
		};
	}

	public static void openAgent(QueryResultVO model) {
		String rt = model.getResourceType().substring(1 + model.getResourceType().lastIndexOf('.'));

		if (rt.equals("ChartDesign")) {
			Map<Long, String> ids = new HashMap<Long, String>();
			ids.put(Long.valueOf(model.getResourceid()), model.getTitle());
			ChartDesignerController.loadChart(ids, WhoCall.USER, "", null, null);
		} else if (rt.equals("CodingSystem")) {
			new CodingSearchResults().build(Long.valueOf(model.getResourceid()));

		} else if (rt.equals("CoreDataset")) {
			new TableWindow().showAllData(Long.valueOf(model.getResourceid()), false, false, model.getTitle());
		} else if (rt.equals("DBFeatureLayer")) {
			FenixAlert.info(BabelFish.print().info(), "This feature is not yet available.");
		} else if (rt.equals("ExternalWMSLayer")) {
			FenixAlert.info(BabelFish.print().info(), "This feature is not yet available.");
		} else if (rt.equals("FenixDocument")) {
			FenixAlert.info(BabelFish.print().info(), "This feature is not yet available.");
		} else if (rt.equals("FenixImage")) {
			CatalogueContextMenuImageController.open(model.getResourceid());
		} else if (rt.equals("MTParameters")) {
			CatalogueContextMenuOlapController.open(model.getResourceid(), model.getTitle(), WhoCall.USER, "");

		} else if (rt.equals("MapView")) {
			MapWindow mw = new MapWindow(String.valueOf(model.getResourceid()), model.getTitle());
			mw.show();
		}
		// else if(rt.equals("Resource")){}
		else if (rt.equals("TableView")) {
			FenixAlert.info(BabelFish.print().info(), "This feature is not yet available.");
		} else if (rt.equals("TinyMCEReport")) {
			new TinyMCEReportWindow().build(model.getResourceid());
		} else if (rt.equals("WMSMapProvider")) {
			FenixAlert.info(BabelFish.print().info(), "This feature is not yet available.");
		} else
			FenixAlert.error(BabelFish.print().error(), "The open resource feature. Not added yet!" + rt);
	}

	public static boolean isValidResource(QueryResultVO model) {
		String rt = model.getResourceType().substring(1 + model.getResourceType().lastIndexOf('.'));

		return iconMap.containsKey(rt);
	}

	public static ListStore<QueryResultVO> getUpdateStore(ListStore<QueryResultVO> store, List<QueryResultVO> vos) {
		store.removeAll();
		for (QueryResultVO vo : vos)
			if (SplashWindowController.isValidResource(vo))
				store.add(vo);
		return store;
	}

	public static SelectionListener<ButtonEvent> metadatas(final SplashWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				List<QueryResultVO> models = new ArrayList<QueryResultVO>();

				for (QueryResultVO qvo : w.getSplashSearchPanel().getSm().getSelectedItems()) {
					models.add(qvo);
				}

				metadatsAgent(models);
			}
		};
	}

	public static void metadatsAgent(List<QueryResultVO> models) {

		for (QueryResultVO model : models) {

			System.out.println(new Date() + " on time_C_mac " + model);

			MEOpener.showMetadata(Long.valueOf(model.getResourceid()), true, false);
		}

	}

	public static SelectionListener<ButtonEvent> opens(final SplashWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				List<QueryResultVO> models = new ArrayList<QueryResultVO>();

				for (QueryResultVO qvo : w.getSplashSearchPanel().getSm().getSelectedItems()) {
					// if (isValidResource(qvo))
					models.add(qvo);
				}

				openAgent(models);
			}
		};
	}

	public static void openAgent(List<QueryResultVO> models) {

		for (QueryResultVO model : models) {
			// System.out.println(new Date() + " on time_C_mac " + model);
			openAgent(model);
		}
	}

	public static SelectionListener<ButtonEvent> search(final SplashWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				// 1 - collect params
				QueryVO vo = collectParameters(w);

				// 2 - call the server
				final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Searching...", BabelFish.print().pleaseWait());
				try {
					WelcomeServiceEntry.getInstance().search(vo, new AsyncCallback<List<QueryResultVO>>() {
						public void onSuccess(List<QueryResultVO> vos) {
							// 3 - refresh table
							// FenixAlert.info(BabelFish.print().info(),
							// "There are c:" + vos.size() + " vos");
							l.destroyLoadingBox();

							w.getSplashSearchPanel().setStore(getUpdateStore(w.getSplashSearchPanel().getStore(), vos));
							l.destroyLoadingBox();
						}

						public void onFailure(Throwable E2) {
							l.destroyLoadingBox();
							FenixAlert.error(BabelFish.print().error(), E2.getMessage());
							l.destroyLoadingBox();
						}
					});
				} catch (FenixGWTException E1) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), E1.getMessage());
					l.destroyLoadingBox();
				}
			}
		};
	}

	public static QueryVO collectParameters(SplashWindow w) {
		QueryVO vo = new QueryVO();
		vo.setFreeText(w.getSplashSearchPanel().getFreeText().getValue());
		vo.setSource(w.getSplashSearchPanel().getSource().getValue());
		vo.setFromDate(w.getSplashSearchPanel().getFromDate().getValue());
		vo.setToDate(w.getSplashSearchPanel().getToDate().getValue());

		if (!w.getSplashSearchPanel().getRegionComboBox().getSelection().isEmpty()) {
			vo.setRegion(w.getSplashSearchPanel().getRegionComboBox().getSelection().get(0).getGaulCode());
		}
		if (!w.getSplashSearchPanel().getResourceTypeComboBox().getSelection().isEmpty()) {
			vo.setResourceType(w.getSplashSearchPanel().getResourceTypeComboBox().getSelection().get(0).getGaulCode());
		}
		if (!w.getSplashSearchPanel().getCategoryComboBox().getSelection().isEmpty()) {
			vo.setCategory(w.getSplashSearchPanel().getCategoryComboBox().getSelection().get(0).getCategoryValue());
		}

		return vo;
	}

	public static SelectionListener<ButtonEvent> getTools(final SplashWindow w, final String xmlFileName, final String category) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				getToolsAgent(w, xmlFileName, category);
			}
		};
	}

	public static Listener<BaseEvent> getToolsListener(final SplashWindow w, final String xmlFileName, final String category) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				getToolsAgent(w, xmlFileName, category);
			}
		};
	}

	public static void getToolsAgent(final SplashWindow w, String xmlFileName, final String category) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Tools for Category: " + category + ".", BabelFish.print().pleaseWait());
		try {
			REServiceEntry.getInstance().getTools(xmlFileName, category, new AsyncCallback<List<FenixMenuItemVo>>() {
				public void onSuccess(List<FenixMenuItemVo> vos) {
					l.destroyLoadingBox();
					w.addItems(vos);
					l.destroyLoadingBox();
				}

				public void onFailure(Throwable E2) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), E2.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException E1) {
			l.destroyLoadingBox();
			FenixAlert.error(BabelFish.print().error(), E1.getMessage());
			l.destroyLoadingBox();
		}
	}

	public static void getIntroText(final SplashWindow w) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Introduction Text.", BabelFish.print().pleaseWait());
		try {
//			REServiceEntry.getInstance().getIntroText(new AsyncCallback<String>() {
//				public void onSuccess(String html) {
			REServiceEntry.getInstance().getNodeSettings(new AsyncCallback<Map<String,String>>() {
				public void onSuccess(Map<String,String> map) {
					l.destroyLoadingBox();
					w.getIntro().setHTML(map.get("WELCOME_SCREEN_TEXT"));
					setWelcomeScreenColors(w, map.get("WELCOME_SCREEN_BACKGROUND_COLOR_ONE"), map.get("WELCOME_SCREEN_BACKGROUND_COLOR_TWO"));
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable E2) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), E2.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException E1) {
			l.destroyLoadingBox();
			FenixAlert.error(BabelFish.print().error(), E1.getMessage());
			l.destroyLoadingBox();
		}
	}
	
	public static void setWelcomeScreenColors(SplashWindow w, String colorOne, String colorTwo) {
		w.getToolsPanel().setStyleAttribute("backgroundColor", colorOne);
		w.getToolsGalleryWrapper().setStyleAttribute("backgroundColor", colorOne);
		w.getCategoriesPanelWrapper().setStyleAttribute("backgroundColor", colorOne);
		w.getItemPanelWrapper().setStyleAttribute("backgroundColor", colorTwo);
		w.getIntroductionHeaderWrapper().setStyleAttribute("backgroundColor", colorOne);
		w.getToolsGalleryWrapper().setStyleAttribute("backgroundColor", colorOne);
		w.getToolsHeaderWrapper().setStyleAttribute("backgroundColor", colorOne);
		w.getBodyWrapper().setStyleAttribute("backgroundColor", colorTwo);
		String introductionHeaderText = w.getIntroductionHeaderHTML().getText();
		w.getIntroductionHeaderHTML().setHTML("<div style='font-family: sans-serif; font-size: 28pt; color: #FFFFFF; background-color: " + colorOne + ";'>" + introductionHeaderText + "</div>");
		String toolsHeaderText = w.getToolsHeaderHTML().getText();
		w.getToolsHeaderHTML().setHTML("<div style='font-family: sans-serif; font-size: 28pt; color: #FFFFFF; background-color: " + colorOne + ";'>" + toolsHeaderText + "</div>");
	}
	
	public static void getNodeSettings(final SplashWindow w) {
		final LoadingWindow l = new LoadingWindow(BabelFish.print().info(), "Loading Introduction Text.", BabelFish.print().pleaseWait());
		try {
			REServiceEntry.getInstance().getNodeSettings(new AsyncCallback<Map<String,String>>() {
				public void onSuccess(Map<String,String> map) {
					l.destroyLoadingBox();
					FenixJS js = new FenixJS();
					js.changeElementBackgroundColor("banner", map.get("BANNER_BACKGROUND_COLOR"));
					js.changeLeftImage(map.get("BANNER_LEFT"));
					js.changeRigthImage(map.get("BANNER_RIGHT"));
					l.destroyLoadingBox();
				}
				public void onFailure(Throwable E2) {
					l.destroyLoadingBox();
					FenixAlert.error(BabelFish.print().error(), E2.getMessage());
					l.destroyLoadingBox();
				}
			});
		} catch (FenixGWTException E1) {
			l.destroyLoadingBox();
			FenixAlert.error(BabelFish.print().error(), E1.getMessage());
			l.destroyLoadingBox();
		}
	}

}