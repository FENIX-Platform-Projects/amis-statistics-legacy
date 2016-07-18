package org.fao.fenix.web.modules.shared.poi.server.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.fao.fenix.core.domain.olap.OLAPBean;
import org.fao.fenix.core.domain.olap.OLAPParameters;
import org.fao.fenix.core.utils.OLAPUtils;
import org.fao.fenix.web.modules.core.server.utils.Setting;

public class OLAPExcel {
	
	private static final Logger LOGGER = Logger.getLogger(OLAPExcel.class);
	
	public static void createExcelSheet(HSSFWorkbook workbook, String sheetTitle, OLAPParameters params, List<OLAPBean> beans) {
		

		HSSFSheet sheet = workbook.createSheet(sheetTitle);
		sheet.setDefaultColumnWidth((short)10);
		
		// initiate filename
		sheet.setDefaultColumnWidth((short)10);
		
		// add first row
		createFirstRow(params, workbook, sheet); // TODO use this function as entry point, then differenciate for 2, 3 or 4 dimensions
		
		// add second row
		createSecondRow(params, workbook, sheet); // TODO use this function as entry point, then differenciate for 2, 3 or 4 dimensions
		
		// add rows
		createGenericRows(params, beans, workbook, sheet); // TODO use this function as entry point, then differenciate for 2, 3 or 4 dimensions

	}

	public static String createExcel(OLAPParameters params, List<OLAPBean> beans) {
		
		// initiate filename
		StringBuilder filename = new StringBuilder();
		filename.append(Setting.getSystemPath());
		filename.append("/olapExcel/");
		filename.append(params.getDataSourceTitle());
		filename.append(".xls");
		
		// create the Excel file
		HSSFWorkbook workbook = new HSSFWorkbook();
	    
		// create a sheet inside
		HSSFSheet sheet = workbook.createSheet(params.getDataSourceTitle());
		sheet.setDefaultColumnWidth((short)10);
		if (params.getReportOrientation().equalsIgnoreCase("landscape")) {
			sheet.getPrintSetup().setLandscape(true);
		}
				
		// add first row
		createFirstRow(params, workbook, sheet); // TODO use this function as entry point, then differenciate for 2, 3 or 4 dimensions
		
		// add second row
		createSecondRow(params, workbook, sheet); // TODO use this function as entry point, then differenciate for 2, 3 or 4 dimensions
		
		// add rows
		createGenericRows(params, beans, workbook, sheet); // TODO use this function as entry point, then differenciate for 2, 3 or 4 dimensions
		
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
		
		// return file path
	    return params.getDataSourceTitle() + ".xls";
	}
	
	/* *************************************************************************************************************** */
	/* *************************************************************************************************************** */
	/* ************************************************** FIRST ROW ************************************************** */
	/* *************************************************************************************************************** */
	/* *************************************************************************************************************** */
	
	private static HSSFRow createFirstRow(OLAPParameters params, HSSFWorkbook workbook, HSSFSheet sheet) {
		if (params.getYLabels().isEmpty() && params.getWLabels().isEmpty())
			return create2DFirstRow(params, workbook, sheet);
		else if (!params.getYLabels().isEmpty() && params.getWLabels().isEmpty()) {
			if(params.isShowXLabel())
				return create3DFirstRow(params, workbook, sheet);
			else return null;
		}	
		else if (!params.getYLabels().isEmpty() && !params.getWLabels().isEmpty())
			return create4DFirstRow(params, workbook, sheet);
		return null;
	}
	
	private static HSSFRow create2DFirstRow(OLAPParameters params, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell0 = row.createCell((short)0);
		cell0.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell0.setCellValue("");
		
		int xLabelSpan = params.getXLabels().keySet().size();
		if (params.getYLabels().size() > 0)
			xLabelSpan *= params.getYLabels().size();
		
		HSSFCell cell1 = row.createCell((short)1);
	    cell1.setCellType( HSSFCell.CELL_TYPE_STRING );
	    cell1.setCellValue(params.getXLabel());
	    cell1.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
	    sheet.addMergedRegion(new Region(0, (short)1, 0, (short)xLabelSpan));
	    
		return row;
	}
	
