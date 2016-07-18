package org.fao.fenix.web.modules.adam.client.view.venn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.control.venn.ADAMVennController;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;

public class ADAMVennPriorities {
	
	public static ADAMResultVO rvo;
	
	public static VerticalPanel infoPanelBig;
	
	public static VerticalPanel infoPanelSmall;
	
    public static VerticalPanel vennPanelBig;
	
	public static VerticalPanel vennPanelSmall;
	
	public static HashMap<String, String> vennDescription = new HashMap<String, String>();
	
	
	public static VerticalPanel buildVennBoxMenu(ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) { 
		VerticalPanel vPanel = new VerticalPanel();
		
		System.out.println("------ buildVennBoxMenu = "+ labelWidth + " | title = "+vo.getTitle());
		vPanel.add(ADAMBoxMaker.buildBoxHeader(vo, labelWidth, objectSizeListener, isSmall));
		vPanel.add(ADAMBoxMaker.buildIconsToolbar(null, vo, position, color, objectSizeListener, isSmall));
		return vPanel;
	}

	public static VerticalPanel buildSmallPriorityVenn(String title, String image, String width, String height, ADAMResultVO rvo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener) {
		
		VerticalPanel p = new VerticalPanel();
		p.setHeight(ADAMConstants.SMALL_BOX_HEIGHT_MAP);
		//p.setWidth(ADAMConstants.BIG_TABLE_CHART_WIDTH);
		
		String labelWidth = ADAMConstants.SMALL_MENU_VENN_GAP_WIDTH_2;
		
		//if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
			//labelWidth = ADAMConstants.SMALL_MENU_VENN_GAP_WIDTH_2;
		//else 
		//	labelWidth = ADAMConstants.SMALL_MENU_VENN_GAP_WIDTH;
		
		VerticalPanel h1 = buildVennBoxMenu(rvo, ADAMConstants.BIG_MENU_GAP, position, color, objectSizeListener, true);	
		//HorizontalPanel h1 = buildBoxMenu(rvo, labelWidth, position, color,  objectSizeListener, true);
		HorizontalPanel holder =  new HorizontalPanel();
		//holder.setSpacing(5);
		
		infoPanelSmall = new VerticalPanel();
		infoPanelSmall.setBorders(true);
		infoPanelSmall.setHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		infoPanelSmall.setWidth(ADAMConstants.BIG_VENN_WIDTH);
		infoPanelSmall.setScrollMode(Scroll.AUTO);
		//infoPanelSmall.setSpacing(5);
		infoPanelSmall.setStyleAttribute("background", "#FFFFFF");
		infoPanelSmall.setStyleAttribute("padding-left", "10px");
		//infoPanelSmall.setHorizontalAlign(HorizontalAlignment.CENTER);
		
	
		/*Html defaultTitle = new Html("<div align='center' class='title'>Click on a Venn Diagram Intersection to get additional information. <br/><br/>For a more detailed explanation on the Venn Diagram click on <img src='adam-images/info.gif' valign='bottom'/></div>");
		infoPanelSmall.add(defaultTitle);*/
		    
		vennPanelSmall =  new VerticalPanel();
		vennPanelSmall.setHeight(ADAMConstants.SMALL_TABLE_CHART_HEIGHT);
		vennPanelSmall.add(buildSmallVenn(image));
		
		holder.add(vennPanelSmall);
		holder.add(infoPanelSmall);
	
		p.add(h1);
		p.add(holder);
		return p;
	}
	
