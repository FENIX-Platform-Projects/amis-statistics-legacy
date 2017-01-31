package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class StepM extends ExcelImporterStepPanel {

	private VerticalPanel wrapper;
	
	/** Containing Keywords and Region*/
	private VerticalPanel leftWrapper;
	
	/** Containing Code and Category */
	private VerticalPanel rightWrapper;
	
	/** Containing <code>leftWrapper</code> and <code>rightWrapper</code> */
	private HorizontalPanel horizontalWrapper;
	
	private TextField<String> titleField;
	
	private TextField<String> keywordsField;
	
	private TextField<String> codeField;
	
	private TextArea textArea;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private ListStore<CategoryModelData> categoryStore;
	
	private ComboBox<CategoryModelData> categoryList;
	
	private final static String LONG_FIELD_WIDTH = "550px";
	
	private final static String SHORT_FIELD_WIDTH = "265px";
	
	private final static String LABEL_WIDTH = "250px";
	
	private final static String RED = "#CA1616";
	
	private final static int SPACING = 3;
	
	public StepM(String suggestion, String width) {
		super(suggestion, width);
		categoryStore = new ListStore<CategoryModelData>();
		categoryList = new ComboBox<CategoryModelData>();
		categoryList.setTriggerAction(TriggerAction.ALL);
		categoryList.setStore(categoryStore);
		gaulStore = new ListStore<GaulModelData>();
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		MEController.fillCategoryPanel(categoryStore);
		MEController.fillGaulStore(gaulStore);
		this.getLayoutContainer().add(buildWrapperPanel());
	}
	
	private VerticalPanel buildWrapperPanel() {
		wrapper = new VerticalPanel();
		wrapper.setSpacing(SPACING);
		wrapper.add(buildTitlePanel());
		wrapper.add(buildHorizontalWrapper());
		wrapper.add(buildAbstractPanel());
		return wrapper;
	}
	
	private HorizontalPanel buildHorizontalWrapper() {
		horizontalWrapper = new HorizontalPanel();
		horizontalWrapper.add(buildLeftWrapper());
		horizontalWrapper.add(buildRightWrapper());
		return horizontalWrapper;
	}
	
	private VerticalPanel buildRightWrapper() {
		rightWrapper = new VerticalPanel();
		rightWrapper.setSpacing(SPACING);
		rightWrapper.add(buildCodePanel());
		rightWrapper.add(buildCategoryPanel());
		return rightWrapper;
	}
	
	private VerticalPanel buildLeftWrapper() {
		leftWrapper = new VerticalPanel();
		leftWrapper.setSpacing(SPACING);
		leftWrapper.add(buildKeywordsPanel());
		leftWrapper.add(buildRegionPanel());
		return leftWrapper;
	}
	
	private VerticalPanel buildCategoryPanel() {
		VerticalPanel categoryPanel = new VerticalPanel();
		categoryPanel.setSpacing(SPACING);
		Html label = createLabel(BabelFish.print().metadataCategories(), LABEL_WIDTH, RED);
		categoryList.setDisplayField("categoryName");
		categoryList.setWidth(SHORT_FIELD_WIDTH);
		categoryList.setAllowBlank(false);
		categoryList.setAutoValidate(true);
		categoryPanel.add(label);
		categoryPanel.add(categoryList);
		return categoryPanel;
	}
	
	private VerticalPanel buildRegionPanel() {
		VerticalPanel regionPanel = new VerticalPanel();
		regionPanel.setSpacing(SPACING);
		Html label = createLabel(BabelFish.print().metadataRegion(), LABEL_WIDTH, RED);
		gaulList.setDisplayField("gaulLabel");
		gaulList.setWidth(SHORT_FIELD_WIDTH);
		gaulList.setAllowBlank(false);
		gaulList.setAutoValidate(true);
		regionPanel.add(label);
		regionPanel.add(gaulList);
		return regionPanel;
	}
	
	private VerticalPanel buildCodePanel() {
		VerticalPanel codePanel = new VerticalPanel();
		codePanel.setSpacing(SPACING);
		Html label = createLabel(BabelFish.print().metadataCode(), LABEL_WIDTH);
		codeField = new TextField<String>();
		codeField.setWidth(SHORT_FIELD_WIDTH);
		codeField.setAllowBlank(true);
		codeField.setAutoValidate(true);
		codePanel.add(label);
		codePanel.add(codeField);
		return codePanel;
	}
	
	private VerticalPanel buildKeywordsPanel() {
		VerticalPanel keywordsPanel = new VerticalPanel();
		keywordsPanel.setSpacing(SPACING);
		Html label = createLabel(BabelFish.print().keywords(), LABEL_WIDTH, RED);
		keywordsField = new TextField<String>();
		keywordsField.setWidth(SHORT_FIELD_WIDTH);
		keywordsField.setAllowBlank(false);
		keywordsField.setAutoValidate(true);
		keywordsPanel.add(label);
		keywordsPanel.add(keywordsField);
		return keywordsPanel;
	}
	
	private VerticalPanel buildTitlePanel() {
		VerticalPanel titlePanel = new VerticalPanel();
		titlePanel.setSpacing(SPACING);
		Html label = createLabel(BabelFish.print().title(), LABEL_WIDTH, RED);
		titleField = new TextField<String>();
		titleField.setWidth(LONG_FIELD_WIDTH);
		titleField.setAllowBlank(false);
		titleField.setAutoValidate(true);
		titlePanel.add(label);
		titlePanel.add(titleField);
		return titlePanel;
	}
	
	private VerticalPanel buildAbstractPanel() {
		VerticalPanel abstractPanel = new VerticalPanel();
		abstractPanel.setSpacing(SPACING);
		Html label = createLabel(BabelFish.print().abstractAbstract(), LABEL_WIDTH);
		textArea = new TextArea();
		textArea.setWidth(LONG_FIELD_WIDTH);
		textArea.setAllowBlank(true);
		textArea.setAutoValidate(true);
		textArea.setHeight("35px");
		abstractPanel.add(label);
		abstractPanel.add(textArea);
		return abstractPanel;
	}

	public TextField<String> getTitleField() {
		return titleField;
	}

	public TextField<String> getKeywordsField() {
		return keywordsField;
	}

	public TextField<String> getCodeField() {
		return codeField;
	}

	public TextArea getTextArea() {
		return textArea;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public ComboBox<CategoryModelData> getCategoryList() {
		return categoryList;
	}
	
}
