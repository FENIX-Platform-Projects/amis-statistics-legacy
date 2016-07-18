package org.fao.fenix.web.modules.adam.server.report.global;

import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.server.report.utils.CreateADAMHeaderReport;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateADAMGlobalStandardReport {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ADAMReportBeanVO adamReportBean = null;

	
	public CreateADAMGlobalStandardReport(ADAMReportBeanVO adamReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.adamReportBean = adamReportBean;
	}
	public void build() throws SemanticException {
		new CreateADAMHeaderReport(adamReportBean, designHandle, designFactory).build(); 
		new DesignUtils().addSpace(designHandle);
		new CreateADAMGlobalMap(adamReportBean, designHandle, designFactory).buildGlobalMap();
		new DesignUtils().addSpace(designHandle);
		new CreateADAMGlobalLegendInfo(adamReportBean, designHandle, designFactory).build();
		
		
////	new DesignUtils().addLine(designHandle, designFactory, "1", "black");
//		new CreateVennSummaryFirstPage(vennReportBean, designHandle, designFactory).buildStandardReport();
////		new CreateVennGraphReport(vennReportBean, designHandle, designFactory).buildStandardReport();
//		new VennSummaryReport(vennReportBean, designHandle, designFactory).buildStandardReport();
//		new CreateVennTablesReport(vennReportBean, designHandle, designFactory).buildStandardReport(); 
//		new CreateVennChartsReport(vennReportBean, designHandle, designFactory).buildStandardReport(); 
		
////		buildHeaderPicture();
//		buildHeaderSecondRow();
////		DesignUtils.addSpace(designHandle);
//		buildKeyMessages();
//		DesignUtils.addSpace(designHandle);
////		DesignUtils.addSpace(designHandle);
//		buildBackground();
//		buildSecondPart();
	}
	
	

	
	
}