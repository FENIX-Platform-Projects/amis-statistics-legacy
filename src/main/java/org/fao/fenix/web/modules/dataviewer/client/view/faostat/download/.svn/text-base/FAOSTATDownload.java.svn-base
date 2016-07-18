package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.download.FAOSTATDownloadController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot.FAOSTATDownloadOutputType;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot.FAOSTATDownloadTablePanel;
import org.fao.fenix.web.modules.dataviewer.common.enums.DataViewerBoxContent;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATClientConstants;
import org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATDownloadConstants;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;

public class FAOSTATDownload {
	
	ContentPanel panel;
	
	VerticalPanel domainsPanel;
	
	ContentPanel selectionPanel;
	
	FAOSTATDownloadTitlePanel titlePanel;
	
	FAOSTATDownloadTablePanel tablePanel;
	
	FAOSTATDownloadDomainPanel domains;
	
	FAOSTATDownloadSelectorAreas areas;
	
	FAOSTATDownloadSelectorAreas reportedAreas;
	
	FAOSTATDownloadSelectorAreas partnerAreas;
	
	FAOSTATDownloadSelectorPanel years;
	
	FAOSTATDownloadSelectorPanel elements;
	
	FAOSTATDownloadSelectorPanel elementsList;
	
	FAOSTATDownloadSelectorItems items;
	
	FAOSTATDownloadOptions options;

	Button exportButton;
	
	Button tableButton;

	FAOSTATDownloadOutputType outputType;
	
	//Notes
	ContentPanel notesPanel;
	FAOSTATDownloadNotes notes;
	
	FAOSTATDownloadIntroduction introduction;
	
	Boolean isFBS = false;
	
	public FAOSTATDownload() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		panel.setStyleAttribute("min-height", String.valueOf(FAOSTATClientConstants.getMainContentHeight(true)) + "px");
		
		domainsPanel = new VerticalPanel();
//		domainsPanel.setBodyBorder(false);
//		domainsPanel.setHeaderVisible(false);
		
		selectionPanel = new ContentPanel();
		selectionPanel.setAutoWidth(true);
		selectionPanel.setAutoHeight(true);
		selectionPanel.setBodyBorder(false);
		selectionPanel.setHeaderVisible(false);
		
		tablePanel = new FAOSTATDownloadTablePanel();
		
		domains = new FAOSTATDownloadDomainPanel();

		options = new FAOSTATDownloadOptions();
		
		outputType = new FAOSTATDownloadOutputType();
		
		titlePanel = new FAOSTATDownloadTitlePanel();
		
		// Notes
		notesPanel = new ContentPanel();
		notesPanel.setAutoWidth(true);
		notesPanel.setAutoHeight(true);
		notesPanel.setBodyBorder(false);
		notesPanel.setHeaderVisible(false);
		
