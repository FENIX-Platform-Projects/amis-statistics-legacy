package org.fao.fenix.web.modules.haiti.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.ui.HTML;

public class HaitiRSSPanel {

	private ContentPanel rssPanel;
	
	private TabPanel tabPanel;
	
	private List<String> urls;
	
	private Map<String, String> sourceUrlMap;
	
	private Button refreshButton;
	
	private final String PANEL_HEIGHT = "675px";
	
	private final String PANEL_WIDTH = "1325px";
	
	public HaitiRSSPanel() {
		rssPanel = new ContentPanel();
		rssPanel.setHeight(PANEL_HEIGHT);
		rssPanel.setWidth(PANEL_WIDTH);
		rssPanel.setHeaderVisible(false);
//		rssPanel.setScrollMode(Scroll.AUTO);
		tabPanel = new TabPanel();
		initURLList();
		initSourceUrlMap();
	}
	
	public ContentPanel build() {
		rssPanel.add(tabPanel);
		rssPanel.setBottomComponent(buildButtonPanel(null, null));
		return rssPanel;
	}
	
	public ContentPanel build(String width, String height, String language) {
		rssPanel.setHeight(reduceHeight(height, 0));
		rssPanel.setWidth(width);
		rssPanel.add(tabPanel);
		rssPanel.setBottomComponent(buildButtonPanel(width, language));
		return rssPanel;
	}
	
	public void addNews(List<String> news) {
		for (String n : news) {
			HorizontalPanel newsPanel = buildNewsPanel(n); 
			rssPanel.add(newsPanel);
		}
	}
	
	public void buildTabPanel(Map<String, List<String>> sourceNewsMap, String width, String height) {
		tabPanel.removeAll();
		for (String source : sourceNewsMap.keySet())
			tabPanel.add(buildSourceTabItem(source, sourceNewsMap.get(source), width, height));
	}
	
	private HorizontalPanel buildButtonPanel(String width, String language) {
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setSpacing(10);
		refreshButton = new Button(HaitiLangEntry.getInstance(language).refreshAllNews());
		width = reduceWidth(width, 25);
		if (width != null)
			refreshButton.setMinWidth(Integer.parseInt(width.substring(0, width.length() - 2)));
		buttonPanel.add(refreshButton);
		return buttonPanel;
	}
	
	private TabItem buildSourceTabItem(String source, List<String> news, String width, String height) {
		TabItem tabItem = new TabItem(source);
		tabItem.add(buildNewsSourcePanel(news, width, height));
		return tabItem;
	}
	
	private VerticalPanel buildNewsSourcePanel(List<String> news, String width, String height) {
		VerticalPanel newsSourcePanel = new VerticalPanel();
		newsSourcePanel.setSpacing(10);
		newsSourcePanel.setScrollMode(Scroll.AUTO);
		for (String n : news) {
			HorizontalPanel newsPanel = buildNewsPanel(n); 
			newsSourcePanel.add(newsPanel);
		}
		newsSourcePanel.setWidth(fixWidth(width));
		newsSourcePanel.setHeight(reduceHeight(height, 75));
		return newsSourcePanel;
	}
	
	private String fixWidth(String width) {
		if (width == null) {
			return "1350px";
		} else {
			String fixed = width.substring(0, width.length() - 2);
			return String.valueOf((Integer.parseInt(fixed) - 5)) + "px";
		}
	}
	
	private String fixHeight(String height) {
		if (height == null) {
			return "650px";
		} else {
//			String fixed = height.substring(0, height.length() - 2);
//			return String.valueOf((Integer.parseInt(fixed) + 50)) + "px";
			return height;
		}
	}
	
	private String reduceHeight(String height, int pixelsToRemove) {
		if (height == null) {
			return "650px";
		} else {
			String fixed = height.substring(0, height.length() - 2);
			return String.valueOf((Integer.parseInt(fixed) - pixelsToRemove)) + "px";
		}
	}
	
	private String reduceWidth(String width, int pixelsToRemove) {
		if (width == null) {
			return "1350px";
		} else {
			String fixed = width.substring(0, width.length() - 2);
			return String.valueOf((Integer.parseInt(fixed) - pixelsToRemove)) + "px";
		}
	}
	
	private HorizontalPanel buildNewsPanel(String news) {
		HorizontalPanel newsPanel = new HorizontalPanel();
		newsPanel.setSpacing(10);
		newsPanel.add(new HTML(news));
		return newsPanel;
	}
	
	private void initURLList() {
		urls = new ArrayList<String>();
		urls.add("http://www.reliefweb.int/RWFeed/Feed?Type=RSS20&ID=02-P&cc=hti");
		urls.add("http://www.irinnews.org/irin.xml");
		urls.add("http://newsrss.bbc.co.uk/rss/newsonline_world_edition/americas/rss.xml");
		urls.add("http://feeds.reuters.com/reuters/worldNews?format=xml");
//		urls.add("http://news.google.com/news?cf=all&ned=uk&hl=en&q=haiti&cf=all&output=rss");
	}
	
	private void initSourceUrlMap() {
		sourceUrlMap = new HashMap<String, String>();
		sourceUrlMap.put("Relief Web", "http://www.reliefweb.int/RWFeed/Feed?Type=RSS20&ID=02-P&cc=hti");
		sourceUrlMap.put("IRIN News", "http://www.irinnews.org/irin.xml");
		sourceUrlMap.put("BBC", "http://newsrss.bbc.co.uk/rss/newsonline_world_edition/americas/rss.xml");
		sourceUrlMap.put("Reuters", "http://feeds.reuters.com/reuters/worldNews?format=xml");
//		sourceUrlMap.put("Google News", "http://news.google.com/news?cf=all&ned=uk&hl=en&q=haiti&cf=all&output=rss");
		sourceUrlMap.put("Workstation Datasets", "http://fenix.fao.org:8080/fenix-web/RSS/dataset.xml");
		sourceUrlMap.put("Workstation Texts", "http://fenix.fao.org:8080/fenix-web/RSS/text.xml");
	}

	public List<String> getUrls() {
		return urls;
	}

	public Map<String, String> getSourceUrlMap() {
		return sourceUrlMap;
	}

	public Button getRefreshButton() {
		return refreshButton;
	}
	
}
