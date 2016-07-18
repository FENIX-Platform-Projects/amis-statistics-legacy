package org.fao.fenix.web.modules.giews.server;

import java.util.Date;

import org.fao.fenix.core.utils.FieldParser;

public class GIEWSUtils {

	@SuppressWarnings("deprecation")
	public static String createAfricaLayerCodeDA(Date date) {
		String code = "raster_di";
		String dateString = FieldParser.parseDate(date);
		code += dateString.substring(2, 4);
		code += dateString.substring(5, 7);
		if (date.getDate() == 1)
			code += "1";
		else if (date.getDate() == 11)
			code += "2";
		else if (date.getDate() == 21)
			code += "3";
		code += "_da";
		return code;
	}
	
	@SuppressWarnings("deprecation")
	public static String createAfricaLayerCode(Date date) {
		String code = "raster_di";
		String dateString = FieldParser.parseDate(date);
		code += dateString.substring(2, 4);
		code += dateString.substring(5, 7);
		if (date.getDate() == 1)
			code += "1";
		else if (date.getDate() == 11)
			code += "2";
		else if (date.getDate() == 21)
			code += "3";
		return code;
	}
	
	@SuppressWarnings("deprecation")
	public static String createLayerCodeDA(Date date) {
		String code = "raster_ri";
		String dateString = FieldParser.parseDate(date);
		code += dateString.substring(2, 4);
		code += dateString.substring(5, 7);
		if (date.getDate() == 1)
			code += "1";
		else if (date.getDate() == 11)
			code += "2";
		else if (date.getDate() == 21)
			code += "3";
		code += "_da";
		return code;
	}
	
	@SuppressWarnings("deprecation")
	public static String createLayerCode(Date date) {
		String code = "raster_";
		String dateString = FieldParser.parseDate(date);
		String year = dateString.substring(0, 4);
		code += String.valueOf(Integer.valueOf(year) - 1900);
		code += dateString.substring(5, 7);
		if (date.getDate() == 1)
			code += "1";
		else if (date.getDate() == 11)
			code += "2";
		else if (date.getDate() == 21)
			code += "3";
		code += "-rain";
		return code;
	}
	
	@SuppressWarnings("deprecation")
	public static String createNdviLayerCodeDA(Date date) {
		String code = "raster_dv";
		String dateString = FieldParser.parseDate(date);
		code += dateString.substring(2, 4);
		code += dateString.substring(5, 7);
		if (date.getDate() == 1)
			code += "1";
		else if (date.getDate() == 11)
			code += "2";
		else if (date.getDate() == 21)
			code += "3";
		code += "_da";
		return code;
	}
	
	@SuppressWarnings("deprecation")
	public static String createNdviLayerCode(Date date) {
		String code = "raster_dv";
		String dateString = FieldParser.parseDate(date);
		code += dateString.substring(2, 4);
		code += dateString.substring(5, 7);
		if (date.getDate() == 1)
			code += "1";
		else if (date.getDate() == 11)
			code += "2";
		else if (date.getDate() == 21)
			code += "3";
		return code;
	}
	
}