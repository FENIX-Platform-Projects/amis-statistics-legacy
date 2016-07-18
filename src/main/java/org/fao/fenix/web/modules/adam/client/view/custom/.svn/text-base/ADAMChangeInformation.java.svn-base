package org.fao.fenix.web.modules.adam.client.view.custom;


import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.RichTextArea;

public class ADAMChangeInformation extends FenixWindow {

	
	private ContentPanel panel;
	
	private static VerticalPanel optionsPanel;
	
	private static TextField<String> title;
	
	private static RichTextArea description;
	
	public static String text;
	
	

	public ADAMChangeInformation() {
		panel = new  ContentPanel();
		panel.setHeaderVisible(false);
		panel.setHeight(ADAMConstants.SELECTION_CENTRAL_HEIGHT);
		panel.setWidth(ADAMConstants.CUSTOM_SHOW_WIDTH);
		panel.setBorders(true);
		optionsPanel = new VerticalPanel();
	}
	
	
	public void build(String title, String description) {
		VerticalPanel v = new VerticalPanel();
		v.add(optionsPanel);
		panel.add(buildOptions(title, description));
		panel.add(v);
		panel.setBottomComponent(addButtonPanel());
	
		this.setTitle("Change information");
		setCenterProperties();
		getCenter().add(panel);
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getCenter().setBodyBorder(false);
		//getCenter().setLayout(new CenterLayout());
		
		show();
	} 
	
	public static VerticalPanel buildOptions(String title, String description) {
		optionsPanel.removeAll();
		optionsPanel.setSpacing(10);
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		
		panel.add(titlePanel(title));

		panel.add(descriptionPanel(description));
		
		return panel;
	}
	
	private static HorizontalPanel titlePanel(String titleBase) {
		HorizontalPanel panel = new HorizontalPanel();
		title = new TextField<String>();
		Html titleHtml = new Html("<div class='small-content'>Chart Title:</div>");
		titleHtml.setWidth("110px");
		title.setEmptyText("Insert a title");
		title.setWidth(300);
		title.setValue(titleBase);
		
//		IconButton icon = new IconButton();
//		icon.setStyleName("noteIcon");
//		icon.setToolTip("Add/Modify title");
		
		panel.add(titleHtml);
//		panel.add(icon);
		panel.add(title);
		return panel;
	}
	
	private static HorizontalPanel descriptionPanel(String descriptionBase) {
		HorizontalPanel panel = new HorizontalPanel();
		description = new RichTextArea();
		description.setWidth("300px");
		description.setHeight("100px");
		description.setHTML(descriptionBase);

		Html descriptionHtml = new Html("<div class='small-content'>Description:</div>");
		descriptionHtml.setWidth("85px");
//		description.setEmptyText("Insert a description");

		IconButton icon = new IconButton();
		icon = new IconButton("textEditBtn");		
		icon.setToolTip("Add/Modify description");
		icon.addSelectionListener(ADAMCustomController.modifyText(description));
		icon.setWidth(20);
		
		panel.add(descriptionHtml);
		panel.add(icon);
		panel.add(description);
		return panel;
	}

	
	private HorizontalPanel addButtonPanel() {
		HorizontalPanel panel = new HorizontalPanel();
		
//		panel.add(addResource);
		return panel;
	}
	


	public ContentPanel getPanel() {
		return panel;
	}



	public static VerticalPanel getOptionsPanel() {
		return optionsPanel;
	}


	public static TextField<String> getTitle() {
		return title;
	}


	public static RichTextArea getDescription() {
		return description;
	}

	
	
}
