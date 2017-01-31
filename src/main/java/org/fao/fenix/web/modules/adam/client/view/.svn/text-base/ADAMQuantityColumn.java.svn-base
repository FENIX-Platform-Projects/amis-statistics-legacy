package org.fao.fenix.web.modules.adam.client.view;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMQuantityColumnController;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public class ADAMQuantityColumn {

	private static HorizontalPanel aggregationTypeFilter =  new HorizontalPanel();

	private static ListStore<GaulModelData> store;

	public static ComboBox<GaulModelData> list;

	public static Boolean isBuild = false;

	public static String quantityURLParameter;
	
	static Html displayLabel;
	
	static IconButton infoIcon;


	public static HorizontalPanel buildAggregationSelector(ADAMCurrentVIEW currentVIEW) {
			
		aggregationTypeFilter =  new HorizontalPanel();
		isBuild = true;
		
		aggregationTypeFilter.setSpacing(5);
		aggregationTypeFilter.setHorizontalAlign(HorizontalAlignment.LEFT);
		aggregationTypeFilter.setVerticalAlign(VerticalAlignment.MIDDLE);

		store = new ListStore<GaulModelData>();
		list = new ComboBox<GaulModelData>();
		list.setTriggerAction(TriggerAction.ALL);
		list.setStore(store);
		list.setDisplayField("gaulLabel");
		list.setWidth(ADAMConstants.AGGREGATION_LIST_WIDTH);
		list.setTemplate(getTemplate()); 
		
		
		displayLabel = new Html();
		
		
		infoIcon = new IconButton("adam_info");
		infoIcon.setId("INFO_ICON_ID");
    	infoIcon.setToolTip("Click for more information on the OECD flow types");
    	infoIcon.setHeight(20);
    	
    	

		ADAMSelectedDataset dataset = ADAMController.currentlySelectedDatasetCode;
	
		
		infoIcon.removeAllListeners();
		
		switch (dataset) {
		case ADAM_CRS:
			infoIcon.addSelectionListener(ADAMQuantityColumnController.showOECDQuantityInfo());
			reLabelComboBox("Development Assistance Flow Type", ADAMConstants.QUANTITY_LABEL_WIDTH);
			fillCRSDateStore(store);
			break;
		case ADAM_FPMIS:
			infoIcon.addSelectionListener(ADAMQuantityColumnController.showFPMISQuantityInfo());
			reLabelComboBox("Amount", ADAMConstants.QUANTITY_LABEL_WIDTH_SHORT);
			fillFPMISDateStore(store);
			break;
		default:
			infoIcon.addSelectionListener(ADAMQuantityColumnController.showOECDQuantityInfo());
			fillCRSDateStore(store);
			break;	
		}
		
	
		setSelectedValues(list, store, quantityURLParameter, dataset);

		list.addSelectionChangedListener(ADAMQuantityColumnController.updateAggregationColumnADAMView(list));
			

    	aggregationTypeFilter.add(infoIcon);
    		 
		aggregationTypeFilter.add(displayLabel);
		aggregationTypeFilter.add(list);

		return aggregationTypeFilter;
	}



	public static void updateAggregationSelector() {	
		isBuild = true;

		ADAMSelectedDataset dataset = ADAMController.currentlySelectedDatasetCode;
		//quantityURLParameter = list.getValue().getGaulCode();
		
		quantityURLParameter = null;
	
		infoIcon.removeAllListeners();
		
		switch (dataset) {
		case ADAM_CRS:
			infoIcon.addSelectionListener(ADAMQuantityColumnController.showOECDQuantityInfo());
			reLabelComboBox("Development Assistance Flow Type", ADAMConstants.QUANTITY_LABEL_WIDTH);
			fillCRSDateStore(store);
			aggregationTypeFilter.getLayout().layout();
			break;
		case ADAM_FPMIS:
			infoIcon.addSelectionListener(ADAMQuantityColumnController.showFPMISQuantityInfo());
			reLabelComboBox("Amount", ADAMConstants.QUANTITY_LABEL_WIDTH_SHORT);
			fillFPMISDateStore(store);
			break;
		}

		setSelectedValues(list, store, quantityURLParameter, dataset);
	}


	private static void setSelectedValues(ComboBox<GaulModelData> list, ListStore<GaulModelData> store, String quantityURLParameter, ADAMSelectedDataset dataset){
		
		if ( quantityURLParameter == null) { 
			list.setValue(store.getAt(0));	
		}
		else {
			if(store.findModel(quantityURLParameter)!=null)
				list.setValue(store.findModel("gaulCode", quantityURLParameter));
			else  {
				list.setValue(store.getAt(0));	
			}	
		}

	}

	private static void fillCRSDateStore(ListStore<GaulModelData> store){
		store.removeAll();
		store.add(new GaulModelData("Commitments Current", "usd_commitment", "Expressed in USD millions in Current Prices"));
		store.add(new GaulModelData("Disbursements Current", "usd_disbursement", "Expressed in USD millions in Current Prices"));
		store.add(new GaulModelData("Commitments Constant (2011 USD Mil)", "usd_commitment_defl", "Expressed in USD millions in Constant Prices for 2011 (deflated amounts)"));
		store.add(new GaulModelData("Disbursements Constant (2011 USD Mil)", "usd_disbursement_defl", "Expressed in USD millions in Constant Prices for 2011 (deflated amounts)"));
	}
	
	
	private static void reLabelComboBox(String label, String width){
		displayLabel.setHtml("<div class='filters-text'>"+label+":</div>");
		displayLabel.setWidth(width);
	}

	private static void fillFPMISDateStore(ListStore<GaulModelData> store){
		store.removeAll();
		store.add(new GaulModelData("Approvals", "usd_commitment"));
	}

	public static Boolean getIsBuild() {
		return isBuild;
	}

	private native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-combo-list-item" qtip="{description}" qtitle="">{gaulLabel}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;  
	
	
}

