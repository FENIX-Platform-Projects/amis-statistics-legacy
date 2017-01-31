/*
 */

package org.fao.fenix.web.modules.map.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * These are the fields in a ClientGeoView that can be modified and saved by the user.
 *
 * @author etj
 */

public class EditableClientGeoView implements IsSerializable {
	private String title;
	private String stylename;
	private boolean hidden;

//	/**
//	 * The position of this layer in the current map.
//	 * This field should not be considered client-side, where the position is
//	 * directly read from the container list.
//	 * It matters when converting the cgv into a GeoView.
//	 */
//	private Integer position = -1;

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getStyleName() {
		return stylename;
	}

	public void setStyleName(String sn) {
		this.stylename = sn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public Integer getPosition() {
//		return position;
//	}
//
//	public void setPosition(Integer position) {
//		this.position = position;
//	}

}
