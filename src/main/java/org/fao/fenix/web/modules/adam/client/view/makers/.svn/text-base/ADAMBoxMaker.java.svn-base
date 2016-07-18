package org.fao.fenix.web.modules.adam.client.view.makers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.client.control.ADAMCustomController;
import org.fao.fenix.web.modules.adam.client.control.ADAMMultiSelectionController;
import org.fao.fenix.web.modules.adam.client.control.analyse.ADAMAnalyseDataController;
import org.fao.fenix.web.modules.adam.client.view.ADAMComboMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMSwitchClassificationPanel;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomShowBox;
import org.fao.fenix.web.modules.adam.common.enums.ADAMAnalyseVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.enums.ADAMCurrentVIEW;
import org.fao.fenix.web.modules.adam.common.enums.ADAMSelectedDataset;
import org.fao.fenix.web.modules.adam.common.model.DatasetModel;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;

public class ADAMBoxMaker {

	public static ListStore<GaulModelData> gaulStore;

	public static ComboBox<GaulModelData> gaulList;

	public static ListStore<GaulModelData> donorStore;

	public static ComboBox<GaulModelData> donorList;

	public static ListStore<GaulModelData> sectorStore;

	public static ComboBox<GaulModelData> sectorList;
	
	public static ListStore<GaulModelData> soStore;

	public static ComboBox<GaulModelData> soList;
	
	public static ListStore<GaulModelData> subSectorStore;

	public static ComboBox<GaulModelData> subSectorList;
	
	public static ListStore<GaulModelData> orStore;

	public static ComboBox<GaulModelData> orList;

	private static ListStore<GaulModelData> channelStore;

	private static ComboBox<GaulModelData> channelList;
	
	private static IconButton gaulMultiSelection;
	
	private static IconButton donorMultiSelection;
	
	private static IconButton sectorMultiSelection;
	
	private static IconButton soMultiSelection;
	
    private static IconButton subSectorMultiSelection;
	
	private static IconButton orMultiSelection;
	
	private static Html sectorLabel;
	
	private static Html subSectorLabel;
	
	private static Button apply;

//	private static String LIST_WIDTH = "125px";
	
	
	public static Map<String, String> countriesSelected;
	
	// this is used for the hierarchy
	public static Map<String, String> countriesRegionsSelected;
	
	public static Map<String, String> donorsSelected;
	
	public static Map<String, String> sectorsSelected;
	
	public static Map<String, String> soSelected;
	
	public static Map<String, String> subSectorsSelected;
	
	public static Map<String, String> orSelected;
	
	public static Map<String, String> channelsSelected;
	
	public static ADAMComboMultiSelection adamComboMultiSelection;
	
	public static String selectedCountryFromURL;
	
	public static String selectedDonorFromURL;
	
	public static String selectedSectorFromURL;
	
	public static String selectedChannelFromURL;
	
	public static String selectedSOFromURL;
	
	public static String selectedORFromURL;
	
	public static String selectedSubSectorFromURL;
	
	public static Boolean faoSectorAdded;
	
	public static SelectionChangedListener<GaulModelData> gaulListListener;
	public static SelectionChangedListener<GaulModelData> donorListListener;
	public static SelectionChangedListener<GaulModelData> sectorListListener;
	public static SelectionChangedListener<GaulModelData> soListListener;
	public static SelectionChangedListener<GaulModelData> subSectorListListener;
	public static SelectionChangedListener<GaulModelData> orListListener;
	
	
	static {
		faoSectorAdded = false;
		
		adamComboMultiSelection = new ADAMComboMultiSelection();
		
		gaulStore = new ListStore<GaulModelData>();
		gaulList = new ComboBox<GaulModelData>();
		gaulList.setTriggerAction(TriggerAction.ALL);
		gaulList.setStore(gaulStore);
		gaulList.setDisplayField("gaulLabel");
		gaulList.setEmptyText("Country");
		gaulList.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		gaulStore.add(defaultCodes(true));
		gaulList.setValue(gaulStore.getAt(0));
		countriesSelected = new HashMap<String, String>();
		countriesRegionsSelected = new HashMap<String, String>();
//		ADAMController.fillSelectorStoreADAMBox("Gaul_", gaulStore, gaulList, selectedCountryFromURL);
		
		donorStore = new ListStore<GaulModelData>();
		donorList = new ComboBox<GaulModelData>();
		donorList.setTriggerAction(TriggerAction.ALL);
		donorList.setStore(donorStore);
		donorList.setDisplayField("gaulLabel");
		donorList.setEmptyText("Donor");
		donorList.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		donorStore.add(defaultCodes(true));
		donorList.setValue(donorStore.getAt(0));
		donorsSelected = new HashMap<String, String>();
//		ADAMController.fillSelectorStoreADAMBox("Donor_", donorStore, donorList, selectedDonorFromURL);
		
		sectorStore = new ListStore<GaulModelData>();
		sectorList = new ComboBox<GaulModelData>();
		sectorList.setTriggerAction(TriggerAction.ALL);
		sectorList.setStore(sectorStore);
		sectorList.setDisplayField("gaulLabel");
		sectorList.setEmptyText("Sector");
		sectorList.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		sectorStore.add(defaultCodes(true));		
		//sectorList.setValue(sectorStore.getAt(0));
//		ADAMController.fillSelectorStoreADAMBox("Dac_", sectorStore, sectorList, selectedSelectorFromURL);
		sectorsSelected = new HashMap<String, String>();
		sectorLabel = new Html("<div class='filters-text'>Sector:</div>");
		sectorLabel.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		sectorLabel.setStyleAttribute("padding-left", "8px");
		
		soStore = new ListStore<GaulModelData>();
		soList = new ComboBox<GaulModelData>();
		soList.setTriggerAction(TriggerAction.ALL);
		soList.setStore(soStore);
		soList.setDisplayField("gaulLabel");
		soList.setEmptyText("SO");
		soList.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		soStore.add(defaultCodes(true));
		soList.setValue(soStore.getAt(0));
//		ADAMController.fillSelectorStoreADAMBox("SO_", soStore, soList, selectedSOFromURL);
		soSelected = new HashMap<String, String>();
		

		subSectorStore = new ListStore<GaulModelData>();
		subSectorList = new ComboBox<GaulModelData>();
		subSectorList.setTriggerAction(TriggerAction.ALL);
		subSectorList.setStore(subSectorStore);
		subSectorList.setDisplayField("gaulLabel");
		subSectorList.setEmptyText("Sub-Sector");
		subSectorList.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		subSectorStore.add(defaultCodes(true));
		subSectorList.setValue(subSectorStore.getAt(0));
		subSectorsSelected = new HashMap<String, String>();
		subSectorLabel = new Html("<div class='filters-text'>Sub-Sector:</div>");
		subSectorLabel.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		subSectorLabel.setStyleAttribute("padding-left", "8px");
		
		orStore = new ListStore<GaulModelData>();
		orList = new ComboBox<GaulModelData>();
		orList.setTriggerAction(TriggerAction.ALL);
		orList.setStore(orStore);
		orList.setDisplayField("gaulLabel");
		orList.setEmptyText("OR");
		orList.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		orStore.add(defaultCodes(true));
		orList.setValue(orStore.getAt(0));
		orSelected = new HashMap<String, String>();
				
		channelStore = new ListStore<GaulModelData>();
		channelList = new ComboBox<GaulModelData>();
		channelList.setTriggerAction(TriggerAction.ALL);
		channelList.setStore(channelStore);
		channelList.setDisplayField("gaulLabel");
		channelList.setEmptyText("SO");
		channelList.setWidth(ADAMConstants.FILTERS_LIST_WIDTH);
		channelStore.add(defaultCodes(false));
		channelList.setValue(channelStore.getAt(0));
		//ADAMController.fillSelectorStoreADAMBox("Channel_", channelStore, channelList, selectedChannelFromURL);
		channelsSelected = new HashMap<String, String>();
		
		
		System.out.println("@@@ ADAMBoxMaker.selectedCountryFromURL : "+ADAMBoxMaker.selectedCountryFromURL );
		
		gaulListListener = ADAMController.updateEntity(adamComboMultiSelection, gaulList, countriesSelected, countriesRegionsSelected, "COUNTRY", "gaulListListener");
		donorListListener = ADAMController.updateEntity(adamComboMultiSelection, donorList, donorsSelected, null, "DONOR", "donorListListener");
		sectorListListener = ADAMController.updateEntity(adamComboMultiSelection, sectorList, sectorsSelected, null, "SECTOR", "sectorListListener");
		soListListener = ADAMController.updateEntity(adamComboMultiSelection, soList, soSelected, null, "SO", "soListListener");
		subSectorListListener = ADAMController.updateEntity(adamComboMultiSelection, subSectorList, subSectorsSelected, null, "SUB-SECTOR", "subSectorListListener");
		orListListener = ADAMController.updateEntity(adamComboMultiSelection, orList, orSelected, null, "OR", "orListener");
		
//		donorList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, donorList, donorsSelected, null, "DONOR", "donorListListener"));
//		sectorList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, sectorList, sectorsSelected, null, "SECTOR", "sectorListListener"));
//		soList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, soList, soSelected, null, "SO", "soListListener"));
//		subSectorList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, subSectorList, subSectorsSelected, null, "SUB-SECTOR", "subSectorListListener"));
//		orList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, orList, orSelected, null, "OR", "orListener"));	

		
		
//		gaulList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, gaulList, countriesSelected, countriesRegionsSelected, "COUNTRY", "gaulListListener"));
//		donorList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, donorList, donorsSelected, null, "DONOR", "donorListListener"));
//		sectorList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, sectorList, sectorsSelected, null, "SECTOR", "sectorListListener"));
//		soList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, soList, soSelected, null, "SO", "soListListener"));
//		subSectorList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, subSectorList, subSectorsSelected, null, "SUB-SECTOR", "subSectorListListener"));
//		orList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, orList, orSelected, null, "OR", "orListener"));	
		//channelList.addSelectionChangedListener(ADAMController.updateEntity(adamComboMultiSelection, channelList, channelsSelected, null, "CHANNEL", "channelListListener"));

	
		gaulMultiSelection = new IconButton("multiple_select");
		gaulMultiSelection.setToolTip("Select Multiple Recipient Countries/Geographic Areas");
		gaulMultiSelection.setHeight(20);
		gaulMultiSelection.addSelectionListener(ADAMMultiSelectionController.addGaulMultiselection(adamComboMultiSelection));

		donorMultiSelection = new IconButton("multiple_select");
		donorMultiSelection.setToolTip("Select Multiple Resource Partners");
		donorMultiSelection.setHeight(20);
		donorMultiSelection.addSelectionListener(ADAMMultiSelectionController.addDonorMultiselection(adamComboMultiSelection));

			
		sectorMultiSelection = new IconButton("multiple_select");
		sectorMultiSelection.setToolTip("Select Multiple Sectors");
		sectorMultiSelection.setHeight(20);
		sectorMultiSelection.addSelectionListener(ADAMMultiSelectionController.addSectorMultiselection(adamComboMultiSelection));

		soMultiSelection = new IconButton("multiple_select");
		soMultiSelection.setToolTip("Select Multiple Strategic Objectives");
		soMultiSelection.setHeight(20);
		soMultiSelection.addSelectionListener(ADAMMultiSelectionController.addSOMultiselection(adamComboMultiSelection));
		
		
		subSectorMultiSelection = new IconButton("multiple_select");
		subSectorMultiSelection.setToolTip("Select Multiple Sub-Sectors");
		subSectorMultiSelection.setHeight(20);
		subSectorMultiSelection.addSelectionListener(ADAMMultiSelectionController.addSubSectorMultiselection(adamComboMultiSelection));
		
		orMultiSelection = new IconButton("multiple_select");
		orMultiSelection.setToolTip("Select Multiple Organizational Results");
		orMultiSelection.setHeight(20);
		orMultiSelection.addSelectionListener(ADAMMultiSelectionController.addORMultiselection(adamComboMultiSelection));
		

		apply = new Button("Apply", ADAMController.updateEnties());//(donorList, gaulList, sectorList, subSectorsSelected));
		
		gaulList.setTemplate(getTemplate());  
		donorList.setTemplate(getTemplate());  
		sectorList.setTemplate(getTemplate());  
		soList.setTemplate(getTemplate());  
		subSectorList.setTemplate(getTemplate());  
		orList.setTemplate(getTemplate());  
		//channelList.setTemplate(getTemplate());  
	}
	
