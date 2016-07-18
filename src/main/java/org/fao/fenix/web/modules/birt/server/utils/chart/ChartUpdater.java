/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.birt.server.utils.chart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.ChartWithoutAxes;
import org.eclipse.birt.chart.model.attribute.ChartDimension;
import org.eclipse.birt.chart.model.attribute.ColorDefinition;
import org.eclipse.birt.chart.model.attribute.NumberFormatSpecifier;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.attribute.impl.NumberFormatSpecifierImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Scale;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.NumberDataElementImpl;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.constants.PeriodTypeCode;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.perspective.ChartDatasetMapping;
import org.fao.fenix.core.domain.perspective.ChartView;
import org.fao.fenix.core.domain.perspective.DataView;
import org.fao.fenix.core.domain.perspective.Scales;
import org.fao.fenix.core.domain.perspective.SelectedField;
import org.fao.fenix.core.domain.perspective.SelectedFieldDouble;
import org.fao.fenix.core.domain.perspective.SelectedFieldList;
import org.fao.fenix.core.domain.perspective.SelectedValue;
import org.fao.fenix.core.domain.perspective.SelectedValueBar;
import org.fao.fenix.core.domain.perspective.SelectedValueLine;
import org.fao.fenix.core.domain.perspective.charting.OtherYDimension;
import org.fao.fenix.core.domain.perspective.charting.YDimension;
import org.fao.fenix.core.domain.perspective.charting.YDimensionBarLine;
import org.fao.fenix.core.domain.resourceview.ResourceView;
import org.fao.fenix.core.domain.udtable.UniqueDateValues;
import org.fao.fenix.core.domain.udtable.UniqueTextValues;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.olap.OLAPUpdater;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.SaveUniqueDao;
import org.fao.fenix.core.persistence.UniqueValuesDao;
import org.fao.fenix.core.persistence.perspective.ChartDao;
import org.fao.fenix.core.persistence.perspective.DataViewDao;
import org.fao.fenix.core.persistence.resourceview.ResourceViewDao;
import org.fao.fenix.core.utils.FieldParser;
import org.fao.fenix.web.modules.birt.client.utils.ColorPicker;
import org.fao.fenix.web.modules.birt.client.view.chart.viewer.format.FormatChart;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.DateVo;
import org.fao.fenix.web.modules.birt.common.vo.FormatChartVo;
import org.fao.fenix.web.modules.birt.server.utils.GraphEngine;
import org.fao.fenix.web.modules.birt.server.utils.RptdesignToString;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.ibm.icu.util.ULocale;

public class ChartUpdater {

	private ChartDao chartDao;

	private DataViewDao dataviewDao;

	private UniqueValuesDao uniqueValuesDao;

	private CodecDao codecDao;

	private DatasetDao datasetDao;

	private GwtConnector gwtConnector;

	private GraphEngine graphEngine;

	private SaveUniqueDao saveUniqueDao;

	private String port;

	private OLAPUpdater olapUpdater;
	
	private ResourceViewDao resourceViewDao;

	private static final Logger LOGGER = Logger.getLogger(ChartUpdater.class);

