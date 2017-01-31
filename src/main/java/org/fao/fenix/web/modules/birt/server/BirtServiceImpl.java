package org.fao.fenix.web.modules.birt.server;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.ChartWithoutAxes;
import org.eclipse.birt.chart.model.attribute.ChartDimension;
import org.eclipse.birt.chart.model.attribute.ColorDefinition;
import org.eclipse.birt.chart.model.attribute.Fill;
import org.eclipse.birt.chart.model.attribute.NumberFormatSpecifier;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.attribute.impl.NumberFormatSpecifierImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Scale;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.NumberDataElementImpl;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.model.api.CellHandle;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.DesignElementHandle;
import org.eclipse.birt.report.model.api.DesignEngine;
import org.eclipse.birt.report.model.api.ElementFactory;
import org.eclipse.birt.report.model.api.ExtendedItemHandle;
import org.eclipse.birt.report.model.api.GridHandle;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.ImageHandle;
import org.eclipse.birt.report.model.api.ReportDesignHandle;
import org.eclipse.birt.report.model.api.ReportItemHandle;
import org.eclipse.birt.report.model.api.RowHandle;
import org.eclipse.birt.report.model.api.ScriptDataSetHandle;
import org.eclipse.birt.report.model.api.ScriptDataSourceHandle;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.eclipse.birt.report.model.api.TextItemHandle;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.fao.fenix.core.connection.GwtConnector;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.domain.constants.DataType;
import org.fao.fenix.core.domain.dataset.Dataset;
import org.fao.fenix.core.domain.dataset.Descriptor;
import org.fao.fenix.core.domain.map.GeoView;
import org.fao.fenix.core.domain.perspective.ChartDatasetMapping;
import org.fao.fenix.core.domain.perspective.ChartView;
import org.fao.fenix.core.domain.perspective.DataView;
import org.fao.fenix.core.domain.perspective.MapView;
import org.fao.fenix.core.domain.perspective.ReportView;
import org.fao.fenix.core.domain.perspective.Scales;
import org.fao.fenix.core.domain.perspective.SelectedField;
import org.fao.fenix.core.domain.perspective.SelectedFieldDouble;
import org.fao.fenix.core.domain.perspective.SelectedFieldList;
import org.fao.fenix.core.domain.perspective.SelectedValue;
import org.fao.fenix.core.domain.perspective.SelectedValueBar;
import org.fao.fenix.core.domain.perspective.SelectedValueLine;
import org.fao.fenix.core.domain.perspective.TableView;
import org.fao.fenix.core.domain.perspective.TextView;
import org.fao.fenix.core.domain.perspective.charting.OtherYDimension;
import org.fao.fenix.core.domain.perspective.charting.YDimension;
import org.fao.fenix.core.domain.perspective.charting.YDimensionBarLine;
import org.fao.fenix.core.domain.resourceview.ResourceView;
import org.fao.fenix.core.domain.udtable.UDTable;
import org.fao.fenix.core.exception.FenixException;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.core.persistence.DatasetDao;
import org.fao.fenix.core.persistence.SaveUniqueDao;
import org.fao.fenix.core.persistence.IPCReport.IPCDao;
import org.fao.fenix.core.persistence.cb.CerealBalanceCBDao;
import org.fao.fenix.core.persistence.cb.CerealBalanceChartBean;
import org.fao.fenix.core.persistence.estpricedatabase.ImportESTPriceDatabase;
import org.fao.fenix.core.persistence.fsatmis.FsatmisDao;
import org.fao.fenix.core.persistence.isfp.ISFPBeanForBirt;
import org.fao.fenix.core.persistence.isfp.ISFPDao;
import org.fao.fenix.core.persistence.isfp.util.ChartFoodPricesInfo;
import org.fao.fenix.core.persistence.map.WMSMapProviderDao;
import org.fao.fenix.core.persistence.olap.OLAPDao;
import org.fao.fenix.core.persistence.perspective.ChartDao;
import org.fao.fenix.core.persistence.perspective.DataViewDao;
import org.fao.fenix.core.persistence.perspective.MapDao;
import org.fao.fenix.core.persistence.perspective.TableDao;
import org.fao.fenix.core.persistence.perspective.TextDao;
import org.fao.fenix.core.persistence.rainfall.RainfallDao;
import org.fao.fenix.map.mapretriever.WMSMapRetriever;
import org.fao.fenix.web.modules.birt.common.ISFPCountries;
import org.fao.fenix.web.modules.birt.common.services.BirtService;
import org.fao.fenix.web.modules.birt.common.vo.ChartBirtBean;
import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.DateVo;
import org.fao.fenix.web.modules.birt.common.vo.FormatChartVo;
import org.fao.fenix.web.modules.birt.common.vo.ReportVo;
import org.fao.fenix.web.modules.birt.server.exportmap.MapReport;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.birt.server.utils.GraphEngine;
import org.fao.fenix.web.modules.birt.server.utils.MatchCodeLabel;
import org.fao.fenix.web.modules.birt.server.utils.RptdesignToString;
import org.fao.fenix.web.modules.birt.server.utils.TableReport;
import org.fao.fenix.web.modules.birt.server.utils.CountryBriefTemplate.CerealBalanceChart;
import org.fao.fenix.web.modules.birt.server.utils.CountryBriefTemplate.CropLegend;
import org.fao.fenix.web.modules.birt.server.utils.CountryBriefTemplate.CropMaps;
import org.fao.fenix.web.modules.birt.server.utils.CountryBriefTemplate.RainfallCharts;
import org.fao.fenix.web.modules.birt.server.utils.FPITemplate.FPITemplate;
import org.fao.fenix.web.modules.birt.server.utils.ISFPTemplate.ISFPChart;
import org.fao.fenix.web.modules.birt.server.utils.ISFPTemplate.TableAsGrid;
import org.fao.fenix.web.modules.birt.server.utils.chart.ChartColors;
import org.fao.fenix.web.modules.birt.server.utils.chart.ChartUpdater;
import org.fao.fenix.web.modules.birt.server.utils.fsatmisTemplate.CreateFsatmisChartForReport;
import org.fao.fenix.web.modules.birt.server.utils.report.AddChartToReport;
import org.fao.fenix.web.modules.birt.server.utils.report.AddMapToReport;
import org.fao.fenix.web.modules.birt.server.utils.report.AddTableToReport;
import org.fao.fenix.web.modules.birt.server.utils.report.AddTextToReport;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.core.server.utils.EmptyDatumLabel;
import org.fao.fenix.web.modules.core.server.utils.FenixResourceBuilder;
import org.fao.fenix.web.modules.core.server.utils.Setting;
import org.fao.fenix.web.modules.core.server.utils.UrlFinder;
import org.fao.fenix.web.modules.ipc.common.vo.IPCReportBeanVO;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.map.server.util.MapViewUtils;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.rainfall.common.vo.RainfallParameters;
import org.fao.fenix.web.modules.rainfall.server.RainfallUtils;
import org.gwtwidgets.server.spring.ServletUtils;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.util.ULocale;

public class BirtServiceImpl extends RemoteServiceServlet implements BirtService {

	private static final long serialVersionUID = -6765126326258797886L;

	private DataViewDao dataviewDao;

	private MapDao mapDao;

	private ChartDao chartDao;

	private DatasetDao datasetDao;

	private TableDao tableDao;

	private TextDao textDao;

	private ISFPDao isfpDao;

	private ImportESTPriceDatabase importESTPriceDatabase;

	private CodecDao codecDao;

	private GwtConnector gwtConnector;

	private RainfallDao rainfallDao;

	private RainfallUtils rainfallUtils;

	private CropMaps cropMaps;

	private CerealBalanceCBDao cerealBalanceCBDao;

	private WMSMapProviderDao wmsMapProviderDao;

	private IPCDao ipcDao;

	private FsatmisDao fsatmisDao;

	private SaveUniqueDao saveUniqueDao;
	
	private ChartUpdater chartUpdater;
	
	private OLAPDao olapDao;

	private Logger LOGGER = Logger.getLogger(BirtServiceImpl.class);

	@SuppressWarnings("deprecation")
	public String getMUSymbol(long datasetId, List<List<String>> otherDim) {
		for (List<String> dim : otherDim) {
			DataType type = gwtConnector.findContentDescriptorFromColumnName(datasetId, dim.get(0));
			if (type.name().equals(DataType.measurementUnit.name()))
				return dim.get(1);
		}
		return null;
	}

	public boolean isQualitativeDataset(long datasetId) {
		return gwtConnector.isQualitative(datasetId);
	}

	public boolean isFlexDataset(long datasetId) {
		return gwtConnector.isFlexDataset(datasetId);
	}

	@SuppressWarnings("deprecation")
	public List<String> getQuantityColumnNames(long datasetId, List<String> otherDim) {
		List<String> result = new ArrayList<String>();
		for (String cn : otherDim)
			if (gwtConnector.findContentDescriptorFromColumnName(datasetId, cn) == DataType.quantity)
				result.add(cn);
		return result;
	}

	public String getBirtApplName() {
		return Setting.getBirtApplName();
	}

