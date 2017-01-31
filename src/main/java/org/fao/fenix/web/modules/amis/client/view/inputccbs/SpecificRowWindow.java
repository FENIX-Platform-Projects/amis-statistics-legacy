package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Set;

import org.fao.fenix.web.modules.amis.client.control.inputccbs.PagePanelController;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.amis.common.vo.ClientCbsDatasetResult;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.Label;

public class SpecificRowWindow extends FenixWindow {
	
	ComboBox<AMISCodesModelData> combo;
	//FieldSet fieldSetNorthOtherUses = new FieldSet();
	
	public void build()
	{
//		System.out.println("Class: CCBSWindow Function:build Text: Build Start");
		setSize(410, 380);
//		setTitle(BabelFish.print().ccbsTool());
//		getWindow().setCollapsible(true);
//		getWindow().setHeading(BabelFish.print().ccbsTool());
//		getWindow().setPosition(30, 30);
//		
	}
	
//	//For Production Form
//	public FieldSet createFieldSetForProduction(ProductionFormField productionFormField, PagePanelVariables ppv)
//	{
//		String fildSetHeading = productionFormField.getFildSetHeading();
//		final FieldSet fieldSet = new FieldSet();  
//		fieldSet.setHeading(productionFormField.getFildSetHeading());  
//		fieldSet.setCollapsible(true); 
//		  
//		FormLayout layout = new FormLayout();  
//		layout.setLabelWidth(productionFormField.getLayoutLabelWidth());  
//		fieldSet.setLayout(layout);  
//		
//		//Production
//		HorizontalPanel hp = new HorizontalPanel();
//		hp.setSpacing(productionFormField.getHorizontalPanelSpacing());
//		Label label = new Label(productionFormField.getPlabelname());
//		label.setStyleName(productionFormField.getLabelStyleName());
//		label.setWidth(productionFormField.getPlabelWidth());
//		hp.add(label);
//		NumberField numberField;
//		if(productionFormField.isWithnumberfield())
//		{
//			numberField = new NumberField();
//			numberField.setTitle(productionFormField.getPnumberFieldTitle());
//			numberField.setId(productionFormField.getPnumberFieldId());
//			numberField.setAllowBlank(productionFormField.getNumberFieldAllow());
//			numberField.setWidth(productionFormField.getNumberFieldWidth());
//			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: fildSetHeading "+ fildSetHeading);
//			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.firstProductionFound "+ ppv.firstProductionFound);
//			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.secondProductionFound "+ ppv.secondProductionFound);
//			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.thirdProductionFound "+ ppv.thirdProductionFound);
//			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.firstNumberFieldProdValueFound "+ ppv.firstNumberFieldProdValueFound);
//			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.secondNumberFieldProdValueFound "+ ppv.secondNumberFieldProdValueFound);
//			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.thirdNumberFieldProdValueFound "+ ppv.thirdNumberFieldProdValueFound);
//			if(fildSetHeading.equals("First Crop"))
//			{
//				if(ppv.firstProductionFound)
//				{
//					numberField.setValue(ppv.firstNumberFieldProdValueFound);
//				}
//			}
//			if(fildSetHeading.equals("Second Crop"))
//			{
//				if(ppv.secondProductionFound)
//				{
//					numberField.setValue(ppv.secondNumberFieldProdValueFound);
//				}
//			}
//			if(fildSetHeading.equals("Third Crop"))
//			{
//				if(ppv.thirdProductionFound)
//				{
//					numberField.setValue(ppv.thirdNumberFieldProdValueFound);
//				}
//			}
//			hp.add(numberField);	
//		}
//		combo = PagePanelController.addFlagCombo();
//		combo.setAllowBlank(productionFormField.getComboAllow());
//		if(productionFormField.getPcomboListener().equals("firstProductionListener"))
//		{
//		 	combo.addSelectionChangedListener(PagePanelController.firstProductionListener());
//		 	if(fildSetHeading.equals("First Crop"))
//			{
//				if(ppv.firstProductionFound)
//				{
//					PagePanelVariables.firstProductionFlag = ppv.firstComboProdValueFound;
//				}
//				else
//				{
//					//PagePanelVariables.firstProductionFlag = CCBS.FLAGS.getAt(2).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 	//	PagePanelVariables.firstProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}	 
//		}
//		else if(productionFormField.getPcomboListener().equals("secondProductionListener"))
//		{
//		 	 combo.addSelectionChangedListener(PagePanelController.secondProductionListener());
//		 	if(fildSetHeading.equals("Second Crop"))
//			{
//				if(ppv.secondProductionFound)
//				{
//					PagePanelVariables.secondProductionFlag = ppv.secondComboProdValueFound;
//				}
//				else
//				{
//					//PagePanelVariables.secondProductionFlag = CCBS.FLAGS.getAt(2).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 //		PagePanelVariables.secondProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}
//		}
//		else if(productionFormField.getPcomboListener().equals("thirdProductionListener"))
//		{
//		 	combo.addSelectionChangedListener(PagePanelController.thirdProductionListener());
//		 	if(fildSetHeading.equals("Third Crop"))
//			{
//				if(ppv.thirdProductionFound)
//				{
//					PagePanelVariables.thirdProductionFlag = ppv.thirdComboProdValueFound;
//				}
//				else
//				{
//					//PagePanelVariables.thirdProductionFlag = CCBS.FLAGS.getAt(2).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 	//	PagePanelVariables.thirdProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}
//		}
//		else if(productionFormField.getPcomboListener().equals("totalProductionListener"))
//		{
//		 	combo.addSelectionChangedListener(PagePanelController.totalProductionListener());
//		 	if(fildSetHeading.equals("Total Annotations"))
//			{
//				if(ppv.totalProductionFound)
//				{
//					PagePanelVariables.totalProductionFlag = ppv.totalComboProdValueFound;
//				}
//				else
//				{
//			//		PagePanelVariables.totalProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 	//	PagePanelVariables.totalProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}
//		}
//		if(fildSetHeading.equals("First Crop"))
//		{
//			if(ppv.firstProductionFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.firstComboProdValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		if(fildSetHeading.equals("Second Crop"))
//		{
//			if(ppv.secondProductionFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.secondComboProdValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		if(fildSetHeading.equals("Third Crop"))
//		{
//			if(ppv.thirdProductionFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.thirdComboProdValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		if(fildSetHeading.equals("Total Annotations"))
//		{
//			if(ppv.totalProductionFound)
//			{
//				System.out.println("Class:PagePanel Function: grid.addListener(Events.OnClick ppv.totalComboProdValueFound = "+ppv.totalComboProdValueFound);
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.totalComboProdValueFound);
//				//System.out.println("Class:PagePanel Function: grid.addListener(Events.OnClick object.getLabel() = "+object.getLabel());
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		hp.add(combo);
//		fieldSet.add(hp); 
//		
//		//Area Harvested
//		hp = new HorizontalPanel();
//		hp.setSpacing(5);
//		label = new Label(productionFormField.getAlabelname());
//		label.setStyleName(productionFormField.getLabelStyleName());
//		label.setWidth(productionFormField.getAlabelWidth());
//		hp.add(label);
//		if(productionFormField.isWithnumberfield())
//		{
//			numberField = new NumberField();
//			numberField.setId(productionFormField.getAnumberFieldId());
//			numberField.setTitle(productionFormField.getAnumberFieldTitle());
//			numberField.setAllowBlank(productionFormField.getNumberFieldAllow());
//			numberField.setWidth(productionFormField.getNumberFieldWidth());
//			if(fildSetHeading.equals("First Crop"))
//			{
//				if(ppv.firstAreaHarvestedFound)
//				{
//					numberField.setValue(ppv.firstNumberFieldAreaValueFound);
//				}
//			}
//			if(fildSetHeading.equals("Second Crop"))
//			{
//				if(ppv.secondAreaHarvestedFound)
//				{
//					numberField.setValue(ppv.secondNumberFieldAreaValueFound);
//				}
//			}
//			if(fildSetHeading.equals("Third Crop"))
//			{
//				if(ppv.thirdAreaHarvestedFound)
//				{
//					numberField.setValue(ppv.thirdNumberFieldAreaValueFound);
//				}
//			}
//			hp.add(numberField);
//		}
//		combo = PagePanelController.addFlagCombo();
//		combo.setAllowBlank(productionFormField.getComboAllow());
//		if(productionFormField.getAcomboListener().equals("firstAreaHarvestedListener"))
//		{
//		 	combo.addSelectionChangedListener(PagePanelController.firstAreaHarvestedListener());
//		 	if(fildSetHeading.equals("First Crop"))
//			{
//				if(ppv.firstAreaHarvestedFound)
//				{
//					PagePanelVariables.firstAreaHarvestedFlag = ppv.firstComboAreaValueFound;
//				}
//				else
//				{
//					// PagePanelVariables.firstAreaHarvestedFlag = CCBS.FLAGS.getAt(2).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 	//	 PagePanelVariables.firstAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}
//		}
//		else if(productionFormField.getAcomboListener().equals("secondAreaHarvestedListener"))
//		{
//		  	combo.addSelectionChangedListener(PagePanelController.secondAreaHarvestedListener());
//		 	if(fildSetHeading.equals("Second Crop"))
//			{
//				if(ppv.secondAreaHarvestedFound)
//				{
//					PagePanelVariables.secondAreaHarvestedFlag = ppv.secondComboAreaValueFound;
//				}
//				else
//				{
//				//	PagePanelVariables.secondAreaHarvestedFlag = CCBS.FLAGS.getAt(2).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 //		PagePanelVariables.secondAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	} 
//		}
//		else if(productionFormField.getAcomboListener().equals("thirdAreaHarvestedListener"))
//		{
//		  	 combo.addSelectionChangedListener(PagePanelController.thirdAreaHarvestedListener());
//			 	if(fildSetHeading.equals("Third Crop"))
//				{
//					if(ppv.thirdAreaHarvestedFound)
//					{
//						PagePanelVariables.thirdAreaHarvestedFlag = ppv.thirdComboAreaValueFound;
//					}
//					else
//					{
//					//	PagePanelVariables.thirdAreaHarvestedFlag = CCBS.FLAGS.getAt(2).getLabel();
//					}
//				}
//			 	else
//			 	{
//			// 		PagePanelVariables.thirdAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
//			 	}
//		}
//		else if(productionFormField.getAcomboListener().equals("totalAreaHarvestedListener"))
//		{
//		  	 combo.addSelectionChangedListener(PagePanelController.totalAreaHarvestedListener());
//		 	if(fildSetHeading.equals("Total Annotations"))
//			{
//				if(ppv.totalAreaHarvestedFound)
//				{
//					PagePanelVariables.totalAreaHarvestedFlag = ppv.totalComboAreaValueFound;
//				}
//				else
//				{
//			//		PagePanelVariables.totalAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 	//	PagePanelVariables.totalAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}
//		}
//		if(fildSetHeading.equals("First Crop"))
//		{
//			if(ppv.firstAreaHarvestedFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.firstComboAreaValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		if(fildSetHeading.equals("Second Crop"))
//		{
//			if(ppv.secondAreaHarvestedFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.secondComboAreaValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		if(fildSetHeading.equals("Third Crop"))
//		{
//			if(ppv.thirdAreaHarvestedFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.thirdComboAreaValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		if(fildSetHeading.equals("Total Annotations"))
//		{
//			if(ppv.totalAreaHarvestedFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.totalComboAreaValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		hp.add(combo);
//		fieldSet.add(hp);  
//		
//		//Yield
//		hp = new HorizontalPanel();
//		hp.setSpacing(5);
//		label = new Label(productionFormField.getYlabelname());
//		label.setStyleName(productionFormField.getLabelStyleName());
//		label.setWidth(productionFormField.getYlabelWidth());
//		hp.add(label);
//		if(productionFormField.isWithnumberfield())
//		{
//			numberField = new NumberField();
//			numberField.setId(productionFormField.getYnumberFieldId());
//			numberField.setTitle(productionFormField.getYnumberFieldTitle());
//			numberField.setAllowBlank(productionFormField.getNumberFieldAllow());
//			numberField.setWidth(productionFormField.getNumberFieldWidth());
//			if(fildSetHeading.equals("First Crop"))
//			{
//				if(ppv.firstYieldFound)
//				{
//					numberField.setValue(ppv.firstNumberFieldYieldValueFound);
//				}
//			}
//			if(fildSetHeading.equals("Second Crop"))
//			{
//				if(ppv.secondYieldFound)
//				{
//					numberField.setValue(ppv.secondNumberFieldYieldValueFound);
//				}
//			}
//			if(fildSetHeading.equals("Third Crop"))
//			{
//				if(ppv.thirdYieldFound)
//				{
//					numberField.setValue(ppv.thirdNumberFieldYieldValueFound);
//				}
//			}
//			hp.add(numberField);	
//		}
//		combo = PagePanelController.addFlagCombo();
//		combo.setAllowBlank(productionFormField.getComboAllow());
//		if(productionFormField.getYcomboListener().equals("firstYieldListener"))
//		{
//		  	combo.addSelectionChangedListener(PagePanelController.firstYieldListener());
//		  	if(fildSetHeading.equals("First Crop"))
//			{
//				if(ppv.firstYieldFound)
//				{
//					PagePanelVariables.firstYieldFlag = ppv.firstComboFieldYieldValueFound;
//				}
//				else
//				{
//					// PagePanelVariables.firstYieldFlag = CCBS.FLAGS.getAt(2).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 //		 PagePanelVariables.firstYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}
//		}
//		else if(productionFormField.getYcomboListener().equals("secondYieldListener"))
//		{
//		 	combo.addSelectionChangedListener(PagePanelController.secondYieldListener());
//		 	if(fildSetHeading.equals("Second Crop"))
//			{
//				if(ppv.secondYieldFound)
//				{
//					PagePanelVariables.secondYieldFlag = ppv.secondComboYieldValueFound;
//				}
//				else
//				{
//					//PagePanelVariables.secondYieldFlag = CCBS.FLAGS.getAt(2).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 	//	PagePanelVariables.secondYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}
//		}
//		else if(productionFormField.getYcomboListener().equals("thirdYieldListener"))
//		{
//		 	 combo.addSelectionChangedListener(PagePanelController.thirdYieldListener());
//		 	if(fildSetHeading.equals("Third Crop"))
//			{
//				if(ppv.thirdYieldFound)
//				{
//					 PagePanelVariables.thirdYieldFlag = ppv.thirdComboYieldValueFound;
//				}
//				else
//				{
//					// PagePanelVariables.thirdYieldFlag = CCBS.FLAGS.getAt(2).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 		// PagePanelVariables.thirdYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}
//		}
//		else if(productionFormField.getYcomboListener().equals("totalYieldListener"))
//		{
//		 	combo.addSelectionChangedListener(PagePanelController.totalYieldListener());
//		 	if(fildSetHeading.equals("Total Annotations"))
//			{
//				if(ppv.totalYieldFound)
//				{
//					PagePanelVariables.totalYieldFlag = ppv.totalComboYieldValueFound;
//				}
//				else
//				{
//				//	PagePanelVariables.totalYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
//				}
//			}
//		 	else
//		 	{
//		 	//	PagePanelVariables.totalYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
//		 	}
//		}
//		System.out.println("Class: SpecificRowWindow Text: After PagePanelVariables.totalYieldFlag ="+PagePanelVariables.totalYieldFlag);
//		hp.add(combo);
//		if(fildSetHeading.equals("First Crop"))
//		{
//			if(ppv.firstYieldFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.firstComboFieldYieldValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		if(fildSetHeading.equals("Second Crop"))
//		{
//			if(ppv.secondYieldFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.secondComboYieldValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		if(fildSetHeading.equals("Third Crop"))
//		{
//			if(ppv.thirdYieldFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.thirdComboYieldValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		if(fildSetHeading.equals("Total Annotations"))
//		{
//			if(ppv.totalYieldFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.totalComboYieldValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
//		}
//		fieldSet.add(hp); 
//		
//		return fieldSet;
//	}
	
	//For Production Form
	public FieldSet createFieldSetForProduction(ProductionFormField productionFormField, PagePanelVariables ppv)
	{
		String fildSetHeading = productionFormField.getFildSetHeading();
		final FieldSet fieldSet = new FieldSet();  
		fieldSet.setHeading(productionFormField.getFildSetHeading());  
		fieldSet.setCollapsible(true); 
		  
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(productionFormField.getLayoutLabelWidth());  
		fieldSet.setLayout(layout);  
		
		//Production
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(productionFormField.getHorizontalPanelSpacing());
		Label label = new Label(productionFormField.getPlabelname());
		label.setStyleName(productionFormField.getLabelStyleName());
		label.setWidth(productionFormField.getPlabelWidth());
		hp.add(label);
		NumberField numberField;
		if(productionFormField.isWithnumberfield())
		{
			numberField = new NumberField();
			numberField.setTitle(productionFormField.getPnumberFieldTitle());
			numberField.setId(productionFormField.getPnumberFieldId());
			numberField.setAllowBlank(productionFormField.getNumberFieldAllow());
			numberField.setWidth(productionFormField.getNumberFieldWidth());
			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: fildSetHeading "+ fildSetHeading);
			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.firstProductionFound "+ ppv.firstProductionFound);
			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.secondProductionFound "+ ppv.secondProductionFound);
			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.thirdProductionFound "+ ppv.thirdProductionFound);
			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.firstNumberFieldProdValueFound "+ ppv.firstNumberFieldProdValueFound);
			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.secondNumberFieldProdValueFound "+ ppv.secondNumberFieldProdValueFound);
			System.out.println("Class: SpecificRowWindow Function: createFieldSetForProduction Text: ppv.thirdNumberFieldProdValueFound "+ ppv.thirdNumberFieldProdValueFound);
			if(fildSetHeading.equals("First Crop"))
			{
				if(ppv.firstProductionFound)
				{
					numberField.setValue(ppv.firstNumberFieldProdValueFound);
				}
			}
			if(fildSetHeading.equals("Second Crop"))
			{
				if(ppv.secondProductionFound)
				{
					numberField.setValue(ppv.secondNumberFieldProdValueFound);
				}
			}
			if(fildSetHeading.equals("Third Crop"))
			{
				if(ppv.thirdProductionFound)
				{
					numberField.setValue(ppv.thirdNumberFieldProdValueFound);
				}
			}
			hp.add(numberField);	
		}
		combo = PagePanelController.addFlagCombo();
		combo.setAllowBlank(productionFormField.getComboAllow());
		if(productionFormField.getPcomboListener().equals("firstProductionListener"))
		{
		 	combo.addSelectionChangedListener(PagePanelController.firstProductionListener());
		 	if(fildSetHeading.equals("First Crop"))
			{
				if(ppv.firstProductionFound)
				{
					PagePanelVariables.firstProductionFlag = ppv.firstComboProdValueFound;
				}
				else
				{
					//PagePanelVariables.firstProductionFlag = CCBS.FLAGS.getAt(2).getLabel();
				}
			}
		 	else
		 	{
		 	//	PagePanelVariables.firstProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}	 
		}
		else if(productionFormField.getPcomboListener().equals("secondProductionListener"))
		{
		 	 combo.addSelectionChangedListener(PagePanelController.secondProductionListener());
		 	if(fildSetHeading.equals("Second Crop"))
			{
				if(ppv.secondProductionFound)
				{
					PagePanelVariables.secondProductionFlag = ppv.secondComboProdValueFound;
				}
				else
				{
					//PagePanelVariables.secondProductionFlag = CCBS.FLAGS.getAt(2).getLabel();
				}
			}
		 	else
		 	{
		 //		PagePanelVariables.secondProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}
		}
		else if(productionFormField.getPcomboListener().equals("thirdProductionListener"))
		{
		 	combo.addSelectionChangedListener(PagePanelController.thirdProductionListener());
		 	if(fildSetHeading.equals("Third Crop"))
			{
				if(ppv.thirdProductionFound)
				{
					PagePanelVariables.thirdProductionFlag = ppv.thirdComboProdValueFound;
				}
				else
				{
					//PagePanelVariables.thirdProductionFlag = CCBS.FLAGS.getAt(2).getLabel();
				}
			}
		 	else
		 	{
		 	//	PagePanelVariables.thirdProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}
		}
		else if(productionFormField.getPcomboListener().equals("totalProductionListener"))
		{
		 	combo.addSelectionChangedListener(PagePanelController.totalProductionListener());
		 	if(fildSetHeading.equals("Total Annotations"))
			{
				if(ppv.totalProductionFound)
				{
					PagePanelVariables.totalProductionFlag = ppv.totalComboProdValueFound;
				}
				else
				{
			//		PagePanelVariables.totalProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
				}
			}
		 	else
		 	{
		 	//	PagePanelVariables.totalProductionFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}
		}
		if(fildSetHeading.equals("First Crop"))
		{
			AMISCodesModelData object = getObjectByFlagCombo(ppv.firstProductionFlag);
			if(object!=null)
			{
				combo.setValue(object);
			}
//			AMISCodesModelData object=null;
//			if(ppv.firstProductionFound)
//			{
//				object = getObjectByFlagCombo(ppv.firstComboProdValueFound);
//			}
//			else
//			{
//				object = getObjectByFlagCombo(ppv.firstProductionFlag);
//			}
//			if(object!=null)
//			{
//				combo.setValue(object);
//			}
		}
		if(fildSetHeading.equals("Second Crop"))
		{
//			if(ppv.secondProductionFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.secondComboProdValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
			AMISCodesModelData object = getObjectByFlagCombo(ppv.secondProductionFlag);
			if(object!=null)
			{
				combo.setValue(object);
			}
		}
		if(fildSetHeading.equals("Third Crop"))
		{
//			if(ppv.thirdProductionFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.thirdComboProdValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
			AMISCodesModelData object = getObjectByFlagCombo(ppv.thirdProductionFlag);
			if(object!=null)
			{
				combo.setValue(object);
			}
		}
		if(fildSetHeading.equals("Total Annotations"))
		{
			if(ppv.totalProductionFound)
			{
				System.out.println("Class:PagePanel Function: grid.addListener(Events.OnClick ppv.totalComboProdValueFound = "+ppv.totalComboProdValueFound);
				AMISCodesModelData object = getObjectByFlagCombo(ppv.totalComboProdValueFound);
				//System.out.println("Class:PagePanel Function: grid.addListener(Events.OnClick object.getLabel() = "+object.getLabel());
				if(object!=null)
				{
					combo.setValue(object);
				}
			}
		}
		hp.add(combo);
		fieldSet.add(hp); 
		
		//Area Harvested
		hp = new HorizontalPanel();
		hp.setSpacing(5);
		label = new Label(productionFormField.getAlabelname());
		label.setStyleName(productionFormField.getLabelStyleName());
		label.setWidth(productionFormField.getAlabelWidth());
		hp.add(label);
		if(productionFormField.isWithnumberfield())
		{
			numberField = new NumberField();
			numberField.setId(productionFormField.getAnumberFieldId());
			numberField.setTitle(productionFormField.getAnumberFieldTitle());
			numberField.setAllowBlank(productionFormField.getNumberFieldAllow());
			numberField.setWidth(productionFormField.getNumberFieldWidth());
			if(fildSetHeading.equals("First Crop"))
			{
				if(ppv.firstAreaHarvestedFound)
				{
					numberField.setValue(ppv.firstNumberFieldAreaValueFound);
				}
			}
			if(fildSetHeading.equals("Second Crop"))
			{
				if(ppv.secondAreaHarvestedFound)
				{
					numberField.setValue(ppv.secondNumberFieldAreaValueFound);
				}
			}
			if(fildSetHeading.equals("Third Crop"))
			{
				if(ppv.thirdAreaHarvestedFound)
				{
					numberField.setValue(ppv.thirdNumberFieldAreaValueFound);
				}
			}
			hp.add(numberField);
		}
		combo = PagePanelController.addFlagCombo();
		combo.setAllowBlank(productionFormField.getComboAllow());
		if(productionFormField.getAcomboListener().equals("firstAreaHarvestedListener"))
		{
		 	combo.addSelectionChangedListener(PagePanelController.firstAreaHarvestedListener());
		 	if(fildSetHeading.equals("First Crop"))
			{
				if(ppv.firstAreaHarvestedFound)
				{
					PagePanelVariables.firstAreaHarvestedFlag = ppv.firstComboAreaValueFound;
				}
				else
				{
					// PagePanelVariables.firstAreaHarvestedFlag = CCBS.FLAGS.getAt(2).getLabel();
				}
			}
		 	else
		 	{
		 	//	 PagePanelVariables.firstAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}
		}
		else if(productionFormField.getAcomboListener().equals("secondAreaHarvestedListener"))
		{
		  	combo.addSelectionChangedListener(PagePanelController.secondAreaHarvestedListener());
		 	if(fildSetHeading.equals("Second Crop"))
			{
				if(ppv.secondAreaHarvestedFound)
				{
					PagePanelVariables.secondAreaHarvestedFlag = ppv.secondComboAreaValueFound;
				}
				else
				{
				//	PagePanelVariables.secondAreaHarvestedFlag = CCBS.FLAGS.getAt(2).getLabel();
				}
			}
		 	else
		 	{
		 //		PagePanelVariables.secondAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	} 
		}
		else if(productionFormField.getAcomboListener().equals("thirdAreaHarvestedListener"))
		{
		  	 combo.addSelectionChangedListener(PagePanelController.thirdAreaHarvestedListener());
			 	if(fildSetHeading.equals("Third Crop"))
				{
					if(ppv.thirdAreaHarvestedFound)
					{
						PagePanelVariables.thirdAreaHarvestedFlag = ppv.thirdComboAreaValueFound;
					}
					else
					{
					//	PagePanelVariables.thirdAreaHarvestedFlag = CCBS.FLAGS.getAt(2).getLabel();
					}
				}
			 	else
			 	{
			// 		PagePanelVariables.thirdAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
			 	}
		}
		else if(productionFormField.getAcomboListener().equals("totalAreaHarvestedListener"))
		{
		  	 combo.addSelectionChangedListener(PagePanelController.totalAreaHarvestedListener());
		 	if(fildSetHeading.equals("Total Annotations"))
			{
				if(ppv.totalAreaHarvestedFound)
				{
					PagePanelVariables.totalAreaHarvestedFlag = ppv.totalComboAreaValueFound;
				}
				else
				{
			//		PagePanelVariables.totalAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
				}
			}
		 	else
		 	{
		 	//	PagePanelVariables.totalAreaHarvestedFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}
		}
		if(fildSetHeading.equals("First Crop"))
		{
//			if(ppv.firstAreaHarvestedFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.firstComboAreaValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
			AMISCodesModelData object = getObjectByFlagCombo(ppv.firstAreaHarvestedFlag);
			if(object!=null)
			{
				combo.setValue(object);
			}
		}
		if(fildSetHeading.equals("Second Crop"))
		{
//			if(ppv.secondAreaHarvestedFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.secondComboAreaValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
			AMISCodesModelData object = getObjectByFlagCombo(ppv.secondAreaHarvestedFlag);
			if(object!=null)
			{
				combo.setValue(object);
			}
		}
		if(fildSetHeading.equals("Third Crop"))
		{
//			if(ppv.thirdAreaHarvestedFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.thirdComboAreaValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
			AMISCodesModelData object = getObjectByFlagCombo(ppv.thirdAreaHarvestedFlag);
			if(object!=null)
			{
				combo.setValue(object);
			}
		}
		if(fildSetHeading.equals("Total Annotations"))
		{
			if(ppv.totalAreaHarvestedFound)
			{
				AMISCodesModelData object = getObjectByFlagCombo(ppv.totalComboAreaValueFound);
				if(object!=null)
				{
					combo.setValue(object);
				}
			}
		}
		hp.add(combo);
		fieldSet.add(hp);  
		
		//Yield
		hp = new HorizontalPanel();
		hp.setSpacing(5);
		label = new Label(productionFormField.getYlabelname());
		label.setStyleName(productionFormField.getLabelStyleName());
		label.setWidth(productionFormField.getYlabelWidth());
		hp.add(label);
		if(productionFormField.isWithnumberfield())
		{
			numberField = new NumberField();
			numberField.setId(productionFormField.getYnumberFieldId());
			numberField.setTitle(productionFormField.getYnumberFieldTitle());
			numberField.setAllowBlank(productionFormField.getNumberFieldAllow());
			numberField.setWidth(productionFormField.getNumberFieldWidth());
			if(fildSetHeading.equals("First Crop"))
			{
				if(ppv.firstYieldFound)
				{
					numberField.setValue(ppv.firstNumberFieldYieldValueFound);
				}
			}
			if(fildSetHeading.equals("Second Crop"))
			{
				if(ppv.secondYieldFound)
				{
					numberField.setValue(ppv.secondNumberFieldYieldValueFound);
				}
			}
			if(fildSetHeading.equals("Third Crop"))
			{
				if(ppv.thirdYieldFound)
				{
					numberField.setValue(ppv.thirdNumberFieldYieldValueFound);
				}
			}
			hp.add(numberField);	
		}
		combo = PagePanelController.addFlagCombo();
		combo.setAllowBlank(productionFormField.getComboAllow());
		if(productionFormField.getYcomboListener().equals("firstYieldListener"))
		{
		  	combo.addSelectionChangedListener(PagePanelController.firstYieldListener());
		  	if(fildSetHeading.equals("First Crop"))
			{
				if(ppv.firstYieldFound)
				{
					PagePanelVariables.firstYieldFlag = ppv.firstComboFieldYieldValueFound;
				}
				else
				{
					// PagePanelVariables.firstYieldFlag = CCBS.FLAGS.getAt(2).getLabel();
				}
			}
		 	else
		 	{
		 //		 PagePanelVariables.firstYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}
		}
		else if(productionFormField.getYcomboListener().equals("secondYieldListener"))
		{
		 	combo.addSelectionChangedListener(PagePanelController.secondYieldListener());
		 	if(fildSetHeading.equals("Second Crop"))
			{
				if(ppv.secondYieldFound)
				{
					PagePanelVariables.secondYieldFlag = ppv.secondComboYieldValueFound;
				}
				else
				{
					//PagePanelVariables.secondYieldFlag = CCBS.FLAGS.getAt(2).getLabel();
				}
			}
		 	else
		 	{
		 	//	PagePanelVariables.secondYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}
		}
		else if(productionFormField.getYcomboListener().equals("thirdYieldListener"))
		{
		 	 combo.addSelectionChangedListener(PagePanelController.thirdYieldListener());
		 	if(fildSetHeading.equals("Third Crop"))
			{
				if(ppv.thirdYieldFound)
				{
					 PagePanelVariables.thirdYieldFlag = ppv.thirdComboYieldValueFound;
				}
				else
				{
					// PagePanelVariables.thirdYieldFlag = CCBS.FLAGS.getAt(2).getLabel();
				}
			}
		 	else
		 	{
		 		// PagePanelVariables.thirdYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}
		}
		else if(productionFormField.getYcomboListener().equals("totalYieldListener"))
		{
		 	combo.addSelectionChangedListener(PagePanelController.totalYieldListener());
		 	if(fildSetHeading.equals("Total Annotations"))
			{
				if(ppv.totalYieldFound)
				{
					PagePanelVariables.totalYieldFlag = ppv.totalComboYieldValueFound;
				}
				else
				{
				//	PagePanelVariables.totalYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
				}
			}
		 	else
		 	{
		 	//	PagePanelVariables.totalYieldFlag = CCBS.FLAGS.getAt(0).getLabel();
		 	}
		}
		System.out.println("Class: SpecificRowWindow Text: After PagePanelVariables.totalYieldFlag ="+PagePanelVariables.totalYieldFlag);
		hp.add(combo);
		if(fildSetHeading.equals("First Crop"))
		{
//			if(ppv.firstYieldFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.firstComboFieldYieldValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
			AMISCodesModelData object = getObjectByFlagCombo(ppv.firstYieldFlag);
			if(object!=null)
			{
				combo.setValue(object);
			}
		}
		if(fildSetHeading.equals("Second Crop"))
		{
//			if(ppv.secondYieldFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.secondComboYieldValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
			AMISCodesModelData object = getObjectByFlagCombo(ppv.secondYieldFlag);
			if(object!=null)
			{
				combo.setValue(object);
			}
		}
		if(fildSetHeading.equals("Third Crop"))
		{
//			if(ppv.thirdYieldFound)
//			{
//				AMISCodesModelData object = getObjectByFlagCombo(ppv.thirdComboYieldValueFound);
//				if(object!=null)
//				{
//					combo.setValue(object);
//				}
//			}
			AMISCodesModelData object = getObjectByFlagCombo(ppv.thirdYieldFlag);
			if(object!=null)
			{
				combo.setValue(object);
			}
		}
		if(fildSetHeading.equals("Total Annotations"))
		{
			if(ppv.totalYieldFound)
			{
				AMISCodesModelData object = getObjectByFlagCombo(ppv.totalComboYieldValueFound);
				if(object!=null)
				{
					combo.setValue(object);
				}
			}
		}
		fieldSet.add(hp); 
		
		return fieldSet;
	}
	
	//Calculating total production, total area harvested and total yield with flags
	//public ProductionFormField getFieldValueForProduction(FieldSet fieldSetOne, FieldSet fieldSetTwo, FieldSet fieldSetThree, String cropsNum, int rowNum, int colNum, String dataSource, String countryCode, String countryName, String commodityCode, String commodityName, PageDecoration decoration)
	public ProductionFormField getFieldValueForProduction(FieldSet fieldSetOne, FieldSet fieldSetTwo, FieldSet fieldSetThree, int rowNum, int colNum, PageDecoration decoration)
	{
		
		String cropsNum = decoration.getCropNum(); 
		String dataSource = decoration.getDataSource();
		String countryCode = decoration.getCountryCode();
		String countryName = decoration.getCountryName();
		String commodityCode = decoration.getCommodityCode();
		String commodityName = decoration.getCommodityName();
		int intCropsNum = Integer.parseInt(cropsNum);
		System.out.println("Class: SpecificRowWindow Function Text:getFieldValueForProduction intCropsNum "+intCropsNum);
		boolean productionFlag[] = new boolean[3];
		boolean areaHarvestedFlag[] = new boolean[3];
		boolean yieldFlag[] = new boolean[3];
		double production = 0;
		double areaHarvested = 0;
		double yield = 0;
		double cropProduction = 0;
		double cropAreaHarvested = 0;
		double cropYield = 0 ;
		int average = 0;
		
		for(int i=0; i<productionFlag.length; i++)
		{
			productionFlag[i] = false;
			areaHarvestedFlag[i] = false;
			yieldFlag[i] = false;
		}
		
		String appValue = "";
		HorizontalPanel hp;
		String productionElementCode = "";
		String productionElementName = "";
		String areaHarvestedElementCode = "";
		String areaHarvestedElementName = "";
		String yieldElementCode = "";
		String yieldElementName = "";
		String year = ""; 
		String forecast = "";
		String valueType = "";
		String currentId = "row_"+rowNum+ "col_"+ colNum+"Crop_";
		String currentIdProduction = currentId + "Production";
		String currentIdAreaHarvested = currentId + "AreaHarvested";
		String currentIdYield = currentId + "Yield";
		String resultProduction[] = parseRowHeader(decoration.getRowHeader(rowNum), decoration.getelementUnit(rowNum));
		String resultAreaHarvested[] = parseRowHeader(decoration.getRowHeader(rowNum+1), decoration.getelementUnit(rowNum+1));
		String resultYield[] = parseRowHeader(decoration.getRowHeader(rowNum+2), decoration.getelementUnit(rowNum+2));
		List<Integer> indexToRemove = new ArrayList();
		List<ClientCbsDatasetResult> clientIndexToRemove = new ArrayList();
		int i =0;
		
		if(intCropsNum>0)
		{
			valueType = "CROP_1";
			//Delete the same elements in the LIST_FOR_PROD_AND_OTHER_USES_FORM 
			for(ClientCbsDatasetResult clientCbsDatasetResult: CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM)
			{				
				System.out.println("Class: SpecificRowWindow Function Text:getFieldValueForProduction clientCbsDatasetResult.getId() "+clientCbsDatasetResult.getId());
				System.out.println("Class: SpecificRowWindow Function Text:getFieldValueForProduction currentId "+currentId);
				//For Production, Area Harvested and Yield
				if(clientCbsDatasetResult.getId().contains(currentId))
				{
					System.out.println("Class: SpecificRowWindow Function Text:getFieldValueForProduction UGUALI ");
					//CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.remove(clientCbsDatasetResult);
					indexToRemove.add(i);
					clientIndexToRemove.add(clientCbsDatasetResult);
					i++;
				}
				
			}
			System.out.println("Class:SpecificRowWindow Function:getFieldValueForProduction Text: Before Remove CROP CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size()= "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
			for(ClientCbsDatasetResult c: clientIndexToRemove)
			{
				//System.out.println("Class:SpecificRowWindow Function:getFieldValueForProduction Text: ToRemove num= "+);
				if(c!=null)
				{
					System.out.println("Class:SpecificRowWindow Function:getFieldValueForProduction Text: ToRemove c value= "+c.getValue()+ " flag "+ c.getAnnotation());
					CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.remove(c);
				}
				
				//CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.remo.remove(num);
			}
			System.out.println("Class:SpecificRowWindow Function:getFieldValueForProduction Text: After Remove CROP CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size()= "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
			for(int z=0; z<CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size(); z++)
			{
				System.out.println("Class:SpecificRowWindow Function:getFieldValueForProduction Text: CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getDatabase()= "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getDatabase());
			}
			
			if(CCBS.NAMES_CODES_FIELD.get(resultProduction[0])!=null)
			{
				Set<String> keySet = CCBS.NAMES_CODES_FIELD.get(resultProduction[0]).keySet();
				//There is just an element in this list: code, name of the element in the database
				for(String key: keySet)
				{
					productionElementCode = key;
					productionElementName = CCBS.NAMES_CODES_FIELD.get(resultProduction[0]).get(productionElementCode);
					break;
				}
			}
			if(CCBS.NAMES_CODES_FIELD.get(resultAreaHarvested[0])!=null)
			{
				Set<String> keySet = CCBS.NAMES_CODES_FIELD.get(resultAreaHarvested[0]).keySet();
				//There is just an element in this list: code, name of the element in the database
				for(String key: keySet)
				{
					areaHarvestedElementCode = key;
					areaHarvestedElementName = CCBS.NAMES_CODES_FIELD.get(resultAreaHarvested[0]).get(areaHarvestedElementCode);
					break;
				}
			}
			
			if(CCBS.NAMES_CODES_FIELD.get(resultYield[0])!=null)
			{
				Set<String> keySet = CCBS.NAMES_CODES_FIELD.get(resultYield[0]).keySet();
				//There is just an element in this list: code, name of the element in the database
				for(String key: keySet)
				{
					yieldElementCode = key;
					yieldElementName = CCBS.NAMES_CODES_FIELD.get(resultYield[0]).get(yieldElementCode);
					break;
				}
			}
			
			if((colNum != (decoration.numCols()-2))&&(colNum != (decoration.numCols()-1)))
			{
				year = CCBS.YEARS_ON_GRID.get(colNum);
				forecast = CCBS.MONTH_FOR_YEARS.get(CCBS.YEARS_ON_GRID.get(colNum));
			}
			else
			{
				//Last two column
				year = CCBS.OTHER_YEARS.get(0);
				forecast = CCBS.MONTH_WITH_FLAG_LONG[0];
			}
			
			for(Component component : fieldSetOne.getItems())
			{
				hp = (HorizontalPanel) component;
				if(hp!=null)
				{
					NumberField nf = (NumberField)hp.getItemByItemId("FirstProduction");
					if(nf!=null)
					{
						System.out.println(" FirstProduction nf.getValue() "+ nf.getValue());
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							production+= Double.parseDouble(appValue);
							cropProduction = production;
							productionFlag[0] = true;
						}
					}
					nf = (NumberField)hp.getItemByItemId("FirstAreaHarvested");
					if(nf!=null)
					{
						System.out.println("FirstAreaHarvested nf.getValue() "+ nf.getValue());
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							areaHarvested+= Double.parseDouble(appValue);
							cropAreaHarvested = areaHarvested;
							areaHarvestedFlag[0] = true;
						}
					}
					nf = (NumberField)hp.getItemByItemId("FirstYield");
					if(nf!=null)
					{
						System.out.println("FirstYield nf.getValue() "+ nf.getValue());
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							yield+= Double.parseDouble(appValue);
							cropYield = yield;
							yieldFlag[0] = true;
							average++;
						}
					}
				}				
			}
			//Crop Production Result
			ClientCbsDatasetResult newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, productionElementCode, productionElementName, resultProduction[1], year, ""+cropProduction, PagePanelVariables.firstProductionFlag, forecast, valueType);
			newClientCbsDatasetResult.setId(currentIdProduction);
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
			newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, areaHarvestedElementCode, areaHarvestedElementName, resultAreaHarvested[1], year, ""+cropAreaHarvested, PagePanelVariables.firstAreaHarvestedFlag, forecast, valueType);
			newClientCbsDatasetResult.setId(currentIdAreaHarvested);
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
			newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, yieldElementCode, yieldElementName, resultYield[1], year, ""+cropYield, PagePanelVariables.firstYieldFlag, forecast, valueType);
			newClientCbsDatasetResult.setId(currentIdYield);
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
			
			for(int z=0; z<CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size(); z++)
			{
				System.out.println("Class:SpecificRowWindow Function:getFieldValueForProduction Text: ValueType = "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getValueType()+" getValue= "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getValue() +" getFlag= "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getAnnotation());
			}
		}
		if(intCropsNum>1)
		{
			valueType = "CROP_2";
			
			for(Component component : fieldSetTwo.getItems())
			{
				hp = (HorizontalPanel) component;
				if(hp!=null)
				{
					NumberField nf = (NumberField)hp.getItemByItemId("SecondProduction");
					if(nf!=null)
					{
						System.out.println("SecondProduction nf.getValue() "+ nf.getValue());
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							cropProduction = Double.parseDouble(appValue);
							production+= Double.parseDouble(appValue);
							productionFlag[1] = true;
						}
					}
					nf = (NumberField)hp.getItemByItemId("SecondAreaHarvested");
					if(nf!=null)
					{
						System.out.println("SecondAreaHarvested nf.getValue() "+ nf.getValue());
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							cropAreaHarvested = Double.parseDouble(appValue);
							areaHarvested+= Double.parseDouble(appValue);
							areaHarvestedFlag[1] = true;
						}
					}
					nf = (NumberField)hp.getItemByItemId("SecondYield");
					if(nf!=null)
					{
						System.out.println("SecondYield nf.getValue() "+ nf.getValue());
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							cropYield = Double.parseDouble(appValue);
							yield+= Double.parseDouble(appValue);
							yieldFlag[1] = true;
							average++;
						}
					}
				}
			}
			//Crop Production Result
			ClientCbsDatasetResult newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, productionElementCode, productionElementName, resultProduction[1], year, ""+cropProduction, PagePanelVariables.secondProductionFlag, forecast, valueType);
			newClientCbsDatasetResult.setId(currentIdProduction);
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
			newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, areaHarvestedElementCode, areaHarvestedElementName, resultAreaHarvested[1], year, ""+cropAreaHarvested, PagePanelVariables.secondAreaHarvestedFlag, forecast, valueType);
			newClientCbsDatasetResult.setId(currentIdAreaHarvested);
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
			newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, yieldElementCode, yieldElementName, resultYield[1], year, ""+cropYield, PagePanelVariables.secondYieldFlag, forecast, valueType);
			newClientCbsDatasetResult.setId(currentIdYield);
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
			for(int z=0; z<CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size(); z++)
			{
				System.out.println("Class:SpecificRowWindow Function:getFieldValueForProduction Text: ValueType = "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getValueType()+" getValue= "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getValue() +" getFlag= "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getAnnotation());
			}
		}
		if(intCropsNum>2)
		{
			valueType = "CROP_3";
			for(Component component : fieldSetThree.getItems())
			{
				hp = (HorizontalPanel) component;
				if(hp!=null)
				{
					NumberField nf = (NumberField)hp.getItemByItemId("ThirdProduction");
					if(nf!=null)
					{
						System.out.println("ThirdProduction nf.getValue() "+ nf.getValue());
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							cropProduction = Double.parseDouble(appValue);
							production+= Double.parseDouble(appValue);
							productionFlag[2] = true;
						}
					}
					nf = (NumberField)hp.getItemByItemId("ThirdAreaHarvested");
					if(nf!=null)
					{
						System.out.println("ThirdAreaHarvested nf.getValue() "+ nf.getValue());
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							cropAreaHarvested = Double.parseDouble(appValue);
							areaHarvested+= Double.parseDouble(appValue);
							areaHarvestedFlag[2] = true;
						}
					}
					nf = (NumberField)hp.getItemByItemId("ThirdYield");
					if(nf!=null)
					{
						System.out.println("ThirdYield nf.getValue() "+ nf.getValue());
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							cropYield = Double.parseDouble(appValue);
							yield+= Double.parseDouble(appValue);
							yieldFlag[2] = true;
							average++;
						}
					}
				}
			}
			//Crop Production Result
			ClientCbsDatasetResult newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, productionElementCode, productionElementName, resultProduction[1], year, ""+cropProduction, PagePanelVariables.thirdProductionFlag, forecast, valueType);
			newClientCbsDatasetResult.setId(currentIdProduction);
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
			newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, areaHarvestedElementCode, areaHarvestedElementName, resultAreaHarvested[1], year, ""+cropAreaHarvested, PagePanelVariables.thirdAreaHarvestedFlag, forecast, valueType);
			newClientCbsDatasetResult.setId(currentIdAreaHarvested);
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
			newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, yieldElementCode, yieldElementName, resultYield[1], year, ""+cropYield, PagePanelVariables.thirdYieldFlag, forecast, valueType);
			newClientCbsDatasetResult.setId(currentIdYield);
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
			
			for(int z=0; z<CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size(); z++)
			{
				System.out.println("Class:SpecificRowWindow Function:getFieldValueForProduction Text: ValueType = "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getValueType()+" getValue= "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getValue() +" getFlag= "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.get(z).getAnnotation());
			}
		}
		System.out.println("Class: PagePanel Function: componentSelected Text: production= "+production);
		System.out.println("Class: PagePanel Function: componentSelected Text: areaHarvested= "+areaHarvested);
		System.out.println("Class: PagePanel Function: componentSelected Text: yield= "+yield+ " average= "+average);
		System.out.println("Class: PagePanel Function: componentSelected Text: average tot= "+yield/average);
		double averageTot = 0;
		if(average!= 0)
		{
			averageTot = yield/average;
		}
		
		ProductionFormField productionFormFieldResult = new ProductionFormField(productionFlag, areaHarvestedFlag, yieldFlag, production, areaHarvested, averageTot);
		
		return productionFormFieldResult;
	}

