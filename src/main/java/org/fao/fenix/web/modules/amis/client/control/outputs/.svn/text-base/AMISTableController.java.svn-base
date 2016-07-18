package org.fao.fenix.web.modules.amis.client.control.outputs;


import org.fao.fenix.web.modules.amis.client.view.utils.layout.LoadingPanel;
import org.fao.fenix.web.modules.amis.client.view.utils.outputboxes.boxmaker.AMISBoxMakerFlatTable;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AMISTableController {

	public static void addTable(final ContentPanel panel, final AMISQueryVO qvo, final String width, final String height) {

		panel.add(new LoadingPanel().buildPanel("Loading", width, height, true));

		try {
			AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {


				public void onSuccess(AMISResultVO rvo) {
					panel.removeAll();

					panel.setWidth(width);

					panel.add(AMISBoxMakerFlatTable.build(qvo, rvo, width, height));

					panel.layout();

				}

				public void onFailure(Throwable arg0) {
					// TODO Auto-generated method stub

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