	private static HSSFRow create3DFirstRow(OLAPParameters params, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell0 = row.createCell((short)0);
		cell0.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell0.setCellValue("");
		
		int xLabelSpan = params.getXLabels().keySet().size();
		if (params.getYLabels().size() > 0)
			xLabelSpan *= params.getYLabels().size();
		
		HSSFCell cell1 = row.createCell((short)1);
	    cell1.setCellType( HSSFCell.CELL_TYPE_STRING );
	    cell1.setCellValue(params.getXLabel());
	    cell1.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
	    sheet.addMergedRegion(new Region(0, (short)1, 0, (short)xLabelSpan));
	    
		return row;
	}
	
	private static HSSFRow create4DFirstRow(OLAPParameters params, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(0);
		
		HSSFCell cell0 = row.createCell((short)0);
		cell0.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell0.setCellValue("");
		HSSFCell cell1 = row.createCell((short)1);
		cell1.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell1.setCellValue("");
		
		int xLabelSpan = params.getXLabels().keySet().size();
		if (params.getYLabels().size() > 0)
			xLabelSpan *= params.getYLabels().size();
		xLabelSpan += 1;
		
		HSSFCell cell2 = row.createCell((short)2);
		cell2.setCellType( HSSFCell.CELL_TYPE_STRING );
	    cell2.setCellValue(params.getXLabel());
	    cell2.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
	    sheet.addMergedRegion(new Region(0, (short)2, 0, (short)xLabelSpan));
	    
		return row;
	}
	
	/* **************************************************************************************************************** */
	/* **************************************************************************************************************** */
	/* ************************************************** SECOND ROW ************************************************** */
	/* **************************************************************************************************************** */
	/* **************************************************************************************************************** */
	
	private static HSSFRow createSecondRow(OLAPParameters params, HSSFWorkbook workbook, HSSFSheet sheet) {
		if (params.getYLabels().isEmpty() && params.getWLabels().isEmpty())
			return create2DSecondRow(params, workbook, sheet);
		else if (!params.getYLabels().isEmpty() && params.getWLabels().isEmpty())
			return create3DSecondRow(params, workbook, sheet);
		else if (!params.getYLabels().isEmpty() && !params.getWLabels().isEmpty())
			return create4DSecondRow(params, workbook, sheet);
		return null;
	}
	
	private static HSSFRow create2DSecondRow(OLAPParameters params, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(1);
		HSSFCell cell0 = row.createCell((short)0);
		cell0.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell0.setCellValue("");
		
		int index = 1;
		for (String xLabel : params.getSortedXLabels()) {
			HSSFCell cellN = row.createCell((short)index++);
			cellN.setCellType( HSSFCell.CELL_TYPE_STRING );
//			cellN.setCellType( HSSFCell.CELL_TYPE_NUMERIC );
			cellN.setCellValue(xLabel);
			cellN.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
		}
		
		return row;
	}
	