//	//For OtherUsesForm
//	public FieldSet createMainFieldSetForOtherUses(final OtherUsesFormField otherUsesFormField, final Button mainSave)
//	{
//		FieldSet mainFieldSet = new FieldSet();
//		mainFieldSet.setHeading("Other Uses by Field");  
//		mainFieldSet.setCollapsible(true);
//		mainFieldSet.setWidth(450);
//		mainFieldSet.setHeight(400);
//		FormLayout mainLayout = new FormLayout();  
//		mainLayout.setLabelWidth(130);  
//		mainFieldSet.setLayout(mainLayout);  
//		
//		HorizontalPanel hp = new HorizontalPanel();
//		hp.setSpacing(5);
//
//	    Label label = new Label("Other Uses Flag:");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    
//	    hp.add(label);
//	   
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	    combo.setId("OtherUsesFlagCombo");
//	    combo.addSelectionChangedListener(PagePanelController.otherUseFlagListener(otherUsesFormField.getOtherUsesFlagCombo(), mainSave, otherUsesFormField.getNorthDim()));
//	    hp.add(combo);
//	    mainFieldSet.add(hp); 
//		   
//	    hp = new HorizontalPanel();
//	    hp.setSpacing(5);
//
//	    label = new Label("Seeds:");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    NumberField field = new NumberField();  
//	    field.setId("Seeds");
//	    field.setAllowBlank(false);
//	    field.setWidth(120);
//	    field.setEmptyText("0");
//	    field.setId("OtherUsesSeedsField");
//	    field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedListener(otherUsesFormField, mainSave));
//	    hp.add(label);
//	    hp.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	    combo.setId("OtherUsesSeedsCombo");
//	    combo.addSelectionChangedListener(PagePanelController.seedsListener(otherUsesFormField.getSeedsCombo(), mainSave, otherUsesFormField.getNorthDim()));
//	    hp.add(combo);
//	    mainFieldSet.add(hp); 
//	
//	    hp = new HorizontalPanel();
//	    hp.setSpacing(5);
//
//	    label = new Label("Post Harvest Losses");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    field = new NumberField();  
//	    field.setId("Post Harvest Losses");
//	   
//	    field.setId("OtherUsesPostHarvestField");
//	    field.setAllowBlank(false);
//	    field.setWidth(120);
//	    field.setEmptyText("0");
//	    field.addListener(Events.KeyUp, PagePanelController.setPostHarvestedLossesSeedListener(otherUsesFormField, mainSave));
//	    
//	    hp.add(label);
//	    hp.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	    combo.setId("OtherUsesPostHarvestCombo");
//	    combo.addSelectionChangedListener(PagePanelController.postHarvestLossesListener(otherUsesFormField.getPostHarvestedCombo(), mainSave, otherUsesFormField.getNorthDim()));
//	    hp.add(combo);
//	    mainFieldSet.add(hp); 
//		
//	    hp = new HorizontalPanel();
//	    hp.setSpacing(5);
//
//	    label = new Label("Industrial Use");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    field = new NumberField();  
//	    field.setId("Industrial Use");
//	    field.setAllowBlank(false);
//	    field.setWidth(120);
//	    field.setEmptyText("0");
//	    field.addListener(Events.KeyUp, PagePanelController.setIndustrialUseLossesSeedListener(otherUsesFormField, mainSave));
//	    
//	    hp.add(label);
//	    hp.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	    combo.setId("OtherUsesIndustrialUseCombo");
//	    combo.addSelectionChangedListener(PagePanelController.industrialUseFlagListener(otherUsesFormField.getIndustrialUseCombo(), mainSave, otherUsesFormField.getNorthDim()));
//	    hp.add(combo);
//	    mainFieldSet.add(hp); 
//	    
//	    return mainFieldSet;
//	}
	
