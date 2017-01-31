package org.fao.fenix.web.modules.ipc.client.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;
import org.fao.fenix.web.modules.ipc.client.view.ConfirmWindow;
import org.fao.fenix.web.modules.ipc.client.view.CreateIPCWorkflowTab;
import org.fao.fenix.web.modules.ipc.client.view.IPCWorkflowWindow;
import org.fao.fenix.web.modules.ipc.client.view.IPCaddActorsWindow;
import org.fao.fenix.web.modules.ipc.client.view.ShowEditIPCWorkflow;
import org.fao.fenix.web.modules.ipc.client.view.ShowIPCWorkflowTab;
import org.fao.fenix.web.modules.ipc.client.view.ViewerIPCWorkflowTab;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.IPCDataEntryControllerWindow;
import org.fao.fenix.web.modules.ipc.client.view.old.CreateIPCWorkflow;
import org.fao.fenix.web.modules.ipc.client.view.old.CreateIPCWorkflowViewer;
import org.fao.fenix.web.modules.ipc.client.view.reports.IPCReportsPanel;
import org.fao.fenix.web.modules.ipc.client.view.viewer.IPCViewerWindow;
import org.fao.fenix.web.modules.ipc.common.services.IPCServiceEntry;
import org.fao.fenix.web.modules.ipc.common.vo.IPCCodesVO;
import org.fao.fenix.web.modules.ipc.common.vo.IPCGridMD;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.UserVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class CreateIPCWorkflowController {

	public static List<ComboBox<ListItem>> fieldList = new ArrayList<ComboBox<ListItem>>();
	
	public static void fillPageWidgets(final CreateIPCWorkflow window) {
		IPCServiceEntry.getInstance().getCountryList(new AsyncCallback<List<CodeVo>>() {

			public void onSuccess(List<CodeVo> result) {
				//System.out.println("getCountryList countries list size ------------------ " + result.size());

				List<ListItem> countryItems = new ArrayList<ListItem>();

				for (CodeVo vo : result)
					countryItems.add(new ListItem(vo.getLabel(), vo.getCode()));

				sortStore(countryItems);

				ListStore<ListItem> store = new ListStore<ListItem>();
				store.add(countryItems);

//				window.countryComboBox.setStore(store);
				window.countryComboBox.getStore().add((List<? extends ListItem>) store);
				fieldList.add(window.countryComboBox);

				window.formPanel.add(window.countryComboBox);

				IPCServiceEntry.getInstance().getLayerList(new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> result) {
						//System.out.println("getLayerList layer list size ------------------ " + result.size());

						List<ListItem> layerItems = new ArrayList<ListItem>();

						for (int i = 0; i < result.size(); i++) {
							String gaulLevel = result.get(i);
							String gaulLabel = BabelFish.print().getString(gaulLevel);
							if (gaulLabel == null) {
								gaulLabel = gaulLevel;
							}
							layerItems.add(new ListItem(gaulLabel, gaulLevel));

						}

						sortStore(layerItems);

						ListStore<ListItem> store = new ListStore<ListItem>();
						store.add(layerItems);

						window.layerComboBox.setStore(store);
						
						window.formPanel.add(window.layerComboBox);

						fieldList.add(window.layerComboBox);

						window.openIPC.addSelectionListener(openIPC(window));

						window.formPanel.setButtonAlign(HorizontalAlignment.CENTER);

						window.formPanel.addButton(window.openIPC);

						window.panel.add(window.formPanel);

					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getLayerList()");
					}
				});
				
				
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getCountryList()");
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

	public static SelectionListener<ButtonEvent> openIPC(final CreateIPCWorkflow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				
				if (window.formPanel.isValid()) {

					String countryComboId = window.countryComboBox.getId();
					ListItem selectedCountryItem = window.countryComboBox.getValue();
					final String countryLabel = selectedCountryItem.getName();
					final String countryGaulCode = selectedCountryItem.getValue();

					System.out.println("country id = " + countryComboId + " countryLabel = " + countryLabel
							+ " countryGaulCode " + countryGaulCode);

					ListItem selectedLayerItem = window.layerComboBox.getValue();
					String layerComboId = window.layerComboBox.getId();
					final String layerLabel = selectedLayerItem.getName();
					String layerCode = selectedLayerItem.getValue();

					System.out.println("layer id = " + layerComboId + " layerLabel = " + layerLabel + " layerCode "
							+ layerCode);

					IPCServiceEntry.getInstance().openIPCCreateWorkflow(layerCode, layerLabel, countryGaulCode, countryLabel, new AsyncCallback<String>(){
						public void onSuccess(String url){
							System.out.println("openIPCCreateWorkflow: url = "+url);	
							//create the iframe
							String iFrame = "<iframe src='" +url + "' width='100%' height='100%' />";
							new CreateIPCWorkflowViewer(iFrame, countryLabel, layerLabel).build();
							
							window.window.hide();
						}
					
						public void onFailure(Throwable caught) {
							Info.display("CreateIPCWorkflowController RPC Failed:", "Service call to {0} failed", "openIPCCreateWorkflow()");
						}
						
					});
					
				} else
					FenixAlert.error("Error: Missing Fields", "Required fields are missing!");
			}
		};
	}
	
	
	/*********************************************************************************
	 * 
	 * NEW IPC CONTROLLER
	 * 
	 *******************************************************************************/
	
	
	public static void fillPageWidgets(final CreateIPCWorkflowTab window) {
		IPCServiceEntry.getInstance().getCountryList(new AsyncCallback<List<CodeVo>>() {

			public void onSuccess(List<CodeVo> result) {
				//System.out.println("getCountryList countries list size ------------------ " + result.size());

				List<ListItem> countryItems = new ArrayList<ListItem>();

				for (CodeVo vo : result)
					countryItems.add(new ListItem(vo.getLabel(), vo.getCode()));

				sortStore(countryItems);

				window.countryComboBox.getStore().add(countryItems);
				fieldList.add(window.countryComboBox);

				IPCServiceEntry.getInstance().getLayerList(new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> result) {
						//System.out.println("getLayerList layer list size ------------------ " + result.size());

						List<ListItem> layerItems = new ArrayList<ListItem>();

						for (int i = 0; i < result.size(); i++) {
							String gaulLevel = result.get(i);
							String gaulLabel = BabelFish.print().getString(gaulLevel);
							if (gaulLabel == null) {
								gaulLabel = gaulLevel;
							}
							layerItems.add(new ListItem(gaulLabel, gaulLevel));

						}

						sortStore(layerItems);

						
						window.layerComboBox.getStore().add(layerItems);


						fieldList.add(window.layerComboBox);

					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getLayerList()");
					}
				});
				
				
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getCountryList()");
			}
		});
	}
	
	
	
	public static void fillAreasValues(final CreateIPCWorkflowTab window) {
		ListItem selectedcItem = window.countryComboBox.getValue();
		final String countryGaulCode = selectedcItem.getValue();
		ListItem selectedlItem = window.layerComboBox.getValue();
		final String layerCode= selectedlItem.getValue();
		if ( !layerCode.equals("GAUL0")) {
			IPCServiceEntry.getInstance().getAreasCodes(countryGaulCode, layerCode, new AsyncCallback<List<CodeVo>>() {
				public void onSuccess(List<CodeVo> result) {
					ListBox areas = window.areasValues;
					ListBox selectedAreas = window.selectedAreasValues;			
					areas.clear();
					selectedAreas.clear();
					for(CodeVo code : result) 
						areas.addItem(code.getLabel(),code.getCode());
				}
	
				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getLayerList()");
				}
			});		
		}
		else {
			ListBox areas = window.areasValues;
			ListBox selectedAreas = window.selectedAreasValues;
			areas.clear();
			selectedAreas.clear();
			areas.addItem(selectedcItem.getName(), selectedcItem.getValue());
		}
	}
	
	public static void fillAreasValues(final ShowEditIPCWorkflow window) {
		final String countryGaulCode = window.getWorkflowInfoVO().getGeographicArea().getCode();
		final String layerCode= window.getWorkflowInfoVO().getReferenceLayer().getCode();
		if ( !layerCode.equals("GAUL0")) {
			IPCServiceEntry.getInstance().getAreasCodes(countryGaulCode, layerCode, new AsyncCallback<List<CodeVo>>() {
				public void onSuccess(List<CodeVo> result) {
					ListBox areas = window.areasValues;
					ListBox selectedAreas = window.selectedAreasValues;
					areas.clear();
					selectedAreas.clear();
					for(CodeVo code : result) 
						areas.addItem(code.getLabel(),code.getCode());
//					for(IPCCodesVO province : window.getWorkflowInfoVO().getInterestedAreas())
//						selectedAreas.addItem(province.getLabel(), province.getCode());
					for(ProvinceVO province : window.getWorkflowInfoVO().getProvinces())
					selectedAreas.addItem(province.getProvinceLabel(), province.getProvinceCode());
				}
	
				public void onFailure(Throwable caught) {
					FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getLayerList()");
				}
			});		
		}
		else {
			ListBox areas = window.areasValues;
			ListBox selectedAreas = window.selectedAreasValues;
			areas.clear();
			selectedAreas.clear();
			areas.addItem(window.getWorkflowInfoVO().getGeographicArea().getLabel(), window.getWorkflowInfoVO().getGeographicArea().getCode());
		}
	}
	
	public static  SelectionListener<ButtonEvent> addAll(final ListBox areasValues, final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				selectedValues.clear();
				for(int i=0; i < areasValues.getItemCount(); i++){
					selectedValues.addItem(areasValues.getItemText(i), areasValues.getValue(i));
					selectedValues.setItemSelected(i, true);
				}
				
			}
		};
	}
	
	public static  SelectionListener<ButtonEvent> addSelectedValues(final ListBox areasValues, final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				
				for(int i=0; i < areasValues.getItemCount(); i++){
					if(areasValues.isItemSelected(i)){
						/** check if it's not already selected **/
						if ( selectedValues.getItemCount() != 0 ) {
							Boolean check = false;
							for(int j=0; j < selectedValues.getItemCount(); j++){
								if(selectedValues.getItemText(j).equals(areasValues.getItemText(i))){
									check = true;
									break;									
								}	
							}
							if ( !check ) 
								 selectedValues.addItem(areasValues.getItemText(i), areasValues.getValue(i));
						}
						else 
							selectedValues.addItem(areasValues.getItemText(i), areasValues.getValue(i));
					}
				}
				// SELECTED ALL VALUES???
//				for(int i=0; i < selectedValues.getItemCount(); i++)
//					selectedValues.setItemSelected(i, true);

			}
		};
	}
	

	public static SelectionListener<ButtonEvent> removeAll(final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				selectedValues.clear();	
			}
		};
	}


	public static  SelectionListener<ButtonEvent> removeSelected(final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				
				ListBox backup = new ListBox();
				for(int i=0; i < selectedValues.getItemCount(); i++){
					if(!selectedValues.isItemSelected(i)){
						backup.addItem(selectedValues.getItemText(i), selectedValues.getValue(i));
					}
				}
				selectedValues.clear();
				
				for(int i=0; i < backup.getItemCount(); i++){
					selectedValues.addItem(backup.getItemText(i), backup.getValue(i));		
				}
			}
		};
	}
	
	public static void getModerator(final CreateIPCWorkflowTab w){
		IPCServiceEntry.getInstance().getCurrentUserInfo(new AsyncCallback<UserVO>() {
			public void onSuccess(UserVO user) {
				System.out.println(user.getId() + " | " + user.getFirstName() + " | " + user.getFirstName());
				w.setModerator(user);
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failure", "getUserINfo");
			}
		
		});
	}
	
	
	public static  SelectionListener<ButtonEvent> openIPCController(final IPCWorkflowWindow window, final CreateIPCWorkflowTab w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().creatingNewWorkflow(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
				IPCServiceEntry.getInstance().getCurrentUserInfo(new AsyncCallback<UserVO>() {
					public void onSuccess(UserVO user) {
						
						final WorkflowInfoVO workflowInfo = createWorkflowInfo(w);
						workflowInfo.setModerator_id(user.getId());
						workflowInfo.setModeratorFirstName(user.getFirstName());
						workflowInfo.setModeratorLastName(user.getLastName());
						System.out.println(user.getId() + " | " + user.getFirstName() + " | " + user.getFirstName());
						if (!workflowInfo.getProvinces().isEmpty()) {							
							IPCServiceEntry.getInstance().saveWorkflow(workflowInfo, new AsyncCallback<Long>() {
								public void onSuccess(Long workflowId) {								
									IPCDataEntryControllerWindow controller = new IPCDataEntryControllerWindow();
									workflowInfo.setWorkflowId(workflowId);
									controller.build(workflowInfo, true, true);
									loadingWindow.destroyLoadingBox();
									window.getWindow().close();
								}
					
								public void onFailure(Throwable caught) {
									loadingWindow.destroyLoadingBox();
									FenixAlert.alert("RPC Failure", "Failed Workflow Save on DB");
								}
							});
						}
						else
							FenixAlert.alert(BabelFish.print().openingWorkflow(), BabelFish.print().noSelectedProvinces());
							
					}
		
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failure", "getUserINfo");
					}
				
				});

			}
		};
	}
	
	public static  SelectionListener<ButtonEvent> openAddActors(final CreateIPCWorkflowTab w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				LoadingWindow loadingWindow = new LoadingWindow("Retrieving IPC Users", BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
				IPCaddActorsWindow addActorsWindow = new IPCaddActorsWindow();
				addActorsWindow.build(w, w.getModerator().getId(), w.getContributors(), w.getObservers());
				loadingWindow.destroyLoadingBox();
			}
		};
	}
	
	public static  SelectionListener<ButtonEvent> openAddActors(final ShowEditIPCWorkflow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				final LoadingWindow loadingWindow = new LoadingWindow("Retrieving IPC Users", BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
				final IPCaddActorsWindow addActorsWindow = new IPCaddActorsWindow();
				IPCServiceEntry.getInstance().getCurrentUserInfo(new AsyncCallback<UserVO>() {
					public void onSuccess(UserVO currentUser) {
						w.setModerator(currentUser);
						for(UserVO user : w.getContributors()) {
							if(user.getId() == currentUser.getId()){
								w.getContributors().remove(user);
								break;
							}
						}
						for(UserVO user : w.getObservers()) {
							if(user.getId() == currentUser.getId()){
								w.getObservers().remove(user);
								break;
							}
						}
								
						w.getContributors().remove(currentUser);
						w.getObservers().remove(currentUser);
						addActorsWindow.build(w, currentUser.getId(), w.getContributors(), w.getObservers());
						loadingWindow.destroyLoadingBox();
					}
		
					public void onFailure(Throwable caught) {
						loadingWindow.destroyLoadingBox();
					}
				});
				
			}
		};
	}

	

	

	public static void getUsersInIPCGroup(final Long moderator_id, final ListBox contributors, final ListBox observers){

		UserServiceEntry.getInstance().getUsersInGroup("IPC-group", new AsyncCallback<List<FenixUserVo>>() {
		
			public void onSuccess(List<FenixUserVo> result) {
				List<CodeVo> name = new ArrayList<CodeVo>();
				for (FenixUserVo user : result) {
					CodeVo n = new CodeVo();
					n.setCode(user.getFirstName());
					n.setLabel(user.getLastName());
					name.add(n);
				}
		
				IPCServiceEntry.getInstance().getUsers(name, new AsyncCallback<List<UserVO>>() {
					public void onSuccess(List<UserVO> result) {
						for (UserVO user : result) {
							if ( user.getId() != moderator_id) {
								System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getId());
								contributors.addItem(user.getFirstName() + " " + user.getLastName(), String.valueOf(user.getId()));
								observers.addItem(user.getFirstName() + " " + user.getLastName(), String.valueOf(user.getId()));
							}
						}
						
						

					}

					public void onFailure(Throwable caught) {
						Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
					}
				});
				
				
				

			}

			public void onFailure(Throwable caught) {
				Info.display(BabelFish.print().generalServerProblem(), caught.getLocalizedMessage());
			}
		});
	}

	
	

	
	
	
	public static void fillCountryBox(final IPCReportsPanel window) {
		IPCServiceEntry.getInstance().getCountryList(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {
				ListBox country = window.getCountry();
				country.addItem("Please select a Country", "0");
				for (CodeVo vo : result)
					country.addItem(vo.getLabel(), vo.getCode());	
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getCountryList()");
			}
		});
	}
	
	
	public static void fillCountryBox(final ShowIPCWorkflowTab window) {
		IPCServiceEntry.getInstance().getCountryList(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {
				ListBox country = window.getCountry();
				country.addItem("Please select a Country", "0");
				for (CodeVo vo : result)
					country.addItem(vo.getLabel(), vo.getCode());	
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getCountryList()");
			}
		});
	}
	
	public static void fillCountryBox(final ViewerIPCWorkflowTab window) {
		IPCServiceEntry.getInstance().getCountryList(new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> result) {
				ListBox country = window.getCountry();
				country.addItem("Please select a Country", "0");
				for (CodeVo vo : result)
					country.addItem(vo.getLabel(), vo.getCode());	
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failure", "CreateIPCWorkflowController: getCountryList()");
			}
		});
	}
	
	public static void fillLoadGrid(final ShowIPCWorkflowTab window) {
//		ListItem selectedcItem = window.countryComboBox.getValue();
		
		final String countryCode = window.getCountry().getValue(window.getCountry().getSelectedIndex());
		final String countryLabel = window.getCountry().getItemText(window.getCountry().getSelectedIndex());
		System.out.println("Country: " + countryLabel + " | " + countryCode);
		
		IPCServiceEntry.getInstance().getCurrentUserInfo(new AsyncCallback<UserVO>() {
			public void onSuccess(final UserVO user) {
				IPCServiceEntry.getInstance().getContributorWorkflows(user.getId(), countryCode, new AsyncCallback<List<WorkflowInfoVO>>() {
					public void onSuccess(List<WorkflowInfoVO> workflows) {
						ListStore<IPCGridMD> md = window.getMd();
						md.removeAll();

						List<IPCGridMD> newMd = new ArrayList<IPCGridMD>();
						
						for (WorkflowInfoVO w : workflows) {
							System.out.println("workflows: "  + workflows.size() + " | " + w.getPeriod());
							IPCGridMD ipc = new IPCGridMD(w.getWorkflowName(), w.getReferenceLayer().getLabel(), w.getDescription(), w.getPeriod(), w.getStatus().getLabel(), w.getModeratorFirstName() + " " + w.getModeratorLastName());
							ipc.setWorkflowId(w.getWorkflowId());
							ipc.setContributor_id(user.getId());
							ipc.setContributorFirstName(user.getFirstName());
							ipc.setContributorLastName(user.getLastName());
							ipc.setDate(w.getDate());
							ipc.setReferenceLayerCode(w.getReferenceLayer().getCode());
							ipc.setCountryCode(w.getGeographicArea().getCode());
							ipc.setCountryLabel(w.getGeographicArea().getLabel());
							ipc.setStatusCode(w.getStatus().getCode());
							
							newMd.add(ipc);							
						}

						md.add(newMd);
						
					}

					public void onFailure(Throwable caught) {
						ListStore<IPCGridMD> md = window.getMd();
						md.removeAll();
					}
				});			
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: getUserID");
			}
		});	
	}
	
	
	public static void fillViewerGrid(final ViewerIPCWorkflowTab window) {
		final String countryCode = window.getCountry().getValue(window.getCountry().getSelectedIndex());
		final String countryLabel = window.getCountry().getItemText(window.getCountry().getSelectedIndex());
		System.out.println("Country: " + countryLabel + " | " + countryCode);
	
		
		IPCServiceEntry.getInstance().getCurrentUserInfo(new AsyncCallback<UserVO>() {
			@SuppressWarnings("unchecked")
			public void onSuccess(final UserVO user) {
				IPCServiceEntry.getInstance().getObserverProvinces(user.getId(), countryCode, new AsyncCallback<List<WorkflowInfoVO>>() {
					public void onSuccess(List<WorkflowInfoVO> workflows) {
						ListStore<IPCGridMD> md = window.getMd();
						md.removeAll();
	
						List<IPCGridMD> newMd = new ArrayList<IPCGridMD>();
						System.out.println("workflows: "  + workflows.size());
						
						for (WorkflowInfoVO w : workflows) {
							/* TODO: set the infomations **/
							for(ProvinceVO province : w.getProvinces()) {	
//								IPCGridMD(String name, String referenceLayer, String province, String contributor, String description, String period, String status)
								System.out.println(province.getProvinceLabel());
								IPCGridMD ipc = new IPCGridMD(w.getWorkflowName(), w.getReferenceLayer().getLabel(), province.getProvinceLabel(), province.getContributor_firstName() + " " + province.getContributor_lastName(), w.getDescription(), w.getPeriod(), w.getStatus().getLabel(), w.getModeratorFirstName() + "" + w.getModeratorLastName());
								ipc.setWorkId(province.getId());
//								ipc.setXml(province.getXml());
								ipc.setWorkflowId(w.getWorkflowId());
								ipc.setContributor_id(user.getId());
								ipc.setContributorFirstName(user.getFirstName());
								ipc.setContributorLastName(user.getLastName());
								ipc.setDate(w.getDate());
								ipc.setReferenceLayerCode(w.getReferenceLayer().getCode());
								ipc.setCountryCode(w.getGeographicArea().getCode());
								ipc.setCountryLabel(w.getGeographicArea().getLabel());
								
								
								ipc.setStatusCode(w.getStatus().getCode());
								newMd.add(ipc);				
							}
						}

						md.add(newMd);
					}

					public void onFailure(Throwable caught) {
						System.out.println("Found anything");
						ListStore<IPCGridMD> md = window.getMd();
						md.removeAll();
					}
				});			
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: getUserID");
			}
		});	
	}
	
	public static void fillViewerGrid(final IPCReportsPanel window) {
//		ListItem selectedcItem = window.countryComboBox.getValue();
		
		final String countryCode = window.getCountry().getValue(window.getCountry().getSelectedIndex());
		final String countryLabel = window.getCountry().getItemText(window.getCountry().getSelectedIndex());
		System.out.println("Country: " + countryLabel + " | " + countryCode);
		
				IPCServiceEntry.getInstance().getModeratorsWorkflows(countryCode, new AsyncCallback<List<WorkflowInfoVO>>() {
					public void onSuccess(List<WorkflowInfoVO> workflows) {
						ListStore<IPCGridMD> md = window.getMd();
						md.removeAll();

						List<IPCGridMD> newMd = new ArrayList<IPCGridMD>();
						
						for (WorkflowInfoVO w : workflows) {
							System.out.println("workflows: "  + workflows.size() + " | " + w.getPeriod());
							IPCGridMD ipc = new IPCGridMD(w.getWorkflowName(), w.getReferenceLayer().getLabel(), w.getDescription(), w.getPeriod(), w.getStatus().getLabel(), w.getModeratorFirstName() + " " + w.getModeratorLastName());
							ipc.setWorkflowId(w.getWorkflowId());
							ipc.setContributor_id(w.getModerator_id());
							ipc.setContributorFirstName(w.getModeratorFirstName());
							ipc.setContributorLastName(w.getModeratorLastName());
							ipc.setModeratorFullName(w.getModeratorFirstName() + " " + w.getModeratorLastName());
							ipc.setDate(w.getDate());
							ipc.setReferenceLayerCode(w.getReferenceLayer().getCode());
							ipc.setCountryCode(w.getGeographicArea().getCode());
							ipc.setCountryLabel(w.getGeographicArea().getLabel());
							ipc.setStatusCode(w.getStatus().getCode());
							
							newMd.add(ipc);							
						}

						md.add(newMd);
						
					}

					public void onFailure(Throwable caught) {
						ListStore<IPCGridMD> md = window.getMd();
						md.removeAll();
					}
				});			
			

	
	}
	

	
