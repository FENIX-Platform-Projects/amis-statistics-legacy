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

package org.fao.fenix.web.modules.map.client.control;

import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.map.client.control.action.CountryZoomer;
import org.fao.fenix.web.modules.map.client.control.action.ExportMapAsImage;
import org.fao.fenix.web.modules.map.client.control.action.ExportMapAsWmc;
import org.fao.fenix.web.modules.map.client.control.action.SaveMap;
import org.fao.fenix.web.modules.map.client.view.MapWindow;
import org.fao.fenix.web.modules.map.client.view.form.ExportAsPDFForm;
import org.fao.fenix.web.modules.metadataeditor.client.control.MEOpener;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;

/**
 *
 * @author etj
 */
public class MapToolbarController {
	
	public static SelectionListener<IconButtonEvent> showMetadata(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
                if(mw.getMapViewId() == -1)
                    FenixAlert.error(BabelFish.print().error(),
									"Map is not saved yet");
                else
                    ResourceOpener.openMetadata(mw.getMapViewId(), false);
			}
		};
	}

	public static SelectionListener<ComponentEvent> todo(final MapWindow mw) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent e) {
				Info.display("TODO", 
							"This feature has not been implemented yet (mv:{0})", 
							""+mw.getMapViewId());
			}
		};
	}
	
	public static SelectionListener<ComponentEvent> openMetadataEditor(final MapWindow mw) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent e) {
				// new MetadataEditor(new MetadataViewer(mw.getMapViewId())).build();
				MEOpener.showMetadata(mw.getMapViewId(), false, true);
			}
		};
	}

	public static SelectionListener<IconButtonEvent> saveMap(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
                new SaveMap(mw).run();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> saveMapAs(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
                new SaveMap(mw, true).run();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> exportAsPdf(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				new ExportAsPDFForm(mw.buildClientMapView()).run();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> exportAsImage(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				new ExportMapAsImage(mw).run();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> exportAsWmc(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				new ExportMapAsWmc(mw).run();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> zoomToCountry(final MapWindow mw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				new CountryZoomer(mw).run();
			}
		};
	}

//	public static SelectionListener develtest(final MapWindow mw) {
//		return new SelectionListener<ComponentEvent>() {
//			public void componentSelected(ComponentEvent e) {
//				Info.display("TESTING...",
//							"Running a client test: also check output on console");
//
//                //ClientMapView cmv = mw.buildClientMapView();
//				int l = mw.llp.getLayersCount();
//				Info.display(""+ l,
//							"" + l + " layers in list");
//				System.out.println("cmv: layers " + l);
//				for (int i = 0; i < l; i++) {
//					LayerItem li = mw.llp.getLayerByIndex(i);
//					System.out.println(li.getClientGeoView());
//				}
//
//			}
//		};
//	}

}
