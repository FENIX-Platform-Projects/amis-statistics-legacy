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

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BirtServiceAsync {

	public void getAllReport(AsyncCallback<List<Long>> callback);
	public void updatePreview(ChartWizardBean bean, String servletType, String rptDesign, AsyncCallback<List<String>> callback);
	public void openReport(Long id, String servletType,AsyncCallback<String> callback);
	public void openReport(String rptdesign, String servletType,AsyncCallback<String> callback);
	public void getBirtApplName(AsyncCallback<String> callback);
	public void createReport(Long id, int objectPosition, String rptdesign, String typeView, int width, int height, String reportType, AsyncCallback<String> callback);
	public void getReport(Long reportViewId, ReportVo reportVo, String rptdesign, AsyncCallback<List<List<String>>> callback);
	public void getClassTemplate(Long reportViewId, AsyncCallback<String> callback);
	
	public void addTextToReport(String rptDesign,String content,AsyncCallback<List<String>> callback);
	public void removeObjectByReport(String rptDesign,String birtObject,AsyncCallback<String> callback);
	public void moveObjectByReport(String rptDesign,String upDown,String birtObject,AsyncCallback<String> callback);
	public void separatorObjectByReport(String rptDesign,String HTMLObj,String birtObject,AsyncCallback<List<String>> callback);
	
	public void exportTable(Long resourceId, List<String> columnNames, String typeExport,AsyncCallback<String> callback);
	public void exportTable(List<List<String>> data, String typeExport,AsyncCallback<String> callback);
	public void exportAsCSV(Long datasetId, AsyncCallback<String> callback);
	public void exportText(String content, String typeExport,AsyncCallback callback);
	public void exportChart(String rptdesign, String typeExport,AsyncCallback callback);
	
	public void saveChart(String rptDesign, long dataviewId, ChartWizardBean chartWizardBean,AsyncCallback<Long> callback);
	public void saveChartAs(String rptDesign, ChartWizardBean ChartWizardBean, AsyncCallback<Long> callback);	
	
	public void saveChartLayout(String rptDesign, long dataviewId, String Title,AsyncCallback callback);
	
	public void saveReport(String rptDesign, long dataviewId, ReportVo reportVo, AsyncCallback callback);
	public void saveReportAs(String rptDesign, ReportVo reportVo, AsyncCallback callback);
	
	public void nameFileById(Long dataviewId,AsyncCallback callback);
	
	public void showHideParametersToChart(String rptDesign, Map<String, List<List<String>>>  otherDimension, List<List<String>> datasets, AsyncCallback<String> callback);
	public void resizeChart(String rptDesign,String inOut, String stretch, AsyncCallback callback);
	
	public void changeChartBackground(String rptDesign, String color, AsyncCallback<String> callback);
	
	public void getChart(Long dataviewId,ChartWizardBean chartWizardBean,AsyncCallback callback);
		
	public void getTableDimensions(Long id, AsyncCallback<List<String>> callback);
	public void getTemplate(String templateType, AsyncCallback callback);
	
	public void getRecords(long datasetId, List<String> columnNames, AsyncCallback<List<String>> callback);
	public void getRecordsWithLabel(Long datasetId, List<String> columnNames, AsyncCallback callback);
	public void getColumnNames(long datasetId, AsyncCallback<String[]> callback);
	public void getColumnNames(List<String> datasetId, AsyncCallback<String[]> callback);
	public void getColumnsValues(Long datasetId, List<String> columnNames, AsyncCallback<List<List<List<String>>>> callback);
	public void getColumnValues(Long datasetId, String columnName, AsyncCallback<List<List<String>>> callback);
	
	public void exportChartAndTable(String title,List<List<String>> data, String xRow, String yRow, String type, String format, AsyncCallback<String> callback);
	
	//----------------
    public void createNewChartResource(String rptDesign, ChartWizardBean chartWizardBean, AsyncCallback<Long> callback);
	
	public void createNewReportResource(String rptDesign, ReportVo reportVo, AsyncCallback<Long> callback);
	
	public void getNewResource(Long id, AsyncCallback<FenixResourceVo> callback);
	
	public void loadESTPriceDatabase(AsyncCallback<String> callback);
	
	public void ISFPTemplate(List<String> country, AsyncCallback<String> callback);
	
	public void countryBriefTemplate(String countryCode, String countryLabel, AsyncCallback<List<String>> callback);
	
	public void updateCountryBrief(List<String> regionCodeList, String countryCode, String rptDesign, AsyncCallback<String> callback);
	
	public void isQualitativeDataset(long datasetId, AsyncCallback<Boolean> callback);
	
	public void isFlexDataset(long datasetId, AsyncCallback<Boolean> callback);
	
	public void getQuantityColumnNames(long datasetId, List<String> otherDim, AsyncCallback<List<String>> callback);
		
	public void getMUSymbol(long datasetId, List<List<String>> otherDim, AsyncCallback<String> callback);
	
	public void getChartInfo(String rptDesign, FormatChartVo formatChartVo, AsyncCallback<FormatChartVo> callback);
	
	public void setChartInfo(String rptDesign, FormatChartVo formatChartVo, AsyncCallback<String> callback);
		
	public void getIPCTemplate(String workflowId, String countryLabel, AsyncCallback<String> callback);
	
	public void getIPCTemplate(IPCReportBeanVO reportBean, AsyncCallback<String> callback);
	
	public void exportMapToPDF(ClientMapView cmv, List<ClientGeoView> cgvlist, String typeExport, AsyncCallback<String> callbak);
	
	public void getCountryBriefList(AsyncCallback<List<CodeVo>> callback);
	
//	public void getOlapCharts(List<OLAPChartBeanVO> olapChartbeanVo, AsyncCallback<List<OLAPChartResultVo>> callback);
	
	public void addImageToReport(String rptDesign, String fileName, AsyncCallback<List<String>> callback);
	
	public void getFsatmisTemplate(String region, AsyncCallback<String> callback);
	
	public void addTableToReport(String datasetCode, String datasetID, String rptDesign, int objectPosition, String reportType, String tableHTML, AsyncCallback<String> callback);
	
	public void linkChart(Long chartId, AsyncCallback<String> callback);
	
	public void columnIsDate(Long datasetId, String column, AsyncCallback<DescriptorVO> callback);
	
	public void hasChart(Long datasetId, AsyncCallback<List<Long>> callback);
	
	public void chartBooleanFixer(AsyncCallback callback);
	
	public void changeCommentText(String rptDesign, String commentId, String content, AsyncCallback<List<String>> callback);
	
	public void getCommentText(String rptDesign, String commentId, AsyncCallback<String> callback);
	
	public void chartUpdate(Long datasetID, AsyncCallback callback);
}
