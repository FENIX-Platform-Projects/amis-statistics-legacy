package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.util.List;

import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.structures.ComputedColumn;

public class BoundColumns {
	
	public static void setBoundColumns(ExtendedItemHandle chartHandle, String xLabel, List<String> series){
		
		try {
			
			ComputedColumn cs;
			
			cs = StructureFactory.createComputedColumn( );
			cs.setName(xLabel);
			cs.setExpression( "dataSetRow[\"" + xLabel + "\"]");
			cs.setDataType("string");
			chartHandle.addColumnBinding(cs, true);
			
			for (String s : series) {
				cs = StructureFactory.createComputedColumn( );
				cs.setName(s);
				cs.setExpression( "dataSetRow[\""+ s + "\"]");
				cs.setDataType("decimal");				
				chartHandle.addColumnBinding(cs, true);
			}
			
			
			
		} catch (SemanticException e) {
			e.printStackTrace();
		}
		
		
	}

	
	public static void setBoundColumnsForPie(ExtendedItemHandle chartHandle, String xLabel, String yLabel, String series){
		
		try {
			
			ComputedColumn cs;
			
			cs = StructureFactory.createComputedColumn( );
			cs.setName(xLabel);
			cs.setExpression( "dataSetRow[\"" + xLabel + "\"]");
			cs.setDataType("string");
			chartHandle.addColumnBinding(cs, true);
			
			
			cs = StructureFactory.createComputedColumn( );
			cs.setName(yLabel);
			cs.setExpression( "dataSetRow[\"" + yLabel + "\"]");
			cs.setDataType("string");
			chartHandle.addColumnBinding(cs, true);
			
			cs = StructureFactory.createComputedColumn( );
			cs.setName(series);
			cs.setExpression( "dataSetRow[\""+ series + "\"]");
			cs.setDataType("decimal");				
			chartHandle.addColumnBinding(cs, true);
			
			
			
			
		} catch (SemanticException e) {
			e.printStackTrace();
		}
		
		
	}
}
