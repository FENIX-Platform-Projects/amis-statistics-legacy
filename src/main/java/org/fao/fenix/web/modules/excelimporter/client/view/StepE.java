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
package org.fao.fenix.web.modules.excelimporter.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class StepE extends ExcelImporterStepPanel {

	private VerticalPanel wrapper;
	
	private final static String TITLE_PANEL_WIDTH = "560px";
	
	private final static String TITLE_WIDTH = "550px";
	
	private PushButton table;
	
	private PushButton chart;
	
	private PushButton map;
	
	private PushButton olap;
	
	private PushButton qb;
	
	private PushButton csv;
	
	private PushButton xml;
	
	private PushButton zip;
	
	public StepE(String suggestion, String width) {
		super(suggestion, width);
		this.getLayoutContainer().add(buildWrapper());
	}
	
	@Override
	public HorizontalPanel buildSuggestionPanel(String suggestion, String width) {
		HorizontalPanel suggestionPanel = new HorizontalPanel();
		suggestionPanel.setSpacing(10);
		suggestionPanel.setBorders(true);
		HTML suggestionHTML = new HTML("<font color='green'>" + BabelFish.print().getString(suggestion) + "</font>");
		suggestionPanel.add(suggestionHTML);
		return suggestionPanel;
	}
	
	private VerticalPanel buildWrapper() {
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		wrapper.add(buildTitlePanel("Open Your Dataset As..."));
		wrapper.add(buildOpenAsPanel());
		wrapper.add(buildTitlePanel("Download Your Dataset As..."));
		wrapper.add(buildExportAsPanel());
		wrapper.setHorizontalAlign(HorizontalAlignment.CENTER);
		return wrapper;
	}
	
	private HorizontalPanel buildTitlePanel(String text) {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		p.setHorizontalAlign(HorizontalAlignment.CENTER);
		p.setWidth(TITLE_PANEL_WIDTH);
		Html label = new Html("<H1>" + text + "</H1>");
		label.setWidth(TITLE_WIDTH);
		p.add(label);
		return p;
	}
	
	private HorizontalPanel buildOpenAsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		p.setWidth(TITLE_PANEL_WIDTH);
		table = new PushButton(new Image("toolBox-images/tableToolbox.gif"));
		table.setTitle("Table");
		chart = new PushButton(new Image("toolBox-images/chartToolbox.gif"));
		chart.setTitle("Chart");
		chart.setEnabled(false);
		map = new PushButton(new Image("toolBox-images/mapToolbox.gif"));
		map.setTitle("Map");
		olap = new PushButton(new Image("toolBox-images/olap.png"));
		olap.setTitle("Multidimensional Table");
		qb = new PushButton(new Image("images/SQL_icon.png"));
		qb.setTitle("Query Builder");
		p.add(table);
		p.add(chart);
//		p.add(map);
		p.add(olap);
//		p.add(qb);
		return p;
	}
	
	private HorizontalPanel buildExportAsPanel() {
		HorizontalPanel p = new HorizontalPanel();
		p.setSpacing(10);
		p.setWidth(TITLE_PANEL_WIDTH);
		csv = new PushButton(new Image("menu-images/CSV.png"));
		csv.setTitle("Dataset (CSV)");
		xml = new PushButton(new Image("menu-images/XML.png"));
		xml.setTitle("Metadata (XML)");
		zip = new PushButton(new Image("menu-images/ZIP.png"));
		zip.setTitle("Dataset + Metadata (ZIP)");
		p.add(csv);
		p.add(xml);
		p.add(zip);
		return p;
	}

	public PushButton getTable() {
		return table;
	}

	public PushButton getChart() {
		return chart;
	}

	public PushButton getMap() {
		return map;
	}

	public PushButton getOlap() {
		return olap;
	}

	public PushButton getQb() {
		return qb;
	}

	public PushButton getCsv() {
		return csv;
	}

	public PushButton getXml() {
		return xml;
	}

	public PushButton getZip() {
		return zip;
	}
	
}