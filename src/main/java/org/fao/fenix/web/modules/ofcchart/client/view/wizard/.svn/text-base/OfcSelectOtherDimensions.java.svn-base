package org.fao.fenix.web.modules.ofcchart.client.view.wizard;

import java.util.HashMap;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartWizardController;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class OfcSelectOtherDimensions extends TabItem {
	
	private ContentPanel mainCont;
	/** contains the id of the dataset and a list of panels containing the other dimensions of the dataset 
	 *  excluded the quantity, the measurement units and the selected dimensions
	 */
	private HashMap<String, OfcOtherDimensionsPanel> datasetDimensions;
	private HashMap<String, VerticalPanel> datasetPanel;


	
	private Button next;
	private Button back;
	private Button ok;
	
	
	public OfcSelectOtherDimensions(OfcChartWizard chartWizard){
		this.setEnabled(false);
		datasetDimensions = new HashMap<String, OfcOtherDimensionsPanel>();
	
		datasetPanel = new HashMap<String, VerticalPanel>();
		
		setText("Other Dimensions");
	
		next = new Button(BabelFish.print().next());
		next.addSelectionListener(OfcChartWizardController.changeTab(5, chartWizard));
		back = new Button(BabelFish.print().back());
		back.addSelectionListener(OfcChartWizardController.changeTab(3, chartWizard));
		ok = new Button(BabelFish.print().ok());
		ok.setEnabled(false);
//		ok.addSelectionListener(ChartWizardController.chartPreview(chartWizard));
		
		mainCont = new ContentPanel();
		mainCont.setBorders(true);
//		mainCont.setLayout(new AccordionLayout());
		mainCont.setHeaderVisible(false);
		mainCont.setSize(730, 485);
		mainCont.setScrollMode(Scroll.AUTO);

		HorizontalPanel hr = new HorizontalPanel();
		hr.setSpacing(5);
		hr.add(back);
		hr.add(ok);
		hr.add(next);
		hr.setHorizontalAlign(HorizontalAlignment.RIGHT);	
//		mainCont.add(datasetPanel);
		mainCont.setBottomComponent(hr);
		add(mainCont);
	}
	

	public void createOtherDimension(OfcChartWizard window, DatasetVO d){
		VerticalPanel panel = new VerticalPanel();
		panel.add(new Html("<b>" + d.getDatasetName() + "</b>"));
		panel.setSize(705, 220);
		panel.setBorders(true);
		panel.setScrollMode(Scroll.AUTO);
		System.out.println("Creating other dimensions: " + d.getDatasetName() + " | " + d.getDsId());
		OfcOtherDimensionsPanel dimension = new OfcOtherDimensionsPanel();	
		panel.add(dimension.build(d));	
		datasetDimensions.put(d.getDsId(), dimension);
		datasetPanel.put(d.getDsId(), panel);
		panel.setId(d.getDsId());
		dimension.getDimensions().addChangeListener(OfcChartWizardController.dimensionsOthersChangeListenerWithLabels(window, dimension.getDataset(), dimension.getValues()));
		dimension.getValues().addChangeListener(OfcChartWizardController.dimensionsOthersValuesChangeListenerWithLabels(window, dimension.getDataset(), dimension.getDimensions()));
		mainCont.add(datasetPanel.get(d.getDsId()));
	}
	

	

	public void clean() {
		datasetPanel = new HashMap<String, VerticalPanel>();
		datasetDimensions = new HashMap<String, OfcOtherDimensionsPanel>();
	
		mainCont.removeAll();
	}
	
	public Button getOk() {
		return ok;
	}


	public HashMap<String, OfcOtherDimensionsPanel> getDatasetDimensions() {
		return datasetDimensions;
	}


	public void setDatasetDimensions(
			HashMap<String, OfcOtherDimensionsPanel> datasetDimensions) {
		this.datasetDimensions = datasetDimensions;
	}


	public HashMap<String, VerticalPanel> getDatasetPanel() {
		return datasetPanel;
	}


	public void setDatasetPanel(HashMap<String, VerticalPanel> datasetPanel) {
		this.datasetPanel = datasetPanel;
	}


	public ContentPanel getMainCont() {
		return mainCont;
	}


	public void setMainCont(ContentPanel mainCont) {
		this.mainCont = mainCont;
	}


	



	
	

}