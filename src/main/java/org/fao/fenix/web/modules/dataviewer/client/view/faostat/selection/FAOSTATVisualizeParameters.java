package org.fao.fenix.web.modules.dataviewer.client.view.faostat.selection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeByDomainConstants;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;

public class FAOSTATVisualizeParameters {
	
	public static VerticalPanel buildParameter(String title, Map<String, String> values) {
		
		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		HorizontalPanel titlePanel = new HorizontalPanel();
		IconButton arrow = new IconButton();
		arrow.addStyleName("arrow-right_disabled");
		
		titlePanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		titlePanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		
		
		
		titlePanel.add(arrow);
		titlePanel.add(DataViewerClientUtils.addHSpace(2));
		ClickHtml titleHtml = new ClickHtml();
		titleHtml.setHtml("<div class='visualize_parameters_item_title'> "+FAOSTATLanguage.print().selected()+ " " + title + "</div>");

		ClickHtml showhide = new ClickHtml();
		showhide.setHtml("<div class='visualize_parameters_title_showhide'>"+FAOSTATLanguage.print().show()+"</div>");
		//		Html html = new Html("<div class='visualize_parameters_item_title'>" + BabelFish.print().getString(title) + "</div>");


		
		titlePanel.add(showhide);
		titlePanel.add(DataViewerClientUtils.addHSpace(5));
		titlePanel.add(titleHtml);
			
		panel.add(titlePanel);
			
		panel.add(DataViewerClientUtils.addVSpace(3));
			
		HorizontalPanel contentPanel = new HorizontalPanel();	
		
//		panel.setWidth(FAOSTATVisualizeByDomainConstants.CENTER_PANEL_WIDTH - 30);
			
		contentPanel.add(DataViewerClientUtils.addHSpace(20));
		HorizontalPanel valuesPanel = new HorizontalPanel();
		LinkedHashMap<String, String> sorted = FAOSTATMainController.sortByValuesASC(values);			
		int i = 0;
		StringBuffer param = new StringBuffer();
		param.append("<div class='visualize_parameters_item'>");
		for(String key : sorted.keySet()) {
			param.append(sorted.get(key));
			if ( i < sorted.size() -1 )
				param.append(" | ");
			i++;
		}
		param.append("</div>");
		Html value = new Html(param.toString());
		valuesPanel.add(value);
			
		contentPanel.add(valuesPanel);
		
		contentPanel.hide();
	
		panel.add(contentPanel);
		
		
		titleHtml.addListener(Events.OnClick, showhideParametersPanel(contentPanel, arrow, showhide));
		showhide.addListener(Events.OnClick, showhideParametersPanel(contentPanel, arrow, showhide));
		arrow.addListener(Events.OnClick, showhideParametersPanel(contentPanel, arrow, showhide));

		return panel;
	}
	
	private static Listener<ComponentEvent> showhideParametersPanel(final HorizontalPanel panel, final IconButton icon, final ClickHtml showhide) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				
				if ( panel.isVisible() ) {
					panel.hide();
					icon.removeStyleName("arrow-down_disabled");
					icon.addStyleName("arrow-right_disabled");
					showhide.setHtml("<div class='visualize_parameters_title_showhide'>"+FAOSTATLanguage.print().show()+"</div>");

		        } else {       	
					showhide.setHtml("<div class='visualize_parameters_title_showhide'>"+FAOSTATLanguage.print().hide()+"</div>");
					icon.removeStyleName("arrow-right_disabled");
		        	icon.addStyleName("arrow-down_disabled");
		        	panel.show();		        	
		        }
			}
		};
	}

}
