/*
 *  Copyright (C) 2007-2008 Food and Agriculture Organization of the
 *  United Nations (FAO-UN)
 *
 * 	This program is free software; you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation; either version 2 of the License, or (at
 * 	your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful, but
 * 	WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * 	General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 */

package org.fao.fenix.web.modules.map.client.view;


import org.fao.fenix.web.modules.haiti.client.control.HaitiController;
import org.fao.fenix.web.modules.haiti.client.view.HaitiMapTabItem;
import org.fao.fenix.web.modules.haiticnsatool.client.control.HaitiCNSAController;
import org.fao.fenix.web.modules.haiticnsatool.client.view.HaitiCNSAPanel;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.lang.client.HaitiLangEntry;
import org.fao.fenix.web.modules.map.client.control.MapToolbarController;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

/**
 * 
 * @author etj
 */
public class MapPanelControls {
	
	public static ToolBar buildToolbar(MapWindow mw) {

		ToolBar ret = new ToolBar();

		IconButton iconButton;

		iconButton = new IconButton("save");
		iconButton.setToolTip(BabelFish.print().save());
		iconButton.addSelectionListener(MapToolbarController.saveMap(mw));
		ret.add(iconButton);

		iconButton = new IconButton("saveAs");
		iconButton.setToolTip(BabelFish.print().saveAs());
		iconButton.addSelectionListener(MapToolbarController.saveMapAs(mw));
		ret.add(iconButton);

		ret.add(new SeparatorToolItem());

		iconButton = new IconButton("mapViewMetadata");
		iconButton.setTitle(BabelFish.print().viewMetadata());
		iconButton.addSelectionListener(MapToolbarController.showMetadata(mw));
		ret.add(iconButton);

		ret.add(new SeparatorToolItem());

		iconButton = new IconButton("mapExportAsPdf");
		iconButton.setTitle("Export as PDF");
		iconButton.addSelectionListener(MapToolbarController.exportAsPdf(mw));
		ret.add(iconButton);

		iconButton = new IconButton("mapExportAsImage");
		iconButton.setTitle("Export as image");
		iconButton.addSelectionListener(MapToolbarController.exportAsImage(mw));
		ret.add(iconButton);

		iconButton = new IconButton("mapExportAsWmc");
		iconButton.setTitle("Export as Web Map Context");
		iconButton.addSelectionListener(MapToolbarController.exportAsWmc(mw));
		ret.add(iconButton);

		ret.add(new SeparatorToolItem());

		ret.add(new FillToolItem());

		iconButton = new IconButton("mapZoomToCountry");
		iconButton.setTitle("Zoom to country");
		iconButton.addSelectionListener(MapToolbarController.zoomToCountry(mw));
		ret.add(iconButton);

		ret.add(new SeparatorToolItem());

		// open layers reference
		Html div = new Html();
		div.setId("ol_map" + mw.getMapWindowId() + "olpanel"); // fixme name
		// generation
		div.addStyleName("olControlNavToolbar");
		ret.add(div);

		return ret;
	}

	public static ToolBar buildToolbar(HaitiMapTabItem haitiMapTabItem, String language) {
		
		ToolBar ret = new ToolBar();
		IconButton iconButton;
		
		ret.add(new SeparatorToolItem());

		iconButton = new IconButton("mapExportAsPdf");
		iconButton.setTitle("Export as PDF");
		iconButton.addSelectionListener(HaitiController.exportAsPDF(haitiMapTabItem, language));
		ret.add(iconButton);

		ret.add(new SeparatorToolItem());
		iconButton = new IconButton("mapExportAsImage");
		iconButton.setTitle(HaitiLangEntry.getInstance(language).exportAsImage());
		iconButton.addSelectionListener(HaitiController.exportAsImage(haitiMapTabItem, language));
		ret.add(iconButton);
		ret.add(new SeparatorToolItem());

		iconButton = new IconButton("mapZoomToCountry");
		iconButton.setTitle(HaitiLangEntry.getInstance(language).zoomToCountry());
		iconButton.addSelectionListener(HaitiController.zoomToCountry(haitiMapTabItem));
		ret.add(iconButton);
		ret.add(new SeparatorToolItem());

		iconButton = new IconButton("mapZoomToLayer");
		iconButton.setTitle(HaitiLangEntry.getInstance(language).zoomToLayer());
		iconButton.addSelectionListener(HaitiController.zoomToLayer(haitiMapTabItem));
		ret.add(iconButton);

//		ret.add(new FillToolItem());
		ret.add(new SeparatorToolItem());

		Button datasetButton = new Button("Available Data");
		Menu datasetScrollMenu = new Menu();
		datasetScrollMenu.setMaxHeight(200);
		datasetButton.setMenu(datasetScrollMenu);
//		datasetButton.setIconStyle("marker");
		ret.setData("datasetScrollMenu", datasetScrollMenu);
		ret.add(datasetButton);

		// open layers reference
		Html div = new Html();
		div.setId("ol_map0olpanel");
		div.addStyleName("olControlNavToolbar");
		ret.add(div);

		return ret;
	}
	
	
	public static ToolBar buildToolbar(HaitiCNSAPanel haitiCNSAPanel, String language) {
		ToolBar ret = new ToolBar();
		IconButton iconButton;
		
//		ret.add(new SeparatorToolItem());

		iconButton = new IconButton("mapExportAsPdf");
		iconButton.setTitle(HaitiLangEntry.getInstance(language).exportPDFMap());
		iconButton.addSelectionListener(HaitiCNSAController.exportAsPDF(haitiCNSAPanel, language));
		ret.add(iconButton);

		ret.add(new SeparatorToolItem());
		iconButton = new IconButton("mapExportAsImage");
		iconButton.setTitle(HaitiLangEntry.getInstance(language).exportAsImage());
		iconButton.addSelectionListener(HaitiCNSAController.exportAsImage(haitiCNSAPanel, language));
		ret.add(iconButton);

		// open layers reference
		Html div = new Html();
		div.setId("ol_map0olpanel");
		div.addStyleName("olControlNavToolbar");
		ret.add(div);

		return ret;
	}

}
