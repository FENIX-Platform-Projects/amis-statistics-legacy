package org.fao.fenix.web.modules.core.client.security.permissionmanager;

import org.fao.fenix.web.modules.core.client.utils.FenixConstants;
import org.fao.fenix.web.modules.map.client.control.vo.PDDModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.CheckBoxGroup;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class PermissionManagerView extends FenixWindow {

	static final String giveRead = "giveRead";
	static final String giveWrite = "giveWrite";
	static final String giveDelete = "giveDelete";
	static final String giveDownload = "giveDownload";
	static final String deleteRead = "deleteRead";
	static final String deleteWrite = "deleteWrite";
	static final String deleteDelete = "deleteDelete";
	static final String deleteDownload = "deleteDownload";

	static PDDModel giveReadModel = new PDDModel("Give Read Permission", giveRead);
	static PDDModel giveWriteModel = new PDDModel("Give Read & Write", giveWrite);
	static PDDModel giveDeleteModel = new PDDModel("Give Read & Write & Delete", giveDelete);
	static PDDModel giveDownloadModel = new PDDModel("Give Read & Download", giveDownload);
	static PDDModel deleteReadModel = new PDDModel("Remove all permissions (!)", deleteRead);
	static PDDModel deleteWriteModel = new PDDModel("Remove Read & Write", deleteWrite);
	static PDDModel deleteDeleteModel = new PDDModel("Remove Read & Write & Delete", deleteDelete);
	static PDDModel deleteDownloadModel = new PDDModel("Remove Download Permission", deleteDownload);
	static ListStore<PDDModel> states = new ListStore<PDDModel>();
	static ListStore<PDDModel> statesAnonymous = new ListStore<PDDModel>();

	static {
		giveReadModel.set(FenixConstants.VO, giveRead);
		giveReadModel.set(FenixConstants.VO, giveWrite);
		giveReadModel.set(FenixConstants.VO, giveDelete);
		giveReadModel.set(FenixConstants.VO, giveDownload);
		giveReadModel.set(FenixConstants.VO, deleteRead);
		giveReadModel.set(FenixConstants.VO, deleteWrite);
		giveReadModel.set(FenixConstants.VO, deleteDelete);
		giveReadModel.set(FenixConstants.VO, deleteDownload);

		states.add(giveReadModel);
		states.add(giveWriteModel);
		states.add(giveDeleteModel);
		states.add(giveDownloadModel);
		states.add(deleteReadModel);
		states.add(deleteWriteModel);
		states.add(deleteDeleteModel);
		states.add(deleteDownloadModel);

		statesAnonymous.add(giveReadModel);
		statesAnonymous.add(deleteReadModel);
	}

	VerticalPanel centerVerticalPanel;
	HorizontalPanel statusHorizontalPanel;
	HTML statusHTML;
	HTML htmlNote;

	HorizontalPanel centerHorizontalPanel;
	DataList resourceDataList;
	DataList groupDataList;

	CheckBoxGroup permissionCheckBoxGroup;
	CheckBox readPermissionCheckBox;
	CheckBox writePermissionCheckBox;
	CheckBox deletePermissionCheckBox;
	CheckBox downloadPermissionCheckBox;

	VerticalPanel multiplePermissionVerticalPanel;
	ComboBox<PDDModel> permissionActionComboBox;
	Button applyPermissionActionButton;

	private static PermissionManagerView instance = null;

	protected PermissionManagerView() {
	}

	public static PermissionManagerView getInstance() {
		if (instance == null) {
			instance = new PermissionManagerView();
			instance.build();
		}
		return instance;
	}

	public void build() {
		formatWindow();
		fillWindow();
		buildWestPanel();
		buildCenterPanel();
		fillCenterPanel();
		formatWestPanel();
		formatCenterPanel();
	}

	private void formatWindow() {
		setCollapsible(true);
		getWindow().setWidth(650);
		getWindow().setHeight(350);
		getWindow().setPagePosition(30,110);
	}

	private void fillWindow() {
		getWindow().setHeading("Permission Manager");
	}

	private void buildCenterPanel() {

		centerHorizontalPanel = new HorizontalPanel();
		resourceDataList = new DataList();
		groupDataList = new DataList();

		permissionCheckBoxGroup = new CheckBoxGroup();
		readPermissionCheckBox = new CheckBox();
		writePermissionCheckBox = new CheckBox();
		deletePermissionCheckBox = new CheckBox();
		downloadPermissionCheckBox = new CheckBox();
		permissionCheckBoxGroup.add(readPermissionCheckBox);
		permissionCheckBoxGroup.add(writePermissionCheckBox);
		permissionCheckBoxGroup.add(deletePermissionCheckBox);
		permissionCheckBoxGroup.add(downloadPermissionCheckBox);

		multiplePermissionVerticalPanel = new VerticalPanel();
		permissionActionComboBox = new ComboBox<PDDModel>();
		permissionActionComboBox.setTriggerAction(TriggerAction.ALL);
		applyPermissionActionButton = new Button();
		multiplePermissionVerticalPanel.add(permissionActionComboBox);
		multiplePermissionVerticalPanel.add(applyPermissionActionButton);

		centerHorizontalPanel.add(resourceDataList);
		centerHorizontalPanel.add(groupDataList);
		centerHorizontalPanel.add(permissionCheckBoxGroup);
		centerHorizontalPanel.add(multiplePermissionVerticalPanel);

		centerVerticalPanel = new VerticalPanel();
		statusHorizontalPanel = new HorizontalPanel();
		statusHTML = new HTML();
		statusHorizontalPanel.add(statusHTML);
		htmlNote = new HTML();
		centerVerticalPanel.add(statusHorizontalPanel);
		centerVerticalPanel.add(centerHorizontalPanel);
		centerVerticalPanel.add(htmlNote);

		fillCenterPart(centerVerticalPanel);

	}

	private void fillCenterPanel() {
		readPermissionCheckBox.setBoxLabel("Read Permission");
		writePermissionCheckBox.setBoxLabel("Write Permission");
		deletePermissionCheckBox.setBoxLabel("Delete Permission");
		downloadPermissionCheckBox.setBoxLabel("Download Permission");

		permissionActionComboBox.setEmptyText("Select a permission...");
		permissionActionComboBox.setDisplayField("name");
		permissionActionComboBox.setWidth(150);
		permissionActionComboBox.setTypeAhead(true);
		permissionActionComboBox.setTriggerAction(TriggerAction.ALL);
		permissionActionComboBox.setForceSelection(true);
		
		permissionActionComboBox.setEditable(true);

		applyPermissionActionButton.setText("Apply Permissions");
		permissionActionComboBox.setStore(states);
	}

	private void buildWestPanel() {
	}

	private void formatWestPanel() {
	}

	private void formatCenterPanel() {

		centerHorizontalPanel.setSpacing(10);

		statusHorizontalPanel.setSpacing(10);
		statusHTML.setHTML("");
		statusHTML.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		center.setHeaderVisible(false);
		center.setBorders(false);
		center.setBodyBorder(false);
		center.setFrame(false);

		resourceDataList.setBorders(false);
		resourceDataList.setWidth(190);
		resourceDataList.setHeight(200);
		resourceDataList.setScrollMode(Scroll.AUTOY);
		resourceDataList.setSelectionMode(SelectionMode.MULTI);

		groupDataList.setBorders(false);
		groupDataList.setWidth(190);
		groupDataList.setHeight(200);
		groupDataList.setScrollMode(Scroll.AUTOY);

		permissionCheckBoxGroup.setOrientation(Style.Orientation.VERTICAL);
		permissionCheckBoxGroup.disable();

		applyPermissionActionButton.disable();
		permissionActionComboBox.setWidth(180);
		permissionActionComboBox.disable();
		permissionActionComboBox.setForceSelection(true);
		permissionActionComboBox.setEditable(false);

		htmlNote.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		multiplePermissionVerticalPanel.setVisible(false);
		multiplePermissionVerticalPanel.setSpacing(10);
		multiplePermissionVerticalPanel.setAutoWidth(true);
	}

}