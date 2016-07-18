package org.fao.fenix.web.modules.r.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.r.client.control.RController;
import org.fao.fenix.web.modules.r.common.vo.RFunctionVO;
import org.fao.fenix.web.modules.r.common.vo.ROptionVO;
import org.fao.fenix.web.modules.table.client.view.TableViewWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.ListBox;

public class RFunctionPanel {

	private FormPanel panel;
	
	private Button closeButton;
	
	private Button calculateButton;
	
	private ListBox dimensionList;
	
	private RMainVariableFieldSet rMainVariableFieldSet;
	
	private HorizontalPanel otherVariablesWrapper;
	
	private List<VerticalPanel> otherDimensions;
	
	private List<HorizontalPanel> options;
	
	private final static String LABEL_WIDTH = "100px";
	
	private final static String FIELD_WIDTH = "150px";
	
	private final static String FIELD_HEIGHT = "75px";
	
	private final static String LONG_WIDTH = "325px";
	
	private final static String FIELDSET_WIDTH = "675px";

	public RFunctionPanel() {
		panel = new FormPanel();
		panel.setFrame(false);
		panel.setHeaderVisible(false);
		panel.setScrollMode(Scroll.AUTO);
		closeButton = new Button(BabelFish.print().close());
		calculateButton = new Button(BabelFish.print().calculate());
		dimensionList = new ListBox();
		otherVariablesWrapper = new HorizontalPanel();
		rMainVariableFieldSet = new RMainVariableFieldSet();
		otherDimensions = new ArrayList<VerticalPanel>();
		options = new ArrayList<HorizontalPanel>();
	}

	public FormPanel build(TableWindow tw, RFunctionVO vo) {
		TableVO tvo = (TableVO)tw.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
		panel.add(buildDescriptionFieldSet(vo));
		panel.add(rMainVariableFieldSet.build(tvo));
		panel.add(buildGroupByFieldSet(tw, vo));
		panel.add(buildAvailableOptionsFieldSet(vo));
		panel.setBottomComponent(buildButtonsPanel());
		RController.createOtherDimensions(tvo, this);
		return panel;
	}
	
	public FormPanel build(TableViewWindow tw, RFunctionVO vo) {
		TableVO tvo = (TableVO)tw.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
		panel.add(buildDescriptionFieldSet(vo));
		panel.add(rMainVariableFieldSet.build(tvo));
		panel.add(buildGroupByFieldSet(tw, vo));
		panel.add(buildAvailableOptionsFieldSet(vo));
		panel.setBottomComponent(buildButtonsPanel());
		RController.createOtherDimensions(tvo, this);
		return panel;
	}
	
	private FieldSet buildDescriptionFieldSet(RFunctionVO vo) {
		FieldSet descriptionFieldSet = new FieldSet();
		descriptionFieldSet.setHeading(BabelFish.print().description());
		descriptionFieldSet.setCheckboxToggle(true);
		descriptionFieldSet.collapse();
		descriptionFieldSet.add(new Html(vo.getDescription()));
		descriptionFieldSet.setWidth(FIELDSET_WIDTH);
		return descriptionFieldSet;
	}
	
	private FieldSet buildGroupByFieldSet(TableWindow tw, RFunctionVO vo) {
		FieldSet groupByFieldSet = new FieldSet();
		groupByFieldSet.setHeading(BabelFish.print().groupBy());
		groupByFieldSet.setCheckboxToggle(true);
		groupByFieldSet.collapse();
		groupByFieldSet.add(otherVariablesWrapper);
		groupByFieldSet.setWidth(FIELDSET_WIDTH);
		groupByFieldSet.setScrollMode(Scroll.AUTOX);
		return groupByFieldSet;
	}
	
	private FieldSet buildGroupByFieldSet(TableViewWindow tw, RFunctionVO vo) {
		FieldSet groupByFieldSet = new FieldSet();
		groupByFieldSet.setHeading(BabelFish.print().groupBy());
		groupByFieldSet.setCheckboxToggle(true);
		groupByFieldSet.collapse();
		groupByFieldSet.add(otherVariablesWrapper);
		groupByFieldSet.setWidth(FIELDSET_WIDTH);
		groupByFieldSet.setScrollMode(Scroll.AUTOX);
		return groupByFieldSet;
	}
	
