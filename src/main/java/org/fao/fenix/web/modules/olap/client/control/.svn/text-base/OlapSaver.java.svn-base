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
package org.fao.fenix.web.modules.olap.client.control;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.olap.client.view.OlapWindow;
import org.fao.fenix.web.modules.olap.common.services.OlapServiceEntry;
import org.fao.fenix.web.modules.olap.common.vo.OLAPParametersVo;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class OlapSaver extends OlapController {

	public static SelectionListener<IconButtonEvent> save(final OlapWindow window) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				OLAPParametersVo pvo = retrieveParameters(window);
				pvo.setOlapHtml(window.getHtml().toString());
				if (window.getResourceViewID() == null) {
					MetadataEditorWindow w = new MetadataEditorWindow();
					w.build(false, true, false, MESaver.getSaveOlapListener(w, pvo, window));
				} else {
					updateOLAP(pvo);
				}
			}
		};
	}
	
	private static void updateOLAP(final OLAPParametersVo pvo) {
		final LoadingWindow l = new LoadingWindow("Update", "Multidimensional Table is going to be updated.", "Please wait...");
		OlapServiceEntry.getInstance().saveOlap(pvo, new AsyncCallback<Long>() {
			public void onSuccess(Long id) {
				l.destroyLoadingBox();
				FenixAlert.info("INFO", "Multidimensional Table has been updated.");
				l.destroyLoadingBox();
			}
			public void onFailure(Throwable e) {
				l.destroyLoadingBox();
				FenixAlert.alert(BabelFish.print().warning(), BabelFish.print().faq());
				l.destroyLoadingBox();
			}
		});
	}
	
}