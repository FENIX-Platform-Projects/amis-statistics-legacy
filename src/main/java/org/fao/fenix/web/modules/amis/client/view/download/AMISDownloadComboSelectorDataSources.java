package org.fao.fenix.web.modules.amis.client.view.download;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.LoadingPanel;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

public class AMISDownloadComboSelectorDataSources {

	AMISDownloadComboSelectorPanel selectorPanel;

	ContentPanel panel;

	ClickHtml datasources;

	public AMISDownloadComboSelectorDataSources() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setAutoHeight(true);

		selectorPanel = new AMISDownloadComboSelectorPanel();
	}

    public ContentPanel build(AMISDownload download, String width, String height) {
        System.out.println("AMISDownloadComboSelectorDataSources BUILD");
        HorizontalPanel p = new HorizontalPanel();
        defaultStyles();

        p.add(datasources);
        p.add(addHSpace(5));

        panel.add(p);

        panel.add(buildSourcesPanel(download, null, width, height));

        setListeners();

        return panel;
    }


    private ContentPanel buildSourcesPanel(AMISDownload download, String title, String width, String height) {
        System.out.println("AMISDownloadComboSelectorDataSources buildSourcesPanel");
        if ( title != null ) {
            selectorPanel.title = title;
            selectorPanel.getPanel().add(selectorPanel.addTitle(title));
        }

        selectorPanel.comboBox.setWidth(width);

        selectorPanel.getPanel().add(new LoadingPanel().buildPanel("Loading", width, height, true));

        AMISDownloadController.getDataSourceComboSelectors(download, selectorPanel);

        return  selectorPanel.getPanel();
    }


	private void defaultStyles(){
        System.out.println("AMISDownloadComboSelectorDataSources defaultStyles");
        datasources = new ClickHtml();
        datasources.setHtml("Data Sources");
        datasources.addStyleName("download_tab_selected");

	}

	private void setListeners() {
        datasources.addListener(Events.OnClick, setTab(datasources));
	}

	private void resetStyles() {
        datasources.removeStyleName("download_tab_selected");
        datasources.removeStyleName("download_tab_unselected");
        datasources.addStyleName("download_tab_unselected");
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

    public ContentPanel getPanel() {
        return panel;
    }
	
}

