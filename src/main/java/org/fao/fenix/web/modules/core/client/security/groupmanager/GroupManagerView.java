package org.fao.fenix.web.modules.core.client.security.groupmanager;

import org.fao.fenix.web.modules.core.client.security.usermanager.UserManagerController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;

public class GroupManagerView extends FenixWindow {

	VerticalPanel centerVerticalPanel;

	HorizontalPanel topHorizontalPanel;
	VerticalPanel addUserVerticalPanel;
	TextField<String> groupNameTextField;
	Button deleteGroupButton;
	Button addGroupButton;
	HTML groupAlreadyExistsHTML;
	DataList groupDataList;

	VerticalPanel buttonVerticalPanel;
	Button moveRightButton;
	Button moveLeftButton;
	Button moveAllRightButton;
	Button moveAllLeftButton;

	HorizontalPanel bottomHorizontalPanel;
	DataList userDataList;
	DataList selectedUserDataList;

	HTML statusHTML;
	
	private static GroupManagerView instance = null;

	protected GroupManagerView() {
	}

	public static GroupManagerView getInstance() {
		if (instance == null) {
			instance = new GroupManagerView();
			UserManagerController.getInstance().hide();
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
	}

	private void fillWindow() {
		getWindow().setHeading("Group Manager");
	}

	private void buildCenterPanel() {
		topHorizontalPanel = new HorizontalPanel();
		groupNameTextField = new TextField<String>();
		addGroupButton = new Button();
		groupAlreadyExistsHTML = new HTML();
		deleteGroupButton = new Button();
		groupDataList = new DataList();

		addUserVerticalPanel = new VerticalPanel();
		addUserVerticalPanel.add(groupNameTextField);
		addUserVerticalPanel.add(addGroupButton);
		addUserVerticalPanel.add(groupAlreadyExistsHTML);

		topHorizontalPanel.add(addUserVerticalPanel);
		topHorizontalPanel.add(groupDataList);
		topHorizontalPanel.add(deleteGroupButton);

		buttonVerticalPanel = new VerticalPanel();
		moveRightButton = new Button();
		moveLeftButton = new Button();
		moveAllRightButton = new Button();
		moveAllLeftButton = new Button();
		buttonVerticalPanel.add(moveRightButton);
		buttonVerticalPanel.add(moveLeftButton);
		buttonVerticalPanel.add(moveAllRightButton);
		buttonVerticalPanel.add(moveAllLeftButton);
		bottomHorizontalPanel = new HorizontalPanel();
		userDataList = new DataList();
		selectedUserDataList = new DataList();
		bottomHorizontalPanel.add(userDataList);
		bottomHorizontalPanel.add(buttonVerticalPanel);
		bottomHorizontalPanel.add(selectedUserDataList);

		statusHTML = new HTML(); 
		
		centerVerticalPanel = new VerticalPanel();
		centerVerticalPanel.add(topHorizontalPanel);
		centerVerticalPanel.add(statusHTML);
		centerVerticalPanel.add(bottomHorizontalPanel);
		fillCenterPart(centerVerticalPanel);
	}

	private void fillCenterPanel() {
		addGroupButton.setText(BabelFish.print().add());
		addGroupButton.disable();		
		deleteGroupButton.setText(BabelFish.print().delete());
		moveRightButton.setText(">");
		moveLeftButton.setText("<");
		moveAllRightButton.setText(">>");
		moveAllLeftButton.setText("<<");
		deleteGroupButton.disable();
		buttonVerticalPanel.setHorizontalAlign(HorizontalAlignment.CENTER);

		moveLeftButton.hide();
		moveRightButton.hide();
		moveAllLeftButton.hide();
		moveAllRightButton.hide();

	}

	private void buildWestPanel() {
	}

	private void formatWestPanel() {
	}

	private void formatCenterPanel() {
		center.setHeaderVisible(false);
		center.setBorders(false);
		center.setBodyBorder(false);
		center.setFrame(false);

		moveRightButton.disable();
		moveLeftButton.disable();

		centerVerticalPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		centerVerticalPanel.setSpacing(10);

		groupDataList.setBorders(false);
		groupDataList.setWidth(190);
		groupDataList.setScrollMode(Scroll.NONE);
		userDataList.setBorders(false);
		userDataList.setWidth(190);
		userDataList.setScrollMode(Scroll.NONE);
		selectedUserDataList.setBorders(false);
		selectedUserDataList.setWidth(190);
		selectedUserDataList.setScrollMode(Scroll.NONE);
	}

}