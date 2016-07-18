package org.fao.fenix.web.modules.adam.client.control;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.adam.client.view.ADAMComboMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMCustomComboMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMCustomMultiSelection;
import org.fao.fenix.web.modules.adam.client.view.ADAMQueryVOBuilder;
import org.fao.fenix.web.modules.adam.client.view.ADAMToolbar;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomBox;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomChartBox;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomFilterBox;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomSelectionBox;
import org.fao.fenix.web.modules.adam.client.view.custom.ADAMCustomShowBox;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMBoxMaker;
import org.fao.fenix.web.modules.adam.client.view.makers.ADAMChartMaker;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.adam.common.services.ADAMServiceEntry;
import org.fao.fenix.web.modules.adam.common.vo.ADAMConstants;
import org.fao.fenix.web.modules.adam.common.vo.ADAMQueryVO;
import org.fao.fenix.web.modules.adam.common.vo.ADAMResultVO;
import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.common.services.UserServiceEntry;
import org.fao.fenix.web.modules.core.common.vo.LoginResponseVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItem;
import org.fao.fenix.web.modules.metadataeditor.client.utils.ListItemTree;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;

public class ADAMCustomController extends ADAMController {
	
	
	public static SelectionListener<ButtonEvent> fullScreenViewCustomChart(final ADAMQueryVO qvo, final ADAMResultVO vo) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
//				removeBoxes();
//				ADAMVisibilityController.restoreAdamViewVisibility();
				removeCenter();
				//RootPanel.get("CENTER").setStyleName("big-" + vo.getBoxColor() + "-box border  content");
				RootPanel.get("CENTER").setStyleName("big-box border  content");
				RootPanel.get("CENTER").add(ADAMChartMaker.buildBigChart(qvo, vo, "CENTER", vo.getBoxColor(), smallScreenChart("", vo.getBoxColor(), vo.getTitle())));
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> addCustomChart(final ADAMQueryVO qvo, final ADAMResultVO vo, final ADAMCustomShowBox adamCustomShowBox) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				String position = getCustomPosition();
				ADAMVisibilityController.styleVisibilityDisplay(position);
				
				System.out.println("POSITION: " + position);
				
				String title = adamCustomShowBox.getTitle().getValue();
				if ( title != null)
					vo.setTitle(title);
				else
					vo.setTitle("");
				
				String description = adamCustomShowBox.getDescription().getHTML().toString();
				if ( title != null)
					vo.setDescription(description);
				else
					vo.setDescription("");
				
				VerticalPanel chartPanel = ADAMChartMaker.buildSmallCustomChart(qvo, vo, fullScreenCustomChart(qvo, position, "yellow", vo.getTitle()), position);

				
				RootPanel.get(position).setStyleName("small-box content");
				RootPanel.get(position).add(chartPanel);
				
				currentCustom.put(position, vo);
				
