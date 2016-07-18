package org.fao.fenix.web.modules.x.common.vo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class RSSConfigurationVO implements IsSerializable {
	
	private String channelTitle;
	
	private String channelDescription;
	
	private String channelLink;
	
	private String imageLink;
	
	public RSSConfigurationVO() {
		this.setChannelDescription("");
		this.setChannelLink("");
		this.setChannelTitle("");
		this.setImageLink("");
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public String getChannelDescription() {
		return channelDescription;
	}

	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}

	public String getChannelLink() {
		return channelLink;
	}

	public void setChannelLink(String channelLink) {
		this.channelLink = channelLink;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

}