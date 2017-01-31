package org.fao.fenix.web.modules.birt.server.utils.report;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.LabelHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.DesignUtils;

public class AddGridToReport {
	
	private static final Logger LOGGER = Logger.getLogger(AddGridToReport.class);
	
	
	public static GridHandle buildGrid(List<List<String>> table, ElementFactory designFactory, ReportDesignHandle designHandle, String textSize, String textColor,String headerSize, String headerColor, String gridBorderColor, String backgroundHeadersColor ) throws SemanticException {
		
//		LOGGER.info(table);
//		LOGGER.info(table.get(0).size());
//		LOGGER.info(table.size());
		
		GridHandle gridHandle = designFactory.newGridItem("buildGraphAndTable" + BirtUtil.randomChartName(), table.get(0).size(), table.size());
		for(int i=0; i < table.size(); i++) {
			for(int j=0; j< table.get(i).size(); j++) {
				System.out.println("table[i][j]: " + table.get(i).get(j) + " | " +  textColor);
				
				RowHandle row = (RowHandle) gridHandle.getRows().get(i);	
				CellHandle gridCellHandle = (CellHandle) row.getCells().get(j);
				TextItemHandle text = designHandle.getElementFactory().newTextItem("");

				
				// headers
				if ( i == 0 )
					text = setHeaders(designHandle, gridCellHandle, headerSize, backgroundHeadersColor, table.get(i).get(j), headerColor);
				// content
				else
					text = setRows(designHandle, textSize, table.get(i).get(j), textColor);
				
				
				DesignUtils.setBorderGrid(gridCellHandle, gridBorderColor);
				gridCellHandle.getContent().add(text);
				
				
			}
		}


		return gridHandle;
	}
	
   public static GridHandle buildColumnGrid(String title, List<List<String>> table, ElementFactory designFactory, ReportDesignHandle designHandle, String textSize, String textColor,String headerSize, String headerColor, String gridBorderColor, String backgroundHeadersColor ) throws SemanticException {
		
//		LOGGER.info(table);
//		LOGGER.info(table.get(0).size());
//		LOGGER.info(table.size());
		
	   //column 1, column 2
	   // label: value
	   
	    
	   /***cs3 = StructureFactory.createComputedColumn( );


	   cs3.setName("Node");
	   cs3.setExpression("dataSetRow[\"value\"]");//$NON-NLS-1$
	   computedSet.addItem( cs3 );


	   RowHandle detail3 = (RowHandle) table.getDetail( ).get( 0 );
	   tcell = (CellHandle) detail3.getCells( ).get( 2 );
	   tcell.setStringProperty("color", "red"); 
	   DataItemHandle data3 = elementFactory.newDataItem( null );
	   data3.setResultSetColumn( cs3.getName( ) );
	   tcell.getContent( ).add( data3 );
	   
	   tcell.setOnCreate("this.getStyle().backgroundColor = row['valuecolor'];");
	   **/
	   
		//GridHandle gridHandle = designFactory.newGridItem"buildGraphAndTable" + BirtUtil.randomChartName(), table.get(0).size(), table.size());
	
	   List<String> headers = table.get(0);
		
		GridHandle gridHandle = designFactory.newGridItem("buildGraphAndTable" + BirtUtil.randomChartName(), 2, headers.size());
		DesignUtils.setBorderGrid(gridHandle, Colors.black);		
		
		  LOGGER.info("column count = "+gridHandle.getColumnCount() + ": row count = "+gridHandle.getRows().getCount());
			
		// position 0 headers List<> strings
		// position 1 ... n values
		
		//table size = number of rows in the result
		
		
		for(int i=0; i< headers.size(); i++) {	
		  LOGGER.info("TEST: headers.get(i) = "+headers.get(i) + ": table.get("+i+1+").get("+i+") value = "+table.get(1).get(i));
		}
		
		RowHandle row = (RowHandle) gridHandle.getRows().get(0);	
		CellHandle gridCellHandle = (CellHandle) row.getCells().get(0);
		//gridCellHandle.setColumnSpan(2);
		gridCellHandle.setRowSpan(1);
		gridCellHandle.getContent().add(setHeaders(designHandle, gridCellHandle, textSize, backgroundHeadersColor, title, textColor));
		DesignUtils.setBorderGrid(gridCellHandle, gridBorderColor);

		
		
			for(int j=0; j< headers.size(); j++) {
			//	for(int i=1; i < table.size(); i++) {
					
				//System.out.println("header = " +headers.get(j)+ ": value = "+ table.get(i).get(j) + " | " +  textColor);
				
				 row = (RowHandle) gridHandle.getRows().get(j+1);	
				
				
				if(row!=null){
					//CELL 1
					gridCellHandle = (CellHandle) row.getCells().get(0);
//					Te
//					TextItemHandle text = setHeaders(designHandle, gridCellHandle, textSize, backgroundHeadersColor, headers.get(j), textColor);
					//System.out.println("headers.get("+j+") = "+headers.get(j)+" text = " +text.getContent());
					gridCellHandle.getContent().add(setHeaders(designHandle, gridCellHandle, textSize, backgroundHeadersColor, headers.get(j), textColor));
				
					//CELL 2
					gridCellHandle = (CellHandle) row.getCells().get(1);
				  //  TextItemHandle text2 = setRows(designHandle, textSize, table.get(1).get(j), textColor);
				    gridCellHandle.getContent().add(setRows(designHandle, textSize, table.get(1).get(j), textColor));
				
				    DesignUtils.setBorderGrid(gridCellHandle, gridBorderColor);
//				    gridCellHandle.getContent().add(text2);				
				}
				
				
				//TextItemHandle text = setHeaders(designHandle, gridCellHandle, textSize, backgroundHeadersColor, headers.get(j), textColor);
				//gridCellHandle.getContent().add(text);
				
				//CELL 2
				//gridCellHandle = (CellHandle) row.getCells().get(1);
				//text = setRows(designHandle, textSize, table.get(i).get(j), textColor);
				//gridCellHandle.getContent().add(text);
				
				//DesignUtils.setBorderGrid(gridCellHandle, gridBorderColor);
				//gridCellHandle.getContent().add(text);
	
			//}
		}

		
		
		/***for(int i=0; i < table.size(); i++) {
			
			for(int j=0; j< table.get(i).size(); j++) {
				System.out.println("table[i][j]: " + table.get(i).get(j) + " | " +  textColor);
				
				//RowHandle row = (RowHandle) gridHandle.getRows().get(j);//(RowHandle) gridHandle.getRows().get(i);	
				//CellHandle gridCellHandle = (CellHandle) row.getCells().get(i); //(CellHandle) row.getCells().get(j);
				
				RowHandle row = (RowHandle) gridHandle.getRows().get(i);	
				CellHandle gridCellHandle = (CellHandle) row.getCells().get(j);
				
				
			
				TextItemHandle text = designHandle.getElementFactory().newTextItem("");

				
				// headers
				//if ( i == 0 )
				//	text = setHeaders(designHandle, gridCellHandle, headerSize, backgroundHeadersColor, table.get(i).get(j), headerColor);
				// content
				//else
					text = setRows(designHandle, textSize, table.get(i).get(j), textColor);
				
				
				DesignUtils.setBorderGrid(gridCellHandle, gridBorderColor);
				gridCellHandle.getContent().add(text);
				
				
			}
		}
***/

		return gridHandle;
	}
	

	
	
