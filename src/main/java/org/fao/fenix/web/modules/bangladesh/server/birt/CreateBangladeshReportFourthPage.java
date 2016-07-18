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
import org.fao.fenix.web.modules.bangladesh.common.vo.BangladeshChartsBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.GraphReportEngine;

public class CreateBangladeshReportFourthPage {

	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	BangladeshBean bangladeshBean = null;
	
	BangladeshChartsBean charts = null;
	
	BangladeshReportStyleBean  styleBean = null;
	
	String priceChangeTable = null;
	
	
	
	private static final Logger LOGGER = Logger.getLogger(CreateBangladeshReportFourthPage.class);
	
	public CreateBangladeshReportFourthPage(ReportDesignHandle designHandle, ElementFactory designFactory, BangladeshBean bangladeshBean, String priceChangeTable, BangladeshChartsBean charts, BangladeshReportStyleBean styleBean) {
		this.designHandle = designHandle;
		this.designFactory = designFactory;
		this.bangladeshBean = bangladeshBean;
		this.priceChangeTable = priceChangeTable;
		this.charts = charts;
		this.styleBean = styleBean;
		
		try {
			createPage();
		} catch (SemanticException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private void createPage() throws SemanticException {
		createHeader();
		DesignUtils.addSpace(designHandle);
		amanProcurement();
		DesignUtils.addSpace(designHandle);
		publicDistribution();
	}
	
	

	
	
	/***************
	 * 
	 * create the Aman Procurement
	 * 
	 */
	
	private void amanProcurement() throws SemanticException {
		
		/** HEADER **/
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "12pt", "left");
		text.setContent("<div> 4. "+ bangladeshBean.getProcurementHeaderTitle() +" Procurement </div>");
		
		// add export options
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, styleBean.getColorTitle());
		text.setProperty(StyleHandle.COLOR_PROP, styleBean.getColorTitleText());
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		DesignUtils.setBorderGrid(text, "#000000");
		designHandle.getBody().add(text);	
		DesignUtils.addSpace(designHandle);
		buildAmanProcurementContents();		
	}
	
	private void buildAmanProcurementContents() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("buildAmanProcurementContents", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** CHARTS **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildAmanCharts());
		
		/** TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildAmanText());
		
		
		designHandle.getBody().add(dataGridHandle);
	}
	
	private GridHandle buildAmanCharts() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("buildAmanCharts", 3, 4);
		/** total **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, styleBean.getImageTextSize(), "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div> FIGURE 7. Total "+ bangladeshBean.getProcurementHeaderTitle().toLowerCase() +" procurement </div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		GraphReportEngine gre = new GraphReportEngine(charts.getAmanProcurementTotal(), designHandle);
		gridCellHandle.getContent().add(gre.getChart());
		DesignUtils.setBorderGrid(gridCellHandle, "#000000");
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, styleBean.getLegendSize(), "left");
		text.setContent("<div font-style='italic'> Source: MIS DG Food </div>");
		gridCellHandle.getContent().add(text);
		
		
		/** progress **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, styleBean.getImageTextSize(), "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div> FIGURE 8. "+ bangladeshBean.getProcurementHeaderTitle() +" procurement progress and contracts </div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		GraphReportEngine gre2 = new GraphReportEngine(charts.getAmanProcurementProgress(), designHandle);
		gridCellHandle.getContent().add(gre2.getChart());
		DesignUtils.setBorderGrid(gridCellHandle, "#000000");
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, styleBean.getLegendSize(), "left");
		text.setContent("<div font-style='italic'> Source: MIS DG Food </div>");
		gridCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "48%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "4%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "48%");

		
		return dataGridHandle;
	} 

	private GridHandle buildAmanText() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("buildAmanText", 1, 2);
		DesignUtils.setBorderGrid(dataGridHandle, styleBean.getTableColor());
		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, styleBean.getBackgroundText());
		
		/** add title **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, styleBean.getImageTextSize(), "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> PROCUREMENT </div>");
		gridCellHandle.getContent().add(text);
		
		/** add text **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, styleBean.getTextSizeBigger(), "justify");
		text.setContent("<div> " + bangladeshBean.getProcurementText() +" </div>");
		gridCellHandle.getContent().add(text);
		
		
		
		return dataGridHandle;
	}
	
	
	
	/***************
	 * 
	 * create the Public distribution 
	 *  
	 */
	
	private void publicDistribution() throws SemanticException {
		
		/** HEADER **/
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, styleBean.getTitleTextSize(), "left");
		text.setContent("<div style='font-weight: bold;'> 5. Public Distribution </div>");
		
		// add export options
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, styleBean.getColorTitle());
		text.setProperty(StyleHandle.COLOR_PROP, styleBean.getColorTitleText());
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		DesignUtils.setBorderGrid(text, "#000000");
		designHandle.getBody().add(text);	
		DesignUtils.addSpace(designHandle);
		buildPublicDistributionContents();		
	}
	
	private void buildPublicDistributionContents() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("buildPublicDistributionContents", 3, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** CHARTS **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildPublicDistributionChart());
		
		/** TEXT **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		gridCellHandle.getContent().add(buildPublicDistributionText());
		
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "48%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "4%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "48%");
		
		designHandle.getBody().add(dataGridHandle);
	}
	
	private GridHandle buildPublicDistributionChart() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("buildPublicDistributionChart", 1, 4);
		
		/** add text **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, styleBean.getImageTextSize(), "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div> Figure 9. Rice and wheat distributed through PFDS so far and yearly target (as of " + bangladeshBean.getPublicDistributionDate() +"), in thousand MT </div>");
		gridCellHandle.getContent().add(text);
		
		/** add chart **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		GraphReportEngine gre = new GraphReportEngine(charts.getPublicDistribution(), designHandle);
		gridCellHandle.getContent().add(gre.getChart());
		DesignUtils.setBorderGrid(gridCellHandle, "#000000");
		
		/** add source **/
		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, styleBean.getLegendSize(), "left");
		text.setContent("<div style='font-style:italic;'> Source: DG Food and FPMU </div>");
		gridCellHandle.getContent().add(text);
		
		return dataGridHandle;
	}
	
	
	private GridHandle buildPublicDistributionText() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("buildAmanText", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		DesignUtils.setBorderGrid(dataGridHandle, "#000000");
		dataGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, styleBean.getBackgroundText());
		
		/** add title **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, styleBean.getImageTextSize(), "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> DISTRIBUTION </div>");
		gridCellHandle.getContent().add(text);
		
		/** add title **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, styleBean.getTextSizeBigger(), "justify");
		text.setContent("<div>" + bangladeshBean.getPublicDistributionText() +" </div>");
		gridCellHandle.getContent().add(text);
		
		
		
		return dataGridHandle;
	}
	

	
	private void createHeader() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("otherHeaderPage", 3, 1);	
		
		/** create issue **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
	
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("secondIssue");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getImageTextSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
		text.setContent("<div style='font-style:italic;'> Fortnightly Foodgrain Outlook, " + bangladeshBean.getIssue() + "</div>");	
		gridCellHandle.getContent().add(text);	
		
		/** create date **/ 
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		
		text = designHandle.getElementFactory().newTextItem("secondDate");
		
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getImageTextSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
		text.setContent("<div>" + bangladeshBean.getDate() + "</div>");
		
		gridCellHandle.getContent().add(text);
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "35%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "10%");	
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "20%");
		designHandle.getBody().add(dataGridHandle);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}