	private static HSSFRow create3DSecondRow(OLAPParameters params, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(1);
		HSSFCell cell0 = row.createCell((short)0);
		cell0.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell0.setCellValue("");
		
		short yLabelSpan = (short)params.getYLabels().size();
		   
		int index = 1;
		int colIndex = 1;				
		for (String xLabel : params.getSortedXLabels()) {			
			HSSFCell cellN = row.createCell((short)colIndex);
			cellN.setCellType( HSSFCell.CELL_TYPE_STRING );
			cellN.setCellValue(xLabel);
			cellN.setCellStyle(createDarkBlueStyle(workbook));
			short colTo = (short)(colIndex + yLabelSpan - 1);
			sheet.addMergedRegion( new Region(1, (short)colIndex, 1, colTo));
			
			index++;
			colIndex += params.getYLabels().size();
		}
		
		int xLabelSpan = params.getXLabels().keySet().size();
		if (params.getYLabels().size() > 0)
			xLabelSpan *= params.getYLabels().size();
		
		if(params.isShowYLabel()) {
			HSSFRow row2 = sheet.createRow(2);
			HSSFCell cell1 = row2.createCell((short)0);
			cell1.setCellType( HSSFCell.CELL_TYPE_STRING );
			cell1.setCellValue("");

			HSSFCell cell2 = row2.createCell((short)1);
			cell2.setCellType( HSSFCell.CELL_TYPE_STRING );
			LOGGER.info(params.getYLabel());
			cell2.setCellValue(params.getYLabel());
			cell2.setCellStyle(createTitleStyle(workbook, IndexedColors.LIGHT_BLUE));
			sheet.addMergedRegion(new Region(2, (short)1, 2, (short)xLabelSpan));

			HSSFRow row3 = sheet.createRow(3);
			int cellIndex = 1;
			for (String xLabel : params.getSortedXLabels()) {
				for (String yLabel : params.getSortedYLabels()) {
					HSSFCell cellN = row3.createCell((short)cellIndex++);
					cellN.setCellType( HSSFCell.CELL_TYPE_STRING );
					//		    	cellN.setCellType( HSSFCell.CELL_TYPE_NUMERIC );
					cellN.setCellValue(yLabel);
					cellN.setCellStyle(createColouredStyle(workbook, IndexedColors.LIGHT_BLUE));
				}
			}
		}
		else {
			HSSFRow row2 = sheet.createRow(2);
			int cellIndex = 1;
			for (String xLabel : params.getSortedXLabels()) {
				for (String yLabel : params.getSortedYLabels()) {
					HSSFCell cellN = row2.createCell((short)cellIndex++);
					cellN.setCellType( HSSFCell.CELL_TYPE_STRING );
					//				    	cellN.setCellType( HSSFCell.CELL_TYPE_NUMERIC );
					cellN.setCellValue(yLabel);
					cellN.setCellStyle(createColouredStyle(workbook, IndexedColors.LIGHT_BLUE));
				}
			}
		}
		return row;
	}
	
	private static HSSFRow create4DSecondRow(OLAPParameters params, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(1);
		HSSFCell cell0 = row.createCell((short)0);
		cell0.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell0.setCellValue("");
		HSSFCell cell1 = row.createCell((short)1);
		cell1.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell1.setCellValue("");
		
		short yLabelSpan = (short)params.getYLabels().size();
	    
		int index = 2;
		int colIndex = 2;
		for (String xLabel : params.getSortedXLabels()) {
			HSSFCell cellN = row.createCell((short)index);
			cellN.setCellType( HSSFCell.CELL_TYPE_STRING );
			cellN.setCellValue(xLabel);
			cellN.setCellStyle(createDarkBlueStyle(workbook));
			short colTo = (short)(colIndex + yLabelSpan - 1);
			System.out.println(xLabel + " from "  +colIndex + " to " + colTo);
			Region r = new Region(1, (short)colIndex, 1, colTo);
			sheet.addMergedRegion(r);
			index++;
			colIndex += params.getYLabels().size();
		}
		
		int xLabelSpan = params.getXLabels().keySet().size();
		if (params.getYLabels().size() > 0)
			xLabelSpan *= params.getYLabels().size();
		xLabelSpan += 1;
		
		HSSFRow row2 = sheet.createRow(2);
		HSSFCell cell2 = row2.createCell((short)0);
		cell2.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell2.setCellValue("");
		
		HSSFCell cell3 = row2.createCell((short)1);
		cell3.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell3.setCellValue("");
		
		HSSFCell cell4 = row2.createCell((short)2);
		cell4.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell4.setCellValue(params.getYLabel());
		cell4.setCellStyle(createTitleStyle(workbook, IndexedColors.LIGHT_BLUE));
	    sheet.addMergedRegion(new Region(2, (short)2, 2, (short)xLabelSpan));
	    
	    HSSFRow row3 = sheet.createRow(3);
	    int cellIndex = 2;
	    for (String xLabel : params.getSortedXLabels()) {
		    for (String yLabel : params.getSortedYLabels()) {
		    	HSSFCell cellN = row3.createCell((short)cellIndex++);
				cellN.setCellType( HSSFCell.CELL_TYPE_STRING );
				cellN.setCellValue(yLabel);
				cellN.setCellStyle(createColouredStyle(workbook, IndexedColors.LIGHT_BLUE));
		    }
	    }
		
		return row;
	}
	
