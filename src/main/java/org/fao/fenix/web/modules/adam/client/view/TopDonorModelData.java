package org.fao.fenix.web.modules.adam.client.view;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.IsSerializable;

@SuppressWarnings("serial")
public class TopDonorModelData extends BaseModel implements IsSerializable {

	private String donor;
	
	private Double budget;
	
	private String channel;
	
	private String sector;
	
	private String recipient;

	public String getDonor() {
		return donor;
	}

	public void setDonor(String donor) {
		this.donor = donor;
		set("donor", this.donor);
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
		set("budget", this.budget);
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
		set("channel", this.channel);
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
		set("sector", this.sector);
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
		set("recipient", this.recipient);
	}
	
}
