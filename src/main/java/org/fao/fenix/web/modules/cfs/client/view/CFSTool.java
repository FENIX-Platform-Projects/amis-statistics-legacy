package org.fao.fenix.web.modules.cfs.client.view;

import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class CFSTool extends FenixToolBase {

	private Button button;
	
	public CFSTool() {
		super();
		button = new Button();
	}
	
	public void build() {
		buildEastPanel();
		buildCenterPanel();
//		enhance();
		format();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getCenter().setId("cfs");
	}
	
	private void buildEastPanel() {
		fillEastPart(buildController());
		getEastData().setSize(350);
		getEast().setHeaderVisible(false);
	}
	
	private VerticalPanel buildController() {
		VerticalPanel controller = new VerticalPanel();
		controller.setSpacing(10);
		HTML country = new HTML("<b>Country Name</b>");
		country.setWidth("300px");
		controller.add(country);
		TextArea description = new TextArea();
		description.setValue("This is the country description");
		description.setSize("300px", "600px");
		controller.add(description);
		controller.add(buildFilterPanel());
		return controller;
	}
	
	private HorizontalPanel buildFilterPanel() {
		HorizontalPanel filterPanel = new HorizontalPanel();
//		filterPanel.setSpacing(10);
		ListBox list = new ListBox();
		list.setWidth("250px");
		filterPanel.add(list);
		button.setText("Apply");
		filterPanel.add(button);
		return filterPanel;
	}
	
	private void format() {
		getToolBase().setWidth("100%");
		getToolBase().setHeight("750px");
		getToolBase().setHeaderVisible(false);
	}
	
}