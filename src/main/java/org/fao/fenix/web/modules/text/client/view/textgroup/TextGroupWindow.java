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

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.text.common.vo.TextGroupVO;
import org.fao.fenix.web.modules.text.common.vo.TextVO;

/**
 * TextGroupWindow is split into two main panels:
 * <LI> TextListPanel: the panel on the left, lists the texts which make up the text group. </LI>
 * <LI> TextViewerPanel: the center panel, is where the selected text is viewed or edited. </LI>
 * <br><br>
 *
 * TextGroupWindow forwards every command about texts to the two subpanels,
 * so that each of them can update the info it handles.
 *
 *
 */
public class TextGroupWindow extends FenixWindow {

	public TextListPanel textListPanel;
	private TextViewerPanel textViewerPanel;
	private String windowTitle = BabelFish.print().textGroup();	
	private Long textGroupID;
	private TextGroupVO textGroup;
	private String textGroupTitle;
	
	public TextGroupWindow() {
		setWindowTitle(windowTitle +": "+BabelFish.print().newNew());
		setTextGroupTitle(null);
		setTextGroupID(null);
		setTextGroup(null);
	}
	
	public TextGroupWindow(TextGroupVO textGroupVO) {
		setWindowTitle(windowTitle +": "+textGroupVO.getTitle());
		setTextGroupTitle(textGroupVO.getTitle());
		setTextGroupID(textGroupVO.getResourceId());
		setTextGroup(textGroupVO);
	}

    public void build(boolean isEditable) {

		setTitle(getWindowTitle());
		
		setSize(750, 500);
		setCollapsible(true);
		setMaximizable(true);

		textViewerPanel = new TextViewerPanel(this);
		fillCenterPart(textViewerPanel.build());
	
		textListPanel = new TextListPanel(this, textViewerPanel, isEditable);

		fillWestPart(textListPanel.build());
		getWest().setHeading(BabelFish.print().textList());
		
		//getCenter().setTopComponent(textPanel.egetToolbar());

		show();
    }
 
	public void renameText(long textId, String newTitle) {
		textListPanel.renameText(textId, newTitle);
	}

    public void addText(TextVO text)
    {
		textListPanel.addText(text);
    }


    public void removeText(long textId)
    {
		textListPanel.removeText(textId);
    }

    public TextListPanel getTextListPanel()
    {
		return textListPanel;
    }
    
    public Long getTextGroupID() {
		return textGroupID;
	}

	public void setTextGroupID(Long textGroupID) {
		this.textGroupID = textGroupID;
	}

   public String getTextGroupTitle() {
		return textGroupTitle;
	}

	public void setTextGroupTitle(String textGroupTitle) {
		this.textGroupTitle = textGroupTitle;
	}
	
	public TextGroupVO getTextGroup() {
		return textGroup;
	}

	public void setTextGroup(TextGroupVO textGroup) {
		this.textGroup = textGroup;
	}
	
	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}

}
