package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class ImageImporterStepB extends ExcelImporterStepPanel {

	private VerticalPanel wrapperPanel;

	private ListStore<GaulModelData> gaulStore;

	private ComboBox<GaulModelData> gaulList;

	private TextField<String> sourceField;

	private final static String FIELD_WIDTH = "400px";

	public final static String LABEL_WIDTH = "150px";

	public ImageImporterStepB(String suggestion, String width) {
		super(suggestion, width);
		gaulStore = new ListStore<GaulModelData>();
		MEController.fillGaulStore(gaulStore);
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		this.getLayoutContainer().add(buildWrapperPanel());
	}

	private VerticalPanel buildWrapperPanel() {
		wrapperPanel = new VerticalPanel();
		wrapperPanel.setSpacing(5);
		wrapperPanel.add(buildSource());
		wrapperPanel.add(buildRegion());
		return wrapperPanel;
	}

	private HorizontalPanel buildSource() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		sourceField = new TextField<String>();
		sourceField.setWidth(FIELD_WIDTH);
		sourceField.setAllowBlank(false);
		sourceField.setAutoValidate(true);
		panel.add(createLabel("<font color='#CA1616'>" + BabelFish.print().source() + "</font>", LABEL_WIDTH));
		panel.add(sourceField);
		return panel;
	}

	private HorizontalPanel buildRegion() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		gaulList.setWidth(FIELD_WIDTH);
		gaulList.setAllowBlank(false);
		gaulList.setAutoValidate(true);
		panel.add(createLabel("<font color='#CA1616'>" + BabelFish.print().region() + "</font>", LABEL_WIDTH));
		panel.add(gaulList);
		return panel;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public TextField<String> getSourceField() {
		return sourceField;
	}

}
