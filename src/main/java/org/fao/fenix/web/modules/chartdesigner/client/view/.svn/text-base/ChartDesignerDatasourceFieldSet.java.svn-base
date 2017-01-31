package org.fao.fenix.web.modules.chartdesigner.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

public class ChartDesignerDatasourceFieldSet {

	private FieldSet fieldSet;
	
	private VerticalPanel wrapper;
	
	private Button addDatasetButton;
	
	private final static String GRID_WIDTH = "825px";

	private final static String TITLE_WIDTH = "315px";
	
	private final static String CHECKBOX_WIDTH = "345px";
	
	private final static String CHART_TYPE_LIST_WIDTH = "125px";
	
	private final static int SPACING = 3;
	
	private List<ComboBox<DescriptorModelData>> descriptorComboBoxes;
	
	private List<DatasetVO> datasets;
	
	private CheckBox useThisDataset;
	
	private ListStore<ChartTypeModelData> chartTypeStore;
	
	private ComboBox<ChartTypeModelData> chartTypeList;
	
	private String selectedChartType;
	
	public ChartDesignerDatasourceFieldSet(String selectedChartType) {
		this.setSelectedChartType(selectedChartType);
		fieldSet = new FieldSet();
		wrapper = new VerticalPanel();
		wrapper.setLayout(new FillLayout());
		addDatasetButton = new Button("Select Dataset");
		addDatasetButton.setIconStyle("addDataset");
		descriptorComboBoxes = new ArrayList<ComboBox<DescriptorModelData>>();
		datasets = new ArrayList<DatasetVO>();
		useThisDataset = new CheckBox();
		chartTypeStore = new ListStore<ChartTypeModelData>();
		chartTypeList = new ComboBox<ChartTypeModelData>();
	}
	
	public FieldSet build(DatasetVO vo, ChartDesignerWindow w, boolean calculateYAxisMaxValue) {
		fieldSet.setCheckboxToggle(false);
		fieldSet.setHeading(vo.getDatasetName());
		fieldSet.setWidth(GRID_WIDTH);
		fieldSet.setBorders(true);
		fieldSet.add(buildFieldSetWrapper(vo, w, calculateYAxisMaxValue));
		datasets.add(vo);
		return fieldSet;
	}
	
	private HorizontalPanel buildNavigationPanel(ChartDesignerWindow w, Long datasetID) {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setHorizontalAlign(HorizontalAlignment.RIGHT);
		p.setSpacing(SPACING);
//		p.setBorders(true);
		p.setWidth(GRID_WIDTH);
		Button goToXButton = new Button("Go To X-Axis");
		goToXButton.setIconStyle("checklist");
		goToXButton.setWidth(CHART_TYPE_LIST_WIDTH);
		goToXButton.addSelectionListener(ChartDesignerController.goToXAxis(w));
		Button goToYButton = new Button("Go To Y-Axis");
		goToYButton.setIconStyle("checklist");
		goToYButton.setWidth(CHART_TYPE_LIST_WIDTH);
		goToYButton.addSelectionListener(ChartDesignerController.goToYAxis(w, datasetID));
		HorizontalPanel spacer = new HorizontalPanel();
		spacer.setWidth("340px");
		p.add(spacer);
		p.add(goToXButton);
		p.add(goToYButton);
		return p;
	}
	
	private VerticalPanel buildFieldSetWrapper(DatasetVO vo, ChartDesignerWindow w, boolean calculateYAxisMaxValue) {
		VerticalPanel p = new VerticalPanel();
		p.add(buildFieldSetBody(vo, w, calculateYAxisMaxValue));
//		p.add(buildNavigationPanel(w, Long.valueOf(vo.getDsId())));
		return p;
	}
	
