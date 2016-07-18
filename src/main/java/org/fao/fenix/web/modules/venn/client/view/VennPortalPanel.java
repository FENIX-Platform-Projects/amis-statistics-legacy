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
package org.fao.fenix.web.modules.venn.client.view;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISDatasetPanel;
import org.fao.fenix.web.modules.venn.client.control.VennController;
import org.fao.fenix.web.modules.venn.client.view.vennclient.VennTabPanel;
import org.fao.fenix.web.modules.venn.common.vo.VennBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.VennChartBeanVO;
import org.fao.fenix.web.modules.venn.common.vo.report.VennReportBeanVO;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

public class VennPortalPanel {

	private ControllerPortalPanel controllerPortalPanel;
	
	private CenterToolPanel centerToolPanel;
	
	private Button apply = new Button("Apply");
	
	private VennBeanVO vennBean;
	
	private VennReportBeanVO vennReportBean;
	
	private List<String> countryCodes;
	
	private LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> vennChartsBeanVO = new LinkedHashMap<String, LinkedHashMap<String,VennChartBeanVO>>();
	
	public VennPortalPanel() {
		controllerPortalPanel = new ControllerPortalPanel();
		centerToolPanel = new CenterToolPanel();
		vennReportBean = new VennReportBeanVO();
	}
	
//	public ContentPanel build(int widht, int height, Boolean useXml, String xml, String language) {
//		
//		panel.setHeaderVisible(false);
//		panel.setWidth(widht);
//		
//		HorizontalPanel contPanel = new HorizontalPanel();
//		contPanel.setSpacing(5);
//		ContentPanel c1 = new ContentPanel();
//		c1.setHeaderVisible(false);
//		c1.setWidth(320); // dynamic
//		c1.setHeight(650); // dynamic
//		c1.setScrollMode(Scroll.AUTO);
//		c1.add(controllerPortalPanel.build(useXml, xml, language));
//		c1.setBottomComponent(apply);
//		contPanel.add(c1);
//		
//		HorizontalPanel c2 = new HorizontalPanel();
//		c2.setWidth(950); // dynamic
//		c2.setHeight(700); // dynamic
////   	c2.setScrollMode(Scroll.AUTO);
//		c2.add(centerToolPanel.build());
//		contPanel.add(c2);
//	
//
//		panel.add(contPanel);
//		enancheButton();
//		return panel;
//	}
	
	
	public ContentPanel buildWest(int widht, int height, Boolean useXml, String xml, String language, List<String> countryCodes) {
		this.countryCodes = countryCodes;
		ContentPanel westPanel = new ContentPanel();
		westPanel.setHeaderVisible(false);
		westPanel.setBorders(false);
		
		westPanel.setHeaderVisible(false);
		
		ContentPanel c1 = new ContentPanel();
		c1.setHeaderVisible(false);
		c1.setWidth(320); // dynamic
		c1.setHeight(650); // dynamic
		c1.setScrollMode(Scroll.AUTO);
		c1.add(controllerPortalPanel.build(this, centerToolPanel, useXml, xml, language, countryCodes));
		c1.setBottomComponent(apply);
		
		westPanel.add(c1);
		enancheButton(language, countryCodes);
		return westPanel;
	}
	
	public ContentPanel buildCenter(int widht, int height) {
		
		ContentPanel westPanel = new ContentPanel();
		westPanel.setHeaderVisible(false);
		westPanel.setBorders(false);

		HorizontalPanel c2 = new HorizontalPanel();
		c2.setSpacing(4);
		c2.setWidth(900); // dynamic
		c2.setHeight(700); // dynamic
//   	c2.setScrollMode(Scroll.AUTO);
//		c2.add(centerToolPanel.build());
		c2.add(centerToolPanel.build4Portlet());
		
		westPanel.add(c2);
//		enancheButton();
		return westPanel;
	}
	
	private void enancheButton(String language, List<String> countryCodes) {
		apply.addSelectionListener(VennController.applyButton(this, controllerPortalPanel.getxPortlet().getTreePanel(), controllerPortalPanel.getyPortlet().getTreePanel(), controllerPortalPanel.getwPortlet().getTreePanel(), centerToolPanel, language));
	}

	public VennBeanVO getVennBean() {
		return vennBean;
	}

	public void setVennBean(VennBeanVO vennBean) {
		this.vennBean = vennBean;
	}

	public ControllerPortalPanel getControllerPortalPanel() {
		return controllerPortalPanel;
	}

	public CenterToolPanel getCenterToolPanel() {
		return centerToolPanel;
	}

	public VennReportBeanVO getVennReportBean() {
		return vennReportBean;
	}

	public void setVennReportBean(VennReportBeanVO vennReportBean) {
		this.vennReportBean = vennReportBean;
	}

	public LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> getVennChartsBeanVO() {
		return vennChartsBeanVO;
	}

	public void setVennChartsBeanVO(
			LinkedHashMap<String, LinkedHashMap<String, VennChartBeanVO>> vennChartsBeanVO) {
		this.vennChartsBeanVO = vennChartsBeanVO;
	}

	public List<String> getCountryCodes() {
		return countryCodes;
	}

	public void setCountryCodes(List<String> countryCodes) {
		this.countryCodes = countryCodes;
	}

	
	
	
}