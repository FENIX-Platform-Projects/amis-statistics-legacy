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
package org.fao.fenix.web.modules.ofcchart.client.view.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.ofcchart.common.vo.OfcFilterVo;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class OfcOtherDimensionsPanel {
	
	private String width = "200px";
	
	private String height = "120px";
	
	private ListBox dimensions;
	
	
	private ListBox values;
	
	private HorizontalPanel axisPanel;
	
	private CheckBox aggregateSubLevel;
	
	private Button showUpperLevel;
	
	private Button showLowerLevel;
	
	private DatasetVO dataset;
	
	private HashMap<String, OfcFilterVo> filter;
	
	private List<OfcFilterVo> filters;
	
	/** <dimension, <code, label>> **/
//	private HashMap<String, HashMap<String, String>> filters;
	
	public OfcOtherDimensionsPanel() {
		axisPanel = new HorizontalPanel();
		axisPanel.setSize(670, 205);
		axisPanel.setBorders(false);
		dimensions = new ListBox();
		dimensions.setStyleName("");
		dataset = new DatasetVO();
		values = new ListBox();
		filters = new ArrayList<OfcFilterVo>();
		
	}

	public HorizontalPanel build(DatasetVO d) {
		dataset.setDatasetName(d.getDatasetName());
		dataset.setDsId(d.getDsId());
		axisPanel.add(buildDimensionsPanel());
		axisPanel.add(buildSpace());
		axisPanel.add(buildValuesPanel());
		axisPanel.setData("dimensions", dimensions);
		axisPanel.setData("values", values);
		return axisPanel;
	}
	
	private HorizontalPanel buildSpace(){
		HorizontalPanel panel = new HorizontalPanel();
		panel.setWidth(100);
		return panel;
	}
	
	private VerticalPanel buildSelectorsPanel() {
		VerticalPanel selectorsPanel = new VerticalPanel();
		selectorsPanel.add(buildDimensionsPanel());
		selectorsPanel.add(buildValuesPanel());
		return selectorsPanel;
	}
	
	
	
	private HorizontalPanel buildLevelNavigatorPanel() {
		HorizontalPanel levelNavigatorPanel = new HorizontalPanel();
		levelNavigatorPanel.setSpacing(10);
		showLowerLevel = new Button(BabelFish.print().showLowerLevel());
		levelNavigatorPanel.add(showLowerLevel);
		showUpperLevel = new Button(BabelFish.print().showUpperLevel());
		levelNavigatorPanel.add(showUpperLevel);
		return levelNavigatorPanel;
	}
	
	private HorizontalPanel buildAggregateLevelPanel() {
		HorizontalPanel aggregateNavigatorPanel = new HorizontalPanel();
		aggregateNavigatorPanel.setSpacing(10);
		aggregateSubLevel = new CheckBox();
		aggregateSubLevel.setBoxLabel(BabelFish.print().aggregateSubLevel());
		aggregateNavigatorPanel.add(aggregateSubLevel);
		return aggregateNavigatorPanel;
	}
	
	private HorizontalPanel buildTitle(String title) {
		HorizontalPanel dimensionsPanel = new HorizontalPanel();
		dimensionsPanel.setSpacing(5);
		HTML label = new HTML("<b>"+title+"</b>");
		dimensions.setWidth(width);
		dimensionsPanel.add(label);
		return dimensionsPanel;
	}
	
	private VerticalPanel buildDimensionsPanel() {
		VerticalPanel dimensionsPanel = new VerticalPanel();
		dimensionsPanel.setSpacing(10);
		HTML label = new HTML("<b>"+BabelFish.print().dimensions()+":</b>");
		label.setWidth("75px");
		dimensions.setWidth(width);
		dimensions.setHeight(height);
		
		
//		dimensions.setMultipleSelect(true);
		dimensions.setVisibleItemCount(100);
		dimensionsPanel.add(label);
		dimensionsPanel.add(dimensions);
		return dimensionsPanel;
	}
	
	private VerticalPanel buildValuesPanel() {
		VerticalPanel valuesPanel = new VerticalPanel();
		valuesPanel.setSpacing(10);
		HTML label = new HTML("<b>"+BabelFish.print().values()+":</b>");
		label.setWidth("75px");
		values.setWidth(width);
		values.setHeight(height);
		values.setMultipleSelect(true);
		values.setVisibleItemCount(8);
		valuesPanel.add(label);
		valuesPanel.add(values);
		return valuesPanel;
	}

	public ListBox getDimensions() {
		return dimensions;
	}

	public ListBox getValues() {
		return values;
	}

	public CheckBox getAggregateSubLevel() {
		return aggregateSubLevel;
	}

	public Button getShowUpperLevel() {
		return showUpperLevel;
	}

	public Button getShowLowerLevel() {
		return showLowerLevel;
	}

	public DatasetVO getDataset() {
		return dataset;
	}

	public void setDataset(DatasetVO dataset) {
		this.dataset = dataset;
	}

	public List<OfcFilterVo> getFilters() {
		return filters;
	}

	public void setFilters(List<OfcFilterVo> filters) {
		this.filters = filters;
	}
	
	
	
}