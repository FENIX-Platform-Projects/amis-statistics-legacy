package org.fao.fenix.web.modules.amis.client.view.home;

import org.fao.fenix.web.modules.amis.client.view.utils.AMISFiltersPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.AMISParametersPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.TitlePanel;
import org.fao.fenix.web.modules.amis.common.vo.AMISSettingsVO;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

public class AMISDatasource extends LayoutContainer {

	    ContentPanel panel;

		TitlePanel titlePanel;

		AMISParametersPanel parametersPanel;

		AMISFiltersPanel filtersPanel;

		ContentPanel centerPanel;

		BorderLayoutData centerData;

		Button exportButton;

		Button tableButton;

		AMISSettingsVO settings;
		
		AMISDatasourceSelectorPanel dataSourceSelectionPanel;


		public AMISDatasource() {
			panel = new ContentPanel();
			panel.setBodyBorder(false);
			panel.setHeaderVisible(false);
			panel.setStyleAttribute("backgroundColor", "#FFFFFF");


			centerPanel = new ContentPanel();
			centerPanel.setBodyBorder(false);
			centerPanel.setHeaderVisible(false);
				

			centerData = new BorderLayoutData(LayoutRegion.CENTER);
			centerData.setMargins(new Margins(5));

			filtersPanel = new AMISFiltersPanel();

			settings = new AMISSettingsVO();

			//parametersPanel = new AMISParametersPanel();
			
			titlePanel = new TitlePanel();

		}


		public ContentPanel getPanel() {
			return panel;
		}


		public ContentPanel getCenterPanel() {
			return centerPanel;
		}

		public AMISFiltersPanel getFiltersPanel() {
			return filtersPanel;
		}

		public AMISSettingsVO getSettings() {
			return settings;
		}

		public TitlePanel getTitlePanel() {
			return titlePanel;
		}

		public AMISParametersPanel getParametersPanel() {
			return parametersPanel;
		}

	
		public AMISDatasourceSelectorPanel getDataSourceSelectionPanel() {
			return dataSourceSelectionPanel;
		}


		public void setDataSourceSelectionPanel(
				AMISDatasourceSelectorPanel dataSourceSelectionPanel) {
			this.dataSourceSelectionPanel = dataSourceSelectionPanel;
		}
		
}
