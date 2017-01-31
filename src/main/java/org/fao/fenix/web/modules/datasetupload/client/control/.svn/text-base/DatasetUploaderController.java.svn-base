package org.fao.fenix.web.modules.datasetupload.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.birt.client.view.chart.viewer.UpdateChart;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.core.client.uploader.control.UploaderController;
import org.fao.fenix.web.modules.datasetupload.client.view.DatasetUploaderWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.re.common.services.REServiceEntry;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.Widget;

public class DatasetUploaderController extends UploaderController{

	static LoadingWindow loadingWindow = null;
	
	public static void updateChart(final DatasetUploaderWindow window) {
		REServiceEntry.getInstance().getLastSavedDataset(new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> list) {				
				System.out.println("datasetId: " + list.get(1));
				new UpdateChart(list.get(1));
			}
			public void onFailure(Throwable caught) {
				FenixAlert.error("RPC Failure", "getOpenAsTableListener @ DatasetUploaderController");
			}
		});
	}
	
	public static ClickListener getOpenAsTableListener(final DatasetUploaderWindow window) {
		return new ClickListener() {
			public void onClick(Widget widget) {
				REServiceEntry.getInstance().getLastSavedDataset(new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> list) {
						
						new TableWindow().showAllData(Long.parseLong(list.get(1)), false, false, list.get(0));
						//new TableWindow().build(Long.parseLong(list.get(1)), false, false, list.get(0));
						window.getWindow().close();
					}
					public void onFailure(Throwable caught) {
						FenixAlert.error("RPC Failure", "getOpenAsTableListener @ DatasetUploaderController");
					}
				});
			}
		};
	}
	
	public static ClickListener getOpenAsChartListener(final DatasetUploaderWindow window) {
		return new ClickListener() {
			public void onClick(Widget widget) {
				REServiceEntry.getInstance().getLastSavedDataset(new AsyncCallback<List<String>>() {
					public void onSuccess(List<String> list) {
						List<List<String>> datasetMap = new ArrayList<List<String>>();
						datasetMap.add(list);
						new ChartWizard(datasetMap);
						window.getWindow().close();
					}
					public void onFailure(Throwable caught) {
						FenixAlert.error("RPC Failure", "getOpenAsTableListener @ DatasetUploaderController");
					}
				});
			}
		};
	}
	
//	public static SelectionListener<ComponentEvent> getTemplateListener() {
//		return new SelectionListener<ComponentEvent>() {
//			public void componentSelected(ComponentEvent ce) {
//				new DatasetUploaderWindow().build();
//			}
//		};
//	}
	
	public static SelectionListener<ButtonEvent> getUploadListener(final DatasetUploaderWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
				// retrieve form parameters
				FileUploadField dataset = window.getFile();
				FileUploadField metadata = window.getMetadata();					
				FormPanel form = window.getFormPanel();
			
				try {
					
					if(form.isValid()){
				
						// get dataset name index
						int index = 0;

						for (int i = dataset.getValue().length() - 1; i >= 0; i--) 
							if ((dataset.getValue().charAt(i) == '/') || (dataset.getValue().charAt(i) == '\\')) {
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
						String fileName = dataset.getValue();
						if (index != 0)
							fileName = dataset.getValue().substring(index + 1, dataset.getValue().length());
						// get metadata name
					    String metadatafileName = metadata.getValue();
						if (index2 != 0)
							metadatafileName = metadata.getValue().substring(index2 + 1, metadata.getValue().length());

						// extension controls
						if (!fileName.contains(".zip") && !fileName.contains(".csv") && !fileName.contains(".xls"))
							throw new Throwable(BabelFish.print().badDatasetExtension());
						if (!fileName.contains(".zip") && !metadatafileName.contains("_metadata.xml"))
							throw new Throwable(BabelFish.print().badMetadataExtension());
						// submit form
						
						if (!fileName.contains(".zip") && (fileName.equals("") || metadatafileName.equals(""))){
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
	

	public static Listener<FormEvent> getAfterSubmitFormListener(final DatasetUploaderWindow window) {
		return new Listener<FormEvent>() {
		public void handleEvent(FormEvent be) {
			// after submit event
			if(loadingWindow!=null)
				loadingWindow.destroyLoadingBox();
		
			String results = be.getResultHtml();
			if (results == null || results.equals("") || results.equals("<pre></pre>")) {
				window.clearRemarksArea();
				window.setViewDatasetLinks();
			} else {
				window.fillRemarksArea("<div style='color:red; font-weight:bold;'>" + BabelFish.print().uploadUnsuccessful()
							+ "<BR>" + results
							+ "</div>");
			}
			
		}
	 };
	}
	
	public static Listener<FormEvent> getBeforeSubmitListener(final DatasetUploaderWindow window) {
		return new Listener<FormEvent>() {
		public void handleEvent(FormEvent be) {
			// before submit event
			loadingWindow = new LoadingWindow(BabelFish.print().uploadingDataset(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
			loadingWindow.showLoadingBox();		
		}
	 };
	}
	
	
 
	
	public static FormHandler getFormHandler(final DatasetUploaderWindow window) {
		return new FormHandler() {
		    //int widgetCount = 0;
			LoadingWindow loadingWindow = null;
			public void onSubmit(FormSubmitEvent event) {
				loadingWindow = new LoadingWindow(BabelFish.print().uploadingDataset(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
				//window.setMessage("<font color='orange'>" + I18N.print().loading() + "...</font>");
				//if (window.getLinkPanel() != null)
				//	window.getPanel().remove(window.getLinkPanel());
				//widgetCount = window.getPanel().getItemCount();
			}
			public void onSubmitComplete(FormSubmitCompleteEvent event) {
				if(loadingWindow!=null)
					loadingWindow.destroyLoadingBox();
				String results = event.getResults();
				if (results == null || results.equals("") || results.equals("<pre></pre>")) {
					window.setMessage("<div style='color:green; font-weight:bold;'>" + BabelFish.print().uploadSuccessful() + "</div>");
					window.setViewDatasetLinks();
				} else {
					window.setMessage("<div style='color:red; font-weight:bold;'>" + BabelFish.print().uploadUnsuccessful()
							+ "<BR>" + results
							+ "</div>");
				}
			}
		};
	}
	
}
