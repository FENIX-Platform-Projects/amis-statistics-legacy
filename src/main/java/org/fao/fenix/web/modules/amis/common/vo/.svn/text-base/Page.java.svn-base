package org.fao.fenix.web.modules.amis.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Page implements IsSerializable
{
	private Book     book;
	private int      pageNum;
	private int      nRows, nCols;
	private Cell[][] cells;

	public Page() {}
	
	public Page(int nRows, int nCols)
	{
		this.nRows  = nRows;
		this.nCols  = nCols;

		cells = new Cell[nRows][nCols];
		for (int rowNum = 0; rowNum < nRows; rowNum++)
			for (int colNum = 0; colNum < nCols; colNum++)
			{
				Cell cell = new Cell();
				cell.init(this, rowNum, colNum);
				cells[rowNum][colNum] = cell;
			}
	}

	void init(Book book, int pageNum)
	{
		this.book    = book;
		this.pageNum = pageNum;
	}

	public int numRows()  { return nRows; }
	public int numCols()  { return nCols; }

	public Book getBook()    { return book;    }
	public int  getPageNum() { return pageNum; }

	public void setCell(int rowNum, int colNum, double value)
	{
		cells[rowNum][colNum].setValue(value);
	}

	public void setCell(int rowNum, int colNum, String text)
	{
		cells[rowNum][colNum].setText(text);
	}

	public Cell getCell(int rowNum, int colNum)
	{
		return cells[rowNum][colNum];
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder("Page " + pageNum + ":\n");
		for (int i = 0; i < nRows; i++)
		{
			sb.append(i);
			for (int j = 0; j < nCols; j++)
				sb.append("\t").append(cells[i][j]);
			sb.append("\n");
		}
		return sb.toString();
	}
}
