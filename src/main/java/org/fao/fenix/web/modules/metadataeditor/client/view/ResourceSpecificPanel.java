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
package org.fao.fenix.web.modules.metadataeditor.client.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.ui.HTML;

public class ResourceSpecificPanel {

	private ContentPanel panel;
	
	private VerticalPanel wrapper;
	
	private Map<String, TextField<String>> fieldMap;
	
	public ResourceSpecificPanel() {
		panel = new ContentPanel();
		panel.setHeaderVisible(false);
		panel.setHeight("600px");
		wrapper = new VerticalPanel();
		wrapper.setSpacing(10);
		fieldMap = new HashMap<String, TextField<String>>();
	}
	
	public ContentPanel build() {
		panel.add(wrapper);
		return panel;
	}
	
	public ContentPanel build(List<String> resourceSpecificFields) {
		panel.add(wrapper);
		for (String field : resourceSpecificFields) 
			wrapper.add(buildTextPanel(field, "", true, true));
		return panel;
	}
	
	public HorizontalPanel buildTextPanel(String fieldName, String fieldValue, boolean isMandatory, boolean isEditable) {
		HorizontalPanel textPanel = new HorizontalPanel();
		textPanel.setSpacing(10);
		textPanel.setWidth("750px");
		textPanel.setBorders(true);
		HTML label = new HTML("<b>" + fieldName + ":</b>");
		label.setWidth("100px");
		TextField<String> field = new TextField<String>();
		field.setValue(fieldValue);
		field.setAllowBlank(!isMandatory);
		field.setReadOnly(!isEditable);
		field.setWidth("600px");
		textPanel.add(label);
		textPanel.add(field);
		fieldMap.put(fieldName, field);
		return textPanel;
	}

	public VerticalPanel getWrapper() {
		return wrapper;
	}

	public Map<String, TextField<String>> getFieldMap() {
		return fieldMap;
	}

	public ContentPanel getPanel() {
		return panel;
	}
	
}