	/* **************************************************************************************************************** */
	/* **************************************************************************************************************** */
	/* ************************************************** TABLE ROWS ************************************************** */
	/* **************************************************************************************************************** */
	/* **************************************************************************************************************** */
	
	private static void createGenericRows(OLAPParameters params, List<OLAPBean> beans, HSSFWorkbook workbook, HSSFSheet sheet) {
		if (params.getYLabels().isEmpty() && params.getWLabels().isEmpty())
			create2DGenericRows(params, beans, workbook, sheet);
		else if (!params.getYLabels().isEmpty() && params.getWLabels().isEmpty())
			create3DGenericRows(params, beans, workbook, sheet);
		else if (!params.getYLabels().isEmpty() && !params.getWLabels().isEmpty())
			create4DGenericRows(params, beans, workbook, sheet);
	}
	
	private static void create2DGenericRows(OLAPParameters params, List<OLAPBean> beans, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell0 = row.createCell((short)0);
		cell0.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell0.setCellValue(params.getZLabel());
		cell0.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
		
		Map<String, OLAPBean> olapBeansMap = OLAPUtils.create2DOlapBeansMap(beans);
		add2DEmptyBeans(beans, params, olapBeansMap);
		
		int rowIndex = 3;
		
		for (String zLabel : params.getSortedZLabels()) {
			
			String zKey = params.getzCodes().get(zLabel);
			
			int cellIndex = 0;
			
			HSSFRow rowN = sheet.createRow(rowIndex++);
			HSSFCell cellZ = rowN.createCell((short)cellIndex++);
			cellZ.setCellType( HSSFCell.CELL_TYPE_STRING );
			cellZ.setCellValue(zLabel);
			cellZ.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
//			cellZ.setCellStyle(darkBlueStyle);
			
			for (String xLabel : params.getSortedXLabels()) {
				
				String xKey = params.getxCodes().get(xLabel);
				StringBuilder key = OLAPUtils.generateKey(xKey, zKey);
				OLAPBean b = olapBeansMap.get(key.toString());
				
				HSSFCell cellN = rowN.createCell((short)cellIndex++);
				if (b.getQuantity() != null) {
					cellN.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					cellN.setCellValue(b.getQuantity());
				} else {
					cellN.setCellType(HSSFCell.CELL_TYPE_STRING);
					cellN.setCellValue("n.a.");
				}
				cellN.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
				
//				if (rowIndex % 2 == 0)
//					cellN.setCellStyle(lightGrayStyle);
//				else
//					cellN.setCellStyle(darkGrayStyle);
			}
		}
		
	}
	
