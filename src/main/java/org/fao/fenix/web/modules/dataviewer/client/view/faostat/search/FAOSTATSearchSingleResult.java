package org.fao.fenix.web.modules.dataviewer.client.view.faostat.search;

import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.search.FAOSTATSearchController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot.FAOSTATDownloadTablePanel;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;

public class FAOSTATSearchSingleResult {
	
	ContentPanel panel;
	
	Button download;
	
	Map<String, String> groups;
	
	Map<String, String> domains;
	
	Map<String, String> elements;
	
	Map<String, String> items;
	
	FAOSTATDownloadTablePanel tablePanel;

	Integer groupLeftMargin = 10;
	
	Integer othersLeftMargin = 35;
	Integer furtherIndentationLeftMargin = 45;
	
	public FAOSTATSearchSingleResult() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
	
		tablePanel = new FAOSTATDownloadTablePanel();
	}


	public ContentPanel build(FAOSTATSearchTextBox searchTextBox, Map<String, String> groups, Map<String, String> domains, Map<String, String> elements, Map<String, String> items) {
		this.groups = groups;
		this.domains = domains;
		this.items = items;
		this.elements = elements;
		
		panel.add(DataViewerClientUtils.addVSpace(5));
		panel.add(addTitle());
		panel.add(DataViewerClientUtils.addVSpace(3));
		panel.add(addElements());
		panel.add(DataViewerClientUtils.addVSpace(9));
		panel.add(addButtons(searchTextBox));
		
		panel.add(DataViewerClientUtils.addVSpace(3));
		panel.add(tablePanel.build());
				
		return panel;
	}
	

	private HorizontalPanel addGroup(Map<String, String> map) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		panel.add(DataViewerClientUtils.addHSpace(groupLeftMargin));

		String title = null;
		for(String key : map.keySet()) {
			title = map.get(key);
		}
		

		Html bullet = new Html("<IMG SRC='dataviewer-images/turquoise_bullet.png'>");

		
		Html html = new Html("<div class='search_result_grouptitle'> " + title + "</div>");
		
		panel.add(bullet);
		panel.add(DataViewerClientUtils.addHSpace(5));
		panel.add(html);
		return panel;
	}
	
	private HorizontalPanel addDomain(Map<String, String> map) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(DataViewerClientUtils.addHSpace(othersLeftMargin));

		
		String title = null;
		for(String key : map.keySet()) {
			title = map.get(key);
		}
		
		Html html = new Html("<div class='search_result_domaintitle'> " + title + " </div>");
		
		panel.add(html);
		return panel;
	}
	
	
	private HorizontalPanel addElements(Map<String, String> map) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(DataViewerClientUtils.addHSpace(othersLeftMargin));
		
		
		StringBuffer title = new StringBuffer();
		int i = 0;
		for(String key : map.keySet()) {
			title.append(map.get(key) + " ");
			if ( i < map.size() - 1) {
				title.append(" - ");
			} 
			i++;
		}
		
		Html html = new Html("<div class='search_result_elements'> " + title + " </div>");

		
		panel.add(html);
		return panel;
	}
	

	
	private VerticalPanel addTitle() {
		VerticalPanel panel = new VerticalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
//		panel.add(addGroup(items));
		panel.add(addGroup(groups));
		panel.add(DataViewerClientUtils.addVSpace(3));
		panel.add(addDomain(domains));
		return panel;
	}
	
	private HorizontalPanel addElements() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(addElements(elements));
		return panel;
	}
	
	private HorizontalPanel addButtons(FAOSTATSearchTextBox searchTextBox) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		panel.add(DataViewerClientUtils.addHSpace(furtherIndentationLeftMargin));
		panel.add(addDownload(searchTextBox));
		panel.add(DataViewerClientUtils.addHSpace(40));
		panel.add(addShowProview(searchTextBox));
		return panel;
	}
	
	private HorizontalPanel addDownload(FAOSTATSearchTextBox searchTextBox) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);

		IconButton downloadButton = new IconButton("downloadIcon");
		downloadButton.setWidth(19);
		//downloadButton.setHeight(15);
		downloadButton.addListener(Events.OnClick, FAOSTATSearchController.quickDownloadCSV(searchTextBox, this));
		
		ClickHtml html = new ClickHtml();
		html.setHtml("<div class='search_result_export'>"+FAOSTATLanguage.print().download()+"</div>");
		html.addListener(Events.OnClick, FAOSTATSearchController.quickDownloadCSV(searchTextBox, this));
		
		panel.add(downloadButton);
		panel.add(DataViewerClientUtils.addHSpace(5));
		panel.add(html);
		
		return panel;
	}
	
	private HorizontalPanel addShowProview(FAOSTATSearchTextBox searchTextBox) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		IconButton previewButton = new IconButton("previewIcon");
		previewButton.setWidth(19);
		//previewButton.setHeight(10);
		previewButton.addListener(Events.OnClick, FAOSTATSearchController.showTable(searchTextBox, this));
		
		ClickHtml html = new ClickHtml();
		html.setHtml("<div class='search_result_preview'>"+FAOSTATLanguage.print().dataPreview()+"</div>");
		
		html.addListener(Events.OnClick, FAOSTATSearchController.showTable(searchTextBox, this));
		
		panel.add(previewButton);
		panel.add(DataViewerClientUtils.addHSpace(5));
		panel.add(html);
		
		return panel;
	}


	public Map<String, String> getGroups() {
		return groups;
	}


	public Map<String, String> getDomains() {
		return domains;
	}


	public Map<String, String> getElements() {
		return elements;
	}


	public Map<String, String> getItems() {
		return items;
	}


	public FAOSTATDownloadTablePanel getTablePanel() {
		return tablePanel;
	}


	public ContentPanel getPanel() {
		return panel;
	}



	
	

}
