package org.fao.fenix.web.modules.birt.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.common.vo.ChartWizardBean;
import org.fao.fenix.web.modules.birt.common.vo.FormatChartVo;
import org.fao.fenix.web.modules.birt.common.vo.ReportVo;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;
import org.fao.fenix.web.modules.ipc.common.vo.IPCReportBeanVO;
import org.fao.fenix.web.modules.map.common.vo.ClientGeoView;
import org.fao.fenix.web.modules.map.common.vo.ClientMapView;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

public interface BirtService extends RemoteService {

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<Long> getAllReport();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> updatePreview(ChartWizardBean bean, String servletType, String rptDesign);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String openReport(Long id, String servletType);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String openReport(String rptdesign, String servletType);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String getBirtApplName();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String exportTable(Long resourceId, List<String> columnNames, String typeExport);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String exportTable(List<List<String>> data, String typeExport);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String exportAsCSV(Long datasetId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String exportChart(String rptdesign, String typeExport);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String exportText(String content, String typeExport);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<List<String>> getReport(Long reportViewId, ReportVo reportVo, String rptdesign);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String getClassTemplate(Long reportViewId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String createReport(Long id, int objectPosition, String rptdesign, String typeView, int width, int height, String reportType);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> addTextToReport(String rptDesign, String content);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String removeObjectByReport(String rptDesign, String birtObject);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String moveObjectByReport(String rptDesign, String upDown, String birtObject);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> separatorObjectByReport(String rptDesign, String HTMLObj, String birtObject);

	@Secured( { "ROLE_USER" })
	public Long saveChart(String rptDesign, long dataviewId, ChartWizardBean bean);

	@Secured( { "ROLE_USER" })
	public Long saveChartAs(String rptDesign, ChartWizardBean bean);

	@Secured( { "ROLE_USER" })
	public Long saveChartLayout(String rptDesign, long dataviewId, String Title);

	@Secured( { "ROLE_USER" })
	public Long saveReport(String rptDesign, long dataviewId, ReportVo reportVo);

	@Secured( { "ROLE_USER" })
	public Long saveReportAs(String rptDesign, ReportVo reportVo);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String nameFileById(Long dataviewId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String showHideParametersToChart(String rptDesign, Map<String, List<List<String>>> otherDimension, List<List<String>> datasets);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String resizeChart(String rptDesign, String inOut, String stretch);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String changeChartBackground(String rptDesign, String color);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public ChartWizardBean getChart(Long dataviewId, ChartWizardBean chartWizardBean);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> getTableDimensions(Long id);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String getTemplate(String templateType);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<List<String>> getRecords(long datasetId, List<String> columnNames);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<List<String>> getRecordsWithLabel(Long datasetId, List<String> columnNames);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String[] getColumnNames(long datasetId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String[] getColumnNames(List<String> datasetId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<List<String>> getColumnValues(Long datasetId, String columnName);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<List<List<String>>> getColumnsValues(Long datasetId, List<String> columnNames);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String exportChartAndTable(String title, List<List<String>> data, String xRow, String yRow, String type, String format);

	@Secured( { "ROLE_USER" })
	public Long createNewChartResource(String rptDesign, ChartWizardBean chartWizardBean);

	@Secured( { "ROLE_USER" })
	public Long createNewReportResource(String rptDesign, ReportVo reportVo);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public FenixResourceVo getNewResource(Long id);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String loadESTPriceDatabase();

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String ISFPTemplate(List<String> country);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> countryBriefTemplate(String countryCode, String countryLabel);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String updateCountryBrief(List<String> regionCodeList, String countryCode, String rptDesign);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public boolean isQualitativeDataset(long datasetId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public boolean isFlexDataset(long datasetId);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> getQuantityColumnNames(long datasetId, List<String> otherDim);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String getMUSymbol(long datasetId, List<List<String>> otherDim);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public FormatChartVo getChartInfo(String rptDesign, FormatChartVo formatChartVo);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String setChartInfo(String rptDesign, FormatChartVo formatChartVo);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String getIPCTemplate(String workflowId, String countryLabel);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String getIPCTemplate(IPCReportBeanVO reportBean);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String exportMapToPDF(ClientMapView cmv, List<ClientGeoView> cgvlist, String typeExport);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<CodeVo> getCountryBriefList();

	// @Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	// public List<OLAPChartResultVo> getOlapCharts(List<OLAPChartBeanVO>
	// olapChartbeanVo);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public List<String> addImageToReport(String rptDesign, String fileName);

	@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
	public String getFsatmisTemplate(String region);
	
	public String addTableToReport(String datasetCode, String datasetID, String rptDesign, int objectPosition, String reportType, String tableHTML);

	public String linkChart(Long chartId);
	
	public DescriptorVO columnIsDate(Long datasetId, String column);
	
	public List<Long> hasChart(Long datasetId);
	
	public void chartBooleanFixer();
	
	public List<String> changeCommentText(String rptDesign, String commentId, String content);
	
	public String getCommentText(String rptDesign, String commentId);
	
	public void chartUpdate(Long datasetID);
}

