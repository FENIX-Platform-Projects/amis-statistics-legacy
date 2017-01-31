package org.fao.fenix.web.modules.adam.server.report.donorprofile;

import java.util.Map;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorChannelOfCooperationVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.server.report.utils.ADAMReportConstants;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.report.Colors;

public class CreateADAMDonorProcessesBody {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ADAMReportBeanVO adamReportBean = null;
	
	ADAMDonorProfileVO profileBean = null;
	
	String blueText =  "#124c7d";
	String greyBorder = "#BBBBBB";
	
	String headerSize = "14pt";
	String smallHeaderSize = "12pt";
	
	
	public CreateADAMDonorProcessesBody(ADAMReportBeanVO adamReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.adamReportBean = adamReportBean;
		this.profileBean = adamReportBean.getDonorProfileVO();
	}
	
	public void build() throws SemanticException {
		buildTitleRow();
		new DesignUtils().addSpace(designHandle);
		buildSection(profileBean.getFundingBodiesTitle(), profileBean.getFundingBodies(), 0, false);
		
		
		/*Map<String, ADAMDonorChannelOfCooperationVO> channels = profileBean.getChannelsOfCooperation();
	 
	    if(channels!=null){
	    	int i= 0;
	    	for(String type: channels.keySet()){
	    		ADAMDonorChannelOfCooperationVO vo = channels.get(type);
	    		buildChannelsOfCooperationSection(vo, i);
	    		i++;
	    	}

	    }**/

	}
		
	private void buildTitleRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("profileHeaderGrid", 1, 1);
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, headerSize, "left");
		text.setProperty(StyleHandle.COLOR_PROP, Colors.red);
		text.setContent("<div style='font-weight:bold;'> Processes </div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
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

	
	
	public void buildChannelsOfCooperationSection(ADAMDonorChannelOfCooperationVO vo, int index) throws SemanticException {

		GridHandle dataGridHandle = designFactory.newGridItem("section" + index, 1, 12);

		// ADDING CONTENT				
		GridHandle contentGridHandle = designFactory.newGridItem("channelsOfCooperation" + index, 1, 12);
		DesignUtils.setBorderGrid(contentGridHandle, greyBorder);	

		//TITLE
		RowHandle row  = (RowHandle) contentGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		text.setContent("<div style='font-weight:bold'><center> <br/>"+profileBean.getChannelsOfCooperationTitle()+"</center><br/></div>");
		gridCellHandle.getContent().add(text);


		RowHandle rowContent = (RowHandle) contentGridHandle.getRows().get(1);
		CellHandle gridCellHandleContent = (CellHandle) rowContent.getCells().get(0);
		TextItemHandle textContent = designHandle.getElementFactory().newTextItem("");
		textContent = DesignUtils.createText(designHandle, textContent, ADAMReportConstants.descriptionTextSize, "justify");
		textContent.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + vo.getChannelOfCooperation() +"<br/><br/></div>");
		gridCellHandleContent.getContent().add(textContent);


		row  = (RowHandle) contentGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		text.setContent("<div style='font-weight:bold'>"+profileBean.getApplicationAndNegotiationProcessTitle()+" <br/><br/></div>");
		gridCellHandle.getContent().add(text);


		rowContent = (RowHandle) contentGridHandle.getRows().get(3);
		gridCellHandleContent = (CellHandle) rowContent.getCells().get(0);
		textContent = designHandle.getElementFactory().newTextItem("");
		textContent = DesignUtils.createText(designHandle, textContent, ADAMReportConstants.descriptionTextSize, "justify");
		textContent.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + vo.getApplicationAndNegotiationProcess()+" <br/><br/></div>");
		gridCellHandleContent.getContent().add(textContent);



		row  = (RowHandle) contentGridHandle.getRows().get(4);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		text.setContent("<div style='font-weight:bold'>"+profileBean.getAnnualFundingCycleTitle()+"<br/><br/></div>");
		gridCellHandle.getContent().add(text);


		rowContent = (RowHandle) contentGridHandle.getRows().get(5);
		gridCellHandleContent = (CellHandle) rowContent.getCells().get(0);
		textContent = designHandle.getElementFactory().newTextItem("");
		textContent = DesignUtils.createText(designHandle, textContent, ADAMReportConstants.descriptionTextSize, "justify");
		textContent.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + vo.getAnnualFundingCycle()+" <br/><br/></div>");
		gridCellHandleContent.getContent().add(textContent);



		row  = (RowHandle) contentGridHandle.getRows().get(6);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		text.setContent("<div style='font-weight:bold'>"+profileBean.getSpecialCharacteristicsTitle()+"<br/><br/></div>");
		gridCellHandle.getContent().add(text);


		rowContent = (RowHandle) contentGridHandle.getRows().get(7);
		gridCellHandleContent = (CellHandle) rowContent.getCells().get(0);
		textContent = designHandle.getElementFactory().newTextItem("");
		textContent = DesignUtils.createText(designHandle, textContent, ADAMReportConstants.descriptionTextSize, "justify");
		textContent.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + vo.getSpecialCharacteristics()+"<br/><br/> </div>");
		gridCellHandleContent.getContent().add(textContent);

		
		row  = (RowHandle) contentGridHandle.getRows().get(8);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		text.setContent("<div style='font-weight:bold'>"+profileBean.getBudgetRevisionPoliciesTitle()+" <br/><br/></div>");
		gridCellHandle.getContent().add(text);


		rowContent = (RowHandle) contentGridHandle.getRows().get(9);
		gridCellHandleContent = (CellHandle) rowContent.getCells().get(0);
		textContent = designHandle.getElementFactory().newTextItem("");
		textContent = DesignUtils.createText(designHandle, textContent, ADAMReportConstants.descriptionTextSize, "justify");
		textContent.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + vo.getBudgetRevisionPolicies()+" <br/><br/></div>");
		gridCellHandleContent.getContent().add(textContent);
		
		row  = (RowHandle) contentGridHandle.getRows().get(10);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionHeaderTextSize, "left");
		text.setHeight("0.4cm");
		text.setProperty(StyleHandle.COLOR_PROP, blueText);
		text.setContent("<div style='font-weight:bold'>"+profileBean.getAccruedInterestPoliciesTitle()+" <br/><br/></div>");
		gridCellHandle.getContent().add(text);


		rowContent = (RowHandle) contentGridHandle.getRows().get(11);
		gridCellHandleContent = (CellHandle) rowContent.getCells().get(0);
		textContent = designHandle.getElementFactory().newTextItem("");
		textContent = DesignUtils.createText(designHandle, textContent, ADAMReportConstants.descriptionTextSize, "justify");
		textContent.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> " + vo.getAccruedInterestPolicies()+" <br/><br/></div>");
		gridCellHandleContent.getContent().add(textContent);
		
		

		// ADDING GRID
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(contentGridHandle);

		designHandle.getBody().add(dataGridHandle);
	}

	
}