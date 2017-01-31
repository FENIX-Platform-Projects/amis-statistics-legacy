package org.fao.fenix.web.modules.core.client.security;

import java.util.HashMap;
import java.util.Map;

import org.fao.fenix.web.modules.core.client.utils.FenixConstants;

import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;

public class FenixGoupListController {
	DataList groupDataList;

	static final Map<String, String> groupLabelMap = new HashMap<String, String>();
	static {
		groupLabelMap.put(FenixRole.ANONYMOUS, "Anonymous Users");
		groupLabelMap.put(FenixSecurityConstant.ALL_USER_GROUP, "All user group");
	}

	public FenixGoupListController(DataList groupDataList) {
		this.groupDataList = groupDataList;
	}

	public void updateGroupList(String[] groupList) {

		groupDataList.removeAll();
		for (String group : groupList) {
			DataListItem item = new DataListItem();
			if (groupLabelMap.get(group) == null)
				item.setText(group);
			else
				item.setText(groupLabelMap.get(group));
			item.setData(FenixConstants.VO, group);

			item.setIconStyle("user_group");
			if (group.equals(FenixRole.ANONYMOUS))
				item.setIconStyle("people");
			if (group.equals(FenixSecurityConstant.ALL_USER_GROUP))
				item.setIconStyle("user_group_all");
			groupDataList.add(item);
		}

	}

}
