package org.fao.fenix.web.modules.venn.client.view;


import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.shared.window.client.view.FenixToolBase;
import org.fao.fenix.web.modules.venn.client.control.VennController;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

public class VennTypeChooseWindow extends FenixToolBase {
	
	Dialog complex;
	
	ContentPanel panel;
	
	ComboBox<ListItem> countryComboBox;
	
	ListStore<ListItem> countryStore;
	
	RadioButton country = new RadioButton("selection");
	RadioButton multiCountry = new RadioButton("selection");
	RadioButton generic = new RadioButton("selection");

	HorizontalPanel chooseCountry;
	
	Html label = new Html("<b>Choose the country:</b>");
	
	Integer comboWidth = 250;
	
	public VennTypeChooseWindow() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		complex = new Dialog();  
		panel.add(complex);
		countryComboBox = new ComboBox<ListItem>();
		countryStore = new ListStore<ListItem>(); 
		chooseCountry = new HorizontalPanel();
//		chooseCountry.setHeaderVisible(false);
//		chooseCountry.setBodyBorder(false);

	}
	
	public void build() {
		 complex.setBodyBorder(false);  
		 complex.setHeading("Selection type");  
		 complex.setWidth(400);  
		 complex.setHeight(225);  
		 complex.setHideOnButtonClick(true); 

		 ClickListener t = new ClickListener() {  
				public void onClick(Widget sender) {
					RadioButton radio = (RadioButton) sender;
					if ( radio.getFormValue().equals("c")) {
						label.show();
						countryComboBox.show();
						panel.layout();
					}
					else {
						label.hide();
						countryComboBox.hide();
						panel.layout();
					}
				}
			};  
		 
		 country.setHTML(BabelFish.print().country());
		 country.setValue(true);
		 country.setFormValue("c");
		 country.addClickListener(t);
		 multiCountry.setHTML("Multi Countries");
		 multiCountry.setValue(false);
		 multiCountry.setFormValue("m");
		 multiCountry.addClickListener(t);
		 generic.setHTML("Generic");
		 generic.setValue(false);
		 generic.setFormValue("g");
		 generic.addClickListener(t);
		 complex.add(buildCenterPanel());
		 complex.show();
		 
		 
	}
	
	private ContentPanel buildCenterPanel() {
		
		VerticalPanel v = new VerticalPanel();
		v.setSpacing(10);
		v.add(country);
		v.add(multiCountry);
		v.add(generic);
		v.add(buildChooseCountryPanel());
		panel.add(v);
		return panel;
	}
	
	private HorizontalPanel buildChooseCountryPanel() {

		chooseCountry.setSpacing(10);
		chooseCountry.add(label);
		chooseCountry.add(buildCountryComboBox());
		return chooseCountry;
	}
	
	public ComboBox<ListItem> buildCountryComboBox() {
		countryComboBox = new ComboBox<ListItem>();
		countryComboBox.setTriggerAction(TriggerAction.ALL);
		countryStore = new ListStore<ListItem>();
		countryComboBox.setStore(countryStore);
		countryComboBox.setFieldLabel("<b>Administrative Level of Analysis: <b>");
		countryComboBox.setEditable(false);
		countryComboBox.setTypeAhead(true);
		countryComboBox.setDisplayField("name");
		countryComboBox.setEmptyText(BabelFish.print().pleaseSelect());
		countryComboBox.setAllowBlank(false);
//		countryComboBox.addSelectionChangedListener(fillDescriptions(this));
		VennController.fillCountriesListStore(countryComboBox, countryStore);
		return countryComboBox;
	}
	

	


	public Dialog getComplex() {
		return complex;
	}

	public void setComplex(Dialog complex) {
		this.complex = complex;
	}

	public ContentPanel getPanel() {
		return panel;
	}

	public void setPanel(ContentPanel panel) {
		this.panel = panel;
	}
		
	
	

}