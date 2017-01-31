package org.fao.fenix.web.modules.haiticnsatool.server;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IRenderTask;
import org.eclipse.birt.report.engine.api.IReportDocument;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.core.domain.cnsa.CNSAByDateBean;
import org.fao.fenix.core.domain.cnsa.CNSAByDateRowBean;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class CreateCNSAToolReport {

	ReportDesignHandle designHandle = null;

	IDesignEngine designEngine = null;

	ElementFactory designFactory = null;
	
	CNSAByDateBean cnsaBean = null;
	
	String black = "#000000";
	
	String textSize = "8.5pt";
	
	String textHeaderSize = "11pt";
	
	String backgroudHeaders = "#FF9933"; 
	
	String width;
	
	String height;
	
	private static final Logger LOGGER = Logger.getLogger(CreateCNSAToolReport.class);
	
public static Map<Integer, String> monthLabelMap;
	
	static {
		monthLabelMap = new HashMap<Integer, String>();
		monthLabelMap.put(0, "January");
		monthLabelMap.put(1, "February");
		monthLabelMap.put(2, "March");
		monthLabelMap.put(3, "April");
		monthLabelMap.put(4, "May");
		monthLabelMap.put(5, "June");
		monthLabelMap.put(6, "July");
		monthLabelMap.put(7, "August");
		monthLabelMap.put(8, "September");
		monthLabelMap.put(9, "October");
		monthLabelMap.put(10, "November");
		monthLabelMap.put(11, "December");
	}
	
	
	public CreateCNSAToolReport(CNSAByDateBean cnsaBean, String height, String width) {
		this.cnsaBean = cnsaBean;
		this.width = width;
		this.height = height;

	}
	
	public String createTable(List<List<String>> table) {
		String html = "<table border=\"1\">";
		
		for(List<String> row : table){
			html += "<tr> ";
			for (String cell : row) {
				html += "<td> "+ cell+ "</td>";
			}		
			html += "<\tr>";
		}
		return html;
		
	}
	
	public String createReport(Boolean exportPDF) {
		
		System.out.println("exportPDF: "+ exportPDF);
		
		if ( exportPDF ) 
			return exportReportToPDF(cnsaBean.getDate());
		else {
				SessionHandle session = BirtUtil.getDesignSessionHandle();
				String rep = BirtUtil.randomNameFile();
				
				System.out.println("rep: " + rep);
				try {

					designHandle = session.createDesign();
					designFactory = designHandle.getElementFactory();	
					
					createReportPage();

					designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);

					designHandle.close();
					Platform.shutdown();
		
					
		
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rep
				+ "&servletType=preview' width='"+ width +"' height='"+ height +"' />";
			}
		
		

	}
	
	public String exportReportToPDF(String date) {
		
		SessionHandle session = BirtUtil.getDesignSessionHandle();
		String rep = BirtUtil.randomNameFile();
		
		System.out.println("rep: " + rep);
		try {

			designHandle = session.createDesign();
			designFactory = designHandle.getElementFactory();	
			
			createReportPage();

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
	
	
	private void createReportPage() throws SemanticException {
		buildTable();
	}
	

	private void buildTable() throws SemanticException {
		// the +2 is because the first column and the second
		// are respectively the commodity and measurement unit
		Integer tableLenght = cnsaBean.getMarkets().size() + 2;
		
		// based on the number of commodity and the headers
		Integer tableHeight = cnsaBean.getRows().size() + 2;
		
		LOGGER.info("table lenght: " + tableLenght);
		LOGGER.info("table height: " + tableHeight);
		
		
		
		GridHandle gridHandle = designFactory.newGridItem("fpHeaderGrid", tableLenght, tableHeight);
//		headerGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
//		/** date **/
//		RowHandle row = (RowHandle) gridHandle.getRows().get(0);
//		row.setProperty(RowHandle.HEIGHT_PROP, "1.2cm");	
//		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
//		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
//		text = DesignUtils.createText(designHandle, text, textHeaderSize, "left");
//		text.setContent("<div> <b>"+ cnsaBean.getDate()  +"</b> </div>");
//		gridCellHandle.getContent().add(text);
//		
//		/** header **/
//		row = (RowHandle) gridHandle.getRows().get(0);	
//		gridCellHandle = (CellHandle) row.getCells().get(2);
//		text = DesignUtils.createText(designHandle, text,textHeaderSize, "left");
//		text.setContent("<div> <b> Prix moyen en gourdes </b> </div>");
//		gridCellHandle.getContent().add(text);
		
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, textHeaderSize, "left");
		Date date = FieldParser.parseDate(cnsaBean.getDate());
		String d = date.getDate() + " " + monthLabelMap.get(date.getMonth()) + " " + ( 1900 + date.getYear());
		text.setContent("<div> <b>"+ d +" - Prix moyen en gourdes </b> </div>");
		designHandle.getBody().add(text);
		
		DesignUtils.addSpace(designHandle);
		DesignUtils.addSpace(designHandle);
		
		
		
		createRows(gridHandle);
		

		 
		designHandle.getBody().add(gridHandle);
	}
	
	private void createRows(GridHandle gridHandle) throws SemanticException {
		// create Products
		RowHandle row = (RowHandle) gridHandle.getRows().get(1);	
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, textSize, "left");
		text.setContent("<div> <b> Produits </b> </div>");
		DesignUtils.setBorderGrid(gridCellHandle, black);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, backgroudHeaders);
		gridCellHandle.getContent().add(text);
		
		row = (RowHandle) gridHandle.getRows().get(1);	
		gridCellHandle = (CellHandle) row.getCells().get(1);
		text = DesignUtils.createText(designHandle, text, textSize, "left");
		text.setContent("<div><b> Unité de mesure </b></div>");
		DesignUtils.setBorderGrid(gridCellHandle, black);
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, backgroudHeaders);
		gridCellHandle.getContent().add(text);
		
		int i=2;
		// create Provinces
		for(String key : cnsaBean.getMarkets().keySet()) {
			row = (RowHandle) gridHandle.getRows().get(1);	
			gridCellHandle = (CellHandle) row.getCells().get(i);
			text = DesignUtils.createText(designHandle, text, textSize, "left");
			text.setContent("<div> <b> "+ cnsaBean.getMarkets().get(key) +" </b></div>");
			DesignUtils.setBorderGrid(gridCellHandle, black);
			gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, backgroudHeaders);
			gridCellHandle.getContent().add(text);
			i++;
		}
		
		// create commodities
		int j=2;
		for(String commodityKey : cnsaBean.getRows().keySet()){
			
			// add commodity 
			row = (RowHandle) gridHandle.getRows().get(j);	
			gridCellHandle = (CellHandle) row.getCells().get(0);
			text = DesignUtils.createText(designHandle, text, textSize, "left");
			text.setContent("<div> <b>"+ commodityKey +" </b> </div>");
			gridCellHandle.getContent().add(text);
			DesignUtils.setBorderGrid(gridCellHandle, black);
			CNSAByDateRowBean rowBean = cnsaBean.getRows().get(commodityKey);
			
			// add measuremant unit
			row = (RowHandle) gridHandle.getRows().get(j);	
			gridCellHandle = (CellHandle) row.getCells().get(1);
			text = DesignUtils.createText(designHandle, text, textSize, "left");
			text.setContent("<div> "+ rowBean.getMeasurmentUnit() +" </div>");
			DesignUtils.setBorderGrid(gridCellHandle, black);
			gridCellHandle.getContent().add(text);
			
			// add provinces values
			int z=2;
			for(String key : cnsaBean.getMarkets().keySet()) {
				row = (RowHandle) gridHandle.getRows().get(j);	
				gridCellHandle = (CellHandle) row.getCells().get(z);
				text = DesignUtils.createText(designHandle, text, textSize, "right");
				if ( rowBean.getMarketsValue().get(key) != null )
					text.setContent("<div> "+ rowBean.getMarketsValue().get(key)+" </div>");
				else
					text.setContent("<div> </div>");
				gridCellHandle.getContent().add(text);
				DesignUtils.setBorderGrid(gridCellHandle, black);
				z++;
			}
			
				
			
			j++;
		}
		
		
		
	}
	
	private void buildHeaderFirstRow() throws SemanticException {
		// the +2 is because the first column and the second
		// are respectively the commodity and measurement unit
		Integer tableLenght = cnsaBean.getMarkets().size() + 2;
		
		// based on the number of commodity and the headers
		Integer tableHeight = cnsaBean.getMarkets().size() + 2;
		
		
		GridHandle headerGridHandle = designFactory.newGridItem("fpHeaderGrid", tableLenght, tableHeight);
//		headerGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		
		/** BLUE **/
		RowHandle row = (RowHandle) headerGridHandle.getRows().get(0);
		row.setProperty(RowHandle.HEIGHT_PROP, "1.2cm");	
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(3);
		DesignUtils.setLeftBorderGrid(gridCellHandle, black);

		 
		designHandle.getBody().add(headerGridHandle);
	}
	
	
	
	
	
}