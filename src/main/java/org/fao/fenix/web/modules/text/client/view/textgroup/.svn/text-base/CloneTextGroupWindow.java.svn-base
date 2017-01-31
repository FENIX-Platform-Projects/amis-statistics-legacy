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

import java.util.Date;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixWindow;
import org.fao.fenix.web.modules.text.client.control.textgroup.TextListController;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * CloneTextGroupWindow
 * Entry point to cloning the existing text group to produce a new text group with a series of new texts
 * The reference date field is appended to the title of the cloned text group title and the title's of the copied text items.
 *  
 */
 
public class CloneTextGroupWindow extends FenixWindow {

	private FormPanel formPanel;
	
	private DateField dateField;
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
	private FormData formData;
	private Html html;
	
	
	public CloneTextGroupWindow(TextGroupWindow tgw) {
			
		    formPanel = new FormPanel();   
		    formPanel.setHeaderVisible(false);   
		    formPanel.setFrame(true); 
		     		    
		    formData = new FormData("-20");   
		   
		    html = new Html();
		    html.setHtml(BabelFish.print().createCopyInstructions());
		    formPanel.add(html, formData);   
		    
		    dateField = new DateField();   
		    dateField.setWidth("100px");
		    dateField.setHideLabel(true);
		 	dateField.getPropertyEditor().setFormat(dateFormat);
		 	dateField.setValue(new Date());		
		 	dateField.setAllowBlank(false);
			formPanel.add(dateField, formData);   
		  
		    Button copy = new Button(BabelFish.print().copy());  
		    copy.addSelectionListener(TextListController.clone(dateField, tgw, this));

		    Button cancel = new Button(BabelFish.print().cancel());  
		    cancel.addSelectionListener(TextListController.cancel(this));

		    formPanel.addButton(copy);   
		    formPanel.addButton(cancel);   
		  
		    formPanel.setButtonAlign(HorizontalAlignment.CENTER);   
		  
		    FormButtonBinding binding = new FormButtonBinding(formPanel);   
		    binding.addButton(copy);
		    binding.addButton(cancel);
		  
	}


    public void build() {

		setTitle(BabelFish.print().copyTextGroup());

		setSize(450, 150);
		setCollapsible(true);
		setMaximizable(true);

		fillCenterPart(formPanel);
		getCenter().setHeaderVisible(false);

		show();
    }


}