	public static VerticalPanel buildBigPriorityVenn(String title, String image, String width, String height, ADAMResultVO rvo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener) {

		VerticalPanel p = new VerticalPanel();
		p.setHeight(ADAMConstants.BIG_BOX_HEIGHT);
		p.setWidth(ADAMConstants.BIG_BOX_WIDTH);
		
		//String  labelWidth = ADAMConstants.BIG_MENU_VENN_GAP_WIDTH_2;
		
		String  labelWidth = ADAMConstants.BIG_MENU_VENN_GAP_WIDTH;
		
		///if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
		//	labelWidth = ADAMConstants.BIG_MENU_VENN_GAP_WIDTH_2;
		//else 
			//labelWidth = ADAMConstants.BIG_MENU_VENN_GAP_WIDTH;
		
		HorizontalPanel h1 = buildBoxMenu(rvo, labelWidth, position, color,  objectSizeListener, false);
		HorizontalPanel holder =  new HorizontalPanel();
		holder.setSpacing(20);

		infoPanelBig =  new VerticalPanel();
		infoPanelBig.setHeight(ADAMConstants.BIG_VENN_HEIGHT);
		infoPanelBig.setWidth(ADAMConstants.BIG_VENN_WIDTH);
		infoPanelBig.setScrollMode(Scroll.AUTO);
		infoPanelBig.setHorizontalAlign(HorizontalAlignment.CENTER);
		
		Html defaultTitle = new Html("<div align='center' class='title'>Click on a Venn Diagram Intersection to get additional information. <br/><br/>For a more detailed explanation on the Venn Diagram click on <img src='adam-images/info.gif' valign='bottom'/></div>");
		
        if(!vennDescription.isEmpty()){
        	infoPanelBig.add(new Html(vennDescription.get("TITLE")));
        	infoPanelBig.add(new Html(vennDescription.get("CONTENT")));
			
		} else 
			infoPanelBig.add(defaultTitle);
		
       
		vennPanelBig =  new VerticalPanel();
		vennPanelBig.setHeight(ADAMConstants.BIG_VENN_HEIGHT);
		vennPanelBig.add(buildBigVenn(image));
		  
		holder.add(vennPanelBig);
		holder.add(infoPanelBig);
	
		
		p.add(h1);
		p.add(holder);
		return p;
	}
	
	public static HorizontalPanel buildBoxMenu(ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) {
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
		
		label.setWidth(labelWidth);	
			
		//h1.add(ADAMBoxMaker.addPrioritiesDisclaimer());
		h1.add(ADAMBoxMaker.addInfo(vo.getDescription(), "Explanation note on the Venn Diagram intersections", 530));
		h1.add(label);
		h1.add(buildVennBoxMenu(vo, position, color, objectSizeListener, isSmall));
		return h1;
	}
	
	private static HorizontalPanel buildVennBoxMenu(ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton("<div class='button-options-text'>More Options</div>");
		more.setIconStyle("gear");
		
		Button showRecipients = new Button("<div class='button-options-text'>What are the Priorities?</div>");
		showRecipients.setIconStyle("tableIcon");
		//showRecipients.addSelectionListener(ADAMVennController.buildVennRecipientMatrix());
		
//		showRecipients.addSelectionListener(ADAMViewMatrix.showTableRecipientsORsButton(rvo));

		if ( !vo.getSwitchResources().isEmpty() && isSmall) {
			for ( ADAMQueryVO switchVO : vo.getSwitchResources()) {
				System.out.println("chart swtich resource button");
				
				MenuItem switchResource = new MenuItem();
				ADAMBoxContent type = ADAMBoxContent.valueOf(switchVO.getResourceType());

				switch(type) {
				case TABLE:
					switchResource.setText("See as Table");
					switchResource.setIconStyle("tableIcon");
				break;

				case CHART:
					switchResource.setText("See as " + switchVO.getOutputType().toLowerCase());
					switchResource.setIconStyle("chartIcon");
				break;
				}

				switchResource.addSelectionListener(ADAMCustomController.switchResource(position, color, switchVO));
				moreMenu.add(switchResource);
			}
		}

//		moreMenu.add(showRecipients);
//		moreMenu.add(showChart);
		more.setMenu(moreMenu);

		
		Button full = new Button();
		full.setToolTip("Go to full screen view");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close the view");
			full.setIconStyle("delete");
		}
		//full.setEnabled(false);
		full.addSelectionListener(objectSizeListener);
		
		
		//if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
		//panel.add(showRecipients);
		
//		panel.add(more);
//		h1.add(print);
//		panel.add(export);
		
