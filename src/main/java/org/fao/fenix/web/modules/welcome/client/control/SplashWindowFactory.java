package org.fao.fenix.web.modules.welcome.client.control;

import org.fao.fenix.web.modules.welcome.client.view.SplashWindow;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;

public class SplashWindowFactory {

	private static SplashWindow sw;

	public static void getInstance(boolean focusedOnTools) {
		if (sw == null) {
			sw = new SplashWindow();
			sw.setFocusedOnTools(focusedOnTools);
			sw.build();
			sw.getWindow().addWindowListener(new WindowListener() {
				public void windowHide(WindowEvent we) {
					super.windowHide(we);
					sw = null;
				}
			});
		} else {
			if (focusedOnTools) {
				sw.getTabPanel().setSelection(sw.getToolsTabItem());
				SplashWindowController.getToolsAgent(sw, "tools", "all");
			} else {
				sw.getTabPanel().setSelection(sw.getInfoTabItem());
			}
		}
	}
	
}