package org.fao.fenix.web.modules.adam.client.view.makers;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.control.DonorProfileController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMDonorMatrixController;
import org.fao.fenix.web.modules.adam.client.view.ADAMReferencePeriodPanel;
import org.fao.fenix.web.modules.adam.client.view.ADAMViewMenu;
import org.fao.fenix.web.modules.adam.client.view.menu.ADAMViewMenuBuilder;
import org.fao.fenix.web.modules.adam.client.view.profile.ADAMDonorProfileTabs;
import org.fao.fenix.web.modules.adam.common.model.DonorMatrixModel;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorMatrixVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.core.client.utils.ClickHtml;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.common.vo.OLAPFilterVo;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMDonorProfileMaker extends ADAMBoxMaker {

	
	//called from DonorMatrix
	public static VerticalPanel build(final ADAMDonorMatrixVO matrixVo, final List<DonorMatrixModel> models, ADAMDonorProfileVO vo, String donorcode, String donorLabel) {
		VerticalPanel p = new VerticalPanel();
		 p.setStyleAttribute("backgroundColor", "#FFFFFF");
		 p.setBorders(false);
		
		HorizontalPanel h1 =  new HorizontalPanel();
		HorizontalPanel donorProfilePanel =  new HorizontalPanel();
		donorProfilePanel.setStyleAttribute("backgroundColor", "#FFFFFF");
		donorProfilePanel.setBorders(false);
		
		 LayoutContainer cp = new LayoutContainer();
		 cp.setLayout(new FitLayout());
		 cp.setStyleAttribute("backgroundColor", "#FFFFFF");
		 cp.setBorders(false);
	   // cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH), 430); 
		  
		  
	   
	    cp.setSize(1150, 400);
		
		if(donorcode!=null && !donorcode.equals("")){	
			//System.out.println("zzzzzzzzzzzzzzzzzzzzz ADAMDonorProfileMaker: build CALLED");
			
			if(vo.getResponsibleMobilizationOfficer()!=null){
				p.add(buildDonorProfileMenu("850px", vo, matrixVo, models));
				p.add(buildWaitingPanel("795px"));

				buildHeaderRows(vo,p,donorcode,donorLabel);


				cp = buildProfile(cp, donorcode, vo);
			} else {
				p.add(buildDonorProfileMenu("850px", null, matrixVo, models));
				cp.add(new Html("<b>"+"No resource partner profile available for "+donorLabel+"</b>"));
			}
		}
		else {
			//cp.setHeaderVisible(false); 		
			
			cp.add(new Html("<div id='profile-header-box'><div class='profile-content'>"+BabelFish.print().pleaseSelectResourcePartner()+"</div></div>"));	
		}
		
		donorProfilePanel.add(cp);
		
		p.add(donorProfilePanel);
		
		return p;
	}
	
	public static VerticalPanel build(ADAMDonorProfileVO vo, String donorcode) {
		VerticalPanel p = new VerticalPanel();
		 p.setStyleAttribute("backgroundColor", "#FFFFFF");
		 p.setBorders(false);
		
		HorizontalPanel h1 =  new HorizontalPanel();
		HorizontalPanel donorProfilePanel =  new HorizontalPanel();
		donorProfilePanel.setStyleAttribute("backgroundColor", "#FFFFFF");
		donorProfilePanel.setBorders(false);
		
		 LayoutContainer cp = new LayoutContainer();
		 cp.setLayout(new FitLayout());
		 cp.setStyleAttribute("backgroundColor", "#FFFFFF");
		 cp.setBorders(false);
	     // cp.setBounds(10, 10, Integer.valueOf(ADAMConstants.BIG_BOX_WIDTH), 430); 
		  
		  
	   
	     cp.setSize(1150, 400);
		
		if(donorcode!=null && !donorcode.equals("")){	
			
			if(vo.getResponsibleMobilizationOfficer()!=null){
				p.add(buildDonorProfileMenu("850px", vo, null, null));
				p.add(buildWaitingPanel("795px"));

				buildHeaderRows(vo,p,donorcode, null);


				cp = buildProfile(cp, donorcode, vo);
			} else {
				cp.add(new Html("No resource partner profile available for "+donorList.getValue().getGaulLabel()));
			}
		}
		else {
			//cp.setHeaderVisible(false); 
			cp.add(new Html("<div id='profile-header-box'><div class='profile-content'>"+BabelFish.print().pleaseSelectResourcePartner()+"</div></div>"));	
		}
		
		donorProfilePanel.add(cp);
		
		p.add(donorProfilePanel);
		
		return p;
	}
	
	public static ContentPanel buildProfile(ContentPanel cp, String donorcode, ADAMDonorProfileVO vo) {
		cp.setBorders(false);
		   
		cp.add(ADAMDonorProfileTabs.build(vo));
		
		return cp;
	}
	
	public static LayoutContainer buildProfile(LayoutContainer cp, String donorcode, ADAMDonorProfileVO vo) {
		cp.setBorders(false);
		   
		cp.add(ADAMDonorProfileTabs.build(vo));
		
		return cp;
	}
	

	public static void buildHeaderRows(ADAMDonorProfileVO vo, VerticalPanel panel, String donorcode, String donorLabel){
		
		panel.add(buildTitleOfficerRow(vo.getResourceMobilizationOfficerTitle(), vo.getResponsibleMobilizationOfficer(), donorcode, donorLabel));
		panel.add(buildEmailRow(vo.getEmailTitle(), vo.getResourceMobilizationOfficerEmail()));
	}
	
	
	 private static HorizontalPanel buildTitleOfficerRow(String title, String officer, String donorcode, String donorLabel){

		 HorizontalPanel titlePanel = new HorizontalPanel();
		 titlePanel.setTableWidth("100%");
		    
			TableData data = new TableData("65%", "100%");
		    data.setHorizontalAlign(HorizontalAlignment.CENTER);
		    
		    ClickHtml donorName = new ClickHtml();
		    if(donorLabel==null){
		    	donorName.setHtml("<span style='text-decoration: underline; cursor: pointer; font-size:20px'>"+donorList.getValue().getGaulLabel()+"</span>");
		    	donorName.setTitle(BabelFish.print().clickToOpenADAMView()+ " " + donorList.getValue().getGaulLabel());
		    	donorName.addListener(Events.OnClick, DonorProfileController.openADAMDonorView(donorcode, donorList.getValue().getGaulLabel()));
		    }
		    else {
		    	donorName.setHtml("<span style='text-decoration: underline; cursor: pointer; font-size:20px'>"+donorLabel+"</span>");
		    	donorName.setTitle(BabelFish.print().clickToOpenADAMView()+ " " + donorLabel);
		    	donorName.addListener(Events.OnClick, DonorProfileController.openADAMDonorView(donorcode, donorLabel));
		    }
		    
		    titlePanel.add(donorName, data);

		    data = new TableData("20%", "100%");
		    data.setHorizontalAlign(HorizontalAlignment.RIGHT);
		    titlePanel.add(new Html("<b>"+title+":<b/>"), data);
		   
		    data = new TableData("15%", "100%");
		    data.setHorizontalAlign(HorizontalAlignment.LEFT);
		    titlePanel.add(new Html("&nbsp;&nbsp;"+ officer), data);
		    
		
			return titlePanel;
			    
		}
		
	 
	 private static HorizontalPanel buildEmailRow(String title, String email){

	    HorizontalPanel headerPanel = new HorizontalPanel();
	    headerPanel.setTableWidth("100%");

	    TableData data = new TableData("85%", "100%");
		data.setHorizontalAlign(HorizontalAlignment.RIGHT);
		headerPanel.add(new Html("<b>"+title+":<b/>"), data);
		
		 data = new TableData("15%", "100%");
		 data.setHorizontalAlign(HorizontalAlignment.LEFT);
		 headerPanel.add(new Html("&nbsp;&nbsp;"+ email), data);
		  
		 return headerPanel;
	}

	
	public static HorizontalPanel buildDonorProfileMenu(String labelWidth, ADAMDonorProfileVO vo, final ADAMDonorMatrixVO matrixVo, final List<DonorMatrixModel> models) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);	
		h1.add(buildDonorProfileButtonsMenu(vo, labelWidth, matrixVo, models));
		
		return h1;
	}
	
	public static HorizontalPanel buildWaitingPanel(String labelWidth) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		
		Html label = new Html("<div class='title-box-nounderline'></div>");
		
		h1.add(label);
				
		label.setWidth(labelWidth);
		h1.add(label);
		h1.add(ADAMViewMenuBuilder.buildWaitingPanel());
		ADAMViewMenuBuilder.hideWaitingPanel();
