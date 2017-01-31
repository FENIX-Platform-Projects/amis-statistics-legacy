package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.ui.ListBox;

public class DatasourcePanel extends ChartDesignerStepPanel {

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
	
	private List<ChartDesignerDatasourceFieldSet> fieldSets;
	
	private List<Long> addedDatasets;
	
	private int SERIES_NUMBER = 1;
	
	private MenuItem barMI;
	
	private MenuItem lineMI;
	
	private MenuItem pointsMI;
	
	private MenuItem targetMI;
	
	private MenuItem pieMI;
	
	private MenuItem boxplotMI;

	public DatasourcePanel(String suggestion, String width) {
		super(suggestion, width);
		wrapper = new VerticalPanel();
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setLayout(new FillLayout());
		addDatasetButton = new Button("Add Dataset As...");
		addDatasetButton.setIconStyle("addDataset");
		addDatasetButton.setWidth(BUTTON_WIDTH);
		models = new ArrayList<ResourceChildModel>();
		datasourceIDs = new ArrayList<Long>();
		plotDimensionMap = new HashMap<Long, ListBox>();
		fieldSets = new ArrayList<ChartDesignerDatasourceFieldSet>();
		addedDatasets = new ArrayList<Long>();
		this.getLayoutContainer().add(buildWrapperPanel());
	}

	private VerticalPanel buildWrapperPanel() {
		wrapper.setSpacing(SPACING);
		wrapper.setHeight(WRAPPER_HEIGHT);
		wrapper.add(buildHeadersPanel());
		return wrapper;
	}
	
	public void addSeries(String title, List<DatasetVO> datasetVOs, String selectedChartType, ChartDesignerWindow w, boolean calculateYAxisMaxValue) {
		for (DatasetVO dvo : datasetVOs) {
			ChartDesignerDatasourceFieldSet fs = new ChartDesignerDatasourceFieldSet("BAR");
			wrapper.add(fs.build(dvo, w, calculateYAxisMaxValue));
			wrapper.getLayout().layout();
			fieldSets.add(fs);
			SERIES_NUMBER++;
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
		Html plotDimensionLabel = new Html("<div style='color: #FFFFFF; font-family: sans-serif; font-weight: bold;'>Plot Dimension</div>");
		plotDimensionLabel.setWidth(TITLE_WIDTH);
		p.add(plotDimensionLabel);
		p.add(buildAddDatasetAsMenu());
		p.setStyleAttribute("backgroundColor", "#1D4589");
		return p;
	}
	
	public Button buildAddDatasetAsMenu() {
		addDatasetButton.setIconStyle("addDataset");
		Menu m = new Menu();
		barMI = new MenuItem("Bar Chart");
		barMI.setIconStyle("workspaceChart");
		m.add(barMI);
		lineMI = new MenuItem("Line Chart");
		lineMI.setIconStyle("workspaceChart");
		m.add(lineMI);
		pointsMI = new MenuItem("Points Chart");
		pointsMI.setIconStyle("workspaceChart");
		m.add(pointsMI);
		targetMI = new MenuItem("Targets Chart");
		targetMI.setIconStyle("workspaceChart");
		m.add(targetMI);
		addDatasetButton.setMenu(m);
		addDatasetButton.setWidth(BUTTON_WIDTH);
		return addDatasetButton;
	}

	public HorizontalPanel buildDatasetPanel(DatasetVO dvo) {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(SPACING);
		p.setBorders(true);
		p.setWidth(GRID_WIDTH);
		CheckBox cb = new CheckBox();
		cb.setValue(true);
		cb.setWidth(CHECKBOX_WIDTH);
		Html datasetTitleLabel = new Html("<div class='hyperlink'>" + dvo.getDatasetName() + "</div>");
		datasetTitleLabel.setTitle("Click to show the metadata.");
		datasetTitleLabel.setWidth(TITLE_WIDTH);
		ListBox plotDimensionList = new ListBox();
		plotDimensionList.setWidth(TITLE_WIDTH);
		for (DescriptorViewVO dvvo : dvo.getDescriptorViews())
			if (dvvo.getContentDescriptor().equalsIgnoreCase("quantity"))
				plotDimensionList.addItem(dvvo.getHeader(), dvvo.getContentDescriptor());
		Html sourceLabel = new Html("<div style='color: #1D4589; font-family: sans-serif;'><i>&nbsp;&nbsp;source: </i><b>" + dvo.getSourceName() + "</b></div>");
		sourceLabel.setWidth(BUTTON_WIDTH);
		p.add(cb);
		p.add(datasetTitleLabel);
		p.add(plotDimensionList);
		p.add(sourceLabel);
		this.getDatasourceIDs().add(Long.valueOf(dvo.getDsId()));
		this.getPlotDimensionMap().put(Long.valueOf(dvo.getDsId()), plotDimensionList);
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

	public List<ChartDesignerDatasourceFieldSet> getFieldSets() {
		return fieldSets;
	}

	public int getSERIES_NUMBER() {
		return SERIES_NUMBER;
	}

	public List<Long> getAddedDatasets() {
		return addedDatasets;
	}

	public MenuItem getBarMI() {
		return barMI;
	}

	public MenuItem getLineMI() {
		return lineMI;
	}

	public MenuItem getPointsMI() {
		return pointsMI;
	}

	public MenuItem getTargetMI() {
		return targetMI;
	}

	public MenuItem getPieMI() {
		return pieMI;
	}

	public MenuItem getBoxplotMI() {
		return boxplotMI;
	}

}