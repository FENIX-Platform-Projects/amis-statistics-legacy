package org.fao.fenix.web.modules.adam.server.report.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.axis.encoding.Base64;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class ADAMReportUtils {
	
	public static Boolean saveChartOnBirt(String birtFolder, String fenixFolder, String filename) {
		
		
		String image = Setting.getFenixPath() + File.separator + "tc" + File.separator +"adam" + File.separator + "adamObjects" + File.separator + filename + "\"";

		
//		LOGGER.info(Setting.getFenixPath());
//		LOGGER.info(Setting.getBirtApplName());
//		LOGGER.info(filename);
		
		File file = new File(Setting.getFenixPath() + File.separator + Setting.getBirtApplName() + File.separator + birtFolder + File.separator + filename);
		System.out.println("PATH: " + file.getAbsolutePath());
		byte[] imageBytes = Base64.decode(image);
		
		System.out.println("imageBytes: " + imageBytes);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(imageBytes);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return true;
	}

}
