package org.fao.fenix.web.modules.console.client.control;

import java.util.List;

import org.fao.fenix.web.modules.console.common.service.ConsoleServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class ConsoleController {

	public static ListBox createLogsList(final ListBox logsList) {
		ConsoleServiceEntry.getInstance().getLogsList(new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> result) {
				for (String logName : result) 
					logsList.addItem(logName);
			}
			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPCFailed", "ConsoleController @ createLogsList");
			}
		});
		return logsList;
	}
	
	public static ChangeListener createLogListChangeListener(final HTML textbox) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				final ListBox listbox = (ListBox)widget;
				String fileName = listbox.getItemText(listbox.getSelectedIndex());
				ConsoleServiceEntry.getInstance().getLogContent(fileName, new AsyncCallback<String>() {
					public void onSuccess(String result) {
						System.out.println(result);
						textbox.setHTML(result);
					}
					public void onFailure(Throwable caught) {
						FenixAlert.alert("RPCFailed", "ConsoleController @ createLogListChangeListener");
					}
				});
			}
		};
	}
	
}
