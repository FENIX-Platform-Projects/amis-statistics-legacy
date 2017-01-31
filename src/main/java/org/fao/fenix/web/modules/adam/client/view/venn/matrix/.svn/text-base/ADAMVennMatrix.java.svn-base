package org.fao.fenix.web.modules.adam.client.view.venn.matrix;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennController;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennMatrixController;
import org.fao.fenix.web.modules.adam.client.control.venn.matrix.ADAMVennChannelMatrixController;
import org.fao.fenix.web.modules.adam.client.control.venn.matrix.ADAMVennDonorMatrixController;
import org.fao.fenix.web.modules.adam.client.control.venn.matrix.ADAMVennRecipientMatrixController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;


public class ADAMVennMatrix extends ADAMBoxMaker {
	
	Window window;
	
	ContentPanel panel;
	
	ContentPanel matrixPanel;
	
	ContentPanel menuPanel;

	CheckBox showCPF;
	
	CheckBox showStatedPriorities;
	 
	
	// VENN RECIPIENT MATRIX
	public ContentPanel buildBigVennMatrixRecipientMenu(ADAMResultVO vo) {
		ContentPanel cp = new ContentPanel();
		cp.setCollapsible(false);  
		cp.setAnimCollapse(false);
		cp.setFrame(true); 
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FitLayout());
		cp.setHeaderVisible(false);
		cp.setBodyBorder(false);

		cp.add(buildRecipientMatrixBoxMenu(vo, ADAMConstants.BIG_MENU_VENN_RECIPIENT_MATRIX_GAP_WIDTH));


