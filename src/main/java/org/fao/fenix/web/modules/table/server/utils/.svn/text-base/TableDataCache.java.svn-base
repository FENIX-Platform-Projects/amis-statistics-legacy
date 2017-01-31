package org.fao.fenix.web.modules.table.server.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.table.common.model.DatasetRowModel;

public class TableDataCache {

	static Map<Long, List<DatasetRowModel>> tableMap = new HashMap<Long, List<DatasetRowModel>>();
	
	public static  List<DatasetRowModel> getTableData(Long resourceId) {
		return tableMap.get(resourceId);
	}
	
	public static void addTableDataToCache(Long resourceId, List<DatasetRowModel> data) {
		tableMap.put(resourceId, data);
	}
	
	
	public static Map<Long, List<DatasetRowModel>> getTableCache() {
		return tableMap;
	}
	
	
} 