package org.fao.fenix.web.modules.coding.client.view.utils;


import org.fao.fenix.web.modules.lang.client.BabelFish;

import com.extjs.gxt.ui.client.widget.table.Table;


public class TableHeadersBuilder {
	
	

	
	public static void renameTableHeadersForSearch(Table table) {
		table.getColumn(0).setText(BabelFish.print().code());
		table.getColumn(0).setWidth(120);
		table.getColumn(1).setText(BabelFish.print().label());
		table.getColumn(1).setWidth(260);

		table.getColumn(2).setText(BabelFish.print().langCode());
		table.getColumn(2).setWidth(1);
		table.getColumn(2).setHidden(true);
//		
		table.getColumn(3).setText(BabelFish.print().description());
//		table.getColumn(3).setWidth(250);
		table.getColumn(3).setHidden(true);
		
//		table.getColumn(3).setWidth(250);
		table.getColumn(4).setHidden(true);
		table.getColumn(5).setHidden(true);
	}
	
	public static void renameTableHeadersForCreate(Table table) {
		table.getColumn(0).setText(BabelFish.print().code());
		table.getColumn(0).setWidth(100);
		table.getColumn(1).setText(BabelFish.print().label());
		table.getColumn(1).setWidth(600);
		table.getColumn(2).setText(BabelFish.print().mapped());
		table.getColumn(2).setWidth(200);
	}
	
	
	public static void renameTableHeadersForDcmtConvert(Table table) {
		table.getColumn(0).setText(BabelFish.print().code());
		table.getColumn(0).setWidth(100);
		table.getColumn(1).setText("Dcmt " +BabelFish.print().code());
		table.getColumn(1).setWidth(100);
	}
	
	public static void renameTableHeadersForSearchAll(Table table){
		table.getColumn(0).setText(BabelFish.print().codeSystem());
		table.getColumn(0).setWidth(120);
		table.getColumn(1).setText(BabelFish.print().code());
		table.getColumn(1).setWidth(120);
		table.getColumn(2).setText(BabelFish.print().label());
		table.getColumn(2).setWidth(260);
		table.getColumn(2).setHidden(false);
		table.getColumn(3).setText(BabelFish.print().langCode());
//		table.getColumn(3).setMaxWidth(1);
		table.getColumn(3).setHidden(true);
//		table.getColumn(4).setText(I18N.print().description());
//		table.getColumn(4).setWidth(100);
		table.getColumn(4).setHidden(true);
		table.getColumn(5).setHidden(true);
	}
}
