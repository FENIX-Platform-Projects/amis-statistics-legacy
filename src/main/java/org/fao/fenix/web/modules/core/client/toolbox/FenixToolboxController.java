package org.fao.fenix.web.modules.core.client.toolbox;

import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.excelimporter.client.view.ExcelImporterWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ImageImporterWindow;
import org.fao.fenix.web.modules.map.client.control.action.CreateMap;
import org.fao.fenix.web.modules.olap.client.view.MTWindow;
import org.fao.fenix.web.modules.pm.client.control.PMModel;
import org.fao.fenix.web.modules.pm.client.view.ProjectManager;
import org.fao.fenix.web.modules.re.client.view.dataset.ResourceExplorerDataset;
import org.fao.fenix.web.modules.re.client.view.search.ResourceExplorerSearch;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;
import org.fao.fenix.web.modules.tinymcereport.client.view.TinyMCEReportWindow;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class FenixToolboxController {

	public static ClickListener open(final String resources) {
		return new ClickListener() {
			public void onClick(Widget widget) {
				if (resources.equals("project")) {
					if (!PMModel.isProjectManagerOpen() || PMModel.getProjectManager() == null) {
						new ProjectManager().build();
					} else {
						PMModel.getProjectManager().show();
					}
				} else if (resources.equals("map")) {
					CreateMap.run();
				} else if (resources.equals("table")) {
					new ResourceExplorerDataset();
				} else if (resources.equals("chart")) {
//					new ChartWizard();
					new ChartDesignerWindow().build();
				} else if (resources.equals("text")) {
					if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
						new TextEditor().build();
					} else {
						FenixAlert.info("Message", "You need to sign in before creating a new text");
					}
				} else if (resources.equals("image")) {
					if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
						new ImageImporterWindow(false).build();
					} else {
						FenixAlert.info("Message", "You need to sign in before importing a new image");
					}
				} else if (resources.equals("olap")) {
					new MTWindow().build();
//					if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
//						new OlapWindow().build(true, true);
//					} else {
//						new OlapWindow().build(true, false);
//					}
				} else if (resources.equals("report")) {
//					if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
//						new DesignerWindow().build();
						new TinyMCEReportWindow().build(null);
//					} else {
//						FenixAlert.info("Message", "You need to sign in before creating a new report");
//					}
				} else if (resources.equals("search")) {
					new ResourceExplorerSearch();
				} else if (resources.equals("upload")) {
					if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
						new ExcelImporterWindow().build(); //new DatasetUploaderWindow().build();
					} else {
						FenixAlert.info("Message", "You need to sign in before uploading a new dataset");
					}
				}
			}
		};
	}

	public static WindowListener onCloseManager(final FenixToolbox fenixToolbox) {
		return new WindowListener() {
			public void handleEvent(WindowEvent event) {
				if (event.getType() == Events.Hide) {
					FenixToolbox.toolboxIsopen = false;
					fenixToolbox.getWindowToolbox().close();
				}
			}
		};
	}

}
