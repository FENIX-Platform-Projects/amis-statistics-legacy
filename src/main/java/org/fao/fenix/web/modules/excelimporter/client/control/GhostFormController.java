package org.fao.fenix.web.modules.excelimporter.client.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.excelimporter.client.view.ConfirmAppendWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ExcelImporterPanel;
import org.fao.fenix.web.modules.excelimporter.client.view.StepA;
import org.fao.fenix.web.modules.excelimporter.client.view.StepB;
import org.fao.fenix.web.modules.excelimporter.client.view.StepC;
import org.fao.fenix.web.modules.excelimporter.client.view.StepD;
import org.fao.fenix.web.modules.excelimporter.client.view.StepE;
import org.fao.fenix.web.modules.excelimporter.client.view.StepM;
import org.fao.fenix.web.modules.excelimporter.client.view.StepMU;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.re.common.vo.ResourceChildModel;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;

public class GhostFormController {

	private static LoadingWindow loadingWindow = null;
	
	private static Map<String, String> dataTypeMap;
	
	static {
		dataTypeMap = new HashMap<String, String>();
		dataTypeMap.put(BabelFish.print().date(), "Date");
		dataTypeMap.put(BabelFish.print().indicator(), "Indicator");
		dataTypeMap.put(BabelFish.print().numericValue(), "Numeric Value");
		dataTypeMap.put(BabelFish.print().measurementUnit(), "Measurement Unit");
	}
	
	@SuppressWarnings("deprecation")
	public static SelectionListener<ButtonEvent> confirmAppend(final ExcelImporterPanel panel, final ConfirmAppendWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (panel.getStep_d().getGrid().getSelectionModel().getSelection().isEmpty()) {
					w.getWindow().close();
					panel.getLayout().setActiveItem(panel.getStep_m().getLayoutContainer());
					panel.getNextButton().setEnabled(true);
					panel.getNextButton().addSelectionListener(importNewDataset(panel));
				} else {
					createGhostForm(panel, w);
				}
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> importNewDataset(final ExcelImporterPanel panel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				if (isValid(panel.getStep_m())) {
					ConfirmAppendWindow w = new ConfirmAppendWindow(panel);
					w.build(panel.getStep_d().getGrid());
					w.getConfirmButton().removeAllListeners();
					w.getConfirmButton().addSelectionListener(importNewDataset(panel, w));
				} else {
					FenixAlert.info("INFO", "Please fill all the mandatory fields marked in <font color='#CA1616'>red</font>.");
				}
			}
		};
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
	
