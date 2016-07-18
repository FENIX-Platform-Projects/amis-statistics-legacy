package org.fao.fenix.web.modules.birt.server.utils;


import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.ScriptDataSourceHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.YSerieBean;
import org.fao.fenix.web.modules.birt.server.utils.chart.AreaGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.BarGraphReport;
import org.fao.fenix.web.modules.birt.server.utils.chart.BarLineGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.ConeGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.LineGraphReport;
import org.fao.fenix.web.modules.birt.server.utils.chart.PieGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.PyramidGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.ScatterGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.TubeGraph;

public class GraphReportEngine {
	
	private Logger LOGGER = Logger.getLogger(GraphReportEngine.class);

	ReportDesignHandle designHandle = null;

	ChartWizardBean dataGWT = null;
	
	String srcScripted = null;
	
	String setScripted = null;
	
	public GraphReportEngine() {
		
	}
	
	
	
	public GraphReportEngine(ChartWizardBean dataGWT, ReportDesignHandle designHandle){
		this.designHandle = designHandle;
		this.dataGWT = dataGWT;
//		String scriptValue = BirtUtil.randomChartName();
//		this.srcScripted = "srcScripted" + scriptValue.substring(0, 3); //dataGWT.getChartReportBean().getSrcScripted();
//		this.setScripted = "setScripted" + scriptValue.substring(0, 3); //dataGWT.getChartReportBean().getSetScripted();
		this.srcScripted = dataGWT.getChartReportBean().getSrcScripted();
		this.setScripted = dataGWT.getChartReportBean().getSetScripted();
		System.out.println("srcScripted: " + srcScripted);
		System.out.println("setScripted: " + setScripted);
	}
	

