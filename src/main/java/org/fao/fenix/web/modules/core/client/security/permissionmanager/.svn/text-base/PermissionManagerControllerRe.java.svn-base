package org.fao.fenix.web.modules.core.client.security.permissionmanager;

import java.util.List;

import org.fao.fenix.web.modules.core.common.vo.FenixResourceVo;

/**
 * This PermissionManagerController is called from the RE with a predefined set of resources.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class PermissionManagerControllerRe extends PermissionManagerController {

	List<FenixResourceVo> fenixResourceVoList;

	/**
	 * In order to implement the Singleton behavior, the constructor is protected.
	 */
	protected PermissionManagerControllerRe() {
		super();
	}

	/**
	 * instantiates an instance of this class when not yet exist or when it is not an instance of
	 * this class.
	 * 
	 * @param fenixResourceVoList
	 * @return
	 */
	public static PermissionManagerControllerRe getInstance(List<FenixResourceVo> fenixResourceVoList) {
		if (instance == null || !(instance instanceof PermissionManagerControllerRe)) {
			instance = new PermissionManagerControllerRe();
			instance.build();
		}
		((PermissionManagerControllerRe) instance).fenixResourceVoList = fenixResourceVoList;
		initInstance();
		return (PermissionManagerControllerRe) instance;
	}

	/**
	 * this method takes the selection from the RE instead of reading the whole fornicating list
	 * from the DB.
	 */
	protected void loadResourceList() {
		permissionManagerView.resourceDataList.removeAll();
		addFenixResourceVoList2ResourceDataList(fenixResourceVoList);
		permissionManagerView.resourceDataList.setSelectedItems(permissionManagerView.resourceDataList.getItems());
	}

}
