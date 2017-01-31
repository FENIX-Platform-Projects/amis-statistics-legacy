package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import java.util.List;
import java.util.Set;

import org.fao.fenix.web.modules.amis.client.control.inputccbs.PagePanelController;
import org.fao.fenix.web.modules.amis.client.lang.AMISLanguage;
import org.fao.fenix.web.modules.amis.client.view.input.AMISInput;
import org.fao.fenix.web.modules.amis.common.constants.AMISConstants;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;
import org.fao.fenix.web.modules.amis.common.services.AMISServiceEntry;
import org.fao.fenix.web.modules.amis.common.vo.AMISQueryVO;
import org.fao.fenix.web.modules.amis.common.vo.AMISResultVO;
import org.fao.fenix.web.modules.amis.common.vo.CCBS;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

public class CCBSWindow extends FenixWindow
{
	protected BookPanel           bookPanel;
	protected CCBSParametersPanel ccbsParametersPanel;
	protected String              gaulCodes[], gaulNames[], elementUnits[], subElementUnits[];
	private  Button save, cancel, saveAndClose;
	private Button showFlags, showHideFlags, countryInformation;
	private AMISInput amisInput;

	public void build(AMISInput amisInput)
	{
		getWindow().setClosable(false);
		this.amisInput = amisInput;
		System.out.println("Class: CCBSWindow Function:build Text: Build Start");
		//setSize(1000, 400);
		setTitle(AMISLanguage.print().ccbsTool());
		getWindow().setCollapsible(true);
		getWindow().setHeading(AMISLanguage.print().ccbsTool());
//		getWindow().setPosition(70, 70);
		int oneSixthWidth = Window.getClientWidth()/6;
		int oneTenthHeight = Window.getClientHeight()/10;
//		System.out.println("Class: CCBSWindow Function:build Text: oneQuarterWidth "+oneSixthWidth);
//		System.out.println("Class: CCBSWindow Function:build Text: oneQuarterHeight "+oneTenthHeight);
		getWindow().setPosition(oneSixthWidth, oneTenthHeight);
		buildToolbar();
		loadFlags();
		fillCenterPart();
		System.out.println("Class: CCBSParametersPanel Function:setBottomComponent Text: before setBottomComponent");
		HorizontalPanel hpTot = new HorizontalPanel();
		hpTot.add(insertButton());
	//	hpTot.add(insertInformationPanel());
		//getCenter().setBottomComponent(hpTot);
		getCenter().setBottomComponent(insertButton());
	//	getCenter().setTopComponent(insertInformationPanel());
		System.out.println("Class: CCBSParametersPanel Function:setBottomComponent Text: after setBottomComponent");
	}

//	private void loadElementUnits()
//	{
//		try {
//				AMISQueryVO qvo =  new AMISQueryVO();
//				qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
//				qvo.setTypeOfOutput(AMISConstants.AMIS_ELEMENTS.toString());
//				qvo.setElementsWithUnit(true);
//				AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
//					
//					@SuppressWarnings("unchecked")
//					public void onSuccess(AMISResultVO vo) {
//								fillMeasurementUnits(vo.getCodes());
//								buildCenterPanel();
//								loadGaulCodes();
//                                   }
//								public void onFailure(Throwable caught)
//                                   {
//                                       FenixAlert.alert("RPC Failed", "CCBSService.getCCBSGaulCodes()");
//                                   }
//                               });
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//	}
	
	private void loadFlags()
	{
		try {
				AMISQueryVO qvo =  new AMISQueryVO();
				qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
				qvo.setTypeOfOutput(AMISConstants.AMIS.toString());
				qvo.setFlags(true);
			//	System.out.println("Class CCBSWindow Function: loadFlags Text:  Flag "+ qvo.isFlags());
				AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
					
					@SuppressWarnings("unchecked")
					public void onSuccess(AMISResultVO vo) {
						//Fill the list of the flags
								CCBS.FLAGS = fillStoreFromFlags(vo.getCodes());
							//	System.out.println("Class CCBSWindow Function: loadFlags Text:  CCBS.FLAGS.getCount() "+ CCBS.FLAGS.getCount());
								loadElementUnits();
                                   }
								public void onFailure(Throwable caught)
                                   {
                                       FenixAlert.alert("RPC Failed", "CCBSService.getCCBSGaulCodes()");
                                   }
                               });
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
	
	public static ListStore<AMISCodesModelData> fillStoreFromFlags(List<AMISCodesModelData> codes)
	{
		 ListStore<AMISCodesModelData> flags = new ListStore<AMISCodesModelData>();
		// flags.add(new AMISCodesModelData("No Flag","No Flag"));
		 for(int iFlags=0; iFlags<codes.size(); iFlags++)
		 {
			// System.out.println("Class CCBSWindow Function: loadFlags Text:  iFlags= "+ iFlags+" codes.get(iFlags) = "+codes.get(iFlags).getCode());
			 flags.add(codes.get(iFlags));
		 }
		 return flags;
	}
	
