package org.fao.fenix.web.modules.bangladesh.server.birt;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.model.api.AutoTextHandle;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleMasterPageHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.elements.AutoText;
import org.eclipse.birt.report.model.elements.MasterPage;
import org.fao.fenix.core.domain.bangladesh.BangladeshBean;
import org.fao.fenix.core.domain.bangladesh.BangladeshReportStyleBean;
import org.fao.fenix.web.modules.bangladesh.common.vo.BangladeshChartsBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class CreateBangladeshReport {

	ReportDesignHandle designHandle = null;

	IDesignEngine designEngine = null;

	ElementFactory designFactory = null;
	
	BangladeshBean bangladeshBean = null;
	
	BangladeshChartsBean charts = null;
	
	String priceChangeTable = null;
	
	BangladeshReportStyleBean styleBean = null;
	
	private static final Logger LOGGER = Logger.getLogger(CreateBangladeshReport.class);
	
	public CreateBangladeshReport(BangladeshBean bangladeshBean, String priceChangeTable, BangladeshChartsBean charts, BangladeshReportStyleBean styleBean) {
		this.bangladeshBean = bangladeshBean;
		this.priceChangeTable = priceChangeTable;
		this.charts = charts;
		this.styleBean = styleBean;
		
//		LOGGER.info("************************************************** CHANGE TABLE");
//		LOGGER.info(this.priceChangeTable);
	}
	
	public String createReport() {
		
		
		

		SessionHandle session = BirtUtil.getDesignSessionHandle();
		String rep = BirtUtil.randomNameFile();
		String exportFile = BirtUtil.randomFileExport() + ".PDF"; 
		
		System.out.println("rep: " + rep);
		try {

			designHandle = session.createDesign();
			designFactory = designHandle.getElementFactory();	

			SimpleMasterPageHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");
			simpleMasterPage.setPageType("a4");
			simpleMasterPage.setOrientation("portrait");
			simpleMasterPage.setProperty(MasterPage.BOTTOM_MARGIN_PROP, "0.4cm");
			simpleMasterPage.setProperty(MasterPage.TOP_MARGIN_PROP, "0.4cm");
			simpleMasterPage.setProperty(MasterPage.LEFT_MARGIN_PROP, "1.10cm");
			simpleMasterPage.setProperty(MasterPage.RIGHT_MARGIN_PROP, "1.10cm");

			// add footer
			simpleMasterPage.getPageFooter().add(createFooter());

			designHandle.getMasterPages().add(simpleMasterPage);
			
	
			new CreateBangladeshReportFirstPage(designHandle, designFactory, bangladeshBean, styleBean);
			new CreateBangladeshReportSecondPage(designHandle, designFactory, bangladeshBean, priceChangeTable, charts, styleBean);
			new CreateBangladeshReportThirdPage(designHandle, designFactory, bangladeshBean, priceChangeTable, charts, styleBean);
			new CreateBangladeshReportFourthPage(designHandle, designFactory, bangladeshBean, priceChangeTable, charts, styleBean);
		

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);

			designHandle.close();
			Platform.shutdown();

//			System.out.println("Finished Successfull");

			
			
//			IReportEngine reportEngine = BirtUtil.getReportEngine();
//			try {
//
//				IReportRunnable design = reportEngine.openReportDesign(System.getProperty("java.io.tmpdir") + File.separator + rep);
//				IRunTask task1 = reportEngine.createRunTask(design);
//				task1.run(rep + ".rptdocument");
//
//				IReportDocument document = reportEngine.openReportDocument(rep + ".rptdocument");
//
//				IRenderOption options = new RenderOption();
//
//				options.setOutputFormat(DesignChoiceConstants.FORMAT_TYPE_PDF); 
//
//				options.setOutputFileName(Setting.getSystemPath() + "/bangladeshReports/" + exportFile);
//				
//				IRenderTask task = reportEngine.createRenderTask(document);
//				task.setRenderOption(options);
//
//				task.render();
//				task.close();
//
//			} catch (Exception e) {
//				LOGGER.warn(e.getMessage());
//			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(rep);
		
//		return exportFile;
		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rep
		+ "&servletType=frameset' width='100%' height='100%' />";

//		return rep.substring(0, (rep.length() - 10));
	}
	

	
	private GridHandle createFooter() throws SemanticException {
		GridHandle dataGridHandle = designFactory.newGridItem("dataGrid", 5, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row1 = (RowHandle) dataGridHandle.getRows().get(0);

		CellHandle gridCellHandle = (CellHandle) row1.getCells().get(4);
		
		AutoTextHandle autoTextPage = designHandle.getElementFactory().newAutoText("Pages");
		autoTextPage.setProperty(AutoText.AUTOTEXT_TYPE_PROP, "page-number");
		autoTextPage.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		autoTextPage.setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
		autoTextPage.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		gridCellHandle.getContent().add(autoTextPage);

	

		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "45%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "5%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "20%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "10%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "20%");

		return dataGridHandle;
	}
	
	
	
	
	
	
	
	
	
}