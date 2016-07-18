package org.fao.fenix.web.modules.edi.client.view.faostat;

import org.fao.fenix.web.modules.edi.client.view.EDIPanel;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.google.gwt.user.client.ui.ListBox;

public class FAOStatCodesPanel extends EDIPanel {

	private ListBox codesList;
	
	private TextArea descriptionArea;
	
	private Button getCSVButton;
	
	private Button getMetadataButton;
	
	private VerticalPanel wrapper;
	
	private String FIELD_WIDTH = "170px";
	
	private String LABEL_WIDTH = "170px";
	
	private String FIELD_HEIGHT = "285px";
	
	public FAOStatCodesPanel(String tabItemHeader, String iconStyle) {
		super(tabItemHeader, iconStyle);
		wrapper = new VerticalPanel();
		codesList = new ListBox();
		descriptionArea = new TextArea();
	}
	
	@Override
	public TabItem build() {
		wrapper.setSpacing(getSPACING());
		wrapper.add(buildSuggestionPanel());
		wrapper.add(buildCodelistPanel());
		wrapper.add(buildDescriptionPanel());
		wrapper.add(buildButtonsPanel());
		this.getTabItem().add(wrapper);
		return this.getTabItem();
	}
	
	private VerticalPanel buildCodelistPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Codelist</b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		codesList.setWidth(getFIELD_WIDTH());
		codesList.addItem("Please Select a Group...");
		codesList.addItem("Area", "Area");
		codesList.addItem("Domain", "Domain");
		codesList.addItem("Element", "Element");
		codesList.addItem("Group", "Group");
		codesList.addItem("Item", "Item");
		p.add(codesList);
		return p;
	}
	
	private VerticalPanel buildDescriptionPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<b>Description</b>");
		l.setWidth(LABEL_WIDTH);
		p.add(l);
		descriptionArea.setSize(getFIELD_WIDTH(), FIELD_HEIGHT);
		p.add(descriptionArea);
		return p;
	}
	
	private VerticalPanel buildSuggestionPanel() {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(getSPACING());
		Html l = new Html("<font color='#1D4589'>Use this interface to create FAOStat coding systems to be used by the Workstation.</font>");
		l.setWidth(getFIELD_WIDTH());
		p.add(l);
		return p;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(getSPACING());
		getCSVButton = new Button("Add Selection To Filter");
		getCSVButton.setWidth(FIELD_WIDTH);
		getMetadataButton = new Button("Remove Selection From Filter");
		getMetadataButton.setWidth(FIELD_WIDTH);
		p.add(getCSVButton);
		p.add(getMetadataButton);
		return p;
	}
	
}
