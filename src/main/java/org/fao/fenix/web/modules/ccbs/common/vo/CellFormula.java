package org.fao.fenix.web.modules.ccbs.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CellFormula implements IsSerializable
{
	private Cell cell;

	void init(Cell cell)
	{
		this.cell = cell;
	}

	public double eval() { return cell.getValue(); }

	// returns the value of a cell; binding is positional
	protected double v(int i)
	{
		Cell ref = cell.getBkRefs().get(i);
		return ref.getValue();
	}
}