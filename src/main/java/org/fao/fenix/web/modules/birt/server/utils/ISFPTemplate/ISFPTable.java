package org.fao.fenix.web.modules.birt.server.utils.ISFPTemplate;

import java.util.Iterator;
import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.DataItemHandle;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.LabelHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TableHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ComputedColumn;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.eclipse.birt.report.model.elements.interfaces.IStyleModel;

public class ISFPTable {
	
	/**
	 *  list[0] header , list[x] body
	 */
	List<List<String>> data;
	ReportDesignHandle design;
	String nameTableObject;
	int x;
	int y;
	
	public ISFPTable(List<List<String>> data, ReportDesignHandle design, String nameTableObject, int x, int y){
		this.data = data;
		this.design = design;
		this.nameTableObject = nameTableObject;
		this.x = x;
		this.y = y;
		
		try {
			createDataSets();
			createStyles();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	private void createDataSets() throws SemanticException {

		// Data Set
		ScriptDataSetHandle dataSetHandle = design.getElementFactory().newScriptDataSet(nameTableObject);
		dataSetHandle.setDataSource("srcScripted");

		// Set open( ) in code
		StringBuffer openCode = generateOpenCode();
		dataSetHandle.setOpen(openCode.toString());

		// Set fetch( ) in code
		StringBuffer fetchCode = generateFetchCode();
		dataSetHandle.setFetch(fetchCode.toString());

		PropertyHandle computedSet = dataSetHandle.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

		// Since this is a Scripted Data Source you need to tell the
		// DataSet what the columns are. For this example, we will just
		// hard code the known resultColumn values

		ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();

		for (int i = 0; i < data.get(0).size(); i++) {
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(i);
			//resultColumn.setColumnName(data.get(0).get(i));
			resultColumn.setColumnName("col" + i);
			resultColumn.setDataType("string");
			computedSet.addItem(resultColumn);
		}

		design.getDataSets().add(dataSetHandle);

	}

	private StringBuffer generateFetchCode() {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		
		for (int j = 0; j < data.get(0).size(); j++) {
			fetchCode.append("row[\"col" + j + "\"] = sourcedata[count][" + j + "];").append("\n\t");
		}

		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
		return fetchCode;
	}

	private StringBuffer generateOpenCode() {

		StringBuffer openCode = new StringBuffer();

		openCode.append("count = 0;").append("\n");

		String tmp = "sourcedata = new Array(";

		int numCol = data.get(0).size();

		for (int i = 1; i < data.size(); i++) {
			if ((i + 1) == data.size()) {
				tmp += "new Array(" + numCol + "));";
			} else {
				tmp += "new Array(" + numCol + "),";
			}

		}

		openCode.append(tmp).append("\n\n");

		for (int i = 1; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				openCode.append("sourcedata[" + (i-1) + "][" + j + "]=\"" + data.get(i).get(j) + "\";").append("\n");
			}
		}

		return openCode;

	}
	
	private void createStyles() throws SemanticException {
		
		StyleHandle newStyle = design.getElementFactory().newStyle("Header");
		newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
		newStyle.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Arial");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "8px");
		
		newStyle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP , "solid");
		newStyle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");
		
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		design.getStyles().add(newStyle);

		newStyle = design.getElementFactory().newStyle("FirstRow");
		newStyle.setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Arial");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "8px");
		
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		newStyle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP,"solid");
		
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		design.getStyles().add(newStyle);
		
