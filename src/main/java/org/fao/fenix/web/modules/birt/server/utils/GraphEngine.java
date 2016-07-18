package org.fao.fenix.web.modules.birt.server.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.PropertyHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.ScriptDataSourceHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.SimpleMasterPageHandle;
import org.eclipse.birt.report.model.api.StructureFactory;
import org.eclipse.birt.report.model.api.StyleHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.birt.report.model.api.elements.structures.ResultSetColumn;
import org.eclipse.birt.report.model.api.extension.ExtendedElementException;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.server.BirtServiceImpl;
import org.fao.fenix.web.modules.birt.server.utils.chart.AreaGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.BarGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.BarLineGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.ConeGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.LineGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.PieGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.PyramidGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.ScatterGraph;
import org.fao.fenix.web.modules.birt.server.utils.chart.TubeGraph;
import org.fao.fenix.web.modules.re.common.services.REService;

public class GraphEngine {

	// when an user clicks apply on chartViewer I take the "style" 
	String rptDesign;
	REService reServiceImpl;
	BirtServiceImpl birtServiceImpl;
	ReportDesignHandle designHandle = null;
	IDesignEngine designEngine = null;
	ElementFactory designFactory = null;
	CodecDao codecDao;
	DatasetDao datasetDao;
	GwtConnector gwtConnector;
	MatchValue matchValue;

	private ChartWizardBean dataGWT;

	public GraphEngine() {
		
	}
	
	public GraphEngine(ChartWizardBean dataGWT, REService resourceExplorerServiceImpl) {
		this.dataGWT = dataGWT;
		this.reServiceImpl = resourceExplorerServiceImpl;
	}
	
	public GraphEngine(ChartWizardBean dataGWT, BirtServiceImpl birtServiceImpl, CodecDao codecDao, DatasetDao datasetDao, GwtConnector gwtConnector, String rptDesign) {
		this.rptDesign = rptDesign;
		this.dataGWT = dataGWT;
		this.birtServiceImpl = birtServiceImpl;
		this.codecDao  = codecDao;
		this.datasetDao = datasetDao;
		this.gwtConnector = gwtConnector;
	}
	
	public GraphEngine(ChartWizardBean dataGWT, CodecDao codecDao, DatasetDao datasetDao, GwtConnector gwtConnector, String rptDesign) {
		this.rptDesign = rptDesign;
		this.dataGWT = dataGWT;
		this.codecDao  = codecDao;
		this.datasetDao = datasetDao;
		this.gwtConnector = gwtConnector;
	}

	public String createReport() {

		SessionHandle session = BirtUtil.getDesignSessionHandle();
		String nameReport = null;

		try {

			designHandle = session.createDesign();

			designFactory = designHandle.getElementFactory();

			SimpleMasterPageHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");//$NON-NLS-1$
			simpleMasterPage.setOrientation("landscape");
			designHandle.getMasterPages().add(simpleMasterPage);

			createDataSources();
			createDataSets();
//			printData();

			createBody();

			/*
			 * RptdesignToString report = new RptdesignToString(designHandle); rep = report.getRptdesign();
			 */

			nameReport = BirtUtil.randomNameFile();
			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + nameReport);

			designHandle.close();
			//Platform.shutdown();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return nameReport;
	}
	
