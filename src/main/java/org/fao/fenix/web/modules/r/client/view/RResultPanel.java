package org.fao.fenix.web.modules.r.client.view;

import java.util.Map;
import java.util.MissingResourceException;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.r.client.control.RController;
import org.fao.fenix.web.modules.r.common.vo.RResultVO;
import org.fao.fenix.web.modules.r.common.vo.RUserSettingsVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class RResultPanel {

	private FormPanel panel;
	
	private final static String LABEL_WIDTH = "100px";
	
	private final static String FIELD_WIDTH = "150px";
	
	private final static String FIELDSET_WIDTH = "425px";
	
	public RResultPanel() {
		panel = new FormPanel();
		panel.setFrame(false);
		panel.setHeaderVisible(false);
		panel.setScrollMode(Scroll.AUTO);
	}
	
	public FormPanel build(RResultVO rvo, RUserSettingsVO usvo) {
		for (String resultName : rvo.getResults().keySet())
			panel.add(buildResultFieldSet(resultName, usvo.getMainDimensionValues(), rvo.getResults().get(resultName), rvo.getResultType().get(resultName)));
		panel.add(buildMainVariableFieldSet(usvo));
		if (!usvo.getOtherDimensions().isEmpty())
			panel.add(buildOtherVariablesFieldSet(usvo));
		if (!usvo.getOptions().isEmpty())
			panel.add(buildOptionsFieldSet(usvo));
		if (!rvo.getPlots().isEmpty())
			panel.add(buildPlotFieldSet(rvo));
		return panel;
	}
	
	private FieldSet buildResultFieldSet(String resultName, Map<String, String> mainDimension, Object[][] resultMatrix, String resultType) {
		FieldSet matrixFieldSet = new FieldSet();
		try {
			matrixFieldSet.setHeading(BabelFish.print().getString(resultName));
		} catch (MissingResourceException e) {
			matrixFieldSet.setHeading(resultName);
		}
		matrixFieldSet.setCheckboxToggle(true);
		String html = "";
		if (resultType.equals("MATRIX") || resultType.equals("TABLE"))
			html = RController.matrix2htmlTable(resultMatrix, mainDimension);
		else if (resultType.equals("LIST"))
			html = RController.matrix2htmlList(resultMatrix, mainDimension);
		else if (resultType.equals("SINGLE_RESULT"))
			html = RController.matrix2htmlLabel(resultMatrix);
		matrixFieldSet.add(new Html(html));
		matrixFieldSet.setWidth(FIELDSET_WIDTH);
		return matrixFieldSet;
	}
	
	private FieldSet buildMainVariableFieldSet(RUserSettingsVO usvo) {
		FieldSet mainVariableFieldSet = new FieldSet();
		mainVariableFieldSet.setHeading(BabelFish.print().variableOfInterest());
		mainVariableFieldSet.setCheckboxToggle(true);
		mainVariableFieldSet.collapse();
		for (String variableOfInterest : usvo.getMainDimension().keySet())
			mainVariableFieldSet.add(new Html("<b>" + BabelFish.print().variableOfInterest() + ": </b>" + variableOfInterest));
		String valuesOfInterestString = "";
		int counter = 0;
		for (String value : usvo.getMainDimensionValues().values()) {
			valuesOfInterestString += value;
			if (counter < usvo.getMainDimensionValues().values().size() - 1)
				valuesOfInterestString += ", ";
			counter++;
		}
		Html valuesOfInterest = new Html("<b>" + BabelFish.print().valuesOfInterest() + ": </b>" + valuesOfInterestString);
		mainVariableFieldSet.add(valuesOfInterest);
		mainVariableFieldSet.setWidth(FIELDSET_WIDTH);
		return mainVariableFieldSet;
	}
	
	private FieldSet buildOptionsFieldSet(RUserSettingsVO usvo) {
		FieldSet optionsFieldSet = new FieldSet();
		optionsFieldSet.setHeading(BabelFish.print().availableOptions());
		optionsFieldSet.setCheckboxToggle(true);
		optionsFieldSet.collapse();
		for (String optionName : usvo.getOptions().keySet())
			optionsFieldSet.add(new Html("<b>" + optionName + ": </b> " + usvo.getOptions().get(optionName)));
		optionsFieldSet.setWidth(FIELDSET_WIDTH);
		return optionsFieldSet;
	}
	
	private FieldSet buildPlotFieldSet(final RResultVO rvo) {
		FieldSet plotFieldSet = new FieldSet();
		plotFieldSet.setHeading(BabelFish.print().availablePlots());
		plotFieldSet.setCheckboxToggle(true);
		plotFieldSet.collapse();
		for (final String plotName : rvo.getPlots().keySet()) {
			System.out.println(plotName + " @ " + rvo.getPlots().get(plotName));
			Hyperlink hl = null;
			try {
				hl = new Hyperlink(BabelFish.print().getString(plotName), BabelFish.print().getString(plotName));
			} catch (MissingResourceException e) {
				hl = new Hyperlink(plotName, plotName);
			}
			hl.addClickListener(new ClickListener() {
				public void onClick(Widget arg0) {
					RPlotWindow w = new RPlotWindow();
					try {
						w.build(BabelFish.print().getString(plotName), rvo.getPlots().get(plotName));
					}  catch (MissingResourceException e) {
						w.build(plotName, rvo.getPlots().get(plotName));
					}
				}
			});
			plotFieldSet.add(hl);
		}
		plotFieldSet.setWidth(FIELDSET_WIDTH);
		return plotFieldSet;
	}
	
	private FieldSet buildOtherVariablesFieldSet(RUserSettingsVO usvo) {
		FieldSet otherVariablesFieldSet = new FieldSet();
		otherVariablesFieldSet.setHeading(BabelFish.print().groupBy());
		otherVariablesFieldSet.setCheckboxToggle(true);
		otherVariablesFieldSet.collapse();
		for (String otherVariable : usvo.getOtherDimensions().keySet()) {
			otherVariablesFieldSet.add(new Html("<b>" + BabelFish.print().variableOfInterest() + ": </b>" + otherVariable));
			Map<String, String> otherVariableValues = usvo.getOtherDimensionsValues().get(otherVariable);
			String valuesOfInterestString = "";
			int counter = 0;
			for (String value : otherVariableValues.values()) {
				valuesOfInterestString += value;
				if (counter < otherVariableValues.values().size() - 1)
					valuesOfInterestString += ", ";
				counter++;
			}
			Html valuesOfInterest = new Html("<b>" + BabelFish.print().valuesOfInterest() + ": </b>" + valuesOfInterestString);
			otherVariablesFieldSet.add(valuesOfInterest);
		}
		otherVariablesFieldSet.setWidth(FIELDSET_WIDTH);
		return otherVariablesFieldSet;
	}
	
}
