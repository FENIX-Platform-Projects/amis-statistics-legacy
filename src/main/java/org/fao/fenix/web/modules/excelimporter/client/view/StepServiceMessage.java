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
package org.fao.fenix.web.modules.excelimporter.client.view;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;

public class StepServiceMessage extends ExcelImporterStepPanel {
	
	private final static String FIELD_WIDTH = "425px";
	
	private final static String OPEN_GREEN = "<font color='#15428B'>";
	
	private final static String OPEN_RED = "<font color='#CA1616'>";
	
	private final static String CLOSE = "</font>";
	
	private Html message;

	public StepServiceMessage(String suggestion, String width) {
		super(suggestion, width);
		message = new Html();
		this.getLayoutContainer().add(buildMessage());
	}
	
	private HorizontalPanel buildMessage() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setSpacing(5);
		panel.setSize("580px", "245px");
		message.setWidth(FIELD_WIDTH);
		panel.add(message);
		panel.setScrollMode(Scroll.AUTO);
		return panel;
	}
	
	public void setMessage(String msg, String colour) {
		String html = "";
		if (colour.equals("GREEN"))
			html += OPEN_GREEN;
		else
			html += OPEN_RED;
		html += msg;
		html += CLOSE;
		message.setHtml(html);
	}
	
}