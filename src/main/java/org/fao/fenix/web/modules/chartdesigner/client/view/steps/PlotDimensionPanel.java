package org.fao.fenix.web.modules.chartdesigner.client.view.steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.chartdesigner.client.control.ChartDesignerController;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.UniqueValueVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.ListBox;

public class PlotDimensionPanel extends ChartDesignerStepPanel {

	private VerticalPanel wrapper;
	
	private final static int SPACING = 5;
	
	private final static int VISIBLE_ITEMS = 10;
	
	private final static String FIELD_WIDTH = "125px";
	
	private final static String WIDTH = "620px";
	
	private final static String INNER_WIDTH = "585px";
	
	private final static String WRAPPER_HEIGHT = "370px";
	
	private Map<Long, List<ListBox>> filtersMap;
	
	private Map<ListBox, String> contentDescriptorMap;
	
	private Map<Long, ListBox> measurementUnitMap;
	
	private Map<Long, FieldSet> fieldSetMap;
	
	/* To be used to disable dimensions already selected as X or Y axis */
	private Map<Long, List<VerticalPanel>> dimensionPanelMap;
	
	public PlotDimensionPanel(String suggestion, String width) {
		super(suggestion, width);
		wrapper = new VerticalPanel();
		wrapper.setLayout(new FillLayout());
		filtersMap = new HashMap<Long, List<ListBox>>();
		contentDescriptorMap = new HashMap<ListBox, String>();
		fieldSetMap = new HashMap<Long, FieldSet>();
		dimensionPanelMap = new HashMap<Long, List<VerticalPanel>>();
		measurementUnitMap = new HashMap<Long, ListBox>();
		this.getLayoutContainer().add(buildWrapperPanel());
	}
	
	private VerticalPanel buildWrapperPanel() {
		wrapper.setSpacing(SPACING);
		wrapper.setHorizontalAlign(HorizontalAlignment.LEFT);
		wrapper.setVerticalAlign(VerticalAlignment.MIDDLE);
		wrapper.setHeight(WRAPPER_HEIGHT);
		wrapper.setScrollMode(Scroll.AUTO);
		return wrapper;
	}
	
	public void addFilter(DatasetVO dataset) {
		FieldSet fs = buildFilter(dataset);
		wrapper.add(fs);
		fieldSetMap.put(Long.valueOf(dataset.getDsId()), fs);
		wrapper.getLayout().layout();
	}
	
	private FieldSet buildFilter(DatasetVO dvo) {
		filtersMap.put(Long.valueOf(dvo.getDsId()), new ArrayList<ListBox>());	
		FieldSet fs = new FieldSet();
		fs.setCheckboxToggle(false);
		fs.setHeading(dvo.getDatasetName());
		fs.setWidth(WIDTH);
		fs.setBorders(true);
		HorizontalPanel w = new HorizontalPanel();
		List<VerticalPanel> dimensionPanels = new ArrayList<VerticalPanel>();
		for (DescriptorViewVO dvvo : dvo.getDescriptorViews()) {
			if (!dvvo.getContentDescriptor().equalsIgnoreCase("quantity")) {
				VerticalPanel vp = buildDimension(dvvo, Long.valueOf(dvo.getDsId())); 
				w.add(vp);
				dimensionPanels.add(vp);
			}
		}
		dimensionPanelMap.put(Long.valueOf(dvo.getDsId()), dimensionPanels);
		w.setWidth(INNER_WIDTH);
		w.setScrollMode(Scroll.AUTO);
		fs.add(w);
		return fs;
	}
	
	private VerticalPanel buildDimension(DescriptorViewVO dvvo, Long datasetID) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(SPACING);
		Html l = new Html("<div style='font-family: sans-serif; color: #1D4589; font-size: 8pt; font-weight: bold; '>" + dvvo.getHeader() + "</div>");
		p.add(l);
		ListBox list = new ListBox(true);
		list.setVisibleItemCount(VISIBLE_ITEMS);
		list.setWidth(FIELD_WIDTH);
		if (dvvo.getValues() != null)
			for (UniqueValueVO uvvo : dvvo.getValues())
				list.addItem(uvvo.getLabel(), uvvo.getCode());
		p.add(list);
		filtersMap.get(datasetID).add(list);
		contentDescriptorMap.put(list, dvvo.getContentDescriptor());
		if (dvvo.getContentDescriptor().equalsIgnoreCase("measurementUnit"))
			measurementUnitMap.put(datasetID, list);
		Button selectAllButton = new Button("Select All");
		selectAllButton.setWidth(FIELD_WIDTH);
		selectAllButton.addSelectionListener(ChartDesignerController.selectAll(list, true));
		Button deSelectAllButton = new Button("Deselect All");
		deSelectAllButton.setWidth(FIELD_WIDTH);
		deSelectAllButton.addSelectionListener(ChartDesignerController.selectAll(list, false));
		p.add(selectAllButton);
		p.add(deSelectAllButton);
		return p;
	}

	public Map<Long, List<ListBox>> getFiltersMap() {
		return filtersMap;
	}

	public Map<ListBox, String> getContentDescriptorMap() {
		return contentDescriptorMap;
	}

	public Map<Long, FieldSet> getFieldSetMap() {
		return fieldSetMap;
	}

	public Map<Long, List<VerticalPanel>> getDimensionPanelMap() {
		return dimensionPanelMap;
	}

	public Map<Long, ListBox> getMeasurementUnitMap() {
		return measurementUnitMap;
	}
	
}