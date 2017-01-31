package org.fao.fenix.web.modules.birt.client.view.report.giewsMap;

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

public class GiewsMaps {
	
	Window window = new Window();
	HTML linkTable;
	Button refresh; 
	VerticalPanel vPanel;
	
	public GiewsMaps(){
		window.setSize(600,350);
		window.setHeading("GIEWS maps");

		vPanel = new VerticalPanel();
		vPanel.setSpacing(5);

		linkTable = new HTML();

		String content = "<br><b>NDVI resources: </b><br/>";
		content += "<a href='../GiewsMaps/ndviNormal.png' target='_blank'>NDVI Normal</a><br/>";
		content += "<a href='../GiewsMaps/ndviDa.png' target='_blank'>NDVI DA<br/></a>";
		content += "<a href='../GiewsMaps/ndviDekade.html' target='_blank'>The Latest decade<br/></a>";
		
		content += "<br><b>Rainfall resources:</b><br/>";
		content += "<a href='../GiewsMaps/rfeNormal.png' target='_blank'>Rainfall Normal</a><br/>";
		content += "<a href='../GiewsMaps/rfeDa.png' target='_blank'>Rainfall DA<br/></a>";
		content += "<a href='../GiewsMaps/rfeDekade.html' target='_blank'>The Latest decade<br/></a>";

		content += "<br><b>Early Warning resources:</b><br/>";
		content += "<a href='../earlyWarning/outputs/CountriesInCrisisBig.png' target='_blank'>Countries in crisis requiring external assistance</a><br/>";
		content += "<a href='../earlyWarning/outputs/UnfavourableProspectsBig.png' target='_blank'>Countries with Unfavourable Prospects for Current Crops</a><br/><br/>";
			
		linkTable.setHTML(content);
		vPanel.add(linkTable);
		
		refresh = new Button("Refresh Resources");
		
		vPanel.add(refresh);
		
		refresh.addSelectionListener(new SelectionListener<ButtonEvent>(){
			public void componentSelected(ButtonEvent ce){
				
				final LoadingWindow loadingWindow = new LoadingWindow("Creating resources ...", BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
				
				GIEWSServiceEntry.getInstance().createGiewsMapsImages(new AsyncCallback<Void>(){
				
				public void onSuccess(Void result) {
					Info.display("Create GIEWS maps", "Resources have been created.");
					loadingWindow.destroyLoadingBox();
				}
	
				public void onFailure(Throwable caught) {
					loadingWindow.destroyLoadingBox();
					Info.display("createGiewsMapsImages", caught.getLocalizedMessage());
				}
				
				});
			}
		});
		

		
		window.add(vPanel);
		window.show();
	}

}
