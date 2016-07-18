package org.fao.fenix.web.modules.shared.window.client.view.utils;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.MessageBox.MessageBoxType;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class LoadingWindow {

	private HTML html;

	private HorizontalPanel panel;

	private Timer timer;

	private int mills;
	
	private MessageBox box;
	

	/**
	 * @deprecated
	 */
	public LoadingWindow() {
		html = new HTML("");
		panel = new HorizontalPanel();
		panel.setSpacing(10);
		panel.setData("html", html);
		mills = 0;
	}
	
	public LoadingWindow(String title, String message, String progressText) {
		box = MessageBox.wait(title, message, progressText);
		box.setModal(false);
		mills = 0;
	}
	

	/**
	 * @deprecated 
	 */
	public void create() {
		create(BabelFish.print().loading());
	}

	public void create(final String message) {
		timer = new Timer() {
			public void run() {
				if (getMills() > 3)
					setMills(1);
				else
					setMills(1 + getMills());
				int dots = getDots(getMills());
				html = new HTML(createHtmlFromMessage(message, dots));
				panel = new HorizontalPanel();
				panel.setSpacing(10);
				panel.add(html);
				if (RootPanel.get("loading").getWidgetCount() > 0)
					RootPanel.get("loading").remove(0);
				RootPanel.get("loading").add(panel);
			}
		};
		timer.scheduleRepeating(1000);
	}

	public void showLoadingBox() {
		timer = new Timer() {
			@Override 
			public void run() {
				if (getMills() > 3)
					setMills(1);
				else
					setMills(1 + getMills());
				
				box.show();	
			}
		};
		timer.scheduleRepeating(1000);
	}
	
	private int getDots(int millis) {
		if (millis % 4 == 0)
			return 4;
		if (millis % 3 == 0)
			return 3;
		else if (millis % 2 == 0)
			return 2;
		return 1;
	}

	public String createHtmlFromMessage(String message) {
		String html = "<table width='250px' height='50px' border='0' bgcolor='#15428B'>";
		html += "<tr width='250px'>";
		html += "<td width='250px'><font color='#d0dded'>" + BabelFish.print().loading() + "...</font></td>";
		html += "</tr>";
		html += "<tr width='250px'>";
		html += "<td width='250px'><font color='#d0dded'>" + message + "</font></td>";
		html += "</tr>";
		html += "</table>";
		return html;
	}

	public String createHtmlFromMessage(String message, int dots) {
		String html = "<table width='250px' height='50px' border='0' bgcolor='#15428B'>";
		html += "<tr width='250px'>";
		html += "<td width='250px'><font color='#d0dded'>" + BabelFish.print().loading();
		for (int i = 0; i < dots; i++)
			html += ".";
		html += "</font></td>";
		html += "</tr>";
		html += "<tr width='250px'>";
		html += "<td width='250px'><font color='#d0dded'>" + message + "</font></td>";
		html += "</tr>";
		html += "</table>";
		return html;
	}
		
	public void destroy() {
		if (timer != null) 
			this.timer.cancel();
		for (int i = RootPanel.get("loading").getWidgetCount() -1 ; i >= 0 ; i--)
			RootPanel.get("loading").remove(RootPanel.get("loading").getWidget(i));
	}
	
	
	public void destroyLoadingBox() {
		if (timer != null) 
			this.timer.cancel();
		if(box != null)
			this.box.close();	
	}
	
	

	public int getMills() {
		return mills;
	}

	public void setMills(int mills) {
		this.mills = mills;
	}

	public MessageBox getBox() {
		return box;
	}

}