		newStyle = design.getElementFactory().newStyle("Row");
		newStyle.setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Arial");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "8px");
		
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP , "solid");
		newStyle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP,"solid");
		
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP,"1px");
		design.getStyles().add(newStyle);
		
	}
		
	
	public void addTableTo(String container, String widthFirstColumn, String widthColumn) throws SemanticException {
		ElementFactory elementFactory = design.getElementFactory();

		// create a table and set it up, add the table to the design
		TableHandle table = elementFactory.newTableItem("dataTable", data.size(), 1, 1, 1);
		table.setProperty(IStyleModel.TEXT_ALIGN_PROP, DesignChoiceConstants.TEXT_ALIGN_LEFT);
		table.setWidth("99%");
		table.setProperty(TableHandle.DATA_SET_PROP, nameTableObject);
		
		// set the width of the first column to 3 inch
		ColumnHandle ch;
		ch = (ColumnHandle) table.getColumns().get(0);
		ch.getWidth().setStringValue(widthFirstColumn);
		
		if (nameTableObject.equals("Cereal Balance")){
			ch = (ColumnHandle) table.getColumns().get(1);
			ch.getWidth().setStringValue(widthColumn);
			ch = (ColumnHandle) table.getColumns().get(2);
			ch.getWidth().setStringValue(widthColumn);
			ch = (ColumnHandle) table.getColumns().get(3);
			ch.getWidth().setStringValue(widthColumn);
			ch = (ColumnHandle) table.getColumns().get(4);
			ch.getWidth().setStringValue(widthColumn);
		} else if (nameTableObject.equals("Poverty")){
			ch = (ColumnHandle) table.getColumns().get(1);
			ch.getWidth().setStringValue(widthColumn);
		} else if (nameTableObject.equals("Food Cons and Expen")){
			ch = (ColumnHandle) table.getColumns().get(1);
			ch.getWidth().setStringValue(widthColumn);
			ch = (ColumnHandle) table.getColumns().get(2);
			ch.getWidth().setStringValue(widthColumn);
			ch = (ColumnHandle) table.getColumns().get(3);
			ch.getWidth().setStringValue(widthColumn);
		} 
		
		GridHandle reportGridHandle = (GridHandle) design.findElement(container);
		RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(x);
		CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(y);
		gridCellHandle.getContent().add(table);
		
		// bind the data set columns to the table
		DataSetHandle dataSetHandle = (DataSetHandle) design.findDataSet(nameTableObject);
		List resultSetCols = dataSetHandle.getListProperty(DataSetHandle.RESULT_SET_PROP);
		PropertyHandle boundCols = table.getColumnBindings();
		for (Iterator iterator = resultSetCols.iterator(); iterator.hasNext();) {
			ResultSetColumn rsHandle = (ResultSetColumn) iterator.next();
			ComputedColumn col = StructureFactory.createComputedColumn();
			col.setName(rsHandle.getColumnName());
			col.setExpression("dataSetRow[\"" + rsHandle.getColumnName() + "\"]");
			boundCols.addItem(col);
		}
		
		// These element handles will be used to populate the various
		// table rows, cells, labels, and dataItems
		RowHandle rowHandle;
		CellHandle cellHandle;
		LabelHandle labelHandle;
		DataItemHandle dataHandle;
		
		{ // Create a Header Row Filled with labels
			rowHandle = (RowHandle) table.getHeader().get(0);

			for (int i=0; i<data.get(0).size(); i++){
				try {
					cellHandle = (CellHandle) rowHandle.getCells().get(i);
					
					String cazzo = data.get(0).get(i);
					System.out.println("i = " + i + " label = " + cazzo);
					labelHandle = elementFactory.newLabel(cazzo);
					labelHandle.setText(data.get(0).get(i));
					cellHandle.getContent().add(labelHandle);
					cellHandle.setStyleName("Header");
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		{ // Add DataItems to the Detail Row
			rowHandle = (RowHandle) table.getDetail().get(0);
			
			for(int i=0; i<data.get(0).size(); i++){
				cellHandle = (CellHandle) rowHandle.getCells().get(i);
				dataHandle = elementFactory.newDataItem(data.get(0).get(i));
				dataHandle.setResultSetColumn("col" + i);
				cellHandle.getContent().add(dataHandle);
				cellHandle.setStyleName("Row");
				if ( i==0 ){
					cellHandle.setStyleName("FirstRow");
				}else{
					cellHandle.setStyleName("Row");
				}
			}
					
		}

	}
	

}
