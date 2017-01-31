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

import com.extjs.gxt.ui.client.widget.TabItem;

public class SLDEditorTabItem {

	private TabItem tabItem;
	
	private final String LABEL_WIDTH = "150px";
	
	private final String FIELD_WIDTH = "370px";
	
	private final String FIELD_HEIGHT = "315px";
	
	private final int SPACING = 5;
	
	private final String LABEL_WIDTH_RASTER = "300px";
	
	private final String FIELD_WIDTH_RASTER = "450px";
	
	private final String FIELD_HEIGHT_RASTER = "385px";
	
	private final int SPACING_RASTER = 10;
	
	public SLDEditorTabItem(String header) {
		tabItem = new TabItem(header);
	}

	public TabItem getTabItem() {
		return tabItem;
	}

	public String getLABEL_WIDTH() {
		return LABEL_WIDTH;
	}

	public String getFIELD_WIDTH() {
		return FIELD_WIDTH;
	}

	public String getFIELD_HEIGHT() {
		return FIELD_HEIGHT;
	}

	public int getSPACING() {
		return SPACING;
	}
	
	public String getLABEL_WIDTH_RASTER() {
		return LABEL_WIDTH_RASTER;
	}

	public String getFIELD_WIDTH_RASTER() {
		return FIELD_WIDTH_RASTER;
	}

	public String getFIELD_HEIGHT_RASTER() {
		return FIELD_HEIGHT_RASTER;
	}

	public int getSPACING_RASTER() {
		return SPACING_RASTER;
	}
}