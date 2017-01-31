package org.fao.fenix.web.modules.dataviewer.client.view.faostat.home;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.utils.FAOSTATLoadingPanel;
import org.fao.fenix.web.modules.dataviewer.common.services.DataViewerServiceEntry;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.DWFAOSTATQueryVO;
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

public class FAOSTATDatabaseUpdatesPanel extends LayoutContainer {

	private final VerticalPanel wrapper;
	
	private final static String WIDTH = "240px";
	
	private final static String HEIGHT = "290px";
	
	private final static int SPACING = 2;
	
	
	public FAOSTATDatabaseUpdatesPanel() {
		wrapper = new VerticalPanel();
		wrapper.setLayout(new FillLayout());
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setSize(WIDTH, HEIGHT);
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		add(new Html("<div class='news_text'>" + FAOSTATLanguage.print().latestUpdatedDomains() + "</div>"));
		add(wrapper);
		wrapper.add(new FAOSTATLoadingPanel().buildWaitingPanel("", WIDTH, HEIGHT, false));
		build();
	}
	
	private void build() {
		DWFAOSTATQueryVO qvo = new DWFAOSTATQueryVO();
		FAOSTATConstants.setFAOSTATDBSettings(qvo);
		
		try{
			DataViewerServiceEntry.getInstance().getLatestDatabaseUpdates(qvo, FAOSTATConstants.faostatLanguage, FAOSTATLanguage.print().getString(FAOSTATConstants.faostatLanguage), new AsyncCallback<Map<String, List<String>>>() {
				public void onSuccess(Map<String, List<String>> m) {	
					wrapper.removeAll();
					
					for (Map.Entry<String, List<String>> entry : m.entrySet()) {
					    String grp = entry.getKey();
					    List<String> domains = entry.getValue();
					    wrapper.add(buildItem(grp, domains));
					  
					}
					wrapper.add(buildReleaseCalendarItem());
					
					wrapper.getLayout().layout();
				
				}
				@Override
				public void onFailure(Throwable E2) {
					FenixAlert.error(BabelFish.print().error(), E2.getMessage());
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
   private VerticalPanel buildItem(String group, List<String> domains) {
		
		VerticalPanel p = new VerticalPanel();
		p.setTableWidth("100%");
		p.setSpacing(SPACING);
		
		p.add(new Html("<b><div style='color: #1B65A4; font-family: verdana; font-size: 8pt;margin-left: 1em;padding-top:5px;'>"+group+"</div></b>"));
		
		for(String domain: domains){
			p.add(new Html("<div style='color: #000000; font-family: verdana; font-size: 8pt; padding-left:1.2em;padding-top:4px;'><img src='dataviewer-images/blue_circle_bullet.png'/> "+domain+"</div>"));	
		}
		
		return p;
	}
   
   private VerticalPanel buildReleaseCalendarItem() {
		
		VerticalPanel p = new VerticalPanel();
		p.setTableWidth("100%");
		p.setSpacing(SPACING);
		
		p.add(new Html("<div style='color: #1B65A4; font-family: verdana; font-size: 8pt;margin-left: 1em;padding-top:10px;'><img src='dataviewer-images/calendar.png' onClick=\"window.open('faostatReleaseCalendar.html')\"/><a href='#' class='calendar-link' onClick=\"window.open('faostatReleaseCalendar.html')\"> <b>"+FAOSTATLanguage.print().releaseCalendar()+"</b></a></div>"));
		
		return p;
	}
	
}