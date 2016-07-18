package org.fao.fenix.web.modules.birt.server.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.model.api.DesignConfig;
import org.eclipse.birt.report.model.api.IDesignEngine;
import org.eclipse.birt.report.model.api.IDesignEngineFactory;
import org.eclipse.birt.report.model.api.SessionHandle;
import org.fao.fenix.web.modules.core.server.utils.Setting;

import com.ibm.icu.util.ULocale;

public class BirtUtil {

	public final static int TABLE_LIMIT=830;
	
	public static SessionHandle getDesignSessionHandle() {

		try {
			DesignConfig config = new DesignConfig();
			config.setBIRTHome(Setting.getReportEngine());

			Platform.startup(config);
			IDesignEngineFactory factory = (IDesignEngineFactory) Platform
					.createFactoryObject(IDesignEngineFactory.EXTENSION_DESIGN_ENGINE_FACTORY);
			IDesignEngine engine = factory.createDesignEngine(config);

			SessionHandle sessionHandle = engine
					.newSessionHandle(ULocale.ENGLISH);

			return sessionHandle;

		} catch (BirtException e) {
			System.out.println("Failure to create design engine: "
					+ e.getMessage());
			e.printStackTrace();
		}

		return null;

	}
	
	public static IReportEngine getReportEngine() {
		EngineConfig config = new EngineConfig();
		config.setBIRTHome(Setting.getReportEngine());
		
		try {
			Platform.startup(config);
			IReportEngineFactory factory = (IReportEngineFactory) Platform
					.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
			IReportEngine engine = factory.createReportEngine(config);
			return engine;
		} catch (Exception e) {
			System.out.println("Failure to create report engine: " + e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String randomNameFile() {
		return String.valueOf((Math.random() * 10)) + ".rptdesign";

	}
	
	public static String randomFileExport() {
		return String.valueOf((Math.random() * 10));

	}
	
	public static String randomChartName() {
		String chartName = String.valueOf((Math.random() * 10) +1);
		chartName = chartName.replace(".", "");
		System.out.println("chartName: " + chartName);
		return chartName;

	}
	
	public static String fileImagine(BufferedImage image){
		
		String nameFile=null;
		
		try{
			File f=new File(Setting.getFenixPath() +"/"+Setting.getBirtApplName()+"/MapImg");
			
			synchronized (f) {
				int i=f.list().length;
				nameFile="img" + i + ".png";
			}
					
			
			ImageIO.write(image, "png", new File(Setting.getFenixPath() +"/"+Setting.getBirtApplName()+"/MapImg/"+nameFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return nameFile; 
	
	}
	
	public static float getDecimalPart(float d){
		String doubleToString = String.valueOf(d);
		int index=0;
		for (int i=0; i<doubleToString.length(); i++){
			if (doubleToString.charAt(i)=='.'){
				index=i;
				break;
			}
		}
		doubleToString = doubleToString.substring(index, doubleToString.length());
		doubleToString = "0" + doubleToString;
		return Float.valueOf(doubleToString);
		
	}
	
	/**
	 * Remove spaces form a string.
	 * 
	 * @param string Original string
	 * @return String with no spaces
	 */
	public String fenixTrim(String string) {
		char[] noSpace = new char[string.length()];
		int index = 0;
		for (int i = 0 ; i < string.length() ; i++)
			if (string.charAt(i) != ' ')
				noSpace[index++] = string.charAt(i); 
		return String.valueOf(noSpace, 0, noSpace.length);
	}

	
	public static List<Integer> convertHextToRGB(String hex) {
		List<Integer> rgb = new ArrayList<Integer>();
		if (hex == null || hex.equals("")) {return null;}
		hex = hex.replaceAll("#", "");
		
		while (hex.length() < 6) {
			hex = "0" + hex;
		}
		
		String red = "0x"+hex.substring(0, 2);
		String green = "0x"+hex.substring(2, 4);
		String blue = "0x"+hex.substring(4, 6);
//		System.out.println(red + " | " + green + " | " + blue);
		rgb.add(Integer.decode(red).intValue());
		rgb.add(Integer.decode(green).intValue());
		rgb.add(Integer.decode(blue).intValue());
		return rgb;
	}
	


}
