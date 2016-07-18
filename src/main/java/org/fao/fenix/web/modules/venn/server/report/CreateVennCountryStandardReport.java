package org.fao.fenix.web.modules.venn.server.report;

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
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.server.report.utils.CreateVennChartsReport;
import org.fao.fenix.web.modules.venn.server.report.utils.CreateVennGraphReport;
import org.fao.fenix.web.modules.venn.server.report.utils.CreateVennHeaderReport;
import org.fao.fenix.web.modules.venn.server.report.utils.CreateVennSummaryFirstPage;
import org.fao.fenix.web.modules.venn.server.report.utils.CreateVennTablesReport;
import org.fao.fenix.web.modules.venn.server.report.utils.VennSummaryReport;


public class CreateVennCountryStandardReport {
	
	ReportDesignHandle designHandle = null;

	ElementFactory designFactory = null;
	
	VennReportBeanVO vennReportBean = null;

	
	public CreateVennCountryStandardReport(VennReportBeanVO vennReportBean, ReportDesignHandle designHandle, ElementFactory designFactory ){
		this.designFactory = designFactory;
		this.designHandle = designHandle;
		this.vennReportBean = vennReportBean;
	}
	public void build() throws SemanticException {
		new CreateVennHeaderReport(vennReportBean, designHandle, designFactory).build(); 
		new DesignUtils().addSpace(designHandle);
//		new DesignUtils().addLine(designHandle, designFactory, "1", "black");
		new CreateVennSummaryFirstPage(vennReportBean, designHandle, designFactory).buildStandardReport();
//		new CreateVennGraphReport(vennReportBean, designHandle, designFactory).buildStandardReport();
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