package org.fao.fenix.web.modules.amis.client.view.home;

import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

public class AMISPsd extends AMISDatasource {

	    AMISPsdPanel psdPanel;
	    AMISCodesModelData selectedProduct;
	    
		public AMISPsd() {
			super();
			psdPanel = new AMISPsdPanel();

		}
		
//		public AMISPsd(AMISMainMenu mainMenu) {
//			super();
//			psdPanel = new AMISPsdPanel();
//			this.mainMenu = mainMenu;
//		}

		public ContentPanel build(AMISDatasourceSelectorPanel datasetSelectorPanel, AMISCodesModelData selectedProduct) {
			this.selectedProduct = selectedProduct;
			setDataSourceSelectionPanel(datasetSelectorPanel);
			
			panel.add(buildMainPanel());
			

			return panel;
		}

		private HorizontalPanel buildMainPanel(){
			HorizontalPanel p = new HorizontalPanel();
			p.setSpacing(10);
			p.setStyleAttribute("backgroundColor", "#FFFFFF");
			p.add(buildCenterPanel());

			return p;
		}

		private VerticalPanel buildCenterPanel() {
			VerticalPanel p = new VerticalPanel();
			p.setStyleAttribute("backgroundColor", "#FFFFFF");

			// filters panel
			p.add(filtersPanel.build(null));

			// title panel
			p.add(titlePanel.build(false));

			p.add(buildView());

			ContentPanel c = new ContentPanel();
			c.setHeaderVisible(false);
			c.setBodyBorder(false);
			c.setStyleAttribute("backgroundColor", "#FFFFFF");
			c.add(centerPanel, centerData);
			p.add(c);
			return p;
		}


		private ContentPanel buildView() {
			return psdPanel.build(this);
		}

		public AMISCodesModelData getSelectedProduct() {
			return selectedProduct;
		}

}
