package org.fao.fenix.web.modules.rainfall.server.birt;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.model.api.AutoTextHandle;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.ColumnHandle;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.ImageHandle;
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
import org.eclipse.birt.report.model.elements.AutoText;
import org.eclipse.birt.report.model.elements.MasterPage;
import org.fao.fenix.core.domain.rainfall.RainfallBean;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class CreateReport {

	ReportDesignHandle designHandle = null;

	IDesignEngine designEngine = null;

	ElementFactory designFactory = null;

	RainfallBean rainfallBean = null;

	List<RainfallBean> rainfallList = null;

	private static final Logger LOGGER = Logger.getLogger(CreateReport.class);

	public static Map<String, String> monthLabelMap;

	static {
		monthLabelMap = new HashMap<String, String>();
		monthLabelMap.put("01", "Jan");
		monthLabelMap.put("02", "Feb");
		monthLabelMap.put("03", "Mar");
		monthLabelMap.put("04", "Apr");
		monthLabelMap.put("05", "May");
		monthLabelMap.put("06", "Jun");
		monthLabelMap.put("07", "Jul");
		monthLabelMap.put("08", "Aug");
		monthLabelMap.put("09", "Sep");
		monthLabelMap.put("10", "Oct");
		monthLabelMap.put("11", "Nov");
		monthLabelMap.put("12", "Dec");
	}

	public CreateReport(List<RainfallBean> rainfallBean) {
		this.rainfallList = rainfallBean;
	}

	public String createReport() {

		SessionHandle session = BirtUtil.getDesignSessionHandle();
		String rep = BirtUtil.randomNameFile();

		try {

			designHandle = session.createDesign();
			designFactory = designHandle.getElementFactory();

			ImageHandle imageHandle = designHandle.getElementFactory().newImage("Header");
			imageHandle.setWidth("17.75cm");
			imageHandle.setHeight("1.5cm");
			imageHandle.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ImgForTemplate/rainfall_report_banner.tif\"");

			SimpleMasterPageHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");
			simpleMasterPage.setPageType("a4");
			simpleMasterPage.setOrientation("portrait");
			simpleMasterPage.setProperty(MasterPage.BOTTOM_MARGIN_PROP, "1cm");
			simpleMasterPage.setProperty(MasterPage.TOP_MARGIN_PROP, "1cm");
			simpleMasterPage.setProperty(MasterPage.LEFT_MARGIN_PROP, "1.45cm");
			simpleMasterPage.setProperty(MasterPage.RIGHT_MARGIN_PROP, "1.45cm");
			simpleMasterPage.getPageHeader().add(imageHandle);

			// add footer
			simpleMasterPage.getPageFooter().add(createFooter());

			designHandle.getMasterPages().add(simpleMasterPage);

			createDataSources();

			// createStyles();

			createDataSets();

			List<RainfallBean> countries = findAllCountries();
			for (RainfallBean b : countries)
				createBody(b.getGaul0code(), b.getGaul0label(), b.getGeneralClimate(), b.getGeneralClimateImage());

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rep);

			designHandle.close();
			Platform.shutdown();

			System.out.println("Finished Successfull");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rep.substring(0, (rep.length() - 10));
	}

	private GridHandle createFooter() throws SemanticException {

		GridHandle dataGridHandle = designFactory.newGridItem("dataGrid", 5, 1);
		dataGridHandle.setProperty(GridHandle.WIDTH_PROP, "100%");
		RowHandle row1 = (RowHandle) dataGridHandle.getRows().get(0);

		CellHandle gridCellHandle = (CellHandle) row1.getCells().get(0);
		TextItemHandle fenix = designHandle.getElementFactory().newTextItem("");
		fenix.setContentType(DesignChoiceConstants.TEXT_CONTENT_TYPE_PLAIN);
		fenix.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		fenix.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		fenix.setContent("http://lprapp08.fao.org:8080/fenix-web");
		gridCellHandle.getContent().add(fenix);

		gridCellHandle = (CellHandle) row1.getCells().get(1);
		AutoTextHandle autoTextPage = designHandle.getElementFactory().newAutoText("Pages");
		autoTextPage.setProperty(AutoText.AUTOTEXT_TYPE_PROP, "page-number");
		autoTextPage.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		autoTextPage.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		gridCellHandle.getContent().add(autoTextPage);

		gridCellHandle = (CellHandle) row1.getCells().get(2);
		TextItemHandle slash = null;
		slash = designHandle.getElementFactory().newTextItem("");
		slash.setContentType(DesignChoiceConstants.TEXT_CONTENT_TYPE_PLAIN);
		slash.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		slash.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		slash.setContent("/");
		gridCellHandle.getContent().add(slash);

		gridCellHandle = (CellHandle) row1.getCells().get(3);
		AutoTextHandle autoTextTotalPages = designHandle.getElementFactory().newAutoText("Total");
		autoTextTotalPages.setProperty(AutoText.AUTOTEXT_TYPE_PROP, "total-page");
		autoTextTotalPages.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		autoTextTotalPages.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		gridCellHandle.getContent().add(autoTextTotalPages);

		gridCellHandle = (CellHandle) row1.getCells().get(4);
		TextItemHandle date = designHandle.getElementFactory().newTextItem("");
		date.setContentType(DesignChoiceConstants.TEXT_CONTENT_TYPE_PLAIN);
		date.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");
		date.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		date.setContent("Created on " + FieldParser.parseDate(new Date()));
		date.setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
		gridCellHandle.getContent().add(date);

		ColumnHandle col = (ColumnHandle) dataGridHandle.getColumns().get(0);
		col.setProperty("width", "45%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(4);
		col.setProperty("width", "45%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(1);
		col.setProperty("width", "2%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(2);
		col.setProperty("width", "1%");
		col = (ColumnHandle) dataGridHandle.getColumns().get(3);
		col.setProperty("width", "2%");

		return dataGridHandle;
	}

	private void createDataSources() throws SemanticException {

		ScriptDataSourceHandle dataSourceHandle = designHandle.getElementFactory().newScriptDataSource("srcScripted");
		designHandle.getDataSources().add(dataSourceHandle);
	}

	private void createDataSets() throws SemanticException {

		for (int i = 0; i < rainfallList.size(); i++) {
			ScriptDataSetHandle dataSetHandle;
			dataSetHandle = designHandle.getElementFactory().newScriptDataSet("setScripted" + i);

			dataSetHandle.setDataSource("srcScripted");

			PropertyHandle computedSet = dataSetHandle.getPropertyHandle(ScriptDataSetHandle.RESULT_SET_PROP);

			ResultSetColumn resultColumn = StructureFactory.createResultSetColumn();

			// date
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(0);
			resultColumn.setColumnName("date");
			resultColumn.setDataType("string");
			computedSet.addItem(resultColumn);

			// first bar chart
			resultColumn = StructureFactory.createResultSetColumn();
			resultColumn.setPosition(1);
			resultColumn.setColumnName("value0");
			resultColumn.setDataType("decimal");
			computedSet.addItem(resultColumn);

			boolean isSecondbarChartThere = (rainfallList.get(i).getSecondIstogramValues().size() > 0);
			boolean isLineChartThere = (rainfallList.get(i).getLineValues().size() > 0);

			// second bar chart
			if (isSecondbarChartThere) {
				resultColumn = StructureFactory.createResultSetColumn();
				resultColumn.setPosition(2);
				resultColumn.setColumnName("value1");
				resultColumn.setDataType("decimal");
				computedSet.addItem(resultColumn);
			}

			// line chart
			if (isLineChartThere) {
				resultColumn = StructureFactory.createResultSetColumn();
				if (isSecondbarChartThere) {
					resultColumn.setPosition(3);
					resultColumn.setColumnName("value2");
				} else {
					resultColumn.setPosition(2);
					resultColumn.setColumnName("value1");
				}

				resultColumn.setDataType("decimal");
				computedSet.addItem(resultColumn);
			}

			// Set open( ) in code
			StringBuffer openCode = generateOpenCode(i);
			dataSetHandle.setOpen(openCode.toString());

			// Set fetch( ) in code
			StringBuffer fetchCode = generateFetchCode(i);
			dataSetHandle.setFetch(fetchCode.toString());

			designHandle.getDataSets().add(dataSetHandle);
		}

	}

	private StringBuffer generateFetchCode(int rainfallProvince) {
		StringBuffer fetchCode = new StringBuffer();
		fetchCode.append("if ( count < sourcedata.length ){").append("\n\t");
		fetchCode.append("row[\"date\"] = sourcedata[count][0];").append("\n\t");

		fetchCode.append("row[\"value0\"] = sourcedata[count][1];").append("\n\t");

		boolean isSecondbarChartThere = (rainfallList.get(rainfallProvince).getSecondIstogramValues().size() > 0);
		boolean isLineChartThere = (rainfallList.get(rainfallProvince).getLineValues().size() > 0);

		if (isSecondbarChartThere)
			fetchCode.append("row[\"value1\"] = sourcedata[count][2];").append("\n\t");

		if (isLineChartThere) {
			if (isSecondbarChartThere)
				fetchCode.append("row[\"value2\"] = sourcedata[count][3];").append("\n\t");
			else
				fetchCode.append("row[\"value1\"] = sourcedata[count][2];").append("\n\t");
		}

		fetchCode.append("count++;").append("\n\t");
		fetchCode.append("return true;").append("\n");
		fetchCode.append("} else {").append("\n\t");
		fetchCode.append("return false;").append("\n");
		fetchCode.append("};");
		return fetchCode;

	}

	private StringBuffer generateOpenCode(int rainfallProvince) {

		StringBuffer openCode = new StringBuffer();
		openCode.append("count = 0;").append("\n");
		String tmp = "sourcedata = new Array(";

		int seriesNumber = getSeriesNumber(rainfallProvince);
		int numElements = rainfallList.get(rainfallProvince).getDates().size();
		for (int i = 0; i < numElements; i++) {
			if ((i + 1) == numElements) {
				tmp += "new Array(" + seriesNumber + "));";
			} else {
				tmp += "new Array(" + seriesNumber + "),";
			}
		}

		openCode.append(tmp).append("\n\n");

		boolean isSecondbarChartThere = (rainfallList.get(rainfallProvince).getSecondIstogramValues().size() > 0);
		boolean isLineChartThere = (rainfallList.get(rainfallProvince).getLineValues().size() > 0);

//		LOGGER.info(rainfallList.get(rainfallProvince).getDates().size() + " dates");
//		LOGGER.info(rainfallList.get(rainfallProvince).getFirstIstogramValues().size() + " 1st values");
//		LOGGER.info(rainfallList.get(rainfallProvince).getSecondIstogramValues().size() + " 2nd values");
		
		for (int i = 0; i < rainfallList.get(rainfallProvince).getDates().size(); i++) {
			// dates
			openCode.append("sourcedata[" + i + "][0]=\"" + rainfallList.get(rainfallProvince).getDates().get(i) + "\";").append("\n");

			// first bar chart
			openCode.append("sourcedata[" + i + "][1]=" + rainfallList.get(rainfallProvince).getFirstIstogramValues().get(i) + ";").append("\n");

			// second bar chart
			if (isSecondbarChartThere)
				openCode.append("sourcedata[" + i + "][2]=" + rainfallList.get(rainfallProvince).getSecondIstogramValues().get(i) + ";").append("\n");

			// line chart
			if (isLineChartThere) {
				if (isSecondbarChartThere)
					openCode.append("sourcedata[" + i + "][3]=" + rainfallList.get(rainfallProvince).getLineValues().get(i) + ";").append("\n");
				else
					openCode.append("sourcedata[" + i + "][2]=" + rainfallList.get(rainfallProvince).getLineValues().get(i) + ";").append("\n");
			}

		}

		return openCode;
	}

	private int getSeriesNumber(int rainfallProvince) {
		int num = 1;
		if (rainfallList.get(rainfallProvince).getSecondIstogramValues().size() > 0)
			num++;
		if (rainfallList.get(rainfallProvince).getLineValues().size() > 0)
			num++;

		return num;
	}

	private boolean hasMaps() {
		for (RainfallBean b : rainfallList)
			if (b.isHasMap())
				return true;
		return false;
	}
	
	private boolean hasNdvi() {
		for (RainfallBean b : rainfallList)
			if (b.isHasNdvi())
				return true;
		return false;
	}

	private boolean hasCharts() {
		for (RainfallBean b : rainfallList)
			if (b.isHasChart())
				return true;
		return false;
	}

	private boolean hasTables() {
		for (RainfallBean b : rainfallList)
			if (b.isHasTable())
				return true;
		return false;
	}

	private boolean hasGeneralClimate() {
		for (RainfallBean b : rainfallList)
			if (b.isHasGeneralClimate())
				return true;
		return false;
	}

	private void createBody(String gaul0code, String gaul0label, String generalClimateText, String generalClimateImage) throws SemanticException {

//		LOGGER.info("Create body for [" + gaul0code + "] " + gaul0label);
		
		// add general climate
		if (hasGeneralClimate())
			addGeneralClimate(gaul0label, generalClimateText, generalClimateImage);

//		LOGGER.info("Adding maps? " + hasMaps());
		if (hasMaps())
			createMaps(gaul0code);
		
//		LOGGER.info("Adding NDVI? " + hasNdvi());
		if (hasNdvi())
			createNdviMaps(gaul0code);

		// add space
		TextItemHandle spaceOne = designHandle.getElementFactory().newTextItem("");
		spaceOne.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		spaceOne.setContent("<br>");
		designHandle.getBody().add(spaceOne);

		// add "Rainfall Situation" strip
		TextItemHandle chartsAndTables = designHandle.getElementFactory().newTextItem("");
		chartsAndTables.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		chartsAndTables.setContent("<div style='color: blue; background: #d0dded; color: black; width: 100%; '>3. Rainfall Situation - Charts and Tables</div>");
		designHandle.getBody().add(chartsAndTables);

		// add export options
		chartsAndTables.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#d0dded");
		chartsAndTables.setProperty(StyleHandle.COLOR_PROP, "#000000");
		chartsAndTables.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		chartsAndTables.setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
		chartsAndTables.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");

		// create one page for each GAUL code when both Table and chart are
		// selected
		if (hasCharts() && hasTables()) {
			
			Double maxScale = findMaxScale();

			for (int i = 0; i < rainfallList.size(); i++) {

				if ((rainfallList.get(i).getGaul0label() != null) && rainfallList.get(i).getGaul0label().equalsIgnoreCase(gaul0label)) {

					GridHandle table = null;

					table = designHandle.getElementFactory().newGridItem("Table", 1, 3);
					table.setWidth("99%");

					TextItemHandle space;
					space = designHandle.getElementFactory().newTextItem("");
					space.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					space.setContent("<br>");
					((CellHandle) ((RowHandle) table.getRows().get(0)).getCells().get(0)).getContent().add(space);

					TextItemHandle titleProvince;
					titleProvince = designHandle.getElementFactory().newTextItem("");
					titleProvince.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					titleProvince.setContent("<div style='font-weight:bold; font-size:14px; color:#000000;'>" + rainfallList.get(i).getTitle() + "</div>");
					((CellHandle) ((RowHandle) table.getRows().get(1)).getCells().get(0)).getContent().add(titleProvince);

					space = designHandle.getElementFactory().newTextItem("");
					space.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					space.setContent("<br>");
					((CellHandle) ((RowHandle) table.getRows().get(2)).getCells().get(0)).getContent().add(space);

					designHandle.getBody().add(table);

					TextItemHandle text = null;

					if (rainfallList.get(i).isHasChart()) {
						designHandle.getBody().add(AddChart.getChart(designHandle, rainfallList.get(i), i, maxScale));
						if (rainfallList.get(i).isHasTable()) {
							text = designHandle.getElementFactory().newTextItem("");
							text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
							text.setContent("<br><br><br>");
							designHandle.getBody().add(text);
						}

					}

					if (rainfallList.get(i).isHasTable())
						designHandle.getBody().add(AddTable.getTable(rainfallList.get(i).getTable(), designHandle));

				}

			}
		}

		// create 6 charts per page when tables are not selected
		if (hasCharts() && !hasTables()) {
			createSixChartsPerPage(gaul0code);
		}

	}

	private void createSixChartsPerPage(String gaul0code) throws SemanticException {
		List<RainfallBean> sub = new ArrayList<RainfallBean>();
		int birtIndex = 0;
		for (RainfallBean b : rainfallList) {
			if (b.getGaul0code().equals(gaul0code)) {
				sub.add(b);
				if (sub.size() == 6) {
					addChartGrid(sub, birtIndex);
					sub = new ArrayList<RainfallBean>();
					birtIndex += 6;
				}
			}
		}
		if (sub.size() > 0) {
			addChartGrid(sub, birtIndex);
		}
	}

	private Double findMaxScale() {
		Double max = 0.0;
		for (RainfallBean b : rainfallList) {
			if (b.getFirstIstogramValues() != null)
				for (String v : b.getFirstIstogramValues())
					if (v != null && !v.equals("null"))
						if (Double.valueOf(v) > max)
							max = Double.valueOf(v);
			if (b.getSecondIstogramValues() != null)
				for (String v : b.getSecondIstogramValues())
					if (v != null && !v.equals("null"))
						if (Double.valueOf(v) > max)
							max = Double.valueOf(v);
			if (b.getLineValues() != null)
				for (String v : b.getLineValues())
					if (v != null && !v.equals("null"))
						if (Double.valueOf(v) > max)
							max = Double.valueOf(v);
		}
		return max;
	}

	private void addChartGrid(List<RainfallBean> beans, int birtIndex) throws SemanticException {

		TextItemHandle spaceOne = designHandle.getElementFactory().newTextItem("");
		spaceOne.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		spaceOne.setContent("<br>");
		designHandle.getBody().add(spaceOne);

		GridHandle gridHandle = designFactory.newGridItem("dataGrid", 2, 3);
		designHandle.getBody().add(gridHandle);

		Double maxScale = findMaxScale();
		
		// map cell position
		Map<Integer, CellHandle> map = new HashMap<Integer, CellHandle>();
		map.put(0, next(gridHandle, 0, 0));
		map.put(1, next(gridHandle, 1, 0));
		map.put(2, next(gridHandle, 0, 1));
		map.put(3, next(gridHandle, 1, 1));
		map.put(4, next(gridHandle, 0, 2));
		map.put(5, next(gridHandle, 1, 2));

		// add charts to the grid
		for (int i = 0 ; i < beans.size() ; i++) {
			map.get(i).getContent().add(AddSmallCharts.getChart(designHandle, beans.get(i), birtIndex++, maxScale));
		}
		
		TextItemHandle spaceTwo = designHandle.getElementFactory().newTextItem("");
		spaceTwo.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		spaceTwo.setContent("<br>");
		designHandle.getBody().add(spaceTwo);

		// add legend
		if (beans.get(0) != null)
			addHtmlLegend(beans.get(0));
	}

	private void addHtmlLegend(RainfallBean b) throws SemanticException {

		// legend label
		TextItemHandle legendLabel = designHandle.getElementFactory().newTextItem("");
		legendLabel.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		legendLabel.setContent("<div style='font-weight: bold; font-size: 12pt; color: black;'>Legend</div><br>");
		designHandle.getBody().add(legendLabel);
		legendLabel.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		legendLabel.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");

		// legend
		TextItemHandle legendBody = designHandle.getElementFactory().newTextItem("");
		legendBody.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		legendBody.setContent(getHtmlLegendBody(b));
		designHandle.getBody().add(legendBody);
		legendBody.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");
	}

	private String getHtmlLegendBody(RainfallBean b) {
		String html = "<table width='100%' cellspacing='10'>";
		html += "<div style='font-size: 12pt; color: black;'>";
		html += "<tr>";
		html += "<td width='15%' border='1' bordercolor='black'><div style='background-color: #15428B; color: #d0dded;'>1st Timeserie</div></td>";
		html += "<td width='15%'><div style='color: black;'>" + b.getYearOneRange() + "</div></td>";
		
		if (b.getSecondIstogramValues().size() > 0) {
			html += "<td width='15%' border='1' bordercolor='black'><div style='background-color: #d0dded; color: #15428B;'>2nd Timeserie</div></td>";
			html += "<td width='15%' ><div style='color: black;'>" + b.getYearTwoRange() + "</div></td>";
		}
		if (b.getLineValues().size() > 0) {
			html += "<td width='15%' border='1' bordercolor='black'><div style='background-color: #ca1616; color: white;'>Average</div></td>";
			html += "<td width='25%'><div style='color: black;'>" + b.getAverageRange() + "</div></td>";
		}
		html += "</tr>";
		html += "</table>";
		html += "</div>";
		return html;
	}

	private CellHandle next(GridHandle gridHandle, int col, int row) {
		RowHandle rowHandle = (RowHandle) gridHandle.getRows().get(row);
		CellHandle cellHandle = (CellHandle) rowHandle.getCells().get(col);
		return cellHandle;
	}

	private void createMaps(String gaul0code) throws SemanticException {

//		Logger.getRootLogger().warn("Creating Maps for " + gaul0code);
		
		// add space
		TextItemHandle space = designHandle.getElementFactory().newTextItem("");
		space.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		space.setContent("<br>");
		designHandle.getBody().add(space);

		// add "Rainfall Situation" strip
		TextItemHandle satelliteImages = designHandle.getElementFactory().newTextItem("");
		satelliteImages.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		satelliteImages.setContent("<div style='background: #d0dded; width: 100%; font-weight: bold; '>2. Rainfall Situation - Satellite Images</div>");
		designHandle.getBody().add(satelliteImages);

		// add export options
		satelliteImages.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#d0dded");
		satelliteImages.setProperty(StyleHandle.COLOR_PROP, "#000000");
		satelliteImages.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		satelliteImages.setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
		satelliteImages.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");

		for (RainfallBean b : rainfallList) {
			if ((b.getGaul0code() != null) && b.getGaul0code().equals(gaul0code)) {
				List<String> images = new ArrayList<String>();
				LOGGER.info("b.getImages() is null? " + (b.getImages() == null));
				for (String image : b.getImages()) {
					images.add(image);
					if (images.size() == 6) {
						addGrid(images);
						images = new ArrayList<String>();
					}
				}
				if (images.size() > 0) {
					int fakes = 6 - images.size();
					for (int i = 0; i < fakes; i++)
						images.add(null);
					addGrid(images);
				}
				break;
			}
		}

	}
	
	private void createNdviMaps(String gaul0code) throws SemanticException {

//		Logger.getRootLogger().warn("Creating NDVI for " + gaul0code);
		
		// add space
		TextItemHandle space = designHandle.getElementFactory().newTextItem("");
		space.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		space.setContent("<br>");
		designHandle.getBody().add(space);

		// add "Rainfall Situation" strip
		TextItemHandle ndviImages = designHandle.getElementFactory().newTextItem("");
		ndviImages.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		ndviImages.setContent("<div style='background: #d0dded; width: 100%; font-weight: bold; '>2. Rainfall Situation - NDVI Images</div>");
		designHandle.getBody().add(ndviImages);

		// add export options
		ndviImages.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#d0dded");
		ndviImages.setProperty(StyleHandle.COLOR_PROP, "#000000");
		ndviImages.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		ndviImages.setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
		ndviImages.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");

		for (RainfallBean b : rainfallList) {
			if ((b.getGaul0code() != null) && b.getGaul0code().equals(gaul0code)) {
				List<String> images = new ArrayList<String>();
				for (String image : b.getNdviImages()) {
					images.add(image);
					if (images.size() == 6) {
						addNdviGrid(images);
						images = new ArrayList<String>();
					}
				}
				if (images.size() > 0) {
					int fakes = 6 - images.size();
					for (int i = 0; i < fakes; i++)
						images.add(null);
					addNdviGrid(images);
				}
				break;
			}
		}

	}

	private List<RainfallBean> findAllCountries() {
		List<RainfallBean> beans = new ArrayList<RainfallBean>();
		List<String> codes = new ArrayList<String>();
		for (RainfallBean b : rainfallList) {
			if ((b.getGaul0code() != null) && !codes.contains(b.getGaul0code())) {
				codes.add(b.getGaul0code());
				beans.add(b);
			}
		}
//		LOGGER.info(beans.size() + " countries found.");
		return beans;
	}

	private void addGeneralClimate(String country, String generalClimateText, String generalClimateImage) throws SemanticException {

		// add country name
		TextItemHandle countryName = designHandle.getElementFactory().newTextItem("");
		countryName.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		countryName.setContent("<div style='font-weight:bold; font-size:3em; color:#15428B;'>" + country + "</div><br>");
		designHandle.getBody().add(countryName);

		// add export options
		countryName.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");

		// add "General Climate" strip
		TextItemHandle blue = designHandle.getElementFactory().newTextItem("");
		blue.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		blue.setContent("<div style='color: blue; background: #d0dded; font-size: 12pt; font-weight: bold; color: black; width: 100%; '>1. General Climate</div>");
		designHandle.getBody().add(blue);

		// add export options
		blue.setProperty(StyleHandle.BACKGROUND_COLOR_PROP, "#d0dded");
		blue.setProperty(StyleHandle.COLOR_PROP, "#000000");
		blue.setProperty(StyleHandle.FONT_WEIGHT_PROP, "bold");
		blue.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		blue.setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");

		// add space
		TextItemHandle space = designHandle.getElementFactory().newTextItem("");
		space.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		space.setContent("<br>");
		designHandle.getBody().add(space);

		// add country map
		ImageHandle countryMap = designHandle.getElementFactory().newImage("countryMap");
		countryMap.setWidth("300px");
		countryMap.setHeight("200px");
		countryMap.setFile("\"" + generalClimateImage + "\"");
		// countryMap.setFile("\"" + Setting.getFenixPath() + "/" +
		// Setting.getBirtApplName() +
		// "/ImgForTemplate/rainfall_report_banner.tif\"");
		designHandle.getBody().add(countryMap);

		// country map box
		countryMap.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		countryMap.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP, "solid");
		countryMap.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");
		countryMap.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
		countryMap.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, "#15428B");
		countryMap.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, "#15428B");
		countryMap.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, "#15428B");
		countryMap.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, "#15428B");
		countryMap.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP, "0.75px");
		countryMap.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP, "0.75px");
		countryMap.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP, "0.75px");
		countryMap.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "0.75px");
		countryMap.setProperty(StyleHandle.MARGIN_LEFT_PROP, "150px");

		// add caption
		TextItemHandle mapCaption = designHandle.getElementFactory().newTextItem("");
		mapCaption.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		mapCaption.setContent("<em><font align='center'>" + "Administrative Boundaries (FAO Global Administrative Unit Layers 2009).<br>NOAA raingauges (Global Terrain Stations)." + "</font></em>");
		mapCaption.setWidth("750px");
		designHandle.getBody().add(mapCaption);
		mapCaption.setProperty(StyleHandle.MARGIN_LEFT_PROP, "150px");

		// add "General Climate" text
		TextItemHandle generalClimate = designHandle.getElementFactory().newTextItem("");
		generalClimate.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		generalClimate.setContent("<br>" + generalClimateText);
		designHandle.getBody().add(generalClimate);

		// page break
		generalClimate.setProperty(StyleHandle.LINE_HEIGHT_PROP, "0.35");
		generalClimate.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");
		generalClimate.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		generalClimate.setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
	}

	private void addGrid(List<String> images) throws SemanticException {

//		for (String img : images)
//			Logger.getRootLogger().warn("Add Grid: " + img);

		TextItemHandle spaceOne = designHandle.getElementFactory().newTextItem("");
		spaceOne.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		spaceOne.setContent("<br><br><br>");
		designHandle.getBody().add(spaceOne);

		GridHandle gridHandle = designFactory.newGridItem("dataGrid", 2, 6);
		designHandle.getBody().add(gridHandle);

		int imageIndex = 0;
		for (int j = 0; j < 6; j += 2) {
			CellHandle cellHandle = next(gridHandle, 0, j);
			if (imageIndex < images.size() && images.get(imageIndex) != null) {
				cellHandle.getContent().add(getMap(images.get(imageIndex)));
				cellHandle = next(gridHandle, 0, (j + 1));
				cellHandle.getContent().add(getMapLabel(images.get(imageIndex)));
				if (imageIndex < (images.size() - 1))
					imageIndex += 2;
			}
		}

		imageIndex = 1;
		for (int j = 0; j < 6; j += 2) {
			CellHandle cellHandle = next(gridHandle, 1, j);
			if (imageIndex < images.size() && images.get(imageIndex) != null) {
				cellHandle.getContent().add(getMap(images.get(imageIndex)));
				cellHandle = next(gridHandle, 1, (j + 1));
				cellHandle.getContent().add(getMapLabel(images.get(imageIndex)));
				if (imageIndex < (images.size() - 1))
					imageIndex += 2;
			}
		}

		TextItemHandle spaceTwo = designHandle.getElementFactory().newTextItem("");
		spaceTwo.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		spaceTwo.setContent("<br><br>");
		designHandle.getBody().add(spaceTwo);

		// add legend
		TextItemHandle legendLabel = designHandle.getElementFactory().newTextItem("");
		legendLabel.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		legendLabel.setContent("<div style='font-weight: bold; font-size: 12pt; color: black;'>Legend</div><br>");
		designHandle.getBody().add(legendLabel);
		legendLabel.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		legendLabel.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");

		ImageHandle legend = designHandle.getElementFactory().newImage("Header");
		legend.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ImgForTemplate/rainfall_legend.jpg\"");
		legend.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");
		legend.setWidth("10cm");
		legend.setHeight("1.5cm");
		designHandle.getBody().add(legend);
	}
	
	private void addNdviGrid(List<String> images) throws SemanticException {

//		for (String img : images)
//			Logger.getRootLogger().warn("Add NDVI: " + img);

		TextItemHandle spaceNdviOne = designHandle.getElementFactory().newTextItem("");
		spaceNdviOne.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		spaceNdviOne.setContent("<br><br><br>");
		designHandle.getBody().add(spaceNdviOne);

		GridHandle gridHandle = designFactory.newGridItem("ndviDataGrid", 2, 6);
		designHandle.getBody().add(gridHandle);

		int imageIndex = 0;
		for (int j = 0; j < 6; j += 2) {
			CellHandle cellHandle = next(gridHandle, 0, j);
			if (imageIndex < images.size() && images.get(imageIndex) != null) {
				cellHandle.getContent().add(getMap(images.get(imageIndex)));
				cellHandle = next(gridHandle, 0, (j + 1));
				cellHandle.getContent().add(getMapLabel(images.get(imageIndex)));
				if (imageIndex < (images.size() - 1))
					imageIndex += 2;
			}
		}

		imageIndex = 1;
		for (int j = 0; j < 6; j += 2) {
			CellHandle cellHandle = next(gridHandle, 1, j);
			if (imageIndex < images.size() && images.get(imageIndex) != null) {
				cellHandle.getContent().add(getMap(images.get(imageIndex)));
				cellHandle = next(gridHandle, 1, (j + 1));
				cellHandle.getContent().add(getMapLabel(images.get(imageIndex)));
				if (imageIndex < (images.size() - 1))
					imageIndex += 2;
			}
		}

		TextItemHandle spaceNdviTwo = designHandle.getElementFactory().newTextItem("");
		spaceNdviTwo.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		spaceNdviTwo.setContent("<br><br>");
		designHandle.getBody().add(spaceNdviTwo);

		// add legend
		TextItemHandle ndviLegendLabel = designHandle.getElementFactory().newTextItem("");
		ndviLegendLabel.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		ndviLegendLabel.setContent("<div style='font-weight: bold; font-size: 12pt; color: black;'>Legend</div><br>");
		designHandle.getBody().add(ndviLegendLabel);
		ndviLegendLabel.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		ndviLegendLabel.setProperty(StyleHandle.FONT_SIZE_PROP, "10pt");

		ImageHandle ndviLegend = designHandle.getElementFactory().newImage("ndviHeader");
		ndviLegend.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ImgForTemplate/legend_ndvi.jpg\"");
		ndviLegend.setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");
		ndviLegend.setWidth("15cm");
		ndviLegend.setHeight("2cm");
		designHandle.getBody().add(ndviLegend);
	}

	private ImageHandle getMap(String image) throws SemanticException {
		ImageHandle imageHandle = designHandle.getElementFactory().newImage(image);
		imageHandle.setFile("\"" + image + "\"");
		imageHandle.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
		imageHandle.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP, "solid");
		imageHandle.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");
		imageHandle.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
		imageHandle.setProperty(StyleHandle.BORDER_BOTTOM_COLOR_PROP, "#15428B");
		imageHandle.setProperty(StyleHandle.BORDER_TOP_COLOR_PROP, "#15428B");
		imageHandle.setProperty(StyleHandle.BORDER_LEFT_COLOR_PROP, "#15428B");
		imageHandle.setProperty(StyleHandle.BORDER_RIGHT_COLOR_PROP, "#15428B");
		imageHandle.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP, "0.75px");
		imageHandle.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP, "0.75px");
		imageHandle.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP, "0.75px");
		imageHandle.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "0.75px");
		return imageHandle;
	}

	private TextItemHandle getMapLabel(String image) throws SemanticException {
		TextItemHandle textHandle = designHandle.getElementFactory().newTextItem("");
		textHandle.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		textHandle.setContent("<b>" + getLabelFromDecade(parseImageName(image)) + "</b>");
		textHandle.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		textHandle.setProperty(StyleHandle.FONT_SIZE_PROP, "9pt");
		return textHandle;
	}

	private TextItemHandle getChartLabel(String image) throws SemanticException {
		TextItemHandle textHandle = designHandle.getElementFactory().newTextItem("");
		textHandle.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		// textHandle.setContent("<b>" +
		// getLabelFromDecade(parseImageName(image)) + "</b>");
		textHandle.setContent("<b>" + image + "</b>");
		textHandle.setProperty(StyleHandle.FONT_FAMILY_PROP, DesignChoiceConstants.FONT_FAMILY_SERIF);
		textHandle.setProperty(StyleHandle.FONT_SIZE_PROP, "9pt");
		return textHandle;
	}

	private String getLabelFromDecade(String decade) {
		String label = "";
		String date = String.valueOf(decade.charAt(decade.length() - 1));
		if (date.equals("1"))
			label += "1st Dekad ";
		else if (date.equals("2"))
			label += "2nd Dekad ";
		else if (date.equals("3"))
			label += "3rd Dekad ";
		label += monthLabelMap.get(decade.substring(2, 4)) + " ";
		label += "20" + decade.substring(0, 2);
		return label;
	}

	private String parseImageName(String image) {
		int from = 0;
		int to = 0;
		int count = 0;
		for (int i = 0; i < image.length(); i++) {
			if (image.charAt(i) == '_') {
				from = i;
				count++;
				if (count == 2)
					break;
			}
		}
		for (int i = from + 1; i < image.length(); i++) {
			if (image.charAt(i) == '_') {
				to = i;
				break;
			}
		}
		return image.substring(from + 3, to);
	}

}