	private static void create3DGenericRows(OLAPParameters params, List<OLAPBean> beans, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(4);
		HSSFCell cell0 = row.createCell((short)0);
		cell0.setCellType( HSSFCell.CELL_TYPE_STRING );
		if(params.getZHeader()!=null)
			cell0.setCellValue(params.getZHeader());
		else
			cell0.setCellValue(params.getZLabel());
		
		cell0.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
		
		Map<String, OLAPBean> olapBeansMap = OLAPUtils.create3DOlapBeansMap(beans, params);
		add3DEmptyBeans(beans, params, olapBeansMap);
		
		int rowIndex = 5;
		
		for (String zLabel : params.getSortedZLabels()) {
			
			int cellIndex = 0;
			String zKey = params.getzCodes().get(zLabel);
			
			HSSFRow rowN = sheet.createRow(rowIndex++);
			HSSFCell cellZ = rowN.createCell((short)cellIndex++);
			cellZ.setCellType( HSSFCell.CELL_TYPE_STRING );
			cellZ.setCellValue(zLabel);
			cellZ.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
//			cellZ.setCellStyle(darkBlueStyle);
			
			for (String xLabel : params.getSortedXLabels()) {
				
				for (String yLabel : params.getSortedYLabels()) {
					
					String xKey = params.getxCodes().get(xLabel);
					String yKey = params.getyCodes().get(yLabel);
				
					StringBuilder key = OLAPUtils.generateKey(xKey, zKey, yKey);
					OLAPBean b = olapBeansMap.get(key.toString());
					
					HSSFCell cellN = rowN.createCell((short)cellIndex++);					
					if (b.getQuantity() != null) {
						cellN.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cellN.setCellValue(b.getQuantity());
					} else if(b.getStringValue() != null){
						cellN.setCellType(HSSFCell.CELL_TYPE_STRING);
						cellN.setCellValue(b.getStringValue());
					} else {
						cellN.setCellType(HSSFCell.CELL_TYPE_STRING);
						cellN.setCellValue("n.a.");
					}
					cellN.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
					
//					if (rowIndex % 2 == 0)
//						cellN.setCellStyle(lightGrayStyle);
//					else
//						cellN.setCellStyle(darkGrayStyle);
				
				}
			}
		}
	}
	
	private static void create4DGenericRows(OLAPParameters params, List<OLAPBean> beans, HSSFWorkbook workbook, HSSFSheet sheet) {
		
		HSSFRow row = sheet.createRow(4);
		
		HSSFCell cell0 = row.createCell((short)0);
		cell0.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell0.setCellValue(params.getZLabel());
		cell0.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
		
		HSSFCell cell1 = row.createCell((short)1);
		cell1.setCellType( HSSFCell.CELL_TYPE_STRING );
		cell1.setCellValue(params.getWLabel());
		cell1.setCellStyle(createTitleStyle(workbook, IndexedColors.LIGHT_BLUE));
		
		Map<String, OLAPBean> olapBeansMap = OLAPUtils.create4DOlapBeansMap(beans);
		add4DEmptyBeans(beans, params, olapBeansMap);
//		for (String key : olapBeansMap.keySet())
//			LOGGER.info(key + ": " + olapBeansMap.get(key).getQuantity());
		
		int rowIndex = 5;
		int rowSpan = params.getWLabels().size();
		
		// add z labels column
		for (String zLabel : params.getSortedZLabels()) {
			String zKey = params.getzCodes().get(zLabel);
			HSSFRow rowN = sheet.createRow(rowIndex);
			HSSFCell cellN = rowN.createCell((short)0);
			cellN.setCellType( HSSFCell.CELL_TYPE_STRING );
			cellN.setCellValue(params.getZLabels().get(zKey));
			cellN.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
			Region r = new Region(rowIndex, (short)0, rowIndex + rowSpan - 1, (short)0);
			sheet.addMergedRegion(r);
			rowIndex += rowSpan;
		}
		
		// add w labels column
		rowIndex = 5;
		for (String zKey : params.getZLabels().keySet()) {
			for (String wLabel : params.getSortedWLabels()) {
				HSSFRow rowN = sheet.createRow(rowIndex);
				HSSFCell cellN = rowN.createCell((short)1);
				cellN.setCellType( HSSFCell.CELL_TYPE_STRING );
				cellN.setCellValue(wLabel);
				cellN.setCellStyle(createTitleStyle(workbook, IndexedColors.LIGHT_BLUE));
				rowIndex++;
			}
		}
		
		// fill the table
		int toCell = 2 + params.getXLabels().size() * params.getYLabels().size();
		int toRow = 5 + params.getZLabels().size() * params.getWLabels().size();
		
		for (int i = 5 ; i < toRow ; i++) {
			
			for (int j = 2 ; j < toCell ; j++) {
				
				String xLabel = extractXLabel(params, sheet, j);
				String yLabel = extractYLabel(sheet, j);
				String wLabel = extractWLabel(params, sheet, i);
				String zLabel = extractZLabel(params, sheet, i);
				
				String xKey = findKey(params, xLabel, "x");
				String zKey = findKey(params, zLabel, "z");
				String yKey = findKey(params, yLabel, "y");
				String wKey = findKey(params, wLabel, "w");
				
				HSSFRow rowN = sheet.createRow(i);
				HSSFCell cellN = rowN.createCell((short)j);
				cellN.setCellType( HSSFCell.CELL_TYPE_STRING );
				
				StringBuilder key = OLAPUtils.generateKey(xKey, zKey, yKey, wKey);
				OLAPBean b = olapBeansMap.get(key.toString());
								
				if (b != null) {

//					LOGGER.info(key + ": " + b.getQuantity() + " (i%2="+(i%2)+")");
					
					if (b.getQuantity() != null) {
						cellN.setCellType(HSSFCell.CELL_TYPE_STRING);
						cellN.setCellValue(String.valueOf(b.getQuantity()));
					} else {
						cellN.setCellType(HSSFCell.CELL_TYPE_STRING);
						cellN.setCellValue("n.a.");
					}
					cellN.setCellStyle(createTitleStyle(workbook, IndexedColors.DARK_BLUE));
					
//					if (i % 2 == 0)
//						cellN.setCellStyle(lightGrayStyle);
//					else
//						cellN.setCellStyle(darkGrayStyle);
				
				}
			}
		}
		
	}
	
