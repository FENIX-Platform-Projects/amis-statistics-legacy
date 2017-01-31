
package org.fao.fenix.web.modules.sldeditor.common.vo;

import com.extjs.gxt.ui.client.data.BaseModelData;

public class RGBContrast extends BaseModelData{
	
	public RGBContrast ()
	{}
	
	public RGBContrast (String nameChannel, String typeChannelContrast, double gammaValueContrast)
	{
		set("nameChannel", nameChannel);
		set("typeChannelContrast", typeChannelContrast);
		set("gammaValueContrast", gammaValueContrast);
	}
	
	 public String getnameChannel() {  
		 String nameChannel = (String) get("nameChannel");
		 return nameChannel;
	 }
	
	 public String gettypeChannelContrast() {  
		 String typeChannelContrast = (String) get("typeChannelContrast");
		 return typeChannelContrast;
	 }
	
	 public double getgammaValueContrast() {  
		 Double gammaValueContrast = (Double) get("gammaValueContrast");
		 return gammaValueContrast.doubleValue();
	 }
	
	 public void setnameChannel(String nameChannel)
	 {
		 set("nameChannel", nameChannel);
	 }
	 
	 public void settypeChannelContrast(String typeChannelContrast)
	 {
		 set("typeChannelContrast", typeChannelContrast);
	 }
	 
	 public void setgammaValueContrast(double gammaValueContrast)
	 {
		 set("gammaValueContrast", gammaValueContrast);
	 }
}
