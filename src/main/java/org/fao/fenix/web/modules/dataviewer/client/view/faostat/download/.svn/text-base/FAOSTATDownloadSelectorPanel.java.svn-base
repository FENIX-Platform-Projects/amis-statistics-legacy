package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;


import java.util.HashMap;
import java.util.List;


import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;


import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.ListViewSelectionModel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;

public class FAOSTATDownloadSelectorPanel {
	
	
	ContentPanel panel;
	
	public ListStore<DWCodesModelData> store;
	
	public ListView<DWCodesModelData> list;

	
	String title;

	private String selectionType;

	
	Boolean dragActive = false;
	Integer currentIndex = -1;

	Timer timer = null;
	StringBuffer search;
	Boolean destroyTimer = false;
	

	public FAOSTATDownloadSelectorPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);
		
		/** Tree **/
		store = new ListStore<DWCodesModelData>();
		list = new ListView<DWCodesModelData>();

		list.setStore(store);
		list.setDisplayProperty("label");

		search = new StringBuffer();
		
		
//		KeyListener listener = new KeyListener() {
//	        @Override
//	        public void componentKeyPress(ComponentEvent event) {
//	        
//	       }
//	    };
		

	  

		// key listener to change item
		list.addListener(Events.OnKeyDown, new Listener<ComponentEvent>() {
					@Override
					public void handleEvent(ComponentEvent be) {
						
						// timer to reset the buffer
						checkString();

						Integer key = be.getKeyCode();
						System.out.println("KeyListener componentKeyPress: " + key);
						
						String c = charValue(String.valueOf(key));
						
						System.out.println("char: " + c);
						
						search.append(c);
						
						String s = search.toString().toLowerCase();
						
						System.out.println("String to search: " + s);
						Boolean found = false;
						for(DWCodesModelData code : store.getModels()) {
//							System.out.println("code: " + code.getLabel());
							if ( code.getLabel().toLowerCase().startsWith(s)) {

								// this is a workaround to set the focus on the current selection
								Element elem = list.getElement(store.indexOf(code));
								if (elem != null) {
								   list.fly(elem).scrollIntoView(list.getElement(), false);
								}
								list.focus();

								list.getSelectionModel().select(code, false);

								found = true;
								break;
							}
							
						}
						
						if ( !found ) {
							// TODO: 
//							if ( c == al primo char della label selezionata passa all'item dopo se inizia con la stessa lettera)
							
						}
						
					}
				});
		
//		checkMouse();
//
//
//		list.addListener(Events.OnMouseOver, new Listener<ComponentEvent>() {
//
//			@Override
//			public void handleEvent(ComponentEvent ce) {
//				dragActive = getMouseStatus();
//				System.out.println("OnMouseOver: " + dragActive);
//				
//				if ( dragActive ) {
//					ListViewEvent le = (ListViewEvent) ce;
//					ListViewSelectionModel<DWCodesModelData> sm = list.getSelectionModel();
////					System.out.println("index: " + le.getIndex());
//
//					if ( le.getIndex() != -1 ) {
//						sm.select(currentIndex, le.getIndex(), true);
//					}
//
//					
//					try {
//						if ( le.getIndex() != -1 ) {
//							if ( le.getIndex() < store.getCount() -1 ) {
//								Element elem = list.getElement(le.getIndex() + 2);
//								if (elem != null) {
//									list.fly(elem).scrollIntoView(list.getElement(), false);
//									
//								}
//								list.focus();
//							}
//						}
//					}catch (Exception e) {
//					}
//				}
//			}
//		});
//	
//		list.addListener(Events.OnMouseDown, new Listener<ComponentEvent>() {
//			@Override
//			public void handleEvent(ComponentEvent ce) {
//				dragActive = true;
//				
//				System.out.println("OnMouseDown: " + dragActive);
//				
//				ListViewEvent le = (ListViewEvent) ce;
//				ListViewSelectionModel<DWCodesModelData> sm = list.getSelectionModel();	
//				currentIndex = le.getIndex();
//				
//			}
//		});
//		
//		list.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {
//			@Override
//			public void handleEvent(BaseEvent be) {
//				dragActive = false;
//				currentIndex = -1;
//				System.out.println("OnMouseDown: " + dragActive);
//			}
//		});
		
		
		