	private static String findKey(OLAPParameters p, String value, String dimension) {
		Map<String, String> map = p.getXLabels();
		if (dimension.equalsIgnoreCase("z"))
			map = p.getZLabels();
		else if (dimension.equalsIgnoreCase("y"))
			map = p.getYLabels();
		if (dimension.equalsIgnoreCase("w"))
			map = p.getWLabels();
		for (String key : map.keySet())
			if (map.get(key).equalsIgnoreCase(value))
				return key;
		return null;
	}
	
	private static String extractXLabel(OLAPParameters params, HSSFSheet sheet, int col) {
		HSSFRow xRow = sheet.getRow(1);
		int ways = params.getYLabels().size();
		HSSFCell xCell = xRow.getCell((short)(2 + col / ways));
		if (xCell != null) 
			return xCell.getStringCellValue();
		return null;
	}
	
	private static String extractZLabel(OLAPParameters params, HSSFSheet sheet, int row) {
		int zIndex = row % params.getZLabels().size();
		Object[] zLabels = params.getZLabels().keySet().toArray();
		return zLabels[zIndex].toString();
	}
	
	private static String extractYLabel(HSSFSheet sheet, int col) {
		HSSFRow yRow = sheet.getRow(3);
		HSSFCell yCell = yRow.getCell((short)col);
		if (yCell != null)
			return yCell.getStringCellValue();
		return null;
	}
	
	private static String extractWLabel(OLAPParameters params, HSSFSheet sheet, int row) {
		int wIndex = (row - 1) % params.getWLabels().size();
		Object[] wLabels = params.getWLabels().keySet().toArray();
		return wLabels[wIndex].toString();
	}
	
	public static void add2DEmptyBeans(List<OLAPBean> beans, OLAPParameters params, Map<String, OLAPBean> olapBeansMap) {

		for (String zLabel : params.getSortedZLabels()) {
			for (String xLabel : params.getSortedXLabels()) {
				String zKey = params.getzCodes().get(zLabel);
				String xKey = params.getxCodes().get(xLabel);
				StringBuilder key = OLAPUtils.generateKey(xKey, zKey);
				OLAPBean b = olapBeansMap.get(key.toString());
				if (b == null) {
					OLAPBean bean = new OLAPBean();
					bean.setX(params.getX());
					bean.setXValue(params.getXLabels().get(xKey));
					bean.setZ(params.getZ());
					bean.setZValue(params.getZLabels().get(zKey));
					bean.setQuantity(null);
					beans.add(bean);
					olapBeansMap.put(key.toString(), bean); // update the map with the empty beans
				}
			}
		}
	}
	
