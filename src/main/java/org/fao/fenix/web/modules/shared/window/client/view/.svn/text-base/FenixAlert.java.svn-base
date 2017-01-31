package org.fao.fenix.web.modules.shared.window.client.view;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;

public class FenixAlert {

	//For warning messages
	public static void alert(final String alertTitle, final String alertMessage, final String ...values) {
		MessageBox.alert(alertTitle, alertMessage, new Listener<MessageBoxEvent>() {
			public void handleEvent(MessageBoxEvent event) {
				Info.display(alertTitle, alertMessage, values);
			}
		});
	}

	//For onFailure and error messages
	public static void error(final String errorTitle, final String errorMessage, final String ...values) {
		MessageBox box = new MessageBox();   
		box.setButtons(MessageBox.OK);   
		box.setIcon(MessageBox.ERROR);   
		box.setTitle("<b>"+errorTitle+"</b>");   
		box.addCallback(new Listener<MessageBoxEvent>() {
			public void handleEvent(MessageBoxEvent event) {
				//Info.display(errorTitle, errorMessage, values);
			}   
		});   

		String message = Format.substitute(errorMessage, values);
		box.setMessage("<b>"+message+"</b>");
		box.show();   
	}

	// For OnSuccess messages
	public static void info(final String infoTitle, final String infoMessage, final String ...values) {

		MessageBox box = new MessageBox();   
		box.setButtons(MessageBox.OK);   
		box.setIcon(MessageBox.INFO);   
		box.setTitle("<b>"+infoTitle+"</b>");   
		box.addCallback(new Listener<MessageBoxEvent>() {
			public void handleEvent(MessageBoxEvent event) {
				//Info.display(infoTitle, infoMessage, values); 
			}   
		});   

		String message = Format.substitute(infoMessage, values);
		box.setMessage("<b>"+message+"</b>");
		box.show();   
	}
	
	public static void infoAmisRegistration(final String infoTitle, final String infoMessage, final String ...values) {

		MessageBox box = new MessageBox();   
		box.setButtons(MessageBox.OK);   
		box.setIcon(MessageBox.INFO);   
		box.setTitle("<b>"+infoTitle+"</b>");   
		box.addCallback(new Listener<MessageBoxEvent>() {
			public void handleEvent(MessageBoxEvent event) {
				//Info.display(infoTitle, infoMessage, values); 
			}   
		});   

		String message = Format.substitute(infoMessage, values);
		box.setMessage("<div style= 'font-family:sans-serif;'>"+message+"</div>");
		box.show();   
	}
}