	public static List<GaulModelData> defaultCodes(Boolean isMultiSelection){
		List<GaulModelData> list = new ArrayList<GaulModelData>();
		GaulModelData g = new GaulModelData();
		g.setGaulCode("GLOBAL");
		g.setGaulLabel("All Selected");
		list.add(g);
		if ( isMultiSelection ) {
			g = new GaulModelData();
			g.setGaulCode("MULTI");
			g.setGaulLabel("Multi Selection");
			list.add(g);
		}
		return list;
	}
	
	
	/**
	 * @return
	 */
	public static List<DatasetModel> defaultDatasetCodes(){
		List<DatasetModel> list = new ArrayList<DatasetModel>();
		
		DatasetModel d = new DatasetModel();
		d.setName("OECD-Creditor Reporting System (CRS) ");
		d.setCode(ADAMSelectedDataset.ADAM_CRS.name());		
		list.add(d);
		
		d = new DatasetModel();
		d.setName("FAO-FPMIS");
		d.setCode(ADAMSelectedDataset.ADAM_FPMIS.name());
		list.add(d);
		
//		d = new DatasetModel();
//		d.setName("AidData for Non-DAC Resource Partners");
//		d.setCode(ADAMSelectedDataset.AID_DATA.name());
//		list.add(d);
		
//		d = new DatasetModel();
//		d.setName("OECD-CRS & AidData (DAC & Non-DAC)");
//		d.setCode(ADAMSelectedDataset.ADAM_CRS_AID_DATA.name());
//		list.add(d);
		
		return list;
	}
	
	private static GaulModelData defaultDonorCode(){					
		GaulModelData g = new GaulModelData();
		g.setGaulCode("GLOBAL");
		g.setGaulLabel("Global View");
		return g;
	}
	
	private static GaulModelData defaultSectorCode(){					
		GaulModelData g = new GaulModelData();
		g.setGaulCode("GLOBAL");
		g.setGaulLabel("Global View");
		return g;
	}
	
