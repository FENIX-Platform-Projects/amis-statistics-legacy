package org.fao.fenix.web.modules.ccbs.client.view;

public class PageDecoration
{
	private String  title;
	private String  rowHeaders[];
	private int     rowHeadersWidth;
	private String  colHeaders[];
	private int     colHeaderWidths[];
	private boolean editableRows[], editableCols[];

	public PageDecoration(String title,
	                      String rowHeaders[], int rowHeadersWidth,   boolean editableRows[],
	                      String colHeaders[], int colHeaderWidths[], boolean editableCols[])
	{
		this.title           = title;
		this.rowHeaders      = rowHeaders;
		this.rowHeadersWidth = rowHeadersWidth;
		this.editableRows    = editableRows;
		this.colHeaders      = colHeaders;
		this.colHeaderWidths = colHeaderWidths;
		this.editableCols    = editableCols;
	}

	public String getTitle()             { return title;       }
	public void   setTitle(String title) { this.title = title; }

	public int     numRows()                { return rowHeaders.length;  }
	public String  getRowHeader(int i)      { return rowHeaders[i];      }
	public int     getRowHeadersWidth()     { return rowHeadersWidth;    }
	public boolean isRowEditable(int i)     { return editableRows[i]; }
	public int     numCols()                { return colHeaders.length;  }
	public String  getColHeader(int i)      { return colHeaders[i];      }
	public int     getColHeaderWidth(int i) { return colHeaderWidths[i]; }
	public boolean isColEditable(int i)     { return editableCols[i];    }
}