//		panel.addListener(Events.OnMouseOver, new Listener<ComponentEvent>() {
//		
//					@Override
//					public void handleEvent(ComponentEvent ce) {
//						
//						panel.addListener(Events.OnMouseUp, new Listener<BaseEvent>() {
//							@Override
//							public void handleEvent(BaseEvent be) {
//								dragActive = false;
//								currentIndex = -1;
//								System.out.println("OnMouseUp: " + dragActive);
//							}
//						});
//						
//						panel.addListener(Events.OnMouseDown, new Listener<ComponentEvent>() {
//							@Override
//							public void handleEvent(ComponentEvent ce) {
//								dragActive = true;
//								
//								System.out.println("OnMouseDown: " + dragActive);
//								
////								ListViewEvent le = (ListViewEvent) ce;
////								ListViewSelectionModel<DWCodesModelData> sm = list.getSelectionModel();	
////								currentIndex = le.getIndex();
//								
//							}
//						});
//
//						
//						System.out.println("OnMouseOver: " + dragActive);
////						
////						if ( dragActive ) {
////							ListViewEvent le = (ListViewEvent) ce;
////							ListViewSelectionModel<DWCodesModelData> sm = list.getSelectionModel();
//////							System.out.println("index: " + le.getIndex());
////		
////							if ( le.getIndex() != -1 ) {
////								sm.select(currentIndex, le.getIndex(), true);
////							}
////		
////							
////							try {
////								if ( le.getIndex() != -1 ) {
////									if ( le.getIndex() < store.getCount() -1 ) {
////										Element elem = list.getElement(le.getIndex() + 2);
////										if (elem != null) {
////											list.fly(elem).scrollIntoView(list.getElement(), false);
////											
////										}
////										list.focus();
////									}
////								}
////							}catch (Exception e) {
////							}
////						}
//					}
//				});
		
			
		
