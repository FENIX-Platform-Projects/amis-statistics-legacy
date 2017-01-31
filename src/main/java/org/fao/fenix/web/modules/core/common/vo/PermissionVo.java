/*
 */

package org.fao.fenix.web.modules.core.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author ETj
 */
public class PermissionVo implements IsSerializable {

	private boolean canRead = false;
	private boolean canWrite = false;
	private boolean canDelete = false;
	private boolean canDownload = false;

	public PermissionVo() {
	}

	public boolean canDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	public boolean canDownload() {
		return canDownload;
	}

	public void setCanDownload(boolean canDownload) {
		this.canDownload = canDownload;
	}

	public boolean canRead() {
		return canRead;
	}

	public void setCanRead(boolean canRead) {
		this.canRead = canRead;
	}

	public boolean canWrite() {
		return canWrite;
	}

	public void setCanWrite(boolean canWrite) {
		this.canWrite = canWrite;
	}

}
