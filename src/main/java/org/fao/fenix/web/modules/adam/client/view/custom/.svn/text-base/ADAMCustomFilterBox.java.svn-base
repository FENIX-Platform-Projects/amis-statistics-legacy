package org.fao.fenix.web.modules.adam.client.view.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.fao.fenix.web.modules.adam.client.control.ADAMController;
import org.fao.fenix.web.modules.adam.common.enums.ADAMBoxContent;
import org.fao.fenix.web.modules.lang.client.BabelFish;
import org.fao.fenix.web.modules.metadataeditor.common.vo.modeldata.GaulModelData;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.IconButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;


public class ADAMCustomFilterBox {
	
	private ContentPanel panel;
	
	private ADAMCustomFilter sector;
	
	private ADAMCustomFilter donor;
	
	private ADAMCustomFilter recipient;

	public ADAMCustomFilterBox() {
	}
	
	public ContentPanel build(String selectedParam) {
		panel = new ContentPanel();	
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);	
		//panel.setSpacing(10);
		donor = new ADAMCustomFilter();
		recipient = new ADAMCustomFilter();
		sector = new ADAMCustomFilter();
		System.out.println("building: " + selectedParam);
		if ( !selectedParam.equalsIgnoreCase("recipientcode")) {
			panel.add(recipient.build("Recipient Country", "recipientcode", "Gaul_"));
		}
		if  ( !selectedParam.equalsIgnoreCase("donorcode")) {
			panel.add(donor.build("Resource Partner", "donorcode", "Donor_"));
		}
		if  ( !selectedParam.equalsIgnoreCase("dac_sector")) {
			panel.add(sector.build("Sector", "dac_sector", "Dac_"));
		}

		return panel;
	}

	public ADAMCustomFilter getSector() {
		return sector;
	}

	public void setSector(ADAMCustomFilter sector) {
		this.sector = sector;
	}

	public ADAMCustomFilter getDonor() {
		return donor;
	}

	public void setDonor(ADAMCustomFilter donor) {
		this.donor = donor;
	}

	public ADAMCustomFilter getRecipient() {
		return recipient;
	}

	public void setRecipient(ADAMCustomFilter recipient) {
		this.recipient = recipient;
	}
	
	
	
	
	

	
	
}