//	//For OtherUsesForm
//	public FieldSet createMainFieldSetForOtherUses(final OtherUsesFormField otherUsesFormField, FieldSet fieldSetNorthOtherUses, final Button mainSave, PagePanelVariables ppv)
//	{
//		FieldSet mainFieldSet = new FieldSet();
//		mainFieldSet.setHeading("Other Uses by Field");  
//		mainFieldSet.setCollapsible(true);
//		mainFieldSet.setWidth(450);
//		mainFieldSet.setHeight(400);
//		FormLayout mainLayout = new FormLayout();  
//		mainLayout.setLabelWidth(130);  
//		mainFieldSet.setLayout(mainLayout);  
//		
//		HorizontalPanel hp = new HorizontalPanel();
//		hp.setSpacing(5);
//
////	    Label label = new Label("Other Uses Flag:");
////	    label.setStyleName("ccbs-FlagLabel");
////	    label.setWidth("130px");
////	    
////	    hp.add(label);
////	   
////	    combo = PagePanelController.addFlagCombo();
////	    combo.setAllowBlank(false);
////	    combo.setId("OtherUsesFlagCombo");
////	   // combo.addSelectionChangedListener(PagePanelController.otherUseFlagListener(otherUsesFormField.getOtherUsesFlagCombo(), mainSave, otherUsesFormField.getNorthDim()));
////	    hp.add(combo);
////	    mainFieldSet.add(hp); 
//		   
//	    hp = new HorizontalPanel();
//	    hp.setSpacing(5);
//
//	    Label label = new Label("Seeds:");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    NumberField field = new NumberField();  
//	    field.setId("Seeds");
//	   // field.setAllowBlank(false);
//	    field.setWidth(120);
//	    //field.setEmptyText("0.0");
//	   
//	    field.setId("OtherUsesSeedsField");
//	   // field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedListener(otherUsesFormField, mainSave));
//	   // field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField, (NumberField)storedMainFieldSet.getItemByItemId("OtherUsesPostHarvestField"), (NumberField)storedMainFieldSet.getItemByItemId("Industrial Use"),fieldSetNorthOtherUses));
//	    field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField,fieldSetNorthOtherUses));
//	   if(ppv.seedsFound)
//	   {
//		   field.setValue(ppv.seedsNumberFieldValueFound);
//	   }
//	   else
//	   {
//		   field.setValue(0.0);
//	   }
//	    hp.add(label);
//	    hp.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	   // combo.setAllowBlank(false);
//	    combo.setId("OtherUsesSeedsCombo");
//	    if(ppv.seedsFound)
//		{
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.seedsComboProdValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);
//				PagePanelVariables.seedsFlag = object.getCode();
//			}
//			else
//			{
//				PagePanelVariables.seedsFlag = "";
//			}
//		}
//	    else
//	    {
//	    	//PagePanelVariables.seedsFlag = CCBS.FLAGS.getAt(0).getLabel();
//	    	PagePanelVariables.seedsFlag = "";
//	    }
//	    combo.addSelectionChangedListener(PagePanelController.seedsListener(otherUsesFormField.getSeedsCombo(), mainSave, otherUsesFormField.getNorthDim()));
//	  
//	    hp.add(combo);
//	    mainFieldSet.add(hp); 
//	
//	    hp = new HorizontalPanel();
//	    hp.setSpacing(5);
//
//	    label = new Label("Post Harvest Losses");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    field = new NumberField();  
//	    //field.setId("Post Harvest Losses");
//	   
//	    field.setId("OtherUsesPostHarvestField");
//	   // field.setAllowBlank(false);
//	    field.setWidth(120);
//	    //field.setEmptyText("0.0");
//	   if(ppv.postHarvestedLossesFound)
//	   {
//		   field.setValue(ppv.postHarvestedLossesNumberFieldValueFound);
//	   }
//	   else
//	   {
//		   field.setValue(0.0);
//	   }
//	   // field.addListener(Events.KeyUp, PagePanelController.setPostHarvestedLossesSeedListener(otherUsesFormField, mainSave));
//	   // field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField, (NumberField)storedMainFieldSet.getItemByItemId("Seeds"), (NumberField)storedMainFieldSet.getItemByItemId("Industrial Use"),fieldSetNorthOtherUses));
//	    field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField,fieldSetNorthOtherUses));
//	    hp.add(label);
//	    hp.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	 //   combo.setAllowBlank(false);
//	    combo.setId("OtherUsesPostHarvestCombo");
//	    if(ppv.postHarvestedLossesFound)
//		{
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.postHarvestedLossesComboAreaValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);
//				PagePanelVariables.postHarvestLossesFlag = object.getCode();
//			}
//			else
//			{
//				 PagePanelVariables.postHarvestLossesFlag = "";
//			}
//		}
//	    else
//	    {
//	       // PagePanelVariables.postHarvestLossesFlag = CCBS.FLAGS.getAt(0).getLabel();
//	        PagePanelVariables.postHarvestLossesFlag = "";
//	    }
//	    combo.addSelectionChangedListener(PagePanelController.postHarvestLossesListener(otherUsesFormField.getPostHarvestedCombo(), mainSave, otherUsesFormField.getNorthDim()));
//	
//	    hp.add(combo);
//	    mainFieldSet.add(hp); 
//		
//	    hp = new HorizontalPanel();
//	    hp.setSpacing(5);
//
//	    label = new Label("Industrial Use");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    field = new NumberField();  
//	    field.setId("Industrial Use");
//	 //   field.setAllowBlank(false);
//	    field.setWidth(120);
//	   // field.setEmptyText("0.0");
//		   if(ppv.industrialUseFound)
//		   {
//			   field.setValue(ppv.industrialUseNumberFieldValueFound);
//		   }
//		   else
//		   {
//			   field.setValue(0.0);
//		   }
//	  //  field.addListener(Events.KeyUp, PagePanelController.setIndustrialUseLossesSeedListener(otherUsesFormField, mainSave));
//	   // field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField, (NumberField)storedMainFieldSet.getItemByItemId("Seeds"), (NumberField)storedMainFieldSet.getItemByItemId("Post Harvest Losses"),fieldSetNorthOtherUses));
//	    field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField,fieldSetNorthOtherUses));
//	    hp.add(label);
//	    hp.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	    if(ppv.industrialUseFound)
//		{
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.industrialUseComboFieldYieldValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);
//				PagePanelVariables.industrialUseFlag = object.getCode();
//			}
//			else
//			{
//				PagePanelVariables.industrialUseFlag = "";
//			}
//		}
//	    else
//	    {
//	       // PagePanelVariables.industrialUseFlag = CCBS.FLAGS.getAt(0).getLabel();
//	        PagePanelVariables.industrialUseFlag = "";
//	    }
//	 //   combo.setAllowBlank(false);
//	    combo.setId("OtherUsesIndustrialUseCombo");
//	    combo.addSelectionChangedListener(PagePanelController.industrialUseFlagListener(otherUsesFormField.getIndustrialUseCombo(), mainSave, otherUsesFormField.getNorthDim()));
//	    hp.add(combo);
//	    mainFieldSet.add(hp); 
//	    
//	    if(ppv.seedsFound)
//	    {
//	    	otherUsesFormField.setSeeds(ppv.seedsNumberFieldValueFound);
//		}
//	    if(ppv.postHarvestedLossesFound)
//		{
//	    	otherUsesFormField.setPostHarvestLosses(ppv.postHarvestedLossesNumberFieldValueFound);
//		}
//	    if(ppv.industrialUseFound)
//		{
//	    	otherUsesFormField.setIndustrialUse(ppv.industrialUseNumberFieldValueFound);
//		} 
//	    return mainFieldSet;
//	}
	
	//For OtherUsesForm
	public FieldSet createMainFieldSetForOtherUses(final OtherUsesFormField otherUsesFormField, FieldSet fieldSetNorthOtherUses, final Button mainSave, PagePanelVariables ppv)
	{
		FieldSet mainFieldSet = new FieldSet();
		mainFieldSet.setHeading("Other Uses by Field");  
		mainFieldSet.setCollapsible(true);
		mainFieldSet.setWidth(450);
		mainFieldSet.setHeight(400);
		FormLayout mainLayout = new FormLayout();  
		mainLayout.setLabelWidth(130);  
		mainFieldSet.setLayout(mainLayout);  
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(5);

//	    Label label = new Label("Other Uses Flag:");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    
//	    hp.add(label);
//	   
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	    combo.setId("OtherUsesFlagCombo");
//	   // combo.addSelectionChangedListener(PagePanelController.otherUseFlagListener(otherUsesFormField.getOtherUsesFlagCombo(), mainSave, otherUsesFormField.getNorthDim()));
//	    hp.add(combo);
//	    mainFieldSet.add(hp); 
		   
	    hp = new HorizontalPanel();
	    hp.setSpacing(5);

	    Label label = new Label("Seeds:");
	    label.setStyleName("ccbs-FlagLabel");
	    label.setWidth("130px");
	    NumberField field = new NumberField();  
	    field.setId("Seeds");
	   // field.setAllowBlank(false);
	    field.setWidth(120);
	    //field.setEmptyText("0.0");
	   
	    field.setId("OtherUsesSeedsField");
	   // field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedListener(otherUsesFormField, mainSave));
	   // field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField, (NumberField)storedMainFieldSet.getItemByItemId("OtherUsesPostHarvestField"), (NumberField)storedMainFieldSet.getItemByItemId("Industrial Use"),fieldSetNorthOtherUses));
	    field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField,fieldSetNorthOtherUses));
	   if(ppv.seedsFound)
	   {
		   field.setValue(ppv.seedsNumberFieldValueFound);
	   }
	   else
	   {
		   field.setValue(0.0);
	   }
	    hp.add(label);
	    hp.add(field);
	    combo = PagePanelController.addFlagCombo();
	   // combo.setAllowBlank(false);
	    combo.setId("OtherUsesSeedsCombo");