				currentUsedObjects.put(position, ADAMBoxContent.CUSTOM_CHART);
			}
		};
	}
	
	private static String getCustomPosition() {
		String position = null;
		for(int i = 0; i <= CUSTOMS; i++) {
			if ( !currentCustom.containsKey("CUSTOM_1" + i )) {
				position = "CUSTOM_1" + i ;
				return position;
			}
		}
		return position;
	}
	
	public static SelectionListener<ButtonEvent> closeCustom() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMVisibilityController.removeCustomVisibility(true);
				if (RootPanel.get("CUSTOM").getWidgetCount() > 0)
					RootPanel.get("CUSTOM").remove(RootPanel.get("CUSTOM").getWidget(0));
				RootPanel.get("CUSTOM").setStyleName("");
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> closeShowProjects() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMVisibilityController.removeProjectsVisibility();
				if (RootPanel.get("PROJECT_TABLE").getWidgetCount() > 0)
					RootPanel.get("PROJECT_TABLE").remove(RootPanel.get("PROJECT_TABLE").getWidget(0));
				RootPanel.get("PROJECT_TABLE").setStyleName("");
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> close(final ADAMQueryVO qvo, final String position, final ADAMBoxContent type) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {

				if (RootPanel.get(position).getWidgetCount() > 0)
					RootPanel.get(position).remove(RootPanel.get(position).getWidget(0));
				RootPanel.get(position).setStyleName("");
				
				currentCustom.remove(position);
				currentUsedObjects.remove(position);
				
				relocateObjects(qvo, position, type);
			}
		};
	}
	
	private static void relocateObjects(final ADAMQueryVO qvo, String position, ADAMBoxContent type){
		System.out.println("relocate: "+ position);
		if ( !currentCustom.isEmpty()) {
			for( int i= CUSTOMS ; i >= 0; i-- ) {
				if (currentCustom.containsKey("CUSTOM_1" + i)) {
					
					// add to the new position
					currentCustom.put(position, currentCustom.get("CUSTOM_1" + i));
					currentUsedObjects.put(position, type);
					
					// reload object
					ADAMResultVO vo = currentCustom.get("CUSTOM_1" + i);

					
					switch (type) {
					case CUSTOM_CHART:
						VerticalPanel chartPanel = ADAMChartMaker.buildSmallCustomChart(qvo, vo, fullScreenCustomChart(qvo, position, "yellow", ""), position);
						ADAMVisibilityController.styleVisibilityDisplay(position);
						RootPanel.get(position).setStyleName("small-box content");
						RootPanel.get(position).add(chartPanel);
						
						
						
						break;

					default:
						break;
					}
					
					
					// remove from old position
					currentCustom.remove("CUSTOM_1" + i);
					currentUsedObjects.remove("CUSTOM_1" + i);
//					
					if (RootPanel.get("CUSTOM_1" + i).getWidgetCount() > 0)
						RootPanel.get("CUSTOM_1" + i).remove(RootPanel.get("CUSTOM_1" + i).getWidget(0));
					RootPanel.get("CUSTOM_1" + i).setStyleName("");

					break;
				}
			}
		}
		
	}
	
	public static SelectionListener<ButtonEvent> applyCreateResource(final ADAMCustomBox adamCustomBox) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				applyCreateResourceAgent(adamCustomBox);
			}
		};
	}
	
	public static void applyCreateResourceAgent(final ADAMCustomBox adamCustomBox) {
		ComboBox<GaulModelData>	outputType = adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOutputBox().getOutputTypeList();
		ADAMBoxContent resourceType = ADAMBoxContent.valueOf(outputType.getSelection().get(0).getGaulCode());
		
		
		
		
		switch(resourceType) {
			case PIE:
				System.out.println("CREATING RESOURCE PIE");
				createPieChart(adamCustomBox, resourceType);
			break;
			
			case BAR:
				System.out.println("CREATING RESOURCE BAR");
				createBarChart(adamCustomBox, resourceType);
			break;	
			
			case TABLE:
				System.out.println("CREATING RESOURCE TABLE");
			break;	
		}
	}
	
	private static void createBarChart(final ADAMCustomBox adamCustomBox, final ADAMBoxContent resourceType){
		System.out.println("createBarChart()");
		
		// measurementUnit TODO: if it's not count
		//String measurementUnit = "USD Mil";
		String measurementUnit = ADAMController.baseUnit;

		// limit
		Integer limit = Integer.valueOf(adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getLimitFilter().getValue());

		
		// getting dates
		List<String> dates = getReferencePeriod(adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getFromDateList(), adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getToDateList());
		
		// aggregation type
		String aggregationType = adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getAggregationList().getValue().getGaulCode();
				
		// agrgegation column
		String aggregationColumn = adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getAggregationColumnList().getValue().getGaulCode();
	
		
		if (!dates.isEmpty()) {
			// getting the coding system X axis
			String xAxisColumn = adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOutputBox().getAdamCustomChartBox().getxAxisList().getSelection().get(0).getGaulCode();
	
			// filters 
			final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
			Boolean selectionMade = setChartFiltersAxis(filters, xAxisColumn, adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOutputBox().getAdamCustomChartBox().getAdamComboXMultiSelection() );
			
			if ( !selectionMade && !xAxisColumn.equalsIgnoreCase("year")) {
				FenixAlert.info("Axis Selection", "Select some values");
			}
			else {
				setChartFiltersOptions(filters, adamCustomBox.getAdamCustomSelectionBox().getAdamCustomFilterBox());
				
				
				final ADAMQueryVO qvo = ADAMQueryVOBuilder.createCRSResource(filters, dates, xAxisColumn, aggregationColumn, aggregationType, resourceType.toString(), ADAMBoxContent.CHART.toString(), "", measurementUnit, limit, "yellow");
//				ADAMQueryVO qvo = ADAMQueryVOBuilder.createCRSResource(filters, dates, xAxisColumn, "usd_disbursement", aggregationType, ADAMBoxContent.BAR_WITH_CATEGORIES.toString(), ADAMBoxContent.CHART.toString(), "", measurementUnit, limit, "yellow");

				adamCustomBox.getAdamCustomShowBox().getImagePanel().removeAll();
				adamCustomBox.getAdamCustomShowBox().getOptionsPanel().removeAll();
				adamCustomBox.getAdamCustomShowBox().getImagePanel().add(buildWaitingPanel());
				adamCustomBox.getAdamCustomShowBox().getPanel().layout();
				
				System.out.println("FILTERS: "+ filters);
				
				ADAMServiceEntry.getInstance().askADAM(qvo, new AsyncCallback<ADAMResultVO>() {
					public void onSuccess(ADAMResultVO vo) {
						System.out.println("adding chart");
						adamCustomBox.getAdamCustomShowBox().getImagePanel().removeAll();
						VerticalPanel chartPanel = ADAMChartMaker.buildCustomChart(qvo, vo, fullScreenViewCustomChart(qvo, vo), adamCustomBox.getAdamCustomShowBox());
		
						adamCustomBox.getAdamCustomShowBox().getImagePanel().add(chartPanel);
						adamCustomBox.getAdamCustomShowBox().getPanel().layout();
						
						adamCustomBox.getAdamCustomShowBox().getOptionsPanel().add(adamCustomBox.getAdamCustomShowBox().buildOptions());
						adamCustomBox.getAdamCustomShowBox().getPanel().layout();
						
					}
					public void onFailure(Throwable e) {
							FenixAlert.error(BabelFish.print().error(), e.getMessage());
						}
				 });
			}
		}

	}
	
	private static void createPieChart(final ADAMCustomBox adamCustomBox, final ADAMBoxContent resourceType){
		System.out.println("createPieChart()");
		
		// measurementUnit TODO: if it's not count
		//String measurementUnit = "USD Mil";
		String measurementUnit = ADAMController.baseUnit;
		
		// limit
		Integer limit = Integer.valueOf(adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getLimitFilter().getValue());

		
		// getting dates
		List<String> dates = getReferencePeriod(adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getFromDateList(), adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getToDateList());
		
		// aggregation type
		String aggregationType = adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getAggregationList().getValue().getGaulCode();
		
		// agrgegation column
		String aggregationColumn = adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOptionsBox().getAggregationColumnList().getValue().getGaulCode();
		

		// getting the coding system X axis
		String xAxisColumn = adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOutputBox().getAdamCustomChartBox().getxAxisList().getSelection().get(0).getGaulCode();
	
		// filters 
		final HashMap<String, List<String>> filters = new HashMap<String, List<String>>();
		Boolean selectionMade = setChartFiltersAxis(filters, xAxisColumn, adamCustomBox.getAdamCustomSelectionBox().getAdamCustomOutputBox().getAdamCustomChartBox().getAdamComboXMultiSelection() );
			
		if ( !selectionMade && !xAxisColumn.equalsIgnoreCase("year")) {
			FenixAlert.info("Axis Selection", "Select some values");
		}
		else {
			setChartFiltersOptions(filters, adamCustomBox.getAdamCustomSelectionBox().getAdamCustomFilterBox());
			
			final ADAMQueryVO qvo = ADAMQueryVOBuilder.createCRSResource(filters, dates, xAxisColumn, aggregationColumn, aggregationType, resourceType.toString(), ADAMBoxContent.CHART.toString(), "", measurementUnit, limit, "yellow");
		
			adamCustomBox.getAdamCustomShowBox().getImagePanel().removeAll();
			adamCustomBox.getAdamCustomShowBox().getOptionsPanel().removeAll();
			adamCustomBox.getAdamCustomShowBox().getImagePanel().add(buildWaitingPanel());
			adamCustomBox.getAdamCustomShowBox().getPanel().layout();
			
			System.out.println("FILTERS: "+ filters);
				
			ADAMServiceEntry.getInstance().askADAM(qvo, new AsyncCallback<ADAMResultVO>() {
				public void onSuccess(ADAMResultVO vo) {
					System.out.println("adding chart");
					adamCustomBox.getAdamCustomShowBox().getImagePanel().removeAll();
					VerticalPanel chartPanel = ADAMChartMaker.buildCustomChart(qvo, vo, fullScreenViewCustomChart(qvo, vo), adamCustomBox.getAdamCustomShowBox());
							
					adamCustomBox.getAdamCustomShowBox().getImagePanel().add(chartPanel);
					
					Integer panelWidth = Integer.valueOf(ADAMConstants.SMALL_TABLE_CHART_WIDTH) + 10 ;
					adamCustomBox.getAdamCustomShowBox().getImagePanel().setWidth(panelWidth);
					adamCustomBox.getAdamCustomShowBox().getPanel().layout();
					
					adamCustomBox.getAdamCustomShowBox().getOptionsPanel().add(adamCustomBox.getAdamCustomShowBox().buildOptions());
					adamCustomBox.getAdamCustomShowBox().getPanel().layout();					
				}
				public void onFailure(Throwable e) {
						FenixAlert.error(BabelFish.print().error(), e.getMessage());
					}
			 });
		}

	}
	
	private static void setChartFiltersOptions(HashMap<String, List<String>> filters, ADAMCustomFilterBox adamCustomFilterBox) {
		// recipient country
		setChartFilters(filters, "recipientcode", adamCustomFilterBox.getRecipient().getMultiSelection());
		
		// donor
		setChartFilters(filters, "donorcode", adamCustomFilterBox.getDonor().getMultiSelection());
		
		// sector
		setChartFilters(filters, "dac_sector", adamCustomFilterBox.getSector().getMultiSelection());
		
	}
	
	private static void setChartFilters(HashMap<String, List<String>> filters, String columnType, ADAMCustomMultiSelection adamComboSelection) {
		TreePanel treePanel = adamComboSelection.treePanel;
		System.out.println("treepanel size: " + treePanel.getStore().getAllItems().size());
		System.out.println("checked size: " + treePanel.getCheckedSelection().size());
		
		if ( treePanel.getStore().getAllItems().size() != treePanel.getCheckedSelection().size()){
			Map<String, String> map = getSelectionAgent(treePanel);
			if ( columnType == "recipientcode") {
				Boolean countryAdded = addCountryFilter(filters, map);
				System.out.println("countryAdded: " + countryAdded);
			}
			else if ( columnType == "donorcode") {
				Boolean donorAdded = addDonorFilter(filters, map);
				System.out.println("donorAdded: " + donorAdded);
			}
			else if ( columnType == "dac_sector") {
				Boolean sectorAdded =  addSectorFilter(filters, map);
				System.out.println("sectorAdded: " + sectorAdded);
			}
			else if ( columnType == "purposecode") {
				
			}
		}
		else {
			System.out.println(columnType + " is all selected" );
		}
	}
	
	private static Boolean setChartFiltersAxis(HashMap<String, List<String>> filters, String columnType, ADAMCustomComboMultiSelection AdamComboMultiSelection) {
		if ( columnType == "recipientcode") {
			TreePanel treePanel = AdamComboMultiSelection.gaulTreePanel;
			Map<String, String> map = getSelectionAgent(treePanel);
			Boolean countryAdded = addCountryFilter(filters, map);
			System.out.println("countryAdded: " + countryAdded);
			return countryAdded;
		}
		else if ( columnType == "donorcode") {
			TreePanel treePanel = AdamComboMultiSelection.donorTreePanel;
			Map<String, String> map = getSelectionAgent(treePanel);
			Boolean donorAdded = addDonorFilter(filters, map);
			System.out.println("donorAdded: " + donorAdded);
			return donorAdded;
		}
		else if ( columnType == "dac_sector") {
			TreePanel treePanel = AdamComboMultiSelection.sectorTreePanel;
			Map<String, String> map = getSelectionAgent(treePanel);
			Boolean sectorAdded =  addSectorFilter(filters, map);
			System.out.println("sectorAdded: " + sectorAdded);
			return sectorAdded;
		}
		else if ( columnType == "purposecode") {
			return false;
		}
		return false;
	}
	
	
	
	public static Map<String, String> getSelectionAgent(final TreePanel<ListItemTree> treePanel) {
		Map<String, String> map = new HashMap<String, String>();
		List<ListItemTree> items = treePanel.getCheckedSelection();
		for (ListItemTree itemSelected : items) {
			String code = itemSelected.getCode();
			String label = itemSelected.getLabel();
			map.put(code, label);
		}
		return map;
	}
	
	
	
	public static SelectionListener<MenuEvent> createCustomResource() {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				createCustomResourceAgent();
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> customizeResource() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createCustomResourceAgent();
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> createCustomResourceButtonEvent() {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				createCustomResourceAgent();
			}
		};
	}
	
	public static SelectionListener<MenuEvent> createCustomResourceMenuEvent() {
		return new SelectionListener<MenuEvent>() {
			public void componentSelected(MenuEvent ce) {
				createCustomResourceAgent();
			}
		};
	}
	
	private static void createCustomResourceAgent() {
		System.out.println("create resource");
		ADAMVisibilityController.removeCustomBoxContent();
		ADAMVisibilityController.restoreCustomVisibility(true);
		
		RootPanel.get("CUSTOM").setStyleName("big-box border  content");
		
		ADAMCustomBox adamCustomBox = new ADAMCustomBox();
		
		RootPanel.get("CUSTOM").add(adamCustomBox.build());
	}
	
	
	public static SelectionChangedListener<GaulModelData> listBoxSelectionXaxis(final ADAMCustomSelectionBox adamCustomSelectionBox, final ComboBox<GaulModelData> list, final ADAMCustomChartBox adamCustomChartBox) {
		return new SelectionChangedListener<GaulModelData>() {
			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
			//	System.out.println("HERE!!!");
				// switch selection
				String code = list.getSelection().get(0).getGaulCode();
				switchResourceXaxis(code, adamCustomChartBox);	
				adamCustomSelectionBox.getFilterPanel().removeAll();
				adamCustomSelectionBox.getFilterPanel().add(adamCustomSelectionBox.getAdamCustomFilterBox().build(code));
				adamCustomSelectionBox.getFilterPanel().layout();
			}
		};
	}
	
	private static void switchResourceXaxis(String code, final ADAMCustomChartBox adamCustomChartBox) {
		System.out.println("CODE SELECTION: " + code);
		adamCustomChartBox.getxAxis().removeAll();
		if ( code == "recipientcode") {
			addGaulMultiselectionAgent(adamCustomChartBox.getAdamComboXMultiSelection(), adamCustomChartBox.getxAxis());
			adamCustomChartBox.getxAxis().layout();
		}
		else if ( code == "donorcode") {
			addDonorMultiselectionAgent(adamCustomChartBox.getAdamComboXMultiSelection(), adamCustomChartBox.getxAxis());
			adamCustomChartBox.getxAxis().layout();
		}
		else if ( code == "dac_sector") {
			addSectorMultiselectionAgent(adamCustomChartBox.getAdamComboXMultiSelection(), adamCustomChartBox.getxAxis());
			adamCustomChartBox.getxAxis().layout();
		}
	}
	
	
	public static SelectionChangedListener<GaulModelData> listBoxSelectionYaxis(final ComboBox<GaulModelData> list, final ADAMCustomChartBox adamCustomChartBox) {
		return new SelectionChangedListener<GaulModelData>() {
			public void selectionChanged(SelectionChangedEvent<GaulModelData> se) {
				//System.out.println("HERE!!!");
				// switch selection
				String code = list.getSelection().get(0).getGaulCode();
				switchResourceYaxis(code, adamCustomChartBox);
			}
		};
	}
	
	private static void switchResourceYaxis(String code, final ADAMCustomChartBox adamCustomChartBox) {
		System.out.println("CODE SELECTION: " + code);
		adamCustomChartBox.getyAxis().removeAll();
		if ( code == "recipientcode") {
			addGaulMultiselectionAgent(adamCustomChartBox.getAdamComboYMultiSelection(), adamCustomChartBox.getyAxis());
			adamCustomChartBox.getyAxis().layout();
		}
		else if ( code == "donorcode") {
			addDonorMultiselectionAgent(adamCustomChartBox.getAdamComboYMultiSelection(), adamCustomChartBox.getyAxis());
			adamCustomChartBox.getyAxis().layout();
		}
		else if ( code == "dac_sector") {
			addSectorMultiselectionAgent(adamCustomChartBox.getAdamComboYMultiSelection(), adamCustomChartBox.getyAxis());
			adamCustomChartBox.getyAxis().layout();
		}
		
	}
	
	public static void addSectorMultiselectionAgent(final ADAMCustomComboMultiSelection adamComboMultiSelection, final HorizontalPanel panel) {
		String codingSystemCode = getCodingSystemCode(currentlySelectedDatasetCode);
		
		
		ADAMServiceEntry.getInstance().findAll("Dac_", codingSystemCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {

				adamComboMultiSelection.sectorTreeStore.removeAll();

				for (CodeVo vo : vos) {
					List<ListItem> values = new ArrayList<ListItem>();

					// Agricultural/FAO related sectors = 9999, should not be included in multi-selection
					if(!vo.getCode().equalsIgnoreCase("Dac_9999")) {
						ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
						adamComboMultiSelection.sectorTreeStore.add(treeItem, false);
					}
				}
				
				 // change in node check state  
				VerticalPanel infoPanel = new VerticalPanel();
				infoPanel.setHeight(150);
				infoPanel.setWidth(250);
				infoPanel.setScrollMode(Scroll.AUTO);
				
				adamComboMultiSelection.sectorTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.sectorTreePanel));
				
//				checkValues(ADAMBoxMaker.sectorsSelected, adamComboMultiSelection.sectorTreePanel, infoPanel);
				
				panel.add(adamComboMultiSelection.buildSectorPanel(infoPanel));
				
				selectAll(adamComboMultiSelection.sectorTreePanel, infoPanel);
				panel.layout();

			}
			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});
	}

	
	public static void addGaulMultiselectionAgent(final ADAMCustomComboMultiSelection adamComboMultiSelection, final HorizontalPanel panel) {

		ADAMServiceEntry.getInstance().getCountriesList("ADAMRecipients", new AsyncCallback<LinkedHashMap<CodeVo,LinkedHashMap<CodeVo,List<CodeVo>>>>() {
			public void onSuccess(LinkedHashMap<CodeVo, LinkedHashMap<CodeVo, List<CodeVo>>> result) {

				TreeStore treeStore = adamComboMultiSelection.gaulTreeStore;
				treeStore.removeAll();

//				System.out.println("\n\nPRINT");
				for(CodeVo topcodeVo : result.keySet()) {
					ListItemTree topItem = createTreeItem("Gaul_" + topcodeVo.getCode(), topcodeVo.getLabel(), "0");
					treeStore.add(topItem, false);
//					System.out.println("- " + topcodeVo.getLabel());

					for(CodeVo subCodeVo : result.get(topcodeVo).keySet()) {
//						System.out.println("-- " + subCodeVo.getLabel());
						ListItemTree subItem = createTreeItem("Gaul_" + subCodeVo.getCode(), subCodeVo.getLabel(), "1");
						treeStore.add(topItem, subItem, false);

						if ( result.get(topcodeVo).containsKey(subCodeVo)) {
							for(CodeVo subSubCodeVo : result.get(topcodeVo).get(subCodeVo)) {
//								System.out.println("--- " + subSubCodeVo.getLabel());
								ListItemTree subSubItem = createTreeItem("Gaul_" + subSubCodeVo.getCode(), subSubCodeVo.getLabel(), "1");
								treeStore.add(subItem, subSubItem, false);
							
							}
						}
					}
				}
//				adamComboMultiSelection.gaulTreePanel.addCheckListener(checkedItem());
				
				 // change in node check state  
				VerticalPanel infoPanel = new VerticalPanel();
				infoPanel.setHeight(150);
				infoPanel.setWidth(250);
				infoPanel.setScrollMode(Scroll.AUTO);
				
				adamComboMultiSelection.gaulTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.gaulTreePanel));
				
//				checkCountryValues(ADAMBoxMaker.countriesSelected, adamComboMultiSelection.gaulTreePanel, infoPanel);

				panel.add(adamComboMultiSelection.buildGaulRegions(infoPanel));
				
				selectAll(adamComboMultiSelection.gaulTreePanel, infoPanel);
				panel.layout();

			}


			public void onFailure(Throwable arg0) {
			}
		});
	}
	
	
	public static void addDonorMultiselectionAgent(final ADAMCustomComboMultiSelection adamComboMultiSelection, final HorizontalPanel panel) {
		String codingSystemCode = getCodingSystemCode(currentlySelectedDatasetCode);
		
		
		ADAMServiceEntry.getInstance().findAll("Donor_", codingSystemCode, new AsyncCallback<List<CodeVo>>() {
			public void onSuccess(List<CodeVo> vos) {

				adamComboMultiSelection.donorTreeStore.removeAll();

				for (CodeVo vo : vos) {
					ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
					adamComboMultiSelection.donorTreeStore.add(treeItem, false);

				}
				
				 // change in node check state  
				VerticalPanel infoPanel = new VerticalPanel();
				infoPanel.setHeight(150);
				infoPanel.setWidth(250);
				infoPanel.setScrollMode(Scroll.AUTO);
				
				adamComboMultiSelection.donorTreePanel.addListener(Events.OnClick, createPanel(infoPanel, adamComboMultiSelection.donorTreePanel));

//				checkValues(ADAMBoxMaker.donorsSelected, adamComboMultiSelection.donorTreePanel, infoPanel);

				panel.add(adamComboMultiSelection.buildDonorPanel(infoPanel));
				
				selectAll(adamComboMultiSelection.donorTreePanel, infoPanel);
				
				panel.layout();
			}
			public void onFailure(Throwable e) {
				FenixAlert.error(BabelFish.print().error(), e.getMessage());
			}
		});

	}

	
	// codeToSearch = "Donor_", "Gaul_", "Dac_sector_"
	public static void addMultiselectionAgent(final ADAMCustomMultiSelection adamMultiSelection, final HorizontalPanel panel, final String codeToSearch, final String title) {
		if (codeToSearch.contains("Gaul_")) {
			addGaulMultiselectionAgent(adamMultiSelection, panel, codeToSearch, title);
		}
		else {
			addNormalMultiselectionAgent(adamMultiSelection, panel, codeToSearch, title);
		}
	

	}
	
	
	public static void addNormalMultiselectionAgent(final ADAMCustomMultiSelection adamMultiSelection, final HorizontalPanel panel, final String codeToSearch, final String title) {
		String codingSystemCode = getCodingSystemCode(currentlySelectedDatasetCode);
		
		
		ADAMServiceEntry.getInstance().findAll(codeToSearch, codingSystemCode, new AsyncCallback<List<CodeVo>>() {
				public void onSuccess(List<CodeVo> vos) {
	
					adamMultiSelection.treeStore.removeAll();
	
					for (CodeVo vo : vos) {
						ListItemTree treeItem = createTreeItem(vo.getCode(), vo.getLabel(), "0");
						adamMultiSelection.treeStore.add(treeItem, false);
	
					}
					
					
					
					 // change in node check state  
//					VerticalPanel infoPanel = new VerticalPanel();
//					infoPanel.setHeight(150);
//					infoPanel.setWidth(250);
//					infoPanel.setScrollMode(Scroll.AUTO);

					adamMultiSelection.treePanel.addListener(Events.OnClick, createPanel(adamMultiSelection.getInfoPanel(), adamMultiSelection.treePanel));
					
					panel.add(adamMultiSelection.buildPanel(title));
					
					selectAll(adamMultiSelection.getTreePanel(), adamMultiSelection.getInfoPanel());
					
					panel.layout();
				}
				public void onFailure(Throwable e) {
					FenixAlert.error(BabelFish.print().error(), e.getMessage());
				}
			});
	}
	
	public static void addGaulMultiselectionAgent(final ADAMCustomMultiSelection adamMultiSelection, final HorizontalPanel panel, final String codeToSearch, final String title) {

		ADAMServiceEntry.getInstance().getCountriesList("ADAMRecipients", new AsyncCallback<LinkedHashMap<CodeVo,LinkedHashMap<CodeVo,List<CodeVo>>>>() {
			public void onSuccess(LinkedHashMap<CodeVo, LinkedHashMap<CodeVo, List<CodeVo>>> result) {

				TreeStore treeStore = adamMultiSelection.treeStore;
				treeStore.removeAll();

//				System.out.println("\n\nPRINT");
				for(CodeVo topcodeVo : result.keySet()) {
					ListItemTree topItem = createTreeItem("Gaul_" + topcodeVo.getCode(), topcodeVo.getLabel(), "0");
					treeStore.add(topItem, false);
//					System.out.println("- " + topcodeVo.getLabel());

					for(CodeVo subCodeVo : result.get(topcodeVo).keySet()) {
//						System.out.println("-- " + subCodeVo.getLabel());
						ListItemTree subItem = createTreeItem("Gaul_" + subCodeVo.getCode(), subCodeVo.getLabel(), "1");
						treeStore.add(topItem, subItem, false);

						if ( result.get(topcodeVo).containsKey(subCodeVo)) {
							for(CodeVo subSubCodeVo : result.get(topcodeVo).get(subCodeVo)) {
//								System.out.println("--- " + subSubCodeVo.getLabel());
								ListItemTree subSubItem = createTreeItem("Gaul_" + subSubCodeVo.getCode(), subSubCodeVo.getLabel(), "1");
								treeStore.add(subItem, subSubItem, false);
							
							}
						}
					}
				}
//				adamComboMultiSelection.gaulTreePanel.addCheckListener(checkedItem());
				
				 // change in node check state  
//				VerticalPanel infoPanel = new VerticalPanel();
//				infoPanel.setHeight(150);
//				infoPanel.setWidth(250);
//				infoPanel.setScrollMode(Scroll.AUTO);
				
				adamMultiSelection.treePanel.addListener(Events.OnClick, createPanel(adamMultiSelection.getInfoPanel(), adamMultiSelection.treePanel));
				
//				checkCountryValues(ADAMBoxMaker.countriesSelected, adamComboMultiSelection.gaulTreePanel, infoPanel);

				panel.add(adamMultiSelection.buildPanel(title));
				
				selectAll(adamMultiSelection.getTreePanel(), adamMultiSelection.getInfoPanel());
				panel.layout();

			}


			public void onFailure(Throwable arg0) {
			}
		});
	}
	
	
	private static ListItemTree createTreeItem(String code, String fullLabel, String level) {
		String reducedLabel = "";
		if (fullLabel.length() > 25)
			reducedLabel = fullLabel.substring(0, 24) + "...";
		else
			reducedLabel = fullLabel;

		ListItemTree item = new ListItemTree(code, fullLabel);
		item.setShortLabel(reducedLabel);
		item.setLevel(level);

		return item;
	}
	
	private static void checkValues(final Map<String, String> map,  final TreePanel<ListItemTree> treePanel,  final VerticalPanel infoPanel) {
		TreeStore<ListItemTree> items = treePanel.getStore();
		if( map != null ) {
			for (String code : map.keySet()) {
				for (ListItemTree itemSelected : items.getAllItems()) {
					if( code.equalsIgnoreCase(itemSelected.getCode())) {
						treePanel.setChecked(itemSelected, true);
						if (treePanel.isLeaf(itemSelected)) {
							infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
						}
						break;
					}

				}
			}
		}
	}
	
	
	private static void checkCountryValues(final Map<String, String> map,  final TreePanel<ListItemTree> treePanel, final VerticalPanel infoPanel) {
		TreeStore<ListItemTree> items = treePanel.getStore();
		if( map != null ) {
			for (String code : map.keySet()) {
				for (ListItemTree itemSelected : items.getAllItems()) {
//					System.out.println("----> " + code +" | "+ itemSelected.getCode());
					if( itemSelected.getCode().equalsIgnoreCase(code)) {
//						System.out.println("equal " + code +" | "+ itemSelected.getCode());
						treePanel.setChecked(itemSelected, true);					
						
						if (treePanel.isLeaf(itemSelected)) {
							infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
						}
						break;
					}
					

				}
			}
		}
	}
	
	private static Listener<TreePanelEvent<ListItemTree>> createPanel(final VerticalPanel infoPanel, final TreePanel<ListItemTree> treePanel) {
		return new Listener<TreePanelEvent<ListItemTree>>() {
			public void handleEvent(TreePanelEvent<ListItemTree> be) {  
				createPanelAgent(infoPanel, treePanel);
		 	} 
		 };
	}
	
	private static void createPanelAgent(final VerticalPanel infoPanel, final TreePanel<ListItemTree> treePanel) {
		infoPanel.removeAll();
		List<ListItemTree> items = treePanel.getCheckedSelection();
		for (ListItemTree itemSelected : items) {
			if (treePanel.isLeaf(itemSelected)) {
				infoPanel.add(new Html("<font size='1.5pt'> " + itemSelected.getLabel() +"</font>"));
			}
		}
		infoPanel.layout();
	}
	
	public static HorizontalPanel buildWaitingPanel() {
		
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(5);
		
		VerticalPanel left = new VerticalPanel();
		left.setSpacing(5);
		
		VerticalPanel right = new VerticalPanel();
		right.setSpacing(5);
		
		Html q = new Html("<div align='center' style='font-size: 15px; color: #17376D;'>Please wait, loading ...</div>");
		q.setHeight("120px");
		
		left.add(q);
//		left.add(buildQuestionsToolbar());
		
		Image i = new Image("adam-images/loading.gif");
		
		right.add(i);
		
		p.add(left);
		p.add(right);
		
		return p;
	}
	
	
	public static List<String> getReferencePeriod(final ComboBox<GaulModelData> fromDateList, final ComboBox<GaulModelData> toDateList) {
		List<String> dates = new ArrayList<String>();

	 	Date fromDate = FieldParser.parseDate(fromDateList.getValue().getGaulCode());
	 	Date toDate =  FieldParser.parseDate(toDateList.getValue().getGaulCode());

	 	// error message
	 	if ( fromDate.compareTo(toDate) > 0 ) {
	 		FenixAlert.error("Select date", ("Date selection is wrong"));
//	 		Window.alert("Date selection is wrong");
		}
	 	
	 	else if ( fromDate.compareTo(toDate) == 0 ) {
	 		dates.add(toDateList.getValue().getGaulCode());
	 	}
	 	
	 	else if ( fromDate.compareTo(toDate) < 0 ) {
	 		while( fromDate.compareTo(toDate) <= 0 ) {
	 			System.out.println("dates: " + fromDate + " | " + toDate);

	 			dates.add(FieldParser.formatDate(fromDate, "formatterMinusReverse"));
				fromDate = new Date(fromDate.getYear() + 1, fromDate.getMonth(), fromDate.getDate());
	 		}

	 	}

	 	System.out.println("dates: " +  dates);

	 	return dates;
	}
	
	public static SelectionListener<ButtonEvent> deselectAllEvent(final TreePanel<ListItemTree> tree, final VerticalPanel infoPanel ) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				
				List<ListItemTree> items = tree.getCheckedSelection();
