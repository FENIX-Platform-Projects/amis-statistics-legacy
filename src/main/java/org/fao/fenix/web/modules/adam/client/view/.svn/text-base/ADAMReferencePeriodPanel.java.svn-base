package org.fao.fenix.web.modules.adam.client.view;

import java.util.HashMap;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class ADAMReferencePeriodPanel {

	private static VerticalPanel referencePeriodFilter =  new VerticalPanel();

	private static ListStore<GaulModelData> fromDateStore;

	public static ComboBox<GaulModelData> fromDateList;

	private static ListStore<GaulModelData> toDateStore;

	public static ComboBox<GaulModelData> toDateList;

	public static Boolean isBuild = false;
	public static Boolean isCRS = true;

	public static String fromDateURLParameter;

	public static String toDateURLParameter;

	static {
		fromDateStore = new ListStore<GaulModelData>();
		fromDateList = new ComboBox<GaulModelData>();
		fromDateList.setTriggerAction(TriggerAction.ALL);
		fromDateList.setStore(fromDateStore);
		fromDateList.setDisplayField("gaulLabel");
		fromDateList.setWidth(ADAMConstants.REFERENCE_PERIOD_LIST_WIDTH);
		
		toDateStore = new ListStore<GaulModelData>();
		toDateList = new ComboBox<GaulModelData>();
		toDateList.setTriggerAction(TriggerAction.ALL);
		toDateList.setStore(toDateStore);
		toDateList.setDisplayField("gaulLabel");
		toDateList.setWidth(ADAMConstants.REFERENCE_PERIOD_LIST_WIDTH);
	
	}
	
	public static VerticalPanel buildReferencePeriod(ADAMCurrentVIEW currentVIEW) {
		
		referencePeriodFilter =  new VerticalPanel();
		isBuild = true;
		
		referencePeriodFilter.setSpacing(5);
		//referencePeriodFilter.setHorizontalAlign(HorizontalAlignment.RIGHT);
		referencePeriodFilter.setVerticalAlign(VerticalAlignment.MIDDLE);

		fromDateStore = new ListStore<GaulModelData>();
		fromDateList = new ComboBox<GaulModelData>();
		fromDateList.setTriggerAction(TriggerAction.ALL);
		fromDateList.setStore(fromDateStore);
		fromDateList.setDisplayField("gaulLabel");
		fromDateList.setWidth(ADAMConstants.REFERENCE_PERIOD_LIST_WIDTH);
		
		toDateStore = new ListStore<GaulModelData>();
		toDateList = new ComboBox<GaulModelData>();
		toDateList.setTriggerAction(TriggerAction.ALL);
		toDateList.setStore(toDateStore);
		toDateList.setDisplayField("gaulLabel");
		toDateList.setWidth(ADAMConstants.REFERENCE_PERIOD_LIST_WIDTH);
		
		ADAMSelectedDataset dataset = ADAMController.currentlySelectedDatasetCode;
	
		
		switch (dataset) {
		case ADAM_CRS:
			isCRS = true;
			fillCRSDateStore(fromDateStore);
			fillCRSDateStore(toDateStore);
			break;
		case ADAM_FPMIS:
			isCRS = false;
			fillFPMISDateStore(fromDateStore);
			fillFPMISDateStore(toDateStore);
			break;
		default:
			isCRS = true;
			fillCRSDateStore(fromDateStore);
			fillCRSDateStore(toDateStore);
			break;	
		}

	
		setSelectedValues(fromDateList, toDateList, fromDateStore, toDateStore, fromDateURLParameter, toDateURLParameter, dataset);

		fromDateList.addSelectionChangedListener(ADAMController.changeReferencePeriodListener(fromDateList, toDateList));
		toDateList.addSelectionChangedListener(ADAMController.changeReferencePeriodListener(fromDateList, toDateList));
		
		Html fromYear = new Html("<div class='filters-text'>From:</div>");
		fromYear.setStyleAttribute("padding-left", "8px");
		fromYear.setWidth(70);

		referencePeriodFilter.add(fromYear);
		referencePeriodFilter.add(fromDateList);

		Html toYear = new Html("<div class='filters-text'>To:</div>");
		toYear.setStyleAttribute("padding-left", "8px");
		toYear.setWidth(60);

		referencePeriodFilter.add(toYear);
		referencePeriodFilter.add(toDateList);

		referencePeriodFilter.setStyleName("reference-period border");

		return referencePeriodFilter;
	}

	public static VerticalPanel buildFromDateSelector() {
		isBuild = true;
		
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		
		Html label = new Html("<div class='filters-text'>From:</div>");
		label.setWidth(ADAMConstants.REFERENCE_PERIOD_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		p.setSpacing(5);
	
		ADAMSelectedDataset dataset = ADAMController.currentlySelectedDatasetCode;
		
		switch (dataset) {
		case ADAM_CRS:
			isCRS = true;
			fillCRSDateStore(fromDateStore);
			fillCRSDateStore(toDateStore);
			break;
		case ADAM_FPMIS:
			isCRS = false;
			fillFPMISDateStore(fromDateStore);
			fillFPMISDateStore(toDateStore);
			break;
		default:
			isCRS = true;
			fillCRSDateStore(fromDateStore);
			fillCRSDateStore(toDateStore);
			break;	
		}

		setSelectedValues(fromDateList, toDateList, fromDateStore, toDateStore, fromDateURLParameter, toDateURLParameter, dataset);

		fromDateList.addSelectionChangedListener(ADAMController.changeReferencePeriodListener(fromDateList, toDateList));
		p.add(fromDateList);
		
		vp.add(p);
		
		return vp;
	}
	
	public static VerticalPanel buildToDateSelector() {	
		isBuild = true;
		
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		
		Html label = new Html("<div class='filters-text'>To:</div>");
		label.setWidth(ADAMConstants.REFERENCE_PERIOD_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		p.setSpacing(5);
	
		toDateList.addSelectionChangedListener(ADAMController.changeReferencePeriodListener(fromDateList, toDateList));
		
		p.add(toDateList);
		
		vp.add(p);
		
		return vp;
	}

	public static void updateReferenceDateSelector() {	
		isBuild = true;

		ADAMSelectedDataset dataset = ADAMController.currentlySelectedDatasetCode;
		//fromDateURLParameter = fromDateList.getValue().getGaulCode();
		//toDateURLParameter = toDateList.getValue().getGaulCode();
		fromDateURLParameter = null;
		toDateURLParameter = null;
		ADAMController.baseDate.clear();
	
		switch (dataset) {
		case ADAM_CRS:
			isCRS = true;
			fillCRSDateStore(fromDateStore);
			fillCRSDateStore(toDateStore);
			break;
		case ADAM_FPMIS:
			isCRS = false;
			fillFPMISDateStore(fromDateStore);
			fillFPMISDateStore(toDateStore);
			break;
		}

		setSelectedValues(fromDateList, toDateList, fromDateStore, toDateStore, fromDateURLParameter, toDateURLParameter, dataset);
	}


	private static void setSelectedValues(ComboBox<GaulModelData> fromDateList, ComboBox<GaulModelData>  toDateList, ListStore<GaulModelData> fromDateStore, ListStore<GaulModelData> toDateStore, String fromDateURLParameter, String toDateURLParameter, ADAMSelectedDataset dataset){
		
		GaulModelData fromBaseDate = new GaulModelData("2006", "01-01-2006");
    	
		if ( fromDateURLParameter == null) { 
			if(isCRS)
				fromDateList.setValue(fromBaseDate);
			else 
				fromDateList.setValue(fromDateStore.getAt(fromDateStore.getCount()-1));
		}
		else {
			if(fromDateStore.findModel(fromDateURLParameter)!=null)
				fromDateList.setValue(fromDateStore.findModel("gaulCode", fromDateURLParameter));
			else  {
				if(isCRS)
					fromDateList.setValue(fromBaseDate);
				else
					fromDateList.setValue(fromDateStore.getAt(fromDateStore.getCount()-1));	
			}	
		}

		if ( toDateURLParameter == null) { 
			toDateList.setValue(toDateStore.getAt(0));
		}
		else {
			if(toDateStore.findModel(toDateURLParameter)!=null)
				toDateList.setValue(toDateStore.findModel("gaulCode", toDateURLParameter));
			else 
				toDateList.setValue(toDateStore.getAt(0));
		}
	}

	private static void fillCRSDateStore(ListStore<GaulModelData> store){
		store.removeAll();
		store.add(new GaulModelData("2011", "01-01-2011"));
		store.add(new GaulModelData("2010", "01-01-2010"));
		store.add(new GaulModelData("2009", "01-01-2009"));
		store.add(new GaulModelData("2008", "01-01-2008"));
		store.add(new GaulModelData("2007", "01-01-2007"));
		store.add(new GaulModelData("2006", "01-01-2006"));
		store.add(new GaulModelData("2005", "01-01-2005"));
		store.add(new GaulModelData("2004", "01-01-2004"));
		store.add(new GaulModelData("2003", "01-01-2003"));
		store.add(new GaulModelData("2002", "01-01-2002"));
	}

	private static void fillFPMISDateStore(ListStore<GaulModelData> store){
		store.removeAll();
		//store.add(new GaulModelData("2012", "01-01-2012"));
		store.add(new GaulModelData("2011", "01-01-2011"));
		store.add(new GaulModelData("2010", "01-01-2010"));
	}

	public static Boolean getIsBuild() {
		return isBuild;
	}

	private static VerticalPanel addSpace() {
		VerticalPanel panel = new VerticalPanel();
		panel.setHeight(15);
		return panel;
	}

}