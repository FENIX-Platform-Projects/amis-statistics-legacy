package org.fao.fenix.web.modules.core.client.uploader.control;

import org.fao.fenix.web.modules.core.client.uploader.view.UploaderWindow;
import org.fao.fenix.web.modules.excelimporter.client.view.StepA;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class UploaderController {

	
	public static ChangeListener getPolicyListener(final ListBox list) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				list.setName(list.getItemText(list.getSelectedIndex()));
			}
		};
	}
	
	public static Listener<BaseEvent> getComboBoxListener(final SimpleComboBox<String> combo) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				 combo.setName(combo.getStore().getAt(combo.getSelectedIndex()).getValue());
				 System.out.println("COMBO NAME: " + combo.getName());
			}
		};
	}
	
	public static Listener<BaseEvent> getUploadPolicyListener(final SimpleComboBox<String> combo) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				 combo.setName("POLICY_{" + combo.getStore().getAt(combo.getSelectedIndex()).getValue() + "}");
			}
		};
	}
	
	public static Listener<BaseEvent> getDelimiterListener(final SimpleComboBox<String> combo) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				 combo.setName("DELIMITER_{" + combo.getStore().getAt(combo.getSelectedIndex()).getValue() + "}");
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
				} else {
					step_a.getMuValuePanel().setVisible(false);
				}
			}
		};
	}
	
	public static Listener<BaseEvent> getResourceTypeListener(final SimpleComboBox<String> combo, final FormPanel form) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				 combo.setName(combo.getStore().getAt(combo.getSelectedIndex()).getValue());
				 if(combo.getStore().getAt(combo.getSelectedIndex()).getValue().equals(BabelFish.print().codingSystem())){
					 //do something
				 }
				   
			}
		};
	}
	
	public static ChangeListener getDelimiterListener(final ListBox list) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				list.setName(list.getItemText(list.getSelectedIndex()));
			}
		};
	}
	
	
	public static ChangeListener getResourceTypeListener(final ListBox list) {
		return new ChangeListener() {
			public void onChange(Widget widget) {
				list.setName(list.getItemText(list.getSelectedIndex()));
			}
		};
	}
	

	public static SelectionListener<ButtonEvent> getCancelListener(final UploaderWindow window) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				window.getWindow().close();
			}
		};
	}

}
