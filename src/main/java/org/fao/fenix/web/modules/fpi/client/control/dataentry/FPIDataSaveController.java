package org.fao.fenix.web.modules.fpi.client.control.dataentry;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.fpi.common.services.FpiServiceEntry;
import org.fao.fenix.web.modules.fpi.common.vo.SaveDataVo;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public class FPIDataSaveController {

	private static HorizontalPanel gaulPanel;

	private static HorizontalPanel commodityPanel;

	private static HorizontalPanel datasetTypePanel;

	private static HorizontalPanel measurementUnitPanel;

	private static HorizontalPanel currencyPanel;

	private static HorizontalPanel acccessTypePanel;

	private static HorizontalPanel dataSourcePanel;

	private static HorizontalPanel transportTypePanel;

	private static String gaulCode;

	private static String commodityCode;

	private static String datasetType;

	private static String measurementUnit;

	private static String currency;

	private static String accessType;

	private static String dataSourceType;

	private static String transportType;
	
	private static String periodType;
	
	private static Map<String, String> priceTypeMap;
	
	static {
		priceTypeMap = new HashMap<String, String>();
		priceTypeMap.put(BabelFish.print().consumerPrice(), "Consumer");
		priceTypeMap.put(BabelFish.print().inputPrice(), "Input");
		priceTypeMap.put(BabelFish.print().internationalPrice(), "International");
		priceTypeMap.put(BabelFish.print().producerPrice(), "Producer");
		priceTypeMap.put(BabelFish.print().wholesalePrice(), "Wholesale");
		priceTypeMap.put(BabelFish.print().retailPrice(), "Retail");
	}

	public static SelectionListener<ButtonEvent> saveDataset(final VerticalPanel parametersPanel, final VerticalPanel dataEntryPanel, final ListBox dayList, final ListBox monthList, final ListBox yearList) {
		return new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent event) {
				retrieveParameters(parametersPanel);
				int day = 1 + dayList.getSelectedIndex();
				int month = monthList.getSelectedIndex();
				int year = Integer.parseInt(yearList.getItemText(yearList.getSelectedIndex()));
				//System.out.println(">>>>>>>>>> " + year);
				TabPanel tabPanel = dataEntryPanel.getData("tabPanel");
				periodType = tabPanel.getSelectedItem().getText();
				if (periodType.equals(BabelFish.print().dailyData()))
					saveDailyData(day, month, year);
				else if (periodType.equals(BabelFish.print().weeklyData()))
					saveWeekilyData(day, month, year);
				else if (periodType.equals(BabelFish.print().monthlyData()))
					saveMonthlyData(year);
			}
		};
	}
	
	public static List<SaveDataVo> retrieveDailyData(int collectionDay, int month, int year) {
		List<SaveDataVo> list = new ArrayList<SaveDataVo>();
		Date date = FPIDataEntryController.calculateStartDate(collectionDay, new Date(year - 1900, month, 1));
		for (int i = 1 ; i <= FPIDataEntryConstants.week2ArrayMap.size() ; i++) { 
			for (int j = 0 ; j < FPIDataEntryConstants.week2ArrayMap.get(i).length ; j++) { 
				SaveDataVo vo = new SaveDataVo();
				vo.setAccessType(accessType);
				vo.setCommodityCode(commodityCode);
				vo.setCurrency(currency);
				vo.setDatasetType(datasetType);
				vo.setDataSource(dataSourceType);
				vo.setMeasurementUnit(measurementUnit);
				vo.setPeriodType(periodType);
				vo.setRegion(gaulCode);
				vo.setTransportType(transportType);
				vo.setValue(FPIDataEntryConstants.week2ArrayMap.get(i)[j]);
				vo.setDate(date);
				date = upgradeDailyDate(date);
				list.add(vo);
			}
		}
		return list;
	}
	
	public static Date upgradeDailyDate(Date date) {
		int year = date.getYear();
		int month = date.getMonth();
		int day = 1 + date.getDate();
		return new Date(year-1900, month, day);
	}
	
	public static void saveDailyData(int collectionDay, int month, int year) {
		List<SaveDataVo> list = retrieveDailyData(collectionDay, month, year);
		System.out.println("SAVE " + list.size() + " DAILY DATA FROM " + dataSourceType + " AS " + priceTypeMap.get(datasetType) + " PRICE");
		FpiServiceEntry.getInstance().saveDailyData(list, priceTypeMap.get(datasetType), dataSourceType, new AsyncCallback() {
			public void onSuccess(Object result) {
				FenixAlert.info(BabelFish.print().dataEntry(), "Data successfully saved.");
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert(BabelFish.print().dataEntry(), "Data saving failed!");
			}
		});
	}
	
	public static List<SaveDataVo> retrieveWeeklyData(int collectionDay, int month, int year) {
		List<SaveDataVo> list = new ArrayList<SaveDataVo>();
		for (int i = 0 ; i < FPIDataEntryConstants.month2ArrayMap.size() ; i++) {
			for (int j = 0 ; j < FPIDataEntryConstants.month2ArrayMap.get(i).length ; j++) { 
				SaveDataVo vo = new SaveDataVo();
				vo.setAccessType(accessType);
				vo.setCommodityCode(commodityCode);
				vo.setCurrency(currency);
				vo.setDatasetType(datasetType);
				vo.setDataSource(dataSourceType);
				vo.setMeasurementUnit(measurementUnit);
				vo.setPeriodType(periodType);
				vo.setRegion(gaulCode);
				vo.setTransportType(transportType);
				vo.setValue(FPIDataEntryConstants.month2ArrayMap.get(i)[j]);
				vo.setDate(calculateWeeklyDate(j, collectionDay, i, year));
				list.add(vo);
			}
		}
		return list;
	}
	
	public static Date calculateWeeklyDate(int week, int collectionDay, int month, int year) {
		week += 1;
		Date date = new Date(year-1900, month, collectionDay);
		int weekNumber = 0;
		for (int i = 1 ; i < 32 ; i++) {
			Date forDate = new Date(year-1900, month, i);
			if (forDate.getDay() == collectionDay)
				weekNumber++;
			if (weekNumber == week) 
				date = forDate;
		}
		return date;
	}
	
	public static void saveWeekilyData(int day, int month, int year) {
		List<SaveDataVo> list = retrieveWeeklyData(day, month, year);
		FpiServiceEntry.getInstance().saveWeeklyData(list, datasetType, dataSourceType, new AsyncCallback() {
			public void onSuccess(Object result) {
				FenixAlert.info(BabelFish.print().dataEntry(), "Data successfully saved.");
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert(BabelFish.print().dataEntry(), "Data saving failed!");
			}
		});
	}
	
	public static List<SaveDataVo> retrieveMonthlyData(int year) {
		List<SaveDataVo> list = new ArrayList<SaveDataVo>();
		for (int i = 0 ; i < 12 ; i++) {
			SaveDataVo vo = new SaveDataVo();
			vo.setAccessType(accessType);
			vo.setCommodityCode(commodityCode);
			vo.setCurrency(currency);
			vo.setDatasetType(datasetType);
			vo.setDataSource(dataSourceType);
			vo.setMeasurementUnit(measurementUnit);
			vo.setPeriodType(periodType);
			vo.setRegion(gaulCode);
			vo.setTransportType(transportType);
			vo.setValue(FPIDataEntryConstants.year[i]);
			vo.setDate(new Date(year-1900, i, 1));
			list.add(vo);
		}
		return list;
	}
	
	public static void saveMonthlyData(int year) {
		List<SaveDataVo> list = retrieveMonthlyData(year);
		FpiServiceEntry.getInstance().saveMonthlyData(list, datasetType, dataSourceType, new AsyncCallback() {
			public void onSuccess(Object result) {
				FenixAlert.info(BabelFish.print().dataEntry(), "Data successfully saved.");
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert(BabelFish.print().dataEntry(), "Data saving failed!");
			}
		});
	}

	public static void retrieveParameters(VerticalPanel parametersPanel) {
		gaulPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().gaulCode());
		commodityPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().commodityLabel());
		datasetTypePanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().datasetType());
		measurementUnitPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().measurementUnit());
		currencyPanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().currency());
		acccessTypePanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().accessType());
		dataSourcePanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().dataSource());
		transportTypePanel = (HorizontalPanel) parametersPanel.getData(BabelFish.print().transportType());
		gaulCode = (String) gaulPanel.getData("gaulCode");
		commodityCode = (String) commodityPanel.getData("hsCode");
		datasetType = ((ListBox) datasetTypePanel.getData("parameterValue")).getItemText(((ListBox) datasetTypePanel.getData("parameterValue")).getSelectedIndex());
		
		measurementUnit = ((ListBox) measurementUnitPanel.getData("parameterValue")).getItemText(((ListBox) measurementUnitPanel.getData("parameterValue")).getSelectedIndex());
		currency = ((ListBox) currencyPanel.getData("parameterValue")).getItemText(((ListBox) currencyPanel.getData("parameterValue")).getSelectedIndex());
		
		accessType = ((ListBox) acccessTypePanel.getData("parameterValue")).getItemText(((ListBox) acccessTypePanel.getData("parameterValue")).getSelectedIndex());
		dataSourceType = ((ListBox) dataSourcePanel.getData("parameterValue")).getItemText(((ListBox) dataSourcePanel.getData("parameterValue")).getSelectedIndex());
		transportType = ((ListBox) transportTypePanel.getData("parameterValue")).getItemText(((ListBox) transportTypePanel.getData("parameterValue")).getSelectedIndex());
	}
}