	private HorizontalPanel buildFieldSetBody(DatasetVO vo, ChartDesignerWindow w, boolean calculateYAxisMaxValue) {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(SPACING);
		p.setWidth(GRID_WIDTH);
		useThisDataset = new CheckBox();
		useThisDataset.setBoxLabel("Plot This Dataset");
		useThisDataset.setValue(true);
		useThisDataset.setWidth(CHECKBOX_WIDTH);
		useThisDataset.setData("DATASET_ID", vo.getDsId());
		useThisDataset.setData("DATASET_NAME", vo.getDatasetName());
		useThisDataset.setData("DATASET_SOURCE", vo.getSourceName());
		useThisDataset.addListener(Events.OnClick, ChartDesignerController.useThisDatasetHandler(this, w));
		p.add(useThisDataset);
		p.add(createDescriptorList(vo));
		p.add(createChartTypeList(w, calculateYAxisMaxValue));
		return p;
	}
	
	private ComboBox<ChartTypeModelData> createChartTypeList(ChartDesignerWindow w, boolean calculateYAxisMaxValue) {
		chartTypeList.setTriggerAction(TriggerAction.ALL);
		chartTypeList.setStore(chartTypeStore);
		chartTypeList.setDisplayField("chartTypeLabel");
		chartTypeList.setWidth(CHART_TYPE_LIST_WIDTH);
		ChartTypeModelData bar = new ChartTypeModelData("BAR", "BAR");
		ChartTypeModelData boxplot = new ChartTypeModelData("BOXPLOT", "BOXPLOT");
		ChartTypeModelData line = new ChartTypeModelData("LINE", "LINE");
		ChartTypeModelData point = new ChartTypeModelData("POINT", "POINT");
		ChartTypeModelData target = new ChartTypeModelData("TARGET", "TARGET");
		chartTypeStore.add(bar);
		chartTypeStore.add(boxplot);
		chartTypeStore.add(line);
		chartTypeStore.add(point);
		chartTypeStore.add(target);
		for (int i = 0 ; i < chartTypeList.getStore().getCount() ; i++) 
			if (chartTypeList.getStore().getAt(i).getChartTypeCode().equalsIgnoreCase(this.getSelectedChartType())) 
				chartTypeList.setValue(chartTypeList.getStore().getAt(i));
		chartTypeList.addSelectionChangedListener(ChartDesignerController.chartTypeSelectionChangedListener(w, calculateYAxisMaxValue));
		return chartTypeList;
	}
	
	private ComboBox<DescriptorModelData> createDescriptorList(DatasetVO vo) {
		ListStore<DescriptorModelData> descriptorStore = new ListStore<DescriptorModelData>();
		ComboBox<DescriptorModelData> descriptorList = new ComboBox<DescriptorModelData>();
		descriptorList.setTriggerAction(TriggerAction.ALL);
		descriptorList.setStore(descriptorStore);
		descriptorList.setDisplayField("header");
		descriptorList.setWidth(TITLE_WIDTH);
		for (DescriptorViewVO dvvo : vo.getDescriptorViews()) {
			DescriptorModelData d = new DescriptorModelData();
			d.setContentDescriptor(dvvo.getContentDescriptor());
			d.setHeader(dvvo.getHeader());
			d.setDatasetID(vo.getDsId());
			d.setDatasetTitle(vo.getDatasetName());
			if (dvvo.getContentDescriptor().equalsIgnoreCase("quantity")) {
				descriptorStore.add(d);
				descriptorList.setValue(d);
			}
		}
		descriptorComboBoxes.add(descriptorList);
		return descriptorList;
	}

	public FieldSet getFieldSet() {
		return fieldSet;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public List<ComboBox<DescriptorModelData>> getDescriptorComboBoxes() {
		return descriptorComboBoxes;
	}

	public List<DatasetVO> getDatasets() {
		return datasets;
	}

	public CheckBox getUseThisDataset() {
		return useThisDataset;
	}

	public ListStore<ChartTypeModelData> getChartTypeStore() {
		return chartTypeStore;
	}

	public ComboBox<ChartTypeModelData> getChartTypeList() {
		return chartTypeList;
	}

	public String getSelectedChartType() {
		return selectedChartType;
	}

	public void setSelectedChartType(String selectedChartType) {
		this.selectedChartType = selectedChartType;
	}
		
}