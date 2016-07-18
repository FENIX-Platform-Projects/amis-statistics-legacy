package org.fao.fenix.web.modules.amis.client.view.download;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.client.view.download.AMISDownload;
import org.fao.fenix.web.modules.amis.client.view.download.AMISDownloadComboSelectorPanel;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

public class AMISDownloadComboSelectorAreas {

	AMISDownloadComboSelectorPanel selectorPanel;

	ContentPanel panel;

	ClickHtml areas;

	public AMISDownloadComboSelectorAreas() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);

		selectorPanel = new AMISDownloadComboSelectorPanel();
	}

	public ContentPanel build(AMISDownload download, AMISCodesModelData domainFilter, String width, String height) {
		System.out.println("AMISDownloadComboSelectorAreas BUILD");
		HorizontalPanel p = new HorizontalPanel();
		defaultStyles();

		p.add(areas);
		p.add(addHSpace(5));

        areas.addListener(Events.OnClick, AMISDownloadController.reloadComboFilter(download, selectorPanel, domainFilter, AMISConstants.AMIS_FOOD_BALANCE_COUNTRIES.toString(), false, false, width, height));

    	panel.add(p);

		panel.add(selectorPanel.build(download, domainFilter, null, AMISConstants.AMIS_FOOD_BALANCE_COUNTRIES.toString(), false, false, width, height));

		setListners();

		return panel;
	}


    public ContentPanel build(AMISDownload download, String width, String height) {
        System.out.println("AMISDownloadComboSelectorAreas BUILD");
        HorizontalPanel p = new HorizontalPanel();
        defaultStyles();

        p.add(areas);
        p.add(addHSpace(5));

        areas.addListener(Events.OnClick, AMISDownloadController.reloadComboFilter(download, selectorPanel, AMISConstants.AMIS_FOOD_BALANCE_COUNTRIES.toString(), false, false, width, height));

        panel.add(p);

        panel.add(selectorPanel.build(download, "CBS", null, AMISConstants.AMIS_FOOD_BALANCE_COUNTRIES.toString(), false, false, width, height));

        setListners();

        return panel;
    }


    private void defaultStyles(){
        areas = new ClickHtml();
        areas.setHtml("Countries/Regions");
        areas.addStyleName("download_tab_selected");

	}

	private void setListners() {
        areas.addListener(Events.OnClick, setTab(areas));
	}

	private void resetStyles() {
        areas.removeStyleName("download_tab_selected");
        areas.removeStyleName("download_tab_unselected");
        areas.addStyleName("download_tab_unselected");
	}





	public Listener<ComponentEvent> setTab(final ClickHtml html) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent ce) {
				resetStyles();

				html.removeStyleName("download_tab_unselected");
				html.addStyleName("download_tab_selected");
			}
		};
	}



	private HorizontalPanel addHSpace(Integer width) {
		HorizontalPanel p = new HorizontalPanel();
		p.setWidth(width);
		return p;
	}

	public AMISDownloadComboSelectorPanel getSelectorPanel() {
		return selectorPanel;
	}


	
}

