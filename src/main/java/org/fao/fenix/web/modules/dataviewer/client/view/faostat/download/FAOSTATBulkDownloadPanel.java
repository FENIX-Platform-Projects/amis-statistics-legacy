package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.dataviewer.common.vo.BulkDownloadVO;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;

public class FAOSTATBulkDownloadPanel extends LayoutContainer {
	
	public HorizontalPanel wrapper;
	
	public VerticalPanel left;
	
	public VerticalPanel right;
	
	private final static String WIDTH = "500px";
	
	public FAOSTATBulkDownloadPanel() {
		wrapper = new HorizontalPanel();
		wrapper.setLayout(new FillLayout());
		wrapper.setSpacing(5);
		wrapper.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.getMainContentHeight(true)) + "px");
		left = new VerticalPanel();
		left.setLayout(new FillLayout());
		left.setSpacing(5);
		left.setWidth(WIDTH);
		right = new VerticalPanel();
		right.setLayout(new FillLayout());
		right.setSpacing(5);
		right.setWidth(WIDTH);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		wrapper.removeAll();
		HTML html = new HTML("<iframe frameborder='0' style='width: 1000px; height: 680px;' src='../bulk-download/BulkDownload.html?language=" + lang() + "'>Your browser does not support iframes.</iframe>");
		wrapper.add(html);
		wrapper.getLayout().layout();
		add(wrapper);
	}
	
	private String lang() {
		String l = FAOSTATConstants.faostatLanguage;
		if (l.equalsIgnoreCase("F"))
			return("fr");
		if (l.equalsIgnoreCase("S"))
			return("es");
		return "en";
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