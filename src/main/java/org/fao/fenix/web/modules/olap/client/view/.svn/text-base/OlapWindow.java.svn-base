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
package org.fao.fenix.web.modules.olap.client.view;

import org.fao.fenix.web.modules.birt.client.view.report.viewer.ReportViewer;
import org.fao.fenix.web.modules.core.client.utils.SocialBar;
import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.olap.client.control.OlapController;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class OlapWindow extends FenixWindow {

	private OlapTabPanel tabPanel;
	
	private OlapFilterPanel filterPanel;
	
	private HTML html;
	
	private OlapMapWindow olapMapWindow;
	
	private OlapToolbar toolbar;
	
	private boolean isForReport;
	
	private ReportViewer reportViewer;
	
	private Long resourceViewID;
	
	private VerticalPanel wrapper;
	
	private VerticalPanel olapWrapper;
	
	private VerticalPanel heightsWrapper;
	
	private HorizontalPanel widthsWrapper;
	
	private HorizontalPanel secondLineWrapper;
	
	private OlapRuler olapRuler;

	public OlapWindow() {
		
		tabPanel = new OlapTabPanel();
		filterPanel = new OlapFilterPanel();
		olapRuler = new OlapRuler();
		
		html = new HTML(BabelFish.print().olapManual());
		
		olapWrapper = new VerticalPanel();
		olapWrapper.add(html);
		olapWrapper.setScrollMode(Scroll.AUTO);
//		olapWrapper.setBorders(true);
//		olapWrapper.setSize("550px", "475px");
		
		heightsWrapper = new VerticalPanel();
		heightsWrapper.setLayout(new FillLayout());
//		heightsWrapper.setBorders(true);
		widthsWrapper = new HorizontalPanel();
//		widthsWrapper.setSpacing(10);
//		widthsWrapper.setBorders(true);
		
		secondLineWrapper = new HorizontalPanel();
		secondLineWrapper.add(olapWrapper);
		secondLineWrapper.add(heightsWrapper);
//		secondLineWrapper.setSpacing(5);
//		secondLineWrapper.setBorders(true);
		
		wrapper = new VerticalPanel();
		wrapper.setSpacing(5);
		wrapper.setScrollMode(Scroll.AUTO);
//		wrapper.setWidth("300px");
		wrapper.add(widthsWrapper);
		wrapper.add(secondLineWrapper);
		
		olapMapWindow = new OlapMapWindow();
	}
	
	public void build(boolean show, boolean enableSave) {
		buildWestPanel();
		buildCenterPanel(enableSave);
		buildEastPanel();
		format();
		if (show)
			show();
		tabPanel.getSingleDatasetPanel().getAddDataset().addSelectionListener(OlapController.addDataset(this));
		filterPanel.getAddFilterButton().addSelectionListener(OlapController.addFilter(filterPanel, tabPanel.getSingleDatasetPanel().getDataSource()));
		filterPanel.getRemoveFilterButton().addSelectionListener(OlapController.removeFilter(filterPanel));
//		this.tabPanel.getSingleDatasetPanel().getShowWidthAndHeight().addListener(Events.OnClick, OlapController.showWidthsAndHeights(this));
	}
	
	public void build(boolean show, Long datasetID, boolean enableSave) {
		buildWestPanel();
		buildCenterPanel(enableSave);
		buildEastPanel();
		format();
		if (show)
			show();
		tabPanel.getSingleDatasetPanel().getAddDataset().addSelectionListener(OlapController.addDataset(this));
		filterPanel.getAddFilterButton().addSelectionListener(OlapController.addFilter(filterPanel, tabPanel.getSingleDatasetPanel().getDataSource()));
		filterPanel.getRemoveFilterButton().addSelectionListener(OlapController.removeFilter(filterPanel));
		this.tabPanel.getSingleDatasetPanel().getDataSource().addItem("Dataset", String.valueOf(datasetID));
//		this.tabPanel.getSingleDatasetPanel().getShowWidthAndHeight().addListener(Events.OnClick, OlapController.showWidthsAndHeights(this));
	}
	
	private void buildCenterPanel(boolean enableSave) {
		setCenterProperties();
		getCenter().setLayout(new FitLayout());
		getCenter().setHeading("Create and Export the " + BabelFish.print().multidimensionalTable());
		getCenter().setIconStyle("olap");
		toolbar = new OlapToolbar();
		getCenter().setTopComponent(toolbar.build(this, enableSave));
		getCenter().add(wrapper);
		getCenterData().setSize(600);
		addCenterPartToWindow();
		SocialBar sb = new SocialBar();
		getCenter().setBottomComponent(sb.getSocialBar(ResourceType.OLAP, String.valueOf(resourceViewID)));
	}
	
	private void buildWestPanel() {
		setWestProperties();
		fillWestPart(tabPanel.build());
		getWestData().setSize(250);
		getWest().setHeading(BabelFish.print().controller());
		getWest().setIconStyle("gear");
	}
	
	private void buildEastPanel() {
		setEastProperties();
		fillEastPart(filterPanel.build());
		getEastData().setSize(150);
		getEast().setHeading(BabelFish.print().filters());
		getEast().setIconStyle("filter");
		getEast().hide();
		getEast().setCollapsible(false);
	}
	
	private void format() {
		setSize("825px", "575px");
		getWindow().setHeading(BabelFish.print().multidimensionalTableTool());
		setCollapsible(true);
		getWindow().setResizable(true);
		getWindow().setIconStyle("olap");
		getWindow().setBodyBorder(false);
		getWindow().setModal(false);
	}
	
	public OlapTabPanel getTabPanel() {
		return tabPanel;
	}

	public HTML getHtml() {
		return html;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public OlapFilterPanel getFilterPanel() {
		return filterPanel;
	}

	public OlapMapWindow getOlapMapWindow() {
		return olapMapWindow;
	}

	public OlapToolbar getOlapToolbar() {
		return toolbar;
	}

	public boolean isForReport() {
		return isForReport;
	}

	public void setForReport(boolean isForReport) {
		this.isForReport = isForReport;
	}

	public ReportViewer getReportViewer() {
		return reportViewer;
	}

	public void setReportViewer(ReportViewer reportViewer) {
		this.reportViewer = reportViewer;
	}

	public Long getResourceViewID() {
		return resourceViewID;
	}

	public void setResourceViewID(Long resourceViewID) {
		this.resourceViewID = resourceViewID;
	}

	public VerticalPanel getOlapWrapper() {
		return olapWrapper;
	}

	public VerticalPanel getHeightsWrapper() {
		return heightsWrapper;
	}

	public HorizontalPanel getWidthsWrapper() {
		return widthsWrapper;
	}

	public HorizontalPanel getSecondLineWrapper() {
		return secondLineWrapper;
	}

	public OlapRuler getOlapRuler() {
		return olapRuler;
	}
	
}