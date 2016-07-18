package org.fao.fenix.web.modules.map.client.view.sldeditor;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

/**
 * 
 * @author $Author: Tobia Di Pisa (tobia.dipisa@geo-solutions.it)
 *
 */

public class OperationManager {

	private ComboBox<ComboValue> combo;
	private Styler styler;
	
	public OperationManager(Styler styler){
		this.styler = styler;
//		buildForm();
	}
	
	public OperationFormPanel buildForm(){
		OperationFormPanel form = new OperationFormPanel();
		form.setId("opManagerForm");
		form.setFrame(true);
		form.setLabelWidth(50);
		form.setHeaderVisible(false);
		form.setAutoHeight(true);
		form.setAutoWidth(true);
		
		List<ComboValue> comboList = new ArrayList<ComboValue>();
		comboList.add(new ComboValue("UniqueValue", "UniqueValue"));
		comboList.add(new ComboValue("Classification", "Classification"));
		comboList.add(new ComboValue("Symbol", "Symbol"));		
	  	 
		ListStore<ComboValue> comboStore = new ListStore<ComboValue>();
		comboStore.add(comboList);		
		
		combo = new ComboBox<ComboValue>();	
		combo.setTriggerAction(TriggerAction.ALL);
		combo.setDisplayField("property");
		combo.setFieldLabel("value");		
		combo.setEditable(false);
		combo.setStore(comboStore);	
		combo.setValue(comboStore.getAt(0));
		form.add(combo);

		Button visBtn = new Button("Visualize", new VisualizeListener());
		form.addButton(visBtn);
		form.setVisualizeButton(visBtn);
	
		return form;
	}

	public class VisualizeListener extends SelectionListener<ButtonEvent> {
		@Override
		public void componentSelected(ButtonEvent ce) {
			if(styler.getClassifier() != null &&
					combo.getValue().getProperty().equalsIgnoreCase("Classification")){

				if(styler.getUniqueValueClassifier().getUniqueValueForm().isVisible()){
					styler.getUniqueValueClassifier().getUniqueValueForm().setVisible(false);
				}else if(styler.getSymbolOperation().getSymbolForm().isVisible()){
					styler.getSymbolOperation().getSymbolForm().setVisible(false);
				}

				styler.hideSymbolizerFormPanel();

				styler.getApplyButton().disable();
//				OperationFormPanel ofm = (OperationFormPanel)styler.getSymbolizersWindow().getItemByItemId("opManagerForm");
//				ofm.getVisualizeButton().disable();
				styler.getClassifier().getClassifyForm().setVisible(true);

			}else if(styler.getClassifier() != null &&
					combo.getValue().getProperty().equalsIgnoreCase("UniqueValue")){

				if(styler.getClassifier().getClassifyForm().isVisible()){
					styler.getClassifier().getClassifyForm().setVisible(false);
				}else if(styler.getSymbolOperation().getSymbolForm().isVisible()){
					styler.getSymbolOperation().getSymbolForm().setVisible(false);
				}

				styler.hideSymbolizerFormPanel();
				styler.getApplyButton().disable();
//				OperationFormPanel ofm = (OperationFormPanel)styler.getSymbolizersWindow().getItemByItemId("opManagerForm");
//				ofm.getVisualizeButton().disable();
				styler.getUniqueValueClassifier().getUniqueValueForm().setVisible(true);

			}else if(styler.getClassifier() != null &&
					combo.getValue().getProperty().equalsIgnoreCase("Symbol")){

				if(styler.getClassifier().getClassifyForm().isVisible()){
					styler.getClassifier().getClassifyForm().setVisible(false);
				}else if(styler.getUniqueValueClassifier().getUniqueValueForm().isVisible()){
					styler.getUniqueValueClassifier().getUniqueValueForm().setVisible(false);
				}

				styler.hideSymbolizerFormPanel();
				styler.getApplyButton().disable();
//				OperationFormPanel ofm = (OperationFormPanel)styler.getSymbolizersWindow().getItemByItemId("opManagerForm");
//				ofm.getVisualizeButton().disable();
				styler.getSymbolOperation().getSymbolForm().setVisible(true);
			}
		}
	}

	public class ComboValue extends BaseModelData {

		  private static final long serialVersionUID = 8114333007161030810L;

		  public ComboValue() {
		  }

		  public ComboValue(String property, String value) {
		    setProperty(property);
		    setValue(value);
		  }
		  
		  public String getProperty() {
		    return get("property");
		  }

		  public void setProperty(String property) {
		    set("property", property);
		  }

		  public String getValue() {
		    return get("value");
		  }

		  public void setValue(String value) {
		    set("value", value);
		  }
	}
}

class OperationFormPanel extends FormPanel {

	private Button visualizeButton;

	public Button getVisualizeButton() {
		return visualizeButton;
	}

	public void setVisualizeButton(Button visualizeButton) {
		this.visualizeButton = visualizeButton;
	}

}
