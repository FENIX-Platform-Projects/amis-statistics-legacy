package org.fao.fenix.web.modules.birt.client.view.chart.viewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.control.chart.ChartViewerController;
import org.fao.fenix.web.modules.birt.client.control.chart.ChartWizardController;
import org.fao.fenix.web.modules.birt.client.utils.Counter;
import org.fao.fenix.web.modules.birt.client.utils.DateUtils;
import org.fao.fenix.web.modules.birt.client.utils.DimensionBlackList;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateFromToPanel;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateLastUpdatePanel;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.core.client.utils.SocialBar;
import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChartViewer extends FenixWindow {
	
	private DateFromToPanel dateFromToPanel;
	private DateLastUpdatePanel dateLastUpdatePanel;
	private VerticalPanel datePanel;
	private VerticalPanel dateLUPanel;
	private CheckBox xValuesCheckBox;
	
	private SideBar sideBar;
	private ChartToolBar chartToolBar;
	private HTML chart;
	private String rptdesign;
	private Long chartViewID=0L;
	private Button preview;
	
	private ListBox dimensionX;
	private ListBox dimensionXValues;
	private TabPanel panelYAxis;
	
	private ListBox aggregation = new ListBox();
	
	private TabPanel panelOtherDim;
	private ChartWizardBean chartWizardBean;
	
	private List<CheckBox> listCheckBoxDataset = new ArrayList<CheckBox>();
	private Map<CheckBox, List<String>> mapDatasetList = new HashMap<CheckBox, List<String>>();	
	
	private boolean isShow = true;
	
	public ListBox getAggregation() {
		return aggregation;
	}

	public ListBox getDimensionX() {
		return dimensionX;
	}

	public List<CheckBox> getListCheckBoxDataset() {
		return listCheckBoxDataset;
	}

	public Map<CheckBox, List<String>> getMapDatasetList() {
		return mapDatasetList;
	}

	public ListBox getDimensionXValues() {
		return dimensionXValues;
	}

	public Long getChartViewID() {
		return chartViewID;
	}

	public void setChartViewID(Long chartViewID) {
		this.chartViewID = chartViewID;
	}

	public ChartWizardBean getChartWizardBean() {
		return chartWizardBean;
	}

	public void setChartWizardBean(ChartWizardBean chartWizardBean) {
		this.chartWizardBean = chartWizardBean;
	}

	public HTML getChart() {
		return chart;
	}

	public void setChart(HTML chart) {
		this.chart = chart;
	}

	public String getRptdesign() {
		return rptdesign;
	}

	public void setRptdesign(String rptdesign) {
		this.rptdesign = rptdesign;
	}

	public TabPanel getPanelYAxis() {
		return panelYAxis;
	}

	public SideBar getSideBar() {
		return sideBar;
	}

	public ChartToolBar getChartToolBar() {
		return chartToolBar;
	}

	private void build(){
		setTitle(BabelFish.print().chartViewer());
		setCollapsible(true);
		setMaximizable(true);
		setSize(750, 500);
		chart = new HTML();
		sideBar = new SideBar();
		
		preview = new Button(BabelFish.print().apply());
		preview.addSelectionListener(ChartViewerController.chartPreview(ChartViewer.this));
		
		setWestProperties();
		getWest().add(sideBar.getMainCont());
		getWest().setBottomComponent(preview);
		addWestPartToWindow();
		
		chartToolBar = new ChartToolBar(this);
		
		setCenterProperties();
		getCenter().setTopComponent(chartToolBar.getToolBar());
		getCenter().add(chart);
		addCenterPartToWindow();
		
		getCenter().setHeaderVisible(false);
		getWestData().setFloatable(true);
//		getWest().setWidth(5);
		
		
		//show();
		
		SocialBar sb = new SocialBar();
		getCenter().setBottomComponent(sb.getSocialBar(ResourceType.CHART, String.valueOf(chartViewID)));
	}
	
	private void selectValue(ListBox listBox, List<List<String>> dimensionValuesX){
		List<String> codes = new ArrayList<String>(); 
		for (List<String> justCode : dimensionValuesX){
			codes.add(justCode.get(0));
		}
		for (int i=0; i < listBox.getItemCount(); i++){
			if (codes.contains(listBox.getValue(i))){
				listBox.setItemSelected(i, true);
			}
		}
	}
	
	private int getIndexListBox(ListBox list, String value){
		for (int i=0; i<list.getItemCount(); i++){
			if (list.getItemText(i).equals(value)){
				return i;
			}
		}
		
		return -1;
	}
	
	private void populateDimension(){
		for (int i=0; i<((TabPanel) sideBar.getVpChartAxis().getWidget(4)).getItemCount(); i++){
			//Window.alert( ((TabItem)((TabPanel) sideBar.getVpChartAxis().getWidget(4)).getWidget(i)).getItemId() + " " + ((TabItem)((TabPanel) sideBar.getVpChartAxis().getWidget(4)).getWidget(i)).getText());
			final TabItem dimY = (TabItem)((TabPanel) sideBar.getVpChartAxis().getWidget(4)).getWidget(i);
			final TabItem otherDim = (TabItem)((TabPanel) sideBar.getVpOtherDim().getWidget(0)).getWidget(i);
			
			//Window.alert(((HTML)((VerticalPanel)otherDim.getWidget(0)).getWidget(0)).getHTML());
			((ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(1)).addChangeListener(ChartViewerController.fillColumnValues(this, i, dimY.getItemId(), (ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(1), getDimensionX(), (ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(3), (com.google.gwt.user.client.ui.VerticalPanel) otherDim.getWidget(0)));
				
				BirtServiceEntry.getInstance().getColumnNames(Long.valueOf(dimY.getItemId()), new AsyncCallback<String[]>(){
					public void onSuccess(String[] columnNames){
						for (String s: columnNames){
							if (!DimensionBlackList.blackDimensionList.contains(s.toLowerCase())){
								((ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(1)).addItem(s);
							}
						}
						
						for (int k=0; k<((ListBox) ((VerticalPanel) dimY.getWidget(0)).getWidget(1)).getItemCount(); k++){
							if (((ListBox) ((VerticalPanel) dimY.getWidget(0)).getWidget(1)).getItemText(k).equals(chartWizardBean.getDimensionY().get(dimY.getItemId()))){
								((ListBox) ((VerticalPanel) dimY.getWidget(0)).getWidget(1)).setSelectedIndex(k);
							}
						}
													
						
						TableServiceEntry.getInstance().getDimensionValues(Long.valueOf(dimY.getItemId()), ((ListBox) ((VerticalPanel) dimY.getWidget(0)).getWidget(1)).getItemText(((ListBox) ((VerticalPanel) dimY.getWidget(0)).getWidget(1)).getSelectedIndex()), CheckLanguage.getLanguage(), new AsyncCallback<Map<String, String>>(){
							
							public void onSuccess(Map<String, String> values){
								
								Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
								for (int i = 0; i < values.size(); i++) {
									//el = new ArrayList<String>();
									Map.Entry<String, String> entry = iterator.next();
									((ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(3)).addItem(entry.getValue(), entry.getKey());
								}
								
								if (chartWizardBean.getChartType().equals("BarLine")){
									
									final List<String> listOfDimensionBar = new ArrayList<String>();	
									final Map<String, String> mapOfDimensionBar = new HashMap<String, String>();
									for (List<String> element : chartWizardBean.getDimensionValuesYBar().get(dimY.getItemId())){
										listOfDimensionBar.add(element.get(1));
										//I assume that the label are unique DANGER
										mapOfDimensionBar.put(element.get(1), element.get(0));
										//((ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(6)).addItem(element.get(1), element.get(0));
									}
									Collections.sort(listOfDimensionBar);
									for (String element :  listOfDimensionBar){
										((ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(6)).addItem(element, mapOfDimensionBar.get(element));
									}
									
									final List<String> listOfDimensionLine = new ArrayList<String>();	
									final Map<String, String> mapOfDimensionLine = new HashMap<String, String>();
									for (List<String> element : chartWizardBean.getDimensionValuesYLine().get(dimY.getItemId())){
										listOfDimensionLine.add(element.get(1));
										//I assume that the label are unique DANGER
										mapOfDimensionLine.put(element.get(1), element.get(0));
										//((ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(9)).addItem(element.get(1), element.get(0));
									}
									Collections.sort(listOfDimensionLine);
									for (String element :  listOfDimensionLine){
										((ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(9)).addItem(element, mapOfDimensionLine.get(element));
									}
								
								} else {
									selectValue(((ListBox)((VerticalPanel) dimY.getWidget(0)).getWidget(3)), chartWizardBean.getDimensionValuesY().get(dimY.getItemId()));
								}
								
					
								dimY.layout();
							}
						
							public void onFailure(Throwable caught){
								Info.display("getColumnValues", caught.getLocalizedMessage());
							}
						
						});
					}
					
					public void onFailure(Throwable caught){
						Info.display("getColumnNames", caught.getLocalizedMessage());
					}
				
				});
			

			
			List<List<Object>> otherDimList = new ArrayList<List<Object>>();
			List<Object> element;
			
			//Window.alert(String.valueOf(((VerticalPanel)otherDim.getWidget(0)).getWidgetCount()/2));
			for (int k=1; k<((VerticalPanel)otherDim.getWidget(0)).getWidgetCount(); k++){
				HorizontalPanel hr = (HorizontalPanel)((VerticalPanel)otherDim.getWidget(0)).getWidget(k); 
				element = new ArrayList<Object>();
				element.add(((HTML) hr.getWidget(0)).getHTML());
				element.add((ListBox) hr.getWidget(1));
				element.add((CheckBox) hr.getWidget(2));
				otherDimList.add(element);
				
			}
			
			final Map<String, String> mapOtherDim = new HashMap<String, String>();
			final Map<String, String> mapOtherDimEnaDis = new HashMap<String, String>();
			for (List<String> dim : chartWizardBean.getOtherDimension().get(otherDim.getItemId())){
				mapOtherDim.put(dim.get(0), dim.get(1));
				if (dim.size() == 3){
					mapOtherDimEnaDis.put(dim.get(0), dim.get(2));
				} else {
					//FIXME workaround because at the moment I can't save this information without touching the domain
					mapOtherDimEnaDis.put(dim.get(0), "false");
				}
			}
			
			for (final List<Object> dimO: otherDimList){
				
				//BirtServiceEntry.getInstance().getColumnValues(Long.valueOf(otherDim.getItemId()), (String) dimO.get(0), new AsyncCallback<List<List<String>>>(){
				TableServiceEntry.getInstance().getDimensionValues(Long.valueOf(otherDim.getItemId()), (String) dimO.get(0), CheckLanguage.getLanguage(), new AsyncCallback<Map<String, String>>(){
					
					public void onSuccess(Map<String, String> values){
						
//						final List<String> listOfDimensionOther = new ArrayList<String>();	
//						final Map<String, String> mapOfDimensionOther = new HashMap<String, String>();
						
						if (mapOtherDimEnaDis.get((String) dimO.get(0)).equals("true")) ((CheckBox) dimO.get(2)).setValue(true); 
						else ((CheckBox) dimO.get(2)).setValue(false);
						
						if (values.size() == 0){
							((ListBox) dimO.get(1)).addItem("null", "");
						} else {
							Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
							for (int i = 0; i < values.size(); i++) {
								//el = new ArrayList<String>();
								Map.Entry<String, String> entry = iterator.next();
								((ListBox) dimO.get(1)).addItem(entry.getValue(), entry.getKey());
//								el.add();
//								el.add(entry.getValue());
//								revertdata.add(el);
							}
							
//							Collections.sort(listOfDimensionOther);
//							for (String element :  listOfDimensionOther){
//								((ListBox) dimO.get(1)).addItem(element, mapOfDimensionOther.get(element));
//							}
							
							for (int k=0; k<((ListBox) dimO.get(1)).getItemCount(); k++){
								if (((ListBox) dimO.get(1)).getValue(k).equals(mapOtherDim.get((String) dimO.get(0)))){
									((ListBox) dimO.get(1)).setSelectedIndex(k);
								}
							}
						}
						
					}
				
					public void onFailure(Throwable caught){
						Info.display("getColumnValues", caught.getLocalizedMessage());
					}
				
				});
			
				
				
			}
			
			
			
		}
		
		//show chart window

		// workaround for the rendering of the object with the new gwt
		panelYAxis.setAutoHeight(true);
		panelYAxis.setResizeTabs(true);
		panelYAxis.setWidth(300);
		panelYAxis.setTabScroll(true);
		panelYAxis.setBodyBorder(true);
		
//		panelOtherDim.setHeight(285);
		panelOtherDim.setAutoHeight(true);
		panelOtherDim.setWidth(300);
		panelOtherDim.setResizeTabs(true);
		panelOtherDim.setBodyBorder(true);
		panelOtherDim.setTabScroll(true);
		show();
		
	}		
	
	
	private void populateSideBar(final ChartWizardBean chartWizardBean){
		
		HorizontalPanel agrCont = new HorizontalPanel();
		agrCont.setSpacing(5);
		agrCont.add(new HTML("Aggregation"));
		
		aggregation.setWidth("120px");
		aggregation.addItem("SUM");
		aggregation.addItem("MIN");
		aggregation.addItem("MAX");
		aggregation.addItem("AVG");
		aggregation.addItem("FREQUENCY");
		
		agrCont.add(aggregation);
		sideBar.getVpContDS().add(agrCont);
		
		
		
		//dataset List
		List<String> listTemp;
		HorizontalPanel hr;
		CheckBox checkBoxTmp;
		for (List<String> dsList : chartWizardBean.getDatasetId()){
			hr = new HorizontalPanel();
			hr.setSpacing(5);
			checkBoxTmp = new CheckBox();
			checkBoxTmp.setValue(true);
			listCheckBoxDataset.add(checkBoxTmp);
			//hr.add(listCheckBoxDataset.get((listCheckBoxDataset.size()-1)));
			hr.add(new HTML(dsList.get(0)));
			listTemp = new ArrayList<String>();
			listTemp.add(dsList.get(0));
			listTemp.add(dsList.get(1));
			mapDatasetList.put(listCheckBoxDataset.get((listCheckBoxDataset.size()-1)), listTemp);
			//sideBar.getVpContDS().insert(hr, sideBar.getVpContDS().getItemCount());
			sideBar.getVpContDS().add(hr);
			
		}
		
				
		//X axis
		dimensionX = new ListBox();
		sideBar.getVpChartAxis().add(new HTML(BabelFish.print().axisX()+ ":"));
		sideBar.getVpChartAxis().add(dimensionX);
		List<String> listDS = new ArrayList<String>();
		for (int i=0; i< listCheckBoxDataset.size(); i++){
			if (listCheckBoxDataset.get(i).getValue()){
				listDS.add(mapDatasetList.get(listCheckBoxDataset.get(i)).get(1));
			}
		}
		
		BirtServiceEntry.getInstance().getColumnNames(listDS, new AsyncCallback<String[]>(){
			
			public void onSuccess(String[] columnNames){
				
					for(String s: columnNames){
						if (!DimensionBlackList.blackDimensionList.contains(s)){
							dimensionX.addItem(s);
						}
					}
					dimensionX.setSelectedIndex(getIndexListBox(dimensionX, chartWizardBean.getDimensionX()));
					/*** CHANGE IT **/
					dimensionX.addChangeListener(ChartViewerController.fillColumnValuesX(ChartViewer.this));
					
					//X axis values
					dimensionXValues = new ListBox(true);
					dateLastUpdatePanel = new DateLastUpdatePanel();
					dateFromToPanel = new DateFromToPanel();
					sideBar.getVpChartAxis().add(new HTML(BabelFish.print().axisXValues() + ":"));
					sideBar.getVpChartAxis().add(buildXValuesPanel());
					

					// CHANGES TO SORT DI DIMENSIONS 
					/** TODO: QUICK FIX, should be checked on the column datatype and no column header **/ 
					BirtServiceEntry.getInstance().columnIsDate(Long.valueOf(mapDatasetList.get(listCheckBoxDataset.get(0)).get(1)), dimensionX.getValue(dimensionX.getSelectedIndex()), new AsyncCallback<DescriptorVO>() {
						public void onSuccess(final DescriptorVO descriptor) {	
							final Counter counter = new Counter();
							List<Long> datasetsIds = new ArrayList<Long>();
							for (int i=0; i < listCheckBoxDataset.size(); i++) {
								if (listCheckBoxDataset.get(i).getValue()) {
									datasetsIds.add(Long.valueOf(mapDatasetList.get(listCheckBoxDataset.get(i)).get(1)));
								}
							}
//							System.out.println("-> " + dimensionX.getValue(dimensionX.getSelectedIndex()));
							if ( descriptor != null ) {
								getDatePanel().setVisible(true);
								getDateLUPanel().setVisible(true);
							}
							else {
								getDatePanel().setVisible(false);
								getDateLUPanel().setVisible(false);
							}				
							TableServiceEntry.getInstance().getDimensionValues(datasetsIds, dimensionX.getItemText(dimensionX.getSelectedIndex()), CheckLanguage.getLanguage(), new AsyncCallback<Map<String, String>>() {
								public void onSuccess(Map<String, String> values){
//									System.out.println("TableServiceEntry.getInstance().getDimensionValues(datasetsIds...");
									String startDate = new String();
									String endDate = new String();
									Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();								
									for (int i = 0; i < values.size(); i++) {
										Map.Entry<String, String> entry = iterator.next();
										dimensionXValues.addItem(entry.getValue(),entry.getKey());
										
										/** TODO: should be on selected values **/
										if ( descriptor != null ) { 
											if  ( i == values.size() - 1) 
												startDate = entry.getKey();
											if ( i == 0 ) 
												endDate = entry.getKey();											
										}
									}
									if ( descriptor != null )  {
										DateFromToPanel p = getDateFromToPanel();
										DateLastUpdatePanel lu = getDateLastUpdatePanel();
										p.setPeriodType(descriptor.getContentDescriptor());
										lu.setPeriodType(descriptor.getContentDescriptor());
										DateUtils.setDateFromToDefault(startDate, endDate, descriptor.getContentDescriptor(), p.getYearFrom(), p.getMonthFrom(), p.getDayFrom(), p.getYearTo(), p.getMonthTo(), p.getDayTo());
										DateUtils.setDateLastUpdateDefault(lu.getYears(), lu.getMonths(), lu.getDays(), descriptor.getContentDescriptor(), p.getYearFrom().getItemText(p.getYearFrom().getSelectedIndex()), p.getYearTo().getItemText(p.getYearTo().getSelectedIndex()));
										if(chartWizardBean.isMostRecent()) {
											/** setting check box **/
											lu.getCheckBox().setValue(true);
											lu.setBox();
											lu.getYears().setSelectedIndex(Integer.valueOf(chartWizardBean.getMostRecent().getYears()));
											lu.getMonths().setSelectedIndex(Integer.valueOf(chartWizardBean.getMostRecent().getMonths()));
											lu.getDays().setSelectedIndex(Integer.valueOf(chartWizardBean.getMostRecent().getDays()));
//											lu.getYears(), lu.getMonths(), lu.getDays();
//											System.out.println("------------------------YES(): " + chartWizardBean.isMostRecent());											
										}
										else {
//											System.out.println("------------------------NO(): " + chartWizardBean.isMostRecent());											
										}
									}
						
									System.out.println("counter.getCount(): " + counter.getCount());
									System.out.println("listCheckBoxDataset: " + listCheckBoxDataset.size());
//									if ((counter.getCount()+1)==listCheckBoxDataset.size()){
//										ChartWizardController.selectAllItemsToListBox(dimensionXValues);
										System.out.println("chartWizardBean.getDimensionValuesX(): " + chartWizardBean.getDimensionValuesX());
										selectValue(dimensionXValues, chartWizardBean.getDimensionValuesX());
//									} 
//									else {
										counter.increaseCount();
//									}
								}
										
								public void onFailure(Throwable caught){
										Info.display("getColumnValues", caught.getLocalizedMessage());
									}
											
							});
						}
						
						public void onFailure(Throwable caught){
							Info.display("columnIsDate", caught.getLocalizedMessage());
						}		
					}); 
										
					
					
					//Y Dimension
//					ContentPanel panel = new ContentPanel();
					panelYAxis = new TabPanel();
					panelYAxis.setBodyBorder(false);
					panelYAxis.setTabScroll(true);
//					panel.add(panelYAxis);
					
					TabItem fakeYTabItem = new TabItem();
					fakeYTabItem.setText("fake");
					panelYAxis.add(fakeYTabItem);
					
					
					panelOtherDim = new TabPanel();
					panelOtherDim.setBodyBorder(false);
					panelOtherDim.setTabScroll(true);
					
					TabItem fakeOTabItem = new TabItem();
					fakeOTabItem.setText("fake");
					panelOtherDim.add(fakeOTabItem);
					

					for (final List<String> dsId: chartWizardBean.getDatasetId()){
								final TabItem newYDimension = new TabItem();
								newYDimension.setScrollMode(Scroll.AUTO);
								newYDimension.setAutoHeight(true);
								newYDimension.setText(dsId.get(0));
								newYDimension.setToolTip(dsId.get(0));
								newYDimension.setItemId(dsId.get(1));
								newYDimension.add(new VerticalPanel());
								((VerticalPanel) newYDimension.getWidget(0)).setSpacing(5);
								
								if (chartWizardBean.getChartType().equals("BarLine")){
									
									((VerticalPanel) newYDimension.getWidget(0)).add(new HTML(BabelFish.print().axisY() + ":"));
									((VerticalPanel) newYDimension.getWidget(0)).add(new ListBox());
									((VerticalPanel) newYDimension.getWidget(0)).add(new HTML(BabelFish.print().axisYValues() + ":"));
									((VerticalPanel) newYDimension.getWidget(0)).add(new ListBox(true));
									((ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(3)).setSize("200px", "130px");
									((VerticalPanel) newYDimension.getWidget(0)).add(new HorizontalPanel());
									((HorizontalPanel)((VerticalPanel) newYDimension.getWidget(0)).getWidget(4)).setSpacing(5);
									((HorizontalPanel)((VerticalPanel) newYDimension.getWidget(0)).getWidget(4)).add(new Button("Add as Bar"));
									((HorizontalPanel)((VerticalPanel) newYDimension.getWidget(0)).getWidget(4)).add(new Button("Add as Line"));
									
									((VerticalPanel) newYDimension.getWidget(0)).add(new HTML("Bar: "));
									((VerticalPanel) newYDimension.getWidget(0)).add(new ListBox(true));
									((ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(6)).setSize("200px", "60px");
									((VerticalPanel) newYDimension.getWidget(0)).add(new Button("Delete bar values"));
																		
									((VerticalPanel) newYDimension.getWidget(0)).add(new HTML("Line: "));
									((VerticalPanel) newYDimension.getWidget(0)).add(new ListBox(true));
									((ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(9)).setSize("200px", "60px");
									((VerticalPanel) newYDimension.getWidget(0)).add(new Button("Delete line values"));
									
									HorizontalPanel hrBarLineButtons = (HorizontalPanel)((VerticalPanel) newYDimension.getWidget(0)).getWidget(4); 
									((Button) hrBarLineButtons.getWidget(0)).addSelectionListener(ChartWizardController.fillBarLine((ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(3), (ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(6), (ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(9)));
									((Button) hrBarLineButtons.getWidget(1)).addSelectionListener(ChartWizardController.fillBarLine((ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(3), (ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(9), (ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(6)));
									((Button) ((VerticalPanel) newYDimension.getWidget(0)).getWidget(7)).addSelectionListener(ChartWizardController.deleteBarLineItems((ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(6)));
									((Button) ((VerticalPanel) newYDimension.getWidget(0)).getWidget(10)).addSelectionListener(ChartWizardController.deleteBarLineItems((ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(9)));
									
									
								} else {
									
									((VerticalPanel) newYDimension.getWidget(0)).add(new HTML(BabelFish.print().axisY() + ":"));
									((VerticalPanel) newYDimension.getWidget(0)).add(new ListBox());
									((VerticalPanel) newYDimension.getWidget(0)).add(new HTML(BabelFish.print().axisYValues() + ":"));
									((VerticalPanel) newYDimension.getWidget(0)).add(new ListBox(true));
									((ListBox)((VerticalPanel) newYDimension.getWidget(0)).getWidget(3)).setSize("200px", "130px");
									
								}
								
								
								
								panelYAxis.add(newYDimension);
								
								final TabItem newOtherDimension = new TabItem();
								newOtherDimension.setScrollMode(Scroll.AUTO);
								newOtherDimension.setAutoHeight(true);
								newOtherDimension.setText(dsId.get(0));
								newOtherDimension.setToolTip(dsId.get(0));								
								
								newOtherDimension.setItemId(dsId.get(1));
								newOtherDimension.add(new VerticalPanel());
								((VerticalPanel) newOtherDimension.getWidget(0)).setSpacing(5);
								((VerticalPanel) newOtherDimension.getWidget(0)).add(new HTML(BabelFish.print().otherDim()+":"));
								
								for (final List<String> l: chartWizardBean.getOtherDimension().get(dsId.get(1))){
									HorizontalPanel hpTmp = new HorizontalPanel();
									hpTmp.setSpacing(5);
									HTML t = new HTML(l.get(0));
									t.setStyleName("simpleText");
									hpTmp.add(t);
									hpTmp.add(new ListBox());
									hpTmp.add(new CheckBox());
									((VerticalPanel) newOtherDimension.getWidget(0)).add(hpTmp);	
								}	
								
								panelOtherDim.add(newOtherDimension);
								
								
						
					}
					
					for (TabItem tab : panelYAxis.getItems()){
//						panelYAxis.setSelection(tab);
//						tab.layout();
//						sideBar.getVpChartAxis().layout();
						sideBar.getChartAxis().expand();
						sideBar.getChartAxis().collapse();
					}
//					
					for (TabItem tab : panelOtherDim.getItems()){
						sideBar.getOtherDimension().expand();
						sideBar.getOtherDimension().collapse();
					}
					
//					for (TabItem tab : chartWizard.getSelectData().getPanelYAxis().getItems()){
//						chartWizard.getSelectData().getContYAxis().expand();
//						chartWizard.getSelectData().getContYAxis().collapse();
//					}
					
					panelYAxis.remove(fakeYTabItem);
					panelOtherDim.remove(fakeOTabItem);

					
					sideBar.getVpChartAxis().add(panelYAxis);
//					sideBar.getVpChartAxis().add(panel);
					sideBar.getVpOtherDim().add(panelOtherDim);
					populateDimension();
					
					
			}
			
			public void onFailure(Throwable caught){
				Info.display("getColumnNames", caught.getLocalizedMessage());
			}
		
		 });
			
	}
	
	public ChartViewer(String chartViewID){
		this.chartWizardBean = new ChartWizardBean();
		setChartViewID(Long.valueOf(chartViewID));
		build();		
//		System.out.println("opening chart");
		BirtServiceEntry.getInstance().nameFileById(Long.valueOf(chartViewID), new AsyncCallback<String>(){
			public void onSuccess(final String rptdesign){
//				System.out.println(rptdesign);
				setRptdesign(rptdesign);
				BirtServiceEntry.getInstance().openReport(rptdesign.substring(0, (rptdesign.length()-10)), "preview", new AsyncCallback<String>(){
					public void onSuccess(String iframeChart){
//						System.out.println("iframe chart: " +iframeChart);
						chart.setHTML(iframeChart);
					}
				
					public void onFailure(Throwable caught){
						Info.display("openReport", caught.getLocalizedMessage());
					}
				});
			}
		
			public void onFailure(Throwable caught){
				Info.display("nameFileById", caught.getLocalizedMessage());
			}
		});
		BirtServiceEntry.getInstance().getChart(Long.valueOf(chartViewID), this.getChartWizardBean(), new AsyncCallback<ChartWizardBean>(){
			
			public void onSuccess(ChartWizardBean chartWizardBean){
				setChartWizardBean(chartWizardBean);
				populateSideBar(chartWizardBean);
			}
		
			public void onFailure(Throwable caught){
				Info.display("getChart", caught.getLocalizedMessage());
			}
		});
	}
	

	
	public ChartViewer(List<String> preview, ChartWizardBean chartWizardBean){
		this.chartWizardBean = chartWizardBean;
		build();
		populateSideBar(chartWizardBean);
		this.rptdesign = preview.get(0);
//		System.out.println("iframe chartpreview.get(1): " +preview.get(1));
		chart.setHTML(preview.get(1));
	}
	
	
	public ChartViewer(String chartViewID, boolean update) {
//		System.out.println("updateChart(String chartViewID): " + chartViewID );
		this.chartWizardBean = new ChartWizardBean();
		setShow(false);
		setChartViewID(Long.valueOf(chartViewID));
		final ChartViewer chartViewer = this;
		BirtServiceEntry.getInstance().nameFileById(Long.valueOf(chartViewID), new AsyncCallback<String>(){
			public void onSuccess(final String rptdesign){
				setRptdesign(rptdesign);
			}
		
			public void onFailure(Throwable caught){
				Info.display("nameFileById", caught.getLocalizedMessage());
			}
		});
		
		BirtServiceEntry.getInstance().getChart(Long.valueOf(chartViewID), this.getChartWizardBean(), new AsyncCallback<ChartWizardBean>(){
			public void onSuccess(ChartWizardBean chartWizardBean){
				setChartWizardBean(chartWizardBean);
				List<Long> datasetsIds = new ArrayList<Long>();
				for (int i=0; i < chartWizardBean.getDatasetId().size(); i++) 
					datasetsIds.add(Long.valueOf(chartWizardBean.getDatasetId().get(i).get(1)));	
				
				TableServiceEntry.getInstance().getDimensionValues(datasetsIds,chartWizardBean.getDimensionX() ,CheckLanguage.getLanguage(), new AsyncCallback<Map<String, String>>() {
					public void onSuccess(Map<String, String> values){
						String latestDateString = new String();
						Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();								
						for (int i = 0; i < values.size(); i++) {
							Map.Entry<String, String> entry = iterator.next();								
//							if ( i == values.size() - 1) 
							if ( i == 0) 
								latestDateString = entry.getKey();				
//							System.out.println("values: " + values);
//							System.out.println("latestDateString: " + latestDateString);
						}
						Date latestDate = FieldParser.parseDate(latestDateString);
						ChartViewerController.updateChart(chartViewer, latestDate, values);
					}
							
					public void onFailure(Throwable caught){
						Info.display("getColumnValues", caught.getLocalizedMessage());
					}
								
				});
			}
				
			public void onFailure(Throwable caught){
				Info.display("columnIsDate", caught.getLocalizedMessage());
			}		
		});	
	}
	
	
	private ContentPanel buildXValuesPanel() {
//		System.out.println("buildXValuesPanel");
		ContentPanel v = new ContentPanel();
		v.setHeaderVisible(false);
		v.setBodyBorder(false);
		v.setBorders(false);
		v.setScrollMode(Scroll.AUTO);
		v.add(buildXAxisValuesPanel());
		v.add(buildFromToDatePanel());
		v.add(buildLastUpdatePanel());
		return v;
	}
	
	private VerticalPanel buildFromToDatePanel(){
		datePanel = new VerticalPanel();
		datePanel.add(dateFromToPanel.build(dimensionXValues, xValuesCheckBox, dateLastUpdatePanel, "100px"));
		datePanel.setVisible(false);
		return datePanel;
	}
	
	private VerticalPanel buildLastUpdatePanel(){
		dateLUPanel = new VerticalPanel();
		dateLUPanel.add(dateLastUpdatePanel.build(dimensionXValues, xValuesCheckBox, dateFromToPanel, "100px"));
		dateLUPanel.setVisible(false);
		return dateLUPanel;
	}
	
	private VerticalPanel buildXAxisValuesPanel() {
		VerticalPanel p  = new VerticalPanel();
		xValuesCheckBox = new CheckBox();
		xValuesCheckBox.setBoxLabel(BabelFish.print().axisXValues());
		xValuesCheckBox.setValue(true);
		Listener<ComponentEvent> t = new Listener<ComponentEvent>() {  
	        public void handleEvent(ComponentEvent ce) {  
	        		setBox();
		        }
		    };  
		xValuesCheckBox.addListener(Events.OnClick, t);
		dimensionXValues.setSize("200px", "140px");
		p.add(xValuesCheckBox);
		p.add(dimensionXValues);
		return p;
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
		getChartWizardBean().setMostRecent(false);
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	
	
	
}
