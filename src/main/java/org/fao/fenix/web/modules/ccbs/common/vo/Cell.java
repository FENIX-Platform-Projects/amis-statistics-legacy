package org.fao.fenix.web.modules.ccbs.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.IsSerializable;

// simple class to implement text and numeric cells; no subclassing, etc.
public class Cell implements IsSerializable
{
	private String      text;
	private double      number;
	private boolean     numeric;
	private Page        page;
	private int         rowNum, colNum;
	private boolean     valid, updated;
	private CellFormula formula;

	public Cell() {}

	void init(Page page, int rowNum, int colNum)
	{
		this.page    = page;
		this.rowNum  = rowNum;
		this.colNum  = colNum;

		valid   = true;
		updated = false;
	}
	
	public boolean isValid()                   { return valid;           }
	public void    setValid(boolean valid)     { this.valid = valid;     }
	public boolean isUpdated()                 { return updated;         }
	public void    setUpdated(boolean updated) { this.updated = updated; }

	public int getPageNum() { return page.getPageNum(); }

	public int getRowNum()  { return rowNum;  }
	public int getColNum()  { return colNum;  }

	public boolean isNumeric() { return numeric; }

	public Double getValue()
	{
		if (numeric) return number;
		else if (text != null)
		{
			try
			{
				return Double.parseDouble(text);
			}
			catch (NumberFormatException nfe)
			{
				return 0.0;
			}
		}
		else return 0.0;
	}

	public void setValue(double value)
	{
		number  = value;
		numeric = true;
	}

	public String getText()
	{
		if      (numeric)      return NumberFormat.getFormat("########0.##").format(number);
		else if (text == null) return "";
		else                   return text;
	}

	public void setText(String text)
	{
		this.text = text;
		numeric   = false;
	}

	private List<Cell> bkRefs = new ArrayList<Cell>(); // backward references (cells cite in cell formula
	private List<Cell> fwRefs = new ArrayList<Cell>(); // forward references (cells citing this in their formula)

	// add a reference by coordinates
	public Cell addRef(int pageNum, int rowNum, int colNum)
	{
		return addRef(page.getBook().getCell(pageNum, rowNum, colNum));
	}
	// add a reference in the same page by page coordinates
	public Cell addRef(int rowNum, int colNum)
	{
		return addRef(page.getCell(rowNum, colNum));
	}
	// add a reference in the same page and column by row coordinates
	public Cell addRef(int rowNum)
	{
		return addRef(page.getCell(rowNum, colNum));
	}

	private Cell addRef(Cell ref)
	{
		bkRefs.add(ref);
		ref.fwRefs.add(this);
		return this;
	}

	List<Cell> getBkRefs() { return bkRefs; }
	List<Cell> getFwRefs() { return fwRefs; }

	public int countNotValidRefs()
	{
		int n = 0;
		for (Cell ref: bkRefs)
			if (!ref.isValid()) n++;
		return n;
	}

	public Cell setFormula(CellFormula formula)
	{
		this.formula = formula;
		formula.init(this);
		return this;
	}

	public double eval()
	{
		if (formula == null) return getValue();
		else                 return formula.eval();
	}
}
