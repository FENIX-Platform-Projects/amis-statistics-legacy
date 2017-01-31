package org.fao.fenix.web.modules.venn.server.report.utils;

import java.io.File;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ImageHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.report.Colors;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;


public class CreateVennHeaderReport {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	VennReportBeanVO vennReportBean = null;
	
	public CreateVennHeaderReport(VennReportBeanVO vennReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.vennReportBean = vennReportBean;
	}
	
	public void build() throws SemanticException {
		buildHeader();
		buildHeaderSecondRow();
	}
	

	
	
	private void buildHeader() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderGrid", 9, 1);
		DesignUtils.setBottomBorderGrid(headerGridHandle, Colors.black);
//		headerGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** BLUE **/
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		row.setProperty(RowHandle.HEIGHT_PROP, "1.2cm");	
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);
		
		/** FAO LOGO **/
		
		gridCellHandle = (CellHandle) row.getCells().get(1);
		ImageHandle FAOLogoImage = designHandle.getElementFactory().newImage("FaoLogo");
		FAOLogoImage.setWidth("0.9cm");
		FAOLogoImage.setHeight("0.9cm");
//		FAOLogoImage.setWidth("1.1cm");
//		FAOLogoImage.setHeight("1.1cm");
		FAOLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "fao.png\"");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);
		DesignUtils.setBorderGrid(gridCellHandle, Colors.red);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.15cm");
		gridCellHandle.getContent().add(FAOLogoImage);
		
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(2);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);	
		
		/** EC LOGO **/
		gridCellHandle = (CellHandle) row.getCells().get(3);
		ImageHandle ECLogoImage = designHandle.getElementFactory().newImage("ECLogoImage");
		ECLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ImgForTemplate" + File.separator + "eu.png\"");
				+ File.separator + "ImgForTemplate" + File.separator + "ec_logo.png\"");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.white);
		ECLogoImage.setWidth("1.2cm");
		ECLogoImage.setHeight("0.8cm");
//		ECLogoImage.setWidth("1.5cm");
//		ECLogoImage.setHeight("1.1cm");
		
		DesignUtils.setBorderGrid(gridCellHandle, Colors.red);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.15cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_LEFT_PROP, "0.1cm");
		gridCellHandle.getContent().add(ECLogoImage);
		/** add eu text **/ 
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "4.5pt", "right");
		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.15cm");
		text.setContent("<div style='color:" + Colors.white + ";'> European Union </div>");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);
		gridCellHandle.getContent().add(text);
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(4);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);
		
		/** MAP **/
		gridCellHandle = (CellHandle) row.getCells().get(5);
//		ImageHandle MapImage = designHandle.getElementFactory().newImage("ECMapImage");
//		MapImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
//				+ File.separator + "ImgForTemplate" + File.separator + "map_blue.png\"");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);
//		MapImage.setWidth("1.5cm");
//		MapImage.setHeight("1.1cm");
		DesignUtils.setBorderGrid(gridCellHandle, Colors.red);
//		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
//		gridCellHandle.getContent().add(MapImage);
		
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(6);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);
		
		
		/** TEXT **/
		gridCellHandle = (CellHandle) row.getCells().get(7);
		text = DesignUtils.createText(designHandle, text, "12pt", "right");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);
		text.setProperty(StyleHandle.COLOR_PROP, Colors.white);
		text.setContent("<div>Agriculture Development Assistance Mapping Report<br></div>");
		DesignUtils.setBorderGrid(gridCellHandle, Colors.red);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);
		DesignUtils.setBorderGrid(text, Colors.red);
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.2cm");
		gridCellHandle.getContent().add(text);	
	
		
		
//		text = DesignUtils.createText(designHandle, text,  "10pt", "right");
//		text.setProperty(StyleHandle.COLOR_PROP, white);
//		text.setContent("<div style='font-weight:bold'> www.foodsec.org </div>");
//		text.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.05cm");
//		gridCellHandle.getContent().add(text);	
		
		/** BLUE **/
		gridCellHandle = (CellHandle) row.getCells().get(8);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, Colors.red);
		
		
	
		
		
		ColumnHandle col = (ColumnHandle) headerGridHandle.getColumns().get(0);
		col.setProperty("width", "0.3%");
		col = (ColumnHandle) headerGridHandle.getColumns().get(1);
		col.setProperty("width", "1.1cm");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(2);
		col.setProperty("width", "0.1cm");
		col = (ColumnHandle) headerGridHandle.getColumns().get(3);
		col.setProperty("width", "1.5cm");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(4);
		col.setProperty("width", "0.7cm");
		col = (ColumnHandle) headerGridHandle.getColumns().get(5);
		col.setProperty("width", "4cm");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(6);
		col.setProperty("width", "1cm");
		col = (ColumnHandle) headerGridHandle.getColumns().get(7);
		col.setProperty("width", "11cm");	
		col = (ColumnHandle) headerGridHandle.getColumns().get(8);
		col.setProperty("width", "0.3%");
		 
		designHandle.getBody().add(headerGridHandle);
	}
	
	private void buildHeaderSecondRow() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderSRGrid", 2, 1);
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "13pt", "left");
		text.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#FFFFFF");
		text.setProperty(StyleHandle.COLOR_PROP, Colors.blue);
		text.setContent("<div style='font-weight:bold;'>Mapping of priorities and actions</div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) headerGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, "14pt", "right");
		text.setProperty(StyleHandle.COLOR_PROP, Colors.red);
		
		// TODO: list of countries or subregion
		
//		String countries;		
//		for (String country : vennReportBean.get) {
//			
//		}
		
		text.setContent("<div style='font-weight:bold;text-decoration:underline;'><u> Nepal </u></div>");
		gridCellHandle.setProperty(StyleHandle.PADDING_RIGHT_PROP, "0.4cm");
		gridCellHandle.setProperty(StyleHandle.PADDING_TOP_PROP, "0.1cm");
		gridCellHandle.getContent().add(text);	

		designHandle.getBody().add(headerGridHandle);
	}

	
	
}