package org.fao.fenix.web.modules.giews.client.view;

import org.fao.fenix.web.modules.giews.client.control.GIEWSController;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

public class GIEWSWindow extends FenixWindow {

	private Button createProjectsButton;
	
	private Button createSingleProjectButton;
	
	private Button updateProjectsButton;
	
	private Button deleteProjectsButton;
	
	private VerticalPanel wrapper;
	
	private ComboBox<GaulModelData> gaulList;
	
	private ListStore<GaulModelData> gaulStore;
	
	public GIEWSWindow() {
		wrapper = new VerticalPanel();
		createProjectsButton = new Button("Create Country Projects");
		createSingleProjectButton = new Button("Create Single Project");
		updateProjectsButton = new Button("Update Country Projects");
		deleteProjectsButton = new Button("Delete Country Projects");
	}
	
	public void build() {
		wrapper.setSpacing(10);
		createProjectsButton.addSelectionListener(GIEWSController.createProjects());
//		createProjectsButton.setEnabled(false);
		updateProjectsButton.addSelectionListener(GIEWSController.updateProjects());
		deleteProjectsButton.addSelectionListener(GIEWSController.deleteProjects());
		deleteProjectsButton.setEnabled(false);
		wrapper.add(createProjectsButton);
		wrapper.add(buildSingleProjectPanel());
		wrapper.add(updateProjectsButton);
		wrapper.add(deleteProjectsButton);
		buildCenterPanel();
		format();
		createSingleProjectButton.addSelectionListener(GIEWSController.createSingleProject(gaulList));
		show();
	}
	
	private HorizontalPanel buildSingleProjectPanel() {
		HorizontalPanel singleProjectPanel = new HorizontalPanel();
		singleProjectPanel.setSpacing(10);
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulStore = new ListStore<GaulModelData>();
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		singleProjectPanel.add(gaulList);
		singleProjectPanel.add(createSingleProjectButton);
		MEController.fillGaulStore(gaulStore);
		return singleProjectPanel;
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
		createProjectsButton.setMinWidth(400);
		createSingleProjectButton.setMinWidth(200);
		updateProjectsButton.setMinWidth(400);
		deleteProjectsButton.setMinWidth(400);
		createProjectsButton.setWidth("200px");
		updateProjectsButton.setWidth("200px");
		deleteProjectsButton.setWidth("200px");
		setSize("450px", "200px");
		getWindow().setHeading("GIEWS Country Projects");
		setCollapsible(true);
		getWindow().setResizable(true);
	}
	
}