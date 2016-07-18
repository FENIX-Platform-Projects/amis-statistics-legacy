package org.fao.fenix.web.modules.ofcchart.client.view.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartWizardController;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.DimensionsBeanVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class OfcSelectXAxis extends TabItem {
	
	private ContentPanel mainCont;
	private OfcAxisPanel xAxis;
	private VerticalPanel xPanel;
	private List<DatasetVO> datasets;
		
	private Button next;
	private Button back;
	private Button ok;
	
	private HashMap<String, List<DimensionsBeanVO>> commonXDimensions;
	private HashMap<String, DimensionsBeanVO> selectedXDimensions;

	
	
	public OfcSelectXAxis(OfcChartWizard chartWizard){
		
		datasets = new ArrayList<DatasetVO>();
		commonXDimensions  = new HashMap<String, List<DimensionsBeanVO>>();
		selectedXDimensions = new HashMap<String, DimensionsBeanVO>();
		xPanel = new VerticalPanel();
		this.setEnabled(false);
		setText("X-Axis");
//		setScrollMode(Scroll.AUTO);
		xAxis = new OfcAxisPanel();
		
		
	
		
		next = new Button(BabelFish.print().next());
		next.addSelectionListener(OfcChartWizardController.changeTab(3, chartWizard));
		back = new Button(BabelFish.print().back());
		back.addSelectionListener(OfcChartWizardController.changeTab(1, chartWizard));
		
		ok = new Button(BabelFish.print().ok());
		ok.setEnabled(false);
//		ok.addSelectionListener(ChartWizardController.chartPreview(chartWizard));
		
		mainCont = new ContentPanel();
		mainCont.setBodyBorder(true);
//		mainCont.setLayout(new AccordionLayout());
		mainCont.setHeaderVisible(false);
		mainCont.setSize(740, 485);
		mainCont.setScrollMode(Scroll.AUTOY);
		xPanel.add(xAxis.build(new DatasetVO(), false, false));
//		mainCont.add(xAxis.build(new DatasetVO(), false, false));
		mainCont.add(xPanel);
		xAxis.getDimensions().addChangeListener(OfcChartWizardController.dimensionsXChangeListenerWithLabels(chartWizard, datasets, xAxis.getValues()));

		HorizontalPanel hr = new HorizontalPanel();
		hr.setSpacing(5);
		hr.add(back);
		hr.add(ok);
		hr.add(next);
		hr.setHorizontalAlign(HorizontalAlignment.RIGHT);	
		mainCont.setBottomComponent(hr);
		add(mainCont);
	}

	public Button getOk() {
		return ok;
	}

	
	
	

	

	public OfcAxisPanel getxAxis() {
		return xAxis;
	}

	public void setxAxis(OfcAxisPanel xAxis) {
		this.xAxis = xAxis;
	}

	public List<DatasetVO> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DatasetVO> datasets) {
		this.datasets = datasets;
	}

	public HashMap<String, List<DimensionsBeanVO>> getCommonXDimensions() {
		return commonXDimensions;
	}

	public void setCommonXDimensions(HashMap<String, List<DimensionsBeanVO>> commonXDimensions) {
		this.commonXDimensions = commonXDimensions;
	}

	public HashMap<String, DimensionsBeanVO> getSelectedXDimensions() {
		return selectedXDimensions;
	}

	public void setSelectedXDimensions(HashMap<String, DimensionsBeanVO> selectedXDimensions) {
		this.selectedXDimensions = selectedXDimensions;
	}

	public ContentPanel getMainCont() {
		return mainCont;
	}

	public VerticalPanel getxPanel() {
		return xPanel;
	}

	
}