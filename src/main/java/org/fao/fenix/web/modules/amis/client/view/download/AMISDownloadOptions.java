package org.fao.fenix.web.modules.amis.client.view.download;

import org.fao.fenix.web.modules.amis.client.view.utils.layout.FormattingUtils;
import org.fao.fenix.web.modules.amis.common.model.AMISCodesModelData;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;


public class AMISDownloadOptions {
	
	
	ContentPanel panel;
	
	ContentPanel contentPanel;

	CheckBox showFlags;
	
	CheckBox showCodes;
	
	CheckBox showUnits;
	
	CheckBox showNull;
	
	// thousand separator
	ListStore<AMISCodesModelData> thousandStore;
	ComboBox<AMISCodesModelData> thousandCombo;
	
	// decimal separator
	ListStore<AMISCodesModelData> decimalStore;
	ComboBox<AMISCodesModelData> decimalCombo;
	
	// Decimals
	ListStore<AMISCodesModelData> decimalValuesStore;
	ComboBox<AMISCodesModelData> decimalValuesCombo;
	
	Integer listTitleWidth = 120; 
	Integer comboWidth = 90;

	public AMISDownloadOptions() {
		
	}
	
	
	public ContentPanel build() {
		panel = new ContentPanel();
		panel.setBodyBorder(false);
		panel.setHeaderVisible(false);
		
		contentPanel = new ContentPanel();
		contentPanel.setBodyBorder(false);
		contentPanel.setHeaderVisible(false);
		
		FieldSet optionsFieldSet = buildDownloadOptions();

		
		panel.add(optionsFieldSet);
		
	
		return panel;
	} 
	
	private FieldSet buildDownloadOptions() {
		FieldSet panel = new FieldSet();
		contentPanel = new ContentPanel();
		contentPanel.setBodyBorder(false);
		contentPanel.setHeaderVisible(false);
		
		panel.setCollapsible(true);
		
		panel.setHeading("Download Options");
		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		
		VerticalPanel v1 = new VerticalPanel();
//		v1.add(addShowFlag());
//		v1.add(FormattingUtils.addVSpace(5));
		v1.add(addShowCodes());
		v1.add(FormattingUtils.addVSpace(5));
		v1.add(addShowUnits());
		//v1.add(FormattingUtils.addVSpace(5));
		//v1.add(addShowNull());
		
		VerticalPanel v2 = new VerticalPanel();
		v2.add(addThousandSeparator());
		v2.add(FormattingUtils.addVSpace(5));
		v2.add(addDecimalSeparator());
		v2.add(FormattingUtils.addVSpace(5));
		v2.add(addDecimalsValuesSeparator());
		
		h.add(v1);
		//h.add(v2);
		
		contentPanel.add(h);
		panel.add(contentPanel);
		
//		contentPanel.disable();
		return panel;
	}
	
