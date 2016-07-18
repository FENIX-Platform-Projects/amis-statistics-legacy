package org.fao.fenix.web.modules.datasetupload.client.control;

import org.fao.fenix.web.modules.datasetupload.client.view.DatasetUploaderView;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;

public class DatasetUploaderController_OLD {

	DatasetUploaderView datasetUploaderView;

	public DatasetUploaderController_OLD() {
		datasetUploaderView = new DatasetUploaderView();
		datasetUploaderView.build();
		setEvents();
	}

	private void setEvents() {

		datasetUploaderView.getSubmit().addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				int index = 0;
				for (int i = datasetUploaderView.getUpload().getFilename().length() - 1; i >= 0; i--) {
					if ((datasetUploaderView.getUpload().getFilename().charAt(i) == '/') || (datasetUploaderView.getUpload().getFilename().charAt(i) == '\\')) {
						index = i;
						break;
					}
				}

				int index2 = 0;
				for (int i = datasetUploaderView.getUploadMetadata().getFilename().length() - 1; i >= 0; i--) {
					if ((datasetUploaderView.getUploadMetadata().getFilename().charAt(i) == '/') || (datasetUploaderView.getUploadMetadata().getFilename().charAt(i) == '\\')) {
						index2 = i;
						break;
					}
				}
				String fileName = datasetUploaderView.getUpload().getFilename();
				if (index != 0)
					fileName = datasetUploaderView.getUpload().getFilename().substring(index + 1, datasetUploaderView.getUpload().getFilename().length());
				String metadatafileName = datasetUploaderView.getUploadMetadata().getFilename();
				if (index2 != 0)
					metadatafileName = datasetUploaderView.getUploadMetadata().getFilename().substring(index2 + 1, datasetUploaderView.getUploadMetadata().getFilename().length());

				if (fileName.equals("") && metadatafileName.equals("")) {
					if (fileName.equals("")) {
						FenixAlert.error(BabelFish.print().error(), BabelFish.print().errorSelectDataset());

					}
					if (metadatafileName.equals("")) {
						FenixAlert.error(BabelFish.print().error(), BabelFish.print().errorSelectMetadata());

					}
				} else if (!fileName.toLowerCase().endsWith(".csv")) {
					String error = "<b>ERROR:<br/>The dataset file </b>[" + fileName + "] <b>should be in CSV format</b>";
					datasetUploaderView.getMessage().setHTML("<br/> &nbsp;<div style='color:red'> " + error + "</div>");

				} else if (!metadatafileName.toLowerCase().endsWith("_metadata.xml")) {
					String error = "<b>ERROR:<br/>The metadata file </b>[" + metadatafileName + "] <b>should end with '_metadata.xml'</b>";
					datasetUploaderView.getMessage().setHTML("<br/> &nbsp;<div style='color:red'> " + error + "</div>");

				} else {
					datasetUploaderView.getForm().submit();
				}

			}
		});

		datasetUploaderView.getForm().addFormHandler(new FormHandler() {

			/**
			 * Fired just before submit form, used to perform checks.
			 */
			public void onSubmit(FormSubmitEvent event) {

				// FenixAlert.info("You are going to upload {0} file.",
				// upload.getFilename());
			}

			/**
			 * Fired on submission complete, could be used as a test.
			 */
			public void onSubmitComplete(FormSubmitCompleteEvent event) {

				//com.google.gwt.user.client.Window.alert("event.getResults() ="
				// + event.getResults() + "end");

				int index = 0;
				for (int i = datasetUploaderView.getUpload().getFilename().length() - 1; i >= 0; i--) {
					if ((datasetUploaderView.getUpload().getFilename().charAt(i) == '/') || (datasetUploaderView.getUpload().getFilename().charAt(i) == '\\')) {
						index = i;
						break;
					}
				}

				int index2 = 0;
				for (int i = datasetUploaderView.getUploadMetadata().getFilename().length() - 1; i >= 0; i--) {
					if ((datasetUploaderView.getUploadMetadata().getFilename().charAt(i) == '/') || (datasetUploaderView.getUploadMetadata().getFilename().charAt(i) == '\\')) {
						index2 = i;
						break;
					}
				}
				String fileName = datasetUploaderView.getUpload().getFilename();
				if (index != 0)
					fileName = datasetUploaderView.getUpload().getFilename().substring(index + 1, datasetUploaderView.getUpload().getFilename().length());
				String metadatafileName = datasetUploaderView.getUploadMetadata().getFilename();
				if (index2 != 0)
					metadatafileName = datasetUploaderView.getUploadMetadata().getFilename().substring(index2 + 1, datasetUploaderView.getUploadMetadata().getFilename().length());

				String results = event.getResults();

				if (results == null || results.equals("") || results.equals("<pre></pre>")) {
					datasetUploaderView.getMessage().setHTML("<br/><div style='font-weight:bold; color:green;'>The dataset was successfully uploaded.</div>");
				} else {
					datasetUploaderView.getMessage().setHTML("<br/><div style='font-weight:bold; color:red;'> The dataset was NOT successfully uploaded.</div>");
				}

			}
		});

	}

}
