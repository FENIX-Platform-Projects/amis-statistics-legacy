package org.fao.fenix.web.modules.communication.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.communication.common.services.CommunicationServiceEntry;
import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableColumn;
import com.extjs.gxt.ui.client.widget.table.TableColumnModel;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LocalTable {

private Table table;
	
	private String[] columnHeaders = new String[]{BabelFish.print().title(), 
												  BabelFish.print().type(), 
												  BabelFish.print().group()};
	
	public LocalTable() {
		List<TableColumn> columns = new ArrayList<TableColumn>();
		for (int i = 0; i < TableConstants.COMMUNICATION_COLUMNS; i++) {
			TableColumn column = new TableColumn("", 200);
			column.setSortable(true);
			column.setResizable(true);
			column.setMinWidth(200);
			column.setAlignment(HorizontalAlignment.LEFT);
			columns.add(column);
		}
		TableColumnModel cm = new TableColumnModel(columns);
		table = new Table(cm);
		table.setSelectionMode(Style.SelectionMode.MULTI);
//		table.addTableListener(CommunicationController.getLocalDoubleClickListener(table));
	}
	
	public void build(final Table table, final int startIndex, final int items) {
		try {
			CommunicationServiceEntry.getInstance().findAllLocalPaged(startIndex, items, new AsyncCallback<List<CommunicationResourceVo>>() {
					public void onSuccess(List<CommunicationResourceVo> vos) {
						for (CommunicationResourceVo vo : vos) {
							TableItem item = new TableItem(buildRow(vo)); 
							item.setData("resourceId", vo.getLocalId());
							item.setData("type", vo.getType());
							table.add(item);
						}
					}
					public void onFailure(Throwable caught) {
						FenixAlert.error("RPC Failed", "findAllPaged @ CommunicationTable");
					}
				});
		} catch (FenixGWTException e) {
			FenixAlert.alert(BabelFish.print().info(), e.getMessage());
		}
		for (int i = 0 ; i < columnHeaders.length ; i++)
			table.getColumn(i).setText(columnHeaders[i]);
	}
	
	public void build(final Table table, final List<CommunicationResourceVo> vos) {
		for (CommunicationResourceVo vo : vos) {
			TableItem item = new TableItem(buildRow(vo));
			item.setData("resourceId", vo.getLocalId());
			item.setData("type", vo.getType());
			table.add(item);
		}
		for (int i = 0; i < columnHeaders.length; i++)
			table.getColumn(i).setText(columnHeaders[i]);
	}
	
	public Object[] buildRow(CommunicationResourceVo vo) {
		Object[] values = new Object[TableConstants.COMMUNICATION_COLUMNS];
		values[0] = vo.getTitle();
		if (vo.getType().equalsIgnoreCase("Dataset"))
			values[1] = BabelFish.print().dataset();
		else if (vo.getType().equalsIgnoreCase("TextView"))
			values[1] = BabelFish.print().text();
		values[2] = vo.getOGroup();
		for (int i = 3 ; i < TableConstants.COMMUNICATION_COLUMNS - 3 ; i++)
			values[i] = "";
		return values;
	}

	public Table getTable() {
		return table;
	}
	
}