		introduction = new FAOSTATDownloadIntroduction();
	}

	public ContentPanel build() {
		
		//panel.add(new Html(""));
		panel.add(buildMainPanel());
		
		panel.add(DataViewerClientUtils.addVSpace(10));
		
//		buildTablePanel();
		
		panel.add(buildTablePanel());
	
		return panel;
	}
	
	private HorizontalPanel buildTablePanel() {
		HorizontalPanel h = new HorizontalPanel();
		h.add(DataViewerClientUtils.addHSpace(20));
		h.add(tablePanel.build());
		return h;
	}
	
	private HorizontalPanel buildMainPanel(){
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		p.add(buildDomains());
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(DataViewerClientUtils.addVSpace(5));
		verticalPanel.add(titlePanel.build());
		
		//verticalPanel.add(DataViewerClientUtils.addVSpace(10));
		verticalPanel.add(selectionPanel);
		
		selectionPanel.add(introduction.build(this));
		p.add(verticalPanel);

		return p;
	}
	
	
	public void buildSelectors(DWCodesModelData code){
		
		// hardcoded...checking if is FBS code
		if ( code.getCode().equalsIgnoreCase("FB")) {
			isFBS = true;
		}
		else {
			isFBS = false;
		}
		
		// this is a switch about the trade matrix that is handled 
		// in a different way and contains different information
		if ( code.getCode().equalsIgnoreCase("TM")) {
			System.out.println("BUILDING buildTradeMatrixLayout LAYOUT");
			buildDetailTradeMatrixLayout(code);
		}
		else if ( code.getCode().equalsIgnoreCase("FT")) {
			System.out.println("BUILDING buildTradeMatrixLayout LAYOUT");
			buildDetailTradeMatrixLayout(code);
		}
		else if ( isFBS ) {
			buildFBSLayout(code);
		}
		else {
			System.out.println("BUILDING STANDARD LAYOUT");
			buildStandardLayout(code);
		}
	}
	
	private void buildStandardLayout(DWCodesModelData code){
		initialize();
		
		
		
		selectionPanel.removeAll();
		HorizontalPanel selPanel = new HorizontalPanel();
		selPanel.add(DataViewerClientUtils.addVSpace(5));	
		selectionPanel.add(selPanel);
		
		VerticalPanel selectorsPanel = new VerticalPanel();
		VerticalPanel optionsPanel = new VerticalPanel();
		

		// adding the selectors ( AREA_ITEMS...)
		selectorsPanel.add(buildCountriesElements(code));
//		selectorsPanel.add(buildCountriesElements(code));
		selectorsPanel.add(buildItemsYears(code));
		selectorsPanel.add(addButtons());
		selPanel.add(selectorsPanel);
		
		// building the options	
		optionsPanel.add(DataViewerClientUtils.addHSpace(10));
		optionsPanel.add(outputType.build(this, isFBS, false));
		optionsPanel.add(DataViewerClientUtils.addHSpace(20));
		optionsPanel.add(options.build());
		optionsPanel.add(DataViewerClientUtils.addHSpace(5));
		selPanel.add(optionsPanel);
		
		selectionPanel.layout();
	}
	
	private void buildFBSLayout(DWCodesModelData code){
		initialize();
		
		
		
		selectionPanel.removeAll();
		HorizontalPanel selPanel = new HorizontalPanel();
		selPanel.add(DataViewerClientUtils.addVSpace(5));	
		selectionPanel.add(selPanel);
		
		VerticalPanel selectorsPanel = new VerticalPanel();
		VerticalPanel optionsPanel = new VerticalPanel();
		

		// adding the selectors ( AREA_ITEMS...)
		selectorsPanel.add(buildCountriesElements(code));
//		selectorsPanel.add(buildCountriesElements(code));
		selectorsPanel.add(buildItemsYears(code));
		selectorsPanel.add(addButtons());
		selPanel.add(selectorsPanel);
		
		// building the options	
		optionsPanel.add(DataViewerClientUtils.addHSpace(10));
		optionsPanel.add(outputType.build(this, isFBS, false));
		optionsPanel.add(DataViewerClientUtils.addHSpace(20));
		optionsPanel.add(options.build());
		optionsPanel.add(DataViewerClientUtils.addHSpace(5));
		selPanel.add(optionsPanel);
		
		selectionPanel.layout();
	}
	
	private void buildDetailTradeMatrixLayout(DWCodesModelData code){
		initialize();
		selectionPanel.removeAll();
		HorizontalPanel selPanel = new HorizontalPanel();
		selPanel.add(DataViewerClientUtils.addVSpace(5));	
		selectionPanel.add(selPanel);
		
		VerticalPanel selectorsPanel = new VerticalPanel();
		VerticalPanel optionsPanel = new VerticalPanel();
		

		// adding the selectors ( AREA_ITEMS...)
		selectorsPanel.add(buildReportedPartnerAreas(code));
		selectorsPanel.add(buildItemsYears(code));
//		selectorsPanel.add(buildElementsItems(code));
		selectorsPanel.add(addButtons());
		selPanel.add(selectorsPanel);
		
		// building the options	
		optionsPanel.add(DataViewerClientUtils.addHSpace(10));
		optionsPanel.add(outputType.build(this, false, true));
		optionsPanel.add(DataViewerClientUtils.addHSpace(20));
		optionsPanel.add(options.build());
		optionsPanel.add(DataViewerClientUtils.addHSpace(5));
		selPanel.add(optionsPanel);
		
		selectionPanel.layout();
	}
	
	private void initialize() {
		areas = new FAOSTATDownloadSelectorAreas();
		reportedAreas = new FAOSTATDownloadSelectorAreas();
		partnerAreas = new FAOSTATDownloadSelectorAreas();
		years = new FAOSTATDownloadSelectorPanel();
		items = new FAOSTATDownloadSelectorItems();
		elements = new FAOSTATDownloadSelectorPanel();
		elementsList = new FAOSTATDownloadSelectorPanel();
	}

	public void buildIntroductionPage(DWCodesModelData code){
		selectionPanel.removeAll();
		
		VerticalPanel p = new VerticalPanel();
		p.setBorders(false);
		
		p.add(getNotes(code));
		
        selectionPanel.add(p);
		
		selectionPanel.layout();
	
	}

	private HorizontalPanel buildReportedPartnerAreas(DWCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		p.add(buildReportedAreas(code));
		p.add(buildElementsList(code));		
//		p.add(buildPartnerAreas(code));		
//		p.add(buildYears(code, FAOSTATDownloadConstants.YEARS_MATRIX_WIDTH));
		return p;
	}
	
	private HorizontalPanel buildElementsItems(DWCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		p.add(buildItems(code));
		p.add(buildElements(code));		
		return p;
	}
	
	private HorizontalPanel buildCountriesElements(DWCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		
		p.add(buildAreas(code));
//		p.add(buildElements(code));		
		p.add(buildElementsList(code));		
		return p;
	}

	private HorizontalPanel buildItemsYears(DWCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		p.add(buildItems(code));		
		p.add(buildYears(code, FAOSTATDownloadConstants.YEARS_WIDTH));
		return p;
	}
	