//	    if(ppv.seedsFound)
//		{
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.seedsComboProdValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);
//				PagePanelVariables.seedsFlag = object.getCode();
//			}
//			else
//			{
//				PagePanelVariables.seedsFlag = "";
//			}
//		}
//	    else
//	    {
//	    	//PagePanelVariables.seedsFlag = CCBS.FLAGS.getAt(0).getLabel();
//	    	PagePanelVariables.seedsFlag = "";
//	    }
	    if(ppv.seedsFound)
		{
	    	ppv.seedsFlag = ppv.seedsComboProdValueFound;
		}
	    AMISCodesModelData object = getObjectByFlagCombo(ppv.seedsFlag);
		if(object!=null)
		{
			combo.setValue(object);
		}
	    combo.addSelectionChangedListener(PagePanelController.seedsListener(otherUsesFormField.getSeedsCombo(), mainSave, otherUsesFormField.getNorthDim()));
	  
	    hp.add(combo);
	    mainFieldSet.add(hp); 
	
	    hp = new HorizontalPanel();
	    hp.setSpacing(5);

	    label = new Label("Post Harvest Losses");
	    label.setStyleName("ccbs-FlagLabel");
	    label.setWidth("130px");
	    field = new NumberField();  
	    //field.setId("Post Harvest Losses");
	   
	    field.setId("OtherUsesPostHarvestField");
	   // field.setAllowBlank(false);
	    field.setWidth(120);
	    //field.setEmptyText("0.0");
	   if(ppv.postHarvestedLossesFound)
	   {
		   field.setValue(ppv.postHarvestedLossesNumberFieldValueFound);
	   }
	   else
	   {
		   field.setValue(0.0);
	   }
	   // field.addListener(Events.KeyUp, PagePanelController.setPostHarvestedLossesSeedListener(otherUsesFormField, mainSave));
	   // field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField, (NumberField)storedMainFieldSet.getItemByItemId("Seeds"), (NumberField)storedMainFieldSet.getItemByItemId("Industrial Use"),fieldSetNorthOtherUses));
	    field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField,fieldSetNorthOtherUses));
	    hp.add(label);
	    hp.add(field);
	    combo = PagePanelController.addFlagCombo();
	 //   combo.setAllowBlank(false);
	    combo.setId("OtherUsesPostHarvestCombo");
