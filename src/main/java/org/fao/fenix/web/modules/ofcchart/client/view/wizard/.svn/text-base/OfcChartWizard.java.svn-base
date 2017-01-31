package org.fao.fenix.web.modules.ofcchart.client.view.wizard;

import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcChartFormatBeanVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.TabPanel;

public class OfcChartWizard extends FenixWindow{
	
	private TabPanel panel;
	private OfcChartType chartType;
	private OfcSelectData selectData;
	private OfcFormatChart formatChart;
	private OfcSelectXAxis selectXAxis;
	private OfcSelectYAxis selectYAxis;
	private OfcSelectOtherDimensions selectOtherDimensions;
	private OfcChartFormatBeanVO chartFormatBean;
	private OfcSelectOptions selectOptions;
//	private OfcChartViewer chartViewer;
	

	private void buildInterface(){
		chartFormatBean = new OfcChartFormatBeanVO();
		chartType = new OfcChartType(chartFormatBean, this);
		selectData = new OfcSelectData(this);
		selectXAxis = new OfcSelectXAxis(this);
		selectYAxis = new OfcSelectYAxis(this);
		selectOtherDimensions = new OfcSelectOtherDimensions(this);
		selectOptions = new OfcSelectOptions(this);
		formatChart = new OfcFormatChart(this);
//		chartViewer = new OfcChartViewer(this);
		
		
		setTitle(BabelFish.print().chartWizard());
		setCollapsible(true);
		setSize(750, 550);
		
		panel=new TabPanel();
		panel.setAutoHeight(true);
		panel.setAutoWidth(true);
		panel.setPlain(true);
		panel.setBodyBorder(false);
		
		panel.add(getChartType());
		panel.add(getSelectData());
		panel.add(getSelectXAxis());
		panel.add(getSelectYAxis());
		panel.add(getSelectOtherDimensions());
		panel.add(getSelectOptions());
		panel.add(getFormatChart());
		
		setCenterProperties();
		getCenter().add(panel);
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getCenter().setBodyBorder(false);
		//getCenter().setLayout(new CenterLayout());
		
		show();
//		buildChartViewer(chartViewer);
	}
	
	public OfcChartWizard(){
		buildInterface();
	}
	
	public OfcChartWizard(List<List<String>> datasets){
		buildInterface();
		getSelectData().addDatasets(datasets);
	}
	
//	private void buildChartViewer(OfcChartViewer chartViewer) {
//		chartViewer.build(this);
//	}

	
	
	public OfcSelectOptions getSelectOptions() {
		return selectOptions;
	}

	public OfcSelectXAxis getSelectXAxis() {
		return selectXAxis;
	}

	public OfcSelectYAxis getSelectYAxis() {
		return selectYAxis;
	}

	public OfcSelectOtherDimensions getSelectOtherDimensions() {
		return selectOtherDimensions;
	}

	public TabPanel getPanel() {
		return panel;
	}

		public OfcFormatChart getFormatChart() {
		return formatChart;
	}

	public OfcChartType getChartType() {
		return chartType;
	}

	public OfcSelectData getSelectData() {
		return selectData;
	}

	public OfcChartFormatBeanVO getChartFormatBean() {
		return chartFormatBean;
	}

	public void setChartFormatBean(OfcChartFormatBeanVO chartFormatBean) {
		this.chartFormatBean = chartFormatBean;
	}

//	public void setChartViewer(OfcChartViewer chartViewer) {
//		this.chartViewer = chartViewer;
//	}
	
}