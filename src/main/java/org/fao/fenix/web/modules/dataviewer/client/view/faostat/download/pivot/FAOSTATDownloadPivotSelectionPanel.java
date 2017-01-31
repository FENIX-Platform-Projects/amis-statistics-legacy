package org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.pivot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.dataviewer.client.control.faostat.FAOSTATMainController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownload;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.download.FAOSTATDownloadOptions;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.common.enums.faostat.FAOSTATDimensionConstant;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;


import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;


public class FAOSTATDownloadPivotSelectionPanel {
	
	CheckBox checkBox;
	
	ContentPanel panel;
	
	ContentPanel parametersPanel;
	
	ContentPanel pivotParameters;
	
//	FAOSTATdownloadPivotAxisPanel x;
//	
//	FAOSTATdownloadPivotAxisPanel y1;
//	
//	FAOSTATdownloadPivotAxisPanel y2;
//	
//	FAOSTATdownloadPivotAxisPanel nested;
	
	FAOSTATDownloadPivotDimensionList x;
	
	FAOSTATDownloadPivotDimensionList y1;
	
	FAOSTATDownloadPivotDimensionList y2;
	
	FAOSTATDownloadPivotDimensionList nested;
	
	ContentPanel fsbStylePanel;

	CheckBox fsbStyleCheckBox;
	
	Boolean isFBS;
	
	Boolean isTradeMatrix;
	
	public FAOSTATDownloadPivotSelectionPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		
		parametersPanel = new ContentPanel();
		parametersPanel.setHeaderVisible(false);
		parametersPanel.setBodyBorder(false);
		
		fsbStylePanel = new ContentPanel();
		fsbStylePanel.setHeaderVisible(false);
		fsbStylePanel.setBodyBorder(false);
		
		pivotParameters = new ContentPanel();
		pivotParameters.setHeaderVisible(false);
		pivotParameters.setBodyBorder(false);
		
		fsbStyleCheckBox = new CheckBox();
		fsbStyleCheckBox.setValue(false);

//		x = new FAOSTATdownloadPivotAxisPanel();
//		y1 = new FAOSTATdownloadPivotAxisPanel();
//		y2 = new FAOSTATdownloadPivotAxisPanel();	
//		nested = new FAOSTATdownloadPivotAxisPanel();
		
		x = new FAOSTATDownloadPivotDimensionList();
		y1 = new FAOSTATDownloadPivotDimensionList();
		y2 = new FAOSTATDownloadPivotDimensionList();	
		nested = new FAOSTATDownloadPivotDimensionList();

		checkBox = new CheckBox();
	}
	
	
	public ContentPanel build(FAOSTATDownload download, FAOSTATDownloadFlatTable flatTable, Boolean isFBS, Boolean isTradeMatrix){
		// initialize
		panel.removeAll();
		parametersPanel.removeAll();
		fsbStylePanel.removeAll();
		pivotParameters.removeAll();
		fsbStyleCheckBox.setValue(false);
		this.isFBS = isFBS;
		this.isTradeMatrix = isTradeMatrix;
		
		
		
//		panel.addStyleName("content_box");
		
		VerticalPanel p = new VerticalPanel();
//		p.setSpacing(5);
		
		
		p.add(buildTitle(flatTable, download));
		
//		p.add(DataViewerClientUtils.addVSpace(3));
		
		p.add(buildPivotSelection(download, isFBS, isTradeMatrix));
		
		panel.add(p);
		
		parametersPanel.layout();
		panel.layout();
		


		return panel;
	}
	
	private HorizontalPanel buildTitle(FAOSTATDownloadFlatTable flatTable, FAOSTATDownload download){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);

		IconButton icon = new IconButton("info");
		Html html = new Html("<div class='download_option_text'>"+FAOSTATLanguage.print().pivotTable()+"</div>");
		
		// DEFAULT
		checkBox = new CheckBox();
		checkBox.setValue(true);
		
		panel.add(checkBox);
		
		checkBox.addListener(Events.Change, changeCheckBox(flatTable, download));
		