//	    if(ppv.postHarvestedLossesFound)
//		{
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.postHarvestedLossesComboAreaValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);
//				PagePanelVariables.postHarvestLossesFlag = object.getCode();
//			}
//			else
//			{
//				 PagePanelVariables.postHarvestLossesFlag = "";
//			}
//		}
//	    else
//	    {
//	       // PagePanelVariables.postHarvestLossesFlag = CCBS.FLAGS.getAt(0).getLabel();
//	        PagePanelVariables.postHarvestLossesFlag = "";
//	    }
	    if(ppv.postHarvestedLossesFound)
		{
	    	ppv.postHarvestLossesFlag = ppv.postHarvestedLossesComboAreaValueFound;
		}
	    object = getObjectByFlagCombo(ppv.postHarvestLossesFlag);
		if(object!=null)
		{
			combo.setValue(object);
		}
	    combo.addSelectionChangedListener(PagePanelController.postHarvestLossesListener(otherUsesFormField.getPostHarvestedCombo(), mainSave, otherUsesFormField.getNorthDim()));
	
	    hp.add(combo);
	    mainFieldSet.add(hp); 
		
	    hp = new HorizontalPanel();
	    hp.setSpacing(5);

	    label = new Label("Industrial Use");
	    label.setStyleName("ccbs-FlagLabel");
	    label.setWidth("130px");
	    field = new NumberField();  
	    field.setId("Industrial Use");
	 //   field.setAllowBlank(false);
	    field.setWidth(120);
	   // field.setEmptyText("0.0");
		   if(ppv.industrialUseFound)
		   {
			   field.setValue(ppv.industrialUseNumberFieldValueFound);
		   }
		   else
		   {
			   field.setValue(0.0);
		   }
	  //  field.addListener(Events.KeyUp, PagePanelController.setIndustrialUseLossesSeedListener(otherUsesFormField, mainSave));
	   // field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField, (NumberField)storedMainFieldSet.getItemByItemId("Seeds"), (NumberField)storedMainFieldSet.getItemByItemId("Post Harvest Losses"),fieldSetNorthOtherUses));
	    field.addListener(Events.KeyUp, PagePanelController.setOtherUsesSeedNoButtonListener(otherUsesFormField,fieldSetNorthOtherUses));
	    hp.add(label);
	    hp.add(field);
	    combo = PagePanelController.addFlagCombo();
//	    if(ppv.industrialUseFound)
//		{
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.industrialUseComboFieldYieldValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);
//				PagePanelVariables.industrialUseFlag = object.getCode();
//			}
//			else
//			{
//				PagePanelVariables.industrialUseFlag = "";
//			}
//		}
//	    else
//	    {
//	       // PagePanelVariables.industrialUseFlag = CCBS.FLAGS.getAt(0).getLabel();
//	        PagePanelVariables.industrialUseFlag = "";
//	    }
	    
	    if(ppv.industrialUseFound)
		{
	    	ppv.industrialUseFlag = ppv.industrialUseComboFieldYieldValueFound;
		}
	    object = getObjectByFlagCombo(ppv.industrialUseFlag);
		if(object!=null)
		{
			combo.setValue(object);
		}
	 //   combo.setAllowBlank(false);
	    combo.setId("OtherUsesIndustrialUseCombo");
	    combo.addSelectionChangedListener(PagePanelController.industrialUseFlagListener(otherUsesFormField.getIndustrialUseCombo(), mainSave, otherUsesFormField.getNorthDim()));
	    hp.add(combo);
	    mainFieldSet.add(hp); 
	    
	    if(ppv.seedsFound)
	    {
	    	otherUsesFormField.setSeeds(ppv.seedsNumberFieldValueFound);
		}
	    if(ppv.postHarvestedLossesFound)
		{
	    	otherUsesFormField.setPostHarvestLosses(ppv.postHarvestedLossesNumberFieldValueFound);
		}
	    if(ppv.industrialUseFound)
		{
	    	otherUsesFormField.setIndustrialUse(ppv.industrialUseNumberFieldValueFound);
		} 
	    return mainFieldSet;
	}
	
//	public FieldSet createIndustrialUseFieldSetForOtherUses()
//	{
//		final FieldSet fieldSet = new FieldSet();
//		fieldSet.setWidth(400);
//		fieldSet.setHeading("To Calculate Industrial Use");  
//		fieldSet.setCollapsible(true);
//		fieldSet.collapse();
//		
//		FormLayout layout = new FormLayout();  
//		layout.setLabelWidth(75);  
//		fieldSet.setLayout(layout);  
//		   
//		HorizontalPanel hpFirst = new HorizontalPanel();
//		hpFirst.setSpacing(5);
//
//	    Label label = new Label("Malt:");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    NumberField field = new NumberField();
//	    field.setId("Malt");
//	    field.setAllowBlank(false);
//	    field.setWidth(120);
//	    field.setEmptyText("0");
//	    hpFirst.add(label);
//	    hpFirst.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	    combo.addSelectionChangedListener(PagePanelController.maltListener());
//	    hpFirst.add(combo);
//		
//	    fieldSet.add(hpFirst);
//
//		
//	    hpFirst = new HorizontalPanel();
//		hpFirst.setSpacing(5);
//
//	    label = new Label("Biofuel:");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    field = new NumberField();
//	    field.setId("Biofuel");
//	    field.setAllowBlank(false);
//	    field.setWidth(120);
//	    field.setEmptyText("0");
//	    hpFirst.add(label);
//	    hpFirst.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	    combo.addSelectionChangedListener(PagePanelController.biofuelListener());
//	    hpFirst.add(combo);
//		
//	    fieldSet.add(hpFirst);
//
//	    hpFirst = new HorizontalPanel();
//		hpFirst.setSpacing(5);
//
//	    label = new Label("Sweeteners:");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    field = new NumberField(); 
//	    field.setId("Sweeteners");
//	    field.setAllowBlank(false);
//	    field.setEmptyText("0");
//	    field.setWidth(120);
//	    hpFirst.add(label);
//	    hpFirst.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	   combo.addSelectionChangedListener(PagePanelController.sweetenersListener());
//	    hpFirst.add(combo);
//		
//	    fieldSet.add(hpFirst);
//	    hpFirst = new HorizontalPanel();
//		hpFirst.setSpacing(5);
//
//	    label = new Label("Starch:");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    field = new NumberField();
//	    field.setId("Starch");
//	    field.setAllowBlank(false);
//	    field.setWidth(120);
//	    field.setEmptyText("0");
//	    hpFirst.add(label);
//	    hpFirst.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	    combo.addSelectionChangedListener(PagePanelController.starchListener());
//	    hpFirst.add(combo);
//	    fieldSet.add(hpFirst);
//	    hpFirst = new HorizontalPanel();
//		hpFirst.setSpacing(5);
//
//	    label = new Label("Others:");
//	    label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    field = new NumberField(); 
//	    field.setId("Others");
//	    field.setEmptyText("0");
//	    field.setAllowBlank(false);
//	    field.setWidth(120);
//	    hpFirst.add(label);
//	    hpFirst.add(field);
//	    combo = PagePanelController.addFlagCombo();
//	    combo.setAllowBlank(false);
//	    combo.addSelectionChangedListener(PagePanelController.othersListener());
//	    hpFirst.add(combo);
//	    fieldSet.add(hpFirst);
//	    return fieldSet;
//	}
	
	public FieldSet createIndustrialUseFieldSetForOtherUses(PagePanelVariables ppv)
	{		
		final FieldSet fieldSet = new FieldSet();
		fieldSet.setWidth(400);
	//	fieldSet.setHeading("To Calculate Industrial Use");  
		fieldSet.setCollapsible(true);
		fieldSet.collapse();
		
		FormLayout layout = new FormLayout();  
		layout.setLabelWidth(75);  
		fieldSet.setLayout(layout);  
		   
		HorizontalPanel hpFirst = new HorizontalPanel();
		hpFirst.setSpacing(5);

	    Label label = new Label("Malt:");
	    label.setStyleName("ccbs-FlagLabel");
	    label.setWidth("130px");
	    NumberField field = new NumberField();
	    field.setId("Malt");
	   // field.setAllowBlank(false);

	    field.setWidth(120);
	    if(ppv.maltFound)
	    {
	    	field.setValue(ppv.maltNumberFieldValueFound);
	    }
	    else
	    {
		    field.setValue(0.0);
	    }
	  //  field.setEmptyText("0.0");
	   
	    hpFirst.add(label);
	    hpFirst.add(field);
	    combo = PagePanelController.addFlagCombo();
	  //  combo.setAllowBlank(false);
	    combo.setId("MaltCombo");
	    combo.addSelectionChangedListener(PagePanelController.maltListener());
	    
//	    if(ppv.maltFound)
//	    {
//	    	
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.maltComboValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);			
//				PagePanelVariables.maltFlag = object.getCode();
//			}
//			else
//			{
//				PagePanelVariables.maltFlag = "";
//			}
//	    }
//	    else
//	    {
//	    	PagePanelVariables.maltFlag = "";
//	    }
	    if(ppv.maltFound)
	    {
	    	ppv.maltFlag = ppv.maltComboValueFound;
	    }
	    AMISCodesModelData object = getObjectByFlagCombo(ppv.maltFlag);
		if(object!=null)
		{
			combo.setValue(object);
		}
	    
	    hpFirst.add(combo);
		
	    fieldSet.add(hpFirst);

		
	    hpFirst = new HorizontalPanel();
		hpFirst.setSpacing(5);

	    label = new Label("Biofuel:");
	    label.setStyleName("ccbs-FlagLabel");
	    label.setWidth("130px");
	    field = new NumberField();
	    field.setId("Biofuel");
	  //  field.setAllowBlank(false);
	    field.setWidth(120);
	    //field.setEmptyText("0.0");
	    if(ppv.biofuelFound)
	    {
	    	field.setValue(ppv.biofuelNumberFieldValueFound);
	    }
	    else
	    {
		    field.setValue(0.0);
	    }
	    hpFirst.add(label);
	    hpFirst.add(field);
	    combo = PagePanelController.addFlagCombo();
	 //   combo.setAllowBlank(false);
	    combo.addSelectionChangedListener(PagePanelController.biofuelListener());