//				System.out.println("getSize2: " + items.size());
				for (ListItemTree itemSelected : items) {
					tree.setChecked(itemSelected, false);
				}
				infoPanel.removeAll();
				infoPanel.add(new Html("<div class='small-content'>None<div>"));
				infoPanel.layout();
//				createPanelAgent(infoPanel, tree);
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> selectAllEvent(final TreePanel<ListItemTree> tree, final VerticalPanel infoPanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {	
				selectAll(tree, infoPanel);
			}
		};
	}
	
	public static void selectAll(final TreePanel<ListItemTree> tree, final VerticalPanel infoPanel) {
		TreeStore<ListItemTree> itemsTreeStore = tree.getStore();
		List<ListItemTree> items = itemsTreeStore.getAllItems();
		System.out.println("--> " + items.size());
		for (ListItemTree itemSelected : items) {
			System.out.println("--> " + itemSelected.getCode());
			tree.setChecked(itemSelected, true);
		}
		infoPanel.removeAll();
		infoPanel.add(new Html("<div class='small-content'>All selected<div>"));
		infoPanel.layout();
//		createPaneClAgent(infoPanel, tree);
	}
	
	public static SelectionListener<IconButtonEvent> modifyText(final RichTextArea text) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				new TextEditor(text, "ADAM_CUSTOM").build();
			}
		};
	}
	
	public static SelectionListener<ButtonEvent> setText(final TextEditor textEditor) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
				ADAMCustomShowBox.getDescription().setHTML(textEditor.getTextArea().getHTML());
				textEditor.getWindow().close();
				
				ADAMCustomShowBox.getOptionsPanel().layout();
			}
		};
	}
	
}