	public static void add3DEmptyBeans(List<OLAPBean> beans, OLAPParameters params, Map<String, OLAPBean> olapBeansMap) {

		for (String zLabel : params.getSortedZLabels()) {
			for (String xLabel : params.getSortedXLabels()) {
				for (String yLabel : params.getSortedYLabels()) {
					String zKey = params.getzCodes().get(zLabel);
					String xKey = params.getxCodes().get(xLabel);
					String yKey = params.getyCodes().get(yLabel);
					StringBuilder key = OLAPUtils.generateKey(xKey, zKey, yKey);
					OLAPBean b = olapBeansMap.get(key.toString());
					if (b == null) {
						OLAPBean bean = new OLAPBean();
						bean.setX(params.getX());
						bean.setXValue(params.getXLabels().get(xKey));
						bean.setZ(params.getZ());
						bean.setZValue(params.getZLabels().get(zKey));
						bean.setY(params.getY());
						bean.setYValue(params.getYLabels().get(yKey));
						bean.setQuantity(null);
						beans.add(bean);
						olapBeansMap.put(key.toString(), bean); // update the map with the empty beans
					}
				}
			}
		}
	}
	
	public static void add4DEmptyBeans(List<OLAPBean> beans, OLAPParameters params, Map<String, OLAPBean> olapBeansMap) {

		for (String zLabel : params.getSortedZLabels()) {
			for (String xLabel : params.getSortedXLabels()) {
				for (String yLabel : params.getSortedYLabels()) {
					for (String wLabel : params.getSortedWLabels()) {
						String zKey = params.getzCodes().get(zLabel);
						String xKey = params.getxCodes().get(xLabel);
						String wKey = params.getwCodes().get(wLabel);
						String yKey = params.getyCodes().get(yLabel);
						StringBuilder key = OLAPUtils.generateKey(xKey, zKey, yKey, wKey);
						OLAPBean b = olapBeansMap.get(key.toString());
						if (b == null) {
							OLAPBean bean = new OLAPBean();
							bean.setX(params.getX());
							bean.setXValue(params.getXLabels().get(xKey));
							bean.setZ(params.getZ());
							bean.setZValue(params.getZLabels().get(zKey));
							bean.setY(params.getY());
							bean.setYValue(params.getYLabels().get(yKey));
							bean.setW(params.getW());
							bean.setWValue(params.getWLabels().get(wKey));
							bean.setQuantity(null);
							beans.add(bean);
							olapBeansMap.put(key.toString(), bean); // update the map with the empty beans
						}
					}
				}
			}
		}
	}
	
	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */
	/* ************************************************** UTILS ************************************************** */
	/* *********************************************************************************************************** */
	/* *********************************************************************************************************** */
	
	private static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook, IndexedColors color) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
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
	
	private static HSSFCellStyle createDarkBlueStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);	    
	    style.setWrapText(true);
	    HSSFFont font = workbook.createFont();
	    font.setColor(IndexedColors.WHITE.getIndex());
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    style.setFont(font);
	    return style;
	}
	
	private static HSSFCellStyle createColouredStyle(HSSFWorkbook workbook, IndexedColors color) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    style.setFillBackgroundColor(color.getIndex());
//	    style.setFillPattern(CellStyle.BIG_SPOTS);	    
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    style.setWrapText(true);
	    HSSFFont font = workbook.createFont();
	    font.setColor(IndexedColors.WHITE.getIndex());
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    style.setFont(font);
	    return style;
	}
	
}
