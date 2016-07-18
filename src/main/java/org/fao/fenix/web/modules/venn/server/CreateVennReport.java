package org.fao.fenix.web.modules.venn.server;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleMasterPageHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;
import org.fao.fenix.web.modules.venn.server.report.CreateVennCountryStandardReport;

import com.ibm.icu.util.ULocale;

public class CreateVennReport {

	ReportDesignHandle designHandle = null;

	IDesignEngine designEngine = null;

	ElementFactory designFactory = null;
	
	VennReportBeanVO vennReportBean;

	// VennReportConstantsBean
		
	private static final Logger LOGGER = Logger.getLogger(CreateVennReport.class);
	
	public CreateVennReport(VennReportBeanVO vennReportBean) {
		this.vennReportBean = vennReportBean;
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
			
			new CreateVennCountryStandardReport(vennReportBean, designHandle, designFactory).build();

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
	
	
	
	public String Report(String width, String height) {

		SessionHandle session = BirtUtil.getDesignSessionHandle();
		String rep = BirtUtil.randomNameFile();
		
		System.out.println("rep: " + rep);
		try {
			
			

			designHandle = session.createDesign();
			designFactory = designHandle.getElementFactory();	
			
			SimpleMasterPageHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");//$NON-NLS-1$
			simpleMasterPage.setOrientation("portrait");
			designHandle.getMasterPages().add(simpleMasterPage);
			
			
//			GraphReportEngine gre0 = new GraphReportEngine(chart, designHandle);
//			designHandle.getBody().add(gre0.getChart());
			
			
			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);

			designHandle.close();
			Platform.shutdown();
	
			} catch (Exception e) {
				e.printStackTrace();
			}


			String nameFile = System.getProperty("java.io.tmpdir") + File.separator + rep;
			String exportFile = BirtUtil.randomFileExport() + ".pdf"; 
			
			IReportEngine reportEngine = BirtUtil.getReportEngine();
			try {

//				IReportRunnable design = reportEngine.openReportDesign(nameFile);
//				IRunTask task1 = reportEngine.createRunTask(design);
//				task1.run(nameFile + ".rptdocument");

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
		
		
//		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rep
//		+ "&servletType=frameset' width='700px' height='550px' />";
		

//		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rep
//		+ "&servletType=preview' width='" + width + "px' height='" + height + "px' />";

	}
	
	
	
	
	

	
	
	



/*	private void addMap() throws SemanticException{
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("text");
//		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//		text.setContent("<center style='font-size:20px; font-weight:bold;'>" + mapView.getTitle() + "</center><br>");
//		
//		designHandle.getBody().add(text);
		
		GridHandle dataGridHandle = designFactory.newGridItem("dataGrid", 1, 3);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		designHandle.getBody().add(dataGridHandle);
		
		MapRetriever map = new MapRetriever(mapView,System.getProperty("java.io.tmpdir"));
		map.setWidth(Integer.valueOf(width));
		map.setHeight(Integer.valueOf(height));
		BufferedImage image = map.getMapImage();
		String nameFile=String.valueOf((Math.random() * 10)) + ".png";
		try{
			//File f=new File(System.getProperty("java.io.tmpdir") + File.separator + "/MapImg");
			ImageIO.write(image, "png", new File(System.getProperty("java.io.tmpdir") + File.separator + nameFile));
			ImageHandle imageHandle = designHandle.getElementFactory().newImage("Map");
			imageHandle.setDisplayName("map");
			imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
			imageHandle.setFile("\"" + System.getProperty("java.io.tmpdir") + File.separator + nameFile + "\"");
		
			RowHandle row = (RowHandle) dataGridHandle.getRows().get(0);
			CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
			gridCellHandle.getContent().add(imageHandle);
						
			//space
			row = (RowHandle) dataGridHandle.getRows().get(1);
			CellHandle spaceCell = (CellHandle) row.getCells().get(0);
			text = designHandle.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			text.setContent("<br><br>");
			spaceCell.getContent().add(text);
			
			LegendRetriever lr = new LegendRetriever();
			
			row = (RowHandle) dataGridHandle.getRows().get(2);
			CellHandle legendCell = (CellHandle) row.getCells().get(0);
			
			for (GeoView gv : gvList){
				lr.setLayer(gv);
				
				String fileName = System.getProperty("java.io.tmpdir") + File.separator + gv.getTitle() + ".png";
				File legendImage = new File(fileName); 
				
				if(lr.retrieve(legendImage)) {
					
					text = designHandle.getElementFactory().newTextItem("text");
					text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					text.setContent("<div style='font-size:12px;'>" + gv.getTitle() + "</div>");
					legendCell.getContent().add(text);
					
					ImageHandle legend = designHandle.getElementFactory().newImage("Lenged");
					legend.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
					legend.setFile("\"" + fileName + "\"");
					legendCell.getContent().add(legend);
					
					text = designHandle.getElementFactory().newTextItem("text");
					text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					text.setContent("<br>");
					legendCell.getContent().add(text);
					
				} else {
					System.out.println("ERROR adding legend.");
				}
				
			}
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/
	
	
	
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