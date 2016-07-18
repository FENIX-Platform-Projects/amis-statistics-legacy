package org.fao.fenix.web.modules.ipc.client.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.ipc.client.view.old.CreateIPCWorkflow;
import org.fao.fenix.web.modules.ipc.client.view.old.ShowIPCWorkflow;
import org.fao.fenix.web.modules.ipc.client.view.old.ShowIPCWorkflowViewer;
import org.fao.fenix.web.modules.ipc.common.services.IPCServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ShowIPCWorkflowController {

	public static List<ComboBox<ListItem>> fieldList = new ArrayList<ComboBox<ListItem>>();

	public static void fillPageWidgets(final ShowIPCWorkflow window) {
		IPCServiceEntry.getInstance().getUserCountryList(new AsyncCallback<List<CodeVo>>() {

			public void onSuccess(List<CodeVo> result) {
				//System.out.println("getUserCountryList countries list size ------------------ " + result.size());

				if(!result.isEmpty()){
            		List<ListItem> countryItems = new ArrayList<ListItem>();

					for (CodeVo vo : result)
						countryItems.add(new ListItem(vo.getLabel(), vo.getCode()));

					sortStore(countryItems);

					ListStore<ListItem> store = new ListStore<ListItem>();
					store.add(countryItems);

					window.countryComboBox.setStore(store);
					
					window.formPanel.add(window.label);
					window.formPanel.add(window.countryComboBox);
					window.openIPC.addSelectionListener(openIPC(window));
					window.formPanel.setButtonAlign(HorizontalAlignment.CENTER);
					window.formPanel.addButton(window.openIPC);

				}
				else {
					window.message.setHtml(BabelFish.print().noWorkflowMessage());	
					window.formPanel.add(window.message);
					window.formPanel.addButton(window.createWorkflow);
					
				}
				window.panel.add(window.formPanel);
				
			}

			public void onFailure(Throwable caught) {
				Info.display("ShowIPCWorkflowController RPC Failed:", "Service call to {0} failed", "IPCServiceImpl:fillUserCountryList()");
			}
		});
	}
	

	public static SelectionListener<ButtonEvent> openIPC(final ShowIPCWorkflow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {

				if (window.formPanel.isValid()) {

					String countryComboId = window.countryComboBox.getId();
					ListItem selectedCountryItem = window.countryComboBox.getValue();
					final String countryLabel = selectedCountryItem.getName();
					final String countryGaulCode = selectedCountryItem.getValue();

					System.out.println("country id = " + countryComboId + " countryLabel = " + countryLabel
							+ " countryGaulCode " + countryGaulCode);

					IPCServiceEntry.getInstance().openIPCShowWorkflows(countryGaulCode, new AsyncCallback<String>(){
						public void onSuccess(String url){
							System.out.println("showIPCWorkflow: url = "+url);

							//create the iframe
							String iFrame = "<iframe src='" +url + "' width='100%' height='100%' />";
							new ShowIPCWorkflowViewer(iFrame, countryLabel).build();
							//new ShowIPCWorkflowViewer(iFrame, countryLabel).build();

							window.window.hide();
						}

						public void onFailure(Throwable caught) {
							Info.display("ShowIPCWorkflowController: RPC Failed:", "Service call to {0} failed", "ShowIPCWorkflowViewer()");
						}

					});

				} else
					FenixAlert.error("Error: Missing Field", "Please select a geographic area!");
			}
		};
	}


	private static void sortStore(List<ListItem> list) {
		Collections.sort(list, new Comparator<ListItem>() {
			public int compare(ListItem arg0, ListItem arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
	}
	
	public static SelectionListener<ButtonEvent> launchCreateIPCWorkflow(final ShowIPCWorkflow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				window.window.close();
				new CreateIPCWorkflow().build();
			}
		};
	}
}