		return cp;
	}

	public VerticalPanel buildRecipientMatrixBoxMenu(ADAMResultVO vo, String labelWidth) { 
		VerticalPanel vPanel = new VerticalPanel();

		System.out.println("------ buildMatrixBoxMenu = "+ labelWidth + " | title = "+vo.getTitle());
		vPanel.add(buildBoxHeader(vo, labelWidth, null, false));
		vPanel.add(buildRecipientMatrixIconsToolbar(vo));
		return vPanel;
	}

	public HorizontalPanel buildRecipientMatrixIconsToolbar(final ADAMResultVO vo) {

		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleAttribute("padding-top", "3px");
		panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);

		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleAttribute("background", "#F3F5F7"); //very light grey
		toolBar.setBorders(false);
		panel.add(toolBar);

		if(ADAMBoxMaker.countriesSelected.size() > 1) {

			showCPF = new CheckBox();

			showStatedPriorities = new CheckBox();
			showStatedPriorities.setValue(true);
			showStatedPriorities.addListener(Events.OnClick, ADAMVennMatrixController.showGovernmentStatedPriorities(this, vo, showCPF, showStatedPriorities));

			Html html = new Html("<div class='venn_matrix_option_text' style='padding: 6px;'>Show Stated Priorities</div>");
			//toolBar.add(showStatedPriorities);
			//toolBar.add(html);

			showCPF.addListener(Events.OnClick, ADAMVennMatrixController.showGovernmentStatedPriorities(this, vo, showCPF, showStatedPriorities));
			Html showCPFTitle = new Html("<div class='venn_matrix_option_text' style='padding: 6px;'>Show only countries with National Document</div>");
			//toolBar.add(showCPF);
			//toolBar.add(showCPFTitle);

			//toolBar.add(new SeparatorToolItem());  

			Button viewFao = createToolbarButton("Agreed Priorities with FAO");
			viewFao.setIconStyle("tableIcon");
			viewFao.addSelectionListener(ADAMVennChannelMatrixController.buildVennMatrixChannel(this, vo, false, true, "ANALYSE_TOP"));
			//toolBar.add(viewFao);  


			if ( ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS) ) {
				Button viewDonor = createToolbarButton("Resource Partner Priorities");
				viewDonor.setIconStyle("tableIcon");
				viewDonor.addSelectionListener(ADAMVennDonorMatrixController.buildVennDonorMatrix(this, vo, "ANALYSE_TOP"));
				//toolBar.add(viewDonor);  
			} 

			//toolBar.add(new SeparatorToolItem());  
		}
		else {
			Button viewDonor = createToolbarButton("View Resource Partner Priorities");
			viewDonor.setIconStyle("tableIcon");
			viewDonor.addSelectionListener(ADAMVennDonorMatrixController.buildVennDonorMatrixListener(this, vo, "ANALYSE_TOP"));

			//toolBar.add(viewDonor);  
			//toolBar.add(new SeparatorToolItem());  

		}

		Button exportExcel = createToolbarButton("Download Data");
		exportExcel.setIconStyle("exportDataIcon");
		exportExcel.addSelectionListener(ADAMVennController.exportExcelTableButton(vo, true));
		
		Label desc = new Label();
		desc.setStyleAttribute("font-size", "10px");
		desc.setStyleAttribute("color", "#1D4589");
		desc.setText("Hover over the column headers and click on <img src='adam-images/arrow_down.png' border='0'/> for sorting/column options");
		
		
		if(vo.getTableContents().size() > 0) {
			toolBar.add(exportExcel);  
			toolBar.add(new SeparatorToolItem());
			toolBar.add(desc);
		}
		
		return panel;
	}
	
	 // VENN DONOR MATRIX
	 public ContentPanel buildBigVennMatrixDonorMenu(ADAMResultVO vo,  String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean showLinks) {
			ContentPanel cp = new ContentPanel();
			cp.setCollapsible(false);  
			cp.setAnimCollapse(false);
			cp.setFrame(true); 
			cp.setButtonAlign(HorizontalAlignment.CENTER);
			cp.setLayout(new FitLayout());
			cp.setHeaderVisible(false);
			cp.setBodyBorder(false);

			cp.add(buildDonorMatrixBoxMenu(vo, ADAMConstants.BIG_MENU_VENN_RECIPIENT_MATRIX_GAP_WIDTH));


			return cp;
		}

		public VerticalPanel buildDonorMatrixBoxMenu(ADAMResultVO vo, String labelWidth) { 
			VerticalPanel vPanel = new VerticalPanel();

			System.out.println("------ buildMatrixBoxMenu = "+ labelWidth + " | title = "+vo.getTitle());
			vPanel.add(buildBoxHeader(vo, labelWidth, null, false));
			vPanel.add(buildDonorMatrixIconsToolbar(vo));
			return vPanel;
		}

		public HorizontalPanel buildDonorMatrixIconsToolbar(final ADAMResultVO vo) {
			
			HorizontalPanel panel = new HorizontalPanel();
			panel.setStyleAttribute("padding-top", "3px");
			panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
			panel.setVerticalAlign(VerticalAlignment.MIDDLE);

			ToolBar toolBar = new ToolBar();  
			toolBar.setStyleAttribute("background", "#F3F5F7"); //very light grey
			toolBar.setBorders(false);
			panel.add(toolBar);
	
			
			if(ADAMBoxMaker.countriesSelected.size() > 1) {

				String label = "";
				if(ADAMBoxMaker.countriesSelected.size() == 1)
					label = ADAMBoxMaker.countriesSelected.entrySet().iterator().next().getValue() + "'s Priorities";
				else
					label = "Countries' Priorities";
					
				Button viewRecipient = createToolbarButton(label);
				viewRecipient.addSelectionListener(ADAMVennController.buildVennRecipientMatrixMenu(this, vo));
				viewRecipient.setIconStyle("tableIcon");
				//toolBar.add(viewRecipient);
				
				


				Button viewFAO = createToolbarButton("Agreed Priorities with FAO");
				/**if(ADAMBoxMaker.countriesSelected.size() > 1){
					viewFAO.addSelectionListener(ADAMVennController.buildVennMatrixChannel(vennMatrix, vo, false, false));
				} else {
					viewFAO.addSelectionListener(ADAMVennController.buildVennMatrixChannel(vennMatrix, vo, false, true));
				}**/
				
				//set view priorities by default
			//	viewFAO.addSelectionListener(ADAMVennChannelMatrixController.buildVennMatrixChannel(vennMatrix, vo, false, true));
				
				viewFAO.setIconStyle("tableIcon");
				
				toolBar.add(new SeparatorToolItem());  
			}
			else {

				String label = "View "+ ADAMBoxMaker.countriesSelected.entrySet().iterator().next().getValue() + "'s Priorities";
				
				Button viewRecipient = new Button(label);
				viewRecipient.setIconStyle("tableIcon");
				viewRecipient.addSelectionListener(ADAMVennController.buildVennRecipientMatrixMenuListener(this, vo));
				
			//	toolBar.add(viewRecipient);  
			//	toolBar.add(new SeparatorToolItem());  

			}

			
			Button exportExcel = createToolbarButton("Download Data");
			exportExcel.setIconStyle("exportDataIcon");
			exportExcel.addSelectionListener(ADAMVennController.exportExcelTableButton(vo, true));
			
			Label desc = new Label();
			desc.setStyleAttribute("font-size", "10px");
			desc.setStyleAttribute("color", "#1D4589");
			StringBuilder info = new StringBuilder();
			if(ADAMBoxMaker.countriesSelected.size() > 1)
				info.append("Click on <img src='adam-images/plus.png' border='0'/> next to the country name to view priorities. | ");
			
			info.append("When scrolling the table, hover over the <img src='adam-images/tick.png' border='0'/> for details. | Hover over the column headers and click on <img src='adam-images/arrow_down.png' border='0'/> for sorting/column options.");
			
			desc.setText(info.toString());
			
			
			if(vo.getTableContents().size() > 0) {
				toolBar.add(exportExcel);  
				toolBar.add(new SeparatorToolItem());
				toolBar.add(desc);
			}
			
			return panel;
		}
		
		// VENN CHANNEL MATRIX
		public ContentPanel buildBigVennMatrixChannelMenu(ADAMResultVO vo,  String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean showLinks) {
			ContentPanel cp = new ContentPanel();
			cp.setCollapsible(false);  
			cp.setAnimCollapse(false);
			cp.setFrame(true); 
			cp.setButtonAlign(HorizontalAlignment.CENTER);
			cp.setLayout(new FitLayout());
			cp.setHeaderVisible(false);
			cp.setBodyBorder(false);

			cp.add(buildChannelMatrixBoxMenu(vo, ADAMConstants.BIG_MENU_VENN_RECIPIENT_MATRIX_GAP_WIDTH));


			return cp;
		}

		public VerticalPanel buildChannelMatrixBoxMenu(ADAMResultVO vo, String labelWidth) { 
			VerticalPanel vPanel = new VerticalPanel();

			System.out.println("------ buildMatrixBoxMenu = "+ labelWidth + " | title = "+vo.getTitle());
			vPanel.add(buildBoxHeader(vo, labelWidth, null, false));
			vPanel.add(buildChannelMatrixIconsToolbar(vo));
			return vPanel;
		}

		public HorizontalPanel buildChannelMatrixIconsToolbar(final ADAMResultVO vo) {
			
			HorizontalPanel panel = new HorizontalPanel();
			panel.setStyleAttribute("padding-top", "3px");
			panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
			panel.setVerticalAlign(VerticalAlignment.MIDDLE);

			ToolBar toolBar = new ToolBar();  
			toolBar.setStyleAttribute("background", "#F3F5F7"); //very light grey
			toolBar.setBorders(false);
			panel.add(toolBar);

			showCPF = new CheckBox();

			showStatedPriorities = new CheckBox();
			showStatedPriorities.setValue(true);
			showStatedPriorities.addListener(Events.OnClick, ADAMVennMatrixController.showFAOAgreedStatedPriorities(this, vo, showCPF, showStatedPriorities));

			Html html = new Html("<div class='venn_matrix_option_text' style='padding: 6px;'>Show Stated Priorities</div>");
			//toolBar.add(showStatedPriorities);
			//toolBar.add(html);

			showCPF.addListener(Events.OnClick, ADAMVennMatrixController.showFAOAgreedStatedPriorities(this, vo, showCPF, showStatedPriorities));
			Html showCPFTitle = new Html("<div class='venn_matrix_option_text' style='padding: 6px;'>Show only countries with National Document</div>");
			//toolBar.add(showCPF);
			//toolBar.add(showCPFTitle);

			//toolBar.add(new SeparatorToolItem());  


			String label = "";
			if(ADAMBoxMaker.countriesSelected.size() == 1)
				label = ADAMBoxMaker.countriesSelected.entrySet().iterator().next().getValue() + "'s Priorities";
			else
				label = "Countries' Priorities";

			Button viewRecipient = createToolbarButton(label);
			viewRecipient.addSelectionListener(ADAMVennController.buildVennRecipientMatrixMenu(this, vo));
			viewRecipient.setIconStyle("tableIcon");
			//toolBar.add(viewRecipient);


			if ( ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS) ) {
				Button viewDonor = createToolbarButton("Resource Partner Priorities");
				viewDonor.addSelectionListener(ADAMVennDonorMatrixController.buildVennDonorMatrix(this, vo, "ANALYSE_TOP"));
				viewDonor.setIconStyle("tableIcon");
				//toolBar.add(viewDonor);
			}


			//toolBar.add(new SeparatorToolItem());  

			Button exportExcel = createToolbarButton("Download Data");
			exportExcel.setIconStyle("exportDataIcon");
			exportExcel.addSelectionListener(ADAMVennController.exportExcelTableButton(vo, true));
			
			
			Label desc = new Label();
			desc.setStyleAttribute("font-size", "10px");
			desc.setStyleAttribute("color", "#1D4589");
			desc.setText("Hover over the column headers and click on <img src='adam-images/arrow_down.png' border='0'/> for sorting/column options");
			
			
			if(vo.getTableContents().size() > 0) {
				toolBar.add(exportExcel);  
				toolBar.add(new SeparatorToolItem());
				toolBar.add(desc);
			}
		
			
			return panel;
		}
		
	/*public ContentPanel buildTableBoxRecipientMenu1( ADAMResultVO vo, ADAMQueryVO qvo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		Html label;
		if (vo != null) {
				label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
		}
		else
				label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
		
		h1.add(addInfo(vo.getDescription()));
		h1.add(label);
	 
		ContentPanel panel = new ContentPanel();
		
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleName("content_chart_top_panel");
		
		panel.setWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
	
		HorizontalPanel panelL = new HorizontalPanel();		
		HorizontalPanel panelR = new HorizontalPanel();	
			
		panelL.addStyleName("content_chart_top_panel_left");
		panelR.addStyleName("content_chart_top_panel_right");

		if(ADAMBoxMaker.countriesSelected.size() > 1)
			panelR.add(buildTableBoxRecipientMenu2(this, vo, qvo, position));
		else
			panelR.add(buildTableBoxRecipientMenuSingleCountry(this, vo, position));	
		
		panelL.add(h1); 

		
		toolBar.add(panelL);
		toolBar.add(new FillToolItem());  
		toolBar.add(panelR);
		
		panel.setTopComponent(toolBar);
	
		return panel;
	}*/
	
	/* public ContentPanel buildTableBoxRecipientMenu1( ADAMResultVO vo, ADAMQueryVO qvo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled) {
			HorizontalPanel h1 = new HorizontalPanel();
			h1.setHorizontalAlign(HorizontalAlignment.LEFT);
			h1.setVerticalAlign(VerticalAlignment.MIDDLE);
			h1.setSpacing(5);
			Html label;
			if (vo != null) {
					label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
			}
			else
					label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
			
			h1.add(addInfo(vo.getDescription()));
			h1.add(label);
		 
			ContentPanel panel = new ContentPanel();
			
			panel.setHeaderVisible(false);
			panel.setBodyBorder(false);
			ToolBar toolBar = new ToolBar();  
			toolBar.setStyleName("content_chart_top_panel");
			
			panel.setWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		
			HorizontalPanel panelL = new HorizontalPanel();		
			HorizontalPanel panelR = new HorizontalPanel();	
				
			panelL.addStyleName("content_chart_top_panel_left");
			panelR.addStyleName("content_chart_top_panel_right");

			if(ADAMBoxMaker.countriesSelected.size() > 1)
				panelR.add(buildTableBoxRecipientMenu2(this, vo, qvo, position));
			else
				panelR.add(buildTableBoxRecipientMenuSingleCountry(this, vo, position));	
			
			panelL.add(h1); 

			
			toolBar.add(panelL);
			toolBar.add(new FillToolItem());  
			toolBar.add(panelR);
			
			panel.setTopComponent(toolBar);
		
			return panel;
		}
	 
	 
	 
	 private HorizontalPanel buildStatedPrioritiesRecipientPanel(final ADAMVennMatrix vennMatrix, final ADAMResultVO vo, final ADAMQueryVO qvo) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		showStatedPriorities = new CheckBox();
			
		//if(ADAMBoxMaker.countriesSelected.size() == 1){
		//	showStatedPriorities.setValue(true);
		//} else {
		//	showStatedPriorities.setValue(false);
		//}
		
		showStatedPriorities.setValue(true);
		
		showStatedPriorities.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				// TODO Auto-generated method stub
				
				System.out.println("showCPF value" + showCPF.getValue());
				
				System.out.println("showStatedPriorities value" + showStatedPriorities.getValue());
								
				ADAMVennRecipientMatrixController.buildVennMatrixRecipientReloadAgent(vennMatrix, vo, showCPF.getValue(),  showStatedPriorities.getValue());
				
				vennMatrix.getPanel().layout();
				vennMatrix.getWindow().layout();
			}
			
		});
		Html html = new Html("<div class='venn_matrix_option_text'>Show Stated Priorities</div>");
		panel.add(showStatedPriorities);
		panel.add(html);
			
		return panel;
	 }
	 */
	
	 private HorizontalPanel buildStatedPrioritiesChannelPanel(final ADAMVennMatrix vennMatrix, final ADAMResultVO vo) {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setHorizontalAlign(HorizontalAlignment.LEFT);
			panel.setVerticalAlign(VerticalAlignment.MIDDLE);
			showStatedPriorities = new CheckBox();
				
			//if(ADAMBoxMaker.countriesSelected.size() == 1){
			//	showStatedPriorities.setValue(true);
			//} else {
			//	showStatedPriorities.setValue(false);
			//}
			
			showStatedPriorities.setValue(true);
			
			showStatedPriorities.addListener(Events.OnClick, new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent be) {
					// TODO Auto-generated method stub
					
					System.out.println("showCPF value" + showCPF.getValue());
					
					System.out.println("showStatedPriorities value" + showStatedPriorities.getValue());
									
					ADAMVennChannelMatrixController.buildVennMatrixChannelReloadAgent(vennMatrix, vo, showCPF.getValue(),  showStatedPriorities.getValue());
					
					vennMatrix.getPanel().layout();
					vennMatrix.getWindow().layout();
				}
				
			});
			Html html = new Html("<div class='venn_matrix_option_text'>Show Stated Priorities</div>");
			panel.add(showStatedPriorities);
			panel.add(html);
				
			return panel;
		 }
	 
	 
	 
	/* private HorizontalPanel buildTableBoxRecipientMenu2(final ADAMVennMatrix vennMatrix, final ADAMResultVO vo, final ADAMQueryVO qvo, String position) {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setSpacing(7);
			
			showCPF = new CheckBox();

			
			panel.add(buildStatedPrioritiesRecipientPanel(vennMatrix, vo, qvo));
			
			HorizontalPanel p = new HorizontalPanel();
			p.setHorizontalAlign(HorizontalAlignment.LEFT);
			p.setVerticalAlign(VerticalAlignment.MIDDLE);
			
			
			
			showCPF.addListener(Events.OnClick, new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent be) {
					// TODO Auto-generated method stub
					System.out.println("checkbox");
					
					System.out.println("showCPF value" + showCPF.getValue());
							
					System.out.println("showStatedPriorities value" + showStatedPriorities.getValue());

					ADAMVennRecipientMatrixController.buildVennMatrixRecipientReloadAgent(vennMatrix, vo, showCPF.getValue(), showStatedPriorities.getValue());
					
					vennMatrix.getPanel().layout();
		
					vennMatrix.getWindow().layout();
				}
				
			});
			Html showCPFTitle = new Html("<div class='venn_matrix_option_text'>Show only countries with National Document</div>");
			p.add(showCPF);
			p.add(showCPFTitle);
			
			panel.add(p);
			
			// add listener
			
			SplitButton view = new SplitButton("<div class='button-options-text'>Show Priorities</div>");
			
			Menu viewMenu = new Menu();
			view.setMenu(viewMenu);
			view.setIconStyle("tableIcon");

			
			MenuItem viewFao = new MenuItem("Agreed Priorities with FAO");
			
			//if(ADAMBoxMaker.countriesSelected.size() > 1){
			//	viewFao.addSelectionListener(ADAMVennController.buildVennMatrixChannel(vennMatrix, vo, false, false));
			//} else {
			//	viewFao.addSelectionListener(ADAMVennController.buildVennMatrixChannel(vennMatrix, vo, false, true));
			//}
			
			//view priorities by default
			viewFao.addSelectionListener(ADAMVennChannelMatrixController.buildVennMatrixChannel(vennMatrix, vo, false, true));
			
			//viewFao.addSelectionListener(ADAMVennController.buildVennMatrixChannel(vennMatrix, vo, false, false));
			viewFao.setIconStyle("tableIcon");
			viewMenu.add(viewFao);
			
			if ( ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS) ) {
				MenuItem viewDonor = new MenuItem("Resource Partner Priorities");
				viewDonor.addSelectionListener(ADAMVennDonorMatrixController.buildVennDonorMatrix(vennMatrix, vo));
				viewDonor.setIconStyle("tableIcon");
				viewMenu.add(viewDonor);
			}

			
			panel.add(view);

			
			Menu moreMenu = new Menu();
			SplitButton more = new SplitButton("<div class='button-options-text'>More Options</div>");
			more.setIconStyle("gear");
			

			
			more.setMenu(moreMenu);

			

			MenuItem exportExcel = new MenuItem("Export Excel");
			exportExcel.setIconStyle("sendToExcel");
			exportExcel.addSelectionListener(ADAMController.exportExcelTable(vo, true));
			MenuItem exportAllResources = new MenuItem("Export Visible Resources");
			exportAllResources.setIconStyle("");
			
			moreMenu.add(exportExcel);

			
			panel.add(more);
			
			
			Button exportExcel = new Button("Export to Excel");
			exportExcel.setIconStyle("sendToExcel");
			exportExcel.addSelectionListener(ADAMVennController.exportExcelTableButton(vo, true));
			
			panel.add(exportExcel);

//			panel.add(buildClosePanel());
		return panel;
	}
	 
	 private HorizontalPanel buildTableBoxRecipientMenuSingleCountry(final ADAMVennMatrix vennMatrix, final ADAMResultVO vo, String position) {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setSpacing(7);
			
			
			Button viewDonor = new Button("View Resource Partner Priorities");
			viewDonor.setIconStyle("tableIcon");
			viewDonor.addSelectionListener(ADAMVennDonorMatrixController.buildVennDonorMatrixListener(vennMatrix, vo));
			
			panel.add(viewDonor);
			
			Button exportExcel = new Button("Export to Excel");
			exportExcel.setIconStyle("sendToExcel");
			exportExcel.addSelectionListener(ADAMVennController.exportExcelTableButton(vo, true));
			
			panel.add(exportExcel);
			

//			panel.add(buildClosePanel());
		return panel;
	}
	 */
	 
	 private HorizontalPanel buildClosePanel() {
		 HorizontalPanel panel = new HorizontalPanel();
		 Button close = new Button();
			close = new Button("Close");
			close.setToolTip("Close the view");
			close.setIconStyle("delete");
	
			close.addSelectionListener(new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					getWindow().hide();
				}
			});
			
			panel.add(close);
			return panel;
	 }
	 
	 
	 // VENN CHANNEL MATRIX
	 public ContentPanel buildBigVennMatrixChannelMenu(ADAMResultVO vo, ADAMQueryVO qvo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean showLinks) {
			ContentPanel cp = new ContentPanel();
			cp.setCollapsible(false);  
			cp.setAnimCollapse(false);
			cp.setFrame(true); 
			cp.setButtonAlign(HorizontalAlignment.CENTER);
			cp.setLayout(new FitLayout());
			cp.setHeaderVisible(false);
			cp.setBodyBorder(false);
			
//			HorizontalPanel h1 = buildTableBoxChannelMenu1(vo, ADAMConstants.BIG_MENU_VENN_CHANNEL_MATRIX_GAP_WIDTH, position, color, objectSizeListener, false, false);
//			cp.add(h1);
			
			cp.add(buildTableBoxChannelMenu1(qvo, vo, ADAMConstants.BIG_MENU_VENN_CHANNEL_MATRIX_GAP_WIDTH, position, color, objectSizeListener, false, false));
			
			return cp;
	}

	 public ContentPanel buildTableBoxChannelMenu1(ADAMQueryVO qvo, ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled) {
//			HorizontalPanel h1 = new HorizontalPanel();
//			h1.setHorizontalAlign(HorizontalAlignment.LEFT);
//			h1.setVerticalAlign(VerticalAlignment.MIDDLE);
//			h1.setSpacing(5);
//			Html label;
//			if (vo != null) {
//					label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
//			}
//			else
//					label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
//			
//			label.setWidth(labelWidth);
//			h1.add(addInfo(vo.getDescription()));
//			h1.add(label);
//			h1.add(buildTableBoxChannelMenu2(this, vo, position));
		 
		 
		 HorizontalPanel h1 = new HorizontalPanel();
			h1.setHorizontalAlign(HorizontalAlignment.LEFT);
			h1.setVerticalAlign(VerticalAlignment.MIDDLE);
			h1.setSpacing(5);
			Html label;
			if (vo != null) {
					label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
			}
			else
					label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
			
			h1.add(addInfo(vo.getDescription()));
			h1.add(label);
		 
			ContentPanel panel = new ContentPanel();
			
			panel.setHeaderVisible(false);
			panel.setBodyBorder(false);
			ToolBar toolBar = new ToolBar();  
			toolBar.setStyleName("content_chart_top_panel");
			
			panel.setWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		
			HorizontalPanel panelL = new HorizontalPanel();		
			HorizontalPanel panelR = new HorizontalPanel();	
				
			panelL.addStyleName("content_chart_top_panel_left");
			panelR.addStyleName("content_chart_top_panel_right");

			panelR.add(buildTableBoxChannelMenu2(this, vo, position));
			panelL.add(h1); 

			
			toolBar.add(panelL);
			toolBar.add(new FillToolItem());  
			toolBar.add(panelR);
			
			panel.setTopComponent(toolBar);
		
			return panel;
		}
	 
	 
	 private HorizontalPanel buildTableBoxChannelMenu2(final ADAMVennMatrix vennMatrix, final ADAMResultVO vo, String position) {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setSpacing(5);
			
			HorizontalPanel p = new HorizontalPanel();
			p.setHorizontalAlign(HorizontalAlignment.LEFT);
			p.setVerticalAlign(VerticalAlignment.MIDDLE);
			showCPF = new CheckBox();
			
			panel.add(buildStatedPrioritiesChannelPanel(vennMatrix, vo));
			
			showCPF.addListener(Events.OnClick, new Listener<BaseEvent>() {
				public void handleEvent(BaseEvent be) {
					// TODO Auto-generated method stub
					System.out.println("checkbox");

					ADAMVennChannelMatrixController.buildVennMatrixChannelReloadAgent(vennMatrix, vo, showCPF.getValue(), showStatedPriorities.getValue());
					
					vennMatrix.getPanel().layout();
		
					vennMatrix.getWindow().layout();
				}
				
			});
			Html showCPFTitle = new Html("<div class='venn_matrix_option_text'>Show only countries with CPF</div>");
			p.add(showCPF);
			p.add(showCPFTitle);
			
			panel.add(p);
			
			// add listener
			
			Menu viewMenu = new Menu();
			SplitButton view = new SplitButton("<div class='button-options-text'>Show Priorities</div>");
			view.setMenu(viewMenu);
			
			view.setIconStyle("tableIcon");
			
			String label = "";
			if(ADAMBoxMaker.countriesSelected.size() == 1)
				label = ADAMBoxMaker.countriesSelected.entrySet().iterator().next().getValue() + "'s Priorities";
			else
				label = "Countries' Priorities";
					
			MenuItem viewRecipient = new MenuItem(label);
			//RE-INSTATE////viewRecipient.addSelectionListener(ADAMVennController.buildVennRecipientMatrixMenu(vennMatrix, vo));
			viewRecipient.setIconStyle("tableIcon");
			viewMenu.add(viewRecipient);

			
			if ( ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS) ) {
				MenuItem viewDonor = new MenuItem("Resource Partner Priorities");
				//viewDonor.addSelectionListener(ADAMVennDonorMatrixController.buildVennDonorMatrix(vennMatrix, vo));
				viewDonor.setIconStyle("tableIcon");
				viewMenu.add(viewDonor);
			}
			
			
			
			
			panel.add(view);

			
			/**Menu moreMenu = new Menu();
			SplitButton more = new SplitButton("<div class='button-options-text'>More Options</div>");
			more.setIconStyle("gear");

			more.setMenu(moreMenu);

			MenuItem exportExcel = new MenuItem("Export Excel");
			exportExcel.setIconStyle("sendToExcel");
			exportExcel.addSelectionListener(ADAMController.exportExcelTable(vo, true));
			MenuItem exportAllResources = new MenuItem("Export Visible Resources");
			exportAllResources.setIconStyle("");

			moreMenu.add(exportExcel);
			
			panel.add(more);**/
			
			Button exportExcel = new Button("Export to Excel");
			exportExcel.setIconStyle("sendToExcel");
			exportExcel.addSelectionListener(ADAMVennController.exportExcelTableButton(vo, true));
			
			panel.add(exportExcel);
			

