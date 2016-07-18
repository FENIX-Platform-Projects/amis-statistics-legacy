package org.fao.fenix.web.modules.core.client.utils;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.Window;

public class WindowFactory {

	public static Window createWindow(String title, int width, int height) {
		Window window = new Window();
		window.setResizable(true);
		window.setModal(true);
		window.setCollapsible(true);
		window.setMaximizable(true);
		window.setHeading(title);
		window.setSize(width, height);
		window.setBodyStyleName("pad-text");
		window.setScrollMode(Style.Scroll.AUTO);
		return window;
	}
	
	public static Window createWindow(String title, String width, String height) {
		Window window = new Window();
		window.setResizable(true);
		window.setModal(true);
		window.setCollapsible(true);
		window.setMaximizable(true);
		window.setHeading(title);
		window.setSize(width, height);
		window.setBodyStyleName("pad-text");
		window.setScrollMode(Style.Scroll.AUTO);
		return window;
	}
	
}