	public static VerticalPanel buildCountrySelector() {
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		//vp.addStyleName("div-country");
		
		Html label = new Html("<div class='filters-text'>By Recipient Country:</div>");
		label.setWidth(ADAMConstants.COUNTRY_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		//p.addStyleName("div-country");
		p.setSpacing(5);
		//Html label = new Html("<div class='filters-text'>By Recipient Country:</div>");
		//label.setWidth(ADAMConstants.FILTERS_LABEL_WIDTH);
		//p.add(label);
		p.add(gaulList);
		p.add(gaulMultiSelection);
	//	p.setStyleAttribute("class", "div-table-col1");
		
		vp.add(p);
		
		System.out.println("@@@ ADAMBoxMaker.buildCountrySelector: gaulStore.getCount() "+ gaulStore.getCount() + " | "+ADAMController.currentlySelectedDatasetCode);
		System.out.println("@@@ ADAMBoxMaker.buildCountrySelector: selectedCountryFromURL : "+selectedCountryFromURL );
		
		if ( gaulStore.getCount() <= 2 ) {
			System.out.println("&&&&&&&&&& buildCountrySelector: GaulStore < 2 CALL TO fillSelectorStoreADAMBox Gaul ...");
			
			ADAMController.fillSelectorStoreADAMBox("Gaul_", gaulStore, gaulList, selectedCountryFromURL, ADAMController.currentlySelectedDatasetCode, "BUILD-COUNTRY_SELECTOR", gaulListListener);
		}
		
		return vp;
	}


	
	public static VerticalPanel buildDonorSelector() {
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		//vp.addStyleName("div-donor");
		
		Html label = new Html("<div class='filters-text'>By Resource Partner:</div>");
		label.setWidth(ADAMConstants.DONOR_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		//p.addStyleName("div-donor");
		p.setSpacing(5);
		//Html label = new Html("<div class='filters-text'>By Resource Partner:</div>");
		//label.setWidth(ADAMConstants.FILTERS_LABEL_WIDTH);
		//p.add(label);
			
		GaulModelData allSelectedModel = donorList.getStore().findModel("gaulCode", "GLOBAL");
		GaulModelData multiSelectionModel = donorList.getStore().findModel("gaulCode", "MULTI");
		
		if(ADAMController.currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {		
			allSelectedModel.setGaulLabel("Please Select");
			donorList.getStore().update(allSelectedModel);
			
			 if(donorList.getValue()!=null && donorList.getValue().getGaulCode().equals("GLOBAL"))
				 donorList.setValue(allSelectedModel);
			    
			 if(multiSelectionModel!=null)
				donorList.getStore().remove(donorList.getStore().findModel("gaulCode", "MULTI"));
			
		} else {		
			allSelectedModel.setGaulLabel("All Selected");
			donorList.getStore().update(allSelectedModel);
			
			if(donorList.getValue()!=null && donorList.getValue().getGaulCode().equals("GLOBAL"))
					donorList.setValue(allSelectedModel);
	
			if(multiSelectionModel==null) {
				GaulModelData g = new GaulModelData();
				g.setGaulCode("MULTI");
				g.setGaulLabel("Multi Selection");
				
				donorList.getStore().insert(g, 1);
			}

		}
		
		p.add(donorList);
		
		if(!ADAMController.currentVIEW.equals(ADAMCurrentVIEW.PROFILES)) {
			p.add(donorMultiSelection);
		}
		
		//p.setStyleAttribute("class", "div-table-col1 ");
		
		if ( donorStore.getCount() <= 2 ) {
			
			ADAMController.fillSelectorStoreADAMBox("Donor_", donorStore, donorList, selectedDonorFromURL, ADAMController.currentlySelectedDatasetCode, "BUILD-DONOR_SELECTOR", donorListListener);
		}
		
		vp.add(p);
		
		return vp;
	}

	public static VerticalPanel buildClassificationSelector() {	
		return (ADAMSwitchClassificationPanel.build(ADAMController.currentVIEW));
	}
	
	public static VerticalPanel buildSectorSelector() {
		
		System.out.println("......... buildSectorSelector ");
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		vp.setId("SECTOR_PANEL");
		//vp.addStyleName("div-country");
	
		/*Html label = new Html("<div class='filters-text'>Sector:</div>");
		label.setId("SECTOR_LABEL");
		label.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);*/
		
		vp.add(sectorLabel);
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		//p.addStyleName("div-country");
		p.setSpacing(5);
		p.add(sectorList);
		p.add(sectorMultiSelection);
		//p.setStyleAttribute("class", "div-table-col1");
		
		vp.add(p);
		
		if ( sectorStore.getCount() <= 2 ) {
			ADAMController.fillSelectorStoreADAMBox("Dac_", sectorStore, sectorList, selectedSectorFromURL, ADAMController.currentlySelectedDatasetCode, "BUILD-SECTOR_SELECTOR", sectorListListener);
		}
		
		return vp;
	}
	
	public static VerticalPanel buildSOSelector() {
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		//vp.addStyleName("div-country");
		
		Html label = new Html("<div class='filters-text'>Strategic Objective (SO):</div>");
		label.setId("SO_LABEL");
		label.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		//p.addStyleName("div-country");
		p.setSpacing(5);
		p.add(soList);
		p.add(soMultiSelection);
		//p.setStyleAttribute("class", "div-table-col1");
		
		vp.add(p);
		
		if ( soStore.getCount() <= 2 ) {
			ADAMController.fillSelectorStoreADAMBox("SO_", soStore, soList, selectedSOFromURL, ADAMController.currentlySelectedDatasetCode, "BUILD-SO_SELECTOR", soListListener);
		}
		
		
		return vp;
	}
	
	/**public static HorizontalPanel buildSectorSelector() {
		HorizontalPanel main = new HorizontalPanel();
		main.setHorizontalAlign(HorizontalAlignment.LEFT);
		main.setVerticalAlign(VerticalAlignment.MIDDLE);
		main.setSpacing(5);
		main.addStyleName("div-sector-classification-holder");
		main.add(new ADAMSwitchSelectionPanel().build(ADAMController.currentVIEW));
		
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		vp.addStyleName("div-sector");
		//vp.addStyleName("div-sector");
		    
	    Html label = new Html("<div class='filters-text'>Sector:</div>");
		label.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(5);
		p.addStyleName("div-sector");
		//p.add(new ADAMSwitchSelectionPanel().build(ADAMController.currentVIEW));
		
	
    	
		//Html label = new Html("<div class='filters-text'>Sector:</div>");
		//label.setWidth(ADAMConstants.SECTOR_LABEL_WIDTH);
		//p.add(label);
		p.add(sectorList);

		p.add(sectorMultiSelection);
//		sectorList.setEnabled(false);
//		sectorMultiSelection.setEnabled(false);
		p.setStyleAttribute("class", "div-table-col1");
		
		if ( sectorStore.getCount() <= 2 ) {
			ADAMController.fillSelectorStoreADAMBox("Dac_", sectorStore, sectorList, selectedSectorFromURL);
		}
		
		vp.add(p);
		
		main.add(vp);
		return main;
	}

	
	public static HorizontalPanel buildSOSelector() {
		
		
		HorizontalPanel main = new HorizontalPanel();
		main.setHorizontalAlign(HorizontalAlignment.LEFT);
		main.setVerticalAlign(VerticalAlignment.MIDDLE);
		main.setSpacing(5);
		main.add(new ADAMSwitchSelectionPanel().build(ADAMController.currentVIEW));
		
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		vp.addStyleName("div-sector");
			    
		Html label = new Html("<div class='filters-text'>By Strategic Objective:</div>");
		label.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.MIDDLE);
		p.setSpacing(5);
		p.addStyleName("div-sector");
			
		p.add(soList);
		p.add(soMultiSelection);
		p.setStyleAttribute("class", "div-table-col1");
		
		if ( soStore.getCount() <= 2 ) {
			ADAMController.fillSelectorStoreADAMBox("SO_", soStore, soList, selectedSOFromURL);
		}
		
		vp.add(p);
		
		main.add(vp);
		return main;
	}	**/
	
	
	/*public static HorizontalPanel buildSOSelector() {
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		p.setSpacing(5);
		p.addStyleName("div-sector");
		p.add(new ADAMSwitchSelectionPanel().build(ADAMController.currentVIEW));
		
		Html label = new Html("<div class='filters-text'>Strategic Objective:</div>");
		label.setWidth(ADAMConstants.SO_LABEL_WIDTH);
		p.add(label);
		p.add(soList);
		p.add(soMultiSelection);
//		sectorMultiSelection.setEnabled(false);
		p.setStyleAttribute("class", "div-table-col1");
		
		if ( soStore.getCount() <= 2 ) {
			ADAMController.fillSelectorStoreADAMBox("SO_", soStore, soList, selectedSOFromURL);
		}
		
		return p;
	}*/
	
	
	/**public static VerticalPanel buildSubSectorSelector() {
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		vp.addStyleName("div-sector");
		
		Html label = new Html("<div class='filters-text'>Sub-Sector:</div>");
		label.setWidth(ADAMConstants.SUB_SECTOR_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		p.addStyleName("div-sector");
		p.setSpacing(5);
		
		p.add(subSectorList);
		p.add(subSectorMultiSelection);
		p.setStyleAttribute("class", "div-table-col1");
		
		
		
		if ( subSectorStore.getCount() <= 2 && sectorList.getValue()!=null && sectorList.getValue().getGaulCode().equals("Dac_9999")) {
			//ADAMController.fillSelectorStoreADAMBox("SubDac_", subSectorStore, subSectorList, selectedSubSectorFromURL);
			List<String> parent = new ArrayList<String>();
			for(String fao: ADAMConstants.faoViewPurposes.keySet()){
				parent.add("%_"+fao);
			}
			ADAMController.fillHierarchicalSelectorStoreADAMBox("SubDac_", subSectorStore, subSectorList, selectedSubSectorFromURL, parent);
			
		}

		vp.add(p);
		
		return vp;
	}
	**/
	
	
	
	public static VerticalPanel buildSubSectorSelector() {
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		//vp.addStyleName("div-sector");
		
		/*Html label = new Html("<div class='filters-text'>Sub-Sector:</div>");
		label.setId("SUB_SECTOR_LABEL");
		label.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);*/
		vp.add(subSectorLabel);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		//p.addStyleName("div-sector");
		p.setSpacing(5);
		
		p.add(subSectorList);
		p.add(subSectorMultiSelection);
		//p.setStyleAttribute("class", "div-table-col1");
		
		vp.add(p);
		
		
		if ( subSectorStore.getCount() <= 2 && sectorList.getValue()!=null && sectorList.getValue().getGaulCode().equals("Dac_9999")) {
			//ADAMController.fillSelectorStoreADAMBox("SubDac_", subSectorStore, subSectorList, selectedSubSectorFromURL);
			List<String> parent = new ArrayList<String>();
			for(String fao: ADAMConstants.faoViewPurposes.keySet()){
				parent.add("%_"+fao);
			}
			ADAMController.fillHierarchicalSelectorStoreADAMBox("SubDac_", subSectorStore, subSectorList, selectedSubSectorFromURL, parent, ADAMController.currentlySelectedDatasetCode, subSectorListListener);
			
		}

		vp.add(p);
		
		return vp;
	}
	
	
	
	
	public static VerticalPanel buildORSelector() {
		VerticalPanel vp = new VerticalPanel();
		vp.setHorizontalAlign(HorizontalAlignment.LEFT);
		vp.setVerticalAlign(VerticalAlignment.BOTTOM);
		//vp.addStyleName("div-sector");
		
		Html label = new Html("<div class='filters-text'>Organizational Result (OR):</div>");
		label.setId("OR_LABEL");
		label.setWidth(ADAMConstants.SECTOR_LIST_WIDTH);
		label.setStyleAttribute("padding-left", "8px");
		vp.add(label);
		
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
	//	p.addStyleName("div-sector");
		p.setSpacing(5);
	
		p.add(orList);
		p.add(orMultiSelection);
		//p.setStyleAttribute("class", "div-table-col1");
		
		
		if ( orStore.getCount() <= 2 && soList.getValue()!=null &&  soList.getValue().getGaulCode().equals("GLOBAL")) {
			//get all ORS 
			ADAMController.fillSelectorStoreADAMBox("OR_", orStore, orList, selectedORFromURL, ADAMController.currentlySelectedDatasetCode, "BUILD-OR_SELECTOR", orListListener);
		}
		
		vp.add(p);
		return vp;
	}
	

	public static HorizontalPanel buildChannelSelector() {
		HorizontalPanel p = new HorizontalPanel();
		p.setHorizontalAlign(HorizontalAlignment.LEFT);
		p.setVerticalAlign(VerticalAlignment.BOTTOM);
		p.setSpacing(5);
		Html label = new Html("<div class='filters-text'>By Implem. Agency:</div>");
		label.setWidth(ADAMConstants.FILTERS_LABEL_WIDTH);
		p.add(label);
		p.add(channelList);
//		p.add(channelMultiSelection);

		//p.setStyleAttribute("class", "div-table-col1");
		return p;
	}
	
	
	public static VerticalPanel buildApplyButton() {
		VerticalPanel p = new VerticalPanel();
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setSpacing(5);
		HorizontalPanel h = new HorizontalPanel();
		h.setHeight(17);
		p.add(h);
		apply.setWidth(55);
		apply.setHeight(22);
		p.add(apply);
		
		return p;
	}
	
	public static VerticalPanel buildChartBoxMenu(ADAMQueryVO qvo, ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, boolean addRemoveChartIcon, SelectionListener<ButtonEvent> removeListener, String removeLabel) { 
		VerticalPanel vPanel = new VerticalPanel();
		//vPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		//vPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		System.out.println("------ buildChartBoxMenu = "+ labelWidth + " | title = "+vo.getTitle());
		//vPanel.add(buildChartBoxTitle(vo, labelWidth, position, color, objectSizeListener, isSmall, addRemoveChartIcon, removeListener, removeLabel));
		vPanel.add(buildBoxHeader(vo, labelWidth, objectSizeListener, isSmall));
		vPanel.add(buildIconsToolbar(qvo, vo, position, color, objectSizeListener, isSmall));
		return vPanel;
	}
	
	public static HorizontalPanel buildBoxHeader(ADAMResultVO vo, String labelWidth, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) { 
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleAttribute("background", "#1B65A4");
		hPanel.setSpacing(5);
		hPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		hPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		System.out.println("------ buildChartBoxTitle = "+ labelWidth + " | title = "+vo.getTitle());

		Html label;
		if (vo != null) {
				label = new Html("<div  class='title-box' style='color:#FFFFFF'>" + vo.getTitle()+"</div>"); //
		}
		else
				label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
		
		label.setWidth(labelWidth);
		hPanel.add(label);
		Html padding = new Html();
		padding.setWidth(100);
		//hPanel.add(padding);
		
///
		Button full = new Button();
		full.setToolTip("Maximize");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close");
			full.setIconStyle("delete");
		}
		
	
		full.addSelectionListener(objectSizeListener);
		
		
		////
		/*Button remove = new Button("Close");
		if(removeLabel!=null)
			remove.setToolTip("Close "+removeLabel);
		remove.setIconStyle("delete");
		
		Button full = new Button();
		full.setToolTip("Maximize");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close");
			full.setIconStyle("delete");
		} else {
			if(addRemoveChartIcon && removeListener!=null)  {
				remove.addSelectionListener(removeListener);
				hPanel.add(remove);
			}
		}
		
		full.addSelectionListener(objectSizeListener);*/

		//System.out.println("------------------------- buildBoxHeader: !ADAMController.selectedTab.equals(ADAMCurrentVIEW.DONORMATRIX) = "+!ADAMController.selectedTab.equals(ADAMCurrentVIEW.DONORMATRIX));
		//System.out.println("------------------------- buildBoxHeader: ADAMController.selectedTab "+ADAMController.selectedTab +" | "+ !ADAMAnalyseDataController.analyseView.equals(ADAMAnalyseVIEW.ANALYSE_PRIORITIES_MATRIX));
		
		//if(!ADAMController.selectedTab.equals(ADAMCurrentVIEW.DONORMATRIX) && !ADAMAnalyseDataController.analyseView.equals(ADAMAnalyseVIEW.ANALYSE_PRIORITIES_MATRIX)){
		
		if(!ADAMController.selectedTab.equals(ADAMCurrentVIEW.DONORMATRIX)){
		
			if(objectSizeListener!=null)
				hPanel.add(full);
		}
			
		
		return hPanel;
	}
	
	
	
	public static HorizontalPanel buildIconsToolbar(ADAMQueryVO qvo, ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean isSmall) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleAttribute("padding-top", "3px");
		//panel.setSpacing(5);
		
		//panel.setSpacing(5);
		panel.setHorizontalAlign(HorizontalAlignment.RIGHT);
		panel.setVerticalAlign(VerticalAlignment.MIDDLE);
		//panel.setStyleAttribute("background", "gray");
		
		//Html padding = new Html();
		//padding.setWidth(paddingWidth);
		//panel.add(padding);
		
		ToolBar toolBar = new ToolBar();  
		toolBar.setStyleAttribute("background", "#F3F5F7"); //very light grey
		toolBar.setBorders(false);
		panel.add(toolBar);
		
		
		
		/*ToolBar menu = new ToolBar();
		menu.setWidth("100%");
		menu.add(new SeparatorToolItem());
		menu.add(new FillToolItem());*/
		
	
		if ( !vo.getSwitchResources().isEmpty() && isSmall) {
			for ( ADAMQueryVO switchVO : vo.getSwitchResources()) {
				
				//MenuItem switchResource = createMenuItem(null);
				Button switchResource = createToolbarButton(null);
				ADAMBoxContent type = ADAMBoxContent.valueOf(switchVO.getResourceType());

				switch(type) {
				case TABLE:
					switchResource.setText("Show Table");
					switchResource.setIconStyle("tableIcon");
				break;

				case CHART: 
					StringBuffer text = new StringBuffer();	
					if(!switchVO.getOutputType().equals(ADAMBoxContent.BAR_STACK_SECTOR_COMPARISON.name()))
						text.append("Show ");
				
					text.append(ADAMConstants.getBoxContentLabel(switchVO.getOutputType(), switchVO));
					switchResource.setText(text.toString());
					
					if(switchVO.getOutputType().equals(ADAMBoxContent.TIMESERIE.name()))
						switchResource.setIconStyle("chartTimeSeriesIcon");
					else if(switchVO.getOutputType().equals(ADAMBoxContent.PIE.name()))
						switchResource.setIconStyle("chartPieIcon");
					else
						switchResource.setIconStyle("chartIcon");
					
				break;
				
				case MAP_COUNTRIES_BY_DONOR_GOOGLE:
					switchResource.setText("Show Map");
					switchResource.setIconStyle("globeMapIcon");
				break;
				}

				switchResource.addSelectionListener(ADAMCustomController.switchResourceListener(position, color, switchVO));
				
				//System.out.println("((( buildChartsButtonsMenu button ADDED type | "+switchVO.getResourceType()+" | " +switchResource.getText() +" | "+vo.getTitle());
				
				//menu.add(switchResource);
				//panel.add(switchResource);
				toolBar.add(switchResource);
				
				//panel.add(new Image("adam-images/grey_separator.png"));
			}
			
			toolBar.add(new SeparatorToolItem());  
		}	
		
		//MenuItem exportImage = createMenuItem("Download Chart");
		Button exportImage = createToolbarButton("Download Chart");
		exportImage.setIconStyle("exportChartIcon"); 
		exportImage.addSelectionListener(ADAMController.exportChartImageListener(vo));
		
		//MenuItem exportExcel = createMenuItem("Download Data");
		Button exportExcel = createToolbarButton("Download Data");
		exportExcel.setIconStyle("exportDataIcon");
		//exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(ADAMController.exportChartExcelListener(qvo, vo));
		//MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		//exportAllResources.setIconStyle("");
		
		Label label = new Label();
		label.setStyleAttribute("font-size", "10px");
		label.setStyleAttribute("color", "#1D4589");
		
		
		if(!vo.getResourceType().equals(ADAMBoxContent.MAP_COUNTRIES_BY_DONOR_GOOGLE.name())){
			//panel.add(exportImage);
			if(vo.getResourceType().equals(ADAMBoxContent.VENN_PRIORITIES.name())){
				label.setText("Click on the Venn Diagram intersections to view the priorities and associated funding allocations.");
			} else {
				toolBar.add(exportImage);
				toolBar.add(exportExcel);	
				
				String stack_details = "Each chart bar is divided by resource partner funding. Hover over for details.";
				
				if(vo.getOutputType().contains("BAR_STACK_"))
					label.setText(stack_details);
				else
					label.setText("Hover over the chart for details.");
				
				toolBar.add(new SeparatorToolItem());  
			}
			
			//menu.add(exportImage);
		}	else  {
			label.setText("Hover over the map for details.");
			toolBar.add(exportExcel);	
			toolBar.add(new SeparatorToolItem());  
		}
		
		toolBar.add(label);
		
		if(vo.getDescription()!=null && vo.getDescription().length() > 0){
			toolBar.add(new SeparatorToolItem());  
			
			if(vo.getOutputType().equals(ADAMBoxContent.VENN_PRIORITIES.name()))
				toolBar.add(addReadMoreButton(vo.getDescription(), "Explanation note on the Venn Diagram intersections", 530, 0, false)); //VENN
			else	
				toolBar.add(addReadMoreButton(vo.getDescription()));	
		}
		
		
		
		
		/*Button remove = new Button("Close");
		if(removeLabel!=null)
			remove.setToolTip("Close "+removeLabel);
		remove.setIconStyle("delete");
		
	
		
		Button full = new Button();
		full.setToolTip("Go to full screen view");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close the view");
			full.setIconStyle("delete");
		} else {
			if(addRemoveChartIcon && removeListener!=null)  {
				remove.addSelectionListener(removeListener);
				panel.add(remove);
			}
		}
		
	
		full.addSelectionListener(objectSizeListener);
		//panel.add(more);
		
//		h1.add(print);
//		panel.add(export);
		if(!ADAMController.currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX))
			panel.add(full);*/
		
		//panel.add(menu);
		
		return panel;
	}
	
	/*public static HorizontalPanel buildChartBoxMenu(ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, boolean addRemoveChartIcon, SelectionListener<ButtonEvent> removeListener, String removeLabel) { 
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		Html label;
		if (vo != null) {
				label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
		}
		else
				label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
		
		label.setWidth(labelWidth);
		if(vo.getDescription()!=null && vo.getDescription().length() > 0){
			h1.add(addInfo(vo.getDescription()));
		}
		h1.add(label);
		h1.add(buildChartsButtonsMenu(vo, position, color, objectSizeListener, isSmall, addRemoveChartIcon, removeListener, removeLabel));
	
		return h1;
	}
	
	private static HorizontalPanel buildChartsButtonsMenu(ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean isSmall, boolean addRemoveChartIcon, SelectionListener<ButtonEvent> removeListener, String removeLabel) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton("<div class='button-options-text'>More Options</div>");
		more.setIconStyle("gear");
		MenuItem showProjects = new MenuItem("Show Projects");
		showProjects.addSelectionListener(ADAMController.showProjects());
		showProjects.setIconStyle("tableIcon");
		
		MenuItem createResource = new MenuItem("Create Resource");
		createResource.addSelectionListener(ADAMCustomController.createCustomResource());
		createResource.setIconStyle("wand");
		
		MenuItem changeInfo = new MenuItem("Change Title/Description");
		changeInfo.addSelectionListener(ADAMCustomController.changeTitleDescription(vo));
		changeInfo.setIconStyle("textEditBtn");
		
		//System.out.println("vo.getSwitchResource(): " + vo.getSwitchResources());
		
		//System.out.println("((( buildChartsButtonsMenu button vo.getSwitchResources() | "+vo.getSwitchResources().size());
		
		if ( !vo.getSwitchResources().isEmpty() && isSmall) {
			for ( ADAMQueryVO switchVO : vo.getSwitchResources()) {
				
				MenuItem switchResource = new MenuItem();
				ADAMBoxContent type = ADAMBoxContent.valueOf(switchVO.getResourceType());

				switch(type) {
				case TABLE:
					switchResource.setText("See as Table");
					switchResource.setIconStyle("tableIcon");
				break;

				case CHART: 
					StringBuffer text = new StringBuffer();	
					if(!switchVO.getOutputType().equals(ADAMBoxContent.BAR_STACK_SECTOR_COMPARISON.name()))
						text.append("See as ");
				
					text.append(ADAMConstants.getBoxContentLabel(switchVO.getOutputType(), switchVO));
					switchResource.setText(text.toString());
					
					if(switchVO.getOutputType().equals(ADAMBoxContent.TIMESERIE))
						switchResource.setIconStyle("chartTimeSeriesIcon");
					else
						switchResource.setIconStyle("chartIcon");
					
				break;
				
				case MAP_COUNTRIES_BY_DONOR_GOOGLE:
					switchResource.setText("See as Map");
					switchResource.setIconStyle("mapIcon");
				break;
				}

				switchResource.addSelectionListener(ADAMCustomController.switchResource(position, color, switchVO));
				
				//System.out.println("((( buildChartsButtonsMenu button ADDED type | "+switchVO.getResourceType()+" | " +switchResource.getText() +" | "+vo.getTitle());
				
				moreMenu.add(switchResource);
			}
		}
		
//		moreMenu.add(showProjects);
//		moreMenu.add(createResource);
//		moreMenu.add(changeInfo);
		more.setMenu(moreMenu);
//		Button print = new Button("Print");
//		print.setIconStyle("print");
		
		// Export Menu
//		SplitButton export = new SplitButton("Export...");
//		export.setIconStyle("export");
		MenuItem exportImage = new MenuItem("Export Image");
		exportImage.setIconStyle("pdfIcon");
		
		//System.out.println("&&&&&&&&& buildChartsButtonsMenu: Export to Excel: "+vo.getTitle());
		exportImage.addSelectionListener(ADAMController.exportChartImage(vo));
		MenuItem exportExcel = new MenuItem("Export Excel");
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(ADAMController.exportChartExcel(vo));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");
		
		moreMenu.add(exportImage);
		moreMenu.add(exportExcel);

//		Menu exportMenu = new Menu();
//		exportMenu.add(exportImage);
//		exportMenu.add(exportExcel);
//		exportMenu.add(exportAllResources);
//		export.setMenu(exportMenu);
	
		Button remove = new Button("Close");
		if(removeLabel!=null)
			remove.setToolTip("Close "+removeLabel);
		remove.setIconStyle("delete");
		
	
		
		Button full = new Button();
		full.setToolTip("Go to full screen view");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close the view");
			full.setIconStyle("delete");
		} else {
			if(addRemoveChartIcon && removeListener!=null)  {
				remove.addSelectionListener(removeListener);
				panel.add(remove);
			}
		}
		
	
		full.addSelectionListener(objectSizeListener);
		panel.add(more);
		
//		h1.add(print);
//		panel.add(export);
		if(!ADAMController.currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX))
			panel.add(full);
		return panel;
	}
	*/
		
	
	public static HorizontalPanel buildSmallCustomChartBoxMenu(ADAMQueryVO qvo, ADAMResultVO vo, String labelWidth, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, String position) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		Html label;
		if (vo != null) {
				label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
		}
		else
				label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
		
		label.setWidth(labelWidth);
		if(vo.getDescription()!=null && vo.getDescription().length() > 0){
			h1.add(addInfo(vo.getDescription()));
		}
		h1.add(label);
		
		// Export Menu
		Button export = new Button("Export...");
		export.setIconStyle("export");
		MenuItem exportImage = new MenuItem("Export Image");
		exportImage.setIconStyle("pdfIcon");
		exportImage.addSelectionListener(ADAMController.exportChartImage(vo));
		MenuItem exportExcel = new MenuItem("Export Excel");
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(ADAMController.exportChartExcel(qvo,vo));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");

		Menu exportMenu = new Menu();
		exportMenu.add(exportImage);
		exportMenu.add(exportExcel);
//		exportMenu.add(exportAllResources);
		export.setMenu(exportMenu);
		
		Button full = new Button();
		full.setToolTip("Go to full screen view");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close the view");
			full.setIconStyle("delete");
		}
		
