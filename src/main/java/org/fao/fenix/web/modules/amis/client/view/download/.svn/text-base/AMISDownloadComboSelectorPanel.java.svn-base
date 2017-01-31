package org.fao.fenix.web.modules.amis.client.view.download;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import org.fao.fenix.web.modules.amis.client.control.AMISController;
import org.fao.fenix.web.modules.amis.client.control.download.AMISDownloadController;
import org.fao.fenix.web.modules.amis.client.view.download.AMISDownload;
import org.fao.fenix.web.modules.amis.client.view.download.AMISDownloadSelectorPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.client.view.utils.layout.LoadingPanel;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.constants.AMISCurrentDatasetView;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.AMISConstantsVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AMISDownloadComboSelectorPanel extends AMISDownloadSelectorPanel {
	
	
	ContentPanel panel;
	
	public ListStore<AMISCodesModelData> store;
	
	public ComboBox<AMISCodesModelData> comboBox;
	
	String title;


	public AMISDownloadComboSelectorPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setAutoHeight(true);
		panel.setScrollMode(Scroll.NONE);

		store = new ListStore<AMISCodesModelData>();
        comboBox = new ComboBox<AMISCodesModelData>();
        comboBox.setStore(store);
        comboBox.setDisplayField("label");
        comboBox.setTriggerAction(ComboBox.TriggerAction.ALL);

	}
      public ContentPanel build(AMISDownload download, String domainFilter, String title, String selectionType, Boolean isTotalAndList, Boolean addSelectAll, String width, String height) {
        this.selectionType = selectionType;

        System.out.println("---------------------------- BUILDING AMISDownloadComboSelectorPanel build() "+selectionType);

        if ( title != null ) {
            this.title = title;
            panel.add(addTitle(title));
        }

        comboBox.setWidth(width);
       // comboBox.setHeight(height);
       // comboBox.setTemplate(getTemplate());

        AMISQueryVO qvo = setQVOParameters(download, domainFilter, isTotalAndList);

        panel.add(new LoadingPanel().buildPanel("Loading", width, height, true));

        AMISDownloadController.getComboSelectors(this, qvo);

       return panel;
    }


    public void addCombo() {
       if(store.getCount() > 0)
        comboBox.setValue(store.getAt(0));

        System.out.println("addCombo: store.getCount() = "+store.getCount());

        panel.add(comboBox);
    }

    public ListStore<AMISCodesModelData> getStore() {
        return store;
    }


    public ComboBox<AMISCodesModelData> getCombo() {
        return comboBox;
    }

    public ContentPanel getPanel() {
        return panel;
    }

    public String getTitle() {
        return title;
    }

}