package org.fao.fenix.web.modules.fpi.client.view.dataentry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.fpi.client.control.dataentry.FPIDataEntryController;
import org.fao.fenix.web.modules.fpi.client.control.dataentry.FPIDataSaveController;
import org.fao.fenix.web.modules.fpi.common.services.FpiServiceEntry;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class FPIDataEntryParametersPanel {

	public VerticalPanel panel;

	public String[] parametersMap;

	public String[] listParametersMap;

	public Map<String, String[]> listValuesMap;

	public HorizontalPanel currenciesPanel;

	public FPIDataEntryParametersPanel() {
		panel = new VerticalPanel();
		listValuesMap = new HashMap<String, String[]>();
		initiateParametersMap();
		initiateListParametersMap();
		initiateListValuesMap();
	}

	public VerticalPanel build(VerticalPanel dataEntryPanel, FPIDataEntryPanel fpiDataEntryPanel) {
		HorizontalPanel parameterPanel;
		ListBox dayList = fpiDataEntryPanel.weekList;
		ListBox monthList = fpiDataEntryPanel.monthList;
		ListBox yearList = fpiDataEntryPanel.yearList;
		for (int i = 0; i < parametersMap.length; i++) {
			parameterPanel = buildParameterPanel(parametersMap[i]);
			panel.add(parameterPanel);
			panel.setData(parametersMap[i], parameterPanel);
			addDefaultValuesToThePanel(parameterPanel);
		}
		for (int i = 0; i < listParametersMap.length; i++) {
			parameterPanel = buildListParameterPanel(listParametersMap[i]);
			panel.add(parameterPanel);
			panel.setData(listParametersMap[i], parameterPanel);
			addDefaultValuesToThePanel(parameterPanel);
		}
		fillCurrenciesList();
		fillMeasurementUnitsList();
		panel.add(buildButtonsPanel(dataEntryPanel, dayList, monthList, yearList));
		return panel;
	}
	
	public HorizontalPanel buildButtonsPanel(VerticalPanel dataEntryPanel, ListBox dayList, ListBox monthList, ListBox yearList) {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		
		Button showButton = new Button(BabelFish.print().showExistingData());
		buttonsPanel.add(showButton);
		VerticalPanel monthlyPanel = (VerticalPanel)dataEntryPanel.getData("monthlyPanel");
		HorizontalPanel gaulPanel = (HorizontalPanel) panel.getData(BabelFish.print().gaulCode());
		HorizontalPanel hsPanel = (HorizontalPanel) panel.getData(BabelFish.print().commodityLabel());
		HorizontalPanel datasetTypePanel = (HorizontalPanel) panel.getData(BabelFish.print().datasetType());
		showButton.addSelectionListener(FPIDataEntryController.showExistingMonthlyData(monthlyPanel, yearList, hsPanel, gaulPanel, datasetTypePanel));
		
		Button saveButton = new Button(BabelFish.print().save());
		buttonsPanel.add(saveButton);
		saveButton.addSelectionListener(FPIDataSaveController.saveDataset(panel, dataEntryPanel, dayList, monthList, yearList));
		buttonsPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		return buttonsPanel;
	}

	public void addDefaultValuesToThePanel(HorizontalPanel panel) {
		panel.setData("gaulCode", "0");
		panel.setData("gaulName", BabelFish.print().gaulWorld());
		panel.setData("hsCode", BabelFish.print().gaulNoneSelectedYet());
		panel.setData("hsName", BabelFish.print().gaulNoneSelectedYet());
	}

	public HorizontalPanel buildParameterPanel(String parameter) {
		HorizontalPanel parameterPanel = new HorizontalPanel();
		parameterPanel.setSpacing(5);
		HTML label = new HTML("<b>" + parameter + ": </b>");
		HTML value = new HTML(BabelFish.print().gaulNoneSelectedYet());
		Button button = new Button(BabelFish.print().select());
		button.setTitle(BabelFish.print().select() + "  " + parameter);
		button.addSelectionListener(FPIDataEntryController.buildSelectParameterListener(parameter, parameterPanel));
		parameterPanel.add(label);
		parameterPanel.add(value);
		parameterPanel.setData("parameterName", parameter);
		parameterPanel.setData("parameterValue", value);
		parameterPanel.add(button);
		label.setWidth("150px");
		value.setWidth("100px");
		return parameterPanel;
	}

	public HorizontalPanel buildListParameterPanel(String parameter) {
		HorizontalPanel parameterPanel = new HorizontalPanel();
		parameterPanel.setSpacing(5);
		HTML label = new HTML("<b>" + parameter + ": </b>");
		ListBox list = new ListBox();
		String[] listValues = listValuesMap.get(parameter);
		for (int i = 0; i < listValues.length; i++)
			list.addItem(listValues[i]);
		parameterPanel.add(label);
		parameterPanel.add(list);
		parameterPanel.setData("parameterName", parameter);
		parameterPanel.setData("parameterValue", list);
		label.setWidth("150px");
		list.setWidth("150px");
		return parameterPanel;
	}

	public HorizontalPanel buildTextParameterPanel(String parameter) {
		HorizontalPanel parameterPanel = new HorizontalPanel();
		parameterPanel.setSpacing(5);
		HTML label = new HTML("<b>" + parameter + ": </b>");
		TextBox text = new TextBox();
		parameterPanel.add(label);
		parameterPanel.add(text);
		parameterPanel.setData("parameterName", parameter);
		parameterPanel.setData("parameterValue", text);
		label.setWidth("150px");
		text.setWidth("150px");
		text.setText("1000");
		return parameterPanel;
	}

	public void setParameterValue(HorizontalPanel panel, Object value) {
		((HTML) panel.getData("parameter")).setText(value.toString());
	}

	public void initiateParametersMap() {
		parametersMap = new String[] { BabelFish.print().gaulCode(), BabelFish.print().commodityLabel() };
	}

	public void initiateListParametersMap() {
		listParametersMap = new String[] { BabelFish.print().datasetType(), BabelFish.print().measurementUnit(), BabelFish.print().currency(), BabelFish.print().accessType(), BabelFish.print().dataSource(),
				BabelFish.print().transportType() };
	}

	public void initiateListValuesMap() {
		listValuesMap.put(BabelFish.print().datasetType(), new String[] { BabelFish.print().consumerPrice(), BabelFish.print().inputPrice(), BabelFish.print().internationalPrice(),
				BabelFish.print().producerPrice(), BabelFish.print().retailPrice(), BabelFish.print().wholesalePrice() });
		listValuesMap.put(BabelFish.print().accessType(), new String[] { BabelFish.print().publicAccess(), BabelFish.print().sharedAccess(), BabelFish.print().privateAccess() });
		listValuesMap.put(BabelFish.print().measurementUnit(), new String[] {});

		listValuesMap.put(BabelFish.print().dataSource(), new String[] { "Dairy Market News (USDA)", "Pink Sheet-Worldbank", "SAGPyA", "ALIC", "USDA-FAS", "USDA - Livestock, Dairy & Poultry Situation and Outlook", "International Sugar Agreement Daily Price", "IMF - International Monetary Fund", "ICAC - Cotton  International Cotton Advisory Committee", "Oil World", "Jackson Son & Co. (London) Ltd", "Trade and Industry Dpt., Hong Kong SAR", "The Public Ledger", "ONIGC" });

		listValuesMap.put(BabelFish.print().transportType(), new String[] { BabelFish.print().land(), BabelFish.print().air(), BabelFish.print().sea() });
		listValuesMap.put(BabelFish.print().currency(), new String[] {});
	}

	public void fillCurrenciesList() {
		FpiServiceEntry.getInstance().findAllCurrencies(new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> result) {
				HorizontalPanel parameterPanel = panel.getData(BabelFish.print().currency());
				ListBox list = parameterPanel.getData("parameterValue");
				list.addItem("");
				for (int i = 0; i < result.size(); i++)
					list.addItem(result.get(i));
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("FPIDataEntryParametersPanel", "RPC failure on 'fillCurrenciesList'");
			}
		});
	}

	public void fillMeasurementUnitsList() {
		FpiServiceEntry.getInstance().findAllMeasurementUnits(new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> result) {
				HorizontalPanel parameterPanel = panel.getData(BabelFish.print().measurementUnit());
				ListBox list = parameterPanel.getData("parameterValue");
				list.addItem("");
				for (int i = 0; i < result.size(); i++)
					list.addItem(result.get(i));
			}

			public void onFailure(Throwable caught) {
				FenixAlert.alert("FPIDataEntryParametersPanel", "RPC failure on 'fillMeasurementUnitsList'");
			}
		});
	}

}
