package org.fao.fenix.web.modules.fsatmis.server.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fenix.web.modules.fsatmis.common.vo.FSATMISModelData;

public class FSATMISTableCacheData {

static Map<Long, List<FSATMISModelData>> fsatmisTableMap = new HashMap<Long, List<FSATMISModelData>>();
	
	public static List<FSATMISModelData> getTableData(Long resourceId) {
		return fsatmisTableMap.get(resourceId);
	}
	
	public static void addTableDataToCache(Long resourceId, List<FSATMISModelData> data) {
		fsatmisTableMap.put(resourceId, data);
	}
	
	public static Map<Long, List<FSATMISModelData>> getTableCache() {
		return fsatmisTableMap;
	}
	
}