	public String nameFileById(Long dataviewId) {
		DataView dataView = dataviewDao.findById(dataviewId);
		String nameFile = BirtUtil.randomNameFile();
		File file = new File(System.getProperty("java.io.tmpdir") + File.separator + nameFile);
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
			out.write(dataView.getRptdesign());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new FenixException("Unable to create rptdesign file for dataview " + dataView.getResourceId());
		}
		return nameFile;

	}

	public String openReport(Long id, String servletType) {
		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServlet?dataViewId=" + id + "&servletType=" + servletType + "' width='100%' height='100%' />";
	}

	public String openReport(String rptdesign, String servletType) {
		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptdesign + ".rptdesign&servletType=" + servletType + "' width='100%' height='100%' />";
	}

	public String getTemplate(String templateType) {
		String template = Setting.systemPathBirt + "/" + getBirtApplName() + "/template/" + templateType + ".rptdesign";
		String nameRptdesign = BirtUtil.randomNameFile();
		String renameTemplate = System.getProperty("java.io.tmpdir") + File.separator + nameRptdesign;
		try {
			FileUtils.copyFile(new File(template), new File(renameTemplate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nameRptdesign;
	}

	public List<Long> getAllReport() {
		List<Long> retList = new ArrayList<Long>();
		for (DataView view : dataviewDao.findAll())
			if (view.getRptdesign() != null)
				retList.add(view.getResourceId());
		return retList;
	}

	public Long saveChartLayout(String rptDesign, long dataviewId, String Title) {
		DataView r;
		String rep = new RptdesignToString(rptDesign).getRptdesignLikeString();
		r = dataviewDao.findById(dataviewId);
		r.setRptdesign(rep);
		if (!Title.equals(""))
			r.setTitle(Title);
		dataviewDao.save(r);
		return r.getResourceId();
	}

	public Long saveChart(String rptDesign, long dataviewId, ChartWizardBean chartWizardBean) {
		ChartView chartView = (ChartView) dataviewDao.findById(dataviewId);
		this.writeChartInfo(chartView, rptDesign, chartWizardBean);
		dataviewDao.update(chartView);
		saveChartDatasetMapping(chartView.getResourceId(), getDatasets(chartWizardBean.getDatasetId()));
		return chartView.getResourceId();
	}

	public Long saveChartAs(String rptDesign, ChartWizardBean chartWizardBean) {
		ChartView chartView = new ChartView();
		this.writeChartInfo(chartView, rptDesign, chartWizardBean);
		dataviewDao.save(chartView);
		saveChartDatasetMapping(chartView.getResourceId(), getDatasets(chartWizardBean.getDatasetId()));
		return chartView.getResourceId();
	}

	private List<Long> getDatasets(List<List<String>> datasets) {
		List<Long> datasetsIDs = new ArrayList<Long>();
		for (List<String> dataset : datasets)
			datasetsIDs.add(Long.valueOf(dataset.get(1)));
		return datasetsIDs;
	}

	private void saveChartDatasetMapping(Long chartID, List<Long> datasetsIDs) {
		for (Long datasetID : datasetsIDs) {
			ChartDatasetMapping cdm = new ChartDatasetMapping();
			cdm.setChartViewID(chartID);
			cdm.setDatasetID(datasetID);
			saveUniqueDao.saveUnique(cdm);
		}
	}

	private void writeChartInfo(ChartView chartView, String rptDesign, ChartWizardBean chartWizardBean) {
		String rep = new RptdesignToString(rptDesign).getRptdesignLikeString();
		
		LOGGER.info(" chartWizardBean.getDimensionY() --> " + chartWizardBean.getDimensionY());
		LOGGER.info(" chartWizardBean.getDimensionValuesYLine() --> " + chartWizardBean.getDimensionValuesYLine());
		LOGGER.info(" chartWizardBean.getDimensionValuesY() --> " + chartWizardBean.getDimensionValuesY());
		
		
		
		LOGGER.info("Y START: " + chartView.getSelectedFieldsY().size());
		
		
		for(YDimension dimension : chartView.getSelectedFieldsY()) {
			LOGGER.info("***********************************");
			LOGGER.info("List<Ydimensions>: " + dimension.getId() + " | " + dimension.getDataset().getTitle() + " | " + dimension.getName());
			
			LOGGER.info("SELECTED FIELD: " + dimension.getSelectedField().getId() + " | " + dimension.getSelectedField().getFieldName());
			
			for(SelectedValue value : dimension.getSelectedField().getSelectedValues() ){
				LOGGER.info("SELECTED VALUE: " + value.getId() + " | " + value.getSelectedValue());
			}
//			for(Sele)
			LOGGER.info("-----------------------------------");
		}
		
		
		LOGGER.info("Y END");
		
		
		
	
	
		
		
		LOGGER.info("Y END");

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

		// clear values TODO: DOESN'T WORK
		chartView.setSelectedFieldsYBarLine(new ArrayList<YDimensionBarLine>());
		chartView.setSelectedFieldsY(new ArrayList<YDimension>());
		chartView.setSelectedFieldsOtherDim(new ArrayList<OtherYDimension>());
		chartView.setScales(new ArrayList<Scales>());
		
		LOGGER.info("before DELETING ALL getSelectedFieldsY: " + chartView.getSelectedFieldsY().size());
		
		chartView.getSelectedFieldsY().clear();
		
		LOGGER.info("after DELETING ALL getSelectedFieldsY: " + chartView.getSelectedFieldsY().size());

		
		

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
				LOGGER.info("DS: " + ds);
				yDimension = new YDimension();
				selectedFieldY = new SelectedField();
				HashMap<String, String> addedValues = new HashMap<String, String>();
				
				
				selectedFieldY.setFieldName(chartWizardBean.getDimensionY().get(ds.get(1)));
				
				LOGGER.info("---> chartWizardBean.getDimensionValuesY(): " + chartWizardBean.getDimensionValuesY().get(ds.get(1)));
				for (int i = 0; i < chartWizardBean.getDimensionValuesY().get(ds.get(1)).size(); i++) {
					SelectedValue value = new SelectedValue();
					value.setSelectedValue(chartWizardBean.getDimensionValuesY().get(ds.get(1)).get(i).get(0));				
					LOGGER.info("--> ADDING VALUE: "+ i + " | " + value.getSelectedValue());
					
					// TODO if the values already in skip it...
					if ( !addedValues.containsKey(value.getSelectedValue())) {
						LOGGER.info("--> VALUE NOT IN: " + value.getSelectedValue()); 
						addedValues.put(value.getSelectedValue(), value.getSelectedValue());
						selectedFieldY.addsetSelectedValue(value);
					}
					else {
						LOGGER.info("--> VALUE ALREADY IN: " + value.getSelectedValue());
					}
						
				}

				yDimension.setDataset(dataset);
				yDimension.setSelectedField(selectedFieldY);
				
			
				
				
				
				// todo remove first TODO: FIX FOR YDIMENSION
				
				
				LOGGER.info("Y CURRENT start");
				
				// todo remove first TODO: FIX FOR YDIMENSION
				
				chartView.addsetSelectedFieldsY(yDimension);
				
				for(YDimension dimension : chartView.getSelectedFieldsY()) {
					LOGGER.info("***********************************");
					LOGGER.info("List<Ydimensions>: " + dimension.getId() + " | " + dimension.getDataset().getTitle() + " | " + dimension.getName());
					
					LOGGER.info("SELECTED FIELD: " + dimension.getSelectedField().getId() + " | " + dimension.getSelectedField().getFieldName());
					
					for(SelectedValue value : dimension.getSelectedField().getSelectedValues() ){
						LOGGER.info("SELECTED VALUE: " + value.getId() + " | " + value.getSelectedValue());
					}
//					for(Sele)
					LOGGER.info("-----------------------------------");
				}
				
				
				LOGGER.info("Y CURRENT END");

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
//		LOGGER.info("chartWizardBean.getTitleIsBold(): " + chartWizardBean.getTitleIsBold());
//		LOGGER.info("chartWizardBean.getTitleIsItalic(): " +chartWizardBean.getTitleIsItalic());
//		LOGGER.info("chartWizardBean.getTitleIsItalic(): " +chartWizardBean.getTitleIsItalic());
		
		
		chartView.setTitleIsBold(chartWizardBean.getTitleIsBold());
		chartView.setTitleIsItalic(chartWizardBean.getTitleIsItalic());
		chartView.setTitleIsUnderlined(chartWizardBean.getTitleIsUnderline());
		
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

	
	/*** TODO: THIS METHOD HAS TO BE OPTIMAZED....
	 *		   THERE ARE SEVERAL USELESS CALLS TO THE DB  		
	 *  **/
	private List<List<String>> reportObject(String rptDesign, ReportVo reportVo) {
//		LOGGER.info("reportObject");
		String templateType = reportVo.getTemplate();

		List<List<String>> listObject = new ArrayList<List<String>>();

		DesignConfig dConfigReport = new DesignConfig();
		dConfigReport.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfigReport);

		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);

		// Create a handle for an existing report design.
		String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle designReport = null;

		try {
			designReport = session.openDesign(nameReport);
			List<String> element;
			if (templateType.equals("blankTemplate")) {
				int numObject = designReport.getBody().getCount();
				for (int i = 0; i < numObject; i++) {
					String displayName = ((ReportItemHandle) designReport.getBody().get(i)).getDisplayName();
					System.out.println("displayName: " + displayName + " | " + i);
					element = new ArrayList<String>();
					if (displayName.equals("map")) {						
						element.add("map");
						element.add(((ReportItemHandle) designReport.getBody().get(i)).getName());
						element.add(mapDao.findById(Long.valueOf(designReport.getBody().get(i).getName())).getTitle());
					} else if (displayName.equals("text")) {
						element.add("text");
						String id = ((ReportItemHandle) designReport.getBody().get(i)).getName();
						int pos = designReport.getBody().findPosn(designReport.findElement(id));
//						System.out.println("positiontext: " + pos);
						removeObjectByReport(rptDesign, id);
//						DataView r = dataviewDao.findById(Long.valueOf(id));
						AddTextToReport.getText(rptDesign, Long.valueOf(id), textDao, i, "blankTemplate");
						element.add(((ReportItemHandle) designReport.getBody().get(i)).getName());
						element.add(textDao.findById(Long.valueOf(designReport.getBody().get(i).getName())).getTitle());
					} else if (displayName.equals("chart")) {
						element.add("chart");
						String id = ((ReportItemHandle) designReport.getBody().get(i)).getName();
						int pos = designReport.getBody().findPosn(designReport.findElement(id));
//						System.out.println("positionchart: " + pos);
						removeObjectByReport(rptDesign, id);
						DataView r = dataviewDao.findById(Long.valueOf(id));
						AddChartToReport.getChart(rptDesign, r, i, "blankTemplate");
						element.add(((ReportItemHandle) designReport.getBody().get(i)).getName());
						element.add(chartDao.findById(Long.valueOf(designReport.getBody().get(i).getName())).getTitle());
					} else if (displayName.equals("table")) {
						element.add("table");
						System.out.println("displayName: " + displayName + " | " + ((ReportItemHandle) designReport.getBody().get(i)).getName());
						reportVo.addsetTableIdList(Long.valueOf(((ReportItemHandle) designReport.getBody().get(i)).getName()));
						element.add(((ReportItemHandle) designReport.getBody().get(i)).getName());
						/** TODO: table is saved without the DatasetID, so the title it cannot be taken dinamically
						 *  	  The title or better the datasetID has to be stored somewhere **/
			
						String datasetID = ((ReportItemHandle) designReport.getBody().get(i)).getDisplayNameKey();
						if ( datasetID != null) {
							Dataset dataset = datasetDao.findById(Long.valueOf(datasetID));
							// check in datasett table if exist
							if ( dataset != null) {
								element.add(dataset.getTitle());
							}
							else {
								
								ResourceView resource = olapDao.findByID(Long.valueOf(datasetID));
								if ( resource != null)
									element.add(resource.getTitle());
								else
									element.add("Table");
							}
						}
						else {
//							LOGGER.info("missing table reference");
							element.add("Table");
						}
						
						
					} else if (displayName.equals("comment")) {
						element.add("comment");
						String id = ((ReportItemHandle) designReport.getBody().get(i)).getName();	
						int pos = designReport.getBody().findPosn(designReport.findElement(id));
//						System.out.println("positioncomment: " + pos);
						element.add(((ReportItemHandle) designReport.getBody().get(i)).getName());
						element.add("comment");
					} else if (displayName.equals("space")) {
						element.add("space");
						element.add(((ReportItemHandle) designReport.getBody().get(i)).getName());
						element.add("Space");
					} else if (displayName.equals("horizontalBar")) {
						element.add("horizontalBar");
						element.add(((ReportItemHandle) designReport.getBody().get(i)).getName());
						element.add("Horizontal bar");
					}

					listObject.add(element);
				}
				System.out.println("ELEMENT: " + listObject);

			} else if (templateType.equals("template1")) {

				GridHandle reportGridHandle = (GridHandle) designReport.findElement("Grid1");
				RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				element = new ArrayList<String>();
				element.add("map");
				if (gridCellHandle.getContent().getCount() != 0) {
					element.add(((ImageHandle) gridCellHandle.getContent().get(0)).getName());
					element.add(mapDao.findById(Long.valueOf(((ImageHandle) gridCellHandle.getContent().get(0)).getName())).getTitle());
				} else {
					element.add("-1");
					element.add("( empty )");
				}
				listObject.add(element);

				element = new ArrayList<String>();
				reportGridHandle = (GridHandle) designReport.findElement("Grid1");
				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				gridCellHandle = (CellHandle) birtRow.getCells().get(1);
				element = new ArrayList<String>();
				element.add("chart");
				if (gridCellHandle.getContent().getCount() != 0) {
					String id = ((GridHandle) gridCellHandle.getContent().get(0)).getName();
					DataView r = dataviewDao.findById(Long.valueOf(id));
					AddChartToReport.getChart(rptDesign, r, 2, "template1");
					element.add(((GridHandle) gridCellHandle.getContent().get(0)).getName());
					element.add(chartDao.findById(Long.valueOf(((GridHandle) gridCellHandle.getContent().get(0)).getName())).getTitle());
				} else {
					element.add("-1");
					element.add("( empty )");
				}
				listObject.add(element);

				element = new ArrayList<String>();
				reportGridHandle = (GridHandle) designReport.findElement("Grid2");
				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				element = new ArrayList<String>();
				element.add("table");
				if (gridCellHandle.getContent().getCount() != 0) {
					element.add("table");
//					System.out.println("displayName: " + displayName + " | " + ((ReportItemHandle) designReport.getBody().get(i)).getName());
					reportVo.addsetTableIdList(Long.valueOf(((GridHandle) gridCellHandle.getContent().get(0)).getName()));
					element.add(((GridHandle) gridCellHandle.getContent().get(0)).getName());
					/** TODO: table is saved without the DatasetID, so the title it cannot be taken dinamically
					 *  	  The title or better the datasetID has to be stored somewhere **/
//					datasetDao.findById(Long.valueOf(designReport.getBody().get(i).getName()));
					element.add("Table");
				} else {
					element.add("-1");
					element.add("( empty )");
				}
				listObject.add(element);

				element = new ArrayList<String>();
				reportGridHandle = (GridHandle) designReport.findElement("Grid2");
				birtRow = (RowHandle) reportGridHandle.getRows().get(2);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				element = new ArrayList<String>();
				element.add("text");
				if (gridCellHandle.getContent().getCount() != 0) {
					String id = ((TextItemHandle) gridCellHandle.getContent().get(0)).getName();
					AddTextToReport.getText(rptDesign, Long.valueOf(id), textDao, 4, "template1");	
					element.add(((TextItemHandle) gridCellHandle.getContent().get(0)).getName());
					element.add(textDao.findById(Long.valueOf(((TextItemHandle) gridCellHandle.getContent().get(0)).getName())).getTitle());
				} else {
					element.add("-1");
					element.add("( empty )");
				}
				listObject.add(element);

			} else if (templateType.equals("template2")) {

				GridHandle reportGridHandle = (GridHandle) designReport.findElement("Grid1");
				RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				element = new ArrayList<String>();
				element.add("map");
				if (gridCellHandle.getContent().getCount() != 0) {
					element.add(((ImageHandle) gridCellHandle.getContent().get(0)).getName());
					element.add(mapDao.findById(Long.valueOf(((ImageHandle) gridCellHandle.getContent().get(0)).getName())).getTitle());
				} else {
					element.add("-1");
					element.add("( empty )");
				}
				listObject.add(element);

				element = new ArrayList<String>();
				reportGridHandle = (GridHandle) designReport.findElement("Grid1");
				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				gridCellHandle = (CellHandle) birtRow.getCells().get(1);
				element = new ArrayList<String>();
				element.add("chart");
				if (gridCellHandle.getContent().getCount() != 0) {
					String id = ((GridHandle) gridCellHandle.getContent().get(0)).getName();
					DataView r = dataviewDao.findById(Long.valueOf(id));
					AddChartToReport.getChart(rptDesign, r, 2, "template2");
					element.add(((GridHandle) gridCellHandle.getContent().get(0)).getName());
					element.add(chartDao.findById(Long.valueOf(((GridHandle) gridCellHandle.getContent().get(0)).getName())).getTitle());
				} else {
					element.add("-1");
					element.add("( empty )");
				}
				listObject.add(element);

				element = new ArrayList<String>();
				reportGridHandle = (GridHandle) designReport.findElement("Grid2");
				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				element = new ArrayList<String>();
				element.add("table");
				if (gridCellHandle.getContent().getCount() != 0) {
					reportVo.addsetTableIdList(Long.valueOf(((GridHandle) gridCellHandle.getContent().get(0)).getName()));
					element.add(((GridHandle) gridCellHandle.getContent().get(0)).getName());
					/** TODO: table is saved without the DatasetID, so the title it cannot be taken dinamically
					 *  	  The title or better the datasetID has to be stored somewhere **/
//					datasetDao.findById(Long.valueOf(designReport.getBody().get(i).getName()));
					element.add("Table");
				} else {
					element.add("-1");
					element.add("( empty )");
				}
				listObject.add(element);

				element = new ArrayList<String>();
				reportGridHandle = (GridHandle) designReport.findElement("Grid2");
				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				gridCellHandle = (CellHandle) birtRow.getCells().get(1);
				element = new ArrayList<String>();
				element.add("map");
				if (gridCellHandle.getContent().getCount() != 0) {
					element.add(((ImageHandle) gridCellHandle.getContent().get(0)).getName());
					element.add(mapDao.findById(Long.valueOf(((ImageHandle) gridCellHandle.getContent().get(0)).getName())).getTitle());
				} else {
					element.add("-1");
					element.add("( empty )");
				}
				listObject.add(element);

				element = new ArrayList<String>();
				reportGridHandle = (GridHandle) designReport.findElement("Grid3");
				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				element = new ArrayList<String>();
				element.add("text");
				if (gridCellHandle.getContent().getCount() != 0) {
					String id = ((TextItemHandle) gridCellHandle.getContent().get(0)).getName();
					AddTextToReport.getText(rptDesign, Long.valueOf(id), textDao, 5, "template2");				
					element.add(((TextItemHandle) gridCellHandle.getContent().get(0)).getName());
					element.add(textDao.findById(Long.valueOf(((TextItemHandle) gridCellHandle.getContent().get(0)).getName())).getTitle());
				} else {
					element.add("-1");
					element.add("( empty )");
				}
				listObject.add(element);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return listObject;

	}

	public List<List<String>> getReport(Long reportViewId, ReportVo reportVo, String rptdesign) {
//		LOGGER.info("getting report");
		ReportView reportView = (ReportView) dataviewDao.findById(reportViewId);
		reportVo.setTemplate(reportView.getTemplate());
		for (MapView mapView : reportView.getMapViewList())
			reportVo.addsetMapIdList(mapView.getResourceId());
		for (TextView textView : reportView.getTextViewList())
			reportVo.addsetTextIdList(textView.getResourceId());
		for (ChartView chartView : reportView.getChartViewList())
			reportVo.addsetChartIdList(chartView.getResourceId());
		/** TODO: This is useless the tables are not stored in the db,
		 * 		  they are read from the rptdesign.
		 * 		  The id for the listObject on the reportVo, 
		 * 		  are store in the reportObject method and it has to be changed
		 * 		  when the querybuilder will be up		
		 * **/
		for (TableView tableView : reportView.getTableViewList())
			reportVo.addsetTableIdList(tableView.getResourceId());
		
		List<List<String>> listObject = this.reportObject(rptdesign, reportVo);
		
		/** TODO: shitty thing on salvo List<List<String>> **/		
		List<String> rptDesign = new ArrayList<String>();
		rptDesign.add(rptdesign);
		listObject.add(rptDesign);
		return listObject;
	}
	
	

	public String getClassTemplate(Long reportViewId) {
//		LOGGER.info("getClassTemplate");
		ReportView reportView = (ReportView) dataviewDao.findById(reportViewId);
		return reportView.getTemplate();
	}

	private void writeReportInfo(ReportView reportView, String rptDesign, ReportVo reportVo) {
//		LOGGER.info("writeReportInfo");
		String rep = new RptdesignToString(rptDesign).getRptdesignLikeString();
		reportView.setRptdesign(rep);
		reportView.getMapViewList().clear();
		for (Long idMap : reportVo.getMapIdList())
			reportView.addsetMapViewList(mapDao.findById(idMap));
		reportView.getTableViewList().clear();
		for (Long idTable : reportVo.getTableIdList())
			reportView.addsetTableViewList(tableDao.findById(idTable));
		reportView.getTextViewList().clear();
		for (Long idText : reportVo.getTextIdList())
			reportView.addsetTextViewList(textDao.findById(idText));
		reportView.getChartViewList().clear();
		for (Long idChart : reportVo.getChartIdList())
			reportView.addsetChartViewList(chartDao.findById(idChart));
	}

	public Long saveReportAs(String rptDesign, ReportVo reportVo) {
		ReportView reportView = new ReportView();
		reportView.setTemplate(reportVo.getTemplate());
		this.writeReportInfo(reportView, rptDesign, reportVo);
		dataviewDao.save(reportView);
		return reportView.getResourceId();
	}

	public Long saveReport(String rptDesign, long dataviewId, ReportVo reportVo) {
		ReportView reportView = (ReportView) dataviewDao.findById(dataviewId);
		this.writeReportInfo(reportView, rptDesign, reportVo);
		dataviewDao.update(reportView);
		return reportView.getResourceId();
	}

	public ChartWizardBean getChart(Long dataviewId, ChartWizardBean chartWizardBean) {

		ChartView chartView = (ChartView) dataviewDao.findById(dataviewId);

		chartWizardBean.setAggregation(chartView.getAggregation());
		chartWizardBean.setMostRecent(chartView.isMostRecent());
		if (chartWizardBean.isMostRecent())
			chartWizardBean.setMostRecent(new DateVo(chartView.getYears(), chartView.getMonths(), chartView.getDays()));
		else
			chartWizardBean.setMostRecent(new DateVo("0", "0", "0"));

		chartWizardBean.setDimensionX(chartView.getSelectedFieldsX().getFieldName());
		List<List<String>> dimensionValuesX = new ArrayList<List<String>>();
		List<String> elementValuesX;
		// X dimension
		for (SelectedValue selectedValue : chartView.getSelectedFieldsX().getSelectedValues()) {
			elementValuesX = new ArrayList<String>();
			elementValuesX.add(selectedValue.getSelectedValue());
			elementValuesX.add(selectedValue.getSelectedValue());
			dimensionValuesX.add(elementValuesX);
		}

		chartWizardBean.setDimensionValuesX(dimensionValuesX);

		List<List<String>> datasetId = new ArrayList<List<String>>();
		List<String> elementDS;

		Map<String, String> dimensionY = new HashMap<String, String>();

		Map<String, List<List<String>>> dimensionValuesY = new HashMap<String, List<List<String>>>();
		List<List<String>> dimensionValuesYList;

		Map<String, List<List<String>>> dimensionValuesYBar = new HashMap<String, List<List<String>>>();
		List<List<String>> dimensionValuesYBarList;
		Map<String, List<List<String>>> dimensionValuesYLine = new HashMap<String, List<List<String>>>();
		List<List<String>> dimensionValuesYLineList;

		List<String> elementValues;

		Map<String, List<List<String>>> otherDimensionMap = new HashMap<String, List<List<String>>>();
		List<List<String>> dimensionValuesOthList;

		if (chartView.getChartType().equals("BarLine")) {

			dimensionValuesYBarList = new ArrayList<List<String>>();
			for (YDimensionBarLine yDimensionBarLine : chartView.getSelectedFieldsYBarLine()) {
				dimensionY.put(String.valueOf(yDimensionBarLine.getDataset().getResourceId()), yDimensionBarLine.getSelectedField().getFieldName());
				elementDS = new ArrayList<String>();
				elementDS.add(yDimensionBarLine.getDataset().getTitle());
				elementDS.add(String.valueOf(yDimensionBarLine.getDataset().getResourceId()));
				datasetId.add(elementDS);
				for (SelectedValueBar selectedValue : yDimensionBarLine.getSelectedField().getSelectedBarValues()) {
					elementValues = new ArrayList<String>();
					elementValues.add(selectedValue.getSelectedValue());
					elementValues.add(selectedValue.getSelectedValue());
					dimensionValuesYBarList.add(elementValues);
				}

				dimensionValuesYBar.put(String.valueOf(yDimensionBarLine.getDataset().getResourceId()), dimensionValuesYBarList);

				dimensionValuesYLineList = new ArrayList<List<String>>();
				for (SelectedValueLine selectedValue : yDimensionBarLine.getSelectedField().getSelectedLineValues()) {
					elementValues = new ArrayList<String>();
					elementValues.add(selectedValue.getSelectedValue());
					elementValues.add(selectedValue.getSelectedValue());
					dimensionValuesYLineList.add(elementValues);
				}

				dimensionValuesYLine.put(String.valueOf(yDimensionBarLine.getDataset().getResourceId()), dimensionValuesYLineList);

			}

			chartWizardBean.setDimensionValuesYBar(dimensionValuesYBar);
			chartWizardBean.setDimensionValuesYLine(dimensionValuesYLine);

		} else {

//			for(YDimension dimension : chartView.getSelectedFieldsY()) {
//				LOGGER.info("***********************************");
//				LOGGER.info("List<Ydimensions>: " + dimension.getId() + " | " + dimension.getDataset().getTitle() + " | " + dimension.getName());
//				
//				LOGGER.info("SELECTED FIELD: " + dimension.getSelectedField().getId() + " | " + dimension.getSelectedField().getFieldName());
//				
//				for(SelectedValue value : dimension.getSelectedField().getSelectedValues() ){
//					LOGGER.info("SELECTED VALUE: " + value.getId() + " | " + value.getSelectedValue());
//				}
////				for(Sele)
//				LOGGER.info("-----------------------------------");
//			}
			
//			dimensionValuesYList = new ArrayList<List<String>>();
			for (YDimension yDimension : chartView.getSelectedFieldsY()) {
				dimensionValuesYList = new ArrayList<List<String>>();
				LOGGER.info("***********************************");
				LOGGER.info("List<Ydimensions>: " + yDimension.getId() + " | " + yDimension.getDataset().getTitle() + " | " + yDimension.getName());
				
				LOGGER.info("SELECTED FIELD: " + yDimension.getSelectedField().getId() + " | " + yDimension.getSelectedField().getFieldName());
				
				dimensionY.put(String.valueOf(yDimension.getDataset().getResourceId()), yDimension.getSelectedField().getFieldName());
				elementDS = new ArrayList<String>();
				elementDS.add(yDimension.getDataset().getTitle());
				elementDS.add(String.valueOf(yDimension.getDataset().getResourceId()));
				datasetId.add(elementDS);
				for (SelectedValue selectedValue : yDimension.getSelectedField().getSelectedValues()) {
					elementValues = new ArrayList<String>();
					elementValues.add(selectedValue.getSelectedValue());
					elementValues.add(selectedValue.getSelectedValue());
					dimensionValuesYList.add(elementValues);
					LOGGER.info("SELECTED VALUE: " + selectedValue.getId() + " | " + selectedValue.getSelectedValue());
				}

				dimensionValuesY.put(String.valueOf(yDimension.getDataset().getResourceId()), dimensionValuesYList);
				LOGGER.info("-----------------------------------");
			}
			

			chartWizardBean.setDimensionValuesY(dimensionValuesY);

		}

		// other dimensions

		for (OtherYDimension otherYDimension : chartView.getSelectedFieldsOtherDim()) {
			dimensionValuesOthList = new ArrayList<List<String>>();
			for (SelectedField selectedField : otherYDimension.getSelectedFieldList().getSelectedFieldsList()) {
				elementValues = new ArrayList<String>();
				elementValues.add(selectedField.getFieldName());
				elementValues.add(selectedField.getSelectedValues().get(0).getSelectedValue());
				if (selectedField.getSelectedValues().get(0).getUniqueOtherValue() == null)
					elementValues.add("false");
				else
					elementValues.add(selectedField.getSelectedValues().get(0).getUniqueOtherValue());
				dimensionValuesOthList.add(elementValues);

			}
			otherDimensionMap.put(String.valueOf(otherYDimension.getDataset().getResourceId()), dimensionValuesOthList);
		}

		// scales
		if (chartView.getScales().size() > 0) {
			Map<String, String> scalesMap = new HashMap<String, String>();
			for (Scales s : chartView.getScales()) {
				scalesMap.put(String.valueOf(s.getDataset().getResourceId()), s.getNumberScale());
			}
			chartWizardBean.setScalesMap(scalesMap);
		}

		chartWizardBean.setDatasetId(datasetId);
		chartWizardBean.setDimensionY(dimensionY);

		chartWizardBean.setDoubleScale(chartView.isDoubleAxis());
		chartWizardBean.setOtherDimension(otherDimensionMap);
		
		// title settings
//		LOGGER.info("chartWizardBean.getTitleIsBold(): " + chartWizardBean.getTitleIsBold());
//		LOGGER.info("chartWizardBean.getTitleIsItalic(): " +chartWizardBean.getTitleIsItalic());
//		LOGGER.info("chartWizardBean.getTitleIsItalic(): " +chartWizardBean.getTitleIsItalic());
		
		
		chartWizardBean.setTitle(chartView.getTitleChart());
		chartWizardBean.setTitleIsBold(chartView.getTitleIsBold());
		chartWizardBean.setTitleIsItalic(chartView.getTitleIsItalic());
		chartWizardBean.setTitleIsUnderline(chartView.getTitleIsUnderlined());
		
		chartWizardBean.setChartType(chartView.getChartType());
		chartWizardBean.setFlip(chartView.isFlip());
		chartWizardBean.setPosLegend(chartView.getPositionLegend());
		chartWizardBean.setShowLegend(chartView.isShowLegend());
		chartWizardBean.setXAxisTitle(chartView.getXAxisTitle());
		chartWizardBean.setXAxisShowLabels(chartView.isXAxisShowLabels());
		chartWizardBean.setYAxisTitle(chartView.getYAxisTitle());
		chartWizardBean.setYAxisShowLabels(chartView.isYAxisShowLabels());
		chartWizardBean.setRotateXLabels(String.valueOf(chartView.getRotateXLabels()));
		chartWizardBean.setDim2_3D(chartView.getDimension());
		chartWizardBean.setDisposition(chartView.getDisposition());
		
		
		
		
		
		
		
		LOGGER.info(" chartWizardBean.getDimensionY() --> " + chartWizardBean.getDimensionY());
		LOGGER.info(" chartWizardBean.getDimensionValuesYLine() --> " + chartWizardBean.getDimensionValuesYLine());
		LOGGER.info(" chartWizardBean.getDimensionValuesY() --> " + chartWizardBean.getDimensionValuesY());
		

		return chartWizardBean;
	}

	public List<String> updatePreview(ChartWizardBean bean, String servletType, String rptDesign) {

		List<String> result = new ArrayList<String>();

		String message;

		GraphEngine newChart = new GraphEngine(bean, this, codecDao, datasetDao, gwtConnector, rptDesign);
		String rep = newChart.createReport();
//		System.out.println("rep: " + rep);
		message = "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rep + "&servletType=" + servletType + "' width='100%' height='100%' />";

		result.add(rep);
		result.add(message);

		return result;
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

	public void legendChart(Chart chart, String method, FormatChartVo formatChartVo) throws IOException {

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

			if (!chart.getType().equals("Pie")) {
				formatChartVo.setUnitSpacing(String.valueOf(((ChartWithAxes) chart).getUnitSpacing()));
				formatChartVo.setFlip(((ChartWithAxes) chart).isTransposed());
			}

			SeriesDefinition s = (SeriesDefinition) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getSeriesDefinitions().get(0);
			if (((Series) s.getSeries().get(0)).isStacked())
				formatChartVo.setDisposition("Stacked");
			else
				formatChartVo.setDisposition("Side by Side");

			formatChartVo.setColorAndLabelFromClientToServer(ChartColors.getChartColors(chart, "hex"));

		} else if (method.equals("set")) {

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
					String col = formatChartVo.getColorAndLabelFromClientToServer().get(i).get(1);
					StringTokenizer colorComp = new StringTokenizer(col, "_");
					int r = 0;
					int g = 0;
					int b = 0;
					int cont = 0;
					while (colorComp.hasMoreTokens()) {
						if (cont == 0) {
							r = Integer.valueOf(colorComp.nextToken()).intValue();
						} else if (cont == 1) {
							g = Integer.valueOf(colorComp.nextToken()).intValue();
						} else if (cont == 2) {
							b = Integer.valueOf(colorComp.nextToken()).intValue();
						}
						cont++;
					}

					if (chart.getType().equals("Line")) {

						((LineSeries) s.getSeries().get(0)).getLineAttributes().setColor(ColorDefinitionImpl.create(r, g, b));

					} else if (chart.getType().equals("Bar/Line")) {
						String packClass = s.getSeries().get(0).getClass().toString();
						int index = (packClass.length()) - 1;
						while (packClass.charAt(index) != '.') {
							index--;
						}
						index++;
						packClass = packClass.substring(index, packClass.length());
						if (packClass.equals("LineSeriesImpl")) {
							((LineSeries) s.getSeries().get(0)).getLineAttributes().setColor(ColorDefinitionImpl.create(r, g, b));
						} else {
							s.getSeriesPalette().getEntries().clear();
							final Fill[] fiaBase = { ColorDefinitionImpl.create(r, g, b), };
							for (int z = 0; z < fiaBase.length; z++) {
								s.getSeriesPalette().getEntries().add(fiaBase[z]);
							}
						}
					} else {
						s.getSeriesPalette().getEntries().clear();
						final Fill[] fiaBase = { ColorDefinitionImpl.create(r, g, b), };
						for (int z = 0; z < fiaBase.length; z++) {
							s.getSeriesPalette().getEntries().add(fiaBase[z]);
						}
					}

				}
			}

		}

	}

	public String changeChartBackground(String rptDesign, String color) {
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

			StringTokenizer colorFormat = new StringTokenizer(color, "_");
			// 2D/2D with depth or Side-by-side/Stacked
			List<Integer> RGB = new ArrayList<Integer>();

			while (colorFormat.hasMoreTokens()) {
				RGB.add(Integer.valueOf(colorFormat.nextToken()));
			}

			chart.getPlot().getClientArea().setBackground(ColorDefinitionImpl.create(RGB.get(0), RGB.get(1), RGB.get(2)));

			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());

		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=preview ' width='100%' height='100%' />";
	}

	private List<Integer> formatColorForBirt(String color) {
		StringTokenizer colorFormat = new StringTokenizer(color, "_");
		List<Integer> RGB = new ArrayList<Integer>();

		while (colorFormat.hasMoreTokens()) {
			RGB.add(Integer.valueOf(colorFormat.nextToken()));
		}

		return RGB;
	}

	public String setChartInfo(String rptDesign, FormatChartVo formatChartVo) {
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

			List<Integer> plotAreaColor = formatColorForBirt(formatChartVo.getPlotAreaColor());
			chart.getPlot().getClientArea().setBackground(ColorDefinitionImpl.create(plotAreaColor.get(0), plotAreaColor.get(1), plotAreaColor.get(2)));

			List<Integer> chartAreaColor = formatColorForBirt(formatChartVo.getChartAreaColor());
			chart.getBlock().setBackground(ColorDefinitionImpl.create(chartAreaColor.get(0), chartAreaColor.get(1), chartAreaColor.get(2)));

			if (formatChartVo.getDimension().equals("Two_Dimensional")) {
				chart.setDimension(ChartDimension.TWO_DIMENSIONAL_LITERAL);
			} else if (formatChartVo.getDimension().equals("Two_Dimensional_With_Depth")) {
				chart.setDimension(ChartDimension.TWO_DIMENSIONAL_WITH_DEPTH_LITERAL);
			}

			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getMajorGrid().getLineAttributes().setVisible(formatChartVo.isYGrid());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getMajorGrid().getLineAttributes().setVisible(formatChartVo.isXGrid());

			try {
				Axis xAxisPrimary = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0];
				xAxisPrimary.getLabel().getCaption().getFont().setRotation(Double.valueOf(formatChartVo.getxAxixRotation()));
			} catch (Exception e) {
			}
				
			
			setLabelChart(chart, formatChartVo);
			scaleChart(chart, "set", formatChartVo);
			legendChart(chart, "set", formatChartVo);

			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());

		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=preview' width='100%' height='100%' />";
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

	private void getAreaChartInfo(Chart chart, FormatChartVo formatChartVo) {

		ColorDefinition plotAreaBG = (ColorDefinitionImpl) chart.getPlot().getClientArea().getBackground();
		formatChartVo.setPlotAreaColor(formatColor(plotAreaBG));

		ColorDefinition chartAreaBG = (ColorDefinitionImpl) chart.getBlock().getBackground();
		formatChartVo.setChartAreaColor(formatColor(chartAreaBG));

		formatChartVo.setDimension(chart.getDimension().getLiteral());

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
			
			Boolean isBold = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().isBold();
			Boolean isItalic = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().isItalic();
			Boolean isUnderline = ((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().isUnderline();
			
//			LOGGER.info("x FONT BOLD:"  + isBold );
//			LOGGER.info("x FONT ITALIC:"  + isItalic );
//			LOGGER.info("x FONT UNDERLINED:"  + isUnderline );
			
			formatChartVo.setxAxisLabelIsBold(isBold);
			formatChartVo.setxAxisLabelIsItalic(isItalic);
			formatChartVo.setxAxisLabelIsUnderline(isUnderline);
			formatChartVo.setxAxisTitleFont(((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().getName());
//			LOGGER.info("x FONT UNDERLINED:"  + formatChartVo.getxAxisTitleFont() );
			
			
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

//			LOGGER.info("x AXIS BOLD:"  + isBold );
//			LOGGER.info("x AXIS ITALIC:"  + isItalic );
//			LOGGER.info("x AXIS UNDERLINED:"  + isUnderline );
//			LOGGER.info("x FONT UNDERLINED:"  + formatChartVo.getxLabelFont() );

			
			
			
			formatChartVo.setYAxisTitle(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getValue());
			formatChartVo.setYAxisVisible(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().isVisible());
			formatChartVo.setSizeYAxis((int) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().getSize());
			formatChartVo.setYAxisTitleColor(formatColor(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getColor()));

			formatChartVo.setYAxisLabelVisible(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().isVisible());
			formatChartVo.setSizeYAxisLabel((int) ((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().getSize());
			formatChartVo.setYAxisLabelColor(formatColor(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getColor()));

			
		
			// y style title
			
			
			formatChartVo.setyTitleLabelIsBold(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().isBold());
			formatChartVo.setyTitleIsItalic(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().isItalic());
			formatChartVo.setyTitleIsUnderline(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().isUnderline());
			formatChartVo.setyTitleFont(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().getFont().getName());

			
//			LOGGER.info("y TITLE BOLD:"  + formatChartVo.getyTitleLabelIsBold() );
//			LOGGER.info("y TITLE ITALIC:"  + formatChartVo.getyTitleIsItalic() );
//			LOGGER.info("y TITLE UNDERLINED:"  + formatChartVo.getyTitleIsUnderline() );
//			LOGGER.info("y TITLE FONT:"  + formatChartVo.getyTitleFont() );

			
			

			formatChartVo.setyAxisLabelIsBold(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().isBold());
			formatChartVo.setyAxisLabelIsItalic(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().isItalic());
			formatChartVo.setyAxisLabelIsUnderline(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().isUnderline());
			formatChartVo.setyLabelFont(((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().getName());

			
//			LOGGER.info("y LABEL BOLD:"  + formatChartVo.getyAxisLabelIsBold() );
//			LOGGER.info("y LABEL ITALIC:"  + formatChartVo.getyAxisLabelIsItalic() );
//			LOGGER.info("y LABEL UNDERLINED:"  + formatChartVo.getyAxisLabelIsUnderline() );
//			LOGGER.info("y LABEL FONT:"  + formatChartVo.getyLabelFont() );

			
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

	public void setLabelChart(Chart chart, FormatChartVo formatChartVo) {

		if (formatChartVo.getTitle() != null)
			chart.getTitle().getLabel().getCaption().setValue(formatChartVo.getTitle());
		else
			chart.getTitle().getLabel().getCaption().setValue("");
		chart.getTitle().setVisible(formatChartVo.isTitleVisible());
		chart.getTitle().getLabel().getCaption().getFont().setSize(formatChartVo.getSizeTitle());
		List<Integer> colorTitle = formatColorForBirt(formatChartVo.getColorTitle());
		chart.getTitle().getLabel().getCaption().setColor(ColorDefinitionImpl.create(colorTitle.get(0), colorTitle.get(1), colorTitle.get(2)));
	
		chart.getTitle().getLabel().getCaption().getFont().setBold(formatChartVo.getTitleIsBold());
		chart.getTitle().getLabel().getCaption().getFont().setItalic(formatChartVo.getTitleIsItalic());
		chart.getTitle().getLabel().getCaption().getFont().setUnderline(formatChartVo.getTitleIsUnderline());
		
		// x titlefont
//		LOGGER.info("TITLE FONT TYPE:"  +formatChartVo.getxTitleFont() );
		chart.getTitle().getLabel().getCaption().getFont().setName(formatChartVo.getxTitleFont());
//		LOGGER.info("TITLE FONT TYPE NAME:"  +chart.getTitle().getLabel().getCaption().getFont().getName() );
		
	
		if (!chart.getType().equals("Pie")) {
			if (formatChartVo.getXAxisTitle() != null) {
				((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().setValue(formatChartVo.getXAxisTitle());
			}
			else
				((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().setValue("");
			
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setBold(formatChartVo.getxAxisLabelIsBold());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setItalic(formatChartVo.getxAxisLabelIsItalic());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setUnderline(formatChartVo.getxAxisLabelIsUnderline());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setName(formatChartVo.getxAxisTitleFont());

			
//			LOGGER.info("x TITLE FONT BOLD:"  + formatChartVo.getxAxisLabelIsBold() );
//			LOGGER.info("x TITLE FONT ITALIC:"  + formatChartVo.getxAxisLabelIsItalic() );
//			LOGGER.info("x TITLE FONT UNDERLINED:"  + formatChartVo.getxAxisLabelIsUnderline() );
//			LOGGER.info("x TITLE FONT :"  + formatChartVo.getxAxisTitleFont() );

			
			
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().setVisible(formatChartVo.isXAxisVisible());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().getFont().setSize(formatChartVo.getSizeXAxis());
			List<Integer> colorXAxisTitle = formatColorForBirt(formatChartVo.getXAxisTitleColor());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getTitle().getCaption().setColor(ColorDefinitionImpl.create(colorXAxisTitle.get(0), colorXAxisTitle.get(1), colorXAxisTitle.get(2)));

			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setSize(formatChartVo.getSizeXAxisLabel());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().setVisible(formatChartVo.isXAxisLabelVisible());
			List<Integer> colorXAxisLabel = formatColorForBirt(formatChartVo.getXAxisLabelColor());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().setColor(ColorDefinitionImpl.create(colorXAxisLabel.get(0), colorXAxisLabel.get(1), colorXAxisLabel.get(2)));

			// AXIX SETTINGS
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setBold(formatChartVo.getxAxisIsBold());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setItalic(formatChartVo.getxAxisIsItalic());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setUnderline(formatChartVo.getxAxisIsUnderline());
			((ChartWithAxes) chart).getPrimaryBaseAxes()[0].getLabel().getCaption().getFont().setName(formatChartVo.getxLabelFont());

			
//			LOGGER.info("x AXIS FONT BOLD:"  + formatChartVo.getxAxisIsBold() );
//			LOGGER.info("x AXIS FONT ITALIC:"  + formatChartVo.getxAxisIsItalic() );
//			LOGGER.info("x AXIS FONT UNDERLINED:"  + formatChartVo.getxAxisIsUnderline() );
//			LOGGER.info("x AXIS FONT FONT:"  + formatChartVo.getxLabelFont() );

			
			// Y -AXIS 
			
			// y style title
//			LOGGER.info("SETTINGS getyTitleLabelIsBold(): " + formatChartVo.getyTitleLabelIsBold());
//			LOGGER.info("SETTINGS getyTitleIsItalic(): " + formatChartVo.getyTitleIsItalic());
//			LOGGER.info("SETTINGS getyTitleIsUnderline(): " + formatChartVo.getyTitleIsUnderline());
//			LOGGER.info("SETTINGS getyTitlefont(): " + formatChartVo.getyTitleFont());

			
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
			List<Integer> colorYAxisTitle = formatColorForBirt(formatChartVo.getYAxisTitleColor());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getTitle().getCaption().setColor(ColorDefinitionImpl.create(colorYAxisTitle.get(0), colorYAxisTitle.get(1), colorYAxisTitle.get(2)));

			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().setVisible(formatChartVo.isYAxisLabelVisible());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().getFont().setSize(formatChartVo.getSizeYAxisLabel());
			List<Integer> colorYAxisLabel = formatColorForBirt(formatChartVo.getYAxisLabelColor());
			((ChartWithAxes) chart).getPrimaryOrthogonalAxis(((ChartWithAxes) chart).getPrimaryBaseAxes()[0]).getLabel().getCaption().setColor(ColorDefinitionImpl.create(colorYAxisLabel.get(0), colorYAxisLabel.get(1), colorYAxisLabel.get(2)));

			// y style label
//			LOGGER.info("SETTINGS getyAxisLabelIsBold(): " + formatChartVo.getyAxisLabelIsBold());
//			LOGGER.info("SETTINGS getyAxisLabelIsItalic(): " + formatChartVo.getyAxisLabelIsItalic());
//			LOGGER.info("SETTINGS getyAxisLabelIsUnderline(): " + formatChartVo.getyAxisLabelIsUnderline());
//			LOGGER.info("SETTINGS getyAxisLabelfont(): " + formatChartVo.getyLabelFont());

			
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
				List<Integer> colorY2AxisTitle = formatColorForBirt(formatChartVo.getYAxisTitleColorScale2());
				axis2.getTitle().getCaption().setColor(ColorDefinitionImpl.create(colorY2AxisTitle.get(0), colorY2AxisTitle.get(1), colorY2AxisTitle.get(2)));

				axis2.getLabel().setVisible(formatChartVo.isYAxisLabelVisibleScale2());
				axis2.getLabel().getCaption().getFont().setSize(formatChartVo.getSizeYAxisLabelScale2());
				List<Integer> colorY2AxisLabel = formatColorForBirt(formatChartVo.getYAxisLabelColorScale2());
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

	public String showHideParametersToChart(String rptDesign, Map<String, List<List<String>>> otherDimension, List<List<String>> datasets) {
		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle design = null;
		try {
			design = session.openDesign(name);
			GridHandle gridChart = (GridHandle) design.findElement("dataGrid");

			RowHandle row2 = (RowHandle) gridChart.getRows().get(1);
			CellHandle cell = (CellHandle) row2.getCells().get(0);
			// delete parameters
			if (cell.getContent().getCount() > 0) {

				int parametersNumber = cell.getContent().getCount();

				for (int i = 0; i < parametersNumber; i++) {
					cell.getContent().drop(0);
				}

			} else /* show parameters */{

				TextItemHandle parameter;
				for (int d = 0; d < datasets.size(); d++) {
					parameter = design.getElementFactory().newTextItem("Par");
					parameter.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
					String DS = "";
					if (datasets.size() > 1)
						DS = "DS" + (d + 1) + "-";

					String textOtherDim = "<div style='font-weight:bold; font-size: 12px; color:#FF0000;'>Parameters (" + DS + datasets.get(d).get(0) + "): </div>";
					for (List<String> values : otherDimension.get(datasets.get(d).get(1))) {
						textOtherDim += "<div style='font-style:italic; font-size: 10px;'>" + values.get(0);
						// TODO: This should be with a given langcode
						textOtherDim += ": " + MatchCodeLabel.getLabel(gwtConnector, codecDao, Long.valueOf(datasets.get(d).get(1)), values.get(1), values.get(0), "EN")+ "</div>";
					}
					parameter.setContent(textOtherDim);

					cell.getContent().add(parameter);
				}

			}

			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());
		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=preview' width='100%' height='100%' />";
	}

	public String resizeChart(String rptDesign, String inOut, String stretch) {
		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle design = null;
		try {
			design = session.openDesign(name);
			DesignElementHandle handle = (DesignElementHandle) design.findElement("NewChart");
			ExtendedItemHandle eHandle = (ExtendedItemHandle) handle;
			Chart chart = (Chart) eHandle.getReportItem().getProperty("chart.instance");
			double oldWidth = chart.getBlock().getBounds().getWidth();
			double oldHeight = chart.getBlock().getBounds().getHeight();

			if (inOut.equals("in")) {
				if (stretch.equals("hor"))
					chart.getBlock().getBounds().setWidth(oldWidth + 50);
				else if (stretch.equals("ver"))
					chart.getBlock().getBounds().setHeight(oldHeight + 50);
				else if (stretch.equals("both")) {
					chart.getBlock().getBounds().setWidth(oldWidth + 50);
					chart.getBlock().getBounds().setHeight(oldHeight + 50);
				}

			} else if (inOut.equals("out")) {

				if ((oldWidth - 50) > 0 && (oldHeight - 50) > 0) {

					if (stretch.equals("hor"))
						chart.getBlock().getBounds().setWidth(oldWidth - 50);
					else if (stretch.equals("ver"))
						chart.getBlock().getBounds().setHeight(oldHeight - 50);
					else if (stretch.equals("both")) {
						chart.getBlock().getBounds().setWidth(oldWidth - 50);
						chart.getBlock().getBounds().setHeight(oldHeight - 50);
					}

				}

			}

			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());

		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=preview' width='100%' height='100%' />";
	}

	public List<String> addTextToReport(String rptDesign, String content) {

		List<String> reportObject = new ArrayList<String>();

		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);

		if (rptDesign.equals("")) {
			// create the rptdesign file
			String nameRptdesign = BirtUtil.randomNameFile();

			TextItemHandle text = null;

			try {
				SessionHandle newSession = BirtUtil.getDesignSessionHandle();
				String rep = null;

				ReportDesignHandle designHandle = null;

				IDesignEngine designEngine = null;

				ElementFactory designFactory = null;

				designHandle = session.createDesign();

				designFactory = designHandle.getElementFactory();

				DesignElementHandle simpleMasterPage = designFactory.newSimpleMasterPage("Master Page");//$NON-NLS-1$
				designHandle.getMasterPages().add(simpleMasterPage);

				ElementFactory elementFactory = designHandle.getElementFactory();

				text = designHandle.getElementFactory().newTextItem("Note");
				text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);

				text.setContent(content);

				designHandle.getBody().add(text);

				designHandle.saveAs(System.getProperty("java.io.tmpdir") + File.separator + nameRptdesign);

			} catch (BirtException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			reportObject.add(nameRptdesign);
			reportObject.add(text.getName());

		} else {

			// update the rptdesign file
			DesignConfig dConfigReport = new DesignConfig();
			dConfigReport.setBIRTHome(Setting.getReportEngine());

			DesignEngine dEngineReport = new DesignEngine(dConfigReport);
			// Create a session handle, using the system locale.
			SessionHandle sessionReport = dEngine.newSessionHandle(ULocale.ENGLISH);
			// Create a handle for an existing report design.
			String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
			ReportDesignHandle designReport = null;
			TextItemHandle text = null;
			try {
				designReport = session.openDesign(nameReport);

				text = designReport.getElementFactory().newTextItem("Note");
				text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);

				text.setContent(content);
				text.setDisplayName("comment");
				designReport.getBody().add(text);

			} catch (Exception e) {
				System.err.println("Report  not opened!\nReason is " + e.toString());

			}

			try {
				designReport.saveAs(nameReport);

			} catch (Exception e) {
				e.printStackTrace();
			}

			reportObject.add(rptDesign);
			reportObject.add(text.getName());
			reportObject.add("<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />");

		}

		return reportObject;

	}
	

	public List<String> changeCommentText(String rptDesign, String commentId, String content) {

//		LOGGER.info(commentId + " | " + content);
		List<String> reportObject = new ArrayList<String>();

		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		
		DesignConfig dConfigReport = new DesignConfig();
		dConfigReport.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngineReport = new DesignEngine(dConfigReport);
		// Create a session handle, using the system locale.
		SessionHandle sessionReport = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle designReport = null;
		TextItemHandle text = null;
		
		try {
			designReport = session.openDesign(nameReport);

			text = designReport.getElementFactory().newTextItem("Note");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);

			text.setContent(content);

			int pos = designReport.getBody().findPosn(designReport.findElement(commentId));

			designReport.getBody().drop(pos);
			designReport.getBody().add(text, pos);

			designReport.saveAs(nameReport);
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}


		reportObject.add(rptDesign);
		reportObject.add(text.getName());
		reportObject.add("<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />");
		return reportObject;
	}
	
	
	public String getCommentText(String rptDesign, String commentId) {

		String content = new String();

		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		
		DesignConfig dConfigReport = new DesignConfig();
		dConfigReport.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngineReport = new DesignEngine(dConfigReport);
		// Create a session handle, using the system locale.
		SessionHandle sessionReport = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle designReport = null;
		TextItemHandle text = null;
		
		try {
			
			designReport = session.openDesign(nameReport);

			TextItemHandle t = (TextItemHandle) designReport.findElement(commentId);

//			System.out.println("getContent: " + t.getContent());
			
			content = t.getContent();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}


		return content;
	}

	public List<String> addImageToReport(String rptDesign, String fileName) {

		List<String> reportObject = new ArrayList<String>();

		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);

		// Create a handle for an existing report design.
		String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle designReport = null;
		ImageHandle imageHandle = null;
		try {
			designReport = session.openDesign(nameReport);

			imageHandle = designReport.getElementFactory().newImage("CustomImage");
			imageHandle.setDisplayName("Image");
			imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
			imageHandle.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getApplicationName() + File.separator + "imageRepository" + File.separator + fileName + "\"");

			designReport.getBody().add(imageHandle);

		} catch (Exception e) {
			System.err.println("Report  not opened!\nReason is " + e.toString());
		}

		try {
			designReport.saveAs(nameReport);

		} catch (Exception e) {
			e.printStackTrace();
		}

		reportObject.add(rptDesign);
		reportObject.add(imageHandle.getName());
		reportObject.add("<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />");

		return reportObject;

	}

	/** TODO: this is used for the chart wizard **/	
	public String createReport(Long id, int objectPosition, String rptDesign, String typeView, int width, int height, String reportType) {
//		System.out.println("Position: " + objectPosition);
		if (typeView.equals("chart")) {
			DataView r;
			r = dataviewDao.findById(id);
			AddChartToReport.getChart(rptDesign, r, objectPosition, reportType);
		} else if (typeView.equals("map")) {
			AddMapToReport.getMap(rptDesign, id, mapDao, objectPosition, width, height, reportType);
		} else if (typeView.equals("text")) {
			AddTextToReport.getText(rptDesign, id, textDao, objectPosition, reportType);
		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />";
	}

	public String addTableToReport(String datasetCode, String datasetID, String rptDesign, int objectPosition, String reportType, String tableHTML) {	
		AddTableToReport.getTable(datasetCode, datasetID, rptDesign, objectPosition, reportType, tableHTML);
		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />";
	}

	public String removeObjectByReport(String rptDesign, String birtObject) {
		DesignConfig dConfigReport = new DesignConfig();
		dConfigReport.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfigReport);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle designReport = null;
		try {

			designReport = session.openDesign(nameReport);

			designReport.getBody().drop(designReport.findElement(birtObject));

			designReport.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

		} catch (Exception e) {
			LOGGER.info("FAILED TO REMOVED: " + birtObject);
		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />";
	}

	public String moveObjectByReport(String rptDesign, String upDown, String birtObject) {
		DesignConfig dConfigReport = new DesignConfig();
		dConfigReport.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfigReport);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle designReport = null;
		try {

			designReport = session.openDesign(nameReport);

			int pos = designReport.getBody().findPosn(designReport.findElement(birtObject));

			if (upDown.equals("up")) {
				if (pos != 0) {
					designReport.getBody().shift(designReport.findElement(birtObject), (pos - 1));
					designReport.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
				}
			} else if (upDown.equals("down")) {
				if (pos != designReport.getBody().getCount()) {
					designReport.getBody().shift(designReport.findElement(birtObject), (pos + 1));
					designReport.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
				}

			}

		} catch (Exception e) {
			LOGGER.error("Report not opened!\nReason is " + e.toString());

		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />";
	}

	/**
	 * @return #0 Object name, #1 iFrame
	 */
	public List<String> separatorObjectByReport(String rptDesign, String HTMLObj, String birtObject) {

		List<String> result = new ArrayList<String>();

		DesignConfig dConfigReport = new DesignConfig();
		dConfigReport.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfigReport);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String nameReport = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle designReport = null;
		try {

			designReport = session.openDesign(nameReport);

			int pos = designReport.getBody().findPosn(designReport.findElement(birtObject));

			TextItemHandle text = null;
			ElementFactory elementFactory = designReport.getElementFactory();

			if (HTMLObj.equals("br")) {
				text = designReport.getElementFactory().newTextItem("Space");
				text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				text.setContent("<br>");
				text.setDisplayName("space");
				designReport.getBody().add(text, (pos + 1));
				designReport.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
			} else if (HTMLObj.equals("hr")) {
				text = designReport.getElementFactory().newTextItem("HorizontalBar");
				text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				// the line below doesn't work when you try to export that
				// report - birt bug
				// text.setContent("<hr style='width: 100%; height: 2px;'>");
				// Workaround
				text.setDisplayName("horizontalBar");
				text.setContent("<br><div style='width: 100%; height:1px; background-color:gray;'>&nbsp;</div><br>");

				designReport.getBody().add(text, (pos + 1));
				designReport.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
			}

			result.add(text.getName());

		} catch (Exception e) {
			LOGGER.error("Report not opened!\nReason is " + e.toString());

		}

		result.add("<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />");

		return result;
	}

	/**
	 * Export a dataset as CSV
	 */
	public String exportAsCSV(Long datasetId) {
		String[] columnNames = getColumnNamesForCsvExport(datasetId);
		List<String> colNames = new ArrayList<String>();
		for (String name : columnNames) {
			colNames.add(name);
		}
		List<List<String>> data = getRecordsWithLabel(datasetId, colNames);
		String exportFile = BirtUtil.randomFileExport() + ".csv";

		try {

			File file = new File(Setting.getSystemPath() + "/exportObject/" + exportFile);
			FileOutputStream stream = new FileOutputStream(file);

			int sizeColumns = colNames.size();
			String tmp;

			// add headers
			for (int i = 0; i < sizeColumns; i++) {
				if ((i + 1) == sizeColumns) {
					tmp = colNames.get(i);
				} else {
					tmp = colNames.get(i) + ",";
				}
				stream.write(tmp.getBytes());
			}

			stream.write(("\n").getBytes());

			for (List<String> row : data) {
				for (int i = 0; i < sizeColumns; i++) {
					if ((i + 1) == sizeColumns) {
						tmp = row.get(i);
					} else {
						tmp = row.get(i) + ",";
					}
					stream.write(tmp.getBytes());
				}
				stream.write(("\n").getBytes());
			}

			stream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exportFile;
	}

	public String exportTable(List<List<String>> data, String typeExport) {

		TableReport table = new TableReport(data);
		String nameFile = table.createReport();

		return "/" + Setting.getBirtApplName() + "/preview?__report=" + nameFile + "&__format=" + typeExport;

	}

	public String exportTable(Long resourceId, List<String> columnNames, String typeExport) {

		List<List<String>> data = getRecordsWithLabel(resourceId, columnNames);
		data.add(0, columnNames);

		TableReport table = new TableReport(data);
		String nameFile = table.createReport();

		return "/" + Setting.getBirtApplName() + "/preview?__report=" + nameFile + "&__format=" + typeExport;

	}

	public String exportTable(List<List<String>> data, List<String> columnNames, String typeExport) {

		data.add(0, columnNames);

		TableReport table = new TableReport(data);
		String nameFile = table.createReport();

		return "/" + Setting.getBirtApplName() + "/preview?__report=" + nameFile + "&__format=" + typeExport;

	}

	public String linkChart(Long chartId) {
		/** save rptDesign using the chartId saved into the db **/

		try {
			String fileName = chartId.toString();
			fileName += ".rptdesign";
			// LOGGER.info("fileName using chartID: " + fileName);
			/** get the saved rptdesign, not the temp one **/
			ChartView chart = chartDao.findById(chartId);

			File out = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "report" + File.separator + "links" + File.separator + fileName);
			BufferedWriter fos = new BufferedWriter(new FileWriter(out));

			fos.write(chart.getRptdesign());
			fos.flush();
			fos.close();

//			String ip = UrlFinder.findBirtUrl(8070);
			String ip = UrlFinder.fenixBirtUrl;
			if (ip != null) {
//				System.out.println("fenix-birt: " + ip);
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

	// ************************* EXPORT Object
	// *******************************************

	public String exportChartAndTable(String title, List<List<String>> data, String xRow, String yRow, String type, String format) {

		FPITemplate table = new FPITemplate(title, data);
		String nameFile = table.createReport(xRow, yRow, type);

		return "/" + Setting.getBirtApplName() + "/preview?__report=" + nameFile + "&__format=" + format;

	}

	public String exportChart(String rptdesign, String typeExport) {

		return "/" + Setting.getBirtApplName() + "/preview?__report=" + System.getProperty("java.io.tmpdir") + File.separator + rptdesign + "&__format=" + typeExport;

	}

	public String exportText(String content, String typeExport) {

		List textReport = addTextToReport("", content);

		String fileName = (String) textReport.get(0);

		return "/" + Setting.getBirtApplName() + "/preview?__report=" + System.getProperty("java.io.tmpdir") + File.separator + fileName + "&__format=" + typeExport;

	}

	@SuppressWarnings("unchecked")
	public List<String> getTableDimensions(Long id) {
		List<String> dimensions = new ArrayList();
		TableView table = tableDao.findById(id);
		// add dataset's ID to the result
		// dimensions.add(table.getDataList().getResourceId());
		dimensions.add(String.valueOf(table.getDatasetId()));
		// add table's title to the result
		dimensions.add(table.getTitle());
		List fields = table.getSelectedFields();
		// add table dimensions to the result
		for (int i = 0; i < fields.size(); i++)
			dimensions.add(((SelectedField) fields.get(i)).getFieldName());
		return dimensions;
	}

	// ===========================================
	// DATASET RELATED METHODS
	// TODO These methods need to be moved in a specific service, because
	// several objects will need
	// to use them, not only birt

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

	public List<List<String>> getRecordsWithLabel(Long datasetId, List<String> columnNames) {
		List<Object[]> originalRowList = datasetDao.getRowValues(datasetId, columnNames);
		List<List<String>> rowList = new ArrayList<List<String>>();
		for (Object[] originalRow : originalRowList) {
			List<String> row = new ArrayList<String>(originalRow.length);
			for (Object field : originalRow) {
				if (field != null)
					row.add(field.toString());
				else
					row.add(EmptyDatumLabel.getLabel());
			}
			rowList.add(row);
		}
		for (int i = 0; i < rowList.size(); i++)
			for (int j = 0; j < columnNames.size(); j++) {
				DataType contentDescriptor = gwtConnector.findContentDescriptorFromColumnName(datasetId, columnNames.get(j));
				if (contentDescriptor.name().equals("date"))
					rowList.get(i).set(j, gwtConnector.formatDate(datasetId, rowList.get(i).get(j)));
				else
					// TODO: THIS SHOULD BE GIVED WITH A DYNAMIC LANGCODE
					rowList.get(i).set(j, MatchCodeLabel.getLabel(gwtConnector, codecDao, datasetId, rowList.get(i).get(j), columnNames.get(j), "EN"));
			}

		return rowList;
	}

	public int findCommodityCodeRowIndex(List<String> columnNames) {
		for (int i = 0; i < columnNames.size(); i++)
			if (columnNames.get(i).equals("commodityCode"))
				return i;
		return 0;
	}

	public int findFeatureCodeRowIndex(List<String> columnNames) {
		for (int i = 0; i < columnNames.size(); i++)
			if (columnNames.get(i).equals("featureCode"))
				return i;
		return 0;
	}

	private List<String> getColumnNamesFiltering(long datasetId, List<String> columnNames, boolean lowerCase) {
		List<String> result = new ArrayList<String>();
		if (gwtConnector.isQuantitative(datasetId)) {
			for (String element : columnNames) {
				DataType s = gwtConnector.findContentDescriptorFromColumnName(datasetId, element);
				if (s != DataType.quantity)
					if (lowerCase) {
						result.add(element.toLowerCase());
					} else
						result.add(element);
			}
			// LOGGER.info(result);
			return result;
		}
		// LOGGER.info(columnNames);
		return columnNames;
	}

	public String[] getColumnNames(long datasetId) {
		List<String> fieldList = getColumnNamesFiltering(datasetId, gwtConnector.getColumnNames(datasetId), false);
		return fieldList.toArray(new String[fieldList.size()]);
	}

	public String[] getColumnNamesForCsvExport(long datasetId) {
		List<String> fieldList = gwtConnector.getColumnNames(datasetId);
		return fieldList.toArray(new String[fieldList.size()]);
	}

	/**
	 * 
	 * This Methods returns all the names in common for List<String> datasetId
	 */
	public String[] getColumnNames(List<String> datasetId) {
		List<String> fieldList = new ArrayList<String>();
		List<String> tmpList = new ArrayList<String>();
		List<String> copyFieldList;
		for (int i = 0; i < datasetId.size(); i++) {
			long datasetid = Long.valueOf(datasetId.get(i));
			if (datasetId.size() > 1)
				tmpList = getColumnNamesFiltering(datasetid, gwtConnector.getColumnNames(datasetid), false);
			else
				tmpList = getColumnNamesFiltering(datasetid, gwtConnector.getColumnNames(datasetid), false);

			if (i == 0) {
				fieldList = tmpList;
			} else {
				copyFieldList = new ArrayList<String>();
				copyFieldList.addAll(fieldList);
				for (String l : copyFieldList) {
					if (!tmpList.contains(l)) {
						fieldList.remove(l);
					}
				}
			}
		}

		return fieldList.toArray(new String[fieldList.size()]);
	}

	public List<List<String>> getColumnValues(Long datasetId, String columnName) {
		List<String> valueList = datasetDao.findDistinctRowValues(datasetId, columnName);
		List<List<String>> valueLabelList = new ArrayList<List<String>>();

		for (int i = 0; i < valueList.size(); i++) {
			List<String> element = new ArrayList<String>();
			element.add(valueList.get(i));
			DataType contentDescriptor = gwtConnector.findContentDescriptorFromColumnName(datasetId, columnName);
			if (contentDescriptor.name().equals("date"))
				element.add(gwtConnector.formatDate(datasetId, valueList.get(i)));
			else
				// TODO: THIS SHOULD BE GIVEN WITH A DYNAMIC LANGCODE
				element.add(MatchCodeLabel.getLabel(gwtConnector, codecDao, datasetId, valueList.get(i), columnName, "EN"));

			valueLabelList.add(element);
		}

		return valueLabelList;

	}

	public List<List<List<String>>> getColumnsValues(Long datasetId, List<String> columnNames) {

		List<List<List<String>>> valueCodeLabelList = new ArrayList<List<List<String>>>();

		for (String columnName : columnNames) {
			valueCodeLabelList.add(getColumnValues(datasetId, columnName));
		}

		return valueCodeLabelList;

	}

	public Long createNewChartResource(String rptDesign, ChartWizardBean chartWizardBean) {

		// save new chart
		final Long id = saveChartAs(rptDesign, chartWizardBean);

		return id;
	}

	public Long createNewReportResource(String rptDesign, ReportVo reportVo) {

		final Long id = saveReportAs(rptDesign, reportVo);

		return id;
	}

	public FenixResourceVo getNewResource(Long id) {

		DataView view = dataviewDao.findById(id);
		FenixResourceVo fenixResource = null;

		if (view != null) {
			fenixResource = FenixResourceBuilder.build(dataviewDao.findById(id));
		}

		return fenixResource;
	}

	private void addSource(ReportDesignHandle design, String container, int x, int y, String source, boolean border) throws Exception {
		GridHandle reportGridHandle = (GridHandle) design.findElement(container);
		RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(x);
		CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(y);

		TextItemHandle text = design.getElementFactory().newTextItem("text");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		String testo = "<div style='font-size:8px; color:#0000FF;";
		if (border)
			testo += " border-bottom:1px solid black;'>";
		else
			testo += "'>";
		testo += source + "</div>";
		text.setContent(testo);
		gridCellHandle.getContent().add(text);
	}

	private void resourceUnavailable(ReportDesignHandle design, String container, int x, int y, boolean cropCalendar) throws Exception {
		GridHandle reportGridHandle = (GridHandle) design.findElement(container);
		RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(x);
		CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(y);

		TextItemHandle text = design.getElementFactory().newTextItem("text");
		text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
		String mess = "<div style='font-size:9px; font-style:italic;'>";
		if (cropCalendar)
			text.setContent(mess + "Crop calendar not available</div>");
		else
			text.setContent(mess + "Not available</div>");
		gridCellHandle.getContent().add(text);
	}

	public ImageHandle getISFPTable(String name, ReportDesignHandle design, String countryLabel) throws Exception {
		File filePng = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ISFP/" + countryLabel + "/" + name + ".png");
		String extension = "png";
		if (!filePng.exists()) {
			File fileGif = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ISFP/" + countryLabel + "/" + name + ".gif");
			if (!fileGif.exists())
				return null;
			else
				extension = "gif";
		}

		ImageHandle imageHandle = design.getElementFactory().newImage(name);
		imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
		imageHandle.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ISFP/" + countryLabel + "/" + name + "." + extension + "\"");

		return imageHandle;

	}

	public String loadESTPriceDatabase() {
		importESTPriceDatabase.getDataToDatasets();
		return null;
	}

	private String parseImageName(String image) {
		int from = 0;
		int to = 0;
		int count = 0;
		for (int i = 0; i < image.length(); i++) {
			if (image.charAt(i) == '_') {
				from = i;
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

	public List<Date> addRainfallChartsToCountryBrief(List<String> regionCodeList, String countryCode, String rptDesign, ReportDesignHandle design) {

		try {

			Date maxDate = rainfallDao.getMostRecentDate(regionCodeList.get(0));
			if (maxDate != null) {
				int year = (1900 + maxDate.getYear());
				int month = (1 + maxDate.getMonth());
				int day = maxDate.getDate();

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				List<Date> decadi = new ArrayList<Date>();
				decadi.add(maxDate);
				for (int i = 0; i < 35; i++) {
					if (month == 1 && day == 1) {
						year--;
						month = 12;
					} else if (day == 1) {
						month--;
					}
					if (day == 1)
						day = 21;
					else if (day == 11)
						day = 1;
					else if (day == 21)
						day = 11;

					String monthString = "";
					if (month < 10)
						monthString = "0" + String.valueOf(month);
					else
						monthString = String.valueOf(month);

					String dayString = "";
					if (day == 1)
						dayString = "0" + String.valueOf(day);
					else
						dayString = String.valueOf(day);
					String dateString = String.valueOf(year) + "-" + monthString + "-" + dayString;
					Date date = simpleDateFormat.parse(dateString);
					decadi.add(date);
				}

				// overturn order
				List<Date> decadiAsc = new ArrayList<Date>();
				for (int j = (decadi.size() - 1); j >= 0; j--) {
					decadiAsc.add((decadi.get(j)));
				}

				RainfallParameters rainfallParameters = new RainfallParameters();
				rainfallParameters.setCountry(countryCode);
				rainfallParameters.setRegion(regionCodeList);
				rainfallParameters.setYearOneDates(decadiAsc);
				rainfallParameters.setYearTwoDates(new ArrayList<Date>());
				rainfallParameters.setStations(new ArrayList<String>());
				rainfallParameters.setHasAverage(true);
				rainfallParameters.setHasMap(false);
				rainfallParameters.setHasTable(false);

				RainfallCharts rainfallCharts = rainfallUtils.createRainfallChartsToCountryBrief(rainfallParameters);
				rainfallCharts.addChartToReport("RainfallCharts", rptDesign, design, countryCode);

				return decadi;
			}

		} catch (Exception e) {
			System.err.println("Report " + rptDesign + " not opened!\nReason is " + e.toString());
		}

		return null;

	}

	public String updateCountryBrief(List<String> regionCodeList, String countryCode, String rptDesign) {

		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle design = null;
		try {

			design = session.openDesign(name);

			// delete old rainfall charts
			int i = 0;
			int numerationDataset = 0;
			List<String> datasetCharts = new ArrayList<String>();
			while (design.findElement("dataGrid" + i) != null) {
				datasetCharts.add("Chart" + numerationDataset);
				numerationDataset++;
				datasetCharts.add("Chart" + numerationDataset);
				numerationDataset++;
				design.findElement("dataGrid" + i).drop();
				i++;
			}

			List<ScriptDataSetHandle> listDatasets = design.getAllDataSets();
			for (ScriptDataSetHandle element : listDatasets) {
				if (datasetCharts.contains(element.getName()))
					element.drop();
			}

			addRainfallChartsToCountryBrief(regionCodeList, countryCode, rptDesign, design);

			design.saveAs(name);

		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());
		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />";

	}

	/**
	 * #0 rptdesign #1 report
	 */
	public List<String> countryBriefTemplate(String countryCode, String countryLabel) {
		String rptDesign = getTemplate("country_brief");

		List<String> result = new ArrayList<String>();
		result.add(rptDesign);

		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle design = null;
		try {

			design = session.openDesign(name);

			ImageHandle GIEWSLogoImage = (ImageHandle) design.findElement("GIEWSLogo");
			GIEWSLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "GIEWSWorkstation.png\"");

			ImageHandle FAOLogoImage = (ImageHandle) design.findElement("FAOLogo");
			FAOLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "FAOLogo.png\"");

			TextItemHandle countryName = (TextItemHandle) design.findElement("CountryName");
			countryName.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			countryName.setContent(countryLabel);

			// footer
			TextItemHandle footerCountryName = (TextItemHandle) design.findElement("FooterCountryName");
			footerCountryName.setContent(footerCountryName.getContent() + countryLabel);

			// Food situation Text
			TextView textView = isfpDao.getCountryBriefText(countryCode);
			GridHandle reportGridHandle = (GridHandle) design.findElement("Grid1");
			RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(1);
			CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(0);
			TextItemHandle text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			if (textView == null) {
				text.setContent("<div style='font-size:9px; font-style:italic;'>Not Available</div>");
			} else {
				text.setContent(textView.getText().getContent());
			}
			gridCellHandle.getContent().add(text);

			// crop maps
			CropLegend cropImagines = cropMaps.getCropMapImagesForCountryBrief(countryCode);
			if (cropImagines != null) {
				reportGridHandle = (GridHandle) design.findElement("Map");
				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				ImageHandle image = (ImageHandle) design.getElementFactory().newImage("crop");
				image.setFile("\"" + cropImagines.getPathMapFile() + "\"");
				gridCellHandle.getContent().add(image);

				// add source
				TextItemHandle sourceCrop;
				sourceCrop = design.getElementFactory().newTextItem("sourceCrop");
				sourceCrop.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				sourceCrop.setContent("<div style='font-size:8px; font-style:italic;'>Source: FAO GIEWS</div>");
				gridCellHandle.getContent().add(sourceCrop);

				TextItemHandle legend;
				legend = design.getElementFactory().newTextItem("text");
				legend.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				String htmlLegend = "";
				for (int i = 0; i < cropImagines.getCodeCropLayers().size(); i++) {
					htmlLegend += "<div><span style='width:50px; background-color:" + codecDao.getLabelFromCodeCodingSystem(cropImagines.getCodeCropLayers().get(i), "CropLegend", "0", "EN")
							+ ";'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='font-size:8px;'>&nbsp;&nbsp;" + cropImagines.getCropName().get(i) + "&nbsp;</span></div>";
				}
				legend.setContent(htmlLegend);
				birtRow = (RowHandle) reportGridHandle.getRows().get(2);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(legend);

			} else {
				resourceUnavailable(design, "Map", 0, 0, false);
			}

			// cropland
			CropLegend cropland = cropMaps.getCroplandMapImagesForCountryBrief(countryCode);
			if (cropland != null) {
				reportGridHandle = (GridHandle) design.findElement("Map");
				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				gridCellHandle = (CellHandle) birtRow.getCells().get(1);
				ImageHandle image = (ImageHandle) design.getElementFactory().newImage("cropland");
				image.setFile("\"" + cropland.getPathMapFile() + "\"");
				gridCellHandle.getContent().add(image);

				// add source
				TextItemHandle sourceCropland;
				sourceCropland = design.getElementFactory().newTextItem("sourceCropland");
				sourceCropland.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				sourceCropland.setContent("<div style='font-size:8px; font-style:italic;'>Source: Global Land Cover V.2 ESA/ESA Globcover Project, led by MEDIAS</div>");
				gridCellHandle.getContent().add(sourceCropland);

				TextItemHandle legendCL;
				legendCL = design.getElementFactory().newTextItem("text");
				legendCL.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
				String htmlLegendCL = "";
				htmlLegendCL += "<div><span style='width:50px; background-color:#FF0000;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='font-size:8px;'>&nbsp;&nbsp;Cultivated and Managed areas&nbsp;</span></div>";
				htmlLegendCL += "<div><span style='width:50px; background-color:#005CE6;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='font-size:8px;'>&nbsp;&nbsp;Post-flooding or irrigated croplands (or aquatic)&nbsp;</span></div>";
				htmlLegendCL += "<div><span style='width:50px; background-color:#FFFF00;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='font-size:8px;'>&nbsp;&nbsp;Rainfed croplands.Rainfed herbaceous crops.Rainfed shrub or tree crops&nbsp;</span></div>";
				htmlLegendCL += "<div><span style='width:50px; background-color:#E69800;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='font-size:8px;'>&nbsp;&nbsp;Mosaic cropland (50-70%) / vegetation (grassland/shrubland/forest) (20-50%)&nbsp;</span></div>";
				htmlLegendCL += "<div><span style='width:50px; background-color:#38A800;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='font-size:8px;'>&nbsp;&nbsp;Mosaic vegetation (grassland/shrubland/forest) (50-70%) / cropland (20-50%)&nbsp;</span></div>";
				legendCL.setContent(htmlLegendCL);
				birtRow = (RowHandle) reportGridHandle.getRows().get(2);
				gridCellHandle = (CellHandle) birtRow.getCells().get(1);
				gridCellHandle.getContent().add(legendCL);
			} else {
				resourceUnavailable(design, "Map", 0, 1, false);
			}

			// Crop Calendar
			File cropCalendar = new File(Setting.getFenixPath() + Setting.getApplicationName() + "/cropCalendars/" + countryCode + "_CAL1E.GIF");
			if (cropCalendar.exists()) {
				ImageHandle imageHandle = design.getElementFactory().newImage("CropCalendar");
				imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
				imageHandle.setFile("\"" + Setting.getFenixPath() + Setting.getApplicationName() + "/cropCalendars/" + countryCode + "_CAL1E.GIF\"");

				reportGridHandle = (GridHandle) design.findElement("CropCalendar");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(imageHandle);
			} else
				resourceUnavailable(design, "CropCalendar", 1, 0, true);

			// create DataSource
			ScriptDataSourceHandle dataSourceHandle = design.getElementFactory().newScriptDataSource("srcScripted");
			design.getDataSources().add(dataSourceHandle);

			// Rainfall charts
			List<Code> allProvinces = codecDao.findAllGaul1FromGaul0(countryCode);
			// int numProvinces;
			// if (allProvinces.size() >= 6) numProvinces=6;
			// else numProvinces=allProvinces.size();

			int numPro;
			if (allProvinces.size() < 4)
				numPro = allProvinces.size();
			else
				numPro = 4;

			List<Code> provinceList = allProvinces.subList(0, numPro);
			List<String> regionCodeList = new ArrayList<String>();
			for (Code code : provinceList) {
				regionCodeList.add(code.getCode());
			}

			List<Date> decadi = addRainfallChartsToCountryBrief(regionCodeList, countryCode, rptDesign, design);

			if (decadi != null) {

				List<String> ndviImagines = rainfallUtils.getMapImagesForCountryBrief(decadi.subList(0, 4), countryCode);
				reportGridHandle = (GridHandle) design.findElement("NDVI");

				int row = 0;
				int col = 0;
				for (String ndvi : ndviImagines) {
					if (col > 1) {
						row = 2;
						col = 0;
					}
					birtRow = (RowHandle) reportGridHandle.getRows().get(row);
					gridCellHandle = (CellHandle) birtRow.getCells().get(col);

					Map<String, String> monthLabelMap;

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

					if (!ndvi.equals("No Map")) {
						String decPar = parseImageName(ndvi);
						String label = "";
						String date = String.valueOf(decPar.charAt(decPar.length() - 1));
						if (date.equals("1"))
							label += "1st Dekad ";
						else if (date.equals("2"))
							label += "2nd Dekad ";
						else if (date.equals("3"))
							label += "3rd Dekad ";
						label += monthLabelMap.get(decPar.substring(2, 4)) + " ";
						label += "20" + decPar.substring(0, 2);

						// add decadeLabel
						TextItemHandle decadeLabel;
						decadeLabel = design.getElementFactory().newTextItem("text");
						decadeLabel.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
						decadeLabel.setContent("<div style='font-size:8px; font-weight:bold;'>" + label + "</div>");
						gridCellHandle.getContent().add(decadeLabel);

						ImageHandle image = (ImageHandle) design.getElementFactory().newImage("ndvi");
						image.setFile("\"" + ndvi + "\"");
						gridCellHandle.getContent().add(image);

						// add source
						TextItemHandle source;
						source = design.getElementFactory().newTextItem("text");
						source.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
						source.setContent("<div style='font-size:8px; font-style:italic;'>Source: FAO GIEWS  (derived from SPOT Vegetation Index, resolution 1 Km)</div>");
						gridCellHandle.getContent().add(source);
					} else {
						resourceUnavailable(design, "NDVI", row, col, false);
					}

					col++;
				}

				// add NDVI legend
				reportGridHandle = (GridHandle) design.findElement("NDVI_Legend");
				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				ImageHandle image = (ImageHandle) design.getElementFactory().newImage("ndviLegend");
				image.setFile("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ImgForTemplate/legend_ndvi.jpg\"");
				gridCellHandle.getContent().add(image);

			} else {

				resourceUnavailable(design, "RainfallCharts", 0, 0, false);
				resourceUnavailable(design, "NDVI", 0, 0, false);

			}

			// cereal balance
			CerealBalanceChartBean cerealBalanceChartBean = cerealBalanceCBDao.getChartData(countryCode);
			if (cerealBalanceChartBean.getData() != null) {
				CerealBalanceChart cerealBalanceChart = new CerealBalanceChart(cerealBalanceChartBean, codecDao);
				cerealBalanceChart.createCharts();
				cerealBalanceChart.addChartToReport("CerealBalance", rptDesign, design, countryCode);
			}

			// Price Monitoring
			textView = cerealBalanceCBDao.getPriceMonitoringText(countryCode);
			reportGridHandle = (GridHandle) design.findElement("PriceMonitoring");
			birtRow = (RowHandle) reportGridHandle.getRows().get(0);
			gridCellHandle = (CellHandle) birtRow.getCells().get(0);
			text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			if (textView == null) {
				text.setContent("<div style='font-size:9px; font-style:italic;'>Not Available</div>");
			} else {
				text.setContent(textView.getText().getContent());
			}
			gridCellHandle.getContent().add(text);

			// ISFP charts
			ISFPCountries isfpCountries = new ISFPCountries();
			if (isfpCountries.getCountryMap().get(countryCode) != null) {
				List<Object> info = isfpDao.getFoodPrices(countryCode);

				if (info.size() > 0 && (((List<ChartFoodPricesInfo>) info.get(0)).get(0) != null || ((List<ChartFoodPricesInfo>) info.get(0)).get(1) != null)) {

					List<ChartFoodPricesInfo> infoChart = (List<ChartFoodPricesInfo>) info.get(0);

					ISFPChart chart = new ISFPChart(infoChart, "", "294", "307");
					chart.addChartToReport("ISFPChart", rptDesign, design);

				}
			}

			// Policy Monitoring
			textView = cerealBalanceCBDao.getPolicyMonitoringText(countryCode);
			reportGridHandle = (GridHandle) design.findElement("PolicyMonitoring");
			birtRow = (RowHandle) reportGridHandle.getRows().get(0);
			gridCellHandle = (CellHandle) birtRow.getCells().get(0);
			text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			if (textView == null) {
				text.setContent("<div style='font-size:9px; font-style:italic;'>Not Available</div>");
			} else {
				text.setContent(textView.getText().getContent());
			}
			gridCellHandle.getContent().add(text);

			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());
		}

		result.add("<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />");

		return result;
	}

	/**
	 * @param country
	 *            , country[0] = featureCode country[1] = label
	 */
	public String ISFPTemplate(List<String> country) {
		String rptDesign = getTemplate("countryprofile");

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

			ImageHandle img;
			String tmp;

			ImageHandle headerImage = (ImageHandle) design.findElement("HeaderImage");
			headerImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "countryprofileheader.png\"");

			TextItemHandle countryName = (TextItemHandle) design.findElement("CountryName");
			countryName.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			countryName.setContent(country.get(1));

			// footer
			TextItemHandle footerCountryName = (TextItemHandle) design.findElement("FooterCountryName");
			footerCountryName.setContent(footerCountryName.getContent() + country.get(1));

			// Food situation Text
			TextView textView = isfpDao.getFoodSituationText(country.get(0));
			GridHandle reportGridHandle = (GridHandle) design.findElement("ObjectGrid0");
			RowHandle birtRow = (RowHandle) reportGridHandle.getRows().get(1);
			CellHandle gridCellHandle = (CellHandle) birtRow.getCells().get(0);
			TextItemHandle text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			if (textView == null) {
				text.setContent("<div style='font-size:9px; font-style:italic;'>Not Available</div>");
			} else {
				text.setContent(textView.getText().getContent());
			}
			gridCellHandle.getContent().add(text);
			if (textView != null)
				addSource(design, "ObjectGrid0", 1, 0, "Source: FAO Global Information and Early Warning System", false);

			// create DataSource
			ScriptDataSourceHandle dataSourceHandle = design.getElementFactory().newScriptDataSource("srcScripted");
			design.getDataSources().add(dataSourceHandle);

			List<List<String>> dati;

			// Cereal Production
			dati = isfpDao.getCerealProduction(country.get(0), "Cereal");
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid1a");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(2);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "0.76in", "0.263in", "7px", "Cereal Production"));
				addSource(design, "ObjectGrid1a", 1, 2, "Source: FAO Global Information and Early Warning System", false);
			} else
				resourceUnavailable(design, "ObjectGrid1a", 1, 2, false);

			// add Cereal Balance
			dati = isfpDao.getCerealBalance(country.get(0));
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid1a");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "0.85%", "0.25%", "8px", "Cereal Balance"));
				addSource(design, "ObjectGrid1a", 1, 0, "Source: FAO Global Information and Early Warning System", false);
			} else
				resourceUnavailable(design, "ObjectGrid1a", 1, 0, false);

			// Cereal Imports vs Production
			dati = isfpDao.getCerealVsProduction(country.get(0));
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("CerealvsProduction");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "0.85%", "0.25%", "8px", "CerealvsProduction"));
				addSource(design, "CerealvsProduction", 1, 0, "Source: FAO Global Information and Early Warning System", false);
			} else
				resourceUnavailable(design, "CerealvsProduction", 1, 0, false);

			// Crop production
			ISFPBeanForBirt cropProduction = isfpDao.getCropProduction(country.get(0), "Crop");
			dati = cropProduction.getTable();
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid2a");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);

				List<String> extraHeader = dati.get(0);
				gridCellHandle.getContent().add(TableAsGrid.getExtraHeader(extraHeader, design, "0.70in", "0.789in", "Crop Production"));
				dati.remove(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "0.76in", "0.263in", "7px", "Crop Production"));
				addSource(design, "ObjectGrid2a", 1, 0, "Source: " + cropProduction.getSource().getTitle(), false);
			} else
				resourceUnavailable(design, "ObjectGrid2a", 1, 0, false);

			// Crop calendar
			tmp = country.get(1).replaceAll(" ", "");
			img = getISFPTable("CropCalendar", design, tmp);
			if (img != null) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid2a");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(2);
				gridCellHandle.getContent().add(img);
			} else
				resourceUnavailable(design, "ObjectGrid2a", 1, 2, true);

			// Food Accessibility Text
			TextView foodAccessibilityText = isfpDao.getFoodAccessibilityText(country.get(0));
			GridHandle reportGridHandleFA = (GridHandle) design.findElement("foodAccessibilityText");
			RowHandle birtRowFA = (RowHandle) reportGridHandleFA.getRows().get(1);
			CellHandle gridCellHandleFA = (CellHandle) birtRowFA.getCells().get(0);
			TextItemHandle textFA = design.getElementFactory().newTextItem("text");
			textFA.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			if (foodAccessibilityText == null) {
				textFA.setContent("<div style='font-size:9px; font-style:italic;'>Not Available</div>");
			} else {
				textFA.setContent(foodAccessibilityText.getText().getContent());
			}
			gridCellHandleFA.getContent().add(textFA);
			if (foodAccessibilityText != null)
				addSource(design, "foodAccessibilityText", 1, 0, "Source: E.C. Rapid Appraisal.", false);

			// add Food Prices
			List<Object> info = isfpDao.getFoodPrices(country.get(0));
			if (info.size() > 0 && (((List<ChartFoodPricesInfo>) info.get(0)).get(0) != null || ((List<ChartFoodPricesInfo>) info.get(0)).get(1) != null)) {

				List<ChartFoodPricesInfo> infoChart = (List<ChartFoodPricesInfo>) info.get(0);

				ISFPChart chart = new ISFPChart(infoChart, country.get(1), "294", "307");
				chart.addChartToReport("FoodPrices", rptDesign, design);
				if (infoChart.get(0) != null)
					addSource(design, "FoodPrices", 1, 0, "Source: FAO Global Information and Early Warning System", false);

				if (infoChart.get(1) != null)
					addSource(design, "FoodPrices", 1, 1, "Source: FAO Global Information and Early Warning System", false);

				if (info.get(0) != null && info.get(1) != null) {
					reportGridHandle = (GridHandle) design.findElement("TableFoodPrices");
					birtRow = (RowHandle) reportGridHandle.getRows().get(1);
					gridCellHandle = (CellHandle) birtRow.getCells().get(0);
					List<List<String>> tablePrices = (List<List<String>>) info.get(1);
					List<String> numColExtraHeader = tablePrices.get(0);
					int numRowsBody = (tablePrices.get(2).size() - 1);
					String widthBodyCol = String.valueOf((6.27 / numRowsBody));
					List<String> otherDim = new ArrayList<String>();
					for (String dim : numColExtraHeader) {
						otherDim.add(String.valueOf(((6.27 / numRowsBody) * Integer.valueOf(dim))));
					}

					tablePrices.remove(0);

					List<String> extraHeader = tablePrices.get(0);
					gridCellHandle.getContent().add(TableAsGrid.getExtraHeader(extraHeader, design, "0.2in", otherDim, "Table Food Prices"));
					tablePrices.remove(0);
					gridCellHandle.getContent().add(TableAsGrid.getTable(tablePrices, design, "0.2in", widthBodyCol + "in", "8px", "Table Food Prices"));
					addSource(design, "TableFoodPrices", 1, 0, "Source: FAO Global Information and Early Warning System", false);
				}
			} else {
				resourceUnavailable(design, "FoodPrices", 1, 0, false);
				// resourceUnavailable(design, "FoodPrices", 1, 1, false);
			}

			// add Poverty
			dati = isfpDao.getPoverty(country.get(0));
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid3a");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "0.85%", "0.25%", "8px", "Poverty"));
				addSource(design, "ObjectGrid3a", 1, 0, "Sources: World Bank - World Development Indicators", false);
			} else
				resourceUnavailable(design, "ObjectGrid3a", 1, 0, false);

			// add Food Consumption and Expenditures
			dati = isfpDao.getFoodConsAndExpen(country.get(0));
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid3b");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "0.85%", "0.25%", "8px", "Food Consumption and Expenditures"));
				addSource(design, "ObjectGrid3b", 1, 0, "Source: FAO Food Security Statistics: http://www.fao.org/es/ESS/faostat/foodsecurity/index_en.ht", false);
			} else
				resourceUnavailable(design, "ObjectGrid3b", 1, 0, false);
			// add Fertilizers
			ISFPBeanForBirt result = isfpDao.getFertilizers(country.get(0));
			dati = result.getTable();
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid5");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);

				List<String> extraHeader = dati.get(0);
				gridCellHandle.getContent().add(TableAsGrid.getExtraHeader(extraHeader, design, "1.7in", "1.59in", "Fertilizers"));
				dati.remove(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "1.8in", "0.53in", "8px", "Fertilizers"));
				addSource(design, "ObjectGrid5", 1, 0, "Source: " + result.getSource().getTitle(), false);
			} else
				resourceUnavailable(design, "ObjectGrid5", 1, 0, false);

			// add Seeds
			result = isfpDao.getSeeds(country.get(0));
			dati = result.getTable();
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid5");
				birtRow = (RowHandle) reportGridHandle.getRows().get(3);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				int numRowsBody = (dati.get(1).size() - 1);
				String widthHeaderCol = String.valueOf(((5.47 / numRowsBody) * 3));
				String widthBodyCol = String.valueOf((5.47 / numRowsBody));
				List<String> extraHeader = dati.get(0);
				gridCellHandle.getContent().add(TableAsGrid.getExtraHeader(extraHeader, design, "1in", widthHeaderCol + "in", "Seeds"));
				dati.remove(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "1in", widthBodyCol + "in", "8px", "Seeds"));
				addSource(design, "ObjectGrid5", 3, 0, "Source: " + result.getSource().getTitle(), false);
			} else
				resourceUnavailable(design, "ObjectGrid5", 3, 0, false);

			// add Fuel Prices
			dati = isfpDao.getFuelPrices(country.get(0));
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid5");
				birtRow = (RowHandle) reportGridHandle.getRows().get(5);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "17%", "8%", "8px", "Fuel Prices"));
				addSource(design, "ObjectGrid5", 5, 0, "Source: Deutsche Gesellschaft fur Technische Zusammenarbeit", false);
			} else
				resourceUnavailable(design, "ObjectGrid5", 5, 0, false);

			// add key economic
			dati = isfpDao.getKeyIndicators(country.get(0));
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid6");
				birtRow = (RowHandle) reportGridHandle.getRows().get(1);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "0.85%", "0.25%", "8px", "key economic"));
				addSource(design, "ObjectGrid6", 1, 0, "Source: IMF - International Financial Statistics; Economist Intelligence Unit forecasts", false);
			} else
				resourceUnavailable(design, "ObjectGrid6", 1, 0, false);

			// add Employment in agriculture
			dati = isfpDao.getEmployment(country.get(0));
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid6");
				birtRow = (RowHandle) reportGridHandle.getRows().get(3);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "17%", "8%", "8px", "Employment in agriculture"));
				addSource(design, "ObjectGrid6", 3, 0, "Source: World Bank", false);
			} else
				resourceUnavailable(design, "ObjectGrid6", 3, 0, false);

			// add demography
			dati = isfpDao.getDemography(country.get(0));
			if (dati.size() > 0) {
				reportGridHandle = (GridHandle) design.findElement("ObjectGrid6");
				birtRow = (RowHandle) reportGridHandle.getRows().get(5);
				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
				gridCellHandle.getContent().add(TableAsGrid.getTable(dati, design, "17%", "8%", "8px", "demography"));
				addSource(design, "ObjectGrid6", 5, 0, "Source: World Bank - World Development Indicators", false);
			} else
				resourceUnavailable(design, "ObjectGrid6", 5, 0, false);

			// Implementation Strategy Text
			TextView textViewIS = isfpDao.getImplementationStrategyText(country.get(0));
			GridHandle reportGridHandleIS = (GridHandle) design.findElement("ObjectGrid7");
			RowHandle birtRowIS = (RowHandle) reportGridHandleIS.getRows().get(1);
			CellHandle gridCellHandleIS = (CellHandle) birtRowIS.getCells().get(0);
			TextItemHandle textIS = design.getElementFactory().newTextItem("text");
			textIS.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			if (textViewIS == null) {
				textIS.setContent("<div style='font-size:9px; font-style:italic;'>Not Available</div>");
			} else {
				textIS.setContent(textViewIS.getText().getContent());
			}
			gridCellHandleIS.getContent().add(textIS);
			if (textViewIS != null)
				addSource(design, "ObjectGrid7", 1, 0, "Source: E.C. Rapid Appraisal.", false);

			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

			// double newHeight = Double.parseDouble(this.height)+1;
			((GridHandle) design.findElement("ObjectGrid3a")).setWidth("3.27in");

			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());
		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />";
	}

	// ----------------------- IPC stuff --------------------------------

	private Document getIPCWorkflowDocument(String workflowId) throws Exception {
		HttpServletRequest request = ServletUtils.getRequest();

		String urlString = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/fenix-ipc/org.fao.ipc.IPC/service?ss=getWorkflowInfo&workflowId=" + workflowId;

		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		int responseCode = conn.getResponseCode();
		if (responseCode != HttpURLConnection.HTTP_OK)
			throw new Exception("http error: " + responseCode + "(" + conn.getResponseMessage() + ")");

		SAXBuilder builder = new SAXBuilder();

		return builder.build(conn.getInputStream());
	}

	public String getIPCTemplate(String workflowId, String countryLabel) {
//		
//		LOGGER.info("here");
//		String rptDesign = getTemplate("IPC_Template");
//
//		List<String> result = new ArrayList<String>();
//		result.add(rptDesign);
//
//		DesignConfig dConfig = new DesignConfig();
//		dConfig.setBIRTHome(Setting.getReportEngine());
//
//		DesignEngine dEngine = new DesignEngine(dConfig);
//		// Create a session handle, using the system locale.
//		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
//		// Create a handle for an existing report design.
//		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
//		ReportDesignHandle design = null;
//		try {
//
//			design = session.openDesign(name);
//
//			ImageHandle GIEWSLogoImage = (ImageHandle) design.findElement("GIEWSLogo");
//			GIEWSLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "ec_logo.png\"");
//
//			ImageHandle FAOLogoImage = (ImageHandle) design.findElement("FAOLogo");
//			FAOLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "faologo.gif\"");
//
//			ImageHandle Legend1 = (ImageHandle) design.findElement("Legend1");
//			Legend1.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "legend1.gif\"");
//
//			ImageHandle Legend2 = (ImageHandle) design.findElement("Legend2");
//			Legend2.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "legend2.gif\"");
//
//			ImageHandle Legend3 = (ImageHandle) design.findElement("Legend3");
//			Legend3.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "legend3.gif\"");
//
//			Document document = getIPCWorkflowDocument(workflowId);
//			IPCData ipcData = ipcDao.getIPCDataFromXMLDocument(document);
//
//			// header
//			TextItemHandle headerTitle = (TextItemHandle) design.findElement("title");
//			headerTitle.setContent(countryLabel.toUpperCase() + " INTEGRATED FOOD SECURITY PHASE CLASSIFICATION");
//
//			GridHandle reportGridHandle;
//			RowHandle birtRow;
//			CellHandle gridCellHandle;
//			TextItemHandle text;
//
//			// Description
//			text = design.getElementFactory().newTextItem("text");
//			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//			text.setContent("<center style='font-size:14px;'>" + ipcData.getDescription() + "</center>");
//
//			reportGridHandle = (GridHandle) design.findElement("generalInfo");
//			birtRow = (RowHandle) reportGridHandle.getRows().get(1);
//			gridCellHandle = (CellHandle) birtRow.getCells().get(0);
//			gridCellHandle.getContent().add(text);
//
//			// Status
//			text = design.getElementFactory().newTextItem("text");
//			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//			text.setContent("<center style='font-size:14px; color:#FF0000;'>Status: " + ipcData.getStatus() + "</center><br>");
//
//			reportGridHandle = (GridHandle) design.findElement("generalInfo");
//			birtRow = (RowHandle) reportGridHandle.getRows().get(2);
//			gridCellHandle = (CellHandle) birtRow.getCells().get(0);
//			gridCellHandle.getContent().add(text);
//
//			// Map
//			String code = "IPC_MAP_" + workflowId;
//			MapView mapView = mapDao.findByCode(code);
//			MapRetriever map = new MapRetriever(mapView, System.getProperty("java.io.tmpdir"));
//			map.setWidth(590);
//			map.setHeight(590);
//			BufferedImage image = map.getMapImage();
//			String nameFile = String.valueOf((Math.random() * 10)) + ".png";
//			try {
//				// File f=new File(System.getProperty("java.io.tmpdir") +
//				// File.separator + "/MapImg");
//				ImageIO.write(image, "png", new File(System.getProperty("java.io.tmpdir") + File.separator + nameFile));
//				ImageHandle imageHandle = design.getElementFactory().newImage("Map");
//				imageHandle.setDisplayName("map");
//				imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
//				imageHandle.setFile("\"" + System.getProperty("java.io.tmpdir") + File.separator + nameFile + "\"");
//
//				reportGridHandle = (GridHandle) design.findElement("Map");
//				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
//				gridCellHandle = (CellHandle) birtRow.getCells().get(0);
//				gridCellHandle.getContent().add(imageHandle);
//
//				// legend
//				reportGridHandle = (GridHandle) design.findElement("mapLegend");
//				birtRow = (RowHandle) reportGridHandle.getRows().get(0);
//
//				List<GeoView> layerList = mapView.getDetachedSortedGeoViewList();
//				int flagCell = 0;
//				for (GeoView gv : layerList) {
//					if (gv.getWmsMapProvider() instanceof ProjectedData) {
//
//						gridCellHandle = (CellHandle) birtRow.getCells().get(flagCell);
//
//						text = design.getElementFactory().newTextItem("text");
//						text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//						text.setContent("<div style='font-size:14px; font-weight:bold;'>" + gv.getTitle() + "</div>");
//						gridCellHandle.getContent().add(text);
//
//						LegendRetriever lr = new LegendRetriever();
//						lr.setLayer(gv);
//						lr.setWidth(34);
//						lr.setHeight(13);
//
//						String fileName = System.getProperty("java.io.tmpdir") + File.separator + gv.getTitle() + ".png";
//						File legendImage = new File(fileName);
//
//						if (lr.retrieve(legendImage)) {
//							ImageHandle legend = design.getElementFactory().newImage("Lenged");
//							legend.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
//							legend.setFile("\"" + fileName + "\"");
//							gridCellHandle.getContent().add(legend);
//						}
//
//						flagCell++;
//					}
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			// Tables
//			reportGridHandle = (GridHandle) design.findElement("table");
//			birtRow = (RowHandle) reportGridHandle.getRows().get(1);
//			gridCellHandle = (CellHandle) birtRow.getCells().get(0);
//
//			// Moderator
//			text = design.getElementFactory().newTextItem("text");
//			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//			text.setContent("<div style='font-size:14px; color:#0000FF;'>Moderator: " + ipcData.getModerator() + "</div>");
//			gridCellHandle.getContent().add(text);
//
//			for (IPCTable ipcTable : ipcData.getTables()) {
//
//				// space
//				text = design.getElementFactory().newTextItem("text");
//				text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//				text.setContent("<br>");
//				gridCellHandle.getContent().add(text);
//
//				// title table
//				text = design.getElementFactory().newTextItem("text");
//				text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//				text.setContent("<div style='font-size:12px;'>" + ipcTable.getTitle() + "</div>");
//				gridCellHandle.getContent().add(text);
//
//				gridCellHandle.getContent().add(TableAsGrid.getTable(ipcTable.getTable(), design, "0.50%", "0.50%", "10px", ""));
//
//			}
//
//			// footer
//			TextItemHandle footerCountryName = (TextItemHandle) design.findElement("FooterCountryName");
//			footerCountryName.setContent(countryLabel);
//
//			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);
//
//		} catch (Exception e) {
//			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());
//		}
//
//		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />";
		return "";
	}

	private org.w3c.dom.Document getIPCWorkflowDocument(Long workId) throws Exception {

		String xml = ipcDao.getXml(workId);
		// LOGGER.info("xml\n" + xml);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		org.w3c.dom.Document document = db.parse(stream);
		return document;
	}

	public String getIPCTemplate(IPCReportBeanVO reportBean) {
//		LOGGER.info("here2");

		String rptDesign = getTemplate("IPC_Template");

		List<String> result = new ArrayList<String>();
		result.add(rptDesign);

		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);

		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle design = null;
		try {

			design = session.openDesign(name);

			ImageHandle GIEWSLogoImage = (ImageHandle) design.findElement("ECLogo");
			GIEWSLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "ec_LOGO2.png\"");

			ImageHandle FAOLogoImage = (ImageHandle) design.findElement("FAOLogo");
			FAOLogoImage.setWidth("1.5cm");
			FAOLogoImage.setHeight("1.5cm");
			FAOLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "fao.png\"");

			ImageHandle IPCLegendImage = (ImageHandle) design.findElement("IPCLegend");
			IPCLegendImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "ipc_legend.jpg\"");

			ImageHandle GIEWSLogoImageLandscape = (ImageHandle) design.findElement("ECLogo1");
			GIEWSLogoImageLandscape.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "ec_LOGO2.png\"");

			ImageHandle FAOLogoImageLandscape = (ImageHandle) design.findElement("FAOLogo1");
			FAOLogoImageLandscape.setWidth("1.5cm");
			FAOLogoImageLandscape.setHeight("1.5cm");
			FAOLogoImageLandscape.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "fao.png\"");

			ImageHandle IPCLegendImageLandscape = (ImageHandle) design.findElement("IPCLegend1");
			IPCLegendImageLandscape.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "ipc_legend.jpg\"");

			ImageHandle Legend1Image = (ImageHandle) design.findElement("Legend1");
			Legend1Image.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "legend1.gif\"");

			ImageHandle Legend2Image = (ImageHandle) design.findElement("Legend2");
			Legend2Image.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "legend2.png\"");

			ImageHandle Legend3Image = (ImageHandle) design.findElement("Legend3");
			Legend3Image.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "legend3.gif\"");

			// header
			TextItemHandle headerTitle = (TextItemHandle) design.findElement("title");
			headerTitle.setContent(reportBean.getCountry() + " Integrated Food Security Phase Classification");

			TextItemHandle headerTitleLandscape = (TextItemHandle) design.findElement("title1");
			headerTitleLandscape.setContent(reportBean.getCountry() + " Integrated Food Security Phase Classification");

			GridHandle reportGridHandle;
			RowHandle birtRow;
			CellHandle gridCellHandle;
			TextItemHandle text;

			// Description
			text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			text.setContent("<center style='font-size:14px;'>Description: " + reportBean.getDescription() + "</center>");

			reportGridHandle = (GridHandle) design.findElement("generalInfo");
			birtRow = (RowHandle) reportGridHandle.getRows().get(1);
			gridCellHandle = (CellHandle) birtRow.getCells().get(0);
			gridCellHandle.getContent().add(text);

			// Status
			text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			text.setContent("<center style='font-size:14px; color:#FF0000;'>Status: " + reportBean.getStatus() + " </center><br>");

			reportGridHandle = (GridHandle) design.findElement("generalInfo");
			birtRow = (RowHandle) reportGridHandle.getRows().get(2);
			gridCellHandle = (CellHandle) birtRow.getCells().get(0);
			gridCellHandle.getContent().add(text);

			
