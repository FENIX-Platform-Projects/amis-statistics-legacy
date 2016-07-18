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
package org.fao.fenix.web.modules.ec.common.vo;

import com.extjs.gxt.ui.client.data.BaseModel;

@SuppressWarnings("serial")
public class ECItem extends BaseModel {

  public ECItem() {
  }
  
  public ECItem(String name, String value) {
	    set("name", name);
	    set("value", value);
	  }
  
  public ECItem(String name, String value, String showPriceLegend, String showFoodBalanceLegend, String showVegetationLegend, String unhcrDate, String viewData, String reportType) {
	    set("name", name);
	    set("value", value);
	    set("showPriceLegend", showPriceLegend);
	    set("showFoodBalanceLegend", showFoodBalanceLegend);
	    set("showVegetationLegend", showVegetationLegend);
	    set("unhcrDate", unhcrDate);
	    set("viewData", viewData);
	    set("reportType", reportType);
	  }

  public ECItem(String name, String value, Boolean isHaitiEmergency) {
    set("name", name);
    set("value", value);
    set("isHaitiEmergency", isHaitiEmergency);
  }

  public String getName() {
    return (String) get("name");
  }

  public String getValue() {
    return (String) get("value");
  }
  
  public Boolean isHaitiEmergency() {
	return (Boolean) get("isHaitiEmergency");
  }
  
  public String getShowPriceLegend() {
	return (String) get("showPriceLegend");
  }
  
  public String getShowFoodBalanceLegend() {
	return (String) get("showFoodBalanceLegend");
  }
  
  public String getShowVegetationLegend() {
		return (String) get("showVegetationLegend");
	  }
	  
  public String getUnhcrDate() {
	return (String) get("unhcrDate");
  }
  
  public String getViewData() {
	return (String) get("viewData");	
  }
  
  
  public String getReportType() {
	return (String) get("reportType");	
  }

}