	private void loadElementUnits()
	{
		try {
				AMISQueryVO qvo =  new AMISQueryVO();
				qvo.setOutput(AMISConstants.CODING_SYSTEM.toString());
				qvo.setTypeOfOutput(AMISConstants.AMIS_ELEMENTS.toString());
				//qvo.setElementsWithUnit(true);
				qvo.setElementsUnitsWithoutBrackets(true);
				AMISServiceEntry.getInstance().askAMIS(qvo, new AsyncCallback<AMISResultVO>() {
					
					@SuppressWarnings("unchecked")
					public void onSuccess(AMISResultVO vo) {
								fillMeasurementUnits(vo.getCodes());
								//loadGaulCodes();
								 continueBuild();
								//buildCenterPanel();
                                   }
								public void onFailure(Throwable caught)
                                   {
                                       FenixAlert.alert("RPC Failed", "CCBSService.getCCBSGaulCodes()");
                                   }
                               });
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
	
	 private void fillMeasurementUnits(List<AMISCodesModelData> codes) {
		 int codesLength = CCBS.FIELD_NAMES.length;
		 int subElementCodeLength = CCBS.SUB_ELEMENTS_NAMES.length;
		 //System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: codesLength "+codesLength);
		 elementUnits = new String[codesLength];
		 subElementUnits = new String[subElementCodeLength];
		 int iElement=0;
		 for(iElement=0; iElement< codesLength; iElement++)
		 {
			// System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: Before iElement "+iElement+ " codesLength "+codesLength);
			 elementUnits[iElement] = getUnitsForEachElement(CCBS.FIELD_NAMES[iElement], codes);
			// System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: After iElement "+iElement+ " elementUnits[iElement] "+elementUnits[iElement]);
				//System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: iElement "+iElement+" elementUnits[iElement] "+  elementUnits[iElement]);
		 }
		 for(iElement=0; iElement< subElementCodeLength; iElement++)
		 {
		//	 System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: Before iElement "+iElement+ " codesLength "+codesLength);
			 subElementUnits[iElement] = getUnitsForEachSubElement(CCBS.SUB_ELEMENTS_NAMES[iElement], codes);
		//	 System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: After iElement "+iElement+ " elementUnits[iElement] "+elementUnits[iElement]);
				//System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: iElement "+iElement+" elementUnits[iElement] "+  elementUnits[iElement]);
		 }
		 System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: Total loop start ");
		 for(iElement=0; iElement< codesLength; iElement++)
		 {
		//	 System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: iElement "+iElement+ " elementUnits[iElement] "+elementUnits[iElement]);
			// System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: iElement "+iElement+ " CCBS.FIELD_NAMES[iElement] "+CCBS.FIELD_NAMES[iElement]);
		//	 System.out.println("");
			// System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: iElement "+iElement+" elementUnits[iElement] "+  elementUnits[iElement]);
		 }
		// System.out.println("Class: CCBSWindow Function: fillMeasurementUnits Text: Total loop end ");
		}

	private String getUnitsForEachElement(String fieldName, List<AMISCodesModelData> codes) {
	//	System.out.println("\n\nClass: CCBSWindow Function: getUnitsForEachElement Text: fieldName "+fieldName);
		String newFieldName = changeLabel(fieldName);
	//	System.out.println("Class: CCBSWindow Function: getUnitsForEachElement Text: newFieldName "+newFieldName);
		 for(AMISCodesModelData topcode : codes) {
		//	System.out.println("Class: CCBSWindow Function: getUnitsForEachElement Text: topcode.getLabel() "+topcode.getLabel());
		//	System.out.println("Class: CCBSWindow Function: getUnitsForEachElement Text: topcode.getCode() "+topcode.getCode());
			 if(topcode.getCode().equals(newFieldName))
			 {
		//		System.out.println("Class: CCBSWindow Function: getUnitsForEachElement Text: Uguali!!! topcode.getLabel() " + topcode.getCode());
				 return topcode.getLabel();
			 }
		 }
		 
		return "";
	}
	
	private String getUnitsForEachSubElement(String fieldName, List<AMISCodesModelData> codes) {
		//	System.out.println("\n\nClass: CCBSWindow Function: getUnitsForEachElement Text: fieldName "+fieldName);
		//	System.out.println("Class: CCBSWindow Function: getUnitsForEachElement Text: newFieldName "+newFieldName);
			 for(AMISCodesModelData topcode : codes) {
			//	System.out.println("Class: CCBSWindow Function: getUnitsForEachElement Text: topcode.getLabel() "+topcode.getLabel());
			//	System.out.println("Class: CCBSWindow Function: getUnitsForEachElement Text: topcode.getCode() "+topcode.getCode());
				 if(topcode.getCode().equals(fieldName))
				 {
			//		System.out.println("Class: CCBSWindow Function: getUnitsForEachElement Text: Uguali!!! topcode.getLabel() " + topcode.getCode());
					 return topcode.getLabel();
				 }
			 }
			return "";
		}
	
	private String changeLabel(String fieldName) {
		String newFieldName = fieldName;
		if(fieldName.equalsIgnoreCase("Total Supply"))
		{
			newFieldName = "Supply";
		}
		else if(fieldName.equalsIgnoreCase("Domestic Utilization"))
		{
			newFieldName = "Utilization";
		}
		else if(fieldName.equalsIgnoreCase("Of which Government"))
		{
			newFieldName = "Government Stocks";
		}
		else if(fieldName.equalsIgnoreCase("Per Cap. Food Use"))
		{
			newFieldName = "Per Capita Food Use";
		}
			
		return newFieldName;
	}
	
//	private void loadGaulCodes()
//	{
//		AMISServiceEntry.getInstance().getCCBSGaulCodes(new AsyncCallback<String[]>()
//		                                           {
//			                                           public void onSuccess(String result[])
//			                                           {
//				                                           gaulCodes = result;
//				                                           loadGaulNames();
//			                                           }
//
//			                                           public void onFailure(Throwable caught)
//			                                           {
//				                                           FenixAlert.alert("RPC Failed", "CCBSService.getCCBSGaulCodes()");
//			                                           }
//		                                           });
//	}
//
//	private void loadGaulNames()
//	{
//		AMISServiceEntry.getInstance().getGaulNames(gaulCodes, new AsyncCallback<String[]>()
//		                                           {
//			                                           public void onSuccess(String result[])
//			                                           {
//				                                           gaulNames = result;
//				                                           continueBuild();
//			                                           }
//
//			                                           public void onFailure(Throwable caught)
//			                                           {
//				                                           FenixAlert.alert("RPC Failed", "CCBSService.getGaulNames()");
//			                                           }
//		                                           });
//	}
	

	private void continueBuild()
	{
		//buildWestPanel();
		buildNorthPanel();
		//setSize(1000, 400); // redo now, otherwise it is not rendered correctly
		//setSize(947, 780);
		setSize(947, 740);
		if(amisInput.getDataSourceSelected().getLabel().equals(CCBS.elementForTraining))
		{
			PagePanelController.showInfoPopup();
		}
	}

	protected void buildToolbar()
	{
		// TODO CCBSToolbar extending FenixToolbar
	}

//	protected void buildWestPanel()
//	{
//		//ccbsParametersPanel = new CCBSParametersPanel(bookPanel, gaulCodes, gaulNames);
//		ccbsParametersPanel = new CCBSParametersPanel(bookPanel, gaulCodes);
//		fillWestPart(ccbsParametersPanel);
//		getWestData().setSize(250);
//		//getWest().setHeading(BabelFish.print().parameters()); // TODO: improve title
//	}

//	protected void buildNorthPanel()
//	{
//		//ccbsParametersPanel = new CCBSParametersPanel(bookPanel, gaulCodes, gaulNames);
//		ccbsParametersPanel = new CCBSParametersPanel(this, bookPanel, gaulCodes);
//		fillNorthPart(ccbsParametersPanel);
//		getNorthData().setSize(200);
//		//getNorth().setSize(1030, 50);
//		getNorth().setHeading(BabelFish.print().parameters()); // TODO: improve title 
//	}
	
	protected void buildNorthPanel()
	{
		//ccbsParametersPanel = new CCBSParametersPanel(bookPanel, gaulCodes, gaulNames);
		ccbsParametersPanel = new CCBSParametersPanel(this, gaulCodes, amisInput);
		fillNorthPart(ccbsParametersPanel);
		//getNorthData().setSize(200);
		getNorthData().setSize(120);
		//getNorth().setSize(1030, 50);
		getNorth().setHeading(AMISLanguage.print().ccbsToolTitle() + " - "+amisInput.getCountrySelected().getLabel()+ " ("+amisInput.getDataSourceSelected().getLabel()+")"); // TODO: improve title 
		System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsWindow before call insertIconsButtons");
		getNorth().setTopComponent(insertIconsButton());
		System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsWindow after call insertIconsButtons");
	}
	
//	protected void buildCenterPanel()
//	{
//		PageDecoration pageDecorations[] = new PageDecoration[CCBS.COMMODITY_NAMES.length];
//		for (int numPage = 0; numPage < CCBS.COMMODITY_NAMES.length; numPage++)
//		{
//			String  colHeaders[]       = new String[CCBS.YEARS.length * 2];
//			int     colHeadersWidths[] = new int[CCBS.YEARS.length * 2];
//			boolean editableCols[]     = new boolean[CCBS.YEARS.length * 2];
//			for (int i = 0; i < CCBS.YEARS.length; i++)
//			{
//				int colNum = i * 2;
//				colHeaders[colNum]           = CCBS.YEARS[i]+"";
//				//colHeaders[colNum]           = parseYear(CCBS.YEARS[i]);
//				colHeadersWidths[colNum]     = 80;
//				editableCols[colNum]         = numPage < CCBS.COMMODITY_NAMES.length - 2; // skip last 2 pages;
//				colHeaders[colNum + 1]       = "";
//				colHeadersWidths[colNum + 1] = 20;
//				editableCols[colNum + 1]     = false;
//			}
//			pageDecorations[numPage] = new PageDecoration("",
//			                                              CCBS.FIELD_NAMES, 120,              CCBS.FIELD_EDITABLE,
//			                                              colHeaders,       colHeadersWidths, editableCols);
//		}
//		bookPanel = new BookPanel(CCBS.COMMODITY_NAMES, pageDecorations);
//		fillCenterPart(bookPanel);
//		HorizontalPanel hp = new HorizontalPanel();
//		hp.setHeight(30);
////		HorizontalAlignment align = HorizontalAlignment.RIGHT;
////		hp.setButtonAlign(align); 
//		
////		HBoxLayout layout = new HBoxLayout();  
////		layout.setPadding(new Padding(5));  
////		layout.setHBoxLayoutAlign(HBoxLayoutAlign.BOTTOM);
////		layout.setPack(BoxLayoutPack.END);  
////		hp.setLayout(layout);  
////		HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 5, 0, 0));  
////		hp.add(new Button("Ciao! "), layoutData);  
//		//hp.add(new Button(button1Text), new HBoxLayoutData(new Margins(0, 5, 0, 0)));
//		Button cancel = new Button("Cancel ");
//		cancel.setWidth(80);
//		cancel.setBorders(true);
//		cancel.setIconStyle("cancel");
//		cancel.setIconAlign(IconAlign.RIGHT);
//		//b.setMenuAlign("RIGHT");
//		hp.add(cancel);
//		Button save = new Button("Save", new SelectionListener<ButtonEvent>() {  
//			@Override  
//			public void componentSelected(ButtonEvent ce) { 
//				MessageBox.info("Action", "The data have been saved", null);
//			  }  
//			});  
//		save.setWidth(80);
//		save.setIconStyle("save");
//		save.setIconAlign(IconAlign.RIGHT);
//		save.setBorders(true);
//		//b.setMenuAlign("RIGHT");
//		hp.add(save);
//		
//		//getCenter().setBottomComponent(new Button("Ciao!"));
//		//getCenter().setBottomComponent(hp);
//		getCenter().setTopComponent(hp);
//		getCenterData().setSize(600);
//		getCenter().setHeading(BabelFish.print().table()); // TODO: improve title
//		
//		//getCenter().setHeaderVisible(false);
//	}

//	protected BookPanel buildCenterPanel(String formData[])
//	{
//		
//		Html html = new Html("Added");
//		//fillCenterPart();
//		getCenter().add(html);
//	
//		return bookPanel;
//	}
	
	private HorizontalPanel insertButton()
	{
		HorizontalPanel p1 = new HorizontalPanel();
		p1.setTableWidth("100%");
		p1.setHorizontalAlign(HorizontalAlignment.RIGHT);
		p1.setVerticalAlign(VerticalAlignment.MIDDLE);

		HorizontalPanel p2 = new HorizontalPanel();
		cancel = new Button("Cancel ");
		cancel.setEnabled(false);
		cancel.setWidth(80);
		cancel.setBorders(true);
		cancel.setIconStyle("cancel");
		cancel.setIconAlign(IconAlign.RIGHT);
		cancel.addSelectionListener(PagePanelController.cancelGrid(this));
		cancel.setEnabled(true);
		
//		save = new Button("Save", new SelectionListener<ButtonEvent>() {  
//			@Override  
//			public void componentSelected(ButtonEvent ce) { 
//				MessageBox.info("Action", "The data have been saved", null);
//				
//			  }  
//			});  
		save = new Button("Save");
		//save.addSelectionListener(PagePanelController.saveGrid(bookPanel, 0));
		save.setEnabled(false);
		save.setWidth(80);
		save.setIconStyle("save");
		save.setIconAlign(IconAlign.RIGHT);
		save.setBorders(true);
		
				
		
		saveAndClose = new Button("Save And Close");
		saveAndClose.setEnabled(false);
		saveAndClose.setWidth(110);
		saveAndClose.setIconStyle("save");
		saveAndClose.setIconAlign(IconAlign.RIGHT);
		saveAndClose.setBorders(true);
		p2.add(save);
		p2.add(saveAndClose);
		p2.add(cancel);
		p1.add(p2);
		
		return p1;
	
	}


	private HorizontalPanel insertIconsButton()
	{
		System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsWindow insertIconsButtons");
		HorizontalPanel p1 = new HorizontalPanel();
		p1.setTableWidth("100%");
		p1.setHorizontalAlign(HorizontalAlignment.RIGHT);
		p1.setVerticalAlign(VerticalAlignment.MIDDLE);

		HorizontalPanel p2 = new HorizontalPanel();
		countryInformation = new Button(AMISLanguage.print().iconViewCountryInformation());
		countryInformation.setEnabled(false);
		countryInformation.setWidth(120);
		countryInformation.setBorders(true);
	//	System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsShowFlagsWindow 1");
		CcbsShowCbsInformationWindow ccbsShowCountryInformationWindow = new CcbsShowCbsInformationWindow();
		System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsShowFlagsWindow 2");
		String width = "700";
		String height;
		//ccbsShowCountryInformationWindow.build(350, Integer.parseInt(height)+4);
	//	ccbsShowCountryInformationWindow.build(420, 210);
	//	System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsShowFlagsWindow 3 amisInput.getDataSourceSelected().getLabel()"+amisInput.getDataSourceSelected().getLabel());
		countryInformation.enable();
//		if(amisInput.getDataSourceSelected().getLabel().equals("FAO-CBS"))
//		{
//			height =""+ (20 * CCBS.COMMODITY_NAMES_CBS.size());
//			ccbsShowCountryInformationWindow.build("420", "145");
//			System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsShowFlagsWindow 3 IF amisInput.getDataSourceSelected().getLabel()"+amisInput.getDataSourceSelected().getLabel());
//			countryInformation.addSelectionListener(PagePanelController.showCountryInformation(ccbsShowCountryInformationWindow, width, "110", this, amisInput));
//		}
//		else
//		{
//			System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsShowFlagsWindow 3 ELSE amisInput.getDataSourceSelected().getLabel()"+amisInput.getDataSourceSelected().getLabel());
//			//Different height
//			ccbsShowCountryInformationWindow.build("420", "210");
//			height =""+ (30 * CCBS.COMMODITY_NAMES_CBS.size());
//			countryInformation.addSelectionListener(PagePanelController.showCountryInformation(ccbsShowCountryInformationWindow, width, "180", this, amisInput));
//		}
		
		height =""+ (20 * CCBS.COMMODITY_NAMES_CBS.size());
		ccbsShowCountryInformationWindow.build("420", "145");
	//	System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsShowFlagsWindow 3 IF amisInput.getDataSourceSelected().getLabel()"+amisInput.getDataSourceSelected().getLabel());
		countryInformation.addSelectionListener(PagePanelController.showCountryInformation(ccbsShowCountryInformationWindow, width, "110", this, amisInput));
		
		p2.add(countryInformation);
		
		
		
		
		showFlags = new Button(AMISLanguage.print().iconViewCcbsFlag());
		showFlags.setEnabled(false);
		showFlags.setWidth(90);
		showFlags.setBorders(true);
		//showFlags.setIconStyle("amisFlags");
		//showFlags.setTitle(AMISLanguage.print().iconViewCcbsFlag());
		//showFlags.setIconAlign(IconAlign.LEFT);
	//	System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsShowFlagsWindow 1");
		CcbsShowCbsInformationWindow ccbsShowFlagsWindow = new CcbsShowCbsInformationWindow();
	//	System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsShowFlagsWindow 2");
		width = "500";
		height =""+ (30 * CCBS.FLAGS.getCount());
		ccbsShowFlagsWindow.build("260", (Integer.parseInt(height)+4)+"");
	//	System.out.println("Class: CcbsShowFlagsWindow Function:build Text: Before CcbsShowFlagsWindow 3");
		showFlags.enable();
		showFlags.addSelectionListener(PagePanelController.showFlags(ccbsShowFlagsWindow, width, height));
		p2.add(showFlags);
		
//		save = new Button("Save", new SelectionListener<ButtonEvent>() {  
//			@Override  
//			public void componentSelected(ButtonEvent ce) { 
//				MessageBox.info("Action", "The data have been saved", null);
//				
//			  }  
//			});  
		showHideFlags = new Button(AMISLanguage.print().iconShowHideCcbsFlag());
	//	showHideFlags.addSelectionListener(PagePanelController.showHideFlagsOfTheGrid(bookPanel, 0, this));
	//	showHideFlags.setEnabled(false);
		showHideFlags.setWidth(95);
		//showHideFlags.setIconStyle("amisFlags");
		showHideFlags.setIconAlign(IconAlign.RIGHT);
		showHideFlags.setBorders(true);
		showHideFlags.disable();
		p2.add(showHideFlags);
		p1.add(p2);
		return p1;	
	}

//	private HorizontalPanel insertInformationPanel()
//	{
//		HorizontalPanel p1 = new HorizontalPanel();
//		p1.setTableWidth("100%");
//		p1.setHorizontalAlign(HorizontalAlignment.LEFT);
//		p1.setVerticalAlign(VerticalAlignment.MIDDLE);
//
//		HorizontalPanel p2 = new HorizontalPanel();
//		p2.setSpacing(6);
//		String writing = "Fc* = Forecast;";
//		Label labelInfo = new Label(writing);
//		labelInfo.setStyleName("ccbs-Label");
//		labelInfo.setWidth("240px");
//		p2.add(labelInfo);
//		writing = "Val* = Validated (Official Data)";
//		labelInfo = new Label(writing);
//		labelInfo.setStyleName("ccbs-Label");
//		labelInfo.setWidth("280px");
//		p2.add(labelInfo);
//		writing = "Est* = Estimate (Last estimate of the marketing year)";
//		labelInfo = new Label(writing);
//		labelInfo.setStyleName("ccbs-Label");
//		labelInfo.setWidth("400px");
//		p2.add(labelInfo);
//		p1.add(p2);
//		return p1;
//	}
	
	private HorizontalPanel insertInformationPanel()
	{
		HorizontalPanel p1 = new HorizontalPanel();
		p1.setTableWidth("100%");
		p1.setHorizontalAlign(HorizontalAlignment.LEFT);
		p1.setVerticalAlign(VerticalAlignment.MIDDLE);

		HorizontalPanel p2 = new HorizontalPanel();
		//p2.setSpacing(6);
		String writing = "---------------------------------------------------------------------------------HISTORICAL DATA---------------------------------------------------------------------------------FORECAST-----------------------";
//		String writing = "Fc* = Forecast;";
//		Label labelInfo = new Label(writing);
//		labelInfo.setStyleName("ccbs-Label");
//		labelInfo.setWidth("240px");
//		p2.add(labelInfo);
//		writing = "Val* = Validated (Official Data)";
//		labelInfo = new Label(writing);
//		labelInfo.setStyleName("ccbs-Label");
//		labelInfo.setWidth("280px");
//		p2.add(labelInfo);
//		writing = "Est* = Estimate (Last estimate of the marketing year)";
//		labelInfo = new Label(writing);
//		labelInfo.setStyleName("ccbs-Label");
//		labelInfo.setWidth("400px");
//		p2.add(labelInfo);
		Label labelInfo = new Label(writing);
		labelInfo.setStyleName("ccbs-Label");
		labelInfo.setWidth("980px");
		p2.add(labelInfo);
		p1.add(p2);
		return p1;
	}
	
	
//	protected BookPanel buildCenterPanel()
//	{
////		System.out.println("Class: CCBSWindow Function: buildCenterPanel Text: formData[0] " + formData[0]);
////		System.out.println("Class: CCBSWindow Function: buildCenterPanel Text: formData[1] " + formData[1]);
////		System.out.println("Class: CCBSWindow Function: buildCenterPanel Text: formData[2] " + formData[2]);
////		System.out.println("Class: CCBSWindow Function: buildCenterPanel Text: formData[3] " + formData[3]);
////		System.out.println("Class: CCBSWindow Function: buildCenterPanel Text: formData[4] " + formData[4]);
//		
//		System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:CCBS.YEARS.size() !!!!!!!!!!!!!!! " + CCBS.YEARS.size());
//		PageDecoration pageDecorations[] = new PageDecoration[CCBS.COMMODITY_NAMES.length];
//		for (int numPage = 0; numPage < CCBS.COMMODITY_NAMES.length; numPage++)
//		{
//			int yearIndex = 0;
//			//Setting the number of the column as the number of years to show 
//			if(CCBS.NUMBER_OF_YEARS > CCBS.YEARS.size())
//			{
//				CCBS.NUMBER_OF_YEARS = CCBS.YEARS.size();
//				CCBS.NAME_FIRST_YEARS_TO_SHOW = CCBS.YEARS.get(0);
//				CCBS.NUMBER_FIRST_YEARS_TO_SHOW = 0;
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1 yearIndex " + yearIndex);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1 CCBS.NUMBER_OF_YEARS " + CCBS.NUMBER_OF_YEARS);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1 CCBS.NAME_FIRST_YEARS_TO_SHOW " + CCBS.NAME_FIRST_YEARS_TO_SHOW);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1 CCBS.NUMBER_FIRST_YEARS_TO_SHOW " + CCBS.NUMBER_FIRST_YEARS_TO_SHOW);
//			}
//			else
//			{
//				yearIndex = CCBS.YEARS.size()-CCBS.NUMBER_OF_YEARS;
//				CCBS.NAME_FIRST_YEARS_TO_SHOW = CCBS.YEARS.get(yearIndex);
//				CCBS.NUMBER_FIRST_YEARS_TO_SHOW = yearIndex;
//				
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:2 yearIndex " + yearIndex);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:2 CCBS.NUMBER_OF_YEARS " + CCBS.NUMBER_OF_YEARS);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:2 CCBS.NAME_FIRST_YEARS_TO_SHOW " + CCBS.NAME_FIRST_YEARS_TO_SHOW);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:2 CCBS.NUMBER_FIRST_YEARS_TO_SHOW " + CCBS.NUMBER_FIRST_YEARS_TO_SHOW);
//			}
//			
//			System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:yearIndex " + yearIndex);
//			System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:CCBS.YEARS.size() " + CCBS.YEARS.size());
//			
//			//System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:CCBS.YEARS.size() !!!!!!!!!!!!!!! " + CCBS.YEARS.size());
////			String  colHeaders[]       = new String[CCBS.NUMBER_OF_YEARS * 2];
////			int     colHeadersWidths[] = new int[CCBS.NUMBER_OF_YEARS * 2];
////			boolean editableCols[]     = new boolean[CCBS.NUMBER_OF_YEARS * 2];
//			String  colHeaders[]       = new String[(CCBS.NUMBER_OF_YEARS+1) * 2];
//			int     colHeadersWidths[] = new int[(CCBS.NUMBER_OF_YEARS+1) * 2];
//			boolean editableCols[]     = new boolean[(CCBS.NUMBER_OF_YEARS+1) * 2];
//			int columnIndex= 0;
//			for (int i = yearIndex; i < CCBS.YEARS.size(); i++)
//			{				
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Ciclo i " + i+" CCBS.YEARS.get(i) "+CCBS.YEARS.get(i));
//				int colNum = columnIndex * 2;
//				colHeaders[colNum]           = CCBS.YEARS.get(i)+"";
//				//colHeaders[colNum]           = parseYear(CCBS.YEARS[i]);
//				//if(i== (CCBS.YEARS.size()-1))
////				if(i== CCBS.YEARS.size())
////				{
////					colHeadersWidths[colNum]     = 235;
////				}
////				else
////				{
////					colHeadersWidths[colNum]     = 80;
////				}
//				colHeadersWidths[colNum]     = 80;
//				editableCols[colNum]         = numPage < CCBS.COMMODITY_NAMES.length - 2; // skip last 2 pages;
//				colHeaders[colNum + 1]       = "";
//				colHeadersWidths[colNum + 1] = 20;
//				editableCols[colNum + 1]     = false;
//				columnIndex++;
//			}
//			
//			pageDecorations[numPage] = new PageDecoration("", elementUnits,
//			                                              CCBS.FIELD_NAMES,  160,              CCBS.FIELD_EDITABLE,
//			                                              colHeaders,       colHeadersWidths, editableCols);
//		}
//		bookPanel = new BookPanel(CCBS.COMMODITY_NAMES, pageDecorations);
//		//fillCenterPart(bookPanel);
//		
////		HorizontalPanel p1 = new HorizontalPanel();
////		p1.setTableWidth("100%");
////		//p1.setSpacing(SPACING);
////		p1.setHorizontalAlign(HorizontalAlignment.RIGHT);
////		p1.setVerticalAlign(VerticalAlignment.MIDDLE);
////
////		HorizontalPanel p2 = new HorizontalPanel();
////		//p2.setSpacing(SPACING);
////		
////
////	
////
//////		HorizontalAlignment align = HorizontalAlignment.RIGHT;
//////		hp.setButtonAlign(align); 
////		
//////		HBoxLayout layout = new HBoxLayout();  
//////		layout.setPadding(new Padding(5));  
//////		layout.setHBoxLayoutAlign(HBoxLayoutAlign.BOTTOM);
//////		layout.setPack(BoxLayoutPack.END);  
//////		hp.setLayout(layout);  
//////		HBoxLayoutData layoutData = new HBoxLayoutData(new Margins(0, 5, 0, 0));  
//////		hp.add(new Button("Ciao! "), layoutData);  
////		//hp.add(new Button(button1Text), new HBoxLayoutData(new Margins(0, 5, 0, 0)));
////		Button cancel = new Button("Cancel ");
////		cancel.setWidth(80);
////		cancel.setBorders(true);
////		cancel.setIconStyle("cancel");
////		cancel.setIconAlign(IconAlign.RIGHT);
////		//b.setMenuAlign("RIGHT");
////
////		p2.add(cancel);
////		
////		Button save = new Button("Save", new SelectionListener<ButtonEvent>() {  
////			@Override  
////			public void componentSelected(ButtonEvent ce) { 
////				MessageBox.info("Action", "The data have been saved", null);
////			  }  
////			});  
////		save.setWidth(80);
////		save.setIconStyle("save");
////		save.setIconAlign(IconAlign.RIGHT);
////		save.setBorders(true);
////		//b.setMenuAlign("RIGHT");
////
////		p2.add(save);
////		p1.add(p2);
////		
////		//getCenter().setBottomComponent(new Button("Ciao!"));
////		getCenter().setBottomComponent(p1);
////		//getCenter().setTopComponent(p1);
//		getCenterData().setSize(400);
//		getCenter().setHeading(BabelFish.print().table()); // TODO: improve title
//		
//		//getCenter().setHeaderVisible(false);
//		
//		return bookPanel;
//	}
	
	protected BookPanel buildCenterPanel(String country, String countryCode, String commodity, String commodityCode, String cropNum, String datasource, String firstMonthMarketingYear)
	{	
	//	System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:CCBS.YEARS.size() !!!!!!!!!!!!!!! " + CCBS.YEARS.size());
		
	//	System.out.println("Class: CCBSWindow Function: buildCenterPanel Text: datasource "+datasource);
		//One of the list that contain DataResult of the grid
		CCBS.LIST_FOR_PROD_AND_OTHER_USES_FORM.clear();
		PageDecoration pageDecorations[] = new PageDecoration[CCBS.NUM_BOOK_PAGE];
		if(CCBS.YEARS.size()== 0)
		{
			MessageBox.info("Action", "There are not data with this selection. Please retry with another selection!", null);
			return null;
		}
		for (int numPage = 0; numPage < CCBS.NUM_BOOK_PAGE; numPage++)
		{
			int yearIndex = 0;
			//Setting the number of the column as the number of years to show 
			if(CCBS.NUMBER_OF_YEARS > CCBS.YEARS.size())
			{
				CCBS.NUMBER_OF_YEARS = CCBS.YEARS.size();
				CCBS.NAME_FIRST_YEARS_TO_SHOW = CCBS.YEARS.get(0);
				CCBS.NUMBER_FIRST_YEARS_TO_SHOW = 0;
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1 yearIndex " + yearIndex);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1 CCBS.NUMBER_OF_YEARS " + CCBS.NUMBER_OF_YEARS);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1 CCBS.NAME_FIRST_YEARS_TO_SHOW " + CCBS.NAME_FIRST_YEARS_TO_SHOW);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1 CCBS.NUMBER_FIRST_YEARS_TO_SHOW " + CCBS.NUMBER_FIRST_YEARS_TO_SHOW);
			}
			else
			{
			//	System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1!!!!!!!!!!!!!!!!!!!!!!! PagePanelController.getLastYearToShow() " + PagePanelController.getLastYearToShow());
//				yearIndex = CCBS.YEARS.size()-CCBS.NUMBER_OF_YEARS;
//				CCBS.NAME_FIRST_YEARS_TO_SHOW = CCBS.YEARS.get(yearIndex);
//				CCBS.NUMBER_FIRST_YEARS_TO_SHOW = yearIndex;
				int index=0;
				
				for(int i=0; i< CCBS.YEARS.size(); i++)
				{
					if(CCBS.YEARS.get(i).equals(PagePanelController.getLastYearToShow()))
					{
						index = i;
						break;
					}					
				}
				int cal = (index+1)-CCBS.NUMBER_OF_YEARS;
			//	System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1!!!!!!!!!!!!!!!!!!!!!!! index " + index);
				//yearIndex = (index+1)-CCBS.NUMBER_OF_YEARS;
				if(cal<0)
				{
					yearIndex = index;
				}
				else
				{
					yearIndex = cal;
				}
			//	System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:1!!!!!!!!!!!!!!!!!!!!!!! yearIndex " + yearIndex);
				CCBS.NAME_FIRST_YEARS_TO_SHOW = CCBS.YEARS.get(yearIndex);
				CCBS.NUMBER_FIRST_YEARS_TO_SHOW = yearIndex;				
				
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:2 yearIndex " + yearIndex);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:2 CCBS.NUMBER_OF_YEARS " + CCBS.NUMBER_OF_YEARS);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:2 CCBS.NAME_FIRST_YEARS_TO_SHOW " + CCBS.NAME_FIRST_YEARS_TO_SHOW);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:2 CCBS.NUMBER_FIRST_YEARS_TO_SHOW " + CCBS.NUMBER_FIRST_YEARS_TO_SHOW);
			}
			
		//	System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:yearIndex " + yearIndex);
		//	System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:CCBS.YEARS.size() " + CCBS.YEARS.size());
			
			//System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:CCBS.YEARS.size() !!!!!!!!!!!!!!! " + CCBS.YEARS.size());