//			System.out.println("creating map");
//			System.out.println("creating map2");
//			// Map
			String code = "IPC_MAP_" + reportBean.getWorkFlowId().toString() + "_" + reportBean.getContributorId();
//			System.out.println("code: " + code);
			MapView mapView = mapDao.findByCode(code);
//			System.out.println("got map");
			if (mapView != null) {
//				System.out.println("ok?" + mapView.getTitle());
//				mapView.setWidth(1024);
//				mapView.setHeight(768);
				mapView.setWidth(665);
				mapView.setHeight(530);
//				System.out.println("ok?" + mapView.getHeight());
//				System.out.println("ok?" + mapView.getWidth());
				WMSMapRetriever wmr = new WMSMapRetriever(System.getProperty("java.io.tmpdir"));
//				System.out.println("map retrieved111");
				wmr.setHeight(mapView.getHeight());
				wmr.setWidth(mapView.getWidth());
				wmr.setBoundingBox(mapView.getBoundingBox());
				
				for(GeoView geoView : mapView.getDetachedSortedGeoViewList()) {
					if( ! geoView.isHidden()) {
//						System.out.println(geoView.getTitle());
//						System.out.println(geoView.getWmsMapProvider().getGetMapUrl());
//						System.out.println( geoView.getWmsMapProvider().getLayerName());
//						System.out.println( geoView.getStyleName());
//						System.out.println( geoView.getCqlFilter());
						
						if ( geoView.getOpacity() == null )
							geoView.setOpacity(new Float(1.0));
						System.out.println(geoView.getOpacity());
						wmr.addLayer(geoView);
					}
				}
//				MapRetriever mapRetriever = new MapRetriever(mapView, System.getProperty("java.io.tmpdir"));
//				System.out.println("map retrieved");
//				mapRetriever.setWidth(1024);
//				mapRetriever.setHeight(768);
//				System.out.println("error?");
				BufferedImage bi = wmr.getMapImage();
//				BufferedImage image = map.getMapImage();
//				System.out.println("error image");
				String nameFile = String.valueOf((Math.random() * 10)) + ".png";
				try {
//					System.out.println("error file");
					 File f=new File(System.getProperty("java.io.tmpdir") +  File.separator + nameFile);
//					 System.out.println("error file 2");
					ImageIO.write(bi, "png", new File(System.getProperty("java.io.tmpdir") + File.separator + nameFile));
					ImageHandle imageHandle = design.getElementFactory().newImage("Map");
					imageHandle.setDisplayName("map");
					imageHandle.setSource(DesignChoiceConstants.IMAGE_REF_TYPE_FILE);
					imageHandle.setFile("\"" + System.getProperty("java.io.tmpdir") + File.separator + nameFile + "\"");
					imageHandle.setWidth("17.5cm");
					imageHandle.setHeight("14cm");
					reportGridHandle = (GridHandle) design.findElement("Map");
					birtRow = (RowHandle) reportGridHandle.getRows().get(0);
					gridCellHandle = (CellHandle) birtRow.getCells().get(0);
					gridCellHandle.getContent().add(imageHandle);
				
//				mapRetriever.setWidth(1024);
//				mapRetriever.setHeight(768);
//				
//				BufferedImage bi = mapRetriever.getMapImage();

//				try {
//					File png =new File("../exportObject/simone.png");
//					File png = File.createTempFile("map", ".png", "/home/vortex/Desktop/");
//					ImageIO.write(bi, "png", png);
//					png.deleteOnExit(); // todo: we need a process that deletes temp files after a while
//					return png.getName();
//				} catch (IOException ex) {
//					LOGGER.warn("Error in saving map image", ex);
//					return null;
//				}

//				System.out.println("created");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// Tables
			reportGridHandle = (GridHandle) design.findElement("table");
			birtRow = (RowHandle) reportGridHandle.getRows().get(1);
			gridCellHandle = (CellHandle) birtRow.getCells().get(0);

			// Moderator
			text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			text.setContent("<div style='font-size:14px; color:#000000;'>Moderator: " + reportBean.getContributorFirstName() + " " + reportBean.getContributorLastName() + "</div>");

			gridCellHandle.getContent().add(text);

			// space
			text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			text.setContent("<br><br>");
			gridCellHandle.getContent().add(text);

			// title table
			text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			text.setContent("<div style='font-size:12px;'>" + reportBean.getModuleOne() + "</div>");
			gridCellHandle.getContent().add(text);

			// space
			text = design.getElementFactory().newTextItem("text");
			text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
			text.setContent("<br>");
			gridCellHandle.getContent().add(text);

			// footer
			TextItemHandle footerCountryName = (TextItemHandle) design.findElement("FooterCountryName");
			footerCountryName.setContent("");

			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

		} catch (Exception e) {
			System.err.println("Report VO " + name + " not opened!\nReason is " + e.toString());
		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />";

//		return "";
	}

	public String exportMapToPDF(ClientMapView cmv, List<ClientGeoView> cgvlist, String typeExport) {

		MapViewUtils mapViewUtils = new MapViewUtils();
		mapViewUtils.setWmsMapProviderDao(wmsMapProviderDao);
		MapView mapView = mapViewUtils.build(cmv);

		List<GeoView> gvList = new ArrayList<GeoView>();
		for (ClientGeoView clientGeoView : cgvlist) {
			gvList.add(mapViewUtils.buildGeoView(clientGeoView, false));
		}

		MapReport map = new MapReport(mapView, gvList, 650, 400);
		String nameFile = map.createReport();

		return "/" + Setting.getBirtApplName() + "/preview?__report=" + nameFile + "&__format=" + typeExport;

	}

	public List<CodeVo> getCountryBriefList() {
		Map countryMap = isfpDao.getCountryBriefList();
		List<CodeVo> countries = new ArrayList<CodeVo>();
		Iterator<Map.Entry<String, String>> keyValuePairs = countryMap.entrySet().iterator();

		List<String> countrylabels = new ArrayList<String>();
		for (int i = 0; i < countryMap.size(); i++) {
			Map.Entry<String, String> entry = keyValuePairs.next();
			countrylabels.add((String) entry.getKey());
		}
		Collections.sort(countrylabels);

		for (int i = 0; i < countrylabels.size(); i++) {
			String code = (String) countryMap.get(countrylabels.get(i));
			CodeVo c = new CodeVo();
			c.setCode(code);
			c.setLabel(countrylabels.get(i));
			countries.add(c);
		}
		return countries;
	}

	public String getFsatmisTemplate(String region) {
		String rptDesign = getTemplate("fs_atmis");

		List<String> result = new ArrayList<String>();
		result.add(rptDesign);

		DesignConfig dConfig = new DesignConfig();
		dConfig.setBIRTHome(Setting.getReportEngine());

		DesignEngine dEngine = new DesignEngine(dConfig);
		// Create a session handle, using the system locale.
		SessionHandle session = dEngine.newSessionHandle(ULocale.ENGLISH);
		// Create a handle for an existing report design.
		String name = System.getProperty("java.io.tmpdir") + File.separator + rptDesign;
		ReportDesignHandle design = null;
		try {

			design = session.openDesign(name);

			ImageHandle GIEWSLogoImage = (ImageHandle) design.findElement("GIEWSLogo");
			GIEWSLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "GIEWSWorkstation.png\"");

			ImageHandle FAOLogoImage = (ImageHandle) design.findElement("FAOLogo");
			FAOLogoImage.setFile("\"" + Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + "ImgForTemplate" + File.separator + "FAOLogo.png\"");

			// create DataSource
			ScriptDataSourceHandle dataSourceHandle = design.getElementFactory().newScriptDataSource("srcScripted");
			design.getDataSources().add(dataSourceHandle);

			List<String> test = new ArrayList<String>();
			test.add("60");

			List<UDTable> udTableList = fsatmisDao.getChartInfo(test);

			CreateFsatmisChartForReport createFsatmisChartForReport = new CreateFsatmisChartForReport(udTableList);
			createFsatmisChartForReport.createCharts();
			createFsatmisChartForReport.addChartToReport("FirstChart", 0, 0, 0, rptDesign, design);

			design.saveAs(System.getProperty("java.io.tmpdir") + File.separator + rptDesign);

		} catch (Exception e) {
			System.err.println("Report " + name + " not opened!\nReason is " + e.toString());
		}

		return "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rptDesign + "&servletType=frameset' width='100%' height='100%' />";
	}

	public DescriptorVO columnIsDate(Long datasetId, String column) {
		Descriptor descriptor = gwtConnector.getDescriptorFromColumnName(datasetId, column);
		DescriptorVO descriptorVO = new DescriptorVO();
		if (DataType.date == descriptor.getDataType() || DataType.baseDateFrom == descriptor.getDataType() || DataType.baseDateTo == descriptor.getDataType()) {
			Dataset dataset = datasetDao.findById(datasetId);
			descriptorVO.setContentDescriptor(dataset.getPeriodTypeCode());
			return descriptorVO;
		}
		return null;
	}
	
