package org.fao.fenix.web.modules.r.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.r.client.control.RController;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.google.gwt.user.client.ui.ListBox;

public class RMainVariableFieldSet {

	private FieldSet fieldSet;
	
	private Html instructions;
	
	private ListBox variableList;
	
	private ListBox leftList;
	
	private ListBox rightList;
	
	private Button addButton;
	
	private Button addAllButton;
	
	private Button removeButton;
	
	private Button removeAllButton;
	
	private HorizontalPanel wrapper;
	
	private VerticalPanel variablePanel;
	
	private VerticalPanel leftListPanel;
	
	private VerticalPanel buttonsPanel;
	
	private VerticalPanel rightListPanel;
	
	private final static int SPACING = 3;
	
	private final static String FIELDSET_WIDTH = "675px";
	
	private final static String LABEL_WIDTH = "100px";
	
	private final static String FIELD_WIDTH = "150px";
	
	public RMainVariableFieldSet() {
		fieldSet = new FieldSet();
		fieldSet.setWidth(FIELDSET_WIDTH);
		fieldSet.setHeading(BabelFish.print().variableOfInterest());
		fieldSet.setCheckboxToggle(true);
		instructions = new Html(BabelFish.print().variableOfInterestDescription());
		leftList = new ListBox();
		rightList = new ListBox();
		variableList = new ListBox();
		addButton = new Button(">");
		addAllButton = new Button(">>");
		removeButton = new Button("<");
		removeAllButton = new Button("<<");
		leftList.setWidth(FIELD_WIDTH);
		leftList.setMultipleSelect(true);
		rightList.setWidth(FIELD_WIDTH);
		rightList.setMultipleSelect(true);
		variableList.setWidth(FIELD_WIDTH);
		addButton.setWidth(FIELD_WIDTH);
		addButton.addSelectionListener(RController.add(leftList, rightList));
		addAllButton.setWidth(FIELD_WIDTH);
		addAllButton.addSelectionListener(RController.addAll(leftList, rightList));
		removeButton.setWidth(FIELD_WIDTH);
		removeButton.addSelectionListener(RController.remove(rightList));
		removeAllButton.setWidth(FIELD_WIDTH);
		removeAllButton.addSelectionListener(RController.removeAll(rightList));
	}
	
	public FieldSet build(TableVO tvo) {
		wrapper = new HorizontalPanel();
		wrapper.setSpacing(SPACING);
		wrapper.add(buildVariablePanel(tvo));
		wrapper.add(buildLeftListPanel());
		wrapper.add(buildButtonsPanel());
		wrapper.add(buildRightListPanel());
		fieldSet.add(wrapper);
		return fieldSet;
	}
	
	private VerticalPanel buildVariablePanel(TableVO tvo) {
		variablePanel = new VerticalPanel();
		variablePanel.setSpacing(SPACING);
		variablePanel.setBorders(true);
		Html label = new Html("<b>" + BabelFish.print().variableOfInterest() + "</b>");
		label.setWidth(LABEL_WIDTH);
		variableList.addItem(BabelFish.print().pleaseSelectADimension(), "NULL");
		for (String header : tvo.getDimensionDescriptorMap().keySet()) {
			DimensionBeanVO dbvo = tvo.getDimensionDescriptorMap().get(header);
			if (dbvo.getDistinctDimensionValues().size() > 1)
				variableList.addItem(dbvo.getHeader(), header);
		}
		variableList.addChangeHandler(RController.mainVariableChangeListener(tvo, variableList, leftList, rightList));
		variablePanel.add(label);
		variablePanel.add(variableList);
		instructions.setWidth(FIELD_WIDTH);
		variablePanel.add(instructions);
		return variablePanel;
	}
	
	private VerticalPanel buildLeftListPanel() {
		leftListPanel = new VerticalPanel();
		leftListPanel.setSpacing(SPACING);
		leftListPanel.setBorders(true);
		leftList.setVisibleItemCount(7);
		leftListPanel.add(leftList);
		return leftListPanel;
	}
	
	private VerticalPanel buildButtonsPanel() {
		buttonsPanel = new VerticalPanel();
		buttonsPanel.setSpacing(SPACING);
		buttonsPanel.setBorders(true);
		buttonsPanel.add(addButton);
		buttonsPanel.add(addAllButton);
		buttonsPanel.add(removeButton);
		buttonsPanel.add(removeAllButton);
		return buttonsPanel;
	}
	
	private VerticalPanel buildRightListPanel() {
		rightListPanel = new VerticalPanel();
		rightListPanel.setSpacing(SPACING);
		rightListPanel.setBorders(true);
		rightList.setVisibleItemCount(7);
		rightListPanel.add(rightList);
		return rightListPanel;
	}

	public ListBox getVariableList() {
		return variableList;
	}

	public ListBox getRightList() {
		return rightList;
	}
	
}