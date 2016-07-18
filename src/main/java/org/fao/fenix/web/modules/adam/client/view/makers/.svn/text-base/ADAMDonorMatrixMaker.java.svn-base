package org.fao.fenix.web.modules.adam.client.view.makers;

import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMDonorMatrixController;
import org.fao.fenix.web.modules.adam.client.view.ADAMDonorMatrix;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class ADAMDonorMatrixMaker extends ADAMBoxMaker {

	private static Grid<DonorMatrixModel> matrixGrid;
	
	public static VerticalPanel build(ADAMDonorMatrixVO matrixVo, List<DonorMatrixModel> models) {

		VerticalPanel p = new VerticalPanel();
		VerticalPanel h1 = buildDonorMatrixBoxMenu(matrixVo, models, ADAMConstants.BIGGER_MENU_GAP, null, true);
		if(models!=null && !models.isEmpty()) {
			p.add(h1);
			p.add(buildGridContainer(matrixVo, models));
			//p.add(buildDisclaimer());			
		}
		else	{
			p.add(ADAMEmptyValuesPanel.build("No data available for the current selection"));
		}
		return p;
	}
	
	private static VerticalPanel buildDonorMatrixBoxMenu(ADAMDonorMatrixVO vo, List<DonorMatrixModel> models, String labelWidth, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) { 
		VerticalPanel vPanel = new VerticalPanel();	
		ADAMResultVO rvo = new ADAMResultVO();
		String title = "ODA Commitments in FAO Related Sectors in USD Millions  ";
		
		if(ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() == ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel())
			title += "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() + ")";
		else
			title += "("+ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() +" - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel()+")";
	
		rvo.setTitle(title);
		
		vPanel.add(ADAMBoxMaker.buildBoxHeader(rvo, labelWidth, objectSizeListener, isSmall));
		vPanel.add(buildDonorMatrixIconsToolbar(vo, models));
		return vPanel;
	}
	
	private static HorizontalPanel buildDonorMatrixIconsToolbar(final ADAMDonorMatrixVO matrixVo, List<DonorMatrixModel> models) {
		
		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleAttribute("padding-top", "3px");
		panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);

		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleAttribute("background", "#F3F5F7"); //very light grey
		toolBar.setBorders(false);
		panel.add(toolBar);

		Button exportExcel = ADAMBoxMaker.createToolbarButton("Download Data");
		exportExcel.setIconStyle("exportDataIcon");
		exportExcel.addSelectionListener(ADAMDonorMatrixController.exportExcel(matrixVo.getOlapParametersVO()));
		
		if(models!=null && models.size() > 0) {
			toolBar.add(exportExcel);  
			toolBar.add(new SeparatorToolItem());  
		}	
		
		
		toolBar.add(addReadMoreButtonForUrl("adam-docs/adam_fao_sector_breakdown.pdf")); 
		toolBar.add(new SeparatorToolItem());
		
		Label label = new Label();
		label.setStyleAttribute("font-size", "10px");
		label.setStyleAttribute("color", "#1D4589");
		label.setText("Hover over the column headers and click on <img src='adam-images/arrow_down.png' border='0'/> for sorting/column options");
		
		
		toolBar.add(label);
		
		return panel;
	}
	
	private static ContentPanel buildGridContainer(ADAMDonorMatrixVO matrixVo, List<DonorMatrixModel> models){
		setDonorMatrixGrid(matrixVo, models);
		ContentPanel cp = new ContentPanel();
		cp.setCollapsible(false);  
		cp.setAnimCollapse(false);
		cp.setFrame(true); 
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setWidth(Integer.valueOf(ADAMConstants.BIGGER_BOX_WIDTH));
		
		//cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIGGER_BOX_WIDTH), height);  
		cp = buildGrid(cp, matrixVo.getOlapParametersVO(), models);
		
		return cp;
	}
	
	private static HorizontalPanel buildDisclaimer(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		String disclaimer="<br/> See <b>Read More</b> for the full breakdown, but the OECD Development Assistance Committee (DAC) sector classifications include: <br/>Agriculture/Forestry/Fishery, Business and Other Services, Commodity Aid and General Programme Assistance, Government and Civil Society, Government and Civil Society, Health, Humanitarian Aid, Industry/Mining/Construction, Multisector/Cross-Cutting, Other Social Infrastructure and Services, Water and Sanitation.<br/>";
		
		panel.add(new Html(disclaimer));

		return panel;		
	}
	
	
	/*public static VerticalPanel build(ADAMDonorMatrixVO matrixVo, List<DonorMatrixModel> models) {
		VerticalPanel p = new VerticalPanel();
		p.setSpacing(5);
		
		HorizontalPanel h1 =  new HorizontalPanel();
		HorizontalPanel h2 =  new HorizontalPanel();
		 ContentPanel cp = new ContentPanel();
		    cp.setCollapsible(false);  
		    cp.setAnimCollapse(false);
		    cp.setFrame(true); 
		  	cp.setButtonAlign(HorizontalAlignment.CENTER);
			cp.setLayout(new FitLayout());
			cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH), 400);  
			//was 1150 before instead of 950
		
		if(models!=null && !models.isEmpty()){
			
			setDonorMatrixGrid(matrixVo, models);
			int gap = Integer.valueOf(ADAMConstants.BIG_MENU_GAP_WIDTH_NOPIXEL) - 20;
			//GWT.log(String.valueOf(gap), null);
			
			h1 = buildDonorMatrixMenu(String.valueOf(gap), matrixVo, cp); // was 850 before
			//cp.add(new Html(params.getTitle()));
			cp = buildGrid(cp, matrixVo.getOlapParametersVO(), models);
			
		}
		else {
			cp.setHeaderVisible(false); 
			cp.add(new Html(BabelFish.print().noResultsMatchRequest()));	
		}
		
		h2.add(cp);
			
		HorizontalPanel h1 = buildDonorMatrixMenu("1000px");
		HorizontalPanel h2 =  new HorizontalPanel();
		ContentPanel cp = null;
		cp = buildGrid(params, models);
		h2.add(cp);
		p.add(h1);
		p.add(h2);
		if(models!=null && !models.isEmpty()){
			p.add(new Html("<br/><b>*</b> The Development Assistance Committee (DAC) sector classifications include: <br/>Agriculture/Forestry/Fishery, Business and Other Services, Commodity Aid and General Programme Assistance, Government and Civil Society, Government and Civil Society, Health, Humanitarian Aid, Industry/Mining/Construction, Multisector/Cross-Cutting, Other Social Infrastructure and Services, Water and Sanitation.<br/>(Click <a href='adam-docs/adam_fao_sector_breakdown.pdf' target='_blank'> here </a> to view the full breakdown)"));			
		}
		return p;
	}*/
	
	public static void setDonorMatrixGrid(ADAMDonorMatrixVO matrixVo, List<DonorMatrixModel> models) {
		matrixGrid = ADAMDonorMatrix.createDonorMatrix(matrixVo, models);		
	}
	
	public static Grid<DonorMatrixModel> getDonorMatrixGrid() {
		return matrixGrid;
	}
	
	public static ContentPanel buildGrid(ContentPanel cp, OLAPParametersVo params, List<DonorMatrixModel> models) {
		cp.setHeaderVisible(false); 
		//cp.setHeading("ODA Commitments in FAO related sectors<b>*</b> for the selected timeperiod (" + ADAMReferencePeriodPanel.fromDateList.getValue().getGaulLabel() +" - "+ADAMReferencePeriodPanel.toDateList.getValue().getGaulLabel() + ") (shown in USD Millions)");  
		//cp.add(ADAMDonorMatrix.createDonorMatrix(params, models));
		
		if( getDonorMatrixGrid()!=null) {
			cp.add(getDonorMatrixGrid());
		}	
		
		
		/*if(!models.isEmpty()){
			cp.setHeaderVisible(true); 
			cp.setHeading("ODA Commitments to Production Sectors  -  Food Security Assistance  - Rural Development - Humanitarian Aid (totals shown in USD Millions)");  
			cp.add(ADAMDonorMatrix.createDonorMatrix(params, models));
		}
		else {
			cp.setHeaderVisible(false); 
			cp.add(new Html("No results matched the request, so the resource partner matrix could not be created"));	
		}*/
		
		return cp;
	}
	

	/*public static HorizontalPanel buildDonorMatrixMenu(String labelWidth, ADAMDonorMatrixVO vo, ContentPanel panel) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		
		OLAPParametersVo params = vo.getOlapParametersVO();
		
		Html label = new Html("<div class='title-box-nounderline'>"+params.getOlapTitle()+"</div>");
		
		h1.add(label);
		
		//Html label = new Html("<div class='title-box'></div>");
		
		label.setWidth(labelWidth);
		//h1.add(addInfo("The Resource Partner matrix ..... details"));
		h1.add(label);
		h1.add(buildDonorMatrixButtonsMenu(vo, panel));
		return h1;
	}
	
	
	private static HorizontalPanel buildDonorMatrixButtonsMenu(final ADAMDonorMatrixVO vo, final ContentPanel contentPanel) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		*//**CheckBox hideColumns = new CheckBox();  
		hideColumns.setBoxLabel(BabelFish.print().showAllWebLinkColumns());  
		hideColumns.setValue(false);  
		hideColumns.addListener(Events.OnClick, DonorMatrixController.hideColumns(vo.getOlapParametersVO(), getDonorMatrixGrid(), panel));
		**//*
		
		CheckBox switchToFAOSectorView = new CheckBox();  
		switchToFAOSectorView.setBoxLabel("Switch To FAO Sector View");  
		switchToFAOSectorView.setValue(false);  
	//	switchToFAOSectorView.addListener(Events.OnClick, DonorMatrixController.showFaoSectorView(vo));
		
		
		
		
		//ToggleButton hideColumns = new ToggleButton("Hide/Show Web Link");  
		//hideColumns.toggle(true);  
		//hideColumns.addSelectionListener(DonorMatrixController.hideColumns(params, getDonorMatrixGrid(), contentPanel));
		
		Button customize = new Button("Customize");
		customize.addSelectionListener(ADAMCustomController.customizeResource());
		
		Button exportToExcel = new Button(BabelFish.print().export());
		exportToExcel.setIconStyle("sendToExcel");
		exportToExcel.addSelectionListener(ADAMDonorMatrixController.exportExcel(vo.getOlapParametersVO()));
		
		
		
//		print.setIconStyle("print");

		MenuItem createResource = new MenuItem("Customize");
		createResource.addSelectionListener(ADAMCustomController.createCustomResource());
		createResource.setIconStyle("");
		
		
		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton("More...");
		more.setIconStyle("gear");
		MenuItem showProjects = new MenuItem("Show Projects");
		//showProjects.addSelectionListener(ADAMController.showProjects());
		showProjects.setIconStyle("tableIcon");
		

		moreMenu.add(exportToExcel);
		moreMenu.add(showProjects);
		moreMenu.add(createResource);
		more.setMenu(moreMenu);
//		Button print = new Button("Print");
//		print.setIconStyle("print");
		
		// Export Menu
		SplitButton export = new SplitButton("Export...");
		export.setIconStyle("export");
		MenuItem exportImage = new MenuItem("Export Image");
		exportImage.setIconStyle("pdfIcon");
		//exportImage.addSelectionListener(ADAMController.exportChartImage(vo));
		MenuItem exportExcel = new MenuItem("Export Excel");
		exportExcel.setIconStyle("sendToExcel");
		//exportExcel.addSelectionListener(ADAMController.exportChartExcel(vo));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");

		Menu exportMenu = new Menu();
		exportMenu.add(exportImage);
		exportMenu.add(exportExcel);
		export.setMenu(exportMenu);
		
		
		//panel.add(more);
		//panel.add(switchToFAOSectorView);
		//panel.add(hideColumns);
		panel.add(exportToExcel);
		//panel.add(customize);

	//	panel.add(export);
		
		return panel;
	}
	
*/
	
	/*public static ContentPanel build(final OLAPParametersVo params) {

		final ContentPanel panel = new ContentPanel();
		panel.setHeaderVisible(false);
		
		 ADAMServiceEntry.getInstance().populateEmptyAxesDonorMatrix (params, new AsyncCallback<OLAPParametersVo>() {
				public void onSuccess(final OLAPParametersVo updatedVO) {
				
					 ADAMServiceEntry.getInstance().getDonorMatrixRows (updatedVO, new AsyncCallback<List<DonorMatrixModel>>() {
							public void onSuccess(List<DonorMatrixModel> models) {
								Info.display("Retrieved Matrix Rows", "rows size = "+models.size());
								System.out.println("Retrieved Matrix Rows rows size = "+models.size());

								panel.add(ADAMDonorMatrix.createDonorMatrix(updatedVO, models));
									
								panel.getLayout().layout();
							}
							public void onFailure(Throwable caught) {
								Info.display("Retrieved Matrix Rows: Failed", "Error in retrieving Matrix Rows", caught.getLocalizedMessage());
							}
						});
				
				}
				public void onFailure(Throwable caught) {
					Info.display("Retrieved Matrix Rows: Failed", "Error in retrieving Matrix Rows", caught.getLocalizedMessage());
				}
			});
		 
	   
	    
		return panel;
	}*/
	
	
	

}