/*	public static void fillViewerGrid(final IPCReportsPanel window) {
		final String countryCode = window.getCountry().getValue(window.getCountry().getSelectedIndex());
		final String countryLabel = window.getCountry().getItemText(window.getCountry().getSelectedIndex());
		System.out.println("Country: " + countryLabel + " | " + countryCode);
	
		
		IPCServiceEntry.getInstance().getModerator(new AsyncCallback<UserVO>() {
			@SuppressWarnings("unchecked")
			public void onSuccess(final UserVO user) {
				IPCServiceEntry.getInstance().getObserverProvinces(user.getId(), countryCode, new AsyncCallback<List<WorkflowInfoVO>>() {
					public void onSuccess(List<WorkflowInfoVO> workflows) {
						ListStore<IPCGridMD> md = window.getMd();
						md.removeAll();
	
						List<IPCGridMD> newMd = new ArrayList<IPCGridMD>();
						System.out.println("workflows: "  + workflows.size());
						
						for (WorkflowInfoVO w : workflows) {
							for(ProvinceVO province : w.getProvinces()) {	
//								IPCGridMD(String name, String referenceLayer, String province, String contributor, String description, String period, String status)
								System.out.println(province.getProvinceLabel());
								IPCGridMD ipc = new IPCGridMD(w.getWorkflowName(), w.getReferenceLayer().getLabel(), province.getProvinceLabel(), province.getContributor_firstName() + " " + province.getContributor_lastName(), w.getDescription(), w.getPeriod(), w.getStatus().getLabel(), w.getModeratorFirstName() + " " + w.getModeratorLastName());
								ipc.setWorkId(province.getId());
								ipc.setWorkflowId(w.getWorkflowId());
								ipc.setContributor_id(province.getContributor_id());
								ipc.setContributorFirstName(province.getContributor_firstName());
								ipc.setContributorLastName(province.getContributor_lastName());
								ipc.setDate(w.getDate());
								ipc.setReferenceLayerCode(w.getReferenceLayer().getCode());
//								ipc.setXml(province.getXml());
								ipc.setStatusCode(w.getStatus().getCode());
								newMd.add(ipc);				
							}
						}

						md.add(newMd);
					}

					public void onFailure(Throwable caught) {
						System.out.println("Found anything");
						ListStore<IPCGridMD> md = window.getMd();
						md.removeAll();
					}
				});			
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error("ERROR", "RPC: getUserID");
			}
		});	
	}*/
	
	
	
	public static  SelectionListener<ButtonEvent> openWorkflow(final IPCWorkflowWindow ipcWindow, final ShowIPCWorkflowTab window, final Grid<IPCGridMD> grid) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				final IPCGridMD row = (IPCGridMD) grid.getSelectionModel().getSelectedItem();
				final Long workflowID = row.getWorkflowId();
				final Long user_id = row.getContributor_id();
				/** TODO: service that takes all the provinces related to a certain user_id, and workflow_id 
				 * 	and creates the controller
				 * **/
				IPCServiceEntry.getInstance().getWorkflowProvinces(user_id, workflowID, new AsyncCallback<List<ProvinceVO>>() {
					public void onSuccess(List<ProvinceVO> provinces) {
						WorkflowInfoVO w = window.getWorkflowInfo();
						w.setGeographicArea(new IPCCodesVO(window.getCountry().getValue(window.getCountry().getSelectedIndex()), window.getCountry().getItemText(window.getCountry().getSelectedIndex())));
						w.setProvinces(provinces);
						w.setWorkflowId(workflowID);
						w.setWorkflowName(row.getName());
						w.setDescription(row.getDescription());
						
					
						
						w.setStatus(new IPCCodesVO(row.getStatusCode(), row.getStatus()));
						w.setDate(row.getDate());
						w.setPeriod(row.getPeriod());
						/** TODO: add reference layer code**/
						w.setReferenceLayer(new IPCCodesVO(row.getReferenceLayerCode(), row.getReferenceLayer()));
						
						w.setProvinces(provinces);
						System.out.println("Controller_provinces size: "  + row.getReferenceLayerCode());
					
						IPCDataEntryControllerWindow controller = new IPCDataEntryControllerWindow();
						controller.build(w, false, true);
						ipcWindow.getWindow().close();
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failure", "RPC: opening workflow");
					}
				});			
				
			}
		};
	}
	
	
	public static  SelectionListener<ButtonEvent> openExistingWorkflow(final WorkflowInfoVO w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				IPCDataEntryControllerWindow controller = new IPCDataEntryControllerWindow();
				controller.build(w, false, true);	
			}
		};
	}
	
	public static  SelectionListener<ButtonEvent> getContributorsAndObservers(final CreateIPCWorkflowTab createTab, final IPCaddActorsWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				ListBox contributors = w.getSelectedContValues();
				List<UserVO> users = new ArrayList<UserVO>();
				for(int i=0; i < contributors.getItemCount(); i++) {
					UserVO user = new UserVO();
					user.setId(Long.valueOf(contributors.getValue(i)));
					user.setFirstAndLastName(contributors.getItemText(i));
					users.add(user);
				}
				createTab.setContributors(users);
				
				ListBox observers = w.getSelectedObsValues();
				users = new ArrayList<UserVO>();
				for(int i=0; i < observers.getItemCount(); i++) {
					UserVO user = new UserVO();
					user.setId(Long.valueOf(observers.getValue(i)));
					user.setFirstAndLastName(observers.getItemText(i));
					users.add(user);
				}
				createTab.setObservers(users);
				w.getWindow().close();
			}
		};
	}
	
	public static  SelectionListener<ButtonEvent> getContributorsAndObservers(final ShowEditIPCWorkflow window, final IPCaddActorsWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				ListBox contributors = w.getSelectedContValues();
				List<UserVO> users = new ArrayList<UserVO>();
				for(int i=0; i < contributors.getItemCount(); i++) {
					UserVO user = new UserVO();
					user.setId(Long.valueOf(contributors.getValue(i)));
					user.setFirstAndLastName(contributors.getItemText(i));
					users.add(user);
				}
				window.setContributors(users);
				
				ListBox observers = w.getSelectedObsValues();
				users = new ArrayList<UserVO>();
				for(int i=0; i < observers.getItemCount(); i++) {
					UserVO user = new UserVO();
					user.setId(Long.valueOf(observers.getValue(i)));
					user.setFirstAndLastName(observers.getItemText(i));
					users.add(user);
				}
				window.setObservers(users);
				w.getWindow().close();
			}
		};
	}
	
	public static  SelectionListener<ButtonEvent> openViewverWindow(final ViewerIPCWorkflowTab createTab, final Grid<IPCGridMD> grid) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				final IPCGridMD row = (IPCGridMD) grid.getSelectionModel().getSelectedItem();
								
				/* TODO: create a method to retrieve the xml */
				IPCServiceEntry.getInstance().getXml(row.getWorkId(), new AsyncCallback<String>() {
					public void onSuccess(String xml) {
//						final String xml = row.getXml();
						IPCViewerWindow window = new IPCViewerWindow();
						window.build(xml, false, row.getContributorFirstName() + " " + row.getContributorLastName() );
					}
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failure", "RPC: opening workflow");
					}
				});			
				
			}
		};
	}
	
	
	public static SelectionListener<ButtonEvent> deleteWorkflow(final ShowIPCWorkflowTab window, final Grid<IPCGridMD> grid) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("deprecation")
			public void componentSelected(ButtonEvent event) {
				ConfirmWindow confirm = new ConfirmWindow(BabelFish.print().areYouSureDeleteResource(), "Are you sure?", delete(window, grid));
				
			}
		};
	}
	
	
	private static SelectionListener<ButtonEvent> delete(final ShowIPCWorkflowTab window, final Grid<IPCGridMD> grid) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				final IPCGridMD row = (IPCGridMD) grid.getSelectionModel().getSelectedItem();
				final Long workflowID = row.getWorkflowId();
				
				IPCServiceEntry.getInstance().deleteIPCWorkflow(workflowID, new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean isDeleted) {
						if (isDeleted) {
							Info.display("Delete Workflow", "The IPC Workflow has been succefully deleted");
							fillLoadGrid(window);
						}
						else 
							FenixAlert.alert("Deleting Workflow", "Only moderator can delete the Workflow");
						
					}
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPC Failure", "Failed while deleting workflow");
					}
				});		
				
			}
		};
	}
	
	private static WorkflowInfoVO createWorkflowInfo(CreateIPCWorkflowTab window) {
		WorkflowInfoVO workflowInfo = new WorkflowInfoVO();
		
		ListItem selectedCountryItem = window.countryComboBox.getValue();
		ListItem selectedLayerItem = window.layerComboBox.getValue();
		ListBox selectedAreas = window.selectedAreasValues;			
		
		
		workflowInfo.setGeographicArea(new IPCCodesVO(selectedCountryItem.getValue(), selectedCountryItem.getName()));
		workflowInfo.setReferenceLayer(new IPCCodesVO(selectedLayerItem.getValue(), selectedLayerItem.getName()));
		
//		List<workflowInfo> interestedAreas = new ArrayList<IPCCodesVO>();
//		for(int i=0; i < selectedAreas.getItemCount(); i++)
//			interestedAreas.add(new IPCCodesVO(selectedAreas.getValue(i), selectedAreas.getItemText(i)));
//		workflowInfo.setInterestedAreas(interestedAreas);
		
		List<ProvinceVO> provinces = new ArrayList<ProvinceVO>();
		for(int i=0; i < selectedAreas.getItemCount(); i++) {
			ProvinceVO province = new ProvinceVO();
			province.setProvinceCode(selectedAreas.getValue(i));
			province.setProvinceLabel(selectedAreas.getItemText(i));
			provinces.add(province);
		}
		workflowInfo.setProvinces(provinces);
		
		workflowInfo.setWorkflowName(window.getName().getValue());
		workflowInfo.setDescription(window.getDescription().getValue());
		workflowInfo.setStatus(new IPCCodesVO(window.getStatus().getValue(window.getStatus().getSelectedIndex()), window.getStatus().getItemText(window.getStatus().getSelectedIndex())));
		workflowInfo.setDate(new Date());
		workflowInfo.setPeriod(window.getPeriod().getValue());
		
		workflowInfo.setContributors(window.getContributors());

		workflowInfo.setObservers(window.getObservers());		
		
		return workflowInfo;
	}
}