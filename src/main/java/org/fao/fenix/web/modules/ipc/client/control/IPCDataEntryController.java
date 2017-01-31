package org.fao.fenix.web.modules.ipc.client.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fao.fenix.web.modules.ipc.client.view.ConfirmWindow;
import org.fao.fenix.web.modules.ipc.client.view.ShowEditIPCWorkflow;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.IPCDataEntryControllerWindow;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.IPCDataEntryWindow;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.ModuleFour;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.ModuleOne;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.ModuleThree;
import org.fao.fenix.web.modules.ipc.client.view.dataentry.ModuleTwo;
import org.fao.fenix.web.modules.ipc.common.services.IPCServiceEntry;
import org.fao.fenix.web.modules.ipc.common.services.IPCXMLServiceEntry;
import org.fao.fenix.web.modules.ipc.common.vo.BulletPointVO;
import org.fao.fenix.web.modules.ipc.common.vo.DropDownVO;
import org.fao.fenix.web.modules.ipc.common.vo.FreeTextVO;
import org.fao.fenix.web.modules.ipc.common.vo.IPCCodesVO;
import org.fao.fenix.web.modules.ipc.common.vo.MapVO;
import org.fao.fenix.web.modules.ipc.common.vo.ModuleVO;
import org.fao.fenix.web.modules.ipc.common.vo.ProvinceVO;
import org.fao.fenix.web.modules.ipc.common.vo.UserVO;
import org.fao.fenix.web.modules.ipc.common.vo.WorkflowInfoVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class IPCDataEntryController {
	
private static Map<Integer, String[]> freeTextMap;
	
	private static Map<Integer, String[]> dropDownMap;
	
	static {
		freeTextMap = new HashMap<Integer, String[]>();
		freeTextMap.put(1, new String[]{"FT-00", "FT-01", "FT-02", "FT-03", "FT-04", "FT-05", "FT-06", "FT-07", "FT-08", "FT-09", "FT-10", "FT-11"});
		freeTextMap.put(2, new String[]{"FT-12"});
		freeTextMap.put(3, new String[]{"FT-13", "FT-14", "FT-15", "FT-16", "FT-17", "FT-18"});
		freeTextMap.put(4, new String[]{"FT-19", "FT-20", "FT-21", "FT-22", "FT-23", "FT-24", "FT-25", "FT-26", "FT-27", "FT-28", "FT-29", "FT-30", "FT-31"});
		dropDownMap = new HashMap<Integer, String[]>();
		dropDownMap.put(2, new String[]{"DD-00", "DD-01"});
		dropDownMap.put(3, new String[]{"DD-02", "DD-03"});
		dropDownMap.put(4, new String[]{"DD-04", "DD-05", "DD-06", "DD-07", "DD-08", "DD-09", "DD-10"});
	}

	public static WindowListener onCloseManager(final IPCDataEntryWindow window) {
		return new WindowListener() {
			@SuppressWarnings("deprecation")
			public void handleEvent(WindowEvent event) {
				if (event.getType() == Events.Hide) {
					window.windowIsOpen = false;
					window.getTabPanel().setSelection(window.getModuleOneTabItem());
					window.getWindow().close();
				}
			}
		};
	}

	public static SelectionListener<ButtonEvent> show(final IPCDataEntryWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				if ( !window.windowIsOpen ) {
					window.getTabPanel().setSelection(window.getModuleOneTabItem());
					window.getWindow().show();
					window.windowIsOpen = true;
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> hide(final IPCDataEntryWindow window) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("deprecation")
			public void componentSelected(ButtonEvent event) {
				window.windowIsOpen = false;
				window.getTabPanel().setSelection(window.getModuleOneTabItem());
				window.getWindow().close();
				
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> saveChangesWorkflowInfo(final IPCDataEntryControllerWindow ipcDataWindow, final ShowEditIPCWorkflow window) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("deprecation")
			public void componentSelected(ButtonEvent event) {
				new ConfirmWindow(BabelFish.print().confirmYourChoice(), BabelFish.print().areYouSure(), saveWorkflowInformations(ipcDataWindow, window));				
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> saveWorkflowInformations(final IPCDataEntryControllerWindow ipcDataWindow, final ShowEditIPCWorkflow window) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("deprecation")
			public void componentSelected(ButtonEvent event) {
				final WorkflowInfoVO updatedWorkflowInfoVO = new WorkflowInfoVO();
				WorkflowInfoVO workflow = window.getWorkflowInfoVO();
				updatedWorkflowInfoVO.setGeographicArea(workflow.getGeographicArea());
				updatedWorkflowInfoVO.setModerator_id(workflow.getModerator_id());
				updatedWorkflowInfoVO.setModeratorFirstName(workflow.getModeratorFirstName());
				updatedWorkflowInfoVO.setModeratorLastName(workflow.getModeratorLastName());
				updatedWorkflowInfoVO.setReferenceLayer(workflow.getReferenceLayer());
				updatedWorkflowInfoVO.setWorkflowId(workflow.getWorkflowId());	
				updatedWorkflowInfoVO.setDate(workflow.getDate());
			
				
				// setting modifications
				updateWorkflowInfo(window, updatedWorkflowInfoVO);
				if ( !updatedWorkflowInfoVO.getProvinces().isEmpty()) {
					IPCServiceEntry.getInstance().saveWorkflowInformations(updatedWorkflowInfoVO, window.getWorkflowInfoVO(), new AsyncCallback<Boolean>() {
						public void onSuccess(Boolean result) {
							IPCServiceEntry.getInstance().getCurrentUserInfo(new AsyncCallback<UserVO>() {
								public void onSuccess(final UserVO currentUser) {
									updatedWorkflowInfoVO.setModerator_id(currentUser.getId());
									updatedWorkflowInfoVO.setModeratorFirstName(currentUser.getFirstName());
									updatedWorkflowInfoVO.setModeratorLastName(currentUser.getLastName());
									
									IPCServiceEntry.getInstance().getWorkflowProvinces(updatedWorkflowInfoVO.getModerator_id(), updatedWorkflowInfoVO.getWorkflowId(), new AsyncCallback<List<ProvinceVO>>() {
										public void onSuccess(List<ProvinceVO> provinces) {
											Info.display(BabelFish.print().savingWorkflow(), BabelFish.print().saveCompleted());
											/** closes all the data entry windows **/
										
											Collection<IPCDataEntryWindow> ipcDataEntryWindows = ipcDataWindow.getIpcDataEntryWindows().values();
											for(IPCDataEntryWindow w : ipcDataEntryWindows)
												w.getWindow().close();										
											
											window.getWindow().close();
											ipcDataWindow.getWindow().close();
											updatedWorkflowInfoVO.setProvinces(provinces);
											IPCDataEntryControllerWindow controller = new IPCDataEntryControllerWindow();
											controller.build(updatedWorkflowInfoVO, false, true);								
										}
		
										public void onFailure(Throwable caught) {
											FenixAlert.alert("RPC Failure", "RPC: opening workflow");
										}
									});		
								}
								public void onFailure(Throwable caught) {
								}
							});		
							
						
						}
						public void onFailure(Throwable caught) {
							
						}
					});	
				}
				else
					FenixAlert.alert(BabelFish.print().savingWorkflow(), BabelFish.print().noSelectedProvinces());
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> saveWork(final IPCDataEntryControllerWindow window) {
		return new SelectionListener<ButtonEvent>() {
			@SuppressWarnings("static-access")
			public void componentSelected(ButtonEvent event) {
				
				final Map<String, IPCDataEntryWindow> ipcDataEntryWindows = window.getIpcDataEntryWindows();
				final Set<String> provinces = ipcDataEntryWindows.keySet();		
				final WorkflowInfoVO workflowInfoVO = window.getWorkFlowInfoVO();
				
//				final LoadingWindow loadingWindow = new LoadingWindow(I18N.print().savingWorkflow(), I18N.print().pleaseWait(), I18N.print().saving());
//				loadingWindow.showLoadingBox();

				IPCServiceEntry.getInstance().getWorkflowID(workflowInfoVO, new AsyncCallback<Long>() {
					public void onSuccess(Long result) {					
						workflowInfoVO.setWorkflowId(result);
							
						IPCServiceEntry.getInstance().deleteWork(workflowInfoVO, new AsyncCallback() {
							public void onSuccess(Object result) {
																
								final List<List<ModuleVO>> modulesVO = new ArrayList<List<ModuleVO>>();
								final List<ProvinceVO> provincesVO = new ArrayList<ProvinceVO>();
								for(String province : provinces) {	
									final String provinceCode = province;
									IPCDataEntryWindow ipcDataEntryWindow = ipcDataEntryWindows.get(province);
									final List<ModuleVO> mVO = parseModules(ipcDataEntryWindow.getModuleOne(), ipcDataEntryWindow.getModuleTwo(),  ipcDataEntryWindow.getModuleThree(), ipcDataEntryWindow.getModuleFour());
									final ProvinceVO provinceVO = new ProvinceVO();
									provinceVO.setPeriod(workflowInfoVO.getPeriod());
									provinceVO.setProvinceCode(provinceCode);
									provinceVO.setProvinceLabel(ipcDataEntryWindow.getProvinceLabel());									
									provinceVO.setModules(mVO);
									provincesVO.add(provinceVO);				
									modulesVO.add(mVO);
								}
								
									IPCXMLServiceEntry.getInstance().saveAllXml(modulesVO, workflowInfoVO, provincesVO, new AsyncCallback<List<ProvinceVO>>() {
										public void onSuccess(List<ProvinceVO> provinces) {
											Info.display(BabelFish.print().savingWorkflow(), BabelFish.print().saveCompleted()); 			
											IPCXMLServiceEntry.getInstance().getIPCMapValues(provinces, new AsyncCallback<List<ProvinceVO>>(){
												public void onSuccess(List<ProvinceVO> result) {
													final List<ProvinceVO> provinceVOList = new ArrayList<ProvinceVO>();
													provinceVOList.addAll(result);
													for(ProvinceVO provinceVO : result) {
														System.out.println("C_ID: " + provinceVO.getContributor_id());
														System.out.println("W_ID: " + workflowInfoVO.getWorkflowId());
														System.out.println("Country: " + workflowInfoVO.getGeographicArea().getLabel());
														System.out.println("Phase C: " +  provinceVO.getPhaseClassification());
														System.out.println("Projected T: " + provinceVO.getProjectedTrend());
														System.out.println("Rrisk L: " + provinceVO.getRiskLevel());
														System.out.println("ReferenceLayer C: " + workflowInfoVO.getReferenceLayer().getCode());
														System.out.println("ReferenceLayer L: " + workflowInfoVO.getReferenceLayer().getLabel());
														System.out.println("ProvinceListSize : " + provinceVOList.size());
													}
																										
													workflowInfoVO.setProvinces(provinceVOList);
													
													IPCServiceEntry.getInstance().createIPCDatasetsAndMap(provinceVOList, workflowInfoVO, new AsyncCallback(){
														public void onSuccess(Object result) {
//															loadingWindow.destroyLoadingBox();
															System.out.println("createIPCDatasetsAndMap done! ");
															FenixAlert.info("Creating Map", "The Map has been processed");
														}
														public void onFailure(Throwable caught) {
//															loadingWindow.destroyLoadingBox();
															FenixAlert.error("Creating Map", "The Map has not been created");
														}
													});			
		

												}
												public void onFailure(Throwable caught) {
//													loadingWindow.destroyLoadingBox();
													FenixAlert.alert("saveWork: getIPCMapValues failed", "getIPCMapValues failed");
													
												}
											});		
											
										}
										public void onFailure(Throwable caught) {
//											loadingWindow.destroyLoadingBox();
											FenixAlert.alert("Save Workflow", "Failed Saving the Workflow");
											
										}
									});			
							}
							

							public void onFailure(Throwable caught) {
								FenixAlert.alert("saveWork: deleteWork Failed", "deleteWork failed");
							}
						});
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("saveWork: getWorkflowID Failed", "getWorkflowID failed");
					}
				});
			}
		};
	}
	
	private static List<ModuleVO> parseModules(ModuleOne moduleOne, ModuleTwo moduleTwo, ModuleThree moduleThree, ModuleFour moduleFour){
		List<ModuleVO> modulesVO = new ArrayList<ModuleVO>();
		modulesVO.add(createModule1(moduleOne, moduleOne.getDropDownMap(), moduleOne.getTextAreaMap()));
		modulesVO.add(createModule2(moduleTwo.getDropDownMap(), moduleTwo.getTextAreaMap()));
		modulesVO.add(createModule3(moduleThree.getDropDownMap(), moduleThree.getTextAreaMap()));
		modulesVO.add(createModule4(moduleFour.getDropDownMap(), moduleFour.getTextAreaMap()));
		return modulesVO;
	}

	
	private static ModuleVO createModule1(ModuleOne moduleOne, Map<String, ListBox> dd, Map<String, HTML> ta) {

		ModuleVO moduleVO = new ModuleVO();
		
		moduleVO.setLevel(1);
		String[] FTS = freeTextMap.get(1);
//		System.out.println("\ncreateModule1");
		for(String FT : FTS) {
//			System.out.println("FT: " + FT);
			/** Create the freeText **/
			FreeTextVO freeText = new FreeTextVO();
			freeText.setCode(FT);
			freeText.setName(getLabel(FT));
		
			/** Create range code **/
			ListBox listBox = dd.get("Range" + FT);
//			System.out.println("setRangeLabel: " + listBox.getItemText(listBox.getSelectedIndex()));
			freeText.setRangeCode(listBox.getValue(listBox.getSelectedIndex()));
			freeText.setRangeLabel(listBox.getItemText(listBox.getSelectedIndex()));
			
			/** create exact value **/
			TextField<String> a = moduleOne.getExactValueMap().get(FT);
//			System.out.println(moduleOne.getExactValueMap().size());
			try {
//				System.out.println("exactValue1: " + a.getValue());			
				freeText.setExactValue(Double.valueOf(a.getValue()));
			}catch (Exception e) {
	
			}
			
			
			/** create level code **/
			listBox = dd.get("Level" + FT);
//			System.out.println("setLevelLabel: " + listBox.getItemText(listBox.getSelectedIndex()));
			freeText.setLevelCode(listBox.getValue(listBox.getSelectedIndex()));
			freeText.setLevelLabel(listBox.getItemText(listBox.getSelectedIndex()));
			
			/** create bullet points **/
			List<BulletPointVO> bulletsPoints = new ArrayList<BulletPointVO>();
			BulletPointVO bulletPoint = new BulletPointVO();
//			RichTextArea richTextArea = ta.get(FT);
			HTML richTextArea = ta.get(FT);
			bulletPoint.setDirectEvidence(false);
			bulletPoint.setText(richTextArea.getHTML());
//			System.out.println("richTextArea: " + bulletPoint.getText());
			bulletsPoints.add(bulletPoint);
			freeText.setBulletPoints(bulletsPoints);
			
			/** add freetext to module **/
			moduleVO.addFreeTextVO(freeText);
		}
		return moduleVO;
	}
	
	private static ModuleVO createModule2(Map<String, ListBox> dd, Map<String, HTML> ta) {
		ModuleVO moduleVO = new ModuleVO();
//		System.out.println("\ncreateModule2");
		moduleVO.setLevel(2);
		
		String [] DDS = dropDownMap.get(2);
		for(String DD : DDS) {
//			System.out.println("DD: " + DD);
			/** Create the dropDownVO **/
			DropDownVO dropDownVO = new DropDownVO();
			dropDownVO.setCode(DD);
			dropDownVO.setName(getLabel(DD));

			ListBox listBox = dd.get(DD);
//			System.out.println("set DD label: " + dd.size());
			
//			System.out.println("set DD label: " + listBox.getItemText(listBox.getSelectedIndex()));
			
			dropDownVO.setDropDownCode(listBox.getValue(listBox.getSelectedIndex()));
			dropDownVO.setDropDownLabel(listBox.getItemText(listBox.getSelectedIndex()));
			
			/** add dropDownVO to module **/
			moduleVO.addDropDownVO(dropDownVO);
		}
		
		String[] FTS = freeTextMap.get(2);
		for(String FT : FTS) {
//			System.out.println("FT: " + FT);
			/** Create the freeText **/
			FreeTextVO freeText = new FreeTextVO();
			freeText.setCode(FT);
			freeText.setName(getLabel(FT));
			/** create bullet points **/
			List<BulletPointVO> bulletsPoints = new ArrayList<BulletPointVO>();
			BulletPointVO bulletPoint = new BulletPointVO();
//			RichTextArea richTextArea = ta.get(FT);
			HTML richTextArea = ta.get(FT);
			bulletPoint.setDirectEvidence(false);
			bulletPoint.setText(richTextArea.getHTML());
//			System.out.println("richTextArea: " + bulletPoint.getText());
			bulletsPoints.add(bulletPoint);
			freeText.setBulletPoints(bulletsPoints);
			
			/** add freetext to module **/
			moduleVO.addFreeTextVO(freeText);
		}

		return moduleVO;
	}
	
	
	private static ModuleVO createModule3(Map<String, ListBox> dd, Map<String, HTML> ta) {
		ModuleVO moduleVO = new ModuleVO();
//		System.out.println("\ncreateModule3");
		moduleVO.setLevel(3);
		
		String [] DDS = dropDownMap.get(3);
		for(String DD : DDS) {
//			System.out.println("DD: " + DD);
			/** Create the dropDownVO **/
			DropDownVO dropDownVO = new DropDownVO();
			dropDownVO.setCode(DD);
			dropDownVO.setName(getLabel(DD));

			ListBox listBox = dd.get(DD);
//			System.out.println("set DD label: " + listBox.getItemText(listBox.getSelectedIndex()));
			dropDownVO.setDropDownCode(listBox.getValue(listBox.getSelectedIndex()));
			dropDownVO.setDropDownLabel(listBox.getItemText(listBox.getSelectedIndex()));
			
			/** add dropDownVO to module **/
			moduleVO.addDropDownVO(dropDownVO);
		}
		
		String[] FTS = freeTextMap.get(3);
		for(String FT : FTS) {
			System.out.println("FT: " + FT);
			/** Create the freeText **/
			FreeTextVO freeText = new FreeTextVO();
			freeText.setCode(FT);
			freeText.setName(getLabel(FT));
			/** create bullet points **/
			List<BulletPointVO> bulletsPoints = new ArrayList<BulletPointVO>();
			BulletPointVO bulletPoint = new BulletPointVO();
//			RichTextArea richTextArea = ta.get(FT);
			HTML richTextArea = ta.get(FT);
			bulletPoint.setDirectEvidence(false);
			bulletPoint.setText(richTextArea.getHTML());
//			System.out.println("richTextArea: " + bulletPoint.getText());
			bulletsPoints.add(bulletPoint);
			freeText.setBulletPoints(bulletsPoints);
			
			/** add freetext to module **/
			moduleVO.addFreeTextVO(freeText);
		}

		return moduleVO;
	}
	
	private static ModuleVO createModule4(Map<String, ListBox> dd, Map<String, HTML> ta) {
		ModuleVO moduleVO = new ModuleVO();
//		System.out.println("\ncreateModule4");
		moduleVO.setLevel(4);
		
		String [] DDS = dropDownMap.get(4);
		for(String DD : DDS) {
//			System.out.println("DD: " + DD);
			/** Create the dropDownVO **/
			DropDownVO dropDownVO = new DropDownVO();
			dropDownVO.setCode(DD);
			dropDownVO.setName(getLabel(DD));

			ListBox listBox = dd.get(DD);
//			System.out.println("set DD label: " + listBox.getItemText(listBox.getSelectedIndex()));
			dropDownVO.setDropDownCode(listBox.getValue(listBox.getSelectedIndex()));
			dropDownVO.setDropDownLabel(listBox.getItemText(listBox.getSelectedIndex()));
			
			/** add dropDownVO to module **/
			moduleVO.addDropDownVO(dropDownVO);
		}
		
		String[] FTS = freeTextMap.get(4);
		for(String FT : FTS) {
//			System.out.println("FT: " + FT);
			/** Create the freeText **/
			FreeTextVO freeText = new FreeTextVO();
			freeText.setCode(FT);
			freeText.setName(getLabel(FT));
			/** create bullet points **/
			List<BulletPointVO> bulletsPoints = new ArrayList<BulletPointVO>();
			BulletPointVO bulletPoint = new BulletPointVO();
//			RichTextArea richTextArea = ta.get(FT);
			HTML richTextArea = ta.get(FT);
			bulletPoint.setDirectEvidence(false);
			bulletPoint.setText(richTextArea.getHTML());
//			System.out.println("richTextArea: " + bulletPoint.getText());
			bulletsPoints.add(bulletPoint);
			freeText.setBulletPoints(bulletsPoints);
			
			/** add freetext to module **/
			moduleVO.addFreeTextVO(freeText);
		}

		return moduleVO;
	}

	
	
	
	private static String getLabel(String code){
		String label = new String();
		if ( code.equals("FT-00")) {
			return "Crude Mortality Rate";
		}
		else if ( code.equals("FT-01")) {
			return "Acute Malnutrition";
		}
		else if ( code.equals("FT-02")) {
			return "Disease";
		}
		else if ( code.equals("FT-02")) {
			return "Disease";
		}
		else if ( code.equals("FT-03")) {
			return "Food Access / Availability";
		}
		else if ( code.equals("FT-04")) {
			return "Dietary diversity";
		}
		else if ( code.equals("FT-05")) {
			return "Water access / availability";
		}
		else if ( code.equals("FT-06")) {
			return "Destitution / Displacement";
		}
		else if ( code.equals("FT-07")) {
			return "Civil Security";
		}
		else if ( code.equals("FT-08")) {
			return "Coping";
		}
		else if ( code.equals("FT-09")) {
			return "Structural Issues";
		}
		else if ( code.equals("FT-10")) {
			return "Hazards";
		}
		else if ( code.equals("FT-11")) {
			return "Livelihood Assets (5 capitals)";
		}
		else if ( code.equals("DD-00") || code.equals("DD-03") || code.equals("DD-05") || code.equals("DD-06")  || code.equals("DD-07") || code.equals("DD-08") || code.equals("DD-09") || code.equals("DD-10")) {
			return "Projected Phase";
		}
		else if ( code.equals("FT-12")) {
			return "Evidence of Risk for Worsening Phase or Magnitude";
		}
		else if ( code.equals("DD-01")) {
			return "Risk Level";
		}
		else if (  code.equals("DD-02") || code.equals("DD-04")) {
			return "Current or Imminent Phase";
		}
		else if ( code.equals("FT-13")) {
			return "Immediate Hazards";
		}
		
		else if ( code.equals("FT-14")) {
			return "Direct Food Security Problem";
		}
		else if ( code.equals("FT-15")) {
			return "Effect on Livelihood Strategies";
		}
		
		else if ( code.equals("FT-16")) {
			return "Population Affected";
		}
		else if ( code.equals("FT-17")) {
			return "Risk Factors to Monitor";
		}
		else if ( code.equals("FT-18")) {
			return "Opportunities for Response";
		}

		else if ( code.equals("FT-19")) {
			return "Underlying Causes";
		}
		else if ( code.equals("FT-20")) {
			return "Physical Capital";
		}
		else if ( code.equals("FT-21") || code.equals("FT-23") || code.equals("FT-25") || code.equals("FT-27")  || code.equals("FT-29") || code.equals("FT-31")) {
			return "Action";
		}
		else if ( code.equals("FT-22")) {
			return "Social Capital";
		}
		else if ( code.equals("FT-24")) {
			return "Financial Capital";
		}
		else if ( code.equals("FT-26")) {
			return "Natural Capital";
		}
		else if ( code.equals("FT-28")) {
			return "Human Capital";
		}
		else if ( code.equals("FT-30")) {
			return "Local Political Capital";
		}
		
		return label;
	}
	
	public static Listener<BaseEvent> openViewEdit(final IPCDataEntryControllerWindow window) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				final WorkflowInfoVO workflow = window.getWorkFlowInfoVO();
				IPCServiceEntry.getInstance().isModerator(workflow.getWorkflowId(), new AsyncCallback<Boolean>() {
					public void onSuccess(Boolean isModerator) {					
						ShowEditIPCWorkflow w = new ShowEditIPCWorkflow();
						w.build(window, isModerator);
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("openShowEdit Failed", "Could not open the IPC Show/Edit Workflow");
					}
				});
				
			}
		};
	}
	
	public static Listener<BaseEvent> openIPCMap(final IPCDataEntryControllerWindow window) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent event) {
				final WorkflowInfoVO workflow = window.getWorkFlowInfoVO();
				final List<ProvinceVO> provinces = workflow.getProvinces();	

				IPCServiceEntry.getInstance().findIPCMap(provinces, workflow, new AsyncCallback<MapVO>() {
					public void onSuccess(MapVO vo) {
						System.out.println("mapId is NOT null for the IPC map");
						
						if(vo!=null) {
							String mapId = vo.getMapId().toString();
							String mapTitle = vo.getMapTitle();

							MapWindow mw = new MapWindow(mapId, mapTitle);
							mw.show();
						} else { 
							System.out.println("mapId is null for the IPC map");
							
							FenixAlert.alert("Save Changes", "Could not open the IPC map, please save the workflow then re-try");

							/*IPCServiceEntry.getInstance().createIPCDatasetsAndMap(provinces, workflow, new AsyncCallback() {
								public void onSuccess(Object result) {

									IPCServiceEntry.getInstance().findIPCMap(provinces, workflow, new AsyncCallback<String>() {
										public void onSuccess(String mapIdTitle) {
											System.out.println("mapId is NOT null for the IPC map");
											StringTokenizer tokenizer = new StringTokenizer(mapIdTitle, ":");

											String mapId = tokenizer.nextToken();
											String mapTitle = tokenizer.nextToken();

											if(mapId!=null) {
												MapWindow mw = new MapWindow(mapId, mapTitle);
												mw.show();
											} 
										}

										public void onFailure(Throwable caught) {
											FenixAlert.alert("findIPCMap: Failed", "Could not find the IPC map");
										}
									});

								}

								public void onFailure(Throwable caught) {
									FenixAlert.alert("createIPCDatasetsAndMap: Failed", "Could not create the IPC map");
								}
							});*/

						}

					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("openIPCMap Failed", "Could not open the IPC map");
					}
				});
			}
		};
	}
	
	public static void getUsers(final IPCDataEntryControllerWindow window) {
				final WorkflowInfoVO workflow = window.getWorkFlowInfoVO();
				
				IPCServiceEntry.getInstance().getCurrentUserInfo( new AsyncCallback<UserVO>() {
					public void onSuccess(final UserVO currentUser) {
						IPCServiceEntry.getInstance().getContributors(workflow.getWorkflowId(), new AsyncCallback<List<UserVO>>() {
							public void onSuccess(final List<UserVO> contributors) {
								if ( contributors != null)
									workflow.setContributors(contributors);
								IPCServiceEntry.getInstance().getObservers(workflow.getWorkflowId(), new AsyncCallback<List<UserVO>>() {
									public void onSuccess(List<UserVO> observers) {
										if ( observers != null)
											workflow.setObservers(observers);
										
										
										VerticalPanel v = new VerticalPanel();
										v.setSpacing(10);
									
										
										HTML label = new HTML("<b>Contributors:</b>");
										
										VerticalPanel u = new VerticalPanel();
//										u.setSpacing(10);
										for(UserVO user : contributors) {
											if ( user.getId() != currentUser.getId())
												u.add(new HTML(user.getFirstName() + " " + user.getLastName()));
										}
										v.add(label);
										v.add(u);
										window.getUsersPanel().removeAll();
										window.getUsersPanel().add(v);
										
										window.getWindow().layout();
										
//										label = new HTML("<br>Observers:</br>");
//										v.add(label);	
//										for(UserVO user : workflow.getObservers()) {
//											if ( user.getId() != currentUser.getId())
//												v.add(new HTML(user.getFirstName() + " " + user.getLastName()));
//										}
									}
									public void onFailure(Throwable caught) {
										FenixAlert.alert("getting Observers Failed", "Could not get Workflow Observers");
									}
								});

							}

							public void onFailure(Throwable caught) {
								FenixAlert.alert("getting Contributos Failed", "Could not get Workflow Contributors");
							}
						});
					}
					public void onFailure(Throwable caught) {
					}
				});
			}
	
	private static void updateWorkflowInfo(ShowEditIPCWorkflow window, WorkflowInfoVO wf) {
		ListBox selectedAreas = window.selectedAreasValues;			
		
		List<ProvinceVO> provinces = new ArrayList<ProvinceVO>();
		for(int i=0; i < selectedAreas.getItemCount(); i++) {
			ProvinceVO province = new ProvinceVO();
			province.setProvinceCode(selectedAreas.getValue(i));
			province.setProvinceLabel(selectedAreas.getItemText(i));
			provinces.add(province);
		}
		wf.setProvinces(provinces);
		
		wf.setWorkflowName(window.getName().getValue());
		wf.setDescription(window.getDescription().getValue());
		wf.setStatus(new IPCCodesVO(window.getStatus().getValue(window.getStatus().getSelectedIndex()), window.getStatus().getItemText(window.getStatus().getSelectedIndex())));
		wf.setPeriod(window.getPeriod().getValue());
		
		wf.setContributors(window.getContributors());

		wf.setObservers(window.getObservers());		
	}
}