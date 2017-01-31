package org.fao.fenix.web.modules.olap.client.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.ListBox;

public class MTDatasourcePanel extends MTStepPanel {
	
	private VerticalPanel wrapper;

	private Button addDatasetButton;

	private final static int SPACING = 3;

	private final static String GRID_WIDTH = "825px";

	private final static String TITLE_WIDTH = "315px";

	private final static String BUTTON_WIDTH = "125px";

	private final static String CHECKBOX_WIDTH = "30px";

	private final static String WRAPPER_HEIGHT = "370px";
	
	private List<ResourceChildModel> models;
	
	private List<Long> datasourceIDs;
	
	private Map<Long, ListBox> plotDimensionMap;
	
	private List<MTDatasourceFieldSet> fieldSets;
	
	private List<Long> addedDatasets;

	public MTDatasourcePanel(String suggestion, String width) {
		super(suggestion, width);wrapper = new VerticalPanel();
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setLayout(new FillLayout());
		addDatasetButton = new Button("Select Dataset");
		addDatasetButton.setIconStyle("addDataset");
		addDatasetButton.setWidth(BUTTON_WIDTH);
		models = new ArrayList<ResourceChildModel>();
		datasourceIDs = new ArrayList<Long>();
		plotDimensionMap = new HashMap<Long, ListBox>();
		fieldSets = new ArrayList<MTDatasourceFieldSet>();
		addedDatasets = new ArrayList<Long>();
		this.getLayoutContainer().add(buildWrapperPanel());
	}
	
	private VerticalPanel buildWrapperPanel() {
		wrapper.setSpacing(SPACING);
		wrapper.setHeight(WRAPPER_HEIGHT);
		wrapper.add(new Html(BabelFish.print().olapMainPage()));
		wrapper.add(buildHeadersPanel());
		return wrapper;
	}
	
	public void addSeries(String title, List<DatasetVO> datasetVOs, String selectedChartType, MTWindow w) {
		for (DatasetVO dvo : datasetVOs) {
			MTDatasourceFieldSet fs = new MTDatasourceFieldSet();
			wrapper.add(fs.build(dvo, w));
			wrapper.getLayout().layout();
			fieldSets.add(fs);
			addedDatasets.add(Long.valueOf(dvo.getDsId()));
		}
	}
	
	private HorizontalPanel buildHeadersPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(SPACING * 2);
		p.setBorders(true);
		p.setWidth(GRID_WIDTH);
		Html selectLabel = new Html("<div style='color: #FFFFFF; font-family: sans-serif; font-weight: bold;'>&nbsp;</div>");
		selectLabel.setWidth(CHECKBOX_WIDTH);
		p.add(selectLabel);
		Html datasetLabel = new Html("<div style='color: #FFFFFF; font-family: sans-serif; font-weight: bold;'>Dataset</div>");
		datasetLabel.setWidth(TITLE_WIDTH);
		p.add(datasetLabel);
		Html plotDimensionLabel = new Html("<div style='color: #FFFFFF; font-family: sans-serif; font-weight: bold;'>&nbsp;</div>");
		plotDimensionLabel.setWidth(TITLE_WIDTH);
		p.add(plotDimensionLabel);
		p.add(addDatasetButton);
		p.setStyleAttribute("backgroundColor", "#1D4589");
		return p;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public Button getAddDatasetButton() {
		return addDatasetButton;
	}

	public List<ResourceChildModel> getModels() {
		return models;
	}

	public List<Long> getDatasourceIDs() {
		return datasourceIDs;
	}

	public Map<Long, ListBox> getPlotDimensionMap() {
		return plotDimensionMap;
	}

	public List<MTDatasourceFieldSet> getFieldSets() {
		return fieldSets;
	}

	public List<Long> getAddedDatasets() {
		return addedDatasets;
	}
	
}