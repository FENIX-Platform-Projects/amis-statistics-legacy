package org.fao.fenix.web.modules.birt.server.utils.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DataItemHandle;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.LabelHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.ScriptDataSourceHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TableHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ComputedColumn;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.eclipse.birt.report.model.elements.interfaces.IStyleModel;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.web.modules.birt.common.services.BirtService;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;

public class CreateTable {

	
	BirtService birtServiceImpl;
	
	GwtConnector gwtConnector;

	Long resourceId;

	ReportDesignHandle designHandle;

	List<String> datasetList;

	Long idDT;

	List<List<String>> valuesTable;

	List<String> dimension;

	int datasetNum;

	public CreateTable(BirtService birtServiceImpl, Long resourceId, ReportDesignHandle designHandle, GwtConnector gwtConnector) {
		this.resourceId = resourceId;
		this.birtServiceImpl = birtServiceImpl;
		this.designHandle = designHandle;
		this.gwtConnector = gwtConnector;

	}

	public BirtService getBirtServiceImpl() {
		return birtServiceImpl;
	}

	public void setBirtServiceImpl(BirtService birtServiceImpl) {
		this.birtServiceImpl = birtServiceImpl;
	}


	public ReportDesignHandle getDesignHandle() {
		return designHandle;
	}

	public Long getResourceId() {
		return resourceId;
	}

	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource("srcScripted");
		designHandle.getDataSources().add(dataSourceHandle);
	}

	private void createDataSets() throws SemanticException {

		// Data Set
		ScriptDataSetHandle dataSetHandle = designHandle.getElementFactory().newScriptDataSet("setScripted" + datasetNum);
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

		for (int i = 0; i < datasetList.size(); i++) {
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(i);
			resultColumn.setColumnName(datasetList.get(i));
			resultColumn.setDataType("string");
			computedSet.addItem(resultColumn);
		}

		designHandle.getDataSets().add(dataSetHandle);

	}

	private StringBuffer generateFetchCode() {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		
		for (int j = 0; j < valuesTable.get(0).size(); j++) {
			fetchCode.append("row[\"" + datasetList.get(j) + "\"] = sourcedata[count][" + j + "];").append("\n\t");
		}

		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
		return fetchCode;
	}

	private int getSize() {

		int numCol = valuesTable.get(0).size();

		for (int i = 1; i < valuesTable.size(); i++) {
			if ((numCol * i) > BirtUtil.TABLE_LIMIT) {
				return i;
			}
		}

		return valuesTable.size();
	}

	private StringBuffer generateOpenCode() {

		StringBuffer openCode = new StringBuffer();

		openCode.append("count = 0;").append("\n");

		String tmp = "sourcedata = new Array(";

		int numCol = valuesTable.get(0).size();

		int size = this.getSize();
		for (int i = 0; i < size; i++) {
			if ((i + 1) == size) {
				tmp += "new Array(" + numCol + "));";
			} else {
				tmp += "new Array(" + numCol + "),";
			}

		}

		openCode.append(tmp).append("\n\n");

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < valuesTable.get(i).size(); j++) {
				openCode.append("sourcedata[" + i + "][" + j + "]=\"" + valuesTable.get(i).get(j) + "\";").append("\n");
			}
		}

		return openCode;

	}

	private void createStyles() throws SemanticException {

		StyleHandle newStyle = designHandle.getElementFactory().newStyle("LabelHeader");
		newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_NORMAL);
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Arial Black");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "13px");
		newStyle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#0072BC");
		newStyle.setProperty(StyleHandle.COLOR_PROP, "#FFFFFF");
		designHandle.getStyles().add(newStyle);

		newStyle = designHandle.getElementFactory().newStyle("LabelRow1");
		// newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
		// newStyle.setProperty(StyleHandle.COLOR_PROP, "#009B9B");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "12px");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "1px");
		newStyle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#F7F7F7");
		designHandle.getStyles().add(newStyle);

		newStyle = designHandle.getElementFactory().newStyle("LabelRow2");
		// newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "12px");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "1px");
		newStyle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#E6E5E5");
		designHandle.getStyles().add(newStyle);

	}

	private String createTable(String templateType, int objectPosition) throws SemanticException {
		ElementFactory elementFactory = designHandle.getElementFactory();

		// create a table and set it up, add the table to the design
		TableHandle table = elementFactory.newTableItem("dataTable", datasetList.size(), 1, 1, 1);
		table.setProperty(IStyleModel.TEXT_ALIGN_PROP, DesignChoiceConstants.TEXT_ALIGN_CENTER);
		table.setWidth("100%");
		table.setDisplayName("table");
		table.setName(String.valueOf(resourceId));
		table.setProperty(TableHandle.DATA_SET_PROP, "setScripted" + datasetNum);
		
		List<Integer> gridReference;
		if (templateType.equals("blankTemplate")){
			designHandle.getBody().add(table);
		} else  if (templateType.equals("template1")){
			gridReference = FindGridReference.template1(objectPosition);
			GridHandle reportGridHandle = (GridHandle) designHandle.findElement("Grid" + gridReference.get(0));
			RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
			CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
			if (gridCellHandle.getContent().getCount() != 0 ){
				gridCellHandle.getContent().drop(gridReference.get(2));
			}
			gridCellHandle.getContent().add(table);
		} else  if (templateType.equals("template2")){
			gridReference = FindGridReference.template2(objectPosition);
			GridHandle reportGridHandle = (GridHandle) designHandle.findElement("Grid" + gridReference.get(0));
			RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(gridReference.get(1));
			CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(gridReference.get(2));
			if (gridCellHandle.getContent().getCount() != 0 ){
				gridCellHandle.getContent().drop(gridReference.get(2));
			}
			gridCellHandle.getContent().add(table);
		}
		
		// bind the data set columns to the table
		DataSetHandle dataSetHandle = (DataSetHandle) designHandle.getDataSets().get(datasetNum);
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

			for (int i = 0; i < datasetList.size(); i++) {
				cellHandle = (CellHandle) rowHandle.getCells().get(i);
				labelHandle = elementFactory.newLabel(datasetList.get(i));
				labelHandle.setText(datasetList.get(i));
				cellHandle.getContent().add(labelHandle);
				cellHandle.setStyleName("LabelHeader");
			}

		}

		{ // Add DataItems to the Detail Row
			rowHandle = (RowHandle) table.getDetail().get(0);

			int spyRow = 0;
			for (int i = 0; i < datasetList.size(); i++) {
				cellHandle = (CellHandle) rowHandle.getCells().get(i);
				dataHandle = elementFactory.newDataItem(datasetList.get(i));
				dataHandle.setResultSetColumn(datasetList.get(i));
				cellHandle.getContent().add(dataHandle);
				if (spyRow == 0) {
					cellHandle.setStyleName("LabelRow1");
					spyRow++;
				} else {
					cellHandle.setStyleName("LabelRow2");
					spyRow = 0;
				}

			}

		}

		return table.getName();

	}

	public String getTable(String templateType, int objectPosition) {

		dimension = getBirtServiceImpl().getTableDimensions(resourceId);
		idDT = Long.valueOf(dimension.get(0));
		datasetList = new ArrayList<String>();
		for (int i = 2; i < dimension.size(); i++) {
			datasetList.add(gwtConnector.getColumnNameFromContentDescriptor(idDT, dimension.get(i)));
		}
		valuesTable = getBirtServiceImpl().getRecordsWithLabel(idDT, datasetList);

		String nameTable = null;

		try {

			if (designHandle.getAllDataSources().size() == 0) {
				createDataSources();
			}
			datasetNum = designHandle.getAllDataSets().size();

			createStyles();

			createDataSets();

			nameTable = createTable(templateType, objectPosition);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nameTable;

	}

}