	private HorizontalPanel addThousandSeparator() {	
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		thousandStore = new ListStore<AMISCodesModelData>();
		thousandCombo = new ComboBox<AMISCodesModelData>();
		thousandCombo.setStore(thousandStore);	
		thousandCombo.setWidth(comboWidth);

		thousandCombo.setDisplayField("label");
		thousandCombo.setEmptyText("Please make a selection");
		thousandCombo.setTriggerAction(TriggerAction.ALL);
		thousandCombo.setTemplate(getTemplate());  
		setSeparatorValues(thousandStore);
		
		thousandCombo.setValue(thousandStore.getAt(0));

		
		p.add(FormattingUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip("Thousand separator");
//		p.add(icon);

		p.add(FormattingUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> Thousand Separator </div>");
		html.setWidth(listTitleWidth);
		
		html.setToolTip("Thousand separator");
		
		p.add(html);
		
		p.add(FormattingUtils.addHSpace(5));
		
		p.add(thousandCombo);
		
		return p;
	}
	
	private HorizontalPanel addDecimalSeparator() {	
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		decimalStore = new ListStore<AMISCodesModelData>();
		decimalCombo = new ComboBox<AMISCodesModelData>();
		decimalCombo.setStore(decimalStore);
		decimalCombo.setWidth(comboWidth);

		decimalCombo.setDisplayField("label");
		decimalCombo.setEmptyText("Please make a selection");
		decimalCombo.setTriggerAction(TriggerAction.ALL);
		decimalCombo.setTemplate(getTemplate());  
		setSeparatorValues(decimalStore);
		
		decimalCombo.setValue(decimalStore.getAt(2));
		

		
		p.add(FormattingUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip("");
//		p.add(icon);

		p.add(FormattingUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> Decimal Separator </div>");
		html.setWidth(listTitleWidth);
		
		
		html.setToolTip("");
		
		p.add(html);
		
		p.add(FormattingUtils.addHSpace(5));
		
		p.add(decimalCombo);
		
		return p;
	}
	
	private HorizontalPanel addDecimalsValuesSeparator() {	
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		decimalValuesStore = new ListStore<AMISCodesModelData>();
		decimalValuesCombo = new ComboBox<AMISCodesModelData>();
		decimalValuesCombo.setStore(decimalValuesStore);
		decimalValuesCombo.setWidth(comboWidth);

		decimalValuesCombo.setDisplayField("label");
		decimalValuesCombo.setEmptyText("Please make a selection");
		decimalValuesCombo.setTriggerAction(TriggerAction.ALL);
		decimalValuesCombo.setTemplate(getTemplate());  
		setDecimalValues(decimalValuesStore);
		
		decimalValuesCombo.setValue(decimalValuesStore.getAt(3));
		

		
		p.add(FormattingUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip("");
//		p.add(icon);

		p.add(FormattingUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> Decimal Values </div>");
		html.setWidth(listTitleWidth);
		
	
		html.setToolTip("");
		
		p.add(html);
		
		p.add(FormattingUtils.addHSpace(5));
		
		p.add(decimalValuesCombo);
		
		return p;
	}
	
	private void setDecimalValues(ListStore<AMISCodesModelData> store) {
		for (Integer i = 0; i < 4; i++ ) {
			store.add(new AMISCodesModelData(i.toString(), i.toString()));
		}
	}

	
	private void setSeparatorValues(ListStore<AMISCodesModelData> store) {
		store.add(new AMISCodesModelData(" ", "None"));
		store.add(new AMISCodesModelData(",", "Comma"));
		store.add(new AMISCodesModelData(".", "Period"));
		store.add(new AMISCodesModelData(" ", "Space"));
	}
	
	private HorizontalPanel addShowFlag() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		showFlags = new CheckBox();
		showFlags.setValue(true);
		
		p.add(showFlags);
		
		p.add(FormattingUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip("Add flags to the export file");
		p.add(icon);
		

		p.add(FormattingUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> Show Flags </div>");
		
		html.setToolTip("Add flags to the table");
		
		p.add(html);
		
		return p;
	}
	
	private HorizontalPanel addShowCodes() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		showCodes = new CheckBox();
		showCodes.setValue(false);
		
		p.add(showCodes);
		
		p.add(FormattingUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip("Add codes to the export file");
		p.add(icon);
		
		p.add(FormattingUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> Show Codes </div>");
		
		html.setToolTip("Add codes to the table");
		
		p.add(html);
		
		return p;
	}
	
	private HorizontalPanel addShowUnits() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		showUnits = new CheckBox();
		showUnits.setValue(true);
		
		p.add(showUnits);
		
		p.add(FormattingUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip("Add measuremant units to the table");
		p.add(icon);
		
		p.add(FormattingUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> Show Units </div>");
		
		html.setToolTip("Add codes to the export file");
		
		p.add(html);
		
		return p;
	}
	
	private HorizontalPanel addShowNull() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		showNull = new CheckBox();
		showNull.setValue(true);
		
		p.add(showNull);
		
		p.add(FormattingUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip("");
		p.add(icon);
		
		p.add(FormattingUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> Show Null Values </div>");
		
		html.setToolTip("");
		
		p.add(html);
		
		return p;
	}


	public CheckBox getShowFlags() {
		return showFlags;
	}


	public CheckBox getShowCodes() {
		return showCodes;
	}


	public CheckBox getShowUnits() {
		return showUnits;
	}
	
	
	
	public CheckBox getShowNull() {
		return showNull;
	}


	public ComboBox<AMISCodesModelData> getThousandCombo() {
		return thousandCombo;
	}


	public ComboBox<AMISCodesModelData> getDecimalCombo() {
		return decimalCombo;
	}
	
	


	public ComboBox<AMISCodesModelData> getDecimalValuesCombo() {
		return decimalValuesCombo;
	}


	public native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-combo-list-item" qtip="{label}" qtitle="">{label}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;


	public ContentPanel getContentPanel() {
		return contentPanel;
	}


	
	
	
	
}
