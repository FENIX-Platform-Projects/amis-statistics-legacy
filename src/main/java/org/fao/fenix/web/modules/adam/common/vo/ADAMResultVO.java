package org.fao.fenix.web.modules.adam.common.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.common.vo.venn.matrix.ADAMVennMatrixVO;
import org.fao.fenix.web.modules.haiti.common.vo.LayerVO;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

public class ADAMResultVO implements IsSerializable {
	
	private String text;
	
	private String resourceType;

	private String outputType;
	
	/** this is used for the SOs and ORs*/
	private String filterFrameworkType;
	
	private String returnFrameworkType;
		
	private String bigImagePath;
	
	private String bigHtmlImageMap;
	
	private String smallImagePath;
	
	private String smallHtmlImageMap;
	
	private String mediumImagePath;
	
	private String mediumHtmlImageMap;
	
	private String exportImagePath;
	
	private String exportHtmlImageMap;
	
	private String title;
	
	private String description;
	
	private String question;
	
	private String boxColor;
	
	private String smallWidth;
	
	private String smallHeight;
	
	private String bigWidth;
	
	private String bigHeight;
	
	private String mediumWidth;
	
	private String mediumHeight;
	
	private LinkedHashMap<String, Map<String, Double>> chartValues = new LinkedHashMap<String, Map<String,Double>>();
	
	private HashMap<String, String> xAxisFullLabels = new HashMap<String, String>();

	/** Map<GAUL Code, GAUL Label> */
	private Map<String, String> gaulMap;
	
	/** Map<DONOR Code, donor Label> */
	private Map<String, String> donorMap;
	
	/** Map<Channe Code, channe Label> */
	private Map<String, String> channelMap;
	
	/** Map<DAC Code, DAC Label> */
	private Map<String, String> dacMap;
	
	private List<String> tableHeaders = new ArrayList<String>();
	
	private Map<String, String> tableHeadersMap = new LinkedHashMap<String, String>();
	
	private List<String> highlightedTableColumns = new ArrayList<String>();
	
	
	private List<List<String>> tableContents = new ArrayList<List<String>>();
	
	private String tableHTML;
	
	private Long rows;
	
	private String measurementUnit;
	
	private List<Integer> quantitiesIdx;
	
	private Integer summaryTableColumn;
	
	private Integer groupingTableColumn;
	
	private String groupingTableColumnId;
	
	private Boolean groupTable = false;
	
	private Boolean baseModelGrid = false;
	
	private VennBeanVO vennBean = new VennBeanVO();
	
	private ADAMVennMatrixVO vennMatrix;
	
//	private Map<String, String> priorities;
	
	/** maps **/
	
	private LinkedHashMap<Long, LayerVO> layersMap = new LinkedHashMap<Long, LayerVO>();
	
//	private ADAMQueryVO switchResource;
	
	private List<ADAMQueryVO> switchResources = new ArrayList<ADAMQueryVO>();
	
	// ADAM Venn
	
	private Map<String, String> priorities;
	
	private Map<String, String> dac_sectors;
	
	// this is used for the countries and donors, to check which are the donors involved
	private Map<String, Map<String, String>> selectedPriorities;
	
	private Map<String, Map<String, String>> donorsPriorities;
	
	private Map<String, Map<String, String>> countriesPriorities;
	
    private String bigBaseImagePath;
	
	private String smallBaseImagePath;

	//Models
	private List<BaseModel> baseModels;
	
	
	private String disclaimer;
	
	
	private String columnWidth = null;

	private Boolean hasSourceRow = false;
	

	private Boolean addTotalRow = false;
	
	//Resource Partner Profile variable
	ADAMDonorProfileVO donorProfileVO;
	
	//Storing the converted to DAC codes when the selected Data Source is not OECD (e.g. for Venn Priorities Matrix)
	private Map<String, String> convertedRecipients;
	
	

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public String getSmallWidth() {
		return smallWidth;
	}

