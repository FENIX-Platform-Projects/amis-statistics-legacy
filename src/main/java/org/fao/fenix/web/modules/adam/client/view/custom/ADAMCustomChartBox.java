package org.fao.fenix.web.modules.adam.client.view.custom;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.view.ADAMComboMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMCustomComboMultiSelection;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;


public class ADAMCustomChartBox {
	
	private HorizontalPanel xAxis;
	
	private HorizontalPanel yAxis;
	
	private HorizontalPanel quantityValue;
	
	private ListStore<GaulModelData> xAxisStore;

	private ComboBox<GaulModelData> xAxisList;
	
	private ListStore<GaulModelData> yAxisStore;

	private ComboBox<GaulModelData> yAxisList;
	
	private ADAMCustomComboMultiSelection adamComboXMultiSelection;
	
	private ADAMCustomComboMultiSelection adamComboYMultiSelection;
	
	private ADAMCustomSelectionBox adamCustomSelectionBox; 

	public ADAMCustomChartBox(ADAMCustomSelectionBox selectionBox){
		this.setAdamCustomSelectionBox(selectionBox);
		xAxisStore = new ListStore<GaulModelData>();
		xAxisList = new ComboBox<GaulModelData>();
		yAxisStore = new ListStore<GaulModelData>();
		yAxisList = new ComboBox<GaulModelData>();
		adamComboXMultiSelection = new ADAMCustomComboMultiSelection();
		adamComboYMultiSelection = new ADAMCustomComboMultiSelection();
		xAxis = new HorizontalPanel();
		yAxis = new HorizontalPanel();
	}
	
	
	public VerticalPanel buildBarChartBox() {
		VerticalPanel panel = new VerticalPanel();
		panel.add(selectXAxisPanel());
		panel.add(addSpace());
//		panel.add(selectYAxisPanel());
		return panel;
	}
	
	public VerticalPanel buildPieChartBox() {
		VerticalPanel panel = new VerticalPanel();
		panel.add(selectXAxisPanel());
		panel.add(addSpace());
//		panel.add(selectYAxisPanel());
		return panel;
	}
	
