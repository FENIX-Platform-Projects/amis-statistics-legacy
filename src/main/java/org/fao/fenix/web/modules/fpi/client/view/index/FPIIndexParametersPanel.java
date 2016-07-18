package org.fao.fenix.web.modules.fpi.client.view.index;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.fpi.client.control.FPIController;
import org.fao.fenix.web.modules.fpi.client.control.index.FPIIndexController;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class FPIIndexParametersPanel {

	public VerticalPanel panel;

	public String[] parametersMap;

	public String[] listParametersMap;

	public String[] textParametersMap;

	public Map<String, String[]> listValuesMap;
	
	public FPIIndexParametersPanel() {
		panel = new VerticalPanel();
		initiateParametersMap();
		initiateListParametersMap();
		initiateTextParametersMap();
		initiateListValuesMap();
	}

	@SuppressWarnings("deprecation")
	public VerticalPanel build(Table table) {
		HorizontalPanel parameterPanel;
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
		/* base quantity box removed
		for (int i = 0; i < textParametersMap.length; i++) {
			parameterPanel = buildTextParameterPanel(textParametersMap[i]);
			panel.add(parameterPanel);
			panel.setData(textParametersMap[i], parameterPanel);
			addDefaultValuesToThePanel(parameterPanel);
		}
		*/
		addIndexListener();
		panel.add(buildButtonsPanel(table));
		return panel;
	}
	
	@SuppressWarnings("deprecation")
	public void addIndexListener() {
		HorizontalPanel indexPanel = (HorizontalPanel) panel.getData(BabelFish.print().indexType());
		ListBox indexList = (ListBox) indexPanel.getData("parameterValue");
		indexList.addChangeListener(FPIIndexController.showSubIndices(panel));
	}

	public void addDefaultValuesToThePanel(HorizontalPanel panel) {
		panel.setData("gaulCode", "0");
		panel.setData("gaulName", BabelFish.print().gaulWorld());
		panel.setData("hsCode", BabelFish.print().gaulNoneSelectedYet());
		panel.setData("hsName", BabelFish.print().gaulNoneSelectedYet());
	}

	@SuppressWarnings("deprecation")
	public HorizontalPanel buildButtonsPanel(Table table) {
		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(10);
		Button calculateButton = new Button(BabelFish.print().calculate());
		Button clearTableButton = new Button(BabelFish.print().clearTable());
		buttonsPanel.add(calculateButton);
		buttonsPanel.add(clearTableButton);
		calculateButton.addSelectionListener(FPIIndexController.buildCalculateIndexSelectionListener(panel, table));
		clearTableButton.addSelectionListener(FPIIndexController.buildClearTableSelectionListener(table));
		buttonsPanel.setHorizontalAlign(HorizontalAlignment.CENTER);
		return buttonsPanel;
	}

	public HorizontalPanel buildParameterPanel(String parameter) {
		HorizontalPanel parameterPanel = new HorizontalPanel();
		parameterPanel.setSpacing(5);
		HTML label = new HTML("<b>" + parameter + ": </b>");
		HTML value = new HTML(BabelFish.print().gaulNoneSelectedYet());
		Button button = new Button(BabelFish.print().select());
		button.setTitle(BabelFish.print().select() + "  " + parameter);
		button.addSelectionListener(FPIController.buildSelectParameterListener(parameter, parameterPanel));
		parameterPanel.add(label);
		parameterPanel.add(value);
		parameterPanel.setData("parameterName", parameter);
		parameterPanel.setData("parameterValue", value);
		parameterPanel.add(button);
		label.setWidth("150px");
		value.setWidth("100px");
		button.setEnabled(false);
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
		/* SET DEFAULT VALUE */
		if (parameter.equals(BabelFish.print().basePriceStartYear())) {
			list.setEnabled(false);
		} else if (parameter.equals(BabelFish.print().basePriceEndYear())) {
			list.setEnabled(false);
		} else if (parameter.equals(BabelFish.print().indexStartYear())) {
			list.setSelectedIndex(3);
		} else if (parameter.equals(BabelFish.print().indexEndYear())) {
			list.setSelectedIndex(1);
		} else if (parameter.equals(BabelFish.print().priceTypeLabel())) {
			list.setSelectedIndex(2);
		} else if (parameter.equals(BabelFish.print().timePeriod())) {
			list.setSelectedIndex(0);
		}
		/* SET DEFAULT VALUE */
		parameterPanel.add(label);
		parameterPanel.add(list);
		parameterPanel.setData("parameterName", parameter);
		parameterPanel.setData("parameterValue", list);
		label.setWidth("150px");
		list.setWidth("150px");
		if (parameter.equalsIgnoreCase(BabelFish.print().priceTypeLabel()))
			list.setEnabled(false);
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
		parametersMap = new String[] { BabelFish.print().gaulCode()}; //, I18N.print().commodityLabel() };
	}

	public void initiateListParametersMap() {
		listParametersMap = new String[] { BabelFish.print().priceTypeLabel(), BabelFish.print().basePriceStartYear(), BabelFish.print().basePriceEndYear(), BabelFish.print().indexStartYear(), BabelFish.print().indexEndYear(), BabelFish.print().indexType(), BabelFish.print().subIndexType(), BabelFish.print().timePeriod() };
	}

	public void initiateTextParametersMap() {
		textParametersMap = new String[] { BabelFish.print().baseQuantity() };
	}

	@SuppressWarnings("deprecation")
	public void initiateListValuesMap() {
		listValuesMap = new HashMap<String, String[]>();
		listValuesMap.put(BabelFish.print().priceTypeLabel(), new String[] { BabelFish.print().consumerPrice(), BabelFish.print().inputPrice(), BabelFish.print().internationalPrice(), BabelFish.print().producerPrice(), BabelFish.print().retailPrice(),
				BabelFish.print().wholesalePrice() });
		String[] years = new String[100];
		int year = 1900 + new Date().getYear();
		for (int i = 0; i < 50; i++)
			years[i] = String.valueOf(year--);
		listValuesMap.put(BabelFish.print().basePriceStartYear(), new String[]{"2002"});
		listValuesMap.put(BabelFish.print().basePriceEndYear(), new String[]{"2004"});
		listValuesMap.put(BabelFish.print().indexStartYear(), years);
		listValuesMap.put(BabelFish.print().indexEndYear(), years);
		listValuesMap.put(BabelFish.print().indexType(), new String[] { "-----", BabelFish.print().foodPriceIndex(), BabelFish.print().meatPriceIndex(), BabelFish.print().dairyPriceIndex(), BabelFish.print().cerealsPriceIndex(), BabelFish.print().ricePriceIndex(), BabelFish.print().oilsPriceIndex(), BabelFish.print().sugarPriceIndex(), BabelFish.print().mealsPriceIndex(), BabelFish.print().feedPriceIndex() });
		listValuesMap.put(BabelFish.print().subIndexType(), new String[] { "-----" });
		listValuesMap.put(BabelFish.print().timePeriod(), new String[] {BabelFish.print().monthly(), BabelFish.print().annual()});
	}

}