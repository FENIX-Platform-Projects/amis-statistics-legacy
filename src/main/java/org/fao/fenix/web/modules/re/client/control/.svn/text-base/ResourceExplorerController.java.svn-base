package org.fao.fenix.web.modules.re.client.control;

import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;

public class ResourceExplorerController {

	public static WindowListener onCloseEditor(final ResourceExplorer explorer) {
		return new WindowListener() {
			public void handleEvent(WindowEvent event) {
				if (event.getType() == Events.Hide) {
					REModel.setReIsopen(false);
					REModel.setResourceExplorer(null);
					explorer.getWindow().hide();
				}
			}
		};
	}

}