//	public ChartBirtBean createChart(ChartWizardBean bean, String servletType, String rptDesign) {
//		List<String> result = new ArrayList<String>();;
		ChartBirtBean chart = new ChartBirtBean();
//		ChartWizardBean b = new ChartWizardBean();
//		b.setChartType("Line");
//		b.setDisposition("");
//		b.setTitle("title");
//		b.setXAxisTitle("x title");
//		b.setXAxisShowLabels(true);
//		b.setYAxisTitle("y title");
//		b.setYAxisShowLabels(true);
//		b.setPosLegend("Right");
//		b.setShowLegend(true);
//		b.setDim2_3D("Two_Dimensional");
//		b.setRotateXLabels("45");
//		
//		/** chart bean **/
//		ChartBean chartBean = new ChartBean();
//		ChartBeanValues chartBeanValues = new ChartBeanValues();
////		chartBeanValues.setxValuesMap(testHashMap());
//		chartBeanValues.setySeries(testYSeries());
//		chartBeanValues.setxValues(testXLabels());
//		
//		chartBean.setChartValues(chartBeanValues);
//		
//		b.setChartReportBean(chartBean);
//		
		

//		GraphReportEngine newChart = new GraphReportEngine(bean, this, codecDao, datasetDao, gwtConnector, rptDesign);
		
		
		
