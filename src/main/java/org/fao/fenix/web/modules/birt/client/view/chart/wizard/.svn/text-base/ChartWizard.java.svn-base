package org.fao.fenix.web.modules.birt.client.view.chart.wizard;

import java.util.List;

import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.widget.Html;
import com.google.gwt.user.client.ui.TabPanel;


public class ChartWizard extends FenixWindow{
	
	private TabPanel panel;
	
	private ChartWizardBean chartWizardBean;
	
	private ChartType chartType;
	
	private SelectData selectData;
	
	private FormatChart formatChart;
	
	private int selectedTabIndex = 0;
	
	public TabPanel getPanel() {
		return panel;
	}

	public ChartWizardBean getChartWizardBean() {
		return chartWizardBean;
	}

	public FormatChart getFormatChart() {
		return formatChart;
	}

	public ChartType getChartType() {
		return chartType;
	}

	public SelectData getSelectData() {
		return selectData;
	}

	private void buildInterface(){
		chartWizardBean=new ChartWizardBean();
		chartType = new ChartType(chartWizardBean, this);
		selectData = new SelectData(this);
		formatChart = new FormatChart(this);
		
		
		setTitle(BabelFish.print().chartWizard());
		setCollapsible(true);
		setSize(600, 550);
		
		panel=new TabPanel();
//		panel.setAutoHeight(true);
//		panel.setAutoWidth(true);
//		panel.setPlain(true);
//		panel.setBodyBorder(false);
		
		panel.add(getChartType(), BabelFish.print().chartType());
		panel.add(getSelectData(), BabelFish.print().selectData());
		panel.add(getFormatChart(), BabelFish.print().formatChart());
		
		setCenterProperties();
		getCenter().add(panel);
		addCenterPartToWindow();
		getCenter().setHeaderVisible(false);
		getCenter().setBodyBorder(false);
		//getCenter().setLayout(new CenterLayout());
		
		show();
		panel.selectTab(selectedTabIndex);
	}
	
	public ChartWizard(){
		buildInterface();
	}
	
	public ChartWizard(List<List<String>> datasets){
		buildInterface();
		getSelectData().addDatasets(datasets);
	}

	public int getSelectedTabIndex() {
		return selectedTabIndex;
	}

	public void setSelectedTabIndex(int selectedTabIndex) {
		this.selectedTabIndex = selectedTabIndex;
	}
	
}