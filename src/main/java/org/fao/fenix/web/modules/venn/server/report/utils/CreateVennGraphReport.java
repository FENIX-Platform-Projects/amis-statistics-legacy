package org.fao.fenix.web.modules.venn.server.report.utils;

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
import org.eclipse.birt.report.model.api.TextItemHandle;

import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.birt.server.utils.report.Colors;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.venn.client.control.VennController;
import org.fao.fenix.web.modules.venn.common.vo.VennGraphBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennIntersectionsBean;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.server.report.VennReportConstants;

import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;



public class CreateVennGraphReport {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	VennReportBeanVO vennReportBean = null;

	
	public CreateVennGraphReport(VennReportBeanVO vennReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.vennReportBean = vennReportBean;
	}
	
	public void buildStandardReport() throws SemanticException {
		buildVennGraphGrid(true);
	}
	
	public GridHandle buildVennGraphGrid(Boolean insertIntoReport) throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("gridVenn", 1, 3);
//		DesignUtils.setBorderGrid(dataGridHandle, Colors.black);
		
		//build title
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildtitle());	
//		
		// build content
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(buildVennGraph());	
		
		// build info
		row = (RowHandle) dataGridHandle.getRows().get(2);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		gridCellHandle.getContent().add(new VennSummaryReport(vennReportBean, designHandle, designFactory).buildGridStandardReport());	

		if ( insertIntoReport ) 
			designHandle.getBody().add(dataGridHandle);
		
		return dataGridHandle;
		
//		buildVennInfo();
	}
	
	
	private GridHandle buildtitle() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("fpKeyMessageGrid", 1, 2);
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "11pt", "left");	
		text.setContent("<div style='font-weight:bold; color:" + Colors.orange + ";'> Common Priorities </div>");
		gridCellHandle.getContent().add(text);	
		
		row = (RowHandle) dataGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);
		text = DesignUtils.createText(designHandle, text, VennReportConstants.tableHeaderTextSize, "left");	
		text.setContent("<div style='font-weight:bold;'> The VENN diagram shows convergences between the selected actors, helping in identifying communalities and possible gaps. </div>");
		gridCellHandle.getContent().add(text);	
		

//		designHandle.getBody().add(dataGridHandle);
		return dataGridHandle;
	}
	
	
	private GridHandle buildVennGraph() throws SemanticException {
		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderGrid", 3, 1);

		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(1);
		ImageHandle	VennImage = designHandle.getElementFactory().newImage("FaoLogo");


		System.out.println(vennReportBean.getVennGraphReportBean().getVennGraphBeanVO().getVennImages().get("normal"));
		String vennImageName = vennReportBean.getVennGraphReportBean().getVennGraphBeanVO().getVennImages().get("normal");
		
		VennImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName()
				+ File.separator + "venn" + File.separator +  "temp" + File.separator + vennImageName +"\"");
		
		gridCellHandle.getContent().add(VennImage);


		
//		ColumnHandle col = (ColumnHandle) headerGridHandle.getColumns().get(0);
//		col.setProperty("width", "10%");
//		col = (ColumnHandle) headerGridHandle.getColumns().get(1);
//		col.setProperty("width", "80%");
//		col = (ColumnHandle) headerGridHandle.getColumns().get(2);
//		col.setProperty("width", "10%");
		
		 
//		designHandle.getBody().add(headerGridHandle);
		return headerGridHandle;
	}
	
	private GridHandle buildVennInfo() throws SemanticException {
		
		VennGraphBeanVO vennGraphBean = vennReportBean.getVennGraphReportBean().getVennGraphBeanVO();
		List<VennIntersectionsBean> intersactions = vennGraphBean.getAllIntersections();
		
		GridHandle intersectionsGridHandle = designFactory.newGridItem("vennTableInfo", 1, intersactions.size());
		
		for (int i=0; i < intersactions.size(); i++) { 
//			List<String> dacCodes = filterDACCodes(intersaction.getDacCodes(), beanVenn.getAggregationLevel());
		
				if (!intersactions.get(i).getAggregatedDacCodes().isEmpty()) {
					
					RowHandle row = (RowHandle) intersectionsGridHandle.getRows().get(i);
					CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
					gridCellHandle.getContent().add(buildIntersectionInfoGrid(intersactions.get(i), i));
					
					// CENTRAL
//					String htmlCentralString = new String();
//					Hyperlink labelCentral = new Hyperlink();
//					String str = "000000" + Integer.toHexString(intersaction.getColor());
//					String color = "#" + str.substring(str.length() - 6);
//					if ( color.equals("#ffffff")) 
//						color = "#000000";
//					labelCentral.setHTML("<div style='font-size:11px; color:" + color + "'><u>" + intersaction.getLabel() + "</u></div>");
//					panelCentral.add(labelCentral);
//	
//					Window windowCentral = new Window();
//					windowCentral.setHeading(intersaction.getLabel());
//					for(String dacCode : intersaction.getAggregatedDacCodes()) {
////						htmlString += l + " - " + beanVenn.getDacCodes().get(l);
////						htmlString +=  dacCode + " - " + beanVenn.getDacCodes().get(dacCode);
//						htmlCentralString += vennGraphBean.getDacCodes().get(dacCode);
//						htmlCentralString += "<br>";
//					}
//					HTML htmlCentral = new HTML(htmlCentralString);
//					windowCentral.add(htmlCentral);
	
									
				}
			
			
		}

		 
//		designHandle.getBody().add(intersectionsGridHandle);
		return intersectionsGridHandle;
	}
	
	
	private GridHandle buildIntersectionInfoGrid(VennIntersectionsBean intersaction, int tableIndex) throws SemanticException {
		GridHandle tableGridHandle = designFactory.newGridItem("vennTableValues" + tableIndex, 2, 2 );
		
		// intersection title
		RowHandle row = (RowHandle) tableGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, "11pt", "left");	
		
//		String str = "000000" + Integer.toHexString(intersaction.getColor());
//		String color = "#" + str.substring(str.length() - 6);
//		if ( color.equals("#ffffff")) 
//			color = "#000000";
		
		text.setContent("<div style='font-weight:bold'> "+ intersaction.getLabel() +"</div>");
		gridCellHandle.getContent().add(text);	
		
		//intersection content
		row = (RowHandle) tableGridHandle.getRows().get(1);
		gridCellHandle = (CellHandle) row.getCells().get(0);	
		
		GridHandle contentGridHandle = designFactory.newGridItem("vennTablecontentValues" + tableIndex, 2, intersaction.getAggregatedDacCodes().size());

				
		for(int i=0; i < intersaction.getAggregatedDacCodes().size(); i++) {
			RowHandle contentRow = (RowHandle) tableGridHandle.getRows().get(i);
			CellHandle contentGridCellHandle = (CellHandle) row.getCells().get(1);	
			text = DesignUtils.createText(designHandle, text, "11pt", "left");	
			// DAC LABEL
			text.setContent("<div style='font-weight:bold'> "+ vennReportBean.getVennGraphReportBean().getVennGraphBeanVO().getDacCodes().get(intersaction.getAggregatedDacCodes().get(i)) +"</div>");
			contentGridCellHandle.getContent().add(text);
		}

		gridCellHandle.getContent().add(contentGridHandle);

		
		return tableGridHandle;
	}
	

	
	
}