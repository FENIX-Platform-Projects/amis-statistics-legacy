package org.fao.fenix.web.modules.birt.client.view.report.crisis;


import org.fao.fenix.web.modules.giews.common.services.GIEWSServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CrisisView {

	Window window = new Window();
	HTML linkTable;
	HTML outputLink;
	Button exportButton; 
	Button refreshButton; 
	HTML exportLink;
	VerticalPanel vPanel;

	public CrisisView(){
		window.setSize(600, 400);
		window.setHeading("GIEWS Early Warning Outputs");

		vPanel = new VerticalPanel();
		vPanel.add(new HTML("<iframe src='../earlyWarning/CountryInCrisisLatestDate.html' style='height:13px;width:100%' scrolling=no marginheight='0px' frameborder=no></iframe><br><br> Click links to preview the tables and map images"));
		vPanel.setSpacing(5);

		linkTable = new HTML();

		String content = "<br><b>Countries in crisis requiring external assistance: </b><br/>";
		content += "<a href='../earlyWarning/outputs/CountriesInCrisisTable.html' target='_blank'>Table</a><br/>";
		content += "<a href='../earlyWarning/outputs/CountriesInCrisisBig.png' target='_blank'>Map<br/><br/></a>";
		content += "<b>Countries with Unfavourable Prospects for Current Crops</b>: <br/>";
		content += "<a href='../earlyWarning/outputs/CountriesUnfavourableProspects.html' target='_blank'>Table<br/></a>";
		content += "<a href='../earlyWarning/outputs/UnfavourableProspectsBig.png' target='_blank'>Map<br/><br/><br/></a>";

		linkTable.setHTML(content);
		vPanel.add(linkTable);

		vPanel.add(new HTML("If recent dataset updates have been made, please press the 'Refresh' button to update the above Tables and Maps."));
		refreshButton = new Button("Refresh");
		
		vPanel.add(refreshButton);
		
		refreshButton.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				final LoadingWindow loadingWindow = new LoadingWindow("Refreshing maps and tables ...", BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
				
				GIEWSServiceEntry.getInstance().createEarlyWarningResources(new AsyncCallback<String>(){

					public void onSuccess(String result) {
						Info.display("countriesInCrisis: Create maps and tables", "Resources have been refreshed.");
						loadingWindow.destroyLoadingBox();
					}

					public void onFailure(Throwable caught) {
						Info.display("countriesInCrisis", caught.getLocalizedMessage());
						loadingWindow.destroyLoadingBox();
					}

				});		
			}
		});
		
		outputLink = new HTML();
		String output = "<br>Download the zip file <a href='../earlyWarning/output.zip' target='_blank'><b>here</b></a>";
		outputLink.setHTML(output);

		vPanel.add(new HTML("<br/>Click on the 'Create Zip File' button to create a zip file containing 4 map images and 2 HTML tables."));
		exportButton = new Button("Create Zip file");
		
		exportButton.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				final LoadingWindow loadingWindow = new LoadingWindow("Creating zip file ...", BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
				
				GIEWSServiceEntry.getInstance().createEarlyWarningOutputZipFile(new AsyncCallback(){

					public void onSuccess(Object result) {
						loadingWindow.destroyLoadingBox();
						vPanel.add(outputLink);
					}

					public void onFailure(Throwable caught) {
						Info.display("Problem exporting zip file", caught.getLocalizedMessage());
						loadingWindow.destroyLoadingBox();
					}

				});
			}
		});
		
		vPanel.add(exportButton);
		
		window.add(vPanel);
		window.show();
	}



}
