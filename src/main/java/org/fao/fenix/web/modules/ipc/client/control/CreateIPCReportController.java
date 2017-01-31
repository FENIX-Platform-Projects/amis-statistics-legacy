package org.fao.fenix.web.modules.ipc.client.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.client.view.old.CreateIPCReport;
import org.fao.fenix.web.modules.ipc.client.view.old.CreateIPCReportViewer;
import org.fao.fenix.web.modules.ipc.client.view.old.CreateIPCWorkflow;
import org.fao.fenix.web.modules.ipc.client.view.reports.IPCReportsPanel;
import org.fao.fenix.web.modules.ipc.client.view.viewer.IPCReportViewBuilder;
import org.fao.fenix.web.modules.ipc.common.services.IPCServiceEntry;
import org.fao.fenix.web.modules.ipc.common.services.IPCXMLServiceEntry;
import org.fao.fenix.web.modules.ipc.common.vo.IPCGridMD;
import org.fao.fenix.web.modules.ipc.common.vo.IPCReportBeanVO;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

public class CreateIPCReportController {

	public static List<ComboBox<ListItem>> fieldList = new ArrayList<ComboBox<ListItem>>();
	
	public static void fillUserCountryList(final ComboBox<ListItem> countryComboBox, final CreateIPCReport window) {
		IPCServiceEntry.getInstance().getUserCountryList(new AsyncCallback<List<CodeVo>>() {

			public void onSuccess(List<CodeVo> result) {
				System.out.println("getUserCountryList countries list size ------------------ " + result.size());

				if(!result.isEmpty()){
            		List<ListItem> countryItems = new ArrayList<ListItem>();

					for (CodeVo vo : result)
						countryItems.add(new ListItem(vo.getLabel(), vo.getCode()));

					sortStore(countryItems);

					ListStore<ListItem> store = new ListStore<ListItem>();
					store.add(countryItems);

					window.countryComboBox.setStore(store);
					
					window.formPanel.add(window.label);
					window.formPanel.add(window.countryComboBox);
					window.formPanel.setButtonAlign(HorizontalAlignment.CENTER);
					window.formPanel.addButton(window.createReport);
				}
				else {
					window.message.setHtml(BabelFish.print().noWorkflowToCreateReportMessage());	
					window.formPanel.add(window.message);
					window.formPanel.addButton(window.createWorkflow);					
				}
				window.panel.add(window.formPanel);
				
			}

			public void onFailure(Throwable caught) {
				Info.display("ShowIPCReportController RPC Failed:", "Service call to {0} failed", "IPCServiceImpl:fillUserCountryList()");
			}
		});
	}
	
	
	public static SelectionListener<ButtonEvent> launchCreateIPCWorkflow(final CreateIPCReport window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				window.window.close();
				new CreateIPCWorkflow().build();
			}
		};
	}
	
	
	
	public  static SelectionChangedListener<ListItem> getWorkflows(final ComboBox<ListItem> countryComboBox, final ComboBox<ListItem> workflowComboBox, final CreateIPCReport window) {
		return new SelectionChangedListener<ListItem>() {
			@Override
		      public void selectionChanged(SelectionChangedEvent<ListItem> se) {
		        System.out.println(se.getSelectedItem().get("value"));
		        
		        fillWorkflows(countryComboBox, workflowComboBox, window);
			};
		};
	}

	
	private static void fillWorkflows(final ComboBox<ListItem> countryComboBox, final ComboBox<ListItem> workflowComboBox, final CreateIPCReport window) {
		String countryComboId = countryComboBox.getId();
		ListItem selectedCountryItem = countryComboBox.getValue();
		
		final String countryLabel = selectedCountryItem.getName();
		final String countryGaulCode = selectedCountryItem.getValue();

		System.out.println("country id = " + countryComboId + " countryLabel = " + countryLabel
				+ " countryGaulCode " + countryGaulCode);
		
		IPCServiceEntry.getInstance().getCountryWorkflowList(countryGaulCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {
				System.out.println("getCountryWorkflowList layer list size ------------------ " + result.size());

				List<ListItem> workflowItems = new ArrayList<ListItem>();

				for (CodeVo vo : result)
					workflowItems.add(new ListItem(vo.getLabel(), vo.getCode()));

				sortStore(workflowItems);
				
				ListStore<ListItem> store = new ListStore<ListItem>();
				store.add(workflowItems);

				window.workflowComboBox.setStore(store);
				fieldList.add(workflowComboBox);

				window.formPanel.add(window.workflowComboBox);
				
				window.panel.add(window.formPanel);
				
				
			}

			public void onFailure(Throwable caught) {
				Info.display("ShowIPCReportController RPC Failed:", "Service call to {0} failed", "IPCServiceImpl:getCountryWorkflowList()");
			}
		});
	}
	

	private static void sortStore(List<ListItem> list) {
		Collections.sort(list, new Comparator<ListItem>() {
			public int compare(ListItem arg0, ListItem arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
	}

	public static SelectionListener<ButtonEvent> createIPCReport(final CreateIPCReport window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				if (window.formPanel.isValid()) {

					ListItem selectedCountryItem = window.countryComboBox.getValue();
					final String selectedCountryLabel = selectedCountryItem.getName();
					
					ListItem selectedWorkflowItem = window.workflowComboBox.getValue();
					final String workflowLabel = selectedWorkflowItem.getName();
					String workflowId = selectedWorkflowItem.getValue();
					String mapIdentifier = "IPC_MAP_"+workflowId;

					System.out.println("workflowLabel = " + workflowLabel + " workflowId "+ workflowId + " mapIdentifier = "+mapIdentifier);

					
			 		final LoadingWindow loadingWindow = new LoadingWindow("Creating Report", BabelFish.print().pleaseWait(), BabelFish.print().loading());
					loadingWindow.showLoadingBox();
				
				 	BirtServiceEntry.getInstance().getIPCTemplate(workflowId, selectedCountryLabel, new AsyncCallback<String>(){
				 		
						
						public void onSuccess(String url){
							System.out.println("CreateIPCReportController: getIPCTemplate: url = "+url);	
							loadingWindow.destroyLoadingBox();
							
							new CreateIPCReportViewer(url, selectedCountryLabel);
							
							window.window.hide();
						}
					
						public void onFailure(Throwable caught) {
							loadingWindow.destroyLoadingBox();
							Info.display("CreateIPCReportController: getIPCTemplate RPC Failed:", "Service call to {0} failed", "BirtServiceImpl:getIPCTemplate()");
						}
						
					});
					
				} else
					FenixAlert.error("Error: Missing Fields", "Required fields are missing!");
			}
		};
	}
	
	
	
	/*** NEW REPORT CONTROLLER ***/
	

	public static SelectionListener<ButtonEvent> createIPCSummaryReport(final IPCReportsPanel window, final Grid<IPCGridMD> grid) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				final IPCGridMD row = (IPCGridMD) grid.getSelectionModel().getSelectedItem();
				final String country = window.getCountry().getItemText(window.getCountry().getSelectedIndex());
					
				IPCXMLServiceEntry.getInstance().getWorkflowProvinces(row.getContributor_id(), row.getWorkflowId(), new AsyncCallback<List<ProvinceVO>>() {
					
					public void onSuccess(List<ProvinceVO> result){
						
							HTML partOneHtml = new HTML(IPCReportViewBuilder.buildSummary(result));
							
								IPCReportBeanVO reportBean = new IPCReportBeanVO();
								reportBean.setModeratorFullName(row.getModeratorFullName());
								reportBean.setContributorFirstName(row.getContributorFirstName());
								reportBean.setContributorId(row.getContributor_id());
								reportBean.setContributorLastName(row.getContributorLastName());
								reportBean.setDescription(row.getDescription());
								reportBean.setPeriod(row.getPeriod());
								reportBean.setModuleOne(partOneHtml.toString());
								reportBean.setStatus(row.getStatus());
								reportBean.setWorkFlowId(row.getWorkflowId());
								reportBean.setWorkId(row.getWorkId());
								reportBean.setTitle(row.getName());
								reportBean.setCountry(country);
								
								
								System.out.println("create report");
								BirtServiceEntry.getInstance().getIPCTemplate(reportBean,  new AsyncCallback<String>(){
									public void onSuccess(String url){
										System.out.println("CreateIPCReportController: getIPCTemplate: url = "+url);	
//										loadingWindow.destroyLoadingBox();
										
										new CreateIPCReportViewer(url, country);
										
//										window.window.hide();
									}
								
									public void onFailure(Throwable caught) {
//										loadingWindow.destroyLoadingBox();
										
									}
									
								});
//								tabOne.add(partOneHtml);
//								tabTwo.add(partTwoHtml);
//								tabThree.add(partThreeHtml);
//								
//								buildCenterPanel();
//								format(windowHeader);
//								show();
							}
							
							public void onFailure(Throwable e) {
								FenixAlert.error("Error", e.getMessage());
							}
							
					
				});
				
				
		
					
				}
				
				

				 	
					
			
		};
	}
	/*public static SelectionListener<ButtonEvent> createIPCReport(final IPCReportsPanel window, final Grid<IPCGridMD> grid) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				final IPCGridMD row = (IPCGridMD) grid.getSelectionModel().getSelectedItem();
				final String country = window.getCountry().getItemText(window.getCountry().getSelectedIndex());
				
				
		
					IPCServiceEntry.getInstance().getXml(row.getWorkId(), new AsyncCallback<String>() {
						 public void onSuccess(String xml){
							IPCXMLServiceEntry.getInstance().getAllModules(xml, false, new AsyncCallback<List<ModuleVO>>() {
								
								public void onSuccess(List<ModuleVO> modules) {
									
									ModuleVO moduleOne = modules.get(0);
									ModuleVO moduleTwo = modules.get(1);
									ModuleVO moduleThree = modules.get(2);
									ModuleVO moduleFour = modules.get(3);
									
									HTML partOneHtml = new HTML(IPCViewBuilder.buildTabOne(moduleOne, moduleTwo));
									HTML partTwoHtml = new HTML(IPCViewBuilder.buildTabTwo(moduleThree));
									HTML partThreeHtml = new HTML(IPCViewBuilder.buildTabThree(moduleFour));
								
									IPCReportBeanVO reportBean = new IPCReportBeanVO();
									reportBean.setContributorFirstName(row.getContributorFirstName());
									reportBean.setContributorId(row.getContributor_id());
									reportBean.setContributorLastName(row.getContributorLastName());
									reportBean.setDescription(row.getDescription());
									reportBean.setPeriod(row.getPeriod());
									reportBean.setModuleOne(partOneHtml.toString());
									reportBean.setModuleTwo(partTwoHtml.toString());
									reportBean.setModuleThree(partThreeHtml.toString());
									reportBean.setStatus(row.getStatus());
									reportBean.setWorkFlowId(row.getWorkflowId());
									reportBean.setWorkId(row.getWorkId());
									reportBean.setTitle(row.getName());
									reportBean.setCountry(country);
									
									
									
									BirtServiceEntry.getInstance().getIPCTemplate(reportBean,  new AsyncCallback<String>(){
										public void onSuccess(String url){
											System.out.println("CreateIPCReportController: getIPCTemplate: url = "+url);	
//											loadingWindow.destroyLoadingBox();
											
											new CreateIPCReportViewer(url, country);
											
//											window.window.hide();
										}
									
										public void onFailure(Throwable caught) {
//											loadingWindow.destroyLoadingBox();
											
										}
										
									});
//									tabOne.add(partOneHtml);
//									tabTwo.add(partTwoHtml);
//									tabThree.add(partThreeHtml);
//									
//									buildCenterPanel();
//									format(windowHeader);
//									show();
								}
								
								public void onFailure(Throwable e) {
									FenixAlert.error("Error", e.getMessage());
								}
								
							});
						}
					
						public void onFailure(Throwable caught) {
							
						}
					});
				}
				
				

				 	
					
			
		};
	}*/
	

}