	private HorizontalPanel addSpace() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHeight(5);
		return panel;
	}
	
	private FieldSet selectYAxisPanel() {
		FieldSet panel = new FieldSet();
		panel.setCollapsible(true);
		panel.setHeading("Y-AXIS");
		HorizontalPanel listPanel = new HorizontalPanel();
		panel.setBorders(true);
//		panel.setSpacing(5);
		panel.setBorders(true);
		panel.setWidth(ADAMConstants.SELECTION_AXIS_WIDTH);
		Html title = new Html("<div class='small-content'> Select Y Axis:</div>");
		title.setWidth(ADAMConstants.SELECTION_TITLE_WIDTH);
		listPanel.setSpacing(5);
		listPanel.add(title);
		listPanel.add(buildYAxisBox());
		panel.add(listPanel);
		panel.add(yAxis);
		return panel;
	}
	
	private FieldSet selectXAxisPanel() {
		FieldSet panel = new FieldSet();
		panel.setCollapsible(true);
		panel.setHeading("X-AXIS");
		HorizontalPanel listPanel = new HorizontalPanel();
		panel.setBorders(true);
//		panel.setSpacing(5);
		panel.setWidth(ADAMConstants.SELECTION_AXIS_WIDTH);
		Html title = new Html("<div class='small-content'> Select X Axis:</div>");
		title.setWidth(ADAMConstants.SELECTION_TITLE_WIDTH);
		listPanel.setSpacing(5);
		listPanel.add(title);
		listPanel.add(buildXAxisBox());
		panel.add(listPanel);
		panel.add(xAxis);
		return panel;
	}
	
	private ComboBox<GaulModelData> buildXAxisBox() {
		xAxisStore = new ListStore<GaulModelData>();
		xAxisList.setTriggerAction(TriggerAction.ALL);
		xAxisList.setStore(xAxisStore);
		xAxisList.setDisplayField("gaulLabel");
		xAxisList.setEmptyText("Dimension");
		xAxisList.setWidth(ADAMConstants.SELECTION_LIST_WIDTH);
		xAxisStore.add(defaultAxis());
		xAxisList.addSelectionChangedListener(ADAMCustomController.listBoxSelectionXaxis(adamCustomSelectionBox, xAxisList, this));
		xAxisList.setValue(xAxisStore.getAt(0));
		return xAxisList;
	}
	
	private ComboBox<GaulModelData> buildYAxisBox() {
		yAxisStore = new ListStore<GaulModelData>();
		yAxisList.setTriggerAction(TriggerAction.ALL);
		yAxisList.setStore(yAxisStore);
		yAxisList.setDisplayField("gaulLabel");
		yAxisList.setEmptyText("Dimension");
		yAxisList.setWidth(ADAMConstants.SELECTION_LIST_WIDTH);
		yAxisStore.add(defaultAxis());
		yAxisList.addSelectionChangedListener(ADAMCustomController.listBoxSelectionYaxis(yAxisList, this));
		yAxisList.setValue(yAxisStore.getAt(0));
		return yAxisList;
	}
	
	
	private List<GaulModelData> defaultAxis(){
		List<GaulModelData> list = new ArrayList<GaulModelData>();
		GaulModelData g = new GaulModelData();
		g.setGaulCode("recipientcode");
		g.setGaulLabel("Recipient Country");
		list.add(g);
		g = new GaulModelData();
		g.setGaulCode("donorcode");
		g.setGaulLabel("Resource Partner");
		list.add(g);
		g = new GaulModelData();
		g.setGaulCode("dac_sector");
		g.setGaulLabel("OECD-DAC Sector");
		list.add(g);
		g = new GaulModelData();
		g.setGaulCode("year");
		g.setGaulLabel("Date");
		list.add(g);
		return list;
	}


	public HorizontalPanel getxAxis() {
		return xAxis;
	}


	public void setxAxis(HorizontalPanel xAxis) {
		this.xAxis = xAxis;
	}


	public HorizontalPanel getyAxis() {
		return yAxis;
	}


	public void setyAxis(HorizontalPanel yAxis) {
		this.yAxis = yAxis;
	}


	public HorizontalPanel getQuantityValue() {
		return quantityValue;
	}


	public void setQuantityValue(HorizontalPanel quantityValue) {
		this.quantityValue = quantityValue;
	}


	public ListStore<GaulModelData> getxAxisStore() {
		return xAxisStore;
	}


	public void setxAxisStore(ListStore<GaulModelData> xAxisStore) {
		this.xAxisStore = xAxisStore;
	}


	public ComboBox<GaulModelData> getxAxisList() {
		return xAxisList;
	}


	public void setxAxisList(ComboBox<GaulModelData> xAxisList) {
		this.xAxisList = xAxisList;
	}


	public ListStore<GaulModelData> getyAxisStore() {
		return yAxisStore;
	}


	public void setyAxisStore(ListStore<GaulModelData> yAxisStore) {
		this.yAxisStore = yAxisStore;
	}


	public ComboBox<GaulModelData> getyAxisList() {
		return yAxisList;
	}


	public void setyAxisList(ComboBox<GaulModelData> yAxisList) {
		this.yAxisList = yAxisList;
	}


	public ADAMCustomComboMultiSelection getAdamComboXMultiSelection() {
		return adamComboXMultiSelection;
	}


	public void setAdamComboXMultiSelection(
			ADAMCustomComboMultiSelection adamComboXMultiSelection) {
		this.adamComboXMultiSelection = adamComboXMultiSelection;
	}


	public ADAMCustomComboMultiSelection getAdamComboYMultiSelection() {
		return adamComboYMultiSelection;
	}


	public void setAdamComboYMultiSelection(
			ADAMCustomComboMultiSelection adamComboYMultiSelection) {
		this.adamComboYMultiSelection = adamComboYMultiSelection;
	}


	public ADAMCustomSelectionBox getAdamCustomSelectionBox() {
		return adamCustomSelectionBox;
	}


	public void setAdamCustomSelectionBox(
			ADAMCustomSelectionBox adamCustomSelectionBox) {
		this.adamCustomSelectionBox = adamCustomSelectionBox;
	}





	
}
