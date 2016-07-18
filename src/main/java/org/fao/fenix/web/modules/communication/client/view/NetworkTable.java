package org.fao.fenix.web.modules.communication.client.view;

import java.util.ArrayList;
import java.util.List;

import org.fao.fenix.web.modules.communication.common.services.CommunicationServiceEntry;
import org.fao.fenix.web.modules.communication.common.vo.CommunicationResourceVo;
import org.fao.fenix.web.modules.core.common.exception.FenixGWTException;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.shared.window.client.view.FenixAlert;
import org.fao.fenix.web.modules.shared.window.client.view.utils.LoadingWindow;
import org.fao.fenix.web.modules.table.client.view.utils.TableConstants;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.table.Table;
import com.extjs.gxt.ui.client.widget.table.TableColumn;
import com.extjs.gxt.ui.client.widget.table.TableColumnModel;
import com.extjs.gxt.ui.client.widget.table.TableItem;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class NetworkTable {

	private Table table;
	
	private String[] columnHeaders = new String[]{BabelFish.print().hostLabel()};
	
	public NetworkTable() {
		List<TableColumn> columns = new ArrayList<TableColumn>();
		for (int i = 0; i < 1; i++) { // just in case I'll need to add more headers...
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
		table.setHeight("350px");
	}
	
	public void build(final Table table) {
		System.out.println("BUILDING TABLE");
		final LoadingWindow loading = new LoadingWindow();
		loading.create("Get peer list.");
			try {
				CommunicationServiceEntry.getInstance().getPeerList(new AsyncCallback<List<CommunicationResourceVo>>() {
					public void onSuccess(List<CommunicationResourceVo> vos) {
						loading.destroy();
						System.out.println(vos.size() + " peers...");
						for (CommunicationResourceVo vo : vos) {
							TableItem item = new TableItem(buildRow(vo, "orange"));
							item.setData("host", vo.getHost());
							table.add(item);
						}
						update(table, vos);
					}
					public void onFailure(Throwable caught) {
						loading.destroy();
						FenixAlert.error("RPC Failed", "getPeerList @ CommunicationTable");
					}
				});
			} catch (FenixGWTException e) {
				FenixAlert.alert(BabelFish.print().info(), e.getMessage());
			}
		for (int i = 0 ; i < columnHeaders.length ; i++)
			table.getColumn(i).setText(columnHeaders[i]);
	}
	
	public void update(final Table table, final List<CommunicationResourceVo> peers) {
		final LoadingWindow loading = new LoadingWindow();
		loading.create("Get active peer list.");
			try {
				CommunicationServiceEntry.getInstance().getActivePeerListVo(new AsyncCallback<List<CommunicationResourceVo>>() {
					public void onSuccess(List<CommunicationResourceVo> vos) {
						loading.destroy();
						table.removeAll();
						System.out.println();
						for (CommunicationResourceVo peer : peers) {
							if (contains(vos, peer)) {
								TableItem item = new TableItem(buildRow(peer, "green"));
								item.setData("host", peer.getHost());
								table.add(item);
							} else {
								TableItem item = new TableItem(buildRow(peer, "red"));
								item.setData("host", peer.getHost());
								table.add(item);
							}
						}
					}
					public void onFailure(Throwable caught) {
						loading.destroy();
						FenixAlert.error("RPC Failed", "getPeerList @ CommunicationTable");
					}
				});
			} catch (FenixGWTException e) {
				FenixAlert.alert(BabelFish.print().info(), e.getMessage());
			}
		for (int i = 0 ; i < columnHeaders.length ; i++)
			table.getColumn(i).setText(columnHeaders[i]);
	}
	
	public boolean contains(List<CommunicationResourceVo> vos, CommunicationResourceVo vo) {
		for (CommunicationResourceVo tmp : vos)
			if (tmp.getHost().equals(vo.getHost()))
				return true;
		return false;
	}
	
	public Object[] buildRow(CommunicationResourceVo vo, String colour) {
		Object[] values = new Object[TableConstants.COMMUNICATION_COLUMNS];
		values[0] = "<font color='"  + colour + "'><em>" + vo.getHostLabel() + "</em></font>";
		for (int i = 1 ; i < TableConstants.COMMUNICATION_COLUMNS - 1 ; i++)
			values[i] = "";
		return values;
	}

	public Table getTable() {
		return table;
	}
	
}
