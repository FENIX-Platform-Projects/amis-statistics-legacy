package org.fao.fenix.web.modules.fpi.client.view.index;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;

public class CerealsIndexParametersWindow extends FenixWindow {

	public static Map<String, String> methodsMap = new HashMap<String, String>();
	
	public VerticalPanel mainPanel;
	
	static {
		methodsMap.put("getWheatPriceBaseYearAverage", BabelFish.print().wheatPriceMonthlyWeighted());
		methodsMap.put("getMaizePriceBaseYearAverage", BabelFish.print().maizePriceMonthlyWeighted());
		methodsMap.put("getBarleyPriceBaseYearAverage", BabelFish.print().barleyPriceMonthlyWeighted());
		methodsMap.put("getRicePriceBaseYearAverage", BabelFish.print().ricePriceMonthlyWeighted());
		methodsMap.put("getWheatFixedAggregate", BabelFish.print().wheatFixedAggregate());
		methodsMap.put("getMaizeFixedAggregate", BabelFish.print().maizeFixedAggregate());
		methodsMap.put("getBarleyFixedAggregate", BabelFish.print().barleyFixedAggregate());
		methodsMap.put("getRiceFixedAggregate", BabelFish.print().riceFixedAggregate());
		methodsMap.put("getGrainFixedAggregate", BabelFish.print().grainsFixedAggregate());
		methodsMap.put("getCoarseGrainFixedAggregate", BabelFish.print().coarseGrainsFixedAggregate());
		methodsMap.put("getCoarseGrainsMaizeFixedWeight", BabelFish.print().coarseGrainsMaizeFixedAggregate());
		methodsMap.put("getCoarseGrainsBarleyFixedWeight", BabelFish.print().coarseGrainsBarleyFixedWeight());
		methodsMap.put("getWheatFixedWeight", BabelFish.print().wheatFixedWeight());
		methodsMap.put("getMaizeFixedWeight", BabelFish.print().maizeFixedWeight());
		methodsMap.put("getBarleyFixedWeight", BabelFish.print().barleyFixedWeight());
		methodsMap.put("getRiceFixedWeight", BabelFish.print().riceFixedWeight());
		methodsMap.put("getWheatDenominator", BabelFish.print().wheatDenominator());
		methodsMap.put("getMaizeDenominator", BabelFish.print().maizeDenominator());
		methodsMap.put("getBarleyDenominator", BabelFish.print().barleyDenominator());
		methodsMap.put("getRiceDenominator", BabelFish.print().riceDenominator());
		methodsMap.put("getGrainsDenominator", BabelFish.print().grainsDenominator());
		methodsMap.put("getCoarseGrainsMaizeDenominator", BabelFish.print().coarseGrainsMaizeDenominator());
		methodsMap.put("getCoarseGrainsBarleyDenominator", BabelFish.print().coarseGrainsBarleyDenominator());
		methodsMap.put("getCoarseGrainsDenominator", BabelFish.print().coarseGrainsDenominator());
	}
	
	public void build(Map<String, Double> parameters, VerticalPanel parametersPanel, Table table) {
		mainPanel = buildMainPanel(parameters);
		fillCenterPart(mainPanel);
		addToolbar(parameters, parametersPanel, mainPanel, table);
		format();
		getWindow().show();
	}
	
	public VerticalPanel buildMainPanel(Map<String, Double> parameters) {
		VerticalPanel panel = new VerticalPanel();
		Iterator<String> iterator = parameters.keySet().iterator();
		while (iterator.hasNext()) {
			String parameter = iterator.next();
			String value = String.valueOf(parameters.get(parameter));
			HorizontalPanel parameterPanel = buildParameterPanel(parameter, value);
			panel.add(parameterPanel);
			panel.setData(parameter, parameterPanel.getData(parameter));
		}
		return panel;
	}
	
	public HorizontalPanel buildParameterPanel(String parameter, String value) {
		HorizontalPanel result = new HorizontalPanel();
		HTML label = new HTML(methodsMap.get(parameter));
		TextBox textbox = new TextBox();
		textbox.setText(value);
		label.setWidth("200px");
		textbox.setWidth("150px");
		result.add(label);
		result.add(textbox);
		result.setData(parameter, textbox);
		return result;
	}
	
	public void addToolbar(Map<String, Double> parameters, VerticalPanel parametersPanel, VerticalPanel mainPanel, Table table) {
		ToolBar toolbar = new ToolBar();
		IconButton iconButton = new IconButton("textIcon");
		iconButton.setTitle(BabelFish.print().changeIndexParameters());
		toolbar.add(iconButton);
		// iconButton.addSelectionListener(FPIIndexController.buildUpdateCerealsIndexSelectionListener(parameters, parametersPanel, mainPanel, table));
		addToolbar(toolbar);
	}
	
	public void format() {
		getWindow().setSize("400px", "400px");
		getWindow().setHeading(BabelFish.print().fpiTool());
		getCenter().setHeading(BabelFish.print().parameters());
		getCenter().setScrollMode(Style.Scroll.AUTO);
		getWindow().setModal(true);
	}
	
}