//			String  colHeaders[]       = new String[CCBS.NUMBER_OF_YEARS * 2];
//			int     colHeadersWidths[] = new int[CCBS.NUMBER_OF_YEARS * 2];
//			boolean editableCols[]     = new boolean[CCBS.NUMBER_OF_YEARS * 2];
			String  colHeaders[]       = new String[(CCBS.NUMBER_OF_YEARS+1) * 2];
			int     colHeadersWidths[] = new int[(CCBS.NUMBER_OF_YEARS+1) * 2];
			boolean editableCols[]     = new boolean[(CCBS.NUMBER_OF_YEARS+1) * 2];
			int columnIndex= 0;
			int colNum;
			int yearToStopIndex = CCBS.NUMBER_OF_YEARS + yearIndex;
			for (int i = yearIndex; i < yearToStopIndex; i++)
			{
				//System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Ciclo i " + i+" CCBS.YEARS.get(i) "+CCBS.YEARS.get(i));
				colNum = columnIndex * 2;
				//The year for the column value
				CCBS.YEARS_ON_GRID.add(colNum, CCBS.YEARS.get(i));
				//The year for the column flag
				CCBS.YEARS_ON_GRID.add((colNum+1), CCBS.YEARS.get(i));
				//System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Ciclo i " + i+" CCBS.YEARS.get(i) "+CCBS.YEARS.get(i));
				
				colHeaders[colNum]           = CCBS.YEARS.get(i)+"";
			//	System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Ciclo colNum " + colNum+ " colHeaders[colNum] "+ colHeaders[colNum]);
				//colHeaders[colNum]           = parseYear(CCBS.YEARS[i]);
				//if(i== (CCBS.YEARS.size()-1))
//				if(i== CCBS.YEARS.size())
//				{
//					colHeadersWidths[colNum]     = 235;
//				}
//				else
//				{
//					colHeadersWidths[colNum]     = 80;
//				}
				colHeadersWidths[colNum]     = 60;//80
				//editableCols[colNum]         = numPage < CCBS.COMMODITY_NAMES.length - 2; // skip last 2 pages;
				editableCols[colNum]         = true;
				colHeaders[colNum + 1]       = "";
				colHeadersWidths[colNum + 1] = 20;
				editableCols[colNum +1]         = true;
			//	editableCols[colNum + 1]     = false;
				columnIndex++;
			}
			// Adding new column
			colNum = columnIndex * 2;
			colHeaders[colNum]           = "";
			colHeadersWidths[colNum]     = 140;//185
			//editableCols[colNum]         = numPage < CCBS.COMMODITY_NAMES.length - 2; // skip last 2 pages;
			editableCols[colNum]         = true;
			colHeaders[colNum + 1]       = "";
			colHeadersWidths[colNum + 1] = 20;
			editableCols[colNum +1]         = true;
			//editableCols[colNum + 1]     = false;

