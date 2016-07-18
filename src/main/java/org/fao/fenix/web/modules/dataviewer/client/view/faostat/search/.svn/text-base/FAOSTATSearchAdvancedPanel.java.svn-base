package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;


import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadSelectorAreas;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadSelectorPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters.FAOSTATVisualizeTimerangeFilter;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATSearchConstants;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class FAOSTATSearchAdvancedPanel {
	
	
	public ContentPanel panel;
	
	FAOSTATDownloadSelectorAreas areas;
	
	List<DWCodesModelData> defaultDomainCodes;
	
	FAOSTATVisualizeTimerangeFilter timerangeFilter;
	
	FAOSTATDownloadSelectorPanel years;

	Boolean isBuild = false;
	
	public FAOSTATSearchAdvancedPanel() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);

		
//		defaultDomainCode = new DWCodesModelData("QC", "Crops");
		
		timerangeFilter = new FAOSTATVisualizeTimerangeFilter();
		
		panel.hide();
	}


	
	private ContentPanel buildAreas(List<DWCodesModelData> codes) {
		areas = new FAOSTATDownloadSelectorAreas();
		return areas.build(codes,"300px", "130px", true, true);
	}
	
	private ContentPanel buildYears(DWCodesModelData code) {
		years = new FAOSTATDownloadSelectorPanel();
		return years.build(code, FAOSTATLanguage.print().years(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString(), false, true, "170px", "130px");
	}
	
	public void build() {
		
		if ( !isBuild  ) {
			panel.removeAll();
			
			panel.addStyleName("advanced_search_panel");
			
			VerticalPanel v = new VerticalPanel();
			v.add(DataViewerClientUtils.addVSpace(5));
//			Html html = new Html("<div class='advanced_search_title'>Advanced Search <i> (Please select to filter the results) </i></div>");
//			v.add(html);
			v.add(buildTitle());
			v.add(DataViewerClientUtils.addVSpace(5));
			
			
			HorizontalPanel p = new HorizontalPanel();
			p.add(buildAreas(defaultDomainCodes));
			p.add(DataViewerClientUtils.addHSpace(15));
			
			p.add(buildYears(null));
			
//			p.add(timerangeFilter.build("TIMERANGE", defaultDomainCode));
			
			v.add(DataViewerClientUtils.addVSpace(5));
			v.add(p);
			v.add(DataViewerClientUtils.addVSpace(15));
			
			panel.add(v);
			
			isBuild = true;
		}
			
		panel.show();
	

		
		panel.layout();
	}
	
	private ContentPanel buildTitle() {
		// main panel
		ContentPanel panel = new ContentPanel();
		
		panel.setWidth(FAOSTATSearchConstants.ADVANCED_WIDTH);

		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);

		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleName("content_chart_top_panel");
		

		HorizontalPanel panelL = new HorizontalPanel();		
		HorizontalPanel panelR = new HorizontalPanel();	
		
		Html html = new Html("<div class='advanced_search_title'>"+FAOSTATLanguage.print().advancedSearch()+" <i> ("+FAOSTATLanguage.print().pleaseSelectToFilterResults()+") </i></div>");
		panelL.add(html);
		
		IconButton close = new IconButton("delete");
		close.addSelectionListener(hidePanel());
		panelR.add(close);
		ClickHtml closeHtml = new ClickHtml();
		closeHtml.setHtml("<div class='oraclevalues'>Close</div>");
		closeHtml.addListener(Events.OnClick, hidePanelHtml());
		panelR.add(closeHtml);
		
		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
		
		panel.add(toolBar);
		
		return panel;
	}
	
	private SelectionListener<IconButtonEvent> hidePanel() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				hidePanelAgent();
			}
		};
	}
	
	public Listener<ComponentEvent> hidePanelHtml() {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				hidePanelAgent();
			}
		};
	}
	
	private void hidePanelAgent() {
		panel.hide();
	}
	
	private HorizontalPanel addButtons() {
		HorizontalPanel panel = new HorizontalPanel();
		
		return panel;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public FAOSTATDownloadSelectorAreas getAreas() {
		return areas;
	}

	public FAOSTATVisualizeTimerangeFilter getTimerangeFilter() {
		return timerangeFilter;
	}

	public FAOSTATDownloadSelectorPanel getYears() {
		return years;
	}
	
	
}
