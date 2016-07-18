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
package org.fao.fenix.web.modules.text.client.control;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;
import org.fao.fenix.web.modules.text.common.services.TextServiceEntry;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class SaveText {

	public void build(final String textContent, final String referenceDate) {

		MetadataEditorWindow meWindow = new MetadataEditorWindow();
		meWindow.build(false, true, false, MESaver.getSaveTextListener(meWindow, textContent, referenceDate));

	}

	public void update(final String textContent, final TextEditor textEditor) {
		System.out.println("textEditor.editorToolBar.dateField: " + textEditor.editorToolBar.dateField.getValue());
		String year = Integer.toString(textEditor.editorToolBar.dateField.getValue().getYear() + 1900);
		String month = Integer.toString(textEditor.editorToolBar.dateField.getValue().getMonth() + 1);
		String day = Integer.toString(textEditor.editorToolBar.dateField.getValue().getDate());

		String date = year + "-" + month + "-" + day;

		// TextServiceEntry.getInstance().updateText(textEditor.getTextViewId(),
		// textContent,
		// (textEditor.editorToolBar.dateField).getValue().toString(), new
		// AsyncCallback<Long>() {
		TextServiceEntry.getInstance().updateText(textEditor.getTextViewId(), textContent, date, new AsyncCallback<Long>() {

			public void onSuccess(final Long result) {
				FenixAlert.info(BabelFish.print().updateCompleted(), BabelFish.print().updateSuccessful());
			}

			public void onFailure(Throwable caught) {
				FenixAlert.error(BabelFish.print().error(), "The text update failed because you do not have update permission or there is a technical problem");
			}

		});
	}

}