//			System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:TEST Before if else country =  "+ country);
//			System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:TEST Before if else commodity =  "+ commodity);
//			if((country.equalsIgnoreCase("European Union"))&& commodity.equalsIgnoreCase("Wheat"))
//			{
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:European Union Wheat ");
//				pageDecorations[numPage] = new PageDecoration("", elementUnits,
//                        CCBS.FIELD_NAMES_EXPORT_EXCEPTION,  180,              CCBS.FIELD_EDITABLE,
//                        colHeaders,       colHeadersWidths, editableCols);
//			}
//			else if((country.equalsIgnoreCase("Russian Federation"))||(country.equalsIgnoreCase("Ukraine"))||(country.equalsIgnoreCase("Kazakhstan")))
//			{
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:Russian Federation o  Ukraine o Ukraine");
//				pageDecorations[numPage] = new PageDecoration("", elementUnits,
//                        CCBS.FIELD_NAMES_IMPORT_EXPORT_EXCEPTION,  180,              CCBS.FIELD_EDITABLE,
//                        colHeaders,       colHeadersWidths, editableCols);
//			}
//			else
//			{
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:General country");
//				pageDecorations[numPage] = new PageDecoration("", elementUnits,
//                        CCBS.FIELD_NAMES,  160,              CCBS.FIELD_EDITABLE,
//                        colHeaders,       colHeadersWidths, editableCols);
//			}
			System.out.println("Class: CCBSWindow Function: buildCenterPanel Text:cropNum " +cropNum);
			Set<String> keySet =CCBS.MONTH_FOR_YEARS.keySet();