	panel.add(full);
		return panel;
	}
	

	public ADAMVennPriorities(ADAMResultVO resultVo, Boolean isSmallVenn){
		rvo = resultVo;
	}
	
	public static HTML buildBigVenn(String imagePath) {
		
		VennBeanVO vennBean = rvo.getVennBean();
		
		System.out.println("buildBigVenn");
		HTML vennHtml = new HTML();
		StringBuffer venn = new StringBuffer();
		
		venn.append("<div>");
		venn.append("<MAP id='vennid2' Name='mymap2'> ");

		// A intersection
		venn.append("<area class='showTip A' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getA().getHoverLabel() +". Click 'i' for more info. \" title=\""+ vennBean.getVennGraphBeanVO().getA().getHoverLabel() +". Click 'i' for more info. \" " +
					"onmouseover='hover()'  onClick=\"writePriorities('a', 'bigVenn', event)\" href=\"#ANALYSE_VENN\" " +
					"coords='109,54,69,68,45,104,46,140,64,167,74,176,88,181,104,147,136,133,137,101,151,67,153,65,122,51'></area>");
					
		venn.append("<area class='showTip B'  shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getB().getHoverLabel() +". Click 'i' for more info. \" title=\""+ vennBean.getVennGraphBeanVO().getB().getHoverLabel() +". Click 'i' for more info. \" " +
					"onmouseover='hover()'  onClick=\"writePriorities('b', 'bigVenn', event)\" href=\"#ANALYSE_VENN\" " +
					"coords='175,64,208,50,258,60,279,101,284,147,257,174,239,182,225,154,197,136,193,108,177,66,179,66,178,66'/>");

		venn.append("<area class='showTip AB' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getAb().getLabel() +"\" title=\""+ vennBean.getVennGraphBeanVO().getAb().getLabel() +"\" " +
					"onmouseover='hover()'  onClick=\"writePriorities('ab', 'bigVenn', event)\" href=\"#ANALYSE_VENN\" " +
					"coords='162,71,150,94,146,121,147,135,165,131,183,135,183,107,166,71'/>");

		
	    //  C intersection big image
		venn.append("<area class='showTip C' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getC().getHoverLabel() +". Click 'i' for more info. \"title=\""+ vennBean.getVennGraphBeanVO().getC().getHoverLabel() +". Click 'i' for more info. \" " +
				    "onmouseover=\"onhover('c', event)\"  onClick=\"writePriorities('c', 'bigVenn', event)\" href=\"#ANALYSE_VENN\" " +
				    "coords='95,198,100,238,137,273,169,276,206,266,225,235,234,215,234,199,195,198,164,180,144,198,96,197'/>");
		

		venn.append("<area class='showTip AC' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getAc().getLabel() +"\" title=\""+ vennBean.getVennGraphBeanVO().getAc().getLabel() +"\"" +
				    "onmouseover='hover()'  onClick=\"writePriorities('ac', 'bigVenn', event)\" href=\"#ANALYSE_VENN\" " +
				    "coords='98,187,107,168,133,147,142,145,160,175,121,190,98,188,98,187,98,186,98,188'/>");

		venn.append("<area class='showTip BC' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getBc().getLabel() +"\" title=\""+ vennBean.getVennGraphBeanVO().getBc().getLabel() +"\"" +
				    "onmouseover='hover()'  onClick=\"writePriorities('bc', 'bigVenn', event)\" href=\"#ANALYSE_VENN\" " +
				    "coords='171,174,183,156,186,142,225,172,233,189,203,188,172,174'/>");
		
		venn.append("<area class='showTip ABC' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getAbc().getLabel() +"\" title=\""+ vennBean.getVennGraphBeanVO().getAbc().getLabel() +"\"" +
				    "onmouseover='hover()'  onClick=\"writePriorities('abc', 'bigVenn', event)\" href=\"#ANALYSE_VENN\" " +
				    "coords='149,140,166,139,181,141,170,163,163,168,152,148,148,139'/>");


		venn.append("<area shape='POLYGON' onmouseover='hover()'  onClick=\"writePriorities('', '', event)\" />");

		
		venn.append("</MAP> ");
		
		venn.append("<IMG Src='"+ imagePath +"' Alt='Image Map' Usemap='#mymap2' Ismap>'"); 
		venn.append("</div> ");
		

		vennHtml.setHTML(venn.toString());
		
		vennHtml.addClickHandler(t());
		return vennHtml;
	}
	
	public static HTML buildSmallVenn(String imagePath) {
			
		VennBeanVO vennBean = rvo.getVennBean();
		
		System.out.println("buildSmallVenn");
		HTML vennHtml = new HTML();
		StringBuffer venn = new StringBuffer();

		
		venn.append("<div>");
		venn.append("<MAP id='vennid' Name='mymap'> ");

		// A intersection
		venn.append("<area class='showTip A' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getA().getHoverLabel() +". Click 'i' for more info. \" title=\""+ vennBean.getVennGraphBeanVO().getA().getHoverLabel() +". Click 'i' for more info. \" " +
					"onmouseover='hover()'  onClick=\"writePriorities('a', 'smallVenn', event)\" href=\"#ANALYSE_VENN\" " +
					"coords='41,44,57,34,92,43,83,78,47,103,41,44'></area>");
					
		venn.append("<area class='showTip B'  shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getB().getHoverLabel() +". Click 'i' for more info. \" title=\""+ vennBean.getVennGraphBeanVO().getB().getHoverLabel() +". Click 'i' for more info. \" " +
					"onmouseover='hover()'  onClick=\"writePriorities('b', 'smallVenn', event)\" href=\"#ANALYSE_VENN\" " +
					"coords='109,41,143,33,170,59,158,107,146,110,118,83,109,41'/>");

		venn.append("<area class='showTip AB' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getAb().getLabel() +"\" title=\""+ vennBean.getVennGraphBeanVO().getAb().getLabel() +"\" " +
					"onmouseover='hover()'  onClick=\"writePriorities('ab', 'smallVenn', event)\" href=\"#ANALYSE_VENN\" " +
					"coords='89,83,90,62,100,43,111,66,112,82'/>");

		
	//  C intersection big image
		venn.append("<area class='showTip C' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getC().getHoverLabel() +". Click 'i' for more info. \" title=\""+ vennBean.getVennGraphBeanVO().getC().getHoverLabel() +". Click 'i' for more info. \" " +
				    "onmouseover=\"onhover('c', event)\"  onClick=\"writePriorities('c', 'smallVenn', event)\" href=\"#ANALYSE_VENN\" " +
				    "coords='58,119,80,120,100,108,120,120,142,120,136,154,124,164,101,171,70,159'/>");
		

		venn.append("<area class='showTip AC' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getAc().getLabel() +"\" title=\""+ vennBean.getVennGraphBeanVO().getAc().getLabel() +"\"" +
				    "onmouseover='hover()'  onClick=\"writePriorities('ac', 'smallVenn', event)\" href=\"#ANALYSE_VENN\" " +
				    "coords='59,116,70,99,86,87,98,106,82,114'/>");

		venn.append("<area class='showTip BC' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getBc().getLabel() +"\" title=\""+ vennBean.getVennGraphBeanVO().getBc().getLabel() +"\"" +
				    "onmouseover='hover()'  onClick=\"writePriorities('bc', 'smallVenn', event)\" href=\"#ANALYSE_VENN\" " +
				    "coords='142,116,130,96,113,87,102,106,118,114'/>");
		
		venn.append("<area class='showTip ABC' shape='POLYGON' alt=\""+ vennBean.getVennGraphBeanVO().getAbc().getLabel() +"\" title=\""+ vennBean.getVennGraphBeanVO().getAbc().getLabel() +"\"" +
				    "onmouseover='hover()'  onClick=\"writePriorities('abc', 'smallVenn', event)\" href=\"#ANALYSE_VENN\" " +
				    "coords='90,85,110,86,100,103'/>");


		venn.append("<area shape='POLYGON' onmouseover='hover()'  onClick=\"writePriorities('', '', event)\" coords='0,0,103,2,100,35,69,25,23,57,25,95,0,95'/>");

		
		venn.append("</MAP> ");
		
		venn.append("<IMG Src='"+ imagePath +"' Alt='Image Map' Usemap='#mymap' Ismap>'"); 
		venn.append("</div> ");
		

		vennHtml.setHTML(venn.toString());
		
		vennHtml.addClickHandler(t());
		return vennHtml;
	}
	
	public static ClickHandler t() {
		return new ClickHandler() {
			public void onClick(ClickEvent arg0) {
				//System.out.println("ClickHandler!");
				getPriority();
			}
		};
	}
	
	public static void callPriorityAgent(String intersection, String imageMapId) {
		System.out.println("callPriorityAgent intersection: " + intersection + " imageMapId: "+imageMapId);

		LinkedHashMap<String, String> codesHM = new LinkedHashMap<String, String>();
		StringBuffer htmlTitleBuffer = new StringBuffer();
		StringBuffer htmlContentBuffer = new StringBuffer();

		VennIntersectionsBean vennIntersection = rvo.getVennBean().getVennGraphBeanVO().getIntersection(intersection);

		
		if ( vennIntersection.getIsIntersected()) {
			
			String label = "";
			
			if(vennIntersection.getHoverLabel()!=null && vennIntersection.getHoverLabel().length() > 0)
				label = vennIntersection.getHoverLabel();
			else
				label = vennIntersection.getLabel();
			
			if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
				String type = "";
				
				if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
					type = "Priority ORs";
				else
				  type = "Priority OECD-DAC Sub-Sectors";
				
				htmlTitleBuffer.append("<div align='left' class='small-content' style='padding-top:5px; padding-bottom:5px; color:#579165'><b>"+vennIntersection.getValue().intValue()+" "+type+" selected from intersection '" + label + "':</b>");

			}
			else if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
				htmlTitleBuffer.append("<div align='left' class='small-content' style='padding-top:5px; padding-bottom:5px; color:#579165'><b>"+vennIntersection.getValue().intValue()+" Priority ORs selected from intersection '" + label + "':</b>");

			htmlTitleBuffer.append("<br/></div>");

			Map<String, String> codes = new TreeMap<String, String>();
			for(String code : vennIntersection.getAggregatedDacCodes()) {
				codes.put(code, rvo.getPriorities().get(code));			
			}

			TreeMap<String, String> a = ADAMController.sortByKeys(codes);
			for(String code : a.keySet()) {
				codesHM.put(code, a.get(code));
			}

			System.out.println("CODEHM: " + codesHM);
			// sort HM

			System.out.println("SIZE OF THE DONORS SELECTED: " + ADAMBoxMaker.donorsSelected.size());

			htmlContentBuffer.append("<div align='left' class='small-content'>");
			for(String code : codesHM.keySet()) {

				if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.ADAMVIEW)) {
					if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_CRS))
						htmlContentBuffer.append("<div style='padding:3px;'><b>OECD-DAC "+code + "</b> - " + codesHM.get(code)+"</div>");
					else
						htmlContentBuffer.append("<div style='padding:3px;'><b>"+code + "</b> - " + codesHM.get(code)+"</div>");
					
				}	
				else if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.FAOVIEW))
					htmlContentBuffer.append("<div style='padding:3px;'><b>" +code + "</b> - " + codesHM.get(code)+"</div>");

				// ADD donors if more than 1 (do not add in Global View) 
				//if ( ADAMBoxMaker.donorsSelected.size() == 0 || ADAMBoxMaker.donorsSelected.size() > 1 || ADAMBoxMaker.donorsSelected.containsKey("GLOBAL")) {
			    if ( ADAMBoxMaker.donorsSelected.size() > 1) {
					htmlContentBuffer.append(addDonors(code));
				}

				//htmlContentBuffer.append("<br>");
			}


			htmlContentBuffer.append("</div>");

			//Synchronize the big and small venn information display, so that any selection from the small venn is also matched in the big venn information display and vice versa
			vennDescription.put("TITLE", htmlTitleBuffer.toString());
			vennDescription.put("CONTENT", htmlContentBuffer.toString());

			if(imageMapId.contains("small")) {	
				infoPanelSmall.removeAll();

				if(!vennDescription.isEmpty()) {
					/*Button clearVennResults = new Button("Clear the Venn Results Charts");
					clearVennResults.setIconStyle("removeVennResults");
					clearVennResults.addSelectionListener(ADAMVennController.clearVennResults());
					
					infoPanelSmall.add(clearVennResults);*/
					infoPanelSmall.add(new Html(vennDescription.get("TITLE")));
					infoPanelSmall.add(new Html(vennDescription.get("CONTENT")));	

					if(infoPanelBig!=null){
						infoPanelBig.removeAll();						
						infoPanelBig.add(new Html(vennDescription.get("TITLE")));
						infoPanelBig.add(new Html(vennDescription.get("CONTENT")));	
					}
				}			
			} else if(imageMapId.contains("big")) {
				infoPanelBig.removeAll();

				if(!vennDescription.isEmpty()) {
					infoPanelBig.add(new Html(vennDescription.get("TITLE")));
					infoPanelBig.add(new Html(vennDescription.get("CONTENT")));	

					if(infoPanelSmall!=null){
						infoPanelSmall.removeAll();

						infoPanelSmall.add(new Html(vennDescription.get("TITLE")));
						infoPanelSmall.add(new Html(vennDescription.get("CONTENT")));	
					}
				}
			}

			ADAMVennController.putHighlightedVenn(imageMapId, vennPanelBig, vennPanelSmall, rvo, rvo.getVennBean(), intersection, codesHM, Integer.toString(vennIntersection.getValue().intValue()), infoPanelSmall, infoPanelBig);

			//ADAMVennController.changeViewOnPriorities(ADAMBoxMaker.donorsSelected, ADAMBoxMaker.countriesSelected, codesHM, vennIntersection.getLabel());

			//			showRecipientsTable(codesHM, rvo.getCountriesPriorities());

		}
	}
	
	// THIS IS BASED ON SOs, BECAUSE THEY STILL DIDN'T PROVE US THE ORs
	private static String addDonors(String orCode) {
		StringBuffer donors = new StringBuffer();
		
		donors.append("<br>");
		donors.append("<b>Resource Partners: </b> ");
		
		System.out.println("CODE: " + orCode);
		
		String so = String.valueOf(orCode.charAt(0));
		
		System.out.println("checking OR and SO: " + orCode + " | " + so);
		
		// SO
		Boolean added = false;
		if ( rvo.getDonorsPriorities().containsKey(so)) {
			Map<String, String> donorsList = rvo.getDonorsPriorities().get(so);
			added = true;
			
			int i = 0;
			for(String code : donorsList.keySet()) {
				donors.append(donorsList.get(code));
				if ( i < donorsList.size() -1)
					donors.append(", ");
				i++;
			}
			
		}else if ( rvo.getDonorsPriorities().containsKey(orCode)) {
			Map<String, String> donorsList = rvo.getDonorsPriorities().get(orCode);
			added = true;
			
			int i = 0;
			for(String code : donorsList.keySet()) {
				donors.append(donorsList.get(code));
				if ( i < donorsList.size() -1)
					donors.append(", ");
				i++;
			}
		}

		
//		donors.append(")");
		
		System.out.println("donorssize: " + donors.length());
		if ( added )  
			return donors.toString();
		else
			return "";
		
	}
	
	public static native void getPriority()/*-{
	
		  	var intersection = $wnd.intersection;
		  	// alert(intersection);
		  	 
		  	var imageMapId = $wnd.imageMapId;
		  	 
		  	if ( intersection != '0' ) {
		  		@org.fao.fenix.web.modules.adam.client.view.venn.ADAMVennPriorities::callPriorityAgent(Ljava/lang/String;Ljava/lang/String;)(intersection, imageMapId);
		  	}
		  	
			// setting the default value
		  	$wnd.intersection = 0;
			//	alert("$wnd.intersection: " + $wnd.intersection);
  	
	}-*/;


	
	
}
