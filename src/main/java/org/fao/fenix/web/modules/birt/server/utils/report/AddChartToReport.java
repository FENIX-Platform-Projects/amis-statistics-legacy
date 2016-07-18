package org.fao.fenix.web.modules.birt.server.utils.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.DataSourceHandle;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.ReportItemHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.fao.fenix.core.domain.perspective.DataView;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.web.modules.core.server.utils.Setting;

import com.ibm.icu.util.ULocale;

public class AddChartToReport {
	
	public static void getChart(String rptDesign, DataView r, int objectPosition, String reportType){
		
		DesignConfig dConfig = new DesignConfig( );
		dConfig.setBIRTHome(Setting.getReportEngine());
		
		DesignEngine dEngine = new DesignEngine( dConfig );
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle( ULocale.ENGLISH );
		
		
		//=============== Open the chart in a rptdesign file ======================
		
					
		File file = new File(System.getProperty("java.io.tmpdir") + File.separator + r.getResourceId() + ".rptdesign");
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
			out.write(r.getRptdesign());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FenixException("Unable to create rptdesign file for dataview " + r.getResourceId());
		}
							
		
			
			// Create a handle for an existing report design.
			String name = System.getProperty("java.io.tmpdir") + File.separator + r.getResourceId() + ".rptdesign";
			ReportDesignHandle design = null;
			try {
			     design = session.openDesign( name );
			} catch (Exception e) {
			     System.err.println ( "Report " + name + " not opened!\nReason is " +  e.toString( ) );
			    
			}
			
			//================= took the chart element with datasource and dataset ====
			
			
			
			DesignElementHandle chart= design.findElement("dataGrid");
			DataSourceHandle ds=design.findDataSource("srcScripted");
			DataSetHandle dset=design.findDataSet("setScripted");
						
			//=================== Create the new report ============================
			
			
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
				     System.err.println( "Report " + name + " not opened!\nReason is " +  e.toString( ) );
			}
				
				
			try{
					
					if (designReport.getAllDataSources().size()==0){
						designReport.getDataSources().add(ds);
					}
					
					int numDS=designReport.getAllDataSets().size();
					
					dset.setName("setScripted" + numDS);
					designReport.getDataSets().add(dset);
					
					chart.setProperty(ExtendedItemHandle.DATA_SET_PROP, dset.getName());
					
					chart.setName("dataGrid" + numDS);
					((ReportItemHandle) chart).setName(String.valueOf(r.getResourceId()));
					((ReportItemHandle) chart).setDisplayName("chart");
					((CellHandle)((RowHandle)((GridHandle) chart).getRows().get(0)).getCells().get(0)).getContent().get(0).setName("NewChart" + numDS);
					
					
					
//					try {
//					if (((CellHandle)((RowHandle)((GridHandle) chart).getRows().get(1)).getCells().get(0)).getContent().getCount() > 0) {
//						System.out.println("Par" + numDS + String.valueOf((int)(Math.random() * 10))+ String.valueOf((int)(Math.random() * 10)));
//						((CellHandle)((RowHandle)((GridHandle) chart).getRows().get(1)).getCells().get(0)).getContent().get(0).setName("Par" + numDS + String.valueOf((int)(Math.random() * 10)) + String.valueOf((int)(Math.random() * 10)));
//					}
//					}
//					catch (Exception e) {
//						System.out.println("error1: " + e.getStackTrace());
//					}
//					try { 
//					if (((CellHandle)((RowHandle)((GridHandle) chart).getRows().get(1)).getCells().get(0)).getContent().getCount() > 0) {
//						System.out.println("Par" + numDS + String.valueOf((int)(Math.random() * 10))+ String.valueOf((int)(Math.random() * 10)));
//						((CellHandle)((RowHandle)((GridHandle) chart).getRows().get(1)).getCells().get(0)).getContent().get(1).setName("Par" + numDS + String.valueOf((int)(Math.random() * 10)) + String.valueOf((int)(Math.random() * 10)));
//					}
					
					/// (CellHandle)((RowHandle)((GridHandle) chart).getRows() per ogni row dare il nome a cell(0), usando il par + numDS + math...etc
					
					int count = (((RowHandle)((GridHandle) chart).getRows().get(1)).getCells().getCount());
					
					System.out.println("cells: " + count);
					
					
					for( int i=0; i <= count; i++){
						String value = "Par" + numDS + String.valueOf((int)(Math.random() * 10)) + String.valueOf((int)(Math.random() * 10));
						
						try {
							if (((CellHandle)((RowHandle)((GridHandle) chart).getRows().get(1)).getCells().get(0)).getContent().get(i) != null)
								((CellHandle)((RowHandle)((GridHandle) chart).getRows().get(1)).getCells().get(0)).getContent().get(i).setName(value);
							}
						catch (Exception e) {
							System.out.println("errorPar: " + e.getStackTrace());
						}
					
					}
					
										
					List<Integer> gridReference;
					if (reportType.equals("blankTemplate")){
						if ( objectPosition == -1)
							designReport.getBody().add(chart);
						else
							designReport.getBody().add(chart, objectPosition);
					} 
					else  if (reportType.equals("template1")){
						gridReference = FindGridReference.template1(objectPosition);
						System.out.println("gridReference: " + gridReference);
						GridHandle reportGridHandle = (GridHandle) designReport.findElement("Grid" + gridReference.get(0));
						RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
						CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
						if (gridCellHandle.getContent().getCount() != 0 ){
							try {
								gridCellHandle.getContent().drop(0);
							}
							catch (Exception e) {								
								System.out.println("Error: gridCellHandle.getContent().drop(0): " + e.getMessage());
							}
							
						}
						gridCellHandle.getContent().add(chart);
					} 
					else  if (reportType.equals("template2")){
						gridReference = FindGridReference.template2(objectPosition);
						GridHandle reportGridHandle = (GridHandle) designReport.findElement("Grid" + gridReference.get(0));
						RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
						CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
						if (gridCellHandle.getContent().getCount() != 0 ){
//							gridCellHandle.getContent().drop(gridReference.get(2));
							try {
								gridCellHandle.getContent().drop(0);
							}
							catch (Exception e) {								
								System.out.println("Error: gridCellHandle.getContent().drop(0): " + e.getMessage());
							}
						}
						gridCellHandle.getContent().add(chart);
					}
					
					designReport.saveAs(nameReport);
				
				}catch (Exception e) {
					e.printStackTrace();
				}
				
		}
	
}


