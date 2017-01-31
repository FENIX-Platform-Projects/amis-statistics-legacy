package org.fao.fenix.web.modules.ccbs.common.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Book implements IsSerializable
{
	private int    nPages;
	private Page[] pages;

	public Book() {}

	public Book(int nPages)
	{
		this.nPages = nPages;

		pages = new Page[nPages];
	}

	public int  numPages()                      { return nPages;         }
	public Page getPage(int pageNum)            { return pages[pageNum]; }
	public void setPage(int pageNum, Page page)
	{
		pages[pageNum] = page;
		page.init(this, pageNum);
	}

	public Cell getCell(int pageNum, int rowNum, int colNum)
	{
		return getPage(pageNum).getCell(rowNum, colNum);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder("Book:\n");
		for (int i = 0; i < nPages; i++)
			sb.append(pages[i]);
		return sb.toString();
	}

	public interface UpdateListener
	{
		public void bookUpdated();
		public void cellUpdated(int pageNum, int rowNum, int colNum);
	}

	private List<UpdateListener> listeners = new ArrayList<UpdateListener>();

	public void addBookUpdateListener(UpdateListener listener)
	{
		listeners.add(listener);
	}

	public synchronized void update() throws Exception
	{
		// initialize queue with not valid cells
		Set<Cell> pending = new HashSet<Cell>();
		for (int pageNum = 0; pageNum < nPages; pageNum++)
		{
			Page page = pages[pageNum];
			for (int rowNum = 0; rowNum < page.numRows(); rowNum++)
				for (int colNum = 0; colNum < page.numCols(); colNum++)
				{
					Cell cell = page.getCell(rowNum, colNum);
					if (!cell.isValid())
						pending.add(page.getCell(rowNum, colNum));
				}
		}
		// main computation loop
		while (!pending.isEmpty())
		{
			// find a not valid cell with all valid references
			// TODO: has less than optimal complexity
			Cell cell = null;
			for (Cell c: pending)
			{
				if (c.countNotValidRefs() == 0)
				{
					cell = c;
					break;
				}
			}
			if (cell == null) throw new Exception("Circular references found");

			// update cell value
			pending.remove(cell);
			cell.setValue(cell.eval());
			int    pageNum = cell.getPageNum();
			int    rowNum  = cell.getRowNum();
			int    colNum  = cell.getColNum();

			// reset not valid and set updated
			cell.setValid(true);
			cell.setUpdated(true);

			// invalidate dependent cells and add to the queue
			for (Cell c: cell.getFwRefs())
				pending.add(c);

			// fire cellUpdated event
			for (UpdateListener listener: listeners)
				listener.cellUpdated(pageNum, rowNum, colNum);
		}
		// fire bookUpdated event
		for (UpdateListener listener: listeners)
			listener.bookUpdated();

		// reset updated cells
		for (int pageNum = 0; pageNum < nPages; pageNum++)
		{
			Page page = pages[pageNum];
			for (int rowNum = 0; rowNum < page.numRows(); rowNum++)
				for (int colNum = 0; colNum < page.numCols(); colNum++)
				{
					Cell cell = page.getCell(rowNum, colNum);
					if (cell.isUpdated())
						cell.setUpdated(false);
				}
		}
	}
}
