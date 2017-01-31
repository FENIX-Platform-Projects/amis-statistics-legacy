package org.fao.fenix.web.modules.excelimporter.client.control;

import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.excelimporter.client.view.ConfirmAppendWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.ImageImporterPanel;
import org.fao.fenix.web.modules.excelimporter.client.view.ImageImporterStepA;
import org.fao.fenix.web.modules.excelimporter.client.view.ImageImporterStepB;
import org.fao.fenix.web.modules.excelimporter.client.view.StepM;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.CategoryModelData;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.tinymcereport.client.control.TinyMCEController;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.google.gwt.core.client.GWT;

public class GhostImageImporterController {

	private static LoadingWindow loadingWindow = null;

	public static SelectionListener<ButtonEvent> confirmAppend(final ImageImporterPanel p, final ConfirmAppendWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createGhostForm(p, w);
			}
		};
	}

	private static FormPanel createGhostForm(final ImageImporterPanel p, final ConfirmAppendWindow w) {

		FormPanel fp = new FormPanel();

		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String imageUploaderServletURL = ep.getImageUpload(GWT.getModuleBaseURL(), GWT.getModuleName());

		addStepAFields(fp, p.getStep_a());
		addStepBFields(fp, p.getStep_b());
		addStepMFields(fp, p.getStep_m());

		w.getCenter().remove(w.getCenter().getWidget(0));
		w.getCenter().add(fp);
		w.getCenter().getLayout().layout();

		fp.setAction(imageUploaderServletURL);
		fp.setEncoding(Encoding.MULTIPART);
		fp.setMethod(Method.POST);
		fp.addListener(Events.BeforeSubmit, beforeSubmitListener());
		fp.addListener(Events.Submit, afterSubmitFormListener(p, w));
		fp.submit();

		return fp;
	}

	private static void addStepAFields(FormPanel fp, ImageImporterStepA step_a) {
		FileUploadField image = step_a.getDataFile();
		image.setName("IMAGE");
		fp.add(image);
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
	
	private static void addStepBFields(FormPanel fp, ImageImporterStepB step_b) {
		
		ComboBox<GaulModelData> sourceRegionCombo = step_b.getGaulList();
		if (!sourceRegionCombo.getSelection().isEmpty())
			sourceRegionCombo.setName("SOURCE_REGION_{" + sourceRegionCombo.getSelection().get(0).getGaulCode() + "}");
		else
			sourceRegionCombo.setName("SOURCE_REGION_{n.a.}");
		fp.add(sourceRegionCombo);
		
		TextField<String> sourceNameCombo = step_b.getSourceField();
		sourceNameCombo.setName("SOURCE_NAME_{" + sourceNameCombo.getValue() + "}");
		fp.add(sourceNameCombo);
	}
	
	private static Listener<FormEvent> beforeSubmitListener() {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				loadingWindow = new LoadingWindow(BabelFish.print().codingSystem(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
			}
		};
	}
	
	public static Listener<FormEvent> afterSubmitFormListener(final ImageImporterPanel p, final ConfirmAppendWindow w) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				w.getWindow().hide();
				if (loadingWindow != null)
					loadingWindow.destroyLoadingBox();
				String results = be.getResultHtml();
				if (results.contains("imageID")) {
					p.getLayout().setActiveItem(p.getStep_c().getLayoutContainer());
					p.getPrevButton().setEnabled(false);
					p.getNextButton().setEnabled(false);
					int startIDX = 1 + results.indexOf(":");
					int endIDX = results.indexOf("</pre>");
					Long imageID = Long.valueOf(results.substring(startIDX, endIDX));
					p.getStep_c().getImage().addClickHandler(ImageImporterController.openImages(imageID));
					p.getStep_c().getAddToReport().addClickHandler(TinyMCEController.addImage(p, imageID));
				}
				w.getWindow().hide();
			}
		};
	}

}
