package org.fao.fenix.web.modules.dataviewer.client.control.faostat.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.objects.FAOSTATPivotTableController;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.analysis.FAOSTATAnalysis;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownload;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadSelectorPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot.FAOSTATDownloadTablePanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearch;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchAdvancedPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchClusterResult;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchClusterResults;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchFilter;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchOracleOutput;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchResults;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchSingleResult;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.search.FAOSTATSearchTextBox;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeMultiselectionFilter;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeMultiselectionSelectedItem;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstantsFilters;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchResultVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchResultsVO;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;



import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Items;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;



public class FAOSTATAnalysisController extends FAOSTATDownloadController {

	
	public static void callSearchViewAgent(FAOSTATCurrentView view) {
		
		new FAOSTATAnalysis().build();

//		RootPanel.get("MAIN_CONTENT").add(new FAOSTATAnalysis().build(view));
	}
	
	public static Listener<ComponentEvent> callSearchView(final FAOSTATCurrentView view) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				callSearchViewAgent(view);
			}
		};
	}



}