//		h1.add(ADAMViewMenu.buildWaitingPanel());
//		ADAMViewMenu.hideWaitingPanel();
		return h1;
	}
	
	
	/*private static HorizontalPanel buildDonorProfileButtonsMenu(final ADAMDonorProfileVO vo, final LayoutContainer contentPanel) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		
		Button button = new Button(BabelFish.print().print());
		button.setIconStyle("print");
		button.addSelectionListener(DonorProfileController.exportPDF(vo));
		panel.add(button);
		
		
		return panel;
	}*/
	
	private static HorizontalPanel buildDonorProfileButtonsMenu(final ADAMDonorProfileVO vo, String labelWidth, final ADAMDonorMatrixVO matrixVo, final List<DonorMatrixModel> matrixModels) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(10);
		
		Button print = new Button(BabelFish.print().print());
		print.setIconStyle("print");
		
		
		
		if(matrixVo!=null && matrixModels!=null){
			OLAPFilterVo filter =  matrixVo.getOlapParametersVO().getFilters().get(0);
			List<String> dates = new ArrayList<String>();

			for (String value: filter.getDimensionMap().keySet()) 
				dates.add(value);

			Button switchToDonorMatrixView = new Button("Go Back To Matrix");
			switchToDonorMatrixView.setIconStyle("back");
			
			
			
			switchToDonorMatrixView.addSelectionListener(ADAMDonorMatrixController.backToMatrixViewFromProfiles(matrixVo, dates, matrixModels));

			panel.add(switchToDonorMatrixView);
		}
		
	    Html label = new Html("<div class='title-box-nounderline'></div>");
	    label.setWidth(labelWidth);
	      
		panel.add(label);
		
		if(vo!=null){
			print.addSelectionListener(DonorProfileController.exportPDF(vo));
			panel.add(print);
		}

		return panel;
	}
}