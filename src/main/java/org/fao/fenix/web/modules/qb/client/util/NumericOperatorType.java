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

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.NumberField;


public enum NumericOperatorType  {

	equals,
	isLessThan,
	isLessThanOrEqualTo,
	isGreaterThan,
	isGreaterThanOrEqualTo,
	between;
	
	
	public static NumericOperatorType valueOfIgnoreCase(String name) {
		for(NumericOperatorType type : NumericOperatorType.values())
			if(type.toString().equalsIgnoreCase(name))
				return type;
		
		return null;
	}

   public static List<NumericOperatorType> getOperatorTypes() {
		List<NumericOperatorType> list = new ArrayList<NumericOperatorType>();
		for(NumericOperatorType type : NumericOperatorType.values()) {
			list.add(type);
		}
		return list;
	}
   
   public static List<Field> getValueField(String operatorName){
	   List<Field> fieldList = new ArrayList<Field>();
	   
	   NumberField number = new NumberField();
	   number.setEmptyText(BabelFish.print().enterNumber());
	   fieldList.add(number);

	   if(operatorName.contains("between")) {
		   number = new NumberField();
		   fieldList.add(number);
	   } 
	   
    	return fieldList;	
	}

}