//	    if(ppv.biofuelFound)
//	    {
//	    	
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.biofuelComboValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);			
//				PagePanelVariables.biofuelFlag = object.getCode();
//			}
//			else
//			{
//				PagePanelVariables.biofuelFlag = "";
//			}
//	    }
//	    else
//	    {
//	    	PagePanelVariables.biofuelFlag = "";
//	    }
	    if(ppv.biofuelFound)
	    {
	    	ppv.biofuelFlag = ppv.biofuelComboValueFound;
	    }
	    object = getObjectByFlagCombo(ppv.biofuelFlag);
		if(object!=null)
		{
			combo.setValue(object);
		}
	    combo.setId("BiofuelCombo");
	    hpFirst.add(combo);
		
	    fieldSet.add(hpFirst);

	    hpFirst = new HorizontalPanel();
		hpFirst.setSpacing(5);

	    label = new Label("Sweeteners:");
	    label.setStyleName("ccbs-FlagLabel");
	    label.setWidth("130px");
	    field = new NumberField(); 
	    field.setId("Sweeteners");
	  //  field.setAllowBlank(false);
	    //field.setEmptyText("0.0");
	    if(ppv.sweetenersFound)
	    {
	    	field.setValue(ppv.sweetenersNumberFieldValueFound);
	    }
	    else
	    {
		    field.setValue(0.0);
	    }
	    field.setWidth(120);
	    hpFirst.add(label);
	    hpFirst.add(field);
	    combo = PagePanelController.addFlagCombo();
	  //  combo.setAllowBlank(false);
	    combo.setId("SweetenersCombo");
	    combo.addSelectionChangedListener(PagePanelController.sweetenersListener());
//	    if(ppv.sweetenersFound)
//	    {
//	    	
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.sweetenersComboFieldYieldValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);			
//				PagePanelVariables.sweetenersFlag = object.getCode();
//			}
//			else
//			{
//				PagePanelVariables.sweetenersFlag = "";
//			}
//	    }
//	    else
//	    {
//	    	PagePanelVariables.sweetenersFlag = "";
//	    }
	    if(ppv.sweetenersFound)
	    {
	    	ppv.sweetenersFlag = ppv.sweetenersComboFieldYieldValueFound;
	    }
	    object = getObjectByFlagCombo(ppv.sweetenersFlag);
		if(object!=null)
		{
			combo.setValue(object);
		}
	    hpFirst.add(combo);
		
	    fieldSet.add(hpFirst);
	    hpFirst = new HorizontalPanel();
		hpFirst.setSpacing(5);

	    label = new Label("Starch:");
	    label.setStyleName("ccbs-FlagLabel");
	    label.setWidth("130px");
	    field = new NumberField();
	    field.setId("Starch");
	  //  field.setAllowBlank(false);
	    field.setWidth(120);
	    //field.setEmptyText("0.0");
	    if(ppv.starchFound)
	    {
	    	field.setValue(ppv.starchNumberFieldValueFound);
	    }
	    else
	    {
		    field.setValue(0.0);
	    }
	    hpFirst.add(label);
	    hpFirst.add(field);
	    combo = PagePanelController.addFlagCombo();
	//    combo.setAllowBlank(false);
	    combo.setId("StarchCombo");
	    combo.addSelectionChangedListener(PagePanelController.starchListener());
//	    if(ppv.starchFound)
//	    {
//	    	
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.starchComboValueFound);			
//			if(object!=null)
//			{
//				combo.setValue(object);			
//				PagePanelVariables.starchFlag = object.getCode();
//			}
//			else
//			{
//				PagePanelVariables.starchFlag = "";
//			}
//	    }
//	    else
//	    {
//	    	PagePanelVariables.starchFlag = "";
//	    }
	    if(ppv.starchFound)
	    {
	    	ppv.starchFlag = ppv.starchComboValueFound;
	    }
	    object = getObjectByFlagCombo(ppv.starchFlag);
		if(object!=null)
		{
			combo.setValue(object);
		}
	    hpFirst.add(combo);
	    fieldSet.add(hpFirst);
	    hpFirst = new HorizontalPanel();
		hpFirst.setSpacing(5);

	    label = new Label("Others:");
	    label.setStyleName("ccbs-FlagLabel");
	    label.setWidth("130px");
	    field = new NumberField(); 
	    field.setId("Others");
	    //field.setEmptyText("0.0");
	    if(ppv.othersFound)
	    {
	    	field.setValue(ppv.othersNumberFieldValueFound);
	    }
	    else
	    {
		    field.setValue(0.0);
	    }
	 //   field.setAllowBlank(false);
	    field.setWidth(120);
	    hpFirst.add(label);
	    hpFirst.add(field);
	    combo = PagePanelController.addFlagCombo();
	    combo.setId("OthersCombo");
	 //   combo.setAllowBlank(false);
	    combo.addSelectionChangedListener(PagePanelController.othersListener());
	 //   PagePanelVariables.othersFlag = CCBS.FLAGS.getAt(0).getCode();
//	    if(ppv.othersFound)
//	    {
//	    	
//	    	AMISCodesModelData object = getObjectByFlagCombo(ppv.othersComboFieldYieldValueFound);
//			if(object!=null)
//			{
//				combo.setValue(object);			
//				PagePanelVariables.othersFlag = object.getCode();
//			}
//			else
//			{
//				PagePanelVariables.othersFlag = "";
//			}
//	    }
//	    else
//	    {
//	    	PagePanelVariables.othersFlag = "";
//	    }
	    if(ppv.othersFound)
	    {
	    	ppv.othersFlag = ppv.othersComboFieldYieldValueFound;
	    }
	    object = getObjectByFlagCombo(ppv.othersFlag);
		if(object!=null)
		{
			combo.setValue(object);
		}
	    hpFirst.add(combo);
	    fieldSet.add(hpFirst);
	    return fieldSet;
	}
	
	//Take the values from the main fieldset(Other Uses By Field)
	public void getOtherUsesByFieldFieldSetForOtherUses(FieldSet fieldset, PageDecoration decoration, int rowNum, int colNum)
	{
		String cropsNum = decoration.getCropNum(); 
		String dataSource = decoration.getDataSource();
		String countryCode = decoration.getCountryCode();
		String countryName = decoration.getCountryName();
		String commodityCode = decoration.getCommodityCode();
		String commodityName = decoration.getCommodityName();
		//String resultProduction[] = parseRowHeader(decoration.getRowHeader(rowNum), decoration.getelementUnit(rowNum));
		String valueType = "SUBELEMENT";
		String currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_Main_";
		//String valueType = "SUBELEMENT";
		String currentIdSeeds = currentId + "Seeds";
		String currentIdPostHarvestLosses = currentId + "PostHarvestLosses";
		String currentIdIndustrialUse = currentId + "IndustrialUse";
		//String productionElementCode = "";
		//String productionElementName = "";
		String year = "";
		String forecast = "";
		//Delete the same elements in the listForProdAndOtherUForm 
		List<Integer> indexToRemove = new ArrayList();
		List<ClientCbsDatasetResult> clientindexToRemove = new ArrayList();
		int i=0;
		for(ClientCbsDatasetResult clientCbsDatasetResult: CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM)
		{				
			//For Other Uses
			if(clientCbsDatasetResult.getId().contains(currentId))
			{
				indexToRemove.add(i);
				clientindexToRemove.add(clientCbsDatasetResult);
				//CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.remove(clientCbsDatasetResult);
				i++;
			}
			
		}	
		for(ClientCbsDatasetResult client : clientindexToRemove)
		{
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.remove(client);
		}
		if((colNum != (decoration.numCols()-2))&&(colNum != (decoration.numCols()-1)))
		{
			year = CCBS.YEARS_ON_GRID.get(colNum);
			forecast = CCBS.MONTH_FOR_YEARS.get(CCBS.YEARS_ON_GRID.get(colNum));
			System.out.println("Class:SpecificRowWindow Function: getOtherUsesByFieldFieldSetForOtherUses if all col Text:year= "+year+" forecast= "+forecast);
		}
		else
		{
			//Last two column
			year = CCBS.OTHER_YEARS.get(0);
			forecast = CCBS.MONTH_WITH_FLAG_LONG[0];
			System.out.println("Class:SpecificRowWindow Function: getOtherUsesByFieldFieldSetForOtherUses if last Text:year= "+year+" forecast= "+forecast);
		}
		
		//Take the values from the fieldset
		int horPanelIndex = 0;
		double seedsValue = 0;
		double postHarvestLossesValue = 0;
		double industrialUseValue = 0;
		String appValue;
		for(Component component :fieldset.getItems())
		{
			if(horPanelIndex<3)
			{
				HorizontalPanel hp = (HorizontalPanel)component;
				if(hp != null)
				{
					NumberField element = (NumberField)hp.getItemByItemId("OtherUsesSeedsField");
					if(element!= null)
					{
						if(element.getValue()!=null)
						{
							appValue = element.getValue().toString();
							if((appValue != null)&&(!(appValue.equals(""))))
							{
								seedsValue = Double.parseDouble(appValue);
							}
						}
					}
				}
				if(hp != null)
				{
					NumberField element = (NumberField)hp.getItemByItemId("OtherUsesPostHarvestField");
					if(element!= null)
					{
						if(element.getValue()!=null)
						{
							appValue = element.getValue().toString();
							if((appValue != null)&&(!(appValue.equals(""))))
							{
								postHarvestLossesValue = Double.parseDouble(appValue);
							}
						}
					}
				}
				if(hp != null)
				{
					NumberField element = (NumberField)hp.getItemByItemId("Industrial Use");
					if(element!= null)
					{
						if(element.getValue()!=null)
						{
							appValue = element.getValue().toString();
							if((appValue != null)&&(!(appValue.equals(""))))
							{
								industrialUseValue = Double.parseDouble(appValue);
							}
						}
					}
				}
			}
			horPanelIndex++;
		}
		//Seeds Subelement
		ClientCbsDatasetResult newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, CCBS.SUB_ELEMENTS_WITH_CODE.get(CCBS.SUB_ELEMENTS_NAMES[0]), CCBS.SUB_ELEMENTS_NAMES[0], decoration.getSubElementUnits()[0], year, ""+seedsValue, PagePanelVariables.seedsFlag, forecast, valueType);
		newClientCbsDatasetResult.setId(currentIdSeeds);
		CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
		//Post Harvest Losses Subelement
		newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, CCBS.SUB_ELEMENTS_WITH_CODE.get(CCBS.SUB_ELEMENTS_NAMES[1]), CCBS.SUB_ELEMENTS_NAMES[1], decoration.getSubElementUnits()[1], year, ""+postHarvestLossesValue, PagePanelVariables.postHarvestLossesFlag, forecast, valueType);
		newClientCbsDatasetResult.setId(currentIdPostHarvestLosses);
		CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
		//Industrial Use Subelement
		newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, CCBS.SUB_ELEMENTS_WITH_CODE.get(CCBS.SUB_ELEMENTS_NAMES[2]), CCBS.SUB_ELEMENTS_NAMES[2], decoration.getSubElementUnits()[2], year, ""+industrialUseValue, PagePanelVariables.industrialUseFlag, forecast, valueType);
		newClientCbsDatasetResult.setId(currentIdIndustrialUse);
		CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
		for(ClientCbsDatasetResult client: CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM)
		{
			System.out.println("Class:SpecificRowWindow Function: getOtherUsesByFieldFieldSetForOtherUses Text:Other element");
			System.out.println("Class:SpecificRowWindow Function: getOtherUsesByFieldFieldSetForOtherUses Text:value= "+client.getValue()+" flag= "+client.getAnnotation()+" year= "+client.getYear()+" forecast= "+client.getMonth()+ "id = "+ client.getId());
		}
		
	}
	
	//Take the values from the Industrial Use fieldset
	public void getIndustrialUseFieldSetForOtherUses(FieldSet fieldset, PageDecoration decoration, int rowNum, int colNum)
	{
		String cropsNum = decoration.getCropNum(); 
		String dataSource = decoration.getDataSource();
		String countryCode = decoration.getCountryCode();
		String countryName = decoration.getCountryName();
		String commodityCode = decoration.getCommodityCode();
		String commodityName = decoration.getCommodityName();
		//String resultProduction[] = parseRowHeader(decoration.getRowHeader(rowNum), decoration.getelementUnit(rowNum));
		String valueType = "SUBELEMENT";
		String currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_IndustrialUse_";		
		String maltCurrentId = currentId+ "Malt";
		String biofuelCurrentId = currentId+ "Biofuel";
		String sweetenersCurrentId = currentId+ "Sweeteners";
		String starchCurrentId = currentId+ "Starch";
		String othersCurrentId = currentId+ "Others";
		
		//String productionElementCode = "";
		//String productionElementName = "";
		String year = "";
		String forecast = "";
		//Delete the same elements in the listForProdAndOtherUForm 
		int i =0;
		System.out.println("Class: SpecificRowWindow Function:getIndustrialUseFieldSetForOtherUses before loop i="+i);
		List<Integer> indexToRemove = new ArrayList();
		List<ClientCbsDatasetResult> clientIndexToRemove = new ArrayList();
		for(ClientCbsDatasetResult clientCbsDatasetResult: CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM)
		{		
			System.out.println("Class: SpecificRowWindow Function:getIndustrialUseFieldSetForOtherUses  i="+i);
			//For Other Uses
			if(clientCbsDatasetResult.getId()==null)
			{
				System.out.println("Class: SpecificRowWindow Function:getIndustrialUseFieldSetForOtherUses  clientCbsDatasetResult.getId()==null "+i);
			}
			else
			{
				System.out.println("Class: SpecificRowWindow Function:getIndustrialUseFieldSetForOtherUses  clientCbsDatasetResult.getId()= "+clientCbsDatasetResult.getId());
			}
			if(clientCbsDatasetResult.getId().contains(currentId))
			{
				indexToRemove.add(i);
				clientIndexToRemove.add(clientCbsDatasetResult);
				i++;
			}
			
		}
		//Now the elements can be removed
		for(ClientCbsDatasetResult client : clientIndexToRemove)
		{
			CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.remove(client);
		}
		System.out.println("Class: SpecificRowWindow Function:getIndustrialUseFieldSetForOtherUses before 111111111");
		if((colNum != (decoration.numCols()-2))&&(colNum != (decoration.numCols()-1)))
		{
			System.out.println("Class: SpecificRowWindow Function:getIndustrialUseFieldSetForOtherUses before 111111111222");
			year = CCBS.YEARS_ON_GRID.get(colNum);
			forecast = CCBS.MONTH_FOR_YEARS.get(CCBS.YEARS_ON_GRID.get(colNum));
		}
		else
		{
			System.out.println("Class: SpecificRowWindow Function:getIndustrialUseFieldSetForOtherUses before 111111111222last");
			//Last two column
			year = CCBS.OTHER_YEARS.get(0);
			forecast = CCBS.MONTH_WITH_FLAG_LONG[0];
		}
		//Take the values from the fieldset
		int horPanelIndex = 0;
		double maltValue = 0;
		double biofuelValue = 0;
		double sweetenersValue = 0;
		double starchValue = 0;
		double othersValue = 0;
		String appValue;
		System.out.println("Class: SpecificRowWindow Function: getIndustrialUseFieldSetForOtherUses Text:before loop");
		for(Component component :fieldset.getItems())
		{
			if(horPanelIndex<5)
			{
				HorizontalPanel hp = (HorizontalPanel)component;
				if(hp != null)
				{
					NumberField element = (NumberField)hp.getItemByItemId("Malt");
					if(element!= null)
					{
						if(element.getValue()!=null)
						{
							appValue = element.getValue().toString();
							if((appValue != null)&&(!(appValue.equals(""))))
							{
								System.out.println("Class: SpecificRowWindow Function: getIndustrialUseFieldSetForOtherUses Text:maltValue before "+maltValue);
								maltValue = Double.parseDouble(appValue);
								System.out.println("Class: SpecificRowWindow Function: getIndustrialUseFieldSetForOtherUses Text:maltValue after "+maltValue);
							}
						}
					}
				}
				if(hp != null)
				{
					NumberField element = (NumberField)hp.getItemByItemId("Biofuel");
					if(element!= null)
					{
						if(element.getValue()!=null)
						{
							appValue = element.getValue().toString();
							if((appValue != null)&&(!(appValue.equals(""))))
							{
								biofuelValue = Double.parseDouble(appValue);
							}
						}
					}
				}
				if(hp != null)
				{
					NumberField element = (NumberField)hp.getItemByItemId("Sweeteners");
					if(element!= null)
					{
						if(element.getValue()!=null)
						{
							appValue = element.getValue().toString();
							if((appValue != null)&&(!(appValue.equals(""))))
							{
								sweetenersValue = Double.parseDouble(appValue);
							}
						}
					}
				}
				if(hp != null)
				{
					NumberField element = (NumberField)hp.getItemByItemId("Starch");
					if(element!= null)
					{
						if(element.getValue()!=null)
						{
							appValue = element.getValue().toString();
							if((appValue != null)&&(!(appValue.equals(""))))
							{
								starchValue = Double.parseDouble(appValue);
							}
						}
					}
				}
				if(hp != null)
				{
					NumberField element = (NumberField)hp.getItemByItemId("Others");
					if(element!= null)
					{
						if(element.getValue()!=null)
						{
							appValue = element.getValue().toString();
							if((appValue != null)&&(!(appValue.equals(""))))
							{
								othersValue = Double.parseDouble(appValue);
							}
						}
					}
				}
			}
			horPanelIndex++;
		}
		System.out.println("Class: SpecificRowWindow Function: getIndustrialUseFieldSetForOtherUses Text:after loop");
		//Malt Subelement
		ClientCbsDatasetResult newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, CCBS.SUB_ELEMENTS_WITH_CODE.get(CCBS.SUB_ELEMENTS_NAMES[3]), CCBS.SUB_ELEMENTS_NAMES[3], decoration.getSubElementUnits()[3], year, ""+maltValue, PagePanelVariables.maltFlag, forecast, valueType);
		newClientCbsDatasetResult.setId(maltCurrentId);
		CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
		//Biofuel Subelement
		newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, CCBS.SUB_ELEMENTS_WITH_CODE.get(CCBS.SUB_ELEMENTS_NAMES[4]), CCBS.SUB_ELEMENTS_NAMES[4], decoration.getSubElementUnits()[4], year, ""+biofuelValue, PagePanelVariables.biofuelFlag, forecast, valueType);
		newClientCbsDatasetResult.setId(biofuelCurrentId);
		CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
		//Sweeteners Subelement
		newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, CCBS.SUB_ELEMENTS_WITH_CODE.get(CCBS.SUB_ELEMENTS_NAMES[5]), CCBS.SUB_ELEMENTS_NAMES[5], decoration.getSubElementUnits()[5], year, ""+sweetenersValue, PagePanelVariables.sweetenersFlag, forecast, valueType);
		newClientCbsDatasetResult.setId(sweetenersCurrentId);
		CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
		//Starch Subelement
		newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, CCBS.SUB_ELEMENTS_WITH_CODE.get(CCBS.SUB_ELEMENTS_NAMES[6]), CCBS.SUB_ELEMENTS_NAMES[6], decoration.getSubElementUnits()[6], year, ""+starchValue, PagePanelVariables.starchFlag, forecast, valueType);
		newClientCbsDatasetResult.setId(starchCurrentId);
		CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
		//Others Subelement
		newClientCbsDatasetResult = new ClientCbsDatasetResult(dataSource, countryCode, countryName, commodityCode, commodityName, CCBS.SUB_ELEMENTS_WITH_CODE.get(CCBS.SUB_ELEMENTS_NAMES[7]), CCBS.SUB_ELEMENTS_NAMES[7], decoration.getSubElementUnits()[7], year, ""+othersValue, PagePanelVariables.othersFlag, forecast, valueType);
		newClientCbsDatasetResult.setId(othersCurrentId);
		CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.add(newClientCbsDatasetResult);
	}
	
