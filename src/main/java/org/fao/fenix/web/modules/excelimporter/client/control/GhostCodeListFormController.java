/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.excelimporter.client.control;

import org.fao.fenix.web.modules.coding.client.view.search.CodingSearchResults;
import org.fao.fenix.web.modules.core.client.utils.FenixServiceEntryPoint;
import org.fao.fenix.web.modules.excelimporter.client.view.CodeListPanel;
import org.fao.fenix.web.modules.excelimporter.client.view.CodeListStepA;
import org.fao.fenix.web.modules.excelimporter.client.view.CodeListStepB;
import org.fao.fenix.web.modules.excelimporter.client.view.ConfirmAppendWindow;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.google.gwt.core.client.GWT;

public class GhostCodeListFormController {
	
	private static LoadingWindow loadingWindow = null;

	public static SelectionListener<ButtonEvent> confirmAppend(final CodeListPanel panel, final ConfirmAppendWindow w) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createGhostForm(panel, w);
			}
		};
	}
	
	private static FormPanel createGhostForm(final CodeListPanel panel, final ConfirmAppendWindow w) {
		
		FormPanel fp = new FormPanel();
		
		FenixServiceEntryPoint ep = new FenixServiceEntryPoint();
		String codeListUploaderServletURL = ep.getCodeListUploaderServletEntryPoint(GWT.getModuleBaseURL(), GWT.getModuleName());
		
		addStepAFields(fp, panel.getStep_a());
		addStepBFields(fp, panel.getStep_b());
		
		w.getCenter().remove(w.getCenter().getWidget(0));
		w.getCenter().add(fp);
		w.getCenter().getLayout().layout();
		
		fp.setAction(codeListUploaderServletURL);
		fp.setEncoding(Encoding.MULTIPART);
		fp.setMethod(Method.POST);
		fp.addListener(Events.BeforeSubmit, beforeSubmitListener());
		fp.addListener(Events.Submit, afterSubmitFormListener(panel, w));
		fp.submit();
		
		return fp;
	}
	
	private static void addStepAFields(FormPanel fp, CodeListStepA step_a) {
		
		FileUploadField dataset = step_a.getDataFile();
		dataset.setName("CODING_SYSTEM");
		fp.add(dataset);
		
		SimpleComboBox<String> delimiter = step_a.getDelimiterCombo();
		if (!delimiter.getSelection().isEmpty())
			delimiter.setName("DELIMITER_{" + delimiter.getSelection().get(0).getValue() + "}");
		else
			delimiter.setName("DELIMITER_{,}");
		fp.add(delimiter);
		
		SimpleComboBox<String> policy = step_a.getUploadPolicyCombo();
		if (!policy.getSelection().isEmpty())
			policy.setName("POLICY_{" + policy.getSelection().get(0).getValue() + "}");
		else
			policy.setName("POLICY_{Default}");
		fp.add(policy);
	}
	
	private static void addStepBFields(FormPanel fp, CodeListStepB step_b) {
		
		TextField<String> sourceField = step_b.getSourceField();
		sourceField.setName("SOURCE_NAME_{" + sourceField.getValue() + "}");
		fp.add(sourceField);
		
		ComboBox<GaulModelData> sourceRegionCombo = step_b.getGaulList();
		if (!sourceRegionCombo.getSelection().isEmpty())
			sourceRegionCombo.setName("SOURCE_REGION_{" + sourceRegionCombo.getSelection().get(0).getGaulCode() + "}");
		else
			sourceRegionCombo.setName("SOURCE_REGION_{n.a.}");
		fp.add(sourceRegionCombo);
		
		SimpleComboBox<String> codeListType = step_b.getCodingType();
		if (!codeListType.getSelection().isEmpty())
			codeListType.setName("CODING_SYSTEM_TYPE_{" + codeListType.getSelection().get(0).getValue() + "}");
		else
			codeListType.setName("CODING_SYSTEM_TYPE_{,}");
		fp.add(codeListType);
		
		TextField<String> titleField = step_b.getTitleField();
		titleField.setName("CODING_SYSTEM_NAME_{" + titleField.getValue() + "}");
		fp.add(titleField);
		
		TextArea textArea = step_b.getTextArea();
		textArea.setName("ABSTRACT_{" + textArea.getValue() + "}");
		fp.add(textArea);
	}
	
	private static Listener<FormEvent> beforeSubmitListener() {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				loadingWindow = new LoadingWindow(BabelFish.print().codingSystem(), BabelFish.print().pleaseWait(), BabelFish.print().loading());
				loadingWindow.showLoadingBox();
			}
		};
	}
	
	public static Listener<FormEvent> afterSubmitFormListener(final CodeListPanel panel, final ConfirmAppendWindow w) {
		return new Listener<FormEvent>() {
			public void handleEvent(FormEvent be) {
				w.getWindow().hide();
				if (loadingWindow != null)
					loadingWindow.destroyLoadingBox();
				String results = be.getResultHtml();
				panel.getLayout().setActiveItem(panel.getStepServiceMessage().getLayoutContainer());
				if (results.contains("codeListID")) {
					int startIDX = 1 + results.indexOf(":");
					int endIDX = results.indexOf("</pre>");
					Long codeListID = Long.valueOf(results.substring(startIDX, endIDX));
					w.getWindow().hide();
					panel.getStepServiceMessage().setMessage(BabelFish.print().codeListSuccess(), "GREEN");
					new CodingSearchResults().build(codeListID);
				} else {
					panel.getStepServiceMessage().setMessage(results, "RED");
				}
				w.getWindow().hide();
			}
		};
	}

}