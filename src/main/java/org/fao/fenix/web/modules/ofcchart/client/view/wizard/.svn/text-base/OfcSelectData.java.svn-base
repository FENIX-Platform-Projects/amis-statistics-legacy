package org.fao.fenix.web.modules.ofcchart.client.view.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.control.OfcChartWizardController;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;


public class OfcSelectData extends TabItem {
	
	private OfcChartWizard chartWizard;
	private List<CheckBox> listCheckBoxDataset = new ArrayList<CheckBox>();
	private Map<CheckBox, DatasetVO> mapDatasetList;
	
	private ContentPanel mainCont;
	private ContentPanel contDS;
	private VerticalPanel vpContDS;
	private ContentPanel contXAxis;
	private ContentPanel contYAxis;
	private TabPanel panelYAxis;
		
	//private ListBox datasetList;
	private Button  selectDataset;
	private Button loadDatasetsSelected;
	private Button next;
	private Button back;
	private Button ok;
	
	


	private boolean alreadyIsThere(List<String> ds){
		boolean found = false;
		for (CheckBox checkBox : listCheckBoxDataset){
			if (ds.get(1).equals(mapDatasetList.get(checkBox).getDsId())){
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	public void addDatasets(List<List<String>> datasets){
		DatasetVO listTemp;
		HorizontalPanel hr;
		CheckBox checkBox;
		if (!loadDatasetsSelected.isVisible() && datasets.size()>0){
			loadDatasetsSelected.setVisible(true);
		}
		
		List<List<String>> listDatasetChecked;
		//to avoid loading the same datasets twice
		if (listCheckBoxDataset.size()>0){
			listDatasetChecked = new ArrayList<List<String>>();
			for(List<String> ds : datasets){
				if (!alreadyIsThere(ds)){
					listDatasetChecked.add(ds);
				}
			}
		} else {
			listDatasetChecked = datasets;
		}
		
		
		
		for (List<String> newDataset : listDatasetChecked){
			hr = new HorizontalPanel();
			hr.setSpacing(5);
			checkBox = new CheckBox();
	
			checkBox.setChecked(true);
			listCheckBoxDataset.add(checkBox);
			hr.add(listCheckBoxDataset.get((listCheckBoxDataset.size()-1)));
			hr.add(new HTML(newDataset.get(0)));

//			listTemp = new DatasetVO(newDataset.get(1), newDataset.get(0));
			listTemp = new DatasetVO();
			listTemp.setDsId(newDataset.get(1));
			listTemp.setDatasetName(newDataset.get(0));
			mapDatasetList.put(listCheckBoxDataset.get((listCheckBoxDataset.size()-1)), listTemp);
			vpContDS.insert(hr, (vpContDS.getWidgetCount()-2));
		}
		
	}

	public OfcSelectData(OfcChartWizard chartWizard){	
		this.chartWizard = chartWizard;
		this.setEnabled(false);
		
		setText(BabelFish.print().selectData());
		setScrollMode(Scroll.AUTOY);
	
		
		
		mapDatasetList = new HashMap<CheckBox, DatasetVO>();
		
		next = new Button(BabelFish.print().next());
		next.addSelectionListener(OfcChartWizardController.changeTab(2, chartWizard));
		back = new Button(BabelFish.print().back());
		back.addSelectionListener(OfcChartWizardController.changeTab(0, chartWizard));
		
		ok = new Button(BabelFish.print().ok());
		ok.setEnabled(false);
//		ok.addSelectionListener(OfcChartWizardController.createChart(chartWizard));
		
		panelYAxis = new TabPanel();
		panelYAxis.setBodyBorder(false);
		panelYAxis.setAutoHeight(true);
		mainCont = new ContentPanel();
		mainCont.setLayout(new AccordionLayout());
		mainCont.setHeaderVisible(false);
		mainCont.setSize(740, 485);
		
		
		contDS = new ContentPanel();
		contDS.setHeading(BabelFish.print().datasetList());
		contDS.setScrollMode(Scroll.AUTOY);
		contDS.setBodyBorder(false);
		contDS.setAutoWidth(true);
		//contDS.setCollapsible(true);
		//contDS.setHeight(150);
		contDS.setExpanded(true);
		vpContDS = new VerticalPanel();
		vpContDS.setSpacing(10);
		contDS.add(vpContDS);
		mainCont.add(contDS);
		
		
		
		selectDataset=new Button(BabelFish.print().addDataset());
		selectDataset.addSelectionListener(OfcChartWizardController.addDataset(chartWizard));
		
		loadDatasetsSelected = new Button(BabelFish.print().ok());
		loadDatasetsSelected.addSelectionListener(OfcChartWizardController.loadDatasets(chartWizard));
//		datasetCont.add(labelDataset);
//		datasetCont.add(datasetList);
//		datasetCont.add(selectDataset);
		vpContDS.add(selectDataset);
		vpContDS.add(new HTML("<br>"));
		vpContDS.add(loadDatasetsSelected);
		loadDatasetsSelected.setVisible(false);
				
						
		//Axis X
		HorizontalPanel hrAxisX = new HorizontalPanel();
		hrAxisX.setSpacing(5);
		HTML axisX = new HTML(BabelFish.print().axisX());
		axisX.setStyleName("simpleText");
		hrAxisX.add(axisX);

		
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

	

	public ContentPanel getContXAxis() {
		return contXAxis;
	}

	public ContentPanel getContYAxis() {
		return contYAxis;
	}
	
	

	public TabPanel getPanelYAxis() {
		return panelYAxis;
	}


	public List<CheckBox> getListCheckBoxDataset() {
		return listCheckBoxDataset;
	}

	public Map<CheckBox, DatasetVO> getMapDatasetList() {
		return mapDatasetList;
	}

	public void setMapDatasetList(Map<CheckBox, DatasetVO> mapDatasetList) {
		this.mapDatasetList = mapDatasetList;
	}


	
}