//		panel.add(DataViewerClientUtils.addHSpace(5));
//		panel.add(icon);
		panel.add(DataViewerClientUtils.addHSpace(5));
		panel.add(html);
		
		
		
		return panel;
	}
	
	
	private HorizontalPanel buildFBSStyle(FAOSTATDownload download){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setHorizontalAlign(HorizontalAlignment.LEFT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
//		panel.add(DataViewerClientUtils.addHSpace(5));

		
		Html html = new Html("<div class='download_option_text'>" + FAOSTATLanguage.print().fsbStyle() +"</div>");
		
		// DEFAULT
		fsbStyleCheckBox = new CheckBox();
		fsbStyleCheckBox.setValue(true);
		
		panel.add(fsbStyleCheckBox);

		fsbStyleCheckBox.addListener(Events.Change, fsbCheckBox(download));


		panel.add(DataViewerClientUtils.addHSpace(5));
		panel.add(html);
		return panel;
	}
	
	private ContentPanel buildPivotSelection(FAOSTATDownload download, Boolean isFBS, Boolean isTradeMatrix) {
	
		if ( isTradeMatrix ){
			// empty?
		}
		else {
			HorizontalPanel h = new HorizontalPanel();
			h.setHorizontalAlign(HorizontalAlignment.LEFT);
			h.setVerticalAlign(VerticalAlignment.MIDDLE);
	//		h.add(DataViewerClientUtils.addHSpace(3));
			VerticalPanel v = new VerticalPanel();
			v.setHorizontalAlign(HorizontalAlignment.LEFT);
			v.setVerticalAlign(VerticalAlignment.MIDDLE);
			h.add(v);
	//		v.setSpacing(3);
			if ( isFBS ) {
				fsbStylePanel.add(buildFBSStyle(download));
				v.add(fsbStylePanel);
			}
			else {
				fsbStyleCheckBox.setValue(false);
				pivotParameters.show();
			}
			
			fsbCheckBoxAgent(download);
			
			v.add(DataViewerClientUtils.addVSpace(2));
	
	
			pivotParameters.add(y1.build(FAOSTATLanguage.print().rows(), FAOSTATDimensionConstant.COUNTRIES));
		
			pivotParameters.add(DataViewerClientUtils.addVSpace(2));
	
			pivotParameters.add(y2.build(FAOSTATLanguage.print().subRows(), FAOSTATDimensionConstant.ITEMS));
			
			pivotParameters.add(DataViewerClientUtils.addVSpace(2));
			
			pivotParameters.add(x.build(FAOSTATLanguage.print().columns(), FAOSTATDimensionConstant.YEARS));
			
			pivotParameters.add(DataViewerClientUtils.addVSpace(2));
				
			pivotParameters.add(nested.build(FAOSTATLanguage.print().createTablesBy(), FAOSTATDimensionConstant.ELEMENTS));
	
			v.add(pivotParameters);
			
	
			parametersPanel.add(h);
			
	//		parametersPanel.hide();
			parametersPanel.show();
			
			addlisteners();
		}
		return parametersPanel;
	}
	
	
	private void addlisteners() { 
		nested.getCombo().addSelectionChangedListener(updateFilterNested());
		x.getCombo().addSelectionChangedListener(updateFilterX());
		y1.getCombo().addSelectionChangedListener(updateFilterY1());
		y2.getCombo().addSelectionChangedListener(updateFilterY2());
	}

	
	
	
	
	private SelectionChangedListener<DWCodesModelData> updateFilterNested() {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				nested.getCombo().enableEvents(false);
	
				String oldCode = nested.getCurrentCode();
				String newValue = nested.getCombo().getValue().getCode();
				
				swapValues(x, newValue, oldCode);
				swapValues(y1, newValue, oldCode);
				swapValues(y2, newValue, oldCode);
				
				nested.setCurrentCode(newValue);
				nested.getCombo().enableEvents(true);
			}
		};
	}
	
	private SelectionChangedListener<DWCodesModelData> updateFilterX() {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				x.getCombo().enableEvents(false);
	
				String oldCode = x.getCurrentCode();
				String newValue = x.getCombo().getValue().getCode();
				
				swapValues(y1, newValue, oldCode);
				swapValues(y2, newValue, oldCode);

				// TODO: TO DO?
				swapValues(nested, newValue, oldCode);
				
				x.setCurrentCode(newValue);
				x.getCombo().enableEvents(true);
			}
		};
	}
	

	private SelectionChangedListener<DWCodesModelData> updateFilterY1() {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				y1.getCombo().enableEvents(false);
				
				String oldCode = y1.getCurrentCode();
				String newValue = y1.getCombo().getValue().getCode();
				
				swapValues(y2, newValue, oldCode);

				// TODO: TO DO?
				swapValues(x, newValue, oldCode);
				swapValues(nested, newValue, oldCode);
				
				y1.setCurrentCode(newValue);
				y1.getCombo().enableEvents(true);
			}
		};
	}
	
	private SelectionChangedListener<DWCodesModelData> updateFilterY2() {
		return new SelectionChangedListener<DWCodesModelData>() {
			public void selectionChanged(SelectionChangedEvent<DWCodesModelData> se) {
				y2.getCombo().enableEvents(false);
	
				String oldCode = y2.getCurrentCode();
				String newValue = y2.getCombo().getValue().getCode();
				
				// TODO: TO DO?
				swapValues(x, newValue, oldCode);
				swapValues(y1, newValue, oldCode);
				swapValues(nested, newValue, oldCode);
				
				y2.setCurrentCode(newValue);
				y2.getCombo().enableEvents(true);
			}
		};
	}
	
	private Boolean swapValues(FAOSTATDownloadPivotDimensionList dimension, String currentValue, String newValue) {
		dimension.getCombo().enableEvents(false);
		
		System.out.println("currentValue!" + currentValue);
		System.out.println("newValue!" + newValue);

		Boolean swap = false;
		
		String currentComboValue = dimension.getCombo().getValue().getCode();
		
		System.out.println("currentComboValue!" + currentComboValue);
		
		if ( currentComboValue.equals(currentValue)) {		
			swap = true;
		}
		
		System.out.println("SWAP: " + swap);
				
		if ( swap ) {
			dimension.setCurrentCode(newValue);
			for (int i = 0; i < dimension.getStore().getCount() ; i++ ) {
				if ( dimension.getStore().getAt(i).getCode().equals(newValue)) {
					dimension.getCombo().setValue(dimension.getStore().getAt(i));

				}
			}
		}
		dimension.getCombo().enableEvents(true);
		
		return swap;
	}
	
