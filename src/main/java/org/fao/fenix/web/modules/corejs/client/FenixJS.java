package org.fao.fenix.web.modules.corejs.client;

import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.core.client.control.menu.FenixSelectionPool;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.excelimporter.client.view.ExcelImporterWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.action.CreateMap;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.client.view.search.ResourceExplorerSearch;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.ImageWindow;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportWindow;
import org.fao.fenix.web.modules.welcome.client.control.SplashWindowFactory;
import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;
import org.timepedia.exporter.client.Exportable;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;

@Export
@ExportPackage("helloworld")
public class FenixJS implements Exportable {
	
	@Export
	public void UpdateMyDomObject(String id) {
		DOM.getElementById(id).setInnerHTML("Hello World");
		FenixAlert.info("INFO", "Test successfull!!!");
	}
	
	@Export
	public void changeElementBackgroundColor(String id, String hex) {
		DOM.getElementById(id).getStyle().setBackgroundColor(hex);
	}
	
	@Export
	public void changeLeftImage(String imagePath) {
		DOM.getElementById("BANNER_LEFT").setPropertyString("src", imagePath);
	}
	
	@Export
	public void changeRigthImage(String imagePath) {
		DOM.getElementById("BANNER_RIGTH").setPropertyString("src", imagePath);
	}
	
	@Export
	public void execute(String action) {
		if (action.equalsIgnoreCase("openRE")) {
			new ResourceExplorerSearch();
		} else if (action.equalsIgnoreCase("createMap")) {
			CreateMap.run();
		} else if (action.equalsIgnoreCase("createChart")) {
			new ChartDesignerWindow().build();
		} else if (action.equalsIgnoreCase("createOLAP")) {
			new MTWindow().build();
		} else if (action.equalsIgnoreCase("createReport")) {
			new TinyMCEReportWindow().build(null);
		} else if (action.equalsIgnoreCase("openDataset")) {
			new ResourceExplorerDataset();
		} else if (action.equalsIgnoreCase("signin")) {
			FenixSelectionPool.loginAction();
		} else if (action.equalsIgnoreCase("showInfo")) {
			SplashWindowFactory.getInstance(false);
		} else if (action.equalsIgnoreCase("tools")) {
			SplashWindowFactory.getInstance(true);
		} else if (action.equalsIgnoreCase("uploadDataset")) {
			try {
				isLogged();
				new ExcelImporterWindow().build();
			} catch(FenixGWTException e) {
				FenixAlert.info(BabelFish.print().info(), e.getMessage());
			}
		} else {
			FenixAlert.info(BabelFish.print().info(), "This function is not yet available.");
		}
	}
	
	@Export
	public void preview(final String imageName) {
		final ImageWindow imageWindow = new ImageWindow(null, imageName);
		imageWindow.buildPreview();
		imageWindow.getWindow().setPosition(imageWindow.getWindow().getAbsoluteLeft(), imageWindow.getWindow().getAbsoluteTop() - 20);
		Timer t = new Timer() {
			public void run() {
				imageWindow.getWindow().hide();
			}
		};
		t.schedule(1000);
	}
	
	public void isLogged() throws FenixGWTException {
		if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
			
		} else {
			throw new FenixGWTException(BabelFish.print().pleaseLogin());
		}
	}

}