//		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//		list.getSelectionModel().setSelectionMode(SelectionMode.SIMPLE);

	}
	
	private void checkString() {
		timer = new Timer() {
			public void run() {
				search = new StringBuffer();

//				if ( destroyTimer ) {
//					search = new StringBuffer();
//				}
					
			}
		};
		timer.schedule(1000);
	}
	
	
    public static native String charValue(String value) /*-{
	    var c = String.fromCharCode(value);
	    return c;
//	    alert(c);
	 }-*/;
	
	public ContentPanel build(DWCodesModelData domainFilter, String title, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, String width, String height) {

		this.selectionType = selectionType;
		
		if ( title != null ) {
			this.title = title;
			panel.add(addTitle(title));
		}
		
		list.setWidth(width);
		list.setHeight(height);
//		list.setTemplate(getTemplate());
		
		/** TODO: move it...**/
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(selectionType);			
		FAOSTATConstants.setFAOSTATDBSettings(qvo);		
		qvo.setDomains(buildDomain(domainFilter));
		qvo.setGetTotalAndList(isTotalAndList);

		panel.add(new FAOSTATLoadingPanel().buildWaitingPanel(FAOSTATLanguage.print().loading(), width, height, true));

		FAOSTATDownloadController.getSelectors(this, qvo, selectionType, addSelectAll);

		return panel;
	}
	
	public ContentPanel build(List<DWCodesModelData> domainFilter, String title, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, String width, String height) {

		this.selectionType = selectionType;
		
		if ( title != null ) {
			this.title = title;
			panel.add(addTitle(title));
		}
		
		list.setWidth(width);
		list.setHeight(height);
		
		/** TODO: move it...**/
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(selectionType);				
		FAOSTATConstants.setFAOSTATDBSettings(qvo);		
		qvo.setDomains(buildDomain(domainFilter));
		qvo.setGetTotalAndList(isTotalAndList);

		panel.add(new FAOSTATLoadingPanel().buildWaitingPanel(FAOSTATLanguage.print().loading(), width, height, true));

		FAOSTATDownloadController.getSelectors(this, qvo, selectionType, addSelectAll);

		return panel;
	}
	
	
	
	
	/** ad hoc for fertilazers, land and pesticides... **/
	public ContentPanel buildItems(DWCodesModelData domainFilter, String title, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, Boolean selectAll, String width, String height) {

		this.selectionType = selectionType;
		
		if ( title != null ) {
			this.title = title;
			panel.add(addTitle(title));
		}
		
		list.setWidth(width);
		list.setHeight(height);
//		list.setTemplate(getTemplate());
		
		/** TODO: move it...**/
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(selectionType);		
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(selectionType);			
		FAOSTATConstants.setFAOSTATDBSettings(qvo);		
		qvo.setDomains(buildDomain(domainFilter));
		qvo.setGetTotalAndList(isTotalAndList);
		
		
		panel.add(new FAOSTATLoadingPanel().buildWaitingPanel(FAOSTATLanguage.print().loading(), width, height, true));

		FAOSTATDownloadController.getSelectorsItems(this, qvo, selectionType, addSelectAll, selectAll, domainFilter);

		
		
		return panel;
	}
	
	
	/** ad hoc for fertilazers, land and pesticides... **/
	public ContentPanel buildElementsList(DWCodesModelData domainFilter, String title, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, Boolean selectAll, String width, String height) {

		this.selectionType = selectionType;
		
		if ( title != null ) {
			this.title = title;
			panel.add(addTitle(title));
		}
		
		list.setWidth(width);
		list.setHeight(height);
//		list.setTemplate(getTemplate());
		
		/** TODO: move it...**/
		DWFAOSTATQueryVO qvo =  new DWFAOSTATQueryVO();
		qvo.setOutput(DataViewerBoxContent.GET.toString());
		qvo.setTypeOfOutput(selectionType);		
//		qvo.setOutputType(DataViewerBoxContent.GET.toString());
//		qvo.setResourceType(selectionType);			
		FAOSTATConstants.setFAOSTATDBSettings(qvo);		
		qvo.setDomains(buildDomain(domainFilter));
		qvo.setGetTotalAndList(isTotalAndList);
		
		
		panel.add(new FAOSTATLoadingPanel().buildWaitingPanel(FAOSTATLanguage.print().loading(), width, height, true));

		FAOSTATDownloadController.getSelectorsElementsList(this, qvo, selectionType, addSelectAll, selectAll, domainFilter);

		
		
		return panel;
	}
	
	
	
	public HorizontalPanel buildSelectAllPanel() {
		HorizontalPanel p = new HorizontalPanel();
		
		Button selectAll = new Button(FAOSTATLanguage.print().selectAll());
//		Button selectAll = new Button();
		selectAll.setIconStyle("addIcon");
		
		Button deselectAll = new Button(FAOSTATLanguage.print().deselectAll());
//		Button deselectAll = new Button();
		deselectAll.setIconStyle("deleteObj");
		
		p.add(selectAll);
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(deselectAll);

		// listeners
		
		selectAll.addSelectionListener(FAOSTATMainController.selectAll(list));
		
		deselectAll.addSelectionListener(FAOSTATMainController.deselectAll(list));

		
		return p;
	}

	
	private HashMap<String, String> buildDomain(DWCodesModelData domainFilter) {
		HashMap<String, String> domain = new HashMap<String, String>();
		
		if ( domainFilter != null )
			domain.put(domainFilter.getCode(), domainFilter.getLabel());
		
		return domain;
	}
	
	private HashMap<String, String> buildDomain(List<DWCodesModelData> domainFilter) {
		HashMap<String, String> domain = new HashMap<String, String>();
		
		if ( domainFilter != null)
			for(DWCodesModelData code : domainFilter ) {
				domain.put(code.getCode(), code.getLabel());
			}
		
		return domain;
	}
	
	public HorizontalPanel addTitle(String title) {
		HorizontalPanel p = new HorizontalPanel();
		p.addStyleName("download_tab_selected");
		Html html = new Html();
		html.setHtml("<div class='download_tab_title'>" + title + "</div>");
		p.add(html);
		return p;

//		return new Html("<div class='content-title'>" + title + "</div>");
	}
	
	public void addList() {
		panel.add(list);
		
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public ListStore<DWCodesModelData> getStore() {
		return store;
	}

	public ListView<DWCodesModelData> getList() {
		return list;
	}

	public String getTitle() {
		return title;
	}

	public String getSelectionType() {
		return selectionType;
	}



	
	
	private native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-view-item" qtip="{label}" qtitle="">{label}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;  
	
	
	public static native boolean getMouseStatus()/*-{
	
  	var isMouseDown = $wnd.mouseStatus;
  	
  	return isMouseDown;
	
	}-*/;
	
	private native static void checkMouse() /*-{ 
	
		var isMouseDown = $wnd.mouseStatus;
		$wnd.document.body.onmousedown = function() {
			    $wnd.mouseStatus = true;
			    console.log("onmousedown: " + $wnd.mouseStatus );
			};
			
		$wnd.document.body.onmouseup = function() {
			   $wnd.mouseStatus = false;
			    console.log("onmouseup: " + $wnd.mouseStatus);
			};
	}-*/;
	
	
	
}
