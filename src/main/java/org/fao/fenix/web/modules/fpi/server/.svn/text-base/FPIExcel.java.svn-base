package org.fao.fenix.web.modules.fpi.server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.fao.fenix.core.domain.coding.Code;
import org.fao.fenix.core.persistence.CodecDao;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class FPIExcel {

	private CodecDao codecDao;

	private static final Logger LOGGER = Logger.getLogger(FPIExcel.class);

	private static final DecimalFormat twoDigit = new DecimalFormat("#,####0.0000");

	private static Map<String, String> indexNotesMap;

	static {
		indexNotesMap = new HashMap<String, String>();
		indexNotesMap.put("Food Price Index", "FPI");
		indexNotesMap.put("Sugar Price Index", "SPI");
		indexNotesMap.put("Meat Price Index", "MPI");
		indexNotesMap.put("Cereals Price Index", "CPI");
		indexNotesMap.put("Oils Price Index", "OPI");
		indexNotesMap.put("Dairy Price Index", "DPI");
	}

	public String createExcel(String indexName, List<List<String>> table) {

		// initiate filename
		StringBuilder filename = new StringBuilder();
		filename.append(Setting.getSystemPath());
		filename.append("/olapExcel/");
		filename.append(indexName);
		filename.append(".xls");

		// create the Excel file
		HSSFWorkbook workbook = new HSSFWorkbook();

		// create a sheet inside
		HSSFSheet sheet = workbook.createSheet(indexName);
		sheet.setDefaultColumnWidth((short) 25);

		// add title
		createTitle(indexName, table.get(0).size(), workbook, sheet);

		// add headers
		createHeaders(table.get(0), workbook, sheet);

		// add contents
		int rowNumber = createContents(table, workbook, sheet);

		// add notes
		LOGGER.info(indexName + " -> " + (indexName.equals("Food Price Index")));
		if (indexNotesMap.keySet().contains(indexName)) {
			List<String> indexNames = new ArrayList<String>();
			LOGGER.info(indexName + " -> " + (indexName.equals("Food Price Index")));
			if (indexName.equals("Food Price Index"))
				for (String value : indexNotesMap.values())
					indexNames.add(value);
			else
				indexNames.add(indexNotesMap.get(indexName));
			createNotes(indexNames, rowNumber, table.get(0).size(), workbook, sheet);
		}

		try {
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(filename.toString());
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return indexName + ".xls";
	}

	private void createTitle(String indexName, int span, HSSFWorkbook workbook, HSSFSheet sheet) {
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(indexName);
		cell.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE, HSSFCellStyle.ALIGN_CENTER));
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (span - 1)));
	}

	private void createHeaders(List<String> table, HSSFWorkbook workbook, HSSFSheet sheet) {
		HSSFRow row = sheet.createRow(1);
		for (int i = 0; i < table.size(); i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(table.get(i));
			cell.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE, HSSFCellStyle.ALIGN_CENTER));
		}
	}

	private int createContents(List<List<String>> table, HSSFWorkbook workbook, HSSFSheet sheet) {
		for (int i = 1; i < table.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);
			List<String> content = table.get(i);
			for (int j = 0; j < content.size(); j++) {
				HSSFCell cell = row.createCell((short) j);
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				if (j > 0)
					cell.setCellValue(twoDigit.format(Double.valueOf(content.get(j))));
				else
					cell.setCellValue(content.get(j));
			}
		}
		return table.size();
	}

	private void createNotes(List<String> indexNames, int rowNumber, int span, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		int rowCounter = 2 + rowNumber;
		
		for (String indexName : indexNames) {
			
			Code code = codecDao.getCodeFromCodeSystemRegion(indexName, "FPINotes", "0", "EN");
			
			HSSFRow row = sheet.createRow(rowCounter);
			HSSFCell cell = row.createCell((short) 0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(code.getLabel());
			cell.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE, HSSFCellStyle.ALIGN_LEFT));
			sheet.addMergedRegion(new Region(rowCounter, (short) 0, rowCounter, (short) (span - 1)));
			rowCounter++;
			
			HSSFRow row2 = sheet.createRow(rowCounter);
			HSSFCell cell2 = row2.createCell((short) 0);
			cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell2.setCellValue(code.getDescription());
//			cell2.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE, HSSFCellStyle.ALIGN_LEFT));
			sheet.addMergedRegion(new Region(rowCounter, (short) 0, rowCounter, (short) (span - 1)));
			rowCounter++;
		}
	}

//	private StringBuilder getNotes(String noteCode) {
//		StringBuilder sb = new StringBuilder();
//		Code code = codecDao.getCodeFromCodeSystemRegion(noteCode, "FPINotes", "0", "EN");
//		sb.append(code.getLabel());
//		sb.append("\n");
//		sb.append(code.getDescription());
//		sb.append("\n");
//		return sb;
//	}

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

	public void setCodecDao(CodecDao codecDao) {
		this.codecDao = codecDao;
	}

}