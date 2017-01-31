package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class MonthForecast extends BaseModelData {

	  public MonthForecast() {
	  }

	  public MonthForecast(String monthForecast) {
		  setMonthForecast(monthForecast);
	  }

	  public String  getMonthForecast() {
		      return get("monthForecast");
		    }
	  
	 public void setMonthForecast(String monthForecast) {
	    set("monthForecast", monthForecast);
	  }
}
