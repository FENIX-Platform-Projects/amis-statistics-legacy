package org.fao.fenix.web.modules.cnsa.server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class CNSAExportExcel {

	private static final Logger LOGGER = Logger.getLogger(CNSAExportExcel.class);


	public String createExcel(String title, List<List<String>> table) {

		// initiate filename
		StringBuilder filename = new StringBuilder();
		filename.append(Setting.getSystemPath());
		filename.append("/exportObject/");
		filename.append(title);
		filename.append(".xls");

		// create the Excel file
		HSSFWorkbook workbook = new HSSFWorkbook();

		// create a sheet inside
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		sheet.setDefaultColumnWidth((short) 25);
		

		// add title (not needed)
//		createTitle(title, table.get(0).size(), workbook, sheet);

		// add headers
		createHeaders(table.get(0), workbook, sheet);
		
		// add contents
		createContents(table, workbook, sheet);
	

		try {
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(filename.toString());
			System.out.println(filename.toString());
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		
		return title + ".xls";
	}

	private void createHeaders(List<String> table, HSSFWorkbook workbook, HSSFSheet sheet) {
		System.out.println();
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < table.size(); i++) {		
			HSSFCell cell = row.createCell((short) i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(table.get(i));
			cell.setCellStyle(createTitleStyle(workbook, IndexedColors.ORANGE, HSSFCellStyle.ALIGN_CENTER));
		}
	}

	private int createContents(List<List<String>> table, HSSFWorkbook workbook, HSSFSheet sheet) {

		for (int i = 1; i < table.size(); i++) {		
			HSSFRow row = sheet.createRow(i);
			List<String> content = table.get(i);
			for (int j = 0; j < content.size(); j++) {
				HSSFCell cell = row.createCell((short) j);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(content.get(j));
			}
		}
		return table.size();
	}

	


	private HSSFCellStyle createTitleStyle(HSSFWorkbook workbook, IndexedColors color, short alignStyle) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(alignStyle);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		style.setLeftBorderColor(color.getIndex());
		style.setRightBorderColor(color.getIndex());
		style.setTopBorderColor(color.getIndex());
		style.setBottomBorderColor(color.getIndex());
		HSSFFont font = workbook.createFont();
		font.setColor(color.getIndex());
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}

	private HSSFCellStyle createColouredStyle(HSSFWorkbook workbook, IndexedColors color) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillBackgroundColor(color.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setWrapText(true);
		HSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}


}