package org.fao.fenix.web.modules.amis.client.view.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.amis.client.view.utils.filters.AMISFilter;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.lang.AMISLanguage;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISProductionInterfaceConstants;


import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;


public class AMISParametersPanel {

	ContentPanel panel;

	ContentPanel parameters;

	public AMISParametersPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
	
		parameters = new ContentPanel();
		parameters.setHeaderVisible(false);
		parameters.setBodyBorder(false);
	}

//	public ContentPanel build() {
//		return panel;
//	}


	public ContentPanel build(Boolean buildWaitingPanel) {
		panel.removeAll();
//
//		if ( buildWaitingPanel ) {
//			panel.add(buildTitle());
//
//			panel.add(DataViewerClientUtils.addVSpace(5));
//
////			panel.add(new FAOSTATLoadingPanel().buildWaitingPanel("Loading", "300px", "50px", false));
//		}

		return panel;
	}


	public void build(List<AMISFilter> filters) {

		panel.removeAll();

//		panel.add(buildTitle());

		panel.add(FormattingUtils.addVSpace(5));

		parameters.removeAll();

		VerticalPanel v = new VerticalPanel();
		for(AMISFilter filter : filters ) {
//			panel.add(buildParameter(filter.getTitle(), filter.getSelectedCodes()));
//			panel.add(DataViewerClientUtils.addVSpace(5));
			if ( filter.getSelectedCodes().size() > 1 ) {
				v.add(buildParameter(filter.getTitle(), filter.getSelectedCodes()));
				v.add(FormattingUtils.addVSpace(5));
			}
		}

		parameters.add(FormattingUtils.addHSpace(10));
		parameters.add(v);

		panel.add(parameters);

		// default is hidden
//		parameters.hide();
//		parameters.el().slideIn(Direction.DOWN, FxConfig.NONE);
	}

	private HorizontalPanel buildTitle() {
		HorizontalPanel panel = new HorizontalPanel();

		Html title = new Html("<div class='visualize_parameters_title>Parameters</div>");

		ClickHtml showhide = new ClickHtml();
		showhide.setHtml("<div class='visualize_parameters_title_showhide'>(show/hide):</div>");

		showhide.addListener(Events.OnClick, showhideParametersPanel());


		panel.add(title);
		panel.add(FormattingUtils.addHSpace(5));
		panel.add(showhide);

		return panel;
	}


	private VerticalPanel buildParameter(String title, HashMap<String, String> values) {

		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);

		HorizontalPanel titlePanel = new HorizontalPanel();
		IconButton arrow = new IconButton();
		arrow.addStyleName("arrow-right_disabled");

		titlePanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		titlePanel.setVerticalAlign(VerticalAlignment.MIDDLE);




		titlePanel.add(arrow);
		titlePanel.add(FormattingUtils.addHSpace(2));
		ClickHtml titleHtml = new ClickHtml();
		titleHtml.setHtml("<div class='visualize_parameters_item_title'> Selected " + AMISLanguage.print().getString(title) + "</div>");

		ClickHtml showhide = new ClickHtml();
		showhide.setHtml("<div class='visualize_parameters_title_showhide'>Show</div>");
		//		Html html = new Html("<div class='visualize_parameters_item_title'>" + BabelFish.print().getString(title) + "</div>");



		titlePanel.add(showhide);
		titlePanel.add(FormattingUtils.addHSpace(5));
		titlePanel.add(titleHtml);

		panel.add(titlePanel);

		panel.add(FormattingUtils.addVSpace(3));

		HorizontalPanel contentPanel = new HorizontalPanel();

		panel.setWidth(AMISProductionInterfaceConstants.CENTER_PANEL_WIDTH - 30);

		contentPanel.add(FormattingUtils.addHSpace(20));
		HorizontalPanel valuesPanel = new HorizontalPanel();
		LinkedHashMap<String, String> sorted = AMISController.sortByValuesASC(values);
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



//	private HorizontalPanel buildParameter(String title, HashMap<String, String> values) {
//		HorizontalPanel panel = new HorizontalPanel();
//
//		Html html = new Html("<div class='visualize_parameters_item_title'>" + title + "</div>");
////		Html html = new Html("<div class='visualize_parameters_item_title'>" + BabelFish.print().getString(title) + "</div>");
//		html.setWidth(170);
//		panel.add(html);
//
//		panel.add(DataViewerClientUtils.addHSpace(10));
//
//		HorizontalPanel valuesPanel = new HorizontalPanel();
//
//		LinkedHashMap<String, String> sorted = FAOSTATMainController.sortByValuesASC(values);
//
//		int i = 0;
//		StringBuffer param = new StringBuffer();
//		param.append("<div class='visualize_parameters_item'>");
//		for(String key : sorted.keySet()) {
//			param.append(sorted.get(key));
//			if ( i < sorted.size() -1 )
//				param.append(" | ");
//			i++;
//		}
//		param.append("</div>");
//		Html value = new Html(param.toString());
//		valuesPanel.add(value);
//
//		panel.add(valuesPanel);
//		return panel;
//	}


	public Listener<ComponentEvent> showhideParametersPanel() {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {

				if ( parameters.isVisible() ) {
//					parameters.hide();
					parameters.el().slideOut(Direction.UP, FxConfig.NONE);
//					panel.layout();
		        } else {
		        	parameters.el().slideIn(Direction.DOWN, FxConfig.NONE);
//		        	panel.layout();
//					parameters.show();
		        }
			}
		};
	}

	public Listener<ComponentEvent> showhideParametersPanel(final HorizontalPanel panel, final IconButton icon, final ClickHtml showhide) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {

				if ( panel.isVisible() ) {
					panel.hide();
					icon.removeStyleName("arrow-down_disabled");
					icon.addStyleName("arrow-right_disabled");
					showhide.setHtml("<div class='visualize_parameters_title_showhide'>Show</div>");

		        } else {
					showhide.setHtml("<div class='visualize_parameters_title_showhide'>Hide</div>");
					icon.removeStyleName("arrow-right_disabled");
		        	icon.addStyleName("arrow-down_disabled");
		        	panel.show();
		        }
			}
		};
	}


	public ContentPanel getPanel() {
		return panel;
	}


}
