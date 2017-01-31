package org.fao.fenix.web.modules.ec.server.birt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.CreateECFirstPageNewTemplate;
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.CreateECSecondPageNewTemplate;
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.haiti_earhquake.CreateECFirstPageHaitiEarthquake;
import org.fao.fenix.web.modules.ec.server.birt.newtemplate.haiti_earhquake.CreateECSecondPageHaitiEarthquake;

import com.ibm.icu.util.ULocale;


public class CreateECReport {

	ReportDesignHandle designHandle = null;

	IDesignEngine designEngine = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	public CreateECReport(ECBean ecBean) {
		this.ecBean = ecBean;
	}
	
	public String createECReport(Boolean exportPDF) {
		String rptDesign = getTemplate("EC");
		System.out.println("exportPDF: "+ exportPDF);
		
		if ( exportPDF ) 
			return exportECReportToPDF();
		else {
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
				
		
		//			CreateECFirstPage createECFirstPage = new CreateECFirstPage(ecBean, designHandle, designFactory);
		//			createECFirstPage.build();
		//			CreateECSecondPage createECSecondPage = new CreateECSecondPage(ecBean, designHandle, designFactory);
		//			createECSecondPage.build();
					
			
					CreateECFirstPageNewTemplate createECFirstPage = new CreateECFirstPageNewTemplate(ecBean, designHandle, designFactory);
					createECFirstPage.build();
					CreateECSecondPageNewTemplate createECSecondPage = new CreateECSecondPageNewTemplate(ecBean, designHandle, designFactory);
					createECSecondPage.build();
				
		
					designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
		
					designHandle.close();
					Platform.shutdown();
		
					
		
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign
				+ "&servletType=frameset' width='100%' height='100%' />";
		}
		
		

	}
	
	public String exportECReportToPDF() {
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
		

//			CreateECFirstPage createECFirstPage = new CreateECFirstPage(ecBean, designHandle, designFactory);
//			createECFirstPage.build();
//			CreateECSecondPage createECSecondPage = new CreateECSecondPage(ecBean, designHandle, designFactory);
//			createECSecondPage.build();
			
	
			CreateECFirstPageNewTemplate createECFirstPage = new CreateECFirstPageNewTemplate(ecBean, designHandle, designFactory);
			createECFirstPage.build();
			CreateECSecondPageNewTemplate createECSecondPage = new CreateECSecondPageNewTemplate(ecBean, designHandle, designFactory);
			createECSecondPage.build();
		

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
			designHandle.setProperty("outputFormat", "PDF");

			designHandle.close();
			Platform.shutdown();

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		String nameFile = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		String exportFile = ecBean.getCountry() + "_" + ecBean.getDate() + ".pdf"; 
		
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

		} catch (Exception e) {
			e.printStackTrace();
		}

		return exportFile;

	}
	
	
	public String createHaitiEmergencyReport() {
		String rptDesign = getTemplate("EC");
		
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
			
			
			CreateECFirstPageHaitiEarthquake createECFirstPage = new CreateECFirstPageHaitiEarthquake(ecBean, designHandle, designFactory);
			createECFirstPage.build();
			CreateECSecondPageHaitiEarthquake createECSecondPage = new CreateECSecondPageHaitiEarthquake(ecBean, designHandle, designFactory);
			createECSecondPage.build();
		

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

			designHandle.close();
			Platform.shutdown();

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign
		+ "&servletType=frameset' width='100%' height='100%' />";

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