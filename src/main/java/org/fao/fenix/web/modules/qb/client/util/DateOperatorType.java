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
import java.util.Date;
import java.util.List;

import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.table.client.view.utils.FieldParser;

import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Field;


public enum DateOperatorType  {

	equals,
	between,
	isAfter,
	isBefore;
	
	
	public static DateOperatorType valueOfIgnoreCase(String name) {
		for(DateOperatorType type : DateOperatorType.values())
			if(type.toString().equalsIgnoreCase(name))
				return type;
		
		return null;
	}

   public static List<DateOperatorType> getOperatorTypes() {
		List<DateOperatorType> list = new ArrayList<DateOperatorType>();
		for(DateOperatorType type : DateOperatorType.values()) {
			list.add(type);
		}
		return list;
	}
   
  
   public static List<Field> getValueField(String operatorName, String periodType){
	   List<Field> fieldList = new ArrayList<Field>();
	   
	    DateField date = new DateField();
	    date.setValue(new Date()); //default is today's date
	    //format the date field based on period type  
	    date.setPropertyEditor(new DateTimePropertyEditor(FieldParser.getDatePattern(periodType))); 

	    fieldList.add(date);
		 		  	   
	   if(operatorName.contains("between")) {
		   date = new DateField();
		   date.setPropertyEditor(new DateTimePropertyEditor(FieldParser.getDatePattern(periodType))); 
		   date.setEmptyText(BabelFish.print().enterSelectDate());
		   fieldList.add(date);
	   }
	   
    	return fieldList;	
	}
}