		full.addSelectionListener(objectSizeListener);
		
		Button close = new Button("Close");
		close.setToolTip("Close view");
		
		close.setIconStyle("delete");
		close.addSelectionListener(ADAMCustomController.close(qvo, position, ADAMBoxContent.CUSTOM_CHART));
		
		
//		h1.add(more);
		
//		h1.add(print);
		h1.add(export);
		h1.add(full);
		h1.add(close);
		
		return h1;
	}
	
	public static HorizontalPanel buildCustomChartBoxMenu(ADAMQueryVO qvo, ADAMResultVO vo, String labelWidth, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, ADAMCustomShowBox adamCustomShowBox) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
	
		
//		Button print = new Button("Print");
//		print.setIconStyle("print");
		
		// Export Menu
		Button export = new Button("Export...");
		export.setIconStyle("export");
		MenuItem exportImage = new MenuItem("Export Image");
		exportImage.setIconStyle("pdfIcon");
		exportImage.addSelectionListener(ADAMController.exportChartImage(vo));
		MenuItem exportExcel = new MenuItem("Export Excel");
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(ADAMController.exportChartExcel(qvo,vo));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");

		Menu exportMenu = new Menu();
		exportMenu.add(exportImage);
		exportMenu.add(exportExcel);
//		exportMenu.add(exportAllResources);
		export.setMenu(exportMenu);
		
		Button full = new Button();
		full.setToolTip("Go to full screen view");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close the view");
			full.setIconStyle("delete");
		}
		
		full.addSelectionListener(objectSizeListener);

		Button addResource = new Button("Add to View");
		addResource.setToolTip("Add the Chart to the current view");
		addResource.setIconStyle("addIcon");
		addResource.addSelectionListener(ADAMCustomController.addCustomChart(qvo, vo, adamCustomShowBox));


