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
package org.fao.fenix.web.modules.sldeditor.client.view;

import java.util.List;

import org.fao.fenix.web.modules.sldeditor.common.vo.SLDEditorRuleVO;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;

public class SLDEditorName extends SLDEditorTabItem {

	private VerticalPanel wrapper;
	
	private TextField<String> styleNameField;
	
	private TextArea descriptionArea;
	
	private SLDEditorWindow myWindow;
	
	public SLDEditorName(String header, SLDEditorWindow myWindow) {
		super(header);
		this.myWindow = myWindow;
		styleNameField = new TextField<String>();
		descriptionArea = new TextArea();
	}
	
	public TabItem build() {
		
		wrapper = new VerticalPanel();
		wrapper.setSpacing(this.getSPACING());
		
		Html styleNameLabel = new Html("<b>Style Name</b>");
		styleNameLabel.setWidth(this.getLABEL_WIDTH());
		wrapper.add(styleNameLabel);
		
		styleNameField.setWidth(this.getFIELD_WIDTH());
		styleNameField.setEmptyText("Style Name");
		styleNameField.setAllowBlank(false);
		wrapper.add(styleNameField);
		
		Html descriptionLabel = new Html("<b>Description</b>");
		descriptionLabel.setWidth(this.getLABEL_WIDTH());
		wrapper.add(descriptionLabel);
		
		descriptionArea.setSize(this.getFIELD_WIDTH(), this.getFIELD_HEIGHT());
		descriptionArea.setEmptyText("Style Description");
		wrapper.add(descriptionArea);
		
		this.getTabItem().add(wrapper);
		
		this.populateUi();
		
		return this.getTabItem();
	}

	private void populateUi() {
		
		if(myWindow != null)
		{
			List<SLDEditorRuleVO> list = myWindow.getVoList();
			if(list != null)
			{
				if(list.size() != 0)
				{
					styleNameField.setValue(list.get(0).getNameUserLayer());
					descriptionArea.setValue(list.get(0).getAbstractUserStyle());
				}
			}
		}
	}

	public TabItem buildRaster() {
		
		wrapper = new VerticalPanel();
		wrapper.setSpacing(this.getSPACING_RASTER());
		
		Html styleNameLabel = new Html("<b>Style Name</b>");
		styleNameLabel.setWidth(this.getLABEL_WIDTH_RASTER());
		wrapper.add(styleNameLabel);
		
		styleNameField.setWidth(this.getFIELD_WIDTH_RASTER());
		styleNameField.setEmptyText("Style Name");
		styleNameField.setAllowBlank(false);
		wrapper.add(styleNameField);
		
		Html descriptionLabel = new Html("<b>Description</b>");
		descriptionLabel.setWidth(this.getLABEL_WIDTH_RASTER());
		wrapper.add(descriptionLabel);
		
		descriptionArea.setSize(this.getFIELD_WIDTH_RASTER(), this.getFIELD_HEIGHT_RASTER());
		descriptionArea.setEmptyText("Style Description");
		wrapper.add(descriptionArea);
		
		this.getTabItem().add(wrapper);
		
		this.populateUi();
		
		return this.getTabItem();
	}

	public TextField<String> getStyleNameField() {
		return styleNameField;
	}

	public TextArea getDescriptionArea() {
		return descriptionArea;
	}
	
}