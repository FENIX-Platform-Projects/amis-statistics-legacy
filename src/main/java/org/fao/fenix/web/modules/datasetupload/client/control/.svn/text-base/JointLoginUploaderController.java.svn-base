package org.fao.fenix.web.modules.datasetupload.client.control;

import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.LoginResponseVo;
import org.fao.fenix.web.modules.datasetupload.client.view.JointLoginUploaderPanel;
import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISTabPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.OlapToolController;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class JointLoginUploaderController {

	static LoadingWindow loadingWindow = null;

	public static Listener<FormEvent> getBeforeSubmitListener(final JointLoginUploaderPanel panel) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				loadingWindow = new LoadingWindow(BabelFish.print().uploadingDataset(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				panel.clearRemarksArea();
				loadingWindow.showLoadingBox();
			}
		};
	}

	public static SelectionListener<ButtonEvent> getUploadListener(final JointLoginUploaderPanel panel) {

		return new SelectionListener<ButtonEvent>() {

			public void componentSelected(ButtonEvent ce) {

				UserServiceEntry.getInstance().login(panel.getUsernameField().getValue(), panel.getPasswordField().getValue(), new AsyncCallback<LoginResponseVo>() {

					public void onSuccess(LoginResponseVo r) {
						panel.fillRemarksArea("<font color='green'>" + panel.getUsernameField().getValue() + " successfully logged in!</font>");
						uploadDataset(panel);
					}

					public void onFailure(Throwable e) {
						loadingWindow.destroyLoadingBox();
						FenixAlert.error(BabelFish.print().info(), e.getMessage());
						loadingWindow.destroyLoadingBox();
					}

				});
			}
		};
	}

	private static void uploadDataset(final JointLoginUploaderPanel panel) {

		// retrieve form parameters
		FileUploadField dataset = panel.getFile();
		FileUploadField metadata = panel.getMetadata();
		FormPanel form = panel.getPanel();

		try {

			if (form.isValid()) {

				// get dataset name index
				int index = 0;

				for (int i = dataset.getValue().length() - 1; i >= 0; i--)
					if ((dataset.getValue().charAt(i) == '/') || (dataset.getValue().charAt(i) == '\\')) {
						index = i;
						break;
					}

				// get metadata name index
				int index2 = 0;
				for (int i = metadata.getValue().length() - 1; i >= 0; i--)
					if ((metadata.getValue().charAt(i) == '/') || (metadata.getValue().charAt(i) == '\\')) {
						index2 = i;
						break;
					}

				// get filename
				String fileName = dataset.getValue();
				if (index != 0)
					fileName = dataset.getValue().substring(index + 1, dataset.getValue().length());

				// get metadata name
				String metadatafileName = metadata.getValue();
				if (index2 != 0)
					metadatafileName = metadata.getValue().substring(index2 + 1, metadata.getValue().length());

				// extension controls
				if (!fileName.contains(".zip") && !fileName.contains(".csv"))
					throw new Throwable(BabelFish.print().badDatasetExtension());
				if (!fileName.contains(".zip") && !metadatafileName.contains("_metadata.xml"))
					throw new Throwable(BabelFish.print().badMetadataExtension());

				// submit form
				if (!fileName.contains(".zip") && (fileName.equals("") || metadatafileName.equals(""))) {
					if (fileName.equals(""))
						throw new Throwable(BabelFish.print().errorSelectDataset());
					else if (metadatafileName.equals(""))
						throw new Throwable(BabelFish.print().errorSelectMetadata());
				} else {
					form.submit();
				}

			} else
				FenixAlert.error(BabelFish.print().error(), BabelFish.print().requiredFieldsMissing());

		} catch (Throwable e) {
			panel.fillRemarksArea("<font color='red'>" + e.getMessage() + "</font>");
		}

	}

	public static Listener<FormEvent> getAfterSubmitFormListener(final JointLoginUploaderPanel panel, final FSATMISTabPanel tabPanel, final ListBox dataSource) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				// after submit event
				if (loadingWindow != null)
					loadingWindow.destroyLoadingBox();

				String results = be.getResultHtml();
				if (results == null || results.equals("") || results.equals("<pre></pre>")) {
//					panel.clearRemarksArea();
					panel.fillRemarksArea("<font color='green'>Dataset successfully uploaded!</font>");
					OlapToolController.fillDataSourceList(dataSource);
					TabItem olapTabItem = tabPanel.getOlapTabItem();
					tabPanel.getTabPanel().setSelection(olapTabItem);
					olapTabItem.getLayout().layout();
				} else {
					panel.fillRemarksArea("<div style='color:red; font-weight:bold;'>" + BabelFish.print().uploadUnsuccessful() + "<BR>" + results + "</div>");
				}

			}
		};
	}

}
