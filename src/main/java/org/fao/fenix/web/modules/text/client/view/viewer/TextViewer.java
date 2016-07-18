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

package org.fao.fenix.web.modules.text.client.view.viewer;

import java.util.List;

import org.fao.fenix.web.modules.core.client.security.FenixUser;
import org.fao.fenix.web.modules.core.client.utils.SocialBar;
import org.fao.fenix.web.modules.core.common.vo.ResourceType;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.text.client.control.TextEditorController;
import org.fao.fenix.web.modules.text.common.vo.TextVO;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;


public class TextViewer extends FenixWindow{

	public String textTitle;
	public Long textID;
	public HTML textContent;
	public String textReferenceDate;
	public String textLastUpdated;

	private String index;
	private List<String> data;

	private TextViewerToolbar viewerToolBar;

	private TextVO textVO;


	public TextViewer() {
	}

	public TextViewer(TextVO textVO) {
		
		textVO = textVO;
		
		setTextViewId(Long.valueOf(textVO.getResourceId()));
		setTextTitle(textVO.getTitle());
		setTextContent(new HTML(textVO.getContent()));
		if (textVO.getReferenceDate() != null && !textVO.getReferenceDate().equals("null")) 
			setReferenceDate(textVO.getReferenceDate());
		if (textVO.getDateLastUpdate()!= null) 
			setLastUpdated(textVO.getDateLastUpdate().toString());

		setData(data);
	}
	
	
    public TextViewer(Long id,  List<String> textList) {

		final List<String> data = textList;
		
		setTextViewId(id);
		setTextTitle((String) data.get(1));
		setTextContent(new HTML((String) data.get(2)));
		if ((String) data.get(3) != null && !((String) data.get(3)).equals("null")) 
			setReferenceDate((String) data.get(3));
		if ((String) data.get(4) != null) 
			setLastUpdated((String) data.get(4));
		

		setData(data);
	}


	public TextViewer(Long id,  String index,  List<String> textList) {

		final List<String> data = textList;
		this.data = data;
		this.index = index;

		setTextViewId(id);
		setTextTitle((String) data.get(1));
		setTextContent(new HTML((String) data.get(2)));
		if ((String) data.get(3) != null && !((String) data.get(3)).equals("null")) 
			setReferenceDate((String) data.get(3));
		if ((String) data.get(4) != null) 
			setLastUpdated((String) data.get(4));
		
	}


	public Long getTextViewId() {
		return textID;
	}

	public void setTextViewId(Long textID) {
		this.textID = textID;
	}

	public String getTextTitle() {
		return textTitle;
	}

	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}

	public HTML getTextContent() {
		return textContent;
	}

	public void setTextContent(HTML textContent) {
		this.textContent = textContent;
	}

	public void setReferenceDate(String textReferenceDate) {
		this.textReferenceDate = textReferenceDate;
	}

	public String getReferenceDate() {
		return textReferenceDate;
	}

	public void setLastUpdated(String textLastUpdated) {
		this.textLastUpdated = textLastUpdated;
	}

	public String getLastUpdated() {
		return textLastUpdated;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public List<String> getData() {
		return data;
	}

	public void build(boolean isEditable){
		setTitle(getTextTitle() + " ("+BabelFish.print().referenceDate()  +": "+ getReferenceDate()+ ")");

		if(getReferenceDate()!=null)
			setTitle(getTextTitle() + " ("+BabelFish.print().referenceDate()  +": "+ getReferenceDate()+ ")");	
		else setTitle(getTextTitle());
	
			
		setCollapsible(true);
		setMaximizable(true);
		setSize(650, 430);
		getWindow().setClosable(true);

		viewerToolBar = new TextViewerToolbar(this, isEditable);

		setCenterProperties();
		getCenter().setTopComponent(viewerToolBar.getToolbar());
		
		getCenter().add(getTextContentContainer());
		addCenterPartToWindow();

		//on close handler
		getWindow().addWindowListener(TextEditorController.onCloseViewer(getWindow()));
		
		getCenter().setHeaderVisible(false);
		
		SocialBar sb = new SocialBar();
		getCenter().setBottomComponent(sb.getSocialBar(ResourceType.TEXT, String.valueOf(getTextViewId())));

		show();
	}

	public LayoutContainer getTextContentContainer(){
		LayoutContainer c = new LayoutContainer();
		c.setLayout(new FitLayout());
	    c.setScrollMode(Style.Scroll.AUTO);
	   
		VerticalPanel vp = new VerticalPanel();
		vp.setSpacing(10);
		vp.addText(getTextContent().toString());
		
		c.add(vp);
		return c;
	}

}