	public void setSmallWidth(String smallWidth) {
		this.smallWidth = smallWidth;
	}

	public String getSmallHeight() {
		return smallHeight;
	}

	public void setSmallHeight(String smallHeight) {
		this.smallHeight = smallHeight;
	}

	public String getBigWidth() {
		return bigWidth;
	}

	public void setBigWidth(String bigWidth) {
		this.bigWidth = bigWidth;
	}

	public String getBigHeight() {
		return bigHeight;
	}

	public void setBigHeight(String bigHeight) {
		this.bigHeight = bigHeight;
	}

	public String getBigImagePath() {
		return bigImagePath;
	}

	public void setBigImagePath(String bigImagePath) {
		this.bigImagePath = bigImagePath;
	}

	public String getSmallImagePath() {
		return smallImagePath;
	}

	public void setSmallImagePath(String smallImagePath) {
		this.smallImagePath = smallImagePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBoxColor() {
		return boxColor;
	}

	public void setBoxColor(String boxColor) {
		this.boxColor = boxColor;
	}

	public Map<String, String> getGaulMap() {
		return gaulMap;
	}

	public void setGaulMap(Map<String, String> gaulMap) {
		this.gaulMap = gaulMap;
	}


	public Map<String, String> getDonorMap() {
		return donorMap;
	}

	public void setDonorMap(Map<String, String> donorMap) {
		this.donorMap = donorMap;
	}

	public Map<String, String> getDacMap() {
		return dacMap;
	}

	public void setDacMap(Map<String, String> dacMap) {
		this.dacMap = dacMap;
	}

	public List<String> getTableHeaders() {
		return tableHeaders;
	}

	public void setTableHeaders(List<String> tableHeaders) {
		this.tableHeaders = tableHeaders;
	}
	
	
	public Map<String, String> getTableHeadersMap() {
		return tableHeadersMap;
	}
	
	public void setTableHeadersMap(Map<String, String> tableHeadersMap) {
		this.tableHeadersMap = tableHeadersMap;
	}

	public List<List<String>> getTableContents() {
		return tableContents;
	}

	public void setTableContents(List<List<String>> tableContents) {
		this.tableContents = tableContents;
	}

	public VennBeanVO getVennBean() {
		return vennBean;
	}

	public void setVennBean(VennBeanVO vennBean) {
		this.vennBean = vennBean;
	}

	public LinkedHashMap<Long, LayerVO> getLayersMap() {
		return layersMap;
	}

	public void setLayersMap(LinkedHashMap<Long, LayerVO> layersMap) {
		this.layersMap = layersMap;
	}
		
	public void addLayer(LayerVO vo) {
		layersMap.put(vo.getOlID(), vo);
	}
	
	
	public void recreateLayers(LayerVO vo) {
		LinkedHashMap<Long, LayerVO> vos = new LinkedHashMap<Long, LayerVO>();
		for(Long key : layersMap.keySet()) {
			
		}
		layersMap.put(vo.getOlID(), vo);
	}

	public List<Integer> getQuantitiesIdx() {
		return quantitiesIdx;
	}

	public void setQuantitiesIdx(List<Integer> quantitiesIdx) {
		this.quantitiesIdx = quantitiesIdx;
	}

	

	public Integer getSummaryTableColumn() {
		return summaryTableColumn;
	}

	public void setSummaryTableColumn(Integer summaryTableColumn) {
		this.summaryTableColumn = summaryTableColumn;
	}

	public Integer getGroupingTableColumn() {
		return groupingTableColumn;
	}

	public void setGroupingTableColumn(Integer groupingTableColumn) {
		this.groupingTableColumn = groupingTableColumn;
	}
	
	public String getGroupingTableColumnId() {
		return groupingTableColumnId;
	}

	public void setGroupingTableColumnId(String groupingTableColumnId) {
		this.groupingTableColumnId = groupingTableColumnId;
	}

	public Boolean getGroupTable() {
		return groupTable;
	}

	public void setGroupTable(Boolean groupTable) {
		this.groupTable = groupTable;
	}

	public Map<String, String> getChannelMap() {
		return channelMap;
	}

	public void setChannelMap(Map<String, String> channelMap) {
		this.channelMap = channelMap;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getMediumImagePath() {
		return mediumImagePath;
	}

	public void setMediumImagePath(String mediumImagePath) {
		this.mediumImagePath = mediumImagePath;
	}

	public String getMediumWidth() {
		return mediumWidth;
	}

	public void setMediumWidth(String mediumWidth) {
		this.mediumWidth = mediumWidth;
	}

	public String getMediumHeight() {
		return mediumHeight;
	}

	public void setMediumHeight(String mediumHeight) {
		this.mediumHeight = mediumHeight;
	}

	public String getTableHTML() {
		return tableHTML;
	}

	public void setTableHTML(String tableHTML) {
		this.tableHTML = tableHTML;
	}

	public Long getRows() {
		return rows;
	}

	public void setRows(Long rows) {
		this.rows = rows;
	}

	public String getMeadurementUnit() {
		return measurementUnit;
	}

	public void setMeadurementUnit(String meadurementUnit) {
		this.measurementUnit = meadurementUnit;
	}

	public LinkedHashMap<String, Map<String, Double>> getChartValues() {
		return chartValues;
	}

	public void setChartValues(
			LinkedHashMap<String, Map<String, Double>> chartValues) {
		this.chartValues = chartValues;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

//	public ADAMQueryVO getSwitchResource() {
//		return switchResource;
//	}
//
//	public void setSwitchResource(ADAMQueryVO switchResource) {
//		this.switchResource = switchResource;
//	}

	public void setDefaultResultVO(ADAMResultVO rvo ,ADAMQueryVO qvo) {
		rvo.setTitle(qvo.getTitle());
		rvo.setBoxColor(qvo.getBoxColor());
		rvo.setSmallWidth(qvo.getSmallWidth() + "px");
		rvo.setSmallHeight(qvo.getSmallHeight() + "px");
		rvo.setMediumWidth(qvo.getSmallHeight() + "px");
		rvo.setMediumHeight(qvo.getSmallHeight() + "px");
		rvo.setBigWidth(qvo.getSmallWidth() + "px");
		rvo.setBigHeight(qvo.getSmallHeight() + "px");
		rvo.setOutputType(qvo.getOutputType());
		rvo.setResourceType(rvo.getResourceType());
	}

	public Map<String, String> getPriorities() {
		return priorities;
	}

	public List<ADAMQueryVO> getSwitchResources() {
		return switchResources;
	}

	public void setSwitchResources(List<ADAMQueryVO> switchResources) {
		this.switchResources = switchResources;
	}

	public void setPriorities(Map<String, String> priorities) {
		this.priorities = priorities;
	}

	public Map<String, Map<String, String>> getSelectedPriorities() {
		return selectedPriorities;
	}

	public void setSelectedPriorities(
			Map<String, Map<String, String>> selectedPriorities) {
		this.selectedPriorities = selectedPriorities;
	}

	public Map<String, String> getDac_sectors() {
		return dac_sectors;
	}

	public void setDac_sectors(Map<String, String> dacSectors) {
		dac_sectors = dacSectors;
	}

	public String getBigHtmlImageMap() {
		return bigHtmlImageMap;
	}

	public void setBigHtmlImageMap(String bigHtmlImageMap) {
		this.bigHtmlImageMap = bigHtmlImageMap;
	}

	public String getSmallHtmlImageMap() {
		return smallHtmlImageMap;
	}

	public void setSmallHtmlImageMap(String smallHtmlImageMap) {
		this.smallHtmlImageMap = smallHtmlImageMap;
	}

	public String getMediumHtmlImageMap() {
		return mediumHtmlImageMap;
	}

	public void setMediumHtmlImageMap(String mediumHtmlImageMap) {
		this.mediumHtmlImageMap = mediumHtmlImageMap;
	}

	public Map<String, Map<String, String>> getDonorsPriorities() {
		return donorsPriorities;
	}

	public void setDonorsPriorities(
			Map<String, Map<String, String>> donorsPriorities) {
		this.donorsPriorities = donorsPriorities;
	}

	public Map<String, Map<String, String>> getCountriesPriorities() {
		return countriesPriorities;
	}

	public void setCountriesPriorities(
			Map<String, Map<String, String>> countriesPriorities) {
		this.countriesPriorities = countriesPriorities;
	}

	public String getFilterFrameworkType() {
		return filterFrameworkType;
	}

	public void setFilterFrameworkType(String filterFrameworkType) {
		this.filterFrameworkType = filterFrameworkType;
	}

	public String getReturnFrameworkType() {
		return returnFrameworkType;
	}

	public void setReturnFrameworkType(String returnFrameworkType) {
		this.returnFrameworkType = returnFrameworkType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ADAMVennMatrixVO getVennMatrix() {
		return vennMatrix;
	}

	public void setVennMatrix(ADAMVennMatrixVO vennMatrix) {
		this.vennMatrix = vennMatrix;
	}
	
	public String getExportImagePath() {
		return exportImagePath;
	}

	public void setExportImagePath(String exportImagePath) {
		this.exportImagePath = exportImagePath;
	}

	public String getExportHtmlImageMap() {
		return exportHtmlImageMap;
	}

	public void setExportHtmlImageMap(String exportHtmlImageMap) {
		this.exportHtmlImageMap = exportHtmlImageMap;
	}
	

	public List<BaseModel> getBaseModels() {
		return baseModels;
	}

	public void setBaseModels(List<BaseModel> baseModels) {
		this.baseModels = baseModels;
	}

	public Boolean getBaseModelGrid() {
		return baseModelGrid;
	}

	public void setBaseModelGrid(Boolean baseModelGrid) {
		this.baseModelGrid = baseModelGrid;
	}

	public String getBigBaseImagePath() {
		return bigBaseImagePath;
	}

	public void setBigBaseImagePath(String bigBaseImagePath) {
		this.bigBaseImagePath = bigBaseImagePath;
	}

	public String getSmallBaseImagePath() {
		return smallBaseImagePath;
	}

	public void setSmallBaseImagePath(String smallBaseImagePath) {
		this.smallBaseImagePath = smallBaseImagePath;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public void setTableColumnWidth(String columnWidth) {
		this.columnWidth = columnWidth;
		
	}
	

	public String getTableColumnWidth() {
	   return columnWidth;		
	}

	public void setHighlightedTableColumns(List<String> highlightedColumns) {
		this.highlightedTableColumns = highlightedColumns;
		
	}
	
	public List<String> getHighlightedTableColumns() {
		   return highlightedTableColumns;		
		}

	public Boolean setHasSourceRow(Boolean hasSourceRow) {
		return this.hasSourceRow = hasSourceRow;
		
	}
	
	public Boolean getHasSourceRow() {
		   return hasSourceRow;		
		}

	
	public ADAMDonorProfileVO getDonorProfileVO() {
		return this.donorProfileVO;
		
	}
	public void setDonorProfileVO(ADAMDonorProfileVO donorProfileVO) {
		this.donorProfileVO = donorProfileVO;
		
	}

	public Boolean setAddTotalRow(Boolean addTotalRow) {
		return this.addTotalRow = addTotalRow;
		
	}
	
	public Boolean addTotalRow() {
		   return addTotalRow;		
		}

	public Map<String, String> getConvertedRecipients() {
		return convertedRecipients;
	}
	
	public void setConvertedRecipients(Map<String, String> convertedRecipients) {
		this.convertedRecipients = convertedRecipients;
	}

	public HashMap<String, String> getxAxisFullLabels() {
		return xAxisFullLabels;
	}

	public void setxAxisFullLabels(HashMap<String, String> xAxisFullLabels) {
		this.xAxisFullLabels = xAxisFullLabels;
	}
	
}