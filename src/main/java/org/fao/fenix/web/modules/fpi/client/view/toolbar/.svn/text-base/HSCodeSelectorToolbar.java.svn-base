package org.fao.fenix.web.modules.fpi.client.view.toolbar;

import org.fao.fenix.web.modules.fpi.client.control.HSCodeSelectorController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HTML;

public class HSCodeSelectorToolbar {

	public ToolBar toolbar;
	
	public HSCodeSelectorToolbar() {
		toolbar = new ToolBar();
	}

	public ToolBar build(HorizontalPanel parameterPanel) {
		IconButton getCode = new IconButton();
		getCode.setStyleName("tableSave");
		HTML reminderHTML = (HTML)parameterPanel.getData("parameterValue");
		getCode.addSelectionListener(HSCodeSelectorController.buildGetHSCodeListener(toolbar, parameterPanel, reminderHTML));
		toolbar.add(getCode);
		Button reminder = new Button("<b>" + BabelFish.print().hsLastSelection() + ": </b>" + BabelFish.print().hsNoneSelectedYet() + "" + " <em>(" + BabelFish.print().hsNoneSelectedYet() + ")</em>");
		toolbar.add(reminder);
		toolbar.setData("reminder", reminder);
		toolbar.setData("hsName", BabelFish.print().hsNoneSelectedYet());
		toolbar.setData("hsCode", "0");
		return toolbar;
	}
	
	public void updateHSCodeReminder(String name, Object code) {
		((Button)toolbar.getData("reminder")).setText("<b>" + BabelFish.print().hsLastSelection() + ": </b>" + name + "" + " <em>(" + code.toString() + ")</em>");
		((Button)toolbar.getData("reminder")).setToolTip(name);
		toolbar.setData("hsName", name);
		toolbar.setData("hsCode", code.toString());
	}
	
}
