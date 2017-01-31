package org.fao.fenix.web.modules.adam.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleMasterPageHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMReportBeanVO;
import org.fao.fenix.web.modules.adam.server.report.donorprofile.CreateADAMDonorProfileReport;
import org.fao.fenix.web.modules.adam.server.report.global.CreateADAMAskAdamBody;
import org.fao.fenix.web.modules.adam.server.report.global.CreateADAMAskAdamReport;
import org.fao.fenix.web.modules.adam.server.report.global.CreateADAMGlobalStandardReport;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.CreateECFirstPageNewTemplate;
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.CreateECSecondPageNewTemplate;
import org.fao.fenix.web.modules.venn.server.report.CreateVennCountryStandardReport;

import com.ibm.icu.util.ULocale;

public class CreateADAMReport {

	ReportDesignHandle designHandle = null;

	IDesignEngine designEngine = null;

	ElementFactory designFactory = null;
	
	ADAMReportBeanVO reportBean;

	// VennReportConstantsBean
		
	private static final Logger LOGGER = Logger.getLogger(CreateADAMReport.class);
	
	public CreateADAMReport(ADAMReportBeanVO reportBean) {
		this.reportBean = reportBean;
	}
	
	
	public String createReport() {
//		String rep = BirtUtil.randomNameFile();
//		String rep = getTemplate("VENN");
		String rptDesign = getTemplate("EC");
		
		LOGGER.info("rptdesign: " + rptDesign);
		try {
			
			
			DesignConfig dConfig = new DesignConfig();
			dConfig.setBIRTHome(Setting.getReportEngine());

			DesignEngine dEngine = new DesignEngine(dConfig);
			
			// Create a session handle, using the system locale.
			SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
			// Create a handle for an existing report design.
			String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
			
			designHandle = session.openDesign(name);
			designFactory = designHandle.getElementFactory();
			
			swithReportType();
//			new CreateVennCountryStandardReport(vennReportBean, designHandle, designFactory).build();

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

			designHandle.close();
			Platform.shutdown();


			

		} catch (Exception e) {
			e.printStackTrace();
		}

//		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rep
//		+ "&servletType=preview' width='100%' height='100%' frameborder='0' SCROLLING=NO  />";

		
		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign
		+ "&servletType=frameset' width='100%' height='100%' />";
	}
	
	private void swithReportType() throws SemanticException {
		if ( reportBean.getReportType().equals("GLOBAL_REPORT")) {
			LOGGER.info("GLOBAL_REPORT");
			new CreateADAMGlobalStandardReport(reportBean, designHandle, designFactory).build();
		}
		else if ( reportBean.getReportType().contains("ASKADAM")) {
			LOGGER.info("ASKADAM report");
			new CreateADAMAskAdamReport(reportBean, designHandle, designFactory).build();
		}
		else if ( reportBean.getReportType().equals("CURRENTVIEW")) {
			LOGGER.info("CURRENTVIEW report");
			new CreateADAMAskAdamReport(reportBean, designHandle, designFactory).build();
		}
		else if ( reportBean.getReportType().equals("DONORPROFILE")) {
			LOGGER.info("DONORPROFILE report");
			new CreateADAMDonorProfileReport(reportBean, designHandle, designFactory).build();
		}
	}
	
	

	
	public String createExportReport() {

		String rptDesign = getTemplate("EC");
//		System.out.println("exportECReportToPDF()");
		try {
				
			List<String> result = new ArrayList<String>();
			result.add(rptDesign);
			
			DesignConfig dConfig = new DesignConfig();
			dConfig.setBIRTHome(Setting.getReportEngine());

			DesignEngine dEngine = new DesignEngine(dConfig);
			
			// Create a session handle, using the system locale.
			SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
			// Create a handle for an existing report design.
			String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
			
			designHandle = session.openDesign(name);
			designFactory = designHandle.getElementFactory();
		

			swithReportType();
		

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
//			designHandle.setProperty("outputFormat", "PDF");

			designHandle.close();
//			Platform.shutdown();

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		String nameFile = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		String exportFile = String.valueOf((Math.random() * 10)) + ".pdf"; 
		
		IReportEngine reportEngine = BirtUtil.getReportEngine();
		try {

			IReportRunnable design = reportEngine.openReportDesign(nameFile);
			IRunTask task1 = reportEngine.createRunTask(design);
			task1.run(nameFile + ".rptdocument");

			IReportDocument document = reportEngine.openReportDocument(nameFile + ".rptdocument");

			IRenderOption options = new RenderOption();

			options.setOutputFormat(DesignChoiceConstants.FORMAT_TYPE_PDF);

			options.setOutputFileName(Setting.getSystemPath() + "/exportObject/" + exportFile);

			
			System.out.println("Setting.getSystemPath() exportFile:" +Setting.getSystemPath() + "/exportObject/" + exportFile);
			IRenderTask task = reportEngine.createRenderTask(document);
			task.setRenderOption(options);
			task.render();
			task.close();
//			Platform.shutdown();

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("exportFile: " + exportFile);
		return exportFile;

	}
	


	
	
	
	
	private String computeHeightRatio(String height) {
		Double h = Double.parseDouble(height.substring(0, height.length()) + 200);
		
		System.out.println("H: " + h );
		
	
		return Double.toString(h);
	}
	
	
	
	
	public String getTemplate(String templateType) {
		String template = Setting.systemPathBirt + "/" + getBirtApplName() + "/template/" + templateType + ".rptdesign";
		String nameRptdesign = BirtUtil.randomNameFile();
		String renameTemplate = System.getProperty("java.io.tmpdir") + File.separator + nameRptdesign;
		try {
			FileUtils.copyFile(new File(template), new File(renameTemplate));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nameRptdesign;
	}
	
	public String getBirtApplName() {
		return Setting.getBirtApplName();
	}
	
	
	
}