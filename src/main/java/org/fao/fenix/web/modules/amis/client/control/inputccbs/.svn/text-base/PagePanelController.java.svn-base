package org.fao.fenix.web.modules.amis.client.control.inputccbs;

import java.util.List;

import org.fao.fenix.web.modules.amis.client.view.input.AMISInput;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.BookPanel;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.CCBSParametersPanel;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.CCBSWindow;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.CcbsShowCbsInformationWindow;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.MonthForecast;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.OtherUsesFormField;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.PagePanel;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.PagePanelVariables;
import org.fao.fenix.web.modules.amis.client.view.inputccbs.TestMonthForecast;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISService;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.Book;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.amis.common.vo.ClientCbsDatasetResult;
import org.fao.fenix.web.modules.amis.common.vo.Page;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ButtonBar;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

public class PagePanelController {
	
	private static String lastYearToShow;
	
	public static SelectionChangedListener<AMISCodesModelData> maltListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("Malt selected.getCode() "+ selected.getCode());
					PagePanelVariables.maltFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> biofuelListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("Biofuel selected.getCode() "+ selected.getCode());
					PagePanelVariables.biofuelFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> sweetenersListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("Sweeteners selected.getCode() "+ selected.getCode());
					PagePanelVariables.sweetenersFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> starchListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("Starch selected.getCode() "+ selected.getCode());
					PagePanelVariables.starchFlag = selected.getCode();
				}
			}
		};
	}

	public static SelectionChangedListener<AMISCodesModelData> othersListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("Others selected.getCode() "+ selected.getCode());
					PagePanelVariables.othersFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> otherUseListener(final int otherUsesCombo, final Button mainSave, final int northDim) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("otherUse selected.getCode() "+ selected.getCode());
					PagePanelVariables.otherUsesFormArray[otherUsesCombo] = true; 
					PagePanelVariables.otherUseFlag = selected.getCode();
					updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
				else
				{
					PagePanelVariables.otherUsesFormArray[otherUsesCombo] = false; 
					updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> otherUseFlagListener(final int otherUsesFlagCombo, final Button mainSave, final int northDim) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.otherUsesFormArray[otherUsesFlagCombo] = true; 
					PagePanelVariables.otherUseFlag = selected.getCode();
					updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
				else
				{
					PagePanelVariables.otherUsesFormArray[otherUsesFlagCombo] = false; 
					updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> seedsListener(final int seedsCombo, final Button mainSave, final int northDim) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.otherUsesFormArray[seedsCombo] = true; 
					PagePanelVariables.seedsFlag = selected.getCode();
				//	updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
				else
				{
					PagePanelVariables.otherUsesFormArray[seedsCombo] = false; 
				//	updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> postHarvestLossesListener(final int postHarvestedCombo, final Button mainSave, final int northDim) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.otherUsesFormArray[postHarvestedCombo] = true; 
					PagePanelVariables.postHarvestLossesFlag = selected.getCode();
					//updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
				else
				{
					PagePanelVariables.otherUsesFormArray[postHarvestedCombo] = false; 
					//updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> industrialUseFlagListener(final int industrialUseCombo, final Button mainSave, final int northDim) {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.otherUsesFormArray[industrialUseCombo] = true; 
					PagePanelVariables.industrialUseFlag = selected.getCode();
					//updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
				else
				{
					PagePanelVariables.otherUsesFormArray[industrialUseCombo] = false; 
					//updateSaveButton(mainSave, northDim, PagePanelVariables.otherUsesFormArray);
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> firstProductionListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.firstProductionFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> firstAreaHarvestedListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.firstAreaHarvestedFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> firstYieldListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.firstYieldFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> secondProductionListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.secondProductionFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> secondAreaHarvestedListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.secondAreaHarvestedFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> secondYieldListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.secondYieldFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> thirdProductionListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.thirdProductionFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> thirdAreaHarvestedListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.thirdAreaHarvestedFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> thirdYieldListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.thirdYieldFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> totalProductionListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.totalProductionFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> totalAreaHarvestedListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.totalAreaHarvestedFlag = selected.getCode();
				}
			}
		};
	}
	
	public static SelectionChangedListener<AMISCodesModelData> totalYieldListener() {
		return new SelectionChangedListener<AMISCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<AMISCodesModelData> se) {
				AMISCodesModelData selected = se.getSelectedItem();
				if(selected!=null){
					System.out.println("selected.getCode() "+ selected.getCode());
					PagePanelVariables.totalYieldFlag = selected.getCode();
				}
			}
		};
	}
	
	public static Listener<ComponentEvent> setOtherUsesSeedListener(final OtherUsesFormField otherUsesFormField, final Button mainSave){
		return new Listener<ComponentEvent>() {
			        public void handleEvent(ComponentEvent fe) {
			            System.out.println("keyDown "+fe.getEvent());
			            NumberField search = (NumberField) fe.getSource();
			        	
						System.out.println("Seeds: " + search.getValue());
						if(search != null)
						{
							if(search.getValue() != null)
							{
								if(!search.equals(""))
								{
									PagePanelVariables.otherUsesFormArray[otherUsesFormField.getSeedsTextField()] = true;
									System.out.println("Before button save Seeds: " + search.getValue());													
									PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
								}
								else
								{
									PagePanelVariables.otherUsesFormArray[otherUsesFormField.getSeedsTextField()] = false;
									PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
								}
							}
							else
							{
								PagePanelVariables.otherUsesFormArray[otherUsesFormField.getSeedsTextField()] = false;
								PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
							}
						}
						else
						{
							PagePanelVariables.otherUsesFormArray[otherUsesFormField.getSeedsTextField()] = false;
							PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
						}
			        }
			    };		
	}	
	
