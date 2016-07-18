package org.fao.fenix.web.modules.metadataeditor.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.CodeListMetadataEditorController;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingTypeModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class CodeListMetadataEditorWindow extends FenixWindow {

	private ContentPanel panel;
	
	private ListStore<CodingTypeModelData> codingTypeListStore;
	
	private ComboBox<CodingTypeModelData> codingType;
	
	private TextField<String> codingName;
	
	private TextField<String> source;
	
	private Button getArchive;
	
	private static Integer WIDTH = 200;
	
	public CodeListMetadataEditorWindow() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		codingTypeListStore = new ListStore<CodingTypeModelData>();
		MEController.fillCodingTypeStore(codingTypeListStore);
	}
	
	public void build() {
		buildCenterPanel();
		format();
		show();
	}
	
	private void buildCenterPanel() {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeaderVisible(false);
		panel.add(buildWrapper());
		getCenter().add(panel);
		addCenterPartToWindow();
	}
	
	private VerticalPanel buildWrapper() {
		
		VerticalPanel wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		
		HTML codingTypeLabel = new HTML("<b>" + BabelFish.print().codingType() + "</b>");
		codingTypeLabel.setWidth(WIDTH + "px");
		codingType = new ComboBox<CodingTypeModelData>();
		codingType.setTriggerAction(TriggerAction.ALL);
		codingType.setStore(codingTypeListStore);
		codingType.setDisplayField("codingType");
		codingType.setWidth(WIDTH + "px");
		codingType.setForceSelection(true);
		codingType.setAllowBlank(false);
		
		HTML codingNameLabel = new HTML("<b>" + BabelFish.print().codingName() + "</b>");
		codingNameLabel.setWidth(WIDTH + "px");
		codingName = new TextField<String>();
		codingName.setAllowBlank(false);
		codingName.setWidth(WIDTH + "px");
		
		HTML sourceLabel = new HTML("<b>" + BabelFish.print().source() + "</b>");
		sourceLabel.setWidth(WIDTH + "px");
		source = new TextField<String>();
		source.setAllowBlank(true);
		source.setWidth(WIDTH + "px");
		
		getArchive = new Button(BabelFish.print().getMetadataArchive());
		getArchive.addSelectionListener(CodeListMetadataEditorController.getXml(this));
		getArchive.setMinWidth(WIDTH);
		
		wrapper.add(codingTypeLabel);
		wrapper.add(codingType);
		wrapper.add(codingNameLabel);
		wrapper.add(codingName);
		wrapper.add(sourceLabel);
		wrapper.add(source);
		wrapper.add(getArchive);
		
		return wrapper;
	}
	
	private void format() {
		setSize("275px", "250px");
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setHeading("Coding List Metadata Editor");
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public ListStore<CodingTypeModelData> getCodingTypeListStore() {
		return codingTypeListStore;
	}

	public ComboBox<CodingTypeModelData> getCodingType() {
		return codingType;
	}

	public TextField<String> getCodingName() {
		return codingName;
	}

	public Button getGetArchive() {
		return getArchive;
	}

	public static Integer getWIDTH() {
		return WIDTH;
	}

	public TextField<String> getSource() {
		return source;
	}
	
	
}