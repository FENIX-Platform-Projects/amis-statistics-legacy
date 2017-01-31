package org.fao.fenix.web.modules.codinghierarchyupload.client.control;


import org.fao.fenix.web.modules.codinghierarchyupload.client.view.CodingHierarchyUploaderWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;



public class CodingHierarchyUploaderController {

	
	static LoadingWindow loadingWindow = null;
	

	
	public static SelectionListener<ButtonEvent> getUploadListener(final CodingHierarchyUploaderWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				// retrieve form parameters
				FileUploadField hierarchy = window.getFile();
				FileUploadField metadata = window.getMetadata();					
				FormPanel form = window.getFormPanel();
			
				try {
					
					if(form.isValid()){
				
						// get dataset name index
						int index = 0;

						for (int i = hierarchy.getValue().length() - 1; i >= 0; i--) 
							if ((hierarchy.getValue().charAt(i) == '/') || (hierarchy.getValue().charAt(i) == '\\')) {
								index = i;
								break;
							}
						
						// get metadata name index
						int index2 = 0;
							System.out.println("METADATA FILENAME LENGTH " + metadata.getValue().length());
							for (int i = metadata.getValue().length() - 1; i >= 0; i--) 
								if ((metadata.getValue().charAt(i) == '/') || (metadata.getValue().charAt(i) == '\\')) {
									index2 = i;
									break;
								}
						// get filename
						String fileName = hierarchy.getValue();
						if (index != 0)
							fileName = hierarchy.getValue().substring(index + 1, hierarchy.getValue().length());
						// get metadata name
					    String metadatafileName = metadata.getValue();
						if (index2 != 0)
							metadatafileName = metadata.getValue().substring(index2 + 1, metadata.getValue().length());

						// extension controls
						if (!fileName.contains(".csv"))
							throw new Throwable(BabelFish.print().badDatasetExtension());
						if (!metadatafileName.contains(".xml"))
							throw new Throwable(BabelFish.print().badMetadataExtension());
						// submit form
						
						if ((fileName.equals("") || metadatafileName.equals(""))){
							if (fileName.equals("")) 
								throw new Throwable(BabelFish.print().errorSelectDataset());
							else if (metadatafileName.equals("")) 
								throw new Throwable(BabelFish.print().errorSelectMetadata());
						} else {
							form.submit();
						}
					} else FenixAlert.error(BabelFish.print().error(), BabelFish.print().requiredFieldsMissing());	
				} catch (Throwable e) {
					window.fillRemarksArea("<font color='red'>" + e.getMessage() + "</font>");
				}
			}
		};
	}
	

	public static Listener<FormEvent> getAfterSubmitFormListener(final CodingHierarchyUploaderWindow window) {
		return new Listener<FormEvent>() {
		public void handleEvent(FormEvent be) {
			// after submit event
			if(loadingWindow!=null)
				loadingWindow.destroyLoadingBox();
		
			String results = be.getResultHtml();
			if (results == null || results.equals("") || results.equals("<pre></pre>")) {
				window.fillRemarksArea("<div style='color:green; font-weight:bold;'>" + BabelFish.print().uploadSuccessful() + "</div>");
			} else {
				window.fillRemarksArea("<div style='color:red; font-weight:bold;'>" + BabelFish.print().uploadUnsuccessful()
							+ "<BR>" + results
							+ "</div>");
			}
			
		}
	 };
	}
	
	public static Listener<FormEvent> getBeforeSubmitListener(final CodingHierarchyUploaderWindow window) {
		return new Listener<FormEvent>() {
		public void handleEvent(FormEvent be) {
			// before submit event
			loadingWindow = new LoadingWindow(BabelFish.print().uploadingCodes(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
			loadingWindow.showLoadingBox();		
		}
	 };
	}
	
	
 
	
	public static FormHandler getFormHandler(final CodingHierarchyUploaderWindow window) {
		return new FormHandler() {
		    //int widgetCount = 0;
			LoadingWindow loadingWindow = null;
			public void onSubmit(FormSubmitEvent event) {
				loadingWindow = new LoadingWindow(BabelFish.print().uploadingDataset(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
			}
			public void onSubmitComplete(FormSubmitCompleteEvent event) {
				if(loadingWindow!=null)
					loadingWindow.destroyLoadingBox();
				String results = event.getResults();
				if (results == null || results.equals("") || results.equals("<pre></pre>")) {
					window.fillRemarksArea("<div style='color:green; font-weight:bold;'>" + BabelFish.print().uploadSuccessful() + "</div>");
				} else {
					window.fillRemarksArea("<div style='color:red; font-weight:bold;'>" + BabelFish.print().uploadUnsuccessful()
							+ "<BR>" + results
							+ "</div>");
				}
			}
		};
	}
}
