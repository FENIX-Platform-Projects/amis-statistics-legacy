package org.fao.fenix.web.modules.amis.client.view.home;

import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.view.menu.AMISMainMenu;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentDatasetView;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;

public class AMISDatasourceSelectorPanel {
	
	ContentPanel panel;
	
	ContentPanel datasetOutputPanel;

	ClickHtml ccbs;

	ClickHtml psd;
	
	AMISCcbs amisCcbs;
	
	AMISPsd amisPsd;
	
	AMISCodesModelData selectedArea;
	AMISCodesModelData selectedProduct;
	
	AMISMainMenu mainMenu;

	public AMISDatasourceSelectorPanel(AMISCodesModelData selectedArea, AMISCodesModelData selectedProduct) {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);
		panel.setStyleAttribute("backgroundColor", "#d7d6d4");
		
		
		datasetOutputPanel = new ContentPanel();
		datasetOutputPanel.setBodyBorder(false);
		datasetOutputPanel.setHeaderVisible(false);
		datasetOutputPanel.setStyleAttribute("backgroundColor", "#d7d6d4");
		
		this.selectedArea = selectedArea;
		this.selectedProduct = selectedProduct;
		
		amisCcbs = new AMISCcbs();		
		amisPsd = new AMISPsd();
	}
	
//	public AMISDatasourceSelectorPanel(AMISCodesModelData selectedArea, AMISCodesModelData selectedProduct, AMISMainMenu mainMenu) {
//		panel = new ContentPanel();
//		panel.setBodyBorder(false);
//		panel.setHeaderVisible(false);
//		panel.setAutoHeight(true);
//		panel.setStyleAttribute("backgroundColor", "#d7d6d4");
//		
//		
//		datasetOutputPanel = new ContentPanel();
//		datasetOutputPanel.setBodyBorder(false);
//		datasetOutputPanel.setHeaderVisible(false);
//		datasetOutputPanel.setStyleAttribute("backgroundColor", "#d7d6d4");
//		
//		this.selectedArea = selectedArea;
//		this.selectedProduct = selectedProduct;
//		
//		amisCcbs = new AMISCcbs(mainMenu);		
//		amisPsd = new AMISPsd(mainMenu);
//		
//		this.mainMenu = mainMenu;
//	}

	
	public ContentPanel build() {

		HorizontalPanel p = new HorizontalPanel();
		p.setHeight(45);
		p.setBorders(false);
		p.setStyleAttribute("backgroundColor", "#d7d6d4");
		
		defaultStyles();
		p.add(ccbs);

        if(!this.selectedArea.getCode().equals("999000")) {   // Check if World Selected
           if(p.getItem(2) == null)
             p.add(psd);
        } else {
            if(p.getItem(2) != null)
               p.remove(psd);
        }

		p.add(FormattingUtils.addHSpace(5));

		setListeners();

		panel.add(p);
		
		datasetOutputPanel.add(amisCcbs.build(this, this.selectedProduct));
		
		panel.add(datasetOutputPanel);

		return panel;
	}

	private void defaultStyles(){
		
		ccbs = new ClickHtml();
//		ccbs.setHtml("FAO-CBS");
        ccbs.setHtml("FAO-AMIS");
		ccbs.addStyleName("dataset_tab_selected");
		ccbs.setToolTip("FAO-AMIS Commodity Balance Sheet (CBS)");
		
		psd = new ClickHtml();
		psd.setHtml("USDA-PSD");
		psd.addStyleName("dataset_tab_unselected");
		psd.setToolTip("USDA-Production, Supply and Distribution database (PSD)");

	}

	private void setListeners() {
		ccbs.addListener(Events.OnClick, setTab(ccbs, AMISCurrentDatasetView.CBS, this));
		psd.addListener(Events.OnClick, setTab(psd, AMISCurrentDatasetView.PSD, this));
	}

	private void resetStyles() {
		ccbs.removeStyleName("dataset_tab_unselected");
		ccbs.removeStyleName("dataset_tab_selected");
		ccbs.addStyleName("dataset_tab_unselected");

		psd.removeStyleName("dataset_tab_selected");
		psd.removeStyleName("dataset_tab_unselected");
		psd.addStyleName("dataset_tab_unselected");
	}

	public Listener<ComponentEvent> setTab(final ClickHtml html, final AMISCurrentDatasetView dataset, final AMISDatasourceSelectorPanel dsp) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				resetStyles();

				html.removeStyleName("dataset_tab_unselected");
				html.addStyleName("dataset_tab_selected");
					
				AMISController.openDatasetView(dataset, datasetOutputPanel, dsp);
				
			}
		};
	}


	public AMISCcbs getCCBSPanel() {
		return amisCcbs;
	}

	public AMISCodesModelData getSelectedArea() {
		return selectedArea;
	}

	public void setSelectedArea(AMISCodesModelData selectedArea) {
		this.selectedArea = selectedArea;
	}

	public AMISCodesModelData getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(AMISCodesModelData selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public ContentPanel getDatasetOutputPanel() {
		return datasetOutputPanel;
	}


	public void setDatasetOutputPanel(ContentPanel datasetOutputPanel) {
		this.datasetOutputPanel = datasetOutputPanel;
	}


	public AMISCcbs getAmisCcbs() {
		return amisCcbs;
	}


	public void setAmisCcbs(AMISCcbs amisCcbs) {
		this.amisCcbs = amisCcbs;
	}


	public AMISPsd getAmisPsd() {
		return amisPsd;
	}


	public void setAmisPsd(AMISPsd amisPsd) {
		this.amisPsd = amisPsd;
	}


}