	public ExtendedItemHandle getChart() {
		LOGGER.info("getChart");
		try {
			createData();
			return callGraph(dataGWT.getChartType());
		} catch (ExtendedElementException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void createData() {
		LOGGER.info("creating data");
		try {

			createDataSources();
			createChartDataSets();
			
			
		} catch (SemanticException e) {
			e.printStackTrace();
		}
	}
	
	

//	private void createDataSources() throws SemanticException {
//
//		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource("srcScripted");
//		designHandle.getDataSources().add(dataSourceHandle);
//	}


	

	private ExtendedItemHandle callGraph(String typeChart) throws ExtendedElementException {
		ExtendedItemHandle handle = null;
		System.out.println("typeChart: " + typeChart);
		if (typeChart.equals("Bar")) {
			handle = new BarGraphReport().createChart(designHandle, dataGWT);
		} else if (typeChart.equals("Line")) {
			handle = new LineGraphReport().createChart(designHandle, dataGWT);
		} else if (typeChart.equals("Scatter")) {
			handle = new ScatterGraph().createChart(designHandle, dataGWT, "");
		} else if (typeChart.equals("Pie")) {
			handle = new PieGraph().createChart(designHandle, dataGWT, "");
		} else if (typeChart.equals("Tube")) {
			handle = new TubeGraph().createChart(designHandle, dataGWT, "");
		} else if (typeChart.equals("Cone")) {
			handle = new ConeGraph().createChart(designHandle, dataGWT, "");
		} else if (typeChart.equals("Pyramid")) {
			handle = new PyramidGraph().createChart(designHandle, dataGWT, "");
		} else if (typeChart.equals("BarLine")) {
			handle = new BarLineGraph().createChart(designHandle, dataGWT, "");
		} else if (typeChart.equals("Area")) {
			handle = new AreaGraph().createChart(designHandle, dataGWT, "");
		}

		return handle;
	}
	
	
	
	
	private ScriptDataSetHandle createChartDataSets() throws SemanticException {

		// Data Set
		ScriptDataSetHandle dataSetHandle = designHandle.getElementFactory().newScriptDataSet(setScripted);
		dataSetHandle.setDataSource(srcScripted);

		// Set open( ) in code
		StringBuffer openCode = generateChartOpenCode();
		dataSetHandle.setOpen(openCode.toString());

		// Set fetch( ) in code
		StringBuffer fetchCode = generateChartFetchCode();
		dataSetHandle.setFetch(fetchCode.toString());

		PropertyHandle computedSet = dataSetHandle.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

		// Since this is a Scripted Data Source you need to tell the
		// DataSet what the columns are. For this example, we will just
		// hard code the known resultColumn values

		ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();
		resultColumn.setPosition(0);
		resultColumn.setColumnName("label");
		resultColumn.setDataType("string");
		computedSet.addItem(resultColumn);

		if (dataGWT.getChartType().equals("Pie")) {
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(1);
			resultColumn.setColumnName("labelY");
			resultColumn.setDataType("string");
			computedSet.addItem(resultColumn);

			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(2);
			resultColumn.setColumnName("value");
			resultColumn.setDataType("decimal");
			computedSet.addItem(resultColumn);
		} else {
			
//			int numValuesY=MatchValue.getNumValuesY(dataGWT);
//			for (int i = 0; i < numValuesY; i++) {
			for (int i=0; i < dataGWT.getChartReportBean().getChartValues().getySeries().size(); i++){
				resultColumn = StructureFactory.createResultSetColumn();
				resultColumn.setPosition((i + 1));
				resultColumn.setColumnName("value" + i);
				resultColumn.setDataType("decimal");
				computedSet.addItem(resultColumn);
			}
			
		}

		designHandle.getDataSets().add(dataSetHandle);
		return dataSetHandle;
	}
	
	
	
	private String createSourceData() {
		String tmp = "";
		String sD = null;
	
//		HashMap<String, String> xLabelsMap = dataGWT.getChartReportBean().getChartValues().getxValuesMap();
		List<String> xLabels = dataGWT.getChartReportBean().getChartValues().getxValues();
		List<YSerieBean> ySeries = dataGWT.getChartReportBean().getChartValues().getySeries();
		
		for (int i = 0; i < xLabels.size(); i++) {
			if ((i + 1) == xLabels.size()) {
				tmp += "new Array(" + ySeries.size() + ")";
			} else {
				tmp += "new Array(" + ySeries.size() + "),";
			}
		}

		sD = "sourcedata = new Array(" + tmp + ");";
		
		int i=0;
		for (String xlabel : xLabels) {
			for (int j = 0; j <= ySeries.size() ; j++) {
				if (j == 0) {
					sD += "sourcedata[" + i + "][" + j + "]=\""+ xlabel + "\";";
				} 
				else {
					sD += "sourcedata[" + i + "][" + j + "]="+ ySeries.get(j-1).getValues().get(i) + ";";
				}
			}
		i++;
		}
//		System.out.println("SD: " + sD + " \n\n");
		return sD;
	}
	
	
	private StringBuffer generateChartOpenCode() {
//		System.out.println("generateChartOpenCode()");

		StringBuffer openCode = new StringBuffer();

		openCode.append("count = 0;").append("\n");

		openCode.append(createSourceData());

		return openCode;

	}
	
	private StringBuffer generateChartFetchCode() {
//		System.out.println("generateChartFetchCode()");
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		fetchCode.append("row[\"label\"] = sourcedata[count][0];").append("\n\t");

		/** TODO: CHANGE WITH THE NEW SOURCES **/
		if (dataGWT.getChartType().equals("Pie")) {
			fetchCode.append("row[\"labelY\"] = sourcedata[count][1];").append("\n\t");
			fetchCode.append("row[\"value\"] = sourcedata[count][2];").append("\n\t");
		} 
		else {
			int count = 0;
			if (dataGWT.getChartType().equals("BarLine")) {
				for (List<String> ds : dataGWT.getDatasetId()){
					
					for (int i = 0; i < dataGWT.getDimensionValuesYBar().get(ds.get(1)).size(); i++) {
						fetchCode.append("row[\"value" + count + "\"] = sourcedata[count][" + (count + 1) + "];").append("\n\t");
						count++;
					}
					
					for (int i = 0; i < dataGWT.getDimensionValuesYLine().get(ds.get(1)).size(); i++) {
						fetchCode.append("row[\"value" + (dataGWT.getDimensionValuesYBar().get(ds.get(1)).size() + i) + "\"] = sourcedata[count][" + (count + 1) + "];").append("\n\t");
						count++;
					}
					
				}
			}
			
			/** TODO: that's correct **/
			else {
				for (int i=0; i < dataGWT.getChartReportBean().getChartValues().getySeries().size(); i++){
					fetchCode.append("row[\"value" + count + "\"] = sourcedata[count][" + (count + 1) + "];").append("\n\t");
					count++;	
				}
			}
			
			
		}

		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
//		System.out.println("FETCH CODE:\n " + fetchCode + "\n\n");
		
		return fetchCode;
	}
	
	
	private void createChartBody() {
		ElementFactory elementFactory = designHandle.getElementFactory();

		// Grid 1
		try {

			GridHandle dataGridHandle;

			//if (dataGWT.getOtherDimension().size() > 0) {
//				dataGridHandle = elementFactory.newGridItem("dataGrid", 1, 2);
//			} else {
				dataGridHandle = elementFactory.newGridItem("dataGrid", 1, 1);
//			}

			dataGridHandle.setWidth("100%");
			designHandle.getBody().add(dataGridHandle);

			// Add the pie chart to the right grid pane
			RowHandle row1 = (RowHandle) dataGridHandle.getRows().get(0);
			CellHandle gridCellHandle = (CellHandle) row1.getCells().get(0);
			gridCellHandle.setProperty(StyleHandle.TEXT_ALIGN_PROP, DesignChoiceConstants.TEXT_ALIGN_LEFT);

			gridCellHandle.getContent().add(callGraph(dataGWT.getChartType()));


			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	
	public void setDesignHandle(ReportDesignHandle designHandle) {
		this.designHandle = designHandle;
	}


	
	public void setDataGWT(ChartWizardBean dataGWT) {
		this.dataGWT = dataGWT;
	}
	
	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource(srcScripted);
		designHandle.getDataSources().add(dataSourceHandle);
	}

}