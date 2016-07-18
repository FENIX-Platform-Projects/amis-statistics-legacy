package org.fao.fenix.web.modules.birt.server.utils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DataItemHandle;
import org.eclipse.birt.report.model.api.DataSetHandle;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.LabelHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.ScriptDataSourceHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TableHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ComputedColumn;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.eclipse.birt.report.model.elements.MasterPage;
import org.eclipse.birt.report.model.elements.interfaces.IStyleModel;


public class TableReport {

	ReportDesignHandle designHandle = null;
	IDesignEngine designEngine = null;
	ElementFactory designFactory = null;
	List<List<String>> data=null;
	int step=1;
	int numRowForArray;
	int numArray;
	
	public TableReport(List<List<String>> data){
		this.data=data;
		this.numRowForArray=this.getSize();
		this.numArray = this.getNumArrays();
	}
		
	
	public String createReport() {

		SessionHandle session = BirtUtil.getDesignSessionHandle();
		String rep = BirtUtil.randomNameFile();;

		try {

			designHandle = session.createDesign();

			designFactory = designHandle.getElementFactory();

			DesignElementHandle simpleMasterPage = designFactory
					.newSimpleMasterPage("Master Page");//$NON-NLS-1$
			
			simpleMasterPage.setProperty(MasterPage.BOTTOM_MARGIN_PROP, "1cm");
			simpleMasterPage.setProperty(MasterPage.TOP_MARGIN_PROP, "1cm");
			simpleMasterPage.setProperty(MasterPage.LEFT_MARGIN_PROP, "1.45cm");
			simpleMasterPage.setProperty(MasterPage.RIGHT_MARGIN_PROP, "1.45cm");
			
			
			designHandle.getMasterPages().add(simpleMasterPage);

			
			createDataSources();
			
			createStyles();
			
			createDataSets();

			createBody();

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);
			

			designHandle.close();
			Platform.shutdown();

			System.out.println("Finished");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return System.getProperty("java.io.tmpdir") + File.separator + rep;
	}

	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle
				.getElementFactory().newScriptDataSource("srcScripted");
		designHandle.getDataSources().add(dataSourceHandle);
	}

	private void createDataSets() throws SemanticException {

		for (int k=0; k<numArray; k++){
			// Data Set
			ScriptDataSetHandle dataSetHandle = designHandle.getElementFactory()
					.newScriptDataSet("setScripted" + k);
			dataSetHandle.setDataSource("srcScripted");

			// Set open( ) in code
			StringBuffer openCode = generateOpenCode(k);
			dataSetHandle.setOpen(openCode.toString());

			// Set fetch( ) in code
			StringBuffer fetchCode = generateFetchCode();
			dataSetHandle.setFetch(fetchCode.toString());

			PropertyHandle computedSet = dataSetHandle
					.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

			// Since this is a Scripted Data Source you need to tell the
			// DataSet what the columns are. For this example, we will just
			// hard code the known resultColumn values

			ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();
			
			for (int i=0; i < data.get(0).size(); i++){
				resultColumn = StructureFactory.createResultSetColumn();
				resultColumn.setPosition(i);
				resultColumn.setColumnName(data.get(0).get(i));
				resultColumn.setDataType("string");
				computedSet.addItem(resultColumn);
			}
			
			designHandle.getDataSets().add(dataSetHandle);
		}
		
	}

	private StringBuffer generateFetchCode() {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		
		
		for (int j=0; j < data.get(0).size(); j++){
			fetchCode.append("row[\""+ data.get(0).get(j) +"\"] = sourcedata[count]["+j+"];").append("\n\t");
		}
						
		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
		return fetchCode;
	}
	
	private int getSize(){
		
		int numCol=data.get(0).size();
		
		for (int i=1; i< data.size(); i++){
			if ((numCol*i) > BirtUtil.TABLE_LIMIT){
				  return i;
			}
		}
		
		return data.size();
	}
	
	private StringBuffer generateOpenCode(int loop) {

		StringBuffer openCode = new StringBuffer();

		openCode.append("count = 0;").append("\n");

		String tmp="sourcedata = new Array(";
		
		int numCol=data.get(0).size();
				
		if ((loop+1)==numArray){
			for (int i=step; i< data.size(); i++){
				  if ((i+1) == data.size()){
					tmp+="new Array("+numCol+"));";
				  } else{
					 tmp+="new Array("+numCol+"),";  
				  }
			}
									
			openCode.append(tmp).append("\n\n");
			
			int contatatore=1;
			for (int i=step; i< data.size(); i++){
				for (int j=0; j<data.get(i).size(); j++){
					openCode.append("sourcedata["+(contatatore-1)+"]["+j+"]=\""+data.get(i).get(j)+"\";").append("\n");
				}
				contatatore++;
			}
		}else{
			for (int i=0; i< numRowForArray; i++){
				  if ((i+1) == numRowForArray){
					tmp+="new Array("+numCol+"));";
				  } else{
					 tmp+="new Array("+numCol+"),";  
				  }
			}
									
			openCode.append(tmp).append("\n\n");
			
			int nextStep=step+numRowForArray;
			int contatatore=1;
			for (int i=step; i< nextStep; i++){
				for (int j=0; j<data.get(i).size(); j++){
					openCode.append("sourcedata["+(contatatore-1)+"]["+j+"]=\""+data.get(i).get(j)+"\";").append("\n");
				}
				contatatore++;
			}
			
			step+=numRowForArray;
		}
		
		return openCode;

	}
	
	private void createStyles() throws SemanticException {
		
		StyleHandle newStyle = designHandle.getElementFactory().newStyle("LabelHeader");
		newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_NORMAL);
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "10px");
		newStyle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#0072BC");
		newStyle.setProperty(StyleHandle.COLOR_PROP, "#FFFFFF");
		designHandle.getStyles().add(newStyle);

		newStyle = designHandle.getElementFactory().newStyle("LabelRow1");
		//newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
		//newStyle.setProperty(StyleHandle.COLOR_PROP, "#009B9B");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "10px");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#F7F7F7");
		designHandle.getStyles().add(newStyle);
		
		newStyle = designHandle.getElementFactory().newStyle("LabelRow2");
		//newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "10px");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#E6E5E5");
		designHandle.getStyles().add(newStyle);
		
	}
	
	private int getNumArrays(){
		int numArray=0;
		float value = (float) ((data.size()-1)*data.get(0).size()) / (float) BirtUtil.TABLE_LIMIT;
		if (BirtUtil.getDecimalPart(value) > 0){
			numArray = Math.round((((data.size()*data.get(0).size()) / BirtUtil.TABLE_LIMIT) + 0.5F)); 
		} else{
			numArray = Math.round(((data.size()*data.get(0).size()) / BirtUtil.TABLE_LIMIT));
		}
		return numArray;
	}
	
	private void createBody() throws SemanticException{
		ElementFactory elementFactory = designHandle.getElementFactory();

		for (int k=0; k<numArray; k++){
			// create a table and set it up, add the table to the design
			TableHandle table = elementFactory.newTableItem("dataTable", data.get(0).size(), 1, 1, 1);
			table.setProperty(IStyleModel.TEXT_ALIGN_PROP, DesignChoiceConstants.TEXT_ALIGN_CENTER);
			table.setWidth("100%");
			table.setProperty(TableHandle.DATA_SET_PROP, "setScripted"+k);
			designHandle.getBody().add(table);

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

			if (k==0){
				{ // Create a Header Row Filled with labels
					rowHandle = (RowHandle) table.getHeader().get(0);

					for (int i=0; i<data.get(0).size(); i++){
						cellHandle = (CellHandle) rowHandle.getCells().get(i);
						labelHandle = elementFactory.newLabel(data.get(0).get(i));
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
					dataHandle = elementFactory.newDataItem(data.get(0).get(i));
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
		}
			
	}
	
}
