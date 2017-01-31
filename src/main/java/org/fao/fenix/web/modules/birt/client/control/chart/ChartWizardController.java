package org.fao.fenix.web.modules.birt.client.control.chart;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.utils.Counter;
import org.fao.fenix.web.modules.birt.client.utils.DateUtils;
import org.fao.fenix.web.modules.birt.client.view.chart.viewer.ChartViewer;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartType;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateFromToPanel;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateLastUpdatePanel;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.QuantityDimension;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChartWizardController {
	
	

	public static boolean goChart(final ChartWizard chartWizard){
		ListBox xValues = chartWizard.getSelectData().getDimensionXValues();
		if (xValues.getSelectedIndex() == -1) return false;
			
		ListBox listYValues;
		for (TabItem tab : chartWizard.getSelectData().getPanelYAxis().getItems()){
			 listYValues = chartWizard.getSelectData().getDimensionYValues().get(tab.getItemId());
			 if (listYValues.getSelectedIndex() == -1) return false;
		}
		
		return true;
	}
	
	public static SelectionListener<ButtonEvent> changeTab(final int numTab, final ChartWizard chartWizard){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				chartWizard.getPanel().selectTab(numTab);
			}
		};
	}
	
	public static void selectAllItemsToListBox(ListBox list){
		for(int i=0; i < list.getItemCount(); i++){
			list.setItemSelected(i, true);
		}
	}
	
	public static void uniqueValue(List<String> listOfDate, String value){
		if (!listOfDate.contains(value)){
			listOfDate.add(value);
		}
		
	}
	
	public static boolean uniqueValue(ListBox list, String value){
		for (int i=0; i < list.getItemCount(); i++){
			if (list.getValue(i).equals(value)){
				return false;
			}
		}
		return true;
	}
	
	public static ClickListener activeDisactive(final HorizontalPanel cont,	final String nameChart, final ChartWizardBean chartWizardBean, final ChartType chartType) {
		return new ClickListener() {
			public void onClick(Widget wid) {
				// if the user doesn't click on the chart type already selected
				// I change the chart type
				if (cont != chartType.getChartTypeActivated()) {
					DOM.setStyleAttribute(chartType.getChartTypeActivated().getElement(), "backgroundColor", "#cbc4c4");
					DOM.setStyleAttribute(cont.getElement(), "backgroundColor", "#FF0000");
					chartType.setChartTypeActivated(cont);
					chartWizardBean.setChartType(nameChart);
				}

			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addDataset(final ChartWizard chartWizard){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				new ResourceExplorerDataset(chartWizard);
			}
		};
	}
	
	
	private static boolean uniqueValueToListBoxs(ListBox first, ListBox second, String value){
		boolean found = false;
		
		for (int i=0; i<first.getItemCount(); i++){
			if (first.getValue(i).equals(value)){
				found = true;
				return found;
			}
		}
		
		for (int i=0; i<second.getItemCount(); i++){
			if (second.getValue(i).equals(value)){
				found = true;
				return found;
			}
		}
				
		return found;
	}
	
	
	public static SelectionListener<ButtonEvent> fillBarLine(final ListBox from, final ListBox first, final ListBox second){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				for (int i=0; i<from.getItemCount(); i++){
					if (from.isItemSelected(i)){
						if (!uniqueValueToListBoxs(first, second, from.getValue(i)))
							first.addItem(from.getItemText(i), from.getValue(i));
					}
				}
				
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> deleteBarLineItems(final ListBox list){
		return new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				ListBox temp = new ListBox();
				for (int i=0; i<list.getItemCount(); i++){
					if (!list.isItemSelected(i)){
						temp.addItem(list.getItemText(i), list.getValue(i));
					}
				}
				
				//TODO optimize 
				list.clear();
				for (int i=0; i<temp.getItemCount(); i++){
					list.addItem(temp.getItemText(i), temp.getValue(i));
				}
						
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> loadDataset(final ChartWizard chartWizard) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				//reset
				chartWizard.getSelectData().getDimensionX().clear();
				chartWizard.getSelectData().getDimensionX().addItem(" "," ");
				chartWizard.getSelectData().getDimensionXValues().clear();
				chartWizard.getSelectData().getDimensionY().clear();
				chartWizard.getSelectData().getOtherDimensions().clear();
				chartWizard.getSelectData().getPanelYAxis().removeAll();
				chartWizard.getSelectData().resetDatePanel();
				
				final List<List<String>> dsId = new ArrayList<List<String>>();
				List<String> listTemp;
				for (int i=0; i<chartWizard.getSelectData().getListCheckBoxDataset().size(); i++){
					if (chartWizard.getSelectData().getListCheckBoxDataset().get(i).getValue()){
						listTemp = new ArrayList<String>();
						listTemp.add(chartWizard.getSelectData().getMapDatasetList().get(chartWizard.getSelectData().getListCheckBoxDataset().get(i)).get(0));
						listTemp.add(chartWizard.getSelectData().getMapDatasetList().get(chartWizard.getSelectData().getListCheckBoxDataset().get(i)).get(1));
						dsId.add(listTemp);
					}
				}
				
				if (dsId.size() > 0){
					List<String> listDS = new ArrayList<String>();
					for (final List<String> l : dsId){
						listDS.add(l.get(1));
					}//end for to count the datasets selected
					
					
							
					BirtServiceEntry.getInstance().getColumnNames(listDS, new AsyncCallback<String[]>(){
							
						public void onSuccess(String[] columnNames){
							for(String s: columnNames){
									System.out.println("chartWizard.getSelectData().getDimensionX().addItem(s);: " + s);
										chartWizard.getSelectData().getDimensionX().addItem(s);
								}
							
								
//							TabItem fakeTabItem = new TabItem();
//							chartWizard.getSelectData().getPanelYAxis().add(fakeTabItem);
//							fakeTabItem.setText("fake");
										
								for (final List<String> l : dsId){	
									
									TabItem fakeTabItem = new TabItem();
									chartWizard.getSelectData().getPanelYAxis().add(fakeTabItem);
									fakeTabItem.setText("fake");
									
									 BirtServiceEntry.getInstance().getColumnNames(Long.valueOf(l.get(1)), new AsyncCallback<String[]>(){
										 
										public void onSuccess(String[] columnNames){
											
											
											
											//Y values
											TabItem newYDimension = new TabItem();
											newYDimension.setScrollMode(Scroll.AUTO);
											newYDimension.setAutoHeight(true);
											newYDimension.setText(l.get(0));
											newYDimension.setItemId(l.get(1));
											chartWizard.getSelectData().getDimensionY().put(l.get(1), new ListBox());
											chartWizard.getSelectData().getDimensionYValues().put(l.get(1),new ListBox(true));
											chartWizard.getSelectData().getOtherDimensions().put(l.get(1), new VerticalPanel());
											VerticalPanel vrAxisY = new VerticalPanel();
											
											HorizontalPanel hrAxisYName = new HorizontalPanel();
											hrAxisYName.setSpacing(5);
											HTML axisY = new HTML(BabelFish.print().axisY() + "&nbsp;&nbsp;&nbsp;&nbsp;");
											axisY.setStyleName("simpleText");
											hrAxisYName.add(axisY);
											chartWizard.getSelectData().getDimensionY().get(l.get(1)).setWidth("200px");
											chartWizard.getSelectData().getDimensionY().get(l.get(1)).addChangeListener(ChartWizardController.fillColumnValues(chartWizard, l.get(1), chartWizard.getSelectData().getDimensionY().get(l.get(1)), chartWizard.getSelectData().getDimensionX(), chartWizard.getSelectData().getDimensionYValues().get(l.get(1)), chartWizard.getSelectData().getOtherDimensions().get(l.get(1))));
											chartWizard.getSelectData().getDimensionY().get(l.get(1)).addItem(" "," ");
											hrAxisYName.add(chartWizard.getSelectData().getDimensionY().get(l.get(1)));
											vrAxisY.add(hrAxisYName);
											
											
											HorizontalPanel hrAxisYValues = new HorizontalPanel();
											hrAxisYValues.setSpacing(5);
											HTML valueY = new HTML(BabelFish.print().axisYValues());
											valueY.setStyleName("simpleText");
											hrAxisYValues.add(valueY);
											chartWizard.getSelectData().getDimensionYValues().get(l.get(1)).setSize("200px", "150px");
											hrAxisYValues.add(chartWizard.getSelectData().getDimensionYValues().get(l.get(1)));
											if (chartWizard.getChartWizardBean().getChartType().equals("BarLine")){
												VerticalPanel contBarLine = new VerticalPanel();
												chartWizard.getSelectData().getDimensionYValuesBar().put(l.get(1),new ListBox(true));
												chartWizard.getSelectData().getDimensionYValuesLine().put(l.get(1),new ListBox(true));
												
												HorizontalPanel hrForBar = new HorizontalPanel();
												hrForBar.setSpacing(5);
												VerticalPanel vp0Shit = new VerticalPanel();
												vp0Shit.setSpacing(3);
												vp0Shit.add(new HTML("&nbsp;"));
												Button barButtonAdd = new Button(">");
												vp0Shit.add(barButtonAdd);
												Button barButtonDel = new Button("<");
												vp0Shit.add(barButtonDel);
												hrForBar.add(vp0Shit);
												VerticalPanel vp1Shit = new VerticalPanel();
												vp1Shit.add(new HTML("Bar:"));
												chartWizard.getSelectData().getDimensionYValuesBar().get(l.get(1)).setSize("200px", "50px");
												vp1Shit.add(chartWizard.getSelectData().getDimensionYValuesBar().get(l.get(1)));
												hrForBar.add(vp1Shit);
												
												HorizontalPanel hrForLine = new HorizontalPanel();
												hrForLine.setSpacing(5);
												VerticalPanel vp3Shit = new VerticalPanel();
												vp3Shit.setSpacing(3);
												vp3Shit.add(new HTML("&nbsp;"));
												Button lineButtonAdd = new Button(">");
												vp3Shit.add(lineButtonAdd);
												Button lineButtonDel = new Button("<");
												vp3Shit.add(lineButtonDel);
												hrForLine.add(vp3Shit);
												VerticalPanel vp2Shit = new VerticalPanel();
												vp2Shit.add(new HTML("Line:"));
												chartWizard.getSelectData().getDimensionYValuesLine().get(l.get(1)).setSize("200px", "50px");
												vp2Shit.add(chartWizard.getSelectData().getDimensionYValuesLine().get(l.get(1)));
												hrForLine.add(vp2Shit);
												
												barButtonAdd.addSelectionListener(ChartWizardController.fillBarLine(chartWizard.getSelectData().getDimensionYValues().get(l.get(1)), chartWizard.getSelectData().getDimensionYValuesBar().get(l.get(1)), chartWizard.getSelectData().getDimensionYValuesLine().get(l.get(1))));
												lineButtonAdd.addSelectionListener(ChartWizardController.fillBarLine(chartWizard.getSelectData().getDimensionYValues().get(l.get(1)), chartWizard.getSelectData().getDimensionYValuesLine().get(l.get(1)), chartWizard.getSelectData().getDimensionYValuesBar().get(l.get(1))));
												barButtonDel.addSelectionListener(ChartWizardController.deleteBarLineItems(chartWizard.getSelectData().getDimensionYValuesBar().get(l.get(1))));
												lineButtonDel.addSelectionListener(ChartWizardController.deleteBarLineItems(chartWizard.getSelectData().getDimensionYValuesLine().get(l.get(1))));
												contBarLine.add(hrForBar);
												contBarLine.add(hrForLine);
												
												hrAxisYValues.add(contBarLine);
											}
											
											vrAxisY.add(hrAxisYValues);
											
											newYDimension.add(vrAxisY);
											newYDimension.add(chartWizard.getSelectData().getOtherDimensions().get(l.get(1)));
											
											chartWizard.getSelectData().getPanelYAxis().add(newYDimension);
											chartWizard.getSelectData().getPanelYAxis().setSelection(newYDimension);
											
											for(String dim: columnNames){
													chartWizard.getSelectData().getDimensionY().get(l.get(1)).addItem(dim);														
											}
											
											chartWizard.getSelectData().getPanelYAxis().getLayout().layout();
										
									

										  }
								 
									
										public void onFailure(Throwable caught){
											Info.display("getColumnNames", caught.getLocalizedMessage());
										}
										
										
									
									 });
									
									 
										for (TabItem tab : chartWizard.getSelectData().getPanelYAxis().getItems()){
											chartWizard.getSelectData().getContYAxis().expand();
											chartWizard.getSelectData().getContYAxis().collapse();
										}
										
										chartWizard.getSelectData().getContYAxis().setExpanded(false);						
										chartWizard.getSelectData().getPanelYAxis().remove(fakeTabItem);
									 
								}//end for
								
								
								chartWizard.getSelectData().getContXAxis().setExpanded(true);
							}
							
						public void onFailure(Throwable caught){
							Info.display("getColumnNames", caught.getLocalizedMessage());
						}
						
					});
						
				}else{
					FenixAlert.alert("Message", "You need to select at least one dataset.");
				}
			}
		};
	}
	
	/*public static ChangeListener fillColumnValuesX(final ChartWizard chartWizard){
		return new ChangeListener(){
			
			public void onChange(Widget widget){
				
				chartWizard.getSelectData().getDimensionXValues().clear();
				
				//used to sort the dimensions
//				final List<String> listOfDimension = new ArrayList<String>();	
//				final Map<String, String> mapOfDimension = new HashMap<String, String>();
				final Counter counter = new Counter();
				for (int i=0; i<chartWizard.getSelectData().getListCheckBoxDataset().size(); i++){
						if (chartWizard.getSelectData().getListCheckBoxDataset().get(i).isChecked()){
							Long dsId = Long.valueOf(chartWizard.getSelectData().getMapDatasetList().get(chartWizard.getSelectData().getListCheckBoxDataset().get(i)).get(1));
							//BirtServiceEntry.getInstance().getColumnValues(dsId, chartWizard.getSelectData().getDimensionX().getItemText(chartWizard.getSelectData().getDimensionX().getSelectedIndex()), new AsyncCallback<List<List<String>>>(){
							TableServiceEntry.getInstance().getDimensionValues(dsId, chartWizard.getSelectData().getDimensionX().getItemText(chartWizard.getSelectData().getDimensionX().getSelectedIndex()), CheckLanguage.getLanguage(), new AsyncCallback<Map<String, String>>() {
								
								public void onSuccess(Map<String, String> values){
									
									Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
									for (int i = 0; i < values.size(); i++) {
										Map.Entry<String, String> entry = iterator.next();
										chartWizard.getSelectData().getDimensionXValues().addItem(entry.getValue(),entry.getKey());
										
									}
									
//									List<List<String>> revertdata = new ArrayList<List<String>>();
//									Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
//									List<String> el;
//									for (int i = 0; i < values.size(); i++) {
//										el = new ArrayList<String>();
//										Map.Entry<String, String> entry = iterator.next();
//										el.add(entry.getKey());
//										el.add(entry.getValue());
//										revertdata.add(el);
//									}
									
//										for (List<String> element : revertdata){
//											ChartWizardController.uniqueValue(listOfDimension, element.get(1));
//											//I assume that the label are unique DANGER
//											mapOfDimension.put(element.get(1), element.get(0));
//										}
										
										
										if ((counter.getCount()+1)==chartWizard.getSelectData().getListCheckBoxDataset().size()){
//											Collections.sort(listOfDimension);
//											for (String element :  listOfDimension){
//												chartWizard.getSelectData().getDimensionXValues().addItem(element, mapOfDimension.get(element));
//											}
											ChartWizardController.selectAllItemsToListBox(chartWizard.getSelectData().getDimensionXValues());
											if (goChart(chartWizard)){
												chartWizard.getSelectData().getOk().setEnabled(true);
												chartWizard.getFormatChart().getPreview().setEnabled(true);
											}
										} else {
											counter.increaseCount();
										}
																			
								}
							
								public void onFailure(Throwable caught){
									Info.display("getColumnValues", caught.getLocalizedMessage());
								}
							
							});
						}
									
					}
				
					
					//chartWizard.getSelectData().getContYAxis().setExpanded(true);
					
					List<String> idDataset = new ArrayList<String>();
					for (int k = 0;  k < chartWizard.getSelectData().getPanelYAxis().getItemCount(); k++){
						if (!chartWizard.getSelectData().getDimensionY().get(chartWizard.getSelectData().getPanelYAxis().getItem(k).getItemId()).getValue(chartWizard.getSelectData().getDimensionY().get(chartWizard.getSelectData().getPanelYAxis().getItem(k).getItemId()).getSelectedIndex()).equals(" ")){
							idDataset.add(chartWizard.getSelectData().getPanelYAxis().getItem(k).getItemId());
						}
					}
					
					if (idDataset.size()>0){
						ChartWizardController.fillOtherDimensionXEvent(idDataset, chartWizard);
					}
								
			}
		
		};
	}*/
	
	
	// modified because it didn't work the sort with more datasets 
	/** TODO: Modify all the queries with the column datatype and not with the column header **/
	public static ChangeListener fillColumnValuesX(final ChartWizard chartWizard){
		return new ChangeListener(){
			
			public void onChange(Widget widget){
				chartWizard.getSelectData().getDimensionXValues().clear();
				
				
				System.out.println("-> " + chartWizard.getSelectData().getDimensionX().getValue(chartWizard.getSelectData().getDimensionX().getSelectedIndex()));
				System.out.println("-> " + chartWizard.getSelectData().getDimensionX().getItemText(chartWizard.getSelectData().getDimensionX().getSelectedIndex()));
				
				/*** Test if the column is a date **/
				/** TODO: QUICK FIX, should be checked on the column datatype and no column header **/ 
				BirtServiceEntry.getInstance().columnIsDate(Long.valueOf(chartWizard.getSelectData().getMapDatasetList().get(chartWizard.getSelectData().getListCheckBoxDataset().get(0)).get(1)), chartWizard.getSelectData().getDimensionX().getValue(chartWizard.getSelectData().getDimensionX().getSelectedIndex()), new AsyncCallback<DescriptorVO>() {
					public void onSuccess(final DescriptorVO descriptor) {	
						List<Long> datasetsIds = new ArrayList<Long>();
						for (int i=0; i<chartWizard.getSelectData().getListCheckBoxDataset().size(); i++){
							if (chartWizard.getSelectData().getListCheckBoxDataset().get(i).getValue())
								datasetsIds.add(Long.valueOf(chartWizard.getSelectData().getMapDatasetList().get(chartWizard.getSelectData().getListCheckBoxDataset().get(i)).get(1)));
						}
						
						final Counter counter = new Counter();
						
						if ( descriptor != null) {
							chartWizard.getSelectData().getDatePanel().setVisible(true);
							chartWizard.getSelectData().getDateLUPanel().setVisible(true);
						}
						else {
							chartWizard.getSelectData().resetDatePanel();
							chartWizard.getSelectData().getDatePanel().setVisible(false);
							chartWizard.getSelectData().getDateLUPanel().setVisible(false);

						}
							TableServiceEntry.getInstance().getDimensionValues(datasetsIds, chartWizard.getSelectData().getDimensionX().getItemText(chartWizard.getSelectData().getDimensionX().getSelectedIndex()), CheckLanguage.getLanguage(), new AsyncCallback<Map<String, String>>() {
								public void onSuccess(Map<String, String> values){					
									Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
									String startDate = new String();
									String endDate = new String();
									for (int i = 0; i < values.size(); i++) {
										Map.Entry<String, String> entry = iterator.next();
										chartWizard.getSelectData().getDimensionXValues().addItem(entry.getValue(),entry.getKey());
										// adding dates 
										if ( descriptor != null ) { 
											if  ( i == values.size() - 1) 
												startDate = entry.getKey();
											if ( i == 0 ) 
												endDate = entry.getKey();
										}
									}
									if ( descriptor != null )  {
										DateFromToPanel p = chartWizard.getSelectData().getDateFromToPanel();
										DateLastUpdatePanel lu = chartWizard.getSelectData().getDateLastUpdatePanel();
										p.setPeriodType(descriptor.getContentDescriptor());
										lu.setPeriodType(descriptor.getContentDescriptor());
										DateUtils.setDateFromToDefault(startDate, endDate, descriptor.getContentDescriptor(), p.getYearFrom(), p.getMonthFrom(), p.getDayFrom(), p.getYearTo(), p.getMonthTo(), p.getDayTo());
										DateUtils.setDateLastUpdateDefault(lu.getYears(), lu.getMonths(), lu.getDays(), descriptor.getContentDescriptor(), p.getYearFrom().getItemText(p.getYearFrom().getSelectedIndex()), p.getYearTo().getItemText(p.getYearTo().getSelectedIndex()));
									}
													
//									if ((counter.getCount()+1)==chartWizard.getSelectData().getListCheckBoxDataset().size()){
										ChartWizardController.selectAllItemsToListBox(chartWizard.getSelectData().getDimensionXValues());
											if (goChart(chartWizard)){
												chartWizard.getSelectData().getOk().setEnabled(true);
												chartWizard.getFormatChart().getPreview().setEnabled(true);
											}
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
				
				
						
									
					
				
					
					//chartWizard.getSelectData().getContYAxis().setExpanded(true);
					
					List<String> idDataset = new ArrayList<String>();
					for (int k = 0;  k < chartWizard.getSelectData().getPanelYAxis().getItemCount(); k++){
						if (!chartWizard.getSelectData().getDimensionY().get(chartWizard.getSelectData().getPanelYAxis().getItem(k).getItemId()).getValue(chartWizard.getSelectData().getDimensionY().get(chartWizard.getSelectData().getPanelYAxis().getItem(k).getItemId()).getSelectedIndex()).equals(" ")){
							idDataset.add(chartWizard.getSelectData().getPanelYAxis().getItem(k).getItemId());
						}
					}
					
					if (idDataset.size()>0){
						ChartWizardController.fillOtherDimensionXEvent(idDataset, chartWizard);
					}
								
			}
		
		};
	}
	
	private static void fillOtherDimensionXEvent(final List<String> idDataset, final ChartWizard chartWizard){
	
		for (final String id : idDataset){
				
			chartWizard.getSelectData().getOtherDimensions().get(id).clear();
			ListBox dimensionY = chartWizard.getSelectData().getDimensionY().get(id);
			List<String> otherDim = new ArrayList<String>();
			for (int j=0; j < dimensionY.getItemCount(); j++){
				if (!dimensionY.getItemText(j).toLowerCase().equals(dimensionY.getItemText(dimensionY.getSelectedIndex()).toLowerCase()) && ! dimensionY.getItemText(j).toLowerCase().equals(chartWizard.getSelectData().getDimensionX().getItemText(chartWizard.getSelectData().getDimensionX().getSelectedIndex()).toLowerCase())){
					otherDim.add(dimensionY.getItemText(j));
				}
			}
			
			final List<String> otherDimWrapper = otherDim;
			
				//BirtServiceEntry.getInstance().getColumnsValues(Long.valueOf(id), otherDimWrapper, new AsyncCallback<List<List<List<String>>>>(){
				TableServiceEntry.getInstance().getColumnsValues(Long.valueOf(id), otherDimWrapper, CheckLanguage.getLanguage() , new AsyncCallback<List<List<List<String>>>>(){
					
				public void onSuccess(List<List<List<String>>> values){
					
					List<ListBox> listListBox = new ArrayList<ListBox>();
					for (int numDim=0; numDim < otherDimWrapper.size(); numDim++){
						listListBox.add(new ListBox());
					}
					
					int count=0;
					for (List<List<String>> l1: values){
//						final List<String> listOfDimension = new ArrayList<String>();	
//						final Map<String, String> mapOfDimension = new HashMap<String, String>();
						if (l1.size() == 0) listListBox.get(count).addItem("null", "");
						for (int k=0; k < l1.size(); k++){
							//listOfDimension.add(l1.get(k).get(1));
							//I assume that the label are unique DANGER
							//mapOfDimension.put(l1.get(k).get(1), l1.get(k).get(0));
							listListBox.get(count).addItem(l1.get(k).get(1), l1.get(k).get(0));
						}
						
//						Collections.sort(listOfDimension);
//						for (String element :  listOfDimension){
//							listListBox.get(count).addItem(element, mapOfDimension.get(element));
//						}
						
						count++;
					}
					
					for(int i=0; i < listListBox.size(); i++){
						HorizontalPanel hpTmp = new HorizontalPanel();
						hpTmp.setSpacing(5);
						hpTmp.insert(listListBox.get(i), 0);
						HTML t = new HTML(otherDimWrapper.get(i));
						t.setStyleName("simpleText");
						hpTmp.insert(t, 0);
						chartWizard.getSelectData().getOtherDimensions().get(id).insert(hpTmp, chartWizard.getSelectData().getOtherDimensions().get(id).getWidgetCount());
						
						if ((i+1) == listListBox.size()){
							HorizontalPanel hp = new HorizontalPanel();
							hp.setSpacing(5);
							HTML title = new HTML(BabelFish.print().otherDim() + ": ");
							title.setStyleName("simpleText");
							hp.add(title);
							chartWizard.getSelectData().getOtherDimensions().get(id).insert(hp, 0);
						}
					}
					
					
				}
				
				public void onFailure(Throwable caught){
					Info.display("getColumnsValues", caught.getLocalizedMessage());
				}
		});
		
	  }
		
	}
	
	public static void fillOtherDimensionYEvent(final String datasetID, final ListBox dimensionSel, final ListBox dimensionNoSel, final VerticalPanel otherDimensions, List<String> otherDim){
		if (!dimensionSel.getValue(dimensionSel.getSelectedIndex()).equals(" ") && !dimensionNoSel.getValue(dimensionNoSel.getSelectedIndex()).equals(" ")){
			otherDimensions.clear();
						
			final List<String> otherDimWrapper = otherDim;
			final long dsId = Long.valueOf(datasetID).longValue();
			
			final LoadingWindow loadingWindow = new LoadingWindow("Dimensions ...", BabelFish.print().pleaseWait(), BabelFish.print().loading());
			loadingWindow.showLoadingBox();
			
			//BirtServiceEntry.getInstance().getColumnsValues(dsId, otherDimWrapper, new AsyncCallback<List<List<List<String>>>>(){
			TableServiceEntry.getInstance().getColumnsValues(dsId, otherDimWrapper, CheckLanguage.getLanguage() , new AsyncCallback<List<List<List<String>>>>(){
				
				public void onSuccess(List<List<List<String>>> values){
					
					List<ListBox> listListBox = new ArrayList<ListBox>();
					for (int numDim=0; numDim < otherDimWrapper.size(); numDim++){
						listListBox.add(new ListBox());
					}
					
					int count=0;
					for (List<List<String>> l1: values){
//						final List<String> listOfDimension = new ArrayList<String>();	
//						final Map<String, String> mapOfDimension = new HashMap<String, String>();
						if (l1.size() == 0) listListBox.get(count).addItem("null", "");
						for (int k=0; k < l1.size(); k++){
							//listOfDimension.add(l1.get(k).get(1));
							//I assume that the label are unique DANGER
							//mapOfDimension.put(l1.get(k).get(1), l1.get(k).get(0));
							//listListBox.get(count).addItem(l1.get(k).get(1), l1.get(k).get(0));
							listListBox.get(count).addItem(l1.get(k).get(1), l1.get(k).get(0));
						}
						
//						Collections.sort(listOfDimension);
//						for (String element :  listOfDimension){
//							listListBox.get(count).addItem(element, mapOfDimension.get(element));
//						}
						count++;
					}
					
					for(int i=0; i < listListBox.size(); i++){
						HorizontalPanel hpTmp = new HorizontalPanel();
						hpTmp.setSpacing(5);
						hpTmp.insert(listListBox.get(i), 0);
						HTML t = new HTML(otherDimWrapper.get(i));
						t.setStyleName("simpleText");
						hpTmp.insert(t, 0);
						otherDimensions.insert(hpTmp, otherDimensions.getWidgetCount());
						hpTmp.add(new CheckBox());
						
						if ((i+1) == listListBox.size()){
							HorizontalPanel hp = new HorizontalPanel();
							hp.setSpacing(5);
							HTML title = new HTML(BabelFish.print().otherDim() + ": ");
							title.setStyleName("simpleText");
							hp.add(title);
							otherDimensions.insert(hp, 0);
						}
					}
					
					loadingWindow.destroyLoadingBox();
					
				}
				
				public void onFailure(Throwable caught){
					Info.display("getColumnsValues", caught.getLocalizedMessage());
					loadingWindow.destroyLoadingBox();
				}
				
			});
			
		}
	}
	
	public static ChangeListener fillColumnValues(final ChartWizard chartWizard, final String datasetID, final ListBox dimensionSel, final ListBox dimensionNoSel, final ListBox dimensionValue, final VerticalPanel otherDimensions){
		return new ChangeListener(){
			
			public void onChange(Widget widget){
				
//	
				final long dsId = Long.valueOf(datasetID).longValue();
				String columnName = dimensionSel.getValue(dimensionSel.getSelectedIndex());
				TableServiceEntry.getInstance().getDimensionValues(dsId, columnName, CheckLanguage.getLanguage(), new AsyncCallback<Map<String, String>>() {
				
					public void onSuccess(Map<String, String> values){
						dimensionValue.clear();
						if (chartWizard.getChartWizardBean().getChartType().equals("BarLine")){
							chartWizard.getSelectData().getDimensionYValuesBar().get(datasetID).clear();
							chartWizard.getSelectData().getDimensionYValuesLine().get(datasetID).clear();
						}
						

						Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
						for (int i = 0; i < values.size(); i++) {
							Map.Entry<String, String> entry = iterator.next();
							dimensionValue.addItem(entry.getValue(), entry.getKey());
						}
						
//						
						ChartWizardController.selectAllItemsToListBox(dimensionValue);
						if (goChart(chartWizard)){
							chartWizard.getSelectData().getOk().setEnabled(true);
							chartWizard.getFormatChart().getPreview().setEnabled(true);
						}
						
						
						if (dimensionSel.getValue(0).equals(" "))
							dimensionSel.removeItem(0);
						
						
						BirtServiceEntry.getInstance().isFlexDataset(Long.valueOf(datasetID), new AsyncCallback<Boolean>(){
							
							public void onSuccess(Boolean flexDataset){
								
								List<String> otherDim = new ArrayList<String>();
								for (int j=0; j < dimensionSel.getItemCount(); j++){
									if (!dimensionSel.getItemText(j).toLowerCase().equals(dimensionSel.getItemText(dimensionSel.getSelectedIndex()).toLowerCase()) && !dimensionSel.getItemText(j).toLowerCase().equals(dimensionNoSel.getItemText(dimensionNoSel.getSelectedIndex()).toLowerCase())){
										System.out.println("dimensionSel.getItemText(j): " + dimensionSel.getItemText(j));
										otherDim.add(dimensionSel.getItemText(j));
									}
								}
								
								if (flexDataset){
									new QuantityDimension(Long.valueOf(datasetID), dimensionSel, dimensionNoSel, otherDimensions, otherDim); 
									
								} else {
									ChartWizardController.fillOtherDimensionYEvent(datasetID, dimensionSel, dimensionNoSel, otherDimensions, otherDim);
								}
								
							}
							
							public void onFailure(Throwable caught){
								Info.display("isFlexDataset", caught.getLocalizedMessage());
							}
							
						});
						
						
						
															
					}
					
					public void onFailure(Throwable caught){
						Info.display("getColumnValues", caught.getLocalizedMessage());
					}
					
				});
				
			}
		
		};
	}
	
	public static SelectionListener<ButtonEvent> chartPreview(final ChartWizard chartWizard){
		return new SelectionListener<ButtonEvent>(){
			@SuppressWarnings("deprecation")
			public void componentSelected(ButtonEvent ce){
				
				//build ChartWizardBean
				chartWizard.getChartWizardBean().setTitle(chartWizard.getFormatChart().getChartTitleBox().getText());
				chartWizard.getChartWizardBean().setWidth(chartWizard.getFormatChart().getWidthChart().getText());
				chartWizard.getChartWizardBean().setHeight(chartWizard.getFormatChart().getHeightChart().getText());
				chartWizard.getChartWizardBean().setXAxisTitle(chartWizard.getFormatChart().getXAxisTitle().getText());
				chartWizard.getChartWizardBean().setXAxisShowLabels(chartWizard.getFormatChart().getShowXLabels().isChecked());
				chartWizard.getChartWizardBean().setRotateXLabels(chartWizard.getFormatChart().getRotateXLabels().getText());
				chartWizard.getChartWizardBean().setYAxisTitle(chartWizard.getFormatChart().getYAxisTitle().getText());
				chartWizard.getChartWizardBean().setYAxisShowLabels(chartWizard.getFormatChart().getShowYLabels().isChecked());
								
				chartWizard.getChartWizardBean().setFlip(chartWizard.getFormatChart().getFlip().isChecked());
				
				chartWizard.getChartWizardBean().setShowLegend(chartWizard.getFormatChart().getShowLegend().isChecked());
				if (chartWizard.getFormatChart().getShowLegend().isChecked()){
					chartWizard.getChartWizardBean().setPosLegend(chartWizard.getFormatChart().getListPos().getItemText(chartWizard.getFormatChart().getListPos().getSelectedIndex()));
				} else{
					chartWizard.getChartWizardBean().setPosLegend("Invisible");
				}
				
				chartWizard.getChartWizardBean().setDim2_3D(chartWizard.getFormatChart().getDim().getValue(chartWizard.getFormatChart().getDim().getSelectedIndex()));
				
				chartWizard.getChartWizardBean().setDisposition(chartWizard.getFormatChart().getDisposition().getItemText(chartWizard.getFormatChart().getDisposition().getSelectedIndex()));
				
				
				chartWizard.getChartWizardBean().setAggregation("SUM");
				
				//datasets
				List<List<String>> datasets = new ArrayList<List<String>>();
				List<String> listTemp;
				for (int i=0; i<chartWizard.getSelectData().getListCheckBoxDataset().size(); i++){
					if (chartWizard.getSelectData().getListCheckBoxDataset().get(i).getValue()){
						listTemp = new ArrayList<String>();
						listTemp.add(chartWizard.getSelectData().getMapDatasetList().get(chartWizard.getSelectData().getListCheckBoxDataset().get(i)).get(0));
						listTemp.add(chartWizard.getSelectData().getMapDatasetList().get(chartWizard.getSelectData().getListCheckBoxDataset().get(i)).get(1));
						datasets.add(listTemp);
					}
				}
				chartWizard.getChartWizardBean().setDatasetId(datasets);
				
				//Dimension X
				chartWizard.getChartWizardBean().setDimensionX(chartWizard.getSelectData().getDimensionX().getItemText(chartWizard.getSelectData().getDimensionX().getSelectedIndex()));
				
				List<List<String>> dimX = new ArrayList<List<String>>();
				List<String> element;
				
				chartWizard.getChartWizardBean().setMostRecent(false);
				/*** If from date to date is enabled **/
				if ( chartWizard.getSelectData().getDateFromToPanel().getCheckBox().getValue()) {
					DateFromToPanel p = chartWizard.getSelectData().getDateFromToPanel();
					
					Date fromDate = DateUtils.setFromDate(p);
					Date toDate = DateUtils.setToDate(p);
					/** get all dates **/
			
					dimX = DateUtils.getDates(fromDate, toDate, chartWizard.getSelectData().getDimensionXValues());			
				}
				/*** if most recent is enabled **/
				else if ( chartWizard.getSelectData().getDateLastUpdatePanel().getCheckBox().getValue()){
//					System.out.println("chartWizard.getSelectData().getDateLastUpdatePanel().getCheckBox().getValue()");
					/** TODO: that one is used in ascending order **/
//					String latestDateString = chartWizard.getSelectData().getDimensionXValues().getValue(chartWizard.getSelectData().getDimensionXValues().getItemCount() -1 );
					/** TODO: this one is used in descending order **/
					String latestDateString = chartWizard.getSelectData().getDimensionXValues().getValue(0);
					
					
					Date latestDate = FieldParser.parseDate(latestDateString);
//					System.out.println("LATEST DATE:"  + latestDate);
					ListBox years = chartWizard.getSelectData().getDateLastUpdatePanel().getYears();
					ListBox months = chartWizard.getSelectData().getDateLastUpdatePanel().getMonths();
					ListBox days = chartWizard.getSelectData().getDateLastUpdatePanel().getDays();				
					Date fromDate = DateUtils.getLastModifiedDate(years, months, days, latestDate, chartWizard.getSelectData().getDateLastUpdatePanel().getPeriodType());
					dimX = DateUtils.getDates(fromDate, latestDate, chartWizard.getSelectData().getDimensionXValues());
					chartWizard.getChartWizardBean().setMostRecent(true);
					chartWizard.getChartWizardBean().setMostRecent(DateUtils.getLastModifiedDate(years, months, days));
//					System.out.println(chartWizard.getChartWizardBean().getMostRecent().getYears());
				}
				else {
//					for(int i=0; i < chartWizard.getSelectData().getDimensionXValues().getItemCount(); i++){
					for (int i = chartWizard.getSelectData().getDimensionXValues().getItemCount() -1; i >=0 ; i--) {
						if (chartWizard.getSelectData().getDimensionXValues().isItemSelected(i)){
							element = new ArrayList<String>();
							element.add(chartWizard.getSelectData().getDimensionXValues().getValue(i));
							element.add(chartWizard.getSelectData().getDimensionXValues().getItemText(i));
							dimX.add(element);
						}
					}
				}
				chartWizard.getChartWizardBean().setDimensionValuesX(dimX);
				
								
				//Dimension Y
				ListBox listY;
				ListBox listYValues;
				ListBox listYBarValues;
				ListBox listYLineValues;
				VerticalPanel contOtherDim;
				List<List<String>> dimY;
				List<List<String>> dimYBarLine;
				List<List<String>> otherDim;
				Map<String, String>  dimensionY = new HashMap<String, String>();
				Map<String, List<List<String>>>  dimensionValuesY = new HashMap<String, List<List<String>>>();
				Map<String, List<List<String>>>  dimYBar = new HashMap<String, List<List<String>>>();
				Map<String, List<List<String>>>  dimYLine = new HashMap<String, List<List<String>>>();
				Map<String, List<List<String>>>  otherDimension = new HashMap<String, List<List<String>>>();
				for (TabItem tab : chartWizard.getSelectData().getPanelYAxis().getItems()){
				 listY = chartWizard.getSelectData().getDimensionY().get(tab.getItemId()); 
				 dimensionY.put(tab.getItemId(), listY.getItemText(listY.getSelectedIndex()));
				
				 
				 if (chartWizard.getChartWizardBean().getChartType().equals("BarLine")){
					 listYBarValues = chartWizard.getSelectData().getDimensionYValuesBar().get(tab.getItemId());
					 dimYBarLine = new ArrayList<List<String>>();
					 for(int i=0; i < listYBarValues.getItemCount(); i++){
							element = new ArrayList<String>();
							element.add(listYBarValues.getValue(i));
							element.add(listYBarValues.getItemText(i));
							dimYBarLine.add(element);
					 }
					 dimYBar.put(tab.getItemId(), dimYBarLine);
					 
					 listYLineValues = chartWizard.getSelectData().getDimensionYValuesLine().get(tab.getItemId());
					 dimYBarLine = new ArrayList<List<String>>();
					 for(int i=0; i < listYLineValues.getItemCount(); i++){
							element = new ArrayList<String>();
							element.add(listYLineValues.getValue(i));
							element.add(listYLineValues.getItemText(i));
							dimYBarLine.add(element);
					 }
					 dimYLine.put(tab.getItemId(), dimYBarLine);
					 
					 
				 } else {
					 listYValues = chartWizard.getSelectData().getDimensionYValues().get(tab.getItemId());
					 dimY = new ArrayList<List<String>>();
					 for(int i=0; i < listYValues.getItemCount(); i++){
						if (listYValues.isItemSelected(i)){
							element = new ArrayList<String>();
							element.add(listYValues.getValue(i));
							element.add(listYValues.getItemText(i));
							dimY.add(element);
						}
					 }
					 dimensionValuesY.put(tab.getItemId(), dimY);
				 }
				 
				 
				 //Other Dimensions
				 contOtherDim = chartWizard.getSelectData().getOtherDimensions().get(tab.getItemId());
				 otherDim = new ArrayList<List<String>>();
				 for (int i=1; i < contOtherDim.getWidgetCount(); i++){
					 	element = new ArrayList<String>();
						element.add(((HTML)((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(0)).getHTML());
						element.add(((ListBox)((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(1)).getValue(((ListBox)((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(1)).getSelectedIndex()));
						element.add( ((CheckBox)((HorizontalPanel) contOtherDim.getWidget(i)).getWidget(2)).getValue() ? "true":"false");
						otherDim.add(element);
				 }
				 
				 otherDimension.put(tab.getItemId(), otherDim);
				 
								 
				} // end loop datasets
				
				chartWizard.getChartWizardBean().setDimensionValuesYBar(dimYBar);
				chartWizard.getChartWizardBean().setDimensionValuesYLine(dimYLine);
				chartWizard.getChartWizardBean().setDimensionY(dimensionY);
				chartWizard.getChartWizardBean().setDimensionValuesY(dimensionValuesY);
				chartWizard.getChartWizardBean().setOtherDimension(otherDimension);
				
				final List<List<String>> spyDsId = new ArrayList<List<String>>();
				for (final List<String> dsID : chartWizard.getChartWizardBean().getDatasetId()){
											 
						 BirtServiceEntry.getInstance().getMUSymbol(Long.valueOf(dsID.get(1)), otherDimension.get(dsID.get(1)) , new AsyncCallback<String>(){
							
							public void onSuccess(String result){
								
								List<String> element = new ArrayList<String>();
								element.add(dsID.get(1));
								if (result != null){
									element.add(result);
								} else {
									element.add("null");
								}
								spyDsId.add(element);
								
								if (spyDsId.size() == chartWizard.getChartWizardBean().getDatasetId().size()){
									
									//check duplicates
									List<String> setDup = new ArrayList<String>();
									for (int i=0; i< spyDsId.size(); i++){
										if (!setDup.contains(spyDsId.get(i).get(1))) setDup.add(spyDsId.get(i).get(1));
									}
									
									if (setDup.size() > 2){
										FenixAlert.alert("Alert", "You can not plot three or more different measurement unit.", "");
									}else{
																				
										Map<String, String> scalesMap = new HashMap<String, String>();
										
										if (setDup.size() == 1){
											chartWizard.getChartWizardBean().setYAxisTitle(chartWizard.getChartWizardBean().getYAxisTitle() + "(" + spyDsId.get(0).get(1) + ")");
										} else {
											chartWizard.getChartWizardBean().setChartType("Line");
											chartWizard.getChartWizardBean().setYAxisTitle(chartWizard.getChartWizardBean().getYAxisTitle() + "(" + spyDsId.get(0).get(1) + ")");
											chartWizard.getChartWizardBean().setYSecondAxisTitle("(" + spyDsId.get(1).get(1) + ")");
										}
										
										
										for (List<String> d : spyDsId){
											if (setDup.size() == 1){
												scalesMap.put(d.get(0), "FirstScale");
												chartWizard.getChartWizardBean().setDoubleScale(false);
											} else {
												chartWizard.getChartWizardBean().setDoubleScale(true);
												if (d.get(1).equals(setDup.get(0))){
													scalesMap.put(d.get(0), "FirstScale");
												} else if (d.get(1).equals(setDup.get(1))){
													scalesMap.put(d.get(0), "SecondScale");
												}
											}
										}
										
										chartWizard.getChartWizardBean().setScalesMap(scalesMap);
										
										final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().creatingChart(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
										loadingWindow.showLoadingBox();
										
										BirtServiceEntry.getInstance().updatePreview(chartWizard.getChartWizardBean(), "preview", "", new AsyncCallback<List<String>>(){
											
											public void onSuccess(List<String> result){
												chartWizard.getWindow().hide();
												new ChartViewer(result, chartWizard.getChartWizardBean());
												loadingWindow.destroyLoadingBox();
											}
											
											public void onFailure(Throwable caught){
												Info.display("updatePreview", caught.getLocalizedMessage());
												loadingWindow.destroyLoadingBox();
											}
											
										});
									}
																
								}
								
							}
								
							public void onFailure(Throwable caught){
									Info.display("isMU", caught.getLocalizedMessage());
									
							}						 
							 
						 });
						 
				}
				
			}
		};
	}
	

}
