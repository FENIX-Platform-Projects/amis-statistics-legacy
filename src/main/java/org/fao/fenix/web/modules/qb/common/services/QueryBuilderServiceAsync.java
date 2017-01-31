/**
 *
 * FENIX (Food security and Early warning Network and Information Exchange)
 *
 * Copyright (c) 2008, by FAO of UN under the EC-FAO Food Security
Information for Action Programme
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.fao.fenix.web.modules.qb.common.services;

import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.qb.common.vo.DatasetVO;
import org.fao.fenix.web.modules.qb.common.vo.DimensionVO;
import org.fao.fenix.web.modules.table.common.model.DimensionItemModel;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface QueryBuilderServiceAsync {

	public void getCombinedDimensionList(List<Long> datasetIdList, AsyncCallback<Map<String, DimensionVO>> callback);
	
	public void getDatasetsInfoMap(List<Long> datasetIdList, AsyncCallback<Map<Long, DatasetVO>> callback);
		
	public void getDimensionValues(List<Long> datasetIds, String dataType, String codingSystemTitle, String codingSystemRegion, AsyncCallback<List<DimensionItemModel>> callback);
	
}

