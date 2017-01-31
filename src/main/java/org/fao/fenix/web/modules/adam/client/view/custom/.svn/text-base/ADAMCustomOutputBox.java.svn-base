package org.fao.fenix.web.modules.adam.client.view.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMTableController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMChartMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMTableMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.jfree.chart.ChartPanel;

import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.RootPanel;


public class ADAMCustomOutputBox {
	
	private VerticalPanel panel;
	
	private HorizontalPanel outputPanel;
	
	private VerticalPanel selectionPanel;
	
	private ListStore<GaulModelData> outputTypeStore;

	private ComboBox<GaulModelData> outputTypeList;
	
	private ADAMCustomChartBox adamCustomChartBox;
	
	private ADAMCustomSelectionBox adamCustomSelectionBox; 

	public ADAMCustomOutputBox(ADAMCustomSelectionBox selectionBox) {
		this.setAdamCustomSelectionBox(selectionBox);
		panel = new VerticalPanel();	
		panel.setSpacing(5);
		outputTypeStore = new ListStore<GaulModelData>();
		outputTypeList = new ComboBox<GaulModelData>();
		selectionPanel = new VerticalPanel();
		outputPanel = new HorizontalPanel();
		adamCustomChartBox = new ADAMCustomChartBox(adamCustomSelectionBox);
		outputPanel.setSpacing(5);
	}
	
	public VerticalPanel build() {
		panel.add(outputPanel());
		panel.add(selectionPanel);
//		panel.add(typeList);
		return panel;
	}
	
	private HorizontalPanel outputPanel() {
		Html title = new Html("<div class='small-content'> Select an output:</div>");
		title.setWidth(ADAMConstants.SELECTION_TITLE_WIDTH);
		outputPanel.add(title);
		outputPanel.add(buildOutputComboBox());
		
		return outputPanel;
	}
	
	
	private ComboBox<GaulModelData> buildOutputComboBox() {
		outputTypeStore = new ListStore<GaulModelData>();
		outputTypeList.setTriggerAction(TriggerAction.ALL);
		outputTypeList.setStore(outputTypeStore);
		outputTypeList.setDisplayField("gaulLabel");
		outputTypeList.setEmptyText("Output");
		outputTypeList.setWidth(ADAMConstants.SELECTION_LIST_WIDTH);
		outputTypeStore.add(defaultOutputs());
		outputTypeList.addSelectionChangedListener(entityListener(outputTypeList));
		outputTypeList.setValue(outputTypeStore.getAt(0));
		return outputTypeList;
	}
	
	
	private List<GaulModelData> defaultOutputs(){
		List<GaulModelData> list = new ArrayList<GaulModelData>();
		GaulModelData g = new GaulModelData();
		g.setGaulCode(ADAMBoxContent.BAR.toString());
		g.setGaulLabel("Bar Chart");
		list.add(g);
		g = new GaulModelData();
		g.setGaulCode(ADAMBoxContent.PIE.toString());
		g.setGaulLabel("Pie Chart");
		list.add(g);
		return list;
	}

	
	
	
	public SelectionChangedListener<GaulModelData> entityListener(final ComboBox<GaulModelData> entityList) {
		return new SelectionChangedListener<GaulModelData>() {
			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
				String type = entityList.getSelection().get(0).getGaulCode();

				ADAMBoxContent c = ADAMBoxContent.valueOf(type);
				switch(c) {
					case BAR:
						System.out.println("BULDING BAR");
						selectionPanel.removeAll();
						adamCustomChartBox = new ADAMCustomChartBox(adamCustomSelectionBox);
						selectionPanel.add(adamCustomChartBox.buildBarChartBox());
						panel.layout();
					break;

					case PIE:
						System.out.println("BULDING PIE");
						selectionPanel.removeAll();
						adamCustomChartBox = new ADAMCustomChartBox(adamCustomSelectionBox);
						selectionPanel.add(adamCustomChartBox.buildPieChartBox());
						panel.layout();
					break;

					case MAP:
					break;

					case VENN_PRIORITIES:
					break;
				}
			}
		};
	}
	

	
	public ListStore<GaulModelData> getOutputTypeStore() {
		return outputTypeStore;
	}

	public void setOutputTypeStore(ListStore<GaulModelData> outputTypeStore) {
		this.outputTypeStore = outputTypeStore;
	}

	public ComboBox<GaulModelData> getOutputTypeList() {
		return outputTypeList;
	}

	public void setOutputTypeList(ComboBox<GaulModelData> outputTypeList) {
		this.outputTypeList = outputTypeList;
	}

	public ADAMCustomChartBox getAdamCustomChartBox() {
		return adamCustomChartBox;
	}

	public ADAMCustomSelectionBox getAdamCustomSelectionBox() {
		return adamCustomSelectionBox;
	}

	public void setAdamCustomSelectionBox(
			ADAMCustomSelectionBox adamCustomSelectionBox) {
		this.adamCustomSelectionBox = adamCustomSelectionBox;
	}
	
	
	
	
	
}
