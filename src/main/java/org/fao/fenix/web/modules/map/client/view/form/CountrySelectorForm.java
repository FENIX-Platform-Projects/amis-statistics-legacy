/*
 */

package org.fao.fenix.web.modules.map.client.view.form;

import java.util.List;

import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.map.client.control.vo.PDDModel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author ETj
 */
public class CountrySelectorForm {

	private Window window;

	private List<CodeVo> codeVoList;
	private AsyncCallback<CodeVo> gaulCodeReceptor;

	public CountrySelectorForm(List<CodeVo> codeVoList, AsyncCallback<CodeVo> gaulCodeReceptor) {
		this.codeVoList = codeVoList;
		this.gaulCodeReceptor = gaulCodeReceptor;
	}


	ComboBox<PDDModel> combo;

	public void run() {
		window = new Window();
		window.setSize(400, 80);
		window.setPlain(true);
		window.setHeading("Country selector");
		window.setLayout(new FitLayout());

		FormPanel panel = createForm();
		window.add(panel, new FitData(4));

		window.setModal(true);
		window.show();
	}

	private FormPanel createForm() {
		FormPanel form = new FormPanel();
		form.setFrame(false);
		form.setHeaderVisible(false);

		combo = new ComboBox<PDDModel>();
		combo.setTriggerAction(TriggerAction.ALL);
		form.add(combo);

		ListStore<PDDModel> store = new ListStore<PDDModel>();
		for (CodeVo codeVo : codeVoList) {
			store.add(new PDDModel(codeVo.getCode(), codeVo.getLabel()));
		}

		combo.setStore(store);
		combo.setAllowBlank(false);
		combo.setForceSelection(true);
		combo.setDisplayField("label");
		combo.setAutoHeight(true);
		combo.setAutoWidth(true);
		combo.setEditable(true);
		combo.setEmptyText("Please select a Country");
		combo.setFieldLabel("Country");

		form.setButtonAlign(HorizontalAlignment.CENTER);

		Button okButton = new Button("Zoom");
		okButton.addSelectionListener(
			new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent e) {
					String id = combo.getSelection().get(0).getName();
					for (CodeVo codeVo : codeVoList) {
						if(codeVo.getCode().equals(id)) {
							window.close();
							gaulCodeReceptor.onSuccess(codeVo);
						}
					}
				}
			});
		form.addButton(okButton);

		Button cancelButton = new Button("Cancel");
		cancelButton.addSelectionListener(
			new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					Info.display("Action canceled", "Country selection canceled");
					window.close();
				}
			});

		form.addButton(cancelButton);

		return form;
	}

}


