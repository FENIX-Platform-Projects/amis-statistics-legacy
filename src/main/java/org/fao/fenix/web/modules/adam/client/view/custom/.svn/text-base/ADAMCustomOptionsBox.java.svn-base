package org.fao.fenix.web.modules.adam.client.view.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;


public class ADAMCustomOptionsBox {
	
	private VerticalPanel panel;
	
	// reference period
	private HorizontalPanel referencePeriodFilter;

	private static ListStore<GaulModelData> fromDateStore;

	private static ComboBox<GaulModelData> fromDateList;
	
	private static ListStore<GaulModelData> toDateStore;

	private static ComboBox<GaulModelData> toDateList;
	
	
	// result Limit
	private Integer limit = 10;
	
	private TextField<String> limitFilter;
	
	
	// aggregation Type
	private static ListStore<GaulModelData> aggregationStore;

	private static ComboBox<GaulModelData> aggregationList;
	

	// column of aggregation
	private static ListStore<GaulModelData> aggregationColumnStore;
	
	private static ComboBox<GaulModelData> aggregationColumnList;
	
	
	
//	private static String LIST_WIDTH = "125px";

	public ADAMCustomOptionsBox() {
		panel = new VerticalPanel();	
		panel.setSpacing(5);
		limitFilter = new TextField<String>();
		limitFilter.setValue(limit.toString());
		limitFilter.setWidth(ADAMConstants.SELECTION_LIST_WIDTH);
	}
	
	public VerticalPanel build() {
		panel.add(buildReferencePeriod());
		panel.add(buildLimitPanel());
		panel.add(buildAggregationColumn());
		panel.add(buildAggregationType());
		return panel;
	}

	
	public HorizontalPanel buildReferencePeriod() {	

		/*** SET WITH THE CURRENT REFERENCE PERIOD **/
		referencePeriodFilter = new HorizontalPanel();
	   	fromDateStore = new ListStore<GaulModelData>();
	    fromDateList = new ComboBox<GaulModelData>();
	    toDateStore = new ListStore<GaulModelData>();
	    toDateList = new ComboBox<GaulModelData>();
	 	
	    fromDateList.setTriggerAction(TriggerAction.ALL);
	    fromDateList.setStore(fromDateStore);
	    fromDateList.setDisplayField("gaulLabel");
	    fromDateList.setWidth(ADAMConstants.SELECTION_LIST_WIDTH);
	 	
	    GaulModelData fromBaseDate = new GaulModelData("2006", "01-01-2006");
    	
    	//GaulModelData fromBaseDate = new GaulModelData("2009", "01-01-2009");
    	//fromDateStore.add(fromBaseDate);
	  
	    //remove following two lines for FPMIS test
	   // fromDateStore.add(new GaulModelData("2012", "01-01-2012"));  
	    ///fromDateStore.add(new GaulModelData("2011", "01-01-2011"));
	    
	    fromDateStore.add(new GaulModelData("2011", "01-01-2011"));
	    fromDateStore.add(new GaulModelData("2010", "01-01-2010"));
    	fromDateStore.add(new GaulModelData("2009", "01-01-2009"));
     	fromDateStore.add(new GaulModelData("2008", "01-01-2008"));
     	fromDateStore.add(new GaulModelData("2007", "01-01-2007"));
     	fromDateStore.add(new GaulModelData("2006", "01-01-2006"));
     	fromDateStore.add(new GaulModelData("2005", "01-01-2005"));
     	fromDateStore.add(new GaulModelData("2004", "01-01-2004"));
     	fromDateStore.add(new GaulModelData("2003", "01-01-2003"));
     	fromDateStore.add(new GaulModelData("2002", "01-01-2002"));
     	fromDateList.setValue(fromBaseDate);
	    	
	     	
	    toDateList.setTriggerAction(TriggerAction.ALL);
	    toDateList.setStore(toDateStore);
	    toDateList.setWidth(ADAMConstants.SELECTION_LIST_WIDTH);
	    	
	    GaulModelData toBaseDate = new GaulModelData("2011", "01-01-2011");
	    toDateStore.add(toBaseDate);
	    
	  //remove following two lines for FPMIS test
	   // toDateStore.add(new GaulModelData("2012", "01-01-2012"));  
	   // toDateStore.add(new GaulModelData("2011", "01-01-2011"));
    	
	    toDateStore.add(new GaulModelData("2010", "01-01-2010"));
	    toDateStore.add(new GaulModelData("2009", "01-01-2009"));
	    toDateStore.add(new GaulModelData("2008", "01-01-2008"));
	    toDateStore.add(new GaulModelData("2007", "01-01-2007"));
    	toDateStore.add(new GaulModelData("2006", "01-01-2006"));
    	toDateStore.add(new GaulModelData("2005", "01-01-2005"));
    	toDateStore.add(new GaulModelData("2004", "01-01-2004"));
    	toDateStore.add(new GaulModelData("2003", "01-01-2003"));
    	toDateStore.add(new GaulModelData("2002", "01-01-2002"));
    	//toDateStore.add(new GaulModelData("2001", "01-01-2001"));
    	//toDateStore.add(new GaulModelData("2000", "01-01-2000"));
	    toDateList.setDisplayField("gaulLabel");
	    toDateList.setValue(toBaseDate);

//	 	referencePeriodFilter.setSpacing(5);
	 	referencePeriodFilter.setHorizontalAlign(HorizontalAlignment.RIGHT);
	 	referencePeriodFilter.setVerticalAlign(VerticalAlignment.MIDDLE);

	 	Html start = new Html("<div class='small-content'>From:</div>");
	 	referencePeriodFilter.add(start);
	 	referencePeriodFilter.add(fromDateList);
	 	Html end = new Html("<div class='small-content'>To:</div>");
	 	referencePeriodFilter.add(end);
	 	referencePeriodFilter.add(toDateList);
	 		
	 	return referencePeriodFilter;
	}
	
