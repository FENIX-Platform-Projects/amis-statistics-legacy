package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.core.client.uploader.control.UploaderController;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEController;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;

public class StepC extends ExcelImporterStepPanel {

	private VerticalPanel wrapperPanel;
	
	private ListStore<GaulModelData> gaulStore;
	
	private ComboBox<GaulModelData> gaulList;
	
	private SimpleComboBox<String> periodTypeCodeCombo;
	
	private TextField<String> sourceField;
	
	private final static String FIELD_WIDTH = "400px";
	
	public final static String LABEL_WIDTH = "150px";
	
	public StepC(String suggestion, String width) {
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
		wrapperPanel.add(buildPeriodTypeCode());
		return wrapperPanel;
	}
	
	private HorizontalPanel buildSource() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		sourceField = new TextField<String>();
		sourceField.setWidth(FIELD_WIDTH);
		sourceField.setAllowBlank(false);
		sourceField.setAutoValidate(true);
		panel.add(createLabel(BabelFish.print().source(), LABEL_WIDTH));
		panel.add(sourceField);
		return panel;
	}
	
	private HorizontalPanel buildRegion() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		gaulList.setWidth(FIELD_WIDTH);
		gaulList.setAllowBlank(false);
		gaulList.setAutoValidate(true);
		panel.add(createLabel(BabelFish.print().region(), LABEL_WIDTH));
		panel.add(gaulList);
		return panel;
	}
	
	private HorizontalPanel buildPeriodTypeCode() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		periodTypeCodeCombo = new SimpleComboBox<String>();
		periodTypeCodeCombo.setTriggerAction(TriggerAction.ALL);
		periodTypeCodeCombo.setToolTip(new ToolTipConfig(BabelFish.print().information(), BabelFish.print().periodTypeCode()));   
		periodTypeCodeCombo.setLabelStyle("font-weight:bold;");
		periodTypeCodeCombo.add("daily");
		periodTypeCodeCombo.add("monthly");
		periodTypeCodeCombo.add("yearly");
		periodTypeCodeCombo.addListener(Events.SelectionChange, UploaderController.getComboBoxListener(periodTypeCodeCombo));
		periodTypeCodeCombo.setWidth(FIELD_WIDTH);
		periodTypeCodeCombo.setAllowBlank(false);
		periodTypeCodeCombo.setAutoValidate(true);
		panel.add(createLabel(BabelFish.print().periodTypeCode(), LABEL_WIDTH));
		panel.add(periodTypeCodeCombo);
		return panel;
	}

	public ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public SimpleComboBox<String> getPeriodTypeCodeCombo() {
		return periodTypeCodeCombo;
	}

	public TextField<String> getSourceField() {
		return sourceField;
	}
	
}