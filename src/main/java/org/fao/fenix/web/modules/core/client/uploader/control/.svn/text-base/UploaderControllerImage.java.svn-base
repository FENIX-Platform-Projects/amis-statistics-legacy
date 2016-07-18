package org.fao.fenix.web.modules.core.client.uploader.control;

import org.fao.fenix.web.modules.core.client.uploader.view.images.UploaderImage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.Widget;

public class UploaderControllerImage {

	UploaderImage uploaderImage=null;
		
	public UploaderControllerImage() {
		uploaderImage = getInstance();
	}
	
	public UploaderImage getInstance() {
		if (uploaderImage == null) {
			uploaderImage = new UploaderImage();
			build();
		} 
		
		return uploaderImage;
	}
	
	public void build() {
		setEvents();
	}
	
	private void setEvents() {
		uploaderImage.getSubmit().addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				String imageFile = uploaderImage.getUpload().getFilename();
				
				if (imageFile.isEmpty()) {
					FenixAlert.error(BabelFish.print().error(), "Missing image file");  // FIXME i18n
				} else if ( ! imageFile.toLowerCase().endsWith(".gif") && 
							! imageFile.toLowerCase().endsWith(".jpg") && 
							! imageFile.toLowerCase().endsWith(".jpeg") && 
							! imageFile.toLowerCase().endsWith(".png")) {
					FenixAlert.error(BabelFish.print().error(),
									 "Expected a .gif or .jpg or .png file");  // FIXME i18n
				} else {
					uploaderImage.getForm().submit();
				}
			}
		});
		
		uploaderImage.getForm().addFormHandler(new FormHandler() {
			
			/**
			 * Fired just before submit form, used to perform checks.
			 */
			public void onSubmit(FormSubmitEvent event) {

			}

			/**
			 * Fired on submission complete, could be used as a test.
			 */
			public void onSubmitComplete(FormSubmitCompleteEvent event) {

				String results = event.getResults();
				
				if (results == null || results.equals("") || results.equals("<pre></pre>")) {
					uploaderImage.getMessage().setHTML("<br/><div style='font-weight:bold; color:green;'>The image was successfully uploaded.</div>");
				} else if (results.equals("<pre>The image is too large.</pre>")) {
					uploaderImage.getMessage().setHTML("<br/><div style='font-weight:bold; color:red;'>The image is too large. (Max Size: 1MB)</div>");
				} else {
					uploaderImage.getMessage().setHTML("<br/><div style='font-weight:bold; color:red;'>The image was NOT successfully uploaded.</div>");
				}
				
			}
		});
		

	}
}
