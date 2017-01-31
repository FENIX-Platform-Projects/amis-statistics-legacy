package org.fao.fenix.web.modules.core.client.security.permissionmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class is a copy from org.fao.fenix.core.domain.security.PermissionAssociation with some
 * changes to make it GWT compliant
 * 
 * It only exits to improve the user experience. It gives fast feedback to the user about the
 * associated permissions.
 * 
 * 
 * @author Erik van Ingen
 * 
 */

public class PermissionAssociation {

	static private final Map<String, List<String>> permissionAssociationMap = new HashMap<String, List<String>>();
	static private final Map<String, List<String>> permissionAssociationMapOff = new HashMap<String, List<String>>();

	static {
		List<String> readList = new ArrayList<String>();
		List<String> writeList = new ArrayList<String>();
		List<String> deleteList = new ArrayList<String>();
		List<String> downloadList = new ArrayList<String>();

		List<String> readOffList = new ArrayList<String>();
		List<String> writeOffList = new ArrayList<String>();
		List<String> deleteOffList = new ArrayList<String>();
		List<String> downloadOffList = new ArrayList<String>();

		// ---------------------------------------------------------------------On
		// associated permission for Read
		// associated permission for Read
		readList.add(FenixPermission.READ);
		permissionAssociationMap.put(FenixPermission.READ, readList);

		// associated permission for Write
		writeList.add(FenixPermission.READ);
		writeList.add(FenixPermission.WRITE);
		permissionAssociationMap.put(FenixPermission.WRITE, writeList);

		// associated permission for Delete
		deleteList.add(FenixPermission.READ);
		deleteList.add(FenixPermission.WRITE);
		deleteList.add(FenixPermission.DELETE);
		permissionAssociationMap.put(FenixPermission.DELETE, deleteList);

		// associated permission for Download
		downloadList.add(FenixPermission.DOWNLOAD);
		downloadList.add(FenixPermission.READ);
		permissionAssociationMap.put(FenixPermission.DOWNLOAD, downloadList);

		// ---------------------------------------------------------------------Off

		// associated permission for Read off
		readOffList.add(FenixPermission.READ);
		readOffList.add(FenixPermission.WRITE);
		readOffList.add(FenixPermission.DELETE);
		readOffList.add(FenixPermission.DOWNLOAD);
		permissionAssociationMapOff.put(FenixPermission.READ, readOffList);

		// associated permission for Write off
		writeOffList.add(FenixPermission.WRITE);
		writeOffList.add(FenixPermission.DELETE);
		permissionAssociationMapOff.put(FenixPermission.WRITE, writeOffList);

		// associated permission for Delete off
		deleteOffList.add(FenixPermission.DELETE);
		permissionAssociationMapOff.put(FenixPermission.DELETE, deleteOffList);

		// associated permission for Download off
		downloadOffList.add(FenixPermission.DOWNLOAD);
		permissionAssociationMapOff.put(FenixPermission.DOWNLOAD, downloadOffList);

	}

	public List<String> getAssociatedPermission(String fenixPermission, boolean permissionStatus) {
		if (permissionStatus)
			return permissionAssociationMap.get(fenixPermission);
		else
			return permissionAssociationMapOff.get(fenixPermission);
	}

}
