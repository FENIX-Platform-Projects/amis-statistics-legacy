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
package org.fao.fenix.web.modules.sldeditor.client.view;

import com.extjs.gxt.ui.client.widget.TabPanel;

public class SLDEditorPanel {

	private TabPanel tabPanel;
	
	private SLDEditorName sldEditorName;
	
	private SLDEditorLine sldEditorLine;
	
	private SLDEditorBackground sldEditorBackground;
	
	private SLDEditorLabel sldEditorLabel;
	
	//Raster sld editor
	private SLDEditorRaster sldEditorRaster;
	
	private SLDEditorWindow myWindow;
	
	
	public SLDEditorPanel(SLDEditorWindow myWindow) {
		this.myWindow = myWindow;
		tabPanel = new TabPanel();
		sldEditorName = new SLDEditorName("Info", this.myWindow);
		sldEditorLine = new SLDEditorLine("Lines", this.myWindow);
		sldEditorBackground = new SLDEditorBackground("Polygons", this.myWindow);
		sldEditorLabel = new SLDEditorLabel("Labels", this.myWindow);
	}
	
	public SLDEditorPanel(String raster, SLDEditorWindow myWindow) {
		this.myWindow = myWindow;
		tabPanel = new TabPanel();
		sldEditorName = new SLDEditorName("Info", this.myWindow);
		sldEditorRaster = new SLDEditorRaster("Raster", this.myWindow);
	}
	
	public TabPanel build() {
		tabPanel.add(sldEditorName.build());
		tabPanel.add(sldEditorLine.build());
		tabPanel.add(sldEditorBackground.build());
		tabPanel.add(sldEditorLabel.build());
		tabPanel.setSelection(sldEditorName.getTabItem());
		return tabPanel;
	}
	
	public TabPanel buildRaster() {
		tabPanel.add(sldEditorName.buildRaster());
		tabPanel.add(sldEditorRaster.build());
		tabPanel.setSelection(sldEditorName.getTabItem());
		return tabPanel;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	public SLDEditorName getSldEditorName() {
		return sldEditorName;
	}

	public SLDEditorLine getSldEditorLine() {
		return sldEditorLine;
	}

	public SLDEditorBackground getSldEditorBackground() {
		return sldEditorBackground;
	}

	public SLDEditorLabel getSldEditorLabel() {
		return sldEditorLabel;
	}
	
	public SLDEditorRaster getSldEditorRaster() {
		return sldEditorRaster;
	}	
}