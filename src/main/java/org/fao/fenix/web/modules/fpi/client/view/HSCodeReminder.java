package org.fao.fenix.web.modules.fpi.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.google.gwt.user.client.ui.HTML;

public class HSCodeReminder {

public HorizontalPanel panel;
	
	public HTML message;
	
	public HTML name;
	
	public HTML code;
	
	public HSCodeReminder() {
		panel = new HorizontalPanel();
		panel.setSpacing(10);
	}
	
	public HorizontalPanel buildHSCodeReminder() {
		message = new HTML("<b>" + BabelFish.print().hsLastSelection() + ": </b>");
		name = new HTML(BabelFish.print().hsNoneSelectedYet());
		code = new HTML("<em>(0)</em>");
		panel.setData("message", message);
		panel.setData("name", name);
		panel.setData("code", code);
		panel.add(message);
		panel.add(name);
		panel.add(code);
		message.setWidth("100px");
		name.setWidth("350px");
		code.setWidth("50px");
		return panel;
	}
	
	public void updateHSCodeReminder(String name, long code) {
		((HTML)panel.getData("name")).setText(name);
		((HTML)panel.getData("code")).setHTML("<em>(" + String.valueOf(code) + ")</em>");
	}
	
}
