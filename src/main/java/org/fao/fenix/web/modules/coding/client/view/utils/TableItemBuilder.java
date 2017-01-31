package org.fao.fenix.web.modules.coding.client.view.utils;



import org.fao.fenix.web.modules.coding.common.vo.ClassificationVo;
import org.fao.fenix.web.modules.coding.common.vo.CodeVo;
import org.fao.fenix.web.modules.coding.common.vo.CodingSystemVo;
import org.fao.fenix.web.modules.coding.common.vo.MappingVo;

import com.extjs.gxt.ui.client.widget.table.TableItem;

public class TableItemBuilder {
	

	public static TableItem buildTableItem(CodingSystemVo value) {
		Object[] values = new Object[10];
		values[0] = value.getTitle();
		values[1] = value.getRegion();
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItem(CodeVo value) {
		Object[] values = new Object[10];
		values[0] = value.getCode();
//		values[1] = value.getLabel();
		if (value.getLabel().length() > 40) {
			values[1] = value.getLabel().substring(0, 40) + "...";
		}
		else {
			values[1] = value.getLabel();
		}
		values[2] = value.getLangCode();
		values[3] = value.getDescription();
		values[4] = value.getLabel();
		TableItem item = new TableItem(values);
		return item;
	}
	
	
	
	
	public static TableItem buildTableItemWithCs(CodeVo value) {
		Object[] values = new Object[10];
		if ( value.getRegion().equals("")) {
			values[0] = value.getTitle();
		}
		else {
			values[0] = value.getTitle() + ", " + value.getRegion();
		}
		values[1] = value.getCode();
		if (value.getLabel().length() > 40) {
			values[2] = value.getLabel().substring(0, 40) + "...";
		}
		else {
			values[2] = value.getLabel();
		}
//		values[2] = value.getLabel();
		values[3] = value.getLangCode();
		values[4] = value.getDescription();	
		values[5] = value.getLabel();
		
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItemCreator(CodeVo value) {
		Object[] values = new Object[10];
		values[0] = value.getCode();
//		values[1] = value.getLabel();
		if (value.getLabel().length() > 40) {
			values[1] = value.getLabel().substring(0, 40) + "...";
		}
		else {
			values[1] = value.getLabel();
		}
		TableItem item = new TableItem(values);
		return item;
	}
	
	

	public static TableItem buildTableItem(MappingVo value) {
		Object[] values = new Object[10];
		values[0] = value.getFromCode();
		values[1] = value.getDcmtCode();
		TableItem item = new TableItem(values);
		return item;
	}
	
	public static TableItem buildTableItem(ClassificationVo value) {
		Object[] values = new Object[10];
		values[0] = value.getCode();
		values[1] = value.getLabel();
		values[2] = value.getLangCode();
		values[3] = value.getDescription();
		values[4] = value.getDcmtCode();
		TableItem item = new TableItem(values);
		return item;
	}
	
}