//	private void removeItems(ListStore<DWCodesModelData> store, String removeValue) {
//		combo.enableEvents(false);
//		
//		System.out.println("currentValue!" + currentValue);
//		System.out.println("newValue!" + newValue);
//
//		Boolean swap = false;
//		String currentComboValue = combo.getValue().getCode();
//			if ( currentComboValue.equals(currentValue)) {
//				swap = true;
//	
//		}
//				
//		if ( swap ) {
//			for (int i = 0; i < store.getCount() ; i++ ) {
//				if ( store.getAt(i).getCode().equals(newValue)) {
//					combo.setValue(store.getAt(i));
//				}
//			}
//		}
//		combo.enableEvents(true);
//	}

	// DRAG AND DROP LAYOUT
//	private ContentPanel buildPivotSelection() {
//		HorizontalPanel panel = new HorizontalPanel();
//		
//		VerticalPanel v = new VerticalPanel();
//		v.setSpacing(3);
//
//		v.add(DataViewerClientUtils.addVSpace(30));
//
//		v.add(buildYsAxis());
//		
//		panel.add(v);
//		
//		panel.add(DataViewerClientUtils.addHSpace(5));
//		
//		panel.add(buildXAxis());
//		
//		parametersPanel.add(panel);
//		
//		parametersPanel.disable();
//		return parametersPanel;
//	}
//	
//	private ContentPanel buildXAxis() {
//		return x.build("X-Axis", "Years", "years", FAOSTATDimensionConstant.YEARS);
//	}
//	
//	private HorizontalPanel buildYsAxis() {
//		HorizontalPanel panel = new HorizontalPanel();
//		
//		panel.add(DataViewerClientUtils.addHSpace(5));
//		
////		panel.add(nested.build("Nested", "Elements", "extra", FAOSTATDimensionConstant.ELEMENTS_LIST));
//		panel.add(nested.build("Nested", "Elements", "extra", FAOSTATDimensionConstant.ELEMENTS));
//		
//		panel.add(DataViewerClientUtils.addHSpace(5));
//		
//		panel.add(y1.build("Y1-Axis", "Countries", "countries", FAOSTATDimensionConstant.COUNTRIES));
//		
//		panel.add(DataViewerClientUtils.addHSpace(5));
//		
//		panel.add(y2.build("Y2-Axis", "Items", "items", FAOSTATDimensionConstant.ITEMS));
//	
//		return panel;
//	}
//
//	
//	public FAOSTATdownloadPivotAxisPanel getX() {
//		return x;
//	}
//
//	public FAOSTATdownloadPivotAxisPanel getY1() {
//		return y1;
//	}
//
//	public FAOSTATdownloadPivotAxisPanel getY2() {
//		return y2;
//	}
//
//	public FAOSTATdownloadPivotAxisPanel getNested() {
//		return nested;
//	}

	public ContentPanel getPanel() {
		return panel;
	}


	public CheckBox getCheckBox() {
		return checkBox;
	}

	private Listener<FieldEvent> changeCheckBox(final FAOSTATDownloadFlatTable flatTable,  final FAOSTATDownload download) {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				
				// if value = true 
				// Pivot table, else flattable
				
				flatTable.getCheckBox().enableEvents(false);
				flatTable.getCheckBox().setValue(!checkBox.getValue());
				flatTable.getCheckBox().enableEvents(true);
				
				System.out.println("changeCheckBox flatTable.getCheckBox(): " + flatTable.getCheckBox().getValue());
				
				if ( !flatTable.getCheckBox().getValue() ) {
					System.out.println("PIVOT TABLE: isFBS: " + isFBS);
					download.getOptions().showPivotOptions();
					if (  isFBS ) {
						// show FBS
						fsbStylePanel.show();
						fsbStylePanel.layout();
					}
					
					fsbCheckBoxAgent(download);

				}
				else { 
					System.out.println("FLAT TABLE");
					
					parametersPanel.hide();
					download.getOptions().hidePivotOptions();
					disableFBSView(download);
				}
//				fsbCheckBoxAgent(download);
			}
		};
	}
	
	
	private Listener<FieldEvent> fsbCheckBox(final FAOSTATDownload download) {
		return new Listener<FieldEvent>() {
			public void handleEvent(FieldEvent be) {
				fsbCheckBoxAgent(download);
			}
		};
	}

	public void fsbCheckBoxAgent(FAOSTATDownload download) {
		System.out.println("CHECKBOX fsbCheckBoxAgent: " + fsbStyleCheckBox.getValue());
		if ( fsbStyleCheckBox.getValue() ) {
			System.out.println("fsbStyleCheckBox.getValue(): " + fsbStyleCheckBox.getValue());
			pivotParameters.hide();
			parametersPanel.show();
			fsbStylePanel.show();
			panel.layout();
			enableFBSView(download);
		}
		else {
			parametersPanel.show();
			pivotParameters.show();
			disableFBSView(download);
		}
	}
	
	private void enableFBSView(FAOSTATDownload download) {
		// areas 
		// deselect all
		download.getAreas().getSelectorPanel().getList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		FAOSTATMainController.deselectAllAgent(download.getAreas().getSelectorPanel().getList());
		
		// elements
		download.getElements().getList().disable();
//		download.getElements().getList().se
//		download.getAreas().getSelectorPanel().getList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// elements list
		download.getElementsList().getList().disable();
		FAOSTATMainController.selectAllAgent(download.getElementsList().getList());

		
		// items
		download.getItems().getSelectorPanel().getList().disable();
		FAOSTATMainController.selectAllAgent(download.getItems().getSelectorPanel().getList());

		
		// years 
		// deselect all
		download.getYears().getList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		FAOSTATMainController.deselectAllAgent(download.getYears().getList());

	}
	
	public void disableFBSView(FAOSTATDownload download) {

		download.getAreas().getSelectorPanel().getList().getSelectionModel().setSelectionMode(SelectionMode.MULTI);

		
		// elements
		download.getElements().getList().enable();
//		download.getElements().getList().se
//		download.getAreas().getSelectorPanel().getList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// elements list
		download.getElementsList().getList().enable();

		
		// items
		download.getItems().getSelectorPanel().getList().enable();
		
		// years 
		download.getYears().getList().getSelectionModel().setSelectionMode(SelectionMode.MULTI);
		download.getYears().getList().enable();
		
		if ( isFBS ) {
			// reset all is FBS
			FAOSTATMainController.deselectAllAgent(download.getAreas().getSelectorPanel().getList());
			FAOSTATMainController.deselectAllAgent(download.getElementsList().getList());
			FAOSTATMainController.deselectAllAgent(download.getItems().getSelectorPanel().getList());
			FAOSTATMainController.deselectAllAgent(download.getYears().getList());

		}
		
	}
	

	public ContentPanel getParametersPanel() {
		return parametersPanel;
	}


	public FAOSTATDownloadPivotDimensionList getX() {
		return x;
	}


	public FAOSTATDownloadPivotDimensionList getY1() {
		return y1;
	}


	public FAOSTATDownloadPivotDimensionList getY2() {
		return y2;
	}


	public FAOSTATDownloadPivotDimensionList getNested() {
		return nested;
	}


	public CheckBox getFsbStyleCheckBox() {
		return fsbStyleCheckBox;
	}


	public Boolean getIsFBS() {
		return isFBS;
	}
	
	
	
	


}
