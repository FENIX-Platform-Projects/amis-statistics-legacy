package org.fao.fenix.web.modules.adam.client.control;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.view.ADAMComboMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMLoadingPanel;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItemTree;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMMultiSelectionController extends ADAMController {


	public static void addMultiSelectionPanel(final String type, final ADAMComboMultiSelection adamComboMultiSelection){
		if ( type.equalsIgnoreCase("COUNTRY")) {
			addGaulMultiselectionAgent(adamComboMultiSelection);
		}
		else if ( type.equalsIgnoreCase("DONOR")) {
			if(!currentVIEW.equals(ADAMCurrentVIEW.PROFILES))
				addDonorMultiselectionAgent(adamComboMultiSelection);
			else 
				FenixAlert.info(BabelFish.print().resourcePartnerProfiles(), BabelFish.print().pleaseSelectResourcePartner(), "");
		}
		else if ( type.equalsIgnoreCase("SECTOR")) {
			addSectorMultiselectionAgent(adamComboMultiSelection);
		}
		else if ( type.equalsIgnoreCase("SUB-SECTOR")) {
			addSubSectorMultiselectionAgent(adamComboMultiSelection);
		}
		else if ( type.equalsIgnoreCase("SO")) {
			addSOMultiselectionAgent(adamComboMultiSelection);
		}
		else if ( type.equalsIgnoreCase("OR")) {
			addORMultiselectionAgent(adamComboMultiSelection);
		}

	}

	public static void removeMultiSelections() {
		if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
			RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
		RootPanel.get("MULTISELECTION").setStyleName("");

		ADAMVisibilityController.styleVisibilityNoDisplay("MULTISELECTION_TABLE");
		ADAMVisibilityController.styleVisibilityNoDisplay("MULTISELECTION");
	}


	public static SelectionListener<IconButtonEvent> addGaulMultiselection(final ADAMComboMultiSelection adamComboMultiSelection) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				addGaulMultiselectionAgent(adamComboMultiSelection);
			}
		};
	}

	private static void addGaulMultiselectionAgent(final ADAMComboMultiSelection adamComboMultiSelection) {

		restoreMultiSelectionVisibility();
		
		RootPanel.get("MULTISELECTION").add(new ADAMLoadingPanel().buildWaitingPanelWhite());

		String hiearchyCode = "";
		
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
			hiearchyCode = "FPMISRecipients";
		else
			hiearchyCode = "ADAMRecipients";
		
		ADAMServiceEntry.getInstance().getCountriesList(hiearchyCode, new AsyncCallback<LinkedHashMap<CodeVo,LinkedHashMap<CodeVo,List<CodeVo>>>>() {
			public void onSuccess(LinkedHashMap<CodeVo, LinkedHashMap<CodeVo, List<CodeVo>>> result) {

				TreeStore treeStore = adamComboMultiSelection.gaulTreeStore;
				treeStore.removeAll();

				//				System.out.println("\n\nPRINT");
				for(CodeVo topcodeVo : result.keySet()) {
					ListItemTree topItem = createTreeItem("Gaul_" + topcodeVo.getCode(), topcodeVo.getLabel(), "0");
					treeStore.add(topItem, false);
					//					System.out.println("- " + topcodeVo.getLabel());

					for(CodeVo subCodeVo : result.get(topcodeVo).keySet()) {
						//						System.out.println("-- " + subCodeVo.getLabel());
						ListItemTree subItem = createTreeItem("Gaul_" + subCodeVo.getCode(), subCodeVo.getLabel(), "1");
						treeStore.add(topItem, subItem, false);

						if ( result.get(topcodeVo).containsKey(subCodeVo)) {
							for(CodeVo subSubCodeVo : result.get(topcodeVo).get(subCodeVo)) {
								//								System.out.println("--- " + subSubCodeVo.getLabel());
								ListItemTree subSubItem = createTreeItem("Gaul_" + subSubCodeVo.getCode(), subSubCodeVo.getLabel(), "1");
								treeStore.add(subItem, subSubItem, false);

							}
						}
					}
				}
				//				adamComboMultiSelection.gaulTreePanel.addCheckListener(checkedItem());

				// change in node check state  
				VerticalPanel infoPanel = new VerticalPanel();
				infoPanel.setHeight(150);
				infoPanel.setWidth(250);
				infoPanel.setScrollMode(Scroll.AUTO);

				adamComboMultiSelection.gaulTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.gaulTreePanel));

				checkCountryValues(ADAMBoxMaker.countriesSelected, ADAMBoxMaker.countriesRegionsSelected, adamComboMultiSelection.gaulTreePanel, infoPanel);

				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
					   RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
				
				RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildGaulRegions(infoPanel));
				adamComboMultiSelection.panel.layout();

				//				System.out.println("END PRINT");

			}


			public void onFailure(Throwable arg0) {
				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
					   RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
				
				FenixAlert.error(BabelFish.print().error(), arg0.getMessage());
			}
		});
	}


	public static SelectionListener<IconButtonEvent> addDonorMultiselection(final ADAMComboMultiSelection adamComboMultiSelection) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				addDonorMultiselectionAgent(adamComboMultiSelection);
			}
		};
	}

	private static void addDonorMultiselectionAgent(final ADAMComboMultiSelection adamComboMultiSelection) {
		
		restoreMultiSelectionVisibility();
		
		RootPanel.get("MULTISELECTION").add(new ADAMLoadingPanel().buildWaitingPanelWhite());
		
		String codingSystemCode = getCodingSystemCode(currentlySelectedDatasetCode);
		
		
		ADAMServiceEntry.getInstance().findAll("Donor_", codingSystemCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {

				VerticalPanel infoPanel = fillMuliselectionPanel(adamComboMultiSelection, vos,  ADAMBoxMaker.donorsSelected, adamComboMultiSelection.donorTreePanel, adamComboMultiSelection.donorTreeStore);


				/*adamComboMultiSelection.donorTreeStore.removeAll();

				for (CodeVo vo : vos) {
					ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
					adamComboMultiSelection.donorTreeStore.add(treeItem, false);

				}

				 // change in node check state  
				VerticalPanel infoPanel = new VerticalPanel();
				infoPanel.setHeight(150);
				infoPanel.setWidth(250);
				infoPanel.setScrollMode(Scroll.AUTO);

				adamComboMultiSelection.donorTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.donorTreePanel));

				checkValues(ADAMBoxMaker.donorsSelected, adamComboMultiSelection.donorTreePanel, infoPanel);*/

				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
					RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
				
				RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildDonorPanel(infoPanel));
				adamComboMultiSelection.panel.layout();
			}
			public void onFailure(Throwable e) {
				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
					   RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
				
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
				//FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});

	}


	public static SelectionListener<IconButtonEvent> addSectorMultiselection(final ADAMComboMultiSelection adamComboMultiSelection) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				addSectorMultiselectionAgent(adamComboMultiSelection);
			}
		};
	}



	private static void addSectorMultiselectionAgent(final ADAMComboMultiSelection adamComboMultiSelection) {

		restoreMultiSelectionVisibility();
		
		RootPanel.get("MULTISELECTION").add(new ADAMLoadingPanel().buildWaitingPanelWhite());
		
		String codingSystemCode = getCodingSystemCode(currentlySelectedDatasetCode);
		
		
		/*	ADAMVisibilityController.restoreMultiselectionVisibility();
				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
					RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
				RootPanel.get("MULTISELECTION").setStyleName("multiselection-sector-box border content");*/

		ADAMServiceEntry.getInstance().findAll("Dac_", codingSystemCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {

				VerticalPanel infoPanel = fillMuliselectionPanel(adamComboMultiSelection, vos,  ADAMBoxMaker.sectorsSelected, adamComboMultiSelection.sectorTreePanel, adamComboMultiSelection.sectorTreeStore);

				/**adamComboMultiSelection.sectorTreeStore.removeAll();

						for (CodeVo vo : vos) {
							List<ListItem> values = new ArrayList<ListItem>();

							// Agricultural/FAO related sectors = 9999, should not be included in multi-selection
							if(!vo.getCode().equalsIgnoreCase("Dac_9999")) {
								ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
								adamComboMultiSelection.sectorTreeStore.add(treeItem, false);
							}
						}

						 // change in node check state  
						VerticalPanel infoPanel = new VerticalPanel();
						infoPanel.setHeight(150);
						infoPanel.setWidth(250);
						infoPanel.setScrollMode(Scroll.AUTO);

						adamComboMultiSelection.sectorTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.sectorTreePanel));


						checkValues(ADAMBoxMaker.sectorsSelected, adamComboMultiSelection.sectorTreePanel, infoPanel);
				 **/
				
				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
					   RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
				
				RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildSectorPanel(infoPanel));
				adamComboMultiSelection.panel.layout();

			}
			public void onFailure(Throwable e) {
				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
					   RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
				
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
				
				
			}
		});


	}


	public static SelectionListener<IconButtonEvent> addSubSectorMultiselection(final ADAMComboMultiSelection adamComboMultiSelection) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				addSubSectorMultiselectionAgent(adamComboMultiSelection);
			}
		};
	}



	private static void addSubSectorMultiselectionAgent(final ADAMComboMultiSelection adamComboMultiSelection) {

		restoreMultiSelectionVisibility();

		RootPanel.get("MULTISELECTION").add(new ADAMLoadingPanel().buildWaitingPanelWhite());

		/*ADAMVisibilityController.restoreMultiselectionVisibility();
		if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
			RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
		RootPanel.get("MULTISELECTION").setStyleName("multiselection-sector-box border content");
		 */

		VerticalPanel infoPanel = fillMuliselectionPanel(adamComboMultiSelection, ADAMBoxMaker.subSectorStore,  ADAMBoxMaker.subSectorsSelected, adamComboMultiSelection.subSectorTreePanel, adamComboMultiSelection.subSectorTreeStore);
		
		if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
			RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
		
		RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildSubSectorPanel(infoPanel));
		adamComboMultiSelection.panel.layout();
		
		
		/*if(ADAMBoxMaker.sectorList.getValue().getGaulCode()!="GLOBAL" && ADAMBoxMaker.sectorList.getValue().getGaulCode()!="MULTI") {
			List<String> parents = buildParentList(ADAMBoxMaker.sectorsSelected);

			ADAMServiceEntry.getInstance().findChildren("SubDac_", parents, new AsyncCallback<List<CodeVo>>() {
				public void onSuccess(List<CodeVo> vos) {

					VerticalPanel infoPanel = fillMuliselectionPanel(adamComboMultiSelection, vos,  ADAMBoxMaker.subSectorsSelected, adamComboMultiSelection.subSectorTreePanel, adamComboMultiSelection.subSectorTreeStore);

					*//** adamComboMultiSelection.subSectorTreeStore.removeAll();

						for (CodeVo vo : vos) {
							ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
							adamComboMultiSelection.subSectorTreeStore.add(treeItem, false);
						}

						 // change in node check state  
						VerticalPanel infoPanel = new VerticalPanel();
						infoPanel.setHeight(150);
						infoPanel.setWidth(250);
						infoPanel.setScrollMode(Scroll.AUTO);

						adamComboMultiSelection.subSectorTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.subSectorTreePanel));


						checkValues(ADAMBoxMaker.subSectorsSelected, adamComboMultiSelection.subSectorTreePanel, infoPanel);
					 **//*
					RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildSubSectorPanel(infoPanel));
					adamComboMultiSelection.panel.layout();
				}
				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
				}
			});

		} else {
			ADAMServiceEntry.getInstance().findAll("SubDac_", new AsyncCallback<List<CodeVo>>() {
				public void onSuccess(List<CodeVo> vos) {

					VerticalPanel infoPanel = fillMuliselectionPanel(adamComboMultiSelection, vos,  ADAMBoxMaker.subSectorsSelected, adamComboMultiSelection.subSectorTreePanel, adamComboMultiSelection.subSectorTreeStore);

					*//**	adamComboMultiSelection.subSectorTreeStore.removeAll();

						for (CodeVo vo : vos) {
							ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
							adamComboMultiSelection.subSectorTreeStore.add(treeItem, false);

						}

						 // change in node check state  
						VerticalPanel infoPanel = new VerticalPanel();
						infoPanel.setHeight(150);
						infoPanel.setWidth(250);
						infoPanel.setScrollMode(Scroll.AUTO);

						adamComboMultiSelection.subSectorTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.subSectorTreePanel));


						checkValues(ADAMBoxMaker.subSectorsSelected, adamComboMultiSelection.subSectorTreePanel, infoPanel);
					 **//*
					RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildSubSectorPanel(infoPanel));
					adamComboMultiSelection.panel.layout();

				}
				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
				}
			});
		}*/

	}

	public static SelectionListener<IconButtonEvent> addSOMultiselection(final ADAMComboMultiSelection adamComboMultiSelection) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				addSOMultiselectionAgent(adamComboMultiSelection);
			}
		};
	}

	private static void addSOMultiselectionAgent(final ADAMComboMultiSelection adamComboMultiSelection) {
		restoreMultiSelectionVisibility();
		
		RootPanel.get("MULTISELECTION").add(new ADAMLoadingPanel().buildWaitingPanelWhite());

		String codingSystemCode = getCodingSystemCode(currentlySelectedDatasetCode);
		
		
		ADAMServiceEntry.getInstance().findAll("SO_", codingSystemCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {
				VerticalPanel infoPanel = fillMuliselectionPanel(adamComboMultiSelection, vos,  ADAMBoxMaker.soSelected, adamComboMultiSelection.soTreePanel, adamComboMultiSelection.soTreeStore);

				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
					   RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
				
				RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildSOPanel(infoPanel));
				adamComboMultiSelection.panel.layout();

			}
			public void onFailure(Throwable e) {
				if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
					   RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
				//				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});


	}

	public static SelectionListener<IconButtonEvent> addORMultiselection(final ADAMComboMultiSelection adamComboMultiSelection) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				addORMultiselectionAgent(adamComboMultiSelection);
			}
		};
	}

	private static void addORMultiselectionAgent(final ADAMComboMultiSelection adamComboMultiSelection) {

			restoreMultiSelectionVisibility();

			RootPanel.get("MULTISELECTION").add(new ADAMLoadingPanel().buildWaitingPanelWhite());
			
			VerticalPanel infoPanel = fillMuliselectionPanel(adamComboMultiSelection, ADAMBoxMaker.orStore,  ADAMBoxMaker.orSelected, adamComboMultiSelection.orTreePanel, adamComboMultiSelection.orTreeStore);
			
			if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
				   RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
			
			RootPanel.get("MULTISELECTION").add(adamComboMultiSelection.buildORPanel(infoPanel));
			adamComboMultiSelection.panel.layout();
			
	}

	
	private static VerticalPanel fillMuliselectionPanel(ADAMComboMultiSelection adamComboMultiSelection, List<CodeVo> vos,  Map<String, String> selectedItems, TreePanel<ListItemTree> treePanel, TreeStore<ListItemTree> treeStore){

		treeStore.removeAll();

		for (CodeVo vo : vos) {
			// Agricultural/FAO related sectors = 9999, should not be included in multi-selection
			if(!vo.getCode().equalsIgnoreCase("Dac_9999")) {
				ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
				treeStore.add(treeItem, false);
			}
		}


		// change in node check state  
		VerticalPanel infoPanel = new VerticalPanel();
		infoPanel.setHeight(150);
		infoPanel.setWidth(250);
		infoPanel.setScrollMode(Scroll.AUTO);

		treePanel.addListener(Events.OnClick, createPanel(infoPanel, treePanel));


		checkValues(selectedItems, treePanel, infoPanel);

		return infoPanel;

	}


	private static VerticalPanel fillMuliselectionPanel(ADAMComboMultiSelection adamComboMultiSelection, ListStore<GaulModelData> store,  Map<String, String> selectedItems, TreePanel<ListItemTree> treePanel, TreeStore<ListItemTree> treeStore){

		treeStore.removeAll();

		for (GaulModelData model : store.getModels()) {
			    if(!model.getGaulCode().equals("GLOBAL") && !model.getGaulCode().equals("MULTI")){
			    	ListItemTree treeItem = createTreeItem(model.getGaulCode(), model.getGaulLabel(), "0");
			    	treeStore.add(treeItem, false);
		         }
		}


		// change in node check state  
		VerticalPanel infoPanel = new VerticalPanel();
		infoPanel.setHeight(150);
		infoPanel.setWidth(250);
		infoPanel.setScrollMode(Scroll.AUTO);

		treePanel.addListener(Events.OnClick, createPanel(infoPanel, treePanel));


		checkValues(selectedItems, treePanel, infoPanel);

		return infoPanel;

	}




	private static Listener<TreePanelEvent<ListItemTree>> createPanel(final VerticalPanel infoPanel, final TreePanel<ListItemTree> treePanel) {
		return new Listener<TreePanelEvent<ListItemTree>>() {
			public void handleEvent(TreePanelEvent<ListItemTree> be) {  
				infoPanel.removeAll();
				List<ListItemTree> items = treePanel.getCheckedSelection();
				for (ListItemTree itemSelected : items) {
					if (treePanel.isLeaf(itemSelected)) {
						infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
					}
				}
				infoPanel.layout();
			} 
		};
	}


	private static void checkValues(final Map<String, String> map,  final TreePanel<ListItemTree> treePanel,  final VerticalPanel infoPanel) {
		TreeStore<ListItemTree> items = treePanel.getStore();
		if( map != null ) {
			for (String code : map.keySet()) {
				for (ListItemTree itemSelected : items.getAllItems()) {
					if( code.equalsIgnoreCase(itemSelected.getCode())) {
						treePanel.setChecked(itemSelected, true);
						if (treePanel.isLeaf(itemSelected)) {
							infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
						}
						break;
					}

				}
			}
		}
	}

	private static void checkCountryValues(final Map<String, String> map,  final Map<String, String> hierarchyMap, final TreePanel<ListItemTree> treePanel, final VerticalPanel infoPanel) {
		TreeStore<ListItemTree> items = treePanel.getStore();
		if( map != null ) {
			for (String code : map.keySet()) {
				for (ListItemTree itemSelected : items.getAllItems()) {
					//					System.out.println("----> " + code +" | "+ itemSelected.getCode());
					if( itemSelected.getCode().equalsIgnoreCase(code)) {
						//						System.out.println("equal " + code +" | "+ itemSelected.getCode());
						treePanel.setChecked(itemSelected, true);					

						if (treePanel.isLeaf(itemSelected)) {
							infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
						}
						break;
					}


				}
			}
		}

		/** TODO : OPTIMIZE, merge with the upper code **/
		if( hierarchyMap != null ) {
			for (String code : hierarchyMap.keySet()) {
				for (ListItemTree itemSelected : items.getAllItems()) {
					//					System.out.println("----> " + code +" | "+ itemSelected.getCode());
					if( itemSelected.getCode().equalsIgnoreCase(code)) {
						//						System.out.println("equal " + code +" | "+ itemSelected.getCode());
						treePanel.setChecked(itemSelected, true);

						//						if (treePanel.isLeaf(itemSelected)) {
						//							infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
						//						}
						break;
					}


				}
			}
		}
	}

	public static SelectionListener<ButtonEvent> closeMultiselection() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				removeMultiSelections();
			}
		};
	}
	public static SelectionListener<ButtonEvent> applyMultiselection(final TreePanel<ListItemTree> treePanel, final Map<String, String> map, final Map<String, String> hierachyMap, final String selection) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				// cleaning map codes
				map.clear();

				// cleaning map hierarchy codes
				if ( hierachyMap != null ) {
					hierachyMap.clear();
				}

				List<ListItemTree> items = treePanel.getCheckedSelection();
				for (ListItemTree itemSelected : items) {
					if (treePanel.isLeaf(itemSelected)) {
						String code = itemSelected.getCode();
						String label = itemSelected.getLabel();
						map.put(code, label);
					}
					else {
						if ( hierachyMap != null ) {
							String code = itemSelected.getCode();
							String label = itemSelected.getLabel();
							hierachyMap.put(code, label);
						}
					}
				}

				if ( selection.equals("COUNTRY") ) {
					setListBoxSelection(ADAMBoxMaker.getGaulStore(), ADAMBoxMaker.getGaulList(), map);
					//setMultiSelectionListBox(ADAMBoxMaker.getGaulStore(), ADAMBoxMaker.getGaulList());
				}

				else if ( selection.equals("DONOR") ) {
					setListBoxSelection(ADAMBoxMaker.getDonorStore(), ADAMBoxMaker.getDonorList(), map);
					//setMultiSelectionListBox(ADAMBoxMaker.getDonorStore(), ADAMBoxMaker.getDonorList());
				}

				else if ( selection.equals("SECTOR") ) {
					setListBoxSelection(ADAMBoxMaker.getSectorStore(), ADAMBoxMaker.getSectorList(), map);

					//populate subsector list based on multi selections
					ADAMBoxMaker.subSectorStore.removeAll();
					ADAMBoxMaker.subSectorStore.add(ADAMBoxMaker.defaultCodes(true));
					List<String> parents = buildParentList(map);
					ADAMController.fillHierarchicalSelectorStoreADAMBox("SubDac_", ADAMBoxMaker.subSectorStore, ADAMBoxMaker.subSectorList, ADAMBoxMaker.selectedSubSectorFromURL, parents, ADAMController.currentlySelectedDatasetCode, ADAMBoxMaker.subSectorListListener);

					//setMultiSelectionListBox(ADAMBoxMaker.getSectorStore(), ADAMBoxMaker.getSectorList());
				}

				else if ( selection.equals("SO") ) {
					setListBoxSelection(ADAMBoxMaker.getSoStore(), ADAMBoxMaker.getSoList(), map);

					//populate OR list based on multi selections
					ADAMBoxMaker.orStore.removeAll();
					ADAMBoxMaker.orStore.add(ADAMBoxMaker.defaultCodes(true));
					List<String> parents = buildParentList(map);
					ADAMController.fillHierarchicalSelectorStoreADAMBox("OR_", ADAMBoxMaker.orStore, ADAMBoxMaker.orList, ADAMBoxMaker.selectedORFromURL, parents, ADAMController.currentlySelectedDatasetCode, ADAMBoxMaker.orListListener);


					//	setMultiSelectionListBox(ADAMBoxMaker.getSoStore(), ADAMBoxMaker.getSoList());
				}
				else if ( selection.equals("SUB-SECTOR") ) {
					setListBoxSelection(ADAMBoxMaker.getSubSectorStore(), ADAMBoxMaker.getSubSectorList(), map);
				}
				else if ( selection.equals("OR") ) {
					setListBoxSelection(ADAMBoxMaker.getOrStore(), ADAMBoxMaker.getOrList(), map);
				}


				//System.out.println("map: " + map);
				//System.out.println("hierarchy map: " + hierachyMap);

				callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, ADAMBoxMaker.subSectorsSelected, currentVIEW, "applyMultiselection", selectedTab);
				removeMultiSelections();
			}
		};
	}


	private static void setListBoxSelection(ListStore<GaulModelData> store, ComboBox<GaulModelData> combo, final Map<String, String> selections){

		if(selections.size() == 0) {
			setAllSelectedListBox(store, combo);
		} else if (selections.size() == 1){
			for(String code: selections.keySet()){
				setSelectedItemListBox(store, combo, code);
				break;
			}	
		} else if(selections.size() > 1) {
			setMultiSelectionListBox(store, combo);
		}
	}


	private static void setMultiSelectionListBox(ListStore<GaulModelData> store,  ComboBox<GaulModelData> list) {
		list.disableEvents(true);
		for(GaulModelData c : store.getModels() ){
			if ( c.getGaulCode().equalsIgnoreCase("MULTI")) {
				list.setValue(c);
				break;
			} 

		}
		list.disableEvents(false);

	}

	private static void setAllSelectedListBox(ListStore<GaulModelData> store,  ComboBox<GaulModelData> list) {
		list.disableEvents(true);
		list.setValue(store.getModels().get(0));
		list.disableEvents(false);
	}

	private static void setSelectedItemListBox(ListStore<GaulModelData> store,  ComboBox<GaulModelData> list, String selectedItemCode) {
		list.disableEvents(true);
		for(GaulModelData c : store.getModels() ){
			if ( c.getGaulCode().equalsIgnoreCase(selectedItemCode)) {
				list.setValue(c);
				break;
			} 

		}
		list.disableEvents(false);
	}

	public static SelectionListener<ButtonEvent> applyCountryMultiselection(final TreePanel<ListItemTree> treePanel, final Map<String, String> map) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				map.clear();
				List<ListItemTree> items = treePanel.getCheckedSelection();
				for (ListItemTree itemSelected : items) {
					if (treePanel.isLeaf(itemSelected)) {
						String code = itemSelected.getCode();
						String label = itemSelected.getLabel();
						map.put(code, label);
					}
				}

				//System.out.println("map: " + map);

				callView(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, ADAMBoxMaker.sectorsSelected, ADAMBoxMaker.subSectorsSelected, ADAMCurrentVIEW.ADAMVIEW, "applyCountryMultiselection", selectedTab);
				removeMultiSelections();
			}
		};
	}





	private static ListItemTree createTreeItem(String code, String fullLabel, String level) {
		String reducedLabel = "";
		if (fullLabel.length() > 25)
			reducedLabel = fullLabel.substring(0, 24) + "...";
		else
			reducedLabel = fullLabel;

		ListItemTree item = new ListItemTree(code, fullLabel);
		item.setShortLabel(reducedLabel);
		item.setLevel(level);

		return item;
	}

	private static void restoreMultiSelectionVisibility(){
		ADAMVisibilityController.restoreMultiselectionVisibility();
		if (RootPanel.get("MULTISELECTION").getWidgetCount() > 0)
			RootPanel.get("MULTISELECTION").remove(RootPanel.get("MULTISELECTION").getWidget(0));
		RootPanel.get("MULTISELECTION").setStyleName("multiselection-sector-box content");
	}

	public static List<String> buildParentList(final Map<String, String> selectedItems){
		List<String> filter = new ArrayList<String>();
		System.out.println("buildParentList ... ");
		for(String key : selectedItems.keySet()) {
			String code = key;
			String label = selectedItems.get(key);
			System.out.println("Code " + code);
			System.out.println("Label " + label);
			if ( !key.equalsIgnoreCase("GLOBAL")) {	
				if(key.contains("9999")){
					for(String fao: ADAMConstants.faoViewPurposes.keySet()){
						filter.add("%_"+fao);
					}
				} else {
					code = getCode(code);
					filter.add(code);
				}	
			}
		}

		return filter;
	}
}