	public HorizontalPanel buildAggregationColumn() {	
		HorizontalPanel panel = new HorizontalPanel();
		Html title = new Html("<div class='small-content'>Aggregation Column:</div>");
		title.setWidth(ADAMConstants.SELECTION_TITLE_WIDTH);
		panel.add(title);
		
	   	aggregationColumnStore = new ListStore<GaulModelData>();
	   	aggregationColumnList = new ComboBox<GaulModelData>();

	   	aggregationColumnList.setTriggerAction(TriggerAction.ALL);
	   	aggregationColumnList.setStore(aggregationColumnStore);
	   	aggregationColumnList.setDisplayField("gaulLabel");
	   	aggregationColumnList.setWidth(ADAMConstants.SELECTION_LIST_WIDTH);
	 	
	   	aggregationColumnStore.add(aggregationColumns());
	    aggregationColumnList.setValue(aggregationColumnStore.getAt(0));
	    
	    panel.add(aggregationColumnList);
		return panel;
	}
	
	
	public HorizontalPanel buildAggregationType() {	
		HorizontalPanel panel = new HorizontalPanel();
		Html title = new Html("<div class='small-content'>Aggregation:</div>");
		title.setWidth(ADAMConstants.SELECTION_TITLE_WIDTH);
		panel.add(title);
		
	   	aggregationStore = new ListStore<GaulModelData>();
	   	aggregationList = new ComboBox<GaulModelData>();

	   	aggregationList.setTriggerAction(TriggerAction.ALL);
	   	aggregationList.setStore(aggregationStore);
	   	aggregationList.setDisplayField("gaulLabel");
	   	aggregationList.setWidth(ADAMConstants.SELECTION_LIST_WIDTH);
	 	
	    aggregationStore.add(aggregationTypes());
	    aggregationList.setValue(aggregationStore.getAt(0));
	    
	    panel.add(aggregationList);
		return panel;
	}
	
	private List<GaulModelData> aggregationTypes() {
		List<GaulModelData> data = new ArrayList<GaulModelData>();
		aggregationStore.add( new GaulModelData("Sum", "SUM"));
		aggregationStore.add(new GaulModelData("Average", "AVG"));
		return data;
	}
	
	private List<GaulModelData> aggregationColumns() {
		List<GaulModelData> data = new ArrayList<GaulModelData>();
		aggregationColumnStore.add( new GaulModelData("Disbursement", "usd_disbursement"));
		aggregationColumnStore.add(new GaulModelData("Committed", "usd_commitment"));
		aggregationColumnStore.add(new GaulModelData("Amountuntied", " usd_amountuntied"));
		aggregationColumnStore.add(new GaulModelData("Amounttied", "usd_amounttied"));
		aggregationColumnStore.add(new GaulModelData("Disbursement Defl", "usd_disbursement_defl"));
		aggregationColumnStore.add(new GaulModelData("Committed Defl", "usd_commitment_defl"));
		return data;
	}
	
	public HorizontalPanel buildLimitPanel() {	
		HorizontalPanel panel = new HorizontalPanel();
		Html limit = new Html("<div class='small-content'>Result values:</div>");
		limit.setWidth(ADAMConstants.SELECTION_TITLE_WIDTH);
		panel.add(limit);
		panel.add(limitFilter);
		return panel;
	}

	public static ComboBox<GaulModelData> getFromDateList() {
		return fromDateList;
	}

	public static ComboBox<GaulModelData> getToDateList() {
		return toDateList;
	}

	public Integer getLimit() {
		return limit;
	}

	public TextField<String> getLimitFilter() {
		return limitFilter;
	}

	public static ComboBox<GaulModelData> getAggregationList() {
		return aggregationList;
	}

	public static ComboBox<GaulModelData> getAggregationColumnList() {
		return aggregationColumnList;
	}
	
	
	
	
	
}