//			for(String key:keySet)
//			{
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel key= "+key);
//				System.out.println("Class: CCBSWindow Function: buildCenterPanel CCBS.MONTH_FOR_YEARS.get(key)= "+CCBS.MONTH_FOR_YEARS.get(key));
//			}
			pageDecorations[numPage] = new PageDecoration("", elementUnits, subElementUnits,
                    CCBS.FIELD_NAMES_WITH_UNIT,  220,              CCBS.FIELD_EDITABLE,
                    colHeaders,       colHeadersWidths, editableCols, commodity, cropNum, CCBS.MONTH_FOR_YEARS, datasource, countryCode, country, commodityCode, commodity, firstMonthMarketingYear);
		}
//		Set<String> keySet =CCBS.MONTH_FOR_YEARS.keySet();
//		for(String key:keySet)
//		{
//			System.out.println("Class: PagePanel Function: buildCenterPanel key= "+key);
//			System.out.println("Class: PagePanel Function: buildCenterPanel CCBS.MONTH_FOR_YEARS.get(key)= "+CCBS.MONTH_FOR_YEARS.get(key));
//		}
		
//		keySet =pageDecorations[0].getMonthForYears().keySet();
//		for(String key:keySet)
//		{
//			System.out.println("Class: PagePanel Function: buildCenterPanel key= "+key);
//			System.out.println("Class: PagePanel Function: buildCenterPanel decoration.getMonthForYears().get(key)= "+pageDecorations[0].getMonthForYears().get(key));
//		}
		
		bookPanel = new BookPanel(pageDecorations);
		showHideFlags.removeAllListeners();
		showHideFlags.addSelectionListener(PagePanelController.showHideFlagsOfTheGrid(bookPanel, 0, this));
		save.removeAllListeners();
		if(!datasource.equals(CCBS.elementForTraining))
		{
			//New Case: To save a new data source
			//System.out.println("Class: CCBSWindow Function: buildCenterPanel Text: if datasource "+datasource);
			save.addSelectionListener(PagePanel.saveGrid(this, bookPanel, 0, datasource, country, countryCode, commodity, commodityCode, ccbsParametersPanel.getRadioGroup(),ccbsParametersPanel.getC(),ccbsParametersPanel.getStart(), ccbsParametersPanel.getTo(), ccbsParametersPanel.getRadio()));
			saveAndClose.addSelectionListener(PagePanel.saveAndCloseGrid(this, bookPanel, 0, datasource, country, countryCode, commodity, commodityCode, ccbsParametersPanel.getRadioGroup(),ccbsParametersPanel.getC(),ccbsParametersPanel.getStart(), ccbsParametersPanel.getTo(), ccbsParametersPanel.getRadio()));		
		}
		else
		{
			//System.out.println("Class: CCBSWindow Function: buildCenterPanel Text: else datasource "+datasource);
			//save.addSelectionListener(PagePanel.saveInformationForm(bookPanel, 0, datasource, country, countryCode, commodity, commodityCode));
			save.addSelectionListener(PagePanel.saveGrid(this, bookPanel, 0, datasource, country, countryCode, commodity, commodityCode, ccbsParametersPanel.getRadioGroup(),ccbsParametersPanel.getC(),ccbsParametersPanel.getStart(), ccbsParametersPanel.getTo(), ccbsParametersPanel.getRadio()));
			saveAndClose.addSelectionListener(PagePanel.saveAndCloseGrid(this, bookPanel, 0, datasource, country, countryCode, commodity, commodityCode, ccbsParametersPanel.getRadioGroup(),ccbsParametersPanel.getC(),ccbsParametersPanel.getStart(), ccbsParametersPanel.getTo(), ccbsParametersPanel.getRadio()));	
		}
		//getCenterData().setSize(400);
		getCenterData().setSize(280);
		getCenter().setHeading(AMISLanguage.print().table()); // TODO: improve title
		
		//getCenter().setHeaderVisible(false);
		
		return bookPanel;
	}


	private String parseYear(String year) {
		int index = year.indexOf("/");
		String newYear = year.substring(0, index);
		return newYear;
	}
	
	public void fillCenterPart(){
		setCenterProperties();		
		//center.add(widget);
		window.add(center, centerData);
	}
	
	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}
	
	public String getCommodityCodeFromName(String commodityName)
	{
		int index=0;
		for(String commodity:CCBS.COMMODITY_NAMES_CBS)
		{
		//	System.out.println("Class: CCBSWindow Function: getCommodityCodeFromName Text: commodity " + commodity +" commodityName "+commodityName);
			if(commodityName.equals(commodity))
			{
				break;
			}
			index++;
		}
		return CCBS.COMMODITY_CODES_CBS.get(index);
	}
	
	public String getCountryCodeFromName(String countryName)
	{
		int index=0;
		for(String country:CCBS.COUNTRY_NAMES)
		{
			if(countryName.equals(country))
			{
				break;
			}
			index++;
		}
		return CCBS.COUNTRY_CODES.get(index);
	}

	public String[] getSubElementUnits() {
		return subElementUnits;
	}

	public void setSubElementUnits(String[] subElementUnits) {
		this.subElementUnits = subElementUnits;
	}

	public Button getShowHideFlags() {
		return showHideFlags;
	}

	public void setShowHideFlags(Button showHideFlags) {
		this.showHideFlags = showHideFlags;
	}

	public Button getSaveAndClose() {
		return saveAndClose;
	}

	public void setSaveAndClose(Button saveAndClose) {
		this.saveAndClose = saveAndClose;
	}

	//This function set the constant AMIS_COUNTRY_DATA specific for the country
	public String amisForCountryDataset()
	{
		String result = AMISConstants.AMIS_COUNTRY_DATA.toString()+"_"+ amisInput.getCountrySelected().getLabel();
		return result;
	}
	
}