	public void run() {
		List<ChartView> charts = chartDao.getAllCharts();
		if (charts != null) {
			LOGGER.info("recreating rptdesigns on birt 'links' folder");
			for (ChartView chart : charts) {
				try {
					String fileName = chart.getResourceId().toString();
					fileName += ".rptdesign";
					// LOGGER.info("saving chart rptdesign: " + fileName);
					File out = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "report" + File.separator + "links" + File.separator + fileName);
					BufferedWriter fos = new BufferedWriter(new FileWriter(out));
					fos.write(chart.getRptdesign());
					fos.flush();
					fos.close();

					// LOGGER.info("rptdesign saved: " + fileName);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		createOlapHtmls();
	}

	private void createOlapHtmls() {
		LOGGER.info("Write OLAP HTML(s)... START");
		List<ResourceView> olaps = resourceViewDao.findAllOlaps();
		for (ResourceView rv : olaps)
			olapUpdater.writeHtmlFile(rv);
		LOGGER.info("Write OLAP HTML(s)... DONE");
	}

	public void updateDatasetCharts(Long datasetID) {
		LOGGER.info("updateDatasetCharts ID: " + datasetID);
		List<Long> chartIDs = getDatasetCharts(datasetID);
		for (Long id : chartIDs) {
			// LOGGER.info("Chart ID: " + id);
			String filename = nameFileById(id);
			// LOGGER.info("FILENAME: " + filename);
			ChartWizardBean chartWizardBean = fillChartWizardBean(id);
			// LOGGER.info("CHART TYPE: " + chartWizardBean.getChartType());
			List<Long> datasetsIDs = getDatasetIDS(chartWizardBean);
			// for (Long ds_id : datasetsIDs)
			// LOGGER.info("+---DATASET ID: " + ds_id);
			Map<String, String> dimensionsMap = getDimensionValues(datasetsIDs, chartWizardBean.getDimensionX(), "EN"); // TODO
																														// server-side,
																														// I
																														// can't
																														// check
																														// user's
																														// language
			
			
			
			
			if (chartWizardBean.isMostRecent()) {
				Date latestDate = getLatestDate(dimensionsMap);
				// LOGGER.info("+---LATEST DATE: " + latestDate);
				updateChart(chartWizardBean, latestDate, dimensionsMap, filename, id);
			} else {
				// LOGGER.info("NORMAL UPDATE");
				updateChart(chartWizardBean, dimensionsMap, filename, id);
			}

			/// save chart 
			
			
			String linkChart = linkChart(id);
			
			
			
			LOGGER.info("CHART UPDATED: " + linkChart);
		}
	}

	private void updateChart(ChartWizardBean chartWizardBean, Date latestDate, Map<String, String> dimensionsX, String rptDesign, Long dataViewID) {
		List<List<String>> dimX = new ArrayList<List<String>>();
		DateVo dateVo = chartWizardBean.getMostRecent();
		// System.out.println("DATEVO: " + dateVo.getYears() + " | " +
		// dateVo.getMonths() + " | " + dateVo.getDays());
		Date fromDate = getLastModifiedDate(Integer.valueOf(dateVo.getYears()), Integer.valueOf(dateVo.getMonths()), Integer.valueOf(dateVo.getDays()), latestDate);
		dimX = getDates(fromDate, latestDate, dimensionsX);
		chartWizardBean.setDimensionValuesX(dimX);
		
		// chart formats (quick workaround for the chart formatting)
		FormatChartVo formatChartVo = new FormatChartVo();
		FormatChartVo fcVo = getChartInfo(rptDesign, formatChartVo);
		
		List<String> result = updatePreview(chartWizardBean, "preview", rptDesign);
		
		setChartInfo(result.get(0), fcVo);

		
		saveChart(result.get(0), dataViewID, chartWizardBean);
	}

	private void updateChart(ChartWizardBean chartWizardBean, Map<String, String> dimensionsX, String rptDesign, Long dataViewID) {
		
		// chart formats (quick workaround for the chart formatting)
		FormatChartVo formatChartVo = new FormatChartVo();
		FormatChartVo fcVo = getChartInfo(rptDesign, formatChartVo);
		
		List<String> result = updatePreview(chartWizardBean, "preview", rptDesign);
			
		setChartInfo(result.get(0), fcVo);
		
		saveChart(result.get(0), dataViewID, chartWizardBean);
	}

	private List<String> updatePreview(ChartWizardBean bean, String servletType, String rptDesign) {
		List<String> result = new ArrayList<String>();
		String message;
		graphEngine.setDataGWT(bean);
		graphEngine.setCodecDao(codecDao);
		graphEngine.setDatasetDao(datasetDao);
		graphEngine.setGwtConnector(gwtConnector);
		graphEngine.setRptDesign(rptDesign);
		String rep = graphEngine.createReport();
		message = "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rep + "&servletType=" + servletType + "' width='100%' height='100%' />";
		// LOGGER.info(message);
		result.add(rep);
		result.add(message);
		return result;
	}

	public Long saveChart(String rptDesign, long dataviewId, ChartWizardBean chartWizardBean) {
		ChartView chartView = (ChartView) dataviewDao.findById(dataviewId);
		this.writeChartInfo(chartView, rptDesign, chartWizardBean);
		dataviewDao.update(chartView);
		saveChartDatasetMapping(chartView.getResourceId(), getDatasets(chartWizardBean.getDatasetId()));
		return chartView.getResourceId();
	}

	public String linkChart(Long chartId) {
		try {
			String fileName = chartId.toString();
			fileName += ".rptdesign";
			ChartView chart = chartDao.findById(chartId);
			File out = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "report" + File.separator + "links" + File.separator + fileName);
			BufferedWriter fos = new BufferedWriter(new FileWriter(out));
			fos.write(chart.getRptdesign());
			fos.flush();
			fos.close();
			String ip = UrlFinder.fenixBirtUrl;
			LOGGER.info("[ PRE-FIX] - BIRT_URL: " + ip);
			ip = fixPort(ip);
			LOGGER.info("[POST-FIX] - BIRT_URL: " + ip);
			if (ip != null) {
				String url = ip + File.separator + "preview?__report=" + "report" + File.separator + "links" + File.separator + fileName;
				return "<iframe src='" + url + " ' width='100%' height='100%' />";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String fixPort(String fenixWebUrl) {
		String newURL = fenixWebUrl;
		int idx = 0;
		int counter = 0;
		for (int i = 0; i < fenixWebUrl.length(); i++) {
			if (fenixWebUrl.charAt(i) == ':')
				counter++;
			if (counter == 2) {
				idx = i;
				break;
			}
		}
		String actualPort = fenixWebUrl.substring((1 + idx), (5 + idx));
		newURL = fenixWebUrl.substring(0, (1 + idx)) + port + fenixWebUrl.substring((idx + 5));
		return newURL;
	}

	private void saveChartDatasetMapping(Long chartID, List<Long> datasetsIDs) {
		for (Long datasetID : datasetsIDs) {
			ChartDatasetMapping cdm = new ChartDatasetMapping();
			cdm.setChartViewID(chartID);
			cdm.setDatasetID(datasetID);
			saveUniqueDao.saveUnique(cdm);
		}
	}

	private List<Long> getDatasets(List<List<String>> datasets) {
		List<Long> datasetsIDs = new ArrayList<Long>();
		for (List<String> dataset : datasets)
			datasetsIDs.add(Long.valueOf(dataset.get(1)));
		return datasetsIDs;
	}

	private void writeChartInfo(ChartView chartView, String rptDesign, ChartWizardBean chartWizardBean) {

		String rep = new RptdesignToString(rptDesign).getRptdesignLikeString();

		// X axis
		SelectedField selectedFieldX = new SelectedField();
		selectedFieldX.setFieldName(chartWizardBean.getDimensionX());
		for (int i = 0; i < chartWizardBean.getDimensionValuesX().size(); i++) {
			SelectedValue value = new SelectedValue();
			value.setSelectedValue(chartWizardBean.getDimensionValuesX().get(i).get(0));
			selectedFieldX.addsetSelectedValue(value);
		}

		YDimension yDimension;
		YDimensionBarLine yDimensionBarLine;
		SelectedFieldDouble selectedFieldDouble;
		SelectedField selectedFieldY;
		OtherYDimension otherYDimension;
		SelectedFieldList selectedFieldList;
		SelectedField selectedFieldOther;

		// clear values
		chartView.setSelectedFieldsYBarLine(new ArrayList<YDimensionBarLine>());
		chartView.setSelectedFieldsY(new ArrayList<YDimension>());
		chartView.setSelectedFieldsOtherDim(new ArrayList<OtherYDimension>());
		chartView.setScales(new ArrayList<Scales>());

		for (List<String> ds : chartWizardBean.getDatasetId()) {
			Dataset dataset = datasetDao.findById(Long.valueOf(ds.get(1)));

			// scales
			if (chartWizardBean.getScalesMap() != null) {
				Scales scales = new Scales();
				scales.setDataset(dataset);
				scales.setNumberScale(chartWizardBean.getScalesMap().get((ds.get(1))));
				chartView.addSetScales(scales);
			}

			// Y axis

			if (chartWizardBean.getChartType().equals("BarLine")) {

				yDimensionBarLine = new YDimensionBarLine();
				selectedFieldDouble = new SelectedFieldDouble();
				selectedFieldDouble.setFieldName(chartWizardBean.getDimensionY().get(ds.get(1)));
				// bar values
				for (List<String> element : chartWizardBean.getDimensionValuesYBar().get(ds.get(1))) {
					SelectedValueBar value = new SelectedValueBar();
					value.setSelectedValue(element.get(0));
					selectedFieldDouble.addsetSelectedBarValue(value);
				}
				// line values
				for (List<String> element : chartWizardBean.getDimensionValuesYLine().get(ds.get(1))) {
					SelectedValueLine value = new SelectedValueLine();
					value.setSelectedValue(element.get(0));
					selectedFieldDouble.addsetSelectedLineValue(value);
				}
				yDimensionBarLine.setDataset(dataset);
				yDimensionBarLine.setSelectedField(selectedFieldDouble);
				chartView.addsetSelectedFieldsYBarLine(yDimensionBarLine);

			} else {

				yDimension = new YDimension();
				selectedFieldY = new SelectedField();
				selectedFieldY.setFieldName(chartWizardBean.getDimensionY().get(ds.get(1)));
				for (int i = 0; i < chartWizardBean.getDimensionValuesY().get(ds.get(1)).size(); i++) {
					SelectedValue value = new SelectedValue();
					value.setSelectedValue(chartWizardBean.getDimensionValuesY().get(ds.get(1)).get(i).get(0));
					selectedFieldY.addsetSelectedValue(value);
				}

				yDimension.setDataset(dataset);
				yDimension.setSelectedField(selectedFieldY);
				chartView.addsetSelectedFieldsY(yDimension);

			}

			// other dimensions

			otherYDimension = new OtherYDimension();
			selectedFieldList = new SelectedFieldList();
			for (int j = 0; j < chartWizardBean.getOtherDimension().get(ds.get(1)).size(); j++) {
				selectedFieldOther = new SelectedField();
				selectedFieldOther.setFieldName(chartWizardBean.getOtherDimension().get(ds.get(1)).get(j).get(0));
				SelectedValue value = new SelectedValue();
				value.setSelectedValue(chartWizardBean.getOtherDimension().get(ds.get(1)).get(j).get(1));
				value.setUniqueOtherValue(chartWizardBean.getOtherDimension().get(ds.get(1)).get(j).get(2));
				selectedFieldOther.addsetSelectedValue(value);
				selectedFieldList.addsetSelectedFields(selectedFieldOther);
			}
			otherYDimension.setDataset(dataset);
			otherYDimension.setSelectedFieldList(selectedFieldList);
			chartView.addsetSelectedFieldsOtherDim(otherYDimension);

		}

		chartView.setSelectedFieldsX(selectedFieldX);

		chartView.setRptdesign(rep);

		chartView.setDoubleAxis(chartWizardBean.isDoubleScale());
		chartView.setTitleChart(chartWizardBean.getTitle());
		chartView.setChartType(chartWizardBean.getChartType());
		chartView.setFlip(chartWizardBean.isFlip());
		chartView.setPositionLegend(chartWizardBean.getPosLegend());
		chartView.setShowLegend(chartWizardBean.isShowLegend());
		chartView.setXAxisTitle(chartWizardBean.getXAxisTitle());
		chartView.setXAxisShowLabels(chartWizardBean.isXAxisShowLabels());
		chartView.setYAxisTitle(chartWizardBean.getYAxisTitle());
		chartView.setYAxisShowLabels(chartWizardBean.isYAxisShowLabels());
		chartView.setRotateXLabels(Double.valueOf(chartWizardBean.getRotateXLabels()));
		chartView.setDimension(chartWizardBean.getDim2_3D());
		chartView.setDisposition(chartWizardBean.getDisposition());

		chartView.setMostRecent(chartWizardBean.isMostRecent());

		/** setting most recent options **/
		if (chartWizardBean.isMostRecent()) {
			chartView.setYears(chartWizardBean.getMostRecent().getYears());
			chartView.setMonths(chartWizardBean.getMostRecent().getMonths());
			chartView.setDays(chartWizardBean.getMostRecent().getDays());
		} else {
			chartView.setYears("");
			chartView.setMonths("");
			chartView.setDays("");
		}

		/** adding aggregation **/
		chartView.setAggregation(chartWizardBean.getAggregation());
	}

	public static List<List<String>> getDates(Date fromDate, Date toDate, Map<String, String> dimensionx) {
		List<List<String>> result = new ArrayList<List<String>>();
		Iterator<Map.Entry<String, String>> iterator = dimensionx.entrySet().iterator();

		for (int i = 0; i < dimensionx.size(); i++) {
			Map.Entry<String, String> entry = iterator.next();
			Date d = FieldParser.parseDate(entry.getKey());
			if ((d.before(toDate) && d.after(fromDate)) || (d.compareTo(toDate) == 0) || (d.compareTo(fromDate) == 0)) {
				List<String> element = new ArrayList<String>();
				element.add(entry.getKey());
				element.add(entry.getValue());
				result.add(0, element);

			}
		}

		return result;
	}

	@SuppressWarnings("deprecation")
	private Date getLastModifiedDate(Integer years, Integer months, Integer days, Date latestDate) {
		Date result = new Date(latestDate.getYear() - years, latestDate.getMonth() - months, latestDate.getDate() - days);
		return result;
	}

	private Date getLatestDate(Map<String, String> dimensionsMap) {
		// LOGGER.info("getLatestDate: "+ dimensionsMap);
		String latestDateString = "";
		Iterator<Map.Entry<String, String>> iterator = dimensionsMap.entrySet().iterator();
		for (int i = 0; i < dimensionsMap.size(); i++) {
			Map.Entry<String, String> entry = iterator.next();
			/** this should be used when dates are taken in ASC order **/
			// if (i == dimensionsMap.size() - 1)
			/** this should be used when dates are taken in DESC order **/
			if (i == 0)
				latestDateString = entry.getKey();
		}
		LOGGER.info("latestDateString: " + latestDateString);
		Date latestDate = FieldParser.parseDate(latestDateString);
		return latestDate;
	}

	public Map<String, String> getDimensionValues(List<Long> datasetsId, String columnName, String lang) {
		Map<String, String> dimensionsMap = new LinkedHashMap<String, String>();
		Boolean isDate = false;
		// LOGGER.info("getDimensionValues(List<Long> datasetsId, String columnName, String lang)");

		for (Long datasetId : datasetsId) {
			/**
			 * TODO: should be tested, or passed to the method if the column has
			 * a coding system or not if there is not a codingsystem assosiated
			 * the query should be run without the language but getting all the
			 * codes and labels.
			 */
			System.out.println("looking for header and language ----> " + columnName + " | " + lang);
			List<UniqueTextValues> utvList = uniqueValuesDao.getText(datasetId, columnName, lang);
			List<UniqueTextValues> utvDefaultLanguage = new ArrayList<UniqueTextValues>();

			/**
			 * if there is no coding system for the used language use the
			 * english
			 **/
			// System.out.println("utvList " + utvList.size());

			if (!lang.equals("EN")) {
				utvDefaultLanguage = uniqueValuesDao.getText(datasetId, columnName, "EN");
				if (utvList.size() != utvDefaultLanguage.size()) {
					utvList = mergeCodes(utvList, utvDefaultLanguage);
				}
			}

			// System.out.println("UTVLIST: " + utvList);

			if (!utvList.isEmpty()) {
				for (UniqueTextValues u : utvList) {
					// LOGGER.info(u.getDatatype() + " | " + u.getLabel() +
					// " | " + u.getValue());
					if (!u.getLabel().isEmpty())
						dimensionsMap.put(u.getValue(), u.getLabel());
				}
			}

			if (utvList.isEmpty()) {
				List<UniqueDateValues> udv = uniqueValuesDao.getDates(datasetId, columnName);
				isDate = true;

				if (!udv.isEmpty())
					for (UniqueDateValues u : udv) {
						// LOGGER.info(u.getDatatype() + " | " + u.getDate() );
						if (u.getPeriodType().equals(PeriodTypeCode.monthly.toString()))
							dimensionsMap.put(u.getDate().toString(), parseMonthlyDate(u.getDate()));
						else if (u.getPeriodType().equals(PeriodTypeCode.yearly.toString()))
							dimensionsMap.put(u.getDate().toString(), parseYearDate(u.getDate()));
						else
							dimensionsMap.put(u.getDate().toString(), u.getDate().toString());
					}
			}
		}

		if (!isDate)
			dimensionsMap = sortByValues(dimensionsMap);
		else if (isDate && datasetsId.size() > 1) {
			dimensionsMap = sortByValuesDates(dimensionsMap);
		}

		return dimensionsMap;
	}

	// private Map<String, String> getDimensionValues(List<Long> datasetsIDs,
	// String columnName, String lang) {
	// Map<String, String> dimensionsMap = new LinkedHashMap<String, String>();
	// for (Long datasetId : datasetsIDs) {
	// List<UniqueTextValues> utv = uniqueValuesDao.getText(datasetId,
	// columnName, lang);
	// if (utv.isEmpty() && !lang.equals("EN"))
	// utv = uniqueValuesDao.getText(datasetId, columnName, "EN");
	// if (utv != null) {
	// for (UniqueTextValues u : utv) {
	// if (!u.getLabel().isEmpty())
	// dimensionsMap.put(u.getValue(), u.getLabel());
	// }
	// }
	// List<UniqueDateValues> udv = uniqueValuesDao.getDates(datasetId,
	// columnName);
	// if (udv != null) {
	// for (UniqueDateValues u : udv) {
	// if (u.getPeriodType().equals(PeriodTypeCode.monthly.toString()))
	// dimensionsMap.put(u.getDate().toString(), parseMonthlyDate(u.getDate()));
	// else if (u.getPeriodType().equals(PeriodTypeCode.yearly.toString()))
	// dimensionsMap.put(u.getDate().toString(), parseYearDate(u.getDate()));
	// else
	// dimensionsMap.put(u.getDate().toString(), u.getDate().toString());
	// }
	// }
	// }
	// dimensionsMap = sortByValues(dimensionsMap);
	// return dimensionsMap;
	// }

	private static LinkedHashMap<String, String> sortByValues(Map<String, String> in) {
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);
		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		for (int i = 0; i < size; i++)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		return out;
	}

	private static LinkedHashMap<String, String> sortByValuesDates(Map<String, String> in) {
		// LOGGER.info("IN: " + in);
		LinkedHashMap<String, String> out = new LinkedHashMap<String, String>();
		List<String> mapKeys = new ArrayList<String>(in.keySet());
		List<String> mapValues = new ArrayList<String>(in.values());
		TreeSet<String> sortedSet = new TreeSet<String>(mapValues);

		Object[] sortedArray = sortedSet.toArray();
		int size = sortedArray.length;
		// for (int i=0; i<size; i++)
		for (int i = size - 1; i >= 0; i--)
			out.put(mapKeys.get(mapValues.indexOf(sortedArray[i])), sortedArray[i].toString());
		// LOGGER.info("out: " + in);
		return out;

	}

	@SuppressWarnings("deprecation")
	private String parseMonthlyDate(Date date) {
		int year = 1900 + date.getYear();
		int month = 1 + date.getMonth();
		String y = String.valueOf(year);
		String m = String.valueOf(month);
		if (month < 10)
			m = "0" + m;
		return y + "-" + m;
	}

	@SuppressWarnings("deprecation")
	public static String parseYearDate(Date date) {
		int year = 1900 + date.getYear();
		String y = String.valueOf(year);
		return y;
	}

	private List<Long> getDatasetIDS(ChartWizardBean chartWizardBean) {
		List<Long> datasetsIDs = new ArrayList<Long>();
		for (int i = 0; i < chartWizardBean.getDatasetId().size(); i++)
			datasetsIDs.add(Long.valueOf(chartWizardBean.getDatasetId().get(i).get(1)));
		return datasetsIDs;
	}

	private ChartWizardBean fillChartWizardBean(Long chartID) {
		ChartWizardBean chartWizardBean = new ChartWizardBean();
		ChartView chartView = (ChartView) dataviewDao.findById(chartID);
		ChartWizardBeanFactory f = new ChartWizardBeanFactory();
		return f.fill(chartView, chartWizardBean, uniqueValuesDao);
	}

	private String nameFileById(Long dataviewId) {
		DataView dataView = dataviewDao.findById(dataviewId);
		File file = null;
		try {
			file = File.createTempFile("CHART_UPDATE_", ".rptdesign");
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
			out.write(dataView.getRptdesign());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FenixException("Unable to create rptdesign file for dataview " + dataView.getResourceId());
		}
		return file.getName();

	}

	private List<UniqueTextValues> mergeCodes(List<UniqueTextValues> utvList, List<UniqueTextValues> utvDefaultLanguage) {
		List<UniqueTextValues> utvToAdd = new ArrayList<UniqueTextValues>();
		for (UniqueTextValues utvDL : utvDefaultLanguage) {
			Boolean isIn = false;
			for (UniqueTextValues utv : utvList) {
				System.out.println("utvDL: " + utvDL.getValue() + " | " + utv.getValue());
				if (utv.getValue().equals(utvDL.getValue())) {
					isIn = true;
					break;
				}
			}

			if (!isIn)
				utvToAdd.add(utvDL);
		}

		if (!utvToAdd.isEmpty())
			utvList.addAll(utvToAdd);

		// System.out.println("printing utvDefaultLanguage");
		// System.out.println(utvDefaultLanguage);
		// System.out.println("printing utvToAdd");
		// System.out.println(utvToAdd);
		// System.out.println("printing utvList");
		// System.out.println(utvList);
		return utvList;
	}

	private List<Long> getDatasetCharts(Long datasetID) {
		LOGGER.info(datasetID);
		if (chartDao == null)
			LOGGER.info("is null");
		List<Long> result = chartDao.getCharts(datasetID);
		return result;
	}
	
	public FormatChartVo getChartInfo(String rptDesign, FormatChartVo formatChartVo) {
		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle design = null;
		List<List<String>> parameters = new ArrayList<List<String>>();
		try {
			design = session.openDesign(name);
			DesignElementHandle handle = (DesignElementHandle) design.findElement("NewChart");
			ExtendedItemHandle eHandle = (ExtendedItemHandle) handle;
			Chart chart = (Chart) eHandle.getReportItem().getProperty("chart.instance");

			formatChartVo.setYGrid(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getMajorGrid().getLineAttributes().isVisible());
			formatChartVo.setXGrid((((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getMajorGrid().getLineAttributes().isVisible());

			// rotation of x axis
			try {			
				Axis xAxisPrimary = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0];
				formatChartVo.setxAxixRotation(String.valueOf(xAxisPrimary.getLabel().getCaption().getFont().getRotation()));
				} catch (Exception e) {
			}
			
			
			getAreaChartInfo(chart, formatChartVo);
			getLabelChart(chart, formatChartVo);
			scaleChart(chart, "get", formatChartVo);
			legendChart(chart, "get", formatChartVo);

		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());
			System.err.println(e.getMessage());

		}

		return formatChartVo;
	}
	
	
	/**** FORMAT CHART ***/
	
	private void getAreaChartInfo(Chart chart, FormatChartVo formatChartVo) {

		ColorDefinition plotAreaBG = (ColorDefinitionImpl) chart.getPlot().getClientArea().getBackground();
		formatChartVo.setPlotAreaColor(formatColor(plotAreaBG));

		ColorDefinition chartAreaBG = (ColorDefinitionImpl) chart.getBlock().getBackground();
		formatChartVo.setChartAreaColor(formatColor(chartAreaBG));

		formatChartVo.setDimension(chart.getDimension().getLiteral());

	}
	
	public void legendChart(Chart chart, String method, FormatChartVo formatChartVo) throws IOException {

		LOGGER.info("method: " + method);
		
		if ( chart == null )
			LOGGER.info("chart is null : " + chart);
		
		else
			LOGGER.info("chart is not null : " + chart);
		
		if (method.equals("get")) {

			// Visible - Invisible
			formatChartVo.setVisible(chart.getLegend().isVisible());

			formatChartVo.setSizeLabel((int) chart.getLegend().getText().getFont().getSize());

			// position
			Position pos = chart.getLegend().getPosition();
			if (pos.getName().equals("Right")) {
				formatChartVo.setPosition("3");
			} else if (pos.getName().equals("Left")) {
				formatChartVo.setPosition("2");
			} else if (pos.getName().equals("Above")) {
				formatChartVo.setPosition("0");
			} else if (pos.getName().equals("Below")) {
				formatChartVo.setPosition("1");
			}

			LOGGER.info("position : ");
			
			if (!chart.getType().equals("Pie")) {
				formatChartVo.setUnitSpacing(String.valueOf(((ChartWithAxes) chart).getUnitSpacing()));
				formatChartVo.setFlip(((ChartWithAxes) chart).isTransposed());
			}

			LOGGER.info("Pie : ");
			
			SeriesDefinition s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(0);
			if (((Series) s.getSeries().get(0)).isStacked())
				formatChartVo.setDisposition("Stacked");
			else
				formatChartVo.setDisposition("Side by Side");

			
			LOGGER.info("setColorAndLabelFromClientToServer : ");
			
			formatChartVo.setColorAndLabelFromClientToServer(ChartColors.getChartColors(chart, "hex"));
			
			LOGGER.info("formatChartVo : ");
			
			LOGGER.info(formatChartVo.getColorAndLabelFromClientToServer());

		} else if (method.equals("set")) {
			
			LOGGER.info("position : ");

			chart.getLegend().setVisible(formatChartVo.isVisible());

			chart.getLegend().getText().getFont().setSize(formatChartVo.getSizeLabel());
			
			

			if (formatChartVo.getUnitSpacing() != null && !chart.getType().equals("Pie")) {
				((ChartWithAxes) chart).setUnitSpacing(Double.valueOf(formatChartVo.getUnitSpacing()));
				((ChartWithAxes) chart).setTransposed(formatChartVo.isFlip());
				if (formatChartVo.isFlip()) {
					((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setRotation(90);
					((Axis) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0])).getTitle().getCaption().getFont().setRotation(0);
					if (formatChartVo.isSecondScaleIsThere()) {
						Axis axis2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true)[1];
						axis2.getTitle().getCaption().getFont().setRotation(0);
					}
				} else {
					((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setRotation(0);
					((Axis) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0])).getTitle().getCaption().getFont().setRotation(90);
					if (formatChartVo.isSecondScaleIsThere()) {
						Axis axis2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true)[1];
						axis2.getTitle().getCaption().getFont().setRotation(-90);
					}
				}
			}

			if (formatChartVo.getDisposition().equals("Stacked")) {
				int numDimY = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().size();
				for (int i = 0; i < numDimY; i++) {
					SeriesDefinition s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(i);
					Series ob;
					for (int j = 0; j < s.getSeries().size(); j++) {
						ob = (Series) s.getSeries().get(j);
						ob.setStacked(true);
					}

				}
			} else if (formatChartVo.getDisposition().equals("Side by Side")) {
				int numDimY = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().size();
				for (int i = 0; i < numDimY; i++) {
					SeriesDefinition s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(i);
					Series ob;
					for (int j = 0; j < s.getSeries().size(); j++) {
						ob = (Series) s.getSeries().get(j);
						ob.setStacked(false);
					}

				}
			}

			if (formatChartVo.getPosition().equals("Right")) {
				chart.getLegend().setPosition(Position.RIGHT_LITERAL);
			} else if (formatChartVo.getPosition().equals("Left")) {
				chart.getLegend().setPosition(Position.LEFT_LITERAL);
			} else if (formatChartVo.getPosition().equals("Above")) {
				chart.getLegend().setPosition(Position.ABOVE_LITERAL);
			} else if (formatChartVo.getPosition().equals("Below")) {
				chart.getLegend().setPosition(Position.BELOW_LITERAL);
			}

			if (!chart.getType().equals("Pie")) {
				int tmp = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().size();
				if (((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getAssociatedAxes().size() > 1) {
					tmp += ((Axis) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getAssociatedAxes().get(1)).getSeriesDefinitions().size();
				}

				int y1 = 0;
				int y2 = 0;
				for (int i = 0; i < tmp; i++) {
					SeriesDefinition s;
					if (i < ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().size()) {
						s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(y1);
						y1++;
					} else {
						s = (SeriesDefinition) ((Axis) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getAssociatedAxes().get(1)).getSeriesDefinitions().get(y2);
						y2++;
					}

					((Series) s.getSeries().get(0)).setSeriesIdentifier(formatChartVo.getColorAndLabelFromClientToServer().get(i).get(0));
//					String col = formatChartVo.getColorAndLabelFromClientToServer().get(i).get(1);
//					
//					String c = convertToHexToDec(col);
//					StringTokenizer colorComp = new StringTokenizer(c, "_");
//					int r = 0;
//					int g = 0;
//					int b = 0;
//
//					int cont = 0;
//					LOGGER.info("c:" + c);
//					LOGGER.info("col:" + col);
//					LOGGER.info("colorComp:" + colorComp);
//					/** TODO REMOVED THE WHILE **/
//					while (colorComp.hasMoreTokens()) {
//						if (cont == 0) {
//							r = Integer.valueOf(colorComp.nextToken()).intValue();
//						} else if (cont == 1) {
//							g = Integer.valueOf(colorComp.nextToken()).intValue();
//						} else if (cont == 2) {
//							b = Integer.valueOf(colorComp.nextToken()).intValue();
//						}
//						cont++;
//					}
//
//					if (chart.getType().equals("Line")) {
//
//						((LineSeries) s.getSeries().get(0)).getLineAttributes().setColor(ColorDefinitionImpl.create(r, g, b));

//					} else if (chart.getType().equals("Bar/Line")) {
//						String packClass = s.getSeries().get(0).getClass().toString();
//						int index = (packClass.length()) - 1;
//						while (packClass.charAt(index) != '.') {
//							index--;
//						}
//						index++;
//						packClass = packClass.substring(index, packClass.length());
//						if (packClass.equals("LineSeriesImpl")) {
//							((LineSeries) s.getSeries().get(0)).getLineAttributes().setColor(ColorDefinitionImpl.create(r, g, b));
//						} else {
//							s.getSeriesPalette().getEntries().clear();
//							final Fill[] fiaBase = { ColorDefinitionImpl.create(r, g, b), };
//							for (int z = 0; z < fiaBase.length; z++) {
//								s.getSeriesPalette().getEntries().add(fiaBase[z]);
//							}
//						}
//					} else {
//						s.getSeriesPalette().getEntries().clear();
//						final Fill[] fiaBase = { ColorDefinitionImpl.create(r, g, b), };
//						for (int z = 0; z < fiaBase.length; z++) {
//							s.getSeriesPalette().getEntries().add(fiaBase[z]);
//						}
//					}

				}
			}

		}

	}
	
	public void scaleChart(Chart chart, String method, FormatChartVo formatChartVo) throws IOException {

		Scale scale1 = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getScale();
		Scale scale2 = null;

		int scaleNumbers = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true).length;

		if (scaleNumbers == 2) {
			scale2 = (((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true)[1]).getScale();
		}

		if (method.equals("get")) {

			formatChartVo.setAutoScale(!scale1.isSetStepNumber());
			formatChartVo.setStepNumber(String.valueOf(scale1.getStepNumber()));
			NumberFormatSpecifier format = (NumberFormatSpecifier) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getFormatSpecifier();
			if (format != null)
				formatChartVo.setFractionDigits(String.valueOf(format.getFractionDigits()));
			else
				formatChartVo.setFractionDigits(null);
			if (scale1.getMin() != null)
				formatChartVo.setMin(String.valueOf(((NumberDataElementImpl) scale1.getMin()).getValue()));
			else
				formatChartVo.setMin(null);
			if (scale1.getMax() != null)
				formatChartVo.setMax(String.valueOf(((NumberDataElementImpl) scale1.getMax()).getValue()));
			else
				formatChartVo.setMax(null);

			formatChartVo.setSecondScaleIsThere(false);

			if (scale2 != null) {

				formatChartVo.setSecondScaleIsThere(true);

				formatChartVo.setAutoScaleScale2(!scale2.isSetStepNumber());
				formatChartVo.setStepNumberScale2(String.valueOf(scale2.getStepNumber()));
				NumberFormatSpecifier format2 = (NumberFormatSpecifier) (((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true)[1]).getFormatSpecifier();
				if (format2 != null)
					formatChartVo.setFractionDigitsScale2(String.valueOf(format2.getFractionDigits()));
				else
					formatChartVo.setFractionDigitsScale2(null);
				if (scale2.getMin() != null)
					formatChartVo.setMinScale2(String.valueOf(((NumberDataElementImpl) scale2.getMin()).getValue()));
				else
					formatChartVo.setMinScale2(null);
				if (scale2.getMax() != null)
					formatChartVo.setMaxScale2(String.valueOf(((NumberDataElementImpl) scale2.getMax()).getValue()));
				else
					formatChartVo.setMaxScale2(null);

			}

		} else if (method.equals("set")) {

			if (formatChartVo.isAutoScale())
				scale1.unsetStepNumber();
			else
				scale1.setStepNumber(Integer.valueOf(formatChartVo.getStepNumber()));
			if (formatChartVo.getMin() != null) {
				scale1.setMin(NumberDataElementImpl.create(Double.valueOf(formatChartVo.getMin())));
			} else {
				scale1.setMin(null);
			}
			if (formatChartVo.getMax() != null) {
				scale1.setMax(NumberDataElementImpl.create(Double.valueOf(formatChartVo.getMax())));
			} else {
				scale1.setMax(null);
			}
			if (formatChartVo.getFractionDigits() != null) {
				NumberFormatSpecifier format = NumberFormatSpecifierImpl.create();
				format.setFractionDigits(Integer.valueOf(formatChartVo.getFractionDigits()));
				((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).setFormatSpecifier(format);
			} else
				((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).setFormatSpecifier(null);

			if (scale2 != null) {

				if (formatChartVo.isAutoScaleScale2())
					scale2.unsetStepNumber();
				else
					scale2.setStepNumber(Integer.valueOf(formatChartVo.getStepNumberScale2()));
				if (formatChartVo.getMinScale2() != null) {
					scale2.setMin(NumberDataElementImpl.create(Double.valueOf(formatChartVo.getMinScale2())));
				} else {
					scale2.setMin(null);
				}
				if (formatChartVo.getMaxScale2() != null) {
					scale2.setMax(NumberDataElementImpl.create(Double.valueOf(formatChartVo.getMaxScale2())));
				} else {
					scale2.setMax(null);
				}
				if (formatChartVo.getFractionDigitsScale2() != null) {
					NumberFormatSpecifier format = NumberFormatSpecifierImpl.create();
					format.setFractionDigits(Integer.valueOf(formatChartVo.getFractionDigitsScale2()));
					(((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true)[1]).setFormatSpecifier(format);
				} else
					(((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true)[1]).setFormatSpecifier(null);

			}

		}

	}
	
	public FormatChartVo getLabelChart(Chart chart, FormatChartVo formatChartVo) {

		formatChartVo.setTitle(chart.getTitle().getLabel().getCaption().getValue());
		formatChartVo.setTitleVisible(chart.getTitle().isVisible());
		formatChartVo.setSizeTitle((int) chart.getTitle().getLabel().getCaption().getFont().getSize());
		formatChartVo.setColorTitle(formatColor(chart.getTitle().getLabel().getCaption().getColor()));
		
		formatChartVo.setTitleIsBold(chart.getTitle().getLabel().getCaption().getFont().isBold());
		formatChartVo.setTitleIsItalic(chart.getTitle().getLabel().getCaption().getFont().isItalic());
		formatChartVo.setTitleIsUnderline(chart.getTitle().getLabel().getCaption().getFont().isUnderline());
		formatChartVo.setxTitleFont(chart.getTitle().getLabel().getCaption().getFont().getName());
		

		
		if (!chart.getType().equals("Pie")) {
			
			// X AXIS
			Boolean isBold = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().isBold();
			Boolean isItalic = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().isItalic();
			Boolean isUnderline = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().isUnderline();
			
			LOGGER.info("x FONT BOLD:"  + isBold );
			LOGGER.info("x FONT ITALIC:"  + isItalic );
			LOGGER.info("x FONT UNDERLINED:"  + isUnderline );
			
			formatChartVo.setxAxisLabelIsBold(isBold);
			formatChartVo.setxAxisLabelIsItalic(isItalic);
			formatChartVo.setxAxisLabelIsUnderline(isUnderline);
			
			formatChartVo.setxAxisTitleFont(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().getName());
			LOGGER.info("x FONT UNDERLINED:"  + formatChartVo.getxAxisTitleFont() );
			

			
			formatChartVo.setXAxisTitle(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getValue());
			formatChartVo.setXAxisVisible(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().isVisible());
			formatChartVo.setSizeXAxis((int) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().getSize());
			formatChartVo.setXAxisTitleColor(formatColor(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getColor()));

			formatChartVo.setSizeXAxisLabel((int) ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().getSize());
			formatChartVo.setXAxisLabelVisible(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().isVisible());
			formatChartVo.setXAxisLabelColor(formatColor(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getColor()));

			isBold = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().isBold();
			isItalic = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().isItalic();
			isUnderline = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().isUnderline();
			
			formatChartVo.setxAxisIsBold(isBold);
			formatChartVo.setxAxisIsItalic(isItalic);
			formatChartVo.setxAxisIsUnderline(isUnderline);
			formatChartVo.setxLabelFont(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().getName());

			LOGGER.info("x AXIS BOLD:"  + isBold );
			LOGGER.info("x AXIS ITALIC:"  + isItalic );
			LOGGER.info("x AXIS UNDERLINED:"  + isUnderline );
			LOGGER.info("x FONT UNDERLINED:"  + formatChartVo.getxLabelFont() );
			
			
			
			// Y AXIS
			
			formatChartVo.setYAxisTitle(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getValue());
			formatChartVo.setYAxisVisible(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().isVisible());
			formatChartVo.setSizeYAxis((int) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().getSize());
			formatChartVo.setYAxisTitleColor(formatColor(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getColor()));

			formatChartVo.setYAxisLabelVisible(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().isVisible());
			formatChartVo.setSizeYAxisLabel((int) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().getSize());
			formatChartVo.setYAxisLabelColor(formatColor(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getColor()));

			
			isBold = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().isBold();
			isItalic = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().isItalic();
			isUnderline = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().isUnderline();
			
			formatChartVo.setyTitleLabelIsBold(isBold);
			formatChartVo.setyTitleIsItalic(isItalic);
			formatChartVo.setyTitleIsUnderline(isUnderline);
			formatChartVo.setyTitleFont(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().getName());

			
			LOGGER.info("y TITLE BOLD:"  + formatChartVo.getyTitleLabelIsBold() );
			LOGGER.info("y TITLE ITALIC:"  + formatChartVo.getyTitleIsItalic() );
			LOGGER.info("y TITLE UNDERLINED:"  + formatChartVo.getyTitleIsUnderline() );
			LOGGER.info("y TITLE FONT:"  + formatChartVo.getyTitleFont() );
			
			
			
			isBold = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().isBold();
			isItalic = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().isItalic();
			isUnderline = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().isUnderline();
			
			formatChartVo.setyAxisLabelIsBold(isBold);
			formatChartVo.setyAxisLabelIsItalic(isItalic);
			formatChartVo.setyAxisLabelIsUnderline(isUnderline);
			formatChartVo.setyLabelFont(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().getName());
		
			LOGGER.info("y LABEL BOLD:"  + formatChartVo.getyAxisLabelIsBold() );
			LOGGER.info("y LABEL ITALIC:"  + formatChartVo.getyAxisLabelIsItalic() );
			LOGGER.info("y LABEL UNDERLINED:"  + formatChartVo.getyAxisLabelIsUnderline() );
			LOGGER.info("y LABEL FONT:"  + formatChartVo.getyLabelFont() );
			
			
			
			
			int scaleNumbers = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true).length;
			Axis axis2;

			if (scaleNumbers == 2) {
				axis2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true)[1];

				formatChartVo.setYAxisTitleScale2(axis2.getTitle().getCaption().getValue());
				formatChartVo.setYAxisVisibleScale2(axis2.getTitle().isVisible());
				formatChartVo.setSizeYAxisScale2((int) axis2.getTitle().getCaption().getFont().getSize());
				formatChartVo.setYAxisTitleColorScale2(formatColor(axis2.getTitle().getCaption().getColor()));

				formatChartVo.setYAxisLabelVisibleScale2(axis2.getLabel().isVisible());
				formatChartVo.setSizeYAxisLabelScale2((int) axis2.getLabel().getCaption().getFont().getSize());
				formatChartVo.setYAxisLabelColorScale2(formatColor(axis2.getLabel().getCaption().getColor()));

			}

			SeriesDefinition s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(0);
			Series ob;
			ob = (Series) s.getSeries().get(0);
			formatChartVo.setShowSeriesValues(ob.getLabel().isVisible());
			// bean.setSizeChartValue((int)
			// ob.getLabel().getCaption().getFont().getSize());

		}

		return formatChartVo;

	}
	
	private String formatColor(ColorDefinition colorBirtElement) {
		String color = "#";
		String r = Integer.toHexString(colorBirtElement.getRed());
		if (r.length() == 1)
			r = "0" + r;
		String g = Integer.toHexString(colorBirtElement.getGreen());
		if (g.length() == 1)
			g = "0" + g;
		String b = Integer.toHexString(colorBirtElement.getBlue());
		if (b.length() == 1)
			b = "0" + b;
		color += r + g + b;
		return color;
	}
	
	private static void updateFormatChartVo(String chartType, FormatChart formatChart, ChartWizardBean chartWizardBean){
		
		FormatChartVo formatChartVo = formatChart.getFormatChartVo();

		// colors
		String colorRGB = DOM.getStyleAttribute(formatChart.getTabChartArea().getPlotAreaColor().getElement(), "backgroundColor");
		if (!colorRGB.equals("")) {
			formatChartVo.setPlotAreaColor(ColorPicker.getColorDec(colorRGB));
		}
		else {
			formatChartVo.setPlotAreaColor(convertToHexToDec(formatChartVo.getPlotAreaColor()));
		}

		colorRGB = DOM.getStyleAttribute(formatChart.getTabChartArea().getChartAreaColor().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setChartAreaColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setChartAreaColor(convertToHexToDec(formatChartVo.getChartAreaColor()));

		if (!chartType.equals("Line") && !chartType.equals("Scatter")) {
			formatChartVo.setDimension(formatChart.getTabChartArea().getDimension().getValue(formatChart.getTabChartArea().getDimension().getSelectedIndex()));
		}

		if (!chartType.equals("Line") && !chartType.equals("Area") && !chartType.equals("Pie") && !chartType.equals("Scatter")) {
			formatChartVo.setDisposition(formatChart.getTabLegSer().getDisposition().getValue(formatChart.getTabLegSer().getDisposition().getSelectedIndex()));
		}

		// title
		formatChartVo.setTitle(formatChart.getTabTitle().getTextTitle().getValue());
		formatChartVo.setSizeTitle(Integer.valueOf(formatChart.getTabTitle().getSizeTitle().getItemText(formatChart.getTabTitle().getSizeTitle().getSelectedIndex())).intValue());
		formatChartVo.setTitleVisible(formatChart.getTabTitle().getVisibleTitle().isChecked());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabTitle().getColorTitle().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setColorTitle(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setColorTitle(convertToHexToDec(formatChartVo.getColorTitle()));
		
		formatChartVo.setTitleIsBold(formatChart.getTabTitle().getStylePanel().getBold().getValue());
		formatChartVo.setTitleIsItalic(formatChart.getTabTitle().getStylePanel().getItalic().getValue());
		formatChartVo.setTitleIsUnderline(formatChart.getTabTitle().getStylePanel().getUnderline().getValue());
		
		

		// X Axis
		formatChartVo.setxAxixRotation(formatChart.getTabAxes().getRotateXLabels().getValue());
		
		formatChartVo.setXAxisTitle(formatChart.getTabAxes().getTextXAxisTitle().getValue());
		formatChartVo.setXAxisVisible(formatChart.getTabAxes().getVisibleXAxisTitle().isChecked());
		formatChartVo.setXGrid(formatChart.getTabAxes().getXGrid().isChecked());
		formatChartVo.setSizeXAxis(Integer.valueOf(formatChart.getTabAxes().getSizeXAxisTitle().getItemText(formatChart.getTabAxes().getSizeXAxisTitle().getSelectedIndex())).intValue());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorXAxisTitle().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setXAxisTitleColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setXAxisTitleColor(convertToHexToDec(formatChartVo.getXAxisTitleColor()));

		formatChartVo.setXAxisLabelVisible(formatChart.getTabAxes().getVisibleXAxisLabel().isChecked());
		formatChartVo.setSizeXAxisLabel(Integer.valueOf(formatChart.getTabAxes().getSizeXAxisLabel().getItemText(formatChart.getTabAxes().getSizeXAxisLabel().getSelectedIndex())).intValue());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorXAxisLabel().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setXAxisLabelColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setXAxisLabelColor(convertToHexToDec(formatChartVo.getXAxisLabelColor()));

		// Y Axis
		formatChartVo.setYAxisTitle(formatChart.getTabAxes().getTextYAxisTitle().getValue());
		formatChartVo.setYAxisVisible(formatChart.getTabAxes().getVisibleYAxisTitle().isChecked());
		formatChartVo.setSizeYAxis(Integer.valueOf(formatChart.getTabAxes().getSizeYAxisTitle().getItemText(formatChart.getTabAxes().getSizeYAxisTitle().getSelectedIndex())).intValue());
		formatChartVo.setYGrid(formatChart.getTabAxes().getYGrid().isChecked());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorYAxisTitle().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setYAxisTitleColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setYAxisTitleColor(convertToHexToDec(formatChartVo.getYAxisTitleColor()));

		formatChartVo.setYAxisLabelVisible(formatChart.getTabAxes().getVisibleYAxisLabel().isChecked());
		formatChartVo.setSizeYAxisLabel(Integer.valueOf(formatChart.getTabAxes().getSizeYAxisLabel().getItemText(formatChart.getTabAxes().getSizeYAxisLabel().getSelectedIndex())).intValue());
		colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorYAxisLabel().getElement(), "backgroundColor");
		if (!colorRGB.equals(""))
			formatChartVo.setYAxisLabelColor(ColorPicker.getColorDec(colorRGB));
		else
			formatChartVo.setYAxisLabelColor(convertToHexToDec(formatChartVo.getYAxisLabelColor()));

		// Y Axis (Second Scale)
		if (formatChartVo.isSecondScaleIsThere()) {
			formatChartVo.setYAxisTitleScale2(formatChart.getTabAxes().getTextY2AxisTitle().getValue());
			formatChartVo.setYAxisVisibleScale2(formatChart.getTabAxes().getVisibleY2AxisTitle().isChecked());
			formatChartVo.setSizeYAxisScale2(Integer.valueOf(formatChart.getTabAxes().getSizeY2AxisTitle().getItemText(formatChart.getTabAxes().getSizeY2AxisTitle().getSelectedIndex())).intValue());
			colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorY2AxisTitle().getElement(), "backgroundColor");
			if (!colorRGB.equals(""))
				formatChartVo.setYAxisTitleColorScale2(ColorPicker.getColorDec(colorRGB));
			else
				formatChartVo.setYAxisTitleColorScale2(convertToHexToDec(formatChartVo.getYAxisTitleColorScale2()));

			formatChartVo.setYAxisLabelVisibleScale2(formatChart.getTabAxes().getVisibleY2AxisLabel().isChecked());
			formatChartVo.setSizeYAxisLabelScale2(Integer.valueOf(formatChart.getTabAxes().getSizeY2AxisLabel().getItemText(formatChart.getTabAxes().getSizeY2AxisLabel().getSelectedIndex())).intValue());
			colorRGB = DOM.getStyleAttribute(formatChart.getTabAxes().getColorY2AxisLabel().getElement(), "backgroundColor");
			if (!colorRGB.equals(""))
				formatChartVo.setYAxisLabelColorScale2(ColorPicker.getColorDec(colorRGB));
			else
				formatChartVo.setYAxisLabelColorScale2(convertToHexToDec(formatChartVo.getYAxisLabelColorScale2()));
		}

		// First Scale
		formatChartVo.setAutoScale(formatChart.getTabAxes().getAutoStep().isChecked());
		formatChartVo.setStepNumber(formatChart.getTabAxes().getStepNumber().getValue());
		formatChartVo.setMin(formatChart.getTabAxes().getMinScale().getValue());
		formatChartVo.setMax(formatChart.getTabAxes().getMaxScale().getValue());
		formatChartVo.setFractionDigits(formatChart.getTabAxes().getFractionDigits().getValue());

		// Second Scale
		if (formatChartVo.isSecondScaleIsThere()) {
			formatChartVo.setAutoScaleScale2(formatChart.getTabAxes().getAutoStep2().isChecked());
			formatChartVo.setStepNumberScale2(formatChart.getTabAxes().getStepNumber2().getValue());
			formatChartVo.setMinScale2(formatChart.getTabAxes().getMinScale2().getValue());
			formatChartVo.setMaxScale2(formatChart.getTabAxes().getMaxScale2().getValue());
			formatChartVo.setFractionDigitsScale2(formatChart.getTabAxes().getFractionDigits2().getValue());
		}

		// Legend and Series
		formatChartVo.setVisible(formatChart.getTabLegSer().getChBoxLabel().isChecked());
		formatChartVo.setSizeLabel(Integer.valueOf(formatChart.getTabLegSer().getSizeLabel().getItemText(formatChart.getTabLegSer().getSizeLabel().getSelectedIndex())).intValue());
		formatChartVo.setPosition(formatChart.getTabLegSer().getPosition().getItemText(formatChart.getTabLegSer().getPosition().getSelectedIndex()));
		if (!chartType.equals("Line") && !chartType.equals("Area") && !chartType.equals("Pie") && !chartType.equals("Scatter")) {
			formatChartVo.setUnitSpacing(formatChart.getTabLegSer().getUnitSpace().getValue());
		}

		if (!chartType.equals("Pie")) {
			formatChartVo.setFlip(formatChart.getTabAxes().getFlip().isChecked());
			formatChartVo.setShowSeriesValues(formatChart.getTabAxes().getShowValue().isChecked());
		}

		HorizontalPanel hr;
		IconButton b;
		formatChartVo.getColorAndLabelFromClientToServer().clear();
		List<String> element;
		int numWidgets = formatChart.getTabLegSer().getColorCont().getWidgetCount();
		for (int i = 0; i < numWidgets; i++) {
			element = new ArrayList<String>();
			hr = (HorizontalPanel) formatChart.getTabLegSer().getColorCont().getWidget(i);
			element.add(((TextField<String>) hr.getWidget(0)).getValue());
			b = (IconButton) hr.getWidget(1);
			element.add(ColorPicker.getColorDec(DOM.getStyleAttribute(b.getElement(), "backgroundColor")));
			formatChartVo.addSetColorAndLabelFromClientToServer(element);
		}
		
		
		/// updatating the chartWizardBean (the style and the rotation)
		chartWizardBean.setTitleIsBold(formatChart.getTabTitle().getStylePanel().getBold().getValue());
		chartWizardBean.setTitleIsItalic(formatChart.getTabTitle().getStylePanel().getItalic().getValue());
		chartWizardBean.setTitleIsUnderline(formatChart.getTabTitle().getStylePanel().getUnderline().getValue());
		
		chartWizardBean.setRotateXLabels(formatChartVo.getxAxixRotation());
		
	}
	
	private static String convertToHexToDec(String originalFormat) {
		LOGGER.info("CONVERTING: " + originalFormat);

		String r = String.valueOf(Integer.parseInt(originalFormat.substring(1, 3), 16));
		String g = String.valueOf(Integer.parseInt(originalFormat.substring(3, 5), 16));
		String b = String.valueOf(Integer.parseInt(originalFormat.substring(5, 7), 16));
		
		LOGGER.info("-> CONVERTED: " + r + "_" + g + "_" + b);

		return r + "_" + g + "_" + b;
	}
	
	
	
	
	public String setChartInfo(String rptDesign, FormatChartVo formatChartVo) {
		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());
		

		

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.

		LOGGER.info("before :");
		
		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		
		LOGGER.info("setChartInfo:" + rptDesign);
		LOGGER.info("name:" + name);
		
		
		ReportDesignHandle designHandle = null;
		List<List<String>> parameters = new ArrayList<List<String>>();
		try {
			LOGGER.info("session.createDesign()");
//			designHandle = session.createDesign();

			LOGGER.info("session.openDesign(name)");
			designHandle = session.openDesign(name);
			
			LOGGER.info("handle");
			DesignElementHandle handle = (DesignElementHandle) designHandle.findElement("NewChart");
			ExtendedItemHandle eHandle = (ExtendedItemHandle) handle;
			
			LOGGER.info("chart");
			Chart chart = (Chart) eHandle.getReportItem().getProperty("chart.instance");

			LOGGER.info("plotAreaColor");
			List<Integer> plotAreaColor = formatColorForBirt(convertToHexToDec(formatChartVo.getPlotAreaColor()));
			LOGGER.info("List<Integer> plotAreaColor");
			chart.getPlot().getClientArea().setBackground(ColorDefinitionImpl.create(plotAreaColor.get(0), plotAreaColor.get(1), plotAreaColor.get(2)));

			LOGGER.info("chartAreaColor");
			List<Integer> chartAreaColor = formatColorForBirt(convertToHexToDec(formatChartVo.getChartAreaColor()));
			chart.getBlock().setBackground(ColorDefinitionImpl.create(chartAreaColor.get(0), chartAreaColor.get(1), chartAreaColor.get(2)));
//
			LOGGER.info("Two_Dimensional");
			if (formatChartVo.getDimension().equals("Two_Dimensional")) {
				chart.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
			} else if (formatChartVo.getDimension().equals("Two_Dimensional_With_Depth")) {
				chart.setDimension(ChartDimension.TWO_DIMENSIONAL_WITH_DEPTH_LITERAL);
			}
//
			LOGGER.info("getPrimaryOrthogonalAxis");
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getMajorGrid().getLineAttributes().setVisible(formatChartVo.isYGrid());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getMajorGrid().getLineAttributes().setVisible(formatChartVo.isXGrid());

			LOGGER.info("xAxisPrimary");
			try {
				Axis xAxisPrimary = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0];
				xAxisPrimary.getLabel().getCaption().getFont().setRotation(Double.valueOf(formatChartVo.getxAxixRotation()));
			} catch (Exception e) {
			}
//				
			LOGGER.info("setLabelChart");
			setLabelChart(chart, formatChartVo);
//			
			LOGGER.info("scaleChart");
			scaleChart(chart, "set", formatChartVo);
			
			LOGGER.info("legendChart");
			legendChart(chart, "set", formatChartVo);

			designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
	}
	
	public void setLabelChart(Chart chart, FormatChartVo formatChartVo) {

		if (formatChartVo.getTitle() != null)
			chart.getTitle().getLabel().getCaption().setValue(formatChartVo.getTitle());
		else
			chart.getTitle().getLabel().getCaption().setValue("");
		chart.getTitle().setVisible(formatChartVo.isTitleVisible());
		chart.getTitle().getLabel().getCaption().getFont().setSize(formatChartVo.getSizeTitle());
		List<Integer> colorTitle = formatColorForBirt(convertToHexToDec(formatChartVo.getColorTitle()));
		chart.getTitle().getLabel().getCaption().setColor(ColorDefinitionImpl.create(colorTitle.get(0), colorTitle.get(1), colorTitle.get(2)));
	
		chart.getTitle().getLabel().getCaption().getFont().setBold(formatChartVo.getTitleIsBold());
		chart.getTitle().getLabel().getCaption().getFont().setItalic(formatChartVo.getTitleIsItalic());
		chart.getTitle().getLabel().getCaption().getFont().setUnderline(formatChartVo.getTitleIsUnderline());
		
		// x titlefont
		LOGGER.info("TITLE FONT TYPE:"  +formatChartVo.getxTitleFont() );
		chart.getTitle().getLabel().getCaption().getFont().setName(formatChartVo.getxTitleFont());
		LOGGER.info("TITLE FONT TYPE NAME:"  +chart.getTitle().getLabel().getCaption().getFont().getName() );
		
		if (!chart.getType().equals("Pie")) {
			
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setBold(formatChartVo.getxAxisLabelIsBold());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setItalic(formatChartVo.getxAxisLabelIsItalic());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setUnderline(formatChartVo.getxAxisLabelIsUnderline());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setName(formatChartVo.getxAxisTitleFont());

			
			LOGGER.info("x TITLE FONT BOLD:"  + formatChartVo.getxAxisLabelIsBold() );
			LOGGER.info("x TITLE FONT ITALIC:"  + formatChartVo.getxAxisLabelIsItalic() );
			LOGGER.info("x TITLE FONT UNDERLINED:"  + formatChartVo.getxAxisLabelIsUnderline() );
			LOGGER.info("x TITLE FONT :"  + formatChartVo.getxAxisTitleFont() );

			
			
			if (formatChartVo.getXAxisTitle() != null)
				((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().setValue(formatChartVo.getXAxisTitle());
			else
				((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().setValue("");
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().setVisible(formatChartVo.isXAxisVisible());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setSize(formatChartVo.getSizeXAxis());
			String color = convertToHexToDec(formatChartVo.getXAxisTitleColor());
			LOGGER.info("COLOR: " + color);
			List<Integer> colorXAxisTitle = formatColorForBirt(color);
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().setColor(ColorDefinitionImpl.create(colorXAxisTitle.get(0), colorXAxisTitle.get(1), colorXAxisTitle.get(2)));

			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setSize(formatChartVo.getSizeXAxisLabel());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().setVisible(formatChartVo.isXAxisLabelVisible());
			List<Integer> colorXAxisLabel = formatColorForBirt(convertToHexToDec(formatChartVo.getXAxisLabelColor()));
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().setColor(ColorDefinitionImpl.create(colorXAxisLabel.get(0), colorXAxisLabel.get(1), colorXAxisLabel.get(2)));

			// AXIS SETTINGS
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setBold(formatChartVo.getxAxisIsBold());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setItalic(formatChartVo.getxAxisIsItalic());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setUnderline(formatChartVo.getxAxisIsUnderline());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setName(formatChartVo.getxLabelFont());

			
			LOGGER.info("x AXIS FONT BOLD:"  + formatChartVo.getxAxisIsBold() );
			LOGGER.info("x AXIS FONT ITALIC:"  + formatChartVo.getxAxisIsItalic() );
			LOGGER.info("x AXIS FONT UNDERLINED:"  + formatChartVo.getxAxisIsUnderline() );
			LOGGER.info("x AXIS FONT FONT:"  + formatChartVo.getxLabelFont() );
			
			

			// y style title
			LOGGER.info("SETTINGS getyTitleLabelIsBold(): " + formatChartVo.getyTitleLabelIsBold());
			LOGGER.info("SETTINGS getyTitleIsItalic(): " + formatChartVo.getyTitleIsItalic());
			LOGGER.info("SETTINGS getyTitleIsUnderline(): " + formatChartVo.getyTitleIsUnderline());
			LOGGER.info("SETTINGS getyTitlefont(): " + formatChartVo.getyTitleFont());

			
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().setBold(formatChartVo.getyTitleLabelIsBold());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().setItalic(formatChartVo.getyTitleIsItalic());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().setUnderline(formatChartVo.getyTitleIsUnderline());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().setName(formatChartVo.getyTitleFont());

			if (formatChartVo.getYAxisTitle() != null)
				((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().setValue(formatChartVo.getYAxisTitle());
			else
				((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().setValue("");
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().setVisible(formatChartVo.isYAxisVisible());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().setSize(formatChartVo.getSizeYAxis());
			List<Integer> colorYAxisTitle = formatColorForBirt(convertToHexToDec(formatChartVo.getYAxisTitleColor()));
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().setColor(ColorDefinitionImpl.create(colorYAxisTitle.get(0), colorYAxisTitle.get(1), colorYAxisTitle.get(2)));

			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().setVisible(formatChartVo.isYAxisLabelVisible());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().setSize(formatChartVo.getSizeYAxisLabel());
			List<Integer> colorYAxisLabel = formatColorForBirt(convertToHexToDec(formatChartVo.getYAxisLabelColor()));
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().setColor(ColorDefinitionImpl.create(colorYAxisLabel.get(0), colorYAxisLabel.get(1), colorYAxisLabel.get(2)));


			// y style label
			LOGGER.info("SETTINGS getyAxisLabelIsBold(): " + formatChartVo.getyAxisLabelIsBold());
			LOGGER.info("SETTINGS getyAxisLabelIsItalic(): " + formatChartVo.getyAxisLabelIsItalic());
			LOGGER.info("SETTINGS getyAxisLabelIsUnderline(): " + formatChartVo.getyAxisLabelIsUnderline());
			LOGGER.info("SETTINGS getyAxisLabelfont(): " + formatChartVo.getyLabelFont());

			
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().setBold(formatChartVo.getyAxisLabelIsBold());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().setItalic(formatChartVo.getyAxisLabelIsItalic());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().setUnderline(formatChartVo.getyAxisLabelIsUnderline());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().setName(formatChartVo.getyLabelFont());

			int scaleNumbers = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true).length;
			Axis axis2;


			if (scaleNumbers == 2) {

				axis2 = ((ChartWithAxes) chart).getOrthogonalAxes(((ChartWithAxes) chart).getBaseAxes()[0], true)[1];

				if (formatChartVo.getYAxisTitleScale2() != null)
					axis2.getTitle().getCaption().setValue(formatChartVo.getYAxisTitleScale2());
				else
					axis2.getTitle().getCaption().setValue("");
				axis2.getTitle().setVisible(formatChartVo.isYAxisVisibleScale2());
				axis2.getTitle().getCaption().getFont().setSize(formatChartVo.getSizeYAxisScale2());
				List<Integer> colorY2AxisTitle = formatColorForBirt(convertToHexToDec(formatChartVo.getYAxisTitleColorScale2()));
				axis2.getTitle().getCaption().setColor(ColorDefinitionImpl.create(colorY2AxisTitle.get(0), colorY2AxisTitle.get(1), colorY2AxisTitle.get(2)));

				axis2.getLabel().setVisible(formatChartVo.isYAxisLabelVisibleScale2());
				axis2.getLabel().getCaption().getFont().setSize(formatChartVo.getSizeYAxisLabelScale2());
				List<Integer> colorY2AxisLabel = formatColorForBirt(convertToHexToDec(formatChartVo.getYAxisLabelColorScale2()));
				axis2.getLabel().getCaption().setColor(ColorDefinitionImpl.create(colorY2AxisLabel.get(0), colorY2AxisLabel.get(1), colorY2AxisLabel.get(2)));

			}

			int numDimY = ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().size();

			// show series values
			for (int i = 0; i < numDimY; i++) {
				SeriesDefinition s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(i);
				Series ob;
				for (int j = 0; j < s.getSeries().size(); j++) {
					ob = (Series) s.getSeries().get(j);
					ob.getLabel().setVisible(formatChartVo.isShowSeriesValues());
					// ob.getLabel().getCaption().getFont().setSize(bean.getSizeChartValue());
				}
			}
		} else {

			int tmp = ((ChartWithoutAxes) chart).getSeriesDefinitions().size();
			for (int i = 0; i < tmp; i++) {
				SeriesDefinition s = (SeriesDefinition) ((ChartWithoutAxes) chart).getSeriesDefinitions().get(i);
				Series ob;
				for (int j = 0; j < s.getSeries().size(); j++) {
					ob = (Series) s.getSeries().get(j);
				}
			}
		}

	}
	
	private List<Integer> formatColorForBirt(String color) {
		StringTokenizer colorFormat = new StringTokenizer(color, "_");
		List<Integer> RGB = new ArrayList<Integer>();
		while (colorFormat.hasMoreTokens()) {
			try {
				RGB.add(Integer.valueOf(colorFormat.nextToken()));
			}
			catch (Exception e) {
				LOGGER.error("ERROR formatColorForBirt: " + color);
				e.printStackTrace();
			} 
		}

		return RGB;
	}
	
	
	/*** END FORMAT CHART ***/

	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}

	public void setDataviewDao(DataViewDao dataviewDao) {
		this.dataviewDao = dataviewDao;
	}

	public void setUniqueValuesDao(UniqueValuesDao uniqueValuesDao) {
		this.uniqueValuesDao = uniqueValuesDao;
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

	public void setGraphEngine(GraphEngine graphEngine) {
		this.graphEngine = graphEngine;
	}

	public void setSaveUniqueDao(SaveUniqueDao saveUniqueDao) {
		this.saveUniqueDao = saveUniqueDao;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setOlapUpdater(OLAPUpdater olapUpdater) {
		this.olapUpdater = olapUpdater;
	}

	public void setResourceViewDao(ResourceViewDao resourceViewDao) {
		this.resourceViewDao = resourceViewDao;
	}

}