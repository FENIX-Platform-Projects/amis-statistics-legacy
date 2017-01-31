package org.fao.fenix.web.modules.table.client.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.client.utils.DateUtils;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateFromToPanel;
import org.fao.fenix.web.modules.birt.client.view.chart.wizard.DateLastUpdatePanel;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEOpener;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.toolbar.FenixToolbarConstants;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableViewFilters;
import org.fao.fenix.web.modules.table.client.view.TableViewGrid;
import org.fao.fenix.web.modules.table.client.view.TableViewWindow;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableFilterVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class TableViewController {

	private TableViewFilters tableFilters;

	private Map<String, DimensionBeanVO> queryCache;

	private Map<String, List<String>> filterCriteria;

	private List<TableFilterVO> filters;

	public TableViewController(TableViewFilters tableFilters, final Long datasetId, final Long tableViewID) {
		this.tableFilters = tableFilters;
		this.queryCache = new HashMap<String, DimensionBeanVO>();
		this.filterCriteria = new HashMap<String, List<String>>();
		this.filters = new ArrayList<TableFilterVO>();
		TableServiceEntry.getInstance().getFilters(datasetId, new AsyncCallback<List<TableFilterVO>>() {
			public void onSuccess(List<TableFilterVO> filterList) {
				filters.clear();
				for (TableFilterVO f : filterList)
					filters.add(f);
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("RPC Failed", "buildDatasetPanel @ filters");
			}
		});
	}

	public Listener<ComponentEvent> selectedDimension(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final Map<String, DimensionBeanVO> dimensionMap, final VerticalPanel datePanel,
			final DateFromToPanel dateFromToPanel, final VerticalPanel dateLUpanel, final DateLastUpdatePanel dateLastUpdatePanel, final String periodicity, final HorizontalPanel xValuesPanel) {
		return new Listener<ComponentEvent>() {
			public void handleEvent(ComponentEvent event) {
				fillFromList(dimensionValues, selectedValues, dimensionDataList, dimensionMap, datePanel, dateFromToPanel, dateLUpanel, dateLastUpdatePanel, periodicity, xValuesPanel);
			};
		};
	}

	public void fillFromList(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final Map<String, DimensionBeanVO> dimensionMap, final VerticalPanel datePanel, final DateFromToPanel dateFromToPanel,
			final VerticalPanel dateLUpanel, final DateLastUpdatePanel dateLastUpdatePanel, final String periodicity, final HorizontalPanel xValuesPanel) {
		final String columnId = dimensionDataList.getSelectedItem().getId();
		DimensionBeanVO dimensionVO = dimensionMap.get(columnId);
		String startDate = new String();
		String endDate = new String();
		if (dimensionVO != null) {
			Map<String, String> values = dimensionVO.getDistinctDimensionValues();
			dimensionValues.clear();
			selectedValues.clear();
			enableDisableDatesPanel(dimensionVO.isDate(), datePanel, dateFromToPanel, dateLUpanel, dateLastUpdatePanel, dimensionValues, xValuesPanel);
			if (values != null) {
				Iterator<Map.Entry<String, String>> iterator = values.entrySet().iterator();
				for (int i = 0; i < values.size(); i++) {
					Map.Entry<String, String> entry = iterator.next();
					dimensionValues.addItem(entry.getValue(), entry.getKey());
					if (dimensionVO.isDate()) {
						if (i == values.size() - 1)
							startDate = entry.getKey();
						if (i == 0)
							endDate = entry.getKey();
					}
				}
			}
			if (dimensionVO.isDate()) {
				dateFromToPanel.setPeriodType(periodicity);
				dateLastUpdatePanel.setPeriodType(periodicity);
				DateUtils.setDateFromToDefault(startDate, endDate, periodicity, dateFromToPanel.getYearFrom(), dateFromToPanel.getMonthFrom(), dateFromToPanel.getDayFrom(), dateFromToPanel.getYearTo(), dateFromToPanel.getMonthTo(), dateFromToPanel
						.getDayTo());
			}
		}
	}

	private void enableDisableDatesPanel(Boolean isDate, VerticalPanel datePanel, DateFromToPanel dateFromToPanel, VerticalPanel dateLUpanel, DateLastUpdatePanel dateLastUpdatePanel, ListBox dimensionXValues, HorizontalPanel xValuesPanel) {
		if (isDate) {
			datePanel.setVisible(true);
			dateLUpanel.setVisible(true);
		} else {
			resetDatePanel(datePanel, dateFromToPanel, dateLUpanel, dateLastUpdatePanel, dimensionXValues, xValuesPanel);
			datePanel.setVisible(false);
			dateLUpanel.setVisible(false);
		}
	}

	private void resetDatePanel(VerticalPanel datePanel, DateFromToPanel dateFromToPanel, VerticalPanel dateLUpanel, DateLastUpdatePanel dateLastUpdatePanel, ListBox dimensionXValues, HorizontalPanel xValuesPanel) {
		dateLastUpdatePanel.getCheckBox().setValue(false);
		dateFromToPanel.getCheckBox().setValue(false);
		dimensionXValues.setEnabled(true);
		xValuesPanel.setEnabled(true);
		dateLastUpdatePanel.getMonths().setEnabled(false);
		dateLastUpdatePanel.getYears().setEnabled(false);
		dateLastUpdatePanel.getDays().setEnabled(false);
		dateFromToPanel.getYearFrom().setEnabled(false);
		dateFromToPanel.getMonthFrom().setEnabled(false);
		dateFromToPanel.getDayFrom().setEnabled(false);
		dateFromToPanel.getYearTo().setEnabled(false);
		dateFromToPanel.getMonthTo().setEnabled(false);
		dateFromToPanel.getDayTo().setEnabled(false);
		datePanel.setVisible(false);
		dateLUpanel.setVisible(false);
	}

	public SelectionListener<ButtonEvent> addToFilter(final ListBox dimensionValues, final ListBox selectedValues, final DataList dimensionDataList, final DateFromToPanel dateFromToPanel, final DateLastUpdatePanel dateLastUpdatePanel) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				String selectedDimensionLabel = dimensionDataList.getSelectedItem().getText();
				StringBuilder dimensionBuilder = new StringBuilder();
				if (!dateFromToPanel.getCheckBox().getValue() && !dateLastUpdatePanel.getCheckBox().getValue()) {
					for (TableFilterVO filter : filters) {
						if (filter.getHeader().equals(selectedDimensionLabel)) {
							filter.getAllowedValues().clear();
							filter.getShowValues().clear();
							for (int i = 0; i < selectedValues.getItemCount(); i++) {
								if (selectedValues.isItemSelected(i)) {
									filter.addAllowedValues(selectedValues.getValue(i));
									filter.addShowValues(selectedValues.getItemText(i));
									filter.getSelectedValuesMap().put(selectedValues.getValue(i), selectedValues.getItemText(i));
								}
							}
						}
					}
				}
				if (dateFromToPanel.getCheckBox().getValue()) {
					for (TableFilterVO filter : filters) {
						if (filter.getHeader().equals(selectedDimensionLabel)) {
							filter.getAllowedValues().clear();
							filter.getShowValues().clear();
							Date fromDate = DateUtils.setFromDate(dateFromToPanel);
							Date toDate = DateUtils.setToDate(dateFromToPanel);
							List<List<String>> dimX = DateUtils.getDates(fromDate, toDate, dimensionValues);
							for (List<String> date : dimX) {
								filter.addAllowedValues(date.get(0));
								filter.addShowValues(date.get(1));
								filter.getSelectedValuesMap().put(date.get(0), date.get(1));
							}
						}
					}
				}
				tableFilters.queryFilterFieldSet.removeAll();
				Boolean check = false;
				for (TableFilterVO filter : filters) {
					if (check && filter.getShowValues().size() > 0)
						dimensionBuilder.append(tableFilters.andSeparator);
					for (int i = 0; i < filter.getShowValues().size(); i++) {
						if (i == 0)
							dimensionBuilder.append("<b>").append(filter.getHeader()).append(" </b> = ");
						if (i != 0)
							dimensionBuilder.append(tableFilters.orSeparator);
						dimensionBuilder.append(filter.getShowValues().get(i));
						check = true;
					}
				}
				tableFilters.queryFilterFieldSet.add(new HTML(dimensionBuilder.toString()));
				tableFilters.clearFilterButton.enable();
				tableFilters.loadTableButton.enable();
				tableFilters.panel.layout();
			}
		};
	}

	public SelectionListener<ButtonEvent> getData(final TableVO tableVO, final Long resourceId, final TableViewWindow window, final String caller) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				getDataAgent(tableVO, resourceId, window, caller);
			}
		};
	}
	
	public void getDataAgent(final TableVO tableVO, final Long resourceId, final TableViewWindow window, final String caller) {
		List<TableFilterVO> filterList = getTableFilterVOList();
		for (TableFilterVO filter : filterList)
			if (filter.getAllowedValues() != null && filter.getAllowedValues().size() > 0)
				getFilterCriteria().put(filter.getDataType() + "-" + filter.getDimensionDescriptorId(), filter.getAllowedValues());
		Component gridComponent = window.getCenter().getItemByItemId("grid" + resourceId);
		window.tableGrid = new TableViewGrid(window);
		if (gridComponent != null)
			window.getCenter().remove(gridComponent);
		window.tableGrid.build(tableVO, resourceId, filterCriteria, caller);
		
		// set the listener to the Save Table View As
		IconButton saveTableViewButton = window.getTableToolbar().getIconButton(FenixToolbarConstants.SAVE);
		if (saveTableViewButton.getListeners(Events.Select).size() == 0);
			saveTableViewButton.addSelectionListener(TableSaver.saveTableView(tableVO, filterList, window.getTableViewID(), false));
			
		// set the listener to the Save Table View As
		IconButton saveTableViewAsButton = window.getTableToolbar().getIconButton(FenixToolbarConstants.SAVE_AS);
		if (saveTableViewAsButton.getListeners(Events.Select).size() == 0);
			saveTableViewAsButton.addSelectionListener(saveTableViewAs(resourceId, true, false, window, tableVO, filterList));
	}
	
	public static SelectionListener<IconButtonEvent> saveTableViewAs(final Long resourceId, final boolean isEditable, final boolean hasResourceSpecificFields, final TableViewWindow w, final TableVO tvo, final List<TableFilterVO> filterList) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				MESaveAs.prepopulateTableView(resourceId, true, false, w, tvo, filterList);
			}
		};
	}
	
	/**
	 * 1) Get TableVO
	 * 2) Build FilterCriteria
	 * 3) Load the Table
	 */
	public void loadUserSelections(final Long datasetID, final Long resourceViewID, final TableViewWindow window) {
		
		String language = CheckLanguage.getLanguage();
		final LoadingWindow one = new LoadingWindow("Table View", "Retrieve Dataset Details", "Please Wait...");
		
		TableServiceEntry.getInstance().getDatasetDetails(datasetID, language, new AsyncCallback<TableVO>() {
			
			public void onSuccess(final TableVO tableVO) {
				
				one.destroyLoadingBox();
//				final LoadingWindow two = new LoadingWindow("Table View", "Load Filter Criteria", "Please Wait...");
				
				TableServiceEntry.getInstance().loadFilterCriteria(resourceViewID, new AsyncCallback<Map<String,List<String>>>() {
					
					public void onSuccess(Map<String, List<String>> filterCriteria) {
						
						List<TableFilterVO> filterList = getTableFilterVOList();
						for (TableFilterVO filter : filterList)
							if (filter.getAllowedValues() != null && filter.getAllowedValues().size() > 0)
								getFilterCriteria().put(filter.getDataType() + "-" + filter.getDimensionDescriptorId(), filter.getAllowedValues());
						Component gridComponent = window.getCenter().getItemByItemId("grid" + datasetID);
						window.tableGrid = new TableViewGrid(window);
						if (gridComponent != null)
							window.getCenter().remove(gridComponent);
						window.tableGrid.build(tableVO, datasetID, filterCriteria, "USER");
						
						IconButton saveTableViewButton = window.getTableToolbar().getIconButton(FenixToolbarConstants.SAVE);
						if (saveTableViewButton.getListeners(Events.Select).size() == 0);
							saveTableViewButton.addSelectionListener(TableSaver.saveTableView(tableVO, filterList, window.getTableViewID(), false));
							
						// set the listener to the Save Table View As
						IconButton saveTableViewAsButton = window.getTableToolbar().getIconButton(FenixToolbarConstants.SAVE_AS);
						if (saveTableViewAsButton.getListeners(Events.Select).size() == 0);
							saveTableViewAsButton.addSelectionListener(saveTableViewAs(window.getTableViewID(), true, false, window, tableVO, filterList));
						
//						two.destroyLoadingBox();
						TableViewGridController.build(window.tableGrid, datasetID, filterCriteria, tableVO);
//						two.destroyLoadingBox();
					}
					
					public void onFailure(Throwable e) {
//						two.destroyLoadingBox();
						FenixAlert.error("ERROR", e.getMessage());
//						two.destroyLoadingBox();
					}
					
				});
				
				one.destroyLoadingBox();
				
			}
			
			public void onFailure(Throwable e) {
				one.destroyLoadingBox();
				FenixAlert.error("ERROR", e.getMessage());
				one.destroyLoadingBox();
			}
			
		});
		
	}

	public SelectionListener<ButtonEvent> clearFilter(final Long resourceId) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				List<TableFilterVO> filterList = getTableFilterVOList();
				if (filterList.size() > 0) {
					for (TableFilterVO filter : filterList) {
						filter.getAllowedValues().clear();
						filter.getShowValues().clear();
					}
					getFilterCriteria().clear();
				}
				tableFilters.queryFilterFieldSet.removeAll();
				tableFilters.clearFilterButton.disable();
				tableFilters.loadTableButton.disable();
				tableFilters.panel.layout();
			}
		};
	}

	public void getLatestData(final TableVO tableVO, final Long resourceId, final TableViewWindow window, final String caller) {
		TableServiceEntry.getInstance().getMostRecentDate(tableVO.getDatasetExplicitType(), new AsyncCallback<String>() {
			public void onSuccess(String result) {
				List<String> dimensionCodes = new ArrayList<String>();
				dimensionCodes.add(result);
				getFilterCriteria().put("date", dimensionCodes);
				Component gridComponent = window.getCenter().getItemByItemId("grid" + resourceId);
				window.tableGrid = new TableViewGrid(window);
				if (gridComponent != null)
					window.getCenter().remove(gridComponent);
				window.tableGrid.build(tableVO, resourceId, filterCriteria, caller);
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("getMostRecentDate RPC Failed", "TableController @ getLatestData");
			}
		});
	}

	public SelectionListener<IconButtonEvent> viewMetadata(final Long resourceId) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent evt) {
				MEOpener.showMetadata(resourceId, false, false); // check this!!
			}
		};
	}

	public SelectionListener<ButtonEvent> addAll(final ListBox dimensionValues, final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				selectedValues.clear();
				for (int i = 0; i < dimensionValues.getItemCount(); i++) {
					selectedValues.addItem(dimensionValues.getItemText(i), dimensionValues.getValue(i));
					selectedValues.setItemSelected(i, true);
				}
			}
		};
	}

	public SelectionListener<ButtonEvent> addSelectedValues(final ListBox dimensionValues, final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				for (int i = 0; i < dimensionValues.getItemCount(); i++) {
					if (dimensionValues.isItemSelected(i)) {
						if (selectedValues.getItemCount() != 0) {
							Boolean check = false;
							for (int j = 0; j < selectedValues.getItemCount(); j++) {
								if (selectedValues.getItemText(j).equals(dimensionValues.getItemText(i))) {
									check = true;
									break;
								}
							}
							if (!check)
								selectedValues.addItem(dimensionValues.getItemText(i), dimensionValues.getValue(i));
						} else
							selectedValues.addItem(dimensionValues.getItemText(i), dimensionValues.getValue(i));
					}
				}
				for (int i = 0; i < selectedValues.getItemCount(); i++)
					selectedValues.setItemSelected(i, true);
			}
		};
	}

	public SelectionListener<ButtonEvent> removeSelected(final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				ListBox backup = new ListBox();
				for (int i = 0; i < selectedValues.getItemCount(); i++) {
					if (!selectedValues.isItemSelected(i)) {
						backup.addItem(selectedValues.getItemText(i), selectedValues.getValue(i));
					}
				}
				selectedValues.clear();
				for (int i = 0; i < backup.getItemCount(); i++) {
					selectedValues.addItem(backup.getItemText(i), backup.getValue(i));
				}
			}
		};
	}
	
	public  SelectionListener<ButtonEvent> removeAll(final ListBox selectedValues) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				selectedValues.clear();	
			}
		};
	}

	public List<TableFilterVO> getTableFilterVOList() {
		return filters;
	}

	public Map<String, List<String>> getFilterCriteria() {
		return filterCriteria;
	}

	public Map<String, DimensionBeanVO> getQueryCache() {
		return queryCache;
	}

}