package org.fao.fenix.web.modules.adam.server.report.donorprofile;

import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.web.modules.adam.common.vo.ADAMDonorProfileVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.server.report.utils.CreateADAMHeaderReport;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;


public class CreateADAMDonorProfileReport {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	ADAMReportBeanVO adamReportBean = null;
	
	
	public CreateADAMDonorProfileReport(ADAMReportBeanVO adamReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.adamReportBean = adamReportBean;
	}
	
	public void build() throws SemanticException {
		new CreateADAMHeaderReport(adamReportBean, designHandle, designFactory).build(); 
		new DesignUtils().addSpace(designHandle);
		new CreateADAMDonorProfileBody(adamReportBean, designHandle, designFactory).build();
		//new CreateADAMDonorProcessesBody(adamReportBean, designHandle, designFactory).build();		
	}
	
	

	
	
}