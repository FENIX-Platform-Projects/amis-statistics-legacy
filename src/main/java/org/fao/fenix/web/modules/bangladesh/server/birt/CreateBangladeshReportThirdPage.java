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

public class CreateBangladeshReportThirdPage {

	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	BangladeshBean bangladeshBean = null;
	
	BangladeshChartsBean charts = null;
	
	String priceChangeTable = null;
	
	BangladeshReportStyleBean styleBean = null;

	private static final Logger LOGGER = Logger.getLogger(CreateBangladeshReportThirdPage.class);
	
	public CreateBangladeshReportThirdPage(ReportDesignHandle designHandle, ElementFactory designFactory, BangladeshBean bangladeshBean, String priceChangeTable, BangladeshChartsBean charts, BangladeshReportStyleBean styleBean) {
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
		createInternationalMarket();
		DesignUtils.addSpace(designHandle);
		createImports();
	}
	
	
	/***************
	 * 
	 * create the box 1 (International production and market prospects 2009/2010) of the third Page
	 * 
	 */

	private void createInternationalMarket() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("internationalProductionAndMarketGrid", 1, 1);
		DesignUtils.setBorderGrid(dataGridHandle, "#000000");
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildInternationalMarketContentGrid());
		designHandle.getBody().add(dataGridHandle);
	}
	
	public GridHandle buildInternationalMarketContentGrid() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("boxIPM1", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");	
		
		/** build BOX 1. title **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setProperty(StyleHandle.FONT_SIZE_PROP, styleBean.getImageTextSize());
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		text.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> BOX 1. International production and market prospects 2009/2010 </div>");
		gridCellHandle.getContent().add(text);
		
		/** build BOX 1. Content **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildInternationalMarketContent());

		return dataGridHandle;
	}
	
	private GridHandle buildInternationalMarketContent() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("boxIPM2", 2, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");	

		/** build the first column of the international productions **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildInternationalMarketFirstColumn());
		
		/** build the second column of the international productions **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		gridCellHandle.getContent().add(buildInternationalMarketSecondColumn());
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "50%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "50%");	
		
		return dataGridHandle;
	}
	
	private GridHandle buildInternationalMarketFirstColumn() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("boxIPM_C1", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");	
		
		/** build the rice and wheat production **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildRiceWheatProduction());
		
		/** build wheat text **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildWheatText());
		
		return dataGridHandle;
	}
	
	private GridHandle buildRiceWheatProduction() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("boxIPM_RWP", 1, 3);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		DesignUtils.setBorderGrid(dataGridHandle, styleBean.getTableColor());
		
		/** title **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "8pt", "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> Rice and wheat production, stocks and prices </div>");
		gridCellHandle.getContent().add(text);
		
		/** table **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);		
		gridCellHandle.getContent().add(new CreateRiceWheatProductionTable(designHandle, designFactory).buildTable());
		
		/** text **/
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, styleBean.getTextSizeLittle(), "justify");
		text.setContent("<div> " + bangladeshBean.getProductionText() + " </div>");
		gridCellHandle.getContent().add(text);
		
		return dataGridHandle;
	}
	
	private GridHandle buildWheatText() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("boxIPM_wheatText", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "8pt", "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> WHEAT </div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, styleBean.getTextSizeLittle(), "justify");
		text.setContent("<div> " + bangladeshBean.getWheatMarketText() + " </div>");
		gridCellHandle.getContent().add(text);
		
		return dataGridHandle;
	}
	
	private GridHandle buildInternationalMarketSecondColumn() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("boxIPM_C2", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");	
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildRiceText());
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildWheatTables());
		
		return dataGridHandle;
	}
	
	private GridHandle buildRiceText() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("boxIPM_riceText", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");		
		DesignUtils.setBorderGrid(dataGridHandle, styleBean.getTableColor());
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "8pt", "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div style='font-wight:bold;'> RICE </div>");
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, styleBean.getTextSizeLittle(), "justify");
		text.setContent("<div style='font-wight:bold;'> " + bangladeshBean.getRiceMarketText() + " </div>");
		gridCellHandle.getContent().add(text);
		
		return dataGridHandle;
	}
	
	private GridHandle buildWheatTables() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("boxIPM_wheatTables", 1, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");	
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(new CreateWheatMarketTable(designHandle, designFactory, bangladeshBean).buildTable());
		return dataGridHandle;
	}
	
	
	/***************
	 * 
	 * create the IMPORTS
	 * 
	 */
	
	private void createImports() throws SemanticException {
		
		/** HEADER **/
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, styleBean.getTitleTextSize(), "left");
		text.setContent("<div> 3. Imports </div>");
		
		// add export options
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP,  styleBean.getColorTitle());
		text.setProperty(StyleHandle.COLOR_PROP, styleBean.getColorTitleText());
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SANS_SERIF);
		DesignUtils.setBorderGrid(text, "#000000");
		designHandle.getBody().add(text);	
		DesignUtils.addSpace(designHandle);
		buildImportsContents();		
	}
	
	private void buildImportsContents() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("importsGrid", 1, 2);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** CHARTS **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildCharts());
		
		/** LC **/
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildLCSituation());
		
		
		designHandle.getBody().add(dataGridHandle);
	}
	
	private GridHandle buildCharts() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("buildCharts", 3, 4);
		
		/** RICE IMPORTS **/
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");	
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, styleBean.getImageTextSize(), "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div> Figure 5. Rice Imports </div>");
		gridCellHandle.getContent().add(text);
		
	
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		GraphReportEngine gre = new GraphReportEngine(charts.getRiceImports(), designHandle);
		gridCellHandle.getContent().add(gre.getChart());
		DesignUtils.setTopBorderGrid(gridCellHandle, styleBean.getTableColor());

		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		CreateImportsTable importsTable = new CreateImportsTable("rice", designHandle, designFactory, bangladeshBean.getImportsBean().getChartValuesRice());
		gridCellHandle.getContent().add(importsTable.buildTable());
		DesignUtils.setBottomBorderGrid(gridCellHandle, styleBean.getTableColor());

		row = (RowHandle) dataGridHandle.getRows().get(3);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, styleBean.getLegendSize(), "left");
		text.setContent("<div font-style='italic'> Source: MIS DG Food </div>");
		gridCellHandle.getContent().add(text);
		
		
		
		/** WHEAT IMPORTS **/	
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		text = DesignUtils.createText(designHandle, text, styleBean.getImageTextSize(), "left");
		text.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		text.setContent("<div> Figure 6. Wheat Imports </div>");
		gridCellHandle.getContent().add(text);

		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		GraphReportEngine gre1 = new GraphReportEngine(charts.getWheatImports(), designHandle);
		gridCellHandle.getContent().add(gre1.getChart());
		DesignUtils.setTopBorderGrid(gridCellHandle, styleBean.getTableColor());
		
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		importsTable = new CreateImportsTable("wheat", designHandle, designFactory, bangladeshBean.getImportsBean().getChartValuesWheat());
		gridCellHandle.getContent().add(importsTable.buildTable());
		DesignUtils.setBottomBorderGrid(gridCellHandle, styleBean.getTableColor());

		row = (RowHandle) dataGridHandle.getRows().get(3);
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

	private GridHandle buildLCSituation() throws SemanticException {	
		GridHandle dataGridHandle = designFactory.newGridItem("buildLCSituation", 2, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** add table **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(new CreateLCTable(designHandle, designFactory, bangladeshBean.getImportsBean()).buildTable());
		
		/** add text **/
		GridHandle textGridHandle = designFactory.newGridItem("buildLCSituationText", 1, 2);
		row = (RowHandle) textGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, styleBean.getTextSizeLittle(), "justify");
		text.setContent("<div>" + bangladeshBean.getImportsText() + " </div>");
		text.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");	
		textGridHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP,  styleBean.getBackgroundText());
		gridCellHandle.getContent().add(text);
		DesignUtils.setBorderGrid(textGridHandle, "000000");
		
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		gridCellHandle.getContent().add(textGridHandle);
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "45%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "55%");
		
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