	private FieldSet buildAvailableOptionsFieldSet(RFunctionVO vo) {
		FieldSet availableOptionsFieldSet = new FieldSet();
		availableOptionsFieldSet.setHeading(BabelFish.print().availableOptions());
		availableOptionsFieldSet.setCheckboxToggle(true);
		availableOptionsFieldSet.collapse();
		availableOptionsFieldSet.setWidth(FIELDSET_WIDTH);
		VerticalPanel optionsWrapper = new VerticalPanel();
		HorizontalPanel row = new HorizontalPanel();
		if (vo.getOptions() != null) {
			for (int i = 0 ; i < vo.getOptions().size() ; i++) {
				ROptionVO ovo = vo.getOptions().get(i);
				row.add(buildOptionPanel(ovo.getOptionName(), ovo.getDefaultValue(), ovo.getOptionDescription(), ovo.getOptionValues()));
				if ((i % 2 != 0) || (i == vo.getOptions().size() - 1)) {
					optionsWrapper.add(row);
					row = new HorizontalPanel();
				}
			}
		}
		availableOptionsFieldSet.add(optionsWrapper);
		return availableOptionsFieldSet;
	}
	
	private HorizontalPanel buildOptionPanel(String optionName, String defaultValue, String optionDescription, List<String> optionValues) {
		HorizontalPanel optionPanel = new HorizontalPanel();
		optionPanel.setSpacing(3);
		CheckBox checkBox = new CheckBox();
		optionPanel.add(checkBox);
		Html label = new Html(optionName);
		label.setWidth(LABEL_WIDTH);
		optionPanel.add(label);
		if ((optionValues == null) || optionValues.isEmpty()) {
			TextField<String> optionValue = new TextField<String>();
			optionValue.setWidth(FIELD_WIDTH);
			optionValue.setValue(defaultValue);
			optionPanel.add(optionValue);
			optionPanel.setData("ISLIST", false);
			optionPanel.setData("TEXTFIELD", optionValue);
		} else {
			ListBox optionValueList = new ListBox();
			for (String optionValue : optionValues)
				optionValueList.addItem(optionValue);
			optionValueList.setWidth(FIELD_WIDTH);
			optionPanel.add(optionValueList);
			optionPanel.setData("ISLIST", true);
			optionPanel.setData("LISTBOX", optionValueList);
		}
		IconButton infoButton = new IconButton("info");
		infoButton.setToolTip(optionDescription);
		optionPanel.add(infoButton);
		optionPanel.setData("CHECKBOX", checkBox);
		optionPanel.setData("OPTIONNAME", optionName);
		options.add(optionPanel);
		return optionPanel;
	}
	
	public VerticalPanel buildGroupDimensionPanel(String header, String datatType, Map<String, String> valueCodeMap) {
		VerticalPanel groupDimensionPanel = new VerticalPanel();
		groupDimensionPanel.setSpacing(3);
		Html label = new Html("<b>" + header + "</b>");
		ListBox listbox = new ListBox();
		listbox.setVisibleItemCount(5);
		for (String key : valueCodeMap.keySet())
			listbox.addItem(key, valueCodeMap.get(key));
		listbox.setWidth(FIELD_WIDTH);
		listbox.setEnabled(false);
		listbox.setMultipleSelect(true);
		CheckBox checkbox = new CheckBox();
		checkbox.setBoxLabel("Use for Group");
		checkbox.addListener(Events.OnClick, RController.enableOtherVariablesList(checkbox, listbox));
		groupDimensionPanel.add(label);
		groupDimensionPanel.add(listbox);
		groupDimensionPanel.add(checkbox);
		groupDimensionPanel.setData("CHECKBOX", checkbox);
		groupDimensionPanel.setData("LISTBOX", listbox);
		groupDimensionPanel.setData("HEADER", header);
		groupDimensionPanel.setData("DATATYPE", datatType);
		otherDimensions.add(groupDimensionPanel);
		return groupDimensionPanel;
	}
	
	private HorizontalPanel buildButtonsPanel() {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		buttonsPanel.add(calculateButton);
		buttonsPanel.add(closeButton);
		return buttonsPanel;
	}

	public Button getCloseButton() {
		return closeButton;
	}

	public Button getCalculateButton() {
		return calculateButton;
	}

	public ListBox getDimensionList() {
		return dimensionList;
	}

	public HorizontalPanel getOtherVariablesWrapper() {
		return otherVariablesWrapper;
	}

	public RMainVariableFieldSet getrMainVariableFieldSet() {
		return rMainVariableFieldSet;
	}

	public List<VerticalPanel> getOtherDimensions() {
		return otherDimensions;
	}

	public List<HorizontalPanel> getOptions() {
		return options;
	}

}
