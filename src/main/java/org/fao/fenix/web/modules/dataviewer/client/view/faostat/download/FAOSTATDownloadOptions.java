package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download;

import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.ListView;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.google.gwt.user.client.ui.ListBox;



public class FAOSTATDownloadOptions {
	
	
	ContentPanel panel;
	
	ContentPanel contentPanel;

	CheckBox showFlags;
	
	CheckBox showCodes;
	
	CheckBox showUnits;
	
	CheckBox showNull;
	
	HorizontalPanel showNullPanel;
	
	// thousand separator
	HorizontalPanel thousandPanel;
	ListStore<DWCodesModelData> thousandStore;
	ComboBox<DWCodesModelData> thousandCombo;
	
	// decimal separator
	HorizontalPanel decimalPanel;
	ListStore<DWCodesModelData> decimalStore;
	ComboBox<DWCodesModelData> decimalCombo;
	
	// Decimals
	HorizontalPanel decimalValuesPanel;
	ListStore<DWCodesModelData> decimalValuesStore;
	ComboBox<DWCodesModelData> decimalValuesCombo;
	
	Integer listTitleWidth = 70; 
	Integer comboWidth = 70;

	public FAOSTATDownloadOptions() {
		
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
		
		panel.setHeading(FAOSTATLanguage.print().downloadOptions());
		
		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		
		VerticalPanel v1 = new VerticalPanel();
		v1.add(addShowFlag());
		v1.add(DataViewerClientUtils.addVSpace(5));
		v1.add(addShowCodes());
		v1.add(DataViewerClientUtils.addVSpace(5));
		v1.add(addShowUnits());
		v1.add(DataViewerClientUtils.addVSpace(5));
		v1.add(addShowNull());
		
		v1.add(addThousandSeparator());
		v1.add(DataViewerClientUtils.addVSpace(5));
		v1.add(addDecimalSeparator());
		v1.add(DataViewerClientUtils.addVSpace(5));
		v1.add(addDecimalsValuesSeparator());
		
		h.add(v1);

		
		contentPanel.add(h);
		panel.add(contentPanel);
		
		return panel;
}
	
	// OLD
//	private FieldSet buildDownloadOptions() {
//		FieldSet panel = new FieldSet();
//		contentPanel = new ContentPanel();
//		contentPanel.setBodyBorder(false);
//		contentPanel.setHeaderVisible(false);
//		
//		panel.setCollapsible(true);
//		
//		panel.setHeading(FAOSTATLanguage.print().downloadOptions());
//		
//		HorizontalPanel h = new HorizontalPanel();
//		h.setSpacing(5);
//		
//		VerticalPanel v1 = new VerticalPanel();
//		v1.add(addShowFlag());
//		v1.add(DataViewerClientUtils.addVSpace(5));
//		v1.add(addShowCodes());
//		v1.add(DataViewerClientUtils.addVSpace(5));
//		v1.add(addShowUnits());
//		v1.add(DataViewerClientUtils.addVSpace(5));
//		v1.add(addShowNull());
//		
//		VerticalPanel v2 = new VerticalPanel();
//		v2.add(addThousandSeparator());
//		v2.add(DataViewerClientUtils.addVSpace(5));
//		v2.add(addDecimalSeparator());
//		v2.add(DataViewerClientUtils.addVSpace(5));
//		v2.add(addDecimalsValuesSeparator());
//		
//		h.add(v1);
//		h.add(v2);
//		
//		contentPanel.add(h);
//		panel.add(contentPanel);
//		
//		return panel;
//	}
	
	private HorizontalPanel addThousandSeparator() {	
		thousandPanel = new HorizontalPanel();
		thousandPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		thousandStore = new ListStore<DWCodesModelData>();
		thousandCombo = new ComboBox<DWCodesModelData>();
		thousandCombo.setStore(thousandStore);	
		thousandCombo.setWidth(comboWidth);

		thousandCombo.setDisplayField("label");
		thousandCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		thousandCombo.setTriggerAction(TriggerAction.ALL);
		thousandCombo.setTemplate(getTemplate());  
		setSeparatorValues(thousandStore);
		
		thousandCombo.setValue(thousandStore.getAt(1));

		
//		p.add(DataViewerClientUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip(FAOSTATLanguage.print().thousandSeparator());
//		p.add(icon);

//		p.add(DataViewerClientUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> "+FAOSTATLanguage.print().thousandSeparator()+" </div>");
		html.setWidth(listTitleWidth);
		
		html.setToolTip("");
		
		thousandPanel.add(html);
		
		thousandPanel.add(DataViewerClientUtils.addHSpace(5));
		
		thousandPanel.add(thousandCombo);
		
		return thousandPanel;
	}
	
	private HorizontalPanel addDecimalSeparator() {	
		decimalPanel = new HorizontalPanel();
		decimalPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		decimalStore = new ListStore<DWCodesModelData>();
		decimalCombo = new ComboBox<DWCodesModelData>();
		decimalCombo.setStore(decimalStore);
		decimalCombo.setWidth(comboWidth);

		decimalCombo.setDisplayField("label");
		decimalCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		decimalCombo.setTriggerAction(TriggerAction.ALL);
		decimalCombo.setTemplate(getTemplate());  
		setSeparatorValues(decimalStore);

		decimalCombo.setValue(decimalStore.getAt(2));
		

		
//		p.add(DataViewerClientUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip("");
//		p.add(icon);

//		p.add(DataViewerClientUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> "+FAOSTATLanguage.print().decimalSeparator()+" </div>");
		html.setWidth(listTitleWidth);
		
		
		html.setToolTip("");
		
		decimalPanel.add(html);
		
		decimalPanel.add(DataViewerClientUtils.addHSpace(5));
		
		decimalPanel.add(decimalCombo);
		
		return decimalPanel;
	}
	
