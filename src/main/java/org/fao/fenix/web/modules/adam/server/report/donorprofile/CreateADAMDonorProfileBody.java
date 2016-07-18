package org.fao.fenix.web.modules.adam.server.report.donorprofile;

import java.util.List;
import java.util.Map;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.web.modules.adam.client.view.profile.FormatUtils;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.server.report.utils.ADAMReportConstants;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.report.Colors;

public class CreateADAMDonorProfileBody {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ADAMReportBeanVO adamReportBean = null;
	
	ADAMDonorProfileVO profileBean = null;
	
	String blueText =  "#124c7d";
	String greyBorder = "#BBBBBB";
	
	String headerSize = "14pt";
	
	String smallHeaderSize = "9pt";
	
	
	public CreateADAMDonorProfileBody(ADAMReportBeanVO adamReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.adamReportBean = adamReportBean;
		this.profileBean = adamReportBean.getDonorProfileVO();
	}
	
	
	public void build() throws SemanticException {
		buildTitleRow();
		
		if(profileBean.getPriorityThemes()!=null) {
			buildSection(profileBean.getPriorityThemesTitle(), profileBean.getPriorityThemes(), 1);
			new DesignUtils().addSpace(designHandle);	
		}

		if(profileBean.getPriorityGeographicalAreas()!=null) {			
			buildSection(profileBean.getPriorityGeographicalAreasTitle(), profileBean.getPriorityGeographicalAreas(), 2);
			new DesignUtils().addSpace(designHandle);	
		}

		if(profileBean.getFundingBodies()!=null) {			
			buildSection(profileBean.getFundingBodiesTitle(), profileBean.getFundingBodies(), 3);
			new DesignUtils().addSpace(designHandle);	
		}

		if(profileBean.getChannelsOfCooperation()!=null) {			
			buildSection(profileBean.getChannelsOfCooperationTitle(), profileBean.getChannelsOfCooperation(), 4);
			new DesignUtils().addSpace(designHandle);	
		}

		if(profileBean.getFavouredFundingArrangements()!=null) {			
			buildSection(profileBean.getFavouredFundingArrangementsTitle(), profileBean.getFavouredFundingArrangements(), 5);
			new DesignUtils().addSpace(designHandle);	
		}

		if(profileBean.getAnnualFundingCycle()!=null) {			
			buildSection(profileBean.getAnnualFundingCycleTitle(), profileBean.getAnnualFundingCycle(), 6);
			new DesignUtils().addSpace(designHandle);	
		}

		if(profileBean.getApplicationAndNegotiationProcess()!=null) {			
			buildSection(profileBean.getApplicationAndNegotiationProcessTitle(), profileBean.getApplicationAndNegotiationProcess(), 7);
			new DesignUtils().addSpace(designHandle);	
		}

		if(profileBean.getBudgetRevisionPolicies()!=null) {			
			buildSection(profileBean.getBudgetRevisionPoliciesTitle(), profileBean.getBudgetRevisionPolicies(), 8);
			new DesignUtils().addSpace(designHandle);	
		}

		if(profileBean.getAccruedInterestPolicies()!=null) {			
			buildSection(profileBean.getAccruedInterestPoliciesTitle(), profileBean.getAccruedInterestPolicies(), 9);
			new DesignUtils().addSpace(designHandle);	
		}

		if(profileBean.getSpecialCharacteristics()!=null) {			
			buildSection(profileBean.getSpecialCharacteristicsTitle(), profileBean.getSpecialCharacteristics(), 10);
			new DesignUtils().addSpace(designHandle);	
		}

		if(!profileBean.getExternalLinks().isEmpty()) {			
			buildWebLinksSection(profileBean.getExternalLinksTitle(),profileBean.getExternalLinks(),11);
		}
		
		//Set Page break
		/**GridHandle pageBreakGridHandle = designFactory.newGridItem("pageBreakGrid", 1, 1);
		RowHandle row = (RowHandle) pageBreakGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text.setContent("");
		gridCellHandle.getContent().add(text);	
		designHandle.getBody().add(pageBreakGridHandle);
		
		//ADD PAGE BREAK
		pageBreakGridHandle.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");	**/

	}
	
	 
	public void buildOriginal() throws SemanticException {
		buildFirstRow();
		buildSecondRow();
		buildTitleRow();
		new DesignUtils().addSpace(designHandle);		
		buildSplitSection(profileBean.getPriorityThemesTitle(), profileBean.getPriorityThemes(), 0, true);
		buildSection(profileBean.getPriorityGeographicalAreasTitle(), profileBean.getPriorityGeographicalAreas(), 1, false);
		
		
		/**Map<String, String> assistances = profileBean.getFavouredFundingArrangements();
		    StringBuilder sb = new StringBuilder();

		    if(assistances!=null){
		    	int i= 0;
		    	for(String type: assistances.keySet()){
		    		sb.append(type);  
		    		if (i < assistances.size() - 1)
		    			sb.append(", ");

		    		i++;
		    	}

		    }
		buildSection(profileBean.getFavouredFundingArrangementsTitle(),sb.toString(), 2, false);
		if(profileBean.getExternalFundingForDeliveryPeriods()!=null)
			buildSection(profileBean.getExternalFundingForDeliveryPeriodsTitle(),profileBean.getExternalFundingForDeliveryPeriods(), 3, ":");
			
		buildSection(profileBean.getRegionalRecipientCountriesTitle(),profileBean.getRegionalRecipientCountries(),4, "-");
	**/
		new DesignUtils().addSpace(designHandle);	
		buildWebLinksSection(profileBean.getExternalLinksTitle(),profileBean.getExternalLinks(),5);
		
		//Set Page break
		GridHandle pageBreakGridHandle = designFactory.newGridItem("pageBreakGrid", 1, 1);
		RowHandle row = (RowHandle) pageBreakGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text.setContent("");
		gridCellHandle.getContent().add(text);	
		designHandle.getBody().add(pageBreakGridHandle);
		
		//ADD PAGE BREAK
		pageBreakGridHandle.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");	

	}
	
	
	
	
	private void buildFirstRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("resourceMobilizationOfficerGrid", 1, 1);
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, smallHeaderSize, "right");
		//text.setProperty(StyleHandle.COLOR_PROP, blue);
		text.setContent("<div><font color='"+blueText+"'><b>"+profileBean.getResourceMobilizationOfficerTitle()+": </b></font>"+profileBean.getResponsibleMobilizationOfficer()+"</div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	

		designHandle.getBody().add(headerGridHandle);
	}
	
	
	private void buildSecondRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("resourceMobilizationOfficerEmail", 1, 1);
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, smallHeaderSize, "right");
		//text.setProperty(StyleHandle.COLOR_PROP, blue);
		text.setContent("<div><font color='"+blueText+"'><b>"+profileBean.getEmailTitle()+": </b></font>"+profileBean.getResourceMobilizationOfficerEmail()+"</div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	

		designHandle.getBody().add(headerGridHandle);
	}
	
	

	
	private void buildTitleRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("profileHeaderGrid", 1, 1);
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.headerSize, "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, ADAMReportConstants.blue);
		text.setProperty(StyleHandle.COLOR_PROP, ADAMReportConstants.yellow);
		text.setContent("<div style='font-weight:bold;'> Profile ("+profileBean.getProfileReferenceDate()+")</div>");
		DesignUtils.setBorderGrid(gridCellHandle, ADAMReportConstants.blue);
		
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, ADAMReportConstants.blue);
		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	

		designHandle.getBody().add(headerGridHandle);
	}

	
	private void buildSection(String sectionTitle, String sectionContent, int index) throws SemanticException {
	
		GridHandle headerGridHandle = designFactory.newGridItem("section" + index, 1, 2);
		
		//HEADER
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.subHeadingSize, "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, ADAMReportConstants.lightBlue);
		text.setProperty(StyleHandle.COLOR_PROP, ADAMReportConstants.blue);
		text.setContent("<div style='font-weight:bold;'>"+sectionTitle+"</div>");
		DesignUtils.setBorderGrid(gridCellHandle, ADAMReportConstants.lightBlue);
		
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, ADAMReportConstants.lightBlue);
		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	

		//CONTENT
		row = (RowHandle) headerGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.contentSize, "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, ADAMReportConstants.white);
		text.setProperty(StyleHandle.COLOR_PROP, ADAMReportConstants.black);
		text.setContent("<div> " + sectionContent +"</div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);


		designHandle.getBody().add(headerGridHandle);

	}
	
	
	public void buildSection(String sectionTitle, String sectionContent, int index, boolean isCentered) throws SemanticException {

		GridHandle dataGridHandle = designFactory.newGridItem("section" + index, 1, 1);

		GridHandle contentGridHandle = designFactory.newGridItem("sectionContent" + index, 1, 2);
		DesignUtils.setBorderGrid(contentGridHandle, greyBorder);		

		//TITLE
		RowHandle row  = (RowHandle) contentGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		if(isCentered)
			text.setContent("<div style='font-weight:bold; text-align:center'> " + sectionTitle +" <br/><br/></div>");
		else 
			text.setContent("<div style='font-weight:bold'> " + sectionTitle +" <br/><br/></div>");	

		gridCellHandle.getContent().add(text);

		//CONTENT
		RowHandle row1 = (RowHandle) contentGridHandle.getRows().get(1);
		CellHandle gridCellHandle1 = (CellHandle) row1.getCells().get(0);
		TextItemHandle text1 = designHandle.getElementFactory().newTextItem("");
		text1 = DesignUtils.createText(designHandle, text1, ADAMReportConstants.descriptionTextSize, "justify");
		text1.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + sectionContent +"<br/><br/> </div>");
		gridCellHandle1.getContent().add(text1);

		// ADDING GRID
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(contentGridHandle);


		designHandle.getBody().add(dataGridHandle);
	}

	public void buildSplitSection(String sectionTitle, String sectionContent, int index, boolean isCentered) throws SemanticException {

		GridHandle dataGridHandle = designFactory.newGridItem("section" + index, 1, 1);

		GridHandle contentGridHandle = designFactory.newGridItem("sectionContent" + index, 3, 2);
		DesignUtils.setBorderGrid(contentGridHandle,greyBorder);		

		//TITLE
		RowHandle row  = (RowHandle) contentGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		if(isCentered)
			text.setContent("<div style='font-weight:bold; text-align:center'> " + sectionTitle +" <br/><br/></div>");
		else 
			text.setContent("<div style='font-weight:bold'> " + sectionTitle +" <br/><br/></div>");	

		gridCellHandle.getContent().add(text);



		//empty spacer cell
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = designHandle.getElementFactory().newTextItem("");
		gridCellHandle.getContent().add(text);	


		//second title cell
		gridCellHandle = (CellHandle) row.getCells().get(2);

		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		   	    
		//if(isCentered)
			//text.setContent("<div style='font-weight:bold; font-size:10pt; text-align:center'>"+  profileBean.getKeySOsTitle() +"* <br/>("+profileBean.getKeySOsReferenceDate()+")" +"<br/><br/></div>");
		//else 
		//	text.setContent("<div style='font-weight:bold'> " + sectionTitle +" <br/><br/></div>");	

		gridCellHandle.getContent().add(text);



		//CONTENT
		RowHandle row1 = (RowHandle) contentGridHandle.getRows().get(0);
		CellHandle gridCellHandle1 = (CellHandle) row1.getCells().get(0);
		TextItemHandle text1 = designHandle.getElementFactory().newTextItem("");
		text1 = DesignUtils.createText(designHandle, text1, ADAMReportConstants.descriptionTextSize, "justify");
		text1.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + sectionContent +"<br/><br/> </div>");
		gridCellHandle1.getContent().add(text1);

		//empty spacer cell
		//gridCellHandle1 = (CellHandle) row1.getCells().get(1);
		//text1 = designHandle.getElementFactory().newTextItem("");
		//gridCellHandle1.getContent().add(text);	

		//Second CONTENT cell
		gridCellHandle1 = (CellHandle) row1.getCells().get(2);
		gridCellHandle1.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, Colors.red);

		text1 = designHandle.getElementFactory().newTextItem("");
		text1 = DesignUtils.createText(designHandle, text1, ADAMReportConstants.descriptionTextSize, "justify");
		text1.setProperty(StyleHandle.COLOR_PROP, blueText);

		/**if(profileBean.getKeySOs()!=null){

			StringBuilder sb = new StringBuilder();

			if(profileBean.getKeySOs()!=null){
				for(String so: profileBean.getKeySOs().keySet()){
					sb.append(FormatUtils.getFormattedTitle(so, false, blueText)+ " - "+ profileBean.getKeySOs().get(so)+ "<br/>");  
				}

			}

			sb.append(FormatUtils.getFormattedTitle("(* " + FormatUtils.getFormattedTitle(profileBean.getKeySOsDisclaimer()) +")")+ "<br/>");  
			
			text1.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + sb.toString() +"<br/><br/> </div>");
			gridCellHandle1.getContent().add(text1);		
		}**/

		ColumnHandle col = (ColumnHandle) contentGridHandle.getColumns().get(0);
		col.setProperty("width", "56%");
		col = (ColumnHandle) contentGridHandle.getColumns().get(1);
		col.setProperty("width", "4%");	
		col = (ColumnHandle) contentGridHandle.getColumns().get(2);
		col.setProperty("width", "40%");	


		// ADDING GRID
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(contentGridHandle);


		designHandle.getBody().add(dataGridHandle);
	}
	
	public void buildSection(String sectionTitle, Map<String, String> sectionContent, int index, String symbol) throws SemanticException {

		GridHandle dataGridHandle = designFactory.newGridItem("section" + index, 1, 1);

		// ADDING CONTENT				
		
		GridHandle contentGridHandle = designFactory.newGridItem("sectionContent" + index, 1, sectionContent.size()+1);
		DesignUtils.setBorderGrid(contentGridHandle, greyBorder);	

		//TITLE
		RowHandle row  = (RowHandle) contentGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		text.setContent("<div style='font-weight:bold'> " + sectionTitle +" <br/><br/></div>");
		gridCellHandle.getContent().add(text);


		if (symbol==null)
			symbol = "-";

		int i = 1;
		for(String code: sectionContent.keySet()){

			String content0 = FormatUtils.getFormattedTitle(code, false, blueText)+ " " +symbol+" "+ sectionContent.get(code);

			RowHandle rowContent = (RowHandle) contentGridHandle.getRows().get(i);
			CellHandle gridCellHandleContent = (CellHandle) rowContent.getCells().get(0);
			TextItemHandle textContent = designHandle.getElementFactory().newTextItem("");
			textContent = DesignUtils.createText(designHandle, textContent, ADAMReportConstants.descriptionTextSize, "justify");
			textContent.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + content0 +" </div>");
			gridCellHandleContent.getContent().add(textContent);

			System.out.println("Row ID = "+i + " "+sectionContent.keySet().size());

			i++;
		}

		// ADDING GRID
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(contentGridHandle);

		designHandle.getBody().add(dataGridHandle);
	}

	
	private void buildWebLinksSection(String sectionTitle, List<String> sectionContent, int index) throws SemanticException {
		
		GridHandle headerGridHandle = designFactory.newGridItem("section" + index, 1, 2);
		
		//HEADER
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.subHeadingSize, "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, ADAMReportConstants.lightBlue);
		text.setProperty(StyleHandle.COLOR_PROP, ADAMReportConstants.blue);
		text.setContent("<div style='font-weight:bold;'>"+sectionTitle+"</div>");
		DesignUtils.setBorderGrid(gridCellHandle, ADAMReportConstants.lightBlue);
		
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, ADAMReportConstants.lightBlue);
		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	

		//CONTENT
		row = (RowHandle) headerGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.contentSize, "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, ADAMReportConstants.white);
		text.setProperty(StyleHandle.COLOR_PROP, ADAMReportConstants.black);
		
		StringBuilder links = new StringBuilder();
		for(String code: sectionContent){
			links.append(code+"<br/>");
		}
		
		
		text.setContent("<div> " + links.toString() +"</div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);


		designHandle.getBody().add(headerGridHandle);

	}
	
	public void buildWebLinksSectionORIGINAL(String sectionTitle, List<String> sectionContent, int index) throws SemanticException {

		GridHandle dataGridHandle = designFactory.newGridItem("section" + index, 1, 1);

		// ADDING CONTENT				
		GridHandle contentGridHandle = designFactory.newGridItem("sectionContent" + index, 1, sectionContent.size()+1);
		DesignUtils.setBorderGrid(contentGridHandle, blueText);	

		//TITLE
		RowHandle row  = (RowHandle) contentGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		text.setContent("<div style='font-weight:bold'><center> " + sectionTitle +" </center><br/></div>");
		gridCellHandle.getContent().add(text);

		int i = 1;
		for(String code: sectionContent){

			String content0 = FormatUtils.getFormattedTitle(code, false, blueText);

			RowHandle rowContent = (RowHandle) contentGridHandle.getRows().get(i);
			CellHandle gridCellHandleContent = (CellHandle) rowContent.getCells().get(0);
			TextItemHandle textContent = designHandle.getElementFactory().newTextItem("");
			textContent = DesignUtils.createText(designHandle, textContent, ADAMReportConstants.descriptionTextSize, "justify");
			textContent.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'>"+ content0 +"<br/></div>");
			gridCellHandleContent.getContent().add(textContent);

			i++;
		}

		// ADDING GRID
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(contentGridHandle);

		designHandle.getBody().add(dataGridHandle);
	}
	
}