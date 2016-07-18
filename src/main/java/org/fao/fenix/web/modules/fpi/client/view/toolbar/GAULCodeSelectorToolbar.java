package org.fao.fenix.web.modules.fpi.client.view.toolbar;

import org.fao.fenix.web.modules.fpi.client.control.GAULCodeSelectorController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HTML;

public class GAULCodeSelectorToolbar {

	public ToolBar toolbar;

	public GAULCodeSelectorToolbar() {
		toolbar = new ToolBar();
	}

	public ToolBar build(HorizontalPanel parameterPanel) {
		IconButton getCode = new IconButton();
		getCode.setStyleName("tableSave");
		HTML reminderHTML = (HTML)parameterPanel.getData("parameterValue");
		getCode.addSelectionListener(GAULCodeSelectorController.buildGetGaulCodeListener(toolbar, parameterPanel, reminderHTML));		
		toolbar.add(getCode);
		Button reminder = new Button("<b>" + BabelFish.print().gaulLastSelection() + ": </b>" + BabelFish.print().gaulWorld() + "" + " <em>(0)</em>");
		toolbar.add(reminder);
		toolbar.setData("reminder", reminder);
		toolbar.setData("gaulName", BabelFish.print().gaulWorld());
		toolbar.setData("gaulCode", "0");
		return toolbar;
	}
	
	public void updateGAULCodeReminder(String name, Object code) {
		((Button)toolbar.getData("reminder")).setText("<b>" + BabelFish.print().gaulLastSelection() + ": </b>" + name + "" + " <em>(" + code.toString() + ")</em>");
		toolbar.setData("gaulName", name);
		toolbar.setData("gaulCode", code.toString());
	}
	
}
