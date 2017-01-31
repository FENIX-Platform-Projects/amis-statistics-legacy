package org.fao.fenix.web.modules.amis.client.view.download;

import com.extjs.gxt.ui.client.event.*;
import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.client.control.input.Month;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.LoadingPanel;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;


import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;

public class AMISDownloadSelectorDataSources {

    ContentPanel panel;

    ClickHtml datasources;

    AMISDownloadSelectorPanel selectorPanel;

    public AMISDownloadSelectorDataSources() {
        panel = new ContentPanel();
        panel.setBodyBorder(false);
        panel.setHeaderVisible(false);
        panel.setAutoHeight(true);

        selectorPanel = new AMISDownloadSelectorPanel();
    }


  /**  public AMISDownloadSelectorDataSources() {
        panel = new ContentPanel();
        panel.setHeaderVisible(false);
        panel.setBodyBorder(false);
        panel.setAutoHeight(true);
        panel.setScrollMode(Scroll.NONE);


        holder = new ContentPanel();
        holder.setBodyBorder(false);
        holder.setHeaderVisible(false);
        holder.setAutoHeight(true);

        store = new ListStore<AMISCodesModelData>();
        list = new ListView<AMISCodesModelData>();

        list.setStore(store);
        list.setDisplayProperty("label");

    } **/



    public ContentPanel build(AMISDownload download, String title, String width, String height) {
        System.out.println("------------ AMISDownloadSelectorDataSources: build !!!! ");
        HorizontalPanel p = new HorizontalPanel();

        defaultStyles();

        p.add(datasources);
        p.add(addHSpace(5));

        setListeners();

        panel.add(p);

        panel.add(populateList(download, title,width, height));




        return panel;
    }


    public ContentPanel populateList(AMISDownload download, String title, String width, String height) {
        System.out.println("------------ AMISDownloadSelectorDataSources: build !!!! ");
        if ( title != null ) {
            selectorPanel.title = title;
            selectorPanel.getPanel().add(selectorPanel.addTitle(title));
        }

        selectorPanel.getList().setWidth(width);
        selectorPanel.getList().setHeight(height);
        selectorPanel.getList().setTemplate(selectorPanel.getTemplate());

        selectorPanel.getPanel().add(new LoadingPanel().buildPanel("Loading", width, height, true));

        AMISDownloadController.getDataSourcesAgent(download, selectorPanel, "CBS", true);

        return selectorPanel.panel;
    }


    private void defaultStyles(){
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

    public AMISDownloadSelectorPanel getSelectorPanel() {
        return selectorPanel;
    }



}