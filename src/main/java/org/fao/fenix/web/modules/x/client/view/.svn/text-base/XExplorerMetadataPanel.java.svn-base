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
package org.fao.fenix.web.modules.x.client.view;

import org.fao.fenix.web.modules.x.common.vo.XExplorerModelData;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.user.client.ui.HTML;

public class XExplorerMetadataPanel {

	private ContentPanel metadataPanel;
	
	private VerticalPanel wrapper;
	
	private final String PANEL_HEIGHT = "450px";
	
	private final String LABEL_WIDTH = "75px";
	
	private final String VALUE_WIDTH = "150px";
	
	public XExplorerMetadataPanel() {
		metadataPanel = new ContentPanel();
		metadataPanel.setLayout(new FillLayout());
		wrapper = new VerticalPanel();
	}
	
	public ContentPanel build() {
		metadataPanel.setHeading("Metadata");
		metadataPanel.add(wrapper);
		return metadataPanel;
	}
	
	public void updateMetadata(XExplorerModelData m) {
		buildMetadataPanel(m);
//		metadataPanel.remove(metadataPanel.getWidget(0));
//		metadataPanel.add(wrapper);
	}
	
	private VerticalPanel buildMetadataPanel(XExplorerModelData m) {
		
		wrapper.setSpacing(5);
		wrapper.setScrollMode(Scroll.AUTO);
		wrapper.setHeight(PANEL_HEIGHT);
//		wrapper.setLayout(new FitLayout());
		
		if (m.getResourceType().equalsIgnoreCase("dataset")) {
			wrapper.add(buildFieldPanel("Type", m.getDatasetType()));
			wrapper.add(buildFieldPanel("Frequency", m.getPeriodTypeCode()));
		} else {
			wrapper.add(buildFieldPanel("Type", m.getResourceType()));
		}
		
		wrapper.add(buildFieldPanel("Title", m.getName()));
		wrapper.add(buildFieldPanel("Abstract", m.getAbstractAbstract()));
		wrapper.add(buildFieldPanel("Region", m.getRegion()));
		wrapper.add(buildFieldPanel("Source", m.getSource()));
		wrapper.add(buildFieldPanel("Provider", m.getProvider()));
		wrapper.add(buildFieldPanel("Category", m.getCategory()));
		wrapper.add(buildFieldPanel("Keywords", m.getKeywords()));
		wrapper.add(buildFieldPanel("Last Update", String.valueOf(m.getDateLastUpdate())));
		wrapper.add(buildFieldPanel("Start Date", String.valueOf(m.getStartDate())));
		wrapper.add(buildFieldPanel("End Date", String.valueOf(m.getEndDate())));
		wrapper.add(buildFieldPanel("Code", m.getCode()));
		wrapper.add(buildFieldPanel("Node", m.getNode()));
//		wrapper.add(buildFieldPanel("URL", m.getUrl()));
		
		return wrapper;
	}
	
	private VerticalPanel buildFieldPanel(String field, String content) {
		VerticalPanel fieldPanel = new VerticalPanel();
//		fieldPanel.setSpacing(5);
		HTML label = new HTML("<b>" + field + ": </b>");
		label.setWidth(LABEL_WIDTH);
		HTML value = new HTML("");
		value.setWidth(VALUE_WIDTH);
		if ((content != null) && !content.equals(""))
			value.setHTML(content);
		else
			value.setHTML("n.a.");
		fieldPanel.add(label);
		fieldPanel.add(value);
		return fieldPanel;
	}
	
	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public ContentPanel getMetadataPanel() {
		return metadataPanel;
	}
	
}
