package org.fao.fenix.web.modules.amis.common.vo;

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
	private boolean isNullFromDb;

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
	public boolean isNullFromDb()                   { return isNullFromDb;           }
	public void    setNullFromDb(boolean isNullFromDb)     { this.isNullFromDb = isNullFromDb;     }

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

	public String getValueNull()
	{
		return getValueNull(null);
	}
	
	public String getValueNull(String newText)
	{
		//System.out.println("Class: Cell Function: getValueNull Text :numeric "+ numeric);
		if(newText!=null)
		{
			//System.out.println("Class: Cell Function: getValueNull Text :if(newText!=null)");
			//text = newText;
			return newText;
		} else {
			//System.out.println("Class: Cell Function: getValueNull Text :if(newText==null)");
		if (numeric) 
			{
			//System.out.println("Class: Cell Function: getValueNull Text :number "+ number);
			return ""+number;
			}
		else if (text != null)
		{
			try
			{
				//System.out.println("Class: Cell Function: getValueNull Text :text "+ text);
				return text;
			}
			catch (NumberFormatException nfe)
			{
				return "";
			}
		}
		
		else return "";
		}
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
		//System.out.println("Class: Cell Function: setTexts Text :text "+ text + "rowNum "+ rowNum +" ColNum "+colNum);
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

//	public double eval()
//	{
//		if (formula == null) return getValue();
//		else                 return formula.eval();
//		else                 return Double.parseDouble(formula.evalStringNull());
//	}
	public String eval()
	{
		String result = eval(null);
		return result;
	}
	
	public String eval(String text)
	{
		//if (formula == null) return getValue().toString();
		if (formula == null) 
			{
		//	System.out.println("Class: Cell Function: eval Text :if (formula == null)  ");
			return getValueNull();
			}
		String result;
		if(text == null)
		{
			result = formula.evalStringNull();
		}
		else
		{
			result = formula.evalStringNull(text);
		}
	//	System.out.println("Class: Cell Function: eval Text :result "+ result);
		if(result==null)
		{
			return "";
		}
		else    
		{
			return result;
		}
	}

	public CellFormula getFormula() {
		return formula;
	}
}
