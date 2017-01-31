package org.fao.fenix.web.modules.birt.server.utils.FPITemplate;

import java.io.File;
import java.util.List;

import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.ScriptDataSourceHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;


public class FPITemplate {

	ReportDesignHandle designHandle = null;
	IDesignEngine designEngine = null;
	ElementFactory designFactory = null;
	List<List<String>> data=null;
	String title;
	int step=1;
	int numRowForArray;
	int numArray;
	
	public FPITemplate(String title,List<List<String>> data){
		this.data=data;
		this.title=title;
		this.numRowForArray=this.getSize();
		this.numArray = this.getNumArrays();
	}
	
	
	public String createReport(String xRow, String yRow, String type) {

		SessionHandle session = BirtUtil.getDesignSessionHandle();
		String rep = BirtUtil.randomNameFile();;

		try {

			designHandle = session.createDesign();

			designFactory = designHandle.getElementFactory();

			DesignElementHandle simpleMasterPage = designFactory
					.newSimpleMasterPage("Master Page");//$NON-NLS-1$
			designHandle.getMasterPages().add(simpleMasterPage);

			createDataSources();
			
			createStyles();
			
			createDataSets(xRow, yRow);

			createBody(xRow, yRow, type);

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);
			

			designHandle.close();
			//Platform.shutdown();

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

	private void createDataSets(String xRow, String yRow) throws SemanticException {

		for (int k=-1; k<numArray; k++){
			//Data Set
			ScriptDataSetHandle dataSetHandle;
			if (k==-1){
				dataSetHandle = designHandle.getElementFactory().newScriptDataSet("setScripted");
			} else{
				dataSetHandle = designHandle.getElementFactory().newScriptDataSet("setScripted"+k);
			}
			
			dataSetHandle.setDataSource("srcScripted");

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
				if (data.get(0).get(i).equals(xRow)){
					resultColumn.setDataType("string");
				}else{
					resultColumn.setDataType("decimal");
				}
				
				
				
				
				computedSet.addItem(resultColumn);
			}
			
			// Set open( ) in code
			StringBuffer openCode = generateOpenCode(k);
			dataSetHandle.setOpen(openCode.toString());

			// Set fetch( ) in code
			StringBuffer fetchCode = generateFetchCode();
			dataSetHandle.setFetch(fetchCode.toString());
			
			designHandle.getDataSets().add(dataSetHandle);
		}
		
	}

	private StringBuffer generateFetchCode() {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		
		int size=this.getSize();
		for (int i=0; i < size; i++){
			for (int j=0; j < data.get(i).size(); j++){
				fetchCode.append("row[\""+ data.get(0).get(j) +"\"] = sourcedata[count]["+j+"];").append(
				"\n\t");
			}
			
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
	
	private StringBuffer generateOpenCode(int loop) {

		StringBuffer openCode = new StringBuffer();

		openCode.append("count = 0;").append("\n");

		String tmp="sourcedata = new Array(";
		
		int numCol=data.get(0).size();
		
		if (loop == -1){
			for (int i=1; i< data.size(); i++){
				  if ((i+1) == data.size()){
					tmp+="new Array("+numCol+"));";
				  } else{
					 tmp+="new Array("+numCol+"),";  
				  }
				}
				
				
				
				openCode.append(tmp).append("\n\n");
				
				
				for (int i=1; i< data.size(); i++){
					for (int j=0; j<data.get(i).size(); j++){
						/*
						if (j==indexDate){
							openCode.append("sourcedata["+(i-1)+"]["+j+"]=\"" + data.get(i).get(j) +"\";").append("\n");
						}else{
							openCode.append("sourcedata["+(i-1)+"]["+j+"]=\"" + data.get(i).get(j) +"\";").append("\n");
						}*/
						openCode.append("sourcedata["+(i-1)+"]["+j+"]=\"" + data.get(i).get(j) +"\";").append("\n");
					}
				}
		} else {
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
		}
		

		return openCode;

	}
	
	private void createStyles() throws SemanticException {
		
		StyleHandle newStyle = designHandle.getElementFactory().newStyle("LabelHeader");
		newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "13px");
		newStyle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#0072BC");
		newStyle.setProperty(StyleHandle.COLOR_PROP, "#FFFFFF");
		designHandle.getStyles().add(newStyle);

		newStyle = designHandle.getElementFactory().newStyle("LabelRow1");
		//newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
		//newStyle.setProperty(StyleHandle.COLOR_PROP, "#009B9B");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "12px");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#F7F7F7");
		designHandle.getStyles().add(newStyle);
		
		newStyle = designHandle.getElementFactory().newStyle("LabelRow2");
		//newStyle.setProperty(StyleHandle.FONT_WEIGHT_PROP, DesignChoiceConstants.FONT_WEIGHT_BOLD);
		newStyle.setProperty(StyleHandle.FONT_FAMILY_PROP, "Century");
		newStyle.setProperty(StyleHandle.FONT_SIZE_PROP, "12px");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		newStyle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP,"1px");
		newStyle.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#E6E5E5");
		designHandle.getStyles().add(newStyle);
		
	}
	
	private void createBody(String xRow, String yRow, String type) throws SemanticException{
		
		TextItemHandle text=null;
		text=designHandle.getElementFactory().newTextItem("");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setContent("<div align='center' style='font-weight:bold; font-size:3em; color:#000000;'>"+ this.title + "</div>");
		designHandle.getBody().add(text);
		
		designHandle.getBody().add(AddLineChart.getChart(designHandle, xRow, yRow));
		
		for (int k=0; k<numArray; k++){
			designHandle.getBody().add(AddTable.getTable(designHandle, data, k));
		}
		
		
		text=designHandle.getElementFactory().newTextItem("");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setContent("<br>");
		designHandle.getBody().add(text);
		
		String tmp="";
		if (type.equals("FOOD")){
			tmp=IndexDescription.FOOD + IndexDescription.MEAT + IndexDescription.DAIRY + IndexDescription.CEREALS + IndexDescription.OILS + IndexDescription.SUGAR; 
		} else if (type.equals("MEAT")){
			tmp=IndexDescription.MEAT;
		} else if (type.equals("DAIRY")){
			tmp=IndexDescription.DAIRY;
		} else if (type.equals("CEREALS")){
			tmp=IndexDescription.CEREALS;
		} else if (type.equals("OILS")){
			tmp=IndexDescription.OILS;
		} else if (type.equals("SUGAR")){
			tmp=IndexDescription.SUGAR;
		} 
		
		text=designHandle.getElementFactory().newTextItem("");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		text.setContent(tmp);
		designHandle.getBody().add(text);
	}
	
}
