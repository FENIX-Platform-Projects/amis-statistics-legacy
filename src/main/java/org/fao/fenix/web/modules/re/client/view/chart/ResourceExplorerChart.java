package org.fao.fenix.web.modules.re.client.view.chart;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.designer.client.view.DesignerBox;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.client.control.ResourceTypeSelectorController;
import org.fao.fenix.web.modules.re.client.view.ResourceExplorer;
import org.fao.fenix.web.modules.re.client.view.SearchButtons;
import org.fao.fenix.web.modules.re.client.view.util.WhoCall;
import org.fao.fenix.web.modules.re.common.vo.ResourceType;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCENativeController;

import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;

public class ResourceExplorerChart extends ResourceExplorer{
	
	ReportViewer reportViewer;
		
	public ReportViewer getReportViewer() {
		return reportViewer;
	}
	
	public void  buildInterface(){
		
		setTitle(BabelFish.print().resourceExplorerWindowHeader() + " - " + BabelFish.print().chart());
		ResourceTypeSelectorController.resourceType=ResourceType.CHARTVIEW;
		
		setSearchParameters(new SearchParametersChart(this));
		setSearchButtons(new SearchButtons(this));
		//setCatalogueToolbar(new CatalogueToolbar(this));
		setCataloguePager(new CataloguePagerChart(this));
		setCatalogue(new CatalogueChart(this));
		
	    getWest().add(getSearchParameters().getMainCont());
		getWest().setBottomComponent(getSearchButtons().getCont());		
		addWestPartToWindow();

		//getCenter().setTopComponent(getCatalogueToolbar().getToolBar());
		getCenter().add(getCatalogue().getGrid());
		getCenter().setBottomComponent(getCataloguePager().getMainCont());
		addCenterPartToWindow();

		getCenter().setHeaderVisible(false);
		getWestData().setFloatable(true);
		
		show();
	}
	
	public ResourceExplorerChart(){
		setCaller(WhoCall.USER);
		buildInterface();
	}
	
	public ResourceExplorerChart(ReportViewer reportViewer){
		this.reportViewer = reportViewer;
		setCaller(WhoCall.REPORT);
		buildInterface();
	}

	public ResourceExplorerChart(DesignerBox designerBox){
		this.setDesignerBox(designerBox);
		setCaller(WhoCall.DESIGNER);
		buildInterface();
	}
	
	public ResourceExplorerChart(String tinyMCEPanelID){
		this.setTinyMCEPanelID(tinyMCEPanelID);
		setCaller(WhoCall.DESIGNER);
		buildInterface();
	}
	
	public ResourceExplorerChart(final String tinyMCEPanelID, final String tinyMCEPanelMarkID, final String originalHTML) {
		this.setTinyMCEPanelID(tinyMCEPanelID);
		this.setTinyMCEPanelMarkID(tinyMCEPanelMarkID);
		this.setOriginalHTML(originalHTML);
		setCaller(WhoCall.REPORT);
		buildInterface();
//		getWindow().addWindowListener(new WindowListener() {
//			public void windowHide(WindowEvent we) {
//				super.windowHide(we);
//				String originalContent = TinyMCENativeController.getContent(tinyMCEPanelID);
//				String tmp = originalContent.replace(tinyMCEPanelMarkID, originalHTML);
//				TinyMCENativeController.replaceAll(tinyMCEPanelID);
//				TinyMCENativeController.setContent(tinyMCEPanelID, tmp);
//			}
//		});
	}
	
}
