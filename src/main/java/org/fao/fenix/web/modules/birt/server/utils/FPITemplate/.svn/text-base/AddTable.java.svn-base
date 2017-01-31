package org.fao.fenix.web.modules.birt.server.utils.FPITemplate;

import java.util.Iterator;
import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DataItemHandle;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.LabelHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.TableHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ComputedColumn;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.eclipse.birt.report.model.elements.interfaces.IStyleModel;

public class AddTable {
	
	public static TableHandle getTable(ReportDesignHandle designHandle,List<List<String>> data, int datasetNumber) throws SemanticException{
		ElementFactory elementFactory = designHandle.getElementFactory();

		// create a table and set it up, add the table to the design
		TableHandle table = elementFactory.newTableItem("", data.get(0).size(), 1, 1, 1);
		table.setProperty(IStyleModel.TEXT_ALIGN_PROP, DesignChoiceConstants.TEXT_ALIGN_LEFT);
		table.setWidth("100%");
		table.setProperty(TableHandle.DATA_SET_PROP, "setScripted" + datasetNumber);
		
		// set the width of the first column to 3 inch
		//ColumnHandle ch = (ColumnHandle) table.getColumns().get(0);
		//ch.getWidth().setStringValue("3in");

		// bind the data set columns to the table
		DataSetHandle dataSetHandle = (DataSetHandle) designHandle.getDataSets().get(0);
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

		if (datasetNumber==0){
			{ // Create a Header Row Filled with labels
				rowHandle = (RowHandle) table.getHeader().get(0);

				for (int i=0; i<data.get(0).size(); i++){
					cellHandle = (CellHandle) rowHandle.getCells().get(i);
					labelHandle = elementFactory.newLabel("");
					labelHandle.setText(data.get(0).get(i));
					cellHandle.getContent().add(labelHandle);
					cellHandle.setStyleName("LabelHeader");
				}
				
			}
		}
		
		{ // Add DataItems to the Detail Row
			rowHandle = (RowHandle) table.getDetail().get(0);
			
			int spyRow=0;
			for(int i=0; i<data.get(0).size(); i++){
				cellHandle = (CellHandle) rowHandle.getCells().get(i);
				dataHandle = elementFactory.newDataItem("");
				dataHandle.setResultSetColumn(data.get(0).get(i));
				cellHandle.getContent().add(dataHandle);
				if (spyRow==0){
					cellHandle.setStyleName("LabelRow1");
					spyRow++;
				}else{
					cellHandle.setStyleName("LabelRow2");
					spyRow=0;
				}
			}
					
		}
	
		return table;
		
	}

}
