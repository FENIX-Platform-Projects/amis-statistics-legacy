package org.fao.fenix.web.modules.adam.server.report.global;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.adam.server.report.utils.ADAMReportConstants;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.server.util.MapViewUtils;
import org.fao.fenix.web.modules.olap.client.control.StringComparator;

public class CreateADAMAskAdamBody {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ADAMReportBeanVO adamReportBean = null;
	
	public CreateADAMAskAdamBody(ADAMReportBeanVO adamReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.adamReportBean = adamReportBean;
	}
	
	
	
	public void build() throws SemanticException {
//		buildDescription();
		DesignUtils.addSpace(designHandle);
		
//		GridHandle dataGridHandle = designFactory.newGridItem("askadamgrid", 1, adamReportBean.getResources().size() + 100);
		
		int rowIndex = 0;
//		int columnIndex = 0;
		int index = 0;
		int counter = 0;
		System.out.println("adamReportBean: " + adamReportBean.getResources().size());
		
		TreeMap<String, ADAMResultVO> map = sortByKeys(adamReportBean.getResources());
		for(String key : map.keySet()) {
			
			DesignUtils.addSpace(designHandle);
			
			Boolean inserted = false;
			
				ADAMResultVO qvo = adamReportBean.getResources().get(key);
				System.out.println("ADAMBoxContent: " + qvo.getOutputType());
				ADAMBoxContent c = ADAMBoxContent.valueOf(qvo.getOutputType());
	//			ADAMBoxContent resource = ADAMBoxContent.valueOf(qvo.getResourceType());
				
				System.out.println("key: " + key);
				System.out.println("qvo: " + qvo.getOutputType());
				System.out.println("rowindex: " + rowIndex);
				System.out.println("columnIndex: " + 0);
				
	//			RowHandle row = (RowHandle) dataGridHandle.getRows().get(rowIndex);
	//			CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
				System.out.println("C: " + c);
	//			System.out.println("resource: " + resource);
				switch (c) {
					case BAR: 
							designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addChart(qvo, index));
	//						gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addChart(qvo, index));
							index++;
							break;
					case PIE: 
							designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addChart(qvo, index));
	//						gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addChart(qvo, index));
							index++;
					break;
					case TIMESERIE: 
						designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addChart(qvo, index));
//						gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addChart(qvo, index));
						index++;
					break;
					case MAP : break;
					case TOP_DONORS_TABLE: 
							designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
	//					 gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
							index++;
							break;
					case TOP_DONORS_TABLE_FILTERED: 
						designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
	//					 gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
						index++;
						break;
					case TOP_SECTORS_DONOR_VIEW_TABLE: 
						designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
	//					 gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
						index++;
						break;
					case TOP_SECTORS_DONOR_COUNTRY_VIEW_TABLE: 
						designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
	//					 gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
						index++;
						break;
					case TOP_AGRICULTURAL_SECTORS_DONOR_VIEW_TABLE: 
						designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
	//					 gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
						index++;
							break;
					case TOP_AGRICULTURAL_SECTORS_DONOR_COUNTRY_VIEW_TABLE: 
						designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
	//					 gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
						index++;
							break;
					case TOP_AGRICULTURAL_SECTORS_COUNTRY_VIEW_TABLE: 
						designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
	//					 gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
						index++;
						 break;
					case TOP_COUNTRIES_BY_DONOR: 
						designHandle.getBody().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
	//					 gridCellHandle.getContent().add(new CreateADAMReportResource(designHandle, designFactory).addTable(qvo, index));
						index++;
						break;
					case FAVOURITE_PURPOSES_QUESTIONS_VIEW:  break;
					case OLAP : break;
				}
				
//				DesignUtils.addLine(designHandle, designFactory, ADAMReportConstants.blue, "0.1px");
				DesignUtils.addSpace(designHandle);
				
				counter++;
				
				System.out.println("INDEX: " + index + " | " + counter);
				if ( index%2 == 0 && index != 0) {
					GridHandle dataGridHandle = designFactory.newGridItem("break" + counter, 1, 1);
					dataGridHandle.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");	
					designHandle.getBody().add(dataGridHandle);
					index = 0;
				}
	
				rowIndex = rowIndex + 2;
	

		
		}
//		designHandle.getBody().add(dataGridHandle);
	}
	
	private void buildDescription() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("askADAMDescription", 1, 1);
		
		RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, ADAMReportConstants.descriptionTextSize, "justify");
		
		String description = null;
		if ( adamReportBean.getReportType().equalsIgnoreCase("ASKADAM_COUNTRY")) {
			description = "ADAM provides views of the volume and structure of development assistance made available by resource partners. The data in the Resource partner view is intended to provide information about the aid funding that is being provided by a given resource partner. In addition a focus on FAO activities is included. Disbursement data show the realisation of resource partners' intentions and the implementation of policies, allowing their actual performance to be assessed. In general, disbursement data better describe aid flows from a recipient’s point of view. ";
		}
		else 	if ( adamReportBean.getReportType().equalsIgnoreCase("ASKADAM_DONOR")) {
			description = "ADAM provides views of the volume and structure of aid funding made available by donor countries. In the Country view are provided information  about the volume and structure of aid funding that is being received by a specific developing country. In addition a focus on FAO activities is included. Disbursement data show the realisation of resource partners’ intentions and the implementation of policies, allowing their actual performance to be assessed. In general, disbursement data better describe aid flows from a recipient’s point of view.";
		}
		
		text.setContent("<div style='color:" + ADAMReportConstants.descriptionFontColor + ";'> "+ description +" </div>");	
		gridCellHandle.getContent().add(text);
		
		
		designHandle.getBody().add(dataGridHandle);
	}
	
	
	public static TreeMap<String, ADAMResultVO> sortByKeys(HashMap<String, ADAMResultVO> in) {
		TreeMap<String, ADAMResultVO> out = new TreeMap<String, ADAMResultVO>(new StringComparator());
		for (String key : in.keySet())
			out.put(key, in.get(key));
		return out;
	}
	
	
	
	
	
	

	
}