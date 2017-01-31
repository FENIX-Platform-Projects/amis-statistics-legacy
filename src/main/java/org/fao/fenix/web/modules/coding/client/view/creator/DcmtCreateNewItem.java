package org.fao.fenix.web.modules.coding.client.view.creator;

import org.fao.fenix.web.modules.coding.client.control.creator.DcmtCodingCreatorController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;



public class DcmtCreateNewItem extends FenixWindow {

	private TextField label;
	private TextField description;
	private TextField structure;
	private String item;
	private ListBox langCode;
	private Button saveToDb;
//	private Button saveToFile;
	private Button close;
	private DcmtCreateNewItem eWindow;
		
	
	public void build(String item) {
		setCenterProperties();
		getCenter().add(buildPanel());
		addCenterPartToWindow();
		format(item);
		enhance();
		show();
	}
	
	
	private void enhance() {
		close.addSelectionListener(DcmtCodingCreatorController.closeWindow(this));
		saveToDb.addSelectionListener(DcmtCodingCreatorController.saveNewItem(this));
	}
	
	
	private VerticalPanel buildPanel() {
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(buildLabel());
		vPanel.add(buildLangCode());
		vPanel.add(buildDescription());
		vPanel.add(buildStructure());
		vPanel.add(buildButtons());
		return vPanel;
	}

	
	public HorizontalPanel buildLabel(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML text = new HTML("<b>" + BabelFish.print().label() + "(*): </b>");
		label = new TextField();
		label.setAllowBlank(false);
		text.setSize("100px", "20px");
		label.setSize("300px", "20px");
		panel.add(text);
		panel.add(label);
		return panel;
	}
	
	public HorizontalPanel buildLangCode(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML text = new HTML("<b>" + BabelFish.print().langCode() + ": </b>");
		langCode = new ListBox();
		langCode.addItem("EN");
		langCode.addItem("ES");
		langCode.addItem("FR");
		langCode.addItem("DE");
		langCode.addItem("IT");
		text.setSize("100px", "20px");
		langCode.setSize("300px", "20px");
		panel.add(text);
		panel.add(langCode);
		return panel;
	}
	
	public HorizontalPanel buildDescription(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML text = new HTML("<b>" + BabelFish.print().explonatoryNote()+ ": </b>");
		description = new TextField();
		text.setSize("100px", "20px");
		description.setSize("300px", "20px");
		panel.add(text);
		panel.add(description);
		return panel;
	}
	
	public HorizontalPanel buildStructure(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		HTML text = new HTML("<b>" + BabelFish.print().structure() + ":</b>");
		structure = new TextField();
		text.setSize("100px", "20px");
		structure.setSize("300px", "20px");
		panel.add(text);
		panel.add(structure);
		return panel;
	}
	
	private HorizontalPanel buildButtons() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
//		saveToFile = new Button(I18N.print().saveToFile());
		saveToDb = new Button(BabelFish.print().saveToDb());
		close = new Button(BabelFish.print().close());
//		panel.add(saveToFile);
		panel.add(saveToDb);
		panel.add(close);
		panel.setWidth(200);
		return panel;
	}
	
	

	
	

	
	protected void format(String item) {		
		setSize(500, 250);
		if ( item.equals("element")) {
			window.setHeading("<b> " + BabelFish.print().createNewElement() + "</b>");
		}
		if (item.equals("attribute")) {
				window.setHeading("<b> " + BabelFish.print().createNewAttribute() + "</b>");
		}
		setItem(item);
		getCenter().setHeaderVisible(false);
		getWindow().setModal(true);
	}


	
	

	public Button getClose() {
		return close;
	}


	public void setClose(Button close) {
		this.close = close;
	}


	public DcmtCreateNewItem getEWindow() {
		return eWindow;
	}


	public void setEWindow(DcmtCreateNewItem window) {
		eWindow = window;
	}

	public TextField getLabel() {
		return label;
	}


	public void setLabel(TextField label) {
		this.label = label;
	}


	public TextField getDescription() {
		return description;
	}


	public void setDescription(TextField description) {
		this.description = description;
	}


	public TextField getStructure() {
		return structure;
	}


	public void setStructure(TextField structure) {
		this.structure = structure;
	}


	public ListBox getLangCode() {
		return langCode;
	}


	public void setLangCode(ListBox langCode) {
		this.langCode = langCode;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}

	
	

}