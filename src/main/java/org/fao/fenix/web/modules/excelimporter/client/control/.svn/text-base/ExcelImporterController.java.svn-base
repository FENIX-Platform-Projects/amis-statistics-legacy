package org.fao.fenix.web.modules.excelimporter.client.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.view.chart.wizard.ChartWizard;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.chartdesigner.client.view.ChartDesignerWindow;
import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.core.common.exception.DirectUploadException;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.core.common.exception.InvalidFormException;
import org.fao.fenix.web.modules.core.common.exception.ParseDatasetHeadersException;
import org.fao.fenix.web.modules.excelimporter.client.view.CodeListImporterWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.CodeListPanel;
import org.fao.fenix.web.modules.excelimporter.client.view.CodeListStepA;
import org.fao.fenix.web.modules.excelimporter.client.view.CodingSystemSelectorWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ConfirmAppendWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ExcelImporterPanel;
import org.fao.fenix.web.modules.excelimporter.client.view.StepA;
import org.fao.fenix.web.modules.excelimporter.client.view.StepB;
import org.fao.fenix.web.modules.excelimporter.client.view.StepC;
import org.fao.fenix.web.modules.excelimporter.client.view.StepE;
import org.fao.fenix.web.modules.excelimporter.client.view.StepM;
import org.fao.fenix.web.modules.excelimporter.client.view.StepMU;
import org.fao.fenix.web.modules.excelimporter.common.services.ExcelImporterServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.view.modeldata.CodingNameModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.DescriptorVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.OptionVO;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ExcelImporterController {

	public static Map<String, String> dataTypeIconMap = new HashMap<String, String>();

	private static LoadingWindow loadingWindow = null;

	static {
		dataTypeIconMap.put(BabelFish.print().numericValue(), "value");
		dataTypeIconMap.put(BabelFish.print().textValue(), "value");
		dataTypeIconMap.put(BabelFish.print().date(), "date");
		dataTypeIconMap.put(BabelFish.print().measurementUnit(), "measurementUnit");
		dataTypeIconMap.put(BabelFish.print().indicator(), "indicator");
	}

	public static Listener<BaseEvent> datasetFileListener(final StepA step_a) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				FileUploadField datasetFile = step_a.getDataFile();
				String suffix = datasetFile.getFileInput().getValue().substring(datasetFile.getFileInput().getValue().length() -3);
				if (step_a.getFormPanel().getItemCount() > 3) {
					int end = step_a.getFormPanel().getItemCount();
					for (int i = 3 ; i < end ; i++) 
						step_a.getFormPanel().remove(step_a.getFormPanel().getWidget(step_a.getFormPanel().getItemCount() - 1));
					step_a.getFormPanel().getLayout().layout();
				}
				if (suffix.equalsIgnoreCase("xls")) {
					step_a.getFormPanel().add(step_a.addExcelTemplateCombo());
					step_a.getFormPanel().add(step_a.addMeasurementUnitValue());
				} else if (suffix.equalsIgnoreCase("csv")) {
					step_a.getFormPanel().insert(step_a.addDelimiterCombo(), (step_a.getFormPanel().getItemCount() - 1));
				}
				step_a.getFormPanel().getLayout().layout();
			}
		};
	}
	
	public static Listener<BaseEvent> excelDataLayoutListener(final StepA step_a) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				SimpleComboBox<String> combo = step_a.getExcelTemplate(); 
				combo.setName("TEMPLATE_{" + combo.getStore().getAt(combo.getSelectedIndex()).getValue() + "}");
				if ((step_a.getMetadataFile().getValue() != null) && step_a.getExcelTemplate().getSelectedIndex() == 1) {
					step_a.getMuValuePanel().setVisible(true);
//					step_a.getFormPanel().getLayout().layout();
				} else {
					step_a.getMuValuePanel().setVisible(false);
//					step_a.getFormPanel().getLayout().layout();
				}
			}
		};
	}
	
	public static SelectionListener<MenuEvent> dataTypeSelector(final MenuItem menuItem, final Button button) {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				button.setText(menuItem.getText());
				button.setIconStyle(dataTypeIconMap.get(menuItem.getText()));
			}
		};
	}

	public static void directUpload(ExcelImporterPanel panel) throws FenixGWTException, InvalidFormException, DirectUploadException, ParseDatasetHeadersException {
		
		StepA step_a = panel.getStep_a();
		
		if (step_a.getDataFile().isValid()) { // check that there is a file, CSV or XLS, anything
			
			if (step_a.getUploadPolicyCombo().isValid()) {
			
				String data_extension = step_a.getDataFile().getFileInput().getValue().substring(step_a.getDataFile().getFileInput().getValue().length() - 3);
				
				if (!data_extension.equalsIgnoreCase("CSV") && !data_extension.equalsIgnoreCase("XLS")) {
					
					throw new FenixGWTException("Data format is neither CSV nor XLS. Please provide a valid file.");
				
				} else if (data_extension.equalsIgnoreCase("CSV")) { // logic for CSV file
					
					if (step_a.getDelimiterCombo().isValid()) {
						
						if (!step_a.getMetadataFile().getFileInput().getValue().isEmpty()) { // if metadata is provided too, upload them
							
							String metadata_extension = step_a.getMetadataFile().getFileInput().getValue().substring(step_a.getMetadataFile().getFileInput().getValue().length() - 3);
							if (metadata_extension.equalsIgnoreCase("XML")) 
								defaultImport(panel);
							else 
								throw new FenixGWTException("Metadata file is not in XML format. Please provide a valid file.");
							
						} else {
							parseHeaders(panel);
						}
						
					} else {
						throw new InvalidFormException("Please make sure that the delimiter drop-down has been filled.");
					}
					
				} else if (data_extension.equalsIgnoreCase("XLS")) { // logic for XLS file
					
					if (step_a.getExcelTemplate().isValid()) {
						
						if (!step_a.getMetadataFile().getFileInput().getValue().isEmpty()) { // if metadata is provided too, upload them
							
							String metadata_extension = step_a.getMetadataFile().getFileInput().getValue().substring(step_a.getMetadataFile().getFileInput().getValue().length() - 3);
							if (metadata_extension.equalsIgnoreCase("XML")) 
								defaultImport(panel);
							else 
								throw new FenixGWTException("Metadata file is not in XML format. Please provide a valid file.");
							
						} else {
							parseHeaders(panel);
						}
						
					} else {
						throw new InvalidFormException("Please make sure that the Excel template drop-down has been filled.");
					}

				}
			
			} else {
				throw new InvalidFormException("Please make sure that the policy drop-down has been filled.");
			}
			
		} else {
			throw new InvalidFormException("Please make sure that \"File\" field has been filled.");
		}
	}
	
	public static void directUpload(CodeListImporterWindow w) throws FenixGWTException, InvalidFormException, DirectUploadException, ParseDatasetHeadersException {
		
		CodeListPanel panel = w.getCodeListPanel();
		
		CodeListStepA step_a = panel.getStep_a();
		
		if (step_a.getDataFile().isValid()) { // check that there is a file, CSV or XLS, anything
			
			if (step_a.getUploadPolicyCombo().isValid()) {
			
				String data_extension = step_a.getDataFile().getFileInput().getValue().substring(step_a.getDataFile().getFileInput().getValue().length() - 3);
				
				if (!data_extension.equalsIgnoreCase("CSV")) {
					
					throw new FenixGWTException("Data is not in CSV format. Please provide a valid file.");
				
				} else if (data_extension.equalsIgnoreCase("CSV")) { // logic for CSV file
					
					if (step_a.getDelimiterCombo().isValid()) {
						
						if (!step_a.getMetadataFile().getFileInput().getValue().isEmpty()) { // if metadata is provided too, upload them
							
							String metadata_extension = step_a.getMetadataFile().getFileInput().getValue().substring(step_a.getMetadataFile().getFileInput().getValue().length() - 3);
							if (metadata_extension.equalsIgnoreCase("XML"))
								defaultImport(w);
							else
								throw new FenixGWTException("Metadata file is not in XML format. Please provide a valid file.");
							
						} else {
							System.out.println("XML has not been provided");
							panel.getLayout().setActiveItem(panel.getStep_b().getLayoutContainer());
						}
						
					} else {
						throw new InvalidFormException("Please make sure that the delimiter drop-down has been filled.");
					}
					
				}
			
			} else {
				throw new InvalidFormException("Please make sure that the policy drop-down has been filled.");
			}
			
		} else {
			throw new InvalidFormException("Please make sure that \"File\" field has been filled.");
		}
	}
	
	private static void parseHeaders(ExcelImporterPanel panel) throws ParseDatasetHeadersException {
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String parseDatasetHeadersServletURL = ep.getParseDatasetHeadersServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());
		StepA step_a = panel.getStep_a();
		step_a.getFormPanel().setAction(parseDatasetHeadersServletURL);
		step_a.getFormPanel().setEncoding(Encoding.MULTIPART);
		step_a.getFormPanel().setMethod(Method.POST);
		step_a.getFormPanel().addListener(Events.BeforeSubmit, beforeSubmitListener());
		step_a.getFormPanel().addListener(Events.Submit, afterParseHeadersSubmitFormListener(panel));
		step_a.getFormPanel().submit();
		throw new ParseDatasetHeadersException("Parsing Dataset Headers...");
	}

	private static void defaultImport(ExcelImporterPanel panel) throws DirectUploadException {
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getSingleDatasetUploadServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());
		StepA step_a = panel.getStep_a();
		step_a.getFormPanel().setAction(urlServletUpload);
		step_a.getFormPanel().setEncoding(Encoding.MULTIPART);
		step_a.getFormPanel().setMethod(Method.POST);
		step_a.getFormPanel().addListener(Events.BeforeSubmit, beforeSubmitListener());
		step_a.getFormPanel().addListener(Events.Submit, afterSubmitFormListener(panel));
		step_a.getFormPanel().submit();
		
		for (Field f : step_a.getFormPanel().getFields())
			System.out.println("UPLOADER: " + f.getName());
		
		throw new DirectUploadException("Uploading Dataset...");
	}
	
	private static void defaultImport(CodeListImporterWindow w) throws DirectUploadException {
		CodeListPanel panel = w.getCodeListPanel();
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String urlServletUpload = ep.getServiceEntryPointCodingUploader(GWT.getModuleBaseURL(), GWT.getModuleName());
		CodeListStepA step_a = panel.getStep_a();
		step_a.getFormPanel().setAction(urlServletUpload);
		step_a.getFormPanel().setEncoding(Encoding.MULTIPART);
		step_a.getFormPanel().setMethod(Method.POST);
		step_a.getFormPanel().addListener(Events.BeforeSubmit, beforeSubmitListener());
		step_a.getFormPanel().addListener(Events.Submit, afterSubmitFormListener(w));
		step_a.getFormPanel().submit();
		throw new DirectUploadException("Uploading Code List...");
	}
	
	public static Listener<FormEvent> afterParseHeadersSubmitFormListener(final ExcelImporterPanel panel) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				if (loadingWindow != null)
					loadingWindow.destroyLoadingBox();
				String results = be.getResultHtml();
				int startIDX = 1 + results.indexOf('>');
				int endIDX = results.indexOf('<', startIDX);
				String cleanResponse = results.substring(startIDX, endIDX);
				List<String> headers = parseHeadersFromServletResponse(cleanResponse);
				StepB step_b = panel.getStep_b();
				showUserHeaders(step_b, headers);
				panel.getLayout().setActiveItem(step_b.getLayoutContainer());
				panel.getNextButton().setEnabled(true);
				panel.getPrevButton().setEnabled(true);
			}
		};
	}
	
	private static void showUserHeaders(StepB step_b, List<String> headers) {
		for (String h : headers) 
			step_b.addColumnPanel(h);
	}
	
	private static List<String> parseHeadersFromServletResponse(String response) {
		List<String> headers = new ArrayList<String>();
		int startIDX = 0;
		int endIDX = 0;
		for (int i = 0 ; i < response.length() ; i++) {
			if (response.charAt(i) == ':') {
				endIDX = i;
				String header = response.substring(startIDX, endIDX);
				headers.add(header);
				startIDX = 1 + i;
			}
		}
		// it does not parse the last header, fix this...
		for (int i = response.length() - 1 ; i >= 0 ; i--) {
			if (response.charAt(i) == ':') {
				headers.add(response.substring(1 + i));
				break;
			}
		}
		return headers;
	}

	private static Listener<FormEvent> beforeSubmitListener() {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				loadingWindow = new LoadingWindow(BabelFish.print().uploadingDataset(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
			}
		};
	}

	public static Listener<FormEvent> afterSubmitFormListener(final ExcelImporterPanel panel) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				if (loadingWindow != null)
					loadingWindow.destroyLoadingBox();
				String results = be.getResultHtml();
				System.out.println("SERVLET COMPLETE WITH RESULT: " + results);
				if (results.contains("datasetID")) {
					int startIDX = 1 + results.indexOf(":");
					int endIDX = results.indexOf("</pre>");
					Long datasetID = Long.valueOf(results.substring(startIDX, endIDX));
					StepE step_e = panel.getStep_e();
					step_e.getTable().addClickHandler(openAsTable(datasetID, "Dataset"));
					step_e.getChart().addClickHandler(openAsChart(datasetID));
					step_e.getOlap().addClickHandler(openAsOLAP(datasetID));
					step_e.getCsv().addClickHandler(ExcelImporterController.exportCSV(datasetID));
					step_e.getXml().addClickHandler(ExcelImporterController.exportXML(datasetID));
					step_e.getZip().addClickHandler(ExcelImporterController.exportZIP(datasetID));
					BirtServiceEntry.getInstance().chartUpdate(datasetID, new AsyncCallback() {

						@Override
						public void onFailure(Throwable arg0) {
							
							
						}

						@Override
						public void onSuccess(Object arg0) {
//							System.out.println("ok updated");
							
						}
					});
//					ChartUpdater chartUpdater = new ChartUpdater();
//					chartUpdater.updateDatasetCharts(datasetID);
//					new UpdateChart(String.valueOf(datasetID));
					panel.getLayout().setActiveItem(step_e.getLayoutContainer());
					panel.getNextButton().setEnabled(false);
					panel.getPrevButton().setEnabled(false);
				} else if (results == null || results.equals("") || results.equals("<pre></pre>")) {
					// window.clearRemarksArea();
					// window.setViewDatasetLinks();
				} else {
					panel.getLayout().setActiveItem(panel.getStepServiceMessage().getLayoutContainer());
					panel.getStepServiceMessage().setMessage(results, "RED");
				}
			}
		};
	}
	
	public static Listener<FormEvent> afterSubmitFormListener(final CodeListImporterWindow w) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				if (loadingWindow != null)
					loadingWindow.destroyLoadingBox();
				String results = be.getResultHtml();
				System.out.println("SERVLET COMPLETE WITH RESULT: " + results);
				if (results.contains("codeListID")) {
					int startIDX = 1 + results.indexOf(":");
					int endIDX = results.indexOf("</pre>");
					Long codeListID = Long.valueOf(results.substring(startIDX, endIDX));
					w.getWindow().close(); // TODO restore this
					new CodingSearchResults().build(codeListID);
				} else if (results == null || results.equals("") || results.equals("<pre></pre>")) {
					// window.clearRemarksArea();
					// window.setViewDatasetLinks();
				} else {
					w.getCodeListPanel().getLayout().setActiveItem(w.getCodeListPanel().getStepServiceMessage().getLayoutContainer());
					w.getCodeListPanel().getStepServiceMessage().setMessage(results, "RED");
				}
			}
		};
	}
	
	public static ClickHandler openAsTable(final Long datasetID, final String datasetTitle) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				new TableWindow().build(datasetID, false, false, datasetTitle);
			}
		};
	}
	
	public static ClickHandler exportCSV(final Long datasetID) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					ExcelImporterServiceEntry.getInstance().exportCSV(datasetID, new AsyncCallback<String>() {
						public void onSuccess(String url) {
							Window.open("../exportObject/" + url, "_blank", "status=no");
						}
						public void onFailure(Throwable e) {
							FenixAlert.error("ERROR", e.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}
	
	public static ClickHandler exportXML(final Long datasetID) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					ExcelImporterServiceEntry.getInstance().exportXML(datasetID, new AsyncCallback<String>() {
						public void onSuccess(String url) {
							Window.open("../exportObject/" + url, "_blank", "status=no");
						}
						public void onFailure(Throwable e) {
							FenixAlert.error("ERROR", e.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}
	
	public static ClickHandler exportZIP(final Long datasetID) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				try {
					ExcelImporterServiceEntry.getInstance().exportZIP(datasetID, new AsyncCallback<String>() {
						public void onSuccess(String url) {
							Window.open("../exportObject/" + url, "_blank", "status=no");
						}
						public void onFailure(Throwable e) {
							FenixAlert.error("ERROR", e.getMessage());
						}
					});
				} catch (FenixGWTException e) {
					FenixAlert.error("ERROR", e.getMessage());
				}
			}
		};
	}
	
	public static ClickHandler openAsChart(final Long datasetID) {
		return new ClickHandler() {
			/** This is madness, but it's how the <code>ChartWizard</code> works... */
			public void onClick(ClickEvent event) {
				List<String> l = new ArrayList<String>();
				l.add(String.valueOf(datasetID));
				l.add(String.valueOf(datasetID));
				List<List<String>> datasetMap = new ArrayList<List<String>>();
				datasetMap.add(l);
//				new ChartWizard(datasetMap);
				List<ResourceChildModel> models = new ArrayList<ResourceChildModel>();
				ResourceChildModel m = new ResourceChildModel();
				m.setId(String.valueOf(datasetID));
				m.setName("Uploaded Dataset");
				new ChartDesignerWindow().build(models);
			}
		};
	}
	
	public static ClickHandler openAsOLAP(final Long datasetID) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				OlapWindow w = new OlapWindow();
				if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
					w.build(true, datasetID, true);
				} else {
					w.build(true, datasetID, false);
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> next(final CodeListImporterWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				CodeListPanel p = w.getCodeListPanel();
				if (p.getLayout().getActiveItem().equals(p.getStep_a().getLayoutContainer())) {
					try {
						if (p.getStep_a().getDataFile().isValid()) {
							ExcelImporterController.directUpload(w);
							p.getLayout().setActiveItem(p.getStep_b().getLayoutContainer());
							p.getPrevButton().setEnabled(true);
						} else {
							FenixAlert.info("INFO", "Please fill all the mandatory fields marked in <font color='#CA1616'>red</font>.");
						}
					} catch (FenixGWTException e) {
						FenixAlert.alert("Warning", e.getMessage());
					} catch (InvalidFormException e) {
						FenixAlert.alert("Warning", e.getMessage());
					} catch (DirectUploadException e) {
						
					} catch (ParseDatasetHeadersException e) {
						
					}
				} else if (p.getLayout().getActiveItem().equals(p.getStep_b().getLayoutContainer())) {
					if (isValid(p)) {
						new ConfirmAppendWindow(p).buildForCodeList("You are going to upload " + w.getCodeListPanel().getStep_a().getDataFile().getValue() + ". Are you sure?");
						p.getNextButton().setEnabled(false);
					} else {
						FenixAlert.info("INFO", "Please fill all the mandatory fields marked in <font color='#CA1616'>red</font>.");
					}
				}
			}
		};
	}
	
	private static boolean isValid(CodeListPanel panel) {
		boolean title = panel.getStep_b().getTitleField().isValid();;
		boolean type = panel.getStep_b().getCodingType().isValid();
		boolean sourceName = panel.getStep_b().getSourceField().isValid();
		boolean sourceRegion = panel.getStep_b().getGaulList().isValid();
		if (title && type && sourceName && sourceRegion)
			return true;
		return false;
	}
	
	public static SelectionListener<ButtonEvent> next(final ExcelImporterPanel p) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (p.getLayout().getActiveItem().equals(p.getStep_a().getLayoutContainer())) {
					try {
						ExcelImporterController.directUpload(p);
						p.getLayout().setActiveItem(p.getStep_b().getLayoutContainer());
						p.getPrevButton().setEnabled(true);
					} catch (FenixGWTException e) {
						FenixAlert.alert("Warning", e.getMessage());
					} catch (InvalidFormException e) {
						FenixAlert.alert("Warning", e.getMessage());
					} catch (DirectUploadException e) {
						
					} catch (ParseDatasetHeadersException e) {
						
					}
				} else if (p.getLayout().getActiveItem().equals(p.getStep_b().getLayoutContainer())) {
					if (isValid(p.getStep_b()) && p.getStep_a().getExcelTemplate().getSelection().isEmpty()) {
						p.getLayout().setActiveItem(p.getStep_c().getLayoutContainer());	
					} else if (isValid(p.getStep_b()) && p.getStep_a().getExcelTemplate().getSelection().get(0).getValue().equalsIgnoreCase("GIEWS Workstation")) {
						p.getLayout().setActiveItem(p.getStep_c().getLayoutContainer());
					} else if (isValid(p.getStep_b()) && p.getStep_a().getExcelTemplate().getSelection().get(0).getValue().equalsIgnoreCase("World Bank")) {
						if (containsMU(p.getStep_b())) {
							p.getLayout().setActiveItem(p.getStep_c().getLayoutContainer());
						} else {
							p.getLayout().setActiveItem(p.getStep_mu().getLayoutContainer());
						}
					} else {
						FenixAlert.info("INFO", "Please associate a data type to every column.");
					}
				} else if (p.getLayout().getActiveItem().equals(p.getStep_c().getLayoutContainer())) {
					if (isValid(p.getStep_c())) {
						p.getLayout().setActiveItem(p.getStep_d().getLayoutContainer());
						findSimilarDatasets(p);
					} else {
						FenixAlert.info("INFO", "Please fill all the fields.");
					}
				} else if (p.getLayout().getActiveItem().equals(p.getStep_mu().getLayoutContainer())) {
					if (isValid(p.getStep_mu())) {
						p.getLayout().setActiveItem(p.getStep_c().getLayoutContainer());
						findSimilarDatasets(p);
					} else {
						FenixAlert.info("INFO", "Please fill all the fields.");
					}
				} else if (p.getLayout().getActiveItem().equals(p.getStep_m().getLayoutContainer())) {
//					if (isValid(p.getStep_m())) {
//						new ConfirmAppendWindow(p).build(p.getStep_d().getGrid());
//						p.getNextButton().setEnabled(true);
//					} else {
//						FenixAlert.info("INFO", "Please fill all the mandatory fields marked in <font color='#CA1616'>red</font>.");
//					}
				} else if (p.getLayout().getActiveItem().equals(p.getStep_d().getLayoutContainer())) {
					new ConfirmAppendWindow(p).build(p.getStep_d().getGrid());
					p.getNextButton().setEnabled(false);
				}
			}		
		};
	}
	
	private static boolean containsMU(StepB step_b) {
		List<HorizontalPanel> panels = step_b.getColumnPanelList();
		System.out.println(panels.size() + " panels to check");
		for (int i = 0 ; i < panels.size() ; i++) {
			ToolBar toolbar = (ToolBar)panels.get(i).getData("TOOLBAR");
			TextField<String> dataTypeField = new TextField<String>();
			dataTypeField.setValue(((Button)toolbar.getWidget(0)).getText());
			dataTypeField.setName("DATA_TYPE_" + i + "_{" + dataTypeField.getValue() + "}");
			System.out.println(dataTypeField.getValue());
			if (dataTypeField.getValue().equalsIgnoreCase("Measurement Unit"))
				return true;
		}
		return false;
	}
	
	private static boolean isValid(StepM step_m) {
		boolean title = step_m.getTitleField().isValid();
		boolean category = step_m.getCategoryList().isValid();
		boolean keywords = step_m.getKeywordsField().isValid();
		boolean region = step_m.getGaulList().isValid();
		if (title && category && keywords && region)
			return true;
		return false;
	}
	
	private static boolean isValid(StepC step_c) {
		boolean source = step_c.getSourceField().isValid();
		boolean country = step_c.getGaulList().isValid();
		boolean period = step_c.getPeriodTypeCodeCombo().isValid();
		if (source && country && period)
			return true;
		return false;
	}
	
	private static boolean isValid(StepMU step_mu) {
		if (step_mu.getMuField().isValid() && step_mu.getMuHeader().isValid() && step_mu.getQuantityHeader().isValid())
			return true;
		return false;
	}
	
	private static boolean isValid(StepB step_b) {
		List<HorizontalPanel> panels = step_b.getColumnPanelList();
		String check = BabelFish.print().pleaseSelect();
		int selected = 0;
		for (HorizontalPanel p : panels) {
			ToolBar t = (ToolBar)p.getData("TOOLBAR");
			Button b = (Button)t.getWidget(0);
			if (!b.getText().equals(check))
				selected++;
		}
		if (selected == panels.size())
			return true;
		return false;
	}
	
	private static void findSimilarDatasets(final ExcelImporterPanel p) {
		List<DescriptorVO> descriptorVOs = collectDescriptors(p);
		String sourceName = p.getStep_c().getSourceField().getValue();
		String sourceRegion = p.getStep_c().getGaulList().getSelection().get(0).getGaulCode();
		String periodTypeCode = p.getStep_c().getPeriodTypeCodeCombo().getSelection().get(0).getValue();
		ExcelImporterServiceEntry.getInstance().findSimilarDatasets(sourceName, sourceRegion, periodTypeCode, descriptorVOs, new AsyncCallback<List<ResourceChildModel>>() {
			public void onSuccess(List<ResourceChildModel> models) {
				if (models.isEmpty()) {
					p.getLayout().setActiveItem(p.getStep_m().getLayoutContainer());
					p.getNextButton().setEnabled(true);
					p.getNextButton().addSelectionListener(GhostFormController.importNewDataset(p));
				} else {
					p.getStep_d().getStore().removeAll();
					p.getStep_d().getStore().add(models);
				}
			}
			public void onFailure(Throwable e) {
				FenixAlert.error("ERROR", e.getMessage());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private static List<DescriptorVO> collectDescriptors(ExcelImporterPanel panel) {
		StepB step_b = panel.getStep_b();
		List<DescriptorVO> vos = new ArrayList<DescriptorVO>();
		for (HorizontalPanel p : step_b.getColumnPanelList()) {
			TextField<String> columnNameField = (TextField<String>)p.getData("COLUMN_NAME_FIELD");
			ToolBar toolbar = (ToolBar)p.getData("TOOLBAR");
			String header = columnNameField.getValue();
			String contentDescriptor = ((Button)toolbar.getWidget(0)).getText();
			DescriptorVO vo = new DescriptorVO();
			vo.setContentDescriptor(contentDescriptor);
			vo.setHeader(header);
			if (!contentDescriptor.equals("Numeric Value"))
				vo.setKey(true);
			String cs_name = p.getData("CODING_SYSTEM_NAME");
			String cs_type = p.getData("CODING_SYSTEM_TYPE");
			if ((cs_name != null) && (cs_type != null))
				vo.addOption(new OptionVO(cs_type, cs_name));
			vos.add(vo);
		}
		
		// in 'World Bank' template case, user can specify the measurement unit, let's collect it
		StepMU step_mu = panel.getStep_mu();
		System.out.println("\t\t\t\t\tMU valid? " + step_mu.getMuField().isValid());
		if (step_mu.getMuField().isValid()) {
			DescriptorVO vo = new DescriptorVO();
			vo.setContentDescriptor("Measurement Unit");
			vo.setHeader(step_mu.getMuHeader().getValue());
			vo.setKey(true);
			vos.add(vo);
			vo = new DescriptorVO();
			vo.setContentDescriptor("Date");
			vo.setKey(true);
			vo.setHeader("Date");
			vos.add(vo);
			vo = new DescriptorVO();
			vo.setContentDescriptor("Numeric Value");
			vo.setHeader(step_mu.getQuantityHeader().getValue());
			vos.add(vo);
		}
		
		for (DescriptorVO vo : vos)
			System.out.println("\t\t\t\t\t" + vo.getHeader() + " ===> " + vo.getContentDescriptor());
		
		return vos;
	}
	
	public static SelectionListener<ButtonEvent> prev(final ExcelImporterPanel p) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (p.getLayout().getActiveItem().equals(p.getStep_b().getLayoutContainer())) {
					p.getLayout().setActiveItem(p.getStep_a().getLayoutContainer());
					p.getPrevButton().setEnabled(false);
				} else if (p.getLayout().getActiveItem().equals(p.getStep_c().getLayoutContainer())) {
					p.getLayout().setActiveItem(p.getStep_b().getLayoutContainer());
				} else if (p.getLayout().getActiveItem().equals(p.getStep_d().getLayoutContainer())) {
					p.getLayout().setActiveItem(p.getStep_c().getLayoutContainer());
				} else if (p.getLayout().getActiveItem().equals(p.getStep_e().getLayoutContainer())) {
					p.getLayout().setActiveItem(p.getStep_d().getLayoutContainer());
					p.getNextButton().setEnabled(true);
				}  else if (p.getLayout().getActiveItem().equals(p.getStep_m().getLayoutContainer())) {
					if (p.getStep_d().getGrid().getStore().getCount() > 0) {
						p.getLayout().setActiveItem(p.getStep_d().getLayoutContainer());
					} else {
						p.getLayout().setActiveItem(p.getStep_c().getLayoutContainer());
					}
				}
			}		
		};
	}
	
	public static SelectionListener<ButtonEvent> prev(final CodeListImporterWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> selectCodingSystem(final HorizontalPanel columnPanel) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				CodingSystemSelectorWindow w = new CodingSystemSelectorWindow(columnPanel);
				w.build();
				w.getSelectButton().addSelectionListener(applyCodingSystem(w, columnPanel));
			}
		};
	}
	
	public static SelectionListener<IconButtonEvent> uploadCodingSystem() {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
//				new CodingUploaderWindow().build();
				new CodeListImporterWindow().build();
			}
		};
	}
	
	public static SelectionChangedListener<SimpleComboValue<String>> codingTypeSelectionChanged(final CodingSystemSelectorWindow w) {
		return new SelectionChangedListener<SimpleComboValue<String>>() {
			public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
				ExcelImporterServiceEntry.getInstance().findAllCodingSystems(se.getSelectedItem().getValue(), new AsyncCallback<List<CodingNameModelData>>() {
					public void onSuccess(List<CodingNameModelData> l) {
						w.getCodingNameStore().removeAll();
						for (CodingNameModelData m : l) 
							w.getCodingNameStore().add(m);
						w.getCodingNameCombo().setStore(w.getCodingNameStore());
						w.getCenter().getLayout().layout();
					}
					public void onFailure(Throwable e) {
						FenixAlert.error("ERROR", e.getMessage());
					}
				});
			}
		};
	}
	
	@SuppressWarnings("deprecation")
	public static SelectionListener<ButtonEvent> applyCodingSystem(final CodingSystemSelectorWindow w, final HorizontalPanel columnPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				String cs_name = w.getCodingNameCombo().getSelection().get(0).getCodingName();
				String cs_type = w.getCodingTypeCombo().getSelection().get(0).getValue();
				columnPanel.setData("CODING_SYSTEM_NAME", cs_name);
				columnPanel.setData("CODING_SYSTEM_TYPE", cs_type);
				IconButton i = (IconButton)columnPanel.getData("ICON_BUTTON");
				i.setStyleName("x-icon-btn x-nodrag greenThick x-component ");
				i.setToolTip(cs_name + " [" + cs_type + "]");
				columnPanel.getLayout().layout();
				w.getWindow().close();
			}
		};
	}

}