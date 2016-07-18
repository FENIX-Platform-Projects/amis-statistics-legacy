package org.fao.fenix.web.modules.adam.server.report.global;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.adam.server.report.utils.ADAMReportConstants;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.server.util.MapViewUtils;

public class CreateADAMGlobalMap {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ADAMReportBeanVO adamReportBean = null;
	
	public CreateADAMGlobalMap(ADAMReportBeanVO adamReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.adamReportBean = adamReportBean;
	}
	
	public void buildGlobalMap() throws SemanticException {
		ADAMResultVO adamResultVO = adamReportBean.getResources().get("MAP_GLOBAL_REPORT");
		
		GridHandle dataGridHandle = designFactory.newGridItem("globalMapview", 3, 3);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		designHandle.getBody().add(dataGridHandle);
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(1);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.headerTextSize, "left");
		text.setContent("<div style='color:" + ADAMReportConstants.headerFontColor + ";'> Global Map donor view </div>");
		gridCellHandle.getContent().add(text);
		
		
		ImageHandle imageHandle = designHandle.getElementFactory().newImage("Map");
		imageHandle.setDisplayName("map");
		imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
		imageHandle.setFile("\"" + System.getProperty("java.io.tmpdir") + File.separator + adamResultVO.getBigImagePath() + "\"");
		imageHandle.setWidth("100%");
		imageHandle.setHeight("10cm");

		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(1);
		gridCellHandle.getContent().add(imageHandle);
		
		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "0.2cm");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "0.2cm");
	}
	
	
	
	
	
	
	
	

	
}