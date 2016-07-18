package org.fao.fenix.web.modules.amis.client.view.inputccbs;

import java.util.HashMap;

import org.fao.fenix.web.modules.amis.common.vo.ClientCbsDatasetResult;

public class PageDecoration
{
	private String  title;
	private String  elementUnits[];
	private String  subElementUnits[];
	private String  rowHeaders[];
	private int     rowHeadersWidth;
	private String  colHeaders[];
	private int     colHeaderWidths[];
	private boolean editableRows[], editableCols[];
	private String commodity;
	private String cropNum;
	private HashMap<String, String> monthForYears;
	private String dataSource;
	private String countryCode;
	private String countryName;
	private String commodityCode;
	private String commodityName;
	private String firstMonthMarketingYear;
	
	public PageDecoration(String title, String elementUnits[], String subElementUnits[],
	                      String rowHeaders[], int rowHeadersWidth,   boolean editableRows[],
	                      String colHeaders[], int colHeaderWidths[], boolean editableCols[], 
	                      String commodity, String cropNum, HashMap<String, String> monthForYears,
	                      String dataSource, String countryCode, String countryName, String commodityCode,
	                      String commodityName, String firstMonthMarketingYear)
	{
		this.title           = title;
		this.elementUnits	 = elementUnits;
		this.subElementUnits = subElementUnits;
		this.rowHeaders      = rowHeaders;
		this.rowHeadersWidth = rowHeadersWidth;
		this.editableRows    = editableRows;
		this.colHeaders      = colHeaders;
		this.colHeaderWidths = colHeaderWidths;
		this.editableCols    = editableCols;
		this.commodity 		 = commodity;
		this.cropNum		 = cropNum;
		this.monthForYears	 = monthForYears;
		this.dataSource		 = dataSource;
		this.countryCode	 = countryCode;
		this.countryName	 = countryName;
		this.commodityCode	 = commodityCode;
		this.commodityName	 = commodityName;
		this.firstMonthMarketingYear = firstMonthMarketingYear;
	}

	public String getTitle()             { return title;       }
	public void   setTitle(String title) { this.title = title; }

	public int     numRows()                { return rowHeaders.length;  }
	public String  getelementUnit(int i)    { return elementUnits[i];      }
	public String  getRowHeader(int i)      { return rowHeaders[i];      }
	public int     getRowHeadersWidth()     { return rowHeadersWidth;    }
	public boolean isRowEditable(int i)     { return editableRows[i]; }
	public int     numCols()                { return colHeaders.length;  }
	public String  getColHeader(int i)      { return colHeaders[i];      }
	public int     getColHeaderWidth(int i) { return colHeaderWidths[i]; }
	public boolean isColEditable(int i)     { return editableCols[i];    }
	public String getCommodity()             { return commodity;       }
	public void   setCommodity(String commodity) { this.commodity = commodity; }
	public String getCropNum()             { return cropNum;       }
	public void   setCropNum(String cropNum) { this.cropNum = cropNum; }
	public HashMap<String, String> getMonthForYears() {	return monthForYears;}
	public void setMonthForYears(HashMap<String, String> monthForYears) {this.monthForYears = monthForYears;}

	public String getDataSource() 			{ return dataSource;	}
	public void setDataSource(String dataSource) {	this.dataSource = dataSource; }
	public String getCountryCode() { return countryCode;}
	public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
	public String getCountryName() { return countryName; }
	public void setCountryName(String countryName) { this.countryName = countryName; }
	public String getCommodityCode() { return commodityCode; }
	public void setCommodityCode(String commodityCode) { this.commodityCode = commodityCode; }
	public String getCommodityName() { return commodityName; }
	public void setCommodityName(String commodityName) { this.commodityName = commodityName; }
	public String[] getSubElementUnits() { return subElementUnits; }
	public void setSubElementUnits(String[] subElementUnits) { this.subElementUnits = subElementUnits; }
	public String getFirstMonthMarketingYear() 	{ return firstMonthMarketingYear; }
	public void setFirstMonthMarketingYear(String firstMonthMarketingYear) { this.firstMonthMarketingYear = firstMonthMarketingYear;}	
	
}
