package org.fao.fenix.web.modules.udtable.client;

import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.udtable.client.control.UniqueValuesController;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class UniqueValuesWindow extends FenixWindow {
	private Button createUniqueButton;

	private Button createUniqueCoreButton;
	
	private Button createUniqueFlexButton;
	
	private VerticalPanel wrapper;
	
	public UniqueValuesWindow() {
		wrapper = new VerticalPanel();
		createUniqueButton = new Button("Create Unique");
		createUniqueCoreButton = new Button("Create Core");
		createUniqueFlexButton = new Button("Create Flex");
//		createUniqueButton.addSelectionListener(FixerController.createGhostTables(this));
		
	}
	
	public void build() {
		wrapper.setSpacing(10);
		wrapper.add(createUniqueButton);
//		wrapper.add(createUniqueCoreButton);
//		wrapper.add(createUniqueFlexButton);
		buildCenterPanel();
		format();
		enhance();
		show();
	}
	
	private void enhance(){
		
//		createUniqueButton = new Button("Create Unique");
		createUniqueButton.addSelectionListener(UniqueValuesController.createGhostTables(this));
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		getCenter().add(wrapper);
		getCenterData().setSize(600);
		addCenterPartToWindow();
	}
	
	private void format() {
		createUniqueButton.setMinWidth(200);
		createUniqueCoreButton.setMinWidth(200);
		createUniqueFlexButton.setMinWidth(200);
		createUniqueCoreButton.setWidth("200px");
		createUniqueFlexButton.setWidth("200px");
		createUniqueButton.setWidth("200px");
		setSize("250px", "130px");
		getWindow().setHeading("Create Unique Dataset Values");
		setCollapsible(true);
		getWindow().setResizable(true);
	}
}
