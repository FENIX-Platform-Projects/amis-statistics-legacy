package org.fao.fenix.web.modules.amis.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CellFormula implements IsSerializable
{
	private Cell cell;

	void init(Cell cell)
	{
		this.cell = cell;
	}

	public double eval() { return cell.getValue(); }
	
	public String evalStringNull() { return evalStringNull(null); }
	
	public String evalStringNull(String text) { if(text==null)return cell.getValueNull().toString(); else return cell.getValueNull(text).toString();}
	
	public String evalString() { return cell.getValue().toString(); }

	// returns the value of a cell; binding is positional
	protected double v(int i)
	{
		Cell ref = cell.getBkRefs().get(i);
		return ref.getValue();
	}
	
	protected String vNull(int i)
	{
		Cell ref = cell.getBkRefs().get(i);
		return ref.getValueNull();
	}
	
	protected boolean nullData(int i)
	{
		Cell ref = cell.getBkRefs().get(i);
		return ref.isNullFromDb();
	}

	public Cell getCell() {
		return cell;
	}
}