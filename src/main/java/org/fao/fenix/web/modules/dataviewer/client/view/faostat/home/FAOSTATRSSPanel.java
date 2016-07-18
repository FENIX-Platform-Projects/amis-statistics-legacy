package org.fao.fenix.web.modules.dataviewer.client.view.faostat.home;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class FAOSTATRSSPanel extends LayoutContainer {

	private final VerticalPanel wrapper;
	
	private final static String WIDTH = "375px";
	
//	private final static String WIDTH = "310px";


	private final static String HEIGHT = "290px";
	
	private final static int SPACING = 2;
	
	private final static int RSS_ELEMENTS = 3;
	
	public FAOSTATRSSPanel() {
		wrapper = new VerticalPanel();
		wrapper.setLayout(new FillLayout());
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setSize(WIDTH, HEIGHT);
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		add(new Html("<div class='news_text'>" + FAOSTATLanguage.print().events() + "</div>"));
		add(wrapper);
		wrapper.add(new FAOSTATLoadingPanel().buildWaitingPanel("", WIDTH, HEIGHT, false));
		build();
	}
	
	private void build() {
		try {
			DataViewerServiceEntry.getInstance().read(FAOSTATConstants.faostatLanguage, RSS_ELEMENTS, new AsyncCallback<List<Map<String,String>>>() {
				public void onSuccess(List<Map<String, String>> l) {
					wrapper.removeAll();
					
					for (Map<String, String> m : l)
						wrapper.add(buildItem(m));
					wrapper.getLayout().layout();
				}
				@Override
				public void onFailure(Throwable E2) {
					FenixAlert.error(BabelFish.print().error(), E2.getMessage());
				}
			});
		} catch (FenixGWTException E1) {
			FenixAlert.error(BabelFish.print().error(), E1.getMessage());
		}
	}
	
	private VerticalPanel buildItem(Map<String, String> m) {
		
		VerticalPanel p = new VerticalPanel();
		p.setTableWidth("100%");
		p.setSpacing(SPACING);
		
		HorizontalPanel tp = new HorizontalPanel();
		tp.setSpacing(SPACING);
		tp.add(new Html("<b><div style='color: #1B65A4; font-family: verdana; font-size: 8pt;'><a target='_blank' href='" + 
						m.get("LINK") + 
						"'><div style='font-size: 8pt;'>" + 
						m.get("TITLE") + 
						"</a></div></b>"));
		p.add(tp);
		
		HorizontalPanel dp = new HorizontalPanel();
		dp.setSpacing(SPACING);
		String desc = m.get("DESCRIPTION").replaceAll("<p>", "").replaceAll("</p>", "");	
		dp.add(new Html("<div style='font-family: verdana; font-size: 8pt;'>" + desc + "</div>"));
		p.add(dp);
		
		return p;
	}
	
}