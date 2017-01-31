package org.fao.fenix.web.modules.olap.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.chartdesigner.client.view.DescriptorModelData;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.client.control.MTController;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;

public class MTDatasourceFieldSet {

	private FieldSet fieldSet;
	
	private VerticalPanel wrapper;
	
	private Button addDatasetButton;
	
	private final static String GRID_WIDTH = "800px";

	private final static String TITLE_WIDTH = "220px";
	
	private final static String CHECKBOX_WIDTH = "445px";
	
	private final static String LABEL_WIDTH = "125px";
	
	private final static int SPACING = 3;
	
	private List<ComboBox<DescriptorModelData>> descriptorComboBoxes;
	
	private List<DatasetVO> datasets;
	
	private CheckBox useThisDataset;
	
	private String TITLE = "<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>";
	
	public MTDatasourceFieldSet() {
		fieldSet = new FieldSet();
		wrapper = new VerticalPanel();
		wrapper.setLayout(new FillLayout());
		addDatasetButton = new Button("Select Dataset");
		addDatasetButton.setIconStyle("addDataset");
		descriptorComboBoxes = new ArrayList<ComboBox<DescriptorModelData>>();
		datasets = new ArrayList<DatasetVO>();
		useThisDataset = new CheckBox();
	}
	
	public FieldSet build(DatasetVO vo, MTWindow w) {
		fieldSet.setCheckboxToggle(true);
		fieldSet.setHeading(vo.getDatasetName());
		fieldSet.setWidth(GRID_WIDTH);
		fieldSet.setBorders(true);
		fieldSet.add(buildFieldSetWrapper(vo, w));
		datasets.add(vo);
		return fieldSet;
	}
	
	private VerticalPanel buildFieldSetWrapper(DatasetVO vo, MTWindow w) {
		VerticalPanel p = new VerticalPanel();
		p.add(buildFieldSetBody(vo, w));
		return p;
	}
	
	private HorizontalPanel buildFieldSetBody(DatasetVO vo, MTWindow w) {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(SPACING);
		p.setWidth(GRID_WIDTH);
		useThisDataset = new CheckBox();
		useThisDataset.setBoxLabel("Use This Dataset");
		useThisDataset.setValue(true);
		useThisDataset.setEnabled(false);
		useThisDataset.setWidth(CHECKBOX_WIDTH);
		useThisDataset.setData("DATASET_ID", vo.getDsId());
		useThisDataset.setData("DATASET_NAME", vo.getDatasetName());
		useThisDataset.setData("DATASET_SOURCE", vo.getSourceName());
		useThisDataset.addListener(Events.OnClick, MTController.useThisDatasetHandler(useThisDataset, w));
		Html lbl = new Html(TITLE + "Plot Dimension: </div>");
		lbl.setWidth(LABEL_WIDTH);
		p.add(useThisDataset);
		p.add(lbl);
		p.add(createDescriptorList(vo));
		return p;
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

	public Button getAddDatasetButton() {
		return addDatasetButton;
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
	
}