//	private HorizontalPanel buildCountriesYears(DWCodesModelData code) {
//		HorizontalPanel p = new HorizontalPanel();
//		p.setSpacing(10);
//		
//		p.add(buildAreas(code));
//		p.add(buildYears(code));		
//		return p;
//	}

	private HorizontalPanel getNotes(DWCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
			
		p.add(buildNotes(code));
	
		return p;
	}

//	private HorizontalPanel buildElementsItems(DWCodesModelData code) {
//		HorizontalPanel p = new HorizontalPanel();
//		p.setSpacing(10);
//		p.add(buildItems(code));		
//		p.add(buildElements(code));
////		p.add(buildElementsList(code));
//		return p;
//	}
	
	private ContentPanel buildReportedAreas(DWCodesModelData code) {
		reportedAreas = new FAOSTATDownloadSelectorAreas();
		return reportedAreas.build(code, FAOSTATDownloadConstants.AREA_MATRIX_WIDTH, FAOSTATDownloadConstants.DOMAINS_HEIGHT, false, false);
	}
	
	private ContentPanel buildPartnerAreas(DWCodesModelData code) {
		partnerAreas = new FAOSTATDownloadSelectorAreas();
		return partnerAreas.build(code, FAOSTATDownloadConstants.AREA_MATRIX_WIDTH, FAOSTATDownloadConstants.DOMAINS_HEIGHT, false, false);
	}

	
	private ContentPanel buildAreas(DWCodesModelData code) {
		areas = new FAOSTATDownloadSelectorAreas();
		
		if ( !isFBS ) {
			return areas.build(code, FAOSTATDownloadConstants.AREA_WIDTH, FAOSTATDownloadConstants.DOMAINS_HEIGHT, true, true);
		}
		else {
			return areas.build(code, FAOSTATDownloadConstants.AREA_WIDTH, FAOSTATDownloadConstants.DOMAINS_HEIGHT, false, false);
		}
		
		
	}
	
	private ContentPanel buildNotes(DWCodesModelData code) {
		notes = new FAOSTATDownloadNotes();
		return notes.build(code);
	}
	
	
	private ContentPanel buildYears(DWCodesModelData code, String width) {
		years = new FAOSTATDownloadSelectorPanel();
		return years.build(code, FAOSTATLanguage.print().years(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_YEAR.toString(), false, true, width, FAOSTATDownloadConstants.DOMAINS_HEIGHT);
	}
	
	private ContentPanel buildItems(DWCodesModelData code) {
		items = new FAOSTATDownloadSelectorItems();
		
		if ( !isFBS ) {
			return items.build(code, FAOSTATDownloadConstants.ITEMS_WIDTH, FAOSTATDownloadConstants.DOMAINS_HEIGHT, true, false);
		}
		else {
			return items.build(code, FAOSTATDownloadConstants.ITEMS_WIDTH, FAOSTATDownloadConstants.DOMAINS_HEIGHT, false, true);
		}
	}
	
	
	private ContentPanel buildElements(DWCodesModelData code) {
		elements = new FAOSTATDownloadSelectorPanel();
		return elements.build(code, FAOSTATLanguage.print().elements(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT.toString(), false, true, FAOSTATDownloadConstants.ELEMENTS_WIDTH, FAOSTATDownloadConstants.DOMAINS_HEIGHT);
	}
	
	private ContentPanel buildElementsList(DWCodesModelData code) {
		elementsList = new FAOSTATDownloadSelectorPanel();
		
		if ( !isFBS ) {
			return elementsList.buildElementsList(code, FAOSTATLanguage.print().elements(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT_LIST.toString(), false, true, false, FAOSTATDownloadConstants.ELEMENTS_WIDTH, FAOSTATDownloadConstants.DOMAINS_HEIGHT);
		}
		else {
			return elementsList.buildElementsList(code, FAOSTATLanguage.print().elements(), DataViewerBoxContent.CODING_SYSTEM_FAOSTAT_ELEMENT_LIST.toString(), false, true, true, FAOSTATDownloadConstants.ELEMENTS_WIDTH, FAOSTATDownloadConstants.DOMAINS_HEIGHT);
		}
		
	}

	

	private HorizontalPanel addButtons() {
		HorizontalPanel p = new HorizontalPanel();
		p.add(DataViewerClientUtils.addHSpace(10));

		tableButton = new Button(FAOSTATLanguage.print().viewTables());
		tableButton.addSelectionListener(FAOSTATDownloadController.showTable(this));
		tableButton.setIconStyle("tableIcon");
		p.add(tableButton);
		
		p.add(DataViewerClientUtils.addHSpace(10));

		exportButton = new Button(FAOSTATLanguage.print().exportToCSV());
		exportButton.setIconStyle("sendToExcel");
		exportButton.addSelectionListener(FAOSTATDownloadController.exportCSV(this));
		p.add(exportButton);
		
		return p;
	}
	
	
	private ContentPanel buildDomains() {
		return domains.build(this);
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public ContentPanel getSelectionPanel() {
		return selectionPanel;
	}

	public VerticalPanel getDomainsPanel() {
		return domainsPanel;
	}

	public FAOSTATDownloadDomainPanel getDomains() {
		return domains;
	}

	public FAOSTATDownloadSelectorPanel getYears() {
		return years;
	}

	public FAOSTATDownloadSelectorPanel getElements() {
		return elements;
	}

	public FAOSTATDownloadSelectorAreas getAreas() {
		return areas;
	}

	public FAOSTATDownloadTablePanel getTablePanel() {
		return tablePanel;
	}

	public FAOSTATDownloadSelectorItems getItems() {
		return items;
	}

	public FAOSTATDownloadOptions getOptions() {
		return options;
	}

	public FAOSTATDownloadOutputType getOutputType() {
		return outputType;
	}

	public void setTablePanel(FAOSTATDownloadTablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public FAOSTATDownloadSelectorPanel getElementsList() {
		return elementsList;
	}

	public FAOSTATDownloadTitlePanel getTitlePanel() {
		return titlePanel;
	}

	public FAOSTATDownloadSelectorAreas getReportedAreas() {
		return reportedAreas;
	}

	public FAOSTATDownloadSelectorAreas getPartnerAreas() {
		return partnerAreas;
	}

	public Boolean getIsFBS() {
		return isFBS;
	}

}
