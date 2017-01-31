package org.fao.fenix.web.modules.table.server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.fao.fenix.web.modules.birt.server.utils.BirtUtil;
import org.fao.fenix.web.modules.core.server.utils.EmptyDatumLabel;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class TableExcel {

	private static final Logger LOGGER = Logger.getLogger(TableExcel.class);

//	private static final DecimalFormat twoDigit = new DecimalFormat("#,####0.0000");

	

	/**
	 * 
	 * isAgroupedTable = doesn't repeat the first column more times, but just once.
	 *  
	 */
	public String createExcel(String heading, List<String> headers, List<List<String>> table, Boolean isAgroupedTable) {
		// initiate filename
		StringBuilder filename = new StringBuilder();
		filename.append(Setting.getSystemPath());
		filename.append("/exportObject/");
		String title = "" + BirtUtil.randomChartName();
		filename.append(title);
		filename.append(".xls");
		//		filename.append(".xlsx");

		// create the Excel file
		HSSFWorkbook workbook = new HSSFWorkbook();

		// create a sheet inside
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		sheet.setDefaultColumnWidth((short) 25);
		

		// add heading (not needed)
		if(!heading.isEmpty()){
			int span;
			
			if(table.get(0).size() < 4)
				span = table.get(0).size()*2;
			else
				span =	table.get(0).size();
			
			
			createTitle(heading, span, workbook, sheet);
			
			// add headers
			createHeaders(2, headers, workbook, sheet);
			
			// add contents
			if ( !isAgroupedTable )
				createContents(3, table, workbook, sheet);
			else
				createGroupedContents(3, table, workbook, sheet);
		} else {
			// add headers
			createHeaders(headers, workbook, sheet);
			
			// add contents
			if ( !isAgroupedTable )
				createContents(table, workbook, sheet);
			else
				createGroupedContents(table, workbook, sheet);
		}
		
	

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
		} catch(Exception ex){
			LOGGER.error(ex.getMessage());
		}

		return title + ".xls";
//		return title + ".xlsx";
	}
	
	/**public String createExcel(String titleFilename, String title, List<String> headers, List<List<String>> table, Boolean isAgroupedTable) {
		// initiate filename
		StringBuilder filename = new StringBuilder();
		filename.append(Setting.getSystemPath());
		filename.append("/exportObject/");
		titleFilename = titleFilename + BirtUtil.randomChartName();
		filename.append(titleFilename);
		filename.append(".xls");
		//		filename.append(".xlsx");

		// create the Excel file
		HSSFWorkbook workbook = new HSSFWorkbook();

		// create a sheet inside
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		sheet.setDefaultColumnWidth((short) 25);
		

		// add title (not needed)
		//if ( title != null)
		//	createTitle(title, table.get(0).size(), workbook, sheet);
		
		// add headers
		createHeaders(headers, workbook, sheet);
		
		// add contents
		if ( !isAgroupedTable )
			createContents(table, workbook, sheet);
		else
			createGroupedContents(table, workbook, sheet);
	

		System.out.println("createExcel "+ sheet.getRow(0).getCell((short)0).getStringCellValue());
		System.out.println("createExcel "+ sheet.getRow(0).getCell((short)1).getStringCellValue());
		
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

		return titleFilename + ".xls";
	}**/
	
	public String createExcel(String titleFilename, String title, List<String> headers, List<List<String>> table, Boolean isAgroupedTable) {
		// initiate filename
		
		StringBuilder filename = new StringBuilder();
		filename.append(Setting.getSystemPath());
		filename.append("/exportObject/");
		//String titleFile = "" + BirtUtil.randomChartName();
		
		titleFilename = titleFilename + BirtUtil.randomChartName();
		filename.append(titleFilename);
		filename.append(".xls");
		
		

		// create the Excel file
		HSSFWorkbook workbook = new HSSFWorkbook();

		// create a sheet inside
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		sheet.setDefaultColumnWidth((short) 25);
		
		if(!title.isEmpty()) {
			// add title
			createTitle(title, table.get(0).size(), workbook, sheet);
			
			// add headers
			createHeaders(2, headers, workbook, sheet);

			if ( !isAgroupedTable )
				// add contents
				createContents(3,table, workbook,sheet);
			else
				createGroupedContents(3, table, workbook, sheet);
			
			
			
		} else {
			// add headers
			createHeaders(0, headers, workbook, sheet);
			
			// add contents
			createContents(1, table, workbook, sheet);	
		}
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

		return titleFilename + ".xls";
//		return title + ".xlsx";
	}
	
	public String createExcel(String title, List<String> headers, List<List<String>> table, Integer quantityIndex) {
		// initiate filename
		
		StringBuilder filepath = new StringBuilder();
		filepath.append(Setting.getSystemPath());
		filepath.append("/exportObject/");
		String titleFile = "" + BirtUtil.randomChartName();
		filepath.append(titleFile);
		filepath.append(".xls");

		// create the Excel file
		HSSFWorkbook workbook = new HSSFWorkbook();

		// create a sheet inside
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		sheet.setDefaultColumnWidth((short) 25);
		
		if(!title.isEmpty()) {
			// add title
			createTitle(title, table.get(0).size(), workbook, sheet);
			
			// add headers
			createHeaders(2, headers, workbook, sheet);

			// add contents
			createContents(3,table, workbook,sheet, quantityIndex);
			
		} else {
			// add headers
			createHeaders(0, headers, workbook, sheet);
			
			// add contents
			createContents(1, table, workbook, sheet, quantityIndex);	
		}
		try {
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(filepath.toString());
			System.out.println(filepath.toString());
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return titleFile + ".xls";
//		return title + ".xlsx";
	}

	public String createExcel(String title, List<List<String>> table, Integer quantityIndex, String precision) {

		// initiate filename
		StringBuilder filename = new StringBuilder();
		filename.append(Setting.getSystemPath());
		filename.append("/olapExcel/");
		filename.append(title);
		filename.append(".xls");
//		filename.append(".xlsx");

		// create the Excel file
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// create a sheet inside
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		sheet.setDefaultColumnWidth((short) 25);
		
		//handling decimal places via style
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFDataFormat format = workbook.createDataFormat();
		if(precision!=null)
			style.setDataFormat(format.getFormat(getDecimalFormat(Integer.valueOf(precision))));
		else
			style =null;

		// add title (not needed)
//		createTitle(title, table.get(0).size(), workbook, sheet);

		// add headers
		createHeaders(table.get(0), workbook, sheet);
		
		// add contents
		createContents(table, workbook, sheet, quantityIndex, style);
	

		try {
//			LOGGER.error("filename.toString() = "+filename.toString());
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(filename.toString());
//			System.out.println(filename.toString());
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return title + ".xls";
//		return title + ".xlsx";
	}

	public String createExcel(String titleFilename, String title, List<String> headers, List<List<String>> table, Integer stringColumnIndex, Integer numericColumnIndex) {
		System.out.println("createExcel  EXPORT TO FIX AMIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!?????????????????????????????");
		// initiate filename
		StringBuilder filename = new StringBuilder();
		filename.append(Setting.getSystemPath());
		filename.append("/exportObject/");
		//String titleFile = "" + BirtUtil.randomChartName();
	//	System.out.println("1 fenix web CREATE EXCEL filename = *"+filename.toString()+"*");
		titleFilename = titleFilename + BirtUtil.randomChartName();
		filename.append(titleFilename);
		filename.append(".xls");
		
		// create the Excel file
		HSSFWorkbook workbook = new HSSFWorkbook();

		// create a sheet inside
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		sheet.setDefaultColumnWidth((short) 25);
		
		if(title != null && !title.isEmpty()) {
			// add title
			int span;
			
			if(table.get(0).size() < 3)
				span = table.get(0).size()*2;
			else
				span =	table.get(0).size();
			
				
			createTitle(title, span, workbook, sheet);
			
			// add headers
			if(headers!=null)
				createHeaders(2, headers, workbook, sheet);
			 
			// add contents
			if(stringColumnIndex==null && numericColumnIndex==null){
				//System.out.println("TableExcel:::::: stringColumnIndex && numericColumnIndex == NULL");
				
				createContents(3,table, workbook,sheet); //all to string
			} else {
				//System.out.println("TableExcel:::::: stringColumnIndex == "+stringColumnIndex+" && numericColumnIndex == "+numericColumnIndex);
				if(stringColumnIndex!=null) {
					//System.out.println("TableExcel:::::: stringColumnIndex NOT NULL");
					createContents(3,table, workbook,sheet, stringColumnIndex, true); //set everything numeric except this column
				}
				if(numericColumnIndex!=null){
					//System.out.println("TableExcel:::::: numericColumnIndex NOT NULL");
					createContents(3,table, workbook,sheet, numericColumnIndex, false); //set everything string except this column
				}
			}
			
			
		} else {
			// add headers
			if(headers!=null)
				createHeaders(0, headers, workbook, sheet);
			
			// add contents
			if(stringColumnIndex==null && numericColumnIndex==null){
				createContents(1,table, workbook,sheet); //all to string
			} else {
				if(stringColumnIndex!=null)
					createContents(1,table, workbook,sheet, stringColumnIndex, true); //set everything numeric except this column
				if(numericColumnIndex!=null)
					createContents(1,table, workbook,sheet, numericColumnIndex, false); //set everything string except this column
			}
		}
		try {
			// Write the output to a file
		//	System.out.println("2 fenix web CREATE EXCEL filename = *"+filename.toString()+"*");
			FileOutputStream fileOut = new FileOutputStream(filename.toString());
			System.out.println(filename.toString());
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return titleFilename + ".xls";
//		return title + ".xlsx";
	}
	
	private void createTitle(String title, int span, HSSFWorkbook workbook, HSSFSheet sheet, Integer quantityIndex) {
//		HSSFRow row = sheet.createRow(0);
//		HSSFCell cell = row.createCell((short) 0);
//		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//		cell.setCellValue(title);
//		cell.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE, HSSFCellStyle.ALIGN_CENTER));
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (span - 1)));
	}
	
	private void createTitle(String title, int span, HSSFWorkbook workbook, HSSFSheet sheet) {
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(title);
	    cell.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE, HSSFCellStyle.ALIGN_LEFT));
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (span - 1)));
	}
	
    private void createHeaders(Integer rowIndex, List<String> table, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(rowIndex);
		for (int i = 0; i < table.size(); i++) {		
			HSSFCell cell = row.createCell((short) i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//			System.out.println(table.get(i));
			cell.setCellValue(table.get(i));
			cell.setCellStyle(createHeaderStyle(workbook, IndexedColors.DARK_BLUE, HSSFCellStyle.ALIGN_LEFT));
		}
	}
    
    private int createContents(Integer rowIndex, List<List<String>> table, HSSFWorkbook workbook, HSSFSheet sheet) {
		for (int i = 0; i < table.size(); i++) {
			HSSFRow row = sheet.createRow(rowIndex);
			List<String> content = table.get(i);
			for (int j = 0; j < content.size(); j++) {
				HSSFCell cell = row.createCell((short) j);
//					System.out.println("CELL_TYPE_STRING");
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellStyle(createBordersStyle(workbook));
					
					if(content.get(j).contains("accept.png"))
						cell.setCellValue("true");
					else if(content.get(j).contains("decline.png"))	
						cell.setCellValue("");
					else
						cell.setCellValue(content.get(j));
				
			}
			rowIndex++;
		}
		return table.size();
	}
    
    private int createContents(Integer rowIndex, List<List<String>> table, HSSFWorkbook workbook, HSSFSheet sheet, Integer quantityIndex) {
		for (int i = 0; i < table.size(); i++) {
			HSSFRow row = sheet.createRow(rowIndex);
			List<String> content = table.get(i);
			for (int j = 0; j < content.size(); j++) {
				HSSFCell cell = row.createCell((short) j);
				
				if ( j == quantityIndex) {
//					System.out.println("CELL_TYPE_NUMERIC value = "+content.get(j));
					if(content.get(j).equals(EmptyDatumLabel.getLabel())) {
						cell.setCellType(HSSFCell.CELL_TYPE_BLANK);							
					} else {
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(Double.valueOf(content.get(j).replace(",", "")));			 
					}	
					
				}
				else {
//					System.out.println("CELL_TYPE_STRING");
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(content.get(j));
				}
				
			}
			rowIndex++;
		}
		return table.size();
	}

    private int createContents(Integer rowIndex, List<List<String>> table, HSSFWorkbook workbook, HSSFSheet sheet, Integer columnIndex, Boolean isStringColumnIndex) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!????????????????????????????? createContents columnIndex = "+columnIndex + " isStringColumnIndex " + isStringColumnIndex + " rowIndex = "+rowIndex);
    		 
		for (int i = 0; i < table.size(); i++) {
			HSSFRow row = sheet.createRow(rowIndex);
			List<String> content = table.get(i);
			for (int j = 0; j < content.size(); j++) {
				HSSFCell cell = row.createCell((short) j);

				if(!isStringColumnIndex) {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!????????????????????????????? createContents !isStringColumnIndex (false) " + isStringColumnIndex);

					if ( j == columnIndex) {
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!????????????????????????????? CELL_TYPE_NUMERIC value = '"+content.get(j)+"'");
						if(content.get(j).equals(EmptyDatumLabel.getLabel())) {
							cell.setCellType(HSSFCell.CELL_TYPE_BLANK);		
						} else if(content.get(j).length()==0) {
							cell.setCellType(HSSFCell.CELL_TYPE_BLANK);			 
						} else {
							System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!?????????????????????????????  numeric");
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.valueOf(content.get(j).replace(",", "")));		
						}
					}
					else {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(content.get(j));
					}
				}
				else {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!????????????????????????????? createContents isStringColumnIndex (true) " + isStringColumnIndex);

					if ( j == columnIndex) {
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!????????????????????????????? IF CELL_TYPE_STRING value = "+content.get(j));
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(content.get(j));		 						
					} else {
						System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!????????????????????????????? ELSE CELL_TYPE_NUMERIC  value = '"+content.get(j)+"'");
						if(content.get(j).equals(EmptyDatumLabel.getLabel())) {
							cell.setCellType(HSSFCell.CELL_TYPE_BLANK);		
						} else if(content.get(j).length()==0) {
							cell.setCellType(HSSFCell.CELL_TYPE_BLANK);			 
						} else {
							System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!????????????????????????????? numeric");
						    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.valueOf(content.get(j).replace(",", "")));			 
						}
					}
				}	
				cell.setCellStyle(createBordersStyle(workbook));
			}
			rowIndex++;
		}
		return table.size();
	}

    
    
    
	private void createHeaders(List<String> headers, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.size(); i++) {		
			HSSFCell cell = row.createCell((short) i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		//	System.out.println(headers.get(i));
			cell.setCellValue(headers.get(i));
			cell.setCellStyle(createHeaderStyle(workbook, IndexedColors.DARK_BLUE, HSSFCellStyle.ALIGN_LEFT));
		}	
	}

	private int createContents(List<List<String>> table, HSSFWorkbook workbook, HSSFSheet sheet, Integer quantityIndex, HSSFCellStyle style) {
		for (int i = 1; i < table.size(); i++) {		
			HSSFRow row = sheet.createRow(i);
			List<String> content = table.get(i);
			for (int j = 0; j < content.size(); j++) {
//				System.out.println("row: "+i+" content index : "+j + ": value: "+content.get(j) +": for "+ table.get(0).get(j) );
//				System.out.println("index: " + j + " | " + quantityIndex + " | " + (j == quantityIndex) + "| value "+content.get(j));
				HSSFCell cell = row.createCell((short) j);
				if ( j == quantityIndex) {
//					System.out.println("CELL_TYPE_NUMERIC value = "+content.get(j));
					if(content.get(j).equals(EmptyDatumLabel.getLabel())) {
						cell.setCellType(HSSFCell.CELL_TYPE_BLANK);		
					} else {
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(Double.valueOf(content.get(j).replace(",", "")));			 
					}	
					if(style!=null)
						cell.setCellStyle(style); 
				}
				else {
//					System.out.println("CELL_TYPE_STRING");
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(content.get(j));
				}
//				System.out.println("value: " + j + " | " + quantityIndex + " | " + cell.getCellType());
			}
		}
		return table.size();
	}

	
	private int createContents(List<List<String>> table, HSSFWorkbook workbook, HSSFSheet sheet) {

		int rowIndex = 1;
		for (int i = 0; i < table.size(); i++) {
			HSSFRow row = sheet.createRow(rowIndex);
			List<String> content = table.get(i);
			for (int j = 0; j < content.size(); j++) {
				HSSFCell cell = row.createCell((short) j);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(createBordersStyle(workbook));
				
				if(content.get(j).contains("accept.png"))
					cell.setCellValue("true");
				else if(content.get(j).contains("decline.png"))	
					cell.setCellValue("");
				else
					cell.setCellValue(content.get(j));
				
				
			}
			rowIndex++;
		}
		return table.size();
	}
	
	private int createGroupedContents(List<List<String>> table, HSSFWorkbook workbook, HSSFSheet sheet) {

		int rowIndex = 1;
		String oldValue = "";
		for (int i = 0; i < table.size(); i++) {
			HSSFRow row = sheet.createRow(rowIndex);
			
			List<String> content = table.get(i);
			for (int j = 0; j < content.size(); j++) {
				HSSFCell cell = null;
				if ( j != 0 ) {
					//System.out.println("1 ... "+ content.get(j));
					cell = row.createCell((short) j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellStyle(createBordersStyle(workbook));
					
					if(content.get(j).contains("accept.png"))
						cell.setCellValue("true");
					else if(content.get(j).contains("decline.png"))	
						cell.setCellValue("");
					else if(content.get(j).contains("&"))	
						cell.setCellValue(content.get(j).replace("&", "and"));
					else
						cell.setCellValue(content.get(j));
					
				}
				else {
					//System.out.println(oldValue +" | " + content.get(j));
					if ( oldValue != content.get(j)) {
						cell = row.createCell((short) j);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellStyle(createBordersStyle(workbook));
						
						if(content.get(j).contains("accept.png"))
							cell.setCellValue("true");
						else if(content.get(j).contains("decline.png"))	
							cell.setCellValue("");
						else if(content.get(j).contains("&"))	
							cell.setCellValue(content.get(j).replace("&", "and"));
						else
							cell.setCellValue(content.get(j));
						
						oldValue = content.get(j);
					}
					else {
						oldValue = content.get(j);
						cell = row.createCell((short) j);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellStyle(createBordersStyle(workbook));
						cell.setCellValue("");
					}
				}
			}
			rowIndex++;
		}
		return table.size();
	}
	
	
	private int createGroupedContents(Integer rIndex, List<List<String>> table, HSSFWorkbook workbook, HSSFSheet sheet) {

		int rowIndex = rIndex;
		String oldValue = "";
		for (int i = 0; i < table.size(); i++) {
			HSSFRow row = sheet.createRow(rowIndex);
			List<String> content = table.get(i);
			for (int j = 0; j < content.size(); j++) {
				HSSFCell cell = null;
				if ( j != 0 ) {
				//System.out.println(content.get(j));
					cell = row.createCell((short) j);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellStyle(createBordersStyle(workbook));
					if(content.get(j).contains("accept.png"))
						cell.setCellValue("true");
					else if(content.get(j).contains("decline.png"))	
						cell.setCellValue("");
					else if(content.get(j).contains("&"))	
						cell.setCellValue(content.get(j).replace("&", "and"));
					else
						cell.setCellValue(content.get(j));
				}
				else {
					//System.out.println(oldValue +" | " + content.get(j));
					if ( oldValue != content.get(j)) {
						cell = row.createCell((short) j);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellStyle(createBordersStyle(workbook));
						if(content.get(j).contains("accept.png"))
							cell.setCellValue("true");
						else if(content.get(j).contains("decline.png"))	
							cell.setCellValue("");
						else if(content.get(j).contains("&"))	
							cell.setCellValue(content.get(j).replace("&", "and"));
						else
							cell.setCellValue(content.get(j));
						
						oldValue = content.get(j);
					}
					else {
						oldValue = content.get(j);
						cell = row.createCell((short) j);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellStyle(createBordersStyle(workbook));
						cell.setCellValue("");
					}
				}
			}
			rowIndex++;
		}
		return table.size();
	}


	private HSSFCellStyle createTitleStyle(HSSFWorkbook workbook, IndexedColors color, short alignStyle) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(alignStyle);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		HSSFFont font = workbook.createFont();
		font.setColor(color.getIndex());
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}

	private HSSFCellStyle createHeaderStyle(HSSFWorkbook workbook, IndexedColors color, short alignStyle) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(alignStyle);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
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


	private HSSFCellStyle createBordersStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLUE_GREY.getIndex());
		style.setRightBorderColor(IndexedColors.BLUE_GREY.getIndex());
		style.setTopBorderColor(IndexedColors.BLUE_GREY.getIndex());
		style.setBottomBorderColor(IndexedColors.BLUE_GREY.getIndex());
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		return style;
	}
	
	

	public String getDecimalFormat(int decimals) {
//		LOGGER.info("decimals: " + decimals);
		String decimalFormat =null;
		
		switch (decimals) {
			case 0: decimalFormat = "#0"; break;
			case 1: decimalFormat = "#,#0.0"; break;
			case 2: decimalFormat = "#,##0.00"; break;
			case 3: decimalFormat = "#,###0.000"; break;
			case 4: decimalFormat = "#,####0.0000"; break;
       	}
	
		return decimalFormat;
	}
	
	
	
	
	public void addTableSheet(HSSFWorkbook workbook, String sheetTitle, List<String> headers, List<List<String>> table, Boolean isAgroupedTable) {
		
		HSSFSheet sheet = workbook.createSheet(sheetTitle);
		sheet.setDefaultColumnWidth((short) 25);
		

		// add title (not needed)
//		createTitle(title, table.get(0).size(), workbook, sheet);

		// add headers
		createHeaders(headers, workbook, sheet);
		
		// add contents
		if ( !isAgroupedTable )
			createContents(table, workbook, sheet);
		else
			createGroupedContents(table, workbook, sheet);
		
	}
	
	public String createFileExcelWorkbook(HSSFWorkbook workbook, String title){
		// initiate filename
		StringBuilder filename = new StringBuilder();
		filename.append(Setting.getSystemPath());
		filename.append("/exportObject/");
		title = title + BirtUtil.randomChartName();
		filename.append(title);
		filename.append(".xls");
	

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

}