package org.fao.fenix.web.modules.ec.server.birt.newtemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.fao.fenix.core.domain.ec.ECBean;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;

import com.ibm.icu.util.ULocale;


public class CreateECReportNewTemplate {

	ReportDesignHandle designHandle = null;

	IDesignEngine designEngine = null;

	ElementFactory designFactory = null;
	
	ECBean ecBean = null;
	
	public CreateECReportNewTemplate(ECBean ecBean) {
		this.ecBean = ecBean;
	}
	
	public String createReport() {
//		SessionHandle session = BirtUtil.getDesignSessionHandle();
//		String rptDesign = BirtUtil.randomNameFile();
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