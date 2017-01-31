package org.fao.fenix.web.modules.dataviewer.client.view.faostat.compare;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.compare.FAOSTATCompareDataController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownload;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.FAOSTATDomainPanel;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.constants.FAOSTATCompareConstants;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FAOSTATCompareDomainPanel  extends FAOSTATDomainPanel {

	
	public ContentPanel build(FAOSTATCompareData compare) {
		wrapper.add(panel);
		addTree();
		FAOSTATDownloadController.getDomainsAgent(compare, store, tree, null);
		addListeners(compare);
		wrapper.layout();
		return wrapper;
	}

	Button button;
	 
	private HorizontalPanel buildButtonPanel(FAOSTATCompareData compare) {
		HorizontalPanel panel = new HorizontalPanel();
		
		button = new Button("Add");
		
		button.addSelectionListener(FAOSTATCompareDataController.addDomainPanel(compare));
		
		panel.add(button);

		return panel;
	} 
	
	private void addListeners(final FAOSTATCompareData compare) {

		tree.addListener(Events.BeforeExpand, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				checkTreeOnClick = false;
			}
		});

		tree.addListener(Events.BeforeCollapse, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				checkTreeOnClick = false;
			}
		});
		
		tree.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				checkTreeOnClick = true;
			}
		});

		tree.addListener(Events.OnDoubleClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
//				System.out.println("ON DOUBLE CLICK: " + checkTreeOnClick);

				if (checkTreeOnClick) {
					DWCodesModelData selectedCode = tree.getSelectionModel().getSelectedItem();
					DWCodesModelData parentDomain = tree.getStore().getParent(selectedCode);

					if (selectedCode != null && parentDomain == null) {
//						System.out.println("IS PARENT: |" + selectedCode.getCode() + "|");
						currentCode = selectedCode.getCode();

					} else {
						if (parentDomain != null) {
							currentCode = selectedCode.getCode();
							FAOSTATCompareDataController.addDomainAgent(compare);
						}
					}
				}
				checkTreeOnClick = true;
			}
		});
	}

}
