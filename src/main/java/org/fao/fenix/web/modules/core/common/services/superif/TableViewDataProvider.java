/*
 */
package org.fao.fenix.web.modules.core.common.services.superif;

import java.util.List;

import org.springframework.security.annotation.Secured;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 *
 * @author etj
 */

@Secured( { "IS_AUTHENTICATED_ANONYMOUSLY" })
public interface TableViewDataProvider extends RemoteService {

	public String[] getColumnNames(long wmsMapProviderId);

	public List<List<String>> getRecords(long wmsMapProviderId, List<String> columnNames);
}
