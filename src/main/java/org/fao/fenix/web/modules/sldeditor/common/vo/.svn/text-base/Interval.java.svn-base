package org.fao.fenix.web.modules.sldeditor.common.vo;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class Interval extends BaseModelData{
	
	public Interval ()
	{}
		
	public Interval (double minValue, double maxValue, String color)
	{
		set("minValue", minValue);
		set("maxValue", maxValue);
		set("color", color);
	}
	
	 public double getMinValue() {  
		 Double minValue = (Double) get("minValue");
		 return minValue.doubleValue();
	 }
	 public double getMaxValue() {  
		 Double maxValue = (Double) get("maxValue");
		 return maxValue.doubleValue();
	 }
	
	 public String getColor() {  
		 String color = (String) get("color");
		 return color;
	 }
	
	 public void setMinValue(double minValue)
	 {
		 set("minValue", minValue);
	 }
	 
	 public void setMaxValue(double maxValue)
	 {
		 set("maxValue", maxValue);
	 }
	 
	 public void setColor(String color)
	 {
		 set("color", color);
	 }	
}