//	public static Listener<ComponentEvent> setOtherUsesSeedNoButtonListener(final OtherUsesFormField otherUsesFormField, final NumberField fieldOne, final NumberField fieldTwo, final FieldSet fieldSetNorthOtherUses){
//		return new Listener<ComponentEvent>() {
//			        public void handleEvent(ComponentEvent fe) {
//			            System.out.println("keyDown "+fe.getEvent());
//			            NumberField search = (NumberField) fe.getSource();
//			        	
//						System.out.println("Seeds: " + search.getValue());
//						double value = 0;
//						if(search != null)
//						{
//							if(search.getValue() != null)
//							{
//								if(!search.getValue().equals(""))
//								{
//									value = Double.parseDouble(search.getValue().toString());												
//								}
//								else
//								{
//									value = Double.parseDouble(search.getEmptyText());
//								}
//							}
//							else
//							{
//								value = Double.parseDouble(search.getEmptyText());
//							}
//						}
//						System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  value"+ value);
//						double valueOne = 0;
//						if(fieldOne != null)
//						{
//							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: (fieldOne != null)");
//							if(fieldOne.getValue() != null)
//							{
//								System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: (fieldOne.getValue() != null)");
//								if(!fieldOne.getValue().equals(""))
//								{
//									System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: (!fieldOne.getValue().equals())");
//									valueOne = Double.parseDouble(fieldOne.getValue().toString());												
//								}
//								else
//								{
//									System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: (fieldOne.getValue().equals())");
//									valueOne = Double.parseDouble(fieldOne.getEmptyText());
//								}
//							}
//							else
//							{
//								System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: (fieldOne.getValue() == null)");
//								valueOne = Double.parseDouble(fieldOne.getEmptyText());
//							}
//						}
//						System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  valueOne"+ valueOne);
//						double valueTwo = 0;
//						if(fieldTwo != null)
//						{
//							if(fieldTwo.getValue() != null)
//							{
//								if(!fieldTwo.getValue().equals(""))
//								{
//									valueTwo = Double.parseDouble(fieldTwo.getValue().toString());												
//								}
//								else
//								{
//									valueTwo = Double.parseDouble(fieldTwo.getEmptyText());
//								}
//							}
//							else
//							{
//								valueTwo = Double.parseDouble(fieldTwo.getEmptyText());
//							}
//						}
//						System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  valueTwo"+ valueTwo);
//						double totalValue = value + valueOne + valueTwo;				
//						
//						System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  totalValue"+ totalValue);
//						for(Component component : fieldSetNorthOtherUses.getItems())
//						{
//							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  enter");
//							HorizontalPanel hp = (HorizontalPanel) component;
//							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  enter hp");
//							if(hp!=null)
//							{
//								System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  hp!=null");
//								NumberField nf = (NumberField)hp.getItemByItemId("Other Uses Num Field");
//								if(nf!=null)
//								{
//									System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  nf");
//									nf.setValue(totalValue);
//								}
//							}
//						}
//			        }
//			    };		
//	}	
	
	public static Listener<ComponentEvent> setOtherUsesSeedNoButtonListener(final OtherUsesFormField otherUsesFormField, final FieldSet fieldSetNorthOtherUses){
		return new Listener<ComponentEvent>() {
			        public void handleEvent(ComponentEvent fe) {
			            System.out.println("keyDown "+fe.getEvent());
			            NumberField search = (NumberField) fe.getSource();
			        	
						System.out.println("search.getValue(): " + search.getValue());
						double value = 0;
						double valueOne = 0;
						double valueTwo = 0; 
						if(search != null)
						{
							if(search.getValue() != null)
							{
								value = Double.parseDouble(search.getValue().toString());
//								if(!search.getValue().equals(""))
//								{
//									value = Double.parseDouble(search.getValue().toString());
//								}
//								else
//								{
//									value = Double.parseDouble(search.getEmptyText());
//								}
							}
//							else
//							{
//								value = Double.parseDouble(search.getEmptyText());
//							}
							if(search.getId().equals("OtherUsesSeedsField"))
							{
								//Seeds case
								otherUsesFormField.setSeeds(value);
								System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: Seeds case value"+ value);
							}
							if(search.getId().equals("OtherUsesPostHarvestField"))
							{
								//Post Harvest Losses case
								otherUsesFormField.setPostHarvestLosses(value);
								System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: Post Harvest Losses case value"+ value);
							}
							if(search.getId().equals("Industrial Use"))
							{
								//Industrial Use case
								otherUsesFormField.setIndustrialUse(value);
								System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: Industrial Use case value"+ value);
							}
						}
						
						if(search.getId().equals("OtherUsesSeedsField"))
						{
							//Seeds case
							valueOne = otherUsesFormField.getPostHarvestLosses();
							valueTwo = otherUsesFormField.getIndustrialUse();
							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: Seeds case valueOne"+ valueOne);
							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: Seeds case valueTwo"+ valueTwo);
						}
						if(search.getId().equals("OtherUsesPostHarvestField"))
						{
							//Post Harvest Losses case
							valueOne = otherUsesFormField.getSeeds();
							valueTwo = otherUsesFormField.getIndustrialUse();
							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: Post Harvest Losses valueOne"+ valueOne);
							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: Post Harvest Losses case valueTwo"+ valueTwo);
						}
						if(search.getId().equals("Industrial Use"))
						{
							//Industrial Use case
							valueOne = otherUsesFormField.getSeeds();
							valueTwo = otherUsesFormField.getPostHarvestLosses();
							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: Industrial Use case valueOne"+ valueOne);
							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text: Industrial Use case valueTwo"+ valueTwo);
						}
						System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  value"+ value);
						System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  valueOne"+ valueOne);
						System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  valueTwo"+ valueTwo);
						double totalValue = value + valueOne + valueTwo;				
						
						System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  totalValue"+ totalValue);
						for(Component component : fieldSetNorthOtherUses.getItems())
						{
							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  enter");
							HorizontalPanel hp = (HorizontalPanel) component;
							System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  enter hp");
							if(hp!=null)
							{
								System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  hp!=null");
								NumberField nf = (NumberField)hp.getItemByItemId("Other Uses Num Field");
								if(nf!=null)
								{
									System.out.println("Class: PagePanelController Function: setOtherUsesSeedNoButtonListener Text:  nf");
									nf.setValue(totalValue);
								}
							}
						}
			        }
			    };		
	}	
	
	public static Listener<ComponentEvent> setPostHarvestedLossesSeedListener(final OtherUsesFormField otherUsesFormField, final Button mainSave){
		return new Listener<ComponentEvent>() {
			        public void handleEvent(ComponentEvent fe) {
		                System.out.println("keyDown "+fe.getEvent());
		                NumberField search = (NumberField) fe.getSource();
		            	
						System.out.println("Post Harvest Losses: " + search.getValue());
						
						if(search != null)
						{
							if(search.getValue() != null)
							{
								if(!search.getValue().equals(""))
								{
									PagePanelVariables.otherUsesFormArray[otherUsesFormField.getPostHarvestedTextField()] = true;
									System.out.println("Before button save Post Harvested: " + search.getValue());
									PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
								}
								else
								{
									PagePanelVariables.otherUsesFormArray[otherUsesFormField.getPostHarvestedTextField()] = false;
									PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
								}
							}
							else
							{
								PagePanelVariables.otherUsesFormArray[otherUsesFormField.getPostHarvestedTextField()] = false;
								PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
							}
						}
						else
						{
							PagePanelVariables.otherUsesFormArray[otherUsesFormField.getPostHarvestedTextField()] = false;
							PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
						}
			        }
			    };		
	}	
	
	public static Listener<ComponentEvent> setIndustrialUseLossesSeedListener(final OtherUsesFormField otherUsesFormField, final Button mainSave){
		return new Listener<ComponentEvent>() {
			        public void handleEvent(ComponentEvent fe) {
			        	 System.out.println("keyDown "+fe.getEvent());
			                NumberField search = (NumberField) fe.getSource();
			            	
							System.out.println("Industrial Use: " + search.getValue());

							if(search != null)
							{
								if(search.getValue() != null)
								{
									if(!search.getValue().equals(""))
									{
										PagePanelVariables.otherUsesFormArray[otherUsesFormField.getIndustrialUseTextField()] = true;
										System.out.println("Before button save Industrial Use: " + search.getValue());
										PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
									}
									else
									{
										PagePanelVariables.otherUsesFormArray[otherUsesFormField.getIndustrialUseTextField()] = false;
										PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
									}
								}
								else
								{
									PagePanelVariables.otherUsesFormArray[otherUsesFormField.getIndustrialUseTextField()] = false;
									PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
								}
							}
							else
							{
								PagePanelVariables.otherUsesFormArray[otherUsesFormField.getIndustrialUseTextField()] = false;
								PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
							}
			        }
			    };		
	}	
	
	public static Listener<ComponentEvent> setOtherUsesTotalListener(final OtherUsesFormField otherUsesFormField, final Button mainSave){
		return new Listener<ComponentEvent>() {
			        public void handleEvent(ComponentEvent fe) {
			        	System.out.println("keyDown "+fe.getEvent());
		                NumberField search = (NumberField) fe.getSource();
		            	
						System.out.println("Other Uses: " + search.getValue());
						
						if(search != null)
						{
							if(search.getValue() != null)
							{
								if(!search.getValue().equals(""))
								{
									PagePanelVariables.otherUsesFormArray[otherUsesFormField.getOtherUsesTextField()] = true;
									System.out.println("Before button save Other Uses: " + search.getValue());
									PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
								}
								else
								{
									PagePanelVariables.otherUsesFormArray[otherUsesFormField.getOtherUsesTextField()] = false;
									PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
								}
							}
							else
							{
								PagePanelVariables.otherUsesFormArray[otherUsesFormField.getOtherUsesTextField()] = false;
								PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
							}
						}
						else
						{
							PagePanelVariables.otherUsesFormArray[otherUsesFormField.getOtherUsesTextField()] = false;
							PagePanelController.updateSaveButton(mainSave, otherUsesFormField.getNorthDim(), PagePanelVariables.otherUsesFormArray);
						}
			        }
			    };		
	}	
	
	public static Listener<FieldEvent> lastYearFromComboBox(final ComboBox<AMISCodesModelData> c) {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {			
				if(c.getValue()!=null)
				{
					if(c.getValue().getLabel()!=null)
					{
						lastYearToShow = c.getValue().getLabel();
					}
				}
			}
		};
	}
	
	public static Listener<FieldEvent> disableDefaultYearComboBox(final LayoutContainer lc, final Radio radio) {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {			
				if(radio.getValue())
				{
					ComboBox<AMISCodesModelData> c = (ComboBox<AMISCodesModelData>)lc.getWidget(8);
					c.setValue(CCBS.LAST_YEAR.getAt(0));
					c.disable();
					lastYearToShow = radio.getBoxLabel();
					if(CCBS.CHANGES_IN_THE_GRID)
					{
						CCBS.CHANGES_IN_THE_GRID = false;
						MessageBox.info("Info", "Please save your data before changing the year range, or any modification will be lost", null);
					}
					
				}
			}
		};
	}
	
	public static Listener<FieldEvent> enableDefaultYearComboBox(final LayoutContainer lc, final Radio radio) {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {			
				if(radio.getValue())
				{
					System.out.println("Class:PagePanelController Function:enableDefaultYearComboBox Text: lastYearToShow"+lastYearToShow);
					ComboBox<AMISCodesModelData> c = (ComboBox<AMISCodesModelData>)lc.getWidget(8);
					c.enable();
					//lastYearToShow = CCBS.LAST_YEAR.getAt(0).getLabel();
					if((c!=null) &&(c.getStore()!=null)&& (c.getStore().getAt(0)!= null))
					{
						System.out.println("Class:PagePanelController Function:enableDefaultYearComboBox Text: in if");
						lastYearToShow = c.getStore().getAt(0).getLabel();
					}
					else
					{
						System.out.println("Class:PagePanelController Function:enableDefaultYearComboBox Text: in else");
						lastYearToShow ="";
					}
					System.out.println("Class:PagePanelController Function:enableDefaultYearComboBox Text: lastYearToShow"+lastYearToShow);
					if(CCBS.CHANGES_IN_THE_GRID)
					{
						CCBS.CHANGES_IN_THE_GRID = false;
						MessageBox.info("sInfo", "Please save your data before changing the year range, or any modification will be lost", null);
					}
				}
//				if(marketingCb.getValue())
//				{					 
//					 typeOfYear = "Marketing Year";
//					 calendarCb.setValue(false);
//					 oneTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 twoTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 threeTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 fourTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 fiveTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 sixTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 sevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 eightTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 nineTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 tenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 elevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//						// oneTypeOfYear.setEmptyText(typeOfYear);
////					 twoTypeOfYear.setEmptyText(typeOfYear);
////					 threeTypeOfYear.setEmptyText(typeOfYear);
////					 fourTypeOfYear.setEmptyText(typeOfYear);
////					 fiveTypeOfYear.setEmptyText(typeOfYear);
////					 sixTypeOfYear.setEmptyText(typeOfYear);
////					 sevenTypeOfYear.setEmptyText(typeOfYear);
////					 eightTypeOfYear.setEmptyText(typeOfYear);
////					 nineTypeOfYear.setEmptyText(typeOfYear);
////					 tenTypeOfYear.setEmptyText(typeOfYear);
////					 elevenTypeOfYear.setEmptyText(typeOfYear);
//				//System.out.println("marketingCb Show");
//					fieldSet = new FieldSet();
//				    fieldSet.setHeading("Marketing Year");
//				    fieldSet.setWidth(180);
//				    fieldSet.setCheckboxToggle(true);
//		
//				    FormLayout layout = new FormLayout();
//				    layout.setLabelWidth(35);
//				    fieldSet.setLayout(layout);
//				    
//				    HorizontalPanel hpMarketing = new HorizontalPanel();	    
//				    hpMarketing.setSpacing(3);
//				    HorizontalPanel hpMarketingFrom = new HorizontalPanel();
//				    Html fromMonth = new Html("From");
//				    fromMonth.setWidth(35);
//				    fromMonth.setHeight(10);
//				    hpMarketingFrom.add(fromMonth);
//				    
//				    ListStore<Month> months = new ListStore<Month>();  
//				    months.add(TestMonth.getShortMonth());  
//				    comboMonth = new ComboBox<Month>();  
//					// combo.setEmptyText("Select an year...");  
//				    comboMonth.setDisplayField("month");  
//				    comboMonth.setWidth(55);  
//				    comboMonth.setStore(months);  
//				    comboMonth.setTypeAhead(true);  
//				    comboMonth.setTriggerAction(TriggerAction.ALL); 
//				    hpMarketingFrom.add(comboMonth);
//				    comboMonth.addSelectionChangedListener(selectionMarketingChangedListener);
//				    
//				    hpMarketing.add(hpMarketingFrom);
//				    fieldSet.add(hpMarketing);  
//				    
//				    HorizontalPanel hpMarketingTo = new HorizontalPanel();
//				    Html toMonth = new Html("To");
//				    toMonth.setWidth(20);
//				    toMonth.setHeight(10);
//				    hpMarketingTo.add(toMonth);
//			    
//				    to = new TextField<String>();
//				    to.setFieldLabel("To");
//				    to.setWidth(40);
//				    to.setEnabled(false);
//				    hpMarketingTo.add(to);
//				    hpMarketing.add(hpMarketingTo);
//				    fieldSet.add(hpMarketing);  
//				    vp.add(fieldSet);
//				    vp.getLayout().layout();
//			}
//				else
//				{
//					if(!calendarCb.getValue())
//					{
//					typeOfYear = "";	
//					//System.out.println("marketingCb Hide");
//					 oneTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 twoTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 threeTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 fourTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 fiveTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 sixTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 sevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 eightTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 nineTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 tenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//					 elevenTypeOfYear.setHtml("<b>" + typeOfYear + " </b>");
//						// oneTypeOfYear.setEmptyText(typeOfYear);
////					 twoTypeOfYear.setEmptyText(typeOfYear);
////					 threeTypeOfYear.setEmptyText(typeOfYear);
////					 fourTypeOfYear.setEmptyText(typeOfYear);
////					 fiveTypeOfYear.setEmptyText(typeOfYear);
////					 sixTypeOfYear.setEmptyText(typeOfYear);
////					 sevenTypeOfYear.setEmptyText(typeOfYear);
////					 eightTypeOfYear.setEmptyText(typeOfYear);
////					 nineTypeOfYear.setEmptyText(typeOfYear);
////					 tenTypeOfYear.setEmptyText(typeOfYear);
////					 elevenTypeOfYear.setEmptyText(typeOfYear);
//					}
//					fieldSet.hide();
//				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> cancelGrid(final CCBSWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {				
			//	MessageBox.info("Action", "The data have been saved", null);
//				final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
//				    public void handleEvent(MessageBoxEvent ce) {
//				       Button btn = ce.getButtonClicked();
//				       //Info.display("MessageBox", "The '{0}' button was pressed", btn.getText());
//				       if((btn!=null)&&(btn.getText().equalsIgnoreCase("YES")))
//				       {
//				    	   CCBS.CHANGES_IN_THE_GRID = false;
//				    	   window.getWindow().hide();
//				       }
//				    }
//				    };
                System.out.println("Before msg Confirm 1!! ");
                CCBS.CHANGES_IN_THE_GRID = false;
                window.getWindow().hide();
				//MessageBox.confirm("Confirm", "You are closing the CBS Tool without saving the data. Any modification will be lost. Are you sure you want to do this?", l);
//				public void componentSelected(ButtonEvent ce) {  
//				
//				}  
//				}));  
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> showCountryInformation(final CcbsShowCbsInformationWindow window, final String width,final String height, final CCBSWindow ccbsWindow, final AMISInput amisInput) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("Class: CcbsShowFlagsWindow Function:showCountryInformation Text: SELECTED!!! amisInput.getCountrySelected().getLabel()"+amisInput.getCountrySelected().getLabel()+" height "+height);
			//	window.getWindow().show();
				AMISQueryVO qvo =  new AMISQueryVO();        	   	
 				qvo.setCountryCode(ccbsWindow.getCountryCodeFromName(amisInput.getCountrySelected().getLabel()));
				AMISServiceEntry.getInstance().getCountryInformation(qvo, CCBS.COMMODITY_CODES_CBS,
                        new AsyncCallback<AMISResultVO>()
                        {
                            public void onSuccess(AMISResultVO result)
                            {
                         	   System.out.println("Class: PagePanelController Function: showCountryInformation Text:onsuccess Start" );
                                try
                                {
                            		String marketingYearArray[] = result.getMarketingYearArray();
                            		String cropsNumArray[] = result.getCropsNumArray();
                            		String monthBefore;
                            		//System.out.println("Class: PagePanelController Function: showCountryInformation Text:onsuccess LOOP Start" );
                            		for(int i=0;i<CCBS.COMMODITY_CODES_CBS.size(); i++)
                            		{
                            			//System.out.println("Class: PagePanelController Function: showCountryInformation Text:Before i= "+i+" CCBS.COMMODITY_CODES_CBS element" + CCBS.COMMODITY_CODES_CBS.get(i)+" CCBS.COMMODITY_NAMES_CBS element" + CCBS.COMMODITY_NAMES_CBS.get(i)+" marketingYearArray[i] "+marketingYearArray[i] +" cropsNumArray[i] "+ cropsNumArray[i]);
                            			//To avoid the commodity Population
                            			if(!CCBS.COMMODITY_CODES_CBS.get(i).equals("9"))
                            			{
                            				monthBefore = CCBSParametersPanel.getOneMonthBefore(marketingYearArray[i]);
                                			if(!marketingYearArray[i].equals("Not Available"))
                                			{
                                				marketingYearArray[i] = marketingYearArray[i]+"/"+ monthBefore;
                                			}
                            			}                            			                 			
                            			//System.out.println("Class: PagePanelController Function: showCountryInformation Text:After i= "+i+" CCBS.COMMODITY_CODES_CBS element" + CCBS.COMMODITY_CODES_CBS.get(i)+" CCBS.COMMODITY_NAMES_CBS element" + CCBS.COMMODITY_NAMES_CBS.get(i)+" marketingYearArray[i] "+marketingYearArray[i] +" cropsNumArray[i] "+ cropsNumArray[i]);
                            		}
                            		//System.out.println("Class: PagePanelController Function: showCountryInformation Text:onsuccess LOOP End" );                 							                                           
                            		//Changing startMonth in startMonth/endMonth
                            		//Creating grid
                            		String typeOfGrid = "CountryInformation";
                            		ListStore<AMISCodesModelData> listStore = new ListStore<AMISCodesModelData>();
                            		AMISCodesModelData aMISCodesModelData;
                            		for(int i=0;i<CCBS.COMMODITY_CODES_CBS.size(); i++)
                            		{
                            			//To avoid the commodity Population
                            			if(!CCBS.COMMODITY_CODES_CBS.get(i).equals("9"))
                            			{
		                            		aMISCodesModelData = new AMISCodesModelData();
		                            		//This contains the name of the commodity
		                            		aMISCodesModelData.setCode(CCBS.COMMODITY_NAMES_CBS.get(i));
		                            		//This contains the string start year / end year for this commodity
		                            		aMISCodesModelData.setLabel(marketingYearArray[i]);
		                            		//This contains the name of the number of crops for this commodity
		                            		aMISCodesModelData.setDomain(cropsNumArray[i]);
		                            		//aMISCodesModelData = new AMISCodesModelData(CCBS.COMMODITY_NAMES_CBS.get(i), marketingYearArray[i]);
		                            	//	aMISCodesModelData = new AMISCodesModelData("test", "test");
		                            		listStore.add(aMISCodesModelData);
                            			}
                            		}
                            		System.out.println("Class: PagePanelController Function: showCountryInformation Text:onsuccess listStore.size " +listStore.getCount());
                            		for(int i=0;i<listStore.getCount(); i++)
                            		{
                            			System.out.println("Class: PagePanelController Function: showCountryInformation Text:Before i= "+i+" Code:" + listStore.getAt(i).getCode()+" Label:" + listStore.getAt(i).getLabel()+" Domain:" + listStore.getAt(i).getDomain());
                            			
                            			
                            		}
                            		window.show(width, height, listStore, typeOfGrid);
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }

                            public void onFailure(Throwable caught)
                            {
                                FenixAlert.alert("RPC Failed", "CCBSService.getCCBSData()");
                            }
                        });
				
//				String typeOfGrid = "Flags";
//				window.showFlags(width, height, CCBS.FLAGS, typeOfGrid);
			//	MessageBox.info("Action", "The data have been saved", null);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> showFlags(final CcbsShowCbsInformationWindow window, final String width,final String height) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("Class: CcbsShowFlagsWindow Function:showFlags Text: SELECTED!!!");
			//	window.getWindow().show();
				String typeOfGrid = "Flags";
				window.show(width, height, CCBS.FLAGS, typeOfGrid);
			//	MessageBox.info("Action", "The data have been saved", null);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> showHideFlagsOfTheGrid(final BookPanel bookPanel,final int numPageBookPanel, final CCBSWindow ccbsWindow) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				System.out.println("Class: CcbsShowFlagsWindow Function: showHideFlagsOfTheGrid Text: SELECTED!!!");
				if(bookPanel != null)
				{
					System.out.println("Class: CcbsShowFlagsWindow Function: showHideFlagsOfTheGrid Text: SELECTED!!! if(bookPanel != null)");
					PagePanel.showHideSelectionChangedListener(bookPanel, numPageBookPanel, ccbsWindow);
				}				
			}
		};
	}
	
	public static void showInfoPopup()
	{
				System.out.println("Class: PagePanelController Function: showInfoPopup");
				
				MessageBox.alert("Alert", "This data is for training purposes only. The user can modify the values on the form interactively, but changes will not be saved in the database.", null);
//				Window w = new Window();
//				w.setWidth(300);
//				w.setHeading("Information Form");
//				//w.setTitle("Information Form");
//				FormPanel simple = new FormPanel();  
//				//simple.setHeading("Information Form");  
//				simple.setHeaderVisible(false);
//				simple.setFrame(true);  
//				simple.setWidth(290);  
//				
//				VerticalPanel vp = new VerticalPanel();
//				vp.setSpacing(3);
//				Label label = new Label("Full Data Source Name");
//				label.setStyleName("ccbs-Label");
//				label.setWidth("200px");
//				TextField<String> textField = new TextField<String>();
//				textField.setAllowBlank(false);
//				textField.setWidth(200);
//				vp.add(label);
//				vp.add(textField);
////				TextField<String> fullName = new TextField<String>();  
////				fullName.setFieldLabel("Full Data Source Name");
////				fullName.setAllowBlank(false);  
////				//firstName.getFocusSupport().setPreviousId(simple.getButtonBar().getId()); 
////				simple.add(fullName);  
//				
//				label = new Label("Acronym Data Source Name");
//				label.setStyleName("ccbs-Label");
//				label.setWidth("200px");
//				textField = new TextField<String>();
//				textField.setAllowBlank(false);
//				textField.setWidth(200);
//				vp.add(label);
//				vp.add(textField);
//				
////				TextField<String> email = new TextField<String>();  
////				email.setFieldLabel("Acronym Data Source Name");  
////				email.setAllowBlank(false); 
//				Button b = new Button("Submit");  
//				b.addSelectionListener(saveInformationForm(bookpanel, pagePanelNum, dataSource, country, countryCode, commodity,commodityCode, w));
//				simple.addButton(b);  
//				Button c = new Button("Cancel");
//				c.addSelectionListener(cancelInformationForm(w));
//				simple.addButton(c);  
//				simple.setButtonAlign(HorizontalAlignment.CENTER);  
//				simple.add(vp);
//				
//				FormButtonBinding binding = new FormButtonBinding(simple);  
//				binding.addButton(b);
//				
//				//simple.add(email); 
//				w.add(simple);
//				w.show();
	}
	
	public static String changeMonthString(String month)
	{		
		String changedString = month;
		if(month.equals(CCBS.MONTH_WITH_FLAG[0]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[0];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[1]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[1];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[2]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[2];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[3]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[3];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[4]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[4];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[5]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[5];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[6]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[6];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[7]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[7];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[8]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[8];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[9]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[9];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[10]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[10];
		}
		else if(month.equals(CCBS.MONTH_WITH_FLAG[11]))
		{
			changedString = CCBS.MONTH_WITH_FLAG_LONG[11];
		}
//		else if(month.equals(CCBS.MONTH_WITH_FLAG[12]))
//		{
//			changedString = CCBS.MONTH_WITH_FLAG_LONG[12];
//		}
//		else if(month.equals(CCBS.MONTH_WITH_FLAG[13]))
//		{
//			changedString = CCBS.MONTH_WITH_FLAG_LONG[13];
//		}
		return changedString;
	}
	
	public static String convertMonthToNumber(String month) {
		String marketingYearMonthNum = "1";
//		if(month==null)
//		{
//			marketingYearMonthNum = "0";
//		}
		if((month.equalsIgnoreCase(""))||(month.equalsIgnoreCase(CCBS.N_A_MONTH))||(month.equalsIgnoreCase(CCBS.VALIDATED_MONTH)))
		{
			marketingYearMonthNum = "0";
		}
		else if((month.equalsIgnoreCase("Jan"))||(month.equalsIgnoreCase("January")))
		{
			marketingYearMonthNum = "1";
		}
		else if((month.equalsIgnoreCase("Feb"))||(month.equalsIgnoreCase("February")))
		{
			marketingYearMonthNum = "2";
		}
		else if((month.equalsIgnoreCase("Mar"))||(month.equalsIgnoreCase("March")))
		{
			marketingYearMonthNum = "3";
		}
		else if((month.equalsIgnoreCase("Apr"))||(month.equalsIgnoreCase("April")))
		{
			marketingYearMonthNum = "4";
		}
		else if((month.equalsIgnoreCase("May")))
		{
			marketingYearMonthNum = "5";
		}
		if((month.equalsIgnoreCase("Jun"))||(month.equalsIgnoreCase("June")))
		{
			marketingYearMonthNum = "6";
		}
		else if((month.equalsIgnoreCase("Jul"))||(month.equalsIgnoreCase("July")))
		{
			marketingYearMonthNum = "7";
		}
		else if((month.equalsIgnoreCase("Aug"))||(month.equalsIgnoreCase("August")))
		{
			marketingYearMonthNum = "8";
		}
		else if((month.equalsIgnoreCase("Sep"))||(month.equalsIgnoreCase("September")))
		{
			marketingYearMonthNum = "9";
		}
		else if((month.equalsIgnoreCase("Oct"))||(month.equalsIgnoreCase("October")))
		{
			marketingYearMonthNum = "10";
		}
		if((month.equalsIgnoreCase("Nov"))||(month.equalsIgnoreCase("November")))
		{
			marketingYearMonthNum = "11";
		}
		else if((month.equalsIgnoreCase("Dec"))||(month.equalsIgnoreCase("December")))
		{
			marketingYearMonthNum = "12";
		}
		return marketingYearMonthNum;
	}
	
	static public void updateSaveButton(Button localSave, int northDim,  boolean otherUsesFormArray[])
	{
		System.out.println("Class: PagePanel Function: updateSaveButton Start ");
		int i= 0;
		//Loop on the element of the North Other Uses Form
		for(i=0; i< northDim; i++)
		{
			if(!otherUsesFormArray[i])
			{
				System.out.println("Class: PagePanel Function: updateSaveButton Text: North Before break i= "+i+" otherUsesFormArray[i]= "+otherUsesFormArray[i]);
				break;
			}
		}
		if(i==northDim)
		{
			//All the elements are setted
			System.out.println("Class: PagePanel Function: updateSaveButton Text: North i= "+i+" (i==northDim) ");
			
			localSave.enable();
		}
		else
		{
			//Loop on the element of the South Other Uses Form
			for(i=northDim; i< otherUsesFormArray.length; i++)
			{
				if(!otherUsesFormArray[i])
				{
					System.out.println("Class: PagePanel Function: updateSaveButton Text: South Before break i= "+i+" otherUsesFormArray[i]= "+otherUsesFormArray[i]);
					break;
				}
			}
			if(i==otherUsesFormArray.length)
			{
				System.out.println("Class: PagePanel Function: updateSaveButton Text: South i= "+i+" (i==northDim) ");
				//All the elements are setted
				localSave.enable();
			}
			else
			{
				System.out.println("Class: PagePanel Function: updateSaveButton Text: Else Disable");
				//The selection is not complete
				localSave.disable();
			}
		}
	}
	
	public static ComboBox<AMISCodesModelData> addLastYearCombo()
	{
		ComboBox<AMISCodesModelData> comboLastYear=new ComboBox<AMISCodesModelData>();
		comboLastYear.setDisplayField("label");  
		comboLastYear.setWidth(70);  
		fillLastYearStore();
		comboLastYear.setStore(CCBS.LAST_YEAR);  
		comboLastYear.setTypeAhead(true);  
		comboLastYear.setTriggerAction(TriggerAction.ALL); 
	//	comboLastYear.setValue(CCBS.LAST_YEAR.getAt(0));
	    return comboLastYear;
	}
	
	public static void fillLastYearStore()
	{
		CCBS.LAST_YEAR = new ListStore<AMISCodesModelData>();
		
		for(int i = CCBS.NUMBER_OF_YEARS-1; i< CCBS.YEARS.size(); i++)
		{
			System.out.println("CCBS.YEARS.get(i) = "+ CCBS.YEARS.get(i));
			CCBS.LAST_YEAR.add(new AMISCodesModelData(CCBS.YEARS.get(i), CCBS.YEARS.get(i)));
		}
//		for(int i = 0; i< CCBS.OTHER_YEARS.size(); i++)
//		{
//			System.out.println("CCBS.OTHER_YEARS.get(i) = "+ CCBS.OTHER_YEARS.get(i));
//			CCBS.LAST_YEAR.add(new AMISCodesModelData(CCBS.OTHER_YEARS.get(i), CCBS.OTHER_YEARS.get(i)));
//		}
	}
	
//	public static void fillLastYearStore()
//	{
//		CCBS.LAST_YEAR = new ListStore<AMISCodesModelData>();
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2000/01", "2000/01"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2001/02", "2001/02"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2002/03", "2002/03"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2003/04", "2003/04"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2004/05", "2004/05"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2005/06", "2005/06"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2006/07", "2006/07"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2007/08", "2007/08"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2008/09", "2008/09"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2009/10", "2009/10"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2010/11", "2010/11"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2011/12", "2011/12"));
//		CCBS.LAST_YEAR.add(new AMISCodesModelData("2012/13", "2012/13"));
//	}
	
	public static ComboBox<AMISCodesModelData> addFlagCombo()
	{
		ComboBox<AMISCodesModelData> comboFlag=new ComboBox<AMISCodesModelData>();
		comboFlag.setDisplayField("code");  
		comboFlag.setWidth(70);  
		comboFlag.setStore(CCBS.FLAGS);
		comboFlag.setTypeAhead(true);  
		comboFlag.setTriggerAction(TriggerAction.ALL); 
		//comboFlag.setValue(CCBS.FLAGS.getAt(0));
			
	    return comboFlag;
	}
	
	public static ComboBox<MonthForecast> addFlagCombo2()
	{
		//ComboBox<AMISCodesModelData> comboFlag=new ComboBox<AMISCodesModelData>();
//		comboFlag.setDisplayField("label");  
//		comboFlag.setWidth(70);  
//		comboFlag.setStore(CCBS.FLAGS);
//		comboFlag.setTypeAhead(true);  
//		comboFlag.setTriggerAction(TriggerAction.ALL); 
		//comboFlag.setValue(CCBS.FLAGS.getAt(0));
		
		
		//  ComboBox<MonthForecast> comboFlag=new ComboBox<MonthForecast>();
		  ComboBox comboFlag=new ComboBox();
		//	ListStore<MonthForecast> monthForecast = new ListStore<MonthForecast>();  
			//monthForecast.add(TestMonthForecast.getMonthForecast());   
			// combo.setEmptyText("Select an year...");  
		//	comboFlag.setDisplayField("monthForecast");  
		//	comboFlag.setWidth(75);  
		//	comboFlag.setId("ComboMonth");
		//	comboFlag.setStore(monthForecast);  
			comboFlag.setStore(new ListStore<ModelData>());  
			
	    return comboFlag;
	}
	
	public static int searchFlagComboIndex(String flag)
	{
		int index = 0;
		for(int i=0; i<CCBS.FLAGS.getCount(); i++)
		{
			if(flag.equals(CCBS.FLAGS.getAt(i).getCode()))
			{
				System.out.println("Class: PagePanelController  Function: searchFlagComboIndex Text : flag = "+flag);
				System.out.println("Class: PagePanelController  Function: searchFlagComboIndex Text : CCBS.FLAGS.getAt(i).getCode() = "+CCBS.FLAGS.getAt(i).getCode());
				index = i;
				break;
			}
		}
		if(index == CCBS.FLAGS.getCount())
		{
			System.out.println("Class: PagePanelController  Function: searchFlagComboIndex Text : NOT FOUND = "+flag);
			index = 0;
		}		
		return index;
	}
	
	public static String getLastYearToShow() {
		return lastYearToShow;
	}

	public static void setLastYearToShow(String lastYearToShow) {
		PagePanelController.lastYearToShow = lastYearToShow;
	}

}