//		chart = newChart.createChart();
		
//		System.out.println("rpt: " + chart.getRptdesign());
//		System.out.println("id: " + chart.getChartElement_ID());
//		System.out.println("rep: " + rep);
//		message = "<iframe src='/" + Setting.getBirtApplName() + "/FenixBirtServletByFile?report=" + rep + "&servletType=" + servletType + "' width='100%' height='100%' />";

//		result.add(rep);
//		result.add(message);

//		return chart;
//	}
	
//	private static List<String> testXLabels() {
//		List<String> x = new ArrayList<String>();
//		x.add("2010-04-07");
//		x.add("2010-04-06");
//		x.add("2010-03-07");
//		x.add("2010-02-07");
//		return x;
//	}

//	private static List<YSerieBean> testYSeries() {
//		 List<YSerieBean> ySeries = new ArrayList<YSerieBean>();
//		
//		 List<Double> values = new ArrayList<Double>();
//		 values.add(238.0);
//		 values.add(248.0);
//		 values.add(278.0);
//		 values.add(null);
//	
//		 ySeries.add(new YSerieBean("1", values));
//		 
//		 values = new ArrayList<Double>();
//		 values.add(28.0);
//		 values.add(28.0);
//		 values.add(27.0);
//		 values.add(27.0);
//		 ySeries.add(new YSerieBean("2", values));
//		 System.out.println(ySeries);
//		return ySeries;
//	}

	
	public void chartUpdate(Long datasetID) {
//		LOGGER.info(datasetID);
//		chartUpdater.updateDatasetCharts(datasetID);
	}
	
	public List<Long> hasChart(Long datasetId) {
		return chartDao.getMostRecentCharts(datasetId);
	}

	public void chartBooleanFixer() {
		chartDao.fixBooleanCharts();
	}

	public void setIsfpDao(ISFPDao isfpDao) {
		this.isfpDao = isfpDao;
	}

	public void setMapDao(MapDao mapDao) {
		this.mapDao = mapDao;
	}

	public void setDataviewDao(DataViewDao dataviewDao) {
		this.dataviewDao = dataviewDao;
	}

	public void setTextDao(TextDao textDao) {
		this.textDao = textDao;
	}

	public void setDatasetDao(DatasetDao datasetDao) {
		this.datasetDao = datasetDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	public void setChartDao(ChartDao chartDao) {
		this.chartDao = chartDao;
	}

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

	public void setImportESTPriceDatabase(ImportESTPriceDatabase importESTPriceDatabase) {
		this.importESTPriceDatabase = importESTPriceDatabase;
	}

	public void setGwtConnector(GwtConnector gwtConnector) {
		this.gwtConnector = gwtConnector;
	}

	public void setRainfallDao(RainfallDao rainfallDao) {
		this.rainfallDao = rainfallDao;
	}

	public void setRainfallUtils(RainfallUtils rainfallUtils) {
		this.rainfallUtils = rainfallUtils;
	}

	public void setCropMaps(CropMaps cropMaps) {
		this.cropMaps = cropMaps;
	}

	public void setCerealBalanceCBDao(CerealBalanceCBDao cerealBalanceCBDao) {
		this.cerealBalanceCBDao = cerealBalanceCBDao;
	}

	public void setWmsMapProviderDao(WMSMapProviderDao wmsMapProviderDao) {
		this.wmsMapProviderDao = wmsMapProviderDao;
	}

	public void setIpcDao(IPCDao ipcDao) {
		this.ipcDao = ipcDao;
	}

	public void setFsatmisDao(FsatmisDao fsatmisDao) {
		this.fsatmisDao = fsatmisDao;
	}

	public void setSaveUniqueDao(SaveUniqueDao saveUniqueDao) {
		this.saveUniqueDao = saveUniqueDao;
	}

	public void setChartUpdater(ChartUpdater chartUpdater) {
		this.chartUpdater = chartUpdater;
	}


	public void setOlapDao(OLAPDao olapDao) {
		this.olapDao = olapDao;
	}
	

	

	

}