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

import org.fao.fenix.web.modules.core.client.image.ImageFenixView;
import org.fao.fenix.web.modules.text.client.view.editor.TextEditorToolbar;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

public class TextEditorToolbarController {

	private static TextEditorToolbar toolbar;

	public static ClickListener onClick(final TextEditorToolbar toolbar) {
		return new ClickListener() {
			public void onClick(Widget sender) {
				if (sender == toolbar.bold) {
					toolbar.basic.toggleBold();
				} else if (sender == toolbar.italic) {
					toolbar.basic.toggleItalic();
				} else if (sender == toolbar.underline) {
					toolbar.basic.toggleUnderline();
				} else if (sender == toolbar.subscript) {
					toolbar.basic.toggleSubscript();
				} else if (sender == toolbar.superscript) {
					toolbar.basic.toggleSuperscript();
				} else if (sender == toolbar.strikethrough) {
					toolbar.extended.toggleStrikethrough();
				} else if (sender == toolbar.indent) {
					toolbar.extended.rightIndent();
				} else if (sender == toolbar.outdent) {
					toolbar.extended.leftIndent();
				} else if (sender == toolbar.justifyLeft) {
					toolbar.basic.setJustification(RichTextArea.Justification.LEFT);
				} else if (sender == toolbar.justifyCenter) {
					toolbar.basic.setJustification(RichTextArea.Justification.CENTER);
				} else if (sender == toolbar.justifyRight) {
					toolbar.basic.setJustification(RichTextArea.Justification.RIGHT);
				} else if (sender == toolbar.insertImage) {
					
					ImageFenixView imageFenixView = new ImageFenixView(toolbar);
										
//					String url = Window.prompt("Enter an image URL:", "http://");
//					if (url != null) {
//						toolbar.extended.insertImage(url);
//					}
				} else if (sender == toolbar.createLink) {
					String url = Window.prompt("Enter a link URL:", "http://");
					if (url != null) {
						toolbar.extended.createLink(url);
					}
				} else if (sender == toolbar.removeLink) {
					toolbar.extended.removeLink();
				} else if (sender == toolbar.hr) {
					toolbar.extended.insertHorizontalRule();
				} else if (sender == toolbar.ol) {
					toolbar.extended.insertOrderedList();
				} else if (sender == toolbar.ul) {
					toolbar.extended.insertUnorderedList();
				} else if (sender == toolbar.removeFormat) {
					toolbar.extended.removeFormat();
				}
				// else if (sender == toolbar.backColors) {
				// new ColorPicker(toolbar.setBackColor, 0, 0,
				// toolbar.basic).build(ColorPickerCaller.TEXT_EDITOR_BG);
				// }
				// else if (sender == toolbar.foreColors) {
				// new ColorPicker(toolbar.setForeColor, 0, 0,
				// toolbar.basic).build(ColorPickerCaller.TEXT_EDITOR_FG);
				// }
				else if (sender == toolbar.richText) {
					// RichTextArea's onKeyUp event to update the toolbar
					// status.
					// This will catch any cases where the user moves the cursur
					// using the
					// keyboard, or uses one of the browser's built-in keyboard
					// shortcuts.
					updateStatus(toolbar);
				}
			}
		};
	}

	public static ChangeListener onChange(final TextEditorToolbar toolbar) {
		return new ChangeListener() {
			public void onChange(Widget sender) {
				if (sender == toolbar.backColors) {
					toolbar.basic.setBackColor(toolbar.backColors.getValue(toolbar.backColors.getSelectedIndex()));
					toolbar.backColors.setSelectedIndex(0);
				} else if (sender == toolbar.foreColors) {
					toolbar.basic.setForeColor(toolbar.foreColors.getValue(toolbar.foreColors.getSelectedIndex()));
					toolbar.foreColors.setSelectedIndex(0);
				} else if (sender == toolbar.fonts) {
					toolbar.basic.setFontName(toolbar.fonts.getValue(toolbar.fonts.getSelectedIndex()));
					toolbar.fonts.setSelectedIndex(0);
				} else if (sender == toolbar.fontSizes) {
					toolbar.basic.setFontSize(toolbar.fontSizesConstants[toolbar.fontSizes.getSelectedIndex() - 1]);
					toolbar.fontSizes.setSelectedIndex(0);
				}
			}
		};
	}

	public static KeyboardListener onKey(final TextEditorToolbar toolbar) {
		return new KeyboardListener() {
			public void onKeyDown(Widget sender, char keyCode, int modifiers) {
			}

			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
			}

			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if (sender == toolbar.richText) {
					// RichTextArea's onKeyUp event to update the toolbar
					// status.
					// This will catch any cases where the user moves the cursur
					// using the
					// keyboard, or uses one of the browser's built-in keyboard
					// shortcuts.
					updateStatus(toolbar);
				}
			}
			// }
		};
	}

	/**
	 * Updates the status of all the stateful buttons.
	 */
	private static void updateStatus(final TextEditorToolbar toolbar) {
		if (toolbar.basic != null) {
			toolbar.bold.setDown(toolbar.basic.isBold());
			toolbar.italic.setDown(toolbar.basic.isItalic());
			toolbar.underline.setDown(toolbar.basic.isUnderlined());
			toolbar.subscript.setDown(toolbar.basic.isSubscript());
			toolbar.superscript.setDown(toolbar.basic.isSuperscript());
		}

		if (toolbar.extended != null) {
			toolbar.strikethrough.setDown(toolbar.extended.isStrikethrough());
		}
	}
}
