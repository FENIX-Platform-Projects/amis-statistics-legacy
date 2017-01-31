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

package org.fao.fenix.web.modules.qb.client.util;


import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

public enum StringOperatorType {

	equals,
	inList,
	startsWith,
	contains,
	like;
	
	public static StringOperatorType valueOfIgnoreCase(String name) {
		for(StringOperatorType type : StringOperatorType.values())
			if(type.toString().equalsIgnoreCase(name))
				return type;
		return null;
	}

   public static List<StringOperatorType> getOperatorTypes() {
		List<StringOperatorType> list = new ArrayList<StringOperatorType>();
		for(StringOperatorType type : StringOperatorType.values()) {
			list.add(type);
		}
		return list;
	}
   
   public static List<Field> getValueField(String operatorName){
	   List<Field> fieldList = new ArrayList<Field>();
	   	   
	   if(operatorName.contains("List")) {
		  // ListBox selectedValues = new ListBox(true); //true = multipleselect
		  ListStore store = new ListStore();
	      ComboBox combo =  new ComboBox();
	      combo.setTriggerAction(TriggerAction.ALL);
	      combo.setStore(store);
	   	  combo.setId("valueField");
		  fieldList.add(combo);
	   }
	   else {
		   TextField text = new TextField();
		   text.setEmptyText(BabelFish.print().enterText());
		   text.setId("valueField");
		   fieldList.add(text);
	   }
    	return fieldList;	
   }
  	
}