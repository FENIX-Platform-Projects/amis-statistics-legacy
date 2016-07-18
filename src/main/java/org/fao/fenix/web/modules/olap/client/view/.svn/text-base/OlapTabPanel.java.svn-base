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

import org.fao.fenix.web.modules.fsatmis.client.view.FSATMISDatasetPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;

public class OlapTabPanel {

	private TabPanel tabPanel;
	
	private TabItem singleDatasetTabItem;
	
	private TabItem fsatmisDatasetTabItem;
	
	private SingleDatasetPanel singleDatasetPanel;
	
	private FSATMISDatasetPanel fsatmisDatasetPanel;
	
	public OlapTabPanel() {
		tabPanel = new TabPanel();
		tabPanel.setBodyBorder(false);
		singleDatasetTabItem = new TabItem(BabelFish.print().singleDataset());
		singleDatasetTabItem.setIconStyle("datasetIcon");
		singleDatasetPanel = new SingleDatasetPanel();
		fsatmisDatasetTabItem = new TabItem(BabelFish.print().singleDataset());
		fsatmisDatasetPanel = new FSATMISDatasetPanel();
	}
	
	public TabPanel build() {
		singleDatasetTabItem.add(singleDatasetPanel.build());
		tabPanel.add(singleDatasetTabItem);
		return tabPanel;
	}
	
	public TabPanel buildForFSATMIS() {
		fsatmisDatasetTabItem.add(fsatmisDatasetPanel.build());
		tabPanel.add(fsatmisDatasetTabItem);
		return tabPanel;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public SingleDatasetPanel getSingleDatasetPanel() {
		return singleDatasetPanel;
	}

	public TabItem getFsatmisDatasetTabItem() {
		return fsatmisDatasetTabItem;
	}

	public FSATMISDatasetPanel getFsatmisDatasetPanel() {
		return fsatmisDatasetPanel;
	}
	
}