//	public FieldSet createTotalFieldSetForOtherUses(final OtherUsesFormField otherUsesFormField, final Button mainSave)
//	{
//		final FieldSet fieldSetNorth = new FieldSet();
//		fieldSetNorth.setHeading("Other Uses Total");  
//		fieldSetNorth.setCollapsible(true);
//		
//		FormLayout layoutNorth = new FormLayout();  
//		layoutNorth.setLabelWidth(75);  
//		fieldSetNorth.setLayout(layoutNorth);  
//		   
//		HorizontalPanel hp = new HorizontalPanel();
//		hp.setSpacing(5);
//	
//		Label label = new Label("Other Uses:");
//    	label.setStyleName("ccbs-FlagLabel");
//	    label.setWidth("130px");
//	    hp.add(label);
//		
//		NumberField field = new NumberField();  
//		field.setTitle("Other Uses");
//		field.setId("Other Uses Num Field");
//		field.setAllowBlank(false);
//		field.setWidth(120);
//		field.setEmptyText("0");
//		field.addListener(Events.KeyUp, PagePanelController.setOtherUsesTotalListener(otherUsesFormField, mainSave));
//		hp.add(field);
//		combo = PagePanelController.addFlagCombo();
//        combo.setAllowBlank(false);
//        combo.setId("Other Uses Combo");
//		combo.addSelectionChangedListener(PagePanelController.otherUseListener(otherUsesFormField.getOtherUsesCombo(), mainSave, otherUsesFormField.getNorthDim()));
//		hp.add(combo);
//		fieldSetNorth.add(hp); 
//		
//		return fieldSetNorth;
//	}
	
	public FieldSet createTotalFieldSetForOtherUses(final OtherUsesFormField otherUsesFormField, final String totalValue, final String flagValue, final Button mainSave)
	{
		final FieldSet fieldSetNorth = new FieldSet();
		fieldSetNorth.setHeading("Other Uses Total");  
		fieldSetNorth.setCollapsible(true);
		
		FormLayout layoutNorth = new FormLayout();  
		layoutNorth.setLabelWidth(75);  
		fieldSetNorth.setLayout(layoutNorth);  
		   
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(5);
	
		Label label = new Label("Other Uses:");
    	label.setStyleName("ccbs-FlagLabel");
	    label.setWidth("130px");
	    hp.add(label);
	   
	   NumberField field = new NumberField();  
	   // TextField<String> field = new TextField();  
		field.setTitle("Other Uses");
		field.setId("Other Uses Num Field");
		//field.setAllowBlank(false);
		field.setWidth(120);
		//field.setEmptyText("0.0");
		
	
		
		if((totalValue!=null)&&(!totalValue.equals("")))
		{
			field.setValue(Double.parseDouble(totalValue));
			//field.setValue("0.0");
			//field.setOriginalValue(0.0);
			//field.setRawValue("0.0");
		}
		else
		{
			field.setValue(0.0);
		}
		
		//field.addListener(Events.KeyUp, PagePanelController.setOtherUsesTotalListener(otherUsesFormField, mainSave));
		hp.add(field);
		combo = PagePanelController.addFlagCombo();
       // combo.setAllowBlank(false);
        combo.setId("Other Uses Combo");
     
        if((flagValue!=null)&&(!flagValue.equals("")))
		{
        	int index = PagePanelController.searchFlagComboIndex(flagValue);
			combo.setValue(CCBS.FLAGS.getAt(index));
		}
        else
        {
        	combo.setValue(CCBS.FLAGS.getAt(2));
        }
        //combo.addSelectionChangedListener(PagePanelController.otherUseListener(otherUsesFormField.getOtherUsesCombo(), mainSave, otherUsesFormField.getNorthDim()));
		
       hp.add(combo);
        
//        ComboBox<MonthForecast> combo; 
//        	combo = PagePanelController.addFlagCombo2();
        // combo.setAllowBlank(false);
      //   combo.setId("Other Uses Combo");
         
//         if((flagValue!=null)&&(!flagValue.equals("")))
// 		{
//         	int index = PagePanelController.searchFlagComboIndex(flagValue);
// 			combo.setValue(CCBS.FLAGS.getAt(index));
// 		}
         //combo.addSelectionChangedListener(PagePanelController.otherUseListener(otherUsesFormField.getOtherUsesCombo(), mainSave, otherUsesFormField.getNorthDim()));
 		
       //  hp.add(combo);
        
		fieldSetNorth.add(hp);
		//fieldSetNorthOtherUses = fieldSetNorth;
		return fieldSetNorth;
	}

	public OtherUsesFormField getOtherUseByFieldValue(FieldSet mainFieldSet) {
		System.out.println("FORM FIELD IS NOT VALID");
		double otherUsesTot = 0;
		String appValue = "";
		boolean isValidArray[] = new boolean[7]; 
		String otherUsesFlag = "";
		String otherUsesTotString = "";
		//Check if each element is valid and set the values
		HorizontalPanel hp;
		int i=0;
		for(Component component : mainFieldSet.getItems())
		{
			if(i<4)
			{
			hp = (HorizontalPanel) component;
			if(hp!=null)
			{
				ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>)hp.getItemByItemId("OtherUsesFlagCombo");
				if((combo!=null)&&(combo.getValue()!=null))
				{
					System.out.println("FLAG combo.getValue() "+ combo.getValue().getCode());
					String flagValue = combo.getValue().getCode();
					if((flagValue != null)&&(!(flagValue.equals(""))))
					{
						isValidArray[0] = true;
						otherUsesFlag = flagValue;
					}
				}
				NumberField nf = (NumberField)hp.getItemByItemId("OtherUsesSeedsField");
				if(nf!=null)
				{
					System.out.println("OtherUsesSeedsField nf.getValue() "+ nf.getValue());
					appValue = nf.getValue().toString();
					if((appValue != null)&&(!(appValue.equals(""))))
					{
						isValidArray[1] = true;
						otherUsesTot += Double.parseDouble(appValue);
						System.out.println("OtherUsesSeedsField otherUsesTot "+ otherUsesTot);
					}
				}
				combo = (ComboBox<AMISCodesModelData>)hp.getItemByItemId("OtherUsesSeedsCombo");
				if((combo!=null)&&(combo.getValue()!=null))
				{
					System.out.println("combo.getValue() "+ combo.getValue().getCode());
					appValue = combo.getValue().getCode();
					if((appValue != null)&&(!(appValue.equals(""))))
					{
						isValidArray[2] = true;
					}
				}
				nf = (NumberField)hp.getItemByItemId("OtherUsesPostHarvestField");
				if(nf!=null)
				{
					System.out.println("OtherUsesPostHarvestField nf.getValue() "+ nf.getValue());
					appValue = nf.getValue().toString();
					if((appValue != null)&&(!(appValue.equals(""))))
					{
						isValidArray[3] = true;
						otherUsesTot += Double.parseDouble(appValue);
						System.out.println("OtherUsesPostHarvestField otherUsesTot "+ otherUsesTot);
					}
				}
				combo = (ComboBox<AMISCodesModelData>)hp.getItemByItemId("OtherUsesPostHarvestCombo");
				if((combo!=null)&&(combo.getValue()!=null))
				{
					System.out.println("combo.getValue() "+ combo.getValue().getCode());
					appValue = combo.getValue().getCode();
					if((appValue != null)&&(!(appValue.equals(""))))
					{
						isValidArray[4] = true;
					}
				}
				nf = (NumberField)hp.getItemByItemId("Industrial Use");
				if(nf!=null)
				{
					System.out.println("Industrial Use nf.getValue() "+ nf.getValue());
					appValue = nf.getValue().toString();
					if((appValue != null)&&(!(appValue.equals(""))))
					{
						isValidArray[5] = true;
						otherUsesTot += Double.parseDouble(appValue);
						System.out.println("Industrial Use otherUsesTot "+ otherUsesTot);
					}
				}
				combo = (ComboBox<AMISCodesModelData>)hp.getItemByItemId("OtherUsesIndustrialUseCombo");
				if((combo!=null)&&(combo.getValue()!=null))
				{
					System.out.println("combo.getValue() "+ combo.getValue().getCode());
					appValue = combo.getValue().getCode();
					if((appValue != null)&&(!(appValue.equals(""))))
					{
						isValidArray[6] = true;
					}
				}	
			}
			}
			i++;
		}		
	
		OtherUsesFormField otherUsesFormFieldResult = new OtherUsesFormField(otherUsesTot, isValidArray, otherUsesFlag);

		return otherUsesFormFieldResult;
	}
	
	//Take the values from the north panel
	public OtherUsesFormField getOtherUseTotalValue(FieldSet mainFieldSet) {
		System.out.println("FORM FIELD IS NOT VALID");
		String appValue = "";
		//Check if each element is valid and set the values
		HorizontalPanel hp;
		OtherUsesFormField otherUsesFormFieldResult = new OtherUsesFormField();
		for(Component component : mainFieldSet.getItems())
		{
			hp = (HorizontalPanel) component;
			if(hp!=null)
			{
				ComboBox<AMISCodesModelData> combo = (ComboBox<AMISCodesModelData>)hp.getItemByItemId("Other Uses Combo");
				if((combo!=null)&&(combo.getValue()!=null))
				{
					System.out.println("FLAG combo.getValue() "+ combo.getValue().getCode());
					String flagValue = combo.getValue().getCode();
					otherUsesFormFieldResult.setFlagValue(flagValue);
				}
				NumberField nf = (NumberField)hp.getItemByItemId("Other Uses Num Field");
				if(nf!=null)
				{
					System.out.println("OtherUsesSeedsField nf.getValue() "+ nf.getValue());
					if(nf.getValue()!=null)
					{
						appValue = nf.getValue().toString();
						if((appValue != null)&&(!(appValue.equals(""))))
						{
							otherUsesFormFieldResult.setTotalValueNull(false);
							otherUsesFormFieldResult.setTotalValue(Double.parseDouble(appValue));
						}
					}
					else
					{
						otherUsesFormFieldResult.setTotalValueNull(true);
					}
				}
			}
		}
		return otherUsesFormFieldResult;
	}
	
	public String[] parseRowHeader(String rowHeader, String rowHeaderUnit)
	{
	//	System.out.println("Class: PagePanel Function: parseRowHeader Text: rowHeader "+ rowHeader);
		String result[] = new String[2];
		int firstBracket = rowHeader.indexOf("(");
		if(firstBracket != -1)
		{
			int secondBracket = rowHeader.indexOf(")");
//			System.out.println("Class: PagePanel Function: parseRowHeader Text: firstBracket "+ firstBracket);
	//		System.out.println("Class: PagePanel Function: parseRowHeader Text: secondBracket "+ secondBracket);
			//result[0] = new String(""+rowHeader.substring(0, (firstBracket-1)));
			result[0] = new String(""+rowHeader.substring(0, (firstBracket-1)));
//			System.out.println("Class: PagePanel Function: parseRowHeader Text: if rowHeader "+ rowHeader);
//			System.out.println("Class: PagePanel Function: parseRowHeader Text: if firstBracket "+ (firstBracket+1));
//			System.out.println("Class: PagePanel Function: parseRowHeader Text: if secondBracket "+ (secondBracket-1));
			result[1] = new String(""+rowHeader.substring(firstBracket +1, secondBracket));
			
			//result[1] = new String(""+rowHeader.substring(firstBracket +1, 20));
//			System.out.println("Class: PagePanel Function: parseRowHeader Text: if result[1] "+ result[1]);
		}
		else
		{
			result[0] = rowHeader;
			result[1] = rowHeaderUnit;
		}
		return result;
	}
	public static boolean firstProductionFound = false;
	public static boolean firstAreaHarvestedFound = false;
	public static boolean firstYieldFound = false;
	
	public boolean cropInTheGlobalList(int rowNum, int colNum, PagePanelVariables ppv)
	{
		boolean toReturn = false;
		String currentId = "row_"+rowNum+ "col_"+ colNum+"Crop_";
		String currentIdProduction = currentId + "Production";
		String currentIdAreaHarvested = currentId + "AreaHarvested";
		String currentIdYield = currentId + "Yield";
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM size "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
		for(ClientCbsDatasetResult clientElement :CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM)
		{
			System.out.println();
			
			String id = clientElement.getId();
			System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: client id "+id+ " client value type "+ clientElement.getValueType());
			System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: client id "+id+ " client value "+ clientElement.getValue());
			System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: client id "+id+ " client annotation "+ clientElement.getAnnotation());
			if(id.equals(currentIdProduction))
			{
				toReturn = true;
				String valueType = clientElement.getValueType();
				if(valueType.equals("CROP_1"))
				{
					ppv.firstProductionFound = true;
					ppv.firstNumberFieldProdValueFound = Double.parseDouble(clientElement.getValue());
					ppv.firstComboProdValueFound = clientElement.getAnnotation();					
				}
				if(valueType.equals("CROP_2"))
				{
					ppv.secondProductionFound = true;
					ppv.secondNumberFieldProdValueFound = Double.parseDouble(clientElement.getValue());
					ppv.secondComboProdValueFound = clientElement.getAnnotation();
				}
				if(valueType.equals("CROP_3"))
				{
					ppv.thirdProductionFound = true;
					ppv.thirdNumberFieldProdValueFound = Double.parseDouble(clientElement.getValue());
					ppv.thirdComboProdValueFound = clientElement.getAnnotation();
				}
			}
			
			if(id.equals(currentIdAreaHarvested))
			{
				toReturn = true;
				String valueType = clientElement.getValueType();
				if(valueType.equals("CROP_1"))
				{
					ppv.firstAreaHarvestedFound = true;
					ppv.firstNumberFieldAreaValueFound = Double.parseDouble(clientElement.getValue());
					ppv.firstComboAreaValueFound = clientElement.getAnnotation();	
				}
				if(valueType.equals("CROP_2"))
				{
					ppv.secondAreaHarvestedFound = true;
					ppv.secondNumberFieldAreaValueFound = Double.parseDouble(clientElement.getValue());
					ppv.secondComboAreaValueFound = clientElement.getAnnotation();	
				}
				if(valueType.equals("CROP_3"))
				{
					ppv.thirdAreaHarvestedFound = true;
					ppv.thirdNumberFieldAreaValueFound = Double.parseDouble(clientElement.getValue());
					ppv.thirdComboAreaValueFound = clientElement.getAnnotation();	
				}
			}
			
			if(id.equals(currentIdYield))
			{
				toReturn = true;
				String valueType = clientElement.getValueType();
				if(valueType.equals("CROP_1"))
				{
					ppv.firstYieldFound = true;
					ppv.firstNumberFieldYieldValueFound = Double.parseDouble(clientElement.getValue());
					ppv.firstComboFieldYieldValueFound = clientElement.getAnnotation();	
				}
				if(valueType.equals("CROP_2"))
				{
					ppv.secondYieldFound = true;
					ppv.secondNumberFieldYieldValueFound = Double.parseDouble(clientElement.getValue());
					ppv.secondComboYieldValueFound = clientElement.getAnnotation();	
				}
				if(valueType.equals("CROP_3"))
				{
					ppv.thirdYieldFound = true;
					ppv.thirdNumberFieldYieldValueFound = Double.parseDouble(clientElement.getValue());
					ppv.thirdComboYieldValueFound = clientElement.getAnnotation();	
				}
			}
		}
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.firstProductionFound= "+ppv.firstProductionFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.firstNumberFieldProdValueFound= "+ppv.firstNumberFieldProdValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.firstComboProdValueFound= "+ppv.firstComboProdValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.secondProductionFound= "+ppv.secondProductionFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.secondNumberFieldProdValueFound= "+ppv.secondNumberFieldProdValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.secondComboProdValueFound= "+ppv.secondComboProdValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.thirdProductionFound= "+ppv.thirdProductionFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.thirdNumberFieldProdValueFound= "+ppv.thirdNumberFieldProdValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.thirdComboProdValueFound= "+ppv.thirdComboProdValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.firstAreaHarvestedFound= "+ppv.firstAreaHarvestedFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.firstNumberFieldAreaValueFound= "+ppv.firstNumberFieldAreaValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.firstComboAreaValueFound= "+ppv.firstComboAreaValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.secondAreaHarvestedFound= "+ppv.secondAreaHarvestedFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.secondNumberFieldAreaValueFound= "+ppv.secondNumberFieldAreaValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.secondComboAreaValueFound= "+ppv.secondComboAreaValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.thirdAreaHarvestedFound= "+ppv.thirdAreaHarvestedFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.thirdNumberFieldAreaValueFound= "+ppv.thirdNumberFieldAreaValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.thirdComboAreaValueFound= "+ppv.thirdComboAreaValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.firstYieldFound= "+ppv.firstYieldFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.firstNumberFieldYieldValueFound= "+ppv.firstNumberFieldYieldValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.firstComboYieldValueFound= "+ppv.firstComboFieldYieldValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.secondYieldHarvestedFound= "+ppv.secondYieldFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.secondNumberFieldYieldValueFound= "+ppv.secondNumberFieldYieldValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.secondComboYieldValueFound= "+ppv.secondComboYieldValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.thirdYieldFound= "+ppv.thirdYieldFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.thirdNumberFieldYieldValueFound= "+ppv.thirdNumberFieldYieldValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.thirdComboYieldValueFound= "+ppv.thirdComboYieldValueFound);
		
		
		return toReturn;
	}
	
	public boolean industrialUseInTheGlobalList(int rowNum, int colNum, PagePanelVariables ppv)
	{
		boolean toReturn = false;
		String currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_IndustrialUse_";		
		String maltCurrentId = currentId+ "Malt";
		String biofuelCurrentId = currentId+ "Biofuel";
		String sweetenersCurrentId = currentId+ "Sweeteners";
		String starchCurrentId = currentId+ "Starch";
		String othersCurrentId = currentId+ "Others";
	
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM size "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
		for(ClientCbsDatasetResult clientElement :CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM)
		{
			System.out.println();
			
			String id = clientElement.getId();
			System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: client id "+id+ " client value type "+ clientElement.getValueType());
			System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: client id "+id+ " client value "+ clientElement.getValue());
			System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: client id "+id+ " client annotation "+ clientElement.getAnnotation());
			if(id.equals(maltCurrentId))
			{
				toReturn = true;			
				ppv.maltFound = true;
				ppv.maltNumberFieldValueFound = Double.parseDouble(clientElement.getValue());
				ppv.maltComboValueFound = clientElement.getAnnotation();
			}			
			if(id.equals(biofuelCurrentId))
			{
				toReturn = true;			
				ppv.biofuelFound = true;
				ppv.biofuelNumberFieldValueFound = Double.parseDouble(clientElement.getValue());
				ppv.biofuelComboValueFound = clientElement.getAnnotation();
			}			
			if(id.equals(sweetenersCurrentId))
			{
				toReturn = true;			
				ppv.sweetenersFound = true;
				ppv.sweetenersNumberFieldValueFound = Double.parseDouble(clientElement.getValue());
				ppv.sweetenersComboFieldYieldValueFound = clientElement.getAnnotation();
			}
			if(id.equals(starchCurrentId))
			{
				toReturn = true;			
				ppv.starchFound = true;
				ppv.starchNumberFieldValueFound = Double.parseDouble(clientElement.getValue());
				ppv.starchComboValueFound = clientElement.getAnnotation();
			}
			if(id.equals(othersCurrentId))
			{
				toReturn = true;			
				ppv.othersFound = true;
				ppv.othersNumberFieldValueFound = Double.parseDouble(clientElement.getValue());
				ppv.othersComboFieldYieldValueFound = clientElement.getAnnotation();
			}
		}
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.seedsFound= "+ppv.firstProductionFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.seedsNumberFieldValueFound= "+ppv.firstNumberFieldProdValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.seedsComboProdValueFound= "+ppv.firstComboProdValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.postHarvestedLossesFound= "+ppv.secondProductionFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.postHarvestedLossesNumberFieldValueFound= "+ppv.secondNumberFieldProdValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.postHarvestedLossesComboAreaValueFound= "+ppv.secondComboProdValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.industrialUseFound= "+ppv.thirdProductionFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.industrialUseNumberFieldValueFound= "+ppv.thirdNumberFieldProdValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.industrialUseComboFieldYieldValueFound= "+ppv.thirdComboProdValueFound);
		
		return toReturn;
	}
	
	public boolean otherUsesInTheGlobalList(int rowNum, int colNum, PagePanelVariables ppv)
	{
		boolean toReturn = false;
		String currentId = "row_"+rowNum+ "col_"+ colNum+"SubElement_Main_";
		//String valueType = "SUBELEMENT";
		String currentIdSeeds = currentId + "Seeds";
		String currentIdPostHarvestLosses = currentId + "PostHarvestLosses";
		String currentIdIndustrialUse = currentId + "IndustrialUse";
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM size "+CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.size());
		for(ClientCbsDatasetResult clientElement :CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM)
		{
			System.out.println();
			
			String id = clientElement.getId();
			System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: client id "+id+ " client value type "+ clientElement.getValueType());
			System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: client id "+id+ " client value "+ clientElement.getValue());
			System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: client id "+id+ " client annotation "+ clientElement.getAnnotation());
			if(id.equals(currentIdSeeds))
			{
				toReturn = true;			
				ppv.seedsFound = true;
				ppv.seedsNumberFieldValueFound = Double.parseDouble(clientElement.getValue());
				ppv.seedsComboProdValueFound = clientElement.getAnnotation();
			}			
			if(id.equals(currentIdPostHarvestLosses))
			{
				toReturn = true;			
				ppv.postHarvestedLossesFound = true;
				ppv.postHarvestedLossesNumberFieldValueFound = Double.parseDouble(clientElement.getValue());
				ppv.postHarvestedLossesComboAreaValueFound = clientElement.getAnnotation();
			}			
			if(id.equals(currentIdIndustrialUse))
			{
				toReturn = true;			
				ppv.industrialUseFound = true;
				ppv.industrialUseNumberFieldValueFound = Double.parseDouble(clientElement.getValue());
				ppv.industrialUseComboFieldYieldValueFound = clientElement.getAnnotation();
			}
		}
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.seedsFound= "+ppv.firstProductionFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.seedsNumberFieldValueFound= "+ppv.firstNumberFieldProdValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.seedsComboProdValueFound= "+ppv.firstComboProdValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.postHarvestedLossesFound= "+ppv.secondProductionFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.postHarvestedLossesNumberFieldValueFound= "+ppv.secondNumberFieldProdValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.postHarvestedLossesComboAreaValueFound= "+ppv.secondComboProdValueFound);
		
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.industrialUseFound= "+ppv.thirdProductionFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.industrialUseNumberFieldValueFound= "+ppv.thirdNumberFieldProdValueFound);
		System.out.println("Class: SpecificRowWindow Function: cropInTheGlobalList Text: ppv.industrialUseComboFieldYieldValueFound= "+ppv.thirdComboProdValueFound);
		
		return toReturn;
	}
	
	public static AMISCodesModelData getObjectByFlagCombo(String flagFound)
	{
		System.out.println("Class: SpecificRowWindow Function: getObjectByFlagCombo Text: flagFound= "+flagFound);
		AMISCodesModelData aMISCodesModelData = null;
		for(int i=0; i< CCBS.FLAGS.getCount(); i++)
		{
			//System.out.println("Class: SpecificRowWindow Function: getObjectByFlagCombo Text: flagFound= "+flagFound);
			//if(flagFound.equals(CCBS.FLAGS.getAt(i).getLabel()))
			if(flagFound.equals(CCBS.FLAGS.getAt(i).getCode()))
			{
				aMISCodesModelData = CCBS.FLAGS.getAt(i);
				break;
			}
		}
		
		return aMISCodesModelData;
	}
}