	public static SelectionListener<ButtonEvent> importNewDataset(final ExcelImporterPanel panel, final ConfirmAppendWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createGhostForm(panel, w);
			}
		};
	}
	
	private static FormPanel createGhostForm(final ExcelImporterPanel panel, final ConfirmAppendWindow w) {
		
		FormPanel fp = new FormPanel();
		
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String ghostFormServletURL = ep.getGhostFormServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());
		
		addStepAFields(fp, panel.getStep_a());
		addStepBFields(fp, panel);
		addStepCFields(fp, panel.getStep_c());
		addStepMFields(fp, panel.getStep_m());
		addStepDFields(fp, panel.getStep_d());
		addStepMUFields(fp, panel.getStep_mu());
		
		w.getCenter().remove(w.getCenter().getWidget(0));
		w.getCenter().add(fp);
		w.getCenter().getLayout().layout();
		
		fp.setAction(ghostFormServletURL);
		fp.setEncoding(Encoding.MULTIPART);
		fp.setMethod(Method.POST);
		fp.addListener(Events.BeforeSubmit, beforeSubmitListener());
		fp.addListener(Events.Submit, afterSubmitFormListener(panel, w));
		fp.submit();
		
		return fp;
	}
	
	private static void addStepAFields(FormPanel fp, StepA step_a) {
		
		FileUploadField dataset = step_a.getDataFile();
		dataset.setName("DATASET");
		fp.add(dataset);
		
		FileUploadField metadata = step_a.getMetadataFile();
		metadata.setName("METADATA");
		fp.add(metadata);
		
		SimpleComboBox<String> delimiter = step_a.getDelimiterCombo();
		if (!delimiter.getSelection().isEmpty())
			delimiter.setName("DELIMITER_{" + delimiter.getSelection().get(0).getValue() + "}");
		else
			delimiter.setName("DELIMITER_{,}");
		fp.add(delimiter);
		
		SimpleComboBox<String> template = step_a.getExcelTemplate();
		if (!template.getSelection().isEmpty())
			template.setName("TEMPLATE_{" + template.getSelection().get(0).getValue() + "}");
		else
			template.setName("TEMPLATE_{Default}");
		fp.add(template);
		
		SimpleComboBox<String> policy = step_a.getUploadPolicyCombo();
		if (!policy.getSelection().isEmpty())
			policy.setName("POLICY_{" + policy.getSelection().get(0).getValue() + "}");
		else
			policy.setName("POLICY_{Default}");
		fp.add(policy);
	}
	
	@SuppressWarnings("unchecked")
	private static void addStepBFields(FormPanel fp, ExcelImporterPanel panel) {
		
		StepB step_b = panel.getStep_b();
		StepMU step_mu = panel.getStep_mu();
		
		List<HorizontalPanel> panels = step_b.getColumnPanelList();
		for (int i = 0 ; i < panels.size() ; i++) {
			
			TextField<String> columnNameField = (TextField<String>)panels.get(i).getData("COLUMN_NAME_FIELD");
			columnNameField.setName("COLUMN_NAME_" + i + "_{" + columnNameField.getValue() + "}");
			fp.add(columnNameField);
			
			ToolBar toolbar = (ToolBar)panels.get(i).getData("TOOLBAR");
			TextField<String> dataTypeField = new TextField<String>();
//			dataTypeField.setValue(((Button)toolbar.getWidget(0)).getText());
			dataTypeField.setValue(dataTypeMap.get(((Button)toolbar.getWidget(0)).getText()));
			dataTypeField.setName("DATA_TYPE_" + i + "_{" + dataTypeField.getValue() + "}");
			System.out.println("GhostFormController - 170 - " + dataTypeField.getName());
			fp.add(dataTypeField);
			
			String cs_name = panels.get(i).getData("CODING_SYSTEM_NAME");
			String cs_type = panels.get(i).getData("CODING_SYSTEM_TYPE");
			if ((cs_name != null) && (cs_type != null)) {
				
				TextField<String> csNameField = new TextField<String>();
				csNameField.setValue(cs_name);
				csNameField.setName("CODING_SYSTEM_NAME_" + i + "_{" + csNameField.getValue() + "}");
				fp.add(csNameField);
				
				TextField<String> csTypeField = new TextField<String>();
				csTypeField.setValue(cs_type);
				csTypeField.setName("CODING_SYSTEM_TYPE_" + i + "_{" + csTypeField.getValue() + "}");
				fp.add(csTypeField);
			}
			
		}
		
	}
	
	private static void addStepCFields(FormPanel fp, StepC step_c) {
		
		ComboBox<GaulModelData> sourceRegionCombo = step_c.getGaulList();
		if (!sourceRegionCombo.getSelection().isEmpty())
			sourceRegionCombo.setName("SOURCE_REGION_{" + sourceRegionCombo.getSelection().get(0).getGaulCode() + "}");
		else
			sourceRegionCombo.setName("SOURCE_REGION_{n.a.}");
		fp.add(sourceRegionCombo);
		
		SimpleComboBox<String> periodTypeCombo = step_c.getPeriodTypeCodeCombo();
		if (!periodTypeCombo.getSelection().isEmpty())
			periodTypeCombo.setName("PERIOD_TYPE_CODE_{" + periodTypeCombo.getSelection().get(0).getValue() + "}");
		else
			periodTypeCombo.setName("PERIOD_TYPE_CODE_{n.a.}");
		fp.add(periodTypeCombo);
		
		TextField<String> sourceNameCombo = step_c.getSourceField();
		sourceNameCombo.setName("SOURCE_NAME_{" + sourceNameCombo.getValue() + "}");
		fp.add(sourceNameCombo);
	}
	
	private static void addStepMFields(FormPanel fp, StepM step_m) {
		
		TextField<String> titleField = step_m.getTitleField();
		titleField.setName("TITLE_{" + titleField.getValue() + "}");
		fp.add(titleField);
		
		TextField<String> keywordsField = step_m.getKeywordsField();
		keywordsField.setName("KEYWORDS_{" + keywordsField.getValue() + "}");
		fp.add(keywordsField);
		
		TextField<String> codeField = step_m.getCodeField();
		codeField.setName("CODE_{" + codeField.getValue() + "}");
		fp.add(codeField);
		
		TextArea textArea = step_m.getTextArea();
		textArea.setName("ABSTRACT_{" + textArea.getValue() + "}");
		fp.add(textArea);
		
		ComboBox<GaulModelData> regionField = step_m.getGaulList();
		if (!regionField.getSelection().isEmpty())
			regionField.setName("REGION_{" + regionField.getSelection().get(0).getGaulCode() + "}");
		else
			regionField.setName("REGION_{n.a.}");
		fp.add(regionField);
		
		ComboBox<CategoryModelData> categoryField = step_m.getCategoryList();
		if (!categoryField.getSelection().isEmpty())
			categoryField.setName("CATEGORY_{" + categoryField.getSelection().get(0).getCategoryValue() + "}");
		else
			categoryField.setName("CATEGORY_{n.a.}");
		fp.add(categoryField);
	}
	
	private static void addStepDFields(FormPanel fp, StepD step_d) {
		if (!step_d.getGrid().getSelectionModel().getSelection().isEmpty()) {
			ResourceChildModel m = step_d.getGrid().getSelectionModel().getSelection().get(0);
			TextField<String> datasetIDField = new TextField<String>();
			datasetIDField.setValue(m.getId());
			datasetIDField.setName("DATASET_ID_{" + m.getId() + "}");
			fp.add(datasetIDField);
		}
	}
	
	private static void addStepMUFields(FormPanel fp, StepMU step_mu) {
		
		TextField<String> quantityHeaderField = step_mu.getQuantityHeader();
		quantityHeaderField.setName("QUANTITY_HEADER_{" + quantityHeaderField.getValue() + "}");
		fp.add(quantityHeaderField);
		
		TextField<String> muHeaderField = step_mu.getMuHeader();
		muHeaderField.setName("MU_HEADER_{" + muHeaderField.getValue() + "}");
		fp.add(muHeaderField);
		
		TextField<String> muValueField = step_mu.getMuField();
		muValueField.setName("MU_VALUE_{" + muValueField.getValue() + "}");
		fp.add(muValueField);
	}
	
	private static Listener<FormEvent> beforeSubmitListener() {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				loadingWindow = new LoadingWindow(BabelFish.print().uploadingDataset(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
			}
		};
	}
	
	@SuppressWarnings("deprecation")
	public static Listener<FormEvent> afterSubmitFormListener(final ExcelImporterPanel panel, final ConfirmAppendWindow w) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				w.getWindow().hide();
				if (loadingWindow != null)
					loadingWindow.destroyLoadingBox();
				String results = be.getResultHtml();
				if (results.contains("datasetID")) {
					int startIDX = 1 + results.indexOf(":");
					int endIDX = results.indexOf("</pre>");
					Long datasetID = Long.valueOf(results.substring(startIDX, endIDX));
					StepE step_e = panel.getStep_e();
					step_e.getTable().addClickHandler(ExcelImporterController.openAsTable(datasetID, "Dataset"));
					step_e.getChart().addClickHandler(ExcelImporterController.openAsChart(datasetID));
					step_e.getOlap().addClickHandler(ExcelImporterController.openAsOLAP(datasetID));
					step_e.getCsv().addClickHandler(ExcelImporterController.exportCSV(datasetID));
					step_e.getXml().addClickHandler(ExcelImporterController.exportXML(datasetID));
					step_e.getZip().addClickHandler(ExcelImporterController.exportZIP(datasetID));
					panel.getLayout().setActiveItem(step_e.getLayoutContainer());
					panel.getNextButton().setEnabled(false);
					panel.getPrevButton().setEnabled(false);
					w.getWindow().hide();
				} else {
					panel.getLayout().setActiveItem(panel.getStepServiceMessage().getLayoutContainer());
					panel.getNextButton().setEnabled(false);
					panel.getPrevButton().setEnabled(false);
					panel.getStepServiceMessage().setMessage(results, "RED");
				}
				w.getWindow().hide();
			}
		};
	}
	
}
