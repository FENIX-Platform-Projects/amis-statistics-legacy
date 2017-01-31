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
package org.fao.fenix.web.modules.rainfall.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class OptionPanel {

	private ContentPanel optionPanel;
	
	private CheckBox cumulate;
	
	private CheckBox showAverage;
	
	private CheckBox chart;
	
	private CheckBox table;
	
	private CheckBox map;
	
	private CheckBox ndvi;
	
	private CheckBox generalClimate;
	
	private VerticalPanel wrapper;
	
	private ListBox templateList;
	
	public OptionPanel() {
		optionPanel = new ContentPanel();
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		cumulate = new CheckBox();
		showAverage = new CheckBox();
		chart = new CheckBox();
		map = new CheckBox();
		ndvi = new CheckBox();
		table = new CheckBox();
		generalClimate = new CheckBox();
		optionPanel.setHeight("325px");
		optionPanel.setHeaderVisible(false);
		templateList = new ListBox();
	}
	
	public ContentPanel build() {
		optionPanel.add(buildOptionPanel());
		format();
		return optionPanel;
	}
	
	private VerticalPanel buildOptionPanel() {
		wrapper.add(buildOperationPanel());
		wrapper.add(buildOutputPanel());
		wrapper.add(buildTemplatePanel());
		return wrapper;
	}
	
	private HorizontalPanel buildOperationPanel() {
		HorizontalPanel operationPanel = new HorizontalPanel();
		operationPanel.setSpacing(10);
		operationPanel.setBorders(true);
		HTML label = new HTML("<b>" + BabelFish.print().options() + ": </b>");
		operationPanel.add(label);
		operationPanel.add(cumulate);
		operationPanel.add(showAverage);
		operationPanel.setWidth("700px");
		return operationPanel;
	}
	
	private HorizontalPanel buildOutputPanel() {
		HorizontalPanel outputPanel = new HorizontalPanel();
		outputPanel.setSpacing(10);
		outputPanel.setBorders(true);
		HTML label = new HTML("<b>" + BabelFish.print().output() + ": </b>");
		outputPanel.add(label);
		outputPanel.add(chart);
		outputPanel.add(table);
		outputPanel.add(map);
		outputPanel.add(ndvi);
		outputPanel.add(generalClimate);
		outputPanel.setWidth("700px");
		return outputPanel;
	}
	
	private HorizontalPanel buildTemplatePanel() {
		HorizontalPanel templatePanel = new HorizontalPanel();
		templatePanel.setSpacing(10);
		HTML label = new HTML("<b>" + BabelFish.print().template() + ": </b>");
		templatePanel.add(label);
		templatePanel.add(templateList);
		templatePanel.setBorders(true);
		templatePanel.setWidth("700px");
		templateList.addItem(BabelFish.print().standard(), "standard");
//		templateList.addItem(I18N.print().giewsCB(), "giews");
		templateList.setWidth("500px");
		return templatePanel;
	}
	
	private void format() {
		cumulate.setBoxLabel(BabelFish.print().cumulateValues());
		showAverage.setBoxLabel(BabelFish.print().showAverage());
		showAverage.setValue(true);
		table.setBoxLabel(BabelFish.print().showTable());
		table.setValue(true);
		chart.setBoxLabel(BabelFish.print().showChart());
		chart.setValue(true);
		map.setBoxLabel(BabelFish.print().showMap());
		ndvi.setBoxLabel(BabelFish.print().ndvi());
		generalClimate.setBoxLabel(BabelFish.print().showGeneralClimate());
		generalClimate.setValue(true);
	}

	public ContentPanel getOptionPanel() {
		return optionPanel;
	}

	public CheckBox getCumulate() {
		return cumulate;
	}

	public CheckBox getShowAverage() {
		return showAverage;
	}

	public CheckBox getChart() {
		return chart;
	}

	public CheckBox getTable() {
		return table;
	}

	public CheckBox getMap() {
		return map;
	}

	public CheckBox getGeneralClimate() {
		return generalClimate;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public ListBox getTemplateList() {
		return templateList;
	}

	public CheckBox getNdvi() {
		return ndvi;
	}
	
}