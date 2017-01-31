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

package org.fao.fenix.web.modules.text.client.view.textgroup;

import org.fao.fenix.web.modules.text.client.view.editor.TextEditor;
import org.fao.fenix.web.modules.text.client.view.viewer.TextViewerToolbar;
import org.fao.fenix.web.modules.text.common.model.Text;
import org.fao.fenix.web.modules.text.common.vo.TextVO;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;

/** 
 * This Panel displays the selected text (from the TextListPanel) and the related toolbar.
 * Depending on the edit privileges of the text, the panel displays either the text content
 * contained within a text editor or a simple non-editable view.
 */
public class TextViewerPanel {
	
	private ContentPanel viewerPanel = new ContentPanel();
	private VerticalPanel verticalPanel = new VerticalPanel();
	private TextViewerToolbar toolbar;
	private TextGroupWindow parent;
	
	private TextVO selectedTextVO;

	public TextViewerPanel(TextGroupWindow parentWindow) {
		this.parent = parentWindow;
	}

	public ContentPanel build()
	{
		verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(10);
		
		viewerPanel.add(verticalPanel);
		return viewerPanel;
	}
		
	public void populateViewer(Text selectedTextItem){
		viewerPanel.removeAll();
		verticalPanel.removeAll();

		selectedTextVO = selectedTextItem.getTextVO();
		viewerPanel.setId(String.valueOf(selectedTextItem.getId()));

		if(selectedTextItem!=null){
			parent.getCenter().setHeading(selectedTextVO.getTitle());
			
			System.out.println(" ---- "+selectedTextVO.getTitle());
			
			if(selectedTextVO.isHasWritePermission()) {
				verticalPanel.add(new TextEditor(selectedTextItem).buildTextEditor());
			}
			else {
				verticalPanel.addText(selectedTextVO.getContent());
			}
		  
			verticalPanel.layout();
			
			parent.getWest().layout();
			
			viewerPanel.add(verticalPanel);
		}
		
		viewerPanel.getLayout().layout();
		
	}
	
	
	

	public TextViewerToolbar getToolbar() {
		return toolbar;
	}

}
