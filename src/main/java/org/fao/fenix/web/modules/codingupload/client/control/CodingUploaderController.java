package org.fao.fenix.web.modules.codingupload.client.control;

import org.fao.fenix.web.modules.codingupload.client.view.CodingUploaderWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;

public class CodingUploaderController {
	
	static LoadingWindow loadingWindow = null;


	public static Listener<FormEvent> getAfterSubmitFormListener(final CodingUploaderWindow window) {
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
	
	public static Listener<FormEvent> getBeforeSubmitListener(final CodingUploaderWindow window) {
		return new Listener<FormEvent>() {
		public void handleEvent(FormEvent be) {
			// before submit event
			loadingWindow = new LoadingWindow(BabelFish.print().uploadingCodes(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
			loadingWindow.showLoadingBox();		
		}
	 };
	}
	
	public static SelectionListener<ComponentEvent> getCancelListener(final CodingUploaderWindow window) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				window.getWindow().close();
			}
		};
	}
	
	public static SelectionListener<ComponentEvent> getClearListener(final CodingUploaderWindow window) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				window.getWindow().close();
				CodingUploaderWindow window = new CodingUploaderWindow();
				window.build();
			}
		};
	}
	
	
	public static SelectionListener<ButtonEvent> getUploadListener(final CodingUploaderWindow window) {
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
	

	
//	public static FormHandler getFormHandler(final CodingUploaderWindow window) {
//		return new FormHandler() {
//			int widgetCount = 0;
//			public void onSubmit(FormSubmitEvent event) {
//				window.setMessage("<div style='color:orange; font-weight:bold;'>" + I18N.print().loading() + "...</font>");
//			}
//			public void onSubmitComplete(FormSubmitCompleteEvent event) {
//				String results = event.getResults();
////				System.out.println("result " + results);
//				if (results == null || results.equals("") || results.equals("<pre></pre>")) {
//					window.setMessage("<div style='color:green; font-weight:bold;'>" + I18N.print().updateSuccessful() + "</div>");
//				} else {	
//					CodingServiceEntry.getInstance().tokenizeOutput(results, new AsyncCallback<String>() {
//						public void onSuccess(String response) {
//							window.setMessage("<div style='color:red; font-weight:bold;'>" + I18N.print().updateCSUsuccessful() + "<br>" + I18N.print().error() + ": "+ response + "</div>");	
//						}
//						public void onFailure(Throwable caught) {					
//						}
//					});
//					
//				}
//			}
//			
//		};
//	}
	
	
	

}