	private void printData(){
		System.out.println("printData(): " + dataGWT.getDimensionValuesX().size());
		for(List<String> x :dataGWT.getDimensionValuesX()) {
			System.out.println(x);
		}
		System.out.println("end printData()");
	}
	
	

	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource("srcScripted");
		designHandle.getDataSources().add(dataSourceHandle);
	}

	private void createDataSets() throws SemanticException {

		// Data Set
		ScriptDataSetHandle dataSetHandle = designHandle.getElementFactory().newScriptDataSet("setScripted");
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
			
			int numValuesY=MatchValue.getNumValuesY(dataGWT);
			for (int i = 0; i < numValuesY; i++) {
				resultColumn = StructureFactory.createResultSetColumn();
				resultColumn.setPosition((i + 1));
				resultColumn.setColumnName("value" + i);
				resultColumn.setDataType("decimal");
				computedSet.addItem(resultColumn);
			}
			
		}

		designHandle.getDataSets().add(dataSetHandle);

	}

	private StringBuffer generateFetchCode() {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		fetchCode.append("row[\"label\"] = sourcedata[count][0];").append("\n\t");

		if (dataGWT.getChartType().equals("Pie")) {
			fetchCode.append("row[\"labelY\"] = sourcedata[count][1];").append("\n\t");
			fetchCode.append("row[\"value\"] = sourcedata[count][2];").append("\n\t");
		} else {
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
			} else {
				for (List<String> ds : dataGWT.getDatasetId()){
					
					for (int i = 0; i < dataGWT.getDimensionValuesY().get(ds.get(1)).size(); i++) {
						fetchCode.append("row[\"value" + count + "\"] = sourcedata[count][" + (count + 1) + "];").append("\n\t");
						count++;
					}
							
				}
			}
			
			
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

		openCode.append(MatchValue.getValue(dataGWT, birtServiceImpl, gwtConnector));

		return openCode;

	}
	
	@SuppressWarnings("unchecked")
	public List<List<String>> getRecords(long datasetId, List<String> columnNames) {
		List originalRowList = datasetDao.getRowValues(datasetId, columnNames);
		// Copy content in a new List: original objects may be unavailable to
		// GWT.
		List<List<String>> rowList = new ArrayList<List<String>>();

		for (Object[] originalRow : (List<Object[]>) originalRowList) {
			List<String> row = new ArrayList<String>(originalRow.length);
			for (Object field : originalRow) {
				if (field == null)
					row.add("");
				else
					row.add(field.toString());
			}
			rowList.add(row);
		}
		return rowList;
	}

	private ExtendedItemHandle callGraph(String typeChart) throws ExtendedElementException {
		ExtendedItemHandle handle = null;
		if (typeChart.equals("Bar")) {
			handle = new BarGraph().createChart(designHandle, dataGWT, rptDesign);
		} else if (typeChart.equals("Line")) {
			handle = new LineGraph().createChart(designHandle, dataGWT, rptDesign);
		} else if (typeChart.equals("Scatter")) {
			handle = new ScatterGraph().createChart(designHandle, dataGWT, rptDesign);
		} else if (typeChart.equals("Pie")) {
			handle = new PieGraph().createChart(designHandle, dataGWT, rptDesign);
		} else if (typeChart.equals("Tube")) {
			handle = new TubeGraph().createChart(designHandle, dataGWT, rptDesign);
		} else if (typeChart.equals("Cone")) {
			handle = new ConeGraph().createChart(designHandle, dataGWT, rptDesign);
		} else if (typeChart.equals("Pyramid")) {
			handle = new PyramidGraph().createChart(designHandle, dataGWT, rptDesign);
		} else if (typeChart.equals("BarLine")) {
			handle = new BarLineGraph().createChart(designHandle, dataGWT, rptDesign);
		} else if (typeChart.equals("Area")) {
			handle = new AreaGraph().createChart(designHandle, dataGWT, rptDesign);
		}

		return handle;
	}

	private void createBody() {
		ElementFactory elementFactory = designHandle.getElementFactory();

		// Grid 1
		try {

			GridHandle dataGridHandle;

			//if (dataGWT.getOtherDimension().size() > 0) {
				dataGridHandle = elementFactory.newGridItem("dataGrid", 1, 2);
//			} else {
//				dataGridHandle = elementFactory.newGridItem("dataGrid", 1, 1);
//			}

			dataGridHandle.setWidth("100%");
			designHandle.getBody().add(dataGridHandle);

			// Add the pie chart to the right grid pane
			RowHandle row1 = (RowHandle) dataGridHandle.getRows().get(0);
			CellHandle gridCellHandle = (CellHandle) row1.getCells().get(0);
			gridCellHandle.setProperty(StyleHandle.TEXT_ALIGN_PROP, DesignChoiceConstants.TEXT_ALIGN_LEFT);

			gridCellHandle.getContent().add(callGraph(dataGWT.getChartType()));

			// QUICK "FIX" ON SHOWING PARAMETERS...THEY ARE NOT SHOWED WHEN THE CHART IS CREATED
/**			for (int i=0; i<dataGWT.getDatasetId().size(); i++){
				//if (values.size() > 0) {
					TextItemHandle parameter = designHandle.getElementFactory().newTextItem("Par");
					parameter.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					String DS = "";
					if (dataGWT.getDatasetId().size() > 1) DS = "DS" + (i+1) + "-";
					String textOtherDim = "<div style='font-weight:bold; font-size: 12px; color:#FF0000;'>Parameters (" + DS + dataGWT.getDatasetId().get(i).get(0) + "): </div>";
					for (List<String> values : dataGWT.getOtherDimension().get(dataGWT.getDatasetId().get(i).get(1))){
						if (values.get(2).equals("true")){
							textOtherDim += "<div style='font-style:italic; font-size: 10px;'>" + values.get(0);
							// TODO: THIS SHOULD BE GIVEN WITH A DYNAMIC LANGCODE
							textOtherDim += ": " + MatchCodeLabel.getLabel(gwtConnector, codecDao, Long.valueOf(dataGWT.getDatasetId().get(i).get(1)), values.get(1), values.get(0), "EN") + "</div>";
						}
						
					}
					parameter.setContent(textOtherDim);

					// designHandle.getBody().add(parameter);
					RowHandle row2 = (RowHandle) dataGridHandle.getRows().get(1);
					CellHandle gridCellHandle2 = (CellHandle) row2.getCells().get(0);
					gridCellHandle.setProperty(StyleHandle.TEXT_ALIGN_PROP, DesignChoiceConstants.TEXT_ALIGN_LEFT);

					gridCellHandle2.getContent().add(parameter);
				
				//}
			}
**/			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMatchValue(MatchValue matchValue) {
		this.matchValue = matchValue;
	}

	public void setRptDesign(String rptDesign) {
		this.rptDesign = rptDesign;
	}

	public void setReServiceImpl(REService reServiceImpl) {
		this.reServiceImpl = reServiceImpl;
	}

	public void setBirtServiceImpl(BirtServiceImpl birtServiceImpl) {
		this.birtServiceImpl = birtServiceImpl;
	}

	public void setDesignHandle(ReportDesignHandle designHandle) {
		this.designHandle = designHandle;
	}

	public void setDesignEngine(IDesignEngine designEngine) {
		this.designEngine = designEngine;
	}

	public void setDesignFactory(ElementFactory designFactory) {
		this.designFactory = designFactory;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setGwtConnector(GwtConnector gwtConnector) {
		this.gwtConnector = gwtConnector;
	}

	public void setDataGWT(ChartWizardBean dataGWT) {
		this.dataGWT = dataGWT;
	}

}