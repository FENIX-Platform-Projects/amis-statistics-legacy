package org.fao.fenix.web.modules.birt.server.utils.report;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ImageHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.parser.DesignReader;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.persistence.perspective.MapDao;
import org.fao.fenix.map.mapretriever.MapRetriever;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;

import com.ibm.icu.util.ULocale;

public class AddMapToReport {
	
	public static void getMap(String rptDesign, Long idMapGWT, MapDao mapDao, int objectPosition, int width, int height, String reportType){
		
		DesignConfig dConfig = new DesignConfig( );
		dConfig.setBIRTHome(Setting.getReportEngine());
		
		DesignEngine dEngine = new DesignEngine( dConfig );
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle( ULocale.ENGLISH );
		
		
		//update the rptdesign file
		DesignConfig dConfigReport = new DesignConfig( );
		dConfigReport.setBIRTHome(Setting.getReportEngine());
			
		DesignEngine dEngineReport = new DesignEngine( dConfigReport );
		// Create a session handle, using the system locale.
		SessionHandle sessionReport = dEngine.newSessionHandle( ULocale.ENGLISH );
		// Create a handle for an existing report design.
		String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle designReport = null;
		try {
				
				designReport = session.openDesign( nameReport );
				     
		} catch (Exception e) {
			     System.err.println( "Report not opened!\nReason is " + e.toString( ) );
			    
		}
			
		ImageHandle imageHandle=null;
			
		try{
				
				MapView map=mapDao.findById(idMapGWT); 
				MapRetriever mm = new MapRetriever(map,System.getProperty("java.io.tmpdir"));
				mm.setWidth(width);
				mm.setHeight(height);
				BufferedImage image = mm.getMapImage();
				
				String imagine=BirtUtil.fileImagine(image);
				
				int numImage=designReport.getAllImages().size();
				
				
				imageHandle = designReport.getElementFactory().newImage("Map"+numImage);
				imageHandle.setName(String.valueOf(idMapGWT));
				imageHandle.setDisplayName("map");
				imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
				imageHandle.setFile("\""+Setting.getFenixPath() +"/"+Setting.getBirtApplName()+"/MapImg/"+imagine+"\"");
				
				List<Integer> gridReference;
				if (reportType.equals("blankTemplate")){
					designReport.getBody().add(imageHandle);
				} else  if (reportType.equals("template1")){
					gridReference = FindGridReference.template1(objectPosition);
					GridHandle reportGridHandle = (GridHandle) designReport.findElement("Grid" + gridReference.get(0));
					RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
					CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
					if (gridCellHandle.getContent().getCount() != 0 ){
						gridCellHandle.getContent().drop(gridReference.get(2));
					}
					gridCellHandle.getContent().add(imageHandle);
				} else if (reportType.equals("template2")){
					gridReference = FindGridReference.template2(objectPosition);
					GridHandle reportGridHandle = (GridHandle) designReport.findElement("Grid" + gridReference.get(0));
					RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
					CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
					if (gridCellHandle.getContent().getCount() != 0 ){
						gridCellHandle.getContent().drop(gridReference.get(2));
					}
					gridCellHandle.getContent().add(imageHandle);
				}
				
				designReport.saveAs(nameReport);
			
		}catch (Exception e) {
				e.printStackTrace();
		}
		
			
	}
	
	
	public static void addMap(String rptDesign, ReportDesignHandle designReport, MapView map, int width, int height, String reportType){
		
		
			
		ImageHandle imageHandle=null;
			
		try{
				
			MapRetriever mm = new MapRetriever(map,System.getProperty("java.io.tmpdir"));
			mm.setWidth(width);
			mm.setHeight(height);
			BufferedImage image = mm.getMapImage();
				
			String imagine=BirtUtil.fileImagine(image);
				
			int numImage=designReport.getAllImages().size();
				
				
			imageHandle = designReport.getElementFactory().newImage("Map"+numImage);
			imageHandle.setDisplayName("map");
			imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
			imageHandle.setFile("\""+Setting.getFenixPath() +"/"+Setting.getBirtApplName()+"/MapImg/"+imagine+"\"");
				
			designReport.getBody().add(imageHandle);

		}catch (Exception e) {
				e.printStackTrace();
		}
		
			
	}

}
