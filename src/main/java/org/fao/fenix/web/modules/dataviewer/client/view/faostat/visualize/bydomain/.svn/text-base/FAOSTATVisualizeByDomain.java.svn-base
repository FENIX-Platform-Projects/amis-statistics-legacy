package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain;

import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualize;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATVisualizeParametersPanel;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATVisualizeTitlePanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATCurrentView;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeSettingsVO;


import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

public class FAOSTATVisualizeByDomain extends FAOSTATVisualize {
		
		FAOSTATVisualizeDomainPanel domains;
		Button exportButton;
		Button tableButton;
		VerticalPanel domainsPanel;

		public FAOSTATVisualizeByDomain() {
			panel = new ContentPanel();			
			panel.setBodyBorder(false);
			panel.setHeaderVisible(false);	
			panel.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.MAIN_CONTENT_MIN_HEIGHT) + "px");
			
			domainsPanel = new VerticalPanel();	
			centerPanel = new ContentPanel();
			centerPanel.setBodyBorder(false);
			centerPanel.setHeaderVisible(false);
			domains = new FAOSTATVisualizeDomainPanel();
			centerData = new BorderLayoutData(LayoutRegion.CENTER);  
			centerData.setMargins(new Margins(5));  
			filtersPanel = new FAOSTATVisualizeByDomainFilters();
			settings = new FAOSTATVisualizeSettingsVO();
			parametersPanel = new FAOSTATVisualizeParametersPanel();
			titlePanel = new FAOSTATVisualizeTitlePanel();
			
			// GOOGLE ANALYTCS
			googleCategory = FAOSTATCurrentView.VISUALIZE_BY_DOMAIN.toString();
		}

		public ContentPanel build() {
			panel.add(new Html(""));
			panel.add(buildMainPanel());
			return panel;
		}
		
		private HorizontalPanel buildMainPanel(){
			HorizontalPanel p = new HorizontalPanel();
			p.setSpacing(10);
			p.add(buildDomains());
			p.add(buildCenterPanel());
			return p;
		}
		
//		private VerticalPanel buildCenterPanel() {
		private ContentPanel buildCenterPanel() {
//			VerticalPanel p = new VerticalPanel();
			ContentPanel p = new ContentPanel();
			p.setHeaderVisible(false);
			p.setSize("900px", "645px");
			p.setScrollMode(Scroll.AUTO);
			p.setBorders(false);
			p.setBodyBorder(false);
			
			// title panel
			p.add(titlePanel.build());
			p.add(DataViewerClientUtils.addVSpace(10));

			// filters panel
//			p.add(filtersPanel.build(null));
			p.setTopComponent(filtersPanel.build(null));
			
			// parameters panel
			p.add(parametersPanel.build(false));
//			p.add(addVSpace(10));
			ContentPanel c = new ContentPanel();
			c.setHeaderVisible(false);
			c.setBodyBorder(false);
			c.setBorders(false);
			c.add(centerPanel, centerData);
			p.add(c);
			return p;
		}

		private ContentPanel buildDomains() {
			// TODO: Dynamic
			return domains.build(this, "Q ");
		}

		public ContentPanel getPanel() {
			return panel;
		}

		public FAOSTATVisualizeDomainPanel getDomains() {
			return domains;
		}

		public ContentPanel getCenterPanel() {
			return centerPanel;
		}

		public FAOSTATVisualizeByDomainFilters getFiltersPanel() {
			return filtersPanel;
		}

		public FAOSTATVisualizeSettingsVO getSettings() {
			return settings;
		}

		public FAOSTATVisualizeTitlePanel getTitlePanel() {
			return titlePanel;
		}

		public FAOSTATVisualizeParametersPanel getParametersPanel() {
			return parametersPanel;
		}

}
