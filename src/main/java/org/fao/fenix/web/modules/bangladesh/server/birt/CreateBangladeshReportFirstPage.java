package org.fao.fenix.web.modules.bangladesh.server.birt;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.core.domain.bangladesh.BangladeshBean;
import org.fao.fenix.core.domain.bangladesh.BangladeshReportStyleBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;

public class CreateBangladeshReportFirstPage {

	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	BangladeshBean bangladeshBean = null;
	
	BangladeshReportStyleBean styleBean = null;
	

	private static final Logger LOGGER = Logger.getLogger(CreateBangladeshReportFirstPage.class);
	
	public CreateBangladeshReportFirstPage(ReportDesignHandle designHandle, ElementFactory designFactory, BangladeshBean bangladeshBean, BangladeshReportStyleBean styleBean) {
		this.bangladeshBean = bangladeshBean;
		this.designHandle = designHandle;
		this.designFactory = designFactory;
		this.styleBean = styleBean;
		
		try {
			createFirstPage();
		} catch (SemanticException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void createFirstPage() throws SemanticException {
		createFirstPageHeader();
		DesignUtils.addSpace(designHandle);
		createFirstPageIssueDate();
		DesignUtils.addSpace(designHandle);
		createFirstPageHighlights();
		DesignUtils.addSpace(designHandle);
		createFirstPageFooter();
	}
	
	
	
	
	
	private void createFirstPageIssueDate() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("issueDateGrid", 3, 1);	
		
		/** create issue **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("Issue");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
		text.setContent("<div style='font-weight:bold'>" + bangladeshBean.getIssue() + "</div>");
		
		gridCellHandle.getContent().add(text);
		
		/** create date **/ 
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = designHandle.getElementFactory().newTextItem("Date");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
		text.setContent("<div style='font-weight:bold;'>" + bangladeshBean.getDate() + "</div>");
		
		gridCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "35%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "10%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "20%");
		designHandle.getBody().add(dataGridHandle);
	
	}
	
	private void createFirstPageHighlights() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("highlightsGrid", 1, 2);	
		
		/** create issue **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		DesignUtils.setTopBorderGrid(gridCellHandle, "#000000");
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("highlightHText");
		text.setWidth("17.75cm");
		text.setHeight("1cm");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, "14pt");
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
		text.setContent("<div style='font-weight:bold'> HIGHLIGHTS </div>");		
		gridCellHandle.getContent().add(text);
			
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		DesignUtils.setBottomBorderGrid(gridCellHandle, "#000000");		
		text = designHandle.getElementFactory().newTextItem("highlightText");
		text.setWidth("17.75cm");
		text.setHeight("17cm");
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "justify");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setContent("<div style='font-size: 12pt; '> "+ bangladeshBean.getHighlightsText() +"</div>");		
		gridCellHandle.getContent().add(text);
		
		designHandle.getBody().add(dataGridHandle);
	}
	
	private void createFirstPageFooter() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("firstFooter", 1, 3);	
		
		/** create issue **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("unitDescription");
		text.setWidth("17.75cm");
		text.setHeight("1cm");
		
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
		text.setContent("<div style='font-weight:bold; color:#0E5821;'> Food Planning and Monitoring Unit (FPMU), Ministry of Food and Disaster Management " +
				"<br> " +
				"in collaboration with FAO National Food Policy Capacity Strengthening Programme (NFPCSP) </div>");	
		gridCellHandle.getContent().add(text);	
		
		
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
	
		text = designHandle.getElementFactory().newTextItem("link");
		text.setWidth("17.75cm");
		text.setHeight("0.5cm");		
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
		text.setProperty(StyleHandle.FONT_SIZE_PROP, "9pt");
		text.setContent("<div style='font-style:italic; color:#1538B8;'> http://www.mofdm.gov.bd/ & http://www.nfpcsp.org/</div>");
		gridCellHandle.getContent().add(text);
		
		dataGridHandle.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");	
		designHandle.getBody().add(dataGridHandle);
	}
	
	private void createFirstPageHeader() throws SemanticException {	
		GridHandle headerGridHandle = designFactory.newGridItem("headerGrid", 1, 1);
		headerGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		
//		gridCellHandle.setProperty(StyleHandle.PADDING_BOTTOM_PROP, "20pt");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "50pt");
		
		DesignUtils.setBorderGrid(gridCellHandle, "#000000");
		TextItemHandle header = designHandle.getElementFactory().newTextItem("Header");
		header.setWidth("17.75cm");
		header.setHeight("2.75cm");
		header.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//		header.setProperty(StyleHandle.FONT_SIZE_PROP, "25pt");
		header.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		header.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#0E5821");
		header.setContent("<div style='font-size: 25pt; font-style:italic; font-weight: bold; color: white; ' align='center'>Fortnightly Foodgrain Outlook</div>");
		gridCellHandle.getContent().add(header);
		
		designHandle.getBody().add(headerGridHandle);
	}
	
	

	
}