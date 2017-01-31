package org.fao.fenix.web.modules.birt.server.utils.report;

import java.io.File;
import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.core.persistence.perspective.TextDao;
import org.fao.fenix.web.modules.core.server.utils.Setting;

import com.ibm.icu.util.ULocale;

public class AddTextToReport {

	public static void getText(String rptDesign, Long id, TextDao textDao, int objectPosition,  String reportType){
		
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
			
			TextItemHandle text=null;
			
			try{
				
				text=designReport.getElementFactory().newTextItem("Text");
				text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				text.setName(String.valueOf(id));
				text.setDisplayName("text");
				text.setContent(textDao.findById(id).getText().getContent());
				
				List<Integer> gridReference;

				if (reportType.equals("blankTemplate")){
					if ( objectPosition == -1)
						designReport.getBody().add(text);
					else
						designReport.getBody().add(text, objectPosition);
				} 
				else  if (reportType.equals("template1")){
					gridReference = FindGridReference.template1(objectPosition);
					GridHandle reportGridHandle = (GridHandle) designReport.findElement("Grid" + gridReference.get(0));
					RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
					CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
					if (gridCellHandle.getContent().getCount() != 0 ){
//						gridCellHandle.getContent().drop(gridReference.get(2));
						try {
							gridCellHandle.getContent().drop(0);
						}
						catch (Exception e) {								
							System.out.println("Error: gridCellHandle.getContent().drop(0): " + e.getMessage());
						}
					}
					gridCellHandle.getContent().add(text);
				} else  if (reportType.equals("template2")){
					gridReference = FindGridReference.template2(objectPosition);
					GridHandle reportGridHandle = (GridHandle) designReport.findElement("Grid" + gridReference.get(0));
					RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
					CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
					if (gridCellHandle.getContent().getCount() != 0 ){
//						gridCellHandle.getContent().drop(gridReference.get(2));
						try {
							gridCellHandle.getContent().drop(0);
						}
						catch (Exception e) {								
							System.out.println("Error: gridCellHandle.getContent().drop(0): " + e.getMessage());
						}
					}
					gridCellHandle.getContent().add(text);
				}
				
				designReport.saveAs(nameReport);
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		
	}
	
}
