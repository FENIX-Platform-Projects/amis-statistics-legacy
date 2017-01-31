package org.fao.fenix.web.modules.ec.server.birt.template2011;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.core.domain.ec.ECConflictsBean;
import org.fao.fenix.core.domain.ec.ECValuesBean;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.core.server.utils.Setting;


public class CreateECIPC2011 {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	String grey = "#C9CBCB";
	
	String orange = "#FF9933";
	
	String tableFontSize = "7.5pt";
	
	Logger LOGGER = Logger.getLogger(CreateECFirstPageConflicts2011.class);
	
	public CreateECIPC2011(ECBean ecBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.ecBean = ecBean;
	}
	
	
	public GridHandle buildIPC() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("IPCTABLER", 1, 2);

		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle ECLogoImage = designHandle.getElementFactory().newImage("IPCLogoImage");
		
		gridCellHandle.getContent().add(buildContent());
	

		return dataGridHandle;
		
		
	}
	
	private GridHandle buildContent() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("IPCTABLERcontent", 3, 1);
	
		
		/** logo **/
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		ImageHandle ECLogoImage = designHandle.getElementFactory().newImage("IPCLogoImage");
		ECLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "ImgForTemplate" + File.separator + "ipc_logo.jpg\"");
		ECLogoImage.setWidth("7.5cm");


		
		

		gridCellHandle.getContent().add(ECLogoImage);
		
		/** text **/
		row = (RowHandle) dataGridHandle.getRows().get(0);
		gridCellHandle = (CellHandle) row.getCells().get(2);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, tableFontSize, "center");
		text.setContent("<div><a href='"+ ecBean.getIpcBean().getText() +"'>See More</a></div>");
		gridCellHandle.getContent().add(text);

		


				
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "0.2cm");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "16.6%");	

		return dataGridHandle;
	}
	
	
	
}