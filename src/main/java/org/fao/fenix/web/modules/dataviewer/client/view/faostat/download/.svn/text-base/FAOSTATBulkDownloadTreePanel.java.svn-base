package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.core.client.control.utils.GoogleAnalyticsController;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.BulkDownloadVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATDownloadConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class FAOSTATBulkDownloadTreePanel extends LayoutContainer {
	
	public HorizontalPanel wrapper;
	
	public TreeStore<DWCodesModelData> store;  
	  
	public TreePanel<DWCodesModelData> tree;  

	public ContentPanel left;
	
	public ContentPanel right;
	
	public Boolean checkTreeOnClick = true;
	
	TreeMap<String, List<BulkDownloadVO>> out;
	
//	private final static String WIDTH = "500px";
	
//	private final static String WIDTH = "1000px";
	
	public FAOSTATBulkDownloadTreePanel() {
		wrapper = new HorizontalPanel();
		
//		wrapper.setLayout(new FillLayout());
//		wrapper.setSpacing(5);
		wrapper.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.getMainContentHeight(true)) + "px");

		/** Tree **/
		store = new TreeStore<DWCodesModelData>();
		tree = new TreePanel<DWCodesModelData>(store);
		tree.setDisplayProperty("label");
//		tree.setHeight(FAOSTATDownloadConstants.TREE_HEIGHT);
//		tree.setWidth(FAOSTATDownloadConstants.TREE_WIDTH);
		tree.addStyleName("tree-box");
		
		left = new ContentPanel();
		left.setHeaderVisible(false);
		left.setBodyBorder(false);
		left.setScrollMode(Scroll.NONE);

		right = new ContentPanel();
		right.setHeaderVisible(false);
		right.setBodyBorder(false);
		right.setScrollMode(Scroll.NONE);
		
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		wrapper.setSpacing(10);
		
		wrapper.add(left);
		wrapper.add(right);

		left.add(DataViewerClientUtils.addVSpace(5));
		left.add(addTitle(FAOSTATLanguage.print().domains()));
		left.add(DataViewerClientUtils.addVSpace(5));
		left.add(tree);
		
		loadDomains();

		FAOSTATDownloadController.getDomainsAgent(this, store, tree, null);

		addListeners(this);
		
		wrapper.layout();
		
		add(wrapper);

	}
	
	private void addListeners(final FAOSTATBulkDownloadTreePanel download) {

		tree.addListener(Events.BeforeExpand, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				checkTreeOnClick = false;
			}
		});
		
		tree.addListener(Events.BeforeCollapse, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				checkTreeOnClick = false;			
			}
		});

		tree.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {


				if (checkTreeOnClick) {
					DWCodesModelData selectedCode = tree.getSelectionModel().getSelectedItem();
					DWCodesModelData parentDomain = tree.getStore().getParent(selectedCode);

					GoogleAnalyticsController.trackEvent(FAOSTATCurrentView.DOWNLOAD_BULK.toString(), "Open Tree", selectedCode.getCode());

					if (selectedCode != null && parentDomain == null) {
						tree.setExpanded(selectedCode, true);
					} else {
						if (parentDomain != null) {
							System.out.println("load the tree");
							HTML html = new HTML();
							String s = "<ul class='bullet-list'>";
							System.out.println(selectedCode.getCode());
							
							for (BulkDownloadVO vo : out.get(selectedCode.getCode())) 
								s += "<li><a target='_blank' href='" + vo.getUrl() + "'>" + vo.getContent() + "</a> (" + BabelFish.print().dateLastUpdate() + ": " + vo.getCreationDate() + ")</li>";
							s += "</ul>";
							html.setHTML(s);
							right.removeAll();
							HorizontalPanel h = new HorizontalPanel();
							h.add(DataViewerClientUtils.addHSpace(10));
							VerticalPanel v = new VerticalPanel();
							FAOSTATDownloadTitlePanel  title = new FAOSTATDownloadTitlePanel();
							v.add(DataViewerClientUtils.addVSpace(5));
							v.add(title.buildTitle(parentDomain.getLabel() + " > " + selectedCode.getLabel(), ""));
							v.add(DataViewerClientUtils.addVSpace(6));
							v.add(html);
							h.add(v);
							right.add(h);
							wrapper.layout();
						}
					}
				}
				checkTreeOnClick = true;
			}
		});
	}
	
	
//	@Override
//	protected void onRender(Element parent, int index) {
//		super.onRender(parent, index);
//		try {
////			wrapper.add(new FAOSTATLoadingPanel().buildWaitingPanel(null, WIDTH, WIDTH, true));
//			DataViewerServiceEntry.getInstance().findAllBulkDownloads(FAOSTATConstants.faostatLanguage, new AsyncCallback<Map<String, List<BulkDownloadVO>>>() {
//				public void onSuccess(Map<String, List<BulkDownloadVO>> map) {
//					TreeMap<String, List<BulkDownloadVO>> out = sortByKeys(map);
//					System.out.println(out.size());
//					for (String key : out.keySet()) {
//							VerticalPanel v = new VerticalPanel();
//							v.add(new HTML("<div class='download_title'>" + key + " (" + BabelFish.print().dateLastUpdate() + ": )</div>"));
//							HorizontalPanel p = new HorizontalPanel();
//							p.setWidth(WIDTH);
//							
//							String s = "<div class='bullet-list'>";
//							HTML html = new HTML();
//							for (BulkDownloadVO vo : out.get(key)) {
//								s += "<a target='_blank' href='" + vo.getUrl() + "'>" + vo.getContent() + "</a>";
//								s += "&nbsp";		
//							}
//							s +="</div>";
//							html.setHTML(s);
//							p.add(html);
////							s += "</ul>";
//							v.add(p);
//							wrapper.add(v);
//					}
//					wrapper.getLayout().layout();
//				}
//				public void onFailure(Throwable E) {
//					FenixAlert.error(BabelFish.print().error(), E.getMessage());
//				}
//			});
//		} catch (FenixGWTException E) {
//			FenixAlert.error(BabelFish.print().error(), E.getMessage());
//		}
//		add(wrapper);
//	}
	
	public Html addTitle(String title) {
		return new Html("<div class='download_title'> " + title + "</div>");
	}
	
	private void loadDomains() {
		try {
			DataViewerServiceEntry.getInstance().findAllBulkDownloads(FAOSTATConstants.faostatLanguage, new AsyncCallback<Map<String, List<BulkDownloadVO>>>() {
				public void onSuccess(Map<String, List<BulkDownloadVO>> map) {
					out = sortByKeys(map);
				}
				public void onFailure(Throwable E) {
					FenixAlert.error(BabelFish.print().error(), E.getMessage());
				}
			});
		} catch (FenixGWTException E) {
			FenixAlert.error(BabelFish.print().error(), E.getMessage());
		}
	}
	
	public static Listener<BaseEvent> googleAnalyticsTrack(String label) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent ce) {
				// TODO: call google analytics
			}
		};
	}
	
	public static TreeMap<String, List<BulkDownloadVO>> sortByKeys(Map<String, List<BulkDownloadVO>> in) {
		TreeMap<String, List<BulkDownloadVO>> out = new TreeMap<String, List<BulkDownloadVO>>(new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));	
		return out;
	}
	
}