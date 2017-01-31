package org.fao.fenix.web.modules.ofcchart.client.view.wizard;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartWizardController;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.Button;


public class OfcSelectYAxis extends TabItem {
	
	private ContentPanel mainCont;
	private List<DatasetVO> datasets;
	private List<OfcAxisPanel> yPanels;
		
	
	private Button next;
	private Button back;
	private Button ok;
	
	
	public OfcSelectYAxis(OfcChartWizard chartWizard){
		
		datasets = new ArrayList<DatasetVO>();
		yPanels = new ArrayList<OfcAxisPanel>();
		this.setEnabled(false);
		setText("Y-Axis");

		next = new Button(BabelFish.print().next());
		next.addSelectionListener(OfcChartWizardController.changeTab(4, chartWizard));
		back = new Button(BabelFish.print().back());
		back.addSelectionListener(OfcChartWizardController.changeTab(2, chartWizard));
		
		ok = new Button(BabelFish.print().ok());
//		ok.setEnabled(false);
		ok.setEnabled(true);
		ok.addSelectionListener(OfcChartWizardController.createChart(chartWizard));
		
		mainCont = new ContentPanel();
		mainCont.setBodyBorder(true);
//		mainCont.setLayout(new AccordionLayout());
		mainCont.setHeaderVisible(false);
		mainCont.setSize(730, 490);
		mainCont.setScrollMode(Scroll.AUTO);

		HorizontalPanel hr = new HorizontalPanel();
		hr.setSpacing(5);
		hr.add(back);
		hr.add(ok);
		hr.add(next);
		hr.setHorizontalAlign(HorizontalAlignment.RIGHT);	
		mainCont.setBottomComponent(hr);
		add(mainCont);
	}
	
	public void createYDimensions(OfcChartWizard window, List<DatasetVO> d){
		/** TODO: check if there is not already the dataset wihtout everytime recharge it **/
		yPanels.clear();
		mainCont.removeAll();
		datasets.clear();
		datasets.addAll(d);
		System.out.println("Datasets: " + d.size());
		for(DatasetVO dataset : d) {	
			System.out.println("Creating Y: " + dataset.getDatasetName());
			OfcAxisPanel yAxis = new OfcAxisPanel();
			DatasetVO vo = new DatasetVO();
			vo.setDsId(dataset.getDsId());
			vo.setDatasetName(dataset.getDatasetName());
			yAxis.setDataset(vo);
			yAxis.getDimensions().addChangeListener(OfcChartWizardController.dimensionsYChangeListenerWithLabels(window, yAxis.getDataset(), yAxis.getValues()));
			mainCont.add(yAxis.build(dataset, false, true));
			yPanels.add(yAxis);	
		}
	
	}

	public Button getOk() {
		return ok;
	}
	
	

	
	
	

	

	public List<OfcAxisPanel> getyPanels() {
		return yPanels;
	}

	public void setyPanels(List<OfcAxisPanel> yPanels) {
		this.yPanels = yPanels;
	}

	public List<DatasetVO> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DatasetVO> datasets) {
		this.datasets = datasets;
	}
}