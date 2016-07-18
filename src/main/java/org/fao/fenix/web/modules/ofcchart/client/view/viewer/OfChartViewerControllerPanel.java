package org.fao.fenix.web.modules.ofcchart.client.view.viewer;

import org.fao.fenix.web.modules.ofcchart.client.view.wizard.OfcChartWizard;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;



public class OfChartViewerControllerPanel {
	

	private ContentPanel datasetPanel;
	
	private ContentPanel xPanel;
	
	private ContentPanel yPanel;
	
	private ContentPanel otherDimPanel;
	
	private ContentPanel optionsPanel;
	
	
	public OfChartViewerControllerPanel() {
		datasetPanel = new ContentPanel();		
		datasetPanel.setHeading("Datasets");
		datasetPanel.setLayout(new FitLayout());
		xPanel = new ContentPanel();		
		xPanel.setHeading("xPanel");
		xPanel.setLayout(new FitLayout());
		yPanel = new ContentPanel();	
		yPanel.setHeading("yPanel");
		yPanel.setLayout(new FitLayout());
		otherDimPanel = new ContentPanel();	
		otherDimPanel.setHeading("otherDimPanel");
		otherDimPanel.setLayout(new FitLayout());
		optionsPanel = new ContentPanel();	
		optionsPanel.setHeading("optionsPanel");
		optionsPanel.setLayout(new FitLayout());
	}
	
	/** TODO CHECK IF IS A PIE OR SCATTER **/
	public void build(OfcChartViewer viewer, OfcChartWizard wizard) {
		viewer.getWest().add(buildDatasetPanel(wizard));
		viewer.getWest().add(buildxPanel(wizard));
		viewer.getWest().add(buildyPanel(wizard));
		viewer.getWest().add(buildotherDimPanel());
		viewer.getWest().add(buildoptionsPanel());
	}
	
	public ContentPanel buildDatasetPanel(OfcChartWizard wizard) {
//		datasetPanel.add(wizard.getSelectData().get)
		return datasetPanel;
	}

	public ContentPanel buildxPanel(OfcChartWizard wizard){
		ContentPanel mainPanel = new ContentPanel();
		mainPanel.add(wizard.getSelectXAxis().getxPanel());
		
		xPanel.add(mainPanel);
		return xPanel;
	}
	
	public ContentPanel buildyPanel(OfcChartWizard wizard) {

		return yPanel;
	}

	public ContentPanel buildotherDimPanel(){
		return otherDimPanel;
	}
	
	public ContentPanel buildoptionsPanel(){
		return optionsPanel;
	}
	

	
	
	
	
}