package org.fao.fenix.web.modules.table.client.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.core.client.utils.CheckLanguage;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.TableTypeExportWindow;
import org.fao.fenix.web.modules.table.client.view.TableViewTypeExportWindow;
import org.fao.fenix.web.modules.table.client.view.TableViewWindow;
import org.fao.fenix.web.modules.table.client.view.TableWindow;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;
import org.fao.fenix.web.modules.table.common.services.TableServiceEntry;
import org.fao.fenix.web.modules.table.common.vo.DimensionBeanVO;
import org.fao.fenix.web.modules.table.common.vo.TableVO;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TableViewToolbarController {

	 static String datasetPrecision = "2";
		
	 
	public static SelectionListener<IconButtonEvent> buildExportToSelectionListener(final TableViewWindow tableWindow, final Long resourceId, final String typeExport) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				final Map<String, List<String>> filterCriteria = tableWindow.getTableFilters().getTableController().getFilterCriteria();
				TableVO tableVO = (TableVO) tableWindow.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
				Map<String, DimensionBeanVO> dimensionDetailsMap = tableVO.getDimensionDescriptorMap();
				Iterator<Map.Entry<String, DimensionBeanVO>> iterator = dimensionDetailsMap.entrySet().iterator();
				final List<String> columnNames = new ArrayList<String>();
				columnNames.add("ID");
				for (int i = 0; i < dimensionDetailsMap.size(); i++) {
					Map.Entry<String, DimensionBeanVO> entry = iterator.next();
					columnNames.add(entry.getValue().getHeader());
				}
				final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().exportingTable(), BabelFish.print().pleaseWait(), BabelFish.print().exporting());
				loadingWindow.showLoadingBox();
				TableServiceEntry.getInstance().getFilteredRecords(datasetPrecision, resourceId, columnNames, filterCriteria, CheckLanguage.getLanguage(), new AsyncCallback<List<List<String>>>() {
					public void onSuccess(List<List<String>> result) {
						result.add(0, columnNames);
						BirtServiceEntry.getInstance().exportTable(result, typeExport, new AsyncCallback<String>() {
							public void onSuccess(String result) {
								loadingWindow.destroyLoadingBox();
								Window.open(result, "_blank", "status=no");
							}

							public void onFailure(Throwable caught) {
								loadingWindow.destroyLoadingBox();
								FenixAlert.alert("RPC Failed: TableToolbarController:buildExportToSelectionListener", "@exportTable");
							}
						});
					}

					public void onFailure(Throwable caught) {
						loadingWindow.destroyLoadingBox();
						FenixAlert.alert("RPC Failed: TableToolbarController:buildExportToSelectionListener", "@ getFilteredRecords");
					}
				});
			}
		};
	}

	public static SelectionListener<IconButtonEvent> excelExport(final TableViewWindow tableWindow, final Long resourceId) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent event) {
				TableViewTypeExportWindow window = new TableViewTypeExportWindow(tableWindow, resourceId);
			}
		};
	}

	public static SelectionListener<ButtonEvent> exportToExcel(final TableViewTypeExportWindow window, final TableViewWindow tableWindow, final Long resourceId) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				if (window.getCode().getValue() || window.getLabel().getValue() || window.getCodelabel().getValue()) {
					String t = new String();
					if (window.getCode().getValue())
						t = "code";
					else if (window.getLabel().getValue())
						t = "label";
					else if (window.getCodelabel().getValue())
						t = "codelabel";
					final String type = t;
					window.getWindow().close();
					final Map<String, List<String>> filterCriteria = tableWindow.getTableFilters().getTableController().getFilterCriteria();
					TableVO tableVO = (TableVO) tableWindow.getTableFilters().getPanel().getData(TableConstants.TABLE_VO);
					Map<String, DimensionBeanVO> dimensionDetailsMap = tableVO.getDimensionDescriptorMap();
					Iterator<Map.Entry<String, DimensionBeanVO>> iterator = dimensionDetailsMap.entrySet().iterator();
					final List<String> columnNames = new ArrayList<String>();
					for (int i = 0; i < dimensionDetailsMap.size(); i++) {
						Map.Entry<String, DimensionBeanVO> entry = iterator.next();
						columnNames.add(entry.getValue().getHeader());
					}
					final LoadingWindow loadingWindow = new LoadingWindow(BabelFish.print().exportingTable(), BabelFish.print().pleaseWait(), BabelFish.print().exporting());
					loadingWindow.showLoadingBox();
					
					TableServiceEntry.getInstance().getFilteredRecordsToExport("", resourceId, columnNames, filterCriteria, type, CheckLanguage.getLanguage(), new AsyncCallback<List<List<String>>>() {
						public void onSuccess(List<List<String>> r) {
							TableServiceEntry.getInstance().createExcel(resourceId, type, tableWindow.getTableFilters().getPanel().getHeading(), r, null, new AsyncCallback<String>() {
								public void onSuccess(String result) {
									Window.open("../olapExcel/" + result, "_blank", "status=no");
									loadingWindow.destroyLoadingBox();
								}

								public void onFailure(Throwable caught) {
									FenixAlert.alert("RPC Failed", "exportChartAndTable");
									loadingWindow.destroyLoadingBox();
								}
							});
						}

						public void onFailure(Throwable caught) {
							loadingWindow.destroyLoadingBox();
							FenixAlert.alert("RPC Failed: TableToolbarController:buildExportToSelectionListener", "@ getFilteredRecords");
						}
					});
				}
			}
		};
	}

}