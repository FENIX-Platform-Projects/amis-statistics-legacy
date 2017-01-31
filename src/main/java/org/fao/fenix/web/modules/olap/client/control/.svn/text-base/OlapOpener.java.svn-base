/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.fao.fenix.web.modules.olap.client.control;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.client.view.AxisPanel;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.olap.client.view.SingleDatasetPanel;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.DescriptorViewVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewSettingVO;
import org.fao.fenix.web.modules.olap.common.vo.ResourceViewVO;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class OlapOpener {

	public static void open(Long olapID, List<DatasetVO> vos) {
		OlapWindow ow = new OlapWindow();
		ow.setResourceViewID(olapID);
		if (FenixUser.hasUserRole() || FenixUser.hasAdminRole()) {
			ow.build(true, true);
		} else {
			ow.build(true, false);
		}
		loadFullInformation(ow, vos);
		loadUserSettings(olapID, ow);
		ow.getWindow().show();
		enableToolbar(ow);
	}
	
	private static void enableToolbar(OlapWindow ow) {
		ow.getOlapToolbar().getExportExcel().setEnabled(true);
		ow.getOlapToolbar().getExportHTML().setEnabled(true);
		ow.getOlapToolbar().getExport().setEnabled(true);
	}
	
	private static void enableSaveAsButton(OlapWindow ow) {
		if (ow.getOlapToolbar().getSaveAs() != null) {
			IconButton saveAsButton = ow.getOlapToolbar().getSaveAs();
			saveAsButton.setEnabled(true);
			saveAsButton.addSelectionListener(OlapController.saveAs(ow));
		}
	}
	
	/** Load all the dataset related information */
	private static void loadFullInformation(OlapWindow ow, List<DatasetVO> vos) {
		fillDatasource(ow, vos);
		fillDimensions(ow, vos);
	}
	
	/** Load OLAP user's settings */
	private static void loadUserSettings(final Long olapID, final OlapWindow ow) {
		
		final LoadingWindow loading = new LoadingWindow(BabelFish.print().information(), BabelFish.print().loading(), BabelFish.print().loading());
		
		OlapServiceEntry.getInstance().loadOlapUserSettings(olapID, new AsyncCallback<ResourceViewVO>() {
			
			public void onSuccess(ResourceViewVO rvo) {
				loading.destroyLoadingBox();
				selectFunction(ow, rvo);
				selectDimensions(ow, rvo);
				selectPlotDimension(ow, rvo);
				selectSettings(ow, rvo);
				selectFilters(ow, rvo);
				setWidthsAndHeights(ow, rvo);
				ow.getHtml().setHTML(rvo.getOlapHTML());
				ow.setResourceViewID(Long.valueOf(rvo.getResourceId()));
				enableSaveAsButton(ow);
				loading.destroyLoadingBox();
			}
			
			public void onFailure(Throwable e) {
				loading.destroyLoadingBox();
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				loading.destroyLoadingBox();
			}
			
		});
		
	}
	
	private static void setWidthsAndHeights(OlapWindow ow, ResourceViewVO rvo) {
		for (ResourceViewSettingVO svo : rvo.getSettings()) {
			if (svo.getSettingName().startsWith("WIDTH_")) {
				int idx = 1 + svo.getSettingName().indexOf("_");
				String columnName = svo.getSettingName().substring(idx).replaceAll("_", " ");
				TextField<String> tf = new TextField<String>();
				tf.setWidth(svo.getValue());
				tf.setValue(svo.getValue().substring(0, svo.getValue().indexOf("px")));
				ow.getOlapRuler().getColumnLabelWidths().put(columnName, tf);
			} else if (svo.getSettingName().startsWith("HEIGHT_")) {
				int idx = 1 + svo.getSettingName().indexOf("_");
				String columnName = svo.getSettingName().substring(idx).replaceAll("_", " ");
				TextField<String> tf = new TextField<String>();
				tf.setWidth("50px");
				tf.setValue(svo.getValue().substring(0, svo.getValue().indexOf("px")));
				ow.getOlapRuler().getRowLabelHeights().put(columnName, tf);
			}
		}
	}
	
	private static void selectSettings(OlapWindow ow, ResourceViewVO rvo) {
		SingleDatasetPanel panel = ow.getTabPanel().getSingleDatasetPanel();
		for (ResourceViewSettingVO svo : rvo.getSettings()) {
			try {
				ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
				switch (s) {
					case SHOW_SINGLE_ELEMENTS:
						panel.getShowSingleElements().setValue(Boolean.valueOf(svo.getValue()));
					break;
					case SHOW_TOTALS:
						panel.getShowTotals().setValue(Boolean.valueOf(svo.getValue()));
					break;
					case SHOW_ROW_TOTALS:
						panel.getShowRowTotals().setValue(Boolean.valueOf(svo.getValue()));
					break;
					case SHOW_COL_TOTALS:
						panel.getShowColTotals().setValue(Boolean.valueOf(svo.getValue()));
					break;
					case REPORT_ORIENTATION:
						for (int i = 0 ; i < panel.getReportOrientation().getItemCount() ; i++)
							if (panel.getReportOrientation().getValue(i).equalsIgnoreCase(svo.getValue()))
								panel.getReportOrientation().setItemSelected(i, true);
					break;
					case DECIMALS: 
						for (int i = 0 ; i < panel.getDecimals().getItemCount() ; i++) {
							if (panel.getDecimals().getValue(i).equalsIgnoreCase(svo.getValue())) 
								panel.getDecimals().setItemSelected(i, true);
						}
					break;
					case COL_FONT_COLOR :
						panel.getColFontColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case COL_HEADER_COLOR :
						panel.getColHeaderColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case ROW_FONT_COLOR :
						panel.getRowFontColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case ROW_HEADER_COLOR :
						panel.getRowHeaderColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case SUB_COL_FONT_COLOR :
						panel.getSubColFontColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case SUB_COL_HEADER_COLOR :
						panel.getSubColHeaderColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case SUB_ROW_FONT_COLOR :
						panel.getSubRowFontColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case SUB_ROW_HEADER_COLOR :
						panel.getSubRowHeaderColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case CONTENT_BACKGROUND_COLOR :
						panel.getContentBackgroundColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case CONTENT_FONT_COLOR :
						panel.getContentFontColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case TITLE_FONT_COLOR :
						panel.getTitleFontColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case TITLE :
						panel.getOlapTitle().setValue(svo.getValue());
					break;
					case FONT_FAMILY :
						for (int i = 0 ; i < panel.getFontFamily().getItemCount() ; i++) {
							if (panel.getFontFamily().getValue(i).equalsIgnoreCase(svo.getValue())) 
								panel.getFontFamily().setItemSelected(i, true);
						}
					break;
					case FONT_SIZE :
						for (int i = 0 ; i < panel.getFontSize().getItemCount() ; i++) {
							if (panel.getFontSize().getValue(i).equalsIgnoreCase(svo.getValue())) 
								panel.getFontSize().setItemSelected(i, true);
						}
					break;
					case SUMMARY_BACKGROUND_COLOR :
						panel.getSummaryBackgroundColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case SUMMARY_FONT_COLOR :
						panel.getSummaryFontColor().setHTML("<div align='center' style='border: 3px black solid; background-color: " + svo.getValue() + "; color: white; font-weight: bold; '>" + svo.getValue() + "</div>");
					break;
					case X_USE_FROM_DATE_TO_DATE:
						panel.getXAxis().setUseFromDateToDate(Boolean.valueOf(svo.getValue()));
					break;
					case Z_USE_FROM_DATE_TO_DATE:
						panel.getZAxis().setUseFromDateToDate(Boolean.valueOf(svo.getValue()));
					break;
					case W_USE_FROM_DATE_TO_DATE:
						panel.getWAxis().setUseFromDateToDate(Boolean.valueOf(svo.getValue()));
					break;
					case Y_USE_FROM_DATE_TO_DATE:
						panel.getYAxis().setUseFromDateToDate(Boolean.valueOf(svo.getValue()));
					break;
					case X_USE_MOST_RECENT_DATA:
						panel.getXAxis().setUseMostRecentData(Boolean.valueOf(svo.getValue()));
					break;
					case Z_USE_MOST_RECENT_DATA:
						panel.getZAxis().setUseMostRecentData(Boolean.valueOf(svo.getValue()));
					break;
					case W_USE_MOST_RECENT_DATA:
						panel.getWAxis().setUseMostRecentData(Boolean.valueOf(svo.getValue()));
					break;
					case Y_USE_MOST_RECENT_DATA:
						panel.getYAxis().setUseMostRecentData(Boolean.valueOf(svo.getValue()));
					break;
					case X_LATEST_YEARS:
						if (panel.getXAxis().getLatestYearList() != null) {
							for (int i = 0 ; i < panel.getXAxis().getLatestYearList().getItemCount() ; i++) {
								if (panel.getXAxis().getLatestYearList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getXAxis().getLatestYearList().setItemSelected(i, true);
							}
						}
					break;
					case X_LATEST_MONTHS:
						if (panel.getXAxis().getLatestMonthList() != null) {
							for (int i = 0 ; i < panel.getXAxis().getLatestMonthList().getItemCount() ; i++) {
								if (panel.getXAxis().getLatestMonthList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getXAxis().getLatestMonthList().setItemSelected(i, true);
							}
						}
					break;
					case X_LATEST_DAYS:
						if (panel.getXAxis().getLatestDayList() != null) {
							for (int i = 0 ; i < panel.getXAxis().getLatestDayList().getItemCount() ; i++) {
								if (panel.getXAxis().getLatestDayList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getXAxis().getLatestDayList().setItemSelected(i, true);
							}
						}
					break;
					case Z_LATEST_YEARS:
						if (panel.getZAxis().getLatestYearList() != null) {
							for (int i = 0 ; i < panel.getZAxis().getLatestYearList().getItemCount() ; i++) {
								if (panel.getZAxis().getLatestYearList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getZAxis().getLatestYearList().setItemSelected(i, true);
							}
						}
					break;
					case Z_LATEST_MONTHS:
						if (panel.getZAxis().getLatestMonthList() != null) {
							for (int i = 0 ; i < panel.getZAxis().getLatestMonthList().getItemCount() ; i++) {
								if (panel.getZAxis().getLatestMonthList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getZAxis().getLatestMonthList().setItemSelected(i, true);
							}
						}
					break;
					case Z_LATEST_DAYS:
						if (panel.getZAxis().getLatestDayList() != null) {
							for (int i = 0 ; i < panel.getZAxis().getLatestDayList().getItemCount() ; i++) {
								if (panel.getZAxis().getLatestDayList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getZAxis().getLatestDayList().setItemSelected(i, true);
							}
						}
					break;
					case Y_LATEST_YEARS:
						if (panel.getYAxis().getLatestYearList() != null) {
							for (int i = 0 ; i < panel.getYAxis().getLatestYearList().getItemCount() ; i++) {
								if (panel.getYAxis().getLatestYearList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getYAxis().getLatestYearList().setItemSelected(i, true);
							}
						}
					break;
					case Y_LATEST_MONTHS:
						if (panel.getYAxis().getLatestMonthList() != null) {
							for (int i = 0 ; i < panel.getYAxis().getLatestMonthList().getItemCount() ; i++) {
								if (panel.getYAxis().getLatestMonthList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getYAxis().getLatestMonthList().setItemSelected(i, true);
							}
						}
					break;
					case Y_LATEST_DAYS:
						if (panel.getYAxis().getLatestDayList() != null) {
							for (int i = 0 ; i < panel.getYAxis().getLatestDayList().getItemCount() ; i++) {
								if (panel.getYAxis().getLatestDayList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getYAxis().getLatestDayList().setItemSelected(i, true);
							}
						}
					break;
					case W_LATEST_YEARS:
						if (panel.getWAxis().getLatestYearList() != null) {
							for (int i = 0 ; i < panel.getWAxis().getLatestYearList().getItemCount() ; i++) {
								if (panel.getWAxis().getLatestYearList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getWAxis().getLatestYearList().setItemSelected(i, true);
							}
						}
					break;
					case W_LATEST_MONTHS:
						if (panel.getWAxis().getLatestMonthList() != null) {
							for (int i = 0 ; i < panel.getWAxis().getLatestMonthList().getItemCount() ; i++) {
								if (panel.getWAxis().getLatestMonthList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getWAxis().getLatestMonthList().setItemSelected(i, true);
							}
						}
					break;
					case W_LATEST_DAYS:
						if (panel.getWAxis().getLatestDayList() != null) {
							for (int i = 0 ; i < panel.getWAxis().getLatestDayList().getItemCount() ; i++) {
								if (panel.getWAxis().getLatestDayList().getValue(i).equalsIgnoreCase(svo.getValue())) 
									panel.getWAxis().getLatestDayList().setItemSelected(i, true);
							}
						}
					break;
					case Z_WIDTH:
						if ((svo.getValue() != null) && (!svo.getValue().equals("")))
							ow.getOlapRuler().setColumnLabelWidth(svo.getValue());
					break;
					case W_WIDTH:
						if ((svo.getValue() != null) && (!svo.getValue().equals("")))
							ow.getOlapRuler().setSubRowWidth(svo.getValue());
					break;
					case F_WIDTH:
						if ((svo.getValue() != null) && (!svo.getValue().equals("")))
							ow.getOlapRuler().setFunctionWidth(svo.getValue());
					break;
				}
			} catch (IllegalArgumentException e) {
				GWT.log("IllegalArgumentException: " + e.getMessage());
			}
		}
	}
	
	private static void selectPlotDimension(OlapWindow ow, ResourceViewVO rvo) {
		ListBox plotDimensionsList = ow.getTabPanel().getSingleDatasetPanel().getValues();
		DescriptorViewVO vo = getDescriptorViewVO(null, rvo);
		for (int i = 0 ; i < plotDimensionsList.getItemCount() ; i++)
			if ((plotDimensionsList.getItemText(i).equalsIgnoreCase(vo.getHeader())) && (plotDimensionsList.getValue(i).equalsIgnoreCase(vo.getContentDescriptor())))
				plotDimensionsList.setItemSelected(i, true);
	}
	
	private static void selectFilters(OlapWindow ow, ResourceViewVO rvo) {
		for (DescriptorViewVO dvo : rvo.getDescriptorViews())
			if ((dvo.getAxis() != null) && dvo.getAxis().equalsIgnoreCase(Axis.FILTER.name()))
				selectFilter(ow, dvo);
	}
	
	private static void selectFilter(OlapWindow ow, DescriptorViewVO dvo) {
		ListBox dataSource = ow.getTabPanel().getSingleDatasetPanel().getDataSource();
		if (dvo != null) {
			OlapController.addFilterAndSelectValuesAgent(ow.getFilterPanel(), dataSource, dvo.getContentDescriptor(), dvo.getHeader(), dvo.getValues());
		}
	}
	
	private static void selectDimensions(OlapWindow ow, ResourceViewVO rvo) {
		selectDimension(ow, Axis.X, rvo);
		selectDimension(ow, Axis.Z, rvo);
		selectDimension(ow, Axis.Y, rvo);
		selectDimension(ow, Axis.W, rvo);
	}
	
	private static void selectDimension(OlapWindow ow, Axis axis, ResourceViewVO rvo) {
		
		DescriptorViewVO dvo = getDescriptorViewVO(axis, rvo);
		
		if (dvo != null) {
		
			AxisPanel ap = getAxisPanel(axis, ow);
			ListBox dataSource = ow.getTabPanel().getSingleDatasetPanel().getDataSource();
			ListBox dimensionsList = ap.getDimensions();
			
			for (int i = 0 ; i < dimensionsList.getItemCount() ; i++)
				if ((dimensionsList.getItemText(i).equalsIgnoreCase(dvo.getHeader())) && (dimensionsList.getValue(i).equalsIgnoreCase(dvo.getContentDescriptor())))
					dimensionsList.setItemSelected(i, true);
			
			for (ResourceViewSettingVO svo : rvo.getSettings()) {
				try {
					ResourceViewSettings s = ResourceViewSettings.valueOf(svo.getSettingName()); 
					switch (s) {
						case X_USE_FROM_DATE_TO_DATE:
							ap.setUseFromDateToDate(true);
						break;
						case Z_USE_FROM_DATE_TO_DATE:
							ap.setUseFromDateToDate(true);
						break;
						case Y_USE_FROM_DATE_TO_DATE:
							ap.setUseFromDateToDate(true);
						break;
						case W_USE_FROM_DATE_TO_DATE:
							ap.setUseFromDateToDate(true);
						break;
						case X_USE_MOST_RECENT_DATA:
							ap.setUseMostRecentData(true);
						break;
						case Z_USE_MOST_RECENT_DATA:
							ap.setUseMostRecentData(true);
						break;
						case W_USE_MOST_RECENT_DATA:
							ap.setUseMostRecentData(true);
						break;
						case Y_USE_MOST_RECENT_DATA:
							ap.setUseMostRecentData(true);
						break;
					}
				} catch (IllegalArgumentException e) {
					GWT.log("IllegalArgumentException: " + e.getMessage());
				}
			}
			
//			OlapController.dimensionsChangeWithLabelsAgent(dataSource, dimensionsList, ap, dvo.getValues());
			OlapController.dimensionsChangeWithLabelsAgent(dataSource, dvo.getValues(), ap, rvo, axis.name());
			
//			if (dvo.getContentDescriptor().contains("date") || dvo.getContentDescriptor().contains("Date")) {
//				ap.getFromDateToDateViewButton().setEnabled(true);
//				ap.getFromDateToDateViewButton().addSelectionListener(OlapController.buildFromDateToDatePanel(ap));
//				ap.getMostRecentDataViewButton().setEnabled(true);
//				ap.getMostRecentDataViewButton().addSelectionListener(OlapController.buildMostRecentDataPanel(ap));
//			}
			
		}
	}
	
	private static void selectFunction(OlapWindow ow, ResourceViewVO rvo) {
		ListBox functionList = ow.getTabPanel().getSingleDatasetPanel().getFunction();
		for (int i = 0 ; i < functionList.getItemCount() ; i++)
			if (functionList.getValue(i).equalsIgnoreCase(rvo.getOlapFunction()))
				functionList.setItemSelected(i, true);
	}
	
	private static void fillDimensions(OlapWindow ow, List<DatasetVO> vos) {
		for (DatasetVO vo : vos) {
			fillDimension(ow, vo.getDescriptorViews(), Axis.X);
			fillDimension(ow, vo.getDescriptorViews(), Axis.Z);
			fillDimension(ow, vo.getDescriptorViews(), Axis.Y);
			fillDimension(ow, vo.getDescriptorViews(), Axis.W);
		}
	}
	
	private static void fillDimension(OlapWindow ow, List<DescriptorViewVO> dvos, Axis axis) {
		
		// get objects
		AxisPanel ap = getAxisPanel(axis, ow);
		ListBox dimensionsList = ap.getDimensions();
		ListBox valuesList = ap.getValues();
		List<DescriptorViewVO> plotDimensions = new ArrayList<DescriptorViewVO>();
		
		// clear lists
		for (int i = dimensionsList.getItemCount() - 1; i >= 0; i--)
			dimensionsList.removeItem(i);
		for (int i = valuesList.getItemCount() - 1; i >= 0; i--)
			valuesList.removeItem(i);
		dimensionsList.addItem(BabelFish.print().pleaseSelectADimension());
		
		// add dimensions
		for (DescriptorViewVO dvo : dvos) { 
			if (!dvo.getContentDescriptor().equals("quantity")) { 
				dimensionsList.addItem(dvo.getHeader(), dvo.getContentDescriptor());
			} else {
				plotDimensions.add(dvo);
			}
		}
		
		// fill dimension to plot list
		fillPlotDimension(ow, plotDimensions);
	}
	
	private static void fillPlotDimension(OlapWindow ow, List<DescriptorViewVO> plotDimensions) {
		ListBox plotDimensionsList = ow.getTabPanel().getSingleDatasetPanel().getValues();
		for (int i = plotDimensionsList.getItemCount() - 1; i >= 0; i--)
			plotDimensionsList.removeItem(i);
		for (DescriptorViewVO dvo : plotDimensions)
			plotDimensionsList.addItem(dvo.getHeader(), dvo.getContentDescriptor());
	}
	
	private static void fillDatasource(OlapWindow ow, List<DatasetVO> vos) {
		ListBox datasourceList = ow.getTabPanel().getSingleDatasetPanel().getDataSource();
		datasourceList.addItem(vos.get(0).getDatasetName(), vos.get(0).getDsId());
		datasourceList.setItemSelected((datasourceList.getItemCount() - 1), true);
	}
	
	private static DescriptorViewVO getDescriptorViewVO(Axis axis, ResourceViewVO rvo) {
		DescriptorViewVO vo = null;
		if (axis != null) {
			for (DescriptorViewVO dvo : rvo.getDescriptorViews())
				if ((dvo.getAxis() != null) && (dvo.getAxis().equalsIgnoreCase(axis.name())))
					vo = dvo;
		} else {
			for (DescriptorViewVO dvo : rvo.getDescriptorViews())
				if (dvo.getAxis() == null)
					vo = dvo;
		}
		return vo;
	}
	
	private static AxisPanel getAxisPanel(Axis axis, OlapWindow ow) {
		AxisPanel ap = null;
		switch (axis) {
			case X: ap = ow.getTabPanel().getSingleDatasetPanel().getXAxis(); break;
			case Z: ap = ow.getTabPanel().getSingleDatasetPanel().getZAxis(); break;
			case Y: ap = ow.getTabPanel().getSingleDatasetPanel().getYAxis(); break;
			case W: ap = ow.getTabPanel().getSingleDatasetPanel().getWAxis(); break;
		}
		return ap;
	}
	
	enum Axis {
		X, Y, Z, W, FILTER;
	}
	
	enum ResourceViewSettings {
		SHOW_SINGLE_ELEMENTS, SHOW_TOTALS, SHOW_ROW_TOTALS, SHOW_COL_TOTALS, 
		SHOW_BASE_LAYER, CHART_TYPE, USE_FLASH_CHARTS, REPORT_ORIENTATION, 
		NUMBER_OF_MAP_CLASSES, MAX_COLOR, MIN_COLOR, DECIMALS,
		COL_HEADER_COLOR, ROW_HEADER_COLOR, SUB_COL_HEADER_COLOR, SUB_ROW_HEADER_COLOR,
		COL_FONT_COLOR, ROW_FONT_COLOR, SUB_COL_FONT_COLOR, SUB_ROW_FONT_COLOR,
		CONTENT_BACKGROUND_COLOR, CONTENT_FONT_COLOR,
		FONT_FAMILY, FONT_SIZE,
		SUMMARY_FONT_COLOR, SUMMARY_BACKGROUND_COLOR, TITLE_FONT_COLOR,
		AGGREGATE_X_MONTHLY, AGGREGATE_Z_MONTHLY, AGGREGATE_Y_MONTHLY, AGGREGATE_W_MONTHLY,
		AGGREGATE_X_YEARLY, AGGREGATE_Z_YEARLY, AGGREGATE_Y_YEARLY, AGGREGATE_W_YEARLY,
		X_USE_FROM_DATE_TO_DATE, X_USE_MOST_RECENT_DATA,
		Z_USE_FROM_DATE_TO_DATE, Z_USE_MOST_RECENT_DATA,
		W_USE_FROM_DATE_TO_DATE, W_USE_MOST_RECENT_DATA,
		Y_USE_FROM_DATE_TO_DATE, Y_USE_MOST_RECENT_DATA,
		TITLE,
		X_LATEST_YEARS, X_LATEST_MONTHS, X_LATEST_DAYS, X_FROM_DATE, X_TO_DATE,
		Z_LATEST_YEARS, Z_LATEST_MONTHS, Z_LATEST_DAYS, Z_FROM_DATE, Z_TO_DATE,
		Y_LATEST_YEARS, Y_LATEST_MONTHS, Y_LATEST_DAYS, Y_FROM_DATE, Y_TO_DATE,
		W_LATEST_YEARS, W_LATEST_MONTHS, W_LATEST_DAYS, W_FROM_DATE, W_TO_DATE,
		Z_WIDTH, W_WIDTH, F_WIDTH,
		DATES_FORMAT, SHOW_ROW_VARIATION, SHOW_COLUMN_VARIATION;
	}
	
}
