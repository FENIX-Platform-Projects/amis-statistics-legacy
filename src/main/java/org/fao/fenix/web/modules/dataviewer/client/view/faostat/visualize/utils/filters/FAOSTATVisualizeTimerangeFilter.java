package org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.utils.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.amis.common.vo.CCBS.Codes;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeByDomainController;
import org.fao.fenix.web.modules.dataviewer.client.control.faostat.visualize.FAOSTATVisualizeController;
import org.fao.fenix.web.modules.dataviewer.client.lang.FAOSTATLanguage;
import org.fao.fenix.web.modules.dataviewer.client.view.DataViewerClientUtils;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.FAOSTATVisualize;
import org.fao.fenix.web.modules.dataviewer.client.view.faostat.visualize.bydomain.FAOSTATVisualizeByDomain;
import org.fao.fenix.web.modules.dataviewer.common.vo.DWCodesModelData;

import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class FAOSTATVisualizeTimerangeFilter extends FAOSTATVisualizeFilter {
	
	ComboBox<DWCodesModelData> fromCombo;
	ListStore<DWCodesModelData> fromStore;
	
	
	ComboBox<DWCodesModelData> toCombo;
	ListStore<DWCodesModelData> toStore;

	
	public FAOSTATVisualizeTimerangeFilter() {

		fromCombo = new ComboBox<DWCodesModelData>();
		fromStore = new ListStore<DWCodesModelData>();
		
		toCombo = new ComboBox<DWCodesModelData>();
		toStore = new ListStore<DWCodesModelData>();

		selectedCodes = new HashMap<String, String>();
		
		multiselection = new FAOSTATVisualizeMultiselectionFilter();
	}

	public HorizontalPanel build(FAOSTATVisualize visualization, org.fao.fenix.web.modules.dataviewer.common.vo.faostat.FAOSTATVisualizeFilter filterVO, String title, String filterType, Boolean multi, DWCodesModelData code,  List<DWCodesModelData> defaultCodes, List<DWCodesModelData> basecodes) {
		this.filterVO = filterVO;
		
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
//		System.out.println("build from year to year: " + defaultCodes.size());

		List<DWCodesModelData> fromYear = new ArrayList<DWCodesModelData>();
		List<DWCodesModelData> toYear = new ArrayList<DWCodesModelData>();
		
		if ( !defaultCodes.isEmpty() && defaultCodes != null) {
//			for(DWCodesModelData c : defaultCodes) {
//				System.out.println("default years: " + c.getCode());
//			}
			try {
				/** TODO: sort it...**/
				fromYear.add(defaultCodes.get(0));
				toYear.add(defaultCodes.get(1));
			}catch (Exception e) {
			}
		}
		
		this.title = title;

		
		fromCombo.setStore(fromStore);
		fromCombo.setDisplayField("label");
		fromCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		fromCombo.setStore(fromStore);
		fromCombo.setWidth(60);
		fromCombo.setTriggerAction(TriggerAction.ALL);
		
		

		
		toCombo.setStore(toStore);
		toCombo.setDisplayField("label");
		toCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		toCombo.setStore(toStore);
		toCombo.setWidth(60);
		toCombo.setTriggerAction(TriggerAction.ALL);
		
		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'>"+FAOSTATLanguage.print().fromYear()+" </div>"));
		p.add(DataViewerClientUtils.addHSpace(2));
		p.add(fromCombo);
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(new Html("<div class='visualize_filters_combo'>"+FAOSTATLanguage.print().toYear()+" </div>"));
		p.add(DataViewerClientUtils.addHSpace(2));
		p.add(toCombo);
		p.setHeight(25);
		if ( multi )  { 
			IconButton icon = new IconButton("addIcon");
			p.add(DataViewerClientUtils.addHSpace(2));
			p.add(icon);
		}
		p.add(DataViewerClientUtils.addHSpace(2));
		
		// to avoid to call the combo
		fromCombo.enableEvents(false);
		toCombo.enableEvents(false);
		
		// listener TODO: should be passed?
		fromCombo.addSelectionChangedListener(FAOSTATVisualizeByDomainController.updateTimeRangeFilter(visualization, fromCombo, toCombo, filterType, selectedCodes));
		toCombo.addSelectionChangedListener(FAOSTATVisualizeByDomainController.updateTimeRangeFilter(visualization, fromCombo, toCombo, filterType, selectedCodes));
		
		if ( filterVO !=null )
			if ( !filterVO.getDomains().isEmpty() ) {
				for(String key : filterVO.getDomains().keySet() ) {
					/** TODO: should be multidomains **/
					code = new DWCodesModelData(key, filterVO.getDomains().get(key));
				}
			}
		
		/** TODO: if it's not codying system **/
		if ( basecodes.isEmpty() ) {
			FAOSTATVisualizeController.loadCodingSystemTimerange(fromCombo, fromStore, toCombo, toStore, filterType, code, fromYear, toYear, selectedCodes, selectedCodes);
//			FAOSTATVisualizeController.loadCodingSystem(fromCombo, fromStore, filterType, code, fromYear, selectedCodes, multi);
//			FAOSTATVisualizeController.loadCodingSystem(toCombo, toStore, filterType, code, toYear, selectedCodes, multi);
		}
		else {
			try { 
//				System.out.println("DEFAULT YEARS: " + defaultCodes.get(0).getCode() + " | " + defaultCodes.get(1).getCode());
			}catch (Exception e) {
			}
			
			// TODO Dynamic the YEARS
			int startyear = Integer.valueOf(basecodes.get(0).getCode());
			int endyear = Integer.valueOf(basecodes.get(1).getCode());

			for(int i = endyear; i >= startyear; i--) {
				DWCodesModelData c = new DWCodesModelData(String.valueOf(i), String.valueOf(i));
				fromStore.add(c);
				toStore.add(c);
				
				if ( defaultCodes.isEmpty()) {
					
					// startyear
//					System.out.println("i: " + i + " | " + startyear);
					if ( i == startyear ) {
//						System.out.println("---------------->i : " + i + " | " + startyear);
						selectedCodes.put(String.valueOf(i), String.valueOf(i));
						fromCombo.setValue(c);
					}
					
					// endyear
					if( i == endyear ) {
//						System.out.println("---------------->i : " + i + " | " + endyear);
						selectedCodes.put(String.valueOf(i), String.valueOf(i));
						toCombo.setValue(c);
					}
				}
				else {
//					System.out.println("i: " + i );
					// Use default codes
					// startyear
					if ( i == Integer.valueOf(defaultCodes.get(0).getCode()) ) {
						selectedCodes.put(String.valueOf(i), String.valueOf(i));
						fromCombo.setValue(c);
					}
					
					// endyear
					if( i == Integer.valueOf(defaultCodes.get(1).getCode()) ) {
						selectedCodes.put(String.valueOf(i), String.valueOf(i));
						toCombo.setValue(c);
					}
				}

			}

			fromCombo.enableEvents(true);
			toCombo.enableEvents(true);
		}

		
		return p;
	}
	
	
	public HorizontalPanel build(String filterType, DWCodesModelData code, int startYear, int endYear, int width) {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		List<DWCodesModelData> fromYear = new ArrayList<DWCodesModelData>();
		List<DWCodesModelData> toYear = new ArrayList<DWCodesModelData>();
		fromYear.add(new DWCodesModelData(String.valueOf(startYear), String.valueOf(startYear)));
		toYear.add(new DWCodesModelData(String.valueOf(endYear), String.valueOf(endYear)));
		fromCombo.setStore(fromStore);
		fromCombo.setDisplayField("label");
		fromCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		fromCombo.setStore(fromStore);
		fromCombo.setWidth(width);
		fromCombo.setTriggerAction(TriggerAction.ALL);
		toCombo.setStore(toStore);
		toCombo.setDisplayField("label");
		toCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		toCombo.setStore(toStore);
		toCombo.setWidth(width);
		toCombo.setTriggerAction(TriggerAction.ALL);
		Integer labelWidth = 75;
		VerticalPanel from = new VerticalPanel();
		Html fromLabel = new Html("<div class='visualize_filters_combo'>"+FAOSTATLanguage.print().fromYear()+" </div>");
		fromLabel.setWidth(labelWidth);
		from.add(fromLabel);
		from.add(DataViewerClientUtils.addVSpace(2));
		from.add(fromCombo);
		from.setVerticalAlign(VerticalAlignment.MIDDLE);
		VerticalPanel to = new VerticalPanel();		
		Html toLabel = new Html("<div class='visualize_filters_combo'>"+FAOSTATLanguage.print().toYear()+" </div>");
		toLabel.setWidth(labelWidth);
		to.add(toLabel);
		to.add(DataViewerClientUtils.addVSpace(2));
		to.add(toCombo);
		to.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		p.add(from);
		p.add(DataViewerClientUtils.addHSpace(10));
		p.add(to);
		
		FAOSTATVisualizeController.loadCodingSystemTimerange(fromCombo, fromStore, toCombo, toStore, filterType, code, fromYear, toYear, selectedCodes, selectedCodes);
		return p;
	}
	
	
	public HorizontalPanel build(String filterType, DWCodesModelData code) {
		HorizontalPanel p = new HorizontalPanel();
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		fromCombo.setStore(fromStore);
		fromCombo.setDisplayField("label");
		fromCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		fromCombo.setStore(fromStore);
		fromCombo.setWidth(60);
		fromCombo.setTriggerAction(TriggerAction.ALL);

		toCombo.setStore(toStore);
		toCombo.setDisplayField("label");
		toCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		toCombo.setStore(toStore);
		toCombo.setWidth(60);
		toCombo.setTriggerAction(TriggerAction.ALL);
		
		p.addStyleName("visualize_filters_combo_box");
		p.add(new Html("<div class='visualize_filters_combo'>"+FAOSTATLanguage.print().fromYear()+" </div>"));
		p.add(DataViewerClientUtils.addHSpace(2));
		p.add(fromCombo);
		p.add(DataViewerClientUtils.addHSpace(5));
		p.add(new Html("<div class='visualize_filters_combo'>"+FAOSTATLanguage.print().toYear()+" </div>"));
		p.add(DataViewerClientUtils.addHSpace(2));
		p.add(toCombo);
		p.setHeight(25);
	
		p.add(DataViewerClientUtils.addHSpace(2));
		
		// to avoid to call the combo
		fromCombo.enableEvents(false);
		toCombo.enableEvents(false);
		
		// listener TODO: should be passed?
//		fromCombo.addSelectionChangedListener(FAOSTATVisualizeByDomainController.updateTimeRangeFilter(visualization, fromCombo, toCombo, filterType, selectedCodes));
//		toCombo.addSelectionChangedListener(FAOSTATVisualizeByDomainController.updateTimeRangeFilter(visualization, fromCombo, toCombo, filterType, selectedCodes));
		
		if ( filterVO != null)
			if ( !filterVO.getDomains().isEmpty() ) {
				for(String key : filterVO.getDomains().keySet() ) {
					/** TODO: should be multidomains **/
					code = new DWCodesModelData(key, filterVO.getDomains().get(key));
				}
			}

		FAOSTATVisualizeController.loadCodingSystemTimerange(fromCombo, fromStore, toCombo, toStore, filterType, code, null, null, selectedCodes, selectedCodes);
		
		
		return p;
	}
	
	
	public HorizontalPanel build(int startyear, int endyear, int baseStartYear, int baseEndYear, int width) {
	
		
		fromCombo.setStore(fromStore);
		fromCombo.setDisplayField("label");
		fromCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		fromCombo.setStore(fromStore);
		fromCombo.setWidth(width);
//		fromCombo.setWidth(60);
		fromCombo.setTriggerAction(TriggerAction.ALL);

		toCombo.setStore(toStore);
		toCombo.setDisplayField("label");
		toCombo.setEmptyText(FAOSTATLanguage.print().pleaseMakeASelection());
		toCombo.setStore(toStore);
		toCombo.setWidth(width);
//		toCombo.setWidth(60);
		toCombo.setTriggerAction(TriggerAction.ALL);
		
		
		HorizontalPanel panel = new HorizontalPanel();
//		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		Integer labelWidth = 75;
		
		VerticalPanel from = new VerticalPanel();
		Html fromLabel = new Html("<div class='visualize_filters_combo'>"+FAOSTATLanguage.print().fromYear()+" </div>");
		fromLabel.setWidth(labelWidth);
		from.add(fromLabel);
		from.add(DataViewerClientUtils.addVSpace(2));
		from.add(fromCombo);
		from.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		VerticalPanel to = new VerticalPanel();		
		Html toLabel = new Html("<div class='visualize_filters_combo'>"+FAOSTATLanguage.print().toYear()+" </div>");
		toLabel.setWidth(labelWidth);
		to.add(toLabel);
		to.add(DataViewerClientUtils.addVSpace(2));
		to.add(toCombo);
		to.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		panel.add(from);
		panel.add(DataViewerClientUtils.addHSpace(10));
		panel.add(to);
		

		for(int i = endyear; i >= startyear; i--) {
			DWCodesModelData c = new DWCodesModelData(String.valueOf(i), String.valueOf(i));
			fromStore.add(c);
			toStore.add(c);

			// startyear
			if ( i == startyear ) {
//				System.out.println("---------------->i : " + i + " | " + startyear);
				selectedCodes.put(String.valueOf(i), String.valueOf(i));
				fromCombo.setValue(c);
			}
				
			// endyear
			if( i == endyear ) {
//				System.out.println("---------------->i : " + i + " | " + endyear);
				selectedCodes.put(String.valueOf(i), String.valueOf(i));
				toCombo.setValue(c);
			}
		}
		

		return panel;
	}

	public ComboBox<DWCodesModelData> getFromCombo() {
		return fromCombo;
	}

	public ComboBox<DWCodesModelData> getToCombo() {
		return toCombo;
	}
	
	
	
	
}
