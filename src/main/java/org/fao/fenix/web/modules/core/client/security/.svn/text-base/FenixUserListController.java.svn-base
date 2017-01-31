package org.fao.fenix.web.modules.core.client.security;

import java.util.List;

import org.fao.fenix.web.modules.core.client.utils.FenixConstants;
import org.fao.fenix.web.modules.core.common.vo.FenixUserVo;

import com.extjs.gxt.ui.client.widget.DataList;
import com.extjs.gxt.ui.client.widget.DataListItem;

public class FenixUserListController {
	DataList userDataList;

	public FenixUserListController(DataList userDataList) {
		this.userDataList = userDataList;
	}

	public void updateUserList(List<FenixUserVo> result) {
		userDataList.removeAll();
		for (int i = 0; i < result.size(); i++) {
			FenixUserVo fenixUserVo = (FenixUserVo) result.get(i);
			addUser2DataList(fenixUserVo);

		}
	}

	public void addUser2DataList(FenixUserVo fenixUserVo) {
		DataListItem item = new DataListItem();
		item.setText(fenixUserVo.getFirstName() + " " + fenixUserVo.getLastName() + " (" + fenixUserVo.getLoginName()
				+ ") ");
		String[] roles = fenixUserVo.getRoles();

		if (!fenixUserVo.isEnabled() || roles == null)
			item.setIconStyle("user_red");
		else
			item.setIconStyle("user_green");

		boolean admin = false;
		for (int i = 0; i < roles.length; i++) {
			if (roles[i].equals(FenixRole.ADMIN))
				admin = true;
		}
		if (admin)
			item.setIconStyle("user_suit");

		item.setData(FenixConstants.VO, fenixUserVo);
		userDataList.add(item);
	}

}
