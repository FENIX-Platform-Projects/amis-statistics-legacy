package org.fao.fenix.web.modules.adam.client.view.makers;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMDonorMatrixController;
import org.fao.fenix.web.modules.adam.client.view.ADAMFAOSectorMatrix;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.model.FAOSectorMatrixModel;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class ADAMFAOSectorMatrixMaker extends ADAMBoxMaker {

	private static Grid<FAOSectorMatrixModel> matrixGrid;
	
	
	public static VerticalPanel build(OLAPParametersVo params, ADAMDonorMatrixVO matrixVo, List<FAOSectorMatrixModel> models, List<DonorMatrixModel> matrixModels, String recipientLabel, String recipientCode) {

		VerticalPanel p = new VerticalPanel();
		VerticalPanel h1 = buildFAOSectorMatrixBoxMenu(matrixVo, params, models, matrixModels, ADAMConstants.BIGGER_MENU_GAP, null, true);
		if(models!=null && !models.isEmpty()) {
			setFAOSectorMatrixGrid(params, models, recipientCode);	
			h1.add(buildFAOSectorMatrixIconsToolbar(matrixVo, params, models, matrixModels));
			p.add(h1);
			p.add(buildGridContainer(params, models, recipientLabel, recipientCode));
			p.add(buildDisclaimer());			
		}
		else	{
			p.add(ADAMEmptyValuesPanel.build("No data available for the current selection"));
		}
		return p;
	}
	
	private static VerticalPanel buildFAOSectorMatrixBoxMenu(ADAMDonorMatrixVO vo, OLAPParametersVo params, List<FAOSectorMatrixModel> models, final List<DonorMatrixModel> matrixModels, String labelWidth, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) { 
		VerticalPanel vPanel = new VerticalPanel();	
		
		ADAMResultVO rvo = new ADAMResultVO();
		String title = "Sub-Sector Breakdown of FAO Related Sectors* by ODA Commitments and Disbursements in USD Millions ";
		
		if(ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() == ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel())
			title += "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + ")";
		else
			title += "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() +" - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
	
		rvo.setTitle(title);
		
		vPanel.add(ADAMBoxMaker.buildBoxHeader(rvo, labelWidth, objectSizeListener, isSmall));
		// toolobar added later
		return vPanel;
	}
	
	private static HorizontalPanel buildFAOSectorMatrixIconsToolbar(final ADAMDonorMatrixVO matrixVo, OLAPParametersVo params, List<FAOSectorMatrixModel> models, final List<DonorMatrixModel> matrixModels) {
		
		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleAttribute("padding-top", "3px");
		panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);

		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleAttribute("background", "#F3F5F7"); //very light grey
		toolBar.setBorders(false);
		panel.add(toolBar);

		OLAPFilterVo filter =  matrixVo.getOlapParametersVO().getFilters().get(0);
		List<String> dates = new ArrayList<String>();
		
		for (String value: filter.getDimensionMap().keySet()) 
				 dates.add(value);
	
		Button switchToDonorMatrixView = ADAMBoxMaker.createToolbarButton("Back To Resource Partner Matrix");
		switchToDonorMatrixView.setIconStyle("back");
		switchToDonorMatrixView.addSelectionListener(ADAMDonorMatrixController.backToMatrixViewFromFAOSectorView(matrixVo, dates, matrixModels));
		
		toolBar.add(switchToDonorMatrixView);  
		toolBar.add(new SeparatorToolItem());  
		
		Button exportExcel = ADAMBoxMaker.createToolbarButton("Download Data");
		exportExcel.setIconStyle("exportDataIcon");
		exportExcel.addSelectionListener(ADAMDonorMatrixController.exportExcel(params));
		
		if(models!=null && models.size() > 0) {
			toolBar.add(exportExcel);  
			toolBar.add(new SeparatorToolItem());  
		}	
		
		CheckBox hideCommitmentColumns = createToolbarCheckBox(BabelFish.print().hideAllCommitmentColumns());
		hideCommitmentColumns.addListener(Events.OnClick, ADAMDonorMatrixController.hideCommitmentColumns(params, getFAOSectorMatrixGrid(), toolBar));
	
		CheckBox hideDisbursementColumns = createToolbarCheckBox(BabelFish.print().hideAllDisbursementColumns());
		hideDisbursementColumns.addListener(Events.OnClick, ADAMDonorMatrixController.hideDisbursementColumns(params, getFAOSectorMatrixGrid(), toolBar));	
		
		CheckBox hideORsColumn =createToolbarCheckBox("Show FAO Organizational Results") ;
		hideORsColumn.addListener(Events.OnClick, ADAMDonorMatrixController.hideORsColumn(getFAOSectorMatrixGrid(), toolBar));	
		
	   // Html label = new Html("<div class='title-box-nounderline' align='center'><b>[Click on the <font color='#3668C2'>blue</font> highlighted values to view the projects]</b></div>");
        //label.setWidth(labelWidth);
    	//toolBar.add(label);
		
		toolBar.add(hideORsColumn);
		toolBar.add(hideCommitmentColumns);
		toolBar.add(hideDisbursementColumns);
		
		toolBar.add(new SeparatorToolItem());  
		
		Label label = new Label();
		label.setStyleAttribute("font-size", "10px");
		label.setStyleAttribute("color", "#1D4589");
		label.setText("Hover over the column headers and click on <img src='adam-images/arrow_down.png' border='0'/> for sorting/column options");
		
		toolBar.add(label);
		
		return panel;
	}
	
	private static ContentPanel buildGridContainer(OLAPParametersVo params, List<FAOSectorMatrixModel> models,String recipientLabel, String recipientCode){

		ContentPanel cp = new ContentPanel();
		cp.setCollapsible(false);  
		cp.setAnimCollapse(false);
		cp.setFrame(true); 
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setWidth(Integer.valueOf(ADAMConstants.BIGGER_BOX_WIDTH));
		
		
		cp = buildGrid(recipientLabel, cp, params, models);
		
		return cp;
	}
	
	private static HorizontalPanel buildDisclaimer(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		String disclaimer = "<br/> The OECD Development Assistance Committee (DAC) sector classifications are displayed.";
		disclaimer += "<br/><b>Average</b> calculated using all the Sub-Sectors displayed.";
		disclaimer += "<br/>The Commitments and Disbursements displayed are associated with the Sub-Sector and <b><u>not</u></b> the FAO Organizational Results.";

		panel.add(new Html(disclaimer));

		return panel;		
	}
	
	
	
	
	
	
	
	/*public static VerticalPanel build(OLAPParametersVo params, ADAMDonorMatrixVO matrixVo, List<FAOSectorMatrixModel> models, String recipientLabel, String recipientCode, final List<DonorMatrixModel> matrixModels) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		
		//HorizontalPanel h1 =  new HorizontalPanel();
		VerticalPanel v1 =  new VerticalPanel();
		HorizontalPanel h2 =  new HorizontalPanel();
		 ContentPanel cp = new ContentPanel();
		    cp.setCollapsible(false);  
		    cp.setAnimCollapse(false);
		    cp.setFrame(true); 
		  	cp.setButtonAlign(HorizontalAlignment.CENTER);
			cp.setLayout(new FitLayout());
			//cp.setAutoHeight(true);
			
		
			
			//was 1150 before instead of 950
		
		if(models!=null && !models.isEmpty()){
			int height = 275 + (matrixModels.size() * 35);
			
			//GWT.log("SIZE "+ String.valueOf(height), null);
			cp.setWidth(Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH));
		//	cp.setAutoHeight(true);
			//cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH), height);  //600
			
			setFAOSectorMatrixGrid(params, models, cp, recipientCode);
			int gap = Integer.valueOf(ADAMConstants.BIG_BOX_HEIGHT);
			//GWT.log(String.valueOf(gap), null);
			
			v1 = buildFAOSectorMatrixMenu(String.valueOf(gap), params, cp, matrixVo, matrixModels,  recipientLabel); // was 850 before
			//cp.add(new Html(params.getTitle()));
			cp = buildGrid(recipientLabel, cp, params, models);
			
		}
		else {
			cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH), 400);  //600
			cp.setHeaderVisible(false); 
			cp.add(new Html(BabelFish.print().noResultsMatchRequest()));	
		}
		
		h2.add(cp);
			
		HorizontalPanel h1 = buildDonorMatrixMenu("1000px");
		HorizontalPanel h2 =  new HorizontalPanel();
		ContentPanel cp = null;
		cp = buildGrid(params, models);
		h2.add(cp);
		p.add(v1);
		p.add(h2);
		if(models!=null && !models.isEmpty()){
			p.add(new Html("<br/><b>Average</b> calculated using all the FAO sectors displayed."));	
			p.add(new Html("<br/><b>*</b> The Development Assistance Committee (DAC) sector classifications are displayed."));
			p.add(new Html("<br/>The Commitments and Disbursements displayed are associated with the FAO Sector and <b><u>not</u></b> the FAO Organizational Results."));	
		}
		return p;
	}
	*/
	public static void setFAOSectorMatrixGrid(OLAPParametersVo params, List<FAOSectorMatrixModel> models, String recipientCode) {
		matrixGrid = ADAMFAOSectorMatrix.createFAOSectorMatrix(params, models, recipientCode);		
	}
	
	public static Grid<FAOSectorMatrixModel> getFAOSectorMatrixGrid() {
		return matrixGrid;
	}
	
	public static ContentPanel buildGrid(String recipientLabel, ContentPanel cp, OLAPParametersVo params, List<FAOSectorMatrixModel> models) {
		cp.setHeaderVisible(false); 
		if( getFAOSectorMatrixGrid()!=null) {
			cp.add(getFAOSectorMatrixGrid());
		}	
		return cp;
	}
	

	/*public static HorizontalPanel buildFAOSectorMatrixMenu(String labelWidth, OLAPParametersVo params, ContentPanel panel, ADAMDonorMatrixVO matrixVo, final List<DonorMatrixModel> models, final String recipientLabel) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		h1.add(buildFAOSectorMatrixButtonsMenu(labelWidth, params, panel, matrixVo, models, recipientLabel));
		return h1;
	}*/
	
	/*public static VerticalPanel buildFAOSectorMatrixMenu(String labelWidth, OLAPParametersVo params, ContentPanel panel, ADAMDonorMatrixVO matrixVo, final List<DonorMatrixModel> models, final String recipientLabel) {
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.MIDDLE);
		vp.setSpacing(5);
		vp.add(buildFAOSectorMatrixButtonsMenu(labelWidth, params, panel, matrixVo, models, recipientLabel));
		vp.add(buildHideShowColumnCheckBoxes(labelWidth, params, panel, matrixVo, models, recipientLabel));
		
		return vp;
	}
	*/
	private static HorizontalPanel buildFAOSectorMatrixButtonsMenu(String labelWidth, final OLAPParametersVo params, final ContentPanel contentPanel, final ADAMDonorMatrixVO matrixVo, final List<DonorMatrixModel> models, final String recipientLabel) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		
		Button exportToExcel = new Button(BabelFish.print().export());
		exportToExcel.setIconStyle("sendToExcel");
		exportToExcel.addSelectionListener(ADAMDonorMatrixController.exportExcel(params));
		
		OLAPFilterVo filter =  matrixVo.getOlapParametersVO().getFilters().get(0);
		List<String> dates = new ArrayList<String>();
		
		for (String value: filter.getDimensionMap().keySet()) 
				 dates.add(value);
	
		Button switchToDonorMatrixView = new Button("Go Back To Matrix");
		switchToDonorMatrixView.setIconStyle("back");
		switchToDonorMatrixView.addSelectionListener(ADAMDonorMatrixController.backToMatrixViewFromFAOSectorView(matrixVo, dates, models));
		
		VerticalPanel labelHolder = new VerticalPanel();
		
        Html label = new Html("<div class='title-box-nounderline' align='center'><b>"+recipientLabel+ "</b>: Breakdown of FAO Sectors* by ODA Commitments and Disbursements for the selected timeperiod (" + ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() +" - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel() + ") (shown in USD Millions)"+"</div>");
        label.setWidth(labelWidth);
        
        labelHolder.add(label);
        
       // Html label2 = new Html("<br/><div class='title-box-nounderline' align='center'><b>[Click on the <font color='#3668C2'>blue</font> highlighted values to view the projects]</b></div>");
       // label2.setWidth(labelWidth);
        //labelHolder.add(label2);
		
        panel.add(switchToDonorMatrixView);
		//panel.add(labelHolder);
		panel.add(label);
		panel.add(exportToExcel);
		
		return panel;
	}
	
	/*private static HorizontalPanel buildHideShowColumnCheckBoxes(String labelWidth, final OLAPParametersVo params, final ContentPanel contentPanel, final ADAMDonorMatrixVO matrixVo, final List<DonorMatrixModel> models, final String recipientLabel) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		
		CheckBox hideCommitmentColumns = new CheckBox();  
		hideCommitmentColumns.setBoxLabel(BabelFish.print().hideAllCommitmentColumns());  
		hideCommitmentColumns.setValue(false);  
		hideCommitmentColumns.addListener(Events.OnClick, ADAMDonorMatrixController.hideCommitmentColumns(params, getFAOSectorMatrixGrid(), panel));
	
		CheckBox hideDisbursementColumns = new CheckBox();  
		hideDisbursementColumns.setBoxLabel(BabelFish.print().hideAllDisbursementColumns());  
		hideDisbursementColumns.setValue(false);  
		hideDisbursementColumns.addListener(Events.OnClick, ADAMDonorMatrixController.hideDisbursementColumns(params, getFAOSectorMatrixGrid(), panel));	
		
		CheckBox hideORsColumn = new CheckBox();  
		hideORsColumn.setBoxLabel("Show FAO Organizational Results Column");  
		hideORsColumn.setValue(false);  
		hideORsColumn.addListener(Events.OnClick, ADAMDonorMatrixController.hideORsColumn(getFAOSectorMatrixGrid(), panel));	
		
		
	    Html label = new Html("<div class='title-box-nounderline' align='center'><b>[Click on the <font color='#3668C2'>blue</font> highlighted values to view the projects]</b></div>");
        label.setWidth(labelWidth);
    	
        panel.add(label);
        panel.add(hideORsColumn);
		panel.add(hideCommitmentColumns);
		panel.add(hideDisbursementColumns);
		
		return panel;
	}*/

	
	
	
}