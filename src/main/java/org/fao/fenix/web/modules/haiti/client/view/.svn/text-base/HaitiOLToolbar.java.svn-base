package org.fao.fenix.web.modules.haiti.client.view;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

public class HaitiOLToolbar {

	private ToolBar toolbar;

	private IconButton exportAsImageButton;
	
	private IconButton mapZoomToCountry;
	
	private Html div;

	public HaitiOLToolbar() {
		toolbar = new ToolBar();
	}

	public ToolBar build() {

		// export as image
		exportAsImageButton = new IconButton("sendToPdf");
		exportAsImageButton.setToolTip(BabelFish.print().exportPdf());
		exportAsImageButton.setEnabled(false);
		toolbar.add(exportAsImageButton);
		toolbar.add(new SeparatorToolItem());
		
//		mapZoomToCountry = new IconButton("mapZoomToCountry");
//		mapZoomToCountry.setTitle("Zoom to country");
//		toolbar.add(mapZoomToCountry);
//		toolbar.add(new SeparatorToolItem());
		
		div = new Html();
		div.setId("ol_map0olpanel");
		div.addStyleName("olControlNavToolbar");
		toolbar.add(div);

		return toolbar;
	}
}
