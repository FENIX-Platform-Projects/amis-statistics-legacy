/*
 */
package org.fao.fenix.web.modules.core.common.services.superif;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * This interface provide methods for TableView to display data.
 * 
 * It may be used for instance to supply dataset content or layer tabular data.
 * 
 * @author etj
 */
public interface TableViewDataProviderAsync {

	public void getColumnNames(long wmsMapProviderId, AsyncCallback callback);
	public void getRecords(long wmsMapProviderId, List<String>columnNames, AsyncCallback<List<List<String>>> callback);
}