//		h1.add(print);
		h1.add(addResource);
		h1.add(export);
		h1.add(full);
		return h1;
	}
	

	public static HorizontalPanel buildTableBoxMenu(ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled, boolean addRemoveTableIcon, SelectionListener<ButtonEvent> removeListener, String removeLabel) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		Html label;
		if (vo != null) {
				label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
		}
		else
				label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
		
		label.setWidth("380px");
		if(vo.getDescription()!=null && vo.getDescription().length() > 0){
			h1.add(addInfo(vo.getDescription()));
		}
		h1.add(label);
		h1.add(buildTableBoxMenu(vo, position, color, objectSizeListener, isSmall, closeEnabled, addRemoveTableIcon, removeListener, removeLabel));
	
		return h1;
	}
	
	
	
	
	
	private static HorizontalPanel buildTableBoxMenu(ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled, boolean addRemoveTableIcon, SelectionListener<ButtonEvent> removeListener, String removeLabel) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton("<div class='button-options-text'>More Options</div>");
		more.setIconStyle("gear");
		MenuItem showProjects = new MenuItem("Show Projects");
		//showProjects.addSelectionListener(ADAMController.showProjects());
		showProjects.setIconStyle("tableIcon");
		
		MenuItem createResource = new MenuItem("Create Resource");
		createResource.addSelectionListener(ADAMCustomController.createCustomResource());
		createResource.setIconStyle("wand");
		
		MenuItem changeInfo = new MenuItem("Change Title/Description");
		changeInfo.addSelectionListener(ADAMCustomController.changeTitleDescription(vo));
		changeInfo.setIconStyle("textEditBtn");
		
		//System.out.println("vo.getSwitchResource(): " + vo.getSwitchResources());
		if ( !vo.getSwitchResources().isEmpty() && isSmall) {
			for ( ADAMQueryVO switchVO : vo.getSwitchResources()) {
//				System.out.println("chart swtich resource button");
				
				MenuItem switchResource = new MenuItem();
				ADAMBoxContent type = ADAMBoxContent.valueOf(switchVO.getResourceType());

				switch(type) {
				case TABLE:
					switchResource.setText("See as Table");
					switchResource.setIconStyle("tableIcon");
				break;

				case CHART:
					switchResource.setText("See as " + ADAMConstants.getBoxContentLabel(switchVO.getOutputType(), switchVO));
					switchResource.setIconStyle("chartIcon");
				break;
				
				case MAP_COUNTRIES_BY_DONOR_GOOGLE:
					switchResource.setText("See as Map");
					switchResource.setIconStyle("mapIcon");
				break;
				}

				switchResource.addSelectionListener(ADAMCustomController.switchResource(position, color, switchVO));
				moreMenu.add(switchResource);
			}
		}
		
		more.setMenu(moreMenu);

		

		MenuItem exportExcel = new MenuItem("Export Excel");
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(ADAMController.exportExcelTable(vo, true));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");

		moreMenu.add(exportExcel);

		Button remove = new Button("Close");
		if(removeLabel!=null)
			remove.setToolTip("Close "+removeLabel);
		remove.setIconStyle("delete");
		
		
		Button full = new Button();
		full.setToolTip("Go to full screen view");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close the view");
			full.setIconStyle("delete");
			if ( !closeEnabled ) 
				full.setEnabled(false);
		} else {
			if(addRemoveTableIcon && removeListener!=null)  {
				remove.addSelectionListener(removeListener);
				panel.add(remove);
			}
				
		}
		
	
		full.addSelectionListener(objectSizeListener);
		panel.add(more);

		
		if(objectSizeListener!=null)
			panel.add(full);
		
		
		
		return panel;
	}
	
	public static Button addReadMoreButton(String text,  String header, int height, int width, final Boolean setPanelHeight) {
		Button readMore = createToolbarButton("Read More");
		readMore.setIconStyle("adam_info"); 
		readMore.addSelectionListener(ADAMController.showReadMoreSelectionListener(text, header, height, width,setPanelHeight));
		return readMore;
	}
	
	protected static Button addReadMoreButton(String text) {
		Button readMore = createToolbarButton("Read More");
		readMore.setIconStyle("adam_info"); 
		readMore.addSelectionListener(ADAMController.showReadMoreWindow(text));
		return readMore;
	}
	
	
	public static Button addReadMoreButtonForUrl(String url) {
		Button readMore = createToolbarButton("Read More");
		readMore.setIconStyle("adam_info"); 
		readMore.addSelectionListener(ADAMController.showReadMoreForUrl(url));
		return readMore;
	}
	
	public static HorizontalPanel addInfo(String text) {
		HorizontalPanel panel = new HorizontalPanel();
		IconButton icon = new IconButton("adam_info");
		icon.setToolTip("Additional Information");
		icon.addSelectionListener(ADAMController.showInfo(text));
		panel.add(icon);
		return panel;
	}
	
	public static HorizontalPanel addInfo(String text, String header, int height) {
		HorizontalPanel panel = new HorizontalPanel();
		IconButton icon = new IconButton("adam_info");
		icon.setToolTip(header);
		icon.addSelectionListener(ADAMController.showInfo(text, header, height));
		panel.add(icon);
		return panel;
	}
	
	public static HorizontalPanel addPrioritiesDisclaimer() {
		HorizontalPanel panel = new HorizontalPanel();
		IconButton icon = new IconButton("adam_info");
		icon.setToolTip("View Priorities Disclaimer");
		icon.addSelectionListener(ADAMController.showPrioritiesDisclaimer());
		panel.add(icon);
		return panel;
	}
	
	public static VerticalPanel buildLinksPanel(Map<String, String> gaulMap, Map<String, String> donorsMap, Map<String, String> channelsMap, Map<String, String> dacMap, Boolean isSmall) {

		int counter = 0;
		int maxLinks = 2;
		
		if ( !isSmall )
			maxLinks = 5;

		VerticalPanel p = new VerticalPanel();
		p.setSpacing(3);
		p.setVerticalAlign(VerticalAlignment.TOP);
		p.setSize("175px", "200px");

		HTML countryLabel = new HTML("<div class='link-title'>Geographic Area</div>");
		HTML donorLabel = new HTML("<div class='link-title'>Donor</div>");
		HTML channelsLabel = new HTML("<div class='link-title'>Channels</div>");
		HTML sectorLabel = new HTML("<div class='link-title'>Sector</div>");

		if ( gaulMap != null ) {
			if ( !gaulMap.isEmpty() ) {
				p.add(countryLabel);
				LinkedHashMap<String, String>  sortedCountryMap = ADAMController.sortByValues(gaulMap);
				for (String code : sortedCountryMap.keySet()) {
					if (counter++ < maxLinks)
						p.add(tinyLink(code, sortedCountryMap.get(code), "GAUL"));
					else
						break;
				}
				counter = 0;
				p.add(addShowAllLink(sortedCountryMap, "GAUL", "Geographic Area"));
			}
		}

		if ( donorsMap != null ) {
			if ( !donorsMap.isEmpty() ) {
				p.add(donorLabel);
				LinkedHashMap<String, String>  sortedDonorMap = ADAMController.sortByValues(donorsMap);
				for (String code : sortedDonorMap.keySet()) {
					if (counter++ < maxLinks)
						p.add(tinyLink(code, sortedDonorMap.get(code), "DONOR"));
					else
						break;
				}
				counter = 0;
				p.add(addShowAllLink(sortedDonorMap, "DONOR", "Donor"));
			}
		}
		
		

		if ( dacMap != null ) {
			if ( !dacMap.isEmpty() ) {
				p.add(sectorLabel);
				LinkedHashMap<String, String>  sortedDACMap = ADAMController.sortByValues(dacMap);
				for (String code : sortedDACMap.keySet()) {
					if (counter++ < maxLinks)
						p.add(tinyLink(code, sortedDACMap.get(code), "DAC"));
					else
						break;
				}
				counter = 0;
				p.add(addShowAllLink(sortedDACMap, "DAC", "Sector"));
			}
		}
		

		return p;
	}
	
	private static HTML addShowAllLink(final LinkedHashMap<String, String>  sortedMap, String type, String title) {
		HTML link = new HTML();
		link.setHTML("<div class='link'>show all...</div>");
		

		link.addClickHandler(showAllLinks(sortedMap, type, title));
		return link;
	}
	
	
	
	private static ClickHandler showAllLinks(final LinkedHashMap<String, String>  sortedMap, final String type, final String title) {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window window = new Window();
				HTML htmlTitle = new HTML("<div class='link-title'>" + title + "</div>");
				window.add(htmlTitle);
				window.setHeading(title);
				window.setHeight(450);
				window.setWidth(450);
				window.setScrollMode(Scroll.AUTO);
				
				for(String code : sortedMap.keySet()) {
					HTML html = new HTML();
					String label = sortedMap.get(code);
					html.setHTML("<div class='link'>" + label + "</div>");
					
					if (type.equals("GAUL")) {
						html.addClickHandler(ADAMController.updateCountry(code, label, window));
					} else if (type.equals("DONOR")) {
						html.addClickHandler(ADAMController.updateDonor(code, label, window));
					} else if (type.equals("DAC")) {

					}
					
					window.add(html);
				}
				
				
				window.show();
			}
		};
	}
	

	
	public static HTML tinyLink(String code, String label, String type) {
		HTML html = new HTML();
		int maxLenght = 30;
		if (label.length() > maxLenght)
			html.setHTML("<div class='link'>" + label.substring(0, maxLenght) + "...</div>");
		else
			html.setHTML("<div class='link'>" + label + "</div>");
		html.setTitle(label);
		html.setWidth("170px");
		if (type.equals("GAUL")) {
			html.addClickHandler(ADAMController.updateCountry(code, label, null));
		} else if (type.equals("DONOR")) {
			html.addClickHandler(ADAMController.updateDonor(code, label, null));
		} else if (type.equals("DAC")) {

		}
		return html;
	}
	
	public static HTML showLinks(String code, String label, String type) {
		HTML html = new HTML();
		int maxLenght = 30;
		if (label.length() > maxLenght)
			html.setHTML("<div class='link'>" + label.substring(0, maxLenght) + "...</div>");
		else
			html.setHTML("<div class='link'>" + label + "</div>");
		html.setTitle(label);
		html.setWidth("170px");
		if (type.equals("GAUL")) {

		} else if (type.equals("DONOR")) {
			html.addClickHandler(ADAMController.updateDonor(code, label, null));
		} else if (type.equals("DAC")) {

		}
		return html;
	}

	public static Html buildKeyMessages() {
		Html label = new Html(
				"<table width='650px' align='center'><tr><td class='small-content' colspan='6' align='center'><b><u>United Republic of Tanzania - Key Facts</u></b></td></tr><tr><td align='center'><b>GDP Pro Capita</b></td><td><b>Total Population</b></td><td align='center'><b>Undernourished Children</b></td><td align='center'><b>Average Income</b></td><td align='center'><b>Pollution</b></td><td align='center'><b>Favourite Colour</b></td></tr><tr><td align='center'>32.43</td><td align='center'>27.500</td><td align='center'>71%</td><td align='center'>39.87</td><td align='center'>86%</td><td align='center'>Red</td></tr></table>");
		label.setWidth("650px");
		return label;
	}
	
	
	public static Html createHtmlTable(ADAMResultVO vo) {
		Html table = new Html();
		// TODO: PUT A HTML TABLE IN THE BEAN
		table.setHtml(vo.getDescription());
		return table;
		
	}
	
	public static VerticalPanel buildGoogleMapsMenu(ADAMQueryVO qvo, ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) { 
		VerticalPanel vPanel = new VerticalPanel();
		//vPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		//vPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		System.out.println("------ buildGoogleMapsMenu = "+ labelWidth + " | title = "+vo.getTitle());
		//vPanel.add(buildChartBoxTitle(vo, labelWidth, position, color, objectSizeListener, isSmall, addRemoveChartIcon, removeListener, removeLabel));
		vPanel.add(buildBoxHeader(vo, labelWidth, objectSizeListener, isSmall));
		vPanel.add(buildIconsToolbar(qvo,vo, position, color, objectSizeListener, isSmall));
		return vPanel;
	}
	
	
	/*public static HorizontalPanel buildChartBoxTitle(ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, boolean addRemoveChartIcon, SelectionListener<ButtonEvent> removeListener, String removeLabel) { 
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleAttribute("background", "#1B65A4");
		hPanel.setSpacing(5);
		hPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		hPanel.setVerticalAlign(VerticalAlignment.MIDDLE);
		
		System.out.println("------ buildChartBoxTitle = "+ labelWidth + " | title = "+vo.getTitle());

		Html label;
		if (vo != null) {
				label = new Html("<div  class='title-box' style='color:#FFFFFF'>" + vo.getTitle()+"</div>"); //
		}
		else
				label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
		
		if(vo.getDescription()!=null && vo.getDescription().length() > 0){
			hPanel.add(addInfo(vo.getDescription()));
		}
		
		label.setWidth(labelWidth);
		hPanel.add(label);
		Html padding = new Html();
		padding.setWidth(100);
		//hPanel.add(padding);
		
		Button remove = new Button("Close");
		if(removeLabel!=null)
			remove.setToolTip("Close "+removeLabel);
		remove.setIconStyle("delete");
		
		Button full = new Button();
		full.setToolTip("Maximize");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close");
			full.setIconStyle("delete");
		} else {
			if(addRemoveChartIcon && removeListener!=null)  {
				remove.addSelectionListener(removeListener);
				hPanel.add(remove);
			}
		}
		
		full.addSelectionListener(objectSizeListener);

		if(!ADAMController.currentVIEW.equals(ADAMCurrentVIEW.DONORMATRIX))
			hPanel.add(full);
		
		return hPanel;
	}*/
	

	
	/*public static HorizontalPanel buildGoogleMapsMenu(ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		Html label;
		if (vo != null) {
			label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
		}
		else
			label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
		label.setWidth(labelWidth);
		if(vo.getDescription()!=null && vo.getDescription().length() > 0){
			h1.add(addInfo(vo.getDescription()));
		}
		h1.add(label);
		h1.add(buildGoogleMapsButtonsMenu(vo, position, color, objectSizeListener, isSmall));
		return h1;
	}
	
	private static HorizontalPanel buildGoogleMapsButtonsMenu(ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, Boolean isSmall) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton("<div class='button-options-text'>More Options</div>");
		more.setIconStyle("gear");

		MenuItem changeInfo = new MenuItem("Change Title/Description");
		changeInfo.addSelectionListener(ADAMCustomController.changeTitleDescription(vo));
		changeInfo.setIconStyle("textEditBtn");
		
		//System.out.println("*** buildGoogleMapsButtonsMenu vo.getSwitchResources().size() = "+vo.getSwitchResources().size());
		
		if ( !vo.getSwitchResources().isEmpty() && isSmall) {
			for ( ADAMQueryVO switchVO : vo.getSwitchResources()) {
				//System.out.println("map swtich resource button");
				
				MenuItem switchResource = new MenuItem();
				ADAMBoxContent type = ADAMBoxContent.valueOf(switchVO.getResourceType());
				
				switch(type) {
				case TABLE:
					switchResource.setText("See as Table");
					switchResource.setIconStyle("tableIcon");
				break;

				case CHART:
					switchResource.setText("See as " + ADAMConstants.getBoxContentLabel(switchVO.getOutputType(), switchVO));
					
					if(switchVO.getOutputType().equals(ADAMBoxContent.TIMESERIE))
						switchResource.setIconStyle("chartTimeSeriesIcon");
					else
						switchResource.setIconStyle("chartIcon");
					
				break;
				
				case MAP_COUNTRIES_BY_DONOR_GOOGLE:
					switchResource.setText("See as Map");
					switchResource.setIconStyle("mapIcon");
				break;
				}

				switchResource.addSelectionListener(ADAMCustomController.switchResource(position, color, switchVO));
				
				//System.out.println("*** buildGoogleMapsButtonsMenu button ADDED type | "+switchVO.getResourceType()+" | " +switchResource.getText() +" | "+vo.getTitle());
				moreMenu.add(switchResource);
			}
		}
		

		more.setMenu(moreMenu);

		
//		MenuItem exportImage = new MenuItem("Export Image");
//		exportImage.setIconStyle("pdfIcon");
//		exportImage.addSelectionListener(ADAMController.exportChartImage(vo));
		MenuItem exportExcel = new MenuItem("Export Excel");
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(ADAMController.exportChartExcel(vo));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");
		
//		moreMenu.add(exportImage);
		moreMenu.add(exportExcel);

		
		Button full = new Button();
		full.setToolTip("Go to full screen view");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close the view");
			full.setIconStyle("delete");
		}
		
	
		full.addSelectionListener(objectSizeListener);
//		full.setEnabled(false);
		panel.add(more);

		panel.add(full);
		return panel;
	}*/
	
	
	public static HorizontalPanel buildFPMISProjects(ADAMResultVO vo, String labelWidth, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled) {
		HorizontalPanel h1 = new HorizontalPanel();
		h1.setHorizontalAlign(HorizontalAlignment.LEFT);
		h1.setVerticalAlign(VerticalAlignment.MIDDLE);
		h1.setSpacing(5);
		Html label;
		if (vo != null) {
				label = new Html("<div class='title-box'>" + vo.getTitle() + "</div>");
		}
		else
				label = new Html("<b><font size='2pt'><u>&nbsp;</u></font></b>");
		
		label.setWidth(labelWidth);
		if(vo.getDescription()!=null && vo.getDescription().length() > 0){
			h1.add(addInfo(vo.getDescription()));
		}
		h1.add(label);
		h1.add(buildFPMISProjectsBoxMenu(vo, position, color, objectSizeListener, isSmall, closeEnabled));
	
		return h1;
	}
	
	private static HorizontalPanel buildFPMISProjectsBoxMenu(ADAMResultVO vo, String position, String color, SelectionListener<ButtonEvent> objectSizeListener, boolean isSmall, Boolean closeEnabled) {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		Menu moreMenu = new Menu();
		SplitButton more = new SplitButton("<div class='button-options-text'>More Options</div>");
		more.setIconStyle("gear");
		MenuItem showProjects = new MenuItem("Show Projects");
		//showProjects.addSelectionListener(ADAMController.showProjects());
		showProjects.setIconStyle("tableIcon");
		
		MenuItem createResource = new MenuItem("Create Resource");
		createResource.addSelectionListener(ADAMCustomController.createCustomResource());
		createResource.setIconStyle("wand");
		
		MenuItem changeInfo = new MenuItem("Change Title/Description");
		changeInfo.addSelectionListener(ADAMCustomController.changeTitleDescription(vo));
		changeInfo.setIconStyle("textEditBtn");
		
		//System.out.println("vo.getSwitchResource(): " + vo.getSwitchResources());
		if ( !vo.getSwitchResources().isEmpty() && isSmall) {
			for ( ADAMQueryVO switchVO : vo.getSwitchResources()) {
				System.out.println("chart swtich resource button");
				
				MenuItem switchResource = new MenuItem();
				ADAMBoxContent type = ADAMBoxContent.valueOf(switchVO.getResourceType());

				switch(type) {
				case TABLE:
					switchResource.setText("See as Table");
					switchResource.setIconStyle("tableIcon");
				break;

				case CHART:
					switchResource.setText("See as " + ADAMConstants.getBoxContentLabel(switchVO.getOutputType(), switchVO));
					switchResource.setIconStyle("chartIcon");
				break;
				
				case MAP_COUNTRIES_BY_DONOR_GOOGLE:
					switchResource.setText("See as Map");
					switchResource.setIconStyle("mapIcon");
				break;
				}

				switchResource.addSelectionListener(ADAMCustomController.switchResource(position, color, switchVO));
				moreMenu.add(switchResource);
			}
		}
		
		more.setMenu(moreMenu);

		

		MenuItem exportExcel = new MenuItem("Export Excel");
		exportExcel.setIconStyle("sendToExcel");
		exportExcel.addSelectionListener(ADAMController.exportExcelTable(vo, true));
		MenuItem exportAllResources = new MenuItem("Export Visible Resources");
		exportAllResources.setIconStyle("");
		

		moreMenu.add(exportExcel);
		
		moreMenu.setEnabled(false);

		
		Button full = new Button();
		full.setToolTip("Go to full screen view");
		full.setIconStyle("arrow_out");
		if (!isSmall) {
			full = new Button("Close");
			full.setToolTip("Close the view");
			full.setIconStyle("delete");
			if ( !closeEnabled ) 
				full.setEnabled(false);
		}
		
	
		full.addSelectionListener(objectSizeListener);
		panel.add(more);

		panel.add(full);
		return panel;
	}

	
	public static void updateCountrySelector() {	
		//selectedCountryFromURL = gaulList.getValue().getGaulLabel(); //has to be the label as the code will be dataset specific
		selectedCountryFromURL = null; // so that the selector is set to <All Selected> instead of the above
		gaulStore.removeAll();
		gaulStore.add(defaultCodes(true));		
		ADAMBoxMaker.countriesSelected.clear();
		
		System.out.println(" ##### ADAMBoxMaker: update-COUNTRY-Selector "+ADAMController.currentlySelectedDatasetCode + " | "+gaulStore.getCount());
		
		ADAMController.fillSelectorStoreADAMBox("Gaul_", gaulStore, gaulList, selectedCountryFromURL, ADAMController.currentlySelectedDatasetCode, "UPDATE_COUNTRY", gaulListListener);
	}
	
	public static void updateDonorSelector() {	
		//selectedDonorFromURL = donorList.getValue().getGaulLabel(); //has to be the label as the code will be dataset specific
		selectedDonorFromURL = null; // so that the selector is set to <All Selected> instead of the above
		donorStore.removeAll();
		donorStore.add(defaultCodes(true));		
		ADAMBoxMaker.donorsSelected.clear();
		
		System.out.println(" ##### ADAMBoxMaker: update-DONOR-Selector "+ADAMController.currentlySelectedDatasetCode + " | "+donorStore.getCount());
		
		ADAMController.fillSelectorStoreADAMBox("Donor_", donorStore, donorList, selectedDonorFromURL, ADAMController.currentlySelectedDatasetCode, "UPDATE_DONOR", donorListListener);
	}
	
	public static void updateSectorSelector() {	
		//selectedSectorFromURL = sectorList.getValue().getGaulLabel(); //has to be the label as the code will be dataset specific
		selectedSectorFromURL = null; // so that the selector is set to <All Selected> instead of the above
		sectorStore.removeAll();
		sectorStore.add(defaultCodes(true));	
		sectorList.setValue(sectorStore.getAt(0));
		sectorLabel.setHtml("");
		
		ADAMBoxMaker.sectorsSelected.clear();
		
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
			sectorLabel.setHtml("<div class='filters-text'>Strategic Objective (SO):</div>");
		else
			sectorLabel.setHtml("<div class='filters-text'>Sector:</div>");
		
		System.out.println(" ##### ADAMBoxMaker: update-SECTOR-Selector "+ADAMController.currentlySelectedDatasetCode + " | "+sectorStore.getCount());
		
		ADAMController.fillSelectorStoreADAMBox("Dac_", sectorStore, sectorList, selectedSectorFromURL, ADAMController.currentlySelectedDatasetCode, "UPDATE_SECTOR", sectorListListener);
	}
	
	public static void updateSubSectorSelector() {	
		//selectedSubSectorFromURL = subSectorList.getValue().getGaulLabel(); //has to be the label as the code will be dataset specific
		selectedSubSectorFromURL = null; // so that the selector is set to <All Selected> instead of the above
		subSectorStore.removeAll();
		subSectorStore.add(defaultCodes(true));		
		subSectorList.setValue(subSectorStore.getAt(0));
		subSectorLabel.setHtml("");
		
		ADAMBoxMaker.subSectorsSelected.clear();
		
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
			subSectorLabel.setHtml("<div class='filters-text'>Organizational Result (OR):</div>");
		else
			subSectorLabel.setHtml("<div class='filters-text'>Sub-Sector:</div>");
		
		System.out.println(" ##### ADAMBoxMaker: update-SECTOR-Selector "+ADAMController.currentlySelectedDatasetCode + " | "+sectorStore.getCount());
			
		ADAMController.fillSelectorStoreADAMBox("SubDac_", subSectorStore, sectorList, selectedSectorFromURL, ADAMController.currentlySelectedDatasetCode, "UPDATE_SUB_SECTOR", subSectorListListener);
	}
	
	public static void updateSubSectorSelectorLabels() {	
		if(ADAMController.currentlySelectedDatasetCode.equals(ADAMSelectedDataset.ADAM_FPMIS))
			subSectorLabel.setHtml("<div class='filters-text'>Organizational Result (OR):</div>");
		else
			subSectorLabel.setHtml("<div class='filters-text'>Sub-Sector:</div>");
	}
	
	public static ListStore<GaulModelData> getGaulStore() {
		return gaulStore;
	}

	public static void setGaulStore(ListStore<GaulModelData> gaulStore) {
		ADAMBoxMaker.gaulStore = gaulStore;
	}

	public static ComboBox<GaulModelData> getGaulList() {
		return gaulList;
	}

	public static void setGaulList(ComboBox<GaulModelData> gaulList) {
		ADAMBoxMaker.gaulList = gaulList;
	}

	public static ListStore<GaulModelData> getDonorStore() {
		return donorStore;
	}

	public static void setDonorStore(ListStore<GaulModelData> donorStore) {
		ADAMBoxMaker.donorStore = donorStore;
	}

	public static ComboBox<GaulModelData> getDonorList() {
		return donorList;
	}

	public static void setDonorList(ComboBox<GaulModelData> donorList) {
		ADAMBoxMaker.donorList = donorList;
	}

	public static ListStore<GaulModelData> getSectorStore() {
		return sectorStore;
	}

	public static void setSectorStore(ListStore<GaulModelData> sectorStore) {
		ADAMBoxMaker.sectorStore = sectorStore;
	}

	public static ListStore<GaulModelData> getSubSectorStore() {
		return subSectorStore;
	}

	public static void setSubSectorStore(ListStore<GaulModelData> subSectorStore) {
		ADAMBoxMaker.subSectorStore = subSectorStore;
	}

	
	public static ListStore<GaulModelData> getOrStore() {
		return orStore;
	}

	public static void setOrStore(ListStore<GaulModelData> orStore) {
		ADAMBoxMaker.orStore = orStore;
	}
	
	public static ComboBox<GaulModelData> getSectorList() {
		return sectorList;
	}

	public static void setSectorList(ComboBox<GaulModelData> sectorList) {
		ADAMBoxMaker.sectorList = sectorList;
	}
	
	public static ComboBox<GaulModelData> getSubSectorList() {
		return subSectorList;
	}

	public static void setSubSectorList(ComboBox<GaulModelData> subSectorList) {
		ADAMBoxMaker.subSectorList = subSectorList;
	}
	
	public static ComboBox<GaulModelData> getOrList() {
		return orList;
	}

	public static void setOrList(ComboBox<GaulModelData> orList) {
		ADAMBoxMaker.orList = orList;
	}
	
	
	public static ComboBox<GaulModelData> getSoList() {
		return soList;
	}

	public static ListStore<GaulModelData> getSoStore() {
		return soStore;
	}

	public static Map<String, String> getSoSelected() {
		return soSelected;
	}

	private native static String getTemplate() /*-{ 
	 return  [ 
	    '<tpl for=".">', 
	    '<div class="x-combo-list-item" qtip="{gaulLabel}" qtitle="">{gaulLabel}</div>', 
	    '</tpl>' 
	    ].join(""); 
	  }-*/;  
	
	private static MenuItem createMenuItem(String label){
		MenuItem menuItem = new MenuItem();
			
		if(label!=null)
			menuItem.setText(label);
		
		menuItem.setStyleAttribute("color", "#17376D");
		menuItem.setStyleAttribute("font-size", "11px");
		menuItem.setStyleAttribute("font-weight", "bold");
		menuItem.setStyleAttribute("text-align", "center");
		
		return menuItem;
	}
	
	public static Button createToolbarButton(String label){
		Button button = new Button();
			
		if(label!=null)
			button.setText(label);
		
		button.setStyleAttribute("color", "#17376D");
		button.setStyleAttribute("font-size", "11px");
		button.setStyleAttribute("font-weight", "bold");
		button.setStyleAttribute("text-align", "center");
		
		return button;
	}
	
	public static CheckBox createToolbarCheckBox(String label){
		CheckBox checkBox = new CheckBox();
			
		if(label!=null)
			checkBox.setBoxLabel(label);
		
		checkBox.setValue(false);  
		
		checkBox.setStyleAttribute("color", "#17376D");
		checkBox.setStyleAttribute("font-size", "11px");
		
		
		checkBox.setStyleAttribute("font-weight", "bold");
		checkBox.setStyleAttribute("text-align", "center");
		
		return checkBox;
	}
}