	private HorizontalPanel addDecimalsValuesSeparator() {	
		decimalValuesPanel = new HorizontalPanel();
		decimalValuesPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		decimalValuesStore = new ListStore<DWCodesModelData>();
		decimalValuesCombo = new ComboBox<DWCodesModelData>();
		decimalValuesCombo.setStore(decimalValuesStore);
		decimalValuesCombo.setWidth(comboWidth);

		decimalValuesCombo.setDisplayField("label");
		decimalValuesCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		decimalValuesCombo.setTriggerAction(TriggerAction.ALL);
		decimalValuesCombo.setTemplate(getTemplate());  
		setDecimalValues(decimalValuesStore);
		
		decimalValuesCombo.setValue(decimalValuesStore.getAt(2));
		

		
//		p.add(DataViewerClientUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip("");
//		p.add(icon);

//		p.add(DataViewerClientUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> "+FAOSTATLanguage.print().decimalNumbers()+" </div>");
		html.setWidth(listTitleWidth);
		
	
		html.setToolTip("");
		
		decimalValuesPanel.add(html);
		
		decimalValuesPanel.add(DataViewerClientUtils.addHSpace(5));
		
		decimalValuesPanel.add(decimalValuesCombo);
		
		return decimalValuesPanel;
	}
	
	private void setDecimalValues(ListStore<DWCodesModelData> store) {
		for (Integer i = 0; i < 3; i++ ) {
			store.add(new DWCodesModelData(i.toString(), i.toString()));
		}
	}

	
	private void setSeparatorValues(ListStore<DWCodesModelData> store) {
		store.add(new DWCodesModelData("", FAOSTATLanguage.print().none()));
		store.add(new DWCodesModelData(",", FAOSTATLanguage.print().comma()));
		store.add(new DWCodesModelData(".", FAOSTATLanguage.print().period()));
		store.add(new DWCodesModelData(" ", FAOSTATLanguage.print().space()));
	}
	
	private HorizontalPanel addShowFlag() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		showFlags = new CheckBox();
		showFlags.setValue(true);
		
		p.add(showFlags);
		
		p.add(DataViewerClientUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip(FAOSTATLanguage.print().includeFlagsInDownload());
		p.add(icon);
		

		p.add(DataViewerClientUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> "+FAOSTATLanguage.print().showFlags()+" </div>");
		
		html.setToolTip(FAOSTATLanguage.print().includeFlagsInDownload());
		
		p.add(html);
		
		return p;
	}
	
	private HorizontalPanel addShowCodes() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		showCodes = new CheckBox();
		showCodes.setValue(false);
		
		p.add(showCodes);
		
		p.add(DataViewerClientUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip(FAOSTATLanguage.print().includeCodesInDownload());
		p.add(icon);
		
		p.add(DataViewerClientUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> "+FAOSTATLanguage.print().showCodes()+" </div>");
		
		html.setToolTip(FAOSTATLanguage.print().includeCodesInDownload());
		
		p.add(html);
		
		return p;
	}
	
	private HorizontalPanel addShowUnits() {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		showUnits = new CheckBox();
		showUnits.setValue(true);
		
		p.add(showUnits);
		
		p.add(DataViewerClientUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip(FAOSTATLanguage.print().includeUnitsInDownload());
		p.add(icon);
		
		p.add(DataViewerClientUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> "+FAOSTATLanguage.print().showUnits()+" </div>");
		
		html.setToolTip(FAOSTATLanguage.print().includeUnitsInDownload());
		
		p.add(html);
		
		return p;
	}
	
	private HorizontalPanel addShowNull() {
		showNullPanel = new HorizontalPanel();
		showNullPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		showNull = new CheckBox();
		showNull.setValue(true);
		
		showNullPanel.add(showNull);
		
		showNullPanel.add(DataViewerClientUtils.addHSpace(5));
		
		IconButton icon = new IconButton("info");
		icon.setToolTip(FAOSTATLanguage.print().includeNullValuesInDownload());
		showNullPanel.add(icon);
		
		
		showNullPanel.add(DataViewerClientUtils.addHSpace(5));
		
		Html html = new Html("<div class='download_option_text'> "+FAOSTATLanguage.print().showNullValues()+" </div>");
		
		html.setToolTip(FAOSTATLanguage.print().includeNullValuesInDownload());
		
		showNullPanel.add(html);
		
		return showNullPanel;
	}
	
	public void showPivotOptions() {	
		showNullPanel.show();
		thousandPanel.show();
		decimalPanel.show();
		decimalValuesPanel.show();
		panel.layout();
	}

	public void hidePivotOptions() {
		showNullPanel.hide();
		thousandPanel.hide();
		decimalPanel.hide();
		decimalValuesPanel.hide();
		panel.layout();
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


	public ComboBox<DWCodesModelData> getThousandCombo() {
		return thousandCombo;
	}


	public ComboBox<DWCodesModelData> getDecimalCombo() {
		return decimalCombo;
	}
	
	


	public ComboBox<DWCodesModelData> getDecimalValuesCombo() {
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
