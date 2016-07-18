package org.fao.fenix.web.modules.ec.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class ECGIEWSChartsParser {
	
	private static final Logger LOGGER = Logger.getLogger(ECGIEWSChartsParser.class);

	
	public String getGIEWSPriceChart(String isoCode) {
		String nameFile = BirtUtil.randomFileExport() + ".jpg";		
		try {
			URL url = new URL("http://www.fao.org/giews/countrybrief/country/" + isoCode + "/graphics/3.jpg");
//			System.out.println(url.getFile());
			InputStream is = url.openStream();
			DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			File file = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ecCharts/" + nameFile );
			FileOutputStream fos = new FileOutputStream(file);
		    int c=0;
		    while((c=is.read())!= -1)
		         fos.write(c);
		    fos.close();
		    LOGGER.info(file.getPath());
		    return nameFile;
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}	
		return null;	
	}
	
	public String getGIEWSFoodSecurityIssueChart(String isoCode){
		String nameFile = BirtUtil.randomFileExport() + ".jpg";
		
		try {
			URL url = new URL("http://www.fao.org/giews/countrybrief/country/" + isoCode + "/graphics/2.jpg");
//			System.out.println(url.getFile());
			InputStream is = url.openStream();
			DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			File file = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ecCharts/" + nameFile );
			FileOutputStream fos = new FileOutputStream(file);
		    int c=0;
		    while((c=is.read())!= -1)
		         fos.write(c);
		    fos.close();	   	
		    LOGGER.info(file.getPath());
		    return nameFile;
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}	
		return null;	
	}
	
	public void harverstGiewsCharts(String isoCode, String gaulCode, String gaulLabel, String date) {
		harverstPriceChart(isoCode, gaulCode, gaulLabel, date);
		harverstFoodSecurityIssueChart(isoCode, gaulCode, gaulLabel, date);

	}
	
//	gaulCode + "_pricechart_"+ date +".jpg";
//	bean.getPriceBean().setChartFileName("\"" + Setting.getFenixPath() + "/" + Setting.getBirtApplName() + File.separator + "ec" + File.separator + gaulCode + File.separator + priceChart + "\"");
//	String foodIssuesChart = gaulCode + "_foodissueschart_"+ date +".jpg";	
	
	private void harverstPriceChart(String isoCode, String gaulCode, String gaulLabel,  String date) {
//		String nameFile = gaulLabel + "_" + date + "_PriceChart" + ".jpg";	
		String nameFile = gaulCode + "_pricechart_"+ date +".jpg";
		try {
			URL url = new URL("http://www.fao.org/giews/countrybrief/country/" + isoCode + "/graphics/3.jpg");
			InputStream is = url.openStream();
			DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			File file = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ecHarvestedCharts/" + nameFile );
			FileOutputStream fos = new FileOutputStream(file);
		    int c=0;
		    while((c=is.read())!= -1)
		         fos.write(c);
		    fos.close();
		    LOGGER.info(file.getPath());
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}	
	}
	
	private void harverstFoodSecurityIssueChart(String isoCode,String gaulCode, String gaulLabel,  String date) {
//		String nameFile = gaulLabel + "_" + date + "_FoodSecurityChart" + ".jpg";		
		String nameFile = gaulCode + "_foodissueschart_"+ date +".jpg";
		try {
			URL url = new URL("http://www.fao.org/giews/countrybrief/country/" + isoCode + "/graphics/2.jpg");
//			System.out.println(url.getFile());
			InputStream is = url.openStream();
			DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
			File file = new File(Setting.getFenixPath() + "/" + Setting.getBirtApplName() + "/ecHarvestedCharts/" + nameFile );
			FileOutputStream fos = new FileOutputStream(file);
		    int c=0;
		    while((c=is.read())!= -1)
		         fos.write(c);
		    fos.close();	
		    LOGGER.info(file.getPath());
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}	
	}
}
