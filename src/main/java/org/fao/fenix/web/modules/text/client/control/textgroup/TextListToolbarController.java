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

package org.fao.fenix.web.modules.text.client.control.textgroup;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.ResourceOpener;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.text.client.control.vo.TextItem;
import org.fao.fenix.web.modules.text.client.view.textgroup.CloneTextGroupWindow;
import org.fao.fenix.web.modules.text.client.view.textgroup.TextGroupWindow;
import org.fao.fenix.web.modules.text.common.model.Text;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;

import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.DataListItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TextListToolbarController {

	public static SelectionListener<IconButtonEvent> addText(final TextGroupWindow tgw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				new AddText(tgw).load();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> cloneTextGroup(final TextGroupWindow tgw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				List<Long> textIds = new ArrayList<Long>();

				List<DataListItem> textItems = tgw.getTextListPanel().getAllTextItems();

				if (textItems == null || textItems.size() < 1) {
					FenixAlert.info("No text", "This Text Group contains no text", "No text");
				} else {
					new CloneTextGroupWindow(tgw).build();

					/*
					 * for(DataListItem item:
					 * tgw.getTextListPanel().getAllTextItems()) { TextItem text
					 * = (TextItem)item; textIds.add(text.getTextId()); }
					 * 
					 * if (tgw.getTextGroupID() == null) { MetadataEditorWindow
					 * meWindow = new MetadataEditorWindow();
					 * 
					 * System.out.println(
					 * "TextListToolbarController.saveAs() text list size:\n\n"
					 * + textIds.size()); meWindow.build(false, true, false,
					 * MESaver.getSaveTextGroupListener(meWindow, textIds,
					 * tgw));
					 * 
					 * } else {
					 * MESaveAs.prepopulateTextGroup(tgw.getTextGroupID(), true,
					 * false, textIds, tgw); }
					 */
				}
			}
		};
	}

	public static SelectionListener<IconButtonEvent> saveAs(final TextGroupWindow tgw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				List<Long> textIds = new ArrayList<Long>();

				List<DataListItem> textItems = tgw.getTextListPanel().getAllTextItems();

				if (textItems == null || textItems.size() < 1) {
					FenixAlert.info("No text", "This Text Group contains no text", "No text");
				} else {
					for (DataListItem item : tgw.getTextListPanel().getAllTextItems()) {
						TextItem text = (TextItem) item;
						textIds.add(text.getTextId());
					}

					if (tgw.getTextGroupID() == null) {
						MetadataEditorWindow meWindow = new MetadataEditorWindow();

						System.out.println("TextListToolbarController.saveAs() text list size:\n\n" + textIds.size());
						meWindow.build(false, true, false, MESaver.getSaveTextGroupListener(meWindow, textIds, tgw));

					} else {
						MESaveAs.prepopulateTextGroup(tgw.getTextGroupID(), true, false, textIds, tgw);
					}
				}
			}
		};
	}

	public static SelectionListener<IconButtonEvent> save(final TextGroupWindow tgw) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				List<Long> textIds = new ArrayList<Long>();
				GWT.log("in save", null);

				List<DataListItem> textItems = tgw.getTextListPanel().getAllTextItems();

				if (textItems == null || textItems.size() < 1) {
					FenixAlert.info("No text", "This Text Group contains no text", "No text");
				} else {
					for (DataListItem item : tgw.getTextListPanel().getAllTextItems()) {
						Text text = item.getModel();
						textIds.add(text.getId());
					}

					if (tgw.getTextGroupID() == null) {
						MetadataEditorWindow meWindow = new MetadataEditorWindow();

						System.out.println("TextListToolbarController.saveAs() text list size:\n\n" + textIds.size());
						meWindow.build(false, true, false, MESaver.getSaveTextGroupListener(meWindow, textIds, tgw));

					} else {
						TextServiceEntry.getInstance().updateTextGroup(tgw.getTextGroupID(), textIds, new AsyncCallback<Long>() {

							public void onSuccess(final Long result) {
								FenixAlert.info(BabelFish.print().updateCompleted(), BabelFish.print().updateSuccessful());
							}

							public void onFailure(Throwable caught) {
								FenixAlert.error(BabelFish.print().error(), "The text group update failed because you do not have update permission or there is a technical problem");
							}

						});
					}
				}
			}
		};
	}

	public static SelectionListener<IconButtonEvent> showTextGroupMetadata(final long textGroupId, final boolean isTextEditable) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent e) {
				ResourceOpener.openMetadata(textGroupId, isTextEditable);
			}
		};
	}

}
