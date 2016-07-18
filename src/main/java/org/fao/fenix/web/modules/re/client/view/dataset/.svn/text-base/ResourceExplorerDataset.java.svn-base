package org.fao.fenix.web.modules.re.client.view.dataset;

import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerDatasourceFieldSet;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.client.view.OfcChartWindow;
import org.fao.fenix.web.modules.ofcchart.client.view.wizard.OfcChartWizard;
import org.fao.fenix.web.modules.olap.client.view.MTDatasourceFieldSet;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.qb.client.view.QueryBuilder;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;

public class ResourceExplorerDataset extends ResourceExplorer{
	
	
	private ChartWizard chartWizard;
	
	private OlapWindow olapWindow;
	
	private OfcChartWindow ofcChartWindow;
	
	private OfcChartWizard ofcChartWizard;
	
	private ReportViewer reportViewer;
	
	private QueryBuilder queryBuilder;
	
	private ResourceExplorer chartRE;
	
	private ChartDesignerWindow chartDesignerWindow;
	
	private ChartDesignerDatasourceFieldSet chartDesignerDatasourceFieldSet;
	
	private MTDatasourceFieldSet mtDatasourceFieldSet;
	
	private MTWindow mtWindow;
	
	public ChartWizard getChartWizard() {
		return chartWizard;
	}

	private void buildInterface(){
		setTitle(BabelFish.print().resourceExplorerWindowHeader());
		ResourceTypeSelectorController.resourceType=ResourceType.DATASET;
		//setSize(850, 500);
		setSearchParameters(new SearchParametersDataset(this));
		setSearchButtons(new SearchButtons(this));
		//setCatalogueToolbar(new CatalogueToolbar(this));
		setCataloguePager(new CataloguePagerDataset(this));
		setCatalogue(new CatalogueDataset(this));
		
		getWest().add(getSearchParameters().getMainCont());
		getWest().setBottomComponent(getSearchButtons().getCont());		
		addWestPartToWindow();

        //getCenter().setTopComponent(getCatalogueToolbar().getToolBar());
		getCenter().add(getCatalogue().getGrid());
		
		getCenter().setBottomComponent(getCataloguePager().getMainCont());
		//getCenter().setBottomComponent(getPagingToolBar());
		
		addCenterPartToWindow();

		getCenter().setHeaderVisible(false);
		getWestData().setFloatable(true);
		
		show();
	}

	public ResourceExplorerDataset(){
		setCaller(WhoCall.USER);
		buildInterface();
	}
	
	public ResourceExplorerDataset(ChartWizard chartWizard){
		this.chartWizard=chartWizard;
		setCaller(WhoCall.CHART);
		//setSize(775, 450);
		buildInterface();
	}
	
	public ResourceExplorerDataset(OlapWindow olapWindow){
		this.olapWindow = olapWindow;
		setCaller(WhoCall.OLAP);
		//setSize(775, 450);
		buildInterface();
	}
	
	public ResourceExplorerDataset(ChartDesignerWindow w) {
		this.chartDesignerWindow = w;
		setCaller(WhoCall.CHARTDESIGNER);
		buildInterface();
	}
	
	public ResourceExplorerDataset(MTWindow w) {
		this.mtWindow = w;
		setCaller(WhoCall.MT);
		buildInterface();
	}
	
	public ResourceExplorerDataset(MTWindow w, MTDatasourceFieldSet fs) {
		this.mtWindow = w;
		this.mtDatasourceFieldSet = fs;
		setCaller(WhoCall.MT);
		buildInterface();
	}
	
	public ResourceExplorerDataset(ChartDesignerWindow w, ChartDesignerDatasourceFieldSet chartDesignerDatasourceFieldSet) {
		this.chartDesignerWindow = w;
		this.chartDesignerDatasourceFieldSet = chartDesignerDatasourceFieldSet;
		setCaller(WhoCall.CHARTDESIGNER);
		buildInterface();
	}

	public ResourceExplorerDataset(OfcChartWindow ofcChartWindow){
		this.ofcChartWindow = ofcChartWindow;
		setCaller(WhoCall.OFCCHART);
		//setSize(775, 450);
		buildInterface();
	}
	
	public ResourceExplorerDataset(OfcChartWizard ofcChartWizard){
		this.ofcChartWizard = ofcChartWizard;
		setCaller(WhoCall.OFCCHARTWIZARD);
		//setSize(775, 450);
		buildInterface();
	}
	
	public ResourceExplorerDataset(ReportViewer reportViewer){
		this.reportViewer = reportViewer;
		setCaller(WhoCall.TABLE);
		//setSize(775, 450);
		buildInterface();
	}

	public ResourceExplorerDataset(QueryBuilder queryBuilder){
		this.queryBuilder = queryBuilder;
		setCaller(WhoCall.QUERYBUILDER);
		buildInterface();
	}
	
	public ResourceExplorerDataset(ResourceExplorer chartRE){
		this.chartRE = chartRE;
		setCaller(WhoCall.CHART_RE);
		//setSize(775, 450);
		buildInterface();
	}
	
	public OlapWindow getOlapWindow() {
		return olapWindow;
	}

	public OfcChartWindow getOfcChartWindow() {
		return ofcChartWindow;
	}
	
	public OfcChartWizard getOfcChartWizard() {
		return ofcChartWizard;
	}

	public ReportViewer getReportViewer() {
		return reportViewer;
	}
	
	public QueryBuilder getQueryBuilder() {
		return queryBuilder;
	}

	public ResourceExplorer getChartRE() {
		return chartRE;
	}

	public ChartDesignerWindow getChartDesignerWindow() {
		return chartDesignerWindow;
	}

	public ChartDesignerDatasourceFieldSet getChartDesignerDatasourceFieldSet() {
		return chartDesignerDatasourceFieldSet;
	}

	public MTWindow getMtWindow() {
		return mtWindow;
	}

	public MTDatasourceFieldSet getMtDatasourceFieldSet() {
		return mtDatasourceFieldSet;
	}
	
}