//			panel.add(buildClosePanel());
		return panel;
	}
	 
	 
	 
	
	 
	 ////////////
	/* public ContentPanel buildBigVennMatrixDonorMenu(ADAMResultVO vo,  String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean showLinks) {
			ContentPanel cp = new ContentPanel();
			cp.setCollapsible(false);  
			cp.setAnimCollapse(false);
			cp.setFrame(true); 
			cp.setButtonAlign(HorizontalAlignment.CENTER);
			cp.setLayout(new FitLayout());
			cp.setHeaderVisible(false);
			cp.setBodyBorder(false);
			
//			HorizontalPanel h1 = buildTableBoxDonorMenu1(vo, ADAMConstants.BIG_MENU_VENN_DONOR_MATRIX_GAP_WIDTH, position, color, objectSizeListener, false, false);
//			cp.add(h1);
			
			cp.add(buildTableBoxDonorMenu1(vo, ADAMConstants.BIG_MENU_VENN_DONOR_MATRIX_GAP_WIDTH, position, color, objectSizeListener, false, false));
			
			return cp;
	}

	 public ContentPanel buildTableBoxDonorMenu1( ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled) {
//			HorizontalPanel h1 = new HorizontalPanel();
//			h1.setHorizontalAlign(HorizontalAlignment.LEFT);
//			h1.setVerticalAlign(VerticalAlignment.MIDDLE);
//			h1.setSpacing(5);
//			Html label;
//			if (vo != null) {
//					label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
//			}
//			else
//					label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
//			
//			label.setWidth(labelWidth);
//			h1.add(addInfo(vo.getDescription()));
//			h1.add(label);
//			h1.add(buildTableBoxDonorMenu2(this, vo, position));
		 
		 HorizontalPanel h1 = new HorizontalPanel();
			h1.setHorizontalAlign(HorizontalAlignment.LEFT);
			h1.setVerticalAlign(VerticalAlignment.MIDDLE);
			h1.setSpacing(5);
			Html label;
			if (vo != null) {
					label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
			}
			else
					label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
			
			h1.add(addInfo(vo.getDescription()));
			h1.add(label);
		 
			ContentPanel panel = new ContentPanel();
			
			panel.setHeaderVisible(false);
			panel.setBodyBorder(false);
			ToolBar toolBar = new ToolBar();  
			toolBar.setStyleName("content_chart_top_panel");
			
			panel.setWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		
			HorizontalPanel panelL = new HorizontalPanel();		
			HorizontalPanel panelR = new HorizontalPanel();	
				
			panelL.addStyleName("content_chart_top_panel_left");
			panelR.addStyleName("content_chart_top_panel_right");

			if(ADAMBoxMaker.countriesSelected.size() > 1)
				panelR.add(buildTableBoxDonorMenu2(this, vo, position));
			else
				panelR.add(buildTableBoxDonorMenuSingleCountry(this, vo, position));
			
			panelL.add(h1); 

			
			toolBar.add(panelL);
			toolBar.add(new FillToolItem());  
			toolBar.add(panelR);
			
			panel.setTopComponent(toolBar);
		
			return panel;
		}
	 
	 
	 private HorizontalPanel buildTableBoxDonorMenu2(final ADAMVennMatrix vennMatrix, final ADAMResultVO vo, String position) {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setSpacing(5);
	

			
			// add listener
			
			Menu viewMenu = new Menu();
			SplitButton view = new SplitButton("<div class='button-options-text'>Show Priorities</div>");
			view.setIconStyle("tableIcon");
			
			String label = "";
			if(ADAMBoxMaker.countriesSelected.size() == 1)
				label = ADAMBoxMaker.countriesSelected.entrySet().iterator().next().getValue() + "'s Priorities";
			else
				label = "Countries' Priorities";
				
			MenuItem viewRecipient = new MenuItem(label);
			viewRecipient.addSelectionListener(ADAMVennController.buildVennRecipientMatrixMenu(vennMatrix, vo));
			viewRecipient.setIconStyle("tableIcon");
			
			MenuItem viewFAO = new MenuItem("Agreed Priorities with FAO");
			
			
			*//**if(ADAMBoxMaker.countriesSelected.size() > 1){
				viewFAO.addSelectionListener(ADAMVennController.buildVennMatrixChannel(vennMatrix, vo, false, false));
			} else {
				viewFAO.addSelectionListener(ADAMVennController.buildVennMatrixChannel(vennMatrix, vo, false, true));
			}**//*
			
			//set view priorities by default
		//	viewFAO.addSelectionListener(ADAMVennChannelMatrixController.buildVennMatrixChannel(vennMatrix, vo, false, true));
			
			viewFAO.setIconStyle("tableIcon");
			
			view.setMenu(viewMenu);
			
			viewMenu.add(viewRecipient);
			viewMenu.add(viewFAO);
			
			panel.add(view);

			
			*//**Menu moreMenu = new Menu();
			SplitButton more = new SplitButton("<div class='button-options-text'>More Options</div>");
			more.setIconStyle("gear");

			more.setMenu(moreMenu);

			MenuItem exportExcel = new MenuItem("Export Excel");
			exportExcel.setIconStyle("sendToExcel");
			exportExcel.addSelectionListener(ADAMController.exportExcelTable(vo, true));
			MenuItem exportAllResources = new MenuItem("Export Visible Resources");
			exportAllResources.setIconStyle("");

			moreMenu.add(exportExcel);

			
			panel.add(more);**//*
			
			
			Button exportExcel = new Button("Export to Excel");
			exportExcel.setIconStyle("sendToExcel");
			exportExcel.addSelectionListener(ADAMVennController.exportExcelTableButton(vo, true));
			
			panel.add(exportExcel);

//			panel.add(buildClosePanel());
		return panel;
	}
	 
	 private HorizontalPanel buildTableBoxDonorMenuSingleCountry(final ADAMVennMatrix vennMatrix, final ADAMResultVO vo, String position) {
			HorizontalPanel panel = new HorizontalPanel();
			panel.setSpacing(5);
	
			String label = "View "+ ADAMBoxMaker.countriesSelected.entrySet().iterator().next().getValue() + "'s Priorities";
			
			Button viewRecipient = new Button(label);
			viewRecipient.setIconStyle("tableIcon");
			viewRecipient.addSelectionListener(ADAMVennController.buildVennRecipientMatrixMenuListener(vennMatrix, vo));
			
			panel.add(viewRecipient);
			
			
			Button exportExcel = new Button("Export to Excel");
			exportExcel.setIconStyle("sendToExcel");
			exportExcel.addSelectionListener(ADAMVennController.exportExcelTableButton(vo, true));
			
			panel.add(exportExcel);

//			panel.add(buildClosePanel());
		return panel;
	}*/

	public ContentPanel getMatrixPanel() {
		return matrixPanel;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public Window getWindow() {
		return window;
	}
	
	 
	
	 

}
