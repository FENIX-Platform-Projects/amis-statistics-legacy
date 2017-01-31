package org.fao.fenix.web.modules.birt.client.view.chart.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.control.chart.ChartWizardController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SelectData extends TabItem {
	
	private DateFromToPanel dateFromToPanel;
	private DateLastUpdatePanel dateLastUpdatePanel;
	private VerticalPanel datePanel;
	private VerticalPanel dateLUPanel;
//	private com.google.gwt.user.client.ui.CheckBox xValuesCheckBox;
	private CheckBox xValuesCheckBox;
	
	private ChartWizard chartWizard;
	private List<CheckBox> listCheckBoxDataset = new ArrayList<CheckBox>();
	private Map<CheckBox, List<String>> mapDatasetList;
	
	private ContentPanel mainCont;
	private ContentPanel contDS;
	private VerticalPanel vpContDS;
	private ContentPanel contXAxis;
	private ContentPanel contYAxis;
	private TabPanel panelYAxis;
	private ContentPanel dateXpanel;
		
	//private ListBox datasetList;
	private ListBox dimensionX;
	private ListBox dimensionXValues;
	private Map<String, String> MUList;
	private Map<String, ListBox>  dimensionY;
	private Map<String, ListBox>  dimensionYValues;
	private Map<String, ListBox>  dimensionYValuesBar;
	private Map<String, ListBox>  dimensionYValuesLine;
	private Map<String, VerticalPanel> otherDimensions;
	private Button  selectDataset;
	private Button loadDatasetsSelected;
	private Button next;
	private Button back;
	private Button ok;
	
	public Button getOk() {
		return ok;
	}

	public Map<String, String> getMUList() {
		return MUList;
	}

	public ContentPanel getContXAxis() {
		return contXAxis;
	}

	public ContentPanel getContYAxis() {
		return contYAxis;
	}
	
	public Map<String, ListBox> getDimensionY() {
		return dimensionY;
	}

	public Map<String, ListBox> getDimensionYValues() {
		return dimensionYValues;
	}
	
	public Map<String, ListBox> getDimensionYValuesBar() {
		return dimensionYValuesBar;
	}

	public Map<String, ListBox> getDimensionYValuesLine() {
		return dimensionYValuesLine;
	}

	public Map<String, VerticalPanel> getOtherDimensions() {
		return otherDimensions;
	}

	public TabPanel getPanelYAxis() {
		return panelYAxis;
	}

	public Map<CheckBox, List<String>> getMapDatasetList() {
		return mapDatasetList;
	}

	public List<CheckBox> getListCheckBoxDataset() {
		return listCheckBoxDataset;
	}

	public ListBox getDimensionX() {
		return dimensionX;
	}

	public ListBox getDimensionXValues() {
		return dimensionXValues;
	}


	private boolean alreadyIsThere(List<String> ds){
		boolean found = false;
		for (CheckBox checkBox : listCheckBoxDataset){
			if (ds.get(1).equals(mapDatasetList.get(checkBox).get(1))){
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	public void addDatasets(List<List<String>> datasets){
		List<String> listTemp;
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
			checkBox.setValue(true);
			listCheckBoxDataset.add(checkBox);
			hr.add(listCheckBoxDataset.get((listCheckBoxDataset.size()-1)));
			hr.add(new HTML(newDataset.get(0)));
			listTemp = new ArrayList<String>();
			listTemp.add(newDataset.get(0));
			listTemp.add(newDataset.get(1));
			mapDatasetList.put(listCheckBoxDataset.get((listCheckBoxDataset.size()-1)), listTemp);
			//contDS.insert(hr, (contDS.getItemCount()-2));
			vpContDS.insert(hr, (vpContDS.getWidgetCount()-2));
		}
		
	}
	
	public SelectData(ChartWizard chartWizard){
		
		this.chartWizard = chartWizard;
		setText(BabelFish.print().selectData());
		setScrollMode(Scroll.AUTO);
		dateLastUpdatePanel = new DateLastUpdatePanel();
		dateFromToPanel = new DateFromToPanel();
		
		
		mapDatasetList = new HashMap<CheckBox, List<String>>();
		
		next = new Button(BabelFish.print().nextnext());
		next.addSelectionListener(ChartWizardController.changeTab(2, chartWizard));
		back = new Button(BabelFish.print().back());
		back.addSelectionListener(ChartWizardController.changeTab(0, chartWizard));
		
		ok = new Button(BabelFish.print().ok());
		ok.setEnabled(false);
		ok.addSelectionListener(ChartWizardController.chartPreview(chartWizard));
		
		panelYAxis = new TabPanel();
		panelYAxis.setBodyBorder(false);
		panelYAxis.setAutoHeight(true);
		panelYAxis.setTabScroll(true);
		mainCont = new ContentPanel();
		mainCont.setBodyBorder(false);
		mainCont.setLayout(new AccordionLayout());
		mainCont.setHeaderVisible(false);
		mainCont.setSize(585, 445);
		
		
		contDS = new ContentPanel();
		contDS.setHeading(BabelFish.print().datasetList());
		contDS.setScrollMode(Scroll.AUTO);
		contDS.setBodyBorder(false);
		contDS.setAutoWidth(true);
		//contDS.setCollapsible(true);
		//contDS.setHeight(150);
		contDS.setExpanded(true);
		vpContDS = new VerticalPanel();
		vpContDS.setSpacing(10);
		contDS.add(vpContDS);
		mainCont.add(contDS);
		
		contXAxis = new ContentPanel();
		contXAxis.setHeading(BabelFish.print().axisX());
		contXAxis.setScrollMode(Scroll.AUTO);
		contXAxis.setAutoWidth(true);
		contXAxis.setExpanded(true);
		contXAxis.setBodyBorder(false);
		//contXAxis.setCollapsible(true);
		//contXAxis.setHeight(150);
		mainCont.add(contXAxis);
		
		contYAxis = new ContentPanel();
		contYAxis.setHeading(BabelFish.print().axisY());
		contYAxis.setScrollMode(Scroll.AUTO);
		contYAxis.setExpanded(true);
		contYAxis.setAutoWidth(true);
		contYAxis.setBodyBorder(false);
		//contYAxis.setCollapsible(true);
		//contYAxis.setHeight(200);
		mainCont.add(contYAxis);
		
				
		
		//datasetList=new ListBox();
		dimensionX = new ListBox();
		dimensionXValues = new ListBox(true);
		MUList = new HashMap<String, String>();
		dimensionY = new HashMap<String, ListBox>();
		dimensionYValues = new HashMap<String, ListBox>();
		dimensionYValuesBar = new HashMap<String, ListBox>();
		dimensionYValuesLine = new HashMap<String, ListBox>();
		otherDimensions = new HashMap<String, VerticalPanel>();
		
		
		//Datasets
		selectDataset=new Button(BabelFish.print().addDataset());
		selectDataset.addSelectionListener(ChartWizardController.addDataset(chartWizard));
		
		loadDatasetsSelected = new Button(BabelFish.print().ok());
		loadDatasetsSelected.addSelectionListener(ChartWizardController.loadDataset(chartWizard));
		vpContDS.add(selectDataset);
		vpContDS.add(new HTML("<br>"));
		vpContDS.add(loadDatasetsSelected);
		loadDatasetsSelected.setVisible(false);
				
						
		//Axis X
		HorizontalPanel hrAxisX = new HorizontalPanel();
		hrAxisX.setSpacing(5);
//		HTML axisX = new HTML(I18N.print().axisX());
//		axisX.setStyleName("simpleText");
//		hrAxisX.add(axisX);
//		dimensionX.setWidth("200px");
//		dimensionX.addChangeListener(ChartWizardController.fillColumnValuesX(chartWizard));
//		dimensionX.addItem(" "," ");
//		hrAxisX.add(dimensionX);
		hrAxisX.add(buildXPanel());
		
		hrAxisX.add(buildXValuesPanel());
		
		
		
		contXAxis.add(hrAxisX);
		
		
		//Axis Y
		contYAxis.add(panelYAxis);
		
		com.extjs.gxt.ui.client.widget.HorizontalPanel hr = new com.extjs.gxt.ui.client.widget.HorizontalPanel();
		hr.setSpacing(5);
		hr.add(back);
		hr.add(ok);
		hr.add(next);
		hr.setHorizontalAlign(HorizontalAlignment.RIGHT);
				
		mainCont.setBottomComponent(hr);
		
		add(mainCont);
	}
	
	private VerticalPanel buildXPanel() {
		VerticalPanel v = new VerticalPanel();
		v.setSpacing(5);
		HTML label = new HTML(BabelFish.print().axisX());
		label.setStyleName("simpleText");
		v.add(label);
		dimensionX.setWidth("250px");
		dimensionX.addChangeListener(ChartWizardController.fillColumnValuesX(chartWizard));
		dimensionX.addItem(" "," ");
		v.add(dimensionX);
		return v;
	}
	
	private VerticalPanel buildXValuesPanel() {
		VerticalPanel v = new VerticalPanel();
		v.setSpacing(5);
		v.add(buildXAxisValuesPanel());
		v.add(buildFromToDatePanel());
		v.add(buildLastUpdatePanel());
		return v;
	}
	
	private VerticalPanel buildFromToDatePanel(){
		datePanel = new VerticalPanel();
		datePanel.add(dateFromToPanel.build(dimensionXValues, xValuesCheckBox, dateLastUpdatePanel, "200px"));
		datePanel.setVisible(false);
		return datePanel;
	}
	
	private VerticalPanel buildLastUpdatePanel(){
		dateLUPanel = new VerticalPanel();
		dateLUPanel.add(dateLastUpdatePanel.build(dimensionXValues, xValuesCheckBox, dateFromToPanel, "200px"));
		dateLUPanel.setVisible(false);
		return dateLUPanel;
	}
	
	private VerticalPanel buildXAxisValuesPanel() {
		VerticalPanel xAxisValuesPanel = new VerticalPanel();
		xValuesCheckBox = new CheckBox();
		xValuesCheckBox.setBoxLabel(BabelFish.print().axisXValues());
		xValuesCheckBox.setValue(true);
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
	        public void handleEvent(ComponentEvent ce) {  
	        		setBox();
		        }
		    };  
		xValuesCheckBox.addListener(Events.OnClick, t);
		xAxisValuesPanel.add(xValuesCheckBox);	
		
		dimensionXValues.setSize("250px", "140px");
		xAxisValuesPanel.add(dimensionXValues);
		return xAxisValuesPanel;
	}
	
	public void resetDatePanel() {
		dateLastUpdatePanel.getCheckBox().setValue(false);
		dateFromToPanel.getCheckBox().setValue(false);
		dimensionXValues.setEnabled(true);
		dateLastUpdatePanel.getMonths().setEnabled(false);
		dateLastUpdatePanel.getYears().setEnabled(false);
		dateLastUpdatePanel.getDays().setEnabled(false);
		dateFromToPanel.getYearFrom().setEnabled(false);
		dateFromToPanel.getMonthFrom().setEnabled(false);
		dateFromToPanel.getDayFrom().setEnabled(false);
		dateFromToPanel.getYearTo().setEnabled(false);
		dateFromToPanel.getMonthTo().setEnabled(false);
		dateFromToPanel.getDayTo().setEnabled(false);
//		dateFromToPanel.getFromDateField().setEnabled(false);
//		dateFromToPanel.getToDateField().setEnabled(false);
		getDatePanel().setVisible(false);
		getDateLUPanel().setVisible(false);
		chartWizard.getChartWizardBean().setMostRecent(false);
	}
	
	public void setBox() {
		if (xValuesCheckBox.getValue()) {
			dimensionXValues.setEnabled(true);
			dateLastUpdatePanel.disableListBox();
			dateFromToPanel.disableListBox();
			xValuesCheckBox.setValue(true);
		}
		else {
			xValuesCheckBox.setValue(true);		

		}
	}
	
	public VerticalPanel getDatePanel() {
		return datePanel;
	}

	public DateFromToPanel getDateFromToPanel() {
		return dateFromToPanel;
	}

	public VerticalPanel getDateLUPanel() {
		return dateLUPanel;
	}

	public DateLastUpdatePanel getDateLastUpdatePanel() {
		return dateLastUpdatePanel;
	}

	public ContentPanel getMainCont() {
		return mainCont;
	}
	
	
}
