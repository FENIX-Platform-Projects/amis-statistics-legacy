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

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.ofcchart.common.vo.DatasetVO;
import org.fao.fenix.web.modules.olap.client.control.OlapController;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class OfcAxisPanel {
	
	private String width = "200px";
	
	private String height = "120px";
	
	private ListBox dataSource;
	
	private ListBox dimensions;
	
	private ListBox values;
	
	private VerticalPanel axisPanel;
	
	private CheckBox aggregateSubLevel;
	
	private Button showUpperLevel;
	
	private Button showLowerLevel;
	
	private DatasetVO dataset;
	
	public OfcAxisPanel() {
		axisPanel = new VerticalPanel();
		axisPanel.setSize(340, 215);
//		axisPanel.setHeight(215);
		axisPanel.setBorders(false);
		dataSource = new ListBox();
		dimensions = new ListBox();
		dimensions.setStyleName("");
		dataset = new DatasetVO();
		values = new ListBox();
	}

	public VerticalPanel build(DatasetVO d, boolean hasDataSource, boolean setTitle) {
//		axisPanel.setHeading(title);
		dataset.setDatasetName(d.getDatasetName());
		dataset.setDsId(d.getDsId());
		if (setTitle) 
			axisPanel.add(buildTitle(dataset.getDatasetName()));
		axisPanel.add(buildSelectorsPanel(hasDataSource));
		axisPanel.setData("dimensions", dimensions);
		axisPanel.setData("values", values);
		return axisPanel;
	}
	
	private VerticalPanel buildSelectorsPanel(boolean hasDataSource) {
		VerticalPanel selectorsPanel = new VerticalPanel();
		if (hasDataSource)
			selectorsPanel.add(buildDataSourcePanel());
		selectorsPanel.add(buildDimensionsPanel());
		selectorsPanel.add(buildValuesPanel());
		
//		selectorsPanel.add(buildLevelNavigatorPanel());
//		selectorsPanel.add(buildAggregateLevelPanel());
		
		return selectorsPanel;
	}
	
	private HorizontalPanel buildDataSourcePanel() {
		HorizontalPanel dataSourcePanel = new HorizontalPanel();
		dataSourcePanel.setSpacing(10);
		HTML label = new HTML("<b>"+BabelFish.print().dataSource()+":</b>");
		label.setWidth("75px");
		dataSource.setWidth(width);
		dataSourcePanel.add(label);
		dataSourcePanel.add(dataSource);
		OlapController.fillDataSourceList(dataSource);
		// dataSource.addChangeListener(OlapController.dataSourceChangeListener(dimensions));
		return dataSourcePanel;
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
	
	private HorizontalPanel buildDimensionsPanel() {
		HorizontalPanel dimensionsPanel = new HorizontalPanel();
		dimensionsPanel.setSpacing(10);
		HTML label = new HTML("<b>"+BabelFish.print().dimensions()+":</b>");
		label.setWidth("75px");
		dimensions.setWidth(width);
		dimensionsPanel.add(label);
		dimensionsPanel.add(dimensions);
		return dimensionsPanel;
	}
	
	private HorizontalPanel buildValuesPanel() {
		HorizontalPanel valuesPanel = new HorizontalPanel();
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
	
	
}