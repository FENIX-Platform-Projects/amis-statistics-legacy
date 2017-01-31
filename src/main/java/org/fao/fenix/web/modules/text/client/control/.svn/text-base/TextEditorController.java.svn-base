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

import java.util.List;

import org.fao.fenix.web.modules.birt.common.services.BirtServiceEntry;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaveAs;
import org.fao.fenix.web.modules.metadataeditor.client.control.MESaver;
import org.fao.fenix.web.modules.metadataeditor.client.view.MetadataEditorWindow;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;
import org.fao.fenix.web.modules.text.common.model.Text;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TextEditorController {

	public static Listener<BaseEvent> save(final TextEditor textEditor) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				// Window.alert("textEditor text "+
				// (textEditor.textArea).getHTML());
				/** if exist removes the <p> tag used when copy and paste from word **/
				String text = cleanText((textEditor.textArea).getHTML());
				System.out.println("TextEditorController.save()\n\n" + text);
				new SaveText().update(text, textEditor);
				
				
			}
		};
	}

	public static Listener<BaseEvent> saveFromTextGroup(final TextEditor textEditor, final Text textModel) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				// Window.alert("textEditor text "+
				// (textEditor.textArea).getHTML());
				/** if exist removes the <p> tag used when copy and paste from word **/
				String text = cleanText((textEditor.textArea).getHTML());
				System.out.println("TextEditorController.save()\n\n" + text);
				
				textModel.setContent(text);
				
				new SaveText().update(text, textEditor);
				
				
			}
		};
	}
	
	public static Listener<BaseEvent> saveAs(final TextEditor textEditor) {
		return new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (textEditor.getTextViewId() == null) {
					MetadataEditorWindow meWindow = new MetadataEditorWindow();
					
					String year = Integer.toString(textEditor.editorToolBar.dateField.getValue().getYear() + 1900);
					String month = Integer.toString(textEditor.editorToolBar.dateField.getValue().getMonth() + 1);
					String day = Integer.toString(textEditor.editorToolBar.dateField.getValue().getDate());
					
					String date = year + "-" + month + "-" +day;
					
					String text = cleanText((textEditor.textArea).getHTML());
					System.out.println("TextEditorController.saveAs()\n\n" + text);
					meWindow.build(false, true, false, MESaver.getSaveTextListener(meWindow, text, date));
				} else {
					MESaveAs.prepopulateText(textEditor.getTextViewId(), true, false, textEditor);
				}
			}
		};
	}

	public static SelectionListener<IconButtonEvent> getTextEditor(final Long id, final List<String> textList, final Window viewerWindow) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				new TextEditor(id, textList, viewerWindow).build();
			}
		};
	}

	public static SelectionListener<IconButtonEvent> exportToPDF(final String textContent) {
		return new SelectionListener<IconButtonEvent>() {
			public void componentSelected(IconButtonEvent ce) {
				BirtServiceEntry.getInstance().exportText(textContent, "pdf", new AsyncCallback() {
					public void onSuccess(Object result) {
						com.google.gwt.user.client.Window.open((String) result, "New Window", "");
					}

					public void onFailure(Throwable caught) {
						FenixAlert.alert("Error", "Export to PDF failed");
					}

				});
			}
		};
	}

	public static SelectionListener<ComponentEvent> addTextToChart(final String text, final String reportDesign) {
		return new SelectionListener<ComponentEvent>() {
			public void componentSelected(ComponentEvent ce) {
				/**
				 * Fenix.reportWizard.report.setHTML("<div style='height:600px; margin:0px auto; padding:0pt; position:relative; width:715px;'><div style='height:33px; left:207px; position:absolute; top:283px; width:300px;'><img src='loading.gif'></div></div>"
				 * );
				 * 
				 * window.close(); AsyncCallback callback = new AsyncCallback()
				 * { public void onSuccess(Object result) {
				 * 
				 * Fenix.reportWizard.rptDesign = (String) ((List)
				 * result).get(0);
				 * Fenix.reportWizard.report.setHTML("<iframe src='/" +
				 * Fenix.reportWizard.nameBirtApp +
				 * "/FenixBirtServletByFile?report=" +
				 * Fenix.reportWizard.rptDesign +
				 * "&servletType=frameset' width='100%' height='100%' />");
				 * 
				 * ListItemBirt newItem = new ListItemBirt();
				 * newItem.setValue((String) ((List) result).get(1));
				 * newItem.setText(Fenix.fenixLang.newText());
				 * newItem.setIconStyle("noteIcon");
				 * 
				 * Fenix.reportWizard.listObject.add(newItem); }
				 * 
				 * public void onFailure(Throwable caught) {
				 * Info.show("Service call failed!",
				 * "Service call to {0} failed", "addTextToReport"); } };
				 * Fenix.birtService.addTextToReport(rptdesign ,
				 * rta.getHTML(),callback);
				 **/

			}
		};
	}

	public static WindowListener onCloseEditor(final Window editor, final Window viewer) {
		return new WindowListener() {
			public void handleEvent(WindowEvent event) {
				if (event.getType() == Events.Hide) {
					if (viewer != null) {
						viewer.hide();
					}
					editor.hide();
				}
			}
		};
	}

	public static WindowListener onCloseViewer(final Window viewer) {
		return new WindowListener() {
			public void handleEvent(WindowEvent event) {
				if (event.getType() == Events.Hide) {
					// remove from project manager & workspace

					viewer.hide();
				}
			}
		};
	}

	public static String cleanText(String text) {
		text = text.replaceAll("<p\\s*.*>","");
		text = text.replace("<p>","");
		text = text.replace("</p>", "");
		return text;
	}

}
