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
import org.fao.fenix.web.modules.rainfall.client.control.RainfallController;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;

public class SeasonPeriodPanel {

	private ContentPanel panel;

	private VerticalPanel overallPanel;

	private VerticalPanel decadePanel;
	
	private ListBox decades;
	
	private ListBox fromDecade;
	
	private ListBox toDecade;
	
	private ListBox fromYear;
	
	private ListBox toYear;
	
	private ListBox compareYear;
	
	// TODO move functions to format()
	public SeasonPeriodPanel() {
		overallPanel = new VerticalPanel();
		decadePanel = new VerticalPanel();
		panel = new ContentPanel();
		panel.setLayout(new FitLayout());
		panel.setHeight("450px");
		panel.setHeaderVisible(false);
		decades = new ListBox();
		fromDecade = new ListBox();
		fromDecade.setWidth("200px");
		toDecade = new ListBox();
		toDecade.setWidth("200px");
		fromYear = new ListBox();
		fromYear.setWidth("200px");
		toYear = new ListBox();
		toYear.setWidth("200px");
		compareYear = new ListBox();
		compareYear.setWidth("400px");
	}
	
	public ContentPanel build() {
		panel.add(buildDecadePanel());
		format();
		return panel;
	}
	
	private VerticalPanel buildDecadePanel() {
		decadePanel.setSpacing(10);
		decadePanel.add(buildFromPanel());
		decadePanel.add(buildToPanel());
		decadePanel.add(buildComparePanel());
		return decadePanel;
	}
	
	private HorizontalPanel buildFromPanel() {
		HorizontalPanel fromPanel = new HorizontalPanel();
		fromPanel.setSpacing(10);
		fromPanel.setBorders(true);
		fromPanel.setWidth("700px");
		HTML label = new HTML("<b>" + BabelFish.print().from() + ": <b>");
		label.setWidth("150px");
		fromPanel.add(label);
		fromPanel.add(fromDecade);
		fromPanel.add(fromYear);
		RainfallController.fillDecadeList(fromDecade);
		RainfallController.fillYearList(fromYear);
		return fromPanel;
	}
	
	private HorizontalPanel buildToPanel() {
		HorizontalPanel toPanel = new HorizontalPanel();
		toPanel.setSpacing(10);
		toPanel.setBorders(true);
		toPanel.setWidth("700px");
		HTML label = new HTML("<b>" + BabelFish.print().to() + ": <b>");
		label.setWidth("150px");
		toPanel.add(label);
		toPanel.add(toDecade);
		toPanel.add(toYear);
		RainfallController.fillDecadeList(toDecade);
		RainfallController.fillYearList(toYear);
		return toPanel;
	}
	
	private HorizontalPanel buildComparePanel() {
		HorizontalPanel comparePanel = new HorizontalPanel();
		comparePanel.setSpacing(10);
		comparePanel.setBorders(true);
		comparePanel.setWidth("700px");
		HTML label = new HTML("<b>" + BabelFish.print().compareWithYear() + ": <b>");
		label.setWidth("150px");
		comparePanel.add(label);
		comparePanel.add(compareYear);
		RainfallController.fillYearList(compareYear);
		return comparePanel;
	}
	
	private void format() {
		panel.setHeaderVisible(false);
		overallPanel.setSpacing(10);
		decadePanel.setSpacing(10);
	}

	public ListBox getDecades() {
		return decades;
	}

	public ListBox getToDecade() {
		return toDecade;
	}

	public ListBox getFromYear() {
		return fromYear;
	}

	public ListBox getToYear() {
		return toYear;
	}

	public ListBox getCompareYear() {
		return compareYear;
	}

	public ListBox getFromDecade() {
		return fromDecade;
	}

}