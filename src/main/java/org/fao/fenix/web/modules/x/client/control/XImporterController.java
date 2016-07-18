package org.fao.fenix.web.modules.x.client.control;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.x.client.view.XImporterWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class XImporterController {

	private static LoadingWindow loadingWindow = null;

	public static SelectionListener<ButtonEvent> getUploadListener(final XImporterWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				FileUploadField rss = window.getFile();
				FormPanel form = window.getFormPanel();
				try {
					if (form.isValid()) {
						int index = 0;
						for (int i = rss.getValue().length() - 1; i >= 0; i--) {
							if ((rss.getValue().charAt(i) == '/') || (rss.getValue().charAt(i) == '\\')) {
								index = i;
								break;
							}
						}
						String fileName = rss.getValue();
						if (index != 0)
							fileName = rss.getValue().substring(index + 1, rss.getValue().length());
						if (!fileName.contains(".xml"))
							throw new Throwable(BabelFish.print().badDatasetExtension());
						if (!fileName.contains(".xml") && (fileName.equals(""))) {
							if (fileName.equals(""))
								throw new Throwable(BabelFish.print().errorSelectDataset());
						} else {
							form.submit();
						}
					} else
						FenixAlert.error(BabelFish.print().error(), BabelFish.print().requiredFieldsMissing());
				} catch (Throwable e) {
					window.fillRemarksArea("<font color='red'>" + e.getMessage() + "</font>");
				}
			}
		};
	}

	public static Listener<FormEvent> getAfterSubmitFormListener(final XImporterWindow window) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				if (loadingWindow != null)
					loadingWindow.destroyLoadingBox();
				String results = be.getResultHtml();
				if (results == null || results.equals("") || results.equals("<pre></pre>")) {
					window.clearRemarksArea();
					window.fillRemarksArea("<div style='color:green;'>Upload complete!</div>");
//					window.setViewDatasetLinks();
				} else {
					window.fillRemarksArea("<div style='color:red; font-weight:bold;'>" + BabelFish.print().uploadUnsuccessful() + "<BR>" + results + "</div>");
				}

			}
		};
	}

	public static Listener<FormEvent> getBeforeSubmitListener(final XImporterWindow window) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				// before submit event
				loadingWindow = new LoadingWindow("X Importer", "GIEWS Workstation is importing your file. Please wait.", "Loading...");
				loadingWindow.showLoadingBox();
			}
		};
	}

}