	public static void buildGridToReport(List<List<String>> table, ReportDesignHandle designHandle, GridHandle gridHandle, String textSize, String textColor, String gridBorderColor, String backgroundHeadersColor ) throws SemanticException {
		for(int i=0; i < table.size(); i++) {
			for(int j=0; j< table.get(i).size(); j++) {
//				System.out.println("table[i][j]: " + table.get(i).get(j) + " | " +  textColor);
				
				RowHandle row = (RowHandle) gridHandle.getRows().get(i);	
				CellHandle gridCellHandle = (CellHandle) row.getCells().get(j);
				TextItemHandle text = designHandle.getElementFactory().newTextItem("");
				
				
				// headers
				if ( i == 0 )
					text = setHeaders(designHandle, gridCellHandle, textSize, backgroundHeadersColor, table.get(i).get(j), textColor);
				// content
				else
					text =  setRows(designHandle, textSize, table.get(i).get(j), textColor);
				
				
				DesignUtils.setBorderGrid(gridCellHandle, gridBorderColor);
				gridCellHandle.getContent().add(text);
				
				
			}
		}

		designHandle.getBody().add(gridHandle);
	}

	
	final private static TextItemHandle setHeaders(ReportDesignHandle designHandle, CellHandle gridCellHandle, String textSize, String backgroundHeadersColor, String content, String color) throws SemanticException {
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, textSize, "center");
		System.out.println("content: " + content);
		text.setContent("<div style='color:" + color + ";'> <b>"+ content +"</b> </div>");
		gridCellHandle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, backgroundHeadersColor);
		return text;
		
	}
	
	final private static TextItemHandle setRows(ReportDesignHandle designHandle, String textSize, String content, String color) throws SemanticException {
		System.out.println("content: " + content);
		TextItemHandle text = designHandle.getElementFactory().newTextItem("");
		text = DesignUtils.createText(designHandle, text, textSize, "left");
		text.setContent("<div style='color:" + color + ";'>"+ content +"</div>");
		return text;
	}
	
	
	
	
	
public static GridHandle buildGrid(List<List<String>> table, ElementFactory designFactory, ReportDesignHandle designHandle, String textSize, String textColor,String headerSize, String headerColor, String gridBorderColor, String backgroundHeadersColor, Boolean isGrouped ) throws SemanticException {
		
//		LOGGER.info(table);
//		LOGGER.info(table.get(0).size());
//		LOGGER.info(table.size());
		
		GridHandle gridHandle = designFactory.newGridItem("buildGraphAndTable" + BirtUtil.randomChartName(), table.get(0).size(), table.size());
		for(int i=0; i < table.size(); i++) {
			for(int j=0; j< table.get(i).size(); j++) {
//				System.out.println("table[i][j]: " + table.get(i).get(j) + " | " +  textColor);
				
				RowHandle row = (RowHandle) gridHandle.getRows().get(i);	
				CellHandle gridCellHandle = (CellHandle) row.getCells().get(j);
				TextItemHandle text = designHandle.getElementFactory().newTextItem("");

				
				// headers
				if ( i == 0 )
					text = setHeaders(designHandle, gridCellHandle, headerSize, backgroundHeadersColor, table.get(i).get(j), headerColor);
				// content
				else
					text = setRows(designHandle, textSize, table.get(i).get(j), textColor);
				
				
				DesignUtils.setBorderGrid(gridCellHandle, gridBorderColor);
				gridCellHandle.getContent().add(text);
				
				
			}
		}


		return gridHandle;
	}
}
